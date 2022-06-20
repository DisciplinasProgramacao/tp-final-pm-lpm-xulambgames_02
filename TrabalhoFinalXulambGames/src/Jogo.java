import java.io.Serializable;

public class Jogo implements Serializable {
    private String nome;
    private String descricao;
    private Double preco;
    private Categoria categoria;
    private int quantidadeVendaTotal = 0;
    static final long serialVersionUID = 413L; // Serialização

    public Jogo(String nome, String descricao, Double preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco; // Vai ter que validar isso com base na categoria
        this.categoria = categoria;
    }

    /***
     * Retorna o valor real do jogo
     * 
     * @return Valor do jogo com alteração da categoria
     */
    public double calcularPrecoVenda() {
        return this.preco * this.getCategoria().getValorCategoria();
    }

    // Getters
    public String getNome() {
        return this.nome;
    }

    public Double getPreco() {
        return this.preco;
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
        return "Nome: " + nome + " | Descrição: " + descricao + " | Preço: " + preco + " | Categoria: "
                + categoria.name(); // Debug + " | Quantidade Vendida: " + quantidadeVendaTotal
    }
}
