<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Pacientes con mas de 24 Horas </title>
		<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
		<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
		<script language="javascript" type="text/javascript" src="clickderecho.js"></script>	
		<script type="text/javascript" src="jquery.js"></script>
		<script type="text/javascript" src="thickbox.js"></script>
		<link rel="stylesheet" href="thickbox.css" type="text/css" />
<%
	//String CodUsuario= request.getParameter("CodUsuario");
	String codigou = (String) session.getAttribute("codusuario");
	if (codigou == null){
		codigou = "";
	}
%>
<script language="javascript" type="text/javascript" >
function valrep24h(){
	var user=<%=codigou%>;
	Val24h(user);
	
}

</script>

</head>

<body onload="valrep24h()">
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
		
		<table>
						<tr>
							<td >
											<form id="form1" name="form1" >
											<div id="Pacientes"  > </div>
											</form>
									</td>
						</tr>
		</table>
		<%
		}%>