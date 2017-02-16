package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Prato;

public class PratoDAO {

	public void save(Prato prato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(prato);
		t.commit();
	}

	public Prato getPrato(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Prato) session.load(Prato.class, id);
	}

	public List<Prato> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Prato> lista = session.createQuery("from Prato").list();
		t.commit();
		return lista;
	}

	public void remove(Prato prato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(prato);
		t.commit();
	}

	public void update(Prato prato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(prato);
		t.commit();
		
	}

}



