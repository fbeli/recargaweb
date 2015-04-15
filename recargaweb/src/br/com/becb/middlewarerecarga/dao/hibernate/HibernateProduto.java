package br.com.becb.middlewarerecarga.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LazyInitializationException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.Operadora;
import br.com.becb.middlewarerecarga.entidades.Produto;

@Repository("hDaoProduto")
public class HibernateProduto<T> extends HBDAO<T> {

	public HibernateProduto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Class getClazz() {
		return Produto.class;
	}

	public T get(Long id) {
		return (T) getSession().get(Produto.class, id);
	}

	public DDDProduto salvar(DDDProduto dddProduto) {

		Session session = getSession();
		Transaction trans = session.beginTransaction();
		session.saveOrUpdate(dddProduto);
		trans.commit();
		if (session.isOpen())
			session.close();

		return dddProduto;
	}

	public Produto verificarProdutoExistente(Produto produto) {

		String consulta = "from Produto d where d.codigoProduto =:codigoProduto";
		Session session = getSession();
		session.beginTransaction();
		Query query = session.createQuery(consulta);
		query.setString("codigoProduto", produto.getCodigoProduto());

		Produto produtoQ = (Produto) query.uniqueResult();
		session.close();
		return produtoQ;

	}

	public Produto getProdutoPorCodigo(String codProduto){
		
		Produto produto = new Produto();
		String consulta = "from Produto o where o.codigoProduto =:codigoProduto";
		Session session = getSession();

		Query query = session.createQuery(consulta);
		query.setString("codigoProduto", codProduto);

		produto = (Produto) query.uniqueResult();
		session.close();
		
		return produto;
	}
	public List<Produto> getTodosProdutos(){
		
		
		List<Produto> produtos = new ArrayList<Produto>();
		String consulta = "from Produto p order by p.operadora";
		Session session = getSession();

		Query query = session.createQuery(consulta);
	

		produtos =  (List<Produto>) query.list();
		session.close();
		
		return produtos;
	}
	
	public List<Produto> getProdutosDDDeOperadora(String ddd, String idOperadora) {

		List<Produto> produtos = new ArrayList<Produto>();

		String consulta = "from DDDProduto o where o.ddd =:ddd";
		Session session = getSession();

		Query query = session.createQuery(consulta);
		query.setString("ddd", ddd);

		DDDProduto dddP = (DDDProduto) query.uniqueResult();
		session.close();
		if (dddP != null && dddP.getProdutos() != null) {

			for (int i = 0; i < dddP.getProdutos().size(); i++) {

				if (dddP.getProdutos().get(i) != null 
						&&	dddP.getProdutos().get(i).getOperadora().getId() == Integer.parseInt(idOperadora)
						&& dddP.getProdutos().get(i).isAtivo()) {
					
					produtos.add(dddP.getProdutos().get(i));
					

					
				}
			}

		}

		return produtos;

	}
}
