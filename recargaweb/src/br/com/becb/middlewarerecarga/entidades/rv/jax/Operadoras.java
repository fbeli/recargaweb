package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.becb.middlewarerecarga.entidades.Operadora;



@XmlRootElement(name = "operadora")
@XmlAccessorType (XmlAccessType.FIELD)
public class Operadoras {

	public Operadoras() {
		// TODO Auto-generated constructor stub
	}
	 @XmlElement(name = "operadora")
	private List<OperadoraRV> operadoras;
	 
	public List<OperadoraRV> getOperadoras() {
		return operadoras;
	}
	public void setOperadoras(List<OperadoraRV> operadoras) {
		this.operadoras = operadoras;
	}
}
