package mensageria;

import servico.Mensageiro;

// Não envia nada de verdade: guarda o último envio e imprime no console.
// Substitua por uma implementação real (ex.: WhatsApp) quando o envio for implementado.
public class MensageiroSimulado implements Mensageiro {

    private String ultimoNumero;
    private String ultimoTexto;

    @Override
    public void enviar(String numero, String texto) {
        this.ultimoNumero = numero;
        this.ultimoTexto = texto;
        System.out.println("  [SIMULADO] Para: " + numero);
        System.out.println("  " + texto.replace("\n", "\n  "));
    }

    public String getUltimoNumero() {
        return ultimoNumero;
    }

    public String getUltimoTexto() {
        return ultimoTexto;
    }
}
