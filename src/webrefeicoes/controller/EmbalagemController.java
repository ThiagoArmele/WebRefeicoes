package webrefeicoes.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.EmbalagemDAO;
import webrefeicoes.model.Embalagem;

@ManagedBean(name = "embalagemController")
@SessionScoped
public class EmbalagemController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Embalagem embalagem;
	private List<Embalagem> listaEmbalagems;
	private Embalagem selecionaEmbalagem;
	
	
	public List<Embalagem> listaEmbalagems() {
		listaEmbalagems = new EmbalagemDAO().list();
		return listaEmbalagems;
	}
	
	@PostConstruct
	private void construct() {
		embalagem = new Embalagem();
		listaEmbalagems();
	}
	
	public EmbalagemController() {
	}
	
	public Embalagem getEmbalagem() {
		return embalagem;
	}
	
	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}
	
	public Embalagem getSelecionaEmbalagem() {
		return selecionaEmbalagem;
	}

	public void setSelecionaEmbalagem(Embalagem selecionaEmbalagem) {
		this.selecionaEmbalagem = selecionaEmbalagem;
	}
	
	
	public void excluirEmbalagem(Embalagem embalagem){
		EmbalagemDAO dao = new EmbalagemDAO();
		dao.remove(embalagem);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Embalagem removido com sucesso!", 
              		  ""));
	}
			
	public Embalagem selecionarDados(SelectEvent event) {
   	 Embalagem f = (Embalagem) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Embalagem f = (Embalagem) event.getObject();
    	 setEmbalagem(f); 
     }
	 
	 public void limparDados() {
		 Embalagem f = new Embalagem();
    	 setEmbalagem(f); 
     }
	
	public void adicionarEmbalagem(){
		EmbalagemDAO dao = new EmbalagemDAO();
			if (embalagem.getCodigo() == 0L) {
				dao.save(embalagem);
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Embalagem cadastrado com sucesso!", 
		              		  ""));
			} else {
				dao.update(embalagem);
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Embalagem alterado com sucesso!", 
		              		  ""));
			}
	}


}
