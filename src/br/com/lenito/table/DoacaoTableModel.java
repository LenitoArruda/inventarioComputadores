package br.com.lenito.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.lenito.entity.Doacao;

public class DoacaoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private static final int col_tipo = 0;
	private static final int col_marca = 1;
	private static final int col_modelo = 2;
	private static final int col_serial = 3;
	private static final int col_qtd = 4;
	private static final int col_adicional = 5;


	private List<Doacao> valores;

	public DoacaoTableModel(List<Doacao> valores) {
		this.valores = valores;
	}

	@Override
	public int getRowCount() {

		return valores.size();
	}

	@Override
	public int getColumnCount() {

		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Doacao doacao = valores.get(rowIndex);
		
		if (columnIndex == col_tipo) {

			return doacao.getTipo();

		} else if (columnIndex == col_marca) {

			return doacao.getMarca();

		} else if (columnIndex == col_modelo) {

			return doacao.getModelo();

		} else if (columnIndex == col_serial) {

			return doacao.getSerialNumber();

		} else if (columnIndex == col_qtd) {

			return doacao.getQuantidade();

		} else if (columnIndex == col_adicional) {

			return doacao.getAdicional();

		}
		
		return null;
	}

	@Override
	public String getColumnName(int column) {

		String coluna = "";
		switch (column) {

		case col_tipo:
			coluna = "Tipo";
			break;
		case col_marca:
			coluna = "Marca";
			break;
		case col_modelo:
			coluna = "Modelo";
			break;
		case col_serial:
			coluna = "Serial Number";
			break;
		case col_qtd:
			coluna = "Quantidade";
			break;
		case col_adicional:
			coluna = "Adicional";
			break;
		default:
			throw new IllegalArgumentException("Coluna inválida!");
		}
		return coluna;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if(columnIndex == col_tipo){
			return String.class;
		} else if(columnIndex == col_marca){
			return String.class;
		} else if(columnIndex == col_modelo){
			return String.class;
		} else if(columnIndex == col_serial){
			return String.class;
		} else if(columnIndex == col_qtd){
			return int.class;
		} else if(columnIndex == col_adicional){
			return String.class;
		}
		return null;
	}
	
	public Doacao get(int row){
		return valores.get(row);
	}

}
