package br.com.lenito.cb;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.lenito.entity.Setor;

public class SetorCB extends AbstractListModel<Setor> implements ComboBoxModel<Setor> {

	private static final long serialVersionUID = 1L;

	private List<Setor> lista;

	/* Seleciona um objeto na caixa de seleção */
	private Setor selecionado;

	/* Método construtor */
	public SetorCB(List<Setor> lista) {
		
		this.lista = lista;
		/* Popula a lista */
		popular();

		/* Define o objeto selecionado */
		setSelectedItem(lista.get(0));
	}
	
	public SetorCB(List<Setor> lista, String setor) {

		this.lista = lista;
		if (lista.size() < 1) {
			popular();
		}
		setSelecionadoString(setor, lista);
		setSelectedItem(selecionado);

	}
	
	public SetorCB(List<Setor> lista, int id){
		
		
		this.lista = lista;
		if(lista.size() < 1){
		popular();
		}
		setSelecionado(id, lista);
		setSelectedItem(selecionado);
		
		
	}

	/* Captura o tamanho da listagem */
	public int getSize() {
		int totalElementos = lista.size();
		return totalElementos;
	}

	/* Captura um elemento da lista em uma posição informada */
	public Setor getElementAt(int indice) {
		Setor t = lista.get(indice);
		return t;
	}

	/* Marca um objeto na lista como selecionado */
	public void setSelectedItem(Object item) {
		selecionado = (Setor) item;
	}

	/* Captura o objeto selecionado da lista */
	public Object getSelectedItem() {
		return selecionado;
	}
	
	//Adiciona o primeiro campo do combo box
	private void popular() {

		/* Cria o primeiro registro da lista */
		Setor primeiro = new Setor();
		primeiro.setId(0);
		primeiro.setNome("< Selecione um setor >");

		/* Adiciona o primeiro registro a lista */
		lista.add(0, primeiro);

	}
	
	public void setSelecionadoString(String setor, List<Setor> lista) {

		for (int i = 0; i < lista.size(); i++) {
			Setor s = lista.get(i);
			if (s.getNome().equals(setor)) {
				selecionado = lista.get(i);
			}

		}

	}
	
	//Seta o objeto selecionado na table para o combobox
	public void setSelecionado(int id, List<Setor> lista) {

		for(int i = 0; i < lista.size();i++){
			Setor m = lista.get(i);
			if(m.getId() == id){
				selecionado = lista.get(i);
			}
		}
		
		
	}
	
	
}
