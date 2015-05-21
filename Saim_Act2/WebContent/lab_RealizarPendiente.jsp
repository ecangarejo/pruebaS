
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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Lista De Pacientes</title>
<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 200px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
</style>
<script language="javascript" src="lab_PacPendientes.js"></script>
<script language="javascript" src="Laboratorio1.js"></script>
<script language="javascript" src=".js"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">
function Pendientes(){
	var CodPac=<%=CodPac%>;
	LlenarLaboraPendientePaci(CodPac)
	//alert(CodPac);
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
///////////////////////////////////////////////////////////
function fecha_gru(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
	  document.getElementById('fechagru').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('horagru').value = temp;
	  id = setTimeout("hora()",1000)
	  }
</script>
</head>
<body onload="Pendientes(),fecha(),hora(),fecha_gru(),hora_gru();">
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="color: white" > --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Creaciones-Crear Examen</b></div>
</td>   
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="Copiamenu.jsp"%>
			</td>

			<td width="100%">                
					<br />
<form name="form1" id="form1"  style="margin-top:-16px;">
<input name="horagru" type="hidden" id="horagru" />
<input name="fechagru" type="hidden" id="fechagru" />
<input name="txtHora" type="hidden" id="txtHora" />
<input name="txtFecha" type="hidden" id="txtFecha" />
<input name="txtCodPac" type="hidden" id="txtCodPac" value="<%=CodPac%>"/>
<input name='txtUsuario' id='txtUsuario' type='hidden' value="<%=codigou %>"/>
<input name='txtusu' id='txtusu' type='hidden' value="<%=codigou %>"/>


<div id='formulario'></div>
<div id='examenes'></div>
</form>
</td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>