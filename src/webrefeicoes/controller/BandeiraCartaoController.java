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

import webrefeicoes.dao.BandeiraCartaoDAO;
import webrefeicoes.model.BandeiraCartao;

@ManagedBean(name = "bandeiraCartaoController")
@SessionScoped
public class BandeiraCartaoController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BandeiraCartao bandeiraCartao;
	@SuppressWarnings("rawtypes")
	private DataModel listaBandeiraCartoes;
	private BandeiraCartao selecionaBandeiraCartao;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListaBandeiraCartoes() {
		List<BandeiraCartao> lista = new BandeiraCartaoDAO().list();
		listaBandeiraCartoes = new ListDataModel(lista);
		return listaBandeiraCartoes;
	}
	
	public BandeiraCartaoController() {
		bandeiraCartao = new BandeiraCartao();
	}
	
	public BandeiraCartao getBandeiraCartao() {
		return bandeiraCartao;
	}
	
	public void setBandeiraCartao(BandeiraCartao bandeira) {
		this.bandeiraCartao = bandeira;
	}
	
	public BandeiraCartao getSelecionaBandeiraCartao() {
		return selecionaBandeiraCartao;
	}

	public void setSelecionaBandeiraCartao(BandeiraCartao selecionaBandeiraCartao) {
		this.selecionaBandeiraCartao = selecionaBandeiraCartao;
	}
	
	
	public void excluirBandeiraCartao(BandeiraCartao bandeira){
		BandeiraCartaoDAO dao = new BandeiraCartaoDAO();
		dao.remove(bandeira);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Bandeira removido com sucesso!", 
              		  ""));
	}
			
	public BandeiraCartao selecionarDados(SelectEvent event) {
   	 BandeiraCartao f = (BandeiraCartao) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 BandeiraCartao f = (BandeiraCartao) event.getObject();
    	 setBandeiraCartao(f); 
     }
	 
	 public void limparDados() {
		 BandeiraCartao f = new BandeiraCartao();
    	 setBandeiraCartao(f); 
     }
	
	public void adicionarBandeiraCartao(){
		BandeiraCartaoDAO dao = new BandeiraCartaoDAO();
		
		if (bandeiraCartao.getCodigo() == 0L) {
			dao.save(bandeiraCartao);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Bandeira cadastrado com sucesso!", 
	              		  ""));
		} else {
			dao.update(bandeiraCartao);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Bandeira alterado com sucesso!", 
	              		  ""));
		}
	
	}
}
