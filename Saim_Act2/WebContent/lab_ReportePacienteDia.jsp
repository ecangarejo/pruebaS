
<%@  page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!--  Los import -->
<%@  page session="true"%>

<%@  page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<script language="javascript" src="lab_PacPendientes.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<title>Reporte Diario de Pacientes</title>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript">
function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }
</script>


</head>
<body onload="fecha(),ReportesDia()" id="back">
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} */

</script> 
<%String codigou =(String)session.getAttribute("codusuario");
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
				rs2.close();
	       	%>

<%

if(codigou==null){
	codigou="";
}

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>
<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Reportes-Citas Diarias </b></div>
</td>
</tr>

<tr id="principal1">
<td>

<div >

	<table width="100%" >           
		<tr>
			<td width="100%" >         
					  <br/>   
<form id="form2" name="form2" style="margin-top:-16px;">
<div id="formulario"></div>
 <input name="txtFecha" type="hidden" id="txtFecha" />
</form></td></tr></table>
</div>
</td></tr></table>
<%}%>

</body>
</html>