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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Ingreso de Facturas</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script language="javascript">
function getXMLObject()  //XML OBJECT
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function BuscarPc(){
	
	var contenido=document.getElementById('Detalles');	
	var NumDoc=document.getElementById("txtNumDoc").value;
	var TipoDoc=document.getElementById("cmbTipoDoc").value;
	
	if(NumDoc==""){alert("Debe Ingresar el numero de documento");}else{    
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=BPCF&NumDoc="+NumDoc+"&TipoDoc="+TipoDoc);	
  }
}

function GuardarFactura(){
	var NumDoc=document.getElementById("txtNumDoc").value;
	var TipoDoc=document.getElementById("cmbTipoDoc").value;
	var txtDireccion=document.getElementById("txtDireccion").value;
	var txtTelefono=document.getElementById("txtTelefono").value;
	var txtTipoAfiliacion=document.getElementById("txtTipoAfiliacion").value;
	var txtEstrato=document.getElementById("txtEstrato").value;
	var txtNombrePaciente=document.getElementById("txtNombrePaciente").value;
	/**********************************************/
	var cmbEntidad=document.getElementById("cmbEntidad").value;
	/**********************************************/
	var txtNumeroFactura=document.getElementById("txtNumeroFactura").value;
	var txtCopago=document.getElementById("txtCopago").value;
	var cmbUsuario=document.getElementById("cmbUsuario").value;
	var txtFechaFactura=document.getElementById("txtFechaFactura").value;
	var txtHoraFactura=document.getElementById("txtHoraFactura").value;
	var txtValoFactura=document.getElementById("txtValoFactura").value;
	var txtFechaEgreso=document.getElementById("txtFechaEgreso").value;
	var txtFechaIngreso=document.getElementById("txtFechaIngreso").value;
	var txtCodAdmision=document.getElementById("txtCodAdmision").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText;
			window.location.reload();	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=GFCT&NumDoc="+NumDoc+"&TipoDoc="+TipoDoc+
			"&txtDireccion="+txtDireccion+"&txtTelefono="+txtTelefono+
			"&txtTipoAfiliacion="+txtTipoAfiliacion+"&txtEstrato="+txtEstrato+
			"&txtNombrePaciente="+txtNombrePaciente+"&cmbEntidad="+cmbEntidad+
			"&txtNumeroFactura="+txtNumeroFactura+"&txtCopago="+txtCopago+
			"&cmbUsuario="+cmbUsuario+"&txtFechaFactura="+txtFechaFactura+
			"&txtHoraFactura="+txtHoraFactura+"&txtValoFactura="+txtValoFactura+
			"&txtFechaEgreso="+txtFechaEgreso+"&txtFechaIngreso="+txtFechaIngreso+
			"&txtCodAdmision="+txtCodAdmision);	

	/*alert("valor=GFCT&NumDoc="+NumDoc+"&TipoDoc="+TipoDoc+
			"&txtDireccion="+txtDireccion+"&txtTelefono="+txtTelefono+
			"&txtTipoAfiliacion="+txtTipoAfiliacion+"&txtEstrato="+txtEstrato+
			"&txtNombrePaciente="+txtNombrePaciente+"&cmbEntidad="+cmbEntidad+
			"&txtNumeroFactura="+txtNumeroFactura+"&txtCopago="+txtCopago+
			"&cmbUsuario="+cmbUsuario+"&txtFechaFactura="+txtFechaFactura+
			"&txtHoraFactura="+txtHoraFactura+"&txtValoFactura="+txtValoFactura+
			"&txtFechaEgreso="+txtFechaEgreso+"&txtFechaIngreso="+txtFechaIngreso+
			"&txtCodAdmision="+txtCodAdmision);*/
}


</script>
</head>

<body>

<table width="100%">
<tr >
<td>
</td>
</tr>

<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp"> </a></b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">  
<form id="form1" name="form1">

<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr><td colspan='9'><div id='cabecera2' class='style11' align='center'>INGRESO DE FACTURAS </div></td></tr>

  <tr><td width='24%'>Tipo Documento </td><td colspan='3'><select name='cmbTipoDoc' id='cmbTipoDoc'>
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

</select></td><td width='14%' >Numero Documento </td><td width='17%' ><input name='txtNumDoc' type='text' id='txtNumDoc'></td><td width='22%' ><input type='button' name='Button' value='Buscar' onclick='BuscarPc()' ></td></tr><tr><td id='Detalles' colspan='7'>xxxxx xxxx xxxx</td></tr></table>



</form>
</td></tr></table>
</div>
</td></tr></table>

</body> 
</html>
