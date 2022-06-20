import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompraTest {
    Empolgado cliente1;
    Fanatico cliente2;
    Cadastrado cliente3;

    Jogo jogo1;
    Jogo jogo2;
    Jogo jogo3;
    Jogo jogo4;

    Compra compra;

    @BeforeEach
    public void init() {
        jogo1 = new Jogo("Jogo1", "Jogo1 é brabo", 100.00, Categoria.Regulares);
        jogo2 = new Jogo("Jogo2", "Jogo2 é brabo", 200.00, Categoria.Lancamentos);
        jogo3 = new Jogo("Jogo3", "Jogo3 é brabo", 150.00, Categoria.Premium);
        jogo4 = new Jogo("Jogo4", "Jogo4 é brabo", 50.00, Categoria.Promoções);

        compra = new Compra(LocalDate.now());

        cliente1 = new Empolgado("Joao", "joao2", "123", "joao@gmail.com");
        cliente2 = new Fanatico("Bernardo", "bernardo1", "123", "bernardo@gmail.com");
        cliente3 = new Cadastrado("Ana", "Ana3", "123", "ana@gmail.com");
    }

    // #region Testes Valor Nova Compra(Preço pago e Preço venda)

    /**
     * Jogo regular 85% do preço original
     * Cliente empolgado 10% de desconto no preço de venda
     * 
     * Jogo1 = R$100.00 | Desconto jogo regular(85%) | Jogo1 = R$85.00
     * 
     * Jogo1 * 2 = R$170.00 (Preço de venda)
     * 
     * Como é apenas 2 regulares não tem desconto...
     * 
     * R$170.00 * 10% de Desconto = R$153.00 (Preço pago)
     */
    @Test
    public void testEmpolgadoRegularPrecoVenda() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        cliente1.adicionarCompra(compra);
        assertEquals(170, compra.getPrecoVenda(), 0.1);
    }

    @Test
    public void testEmpolgadoRegularPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.calcularPrecoPago(cliente1);
        cliente1.adicionarCompra(compra);
        assertEquals(153, compra.getPrecoPago(), 0.1);
    }

    /**
     * Jogo lançamento 110% do preço original
     * Cliente fanático 30% de desconto no preço de venda
     * 
     * Jogo2 = R$200.00 | Aumento jogo lançamento(10%) | Jogo2 = R$220.00
     * 
     * Jogo2 * 2 = R$440.00 (Preço de venda)
     * 
     * 2 Lançamentos dão 20% de desconto
     * 
     * Desconto dos lançamentos + Desconto do cliente fanático
     * R$440.00 * 50% de Desconto = R$220.00 (Preço pago)
     */
    @Test
    public void testFanaticoLancamentosPrecoVenda() {
        compra.adicionarJogo(jogo2);
        compra.adicionarJogo(jogo2);
        cliente2.adicionarCompra(compra);
        assertEquals(440, compra.getPrecoVenda(), 0.1);
    }

    @Test
    public void testFanaticoLancamentosPrecoPago() {
        compra.adicionarJogo(jogo2);
        compra.adicionarJogo(jogo2);
        compra.calcularPrecoPago(cliente2);
        cliente2.adicionarCompra(compra);
        assertEquals(220, compra.getPrecoPago(), 0.1);
    }

    /**
     * Jogo premium 100% do preço original / Sem alteração
     * Jogo promoções 40% do preço original
     * Cliente cadastrado 0% de desconto no preço de venda / Sem alteração
     * 
     * Jogo3 = R$150.00
     * Jogo4 = R$50.00 | Desconto jogo lançamento(40%) | Jogo4 = R$30.00
     * 
     * Jogo3 * 2 + jogo 4 = R$330.00 (Preço de venda)
     * 
     * 2 Premium + 1 promoção da 10% de desconto ou 20% de desconto?
     * 
     * Desconto dos lançamentos
     * R$330.00 * 20% de Desconto = R$264.00 (Preço pago)
     */
    @Test
    public void testCadastradoPremiumPromocoesPrecoVenda() {
        compra.adicionarJogo(jogo3);
        compra.adicionarJogo(jogo3);
        compra.adicionarJogo(jogo4);
        cliente3.adicionarCompra(compra);
        assertEquals(330, compra.getPrecoVenda(), 0.1);
    }

    @Test
    public void testCadastradoPremiumPromocoesPrecoPago() {
        compra.adicionarJogo(jogo3);
        compra.adicionarJogo(jogo3);
        compra.adicionarJogo(jogo4);
        compra.calcularPrecoPago(cliente3);
        cliente3.adicionarCompra(compra);
        assertEquals(297, compra.getPrecoPago(), 0.1); // 2 Premium + 1 promoção da 10% de desconto ou 20% de desconto?
    }

}
