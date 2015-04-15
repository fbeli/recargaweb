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
 	<th  >Operadora</th> 
 <th  >valor </th>
 
 </tr>
 </thead>
<c:set var="i" value="1" />
<c:forEach items="${produtos}" var="produto">
<c:choose>
<c:when test="${i/2==0}" >
<tr>

</c:when>
<c:otherwise>
<tr style="tpar">
</c:otherwise>
</c:choose>


 	<td>${produto.operadora.nomeOperadora}</td> 
 <td><fmt:formatNumber value="${produto.valorMaximoProduto}" type="currency"/></td>
 
 </tr>
<c:set var="i" value="${i+1}" />

 </c:forEach>
 </table>