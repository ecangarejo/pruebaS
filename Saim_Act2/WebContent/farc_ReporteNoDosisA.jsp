<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>No. de Dosis Adecuar</title>
		
		<script language="javascript" type="text/javascript" src="farc_Consultas.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	
	String codigo=request.getParameter("codop");
	String codigou = (String) session.getAttribute("codusuario");
	if (codigou == null){
		codigou = "";
	}
%>
<script language="javascript" type="text/javascript" >
function mostrarep(){
	//alert("entrando a mostrar rep");
	var codop=<%=codigo%>;
	vistarep(codop);
}

</script>

</head>

<body onload=mostrarep()>
<%
	if (codigou.equals("")) {
%>
		<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
			window.location.href="Seg_login.jsp";
		</script>
<%
	} else {
		String idu = "";
		Conexion con = new Conexion();
		Statement st2 = con.conn.createStatement();
		ResultSet rs2 = st2.executeQuery("select usuario from seg_usuario  where usu_codigo=" + codigou);
		while (rs2.next()) {
			idu = rs2.getString(1);
		}
		rs2.getStatement().close();
		
	
		//<a href="exportarfact.jsp?Fechai=<%=Fechai&Fechaf=<%=Fechaf" >Exportar a Excel</a>
		%>
		
	
		
		<br><br>
		<table  class=style66 align="center" width="80%">
		<tr><td width="30%" align="left"><img src='Imagenes/logoccosta.jpg' /></td><td width='100%'><div align="rigth">No. de Dosis a Adecuar - Orden de Produccion No. AB<%=codigo%></div><br><br></td></tr><br></table>
	

		<div id="reporte" align="center">
		<br>
		<br>
		<%		
     
		%></div>

		<%
		}

		
	
%>