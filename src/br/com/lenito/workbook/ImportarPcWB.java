package br.com.lenito.workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import br.com.lenito.controller.ComputadorController;
import br.com.lenito.entity.Computador;

public class ImportarPcWB {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private DataFormatter df;

	public ImportarPcWB() {

		try {
			workbook = new HSSFWorkbook(new FileInputStream("C:/Users/lenito.gama/Desktop/teste.xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = workbook.getSheetAt(0);

		df = new DataFormatter();

		for (int i = 0; i < 100; i++) {
			Computador c = new Computador();
			row = sheet.getRow(i);
			if (df.formatCellValue(row.getCell(0)).length() == 0 ){
				break;
			} else {
				
				c.setNome(df.formatCellValue(row.getCell(0)));
				c.setIp(df.formatCellValue(row.getCell(1)));
				c.setModelo(df.formatCellValue(row.getCell(2)));
				c.setSetor(df.formatCellValue(row.getCell(3)));
				c.setMarca(df.formatCellValue(row.getCell(4)));
				c.setSerialNumber(df.formatCellValue(row.getCell(5)));
				c.setTipo(df.formatCellValue(row.getCell(6)));
				c.setUsuario(df.formatCellValue(row.getCell(7)));
				new ComputadorController().addComputador(c);
			}
		}

	}
}
