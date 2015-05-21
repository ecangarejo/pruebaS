

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
<title>Eliminar Subarea</title>

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
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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
	     		    	           	
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	       }			
	   }	   
	}

function ajaxtraslado(h) {
	
	  if(xmlhttp) { 
		  var x=h.cmbarea.selectedIndex;
		  x=h.cmbarea.options[x].text;
		xmlhttp.open("POST","ControlEliminarCama?x="+x+"&va=2",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarSubareaTraslado;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
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

function VerificarSubarea(h){
	if(xmlhttp) { 
		var s=h.cmbsubarea.selectedIndex;
		var subarea=h.cmbsubarea.options[s].text;
	    xmlhttp.open("POST","ControlEliminarSubarea?va=1&subarea="+subarea,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = CoSubAre;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
		 }
	
}//fin de verificar cama

function CoSubAre() {
	//alert("CoSubAre");
	   if (xmlhttp.readyState == 4) {
		   if(xmlhttp.status == 200) {
		   	var a=xmlhttp.responseText;
	     	var y=a.split("_").length;
	     	var z=a.split("_");
	     	form1.txtcodigosub.value=z[0];  
	     	}  else {
	        alert("Error during AJAX call. Please try again");
	     	}
	   }
}
function EliminarSubarea(h){
	var codsub=h.txtcodigosub.value;

	if(codsub==""){
alert("Falta Seleccionar El Subarea");
		}
	else{
	window.location.href="ControlEliminarSubarea?codsub="+codsub;
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Administracion-Eliminar Sub-Area</b></div>
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
    <td align="center" class="style11">ELIMINAR SUB-AREA </td>
    </tr>
<tr><td>&nbsp;</td></tr>
  <tr>
    <td align="center">Seleccione Area </td>
    </tr>
  <tr>
    <td align="center"><select name="cmbarea" id="cmbarea" onchange="ajaxtraslado(form1)" >
<option selected="selected">SELECCIONE...</option>
    </select></td>
    </tr>
  <tr>
    <td align="center">Seleccione Sub-Area</td>
    </tr>
  <tr>
    <td align="center"><label></label>      <label>
    <select name="cmbsubarea" id="cmbsubarea" onchange="VerificarSubarea(form1)">
<option selected="selected">SELECCIONE...</option>
        </select>
      </label></td>
    </tr>
  <tr>
    <td align="center"><input name="btneliminar" class="boton4" type="button" id="btneliminar" value="Eliminar"  onClick="EliminarSubarea(form1)"/>      <label>
    <input name="txtcodigosub" type="hidden" id="txtcodigosub"/>
    </label></td>
    </tr>
</table>
</form>
</td></tr></table>
</div>
</td></tr></table>

</body>
</html>
