package br.com.becb.middleware.controladores;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {



	@RequestMapping("/")
	public String index(Map<String, Object> model ) {
	
		return "index";
	
	}
	
	@RequestMapping("/admin")
	public String admin(Map<String, Object> model ) {
	
		return "admin";
	
	}
	// getters e setters
}