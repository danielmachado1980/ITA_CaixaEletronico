package main;

public class ContaCorrente {
	
	private int numero;
	private String senha;
	private double valor;
	private double saldo;
	
	public ContaCorrente(int numero, String senha) {
		this.numero = numero;
		this.senha = senha;
	}

	public ContaCorrente(int numero, String senha, double valor) {
		this.numero = numero;
		this.senha = senha;
		this.valor += valor;
	}

	public String getSenha() {
		return this.senha;
	}

	public int getNumero() {
		return this.numero;
	}

	public double getValor() {
		return this.valor;
	}

	public double getSaldo() {
		return this.saldo;
	}

}
