package webrefeicoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Cliente;
import webrefeicoes.model.Convenio;
import webrefeicoes.model.ConvenioView;

public class ConvenioDAO {

	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	public void save(Convenio convenio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(convenio);
		t.commit();
	}

	public Convenio getConvenio(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Convenio) session.load(Convenio.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Convenio> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Convenio> lista = session.createQuery("from Convenio").list();
		t.commit();
		return lista;
	}

	public void remove(Convenio convenio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(convenio);
		t.commit();
	}

	public void update(Convenio convenio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(convenio);
		t.commit();
	}

	public Convenio getConvenio(int idCliente) {
		try {
			Convenio convenio = (Convenio) em
               .createQuery(
                           "SELECT c from Convenio c where c.idCliente = :idCliente")
               .setParameter("idCliente", idCliente).getSingleResult();
            return convenio;
      } catch (NoResultException e) {
            return null;
      }
		
	}
}



