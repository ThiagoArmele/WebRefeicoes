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

import webrefeicoes.dao.ConvenioDAO;
import webrefeicoes.dao.FuncionarioDAO;
import webrefeicoes.dao.PerfilDAO;
import webrefeicoes.model.Funcionario;
import webrefeicoes.util.Criptografia;

@ManagedBean(name = "perfilFuncionarioController")
@SessionScoped
public class PerfilFuncionarioController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PerfilDAO dao = new PerfilDAO();
	ConvenioDAO convenioDao;
	@ManagedProperty(value = "#{loginController}")
	private LoginController funcionarioLogado;
	private static MessageDigest md = null;
	private String senha;
	
	public PerfilFuncionarioController() {
	}
	
	public void alterar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario Funcionario = new Funcionario();
		EntityManagerFactory factory = Persistence
	            .createEntityManagerFactory("WebRefeicoes");
		EntityManager em = factory.createEntityManager();
		Funcionario = (Funcionario) em
	               .createQuery(
	                           "SELECT c from Funcionario c where c.codigo = :codigo")
	               .setParameter("codigo", funcionarioLogado.getFuncionario().getCodigo()).getSingleResult();
		
		if (!(Funcionario.toString().equals(funcionarioLogado.getFuncionario().toString()))) {
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			funcionarioLogado.setSenha(Criptografia.criptografar(getSenha(), md));
			dao.update(funcionarioLogado.getFuncionario());
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

	public LoginController getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(LoginController funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	

}
