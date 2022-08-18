package br.com.lenito.workbook;

import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;


import br.com.lenito.controller.DoacaoController;
import br.com.lenito.entity.Doacao;

public class DoacaoWB {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow rowHead, row, rowBot;
	private FileOutputStream fileOut; 
	private List<Doacao> listaDoacao = new DoacaoController().findDoacoes();

	public DoacaoWB(String fileName) {

		try {

			// Local do Arquivo
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet("Doações");
			// Criando as linhas
			rowHead = sheet.createRow((short) 0);
			// Definindo valores da primeira linha
			criandoCelulaTopo(workbook, rowHead, 0, "Tipo");
			criandoCelulaTopo(workbook, rowHead, 1, "Marca");
			criandoCelulaTopo(workbook, rowHead, 2, "Modelo");
			criandoCelulaTopo(workbook, rowHead, 3, "Serial Number");
			criandoCelulaTopo(workbook, rowHead, 4, "Quantidade");
			criandoCelulaTopo(workbook, rowHead, 5, "Adicional");

			
			//Atribuindo os valores do banco para o arquivo xls
			int j = 1;
			for (int i = 0; i < listaDoacao.size(); i++) {
				Doacao c = new Doacao();
				c = listaDoacao.get(i);
				row = sheet.createRow((short) j);
				j++;
				criandoCelulaStr(workbook, row, 0, c.getTipo());
				criandoCelulaStr(workbook, row, 1, c.getMarca());
				criandoCelulaStr(workbook, row, 2, c.getModelo());
				criandoCelulaStr(workbook, row, 3, c.getSerialNumber());
				criandoCelulaInt(workbook, row, 4, c.getQuantidade());
				criandoCelulaStr(workbook, row, 5, c.getAdicional());

			}
			//Definindo os valores da ultima linha
			rowBot = sheet.createRow((short) j);
			criandoCelulaBaixo(workbook, rowBot, 0);
			criandoCelulaBaixo(workbook, rowBot, 1);
			criandoCelulaBaixo(workbook, rowBot, 2);
			criandoCelulaBaixo(workbook, rowBot, 3);
			criandoCelulaBaixo(workbook, rowBot, 4);
			criandoCelulaBaixo(workbook, rowBot, 5);
			
			//Ajustando automaticamento o tamanho das colunas
			int quantidadeColunas = sheet.getRow(0).getPhysicalNumberOfCells();
			for(int i = 0; i < quantidadeColunas; i++ ) {
			     sheet.autoSizeColumn(i);
			}
			
			//Gerando o arquivo xls
			fileOut = new FileOutputStream(fileName + "/ListaDoacao.xls");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			JOptionPane.showMessageDialog(null, "O arquivo 'ListaDoacao.xls' foi gerado no diretório : " + fileName);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo: " + ex);;
		}

	}

	//Configurando estilo das celulas da primeira linha
	public static void criandoCelulaTopo(HSSFWorkbook workbook, HSSFRow row, int column, String conteudo) {
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(conteudo);
		HSSFCellStyle estilo = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		estilo.setFont(font);
		estilo.setBorderTop(BorderStyle.MEDIUM);
		estilo.setBorderBottom(BorderStyle.MEDIUM);
		estilo.setBorderLeft(BorderStyle.MEDIUM);
		estilo.setBorderRight(BorderStyle.MEDIUM);
		cell.setCellStyle(estilo);
	}

	//Configurando estilo das celulas das linhas do meio tipo String
	public static void criandoCelulaStr(HSSFWorkbook workbook, HSSFRow row, int column, String conteudo) {
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(conteudo);
		HSSFCellStyle estilo = workbook.createCellStyle();

		estilo.setBorderRight(BorderStyle.THIN);
		if (column == 0) {
			estilo.setBorderLeft(BorderStyle.MEDIUM);
			estilo.setBorderRight(BorderStyle.THIN);
		}
		if (column == 5) {
			estilo.setBorderLeft(BorderStyle.THIN);
			estilo.setBorderRight(BorderStyle.MEDIUM);
		}
		cell.setCellStyle(estilo);
	}
	
	//Configurando estilo das celulas das linhas do meio tipo int
	public static void criandoCelulaInt(HSSFWorkbook workbook, HSSFRow row, int column, int conteudo) {
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(conteudo);
		HSSFCellStyle estilo = workbook.createCellStyle();

		estilo.setBorderRight(BorderStyle.THIN);
		if (column == 0) {
			estilo.setBorderLeft(BorderStyle.MEDIUM);
			estilo.setBorderRight(BorderStyle.THIN);
		}
		if (column == 5) {
			estilo.setBorderLeft(BorderStyle.THIN);
			estilo.setBorderRight(BorderStyle.MEDIUM);
		}
		cell.setCellStyle(estilo);
	}
	
	//Configurando estilo das celulas da ultima linha
	public static void criandoCelulaBaixo(HSSFWorkbook workbook, HSSFRow row, int column) {
		HSSFCell cell = row.createCell(column);
		HSSFCellStyle estilo = workbook.createCellStyle();
		estilo.setBorderBottom(BorderStyle.MEDIUM);
		estilo.setBorderLeft(BorderStyle.THIN);
		estilo.setBorderRight(BorderStyle.THIN);
		if (column == 0) {
			estilo.setBorderLeft(BorderStyle.MEDIUM);
			estilo.setBorderRight(BorderStyle.THIN);
		}
		if (column == 5) {
			estilo.setBorderLeft(BorderStyle.THIN);
			estilo.setBorderRight(BorderStyle.MEDIUM);
		}
		cell.setCellStyle(estilo);
	}
}

