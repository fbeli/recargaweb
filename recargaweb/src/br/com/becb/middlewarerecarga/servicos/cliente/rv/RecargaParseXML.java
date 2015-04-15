package br.com.becb.middlewarerecarga.servicos.cliente.rv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateOperadora;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.Operadora;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.Logar;

@Scope("prototype")
@Component("parse")
public class RecargaParseXML {

	@Autowired
	private TesteErros testeErros;

	@Autowired
	private ParseProdutosEOperadoras parseProdutosEOperadoras;

	public RecargaParseXML() {
		// TODO Auto-generated constructor stub
	}

	public List<Operadora> parseOperadora(String xml) throws ErroException {

		List<Operadora> operadoras;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			// parse using builder to get DOM representation of the XML file

			System.out.println(xml);
			if (!testeErros.testeErroJAX(xml)) {

				operadoras = new ArrayList<Operadora>();

				operadoras = parseProdutosEOperadoras.parseOperadoraJaxXML(xml);
				Logar.info("Total de Operadoras:"+operadoras.size());

				return operadoras;

			}
			throw new ErroException(
					"Erros no retorno do XML de busca de produtos", "201",
					"Erros no retorno do XML de busca de produtos");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (Exception pce) {
			pce.printStackTrace();
		}

		return null;
	}

}
