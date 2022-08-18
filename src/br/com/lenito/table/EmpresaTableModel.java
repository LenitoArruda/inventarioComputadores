package br.com.lenito.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.lenito.entity.Empresa;

public class EmpresaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int col_nome = 0;
	private static final int col_cnpj = 1;
	private static final int col_contato = 2;
	private static final int col_telefone = 3;

	private List<Empresa> valores;

	public EmpresaTableModel(List<Empresa> valores) {
		this.valores = valores;
	}

	@Override
	public int getRowCount() {

		return valores.size();
	}

	@Override
	public int getColumnCount() {

		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Empresa empresa = valores.get(rowIndex);
		
		if (columnIndex == col_nome) {

			return empresa.getNome();

		} else if (columnIndex == col_cnpj) {

			return empresa.getCnpj();
			
		} else if (columnIndex == col_contato) {

			return empresa.getContato();
			
		} else if (columnIndex == col_telefone) {

			return empresa.getTelefone();
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
		case col_cnpj:
			coluna = "CNPJ";
			break;
		case col_contato:
			coluna = "Contato";
			break;
		case col_telefone:
			coluna = "Telefone";
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
		} else if (columnIndex == col_cnpj) {
			return String.class;
		} else if (columnIndex == col_contato) {
			return String.class;
		} else if (columnIndex == col_telefone) {
			return String.class;
		}
		return null;
	}
	
	public Empresa get(int row) {
		return valores.get(row);
	}

}
