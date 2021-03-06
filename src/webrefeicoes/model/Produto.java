package webrefeicoes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class Produto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column (name="idProduto")
	private int codigo;
	
	@Column (name="nomeProduto")
	private String descricao;
	
	@Column (name="tipoProduto")
	private String tipo;
	
	@Column (name="noCardapio")
	private String noCardapio;
	
	@Column (name="noQuilo")
	private String noQuilo;
	
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

	public String getNoCardapio() {
		return noCardapio;
	}

	public void setNoCardapio(String noCardapio) {
		this.noCardapio = noCardapio;
	}
	
	public String getNoQuilo() {
		return noQuilo;
	}

	public void setNoQuilo(String noQuilo) {
		this.noQuilo = noQuilo;
	}

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(int codigo, String descricao, String tipo, String noCardapio, String noQuilo) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.tipo = tipo;
		this.noCardapio = noCardapio;
		this.noQuilo = noQuilo;
	}

	
}
