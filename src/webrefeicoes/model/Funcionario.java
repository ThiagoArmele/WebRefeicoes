package webrefeicoes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column (name="idFuncionario")
	private int codigo;
	
	@Column (name="nome")
	private String nome;
	
	@Column (name="cpf")
	private String cpf;
	
	@Column (name="rg")
	private String rg;
	
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
	
	@Column (name="senha")
	private String senha;
	
	@Column (name="rua")
	private String rua;
	
	@Column (name="bairro")
	private String bairro;
	
	@Column (name="cidade")
	private String cidade;
	
	@Column (name="cep")
	private String cep;
	
	@Column (name="numResidencia")
	private Integer numEnd;
	
	@Column (name="dataNascimento")
	private Date dataNascimento;
	
	@Column (name="permissao")
	private String permissao;
	
	@Column (name="estado")
	private String estado;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
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
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
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
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumEnd() {
		return numEnd;
	}

	public void setNumEnd(Integer numEnd) {
		this.numEnd = numEnd;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEndereco() {
		return rua + " " + numEnd + " - " + bairro + ", " +  cidade + "(" + cep + ")" + ", " + estado;
	}
	
	public Funcionario() {
		
	}

	public Funcionario(int codigo, String nome, String cpf, String rg, String dddTelefone, String telefone,
			String dddCelular, String celular, String usuario, String senha, String rua, String bairro, String cidade,
			String cep, Integer numEnd, Date dataNascimento, String permissao, String estado) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dddTelefone = dddTelefone;
		this.telefone = telefone;
		this.dddCelular = dddCelular;
		this.celular = celular;
		this.usuario = usuario;
		this.senha = senha;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.numEnd = numEnd;
		this.dataNascimento = dataNascimento;
		this.permissao = permissao;
		this.estado = estado;
	}

	
	
	
	
	
}
