package br.com.lenito.cb;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.lenito.entity.Marca;

public class MarcaCB extends AbstractListModel<Marca> implements ComboBoxModel<Marca> {

	private static final long serialVersionUID = 1L;

	private List<Marca> lista;

	/* Seleciona um objeto na caixa de seleção */
	private Marca selecionado;

	/* Método construtor */
	public MarcaCB(List<Marca> lista) {

		this.lista = lista;
		/* Popula a lista */
		popular();

		/* Define o objeto selecionado */
		setSelectedItem(lista.get(0));

	}

	public MarcaCB(List<Marca> lista, int id) {

		this.lista = lista;
		if (lista.size() < 1) {
			popular();
		}
		setSelecionado(id, lista);
		setSelectedItem(selecionado);

	}

	public MarcaCB(List<Marca> lista, String marca) {

		this.lista = lista;
		if (lista.size() < 1) {
			popular();
		}
		setSelecionadoString(marca, lista);
		setSelectedItem(selecionado);

	}

	/* Captura o tamanho da listagem */
	public int getSize() {
		int totalElementos = lista.size();
		return totalElementos;
	}

	/* Captura um elemento da lista em uma posição informada */
	public Marca getElementAt(int indice) {
		Marca t = lista.get(indice);
		return t;
	}

	/* Marca um objeto na lista como selecionado */
	public void setSelectedItem(Object item) {
		selecionado = (Marca) item;
	}

	public void setSelecionado(int id, List<Marca> lista) {

		for (int i = 0; i < lista.size(); i++) {
			Marca m = lista.get(i);
			if (m.getId() == id) {
				selecionado = lista.get(i);
			}
		}

	}

	public void setSelecionadoString(String marca, List<Marca> lista) {

		for (int i = 0; i < lista.size(); i++) {
			Marca m = lista.get(i);
			if (m.getNome().equals(marca)) {
				selecionado = lista.get(i);
			}

		}

	}

	/* Captura o objeto selecionado da lista */
	public Object getSelectedItem() {
		return selecionado;
	}

	private void popular() {

		/* Cria o primeiro registro da lista */
		Marca primeiro = new Marca();
		primeiro.setId(0);
		primeiro.setNome("< Selecione uma Marca >");

		/* Adiciona o primeiro registro a lista */
		lista.add(0, primeiro);

	}

}