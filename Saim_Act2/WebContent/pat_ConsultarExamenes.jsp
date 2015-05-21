
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@page pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css" />
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico" />
<script src="pat_CrearPaciente.js" type="text/javascript"></script>
<script language="javascript" src="pat_AutocompletarNombrePaciente.js"></script>
<script language="javascript" src="pat_autocompletapaciente.js"></script>

<title>Ver Resultados</title>

<style type="text/css">
<!--
#sugerencias {Cursor : pointer; width:218px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias {height : 100px ; overflow : scroll ; desbordamiento: auto;}

#sugerencias1 {Cursor : pointer; width:500px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias1 {height : 100px ; overflow : scroll ; desbordamiento: auto;}
-->
</style>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
<script language="javascript">

function validar(h){
		if (h.txtnumdoc.value.length==0){
			window.alert('Debe Digitar la Cedula');
			h.nom.focus();
			return false;
		}
	h.submit();
	}
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onload="" id="back">
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Patologia.jsp">Patologia</a>-Ver Resultado-Ver Resultados por Examen</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">      
 
					 <form id="form1"  name="form1" onkeypress = "return pulsar(event);" >
					
					   <table width="100%" class="style6"  align="center">
					
					     <tr id="cabecera2">
					       <td colspan="4"><div align="center" class="style11">DATOS DEL PACIENTE </div></td>
					     </tr>
					
					     <tr>
					       <td>&nbsp;</td>
					       <td><label>
					       </label></td>
					       <td width="225">&nbsp;</td>
					       <td width="602"><label>
					       </label></td>
					     </tr>
					     <tr>
					       <td width="138"><label><input name="radiobutton" type="radio" value="6" id="6" onclick="Radio()"/>NOMBRE</label></td>
					       <td width="237"><label><input name="radiobutton" type="radio" value="7" id="7" onclick="Radio()"  />IDENTIFICACION</label></td>
					       <td colspan="2"><label><div id="Opcion"></div></label></td>
					       </tr>
						 <tr>
					    <td colspan="4"></td>
					    </tr>
					   </table>
					   <div id='Resultados'></div>

        </form></td></tr></table>
</div> 
</td></tr></table>
	 <%} %>
 
</body>
</html>
