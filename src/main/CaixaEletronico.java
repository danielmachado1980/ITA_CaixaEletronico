package main;

import interfaces.ServicoRemoto;

public class CaixaEletronico {

	private ServicoRemoto _servico;
	private ContaCorrente _conta;
	
	public CaixaEletronico(ContaCorrente cc) {
		this._conta = cc;
	}
	
	public void adicionaServico(ServicoRemoto servicoRemoto) {
		this._servico = servicoRemoto;
	}
	
	public String logar() {
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		if(contaRetornada != null)
			return "Usu�rio autenticado";
		return "N�o foi poss�vel autenticar o usu�rio";
	}

	public String saldo() {
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		return "O saldo � R$" + contaRetornada.getSaldo();
	}
	
	public String depositar() {
		return "Dep�sito recebido com sucesso!";
	}
    
	public String sacar() {
		return "Saque realizado com sucesso!";
	}

}
