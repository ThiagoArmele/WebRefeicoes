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

import webrefeicoes.dao.PratoDAO;
import webrefeicoes.model.Prato;

@ManagedBean(name = "pratoController")
@SessionScoped
public class PratoController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Prato prato;
	@SuppressWarnings("rawtypes")
	private DataModel listaPratos;
	private Prato selecionaPrato;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListaPratos() {
		List<Prato> lista = new PratoDAO().list();
		listaPratos = new ListDataModel(lista);
		return listaPratos;
	}
	
	public PratoController() {
		prato = new Prato();
	}
	
	public Prato getPrato() {
		return prato;
	}
	
	public void setPrato(Prato prato) {
		this.prato = prato;
	}
	
	public Prato getSelecionaPrato() {
		return selecionaPrato;
	}

	public void setSelecionaPrato(Prato selecionaPrato) {
		this.selecionaPrato = selecionaPrato;
	}
	
	
	public void excluirPrato(Prato prato){
		PratoDAO dao = new PratoDAO();
		dao.remove(prato);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Prato removido com sucesso!", 
              		  ""));
	}
			
	public Prato selecionarDados(SelectEvent event) {
   	 Prato f = (Prato) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Prato f = (Prato) event.getObject();
    	 setPrato(f); 
     }
	 
	 public void limparDados() {
		 Prato f = new Prato();
    	 setPrato(f); 
     }
	
	public void adicionarPrato(){
		PratoDAO dao = new PratoDAO();
		
		if (prato.getCodigo() == 0L) {
			dao.save(prato);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Prato cadastrado com sucesso!", 
	              		  ""));
		} else {
			dao.update(prato);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Prato alterado com sucesso!", 
	              		  ""));
		}
	
	}
}
