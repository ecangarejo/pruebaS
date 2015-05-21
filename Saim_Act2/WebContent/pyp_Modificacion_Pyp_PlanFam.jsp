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
<script lang="javascript" type="text/javascript" src="pyp_Modificacion_Pyp_PlanFam.js"></script>

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
function Verificar(e)
{
	
	var minavegador = new Browser();
	if (minavegador.name == 'firefox'){
	    if(e) 
		document.onkeypress = function(){return true;} ;
		var evt = e?e:event; 
		if(evt.keyCode==116) 
		{ 
		if(e) 
		document.onkeypress = function(){return false;} ;
		else 
		{ 
		evt.keyCode = 0; 
		evt.returnValue = false; 
		} 
		} 
	}
}
</script>

</head>

<body>
	
	<%
		String codigou =(String)session.getAttribute("codusuario");
	%>
	<%
		Conexion con=new Conexion();
		ResultSet rs;
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
	         <td id="cabecera2" class="style11" align="center">
		     MODIFICAR INFORME PYP
			 </td>
	</tr>
	

	
	<%	
	String CodInfPYPPlanFam=request.getParameter("CodInfPYPPlanFam");
    String Codpaciente = request.getParameter("codPaciente");
    //String CodUsuario = request.getParameter("codusuario");
	Vector <String> datosPaciente = new Vector<String>();
%>	

<tr>
<td id="encuesta_pyp" width="100%" class="style11">
<input type="hidden" id="CodPacienteMod" name="CodPacienteMod" value="<%=Codpaciente%>" />
<input type="hidden" id="CodUsuarioMod" name="CodUsuarioMod" value="<%=codigou%>" />
<div id="tabs"> <!-- Inicia div tabs General -->
  <ul>
    <li><a href="#formulario_informacion_usuario">Informaci&oacute;n Usuario</a></li>
    <li><a href="#formulario_planificacion_familiar">Planificaci&oacute;n Familiar</a></li>
    <li><a href="#formulario_consultas">Consultas</a></li> 
  </ul>
  
  <div id="formulario_informacion_usuario"> <!-- Primera Pestaña --> 
  	<div id="accordion"> <!-- Inicia div acordion General-->
  	  <h3>DATOS PERSONALES</h3>	 	
  		<table style="width: 100%">
		 <tr>
	      <td >
	  <%      rs = null;
	 st = null;
	
	 try {
	    con=new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT apac.*,"+
				" CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido),"+
				" (YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))- (RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento,5)) AS 'EDAD',"+
				" ent.nombre_entidad"+
				" FROM "+
				"adm_paciente apac,"+
				"adm_convenio con,"+
				"adm_entidad ent"+
				" WHERE apac.pac_codigo_paciente= "+Codpaciente+ 
				" AND apac.conv_numero_contrato_fk = con.conv_numero_contrato"+
				" AND con.ent_nit_contratante_fk = ent.ent_nit");
		if(rs.next()){%>
	<tr>
	<td>
	<table style="width: 100%">
		<tr>
			<td><div align='center' class='style11' id='cabecera2' >DATOS DEL PACIENTE </div></td>
		</tr>
	</table>
	
	<table> 
		<tr>
			<td width='13%'>Nombre del Paciente: <input type='hidden' name="numDocumento" id="numDocumento" value="<%=rs.getString("numero_documento")%>" /></td>
			<td width='28%' align='center'><%=rs.getString("nombre")+" "+rs.getString("primer_apellido")+" "+rs.getString("segundo_apellido")%></td>							      					
            <td width='4%'>Genero: </td>
		    <td width='28%' align='center'><%=rs.getString("genero")%></td>
			<td width='4%'>Entidad: </td>
			<td width='28%' align='center'><%=rs.getString("nombre_entidad")%></td>
			<td width='3%'>Edad: </td> 
	        <td width='3%' align='center'><%=rs.getString("EDAD")%></td>
	     </tr>						       
	</table>
	</td>
</tr>	
			
			
			
	<%	}
		
		rs.getStatement().close();
	} catch (Exception ex) {
		System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "+ ex);
	}%>	
	        
	      </td>
	     </tr>
  		<tr>
		 <td>
			<input type="hidden" name="txtCodReporte" id="txtCodReporte" value="<%=CodInfPYPPlanFam %>" />
		 </td>
		</tr>
	
<!-- Tabla Nivel Educativo --> 
<tr>
<td id="nivel_educativo"> 
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
    <% rs = null;
	   st = null;
	   try {
		  con=new Conexion();
		  st = con.conn.createStatement();
		  rs = st.executeQuery("SELECT * FROM pyp_nivel_estudio WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
			if(rs.next()){%>	
			  <option value="<%=rs.getString("nivel_estudio")%>"><%=rs.getString("nivel_estudio")%></option>
			<%	if (rs.getString("nivel_estudio").equals("Analfabeto")){%>
				<option value="Primaria Completa">Primaria Completa</option>
				<option value="Bachillerato Completo">Bachillerato Completo</option>
				<option value="Tecnico">Tecnico</option>
				<option value="Universitario">Universitario</option>	
			<%  }
				if (rs.getString("nivel_estudio").equals("Primaria Completa")){%>
				<option value="Analfabeto">Analfabeto</option>
				<option value="Bachillerato Completo">Bachillerato Completo</option>
				<option value="Tecnico">Tecnico</option>
				<option value="Universitario">Universitario</option>	
			<%	}
				if (rs.getString("nivel_estudio").equals("Bachillerato Completo")){%>
				<option value="Analfabeto">Analfabeto</option>
				<option value="Primaria Completa">Primaria Completa</option>
				<option value="Tecnico">Tecnico</option>
				<option value="Universitario">Universitario</option>	
			<%	}
				if (rs.getString("nivel_estudio").equals("Tecnico")){%>
				<option value="Analfabeto">Analfabeto</option>
				<option value="Primaria Completa">Primaria Completa</option>
				<option value="Bachillerato Completo">Bachillerato Completo</option>
				<option value="Universitario">Universitario</option>	
			<%	}
			if (rs.getString("nivel_estudio").equals("Universitario")){%>
				<option value="Analfabeto">Analfabeto</option>
				<option value="Primaria Completa">Primaria Completa</option>
				<option value="Bachillerato Completo">Bachillerato Completo</option>
				<option value="Tecnico">Tecnico</option>	
			<%	}
		    }
		    rs.getStatement().close();
	  } catch (Exception ex) {
		System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "	+ ex);
	  }%>
  </select>
</th>
</tr>
</table>
</td>
</tr>

<!-- Tabla Convivientes -->
<tr>
<td id="convivientes">
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
	<% rs = null;
	   st = null;
	 try {
	    con=new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT estado_convivencia FROM pyp_convivientes WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
		if(rs.next()){
			if (rs.getString(1).equals("Soltero(a)")){%>
				<input type="checkbox" name="chk_solo" id="chk_solo" onClick="disableConyugue(this);" checked="checked"/>
				</td>
				<td colspan="2" align="center">
				<label for="chk_conyugue">Conyugue</label>
				<input type="checkbox" name="chk_conyugue" id="chk_conyugue" onClick="disableSolo(this);" disabled="disabled"/>
		    <%	}else{%>
				<input type="checkbox" name="chk_solo" id="chk_solo" onClick="disableConyugue(this);" disabled="disabled" />
				</td>
				<td colspan="2" align="center">
				<label for="chk_conyugue">Conyugue</label>
				<input type="checkbox" name="chk_conyugue" id="chk_conyugue" onClick="disableSolo(this);" checked="checked"/>
			<% }
		}
		rs.getStatement().close();
	} catch (Exception ex) {
		System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "	+ ex);
	}%>
</form>
</td>
</tr>
<tr>
 <td align="left"><br>¿Alguno de sus Padres est&aacute; Vivo?</td>
  <form>
    <% rs = null;
	   st = null;
	try {
		con=new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT * FROM pyp_convivientes WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
		if(rs.next()){
		  if (rs.getString("padres").equals("0")){%>
			<td>
			<br>
			<input type="radio" name="radioPadres" id="siPadres" onclick="clicksiPadres()" value="Si"/>Si
			<input type="radio" name="radioPadres" id="noPadres" onclick="num.disabled=true" value="No" checked="checked"/>No
			</td>
			<td><br>N&uacute;mero:</td>
			<td><br><input type="text" name="num" id="numPadres" disabled="disabled" maxlength="1" onkeypress="javascript:return validarNro(event)"/></td>
		  <%}else{%>
		  <td>
			<br>
			<input type="radio" name="radioPadres" id="siPadres" onclick="clicksiPadres()" value="Si" checked="checked"/>Si
			<input type="radio" name="radioPadres" id="noPadres" onclick="num.disabled=true" value="No"/>No
			</td>
			<td><br>N&uacute;mero:</td>
			<td><br><input type="text" name="num" id="numPadres" value="<%=rs.getString("padres")%>" maxlength="1" onkeypress="javascript:return validarNro(event)"/></td>
		   <%}%>  

 </form>
</tr>
<tr>
<td align="left">¿Tiene Hijos?</td>
<form>
	<% if (rs.getString("hijos").equals("0")){%>
	     <td><input type="radio" name="radioHijos" id="siHijos" onclick="clicksiHijos()" value="Si"/>Si
	     <input type="radio" name="radioHijos" id="noHijos" onclick="clickNoHijos(); num.disabled=true" value="No" checked="checked"/>No</td>
		 <td>N&uacute;mero:</td>
		 <td><input type="text" name="num" id="numHijos" disabled="disabled" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td>
	<%}else{%>
		 <td><input type="radio" name="radioHijos" id="siHijos" onclick="clicksiHijos()" value="Si"  checked="checked"/>Si
	     <input type="radio" name="radioHijos" id="noHijos" onclick="clickNoHijos(); num.disabled=true" value="No"/>No</td>
		 <td>N&uacute;mero:</td>
		 <td><input type="text" name="num" id="numHijos" value="<%=rs.getString("hijos")%>" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td>
	<%}%>
</form>
</tr>
<tr>
<td align="left">¿Tiene Nietos?</td>
<form>
	<% if (rs.getString("nietos").equals("0")){%>
		 <td><input type="radio" name="radioNietos" id="siNietos" onclick="clicksiNietos()" value="Si"/>Si
		 <input type="radio" name="radioNietos" id="noNietos" onclick="num.disabled=true" value="No" checked="checked"/>No</td>
		 <td>N&uacute;mero:</td>
		 <td><input type="text" name="num" id="numNietos" disabled="disabled" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td>
	<%}else{%>
	     <input type="radio" name="radioNietos" id="siNietos" onclick="clicksiNietos()" value="Si" checked="checked"/>Si
		 <input type="radio" name="radioNietos" id="noNietos" onclick="num.disabled=true" value="No"/>No
		 <td>N&uacute;mero:</td>
		 <td><input type="text" name="num" id="numNietos" value="<%=rs.getString("nietos")%>" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td>
	<%}%>
</form>
</tr>
<tr>
<td align="left">Otros:</td>
<form>
	<% if (rs.getString("otros").equals("0")){%>
		<td><input type="radio" name="radioOtros" id="siOtros" onclick="clicksiOtros()" value="Si"/>Si
		<input type="radio" name="radioOtros" id="noOtros" onclick="num.disabled=true" value="No" checked="checked"/>No</td>
		<td>N&uacute;mero:</td>
		<td><input type="text" name="num" id="numOtros" disabled="disabled" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td>
	<%}else{%>
		<td><input type="radio" name="radioOtros" id="siOtros" onclick="clicksiOtros()" value="Si"  checked="checked"/>Si
		<input type="radio" name="radioOtros" id="noOtros" onclick="num.disabled=true" value="No"/>No</td>
		<td>N&uacute;mero:</td>
		<td><input type="text" name="num" id="numOtros" value="<%=rs.getString("otros")%>" maxlength="2" onkeypress="javascript:return validarNro(event)"/></td>

	<%}	
	}
		rs.getStatement().close();
	} catch (Exception ex) {
		System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
	}
	%>
</form>
</tr>
</table>
</td>
</tr> 

<!-- Tabla Actividad Laboral --> 
 <tr>
 <td id="actividad_laboral">
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
  <% rs = null;
	 st = null;
	try {
		con=new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT * FROM pyp_actividad_laboral WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
		 if(rs.next()){%>
			<option value="<%=rs.getString("actividad_laboral")%>"><%=rs.getString("actividad_laboral")%></option>
         <%	if (rs.getString("actividad_laboral").equals("Desempleado")){%>
				<option value="Independiente">Independiente</option>
			    <option value="Trabajos Eventuales">Trabajos Eventuales</option>
			    <option value="Plan Social">Plan Social</option>
			    <option value="Contratista">Contratista</option>
			    <option value="Asalariado">Asalariado</option>
			    <option value="Pensionado">Pensionado</option>	
	     <% }
            if (rs.getString("actividad_laboral").equals("Independiente")){%>
				<option value="Desempleado">Desempleado</option>
			    <option value="Trabajos Eventuales">Trabajos Eventuales</option>
			    <option value="Plan Social">Plan Social</option>
			    <option value="Contratista">Contratista</option>
			    <option value="Asalariado">Asalariado</option>
			    <option value="Pensionado">Pensionado</option>	
		 <% }
            if (rs.getString("actividad_laboral").equals("Trabajos Eventuales")){%>
				<option value="Desempleado">Desempleado</option>
			    <option value="Independiente">Independiente</option>
			    <option value="Plan Social">Plan Social</option>
			    <option value="Contratista">Contratista</option>
			    <option value="Asalariado">Asalariado</option>
			    <option value="Pensionado">Pensionado</option>	
	    <%  }		
            if (rs.getString("actividad_laboral").equals("Plan Social")){%>
				<option value="Desempleado">Desempleado</option>
			    <option value="Independiente">Independiente</option>
			    <option value="Trabajos Eventuales">Trabajos Eventuales</option>
			    <option value="Contratista">Contratista</option>
			    <option value="Asalariado">Asalariado</option>
			    <option value="Pensionado">Pensionado</option>	
	   <%  }
           if (rs.getString("actividad_laboral").equals("Contratista")){%>
				<option value="Desempleado">Desempleado</option>
			    <option value="Independiente">Independiente</option>
			    <option value="Trabajos Eventuales">Trabajos Eventuales</option>
			    <option value="Plan Social">Plan Social</option>
			    <option value="Asalariado">Asalariado</option>
			    <option value="Pensionado">Pensionado</option>
	    <% }		
           if (rs.getString("actividad_laboral").equals("Asalariado")){%>
				<option value="Desempleado">Desempleado</option>
			    <option value="Independiente">Independiente</option>
			    <option value="Trabajos Eventuales">Trabajos Eventuales</option>
			    <option value="Plan Social">Plan Social</option>
			    <option value="Contratista">Contratista</option>
			    <option value="Pensionado">Pensionado</option>
	    <% }     
           if (rs.getString("actividad_laboral").equals("Pensionado")){%>
				<option value="Desempleado">Desempleado</option>
			    <option value="Independiente">Independiente</option>
			    <option value="Trabajos Eventuales">Trabajos Eventuales</option>
			    <option value="Plan Social">Plan Social</option>
			    <option value="Contratista">Contratista</option>
			    <option value="Asalariado">Asalariado</option>
	    <% } %>	
</select>
  </td>
</tr>
<tr>
 <td width="43%" align="left"><br>Fuma:</td>
	<%if (rs.getString("fuma").equals("No")){%>	
	  <td>
		<br><input type="radio" name="Fuma" id="siFuma" value="Si" onclick="clicksiFuma()"/>Si
		  	<input type="radio" name="Fuma" id="noFuma" value="No" onclick="nameFuma.disabled=true" checked="checked"/>No
		    &nbsp;&nbsp;&nbsp;&nbsp; 
      		¿Que fuma y con que frecuencia?
     		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
		    <input size="40%" type="text" name="nameFuma" id="campoFuma" disabled="disabled"/>
	  </td>
	<%}else{%>
      <td>
		<br><input type="radio" name="Fuma" id="siFuma" value="Si" onclick="clicksiFuma()" checked="checked"/>Si
		    <input type="radio" name="Fuma" id="noFuma" value="No" onclick="nameFuma.disabled=true"/>No
		    &nbsp;&nbsp;&nbsp;&nbsp; 
      		¿Que fuma y con que frecuencia?
      		&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
		    <input size="40%" type="text" name="nameFuma" id="campoFuma" value="<%=rs.getString("fuma")%>"/>
	  </td>
	<%}%>
</tr>
<tr>
<td align="left">Alcohol:</td>
	<%if (rs.getString("alcohol").equals("No")){%>
		<td>
		  <input type="radio" name="Alcohol" id="siAlcohol" value="Si" onclick="clicksiAlcohol()"/>Si
		  <input type="radio" name="Alcohol" id="noAlcohol" value="No" onclick="nameAlcohol.disabled=true" checked="checked"/>No
		  &nbsp;&nbsp;&nbsp;&nbsp; 
      	  ¿Que bebidas toma y con que frecuencia?
      	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		  <input size="40%" type="text" name="nameAlcohol" id="campoAlcohol" disabled="disabled"/>
		</td>
		
		<%}else{%>
		
		<td>
		  <input type="radio" name="Alcohol" id="siAlcohol" value="Si" onclick="clicksiAlcohol()" checked="checked"/>Si
		  <input type="radio" name="Alcohol" id="noAlcohol" value="No" onclick="nameAlcohol.disabled=true"/>No
		  &nbsp;&nbsp;&nbsp;&nbsp;
      	  ¿Que bebidas toma y con que frecuencia?
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <input size="40%" type="text" name="nameAlcohol" id="campoAlcohol" value="<%=rs.getString("alcohol")%>"/>
		</td>
		
		<%}%>

</tr>

<tr>
<td align="left">Drogas:</td>

		<%if (rs.getString("drogas").equals("No")){%>
		
		<td>
		  <input type="radio" name="Drogas" id="siDrogas" value="Si" onclick="clicksiDrogas()"/>Si
		  <input type="radio" name="Drogas" id="noDrogas" value="No" onclick="nameDrogas.disabled=true" checked="checked"/>No
		  &nbsp;&nbsp;&nbsp;&nbsp; 
          ¿Que drogas consume y con que frecuencia?
          &nbsp;
		  <input size="40%" type="text" name="nameDrogas" id="campoDrogas" disabled="disabled"/>
		</td>
		
		<%}else{%>
		
		<td>
		  <input type="radio" name="Drogas" id="siDrogas" value="Si" onclick="clicksiDrogas()" checked="checked"/>Si
		  <input type="radio" name="Drogas" id="noDrogas" value="No" onclick="nameDrogas.disabled=true"/>No
		  &nbsp;&nbsp;&nbsp;&nbsp; 
          ¿Que drogas consume y con que frecuencia?
          &nbsp;
		  <input size="40%" type="text" name="nameDrogas" id="campoDrogas" value="<%=rs.getString("drogas")%>"/>
		</td>
		
		<%}%>

</tr>

<tr>
<td align="left">Actividad F&iacute;sica:</td>

		<%if (rs.getString("actividad_fisica").equals("No")){%>

		<td>
		  <input type="radio" name="Actividad_Fisica" id="siActividad_Fisica" value="Si" onclick="clicksiActividadFisica()"/>Si
		  <input type="radio" name="Actividad_Fisica" id="noActividad_Fisica" value="No" onclick="nameActividadFisica.disabled=true" checked="checked"/>No
		  &nbsp;&nbsp;&nbsp;&nbsp; 
          ¿Que actividad realiza y con que frecuencia?
          &nbsp;&nbsp;
		  <input size="40%" type="text" name="nameActividadFisica" id="campoActividadFisica" disabled="disabled"/>
		</td>
		
		<%}else{%>
		
		<td>
		  <input type="radio" name="Actividad_Fisica" id="siActividad_Fisica" value="Si" onclick="clicksiActividadFisica()" checked="checked"/>Si
		  <input type="radio" name="Actividad_Fisica" id="noActividad_Fisica" value="No" onclick="nameActividadFisica.disabled=true"/>No
		  &nbsp;&nbsp;&nbsp;&nbsp; 
          ¿Que actividad realiza y con que frecuencia?
          &nbsp;&nbsp;
		  <input size="40%" type="text" name="nameActividadFisica" id="campoActividadFisica" value="<%=rs.getString("actividad_fisica")%>"/>
		</td>

		<%}				
				}
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
		}
		%>

</tr>

</table>
</td>
</tr>



<!-- Tabla Vivienda Habitat -->
<tr>
	<td id="viviendad_habitat">
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

  
  		<% 	 rs = null;
			 st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_vivienda_habitat WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){
		 %>
		 
		 
		 <option value="<%=rs.getString("Propiedad")%>"><%=rs.getString("Propiedad")%></option>

	<%		if (rs.getString("Propiedad").equals("Propia")){%>
				<option value="Alquilada">Alquilada</option>
			    <option value="Familiar">Familiar</option>
	<%}		if (rs.getString("Propiedad").equals("Alquilada")){%>
				<option value="Propia">Propia</option>
			    <option value="Familiar">Familiar</option>
	<%}		if (rs.getString("Propiedad").equals("Familiar")){%>
				<option value="Propia">Propia</option>
			    <option value="Alquilada">Alquilada</option>
	<%} %>
  </select>
  </td>
		 <td></td>
		
  
  <td align="left">Gas:</td>
		 <%if (rs.getString("gas").equals("No")){%>
		 <td>
		    <input type="radio" name="radGas" id="Si"/>Si
		    <input type="radio" name="radGas" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radGas" id="Si" checked="checked"/>Si
		    <input type="radio" name="radGas" id="No"/>No
		 </td>
		 <%}%>
  </tr>
  
  <tr>
  <td align="left">Comparte Habitaci&oacute;n:</td>

		 <%if (rs.getString("habitacion_compartida").equals("No")){%>
  		 <td>
		    <input type="radio" name="radHabitacionCompartida" id="Si"/>Si
		    <input type="radio" name="radHabitacionCompartida" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radHabitacionCompartida" id="Si" checked="checked"/>Si
		    <input type="radio" name="radHabitacionCompartida" id="No"/>No
		 </td>
		 <%}%>

  <td align="left">Luz:</td>
		 <%if (rs.getString("luz").equals("No")){%>
		 <td>
		    <input type="radio" name="radLuz" id="Si"/>Si
		    <input type="radio" name="radLuz" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radLuz" id="Si" checked="checked"/>Si
		    <input type="radio" name="radLuz" id="No"/>No
		 </td>
		 <%}%>
  
  </tr>
  
  <tr>
  <td align="left">Animales Domesticos:</td>
		 <%if (rs.getString("animales_domesticos").equals("No")){%>
  		 <td>
		    <input type="radio" name="radAnimalesDomesticos" id="Si"/>Si
		    <input type="radio" name="radAnimalesDomesticos" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radAnimalesDomesticos" id="Si" checked="checked"/>Si
		    <input type="radio" name="radAnimalesDomesticos" id="No"/>No
		 </td>
		 <%}%>
  
  <td align="left">Aseo:</td>
		 <%if (rs.getString("aseo").equals("No")){%>
  		 <td>
    		<input type="radio" name="radAseo" id="Si"/>Si
    		<input type="radio" name="radAseo" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
    		<input type="radio" name="radAseo" id="Si" checked="checked"/>Si
    		<input type="radio" name="radAseo" id="No"/>No
		 </td>
		 <%}%>
  </tr>
  
  <tr>
  <td></td>
  <td></td>
  <td align="left">Alcantarillado:</td>
	 <%if (rs.getString("alcantarillado").equals("No")){%>
  		 <td>
		    <input type="radio" name="radAlcantarillado" id="Si"/>Si 
		    <input type="radio" name="radAlcantarillado" id="No" checked="checked"/>No
		 </td>
	 <%}else{%>
		 <td>
		    <input type="radio" name="radAlcantarillado" id="Si" checked="checked"/>Si 
		    <input type="radio" name="radAlcantarillado" id="No"/>No
		 </td>
	 <%}%>
  </tr>
  
  <tr>
  <td></td>
  <td></td>
  <td align="left">Agua:</td>
		 <%if (rs.getString("agua").equals("No")){%>
  		 <td>
		    <input type="radio" name="radAgua" id="Si"/>Si 
		    <input type="radio" name="radAgua" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radAgua" id="Si" checked="checked"/>Si 
		    <input type="radio" name="radAgua" id="No"/>No
		 </td>
		 <%}%>
  </tr>
  
  
  <tr><td><br></td></tr>
  
  <tr>
  <th align="left">Convivencia</th>
  </tr>
  
  <tr>
  <td>Se siente en riesgo con su pareja o familia:</td>
		 <%if (rs.getString("riesgo_pareja").equals("No")){%>
		 <td>
		    <input type="radio" name="radRiesgoPareja" id="Si"/>Si
		    <input type="radio" name="radRiesgoPareja" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radRiesgoPareja" id="Si" checked="checked"/>Si
		    <input type="radio" name="radRiesgoPareja" id="No"/>No
		 </td>
		 <%}%>
  </tr>
  
  <tr> 
  <td>Est&aacute; siendo maltratado actualmente por su pareja o familia:</td>
		 <%if (rs.getString("maltrato_actual").equals("No")){%>
		 <td>
		    <input type="radio" name="radMaltratoActual" id="Si"/>Si
		    <input type="radio" name="radMaltratoActual" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radMaltratoActual" id="Si" checked="checked"/>Si
		    <input type="radio" name="radMaltratoActual" id="No"/>No
		 </td>
		 <%}%>
  </tr>
  
  <tr>
  <td>Se han presentado actos de violencia dentro de la relaci&oacute;n con su pareja o familia:</td>
		 <%if (rs.getString("actos_violencia").equals("No")){%>
		 <td>
		    <input type="radio" name="radActosViolencia" id="Si"/>Si
		    <input type="radio" name="radActosViolencia" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radActosViolencia" id="Si" checked="checked"/>Si
		    <input type="radio" name="radActosViolencia" id="No"/>No
		 </td>
		 <%}%>
  </tr>
  
  <tr>
  <td>Satisfecho(a) con la forma como usted comparte con su pareja o familia:</td>
		 <%if (rs.getString("satisfaccion").equals("No")){%>
		 <td>
		    <input type="radio" name="radSatisfaccion" id="Si"/>Si
		    <input type="radio" name="radSatisfaccion" id="No" checked="checked"/>No
		 </td>
		 <%}else{%>
		 <td>
		    <input type="radio" name="radSatisfaccion" id="Si" checked="checked"/>Si
		    <input type="radio" name="radSatisfaccion" id="No"/>No
		 </td>
  </tr>

		<%}				
				}
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
		}
		%>
  
  
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
<td id="antecedentes_familiares">
<table style="width: 100%">
  <tr>
     <td id="cabecera2" class="style11" colspan="2" align="center">ANTECEDENTES FAMILIARES</td>
     <p>&nbsp;</p>
  </tr>
