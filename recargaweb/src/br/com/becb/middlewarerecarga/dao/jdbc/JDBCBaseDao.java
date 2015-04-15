package br.com.becb.middlewarerecarga.dao.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 
 * @author fred
 *
 *Utiliza JdbDaoSuport, template que facilita utiliza��o das conex�es e sess�es:
 *
 *Este padr�o � t�o comum que no Spring j� v�m uma implementa��o
bastante similar ao exemplo exposto anteriormente. Trata-se da classe
org.springframework.jdbc.core.support.JdbcDaoSupport. N�o � necess�rio
implementar o c�digo descrito acima para encapsular seu JdbcTemplate, basta que
suas classes estendam JdbcDaoSupport.
 */
public class JDBCBaseDao extends JdbcDaoSupport{
	
	
	


	/**
	 * Set the JDBC DataSource to be used by this DAO.
	 
	public final void setDataSource(DataSource dataSource) {
		if (this.jdbcTemplate == null || dataSource != this.jdbcTemplate.getDataSource()) {
			this.jdbcTemplate = createJdbcTemplate(dataSource);
			initTemplateConfig();
		}
	}*/
	protected JdbcTemplate createJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	public JDBCBaseDao()  {
		
		// TODO Auto-generated constructor stub
	}
	
	
	protected void initTemplateConfig() {
		
		
	}
	/**public final void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		initTemplateConfig();
	}
	public final JdbcTemplate getJdbcTemplate() {
		  return this.jdbcTemplate;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
*/
	
}
