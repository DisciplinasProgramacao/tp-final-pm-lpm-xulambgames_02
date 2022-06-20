public class Empolgado extends Cliente implements IMensalidade {
    private double mensalidade = 10;
    private double desconto = 0.1;

    public Empolgado(String nome, String nomeUsuario, String senha, String email) {
        super(nome, nomeUsuario, senha, email);
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
        return super.toString() + " | Tipo Cliente: Empolgado";
    }
}
