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

import webrefeicoes.dao.ProdutoDAO;
import webrefeicoes.model.Produto;

@ManagedBean(name = "produtoController")
@SessionScoped
public class ProdutoController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	
	private DataModel listaProdutos;
	private Produto selecionaProduto;
	
	
	public DataModel getListaProdutos() {
		List<Produto> lista = new ProdutoDAO().list();
		listaProdutos = new ListDataModel(lista);
		return listaProdutos;
	}
	
	public ProdutoController() {
		produto = new Produto();
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Produto getSelecionaProduto() {
		return selecionaProduto;
	}

	public void setSelecionaProduto(Produto selecionaProduto) {
		this.selecionaProduto = selecionaProduto;
	}
	
	
	public void excluirProduto(Produto produto){
		ProdutoDAO dao = new ProdutoDAO();
		dao.remove(produto);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Produto removido com sucesso!", 
              		  ""));
	}
			
	public Produto selecionarDados(SelectEvent event) {
   	 Produto f = (Produto) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Produto f = (Produto) event.getObject();
    	 setProduto(f); 
     }
	 
	 public void limparDados() {
		 Produto f = new Produto();
    	 setProduto(f); 
     }
	
	public void adicionarProduto(){
		ProdutoDAO dao = new ProdutoDAO();
		
		if (produto.getCodigo() == 0L) {
			dao.save(produto);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Produto cadastrado com sucesso!", 
	              		  ""));
		} else {
			dao.update(produto);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Produto alterado com sucesso!", 
	              		  ""));
		}
	
	}
}
