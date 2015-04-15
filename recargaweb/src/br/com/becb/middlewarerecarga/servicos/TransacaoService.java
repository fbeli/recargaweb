package br.com.becb.middlewarerecarga.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateRecarga;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.StatusRecargaServer;
import br.com.becb.middlewarerecarga.entidades.rv.jax.ConversoesRV;
import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardStatusTransacao;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.cliente.rv.ParseRecarga;
import br.com.becb.middlewarerecarga.suporte.Suporte;

@Component("transacaoService")
public class TransacaoService {

	@Autowired
	ParseRecarga parseRecarga;

	@Autowired
	ConsultaRV consultaRV;

	@Autowired
	HibernateRecarga<Recarga> hDaoRecarga;

	

	/**
	 * Pega a lista de transações pendentes cancela as transações pendentes não
	 * completadas
	 */
	public void confirmaTransacao() {

	}

	public List<Recarga> buscaTransacoesPendentes() throws ErroException  {
		List<Recarga> recargasPendentes = new ArrayList<Recarga>();

		// criar por interceptor
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);

		// params.add(new BasicNameValuePair("codigo_transacao", ""));
		params.add(new BasicNameValuePair("dataConsulta", ConversoesRV
				.converterDataRV(new Date(), "YYYYMMdd")));

		String xml = consultaRV.fazerConsulta(params);
		CellCardStatusTransacao cell = parseRecarga.parserStatusTransacao(xml);

		return recargasPendentes;
	}

	/**
	 * Busca as transações, codigo recarga no sistema RV
	 * 
	 * @param cod_online
	 *            código da recarga no sistema RV
	 * 
	 * @return
	 * @throws ErroException 
	 */
	public String buscaStatusTransacao_CodOnline(String cod_online) throws ErroException {

		// criar por interceptor
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);

		params.add(new BasicNameValuePair("cod_online", cod_online));

		String xml = consultaRV.fazerConsulta(params);

		CellCardStatusTransacao cell = parseRecarga.parserStatusTransacao(xml);

		return cell.getStatus();
	}

	public List<Recarga> buscaStatusTransacao_idRecarga(String compra) {
		List<Recarga> recargasPendentes = new ArrayList<Recarga>();

		// criar por interceptor
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);

		params.add(new BasicNameValuePair("compra", compra));

		String xml = consultaRV.fazerConsulta(params);
		return recargasPendentes;
	}
	
	public Recarga ajustarStatusTransacao(Recarga recarga) throws ErroException{
		
		
		String status = buscaStatusTransacao_CodOnline(recarga.getCodOnline());
	
		
recarga = this.ajustaStatusRecargaServer(recarga, status);
		if (recarga.getStatusRecargaServer() != null)
			hDaoRecarga.merge(recarga);

		return recarga;
	}
	private Recarga ajustaStatusRecargaServer(Recarga recarga, String status){
		
		switch (status) {
		case "00":
			recarga.setStatusRecargaServer(
					StatusRecargaServer.CONFIRMADO);
			break;
		case "01":
			recarga.setStatusRecargaServer(
					StatusRecargaServer.INEXISTENTE);
			break;
		case "02":
			recarga.setStatusRecargaServer(
					StatusRecargaServer.PENDENTE);
			break;
		case "03":
			recarga.setStatusRecargaServer(
					StatusRecargaServer.ESTORNADA);
			break;

		}
		return recarga;
	}

	public List<Recarga> getRecargaPorFone(String ddd, String fone) throws ErroException {

		List<Recarga> recargas = hDaoRecarga.getRecargaPorFone(ddd, fone);

		List<Recarga> recargasReturn = new ArrayList<Recarga>();
		
		Recarga recarga;
		for (int i = 0; i < recargas.size(); i++) {
			recarga = recargas.get(i);
			if (recarga.getStatusRecargaServer() == null
					&& recarga.getCodOnline() != null) {
				String status = this.buscaStatusTransacao_CodOnline(recargas
						.get(i).getCodOnline());

				recarga = this.ajustaStatusRecargaServer(recarga, status);

				if (recarga.getStatusRecargaServer() != null)
					hDaoRecarga.merge(recarga);
			}

			recargasReturn.add(recarga);
		}
		
		return recargasReturn;
	}
	public List<Recarga> getRecargaPorPin() throws ErroException{
		List<Recarga> recargas = hDaoRecarga.getRecargaPin();		
		List<Recarga> recargasReturn = new ArrayList<Recarga>();
		
		Recarga recarga;
		for (int i = 0; i < recargas.size(); i++) {
			recarga = recargas.get(i);
			if (recarga.getStatusRecargaServer() == null
					&& recarga.getCodOnline() != null) {
				String status = this.buscaStatusTransacao_CodOnline(recargas
						.get(i).getCodOnline());

				recarga = this.ajustaStatusRecargaServer(recarga, status);

				if (recarga.getStatusRecargaServer() != null)
					hDaoRecarga.merge(recarga);
			}

			recargasReturn.add(recarga);
		}
		
		return recargasReturn;
	}

	

}