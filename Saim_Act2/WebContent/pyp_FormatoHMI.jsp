<%@page import="com.lowagie.text.Document"%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	import="adm_logica.Conexion" import="java.sql.*"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico">
<link rel="stylesheet" href="thickbox.css" type="text/css" />

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>  
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />

<title>Informe de PYP</title>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type='text/javascript' src='Browser.js'></script>
<script lang="javascript" type="text/javascript" src="pyp_formatos.js"></script>

 
 <script>
  $(function() {
    $( "#tabs" ).tabs();
  });
  </script>
  
  <script>
  $(function() {
    $( "#accordion" ).accordion({
      collapsible: true
    });
  });
  </script>
  
  <script>
  $(function() {
    $( "#accordion2" ).accordion({
      collapsible: true
    });
  });
  </script>
  
  <script>
  $(function() {
    $( "#accordion3" ).accordion({
      collapsible: true
    });
  });
  </script>

<style type="text/css">
# body {
	-webkit-transition: all; 
	-moz-transition: all;
	-ms-transition: all;
	-o-transition: all;
	transition: all;
</style>


<style type="text/css">
.select{
width: 500px
}
</style>


<script lang="javascript">
	function A(e) {
		tecla = e.keyCode;
		e.which;
		if (tecla == 13) {
			pacienteActivo();
		}
	}
	function pulsar(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		return (tecla != 13);
	}
</script>

<script lang="javascript">
function Verificar(e){
	
	var minavegador = new Browser();
	if (minavegador.name == 'firefox'){
	    if(e) 
		document.onkeypress = function()
		{
	    	return true;
		} ;
	
		var evt = e?e:event; 
		if(evt.keyCode==116) { 
		if(e) 
		document.onkeypress = function(){return false;} ;
			else { 
		evt.keyCode = 0; 
		evt.returnValue = false; 
			} 
		} 
	}
}
</script>

</head>

