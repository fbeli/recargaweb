package br.com.becb.middlewarerecarga.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.becb.middlewarerecarga.entidades.Operadora;


public class JDBCOperadora extends JdbcDaoSupport {

	public JDBCOperadora() {
	
	}

	public Operadora salvarOperadora(Operadora op) {
		/*
		 * 1 - Verificas se operadora existe (codigo operadora) 2 - Se operadora
		 * não existir, salva-la 2 - Verificar atualização da data, se for
		 * diferente atualizar 3 - se operadora não existir
		 */

		long id_op = buscarOperadora(op);
		if (0==id_op) {

			HashMap<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codigoOperadora", op.getCodigoOperadora());
			parametros.put("ativo", "1");
			parametros.put("nomeOperadora", op.getNomeOperadora());
			parametros.put("ultimaAtualizacaoOperadora",
					op.getUltimaAtualizacaoOperadora());

			long id = new SimpleJdbcInsert(getDataSource())
					.withTableName("operadora").usingGeneratedKeyColumns("id")
					.executeAndReturnKey(parametros).intValue();

			op.setId(id);

		}else{
			op.setId(id_op);
		}
		return op;

	}

	public long buscarOperadora(Operadora operadora) {
		Object[] parametros = { operadora.getCodigoOperadora() };
	/*	long id = getJdbcTemplate()
				.queryForLong(
						"select * from operadora where codigoOperadora = ?",
						parametros);
		return id;
	}*/
		long id = 0;
		List<Operadora> operadoras = getJdbcTemplate().query("select * from operadora where codigoOperadora = ?",parametros,
				new RowMapper<Operadora>(){
				public Operadora mapRow(ResultSet rs, int rowNum) throws SQLException{
				Operadora operadora = new Operadora();
				operadora.setId(rs.getInt("id"));
				operadora.setCodigoOperadora(operadora.getCodigoOperadora());
				return operadora;
				}});
		
	if(operadoras != null && operadoras.size()>0){
		return operadoras.get(0).getId();
	}
	return id;
}
}