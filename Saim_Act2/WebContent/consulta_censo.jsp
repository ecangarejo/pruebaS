

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ page import="adm_bean.Censo,java.util.*" import="adm_logica.* "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<center><b><font face="Verdana"> CLINICA DE LA COSTA</font></b></center>
<font face="Arial, Helvetica, sans-serif">
<table border="1">
<tr>
<td width="10%"><font size="2">Cama  </font></td>
<td width="10%"><font size="2">Paciente</font></td>
<td width="10%"><font size="2">Diagnóstico</font></td>
<td width="10%"><font size="2">Sexo</font></td>
<td width="10%"><font size="2">S.Cotiz</font></td>
<td width="10%"><font size="2">Edad</font></td>
<td width="10%"><font size="2">Empresa o EPS</font></td>
<td width="10%"><font size="2">Médico tratante</font></td>
<td width="10%"><font size="2">Entrada</font></td>
<td width="10%"><font size="2">Días</font></td>
<td width="10%"><font size="2">Observación</font></td>
</tr>


        
          <c:forEach items="${listacenso}" var="current">
			<tr>
            <td><c:out value="${current.cama}" /></td>
			<td><c:out value="${current.paciente}" /></td>
			<td><c:out value="${current.diagnostico}" /></td>
			<td><c:out value="${current.sexo}" /></td>
			<td><c:out value="${current.edad}" /></td>
			<td><c:out value="${current.semanac}" /></td>
			<td><c:out value="${current.eps}" /></td>
			<td><c:out value="${current.medico}" /></td>
			<td><c:out value="${current.fechaentrada}" /></td>
			<td><c:out value="${current.dias}" /></td>
            <td><c:out value="${current.observacione}" /></td>
			</tr>
          </c:forEach>
      


</table></font>





<center><input name="sbt_imprimir" type="button" class="botones" onClick="print();" value="Imprimir"></center>

</body>
</html>