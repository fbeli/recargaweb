package br.com.becb.middleware.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.servicos.UserService;


@Controller
public class UserControler {

	@Autowired
	private UserService userService;
	
	
	public UserControler() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/admin/cadastrarUser")
	public ModelAndView cadastrarRegarga(
				@RequestParam(required = false, value = "nome") String nome,
				@RequestParam(required = false, value = "login") String username,
				@RequestParam(required = false, value = "senha") String password,
				@RequestParam(required = false, value = "role") String role,
				HttpSession sessao) {

		ModelAndView resultado = new ModelAndView();
		
		Usuario user = userService.novoUsuario(username, password, nome, role);
		
		resultado.addObject("mensagem", user.getNome()+" - "+user.getLogin() +" - Cadastrado com sucesso!");
		
		resultado.setViewName("admin");
			
	return resultado;	
	}

}
