package dao;

import modelo.Defeito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefeitoDAO implements DefeitoRepository {

    private static final String SQL_LISTAR = "SELECT idDefeito, nome FROM defeito";

    @Override
    public List<Defeito> listarTodos() {
        List<Defeito> defeitos = new ArrayList<>();

        try (Connection conn = ConexaoFactory.criarConexao();
             PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                defeitos.add(new Defeito(rs.getInt("idDefeito"), rs.getString("nome")));
            }

        } catch (SQLException e) {
            throw new RepositorioException("Falha ao listar os defeitos.", e);
        }

        return defeitos;
    }
}
