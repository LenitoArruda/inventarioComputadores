package br.com.lenito.cb;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.lenito.entity.Uf;

public class UfCB extends AbstractListModel<Uf> implements ComboBoxModel<Uf> {

	private static final long serialVersionUID = 1L;

	private List<Uf> lista = new ArrayList<Uf>();

	
	private Uf selecionado;

	
	public UfCB() {

		popular();
		setSelectedItem(lista.get(0));
	}

	public UfCB(String uf) {

		popular();
		setSelecionadoString(uf);
		setSelectedItem(selecionado);

	}

	/* Captura o tamanho da listagem */
	public int getSize() {
		int totalElementos = lista.size();
		return totalElementos;
	}

	/* Captura um elemento da lista em uma posição informada */
	public Uf getElementAt(int indice) {
		Uf t = lista.get(indice);
		return t;
	}

	/* Marca um objeto na lista como selecionado */
	public void setSelectedItem(Object item) {
		selecionado = (Uf) item;
	}

	/* Captura o objeto selecionado da lista */
	public Object getSelectedItem() {
		return selecionado;
	}

	// Adiciona o primeiro campo do combo box
	private void popular() {

		/* Cria o primeiro registro da lista */
		Uf estado = new Uf();
		estado.setNome("< UF >");
		lista.add(0, estado);
		
		estado = new Uf();
		estado.setNome("AC");
		lista.add(1, estado);

		estado = new Uf();
		estado.setNome("AL");
		lista.add(2, estado);

		estado = new Uf();
		estado.setNome("AP");
		lista.add(3, estado);

		estado = new Uf();
		estado.setNome("AM");
		lista.add(4, estado);

		estado = new Uf();
		estado.setNome("BA");
		lista.add(5, estado);

		estado = new Uf();
		estado.setNome("CE");
		lista.add(6, estado);

		estado = new Uf();
		estado.setNome("DF");
		lista.add(7, estado);

		estado = new Uf();
		estado.setNome("ES");
		lista.add(8, estado);

		estado = new Uf();
		estado.setNome("GO");
		lista.add(9, estado);

		estado = new Uf();
		estado.setNome("MA");
		lista.add(10, estado);

		estado = new Uf();
		estado.setNome("MT");
		lista.add(11, estado);

		estado = new Uf();
		estado.setNome("MS");
		lista.add(12, estado);

		estado = new Uf();
		estado.setNome("MG");
		lista.add(13, estado);

		estado = new Uf();
		estado.setNome("PA");
		lista.add(14, estado);

		estado = new Uf();
		estado.setNome("PB");
		lista.add(15, estado);

		estado = new Uf();
		estado.setNome("PR");
		lista.add(16, estado);

		estado = new Uf();
		estado.setNome("PE");
		lista.add(17, estado);

		estado = new Uf();
		estado.setNome("PI");
		lista.add(18, estado);

		estado = new Uf();
		estado.setNome("RJ");
		lista.add(19, estado);

		estado = new Uf();
		estado.setNome("RN");
		lista.add(20, estado);

		estado = new Uf();
		estado.setNome("RS");
		lista.add(21, estado);

		estado = new Uf();
		estado.setNome("RO");
		lista.add(22, estado);

		estado = new Uf();
		estado.setNome("RR");
		lista.add(23, estado);

		estado = new Uf();
		estado.setNome("SC");
		lista.add(24, estado);

		estado = new Uf();
		estado.setNome("SP");
		lista.add(25, estado);

		estado = new Uf();
		estado.setNome("SE");
		lista.add(26, estado);

		estado = new Uf();
		estado.setNome("TO");
		lista.add(27, estado);

	}

	public void setSelecionadoString(String uf) {

		for (int i = 0; i < lista.size(); i++) {
			Uf s = lista.get(i);
			if (s.getNome().equals(uf)) {
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
