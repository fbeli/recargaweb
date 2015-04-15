package br.com.becb.middlewarerecarga.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;

public class ConfirmacaoDeTransacaoException extends ErroException {

	@Autowired
	HibernateErro<Erro> hDaoErro;
	
	Erro erro;

	public ConfirmacaoDeTransacaoException() {
		super("Impossível confirmar transação");
		erro = EntityFabric.createErro(31, "Impossível confirmar transação");
		hDaoErro.persistir(erro);
	}
	public ConfirmacaoDeTransacaoException(String arg0, String codigoErro, String mensagem) {
		super(arg0);
		erro = new Erro(codigoErro, mensagem);
		
		
	}
	public Erro getErro(){
		return erro;
	}
	public ConfirmacaoDeTransacaoException(String arg0) {
		super(arg0);
		erro = EntityFabric.createErro(31, arg0);
		hDaoErro.persistir(erro);
		
	}

}
