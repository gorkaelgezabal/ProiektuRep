package html_erauzlea;

import java.io.IOException;
import java.net.SocketException;

public class Nagusia {
	
	public static int hasiera_denboraldia = 35;
	public static int hasiera_partidua = 1;

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		for(int saiakerak=0;;saiakerak++)
	    {
	    	try
	    	{
	    			String db_helbidea = "C:/Users/Bego/Documents/Gorka/Proiektua";
//	    			Erauzlea.ateraEtaGordeHTML(db_helbidea);
	    			Erauzlea.ateraEtaGordeHTML();
	    			
	    	}catch (SocketException ex){
	    		continue;
	    	}
	    	break;
	    }
	}

}
