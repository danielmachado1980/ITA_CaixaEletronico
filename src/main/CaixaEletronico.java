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
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null)
			return "Usuário autenticado";
		return "Não foi possível autenticar o usuário";
	}

	public String saldo() {
		if(_hardware != null){
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		DecimalFormat valor = new DecimalFormat("0.00");
		return "O saldo é R$" + valor.format(contaRetornada.getSaldo());
	}
		
	public String depositar() {
		if(_hardware != null){
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		contaRetornada.setValor(_conta.getValor());
		{
			_servico.persistirConta(contaRetornada);
			return "Depósito recebido com sucesso";
		}
	}
	
	public String sacar() {
		if(_hardware != null){
			_conta.setNumero(Integer.parseInt(_hardware.pegarNumeroDaContaCartao()));
		}
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		contaRetornada.setValor(_conta.getValor());
		if(contaRetornada != null && contaRetornada.podeSacar()){
			_hardware.entregarDinheiro();
			_servico.persistirConta(contaRetornada);
			return "Retire seu dinheiro";
		}
		return "Saldo insuficiente";
	}

}
