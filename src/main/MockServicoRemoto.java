package main;

import java.util.HashMap;
import java.util.Map;

import interfaces.ServicoRemoto;

public class MockServicoRemoto implements ServicoRemoto{
	
	static Map<String, Double> resource = new HashMap<String, Double>();
	
	public MockServicoRemoto() {
		inicilizarValores();
	}

	@Override
	public ContaCorrente recuperaConta(ContaCorrente _conta) {
		ContaCorrente retorno = new ContaCorrente(123, "senha");
		if (retorno.getSenha() == _conta.getSenha())
			return retorno;
		return null;
	}

	@Override
	public void persistirConta(ContaCorrente _conta) {
		
	}
	
	private void inicilizarValores(){
		resource.put("123", 10.00);
		resource.put("1234", 10.00);
		resource.put("1235", 10.00);
		resource.put("1236", 10.00);
	}

}
