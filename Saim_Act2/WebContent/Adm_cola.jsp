
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="20" />
<link rel="STYLESHEET" type="text/css" href="estilo1.css">
<title>Insert title here</title>
<%String codigou =(String)session.getAttribute("codusuario");%>
</head>
<body onload="ControlCola" >

<div id="contenedor">

	<div id="cabecera">

		<img align="left" src="Imagenes/logopp.gif" height="110" border="0" width="935"/>
	</div>
	<div id="cabecera2">
	</div>
	<div id="cuerpo">
		<div id="lateral">
			<ul>
				<li><br></li>
				<li><a href="menuce.jsp">Consulta Externa</a>
				<li><a href="menuhosp.jsp">Hospitalización</a>
				
			</ul>
		</div>
		<div id="principal">
<form  id="form1" name="form1" method="post"  action="ControlCola">

<table width="99%" border="0">
    <tr>
      <td bgcolor="#0099FF"> <div align="center">LISTA DE PACIENTES </div></td>
    </tr>
  </table>
 
  <table width="25%" border="0" align="center">
    <tr>
      <td width="35%">Hora Actual:</td>
      <td width="65%"><input name="txthora" type="text"  id="txthora" border=""/></td>
    </tr>
  </table>
   

  <p>&nbsp;</p>
</form>
</div>
	</div>
	<div id="pie">
	&copy; 2008 clinicadelacosta.com
	</div>

</div>

</body>
</html>