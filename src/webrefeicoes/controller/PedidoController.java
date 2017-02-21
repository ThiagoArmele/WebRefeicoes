package webrefeicoes.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.PedidoDAO;
import webrefeicoes.model.Pedido;

@ManagedBean(name = "pedidoController")
@SessionScoped
public class PedidoController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido;
	private List<Pedido> listaPedidos;
	private Pedido selecionaPedido;
	
	
	public List<Pedido> listaPedidos() {
		listaPedidos = new PedidoDAO().list();
		return listaPedidos;
	}
	
	@PostConstruct
	private void construct() {
		pedido = new Pedido();
		listaPedidos();
	}
	
	public PedidoController() {
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
	
	
	public void excluirPedido(Pedido pedido){
		PedidoDAO dao = new PedidoDAO();
		dao.remove(pedido);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Pedido removido com sucesso!", 
              		  ""));
	}
			
	public Pedido selecionarDados(SelectEvent event) {
   	 Pedido f = (Pedido) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Pedido f = (Pedido) event.getObject();
    	 setPedido(f); 
     }
	 
	 public void limparDados() {
		 Pedido f = new Pedido();
    	 setPedido(f); 
     }
	
	public void adicionarPedido(){
		PedidoDAO dao = new PedidoDAO();
		System.out.println("Guarnição escolhida: " + pedido.getGuarnicao());
			if (pedido.getCodigo() == 0L) {
				dao.save(pedido);
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Pedido cadastrado com sucesso!", 
		              		  ""));
			} else {
				dao.update(pedido);
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Pedido alterado com sucesso!", 
		              		  ""));
			}
	
	}



}