<body onload="pacienteActivo2();fecha_ingreso();Fecha_Visita()" onKeyDown="Verificar(event)">
	
	<%
	String Genero =request.getParameter("GenValida");
	String 	NumeroDocumento =request.getParameter("NumeroDocumento");
	String 	TipoDocumento =request.getParameter("TipoDocumento");
	if(NumeroDocumento==null){
		NumeroDocumento="";
	}
	if(TipoDocumento==null){
		TipoDocumento="CC";
	}
		
	
		String codigou =(String)session.getAttribute("codusuario");
		//String TipoDocumento =(String)session.getAttribute("TipoDocumento");
		//String 	NumeroDocumento =(String)session.getAttribute("NumeroDocumento");
		//session.removeAttribute("tipodocumentopaciente");
		//session.removeAttribute("numeroDocumento");
	
	%>
	<%
	Conexion con=new Conexion();
	ResultSet rs = null;
	Statement st = null;
		
		String idu="";         
		st = con.conn.createStatement();
		rs=st.executeQuery("select usuario from seg_usuario where usu_codigo="+codigou);
	%>
	<%
		while(rs.next()){
		idu=rs.getString(1);
		}
		rs.getStatement().close();
		
		
		st=null;
		st=con.conn.createStatement();
		rs=st.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
		int madmin=0;
		if(rs.next()){
			madmin=rs.getInt(1);
		}
		rs.getStatement().close();
		con.cerrar();
	%>
	<%
		String cod=request.getParameter("codigo");
	if(codigou==null){
		codigou="";
	}
	if(codigou.equals("")){ //seguridad
	%>

<script lang="javascript">
		alert("Usted No Tiene Permiso Para ver esta Pagina....");
		window.location.href = "Seg_login.jsp";
</script>

	<%
		} else {
	%>
			
	
<table style="width: 100%">
    <tr>
	    <td>
 	        <table style="width: 100%">
		        <tr>
		            <td>
		                <div>
		                    <a href="mensajes.jsp?TB_iframe=true&height=520&width=700" class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin%></b></font></a>
		                    <%if (madmin > 0) {%>
		                    <bgsound src="Imagenes/INNERMK.WAV" loop="2">
		                    <img src="Imagenes/sobr0001.gif" />
		                    <%}%>
		                </div>
		            </td>
		            
		            <td align="right">
		                <div align="right" id="usuario" align="right" class="style11">
		                Bienvenido,<b><%=idu%></b> |<a
		                href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou%>"
		                style="color: white">--Cerrar Session--</a>
		                </div>
		            </td>
		        </tr>
		    </table>
		</td>
	</tr>

	<tr>
	    <td>
	        <div id="cabecera1" align="center" class="linkmenu">
	        <b><a href="menu.jsp">Men&uacute; Principal</a>-<a
	        href="PYP.jsp">PYP</a>-Realizar diagn&oacute;stico
	        pyp</b>
	        </div>
	    </td>
	</tr>
    
    <tr>
	         <td id="cabecera2" class="style11" align="center">
		     REALIZAR INFORME PYP
			 </td>
	</tr>


<!-- CONEXION PARA VALIDAR EL SEXO -->

	<tr>
	    <td>
            <table style="width: 100%">
		        <tr>
		            <td align="center">Tipo de Documento</td>
		            <td>
		                <select name="cbafiliacion" size="1"
			            id="cbafiliacion" onchange="document.getElementById('txtnumdoc').focus();">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery("SELECT " + "sigla, " + "descripcion "
				                    + "FROM " + "adm_tipodocumento");
				                   
				                 %>
								 <option value="<%=TipoDocumento%>"  ><%=TipoDocumento%></option>
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"  ><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  //con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
		            </td>
		            
			        <td align="center">N&uacute;mero de Documento</td>
				    <td>
 				        <form id="form1" name="form1" onkeypress="return pulsar(event);">
				        <input type="text" name="txtnumdoc" id="txtnumdoc" onkeyup="A(event)" value="<%=NumeroDocumento%>" /> 
				        <input type="hidden" name="txtFecha" id="txtFecha" />
				        <input type="hidden" name="txtHora" id="txtHora" /> 
				        <input type="hidden" name="txtCodReporte" id="txtCodReporte" /> 
				        <input type="hidden" name="txtUsuario" id="txtUsuario" value="<%=codigou%>" />
				        </form>
				    </td>	

				    <td>
				    <input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Buscar" onClick="pacienteActivo()">
				    </td>
	     	    </tr>
	        </table>
	    </td>
	</tr>


<tr>
<td id="encuesta_pyp" style="display: none" width="100%" class="style11">
<div id="tabs"> <!-- Inicia div tabs General -->
  <ul>
    <li><a href="#formulario_informacion_usuario">Informaci&oacute;n Usuario</a></li>
    <li><a href="#formulario_planificacion_familiar">Planificaci&oacute;n Familiar</a></li>
    <li><a href="#formulario_consultas">Consultas</a></li> 
  </ul>
  
  
  <div id="formulario_informacion_usuario"> <!-- Primera Pestaña --> 
  	
  	 <div id="accordion"> <!-- Inicia div acordion General-->
  	 	
  	 	<h3>DATOS PERSONALES</h3>	 	
  	  <!-- <div> -->   <!-- Inicia div Acordeon DATOS PERSONALES -->  
  	
		  <table style="width: 100%">
		   <tr>
	       <td >
	        <div id="datos_ingreso_paciente"></div>
	       </td>
	       </tr>
	      <!-- </table> --> 
     

<!-- Tabla Nivel Educativo --> 
<tr>
<td id="nivel_educativo" style="display: none"> 
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">NIVEL EDUCATIVO</td>
<p>&nbsp;</p>

</tr>
</table>
 
<p></p>
<table style="width: 100%">

<tr>
<th colspan="2" align="left">Nivel De Estudio
<select name="nivelEstudio" id="nivelEstudio">
<option value="Seleccione" selected="selected">Seleccione</option>
<option value="Analfabeto">Analfabeto</option>
<option value="Primaria Completa">Primaria Completa</option>
<option value="Bachillerato Completo">Bachillerato Completo</option>
<option value="Tecnico">Tecnico</option>
<option value="Universitario">Universitario</option>
</select>
</th>
</tr>
 
</table>
</td>
</tr>


<!-- Tabla Convivientes -->
<tr>
<td id="convivientes" style="display: none">
<table style="width: 100%">
<tr>
   <td id="cabecera2" class="style11" colspan="2" align="center">CONVIVIENTES</td>
    <p>&nbsp;</p>
</tr>
</table>


<p></p>

<table style="width: 100%">
<tr>
<td colspan="2" align="center">
<form name="formulario">
<label for="chk_solo">Soltero(a)</label>
<input type="checkbox" name="chk_solo" id="chk_solo" onClick="disableConyugue(this);"/>
</td>

<td colspan="2" align="center">
<label for="chk_conyugue">Conyugue</label>
<input type="checkbox" name="chk_conyugue" id="chk_conyugue" onClick="disableSolo(this);"/>
</form>
</td>
</tr>


<tr>
<td align="left"><br>¿Alguno de sus Padres est&aacute; Vivo?</td><td>
<form>
  <br><input type="radio" name="radioPadres" id="siPadres" onclick="clicksiPadres()" value="Si"/>Si
      <input type="radio" name="radioPadres" id="noPadres" onclick="num.disabled=true" value="No"/>No
<td><br>N&uacute;mero:</td>
<td><br><input type="text" name="num" id="numPadres" disabled="disabled" maxlength="1" onkeypress="javascript:return validarNro(event)"/></td></form>
</tr>


<tr>
<td align="left">¿Tiene Hijos?</td><td>
<form>
  <input type="radio" name="radioHijos" id="siHijos" onclick="clicksiHijos()" value="Si"/>Si
  <input type="radio" name="radioHijos" id="noHijos" onclick="clickNoHijos(); num.disabled=true" value="No"/>No
<td>N&uacute;mero:</td>
<td><input type="text" name="num" id="numHijos" disabled="disabled" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td></form>
</tr>


<tr>
<td align="left">¿Tiene Nietos?</td><td>
<form>
  <input type="radio" name="radioNietos" id="siNietos" onclick="clicksiNietos()" value="Si"/>Si
  <input type="radio" name="radioNietos" id="noNietos" onclick="num.disabled=true" value="No"/>No
<td>N&uacute;mero:</td>
<td><input type="text" name="num" id="numNietos" disabled="disabled" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td></form>
</tr>

<tr>
<td align="left">Otros:</td><td>
<form>
  <input type="radio" name="radioOtros" id="siOtros" onclick="clicksiOtros()" value="Si"/>Si
  <input type="radio" name="radioOtros" id="noOtros" onclick="num.disabled=true" value="No"/>No
<td>N&uacute;mero:</td>
<td>
  <input type="text" name="num" id="numOtros" disabled="disabled" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td></form>
</tr>



</table>
</td>
</tr> 


<!-- Tabla Actividad Laboral --> 
 <tr>
 <td id="actividad_laboral" style="display: none">
 <table style="width: 100%">
 <tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">ACTIVIDAD LABORAL</td>
    <p>&nbsp;</p>
  </tr>
</table>

<p></p>

<table style="width: 100%">
<tr>
<td colspan="2" align="left"><label for="actividad">Actividad Laboral: </label>
<select name="actividadLaboral" id="actividadLaboral">
  <option value="Seleccione" selected="selected">Seleccione</option>
  <option value="Desempleado">Desempleado</option>
  <option value="Independiente">Independiente</option>
  <option value="Trabajos Eventuales">Trabajos Eventuales</option>
  <option value="Plan Social">Plan Social</option>
  <option value="Contratista">Contratista</option>
  <option value="Asalariado">Asalariado</option>
  <option value="Pensionado">Pensionado</option>
</select>
  </td>
</tr>

<tr>
<td width="43%" align="left"><br>Fuma:</td>
<td>
  <br><input type="radio" name="Fuma" id="siFuma" value="Si" onclick="clicksiFuma()"/>Si
  	  <input type="radio" name="Fuma" id="noFuma" value="No" onclick="nameFuma.disabled=true"/>No
      &nbsp;&nbsp;&nbsp;&nbsp; 
      ¿Que fuma y con que frecuencia?
      &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
      <input size="40%" type="text" name="nameFuma" id="campoFuma" disabled="disabled"/>
</td>
</tr>

<tr>
<td align="left">Alcohol:</td>
<td>
  <input type="radio" name="Alcohol" id="siAlcohol" value="Si" onclick="clicksiAlcohol()"/>Si
  <input type="radio" name="Alcohol" id="noAlcohol" value="No" onclick="nameAlcohol.disabled=true"/>No
  &nbsp;&nbsp;&nbsp;&nbsp; 
  ¿Que bebidas toma y con que frecuencia?
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input size="40%" type="text" name="nameAlcohol" id="campoAlcohol" disabled="disabled"/>
</td>
</tr>

<tr>
<td align="left">Drogas:</td>
<td>
  <input type="radio" name="Drogas" id="siDrogas" value="Si" onclick="clicksiDrogas()"/>Si
  <input type="radio" name="Drogas" id="noDrogas" value="No" onclick="nameDrogas.disabled=true"/>No
  &nbsp;&nbsp;&nbsp;&nbsp; 
  ¿Que drogas consume y con que frecuencia?
  &nbsp;
  <input size="40%" type="text" name="nameDrogas" id="campoDrogas" disabled="disabled"/>
</td>
</tr>

<tr>
<td align="left">Actividad F&iacute;sica:</td>
<td>
  <input type="radio" name="Actividad_Fisica" id="siActividad_Fisica" value="Si" onclick="clicksiActividadFisica()"/>Si
  <input type="radio" name="Actividad_Fisica" id="noActividad_Fisica" value="No" onclick="nameActividadFisica.disabled=true"/>No
  &nbsp;&nbsp;&nbsp;&nbsp; 
  ¿Que actividad realiza y con que frecuencia?
  &nbsp;&nbsp;
  <input size="40%" type="text" name="nameActividadFisica" id="campoActividadFisica" disabled="disabled"/>
</td>
</tr>

</table>
</td>
</tr>



<!-- Tabla Vivienda Habitat -->
<tr>
	<td id="viviendad_habitat" style="display: none">
  	<table style="width: 100%">
  	<tr>
     <td id="cabecera2" class="style11" colspan="2" align="center">VIVIENDA HABITAT</td>
     <p>&nbsp;</p>
  	</tr>
  	</table>
   
  <p></p>
    
  <table style="width:100%">
  
  <tr>
  <th width="32%" align="left">Propiedad</th><th width="18%"></th>
  <th width="25%" align="left">Servicios</th><th width="25%"></th>
  </tr>
  
  <tr>
  <td align="left">
 	<select name="vivPropiedad" id="vivPropiedad">
	  <option value="Seleccione" selected="selected">Seleccione</option>
	  <option value="Propia">Propia</option>
	  <option value="Alquilada">Alquilada</option>
	  <option value="Familiar">Familiar</option>
	</select>
  </td>
  
  <td></td>
  
  <td align="left">Gas:</td>
  <td>
    <input type="radio" name="radGas" id="Si"/>Si
    <input type="radio" name="radGas" id="No"/>No
  </td>
  </tr>
  
  <tr>
  <td align="left">Comparte Habitaci&oacute;n:</td>
  <td>
    <input type="radio" name="radHabitacionCompartida" id="Si"/>Si
    <input type="radio" name="radHabitacionCompartida" id="No"/>No
  </td>
  <td align="left">Luz:</td>
  <td>
    <input type="radio" name="radLuz" id="Si"/>Si
    <input type="radio" name="radLuz" id="No"/>No
  </td>
  </tr>
  
  <tr>
  <td align="left">Animales Domesticos:</td>
  <td>
    <input type="radio" name="radAnimalesDomesticos" id="Si"/>Si
    <input type="radio" name="radAnimalesDomesticos" id="No"/>No
  </td>
  <td align="left">Aseo:</td>
  <td>
    <input type="radio" name="radAseo" id="Si"/>Si
    <input type="radio" name="radAseo" id="No"/>No
  </td>
  </tr>
  
  <tr>
  <td></td>
  <td></td>  
  <td align="left">Alcantarillado:</td>
  <td>
    <input type="radio" name="radAlcantarillado" id="Si"/>Si 
    <input type="radio" name="radAlcantarillado" id="No"/>No
  </td>
  </tr>
  
  <tr>
  <td></td>
  <td></td>
  <td align="left">Agua:</td>
  <td>
    <input type="radio" name="radAgua" id="Si"/>Si 
    <input type="radio" name="radAgua" id="No"/>No
  </td>
  </tr>
  
  
  <tr><td><br></td></tr>
  
  <tr>
  <th align="left">Convivencia</th>
  </tr>
  
  <tr>
  <td>Se siente en riesgo con su pareja o familia:</td>
  <td>
    <input type="radio" name="radRiesgoPareja" id="Si"/>Si
    <input type="radio" name="radRiesgoPareja" id="No"/>No
  </td>
  </tr>
  
  <tr> 
  <td>Est&aacute; siendo maltratado actualmente por su pareja o familia:</td>
  <td>
    <input type="radio" name="radMaltratoActual" id="Si"/>Si
    <input type="radio" name="radMaltratoActual" id="No"/>No
  </td>
  </tr>
  
  <tr>
  <td>Se han presentado actos de violencia dentro de la relaci&oacute;n con su pareja o familia:</td>
  <td>
    <input type="radio" name="radActosViolencia" id="Si"/>Si
    <input type="radio" name="radActosViolencia" id="No"/>No
  </td>
  </tr>
  
  <tr>
  <td>Satisfecho(a) con la forma como usted comparte con su pareja o familia:</td>
  <td>
    <input type="radio" name="radSatisfaccion" id="Si"/>Si
    <input type="radio" name="radSatisfaccion" id="No"/>No
  </td>
  </tr>
  
  
<tr>
<td><p>&nbsp;</p><input type="submit" value="Guardar Datos Personales" onclick="GuardarDatosPersonales()"/></td>
</tr>
  
  </table>
  </td>
  
</tr>
</table>



<h3>ANTECEDENTES</h3> 
<!-- Tabla Antecedentes Familiares -->
<table style=" width: 100%">  
<tr>
<td id="antecedentes_familiares" style="display: none">
<table style="width: 100%">
  <tr>
     <td id="cabecera2" class="style11" colspan="2" align="center">ANTECEDENTES FAMILIARES</td>
     <p>&nbsp;</p>
  </tr>
</table>

<table style="width: 100%">

<tr>
											<!-- HIPERTENSION ARTERIAL FAMILIAR -->
<td width="36%">1. Hipertension Arterial</td>

		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM hic_antfamiliar haf,cie10 ci,adm_paciente ap WHERE ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente=haf.codpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%' AND observacion='Antecedentes Familiares'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
		%>

 <td width="15%">
    <input type="radio" name="radFamHipertensionArterial" id="Si" checked="checked"/>Si
    <input type="radio" name="radFamHipertensionArterial" id="No"/>No
    <input type="hidden" name="HipArtFAMILIA" id="HipArtFAMILIA" value="0"/>
 </td>
 
 <%}else{ %><!-- Finaliza primera consulta -->
 <td  width="15%">
    <input type="radio" name="radFamHipertensionArterial" id="Si" onclick="AparecerSelects('DivHipArtCIE'); AparecerSelects('DivHipArtFAMILIA')"/>Si
    <input type="radio" name="radFamHipertensionArterial" id="No" onclick="DesaparecerSelects('DivHipArtCIE'); DesaparecerSelects('DivHipArtFAMILIA')"/>No
 </td>
 
 		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoHipertensionArterial>>BuscarAntecedentesFamiliaresHipertensionArterial " + ex);
		}
		%>
 
 		
 											<!-- HEPATOPATIAS FAMILIAR -->
 <td width="35%">4. Hepatopatias</td>
 
	 	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM hic_antfamiliar haf,cie10 ci,adm_paciente ap WHERE ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente=haf.codpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'K7%' AND observacion='Antecedentes Familiares'");
				//System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.nombre LIKE '%HEPATITIS%'");
				if(rs.next()){	
		%>
		
 <td>
    <input type="radio" name="radFamHepatopatias" id="Si" checked="checked"/>Si
    <input type="radio" name="radFamHepatopatias" id="No"/>No
    <input type="hidden" name="HepFAMILIA" id="HepFAMILIA" value="0"/>
 </td>
 
 		<%}else{ %><!-- Finaliza Primera Consulta -->
 
  <td>
    <input type="radio" name="radFamHepatopatias" id="Si" onclick="AparecerSelects('DivHepCIE'); AparecerSelects('DivHepFAMILIA')"/>Si
    <input type="radio" name="radFamHepatopatias" id="No" onclick="DesaparecerSelects('DivHepCIE'); DesaparecerSelects('DivHepFAMILIA')"/>No
 </td>  		
 		
 		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesFamiliaresHepatopatias " + ex);
		}
		%>
