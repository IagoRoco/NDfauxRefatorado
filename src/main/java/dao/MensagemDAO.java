package dao;

import modelo.Defeito;
import modelo.Mensagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MensagemDAO implements MensagemRepository {

    private static final String SQL_LISTAR_POR_DEFEITO =
            "SELECT idMensagem, titulo, texto FROM mensagem WHERE idDefeito = ?";
    private static final String SQL_INSERIR =
            "INSERT INTO mensagem (titulo, texto, idDefeito) VALUES (?, ?, ?)";
    private static final String SQL_EXCLUIR =
            "DELETE FROM mensagem WHERE idMensagem = ?";

    @Override
    public List<Mensagem> listarPorDefeito(Defeito defeito) {
        List<Mensagem> mensagens = new ArrayList<>();

        try (Connection conn = ConexaoFactory.criarConexao();
             PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR_POR_DEFEITO)) {

            stmt.setInt(1, defeito.getIdDefeito());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    mensagens.add(new Mensagem(
                            rs.getInt("idMensagem"),
                            defeito,
                            rs.getString("titulo"),
                            rs.getString("texto")));
                }
            }

        } catch (SQLException e) {
            throw new RepositorioException("Falha ao listar as mensagens do defeito.", e);
        }

        return mensagens;
    }

    @Override
    public void adicionar(Mensagem mensagem) {
        try (Connection conn = ConexaoFactory.criarConexao();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, mensagem.getTitulo());
            stmt.setString(2, mensagem.getTexto());
            stmt.setInt(3, mensagem.getDefeito().getIdDefeito());
            stmt.executeUpdate();

            try (ResultSet chaves = stmt.getGeneratedKeys()) {
                if (chaves.next()) {
                    mensagem.setIdMensagem(chaves.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RepositorioException("Falha ao inserir a mensagem.", e);
        }
    }

    @Override
    public void excluir(int idMensagem) {
        try (Connection conn = ConexaoFactory.criarConexao();
             PreparedStatement stmt = conn.prepareStatement(SQL_EXCLUIR)) {

            stmt.setInt(1, idMensagem);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RepositorioException("Falha ao excluir a mensagem.", e);
        }
    }
}
