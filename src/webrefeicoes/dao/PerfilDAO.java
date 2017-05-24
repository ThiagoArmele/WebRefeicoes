package webrefeicoes.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PerfilDAO {
	
	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	
	public int totalPedidos(int codigo) {
        return em.createQuery("SELECT p from Pedido p where p.codigoCliente = :codigo")
                .setParameter("codigo", codigo).getResultList().size();
	}
	
	public int totalAvaliados(int codigo) {
        return em.createQuery("SELECT p from Pedido p where p.codigoCliente = :codigo and p.avaliado = 1")
                .setParameter("codigo", codigo).getResultList().size();
	}
	
	
	public String empresaCliente(int codigoEmpresa) {
        return (String) em.createQuery("SELECT emp.nomeFantasia from Empresa emp where emp.codigo = :idEmpresa")
                .setParameter("idEmpresa", codigoEmpresa).getSingleResult();
	}
}
