package br.com.becb.middlewarerecarga.entidades.rv.recarga.jax;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "cellcard")
public class CellCardRecarga implements CellCardInterface{

	public CellCardRecarga() {
		// TODO Auto-generated constructor stub
	}

	@XmlElement(name = "cod_online")
	private String codOnline;
	@XmlElement(name = "face")
	private String face;
	@XmlElement(name = "produto")
	private String idProduto;
	/* Como regra, tem 2 dias para confirmar a transação */
	@XmlElement(name = "vencimento")
	private String vencimento;

	@XmlElement(name = "preco")
	private String preco;
	@XmlElement(name = "pagoprivate")
	private String pagoprivate;
	@XmlElement(name = "ddd")
	private String ddd;
	@XmlElement(name = "fone")
	private String fone;
	@XmlElement(name = "mensagem")
	private String mensagem;
	@XmlElement(name = "nsu")
	private String nsu;
	@XmlElement(name = "dataRV")
	private String dataRV;
	@XmlElement(name = "cod_retorno")
	private String codRetorno;
	@XmlElement(name = "codigoTransacao")
	private String codTransacao;
	
	public String getCodOnline() {
		return codOnline;
	}
	public String getFace() {
		return face;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public String getVencimento() {
		return vencimento;
	}
	public String getPreco() {
		return preco;
	}
	public String getPagoprivate() {
		return pagoprivate;
	}
	public String getDdd() {
		return ddd;
	}
	public String getFone() {
		return fone;
	}
	public String getMensagem() {
		return mensagem;
	}
	public String getNsu() {
		return nsu;
	}
	public String getDataRV() {
		return dataRV;
	}
	public String getCodRetorno() {
		return codRetorno;
	}
	public String getCodTransacao() {
		return codTransacao;
	}

}
