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

<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
String TipoEntrada=request.getParameter("TipoEntrada");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<script language="javascript">

</script>
</head>

<body>
<table width="100%">
<tr  id="principal1">  
<td>

<table width="100%" border='0'> 

 <tr id="cabecera2" class="style11">
		    <td colspan="4" align="center" bgcolor=""><h2><font color='black'>SELECCIONE EL ROL DE ACCESO</font></h2></td>
		    </tr>
			
			 <tr>
  <td width="33%" align="center" >&nbsp;</td>
  <td width="34%" align="center" >&nbsp;</td>
  <td width="33%" align="center" >&nbsp;</td>
  </tr>
  			 <tr>
  <td width="33%" align="center" >&nbsp;</td>
  <td width="34%" align="center" >&nbsp;</td>
  <td width="33%" align="center" >&nbsp;</td>
  </tr>
  
  <tr>
  <td width="33%" onclick='window.location.href="hic_BuscarPacientes.jsp";' align="center" bgcolor="#2E64FE">
    <H1><font color='white'>HOSPITALIZACIÓN</font></H1>
    </td>
  <td width="34%" onclick='window.location.href="agm_ListaMedico.jsp";' align="center" bgcolor="#BDBDBD">
    <H1><font color='white'>CONSULTA EXTERNA</font></H1>
    </td>
  <td width="33%" onclick='window.location.href="menu.jsp";' align="center" bgcolor="#2E64FE">
    <H1><font color='white'>MENU PRINCIPAL</font></H1>
    </td>
  </tr>
		 <tr>
  <td width="33%" align="center" >&nbsp;</td>
  <td width="34%" align="center" >&nbsp;</td>
  <td width="33%" align="center" >&nbsp;</td>
  </tr>
  		 <tr>
  <td width="33%" align="center" >&nbsp;</td>
  <td width="34%" align="center" >&nbsp;</td>
  <td width="33%" align="center" >&nbsp;</td>
  </tr>
<tr><td colspan="4" ><div align="center"><img src="Imagenes/SAIM2.jpg" /></div><input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>" /></td></tr></table>



</td></tr>
</table>

</body>
</html>
