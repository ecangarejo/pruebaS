

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Realizacion de Examen</title>

<style type="text/css">
#sugerencias  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias { border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
#sugerenciasRe  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerenciasRe { border:1px solid black; display:none; margin-left:0.8px;}
#sugerenciasRe ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerenciasRe ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;} 
.scrollbox {height : 90px ; overflow:scroll}
</style>

<script language=javascript src="lab_autoRealizarExamen.js"></script>
<script language=javascript src="lab_AsignarExamenesModulo.js"></script>
<script language="javascript">

</script>


</head>
<body onload="fecha_gru(),hora_gru();" id="back" >
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
//System.out.println("codigo"+codigou);
if(codigou.equals("")){%>

<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");window.location.href="Seg_login.jsp";</script>
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
			//	con.cerrar();
	       	%>

<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Realizacion de Examenes-Asignar Laboratorios</b></div>
</td>
</tr>

<tr  id="principal1" >
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
	
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					
								<%@include file="Copiamenu.jsp"%>
			</td>

					<td width="100%">
						  
						  						 <form id="form1" style="margin-top:-17px;" name="form1" onkeypress = "return pulsar(event);" >
						
						   <table width="100%"  align="center" class="style6" >   
						  
						     <tr id="cabecera2">
						       <td colspan="5"><div align="center" class="style11">DATOS DEL PACIENTE </div></td>
						     </tr>
						
						     <tr>
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						       <td width="65">&nbsp;</td>
						       <td width="328">&nbsp;</td>
						     </tr>
						     <tr>
						       <td>Tipo de Documento</td>
						       <td><select name="cbafiliacion" size="1" id="cbafiliacion">
						         <% 
 ResultSet rs3=null;
 Statement st3 = null;  
 try{
 	Conexion con2=new Conexion();
 	st3 = con2.conn.createStatement();
 	rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)%></option>
 	<%}
 	rs3.getStatement().close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
	
						         
						         
						       </select></td>
						       <td>Numero</td>
						       <td><input type="text" name="txtnumdoc" id="txtnumdoc" onkeyup="validarcom(this,event),autocompleta_realizar();" maxlength="12"  /></td>
						     </tr>
						     <tr>
						       <td width="177">&nbsp;</td>
						       <td width="110"> <label></label>  </td>
						       <td colspan="2"><div id="sugerenciasRe"></div></td>   
					         </tr>
						<input type="hidden" name="txtusu" id="txtusu" value="<%=codigou %>"/>
						<input name="fechagru" type="hidden" id="fechagru"  />
						<input name="horagru" type="hidden" id="horagru"  />
				
<tr>
  <td colspan="4"><div id="formulario"></div></td>
</tr>
</table>
    <%}%>
</body>
</html>
