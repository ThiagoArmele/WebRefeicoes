package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Embalagem;

public class EmbalagemDAO {

	public void save(Embalagem embalagem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(embalagem);
		t.commit();
	}

	public Embalagem getEmbalagem(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Embalagem) session.load(Embalagem.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Embalagem> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Embalagem> lista = session.createQuery("from Embalagem").list();
		t.commit();
		return lista;
	}

	public void remove(Embalagem embalagem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(embalagem);
		t.commit();
	}

	public void update(Embalagem embalagem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(embalagem);
		t.commit();
		
	}
}
