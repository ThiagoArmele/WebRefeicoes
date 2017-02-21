package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Pedido;

public class PedidoDAO {

	public void save(Pedido pedido) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(pedido);
		t.commit();
	}

	public Pedido getPedido(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Pedido) session.load(Pedido.class, id);
	}

	public List<Pedido> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Pedido> lista = session.createQuery("from Pedido").list();
		t.commit();
		return lista;
	}

	public void remove(Pedido pedido) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(pedido);
		t.commit();
	}

	public void update(Pedido pedido) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(pedido);
		t.commit();
		
	}

}



