package gui.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sortzailea.csv_sortzailea.*;

public class CsvBean {

	public StreamedContent sortu(){
		String helbidea = System.getProperty("java.io.tmpdir");
		
//		MySQL-ek ez ditu onartzen patherako Windows-ek erabiltzen duen \. Unix sistema baten kasuan bere horretan geratuko litzateke
		helbidea = helbidea.replace("\\", "/");
		
//		Sistema eragilearen arabera, helbidearen amaieran  '/' ager daiteke edo ez.
		char slash = helbidea.charAt(helbidea.length()-1);
		if (slash != '/'){
			helbidea = helbidea+"/";
		}
		
		Csv sortzailea = new Csv();
		sortzailea.Sortu(helbidea);
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
