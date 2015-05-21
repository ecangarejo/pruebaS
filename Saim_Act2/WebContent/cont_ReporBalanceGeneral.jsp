<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte de Balance de Prueba General</title>
		
		<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<script language="javascript" type="text/javascript" src="cont_Reportes.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	//String CodUsuario= request.getParameter("CodUsuario");
	String RC1=request.getParameter("RC1");
	String RC2=request.getParameter("RC2");
	String MC=request.getParameter("MC");
	String AC=request.getParameter("AC");
	String nivelSeleccionado=request.getParameter("nivelSeleccionado");
	System.out.println("Datos recibidos de ConsultarRepBalanceGeneral "+RC1+" "+RC2+" "+MC+" "+AC+" "+nivelSeleccionado);
	//String[] detnivelSeleccionado = nivelSeleccionado.split("_");
	//int j=0;
	//String nivel="";
	//for (int i = 0; i < detnivelSeleccionado.length; i++) {
	//	nivel
	//}
	
	
%>
<script language="javascript" type="text/javascript" >
function mostrarep(){
	
	var RC1='<%=RC1%>';
	
	var RC2='<%=RC2%>';
	//alert("1");
	var MC='<%=MC%>';
	//alert(MC);
	//alert("2");
	var AC=<%=AC%>;
	//alert("3");
	var nivelSeleccionado='<%=nivelSeleccionado%>';
	//alert("4");
	
	vistaRepBalanceGeneral(RC1, RC2, MC, AC, nivelSeleccionado);
}

</script>

</head>

<body onload=mostrarep()>
<%
	
		
	
		//<a href="exportarfact.jsp?Fechai=<%=Fechai&Fechaf=<%=Fechaf" >Exportar a Excel</a>
		%>
		
		<br>

		
	

		<div id="reporte" align="center" width='100%'>
		
		<%		
     
		%></div>



		
	
