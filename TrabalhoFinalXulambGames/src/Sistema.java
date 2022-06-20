import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sistema {
    private String nome; // Não está sendo utilizado...
    private String endereco; // Não está sendo utilizado...
    private ArrayList<Object> totalVendas;
    private ArrayList<Object> totalClientes;
    private ArrayList<Object> totalJogos;

    public Sistema(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.totalVendas = new ArrayList<Object>();
        this.totalClientes = new ArrayList<Object>();
        this.totalJogos = new ArrayList<Object>();
    }

    // Functions
    public void valorMensalVendido() { // Pega o mês atual ou Informa o mês?(PrecoVenda ou PrecoPago?)
        LocalDate atual = LocalDate.now();
        Double valorTotal = this.totalVendas.stream().filter((v) -> ((Compra) v)
                .getDataCompra()
                .getMonth() == atual.getMonth())
                .mapToDouble((v) -> ((Compra) v)
                        .getPrecoVenda())
                .sum();

        System.out.println("\033[1;34mValor mensal vendido");
        System.out.println("======================");
        System.out.println("R$" + valorTotal);
    }

    public void valorMedioCompras() { // (PrecoVenda ou PrecoPago?)
        double valorTotal = this.totalVendas.stream().mapToDouble((v) -> ((Compra) v)
                .getPrecoVenda())
                .average()
                .getAsDouble();

        System.out.println("\033[1;34mValor Médio Compras");
        System.out.println("======================");
        System.out.println("R$" + valorTotal);
    }

    public void jogosExtremos() {
        int maisVendido = 0;
        Jogo jogoMaisVendido = null;

        int menosVendido = Integer.MAX_VALUE;
        Jogo jogoMenosVendido = null;

        // Mais vendido e Menos Vendido
        for (Object jogo : totalJogos) { // Outro jeito melhor de fazer isso?(Stream, comparator...)
            if (((Jogo) jogo).getQuantidadeVendaTotal() > maisVendido) {
                jogoMaisVendido = (Jogo) jogo;
                maisVendido = ((Jogo) jogo).getQuantidadeVendaTotal();
            }

            if (((Jogo) jogo).getQuantidadeVendaTotal() < menosVendido) {
                jogoMenosVendido = (Jogo) jogo;
                menosVendido = ((Jogo) jogo).getQuantidadeVendaTotal();
            }
        }

        System.out.println("\033[1;34mJogos Extremos");
        System.out.println("======================");
        System.out.println("Mais Vendido: " + jogoMaisVendido.getNome());
        System.out.println("Menos Vendido: " + jogoMenosVendido.getNome());
    }

    /**
     * Salva os objetos em formato serializado (Object)
     * 
     * @param frota Lista com os objetos
     * @param arq   Nome do arquivo a ser gerado
     */
    public void salvarBinario(ArrayList<Object> lista, String arq) {
        ObjectOutputStream saida = null;
        try {
            saida = new ObjectOutputStream(new FileOutputStream(arq));
            saida.writeObject(lista);
            saida.close();
        } catch (FileNotFoundException fe) {
            System.out.println(
                    "\033[1;31mArquivo não encontrado, ou permissão negada. Tente novamente com outro arquivo");
        } catch (IOException ex) {
            System.out.println("\033[1;31mProblemas na operação de E/S. Contacte o suporte");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Carrega os objetos de um arquivo serializado (Object)
     * 
     * @param arq Nome do arquivo de dados
     * @return Uma lista genérica com a classe do arquivo passado como parâmetro.
     *         A lista pode estar vazia ou nula em caso de exceções.
     */
    public ArrayList<Object> carregarBinario(String arq) {
        ArrayList<Object> lista = null;

        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(arq));
            lista = (ArrayList<Object>) entrada.readObject();
            entrada.close();
        } catch (FileNotFoundException fe) {
            lista = new ArrayList<Object>();
        } catch (ClassNotFoundException ce) {
            System.out.println("\033[1;31mProblema na conversão dos dados: classe inválida. Contacte o suporte.");
        } catch (IOException ie) {
            System.out.println("\033[1;31mProblemas na operação de E/S. Contacte o suporte");
            System.out.println(ie.getMessage());
        }

        return lista;
    }

    // Getters
    public ArrayList<Object> getTotalVendas() {
        return this.totalVendas;
    }

    public ArrayList<Object> getTotalClientes() {
        return this.totalClientes;
    }

    public ArrayList<Object> getTotalJogos() {
        return this.totalJogos;
    }

    // Setters
    public void setTotalVendas(ArrayList<Object> venda) {
        this.totalVendas = venda;
    }

    public void setTotalClientes(ArrayList<Object> cliente) {
        this.totalClientes = cliente;
    }

    public void setTotalJogos(ArrayList<Object> jogo) {
        this.totalJogos = jogo;
    }

    // Add
    public void addTotalVendas(Compra venda) {
        this.totalVendas.add(venda);
    }

    public void addTotalClientes(Cliente cliente) {
        this.totalClientes.add(cliente);
    }

    public void addTotalJogos(Jogo jogo) {
        this.totalJogos.add(jogo);
    }

    // Remove
    public void removeTotalJogos(String nomeJogo) {
        Jogo jogoRemovido = null;

        for (Object jogo : totalJogos) { // Outro jeito melhor de fazer isso?
            if (((Jogo) jogo).getNome().equals(nomeJogo)) {
                jogoRemovido = (Jogo) jogo;
            }
        }

        this.totalJogos.remove(jogoRemovido); // Remove o objeto
    }

    @Override
    public String toString() {
        return "Empresa: " + nome + " | Endereço: " + endereco;
    }
}
