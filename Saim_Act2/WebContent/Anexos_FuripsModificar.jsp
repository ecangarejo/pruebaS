<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=utf-8"	import="adm_logica.Conexion" import="java.sql.*" pageEncoding="ISO-8859-1"%>
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
<script language="javascript" type='text/javascript' src="Anexos_FuripsModificar.js"></script>
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

<body >
<%	String codigou = (String) session.getAttribute("codusuario");
	Conexion con = new Conexion();
	ResultSet rs, rs1, rs2, rs3, rs4, rs5;
	Statement st, st1,st2, st3, st4, st5 = null;
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
				<div><a	href="mensajes.jsp?TB_iframe=true&height=520&width=700" class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin%></b></font></a>
				<%if (madmin > 0) {%> 
					<bgsound src="Imagenes/INNERMK.WAV" loop="2">
					<img src="Imagenes/sobr0001.gif" /> 
					<% } %>
				</div>
				</td>
				<td align="right">
				<div align="right" id="usuario" align="right" class="style11"> Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou%>" style="color: white">--Cerrar Session--</a></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<div id="cabecera1" align="center" class="linkmenu"><b> <a href="menu.jsp">Men&uacute; Principal</a>-<a href="Furips.jsp">Furips</a>-Modificar Furips</b></div>
		</td>
	</tr>

	<tr>
		<td id="cabecera2" class="style11" align="center">Modificar Furips</td>
	</tr>
	<% 	
	String CodFrips = request.getParameter("CodFrips");
			rs = null;
			st = null;
			try {
				con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery("SELECT * FROM furips_datos WHERE Codigo="+ CodFrips);
				if (rs.next()) {
	%>
	<tr>
		<td id="datos_victima">
		<table border="0" width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="4" align="center">DATOS</td><p>&nbsp;</p>
				<input type="hidden" name="Codigo" id="Codigo" value="<%=rs.getString(1)%>"/>
				<input type="hidden" name="txtUsuario" id="txtUsuario" value="<%=codigou%>"/>
			</tr>
			<tr>
				<td width='30' align="left">N&uacute;mero de Radicado:</td>
				<td width="50"><input id="radicado" name="radicado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs.getString(3)%>" /></td>
				<td width='30' align="left">Fecha de Radicado:</td>
				<td><input type="text" name="fec_radicado" id="fec_radicado" value="<%=rs.getString(2)%>"></td>
			</tr>
			<tr>
				<td width='30' align="left">N&uacute;mero de Radicado Anterior:</td>
				<td width="50"><input id="antradicado" name="antradicado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs.getString(4)%>" /></td>
				<td width='30' align="left">N&uacute;mero de Factura/ Cuenta de Cobro:</td>
				<td><input id="factura" name="factura" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs.getString(5)%>" /></td>
			</tr>
		</table>
			<% 	rs1 = null;
						st1 = null;
						try {
							con = new Conexion();
							st1 = con.conn.createStatement();
							rs1 = st1.executeQuery("SELECT apac.tipo_documento, apac.numero_documento, apac.nombre, apac.primer_apellido, apac.segundo_apellido, apac.pac_codigo_paciente,apac.fecha_nacimiento,apac.genero,apac.direccion, apac.telefono_celular,apac.telefono_fijo, mun.nombre, dpto.nombre,ent.nombre_entidad FROM adm_paciente apac, adm_convenio con, adm_entidad ent, adm_municipio mun, adm_departamento dpto,  furips_datos fd WHERE fd.Codigo="+CodFrips+" AND apac.tipo_documento= fd.TipoDocumento_fk AND apac.numero_documento=fd.NumeroDocumento_fk AND apac.conv_numero_contrato_fk = con.conv_numero_contrato AND con.ent_nit_contratante_fk = ent.ent_nit AND mun.muni_cod=apac.mun_codigo_fk AND mun.dept_codigo_fk=dpto.dept_codigo");
							if (rs1.next()) {%>
				<table width='100%'>
				  <tr>
					<td><div align='center' class='style11' id='cabecera2' >DATOS DEL PACIENTE </div></td></tr></table>
							<table border='0'> 
								<tr>
								<td width='18%'>Tipo de documento: </td>
								<td width='13%' align='left'> <%=rs1.getString(1)%></td>
								<td width='18%'>Numero de documento: </td>
								<td width='20%' align='left'><%=rs1.getString(2)%></td>
								<td width='20%'>Nombre del Paciente: </td>
								<td width='60%' align='center'><%=rs1.getString(3)+" "+rs1.getString(4)+" "+rs1.getString(5)%></td>
								</tr>
								<tr> 
								<td width='18%'>Fecha Nacimiento: </td>
								<td width='13%' align='left'><%=rs1.getString(7)%></td>
								<td width='18%'>Sexo: </td> 
								<td width='3%' align='left'><%=rs1.getString(8)%></td>
								<td width='3%'>Direccion: </td> 
								<td width='60%' align='left'><%=rs1.getString(9)%></td>
								</tr>
								<tr>
								<td width='18%'>Telefono Celular: </td>
								<td width='13%' align='left'><%=rs1.getString(10)%></td>
								<td width='18%'>Telefono Fijo: </td> 
								<td width='3%' align='left'><%=rs1.getString(11)%></td>
								<td width='3%'>Municipio: </td> 
								<td width='60%' align='left'><%=rs1.getString(12)%></td>
								</tr>
								<tr> 
								<td width='18%'>Departamento: </td>
								<td width='13%' align='left'><%=rs1.getString(13)%></td>
								</tr>
					<%}
					  rs1.getStatement().close();
					  } catch (SQLException e) {
						System.out.println(e);
					    out.println("no se pudo realizar la conexion!<br/>");
					  }%>
			<tr>
				<td width="70">Condicion del Accidentado: &nbsp;</td>
				<td width="50"><input id="condicion" name="condicion" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(8)%>" /></td>
				<td>Conductor, Peaton, Ocupante,Ciclista<span class="style8">*</span></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td id="datos_sitio">
		<table border="0" width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="8" align="center">DATOS DEL SITIO DONDE OCURRIO EL EVENTO CATASTROFICO O ACCIDENTE DE TRANSITO</td><p>&nbsp;</p>
			</tr>
			<tr>
				<form id="form2" name="form2" onkeypress="return pulsar(event);" style="margin-top: -16px;">
				<td width="150"><span class="Estilo8">Naturaleza del evento &nbsp; </span></td>
				<td width="100"><%=rs.getString(9)%><label> <input name="eventonaturaleza" id="eventonaturaleza" width='28%' class="styletxt" type="hidden" value="<%=rs.getString(9)%>" /></td>
			</tr>
			<tr>
				<td width="150">Direcci&oacute;n de la Ocurrencia:</td>
				<td width="100"><input id="DirOcurrencia" name="DirOcurrencia" type="text" width='28%' class="styletxt" value="<%=rs.getString(10)%>" /><span class="style8"> *</span></td>
				<td width="150">Fecha Evento/Accidente:</td>
				<td width="158"><input type="text" name="fec_evento" id="fec_evento" value="<%=rs.getString(11)%>" /><span class="style8"> *</span></td>
				<td width="100">Hora: &nbsp; HH:MM</td>
				<td><input type="text" name="hora" id="hora" class="styletxt" onBlur="CheckTime(this)" value="<%=rs.getString(12)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Departamento:</td>
				<td width="20"><label> <select name="cbdep" size="1" id="cbdep" onChange="ajaxxMun(form2)" style="width: 155px">
					<option value="<%=rs.getString(13)%>"><%=rs.getString(13)%></option>
					<% 	rs1 = null;
						st1 = null;
						try {
							con = new Conexion();
							st1 = con.conn.createStatement();
							rs1 = st1.executeQuery("SELECT Nombre FROM adm_departamento where pais_codigo_fk=" + 1);
							while (rs1.next()) {%>
					<option value="<%=rs1.getString(1)%>"><%=rs1.getString(1)%></option>
					<%}
					  rs1.getStatement().close();
					  } catch (SQLException e) {
						System.out.println(e);
					    out.println("no se pudo realizar la conexion!<br/>");
					  }%>
				</select> <span class="style8">*</span></label></td>
				<td width="100"><span class="Estilo8">Municipio: &nbsp;</span></td>
				<td><label> <select name="cbmun" id="cbmun" onChange="CodigoMun(form2)" style="width: 155px">
					<option value="<%=rs.getString(15)%>"><%=rs.getString(15)%></option>
					<% 	rs1 = null;
						st1 = null;
						try {
							con = new Conexion();
							st1 = con.conn.createStatement();
							rs1 = st1.executeQuery("select muni_cod,nombre FROM adm_municipio  WHERE dept_codigo_fk="+rs.getString(14));
							while (rs1.next()) {%>
					<option value="<%=rs1.getString(2)%>"><%=rs1.getString(2)%></option>
					<%}
					  rs1.getStatement().close();
					  } catch (SQLException e) {
						System.out.println(e);
					    out.println("no se pudo realizar la conexion!<br/>");
					  }%>
				</select><span class="style8"> *</span></label></td>
			</tr>
			<tr>
				<td width="150">Zona: &nbsp;</td>
				<% if ((rs.getString(17)).equals("Urbana")) {%>
				<td width="100"><select name="zona" id="zona" style="width: 155px">
					<option value="<%=rs.getString(17)%>" class="styletxt"><%=rs.getString(17)%></option>
					<option value="Rural">Rural</option>
				</select><span class="style8"> *</span></td>
				<% } else { %>
				<td width="100"><select name="zona" id="zona"
					style="width: 155px">
					<option value="<%=rs.getString(17)%>" class="styletxt"><%=rs.getString(17)%></option>
					<option value="Urbana">Urbana</option>
				</select><span class="style8"> *</span></td>
				<% } %>
			</tr>
			<tr>
				<td colspan="2">Descripcion Breve del Evento Catastrofico o Accidente de Transito:</td>
				<td colspan="4"><textarea id="Descripcion" name="Descripcion" cols="50" rows="5" /><%=rs.getString(18)%></textarea><span class="style8"> *</span></td>
			</tr>
		</table>
		</td>
	</tr>
	<% if ((rs.getString(9)).equals("Accidente de transito")) {
			rs1 = null;
			st1 = null;
			try {
				con = new Conexion();
				st1 = con.conn.createStatement();
				rs1 = st1.executeQuery("SELECT * FROM furips_datosaccidente WHERE DatosCodigo_fk="+ rs.getString(1));
				if (rs1.next()) { %>
	<tr>
		<td id="datos_vehiculo">
		<table border="0" width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL VEHICULO DEL ACCIDENTE DE TRANSITO</td> <p>&nbsp;</p></td>
			</tr>
			<%if((rs1.getString(3).equals("Vehiculo Fantasma"))||(rs1.getString(3).equals("Vehiculo Fuga"))){%>
			<tr>
				<td width="150">Estado de Aseguramiento:</td>
				<td width="20"><input id="aseguramiento" name="aseguramiento" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs1.getString(3)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
            <td width="200">Intervencion de Autoridad: &nbsp;</td>
				<% if ((rs1.getString(9)).equals("Si")) {%>
				<td width="20">Si: <input type="radio" name="Autoridad" value="1" id="Si" checked="checked"> 
							   No: <input type="radio" name="Autoridad" value="1" id="No"></td>
			</tr>
			<% } else { %>
				<td width="20">Si: <input type="radio" name="Autoridad" value="1" id="Si"> 
							   No: <input type="radio" name="Autoridad" value="1" id="No" checked="checked"></td>
			</tr>
			<% } %>
			<tr>
			<td width="200">Cobro Excedente Poliza: &nbsp;</td>
				<% if ((rs1.getString(12)).equals("Si")) { %>
				<td width="20">Si: <input type="radio" name="ExcPoliza" value="1" id="Si" checked="checked"> 
							   No: <input type="radio" name="ExcPoliza" value="1" id="No"></td>
			</tr>
				<% } else { %>
				<td width="20">Si: <input type="radio" name="ExcPoliza" value="1" id="Si"> 
							   No: <input type="radio" name="ExcPoliza" value="1" id="No" checked="checked"></td>
			</tr>
				<% } %>
			<%}else{ %>
			<tr>
				<td width="150">Estado de Aseguramiento:</td>
				<td width="20"><input id="aseguramiento" name="aseguramiento" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs1.getString(3)%>" /><span class="style8"> *</span></td>
				<td width="204">Marca:</td>
				<td width="20"><input id="marca" name="marca" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs1.getString(4)%>" /><span class="style8"> *</span></td>
				<td width="173">Placa:</td>
				<td width="20"><input id="placa" name="placa" type="text" width='28%' class="styletxt" value="<%=rs1.getString(5)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Tipo de Servicio:</td>
				<td width="20"><input id="servicio" name="servicio" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs1.getString(6)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="204">Codigo Aseguradora:</td>
				<td width="20"><label> <select name="CodAseguradora" size="1" id="CodAseguradora" style="width: 155px">
					<% rs2 = null;
					   rs3 = null;
					   st2 = null;
     					try {
							st2 = con.conn.createStatement();
							rs2 = st2.executeQuery("SELECT Descripcion FROM furips_datosaseguradora WHERE CodAseguradora='"+ rs1.getString(7)+ "'");
							if (rs2.next()) {%>
					<option value="<%=rs1.getString(7)%>"><%=rs2.getString(1)%></option>
					      <%}
							st2 = null;
							st2= con.conn.createStatement();
							rs3 = st2.executeQuery("SELECT * FROM furips_datosaseguradora");
							while (rs3.next()) {%>
					<option value="<%=rs3.getString(2)%>"><%=rs3.getString(3)%></option>
					      <%}
							rs2.getStatement().close();
							rs3.getStatement().close();
					 	 } catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
						 }%>
				</select> <span class="style8">*</span></label></td>
				<td width="100">N&uacute;mero de Poliza:</td>
				<td width="20"><input id="Numpoliza" name="Numpoliza" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs1.getString(8)%>" /><span class="style8"> *</span></td>
				<td width="200">Intervencion de Autoridad: &nbsp;</td>
				<% if ((rs1.getString(9)).equals("Si")) {%>
				<td width="20">Si: <input type="radio" name="Autoridad" value="1" id="Si" checked="checked"> 
							   No: <input type="radio" name="Autoridad" value="1" id="No"></td>
			</tr>
			<% } else { %>
				<td width="20">Si: <input type="radio" name="Autoridad" value="1" id="Si"> 
							   No: <input type="radio" name="Autoridad" value="1" id="No" checked="checked"></td>
			</tr>
			<% } %>
			<tr>
				<td width="204">Vigencia Desde:</td>
				<td width="20"><input id="desde" name="desde" type="text" width='28%' class="styletxt" value="<%=rs1.getString(10)%>" /><span class="style8"> *</span></td>
				<td width="100">Hasta:</td>
				<td width="20"><input id="hasta" name="hasta" type="text" width='28%' class="styletxt" value="<%=rs1.getString(11)%>" /><span class="style8"> *</span></td>
				<td width="200">Cobro Excedente Poliza: &nbsp;</td>
				<% if ((rs1.getString(12)).equals("Si")) { %>
				<td width="20">Si: <input type="radio" name="ExcPoliza" value="1" id="Si" checked="checked"> 
							   No: <input type="radio" name="ExcPoliza" value="1" id="No"></td>
			</tr>
				<% } else { %>
				<td width="20">Si: <input type="radio" name="ExcPoliza" value="1" id="Si"> 
							   No: <input type="radio" name="ExcPoliza" value="1" id="No" checked="checked"></td>
			</tr>
				<% } %>
		</table>
		</td>
	</tr>
	<tr>
		<td id="datos_propietariovehiculo">
		<table border="0" width="100%">
			<% rs2 = null;
			   st2 = null;
				try {
				  st2 = con.conn.createStatement();
				  rs2 = st2.executeQuery("SELECT * FROM furips_datospropietario WHERE NumeroDocumento="+ rs1.getString(13));
				  if (rs2.next()) {%>
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL PROPIETARIO DEL VEHICULO</td> <p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Primer Apellido o Razon Social:</td>
				<td><input id="PrimerApellido" name="PrimerApellido" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs2.getString(4)%>" /><span class="style8"> *</span></td>
				<td width="150">Segundo Apellido:</td>
				<td><input id="SegundoApellido" name="SegundoApellido" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs2.getString(5)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Primer Nombre:</td>
				<td><input id="PrimerNombre" name="PrimerNombre" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs2.getString(6)%>" /><span class="style8"> *</span></td>
				<td width="150">Segundo Nombre:</td>
				<td><input id="SegundoNombre" name="SegundoNombre" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs2.getString(7)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td>Tipo de Documento:</td>
				<td><%=rs2.getString(2)%></td>
				<td width="100">Numero de Documento:</td>
				<td width="20"><%=rs1.getString(13)%><input id="NumDocumento" name="NumDocumento" type="hidden" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs1.getString(13)%>" /></td>
			</tr>
			<tr>
				<td width="204">Direccion de Residencia:</td>
				<td width="20"><input id="DirPropietario" name="Dirpropietario" type="text" width='28%' class="styletxt" value="<%=rs2.getString(8)%>" /><span class="style8"> *</span></td>
				<td width="200">Telefono: &nbsp;</td>
				<td width="20"><input id="TelPropietario" name="TelPropietario" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs2.getString(13)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Departamento:</td>
				<td width="20"><label> <select name="dpto" size="1" id="dpto" onChange="ajaxxMuni(form2)" style="width: 155px">
					<option value="<%=rs2.getString(9)%>"><%=rs2.getString(9)%></option>
					<% rs3 = null;
					   st3 = null;
						try {
							con = new Conexion();
							st3 = con.conn.createStatement();
							rs3 = st3.executeQuery("SELECT Nombre FROM adm_departamento where pais_codigo_fk=" + 1);
							while (rs3.next()) {%>
					<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)%></option>
						<%  }
							rs3.getStatement().close();
						} catch (SQLException e) {
							System.out.println(e);
							out.println("no se pudo realizar la conexion!<br/>");
						}%>
				
				</select> <span class="style8">*</span></label></td>
				<td width="200">Municipio: &nbsp;</td>
				<td width="20"><label> <select name="municipio" id="municipio" style="width: 155px">
					<option value="<%=rs2.getString(11)%>"><%=rs2.getString(11)%></option>
					<% 	rs3 = null;
						st = null;
						try {
							con = new Conexion();
							st = con.conn.createStatement();
							rs3 = st.executeQuery("select muni_cod,nombre FROM adm_municipio  WHERE dept_codigo_fk="+rs2.getString(10));
							while (rs3.next()) {%>
					<option value="<%=rs3.getString(2)%>"><%=rs3.getString(2)%></option>
					<%}
					  rs3.getStatement().close();
					  } catch (SQLException e) {
						System.out.println(e);
					    out.println("no se pudo realizar la conexion!<br/>");
					  }%>


				</select> <span class="style8">*</span></label></td>
			</tr>
			<%  }
				rs2.getStatement().close();
				} catch (SQLException e) {
					System.out.println(e);
					out.println("no se pudo realizar la conexion!<br/>");
				}
				}%>
		</table>
		</td>
	</tr>
	<tr>
		<td id="datos_conductorvehiculo">
		<table border="0" width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL CONDUCTOR DEL VEHICULO INVOLUCRADO EN EL ACCIDENTE DE TRANSITO</td><p>&nbsp;</p>
			</tr>
			<% rs3 = null;
			   st3 = null;
				try {
				  	con = new Conexion();
					st3 = con.conn.createStatement();
					rs3 = st3.executeQuery("SELECT * FROM furips_datosconductor WHERE Documento="+ rs1.getString(14));
					if (rs3.next()) {%>
			<tr>
				<td width="150">Primer Apellido:</td>
				<td><input id="PAConductor" name="PAConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs3.getString(4)%>" /><span class="style8"> *</span></td>
				<td width="150">Segundo Apellido:</td>
				<td><input id="SAConductor" name="SAConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)"  value="<%=rs3.getString(5)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Primer Nombre:</td>
				<td><input id="PNConductor" name="PNConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs3.getString(6)%>" /><span class="style8"> *</span></td>
				<td width="150">Segundo Nombre:</td>
				<td><input id="SNConductor" name="SNConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs3.getString(7)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td>Tipo de Documento:</td>
				<td><%=rs3.getString(2)%></td>
				<td width="100">Numero de Documento:</td>
				<td width="20"><%=rs1.getString(14)%><input id="NDconductor" name="NDconductor" type="hidden" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs1.getString(14)%>" /></td>
			</tr>
			<tr>
				<td width="204">Direccion de Residencia:</td>
				<td width="20"><input id="DirConductor" name="DirConductor" type="text" width='28%' class="styletxt" value="<%=rs3.getString(8)%>" /><span class="style8"> *</span></td>
				<td width="200">Telefono: &nbsp;</td>
				<td width="20"><input id="TelConductor" name="TelConductor" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs3.getString(13)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Departamento:</td>
				<td width="20"><label> <select name="dp" size="1" id="dp" onChange="ajaxxMunic(form2)" style="width: 155px">
					<option value="<%=rs3.getString(9)%>"><%=rs3.getString(9)%></option>
					<% 	rs4 = null;
						st4 = null;
						try {
							con = new Conexion();
							st4 = con.conn.createStatement();
							rs4 = st4.executeQuery("SELECT Nombre FROM adm_departamento where pais_codigo_fk=" + 1);
							while (rs4.next()) {%>
					<option value="<%=rs4.getString(1)%>"><%=rs4.getString(1)%></option>
					<%}
					  rs4.getStatement().close();
					  } catch (SQLException e) {
						System.out.println(e);
					    out.println("no se pudo realizar la conexion!<br/>");
					  }%>

				</select> <span class="style8">*</span></label></td>
				<td width="200">Municipio: &nbsp;</td>
				<td width="20"><label> <select name="mun" id="mun" style="width: 155px">
					<option value="<%=rs3.getString(11)%>"><%=rs3.getString(11)%></option>
					<% 	rs4 = null;
						st4 = null;
						try {
							con = new Conexion();
							st4 = con.conn.createStatement();
							rs4 = st4.executeQuery("select muni_cod,nombre FROM adm_municipio  WHERE dept_codigo_fk="+rs3.getString(10));
							while (rs4.next()) {%>
					<option value="<%=rs4.getString(2)%>"><%=rs4.getString(2)%></option>
					<%}
					  rs4.getStatement().close();
					  } catch (SQLException e) {
						System.out.println(e);
					    out.println("no se pudo realizar la conexion!<br/>");
					  }%>


				</select> <span class="style8">*</span></label></td>
				</form>
			</tr>
			<% }
				rs3.getStatement().close();
				} catch (SQLException e) {
					System.out.println(e);
					out.println("no se pudo realizar la conexion!<br/>");
				}%>
		</table>
		</td>
	</tr>
	<%	}
		rs1.getStatement().close();
		} catch (SQLException e) {
			System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>");
		}
		}
	%>
	<input type="hidden" name="Remision" id="Remision" value="<%=rs.getString(19)%>"/>
	<%if ((rs.getString(19)).equals("Si")) { %>
	<tr>
		<td id="remision">
		<table border="0" width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DE REMISION</td> <p>&nbsp;</p>
				
			</tr>
			<tr>
				<td width="150">Tipo de Referencia:</td>
				<% if ((rs.getString(20)).equals("Remision")) { %>
				<td width="50">Remision:<input type="radio" id="Remision" name="referencia" value="Remision" checked="checked"> 
							   Orden de Servicio: <input type="radio" name="referencia" id="Orden de Servicio" value="OrdenServicio"><span class="style8"> *</span></td>
				<% } else { %>
				<td width="50">Remision:<input type="radio" id="Remision" name="referencia" value="Remision"> 
							   Orden de Servicio: <input type="radio" name="referencia" id="Orden de Servicio" value="OrdenServicio" checked="checked"> <span class="style8"> *</span></td>
				<% } %>
			</tr>
			<tr>
				<td width="150">Fecha Remision:</td>
				<td><input type="text" id="fec_remision" name="fec_remision" value="<%=rs.getString(21)%>" /><span class="style8">*</span></td>
				<td width="150">Hora: HH:MM</td>
				<td><input id="hora_remision" name="hora_remision" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)" value="<%=rs.getString(22)%>" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td>Prestador que remite:</td>
				<td><input id="PrestadorRemite" name="PrestadorRemite" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(23)%>" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="204">Codigo Inscripcion:</td>
				<td width="20"><input id="CodInsRemite" name="CodInsRemite" type="text" width='28%' class="styletxt" value="<%=rs.getString(24)%>" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="100">Profesional que remite:</td>
				<td width="20"><input id="ProfesionalRemite" name="ProfesionalRemite" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(25)%>" /><span class="style8">*</span></td>
				<td width="200">Cargo: &nbsp;</td>
				<td width="20"><input id="CargoRemite" name="CargoRemite" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(26)%>" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="150">Fecha Aceptacion:</td>
				<td><input type="text" id="fec_aceptacion" name="fec_aceptacion" value="<%=rs.getString(27)%>" /><span class="style8"> *</span></td>
				<td width="150">Hora: HH:MM</td>
				<td><input id="hora_aceptacion" name="hora_aceptacion" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)" value="<%=rs.getString(28)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td>Prestador que recibe:</td>
				<td><input id="PrestadorRecibe" name="PrestadorRecibe" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(29)%>" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="204">Codigo Inscripcion:</td>
				<td width="20"><input id="CodInsRecibe" name="CodInsRecibe" type="text" width='28%' class="styletxt" value="<%=rs.getString(30)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Profesional que recibe:</td>
				<td width="20"><input id="ProfesionalRecibe" name="ProfesionalRecibe" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(31)%>" /><span class="style8">*</span></td>
				<td width="200">Cargo: &nbsp;</td>
				<td width="20"><input id="CargoRecibe" name="CargoRecibe" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(32)%>" /><span class="style8">*</span></td>
			</tr>
		</table>
		</td>
	</tr>
	<% }%>
	<input type="hidden" name="Transporte" id="Transporte" value="<%=rs.getString(33)%>"/>
	<%	if ((rs.getString(33)).equals("Si")) {%>
	<tr>
		<td id="amparo">
		<table border="0" width="100%">
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">AMPARO DE TRANSPORTE Y MOVILIZACION DE LA VICTIMA</td> <p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Datos de Vehiculo</td>
			</tr>
			<tr>
				<td width="150">Placa No:</td>
				<td><input id="placatrans" name="placatrans" type="text" width='28%' class="styletxt" value="<%=rs.getString(34)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Transporto la victima desde:</td>
				<td width="20"><input id="transdesde" name="transdesde" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(35)%>" /><span class="style8">*</span></td>
				<td width="200">Hasta: &nbsp;</td>
				<td width="20"><input id="transhasta" name="transhasta" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs.getString(36)%>" /><span class="style8">*</span></td>
			</tr>
			<tr>
				<td width="150">Tipo de Transporte:</td>
				<% if ((rs.getString(37)).equals("Ambulancia Medicalizada")) { %>
				<td><select name="transporte" id="transporte" style="width: 155px">
					<option value="Ambulancia Medicalizada">Ambulancia Medicalizada</option>
					<option value="Ambulancia Basica">Ambulancia Basica</option>
					<span class="style8">*</span>
				</select></td>
				<% } else { %>
				<td><select name="transporte" id="transporte" style="width: 155px">
					<option value="Ambulancia Basica">Ambulancia Basica</option>
					<option value="Ambulancia Medicalizada">Ambulancia Medicalizada</option>
					<span class="style8">*</span>
				</select></td>
				<% } %>
				<td width="150">Lugar donde recogio la victima:</td>
				<% if ((rs.getString(37)).equals("Urbana")) { %>
				<td><select name="lugarvictima" id="lugarvictima" style="width: 155px">
					<option value="Urbana">Urbana</option>
					<option value="Rural">Rural</option>
					<span class="style8">*</span>
				</select></td>
				<% } else { %>
				<td><select name="lugarvictima" id="lugarvictima" style="width: 155px">
					<option value="Rural">Rural</option>
					<option value="Urbana">Urbana</option>
					<span class="style8">*</span>
				</select></td>
				<% } %>
			</tr>
		</table>
		</td>
	</tr>
	<% } %>
	<tr>
		<td id="certificacion_medica">
		<table border="0" width="100%">
			<% rs3 = null;
			   st3 = null;
				try {
				 	con = new Conexion();
				    st3 = con.conn.createStatement();
					rs3 = st3.executeQuery("SELECT * FROM furips_datoscertificacion WHERE DatosCodigo_fk="+ rs.getString(1));
					if (rs3.next()) { %>
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">CERTIFICACION DE LA ATENCION MEDICA DE LA VICTIMA COMO PRUEBA DEL ACCIDENTE O EVENTO</td>	<p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Fecha de Ingreso:</td>
				<td><input type="text" id="fec_Ingreso" name="fec_Ingreso" value="<%=rs3.getString(3)%>" /><span class="style8"> *</span></td>
				<td width="150">Hora:</td>
				<td><input id="hora_ingreso" name="hora_ingreso" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)" value="<%=rs3.getString(4)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="150">Fecha de Egreso:</td>
				<td><input type="text" id="fec_Egreso" name="fec_Egreso" value="<%=rs3.getString(5)%>" /><span class="style8"> *</span></td>
				<td width="150">Hora:</td>
				<td><input id="hora_egreso" name="hora_egreso" type="text" width='28%' class="styletxt" onBlur="CheckTime(this)" value="<%=rs3.getString(6)%>" /><span class="style8"> *</span></td>
			</tr>
			<tr>
				<td width="100">Codigo Diagnostico principal de Ingreso:</td>
				<td width="20">
					<% st4 = null;
						try {
							st4 = con.conn.createStatement();
							rs4 = st4.executeQuery("SELECT nombre FROM cie10 WHERE codigoCIE='"+ rs3.getString(7) + "'");
							if (rs4.next()) {%>
							<input name="diagini" type="text" id="diagini" onkeyUp="AutoCompleta2('1')" style="width: 200px" value="<%=rs4.getString(1)%>">
							<input type="hidden" name="DiagPrincipalIngreso" id="DiagPrincipalIngreso" value="<%=rs3.getString(7) %>"/> <span class="style8"> *</span>
					        <div id="DiagPrincipalIng"></div>
				          <%}
					       rs4.getStatement().close();
						  } catch (SQLException e) {
								System.out.println(e);
								out.println("no se pudo realizar la conexion!<br/>");
							};	%>
				</td>
				<td width="200">Codigo Diagnostico principal de Egreso: &nbsp;</td>
				<td width="20">
 				<% 	st5 = null;
						  try {
							 if((rs3.getString(10)).equals("")){%>
							<input name="diagegre" type="text" id="diagegre" onkeyUp="AutoCompleta2('2')" style="width: 200px" />
							<input type="hidden" name="DiagPrincipalEgreso" id="DiagPrincipalEgreso" /> <span class="style8"> *</span>
							<div id="DiagPrincipalEgr"></div> 
						<%	 } else{
							st5 = con.conn.createStatement();
							rs5 = st5.executeQuery("SELECT nombre FROM cie10 WHERE codigoCIE='"+ rs3.getString(10) + "'");
							if (rs5.next()) {%>
							<input name="diagegre" type="text" id="diagegre" onkeyUp="AutoCompleta2('2')" style="width: 200px" value="<%=rs5.getString(1)%>">
							<input type="hidden" name="DiagPrincipalEgreso" id="DiagPrincipalEgreso" value="<%=rs3.getString(10)%>" /> <span class="style8"> *</span>
							<div id="DiagPrincipalEgr"></div>
				          <%}
					       rs5.getStatement().close();
							 }
						  } catch (SQLException e) {
								System.out.println(e);
								out.println("no se pudo realizar la conexion!<br/>");
						  };%>
				</td>
			</tr>
			<tr>
				<td width="100">Otro Codigo de Ingreso:</td>
				<td width="20">
					<% st5 = null;
						try {
						if ((rs3.getString(8)).equals("")) {%>
					<input name="Otrodiaging" type="text" id="Otrodiaging" onkeyUp="AutoCompleta2('3')" style="width: 200px">
					<input type="hidden" name="OtroDiagIngreso" id="OtroDiagIngreso" /> 
					<div id="OtroDiagIng"></div>
						<%} else {
							st5 = con.conn.createStatement();
							rs5 = st5.executeQuery("SELECT nombre FROM cie10 WHERE codigoCIE='"+ rs3.getString(8)+ "'");
							if (rs5.next()) {%>
							<input name="Otrodiaging" type="text" id="Otrodiaging" onkeyUp="AutoCompleta2('3')" style="width: 200px" value="<%=rs5.getString(1)%>">
							<input type="hidden" name="OtroDiagIngreso" id="OtroDiagIngreso" value="<%=rs3.getString(8)%>" /> 
							<div id="OtroDiagIng"></div>
				        <%}
					      rs5.getStatement().close();
						}
						  } catch (SQLException e) {
								System.out.println(e);
								out.println("no se pudo realizar la conexion!<br/>");
							};
					%>
				</td>
				<td width="200">Otro Codigo de Egreso: &nbsp;</td>
				<td width="20">
					<% st5 = null;
						try {
							if ((rs3.getString(11)).equals("")) {	%>
				              <input name="Otrodiagegre" type="text" id="Otrodiagegre" onkeyUp="AutoCompleta2('4')" style="width: 200px">
					          <input type="hidden" name="OtroDiagEgreso" id="OtroDiagEgreso" />
					          <div id="OtroDiagEgr"></div>
						<%  }else {
								st5 = con.conn.createStatement();
								rs5 = st5.executeQuery("SELECT nombre FROM cie10 WHERE codigoCIE='"+ rs3.getString(11)+ "'");
								if (rs5.next()) {%>
								  <input name="Otrodiagegre" type="text" id="Otrodiagegre" onkeyUp="AutoCompleta2('4')" style="width: 200px" value="<%=rs5.getString(1)%>">
								  <input type="hidden" name="OtroDiagEgreso" id="OtroDiagEgreso" value="<%=rs3.getString(11)%>" />
								  <div id="OtroDiagEgr"></div>
							<% }
								rs5.getStatement().close();
   							}
								} catch (SQLException e) {
									System.out.println(e);
									out.println("no se pudo realizar la conexion!<br/>");
								}%>
				</select></td>
			</tr>
			<tr>
				<td width="100">Otro Codigo de Ingreso:</td>
				<td width="20">
					<% st = null;
						 try {
							if (rs3.getString(9).equals("")) {%>
						<input name="Otrodiaging2" type="text" id="Otrodiaging2" onkeyUp="AutoCompleta2('5')" style="width: 200px">
					    <input type="hidden" name="OtroDiagIngreso2" id="OtroDiagIngreso2" />
					    <div id="OtroDiagIng2"></div>
							<%  }else {
								st = con.conn.createStatement();
								rs5 = st.executeQuery("SELECT nombre FROM cie10 WHERE codigoCIE='"+ rs3.getString(9)+ "'");
								if (rs5.next()) {%>
						<input name="Otrodiaging2" type="text" id="Otrodiaging2" onkeyUp="AutoCompleta2('5')" style="width: 200px" value="<%=rs5.getString(1)%>">
					    <input type="hidden" name="OtroDiagIngreso2" id="OtroDiagIngreso2" value="<%=rs3.getString(9)%>" />
					    <div id="OtroDiagIng2"></div>
					
							 <% }
								rs5.getStatement().close();
								 }
								} catch (SQLException e) {
									System.out.println(e);
									out.println("no se pudo realizar la conexion!<br/>");
								}%>
				</select></td>
				<td width="200">Otro Codigo de Egreso: &nbsp;</td>
				<td width="20">
					<% st = null;
						try {
							if ((rs3.getString(12)).equals("")) {	%>
							<input name="Otrodiagegre2" type="text" id="Otrodiagegre2" onkeyUp="AutoCompleta2('6')" style="width: 200px">
					        <input type="hidden" name="OtroDiagEgreso2" id="OtroDiagEgreso2" />
					        <div id="OtroDiagEgr2"></div>
							<% } else {
							  st = con.conn.createStatement();
							  rs5 = st.executeQuery("SELECT nombre FROM cie10 WHERE codigoCIE='"+ rs3.getString(12)+ "'");
							 if (rs5.next()) {%>
							<input name="Otrodiagegre2" type="text" id="Otrodiagegre2" onkeyUp="AutoCompleta2('6')" style="width: 200px" value="<%=rs5.getString(1)%>">
							<input type="hidden" name="OtroDiagEgreso2" id="OtroDiagEgreso2" value="<%=rs3.getString(12)%>"  />
							<div id="OtroDiagEgr2"></div>
							<% }
							  rs5.getStatement().close();
							}
							} catch (SQLException e) {
								System.out.println(e);
								out.println("no se pudo realizar la conexion!<br/>");
							}%>
				</select></td>
			</tr>
			<% }
				rs3.getStatement().close();
				} catch (SQLException e) {
					System.out.println(e);
					out.println("no se pudo realizar la conexion!<br/>");
				}%>
		</table>
		</td>
	</tr>
	<tr>
		<td id="medico">
		<table border="0" width="100%">
			<% st4 = null;
				try {
				  st4 = con.conn.createStatement();
				  rs4 = st4.executeQuery("SELECT * FROM furips_datosmedico WHERE NumeroDocumento="+ rs.getString(39));
					if (rs4.next()) {%>
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">DATOS DEL MEDICO O PROFESIONAL TRATANTE</td> <p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150">Primer Apellido:</td>
				<td><input id="PAMedico" name="PAMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs4.getString(5)%>" /><span class="style8">*</span></td>
				<td width="150">Segundo Apellido:</td>
				<td><input id="SAMedico" name="SAMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs4.getString(6)%>" /></td>
			</tr>
			<tr>
				<td width="150">Primer Nombre:</td>
				<td><input id="PNMedico" name="PNMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs4.getString(7)%>" /><span class="style8">*</span></td>
				<td width="150">Segundo Nombre:</td>
				<td><input id="SNMedico" name="SNMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return soloLetras(event)" value="<%=rs4.getString(8)%>" /></td>
			</tr>
			<tr>
				<td>Tipo de Documento:</td>
				<td><%=rs4.getString(2)%></td>
				<td width="100">Numero de Documento:</td>
				<td width="20"><%=rs.getString(39)%><input id="NDMedico" name="NDMedico" type="hidden" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs.getString(39)%>" /></td>
			</tr>
			<tr>
				<td width="150">Numero de Registro Medico:</td>
				<td><input id="RegistroMedico" name="RegistroMedico" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs4.getString(4)%>" /><span class="style8">*</span></td>
			</tr>
			<% }
			   rs4.getStatement().close();
				} catch (SQLException e) {
					System.out.println(e);
					out.println("no se pudo realizar la conexion!<br/>");
				}%>
		</table>
		</td>
	</tr>
	<tr>
		<td id="amparo_reclama">
		<table border="0" width="100%">
			<% st5 = null;
				try {
					st5 = con.conn.createStatement();
					rs5 = st5.executeQuery("SELECT * FROM furips_datosamparo WHERE DatosCodigo_fk="+ rs.getString(1));
					if (rs5.next()) {%>
			<tr>
				<td id="cabecera2" class="style11" colspan="9" align="center">AMPAROS QUE RECLAMA</td> <p>&nbsp;</p>
			</tr>
			<tr>
				<td width="150"></td>
				<td width="50">Valor Total Facturado</td>
				<td width="50">Valor Reclamado al Fosyga</td>
			</tr>
			<tr>
				<td width="150">Gastos Medicos Quirurgicos:</td>
				<td><input id="GastoMedicoFacturado" name="GastoMedicoFacturado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs5.getString(3)%>" /><span class="style8">*</span></td>
				<td><input id="GastoMedicoFosyga" name="GastoMedicoFosyga" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs5.getString(4)%>" /></td>
			</tr>
			<tr>
				<td width="150">Gastos de transporte y movilizacion de la victima:</td>
				<td><input id="GastoTransFacturado" name="GastoTransFacturado" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs5.getString(5)%>" /><span class="style8">*</span></td>
				<td><input id="GastoTransFosyga" name="GastoTransFosyga" type="text" width='28%' class="styletxt" onkeypress="javascript:return validarNro(event)" value="<%=rs5.getString(6)%>" /></td>
			</tr>
			<% 	}
				rs5.getStatement().close();
				} catch (SQLException e) {
					System.out.println(e);
					out.println("no se pudo realizar la conexion!<br/>");
				}%>
			<tr>
				<%
				if((rs.getString(9)).equals("Accidente de transito")){
					if((rs.getString(19)).equals("Si")){
						if((rs.getString(33)).equals("Si")){%>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos()" class="styletxt"></td>
				<%		}else{ %>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos2()" class="styletxt"></td>
				<%		}
					}else{
						if((rs.getString(33)).equals("Si")){%>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos3()" class="styletxt"></td>
				<%		}else{ %>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos4()" class="styletxt"></td>
				<%		}
					}
				}else{
					if((rs.getString(19)).equals("Si")){
						if((rs.getString(33)).equals("Si")){%>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos5()" class="styletxt"></td>
				<%		}else{ %>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos6()" class="styletxt"></td>
				<%		}
					}else{
						if((rs.getString(33)).equals("Si")){%>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos7()" class="styletxt"></td>
				<%		}else{ %>
							<td colspan="9" align="center"><input id="ActualizarDatos" type="button" value="Actualizar Datos" onClick="ActualizarDatos8()" class="styletxt"></td>
				<%		}
					}
				}%>
			</tr>
		</table>
		</td>
	</tr>
</table>
<% }
				rs.getStatement().close();
		} catch (Exception ex) {
			System.out.println("Error select mostrar datos " + ex);
		}
	}%> 
</body>
</html>