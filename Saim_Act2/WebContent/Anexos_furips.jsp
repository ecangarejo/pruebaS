<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=utf-8" import="adm_logica.Conexion" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>




<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<title>Anexos Furips</title>
<script language="javascript" type='text/javascript' src="Furips.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language=javascript src="ValidacionesFurips.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<script language="javascript" type='text/javascript' src='Browser.js'></script>
<script language="javascript">
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
<script language="javascript">
			function Verificar(e){
				var minavegador = new Browser();
				if (minavegador.name == 'firefox'){
	    			if(e) 
						document.onkeypress = function(){return true;} ;
				var evt = e?e:event; 
				if(evt.keyCode==116){ 
					if(e) 
					document.onkeypress = function(){return false;} ;
				else{ 
					evt.keyCode = 0; 
					evt.returnValue = false; 
					} 
				} 
				}
			}
		</script>
</head>

<body onLoad="document.getElementById('txtnumdoc').focus(); ">
	<%  String codigou = (String) session.getAttribute("codusuario");
		Conexion con = new Conexion();
		ResultSet rs;
		Statement st = null;
		String idu = "";
		st = con.conn.createStatement();
		rs = st.executeQuery("select usuario from seg_usuario where usu_codigo="+ codigou);
		while (rs.next()) {
			idu = rs.getString(1);
		}
		rs.getStatement().close();

		st = null;
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+ codigou + "");
		int madmin = 0;
		if (rs.next()) {
			madmin = rs.getInt(1);
		}
		rs.getStatement().close();
		con.cerrar();
		String cod = request.getParameter("codigo");
		if (codigou == null) {
			codigou = "";
		}
		if (codigou.equals("")) { //seguridad
	%>
<script language="javascript">
	alert("Usted No Tiene Permiso Para ver esta Pagina....");
	window.location.href = "Seg_login.jsp";
	<%} else {%>
</script>
<table width="75%" align="center">
	<tr>
		<td>
		<table width="100%">
			<tr>
				<td>
					<div><a href="mensajes.jsp?TB_iframe=true&height=520&width=700" class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin%></b></font></a>
						<%if (madmin > 0) {%> <bgsound src="Imagenes/INNERMK.WAV" loop="2">
						<img src="Imagenes/sobr0001.gif" /> <%}%>
					</div>
				</td>
				<td align="right">
					<div align="right" id="usuario" align="right" class="style11">
						Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou%>"	style="color: white">--Cerrar Session--</a>
					</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
			<div id="cabecera1" align="center" class="linkmenu"><b>
				<a href="menu.jsp">Men&uacute; Principal</a>-Realizar Furips</b>
			</div>
		</td>
	</tr>

	<tr>
		<td id="cabecera2" class="style11" align="center">Realizar Furips
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%">
			<tr>
				<td align="center">Tipo de Documento:</td>
				<td><select name="cbafiliacion" size="1" id="cbafiliacion" onChange="document.getElementById('txtnumdoc').focus();">
					<%rs = null;
					st = null;
					try {
						con = new Conexion();
						st = con.conn.createStatement();
						rs = st.executeQuery("SELECT sigla, descripcion FROM adm_tipodocumento");
						while (rs.next()) {%>
							<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
						<%}
						rs.getStatement().close();
						con.cerrar();
					} catch (SQLException e) {
						System.out.println(e);
						out.println("no se pudo realizar la conexion!<br/>");
					}%></select>
				</td>
				<td align="center">N&uacute;mero de Documento</td>
				<td>
				<form id="form1" name="form1" onKeyPress="return pulsar(event);">
					<input type="text" name="txtnumdoc" id="txtnumdoc" onKeyUp="A(event)" /> 
					<input type="hidden" name="txtFecha" id="txtFecha" /> <input type="hidden" name="txtHora" id="txtHora" />
					<input type="hidden" name="txtUsuario" id="txtUsuario" value="<%=codigou%>" />
				</form>
				</td>
				<td><input name="btnBuscarPac" type="button" id="btnBuscarPac"value="Buscar" onClick="pacienteActivo()"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<div id="datos_ingreso_paciente"></div>
		</td>
	</tr>
	<tr>
		<td id="datos_victima" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="4" align="center">DATOS</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width='30' align="left">N&uacute;mero de Radicado:</td>
				<td width="50"><input id="radicado" name="radicado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
				<td width='30' align="left">Fecha de Radicado:</td>
				<td><input type="text" name="fec_radicado" id="fec_radicado"></td>
			</tr>
	 		<tr>
				<td width='30' align="left">N&uacute;mero de Radicado Anterior:	</td>
				<td width="50"><input id="antradicado" name="antradicado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
				<td width='30' align="left">N&uacute;mero de Factura/ Cuenta de Cobro:</td>
				<td><input id="factura" name="factura" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
			</tr>
			<tr>
				<td width="70">Condicion del Accidentado: &nbsp;</td>
				<td width="50"> Conductor:<input type="radio" id="Conductor" name="condicion" value="conductor"> 
								Peaton: <input type="radio" name="condicion" id="Peaton" value="peaton">
								Ocupante:<input type="radio" name="condicion" id="Ocupante" value="ocupante"> 
								Ciclista: <input type="radio" name="condicion" id="Ciclista" value="ciclista">
						<span class="style8"> *</span>				
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td id="datos_sitio" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="8" align="center">
					DATOS DEL SITIO DONDE OCURRIO EL EVENTO CATASTROFICO O ACCIDENTE DE TRANSITO
				</td>
			</tr>
			<p>&nbsp;</p>
			<tr>
				<form id="form2" name="form2" onkeypress="return pulsar(event);" style="margin-top: -16px;">
				<td width="150"><span class="Estilo8">Naturaleza del evento</span></td>
				<td><label> <select name="evento" size="1" id="evento" onChange="ajaxEvento(form2)" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
					<%rs = null;
					  st = null;
						try {
							con = new Conexion();
							st = con.conn.createStatement();
							rs = st.executeQuery("SELECT CodEvento, Nombre FROM furips_evento");
							while (rs.next()) {%>
								<option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
							<%}
							rs.getStatement().close();
							con.cerrar();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
					}%>
				</select> <span class="style8">*</span></label></td>
				<td><span class="Estilo8">Evento</span></td>
				<td width="100"><div id="evento2"> <select name="eventonaturaleza" id="eventonaturaleza" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
				</select> <span class="style8">*</span></div></td>
			</tr>
			<tr>
				<td width="150">Direcci&oacute;n de la Ocurrencia:</td>
				<td width="100"><input id="DirOcurrencia" name="DirOcurrencia" type="text" width='28%' class="styletxt" /><span class="style8"> *</span></td>
				<td width="150">Fecha Evento/Accidente:</td>
				<td width="158"><input type="text" name="fec_evento" id="fec_evento"><span class="style8"> *</span></td>
				<td width="100">Hora: &nbsp; HH:MM</td>
				<td><input type="text" name="hora" id="hora" class="styletxt" onBlur="CheckTime(this)" ><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Departamento:</td>
				<td width="20"><label> <select name="cbdep" size="1" id="cbdep" onChange="ajaxxMun(form2)" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
					<% 	rs = null;
						st = null;
						try {
							con = new Conexion();
							st = con.conn.createStatement();
							rs = st.executeQuery("SELECT Nombre FROM adm_departamento where pais_codigo_fk=" + 1);
							while (rs.next()) {%>
								<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
							<%}
							rs.getStatement().close();
							con.cerrar();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
					}%>
					</select> <span class="style8">*</span></label></td>
				<td width="200">Municipio: &nbsp;</td>
				<td width="20"><div id="cbmun2"><select name="cbmun" id="cbmun" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
				</select> <span class="style8">*</span></div></td>
			</tr>


			<tr>
				<td width="150">Zona: &nbsp;</td>
				<td width="100"><select name="zona" id="zona" style="width: 155px">
					<option value="Seleccione" selected="selected" class="styletxt">SELECCIONE</option>
					<option value="Urbana">Urbana</option>
					<option value="Rural">Rural</option>
				</select>
				<span class="style8"> *</span>
			</td>
			</tr>
			<tr>
				<td colspan="2">Descripcion Breve del Evento Catastrofico o Accidente de Transito:</td>
				<td colspan="4"><textarea id="Descripcion" name="Descripcion" cols="50" rows="5" /></textarea><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td colspan="9" align="center"><input id="guardarSITIO" type="button" value="Guardar" onClick="GuardarDatos()" class="styletxt"></td>
			</tr>
		</table>
		</td>
	<tr>
		<td>
		<div id="codigo_furips"></div>
		</td>
	</tr>
		<td id="datos_vehiculo" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL VEHICULO DEL ACCIDENTE DE TRANSITO</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Estado de Aseguramiento:</td>
				<td width="190"><select name="aseguramiento" id="aseguramiento" class="styletxt" style="width: 155px" onChange="ajaxVal()">
					<option value="Seleccione" selected="selected">SELECCIONE</option>
					<option value="Asegurado">Asegurado</option>
					<option value="No Asegurado">No Asegurado</option>
					<option value="Vehiculo Fantasma">Vehiculo Fantasma</option>
					<option value="Poliza Falsa">Poliza Falsa</option>
					<option value="Vehiculo Fuga">Vehiculo Fuga</option>
				</select>
				<span class="style8"> *</span>
				</td>
				<td width="150">Marca:</td>
				<td width="190"><input id="marca" name="marca" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" disabled="disabled" /><span class="style8"> *</span></td>
				<td width="150">Placa:</td>
				<td width="20"><input id="placa" name="placa" type="text" width='28%' class="styletxt" disabled="disabled"/><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Tipo de Servicio:</td>
				<td width="190"><select name="servicio" id="servicio" class="styletxt" style="width: 155px" disabled="disabled">
					<option value="Seleccione" selected="selected">SELECCIONE</option>
					<option value="Particular">Particular</option>
					<option value="Publico">Publico</option>
					<option value="Oficial">Oficial</option>
					<option value="Vehiculo Emergencia">Vehiculo de Emergencia</option>
					<option value="Vehiculo Diplomatico">Vehiculo de servicios diplomatico o consultar</option>
					<option value="Vehiculo Transporte Masivo">Vehiculo de Transporte Masivo</option>
					<option value="Vehiculo Escolar">Vehiculo Escolar</option>
				</select><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Codigo Aseguradora:</td>
				<td width="190"><label> <select name="CodAseguradora" size="1" id="CodAseguradora"  style="width: 155px">
					<option selected="selected">SELECCIONE</option>
					<% 	rs = null;
						st = null;
						try {
							con = new Conexion();
							st = con.conn.createStatement();
							rs = st.executeQuery("SELECT * FROM furips_datosaseguradora");
							while (rs.next()) {%>
								<option value="<%=rs.getString(2)%>"><%=rs.getString(3)%></option>
							<%}
							rs.getStatement().close();
							con.cerrar();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
					}%>
					</select> <span class="style8">*</span></label></td>				
				<td width="150">N&uacute;mero de Poliza:</td>
				<td width="190"><input id="Numpoliza" name="Numpoliza" type="text" width='28%' class="styletxt" disabled="disabled"/><span class="style8"> *</span></td>
				<td width="150">Intervencion de Autoridad: &nbsp;</td>
				<td width="20"> Si: <input type="radio" name="Autoridad" value="1" id="Si"> 
								No: <input type="radio" name="Autoridad" value="1" id="No"> <span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="150">Vigencia Desde:</td>
				<td width="190"><input id="desde" name="desde" type="text" width='28%' class="styletxt" disabled="disabled"/><span class="style8"> *</span></td>
				<td width="150">Hasta:</td>
				<td width="190"><input id="hasta" name="hasta" type="text" width='28%' class="styletxt" disabled="disabled" /><span class="style8"> *</span></td>
				<td width="150">Cobro Excedente Poliza: &nbsp;</td>
				<td width="20"> Si: <input type="radio" name="ExcPoliza" value="1" id="Si"> 
								No: <input type="radio" name="ExcPoliza" value="1" id="No"><span class="style8"> *</span></td>
			</tr>
			</table>
		</td>
	</tr>

	<tr>
		<td id="ced_propietario" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">PROPIETARIO</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td align="center">Tipo de Documento:</td>
				<td align="center"><select name="tipdoc" size="1" id="tipdoc" onChange="document.getElementById('txtnd').focus();">
					<option value="CC">CC</option>
					<option value="CE">CE</option>
					<option value="PA">PA</option>
					<option value="NIT">NIT</option>
					<option value="TI">TI</option>
					<option value="RC">RC</option>
					</select></td>
				<td align="center">N&uacute;mero de documento: &nbsp;</td>
				<td align="center"> <input id="txtnd" name="txtnd" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
				<td><input name="btnBuscarPro" type="button" id="btnBuscarPro"value="Buscar" onClick="propietarioActivo()"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
			<div id="datos_ingreso_propietario"></div>
		</td>
	</tr>			
	<tr>
		<td id="datos_propietariovehiculo" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL PROPIETARIO DEL VEHICULO</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Primer Apellido o Razon Social:</td>
				<td><input id="PrimerApellido" name="PrimerApellido" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8"> *</span></td>
				<td width="150">Segundo Apellido:</td>
				<td><input id="SegundoApellido" name="SegundoApellido" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Primer Nombre:</td>
				<td><input id="PrimerNombre" name="PrimerNombre" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8"> *</span></td>
				<td width="150">Segundo Nombre:</td>
				<td><input id="SegundoNombre" name="SegundoNombre" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td>Tipo de Documento:</td>
				<td><select name="tipodocumento" size="1" id="tipodocumento" onChange="document.getElementById('txtnumdoc').focus();" class="styletxt" style="width: 155px">
					<option value="CC">CC</option>
					<option value="CE">CE</option>
					<option value="PA">PA</option>
					<option value="NIT">NIT</option>
					<option value="TI">TI</option>
					<option value="RC">RC</option>
				</select></td>
				<td width="100">Numero de Documento:</td>
				<td width="20"><input id="NumDocumento" name="NumDocumento" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)"/><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="204">Direccion de Residencia:</td>
				<td width="20"><input id="DirPropietario" name="Dirpropietario" type="text" width='28%' class="styletxt" /><span class="style8"> *</span></td>
				<td width="200">Telefono: &nbsp;</td>
				<td width="20"><input id="TelPropietario" name="TelPropietario" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)"/><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Departamento:</td>
				<td width="20"><label> <select name="dpto" size="1" id="dpto" onChange="ajaxxMuni(form2)" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
					<% 	rs = null;
						st = null;
						try {
							con = new Conexion();
							st = con.conn.createStatement();
							rs = st.executeQuery("SELECT Nombre FROM adm_departamento where pais_codigo_fk=" + 1);
							while (rs.next()) {%>
								<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
							<%}
							rs.getStatement().close();
							con.cerrar();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
					}%>
					</select> <span class="style8">*</span></label></td>
				<td width="200">Municipio: &nbsp;</td>
				<td width="20"><div id="municipio2"> <select name="municipio" id="municipio" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
				</select> <span class="style8">*</span></div></td>
			</tr>
	</table>
	</td>
