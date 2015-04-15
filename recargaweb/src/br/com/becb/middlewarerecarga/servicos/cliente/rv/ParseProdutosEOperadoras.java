package br.com.becb.middlewarerecarga.servicos.cliente.rv;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateDDDProduto;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateProduto;
import br.com.becb.middlewarerecarga.dao.jdbc.JDBCDDDProduto;
import br.com.becb.middlewarerecarga.dao.jdbc.JDBCOperadora;
import br.com.becb.middlewarerecarga.dao.jdbc.JDBCProduto;
import br.com.becb.middlewarerecarga.entidades.DDDProduto;
import br.com.becb.middlewarerecarga.entidades.Operadora;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.entidades.rv.jax.CellCard;
import br.com.becb.middlewarerecarga.entidades.rv.jax.OperadoraRV;
import br.com.becb.middlewarerecarga.entidades.rv.jax.ProdutoRV;

@Component("parseProdutosEOperadoras")
public class ParseProdutosEOperadoras {

	@Autowired
	private HibernateDDDProduto<DDDProduto> hDaodddProduto;

	@Autowired
	private JDBCDDDProduto jdbcDaodddProduto;

	@Autowired
	private JDBCProduto jdbcDaoProduto;

	@Autowired
	private JDBCOperadora jdbcDaoOperadora;

	@Autowired
	private HibernateProduto<Produto> hDaoProduto;

	private List<Operadora> listaOperadoras;

	public ParseProdutosEOperadoras() {
		// TODO Auto-generated constructor stub
	}

	public Operadora parseProduto(NodeList nl) {
		return null;
	}

