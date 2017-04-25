package webrefeicoes.dao;

import java.util.Date;
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
	public List<HistoricoPedido> list(int id, int idCliente, int idEmpresa, Date dataInicial, Date dataFinal) {
		java.sql.Date dataInicialSql = new java.sql.Date (dataInicial.getTime());
		java.sql.Date dataFinalSql = new java.sql.Date (dataFinal.getTime());
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<HistoricoPedido> lista = session.
				createQuery("from HistoricoPedido where " +
						(id == 0? "id <> :id" : "id = :id") + 
						(idCliente == 0? " and idCliente <> :idCliente" : " and idCliente = :idCliente") +
						(idEmpresa == 0? " and idEmpresa <> :idEmpresa" : " and idEmpresa = :idEmpresa")+
						(dataInicial == null? "" : dataFinal == null? "" : " and dataPedido BETWEEN :dataInicial and :dataFinal")).setParameter("id",id)
								.setParameter("idCliente", idCliente).setParameter("idEmpresa", idEmpresa).setParameter("dataInicial",dataInicialSql)
								.setParameter("dataFinal",dataFinalSql).list();
		t.commit();
		return lista;
	}
	

}



