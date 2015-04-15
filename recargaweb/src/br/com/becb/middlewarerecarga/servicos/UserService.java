package br.com.becb.middlewarerecarga.servicos;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HBPermissaoUsuario;
import br.com.becb.middlewarerecarga.dao.hibernate.HBUsuario;
import br.com.becb.middlewarerecarga.entidades.PermissaoUsuario;
import br.com.becb.middlewarerecarga.entidades.Usuario;



@Component("userService") 
public class UserService {

	@Autowired
	private HBUsuario hDaoUsuario;
	
	@Autowired
	private HBPermissaoUsuario<PermissaoUsuario> hDaoPermissaoUsuario;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario novoUsuario(String username, String password, String nome, String role){
		Usuario user = new Usuario(username, password, nome);
		hDaoUsuario.persistir(user);
		hDaoPermissaoUsuario.addRole(role, user);
		
		user = hDaoUsuario.getUsuario(username);
		return user;
	}
	
	/**
	 * 
	 * @param role "ROLE_ANONYMOUS,ROLE_ADMIN,ROLE_MEMBRO, ROLE_VENDEDOR"
	 * @return
	 */
	public boolean verificaRole(String role, HttpSession sessao){
		boolean retorno = false;
		List<PermissaoUsuario> permissoes = (List<PermissaoUsuario>) sessao.getAttribute("permissoes");
		if( permissoes==null)
			return false;
		for (PermissaoUsuario permissaoUsuario : permissoes) {
			if(permissaoUsuario.getRole().equals(role)){
				return true;
			}
		}
		
		return retorno;
	}

}
