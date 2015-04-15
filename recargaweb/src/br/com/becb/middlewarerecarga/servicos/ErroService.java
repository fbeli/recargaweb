package br.com.becb.middlewarerecarga.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Erro;


@Component("ErroService")
public class ErroService {

	@Autowired
	HibernateErro<Erro> hDaoErro;
	
	public ErroService() {
		// TODO Auto-generated constructor stub
	}
	
	public Erro createErro(String codigo, String mensagem){
		Erro erro = null;
		try{
		erro = EntityFabric.createErro(codigo, mensagem);
		
		hDaoErro.persistir(erro);
		}catch(Exception e){
			
		}finally{
			return erro;
	
		}}
	
	public Erro createErro(Erro erro){
		try{
		
		hDaoErro.persistir(erro);
		}catch(Exception e){
			
		}finally{
			return erro;
	
		}}
	

}
