<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
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
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Asignar Cuenta Por Cobrar</title>
<style type="text/css">

#Cuentas li:hover{Cursor : pointer;background-color: #cccccc; }
#Cuentas {height:75px; border:1px solid black; display:none; overflow : scroll ;}
#Cuentas ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#Cuentas ul li {padding: .2em; border-bottom: 1px solid silver;}

</style>
<script> 
function A(tecla,e){
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){/*BuscarCuenta();*/}
}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

</script>   
<script languaje="javascript" src="cont_Facturas.js"></script>
<script languaje="javascript" src="cont_AutocompletarCuentas.js"></script>
</head>
<body id="back" onload="fecha(),hora()">

<%String codigou =(String)session.getAttribute("codusuario");
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
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				rs4.getStatement().close();
				con.cerrar();
	       	%>

<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>

<table width="100%">
<tr >
<td>
	  <table width="100%">
			<tr>
			<td>
			<div > <a  href="mensajes.jsp?TB_iframe=true&height=520&width=700"  class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin %></b></font></a> <%if (madmin>0){ %><bgsound src="Imagenes/INNERMK.WAV" loop="2"><img src="Imagenes/sobr0001.gif" /><%}%></div>
			</td>
			<td align="right">
			<div align="right" id="usuario" align="right" class="style11">Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou %>" style="color: white">--Cerrar Session--</a></div>
			</td>
			
		</tr>
	</table>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Contabilidad.jsp">Contabilidad</a>-Cuentas Por Cobrar-Asignar Cuenta Por Cobrar</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>
					<br />
		  <form id="form1" style="margin-top:-15px;" name="form1" onkeypress = "return pulsar(event);" >
		  <table width="100%" > 
		<tr>

			<td width="100%">

<table border='1' width='100%' ><tr><td height='29'><div id='cabecera2' class='style11' align='center'>ASIGNAR CUENTAS POR COBRAR </div></td></tr></table>

  <table width='100%' border='0'><tr class='style3'>
    <td width='75' class='style3'>CUENTA<span class='style8'>*</span></td><td colspan='2'><input name='txtCuenta' type='text' id='txtCuenta' size='70' onKeyUp='autocompletarCuentas()' ></td>
    <td>Nº FACTURA </td>
    <td><input name='txtNumFact' type='text' id='txtNumFact'></td>
    <td>PRECIO</td>
    <td><input name='txtPrecioFact' type='text' id='txtPrecioFact'></td>
  </tr>
  
 <tr class='style3'><td height='11' class='style3'><input name='txtCodigoCuenta' type='hidden' id='txtCodigoCuenta'></td>
 <td colspan='2'><div id='Cuentas'></div></td>
 <td width='53'>IVA</td><td width='158'><select name='cmbIva' id="cmbIva">
   <option value='Seleccione'>Seleccione</option>
   <option value='0'>0</option>
   <option value='16'>16</option>
 </select>
 </td>
 
 <td width='45'>R.ICA</td><td width='273'><input name='txtRetIca' type='text' id='txtRetIca'></td></tr>
 <tr class='style3'>
   <td class='style3'>FECHA FACTURA </td>
   <td width='396'><input name='txtFechFactura' type='text' id='txtFechFactura' onKeyUp="masca(this,'/',patron,true)" ><input name='txtTipoCuenta' type='hidden' id='txtTipoCuenta' value="Debito"></td>
   <td width='93'>OBSERVACION</td>
   <td colspan='4'><textarea name='txtObservacionFact' cols='40' rows='3' id='txtObservacionFact'></textarea></td>
   </tr>
 <tr class='style3'><td colspan='7' class='style3'><div align='center'><input name='btnGuardarCuenta' type='button' value='     Guardar     ' onclick="GuardarFactura()"></div></td></tr></table>
 <input name='txtCodUsuario' type='hidden' id='txtCodUsuario' value="<%=codigou%>">
<input name='txtHora' type='hidden' id='txtHora'/>
<input name='txtFechaFija' type='hidden' id='txtFechaFija'/>
<input name='txtFecha' type='hidden' id='txtFecha'/>
		  
		</form></td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>