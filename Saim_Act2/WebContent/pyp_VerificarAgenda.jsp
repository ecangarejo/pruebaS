<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
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
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Asignar Horario Medico</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script src="pyp_AsignarHorarioMedico.js" type="text/javascript"></script>
<script language="javascript">

</script>
</head>

<body>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="AgendaMedica.jsp">Agenda Medica </a>-Consultas-Verificar Agenda </b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%" border='1'> 

<form id="form1" name="form1">
 <tr id="cabecera2" class="style11">
		    <td colspan="4" align="center" ><div><span >VERIFICAR AGENDA</span></div> </td>
		    </tr>
<tr><td>Seleccione Medico<select id='cmbMedico' onchange='BorrarDiv()' ><option value='Seleccione'>Seleccione</option>
                   <% 
 ResultSet rs3=null;
 Statement st3 = null;  
 try{
 	Conexion con2=new Conexion();
 	st3 = con2.conn.createStatement();
 	rs3=st3.executeQuery("SELECT CONCAT(pm.nombre,' ',pm.apellidos) AS nombres,pm.codigo,pm.* FROM pyp_medico pm,seg_datos_personales sdp WHERE pm.numeroDocumento=sdp.numeroDocumento AND pm.nombre!='' ORDER BY pm.nombre");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(2)%>"><%=rs3.getString(1)%></option>
 	<%}
 	//st3.close();
 	rs3.getStatement().close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>




</select></td><td onclick='CargaAgendaMedico()' >Ver Agenda</td><td onclick='EliminarDS()'>Eliminar Dia de Semana</td><td></td></tr>

<tr><td colspan="4" ><div id="Formulario"></div></td></tr>
<tr><td colspan="4" ><div id="barra"></div></td></tr>

<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
<input name="txtFecha" type="hidden" id="txtFecha" />
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
