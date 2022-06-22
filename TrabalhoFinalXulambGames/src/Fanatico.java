public class Fanatico extends Cliente implements IMensalidade {
    private double mensalidade = 25;
    private double desconto = 0.3;

    public Fanatico(String nome, String nomeUsuario, String senha, String email) {
        super(nome, nomeUsuario, senha, email);
    }

    public Fanatico(Cliente cliente) {
        super(cliente);
    }

    public double calcularDesconto() {
        return this.desconto;
    }

    @Override
    public void pagarMensalidade() {
        System.out.println("Mensalidade paga! Valor: " + mensalidade);
    }

    // Auxiliar nos testes
    @Override
    public String toString() {
        return super.toString() + " | Tipo Cliente: Fanático";
    }
}
