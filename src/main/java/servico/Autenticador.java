package servico;

import dao.UsuarioRepository;
import modelo.Usuario;
import java.util.Optional;

// Responsável apenas pela regra de autenticação.
public class Autenticador {

    private final UsuarioRepository repositorio;

    public Autenticador(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Optional<Usuario> autenticar(String login, String senha) {
        if (login == null || login.trim().isEmpty() || senha == null || senha.isEmpty()) {
            return Optional.empty();
        }
        return repositorio.buscarPorCredenciais(login.trim(), senha);
    }
}
