package test;

import java.util.ArrayList;
import java.util.List;

import interfaces.ServicoRemoto;
import main.ContaCorrente;

public class MockServicoRemoto implements ServicoRemoto{
	
	private List<ContaCorrente> _lstcontacorrente = new ArrayList<>();
	
	public MockServicoRemoto() {
		inicilizarValores();
	}

	@Override
	public ContaCorrente recuperaConta(ContaCorrente _conta) {
		for(ContaCorrente conta : _lstcontacorrente)
			if(conta.getNumero() == _conta.getNumero() && conta.getSenha() == _conta.getSenha())
				return conta;
		return null;
	}

	@Override
	public void persistirConta(ContaCorrente _conta) {
		for(ContaCorrente conta : _lstcontacorrente)
			if(conta.getNumero() == _conta.getNumero() && conta.getSenha() == _conta.getSenha())
				conta.setSaldo(conta.getSaldo() + _conta.getValorMovimentado());
	}
	
	private void inicilizarValores(){
		_lstcontacorrente.add(new ContaCorrente(123, "senha", 100.00));
		_lstcontacorrente.add(new ContaCorrente(1234, "psw", 100.00));
		_lstcontacorrente.add(new ContaCorrente(12356, "123", 100.00));
		_lstcontacorrente.add(new ContaCorrente(12378, "niver", 100.00));
	}

}