</tr>


<tr><!-- Aqui esta el select de hipertension arterial en antecedentes familiares(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select HipArt -->
 			<td><div id="DivHipArtCIE" style="display: none;">
 			<select name="HipArtCIE" id="HipArtCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'I1%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}//Termina Segunda Consulta			
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoHipertensionArterial>>BuscarAntecedentesFamiliaresHipertensionArterial " + ex);
			}
			%>
			
			<td></td>
			
<!-- Aqui esta el select de hepatopatias en antecedentes familiares(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select Hep -->
 			<td><div id="DivHepCIE" style="display: none;">
 			<select name="HepCIE" id="HepCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'K7%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesFamiliaresHepatopatias " + ex);
			}
			%>
			
</tr>

<tr>
<td>
<div id="DivHipArtFAMILIA" style="display: none;">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="HipArtFAMILIA" id="HipArtFAMILIA" size="69%"/>
</div>
</td>

<td></td>

<td>
<div id="DivHepFAMILIA" style="display: none;">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="HepFAMILIA" id="HepFAMILIA" size="69%"/>
</div>
</td>
</tr>


			
			
												<!-- DIABETES FAMILIAR -->		
<tr>  
 <td>2. Diabetes</td>
 
 		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM hic_antfamiliar haf,cie10 ci,adm_paciente ap WHERE ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente=haf.codpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'E1%' AND observacion='Antecedentes Familiares'");
				if(rs.next()){	
		%>
   <td>
    <input type="radio" name="radFamDiabetes" id="Si" checked="checked"/>Si 
    <input type="radio" name="radFamDiabetes" id="No"/>No
    <input type="hidden" name="DiaFAMILIA" id="DiaFAMILIA" value="0"/>
   </td>
   
   		<%}else{ %>
   
   <td>
    <input type="radio" name="radFamDiabetes" id="Si" onclick="AparecerSelects('DivDiaCIE'); AparecerSelects('DivDiaFAMILIA')"/>Si 
    <input type="radio" name="radFamDiabetes" id="No" onclick="DesaparecerSelects('DivDiaCIE'); DesaparecerSelects('DivDiaFAMILIA')"/>No
   </td> 
   
   		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesFamiliaresDiabetes " + ex);
		}
		%>
    
    
    
    										<!-- TUMORES FAMILIAR -->
 <td>5. Tumores</td>
 
		 <%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM hic_antfamiliar haf,cie10 ci,adm_paciente ap WHERE ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente=haf.codpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') AND observacion='Antecedentes Familiares'");
				if(rs.next()){	
		%>
 
   <td>
    <input type="radio" name="radFamTumores" id="Si" checked="checked"/>Si 
    <input type="radio" name="radFamTumores" id="No"/>No
    <input type="hidden" name="TumFAMILIA" id="TumFAMILIA" value="0"/>
   </td>
   
  		 <%}else{ %>
   
   <td>
    <input type="radio" name="radFamTumores" id="Si" onclick="AparecerSelects('DivTumCIE'); AparecerSelects('DivTumFAMILIA')"/>Si 
    <input type="radio" name="radFamTumores" id="No" onclick="DesaparecerSelects('DivTumCIE'); DesaparecerSelects('DivTumFAMILIA')"/>No
   </td>
   
   		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoTumores>>BuscarAntecedentesFamiliaresTumores " + ex);
		}
		%>
</tr>



<tr><!-- Aqui esta el select de diabetes en antecedentes familiares(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select Dia -->
 			<td><div id="DivDiaCIE" style="display: none;">
 			<select name="DiaCIE" id="DiaCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'E1%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesFamiliaresDiabetes " + ex);
			}
			%>

<td></td>
			
<!-- Aqui esta el select de tumores en antecedentes familiares(se coloca aqui para que aparezca debajo de los radio buttons) -->		
			<!-- Aparecer Select Tum -->
 			<td><div id="DivTumCIE" style="display: none;">
 			<select name="TumCIE" id="TumCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE (codigoCIE LIKE 'C0%' OR codigoCIE LIKE 'C1%' OR codigoCIE LIKE 'C2%' OR codigoCIE LIKE 'C3%' OR codigoCIE LIKE 'C4%' OR codigoCIE LIKE 'C5%' OR codigoCIE LIKE 'C6%' OR codigoCIE LIKE 'C7%' OR codigoCIE LIKE '%C80' OR codigoCIE LIKE '%C97' OR codigoCIE LIKE 'D1%' OR codigoCIE LIKE 'D2%' OR codigoCIE LIKE 'D3%' OR codigoCIE LIKE 'D4%')");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoTumores>>BuscarAntecedentesFamiliaresTumores " + ex);
			}
			%>
			
</tr>

<tr>
<td>
<div id="DivDiaFAMILIA" style="display: none;">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="DiaFAMILIA" id="DiaFAMILIA" size="69%"/>
</div>
</td>

<td></td>

<td>
<div id="DivTumFAMILIA" style="display: none;">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="TumFAMILIA" id="TumFAMILIA" size="69%"/>
</div>
</td>
</tr>

								
								<!-- CARDIOPATIAS FAMILIAR -->
<tr>
 <td>3. Cardiopatias</td>
 
		 <%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM hic_antfamiliar haf,cie10 ci,adm_paciente ap WHERE ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente=haf.codpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'Q2%' AND observacion='Antecedentes Familiares'");
				if(rs.next()){	
		%>
   <td>
    <input type="radio" name="radFamCardiopatias" id="Si" checked="checked"/>Si 
    <input type="radio" name="radFamCardiopatias" id="No"/>No
    <input type="hidden" name="CarFAMILIA" id="CarFAMILIA" value="0"/>
   </td>
   
  		 <%}else{ %>
   
   <td>
    <input type="radio" name="radFamCardiopatias" id="Si" onclick="AparecerSelects('DivCarCIE'); AparecerSelects('DivCarFAMILIA')"/>Si 
    <input type="radio" name="radFamCardiopatias" id="No" onclick="DesaparecerSelects('DivCarCIE'); DesaparecerSelects('DivCarFAMILIA')"/>No
   </td>
   
   		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoCardiopatias>>BuscarAntecedentesFamiliaresCardiopatias " + ex);
		}
		%>
		
								<!-- MENTALES FAMILIAR -->
 <td>6. Mentales</td>
 
 		<%
			rs = null; 	
 			st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM hic_antfamiliar haf,cie10 ci,adm_paciente ap WHERE ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente=haf.codpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') AND observacion='Antecedentes Familiares'");
				if(rs.next()){	
		%>
 
   <td>
    <input type="radio" name="radFamMentales" id="Si" checked="checked"/>Si 
    <input type="radio" name="radFamMentales" id="No"/>No
    <input type="hidden" name="MenFAMILIA" id="MenFAMILIA" value="0"/>
   </td>
   
  		 <%}else{ %>
   
   <td>
    <input type="radio" name="radFamMentales" id="Si" onclick="AparecerSelects('DivMenCIE'); AparecerSelects('DivMenFAMILIA')"/>Si 
    <input type="radio" name="radFamMentales" id="No" onclick="DesaparecerSelects('DivMenCIE'); DesaparecerSelects('DivMenFAMILIA')"/>No
   </td>
   
   		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoMentales>>BuscarAntecedentesFamiliaresMentales " + ex);
		}
		%>
</tr>

<tr><!-- Aqui esta el select de cardiopatias en antecedentes familiares(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select Car -->
 			<td><div id="DivCarCIE" style="display: none;">
 			<select name="CarCIE" id="CarCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'Q2%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoCardiopatias>>BuscarAntecedentesFamiliaresCardiopatias " + ex);
			}
			%>

<td></td>

<!-- Aqui esta el select de mentales en antecedentes familiares(se coloca aqui para que aparezca debajo de los radio buttons) -->		
			<!-- Aparecer Select Men -->
 			<td><div id="DivMenCIE" style="display: none;">
 			<select name="MenCIE" id="MenCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE (codigoCIE LIKE 'F0%' OR codigoCIE LIKE 'F1%' OR codigoCIE LIKE 'F7%' OR codigoCIE LIKE 'F9%')");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoMentales>>BuscarAntecedentesFamiliaresMentales " + ex);
			}
			%>
</tr>

<tr>
<td>
<div id="DivCarFAMILIA" style="display: none;">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="CarFAMILIA" id="CarFAMILIA" size="69%"/>
</div>
</td>

<td></td>

<td>
<div id="DivMenFAMILIA" style="display: none;">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="MenFAMILIA" id="MenFAMILIA" size="69%"/>
</div>
</td>
</tr>
 
</table>
</td>
</tr>


<!-- Tabla Antecedentes Personales -->
<tr>
<td id="antecedentes_personales" style="display: none">
<table style="width: 100%">
<tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">ANTECEDENTES PERSONALES</td>
    <p>&nbsp;</p> 
</tr>
</table>

