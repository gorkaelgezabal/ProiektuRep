package html_erauzlea;

import java.io.IOException;
import java.net.SocketException;

//Saiakerak eta funtzionamendua konprobatzeko sortutako klasea
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
	    			String db_helbidea = "";//Irteera helbidea
	    			Erauzlea.ateraEtaGordeHTML(db_helbidea);	    			
	    	}catch (SocketException ex){
	    		continue;
	    	}
	    	break;
	    }
	}

}
