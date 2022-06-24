import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Cliente implements Serializable {
    private String nome;
    public String nomeUsuario;
    private String senha;
    private String email;
    private ArrayList<Compra> compras;
    static final long serialVersionUID = 123L; // Serialização

    public Cliente(String nome, String nomeUsuario, String senha, String email) {
        this.nome = nome;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
        this.compras = new ArrayList<Compra>();
    }

    // Construtor de Cliente que recebe outro cliente
    public Cliente(Cliente cliente) { // Classe filha chama esse construtor
        // Fazer os atributos básicos serem os atributos serem os atributos de parâmetro
        this.nome = cliente.nome;
        this.nomeUsuario = cliente.nomeUsuario;
        this.senha = cliente.senha;
        this.email = cliente.email;
        this.compras = cliente.compras;

        // Ex: Passar fanatico para empolgado - da um new Fanatico(empolgado) - Passa
        // empolgado como base (Fanatico vai copiar todos os parâmetros do cliente)
    }

    // Getters
    public String getNome() {
        return this.nome;
    }

    // Functions

    public void historicoCompras(Categoria categoria, String data) {
        ArrayList<Compra> comprasFiltradas = new ArrayList<Compra>();
        System.out.println("HISTÓRICO DE COMPRAS - " + this.nomeUsuario);

        if (data == null) {
            // Verifica se a compra possui jogos com a categoria filtrada
            for (Compra compra : this.compras) {
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
