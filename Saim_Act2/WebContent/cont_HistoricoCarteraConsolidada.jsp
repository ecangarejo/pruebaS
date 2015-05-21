<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<%@ page import="fact_bean.Tarifas,java.util.*" import="fact_controlador.* " import="fact_metodo.* "%>
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
<script type="text/javascript" src="cont_CrearProveedor.js"></script>
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico"/>
<%
String TipoReporte =request.getParameter("TipoReporte");
if(TipoReporte==null){TipoReporte="'Radicada'";}

String FechaE=request.getParameter("FechaE");
if(FechaE==null){FechaE="2013-07-02";}
%>
<script type="text/javascript" >
function CargarCartera(){
	var t = <%=TipoReporte%>
	var f = <%=FechaE%>
	GenerarCarteraConsolidadaz(t,f)
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Historico Cartera Consolidada</title>

</head>

<body onload='CargarCartera()'>
<table width='100%' border='1'><tr><td id='Datos'></td></tr></table>

</body>
</html>
