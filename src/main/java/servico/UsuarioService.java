package servico;

import dao.UsuarioRepository;
import modelo.Usuario;

// Regras de negócio sobre usuários: só o supervisor cadastra e exclui.
public class UsuarioService {

    private final UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrar(Usuario solicitante, String login, String senha, boolean supervisor) {
        ControleAcesso.exigirSupervisor(solicitante);
        // O construtor de Usuario valida login e senha.
        repositorio.adicionar(new Usuario(0, login, senha, supervisor));
    }

    public boolean excluir(Usuario solicitante, String login) {
        ControleAcesso.exigirSupervisor(solicitante);
        return repositorio.excluirPorLogin(login);
    }
}
