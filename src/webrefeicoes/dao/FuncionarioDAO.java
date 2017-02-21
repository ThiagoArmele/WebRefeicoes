package webrefeicoes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Funcionario;

public class FuncionarioDAO {

	public void save(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(funcionario);
		t.commit();
	}

	public Funcionario getFuncionario(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Funcionario) session.load(Funcionario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Funcionario> lista = session.createQuery("from Funcionario").list();
		t.commit();
		return lista;
	}

	public void remove(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(funcionario);
		t.commit();
	}

	public void update(Funcionario funcionario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(funcionario);
		t.commit();
		
	}

}
