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
<%
String CodAdm=request.getParameter("CodAdm");
if(CodAdm==null){
	CodAdm="";
}

String CodPac=request.getParameter("CodPac");
if(CodPac==null){
	CodPac="";
}

%>
<%String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Lista De Pacientes</title>

<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 200px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
</style>

<style type="text/css">
#sugerencias1 li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias1 {width:545px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias1  {height : 100px ; altura: px;  overflow : scroll ; desbordamiento: auto;}
</style>
<script language="javascript" src="ord_PacLabo.js"></script>
<script languaje="javascript" src="img_autocompletarestudio.js"></script>
<script language="javascript" src=".js"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">
function CodPaciente(){

var CodPac=<%=CodPac%>;
AsignarOrdenLab(CodPac);
}

function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
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
<body onload="CodPaciente(),fecha(),hora();">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuhist.jsp">Historias Clinicas</a>-Crear Historia Clinica-Crear Formato</b></div>
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
<form name="form1" id="form1"  >
<div id='formulario'></div>
<input name='txtNomUsu' id='txtNomUsu' type='hidden' value="<%=idu%>">
<input name='txtCodPac' id='txtCodPac' type='hidden' value="<%=CodPac%>">
<input name='txtHora' id='txtHora' type='hidden' >
<input name='txtFecha' id='txtFecha' type='hidden'>

</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>