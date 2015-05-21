
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="net.sf.jasperreports.engine.design.*" %> 
<%@ page import="net.sf.jasperreports.engine.data.*"%> 
<%@ page import="net.sf.jasperreports.engine.export.*"%> 
<%@ page import="net.sf.jasperreports.engine.util.*"%> 
<%@ page import="net.sf.jasperreports.view.*"%> 
<%@ page import="net.sf.jasperreports.view.save.*"%> 
<%@ page import="java.sql.*"%> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%@ page trimDirectiveWhitespaces="true" %>
<%

String subarea=request.getParameter("subarea");
String codpac=request.getParameter("codpac");
String fecha=request.getParameter("fecha");
String hora=request.getParameter("hora");
String aprobaccion=request.getParameter("aprobaccion");
String codge=request.getParameter("codge");
///System.out.println("aprobaccion aprobaccion aprobaccion "+aprobaccion);
//int edad1= Integer.parseInt(edad);

//System.out.println("edad..."+edad1);
/*Parametros para realizar la conexión*/ 
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!" ); 
/*Establecemos la ruta del reporte*/
File reportFile = new File(application.getRealPath("ExamenGrupo.jasper"));
/* No enviamos parámetros porque nuestro reporte no los necesita asi que escriba cualquier cadena de texto ya que solo seguiremos el formato del método runReportToPdf*/ 
Map parameters = new HashMap(); 
//Map parameters = new HashMap();
parameters.put("Subarea",subarea);
parameters.put("Paciente",codpac);
parameters.put("Fecha",fecha);
parameters.put("Hora",hora);
//parameters.put("Edad",edad1);
parameters.put("Genero",codge);

//parameters.put("josi","valor"); 
/*Enviamos la ruta del reporte, los parámetros y la conexión(objeto Connection)*/
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
/*Indicamos que la respuesta va a ser en formato PDF*/
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
/*Limpiamos y cerramos flujos de salida*/ 
ouputStream.flush();
ouputStream.close();
conexion.close();
%> 