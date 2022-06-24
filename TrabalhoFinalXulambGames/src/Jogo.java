import java.io.Serializable;

public class Jogo implements Serializable {
    private String nome;
    private String descricao;
    private double preco; // precoOriginal (Só pra validação)
    private double precoAtual; // precoAtual (Calculos)
    private Categoria categoria;
    private int quantidadeVendaTotal = 0;
    static final long serialVersionUID = 413L; // Serialização

    public Jogo(String nome, String descricao, Double preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.precoAtual = preco;
        this.categoria = categoria;
    }

    /**
     * Verifica se o novo valor do jogo é válido para a categoria atual
     * 
     * @param novoPreco
     * @return Retorna verdadeiro ou falso para a condição da categoria
     */
    public boolean descontoValido(double novoPreco) {
        double pctDesconto = this.calculoPctDesconto(novoPreco); // Calcula a porcentagem que o novo preço representa do
                                                                 // preço original

        // Verifica se o novo valor é válido para a categoria
        if (this.categoria.descontoValido(pctDesconto)) {
            return true;
        } else {
            System.out.println(
                    "O valor apresentado não está de acordo com a regra da categoria. Valor atual do jogo não foi alterado!");
            return false;
        }
    }

    /**
     * Verifica se o novo valor do jogo é válido para a categoria atual é válido
     * para a nova categoria cadastrada
     * 
     * @param novoPreco
     * @param novaCategoria
     * @return Retorna verdadeiro ou falso para a condição da categoria
     */
    public boolean descontoValido(double novoPreco, Categoria novaCategoria) {
        double pctDesconto = this.calculoPctDesconto(novoPreco); // Calcula a porcentagem que o novo preço representa do
                                                                 // preço original

        // Verifica se o novo valor é válido para a categoria
        if (novaCategoria.descontoValido(pctDesconto)) {
            return true;
        } else {
            System.out.println(
                    "O valor apresentado não está de acordo com a regra da categoria. Valor do jogo e da categoria não foram alterados!");
            return false;
        }

    }

    /**
     * Altera o preço atual do jogo e verifica se ele é válido com base na
     * categoria. Caso não seja válido ele não muda o preço atual.
     * 
     * @param categoriaNova
     */
    public void alterarPrecoAtual(double novoPreco) {
        if (this.descontoValido(novoPreco)) {
            this.precoAtual = novoPreco;
            System.out.println("Valor alterado com sucesso!");
        }
    }

    /**
     * Altera a categoria do jogo e o novo preço do jogo. Verifica se o novo valor é
     * valido com base na nova categoria. Caso não seja válido ele não muda a
     * categoria e nem o preço atual.
     * 
     * @param categoriaNova
     */
    public void alterarCategoria(Categoria novaCategoria, double novoPreco) {
        if (this.descontoValido(novoPreco, novaCategoria)) {
            this.categoria = novaCategoria;
            this.alterarPrecoAtual(novoPreco);
            System.out.println("Valores alterados com sucesso!");
        }
    }

    private double calculoPctDesconto(double novoPreco) {
        return 1 + (((novoPreco - this.preco) / this.preco));
    }

    // Getters
    public String getNome() {
        return this.nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public double getPrecoAtual() {
        return this.precoAtual;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public int getQuantidadeVendaTotal() {
        return this.quantidadeVendaTotal;
    }

    public void setQuantidadeVendaTotal() {
        this.quantidadeVendaTotal++;
    }

    // Auxiliar nos testes
    @Override
    public String toString() {
        return "Nome: " + nome + " | Descrição: " + descricao + " | Preço Original: " + preco + " | Preço Atual: "
                + precoAtual + " | Quantidade Vendas: "
                + quantidadeVendaTotal + " | Categoria: "
                + categoria.name(); // Debug + " | Quantidade Vendida: " + quantidadeVendaTotal
    }
}
