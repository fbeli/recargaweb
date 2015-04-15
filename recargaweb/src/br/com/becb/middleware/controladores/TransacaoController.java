package br.com.becb.middleware.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.becb.middlewarerecarga.dao.hibernate.HibernateRecarga;
import br.com.becb.middlewarerecarga.entidades.Recarga;
import br.com.becb.middlewarerecarga.entidades.StatusRecargaServer;
import br.com.becb.middlewarerecarga.exceptions.ErroException;
import br.com.becb.middlewarerecarga.servicos.RecarregarService;
import br.com.becb.middlewarerecarga.servicos.TransacaoService;

@Controller
public class TransacaoController {

	@Autowired
	RecarregarService recarregarService;
	@Autowired
	TransacaoService transacaoService;

	@RequestMapping(value = "admin/verificaTransacao")
	public ModelAndView verificarTransacao(
			@RequestParam(required = false, value = "ddd") String ddd,
			@RequestParam(required = false, value = "fone") String fone) {

		ModelAndView resultado = new ModelAndView();

		List<Recarga> recargas;
		try {
			recargas = transacaoService.getRecargaPorFone(ddd, fone);
			resultado.addObject("recargas", recargas);
			resultado.setViewName("listaTransacoes");

		} catch (ErroException e) {
			resultado.addObject("erro", e);

			resultado.setViewName("erroRecarga");
		}

		return resultado;
	}

	@RequestMapping(value = "admin/listarTransacaoPin")
	public ModelAndView verificarTransacao() {

		ModelAndView resultado = new ModelAndView();

		List<Recarga> recargas = null;
		try {
			recargas = transacaoService.getRecargaPorPin();

		} catch (ErroException e) {
			resultado.addObject("erro", e);

			resultado.setViewName("erroRecarga");

		}

		resultado.addObject("recargas", recargas);
		resultado.setViewName("listaTransacoes");
		return resultado;
	}

	@RequestMapping(value = "admin/listarTransacaoPorId")
	public ModelAndView verificarTransacao(
			@RequestParam(required = false, value = "id") String id) {

		ModelAndView resultado = new ModelAndView();

		Recarga recarga = recarregarService.getRecargaById(id);
		resultado.addObject("recarga", recarga);

		if (null != recarga.getPin() && recarga.getPin() != "") {
			resultado.setViewName("confirmacaoDeRecargaPIN");
		} else
			resultado.setViewName("confirmacaoDeRecarga");
		return resultado;

	}
	
	@RequestMapping(value = "admin/listarTransacaoPendente")
	public ModelAndView verificarTransacaoPendente() {

		ModelAndView resultado = new ModelAndView();

		List<Recarga> recargas = null;
		try {
			recargas = transacaoService.buscaTransacoesPendentes();

		} catch (ErroException e) {
			resultado.addObject("erro", e);

			resultado.setViewName("erroRecarga");

		}

		resultado.addObject("recargas", recargas);
		resultado.setViewName("listaTransacoes");
		return resultado;
	}

}
