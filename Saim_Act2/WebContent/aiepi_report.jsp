<%@ page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="net.sf.jasperreports.engine.design.*" %> 
<%@ page import="net.sf.jasperreports.engine.data.*"%> 
<%@ page import="net.sf.jasperreports.engine.export.*"%> 
<%@ page import="net.sf.jasperreports.engine.util.*"%> 
<%@ page import="net.sf.jasperreports.view.*"%> 
<%@ page import="net.sf.jasperreports.view.save.*"%> 
<%@ page import="java.sql.*"%> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> "C:/Users/Public/ControlOdontologia.java"
<%
String codPac=request.getParameter("codPac");
String fecha=request.getParameter("fecha");

String ruta="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Saim";
//String ruta="C:\\\\Program Files\\\\Apache Software Foundation\\\\Tomcat 7.0\\\\webapps\\\\Saim\\reportes";
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","123" ); 
File reportFile = new File(application.getRealPath("reportes\\aiepi_reporte.jasper"));
Map parameters = new HashMap(); 
parameters.put("codPac",codPac);
parameters.put("fecha",fecha);
parameters.put("ruta",ruta);

byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();
conexion.close();
%> 