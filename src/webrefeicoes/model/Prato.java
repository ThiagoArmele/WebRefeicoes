package webrefeicoes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prato")
public class Prato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column (name="idPrato")
	private int codigo;
	
	@Column (name="nomePrato")
	private String descricao;
	
	@Column (name="preco")
	private Double preco;
	
	@Column (name="composicaoProduto")
	private String composicaoProduto;
	
	@Column (name="comDesconto")
	private String comDesconto;
	
	@Column (name="precoDesconto")
	private Double precoDesconto;
	
	@Column (name="noCardapio")
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getComposicaoProduto() {
		return composicaoProduto;
	}

	public void setComposicaoProduto(String composicaoProduto) {
		this.composicaoProduto = composicaoProduto;
	}

	public String getComDesconto() {
		return comDesconto;
	}

	public void setComDesconto(String comDesconto) {
		this.comDesconto = comDesconto;
	}

	public Double getPrecoDesconto() {
		return precoDesconto;
	}

	public void setPrecoDesconto(Double precoDesconto) {
		this.precoDesconto = precoDesconto;
	}

	public String getNoCardapio() {
		return noCardapio;
	}

	public void setNoCardapio(String noCardapio) {
		this.noCardapio = noCardapio;
	}
	
	public Prato() {
		// TODO Auto-generated constructor stub
	}
	
	public Prato(int codigo, String descricao, Double preco, String composicaoProduto, String comDesconto,
			Double precoDesconto,String noCardapio) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.composicaoProduto = composicaoProduto;
		this.comDesconto = comDesconto;
		this.precoDesconto = precoDesconto;
		this.noCardapio = noCardapio;
	}

	
	
	
	
}
