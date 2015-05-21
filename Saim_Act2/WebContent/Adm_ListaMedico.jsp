
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<%@ page import="adm_bean.Preingreso,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%String codigou =(String)session.getAttribute("codusuario");%>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"/>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Triage</title>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<style type='text/css'>
<!--

#FormatosPaciente {height : 120px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
#ImagenologiaPaciente {height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
#LaboratoriosPaciente {height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
#areas {height : 110px ; altura: 200px;  overflow : scroll ; desbordamiento: auto;}
#formulario {height : 130px ; altura: 200px;  overflow : scroll ; desbordamiento: auto;}
#formularioAnteriores {height : 130px ; altura: 200px;  overflow : scroll ; desbordamiento: auto;}
#sugerencias {width:570px; border:1px solid black; display:none; margin-left: 215px;}
#sugerencias {height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
-->
</style>

<script src="hic_autocompletarFormato.js" type="text/javascript"></script>
<script src="Adm_ListaMedico.js" type="text/javascript"></script>
<script language='javascript'>
function fecha(){
	  var time1 = new Date()
	  var aÃ±o = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((aÃ±o < 10) ? "0" : "") + aÃ±o
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }


</script>
</head>
<body onload="ListaEspera(),fecha(),hora()" id="back">
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 
} */
</script>
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
				con.conn.close();
	       	%>
<%if(codigou==null){
	codigou="";
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Consulta-Lista Medico</b></div>
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-16px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/> 
								<%@include file="CopyAdm.jsp"%>
			</td>
  
			<td width="100%">   
					<br />
<form id="form1" name="form1" style="margin-top:-16px;">  
<div id="document"></div>
<input name='txtFecha' type='hidden' id='txtFecha' />
<input name='txtHora' type='hidden' id='txtHora' />
<input name='txtUsuario' type='hidden' id='txtUsuario' value='<%=codigou %>' />
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>