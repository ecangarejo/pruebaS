<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<%
String CodAdm=request.getParameter("CodAdm");
if(CodAdm==null){
	CodAdm="";
}

String CodPac=request.getParameter("CodPac");
if(CodPac==null){
	CodPac="";
}

%>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Crear Area</title>
<style type="text/css">
.style1 {color: #FFFFFF}
.style2 {font-size: xx-small}
.style3 {font-size: x-small; }
body {
	background-image: url(Imagenes/ultima.jpg);
	background-repeat: repeat-y;
}
.style4 {color: #FF0000}
.style5 {font-size: x-small; color: #FF0000; }
</style>
<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias { border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
</style>
<script src="hic_OrdenServicio.js" type="text/javascript"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language='javascript'>
function OrdenServicio(){
var CodAdm=<%=CodAdm%>;
	var CodPac=<%=CodPac%>;
	//BuscarPaciente(CodPac,CodAdm);
	CargarOrden(CodPac);
}
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>

<body onload="OrdenServicio()">
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} */
</script>
<%
if(codigou==null){
	codigou="";
}
if(codigou.equals("")){%>

<%}else{
%>
<%
Conexion con=new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	   String idu="";         
	      	
	       	st2 = con.conn.createStatement();
	       	
	       	rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
				<%while(rs2.next()){
					
		        idu=rs2.getString(1);
				}
				rs2.getStatement().close();
				con.cerrar();
	       	%>
<div id="contenedor">

	<div id="cabecera"><img src="Imagenes/Imagenologia.jpg" width="1024" height="112" /></div>
	<div id="cabecera2">
	  <div align="right" class="style2">Usted Ingreso al Sistema Como:<%=idu %></div>
	</div>

				<div id="lateral">
			
				<a href="Seguridad_login?accion=cerrar" class="texto" style="text-decoration:blink" style="color:#FF0000"> --Cerrar Session--</a><br><br>
                <a href="HistoriaClinica.jsp" class="style3">Volver al Menu</a>
          
		</div>
		<div id="principal">
<form  id="form1" name="form1">
 <div id="formulario"></div>
</form>
</div>
</div>
	<%} %>
</body>
</html>