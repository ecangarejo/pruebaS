<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte Anuladas </title>
		
		<script language="javascript" type="text/javascript" src="cont_	ReportesFact.js"></script>
			<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
		
		
<%
	String CodUsuario= request.getParameter("CodUsuario");
	String tanos=request.getParameter("tanos");
	String tmeses=request.getParameter("tmeses");
	String tdia=request.getParameter("tdia");
	String ftanos=request.getParameter("ftanos");
	String ftmeses=request.getParameter("ftmeses");
	String ftdia=request.getParameter("ftdia");
	String ttp=request.getParameter("ttp");
	String ftent=request.getParameter("ftent");
	System.out.println("VALOR DE ENTIDAD"+ftent);
	String codigou = (String) session.getAttribute("codusuario");
	if (codigou == null){
		codigou = "";
	}
%>
<script language="javascript" type="text/javascript" >
function mostrarep(){
	
   
	var tanos=<%=tanos%>;
	var tmeses=<%=tmeses%>;
	var tdia=<%=tdia%>;
	var ftanos=<%=ftanos%>;
	var ftmeses=<%=ftmeses%>;
	var ftdia=<%=ftdia%>;
	
	var ftent="<%=ftent%>";
	var ttp="<%=ttp%>";
	//alert("mostrar rep"+ftent);
	//alert("entrando "+tmeses+" tanos"+tanos+" tdia"+tdia+" ftanos"+ftanos+" ftmeses"+ftmeses+" ftdia"+ftdia);
	//alert("llegando a mostrar rep"+ttp);
	vistarepAnu(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ttp,ftent);
	
}

</script>

</head>

<body onload=mostrarep()>

		
		
		<div id="reporte" align="center">
		
		<%		
     
		%></div>

