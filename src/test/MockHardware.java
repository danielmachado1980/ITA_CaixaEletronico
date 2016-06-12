package test;

import interfaces.Hardware;
import main.HardwareException;

public class MockHardware implements Hardware{
	private boolean erro = false;
	@Override
	public String pegarNumeroDaContaCartao() throws HardwareException{
		if(erro)
			throw new HardwareException();
		return "123";
	}

	@Override
	public void entregarDinheiro() throws HardwareException {
		if(erro)
			throw new HardwareException();
	}

	@Override
	public void lerEnvelope() throws HardwareException {
		if(erro)
			throw new HardwareException();
	}

	public void lancarExcecao() {
		this.erro = true;
	}
	
}
