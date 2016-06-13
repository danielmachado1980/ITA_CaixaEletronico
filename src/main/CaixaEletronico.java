package main;

import java.text.DecimalFormat;

import interfaces.Hardware;
import interfaces.ServicoRemoto;

public class CaixaEletronico {

	private ServicoRemoto _servico;
	private Hardware _hardware;
	private ContaCorrente _conta;
	
	public CaixaEletronico(ContaCorrente cc) {
		this._conta = cc;
	}
	
	public void adicionaServico(ServicoRemoto servicoRemoto) {
		this._servico = servicoRemoto;
	}
	
	public void adicionaHardware(Hardware hardware) {
		this._hardware = hardware;
	}
	
	public String logar() {
		try {
			lerNumeroCartao();
		} catch (HardwareException e) {
			return e.getMessage();
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null)
			return "Usuário autenticado";
		return "Não foi possível autenticar o usuário";
	}

	public String saldo() {
		try {
			lerNumeroCartao();
		} catch (HardwareException e) {
			return e.getMessage();
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada == null)
			return "Dados da conta inválidos";
		DecimalFormat valor = new DecimalFormat("0.00");
		return "O saldo é R$" + valor.format(contaRetornada.getSaldo());
	}
		
	public String depositar() {
		ContaCorrente contaRetornada;
		try {
			contaRetornada = retornarContaCadastrada();
		} catch (HardwareException e) {
			return e.getMessage();
		}
		if(contaRetornada == null)
			return "Dados da conta inválidos";
		contaRetornada.setValorMovimentado(_conta.getValorMovimentado());
		_servico.persistirConta(contaRetornada);
		lerEnvelope();
		return "Depósito recebido com sucesso";
	}
	
	public String sacar() {
		ContaCorrente contaRetornada;
		try {
			contaRetornada = retornarContaCadastrada();
		} catch (HardwareException e) {
			return e.getMessage();
		}
		if(contaRetornada == null)
			return "Dados da conta inválidos";
		contaRetornada.setValorMovimentado(_conta.getValorMovimentado());
		return podeSacar(contaRetornada);
	}

	private String podeSacar(ContaCorrente contaRetornada) {
		if(contaRetornada.podeSacar()){
			_servico.persistirConta(contaRetornada);
			return entregarDinheiro();
		}
		return "Saldo insuficiente";
	}

	private String entregarDinheiro() {
		if(_hardware != null)
			try {
				_hardware.entregarDinheiro();
			} catch (HardwareException ex) {
				return ex.getMessage();
			}
		return "Retire seu dinheiro";
	}
	
	private String lerEnvelope() {
		if(_hardware != null)
			try {
				_hardware.lerEnvelope();
			} catch (HardwareException ex) {
				return ex.getMessage();
			}
		return null;
	}
	
	private ContaCorrente retornarContaCadastrada() throws HardwareException {
		ContaCorrente contaRetornada;
		if(_hardware != null)
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
		contaRetornada = _servico.recuperaConta(_conta);
		return contaRetornada;
	}
	
	private void lerNumeroCartao() throws HardwareException {
		if(_hardware != null)
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
	}
}