<table style="width: 100%">
 									<!-- HIPERTENSION PERSONAL -->
  <tr>
    <td width="36%">1. Hipertension Arterial</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%' AND observacion='Antecedentes Personales'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
		%>
    
    <td width="15%">
      <input type="radio" name="radPerHipertension" id="Si" checked="checked"/>Si
      <input type="radio" name="radPerHipertension" id="No"/>No
    </td> 
    
    	<%}else{ %> 
    
    <td width="15%">
      <input type="radio" name="radPerHipertension" id="Si" onclick="AparecerSelects('DivHipArtPerCIE')"/>Si
      <input type="radio" name="radPerHipertension" id="No" onclick="DesaparecerSelects('DivHipArtPerCIE')"/>No
    </td> 
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoHipertension>>BuscarAntecedentesPersonalesHipertension " + ex);
		}
		%>
            
            							<!-- MENTALES PERSONAL -->        
    <td width="35%">8. Mentales</td>
    
    	<%
			rs = null; 	
 			st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>
      <input type="radio" name="radPerMentales" id="Si" checked="checked"/>Si
      <input type="radio" name="radPerMentales" id="No"/>No
    </td>
    	
    	<%}else{ %>
    <td>
      <input type="radio" name="radPerMentales" id="Si" onclick="AparecerSelects('DivMenPerCIE')"/>Si
      <input type="radio" name="radPerMentales" id="No" onclick="DesaparecerSelects('DivMenPerCIE')"/>No
    </td>
 
  		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoMentales>>BuscarAntecedentesPersonalesMentales " + ex);
		}
		%>
</tr>

  									
<tr><!-- Aqui esta el select de hipertension en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select HipArt -->
 			<td><div id="DivHipArtPerCIE" style="display: none;">
 			<select name="HipArtPerCIE" id="HipArtPerCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'I1%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}//Termina Segunda Consulta			
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoHipertension>>BuscarAntecedentesPersonalesHipertension " + ex);
			}
			%>

<td></td>

<!-- Aqui esta el select de mentales en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->		
			<!-- Aparecer Select Men -->
 			<td><div id="DivMenPerCIE" style="display: none;">
 			<select name="MenPerCIE" id="MenPerCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE (codigoCIE LIKE 'F0%' OR codigoCIE LIKE 'F1%' OR codigoCIE LIKE 'F7%' OR codigoCIE LIKE 'F9%')");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoMentales>>BuscarAntecedentesPersonalesMentales " + ex);
			}
			%>
</tr>


									<!-- DIABETES PERSONAL -->  
<tr>  
    <td>2. Diabetes</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'E1%' AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>
      <input type="radio" name="radPerDiabetes" id="Si" checked="checked"/>Si
      <input type="radio" name="radPerDiabetes" id="No"/>No
    </td> 
    
    	<%}else{ %>
    	
    <td>
      <input type="radio" name="radPerDiabetes" id="Si" onclick="AparecerSelects('DivDiaPerCIE')"/>Si
      <input type="radio" name="radPerDiabetes" id="No" onclick="DesaparecerSelects('DivDiaPerCIE')"/>No
    </td>
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesPersonalesDiabetes " + ex);
		}
		%>
    
    
    								<!-- INFECCIONES PELVICAS PERSONAL -->
    <%if(Genero.equals("Masculino")){ %>
    	<td>9. Infecciones Pelvicas (N/A)</td>  
    	<td></td>
    <%}else{ %>
    <td>9. Infecciones Pelvicas</td>
    
   	 	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND (ci.codigoCIE LIKE 'N70%' OR ci.codigoCIE LIKE 'N71%' OR ci.codigoCIE LIKE 'N72%' OR ci.codigoCIE LIKE 'N73%' OR ci.codigoCIE LIKE 'N74%' OR ci.codigoCIE LIKE 'N75%') AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>
      <input type="radio" name="radPerInfeccionesPelvicas" id="Si" checked="checked"/>Si     
      <input type="radio" name="radPerInfeccionesPelvicas" id="No"/>No
    </td>
    
    <%}else{ %>
    
    <td>
      <input type="radio" name="radPerInfeccionesPelvicas" id="Si" onclick="AparecerSelects('DivInfPelCIE')"/>Si     
      <input type="radio" name="radPerInfeccionesPelvicas" id="No" onclick="DesaparecerSelects('DivInfPelCIE')"/>No
    </td>
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoInfeccionesPelvicas>>BuscarAntecedentesPersonalesInfeccionesPelvicas " + ex);
		}
		%>
		
       
    <%} %><!-- Finaliza Validacion Genero Infecciones Pelvicas -->
     
     												
     												
<tr><!-- Aqui esta el select de diabetes en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select Dia -->
 			<td><div id="DivDiaPerCIE" style="display: none;">
 			<select name="DiaPerCIE" id="DiaPerCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'E1%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesPersonalesDiabetes " + ex);
			}
			%>

<td></td>

<!-- Aqui esta el select de infecciones pelvicas en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select InfPel -->
 			<td><div id="DivInfPelCIE" style="display: none;">
 			<select name="InfPelCIE" id="InfPelCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE (codigoCIE LIKE 'N70%' OR codigoCIE LIKE 'N71%' OR codigoCIE LIKE 'N72%' OR codigoCIE LIKE 'N73%' OR codigoCIE LIKE 'N74%' OR codigoCIE LIKE 'N75%')");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoInfeccionesPelvicas>>BuscarAntecedentesPersonalesInfeccionesPelvicas " + ex);
			}
			%>
</tr>


												<!-- CARDIOPATIAS PERSONAL -->
<tr>
    <td>3. Cardiopatias</td>
    
   	 	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'Q2%' AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>    
       <input type="radio" name="radPerCardiopatias" id="Si" checked="checked"/>Si     
       <input type="radio" name="radPerCardiopatias" id="No"/>No
    </td>
    
   	 	<%}else{ %>
    
    <td>    
       <input type="radio" name="radPerCardiopatias" id="Si" onclick="AparecerSelects('DivCarPerCIE')"/>Si     
       <input type="radio" name="radPerCardiopatias" id="No" onclick="DesaparecerSelects('DivCarPerCIE')"/>No
    </td> 
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoCardiopatias>>BuscarAntecedentesPersonalesCardiopatias " + ex);
		}
		%>  


									<!-- INFECCION CERVICAL PERSONAL -->
    <%if(Genero.equals("Masculino")){ %>
    	<td>10. Infeccion Cervical (N/A)</td>
    	<td></td>
    <%}else{ %> 	
    <td>10. Infeccion Cervical</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'N8%' AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>
       <input type="radio" name="radPerInfeccionCervical" id="Si" checked="checked"/>Si   
       <input type="radio" name="radPerInfeccionCervical" id="No"/>No
    </td>
    
    <%}else{ %>
    
    <td>
       <input type="radio" name="radPerInfeccionCervical" id="Si" onclick="AparecerSelects('DivInfCerCIE')"/>Si   
       <input type="radio" name="radPerInfeccionCervical" id="No" onclick="DesaparecerSelects('DivInfCerCIE')"/>No
    </td>
    
   		<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoInfeccionCervical>>BuscarAntecedentesPersonalesInfeccionCervical " + ex);
		}
		%>
    
    <%} %><!-- Finaliza Validacion Genero Infeccion Cervical -->
  </tr>
  
  										

<tr><!-- Aqui esta el select de cardiopatias en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select Car -->
 			<td><div id="DivCarPerCIE" style="display: none;">
 			<select name="CarPerCIE" id="CarPerCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'Q2%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoCardiopatias>>BuscarAntecedentesPersonalesCardiopatias " + ex);
			}
			%>
			
<td></td>

<!-- Aqui esta el select de infecciones cervicales en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->			
			<!-- Aparecer Select InfCer -->
 			<td><div id="DivInfCerCIE" style="display: none;">
 			<select name="InfCerCIE" id="InfCerCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'N8%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoInfeccionCervical>>BuscarAntecedentesPersonalesInfeccionCervical " + ex);
			}
			%>
</tr>
			
										<!-- HEPATOPATIAS PERSONAL -->
<tr>
    <td>4. Hepatopatias</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'K7%' AND observacion='Antecedentes Personales'");
				//System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.nombre LIKE '%HEPATITIS%'");
				if(rs.next()){	
		%>
    
    <td>
       <input type="radio" name="radPerHepatopatias" id="Si" checked="checked"/>Si   
       <input type="radio" name="radPerHepatopatias" id="No"/>No
    </td>   
    
    	<%}else{ %>
    
    <td>
       <input type="radio" name="radPerHepatopatias" id="Si" onclick="AparecerSelects('DivHepPerCIE')"/>Si   
       <input type="radio" name="radPerHepatopatias" id="No" onclick="DesaparecerSelects('DivHepPerCIE')"/>No
    </td>
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesPersonalesHepatopatias " + ex);
		}
		%>
      
      
      							<!-- FLUJO VAGINAL PERSONAL -->  
    <%if(Genero.equals("Masculino")){ %>  
     	<td>11. Flujo Vaginal (N/A)</td>
   	    <td></td> 
     <%}else{ %>         
    <td>11. Flujo Vaginal</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND (ci.codigoCIE LIKE 'N76%' OR ci.codigoCIE LIKE 'N77%') AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>    
       <input type="radio" name="radPerFlujoVaginal" id="Si" checked="checked"/>Si     
       <input type="radio" name="radPerFlujoVaginal" id="No"/>No
    </td>
    
    	<%}else{ %>
    
    <td>    
       <input type="radio" name="radPerFlujoVaginal" id="Si" onclick="AparecerSelects('DivFluVagCIE')"/>Si     
       <input type="radio" name="radPerFlujoVaginal" id="No" onclick="DesaparecerSelects('DivFluVagCIE')"/>No
    </td> 
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoFlujoVaginal>>BuscarAntecedentesPersonalesFlujoVaginal " + ex);
		}
		%>
    
    <%} %> <!-- Finaliza Validacion Genero Flujo Vaginal -->
  </tr>


<tr><!-- Aqui esta el select de hepatopatias en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->  
  			<!-- Aparecer Select Hep -->
 			<td><div id="DivHepPerCIE" style="display: none;">
 			<select name="HepPerCIE" id="HepPerCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'K7%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesPersonalesHepatopatias " + ex);
			}
			%>
			
<td></td>

<!-- Aqui esta el select de flujo vaginal en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select FluVag -->
 			<td><div id="DivFluVagCIE" style="display: none;">
 			<select name="FluVagCIE" id="FluVagCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE (codigoCIE LIKE 'N76%' OR codigoCIE LIKE 'N77%')");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoFlujoVaginal>>BuscarAntecedentesPersonalesFlujoVaginal " + ex);
			}
			%>
</tr>
  
  												<!-- NEFRITIS PERSONAL -->
