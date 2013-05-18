package html_erauzlea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Erauzlea {
	
	private static int hasiera_denboraldia = 35;
	private static int hasiera_partidua = 1;
	
//	public static void ateraEtaGordeHTML (String db_helbidea) throws IOException{
	public static void ateraEtaGordeHTML () throws IOException{
		
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		String s=context.getRealPath("LACB"); 
		
		String db_helbidea = s;
		System.out.println(s);
		String html;

//    	for(int i=hasiera_denboraldia;i<57;i++)//35-->1991;;56-->2012
		for(int i=hasiera_denboraldia;i<36;i++)
    	{
    		hasiera_denboraldia=i;
//    		for(int j=hasiera_partidua;j<307;j++)//306--> Liga Regularra 34 jornada * 9 partida jornadako
    		for(int j=hasiera_partidua;j<2;j++)
    		{
    			html = erauziHTML(i,j);
    			gordeHTML(html, db_helbidea, i, j);    		}
    		hasiera_partidua=1;
    	}
    }
	
	
//	ACB ren webgunetik adierazitako denboraldia eta partidaren arabera deskargatzen dira datuak
	public static String erauziHTML(Integer denboraldia, Integer partida) throws IOException{
		
		String url = "http://www.acb.com/stspartido.php?cod_competicion=LACB&cod_edicion="+denboraldia+"&partido="+partida;		
		Connection.Response response = null;
		response = Jsoup.connect(url).header("User-Agent", "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:12.0) Gecko/20100101 Firefox/12.0").timeout(0).execute();
		System.out.println(response.statusCode());
		if(response.statusCode()==200)//Status:Found
		{
			System.out.println("Deskargatzen: "+url+"...");
			Document doc = Jsoup.connect(url).timeout(0).get();
			String kodea = doc.toString();   		
			return kodea;
		}
		else{
			return null;
		}
	}
	
//	"html" aldagaian emandako fitxategia "db_helbidea" helbidean gordetzen da, denboraldi eta partida aldagaien oinarritutako
//	izen batekin formatu honetan: LACBddppp non dd denboraldia den eta ppp partida.
	public static void gordeHTML(String html, String db_helbidea, Integer denboraldia, Integer partida) throws IOException{
		
		String partida_oin = "000";
		FileWriter fitxategia = null;
		String partida_zenb = partida_oin.concat(String.valueOf(partida));
		fitxategia = new FileWriter(db_helbidea+"/LACB"+denboraldia+partida_zenb.substring(partida_zenb.length()-3)+".html");
		BufferedWriter bufferedWriter = new BufferedWriter(fitxategia);
        bufferedWriter.write(html);
        bufferedWriter.close();
        System.out.println("Arrakastaz deskargatua!");
		
	}

}
