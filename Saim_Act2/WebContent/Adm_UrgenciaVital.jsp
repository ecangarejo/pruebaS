
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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Urgencia Vital</title>
<script language="javascript">
function fecha_urgencia(){
	  var time1 = new Date()
	  var aÃ±o = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" 
	  temp1 += ((dia < 10) ? "0" : "") + dia
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((aÃ±o < 10) ? "-0" : "-") + aÃ±o
	  form1.txtFechaUrg.value = temp1
	 id = setTimeout("fecha_urgencia()",1000)
	  
	 
	  }

	  function hora_urgencia(){
		  var time = new Date()
		  var hour = time.getHours()
		  var minute = time.getMinutes()
		  var second = time.getSeconds()
		  var temp = "";
		  temp += ((hour <= 12) ? hour : hour-12);
		  temp += ((minute < 10) ? ":0" : ":") + minute;
		  temp += ((second < 10) ? ":0" : ":") + second;
		  form1.txtHoraUrg.value = temp
		  id = setTimeout("hora_urgencia()",1000)
		  }
function ingresar1(){

	var fecha =form1.txtFechaUrg.value;
	var hora =form1.txtHoraUrg.value;
	var estado =form1.txtestado.value;
	var descripcion =form1.txtdescripcion.value;
	if((fecha=="")||(hora=="")||(estado=="")||(descripcion=="")){
		alert("Falta Llenar La Descripcion!!")
		}
	else{
	window.location.href="ControlUrgenciaVital?fecha="+fecha+"&hora="+hora+"&estado="+estado+"&descripcion="+descripcion;
		alert("Ingreso Exitoso!!!");
	}
}


</script>
</head>
<body onLoad="fecha_urgencia(),hora_urgencia()" id="back">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Pre-Ingreso-Urgencia Vital</b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-16px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/>
								<%@include file="CopyAdm.jsp"%>  
			</td>

			<td width="100%">    
					<br />
<form id="form1" name="form1" action="" style="margin-top:-16px;">
  <table width="100%" class="style6" >
    <tr>
     <td  id="cabecera2" class="style11" colspan="3"   align="center" >URGENCIA VITAL </td> 
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
								      <td colspan="3">&nbsp;</td>
								    </tr>
								    <tr align="center">  
								      <td colspan="3" >FECHA
								      
								        <input name="txtFechaUrg" type="text" id="txtFechaUrg" readonly="true">
								      
								        <input name="txtestado" type="text" id="txtestado" value="ACTIVO" style="visibility:hidden">
								       
											HORA <input name="txtHoraUrg" type="text" id="txtHoraUrg" readonly="true">
									  </td>   
								    </tr>
								    <tr>
								      <td colspan="3" id="cabecera2" class="style11" align="center">DESCRIPCION</td>           
								    </tr>
								    <tr>
								      <td></td>
								      <td align="center"> <label>
								      <textarea name="txtdescripcion" id="txtdescripcion" cols="75" rows="4"></textarea>
								      </label><span class="style8">*</span></td>
								      <td>&nbsp;</td>
								    </tr>
								    <tr>
								      <td colspan="3">&nbsp;</td>
								    </tr>
    <tr>
      <td colspan="3" class="style8">Campos Requeridos * </td>
    </tr>
    <tr>
      <td colspan="3" align="center"><label>
        <input name="Ingresar" id="Ingresar" class="boton4" type="button" value="Ingresar" onClick="ingresar1()">
      </label>        <label></label></td> 
    </tr>
  </table>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>