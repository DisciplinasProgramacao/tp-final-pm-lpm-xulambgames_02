
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoTest {
    Jogo jogo1;
    Jogo jogo2;
    Jogo jogo3;
    Jogo jogo4;

    @BeforeEach
    public void init() {
        jogo1 = new Jogo("Jogo1", "Jogo1 é brabo", 100.00, Categoria.Regulares);
        jogo2 = new Jogo("Jogo2", "Jogo2 é brabo", 200.00, Categoria.Lancamentos);
        jogo3 = new Jogo("Jogo3", "Jogo3 é brabo", 200.00, Categoria.Premium);
        jogo4 = new Jogo("Jogo4", "Jogo4 é brabo", 100.00, Categoria.Promocoes);
    }

    // Validar quando alterar o usuário

    // #region Testes Valor Nova Compra(Preço pago e Preço venda)

    /**
     * Validação quando a categoria e o preço do jogo é alterado
     * 
     * Jogo regular vendido a 70 e 100% do valor original
     * para
     * Jogo promocoes vendido entre 30 e 50% do valor original
     */
    @Test
    public void testPrecoAtualRegularPromocoesValido() { // Alterando categoria e valor (Preço original 100)
        jogo1.alterarCategoria(Categoria.Promocoes, 35); // 35% do valor original é válido!
        assertEquals(35, jogo1.getPrecoAtual(), 0.1);
    }

    @Test
    public void testPrecoAtualRegularPromocoesInvalido() { // Alterando categoria e valor (Preço original 100)
        jogo1.alterarCategoria(Categoria.Promocoes, 35); // 35% do valor original é válido!
        jogo1.alterarPrecoAtual(25); // 25% do valor original é inválido!
        assertEquals(35, jogo1.getPrecoAtual(), 0.1); // Mantém o valor anterior válido que foi 35
    }

    @Test
    public void testPromocoesPrecoAtualValido() { // Alterando apenas o valor (Preço original 100)
        jogo4.alterarPrecoAtual(50); // 50% do valor original é válido!
        assertEquals(50, jogo4.getPrecoAtual(), 0.1);
    }

    @Test
    public void testPromocoesPrecoAtualInvalido() { // Alterando apenas o valor (Preço original 100)
        jogo4.alterarPrecoAtual(51); // 35% do valor origina é inválido!
        assertEquals(100, jogo4.getPrecoAtual(), 0.1); // Mantém o valor anterior válido que foi 100
    }

    /**
     * Validação quando a categoria e o preço do jogo é alterado
     * 
     * Jogo premium vendido a 100% do valor original
     * para
     * Jogo Lancamentos vendido a 110% do valor original
     */
    @Test
    public void testPrecoAtualPremiumLancamentoValido() { // Alterando categoria e valor (Preço original 200)
        jogo3.alterarCategoria(Categoria.Lancamentos, 220); // 110% do valor original é válido!
        assertEquals(220, jogo3.getPrecoAtual(), 0.1);
    }

    @Test
    public void testPrecoAtualPremiumLancamentoInvalido() { // Alterando categoria e valor (Preço original 200)
        jogo3.alterarCategoria(Categoria.Lancamentos, 201); // 101% do valor original é inválido!
        assertEquals(200, jogo3.getPrecoAtual(), 0.1); // Mantém o valor anterior válido que foi 200
    }

    @Test
    public void testLancamentoPrecoAtualValido() { // Alterando apenas o valor (Preço original 200)
        jogo2.alterarPrecoAtual(220); // 110% do valor original é válido!
        assertEquals(220, jogo2.getPrecoAtual(), 0.1);
    }

    @Test
    public void testLancamentoPrecoAtualInvalido() { // Alterando apenas o valor (Preço original 200)
        jogo2.alterarPrecoAtual(201); // 101% do valor original é inválido!
        assertEquals(200, jogo2.getPrecoAtual(), 0.1); // Mantém o valor anterior válido que foi 200
    }
}
