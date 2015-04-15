package br.com.becb.middlewarerecarga.servicos.cliente.rv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardRecarga;
import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardRecargaPin;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.ConsultaRV;

@Configuration
@PropertySource("classpath:rv.properties")
@Component("servicoAcessoRV")
public class ServicoAcessoRV {
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
	@Value("${recargaPIN}")
	private String recargaPIN;
	
	@Value("${consultaStatus}")
	private String consultaStatus;
	@Value("${listaTransacoesPendentes}")
	private String listaTransacoesPendentes;
	
	@Autowired
	ConsultaRV consultaRV;
	@Autowired
	ParseRecarga parseRecarga;
	
	public ServicoAcessoRV() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param idRecarga Id da recarga a ser confirmada ou cancelada
	 * @param codRetorno 0 confirma transação  | 1 Cancela transação
	 * @return
	 * @throws ErroException 
	 */
	public CellCardRecarga confirmarTransacao(String idRecarga, String codRetorno) throws ErroException{
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("nome_primario",
				this.nome_primario));
		params.add(new BasicNameValuePair("loja_primaria", this.loja));
		params.add(new BasicNameValuePair("senha_primaria", this.senha));
		params.add(new BasicNameValuePair("codigo_transacao", confirmaTransacao));
		params.add(new BasicNameValuePair("versao", versao));		
		params.add(new BasicNameValuePair("compra", idRecarga));
		params.add(new BasicNameValuePair("cod_retorno", codRetorno));		

		Iterator i = params.iterator();
		BasicNameValuePair bp;
		while(i.hasNext()){
			bp= (BasicNameValuePair) i.next();
			System.out.println(bp.getName() +"  "+bp.getValue());
		}
		
		String retorno = consultaRV.fazerConsulta(params);
		
		CellCardRecarga cell = parseRecarga.parserecargaOnline(retorno);
		
		
		return cell;
	}
	
	/**
	 * 
	 * @param idProduto Código do produto a ser utilizado
	 * @param ddd ddd do telefone a ser recarregado
	 * @param fone número do telefone a ser recarregado
	 * @param usuarioLocal usuário que está operando a máquina, ou null
	 * @param id_terminal id do terminal, se existir
	 * @throws ErroException 
	 */
	
	public CellCardRecarga solicitarRecargaOnline(String idProduto,String ddd, 
			String fone, String usuarioLocal, String id_terminal, String codigoDeCompra,
			String valor) throws ErroException{
		//criar por interceptor
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("nome_primario",	this.nome_primario));
		params.add(new BasicNameValuePair("loja_primaria", this.loja));
		params.add(new BasicNameValuePair("senha_primaria", this.senha));
		params.add(new BasicNameValuePair("codigo_transacao", codRecargaOnline));
		params.add(new BasicNameValuePair("versao", "3.95"));		
		params.add(new BasicNameValuePair("compra", codigoDeCompra));	
		params.add(new BasicNameValuePair("produto", idProduto));
		params.add(new BasicNameValuePair("ddd", ddd));
		params.add(new BasicNameValuePair("fone", fone));
		params.add(new BasicNameValuePair("valor", valor));
		params.add(new BasicNameValuePair("bitBoleto", "0"));
		params.add(new BasicNameValuePair("usuario_local", usuarioLocal));
		params.add(new BasicNameValuePair("id_terminal", id_terminal));
		
		
		Iterator i = params.iterator();
		BasicNameValuePair bp;
		while(i.hasNext()){
			bp= (BasicNameValuePair) i.next();
			System.out.println(bp.getName() +"  "+bp.getValue());
		}
		
		String retorno = consultaRV.fazerConsulta(params);
		CellCardRecarga cellcard = parseRecarga.parserecargaOnline(retorno);
		
		return cellcard;
	}
	
	/**
	 * 
	 * @param codProduto Máximo 9 posições.produto String Código do produto solicitado, de acordo com os valores retornados na transação 1
	 * @param uf
	 * @param usuarioLocal
	 * @param id_terminal
	 * @param idRecarga Código da Compra no sistema do cliente. Deve ser um sequencial único para cada transação (chave primária do cliente - não pode se repetir). Máximo 9 posições.
	 * @return
	 * @throws ErroException 
	 */
	public CellCardRecargaPin solicitarRecargaPIN(String codProduto,String uf, 
			 String usuarioLocal, String id_terminal, String idRecarga) throws ErroException{
		//criar por interceptor
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("nome_primario",	this.nome_primario));
		params.add(new BasicNameValuePair("loja_primaria", this.loja));
		params.add(new BasicNameValuePair("senha_primaria", this.senha));
		params.add(new BasicNameValuePair("codigo_transacao", recargaPIN));
		params.add(new BasicNameValuePair("versao", "3.95"));		
		params.add(new BasicNameValuePair("compra", idRecarga));	
		params.add(new BasicNameValuePair("produto", codProduto));
		params.add(new BasicNameValuePair("uf_terminal", uf));

		params.add(new BasicNameValuePair("bitBoleto", "0"));
		params.add(new BasicNameValuePair("usuario_local", usuarioLocal));
		params.add(new BasicNameValuePair("id_terminal", id_terminal));
		
		
		Iterator i = params.iterator();
		BasicNameValuePair bp;
		while(i.hasNext()){
			bp= (BasicNameValuePair) i.next();
			System.out.println(bp.getName() +"  "+bp.getValue());
		}
		
		String retorno = consultaRV.fazerConsulta(params);
		CellCardRecargaPin cellcard = parseRecarga.parserecargaPin(retorno);
		
		return cellcard;
	}
	
	

}
