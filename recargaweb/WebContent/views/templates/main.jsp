<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

	<title><tiles:getAsString name="titulo" /></title>
	<link href="recursos\estilos\recargahome.css" rel="stylesheet" type="text/css" />
	 <link rel="stylesheet"  type="text/css" href="recursos\estilos\abre_div.css" />
	
		<script src="recursos\script\jquery-1.9.1.js"></script>
</head>
<body>
	
	
	<tiles:insertAttribute name="cabecalho"/>
	

	<tiles:insertAttribute name="conteudo"/>


	<tiles:insertAttribute name="rodape"/>
</body>
</html>