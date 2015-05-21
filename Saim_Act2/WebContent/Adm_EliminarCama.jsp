

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Eliminar Cama</title>
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

	function ajaxFunctionTraslado() {
			
		  if(xmlhttp) { 	 
		    xmlhttp.open("POST","ControlEliminarCama?va=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticionTraslado;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
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
		     	ajaxCmbTipoCama();		    	           	
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		       }			
		   }	   
		}	
////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxtraslado(h) {
	
		  if(xmlhttp) { 
			  var x=h.cmbarea.selectedIndex;
			  x=h.cmbarea.options[x].text;
			xmlhttp.open("POST","ControlEliminarCama?x="+x+"&va=2",true); //getname will be the servlet name
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
		    	//  document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}

function ajaxtrasladocama(h) {
	  if(xmlhttp) { 
		  var xx=h.cmbarea.selectedIndex;
		  xx=h.cmbarea.options[xx].text;
		  var y=h.cmbsubarea.selectedIndex;
		  y=h.cmbsubarea.options[y].text;	
	    xmlhttp.open("POST","ControlEliminarCama?va=3&y="+y,true); //getname will be the servlet name
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
	     	form1.cmbcama.selectedIndex=0;
	     	ajaxCmbTipoHab();	     	 	
	    	//  document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 	    	  
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }	   
	}
///////////////////////////////////////verificar cama//////////////////////////////////////////////

function VerificarCama(h){
	if(xmlhttp) { 
		var s=h.cmbcama.selectedIndex;
		var cama=h.cmbcama.options[s].text;
	    xmlhttp.open("POST","ControlEliminarCama?va=4&cama="+cama,true); //getname will be the servlet name
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
	     	form1.txtcodigocam.value=z[0];	     
	     	form1.txttipohab.value=z[1];	     	
	     	form1.txtpiso.value=z[2];	     
	     	form1.txttipocama.value=z[3];		     	
	    	form1.txtobervacion.value=z[4];	    		
	     	form1.txtposx.value=z[5];	     
	     	form1.txtposy.value=z[6];	   
	     	form1.txtposi.value=z[7];	    	     	 			  		  
	     	}  else {
	        alert("Error during AJAX call. Please try again");
	     	}
	   }
}

function EliminarCama(h){
	var codcam=h.txtcodigocam.value;
	if(codcam==""){
alert("Seleccione La Cama");
		}
	else{
	window.location.href="ControlEliminarCama?codigo="+codcam;
	}
}

</script>
</head>

<body onload="ajaxFunctionTraslado()">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
<%String codigou =(String)session.getAttribute("codusuario");
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
				con.conn.close();
	       	%>


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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Administracion-Eliminar Cama</b></div>
</td>
</tr>

<tr id="principal1">
<td>

<div >

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="CopyAdm.jsp"%>
			</td>

			<td width="100%">                
<form id="form1" name="form1" method="post" >
<table width="100%" class="style6" align="center">
  <tr id="cabecera2">
    <td colspan="3" align="center" class="style11">ELIMINAR CAMA </td>
    </tr>
  <tr align="center">
    <td>Area</td>
    <td>Subarea</td>
    <td >Cama</td>
  </tr>
  <tr>
    <td align="center"><label>
      <select name="cmbarea" id="cmbarea" onchange="ajaxtraslado(form1)">
	  <option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
    <td align="center"><label>
      <select name="cmbsubarea" id="cmbsubarea" onchange="ajaxtrasladocama(form1)">
<option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
    <td align="center"><label>
      <select name="cmbcama" id="cmbcama" onchange="VerificarCama(form1)">
<option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td align="center">Tipo Habitacion </td>
    <td align="center">Tipo Cama </td>
    <td align="center">Piso</td>
  </tr>
  <tr>
    <td align="center"><label>
      <input name="txttipohab" type="text" id="txttipohab"  readonly=""/>
    </label></td>
    <td align="center"><label>
      <input name="txttipocama" type="text" id="txttipocama" readonly="" />
    </label></td>
    <td align="center"><label>
      <input name="txtpiso" type="text" id="txtpiso" readonly="" />
    </label></td>
  </tr>
  <tr>
    <td align="center">Posicion en X </td>
    <td align="center">Posicion en Y </td>
    <td align="center">Posicion</td>
  </tr>
  <tr>
    <td align="center"><label>
      <input name="txtposx" type="text" id="txtposx"  readonly=""/>
    </label></td>
    <td align="center"><label>
      <input name="txtposy" type="text" id="txtposy" readonly="" />
    </label></td>
    <td align="center"><label>
      <input name="txtposi" type="text" id="txtposi" readonly="" />
    </label></td> 
  </tr>
<tr><td>&nbsp;</td> </tr>
  <tr>
    <td colspan="3" id="cabecera2" class="style11" align="center">Observaciones</td>
    </tr>
  <tr>
    <td colspan="3"  align="center"><label>
      <textarea name="txtobervacion" cols="45" rows="4" id="txtobervacion" readonly="readonly"></textarea>
    </label></td>
    </tr>
  <tr>
    <td colspan="3" align="center"><label>
      <input name="btneliminar" type="button" class="boton4" id="btneliminar" value="Eliminar" onClick="EliminarCama(form1)" />
    </label>      <label>
    <input name="txtcodigocam" type="hidden" id="txtcodigocam"  readonly=""  />
    </label></td>
    </tr>
</table>
 </form>
</td></tr></table>
</div>
</td></tr></table> 

</body>
</html>
