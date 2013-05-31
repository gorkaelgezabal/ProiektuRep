package gui.beans;

public class TabBean {
	
	private boolean rendered;

    // default constructor 
    public TabBean(){
       this.rendered= false;
    }

    public boolean isRendered() {
       return rendered;
    }
    public void setRendered(boolean disable) {
       this.rendered = disable;
    }
    
    public void proba(){
    	System.out.println(this.rendered);
    }

}
