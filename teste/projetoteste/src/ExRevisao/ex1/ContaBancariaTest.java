package teste.projetoteste.src.ExRevisao.ex1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaTest {

    @Test
    public void testSaqueDentroDoLimite() {
        Usuario usuario = new Usuario("123.456.789-00", "João Silva", "Rua A, 123");
        ContaBancaria conta = new ContaBancaria(12345, usuario, 100);
        conta.depositar(200);
        boolean resultado = conta.sacar(250);
        Assert.assertTrue(resultado);
        assertEquals(-50, conta.getSaldo(), 0.01);
    }

    @Test
    public void testSaqueForaDoLimite() {
        Usuario usuario = new Usuario("123.456.789-00", "João Silva", "Rua A, 123");
        ContaBancaria conta = new ContaBancaria(12345, usuario, 100);
        conta.depositar(200);
        boolean resultado = conta.sacar(350);
        assertFalse(resultado);
        assertEquals(200, conta.getSaldo(), 0.01);
    }

    @Test
    public void testDepositoComSaldoNegativo() {
        Usuario usuario = new Usuario("123.456.789-00", "João Silva", "Rua A, 123");
        ContaBancaria conta = new ContaBancaria(12345, usuario, 100);
        conta.depositar(200);
        conta.sacar(250);
        conta.depositar(100);
        assertEquals(47, conta.getSaldo(), 0.01); // Taxa de 3% sobre os -50
    }

    @Test
    public void testDepositoComSaldoPositivo() {
        Usuario usuario = new Usuario("123.456.789-00", "João Silva", "Rua A, 123");
        ContaBancaria conta = new ContaBancaria(12345, usuario, 100);
        conta.depositar(200);
        assertEquals(200, conta.getSaldo(), 0.01);
    }
}
