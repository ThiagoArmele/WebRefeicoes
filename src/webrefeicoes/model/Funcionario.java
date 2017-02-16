package webrefeicoes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private Integer dddTelefone;
	
	@Column (name="telefone")
	private Integer telefone;
	
	@Column (name="dddCelular")
	private Integer dddCelular;

	@Column (name="celular")
	private Integer celular;
	
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
	private Integer cep;
	
	@Column (name="numResidencia")
	private Integer numEnd;
	
	@Column (name="dataNascimento")
	private Date dataNascimento;
	
	@Column (name="permissao")
	private String permissao;
	
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
	
	public Integer getDddTelefone() {
		return dddTelefone;
	}
	
	public void setDddTelefone(Integer dddTelefone) {
		this.dddTelefone = dddTelefone;
	}
	
	public Integer getTelefone() {
		return telefone;
	}
	
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
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
	
	public Integer getCep() {
		return cep;
	}
	
	public void setCep(Integer cep) {
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

	public Funcionario() {
		
	}

	public Funcionario(int codigo, String nome, String cpf, String rg, Integer dddTelefone, Integer telefone,
			Integer dddCelular, Integer celular, String usuario, String senha, String rua, String bairro, String cidade,
			Integer cep, Integer numEnd, Date dataNascimento, String permissao) {
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
	}

	
	
	
	
}
