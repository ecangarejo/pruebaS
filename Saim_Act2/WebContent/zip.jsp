<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*"%>
<%@ page import = "java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
String nom=request.getParameter("nom");
try{
	
	//String root = System.getProperty("user.home");
	//String root = "C:\\\\Program Files\\\\Apache Software Foundation\\\\Tomcat 7.0\\\\webapps\\\\Saim\\";
	String root="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\Saim"; 
	File f=new File(root + "\\"+nom+".zip"); 
	response.setContentType("application/zip"); 
	response.setHeader("Content-Disposition", "attachment; filename="+nom+".zip;"); 
	response.setHeader("Cache-Control", "no-cache"); 
	byte[] buf = new byte[response.getBufferSize()]; 
	response.setContentLength((int)f.length()); 
	System.out.println("file length : " + (int)f.length());
	System.out.println("here");
	int length; 
	FileInputStream fis = null;
	BufferedInputStream fileInBuf = null;

	fileInBuf = new BufferedInputStream(new FileInputStream (f));

	ByteArrayOutputStream baos = new ByteArrayOutputStream();

	while((length = fileInBuf.read(buf)) > 0) {
	baos.write(buf, 0, length);
	}

	response.getOutputStream().write(baos.toByteArray());
	response.getOutputStream().flush();
	response.getOutputStream().close();	
	}
	catch(Exception e)
	{
	System.out.println(e.getMessage());	
	} 
	%>
</head>
<body>

</body>
</html>