<tr>    
    <td>5. Nefritis</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND (ci.codigoCIE LIKE 'N0%' OR ci.codigoCIE LIKE 'N1%') AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>   
       <input type="radio" name="radPerNefritis" id="Si" checked="checked"/>Si
       <input type="radio" name="radPerNefritis" id="No"/>No
    </td>
    
    	<%}else{ %>
    
    <td>   
       <input type="radio" name="radPerNefritis" id="Si" onclick="AparecerSelects('DivNefCIE')"/>Si
       <input type="radio" name="radPerNefritis" id="No" onclick="DesaparecerSelects('DivNefCIE')"/>No
    </td>
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoNefritis>>BuscarAntecedentesPersonalesNefritis " + ex);
		}
		%>
		
		
     		         					<!-- CIRUGIA GINECOLOGICA PERSONAL -->
    <%if(Genero.equals("Masculino")){ %> 
		<td>12. Cirug&iacute;a Ginecol&oacute;gica (N/A)</td>
		<td></td>
	<%}else{ %>		         
    <td>12. Cirug&iacute;a Ginecol&oacute;gica</td>
 
    <td>
       <input type="radio" name="radPerCirugiaGinecologica" id="Si"/>Si   
       <input type="radio" name="radPerCirugiaGinecologica" id="No"/>No
    </td>

		
    <%} %><!-- Finaliza Validacion Genero Cirugia Ginecologica -->
  </tr>
  
  										
  											
<tr><!-- Aqui esta el select de nefritis en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select Nef -->
 			<td><div id="DivNefCIE" style="display: none;">
 			<select name="NefCIE" id="NefCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE (codigoCIE LIKE 'N0%' OR codigoCIE LIKE 'N1%')");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoNefritis>>BuscarAntecedentesPersonalesNefritis " + ex);
			}
			%>
			
<td></td>

<td></td>

</tr>


												<!-- TUMORES PERSONAL -->
<tr>
    <td>6. Tumores</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>    
      <input type="radio" name="radPerTumores" id="Si" checked="checked"/>Si  
      <input type="radio" name="radPerTumores" id="No"/>No
    </td>
    
   	 	<%}else{ %>
    
    <td>    
      <input type="radio" name="radPerTumores" id="Si" onclick="AparecerSelects('DivTumPerCIE')"/>Si  
      <input type="radio" name="radPerTumores" id="No" onclick="DesaparecerSelects('DivTumPerCIE')"/>No
    </td>
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoTumores>>BuscarAntecedentesPersonalesTumores " + ex);
		}
		%>
    
    
    									<!-- CITOLOGIAS PREVIAS PERSONAL -->
    <%if(Genero.equals("Masculino")){ %>
	    <td>13. Citolog&iacute;as Previas (N/A)</td>
	    <td></td>
    <%}else{ %>
    <td>13. Citolog&iacute;as Previas</td>  

    <td>       
      <input type="radio" name="radPerCitologiasPrevias" id="Si"/>Si    
      <input type="radio" name="radPerCitologiasPrevias" id="No"/>No
    </td>
		
    <%} %><!-- Finaliza Validacion Genero Citologias Previas --> 
  </tr>
  
  
<tr><!-- Aqui esta el select de tumores en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
  			<!-- Aparecer Select Tum -->
 			<td><div id="DivTumPerCIE" style="display: none;">
 			<select name="TumPerCIE" id="TumPerCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE (codigoCIE LIKE 'C0%' OR codigoCIE LIKE 'C1%' OR codigoCIE LIKE 'C2%' OR codigoCIE LIKE 'C3%' OR codigoCIE LIKE 'C4%' OR codigoCIE LIKE 'C5%' OR codigoCIE LIKE 'C6%' OR codigoCIE LIKE 'C7%' OR codigoCIE LIKE '%C80' OR codigoCIE LIKE '%C97' OR codigoCIE LIKE 'D1%' OR codigoCIE LIKE 'D2%' OR codigoCIE LIKE 'D3%' OR codigoCIE LIKE 'D4%')");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoTumores>>BuscarAntecedentesPersonalesTumores " + ex);
			}
			%>
			
<td></td>

<td></td>

</tr>

											
											<!-- TROMBOFLEBITIS PERSONAL -->
<tr>  
    <td>7. TromboFlebitis</td>
    
    	<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I8%' AND observacion='Antecedentes Personales'");
				if(rs.next()){	
		%>
    
    <td>
      <input type="radio" name="radPerTromboflebitis" id="Si" checked="checked"/>Si
      <input type="radio" name="radPerTromboflebitis" id="No"/>No
    </td>
    
   		 <%}else{ %>
    
    <td>
      <input type="radio" name="radPerTromboflebitis" id="Si" onclick="AparecerSelects('DivTroFleCIE')"/>Si
      <input type="radio" name="radPerTromboflebitis" id="No" onclick="DesaparecerSelects('DivTroFleCIE')"/>No
    </td>
    
    	<%}//Termina Primera Consulta			
				
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoTromboFlebitis>>BuscarAntecedentesPersonalesTromboFlebitis " + ex);
		}
		%>
		
		
      
    <td>14. H&aacute;bito Fumar</td>
    <td>
      <input type="radio" name="radPerHabitoFumar" id="Si"/>Si
      <input type="radio" name="radPerHabitoFumar" id="No"/>No
    </td>
  </tr>


<tr><!-- Aqui esta el select de tromboflebitis en antecedentes personales(se coloca aqui para que aparezca debajo de los radio buttons) -->
			<!-- Aparecer Select TroFle -->
 			<td><div id="DivTroFleCIE" style="display: none;">
 			<select name="TroFleCIE" id="TroFleCIE" class="select">
    		<%		rs = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE 'I8%'");
				  //System.out.println("SELECT * FROM adm_diagnosticocola adc,cie10 ci,adm_paciente ap WHERE ci.codigo=adc.codigoCIE_fk AND ap.pac_codigo_paciente=adc.cedpac_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' AND ci.codigoCIE LIKE 'I1%'");
				if(rs.next()){	
			%>
		
			<%  while (rs.next()) {%>				
    		<option value="<%=rs.getString("codigo") %>"><%=rs.getString("nombre") %>-<%=rs.getString("codigoCIE") %></option>
 			<%} %><!-- Finaliza rs.next -->
    		 </select>
    		 </div></td>
 			<%}else{ %><!-- Finaliza segunda consulta -->

 			<%}	//Termina Segunda Consulta		
				
			rs.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoTromboFlebitis>>BuscarAntecedentesPersonalesTromboFlebitis " + ex);
			}
			%>
</tr> 
 
 
		<%if(Genero.equals("Masculino")){ %>
<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonAntecedentes" value="Guardar Antecedentes" onclick="GuardarAntecedentesMasculino()"/></td>
</tr>    
		<%}else{ %>
<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonAntecedentes" value="Guardar Antecedentes" onclick="GuardarAntecedentesFemenino()"/></td>
</tr>    
		<%} %>
    
</table>
</td>
</tr>
</table>  
 



<h3>HISTORIAS</h3>

<%if(Genero.equals("Femenino")){ %>
<!-- Tabla Embarazos Anteriores -->
<table style="width: 100%">
<tr>
  <td id="historia_embarazos" style="display: none"> 

  <table style="width: 100%">
  <tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">HISTORIA DE EMBARAZOS ANTERIORES</td>
    
    <p>&nbsp;</p>
  </tr>
  </table>


<table style="width: 100%">
 
<tr>
   <td colspan="4">¿Usted ha tenido algún embarazo antes de la consulta?
     &nbsp;
     <select name="EmbarazoAntes" id="EmbarazoAntes" onchange="AsignarNAEmbarazos()">
     <option value="Seleccione" selected="selected">Seleccione</option>
     <option value="Si">Si</option>
     <option value="No">No</option>
     </select>
   </td>
</tr>

<tr>
<td><br><br>Terminaci&oacute;n del Embarazo
<br>
	<input type="text" name="terminaEmbarazo" id="datepickerTerminacionEmbarazo" disabled="disabled">
</td>

<td><br><br>Meses de Gestaci&oacute;n
<br>
	<input type="text" name="mesesGestacion" id="mesesGestacion" onkeypress="javascript:return validarNro(event)" maxlength="1" disabled="disabled">
</td>

<td><br><br>Tipo de Parto
<br>
<select name="tipoParto" id="tipoParto" disabled="disabled">
	<option value="Seleccione" selected="selected">Seleccione</option>
	<option value="Vaginal">Vaginal</option>
	<option value="Cesarea">Cesarea</option>
</select>
</td>

<td><br><br>Aborto
<br>
<select name="aborto" id="aborto" disabled="disabled">
	<option value="Seleccione" selected="selected">Seleccione</option>
	<option value="Si">Si</option>
	<option value="No">No</option>
</select>
</td>
</tr>

</table>

<br>Escriba otros antecedentes
<table>
<tr>
<td>
<textarea name="otrosAntecedentes" id="otrosAntecedentes" rows="5" cols="130" onkeypress="javascript:return soloLetras(event)" disabled="disabled"></textarea>
</td>
</tr>  
</table>  
 
</td>
</tr>


<!-- Tabla Historias Menstruales -->
<tr>
<td id="historias_menstruales" style="display: none">
<table style="width: 100%">
<tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">HISTORIAS MENSTRUALES</td>
    <p>&nbsp;</p>
</tr>
</table>


<table style="width: 100%">

<tr>
<td><br>Ciclos Regulares</td>
<td><br>Ultima Menstruacion</td>
<td><br>Trastornos Menstruales</td>
</tr>

<tr>
<td>
    <input type="radio" name="radCiclosRegulares" id="Si">Si
<br><input type="radio" name="radCiclosRegulares" id="No">No
</td>

<td>
<input type="text" name="ultimaMenstruacion" id="datepickerUltimaMenstruacion">
</td>

<td>
    <input type="radio" name="radTrasMenstruales" id="siTrasMenstruales" onclick="clicksiTrasMenstruales()" value="Si">Si &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    Tipo&nbsp;<input type="text" name="tipo" id="tipoTrasMenstruales" disabled="disabled" onkeypress="javascript:return soloLetras(event)">
<br><input type="radio" name="radTrasMenstruales" id="noTrasMenstruales" onclick="tipo.disabled=true" value="No">No
</td>
</tr>

</table>

</td>
</tr>

  
<!-- Tabla Historia Conceptual -->
<tr>
  <td id="historia_conceptual" style="display: none">
<table style="width: 100%">
 <tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">HISTORIA CONCEPTUAL</td>
  	<p>&nbsp;</p>
  </tr>
 </table>
  
<table style="width: 100%">  
<tr>
   <td colspan="4">Usted ha usado alg&uacute;n metodo anticonceptivo antes de la consulta
     &nbsp;
     <input type="radio" name="radMetodoAnticonceptivo" id="Si" onClick="clicksiMetodosAntesConsulta()">Si
     <input type="radio" name="radMetodoAnticonceptivo" id="No" 
			onClick="txt_metUtilizado.disabled=true;txt_desde.disabled=true;txt_hasta.disabled=true;preescrito.disabled=true;txt_preescrito.disabled=true;txt_problemas.disabled=true">No
   </td>
</tr>

<tr>
   <td rowspan="2"><br>M&eacute;todo Utilizado</td>
   <td colspan="2"><br>Tiempo de Utilizaci&oacute;n</td>
   <td rowspan="2"><br>Prescrito en</td>
   <td rowspan="2"><br>Problemas</td>
</tr>
  
<tr>
   <td>Desde:</td>
   <td>Hasta:</td>
</tr>
  
<tr>
   <td><input type="text" name="txt_metUtilizado" id="txt_metUtilizado" size="10" disabled="disabled" onkeypress="javascript:return soloLetras(event)"/></td>
   <td><input type="text" name="txt_desde" id="txt_desde" disabled="disabled" size="10"/></td>
   <td><input type="text" name="txt_hasta" id="txt_hasta" disabled="disabled" size="10"/></td>
   <td><select name="preescrito" id="preescrito" onchange="selectEntidad()" disabled="disabled">
		<option value="Seleccione" selected="selected">Seleccione</option>
		<option value="Entidad">Entidad</option>
		<option value="Profamiia">Profamilia</option>
		<option value="Otro">Otro</option>
		<option value="Ninguno" >Ninguno</option>
	   </select>
	   &nbsp;
       <input type="text" name="txt_preescrito" id="txt_preescrito" size="10" disabled="disabled" onkeypress="javascript:return soloLetras(event)"/>
   </td>
    
   <td><input type="text" name="txt_problemas" id="txt_problemas" size="25" disabled="disabled" onkeypress="javascript:return soloLetras(event)"/></td>
</tr>
 

</table>

</td>
</tr>

<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonHistorias" value="Guardar Historias" onclick="GuardarHistorias()"/></td>
</tr>

</table>

<%}else{ %>
	<table style="width: 100%">
	<tr>
	<td>ESTA SECCION NO APLICA PARA GENERO MASCULINO</td>
	<td id="historia_embarazos" style="display: none"></td>
	<td id="historias_menstruales" style="display: none"></td>
	<td id="historia_conceptual" style="display: none"></td>
	</tr>
	</table>
<%} %>


