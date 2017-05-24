package webrefeicoes.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;

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
import webrefeicoes.util.Criptografia;
import webrefeicoes.util.Util;

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
	private String senha;
	private static MessageDigest md = null;
	
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
	
	public void adicionarCliente() throws NoSuchAlgorithmException{
		ClienteDAO dao = new ClienteDAO();
		
		md = MessageDigest.getInstance("MD5");
		cliente.setSenha(Criptografia.criptografar(getSenha(), md));
		
		if(cliente.getSenha() != null && cliente.getSenha().trim() != ""){
			
			if(new Util().isCpf(cliente.getCpf().replace(".", "").replace("-", ""))){
				if(isCheckEntrega()) {
					cliente.setRuaEntrega(cliente.getRua());
					cliente.setBairroEntrega(cliente.getBairro());
					cliente.setCidadeEntrega(cliente.getCidade());
					cliente.setCepEntrega(cliente.getCep());
					cliente.setNumEndEntrega(cliente.getNumEnd());
				}
				
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
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_ERROR,"CPF inválido, por favor verfique-o.", 
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static void main(String[] args) throws ParseException {
		
		Util u = new Util();
		// Get a default NumberFormat instance using the
        // en_CA locale.
        Locale caLoc = new Locale("en", "CA");
        NumberFormat numForm =
            NumberFormat.getCurrencyInstance(caLoc);

        // Format some decimals using the pattern supplied above.
        String dest1 = numForm.format(22.3423D);
        System.out.println("dest1 = " + dest1);

        String dest2 = numForm.format(64000D);
        System.out.println("dest2 = " + dest2);
		
	}
}
