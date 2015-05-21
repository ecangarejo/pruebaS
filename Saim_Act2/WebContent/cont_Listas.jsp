<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Listado</title>
		
		<script language="javascript" type="text/javascript" src="cont_grupos.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
	
		
		
		
<%
	String tipo=request.getParameter("tipo");
	String codigou = (String) session.getAttribute("codusuario");
	if (codigou == null){
		codigou = "";
	}
%>
<script language="javascript" type="text/javascript" >


</script>

</head>

<body onload='Listado(<%=tipo%>)'>
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
%>

		<table align='center' width='85%'>
		<%		
		%>
		
		<tr><td><div id='Listado'  ></div></td></tr>
		
		</table>
	<%
	}//fin del sino 	
%>