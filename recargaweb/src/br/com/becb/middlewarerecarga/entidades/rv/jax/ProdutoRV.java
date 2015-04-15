package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.becb.middlewarerecarga.entidades.Produto;

@XmlRootElement(name = "produto")
public class ProdutoRV extends ConversoesRV{

	private String codigoProduto;
	
	private String nomeProduto;
	
	private String nomeRecargaOperadora;
	
	private double precocompraProduto;
	private double precovendaProduto;
	private int validadeProduto;
	private String PIN;
	private String modeloRecarga;
	private String msgHabilitacaoProduto;
	private double valorMinimoProduto;
	private double valorMaximoProduto;
	private double valorIncrementoProduto;
	private String ultima_atualizacaoProduto;
	private double precoVariavelProduto;
	private List<String> estadoProduto;

	private DDDProdutoRV dddProduto;

	
	public ProdutoRV() {
		// TODO Auto-generated constructor stub
	}
	
	@XmlElement(name = "codigoProduto")
	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public double getPrecocompraProduto() {
		return precocompraProduto;
	}

	public void setPrecocompraProduto(double precocompraProduto) {
		this.precocompraProduto = precocompraProduto;
	}

	public double getPrecovendaProduto() {
		return precovendaProduto;
	}

	public void setPrecovendaProduto(double precovendaProduto) {
		this.precovendaProduto = precovendaProduto;
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
	
	@XmlElement(name = "ultima_atualizacaoProduto")
	public String getUltima_atualizacaoProduto() {
		return ultima_atualizacaoProduto;
	}

	public Date getDataUltima_atualizacaoProduto(){
		return this.converterDataRV(ultima_atualizacaoProduto);
	}
	public void setUltima_atualizacaoProduto(String ultima_atualizacaoProduto) {
		this.ultima_atualizacaoProduto = ultima_atualizacaoProduto;
	}

	public double getPrecoVariavelProduto() {
		return precoVariavelProduto;
	}

	public void setPrecoVariavelProduto(double precoVariavelProduto) {
		this.precoVariavelProduto = precoVariavelProduto;
	}

	public List<String> getEstadoProduto() {
		return estadoProduto;
	}

	public void setEstadoProduto(List<String> estadoProduto) {
		this.estadoProduto = estadoProduto;
	}

	
	@XmlElement(name = "nomeProduto")
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	@XmlElement(name = "nomeCurtoProduto")
	public String getNomeRecargaOperadora() {
		return nomeRecargaOperadora;
	}

	public void setNomeRecargaOperadora(String nomeRecargaOperadora) {
		this.nomeRecargaOperadora = nomeRecargaOperadora;
	}
	@XmlElement(name = "modeloRecarga")
	public String getModeloRecarga() {
		return modeloRecarga;
	}

	public void setModeloRecarga(String modeloRecarga) {
		this.modeloRecarga = modeloRecarga;
	}
	@XmlElement(name = "msgHabilitacaoProduto")
	public String getMsgHabilitacaoProduto() {
		return msgHabilitacaoProduto;
	}

	public void setMsgHabilitacaoProduto(String msgHabilitacaoProduto) {
		this.msgHabilitacaoProduto = msgHabilitacaoProduto;
	}

	public void setDddProduto(DDDProdutoRV dddProduto) {
		this.dddProduto = dddProduto;
	}
	@XmlElement(name = "dddsProdutoOnline")
	public DDDProdutoRV getDddProduto() {
		return dddProduto;
	}

}
