<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" type='text/javascript' src='Browser.js'></script>
<script language="javascript">
	function A(e) {
		tecla = e.keyCode;
		e.which;
		if (tecla == 13) {
			
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
		<div id="cabecera1" align="center" class="linkmenu"><b> <a href="menu.jsp">Men&uacute; Principal</a>-Pagos a Facturas</b></div>
		</td>
	</tr>
	<tr>
		<td>
		<form action="ControlRecibosCaja" method="post" enctype="multipart/form-data"> 
		<table width="100%" align="center" border='1' cellspacing='0'>
			<tr>
				<td id="cabecera2" class="style11" colspan="6" align="center">Recibos de Caja</td>
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

				</select> Busqueda Agrupada<input type='checkbox' name='unidos' id='unidos'/></td>
				
			</tr>
			<tr>
		
			</tr>
			<tr>
				<td colspan="6">Seleccione un archivo: <input type="file" name="file" /></td>
			</tr>
			<tr id="p">
				<td align="center" colspan="6"><input id='bt' type="submit" value="Subir archivo" onClick="cargar()"/></td>
			</tr>
	<tr><td colspan="6" ><div id='cargarg'></div></td></tr>
		</table>
		</form>
		</td>
	</tr>
</table>
<% 	} %>
</body>
</html>