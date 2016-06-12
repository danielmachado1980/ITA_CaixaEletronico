package interfaces;

import main.HardwareException;

public interface Hardware {
	String pegarNumeroDaContaCartao() throws HardwareException;
	void entregarDinheiro() throws HardwareException;
	void lerEnvelope() throws HardwareException;

}
