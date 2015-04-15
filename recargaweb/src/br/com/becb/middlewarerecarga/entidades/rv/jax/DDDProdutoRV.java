package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dddProduto")
@XmlAccessorType (XmlAccessType.FIELD)
public class DDDProdutoRV {

	/**
	 * lista de inteiros
	 */
	 @XmlElement(name = "dddProduto")	
	public List<String> dddProdutos;
	
	public DDDProdutoRV() {
		
	}
	



	
}
