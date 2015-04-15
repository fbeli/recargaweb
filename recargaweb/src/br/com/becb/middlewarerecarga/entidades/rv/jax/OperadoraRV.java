package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.becb.middlewarerecarga.entidades.Operadora;

@XmlRootElement(name = "operadora")
public class OperadoraRV extends ConversoesRV{

	public OperadoraRV() {
		// TODO Auto-generated constructor stub
	}

	private String codigoOperadora;
	private String nomeOperadora;
	private String ultimaAtualizacaoOperadora;
	@XmlElement(name = "estadosAtuantes")
	private EstadosAtuantes estadosAtuantes;

	private String nomeRecargaOperadora;

	private Produtos produtos;

	@XmlElement(name = "nomeRecargaOperadora")
	public String getNomeRecargaOperadora() {
		return nomeRecargaOperadora;
	}

	public void setNomeRecargaOperadora(String nomeRecargaOperadora) {
		this.nomeRecargaOperadora = nomeRecargaOperadora;
	}
	
	@XmlElement(name = "codigoOperadora")
	public String getCodigoOperadora() {
		return codigoOperadora;
	}

	public void setCodigoOperadora(String codigoOperadora) {
		this.codigoOperadora = codigoOperadora;
	}
	@XmlElement(name = "nomeOperadora")
	public String getNomeOperadora() {
		return nomeOperadora;
	}

	public void setNomeOperadora(String nomeOperadora) {
		this.nomeOperadora = nomeOperadora;
	}
	@XmlElement(name = "ultimaAtualizacaoOperadora")
	public String getUltimaAtualizacaoOperadora() {
		return ultimaAtualizacaoOperadora;
	}
	public Date getDataUltimaAtualizacaoOperadora(){
		return this.converterDataRV(ultimaAtualizacaoOperadora);
	}

	public void setUltimaAtualizacaoOperadora(String ultimaAtualizacaoOperadora) {
		this.ultimaAtualizacaoOperadora = ultimaAtualizacaoOperadora;
	}

	@XmlElement(name = "produtos")
	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

}
