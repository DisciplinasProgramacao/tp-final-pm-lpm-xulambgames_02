import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Compra implements Serializable {
    private ArrayList<Jogo> jogos; // Olhar qual o melhor tipo de dados [], List, Arraylist, Map, Set...
    private double precoVenda = 0;
    private double precoPago = 0;
    private LocalDate dataCompra;
    static final long serialVersionUID = 321L; // Serialização

    public Compra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
        this.jogos = new ArrayList<Jogo>();
    }

    // Functions
    private double calcularDescontoJogos() {
        int totalCalculoBase = this.jogos.stream().mapToInt((j) -> j.getCategoria().getValorCalculoBase()).sum();
        System.out.println(totalCalculoBase); // Debug

        if (totalCalculoBase > 4) {
            return 0.2;
        } else if (totalCalculoBase == 4)
            return 0.1;
        else {
            return 0;
        }
    }

    public void calcularPrecoPago(Cliente cliente) {
        /**
         * Calcula o novo precoVenda com os descontos(descontoJogosCategoria +
         * desconto do tipo de cliente)
         */
        this.precoPago = this.precoVenda
                - this.precoVenda * (cliente.calcularDesconto() + this.calcularDescontoJogos());
    }

    public void adicionarJogo(Jogo jogo) {
        this.jogos.add(jogo);
        /**
         * Soma o valor do jogo na precoVenda. Já inclui o novo preco do jogo com o
         * valor da categoria.
         */
        this.precoVenda += jogo.calcularPrecoVenda();
    }

    public void removerJogo(Jogo jogo) {
        // Não está sendo utilizado...
        this.jogos.remove(jogo); // Remove todas as ocorrencias?
    }

    // Auxiliar nos testes
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Utilização de StringBuilder para facilitar a montagem da string
        StringBuilder retorno = new StringBuilder();
        retorno.append("\nPreço de Venda: " + precoVenda + " | Preço Pago: " + precoPago + " | Data da Compra: "
                + dataCompra.format(formatter) + "\n");

        // Dados dos jogos que estão na compra
        this.jogos.forEach((j) -> {
            retorno.append(" - " + j.toString() + "\n");
        });

        return retorno.toString();
    }

    // Getters
    public ArrayList<Jogo> getJogos() {
        return this.jogos;
    }

    public LocalDate getDataCompra() {
        return this.dataCompra;
    }

    public double getPrecoVenda() {
        return this.precoVenda;
    }

    public double getPrecoPago() {
        return this.precoPago;
    }
}
