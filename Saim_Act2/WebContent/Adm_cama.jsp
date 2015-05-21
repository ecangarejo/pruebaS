<%@ page import="adm_bean.CrearCama,java.util.*" import="adm_logica.* "%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Creacion de Camas</title>

<script type="text/javascript">
//------------------------------------------------------------------------//
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
//------------------------------------------------------------------------//
	function ajaxCmbPosicion() {		
		  if(xmlhttp) { 	 
		    xmlhttp.open("POST","ControlCrearCama?va=2",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPosicion;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
		   }		
		}


	function ComprobarPosicion() {		
		   if (xmlhttp.readyState == 4) {	
			if(xmlhttp.status == 200) {		
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	form1.cmbposicion.length=y;		     	
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cmbposicion.options[x+1].text=z[x];			   
			    }
		     				    	           	
		     }
		     else {
		        alert("Error during AJAX call. Please try again COMPROBAR CMBTIPOHAB");
		       }			
		   }	   
		}
	
		
	function ajaxCmbTipoHab() {	
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlCrearCama?va=6",true); //getname will be the servlet name
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
	     				    	           	
	     }
	     else {
	        alert("Error during AJAX call. Please try again COMPROBAR CMBTIPOHAB");
	       }			
	   }
	   ajaxCmbTipoCama()	   
	}


