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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Consultar Diagnosticos</title>
<script language="javascript">
</script>


<script languaje="javascript" src="ConsultarProgramas.js"></script>
</head>

<body onload="">
<form id="form1" name="form1" >

<table width="100%" border="1">
  <tr>    
<td width="30%" >Nombre del diagnostico o codigo </td>
    <td width="60%" >
      <input name="txtNombreDiagnostico" type="text" id="txtNombreDiagnostico" size="100"  />   </td>
    <td width="10%" ><input name="btnBuscarDx" type="button" id="btnBuscarDx" value="Buscar" onclick="BuscarDx()" /></td>
  </tr>
</table>
<table width="100%" border="1">
    <tr>
      <td><div  id='resultado'>Resultado de la Busqueda</div></td>
    </tr>
  </table>
	

</form>


</body>
</html>
