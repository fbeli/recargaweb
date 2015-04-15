package br.com.becb.middlewarerecarga.servicos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateDDDProduto;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateProduto;
import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.EntityFabric;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.exceptions.ImpossivelCarregarProdutosException;

@Component("listaProdutosService")
public class ListaProdutosService {

	@Autowired
	private HibernateDDDProduto<DDDProduto> hDaoDDDProduto;

	@Autowired
	private HibernateProduto<Produto> hDaoProduto;

	@Autowired
	private HibernateErro<Erro> hDaoErro;

	@Autowired
	private ErroService erroService;

	public ListaProdutosService() {
		// TODO Auto-generated constructor stub
	}

	public List<Produto> buscarProdutos(String ddd, String operadora)
			throws ImpossivelCarregarProdutosException {
		Erro erro;
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			DDDProduto dddProduto = hDaoDDDProduto.buscarDDD(ddd);
			if (dddProduto == null || dddProduto.getProdutos() == null) {
				erro = erroService
						.createErro(
								"33",
								"Problemas para carregar lista de produtos. ListaProduto.buscarProduts(ddd,operadora). \n Favor informar ao Suporte");

				throw new ImpossivelCarregarProdutosException(erro);
			}
			Iterator<Produto> it = dddProduto.getProdutos().iterator();
			Produto prod;

			while (it.hasNext()) {
				prod = it.next();
				if (prod.getOperadora().getNomeOperadora().toUpperCase()
						.equals(operadora.toUpperCase())
						&& prod.isAtivo()) {
					produtos.add(prod);
				}
			}
			return produtos;
		} catch (Exception e) {
			e.printStackTrace();
			erro = erroService.createErro(	"33",
							"Problemas para carregar lista de produtos. ListaProduto.buscarProduts(ddd,operadora) \n Favor informar ao suporte");
			throw new ImpossivelCarregarProdutosException(erro);
		}
	}

	/**
	 * Listar todos os produtos
	 * 
	 * @return Lista de Produto
	 */
	public List<Produto> listarTodosProdutos() {
		return hDaoProduto.getTodosProdutos();
	}

	public HibernateDDDProduto<DDDProduto> gethDaoDDDProduto() {
		return hDaoDDDProduto;
	}

	public void sethDaoDDDProduto(HibernateDDDProduto<DDDProduto> hDaoDDDProduto) {
		this.hDaoDDDProduto = hDaoDDDProduto;
	}

	public HibernateErro<Erro> gethDaoErro() {
		return hDaoErro;
	}

	public void sethDaoErro(HibernateErro<Erro> hDaoErro) {
		this.hDaoErro = hDaoErro;
	}

}
