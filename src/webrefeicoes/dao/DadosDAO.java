package webrefeicoes.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.model.Pedido;
import webrefeicoes.model.Produto;

public class DadosDAO {

	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	public int totalClientes() {
        return em.createQuery("SELECT c from Cliente c").getResultList().size();
	}
	
	public int totalClientesConveniados() {
        return em.createQuery("SELECT c from Convenio c").getResultList().size();
	}
	
	public int totalAvaliacoes() {
        return em.createQuery("SELECT a from Avaliacao a").getResultList().size();
	}
	
	public int totalFuncionarios() {
        return em.createQuery("SELECT f from Funcionario f").getResultList().size();
	}
	
	public int totalPedidos() {
        return em.createQuery("SELECT p from Pedido p").getResultList().size();
	}
	
	public List<Pedido> valoresPedidos(int ano) {
		@SuppressWarnings("unchecked")
		List<Pedido> lista = (List<Pedido>) em.createQuery("select p from Pedido p where year(p.dataPedido) between :ano and :ano")
				.setParameter("ano", ano).getResultList();
		return lista;
	}
	
}
