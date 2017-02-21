package webrefeicoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.ClienteDAO;
import webrefeicoes.dao.EmpresaDAO;
import webrefeicoes.model.Cliente;
import webrefeicoes.model.Empresa;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<Cliente> listaClientes;
	private Cliente selecionaCliente;
	private List<SelectItem> empresas;
	private boolean checkEntrega;
	
	
	public List<Cliente> listaClientes() {
		listaClientes = new ClienteDAO().list();
		return listaClientes;
	}
	
	@PostConstruct
	private void construct() {
		cliente = new Cliente();
		setEmpresas(new ArrayList<SelectItem>());
		listaClientes();
	}
	
	public ClienteController() {
		setCheckEntrega(false);
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
   	 setCheckEntrega(false);
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Cliente f = (Cliente) event.getObject();
    	 setCheckEntrega(false);
    	 setCliente(f); 
     }
	 
	 public void limparDados() {
		 Cliente f = new Cliente();
		 setCheckEntrega(false);
    	 setCliente(f); 
     }
	
	public void adicionarCliente(){
		ClienteDAO dao = new ClienteDAO();
		if(!isCheckEntrega()) {
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
		} else {
			if (cliente.getCodigo() == 0L) {
				cliente.setRuaEntrega(cliente.getRua());
				cliente.setBairroEntrega(cliente.getBairro());
				cliente.setCidadeEntrega(cliente.getCidade());
				cliente.setCepEntrega(cliente.getCep());
				cliente.setNumEndEntrega(cliente.getNumEnd());
				dao.save(cliente);
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Cliente cadastrado com sucesso!", 
		              		  ""));
			} else {
				cliente.setRuaEntrega(cliente.getRua());
				cliente.setBairroEntrega(cliente.getBairro());
				cliente.setCidadeEntrega(cliente.getCidade());
				cliente.setCepEntrega(cliente.getCep());
				cliente.setNumEndEntrega(cliente.getNumEnd());
				dao.update(cliente);
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Cliente alterado com sucesso!", 
		              		  ""));
			}
		}
		
	
	}

	public List<SelectItem> getEmpresas() {
		empresas.clear();
		EmpresaDAO dao = new EmpresaDAO();
		List<Empresa> listaEmpresas = dao.list(); 
		 for(Empresa emp : listaEmpresas){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(emp.getCodigo());  
			 s.setLabel(emp.getNomeFantasia());  
			 empresas.add(s);  
		 }  
		 
		return empresas;
	}

	public void setEmpresas(List<SelectItem> empresas) {
		this.empresas = empresas;
	}

	public boolean isCheckEntrega() {
		return checkEntrega;
	}

	public void setCheckEntrega(boolean checkEntrega) {
		this.checkEntrega = checkEntrega;
	}

}
