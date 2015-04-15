package br.com.becb.middlewarerecarga.entidades.rv.recarga.jax;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cellcard")
public class CellCardRecargaPin implements CellCardInterface{

	public CellCardRecargaPin() {
		// TODO Auto-generated constructor stub
	}

	@XmlElement(name = "reenvio")
	private String reenvio;
	@XmlElement(name = "codigo")
	private String codigo;
	@XmlElement(name = "cod_online")
	private String codOnline;
	@XmlElement(name = "produto")
	private String idProduto;
	
	
	@XmlElement(name = "face")
	private String face;
	
	/* Como regra, tem 2 dias para confirmar a transação */
	@XmlElement(name = "vencimento")
	private String vencimento;

	@XmlElement(name = "preco")
	private String preco;
	
	@XmlElement(name = "pin")
	private String pin;
	@XmlElement(name = "pago")
	private String pago;
	@XmlElement(name = "mensagem")
	private String mensagem;
	@XmlElement(name = "lote")
	private String lote;
	@XmlElement(name = "dataRV")
	private String dataRV;
	@XmlElement(name = "serie")
	private String serie;
	@XmlElement(name = "possuiBoleto")
	private String possuiBoleto;
	
	
	
	public String getReenvio() {
		return reenvio;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getCodOnline() {
		return codOnline;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public String getFace() {
		return face;
	}
	public String getVencimento() {
		return vencimento;
	}
	public String getPreco() {
		return preco;
	}
	public String getPin() {
		return pin;
	}
	public String getPago() {
		return pago;
	}
	public String getMensagem() {
		return mensagem;
	}
	public String getLote() {
		return lote;
	}
	public String getDataRV() {
		return dataRV;
	}
	public String getSerie() {
		return serie;
	}
	public String getPossuiBoleto() {
		return possuiBoleto;
	}
	

}
