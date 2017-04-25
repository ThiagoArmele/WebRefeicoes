package webrefeicoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
	@SuppressWarnings("rawtypes")
	private DataModel listaConvenios;
	private Convenio selecionaConvenio;
	private List<SelectItem> clientes;
	private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("WebRefeicoes");
	private EntityManager em = factory.createEntityManager();
	private String nomeCliente;
	
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
	
	@SuppressWarnings("deprecation")
	public void adicionarConvenio(){
		ConvenioDAO dao = new ConvenioDAO();
		
		if (convenio.getCodigo() == 0L) {
			
			if(convenio.getTipoConvenio().equals("Mensal")) {
				convenio.getDataFinal().setDate(convenio.getDataInicial().getDate() + 31);
			} else {
				convenio.getDataFinal().setDate(convenio.getDataInicial().getDate() + 15);
			}
				
			
			
			dao.save(convenio);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Convenio cadastrado com sucesso!", 
	              		  ""));
		} else {
			convenio.setDataFinal(convenio.getDataInicial());
			if(convenio.getTipoConvenio().equals("Mensal")) {
				convenio.getDataFinal().setDate(convenio.getDataInicial().getDate() + 31);
			} else {
				convenio.getDataFinal().setDate(convenio.getDataInicial().getDate() + 15);
			}
			System.out.println(convenio.getDataInicial());
			dao.update(convenio);
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

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
