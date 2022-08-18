package br.com.lenito.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.lenito.entity.Computador;

/**
 * @author lenito.gama
 *
 */
public class ComputadorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int col_tipo = 0;
	private static final int col_nome = 1;
	private static final int col_ip = 2;
	private static final int col_modelo = 3;
	private static final int col_serial = 4;
	private static final int col_marca = 5;
	private static final int col_setor = 6;
	private static final int col_usuario = 7;
	

	private List<Computador> valores;

	public ComputadorTableModel(List<Computador> valores) {
		this.valores = valores;
	}

	@Override
	public int getRowCount() {

		return valores.size();
	}

	@Override
	public int getColumnCount() {

		return 8;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Computador computador = valores.get(rowIndex);
		if (columnIndex == col_nome) {

			return computador.getNome();

		} else if (columnIndex == col_ip) {

			return computador.getIp();

		} else if (columnIndex == col_modelo) {

			return computador.getModelo();

		} else if (columnIndex == col_serial) {

			return computador.getSerialNumber();

		} else if (columnIndex == col_marca) {

			return computador.getMarca();

		} else if (columnIndex == col_setor) {

			return computador.getSetor();

		} else if (columnIndex == col_usuario) {

			return computador.getUsuario();

		}  else if (columnIndex == col_tipo) {

			return computador.getTipo();

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
		case col_nome:
			coluna = "Nome";
			break;
		case col_ip:
			coluna = "IP";
			break;
		case col_modelo:
			coluna = "Modelo";
			break;
		case col_serial:
			coluna = "Serial Number";
			break;
		case col_setor:
			coluna = "Setor";
			break;
		case col_marca:
			coluna = "Marca";
			break;
		case col_usuario:
			coluna = "Usuário";
			break;
		
		default:
			throw new IllegalArgumentException("Coluna inválida!");
		}
		return coluna;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if(columnIndex == col_nome){
			return String.class;
		} else if(columnIndex == col_ip){
			return String.class;
		} else if(columnIndex == col_modelo){
			return String.class;
		} else if(columnIndex == col_serial){
			return String.class;
		} else if(columnIndex == col_marca){
			return int.class;
		} else if(columnIndex == col_setor){
			return int.class;
		} else if(columnIndex == col_usuario){
			return String.class;
		} else if(columnIndex == col_tipo){
			return String.class;
		}
		return null;
	}
	
	public Computador get(int row){
		return valores.get(row);
	}

}
