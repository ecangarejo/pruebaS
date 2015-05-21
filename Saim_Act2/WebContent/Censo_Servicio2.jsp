<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page import="java.sql.*"%> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<%@ page import = "javax.swing.JFileChooser"%>
<html>
<head>
<script  language="javascript"></script> 


</head>
<body>
<input type="button"  onclick="ventana()" value="Buscar" />
</body>
<%

String NomSubArea=request.getParameter("NomSubArea");
ResultSet rs3=null;
Statement st3 = null;  
try{
	String fAC="c:\\AC123.txt";
	PrintWriter outputAC= new PrintWriter(new FileWriter(fAC));
	Conexion con2=new Conexion();
	st3 = con2.conn.createStatement();
	rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");
	while(rs3.next()){
	 outputAC.println(rs3.getString(1)+","+rs3.getString(2));
	}
	outputAC.close();
	rs3.getStatement().close();
	
	
	
	
	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}

%> 
</html>