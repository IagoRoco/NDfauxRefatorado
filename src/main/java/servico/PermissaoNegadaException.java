package servico;

// Indica que o usuário não tem permissão para a operação pedida.
public class PermissaoNegadaException extends RuntimeException {

    public PermissaoNegadaException(String mensagem) {
        super(mensagem);
    }
}