</tr>
<tr>
	<td id="ced_conductor" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">CONDUCTOR</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td align="center">Tipo de Documento:</td>
				<td align="center"><select name="tdconductor" size="1" id="tdconductor" onChange="document.getElementById('txtconductor').focus();">
					<option value="CC">CC</option>
					<option value="CE">CE</option>
					<option value="PA">PA</option>
					<option value="AS">AS</option>
					<option value="TI">TI</option>
					<option value="RC">RC</option>
					</select></td>
				<td align="center">N&uacute;mero de documento: &nbsp;</td>
				<td align="center"> <input id="txtconductor" name="txtconductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
				<td><input name="btnBuscarPro" type="button" id="btnBuscarPro"value="Buscar" onClick="ConductorActivo()"></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
			<div id="datos_ingreso_conductor"></div>
		</td>
	</tr>	
<tr>
		<td id="datos_conductorvehiculo" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL CONDUCTOR DEL VEHICULO INVOLUCRADO EN EL ACCIDENTE DE TRANSITO</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Primer Apellido:</td>
				<td><input id="PAConductor" name="PAConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8"> *</span></td>
				<td width="150">Segundo Apellido:</td>
				<td><input id="SAConductor" name="SAConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Primer Nombre:</td>
				<td><input id="PNConductor" name="PNConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8"> *</span></td>
				<td width="150">Segundo Nombre:</td>
				<td><input id="SNConductor" name="SNConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /></td>
			</tr>
			<tr>
				<td>Tipo de Documento:</td>
				<td><select name="tdConductor" size="1" id="tdConductor" onChange="document.getElementById('NDconductor').focus();" class="styletxt" style="width: 155px">
					<option value="CC">CC</option>
					<option value="CE">CE</option>
					<option value="PA">PA</option>
					<option value="AS">AS</option>
					<option value="TI">TI</option>
					<option value="RC">RC</option>
				</select></td>
				<td width="100">Numero de Documento:</td>
				<td width="20"><input id="NDconductor" name="NDconductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)"/><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="204">Direccion de Residencia:</td>
				<td width="20"><input id="DirConductor" name="DirConductor" type="text" width='28%' class="styletxt" /><span class="style8"> *</span></td>
				<td width="200">Telefono: &nbsp;</td>
				<td width="20"><input id="TelConductor" name="TelConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)"/><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Departamento:</td>
				<td width="20"><label> <select name="dp" size="1" id="dp" onChange="ajaxxMunic(form2)" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
					<% 	rs = null;
						st = null;
						try {
							con = new Conexion();
							st = con.conn.createStatement();
							rs = st.executeQuery("SELECT Nombre FROM adm_departamento where pais_codigo_fk=" + 1);
							while (rs.next()) {%>
								<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
							<%}
							rs.getStatement().close();
							con.cerrar();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
					}%>
					</select> <span class="style8">*</span></label></td>
				<td width="200">Municipio: &nbsp;</td>
				<td width="20"><div id="mun2"> <select name="mun" id="mun" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
				</select> <span class="style8">*</div></label></td>
			</form>	
			</tr>
		</table>
