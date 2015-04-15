<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <link href="<%=request.getContextPath()%>/recursos/estilos/recargahome.css" rel="stylesheet" type="text/css" />


<div id="borda">

	<div id="bilhete">
		<div id="icones" align="right">
				<img src="<%=request.getContextPath()%>\recursos\imagem\print.png" onclick="window.print();" width="50" height="50" id="print" /> <br>

		</div>
			<div id="printable">
			
			<h2 id="valor" align="center">
				${recarga.produto.operadora.nomeOperadora}
				
				<br>
				${recarga.ddd} ${recarga.fone}
				<br><br>
			</h2>
				<h3 id="valor">

				Identificação local: ${recarga.id}<br>

Valor Recarga: <fmt:formatNumber value="${recarga.valor}" type="currency"/><br>

PIN:  ${recarga.pin}<br>
Série: ${recarga.serie}<br>
Lote: ${recarga.lote }<br>
Data de Solicitação: <fmt:formatDate value="${recarga.dataDeSolicitacao}" type="both"   
pattern="dd/MM/yyyy HH:mm:ss" /> <br>
Data de Confirmação: <fmt:formatDate value="${recarga.dataDeconfirmacao}" type="both"   
pattern="dd/MM/yyyy HH:mm:ss" /> <br>
***<br>${recarga.mensagem}<br>***<br>


Código de Compra: ${recarga.codOnline}<br>




idTerminal: ${recarga. idTerminal}<br>
Recargas MA Jardim


				</h3>
			</div>
		</div>

	</div>
</div>

