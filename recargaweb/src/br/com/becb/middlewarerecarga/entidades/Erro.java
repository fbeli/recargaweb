package br.com.becb.middlewarerecarga.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import br.com.becb.middlewarerecarga.entidades.enums.CodErro;


@Entity
@Table(name="Erro")
public class Erro {
	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private long id;

	@Column(name = "codigo", length = 15)
	private String codigo;
	
	@Column(name = "mensagem",  length = 128)
	private String mensagem;
	
	@Column(name = "data")
	private Date data = new Date();

	public Erro() {
		// TODO Auto-generated constructor stub
	}

	public Erro (String codigo, String mensagem){
		this.mensagem = mensagem;
		this.codigo = codigo;
	}
	public String getCodigo() {
		return codigo;
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		if(data==null)
			data = new Date();
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
