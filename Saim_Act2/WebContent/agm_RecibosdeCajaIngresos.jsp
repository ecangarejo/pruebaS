
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
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
function getCheckedRadio() {
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radiobutton");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlAsignarCita?valor="+val,true); //getname will be the servlet name
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





function BuscarRipsAtencion(){

	ajax=getXMLObject();
	ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 var Medico = document.getElementById('cmbMedico').value;
	 var Entidad= document.getElementById('cmbEntidad').value;

	 var TipoReporte=document.getElementById('TipoReporte').value;
	if(TipoReporte=="Seleccione"){
		alert("Seleccione el tipo de reporte.");
		}

	 if(TipoReporte=="Solo"){
			 if((Entidad!="Seleccione")){
				alert("Este Reporte solo muestra informacion por medico.");
			 }else{
					if(Medico=="Seleccione"){
						alert("Seleccione el medico.");
						}else{
							ajax.onreadystatechange  = function(){
								if (ajax.readyState == 4) {
									//alert(ajax.responseText);
									document.getElementById('Reporte').innerHTML = ajax.responseText;
								}
							};
							ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							ajax.send("valor=Mer3&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&Medico="+Medico+"&Entidad="+Entidad); //Posting txtname to Servlet
										
							}
				 }
	 }
 	if(TipoReporte=="Diligenciado"){
	 
	 if((Medico=="Seleccione")&&(Entidad=="Seleccione")){
			alert("Seleccione el medico o la entidad");
		 }else{
			if((Medico!="Seleccione")&&(Entidad=="Seleccione")){
				//alert("busca por medico");
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
						//alert(ajax.responseText);
						document.getElementById('Reporte').innerHTML = ajax.responseText;
					}
				};
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=Mer0&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&Medico="+Medico+"&Entidad="+Entidad); //Posting txtname to Servlet
			}
			if((Medico=="Seleccione")&&(Entidad!="Seleccione")){
				//alert("busca por entidad");
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
						//alert(ajax.responseText);
						document.getElementById('Reporte').innerHTML = ajax.responseText;
					}
				};
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=Mer1&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&Medico="+Medico+"&Entidad="+Entidad); //Posting txtname to Servlet
				
			}
			if((Medico!="Seleccione")&&(Entidad!="Seleccione")){
				//alert("busca por medico y entidad");
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
					//	alert(ajax.responseText);
						document.getElementById('Reporte').innerHTML = ajax.responseText;
					}
				};
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=Mer2&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&Medico="+Medico+"&Entidad="+Entidad); //Posting txtname to Servlet
				
			}
		 }
 	}
}


