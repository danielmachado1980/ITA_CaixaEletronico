package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.CaixaEletronico;
import main.ContaCorrente;
import main.MockServicoRemoto;

public class CaixaEletronicoTests {
	
	@Test
	public void logarSucesso() {
		ContaCorrente cc = new ContaCorrente(123, "senha");
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("Usuário autenticado", c.logar());
	}
	
	@Test
	public void logarInsucesso() {
		ContaCorrente cc = new ContaCorrente(123, "senh");
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("Não foi possível autenticar o usuário", c.logar());
	}
	
	@Test
	public void verificarSaldo(){
		ContaCorrente cc = new ContaCorrente(123, "senha");
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("O saldo é R$100,00", c.saldo());
	}
			
	@Test
	public void depositarSucesso(){
		ContaCorrente cc = new ContaCorrente(123, "senha", 10.00);
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("Depósito recebido com sucesso", c.depositar());
		assertEquals("O saldo é R$110,00", c.saldo());
	}
	
	
	@Test
	public void sacarSucesso(){
		ContaCorrente cc = new ContaCorrente(123, "senha", 100.00);
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("Retire seu dinheiro", c.sacar());
	}
	
	@Test
	public void sacarInsucesso(){
		ContaCorrente cc = new ContaCorrente(123, "senha", -101.00);
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("Saldo insuficiente", c.sacar());
	}
	
}
