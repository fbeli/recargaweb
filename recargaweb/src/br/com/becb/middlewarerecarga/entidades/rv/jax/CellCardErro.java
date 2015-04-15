package br.com.becb.middlewarerecarga.entidades.rv.jax;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.becb.middlewarerecarga.entidades.Erro;


@XmlRootElement(name = "cellcard")
@XmlType(propOrder = {"erro"})

public class CellCardErro {
	
	public CellCardErro() {
		// TODO Auto-generated constructor stub
	}
	private Erro erro;
	public Erro getErro() {
		return erro;
	}
	public void setErro(Erro erro) {
		this.erro = erro;
	}

}
