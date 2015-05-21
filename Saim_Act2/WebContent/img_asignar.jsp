<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script languaje="javascript" src="img_autocompletaexamen.js"></script>
<title>Asignacion de Examen</title>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {Cursor : pointer; width:218px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 100px ; overflow : scroll ; desbordamiento: auto;}
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
/////////////////////////////////////////////////////////////////
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
/////////////////////////////////////////////////////////////////////////////
function Comprobarimg_grupo() {
	var x;
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			document.getElementById('examenes').innerHTML = xmlhttp.responseText;
			div.style.display="";
			r = document.getElementById('sugerencias');
			r.style.display='none';
	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}
function img_grupo() {
	  if(xmlhttp) { 
		  var tipo=document.getElementById('cbafiliacion').value;		
		  var cedula=document.getElementById('txtnumdoc').value;
	    xmlhttp.open("POST","ControlExamenImag?valor=2&cedula="+cedula+"&tipo="+tipo,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = Comprobarimg_grupo;
	    xmlhttp.send(""); //Posting txtname to Servlet
	   }
	   var x;
	}

/////////////////////////////////////////////////////////////////
function vacio(){
	var tipo=document.getElementById('cbafiliacion').value;		
    var cedula=document.getElementById('txtnumdoc').value;
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {			
		 var texto= xmlhttp.responseText;
		 if(texto==""){
			window.location.href="ControlExamenImag?valor=1&cedula="+cedula+"&tipo="+tipo+"&op=2",true;
			 }
		 else{
				img_grupo();
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
function asignacion(){
	alert("Examen Asignado Correctamente!!!");	
}

function enviar(d){
	var usuario="";
	usuario=<%=codigou%>;	
	  getXMLObject();
		  var xmlhttp = new getXMLObject();
	  if(xmlhttp) { 
		var cedula=document.getElementById('txtnumdoc').value;
		var datosClinico=document.getElementById('txtdatosClinicos').value;
		/*var e;
	    for(e=0;e<datosClinico.length;e++){
	    	datosClinico=datosClinico.replace('Ñ','@');
	    	datosClinico=datosClinico.replace('ñ','@');
	    	datosClinico=datosClinico.replace('á','a');
	    	datosClinico=datosClinico.replace('é','e');
	    	datosClinico=datosClinico.replace('í','i');
	    	datosClinico=datosClinico.replace('ó','o');
	    	datosClinico=datosClinico.replace('ú','u');
	    	datosClinico=datosClinico.replace('Á','A');
	    	datosClinico=datosClinico.replace('É','E');
	    	datosClinico=datosClinico.replace('Í','I');
	    	datosClinico=datosClinico.replace('Ó','O');
	    	datosClinico=datosClinico.replace('Ú','U');
	    }*/  
		var codPaciente=document.getElementById("txtcedula").value;
		var hora=document.getElementById("txthora").value;
		var fecha=document.getElementById("txtfecha").value;
		var portatil="";
		if(form1.chkportatil.checked){
			 portatil=form1.chkportatil.value;
			}	
		if(d==""){
			alert("No hay Ningun Examen Seleccionado");
			}
		if(datosClinico==""){
			alert("Los Datos Clinicos No Pueden Ir Vacio.");
			}
		if(codPaciente==""){
			alert("No se Ha Seleccionado Ningun Paciente.");
			}	
		if(hora==""){
			alert("No Hay Hora Seleccionada.");
			}
		if(fecha==""){
			alert("No Hay Fecha Seleccionada.");
			}
		if((datosClinico!="")&&(codPaciente!="")&&(hora!="")&&(fecha!="")){
	    xmlhttp.open("POST","ControlCitasExamen?valor=3&codigoExa_fk="+d+"&codigoPac_fk="+codPaciente+"&estado=-1&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&datosClinico="+datosClinico+"&portatil="+portatil+"&cedula="+cedula,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = PERMISOS;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet	    
		}	
		asignacion();  
	}	
	  //window.location.reload(); 
}
/////////////////////////////////////////////////////////////////
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
////////////////////////////////////////////////////////////////////
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onload="img_grupo(),fecha_gru(),hora_gru();" id="back" >
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Asignación de Citas</b></div>
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

 <form id="form1"  name="form1"  style="margin-top:-15px;" onkeypress = "return pulsar(event);" >
				
				   <table width="100%" align="center" class="style6" >
				
				     <tr >
				       <td colspan="5" id="cabecera2" class="style11"><div align="center">DATOS DEL PACIENTE </div></td>
				     </tr>
				 
				     <tr>
				       <td>&nbsp;</td>
				       <td><input name="txtfecha" type="hidden" id="txtfecha" /></td>
				       <td>&nbsp;</td>
				       <td ><input name="txthora" type="hidden" id="txthora" /></td>
				     </tr>

				     <tr>
				       <td align="right">TIPO DE DOCUMENTO <label>
				        <select name="cbafiliacion" size="1" id="cbafiliacion" onchange="autocompleta()">
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
				      </label>  </td>
				       <td ><label>NUMERO</label><label>
				        <input name="txtnumdoc" type="text" id="txtnumdoc" onkeyup="autocompleta()" value="<%=numero %>" />
				        <input name="btnBuscar" type="button" id="btnBuscar" class="boton4" value="Buscar" onclick="ingresar15()" />
				       </label></td>
				</tr>
				     <tr>
				       <td>&nbsp;</td>
				       <td colspan="2"><div id='sugerencias'></div></td>
				       </tr>	 
					  <tr>
				    <td colspan="4"><div id="resultado" style="display:none" ></div></td>
				    </tr>
				   </table>
				<div id='examenes'></div>

				<div  align="center" id='especifico'></div> 
				     </form>
</td></tr></table>
</div>
</td></tr></table>
<%} %>
</body> 
</html>
