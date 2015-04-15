package br.com.becb.middlewarerecarga.entidades.rv.jax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConversoesRV {

	public ConversoesRV() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Date converterDataRV(String sData){
		
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 

	     

	      Date t; 

	      try { 
	          t = ft.parse(sData); 
	          
	          return t;
	      } catch (ParseException e) { 
	          System.out.println("Unparseable using " + ft); 
	      }
	   
		return null;
		

	}
public static  String converterDataRV(Date data, String formato){
		
		SimpleDateFormat ft = new SimpleDateFormat(formato); 
	
		String dateFormat = ft.format(data);
		return dateFormat;
	
		

	}

}
