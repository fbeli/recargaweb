package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.becb.middlewarerecarga.entidades.EstadoOperadora;

@XmlRootElement(name = "estadoOperadora")
@XmlAccessorType (XmlAccessType.FIELD)
public class EstadosAtuantes {

	public EstadosAtuantes() {
		// TODO Auto-generated constructor stub
	}
	 @XmlElement(name = "estadoOperadora")
		private List<String> estadoOperadora;
}
