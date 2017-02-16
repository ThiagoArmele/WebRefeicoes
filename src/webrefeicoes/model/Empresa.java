package webrefeicoes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	@Column (name="idEmpresa")
	private int codigo;
	
	@Column (name="nomeFantasia")
	private String nomeFantasia;
	
	@Column (name="razaoSocial")
	private String razaoSocial;
	
	@Column (name="cnpj")
	private String cnpj;
	
	@Column (name="rua")
	private String rua;
	
	@Column (name="bairro")
	private String bairro;
	
	@Column (name="cidade")
	private String cidade;
	
	@Column (name="cep")
	private Integer cep;
	
	@Column (name="complementar")
	private String complemento;
	
	@Column (name="numero")
	private Integer numEnd;
	
	@Column (name="inscricaoEstadual")
	private String inscricaoEstadual;
	
	@Column (name="usuario")
	private String usuario;
	
	@Column (name="emailEmpresa")
	private String emailEmpresa;
	
	@Column (name="emailResponsavel")
	private String emailResponsavel;
	
	@Column (name="senha")
	private String senha;
	
	@Column (name="dddCelular")
	private Integer dddCelular;
	
	@Column (name="celular")
	private Integer celular;
	
	@Column (name="dddTelefone")
	private Integer dddTelefone;
	
	@Column (name="telefone")
	private Integer telefone;
	
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getRua() {
		return rua;
	}


	public void setRua(String rua) {
		this.rua = rua;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public Integer getCep() {
		return cep;
	}


	public void setCep(Integer cep) {
		this.cep = cep;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public Integer getNumEnd() {
		return numEnd;
	}


	public void setNumEnd(Integer numEnd) {
		this.numEnd = numEnd;
	}


	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}


	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getEmailEmpresa() {
		return emailEmpresa;
	}


	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}


	public String getEmailResponsavel() {
		return emailResponsavel;
	}


	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public Integer getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(Integer dddCelular) {
		this.dddCelular = dddCelular;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public Integer getDddTelefone() {
		return dddTelefone;
	}

	public void setDddTelefone(Integer ddTelefone) {
		this.dddTelefone = ddTelefone;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Empresa() {
	}
	

	public Empresa(int codigo, String nomeFantasia, String razaoSocial, String cnpj, String rua, String bairro,
			String cidade, Integer cep, String complemento, Integer numEnd, String inscricaoEstadual, String usuario,
			String emailEmpresa, String emailResponsavel, String senha,Integer dddTelefone,Integer telefone,
			Integer dddCelular,Integer celular
			) {
		super();
		this.codigo = codigo;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.complemento = complemento;
		this.numEnd = numEnd;
		this.inscricaoEstadual = inscricaoEstadual;
		this.usuario = usuario;
		this.emailEmpresa = emailEmpresa;
		this.emailResponsavel = emailResponsavel;
		this.senha = senha;
		this.dddCelular = dddCelular;
		this.celular = celular;
		this.dddTelefone = dddTelefone;
		this.telefone = telefone;
	}

	

	
}
