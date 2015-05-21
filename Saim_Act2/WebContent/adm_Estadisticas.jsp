
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
<title>Estadisticas Admisiones</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>

<script> 
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio,evt) {//funcion para revisar la fecha
	var sep = "/";
	if (d.valant != d.value) {
		val = d.value
		largo = val.length
		ini = val.substring(0, 2);
		if ((ini > 31) || (ini == "00")) {
			// alert("Dia No Valido!!!");
			val = d.value = "01";
			// d.focus();
		}
		val = val.split(sep);

		val2 = ''
		for (r = 0; r < val.length; r++) {
			val2 += val[r]
		}
		ini = val2.substring(2, 4);
		if ((ini == "04") || (ini == "06") || (ini == "09") || (ini == "11")) {
			x = val2.substring(0, 2);
			if (x == "31") {
				val2 = "30";
				val2 = val2 + ini;
			}
		}
		if ((ini > 12) || (ini == "00")) {
			// alert("Mes No Valido!!!");
			mescj = val2.substring(0, 2);
			val2 = mescj + "01";
			// d.focus();
		}

		ano = val2.substring(4, 8);
		dia = val2.substring(0, 2);

		if (ini == "02") {
			if ((dia == "30") || (dia == "31") || (dia == "29")) {
				if (ano.length == 4) {
					if ((ano % 100 != 0)
							&& ((ano % 4 == 0) || (ano % 400 == 0))) {
						val2 = "29";
						val2 = val2 + ini;
					} else {
						val2 = "28";
						val2 = val2 + ini;
					}
					val2 = val2 + ano;
				}
			}
		}

		//
		 // /////////////////////////////////////////////////// //Validar fecha
		 // mayor que la fecha actual
		 // 
		 // if(ano.length==4){ if (ano==annio) { if(ini==mes){
		 // if((dia==dias)||(dia<dias)){ alert("La fecha de Vencimiento no puede
		 // ser menor o igual a la fecha Actual"); val2=''; } }else{ if(ini<mes){
		// alert("La fecha de Vencimiento no puede ser menor o igual a la fecha
		// Actual"); val2=''; } } } if (ano<annio) { alert("La fecha de
		 // Vencimiento no puede ser menor o igual a la fecha Actual"); val2=''; } }
		 // 
		 // ///////////////////////////////////////////////////
		///
		if (nums) {
			for (z = 0; z < val2.length; z++) {
				if (isNaN(val2.charAt(z))) {
					letra = new RegExp(val2.charAt(z), "g")
					val2 = val2.replace(letra, "")

				}
			}
		}

		val = ''
		val3 = new Array()
		for (s = 0; s < pat.length; s++) {
			val3[s] = val2.substring(0, pat[s])
			val2 = val2.substring(pat[s])
		}

		for (q = 0; q < val3.length; q++) {
			if (q == 0) {
				val = val3[q]
			} else {
				if (val3[q] != "") {
					val += sep + val3[q]
				}
			}
		}
		d.value = val
		d.valant = val
	}
	
	var tecla=evt.keyCode;
	
	//var elEvento =  window.event;  // arguments[0] ||
   // var tecla = elEvento.keyCode;
   // if(tecla == 13) { 
    //		document.getElementById("ref0").focus();
    		
  //  }/
	
}
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

