package servico;

import dao.MensagemRepository;
import modelo.Defeito;
import modelo.Mensagem;
import modelo.Usuario;
import java.util.List;

// Regras de negócio sobre mensagens: qualquer usuário consulta; só o supervisor cadastra e exclui.
public class MensagemService {

    private final MensagemRepository repositorio;

    public MensagemService(MensagemRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Mensagem> listarPorDefeito(Defeito defeito) {
        return repositorio.listarPorDefeito(defeito);
    }

    public Mensagem cadastrar(Usuario solicitante, Defeito defeito, String titulo, String texto) {
        ControleAcesso.exigirSupervisor(solicitante);
        // O construtor de Mensagem valida defeito, título e texto.
        Mensagem mensagem = new Mensagem(0, defeito, titulo, texto);
        repositorio.adicionar(mensagem);
        return mensagem;
    }

    public void excluir(Usuario solicitante, Mensagem mensagem) {
        ControleAcesso.exigirSupervisor(solicitante);
        if (mensagem == null) {
            throw new IllegalArgumentException("Selecione uma mensagem para excluir.");
        }
        repositorio.excluir(mensagem.getIdMensagem());
    }
}
