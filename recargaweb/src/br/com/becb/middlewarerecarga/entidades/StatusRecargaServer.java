package br.com.becb.middlewarerecarga.entidades;

/**
 * 00 – Transação CONFIRMADA
*	01 – Transação Inexistente (ou cancelada
*	no mesmo dia)
*	02 – Transação Pendente
*	03 – Transação Estornada
*
 * @author fred
 *
 */
public enum StatusRecargaServer {

	CONFIRMADO, INEXISTENTE, PENDENTE, ESTORNADA
}
