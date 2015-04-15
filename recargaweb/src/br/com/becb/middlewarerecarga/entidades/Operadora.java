package br.com.becb.middlewarerecarga.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "operadora")
public class Operadora {

	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private long id;

	@Column(name = "codigoOperadora")
	private String codigoOperadora;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Produto> produtos;
	/*
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Estado> estados;
	/*/
	private String nomeOperadora;
	private Date ultimaAtualizacaoOperadora;
	// private List<String> estadosOperadora;
	
	private String nomeRecargaOperadora;
	private boolean ativo = true;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	


	public List<Produto> getListProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public void setProduto(Produto produto){
		if(null == produtos)
			produtos = new ArrayList<Produto>();
	}

	public Operadora() {
		// TODO Auto-generated constructor stub
	}

	public String getCodigoOperadora() {
		return codigoOperadora;
	}

	public void setCodigoOperadora(String codigoOperadora) {
		this.codigoOperadora = codigoOperadora;
	}

	public String getNomeOperadora() {
		return nomeOperadora;
	}

	public void setNomeOperadora(String nomeOperadora) {
		this.nomeOperadora = nomeOperadora;
	}

	public Date getUltimaAtualizacaoOperadora() {
		return ultimaAtualizacaoOperadora;
	}

	public void setUltimaAtualizacaoOperadora(Date ultimaAtualizacaoOperadora) {
		this.ultimaAtualizacaoOperadora = ultimaAtualizacaoOperadora;
	}

	/*
	 * public List<String> getEstadosOperadora() { return estadosOperadora; }
	 * 
	 * public void setEstadosOperadora(List<String> estadosOperadora) {
	 * this.estadosOperadora = estadosOperadora; }
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * public EstadosAtuantes getEstadosAtuantes() { return estadosAtuantes; }
	 * 
	 * public void setEstadosAtuantes(EstadosAtuantes estadosAtuantes) {
	 * this.estadosAtuantes = estadosAtuantes; }
	 */

	public String getNomeRecargaOperadora() {
		return nomeRecargaOperadora;
	}

	public void setNomeRecargaOperadora(String nomeRecargaOperadora) {
		this.nomeRecargaOperadora = nomeRecargaOperadora;
	}

}
