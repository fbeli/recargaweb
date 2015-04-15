<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="recursos\estilos\adm.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página de administrador</title>
</head>
<body>
<table style="width : 90%; border-bottom-style: solid;border-width: thin;" class="tableAdmin">
<tr style="width: 20px;"> <td id="tdMsg"><div id="erro">${mensagem}</div> </td></tr>
<tr  style="height: 50px;">
	<td style="width: 33%;">
	<h3> Incluir usuário</h3>
		<table >
		<form id="formNovoUser" action=" <%= request.getContextPath() %>/admin/cadastrarUser">
			<tr><td>Nome:</td><td> <input type="text" name="nome" id="nome"/></td></tr>
			<tr><td>Login:</td><td> <input type="text" name="login" id="login"/></td></tr>
			<tr><td>Confirmar senha:</td><td> <input type="password" id="senha" name="senha" /></td></tr>
			<tr><td>Nome: </td><td><input type="password" id="confSenha" /></td></tr>
			<tr>
				<td>Role: </td>
				<td>
					<select name="role" form="formNovoUser">
						 <option value="ROLE_MEMBRO">1 - ROLE_MEMBRO</option>
						 <option value="ROLE_VENDEDOR">2 - ROLE_VENDEDOR</option>
						 <option value="ROLE_ADMIN">3 - ROLE_ADMIN</option>
					</select>
				</td>
			</tr>
			
			<tr><td> <button id="btLogin">Confirmar</button></td></tr>
		</form>
		</table>
	</td>
	<td style="width: 33%;">
	
	<h3> Verifica Transação Online</h3>
		<table>
		<form id="formVerificaTransacao" action=" <%= request.getContextPath() %>/admin/verificaTransacao">
			<tr><td>ddd:</td><td> <input type="text" name="ddd" id="vt_ddd" /></td></tr>
			<tr><td>Fone:</td><td> <input type="text" name="fone" id="vt_fone" /></td></tr>			
			<tr><td> <input type="submit" value="buscar" > </td></tr>
		</form>
		</table>
	
	</td>
	<td>
	<h3> Verifica Transação Online</h3>
		<table>
		<form id="formVerificaTransacao" action=" <%= request.getContextPath() %>/admin/listarTransacaoPorId">
			<tr><td>Id Transação:</td><td> <input type="text" name="id" id="vt_id" /></td></tr>
						
			<tr><td> <input type="submit" value="buscar" > </td></tr>
		</form>
		</table>	
	</td>
	
</tr>
<tr>
	<td style="height: 40px;"><br>
	<h3> Verifica Transação Pendente</h3>
		<br>
		<a href="<%= request.getContextPath() %>/admin/listarTransacaoPendente" > Lista transações pendentes </a> 
	</td>
	<td>
	<h3>Listar Produtos</h3><br>
	<a href="<%= request.getContextPath() %>/buscarProdutos"> Listar Produtos</a>
	</td>
	<td>
	</td>
</tr>

</table>

<script type="text/javascript">
$("#btLogin").click( function(){
	if($("#senha").val() == $("#confSenha").val()){
		$("#formNovoUser").submit();
		
	}else{
		$("#tdMsg").html("Senhas Não conferem, favor, colocar senhas iguais")
	}
		
}

</script>
</body>
</html>