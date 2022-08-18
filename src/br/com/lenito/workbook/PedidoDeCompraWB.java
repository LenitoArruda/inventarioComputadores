package br.com.lenito.workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.lenito.entity.Empresa;
import br.com.lenito.utilitarios.Data;

public class PedidoDeCompraWB {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;
	private FileOutputStream fileOut;
	private Data data;

	public PedidoDeCompraWB(String fileName, Empresa e, String nome, String dataVenc, String pedido, String obs1,
			String obs2, String[][] items) {
		
		/* 
		 * items[0][0] = Descrição Item 1 / items[0][1] = Valor Item 1 / items[0][2] = Quantidade Item 1
		 * items[1][0] = Descrição Item 2 / items[1][1] = Valor Item 2 / items[1][2] = Quantidade Item 2
		 * items[2][0] = Descrição Item 3 / items[2][1] = Valor Item 3 / items[2][2] = Quantidade Item 3
		 * items[3][0] = Descrição Item 4 / items[3][1] = Valor Item 4 / items[3][2] = Quantidade Item 4
		 * items[4][0] = Descrição Item 5 / items[4][1] = Valor Item 5 / items[4][2] = Quantidade Item 5
		 * items[5][0] = Descrição Item 6 / items[5][1] = Valor Item 6 / items[5][2] = Quantidade Item 6
		 */

		try {

			if ( items[0][1].equals("") == true) {
				 items[0][1] = "0";
			}
			double valor1 = Double.parseDouble(items[0][1]);
			
			if (items[1][1].equals("") == true) {
				items[1][1] = "0";
			}
			double valor2 = Double.parseDouble(items[1][1]);
			
			if (items[2][1].equals("") == true) {
				items[2][1] = "0";
			}
			double valor3 = Double.parseDouble(items[2][1]);
			
			if (items[3][1].equals("") == true) {
				items[3][1] = "0";
			}
			double valor4 = Double.parseDouble(items[3][1]);

			if (items[0][2].equals("") == true) {
				items[0][2] = "0";
			}
			int qtd1 = Integer.parseInt(items[0][2]);
			
			if (items[1][2].equals("") == true) {
				items[1][2] = "0";
			}
			int qtd2 = Integer.parseInt(items[1][2]);
			
			if (items[2][2].equals("") == true) {
				items[2][2] = "0";
			}
			int qtd3 = Integer.parseInt(items[2][2]);
			
			if (items[3][2].equals("") == true) {
				items[3][2] = "0";
			}
			int qtd4 = Integer.parseInt(items[3][2]);
			
			File file = new File("C:\\Users\\Gamer\\eclipse-workspace\\IventarioComputadores\\src\\xls\\PedidoDeContrato.xls");
			FileInputStream filis = new FileInputStream(file);
			workbook = new HSSFWorkbook(filis);
			//workbook = new HSSFWorkbook(ClassLoader.class.getResourceAsStream("/xls/PedidoDeContrato.xls"));
			sheet = workbook.getSheetAt(0);

			// Campo n° do pedido
			row = sheet.getRow(3);
			cell = row.getCell(1);
			cell.setCellValue(pedido);

			// Campo data
			data = new Data();
			data.lerData();
			cell = row.getCell(6);
			cell.setCellValue(data.getDia() + "/" + data.retornarNumeroMes() + "/" + data.getAno());

			// Campo fornecedor
			row = sheet.getRow(4);
			cell = row.getCell(1);
			cell.setCellValue(e.getNome());

			// Campo CNPJ
			cell = row.createCell(6);
			cell.setCellValue(e.getCnpj());

			// Campo Fone
			cell = row.getCell(9);
			cell.setCellValue(e.getTelefone());

			// Campo endereço
			row = sheet.getRow(5);
			cell = row.getCell(1);
			cell.setCellValue(e.getEndereco());

			// Campo cidade
			cell = row.getCell(5);
			cell.setCellValue(e.getCidade());

			// Campo UF
			cell = row.getCell(7);
			cell.setCellValue(e.getUf());

			// Campo CEP
			cell = row.getCell(9);
			cell.setCellValue(e.getCep());

			// Campo Contato
			row = sheet.getRow(6);
			cell = row.getCell(1);
			cell.setCellValue(e.getContato());

			// Campo N orçamento
			cell = row.getCell(5);
			cell.setCellValue(pedido);

			// Campo N contrato
			cell = row.getCell(7);
			cell.setCellValue(pedido);

			// Campo Vencimento
			cell = row.getCell(9);
			cell.setCellValue(dataVenc);

			// Campos descrição, item, unidade, quantidade, preço unitario

			// Item 1
			row = sheet.getRow(12);
			cell = row.getCell(0);
			cell.setCellValue(1);

			// Descrição 1
			row = sheet.getRow(12);
			cell = row.getCell(1);
			cell.setCellValue(items[0][0]);

			// Unidade 1
			row = sheet.getRow(12);
			cell = row.getCell(6);
			cell.setCellValue(1);

			// Quantidade 1
			row = sheet.getRow(12);
			cell = row.getCell(7);
			cell.setCellValue(qtd1);

			// Unitario 1
			row = sheet.getRow(12);
			cell = row.getCell(8);
			cell.setCellValue(valor1);

			// Total 1
			row = sheet.getRow(12);
			cell = row.getCell(9);
			cell.setCellValue(qtd1 * valor1);

			System.out.println("valor 2: " + valor2);
			if (valor2 != 0 && qtd2 != 0) {
				// Item 2
				row = sheet.getRow(13);
				cell = row.getCell(0);
				cell.setCellValue(2);

				// Descrição 2
				row = sheet.getRow(13);
				cell = row.getCell(1);
				cell.setCellValue(items[1][0]);

				// Unidade 2
				row = sheet.getRow(13);
				cell = row.getCell(6);
				cell.setCellValue(1);

				// Quantidade 2
				row = sheet.getRow(13);
				cell = row.getCell(7);
				cell.setCellValue(qtd2);

				// Unitario 2
				row = sheet.getRow(13);
				cell = row.getCell(8);
				cell.setCellValue(valor2);

				// Total 2
				row = sheet.getRow(13);
				cell = row.getCell(9);
				cell.setCellValue(qtd2 * valor2);
			}

			if (valor3 != 0 && qtd3 != 0) {
				// Item 3
				row = sheet.getRow(14);
				cell = row.getCell(0);
				cell.setCellValue(3);

				// Descrição 3
				row = sheet.getRow(14);
				cell = row.getCell(1);
				cell.setCellValue(items[2][0]);

				// Unidade 3
				row = sheet.getRow(14);
				cell = row.getCell(6);
				cell.setCellValue(1);

				// Quantidade 3
				row = sheet.getRow(14);
				cell = row.getCell(7);
				cell.setCellValue(qtd3);

				// Unitario 3
				row = sheet.getRow(14);
				cell = row.getCell(8);
				cell.setCellValue(valor3);

				// Total 3
				row = sheet.getRow(14);
				cell = row.getCell(9);
				cell.setCellValue(qtd3 * valor3);
			}

			if (valor4 != 0 && qtd4 != 0) {
				// Item 4
				row = sheet.getRow(15);
				cell = row.getCell(0);
				cell.setCellValue(4);

				// Descrição 4
				row = sheet.getRow(15);
				cell = row.getCell(1);
				cell.setCellValue(items[3][0]);

				// Unidade 4
				row = sheet.getRow(15);
				cell = row.getCell(6);
				cell.setCellValue(1);

				// Quantidade 4
				row = sheet.getRow(15);
				cell = row.getCell(7);
				cell.setCellValue(qtd4);

				// Unitario 4
				row = sheet.getRow(15);
				cell = row.getCell(8);
				cell.setCellValue(valor4);

				// Total 4
				row = sheet.getRow(15);
				cell = row.getCell(9);
				cell.setCellValue(qtd4 * valor4);
			}

			// Total Geral
			row = sheet.getRow(24);
			cell = row.getCell(9);
			cell.setCellValue((valor1 * qtd1) + (valor2 * qtd2) + (valor3 * qtd3) + (valor4 * qtd4));

			// Observação 1
			row = sheet.getRow(35);
			cell = row.getCell(1);
			cell.setCellValue(obs1);

			// Observação 2
			row = sheet.getRow(36);
			cell = row.getCell(1);
			cell.setCellValue(obs2);

			fileOut = new FileOutputStream(fileName + "/PedidoDeCompra.xls");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			JOptionPane.showMessageDialog(null,
					"O arquivo 'PedidoDeCompra .xls' foi gerado no diretório : " + fileName);

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo: " + ex);
		}

	}

}