</table>

<table style="width: 100%">
<tr>
 <td>1. Hipertension Arterial</td>

			<%	rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_antecedentes_familiares WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){
		 	    	if (rs.getString("fam_hipertension_arterial").equals("Si")){%>

			 <td>
			    <input type="radio" name="radFamHipertensionArterial" id="Si" onclick="AparecerSelects('DivHipArtCIE'); AparecerSelects('DivHipArtFAMILIA')" checked="checked" />Si
			    <input type="radio" name="radFamHipertensionArterial" id="No" onclick="DesaparecerSelects('DivHipArtCIE'); DesaparecerSelects('DivHipArtFAMILIA')"/>No
			 </td>
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO SI -->
<tr>
<!-- Aparecer Select Hipertension Arterial -->
					<td><div id="DivHipArtCIE">
			 		<select name="HipArtCIE" id="HipArtCIE" class="select"> <!-- onchange="CodigoCieHipArtFamiliares()"> -->
			 		<%	
			 		ResultSet rs1 = null;
			 		ResultSet rs2 = null;
					 	st = null;
					
					 try {
					    con=new Conexion();
						st = con.conn.createStatement();
						rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I1%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
						if(rs1.next()){	%>	
			    	    	<option title="<%=rs1.getString("nombre") %>" value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>
					  <%}	
					    rs1.getStatement().close();
				    
						st = con.conn.createStatement();
						rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE  ci.codigoCIE LIKE 'I1%'");
						while(rs2.next()){	%>	
							<option title="<%=rs2.getString("nombre") %>" value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
					  <%}	
						rs2.getStatement().close();
					} catch (Exception ex) {
							System.out.println("Error en MetodoHipertensionArterial>>BuscarAntecedentesFamiliaresModificacionHipertensionArterial " + ex);
					}%>	
					</select>
					</div></td>
				
		   <%}else{%><!-- Cierra Si Hipertension Arterial Radio -->
		   
			 <td>
			    <input type="radio" name="radFamHipertensionArterial" id="Si" onclick="AparecerSelects('DivHipArtCIE'); AparecerSelects('DivHipArtFAMILIA')"/>Si
			    <input type="radio" name="radFamHipertensionArterial" id="No" onclick="DesaparecerSelects('DivHipArtCIE'); DesaparecerSelects('DivHipArtFAMILIA')" checked="checked"/>No
			 </td>
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->
<tr>			 
			 <!-- Aparecer Select Hipertension Arterial -->
					<td><div id="DivHipArtCIE" style="display: none;">
			 		<select name="HipArtCIE" id="HipArtCIE" class="select"> <!-- onchange="CodigoCieHipArtFamiliares()"> -->
			 		<%	
			 		ResultSet rs1 = null;
			 		ResultSet rs2 = null;
					 	st = null;
					
					 try {
					    con=new Conexion();
						st = con.conn.createStatement();
						rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I1%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
						if(rs1.next()){	%>	
			    	    	<option title="<%=rs1.getString("nombre") %>" value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>
					  <%}	
					    rs1.getStatement().close();
				    
						st = con.conn.createStatement();
						rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE  ci.codigoCIE LIKE 'I1%'");
						while(rs2.next()){	%>	
							<option title="<%=rs2.getString("nombre") %>" value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
					  <%}	
						rs2.getStatement().close();
					} catch (Exception ex) {
							System.out.println("Error en MetodoHipertensionArterial>>BuscarAntecedentesFamiliaresModificacionHipertensionArterial " + ex);
					}%>	
					</select>
					</div></td>
</tr>							
	
<!-- EMPIEZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->							
<tr>			
<td>
<div id="DivHipArtFAMILIA" style="display: none;" >
			<%		ResultSet rsHIPARTN = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsHIPARTN = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I1%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsHIPARTN.next()){	
			%>
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="HipArtFAMILIA" id="HipArtFAMILIA" value="<%=rsHIPARTN.getString("familiar") %>" size="69%" />
				
			<%}else{%>
			¿Que miembros de la familia padece el antecedente?<br>
				<input type="text" name="HipArtFAMILIA" id="HipArtFAMILIA"  size="69%" />		
			<%}
			rsHIPARTN.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarHipertensionArterial " + ex);
			}
			%>
			</div>
			</td>		 
	    	 <%}%><!-- Finaliza Else Checked No -->
</tr>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->

<!-- EMPIEZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
<tr>
			<%		ResultSet rsHIPART = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsHIPART = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I1%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsHIPART.next()){	
			%>


<td>
<div id="DivHipArtFAMILIA">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="HipArtFAMILIA" id="HipArtFAMILIA" value="<%=rsHIPART.getString("familiar") %>" size="69%"/>
</div>
</td>
</tr>
 			<%}			
				
			rsHIPART.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarHipertensionArterial " + ex);
			}
			%>
 <!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
 
 
 <!-- --------------------------------------------------------------------------------------------------------------------- -->
