package modelo;

// Representa um usuário do sistema NDfaux.
// Supervisor gerencia usuários e mensagens; usuário comum apenas envia mensagens.
public class Usuario {

    // Limite da coluna login da tabela usuario.
    public static final int TAMANHO_MAXIMO_LOGIN = 40;

    private int idUsuario;
    private String login;
    private String senha; // ao cadastrar: senha pura; ao ler do banco: hash (MD5)
    private boolean supervisor;

    public Usuario() {
    }

    public Usuario(int idUsuario, String login, String senha, boolean supervisor) {
        this.idUsuario = idUsuario;
        setLogin(login);
        setSenha(senha);
        this.supervisor = supervisor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("O login não pode ser vazio.");
        }
        if (login.trim().length() > TAMANHO_MAXIMO_LOGIN) {
            throw new IllegalArgumentException(
                    "O login pode ter no máximo " + TAMANHO_MAXIMO_LOGIN + " caracteres.");
        }
        this.login = login.trim();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        }
        this.senha = senha;
    }

    public boolean isSupervisor() {
        return supervisor;
    }

    public void setSupervisor(boolean supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return login.equalsIgnoreCase(((Usuario) o).login);
    }

    @Override
    public int hashCode() {
        return login.toLowerCase().hashCode();
    }

    // Não inclui a senha de propósito, para não vazar em logs ou mensagens.
    @Override
    public String toString() {
        return login;
    }
}
