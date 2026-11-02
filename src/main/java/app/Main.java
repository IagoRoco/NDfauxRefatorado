package app;

import dao.DefeitoDAO;
import dao.DefeitoRepository;
import dao.MensagemDAO;
import dao.RepositorioException;
import dao.UsuarioDAO;
import dao.UsuarioRepository;
import mensageria.MensageiroSimulado;
import modelo.Defeito;
import modelo.Mensagem;
import modelo.Usuario;
import servico.Autenticador;
import servico.ControleAcesso;
import servico.EnvioService;
import servico.MensagemService;
import servico.MontadorMensagem;
import servico.PermissaoNegadaException;
import servico.UsuarioService;
import teste.MensagemRepositoryEmMemoria;
import teste.UsuarioRepositoryEmMemoria;
import java.util.List;
import java.util.Optional;

// Testes do projeto refatorado. As seções 1 a 4 rodam sem banco de dados;
// a seção 5 usa o MySQL (banco nd_db) e é pulada se o banco não estiver disponível.
public class Main {

    private static int total = 0;
    private static int falhas = 0;

    public static void main(String[] args) {
        System.out.println("=== NDfaux - testes do projeto refatorado ===");

        testarModelo();
        testarMontadorEEnvio();
        testarAutenticacaoEControleAcesso();
        testarServicosComRepositoriosEmMemoria();
        testarIntegracaoComMySQL();

        System.out.println("\n=== Resultado: " + total + " verificações, " + falhas + " falha(s) ===");
        if (falhas > 0) {
            System.exit(1);
        }
    }

    // ---------------------------------------------------------------- 1. Modelo

    private static void testarModelo() {
        System.out.println("\n[1] Modelo (validações das classes de dados)");

        Defeito defeito = new Defeito(1, "  Aquecimento ");
        verificar("Defeito remove espaços do nome", "Aquecimento".equals(defeito.getNome()));
        verificar("Defeito com nome vazio é recusado", lanca(IllegalArgumentException.class, () -> new Defeito(1, "  ")));
        verificar("Defeitos com o mesmo id são iguais", new Defeito(1, "A").equals(new Defeito(1, "B")));

        verificar("Usuario com login vazio é recusado", lanca(IllegalArgumentException.class, () -> new Usuario(0, "", "123", false)));
        verificar("Usuario com senha vazia é recusado", lanca(IllegalArgumentException.class, () -> new Usuario(0, "ana", "", false)));
        verificar("Usuario com login maior que a coluna é recusado",
                lanca(IllegalArgumentException.class, () -> new Usuario(0, "x".repeat(Usuario.TAMANHO_MAXIMO_LOGIN + 1), "1", false)));
        verificar("Usuario.toString não mostra a senha", !new Usuario(0, "ana", "segredo", false).toString().contains("segredo"));

        verificar("Mensagem sem defeito é recusada", lanca(IllegalArgumentException.class, () -> new Mensagem(0, null, "T", "Texto")));
        verificar("Mensagem sem título é recusada", lanca(IllegalArgumentException.class, () -> new Mensagem(0, defeito, " ", "Texto")));
        verificar("Mensagem sem texto é recusada", lanca(IllegalArgumentException.class, () -> new Mensagem(0, defeito, "T", null)));
        verificar("Mensagem com título maior que a coluna é recusada",
                lanca(IllegalArgumentException.class, () -> new Mensagem(0, defeito, "x".repeat(Mensagem.TAMANHO_MAXIMO_TITULO + 1), "Texto")));
        verificar("Mensagem com texto maior que a coluna é recusada",
                lanca(IllegalArgumentException.class, () -> new Mensagem(0, defeito, "T", "x".repeat(Mensagem.TAMANHO_MAXIMO_TEXTO + 1))));
    }

    // ------------------------------------------------- 2. Montagem e envio

    private static void testarMontadorEEnvio() {
        System.out.println("\n[2] Montagem do texto e envio (Mensageiro simulado)");

        MontadorMensagem montador = new MontadorMensagem();
        MensageiroSimulado mensageiro = new MensageiroSimulado();
        EnvioService envio = new EnvioService(montador, mensageiro);

        Defeito defeito = new Defeito(1, "Aquecimento");
        Mensagem mensagem = new Mensagem(1, defeito, "Aviso", "Seu aparelho está sem defeito.");

        String texto = montador.montar("Maria", "Galaxy A15", mensagem);
        verificar("Texto começa com a saudação e o nome", texto.startsWith("Olá Maria, tudo bem?"));
        verificar("Texto cita o modelo do aparelho", texto.contains("Galaxy A15"));
        verificar("Texto termina com o texto da mensagem", texto.endsWith("Seu aparelho está sem defeito."));
        verificar("Montar sem mensagem é recusado", lanca(IllegalArgumentException.class, () -> montador.montar("Maria", "X", null)));

        envio.enviar("Maria", "Galaxy A15", "(31) 9 8765-4321", mensagem);
        verificar("Envio entrega só os dígitos do número", "31987654321".equals(mensageiro.getUltimoNumero()));
        verificar("Envio entrega o texto montado", texto.equals(mensageiro.getUltimoTexto()));

        verificar("Número incompleto é recusado",
                lanca(IllegalArgumentException.class, () -> envio.enviar("Maria", "X", "(31) 9 8765-____", mensagem)));
        verificar("Número nulo é recusado",
                lanca(IllegalArgumentException.class, () -> envio.enviar("Maria", "X", null, mensagem)));
        verificar("Envio sem mensagem é recusado",
                lanca(IllegalArgumentException.class, () -> envio.enviar("Maria", "X", "(31) 9 8765-4321", null)));
    }

