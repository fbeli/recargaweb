package br.com.becb.middlewarerecarga.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.becb.middlewarerecarga.dao.hibernate.HBPermissaoUsuario;
import br.com.becb.middlewarerecarga.dao.hibernate.HBUsuario;
import br.com.becb.middlewarerecarga.entidades.PermissaoUsuario;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.servicos.AdicionarProdutosService;

/**
 * Exemplo de authentication provider
 * @author kicolobo
 */
public class SFAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private HBUsuario daoUsuario;
	@Autowired
	private HBPermissaoUsuario<PermissaoUsuario> daoPermissao;
	
	
	@Autowired
	private AdicionarProdutosService AdicionarProdutosService;
	
	@Autowired(required = true)
	private HttpServletRequest request;
	
	
	
	public Authentication authenticate(Authentication auth)	throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		String username = token.getName();
		String senha    = token.getCredentials() != null ? token.getCredentials().toString() : null;
		Usuario usuario = daoUsuario.getUsuario(username, senha);
		if (usuario == null) {
			return null;
		}
		List<PermissaoUsuario> permissoes = daoPermissao.getPermissoesUsuario(usuario);
		SFAuthentication resultado = new SFAuthentication(usuario, permissoes);
		resultado.setAuthenticated(usuario != null);
		
		
		request.getSession().setAttribute("permissoes", permissoes);
		request.getSession().setAttribute("usuario", usuario);
		request.getSession().setAttribute("username", usuario.getNome()+"  ");
		//if (null == AdicionarProdutosService.atualizacao || AdicionarProdutosService.atualizacao.getDay() !=  (new Date()).getDay())
			//AdicionarProdutosService.adicionarProdutos();
		return resultado;
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
