package webrefeicoes.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import webrefeicoes.dao.LoginDAO;
import webrefeicoes.model.Funcionario;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDao = new LoginDAO();
    private Funcionario funcionario;
    
    
    public LoginController() {
		funcionario = new Funcionario();
	}
    
    
    public String envia() {
        
    	funcionario = loginDao.getFuncionario(funcionario.getUsuario(), funcionario.getSenha());
        if (funcionario == null) {
        	funcionario = new Funcionario();
              FacesContext.getCurrentInstance().addMessage(
                         null,
                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e senha não encontrado!",
                                     "Erro no Login!"));
              return null;
        } else {
              return "/index";
        }
  }
    
   public Funcionario getFuncionario() {
        return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
  }
}
