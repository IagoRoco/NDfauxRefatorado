package teste;

import dao.MensagemRepository;
import modelo.Defeito;
import modelo.Mensagem;
import java.util.ArrayList;
import java.util.List;

// Repositório falso, só em memória, para testar regras sem depender do MySQL.
public class MensagemRepositoryEmMemoria implements MensagemRepository {

    private final List<Mensagem> mensagens = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public List<Mensagem> listarPorDefeito(Defeito defeito) {
        List<Mensagem> resultado = new ArrayList<>();
        for (Mensagem m : mensagens) {
            if (m.getDefeito().equals(defeito)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    @Override
    public void adicionar(Mensagem mensagem) {
        mensagem.setIdMensagem(proximoId++);
        mensagens.add(mensagem);
    }

    @Override
    public void excluir(int idMensagem) {
        mensagens.removeIf(m -> m.getIdMensagem() == idMensagem);
    }
}
