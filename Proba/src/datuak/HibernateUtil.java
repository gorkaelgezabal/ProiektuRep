
package datuak;
import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
private static final SessionFactory sessionFactory;
static {
try {
	sessionFactory = new Configuration().configure(new File("C:/iso2/workspace/Proba/src/hibernate.cfg.xml")).buildSessionFactory();
} catch (Throwable ex) {
throw new ExceptionInInitializerError(ex);
}
}
public static SessionFactory getSessionFactory() {
	return sessionFactory;
}
}