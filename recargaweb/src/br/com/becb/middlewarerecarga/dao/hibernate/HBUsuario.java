package br.com.becb.middlewarerecarga.dao.hibernate;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.becb.middlewarerecarga.entidades.Usuario;

@Transactional(propagation=Propagation.SUPPORTS)
@Repository("hDaoUsuario")
public class HBUsuario extends HBDAO<Usuario> {
	
	

	
	protected Class getClazz() {
		return Usuario.class;
	}

	public Usuario getUsuario(String login, String senha) {
		Query query = getSession().createQuery("from Usuario usr where usr.login = ? and usr.hashSenha = ?");
		query.setString(0, login);
		query.setString(1, DigestUtils.sha256Hex(senha));
		return (Usuario) query.uniqueResult();				   
	}

	public Usuario getUsuario(String login) {
		Query query = getSession().createQuery("from Usuario usr where usr.login = ?");
		query.setString(0, login);
		return (Usuario) query.uniqueResult();
	}
	
	public Usuario salvarUsuario(Usuario user){
		if(null == getUsuario(user.getLogin() ) )
				persistir(user);
		return user;
		
	}

	
	
}
