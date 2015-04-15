package br.com.becb.middlewarerecarga.suporte;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Suporte {

	public Suporte() {
		// TODO Auto-generated constructor stub
	}

	public static String conveterData(Date data, String formato) {

		SimpleDateFormat ft = new SimpleDateFormat(formato); 
		
		String dateFormat = ft.format(data);
		return dateFormat;
	
	}


}
