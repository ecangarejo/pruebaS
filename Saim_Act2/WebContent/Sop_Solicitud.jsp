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
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Solicitudes</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script src="soporte_tecnico.js" type="text/javascript"></script>
<script language="javascript"></script>
</head>

<body onload="setInterval('ver()',500)">
<%
if(codigou.equals("")){%>

<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");window.location.href="Seg_login.jsp";</script>
<%}else{
%>
<%
Conexion con=new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	   String idu="";         
	      	
	       	st2 = con.conn.createStatement();
	       	
	       	rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
				<%while(rs2.next()){
					
		        idu=rs2.getString(1);
				}
				rs2.getStatement().close();
				con.cerrar();				
	       	%>



<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>

</td>
</tr>

<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="soportetecnico.jsp">Soporte Tecnico</a></b></div>
</td> 
</tr>
</table>





<form id="form12" name="form12">
<table width="100%" class="style6">
  <tr>
    <td colspan="2"><div align="center" class="style11" id="cabecera2">Lista de Solicitudes </div></td>
    </tr>
<td width="40%"><label>
<input type="hidden" value="<%= codigou%>" id="usu">

Seleccione Su Estado<select  id='EstadoA' class='EstadoA' onchange='GuardarEstadistica()' ><option value='Seleccione'>Seleccione</option>

<%
Conexion con2=new Conexion();
	ResultSet rs22;
	Statement st22 = null;
        
	      	
	       	st22 = con2.conn.createStatement();
	       	
	       	rs22=st22.executeQuery("SELECT * FROM sop_estado_tecnico");%>
				<%while(rs22.next()){
					
                   out.print("<option value="+rs22.getString(1)+">"+rs22.getString(2)+"</option>");
		        
				}
				rs22.getStatement().close();
				con2.cerrar();	
				out.print("</select>");
	       	%>



     
</table>
<div id="listSolicitudes"></div>

 

</form>
<div id="opcion" align="center"></div>
<%} %>
</body>
</html>
