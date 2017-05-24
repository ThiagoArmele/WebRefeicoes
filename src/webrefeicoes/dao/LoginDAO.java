package webrefeicoes.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import webrefeicoes.model.Cliente;
import webrefeicoes.model.Funcionario;

public class LoginDAO {
	
	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	public Cliente getCliente(String nomeCliente, String senha) {
		try {
			Cliente cliente = (Cliente) em
               .createQuery(
                           "SELECT c from Cliente c where c.usuario = :name and c.senha = :senha")
               .setParameter("name", nomeCliente)
               .setParameter("senha", senha).getSingleResult();
            return cliente;
      } catch (NoResultException e) {
            return null;
      }
		
	}
	
	public Funcionario getFuncionario(String nomeFuncionario, String senha) {
		try {
			Funcionario funcionario = (Funcionario) em
               .createQuery(
                           "SELECT c from Funcionario c where c.usuario = :name and c.senha = :senha")
               .setParameter("name", nomeFuncionario)
               .setParameter("senha", senha).getSingleResult();
            return funcionario;
      } catch (NoResultException e) {
            return null;
      }
		
	}
	
	public Cliente verificarEmailExistente(String email) {
		try {
			Cliente cliente = (Cliente) em
	               .createQuery(
	                           "SELECT c from Cliente c where c.email = :email")
	               .setParameter("email", email).getSingleResult();
			return cliente;
		}	catch (NoResultException e) {
            return null;
      }		
					
	}
	
	
}
