package sortzailea.csv_sortzailea;

public class Csv {
			
		public void Sortu (String irteera){
			datuak.DatuBasea db = new datuak.DatuBasea();
			db.ireki();
			db.sortu_csv_fitxategia(irteera);
			db.itxi();
		}
}
