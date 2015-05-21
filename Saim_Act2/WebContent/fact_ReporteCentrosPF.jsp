<%//@ page contentType="text/html" pageEncoding="UTF-8"%> 
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
<%String centro=request.getParameter("centro");
String fi=request.getParameter("fi");
String ff=request.getParameter("ff");
String eps=request.getParameter("eps");
String ct=request.getParameter("ct");

System.out.println("centro"+centro);
System.out.println("ff"+ff);
System.out.println("fi"+fi);
System.out.println("eps"+eps);

//String bode=request.getParameter("bode");
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!"); 
File reportFile = new File(application.getRealPath("fact_AuditaCentrosFact.jasper"));
Map parameters = new HashMap(); 
parameters.put("centro",centro);
parameters.put("ff",ff);
parameters.put("fi",fi);
parameters.put("eps",eps);
parameters.put("ct",ct);

//parameters.put("bod",bode);
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();
conexion.close();
%> 


	