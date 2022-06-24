import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    // Nomes dos arquivos binários
    static String arqCompra = "compra.bin";
    static String arqCliente = "cliente.bin";
    static String arqJogo = "jogo.bin";

    // #region utilidades

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Menu
     * 
     * @param teclado Scanner de leitura
     * @return Opção do usuário (int)
     */

    public static int menu(Scanner teclado) {
        limparTela();
        System.out.println("\033[1;32mXULAMB GAMES");
        System.out.println("=================================================");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Jogo");
        System.out.println("3 - Cadastrar Venda");
        System.out.println("4 - Valor Mensal Vendido(Mês atual)");
        System.out.println("5 - Valor Médio das Compras(Total)");
        System.out.println("6 - Jogos E-xtremos(Mais vendido e Menos Vendido)");
        System.out.println("7 - Histórico Cliente");
        System.out.println("8 - Alterar categoria e preço atual do Jogo");
        System.out.println("9 - Alterar tipo do Cliente");
        System.out.println("0 - Sair");
        int opcao = 0;
        try {
            opcao = teclado.nextInt();
            teclado.nextLine();
        } catch (InputMismatchException ex) {
            teclado.nextLine();
            System.out.println("\033[1;31mSomente opções numéricas.");
            opcao = -1;
        }
        return opcao;
    }

    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    static void pausa(Scanner teclado) {
        System.out.println("\033[1;32mEnter para continuar.");
        teclado.nextLine();
    }

    /**
     * Método que realiza o cadastro manual de clientes
     * 
     * @param teclado     Scanner de leitura
     * @param xulambgames Objeto do sistema global
     */
    public static void cadastrarCliente(Scanner teclado, Sistema xulambgames) {
        System.out.println("Digite o nome do usuario: ");
        String nomeUsuario = teclado.nextLine();

        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();

        System.out.println("Digite o email do cliente: ");
        String emailCliente = teclado.nextLine();

        System.out.println("Digite a senha do cliente: ");
        String senha = teclado.nextLine();

        // Cria o objeto com base no tipo de cliente
        System.out.println("Selecione o tipo do cliente:");
        System.out.println("=================================================");
        System.out.println("1 - Empolgado");
        System.out.println("2 - Fanático");
        System.out.println("3 - Cadastrado");
        try {
            int tipoCliente = teclado.nextInt();
            teclado.nextLine();
            switch (tipoCliente) {
                case 1:
                    Empolgado clienteEmpolgado = new Empolgado(nomeCliente, nomeUsuario, senha,
                            emailCliente);
                    xulambgames.addTotalClientes(clienteEmpolgado);
                    break;

                case 2:
                    Fanatico clienteFanatico = new Fanatico(nomeCliente, nomeUsuario, senha, emailCliente);
                    xulambgames.addTotalClientes(clienteFanatico);
                    break;

                case 3:
                    Cadastrado clienteCadastrado = new Cadastrado(nomeCliente, nomeUsuario, senha,
                            emailCliente);
                    xulambgames.addTotalClientes(clienteCadastrado);
                    break;

                default:
                    System.out.println("\033[1;31mOpção inválida!");
                    break;
            }
        } catch (InputMismatchException ex) {
            teclado.nextLine();
            System.out.println("\033[1;31mSomente opções numéricas.");
        }
    }

    /**
     * Método que realiza o cadastro manual de jogos
     * 
     * @param teclado     Scanner de leitura
     * @param xulambgames Objeto do sistema global
     */
    public static void cadastrarJogo(Scanner teclado, Sistema xulambgames) {
        System.out.println("Digite o nome do jogo: ");
        String nomeJogo = teclado.nextLine();

        System.out.println("Digite a descrição do jogo: ");
        String descricaoJogo = teclado.nextLine();

        try {
            System.out.println("Digite o preço do jogo: ");
            Double precoJogo = teclado.nextDouble();

            // Retorna o ENUM com oque foi selecionado
            System.out.println("Selecione a categoria do jogo:");
            System.out.println("=================================================");
            System.out.println("1 - Lancamentos");
            System.out.println("2 - Premium");
            System.out.println("3 - Regulares");
            System.out.println("4 - Promoções");
            int tipoCliente = teclado.nextInt();
            teclado.nextLine();

            Categoria categoria;
            switch (tipoCliente) {
                case 1:
                    categoria = Categoria.Lancamentos;
                    break;

                case 2:
                    categoria = Categoria.Premium;
                    break;

                case 3:
                    categoria = Categoria.Regulares;
                    break;

                case 4:
                    categoria = Categoria.Promocoes;
                    break;

                default:
                    categoria = null;
                    System.out.println("\033[1;31mOpção inválida!");
                    break;
            }

            if (categoria != null) {
                Jogo jogo = new Jogo(nomeJogo, descricaoJogo, precoJogo, categoria);
                xulambgames.addTotalJogos(jogo);
            }
        } catch (InputMismatchException ex) {
            teclado.nextLine();
            System.out.println("\033[1;31mSomente opções numéricas.");
        }
    }

    /**
     * Método que realiza o cadastro manual de compras/vendas
     * 
     * @param teclado     Scanner de leitura
     * @param xulambgames Objeto do sistema global
     */
    public static void cadastrarVenda(Scanner teclado, Sistema xulambgames) {
        Compra compra = new Compra(LocalDate.now());
        ArrayList<Object> jogos = xulambgames.getTotalJogos();
        ArrayList<Object> clientes = xulambgames.getTotalClientes();

        System.out.println("Digite o nome do cliente: (Opções abaixo)");
        clientes.forEach((c) -> System.out.println(c.toString()));

        String nomeCliente = teclado.nextLine();
        // Filtra o Arraylist de Clientes pelo nome e retorna o primeiro elemento
        Cliente cliente = (Cliente) clientes.stream()
                .filter((c) -> ((Cliente) c)
                        .getNome()
                        .equals(nomeCliente))
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            limparTela();

            int contadorJogos = 0;
            while (true) {
                System.out
                        .println("\033[1;32mDigite o nome do jogo: (Opções abaixo) | Para finalizar a compra digite 0");
                jogos.forEach((j) -> System.out.println(j.toString()));

                String nomeJogo = teclado.nextLine();
                // Verifica se tem algum jogo adicionado e se foi digitado 0
                if (contadorJogos > 0 && nomeJogo.equals("0")) {
                    limparTela();
                    break;
                } else {
                    // Filtra o Arraylist de Jogos pelo nome e retorna o primeiro elemento
                    Jogo jogo = (Jogo) jogos.stream()
                            .filter((j) -> ((Jogo) j)
                                    .getNome()
                                    .equals(nomeJogo))
                            .findFirst()
                            .orElse(null);

                    if (jogo != null) {
                        compra.adicionarJogo(jogo);

                        jogo.setQuantidadeVendaTotal(); // Aumenta número de vendas, para saber mais e menos vendidos

                        limparTela();
                        contadorJogos++;
                        System.out.println("\033[1;34mJogo adicionado com sucesso!");
                    } else {
                        System.out.println("\033[1;31mJogo não encontrado!");
                    }
                }
            }
            compra.calcularPrecoPago(cliente); // Calcula o preço pago pelo cliente com os descontos
            cliente.adicionarCompra(compra); // Adiciona compra ao cliente
            xulambgames.addTotalVendas(compra);
        } else
            System.out.println("\033[1;31mCliente não encontrado!");
    }

    /**
     * Método que retorna o histórico do cliente informado
     * 
     * @param teclado     Scanner de leitura
     * @param xulambgames Objeto do sistema global
     */
    public static void historicoCliente(Scanner teclado, Sistema xulambgames) {
        ArrayList<Object> clientes = xulambgames.getTotalClientes();

        System.out.println("Digite o nome do cliente: (Opções abaixo)");
        clientes.forEach((c) -> System.out.println(c.toString()));

        String nomeCliente = teclado.nextLine();
        // Filtra o Arraylist de Clientes pelo nome e retorna o primeiro elemento
        Cliente cliente = (Cliente) clientes.stream()
                .filter((c) -> ((Cliente) c)
                        .getNome()
                        .equals(nomeCliente))
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            // Seleciona os filtros disponíveis
            System.out.println("Seleciona um tipo de filtro: ");
            System.out.println("=================================================");
            System.out.println("1 - Categoria");
            System.out.println("2 - Data da Compra");
            try {
                int tipoFiltro = teclado.nextInt();
                teclado.nextLine();
                switch (tipoFiltro) {
                    case 1:
                        // Retorna o ENUM com oque foi selecionado
                        System.out.println("Selecione a categoria do jogo:");
                        System.out.println("=================================================");
                        System.out.println("1 - Lancamentos");
                        System.out.println("2 - Premium");
                        System.out.println("3 - Regulares");
                        System.out.println("4 - Promoções");
                        int tipoCliente = teclado.nextInt();
                        teclado.nextLine();

                        Categoria categoria;
                        switch (tipoCliente) {
                            case 1:
                                categoria = Categoria.Lancamentos;
                                break;

                            case 2:
                                categoria = Categoria.Premium;
                                break;

                            case 3:
                                categoria = Categoria.Regulares;
                                break;

                            case 4:
                                categoria = Categoria.Promocoes;
                                break;

                            default:
                                categoria = null;
                                System.out.println("Opção inválida!");
                                break;
                        }
                        cliente.historicoCompras(categoria, null); // Validar opção inválida
                        break;

                    case 2:
                        System.out.println("Digite a data: (dd/MM/yyyy)");
                        String data = teclado.nextLine();
                        cliente.historicoCompras(null, data);
                        break;

                    default:
                        System.out.println("\033[1;31mOpção inválida!");
                        break;
                }
            } catch (InputMismatchException ex) {
                teclado.nextLine();
                System.out.println("\033[1;31mSomente opções numéricas.");
            }
        } else
            System.out.println("\033[1;31mCliente não encontrado!");
    }

    /**
     * Método que altera a categoria e o preço atual do jogo
     * 
     * @param teclado     Scanner de leitura
     * @param xulambgames Objeto do sistema global
     */
    public static void alterarJogo(Scanner teclado, Sistema xulambgames) {
        ArrayList<Object> jogos = xulambgames.getTotalJogos();

        System.out.println("Digite o nome do jogo: (Opções abaixo)");
        jogos.forEach((j) -> System.out.println(j.toString()));

        String nomeJogo = teclado.nextLine();
        // Filtra o Arraylist de Clientes pelo nome e retorna o primeiro elemento
        Jogo jogo = (Jogo) jogos.stream()
                .filter((j) -> ((Jogo) j)
                        .getNome()
                        .equals(nomeJogo))
                .findFirst()
                .orElse(null);

        if (jogo != null) {
            limparTela();

            try {
                // Retorna o ENUM com oque foi selecionado
                System.out.println("Selecione a nova categoria do jogo:");
                System.out.println("=================================================");
                System.out.println("1 - Lancamentos");
                System.out.println("2 - Premium");
                System.out.println("3 - Regulares");
                System.out.println("4 - Promoções");
                int tipoJogo = teclado.nextInt();
                teclado.nextLine();

                Categoria categoria;
                switch (tipoJogo) {
                    case 1:
                        categoria = Categoria.Lancamentos;
                        break;

                    case 2:
                        categoria = Categoria.Premium;
                        break;

                    case 3:
                        categoria = Categoria.Regulares;
                        break;

                    case 4:
                        categoria = Categoria.Promocoes;
                        break;

                    default:
                        categoria = null;
                        System.out.println("Opção inválida!");
                        break;
                }

                System.out.println("Digite o novo valor do jogo: ");
                double precoJogo = teclado.nextDouble();
                teclado.nextLine();

                jogo.alterarCategoria(categoria, precoJogo); // Validar opção inválida
            } catch (InputMismatchException ex) {
                teclado.nextLine();
                System.out.println("\033[1;31mSomente opções numéricas.");
            }
        } else
            System.out.println("\033[1;31mCliente não encontrado!");
    }

    /**
     * Método que altera o tipo do cliente
     * 
     * @param teclado     Scanner de leitura
     * @param xulambgames Objeto do sistema global
     */
    public static void alterarCliente(Scanner teclado, Sistema xulambgames) {
        ArrayList<Object> clientes = xulambgames.getTotalClientes();

        System.out.println("Digite o nome do cliente: (Opções abaixo)");
        clientes.forEach((c) -> System.out.println(c.toString()));

        String nomeCliente = teclado.nextLine();
        // Filtra o Arraylist de Clientes pelo nome e retorna o primeiro elemento
        Cliente cliente = (Cliente) clientes.stream()
                .filter((c) -> ((Cliente) c)
                        .getNome()
                        .equals(nomeCliente))
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            limparTela();

            // Altera o objeto com base no tipo de cliente
            System.out.println("Selecione o tipo do cliente:");
            System.out.println("=================================================");
            System.out.println("1 - Empolgado");
            System.out.println("2 - Fanático");
            System.out.println("3 - Cadastrado");
            try {
                int tipoCliente = teclado.nextInt();
                teclado.nextLine();
                switch (tipoCliente) {
                    case 1:
                        // (Reflete apenas se mudar atributos, o objeto inteiro não)
                        cliente = new Empolgado(cliente); // Não está refletindo...Quando muda o obojeto
                        xulambgames.removeTotalClientes(cliente.getNome()); // Tive que remover e adicionar do array
                        xulambgames.addTotalClientes(cliente); // Tive que remover e adicionar do array
                        break;

                    case 2:
                        cliente = new Fanatico(cliente);
                        xulambgames.removeTotalClientes(cliente.getNome());
                        xulambgames.addTotalClientes(cliente);
                        break;

                    case 3:
                        cliente = new Cadastrado(cliente);
                        xulambgames.removeTotalClientes(cliente.getNome());
                        xulambgames.addTotalClientes(cliente);
                        break;

                    default:
                        System.out.println("\033[1;31mOpção inválida!");
                        break;
                }
            } catch (InputMismatchException ex) {
                teclado.nextLine();
                System.out.println("\033[1;31mSomente opções numéricas.");
            }
        } else
            System.out.println("\033[1;31mCliente não encontrado!");
    }

    // #endregion

    public static void main(String[] args) throws Exception {
        /**
         * Criação da classe Empresa e alimentação das classes/carregamento dos
         * arquivos binários caso existam. (Maneiras melhores de fazer isso?)
         */
        Sistema xulambgames = new Sistema("Xulamb Games", "Rua Xulamb");
        xulambgames.setTotalVendas(xulambgames.carregarBinario(arqCompra));
        xulambgames.setTotalClientes(xulambgames.carregarBinario(arqCliente));
        xulambgames.setTotalJogos(xulambgames.carregarBinario(arqJogo));

        /**
         * Menu
         */
        Scanner teclado = new Scanner(System.in);
        int opcao;

        while (true) {
            opcao = menu(teclado);
            if (opcao == 0) {
                limparTela();
                break;
            }

            switch (opcao) {
                case 1:
                    cadastrarCliente(teclado, xulambgames);
                    break;

                case 2:
                    cadastrarJogo(teclado, xulambgames);
                    break;

                case 3:
                    cadastrarVenda(teclado, xulambgames);
                    break;

                case 4:
                    xulambgames.valorMensalVendido();
                    break;
                case 5:
                    xulambgames.valorMedioCompras();
                    break;
                case 6:
                    xulambgames.jogosExtremos();
                    break;
                case 7:
                    historicoCliente(teclado, xulambgames);
                    break;
                case 8:
                    alterarJogo(teclado, xulambgames);
                    break;
                case 9:
                    alterarCliente(teclado, xulambgames);
                    break;

                default:
                    break;
            }

            pausa(teclado);
            limparTela();
        }

        /**
         * Após o fim da aplicação, salvar os dados de compras, clientes e jogos
         */
        xulambgames.salvarBinario(xulambgames.getTotalVendas(), arqCompra);
        xulambgames.salvarBinario(xulambgames.getTotalClientes(), arqCliente);
        xulambgames.salvarBinario(xulambgames.getTotalJogos(), arqJogo);
    }
}