package br.com.becb.middleware.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.entidades.Produto;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.Usuario;
import br.com.becb.middlewarerecarga.exceptions.ConfirmacaoDeTransacaoException;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.exceptions.ImpossivelCarregarProdutosException;
import br.com.becb.middlewarerecarga.exceptions.ProdutoNaoExisteException;
import br.com.becb.middlewarerecarga.servicos.ErroService;
import br.com.becb.middlewarerecarga.servicos.ListaProdutosService;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;
import br.com.becb.middlewarerecarga.servicos.UserService;


@Controller
public class RecargasController {

	@Autowired
	ListaProdutosService listaProdutosService;

	@Autowired
	RecarregarService recarregarService;

	@Autowired(required = true)
	private HttpServletRequest request;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ErroService erroService;
	
	public RecargasController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/finalizarRecarga")
	public ModelAndView cadastrarRegarga(
				@RequestParam(required = false, value = "recarga") Recarga recarga,
				HttpSession sessao) {

		ModelAndView resultado = new ModelAndView();

		if(recarga == null)
			recarga = (Recarga)sessao.getAttribute("recarga");
		
		
		try {

			recarga = recarregarService.confirmarRecarga(recarga, "0");
			resultado.addObject("recarga", recarga);
			if(null != recarga.getPin() &&  recarga.getPin() !=""){
				resultado.setViewName("confirmacaoDeRecargaPIN");
			}else
				resultado.setViewName("confirmacaoDeRecarga");
			
			

		} catch (ErroException e) {
			resultado.addObject("erro", e);
			resultado.addObject("recarga", recarga);
			resultado.setViewName("erroRecarga");
			e.printStackTrace();
		}finally{
			sessao.removeAttribute("recarga");
		}

		
		return resultado;

	}

	/**
	 * 
	 * Solicita a recarga e Autoriza automaticamente
	 * 
	 * 
	 * @param operadora
	 * @param ddd
	 * @param fone
	 * @param valor
	 *            Recebido do form como codProduto
	 * @param sessao
	 * @return
	 */
	@RequestMapping(value = "/solicitarRecarga")
	public ModelAndView solicitarRecarga(
			@RequestParam(required = false, value = "operadora") String operadora,
			@RequestParam(required = false, value = "ddd") String ddd,
			@RequestParam(required = false, value = "fone") String fone,
			@RequestParam(required = false, value = "codProduto") String valor,
			HttpSession sessao) {

		ModelAndView resultado = new ModelAndView();
		Map<String, Produto> mapProduto = (Map<String, Produto>) sessao
				.getAttribute("produtos");
		;
	
		Produto produto = mapProduto.get(Float.parseFloat(valor)+"");
		Recarga recarga = null;

		String usuario = "";
		if( ((Usuario)sessao.getAttribute("usuario")) != null)
			usuario = ((Usuario)sessao.getAttribute("usuario")).getNome();
	
			try {
				recarga = recarregarService.executarRecarga(
						produto.getCodigoProduto(), ddd, fone,
						request.getRemoteAddr(), usuario)
						;
				
				if( userService.verificaRole("ROLE_VENDEDOR", sessao))
					resultado.setViewName("redirect:/finalizarRecarga");
				else
					resultado.setViewName("tipoPagamento");
			
			} catch (ErroException e) {
				
					resultado.addObject("erro", e);
					resultado.addObject("recarga", recarga);
					resultado.setViewName("erroRecarga");
					e.printStackTrace();

				
			}


		sessao.setAttribute("recarga", recarga);
		resultado.addObject("recarga", recarga);
		// Se logado vai direto pra página
		
		return resultado;
	}

	@RequestMapping(value = "/buscarRecargasDisponiveis")
	public ModelAndView buscarRecargasDisponiveis(
			@RequestParam(required = false, value = "operadora") String operadora,
			@RequestParam(required = false, value = "ddd") String ddd,
			@RequestParam(required = false, value = "fone") String fone,
			HttpSession sessao) {

		System.out.println(operadora + " " + ddd + " " + fone);

		List<Produto> produtos = null;
		ModelAndView resultado = new ModelAndView();
		try {
			produtos = listaProdutosService.buscarProdutos(ddd,
					operadora);
		} catch (ImpossivelCarregarProdutosException e) {
			
			resultado.addObject("erro", e.getErro());
			resultado.setViewName("erroRecarga");
			
		}

		if (produtos == null) {
			
			resultado.addObject("erro", erroService.createErro("33", "Impossível carregar produtos"));
			resultado.setViewName("erroRecarga");
			
		} else {

			Map<String, Produto> mapProdutos = new HashMap<String, Produto>();
			for (int i = 0; i < produtos.size(); i++) {
				mapProdutos.put(produtos.get(i).getValorMaximoProduto() + "",
						produtos.get(i));
			}
			sessao.setAttribute("produtos", mapProdutos);
			resultado.addObject("produtos", produtos);
			resultado.addObject("ddd", ddd);
			resultado.addObject("fone", fone);
			resultado.addObject("operadora", operadora);
			resultado.setViewName("valores");
		}
		return resultado;
	}

	@RequestMapping(value = "/buscarProdutos")
	public ModelAndView buscarRecargasDisponiveis(){
		
		List<Produto> produtos = null;
		ModelAndView resultado = new ModelAndView();
		
		

		produtos = listaProdutosService.listarTodosProdutos();

		resultado.addObject("produtos", produtos);
		resultado.setViewName("produtos");
		return resultado;
	}
	
	
	
	public ListaProdutosService getListaProdutosService() {
		return listaProdutosService;
	}

	public void setListaProdutosService(
			ListaProdutosService listaProdutosService) {
		this.listaProdutosService = listaProdutosService;
	}

}
