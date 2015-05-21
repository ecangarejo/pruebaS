

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
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Liberar Cama Reservada</title>

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



function ajaxLlenarArea() {
	
	  if(xmlhttp) { 	 
	    xmlhttp.open("POST","ControlLiberarReser?va=1",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarArea;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	
	}	
////////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarArea() {
	
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
	        alert("Error during AJAX call. Please try again, AREA");
	     }		
	   }   
	}


function ajaxLlenarSubarea(h) {
	  if(xmlhttp) { 
		  var x=h.cmbarea.selectedIndex;
		  x=h.cmbarea.options[x].text;		
		xmlhttp.open("POST","ControlLiberarReser?x="+x+"&va=2",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarSubarea;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }
	}
///////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarSubarea() {
	
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
	     	form1.cmbsubarea.selectedIndex=0;	    		
	    }
	     else {
	        alert("Error during AJAX call. Please try again, SUBAREA");
	     }
	   }
	}


///////////////////////////////////////////////////////////////////////////////////////////////
function ajaxLlenarCama(h) {
	  if(xmlhttp) { 
		  
		var y=h.cmbsubarea.selectedIndex;
		y=h.cmbsubarea.options[y].text;
	    xmlhttp.open("POST","ControlLiberarReser?va=3&y="+y,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarCama;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }
	}
/////////////////////////////////////////////////////////////////////////////////////////////////
function ComprobarCama() {
	
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
	     	
	     }
	     else {
	        alert("Error during AJAX call. Please try again,CAMA");
	     }
	   }
	   
	}


//////////////////////////////////////////////////////////////////////////////////////
function VerificarCama(h){	
		if(xmlhttp) { 
			var s=h.cmbcama.selectedIndex;
			var cama=h.cmbcama.options[s].text;
			
		    xmlhttp.open("POST","ControlLiberarReser?va=4&cama="+cama,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarCodcama;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		 
	}//fin de verificar index

	function ComprobarCodcama() {
		   if (xmlhttp.readyState == 4) {
			   if(xmlhttp.status == 200) {
			   	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");

		     	
		     	
		     	 form1.txtcodpre.value=z[0];
			     form1.txtcodreser.value=z[1];
			     form1.txtnombre.value=z[2];
			     form1.txtprapellido.value=z[3];
			     form1.txtfecha.value=z[4];			     
			     form1.txtcedula.value=z[5];
			     form1.txteps.value=z[6];
			     form1.txttipodoc.value=z[7];
			     form1.txtseapellido.value=z[8];
			     form1.txtnumauto.value=z[9];
			     form1.txtautopor.value=z[10];
		     	
			     
			   }  else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		   
		}

	function ActualizarCamaReser(h){
	
		var codigoCama=h.txtcodreser.value;
		var codPre=h.txtcodpre.value;
	
		
		if(codigoCama==""){
		alert("No Se Ha Escogido Ninguna Cama")
		}else{		

		window.location.href="ControlLiberarReser?codigoCama="+codigoCama+"&codPre="+codPre;
		alert("Actualizacion Exitosa!!!")
		window.location.href="menuadm.jsp";
		}	
	}//fin de trasladar paciente	


</script>
</head>

<body onload="ajaxLlenarArea()" id="back">
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
				con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// Division en trozos con la barra como delimitador. 
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Administracion-Cancelar Reservacion</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/>
								<%@include file="CopyAdm.jsp"%>
			</td>

			<td width="100%">   
					<br />
<form id="form1" name="form1" method="post" style="margin-top:-17px;"  >

<table width="100%"  class="style6" align="center">
  <tr>
    <td colspan="3" align="center" id="cabecera2" class="style11">LIBERAR CAMA </td>
    </tr>
  <tr>
    <td width="209" align="center">AREA</td>
    <td width="212" align="center">SUBAREA</td>
    <td width="194" align="center">CAMA</td>
  </tr>
  <tr>
    <td align="center"><label>
      <select name="cmbarea" id="cmbarea"   onchange="ajaxLlenarSubarea(form1)">
        <option selected="selected">SELECCIONE...</option>
	  </select>
    </label></td>
    <td align="center"><label>
      <select name="cmbsubarea" id="cmbsubarea" onchange="ajaxLlenarCama(form1)">
        <option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
    <td align="center"><label>
      <select name="cmbcama" id="cmbcama" onchange="VerificarCama(form1)">
        <option selected="selected">SELECCIONE...</option>
      </select>
    </label></td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  <tr>
    <td colspan="3" align="center" id="cabecera2" class="style11">DATOS DE LA RESERVACION</td>
    </tr>

  <tr>
    <td align="center">TIPO DOCUMENTO </td>
    <td align="center">NUMERO DOCUMENTO </td>
    <td align="center">EPS</td>
  </tr>
  <tr align="center">
    <td><input name="txttipodoc" type="text" id="txttipodoc" size="34" readonly="" /></td>
    <td><label>
      <input name="txtcedula" type="text" id="txtcedula" size="34" readonly="" />
    </label></td>
    <td><label>
      <input name="txteps" type="text" id="txteps" size="34" readonly="" />
    </label></td>
  </tr>
  <tr>
    <td align="center"><label>NOMBRE</label></td>
    <td align="center">PRIMER APELLIDO </td>
    <td align="center">SEGUNDO APELLIDO </td>
  </tr>
  <tr align="center">
    <td><label>
      <input name="txtnombre" type="text" id="txtnombre" size="34" readonly="" />
    </label></td>
    <td><label>
      <input name="txtprapellido" type="text" id="txtprapellido" size="34" readonly="" />
    </label></td>
    <td><label>
      <input name="txtseapellido" type="text" id="txtseapellido" size="34" readonly="" />
    </label></td>
  </tr>
  <tr>
    <td align="center">FECHA</td>
    <td align="center">NUMERO AUTORIZACION </td>
    <td align="center">AUTORIZADO POR </td>
  </tr>
  <tr align="center">
    <td><label>
      <input name="txtfecha" type="text" id="txtfecha" size="34" readonly="" />
    </label></td>
    <td><label>
      <input name="txtnumauto" type="text" id="txtnumauto" size="34"  readonly="" />
    </label></td>
    <td><label>
      <input name="txtautopor" type="text" id="txtautopor" size="34" readonly="" />
    </label></td>
  </tr>
  <tr>
    <td><label>
      <input name="txtcodpre" type="hidden" id="txtcodpre" />
      </label></td>
    <td><label></label></td>
    <td><input name="txtcodreser" type="hidden" id="txtcodreser" /></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><label>
      <input name="btnliberar" class="boton4" type="button" id="btnliberar" value="     Liberar     " onclick="ActualizarCamaReser(form1)" />
    </label></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
    </tr>
</table>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
