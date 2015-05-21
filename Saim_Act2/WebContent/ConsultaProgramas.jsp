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
<style type="text/css">
#resultado{height:200px; overflow : scroll ;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consultar Programas</title>
<script language="javascript">
</script>


<script languaje="javascript" src="ConsultarProgramas.js"></script>
</head>

<body onload="">
<form id="form1" name="form1" >

<table width="100%" border="1">
  <tr>
    <td >Manual Tarifario </td>
    <td ><select name="cmbManualTar" id="cmbManualTar">
      <option value="Seleccione" selected="selected">Seleccione</option>
<option value="1" >ISS</option>
<option value="2" >SOAT</option>
<option value="3" >OTROS</option>

    </select></td>
    
<td >Nombre Programa,Codigo CUPS o Referencia </td>
    <td >
      <input name="txtCodigoPrograma" type="text" id="txtCodigoPrograma"  />   </td>
    <td ><input name="btnBuscarProg" type="button" id="btnBuscarProg" value="Buscar" onclick="BuscarPrograma()" /></td>
  </tr>
</table>
<table width="100%" border="1">
 	<tr><td>CUPS</td><td>Nombre Programa</td><td>Referencia</td></tr>
    <tr>
      <td colspan='3'><div  id='resultado'>Resultado de la Busqueda</div></td>
    </tr>
	<tr><td colspan='3' id='EntiVal'>entidades con el programa</td></tr>
  </table>
	

</form>


</body>
</html>
