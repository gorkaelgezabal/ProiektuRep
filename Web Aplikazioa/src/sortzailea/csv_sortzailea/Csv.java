package sortzailea.csv_sortzailea;

import java.io.File;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Csv {

	public void Sortu (String helbidea){

		File fileTemp = new File(helbidea+"table.csv");
		if (fileTemp.exists()){
			fileTemp.delete();
			System.out.println("Aurreko bertsioa ezabatuta");
		}
		datuak.DatuBasea db = new datuak.DatuBasea();
		db.ireki();
		db.sortu_csv_fitxategia(helbidea);
		db.itxi();
	}
}