</td>
</tr>
<tr>
	<td id="guardar" style="display: none">
		<table width="100%">
			<tr>
				<td colspan="9" align="center"><input id="guardar" type="button" value="Guardar" onClick="GuardarDatosAccidente()" class="styletxt"></td>
			</tr>
		</table>
	</td>
</tr>


<tr>
	<td id="ver_remision" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">REMISION</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td align="center">Realizo Remision:&nbsp;&nbsp;&nbsp;
					<input type="radio" name="radioremision" id="Si" onClick="muestra('remision')"value="Si"/>Si
      				<input type="radio" name="radioremision" id="No" onClick="oculta('remision')" value="No"/>No <span class="style8"> *</span>
 				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td id="remision" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DE REMISION</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Tipo de Referencia:</td>
				<td width="50"> Remision:<input type="radio" id="Remision" name="referencia" value="Remision"> 
								Orden de Servicio: <input type="radio" name="referencia" id="Orden de Servicio" value="OrdenServicio">
						<span class="style8"> *</span>				
				</td>
			</tr>
			<tr>
				<td width="150">Fecha Remision:</td>
				<td><input type="text" id="fec_remision" name="fec_remision"><span class="style8">*</span></td>
				<td width="150">Hora:  HH:MM</td>
				<td><input id="hora_remision" name="hora_remision" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td>Prestador que remite:</td>
				<td><input id="PrestadorRemite" name="PrestadorRemite" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="204">Codigo Inscripcion:</td>
				<td width="20"><input id="CodInsRemite" name="CodInsRemite" type="text" width='28%' class="styletxt" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="100">Profesional que remite:</td>
				<td width="20"><input id="ProfesionalRemite" name="ProfesionalRemite" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
				<td width="200">Cargo: &nbsp;</td>
				<td width="20"><input id="CargoRemite" name="CargoRemite" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
				
			</tr>
			<tr>
				<td width="150">Fecha Aceptacion:</td>
				<td><input type="text" id="fec_aceptacion" name="fec_aceptacion"><span class="style8"> *</span></td>
				<td width="150">Hora:  HH:MM</td>
				<td><input id="hora_aceptacion" name="hora_aceptacion" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)"/><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td>Prestador que recibe:</td>
				<td><input id="PrestadorRecibe" name="PrestadorRecibe" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="204">Codigo Inscripcion:</td>
				<td width="20"><input id="CodInsRecibe" name="CodInsRecibe" type="text" width='28%' class="styletxt" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Profesional que recibe:</td>
				<td width="20"><input id="ProfesionalRecibe" name="ProfesionalRecibe" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
				<td width="200">Cargo: &nbsp;</td>
				<td width="20"><input id="CargoRecibe" name="CargoRecibe" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
			</tr>
		</table>
	</td>
	</tr>

