
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<%String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Lista De Pacientes</title>

<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias ul {Cursor : pointer; list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 200px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
</style>
<script language="javascript" src="ord_PacLabo.js"></script>
<script language="javascript" src="ord_autocompletarPacientes.js"></script>
</head>
<body onload="Pacientes()">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="HistoriaClinica.jsp">Historias Clinicas</a>-Crear Historia Clinica-Crear Formato</b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/>
								<%@include file="menuhist.jsp"%>  
			</td>

			<td width="100%">    
					<br />
<form name="form1" id="form1" style="margin-top:-15px;"  >
<table width="100%" class="style6">
  <tr>
    <td colspan="5" id="cabecera2"><div align="center" class="style11">LISTA DE PACIENTES</div></td>
  </tr>
  <tr>
    <td width="19%">Nombre Del Paciente </td>
    <td colspan="4"><input name="txtNomPaciente" type="text" id="txtNomPaciente" size="80" onkeyup="autocompletaPacacientes()" /></td>
  </tr>
  <tr>
    <td colspan="5" id="cabecera2" height="2">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4"><div id="sugerencias"></div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="19%">&nbsp;</td>
    <td width="22%">&nbsp;</td>
    <td width="21%">&nbsp;</td>
    <td width="19%">&nbsp;</td>
  </tr>
</table>

</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>	

</body>
</html>