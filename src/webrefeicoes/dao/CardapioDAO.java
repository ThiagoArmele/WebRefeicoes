package webrefeicoes.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import webrefeicoes.model.Produto;

public class CardapioDAO {

	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	
	
	public List<Produto> getProdutoGuarnicao(String tipo) {
		try {
			@SuppressWarnings("unchecked")
			List<Produto> prodto = (List<Produto>) em
               .createQuery(
                           "Select p from Produto p where p.tipo = :tipoProduto and p.noCardapio = 'sim'")
               .setParameter("tipoProduto", tipo).getResultList();
            return prodto;
      } catch (NoResultException e) {
            return null;
      }
	}
}
