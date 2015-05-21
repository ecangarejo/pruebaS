

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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Crear Sub-àreas</title>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
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
   



function ajaxFunction() {
	
	  if(xmlhttp) { 
		 	 
	  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
	    xmlhttp.open("POST","ControlSubareaLab",true); //getname will be the servlet name
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
	     	//form1.cmbarea.length=y;
	     	document.getElementById("cmbarea").length=y;
	        var h,ss;
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    // form1.cmbarea.options[x+1].text=z[x];
		    document.getElementById("cmbarea").options[x+1].text=z[x];
		    // form1.cbArea.value();
		    }
	    		
	    	  //document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

	     }
	     else {
	        alert("Error during AJAX call. Please try again");
	     }
	   }
	}


function verificarNombre(h){
	var s=h.cmbarea.selectedIndex;
	var areas=h.cmbarea.options[s].text;
	var CodUsu=document.getElementById("txtCodusuario").value;
	//alert("area"+areas)
	var subarea=h.txtsubarea.value;
	if(subarea==""||areas=="SELECCIONE"){
	alert("Falta LLenar Algun  Dato");
		}
	else{
	//alert("SUBarea"+subarea)
	window.location.href="ControlSubareaLab?nombre="+areas+"&subarea="+subarea+"&CodUsu="+CodUsu;
	alert("SubGrupo Creado con Exito");
	}
}//fin de verificar index

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
</script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onLoad="ajaxFunction();" id="back" >
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
				//con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
//System.out.println("va.."+va);
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// División en trozos con la barra como delimitador. 
	partes = url.split('/'); 
	 
	var a=partes[partes.length-1];
	
	window.location.href="PermisosPagina?va=1&pa="+a+"&codusu="+<%=codigou%>+""; 
	
</script>
<%} %>
<%
String cod=request.getParameter("codigo");
//System.out.println("cod"+cod);
//System.out.println("cod_usuario yosimar"+codigou);
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
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="color: white" > --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Creaciones-Crear Sub-Area</b></div>
</td>
</tr>
  
<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="Copiamenu.jsp"%>
			</td>

			<td width="100%">                
					<br />
						  <form style="margin-top:-17px;" id="form1"  name="form1"  onkeypress = "return pulsar(event);">
						    <table width="100%" class="margen2"> 
				              <tr>
				                <td colspan="2" align="center"><div id="cabecera2"   class="style11">CREAR SUB-GRUPO</div></td>
				              </tr>
				              <tr>
				                <td>&nbsp;</td>
				                <td>&nbsp;</td>
				              </tr>
				              <tr class="style6">
				                <td>ESCOJA EL GRUPO 				                <select name="cmbarea" id="cmbarea">
				                    <option>SELECCIONE</option>
				                	</select>
								</td>

								<td>NOMBRE DEL SUB-GRUPO <input name="txtsubarea" type="text" id="txtsubarea"></td>
				              </tr>

				              <tr>
				                <td colspan="2" align="center"><label>
				                  <input name="btncrear" class="boton4" type="button" id="btncrear" value="Ingresar" onClick="verificarNombre(form1)" >
				                </label><input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/></td>
				              </tr>
				            </table>
				 
				          </form>
					</td>
			</tr>
	</table>
</div>
</td></tr></table>
    <%} %>
</body>
</html>