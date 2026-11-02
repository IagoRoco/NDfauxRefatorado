package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Responsável apenas por criar conexões com o banco MySQL.
// Os dados de acesso ficam em db.properties (fora do Git), nunca no código.
// Quem recebe a conexão deve fechá-la (use try-with-resources).
public final class ConexaoFactory {

    private static final String ARQUIVO_CONFIGURACAO = "db.properties";

    private ConexaoFactory() {
        // impede que a classe seja instanciada
    }

    public static Connection criarConexao() throws SQLException {
        Properties config = carregarConfiguracao();
        return DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("usuario"),
                config.getProperty("senha"));
    }

    private static Properties carregarConfiguracao() {
        Properties config = new Properties();
        try (InputStream entrada = new FileInputStream(ARQUIVO_CONFIGURACAO)) {
            config.load(entrada);
        } catch (IOException e) {
            throw new RepositorioException("Não foi possível ler o arquivo " + ARQUIVO_CONFIGURACAO
                    + ". Copie db.properties.exemplo para db.properties e ajuste os dados.", e);
        }
        return config;
    }
}
