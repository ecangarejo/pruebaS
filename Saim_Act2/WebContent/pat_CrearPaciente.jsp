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
}%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Datos del Paciente</title>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language=javascript src="pat_CrearPaciente.js"></script>
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
</head>
<body onLoad="fecha(),hora()" id="back">
<%
String Identificacion=request.getParameter("Identificacion");
if(Identificacion==null){
	Identificacion="";
}
String TipoDocumento=request.getParameter("TipoDocumento");
if(TipoDocumento==null){
	TipoDocumento="";
}
String op=request.getParameter("op");
if(op==null){
	op="";
}
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Patologia.jsp">Patologia</a>-Creaciones-Crear Paciente</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%">       
					<br />
<form id="form3" name="form3" style="margin-top:-17px;" onkeypress = "return pulsar(event);">
<table width="100%" class="style6">
  <tr>
    <td colspan="4" id="cabecera2" align="center"> <span class="style11">DATOS DEMOGRAFICOS</span> </td>
  </tr>
  <tr>
    <td width="251">&nbsp;</td>
    <td width="337">&nbsp;</td>
    <td width="123">&nbsp;</td>
    <td width="491">&nbsp;</td>
  </tr>
  <tr>
    <td ><span class="Estilo8"> Tipo Documento</span></td>
    <td align="left"><label>
      <select name="tipodoc" size="1" id="tipodoc">
        <option value="<%=TipoDocumento%>" selected><%=TipoDocumento%></option>
		<option value="CC">CC</option>
        <option value="TI">TI</option>
        <option value="RC">RC</option>
        <option value="Nacido Vivo">Nacido Vivo</option>
      </select>
      <span class="style1"><span class="style8">*</span><br>
      <br>
      </span></label></td>
    <td><span class="Estilo8">N° Identificacion</span></td>
    <td><label>
 <input name="txtnumdocu" type="text" id="txtnumdocu" onKeyUp="validarte(form2.txtnumdocu);" onChange="datos();"  maxlength="12" value="<%=Identificacion %>"/>
    <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Apellidos</span></td>
    <td><label>
      <input name="txtapellidos" type="text" id="txtapellidos" onKeyUp="this.value=this.value.toUpperCase()" value="" size="50" />
      <span class="style8">*</span></label></td>
    <td><span class="Estilo8">Nombres</span></td>
    <td><label>
       <input name="txtnombres" type="text" id="txtnombres" onKeyUp="this.value=this.value.toUpperCase()" value="" size="50"   maxlength="20"/>
       <span ><span class="style8">*</span><br>
       </span></label></td>
  </tr>
<tr>
    <td><span class="Estilo8">Fecha Nacimiento(ddmmaaaa)</span></td>
    <td><label>
    <input type="text" name="txtfechanaci" id="txtfechanaci"   onKeyUp="masca(this,'/',patron,true)" maxlength="10"/>
    </label></td>
    <td><span class="Estilo8">Telefono</span></td>
    <td><label>
 <input name="txtele" type="text" id="txtele"  maxlength="11" value="" />
      <span class="style1"><span class="style8">*</span><br>
      </span></label></td>
  </tr>  
   <tr>
    <td><span class="Estilo8">Direccion</span></td>
    <td><label>
        <input  name="txtdireccion"  type="text" id="txtdireccion" size="50"/>
        <span class="style8">*</span></label></td>
    <td><span class="Estilo8">EPS</span></td>
    <td><label>
      <input name="txteps" type="text" id="txteps"  onKeyUp="this.value=this.value.toUpperCase()" value="" size="50" maxlength="50"/>
      <span class="style8">*</span><br>
    </label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Email</span></td>
    <td><label>
      <input name="txtemail" type="text" id="txtemail" size="50"  maxlength="100"/>
    </label></td>
    <td><span class="Estilo8">Genero</span></td>
    <td><p>
      <select name="cbgenero" id="cbgenero" >
        <option value="Seleccione" selected>SELECCIONE...</option>
        <option value="Masculino">Masculino</option>
        <option value="Femenino">Femenino</option>
      </select>
      <span class="style1"><span class="style8">*</span></span></p>
      <p>&nbsp;</p></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>    
    <td><input type="button" class="boton4" name="btingresar" id="btingresar" value="     Ingresar    " onClick="IngresarPacientePatologia();" /></td>
  </tr>
  <tr>
    <td colspan="2"><span class="style1"><span class="style8">Datos Obligatorios*</span></span></td>
    <td><input  name="op"  type="hidden" id="op" value="<%=op %>" /></td>
<input  name="CodUsu"  type="hidden" id="CodUsu" value="<%=codigou %>" />
<input  name="txtfecha"  type="hidden" id="txtfecha" />
<input  name="txthora"  type="hidden" id="txthora" />
  </tr>
</table>
	  <label></label>
</form></td></tr></table>
</div>
</td></tr></table>

	
   
</body>
</html>