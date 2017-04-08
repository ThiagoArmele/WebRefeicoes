package webrefeicoes.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import webrefeicoes.model.Cliente;

public class LoginDAO {
	
	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	public Cliente getCliente(String nomeFuncionario, String senha) {
		try {
			Cliente cliente = (Cliente) em
               .createQuery(
                           "SELECT c from Cliente c where c.usuario = :name and c.senha = :senha")
               .setParameter("name", nomeFuncionario)
               .setParameter("senha", senha).getSingleResult();
            return cliente;
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