function GenerarConsolidado(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="agm_ReporteConsolidado.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
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

	 pagina="agm_ReporteDetallado.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);

	 }
 

 function IndicadoresAtencion(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin
	 var SQL=""; 
	 var SQL0="";
	 var SQL1="";
	 var SQL2="";
	 var TipoConsulta=document.getElementById('cmbTipoConsulta').value;
	 var CodEnt=document.getElementById('cmbEntidad').value;
	 if(TipoConsulta==""){
			alert("Seleccione Tipo Consulta");
	 }else{
		/* pagina="agm_ReporteIndicadorOportunidad.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&SQL="+SQL+"&TipoConsulta="+TipoConsulta;			
		 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		 window.open(pagina,"NUEVA",opciones);*/
		 if(TipoConsulta=="Todos"){
				SQL1="";
		 }else{
				SQL1="and acp.tipo_consulta ='"+TipoConsulta+"'";
		 }
		 if(CodEnt=="Todas"){
			SQL2="";
		 }else{
			SQL2="and aent.ent_nit='"+CodEnt+"'";
		 }
		 SQL0="and acp.fecha BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'";

			 SQL=SQL0+" "+SQL1+" "+SQL2;

		 ajax=getXMLObject();
			ajax.open("POST","ControlAsignarCita",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if(ajax.readyState == 4){
					document.getElementById("Reporte").innerHTML = ajax.responseText;
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=Ind&SQL="+SQL+"&TipoConsulta="+TipoConsulta+"&CodEnt="+CodEnt+"&fechaIni="+fechaIni+"&fechaFin="+fechaFin);
	 }



	 }

 function BuscarCitasCEX(){

	 var CodUsu=document.getElementById('cmbUsuarioCex').value;
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 var FechaAsignacion="";
	 var FechaCita="";
	 var Usuario="";
	 var SQLVALIDA="";

	 if(fechaIni=="-Mes-Dia"){
		 FechaAsignacion="";
	 }else{
		 FechaAsignacion="AND acp.fecha_insercion='"+fechaIni+"'";
		 }

	 if(fechaFin=="-Mes-Dia"){
		 FechaCita="";
	 }else{
		 FechaCita="AND acp.fecha='"+fechaFin+"'";
	 }

	 if(CodUsu=="VerTodos"){
		 Usuario="";
	 }else{
		 Usuario="AND acp.UsuarioInsercion='"+CodUsu+"'";
	 }
	 SQLVALIDA=FechaAsignacion+" "+FechaCita+" "+Usuario+" ORDER BY acp.fecha_insercion,acp.hora_insercion ";
	 //alert(SQLVALIDA);
	 pagina="agm_ReporteCitasAsignadas.jsp?Psql="+SQLVALIDA;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	 }

  function GenerarMedicoEps(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="agm_ReporteMedicoEps.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);

	 }

 function GenerarInasistenciaEps(){

	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="agm_ReporteInasistenciaEps.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	 
	 }

 function GenerarConcurrenciaPaciente(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="agm_ReporteConcurrenciaPacientes.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	 }
 function GenerarTotalReciboCaja(){

	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var FechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var FechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="agm_ToatlReciboCaja.jsp?FechaIni="+FechaIni+"&FechaFin="+FechaFin;		
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	 }


 function GenerarDetalladoReciboCaja(){

	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var FechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var FechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 var usuario=document.getElementById('cmbUsuario').value;
	 if(usuario=="Seleccione"){
		 pagina="agm_DetalladoReciboCaja.jsp?FechaIni="+FechaIni+"&FechaFin="+FechaFin+"&usuario="+usuario;		
		    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		    window.open(pagina,"NUEVA",opciones);
	 }else{

		 pagina="agm_DetalladoReciboCajaU.jsp?FechaIni="+FechaIni+"&FechaFin="+FechaFin+"&usuario="+usuario;		
		    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		    window.open(pagina,"NUEVA",opciones);
		 
		 }

	 
	 }

 function GenerarDetalladoReciboEgreso(){

	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var FechaIni=anioIni+"-"+mesIni+"-"+diaIni
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var FechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 pagina="agm_DetalladoReciboEgreso.jsp?FechaIni="+FechaIni+"&FechaFin="+FechaFin;		
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
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				rs4.getStatement().close();
				//con.cerrar();
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="ConsultaExterna.jsp">Consulta Externa </a>-<a href="ConsultaExterna.jsp">Reportes</a>-Reportes</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id='Reporte'>       
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1" >
		    <table width="100%" class="style6" border="0">
              <tr >
                <td colspan="3" align="center" id="cabecera2" class="style11"><span>GENERAR REPORTES </span></td>
              </tr>
              
			<tr>
                <td align="left">DETALLADO RECIBOS DE INGRESO                
                  <input name="radiobutton" type="radio" value="RC1" id="RC1" onClick="getCheckedRadio()" >                
                </td>
			<td>Usuario
<select name="cmbUsuario" id="cmbUsuario">
      <option value="Seleccione" selected="selected">Seleccione</option>

<% 
 ResultSet rs3=null;
 Statement st3 = null;  
 try{
 	st3 = con.conn.createStatement();
 	rs3=st3.executeQuery("SELECT su.usuario FROM seg_usuario su,agm_detalle_recibo_caja adrc WHERE su.usu_codigo=adrc.CodUsu_fk GROUP BY su.usuario ORDER BY su.usuario");
 	//System.out.println("SELECT su.usuario FROM seg_usuario su,agm_detalle_recibo_caja adrc WHERE su.usu_codigo=adrc.CodUsu_fk GROUP BY su.usuario ORDER BY su.usuario");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)%></option>
 	<%}
 	rs3.getStatement().close();
 	//con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>

    </select>

			</td>
              </tr>

		

              <tr>
                <td colspan="3" align="left" id="cabecera2">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="3" align="left"><div id="valor" ></div></td>
              </tr>
            </table>

<div id="tipos" ></div>
</form></td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>