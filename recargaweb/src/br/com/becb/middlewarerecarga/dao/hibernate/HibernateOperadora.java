package br.com.becb.middlewarerecarga.dao.hibernate;

import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.Operadora;

@Repository("hDaoOperadora")
public class HibernateOperadora<T> extends HBDAO<T> {

	public HibernateOperadora() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class getClazz() {
		return Operadora.class;
	}

}
