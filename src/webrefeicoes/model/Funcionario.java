package webrefeicoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Funcionario {
	
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
	private int dddTelefone;
	
	@Column (name="telefone")
	private String telefone;
	
	@Column (name="dddCelular")
	private int dddCelular;

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
	private int cep;
	
	@Column (name="numResidencia")
	private int numEnd;
	
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
	
	public int getDddTelefone() {
		return dddTelefone;
	}
	
	public void setDddTelefone(int dddTelefone) {
		this.dddTelefone = dddTelefone;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getDddCelular() {
		return dddCelular;
	}
	
	public void setDddCelular(int dddCelular) {
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
	
	public int getCep() {
		return cep;
	}
	
	public void setCep(int cep) {
		this.cep = cep;
	}

	public int getNumEnd() {
		return numEnd;
	}

	public void setNumEnd(int numEnd) {
		this.numEnd = numEnd;
	}
	
}
