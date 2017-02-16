package webrefeicoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.sun.org.apache.bcel.internal.generic.Select;

import webrefeicoes.dao.EmpresaDAO;
import webrefeicoes.model.Empresa;

@ManagedBean(name = "empresaController")
@SessionScoped
public class EmpresaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private DataModel listaEmpresas;
	private Empresa selecionaEmpresa;
	
	public DataModel getListaEmpresas() {
		List<Empresa> lista = new EmpresaDAO().list();
		listaEmpresas = new ListDataModel(lista);
		return listaEmpresas;
	}
	
	public EmpresaController() {
		empresa = new Empresa();
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Empresa getSelecionaEmpresa() {
		return selecionaEmpresa;
	}

	public void setSelecionaEmpresa(Empresa selecionaEmpresa) {
		this.selecionaEmpresa = selecionaEmpresa;
	}
	
	
	public void excluirEmpresa(Empresa empresa){
		EmpresaDAO dao = new EmpresaDAO();
		dao.remove(empresa);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Empresa removido com sucesso!", 
              		  ""));
	}
			
	public Empresa selecionarDados(SelectEvent event) {
   	 Empresa f = (Empresa) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Empresa f = (Empresa) event.getObject();
    	 setEmpresa(f); 
     }
	 
	 public void limparDados() {
		 Empresa f = new Empresa();
    	 setEmpresa(f); 
     }
	
	public void adicionarEmpresa(){
		EmpresaDAO dao = new EmpresaDAO();
		
		if (empresa.getCodigo() == 0L) {
			dao.save(empresa);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Empresa cadastrado com sucesso!", 
	              		  ""));
		} else {
			dao.update(empresa);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Empresa alterado com sucesso!", 
	              		  ""));
		}
	
	}
	
}
