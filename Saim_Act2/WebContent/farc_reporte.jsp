<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=utf-8" import="adm_logica.Conexion" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<title>Farmacia</title>
<script language="javascript" type='text/javascript' src="farc_reporte.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<script language="javascript" type='text/javascript' src='Browser.js'></script>

</head>

<body>
<table width="75%" align="center">
	<tr>
		<td>
		<table width="100%">
			<tr>
				<td>
					<div><a href="mensajes.jsp?TB_iframe=true&height=520&width=700" class="thickbox"> Mensajes <font color=red size=medium><b></b></font></a>
						
					</div>
				</td>
				<td align="right">
					<div align="right" id="usuario" align="right" class="style11">
					
					</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
			<div id="cabecera1" align="center" class="linkmenu"><b>
				<a href="menu.jsp">Men&uacute; Principal</a>
			</div>
		</td>
	</tr>

	<tr>
		<td id="cabecera2" class="style11" align="center">Reporte Compra
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%">
				<tr>
				<td width="150">Seleccione el periodo a reportar:</td>
				</tr>			
				<tr>
				<td width="150">Periodo:</td>
				<td width="158">
					<select name="periodo" id="periodo" class="styletxt" style="width: 155px" >
					<option value="Seleccione" selected="selected">SELECCIONE</option>
					<option value="P1">Periodo 1</option>
					<option value="P2">Periodo 2</option>
					<option value="P3">Periodo 3</option>
					<option value="P4">Periodo 4</option>
				</select>
				</td>
				<td width="150">Año:</td>
				<td width="158">
					<select name="anio" id="anio" class="styletxt" style="width: 155px" >
					<option value="Seleccione" selected="selected">SELECCIONE</option>
		<% int f;
        for(f=2012;f<=2030;f++) {
            %>
				<option value="<%=Integer.toString(f) %>"><%=Integer.toString(f) %></option>
            <% } %>
				</select>
				</td>
			</tr>
			<tr>
				<td><input name="boton" type="button" id="sismed" value="Exportar" onClick="Exportar()"></td>
				</tr>
	<tr>
		<td colspan="10">
		<div id="SISMED"></div>
		</td>
	</tr>
		</table>
		</td>
	</tr>	
</table>

</body>
</html>