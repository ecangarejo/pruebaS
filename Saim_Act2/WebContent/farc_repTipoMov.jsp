<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte de Movimientos</title>
		
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
	String mov=request.getParameter("mov");
	
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
	var mov="<%=mov%>";
	var bodega="<%=bodega%>";
	

		if(mov=="1"){
			//alert("1");
		VistarepTipoMovT(tano,tmeses,tdia,ftanos,ftmeses,ftdia,bodega);	
		}else{
			if((mov=="2")||(mov=="3")){
				//alert("2-3");
		//	alert("segunda");
			VistarepTipoEnt_Tras(tano,tmeses,tdia,ftanos,ftmeses,ftdia,bodega,mov);
			}else{
				if(mov=="4"){
					//alert("4");
					VistarepSal(tano,tmeses,tdia,ftanos,ftmeses,ftdia,bodega,mov);
				}
		}
			
	
}
}

</script>

</head>

<body onload=mostrarepc()>

	
		
		
	
		
		<br><br>
		<table   width="100%" align='center'>
		<tr><td class=style66 ><div align="center">RELACION DE MOVIMIENTOS </div></td></tr>
		<tr><td>
		<div id="reporte" align="center">
		
		</div>
		</td>
		</table>
