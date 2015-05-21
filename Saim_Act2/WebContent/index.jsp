<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language='java' contentType="text/html; charset=utf-8" import='java.util.*,lab_bean.*' %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>SAIM Sistema de Apoyo de Informacion Medica</title> 
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css" />
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico" />
<script language="javascript">
function A(tecla,e){
	//alert("123456");
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){seguridad_login();}
}
function focus(){document.getElementById("txtusuario").focus();}
</script>
</head>
<body onload="focus()">
<br /><br /><br /><br /><br /><br /><br />
<script>
if (history.forward(1)){location.replace(history.forward(-1))}
</script>
<center>
<div id="encabezado" >
	<table width="100%" > <tr> <td>&nbsp;</td> </tr> </table>
	<table width="100%" > <tr align="right"> <td id="sombra1"><p ></p></td> </tr> </table>
</div>
<div id="contenido12" >
	<form name="login" target="_self" class="style12" id="login" >
		<table width="100%"  align="center" >
		
		<tr align="right">
				<td>Usuario:</td><td><div align="left" style="size:inherit"><input  name="txtusuario" type="text" id="txtusuario" onkeydown="A(this,event)" size="30" maxlength="50" /></div></td>
		</tr>
		<tr align="right">
				<td>Contraseña:</td><td><div align="left" style="size:auto"><input name="contrasena" type="password" id="contrasena" onkeydown="A(this,event)" size="32" maxlength="50" /></div></td>
		</tr>		
		<tr class="style11"><td colspan="2" align="center"><input type="button" class="boton4" onclick="javascript:seguridad_login();" value="Iniciar Sesión"/> </td></tr>
		</table>
	</form>
<div id="seguridad" class="style8"></div>
</div>
<div id="pie" class="style11" align="left" >
<br />
<table width="100%">
<tr align="left">
<td>
<b>
© 2011 Reservados todos los derechos
</b>
</td>
<td rowspan="2" id="sombra" width="50%" align="center"></td>
</tr>
<tr>
<td><a id="somos" href="javascript:tf();" style="color:white">Quienes Somos?</a></td>
</tr>
</table>
</div>
</center>
</body>
</html>