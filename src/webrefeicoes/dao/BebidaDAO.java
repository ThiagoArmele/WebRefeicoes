package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Bebida;

public class BebidaDAO {

	public void save(Bebida bebida) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(bebida);
		t.commit();
	}

	public Bebida getBebida(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Bebida) session.load(Bebida.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Bebida> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Bebida> lista = session.createQuery("from Bebida").list();
		t.commit();
		return lista;
	}

	public void remove(Bebida bebida) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(bebida);
		t.commit();
	}

	public void update(Bebida bebida) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(bebida);
		t.commit();
		
	}

}



