<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Datos Formatos Pendientes</title>
		<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
		<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
		<link rel="StyleSheet" href="dtree.css" type="text/css" />
		<script type="text/javascript" src="dtree.js"></script>
		<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
		
		<script language="javascript" type="text/javascript" src="hic_SeleccionarPacientes3.js"></script>
		<script type="text/javascript" src="jquery.js"></script>
		<script type="text/javascript" src="thickbox.js"></script>
		<link rel="stylesheet" href="thickbox.css" type="text/css" />
<%
	String CodUsuario= request.getParameter("CodUsuario");
	String pend=request.getParameter("pend");
	String codigou = (String) session.getAttribute("codusuario");
	if (codigou == null){
		codigou = "";
	}
%>
<script language="javascript" type="text/javascript" >
function onloaddatos(){
	var CodUsuario2=<%=CodUsuario%>;
	var pend=<%=pend%>;
	//alert("datos");
	//alert(pend);
	onloaddatosl(CodUsuario2, pend);
	//alert(CodUsuario2);
}
</script>

</head>

<body onload="onloaddatos()">
<%
	if (codigou.equals("")) {
%>
		<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
			window.location.href="Seg_login.jsp";
		</script>
<%
	} else {
		String idu = "";
		Conexion con = new Conexion();
		Statement st2 = con.conn.createStatement();
		ResultSet rs2 = st2.executeQuery("select usuario from seg_usuario  where usu_codigo=" + codigou);
		while (rs2.next()) {
			idu = rs2.getString(1);
		}
		rs2.getStatement().close();
		//con.cerrar();
		
		%>
		
		<br><br><br>
		<table align="center" class="style66" border="2">
		<br>
		<tr>
		<td>
		<center>FORMATOS PENDIENTES POR CERRAR</center>
		<div class="style66" id="Listaformatos" align="center">
		<br>
		<br>
		
		<br>
		<br>
		
		</div>
		</td>
		</tr>
		</table>
		<table class="style66" align="center">
		<tr >
		<td >
		<br><br>
		
		SI USTED NECESITA GENERAR UNA ORDEN MEDICA A UN PACIENTE URGENTEMENTE PRESIONE  <a href="hic_BuscarPacientes2.jsp" style="color:red">AQUI</a>
		</td>
		</tr>
		</table>
		<%
		}

		
	
%>