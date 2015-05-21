<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte de Consumo</title>
		
		<script language="javascript" type="text/javascript" src="farc_Consultas.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	//String fi= request.getParameter("fi");
	String tano=request.getParameter("Iano");
	String tmeses=request.getParameter("Imes");
	String tdia=request.getParameter("Idia");
	String ftanos=request.getParameter("Fano");
	String ftmeses=request.getParameter("Fmes");
	String ftdia=request.getParameter("Fdia");
	String producto=request.getParameter("producto");
	//String ftent=request.getParameter("ftent");
//	String Fechai=tano+"-"+tmeses+"-"+tdia;
	//String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
	//String pend=request.getParameter("pend");
	String bodega=request.getParameter("bodega");
	
%>
<script language="javascript" type="text/javascript" >
function mostrarepc(){
//alert();
	var tano=<%=tano%>;
	var tmeses=<%=tmeses%>;
	var tdia=<%=tdia%>;
	var ftanos=<%=ftanos%>;
	var ftmeses=<%=ftmeses%>;
	var ftdia=<%=ftdia%>;
	var bodega="<%=bodega%>";

		if(bodega=="todas"){
			//alert("VistarepDispPac");
		VistarepDispPac(tano,tmeses,tdia,ftanos,ftmeses,ftdia);
		}else{
		//	alert("segunda");
			VistarepDispPacBod(tano,tmeses,tdia,ftanos,ftmeses,ftdia,bodega);
		}
			
	
}

</script>

</head>

<body onload=mostrarepc()>

	
		
		
	
		
		<br><br>
		<table  align="center" width="100%">
		<tr><td  class=style66 ><div align="center">RELACION DE DISPENSACION A PACIENTES</div></td></tr><br>
		<tr><td>
		<div id="reporte" align="center"></div>
		</td></tr>
		</table>