</div> <!-- Finaliza div Accordion General -->
</div> <!-- Finaliza Primera Pestaña -->  



											<!-- Segunda Pestaña -->
<div id="formulario_planificacion_familiar">  

	<div id="accordion2">
	<h3>RIESGO REPRODUCTIVO</h3>

<%if(Genero.equals("Femenino")){ %>
<!-- Tabla Planificacion Familiar --> 
<table style="width: 100%">
<tr>
 <td id="planificacion_familiar" style="display: none">
 <table style="width:100%">
<tr>
  <td id="cabecera2" class="style11" colspan="2" align="center"><p>PLANIFICACION FAMILIAR INDICE DE RIESGO REPRODUCTIVO</p>
  <p>ANEXO 1</p></td>
</tr>
</table>

<table style="width:100%">
<form name=general>

<br>

<tr>
<td colspan="1"><label for="txt_fecha_visita">Fecha de la Visita:</label>
<br>
<input type="text" name="txt_fecha_visita" id="datepickerFechaVisita" size="20"/></td>

<td colspan="1"><label>Fecha &Uacute;ltima Menstruaci&oacute;n:</label>
<br>
<input type="text" name="txt_fecha_ult_menstruacion" id="datepickerFechaUltMenstruacion" size="20"/></td>

</tr>

<p></p>

<tr>
<td align="left"><br>Edad de la Mujer:
<br>
<select name="edadMujer" id="edadMujer">
<option selected="selected">Seleccione</option>
<option value="2">17 a&ntilde;os o menos</option>
<option value="0">18 o 34 a&ntilde;os</option>
<option value="2">35 a&ntilde;os o m&aacute;s</option>
</select></td>

<td height="31" align="left"><br>Pariedad: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>
<select name="pariedad" id="pariedad">
<option selected="selected">Seleccione</option>
<option value="0">Nilipara</option>
<option value="0">1-3 partos</option>
<option value="0">4 o m&aacute;s partos</option>
</select></td>
</tr>

<tr>
<td align="left"><br>Socio Econ&oacute;mico:
<br>
<select name="socioEconomico" id="socioEconomico">
<option selected="selected">Seleccione</option>
<option value="2">Tugurio</option>
<option value="2">Ni&ntilde;os de 2 a&ntilde;os desnutridos</option>
<option value="2">Muerte de ni&ntilde;os menores de 2 a&ntilde;os</option>
<option value="0">Ninguna de las Anteriores</option>
</select></td>

<td align="left"><br>Intervalo Embarazos:
<br>
<select name="intervaloEmbarazo" id="intervaloEmbarazo">
<option selected="selected">Seleccione</option>
<option value="4">Menores de 12 a&ntilde;os</option>
<option value="1">1 a 2 a&ntilde;os</option>
<option value="0">M&aacute;s de 2 a&ntilde;os</option>
<option value="0">Ninguna de las Anteriores</option>
</select></td>
</tr>


<tr>
<th align="left"><br>Antecedentes:</th>

<tr>
<td>
Aborto</br>
  <input type=radio value="0" name="radAborto" id="0"> 0<br/>
  <input type=radio value="1" name="radAborto" id="1 o 2"> 1 o 2<br/>
  <input type=radio value="1" name="radAborto" id="3 o mas"> 3 o m&aacute;s<br/>
</td>

<td>
Cesarea</br>
  <input type="radio" value="0" name="radCesarea" id="0"> 0<br/>
  <input type="radio" value="1" name="radCesarea" id="1 o 2"> 1 o 2<br/>
  <input type="radio" value="1" name="radCesarea" id="3 o mas"> 3 o m&aacute;s<br/>
</td>
</tr>

<tr>
<td>
<br>Mortinato</br>
  <input type="radio" value="0" name="radMortinato" id="0">0 <br/>
  <input type="radio" value="1" name="radMortinato" id="1 o 2"> 1 o 2<br/>
  <input type="radio" value="1" name="radMortinato" id="3 o mas"> 3 o m&aacute;s<br/>
</td>

<td>
<br>Prematuro</br>
  <input type="radio" value="0" name="radPrematuro" id="0"> 0<br/>
  <input type="radio" value="1" name="radPrematuro" id="1 o 2"> 1 o 2<br/>
  <input type="radio" value="1" name="radPrematuro" id="3 o mas"> 3 o m&aacute;s<br/>
</td>
</tr>
</tr>


<tr>
<td align="left"><br>Patologia Actual:
<select name="patologiaActual" id="patologiaActual" onchange="activarelementoFormulario('BotonCalcular')">
<option selected="selected">Seleccione</option>
<option value="4">Anemia severa (Menos de 7 GMS de Hb)</option>
<option value="4">Enfermedad Renal</option>
<option value="4">Hipertension</option>
<option value="4">Cardiopatias</option>
<option value="0">Ninguna de las Anteriores</option>
</select></td>
</tr>

<tr><td><br>
<input type="button" id="BotonCalcular" value="Calcular" onclick="Resultado(this)" disabled="disabled"/>
</td></tr>

</form>
</table>


<!-- Tabla Riesgo Reproductivo -->
<tr>
<td id="riesgo_reproductivo" style="display: none">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">RIESGO REPRODUCTIVO</td>
<p>&nbsp;</p>
</tr>
</table>

<table style="width: 100%">
<tr>
<td><br>Riesgo Reproductivo
<input type="text" name="riesgoReproductivo" id="riesgoReproductivo"/>
</td>
</tr>

</table>

<tr>
<td><p>&nbsp;</p><br><input type="submit" id="BotonRiesgoReproductivo" value="Guardar Riesgo Reproductivo" onclick="GuardarRiesgoReproductivo()"/></td>
</tr>

</td>
</tr>

</td>
</tr>
</table>


<%}else{ %>
	<table style="width: 100%">
	<tr>
	<td>ESTA SECCION NO APLICA PARA GENERO MASCULINO</td>
	<td id="planificacion_familiar" style="display: none"></td>
	<td id="riesgo_reproductivo" style="display: none"></td>
	</tr>
	</table>
<%} %>


