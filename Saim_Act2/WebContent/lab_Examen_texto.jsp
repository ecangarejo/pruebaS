
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language=javascript src="1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Examen Individual--(TEXTO)</title>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<%
String usu=request.getParameter("usu");
if(usu==null){
	usu="";
}

String exa=request.getParameter("exa");
if(exa==null){
	exa="";
}
String area=request.getParameter("area");
if(area==null){
	area="";
}
//System.out.println("exa"+exa);
String valor=request.getParameter("valor");
//System.out.println("valor"+valor);
if(valor==null){
	valor="";
}
String ced=request.getParameter("ced");
//System.out.println(ced);
if(ced==null){
	ced="";
}
String docu=request.getParameter("documento");
//System.out.println(docu);
if(docu==null){
	docu="";
}
%>
<script language="javascript">

function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtfecha').value = temp1
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
	  document.getElementById('txthora').value = temp
	  id = setTimeout("hora()",1000)
	  }

</script>
</head>
<body onLoad="fecha(),hora();">

<form id="form1" name="form1"  > 
  <table width="100%" align="center" class="style6">
    <tr id="cabecera2">
      <td colspan="10" ><div align="center" class="style11">Examen Tipo Texto </div></td>
    </tr>
    <tr>
      <td colspan="10">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="10"><label>Nombre Del Examen: <%=exa%></label></td>
    </tr>
    <tr>
      <td width="150">&nbsp;</td>
      <td width="150"><label>
      
      </label></td>
      <td colspan="3"><label></label>        <label>
      
      </label></td>
    </tr>
    <tr>
      <td colspan="3"><label></label></td>
      <td colspan="2"><label></label></td>
    </tr>
    <tr id="cabecera2">
      <td colspan="10"><div align="center" class="style11"><strong>Descripcion </strong></div></td>
    </tr>
    <tr>
      <td colspan="10" align="center"><label>
        <div align="center">
          <textarea name="textarea" cols="60" id="textarea"></textarea>
         </div>
      </label></td>
    </tr>
    <tr>
      <td colspan="10" align="center"><input type="button"  class="boton4" name="button" id="button" value="      Guardar      "  onClick="Guardar_Texto()" /></td>
    </tr>
    <tr>              
      <td>&nbsp;</td> 
      <td><label> </label></td>
      <td width="15"><input name="txtfecha" type="hidden" id="txtfecha"  ></td>
      <td width="68"><input name="txthora" type="hidden" id="txthora">&nbsp;</td>
  <td width="55"><input name="txtexa" type="hidden" id="txtexa" value="<%=exa %>">&nbsp;</td>
 <td width="81"><input name="txtced" type="hidden" id="txtced" value="<%=ced %>">&nbsp;</td>
 <td width="68"><input name="txttipo" type="hidden" id="txttipo" value="<%=docu %>">&nbsp;</td>
<td width="68"><input name="txtvalor" type="hidden" id="txtvalor" value="<%=valor %>" ></td>
<td width="68"><input name="txtusu" type="hidden" id="txtusu" value="<%=usu %>" ></td>
<td> <input name="txtCedPac" type="text" id="txtCedPac" value="<%=ced %>" ></td>
      <td width="77"><label></label></td>
    </tr>

  </table>
</form>
</body>
</html>