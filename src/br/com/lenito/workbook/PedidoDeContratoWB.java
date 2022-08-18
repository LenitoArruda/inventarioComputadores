package br.com.lenito.workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.lenito.entity.Empresa;
import br.com.lenito.entity.Mes;
import br.com.lenito.utilitarios.Data;

public class PedidoDeContratoWB {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;
	private FileOutputStream fileOut;
	private Data data;
	private List<Mes> meses = popularLista();

	public PedidoDeContratoWB(String fileName, Empresa e, String pedido, String dataVenc, String obs1, String obs2,
			String desc, int qtdMeses, String mesInicio, String preco1) {

		double preco = Double.parseDouble(preco1);

		try {
			File file = new File("C:\\Users\\Gamer\\eclipse-workspace\\IventarioComputadores\\src\\xls\\PedidoDeContrato.xls");
			FileInputStream filis = new FileInputStream(file);
			//workbook = new HSSFWorkbook(ClassLoader.class.getResourceAsStream("/xls/PedidoDeContrato.xls"));
			workbook = new HSSFWorkbook(filis);
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

			for (int i = 0; i < meses.size(); i++) {
				int k = 0;

				if (meses.get(i).getNome().equals(mesInicio)) {

					for (int j = 12; j < 12 + qtdMeses; j++) {

						// Descrição
						if (i + k <= 12) {

							row = sheet.getRow(j);
							cell = row.getCell(1);
							cell.setCellValue(desc + " - " + meses.get(i + k).getNome() + " - " + data.getAno());

						} else {

							row = sheet.getRow(j);
							cell = row.getCell(1);
							cell.setCellValue(desc + " - " + meses.get((i + k) - 12).getNome() + " - "
									+ (Integer.parseInt(data.getAno()) + 1));

						}

						k++;

						// Item
						row = sheet.getRow(j);
						cell = row.getCell(0);
						cell.setCellValue(k);

						// Unidade
						row = sheet.getRow(j);
						cell = row.getCell(6);
						cell.setCellValue(1);

						// Quantidade
						row = sheet.getRow(j);
						cell = row.getCell(7);
						cell.setCellValue(1);

						// Preço unitário
						row = sheet.getRow(j);
						cell = row.getCell(8);
						cell.setCellValue(preco);

						// Preço total
						row = sheet.getRow(j);
						cell = row.getCell(9);
						cell.setCellValue(preco);

					}
				}
			}

			// Total Geral
			row = sheet.getRow(24);
			cell = row.getCell(9);
			cell.setCellValue(preco * qtdMeses);
			
			// Observação 1
			row = sheet.getRow(35);
			cell = row.getCell(1);
			cell.setCellValue(obs1);
			
			// Observação 2
			row = sheet.getRow(36);
			cell = row.getCell(1);
			cell.setCellValue(obs2);

			fileOut = new FileOutputStream(fileName + "/PedidoDeContrato.xls");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			JOptionPane.showMessageDialog(null,
					"O arquivo 'PedidoDeContrato.xls' foi gerado no diretório : " + fileName);

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo: " + ex);

		}

	}

	public List<Mes> popularLista() {

		List<Mes> meses = new ArrayList<Mes>();

		Mes m = new Mes(1);
		m.setNome("Meses");
		m.setNumero(0);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Janeiro");
		m.setNumero(1);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Fevereiro");
		m.setNumero(2);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Março");
		m.setNumero(3);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Abril");
		m.setNumero(4);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Maio");
		m.setNumero(5);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Junho");
		m.setNumero(6);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Julho");
		m.setNumero(7);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Agosto");
		m.setNumero(8);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Setembro");
		m.setNumero(9);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Outubro");
		m.setNumero(10);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Novembro");
		m.setNumero(11);
		meses.add(m);

		m = new Mes(1);
		m.setNome("Dezembro");
		m.setNumero(12);
		meses.add(m);

		return meses;
	}

}
