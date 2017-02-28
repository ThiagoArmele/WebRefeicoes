package webrefeicoes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="convenio")
public class Convenio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="idConvenio")
	private int codigo;
	
	@Column(name="idCliente")
	private Integer idCliente;
	
	@Column(name="dataInicial")
	private Date dataInicial;
	
	@Column(name="dataFinal")
	private Date dataFinal;
	
	@Column(name="precoTotal")
	private Double precoTotal;
	
	@Column(name="statusConvenio")
	private String statusConvenio;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
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

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getStatusConvenio() {
		return statusConvenio;
	}

	public void setStatusConvenio(String statusConvenio) {
		this.statusConvenio = statusConvenio;
	}

	public Convenio(int codigo, Integer idCliente, Date dataInicial, Date dataFinal, Double precoTotal,
			String statusConvenio) {
		super();
		this.codigo = codigo;
		this.idCliente = idCliente;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.precoTotal = precoTotal;
		this.statusConvenio = statusConvenio;
	}

	
	public Convenio() {
		// TODO Auto-generated constructor stub
	}

}
