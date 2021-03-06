package parseatzailea;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Parser {
	
	public static void proba(){
		System.out.println("Proba");
	}
	public static List<datuak.Partida> parseatu(String helbidea){
		
		try{
//			ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
//			String helbidea =context.getRealPath("LACB");
			boolean baliokoa;
			List<datuak.Partida> partidak = new ArrayList<datuak.Partida>();
			List<datuak.Totala> aurkariak = new ArrayList<datuak.Totala>();
			datuak.DatuBasea db = new datuak.DatuBasea();
			
			db.ireki();
			db.garbitu();
			File dir = new File(helbidea);
			String[] fitxategiak = dir.list();
			for(int i=0;i<fitxategiak.length;i++)
			{
				baliokoa = true;
				Document dokumentua = lortuDokumentua(helbidea,fitxategiak,i);
				Elements boxscorea = lortuBoxScorea(dokumentua);
				Elements titulua = lortuPartidaTitulua(dokumentua);
				boolean formatuBerria = isNew(dokumentua);
				Elements errenkadak = boxscorea.get(1).select("tr");
				
				//Baliozko boxscorea den konprobatzen da, horrela ez bada baztertzeko. Horretarako totalaren gelaxkak informazioa duen konprobatzen da
				for(Element tr : errenkadak){
					if(tr.select("td").first().text().equals("Total")){
						if(!tr.select("td").get(1).text().contains(":")){						
							baliokoa = false;
							System.out.println("Ez baliozkoa:"+tr.select("td").get(1).text());
						}
					}
				}
					/* Partiduaren dauten gelaxka lortzen da*/
				Element partida_datuak = boxscorea.select("[class=estnegro]").first().select("td").first();	
				Date partida_data = lortuPartidaData(partida_datuak);
				Integer pertsona_kopurua = lortuPartidaPertsonak(partida_datuak);

				if(baliokoa){
					
					datuak.Partida partida = new datuak.Partida();
					partida.setPartidaKodea(Integer.parseInt(fitxategiak[i].substring(4, 9)));
					partida.setData(partida_data);
					partida.setPertsonaKop(pertsona_kopurua);
					Element talde_datuak = boxscorea.get(1);
					aurkariak = lortuAurkarienDatuak(talde_datuak, titulua, partida.getPartidaKodea(),formatuBerria);					
					
					aurkariak.get(0).setPartida(partida);
					aurkariak.get(1).setPartida(partida);
					partida.getTotalas().add(aurkariak.get(0));
					partida.getTotalas().add(aurkariak.get(1));
					
					
					db.gorde(partida);
					db.gorde(aurkariak.get(0));
					db.gorde(aurkariak.get(1));
					partidak.add(partida);
						
					System.out.println("partida gehitua");
						
				}					
			}
			db.itxi();
		return partidak;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	//Boxscorea lortzen da, existitzen diren formatuak ikuskatuz
	private static Elements lortuBoxScorea(Document doc) throws IOException{
		
		Elements taulak = doc.select("[class=estadisticasnew]");
		
		if(taulak.size()==0){
			taulak = doc.select("[class=estadisticas]");				
		}
		
		return taulak;
	}
	
	//Uneko dokumentuaren boxscorearen formatua zein den ikusten da
	private static boolean isNew (Document doc) {
		Elements taula_new = doc.select("[class=estadisticasnew]");
		if(taula_new.size()==0){
			return false;
		}
		else{
			return true;
		}
	}
	
	//Partidaren tituloa lortzen da, boxscoreen formatu bietarako
	private static Elements lortuPartidaTitulua(Document doc) throws IOException{
		
		Elements taulak = doc.select("[class=titulopartido]");
		if(taulak.size()==0){
			taulak = doc.select("[class=titulopartidonew]");				
		}
		return taulak;
	}
	
	//Adierazitako HTML dokumentuak bilatu eta itzultzen da adirazitako denboraldi eta partidarako, ezarritako helbidetik.
	private static Document lortuDokumentua(String helbidea, String[] fitxategiak, Integer zenb) throws IOException{
		File html = new File(helbidea+"/"+fitxategiak[zenb]);
		System.out.println(helbidea+"/"+fitxategiak[zenb]+" parseatzen");
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		
		return doc;
	}
	
//  Partidaren data eta ordu lortzen dira eta formatua ematen zaie
	private static Date lortuPartidaData(Element partida_datuak){
					
		try {
				
			String datuak =partida_datuak.text();			
			String[] partida_info = datuak.replace("|", ",").split(",");
			SimpleDateFormat data_formatua = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date partida_data = null;
			
			Pattern data_espresioa = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
			Pattern ordu_espresioa = Pattern.compile("[0-9][0-9]:[0-9][0-9]");

			Matcher matcher_data = data_espresioa.matcher(partida_info[1]);
			Matcher matcher_ordua = ordu_espresioa.matcher(partida_info[2]);

			if(matcher_data.find()){
				if(matcher_ordua.find()){
					partida_data = data_formatua.parse(partida_info[1].trim()+" "+partida_info[2].trim());				
				}else{
					partida_data = data_formatua.parse(partida_info[1].trim()+" 00:00");
				}
				return partida_data;
				
			}
			else{
				return null;
			}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}					
	}
		
//  partidako ikusle kopurua lortzen da
	private static Integer lortuPartidaPertsonak(Element partida_datuak){
			
		String datuak =partida_datuak.text();			
		String[] partida_info = datuak.replace("|", ",").split(",");
			
		if(partida_info[4].matches(".*:[0-9][0-9]*")){
			String ikusle_kop = partida_info[4].split(":")[1];
			return Integer.parseInt(ikusle_kop);
		}
		else{
			return null;
		}
	}
		
	private static List<datuak.Totala> lortuAurkarienDatuak (Element talde_datuak, Elements titulua, Integer partida_kodea, boolean formatuBerria){
		int kont = 1;
		Elements errenkadak = talde_datuak.select("tr");
		datuak.Totala talde1 = null;
		datuak.Totala talde2 = null;
		for(Element tr : errenkadak){
			if(tr.select("td").first().text().equals("Total")){//baliozkoa den konprobatzen da
				if(kont == 1){//lehen taldean kokatzen gara
					Elements talde1_datuak = tr.select("td");						
					talde1 = sortuTaldea(talde1_datuak,formatuBerria);
					String izena = titulua.select("td").get(0).text();

					//Taldeen izenetan diren ikur bereziak kentzen dira, tildeak esaterako
					String izen_norm = Normalizatu(izena);
					datuak.TotalaId identifikadorea = new datuak.TotalaId();
					identifikadorea.setTaldea(izen_norm.substring(0, izen_norm.length()-2));
					identifikadorea.setPartida(partida_kodea);
					talde1.setId(identifikadorea);
					kont++;			
				}
				else{//bigarren taldean kokatzen gara
					Elements talde2_datuak = tr.select("td");						
					talde2 = sortuTaldea(talde2_datuak,formatuBerria);
					datuak.TotalaId identifikadorea2 = new datuak.TotalaId();
					String izena = titulua.select("td").get(1).text();


					//Taldeen izenetan diren ikur bereziak kentzen dira, tildeak esaterako
					String izen_norm = Normalizatu(izena);
					identifikadorea2.setTaldea(izen_norm.substring(0, izen_norm.length()).trim());
					identifikadorea2.setPartida(partida_kodea);
					talde2.setId(identifikadorea2);					
				}
			}
		}
			
		List<datuak.Totala> aurkariak = new ArrayList<datuak.Totala>();
		aurkariak.add(talde1);
		aurkariak.add(talde2);
			
		return aurkariak;
	}
	
	private static datuak.Totala sortuTaldea(Elements talde_datuak, boolean formatuBerria){
		
		datuak.Totala taldea = new datuak.Totala();
		taldea.setPuntuak(Integer.parseInt(talde_datuak.get(2).text()));
		
		String[] t2 = talde_datuak.get(3).text().split("/");
		taldea.setT2Jaurtiak(Integer.parseInt(t2[1]));
		taldea.setT2Sartuak(Integer.parseInt(t2[0]));
		
		String[] t3 = talde_datuak.get(5).text().split("/");
		taldea.setT3Jaurtiak(Integer.parseInt(t3[1]));
		taldea.setT3Sartuak(Integer.parseInt(t3[0]));
		
		String[] t1 = talde_datuak.get(7).text().split("/");
		taldea.setT1Jaurtiak(Integer.parseInt(t1[1]));
		taldea.setT1Sartuak(Integer.parseInt(t1[0]));
		
		String[] reb = talde_datuak.get(10).text().split("\\+");
		taldea.setRebDef(Integer.parseInt(reb[0]));
		taldea.setRebEras(Integer.parseInt(reb[1]));
		
		taldea.setAsistentziak(Integer.parseInt(talde_datuak.get(11).text()));
		taldea.setBerreskuratutakoak(Integer.parseInt(talde_datuak.get(12).text()));
		taldea.setGaldutakoak(Integer.parseInt(talde_datuak.get(13).text()));
		taldea.setKontraerasoak(Integer.parseInt(talde_datuak.get(14).text()));
		taldea.setTapAld(Integer.parseInt(talde_datuak.get(15).text()));
		taldea.setTapKont(Integer.parseInt(talde_datuak.get(16).text()));
		taldea.setMateak(Integer.parseInt(talde_datuak.get(17).text()));
		taldea.setFpAld(Integer.parseInt(talde_datuak.get(18).text()));
		taldea.setFpKont(Integer.parseInt(talde_datuak.get(19).text()));
		
		//Boxscorearen arabera galaxka kopurua aldatzen da
		if(formatuBerria){
			taldea.setBalorazioa(Integer.parseInt(talde_datuak.get(21).text()));
		}else{
			taldea.setBalorazioa(Integer.parseInt(talde_datuak.get(20).text()));
		}
		
		
		return taldea;
		
	}
	
	public static String Normalizatu(String izena){
		
//		Karaktere bereziak
		String izen_norm = Normalizer.normalize(izena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
		
//		HTML KODEKETAK

		izen_norm = izen_norm.replace("&AACUTE;", "A");//�
		izen_norm = izen_norm.replace("&EACUTE;", "E");//�
		izen_norm = izen_norm.replace("&IACUTE;", "I");//�
		izen_norm = izen_norm.replace("&OACUTE;", "O");//�
		izen_norm = izen_norm.replace("&UACUTE;", "U");//�
		izen_norm = izen_norm.replace("&NTILDE;", "N");//�
		izen_norm = izen_norm.replace("&UUML;", "U");//�
		
		izen_norm = izen_norm.replace("&AGRAVE;", "A");//�
		izen_norm = izen_norm.replace("&CCEDIL;", "C");//�
		izen_norm = izen_norm.replace("&EGRAVE;", "E");//�
		izen_norm = izen_norm.replace("&IUML;", "I");//�
		izen_norm = izen_norm.replace("&OGRAVE;", "E");//�
		
		return izen_norm;

	}
}