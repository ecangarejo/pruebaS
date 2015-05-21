
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

/*Parametros para realizar la conexión*/ 
String Fechai=request.getParameter("Fechai");
String Fechaf=request.getParameter("Fechaf");
Connection conexion; Class.forName("com.mysql.jdbc.Driver");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","!d1c5ccya!"); 
/*Establecemos la ruta del reporte*/
File reportFile = new File(application.getRealPath("cont_report_fact.jasper"));
Map parameters = new HashMap(); 
parameters.put("Fechai",Fechai);
parameters.put("Fechaf",Fechaf);
/*byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (), parameters, conexion);
response.setContentType("application/pdf"); 
response.setContentLength(bytes.length); 
ServletOutputStream ouputStream = response.getOutputStream(); 
ouputStream.write(bytes, 0, bytes.length); 
ouputStream.flush();
ouputStream.close();*/

//Generar XLS.
//Preparacion del reporte (en esta etapa se inserta el valor del query en el reporte).

JasperPrint jasperPrint=JasperFillManager.fillReport(reportFile.getPath(), parameters, conexion);

//Nombre archivo resultado.
String htmFilesSource = "/reportes/zully.html";

//Creacion del html
JRHtmlExporter exporter = new JRHtmlExporter();
exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,application.getRealPath(htmFilesSource));
exporter.exportReport();

//Leer el archivo.
File f = new File (application.getRealPath(htmFilesSource));

//Obtener el Nombre del archivo.

String name = f.getName().substring(f.getName().lastIndexOf("/") + 1,f.getName().length());

//Configurar cabecera y nombre de archivo a desplegar en DialogBox.
response.setHeader("Content-Disposition", "attachment; filename=\" " + name + "\"");

InputStream in = new FileInputStream(f);
ServletOutputStream outs = response.getOutputStream();

int bit = 256;
int i = 0;

while ((bit) >= 0) {
bit = in.read();
outs.write(bit);
}

outs.flush();
outs.close();
in.close();
%> 