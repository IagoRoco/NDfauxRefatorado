package modelo;

// Representa uma mensagem padrão enviada ao cliente para um defeito específico.
public class Mensagem {

    // Limites das colunas da tabela mensagem.
    public static final int TAMANHO_MAXIMO_TITULO = 100;
    public static final int TAMANHO_MAXIMO_TEXTO = 6000;

    private int idMensagem;
    private Defeito defeito;
    private String titulo;
    private String texto;

    public Mensagem() {
    }

    public Mensagem(int idMensagem, Defeito defeito, String titulo, String texto) {
        this.idMensagem = idMensagem;
        setDefeito(defeito);
        setTitulo(titulo);
        setTexto(texto);
    }

    public int getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(int idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Defeito getDefeito() {
        return defeito;
    }

    public void setDefeito(Defeito defeito) {
        if (defeito == null) {
            throw new IllegalArgumentException("A mensagem precisa estar ligada a um defeito.");
        }
        this.defeito = defeito;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da mensagem não pode ser vazio.");
        }
        if (titulo.trim().length() > TAMANHO_MAXIMO_TITULO) {
            throw new IllegalArgumentException(
                    "O título pode ter no máximo " + TAMANHO_MAXIMO_TITULO + " caracteres.");
        }
        this.titulo = titulo.trim();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("O texto da mensagem não pode ser vazio.");
        }
        if (texto.trim().length() > TAMANHO_MAXIMO_TEXTO) {
            throw new IllegalArgumentException(
                    "O texto pode ter no máximo " + TAMANHO_MAXIMO_TEXTO + " caracteres.");
        }
        this.texto = texto.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mensagem)) return false;
        return idMensagem == ((Mensagem) o).idMensagem;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idMensagem);
    }

    @Override
    public String toString() {
        return titulo;
    }
}
