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
String CodInformeTrans=request.getParameter("CodInformeEcotrans");
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!"); 

File reportFile = new File(application.getRealPath("reportes\\eco_transesofagico.jasper"));

Map parameters = new HashMap(); 
parameters.put("CodInformeTrans",CodInformeTrans);
//parameters.put("SUBREPORT_DIR","C:\\Users\\Juan\\Documents\\Ireport Documents\\"); // al implementar cambiar direccion 

String ruta="C:\\Archivos de programa\\Apache Software Foundation\\Tomcat 6.0\\webapps\\Saim\\reportes\\";
parameters.put("SUBREPORT_DIR",ruta); // al implementar cambiar direccion 

//parameters.put("ruta",ruta);
parameters.put(JRParameter.REPORT_LOCALE,Locale.US);


//parameters.put("ruta",ruta_subinformes_cateterismo);
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();
conexion.close();








%> 


