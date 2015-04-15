package br.com.becb.middlewarerecarga.servicos.cliente.rv;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.rv.jax.CellCard;
import br.com.becb.middlewarerecarga.entidades.rv.jax.CellCardErro;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.ErroService;

/**
 * Classe responsável por testar erros nos xmls da RV
 * @author fred
 *
 */
@Component("testeErros")
public class TesteErros {

	@Autowired
	private HibernateErro<Erro> daoErro;
	
	@Autowired
	private ErroService es;
	
	
	public TesteErros() {
		// TODO Auto-generated constructor stub
	}

public boolean testeErro(NodeList nl) {
		boolean retorno = false;
		Node element = null;
	//	System.out.println("tamanho: " + nl.getLength());
		for (int i = 0; i < nl.getLength(); i++) {
			element = (Node) nl.item(i);
			
			if (!element.getNodeName().equals("#text")) {
				if (element.getNodeName().equals("erro")) {
					Erro erro = EntityFabric.createErro(element);
					daoErro.persistir(erro);
					retorno = true;
				} else {
					//System.out.println("Valor de i: " + i);
					//System.out.println(element.getNodeValue());
					//System.out.println(element.toString());

					if (null != nl.item(i).getChildNodes()
							&& 0 < nl.item(i).getChildNodes().getLength()) {
						testeErro(nl.item(i).getChildNodes());
					}
				}
			}
		}
		return retorno;
	}

public boolean testeErroJAX(String xml) throws ErroException {
	 try {
         JAXBContext context = JAXBContext.newInstance(CellCardErro.class);
         Unmarshaller un = context.createUnmarshaller();
			CellCardErro cell = (CellCardErro) un.unmarshal(new StringReader(xml));
         System.out.println(
         cell.getErro().getMensagem());
         
             if (cell.getErro() != null){
          
        	      	 throw new ErroException( es.createErro(cell.getErro()));
        	
         }return false;
     } catch (JAXBException e) {
         e.printStackTrace();
     }catch (NullPointerException e) {
		return false;
	}
	
	
	return true;
}
public HibernateErro<Erro> getDaoErro() {
	return daoErro;
}

public void setDaoErro(HibernateErro<Erro> daoErro) {
	this.daoErro = daoErro;
}

}
