package webrefeicoes.model;

public class Produto {

	private int codigo;
	private String descriçao;
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
	
	public String getDescriçao() {
		return descriçao;
	}
	
	public void setDescriçao(String descriçao) {
		this.descriçao = descriçao;
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
