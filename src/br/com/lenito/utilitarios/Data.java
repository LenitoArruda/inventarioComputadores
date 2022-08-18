package br.com.lenito.utilitarios;

import java.util.Date;

public class Data {

	private String dia;
	private String mes;
	private String ano;
	private String diaSemana;

	public String getDia() {
		return dia;
	}

	public String getMes() {
		return mes;
	}

	public String getAno() {
		return ano;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getDia_semana() {
		return diaSemana;
	}

	@SuppressWarnings("deprecation")
	public void lerData() {
		Date data = new Date();

		this.dia 	  = "" + data.getDate();
		this.ano		  = "" + (1900 + data.getYear());
		this.diaSemana = "" + data.getDay();
		
		
		switch(data.getDay()){
		case 0: this.diaSemana = "Domingo";break;
		case 1: this.diaSemana  = "Segunda-Feira";break;
		case 2: this.diaSemana  = "Terça-Feira";break;
		case 3: this.diaSemana  = "Quarta-Feira";break;
		case 4: this.diaSemana  = "Quinta-Feira";break;
		case 5: this.diaSemana  = "Sexta-Feira";break;
		case 6: this.diaSemana  = "Sábado";break;
		}
		
		switch(data.getMonth()){
		case 0: this.mes = "Janeiro";break;
		case 1: this.mes = "Fevereiro";break;
		case 2: this.mes = "Março";break;
		case 3: this.mes = "Abril";break;
		case 4: this.mes = "Maio";break;
		case 5: this.mes = "Junho";break;
		case 6: this.mes = "Julho";break;
		case 7: this.mes = "Agosto";break;
		case 8: this.mes = "Setembro";break;
		case 9: this.mes = "Outubro";break;
		case 10: this.mes = "Novembro";break;
		case 11: this.mes = "Dezembro";break;
		
		}
		
		
		
	}
	
	@SuppressWarnings("deprecation")
	public int retornarNumeroMes(){
		Date d = new Date();
		return d.getMonth()+1;
	}


}
