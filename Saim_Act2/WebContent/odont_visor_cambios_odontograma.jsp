<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cambios Realizados</title>
</head>
<body>

<%

String [] dientes = request.getParameter("dientesamodificar").split(","); 
String [] arreglogeneralidadesobtenidas = request.getParameter("generalidadesamodificar").split(",");


%>

<div align="center">

<table border="1">
<tr>
<td colspan="2">
<div align="center">
Cambios Realizados
</div>
</td>



</tr>
<tr>
<td>
No. Diente
</td>
<td>
Generalidades Asignadas
</td>

</tr>

<% 
int i=0;
int j=0;




while (i<dientes.length){
	String [] generalidadespordiente = arreglogeneralidadesobtenidas[i].split("-");
j =0;
	while(j < generalidadespordiente.length){
	%><tr><% 
		if (j==0){
			%>
			
             <td rowspan='<%= generalidadespordiente.length %>'> <%= dientes[i]%></td>
			<%
		}
	String [] generalidadDesglosada = generalidadespordiente[j].split("_");
	String cadamostrar="";
	//System.out.print(""+generalidadDesglosada[0]);
	
	if(generalidadDesglosada[0].equals("normal")){
		cadamostrar = "normal";

	}
	
	else if(generalidadDesglosada[3].equals("caries")){
		cadamostrar = generalidadDesglosada[3] +" "+generalidadDesglosada[2];

	}
	else if(generalidadDesglosada[3].equals("radicina")){
		cadamostrar = "caries recidiva" +" "+generalidadDesglosada[2];

	}
	else if(generalidadDesglosada[3].equals("obturacion")){
		cadamostrar = generalidadDesglosada[3] +" "+generalidadDesglosada[2];

		
	}
	else{

		
		
		cadamostrar = generalidadDesglosada[3] ;
	}

	
	    %>
		<td>
		
		
		
		 <%=cadamostrar%></td>
		</tr>
	<%
		j++;
	}
	                                        
	
%>




	
<%	
	i++;
}


%>



</table>



</div>



</body>
</html>