<tr>  
 <td><br>2. Hepatopatias</td>

			<%if (rs.getString("fam_hepatopatias").equals("Si")){%>
			
			<td><br>
			   <input type="radio" name="radFamHepatopatias" id="Si" onclick="AparecerSelects('DivHepCIE'); AparecerSelects('DivHepFAMILIA')" checked="checked"/>Si 
			   <input type="radio" name="radFamHepatopatias" id="No" onclick="DesaparecerSelects('DivHepCIE'); DesaparecerSelects('DivHepFAMILIA')"/>No
			</td> 
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO SI -->
<tr>
<td><div id="DivHepCIE">
					 		<select name="HepCIE" id="HepCIE" class="select"> <!-- onchange="CodigoCieDiaFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'K7%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE  ci.codigoCIE LIKE 'K7%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesFamiliaresModificacionHepatopatias " + ex);
								}
							%>	
							</select>
							</div></td>
			
			<%}else{%>
			
			<td><br>
			   <input type="radio" name="radFamHepatopatias" id="Si" onclick="AparecerSelects('DivHepCIE'); AparecerSelects('DivHepFAMILIA')"/>Si 
			   <input type="radio" name="radFamHepatopatias" id="No" onclick="DesaparecerSelects('DivHepCIE'); DesaparecerSelects('DivHepFAMILIA')" checked="checked"/>No
			</td>
</tr>

<!-- VALIDACION DE SELECT CODIGOS 10 CUANDO ESTA CHECKEADO NO -->
<tr>		
							<td><div id="DivHepCIE" style="display: none;">
					 		<select name="HepCIE" id="HepCIE" class="select"> <!-- onchange="CodigoCieDiaFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							st = null;				
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'K7%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE  ci.codigoCIE LIKE 'K7%'");
							while(rs2.next()){
							%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesFamiliaresModificacionHepatopatias " + ex);
								}
							%>			
							</select>
							</div></td>
</tr>
<!-- FINALIZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->

<!-- VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->							
<tr>			
<td>
<div id="DivHepFAMILIA" style="display: none;" >
			<%		ResultSet rsHEPN = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsHEPN = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'K7%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsHEPN.next()){	
			%>
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="HepFAMILIA" id="HepFAMILIA" value="<%=rsHEPN.getString("familiar") %>" size="69%" />
				
			<%}else{%>
			¿Que miembros de la familia padece el antecedente?<br>
				<input type="text" name="HepFAMILIA" id="HepFAMILIA"  size="69%" />		
			<%}
			rsHEPN.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarHepatopatias " + ex);
			}
			%>
			</div>
			</td>
			<%}%><!-- Finaliza Else Checked No -->
</tr>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->

<!-- VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
<tr>
			<%		ResultSet rsHEP = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsHEP = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'K7%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsHEP.next()){	
			%>
			
<td>
<div id="DivHepFAMILIA">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="HepFAMILIA" id="HepFAMILIA" value="<%=rsHEP.getString("familiar") %>" size="69%"/>
</div>
</td>
</tr>
			<%}			
				
			rsHEP.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarHepatopatias " + ex);
			}
			%>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->		

			
<!-- -------------------------------------------------------------------------------------------------------------------------- -->
<tr>  
 <td><br>3. Diabetes</td>

			<%if (rs.getString("fam_diabetes").equals("Si")){%>
			
			<td><br>
			   <input type="radio" name="radFamDiabetes" id="Si" onclick="AparecerSelects('DivDiaCIE'); AparecerSelects('DivDiaFAMILIA')" checked="checked"/>Si 
			   <input type="radio" name="radFamDiabetes" id="No" onclick="DesaparecerSelects('DivDiaCIE'); DesaparecerSelects('DivDiaFAMILIA')"/>No
			</td> 
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO SI -->
<tr>
<td><div id="DivDiaCIE">
					 		<select name="DiaCIE" id="DiaCIE" class="select"> <!-- onchange="CodigoCieDiaFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'E1%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE  ci.codigoCIE LIKE 'E1%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesFamiliaresModificacionDiabetes " + ex);
								}
							%>	
							</select>
							</div></td>
			
			<%}else{%>
			
			<td><br>
			   <input type="radio" name="radFamDiabetes" id="Si" onclick="AparecerSelects('DivDiaCIE'); AparecerSelects('DivDiaFAMILIA')"/>Si 
			   <input type="radio" name="radFamDiabetes" id="No" onclick="DesaparecerSelects('DivDiaCIE'); DesaparecerSelects('DivDiaFAMILIA')" checked="checked"/>No
			</td>
</tr>

<!-- VALIDACION DE SELECT CODIGOS 10 CUANDO ESTA CHECKEADO NO -->
<tr>		
							<td><div id="DivDiaCIE" style="display: none;">
					 		<select name="DiaCIE" id="DiaCIE" class="select"> <!-- onchange="CodigoCieDiaFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							st = null;				
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'E1%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE  ci.codigoCIE LIKE 'E1%'");
							while(rs2.next()){
							%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesFamiliaresModificacionDiabetes " + ex);
								}
							%>			
							</select>
							</div></td>
</tr>
<!-- FINALIZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->

<!-- VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->							
<tr>			
<td>
<div id="DivDiaFAMILIA" style="display: none;" >
			<%		ResultSet rsDIAN = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsDIAN = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'E1%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsDIAN.next()){	
			%>
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="DiaFAMILIA" id="DiaFAMILIA" value="<%=rsDIAN.getString("familiar") %>" size="69%" />
				
			<%}else{%>
			¿Que miembros de la familia padece el antecedente?<br>
				<input type="text" name="DiaFAMILIA" id="DiaFAMILIA"  size="69%" />		
			<%}
			rsDIAN.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarDiabetes " + ex);
			}
			%>
			</div>
			</td>
			<%}%><!-- Finaliza Else Checked No -->
</tr>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->

<!-- VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
<tr>
			<%		ResultSet rsDIA = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsDIA = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'E1%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsDIA.next()){	
			%>
			
<td>
<div id="DivDiaFAMILIA">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="DiaFAMILIA" id="DiaFAMILIA" value="<%=rsDIA.getString("familiar") %>" size="69%"/>
</div>
</td>
</tr>
			<%}			
				
			rsDIA.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarDiabetes " + ex);
			}
			%>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
			
<!-- -------------------------------------------------------------------------------------------------------------------------- -->
<tr>  
 <td><br>4. Tumores</td>

			<%if (rs.getString("fam_tumores").equals("Si")){%>
			
			<td><br>
			   <input type="radio" name="radFamTumores" id="Si" onclick="AparecerSelects('DivTumCIE'); AparecerSelects('DivTumFAMILIA')" checked="checked"/>Si 
			   <input type="radio" name="radFamTumores" id="No" onclick="DesaparecerSelects('DivTumCIE'); DesaparecerSelects('DivTumFAMILIA')"/>No
			</td>
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO SI -->
<tr>
<td><div id="DivTumCIE">
					 		<select name="TumCIE" id="TumCIE" class="select"> <!-- onchange="CodigoCieTumFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoTumores>>BuscarAntecedentesFamiliaresModificacionTumores " + ex);
								}
							%>	
							</select>
							</div></td>

			
			<%}else{%> <!-- Finaliza Si Tumores Radio -->
			
			<td><br>
			   <input type="radio" name="radFamTumores" id="Si" onclick="AparecerSelects('DivTumCIE'); AparecerSelects('DivTumFAMILIA')"/>Si 
			   <input type="radio" name="radFamTumores" id="No" onclick="DesaparecerSelects('DivTumCIE'); DesaparecerSelects('DivTumFAMILIA')" checked="checked"/>No
			</td>
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->
<tr>		
							<td><div id="DivTumCIE" style="display: none;">
					 		<select name="TumCIE" id="TumCIE" class="select"> <!-- onchange="CodigoCieTumFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoTumores>>BuscarAntecedentesFamiliaresModificacionTumores " + ex);
								}
							%>	
							</select>
							</div></td>
</tr>
<!-- FINALIZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->

<!-- EMPIEZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->							
<tr>			
<td>
<div id="DivTumFAMILIA" style="display: none;" >
			<%		ResultSet rsTUMN = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsTUMN = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') ORDER BY ci.codigo DESC LIMIT 1");
				if(rsTUMN.next()){	
			%>
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="TumFAMILIA" id="TumFAMILIA" value="<%=rsTUMN.getString("familiar") %>" size="69%" />
				
			<%}else{%>
			¿Que miembros de la familia padece el antecedente?<br>
				<input type="text" name="TumFAMILIA" id="TumFAMILIA"  size="69%" />		
			<%}
			rsTUMN.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarTumores " + ex);
			}
			%>
			</div>
			</td>
			<%}%><!-- FINALIZA ELSE CHECKEADO NO -->
</tr>

<!-- EMPIEZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
<tr>
			<%		ResultSet rsTUM = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsTUM = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') ORDER BY ci.codigo DESC LIMIT 1");
				if(rsTUM.next()){	
			%>
			
<td>
<div id="DivTumFAMILIA">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="TumFAMILIA" id="TumFAMILIA" value="<%=rsTUM.getString("familiar") %>" size="69%"/>
</div>
</td>
</tr>
			<%}			
				
			rsTUM.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarTumores " + ex);
			}
			%>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->

<!-- -------------------------------------------------------------------------------------------------------------------------- -->
<tr>
 <td><br>5. Cardiopatias</td>

			<%if (rs.getString("fam_cardiopatias").equals("Si")){%>
			
			<td><br>
			   <input type="radio" name="radFamCardiopatias" id="Si" onclick="AparecerSelects('DivCarCIE'); AparecerSelects('DivCarFAMILIA')" checked="checked"/>Si 
			   <input type="radio" name="radFamCardiopatias" id="No" onclick="DesaparecerSelects('DivCarCIE'); DesaparecerSelects('DivCarFAMILIA')"/>No
			</td>
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO SI -->
<tr>
<td><div id="DivCarCIE">
					 		<select name="CarCIE" id="CarCIE" class="select"> <!-- onchange="CodigoCieCarFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'Q2%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'Q2%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoCardiopatias>>BuscarAntecedentesFamiliaresModificacionCardiopatias " + ex);
								}
							%>	
							</select>
							</div></td>
			
			<%}else{%> <!-- Finaliza Si Cardiopatias Radio -->
			
			<td><br>
			   <input type="radio" name="radFamCardiopatias" id="Si" onclick="AparecerSelects('DivCarCIE'); AparecerSelects('DivCarFAMILIA')"/>Si 
			   <input type="radio" name="radFamCardiopatias" id="No" onclick="DesaparecerSelects('DivCarCIE'); DesaparecerSelects('DivCarFAMILIA')" checked="checked"/>No
			</td>
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->
<tr>
							<td><div id="DivCarCIE" style="display: none;">
					 		<select name="CarCIE" id="CarCIE" class="select"> <!-- onchange="CodigoCieCarFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'Q2%' AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'Q2%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoCardiopatias>>BuscarAntecedentesFamiliaresModificacionCardiopatias " + ex);
								}
							%>	
							</select>
							</div></td>
</tr>
<!-- FINALIZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->

<!-- EMPIEZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->							
<tr>			
<td>
<div id="DivCarFAMILIA" style="display: none;" >
			<%		ResultSet rsCARN = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsCARN = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'Q2%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsCARN.next()){	
			%>
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="CarFAMILIA" id="CarFAMILIA" value="<%=rsCARN.getString("familiar") %>" size="69%" />
				
			<%}else{%>
			¿Que miembros de la familia padece el antecedente?<br>
				<input type="text" name="CarFAMILIA" id="CarFAMILIA" size="69%" />		
			<%}
			rsCARN.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarCardiopatias " + ex);
			}
			%>
			</div>
			</td>
			<%}%><!-- FINALIZA ELSE CHECKEADO NO -->
</tr>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->

<!-- EMPIEZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
<tr>
			<%		ResultSet rsCAR = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsCAR = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND ci.codigoCIE LIKE 'Q2%' ORDER BY ci.codigo DESC LIMIT 1");
				if(rsCAR.next()){	
			%>
			
<td>
<div id="DivCarFAMILIA">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="CarFAMILIA" id="CarFAMILIA" value="<%=rsCAR.getString("familiar") %>" size="69%"/>
</div>
</td>
</tr>
			<%}			
				
			rsCAR.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarCardiopatias " + ex);
			}
			%>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->

<!-- -------------------------------------------------------------------------------------------------------------------------- -->
<tr>  
 <td><br>6. Mentales</td>

			<%if (rs.getString("fam_mentales").equals("Si")){%>
			
			<td><br>
			   <input type="radio" name="radFamMentales" id="Si" onclick="AparecerSelects('DivMenCIE'); AparecerSelects('DivMenFAMILIA')" checked="checked"/>Si 
			   <input type="radio" name="radFamMentales" id="No" onclick="DesaparecerSelects('DivMenCIE'); DesaparecerSelects('DivMenFAMILIA')"/>No
			</td> 
</tr>

<!-- EMPIEZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO SI -->
<tr>
<td><div id="DivMenCIE">
					 		<select name="MenCIE" id="MenCIE" class="select"> <!-- onchange="CodigoCieDiaFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoMentales>>BuscarAntecedentesFamiliaresModificacionMentales " + ex);
								}
							%>	
							</select>
							</div></td>
			
			<%}else{%>
			
			<td><br>
			   <input type="radio" name="radFamMentales" id="Si" onclick="AparecerSelects('DivMenCIE'); AparecerSelects('DivMenFAMILIA')"/>Si 
			   <input type="radio" name="radFamMentales" id="No" onclick="DesaparecerSelects('DivMenCIE'); DesaparecerSelects('DivMenFAMILIA')" checked="checked"/>No
			</td>
</tr>

<!-- VALIDACION DE SELECT CODIGOS 10 CUANDO ESTA CHECKEADO NO -->
<tr>		
							<td><div id="DivMenCIE" style="display: none;">
					 		<select name="MenCIE" id="MenCIE" class="select"> <!-- onchange="CodigoCieDiaFamiliares()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							st = null;				
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, hic_antfamiliar haf, cie10 ci WHERE ap.pac_codigo_paciente=haf.codpac_fk AND ci.codigo=haf.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') AND haf.observacion LIKE '%Antecedentes Familiares%' ORDER BY haf.hora DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%')");
							while(rs2.next()){
							%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoMentales>>BuscarAntecedentesFamiliaresModificacionMentales " + ex);
								}
							%>			
							</select>
							</div></td>
</tr>
<!-- FINALIZA VALIDACION DE SELECT CODIGOS CIE 10 CUANDO ESTA CHECKEADO NO -->

<!-- VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->							
<tr>			
<td>
<div id="DivMenFAMILIA" style="display: none;" >
			<%		ResultSet rsMENN = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsMENN = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') ORDER BY ci.codigo DESC LIMIT 1");
				if(rsMENN.next()){	
			%>
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="MenFAMILIA" id="MenFAMILIA" value="<%=rsMENN.getString("familiar") %>" size="69%" />
				
			<%}else{%>
			¿Que miembros de la familia padece el antecedente?<br>
				<input type="text" name="MenFAMILIA" id="MenFAMILIA"  size="69%" />		
			<%}
			rsMENN.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarMentales " + ex);
			}
			%>
			</div>
			</td>
			<%}%><!-- Finaliza Else Checked No -->
</tr>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO NO -->

<!-- VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
<tr>
			<%		ResultSet rsMEN = null;
			 		st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rsMEN = st.executeQuery("SELECT * FROM hic_antfamiliar haf, cie10 ci WHERE ci.codigo=haf.codigoCIE_fk AND codpac_fk='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') ORDER BY ci.codigo DESC LIMIT 1");
				if(rsMEN.next()){	
			%>
			
<td>
<div id="DivMenFAMILIA">
¿Que miembros de la familia padece el antecedente?<br>
<input type="text" name="MenFAMILIA" id="MenFAMILIA" value="<%=rsMEN.getString("familiar") %>" size="69%"/>
</div>
</td>
</tr>
			<%}			
				
			rsMEN.getStatement().close();
			} catch (Exception ex) {
				System.out.println("Error en MetodoConsultaFamiliar>>BuscarJSPAntecedenteFamiliarMentales " + ex);
			}
			%>
<!-- FINALIZA VALIDACION DE CAMPO FAMILIAR CUANDO ESTA CHECKEADO SI -->
			
			
		<!-- Finaliza Consulta Inicial -->		
		<%	//FINALIZA ELSE CHECKED NO DE MENTALES			
		}
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoAntecedentesFamiliares>>BuscarAntecedentesFamiliaresModificacion " + ex);
		}%>
</table>
</td>
</tr>

<!-- --------------------------------------------------------------------------------------------------------------------------- -->
<!-- Tabla Antecedentes Personales -->
<tr>
<td id="antecedentes_personales">
<table style="width: 100%">
<tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">ANTECEDENTES PERSONALES</td>
    <p>&nbsp;</p> 
