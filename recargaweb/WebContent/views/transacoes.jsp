<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <style>
table, td, th {
    border: 1px solid blue;
    border-collapse: collapse;
  line-height: 1.42857143;
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	  font-size: 15px;
	  text-align: center;
    
}

th {
    background-color: blue;
    color: white;
    text-align: center;
    padding-top: 5px;
  padding-bottom: 4px;
  width: 200px;

    
}
tr {
  display: table-row;
  vertical-align: inherit;
  border-color: inherit;
  line-height: 1.42857143;
    width: 100px;
}
#tpar{
  display: table-row;
    background-color: navy;
  vertical-align: inherit;
  border-color: inherit;
  line-height: 1.42857143;
}
</style>
 <table id="transacao">
 <thead>
  <tr>
 	<th  >Telefone</th> 
 <th  >Código Operação </th>
 <th>Valor</th>
  <th >Data De Solicitacao</th>
   <th >Data De Confirmacao</th>
   <th >statusRecarga</th>
   <th>statusRecargaServer</th>
   <th>Reemprimir Recibo</th>
   
 </tr>
 </thead>
<c:set var="i" value="1" />
<c:forEach items="${recargas}" var="recarga">
<c:choose>
<c:when test="${i/2==0}" >
<tr>

</c:when>
<c:otherwise>
<tr style="tpar">
</c:otherwise>
</c:choose>


 	<td>${recarga.ddd} ${recarga.fone}</td> 
 <td>${recarga.codOnline}</td>
 <td>${recarga.valor }</td>
  <td> <fmt:formatDate value="${recarga.dataDeSolicitacao}" type="both"   
pattern="dd/MM/yyyy HH:mm:ss" /></td>
   <td> <fmt:formatDate value="${recarga.dataDeconfirmacao}" type="both"   
pattern="dd/MM/yyyy HH:mm:ss" /></td>
   <td>${recarga.statusRecarga}</td>
   <td>${recarga.statusRecargaServer}</td>
   <td><a href="listarTransacaoPorId?id=${recarga.id}">Imprimir</a></td>
   
 </tr>
<c:set var="i" value="${i+1}" />

 </c:forEach>
 </table>