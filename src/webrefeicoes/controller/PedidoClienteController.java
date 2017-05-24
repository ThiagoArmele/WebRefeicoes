package webrefeicoes.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.PedidoDAO;
import webrefeicoes.model.Pedido;

@ManagedBean(name = "pedidoClienteController")
@SessionScoped
public class PedidoClienteController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private Pedido pedidoPendente;
	private List<Pedido> listaPedidos;
	private List<Pedido> listaPedidosPendentes;
	private Pedido selecionaPedido;
	private Pedido selecionaPedidoPendente;
	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	private String nomeCliente;
	
	@ManagedProperty(value = "#{loginController}")
	private LoginController clienteLogado;
	
	public List<Pedido> listaPedidos() {
		listaPedidos = new PedidoDAO().listPedidoCliente(getClienteLogado().getCliente().getCodigo());
		return listaPedidos;
	}
	
	public List<Pedido> listaPedidosPendentes() {
		listaPedidosPendentes = new PedidoDAO().listPedidoClientePendente(getClienteLogado().getCliente().getCodigo());
		return listaPedidosPendentes;
	}
	
	@PostConstruct
	private void construct() {
		pedido = new Pedido();
		pedidoPendente = new Pedido();
		listaPedidos();
		listaPedidosPendentes();
	}
	
	public PedidoClienteController() {
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Pedido getSelecionaPedido() {
		return selecionaPedido;
	}

	public void setSelecionaPedido(Pedido selecionaPedido) {
		this.selecionaPedido = selecionaPedido;
	}
	
	
	public Pedido selecionarDadosPendentes(SelectEvent event) {
   	 Pedido f = (Pedido) event.getObject();
   	 return f;
    }
	
	public Pedido selecionarDados(SelectEvent event) {
	   	 Pedido f = (Pedido) event.getObject();
	   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Pedido f = (Pedido) event.getObject();
    	 setNomeCliente((String) em
		           .createQuery(
	                       "SELECT c.nome from Cliente c where c.codigo = :codigo ")
	           .setParameter("codigo", f.getCodigoCliente()).getSingleResult());
    	 setPedido(f); 
     }
	 
	 public void trazerDadosPendente(SelectEvent event) {
    	 Pedido f = (Pedido) event.getObject();
    	 setNomeCliente((String) em
		           .createQuery(
	                       "SELECT c.nome from Cliente c where c.codigo = :codigo ")
	           .setParameter("codigo", f.getCodigoCliente()).getSingleResult());
    	 setPedidoPendente(f); 
     }
	 

	public void adicionarPedido(){
		PedidoDAO dao = new PedidoDAO();
		if (pedido.getCodigo() != 0L) {
			pedido.setAvaliado(true);
			dao.update(pedido);
			RequestContext.getCurrentInstance().execute("PF('dlgAvaliacao').show()");  
		} else {
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_WARN,"Você deve selecionar um pedido para avaliar!", 
	              		  ""));
		}
	}
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public LoginController getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(LoginController clienteLogado) {
		this.clienteLogado = clienteLogado;
	}

	public Pedido getPedidoPendente() {
		return pedidoPendente;
	}

	public void setPedidoPendente(Pedido pedidoPendente) {
		this.pedidoPendente = pedidoPendente;
	}

	public Pedido getSelecionaPedidoPendente() {
		return selecionaPedidoPendente;
	}

	public void setSelecionaPedidoPendente(Pedido selecionaPedidoPendente) {
		this.selecionaPedidoPendente = selecionaPedidoPendente;
	}
	
	

}
