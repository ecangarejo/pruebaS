
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
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Reportes</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>

<script> 
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
function getCheckedRadio() {
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radiobutton");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlCrearGrupo?valor="+val,true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = Mostrar;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }	     
	    }
	 }
}
function Mostrar() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			document.getElementById('valor').innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,getCheckedRadio");
	     }
	   }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
function generartodo () {		
	pagina="lab_ReporteTodo.jsp"			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}
function GenerarExcel(){
	ajax=getXMLObject();
	ajax.open("POST","ControlSeguimientoAdmision",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Creado Exitosamente.");
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=0");
}

/////////////////////////////////////////////////////////////////////////
function generarfechas(){
 var diaIni=document.getElementById('cmbdiaIni').value;
 var mesIni=document.getElementById('cmbmesIni').value;
 var anioIni=document.getElementById('txtanioIni').value;
 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
 //////////////////
 var diaFin=document.getElementById('cmbdiaFin').value;
 var mesFin=document.getElementById('cmbmesFin').value;
 var anioFin=document.getElementById('txtanioFin').value;
 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

 pagina="lab_ReportesLaboratorioFecha.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
 window.open(pagina,"NUEVA",opciones);
 
}
function GenerarFechasEntidad(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="lab_ReporteLaboratoriosEntidades.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
}
function GenerarFechasMedico(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="lab_ReporteLaboratorioMedico.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
}

function GenerarPacientePorExamen(){
	var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="lab_PacientePorExamen.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
}
function GenerarDetallado(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="lab_ReporteLaboratorioDetallado.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
}

function GenerarDetalladoDia(){
	var dia=document.getElementById('cmbdia').value;
	 var mes=document.getElementById('cmbmes').value;
	 var anio=document.getElementById('txtanio').value;
	 var Fecha=anio+"-"+mes+"-"+dia

	 pagina="lab_ReporteLaboratorioDetalladoDia.jsp?Fecha="+Fecha;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
}

 function GenerarFechasPyP(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="lab_ReporteLaboratorioPyP.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);

	 }
</script> 
</head>
<body id="back">

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
	// DivisiÃ³n en trozos con la barra como delimitador. 
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Reportes-Reportes Laboratorio</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>  
							<%@include file="Copiamenu.jsp"%>	
			</td>
    
			<td width="100%">       
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1" >
		    <table width="100%" class="style6" border="0">
              <tr >
                <td colspan="3" align="center" id="cabecera2" class="style11"><span>GENERAR REPORTES </span></td>
              </tr>
              
              <tr>
                <td width="25%" align="left">REPORTE GENERAL </td>
                <td width="75%" align="left"><label>
                  <input name="radiobutton" type="radio" value="2" id="2"  onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
              </tr>
              <tr>
                <td align="left">REPORTE GENERAL POR FECHAS </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="3" id="3" onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
              </tr>
				<tr>
                <td align="left">REPORTE DETALLADO POR MES </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="9" id="9"  onClick="getCheckedRadio()" >
                </label></td>
                <td width="194"><label></label></td>
            	  </tr>
				<tr>
                <td align="left">REPORTE DETALLADO POR DIA </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="10" id="10"  onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
            	  </tr>

				<tr>
                <td align="left">REPORTE PACIENTE POR EXAMEN </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="11" id="11"  onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
            	  </tr>

 				<tr>
                <td align="left">REPORTE POR ENTIDADES </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="6" id="6"  onClick="getCheckedRadio()" >
                </label></td>
                <td ><label></label></td>
              </tr>
				<tr>
                <td align="left">REPORTE POR MEDICO </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="7" id="7"  onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
              </tr>
			<tr>
                <td align="left">REPORTE DE PyP </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="8" id="8"  onClick="getCheckedRadio()" >
                </label></td>
                <td ><label></label></td>
              </tr>
              <tr>
                <td colspan="3" align="left" id="cabecera2">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="3" align="left"><div id="valor" ></div></td>
              </tr>
            </table>

<div id="tipos" ></div>
</form>
</td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>