package gui.beans;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import sortzailea.pdf_sortzailea.*;
import datuak.DatuBasea;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class PdfBean {
	private Date hasiera_data;
	private Date bukaera_data ;
	private String denboraldia;
	private String taldea;
	private List<String> denboraldiak = new ArrayList<String>();
	private List<String> taldeak = new  ArrayList<String>();

	public PdfBean(){
		DatuBasea db = new DatuBasea();
		db.ireki();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			hasiera_data = sdf.parse("19/09/1990");
			bukaera_data = new Date();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> formatoGabe = db.lortuDenboraldiak();

		Iterator it = formatoGabe.iterator();
		while (it.hasNext()){
			String unekoUrtea = it.next().toString().substring(0, 4);
			String hurrengoUrtea = Integer.toString(Integer.parseInt(unekoUrtea)+1);
			String unekoDenboraldia = unekoUrtea+"/"+hurrengoUrtea.substring(hurrengoUrtea.length()-2, hurrengoUrtea.length());
			denboraldiak.add(unekoDenboraldia);

		}

		taldeak = db.lortuTaldeak();
		db.itxi();
	}

	public StreamedContent sortuDenbAgiria(){
		
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		String helbidea =context.getRealPath("/");
		Pdf sortzailea = new Pdf();
		sortzailea.sortuDenboraldikoKontsulta(helbidea, taldea, denboraldia);
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("DenboraldiKontsulta"+taldea+denboraldia+".pdf");
		StreamedContent file = new DefaultStreamedContent(stream, "text/plain", "DenboraldiKontsulta"+taldea+denboraldia+".pdf");
		return file;
	}

	public StreamedContent sortuDataAgiria(){
		
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		String helbidea =context.getRealPath("/");
		System.out.println(helbidea);
		System.out.println(this.hasiera_data);
		System.out.println(this.bukaera_data);

		Pdf sortzailea = new Pdf();
		//java.util.Date java.sql.Date konbertsioa
		java.sql.Date sqlHasieraData = new java.sql.Date(hasiera_data.getTime());
		java.sql.Date sqlBukaeraData = new java.sql.Date(bukaera_data.getTime());

		sortzailea.sortuDataKontsulta(helbidea, taldea, sqlHasieraData, sqlBukaeraData);
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("DataKontsulta"+taldea+".pdf");
		StreamedContent file = new DefaultStreamedContent(stream, "text/plain", "DataKontsulta"+taldea+".pdf");
		return file;

	}

	public Date getHasiera_data() {
		return hasiera_data;
	}
	public List<String> getDenboraldiak() {
		return denboraldiak;
	}

	public void setDenboraldiak(List<String> denboraldiak) {
		this.denboraldiak = denboraldiak;
	}

	public void setHasiera_data(Date hasiera_data) {
		this.hasiera_data = hasiera_data;
	}
	public Date getBukaera_data() {
		return bukaera_data;
	}
	public void setBukaera_data(Date bukaera_data) {
		this.bukaera_data = bukaera_data;
	}
	public String getDenboraldia() {
		return denboraldia;
	}
	public void setDenboraldia(String denboraldia) {
		this.denboraldia = denboraldia;
	}

	public String getTaldea() {
		return taldea;
	}

	public void setTaldea(String taldea) {
		this.taldea = taldea;
	}

	public List<String> getTaldeak() {
		return taldeak;
	}

	public void setTaldeak(List<String> taldeak) {
		this.taldeak = taldeak;
	}



}
