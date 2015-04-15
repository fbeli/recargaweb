package br.com.becb.middlewarerecarga.entidades;

/**
 * 00 � Transa��o CONFIRMADA
*	01 � Transa��o Inexistente (ou cancelada
*	no mesmo dia)
*	02 � Transa��o Pendente
*	03 � Transa��o Estornada
*
 * @author fred
 *
 */
public enum StatusRecargaServer {

	CONFIRMADO, INEXISTENTE, PENDENTE, ESTORNADA
}
