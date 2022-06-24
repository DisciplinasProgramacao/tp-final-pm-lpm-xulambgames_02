public class Cadastrado extends Cliente {
    private double desconto = 0;

    public Cadastrado(String nome, String nomeUsuario, String senha, String email) {
        super(nome, nomeUsuario, senha, email);
    }

    public Cadastrado(Cliente cliente) {
        super(cliente);
    }

    public double calcularDesconto() {
        return this.desconto;
    }

    // Auxiliar nos testes
    @Override
    public String toString() {
        return super.toString() + " | Tipo Cliente: Cadastrado";
    }
}
