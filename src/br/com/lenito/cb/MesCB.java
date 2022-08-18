package br.com.lenito.cb;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.lenito.entity.Mes;

public class MesCB extends AbstractListModel<Mes> implements ComboBoxModel<Mes>{

	private static final long serialVersionUID = 1L;
	private Mes selecionado;
	private List<Mes> lista = new ArrayList<Mes>();
	private int tipo;

	public MesCB(int tipo) {
		
		this.tipo = tipo;
		popular();
		setSelectedItem(lista.get(0));
		

	}

	/* Captura o tamanho da listagem */
	public int getSize() {
		int totalElementos = lista.size();
		return totalElementos;
	}

	/* Captura um elemento da lista em uma posição informada */
	public Mes getElementAt(int indice) {
		Mes t = lista.get(indice);
		return t;
	}

	/* Marca um objeto na lista como selecionado */
	public void setSelectedItem(Object item) {
		selecionado = (Mes) item;
	}

	/* Captura o objeto selecionado da lista */
	public Object getSelectedItem() {
		return selecionado;
	}

	// Adiciona o primeiro campo do combo box
	private void popular() {
		
		/*
		 * Tipo 1 = Nome meses
		 * Tipo 2 = Quantidade Meses
		 *  
		 */
		
		Mes mes = new Mes(tipo);
		mes.setNome("< Selecione o Mês >");
		mes.setNumero(0);
		lista.add(0, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Janeiro");
		mes.setNumero(1);
		lista.add(1, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Fevereiro");
		mes.setNumero(2);
		lista.add(2, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Março");
		mes.setNumero(3);
		lista.add(3, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Abril");
		mes.setNumero(4);
		lista.add(4, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Maio");
		mes.setNumero(5);
		lista.add(5, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Junho");
		mes.setNumero(6);
		lista.add(6, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Julho");
		mes.setNumero(7);
		lista.add(7, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Agosto");
		mes.setNumero(8);
		lista.add(8, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Setembro");
		mes.setNumero(9);
		lista.add(9, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Outubro");
		mes.setNumero(10);
		lista.add(10, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Novembro");
		mes.setNumero(11);
		lista.add(11, mes);
		
		mes = new Mes(tipo);
		mes.setNome("Dezembro");
		mes.setNumero(12);
		lista.add(12, mes);
	}
	
	public void setSelecionadoString(String mes) {

		for (int i = 0; i < lista.size(); i++) {
			Mes s = lista.get(i);
			if (s.getNome().equals(mes)) {
				selecionado = lista.get(i);
			}

		}

	}
	
	/*
	 * Seta o objeto selecionado na table para o combobox public void
	 * setSelecionado(int id, List<Setor> lista) {
	 * 
	 * for(int i = 0; i < lista.size();i++){ Uf m = lista.get(i); if(m.getId() ==
	 * id){ selecionado = lista.get(i); } }
	 * 
	 * 
	 * }
	 */
}
