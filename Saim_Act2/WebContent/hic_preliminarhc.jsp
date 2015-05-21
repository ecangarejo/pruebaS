<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<%
String usuario=request.getParameter("usuario");
String CodFormato=request.getParameter("CodFormato");
String fecha=request.getParameter("fecha");
String hora=request.getParameter("hora");
String CodPac=request.getParameter("CodPac");
String CodAdmision=request.getParameter("CodAdmision");


String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Formato Preliminar</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script language="javascript"></script>
<script language="javascript" type="text/javascript"  charset="UTF-8"  src="hic_SeleccionarPacientes.js" ></script>
<script languaje="javascript" src="AutocompletarCIE10Egreso.js"></script>
<script languaje="javascript" src="AutocompletarCIE10Relacion1.js"></script>
<script languaje="javascript" src="AutocompletarCIE10Relacion2.js"></script>
<script languaje="javascript" src="AutocompletarCIE10.js"></script>
<script languaje="javascript" src="hic_autocompletarProcedimiento.js"></script>
<script languaje="javascript" src="img_autocompletarestudio.js"></script>
</head>

<body onload="CargarFormatoPreliminar();">
<table width="100%">

<tr>
<td>
	<div id="cabecera1" align="center"  class="style6"><%
		Conexion con=new Conexion();
		ResultSet rs2;
		Statement st2 = null;
		st2 = con.conn.createStatement();
		String NombreFormato=""; 
       	rs2=st2.executeQuery("SELECT nombre_formato FROM hic_formato WHERE codigo="+CodFormato+"");%>
			<%if(rs2.next()){
				
				NombreFormato=rs2.getString(1);
			}
			rs2.getStatement().close();
			con.cerrar();
	
	%>
<%=NombreFormato %></div></td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">  
<form id="form1" name="form1">
<table width="100%" class="style6">
 <tr>
<td colspan="4"><div id="FormatoPH"></div></td>

</tr>

</table>


<input name="CodAdm" type="hidden" id="CodAdm" value="<%=CodAdmision%>" />
<input name="CodFormato" type="hidden" id="CodFormato" value="<%=CodFormato%>" />
<input name="fecha" type="hidden" id="fecha" value="<%=fecha%>" />
<input name="hora" type="hidden" id="txtHora" value="<%=hora%>" />
<input name="fecha" type="hidden" id="txtFecha" value="<%=fecha%>" />
<input name="hora" type="hidden" id="hora" value="<%=hora%>" />
<input name="CodPac" type="hidden" id="CodPac" value="<%=CodPac%>" />
<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=usuario %>" />

</form></td></tr></table>
</div></td></tr></table>


</body>
</html>