<h3>EX&Aacute;MENES</h3>
<%if (Genero.equals("Femenino")){ %>
<!-- Tabla Examen Fisico -->
<table style="width: 100%">
<tr>
 <td id="examen_fisico" style="display: none">
 <table style="width: 100%">
<tr>
   <td id="cabecera2" class="style11" colspan="2" align="center">EXAMEN FISICO</td>
</tr>
</table>

<table style="width: 100%">
<tr>
    <td align="left">Mamas Normales</td>
  	<td>
      <input type="radio" name="radMamasNormales" id="Si"/>Si
      <input type="radio" name="radMamasNormales" id="No"/>No 
  	</td> 
    
    <td align="left">Utero y Anexos Normales</td>
    <td>
      <input type="radio" name="radUteAneNormales" id="Si"/>Si
      <input type="radio" name="radUteAneNormales" id="No"/>No
    </td>
</tr>

<tr>
    <td align="left">Signos De Embarazo</td>
    <td>
      <input type="radio" name="radSigEmbarazo" id="Si"/>Si
      <input type="radio" name="radSigEmbarazo" id="No"/>No
    </td>
 
  <td align="left">Miembros Inferiores: Varices</td>
  <td>
      <input type="radio" name="radMiemInfVarices" id="Si"/>Si
      <input type="radio" name="radMiemInfVarices" id="No"/>No
  </td>
</tr>

<tr>
    <td align="left">Cervix Normal</td>
    <td>
      <input type="radio" name="radCerNormal" id="Si"/>Si
      <input type="radio" name="radCerNormal" id="No"/>No
    </td>
 
  <td align="left">Edemas</td>
  <td>
      <input type="radio" name="radEdemas" id="Si"/>Si
      <input type="radio" name="radEdemas" id="No"/>No
  </td>
</tr>

<tr>
  <td align="left">Peso En KG</td>
  <td>
      <input type="text" name="peso" id="peso" onkeypress="javascript:return validarNro(event)"/>
  </td>
  
  <td align="left">Tension Arterial</td>
  <td>
      <input type="text" name="tenArterial" id="tenArterial"/>
  </td>
</tr>

<tr>
  <td align="left">N&uacute;mero de Semanas de Embarazo</td>
  <td>

<select name="numSemanas" id="numSemanas">
  <%
	for (int x=0; x<=42; x++){
		out.println("<option value="+x+">"+x+"</option>");
	};
  %>
</select>
      <!-- <input type="text" name="numSemanas" id="numSemanas" onkeypress="javascript:return validarNro1(event)" maxlength="2"/> -->
  </td>
      
  <td align="left">Otros Hallazgos(Cuales)</td>
  
  <td>
      <input type="text" name="hallazgos" id="hallazgos" onkeypress="javascript:return soloLetras(event)"/>
  </td>
</tr>
</table>

 
<br>
 Observaciones:<br> 
<textarea name="observaciones" id="observaciones" rows="8" cols="50" style="width:100%"></textarea>

</td>
</tr>


<!-- Tabla Examenes Practicados -->
<tr>
 <td id="examenes_practicados" style="display: none">
 <table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">EXAMENES PRACTICADOS</td>
<p>&nbsp;</p>
</tr>
</table>

<table style="width: 100%">
<tr>
<td align="left">Prueba De Embarazo</td>
<td>
<form>
<input type="radio" name="radPruEmbarazo" id="siPruEmbarazo" onclick="clicksiPruebaEmbarazo()"/>Si
<input type="radio" name="radPruEmbarazo" id="noPruEmbarazo" onclick="resPruEmbarazo.disabled=true"/>No
</td>

<td align="left">Resultados</td>
<td>
<input type="text" name="resPruEmbarazo" id="resPruEmbarazo" disabled="disabled"/>
</form>
</td>
</tr>

<tr>
<td align="left">Citologia Cervico Vaginal</td>
<td>
<form>
<input type="radio" name="radCitCerVaginal" id="siCitCerVaginal" onclick="clicksiCitologiaCervicoVaginal()"/>Si
<input type="radio" name="radCitCerVaginal" id="noCitCerVaginal" onclick="resCitCerVaginal.disabled=true"/>No
</td>

<td align="left">Resultados</td>
<td>
<input type="text" name="resCitCerVaginal" id="resCitCerVaginal" disabled="disabled"/>
</form>
</td>
</tr>

<tr>
<td align="left">Frotis Vaginal</td>
<td>
<form>
<input type="radio" name="radFroVaginal" id="siFroVaginal" onclick="clicksiFrotisVaginal()"/>Si
<input type="radio" name="radFroVaginal" id="noFroVaginal" onclick="resFroVaginal.disabled=true"/>No
</td>

<td align="left">Resultados</td>
<td>
<input type="text" name="resFroVaginal" id="resFroVaginal" disabled="disabled"/>
</form>
</td>
</tr>

<tr>
<td align="left">Otros</td>
<td>
<input type="text" name="otrosExaPracticados" id="otrosExaPracticados"/>
</td>

</tr>

<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonExamenes" value="Guardar Examenes" onclick="GuardarExamenes()"/></td>
</tr>

</table>
</td>
</tr>

</table>

<%}else{ %>
	<table style="width: 100%">
	<tr>
	<td>ESTA SECCION NO APLICA PARA GENERO MASCULINO</td>
	<td id="examen_fisico" style="display: none"></td>
	<td id="examenes_practicados" style="display: none"></td>
	</tr>
	</table>
<%} %>


<h3>M&Eacute;TODOS</h3>

<%if(Genero.equals("Femenino")){ %>
<!-- Tabla Metodo Reproductivo -->
<table style="width: 100%">

<tr>
<td id="metodo_reproductivo" style="display: none">
<table style="width: 100%">
<tr>
   <td id="cabecera2" class="style11" colspan="2" align="center">METODO REPRODUCTIVO</td>
</tr>
</table>

<table style="width: 100%">

<tr>
<td width="10%">Pildoras</td>
<td>
<select name="pildoras" id="pildoras" onchange="selectPildoras()">
		<option value="Seleccione" selected="selected">Seleccione</option>
		<option value="Si">Si</option>
		<option value="No">No</option>
</select>
</td>

<td>
Cu&aacute;les:<input type="text" name="cualPildoras" id="cualPildoras" disabled="disabled" onkeypress="javascript:return soloLetras(event)">
</td>

<td>
Fecha:<input type="text" name="fechaPildoras" id="datepickerPildoras" disabled="disabled">
</td>
</tr>


<tr>
<td width="10%">DIU</td>
<td> 
<select name="diu" id="diu" onchange="selectDIU()">
		<option value="Seleccione" selected="selected">Seleccione</option>
		<option value="Si">Si</option>
		<option value="No">No</option>
</select>
</td>

<td>
Cu&aacute;les:<input type="text" name="cualDIU" id="cualDIU" disabled="disabled" onkeypress="javascript:return soloLetras(event)">
</td>

<td>
Fecha:<input type="text" name="fechaDIU" id="datepickerDIU" disabled="disabled">
</td>
</tr>


<tr>
<td width="10%">Inyectables</td>
<td>  
<select name="inyectables" id="inyectables" onchange="selectInyectables()">
		<option value="Seleccione" selected="selected">Seleccione</option>
		<option value="Si">Si</option>
		<option value="No">No</option>
</select>
</td>

<td>
Cu&aacute;les:<input type="text" name="cualInyectables" id="cualInyectables" disabled="disabled" onkeypress="javascript:return soloLetras(event)">
</td>

<td>
Fecha:<input type="text" name="fechaInyectables" id="datepickerInyectables" disabled="disabled">
</td>
</tr>



<tr>
<td width="10%">Otros</td>
<td>  
<select name="metOtros" id="metOtros" onchange="selectOtros()">
		<option value="Seleccione" selected="selected">Seleccione</option>
		<option value="Si">Si</option>
		<option value="No">No</option>
</select>
</td>

<td>
Cu&aacute;les:<input type="text" name="cualOtros" id="cualOtros" disabled="disabled" onkeypress="javascript:return soloLetras(event)">
</td>

<td>
Fecha:<input type="text" name="fechaOtros" id="datepickerOtros" disabled="disabled">
</td>
</tr>



<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonMetodos" value="Guardar Metodos" onclick="GuardarMetodos()"/></td>
</tr>
</table>

</td>
</tr>
</table>

<%}else{ %>
	<table style="width: 100%">
	<tr>
	<td>ESTA SECCION NO APLICA PARA GENERO MASCULINO</td>
	<td id="metodo_reproductivo" style="display: none"></td>
	</tr>
	</table>
<%} %>

</div> <!-- Finaliza div Accordion2 -->
</div> <!-- Finaliza div Segunda Pestaña -->
  


<div id="formulario_consultas"> <!-- Inicia Tercera Pestaña -->
	
<div id="accordion3">
<h3>CONTROLES</h3>

<!-- Tabla Controles De Enfermeria -->
<table style="width: 100%">  

<tr> 
<td id="controlesEnfermeria" style="display: none">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">CONSULTAS - CONTROLES DE ENFERMERIA</td>

</tr>
</table>

<table style="width: 100%">
<tr>
<td>
Fecha
<input type="text" name="fecControles" id="datepickerFechaControles">
</td>

<td>
Peso Kg.
<input type="text" name="pesoControles" id="pesoControles">
</td>

<td>
Tension Arterial
<input type="text" name="tenArtControles" id="tenArtControles">
</td>
</tr>

<tr>
<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Satisfacci&oacute;n con el M&eacute;todo (N/A)</label><br>
<%}else{ %>	
<label>Satisfacci&oacute;n con el M&eacute;todo</label><br>
<input type="radio" name="radSatisfaccionMetodoC" id="Si">Si
<input type="radio" name="radSatisfaccionMetodoC" id="No">No
</td>
<%} %>

<td><br>		
<%if(Genero.equals("Masculino")){ %>
	<label>Trastornos Menstruales (N/A)</label><br>
<%}else{ %>		
<label>Trastornos Menstruales</label><br>
<input type="radio" name="radTrastornosMenstrualesC" id="Si">Si
<input type="radio" name="radTrastornosMenstrualesC" id="No">No
</td>
<%} %>

<td><br>	
<%if(Genero.equals("Masculino")){ %>
	<label>Cambios de Comportamiento (N/A)</label><br>
<%}else{ %>		
<label>Cambios de Comportamiento</label><br>
<input type="radio" name="radCambioComportamientoC" id="Si">Si
<input type="radio" name="radCambioComportamientoC" id="No">No
</td>
<%} %>
</tr>

<tr>
<td><br>
<label>Cefaleas</label><br>
<input type="radio" name="radCefaleasC" id="Si">Si
<input type="radio" name="radCefaleasC" id="No">No
</td>

<td><br>
<label>Mareos</label><br>
<input type="radio" name="radMareosC" id="Si">Si
<input type="radio" name="radMareosC" id="No">No
</td>

<td><br>
<label>Manchas en la Piel</label><br>
<input type="radio" name="radManchasPielC" id="Si">Si
<input type="radio" name="radManchasPielC" id="No">No
</td>
</tr>

<tr>
<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Molestias en la Mamas (N/A)</label><br>
<%}else{ %>	
<label>Molestias en la Mamas</label><br>
<input type="radio" name="radMolestiasMamaC" id="Si">Si
<input type="radio" name="radMolestiasMamaC" id="No">No
<%} %>

<td><br>
<label>Edemas</label><br>
<input type="radio" name="radEdemasC" id="Si">Si
<input type="radio" name="radEdemasC" id="No">No
</td>

