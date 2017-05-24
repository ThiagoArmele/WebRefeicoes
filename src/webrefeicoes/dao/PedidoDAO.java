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
		List<Pedido> lista = session.createQuery("from Pedido p where  p.statusPedido <> 'Entregue'").list();
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

	
	public List<Pedido> listPedidoCliente(Integer codigoCliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Pedido> lista = session.createQuery("from Pedido p where  p.codigoCliente = :codigoCliente and p.statusPedido = 'Entregue'").
				setParameter("codigoCliente", codigoCliente).list();
		t.commit();
		return lista;
	}
	
	public List<Pedido> listPedidoClientePendente(Integer codigoCliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Pedido> lista = session.createQuery("from Pedido p where  p.codigoCliente = :codigoCliente and p.statusPedido <> 'Entregue'").
				setParameter("codigoCliente", codigoCliente).list();
		t.commit();
		return lista;
	}
}



