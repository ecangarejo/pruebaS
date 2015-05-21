
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
<title> Creacion de Productos</title>

<script language="javascript" type="text/javascript" src="Ord_Producto.js"></script>	

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

</style>

<script type="text/javascript">
	
	</script>

</head>
<body onLoad="" id="back"> 
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
				rs4.getStatement().close();
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="OrdenCompra.jsp">Orden de Compra </a>-Creacion de Grupos </b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>
 
	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
				<br />
					<div style="margin-top:-16px;" id="cabecera2" class="style11" align="center">MODULO DE ORDENES DE COMPRA</div>
						<br/>
								<%@include file="menuord.jsp"%>     
			</td>

			<td width="100%">   
						
					<table width="100%" border="0" cellspacing="0" class="style6">
  						<tr>
   							 <td colspan="4" id="cabecera2" class="style11" ><div align="center">CREACION DE PRODUCTO</div></td>
   						 </tr>
 						<tr>
						<td>
						<table width="100%">
							<tr align='justify'>
								<td width='10%'>
										COD REFERENCIA: 
								</td>
								<td width='60%'>
										<%
											out.println("<input type='text' id='codref' size='20' />");								
										%>
								</td>
							<tr>
								<td width='10%'>
										 NOMBRE :
								</td>
								<td width='60%'>
										<%
										out.println("<input type='text' id='namep' size='60'/>");
										%>
										
								</td>
							</tr>	
							<tr>
								<td width='10%'>
								TIPO DE PRODUCTO :
								</td>
								<td width='60%'>
								<%
								
								ResultSet rs5;
								Statement st5=null;
								st5=con.conn.createStatement();
								rs5=st5.executeQuery("SELECT * FROM ord_tipoproducto ");
								out.println("<select id='tipop' /><option value='---' >---</option>");
								while(rs5.next()){
									out.println("<option value="+rs5.getString(1)+" >"+rs5.getString(2)+"</option>");
								}
								out.println("</select>");
								rs5.getStatement().close();
								
								%>
								</td>
							</tr>
								<tr align='center'>							
	      						<td>
								<br>
								<input type=button value="Guardar" onclick='SaveP()' />
								<input type='hidden' value='<%=codigou%>' id='user' />
								</td>
							</tr>
											
						</table>
						
						
</div>
</td></tr></table>
<%}%>
</body>
</html>