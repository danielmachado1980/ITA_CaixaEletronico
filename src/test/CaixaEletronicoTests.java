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
		assertEquals("Usu�rio autenticado", c.logar());
	}
	
	@Test
	public void logarInsucesso() {
		ContaCorrente cc = new ContaCorrente(123, "senh");
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("N�o foi poss�vel autenticar o usu�rio", c.logar());
	}
	
	@Test
	public void verificarSaldo(){
		ContaCorrente cc = new ContaCorrente(123, "senha");
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("O saldo � R$100,00", c.saldo());
	}
			
	@Test
	public void depositarSucesso(){
		ContaCorrente cc = new ContaCorrente(123, "senha", 10.00);
		MockServicoRemoto mock = new MockServicoRemoto();
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mock);
		assertEquals("Dep�sito recebido com sucesso", c.depositar());
		assertEquals("O saldo � R$110,00", c.saldo());
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
