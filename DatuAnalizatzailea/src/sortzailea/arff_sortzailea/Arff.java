package sortzailea.arff_sortzailea;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class Arff {

		public void Sortu (String irteera){           
			try {
				datuak.DatuBasea db = new datuak.DatuBasea();
				db.ireki();
							
				//Fitxategia sortu
				FileWriter arff = null;
	            PrintWriter pw = null;
				arff = new FileWriter(irteera+"/LACB.arff");
				pw = new PrintWriter(arff);
				
				//Goiburukoa
				pw.println("% 1. Izenburua: ACB Ligako Datubasea");
				pw.println("%");
				pw.println("% 2. Iturburuak: http://www.acb.com/");
				pw.println("%");
				pw.println("% 3. Egilea: Gorka Elgezabal (UPV-EHU)");
				pw.println("%");
				pw.println("%");
		        pw.println("@relation ACBLiga");
		        pw.println();
		        
		      //Aldagaiak
		        pw.println("@ATTRIBUTE  kodea NUMERIC");
		        pw.println("@ATTRIBUTE  data DATE \"yyyy-MM-dd\"");
		        pw.println("@ATTRIBUTE  ikusle_kop NUMERIC");		        
		        pw.println("@ATTRIBUTE  puntuak NUMERIC");
		        pw.println("@ATTRIBUTE  t2_jaurtiak NUMERIC");
		        pw.println("@ATTRIBUTE  t2_sartuak NUMERIC");
		        pw.println("@ATTRIBUTE  t3_jaurtiak NUMERIC");
		        pw.println("@ATTRIBUTE  t3_sartuak NUMERIC");
		        pw.println("@ATTRIBUTE  t1_jaurtiak NUMERIC");
		        pw.println("@ATTRIBUTE  t1_sartuak NUMERIC");
		        pw.println("@ATTRIBUTE  reb_def NUMERIC");
		        pw.println("@ATTRIBUTE  reb_atak NUMERIC");
		        pw.println("@ATTRIBUTE  asistentziak NUMERIC");
		        pw.println("@ATTRIBUTE  galdutakoak NUMERIC");
		        pw.println("@ATTRIBUTE  berreskuratutakoak NUMERIC");
		        pw.println("@ATTRIBUTE  kontraerasoak NUMERIC");
		        pw.println("@ATTRIBUTE  tap_ald NUMERIC");
		        pw.println("@ATTRIBUTE  tap_kont NUMERIC");
		        pw.println("@ATTRIBUTE  mateak NUMERIC");
		        pw.println("@ATTRIBUTE  fp_ald NUMERIC");
		        pw.println("@ATTRIBUTE  fp_kont NUMERIC");
		        pw.println("@ATTRIBUTE  balorazioa NUMERIC");

		        
		        List taldeak = db.lortuTaldeak();		        
		        String talde_aldagaia = "@ATTRIBUTE  taldea {";
		        Iterator it = taldeak.iterator();
		        while (it.hasNext()){
		        	Object taldea = it.next();
		        	talde_aldagaia+=taldea.toString().replace(" ", "_");//wekak atributuetan zuriuneak banaketa moduan hartzen dituenez, ezabatu egin behar dira
		        	if(it.hasNext()){
		        		talde_aldagaia+=",";
		        	}		        	
		        }
		        talde_aldagaia+="}";
		        pw.println(talde_aldagaia);
		        pw.println("@ATTRIBUTE  klasea {irabazi,galdu}");
		        
		        //Datuak
		        pw.println("@data");
		      List partidak =  db.partidenDatuak();
		      it = partidak.iterator();
		      while (it.hasNext()){
		    	 Object[] errenkada = (Object[]) it.next();
		    	 
		    	 idatzi(pw,errenkada[0]);//kodea
		    	 idatzi(pw,errenkada[1]);//data
		    	 idatzi(pw,errenkada[2]);//ikusle kopurua
		    	 idatzi(pw,errenkada[3]);//puntuak
		    	 idatzi(pw,errenkada[4]);//t2 jaurtiak
		    	 idatzi(pw,errenkada[5]);//t2 sartuak
		    	 idatzi(pw,errenkada[6]);//t3 jaurtiak
		    	 idatzi(pw,errenkada[7]);//t3 sartuak
		    	 idatzi(pw,errenkada[8]);//t1 jaurtiak
		    	 idatzi(pw,errenkada[9]);// t1 sartuak
		    	 idatzi(pw,errenkada[10]);//erreboteak defentsan
		    	 idatzi(pw,errenkada[11]);// erreboteak erasoan
		    	 idatzi(pw,errenkada[12]);// asistentziak
		    	 idatzi(pw,errenkada[13]);//galdutakoak
		    	 idatzi(pw,errenkada[14]);//berreskuratutakoak
		    	 idatzi(pw,errenkada[15]);//kontraerasoak
		    	 idatzi(pw,errenkada[16]);//tapoiak alde
		    	 idatzi(pw,errenkada[17]);//tapoiak kontra
		    	 idatzi(pw,errenkada[18]);// mateak
		    	 idatzi(pw,errenkada[19]);//falta pertsonalak alde
		    	 idatzi(pw,errenkada[20]);//falta pertsonalak kontra
		    	 idatzi(pw,errenkada[21]);//balorazioa
		    	 
		    	 //Zuriuneak kentzen dira eta letra guztiak letra larrietan idazten dira
		    	 String taldea = errenkada[22].toString().replace(" ", "_").toUpperCase();
		    	 idatzi(pw,taldea);

		    	 Object[] aurkari_errenkada = db.aurkaria(errenkada[0].toString(), errenkada[22].toString());
		    	 if(Integer.parseInt(errenkada[3].toString())>Integer.parseInt(aurkari_errenkada[2].toString())){
		    		 pw.println("irabazi");
		    	 }
		    	 else{
		    		 pw.println("galdu");
		    	 }
		    	 
		      }
		      pw.close();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		public void idatzi(PrintWriter pw, Object elementua){
			if (elementua == null){
				
				pw.print("?,");
			}
			else{
				pw.print(elementua+",");

			}
		}
}
