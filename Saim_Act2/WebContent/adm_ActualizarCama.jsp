
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Alta de Pacientes</title>
</script>
<style type="text/css">
.style1 {color: #FFFFFF}
.style2 {font-size: xx-small}
.style3 {font-size: x-small;}
body {
	background-image: url(Imagenes/ultima.jpg);
	background-repeat: repeat-y;
}
</style>
<script type="text/javascript">
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

function ajaxCmbPosicion(h) {	
	
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlActualizarCama?va=0&posic="+h.txtcmbposicion.value+"",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarPosicion;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }		
	}


function ComprobarPosicion() {	
	
	   if (xmlhttp.readyState == 4) {	
		if(xmlhttp.status == 200) {		
	     	var a=xmlhttp.responseText;
	     	//alert(a);
	     	var y=a.split("_").length;
	     	var z=a.split("_");		
	     	//alert(a)   ;  	
	     	form1.cmbposicion.length=y;		     	
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		     form1.cmbposicion.options[x+1].text=z[x];			   
		    }
		   
	     				    	           	
	     }
	     else {
	        alert("Error during AJAX call. Please try again COMPROBAR POSICION");
	       }			
	   }	   
	}


function ajaxCmbTipoHab(h) {		
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlActualizarCama?va=6&tipo="+h.txttipohab.value,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCmbTipoHab;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }		
	}

function ComprobarCmbTipoHab() {		
	   if (xmlhttp.readyState == 4) {	
		if(xmlhttp.status == 200) {		
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	     	form1.cmbtipo.length=y;		     	
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		     form1.cmbtipo.options[x+1].text=z[x];			   
		    }
	     	ajaxCmbTipoCama(form1);	
	     		    	           	
	     }
	     else {
	        alert("Error during AJAX call. Please try again ComprobarCmbTipoHab");
	       }			
	   }	   
	}


function ajaxCmbTipoCama(h) {		
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlActualizarCama?va=5&tipcam="+h.txttipocama.value,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCmbTipoCama;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }		
	}

function ComprobarCmbTipoCama() {		
	   if (xmlhttp.readyState == 4) {	
		if(xmlhttp.status == 200) {		
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");		     	
	     	form1.cmbtipocama.length=y;		     	
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		     form1.cmbtipocama.options[x+1].text=z[x];			     		   
		    }	     	
	     	ajaxCmbPosicion(form1);	
	     	
	     }
	     else {
	        alert("Error during AJAX call. Please try again ComprobarCmbTipoCama");
	       }			
	   }	   
	}

/////////////////////////////////////////////////////////////////////////////////////////////////
	function ajaxFunctionTraslado() {		
		  if(xmlhttp) { 	 
		    xmlhttp.open("POST","ControlActualizarCama?va=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticionTraslado;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }		
		}	
////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxtraslado(h) {
		  if(xmlhttp) { 
			  var x=h.cmbarea.selectedIndex;
			  x=h.cmbarea.options[x].text;
			xmlhttp.open("POST","ControlActualizarCama?x="+x+"&va=2",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarSubareaTraslado;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
///////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarSubareaTraslado() {
		
		   if (xmlhttp.readyState == 4) {
			   if(xmlhttp.status == 200) {
			   	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		       	form1.cmbsubarea.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbsubarea.options[x+1].text=z[x];
			    }
		     }
		     else {
		        alert("Error during AJAX call. Please try again ajaxtraslado");
		     }
		   }
		}
function ComprobarPeticionTraslado() {		
		   if (xmlhttp.readyState == 4) {	
			if(xmlhttp.status == 200) {		
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	form1.cmbarea.length=y;		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbarea.options[x+1].text=z[x];			   
			    }
		     		    	           	
		     }
		     else {
		        alert("Error during AJAX call. Please try again ajaxFunctionTraslado");
		       }			
		   }	   
		}
function ajaxtrasladocama(h) {
	  if(xmlhttp) { 
		  var xx=h.cmbarea.selectedIndex;
		  xx=h.cmbarea.options[xx].text;
		  var y=h.cmbsubarea.selectedIndex;
		  y=h.cmbsubarea.options[y].text;	
	    xmlhttp.open("POST","ControlActualizarCama?va=3&y="+y,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = Comprobarxtraslado;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }
	}
/////////////////////////////////////////////////////////////////////////////////////////////////
function Comprobarxtraslado() {	
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {	     	
	     	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");	     
	     	form1.cmbcama.length=y;
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		     form1.cmbcama.options[x+1].text=z[x];		   
		    }
	     }
	     else {
	        alert("Error during AJAX call. Please try again ajaxtrasladocama");
	     }
	   }	   
	}
