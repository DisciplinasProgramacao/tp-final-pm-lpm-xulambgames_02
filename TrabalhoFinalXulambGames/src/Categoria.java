public enum Categoria {
    Lancamentos(3, 1.1),
    Premium(2, 1), // Valor fixo ou aleatório entre? Math.random()
    Regulares(1, 0.85), // Valor fixo ou aleatório entre? Math.random()
    Promoções(0, 0.6);

    int valorCalculaBase;
    /**
     * Lançamentos - Vendidos com adicional de 10% ao preço original
     * Premium - Vendidos pelo preço original
     * Regulares - Vendidos por um valor entre 70 e 100% do preço original
     * Promoções - Vendidos por um valor entre 30 e 50% do preço original
     */
    double valorCategoria;

    Categoria(int valorCalculaBase, double valorAcrescimoJogo) {
        this.valorCalculaBase = valorCalculaBase;
        this.valorCategoria = valorAcrescimoJogo;
    }

    public int getValorCalculoBase() {
        return this.valorCalculaBase;
    }

    public double getValorCategoria() {
        return this.valorCategoria;
    }
}
