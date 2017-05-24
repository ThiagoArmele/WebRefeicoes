package webrefeicoes.model;

import java.io.Serializable;
import java.util.Date;

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
	
	@Column (name="dataPedido")
	private Date dataPedido;
	
	@Column (name="bebida")
	private String bebida;
	
	@Column (name="quilo")
	private String quilo;
	
	@Column (name="valorQuilo")
	private double valorQuilo;
	
	@Column (name="enderecoEntrega")
	private String enderecoEntrega;
	
	@Column (name="avaliado")
	private Boolean avaliado;
	
	@Column (name="troco")
	private Double troco;
	
	@Column (name="formaPagamento")
	private String formaPagamento;
	
	@Column (name="bandeiraCartao")
	private String bandeiraCartao;
	
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

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getBebida() {
		return bebida;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}
	
	public String getQuilo() {
		return quilo;
	}

	public void setQuilo(String quilo) {
		this.quilo = quilo;
	}
	
	public double getValorQuilo() {
		return valorQuilo;
	}

	public void setValorQuilo(Double valorQuilo) {
		this.valorQuilo = valorQuilo;
	}

	
	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	
	public Boolean getAvaliado() {
		return avaliado;
	}

	public void setAvaliado(Boolean avaliado) {
		this.avaliado = avaliado;
	}

	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getBandeiraCartao() {
		return bandeiraCartao;
	}

	public void setBandeiraCartao(String bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}


	public Pedido(int codigo, Integer codigoCliente, String mistura, String guarnicao, String descricaoPrato,
			String observacao, Double valorTotal, Integer idEmbalagem, String outro, String statusPedido,
			Date dataPedido, String bebida, String quilo, double valorQuilo, String enderecoEntrega, Boolean avaliado,
			Double troco, String formaPagamento, String bandeiraCartao) {
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
		this.dataPedido = dataPedido;
		this.bebida = bebida;
		this.quilo = quilo;
		this.valorQuilo = valorQuilo;
		this.enderecoEntrega = enderecoEntrega;
		this.avaliado = avaliado;
		this.troco = troco;
		this.formaPagamento = formaPagamento;
		this.bandeiraCartao = bandeiraCartao;
	}

	public Pedido() {
		// TODO Auto-generated constructor stub
	}
		
}
