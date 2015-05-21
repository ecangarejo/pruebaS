
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Administracion de Parametros</title>
<script language="javascript" type="text/javascript" src="lab_AdministrarParametros.js"></script>
<script></script> 
</head>
<body id="back" onload="Fecha(),Hora()">

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
				rs2.getStatement().close();
				con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// DivisiÃ³n en trozos con la barra como delimitador. 
	partes = url.split('/'); 
	 
	var a=partes[partes.length-1];
	
	window.location.href="PermisosPagina?va=1&pa="+a+"&codusu="+<%=codigou%>+""; 
	
</script>
<%} %>
<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")||cod.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>

<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Administrar Parametros-Administrar Parametros</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>  
							<%@include file="Copiamenu.jsp"%>	
			</td>
    
			<td width="100%">       
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1" >
		    <table width="100%" class="style6" border="0">
              <tr >
                <td colspan="3" align="center" id="cabecera2" class="style11"><span>GENERAR REPORTES </span></td>
              </tr>
              
              <tr>
                <td width="190" align="left">Seleccione Una Opcion </td>
                <td width="365" align="left"><label>
                  <select name="cmbOpcionesParametros" id="cmbOpcionesParametros" onChange="CambiarParametros()">
                    <option value="S" selected="selected">Seleccione</option>
                    <option value="1">Actualizar Nombre del Area</option>
                    <option value="2">Actualizar Nombre de una Subarea</option>
                    <option value="3">Actualizar Nombre de un Examen</option>
                    <option value="4">Actualizar Unidad</option>
                    <option value="5">Eliminar Area</option>
                    <option value="6">Eliminar Subarea</option>
                    <option value="7">Eliminar Examen</option>
					<option value="8">Generar BackUp</option>
					<option value="9"> Sincronizar Diagnosticos Ingreso</option>
					<option value="10">Sincronizar Diagnosticos Egreso</option>
                  </select>
                </label></td>
                <td width="194"><label></label></td>
              </tr>
              <tr>
                <td colspan="3" align="left" id="cabecera2">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="3" align="left"><div id="Formulario" ></div></td>
				<input name='txtFecha' type='hidden' id='txtFecha' />
				<input name='txtHora' type='hidden' id='txtHora' />
				<input name='txtCodUsuario' type='hidden' id='txtCodUsuario' value=<%=codigou %> />
              </tr>
            </table>

<div id="tipos" ></div>
</form>
</td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>