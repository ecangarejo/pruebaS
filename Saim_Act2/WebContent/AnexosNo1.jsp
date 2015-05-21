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
<title>Anexos Tecnicos No 1</title>
<script language="javascript" type='text/javascript' src="AnexosNo1.js"></script>
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

<body onLoad="document.getElementById('txtnumdoc').focus(); ajaxNacionalidad(),focus(form2.txtnumdocu);">
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
				<a href="menu.jsp">Men&uacute; Principal</a>-Anexos Tecnicos No 1</b>
			</div>
		</td>
	</tr>

	<tr>
		<td id="cabecera2" class="style11" align="center">Realizar Anexos Tecnicos No 1
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
		<td id="datos" style="display: none" >
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="4" align="center">DATOS</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150" align="center">Tipo de inconsistencia:</td>
				<td width="190"><select name="TInconsistencia" id="TInconsistencia" class="styletxt" style="width: 155px" onChange="ajaxVal()">
					<option value="Seleccione" selected="selected">SELECCIONE</option>
					<option value="El usuario no existe en la base de datos">El usuario no existe en la base de datos</option>
					<option value="Los datos del usuario no corresponden con los del documento de identificación presentado">Los datos del usuario no corresponden con los del documento de identificación presentado</option>
				</select>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<div id="datos_ingreso_paciente"></div>
		</td>
	</tr>
	<tr> <!-- style="display: none" -->
		<td id="informacion inconsistente" style="display: none">
		<table width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="4" align="center">INFORMACION DE LA POSIBLE INCONSISTENCIA</td>
				<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="250">VARIABLE PRESUNTAMENTE INCORRECTA</td>
				<td width="150" colspan='2' align="center">DATOS SEGUN DOCUMENTO DE IDENTIFICACION (fisico)</td>
			</tr>
			<tr>
				<td width="250"><input type="checkbox" id="PApellido" name="PApellido" value="PApellido" onclick="habilitar('PApellido','papellido')"> Primer Apellido<br></td>
				<td width="200">Primer Apellido</td>
				<td><input type="text" name="papellido" id="papellido" disabled="disabled"/></td>
			</tr>
			<tr>
				<td width="250"><input type="checkbox" id="SApellido" value="SApellido" onclick="habilitar('SApellido','sapellido')"> Segundo Apellido<br></td>
				<td width="200">Segundo Apellido </td>
				<td><input type="text" name="sapellido" id="sapellido" disabled="disabled"/></td>
			</tr>
			<tr>
				<td width="250"><input type="checkbox" id="PNombre" value="PNombre" onclick="habilitar('PNombre','pnombre')"> Primer Nombre<br></td>
				<td width="200">Primer Nombre</td>
				<td><input type="text" name="pnombre" id="pnombre" disabled="disabled"/></td>
			</tr>
			<tr>
				<td width="250"><input type="checkbox" id="SNombre" value="SNombre" onclick="habilitar('SNombre','snombre')"> Segundo Nombre<br></td>
				<td width="200">Segundo Nombre</td>
				<td><input type="text" name="snombre" id="snombre" disabled="disabled"/></td>
			</tr>
			<tr>
				<td width="250"><input type="checkbox" id="TIdentificacion" value="TIdentificacion" onclick="habilitar('TIdentificacion','tidentificacion')"> Tipo Documento de Identificacion<br></td>
				<td width="200">Tipo de Documento de Identificacion</td>
				<td><input type="text" name="tidentificacion" id="tidentificacion" disabled="disabled"/></td>
			</tr>
			<tr>
				<td width="250"><input type="checkbox" id="NDIdentificacion" value="NDIdentificacion" onclick="habilitar('NDIdentificacion','ndidentificacion')"> Numero de Documento de Identificacion<br></td>
				<td width="200">Numero de Documento de Identificacion</td>
				<td><input type="text" name="ndidentificacion" id="ndidentificacion" disabled="disabled"/></td>
			</tr>
			<tr>
				<td width="250"><input type="checkbox" id="FechaNacimiento" value="FechaNacimiento" onclick="habilitar('FechaNacimiento','fechanacimiento')"> Fecha Nacimiento<br></td>
				<td width="200">Fecha Nacimiento</td>
				<td><input type="text" name="fechanacimiento" id="fechanacimiento" disabled="disabled"/></td>
			</tr>
			<tr>
				<td width="250" >Observaciones</td>
				<td width="200" colspan="2"><textarea id="Observacion" name="Observacion" type="textarea" width='200%' cols="50" rows="5" /></textarea></td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input id="guardar" type="button" value="Guardar" onClick="GuardarDatos()" class="styletxt"></td>
			</tr>
		</table>
		</td>
	</tr>
	
</table>
<%}%>
</body>
</html>