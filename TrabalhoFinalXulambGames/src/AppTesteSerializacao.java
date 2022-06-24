import java.time.LocalDate;

public class AppTesteSerializacao {
    // Nomes dos arquivos binários
    static String arqCompra = "compra.bin";
    static String arqCliente = "cliente.bin";
    static String arqJogo = "jogo.bin";

    // Criar métodos para adicionar produtos na main...

    public static void main(String[] args) throws Exception {
        /**
         * Criação da classe Empresa e alimentação das classes/carregamento dos
         * arquivos binários caso existam. (Maneiras melhores de fazer isso?)
         */
        Sistema xulambgames = new Sistema("Xulamb Games", "Rua Xulamb");
        xulambgames.setTotalVendas(xulambgames.carregarBinario(arqCompra));
        xulambgames.setTotalClientes(xulambgames.carregarBinario(arqCliente));
        xulambgames.setTotalJogos(xulambgames.carregarBinario(arqJogo));

        // #region Populando/Testando Dados
        // Clientes
        Empolgado cliente1 = new Empolgado("Joao", "joao2", "123", "joao@gmail.com");
        Fanatico cliente2 = new Fanatico("Bernardo", "bernardo1", "123", "bernardo@gmail.com");
        Cadastrado cliente3 = new Cadastrado("Ana", "Ana3", "123", "ana@gmail.com");

        // Jogos
        Jogo jogo1 = new Jogo("Jogo1", "Jogo1 é brabo", 100.00, Categoria.Regulares);
        Jogo jogo2 = new Jogo("Jogo2", "Jogo2 é brabo", 200.00, Categoria.Lancamentos);
        Jogo jogo3 = new Jogo("Jogo3", "Jogo3 é brabo", 150.00, Categoria.Premium);
        Jogo jogo4 = new Jogo("Jogo4", "Jogo4 é brabo", 50.00, Categoria.Promocoes);

        // Compras/Vendas
        Compra compra1 = new Compra(LocalDate.now());
        Compra compra2 = new Compra(LocalDate.now());

        compra1.adicionarJogo(jogo1);
        compra1.adicionarJogo(jogo2);

        compra2.adicionarJogo(jogo3);

        cliente1.adicionarCompra(compra1);
        cliente1.adicionarCompra(compra2);

        // Teste - Visualizar dados
        xulambgames.addTotalClientes(cliente1);
        xulambgames.addTotalClientes(cliente2);
        xulambgames.addTotalClientes(cliente3);

        xulambgames.addTotalJogos(jogo1);
        xulambgames.addTotalJogos(jogo2);
        xulambgames.addTotalJogos(jogo3);
        xulambgames.addTotalJogos(jogo4);

        xulambgames.addTotalVendas(compra1);
        xulambgames.addTotalVendas(compra2);

        xulambgames.getTotalClientes().forEach((n) -> System.out.println(n.toString()));
        xulambgames.getTotalJogos().forEach((n) -> System.out.println(n.toString()));
        xulambgames.getTotalVendas().forEach((n) -> System.out.println(n.toString()));
        // #endregion

        // Pedir nomeUsuario e senha para quando for cadastrar venda/consultar histórico

        /**
         * Após o fim da aplicação, salvar os dados de compras, clientes e jogos
         * (Maneiras melhores de fazer isso?)
         */
        xulambgames.salvarBinario(xulambgames.getTotalVendas(), arqCompra); // Passar apenas o nome do arquivo como
                                                                            // parâmetro?
        xulambgames.salvarBinario(xulambgames.getTotalClientes(), arqCliente);
        xulambgames.salvarBinario(xulambgames.getTotalJogos(), arqJogo);
    }
}
