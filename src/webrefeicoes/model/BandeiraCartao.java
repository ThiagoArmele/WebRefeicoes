package webrefeicoes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bandeiraCartao")
public class BandeiraCartao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int codigo;
	
	@Column(name="descricaoBandeira")
	private String descricaoBandeira;
	
	@Column(name="status")
	private String status;
	
	
	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getDescricaoBandeira() {
		return descricaoBandeira;
	}


	public void setDescricaoBandeira(String descricaoBandeira) {
		this.descricaoBandeira = descricaoBandeira;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public BandeiraCartao() {
		// TODO Auto-generated constructor stub
	}


	public BandeiraCartao(int codigo, String descricaoBandeira, String status) {
		super();
		this.codigo = codigo;
		this.descricaoBandeira = descricaoBandeira;
		this.status = status;
	}

	
	
}
