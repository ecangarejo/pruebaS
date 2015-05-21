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
<%
String txtCodusuario=request.getParameter("txtCodusuario");
String venc=request.getParameter("venc");
String tipo=request.getParameter("tipo");
String r="";
//String ruta="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Saim";
String ruta = "C:\\\\Program Files\\\\Apache Software Foundation\\\\Tomcat 7.0\\\\webapps\\\\Saim\\";
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!" ); 

//File reportFile = new File(application.getRealPath("reportes\\fact_PacientesAgrupados.jasper"));
if(tipo.equals("1")){ r="reportes\\fact_PacientesAgrupados.jasper"; }

if(tipo.equals("2")){r="reportes\\fact_PacientesAgrupadosxEvento.jasper"; }

File reportFile = new File(application.getRealPath(r));


Map parameters = new HashMap(); 
parameters.put("enca",venc);
parameters.put("ruta",ruta);
parameters.put(JRParameter.REPORT_LOCALE,Locale.US);

byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();
conexion.close();
%> 