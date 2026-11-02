package dao;

// Indica falha no acesso aos dados, sem expor detalhes do banco para as outras camadas.
public class RepositorioException extends RuntimeException {

    public RepositorioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
