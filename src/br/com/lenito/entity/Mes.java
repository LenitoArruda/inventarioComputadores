package br.com.lenito.entity;

public class Mes {

	private int numero;
	private String nome;
	private int tipo = 1;

	public Mes(int tipo) {

		this.tipo = tipo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {

		if (tipo == 1) {

			return nome;

		} else if (tipo == 2 && numero == 0) {

			return "< Selecione a quantidade >";

		} else {

			return Integer.toString(numero);
		}

	}

}
