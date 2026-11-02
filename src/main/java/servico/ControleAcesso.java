package servico;

import modelo.Usuario;

// Regra de acesso compartilhada: só supervisor executa operações de gerenciamento.
public final class ControleAcesso {

    private ControleAcesso() {
    }

    // Para interfaces que só precisam saber se habilitam um botão ou um menu.
    public static boolean podeGerenciar(Usuario usuario) {
        return usuario != null && usuario.isSupervisor();
    }

    // Para serviços que precisam bloquear a operação.
    public static void exigirSupervisor(Usuario solicitante) {
        if (!podeGerenciar(solicitante)) {
            throw new PermissaoNegadaException("Apenas supervisores podem executar esta operação.");
        }
    }
}
