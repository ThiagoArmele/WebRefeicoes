package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Empresa;

public class EmpresaDAO {

	public void save(Empresa empresa) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(empresa);
		t.commit();
	}

	public Empresa getEmpresa(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Empresa) session.load(Empresa.class, id);
	}

	public List<Empresa> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Empresa> lista = session.createQuery("from Empresa").list();
		t.commit();
		return lista;
	}

	public void remove(Empresa empresa) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(empresa);
		t.commit();
	}

	public void update(Empresa empresa) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(empresa);
		t.commit();
		
	}

}



