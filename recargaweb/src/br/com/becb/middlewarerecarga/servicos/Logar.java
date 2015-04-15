package br.com.becb.middlewarerecarga.servicos;

import java.util.logging.Logger;

public class Logar {

	static Logger log = Logger.getLogger(Logar.class.getName());
	
	public static Logger getLog() {
		return log;
	}


	
	
	
	public Logar() {
		// TODO Auto-generated constructor stub
	}

	public static void info(String string){
		log.info(string);
		
	
	}
}
