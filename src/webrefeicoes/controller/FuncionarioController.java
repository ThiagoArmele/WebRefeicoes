package webrefeicoes.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webrefeicoes.model.Funcionario;

public class FuncionarioController {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebRefeicoes");
		EntityManager em = factory.createEntityManager();
		
		Funcionario func = new Funcionario();
		
		func.setNome("Thiago Armele de Campos");
		
		em.getTransaction().begin();
		
		em.persist(func);

		em.getTransaction().commit();
	}
	
	
}
