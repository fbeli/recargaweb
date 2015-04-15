package br.com.becb.middlewarerecarga.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.becb.middlewarerecarga.entidades.OperadoraProduto;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.entidades.rv.jax.ProdutoAtivo;
import br.com.becb.middlewarerecarga.servicos.Logar;


public class JDBCProduto extends JDBCBaseDao{
	

	public static List<ProdutoAtivo> produtosAtivos = null;
	DataSource dataSource;
	
	public JDBCProduto() {
		
	}
	

	
	/**
	 * retorna produto a partir do código do produto
	 * @param codigoProduto
	 * @return
	 */
	public Produto getProduto(String codigoProduto){
		
		boolean ativo = true;
		Object[] parametros = {codigoProduto};
		List<Produto> produtol = getJdbcTemplate().query("select * from produto where codigoProduto = ?",parametros,
					new RowMapper<Produto>(){
					public Produto mapRow(ResultSet rs, int rowNum) throws SQLException{
					Produto produto = new Produto();
					produto.setId(rs.getInt("id"));
					produto.setCodigoProduto(rs.getString("codigoProduto")
					);		
							
					
					return produto;
					}});
		if(produtol != null && produtol.size()>0){
			return produtol.get(0);
		}
		return null;
		
	}
	public Produto getProdutoParaAtivacao(String codigoProduto){
		
		Produto produto = this.getProduto(codigoProduto);
		if(produto != null){
			this.ativarProduto(produto.getId(), produto.getCodigoProduto());
			return produto;
		}
		
		return null;
	}
	public void ativarProduto(long idProduto, String codigoProduto){
		Object[] parametros = {"1", idProduto, codigoProduto};
		getJdbcTemplate()
		.update("update produto set ativo = ? where id = ? and codigoProduto =? ", parametros);
		
		
	}
	/**
	 * 
	 * @param idProduto
	 * @param codigoProduto
	 * @param ativo 1 para ativo, 0 para inativo
	 * @throws Exception Exceção pra o caso de ativo não ser 0 ou 1
	 */
	
	public void desativarProduto(long idProduto, String codigoProduto, int ativo) throws Exception{
		// TODO ler produto, salvar, desativar 
		//ler produtos
		// fazer uma matriz pra saber quais foram desativados para caso de erro.
		
		if(ativo > Integer.parseInt("1") ){
			Exception e =  new Exception("ativo tem que ser 0 ou 1. \n 0 inativo e 1 ativo");
		throw e;
		}
			
		
		Object[] parametros = {ativo, idProduto, codigoProduto};
		getJdbcTemplate()
		.update("update produto set ativo = ? where id = ? and codigoProduto =? ", parametros);
		
		
	}
	
	/**
	 * Desativa todos os produtos, usado somente ao carregar Operadoras. 
	 * Em caso de erro em alguma inserção, reatualizarOperadoras com atualizarOperadoras()
	 */
	public void desativarProdutos(){
		
		
		
		List<ProdutoAtivo> produtosAtivos = getJdbcTemplate().query("select * from produto where ativo = 1",
				new RowMapper<ProdutoAtivo>(){
				public ProdutoAtivo mapRow(ResultSet rs, int rowNum) throws SQLException{
					ProdutoAtivo produtoAtivo = new ProdutoAtivo();
					produtoAtivo.setIdProduto(rs.getInt("id"));
				return produtoAtivo;
				}});
		
		this.produtosAtivos = produtosAtivos;
		if(this.produtosAtivos != null)
		for(int x =0;x<this.produtosAtivos.size();x++){
			Logar.info("Produto ativo id:"+this.produtosAtivos.get(x).getIdProduto());
		}
		getJdbcTemplate()
		.update("update produto set ativo = 0");
		
		
		
	}
	/**
	 * eecutar sempre que utilizar desativarProdutos() para caso de erro na inserção de novo produto.
	 */
	public void ativarProdutoEmCasoDeErro(){
		for(int i = 0;i<this.produtosAtivos.size();i++){
			getJdbcTemplate()
			.update("update produto set ativo = 1 where id = ?", this.produtosAtivos.get(i).getIdProduto());
		}
	}
	
	
	public Produto inserirProduto(Produto pro){
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("PIN", pro.getPIN());
		parametros.put("ativo", Integer.parseInt("1"));
		parametros.put("codigoProduto", pro.getCodigoProduto());
		parametros.put("modeloRecarga", pro.getModeloRecarga());
		parametros.put("msgHabilitacaoProduto", pro.getMsgHabilitacaoProduto());
		parametros.put("nomeProduto", pro.getNomeProduto());
		parametros.put("precoCompraProduto", pro.getPrecoCompraProduto());
		parametros.put("precoVariavelProduto", pro.getPrecoVariavelProduto());
		parametros.put("precoVendaProduto", pro.getPrecoVendaProduto());
		parametros.put("ultima_atualizacaoProduto", pro.getUltima_atualizacaoProduto());
		parametros.put("validadeProduto", pro.getValidadeProduto());
		parametros.put("valorIncrementoProduto", pro.getValorIncrementoProduto());
		parametros.put("valorMaximoProduto", pro.getValorMaximoProduto());
		parametros.put("valorMinimoProduto", pro.getValorMinimoProduto());
		parametros.put("operadoraId", pro.getOperadora().getId());
		
		
		int id = new SimpleJdbcInsert(getDataSource())
		.withTableName("produto")
		.usingGeneratedKeyColumns("id")
		.executeAndReturnKey(parametros).intValue();
		
		pro.setId(id);
		
		return pro;
		
	
	}
	
	/**
	 * faz a associação de operadora de Produto
	 * @param idProduto
	 * @param idDDDProduto
	 */
	public void acertarAssociacaoOperadoraeProduto(long idProduto, long idOperadora){
		
		System.out.println("Produto: "+idProduto+" Operadora:"+idOperadora);
		Object[] parametros = {idProduto, idOperadora};
		List<OperadoraProduto> lista = getJdbcTemplate()
				.query("select * from operadora_produto where produtos_id =? and operadora_id = ?", parametros,
					new RowMapper<OperadoraProduto>(){
					public OperadoraProduto mapRow(ResultSet rs, int rowNum) throws SQLException{
					OperadoraProduto associa = new OperadoraProduto();
					associa.setIdOperadora(rs.getInt("operadora_id"));
					associa.setIdProduto(rs.getInt("produtos_id"));
					return associa;
					}});
		
		if (null == lista || lista.size()<1){
			int linhasAdicionadas = 0;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("operadora_id", idOperadora);
			map.put("produtos_id", idProduto);
			linhasAdicionadas= new SimpleJdbcInsert(getDataSource())
			.withTableName("operadora_produto").execute(map);
			
		}
			
		
	}
}
