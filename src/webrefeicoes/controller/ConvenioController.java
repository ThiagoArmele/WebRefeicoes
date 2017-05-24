package webrefeicoes.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.ClienteDAO;
import webrefeicoes.dao.ConvenioDAO;
import webrefeicoes.model.Cliente;
import webrefeicoes.model.Convenio;

@ManagedBean(name = "convenioController")
@SessionScoped
public class ConvenioController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Convenio convenio;
	@ManagedProperty(value = "#{loginController}")
	private LoginController clienteLogado;
	@SuppressWarnings("rawtypes")
	private DataModel listaConvenios;
	private Convenio selecionaConvenio;
	private List<SelectItem> clientes;
	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	private Date dataAtual;
	private boolean atualizaConvenio;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListaConvenios() {
		List<Convenio> lista = new ConvenioDAO().list();
		listaConvenios = new ListDataModel(lista);
		return listaConvenios;
	}
	
	public ConvenioController() {
		convenio = new Convenio();
		convenio.setDataFinal(new Date());
		convenio.setDataInicial(new Date());
		setClientes(new ArrayList<SelectItem>());
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
		setDataAtual(new Date());
		System.out.println(dataAtual);
	}
	
	public void verificarConvenio() {
		ConvenioDAO dao = new ConvenioDAO();
		
		Convenio cliente = dao.getConvenio(clienteLogado.getCliente().getCodigo());
		if(cliente != null){
			if(cliente.getDataFinal().before(dataAtual)){
				cliente.setStatusConvenio("Fechado");
				dao.update(cliente);
			} 
		}
		
	}

	public Convenio getConvenio() {
		return convenio;
	}
	
	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}
	
	public Convenio getSelecionaConvenio() {
		return selecionaConvenio;
	}

	public void setSelecionaConvenio(Convenio selecionaConvenio) {
		this.selecionaConvenio = selecionaConvenio;
	}
	
	
	public void excluirConvenio(Convenio convenio){
		ConvenioDAO dao = new ConvenioDAO();
		dao.remove(convenio);
		FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(
              		  FacesMessage.SEVERITY_INFO,"Convenio removido com sucesso!", 
              		  ""));
	}
			
	public Convenio selecionarDados(SelectEvent event) {
   	 Convenio f = (Convenio) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 Convenio f = (Convenio) event.getObject();
    	 setConvenio(f); 
     }
	 
	 public void limparDados() {
		 Convenio f = new Convenio();
    	 setConvenio(f); 
     }
	
	public void adicionarConvenio(){
		ConvenioDAO dao = new ConvenioDAO();
		Calendar c = Calendar.getInstance(); 
		convenio.setDataFinal(convenio.getDataInicial());
		c.setTime(convenio.getDataFinal()); 
		
		if(convenio.getTipoConvenio().equals("Mensal")) {
			c.add(Calendar.DATE, 31);
			convenio.setDataFinal(c.getTime());
		} else {
			c.add(Calendar.DATE, 15);
			convenio.setDataFinal(c.getTime());
		}
		
		if (convenio.getCodigo() == 0L) {
			
			Convenio cliente = new ConvenioDAO().getConvenio(convenio.getIdCliente());
			
			if(cliente == null){
				ClienteDAO clienteDao = new ClienteDAO();
				List<Cliente> listaClientes = clienteDao.list();
				for (Cliente clienteConvenio : listaClientes) {
					if(clienteConvenio.getCodigo() == convenio.getIdCliente()){
						convenio.setNomeCliente(clienteConvenio.getNome());
						break;
					}
				}
				convenio.setStatusConvenio("Aberto");
				dao.save(convenio);
				limparDados();
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_INFO,"Convenio cadastrado com sucesso!", 
		              		  ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_ERROR,"Já existe esse cliente com convênio!", 
		              		  ""));
			}
			
		} else {
			dao.update(convenio);
			limparDados();
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Convenio alterado com sucesso!", 
	              		  ""));
		}
	}
	
	public List<SelectItem> getClientes() {
		clientes.clear();
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> listaClientes = dao.list();
		for (Cliente cliente : listaClientes) {
			SelectItem s = new SelectItem();
			s.setLabel(cliente.getNome());
			s.setValue(cliente.getCodigo());
			clientes.add(s);
		}
		
		return clientes;
	}

	public void setClientes(List<SelectItem> clientes) {
		this.clientes = clientes;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date date) {
		this.dataAtual = date;
	}

	
	
	public boolean isAtualizaConvenio() {
			verificarConvenio();
			return atualizaConvenio;
	}

	public void setAtualizaConvenio(boolean atualizaConvenio) {
		this.atualizaConvenio = atualizaConvenio;
	}

	public LoginController getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(LoginController clienteLogado) {
		this.clienteLogado = clienteLogado;
	}
	
	
}
