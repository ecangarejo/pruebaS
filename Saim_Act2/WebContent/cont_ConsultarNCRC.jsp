
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
<title>Consultar</title>

<script> 
function A(tecla,e){
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){BuscarProveedor();}
}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

</script> 
<script languaje="javascript" src="cont_CrearProveedor.js"></script>
</head>
<body id="back">

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
				con.cerrar();
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
<table width="100%"><tr><td id='FormTo'>
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
		  <form id="form1" style="margin-top:-15px;" name="form1" onkeypress = "return pulsar(event);" >
		  <table width="100%" > 
		<tr>

			<td width="100%">
<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>CONSULTAS </div></td></tr></table>
  <table width='100%' border='0'>
  <tr>
<td><label><input name="radiobutton" type="radio" value="6" id="crca"  onclick='Radio()'  >Consultar Recibo de Caja</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cntc"  onclick='Radio()'  >Consultar Notas Credito</label></td>

<!-- <td><label><input name="radiobutton" type="radio" value="6" id="cped"  onclick='Radio()'  >Cartera por Emision Detallada</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cpec"  onclick='Radio()'  >Cartera por Emision Consolidada</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cprc"  onclick='Radio()'  >Cartera por Radicacion Detallada</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="cprc"  onclick='RadioGR()'  >Cartera  Consolidada</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="chc"  onclick='Radio()'  >Cartera Detallada</label></td>
<td><label><input name="radiobutton" type="radio" value="6" id="rg"  onclick='RadioRGE()'  >Reporte Gerencial</label></td>-->
</tr>

<tr>
<td colspan='5'><div id='Creaciones'></div></td></tr>
<td colspan='5'><div id='Actualizaciones'></div></td></tr>
  </tr>
</table>
</br>

<div id='Contenido'></div>
<input id="CodUsuario" type="hidden" value="<%=codigou %>" >		  
		</form></td></tr></table>
</div>
</td></tr></table></td></tr></table>
<%} %>
</body>
</html>