<tr>
	<td id="ver_amparo" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">TRANSPORTE</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td align="center">Transporte del sitio del evento a la IPS:&nbsp;&nbsp;&nbsp;
					<input type="radio" name="radioamparo" id="Si" onClick="muestra2('amparo')"value="Si"/>Si
      				<input type="radio" name="radioamparo" id="No" onClick="oculta2('amparo')" value="No"/>No <span class="style8"> *</span>
 				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr> 
		<td id="amparo" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">AMPARO DE TRANSPORTE Y MOVILIZACION DE LA VICTIMA</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Datos de Vehiculo</td>
			</tr>
			<tr>
				<td width="150">Placa No:</td>
				<td><input id="placatrans" name="placatrans" type="text" width='28%' class="styletxt" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Transporto la victima desde:</td>
				<td width="20"><input id="transdesde" name="transdesde" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
				<td width="200">Hasta: &nbsp;</td>
				<td width="20"><input id="transhasta" name="transhasta" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="150">Tipo de Transporte:</td>
				<td><select name="transporte" id="transporte" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
					<option value="Ambulancia Basica">Ambulancia Basica</option>
					<option value="Ambulancia Medicalizada">Ambulancia Medicalizada</option>
				</select><span class="style8">*</span></td>
				<td width="150">Lugar donde recogio la victima:</td>
				<td><select name="lugarvictima" id="lugarvictima" style="width: 155px">
					<option selected="selected">SELECCIONE</option>
					<option value="Urbana">Urbana</option>
					<option value="Rural">Rural</option>
				</select><span class="style8">*</span>
				</td>
			</tr>
			</table>
	</td>
