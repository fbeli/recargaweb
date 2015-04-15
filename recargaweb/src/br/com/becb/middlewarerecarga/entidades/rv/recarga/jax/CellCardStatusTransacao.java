package br.com.becb.middlewarerecarga.entidades.rv.recarga.jax;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cellcard")
public class CellCardStatusTransacao {

	public CellCardStatusTransacao() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * Indica o c�digo da compra no sistema da RV.
	 */

	private String codOnline;
	
	/**
	 * Indica o c�digo da compra no sistema cliente.
	 */
	
	private String codigo;
	
	/**
	 * 00 � Transa��o CONFIRMADA
		01 � Transa��o Inexistente (ou cancelada
		no mesmo dia)
		02 � Transa��o Pendente
		03 � Transa��o Estornada
	 */

	private String status;

	@XmlElement(name = "cod_online")
	public String getCodOnline() {
		return codOnline;
	}

	public void setCodOnline(String codOnline) {
		this.codOnline = codOnline;
	}

	@XmlElement(name = "codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@XmlElement(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
