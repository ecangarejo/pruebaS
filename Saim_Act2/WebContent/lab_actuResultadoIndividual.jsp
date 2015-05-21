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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Actualizar Resultado Individual</title>

<style type="text/css">
#sugerencias { border:1px solid black; display:none; margin-left:0.8px;}
#sugerenciasRe{ border:1px solid black; display:none; margin-left:0.8px; cursor: pointer;  }
#sugerenciasRe ul{ margin: 0; padding: 0; font-size:.95em;}
#sugerenciasRe li:hover {background-color:#FF00FF}
#sugerenciasRe ul li{padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:auto; background-color: #cccccc; } 
.scrollbox {height : 90px ; overflow:scroll;}
</style>
<script language="javascript" src="lab_actuResultadoIndividual.js"></script>
<script language="javascript" src="lab_autoRealizarExamen.js"></script>

<script language="javascript">
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
</script>
</head>
<body onLoad="ajaxFunction();" id="back">
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
			//	con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
//System.out.println("va.."+va);
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// División en trozos con la barra como delimitador. 
	partes = url.split('/'); 
	 
	var a=partes[partes.length-1];
	
	window.location.href="PermisosPagina?va=1&pa="+a+"&codusu="+<%=codigou%>+""; 
	
</script>
<%} %>
<%
String cod=request.getParameter("codigo");
//System.out.println("cod"+cod);
//System.out.println("cod_usuario yosimar"+codigou);
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Actualizar Examen-Actualizar Examen Individual</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6" >
<br />
					<div id="cabecera2" class="style11" style="margin-top:-15px;" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="Copiamenu.jsp"%>
			</td>

			<td width="100%">  
<br/>               
		  <form id="form1" style="margin-top:-15px;" name="form1" onkeypress = "return pulsar(event);" class="margen1">  
 			<div id='grupo'></div>
			<div id='examBuscados'></div>
			<div id='examenes'></div>
          </form>
		</td>
</tr>
</table>
</div>   
</td></tr></table>
    <%} %> 
</body>
</html>