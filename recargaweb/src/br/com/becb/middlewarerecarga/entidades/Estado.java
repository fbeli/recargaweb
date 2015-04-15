package br.com.becb.middlewarerecarga.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
/*@Entity
@Table(name = "estado")/*/
public class Estado {
	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private int id;
	private String estado;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "produto_estado", 
			joinColumns={@JoinColumn(name="estado_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="produto_id", referencedColumnName="id")})
	public List<Produto> produtos; 
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "operadora_estado", 
			joinColumns={@JoinColumn(name="estado_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="operadora_id", referencedColumnName="id")})
	public List<Operadora> operadoras;
	
	public Estado() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
