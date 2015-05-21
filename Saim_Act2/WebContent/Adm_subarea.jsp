

<%@ page import="adm_bean.Area,java.util.*" import="adm_logica.* "%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 

<title>Crear Sub-Area</title>

<script type="text/javascript">

//------------------------------------------------------------------------
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
	   
	

	
	function ajaxFunction() {
		 
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlSubarea",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticion;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}

	function ComprobarPeticion() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	form1.cbArea.length=y;
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			     form1.cbArea.options[x+1].text=z[x];
			    // form1.cbArea.value();
			    }
		    		
		    	  document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}

	function Mensaje() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		    	 //alert(a); //Update the HTML Form element 
		    	 form1.txtSubArea.value="";
            }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}

	function verificarNombre(h){
		var s=h.cbArea.selectedIndex;
		var areas=h.cbArea.options[s].text;
		//alert("area"+areas)
		var subarea=h.txtSubArea.value;
		if(subarea==""){
			alert("Falta Ingresar El Nombre De La Subarea!!!")
			}
		if(areas=="SELECCIONE..."){
			alert("Falta Seleccionar El Area!!!")
			}
			
		if((subarea!="")&&(areas!="SELECCIONE...")){		
		window.location.href="ControlSubarea?nombre="+areas+"&subarea="+subarea;
		alert("Subarea Creada Con Exito!!!")
		}
	}//fin de verificar index


		
	</script>
<script language=javascript src="Validaciones.js"></script>
</head>

<body onload="ajaxFunction()" id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
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
String va=request.getParameter("va");
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// DivisiÃƒÂ³n en trozos con la barra como delimitador. 
	partes = url.split('/'); 
	 
	var a=partes[partes.length-1];
	
	window.location.href="PermisosPagina?va=1&pa="+a+"&codusu="+<%=codigou%>+""; 
	
</script>
<%} %>
<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")||cod.equals("")){%>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Administracion-Crear Sub-Area</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/>
								<%@include file="CopyAdm.jsp"%>
			</td>
   
			<td width="100%">   
					<br />
<form id="form1" name="form1" method="get" style="margin-top:-16px;" >
<table width="100%" align="center" >
  <tr>
    <td  align="center" id="cabecera2" class="style11">ASIGNACION DE SUB-AREAS </td>
  </tr>

<table width="287" class="style6" align="center" >
  <tr>
    <td align="center"><label>Seleccione Area </label></td>
  </tr>
  <tr>
    <td align="center"><select name="cbArea" id="cbArea" >
	<option selected="selected">SELECCIONE...</option>
    </select></td>
  </tr>
  <tr>
    <td align="center">Nombre Sub-Area </td>
  </tr>
  <tr>
    <td align="center"><label>
      <input name="txtSubArea" type="text" id="txtSubArea" onKeyUp="this.value=this.value.toUpperCase();" size="40" />
    </label></td>
  </tr>
  <tr>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><label>
      <input name="button" type="button" class="boton4" id="button" value="Crear"  onclick="verificarNombre(form1)"/>
    </label></td>
  </tr>
</table>

</form>
</td></tr></table>
</div>
</td></tr></table>
  
<%} %>
</body>

</html>
