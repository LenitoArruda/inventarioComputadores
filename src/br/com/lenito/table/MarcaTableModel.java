package br.com.lenito.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.lenito.entity.Marca;

public class MarcaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int col_nome = 0;

	private List<Marca> valores;

	public MarcaTableModel(List<Marca> valores) {
		this.valores = valores;
	}

	@Override
	public int getRowCount() {

		return valores.size();
	}

	@Override
	public int getColumnCount() {

		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Marca marca = valores.get(rowIndex);
		if (columnIndex == col_nome) {

			return marca.getNome();

		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		String coluna = "";
		switch (column) {

		
		case col_nome:
			coluna = "Nome";
			break;
		default:
			throw new IllegalArgumentException("Coluna inválida!");
		}
		return coluna;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == col_nome) {
			return String.class;
		}
		return null;
	}

	public Marca get(int row) {
		return valores.get(row);
	}

}