    // ------------------------------------- 3. Autenticação e controle de acesso

    private static void testarAutenticacaoEControleAcesso() {
        System.out.println("\n[3] Autenticação e controle de acesso (repositório em memória)");

        UsuarioRepository repo = new UsuarioRepositoryEmMemoria();
        repo.adicionar(new Usuario(0, "ana", "1234", true));
        repo.adicionar(new Usuario(0, "bia", "abcd", false));
        Autenticador autenticador = new Autenticador(repo);

        Optional<Usuario> ana = autenticador.autenticar("ana", "1234");
        verificar("Login correto autentica", ana.isPresent());
        verificar("Supervisor é reconhecido", ana.isPresent() && ana.get().isSupervisor());
        verificar("Login com espaços nas pontas autentica", autenticador.autenticar("  ana ", "1234").isPresent());
        verificar("Senha errada não autentica", !autenticador.autenticar("ana", "errada").isPresent());
        verificar("Login inexistente não autentica", !autenticador.autenticar("zeca", "1234").isPresent());
        verificar("Login vazio não autentica", !autenticador.autenticar("", "1234").isPresent());
        verificar("Valores nulos não autenticam", !autenticador.autenticar(null, null).isPresent());

        Usuario supervisor = new Usuario(0, "sup", "x", true);
        Usuario comum = new Usuario(0, "com", "x", false);
        verificar("Supervisor pode gerenciar", ControleAcesso.podeGerenciar(supervisor));
        verificar("Usuário comum não pode gerenciar", !ControleAcesso.podeGerenciar(comum));
        verificar("Usuário nulo não pode gerenciar", !ControleAcesso.podeGerenciar(null));
        verificar("exigirSupervisor bloqueia usuário comum",
                lanca(PermissaoNegadaException.class, () -> ControleAcesso.exigirSupervisor(comum)));
    }

    // ----------------------------------------- 4. Serviços (sem banco de dados)

    private static void testarServicosComRepositoriosEmMemoria() {
        System.out.println("\n[4] Serviços de usuário e de mensagem (repositórios em memória)");

        Usuario supervisor = new Usuario(0, "sup", "x", true);
        Usuario comum = new Usuario(0, "com", "x", false);

        // Usuários
        UsuarioRepository usuarios = new UsuarioRepositoryEmMemoria();
        UsuarioService usuarioService = new UsuarioService(usuarios);

        usuarioService.cadastrar(supervisor, "carla", "1234", false);
        verificar("Supervisor cadastra usuário", new Autenticador(usuarios).autenticar("carla", "1234").isPresent());
        verificar("Usuário comum não cadastra usuário",
                lanca(PermissaoNegadaException.class, () -> usuarioService.cadastrar(comum, "dani", "1234", false)));
        verificar("Login duplicado é recusado",
                lanca(IllegalArgumentException.class, () -> usuarioService.cadastrar(supervisor, "carla", "1234", false)));
        verificar("Login vazio é recusado",
                lanca(IllegalArgumentException.class, () -> usuarioService.cadastrar(supervisor, "", "1234", false)));
        verificar("Usuário comum não exclui usuário",
                lanca(PermissaoNegadaException.class, () -> usuarioService.excluir(comum, "carla")));
        verificar("Supervisor exclui usuário existente", usuarioService.excluir(supervisor, "carla"));
        verificar("Excluir usuário inexistente devolve false", !usuarioService.excluir(supervisor, "carla"));

        // Mensagens
        MensagemService mensagemService = new MensagemService(new MensagemRepositoryEmMemoria());
        Defeito defeito = new Defeito(1, "Aquecimento");

        Mensagem nova = mensagemService.cadastrar(supervisor, defeito, "Teste", "Texto de teste");
        verificar("Supervisor cadastra mensagem e recebe o id", nova.getIdMensagem() > 0);
        verificar("Mensagem cadastrada aparece na listagem", mensagemService.listarPorDefeito(defeito).contains(nova));
        verificar("Qualquer usuário consulta mensagens", mensagemService.listarPorDefeito(defeito).size() == 1);
        verificar("Usuário comum não cadastra mensagem",
                lanca(PermissaoNegadaException.class, () -> mensagemService.cadastrar(comum, defeito, "Outra", "Texto")));
        verificar("Título vazio é recusado",
                lanca(IllegalArgumentException.class, () -> mensagemService.cadastrar(supervisor, defeito, "", "Texto")));
        verificar("Usuário comum não exclui mensagem",
                lanca(PermissaoNegadaException.class, () -> mensagemService.excluir(comum, nova)));
        verificar("Excluir sem selecionar mensagem é recusado",
                lanca(IllegalArgumentException.class, () -> mensagemService.excluir(supervisor, null)));
        mensagemService.excluir(supervisor, nova);
        verificar("Mensagem excluída some da listagem", mensagemService.listarPorDefeito(defeito).isEmpty());
    }

