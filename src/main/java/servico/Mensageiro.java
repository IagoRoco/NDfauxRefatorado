package servico;

// Contrato para qualquer forma de enviar mensagens (WhatsApp, SMS, etc.).
public interface Mensageiro {

    void enviar(String numero, String texto);
}
