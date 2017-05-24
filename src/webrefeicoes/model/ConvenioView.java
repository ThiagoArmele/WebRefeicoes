package webrefeicoes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="convenioView")
public class ConvenioView implements Serializable{

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
	
	@Column(name="tipoConvenio")
	private String tipoConvenio;

	@Column (name="nome")
	private String nome;
	
	@Column (name="dddTelefone")
	private String dddTelefone;
	
	@Column (name="telefone")
	private String telefone;
	
	@Column (name="dddCelular")
	private String dddCelular;
	
	@Column (name="celular")
	private String celular;
	
	@Column (name="usuario")
	private String usuario;
	
	@Column (name="email", unique = true)
	private String email;
	
	@Column (name="cpf")
	private String cpf;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	public String getTipoConvenio() {
		return tipoConvenio;
	}

	public void setTipoConvenio(String tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}

	public ConvenioView() {
		// TODO Auto-generated constructor stub
	}

	public ConvenioView(int codigo, Integer idCliente, Date dataInicial, Date dataFinal, Double precoTotal,
			String statusConvenio, String tipoConvenio, String nome, String dddTelefone, String telefone,
			String dddCelular, String celular, String usuario, String email, String cpf) {
		super();
		this.codigo = codigo;
		this.idCliente = idCliente;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.precoTotal = precoTotal;
		this.statusConvenio = statusConvenio;
		this.tipoConvenio = tipoConvenio;
		this.nome = nome;
		this.dddTelefone = dddTelefone;
		this.telefone = telefone;
		this.dddCelular = dddCelular;
		this.celular = celular;
		this.usuario = usuario;
		this.email = email;
		this.cpf = cpf;
	}
}