    // ------------------------------------------------- 5. Integração com MySQL

    private static void testarIntegracaoComMySQL() {
        System.out.println("\n[5] Integração com o MySQL (banco nd_db + arquivo db.properties)");

        DefeitoRepository defeitoRepo = new DefeitoDAO();
        List<Defeito> defeitos;
        try {
            defeitos = defeitoRepo.listarTodos();
        } catch (RepositorioException e) {
            System.out.println("  PULADO: não foi possível acessar o banco (" + causa(e) + ")");
            return;
        }
        verificar("DefeitoDAO lista os defeitos cadastrados", !defeitos.isEmpty());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Autenticador autenticador = new Autenticador(usuarioDAO);

        Optional<Usuario> adm = autenticador.autenticar("adm", "123");
        Optional<Usuario> user = autenticador.autenticar("user", "123");
        verificar("Login do supervisor (adm) funciona", adm.isPresent() && adm.get().isSupervisor());
        verificar("Login do usuário comum (user) funciona", user.isPresent() && !user.get().isSupervisor());
        verificar("Usuario lido do banco traz o id", adm.isPresent() && adm.get().getIdUsuario() > 0);
        verificar("Senha errada é recusada pelo banco", !autenticador.autenticar("adm", "errada").isPresent());

        if (!adm.isPresent() || !user.isPresent()) {
            System.out.println("  PULADO: os usuários adm/user (senha 123) do script não foram encontrados.");
            return;
        }

        final String loginTeste = "teste_refat_main";
        UsuarioService usuarioService = new UsuarioService(usuarioDAO);
        usuarioDAO.excluirPorLogin(loginTeste); // limpa sobra de execução anterior
        try {
            usuarioService.cadastrar(adm.get(), loginTeste, "1234", false);
            verificar("Cadastro de usuário grava no banco", autenticador.autenticar(loginTeste, "1234").isPresent());
            verificar("Login duplicado é recusado com mensagem amigável",
                    lanca(IllegalArgumentException.class, () -> usuarioService.cadastrar(adm.get(), loginTeste, "1234", false)));
            verificar("Usuário comum não cadastra no banco",
                    lanca(PermissaoNegadaException.class, () -> usuarioService.cadastrar(user.get(), "outro_teste", "1", false)));
        } finally {
            verificar("Exclusão de usuário remove do banco", usuarioDAO.excluirPorLogin(loginTeste));
        }

        MensagemService mensagemService = new MensagemService(new MensagemDAO());
        Defeito defeito = defeitos.get(0);
        Mensagem nova = null;
        try {
            nova = mensagemService.cadastrar(adm.get(), defeito, "Teste automático", "Mensagem criada pelo Main.");
            verificar("Cadastro de mensagem devolve o id gerado pelo banco", nova.getIdMensagem() > 0);
            verificar("Mensagem aparece na listagem do banco", mensagemService.listarPorDefeito(defeito).contains(nova));
        } finally {
            if (nova != null && nova.getIdMensagem() > 0) {
                mensagemService.excluir(adm.get(), nova);
                verificar("Exclusão de mensagem remove do banco", !mensagemService.listarPorDefeito(defeito).contains(nova));
            }
        }
    }

    // ---------------------------------------------------------------- apoio

    private static void verificar(String descricao, boolean condicao) {
        total++;
        if (!condicao) {
            falhas++;
        }
        System.out.println("  " + (condicao ? "OK     " : "FALHOU ") + "- " + descricao);
    }

    private static boolean lanca(Class<? extends Throwable> tipo, Runnable acao) {
        try {
            acao.run();
            return false;
        } catch (Throwable t) {
            return tipo.isInstance(t);
        }
    }

    private static String causa(RepositorioException e) {
        return e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
    }
}
