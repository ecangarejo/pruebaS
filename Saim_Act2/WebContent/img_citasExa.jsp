<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ut-8" />
<title>Asignar Estudios</title>



<%
String nombre=request.getParameter("nombre");
if(nombre==null){
	nombre="";
}
String cedula=request.getParameter("cedula");
if(cedula==null){
	cedula="";
}
String eps=request.getParameter("eps");
if(eps==null){
	eps="";
}
%>

<script language=javascript >

function getXMLObject()  //XML OBJECT
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
/////////////////////////////////////////////////////////////////////////////////
function codigoPaciente(){
	var a = <%=cedula%>
	ajaxFunction(a);
}
function ajaxFunction(a) {
	var cedula= a;
	  if(xmlhttp) { 		 	 
	    xmlhttp.open("POST","ControlCitasExamen?valor=1&cedula="+cedula,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarPeticion;
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }
	}
function ComprobarPeticion() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('option').innerHTML = xmlhttp.responseText;
	    }
	     else {
	        alert("Error during AJAX call. Please try again,ComprobarPeticion");
	     }
	   }
	}
////////////////////////////////////////////////////////////////////////////////////
function VerExamenes() {	
	  if(xmlhttp) { 
		  var codGrupo=document.getElementById('cmbGrupo').value;		 	 
	    xmlhttp.open("POST","ControlCitasExamen?valor=2&codGrupo="+codGrupo,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarVerExamenes;
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }
	}

function ComprobarVerExamenes() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {

			document.getElementById('examenes').innerHTML = xmlhttp.responseText;
	    }
	     else {
	        alert("Error during AJAX call. Please try again,ComprobarPeticion");
	     }
	   }
}	
///////////////////////////////////////////////////////////////////////////////////
 function checkAll() {
	 var i;
    var nodoCheck = document.getElementsByTagName("input");
    var varCheck = document.getElementById("all").checked;
    for (i=0; i<nodoCheck.length; i++){
        if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
            nodoCheck[i].checked = varCheck;
        }
    } 
}
///////////////////////////////////////////////////////////////////////////////////
function fecha_gru(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
	 document.getElementById('txtfecha').value = temp1
	 id = setTimeout("fecha_gru()",1000)
	  
	 
	  }
function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txthora').value = temp;
	  id = setTimeout("hora_gru()",1000)
	  }


function Asignar(){
    var c=document.getElementById('yo').value;
	var oscar=c;
 if(c!=1){
	for(var i=0; i<=c-1; i++){	
      if (form1.ca[i].checked) {
       	 d=form1.ca[i].value; 
       	 enviar(d);
     }     	  
      oscar--; 
     }
 }
 else{
	 if(form1.ca.checked){
	 d=form1.ca.value;
	 enviar(d);
	}
}
 }//fin de la funcion 
function enviar(d){
	  getXMLObject();
		  var xmlhttp = new getXMLObject();
	  if(xmlhttp) { 
		var codPaciente=document.getElementById("txtcedula").value;
	    xmlhttp.open("POST","ControlCitasExamen?valor=3&codigoExa_fk="+d+"&codigoPac_fk="+codPaciente+"&estado=-1",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = PERMISOS;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet	
	   	}	
	  //window.location.reload(); 
}
function PERMISOS() {		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {			     	
				var a = xmlhttp.responseText		     	
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
}

</script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
</head>
<%String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%> 
<%
Conexion con=new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	   String idu="";         
	      	
	       	st2 = con.conn.createStatement();
	       	
	       	rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
				<%while(rs2.next()){
					
		        idu=rs2.getString(1);
				}
				rs2.getStatement().close();
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				rs4.getStatement().close();
				con.cerrar();
	       	%>

 
<body onload="codigoPaciente(),fecha_gru(),hora_gru();">
<table width="100%">
<tr >
<td>
	<table width="100%">
			<tr>
			<td>
			<div > <a  href="mensajes.jsp?TB_iframe=true&height=520&width=700"  class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin %></b></font></a> <%if (madmin>0){ %><bgsound src="Imagenes/INNERMK.WAV" loop="2"><img src="Imagenes/sobr0001.gif" /><%}%></div>
			</td>
			<td align="right">
			<div align="right" id="usuario" align="right" class="style11">Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou %>" style="color: white">--Cerrar Session--</a></div>
			</td>
			
		</tr>
	</table>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Asignar Examen</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO IMAGENOLOGIA</div>
					<br/><br/>  
								<%@include file="copymenyhi.jsp"%>
			</td>
    
			<td width="100%">       
					<br />
<form name="form1" id="form1" style="margin-top:-17px;" > 
<table width="100%" class="style6">
  <tr>
    <td colspan="4" id="cabecera2" align="center"><span class="style11">ASIGNAR EXAMENES </span></td>
  </tr>

  <tr>
	<td >CEDULA:<%=cedula%></td>
  </tr>

  <tr>
    <td >NOMBRE Y APELLIDOS:<%=nombre%></td>
  </tr>
  <tr>
    <td>EPS:<%=eps%></td>
  </tr>

  <tr>
    <td>SELECCIONE GRUPO </td>
  </tr>

  <tr>
	<td ><div id='option'></div></td>
  </tr>
  <tr>
    <td colspan="4" id="cabecera2" class="style11">&nbsp;</td> 
  </tr>
</table>
<div id='examenes'></div>
</form>
</td></tr></table>
</div>
</td></tr></table>
</body>
</html>
