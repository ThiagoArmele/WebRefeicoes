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

import webrefeicoes.dao.FuncionarioDAO;
import webrefeicoes.model.Funcionario;

@ManagedBean(name = "FuncionarioController")
@SessionScoped
public class funcionarioController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	@SuppressWarnings("rawtypes")
	private DataModel listaFuncionarios;
	private Funcionario selecionaFuncionario;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListaFuncionarios() {
		List<Funcionario> lista = new FuncionarioDAO().list();
		listaFuncionarios = new ListDataModel(lista);
		return listaFuncionarios;
	}
	
	public funcionarioController() {
		funcionario = new Funcionario();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Funcionario getSelecionaFuncionario() {
		return selecionaFuncionario;
	}

	public void setSelecionaFuncionario(Funcionario selecionaFuncionario) {
		this.selecionaFuncionario = selecionaFuncionario;
	}
	
	
	public void excluirFuncionario(Funcionario funcionario){
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.remove(funcionario);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Funcionário removido com sucesso!", 
              		  ""));
	}
			
	public Funcionario selecionarDados(SelectEvent event) {
   	 Funcionario f = (Funcionario) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Funcionario f = (Funcionario) event.getObject();
    	 setFuncionario(f); 
     }
	 
	 public void limparDados() {
		 Funcionario f = new Funcionario();
    	 setFuncionario(f); 
     }
	
	public void adicionarFuncionario(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		if (funcionario.getCodigo() == 0L) {
			dao.save(funcionario);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Funcionário cadastrado com sucesso!", 
	              		  ""));
		} else {
			dao.update(funcionario);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Funcionário alterado com sucesso!", 
	              		  ""));
		}
	
	}

}
