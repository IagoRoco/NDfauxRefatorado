# NDfaux – projeto refatorado (regras de negócio separadas da interface)

Segunda fase do Projeto Integrador: o código do sistema desktop (Java Swing) foi
reorganizado para que as regras de negócio possam ser reaproveitadas no sistema web.
**Este projeto não contém nenhuma classe de interface (`javax.swing`).**

## Estrutura

| Pacote       | Responsabilidade |
|--------------|------------------|
| `modelo`     | Classes de dados com validação: `Defeito`, `Mensagem`, `Usuario` |
| `dao`        | Acesso ao MySQL: interfaces `*Repository`, implementações `*DAO` e `ConexaoFactory` |
| `servico`    | Regras de negócio: `Autenticador`, `UsuarioService`, `MensagemService`, `MontadorMensagem`, `EnvioService`, `ControleAcesso` |
| `mensageria` | Implementação do envio (`MensageiroSimulado`) |
| `teste`      | Repositórios em memória usados nos testes sem banco |
| `app`        | `Main` com os testes |

## Como executar

1. No MySQL, importe o script `db/nd_db.sql` (cria o banco `nd_db` com as tabelas e os dados de exemplo).
2. Copie `db.properties.exemplo` para `db.properties` (na raiz do projeto) e ajuste usuário e senha do MySQL.
3. Abra o projeto no NetBeans (`File > Open Project`, é um projeto Maven) e execute **Run Project**.
   A classe principal é `app.Main`.

Usuários do script: `adm` / `123` (supervisor) e `user` / `123` (usuário comum).

Se preferir um projeto Ant: crie um *Java Application* vazio, copie o conteúdo de
`src/main/java` para *Source Packages*, adicione o `mysql-connector-j` em *Libraries* e
deixe o `db.properties` na raiz do projeto.

## Testes

O `Main` executa 5 grupos de verificações. Os grupos 1 a 4 não precisam de banco.
O grupo 5 usa o MySQL e é pulado, com aviso, se o banco não estiver acessível.
Ele cria e apaga um usuário (`teste_refat_main`) e uma mensagem de teste.
