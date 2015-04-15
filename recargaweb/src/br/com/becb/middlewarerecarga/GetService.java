package br.com.becb.middlewarerecarga;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetService {

	public GetService(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}

	private String url = "https://www.cellcard.com.br/teste/integracao_xml.php?wsdl";

	public void html() {
		

		@SuppressWarnings("deprecation")
		org.apache.http.impl.client.AbstractHttpClient client = new DefaultHttpClient();
		// HttpClient client = new tHttpClient();
		HttpPost request = new HttpPost(url);
		HttpResponse response;
		InputStream content = null;
		try {
			response = client.execute(request);
			System.out.println(response.getEntity().getContent().toString());			;
			content = response.getEntity().getContent();
			System.out.println(content.toString());
			byte[] responseBody = request.getMethod().getBytes();
			System.out.println(responseBody.toString());
			content.close();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public void testeHtml() {
		URL url;
		InputStream content;
		HttpURLConnection connection;
		try {
			url = new URL(
					"https://www.cellcard.com.br/teste/integracao_xml.php");

			connection = (HttpURLConnection) url.openConnection();

			content = connection.getInputStream();

			System.out.println(content.toString());

			content.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void teste2() {
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		String url  =  "?codigo_transacao=1&loja_primaria=xxx&nome_primario=login&senha_primaria=senha&versao=3.94";
		// Create a method instance.
		System.out.println(this.url+url);
		HttpMethod method = new PostMethod(this.url+url);
		
		// HttpPost method = new HttpPost(url);
		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			System.out.println(new String(responseBody));

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

}
