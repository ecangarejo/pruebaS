
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
function BuscarFormatos() {
	if(xmlhttp) { 
		var val="";
		 var radioButtons = document.getElementsByName("radiobutton");
		 var NumDocumento= document.getElementById("txtNumDocumento").value;
		 var TipoDoc= document.getElementById("TipoDoc").value;
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         val=radioButtons[x].id;   
				if(val==""){
					alert("No Ha seleccionado Ninguna Opcion");
					}
				else{
		        xmlhttp.open("POST","ControlCrearFormato",true); //getname will be the servlet name
		     	xmlhttp.onreadystatechange  = function(){
		     		if (xmlhttp.readyState == 4) {
		     			document.getElementById('Resultado').innerHTML = xmlhttp.responseText;
		     			//alert(xmlhttp.responseText);
		     			//window.location.href="Adm_ListaMedico.jsp";
		     		}
		     	};
		     	xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		     	xmlhttp.send("valor="+val+"&NumDocumento="+NumDocumento+"&TipoDoc="+TipoDoc);//Posting txtname to Servlet
			  }
	        }	      	     
	    }
	 }
}
/********************************REPORTES DE CONSULTAS********************************/

function ReporteFormulacionCE(CodFormulacion){
	
    pagina="agm_FormulacionCE.jsp?CodFormulacion="+CodFormulacion;			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
    //OrdenServicio();
}
 function ImprimirEstudiosCE(CodFormlab){
		pagina="agm_FormulacionEstudiosCE.jsp?CodFormlab="+CodFormlab;			
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	}
 
function ReportLabora(CodOrdenLab){	
	pagina="hic_OrdenLaboratorio.jsp?CodOrdenLab="+CodOrdenLab;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);   	
}

function ReportImage(CodOrden){
		
	pagina="hic_OrdenImagenologia.jsp?CodOrden="+CodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);   	
}


function ReportMedica(CodFormulacion){
	
    pagina="hic_Formulacion.jsp?CodFormulacion="+CodFormulacion;			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
	
}
function ReporteOrdenGeneral(CodOrden){

	pagina="hic_OrdenGeneral.jsp?CodOrden="+CodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);   	
}

function ReportesOrdenProcedimiento(CodOrden){
	
	
	pagina="hic_OrdenProcedimientos.jsp?CodOrden="+CodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);   	
}

/*************************************************************************************/

function ImprimirFormatoPreingreso(usuario,CodFormato,anio,mes,dia,hora,minuto,segundo,CedPac){
	var FechaFormato=anio+"-"+mes+"-"+dia
	var HoraFormato=hora+":"+minuto+":"+segundo
	pagina="adm_ImprimirFormatosCola.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CedPac="+CedPac+"&usuario="+usuario;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);
}

function ImprimirEpicrisis(CodEpi){
	pagina="hic_ImprimirEpicrisis.jsp?CodEpi="+CodEpi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function ImprimirDescripcion(venc) {
	pagina = "hic_ReporteDescripcionQx.jsp?venc=" + venc;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}

function ImprimirFormatoHospitalizacion(usuario,CodFormato,anio,mes,dia,hora,minuto,segundo,CodPac,TipoFormato,CodAdmision){
	var FechaFormato=anio+"-"+mes+"-"+dia
	var HoraFormato=hora+":"+minuto+":"+segundo

	/*pagina="hic_ImprimirFormato.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);*/
	if(TipoFormato=="1"){
		//Si el Formato es Historia Clinica de Urgencia y si es 1 no se puede repetir el mismo formato en la misma admision
		pagina="hic_ImprimirFormatoH.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario+"&CodAdmision="+CodAdmision;			
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);

	}
	if(TipoFormato=="0"){
		pagina="hic_ImprimirFormato.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}

	if(TipoFormato=="2"){
		pagina="hic_ImprimirFormatoEnfermeria.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}
}
function mostrarOrdenPat(idcodOrden){
	
	pagina="ordenPatReporte.jsp?idcodOrden="+idcodOrden;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85, left=140";
   	window.open(pagina,"Orden_patologia",opciones);
}


function ImprimirFormatoCexterna(usuario,CodFormato,anio,mes,dia,hora,minuto,segundo,CodPac){
	var FechaFormato=anio+"-"+mes+"-"+dia
	var HoraFormato=hora+":"+minuto+":"+segundo
	//alert("FechaFormato "+FechaFormato+" HoraFormato "+HoraFormato+" usuario "+usuario+" CodFormato "+CodFormato+" CedPac "+CedPac);
	pagina="agm_ImprimirFormatoCE.jsp?HoraFormato="+HoraFormato+"&FechaFormato="+FechaFormato+"&CodFormato="+CodFormato+"&CodPac="+CodPac+"&usuario="+usuario;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina,"NUEVA",opciones);
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
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")){%>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="HistoriaClinica.jsp">Historia Clinica</a>-Consultar Formatos</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1" >
		  <table width="100%" > 
		<tr>
			<td width="100%">       
					<table  class="style6" width="100%">
                      <tr >
                        <td colspan="6" align="center" id="cabecera2" class="style11"><span>GENERAR REPORTES </span></td>
                      </tr>
                      <tr>
                        <td width="23%" align="left">Tipo Documento </td>
                        <td width="21%" align="left"><label></label>
                        <label>
                        <select name="TipoDoc" id="TipoDoc">
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
 	st3.close();
 	rs3.getStatement().getConnection().close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
                        </select>
                        </label></td>
                        <td width="22%" align="left">Numero Documento </td>
                        <td width="23%" align="left"><input name="txtNumDocumento" type="text" id="txtNumDocumento"></td>
                        <td width="11%" align="left"></td>
						<td></td>                      
						</tr>
</table>
<table width="100%" >
                        <tr>                      
                        <td ><label>
                          <input name="radiobutton" type="radio" value="radiobutton" id="hos" onClick="BuscarFormatos()" >
                        Formatos Hospitalizacion</label></td>
				        <td  ><label>
                          <input name="radiobutton" type="radio" value="radiobutton" id="pre" onClick="BuscarFormatos()" >
                        Formatos Preadmision</label></td>
						<td ><label>
                          <input name="radiobutton" type="radio" value="radiobutton" id="cex" onClick="BuscarFormatos()" >
                        Formatos Consulta Externa</label></td>
						<td ><label>
                          <input name="radiobutton" type="radio" value="radiobutton" id="cor" onClick="BuscarFormatos()" >
                        Consultar Ordenes</label></td>
						<td ><label>
                          <input name="radiobutton" type="radio" value="radiobutton" id="corex" onClick="BuscarFormatos()" >
                        Consultar Ordenes Consulta Externa</label></td>
						<td ><label>
                          <input name="radiobutton" type="radio" value="radiobutton" id="epi" onClick="BuscarFormatos()" >
                        Consultar Epicrisis</label></td>
						<td ><label>
                          <input name="radiobutton" type="radio" value="radiobutton" id="qx" onClick="BuscarFormatos()" >
                        Consultar Descripcion Qx</label></td>
                      </tr>
                      <tr>
                        <td colspan="7" align="left"><div id="Resultado" ></div></td>
                      </tr>
                    </table>
</form></td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>