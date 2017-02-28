package webrefeicoes.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import webrefeicoes.model.Funcionario;

public class LoginDAO {
	
	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	public Funcionario getFuncionario(String nomeFuncionario, String senha) {
		try {
            Funcionario funcionario = (Funcionario) em
               .createQuery(
                           "SELECT f from Funcionario f where f.usuario = :name and f.senha = :senha")
               .setParameter("name", nomeFuncionario)
               .setParameter("senha", senha).getSingleResult();
            return funcionario;
      } catch (NoResultException e) {
            return null;
      }
		
	}
	
	
}
