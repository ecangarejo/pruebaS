
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
<title> Creacion de  Programacion de Cirugia</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>	
<script language="javascript" type="text/javascript" src="cig_programaciones.js"></script>	

<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Cirugia.jsp">Cirugia</a>-Creaciones</b></div>
</td>
</tr>

<tr  id="principal1">
<td>
	<table width="100%"> 
		<tr>
			<td>   
					<br/>
				
					<table width="100%"  border="0" cellspacing="0" class="style6" style="margin-top:-16px;" >
  						<tr>
   							 <td colspan="4" id="cabecera2" class="style11" style="margin-top:-16px;"><div align="center">CREACION DE PROGRAMACION </div></td>
   						 </tr>
							<tr>
								<td>
									<br>
										FECHA DE PROGRAMACION : <input type="text" id="FP" size="10" onKeyup="masca(this,patron,true,0,0,0)" />
								</td>
								<td> <br> TIPO DE PROGRAMACION: </td>
								<td> <br><%
								out.println("<select id='tp'><option value='Seleccione'>Seleccione</option>");
								ResultSet rs3;
								Statement st3 = null;
								st3=con.conn.createStatement();
								rs3=st3.executeQuery("SELECT p.codigo, p.descripcion FROM cig_tipoprogramacion p, seg_programacion_autorizadas s where s.cod_programacionfk=p.codigo and s.cod_usuariofk='"+codigou+"' ");
								while(rs3.next()){
									out.println("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
								}
								out.println("</select>"); %>
								</td>
								<td>
									<br>
									<input type="button" value=" Crear Programacion "  id="yulita" onclick="VerFormulario()" />
								
								</td>
							</tr>
							<tr><td colspan="4" >
							<div id="Formulario" ></div>
							</td></tr>	
							<tr><td colspan="4" >
							<div id="Vista" ></div>
							</td></tr>	
							<input type="hidden" value='<%=codigou%>'  id="user" />
	
						</table>
				</td></tr></table>
</td></tr></table>

<%}%>
</body>
</html>