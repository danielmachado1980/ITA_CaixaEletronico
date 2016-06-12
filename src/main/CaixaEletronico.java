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
		if(_hardware != null){
			try {
				_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
			} catch (HardwareException e) {
				return e.getMessage();
			}
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null)
			return "Usuário autenticado";
		return "Não foi possível autenticar o usuário";
	}

	public String saldo() {
		if(_hardware != null){
			try {
				_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
			} catch (HardwareException e) {
				return e.getMessage();
			}
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null){
			DecimalFormat valor = new DecimalFormat("0.00");
			return "O saldo é R$" + valor.format(contaRetornada.getSaldo());
		}else
			return "Dados da conta inválidos";
		
	}
		
	public String depositar() {
		ContaCorrente contaRetornada;
		if(_hardware != null){
			try {
				_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
			} catch (HardwareException e) {
				return e.getMessage();
			}
			contaRetornada = _servico.recuperaConta(_conta);
		}else
			contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null){
			contaRetornada.setValorMovimentado(_conta.getValorMovimentado());
			_servico.persistirConta(contaRetornada);
			if(_hardware != null)
				try {
					_hardware.lerEnvelope();
				} catch (HardwareException e) {
					return e.getMessage();
				}
			return "Depósito recebido com sucesso";
		}
		return "Dados da conta inválidos";
	}
	
	public String sacar() {
		ContaCorrente contaRetornada;
		if(_hardware != null){
			try {
				_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
			} catch (HardwareException e) {
				return e.getMessage();
			}
			contaRetornada = _servico.recuperaConta(_conta);
		}else
			contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null){
			contaRetornada.setValorMovimentado(_conta.getValorMovimentado());
			if(contaRetornada.podeSacar()){
				_servico.persistirConta(contaRetornada);
				if(_hardware != null)
					try {
						_hardware.entregarDinheiro();
					} catch (HardwareException e) {
						return e.getMessage();
					}
				return "Retire seu dinheiro";
			}
			return "Saldo insuficiente";
		}
		return "Dados da conta inválidos";
	}

}
