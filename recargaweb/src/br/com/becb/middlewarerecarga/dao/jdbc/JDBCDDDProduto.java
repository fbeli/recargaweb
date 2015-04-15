package br.com.becb.middlewarerecarga.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.ProdutoDDDProduto;

@Repository("jdbcDaodddProduto")
public class JDBCDDDProduto extends JDBCBaseDao{

	
	
	@Qualifier("dataSourceJDBC")
	DataSource dataSource;
	
	public JDBCDDDProduto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Insere ddd se não existir na tabela.
	 * Se produto existente, retorna produto da Tabela;
	 * 
	 * @param dddProduto
	 * @return
	 */
	public DDDProduto inserirDDD(DDDProduto dddProduto){
		/*
		 * 1 - verificar se DDD consta na Base
		 * 2 - se constar atalizar dddProduto com o do método
		 * 3 - senão incluir DDD 
		 */
	
		DDDProduto dddProdutoTemp = getDDDProduto(dddProduto.getDdd());
		if(dddProdutoTemp != null)
			dddProduto.setId(dddProdutoTemp.getId());
		else{
			 inserirDDDProduto(dddProduto);
			 
		}
		return dddProduto;		
	}
	/**
	 * Busca DDDProduto através do DDD
	 * @param ddd
	 * @return
	 */
	public DDDProduto getDDDProduto(int ddd){
			
		Object[] parametros = {ddd};
		List<DDDProduto> dddProdutol = getJdbcTemplate()
				.query("select * from dddProduto where dddProduto = ?",parametros,
					new RowMapper<DDDProduto>(){
					public DDDProduto mapRow(ResultSet rs, int rowNum) throws SQLException{
					DDDProduto dddProduto = new DDDProduto();
					dddProduto.setId(rs.getInt("id"));
					dddProduto.setDdd(rs.getInt("dddProduto"));
					return dddProduto;
					}});
		if(dddProdutol.size()<1)
			return null;
		return dddProdutol.get(0);
	}
	
	public DDDProduto inserirDDDProduto(DDDProduto dddProduto){
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("dddProduto", dddProduto.getDdd());
		int id = new SimpleJdbcInsert(getDataSource())
		.withTableName("dddProduto")
		.usingGeneratedKeyColumns("id")
		.executeAndReturnKey(parametros).intValue();
		dddProduto.setId(id);
		
		return dddProduto;
	}
//	public boolean inserirListaDDDs(List<>)

	public void acertarAssociacaoProdutoEdddProduto(long idProduto, int idDDDProduto){
		
		Object[] parametros = {idProduto, idDDDProduto};
		List<ProdutoDDDProduto> lista = getJdbcTemplate()
				.query("select * from produto_dddproduto where produto_id =? and dddProduto_id = ?", parametros,
					new RowMapper<ProdutoDDDProduto>(){
					public ProdutoDDDProduto mapRow(ResultSet rs, int rowNum) throws SQLException{
					ProdutoDDDProduto associa = new ProdutoDDDProduto();
					associa.setIdDDDProduto(rs.getInt("dddProduto_id"));
					associa.setIdProduto(rs.getInt("produto_id"));
					return associa;
					}});
		if (null == lista || lista.size()<1){
			int linhasAdicionadas = 0;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dddProduto_id", idDDDProduto);
			map.put("produto_id", idProduto);
			linhasAdicionadas= new SimpleJdbcInsert(getDataSource())
			.withTableName("produto_dddproduto").execute(map);
		//	System.out.println("Relação entre Produto "+idProduto+	" DDDProduto: "+idDDDProduto );
		}
			
		//Parei AQUI!!
	}

	
}
