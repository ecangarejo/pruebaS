<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte de Facturas Radicadas</title>
		
		<script language="javascript" type="text/javascript" src="cont_ReportesFact.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	String CodUsuario= request.getParameter("CodUsuario");
	String tanos=request.getParameter("tanos");
	String tmeses=request.getParameter("tmeses");
	String tdia=request.getParameter("tdia");
	String ftanos=request.getParameter("ftanos");
	String ftmeses=request.getParameter("ftmeses");
	String ftdia=request.getParameter("ftdia");
	String ftent=request.getParameter("ftent");
	String tipo=request.getParameter("tipo");
	String Fechai=tanos+"-"+tmeses+"-"+tdia;
	String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
	//String pend=request.getParameter("pend");
	String codigou = (String) session.getAttribute("codusuario");
	if (codigou == null){
		codigou = "";
	}
%>
<script language="javascript" type="text/javascript" >
function mostrarep(){
	var CodUsuario2=<%=CodUsuario%>;
	var tanos=<%=tanos%>;
	var tmeses=<%=tmeses%>;
	var tdia=<%=tdia%>;
	var ftanos=<%=ftanos%>;
	var ftmeses=<%=ftmeses%>;
	var ftdia=<%=ftdia%>;
	var tipo="<%=tipo%>";
	var ftent="<%=ftent%>";
	vistareprad(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ftent,tipo);
}

</script>

</head>

<body onload=mostrarep()>
<%

		//<a href="exportarfact.jsp?Fechai=<%=Fechai&Fechaf=<%=Fechaf" >Exportar a Excel</a>
		%>
		
	
		
		
		<div id="reporte" align="center">
		
		<%		
     
		%></div>

		<%
		

		
	
%>