</tr>
		
<tr>   
		<td id="certificacion_medica" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">CERTIFICACION DE LA ATENCION MEDICA DE LA VICTIMA COMO PRUEBA DEL ACCIDENTE O EVENTO</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Fecha de Ingreso:</td>
				<td><input type="text" id="fec_Ingreso" name="fec_Ingreso"><span class="style8"> *</span></td>
				<td width="150">Hora:</td>
				<td><input id="hora_ingreso" name="hora_ingreso" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Fecha de Egreso:</td>
				<td><input type="text" id="fec_Egreso" name="fec_Egreso"></td>
				<td width="150">Hora:</td>
				<td><input id="hora_egreso" name="hora_egreso" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)" /></td>
			</tr>
			<tr>
				<td width="200">Codigo Diagnostico principal de Ingreso:</td>
	    		<td width="20"><input name="diagini" type="text" id="diagini" onkeyUp="AutoCompleta2('1')" style="width: 200px">
					<input type="hidden" name="DiagPrincipalIngreso" id="DiagPrincipalIngreso" /> <span class="style8"> *</span>
					<div id="DiagPrincipalIng"></div></td>

				<td width="200">Codigo Diagnostico principal de Egreso: &nbsp;</td>
					<td width="20"><input name="diagegre" type="text" id="diagegre" onkeyUp="AutoCompleta2('2')" style="width: 200px">
					<input type="hidden" name="DiagPrincipalEgreso" id="DiagPrincipalEgreso" /> 
					<div id="DiagPrincipalEgr"></div></td>			
			</tr>
			<tr>
				<td width="200">Otro Codigo de Ingreso:</td>
				<td width="20"><input name="Otrodiaging" type="text" id="Otrodiaging" onkeyUp="AutoCompleta2('3')" style="width: 200px">
					<input type="hidden" name="OtroDiagIngreso" id="OtroDiagIngreso" /> 
					<div id="OtroDiagIng"></div>
				</td>
				<td width="200">Otro Codigo de Egreso: &nbsp;</td>
				<td width="20"><input name="Otrodiagegre" type="text" id="Otrodiagegre" onkeyUp="AutoCompleta2('4')" style="width: 200px">
					<input type="hidden" name="OtroDiagEgreso" id="OtroDiagEgreso" />
					<div id="OtroDiagEgr"></div>
				</td>					
			</tr>
			<tr>
				<td width="200">Otro Codigo de Ingreso:</td>
				<td width="20"><input name="Otrodiaging2" type="text" id="Otrodiaging2" onkeyUp="AutoCompleta2('5')" style="width: 200px">
					<input type="hidden" name="OtroDiagIngreso2" id="OtroDiagIngreso2" />
					<div id="OtroDiagIng2"></div>
				</td>			
				<td width="200">Otro Codigo de Egreso: &nbsp;</td>
				<td width="20"><input name="Otrodiagegre2" type="text" id="Otrodiagegre2" onkeyUp="AutoCompleta2('6')" style="width: 200px">
					<input type="hidden" name="OtroDiagEgreso2" id="OtroDiagEgreso2" />
					<div id="OtroDiagEgr2"></div>
				</td>				
			</tr>
	</table>
