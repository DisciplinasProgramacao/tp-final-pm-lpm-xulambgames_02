public enum Categoria {
    Lancamentos(3, 1.1, 1.1),
    Premium(2, 0, 0),
    Regulares(1, 0.7, 1),
    Promocoes(0.2, 0.3, 0.5);

    double valorCalculaBase;
    /**
     * Lançamentos - Vendidos com adicional de 10% ao preço original
     * Premium - Vendidos pelo preço original
     * Regulares - Vendidos por um valor entre 70 e 100% do preço original
     * Promoções - Vendidos por um valor entre 30 e 50% do preço original
     */
    double valorMinimo;
    double valorMaximo;

    Categoria(double valorCalculaBase, double valorMinimo, double valorMaximo) {
        this.valorCalculaBase = valorCalculaBase;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    public double getValorCalculoBase() {
        return this.valorCalculaBase;
    }

    public boolean descontoValido(double pctDesconto) {
        if (pctDesconto <= valorMaximo && pctDesconto >= valorMinimo)
            return true;
        else
            return false;
    }
}
