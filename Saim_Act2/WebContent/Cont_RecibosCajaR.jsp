<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<%
//String RC =(String)request.getAttribute("FormularioRC");
String RC =(String)session.getAttribute("FormularioRC");

%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Recibos de Caja</title> 
<script language=javascript src='cont_Facturas.js'></script>

</head>
<body>
<form>
<table width="100%">
<tr>
<td><div id='Formularia'><%=RC %></div></td>

</tr></table>
</form>
</body>
</html>