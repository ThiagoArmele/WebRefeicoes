package webrefeicoes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bebidas")
public class Bebida implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int codigo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="tamanho")
	private String tamanho;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="preco")
	private Double preco;
	
	@Column(name="noCardapio")
	private String noCardapio;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getNoCardapio() {
		return noCardapio;
	}

	public void setNoCardapio(String noCardapio) {
		this.noCardapio = noCardapio;
	}
	
	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public Bebida() {
		// TODO Auto-generated constructor stub
	}

	public Bebida(int codigo, String descricao, String tamanho, String tipo, Double preco, String noCardapio) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.tamanho = tamanho;
		this.tipo = tipo;
		this.preco = preco;
		this.noCardapio = noCardapio;
	}

	
	
}
