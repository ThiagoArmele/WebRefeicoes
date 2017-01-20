package webrefeicoes.model;

public class Produto {

	private int codigo;
	private String descricao;
	private String tipo;
	private String nomePrato;
	private double preco;
	private String composicao;
	private boolean isCardapio;
	
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
	
	public String getNomePrato() {
		return nomePrato;
	}
	
	public void setNomePrato(String nomePrato) {
		this.nomePrato = nomePrato;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String getComposicao() {
		return composicao;
	}
	
	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}
	
	public boolean isCardapio() {
		return isCardapio;
	}
	
	public void setCardapio(boolean isCardapio) {
		this.isCardapio = isCardapio;
	}

	
}
