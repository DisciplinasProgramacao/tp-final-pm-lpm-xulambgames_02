import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Cliente implements Serializable {
    private String nome;
    private String nomeUsuario;
    private String senha; // Não está sendo utilizado...
    private String email; // Não está sendo utilizado...
    private ArrayList<Compra> compras; // Olhar qual o melhor tipo de dados [], List, Arraylist, Map, Set...
    static final long serialVersionUID = 123L; // Serialização

    public Cliente(String nome, String nomeUsuario, String senha, String email) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
        this.compras = new ArrayList<Compra>();
    }

    // Getters
    public String getNome() {
        return this.nome;
    }

    // Functions
    public void historicoCompras(Categoria categoria, String data) { // Filtrar por Categoria ou Data
        ArrayList<Compra> comprasFiltradas = new ArrayList<Compra>();
        System.out.println("HISTÓRICO DE COMPRAS - " + this.nomeUsuario);

        if (data == null) {
            // Verifica se a compra possui jogos com a categoria filtrada
            for (Compra compra : this.compras) { // Jeito mais fácil de fazer com stream?
                for (Jogo jogo : compra.getJogos()) {
                    if (jogo.getCategoria().equals(categoria)) {
                        comprasFiltradas.add(compra);
                        break;
                    }
                }
            }
        } else {
            try {
                // Converte a String em LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateTime = LocalDate.parse(data, formatter);

                comprasFiltradas = this.compras.stream()
                        .filter((c) -> c
                                .getDataCompra().isEqual(dateTime))
                        .collect(Collectors.toCollection(ArrayList::new));
            } catch (DateTimeParseException e) { // Exceção caso o formato não seja dd/MM/yyyy
                System.out.println("Data com formato inválido!");
            }
        }

        comprasFiltradas.forEach((c) -> {
            System.out.println("=================================");
            System.out.println(c.toString());
            System.out.println("=================================");
        });
    }

    public void adicionarCompra(Compra compra) {
        this.compras.add(compra);
    }

    public void removerCompra(Compra compra) {
        // Não está sendo utilizado...
        this.compras.remove(compra);
    }

    public void enviarEmail() {
        // Não está sendo utilizado...
    }

    // Auxiliar nos testes
    @Override
    public String toString() {
        return "Nome: " + nome + " | NomeUsuario: " + nomeUsuario + " | Senha: " + senha + " | Email: " + email;
    }

    public abstract double calcularDesconto();
}
