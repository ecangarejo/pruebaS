
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Crear Examen</title>

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

function ajaxFunction() {	
	  if(xmlhttp) { 		 	 
	  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
	    xmlhttp.open("POST","ControlCrearSubExa?valor=1",true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = ComprobarPeticion;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
		  }
	}

function ComprobarPeticion() {
	
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {

			document.getElementById('grupo').innerHTML = xmlhttp.responseText;
	    }
	     else {
	        alert("Error during AJAX call. Please try again,ComprobarPeticion");
	     }
	   }
	}


function IngresarExaSub(){
	var codGrupo=document.getElementById('cmbarea').value;
	//alert(codGrupo)
	var nomExam=document.getElementById('txtExamen').value;
	//alert(nomExam)
	var codExam=document.getElementById('txtCodExam').value;
	//alert(codExam)
	var patron=document.getElementById('txtpatron').value;

	var CodUsu=document.getElementById("txtCodusuario").value;
	//alert(patron)
	/*var e;
    //alert(direccion.length);
    for(e=0;e<patron.length;e++){
    	patron=patron.replace('Ñ','@');
    	patron=patron.replace('ñ','@');
    	patron=patron.replace('Á','A');
    	patron=patron.replace('É','E');
    	patron=patron.replace('Í','I');
    	patron=patron.replace('Ó','O');
    	patron=patron.replace('Ú','U');
    	patron=patron.replace('á','a');
    	patron=patron.replace('é','e');
    	patron=patron.replace('í','i');
    	patron=patron.replace('ó','o');
    	patron=patron.replace('ú','u');
      //alert(direccion);
    }*/
	if(codGrupo=="SELECCIONE"){
		alert("Falta Escojer El Grupo.");		
		}
	if(nomExam==""){
		alert("Falta Llenar El Nombre del Examen.");
		}
	if(codExam==""){
		alert("Falta Llenar El Codigo del Examen.");
		}
	if(patron==""){
		alert("Falta Llenar El Patron del Examen.");
		}
	if((codGrupo!="SELECCIONE")&&(nomExam!="")&&(codExam!="")&&(patron!="")){
		
		window.location.href="ControlCrearSubExa?codGrupo="+codGrupo+"&nomExam="+nomExam+"&codExam="+codExam+"&patron="+patron+"&CodUsu="+CodUsu;
		//alert("ControlCrearSubExa?codGrupo="+codGrupo+"&nomExam="+nomExam+"&codExam="+codExam);
			alert("SubGrupo Creado con Exito");
		}
	else{
	alert("No se Insertaron Los Datos")}
}

</script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onLoad="ajaxFunction();" id="back" >
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
</script>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Crear Examen</b></div> 
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
		  <form style="margin-top:-15px;" id="form1"  name="form1" >  
 			<div id='grupo'></div>
<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
          </form>
</td></tr></table>
</div>
</td></tr></table>
    <%} %>
</body>
</html>