<td><br>
<label>Varices</label><br>
<input type="radio" name="radVaricesC" id="Si">Si
<input type="radio" name="radVaricesC" id="No">No
</td>
</tr>

<tr>
<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Expulsi&oacute;n del Dispositivo (N/A)</label><br>
<%}else{ %>
<label>Expulsi&oacute;n del Dispositivo</label><br>
<input type="radio" name="radExpulsionDispositivoC" id="Si">Si
<input type="radio" name="radExpulsionDispositivoC" id="No">No
<%} %>

<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Dolor Bajo Vientre (N/A)</label><br>
<%}else{ %>
<label>Dolor Bajo Vientre</label><br>
<input type="radio" name="radDolorBajoVientreC" id="Si">Si
<input type="radio" name="radDolorBajoVientreC" id="No">No
<%} %>
</td>

<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Flujo Vaginal (N/A)</label><br>
<%}else{ %>
<label>Flujo Vaginal</label><br>
<input type="radio" name="radFlujoVaginalC" id="Si">Si
<input type="radio" name="radFlujoVaginalC" id="No">No
<%} %>
</td>
</tr>

<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonControles" value="Guardar Controles" onclick="GuardarControles()"/></td>
</tr>

</table>
	
</td>
</tr>
</table>


<h3>CONDUCTAS</h3>
<!-- Tabla Conductas A Seguir -->
<table style="width: 100%">
<tr>
<td id="conductasSeguir" style="display: none">
<table style="width: 100%">	
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">CONDUCTAS A SEGUIR</td>
<p>&nbsp;</p>
</tr>
</table>

<table style="width: 100%">

<tr><td><strong><b><br>EDUCACION</b></strong></td></tr>

<tr>
<td><br>
<label>Estilo de Vida Saludable</label><br>
<input type="radio" name="radEstiloVidaSaludableC" id="Si">Si
<input type="radio" name="radEstiloVidaSaludableC" id="No">No
</td>

<td><br>
<label>Nutrici&oacute;n y Alimentaci&oacute;n</label><br>
<input type="radio" name="radNutricionAlimentacionC" id="Si">Si
<input type="radio" name="radNutricionAlimentacionC" id="No">No
</td>

<td><br>
<label>Prevenci&oacute;n en Enfermedades de Transmisi&oacute;n Sexual</label><br>
<input type="radio" name="radPrevencionEnfermedadesTransmisionSexualC" id="Si">Si
<input type="radio" name="radPrevencionEnfermedadesTransmisionSexualC" id="No">No
</td>
</tr>

<tr>
<td><br>
<label>Consejer&iacute;a de los M&eacute;todos de Planificaci&oacute;n Familiar</label><br>
<input type="radio" name="radConsejeriaMetodosC" id="Si">Si
<input type="radio" name="radConsejeriaMetodosC" id="No">No
</td>

<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Signos y Sintomas Alarma Embarazo (N/A)</label><br>
<%}else{ %>
<label>Signos y Sintomas Alarma Embarazo</label><br>
<input type="radio" name="radSignosSintomasAlarmaC" id="Si">Si
<input type="radio" name="radSignosSintomasAlarmaC" id="No">No
</td>
<%} %>

<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Prevenci&oacute;n para el Cancer de Cuello Uterino (N/A)</label><br>
<%}else{ %>
<label>Prevenci&oacute;n para el Cancer de Cuello Uterino</label><br>
<input type="radio" name="radPrevencionCancerCuelloUterinoC" id="Si">Si
<input type="radio" name="radPrevencionCancerCuelloUterinoC" id="No">No
</td>
<%} %>
</tr>

<tr>
<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>El Autoexamen de Mama (N/A)</label><br>
<%}else{ %>
<label>El Autoexamen de Mama</label><br>
<input type="radio" name="radAutoexamenMamaC" id="Si">Si
<input type="radio" name="radAutoexamenMamaC" id="No">No
</td>
<%} %>

<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Lactancia Materna(N/A)</label><br>
<%}else{ %>
<label>Lactancia Materna</label><br>
<input type="radio" name="radLactanciaMaternaC" id="Si">Si
<input type="radio" name="radLactanciaMaternaC" id="No">No
</td>
<%} %>

<td><br>
<label>Prevencion para la Diabetes, Hipertension, Osteoporosis</label><br>
<input type="radio" name="radPrevenirDiaHipOstC" id="Si">Si
<input type="radio" name="radPrevenirDiaHipOstC" id="No">No
</td>
</tr>

<tr>
<td><br>
<label>Abuso Sexual</label><br>
<input type="radio" name="radAbusoSexualC" id="Si">Si
<input type="radio" name="radAbusoSexualC" id="No">No
</td>

<td><br>
<label>Violencia Intrafamiliar</label><br>
<input type="radio" name="radViolenciaIntrafamiliarC" id="Si">Si
<input type="radio" name="radViolenciaIntrafamiliarC" id="No">No
</td>

<td><br>
<label>Consecuencias del Consumo Abusivo del Alcohol, Cigarrillo</label><br>
<input type="radio" name="radConsecuenciasAlcCigC" id="Si">Si
<input type="radio" name="radConsecuenciasAlcCigC" id="No">No
</td>
</tr>

<tr>
<td><br>
<label>Complicaciones del Consumo de Drogas Psicoactivas</label><br>
<input type="radio" name="radComplicacionesDrogasC" id="Si">Si
<input type="radio" name="radComplicacionesDrogasC" id="No">No
</td>

<td><br>
<label>Autoestima: Autocuidado</label><br>
<input type="radio" name="radAutoestimaC" id="Si">Si
<input type="radio" name="radAutoestimaC" id="No">No
</td>

<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Cuidados del Reci&eacute;n Nacido (N/A)</label><br>
<%}else{ %>
<label>Cuidados del Reci&eacute;n Nacido</label><br>
<input type="radio" name="radCuidadosRecienNacidoC" id="Si">Si
<input type="radio" name="radCuidadosRecienNacidoC" id="No">No
</td>
<%} %>
</tr>

<tr>
<td><br>
<label>Derechos Reproductivos</label><br>
<input type="radio" name="radDerechosReproductivosC" id="Si">Si
<input type="radio" name="radDerechosReproductivosC" id="No">No
</td>

<td><br>
<label>Salud Sexual y Reproductiva</label><br>
<input type="radio" name="radSaludSexualReproductivaC" id="Si">Si
<input type="radio" name="radSaludSexualReproductivaC" id="No">No
</td>
</tr>

<tr><td><strong><b><br><br><br>REMISION</b></strong></td></tr>

<tr>
<td><br>
<label>Otro Programa de P y P de acuerdo a la Edad</label><br>
<input type="radio" name="radOtroProgamaPYPC" id="Si">Si
<input type="radio" name="radOtroProgamaPYPC" id="No">No
</td>

<td><br>
<label>Valoraci&oacute;n por Psicolog&iacute;a</label><br>
<input type="radio" name="radValoracionPsicologiaC" id="Si">Si
<input type="radio" name="radValoracionPsicologiaC" id="No">No
</td>

<td><br>
<label>Valoraci&oacute;n por Nutrici&oacute;n</label><br>
<input type="radio" name="radValoracionNutricionC" id="Si">Si
<input type="radio" name="radValoracionNutricionC" id="No">No
</td>
</tr>

<tr>
<td><br>
<%if(Genero.equals("Masculino")){ %>
	<label>Valoraci&oacute;n por Ginecolog&iacute;a(N/A)</label><br>
<%}else{ %>
<label>Valoraci&oacute;n por Ginecolog&iacute;a</label><br>
<input type="radio" name="radValoracionGinecologiaC" id="Si">Si
<input type="radio" name="radValoracionGinecologiaC" id="No">No
</td>
<%} %>

<td><br>
<label>Valoraci&oacute;n por Urolog&iacute;a</label><br>
<input type="radio" name="radValoracionUrologiaC" id="Si">Si
<input type="radio" name="radValoracionUrologiaC" id="No">No
</td>

<td><br>
<label>Consulta M&eacute;dica por Enfermedad</label><br>
<input type="radio" name="radConsultaMedicaC" id="Si">Si
<input type="radio" name="radConsultaMedicaC" id="No">No
</td>
</tr>

<tr>
<td><br>
Otra especialidad
<input type="text" name="otraEspecialidadControles" id="otraEspecialidadControles">
</table>

<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonConductas" value="Guardar Conductas" onclick="GuardarConductas()"/></td>
</tr>

</td>
</tr>
</table>


<h3>INDICACIONES</h3>
<!-- Tabla Observaciones Especiales -->
<table style="width: 100%">
<tr>
<td id="observacionesEspeciales" style="display: none">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">OBSERVACIONES ESPECIALES</td>
<p>&nbsp;</p>
</tr>
</table>

<br>
Coloque las observaciones especiales
<table>
<tr>
<td>
<textarea rows="5" cols="130" name="observacionEspecial" id="observacionEspecial"></textarea>
</td>
</tr>
</table>

</td>
</tr>


<!-- Tabla Evolucion Clinica -->
<tr>
<td id="evolucionClinica" style="display: none">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">EVOLUCION CLINICA</td>
<p>&nbsp;</p>
</tr>
</table>

<br>
Coloque la evoluci&oacute;n cl&iacute;nica
<table>
<tr>
<td>
<textarea rows="5" cols="130" name="evolucionesClinicas" id="evolucionesClinicas"></textarea>
</td>
</tr>
</table>

<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonIndicaciones" value="Guardar Indicaciones" onclick="GuardarIndicaciones()"/></td>
</tr>

</td>
</tr>

	<tr>
		<td colspan="2">
	      <div align="center"><br>
            <input id="guardar" type="button" value="Generar Informe" onclick="GenerarReporte()"> 
			<input id="anular" 	type="button" value="Anular Informe"  onclick="AnularInforme()">
	      </div>
		</td>
	</tr>
    
</table> <!-- Fin Tabla Observaciones Especiales -->

</div> <!-- Finaliza Tercer Acordeon -->
</div> <!-- Finaliza Tercera Pestaña -->


</div> <!-- Finaliza div tabs -->
</td>
</tr>

</table>
     
	<%}%> 
	
</body>
</html>