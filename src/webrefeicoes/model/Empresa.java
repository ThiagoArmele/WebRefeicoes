package webrefeicoes.model;

import java.util.ArrayList;

public class Empresa {

	private int codigo;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String rua;
	private String bairro;
	private String cidade;
	private int cep;
	private int numEnd;
	private ArrayList<Funcionario> funcionarios;
	
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
	
	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	
	
}
