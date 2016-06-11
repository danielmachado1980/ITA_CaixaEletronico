package main;

public class ContaCorrente {
	
	private int numero;
	private String senha;
	private double saldo;
	private double valor;
	
	public ContaCorrente(int numero, String senha) {
		this.numero = numero;
		this.senha = senha;
	}

	public ContaCorrente(int numero, String senha, double valor) {
		this.numero = numero;
		this.senha = senha;
		this.valor = valor;
		this.saldo += valor;
	}
	
	public ContaCorrente(){
		
	}

	public String getSenha() {
		return this.senha;
	}

	public int getNumero() {
		return this.numero;
	}

	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public boolean podeSacar(){
		return this.getSaldo() >= Math.abs(valor);
	}

	public double getValor() {
		return this.valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
}
