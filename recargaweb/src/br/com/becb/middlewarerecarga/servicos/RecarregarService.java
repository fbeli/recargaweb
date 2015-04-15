package br.com.becb.middlewarerecarga.servicos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateErro;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateProduto;
import br.com.becb.middlewarerecarga.dao.hibernate.HibernateRecarga;
import br.com.becb.middlewarerecarga.entidades.Erro;
import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.StatusRecarga;
import br.com.becb.middlewarerecarga.entidades.enums.CodErro;
import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardRecarga;
import br.com.becb.middlewarerecarga.entidades.rv.recarga.jax.CellCardRecargaPin;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.exceptions.ProdutoNaoExisteException;
import br.com.becb.middlewarerecarga.servicos.cliente.rv.ServicoAcessoRV;

/**
 * Serviço para funções de recarga
 * @author fred
 *
 * @param <T>
 */
@Component("recarregarService")
public class RecarregarService<T> {

	@Autowired
	HibernateProduto<T> hDaoProduto;
	@Autowired
	HibernateErro<Erro> hDaoErro;
	@Autowired
	HibernateRecarga hDaoRecarga;
	
	@Autowired
	TransacaoService transacaoService;
	
	@Autowired
	ServicoAcessoRV servicoAcessoRV;
	
	public RecarregarService() {
	}
	
	
	/**
	 * Busca produto recacionado ao DDD e Operadora
	 * @param ddd
	 * @param Operadora
	 */
	public List<Produto> buscarProdutosAtivosParaDDDeOperadora(String ddd, String idOperadora){
		return hDaoProduto.getProdutosDDDeOperadora(ddd, idOperadora);
		
	}
	
	/**
	 * Serviço responsável pela execução da recarga
	 * Necessário ainda confirmar em confirmarRecarga()
	 * 
	 * @param codProduto Código do Produto
	 * @param ddd
	 * @param fone
	 * @param IP IP da máquina que está fazendo a solicitação
	 * @param usuario
	 * @return
	 * @throws ErroException 
	 */
	public Recarga executarRecarga(String codProduto, String ddd, String fone, String IP, String usuario) throws ErroException{
		
		Recarga recarga = new Recarga();
		try{
		
		//TODO criar AQUI o código da compra
		recarga = this.criarRecarga(codProduto, ddd, fone, IP, usuario);
		//TODO ajustar uruário e id máquina
		CellCardRecarga cellCard = servicoAcessoRV.solicitarRecargaOnline(codProduto, ddd, fone, recarga.getUsuarioLocal(), recarga.getIdTerminal(),recarga.getId()+"", recarga.getValor()+"");
		
		if(cellCard == null){
			recarga.setMensagem("Erro na execução da Recarga, tente novamente.");
		}
		updateRecarga(recarga,cellCard, StatusRecarga.SOLICITADO);
		
		
		}catch(ProdutoNaoExisteException e){
			hDaoErro.persistir(e.getErro());
			recarga.setMensagem("Valor solicitado para recarga não encontrado, tente novamente");
			throw e;
			
		}
		return recarga;
	
	}
	
	public  Recarga  executarRecargaPIN(String codProduto, String uf,  String IP, String usuario) throws ErroException{
		
		Recarga recarga = new Recarga();
		//try{
			//TODO criar AQUI o código da compra
			recarga = this.criarRecargaPin(codProduto, uf, IP, usuario);
			//TODO ajustar uruário e id máquina
			
			CellCardRecargaPin cellCard = servicoAcessoRV.solicitarRecargaPIN(codProduto, uf, recarga.getUsuarioLocal(), recarga.getIdTerminal(), recarga.getId()+"");
					
			
			if(cellCard == null){
				recarga.setMensagem("Erro na execução da Recarga, tente novamente.");
			}
			updateRecarga(recarga,cellCard, StatusRecarga.SOLICITADO);
			
			
		/*	}catch(ProdutoNaoExisteException e){
				hDaoErro.persistir((T) e);
				recarga.setMensagem("Valor solicitado para recarga não encontrado, tente novamente");
				throw e;
				
			}*/
			return recarga;
		
		}
		
	
	
