<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="java.sql.*" %> 
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%
	String codorden=request.getParameter("codorden");


	/*Parametros para realizar la conexión*/ 
	Connection conexion; 
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!"); 
	
	
	/*Establecemos la ruta del reporte*/ 
	File reportFile = new File(application.getRealPath("reportes\\Orden_Aprobada.jasper")); 
	String ruta= "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Saim\\reportes";
	/* No enviamos parámetros porque nuestro reporte no los necesita asi que escriba 
	cualquier cadena de texto ya que solo seguiremos el formato del método runReportToPdf*/ 
	Map parameters = new HashMap(); 
	parameters.put("NumOrden",codorden);
	parameters.put("ruta",ruta);
	
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
%>