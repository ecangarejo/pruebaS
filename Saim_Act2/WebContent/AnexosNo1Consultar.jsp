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

<body onLoad="Furips();">
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
				<a href="menu.jsp">Men&uacute; Principal</a>-Ver Anexos Tecnicos No 1</b>
			</div>
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
				<td><input name="btnBuscarPac" type="button" id="btnBuscarPac"value="Buscar" onClick="consultar()"></td>
			</tr>
		</table>
		</td>
	</tr>	


<tr>
		<td>
		<div id="datos"></div>
		</td>
	</tr>
</table>
<%}%>
</body>
</html>