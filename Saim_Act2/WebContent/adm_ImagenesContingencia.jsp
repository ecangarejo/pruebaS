<%@ page language="java" contentType="text/html; charset=utf-8" import="adm_logica.Conexion" import="java.sql.*" 
pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Imagenologia</title>

<script language="javascript">
function A(tecla,e){
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){BuscarPacienteImgContingencia();}
}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

</script>
<script src="Adm_ConsultarContingencia.js" type="text/javascript"></script>
<script src="Validaciones.js" type="text/javascript"></script>
<script languaje="javascript" src="AutocompletarCIE10Egreso.js"></script>
<script languaje="javascript" src="AutocompletarCIE10Relacion1.js"></script>
<script languaje="javascript" src="AutocompletarCIE10Relacion2.js"></script>
<script languaje="javascript" src="AutocompletarCIE10.js"></script>

</head>
<body onload='' id="back">



<table width="100%">
<tr > 
<td>

</td>
</tr>


<tr>
<td>
</td>
</tr>
  
<tr  id="principal1">
<td>         

<div>

	<table width="100%"> 
		<tr>
			<td width="100%">   
					<br />

<form id="form1"  name="form1" style="margin-top:-17px;" onkeypress = "return pulsar(event);">
  <table width="100%" border="0" align="center" >
    <tr  id="cabecera2" class="style11" >
      <td colspan="6"><div align="center">BUSCAR PACIENTE</div></td>
    </tr>
    <tr>
      <td width="139">Tipo de Documento</td>
      <td width="115"><select name="cbafiliacion" size="1" id="cbafiliacion">
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
 	st3.close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
      </select></td>
      <td width="169">Numero de Documento</td>
      <td width="144"><input type="text" name="txtnumdoc" id="txtnumdoc" onkeydown="A(this,event)" />
      <td width="36"><input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Buscar" onClick="BuscarPacienteImgContingencia()"></td>
      <td width="164">&nbsp;</td>
    </tr>
    <tr><td colspan="6"><div id="Admisiones"></div></td></tr>
	
  </table>
  </form></td></tr></table>
</div>
</td></tr></table>
</body>
</html>