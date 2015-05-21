<%@ page language="java" contentType="text/html; charset=utf-8"
	import="adm_logica.Conexion" import="java.sql.*"
	pageEncoding="ISO-8859-1"%><!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<link rel="stylesheet" href="estilos/jquery-ui.css" type="text/css" />
<title>Creacion De Usuarios</title>

<script language=javascript src="seg_PermisosFormatos.js"></script>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type="text/javascript" src="jquery.js"></script>
<script lang="javascript" type="text/javascript" src="thickbox.js"></script>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type='text/javascript' src='Browser.js'></script>
<script lang="javascript" type="text/javascript" src="jquery-1.8.3.js"></script>
<script lang="javascript" type="text/javascript" src="jquery-ui.js"></script>

</head>

<body>
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

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
				rs2.getStatement().close();
				con.cerrar();
	       	%>
<%
String cod=request.getParameter("codigo");

if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>
<table width="100%">
<tr align="right" class="style6">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white" > --Cerrar Session--</a></div>
</td>
</tr>

<tr>
<td>
	<div id="cabecera1" align="center" class="style6"><a href="menu.jsp">Menu Principal</a>-<a href="seguridad.jsp">Seguridad</a>-Actualizar Firma</div>
</td>
</tr>

<tr>
<td id="principal1">
<center>         
			<table width="100%" > 
				<tr>   
		
		
					<td width="100%">
<br />
		<form id="form1"  name="myform" action="ControlActualizarFirma" method="post" enctype="multipart/form-data" >
		<table width="100%" class="style6" align="center">
		  <tr id="cabecera2" class="style11">
		    <td colspan="2" align="center" ><span >BUSCAR USUARIO</span> </td>
		    </tr>
		  <tr>
		    <td width="355" ><div align="center">NUMERO DOCUMENTO</div></td>
		    <td width="727"><label>
		      <input name="txtNumeroDocumento" type="text" id="txtNumeroDocumento" size="30" maxlength="15" />
		      <input name="btnBuscarUsuario" type="button" id="btnBuscarUsuario" value="     Buscar     " onclick="Buscarusuario()"  />
		    </label></td>
		  </tr>
		  <tr>
		    <td colspan="2"><div id="SubirImagen"></div></td>
		    </tr>
		</table>
	      
		</form>
</td></tr></table>
</div>
</td></tr></table>
    <%} %>


</body>
</html>
