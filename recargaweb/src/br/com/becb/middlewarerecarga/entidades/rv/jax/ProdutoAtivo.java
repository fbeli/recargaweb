package br.com.becb.middlewarerecarga.entidades.rv.jax;

public class ProdutoAtivo {

	private int IdProduto;
	private boolean ativo;
	
	public ProdutoAtivo() {
		// TODO Auto-generated constructor stub
	}

	public int getIdProduto() {
		return IdProduto;
	}

	public void setIdProduto(int idProduto) {
		IdProduto = idProduto;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
