
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Crear Formato</title>

<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias { Cursor : pointer; border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
</style>
<script src="hic_autocompletarArea.js" type="text/javascript"></script>
<script src="hic_CrearFormato.js" type="text/javascript"></script>

</head>

<body onload="formulario()">
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

}*/ 
</script>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
if(codigou.equals("")){%>

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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="HistoriaClinica.jsp">Historias Clinicas</a>-Crear Formato Historia Clinica-Crear Formato</b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/>
								<%@include file="menuhist.jsp"%>  
			</td>

			<td width="100%">    
					<br />
<form  id="form1" name="form1" style="">
 <table width="100%" class="style6">
  <tr>
    <td colspan="2"><div align="center" class="style11" id="cabecera2">Seleccione Una Opcion </div></td>
    </tr>
  <tr>
    <td width="50%"><label>
      <input name="radiobutton" type="radio" value="radiobutton" id="Ingresar" onclick="Radio()" />
      Crear Formato</label></td>
    <td width="50%"><label>
      <input name="radiobutton" type="radio" value="radiobutton" id="Actualizar" onclick="Radio()" />
      Actualizar Formato</label></td>
    </tr>
  <tr>
    <td colspan="2"><div id="formulario"></div></td>
    </tr>
</table>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>	
</body>
</html>