</td>
</tr>

<tr>
	<td id="ced_medico" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">MEDICO O PROFESIONAL TRATANTE</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td align="center">Tipo de Documento:</td>
				<td align="center"><select name="tipmedico" size="1" id="tipmedico" onChange="document.getElementById('txtmedico').focus();">
					<option value="CC">CC</option>
					<option value="CE">CE</option>
					<option value="PA">PA</option>
					</select></td>
				<td align="center">N&uacute;mero de documento: &nbsp;</td>
				<td align="center"> <input id="txtmedico" name="txtmedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
				<td><input name="btnBuscarMed" type="button" id="btnBuscarMed"value="Buscar" onClick="MedicoActivo()"></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
			<div id="datos_ingreso_medico"></div>
		</td>
	</tr>	

<tr>
		<td id="medico" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL MEDICO O PROFESIONAL TRATANTE</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Primer Apellido:</td>
				<td><input id="PAMedico" name="PAMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
				<td width="150">Segundo Apellido:</td>
				<td><input id="SAMedico" name="SAMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /></td>
			</tr>
			<tr>
				<td width="150">Primer Nombre:</td>
				<td><input id="PNMedico" name="PNMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /><span class="style8">*</span></td>
				<td width="150">Segundo Nombre:</td>
				<td><input id="SNMedico" name="SNMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" /></td>
			</tr>
			<tr>
				<td>Tipo de Documento:</td>
				<td><select name="tdmedico" size="1" id="tdmedico" onChange="document.getElementById('txtnumdoc').focus();" class="styletxt" style="width: 155px">
					<option value="CC">CC</option>
					<option value="CE">CE</option>
					<option value="PA">PA</option>
				</select><span class="style8">*</span></td>
				<td width="100">Numero de Documento:</td>
				<td width="20"><input id="NDMedico" name="NDMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)"/><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Numero de Registro Medico:</td>
				<td><input id="RegistroMedico" name="RegistroMedico" type="text" width='28%' class="styletxt" /><span class="style8">*</span></td>
			</tr>
			</table>
	</td>
</tr>
	<tr>
		<td id="amparo_reclama" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">AMPAROS QUE RECLAMA</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150"></td>
				<td width="50"> Valor Total Facturado</td>
				<td width="50"> Valor Reclamado al Fosyga</td>
			</tr>
			<tr>
				<td width="150">Gastos Medicos Quirurgicos:</td>
				<td><input id="GastoMedicoFacturado" name="GastoMedicoFacturado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /><span class="style8">*</span></td>
				<td><input id="GastoMedicoFosyga" name="GastoMedicoFosyga" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
			</tr>
			<tr>
				<td width="150">Gastos de transporte y movilizacion de la victima:</td>
				<td><input id="GastoTransFacturado" name="GastoTransFacturado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /><span class="style8">*</span></td>
				<td><input id="GastoTransFosyga" name="GastoTransFosyga" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" /></td>
			</tr>
			<tr>
				<td colspan="9" align="center"><input id="guardar" type="button" value="Guardar" onClick="GuardarDatosOtros()" class="styletxt"></td>
			</tr>
		</table>
	</td>
	</tr>
	</td>
	</tr>
</table>
<%}%>
</body>
</html>