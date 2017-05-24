package webrefeicoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import webrefeicoes.dao.BebidaDAO;
import webrefeicoes.dao.ClienteDAO;
import webrefeicoes.dao.ConvenioDAO;
import webrefeicoes.dao.GerenciarConvenioDAO;
import webrefeicoes.model.Cliente;
import webrefeicoes.model.Convenio;


@ManagedBean(name = "gerenciarConvenioController")
@SessionScoped	
public class GerenciarConvenioController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Convenio convenio;
	@SuppressWarnings("rawtypes")
	private DataModel listaConvenios;
	private Convenio selecionaConvenio;
	private int codigo, idCliente;
	private String tipoConvenio, statusConvenio;
	private Date dataInicial, dataFinal;
	private List<SelectItem> clientes;
	
	private Date dataInicialAlterado, dataFinalAlterado;
	private String statusConvenioAlterado;
	private String tipoConvenioAlterado;
	
	@SuppressWarnings("deprecation")
	public GerenciarConvenioController() {
		convenio = new Convenio();
		setClientes(new ArrayList<SelectItem>());
		dataInicial = new Date();
		dataFinal = new Date();
		dataInicial.setDate(dataInicial.getDate() - 30);
		
	}
	
	
	public void listar() {
		 if(dataInicial.getTime() < dataFinal.getTime()){
			 getListaConvenios();
		 } else {
			 FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_WARN,"Data Inicial não pode ser maior ou igual que Data Final", 
		              		  ""));
		 }
	}
	
	public void abrirModal(Convenio convenio){
		RequestContext.getCurrentInstance().execute("PF('dlgAlterarConvenio').show()");  
		this.convenio = convenio;
	}
	
	public void alterarConvenio(){
		ConvenioDAO dao = new ConvenioDAO();
		convenio.setStatusConvenio(statusConvenioAlterado);
		convenio.setTipoConvenio(tipoConvenioAlterado);
		
		Calendar c = Calendar.getInstance(); 
		
		if(getDataInicialAlterado() != null){
			setDataFinalAlterado(getDataInicialAlterado());
			c.setTime(getDataFinalAlterado()); 
			
			convenio.setDataInicial(dataInicialAlterado);
			
			if(convenio.getTipoConvenio().equals("Mensal")) {
				c.add(Calendar.DATE, 31);
				convenio.setDataFinal(c.getTime());
			} else {
				c.add(Calendar.DATE, 15);
				convenio.setDataFinal(c.getTime());
			}
			
			dao.update(convenio);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Alterado com sucesso!", 
	              		  ""));
		} else {
			c.setTime(convenio.getDataFinal()); 
			
			if(convenio.getTipoConvenio().equals("Mensal")) {
				c.add(Calendar.DATE, 30);
				convenio.setDataFinal(c.getTime());
			} else {
				c.add(Calendar.DATE, 15);
				convenio.setDataFinal(c.getTime());
			}
			
			dao.update(convenio);
			FacesContext.getCurrentInstance().addMessage(
	                null, new FacesMessage(
	              		  FacesMessage.SEVERITY_INFO,"Alterado com sucesso!", 
	              		  ""));
		}
	}
	 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListaConvenios() {
		List<Convenio> lista = new GerenciarConvenioDAO().list(getCodigo(), getIdCliente(), 
				getStatusConvenio(), getTipoConvenio(), getDataInicial(), getDataFinal());
		listaConvenios = new ListDataModel(lista);
		return listaConvenios;
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getTipoConvenio() {
		return tipoConvenio;
	}

	public void setTipoConvenio(String tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}

	public String getStatusConvenio() {
		return statusConvenio;
	}

	public void setStatusConvenio(String statusConvenio) {
		this.statusConvenio = statusConvenio;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
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


	public Date getDataInicialAlterado() {
		return dataInicialAlterado;
	}


	public void setDataInicialAlterado(Date dataInicialAlterado) {
		this.dataInicialAlterado = dataInicialAlterado;
	}


	public Date getDataFinalAlterado() {
		return dataFinalAlterado;
	}


	public void setDataFinalAlterado(Date dataFinalAlterado) {
		this.dataFinalAlterado = dataFinalAlterado;
	}


	public String getStatusConvenioAlterado() {
		return statusConvenioAlterado;
	}


	public void setStatusConvenioAlterado(String statusConvenioAlterado) {
		this.statusConvenioAlterado = statusConvenioAlterado;
	}


	public String getTipoConvenioAlterado() {
		return tipoConvenioAlterado;
	}


	public void setTipoConvenioAlterado(String tipoConvenioAlterado) {
		this.tipoConvenioAlterado = tipoConvenioAlterado;
	}
	
	
	
}
