package webrefeicoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.hibernate.Session;

import webrefeicoes.dao.CardapioDAO;
import webrefeicoes.dao.EmbalagemDAO;
import webrefeicoes.dao.HibernateUtil;
import webrefeicoes.dao.PedidoDAO;
import webrefeicoes.model.Embalagem;
import webrefeicoes.model.Pedido;
import webrefeicoes.model.Produto;


@ManagedBean(name = "cardapioController")
@SessionScoped
public class CardapioController  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Produto produto;
	private List<SelectItem> produtoGuarnicao;
	private List<SelectItem> produtoMisturas;
	private List<SelectItem> produtoOutros;
	private String selecionados;
	private Pedido pedido;
	private String[] guarnicaoSelecionada;  
	private String[] misturaSelecionada;  
	private String[] outroSelecionada;  
	private List<SelectItem> embalagens;
	
	public CardapioController() {
		produto = new Produto();
		pedido = new Pedido();
		setProdutoGuarnicao(new ArrayList<SelectItem>());
		setProdutoMisturas(new ArrayList<SelectItem>());
		setProdutoOutros(new ArrayList<SelectItem>());
		setEmbalagens(new ArrayList<SelectItem>());
	}

	@SuppressWarnings("unused")
	public void adicionarPedido(){
		PedidoDAO dao = new PedidoDAO();
		String guarnicoes = "";
		String misturas = "";
		String outros = "";
		Double valorTotal = 0.0;
		
		for (String g : guarnicaoSelecionada) {
			guarnicoes +=  g + " / ";
		}
		
		for (String m : misturaSelecionada) {
			misturas +=  m + " / ";
		}
		
		for (String o : outroSelecionada) {
			outros +=  o + " / ";
		}
		
		if(guarnicoes != "" ||  misturas != "" || outros != "") {
			
			if (verificaMarmita(guarnicaoSelecionada, misturaSelecionada, pedido )) {
				
				if(guarnicoes != null) {
					pedido.setGuarnicao(guarnicoes);
				} else {
					pedido.setGuarnicao(null);
				}
			
				if(misturas != null) {
					pedido.setMistura(misturas);
				} else {
					pedido.setMistura(null);
				}
				 
				if(outros != null) {
					pedido.setOutro(outros);
				} else {
					pedido.setOutro(null);
				}
				
				pedido.setStatusPedido("Aguardando Visualização");
				
				valorTotal += calcularValorPedido(pedido);
				pedido.setValorTotal(valorTotal);
				dao.save(pedido);
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Pedido enviado com sucesso!", 
		              		  ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_ERROR,"Deve ser selecionado ao menos um item das opções!", 
	              		  ""));
		}
		
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<SelectItem> getProdutoGuarnicao() {
		produtoGuarnicao.clear();
		CardapioDAO dao = new CardapioDAO();
		List<Produto> listaProdutos = dao.getProdutoGuarnicao("Guarnicao");
		 for(Produto pro : listaProdutos){  
			 SelectItem  s = new SelectItem();  
			 
			 s.setValue(pro.getDescricao());  
			 s.setLabel(pro.getDescricao());  
			 produtoGuarnicao.add(s);  
		 }  
		 
		return produtoGuarnicao;
	}

	public void setProdutoGuarnicao(List<SelectItem> produtoGuarnicao) {
		this.produtoGuarnicao = produtoGuarnicao;
	}

	public String[] getGuarnicaoSelecionada() {
		return guarnicaoSelecionada;
	}

	public void setGuarnicaoSelecionada(String[] guarnicaoSelecionada) {
		this.guarnicaoSelecionada = guarnicaoSelecionada;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(String selecionados) {
		this.selecionados = selecionados;
	}

	public String[] getMisturaSelecionada() {
		return misturaSelecionada;
	}

	public void setMisturaSelecionada(String[] misturaSelecionada) {
		this.misturaSelecionada = misturaSelecionada;
	}

	public List<SelectItem> getProdutoMisturas() {
		produtoMisturas.clear();
		CardapioDAO dao = new CardapioDAO();
		List<Produto> listaProdutos = dao.getProdutoGuarnicao("Mistura");
		 for(Produto pro : listaProdutos){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(pro.getDescricao());  
			 s.setLabel(pro.getDescricao());  
			 produtoMisturas.add(s);  
		 }  
		 
		return produtoMisturas;
	}

	public void setProdutoMisturas(List<SelectItem> produtoMisturas) {
		this.produtoMisturas = produtoMisturas;
	}

	public String[] getOutroSelecionada() {
		return outroSelecionada;
	}

	public void setOutroSelecionada(String[] outroSelecionada) {
		this.outroSelecionada = outroSelecionada;
	}

	public List<SelectItem> getProdutoOutros() {
		produtoOutros.clear();
		CardapioDAO dao = new CardapioDAO();
		List<Produto> listaProdutos = dao.getProdutoGuarnicao("Outros");
		 for(Produto pro : listaProdutos){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(pro.getDescricao());  
			 s.setLabel(pro.getDescricao());  
			 produtoOutros.add(s);  
		 }  
		return produtoOutros;
	}

	public void setProdutoOutros(List<SelectItem> produtoOutros) {
		this.produtoOutros = produtoOutros;
	}

	public List<SelectItem> getEmbalagens() {
		embalagens.clear();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("from Embalagem");
		List<Embalagem> listaEmbalagem = q.list();
		 for(Embalagem emb : listaEmbalagem){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(emb.getCodigo());  
			 s.setLabel(emb.getTamanho());  
			 embalagens.add(s);  
		 }  
		return embalagens;
	}

	public void setEmbalagens(List<SelectItem> embalagens) {
		this.embalagens = embalagens;
	}

	public boolean verificaMarmita(String[] guarnicaoSelecionada, String[] misturaSelecionada, Pedido pedido) {
		EmbalagemDAO dao = new EmbalagemDAO();
		List<Embalagem> listaEmbalagem = dao.list();
		 for(Embalagem emb : listaEmbalagem){  
			 if(emb.getCodigo() == pedido.getIdEmbalagem()) {
				 if(guarnicaoSelecionada.length <= emb.getQtdeGuarnicao()){
					 if(misturaSelecionada.length <= emb.getQtdeMistura()) {
						 return true;
					 } else {
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
			              		  FacesMessage.SEVERITY_WARN,"Tamanho " + emb.getTamanho() + " só pode conter no máximo "
			              		   + emb.getQtdeMistura()+" Misturas", ""));
							break;
					 }
				 } else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
	              		  FacesMessage.SEVERITY_WARN,"Tamanho " + emb.getTamanho() + " só pode conter no máximo "
	              		   + emb.getQtdeGuarnicao() 
	              		   +" Guarnições", ""));
					break;
			 	}			
			 } 	
			 
		 }
		return false;
	}
	
	public Double calcularValorPedido(Pedido pedido) {
		EmbalagemDAO dao = new EmbalagemDAO();
		List<Embalagem> listaEmbalagem = dao.list();
		 for(Embalagem emb : listaEmbalagem){  
			 if(pedido.getIdEmbalagem() == emb.getCodigo()) {
				 return emb.getPreco();
			 }
		 }
		return 0.0;
	}
}
