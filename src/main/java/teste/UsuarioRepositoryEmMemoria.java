package teste;

import dao.UsuarioRepository;
import modelo.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Repositório falso, só em memória, para testar regras sem depender do MySQL.
public class UsuarioRepositoryEmMemoria implements UsuarioRepository {

    private final Map<String, Usuario> usuarios = new HashMap<>();

    @Override
    public Optional<Usuario> buscarPorCredenciais(String login, String senhaPura) {
        Usuario u = usuarios.get(login.toLowerCase());
        return (u != null && u.getSenha().equals(senhaPura)) ? Optional.of(u) : Optional.empty();
    }

    @Override
    public void adicionar(Usuario usuario) {
        String chave = usuario.getLogin().toLowerCase();
        if (usuarios.containsKey(chave)) {
            throw new IllegalArgumentException("Já existe um usuário com o login '" + usuario.getLogin() + "'.");
        }
        usuarios.put(chave, usuario);
    }

    @Override
    public boolean excluirPorLogin(String login) {
        return usuarios.remove(login.toLowerCase()) != null;
    }
}