function ajaxCmbTipoCama() {		
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlCrearCama?va=5",true); //getname will be the servlet name
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
	     }
	     else {
	        alert("Error during AJAX call. Please try again COMPROBAR CMBTIPOCAMA");
	       }			
	   }
	   ajaxCmbPosicion();	   
	}
	function ComprobarPeticion() {
		
		   if (xmlhttp.readyState == 4) {	   

			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	form1.cbNomArea.length=y;
		    
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cbNomArea.options[x+1].text=z[x];
			  
			    }
		     	 ajaxCmbTipoHab();			    		
		    	//  document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element            	
		     }
		     else {
		        alert("Error during AJAX call. Please try again COMPROBARPETICION");
		     }
		   }
		}
	 
	function ajaxFunction() {

		  if(xmlhttp) { 	 
		    xmlhttp.open("POST","ControlCrearCama?va=1",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticion;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
			}

		}



	function ajax(h) {
		  if(xmlhttp) { 
			  var x=h.cbNomArea.selectedIndex;
			  x=h.cbNomArea.options[x].text;
			xmlhttp.open("GET","ControlCrearCama?x="+x+"&va="+2,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarSubarea;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}

//////////////////////////////////////CODIGO SUBAREA/////////////////////////////////////
		function ajaxCodigo(h) {
			  if(xmlhttp) { 
				  var subarea=h.cbSubArea.selectedIndex;
				  subarea=h.cbSubArea.options[subarea].text;
			
				xmlhttp.open("POST","ControlCodigoSubarea?nomsub="+subarea,true); //getname will be the servlet name
			    xmlhttp.onreadystatechange  = ComprobarCodigoSubarea;
			    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			    xmlhttp.send(""); //Posting txtname to Servlet
				  }
			}
			
			
			function ComprobarCodigoSubarea() {

		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	var c=xmlhttp.responseText;
			     form1.txtCodigoEstado.value=c;

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}			
	///////////////////////////////FIN CODIGO SUBAREA///////////////////////////////////////
	function ComprobarSubarea() {
		
		   if (xmlhttp.readyState == 4) {
			   if(xmlhttp.status == 200) {
			   	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");
		       	form1.cbSubArea.length=y;
		     	form1.cbSubArea.selectedIndex=0;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cbSubArea.options[x+1].text=z[x];
			    }
		    		
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}

	function verificarIngresoCama(h){
	
		var s=h.cbNomArea.selectedIndex;
		var nomarea=h.cbNomArea.options[s].text;
			
		var a=h.cmbtipo.selectedIndex;
		var tiphab=h.cmbtipo.options[a].text; 
			
		var aa=h.cmbtipocama.selectedIndex;
		var tipcam=h.cmbtipocama.options[aa].text;	
		
		var piso=h.txtPiso.value;
		
		var observ=h.txtObservaciones.value;
		
		var x=h.cbSubArea.selectedIndex;		
		var sub=h.cbSubArea.options[x].text;
			
		var p=h.cmbposicion.selectedIndex;
		var posicion=h.cmbposicion.options[p].text;
		
		var posx=h.txtposx.value;
		
		var posy=h.txtposy.value;
		
		var cosest=h.txtCodigoEstadoCama.value;
		
		var codsuba=h.txtCodigoEstado.value;
		
		var numcam=h.txtNumeroCama.value;

		var Direccionamiento=h.cmbDireccionamiento.value;
		if(Direccionamiento=="Seleccione"){
			alert("Seleccione El Direccionamiento de la Cama.");
			}
		if(piso==""){
			alert("Falta Ingresar El Piso!!!");
			}
		if(nomarea=="SELECCIONE..."){
			alert("Falta Seleccionar El Area!!!");
			}
		if(sub=="SELECCIONE..."){
			alert("Falta Seleccionar El Subarea!!!");
			}
		if(tiphab=="SELECCIONE..."){
			alert("Falta Seleccionar El Tipo De Habitacion!!!");
			}
		if(tipcam=="SELECCIONE..."){
			alert("Falta Seleccionar El Tipo De Cama!!!");
			}
		if(numcam==""){
			alert("Falta Ingresar El Numero De La Cama!!!");
			}
			
		if((piso!="")&&(nomarea!="SELECCIONE...")&&(sub!="SELECCIONE...")&&(tiphab!="SELECCIONE...")&&(tipcam!="SELECCIONE...")&&(numcam!="")&&(Direccionamiento!="Seleccione")){
			window.location.href="ControlInsertarCama?tipohabitacion="+tiphab+"&area="+nomarea+"&subarea="+sub+"&piso_ubicacion="+piso+"&tipo_cama="+tipcam+"&observaciones="+observ+"&cod_estado="+cosest+"&cod_subarea="+codsuba+"&cod_cama="+numcam+"&posicion="+posicion+"&posx="+posx+"&posy="+posy+"&Direccionamiento="+Direccionamiento;
			alert("Cama Creada Con Exito!!!");
			}
		
	
	}//fin de verificar index

	
	</script>

</head>

<body onload="ajaxFunction();" id="back">

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
				con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// DivisiÃƒÂ³n en trozos con la barra como delimitador. 
	partes = url.split('/'); 
	 
	var a=partes[partes.length-1];
	
	window.location.href="PermisosPagina?va=1&pa="+a+"&codusu="+<%=codigou%>+""; 
	
</script>
<%} %>
<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")||cod.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>
<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>


<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Administracion-Crear Cama</b></div>
</td>
</tr>

<tr id="principal1">
<td>

<div>
  
	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/> 
								<%@include file="CopyAdm.jsp"%>
			</td>

			<td width="100%">   
					<br />
<% String val=request.getParameter("val"); %>
<%
if(val==null){val="";}
if(val.equals("1")){%>
<script >
alert("FALTA ALGUN DATO POR INGRESAR...")
</script>
<%}
if(val.equals("2")){%>
<script >
alert("DATO INGRESADO CON EXITO...")
</script>	


<%}%>

<form id="form1" name="form1" method="post"  style="margin-top:-17px;">
<table width="100%" class="style6" >
  <tr>
    <td colspan="3" align="center" id="cabecera2" class="style11">CREACION DE CAMA </td>
    </tr>
  <tr>
    <td width="179" align="center">Numero Cama </td>
    <td width="219" align="center">Tipo Habitacion </td>
    <td width="192" align="center">Nombre Area </td>
  </tr>
  <tr>
    <td height="32" align="center"><label>
      <input name="txtNumeroCama" type="text" id="txtNumeroCama" />
      <span class="style8">*</span></label></td>
    <td align="center"><label>
      <select name="cmbtipo" id="cmbtipo" >
<option selected="selected">SELECCIONE...</option>
      <!--  <option>UNIPERSONAL</option>
        <option>BIPERSONAL</option>-->
      </select>
      <span class="style8">*</span></label></td>
    <td align="center"><label>
      <select name="cbNomArea" id="cbNomArea" onchange="ajax(form1)">
	<option selected="selected">SELECCIONE...</option>
      </select>
      <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td align="center"><p>Nombre Sub-Area </p></td>
    <td align="center">Piso  </td>
    <td align="center"> <p>Tipo De La Cama </p></td>
    <input name=" txtCodigoEstadoCama" type="text" id="txtCodigoEstadoCama" value="1" style="display:none" />   
  </tr>
  <tr>
    <td height="31" align="center"><select name="cbSubArea" id="cbSubArea" onchange="ajaxCodigo(form1)">
      <option selected="selected">SELECCIONE...</option>
    </select>
      <span class="style8">*</span></td>
    <td align="center"><input name="txtPiso" type="text" id="txtPiso" />
      <span class="style8">*</span></td>
    <td align="center"><select name="cmbtipocama" id="cmbtipocama">
<option selected="selected">SELECCIONE...</option>
     <!-- <option selected="selected" >SENCILLA</option>
      <option >CAMILLA</option>
      <option >QUIROFANO</option>
      <option >SERVOCUNA</option>
      <option >INCUBADORA</option>
      <option >CAMACUNA</option>--> 
    </select>
      <span class="style8">*</span></td>
  </tr>
  <tr>
    <td height="28" align="center">Posicion en X </td>
    <td align="center">Posicion en Y </td>
    <td align="center">Posicion de La Cama </td>
  </tr>
  <tr>
    <td height="29" align="center"><label>
      <input name="txtposx" type="text" id="txtposx" />
    </label></td>
    <td align="center"><label>
      <input name="txtposy" type="text" id="txtposy" />
    </label></td>
	<td align="center"> <p>
	  <label>
	  <select name="cmbposicion" size="1" id="cmbposicion">
<option selected="selected">SELECCIONE...</option>
	  <!--   <option>HORIZONTAL</option>
	    <option>VERTICAL</option> -->
	    </select>
	  </label>
	</p></td>

 <input name="txtCodigoEstado" type="text" id="txtCodigoEstado" style="display:none" />
  </tr>
  <tr>
    <td align="center">Servicio</td>
    <td colspan="2"   align="center">Observaciones</td>
    </tr>
  <tr>
    <td height="33" align="center"><p>
      <label></label>
      <select name="cmbDireccionamiento" id="cmbDireccionamiento">
        <option value="Seleccione" selected="selected">Seleccione</option>
        <option value="1">Urgencia</option>
        <option value="2">Hospitalizacion</option>
        <option value="3">Uci</option>
      </select><span class="style8">*</span>
    </p></td>
    <td colspan="2" rowspan="2" align="center"><textarea name="txtObservaciones" id="txtObservaciones" cols="45" rows="4">BUEN ESTADO</textarea></td>
    </tr>
  <tr>
    <td><p>&nbsp;</p></td>
    </tr>
  <tr>
    <td><span class="style8">Campos Requeridos * </span></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><label></label></td>
    <td align="center"><input name="button"  type="button" id="button" value="Crear" class="boton4" onclick="verificarIngresoCama(form1)" /></td>
    <td align="center"><label></label></td>
  </tr>
</table>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
