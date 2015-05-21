
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="pkgsys.*,java.util.*" %>
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
<%

String numero=request.getParameter("numero");
if(numero==null){
	numero="";
}

String TipoDoc=request.getParameter("TipoDoc");
if(TipoDoc==null){
	TipoDoc="";
}
%> 
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%> 


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script languaje="javascript" src="img_autocompletaexamen.js"></script>
<script languaje="javascript" src="img_autocompletarestudio.js"></script>
<script languaje="javascript" src="ajaxseguridad.js"></script>
<title>Asignacion de Examen</title>

<style type="text/css">
#sugerencias li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias {width:218px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 100px ; overflow : scroll ; Cursor : pointer;}
</style>

<style type="text/css">
#sugerencias1 li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias1 {width:545px; border:1px solid black; display:none; margin-left: 0px; Cursor : pointer;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
</style>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">
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
///////////////////////////////////////////////////////////////////////////
function validar(h){
		if (h.txtnumdoc.value.length==0){
			window.alert('Debe Digitar la Cedula');
			h.nom.focus();
			return false;
		}
	h.submit();
	}
///////////////////////////////////////////////////////////////////////////
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
/////////////////////////////////////////////////////////////////////////////
function ComprobarBusEstudio() {
	var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('examenes').innerHTML = xmlhttp.responseText;
			 r = document.getElementById('sugerencias');
				r.style.display='none';
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}
function img_BusEstudio() {
	  if(xmlhttp) {
		  var tipo=document.getElementById('cbafiliacion').value;		
		  var cedula=document.getElementById('txtnumdoc').value;
	    xmlhttp.open("POST","ControlExamenImag?valor=3&cedula="+cedula+"&tipo="+tipo,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarBusEstudio;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	   var x;
	}
///////////////////////////////////////////////////////////////////////////
function vacio(){
		var tipo=document.getElementById('cbafiliacion').value;		
	    var cedula=document.getElementById('txtnumdoc').value;
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {			
			 var texto= xmlhttp.responseText;
			 if(texto==""){
				window.location.href="ControlExamenImag?valor=1&cedula="+cedula+"&tipo="+tipo+"&op=1",true;
				 }
			 else{
				 img_BusEstudio();
				 }
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
	}
function ingresar15(){
		var xmlhttp = new getXMLObject();
		if(xmlhttp) {
		var tipo=document.getElementById('cbafiliacion').value;		
	    var cedula=document.getElementById('txtnumdoc').value;
		xmlhttp.open("POST","ControlExamenImag?valor=0&cedula="+cedula+"&tipo="+tipo,true); //getname will be the servlet name
		xmlhttp.onreadystatechange  = vacio();
		xmlhttp.send(""); //Posting txtname to Servlet
	  }
}
	


////////////////////////////////////////////////////////////////////////////////
function ComprobarVerExamenes() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('especifico').innerHTML = xmlhttp.responseText;
	    }
	     else {
	        alert("Error during AJAX call. Please try again,ComprobarPeticion");
	     }
	   }
}
function VerExamenes() {	
	  if(xmlhttp) { 
		  var codGrupo=document.getElementById('cmbgrupos').value;		 	 
	    xmlhttp.open("POST","ControlCitasExamen?valor=2&codGrupo="+codGrupo,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarVerExamenes;
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }
	}

/////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////
function finalizar(){
alert("Examenes Asignados Satisfactoriamente!!!!");
window.location.href="Imagenologia.jsp";
	
}
/////////////////////////////////////////////////////////////////
function enviar(){
	var usuario="";	
	usuario=<%=codigou%>;	
	  getXMLObject();
		var xmlhttp = new getXMLObject();
	    ajax=getXMLObject();
		var cedula=document.getElementById('txtnumdoc').value;
		var codPaciente=document.getElementById("txtcedula").value;	
		var codExamen=document.getElementById("txtcodexamen").value;
		var hora=document.getElementById("txthora").value;
		var fecha=document.getElementById("txtfecha").value;	
		var datosClinico=document.getElementById('txtdatosClinicos').value;
		var portatil="";
		if(form1.chkportatil.checked){
			 portatil=form1.chkportatil.value;
			}
		if(codPaciente==""){
			alert("No se Ha Seleccionado Ningun Paciente");
			}
		if(codExamen==""){
			alert("No se Ha Seleccionado Ningun Examen");
			}
		if(hora==""){
			alert("No hay Hora Seleccionado");
			}
		if(fecha==""){
			alert("No hay Fecha Seleccionado");
			}
		if(datosClinico==""){
			alert("Los Datos Clinicos No Pueden Ir Vacios");
			}
			
		if((codPaciente!="")&&(codExamen!="")&&(hora!="")&&(fecha!="")&&(datosClinico!="")){
			    ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name
			    ajax.onreadystatechange  = function(){
			    	if (ajax.readyState==4) {

						//DivValor.innerHTML = ajax.responseText;
			    		//alert(ajax.responseText);
			    		alert("Ingreso Exitoso");
			    		codExamen="";
						var NomExamen=document.getElementById("txtnomexam").value="";
						//form1.chkportatil.checked=false;
						//NomExamen.focus();
			    	}
				}
			    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			    ajax.send("valor=3&codigoExa_fk="+codExamen+"&codigoPac_fk="+codPaciente+"&estado=-1&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&datosClinico="+datosClinico+"&portatil="+portatil+"&cedula="+cedula); //Posting txtname to Servlet
		}
}
/*
function PERMISOS() {		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {			     	
				var a = xmlhttp.responseText		     	
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
}*/
////////////////////////////////////////////////////////////////////
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onload="img_BusEstudio(),fecha_gru(),hora_gru();" id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
</script> 

<%
if(codigou.equals("")){%>

<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");window.location.href="Seg_login.jsp";</script>
<%}else{
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

<table width="100%">
<tr align="right">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Buscar Estudios</b></div>
</td>
</tr>

<tr id="principal1">
 <%
String id=request.getParameter("id");String nombre=request.getParameter("nombre");String tip=request.getParameter("tipo");String z=request.getParameter("z");String cod=request.getParameter("cod");
String gene=request.getParameter("gene");
String edad=request.getParameter("edad");
if(id==null){
	id="";
};if(nombre==null){nombre="";};if(numero==null){numero="";};if(tip==null){tip="";};if(z==null){z="";};if(cod==null){cod="";};
if(edad==null){
	edad="";
}
%>
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
  
							 <form id="form1" style="margin-top:-15px;" name="form1" onkeypress = "return pulsar(event);" >
							  
							   <table width="100%" align="center" class="style6">
							
							     <tr id="cabecera2" class="style11">
							       <td colspan="5"><div align="center" class="style4">DATOS DEL PACIENTE </div></td>
							     </tr>
							
							     <tr>
							       <td>&nbsp;</td>
							       <td><input name="txtfecha" type="hidden" id="txtfecha" /></td>
							       <td>&nbsp;</td>
							       <td><input name="txthora" type="hidden" id="txthora" /></td>
							     </tr>

							     <tr>
							       <td align="right">TIPO DE DOCUMENTO</td>
							       <td > <label>
							        <select class="style6" name="cbafiliacion" size="1" id="cbafiliacion">
							        <option selected="selected" value="<%=TipoDoc%>"><%=TipoDoc%></option>
						         <% 
 ResultSet rs3=null;
 Statement st3 = null;  
 try{
 	Conexion con2=new Conexion();
 	st3 = con2.conn.createStatement();
 	rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)%></option>
 	<%}
 	rs3.getStatement().close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
							        </select>
							      </label>  </td> 
							       <td ><label>NUMERO</label></td>
							       <td ><label>
							        <input name="txtnumdoc" type="text" id="txtnumdoc" onkeyup="autocompleta()" value="<%=numero %>" />
							        <input name="btnBuscar" type="button" class="boton4" id="btnBuscar" value="Buscar" onclick="ingresar15()" /> 
							       </label></td>
							<td align="right">&nbsp;</td>
							     </tr>  
							     <tr>
							       <td>&nbsp;</td>
							       <td>&nbsp;</td>
							       <td>&nbsp;</td>
							       <td><div id='sugerencias'></div></td>
							       <td align="right">&nbsp;</td>
							     </tr>	 
								  <tr>
							    <td colspan="4"><div id="resultado" style="display:none" ></div></td>
							    </tr>
							   </table>
							<div id='examenes'></div>
							<div id='especifico'></div>
							     </form>
</td></tr></table>
</div>
</td></tr></table>
	 <%} %>
 
</body> 
</html>
