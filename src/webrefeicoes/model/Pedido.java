package webrefeicoes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column (name="id")
	private int codigo;
	
	@Column (name="idCliente")
	private Integer codigoCliente;

	@Column (name="mistura")
	private String mistura;
	
	@Column (name="guarnicao")
	private String guarnicao;
	
	@Column (name="descricaoPrato")
	private String descricaoPrato;
	
	@Column (name="observacao")
	private String observacao;
	
	@Column (name="valorTotal")
	private Double valorTotal;

	@Column (name="idEmbalagem")
	private Integer idEmbalagem;
	
	@Column (name="outro")
	private String outro;
	
	@Column (name="statusPedido")
	private String statusPedido;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getIdEmbalagem() {
		return idEmbalagem;
	}

	public void setIdEmbalagem(Integer idEmbalagem) {
		this.idEmbalagem = idEmbalagem;
	}

	public String getMistura() {
		return mistura;
	}

	public void setMistura(String mistura) {
		this.mistura = mistura;
	}

	public String getGuarnicao() {
		return guarnicao;
	}

	public void setGuarnicao(String guarnicao) {
		this.guarnicao = guarnicao;
	}

	public String getDescricaoPrato() {
		return descricaoPrato;
	}

	public void setDescricaoPrato(String descricaoPrato) {
		this.descricaoPrato = descricaoPrato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public String getOutro() {
		return outro;
	}

	public void setOutro(String outro) {
		this.outro = outro;
	}

	public String getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Pedido(int codigo, Integer codigoCliente, String mistura, String guarnicao, String descricaoPrato,
			String observacao, Double valorTotal, Integer idEmbalagem, String outro, String statusPedido) {
		super();
		this.codigo = codigo;
		this.codigoCliente = codigoCliente;
		this.mistura = mistura;
		this.guarnicao = guarnicao;
		this.descricaoPrato = descricaoPrato;
		this.observacao = observacao;
		this.valorTotal = valorTotal;
		this.idEmbalagem = idEmbalagem;
		this.outro = outro;
		this.statusPedido = statusPedido;
	}

	public Pedido() {
		// TODO Auto-generated constructor stub
	}
		
}
