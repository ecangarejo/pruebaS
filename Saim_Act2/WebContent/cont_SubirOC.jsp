
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language=javascript src="Validaciones.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script languaje="javascript" src="Adm_Ping.js"></script>
<title> Cargue a Contabilidad</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language="javascript" type="text/javascript" src="cont_SubirOC.js"></script>	
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:342px; cursor:pointer; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}

#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}

#daux0  li:hover{Cursor : pointer; background-color: #cccccc; }
#daux0 { border:1px solid black; margin-left:0.8px; display:none; height:336px; overflow : scroll ;}
#daux0 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#daux0 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias10  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias10 { border:1px solid black; margin-left:0.8px; display:none;  height:336px; overflow : scroll ;}
#sugerencias10 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias10 ul li {padding: .2em; border-bottom: 1px solid silver;}

.seleccionado {font-weight:auto; background-color: #E6E6E6;j=1 ; desbordamiento: auto;}
.seleccionado2 {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrollbox {height : 90px ; altura: 90px; desbordamiento: auto; overflow:scroll}


.blanco{background-color: white;} 
.gris{background-color: #E6E6E6;} 
.azul{background-color: #08088A;} 



</style>

<script type="text/javascript">
	
	</script>

</head>
<body onload="FormBusq();" id="back"> 
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
				
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Contabilidad.jsp">Contabilidad</a>-Parametrizacion de Grupos de Productos</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>
 
	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
				<br />
					<div style="margin-top:-16px;" id="cabecera2" class="style11" align="center">MODULO DE CONTABILIDAD</div>
						<br/><br/>
								<%@include file="CopyCb.jsp"%>     
			</td>

			<td width="100%">   
					
					<table width="100%" border="0" cellspacing="0" class="style6">
  						<tr>
   							 <td colspan="4" id="cabecera2" class="style11" ><div align="center">Cargue a Contabilidad</div></td>
   						 </tr>
 						<tr>
						<td width="90%" >
							<table width="100%" >
								
								<tr>
								<td colspan='4'>
								
								<br>
								<div id='vistacargue'></div>
								</td>
								</tr>
								<tr>
								<td>
								<div id='Lista'></div>
								</td>
								</tr>	
								<input type='hidden' value='<%=codigou %>' id='user' />		
						</table>
						
</div>
</td></tr></table>
<%}%>
</body>
</html>