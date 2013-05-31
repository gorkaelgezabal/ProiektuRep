package gui.beans;


import java.io.IOException;
import java.io.Serializable;  
  
import javax.faces.application.FacesMessage;  
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext; 

import parseatzailea.Parser;
import html_erauzlea.Erauzlea;
  
public class ProgressBean implements Serializable {  
  
    private Integer progress;  
	private static int hasiera_denboraldia = 35;
	private static int hasiera_partidua = 1;
	ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
	private String db_helbidea = context.getRealPath("LACB");
	private String html;
	
    public Integer getProgress() {  

    		System.out.println("denboraldia"+hasiera_denboraldia);
    		System.out.println("partida"+hasiera_partidua);
    		System.out.println("progress"+progress);

    		if(progress == null){
    				
    				progress = 0;
    			}
    			else{
        			if(progress<100){
		    				int i=hasiera_denboraldia;
		    				int j=hasiera_partidua;
		
		        			try {
		        				if(progress!=0){
		        					html = Erauzlea.erauziHTML(i,j);
			    					Erauzlea.gordeHTML(html, db_helbidea, i, j);
		        				}
		//    					progress = ((j+(i-35)*306)/6732)*100;
		    					progress = (i-34)*4;
		//    					System.out.println("progress"+((j+(i-35)*306)/6732)*100);
		    					
		    					hasiera_partidua = j+1;
		    					
		    					if(hasiera_partidua>2){
		
		//    					if(hasiera_partidua>306){
		    						hasiera_denboraldia=i+1;
		    						hasiera_partidua = 1;
		    					}
		    					
		    					
		    					
		    				} catch (IOException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
		    				
		    				if (progress > 100){
		    					progress = 100;
		    				}
        			}		
    			}

    			return progress;

    }  
    
    public void setProgress(Integer progress) {  
        this.progress = progress;  
    }  


	public void onComplete() {  
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datuen eskuraketa burututa", "Parseaketa burutzeko prest, prozesuak minutu batzuk tarda ditzake."));
    }
    
    public void parseatu() {  
        Parser.parseatu();   
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Parseaketa burututa. Dena prest!", "Parseaketa burututa. Dena prest!"));
     }   
    
      
    public void cancel() {  
        progress = null;  
    }  
}  