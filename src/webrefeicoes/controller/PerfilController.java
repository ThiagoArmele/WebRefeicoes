package webrefeicoes.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import webrefeicoes.dao.ClienteDAO;
import webrefeicoes.dao.PerfilDAO;
import webrefeicoes.model.Cliente;
import webrefeicoes.model.Funcionario;
import webrefeicoes.util.Criptografia;

@ManagedBean(name = "perfilController")
@SessionScoped
public class PerfilController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PerfilDAO dao = new PerfilDAO();
	@ManagedProperty(value = "#{loginController}")
	private LoginController clienteLogado;
	private static MessageDigest md = null;
	private String senha;
	
	public PerfilController() {
	}

	public int getTotalAvaliacao() {
		return dao.totalAvaliados(clienteLogado.getCliente().getCodigo());
	}

	public int getTotalPedido() {
		return dao.totalPedidos(clienteLogado.getCliente().getCodigo());
	}
	
	public LoginController getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(LoginController clienteLogado) {
		this.clienteLogado = clienteLogado;
	}
	
	public void alterar(){
		ClienteDAO dao = new ClienteDAO();
		Cliente cliente = new Cliente();
		EntityManagerFactory factory = Persistence
	            .createEntityManagerFactory("WebRefeicoes");
		EntityManager em = factory.createEntityManager();
		cliente = (Cliente) em
	               .createQuery(
	                           "SELECT c from Cliente c where c.codigo = :codigo")
	               .setParameter("codigo", clienteLogado.getCliente().getCodigo()).getSingleResult();
		
		if (!(cliente.toString().equals(clienteLogado.getCliente().toString()))) {
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clienteLogado.setSenha(Criptografia.criptografar(getSenha(), md));
			dao.update(clienteLogado.getCliente());
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Informações Alteradas com sucesso!", 
	              		  ""));
		}
		
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
