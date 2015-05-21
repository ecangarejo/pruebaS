<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte de Comprobante de Diario General</title>
		
		<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<script language="javascript" type="text/javascript" src="cont_Reportes.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	
	String MC=request.getParameter("MC");
	String AC=request.getParameter("AC");
	String RDiaU=request.getParameter("rdia1");
	String RDiaD=request.getParameter("rdia2");
	String TDOC=request.getParameter("TDOC");
	System.out.println("Valor de TDOC "+TDOC);
	

	
%>
<script language="javascript" type="text/javascript" >
function mostrarep(){

	var MC='<%=MC%>';
	var RDiaU='<%=RDiaU%>';
	var RDiaD='<%=RDiaD%>';
	var TDOC='<%=TDOC%>';
	var AC=<%=AC%>;
	vistaRepCompDiario(MC,AC,TDOC,RDiaU,RDiaD);
}

</script>

</head>

<body onload=mostrarep()>
<%
	
		
	
		
		%>
		
		<br>

		<div id="reporte" align="center" width='100%'>
		
		<%		
     
		%></div>

</body>

		
	
