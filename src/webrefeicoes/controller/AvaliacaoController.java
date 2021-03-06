package webrefeicoes.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.AvaliacaoDAO;
import webrefeicoes.model.Avaliacao;

@ManagedBean(name = "avaliacaoController")
@SessionScoped	
public class AvaliacaoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Avaliacao avaliacao;
	private DataModel listaAvaliacaos;
	private Avaliacao selecionaAvaliacao;
	
	@ManagedProperty(value = "#{loginController}")
	private LoginController clienteLogado;
	
	public DataModel getListaAvaliacaos() {
		List<Avaliacao> lista = new AvaliacaoDAO().list();
		listaAvaliacaos = new ListDataModel(lista);
		return listaAvaliacaos;
	}
	
	public AvaliacaoController() {
		avaliacao = new Avaliacao();
	}
	
	
	public void excluirAvaliacao(Avaliacao avaliacao){
		AvaliacaoDAO dao = new AvaliacaoDAO();
		dao.remove(avaliacao);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Avaliacao removido com sucesso!", 
              		  ""));
	}
			
	public Avaliacao selecionarDados(SelectEvent event) {
   	 Avaliacao f = (Avaliacao) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Avaliacao f = (Avaliacao) event.getObject();
    	 setAvaliacao(f); 
     }
	 
	 public void limparDados() {
		 Avaliacao f = new Avaliacao();
    	 setAvaliacao(f); 
     }
	
	public void adicionarAvaliacao(){
		AvaliacaoDAO dao = new AvaliacaoDAO();
		avaliacao.setMedia(((avaliacao.getPontuacaoComida() + avaliacao.getPontuacaoEntrega() + avaliacao.getPontuacaoTempo())/3));
		avaliacao.setUsuario(clienteLogado.getCliente().getUsuario());
		avaliacao.setDataEnviada(new Date());
		if(avaliacao.getComentario().trim() == ""){
			avaliacao.setComentario("Não há comentário.");
		}
		dao.save(avaliacao);
		FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Avaliação enviada com sucesso!", 
	              		  ""));
		RequestContext.getCurrentInstance().execute("PF('dlgAvaliacao').hide()");  
	
	}
	
	public LoginController getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(LoginController clienteLogado) {
		this.clienteLogado = clienteLogado;
	}
	
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public Avaliacao getSelecionaAvaliacao() {
		return selecionaAvaliacao;
	}

	public void setSelecionaAvaliacao(Avaliacao selecionaAvaliacao) {
		this.selecionaAvaliacao = selecionaAvaliacao;
	}
	
}
