package br.com.lenito.entity;

import java.util.Objects;

public class Marca {

	private int id;
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		String texto = nome;
		return texto;
	}

	@Override
	public boolean equals(Object obj) {
		Marca f = (Marca) obj;
		return Objects.equals(this.id, f.id);
	}

}
