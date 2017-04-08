package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.BandeiraCartao;

public class BandeiraCartaoDAO {

	public void save(BandeiraCartao bandeiraCartao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(bandeiraCartao);
		t.commit();
	}

	public BandeiraCartao getBandeiraCartao(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (BandeiraCartao) session.load(BandeiraCartao.class, id);
	}

	public List<BandeiraCartao> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<BandeiraCartao> lista = session.createQuery("from BandeiraCartao").list();
		t.commit();
		return lista;
	}

	public void remove(BandeiraCartao bandeiraCartao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(bandeiraCartao);
		t.commit();
	}

	public void update(BandeiraCartao bandeiraCartao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(bandeiraCartao);
		t.commit();
		
	}

}



