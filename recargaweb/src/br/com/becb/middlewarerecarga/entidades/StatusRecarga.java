package br.com.becb.middlewarerecarga.entidades;


/**
 *CADASTRADO - chegou ao sistema, mas ainda não solicitado a RV
 *SOLICITADO - primeiro acesso a RV, solicitando recarga
 *EFETUADO - acesso de confirmação de recarga feito
 *CANCELADO - recarga cancelada
*/
public enum StatusRecarga {
 
	CADASTRADO,SOLICITADO,EFETUADO,CANCELADO,ERRONATRANSACAO;
	

	

}
