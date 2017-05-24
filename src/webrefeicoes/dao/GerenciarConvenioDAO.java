package webrefeicoes.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Convenio;

public class GerenciarConvenioDAO {

	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	public void save(Convenio convenio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(convenio);
		t.commit();
	}

	public Convenio getConvenio(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Convenio) session.load(Convenio.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Convenio> list(int id,int idCliente,String status,String tipo, Date dataInicial, Date dataFinal) {
		java.sql.Date dataInicialSql = new java.sql.Date (dataInicial.getTime());
		java.sql.Date dataFinalSql = new java.sql.Date (dataFinal.getTime());
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Convenio> lista = session.createQuery("from Convenio where "+ (id == 0? "id <> :id" : "id = :id") + 
				(idCliente == 0? " and idCliente <> :idCliente" : " and idCliente = :idCliente") +
				(dataInicial == null? "" : dataFinal == null? "" : " and dataFinal BETWEEN :dataInicial and :dataFinal")).setParameter("id",id)
						.setParameter("idCliente", idCliente).setParameter("dataInicial",dataInicialSql)
						.setParameter("dataFinal",dataFinalSql).list();
		t.commit();
		return lista;
	}

	public void remove(Convenio convenio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(convenio);
		t.commit();
	}

	public void update(Convenio convenio) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(convenio);
		t.commit();
	}

	public Convenio getConvenio(int idCliente) {
		try {
			Convenio convenio = (Convenio) em
               .createQuery(
                           "SELECT c from Convenio c where c.idCliente = :idCliente")
               .setParameter("idCliente", idCliente).getSingleResult();
            return convenio;
      } catch (NoResultException e) {
            return null;
      }
		
	}
}
