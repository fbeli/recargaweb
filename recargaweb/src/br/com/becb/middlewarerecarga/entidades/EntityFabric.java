package br.com.becb.middlewarerecarga.entidades;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;

/**
 * Fábrica responsável por trazer o retorno dos objetos da rvTecnologia
 */
public class EntityFabric {
	

	public EntityFabric() {
		// TODO Auto-generated constructor stub
	}

	public static Erro createErro(Node element) {
		Erro erro = new Erro();
		NodeList nl = element.getChildNodes();
		System.out.println();
		for (int i = 0; i < element.getChildNodes().getLength(); i++) {

			if (nl.item(i).getNodeName().equals("codigo")) {
				erro.setCodigo(nl.item(i).getChildNodes().item(0)
						.getNodeValue());
			}
			if (nl.item(i).getNodeName().equals("mensagem"))
				erro.setMensagem(nl.item(i).getChildNodes().item(0)
						.getNodeValue());

		}

		return erro;
	}

	/**
	 * cod 200 = "Não foi possível conectar ai servidor"
	 * */
	public static Erro createErro(int codigo, String mensagem) {
		
		return createErro(codigo+"", mensagem);
		
	}

	public static Erro createErro(CodErro codigo, String mensagem) {
		
		return createErro(codigo+"", mensagem);
	}

	public static Erro createErro(String codigo, String mensagem) {

		Erro erro = new Erro();
		erro.setCodigo(codigo);
		erro.setMensagem(mensagem);
		erro.setData(new Date());
					
		return erro;
	}
}
