
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
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Reportes de Balances y Auxiliares</title>

<style type="text/css">
#Opciones {height : 200px ;  overflow:auto;}
#linea {height : 300px ;  overflow-y:auto;}

#Cabecera {height : 215px ;  overflow:auto;}


#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}

#autoc  li:hover{Cursor : pointer; background-color: #cccccc; }
#autoc { border:1px solid black; margin-left:0.8px;}
#autoc ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#autoc ul li {padding: .2em; border-bottom: 1px solid silver;}

#dcta0  li:hover{Cursor : pointer; background-color: #cccccc; }
#dcta0 { border:1px solid black; margin-left:0.8px; display:none; height:336px; overflow : scroll ;}
#dcta0 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#dcta0 ul li {padding: .2em; border-bottom: 1px solid silver;}

#dter0  li:hover{Cursor : pointer; background-color: #cccccc; }
#dter0 { border:1px solid black; margin-left:0.8px; display:none; height:336px; overflow : scroll ;}
#dter0 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#dter0 ul li {padding: .2em; border-bottom: 1px solid silver;}


#sugerencias10  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias10 { border:1px solid black; margin-left:0.8px; display:none;  height:336px; overflow : scroll ;}
#sugerencias10 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias10 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias210{height:120px; background-color:#FFFFFF; width:600px; overflow-y:scroll; font-size:11px; display:none; position: absolute; border:1px solid #000000;}
#sugerencias3210{height:60px; background-color:#FFFFFF; width:250px; overflow-y:scroll; font-size:11px; display:none; position: absolute; border:1px solid #000000;}
#sugerencias432101{height:60px; background-color:#FFFFFF; width:250px; overflow-y:scroll; font-size:11px; display:none; position: absolute; border:1px solid #000000;}

.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.seleccionado2 {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrollbox {height : 90px ; altura: 90px; desbordamiento: auto; overflow:scroll}


.blanco{background-color: white;} 
.gris{background-color: #E6E6E6;} 
.azul{background-color: #08088A;} 

</style>
<script> 


</script> 
<script languaje="javascript" src="cont_Reportes.js"></script>
<script languaje="javascript" src="cont_AutoRangoCuentas.js"></script>
<script languaje="javascript" src="cont_autocompletarTerceros.js"></script>
</head>
<body id="back">

<%String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Contabilidad.jsp">Contabilidad</a>-Administracion-Consultas</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1"  >
		  <table width="100%" > 
		<tr>

			<td width="100%">
<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>REPORTES </div></td></tr></table>
  <table width='100%' border='0'>
  <tr class='style3'>
<td><label><input name="radiobutton" type="radio" value="6" id="cprbg"  onclick='Radio()'  >Reporte Balance General</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cprbp"  onclick='Radio()'  >Reportes Balances de Prueba </label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cpraux"  onclick='Radio()' >Reportes de Auxiliares</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cprpg"  onclick='Radio()'  >Reporte P y G </label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cprdia"  onclick='Radio()'  >Reporte Comprobante Diario </label></td></tr>
<tr>
<td colspan='7'><div id='Creaciones'></div></td></tr>
<td colspan='7'><div id='Actualizaciones'></div></td></tr>
 
</table>
</br>

<div id='Contenido'></div>
<input id="CodUsuario" type="hidden" value="<%=codigou %>" >		  
		</form></td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>