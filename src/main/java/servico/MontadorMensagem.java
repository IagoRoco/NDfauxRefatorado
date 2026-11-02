package servico;

import modelo.Mensagem;

// Monta o texto final enviado ao cliente: abertura padrão + texto da mensagem escolhida.
public class MontadorMensagem {

    private static final String MODELO_ABERTURA =
            "Olá %s, tudo bem? Somos do setor de sem defeito da Reparo Express Assistência Técnica "
            + "e estamos aqui pra te passar algumas informações sobre seu aparelho %s.\n\n";

    public String montar(String nomeCliente, String modeloAparelho, Mensagem mensagem) {
        if (mensagem == null) {
            throw new IllegalArgumentException("Selecione uma mensagem.");
        }
        return String.format(MODELO_ABERTURA, limpar(nomeCliente), limpar(modeloAparelho))
                + mensagem.getTexto();
    }

    private String limpar(String texto) {
        return texto == null ? "" : texto.trim();
    }
}
