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
String RC1=request.getParameter("RC1");
String RC2=request.getParameter("RC2");
String AC=request.getParameter("AC");
String MC=request.getParameter("MC");
String nivel=request.getParameter("nivel");
String PreSumaSAD=request.getParameter("PreSumaSAD");
String PreSumaDebitoC1=request.getParameter("PreSumaDebitoC1");
String PreSumaCreditoC1=request.getParameter("PreSumaCreditoC1");
String PreSumaDebitoC2=request.getParameter("PreSumaDebitoC2");
String PreSumaCreditoC2=request.getParameter("PreSumaCreditoC2");
String PreSumaSAC=request.getParameter("PreSumaSAC");
String dif1=request.getParameter("dif1");
String dif2=request.getParameter("dif2");
String ident=request.getParameter("ident");
String Fecha=request.getParameter("Fecha");
String Hora=request.getParameter("Hora");
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","123" ); 
File reportFile = new File(application.getRealPath("cont_RepBG1.jasper"));
Map parameters = new HashMap(); 
parameters.put("RC1",RC1);
parameters.put("RC2",RC2);
parameters.put("AC",AC);
parameters.put("MC",MC);
parameters.put("nivel",nivel);
parameters.put("PreSumaSAD",PreSumaSAD);
parameters.put("PreSumaDebitoC1",PreSumaDebitoC1);
parameters.put("PreSumaCreditoC1",PreSumaCreditoC1);
parameters.put("PreSumaDebitoC2",PreSumaDebitoC2);
parameters.put("PreSumaCreditoC2",PreSumaCreditoC2);
parameters.put("PreSumaSAC",PreSumaSAC);
parameters.put("dif1",dif1);
parameters.put("dif2",dif2);
parameters.put("ident",ident);
parameters.put("Fecha",Fecha);
parameters.put("Hora",Hora);
byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();
conexion.close();
%> 