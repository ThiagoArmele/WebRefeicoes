package webrefeicoes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="embalagem")
public class Embalagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column (name="id")
	private int codigo;
	
	@Column (name="tamanho")
	private String tamanho;
	
	@Column (name="preco")
	private Double preco;
	
	@Column (name="qtdeGuarnicao")
	private Integer qtdeGuarnicao;
	
	@Column (name="qtdeMistura")
	private Integer qtdeMistura;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQtdeGuarnicao() {
		return qtdeGuarnicao;
	}

	public void setQtdeGuarnicao(Integer qtdeGuarnicao) {
		this.qtdeGuarnicao = qtdeGuarnicao;
	}

	public Integer getQtdeMistura() {
		return qtdeMistura;
	}

	public void setQtdeMistura(Integer qtdeMistura) {
		this.qtdeMistura = qtdeMistura;
	}

	public Embalagem() {
		// TODO Auto-generated constructor stub
	}

	public Embalagem(int codigo, String tamanho, Double preco, Integer qtdeGuarnicao, Integer qtdeMistura) {
		super();
		this.codigo = codigo;
		this.tamanho = tamanho;
		this.preco = preco;
		this.qtdeGuarnicao = qtdeGuarnicao;
		this.qtdeMistura = qtdeMistura;
	}

	
}
