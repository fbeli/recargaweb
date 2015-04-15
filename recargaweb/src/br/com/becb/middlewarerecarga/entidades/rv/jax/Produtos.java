package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.becb.middlewarerecarga.entidades.Produto;



@XmlRootElement(name = "produto")
@XmlAccessorType (XmlAccessType.FIELD)
public class Produtos {

	
	 @XmlElement(name = "produto")
	private List<ProdutoRV> produtos;

	 
	public Produtos() {
		// TODO Auto-generated constructor stub
	}


	public List<ProdutoRV> getProdutosRV() {
		return produtos;
	}


	public void setProdutoRV(List<ProdutoRV> produtoRV) {
		this.produtos = produtoRV;
	}

}