</tr>
</table>

<table style="width: 100%">
 
  <tr>
    <td>1. Hipertension Arterial</td>

		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_antecedentes_personales WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){
					if (rs.getString("per_hipertension").equals("Si")){%>
					<td>
	      				<input type="radio" name="radPerHipertension" id="Si" onclick="AparecerSelects('DivHipArtPerCIE')" checked="checked"/>Si
	      				<input type="radio" name="radPerHipertension" id="No" onclick="DesaparecerSelects('DivHipArtPerCIE')"/>No
	    			</td>
	    			<td><div id="DivHipArtPerCIE">
				 		<select name="HipArtPerCIE" id="HipArtPerCIE" class="select"> <!-- onchange="CodigoCieHipArtPersonales()"> -->
			 		<% 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I1%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){%>	
					    		  	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'I1%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoHipertension>>BuscarAntecedentesPersonalesModificacionHipertension " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%> <!-- Finaliza Si Hipertension Radio  -->
	    
	    <td>
	      <input type="radio" name="radPerHipertension" id="Si" onclick="AparecerSelects('DivHipArtPerCIE')"/>Si
	      <input type="radio" name="radPerHipertension" id="No" onclick="DesaparecerSelects('DivHipArtPerCIE')" checked="checked"/>No
	    </td>
	    
	    				<td><div id="DivHipArtPerCIE" style="display: none;">
				 		<select name="HipArtPerCIE" id="HipArtPerCIE" class="select"> <!-- onchange="CodigoCieHipArtPersonales()"> -->
			 		<% 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I1%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){%>	
					    		  	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'I1%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoHipertension>>BuscarAntecedentesPersonalesModificacionHipertension " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>
</tr> 
     
<tr>                    
    <td>2. Mentales</td>

		<%if (rs.getString("per_mentales").equals("Si")){%>
		
	    <td>
	      <input type="radio" name="radPerMentales" id="Si" onclick="AparecerSelects('DivMenPerCIE')" checked="checked"/>Si
	      <input type="radio" name="radPerMentales" id="No" onclick="DesaparecerSelects('DivMenPerCIE')"/>No
	    </td>
	    
	    					<td><div id="DivMenPerCIE">
					 		<select name="MenPerCIE" id="MenPerCIE" class="select"> <!-- onchange="CodigoCieMenPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoMentales>>BuscarAntecedentesPersonalesModificacionMentales " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%> <!-- Finaliza Si Mentales Radio -->
	    
	    <td>
	      <input type="radio" name="radPerMentales" id="Si" onclick="AparecerSelects('DivMenPerCIE')"/>Si
	      <input type="radio" name="radPerMentales" id="No" onclick="DesaparecerSelects('DivMenPerCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivMenPerCIE" style="display: none;">
					 		<select name="MenPerCIE" id="MenPerCIE" class="select"> <!-- onchange="CodigoCieMenPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'F0%' OR ci.codigoCIE LIKE 'F1%' OR ci.codigoCIE LIKE 'F7%' OR ci.codigoCIE LIKE 'F9%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoMentales>>BuscarAntecedentesPersonalesModificacionMentales " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>

  </tr>
   
  
  <tr>
    <td>3. Diabetes</td>

		<%if (rs.getString("per_diabetes").equals("Si")){%>
		
	    <td>
	      <input type="radio" name="radPerDiabetes" id="Si" onclick="AparecerSelects('DivDiaPerCIE')" checked="checked"/>Si
	      <input type="radio" name="radPerDiabetes" id="No" onclick="DesaparecerSelects('DivDiaPerCIE')"/>No
	    </td>
	    
	    					<td><div id="DivDiaPerCIE">
					 		<select name="DiaPerCIE" id="DiaPerCIE" class="select"> <!-- onchange="CodigoCieDiaPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'E1%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'E1%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesPersonalesModificacionDiabetes " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%> <!-- Finaliza Si Diabetes Radio -->
	    
	    <td>
	      <input type="radio" name="radPerDiabetes" id="Si" onclick="AparecerSelects('DivDiaPerCIE')"/>Si
	      <input type="radio" name="radPerDiabetes" id="No" onclick="DesaparecerSelects('DivDiaPerCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivDiaPerCIE" style="display: none;">
					 		<select name="DiaPerCIE" id="DiaPerCIE" class="select"> <!-- onchange="CodigoCieDiaPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'E1%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'E1%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoDiabetes>>BuscarAntecedentesPersonalesModificacionDiabetes " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>
</tr>
 
<tr>      
    <td>4. Infecciones Pelvicas</td>

		<%if (rs.getString("per_infecciones_pelvicas").equals("Si")){%>
		
	    <td>
	      <input type="radio" name="radPerInfeccionesPelvicas" id="Si" onclick="AparecerSelects('DivInfPelCIE')" checked="checked"/>Si     
	      <input type="radio" name="radPerInfeccionesPelvicas" id="No" onclick="DesaparecerSelects('DivInfPelCIE')"/>No
	    </td>
	    
	    
	   					 	<td><div id="DivInfPelCIE">
					 		<select name="InfPelCIE" id="InfPelCIE" class="select"> <!-- onchange="CodigoCieInfPelPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'N70%' OR ci.codigoCIE LIKE 'N71%' OR ci.codigoCIE LIKE 'N72%' OR ci.codigoCIE LIKE 'N73%' OR ci.codigoCIE LIKE 'N74%' OR ci.codigoCIE LIKE 'N75%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'N70%' OR ci.codigoCIE LIKE 'N71%' OR ci.codigoCIE LIKE 'N72%' OR ci.codigoCIE LIKE 'N73%' OR ci.codigoCIE LIKE 'N74%' OR ci.codigoCIE LIKE 'N75%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoInfeccionesPelvicas>>BuscarAntecedentesPersonalesModificacionInfeccionesPelvicas " + ex);
								}
							%>	
								
							</select>
							</div></td>
	     
	     <%}else if(rs.getString("per_infecciones_pelvicas").equals("")){%>
	    
	    <td>(N/A)</td>
	    <td></td>
	    
	    <%}else if(rs.getString("per_infecciones_pelvicas").equals("No")){%>
	    	
	    <td>
	      <input type="radio" name="radPerInfeccionesPelvicas" id="Si" onclick="AparecerSelects('DivInfPelCIE')"/>Si     
	      <input type="radio" name="radPerInfeccionesPelvicas" id="No" onclick="DesaparecerSelects('DivInfPelCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivInfPelCIE" style="display: none;">
					 		<select name="InfPelCIE" id="InfPelCIE" class="select"> <!-- onchange="CodigoCieInfPelPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'N70%' OR ci.codigoCIE LIKE 'N71%' OR ci.codigoCIE LIKE 'N72%' OR ci.codigoCIE LIKE 'N73%' OR ci.codigoCIE LIKE 'N74%' OR ci.codigoCIE LIKE 'N75%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'N70%' OR ci.codigoCIE LIKE 'N71%' OR ci.codigoCIE LIKE 'N72%' OR ci.codigoCIE LIKE 'N73%' OR ci.codigoCIE LIKE 'N74%' OR ci.codigoCIE LIKE 'N75%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoInfeccionesPelvicas>>BuscarAntecedentesPersonalesModificacionInfeccionesPelvicas " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%> 
  
  </tr>
  
  <tr>
    <td>5. Cardiopatias</td>

		<%if (rs.getString("per_cardiopatias").equals("Si")){%>

	    <td>    
	       <input type="radio" name="radPerCardiopatias" id="Si" onclick="AparecerSelects('DivCarPerCIE')" checked="checked"/>Si     
	       <input type="radio" name="radPerCardiopatias" id="No" onclick="DesaparecerSelects('DivCarPerCIE')"/>No
	    </td>
	    
	    
	    					<td><div id="DivCarPerCIE">
					 		<select name="CarPerCIE" id="CarPerCIE" class="select"> <!-- onchange="CodigoCieCarPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'Q2%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'Q2%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoInfeccionesCardiopatias>>BuscarAntecedentesPersonalesModificacionCardiopatias " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%> <!-- Finaliza Si Cardiopatias Radio -->
	    
	    <td>    
	       <input type="radio" name="radPerCardiopatias" id="Si" onclick="AparecerSelects('DivCarPerCIE')"/>Si     
	       <input type="radio" name="radPerCardiopatias" id="No" onclick="DesaparecerSelects('DivCarPerCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivCarPerCIE" style="display: none;">
					 		<select name="CarPerCIE" id="CarPerCIE" class="select"> <!-- onchange="CodigoCieCarPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'Q2%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'Q2%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoInfeccionesCardiopatias>>BuscarAntecedentesPersonalesModificacionCardiopatias " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    		
	    <%}%>   
</tr>       
       
<tr>    
    	<td>6. Infeccion Cervical</td>

		<%if (rs.getString("per_infeccion_cervical").equals("Si")){%>
		
	    <td>
	       <input type="radio" name="radPerInfeccionCervical" id="Si" onclick="AparecerSelects('DivInfCerCIE')" checked="checked"/>Si   
	       <input type="radio" name="radPerInfeccionCervical" id="No" onclick="DesaparecerSelects('DivInfCerCIE')"/>No
	    </td>
	    
	    					<td><div id="DivInfCerCIE">
					 		<select name="InfCerCIE" id="InfCerCIE" class="select"> <!-- onchange="CodigoCieInfCerPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'N8%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'N8%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoInfeccionesInfeccionCervical>>BuscarAntecedentesPersonalesModificacionInfeccionCervical " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else if(rs.getString("per_infeccion_cervical").equals("")){%>
	    
	    <td>(N/A)</td>
	    <td></td>
	    
	    <%}else if(rs.getString("per_infeccion_cervical").equals("No")){%>
	    
	    <td>
	       <input type="radio" name="radPerInfeccionCervical" id="Si" onclick="AparecerSelects('DivInfCerCIE')"/>Si   
	       <input type="radio" name="radPerInfeccionCervical" id="No" onclick="DesaparecerSelects('DivInfCerCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivInfCerCIE" style="display: none;">
					 		<select name="InfCerCIE" id="InfCerCIE" class="select"> <!-- onchange="CodigoCieInfCerPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'N8%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'N8%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoInfeccionesInfeccionCervical>>BuscarAntecedentesPersonalesModificacionInfeccionCervical " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>

  </tr>
  
  <tr>
    <td>7. Hepatopatias</td>

		<%if (rs.getString("per_hepatopatias").equals("Si")){%>
		
	    <td>
	       <input type="radio" name="radPerHepatopatias" id="Si" onclick="AparecerSelects('DivHepPerCIE')" checked="checked"/>Si   
	       <input type="radio" name="radPerHepatopatias" id="No" onclick="DesaparecerSelects('DivHepPerCIE')"/>No
	    </td>
	    
	    					<td><div id="DivHepPerCIE">
					 		<select name="HepPerCIE" id="HepPerCIE" class="select"> <!-- onchange="CodigoCieHepPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'K7%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'K7%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesPersonalesModificacionHepatopatias " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%> <!-- Finaliza Si Hepatopatias Radio -->
	    
	    <td>
	       <input type="radio" name="radPerHepatopatias" id="Si" onclick="AparecerSelects('DivHepPerCIE')"/>Si   
	       <input type="radio" name="radPerHepatopatias" id="No" onclick="DesaparecerSelects('DivHepPerCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivHepPerCIE" style="display: none;">
					 		<select name="HepPerCIE" id="HepPerCIE" class="select"> <!-- onchange="CodigoCieHepPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'K7%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'K7%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoHepatopatias>>BuscarAntecedentesPersonalesModificacionHepatopatias " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>
</tr>     
     
<tr>                    
    <td>8. Flujo Vaginal</td>

		<%if (rs.getString("per_flujo_vaginal").equals("Si")){%>
		
	    <td>    
	       <input type="radio" name="radPerFlujoVaginal" id="Si" onclick="AparecerSelects('DivFluVagCIE')" checked="checked"/>Si     
	       <input type="radio" name="radPerFlujoVaginal" id="No" onclick="DesaparecerSelects('DivFluVagCIE')"/>No
	    </td>
	    
	    					<td><div id="DivFluVagCIE">
					 		<select name="FluVagCIE" id="FluVagCIE" class="select"> <!-- onchange="CodigoCieFluVagPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'N76%' OR ci.codigoCIE LIKE 'N77%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'N76%' OR ci.codigoCIE LIKE 'N77%') ");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoFlujoVaginal>>BuscarAntecedentesPersonalesModificacionFlujoVaginal " + ex);
								}
							%>	
								
							</select>
							</div></td>
	   
	    <%}else if (rs.getString("per_flujo_vaginal").equals("")){%>
	    
	    <td>(N/A)</td>
	    <td></td>
	    
	    <%}else if (rs.getString("per_flujo_vaginal").equals("No")){%>
	    
	    <td>    
	       <input type="radio" name="radPerFlujoVaginal" id="Si" onclick="AparecerSelects('DivFluVagCIE')"/>Si     
	       <input type="radio" name="radPerFlujoVaginal" id="No" onclick="DesaparecerSelects('DivFluVagCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivFluVagCIE" style="display: none;">
					 		<select name="FluVagCIE" id="FluVagCIE" class="select"> <!-- onchange="CodigoCieFluVagPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'N76%' OR ci.codigoCIE LIKE 'N77%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'N76%' OR ci.codigoCIE LIKE 'N77%') ");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoFlujoVaginal>>BuscarAntecedentesPersonalesModificacionFlujoVaginal " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>
    
  </tr>
  
  <tr>
    <td>9. Nefritis</td>

		<%if (rs.getString("per_nefritis").equals("Si")){%>
		
	    <td>   
	       <input type="radio" name="radPerNefritis" id="Si" onclick="AparecerSelects('DivNefCIE')" checked="checked"/>Si
	       <input type="radio" name="radPerNefritis" id="No" onclick="DesaparecerSelects('DivNefCIE')"/>No
	    </td>
	    
	    					<td><div id="DivNefCIE">
					 		<select name="NefCIE" id="NefCIE" class="select"> <!-- onchange="CodigoCieNefPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'N0%' OR ci.codigoCIE LIKE 'N1%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'N0%' OR ci.codigoCIE LIKE 'N1%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoNefritis>>BuscarAntecedentesPersonalesModificacionNefritis " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%>
	    
	    <td>   
	       <input type="radio" name="radPerNefritis" id="Si" onclick="AparecerSelects('DivNefCIE')"/>Si
	       <input type="radio" name="radPerNefritis" id="No" onclick="DesaparecerSelects('DivNefCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivNefCIE" style="display: none;">
					 		<select name="NefCIE" id="NefCIE" class="select"> <!-- onchange="CodigoCieNefPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'N0%' OR ci.codigoCIE LIKE 'N1%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'N0%' OR ci.codigoCIE LIKE 'N1%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoNefritis>>BuscarAntecedentesPersonalesModificacionNefritis " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>
</tr>
     
  
  <tr>
    <td>10. Tumores</td>

		<%if (rs.getString("per_tumores").equals("Si")){%>
		
	    <td>    
	      <input type="radio" name="radPerTumores" id="Si" onclick="AparecerSelects('DivTumPerCIE')" checked="checked"/>Si  
	      <input type="radio" name="radPerTumores" id="No" onclick="DesaparecerSelects('DivTumPerCIE')"/>No
	    </td>
	    
	    					<td><div id="DivTumPerCIE">
					 		<select name="TumPerCIE" id="TumPerCIE" class="select"> <!-- onchange="CodigoCieTumPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoTumores>>BuscarAntecedentesPersonalesModificacionTumores " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%>
	    
	    <td>    
	      <input type="radio" name="radPerTumores" id="Si" onclick="AparecerSelects('DivTumPerCIE')"/>Si  
	      <input type="radio" name="radPerTumores" id="No" onclick="DesaparecerSelects('DivTumPerCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivTumPerCIE" style="display: none;">
					 		<select name="TumPerCIE" id="TumPerCIE" class="select"> <!-- onchange="CodigoCieTumPersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%') AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE (ci.codigoCIE LIKE 'C0%' OR ci.codigoCIE LIKE 'C1%' OR ci.codigoCIE LIKE 'C2%' OR ci.codigoCIE LIKE 'C3%' OR ci.codigoCIE LIKE 'C4%' OR ci.codigoCIE LIKE 'C5%' OR ci.codigoCIE LIKE 'C6%' OR ci.codigoCIE LIKE 'C7%' OR ci.codigoCIE LIKE '%C80' OR ci.codigoCIE LIKE '%C97' OR ci.codigoCIE LIKE 'D1%' OR ci.codigoCIE LIKE 'D2%' OR ci.codigoCIE LIKE 'D3%' OR ci.codigoCIE LIKE 'D4%')");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoTumores>>BuscarAntecedentesPersonalesModificacionTumores " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>
</tr>

  <tr>
    <td>11. TromboFlebitis</td>

<%if (rs.getString("per_trombo_flebitis").equals("Si")){%>
		
	    <td>
	      <input type="radio" name="radPerTromboflebitis" id="Si" onclick="AparecerSelects('DivTroFleCIE')" checked="checked"/>Si
	      <input type="radio" name="radPerTromboflebitis" id="No" onclick="DesaparecerSelects('DivTroFleCIE')"/>No
	    </td>
	    
	    					<td><div id="DivTroFleCIE">
					 		<select name="TroFleCIE" id="TroFleCIE" class="select"> <!-- onchange="CodigoCieTroFlePersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I8%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'I8%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoTromboFlebitis>>BuscarAntecedentesPersonalesModificacionTromboFlebitis " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}else{%>
	    
	    <td>
	      <input type="radio" name="radPerTromboflebitis" id="Si" onclick="AparecerSelects('DivTroFleCIE')"/>Si
	      <input type="radio" name="radPerTromboflebitis" id="No" onclick="DesaparecerSelects('DivTroFleCIE')" checked="checked"/>No
	    </td>
	    
	    					<td><div id="DivTroFleCIE" style="display: none;">
					 		<select name="TroFleCIE" id="TroFleCIE" class="select"> <!-- onchange="CodigoCieTroFlePersonales()"> -->
					 		<%	
					 		ResultSet rs1 = null;
					 		ResultSet rs2 = null;
							 	st = null;
							
							 	try {
							    con=new Conexion();
								st = con.conn.createStatement();
								rs1 = st.executeQuery("SELECT ci.* FROM adm_paciente ap, adm_diagnosticocola ad, cie10 ci WHERE ap.pac_codigo_paciente=ad.cedpac_fk AND ci.codigo=ad.codigoCIE_fk AND ap.pac_codigo_paciente='"+Codpaciente+"' AND ci.codigoCIE LIKE 'I8%' AND ad.observacion='Antecedentes Personales' ORDER BY ad.codigo DESC LIMIT 1");
								if(rs1.next()){
						 	%>	
					    		
					    	<option value="<%=rs1.getString("codigo")%>"><%=rs1.getString("nombre") %>-<%=rs1.getString("codigoCIE") %></option>	
								
							<%}				
								
							rs1.getStatement().close();
						    //con=new Conexion();
							st = con.conn.createStatement();
							rs2 = st.executeQuery("SELECT ci.* FROM cie10 ci WHERE ci.codigoCIE LIKE 'I8%'");
							while(rs2.next()){
							 	%>	
							<option value="<%=rs2.getString("codigo")%>"><%=rs2.getString("nombre") %>-<%=rs2.getString("codigoCIE") %></option>	
								
							<%}	
							rs2.getStatement().close();
								} catch (Exception ex) {
									System.out.println("Error en MetodoTromboFlebitis>>BuscarAntecedentesPersonalesModificacionTromboFlebitis " + ex);
								}
							%>	
								
							</select>
							</div></td>
	    
	    <%}%>
</tr>

<tr>       
    <td>12. Cirug&iacute;a Ginecol&oacute;gica</td>
 
		<%if (rs.getString("per_cirugia_ginecologica").equals("Si")){%>
		
	    <td>
	       <input type="radio" name="radPerCirugiaGinecologica" id="Si" checked="checked"/>Si   
	       <input type="radio" name="radPerCirugiaGinecologica" id="No"/>No
	    </td>
	    
	    <%}else if (rs.getString("per_cirugia_ginecologica").equals("")){%>
	    
	    <td>(N/A)</td>
	    <td></td>
	    
	    <%}else if (rs.getString("per_cirugia_ginecologica").equals("No")){%>
	    
	    <td>
	       <input type="radio" name="radPerCirugiaGinecologica" id="Si"/>Si   
	       <input type="radio" name="radPerCirugiaGinecologica" id="No" checked="checked"/>No
	    </td>
	    
	    <%}%>

  </tr>
 
<tr>    
    <td>13. Citolog&iacute;as Previas</td>    

		<%if (rs.getString("per_citologias_previas").equals("Si")){%>  
		  
	    <td>       
	      <input type="radio" name="radPerCitologiasPrevias" id="Si" checked="checked"/>Si    
	      <input type="radio" name="radPerCitologiasPrevias" id="No"/>No
	    </td>
	    
	    <%}else if (rs.getString("per_citologias_previas").equals("")){%>
	    
	    <td>(N/A)</td>
	    <td></td>
	    
	    <%}else if (rs.getString("per_citologias_previas").equals("No")){%>
	    
	    <td>       
	      <input type="radio" name="radPerCitologiasPrevias" id="Si"/>Si    
	      <input type="radio" name="radPerCitologiasPrevias" id="No" checked="checked"/>No
	    </td>
	    
	    <%}%>  
  </tr>
  
    
<tr>    
    <td>14. H&aacute;bito Fumar</td>

<%if (rs.getString("per_habito_fumar").equals("Si")){%>
		
	    <td>
	      <input type="radio" name="radPerHabitoFumar" id="Si" checked="checked"/>Si
	      <input type="radio" name="radPerHabitoFumar" id="No"/>No
	    </td>
	    
	    <%}else{%>
	    
	    <td>
	      <input type="radio" name="radPerHabitoFumar" id="Si"/>Si
	      <input type="radio" name="radPerHabitoFumar" id="No" checked="checked"/>No
	    </td>
  </tr>
 

		<%}				
				}
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoAntecedentesPersonales>>BuscarAntecedentesPersonalesModificacion " + ex);
		}
		%>



			<!--Consulta Para Saber El Genero  -->
		<% 	 	rs = null;
	 		 	st = null;
	
	 			try {
			
	    		con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT genero FROM adm_paciente ap, pyp_informe_diagnostico pid WHERE ap.pac_codigo_paciente = pid.id_paciente AND pid.id_informe_diagnostico_pyp="+CodInfPYPPlanFam);
				
				if(rs.next()){
		if(rs.getString("genero").equals("Masculino")){ %>
<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonAntecedentes" value="Guardar Antecedentes" onclick="GuardarAntecedentesMasculino()"/></td>
</tr>    
		<%}else{ %>
<tr>
<td><p>&nbsp;</p><input type="submit" id="BotonAntecedentes" value="Guardar Antecedentes" onclick="GuardarAntecedentesFemenino()"/></td>
</tr>     

		<%}				
				}
		rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error en MetodoGenero>>BuscarBotonGuardarAntecedentesModificacion " + ex);
		}
		%>  
 
    
</table>
</td>
</tr>
</table>  
 

<h3>HISTORIAS</h3>
<!-- Tabla Embarazos Anteriores -->
<table style="width: 100%">
<%		rs = null;
			 	st = null;
		 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_historias_embarazos_anteriores WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){%>
<tr>
  <td id="historia_embarazos"> 
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
     <option value="<%=rs.getString("embarazo_antes")%>"><%=rs.getString("embarazo_antes")%></option>
     	<%		if (rs.getString("embarazo_antes").equals("Si")){%>
				<option value="No">No</option>	
		<%}		if (rs.getString("embarazo_antes").equals("No")){%>
				<option value="Si">Si</option>	
		<%}%>
     </select>
   </td>
</tr>

<tr>
<td><br>Terminaci&oacute;n del Embarazo
<br>
	<input type="text" name="terminaEmbarazo" id="datepickerTerminacionEmbarazo" value="<%=rs.getString("terminacion_embarazo")%>">
</td>
<td><br>Meses de Gestaci&oacute;n
<br>
	<input type="text" name="mesesGestacion" id="mesesGestacion" onkeypress="javascript:return validarNro(event)" value="<%=rs.getString("meses_gestacion") %>">
</td>
<td><br>Tipo de Parto
<br>
<select name="tipoParto" id="tipoParto" >
<option value="<%=rs.getString("tipo_parto")%>"><%=rs.getString("tipo_parto")%></option>
		<%		if (rs.getString("tipo_parto").equals("Cesarea")){%>
				<option value="Vaginal">Vaginal</option>	
		<%}		if (rs.getString("tipo_parto").equals("Vaginal")){%>
				<option value="Cesarea">Cesarea</option>
		<%}		if (rs.getString("tipo_parto").equals("N/A")){%>
				<option value="Cesarea">Cesarea</option>
				<option value="Vaginal">Vaginal</option>
		<%}%>
</select>
</td>

<td><br>Aborto
<br>
<select name="aborto" id="aborto" >
<option value="<%=rs.getString("aborto")%>"><%=rs.getString("aborto")%></option>
		<%		if (rs.getString("aborto").equals("Si")){%>
				<option value="No">No</option>	
		<%}		if (rs.getString("aborto").equals("No")){%>
				<option value="Si">Si</option>
		<%}		if (rs.getString("aborto").equals("N/A")){%>
				<option value="Si">Si</option>
				<option value="No">No</option>
		<%}%>
</select>
</td>
</tr>

</table>

<br>Escriba otros antecedentes
<table>
<tr>
<td>
<textarea name="otrosAntecedentes" id="otrosAntecedentes" rows="5" cols="130" onkeypress="javascript:return soloLetras(event)"><%=rs.getString("otros_antecedentes")%></textarea>
</td>
</tr>  
</table>
  
<%}else{ %>
<td>ESTA SECCION NO APLICA PARA GENERO MASCULINO</td>

<%	}		
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>  
</td>
</tr>
 
<!-- Tabla Historias Menstruales -->
<tr>
<td id="historias_menstruales">
<table style="width: 100%">
<tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">HISTORIAS MENSTRUALES</td>
    <p>&nbsp;</p>
</tr>
</table>


<table style="width: 100%">
		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_historias_menstruales WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){ %>
<tr>
<td><br>Ciclos Regulares</td>
<td><br>Ultima Menstruacion</td>
<td><br>Trastornos Menstruales</td>
</tr>
<tr>
		<%if (rs.getString("ciclos_regulares").equals("No")){%>
		<td>
		    <input type="radio" name="radCiclosRegulares" id="Si">Si
		<br><input type="radio" name="radCiclosRegulares" id="No" checked="checked">No
		</td>
		<%}else{%>
	    <td>
	      <input type="radio" name="radCiclosRegulares" id="Si" checked="checked"/>Si
	      <input type="radio" name="radCiclosRegulares" id="No"/>No
	    </td>
	    <%}%>
		<td>
		<input type="text" name="ultimaMenstruacion" id="datepickerUltimaMenstruacion" value="<%=rs.getString("ultima_menstruacion")%>">
		</td>
		<%if (rs.getString("trastornos_menstruales").equals("No")){%>		
		<td>
		    <input type="radio" name="radTrasMenstruales" id="siTrasMenstruales" onclick="clicksiTrasMenstruales()" value="Si">Si 
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    
		    Tipo&nbsp;
		    <input type="text" name="tipo" id="tipoTrasMenstruales" disabled="disabled" onkeypress="javascript:return soloLetras(event)">
		<br><input type="radio" name="radTrasMenstruales" id="noTrasMenstruales" onclick="tipo.disabled=true" value="No" checked="checked">No
		</td>
		<%}else{%>
		<td>
		    <input type="radio" name="radTrasMenstruales" id="siTrasMenstruales" onclick="clicksiTrasMenstruales()" value="Si" checked="checked">Si 
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    
		    Tipo&nbsp;
		    <input type="text" name="tipo" id="tipoTrasMenstruales" value="<%=rs.getString("trastornos_menstruales") %>" onkeypress="javascript:return soloLetras(event)">
		<br><input type="radio" name="radTrasMenstruales" id="noTrasMenstruales" onclick="tipo.disabled=true" value="No">No
		</td>
		<%} %><!-- Finaliza Validacion Genero Hist. Menstruales -->
		
		<%}else{ %>
<td>ESTA SECCION NO APLICA PARA GENERO MASCULINO</td>
<% }
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}%>
</tr>
</table>
</td>
</tr>

