package br.com.becb.middlewarerecarga.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.DDDProduto;

@Repository("hDaodddProduto")
public class HibernateDDDProduto<T> extends HBDAO<T> {

	public HibernateDDDProduto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class getClazz() {
		return DDDProduto.class;
	}

	public DDDProduto salvar(DDDProduto dddProduto) {

		// DDDProduto dddProdutoQ = verificarDDDExistente(dddProduto);

		// if (dddProdutoQ == null || dddProduto.getDdd() !=
		// dddProdutoQ.getDdd()) {

		Session session = getSession();

		try {
			Transaction trans = session.beginTransaction();
			session.clear();
			session.saveOrUpdate(dddProduto);
			trans.commit();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (session.isOpen())
				session.close();
		}
		// }else
		// dddProduto = dddProdutoQ;

		return dddProduto;
	}

	public DDDProduto verificarDDDExistente(DDDProduto dddProduto) {

		String consulta = "from DDDProduto d where d.ddd =:ddd";
		Session session = getSession();
		//session.beginTransaction();
		Query query = session.createQuery(consulta);
		query.setInteger("ddd", dddProduto.getDdd());

		DDDProduto dddProdutoQ = (DDDProduto) query.uniqueResult();
		session.close();
		return dddProdutoQ;

	}
	
	public DDDProduto buscarDDD(String ddd){
		return this.buscarDDD(Integer.parseInt(ddd));
	}
	
	public DDDProduto buscarDDD(int ddd){
		String consulta = "from DDDProduto d where d.ddd =:ddd";
		Session session = getSession();
		//session.beginTransaction();
		Query query = session.createQuery(consulta);

		query.setInteger("ddd", ddd);
		DDDProduto dddProdutoQ = (DDDProduto) query.uniqueResult();
		session.close();
		return dddProdutoQ;

		
	}
}
