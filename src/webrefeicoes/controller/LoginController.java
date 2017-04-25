package webrefeicoes.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import webrefeicoes.dao.ClienteDAO;
import webrefeicoes.dao.LoginDAO;
import webrefeicoes.model.Cliente;
import webrefeicoes.util.Criptografia;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDao = new LoginDAO();
    private Cliente cliente  = new Cliente();
    private String emailCliente;
    String senhaNova;
    private String user, senha;
    HtmlEmail  email;
    private static MessageDigest md = null;
    
    public String envia() {
    	
    	try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	
    	setSenha(Criptografia.criptografar(getSenha(), md));
    	
    	cliente = loginDao.getCliente(user, getSenha());
        if (cliente == null) {
              FacesContext.getCurrentInstance().addMessage(
                         null,
                         new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e senha não encontrado!",
                                     "Erro no Login!"));
              return "";
        } else {
        	
            return "/index";
        }
  }
    
  public void enviarSenhaNova() throws EmailException{
	  cliente = loginDao.verificarEmailExistente(emailCliente);
	  
      if (cliente == null) {
            FacesContext.getCurrentInstance().addMessage(
                       null,
                       new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail não encontrado!",
                                   "Erro no Login!"));
      } else {
    	  FacesContext.getCurrentInstance().addMessage(
                  null,
                  new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Enviado com Sucesso!",
                              "Erro no Login!"));
    	  
    	  enviarEmail();
      }
	  
  }
  
  public void enviarEmail() throws EmailException {
	  email = new HtmlEmail();
	  senhaNova = geradorDeSenha();
	  email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
	  email.setSmtpPort(465);
	  email.addTo(getEmailCliente()); //destinatário
	  email.setFrom("webrefeicoes@gmail.com", "WebRefeicoes"); // remetente
	  email.setSubject("Recuperação de Senha - Não responder esse e-mail"); // assunto do e-mail
	  email.setSSLOnConnect(true);
	  email.setAuthentication("webrefeicoes@gmail.com", "webrefeicoes2017");
	  
	  email.setHtmlMsg(
			  "<!DOCTYPE html>"
			  	+ "<html lang=\"en\">"
			  		+ "<head>"
			  			+"<title>Bootstrap Example</title>" 
			  			+ "  <meta charset=\"utf-8\">" 
			  			+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" 
			  			+ "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">" 
			  			+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>" 
			  			+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"
			  		+ "</head>"
			  		+ "<body>"
			  			+ "<div class=\"container\">" 
			  			+ "  <div class='row' style='border-radius: 10px'>" 
			  			+ "  	<div class='col-lg-12'>" 
			  			+ "    	<div class='col-lg-12 text-center'    display: -webkit-box; style='background: #9f0101;border-top-left-radius: 10px; border-top-right-radius: 10px;" 
			  			+ "    border: 1px solid #e82222; padding: 14px; color: white; font-weight: bold; font-size: 20px'>"
			  			+ "      Recuperação de Senha - WebRefeições </div>" 
			  			+ "        <div class='col-lg-12' style='background: #ececec;border: 1px solid #9a9a9a;border-bottom-right-radius: 10px;"
			  			+ "			 border-bottom-left-radius: 10px;padding: 20px;color: #6a6a6a; font-weight: bold;'>" 
			  			+ "    	Sua nova senha é: " + senhaNova + "." 
			  			+ "        <br>" 
			  			+ "        <br>" 
			  			+ "        Obs.: Recomendamos que troque a senha ao acessar o sistema por questões de segurança." 
			  			+ "    </div>" 
			  			+ "    </div>"
			  			+ "  </div>" 
			  			+ "</div>"
			  		+ "</body>"
		  		+ "</html>"
			  );
	  email.send();
	  ClienteDAO daoCliente = new ClienteDAO();
	  cliente.setSenha(senhaNova);
	  daoCliente.update(cliente);
  }
  
  private String geradorDeSenha() {
	  Random caracterAleatorio = new Random();
	  Random quantidadeCaracterAleatorio = new Random();
	  
	  String[] caracteres = {"A", "B", "C", "D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","?","#","!","$","%",
	  "&","*",">","<","(",")","/","|","}","{","=","+","-","_","1","2","3","4","5","6","7","8","9","0"};
	  String senha = "";
	  
	  int quantidadeCaracter = quantidadeCaracterAleatorio.nextInt(14) + 5;
	  for (int i = 0; i < quantidadeCaracter; i++) {
	  senha += caracteres[caracterAleatorio.nextInt(caracteres.length)];
	  }
	  return senha;
  }

  public String getEmailCliente() {
		return emailCliente;
  }
	
  public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
  }
  
  public Cliente getCliente() {
      return cliente;
  }
	
  public void setCliente(Cliente cliente) {
	      this.cliente = cliente;
  }

  public String getUser() {
		return user;
  }
	
  public void setUser(String user) {
		this.user = user;
  }

  public String getSenha() {
		return senha;
  }
	
  public void setSenha(String senha) {
	  this.senha = senha;
  }

  
  
}
