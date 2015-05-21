<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>No. de Dosis Adecuar Por Volumen</title>
		
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
	vrep(codop);
}

</script>

</head>

<body >
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
		<tr><td width="30%" align="left"><img src='Imagenes/logoccosta.jpg' /></td><td width='100%'><div align="rigth">No. de Dosis a Adecuar  Por Volumen  - Orden de Produccion No. AB<%=codigo%></div><br><br></td></tr><br></table>
	

		<div id="reporte" align="left">
		<br>
		<br>
		<table><tr><td class=contpre >Escoja el tipo de Jeringa o Bolsa: </td><td><select id='tipo' ><option value='seleccione'>Seleccione</option>
		<option value='1'>JERINGA 1 mL</option>
		<option value='2'>JERINGA 2 mL</option>
		<option value='3'>JERINGA 3 mL</option>
		<option value='4'>JERINGA 5 mL</option>
		<option value='5'>JERINGA 10 mL</option>
		<option value='6'>JERINGA 20 mL</option>
		<option value='7'>BOLSA DE 100</option>
		<option value='8'>BOLSA DE 250</option>
		<option value='9'>BOLSA DE 500</option>
		</select>
		</td>
		<td><input type='button' value='   consultar   ' onclick='mostrarep()'/></td>
		</tr>
		<tr><br><br></tr>
		<%		
     
		%></div><tr><td align='center' ><div id="reporte1" align="center"><br><br></td></tr></div>

		<%
		}

		
	
%>