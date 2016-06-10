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
			return "Usuário autenticado";
		return "Não foi possível autenticar o usuário";
	}

	public String saldo() {
		ContaCorrente contaRetornada = _servico.recuperaConta(_conta);
		return "O saldo é R$" + contaRetornada.getSaldo();
	}
	
	public String depositar() {
		return "Depósito recebido com sucesso!";
	}
    
	public String sacar() {
		return "Saque realizado com sucesso!";
	}

}
