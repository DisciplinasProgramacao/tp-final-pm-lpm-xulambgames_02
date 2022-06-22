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
        jogo4 = new Jogo("Jogo4", "Jogo4 é brabo", 50.00, Categoria.Promocoes);

        compra = new Compra(LocalDate.now());

        cliente1 = new Empolgado("Joao", "joao2", "123", "joao@gmail.com");
        cliente2 = new Fanatico("Bernardo", "bernardo1", "123", "bernardo@gmail.com");
        cliente3 = new Cadastrado("Ana", "Ana3", "123", "ana@gmail.com");
    }

    // #region Testes Valor Nova Compra(Preço pago) - Condições de desconto

    /**
     * Cliente Cadastrado
     * 
     * Contém três regulares (Jogo1 = 100)
     * 100 * 3 = 300
     * 
     * Não possui nenhum desconto = 300
     */
    @Test
    public void testCadastradoTresRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        // compra.adicionarJogo((Jogo)jogo1.clone());
        // Passar copia do jogo e não o jogo em si,depois validar se há alteração
        // do valor da venda

        cliente3.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente3);
        assertEquals(300, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Empolgado
     * 
     * Contém três regulares (Jogo1 = 100)
     * 100 * 3 = 300
     * 
     * 300 * 0.9 (Empolgado tem 10% de desconto) = 270
     */
    @Test
    public void testEmpolgadoTresRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente1.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente1);
        assertEquals(270, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Fanático
     * 
     * Contém três regulares (Jogo1 = 100)
     * 100 * 3 = 300
     * 
     * 300 * 0.7 (Fanático tem 30% de desconto) = 210
     */
    @Test
    public void testFanaticoTresRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente2.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente2);
        assertEquals(210, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Cadastrado
     * 
     * Contém 1 premium (jogo3 = 150)
     * 
     * Não possui nenhum desconto = 150
     */
    @Test
    public void testCadastradoUmPremiumPrecoPago() {
        compra.adicionarJogo(jogo3);

        cliente3.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente3);
        assertEquals(150, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Empolgado
     * 
     * Contém 1 premium (jogo3 = 150)
     * 
     * 150 * 0.9 (Empolgado tem 10% de desconto) = 135
     */
    @Test
    public void testEmpolgadoUmPremiumPrecoPago() {
        compra.adicionarJogo(jogo3);

        cliente1.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente1);
        assertEquals(135, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Fanático
     * 
     * Contém 1 premium (jogo3 = 150)
     * 
     * 150 * 0.7 (Fanático tem 30% de desconto) = 150
     */
    @Test
    public void testFanaticoUmPremiumPrecoPago() {
        compra.adicionarJogo(jogo3);

        cliente2.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente2);
        assertEquals(105, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Cadastrado
     * 
     * Contém 4 regulares (Jogo1 = 100)
     * 100 * 4 = 400
     * 
     * Contém quatro regulares(10% de desconto) = 400 * 0.9 = 360
     */
    @Test
    public void testCadastradoQuatroRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente3.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente3);
        assertEquals(360, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Empolgado
     * 
     * Contém 4 regulares (Jogo1 = 100)
     * 100 * 4 = 400
     * 
     * Contém quatro regulares(10% de desconto) = 400 * 0.9 = 360
     * 360 * 0.9 (Empolgado tem 10% de desconto) = 324
     */
    @Test
    public void testEmpolgadoQuatroRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente1.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente1);
        assertEquals(324, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Fanático
     * 
     * Contém 4 regulares (Jogo1 = 100)
     * 100 * 4 = 400
     * 
     * Contém quatro regulares(10% de desconto) = 400 * 0.9 = 360
     * 360 * 0.7 (Fanático tem 30% de desconto) = 252
     */
    @Test
    public void testFanaticoQuatroRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente2.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente2);
        assertEquals(252, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Cadastrado
     * 
     * Contém 2 premium (jogo3 = 150)
     * 2 * 150 = 300
     * 
     * Contém dois premium(10% de desconto) = 300 * 0.9 = 270
     */
    @Test
    public void testCadastradoDoisPremiumPrecoPago() {
        compra.adicionarJogo(jogo3);
        compra.adicionarJogo(jogo3);

        cliente3.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente3);
        assertEquals(270, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Empolgado
     * 
     * Contém 2 premium (jogo3 = 150)
     * 2 * 150 = 300
     * 
     * Contém dois premium(10% de desconto) = 300 * 0.9 = 270
     * 270 * 0.9 (Empolgado tem 10% de desconto) = 243
     */
    @Test
    public void testEmpolgadoDoisPremiumPrecoPago() {
        compra.adicionarJogo(jogo3);
        compra.adicionarJogo(jogo3);

        cliente1.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente1);
        assertEquals(243, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Fanático
     * 
     * Contém 2 premium (jogo3 = 150)
     * 2 * 150 = 300
     * 
     * Contém dois premium(10% de desconto) = 300 * 0.9 = 270
     * 270 * 0.7 (Fanático tem 30% de desconto) = 189
     */
    @Test
    public void testFanaticoDoisPremiumPrecoPago() {
        compra.adicionarJogo(jogo3);
        compra.adicionarJogo(jogo3);

        cliente2.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente2);
        assertEquals(189, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Cadastrado
     * 
     * Contém 5 regulares (Jogo1 = 100)
     * 100 * 5 = 500
     * 
     * Contém cinco regulares(20% de desconto) = 500 * 0.8 = 400
     */
    @Test
    public void testCadastradoCincoRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente3.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente3);
        assertEquals(400, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Empolgado
     * 
     * Contém 5 regulares (Jogo1 = 100)
     * 100 * 5 = 500
     * 
     * Contém quatro regulares(20% de desconto) = 500 * 0.8 = 400
     * 400 * 0.9 (Empolgado tem 10% de desconto) = 360
     */
    @Test
    public void testEmpolgadoCincoRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente1.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente1);
        assertEquals(360, compra.getPrecoPago(), 0.1);
    }

    /**
     * Cliente Fanático
     * 
     * Contém 5 regulares (Jogo1 = 100)
     * 100 * 5 = 500
     * 
     * Contém quatro regulares(20% de desconto) = 500 * 0.8 = 400
     * 400 * 0.7 (Fanático tem 30% de desconto) = 280
     */
    @Test
    public void testFanaticoCincoRegularesPrecoPago() {
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);
        compra.adicionarJogo(jogo1);

        cliente2.adicionarCompra(compra);
        compra.calcularPrecoPago(cliente2);
        assertEquals(280, compra.getPrecoPago(), 0.1);
    }

    // Terminar de testar com os demais...
    /**
     * Contém três regulares e um acima
     * Contém três premium
     * Contém dois premium e mais um jogo
     * Contém dois ou mais lançamentos
     */

    /**
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    // Testar valor das compras com os descontos denovo
    // Validar quando altera o preço do jogo de acordo com a categoria
    // Validar quando alterar a categoria e o preço do jogo
    // Validar quando alterar o usuário
    // Faz a compra e mudar a categoria do cliente
}
