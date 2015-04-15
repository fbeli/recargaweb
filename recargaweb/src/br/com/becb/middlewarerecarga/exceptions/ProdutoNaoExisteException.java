package br.com.becb.middlewarerecarga.exceptions;

import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;

public class ProdutoNaoExisteException  extends ErroException {

	Erro erro;
	
	public ProdutoNaoExisteException() {
		
	}

	public ProdutoNaoExisteException(String arg0, String codigoErro, String mensagem) {
		super(arg0);
		erro = new Erro(codigoErro, mensagem);
		
		
	}
	public Erro getErro(){
		return erro;
	}
	public ProdutoNaoExisteException(String arg0) {
		super(arg0);
		erro = new Erro(CodErro.PRODUTOINATIVO+"", arg0);
	}

	

	

}
