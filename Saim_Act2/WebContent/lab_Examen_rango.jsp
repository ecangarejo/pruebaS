<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String usu=request.getParameter("usu");
if(usu==null){
	usu="";
}
String area=request.getParameter("area");
if(area==null){
	area="";
}
String exa=request.getParameter("exa");
if(exa==null){
	exa="";
}
String valor=request.getParameter("valor");
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
String edad=request.getParameter("edad");
//System.out.println(edad+"edad...");
if(edad==null){
	edad="";
}
String gene=request.getParameter("gene");
//System.out.println(gene+"genero...");
if(gene==null){
	gene="";
}
%>
<script language=javascript src="1.js"></script>
<script language=javascript src="Validaciones.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Examen Indivual--(RANGO)</title>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<script language="javascript">


function fecha(){
	  var time1 = new Date()
	  var anos = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((anos < 10) ? "0" : "") + anos
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
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
function validarnumero(h,tecla, e)
{ 
    
	opc = false;
	var s=h.textarea.value;
	var n=0;
	var pos=0;
	for(i=0;i<s.length;i++){
	   valor = parseInt(s.charAt(i));
		if(isNaN(valor)){
			n++;
		}
	}
	if(n>0)
	{
		window.alert('El campo  solo acepta valores Numericos');
		h.textarea.value="";
		h.textarea.focus();
		return false;
	}
	
 
}   

</script>
</head>
<body onLoad="lab_unidad(),fecha(),hora();">

<form id="form1" name="form1" onkeypress = "return pulsar(event)" >
  <table width="100%" class="style6"> 
    <tr id="cabecera2">
      <td colspan="4" ><div align="center" class="style11"><strong>EXAMEN TIPO RANGO </strong></div></td>
    </tr>
    <tr  align="center"> 
      <td colspan="4" >NOMBRE EXAMEN: <%=exa%></td>
    </tr>           
    <tr align="center">            
      <td width="100%"><label>         
        <input name="txtced" type="hidden" id="txtced" value="<%=ced %>"  >    
      </label></td> 
      <td width="100%"><label>
        <input name="txttipo" type="hidden" id="txttipo" value="<%=docu %>" >
      </label></td>
      <td width="100%"> <input name="txtexa" type="hidden" id="txtexa" value="<%=exa %>" ></td>
      <td width="100%"> <input name="txtarea" type="hidden" id="txtarea" value="<%=area %>" ></td>
    </tr>
  </table>
<div align="center" class="style6" id="uni"></div>
  <table width="100%" >
  <tr>
    <td colspan="3"><div align="center" class="style6">RESULTADO
    </div>
      <label>
      <textarea name="textarea" cols="60"  ></textarea>
      </label>      <label></label>
      <div align="center"></div></td>
    </tr>
  <tr>
    <td colspan="3"><div align="center"><input type="button" class="boton4" name="guardar" value="Guardar" onClick="Guardar_Rango()" />
    </div></td>
    </tr>
  <tr>
    <td width="83">&nbsp;</td>
    <td width="83">&nbsp;</td>
    <td width="265">&nbsp;</td>
  </tr>
</table>
<input name="txtfecha" type="hidden" id="txtfecha"  >   
<input name="txthora" type="hidden" id="txthora"  >
<input name="txtvalor" type="hidden" id="txtvalor" value="<%=valor %>" >
<input name="txtedad" type="hidden" id="txtedad" value="<%=edad %>" >
<input name="txtgene" type="hidden" id="txtgene" value="<%=gene %>" >
<input name="txtusu" type="hidden" id="txtusu" value="<%=usu %>" >
<input name="txtCedPac" type="hidden" id="txtCedPac" value="<%=ced %>" >
</form>
</body>
</html>
