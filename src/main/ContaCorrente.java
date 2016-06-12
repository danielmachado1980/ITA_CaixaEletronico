package main;

public class ContaCorrente {
	
	private int numero;
	private String senha;
	private double saldo;
	private double valorMovimentado;
	
	public ContaCorrente(int numero, String senha) {
		this.numero = numero;
		this.senha = senha;
	}

	public ContaCorrente(int numero, String senha, double valor) {
		this.numero = numero;
		this.senha = senha;
		this.valorMovimentado = valor;
		this.saldo += valor;
	}
	
	public ContaCorrente(String senha) {
		this.senha = senha;
	}

	public ContaCorrente(String senha, double valor) {
		this.senha = senha;
		this.valorMovimentado = valor;
	}

	public String getSenha() {
		return this.senha;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public double getValorMovimentado() {
		return this.valorMovimentado;
	}
	
	public void setValorMovimentado(double valor) {
		this.valorMovimentado = valor;
	}
	
	public boolean podeSacar(){
		return this.getSaldo() >= Math.abs(valorMovimentado);
	}

}
