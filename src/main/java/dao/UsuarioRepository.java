package dao;

import modelo.Usuario;
import java.util.Optional;

public interface UsuarioRepository {

    // Devolve o usuário se login e senha conferem; vazio caso contrário.
    Optional<Usuario> buscarPorCredenciais(String login, String senhaPura);

    // Lança IllegalArgumentException se o login já existir.
    void adicionar(Usuario usuario);

    // Devolve true se algum usuário foi excluído.
    boolean excluirPorLogin(String login);
}
