package webrefeicoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import org.hibernate.Session;
import org.hibernate.Transaction;

import webrefeicoes.dao.BebidaDAO;
import webrefeicoes.dao.CardapioDAO;
import webrefeicoes.dao.EmbalagemDAO;
import webrefeicoes.dao.HibernateUtil;
import webrefeicoes.dao.PedidoDAO;
import webrefeicoes.dao.PratoDAO;
import webrefeicoes.model.BandeiraCartao;
import webrefeicoes.model.Bebida;
import webrefeicoes.model.Embalagem;
import webrefeicoes.model.Pedido;
import webrefeicoes.model.Prato;
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
	private List<Embalagem> listaEmbalagens;
	private String[] guarnicaoSelecionada;  
	private String[] misturaSelecionada;  
	private String[] outroSelecionada;  
	private String[] pratosSelecionados;  
	private String[] bebidasSelecionados;  
	private String[] quiloSelecionado;  
	private List<SelectItem> embalagens;
	private List<SelectItem> pratos;
	private List<Prato> listaPratos;
	private List<Bebida> listaBebidas;
	private List<SelectItem> bebidasRefrigerantes;
	private List<SelectItem> bebidasSucos;
	private List<SelectItem> bebidasAgua;
	private List<SelectItem> quilos;
	private List<SelectItem> cartoes;
	@ManagedProperty(value = "#{loginController}")
	private LoginController clienteLogado;
	
