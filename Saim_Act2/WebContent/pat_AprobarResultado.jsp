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
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
String TipoDocumento=request.getParameter("TipoDocumento");
if(TipoDocumento==null){
	TipoDocumento="";
}

String Identificacion=request.getParameter("Identificacion");
if(Identificacion==null){
	Identificacion="";
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Aprobar Patologia</title>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language=javascript src="pat_CrearPaciente.js"></script>
<script language=javascript src="pat_autocompletapaciente.js"></script>
<script language="javascript">
function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	 document.getElementById('txtfecha').value = temp1
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
	  document.getElementById('txthora').value = temp;
	  id = setTimeout("hora()",1000)
	  }

</script>
<style type="text/css">
<!--
#sugerencias {Cursor : pointer; width:218px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias {height : 100px ; overflow : scroll ; desbordamiento: auto;}
-->
</style>
</head>
<body onLoad="BuscarPacienteAprobar(),fecha(),hora()" id="back">
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Patologia.jsp">Patologia</a>-Creaciones-Asignar Estudio</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%">
<form id="form1" name="form1" >

  <label></label>
  <table width="100%" align="center" class="style6" >
    <tr >
      <td colspan="5" id="cabecera2" class="style11"><div align="center">APROBAR PATOLOGIA</div></td>
    </tr>
    <tr>
      <td width="48%">&nbsp;</td>
      <td width="49%"><input name="txtfecha" type="hidden" id="txtfecha" /></td>
      <td width="1%">&nbsp;</td>
      <td width="2%" ><input name="txthora" type="hidden" id="txthora" /><input name="txtCodigoUsuario" type="hidden" id="txtCodigoUsuario" value="<%=codigou %>" /></td>
    </tr>

    <tr>
      <td>&nbsp;</td>
      <td colspan="2"><div id='sugerencias'></div></td>
    </tr>
    <tr>
      <td colspan="4"><div id="resultado" style="display:none" ></div></td>
    </tr>
  </table>
</td></tr></table>
<div  align="center" id='formulario'></div> 
</div>
</td><input id='txtUsuario' value ="<%=codigou%>" type='hidden'></tr></table>
</form>
	
   
</body>
</html>