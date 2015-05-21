<%@page language="java" import ="java.sql.*" %>  
<%@ page import="java.io.PrintWriter" %> 
 <%  
 PrintWriter o=response.getWriter();
 String name=request.getParameter("count");  
 o.println("<table>");  
 Class.forName("com.mysql.jdbc.Driver").newInstance();  
 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/saim","root","123");  
 Statement stmt = con.createStatement();  
 ResultSet rs = stmt.executeQuery("Select * from adm_municipio where nombre LIKE '"+name+"%'");  
   while(rs.next()){
	   o.println("<tr><td><a href='#' onclick='Click("+rs.getString(1)+")'>"+rs.getString("nombre")+"</a></td></tr>"); 
} 
   o.println("<table>"); 
%>