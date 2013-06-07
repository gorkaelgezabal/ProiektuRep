package parseatzailea;

import java.util.List;
import java.io.IOException;
import sortzailea.arff_sortzailea.Arff;
import sortzailea.csv_sortzailea.Csv;
import sortzailea.pdf_sortzailea.Pdf;

public class Nagusia {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		String helbidea = "C:/Users/Bego/Documents/Gorka/Dropbox/Proiektua/Datuak";
		String irteera_arff = "C:/Users/Bego/Documents/Gorka/";
		String irteera_pdf = "C:/Users/Gorka/";
		String irteera_csv = "c:/Windows/Temp/";
		
/*##########################DATUAK DATU BASETIK ESKURATU ##############################*/
//		Parser parser = new Parser();
//		List<datuak.Partida> partida_lista = Parser.parseatu(helbidea);
		
/*######################### ARFF FITXATEGIA SORTU #####################################*/
//		System.out.println("proba");
//		Arff arff = new Arff();
//		arff.Sortu(irteera_arff);
//		System.out.println("#######ARFF SORTUTA#####");
////		
/*############################CSV FITXATEGIA SORTU ####################################*/
		//Csv csv = new Csv();
		//csv.Sortu(irteera_csv);
		//System.out.println("#######CSV SORTUTA#####");
		
/*############################PDF FITXATEGIA SORTU ####################################*/
//		Pdf pdf = new Pdf();
//		pdf.sortuDenboraldikoKontsulta(irteera_pdf,"CAJA LABORAL","56");
//		
//		java.sql.Date hasiera_data = java.sql.Date.valueOf( "2009-10-11" );
//		java.sql.Date bukaera_data = java.sql.Date.valueOf( "2011-03-09" );
//		
//		pdf.sortuDataKontsulta(irteera_pdf, "CAJA LABORAL", hasiera_data, bukaera_data);
//		System.out.println("#######PDFa SORTUTA#####");
	}
	
	
}
