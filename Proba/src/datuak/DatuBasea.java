package datuak;
	// @Repository
	import java.sql.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
	import org.apache.log4j.Level;
	import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
	import org.hibernate.Session;
import datuak.HibernateUtil;
import org.hibernate.Query;

import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

	public class DatuBasea {
	    private final static Logger log = Logger.getLogger(DatuBasea.class);
	    /**
	     * @param args
	     */
	    public static void main(String[] args) {
	        BasicConfigurator.configure();
	        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	        
	    }

//	    Konexioa ireki
	    public void ireki() {
	    	BasicConfigurator.configure();
	        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	        
	    }
	    
//	    Konexioa itxi
	    public void itxi() {
	    	HibernateUtil.getSessionFactory().close();
	    }
	    
//	    Datu basea garbitu
	    public void garbitu(){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
//	    	String sql_totala = 
//	    			"DELETE FROM " +
//	    			"FROM `db_acb`.`totala`;";
//	    	String sql_partida = 
//    			"DELETE FROM " +
//    			"FROM `db_acb`.`partida`;";
	    	
	    	Query q1 = session.createQuery("delete from Totala");
	    	Query q2 = session.createQuery("delete from Partida");
	    	
	    	q1.executeUpdate();
	    	q2.executeUpdate();

	    	session.getTransaction().commit();	    	
	    	log.info("Datu basea garbituta");

	    }
	    
	    
//	    Partida gorde
	    public void gorde(datuak.Partida partida) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        session.save(partida);
	        session.getTransaction().commit();
	        log.info("Txertatuta: "+partida);
	    }
	    
//	    Totala gorde
	    public void gorde(datuak.Totala totala) {
	        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        session.save(totala);
	        session.getTransaction().commit();
	        log.info("Txertatuta: "+totala);
	    }
	    
//	    Talde izenak lortzen dira
	    public List lortuTaldeak(){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	String sql = 
	    			"SELECT taldea " +
	    			"FROM `db_acb`.`totala` GROUP BY taldea;";
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Taldeak lortuta");
	    	return emaitza;
	    }
	    
//	    "talde_id" talde izena eta "denboraldia" denboraldia emanda talde_id taldeak denboraldia denboraldirako datuak itzuliko ditu
	    public List lortuTaldeDatuak(String talde_id, String denboraldia){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	String sql = 
	    		"select * from totala where taldea = '"+talde_id+"' and partida like '"+denboraldia+"___'";
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Taldeak lortuta");
	    	return emaitza;
	    	
	    }
	    
//	    "talde_id" talde izena eta lortu nahi diren emaitzak zein denbora tartekoak diren ematen dira, eta honekin emaitzen lista bat itzultzen da     
	    public List lortuTaldeDatuak(String talde_id, Date hasiera_data, Date bukaera_data){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	String sql = 
	    		"select taldea,partida,puntuak,t2_jaurtiak,t2_sartuak,t3_jaurtiak,t3_sartuak,t1_jaurtiak,t1_sartuak," +
	    		"reb_def,reb_eras,asistentziak,galdutakoak,berreskuratutakoak,kontraerasoak,tap_ald,tap_kont,mateak," +
	    		"fp_ald,fp_kont,balorazioa from partida " +
	    		"inner join totala on partida_kodea = partida  " +
	    		"where data between '"+hasiera_data+"' and '"+bukaera_data+"' and taldea like '"+talde_id+"'";
	    	
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Taldeak lortuta");
	    	return emaitza;
	    }
	    
//	    partida guztien datuak lortzen dira
	    public List<String> partidenDatuak(){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	
	    	String sql = 
	    			"SELECT `partida_kodea`,`data`,`pertsona_kop`,puntuak,t2_jaurtiak,t2_sartuak,t3_jaurtiak," +
	    			"t3_sartuak,t1_jaurtiak,t1_sartuak,reb_def,reb_eras,asistentziak,galdutakoak,berreskuratutakoak," +
	    			"kontraerasoak,tap_ald,tap_kont,mateak,fp_ald,fp_kont,balorazioa,taldea " +
	    			"FROM `db_acb`.`totala` inner join `db_acb`.`partida` on partida_kodea=partida;";
	    	
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Partiden datuak lortuta");
	    	return emaitza;
	    }
	    
//	    "kodea" partida kodea eta "taldea" talde kodea emanda partida horretan talde  honen aurkariaren informazioa itzultzen du
	    public Object[] aurkaria(String kodea, String taldea){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	
	    	String sql = 
	    			"SELECT * " +
	    			"FROM db_acb.totala " +
	    			"WHERE partida="+kodea+" and taldea NOT LIKE '"+taldea+"';";
	    	List emaitza = session.createSQLQuery(sql).list();
	    	session.getTransaction().commit();
	    	log.info("Aurkari kontsulta burututa");
	    	
	    	return (Object[])emaitza.get(0);	    	
	    }
	    
//	    csv fitxategia sortzeko deia exekutatzen du
	    public void sortu_csv_fitxategia (String helbidea){
	    	try{
	    		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    	session.beginTransaction();
		    	String sql = 
		    			"SELECT * INTO OUTFILE '"+helbidea+"table.csv' FIELDS TERMINATED BY ','" +
		    			" OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\n' " +
		    			"FROM partida INNER JOIN totala ON partida_kodea = partida";
		    	
		    	List q = session.createSQLQuery(sql).list();
//		    	System.out.println("###########"+q.size());
		        
		    	session.getTransaction().commit();
		    	log.info("Aurkari kontsulta burututa");
	    	}
	    	catch(Exception ex){
	    		
	    	}
	    }
	    
	    public List<String> lortuDenboraldiak (){
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	    	String sql = "select distinct substring(partida_kodea, 1, 2) + 1955 from partida";
	    	List emaitza = session.createSQLQuery(sql).list();
	    	return emaitza;
	    	
	    }
	    
	}