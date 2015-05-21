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
<%@ page import="java.io.*" %> 
<%@ page trimDirectiveWhitespaces="true" %> 
<%
String CodTratamientoOdontologico=request.getParameter("CodTratamientoOdontologico");
String CodUltimaHistoria = request.getParameter("CodHistoriaOdontologica");

Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!"); 
File reportFile = new File(application.getRealPath("odont_reporte_total_por_tratamiento_odontologia.jasper"));
//String ruta_subinformes_Odontologia= "C:\\Documents and Settings\\Desarrollo 2\\Escritorio\\Reportes Odontologia";
String ruta_subinformes_Odontologia="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Saim";
Map parameters = new HashMap(); 
parameters.put("codTratamientoOdontologico",CodTratamientoOdontologico);
parameters.put("codUltimaHistoria",CodUltimaHistoria);
parameters.put("ruta",ruta_subinformes_Odontologia);
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();
conexion.close();
%> 