package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Produto;

public class ProdutoDAO {

	public void save(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(produto);
		t.commit();
	}

	public Produto getProduto(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Produto) session.load(Produto.class, id);
	}

	public List<Produto> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Produto> lista = session.createQuery("from Produto").list();
		t.commit();
		return lista;
	}

	public void remove(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(produto);
		t.commit();
	}

	public void update(Produto produto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(produto);
		t.commit();
		
	}

}



