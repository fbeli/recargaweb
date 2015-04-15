<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="generator"
	content="CoffeeCup HTML Editor (www.coffeecup.com)">
<meta name="dcterms.created" content="seg, 23 mar 2015 13:50:48 GMT">
<meta name="description" content="">
<meta name="keywords" content="">
<link href="recargahome.css" rel="stylesheet" type="text/css" />
<title></title>

<!--[if IE]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>

    <script>
        function selecionado(divlocal){
        
        	var vSrc = "recursos\\imagem\\ "+divlocal+".jpg";
        	document.getElementById("operadora").value = divlocal;
        	document.getElementById("imagemSelecionada").src = vSrc;
        	
        	
        
        }   
           </script>
	<div id="conteudo">
		<form action="buscarRecargasDisponiveis" method="post" id="formulario" >
			<div id="conteudo">
			<c:if test="${erro!=null }">
				<h5  style="color: red"> ${erro }</h5>
			</c:if>
			<input type="hidden" id="operadora" name="operadora" >

				<div id="esquerda">
					<div id="divoi" class="internoEsquerda">
					
						<img src="recursos\imagem\oi.jpg" width="100" height="100" onclick="selecionado('oi');"/> <br>
					

					</div>
					<div id="divtim" class="internoDireita">
						<img src="recursos\imagem\tim.jpg" width="134" height="100" onclick="selecionado('tim');"/> <br>
						
					</div>
				</div>
				<div id="direita" >
					<div id="divvivo" class="internoEsquerda">
						<img name="operadora" id="imgvivo" src="recursos\imagem\vivo.jpg" width="134"
							height="100" onclick="selecionado('vivo');"/> <br> 
					</div>
					<div id="divclaro" class="internoDireita">
						<img name="operadora" id="imgclaro" src="recursos\imagem\claro.jpg" width="100"
							height="100"  onclick="selecionado('claro');"/> <br> 
					</div>
				</div>
			</div>
			
			<div  class="divimagemSelecionada">
				<img id="imagemSelecionada">
			</div>
			<div id="telefone">
				<div id="ddd">
					<input type="text" label="DDD" size="1" maxlength="2" id="iddd" name="ddd"
						value="21" label="DDD" />
				</div>
				<div id="fone">
					<input type="text" label="Fone" maxlength="9" size="7" name="fone" id="ifone"
						value="987654321" />
				</div>
				<div id="erro" class="centro">
				
					<h3 id="erromsg"></h3>
					
				</div>
				<div id="divsubmit">
					<input id="btnsubmit" type="button" value="Escolha o Valor" />
				</div>
			</div>

		</form>


	</div>

	</form>
	</div>
	</div>
		
<script>
$("#btnsubmit").click(function(){
	
	var confirmaForm=true;
	var erromsg="";
	if($("#operadora").val() == ""){
		erromsg += "<br>Escolha a operadora";
		confirmaForm=false;
	}
	if($("#iddd").val() == ""){
		erromsg += "<br>Preencha o ddd";
		confirmaForm=false;
	}
	if($("#ifone").val() == ""){
		erromsg += "<br>Preencha o Telefone";
		confirmaForm=false;
	}
	if(confirmaForm == true){
		$("form#formulario").submit();
		document.getElementById("formulario").submit();
		
	}else{
		$("#erro").html(erromsg);
		$("#erro").show();
		$("#erro").css("visibility", "visible");
	}
	
});

</script>
</body>
</html>