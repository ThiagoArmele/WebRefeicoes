package webrefeicoes.dao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import webrefeicoes.model.Autorizacao;
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
                // Create the SessionFactory from standard (hibernate.cfg.xml)
                // config file.
                AnnotationConfiguration ac = new AnnotationConfiguration();
                ac.addAnnotatedClass(Autorizacao.class);
                ac.addAnnotatedClass(Funcionario.class);
                sessionFactory = ac.configure().buildSessionFactory();
                //SchemaExport se = new SchemaExport(ac);
                //se.create(true, true);
                
            } catch (Throwable ex) {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }

            return sessionFactory;

        } else {
            return sessionFactory;
        }

	}
	
	public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
    }

}