package webrefeicoes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="avaliacao")
public class Avaliacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column (name = "codigo")
	private int codigo;
	
	@Column (name = "pontuacaoTempo")
	private Double pontuacaoTempo;
	
	@Column (name = "pontuacaoEntrega")
	private Double pontuacaoEntrega;
	
	@Column (name = "pontuacaoComida")
	private Double pontuacaoComida;
	
	@Column (name = "comentario")
	private String comentario;
	
	@Column (name = "usuario")
	private String usuario;
	
	@Column (name = "media")
	private Double media;
	
	@Column (name = "dataEnviada")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date dataEnviada;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Double getPontuacaoTempo() {
		return pontuacaoTempo;
	}

	public void setPontuacaoTempo(Double pontuacaoTempo) {
		this.pontuacaoTempo = pontuacaoTempo;
	}

	public Double getPontuacaoEntrega() {
		return pontuacaoEntrega;
	}

	public void setPontuacaoEntrega(Double pontuacaoEntrega) {
		this.pontuacaoEntrega = pontuacaoEntrega;
	}

	public Double getPontuacaoComida() {
		return pontuacaoComida;
	}

	public void setPontuacaoComida(Double pontuacaoComida) {
		this.pontuacaoComida = pontuacaoComida;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Date getDataEnviada() {
		return dataEnviada;
	}

	public void setDataEnviada(Date dataEnviada) {
		this.dataEnviada = dataEnviada;
	}

	public Avaliacao(int codigo, Double pontuacaoTempo, Double pontuacaoEntrega, Double pontuacaoComida,
			String comentario, String usuario, Double media, Date dataEnviada) {
		super();
		this.codigo = codigo;
		this.pontuacaoTempo = pontuacaoTempo;
		this.pontuacaoEntrega = pontuacaoEntrega;
		this.pontuacaoComida = pontuacaoComida;
		this.comentario = comentario;
		this.usuario = usuario;
		this.media = media;
		this.dataEnviada = dataEnviada;
	}

	public Avaliacao() {
		// TODO Auto-generated constructor stub
	}
}
