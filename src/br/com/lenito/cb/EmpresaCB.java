package br.com.lenito.cb;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.lenito.entity.Empresa;


public class EmpresaCB extends AbstractListModel<Empresa> implements ComboBoxModel<Empresa>{

	private static final long serialVersionUID = 1L;

	private List<Empresa> lista;

	/* Seleciona um objeto na caixa de seleção */
	private Empresa selecionado;

	/* Método construtor */
	public EmpresaCB(List<Empresa> lista) {

		this.lista = lista;
		/* Popula a lista */
		popular();

		/* Define o objeto selecionado */
		setSelectedItem(lista.get(0));

	}

	public EmpresaCB(List<Empresa> lista, int id) {

		this.lista = lista;
		if (lista.size() < 1) {
			popular();
		}
		setSelecionado(id, lista);
		setSelectedItem(selecionado);

	}

	public EmpresaCB(List<Empresa> lista, String empresa) {

		this.lista = lista;
		if (lista.size() < 1) {
			popular();
		}
		setSelecionadoString(empresa, lista);
		setSelectedItem(selecionado);

	}

	/* Captura o tamanho da listagem */
	public int getSize() {
		int totalElementos = lista.size();
		return totalElementos;
	}

	/* Captura um elemento da lista em uma posição informada */
	public Empresa getElementAt(int indice) {
		Empresa t = lista.get(indice);
		return t;
	}

	/* Marca um objeto na lista como selecionado */
	public void setSelectedItem(Object item) {
		selecionado = (Empresa) item;
	}

	public void setSelecionado(int id, List<Empresa> lista) {

		for (int i = 0; i < lista.size(); i++) {
			Empresa m = lista.get(i);
			if (m.getId() == id) {
				selecionado = lista.get(i);
			}
		}

	}

	public void setSelecionadoString(String empresa, List<Empresa> lista) {

		for (int i = 0; i < lista.size(); i++) {
			Empresa m = lista.get(i);
			if (m.getNome().equals(empresa)) {
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
		Empresa primeiro = new Empresa();
		primeiro.setId(0);
		primeiro.setNome("< Selecione uma Empresa >");

		/* Adiciona o primeiro registro a lista */
		lista.add(0, primeiro);

	}

}
