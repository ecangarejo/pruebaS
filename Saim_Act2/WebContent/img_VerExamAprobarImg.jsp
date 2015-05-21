
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
<% String actu=request.getParameter("actu");
//System.out.println("codigo actua en JSPPPPPPPPP "+actu);
%>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 

<title>Aprobar Examen</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language="javascript" >

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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxFunction() {	
	if(xmlhttp) { 		 	  
		xmlhttp.open("POST","ControlCitasExamen?valor=1",true); //getname will be the servlet name
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * este procedimiento permite ver el reporte en ireport para su posterior
 	impresion. 
 */
function mostarexamenes (codIce,usuario) {
	pagina="img_AprobarExaImg.jsp?codIce="+codIce+"&usuario="+usuario;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}
/* function reload(){	
		var s="";
		s=
		if(s=="1"){
			alert(s)
			window.location.reload();		
			}	
		else{
			ajaxFunction();
		}
	}
 */


</script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onLoad="ajaxFunction();" id="back"  >
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 

<%
String va=request.getParameter("va");
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// División en trozos con la barra como delimitador. 
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Aprobar Examenes-Aprobar Examen</b></div> 
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
		  <form id="form1" style="margin-top:-15px;" name="form1" >  
 			<div id='grupo'></div>
			<div id='examBuscados'></div>
			<div id='examenes'></div>
          </form>
</td></tr></table>
</div>
</td></tr></table>
<%} %>    
</body>
</html>