package br.com.becb.middlewarerecarga.dao.hibernate;

import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.Erro;

@Repository("daoErro")
public class HibernateErro<T> extends HBDAO<T>{

	public HibernateErro() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class getClazz() {
		return Erro.class;
	}

}
