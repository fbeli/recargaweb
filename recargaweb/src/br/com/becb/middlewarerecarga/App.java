package br.com.becb.middlewarerecarga;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.becb.middlewarerecarga.servicos.AdicionarProdutosService;
import br.com.becb.middlewarerecarga.servicos.ConsultaRV;




/**
 * Hello world!
 *
 */
public class App 
{
	

    public static void main( String[] args ) 
    {
     String url = "https://www.cellcard.com.br/teste/integracao_xml.php";
       
         ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
         AdicionarProdutosService ap = (AdicionarProdutosService) context.getBean("adicionarProdutosService");
        ap.adicionarProdutos();
     // c2.atualizarListaProdutos(url);

    }
}
