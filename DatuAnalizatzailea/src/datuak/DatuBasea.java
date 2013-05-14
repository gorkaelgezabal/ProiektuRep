package datuak;
	// @Repository
	import java.util.List;

import org.apache.log4j.BasicConfigurator;
	import org.apache.log4j.Level;
	import org.apache.log4j.Logger;
	import org.hibernate.Session;
import datuak.HibernateUtil;

	public class DatuBasea {
	    private final static Logger log = Logger.getLogger(DatuBasea.class);
	    /**
	     * @param args
	     */
	    public static void main(String[] args) {
	        BasicConfigurator.configure();
	        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	        
	    }

	    public void ireki() {
	    	BasicConfigurator.configure();
	        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	        
	    }
	    
	    public void itxi() {
	    	HibernateUtil.getSessionFactory().close();
	    }
	    
	    public void gorde(datuak.Partida partida) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        session.save(partida);
	        session.getTransaction().commit();
	        log.info("Txertatuta: "+partida);
	    }
	    
	    public void gorde(datuak.Totala totala) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        session.save(totala);
	        session.getTransaction().commit();
	        log.info("Txertatuta: "+totala);
	    }
	    
	    //Talde izenak lortzen dira
	    public List lortuTaldeak(){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	String sql = "SELECT taldea FROM `db_acb`.`totala` group by taldea;";
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Kontsulta burutua");
	    	return emaitza;
	    }
	    
	    public List<String> partidenDatuak(){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	String sql = "SELECT `partida_kodea`,`data`,`pertsona_kop`,puntuak,t2_jaurtiak,t2_sartuak,t3_jaurtiak,t3_sartuak,t1_jaurtiak,t1_sartuak,reb_def,reb_eras,asistentziak,galdutakoak,berreskuratutakoak,kontraerasoak,tap_ald,tap_kont,mateak,fp_ald,fp_kont,balorazioa,taldea FROM `db_acb`.`totala` inner join `db_acb`.`partida` on partida_kodea=partida;";
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Kontsulta burutua");
	    	return emaitza;
	    }
	    
	    public Object[] aurkaria(String kodea, String taldea){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	String sql = "select * from db_acb.totala where partida="+kodea+" and taldea not like '"+taldea+"';";
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Kontsulta burutua");
	    	
	    	return (Object[])emaitza.get(0);	    	
	    }
	    
	    
	}