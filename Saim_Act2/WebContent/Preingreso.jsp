<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="adm_logica.Conexion" import="java.util.*" %>
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
<title>Pre-Ingreso</title>


<script language=javascript src="1.js"></script>


</head> 
<body >

<form id="form1" name="form1" >
<table class="style6" width="100%" height="206" border="0">
  <tr>
    <td colspan="4" id="cabecera2" align="center"><span class="style11">SELECCIONE CAMA</span></td>
    </tr>
  <tr>
    <td width="18%" height="21">&nbsp;</td>
    <td colspan="3">&nbsp;</td>
    </tr>

   <tr>
    
    <td>Area <span class="style8">*</span></td>
    <td width="82%"><label>
	
      <select name="cbeps" size="1" id="cbeps" onChange="CargarCamas()"  >
      <option selected="selected">SELECCIONE...</option>
<% 
 	ResultSet rs3=null;
 	Statement st3 = null;  
	 //try{
 		Conexion con2=new Conexion();
 		st3 = con2.conn.createStatement();
 		rs3=st3.executeQuery("SELECT asu.codigo, CONCAT(aa.nombre,'-->',asu.nombre) AS Areas FROM adm_area aa,adm_subarea asu WHERE aa.codigo=asu.codigoarea");%>
 		<%while(rs3.next()){%>
		<option value="<%=rs3.getString(1)%>"><%=rs3.getString(2)%></option>
 		<%}
 		rs3.getStatement().close();
 		con2.cerrar();
	//}catch(SQLException e){	 
		// System.out.println(e);
	//	out.println("no se pudo realizar la conexion!<br/>"); 
	//}
%>
       </select>
		
    </label></td>
  </tr>

<tr>
<td>Cama <span class="style8">*</span></td>
    <td id='CargarCamas'><label>
	
      <select name="cam" size="1" id="cam""  >
     <option selected="selected">SELECCIONE...</option>
       </select>
		
    </label></td>
  </tr>
<tr>
    <td height="30">&nbsp;</td>
    <td><label>
      <input name="btguardar" type="button" class="boton4" id="btguardar" value="Aceptar" onClick="envio(form1)" />
     
    </label></td>
   </tr>
  <tr>
    <td height="22" colspan="4"><label><span class="style8">Campos Requeridos *</span> </label>      <label></label></td>
    </tr>
</table>
<div id="message"></div>
</form>

</body>
</html>