<!-- Tabla Historia Conceptual -->
<tr>
  <td id="historia_conceptual">
  
<table style="width: 100%">
 <tr>
    <td id="cabecera2" class="style11" colspan="2" align="center">HISTORIA CONCEPTUAL</td>
  	<p>&nbsp;</p>
  </tr>
 </table>
 
 		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_historias_conceptuales WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>
 		
<table style="width: 100%"> 
<tr>
   <td colspan="4">Usted ha usado alg&uacute;n metodo anticonceptivo antes de la consulta
 		<%if (rs.getString("metodos_antes_consulta").equals("No")){%>
       &nbsp;
     <input type="radio" name="radMetodoAnticonceptivo" id="Si" onClick="clicksiMetodosAntesConsulta()">Si
     <input type="radio" name="radMetodoAnticonceptivo" id="No" checked="checked" 
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
			<%}else{%>
 <tr>
 <td>
&nbsp;
     <input type="radio" name="radMetodoAnticonceptivo" id="Si" onClick="clicksiMetodosAntesConsulta()" checked="checked">Si
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
   <td><input type="text" name="txt_metUtilizado" id="txt_metUtilizado" size="10" value="<%=rs.getString("metodos_utilizados") %>" onkeypress="javascript:return soloLetras(event)"/></td>
   <td><input type="text" name="txt_desde" id="txt_desde" value="<%=rs.getString("tiempo_utilizacion_desde") %>" size="10"/></td>
   <td><input type="text" name="txt_hasta" id="txt_hasta" value="<%=rs.getString("tiempo_utilizacion_hasta") %>" size="10"/></td>
   <td><select name="preescrito" id="preescrito" onchange="selectEntidad()">
		<option value="<%=rs.getString("seleccion_preescrito") %>"><%=rs.getString("seleccion_preescrito") %></option>
		<%if(rs.getString("seleccion_preescrito").equals("Entidad")){ %>
		<option value="Profamiia">Profamilia</option>
		<option value="Otro">Otro</option>
		<option value="Ninguno" >Ninguno</option>
		<%}if(rs.getString("seleccion_preescrito").equals("Profamilia")){ %>
		<option value="Entidad">Entidad</option>
		<option value="Otro">Otro</option>
		<option value="Ninguno" >Ninguno</option>
		<%}if(rs.getString("seleccion_preescrito").equals("Otro")){ %>
		<option value="Entidad">Entidad</option>
		<option value="Profamilia">Profamilia</option>
		<option value="Ninguno" >Ninguno</option>
		<%}if(rs.getString("seleccion_preescrito").equals("Ninguno")){ %>
		<option value="Entidad">Entidad</option>
		<option value="Profamilia">Profamilia</option>
		<option value="Otro">Otro</option>
		<%}%>
	   </select>
	   &nbsp;
       <input type="text" name="txt_preescrito" id="txt_preescrito" size="10" value="<%=rs.getString("preescrito") %>" onkeypress="javascript:return soloLetras(event)"/>
   </td>
    
   <td><input type="text" name="txt_problemas" id="txt_problemas" size="25" value="<%=rs.getString("problemas") %>" onkeypress="javascript:return soloLetras(event)"/></td>
</tr>




<%} %><!-- Finaliza Validacion Genero Hist. Conceptuales -->

<tr>
<td><p>&nbsp;</p><input type="submit" value="Guardar Historias" onclick="GuardarHistorias()"/></td>
</tr>

