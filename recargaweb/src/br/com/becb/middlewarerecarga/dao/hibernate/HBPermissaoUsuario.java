package br.com.becb.middlewarerecarga.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.PermissaoUsuario;
import br.com.becb.middlewarerecarga.entidades.Usuario;

@Repository("daoPermissaoUsuario")
public class HBPermissaoUsuario<T>  extends HBDAO<PermissaoUsuario> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<PermissaoUsuario> getPermissoesUsuario(Usuario usuario) {
		if (usuario == null) {
			return new ArrayList<PermissaoUsuario>();
		}
		Query query = sessionFactory.getCurrentSession().createQuery("from PermissaoUsuario pu where pu.usuario = ?");
		query.setEntity(0, usuario);
		return query.list();
	}

	/**
	 * 
	 * @param role "ROLE_ANONYMOUS,ROLE_ADMIN,ROLE_MEMBRO, ROLE_VENDEDOR"
	 * @param usuario
	 * @return
	 */
	public void addRole(String role, Usuario usuario) {
	
		switch (role) {
		
		case "ROLE_ADMIN":
			this.persistir("ROLE_ADMIN", usuario);;
		case "ROLE_VENDEDOR":
			this.persistir("ROLE_VENDEDOR", usuario);;
		case "ROLE_MEMBRO":
			this.persistir("ROLE_MEMBRO", usuario);;
			
		default:
			break;
		}
		
		
	}

	private void persistir(String role, Usuario usuario){
		if (role != null && usuario != null) {
			PermissaoUsuario permissao = new PermissaoUsuario();
			permissao.setRole(role);
			permissao.setUsuario(usuario);
			persistir(permissao);
			//getSession().saveOrUpdate(permissao);
		}
	}
	
	
	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

}