function GenerarPromedio(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni; 
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin;
	/*var txtFechaInical=document.getElementById("txtFechaInical").value;
	var txtFechaFinal=document.getElementById("txtFechaFinal").value;
	var cmbServicios=document.getElementById("cmbServicios").value;*/
	ajax=getXMLObject();
	ajax.open("POST","ControlCenso",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('valor').innerHTML =ajax.responseText;				
			//resultado.innerHTML = validar;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=POR&txtFechaInical="+fechaIni+"&txtFechaFinal="+fechaFin);	
}
function getCheckedRadio() {
	if(xmlhttp) { 
		 var radioButtons = document.getElementsByName("radiobutton");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;     
		    xmlhttp.open("POST","ControlCenso?valor="+val,true); //getname will be the servlet name
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

 function GenerarReporteEntidad(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni 
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin
	 //////////////////
	 if(diaIni=="Dia"){alert("Seleccione Dia Inicial");}
		else{
			if(mesIni=="Mes"){alert("Seleccione Mes Inicial");}
			else{
				if(anioIni==""){alert("Digite Año Inicial.");}
				else{
					if(diaFin=="Dia"){alert("Seleccione Dia Final");}
					else{
						if(mesFin=="Mes"){alert("Seleccione Mes Final");}
						else{
							if(anioFin==""){alert("Digite Año Final.");}
							}
						}
					}
				}
			}
	 if((diaIni!="Dia")&&(mesIni!="Mes")&&(anioIni!="")&&(diaFin!="Dia")&&(mesFin!="Mes")&&(anioFin!="")){
	 pagina="adm_EstadisticaEntidad.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	 }
  }

 function GenerarReportePatologias(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni
	//////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

	 if(diaIni=="Dia"){alert("Seleccione Dia Inicial");}
		else{
			if(mesIni=="Mes"){alert("Seleccione Mes Inicial");}
			else{
				if(anioIni==""){alert("Digite Año Inicial.");}
				else{
					if(diaFin=="Dia"){alert("Seleccione Dia Final");}
					else{
						if(mesFin=="Mes"){alert("Seleccione Mes Final");}
						else{
							if(anioFin==""){alert("Digite Año Final.");}
							}
						}
					}
				}
			}

	 if((diaIni!="Dia")&&(mesIni!="Mes")&&(anioIni!="")&&(diaFin!="Dia")&&(mesFin!="Mes")&&(anioFin!="")){
	 pagina="adm_EstadisticaPatologias.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
	 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	 window.open(pagina,"NUEVA",opciones);
	 }

	 }
 function GenerarReportesUsuario(){
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni 
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

		 if(diaIni=="Dia"){alert("Seleccione Dia Inicial");}
		else{
			if(mesIni=="Mes"){alert("Seleccione Mes Inicial");}
			else{
				if(anioIni==""){alert("Digite Año Inicial.");}
				else{
					if(diaFin=="Dia"){alert("Seleccione Dia Final");}
					else{
						if(mesFin=="Mes"){alert("Seleccione Mes Final");}
						else{
							if(anioFin==""){alert("Digite Año Final.");}
							}
						}
					}
				}
			}
	 if((diaIni!="Dia")&&(mesIni!="Mes")&&(anioIni!="")&&(diaFin!="Dia")&&(mesFin!="Mes")&&(anioFin!="")){
		 pagina="adm_EstadisticasUsuarios.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin;			
		 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		 window.open(pagina,"NUEVA",opciones);
	 }
 }

 function GenerarTrasladosActivos(){
	 var NomServicio=document.getElementById('cmbEntidad').value; 
	 if(NomServicio=='Seleccione'){
			alert("Seleccione Un Servicio.");
	 }else{
		 pagina="adm_ImprimirTrasladosActivos.jsp?servicio="+NomServicio;			
		 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		 window.open(pagina,"NUEVA",opciones);
		 }
	 
	 }
 function GenerarReportesSalidas(){
	 var TipoSalida="null";
	 var radioButtons = document.getElementsByName("radiobutton1");
     for (var x = 0; x < radioButtons.length; x ++) {
       if (radioButtons[x].checked) {
	         TipoSalida=radioButtons[x].value;     
		  }	     
	   }
	 var CodEps=document.getElementById('cmbEntidad').value;     
	 var diaIni=document.getElementById('cmbdiaIni').value;
	 var mesIni=document.getElementById('cmbmesIni').value;
	 var anioIni=document.getElementById('txtanioIni').value;
	 var fechaIni=anioIni+"-"+mesIni+"-"+diaIni 
	 //////////////////
	 var diaFin=document.getElementById('cmbdiaFin').value;
	 var mesFin=document.getElementById('cmbmesFin').value;
	 var anioFin=document.getElementById('txtanioFin').value;
	 var fechaFin=anioFin+"-"+mesFin+"-"+diaFin

		 if(TipoSalida=="null"){alert("Seleccione Tipo de Salida");}
			 else{
			 if(diaIni=="Dia"){alert("Seleccione Dia Inicial");}
			else{
				if(mesIni=="Mes"){alert("Seleccione Mes Inicial");}
				else{
					if(anioIni==""){alert("Digite Año Inicial.");}
					else{
						if(diaFin=="Dia"){alert("Seleccione Dia Final");}
						else{
							if(mesFin=="Mes"){alert("Seleccione Mes Final");}
							else{
								if(anioFin==""){alert("Digite Año Final.");}
								}
							}
						}
					}
				}
			 }
	// alert("TipoSalida "+TipoSalida);
	 if((TipoSalida!="null")&&(CodEps=="Seleccione")&&(diaIni!="Dia")&&(mesIni!="Mes")&&(anioIni!="")&&(diaFin!="Dia")&&(mesFin!="Mes")&&(anioFin!="")){
		// alert("Entra Reporte Sin Entidad");
		 ajax=getXMLObject();
		 document.getElementById('Rport').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
			ajax.open("POST","ControlCenso",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if(ajax.readyState == 4){
					//alert("Sin Entidad "+ajax.responseText);
					document.getElementById("Rport").innerHTML = ajax.responseText;
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=RSE&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&TipoSalida="+TipoSalida);
			
		/* pagina="adm_ReporteSalidas.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&TipoSalida="+TipoSalida;			
		 var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		 window.open(pagina,"NUEVA",opciones);*/
	 }
	 if((TipoSalida!="null")&&(CodEps!="Seleccione")&&(diaIni!="Dia")&&(mesIni!="Mes")&&(anioIni!="")&&(diaFin!="Dia")&&(mesFin!="Mes")&&(anioFin!="")){
		 //alert("Entra Reporte Con Entidad");
		 ajax=getXMLObject();
		 document.getElementById('Rport').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
		 
			ajax.open("POST","ControlCenso",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if(ajax.readyState == 4){
					//alert("Con Entidad "+ajax.responseText);
					document.getElementById("Rport").innerHTML = ajax.responseText;
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=RCE&fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&TipoSalida="+TipoSalida+"&CodEps="+CodEps);

		/* pagina="adm_ReporteSalidasEps.jsp?fechaIni="+fechaIni+"&fechaFin="+fechaFin+"&TipoSalida="+TipoSalida+"&CodEps="+CodEps;			
		 var opciones1="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		 opciones1 =opciones1+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		 window.open(pagina,"NUEVA",opciones1);*/
	 }
	
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
<table width="100%" border="0"><tr><td id="Rport">
<table width="100%" border="0">
<tr >
<td>
	 <table width="100%" border="0">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Consultas-Estadisticas</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%" border="0"> 
		<tr>
			<td width="100%">       
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1" >
		    <table width="100%" class="style6" border="0">
              <tr >
                <td colspan="3" align="center" id="cabecera2" class="style11"><span>GENERAR REPORTES </span></td>
              </tr>
              
              <tr>
                <td width="25%" align="left">REPORTE PACIENTES POR ENTIDADES </td>
                <td width="75%" align="left"><label>
                  <input name="radiobutton" type="radio" value="1" id="1"  onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
              </tr>
              <tr>
                <td align="left">REPORTE PACIENTES POR PATOLOGIA </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="2" id="2" onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
              </tr>
			 <tr>
                <td align="left">REPORTE DE ADMISION POR USUARIO </td>
                <td align="left"><label>
                  <input name="radiobutton" type="radio" value="3" id="3" onClick="getCheckedRadio()" >
                </label></td>
                <td><label></label></td>
              </tr>
			<tr>
                <td align="left">REPORTE DE SALIDA </td>
                <td align="left"><label><input name="radiobutton" type="radio" value="5" id="5" onClick="getCheckedRadio()" ></label></td>
                <td><label></label></td>
              </tr>

			<tr>
                <td align="left">REPORTE DE TRASLADOS ACTIVOS </td>
                <td align="left"><label><input name="radiobutton" type="radio" value="6" id="6" onClick="getCheckedRadio()" ></label></td>
                <td><label></label></td>
              </tr>
<tr>
                <td align="left">PROMEDIO ESTANCIA </td>
                <td align="left"><label><input name="radiobutton" type="radio" value="PO" id="PO" onClick="getCheckedRadio()" ></label></td>
                <td><label></label></td>
              </tr>
              <tr>
                <td colspan="5" align="left" id="cabecera2">&nbsp;</td>
              </tr>
              <tr>
                <td colspan="5" align="left"><div id="valor" ></div></td>
              </tr>
            </table>

<div id="tipos" ></div>
</form></td></tr></table>
</div>
</td></tr></table></td></tr></table>
<%} %>
</body>
</html>