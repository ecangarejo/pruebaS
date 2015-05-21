<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Reporte</title>
		
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
	String Fechai=tanos+"-"+tmeses+"-"+tdia;
	String Fechaf=ftanos+"-"+ftmeses+"-"+ftdia; 
	//System.out.println(ftent);
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
	//alert("1");
	var tmeses=<%=tmeses%>;
	//alert("2");
	var tdia=<%=tdia%>;
	//alert("3");
	var ftanos=<%=ftanos%>;
	//alert("4");
	var ftmeses=<%=ftmeses%>;
	//alert("5");
	var ftdia=<%=ftdia%>;
	//alert("6");
	var ftent="<%=ftent%>";
	//alert("7");
	//alert("mostrar rep");
	//alert("entrando a mostrar rep "+ftent);
	vistaRepFactNC(tanos,tmeses,tdia,ftanos,ftmeses,ftdia,ftent);
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
		
		<br>
<table  class=style66 align="center" width="50%">
		<tr><td width="30%" align="left"><img src='Imagenes/logoccosta.jpg' /></td><td><div align="rigth">REPORTE DE FACTURAS CON NOTAS CREDITOS</div></td></tr><br></table>
		
	

		<div id="reporte" align="center">
		
		<%		
     
		%></div>

		<%
		}

		
	
%>