<%@ page contentType="text/html;  charset=UTF-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Devoluciones</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script src="farc_Movimientos.js" type="text/javascript"></script>
<script src="farc_autocompletarInventarioT.js" type="text/javascript"></script>

<style type="text/css">
#autoc  li:hover{Cursor : pointer; background-color: #cccccc; }
#autoc { border:1px solid black; margin-left:0.8px;}
#autoc ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#autoc ul li {padding: .2em; border-bottom: 1px solid silver;}


.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrollbox {height : 90px ; altura: 90px; desbordamiento: auto; overflow:scroll}

</style>
<script language="javascript">
function enter(tecla) {
	    if(tecla == 13){ 
		  BuscarP();	
	   } 
	}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  enter(tecla);
	  return (tecla != 13);
	}
</script>
</head>

<body onload="Devolucion()">
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
<table width="100%">
<tr align="right">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Farmacia.jsp">Servicio Farmaceutico </a>-Movimientos - Devoluciones </b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">  
<form id="form1" name="form1"  onkeypress = "return pulsar(event);">
<table width="100%" class="style6">
  <tr>
    <td><div id="contenido"></div></td>
    </tr>
<!--  <tr><td colspan='2'><div align='center'><label><input type='button' name='btnDev' id='btnDev' value='Generar Devolucion' ></label></div></td></tr>	
  -->		
</table>



<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>