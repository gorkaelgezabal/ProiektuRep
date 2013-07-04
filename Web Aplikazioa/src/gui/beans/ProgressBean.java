package gui.beans;


import java.io.IOException;
import java.io.Serializable;  

import javax.faces.application.FacesMessage;  
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext; 

import parseatzailea.Parser;
import html_erauzlea.Erauzlea;

public class ProgressBean implements Serializable {  

	private  Integer progress;  
	private  Integer hasiera_denboraldia;
	private  Integer hasiera_partidua; 
	private String html;

	public ProgressBean(){
		this.hasiera_denboraldia = 35;
		this.hasiera_partidua = 1;
	}	
	
//	Aurrerapenaren eskaera Ajax bitartez egiten denez, uneko eskaera zein den kontrolatu behar da, prezesua burutu ondoren "bean"-aren egoera eguneratu eta erantzuna bidaltzen da
	public Integer getProgress() {  
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String db_helbidea = context.getRealPath("LACB");
		System.out.println("denboraldia"+hasiera_denboraldia);
		System.out.println("partida"+hasiera_partidua);
		System.out.println(db_helbidea);

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
						hasiera_partidua = j+1;
					}
					
					//Ez du onartzen zehaztasun maila handiagorik
					progress = (i-34)*4;

//					if(hasiera_partidua>306){
					if(hasiera_partidua>2){

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
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String db_helbidea = context.getRealPath("LACB");
		Parser.parseatu(db_helbidea);   

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Parseaketa burututa.", "Datuak ongi gorde dira"));
	}   


	public void cancel() {  
		progress = null;  
	}  
}  