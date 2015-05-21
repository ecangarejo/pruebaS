
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
<%
String CodEntidad=request.getParameter("CodEnt");
String FechaE=request.getParameter("FechaE");
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!" ); 
File reportFile = new File(application.getRealPath("cont_reportecarteraExcel.jasper"));

Map parameters = new HashMap(); 
parameters.put("CodEntidad",CodEntidad);
parameters.put("FechaE",FechaE);
parameters.put(JRParameter.REPORT_LOCALE,Locale.US);
/*byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();*/

////REPORTE EN EXCELLLLLLLLLLLLL////////////////////
JasperPrint print=JasperFillManager.fillReport(reportFile.getPath(), parameters, conexion);
//print = JasperFillManager.fillReport(reporte, parameters, conexion);
JRXlsExporter exportador = new JRXlsExporter();
exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
exportador.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"\\PruebaExcel.xls");
exportador.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, true);
exportador.setParameter(
JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
exportador.setParameter(
JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER, false);
exportador
.setParameter(
JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
true);
exportador
.setParameter(
JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
true);
exportador
.setParameter(
JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
true);
exportador.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
true);
try {
exportador.exportReport();
} catch (JRException e) {
e.printStackTrace();
}
FileInputStream entrada = new FileInputStream("\\PruebaExcel.xls");
byte[] lectura = new byte[entrada.available()];
entrada.read(lectura);
response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition","attachment; filename=PruebaExcel.xls");
response.setContentLength(lectura.length);
response.getOutputStream().write(lectura);
response.getOutputStream().flush();
response.getOutputStream().close();
entrada.close();
%> 