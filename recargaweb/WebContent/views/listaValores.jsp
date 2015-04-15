<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 <script>
  function testar(divlocal){
    

         $("div").each(function(i){ 
                        // Aplica a cor de fundo 
            $(this).css("background", "red"); 

        });


alert(divlocal);
$(this).css("background", "white"); 
       document.getElementById(divlocal).style.background = "blue";  
    
    } 
  
  function setValor(valor){
		
	
	 
	  $(':hidden#codProduto').val(valor);
		$("#ValorSelecionado").html("R$ "+valor+",00");
	  
	
	}
  
       </script>
       
       <style>
table, td, th {
    border: 1px solid blue;
}

th {
    background-color: blue;
    color: white;
}
</style>
       
<div id="site">
<c:set var="entrar" value="true" />
<form action="solicitarRecarga" id="formRecarga">

	<input type="hidden" name="ddd" value="${ddd }">
	<input type="hidden" name="operadora" value="${operadora }">
	<input type="hidden" name="fone" value="${fone }">
	<input type="hidden" name="valor" value="0">
	<input type="hidden" name="codProduto" id="codProduto">
	<c:set var="entrar" value="true" />
	<c:forEach items="${produtos}" var="produto">
		<c:choose>
			<c:when test="${entrar}">
				<c:set var="entrar" value="false" />
				<div id="esquerda">
					<div id="valoresquerdo">
						<h1 id="valor" onclick="setValor(${produto.valorMaximoProduto});"><fmt:formatNumber value="${produto.valorMaximoProduto}" type="currency"/></h1>
						
						<!-- input type="radio" checked="checked" name="codProduto"
							value="${produto.valorMaximoProduto}" / -->

					</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:set var="entrar" value="true" />
				<div id="direita">
					<div id="valordireito">
				
						<h1 id="valor" onclick="setValor(${produto.valorMaximoProduto});">
							<fmt:formatNumber value="${produto.valorMaximoProduto}" type="currency"/>
						</h1>
						<!-- input type="radio" name="codProduto"
							value="${produto.valorMaximoProduto }"/-->
					
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<div  class="divvalorSelecionado">
				<h1 id=ValorSelecionado></h1>
			</div>
</form>
<div id="submit_list">
	<button id="popup_window" data-popup-target="#example-popup">Recarregar</button>
</div>

</div>
<!-- Abertura de uma nova janela pelo JQuery -->

<div id="example-popup" class="popup">
	<div class="popup-body">
		<span class="popup-exit"></span>

		<div class="popup-content">
			<h2 id="valor">Recarga para:</h2><br>
			<h3 id="valor">Operadora: ${operadora }</h3>
			<h3 id="valor">DDD: ${ddd }</h3>
			<h3 id="valor">Telefone: ${fone }</h3>
			<h3 id="valorRecarga" >Valor Recarga: ${valorRecarga }</script>
			
			</h3>
			<br><br>
			<button id="confirmarRecarga" class=".btnsubmit" >
				Confirmar Recarga
			</button>
		</div>
	</div>
</div>
<div class="popup-overlay"></div>
<script type="text/javascript">



$("#popup_window").click( function(){
	//$("#valorRecarga").text("Valor Recarga: R$ "+
		//	$('hidden[name=codProduto]:checked', '#formRecarga').val()
	//	+0);});
	if($(':hidden#codProduto').val() ==""){
		$("#confirmarRecarga").html("verificar Valores");
	}else{
		$("#valorRecarga").text("Valor Recarga: R$ "+ $(':hidden#codProduto').val()	+",00");
		$("#confirmarRecarga").html("Confirmar Recarga");
	}
	
	});
	
	$("#confirmarRecarga").click( function()
        {         
          $("#formRecarga").submit();
        }
     );

</script>
<script type='text/javascript'>
	//<![CDATA[ 
	$(window).load(
			function() {
				jQuery(document).ready(
						function($) {

							$('[data-popup-target]').click(
									function() {
										$('html').addClass('overlay');
										var activePopup = $(this).attr(
												'data-popup-target');
										$(activePopup).addClass('visible');

									});

							$(document).keyup(
									function(e) {
										if (e.keyCode == 27
												&& $('html')
														.hasClass('overlay')) {
											clearPopup();
										}
									});

							$('.popup-exit').click(function() {
								clearPopup();

							});

							$('.popup-overlay').click(function() {
								clearPopup();
							});

							function clearPopup() {
								$('.popup.visible').addClass('transitioning')
										.removeClass('visible');
								$('html').removeClass('overlay');

								setTimeout(function() {
									$('.popup').removeClass('transitioning');
								}, 200);
							}

						});
			});//]]>
</script>
