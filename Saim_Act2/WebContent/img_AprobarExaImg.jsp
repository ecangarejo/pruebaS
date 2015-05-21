
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<%
String codIce=request.getParameter("codIce");
String usuario=request.getParameter("usuario");
%>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Aprobar Estudio</title> 
<script language="javascript" >
function objetoAjax(){
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
  		}
	}

	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxFunction(a) {	
	ajax=objetoAjax();
	if(ajax) { 		 	  
		ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name
		ajax.onreadystatechange  = function (){
			if (ajax.readyState == 4) {
				var ab =ajax.responseText;
				var y=ab.split("_").length;
		     	var z=ab.split("_");
		     	document.getElementById('txtnombres').value=z[0];
		     	document.getElementById('txtfecha').value=z[1];
		     	document.getElementById('txtcedula').value=z[2];
		     	document.getElementById('txtsexo').value=z[3];
		     	document.getElementById('txtedad').value=z[4];
		     	document.getElementById('txtfechanacimiento').value=z[5];
		     	Resultado=z[6];
				datosClinicos=z[8]
				Diagnostico=z[9]	     	
		     /*	var e;
			    for(e=0;e<Resultado.length;e++){
			    	Resultado=Resultado.replace('@','Ñ');
			    }
			    var w;
			    for(w=0;w<datosClinicos.length;w++){
			    	datosClinicos=datosClinicos.replace('@','Ñ');
			    }
			    var v;
			    for(v=0;v<Diagnostico.length;v++){
			    	Diagnostico=Diagnostico.replace('@','Ñ');
			    }
			    */
			    document.getElementById('txtdescripcion').value=Resultado; 
			    document.getElementById('txtnomExamen').value=z[7];
			    document.getElementById('txtdatoClinico').value=datosClinicos;
			    document.getElementById('txtDiagnostico').value=Diagnostico;
	  	   	}
			}		
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=6&codIce="+a); //Posting txtname to Servlet
	}  
}
function mostrar(){
var a = <%=codIce%>
ajaxFunction(a);
}
////////////////////////////////////////////////////////////////////////////////
function verReporte (codIce,usuario) {
	pagina="img_ReporteImg.jsp?codIce="+codIce+"&usuario="+usuario;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}
/////////////////////////////////////////////////////////////////////
function actualizar(){
	var descripcion="";
	var diagnostico="";
	var a="";
	a = <%=codIce%>;
	var usuarios="";
	usuarios=<%=codigou%>;
	descripcion=encodeURIComponent(document.getElementById('txtdescripcion').value);
	diagnostico=encodeURIComponent(document.getElementById('txtDiagnostico').value);
	/*var e;
    for(e=0;e<descripcion.length;e++){
    	descripcion=descripcion.replace('Ñ','@');
    	descripcion=descripcion.replace('ñ','@');
    	descripcion=descripcion.replace('Á','A');
    	descripcion=descripcion.replace('É','E');
    	descripcion=descripcion.replace('Í','I');
    	descripcion=descripcion.replace('Ó','O');
    	descripcion=descripcion.replace('Ú','U');
    	descripcion=descripcion.replace('á','a');
    	descripcion=descripcion.replace('é','e');
    	descripcion=descripcion.replace('í','i');
    	descripcion=descripcion.replace('ó','o');
    	descripcion=descripcion.replace('ú','u');
    }
    var v;
    for(v=0;v<diagnostico.length;v++){
    	diagnostico=diagnostico.replace('Ñ','@');
    	diagnostico=diagnostico.replace('ñ','@');
    	diagnostico=diagnostico.replace('Á','A');
    	diagnostico=diagnostico.replace('É','E');
    	diagnostico=diagnostico.replace('Í','I');
    	diagnostico=diagnostico.replace('Ó','O');
    	diagnostico=diagnostico.replace('Ú','U');
    	diagnostico=diagnostico.replace('á','a');
    	diagnostico=diagnostico.replace('é','e');
    	diagnostico=diagnostico.replace('í','i');
    	diagnostico=diagnostico.replace('ó','o');
    	diagnostico=diagnostico.replace('ú','u');
    }*/
    ajax=objetoAjax();
	//if(xmlhttp) { 		 	  
		ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=7&codIce="+a+"&descripcion="+descripcion+"&usuario="+usuarios+"&Diagnostico="+diagnostico); //Posting txtname to Servlet
	//}
	alert("Datos Aprobados Satisfactoriamente!!!");
	//window.location.href="img_VerExamAprobarImg.jsp?actu="+1,true;
	verReporte(a,usuarios);
	//window.close();
}
////////////////////////////////////////////////////////////////////////////////
function texto(){
var descripcion="";
var diagnostico="";
var a="";
a = <%=codIce%>;
var usuarios="";
usuarios=<%=codigou%>;
descripcion=encodeURIComponent(document.getElementById('txtdescripcion').value);
diagnostico=encodeURIComponent(document.getElementById('txtDiagnostico').value);
/*var e;
for(e=0;e<descripcion.length;e++){
	descripcion=descripcion.replace('Ñ','@');
	descripcion=descripcion.replace('ñ','@');
	descripcion=descripcion.replace('Á','A');
	descripcion=descripcion.replace('É','E');
	descripcion=descripcion.replace('Í','I');
	descripcion=descripcion.replace('Ó','O');
	descripcion=descripcion.replace('Ú','U');
	descripcion=descripcion.replace('á','a');
	descripcion=descripcion.replace('é','e');
	descripcion=descripcion.replace('í','i');
	descripcion=descripcion.replace('ó','o');
	descripcion=descripcion.replace('ú','u');
}
var v;
for(v=0;v<diagnostico.length;v++){
	diagnostico=diagnostico.replace('Ñ','@');
	diagnostico=diagnostico.replace('ñ','@');
	diagnostico=diagnostico.replace('Á','A');
	diagnostico=diagnostico.replace('É','E');
	diagnostico=diagnostico.replace('Í','I');
	diagnostico=diagnostico.replace('Ó','O');
	diagnostico=diagnostico.replace('Ú','U');
	diagnostico=diagnostico.replace('á','a');
	diagnostico=diagnostico.replace('é','e');
	diagnostico=diagnostico.replace('í','i');
	diagnostico=diagnostico.replace('ó','o');
	diagnostico=diagnostico.replace('ú','u');
}*/
ajax=objetoAjax();
//if(xmlhttp) { 		 	  
	ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8&codIce="+a+"&descripcion="+descripcion+"&usuario="+usuarios+"&Diagnostico="+diagnostico); //Posting txtname to Servlet
//}
//alert("Datos Aprobados Satisfactoriamente!!!");
//window.location.href="img_VerExamAprobarImg.jsp?actu="+1,true;
	
}
////////////////////////////////////////////////////////////////////////////////
function alertuuu(){
	//alert()
	var usuario="";
	var codIce="";
	codIce=<%=codIce%>;
	usuario=<%=usuario%>;
	pagina="img_ReporteNoAprobado.jsp?codIce="+codIce+"&usuario="+usuario;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);

	
}
///////////////////////////////////////////////////////////////////////////////