///////////////////////////////////////verificar cama//////////////////////////////////////////////

function VerificarCama(h){
	if(xmlhttp) { 
		var s=h.cmbcama.selectedIndex;
		var cama=h.cmbcama.options[s].text;
	    xmlhttp.open("POST","ControlActualizarCama?va=4&cama="+cama,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = CoVP;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xmlhttp.send(""); //Posting txtname to Servlet
		 }
	
}//fin de verificar cama

function CoVP() {
	   if (xmlhttp.readyState == 4) {
		   if(xmlhttp.status == 200) {			
		   	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");
	     	form1.txtposx.value=z[0];
	     	form1.txtposy.value=z[1];
	     	form1.txtObservaciones.value=z[2];
	     	form1.txtpiso.value=z[3];  
	    	form1.cmbtipo.options[0].text=z[4];
			form1.txttipohab.value=z[4];			
	     	form1.cmbposicion.options[0].text=z[5];
	     	form1.txtcmbposicion.value=z[5];
	     	form1.cmbtipocama.options[0].text=z[6];
	     	form1.txttipocama.value=z[6];
	     	form1.txtcodigo.value=z[7];
	     	form1.txtnomcama.value=z[8];
	        ajaxCmbTipoHab(form1); 	
		   }  else {
	        alert("Error during AJAX call. Please try again VerificarCama");
	     }
	   }
	}

function ActualizarCama(h){

	var Q=h.cmbtipocama.selectedIndex;
	var tipocama=h.cmbtipocama.options[Q].text;	
	var W=h.cmbtipo.selectedIndex;
	var tipohab=h.cmbtipo.options[W].text; 
	var E=h.cmbposicion.selectedIndex;
	var posicion=h.cmbposicion.options[E].text;
	var observ=h.txtObservaciones.value;
	var piso=h.txtpiso.value;
	var posx=h.txtposx.value;
	var posy=h.txtposy.value;
	var codigo=h.txtcodigo.value;
	var nomcama=h.txtnomcama.value;

	
	window.location.href="ControlActualizarCama?tipocama="+tipocama+"&tipohab="+tipohab+"&posicion="+posicion+"&observ="+observ+"&piso="+piso+"&codigo="+codigo+"&posx="+posx+"&posy="+posy+"&nomcama="+nomcama;
	
	//ajaxFunctionTraslado();
}

function aviso (){
alert("Cambiar el Nombre De La Cama Afectara Los Cambios en Los Demas Registros");
	
}

///////////////////////////////////////////fin verificar cama//////////////////////////////////////

</script>

</head>

<body onload="ajaxFunctionTraslado()">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 

<div id="contenedor">

		<div id="cabecera">
	<img src="Imagenes/Admisiones.jpg" />
	</div>
	<div id="cabecera2">
	  <div align="right" class="style2"></div>
	</div>

				<div id="lateral">
                <a href="Seguridad_login?accion=cerrar" class="texto" style="text-decoration:blink" style="color:#FF0000"> --Cerrar Session--</a><br><br>
                <a href="menuadm.jsp" class="style3">Volver al Menu</a>
          
		</div>
		<div id="principal">
<form id="form1" name="form1" method="post" >
  <label></label>
  <table width="546" border="1" align="center">
  <tr>
    <td colspan="3" align="center">ACTUALIZACION DE CAMA </td>
    </tr>
  <tr>
    <td width="158" align="center">Area</td>
    <td width="166" align="center">Subarea</td>
    <td width="200" align="center">Cama</td>
  </tr>
  <tr>
    <td align="center"><select name="cmbarea" id="cmbarea" onchange="ajaxtraslado(form1)">
      <option selected="selected">SELECCIONE...</option>
    </select></td>
    <td align="center"><select name="cmbsubarea" id="cmbsubarea" onchange="ajaxtrasladocama(form1)">
      <option selected="selected">SELECCIONE...</option>
    </select></td>
    <td align="center"><select name="cmbcama" id="cmbcama" onchange="VerificarCama(form1)">
      <option selected="selected">SELECCIONE...</option>
    </select></td>
  </tr>
  <tr>
    <td align="center"><label>
      <input name="txtcmbposicion" type="text" id="txtcmbposicion" style="visibility:hidden" />
    </label></td>
    <td align="center"><label>Nombre De La Cama </label></td>
    <td align="center"><label>
      <input name="txtnomcama" type="text" id="txtnomcama" onclick="aviso()" />
    </label></td>
  </tr>
  <tr>
    <td colspan="3"><label>
      <input name="txttipohab" type="text" id="txttipohab"  style="visibility:hidden" />
      <input name="txttipocama" type="text" id="txttipocama" style="visibility:hidden"  />
      <input name="txtcodigo" type="text" id="txtcodigo"  style="visibility:hidden" />
    </label></td>
    </tr>
  <tr>
    <td align="center">Tipo Habitacion </td>
    <td align="center">Tipo De La Cama </td>
    <td align="center">Piso</td>
  </tr>
  <tr>
    <td align="center"><label>

      <select name="cmbtipo" id="cmbtipo">
<option selected="selected">SELECCIONE...</option>			
			<!--  <option>Unipersonal</option>
             <option>Bipersonal</option>  -->
      </select>
    </label></td>
    <td align="center"><label>
      <select name="cmbtipocama" id="cmbtipocama">
        <option selected="selected">SELECCIONE...</option>
<!--	
<option>Sencilla</option>
        <option>Camilla</option>
        <option>Quirofano</option>
        <option>Servo Cuna</option>
        <option>Incubadora</option>
        <option>Cama Cuna</option>-->	
      </select>
    </label></td>
    <td align="center"><label>
      <input name="txtpiso" type="text" id="txtpiso" />
    </label></td>
  </tr>
  <tr>
    <td align="center">Posicion en X </td>
    <td align="center">Posicion en Y </td>
    <td align="center">Posicion</td>
  </tr>
  <tr>
    <td align="center"><label>
      <input name="txtposx" type="text" id="txtposx" />
    </label></td>
    <td align="center"><label>
      <input name="txtposy" type="text" id="txtposy" />
    </label></td>
    <td align="center"><label>
      <select name="cmbposicion" id="cmbposicion">
<option>Seleccione...</option>
    <!--    <option>HORIZONTAL</option>
        <option>VERTICAL</option>-->
      </select>
    </label></td>
  </tr>
  <tr>
    <td colspan="3" align="center">Observaciones</td>
    </tr>
  <tr>
    <td colspan="3" align="center"><textarea name="txtObservaciones" id="txtObservaciones" cols="45" rows="4"></textarea></td>
    </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td align="center"><label></label></td>
    <td align="center"><input type="button" name="Button" value="Actualizar"  onclick="ActualizarCama(form1)" /></td>
    <td align="center"><label></label></td>
  </tr>
</table>
  <p>&nbsp;</p>
</form>
</div>
</div>
	
</body>
</html>
