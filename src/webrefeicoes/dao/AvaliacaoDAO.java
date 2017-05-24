package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Avaliacao;

public class AvaliacaoDAO {

	public void save(Avaliacao avaliacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(avaliacao);
		t.commit();
	}

	public Avaliacao getAvaliacao(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Avaliacao) session.load(Avaliacao.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Avaliacao> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Avaliacao> lista = session.createQuery("from Avaliacao order by dataEnviada DESC").list();
		t.commit();
		return lista;
	}

	public void remove(Avaliacao avaliacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(avaliacao);
		t.commit();
	}

	public void update(Avaliacao avaliacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(avaliacao);
		t.commit();
		
	}

}



