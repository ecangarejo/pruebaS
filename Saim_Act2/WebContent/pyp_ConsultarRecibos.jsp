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
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
String calendario=request.getParameter("calendario");
if(calendario==null){
	calendario="";
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consultar Recibos</title>
<script language="javascript">
function A(tecla,e){
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){}
}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

</script>
<style type="text/css">
#CantCit{height:170px; overflow : scroll ;}

#arriba{
position: absolute;
    left: 0%;
    top: 20%;
}
</style>

<script languaje="javascript" src="pyp_asignarCita.js"></script>
<script languaje="javascript" src="calendar.js"></script>
<script language="javascript"></script>
</head>

<body onload="">
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
				con.cerrar();				
	       	%>
<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="ConsultaExterna.jsp">Consulta Externa </a>-<a href="ConsultaExterna.jsp">Asignacion</a>-Asignar Cita </b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">  
<form id="form1" name="form1" onkeypress = "return pulsar(event);">

<table width="100%" border="0">
  <tr>
    <td width="0%">&nbsp;</td>
    <td width="36%">Seleccione Fecha
<select name='cmbdia' id='cmbdia'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmes' id='cmbmes'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanio' type='text' id='txtanio' size='4' maxlength='4'>
</td>
    <td width="41%">Tipo de Recibo <select name="select" id='cmbTipoRec'>
      <option value="Seleccione" selected="selected">Seleccione</option>
      <option value="1">Ingreso</option>
      <option value="2">Egreso</option>
    </select>
    </td>
	<td>
	<input name='btnBuscar' value='     Buscar     ' onclick='BuscarRecibos()' type='button' />
	</td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td><div align="center" class="style11" id="cabecera2"></div></td>
    </tr>
  <tr>
    <td><div id="BuscRec"></div></td>
    </tr>
</table>

<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
</form>


</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
