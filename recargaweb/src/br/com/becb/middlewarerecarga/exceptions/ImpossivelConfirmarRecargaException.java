package br.com.becb.middlewarerecarga.exceptions;

import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;

public class ImpossivelConfirmarRecargaException extends ErroException{


Erro erro;

public ImpossivelConfirmarRecargaException(){}
	public ImpossivelConfirmarRecargaException(String arg0) {
		super(arg0);
		erro = new Erro(CodErro.IMPOSSIVELCONFIRMARTRANSACAO.toString(), arg0);
	}
	
	
	
/*
	public ImpossivelConfirmarRecargaException(String arg0, String codigoErro, String mensagem) {
		super(arg0);
		erro = new Erro(CodErro.IMPOSSIVELCONFIRMARTRANSACAO.toString(), mensagem);
		
		
	}*/
	
	public Erro getErro(){
		return erro;
	}
	

	



}
