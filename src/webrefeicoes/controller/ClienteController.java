package webrefeicoes.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.ClienteDAO;
import webrefeicoes.model.Cliente;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	
	private DataModel listaClientes;
	private Cliente selecionaCliente;
	
	
	public DataModel getListaClientes() {
		List<Cliente> lista = new ClienteDAO().list();
		listaClientes = new ListDataModel(lista);
		return listaClientes;
	}
	
	public ClienteController() {
		cliente = new Cliente();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getSelecionaCliente() {
		return selecionaCliente;
	}

	public void setSelecionaCliente(Cliente selecionaCliente) {
		this.selecionaCliente = selecionaCliente;
	}
	
	
	public void excluirCliente(Cliente cliente){
		ClienteDAO dao = new ClienteDAO();
		dao.remove(cliente);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Cliente removido com sucesso!", 
              		  ""));
	}
			
	public Cliente selecionarDados(SelectEvent event) {
   	 Cliente f = (Cliente) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Cliente f = (Cliente) event.getObject();
    	 setCliente(f); 
     }
	 
	 public void limparDados() {
		 Cliente f = new Cliente();
    	 setCliente(f); 
     }
	
	public void adicionarCliente(){
		ClienteDAO dao = new ClienteDAO();
		
		if (cliente.getCodigo() == 0L) {
			dao.save(cliente);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Cliente cadastrado com sucesso!", 
	              		  ""));
		} else {
			dao.update(cliente);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Cliente alterado com sucesso!", 
	              		  ""));
		}
	
	}
}
