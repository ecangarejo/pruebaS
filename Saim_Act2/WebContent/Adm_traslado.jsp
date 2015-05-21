


<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<%@ page import="adm_bean.CrearCama,java.util.*" import="adm_logica.* "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Traslado de Pacientes</title>

<script language=javascript src="translado.js"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>

</head>

<body onload="llenar(),fecha_ingreso(),hora()" id="back">
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} */

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
				con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// DivisiÃ³n en trozos con la barra como delimitador. 
	partes = url.split('/'); 
	 
	var a=partes[partes.length-1];
	
	window.location.href="PermisosPagina?va=1&pa="+a+"&codusu="+<%=codigou%>+""; 
	
</script>
<%} %>
<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")||cod.equals("")){%>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Traslado-Traslado de Paciente</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%">     
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br /><br/>
					<div id="cabecera2" class="style11" style="margin-top:-32px;" align="center">MODULO ADMISIONES</div>
					<br/><br/> 
								<%@include file="CopyAdm.jsp"%>
			</td>

			<td width="100%">   
					<br /><br/>
<form id="form1" name="form1" method="post"  style="margin-top:-32px;">
<table width="100%" class="style6">
  <tr>
    <td colspan="6" align="center" id="cabecera2"><span class="style11"><strong>TRASLADO DE PACIENTES </strong></span></td>
    </tr>
  <tr>
    <td colspan="6">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="6" id="cabecera2" align="center"><span class="style11"><strong>UBICACION ACTUAL </strong></span></td>
    </tr>
  <tr>
    <td width="72" height="25">AREA</td>
    <td width="169"><label>
      <select name="cmbarea" id="cmbarea" onchange="ajaxtraslado(form1);" >

     <option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
    <td width="100">SUBAREA</td>
    <td width="145"><label>
      <select name="cmbsubarea" id="cmbsubarea" onchange="ajaxtrasladocama(form1);">
	 <option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
    <td width="72">CAMA</td>
    <td width="176"><label><div id='cama'>
      <select name="cmbcama" id="cmbcama" >
      <option selected="selected">SELECCIONE...</option>
      </select>
   </div> </label></td>
  </tr>
</table>
<table width="100%"class="style6">
  
  <tr>
    <td height="19" colspan="6">&nbsp;</td>
    </tr>
  <tr>
    <td width="75">NOMBRE</td>
    <td width="168"><label>
      <input name="txtnombretr" type="text" id="txtnombretr"  readonly="" />
    </label></td>
    <td width="100">APELLIDO</td>
    <td width="146"><label>
      <input name="txtapellidotr" type="text" id="txtapellidotr" readonly="" />
    </label></td>
    <td width="73">CEDULA</td>
    <td width="170"><label>
      <input name="txtcedulatr" type="text" id="txtcedulatr" readonly="" />

    </label></td>
      </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="txtcodigoCamNueva" id="txtcodigoCamNueva" type="hidden" /></td>
    <td>&nbsp;</td>
    <td><input name="txtcodigoCamVieja" id="txtcodigoCamVieja" type="hidden" /></td>
    <td><input name="txtcodigoAdmin" id="txtcodigoAdmin" type="hidden"  /></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" id="cabecera2" align="center"><span class="style11"><strong>TRASLADAR A</strong></span></td>
    </tr>
  <tr>
    <td>AREA</td>
    <td><label>
      <select name="cmbareatr" id="cmbareatr" onchange="ajaxtrasladoActualizar(form1);">
 <option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
    <td>SUBAREA</td>
    <td><label>
      <select name="cmbsubareatr" id="cmbsubareatr" onchange="ajaxtrasladocamaActualizar(form1);">
 <option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
    <td>CAMA</td>
    <td><label><div id='camatr'>
      <select name="cmbcamatr" id="cmbcamatr" >
 <option selected="selected">SELECCIONE...</option>
      </select>
    </div></label></td>
  </tr>
  <tr>
    <td colspan="6">&nbsp;</td>
    </tr>
  <tr>
    <td><label></label></td>
    <td></td>
    <td>&nbsp;</td>
    <td><input name="btntrasladar" class="boton4" type="button" id="btntrasladar" value="Trasladar" onclick="TrasladarPaciente();"/></td>
    <td><label></label></td>
    <td><input name="txtFecha" id="txtFecha" type="hidden" />
	<input name="txtHora" id="txtHora" type="hidden" />
	<input name="txtUsuario" id="txtUsuario" type="hidden" value="<%=codigou %>" />

</td>
  </tr>
</table>
<p>&nbsp;</p>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
