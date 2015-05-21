<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<!-- Los import -->
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Crear Clases de Servicio</title>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css" /> 
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico" />
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" src="fact_CrearClasesServicio.js" type="text/javascript"></script>
<%String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
<style type="text/css">
#ResultadosBusqueda{
	height: 336px;
	overflow: scroll;
	color: #215b8b;
}
</style>

<%
if(codigou.equals("")){%>
<script language="javascript">alert("Usted no tiene permiso para ver esta p\xE1gina...");window.location.href="Seg_login.jsp";</script>
<%}else{
%>
<%
	Conexion con = new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	String idu = "";
	st2 = con.conn.createStatement();
	rs2 = st2.executeQuery("SELECT usuario FROM seg_usuario WHERE usu_codigo="+codigou+";");%>
	<%while(rs2.next()){
		idu = rs2.getString(1);
	}
	rs2.getStatement().close();
	
	ResultSet rs4;
	Statement st4=null;
	st4=con.conn.createStatement();
	rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
	int madmin=0;
	if(rs4.next()){
		madmin=rs4.getInt(1);
	}
	rs4.getStatement().close();
	con.cerrar();				
%>
</head>

<body>

<table width="100%">
<tr >
	<td>
	<table width="100%">
			<tr>
			<td>
			<div > <a  href="mensajes.jsp?TB_iframe=true&height=520&width=700"  class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin %></b></font></a> <%if (madmin>0){ %><bgsound src="Imagenes/INNERMK.WAV" loop="2"><img src="Imagenes/sobr0001.gif" /><%}%></div>
			</td>
			<td align="right">
			<div align="right" id="usuario" align="right" class="style11">Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou %>" style="color: white">--Cerrar Session--</a></div>
			</td>
			
		</tr>
	</table>
	</td>
</tr>
<tr>
	<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Facturacion.jsp">Facturacion </a>-Creaciones-Crear Clases de Servicio</b></div>
	</td> 
</tr>
<tr id="principal1">  
	<td width="100%">  
	<form id="form1" name="form1" onsubmit="return false" accept-charset="utf-8">
	<table width="100%" class="style6" border="0">
 	<tr>
   		<td width="25%" rowspan="3" id="lateral4" class="style6">
		<div align="center" class="style11" id="cabecera2">MODULO DE FACTURACION</div>
   		<%@include file="menufact.jsp"%>
		</td>
   	    <td width="75%" colspan="2"><div align="center" class="style11" id="cabecera2">Seleccione Una Opcion </div></td>
	</tr>
 	<tr>
 		<td>
		<label><input name="radiobutton" type="radio" value="CrearRb" id="1" onclick="Radio()" />Crear Clase de Servicio</label>
		</td>
   		<td>
		<label><input name="radiobutton" type="radio" value="ActualizarRb" id="2" onclick="Radio()" />Actualizar Clase de Servicio</label>
		</td>
 	</tr>
 	<tr>
 		<td colspan="2" height="350" valign="top"><div id="Opcion" style="/*border: 1px solid blue;*/"></div></td>
 	</tr>
	</table>
	<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
	</form>
	</td>
</tr>
</table>
<%} %>
</body>
</html>
