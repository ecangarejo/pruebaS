

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String CodAdmCola=request.getParameter("CodAdmCola");
if(CodAdmCola==null){
	CodAdmCola="";
}

String CedPac=request.getParameter("CedPac");
if(CedPac==null){
	CedPac="";
}
String codigou =(String)session.getAttribute("codusuario");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Clasificacion de Diagnostico</title>
<style type='text/css'>
<!--
.style1 {color: #FFFFFF}
.style4 {color: #FF0000}
#SugeDiagnostico {width:570px; border:1px solid black; display:none;}
#SugeDiagnostico {height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
-->
</style>
<script src="AutocompletarCIE10.js" type="text/javascript"></script>
<script src="Adm_ListaMedico.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language='javascript'>
function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }

</script>
</head>
<body onload='fecha(),hora()'>
<form>
<table width='100%' border='1' class='style6'>
  <tr>
    <td colspan='2' id='cabecera2' class='style11'><div align='center'>Clasificacion de Diagnostico</div> </td>
  </tr>
  <tr>
    <td width="155">Diagnostico Principal </td>
    <td width="548"><label>
      <input name="txtNomDiagnos" type="text" id="txtNomDiagnos" size="90" onKeyUp="autocompletarCIE10()">
    </label></td>
  </tr>
  <tr>
    <td><label>
      <input name="txtCodDiagnostico" type="hidden" id="txtCodDiagnostico">
    </label></td>
    <td><div id='SugeDiagnostico'></div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><label>
      <input name="btnAsignarDiagnostico" class='boton4' type="button" id="btnAsignarDiagnostico" value="Asignar" onclick="DiagnosticoPaciente()">
    </label></td>
  </tr>
</table>
<input name='txtUsuario' type='hidden' id='txtUsuario' value='<%=codigou %>' />
<input name='CodAdmCola' type='hidden' id='CodAdmCola' value='<%=CodAdmCola %>' />
<input name='CedPac' type='hidden' id='CedPac' value='<%=CedPac %>' />
<input name='txtHora' type='hidden' id='txtHora'  />
<input name='txtFecha' type='hidden' id='txtFecha'  />
</form>
</body>
</html>