
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
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
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
function CambiarSeleccion (){
	var opcion=document.getElementById('cmbOpciones').value;
	   ajax=getXMLObject();
	   ajax.open("POST","ControlFormatoHic",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('opciones').innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor="+opcion); //Posting txtname to Servlet
	
	//ControlFormatoHic
}

function Radio() {
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radio");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlFormatoHic?valor="+val,true); //getname will be the servlet name
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
			document.getElementById('OpcRepo').innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,Radio");
	     }
	   }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
function generarfechasDiagnostico(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni

	 

	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

		if(fechaIni==""){
			alert("Ingrese la Fecha de Inicio");
			}
		if(fechaFin==""){
			alert("Ingrese la Fecha Final");
			}
	if((fechaIni!="")&&(fechaFin!="")){
	 pagina="con_DiagnosticosFecha.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	}
}

function generartodoDiagnostico(){
	pagina="con_DiagnosticosTodo.jsp"			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);
}
/********************************************************************/
function generarfechasMedicamentos(){

	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //alert("Fecha Inicio "+fechaIni)
	 
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin
	 //alert("Fecha Fin "+fechaFin)

	 if(fechaIni==""){
			alert("Ingrese la Fecha de Inicio");
			}
		if(fechaFin==""){
			alert("Ingrese la Fecha Final");
			}
	if((fechaIni!="")&&(fechaFin!="")){
	 pagina="con_MedicamentosFecha.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	}
}

function generartodoMedicamentos(){
	pagina="con_MedicamentosTodo.jsp"			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);
}
/*********************************************************************/
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Consultas.jsp">Consultas</a>-Reportes</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1" >
		  <table width="100%"> 
		<tr>
			<td width="100%">       
					<table width="100%" class="style6" border="0">
                      <tr >
                        <td colspan="2" align="center" id="cabecera2" class="style11"><span>GENERAR REPORTES </span></td>
                      </tr>
                      <tr>
                        <td width="195" align="left">Seleccione una Opcion </td>
                        <td width="761" align="left"><select name="cmbOpciones" id="cmbOpciones" onchange="CambiarSeleccion ()">
                            <option value="Seleccione" selected="selected">Seleccione</option>
                            <option value="diagnosticos">Diagnosticos Mas Frecuentes</option>
                            <option value="medicamentos">Medicamentos Mas Usados</option>
                          </select>
                            <label></label>
                            <label></label></td>
                      </tr>
                      <tr>
                        <td colspan="2" align="left"><div id="opciones" ></div></td>
                      </tr>
                    </table>
</form></td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>