</script>


</head>
<body onLoad="mostrar()" id="back">
<br>
<table width="100%">

<tr id="principal1">
<td >

<div style="backgroun-color:white">
<table width='100%' class="style6"  ><tr><td width='86' height="33" align="right">Nombre:</td><td width='301'><label>
  <input name="txtnombres" type="text" id="txtnombres" size="50" readonly="">
</label></td>
<td width='157' align="right">Fecha y Hora Procesado:</td><td width='201'><label>
  <input name="txtfecha" type="text" id="txtfecha" size="30" readonly="">
  </label></td></tr><tr><td align="right">Cedula:</td><td><label>
  <input name="txtcedula" type="text" id="txtcedula" size="30" readonly="">
  </label></td>
  <td align="right">Sexo:</td><td><label>
    <input name="txtsexo" type="text" id="txtsexo" readonly="">
  </label></td>
  </tr><tr><td align="right">Edad</td><td><label>
  <input name="txtedad" type="text" id="txtedad" readonly="">
  </label></td>
  <td align="right">Fecha Nacimiento: </td><td><label>
    <input name="txtfechanacimiento" type="text" id="txtfechanacimiento" readonly="">
  </label></td>
  </tr><tr><td colspan='4' align='center'>EXPLORACION</td></tr><tr>
    <td colspan='4' align='center'><label>
      <textarea name="txtnomExamen" cols="110" rows="2" id="txtnomExamen" readonly="readonly"></textarea>
    </label></td>
  </tr>  
  <tr>
    <td colspan='4' align='center'>DATOS CLINICOS </td>
  </tr>
  <tr>
    <td colspan='4' align='center'><textarea name="txtdatoClinico" cols="110" rows="4" id="txtdatoClinico" readonly="readonly"></textarea></td>
  </tr>
  <tr>
    <td colspan='4' align="center">HALLAZGOS</td>
  </tr>
  <tr>
    <td colspan='4' align="center"><textarea name='txtdescripcion' cols='110' rows='8' id='txtdescripcion' onblur='texto()'></textarea></td>
  </tr>
  <tr>
    <td colspan='4' align="center">CONCLUSION</td>
  </tr><tr><td colspan='4'></td></tr><tr><td colspan='4' align="center"><textarea name="txtDiagnostico" cols="110" rows="2" id="txtDiagnostico" onblur='texto()'></textarea></td></tr><tr><td colspan='3' align='center'><label>
  <input name='btnAprobar' type='button' class="boton4" id='btnAprobar' value='Aprobar' onClick='actualizar()' />
  </label></td>
    <td align='center'><input class="boton4" name='btnNoAprobado' type='button' id='btnNoAprobado' value='Ver Preliminar' onClick='alertuuu()' /></td>
  </tr></table></div>
</td></tr></table>
</div>
</body>
</html>