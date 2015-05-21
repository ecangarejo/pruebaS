<%@ page language="java" contentType="text/html; charset=utf-8"	import="adm_logica.Conexion" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<!DOCTYPE html>

<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<title>Pacientes Capitados</title>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
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
	function Verificar(e) {
		var minavegador = new Browser();
		if (minavegador.name == 'firefox') {
			if (e)document.onkeypress = function() {
					return true;
			};
			var evt = e ? e : event;
			if (evt.keyCode == 116) {
				if (e)document.onkeypress = function() {
						return false;
				};
				else {
					evt.keyCode = 0;
					evt.returnValue = false;
				}
			}
		}
	}

	function cargar(){
		//alert();
		document.getElementById('bt').disabled=true;
		document.getElementById('cargarg').innerHTML='<p>Generando... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	}
</script>
</head>
<body>
<%
	String codigou = (String) session.getAttribute("codusuario");
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
				<div>
					<a	href="mensajes.jsp?TB_iframe=true&height=520&width=700" class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin%></b></font></a>
					<%if (madmin > 0) {%> <bgsound src="Imagenes/INNERMK.WAV" loop="2"> <img src="Imagenes/sobr0001.gif" /> <%}%>
				</div>
				</td>
				<td align="right">
				<div align="right" id="usuario" align="right" class="style11">
					Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou%>" style="color: white">--Cerrar Session--</a></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<div id="cabecera1" align="center" class="linkmenu"><b> <a href="menu.jsp">Men&uacute; Principal</a>-Pacientes Capitados</b></div>
		</td>
	</tr>
	<tr>
		<td>
		<form action="ControlBDCapitado" method="post" enctype="multipart/form-data"> 
		<table width="100%" align="center" border='1'>
			<tr>
				<td id="cabecera2" class="style11" colspan="5" align="center">Paciente Capitados</td>
			</tr>
			<tr>
				<td>Entidad:</td>
				<td colspan="5"><select name="codempresa">
					<%
						rs = null;
						st = null;
							try {
								con = new Conexion();
								st = con.conn.createStatement();
								rs = st.executeQuery("SELECT aent.nombre_entidad,aent.ent_nit FROM adm_entidad aent,adm_convenio acv,fact_convenios fcv WHERE fcv.cod_entidad=aent.ent_nit AND fcv.estado=0 AND acv.ent_nit_contratante_fk=aent.ent_nit ORDER BY aent.nombre_entidad");
								while (rs.next()) {
					%>
					<option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
					<%		}
								rs.getStatement().close();
								con.cerrar();
							} catch (SQLException e) {
								System.out.println(e);
								out.println("no se pudo realizar la conexion!<br/>");
							}
					%>

				</select></td>
				<td><br />
				</td>
			</tr>
			<tr>
				<td>Urgencia: <input type="checkbox" id="urg" name="urg" /></td>
				<td>Consulta Externa: <input type="checkbox" id="cex" name="cex" /></td>
				<td>Hospitalizacion: <input type="checkbox" id="hosp" name="hosp" /></td>
				<td>Ambulatorio: <input type="checkbox" id="amb" name="amb" /> </td>
				<td>Promocion y Prevencion: <input type="checkbox" id="pyp" name="pyp" /></td>
			</tr>
			<tr>
				<td colspan="4">Seleccione un archivo: <input type="file" name="file" /></td>
			</tr>
			<tr id="p">
				<td align="center" colspan="5"><input id='bt' type="submit" value="Subir archivo" onClick="cargar()"/></td>
			</tr>
	<tr><td colspan="5" ><div id='cargarg'>.</div></td></tr>
		</table>
		</form>
		</td>
	</tr>
</table>
<% 	} %>
</body>
</html>