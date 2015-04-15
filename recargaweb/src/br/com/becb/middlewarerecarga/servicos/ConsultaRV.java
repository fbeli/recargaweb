package br.com.becb.middlewarerecarga.servicos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateOperadora;
import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.Operadora;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.cliente.rv.RecargaParseXML;

@Configuration
@PropertySource("classpath:rv.properties")
@Component("consultaRV")
public class ConsultaRV {

	@Value("${url}")
	private String url;
	@Value("${loja_primaria}")
	private String loja;
	@Value("${senha_primaria}")
	private String senha;
	@Value("${nome_primario}")
	private String nome_primario;
	@Value("${versao}")
	private String versao;
	@Value("${recargaOnline}")
	private String codRecargaOnline;
	@Value("${confirmaTransacao}")
	private String confirmaTransacao;

	@Value("${consultaStatus}")
	private String consultaStatus;
	@Value("${listaTransacoesPendentes}")
	private String listaTransacoesPendentes;

	@Autowired
	private RecargaParseXML parse;
	@Autowired
	private HibernateErro<Erro> daoErro;
	@Autowired
	private HibernateOperadora<Operadora> daoOperadora;

	public ConsultaRV() {

	}

	@SuppressWarnings("finally")
	/**
	 * Consulta padrão ao cliente CellCard
	 * @param params
	 * @return
	 */
	public String fazerConsulta(List<NameValuePair> params) {
		String responseBody = "";

		// criar por interceptor

		params.add(new BasicNameValuePair("nome_primario", this.nome_primario));
		params.add(new BasicNameValuePair("loja_primaria", this.loja));
		params.add(new BasicNameValuePair("senha_primaria", this.senha));
		params.add(new BasicNameValuePair("versao", versao));
		if (getCodigoTransacao() != "")
			params.add(new BasicNameValuePair("codigo_transacao",
					getCodigoTransacao()));

		//this.imprimeParams(params);
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);

			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpclient.execute(httppost, responseHandler);

		} catch (HttpHostConnectException he) {

			daoErro.persistir(EntityFabric
					.createErro(200,
							"Não foi possível conectar ao servidor de consultas de serviços"));
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		} finally {
			Logar.info(responseBody);
			return responseBody;
		}

	}

	private void imprimeParams(List<NameValuePair> params) {

		System.out.println("*********************************");
		for (int i = 0; i < params.size(); i++) {

			System.out.println(params.get(i).getName() + " - "
					+ params.get(i).getValue());

		}

	}

	private String fazerConsulta(String codigoTransacao) {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		try {
			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("nome_primario",
					this.nome_primario));
			params.add(new BasicNameValuePair("loja_primaria", this.loja));
			params.add(new BasicNameValuePair("senha_primaria", this.senha));
			params.add(new BasicNameValuePair("codigo_transacao",
					codigoTransacao));
			params.add(new BasicNameValuePair("versao", this.versao));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httppost, responseHandler);
			salvarRetorno(responseBody);
			return responseBody;
		} catch (HttpHostConnectException he) {

			daoErro.persistir(EntityFabric
					.createErro(200,
							"Não foi possível conectar ao servidor de consultas de serviços"));
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		}

		return null;
	}

	private void salvarRetorno(String conteudo) {

		FileWriter fw;
		BufferedWriter bw;
		try {
			File file = new File("XML_Operadoras.txt");

			System.out.println(" Impresso em: "+file.getAbsoluteFile());
			
			if (!file.exists())
				file.createNewFile();

			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(conteudo);

			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 
	 * @param codigoTransacao
	 *            - Saber o tipo de consulta deve ser feito. ver em properties
	 */
	public void atualizarListaProdutos(String codigoTransacao) {
		try {
			List<Operadora> operadoras = parse.parseOperadora(this
					.fazerConsulta(codigoTransacao));
			/**
			 * if(null!=operadoras){ for(int i =0 ;i<operadoras.size();i++){
			 * daoOperadora.persistir(operadoras.get(i)); }
			 */
			System.out.println("Adicionadas " + operadoras.size());
		} catch (ErroException e) {
			daoErro.persistir(e.getErro());
			e.printStackTrace();
		}

	}

	private static String classeChamadora() {
		Throwable thr = new Throwable();
		thr.fillInStackTrace();
		StackTraceElement[] ste = thr.getStackTrace();
		return ste[3].getMethodName();
	}

	public String getCodigoTransacao() {
		String codigo = "";
		String classeChamadora = classeChamadora();
		switch (classeChamadora) {
		case "buscaTransacoesPendentes":
			codigo = listaTransacoesPendentes;
			break;

		case "buscaStatusTransacao_CodOnline":
			codigo = consultaStatus;
			break;

		case "buscaStatusTransacao_idRecarga":
			codigo = consultaStatus;
			break;

		default:
			break;
		}

		return codigo;
	}

	@Required
	@Autowired
	public void setParse(RecargaParseXML parse) {
		this.parse = parse;
	}

	public RecargaParseXML getParse() {
		return parse;
	}

	public HibernateErro<Erro> getDaoErro() {
		return daoErro;
	}

	public void setDaoErro(HibernateErro<Erro> daoErro) {
		this.daoErro = daoErro;
	}

	public HibernateOperadora<Operadora> getDaoOperadora() {
		return daoOperadora;
	}

	public void setDaoOperadora(HibernateOperadora<Operadora> daoOperadora) {
		this.daoOperadora = daoOperadora;
	}
}
