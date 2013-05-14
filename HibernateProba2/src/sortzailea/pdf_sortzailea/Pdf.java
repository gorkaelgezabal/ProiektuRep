package sortzailea.pdf_sortzailea;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Pdf {

		private Font test_kol = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.WHITE);
		private BaseColor goib_kol  = WebColors.getRGBColor("#000000");
		private BaseColor atze_kol =  WebColors.getRGBColor("#D0D0D0");
		
		public void sortuDenboraldikoKontsulta (String irteera, String talde_id, String denboraldia){
			
	        try {
//	        	Konexioa datu basera
	        	datuak.DatuBasea db = new datuak.DatuBasea();
	        	db.ireki();
	        	List talde_datuak = db.lortuTaldeDatuak(talde_id, denboraldia);
	        	
//	        	Dokumentua sortu
	        	Document document = new Document();		        
	        	Iterator it = talde_datuak.iterator();
				PdfWriter.getInstance(document, new FileOutputStream(irteera+"DenboraldiKontsulta"+talde_id +denboraldia+".pdf"));			
		        document.open();
		        document.addTitle("Kontsulta");
		        document.addAuthor("Gorka Elgezabal");
		        document.addSubject("Denboraldiko konstulta");

//		        Goiburukoa gehitu
		        gehituGoiburukoa(document);
		        Paragraph paragrafoa = new Paragraph();
		        gehituLerroSaltoa(paragrafoa,1);
		        paragrafoa.add(new Paragraph(talde_id+" taldearen informazioa "+denboraldia+" denboraldirako."));
		        gehituLerroSaltoa(paragrafoa,1);
		        document.add(paragrafoa);
		        paragrafoa.clear();
		        
//		        Datuen taula gehitu
		        int[] totalak = gehituDatuenTaula(it,document, this.test_kol,this.goib_kol);
		        gehituLerroSaltoa(paragrafoa, 3);        
		        paragrafoa.add(new Paragraph("Totalak:"));
		        gehituLerroSaltoa(paragrafoa, 1);
		        document.add(paragrafoa);
		        paragrafoa.clear();
		        
//		        Totalen taula gehitu
		        gehituTotalenTaula(document,totalak,this.test_kol, this.goib_kol);
		        gehituLerroSaltoa(paragrafoa, 2);
		        document.add(paragrafoa);
		        paragrafoa.clear();
		        
//		        Legenda gehitu
		        gehituLegenda(document);
		        document.close();
		        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
		
		
		public void sortuDataKontsulta(String irteera, String talde_id, Date hasiera_data, Date bukaera_data){
	        try {
//	        	Konexioa datu basera
	        	datuak.DatuBasea db = new datuak.DatuBasea();
	        	db.ireki();
	        	List talde_datuak = db.lortuTaldeDatuak(talde_id, hasiera_data, bukaera_data);
	        	
//	        	Dokumentua sortu
	        	Document document = new Document();		        
	        	Iterator it = talde_datuak.iterator();
				PdfWriter.getInstance(document, new FileOutputStream(irteera+"DataKontsulta"+talde_id+hasiera_data+bukaera_data+".pdf"));			
		        document.open();
		        document.addTitle("Kontsulta");
		        document.addAuthor("Gorka Elgezabal");
		        document.addSubject("Denboraldiko konstulta");

//		        Goiburukoa gehitu
		        gehituGoiburukoa(document);
		        Paragraph paragrafoa = new Paragraph();
		        gehituLerroSaltoa(paragrafoa,1);
		        paragrafoa.add(new Paragraph(talde_id+" taldearen informazioa "+hasiera_data+"/"+bukaera_data+" denbora tarterako."));
		        gehituLerroSaltoa(paragrafoa,1);
		        document.add(paragrafoa);
		        paragrafoa.clear();
		        
//		        Datuen taula gehitu
		        int[] totalak = gehituDatuenTaula(it,document, this.test_kol,this.goib_kol);
		        gehituLerroSaltoa(paragrafoa, 3);        
		        paragrafoa.add(new Paragraph("Totalak:"));
		        gehituLerroSaltoa(paragrafoa, 1);
		        document.add(paragrafoa);
		        paragrafoa.clear();
		        
//		        Totalen taula gehitu
		        gehituTotalenTaula(document,totalak,this.test_kol, this.goib_kol);
		        gehituLerroSaltoa(paragrafoa, 2);
		        document.add(paragrafoa);
		        paragrafoa.clear();
		        
//		        Legenda gehitu
		        gehituLegenda(document);
		        document.close();
		        
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
		
		
		private static void gehituLerroSaltoa(Paragraph paragrafoa, int zenbat) {

		    	for (int i = 0; i < zenbat; i++) {
				      paragrafoa.add(new Paragraph(" "));
				    }
		  }
		
//		Dokumentuaren goiburua sortzen du UPV-EHU et aInformatikako Fakultatearen logoekin
		private static void gehituGoiburukoa(Document document){
			
			try {
				
				String url_ehu = "http://www.ehu.es/astronomasbilbao/images/upv.jpg";
		        String url_fak ="http://www.ehu.es/jmuguerza/moise/EmpresasLogos/fiss.jpg";
		        Image ehu = Image.getInstance(new URL(url_ehu));
				Image fak =Image.getInstance(url_fak);
			        
		        ehu.scalePercent(4);
		        fak.scalePercent(90);

		        PdfPTable header = new PdfPTable(new float[] {1f,2f,1f});
		        header.setWidthPercentage(100);
		        PdfPCell ehu_cell = new PdfPCell(ehu);
		        PdfPCell fak_cell = new PdfPCell(fak);
		        PdfPCell tarte_cell = new PdfPCell();
		        ehu_cell.setBorder(Rectangle.NO_BORDER);
		        fak_cell.setBorder(Rectangle.NO_BORDER);
		        tarte_cell.setBorder(Rectangle.NO_BORDER);
		        header.addCell(ehu_cell);
		        header.addCell(tarte_cell);
		        header.addCell(fak_cell);
		        document.add(header);
		        
			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		}
		
//        19 zutabedun taula bat sortzen da, zutabe bakoitzari zabalera jakin bat emanda. Datuak it Iterator aldagaitik lortuko dira, eta adierazitako estiloarekin sortuko da taula
		private static int[] gehituDatuenTaula(Iterator it, Document document, Font test_kol, BaseColor goib_kol){

	        PdfPTable table = new PdfPTable(new float[] {2f,2f,2f,2f,2f,2f,2f,3f,3f,2f,2f,2f,2f,3f,3f,3f,2f,3f,3f,});
	        table.setWidthPercentage(100); 
	        
	        PdfPCell c1 = new PdfPCell(new Phrase("P",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);

	         c1 = new PdfPCell(new Phrase("T2 J",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);

	         c1 = new PdfPCell(new Phrase("T2 S",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("T3 J",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("T3 S",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("T1 J",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("T1 S",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("REB D",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("REB E",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("A",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("G",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("B",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("KE",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("TAP A",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("TAP K",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("TAP M",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("FP A",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("TAP K",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	         c1 = new PdfPCell(new Phrase("BAL",test_kol));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table.addCell(c1);
	        
	        table.setHeaderRows(1);

	        int[] totalak = new int[19];
	        while(it.hasNext()){
	        	
	        	Object[] errenkada = (Object[]) it.next();
	        	table.addCell(errenkada[2].toString());
	        	table.addCell(errenkada[3].toString());
	        	table.addCell(errenkada[4].toString());
	        	table.addCell(errenkada[5].toString());
	        	table.addCell(errenkada[6].toString());
	        	table.addCell(errenkada[7].toString());
	        	table.addCell(errenkada[8].toString());
	        	table.addCell(errenkada[9].toString());
	        	table.addCell(errenkada[10].toString());
	        	table.addCell(errenkada[11].toString());
	        	table.addCell(errenkada[12].toString());
	        	table.addCell(errenkada[13].toString());
	        	table.addCell(errenkada[14].toString());
	        	table.addCell(errenkada[15].toString());
	        	table.addCell(errenkada[16].toString());
	        	table.addCell(errenkada[17].toString());
	        	table.addCell(errenkada[18].toString());
	        	table.addCell(errenkada[19].toString());
	        	table.addCell(errenkada[20].toString());
	        	
	        	totalak[0]+=Integer.parseInt(errenkada[2].toString());
	        	totalak[1]+=Integer.parseInt(errenkada[3].toString());
	        	totalak[2]+=Integer.parseInt(errenkada[4].toString());
	        	totalak[3]+=Integer.parseInt(errenkada[5].toString());
	        	totalak[4]+=Integer.parseInt(errenkada[6].toString());
	        	totalak[5]+=Integer.parseInt(errenkada[7].toString());
	        	totalak[6]+=Integer.parseInt(errenkada[8].toString());
	        	totalak[7]+=Integer.parseInt(errenkada[9].toString());
	        	totalak[8]+=Integer.parseInt(errenkada[10].toString());
	        	totalak[9]+=Integer.parseInt(errenkada[11].toString());
	        	totalak[10]+=Integer.parseInt(errenkada[12].toString());
	        	totalak[11]+=Integer.parseInt(errenkada[13].toString());
	        	totalak[12]+=Integer.parseInt(errenkada[14].toString());
	        	totalak[13]+=Integer.parseInt(errenkada[15].toString());
	        	totalak[14]+=Integer.parseInt(errenkada[16].toString());
	        	totalak[15]+=Integer.parseInt(errenkada[17].toString());
	        	totalak[16]+=Integer.parseInt(errenkada[18].toString());
	        	totalak[17]+=Integer.parseInt(errenkada[19].toString());
	        	totalak[18]+=Integer.parseInt(errenkada[20].toString());
	        	
	        }
	        try {
				document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return totalak;
		}
		
//		Totalen taula bat sortzen du "totalak" aldagaitik hartutako datuekin "dokument" dokumentuan, adierazitako estiloarekin
		private static void gehituTotalenTaula (Document document, int[] totalak, Font test_kol, BaseColor goib_kol){
			
			PdfPTable table_main = new PdfPTable(2);
			table_main.setKeepTogether(true);
			PdfPTable table2 = new PdfPTable(new float[] {1f,2f});
	        table2.setWidthPercentage(40);
	        
	        PdfPCell c1 = new PdfPCell(new Phrase("P",test_kol));
	        PdfPCell c2 = new PdfPCell(new Phrase(Integer.toString(totalak[0])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("T2 J",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[1])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("T2 S",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[2])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("T3 J",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[3])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("T3 S",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[4])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("T1 J",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[5])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("T1 S",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[6])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("REB D",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[7])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("REB E",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[8])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("A",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[9])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table2.addCell(c1);
	        table2.addCell(c2);
	        
	        table2.setHeaderRows(1);
	        
	        PdfPTable table3 = new PdfPTable(new float[] {1f,2f});
	        table3.setWidthPercentage(40);
	        
	        c1 = new PdfPCell(new Phrase("G",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[10])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("B",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[11])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("KE",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[12])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("TAP A",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[13])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("TAP K",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[14])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("TAP M",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[15])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("FP A",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[16])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("TAP K",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[17])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        c1 = new PdfPCell(new Phrase("BAL",test_kol));
	        c2 = new PdfPCell(new Phrase(Integer.toString(totalak[18])));
	        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(goib_kol);
	        table3.addCell(c1);
	        table3.addCell(c2);
	        
	        table3.setHeaderRows(1);
	                
	        try {
	        	table2.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	table3.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        	table_main.addCell(table2);
	        	table_main.addCell(table3);
				document.add(table_main);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		private static void gehituLegenda(Document document){
			
			PdfPTable table_legenda = new PdfPTable(new float[] {1f,2f});
			table_legenda.setKeepTogether(true);
        
	        PdfPCell c1 = new PdfPCell(new Phrase("Legenda:"));
	        c1.setBorder(Rectangle.NO_BORDER);
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase(""));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("P:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Puntuak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("T2 J:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Bi puntuko jaurtiketa eginak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("T2 S:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Bi puntuko jaurtiketa sartuak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("T3 J:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Hiru puntuko jaurtiketa eginak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("T3 S:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Bi puntuko jaurtiketa sartuak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("T1 J:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Puntuko bateko jaurtiketa eginak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("T1 S:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Puntuko bateko jaurtiketa sartuak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("REB D:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Erreboteak defentsan"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("REB E:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Erreboteak erasoan"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("A:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Asistentziak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("G:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Galdutakoak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("B:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Berreskuratutakoak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("KE:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Kontraerasoak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("TAP A:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Tapoiak alde"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("TAP K:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Tapoiak kontran"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("M:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Mateak"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("FP A:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Falta pertsonalak alde"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("FP K:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Falta pertsonalak kontran"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("BAL:"));
	        table_legenda.addCell(c1);
	        c1.setPhrase(new Phrase("Balorazioa"));
	        table_legenda.addCell(c1);
			table_legenda.setKeepTogether(true);
			
			Paragraph paragrafoa = new Paragraph("");
			
	        PdfPTable table_oharra = new PdfPTable(1);
	        table_oharra.setWidthPercentage(100);
	        table_oharra.addCell("*OHARRA: \n Balorazioaren kalkulurako ondorengo formula jarraitzen da: (P+A+REB D+REB E+B+TAP A+FP A+T1 S+T2 S+T3 S)-(G+TAP K+FP K+T1 J+T2 J+T3 J)");

	        try {
				document.add(table_legenda);
				gehituLerroSaltoa(paragrafoa, 2);
				document.add(paragrafoa);
				document.add(table_oharra);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        paragrafoa.clear();
		}
}
