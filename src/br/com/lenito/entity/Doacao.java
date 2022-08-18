package br.com.lenito.entity;

public class Doacao {

	private int id;
	private int quantidade;
	private String tipo;
	private String marca;
	private String modelo;
	private String serialNumber;
	private String adicional;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	@Override
	public String toString() {
		String saida;
		if (tipo.equals("Mouse") || tipo.equals("Teclado")) {
			saida = quantidade + " " + tipo + " / " + marca + " / " + adicional;
		} else {
			saida = tipo + " / " + marca + " / " + serialNumber;
		}
		return saida;
	}

}
