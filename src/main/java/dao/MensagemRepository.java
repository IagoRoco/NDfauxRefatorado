package dao;

import modelo.Defeito;
import modelo.Mensagem;
import java.util.List;

public interface MensagemRepository {

    List<Mensagem> listarPorDefeito(Defeito defeito);

    void adicionar(Mensagem mensagem);

    void excluir(int idMensagem);
}
