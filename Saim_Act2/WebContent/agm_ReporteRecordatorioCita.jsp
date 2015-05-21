
<%@ page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="net.sf.jasperreports.engine.design.*" %> 
<%@ page import="net.sf.jasperreports.engine.data.*"%> 
<%@ page import="net.sf.jasperreports.engine.export.*"%> 
<%@ page import="net.sf.jasperreports.engine.util.*"%> 
<%@ page import="net.sf.jasperreports.engine.query.*" %>
<%@ page import="net.sf.jasperreports.view.*"%> 
<%@ page import="net.sf.jasperreports.view.save.*"%> 
<%@ page import="net.sf.jasperreports.engine.query.*" %>
<%@ page import="java.sql.*"%> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%@ page trimDirectiveWhitespaces="true" %>

<%
//public static final java.lang.String	QUERY_LANGUAGE_SQL	"sql"
String CodHoraMedico=request.getParameter("CodHoraMedico");

Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!" ); 
//conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/saim_hnj","root","123" );
File reportFile = new File(application.getRealPath("RecordatorioCita.jasper"));
Map parameters = new HashMap(); 
Locale locale = new Locale("es", "ES");
parameters.put("CodHoraMedico",CodHoraMedico);
parameters.put(JRParameter.REPORT_LOCALE,locale);

byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();
conexion.close();
%> 