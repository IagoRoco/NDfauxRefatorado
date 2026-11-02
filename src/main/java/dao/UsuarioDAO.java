package dao;

import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Optional;

public class UsuarioDAO implements UsuarioRepository {

    // A senha é guardada como hash MD5 calculado pelo próprio MySQL.
    private static final String SQL_BUSCAR =
            "SELECT id, login, senha, administrador FROM usuario WHERE login = ? AND senha = MD5(?)";
    private static final String SQL_INSERIR =
            "INSERT INTO usuario (login, senha, administrador) VALUES (?, MD5(?), ?)";
    private static final String SQL_EXCLUIR =
            "DELETE FROM usuario WHERE login = ?";

    @Override
    public Optional<Usuario> buscarPorCredenciais(String login, String senhaPura) {
        try (Connection conn = ConexaoFactory.criarConexao();
             PreparedStatement ps = conn.prepareStatement(SQL_BUSCAR)) {

            ps.setString(1, login);
            ps.setString(2, senhaPura);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Usuario(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getBoolean("administrador")));
                }
            }

        } catch (SQLException e) {
            throw new RepositorioException("Falha ao validar o login.", e);
        }

        return Optional.empty();
    }

    @Override
    public void adicionar(Usuario usuario) {
        try (Connection conn = ConexaoFactory.criarConexao();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setBoolean(3, usuario.isSupervisor());
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    usuario.setIdUsuario(chaves.getInt(1));
                }
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            // A coluna login é UNIQUE no banco.
            throw new IllegalArgumentException("Já existe um usuário com o login '" + usuario.getLogin() + "'.");
        } catch (SQLException e) {
            throw new RepositorioException("Falha ao cadastrar o usuário.", e);
        }
    }

    @Override
    public boolean excluirPorLogin(String login) {
        try (Connection conn = ConexaoFactory.criarConexao();
             PreparedStatement ps = conn.prepareStatement(SQL_EXCLUIR)) {

            ps.setString(1, login);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RepositorioException("Falha ao excluir o usuário.", e);
        }
    }
}
