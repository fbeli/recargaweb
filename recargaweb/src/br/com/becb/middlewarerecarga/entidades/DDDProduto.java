package br.com.becb.middlewarerecarga.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity
@Table(name = "dddProduto")
public class DDDProduto {
	public DDDProduto() {
		
	}
	public DDDProduto(int ddd) {
		this.ddd = ddd;
	}
	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;
	
	
	@Column(name = "dddProduto", unique = true)
	private int ddd;

	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "produto_dddproduto", 
			joinColumns={@JoinColumn(name="dddProduto_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="produto_id", referencedColumnName="id")})
	public List<Produto> produtos; 
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public void setProduto (Produto produto){
		if(null == produtos)
			produtos = new ArrayList<Produto>();
		produtos.add(produto);
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int dddProduto) {
		this.ddd = dddProduto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
