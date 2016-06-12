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
			return "Usu�rio autenticado";
		return "N�o foi poss�vel autenticar o usu�rio";
	}

	public String saldo() {
		try {
			lerNumeroCartao();
		} catch (HardwareException e) {
			return e.getMessage();
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null){
			DecimalFormat valor = new DecimalFormat("0.00");
			return "O saldo � R$" + valor.format(contaRetornada.getSaldo());
		}else
			return "Dados da conta inv�lidos";
		
	}
		
	public String depositar() {
		ContaCorrente contaRetornada;
		try {
			contaRetornada = retornarContaCadastrada();
		} catch (HardwareException e) {
			return e.getMessage();
		}
		if(contaRetornada != null){
			contaRetornada.setValorMovimentado(_conta.getValorMovimentado());
			_servico.persistirConta(contaRetornada);
			if(_hardware != null)
				try {
					_hardware.lerEnvelope();
				} catch (HardwareException ex) {
					return ex.getMessage();
				}
			return "Dep�sito recebido com sucesso";
		}
		return "Dados da conta inv�lidos";
	}
	
	public String sacar() {
		ContaCorrente contaRetornada;
		try {
			contaRetornada = retornarContaCadastrada();
		} catch (HardwareException e) {
			return e.getMessage();
		}
		if(contaRetornada != null){
			contaRetornada.setValorMovimentado(_conta.getValorMovimentado());
			if(contaRetornada.podeSacar()){
				_servico.persistirConta(contaRetornada);
				if(_hardware != null)
					try {
						_hardware.entregarDinheiro();
					} catch (HardwareException ex) {
						return ex.getMessage();
					}
				return "Retire seu dinheiro";
			}
			return "Saldo insuficiente";
		}
		return "Dados da conta inv�lidos";
	}
	
	private ContaCorrente retornarContaCadastrada() throws HardwareException {
		ContaCorrente contaRetornada;
		if(_hardware != null){
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
			contaRetornada = _servico.recuperaConta(_conta);
		}else
			contaRetornada = _servico.recuperaConta(_conta);
		return contaRetornada;
	}
	
	private void lerNumeroCartao() throws HardwareException {
		if(_hardware != null)
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
	}
}
