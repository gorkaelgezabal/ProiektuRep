package gui.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sortzailea.csv_sortzailea.*;

public class CsvBean {
	
	public StreamedContent sortu(){
		String helbidea ="c:/Windows/Temp/";
		Csv sortzailea = new Csv();
		sortzailea.Sortu(helbidea);
		System.out.println("#########");
		try {
			InputStream stream = new FileInputStream(helbidea+"table.csv");
			StreamedContent file = new DefaultStreamedContent(stream, "text/csv", "LACB.csv");
			return file;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}

}
