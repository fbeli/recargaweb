package br.com.becb.middlewarerecarga.servicos.cliente.rv;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardRecarga;
import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardRecargaPin;
import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardStatusTransacao;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.ErroService;

@Component("parseRecarga")
public class ParseRecarga {

	@Autowired
	private TesteErros testeErros;
	
	@Autowired
	private ErroService es;

	public ParseRecarga() {

	}

	@SuppressWarnings("finally")
	public CellCardRecarga parserecargaOnline(String xml) throws ErroException {
		if (!testeErros.testeErroJAX(xml)) {
			CellCardRecarga cell = null;
			try {
				JAXBContext context = JAXBContext
						.newInstance(CellCardRecarga.class);
				Unmarshaller un = context.createUnmarshaller();
				cell = (CellCardRecarga) un.unmarshal(new StringReader(xml));

				// this.printOperadoras(cell);
				// return this.createOperadoras(cell);

			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return cell;
			}

		}
		
		return null;
	}
	
	/**
	 * 
	 * @param xml xml com retorno de recraga por Pin
	 * @return
	 * @throws ErroException 
	 */
	@SuppressWarnings("finally")
	public CellCardRecargaPin parserecargaPin(String xml) throws ErroException {
		if (!testeErros.testeErroJAX(xml)) {
			CellCardRecargaPin cell = null;
			try {
				JAXBContext context = JAXBContext
						.newInstance(CellCardRecargaPin.class);
				Unmarshaller un = context.createUnmarshaller();
				cell = (CellCardRecargaPin) un.unmarshal(new StringReader(xml));

				// this.printOperadoras(cell);
				// return this.createOperadoras(cell);

			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return cell;
			}

		}
		return null;
	}
	
	public CellCardStatusTransacao parserStatusTransacao(String xml) throws ErroException {
		if (!testeErros.testeErroJAX(xml)) {
			CellCardStatusTransacao cell = null;
			try {
				JAXBContext context = JAXBContext
						.newInstance(CellCardStatusTransacao.class);
				Unmarshaller un = context.createUnmarshaller();
				cell = (CellCardStatusTransacao) un.unmarshal(new StringReader(xml));

				// this.printOperadoras(cell);
				// return this.createOperadoras(cell);

			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return cell;
			}

		}
		return null;
	}

}
