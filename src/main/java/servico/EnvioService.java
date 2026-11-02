package servico;

import modelo.Mensagem;

// Regras do envio: valida o número de contato e entrega o texto montado ao Mensageiro.
public class EnvioService {

    private static final int TAMANHO_NUMERO = 11; // DDD (2) + 9 dígitos

    private final MontadorMensagem montador;
    private final Mensageiro mensageiro;

    public EnvioService(MontadorMensagem montador, Mensageiro mensageiro) {
        this.montador = montador;
        this.mensageiro = mensageiro;
    }

    public void enviar(String nomeCliente, String modeloAparelho, String numero, Mensagem mensagem) {
        String digitos = somenteDigitos(numero);
        if (digitos.length() != TAMANHO_NUMERO) {
            throw new IllegalArgumentException("Número de contato inválido. Informe o DDD e os 9 dígitos.");
        }
        mensageiro.enviar(digitos, montador.montar(nomeCliente, modeloAparelho, mensagem));
    }

    private String somenteDigitos(String numero) {
        return numero == null ? "" : numero.replaceAll("\\D", "");
    }
}
