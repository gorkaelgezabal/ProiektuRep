package gui.beans;

import java.io.InputStream;
import javax.servlet.ServletContext; 
import org.primefaces.model.DefaultStreamedContent;
import sortzailea.arff_sortzailea.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

public class ArffBean {

	public StreamedContent sortu(){
		ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
		String helbidea =context.getRealPath("LACB.arff");
		Arff sortzailea = new Arff();
		sortzailea.Sortu(helbidea);
		InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("LACB.arff");
		StreamedContent file = new DefaultStreamedContent(stream, "text/plain", "LACB.arff");
		return file;
	}
}