<%}else{ %>
<tr><td>ESTA SECCION NO APLICA PARA GENERO MASCULINO</td></tr>
		<%
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

</table>

</td>
</tr>
</table>



</div> <!-- Finaliza div Accordion General -->
</div> <!-- Finaliza Primera Pestaña -->  



<div id="formulario_planificacion_familiar"> <!-- Segunda Pestaña --> 

	<div id="accordion2">
	<h3>RIESGO REPRODUCTIVO</h3>
	
<!-- Tabla Planificacion Familiar --> 
<table style="width: 100%">
<tr>
 <td id="planificacion_familiar">
 <table style="width:100%">
<tr>
  <td id="cabecera2" class="style11" colspan="2" align="center"><p>PLANIFICACION FAMILIAR INDICE DE RIESGO REPRODUCTIVO</p>
  <p>ANEXO 1</p></td>
</tr>
</table>



<table style="width:100%">
<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_planificacion_familiar WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
					if(rs.getString("edad_mujer").equals("")){ 
		%>
		
				No Aplica Para Genero Masculino
		<%}else{ %>
<form name=general>

<br>



<td colspan="1"><label for="txt_fecha_visita">Fecha de la Visita:</label>

<br>
<input type="text" name="txt_fecha_visita" id="datepickerFechaVisita" size="20" value="<%=rs.getString("fecha_visita") %>"/></td>


<td colspan="1"><label>Fecha &uacute;ltima Menstruaci&oacute;n:</label>
<br>
<input type="text" name="txt_fecha_ult_menstruacion" id="datepickerFechaUltMenstruacion" size="20" value="<%=rs.getString("fecha_ult_menstruacion") %>"/></td>

</tr>


<p></p>

<tr>
<td align="left"><br>Edad de la Mujer:
<br>
<select name="edadMujer" id="edadMujer">

<option value="<%=rs.getString("edad_mujer")%>"><%=rs.getString("edad_mujer")%></option>
		
		<%		if (rs.getString("edad_mujer").equals("17 años o menos")){%>
				<option value="0">18 o 34 a&ntilde;os</option>
				<option value="2">35 a&ntilde;os o m&aacute;s</option>	
		<%}		if (rs.getString("edad_mujer").equals("18 o 34 años")){%>
				<option value="2">17 a&ntilde;os o menos</option>
				<option value="2">35 a&ntilde;os o m&aacute;s</option>
	    <%}     if (rs.getString("edad_mujer").equals("35 años o más")){%>
			    <option value="2">17 a&ntilde;os o menos</option>
			    <option value="0">18 o 34 a&ntilde;os</option>
		<%}%>
</select></td>

<td height="31" align="left"><br>Pariedad: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>
<select name="pariedad" id="pariedad">

<option value="<%=rs.getString("pariedad")%>"><%=rs.getString("pariedad")%></option>
		
		<%		if (rs.getString("pariedad").equals("Nilipara")){%>
				<option value="0">1-3 partos</option>
				<option value="0">4 o m&aacute;s partos</option>	
		<%}		if (rs.getString("pariedad").equals("1-3 partos")){%>
				<option value="0">Nilipara</option>
				<option value="0">4 o m&aacute;s partos</option>
	    <%}     if (rs.getString("pariedad").equals("4 o más partos")){%>
			    <option value="0">Nilipara</option>
			    <option value="0">1-3 partos</option>
		<%}%>
</select></td>
</tr>

<tr>
<td align="left"><br>Socio Econ&oacute;mico:
<br>
<select name="socioEconomico" id="socioEconomico">

<option value="<%=rs.getString("socio_economico")%>"><%=rs.getString("socio_economico")%></option>
		
		<%		if (rs.getString("socio_economico").equals("Tugurio")){%>
				<option value="2">Ni&ntilde;os de 2 a&ntilde;os desnutridos</option>
				<option value="2">Muerte de ni&ntilde;os menores de 2 a&ntilde;os</option>
				<option value="0">Ninguna de las Anteriores</option>	
		<%}		if (rs.getString("socio_economico").equals("Niños de 2 años desnutridos")){%>
				<option value="2">Tugurio</option>
				<option value="2">Muerte de ni&ntilde;os menores de 2 a&ntilde;os</option>
				<option value="0">Ninguna de las Anteriores</option>
	    <%}     if (rs.getString("socio_economico").equals("Muerte de niños menores de 2 años")){%>
			    <option value="2">Tugurio</option>
			    <option value="2">Ni&ntilde;os de 2 a&ntilde;os desnutridos</option>
			    <option value="0">Ninguna de las Anteriores</option>
		<%}     if (rs.getString("socio_economico").equals("Ninguna de las Anteriores")){%>
			    <option value="2">Tugurio</option>
			    <option value="2">Ni&ntilde;os de 2 a&ntilde;os desnutridos</option>
			    <option value="2">Muerte de ni&ntilde;os menores de 2 a&ntilde;os</option>
		<%}%>
</select></td>

<td align="left"><br>Intervalo Embarazos:
<br>
<select name="intervaloEmbarazo" id="intervaloEmbarazo">

<option value="<%=rs.getString("intervalo_embarazo")%>"><%=rs.getString("intervalo_embarazo")%></option>
		
		<%		if (rs.getString("intervalo_embarazo").equals("Menores de 12 años")){%>
				<option value="1">1 a 2 a&ntilde;os</option>
				<option value="0">M&aacute;s de 2 a&ntilde;os</option>	
				<option value="0">Ninguna de las Anteriores</option>
		<%}		if (rs.getString("intervalo_embarazo").equals("1 a 2 años")){%>
				<option value="4">Menores de 12 a&ntilde;os</option>
				<option value="0">M&aacute;s de 2 a&ntilde;os</option>
				<option value="0">Ninguna de las Anteriores</option>
	    <%}     if (rs.getString("intervalo_embarazo").equals("Más de 2 años")){%>
			    <option value="4">Menores de 12 a&ntilde;os</option>
			    <option value="1">1 a 2 a&ntilde;os</option>
			    <option value="0">Ninguna de las Anteriores</option>
		<%}     if (rs.getString("intervalo_embarazo").equals("Ninguna de las Anteriores")){%>
			    <option value="4">Menores de 12 a&ntilde;os</option>
			    <option value="1">1 a 2 a&ntilde;os</option>
			    <option value="0">M&aacute;s de 2 a&ntilde;os</option>	    
		<%}%>
</select></td>
</tr>


<tr>
<th align="left"><br>Antecedentes:</th>

<tr>
<td>
Aborto</br>

<%if (rs.getString("aborto").equals("3 o mas")){%>
		
	       <input type="radio" value="0" name="radAborto" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radAborto" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radAborto" id="3 o mas" checked="checked"/> 3 o m&aacute;s<br/>
	    
	    
<%}if (rs.getString("aborto").equals("1 o 2")){%>
	    
	       <input type="radio" value="0" name="radAborto" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radAborto" id="1 o 2" checked="checked"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radAborto" id="3 o mas"/> 3 o m&aacute;s<br/>
	       
<%}if (rs.getString("aborto").equals("0")){%>

		   <input type="radio" value="0" name="radAborto" id="0" checked="checked"/> 0<br/>
	       <input type="radio" value="1" name="radAborto" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radAborto" id="3 o mas"/> 3 o m&aacute;s<br/>
	    </td>
	    
<%}%>

<td>
Cesarea</br>

<%if (rs.getString("cesarea").equals("3 o mas")){%>
		
	       <input type="radio" value="0" name="radCesarea" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radCesarea" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radCesarea" id="3 o mas" checked="checked"/> 3 o m&aacute;s<br/>
	    
	    
<%}if (rs.getString("cesarea").equals("1 o 2")){%>
	    
	       <input type="radio" value="0" name="radCesarea" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radCesarea" id="1 o 2" checked="checked"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radCesarea" id="3 o mas"/> 3 o m&aacute;s<br/>
	       
<%}if (rs.getString("cesarea").equals("0")){%>
	    
	       <input type="radio" value="0" name="radCesarea" id="0" checked="checked"/> 0<br/>
	       <input type="radio" value="1" name="radCesarea" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radCesarea" id="3 o mas"/> 3 o m&aacute;s<br/>
	    </td>
	    
<%}%>
</tr>

<tr>
<td>
<br>Mortinato</br>

<%if (rs.getString("mortinato").equals("3 o mas")){%>
		
	       <input type="radio" value="0" name="radMortinato" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radMortinato" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radMortinato" id="3 o mas" checked="checked"/> 3 o m&aacute;s<br/>
	    
	    
<%}if (rs.getString("mortinato").equals("1 o 2")){%>
	    
	       <input type="radio" value="0" name="radMortinato" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radMortinato" id="1 o 2" checked="checked"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radMortinato" id="3 o mas"/> 3 o m&aacute;s<br/>
	       
<%}if (rs.getString("mortinato").equals("0")){%>
	    
	       <input type="radio" value="0" name="radMortinato" id="0" checked="checked"/> 0<br/>
	       <input type="radio" value="1" name="radMortinato" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radMortinato" id="3 o mas"/> 3 o m&aacute;s<br/>
	    </td>
	    
	    <%}%>

<td>
<br>Prematuro</br>

<%if (rs.getString("prematuro").equals("3 o mas")){%>
		
	       <input type="radio" value="0" name="radPrematuro" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radPrematuro" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radPrematuro" id="3 o mas" checked="checked"/> 3 o m&aacute;s<br/>
	    
	    
<%}if (rs.getString("prematuro").equals("1 o 2")){%>
	    
	       <input type="radio" value="0" name="radPrematuro" id="0"/> 0<br/>
	       <input type="radio" value="1" name="radPrematuro" id="1 o 2" checked="checked"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radPrematuro" id="3 o mas"/> 3 o m&aacute;s<br/>
	       
<%}if (rs.getString("prematuro").equals("0")){%>
	    
	       <input type="radio" value="0" name="radPrematuro" id="0" checked="checked"/> 0<br/>
	       <input type="radio" value="1" name="radPrematuro" id="1 o 2"/> 1 o 2<br/>
	       <input type="radio" value="1" name="radPrematuro" id="3 o mas"/> 3 o m&aacute;s<br/>
	    </td>
	    
	    <%}%>
  
</tr>
</tr>


<tr>
<td align="left"><br>Patologia Actual:
<select name="patologiaActual" id="patologiaActual">

<option value="<%=rs.getString("patologia_actual")%>"><%=rs.getString("patologia_actual")%></option>
		
		<%		if (rs.getString("patologia_actual").equals("Anemia severa (Menos de 7 GMS de Hb)")){%>
				<option value="4">Enfermedad Renal</option>
				<option value="4">Hipertension</option>
				<option value="4">Cardiopatias</option>
				<option value="0">Ninguna de las Anteriores</option>	
		<%}		if (rs.getString("patologia_actual").equals("Enfermedad Renal")){%>
				<option value="4">Anemia severa (Menos de 7 GMS de Hb)</option>
				<option value="4">Hipertension</option>
				<option value="4">Cardiopatias</option>
				<option value="0">Ninguna de las Anteriores</option>
	    <%}     if (rs.getString("patologia_actual").equals("Hipertension")){%>
			    <option value="4">Anemia severa (Menos de 7 GMS de Hb)</option>
			    <option value="4">Enfermedad Renal</option>
			    <option value="4">Cardiopatias</option>
			    <option value="0">Ninguna de las Anteriores</option>
		<%}     if (rs.getString("patologia_actual").equals("Cardiopatias")){%>
			    <option value="4">Anemia severa (Menos de 7 GMS de Hb)</option>
			    <option value="4">Enfermedad Renal</option>
			    <option value="4">Hipertension</option>
			    <option value="0">Ninguna de las Anteriores</option>
		<%}     if (rs.getString("patologia_actual").equals("Ninguna de las Anteriores")){%>
			    <option value="4">Anemia severa (Menos de 7 GMS de Hb)</option>
			    <option value="4">Enfermedad Renal</option>
			    <option value="4">Hipertension</option>
			    <option value="0">Cardiopatias</option>	   
		<%}%>
</select></td>
</tr>

<!-- 
<tr><td><br>
<input type="button" value="Calcular" onclick="Resultado(this)"/>
</td></tr>
 -->

<%} %><!-- Finaliza Validacion Masculino -->
<%  
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

</form>
</table>


<!-- Tabla Riesgo Reproductivo -->
<tr>
<td id="riesgo_reproductivo">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">RIESGO REPRODUCTIVO</td>
<p>&nbsp;</p>
</tr>
</table>

<table style="width: 100%">

<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_riesgo_reproductivo WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>
		
		<%if (rs.getString("riesgo_reproductivo").equals("")){ %>
				No Aplica Para Genero Masculino
		<%}else{ %>
		

<tr>
<td><br>Riesgo Reproductivo


<input type="text" name="riesgoReproductivo" id="riesgoReproductivo" value="<%=rs.getString("riesgo_reproductivo") %>"/>
</td>
</tr>

<tr>
<td><p>&nbsp;</p><br><input type="submit" value="Guardar Riesgo Reproductivo" onclick="GuardarRiesgoReproductivo()"/></td>
</tr>

<%} %>


</table>



		<%  
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

</td>
</tr>





</td>
</tr>
</table>


<h3>EX&Aacute;MENES</h3>
<!-- Tabla Examen Fisico -->
<table style="width: 100%">
<tr>
 <td id="examen_fisico">
 <table style="width: 100%">
<tr>
   <td id="cabecera2" class="style11" colspan="2" align="center">EXAMEN FISICO</td>
</tr>
</table>


		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_examen_fisico WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>
		
		<%if (rs.getString("mamas_normales").equals("")){%>
				No Aplica Para Genero Masculino
		<%}else{ %>

<table style="width: 100%">
<tr>
  <td align="left">Mamas Normales</td>
		<%if (rs.getString("mamas_normales").equals("No")){%>

		<td>
		    <input type="radio" name="radMamasNormales" id="Si"/>Si
		    <input type="radio" name="radMamasNormales" id="No" checked="checked"/>No 
		</td>
		
		<%}else if (rs.getString("mamas_normales").equals("Si")){%>
	    
	    <td>
		    <input type="radio" name="radMamasNormales" id="Si" checked="checked"/>Si
		    <input type="radio" name="radMamasNormales" id="No"/>No 
		</td>
	    
	    <%}%>  

    
  <td align="left">Utero y Anexos Normales</td>

		<%if (rs.getString("utero_anexos_normales").equals("No")){%>
		
		<td>
			<input type="radio" name="radUteAneNormales" id="Si"/>Si
	      	<input type="radio" name="radUteAneNormales" id="No" checked="checked"/>No
	  	</td>
	  	
	  	<%}else	if (rs.getString("utero_anexos_normales").equals("Si")){%>
	  	
	  	<td>
			<input type="radio" name="radUteAneNormales" id="Si" checked="checked"/>Si
	      	<input type="radio" name="radUteAneNormales" id="No"/>No
	  	</td>
	  	
	  	<%}%>

</tr>

<tr>
  <td align="left">Signos De Embarazo</td>

		<%if (rs.getString("signos_embarazo").equals("No")){%>
		
  		<td>
      		<input type="radio" name="radSigEmbarazo" id="Si"/>Si
      		<input type="radio" name="radSigEmbarazo" id="No" checked="checked"/>No
  		</td>
  		
  		<%}else if (rs.getString("signos_embarazo").equals("Si")){%>
  		
  		<td>
      		<input type="radio" name="radSigEmbarazo" id="Si" checked="checked"/>Si
      		<input type="radio" name="radSigEmbarazo" id="No"/>No
  		</td>
  		
  		<%}%>

 
  <td align="left">Miembros Inferiores: Varices</td>

<%if (rs.getString("varices").equals("No")){%>
		
  		<td>
      		<input type="radio" name="radMiemInfVarices" id="Si"/>Si
      		<input type="radio" name="radMiemInfVarices" id="No" checked="checked"/>No
  		</td>
  		
  		<%}else{%>
  		
  		<td>
      		<input type="radio" name="radMiemInfVarices" id="Si" checked="checked"/>Si
      		<input type="radio" name="radMiemInfVarices" id="No"/>No
  		</td>
  		
  		<%}%>

</tr>

<tr>
  <td align="left">Cervix Normal</td>

		<%if (rs.getString("cervis_normal").equals("No")){%>
		
  		<td>
      		<input type="radio" name="radCerNormal" id="Si"/>Si
      		<input type="radio" name="radCerNormal" id="No" checked="checked"/>No
  		</td>
  		
  		<%}else if (rs.getString("cervis_normal").equals("Si")){%>
  		
  		<td>
      		<input type="radio" name="radCerNormal" id="Si" checked="checked"/>Si
      		<input type="radio" name="radCerNormal" id="No"/>No
  		</td>
  		
  		<%}%>

 
  <td align="left">Edemas</td>

		<%if (rs.getString("edemas").equals("No")){%>
		
  		<td>
      		<input type="radio" name="radEdemas" id="Si"/>Si
      		<input type="radio" name="radEdemas" id="No" checked="checked"/>No
  		</td>
  		
  		<%}else{%>
  		
  		<td>
      		<input type="radio" name="radEdemas" id="Si" checked="checked"/>Si
      		<input type="radio" name="radEdemas" id="No"/>No
  		</td>
  		
  		<%}%>

</tr>

<tr>
  <td align="left">Peso En KG</td>
  <td>
      <input type="text" name="peso" id="peso" onkeypress="javascript:return validarNro(event)" value="<%=rs.getString("peso") %>"/>
  </td>
  
  <td align="left">Tension Arterial</td>
  <td>
      <input type="text" name="tenArterial" id="tenArterial" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString("tension_arterial") %>"/>
  </td>
</tr>

<tr>
  <td align="left">Numero de Semanas de Embarazo</td>
  <td>
     <select name="numSemanas" id="numSemanas">
     	<option value="<%=rs.getString("num_semanas")%>"><%=rs.getString("num_semanas")%></option>
     	<%
			for (int x=1; x<=42; x++){
				out.println("<option value="+x+">"+x+"</option>");
			};
  		%>
	 </select>
  </td>
      
  <td align="left">Otros Hallazgos(Cuales)</td>
  
  <td>
      <input type="text" name="hallazgos" id="hallazgos" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString("otros_hallazgos") %>"/>
  </td>
</tr>
</table>

 
<br>
 Observaciones:<br> 
<textarea name="observaciones" id="observaciones" rows="8" cols="50" style="width:100%"><%=rs.getString("observaciones") %></textarea>

		<%} %><!-- Finaliza Validacion Vacio -->
	
		<% 
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

</td>
</tr>



<!-- Tabla Examenes Practicados -->
<tr>
 <td id="examenes_practicados">
 <table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">EXAMENES PRACTICADOS</td>
<p>&nbsp;</p>
</tr>
</table>

		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_examenes_practicados WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>
		
		<%if (rs.getString("prueba_embarazo").equals("")){ %>
		No Aplica Para Genero Masculino
		<%}else{ %>

<table style="width: 100%">
<tr>
<td align="left">Prueba De Embarazo</td>

		<%if (rs.getString("prueba_embarazo").equals("No")){%>

		<td>
		<form>
		<input type="radio" name="radPruEmbarazo" id="siPruEmbarazo" onclick="clicksiPruebaEmbarazo()"/>Si
		<input type="radio" name="radPruEmbarazo" id="noPruEmbarazo" onclick="resPruEmbarazo.disabled=true" checked="checked"/>No
		</td>
		
		<td align="left">Resultados</td>
		<td>
		<input type="text" name="resPruEmbarazo" id="resPruEmbarazo" disabled="disabled"/>
		</form>
		</td>
		
		<%}else{%>
		
		<td>
		<form>
		<input type="radio" name="radPruEmbarazo" id="siPruEmbarazo" onclick="clicksiPruebaEmbarazo()" checked="checked"/>Si
		<input type="radio" name="radPruEmbarazo" id="noPruEmbarazo" onclick="resPruEmbarazo.disabled=true"/>No
		</td>

		<td align="left">Resultados</td>
		<td>
		<input type="text" name="resPruEmbarazo" id="resPruEmbarazo" value="<%=rs.getString("prueba_embarazo") %>"/>
		</form>
		</td>
		
		<%}%>
</tr>
	
<tr>
<td align="left">Citologia Cervico Vaginal</td>
		
		<%if (rs.getString("citologia_cervico_vaginal").equals("No")){%>

		<td>
		<form>
		<input type="radio" name="radCitCerVaginal" id="siCitCerVaginal" onclick="clicksiCitologiaCervicoVaginal()"/>Si
		<input type="radio" name="radCitCerVaginal" id="noCitCerVaginal" onclick="resCitCerVaginal.disabled=true" checked="checked"/>No
		</td>

		<td align="left">Resultados</td>
		<td>
		<input type="text" name="resCitCerVaginal" id="resCitCerVaginal" disabled="disabled"/>
		</form>
		</td>

		<%}else{%>

		<td>
		<form>
		<input type="radio" name="radCitCerVaginal" id="siCitCerVaginal" onclick="clicksiCitologiaCervicoVaginal()" checked="checked"/>Si
		<input type="radio" name="radCitCerVaginal" id="noCitCerVaginal" onclick="resCitCerVaginal.disabled=true"/>No
		</td>
		
		<td align="left">Resultados</td>
		<td>
		<input type="text" name="resCitCerVaginal" id="resCitCerVaginal" value="<%=rs.getString("citologia_cervico_vaginal") %>"> 
		</form>
		</td>
		
		<%}%>
</tr>
		
<tr>
<td align="left">Frotis Vaginal</td>
		
		<%if (rs.getString("frotis_vaginal").equals("No")){%>

		<td>
		<form>
		<input type="radio" name="radFroVaginal" id="siFroVaginal" onclick="clicksiFrotisVaginal()"/>Si
		<input type="radio" name="radFroVaginal" id="noFroVaginal" onclick="resFroVaginal.disabled=true" checked="checked"/>No
		</td>
		
		<td align="left">Resultados</td>
		<td>
		<input type="text" name="resFroVaginal" id="resFroVaginal" disabled="disabled"/>
		</form>
		</td>
		
		<%}else{%>
		
		<td>
		<form>
		<input type="radio" name="radFroVaginal" id="siFroVaginal" onclick="clicksiFrotisVaginal()" checked="checked"/>Si
		<input type="radio" name="radFroVaginal" id="noFroVaginal" onclick="resFroVaginal.disabled=true"/>No
		</td>
		
		<td align="left">Resultados</td>
		<td>
		<input type="text" name="resFroVaginal" id="resFroVaginal" value="<%=rs.getString("frotis_vaginal") %>"/>
		</form>
		</td>
<%} %>
</tr>

<tr>
<td align="left">Otros</td>
<td>
<input type="text" name="otrosExaPracticados" id="otrosExaPracticados" value="<%=rs.getString("otros") %>"/>
</td>
</tr>
		
</table>

<tr>
<td><p>&nbsp;</p><input type="submit" value="Guardar Examenes" onclick="GuardarExamenes()"/></td>
</tr>

		<%} %>
						
		<%  
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>




</td>
</tr>
</table>



<h3>M&Eacute;TODOS</h3>
<!-- Tabla Metodo Reproductivo -->
<table style="width: 100%">

<tr>
<td id="metodo_reproductivo">
<table style="width: 100%">
<tr>
   <td id="cabecera2" class="style11" colspan="2" align="center">METODO REPRODUCTIVO</td>
</tr>
</table>

<table style="width: 100%">

		<% 	 

		rs = null;
	 	st = null;
	
	 	try {
			
	    con=new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT * FROM pyp_metodo_reproductivo WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
		if(rs.next()){
		%>
		
		<%		if (rs.getString("uso_pildoras").equals("")){%>
				No Aplica Para Genero Masculino
		<%}else{%>

<tr>
<td width="10%">Pildoras</td>
<td>




<select name="pildoras" id="pildoras" onchange="selectPildoras()">

			
			<option value="<%=rs.getString("uso_pildoras")%>"><%=rs.getString("uso_pildoras")%></option>
			
	<%		if (rs.getString("uso_pildoras").equals("Si")){%>
				<option value="No">No</option>	
	<%		}
			if (rs.getString("uso_pildoras").equals("No")){%>
				<option value="Si">Si</option>		
	<%}%>
</select>

<%}%><!-- Finaliza Uso Pildoras Vacio -->

</td>

<% if (rs.getString("cuales_pildoras").equals("")){ %>

<%}else{ %>
<td>
Cu&aacute;les:<input type="text" name="cualPildoras" id="cualPildoras" disabled="disabled" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString("cuales_pildoras") %>">
</td>
<%} %>

<% if (rs.getString("fecha_pildoras").equals("")){ %>

<%}else{ %>
<td>
Fecha:<input type="text" name="fechaPildoras" id="datepickerPildoras" disabled="disabled" value="<%=rs.getString("fecha_pildoras") %>">
</td>
<%} %>
</tr>


<%		if (rs.getString("uso_diu").equals("")){%>
				
		<%}else{%>

<tr>
<td width="10%">DIU</td>
<td> 

<select name="diu" id="diu" onchange="selectDIU()">

<option value="<%=rs.getString("uso_diu")%>"><%=rs.getString("uso_diu")%></option>
			
	<%		if (rs.getString("uso_diu").equals("Si")){%>
				<option value="No">No</option>	
	<%		}
			if (rs.getString("uso_diu").equals("No")){%>
				<option value="Si">Si</option>
	<%}%>
</select>

	<%}%><!-- Finaliza Uso DIU Vacio -->

</td>


<% if (rs.getString("cuales_diu").equals("")){ %>

<%}else{ %>
<td>
Cu&aacute;les:<input type="text" name="cualDIU" id="cualDIU" disabled="disabled" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString("cuales_diu") %>">
</td>
<%} %>


<% if (rs.getString("fecha_diu").equals("")){ %>

<%}else{ %>
<td>
Fecha:<input type="text" name="fechaDIU" id="datepickerDIU" disabled="disabled" value="<%=rs.getString("fecha_diu") %>">
</td>
<%} %>
</tr>


<%		if (rs.getString("uso_inyectables").equals("")){%>
				
		<%}else{%>

<tr>
<td width="10%">Inyectables</td>
<td>  

<select name="inyectables" id="inyectables" onchange="selectInyectables()">

<option value="<%=rs.getString("uso_inyectables")%>"><%=rs.getString("uso_inyectables")%></option>
			
	<%		if (rs.getString("uso_inyectables").equals("Si")){%>
				<option value="No">No</option>	
	<%		}
			if (rs.getString("uso_inyectables").equals("No")){%>
				<option value="Si">Si</option>
	<%}%>
</select>

<%}%><!-- Finaliza Uso Inyectables Vacio -->
</td>


<% if (rs.getString("cuales_inyectables").equals("")){ %>

<%}else{ %>
<td>
Cu&aacute;les:<input type="text" name="cualInyectables" id="cualInyectables" disabled="disabled" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString("cuales_inyectables") %>">
</td>
<%} %>


<% if (rs.getString("fecha_inyectables").equals("")){ %>

<%}else{ %>
<td>
Fecha:<input type="text" name="fechaInyectables" id="datepickerInyectables" disabled="disabled" value="<%=rs.getString("fecha_inyectables") %>">
</td>
<%} %>
</tr>


		<%		if (rs.getString("uso_otros").equals("")){%>
				
		<%}else{%>
		
<tr>
<td width="10%">Otros</td>
<td> 

<select name="metOtros" id="metOtros" onchange="selectOtros()">

<option value="<%=rs.getString("uso_otros")%>"><%=rs.getString("uso_otros")%></option>
			
	<%		if (rs.getString("uso_otros").equals("Si")){%>
				<option value="No">No</option>	
	<%		}
			if (rs.getString("uso_otros").equals("No")){%>
				<option value="Si">Si</option>
	<%}%>
</select>

<%}%><!-- Finaliza Uso Otros Vacio -->

</td>


<% if (rs.getString("cuales_otros").equals("")){ %>

<%}else{ %>
<td>
Cu&aacute;les:<input type="text" name="cualOtros" id="cualOtros" disabled="disabled" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString("cuales_otros") %>">
</td>
<%} %>


<% if (rs.getString("fecha_otros").equals("")){ %>

<%}else{ %>
<td>
Fecha:<input type="text" name="fechaOtros" id="datepickerOtros" disabled="disabled" value="<%=rs.getString("fecha_otros") %>">
</td>

<tr>
<td><p>&nbsp;</p><input type="submit" value="Guardar Metodos" onclick="GuardarMetodos()"/></td>
</tr>

<% }
		}
		rs.getStatement().close();
	} catch (Exception ex) {
		System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "	+ ex);
	}%>



</table>
</td>
</tr>
</table>

</div> <!-- Finaliza div Accordion2 -->
</div> <!-- Finaliza div Segunda Pestaña -->
  


<div id="formulario_consultas"> <!-- Inicia Tercera Pestaña -->
	
<div id="accordion3">
<h3>CONTROLES</h3>

<!-- Tabla Controles De Enfermeria -->
<table style="width: 100%">  
<tr> 
<td id="controlesEnfermeria">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">CONSULTAS - CONTROLES DE ENFERMERIA</td>

</tr>
</table>

<table style="width: 100%">
<tr>
<td>
Fecha

		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_controles_enfermeria WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>

<input type="text" name="fecControles" id="datepickerFechaControles" value="<%=rs.getString("fecha") %>">
</td>

<td>
Peso Kg.
<input type="text" name="pesoControles" id="pesoControles" value="<%=rs.getString("peso") %>">
</td>

<td>
Tension Arterial
<input type="text" name="tenArtControles" id="tenArtControles" value="<%=rs.getString("tension_arterial") %>">
</td>
</tr>

<tr>
<td><br>
<label>Satisfacci&oacute;n con el M&eacute;todo</label><br>

		<%if (rs.getString("satisfaccion_metodo").equals("No")){%>
		
		<input type="radio" name="radSatisfaccionMetodoC" id="Si">Si
		<input type="radio" name="radSatisfaccionMetodoC" id="No" checked="checked">No
		
		<%}else if (rs.getString("satisfaccion_metodo").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("satisfaccion_metodo").equals("Si")){%>
		
		<input type="radio" name="radSatisfaccionMetodoC" id="Si" checked="checked">Si
		<input type="radio" name="radSatisfaccionMetodoC" id="No">No
		</td>
		
		<%}%>


<td><br>
<label>Trastornos Menstruales</label><br>

		<%if (rs.getString("trastornos_menstruales").equals("No")){%>
		
		<input type="radio" name="radTrastornosMenstrualesC" id="Si">Si
		<input type="radio" name="radTrastornosMenstrualesC" id="No" checked="checked">No
		
		<%}else if (rs.getString("trastornos_menstruales").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("trastornos_menstruales").equals("Si")){%>
		
		<input type="radio" name="radTrastornosMenstrualesC" id="Si" checked="checked">Si
		<input type="radio" name="radTrastornosMenstrualesC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Cambios de Comportamiento</label><br>

		<%if (rs.getString("cambios_comportamiento").equals("No")){%>
		
		<input type="radio" name="radCambioComportamientoC" id="Si">Si
		<input type="radio" name="radCambioComportamientoC" id="No" checked="checked">No
		
		<%}else if (rs.getString("cambios_comportamiento").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("cambios_comportamiento").equals("Si")){%>
		
		<input type="radio" name="radCambioComportamientoC" id="Si" checked="checked">Si
		<input type="radio" name="radCambioComportamientoC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>Cefaleas</label><br>

		<%if (rs.getString("cefaleas").equals("No")){%>
		
		<input type="radio" name="radCefaleasC" id="Si">Si
		<input type="radio" name="radCefaleasC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radCefaleasC" id="Si" checked="checked">Si
		<input type="radio" name="radCefaleasC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Mareos</label><br>

		<%if (rs.getString("mareos").equals("No")){%>
		
		<input type="radio" name="radMareosC" id="Si">Si
		<input type="radio" name="radMareosC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radMareosC" id="Si" checked="checked">Si
		<input type="radio" name="radMareosC" id="No">No
		</td>
		
		<%}%>
<td><br>
<label>Manchas en la Piel</label><br>

		<% if (rs.getString("manchas_piel").equals("No")){%>
		
		<input type="radio" name="radManchasPielC" id="Si">Si
		<input type="radio" name="radManchasPielC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radManchasPielC" id="Si" checked="checked">Si
		<input type="radio" name="radManchasPielC" id="No">No
		</td>
		
		<% }%>
</tr>

<tr>
<td><br>
<label>Molestias en las Mamas</label><br>

		<%if (rs.getString("molestias_mamas").equals("No")){%>
		
		<input type="radio" name="radMolestiasMamaC" id="Si">Si
		<input type="radio" name="radMolestiasMamaC" id="No" checked="checked">No
		
		<%}else if (rs.getString("molestias_mamas").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("molestias_mamas").equals("Si")){%>
		
		<input type="radio" name="radMolestiasMamaC" id="Si" checked="checked">Si
		<input type="radio" name="radMolestiasMamaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Edemas</label><br>

		<%if (rs.getString("edemas").equals("No")){%>
		
		<input type="radio" name="radEdemasC" id="Si">Si
		<input type="radio" name="radEdemasC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radEdemasC" id="Si" checked="checked">Si
		<input type="radio" name="radEdemasC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Varices</label><br>

		<%if (rs.getString("varices").equals("No")){%>
		
		<input type="radio" name="radVaricesC" id="Si">Si
		<input type="radio" name="radVaricesC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radVaricesC" id="Si" checked="checked">Si
		<input type="radio" name="radVaricesC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>Expulsi&oacute;n del Dispositivo</label><br>

		<%if (rs.getString("expulsion_dispositivo").equals("No")){%>
		
		<input type="radio" name="radExpulsionDispositivoC" id="Si">Si
		<input type="radio" name="radExpulsionDispositivoC" id="No" checked="checked">No
		
		<%}else if (rs.getString("expulsion_dispositivo").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("expulsion_dispositivo").equals("Si")){%>
	
		<input type="radio" name="radExpulsionDispositivoC" id="Si" checked="checked">Si
		<input type="radio" name="radExpulsionDispositivoC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Dolor Bajo Vientre</label><br>

		<%if (rs.getString("dolor_bajo_vientre").equals("No")){%>
		
		<input type="radio" name="radDolorBajoVientreC" id="Si">Si
		<input type="radio" name="radDolorBajoVientreC" id="No" checked="checked">No
		
		<%}else if (rs.getString("dolor_bajo_vientre").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("dolor_bajo_vientre").equals("Si")){%>
		
		<input type="radio" name="radDolorBajoVientreC" id="Si" checked="checked">Si
		<input type="radio" name="radDolorBajoVientreC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Flujo Vaginal</label><br>

		<%if (rs.getString("flujo_vaginal").equals("No")){%>
		
		<input type="radio" name="radFlujoVaginalC" id="Si">Si
		<input type="radio" name="radFlujoVaginalC" id="No" checked="checked">No
		
		<%}else if (rs.getString("flujo_vaginal").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("flujo_vaginal").equals("Si")){%>
		
		<input type="radio" name="radFlujoVaginalC" id="Si" checked="checked">Si
		<input type="radio" name="radFlujoVaginalC" id="No">No
		</td>
</tr>

		<% }  
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

<tr>
<td><p>&nbsp;</p><input type="submit" value="Guardar Controles" onclick="GuardarControles()"/></td>
</tr>

</table>
	
</td>
</tr>
</table>


<h3>CONDUCTAS</h3>
<!-- Tabla Conductas A Seguir -->
<table style="width: 100%">
<tr>
<td id="conductasSeguir">
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
<label>Estilos de Vida Saludable</label><br>
		<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_conductas WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>

		<%if (rs.getString("estilo_vida_saludable").equals("No")){%>
		
		<input type="radio" name="radEstiloVidaSaludableC" id="Si">Si
		<input type="radio" name="radEstiloVidaSaludableC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radEstiloVidaSaludableC" id="Si" checked="checked">Si
		<input type="radio" name="radEstiloVidaSaludableC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Nutrici&oacute;n y Alimentaci&oacute;n</label><br>

		<%if (rs.getString("nutricion_alimentacion").equals("No")){%>
		
		<input type="radio" name="radNutricionAlimentacionC" id="Si">Si
		<input type="radio" name="radNutricionAlimentacionC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radNutricionAlimentacionC" id="Si" checked="checked">Si
		<input type="radio" name="radNutricionAlimentacionC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Prevenci&oacute;n en Enfermedades de Transmisi&oacute;n Sexual</label><br>

		<%if (rs.getString("prev_enfer_transmision_sexual").equals("No")){%>
		
		<input type="radio" name="radPrevencionEnfermedadesTransmisionSexualC" id="Si">Si
		<input type="radio" name="radPrevencionEnfermedadesTransmisionSexualC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radPrevencionEnfermedadesTransmisionSexualC" id="Si" checked="checked">Si
		<input type="radio" name="radPrevencionEnfermedadesTransmisionSexualC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>Consejer&iacute;a de los M&eacute;todos de Planificaci&oacute;n Familiar</label><br>

		<%if (rs.getString("consejeria_metodos_plan_fam").equals("No")){%>
		
		
		<input type="radio" name="radConsejeriaMetodosC" id="Si">Si
		<input type="radio" name="radConsejeriaMetodosC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radConsejeriaMetodosC" id="Si" checked="checked">Si
		<input type="radio" name="radConsejeriaMetodosC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Signos y Sintomas de Alarma</label><br>

		<%if (rs.getString("signos_sintomas").equals("No")){%>
		
		<input type="radio" name="radSignosSintomasAlarmaC" id="Si">Si
		<input type="radio" name="radSignosSintomasAlarmaC" id="No" checked="checked">No
		
		<%}else if (rs.getString("signos_sintomas").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("signos_sintomas").equals("Si")){%>
		
		<input type="radio" name="radSignosSintomasAlarmaC" id="Si" checked="checked">Si
		<input type="radio" name="radSignosSintomasAlarmaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Prevenci&oacute;n para el Cancer de Cuello Uterino</label><br>

		<%if (rs.getString("prev_cancer_cuello_uter").equals("No")){%>
		
		<input type="radio" name="radPrevencionCancerCuelloUterinoC" id="Si">Si
		<input type="radio" name="radPrevencionCancerCuelloUterinoC" id="No" checked="checked">No
		
		<%}else if (rs.getString("prev_cancer_cuello_uter").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("prev_cancer_cuello_uter").equals("Si")){%>
		
		<input type="radio" name="radPrevencionCancerCuelloUterinoC" id="Si" checked="checked">Si
		<input type="radio" name="radPrevencionCancerCuelloUterinoC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>El Autoexamen de Mama</label><br>

		<%if (rs.getString("autoexamen_mama").equals("No")){%>
		
		<input type="radio" name="radAutoexamenMamaC" id="Si">Si
		<input type="radio" name="radAutoexamenMamaC" id="No" checked="checked">No
		
		<%}else if (rs.getString("autoexamen_mama").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("autoexamen_mama").equals("Si")){%>
		
		<input type="radio" name="radAutoexamenMamaC" id="Si" checked="checked">Si
		<input type="radio" name="radAutoexamenMamaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Lactancia Materna</label><br>

		<%if (rs.getString("lactancia_materna").equals("No")){%>
		
		<input type="radio" name="radLactanciaMaternaC" id="Si">Si
		<input type="radio" name="radLactanciaMaternaC" id="No" checked="checked">No
		
		<%}else if (rs.getString("lactancia_materna").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("lactancia_materna").equals("Si")){%>
		
		<input type="radio" name="radLactanciaMaternaC" id="Si" checked="checked">Si
		<input type="radio" name="radLactanciaMaternaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Prevencion para la Diabetes, Hipertension, Osteoporosis</label><br>

		<%if (rs.getString("prev_diab_hiper_osteop").equals("No")){%>
		
		<input type="radio" name="radPrevenirDiaHipOstC" id="Si">Si
		<input type="radio" name="radPrevenirDiaHipOstC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radPrevenirDiaHipOstC" id="Si" checked="checked">Si
		<input type="radio" name="radPrevenirDiaHipOstC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>Abuso Sexual</label><br>

		<%if (rs.getString("abuso_sexual").equals("No")){%>
		
		<input type="radio" name="radAbusoSexualC" id="Si">Si
		<input type="radio" name="radAbusoSexualC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radAbusoSexualC" id="Si" checked="checked">Si
		<input type="radio" name="radAbusoSexualC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Violencia Intrafamiliar</label><br>

		<%if (rs.getString("violencia_intrafamiliar").equals("No")){%>
		
		<input type="radio" name="radViolenciaIntrafamiliarC" id="Si">Si
		<input type="radio" name="radViolenciaIntrafamiliarC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radViolenciaIntrafamiliarC" id="Si" checked="checked">Si
		<input type="radio" name="radViolenciaIntrafamiliarC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Consecuencias del Consumo Abusivo del Alcohol, Cigarrillo</label><br>

		<%if (rs.getString("consec_alcohol_cigarrillos").equals("No")){%>
		
		<input type="radio" name="radConsecuenciasAlcCigC" id="Si">Si
		<input type="radio" name="radConsecuenciasAlcCigC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radConsecuenciasAlcCigC" id="Si" checked="checked">Si
		<input type="radio" name="radConsecuenciasAlcCigC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>Complicaciones del Consumo de Drogas Psicoactivas</label><br>

		<%if (rs.getString("complicaciones_drogas").equals("No")){%>
		
		<input type="radio" name="radComplicacionesDrogasC" id="Si">Si
		<input type="radio" name="radComplicacionesDrogasC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radComplicacionesDrogasC" id="Si" checked="checked">Si
		<input type="radio" name="radComplicacionesDrogasC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Autoestima: Autocuidado</label><br>

		<%if (rs.getString("autoestima").equals("No")){%>
		
		<input type="radio" name="radAutoestimaC" id="Si">Si
		<input type="radio" name="radAutoestimaC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radAutoestimaC" id="Si" checked="checked">Si
		<input type="radio" name="radAutoestimaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Cuidados del Reci&eacute;n Nacido</label><br>

		<%if (rs.getString("cuidados_recien_nacido").equals("No")){%>
		
		<input type="radio" name="radCuidadosRecienNacidoC" id="Si">Si
		<input type="radio" name="radCuidadosRecienNacidoC" id="No" checked="checked">No
		
		<%}else if (rs.getString("cuidados_recien_nacido").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("cuidados_recien_nacido").equals("Si")){%>
		
		<input type="radio" name="radCuidadosRecienNacidoC" id="Si" checked="checked">Si
		<input type="radio" name="radCuidadosRecienNacidoC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>Derechos Reproductivos</label><br>

		<%if (rs.getString("derechos_reproductivos").equals("No")){%>
		
		<input type="radio" name="radDerechosReproductivosC" id="Si">Si
		<input type="radio" name="radDerechosReproductivosC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radDerechosReproductivosC" id="Si" checked="checked">Si
		<input type="radio" name="radDerechosReproductivosC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Salud Sexual y Reproductiva</label><br>

		<%if (rs.getString("salud_sexual_reproductiva").equals("No")){%>
		
		<input type="radio" name="radSaludSexualReproductivaC" id="Si">Si
		<input type="radio" name="radSaludSexualReproductivaC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radSaludSexualReproductivaC" id="Si" checked="checked">Si
		<input type="radio" name="radSaludSexualReproductivaC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr><td><strong><b><br><br><br>REMISION</b></strong></td></tr>

<tr>
<td><br>
<label>Otro Programa de P y P de acuerdo a la Edad</label><br>

		<%if (rs.getString("otro_prog_pyp").equals("No")){%>
		
		<input type="radio" name="radOtroProgamaPYPC" id="Si">Si
		<input type="radio" name="radOtroProgamaPYPC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radOtroProgamaPYPC" id="Si" checked="checked">Si
		<input type="radio" name="radOtroProgamaPYPC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Valoraci&oacute;n por Psicolog&iacute;a</label><br>

<%if (rs.getString("valoracion_psicologia").equals("No")){%>
		
		<input type="radio" name="radValoracionPsicologiaC" id="Si">Si
		<input type="radio" name="radValoracionPsicologiaC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radValoracionPsicologiaC" id="Si" checked="checked">Si
		<input type="radio" name="radValoracionPsicologiaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Valoraci&oacute;n por Nutrici&oacute;n</label><br>

		<%if (rs.getString("valoracion_nutricion").equals("No")){%>
		
		<input type="radio" name="radValoracionNutricionC" id="Si">Si
		<input type="radio" name="radValoracionNutricionC" id="No" checked="checked">No
		
		<%}else{%>
		
		<input type="radio" name="radValoracionNutricionC" id="Si" checked="checked">Si
		<input type="radio" name="radValoracionNutricionC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
<label>Valoraci&oacute;n por Ginecolog&iacute;a</label><br>

		<%if (rs.getString("valoracion_ginecologia").equals("No")){%>
		
		<input type="radio" name="radValoracionGinecologiaC" id="Si">Si
		<input type="radio" name="radValoracionGinecologiaC" id="No" checked="checked">No
		
		<%}else if (rs.getString("valoracion_ginecologia").equals("")){%>
		
		(N/A)
		
		<%}else if (rs.getString("valoracion_ginecologia").equals("Si")){%>
		
		<input type="radio" name="radValoracionGinecologiaC" id="Si" checked="checked">Si
		<input type="radio" name="radValoracionGinecologiaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Valoraci&oacute;n por Urolog&iacute;a</label><br>

<%if (rs.getString("valoracion_urologia").equals("No")){%>
		
		<input type="radio" name="radValoracionUrologiaC" id="Si">Si
		<input type="radio" name="radValoracionUrologiaC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radValoracionUrologiaC" id="Si" checked="checked">Si
		<input type="radio" name="radValoracionUrologiaC" id="No">No
		</td>
		
		<%}%>

<td><br>
<label>Consulta M&eacute;dica por Enfermedad</label><br>

<%if (rs.getString("consulta_medica").equals("No")){%>
		
		<input type="radio" name="radConsultaMedicaC" id="Si">Si
		<input type="radio" name="radConsultaMedicaC" id="No" checked="checked">No

		<%}else{%>
		
		<input type="radio" name="radConsultaMedicaC" id="Si" checked="checked">Si
		<input type="radio" name="radConsultaMedicaC" id="No">No
		</td>
		
		<%}%>
</tr>

<tr>
<td><br>
Otra especialidad
<input type="text" name="otraEspecialidadControles" id="otraEspecialidadControles" value="<%=rs.getString("otra_especialidad") %>">
</table>

<% 
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

</td>
</tr>

<tr>
<td><p>&nbsp;</p><input type="submit" value="Guardar Conductas" onclick="GuardarConductas()"/></td>
</tr>


</table>


<h3>INDICACIONES</h3>
<!-- Tabla Indicaciones -->
<table style="width: 100%">
<tr>
<td id="observacionesEspeciales">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">OBSERVACIONES ESPECIALES</td>
<p>&nbsp;</p>
</tr>
</table>

<br>
Coloque las observaciones especiales

<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_observaciones_especiales WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>

<table>
<tr>
<td>
<textarea rows="5" cols="130" name="observacionEspecial" id="observacionEspecial" onkeypress="javascript:return soloLetras(event)"><%=rs.getString("observaciones_especiales") %></textarea>
</td>
</tr>
</table>

<%  
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

</td>
</tr>


<!-- Tabla Evolucion Clinica -->
<tr>
<td id="evolucionClinica">
<table style="width: 100%">
<tr>
<td id="cabecera2" class="style11" colspan="2" align="center">EVOLUCION CLINICA</td>
<p>&nbsp;</p>
</tr>
</table>

<br>
Coloque la evoluci&oacute;n cl&iacute;nica

<%		rs = null;
			 	st = null;
			
			 try {
			    con=new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM pyp_evolucion_clinica WHERE id_informe_diagnostico_pyp_fk="+CodInfPYPPlanFam);
				if(rs.next()){	
		%>

<table>
<tr>
<td>
<textarea rows="5" cols="130" name="evolucionesClinicas" id="evolucionesClinicas" onkeypress="javascript:return soloLetras(event)"><%=rs.getString("evolucion_clinica") %></textarea>
</td>
</tr>
</table>

<%  
				}
				rs.getStatement().close();
				} catch (Exception ex) {
					System.out.println("Error en MetodoPaciente>>BuscarAdmisiones " + ex);
				}
		%>

</td>
</tr>

<tr>
<td><p>&nbsp;</p><input type="submit" value="Guardar Indicaciones" onclick="GuardarIndicaciones()"/></td>
</tr>

    
</table> <!-- Fin Tabla Indicaciones -->

</div> <!-- Finaliza Tercer Acordeon -->
</div> <!-- Finaliza Tercera Pestaña -->


</div> <!-- Finaliza div tabs -->
</td>
</tr>

</table>
     
	<%}%>
	
</body>
</html>