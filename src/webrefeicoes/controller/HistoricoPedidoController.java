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

import org.primefaces.event.SelectEvent;

import webrefeicoes.dao.ClienteDAO;
import webrefeicoes.dao.EmpresaDAO;
import webrefeicoes.dao.HistoricoPedidoDAO;
import webrefeicoes.model.Cliente;
import webrefeicoes.model.Empresa;
import webrefeicoes.model.HistoricoPedido;

@ManagedBean(name = "historicoPedidoController")
@SessionScoped
public class HistoricoPedidoController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HistoricoPedido historicoPedido;
	@SuppressWarnings("rawtypes")
	private DataModel listaHistoricoPedidos;
	private HistoricoPedido selecionaHistoricoPedido;
	int id = 0, idCliente = 0, idEmpresa = 0;
	private List<SelectItem> clientes;
	private List<SelectItem> empresas;
	private Date dataInicial, dataFinal;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataModel getListaHistoricoPedidos() {
		List<HistoricoPedido> lista = new HistoricoPedidoDAO().list(id,idCliente,idEmpresa, dataInicial, dataFinal);
		listaHistoricoPedidos = new ListDataModel(lista);
		return listaHistoricoPedidos;
	}
	
	@SuppressWarnings("deprecation")
	public HistoricoPedidoController() {
		setClientes(new ArrayList<SelectItem>());
		setEmpresas(new ArrayList<SelectItem>());
		historicoPedido = new HistoricoPedido();
		dataInicial = new Date();
		dataFinal = new Date();
		dataInicial.setDate(dataInicial.getDate() - 30);
	}
	
	
	public HistoricoPedido selecionarDados(SelectEvent event) {
   	 HistoricoPedido f = (HistoricoPedido) event.getObject();
   	 return f;
    }
	
	 public void trazerDados(SelectEvent event) {
    	 HistoricoPedido f = (HistoricoPedido) event.getObject();
    	 setHistoricoPedido(f); 
     }
	 
	 public void limparDados() {
		 HistoricoPedido f = new HistoricoPedido();
    	 setHistoricoPedido(f); 
     }
	 
	 
	public void listar() {
		 if(dataInicial.getTime() < dataFinal.getTime()){
			 getListaHistoricoPedidos();
		 } else {
			 FacesContext.getCurrentInstance().addMessage(
		                null, new FacesMessage(
		              		  FacesMessage.SEVERITY_WARN,"Data Inicial não pode ser maior ou igual que Data Final", 
		              		  ""));
		 }
	}
	 
	public HistoricoPedido getHistoricoPedido() {
		return historicoPedido;
	}
		
	public void setHistoricoPedido(HistoricoPedido historicoPedido) {
		this.historicoPedido = historicoPedido;
	}
		
	public HistoricoPedido getSelecionaHistoricoPedido() {
		return selecionaHistoricoPedido;
	}

	public void setSelecionaHistoricoPedido(HistoricoPedido selecionaHistoricoPedido) {
		this.selecionaHistoricoPedido = selecionaHistoricoPedido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public List<SelectItem> getClientes() {
		clientes.clear();
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> listaCliente = dao.list(); 
		 for(Cliente cli : listaCliente){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(cli.getCodigo());  
			 s.setLabel(cli.getNome());  
			 clientes.add(s);  
		 }  
		return clientes;
	}

	public void setClientes(List<SelectItem> clientes) {
		this.clientes = clientes;
	}
	 
	public List<SelectItem> getEmpresas() {
		empresas.clear();
		EmpresaDAO dao = new EmpresaDAO();
		List<Empresa> listaEmpresas = dao.list(); 
		 for(Empresa emp : listaEmpresas){  
			 SelectItem  s = new SelectItem();  
			 s.setValue(emp.getCodigo());  
			 s.setLabel(emp.getNomeFantasia());  
			 empresas.add(s);  
		 }  
		return empresas;
	}

	public void setEmpresas(List<SelectItem> empresas) {
		this.empresas = empresas;
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

}
