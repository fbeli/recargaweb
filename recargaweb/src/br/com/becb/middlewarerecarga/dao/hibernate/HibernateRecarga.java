package br.com.becb.middlewarerecarga.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.Recarga;

@Repository("hDaoRecarga")
public class HibernateRecarga<T> extends HBDAO<T>  {

	public HibernateRecarga() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class getClazz() {
		return Recarga.class;
	}
	
	public List<Recarga> getRecargaPorFone(String ddd, String fone){
		
		List<Recarga> recargas;

		String consulta = "from Recarga r where r.ddd =:ddd and r.fone =:fone order by r.dataDeSolicitacao desc";
		Session session = getSession();
		
		Query query = session.createQuery(consulta);
		query.setString("ddd", ddd);
		query.setString("fone", fone);

		recargas =  query.list();
		session.close();
		return recargas;

		
	}
	public List<Recarga> getRecargaPin(){
		
		List<Recarga> recargas;

		String consulta = "from Recarga r where r.pin is not null order by r.dataDeSolicitacao desc";
		Session session = getSession();
		
		Query query = session.createQuery(consulta);
		

		recargas =  query.list();
		session.close();
		return recargas;

		
	}
	
	public Recarga getRecargaPorId(long id){
		
		Recarga recarga;

		String consulta = "from Recarga r where r.id =:id";
		Session session = getSession();
		
		Query query = session.createQuery(consulta);	
		query.setLong("id", id);

		recarga =  (Recarga) query.uniqueResult();
		session.close();
		return recarga;

		
	}
	
	

}
