package webrefeicoes.dao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import webrefeicoes.model.Funcionario;
 
/**
* Hibernate Utility class with a convenient method to get Session Factory object.
*
* @author José Alexandre
*/
public class HibernateUtil {
 
	private static SessionFactory sessionFactory;
	 
	private HibernateUtil() {
	}
 
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
		try {
			AnnotationConfiguration ac = new AnnotationConfiguration();
			ac.addAnnotatedClass(Funcionario.class);
			sessionFactory = ac.configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}
}