package html_erauzlea;

import java.util.List;
import java.io.IOException;
import sortzailea.arff_sortzailea.Arff;

public class Nagusia {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		String helbidea = "C:/Users/Bego/Documents/Gorka/Proiektua";
		String irteera = "C:/Users/Bego/Documents/Gorka/";
		
		
		//Parser parser = new Parser();
		//List<datuak.Partida> partida_lista = Parser.parseatu(helbidea);
		
		System.out.println("proba");
		Arff arff = new Arff();
		arff.Sortu(irteera);
		System.out.println("#######SORTUTA#####");
		
		
		
		
		
		//datu basean gorde
		
		//fitxategia sortu
		
		//String irteera = "C:/Users/Bego/Documents/Gorka";
		
		
		//fitxategira gehitu datuak

	}
	
	
}
