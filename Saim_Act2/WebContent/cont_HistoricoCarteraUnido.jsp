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
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"/> 
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico"/>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<%
String Nit =request.getParameter("Nit");
String Fecha =request.getParameter("Fecha");
if(Nit==null){Nit="314";}
if(Fecha==null){Fecha="2013-12-25";}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Historico Cartera</title>

</head>

<body>

<%
Conexion con=new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	   String idu=""; 
	   int DifPlazo=0;
	      	
	       	st2 = con.conn.createStatement();
	       	
	       	rs2=st2.executeQuery("SELECT * FROM (SELECT ae.ent_nit,ae.nombre_entidad,ae.ent_nit_contratante,cf.numero_factura,cf.fecha_factura,cf.precio_factura,SUM(cdf.cantidad) AS Deducido,(cf.precio_factura-(SUM(cdf.cantidad))) AS Restante,DATEDIFF('"+Fecha+"',cf.fecha_factura) dias,cf.estado FROM cont_factura cf,cont_detalle_factura cdf,cont_cuentas_empresas cce,adm_entidad ae WHERE cf.codigo=cdf.cod_fact_fk AND cce.codigo=cf.cod_cuenta_fk AND ae.ent_nit=cce.nit AND ae.ent_nit_contratante LIKE '%"+Nit+"%' AND cf.fecha_factura<='"+Fecha+"' AND cdf.fecha<='"+Fecha+"' GROUP BY cf.numero_factura ) AS a WHERE a.restante>0");
			%>
			<table width='100%' border='1'><tr><td>Entidad</td><td>Nit</td><td>Numero Factura</td><td>Fecha Factura</td><td>Valor Factura</td><td>Por Vencer</td><td>Vencido 1-30</td><td>Vencido 31-60</td><td>Vencido 61-90</td><td>Vencido 91-180</td><td>Vencido 180-360</td><td>Mas 360</td><td>Estado</td></tr>
	            
			<%while(rs2.next()){
				DifPlazo=rs2.getInt(9);
			
				%>	
			<tr><td><%=rs2.getString(2)%></td><td><%=rs2.getString(3)%></td><td><%=rs2.getString(4)%></td><td><%=rs2.getString(5) %></td><td><%=rs2.getString(6) %></td>
			<%
			if((DifPlazo<=30)){			
			%>
			<td><%=rs2.getString(8) %></td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<%}%>
			<%
			if(((DifPlazo>=31)&&(DifPlazo<=60))){			
			%>
			<td>0</td>
			<td><%=rs2.getString(8) %></td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<%}%>
			<%
			if(((DifPlazo>=61)&&(DifPlazo<=90))){			
			%>
			<td>0</td>
			<td>0</td>
			<td><%=rs2.getString(8) %></td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<%}%>
			<%
			if(((DifPlazo>=91)&&(DifPlazo<=120))){			
			%>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td><%=rs2.getString(8) %></td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<%}%>
			<%
			if(((DifPlazo>=121)&&(DifPlazo<=210))){			
			%>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td><%=rs2.getString(8) %></td>
			<td>0</td>
			<td>0</td>
			<%}%>
			<%
			if(((DifPlazo>=211)&&(DifPlazo<=390))){			
			%>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td><%=rs2.getString(8) %></td>
			<td>0</td>
			<%}%>
			<%
			if((DifPlazo>390)){			
			%>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td><%=rs2.getString(8) %></td>
			<%}
			if(rs2.getString(10).equals("0")){
				%>
				<td>Facturada</td>
			<%}
			if(rs2.getString(10).equals("2")){
				%>
				<td>Radicada</td>
			<%}%>
			</tr>
			<%}
			
			rs2.getStatement().close();
			con.cerrar();				
	       	%>



</body>
</html>