	/**
	 * utilizado para ler XML da RVTecnologia
	 * 
	 * @param xml
	 * @return
	 */
	public List<Operadora> parseOperadoraJaxXML(String xml) {
		try {
			JAXBContext context = JAXBContext.newInstance(CellCard.class);
			Unmarshaller un = context.createUnmarshaller();
			CellCard cell = (CellCard) un.unmarshal(new StringReader(xml));

			// this.printOperadoras(cell);

			return this.createOperadoras(cell);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void printOperadoras(CellCard cellcard) {
		OperadoraRV operadora;
		ProdutoRV produto;
		for (int i = 0; i < cellcard.getOperadoras().getOperadoras().size(); i++) {
			operadora = cellcard.getOperadoras().getOperadoras().get(i);

			if (null != operadora.getProdutos()) {

				try {
					System.out.println(operadora.getNomeOperadora() + " "
							+ operadora.getProdutos().getProdutosRV().size()
							+ "  PIN: " + operadora.getNomeRecargaOperadora());
					for (int j = 0; j < cellcard.getOperadoras()
							.getOperadoras().get(i).getProdutos()
							.getProdutosRV().size(); j++) {
						produto = cellcard.getOperadoras().getOperadoras()
								.get(i).getProdutos().getProdutosRV().get(j);
						System.out.println("\t" + produto.getNomeProduto()
								+ " Venda " + produto.getPrecovendaProduto()
								+ " Maximo" + produto.getValorMaximoProduto());
						if (produto.getDddProduto() != null)
							for (int d = 0; d < produto.getDddProduto().dddProdutos
									.size(); d++) {
								System.out.println("\t  DDD "
										+ produto.getDddProduto().dddProdutos
												.get(d));
							}

					}
				} catch (Exception e) {
					System.out.println("\t Não tem produto");
				}
			}

		}
	}

	private List<Operadora> createOperadoras(CellCard cellcard) {
		OperadoraRV operadora;

		try{
			List<Operadora> operadoras = new ArrayList<Operadora>();
		
		if (cellcard.getOperadoras() != null
				&& cellcard.getOperadoras().getOperadoras() != null) {
			this.desativarProdutos();
			
			for (int i = 0; i < cellcard.getOperadoras().getOperadoras().size(); i++) {
				operadora = cellcard.getOperadoras().getOperadoras().get(i);
				operadoras.add(this.createOperadoras(operadora));
			}

			return operadoras;
		}}catch(Exception e){
			this.ativarProdutosEmCasoDeErro();
			e.printStackTrace();
		}
		return null;
	}

	public void desativarProdutos() {
		jdbcDaoProduto.desativarProdutos();
	}
	private void ativarProdutosEmCasoDeErro(){
		jdbcDaoProduto.ativarProdutoEmCasoDeErro();
	}

	private Operadora createOperadoras(OperadoraRV oRV) throws Exception {

		Operadora op = new Operadora();
		op.setCodigoOperadora(oRV.getCodigoOperadora());
		op.setNomeOperadora(oRV.getNomeOperadora());
		op.setNomeRecargaOperadora(oRV.getNomeRecargaOperadora());
		// op.setUltimaAtualizacaoOperadora(this.ajustaData(oRV.getUltimaAtualizacaoOperadora()));
		op.setUltimaAtualizacaoOperadora(oRV
				.getDataUltimaAtualizacaoOperadora());
		List<Produto> produtos = createProdutos(oRV.getProdutos()
				.getProdutosRV());

		this.salvarOperadora(op);

		try {
			// TODO desativar produto fora desse looping
			// jdbcDaoProduto.desativarProdutos();

			for (int i = 0; i < produtos.size(); i++) {
				Produto prod = produtos.get(i);
				op.setProduto(this.associaProdutoeOperadora(prod, op));
				this.salvarProduto(prod);

				jdbcDaoProduto.acertarAssociacaoOperadoraeProduto(prod.getId(),
						op.getId());
			}
		} catch (Exception e) {
			// jdbcDaoProduto.ativarProdutoEmCasoDeErro();

			throw e;
		}

		return op;
	}

	private List<Produto> createProdutos(List<ProdutoRV> produtosRV) {
		List<Produto> produtos = new ArrayList<Produto>();
		Produto prod;
		ProdutoRV pRV;
		DDDProduto dddProduto;
		if (null != produtosRV)
			for (int p = 0; p < produtosRV.size(); p++) {
				pRV = produtosRV.get(p);

				prod = new Produto();
				prod.setCodigoProduto(pRV.getCodigoProduto());
				prod.setNomeProduto(pRV.getNomeProduto());
				prod.setPrecoCompraProduto(pRV.getPrecocompraProduto());
				prod.setPrecoVendaProduto(pRV.getPrecovendaProduto());
				prod.setUltima_atualizacaoProduto(pRV
						.getDataUltima_atualizacaoProduto());
				prod.setValidadeProduto(pRV.getValidadeProduto());
				prod.setValorIncrementoProduto(pRV.getValorIncrementoProduto());
				prod.setValorMaximoProduto(pRV.getValorMaximoProduto());
				prod.setValorMinimoProduto(pRV.getValorMinimoProduto());
				prod.setModeloRecarga(pRV.getModeloRecarga());
				prod.setMsgHabilitacaoProduto(pRV.getMsgHabilitacaoProduto());
				prod.setAtivo(true);

				if (null != pRV.getDddProduto().dddProdutos)
					for (int d = 0; d < pRV.getDddProduto().dddProdutos.size(); d++) {
						dddProduto = new DDDProduto(Integer.parseInt(pRV
								.getDddProduto().dddProdutos.get(d)));

						dddProduto = associaDDDeProduto(dddProduto, prod);
						prod.setDddProduto(dddProduto);
					}

				produtos.add(prod);
			}

		return produtos;
	}

	private DDDProduto associaDDDeProduto(DDDProduto dddProduto, Produto prod) {
		// DDDProduto dddP = hDaodddProduto.verificarDDDExistente(dddProduto);
		DDDProduto dddP = jdbcDaodddProduto.inserirDDD(dddProduto);
		dddP.setProduto(prod);
		return dddP;
	}

	private Produto salvarProduto(Produto prod) throws Exception {

		/*
		 * 1 - verificar existencia do produto ok 1.2 - se existir setar ativo
		 * como true ok; 2 - se não existir inserir ok 3 - verificar associação
		 * com DDD 4 - Se não existir inserir associação
		 */
		try {
			// 1 - 1.2
			Produto produto = jdbcDaoProduto.getProdutoParaAtivacao(prod
					.getCodigoProduto());
			// 2
			if (null == produto) {
				prod = jdbcDaoProduto.inserirProduto(prod);

			} else {
				prod.setId(produto.getId());
			}

			if (prod.getDddProduto() != null)
				for (int i = 0; i < prod.getDddProduto().size(); i++) {
					// 3 - 4 salvar a associação do DDD e Produto
					jdbcDaodddProduto.acertarAssociacaoProdutoEdddProduto(
							prod.getId(), prod.getDddProduto().get(i).getId());
				}
		} catch (Exception e) {
			throw e;
		}
		return prod;
	}

	private Operadora salvarOperadora(Operadora operadora) {
		/*
		 * 1 - verificar existencia do operadora 2 - se não existir inserir 3 -
		 * verificar associação com Produto 5 - Se não existir inserir
		 * associação
		 */

		operadora = jdbcDaoOperadora.salvarOperadora(operadora);

		return operadora;
	}

	private Produto associaProdutoeOperadora(Produto produto,
			Operadora operadora) {

		produto.setOperadora(operadora);

		return produto;
	}

	public List<Operadora> getListaOperadoras() {
		return listaOperadoras;
	}

	public void setListaOperadoras(List<Operadora> listaOperadoras) {
		this.listaOperadoras = listaOperadoras;
	}

	private Date ajustaData(String sData) {
		Date data = new Date();
		data.setYear(Integer.parseInt(sData.substring(0, 3)));

		return data;

	}

	public HibernateDDDProduto<DDDProduto> gethDaodddProduto() {
		return hDaodddProduto;
	}

	public void sethDaodddProduto(HibernateDDDProduto<DDDProduto> hDaodddProduto) {
		this.hDaodddProduto = hDaodddProduto;
	}

	public HibernateProduto<Produto> gethDaoProduto() {
		return hDaoProduto;
	}

	public void sethDaoProduto(HibernateProduto<Produto> hDaoProduto) {
		this.hDaoProduto = hDaoProduto;
	}

}
