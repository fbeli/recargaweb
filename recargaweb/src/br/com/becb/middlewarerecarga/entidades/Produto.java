package br.com.becb.middlewarerecarga.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private long id;
	private String codigoProduto;

	private double precoCompraProduto;
	private double precoVendaProduto;
	private int validadeProduto;
	private String PIN;
	private double valorMinimoProduto;
	private double valorMaximoProduto;
	private double valorIncrementoProduto;
	private Date ultima_atualizacaoProduto;
	private double precoVariavelProduto;
	private String modeloRecarga;
	private String msgHabilitacaoProduto;
	// private List<String> estadoProduto;
	//private List<Integer> dddProduto;
	private String nomeProduto;
	
	private boolean ativo = true;
	
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<DDDProduto> dddProduto;
	/*
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Estado> estados;
/*/
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "operadoraId")
	@Fetch(FetchMode.JOIN)
	private Operadora operadora;
	//TODO A operadora deveria ser mapeado somente na coluna, mas no mysql há uma tabela, provavelmente pq não usei mappedBy do outro lado
	
	private Date dataUpdate;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="produto")
	private List<Recarga> recargas;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	

	public double getPrecoCompraProduto() {
		return precoCompraProduto;
	}

	public void setPrecoCompraProduto(double precocompraProduto) {
		this.precoCompraProduto = precocompraProduto;
	}

	public double getPrecovendaProduto() {
		return precoVendaProduto;
	}

	public void setPrecoVendaProduto(double precoVendaProduto) {
		this.precoVendaProduto = precoVendaProduto;
	}

	public int getValidadeProduto() {
		return validadeProduto;
	}

	public void setValidadeProduto(int validadeProduto) {
		this.validadeProduto = validadeProduto;
	}

	public String getPIN() {
		return PIN;
	}

	public void setPIN(String pIN) {
		PIN = pIN;
	}

	public double getValorMinimoProduto() {
		return valorMinimoProduto;
	}

	public void setValorMinimoProduto(double valorMinimoProduto) {
		this.valorMinimoProduto = valorMinimoProduto;
	}

	public double getValorMaximoProduto() {
		return valorMaximoProduto;
	}

	public void setValorMaximoProduto(double valorMaximoProduto) {
		this.valorMaximoProduto = valorMaximoProduto;
	}

	public double getValorIncrementoProduto() {
		return valorIncrementoProduto;
	}

	public void setValorIncrementoProduto(double valorIncrementoProduto) {
		this.valorIncrementoProduto = valorIncrementoProduto;
	}

	public Date getUltima_atualizacaoProduto() {
		return ultima_atualizacaoProduto;
	}

	public void setUltima_atualizacaoProduto(Date ultima_atualizacaoProduto) {
		this.ultima_atualizacaoProduto = ultima_atualizacaoProduto;
	}

	public double getPrecoVariavelProduto() {
		return precoVariavelProduto;
	}

	public void setPrecoVariavelProduto(double precoVariavelProduto) {
		this.precoVariavelProduto = precoVariavelProduto;
	}

	/**
	 * public List<String> getEstadoProduto() { return estadoProduto; }
	 * 
	 * public void setEstadoProduto(List<String> estadoProduto) {
	 * this.estadoProduto = estadoProduto; }
	 * 
	 * public List<Integer> getDddProduto() { return dddProduto; }
	 * 
	 * public void setDddProduto(List<Integer> dddProduto) { this.dddProduto =
	 * dddProduto; }
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	@XmlElement(name = "nomeProduto")
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	
	public String getModeloRecarga() {
		return modeloRecarga;
	}

	public void setModeloRecarga(String modeloRecarga) {
		this.modeloRecarga = modeloRecarga;
	}

	public double getPrecoVendaProduto() {
		return precoVendaProduto;
	}

	public String getMsgHabilitacaoProduto() {
		return msgHabilitacaoProduto;
	}

	public void setMsgHabilitacaoProduto(String msgHabilitacaoProduto) {
		this.msgHabilitacaoProduto = msgHabilitacaoProduto;
	}

	public List<DDDProduto> getDddProduto() {
		return dddProduto;
	}

	public void setDddProduto(List<DDDProduto> dddProduto) {
		this.dddProduto = dddProduto;
	}
	public void setDddProduto(DDDProduto dddProduto) {
		if(this.dddProduto == null)
			this.dddProduto = new ArrayList<DDDProduto>();
		this.dddProduto.add(dddProduto);
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public List<Recarga> getRecargas() {
		return recargas;
	}

	public void setRecargas(List<Recarga> recargas) {
		this.recargas = recargas;
	}

/**public List<Integer> getDddProduto() {
		return dddProduto;
	}

	public void setDddProduto(List<Integer> dddProduto) {
		this.dddProduto = dddProduto;
	}*/
}
