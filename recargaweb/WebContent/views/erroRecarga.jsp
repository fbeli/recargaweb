<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="site">
<c:set var="entrar" value="true" />

<div id="titulo_erro">
<h1 id="erro"> erro!</h1>
</div>
<div id="erro">
	<h3 id="erro">
		Recarga telefone ( ${recarga.ddd} ) recarga.fone	<br>
		valor: <fmt:formatNumber value="${recarga.valor}" type="currency"/>
		<br>
		${recarga.statusRecarga}
		<br><br>
		
		Código: ${erro.codigo }<br>
		Mensagem: ${erro.mensagem }
	</h3>
</div>
</div>

