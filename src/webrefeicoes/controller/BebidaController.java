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

import webrefeicoes.dao.BebidaDAO;
import webrefeicoes.model.Bebida;

@ManagedBean(name = "bebidaController")
@SessionScoped
public class BebidaController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bebida bebida;
	@SuppressWarnings("rawtypes")
	private DataModel listaBebidas;
	private Bebida selecionaBebida;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListaBebidas() {
		List<Bebida> lista = new BebidaDAO().list();
		listaBebidas = new ListDataModel(lista);
		return listaBebidas;
	}
	
	public BebidaController() {
		bebida = new Bebida();
	}
	
	public Bebida getBebida() {
		return bebida;
	}
	
	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}
	
	public Bebida getSelecionaBebida() {
		return selecionaBebida;
	}

	public void setSelecionaBebida(Bebida selecionaBebida) {
		this.selecionaBebida = selecionaBebida;
	}
	
	
	public void excluirBebida(Bebida bebida){
		BebidaDAO dao = new BebidaDAO();
		dao.remove(bebida);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Bebida removido com sucesso!", 
              		  ""));
	}
			
	public Bebida selecionarDados(SelectEvent event) {
   	 Bebida f = (Bebida) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Bebida f = (Bebida) event.getObject();
    	 setBebida(f); 
     }
	 
	 public void limparDados() {
		 Bebida f = new Bebida();
    	 setBebida(f); 
     }
	
	public void adicionarBebida(){
		BebidaDAO dao = new BebidaDAO();
		
		if (bebida.getCodigo() == 0L) {
			dao.save(bebida);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Bebida cadastrado com sucesso!", 
	              		  ""));
		} else {
			dao.update(bebida);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Bebida alterado com sucesso!", 
	              		  ""));
		}
	
	}
}
