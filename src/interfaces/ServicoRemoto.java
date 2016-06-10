package interfaces;

import main.ContaCorrente;

public interface ServicoRemoto {

	ContaCorrente recuperaConta(ContaCorrente _conta);
	void persistirConta(ContaCorrente _conta);
}
