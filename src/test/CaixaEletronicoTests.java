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
		assertEquals("O saldo é R$0.0", c.saldo());
	}
	
	/*
	@Test
	public void sacarSucesso(){
		CaixaEletronico c = new CaixaEletronico();
		ContaCorrente cc = new ContaCorrente(123, "senha");
		assertEquals("Saque realizado com sucesso!", c.sacar(cc));
	}
	
	@Test
	public void depositarSucesso(){
		CaixaEletronico c = new CaixaEletronico();
		ContaCorrente cc = new ContaCorrente(123, "senha");
		assertEquals("Depósito realizado com sucesso!", c.depositar(cc));
	}
	
	
	*/
}
