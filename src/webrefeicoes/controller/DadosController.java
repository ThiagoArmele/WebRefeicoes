package webrefeicoes.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import webrefeicoes.dao.DadosDAO;
import webrefeicoes.model.Pedido;

@ManagedBean(name = "dadosController")
@SessionScoped
public class DadosController {

	DadosDAO dao;
	private BarChartModel barModel;
	private List<SelectItem> anos;
	private int anoSelecionado;
	private ArrayList<Pedido> listaPedidos;
	Calendar cal;
	String[] meses = {"","Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	
	public DadosController() {
		dao = new DadosDAO();
		cal = Calendar.getInstance();
		setAnos(new ArrayList<SelectItem>());
		setListaPedidos(new ArrayList<Pedido>());
		createBarModels();
	}
	
	public void createBarModels() {
		createBarModel();
	}

	public int getTotalAvaliacao() {
		return dao.totalAvaliacoes();
	}
	
	public int getClientes() {
		return dao.totalClientes();
	}
	
	public int getFuncionarios() {
		return dao.totalFuncionarios();
	}
	
	public int getPedidos() {
		return dao.totalPedidos();
	}
	
	public int getConvenio() {
		return dao.totalClientesConveniados();
	}

	public BarChartModel getBarModel() {
		return barModel;
	}
	
	public void listar(){
		createBarModels();
	}
	
	private BarChartModel initBarModel() {
		listaPedidos.clear();
		listaPedidos.addAll(dao.valoresPedidos(getAnoSelecionado()));
		BarChartModel model = new BarChartModel();
		ChartSeries anos = new ChartSeries();
		
		if(getAnoSelecionado() == 0){
			anos.setLabel(" ");
		} else {
			anos.setLabel(""+getAnoSelecionado());
		}
		
		
		for (int i = 1; i < meses.length; i++) {
			double valorTotal = 0;
			for (Pedido pedidoValores : listaPedidos) {
				Calendar calPedido = Calendar.getInstance();
				calPedido.setTime(pedidoValores.getDataPedido());
				if(calPedido.get(Calendar.MONTH)+1 == i){
					valorTotal += pedidoValores.getValorTotal();
				}
			}
			
			anos.set(meses[i], valorTotal);
		}
		model.addSeries(anos);
		model.setLegendPosition("ne");
        model.setShadow(false);
        model.setSeriesColors("dd4b39");
        model.setExtender("customExtender");
		Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(300);
		return model;
		 
	 }
	 
    public void createBarModel() {
        barModel = initBarModel();
    }

	public ArrayList<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public String[] getMeses() {
		return meses;
	}

	public void setMeses(String[] meses) {
		this.meses = meses;
	}

	public List<SelectItem> getAnos() {
		anos.clear();
		int ano = 2000;
		for (int i = 0; i < 50; i++) {
			SelectItem  s = new SelectItem();  
			s.setValue(ano);  
			s.setLabel(ano+"");  
			anos.add(s);  
			ano++;
		}  
		return anos;
	}

	public void setAnos(List<SelectItem> anos) {
		this.anos = anos;
	}

	public int getAnoSelecionado() {
		return anoSelecionado;
	}

	public void setAnoSelecionado(int anoSelecionado) {
		this.anoSelecionado = anoSelecionado;
	}
	     
}
