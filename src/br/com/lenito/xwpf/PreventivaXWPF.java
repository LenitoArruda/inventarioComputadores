package br.com.lenito.xwpf;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

import br.com.lenito.utilitarios.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class PreventivaXWPF {

	private FileOutputStream fileOut = null;
	private XWPFDocument documento = null;
	private XWPFParagraph paragrafo1, paragrafo2, paragrafo, paragrafoAss;
	private XWPFRun run1, run2, headerRun, footerRun, AssRun;
	private XWPFHeaderFooterPolicy policy = null;
	private XWPFHeader header = null;
	private URL url = null;
	private File file = null;
	private FileInputStream is = null;
	private XWPFFooter footer = null;
	private Data d;
	private String data;

	ClassLoader loader = getClass().getClassLoader();

	public void gerarRelatorio(String empresa) {

		try {

			// Criando o documento
			documento = new XWPFDocument();
			fileOut = new FileOutputStream(new File("C:\\Users\\lenito.gama\\Desktop\\RelatorioDoado.docx"));
			CTSectPr sectPr = documento.getDocument().getBody().addNewSectPr();
			policy = new XWPFHeaderFooterPolicy(documento, sectPr);

			// Criando o cabeçalho
			header = policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
			paragrafo = header.getParagraphArray(0);
			paragrafo = header.createParagraph();
			paragrafo.setAlignment(ParagraphAlignment.CENTER);
			headerRun = paragrafo.createRun();
			url = loader.getResource("img/IntercamCabecalho.jpg");
			file = new File(url.getFile());
			is = new FileInputStream(file);
			headerRun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "IntercamCabecalho.jpg", Units.toEMU(425),Units.toEMU(80));
			
			// Criando título
			paragrafo1 = documento.createParagraph();
			paragrafo1.setAlignment(ParagraphAlignment.CENTER);
			run1 = paragrafo1.createRun();
			run1.setBold(true);
			run1.setFontSize(14);
			run1.addBreak();
			run1.addBreak();
			run1.setText("Relatório de alguma coisa");
			run1.addTab();
			run1.addBreak();
			run1.addBreak();
			
			// Criando Texto
			paragrafo2 = documento.createParagraph();
			run2 = paragrafo2.createRun();
			run2.addTab();
			run2.setText("Aqui vai o que vai ser escrito no relatório.");
			run2.addBreak();
			run2.addBreak();
			run2.addBreak();
			
			
			// Criando assinatura
			d = new Data();
			d.lerData();
			data = "São Paulo, " + d.getDia() + " de " + d.getMes() + " de " + d.getAno();
			paragrafoAss = documento.createParagraph();
			AssRun = paragrafoAss.createRun();
			AssRun.setText(data);
			AssRun.addBreak();
			AssRun.addBreak();
			AssRun.addBreak();
			AssRun.setText("__________________________________");
			AssRun.addBreak();
			AssRun.addTab();
			AssRun.setText("Intercam Corretora de Câmbio");
			
			

			// Criando o rodapé
			url = loader.getResource("img/IntercamRodape.jpg");
			file = new File(url.getFile());
			is = new FileInputStream(file);
			footer = policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
			paragrafo = footer.getParagraphArray(0);
			paragrafo = footer.createParagraph();
			footerRun = paragrafo.createRun();
			footerRun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "IntercamCabecalho.jpg", Units.toEMU(425),Units.toEMU(80));

			documento.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

	}

}
