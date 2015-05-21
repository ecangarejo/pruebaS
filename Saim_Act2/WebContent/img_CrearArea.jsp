
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="pkgsys.*,java.util.*" %>
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
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Crear Grupo</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language='javascript'>
function Crear_Grupo(){	
	var area;
	area=document.getElementById('txtArea').value;
	if(area==""){
	   alert("Digite el Area...!");    
	}else{
		window.location.href="ControlCrearGrupo?valor=1&area="+area,true;
		alert("Ingreso Exitoso Del Area...");
		}
}

</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
</script>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Crear Grupo</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO IMAGENOLOGIA</div>
					<br/><br/>  
								<%@include file="copymenyhi.jsp"%> 
			</td>
    
			<td width="100%">       
					<br /> 
							<form  id="form1" name="form1" style="margin-top:-15px;">
							  <table width="100%" border="0" align="center">
							    <tr>
							      <td align="center" id="cabecera2" class="style11"> <div>CREAR GRUPO </div></td> 
							    </tr>
							  </table>
							 
							  <table width="100%" border="0" align="center"  class="style6">
							    <tr>
							      <td colspan="4" align="center"><span>NOMBRE DEL GRUPO
							       <input name="txtArea" type="text"  id="txtArea" onkeyup="this.value=this.value.toUpperCase(),caracter(form1.txtArea);" size="70" />
							       <span class="style8">*</span>
								   <input type="button" name="Button2" class="boton4" value="CREAR"  align="middle" onclick="Crear_Grupo()"/> 
								  </td> 
								  
							      </tr>
							    
								<tr>
							      <td colspan="3" align="center"><span class="style8"> Dato Requerido * </span></td>
							    </tr>
							  </table>
							 </form>
</td></tr></table>
</div>
</td></tr></table>
    <%} %>
</body>
</html>