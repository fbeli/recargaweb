package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.becb.middlewarerecarga.entidades.Operadora;

@XmlRootElement(name = "cellcard")
@XmlType(propOrder = {"operadoras"})
public class CellCard {

	public CellCard() {
		// TODO Auto-generated constructor stub
	}
private Operadoras operadoras;


public Operadoras getOperadoras() {
	return operadoras;
}
public void setOperadoras(Operadoras operadoras) {
	this.operadoras = operadoras;
}



}
