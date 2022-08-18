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

import br.com.lenito.controller.ComputadorController;
import br.com.lenito.entity.Computador;
import br.com.lenito.utilitarios.Data;

public class PreventivaWB {
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow rowHead, row, rowBot, rowTitulo, rowAssi, rowData;
	private FileOutputStream fileOut;
	private List<Computador> listaComputador = new ComputadorController().findComputadores();
	private Data data;

	public PreventivaWB(String fileName) {

		try {

			// Local do Arquivo
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet("Computador");
			
			//Criando o titulo
			rowTitulo = sheet.createRow((short) 0);
			HSSFCell cell1 = rowTitulo.createCell(4);
			HSSFCellStyle estilo = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setBold(true);
			estilo.setFont(font);
			cell1.setCellValue("MANUTENÇÃO PREVENTIVA ANUAL");
			cell1.setCellStyle(estilo);
			
			// Criando as linhas
			rowHead = sheet.createRow((short) 2);
			// Definindo valores da primeira linha
			criandoCelulaTopo(workbook, rowHead, 0, "MARCA");
			criandoCelulaTopo(workbook, rowHead, 1, "MODELO");
			criandoCelulaTopo(workbook, rowHead, 2, "N° SERIE");
			criandoCelulaTopo(workbook, rowHead, 3, "SETOR");
			criandoCelulaTopo(workbook, rowHead, 4, "ANTIVIRUS");
			criandoCelulaTopo(workbook, rowHead, 5, "LIMPEZA");
			criandoCelulaTopo(workbook, rowHead, 6, "ARQUIVOS OBSELETOS");
			criandoCelulaTopo(workbook, rowHead, 7, "ATUALIZAÇÕES");


			// Atribuindo os valores do banco para o arquivo xls
			int j = 3;
			for (int i = 0; i < listaComputador.size(); i++) {
				Computador c = new Computador();
				c = listaComputador.get(i);
				row = sheet.createRow((short) j);
				j++;
				criandoCelulaStr(workbook, row, 0, c.getMarca());
				criandoCelulaStr(workbook, row, 1, c.getModelo());
				criandoCelulaStr(workbook, row, 2, c.getSerialNumber());
				criandoCelulaStr(workbook, row, 3, c.getSetor());
				criandoCelulaStr(workbook, row, 4, "OK");
				criandoCelulaStr(workbook, row, 5, "OK");
				criandoCelulaStr(workbook, row, 6, "OK");
				criandoCelulaStr(workbook, row, 7, "OK");
			}
			// Definindo os valores da ultima linha
			rowBot = sheet.createRow((short) j);
			criandoCelulaBaixo(workbook, rowBot, 0);
			criandoCelulaBaixo(workbook, rowBot, 1);
			criandoCelulaBaixo(workbook, rowBot, 2);
			criandoCelulaBaixo(workbook, rowBot, 3);
			criandoCelulaBaixo(workbook, rowBot, 4);
			criandoCelulaBaixo(workbook, rowBot, 5);
			criandoCelulaBaixo(workbook, rowBot, 6);
			criandoCelulaBaixo(workbook, rowBot, 7);
			
			//Criando Assinaturas
			rowAssi = sheet.createRow((short) j+3);
			criandoCelulaAssinatura(workbook, rowAssi, 1, "Supervisor de Informática");
			criandoCelulaAssinatura(workbook, rowAssi, 2, "");
			criandoCelulaAssinatura(workbook, rowAssi, 5, "Superintendência");
			criandoCelulaAssinatura(workbook, rowAssi, 6, "");
			
			//Criando Data
			rowData = sheet.createRow((short) j + 6);
			HSSFCell cellData = rowData.createCell(1);
			data = new Data();
			data.lerData();
			cellData.setCellValue("Data: " + data.getDia() + "/" + data.retornarNumeroMes() + "/" + data.getAno());
			cellData.setCellStyle(estilo);

			// Ajustando automaticamento o tamanho das colunas
			int quantidadeColunas = sheet.getRow(2).getPhysicalNumberOfCells();
			for (int i = 0; i < quantidadeColunas; i++) {
				sheet.autoSizeColumn(i);
			}

			// Gerando o arquivo xls
			fileOut = new FileOutputStream(fileName + "/ManutencaoPreventiva.xls");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

			JOptionPane.showMessageDialog(null,
					"O arquivo 'ManutencaoPreventiva.xls' foi gerado no diretório : " + fileName);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro ao gerar o arquivo: " + ex);
			;
		}

	}

	// Configurando estilo das celulas da primeira linha
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

	// Configurando estilo das celulas das linhas do meio tipo String
	public static void criandoCelulaStr(HSSFWorkbook workbook, HSSFRow row, int column, String conteudo) {
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(conteudo);
		HSSFCellStyle estilo = workbook.createCellStyle();
		estilo.setBorderRight(BorderStyle.MEDIUM);
		estilo.setBorderBottom(BorderStyle.MEDIUM);
		estilo.setBorderTop(BorderStyle.MEDIUM);
		estilo.setBorderLeft(BorderStyle.MEDIUM);
		cell.setCellStyle(estilo);
	}

	/*
	 * Configurando estilo das celulas das linhas do meio tipo int public static
	 * void criandoCelulaInt(HSSFWorkbook workbook, HSSFRow row, int column, int
	 * conteudo) { HSSFCell cell = row.createCell(column);
	 * cell.setCellValue(conteudo); HSSFCellStyle estilo =
	 * workbook.createCellStyle();
	 * 
	 * estilo.setBorderRight(BorderStyle.THIN); if (column == 0) {
	 * estilo.setBorderLeft(BorderStyle.MEDIUM);
	 * estilo.setBorderRight(BorderStyle.THIN); } if (column == 7) {
	 * estilo.setBorderLeft(BorderStyle.THIN);
	 * estilo.setBorderRight(BorderStyle.MEDIUM); } cell.setCellStyle(estilo); }
	 */

	// Configurando estilo das celulas da ultima linha
	public static void criandoCelulaBaixo(HSSFWorkbook workbook, HSSFRow row, int column) {
		HSSFCell cell = row.createCell(column);
		HSSFCellStyle estilo = workbook.createCellStyle();
		estilo.setBorderRight(BorderStyle.MEDIUM);
		estilo.setBorderBottom(BorderStyle.MEDIUM);
		estilo.setBorderTop(BorderStyle.MEDIUM);
		estilo.setBorderLeft(BorderStyle.MEDIUM);
		cell.setCellStyle(estilo);
	}
	
	public static void criandoCelulaAssinatura(HSSFWorkbook workbook, HSSFRow row, int column, String conteudo) {
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(conteudo);
		HSSFCellStyle estilo = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		estilo.setFont(font);
		estilo.setBorderTop(BorderStyle.MEDIUM);
		estilo.setBorderBottom(BorderStyle.NONE);
		estilo.setBorderLeft(BorderStyle.NONE);
		estilo.setBorderRight(BorderStyle.NONE);
		cell.setCellStyle(estilo);
	}
}