	/**
	 * Serviço responsável  pela conclusão da recarga
	 * 
	 * @param idRecarga Id da recarga a ser confirmada ou cancelada
	 * @param opcao 0 confirma transção  | 1 Cancela transação
	 * @return
	 * @throws ErroException 
	 */
	public  Recarga confirmarRecarga(Recarga recarga, String opcao) throws ErroException{

		CellCardRecarga cellCard = 	servicoAcessoRV.confirmarTransacao(recarga.getId()+"", opcao);
		if(cellCard==null){
			throw new ConfirmacaoDeTransacaoException("Erro para Recarga id: "+recarga.getId());
		}
	
		if(cellCard.getCodTransacao().equals("7") && cellCard.getCodRetorno().equals("1"))
			updateRecarga(recarga,cellCard, StatusRecarga.CANCELADO);
		else
			updateRecarga(recarga,cellCard, StatusRecarga.EFETUADO);
		if(opcao.equals("0") && recarga.getStatusRecarga()!= StatusRecarga.EFETUADO){
			ProdutoNaoExisteException e = new ProdutoNaoExisteException("Recarga: "+recarga.getCodOnline()+" Problemas para concluir a transação");
			hDaoErro.persistir(e.getErro());
			throw e;
		
		}
		return recarga;
	}
	
	
	/**
	 * Criação da recraga para primeira solicitação de recarga
	 * @param codProduto
	 * @param ddd
	 * @param fone
	 * @param IP
	 * @param usuario
	 * @return
	 * @throws ProdutoNaoExisteException
	 */
	private Recarga criarRecarga(String codProduto, String ddd, String fone, 
			String IP,String usuario) throws ProdutoNaoExisteException{
		Produto produto = hDaoProduto.getProdutoPorCodigo(codProduto);
		Recarga recarga = new Recarga();
		if(produto.isAtivo()){	
		
			recarga.setDdd(ddd);
			recarga.setFone(fone);
			recarga.setIdTerminal(IP);
			recarga.setStatusRecarga(StatusRecarga.CADASTRADO);
			recarga.setProduto(produto);
			recarga.setCompra(produto.getPrecoCompraProduto());
			recarga.setValor(produto.getPrecoVendaProduto());
			recarga.setUsuarioLocal(usuario);
			
			this.salvarRecarga(recarga);
		
		}else{
		throw new ProdutoNaoExisteException("Produto com codigo: "+codProduto+ " não está ativo",
			CodErro.PRODUTOINATIVO+"",
			"Produto com codigo: "+codProduto+ " não está ativo");
		}		
		return recarga;
	}
	
	private Recarga criarRecargaPin(String codProduto, String uf, 
			String IP,String usuario) throws ProdutoNaoExisteException{
		Produto produto = hDaoProduto.getProdutoPorCodigo(codProduto);
		Recarga recarga = new Recarga();
		if(produto.isAtivo()){	
		
			recarga.setUf_terminal(uf);
			recarga.setIdTerminal(IP);
			recarga.setStatusRecarga(StatusRecarga.CADASTRADO);
			recarga.setProduto(produto);
			recarga.setCompra(produto.getPrecoCompraProduto());
			recarga.setValor(produto.getPrecoVendaProduto());
			recarga.setUsuarioLocal(usuario);
			
			this.salvarRecarga(recarga);
		
		}else{
		throw new ProdutoNaoExisteException("Produto com codigo: "+codProduto+ " não está ativo",
			CodErro.PRODUTOINATIVO+"",
			"Produto com codigo: "+codProduto+ " não está ativo");
		}		
		return recarga;
	}
	
	
	private Recarga salvarRecarga(Recarga recarga){
		
		
		hDaoRecarga.persistir(recarga);
		
		return recarga;
		
	}
	private Recarga updateRecarga(Recarga re, CellCardRecarga cellcard, StatusRecarga status) throws ErroException{
		if(cellcard!=null){
			if(status == StatusRecarga.SOLICITADO){
				re.setMensagem(cellcard.getMensagem());
				re.setCodOnline(cellcard.getCodOnline());
				re.setVencimento(cellcard.getVencimento());
				re.setNsu(cellcard.getNsu());
				re.setStatusRecarga(status);
				if(cellcard.getIdProduto()!=re.getProduto().getCodigoProduto())
					re.setIdProdutoAjustado(cellcard.getIdProduto());
				//hDaoRecarga.merge(re);
			}else {
				if(status == StatusRecarga.CANCELADO){
					re.setStatusRecarga(StatusRecarga.CANCELADO);
					re.setDataDeconfirmacao(new Date());
				}else{
					if(cellcard.getCodRetorno() != null && cellcard.getCodRetorno().equals("1"))
						re.setStatusRecarga(StatusRecarga.ERRONATRANSACAO);
					else{
						re.setStatusRecarga(status);
						re.setDataDeconfirmacao(new Date());	
					}
				}
			}
			
			transacaoService.ajustarStatusTransacao(re);			
		}
		return re;
	}
	
	private Recarga updateRecarga(Recarga re, CellCardRecargaPin cellcard, StatusRecarga status){
		if(cellcard!=null){
			if(status == StatusRecarga.SOLICITADO){
				re.setMensagem(cellcard.getMensagem());
				re.setCodOnline(cellcard.getCodOnline());
				re.setVencimento(cellcard.getVencimento());
				
				re.setDataRV(cellcard.getDataRV());
				re.setFace(cellcard.getFace());
				re.setLote(cellcard.getLote());
				re.setPago(cellcard.getPago());
				re.setPin(cellcard.getPin());
				re.setSerie(cellcard.getSerie());
				re.setVencimento(cellcard.getVencimento());
				re.setStatusRecarga(status);
				
				if(cellcard.getIdProduto()!=re.getProduto().getCodigoProduto())
					re.setIdProdutoAjustado(cellcard.getIdProduto());
			}else {
				
					re.setStatusRecarga(status);
					re.setDataDeconfirmacao(new Date());	
				
			}
			hDaoRecarga.merge(re);
		}
		return re;
	}
	
	public Recarga getRecargaById(long id){
		return hDaoRecarga.getRecargaPorId(id);
	}
	public Recarga getRecargaById(String id){
		return this.getRecargaById(Long.parseLong(id));
	}

}
