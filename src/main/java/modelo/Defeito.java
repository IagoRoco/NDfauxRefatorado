package modelo;

// Representa um defeito que pode ser relatado em um aparelho.
public class Defeito {

    private int idDefeito;
    private String nome;

    public Defeito() {
    }

    public Defeito(int idDefeito, String nome) {
        this.idDefeito = idDefeito;
        setNome(nome);
    }

    public int getIdDefeito() {
        return idDefeito;
    }

    public void setIdDefeito(int idDefeito) {
        this.idDefeito = idDefeito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do defeito não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Defeito)) return false;
        return idDefeito == ((Defeito) o).idDefeito;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idDefeito);
    }

    @Override
    public String toString() {
        return nome;
    }
}
