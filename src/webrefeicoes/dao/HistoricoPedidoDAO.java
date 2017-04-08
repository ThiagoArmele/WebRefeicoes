package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.HistoricoPedido;

public class HistoricoPedidoDAO {
	
	
	public HistoricoPedido getHistoricoPedido(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (HistoricoPedido) session.load(HistoricoPedido.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<HistoricoPedido> list(int id, int idCliente, int idEmpresa) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<HistoricoPedido> lista = session.
				createQuery("from HistoricoPedido where " +
						(id == 0? "id <> :id" : "id = :id") + 
						(idCliente == 0? " and idCliente <> :idCliente" : " and idCliente = :idCliente") +
						(idEmpresa == 0? " and idEmpresa <> :idEmpresa" : " and idEmpresa = :idEmpresa")).setParameter("id",id)
								.setParameter("idCliente", idCliente).setParameter("idEmpresa", idEmpresa)
				.list();
		t.commit();
		return lista;
	}
	

}



