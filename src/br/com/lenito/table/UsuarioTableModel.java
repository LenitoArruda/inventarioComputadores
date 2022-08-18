package br.com.lenito.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.lenito.entity.Usuario;

public class UsuarioTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int col_nome = 0;

	private List<Usuario> valores;

	public UsuarioTableModel(List<Usuario> valores) {
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

		Usuario usuario = valores.get(rowIndex);

		if (columnIndex == col_nome) {

			return usuario.getNome();

		}

		return null;

	}

	@Override
	public String getColumnName(int column) {

		String coluna = "";
		switch (column) {

		case col_nome:
			coluna = "Usuário";
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
	
	public Usuario get(int row){
		
		return valores.get(row);
	}

}