//	Variaveis globais para armazenar qualquer produto selecionado no cardapio
	String guarnicoes = "";
	String misturas = "";
	String outros = "";
	String prato = "";
	String bebidas = "";
	String quilo = "";
	private Double valorTotal = 0.00;
	
	public CardapioController() {
		produto = new Produto();
		pedido = new Pedido();
		
		setBebidasRefrigerantes(new ArrayList<SelectItem>());
		setProdutoGuarnicao(new ArrayList<SelectItem>());
		setProdutoMisturas(new ArrayList<SelectItem>());
		setProdutoOutros(new ArrayList<SelectItem>());
		setBebidasSucos(new ArrayList<SelectItem>());
		setBebidasAgua(new ArrayList<SelectItem>());
		setEmbalagens(new ArrayList<SelectItem>());
		setCartoes(new ArrayList<SelectItem>());
		setPratos(new ArrayList<SelectItem>());
		setQuilos(new ArrayList<SelectItem>());
	
		inicilizarListaEmbalagens();
		iniciliazaListaPratos();
		inicilizarListaBebidas();
	}
	
	public void inicilizarListaBebidas() {
		BebidaDAO dao = new BebidaDAO();
		setListaBebidas(dao.list());
	}
	
	
	public void inicilizarListaEmbalagens() {
		EmbalagemDAO dao = new EmbalagemDAO();
		setListaEmbalagens(dao.list());
	}
	
	public void iniciliazaListaPratos() {
		PratoDAO dao = new PratoDAO();
		setListaPratos(dao.list());
	}

	public void resetarValores() {
		guarnicoes = "";
		misturas = "";
		outros = "";
		prato = "";
		bebidas = "";
		quilo = "";
		valorTotal = 0.00;
	}
	
	public void adicionarPedido(){
		PedidoDAO dao = new PedidoDAO();
		resetarValores();
		validaProdutosSelecionados();
		if (verificaMarmita(guarnicaoSelecionada, misturaSelecionada, outroSelecionada, pedido) && !pedido.getFormaPagamento().equals(" ")){
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
			
			if(prato != null){
				pedido.setDescricaoPrato(prato);
			} else {
				pedido.setDescricaoPrato(null);
			}
			
			if(bebidas != null){
				pedido.setBebida(bebidas);
			} else {
				pedido.setBebida(null);
			}
			
			if(quilo != null){
				pedido.setQuilo(quilo);
			} else {
				pedido.setQuilo(null);
			}
			
			pedido.setStatusPedido("Aguardando Visualização");
			
			pedido.setValorTotal(geraValorTotal());
			pedido.setDataPedido(new Date());
			
			if (clienteLogado.getCliente().getCodigo() != 0) {
				pedido.setCodigoCliente(clienteLogado.getCliente().getCodigo());
				pedido.setEnderecoEntrega(clienteLogado.getCliente().getEnderecoEntrega());
			}
			
			dao.save(pedido);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Pedido enviado com sucesso!", 
	              		  ""));
		}else {
			if((guarnicaoSelecionada.length > 0 || misturaSelecionada.length > 0|| outroSelecionada.length > 0) 
					&& (pedido.getIdEmbalagem() == null)) {
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_ERROR,"Você deve informar qual tamanho da marmitex", 
		              		  ""));
			} else if((pedido.getIdEmbalagem() != null) && ((guarnicaoSelecionada.length == 0 && misturaSelecionada.length == 0 && outroSelecionada.length == 0))){
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_WARN,"Você deve informar o que deseja colocar na marmitex.", 
		              		  ""));
			} else if(pedido.getFormaPagamento().equals(" ")) {
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_WARN,"Você deve informar a forma de pagamento", 
		              		  ""));
			}
		}
	}
	
	
	public void validaProdutosSelecionados() {
		
		if(pratosSelecionados != null) {
			for (String p : pratosSelecionados) {
				prato += p + " / ";
			}
		}
		
		if(bebidasSelecionados != null) {
			for (String b : bebidasSelecionados) {
				bebidas += b + " / ";
			}
		}
		
		if(guarnicaoSelecionada != null) {
			for (String g : guarnicaoSelecionada) {
				guarnicoes +=  g + " / ";
			}
		}
		
		if(misturaSelecionada != null) {
			for (String m : misturaSelecionada) {
				misturas +=  m + " / ";
			}
		}
		
		if (outroSelecionada != null) {
			for (String o : outroSelecionada) {
				outros +=  o + " / ";
			}
		}
		
		if (quiloSelecionado != null) {
			for (String q : quiloSelecionado) {
				quilo +=  q + " / ";
			}
		}
		
	}
	
	public boolean verificaMarmita(String[] guarnicaoSelecionada, String[] misturaSelecionada, String[] outros, Pedido pedido) {
		
		if(pedido.getIdEmbalagem() != null) {
			if(guarnicaoSelecionada.length > 0 || misturaSelecionada.length > 0 || outros.length > 0) {
				for(Embalagem emb : listaEmbalagens){  
					 if(emb.getCodigo() == pedido.getIdEmbalagem()) {
						 if(guarnicaoSelecionada.length <= emb.getQtdeGuarnicao()){
							 if(misturaSelecionada.length > emb.getQtdeMistura()) {
								 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					              		  FacesMessage.SEVERITY_WARN,"Tamanho " + emb.getTamanho() + " só pode conter no máximo "
					              		   + emb.getQtdeMistura()+" Misturas", ""));
								 return false;
							 }
						 } else {
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
			              		  FacesMessage.SEVERITY_WARN,"Tamanho " + emb.getTamanho() + " só pode conter no máximo "
			              		   + emb.getQtdeGuarnicao() 
			              		   +" Guarnições", ""));
							return false;
					 	}			
					 } 	
				 }
			} else {
				return false;
			}
			
		}
		
		if(pedido.getIdEmbalagem() != null || guarnicaoSelecionada.length > 0 ||  pratosSelecionados.length > 0 || bebidasSelecionados.length > 0||
 				misturaSelecionada.length > 0 || outros.length > 0 || quiloSelecionado.length > 0 ||pedido.getValorQuilo() != null) {
			
			if(pedido.getValorQuilo() == null && quiloSelecionado.length > 0) {
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_ERROR,"Para enviar o que foi escolhido no quilo precisa informar um valor no campo 'Valor'.", 
		              		  ""));
				return false;
			}
			
			if(pedido.getValorQuilo() != null && quiloSelecionado.length == 0) {
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_ERROR,"Para enviar o pedido por favor escolha um dos itens do quilo no cardápio.", 
		              		  ""));
				return false;
			}
			
			return true;
		} else {
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_ERROR,"Você deve escolher algo do cardápio ", 
	              		  ""));
			return false;
		}
	}
	
	public Double calcularValorPedido(Pedido pedido) {
		if(verificaMarmita(guarnicaoSelecionada, misturaSelecionada, outroSelecionada, pedido)) {
			if(pedido.getIdEmbalagem() != null) {
				for(Embalagem emb : listaEmbalagens){  
					 if(pedido.getIdEmbalagem() == emb.getCodigo()) {
						 return emb.getPreco();
					 }
				 }
			}
		}
		return 0.0;
	}
	
	public Double geraValorTotal() {
		resetarValores();
		
		if(pedido != null) {
			setValorTotal(calcularValorPedido(pedido));
		}
		
		if(pratosSelecionados != null) {
			setValorTotal(calculaValorPrato(pratosSelecionados));
		}
		
		if(bebidasSelecionados != null) {
			setValorTotal(calculaValorBebida(bebidasSelecionados));
		}
		
		if(pedido.getValorQuilo() != null){
			setValorTotal(pedido.getValorQuilo());
		}
		
		return getValorTotal();
		
	}
	
	
	public Double calculaValorBebida(String[] bebidasSelecionados) {
		Double valorBebidas = 0.0;
		for (int i = 0; i < bebidasSelecionados.length; i++) {
			for (Bebida bebida : listaBebidas) {
				if(bebidasSelecionados[i].equals(bebida.getDescricao()+bebida.getTamanho())){
					valorBebidas += bebida.getPreco();
				}
			}
		}
		return valorBebidas;
	}
	
	public Double calculaValorPrato(String[] pratosSelecionados) {
		Double valorPedidos = 0.0;
		for (int i = 0; i < pratosSelecionados.length; i++) {
			for (Prato prato : listaPratos) {
				if(pratosSelecionados[i].equals(prato.getDescricao())){
					if(prato.getPrecoDesconto() != null) {
							valorPedidos += prato.getPrecoDesconto();
						} else {
							valorPedidos += prato.getPreco();
					 }
				}
			}
		}
		return valorPedidos;
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
		 for(Embalagem emb : listaEmbalagens){  
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

	public List<Embalagem> getListaEmbalagens() {
		return listaEmbalagens;
	}

	public void setListaEmbalagens(List<Embalagem> listaEmbalagens) {
		this.listaEmbalagens = listaEmbalagens;
	}

	public List<SelectItem> getPratos() {
		pratos.clear();
		 for(Prato prato : listaPratos){  
			 SelectItem  s = new SelectItem();  
			 
			 String descricaoPreço;
			 if(prato.getPrecoDesconto() == null){
				 descricaoPreço = "<span style='padding-left: 15px;font-size: 17px;color: #129133;'>R$" + String.format("%.2f",prato.getPreco())+"</span>";
			 } else {
				 descricaoPreço = "<span style='padding-left: 15px;font-size: 17px;color: #129133'>" 
						 + "<span>R$" + String.format("%.2f", prato.getPrecoDesconto()) + "</span></span>"
						 + "<span style='    color: red;text-decoration: line-through;padding-left: 15px'>R$" + String.format("%.2f",prato.getPreco()) + "</span>";
			 }
			 
			 s.setValue(prato.getDescricao());  
			 s.setLabel("<span style='width: 100%;float: left;'>" + prato.getDescricao() + "<span style='    float: right;'>"+ descricaoPreço + "</span>" + "</span>"
			 		+ "<span style='font-weight: normal;text-transform: uppercase;font-style: italic;'>"
			 		+ "(" + prato.getComposicaoProduto() + ")</span>");
			 pratos.add(s);  
		 }  
		return pratos;
	}

	public void setPratos(List<SelectItem> pratos) {
		this.pratos = pratos;
	}

	public List<Prato> getListaPratos() {
		return listaPratos;
	}

	public void setListaPratos(List<Prato> listaPratos) {
		this.listaPratos = listaPratos;
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<SelectItem> getBebidasRefrigerantes() {
		bebidasRefrigerantes.clear();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Bebida> listaRefrigerantes = session.createQuery("from Bebida b where b.tipo = 'refrigerante'").list();
		
		for(Bebida bebida : listaRefrigerantes){  
			 SelectItem  s = new SelectItem();  
			 
			 s.setValue(bebida.getDescricao()  + bebida.getTamanho());  
			 s.setLabel("<span style='width: 100%;float: left;text-transform: uppercase;'>" + bebida.getDescricao() 
			 			+ "<span style='float: right;padding-left: 15px;color: #129133;'> R$" + String.format("%.2f",bebida.getPreco()) + "</span>"
			 			+ "<span style='float: right'>" + bebida.getTamanho() + "</span></span>"
					 );  
			 bebidasRefrigerantes.add(s);  
		 }  
		SelectItemGroup grupoRefri = new SelectItemGroup("Refrigerantes");
		return bebidasRefrigerantes;
	}

	public void setBebidasRefrigerantes(List<SelectItem> bebidas) {
		this.bebidasRefrigerantes = bebidas;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public List<SelectItem> getBebidasSucos() {
		bebidasSucos.clear();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Bebida> listaSucos =  session.createQuery("from Bebida b where b.tipo = 'suco'").list();
		
		for(Bebida bebida : listaSucos){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(bebida.getDescricao()  + bebida.getTamanho());  
			 s.setLabel("<span style='width: 100%;float: left;text-transform: uppercase;'>" + bebida.getDescricao() 
	 			+ "<span style='float: right;padding-left: 15px;color: #129133;'> R$" + String.format("%.2f",bebida.getPreco()) + "</span>"
	 			+ "<span style='float: right'>" + bebida.getTamanho() + "</span></span>"
	 			);  
			 bebidasSucos.add(s);  
		 }  
		 
		return bebidasSucos;
	}

	public void setBebidasSucos(List<SelectItem> bebidasSucos) {
		this.bebidasSucos = bebidasSucos;
	}

	@SuppressWarnings("unused")
	public List<SelectItem> getBebidasAgua() {
		
		bebidasAgua.clear();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Bebida> listaAgua =  session.createQuery("from Bebida b where b.tipo = 'agua'").list();
		
		for(Bebida bebida : listaAgua){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(bebida.getDescricao()  + bebida.getTamanho());  
			 s.setLabel("<span style='width: 100%;float: left;text-transform: uppercase;'>" + bebida.getDescricao() 
	 			+ "<span style='float: right;padding-left: 15px;color: #129133;'> R$" + String.format("%.2f",bebida.getPreco()) + "</span>"
	 			+ "<span style='float: right'>" + bebida.getTamanho() + "</span></span>"
	 			);
			 bebidasAgua.add(s);  
		 }  
		
		return bebidasAgua;
	}

	public void setBebidasAgua(List<SelectItem> bebidasAgua) {
		this.bebidasAgua = bebidasAgua;
	}

	public String[] getPratosSelecionados() {
		return pratosSelecionados;
	}

	public void setPratosSelecionados(String[] pratosSelecionados) {
		this.pratosSelecionados = pratosSelecionados;
	}

	public String[] getBebidasSelecionados() {
		return bebidasSelecionados;
	}

	public void setBebidasSelecionados(String[] bebidasSelecionados) {
		this.bebidasSelecionados = bebidasSelecionados;
	}

	public List<Bebida> getListaBebidas() {
		return listaBebidas;
	}

	public void setListaBebidas(List<Bebida> listaBebidas) {
		this.listaBebidas = listaBebidas;
	}

	public String[] getQuiloSelecionado() {
		return quiloSelecionado;
	}

	public void setQuiloSelecionado(String[] quiloSelecionado) {
		this.quiloSelecionado = quiloSelecionado;
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	public List<SelectItem> getQuilos() {
		quilos.clear();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Produto> listaQuilo =  session.createQuery("from Produto p where p.noQuilo = 'sim'").list();
		for(Produto quilo : listaQuilo){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(quilo.getDescricao());  
			 s.setLabel(quilo.getDescricao());
			 quilos.add(s);  
		 }  
		
		return quilos;
	}

	public void setQuilos(List<SelectItem> quilos) {
		this.quilos = quilos;
	}

	
	public LoginController getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(LoginController clienteLogado) {
		this.clienteLogado = clienteLogado;
	}

	public List<SelectItem> getCartoes() {
		cartoes.clear();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<BandeiraCartao> listaCartoes =  session.createQuery("from BandeiraCartao b where b.status = 'disponivel'").list();
		for(BandeiraCartao quilo : listaCartoes){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(quilo.getDescricaoBandeira());  
			 s.setLabel(quilo.getDescricaoBandeira());
			 cartoes.add(s);  
		 }  
		
		return cartoes;
	}

	public void setCartoes(List<SelectItem> cartoes) {
		
		this.cartoes = cartoes;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal += valorTotal;
	}
	
	

	
}
