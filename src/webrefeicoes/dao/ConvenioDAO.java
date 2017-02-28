package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Convenio;

public class ConvenioDAO {

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

}



