package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.CaixaEletronico;
import main.ContaCorrente;
import main.HardwareException;

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
		ContaCorrente cc = new ContaCorrente(123, "senha", -100.00);
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
	
	@Test
	public void logarComCartaoSucesso() {
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senha");
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Usuário autenticado", c.logar());
	}
	
	@Test
	public void logarComCartaoInsucesso() {
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senh");
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Não foi possível autenticar o usuário", c.logar());
	}
	
	@Test
	public void verificarSaldoComCartao(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senha");
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("O saldo é R$100,00", c.saldo());
	}
	
	@Test
	public void verificarSaldoComCartaoInsucesso(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senh");
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Dados da conta inválidos", c.saldo());
	}
	
	@Test
	public void depositarComCartaoSucesso(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senha", 10.00);
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Depósito recebido com sucesso", c.depositar());
		assertEquals("O saldo é R$110,00", c.saldo());
	}
	
	@Test
	public void sacarComCartaoSucesso(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senha", 100.00);
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Retire seu dinheiro", c.sacar());
	}
	
	@Test
	public void sacarComCartaoInsucesso(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senh", -100.00);
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Dados da conta inválidos", c.sacar());
	}
	
	@Test
	public void sacarComCartaoSemSaldo(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		ContaCorrente cc = new ContaCorrente("senha", -110.00);
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Saldo insuficiente", c.sacar());
	}
	
	@Test
	public void verificarSaldoComCartaoErro(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		mockH.lancarExcecao();
		ContaCorrente cc = new ContaCorrente("senha");
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Erro de hardware", c.saldo());
	}
	
	@Test
	public void depositarComCartaoErro(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		mockH.lancarExcecao();
		ContaCorrente cc = new ContaCorrente("senha", 10.00);
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Erro de hardware", c.depositar());
	}
	
	@Test
	public void sacarComCartaoErro(){
		MockServicoRemoto mockS = new MockServicoRemoto();
		MockHardware mockH = new MockHardware();
		mockH.lancarExcecao();
		ContaCorrente cc = new ContaCorrente("senha", -110.00);
		CaixaEletronico c = new CaixaEletronico(cc);
		c.adicionaServico(mockS);
		c.adicionaHardware(mockH);
		assertEquals("Erro de hardware", c.sacar());
	}
	
}
