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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Creacion De Unidades</title>


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
//////////////////////////////////////////////////////////////////////////////////////////////////
function ajaxFunction(){
	//alert("ajaxGenero")
	if(xmlhttp){
		xmlhttp.open("POST","ControlCrearUnidad?valor=1",true);
		xmlhttp.onreadystatechange = comprobarUnidad;
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send("");
		}
}
function comprobarUnidad(){

	  if (xmlhttp.readyState == 4) {	   
			if(xmlhttp.status == 200) {
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");		     	
		     	document.getElementById("cmbunidades").length=y;	     
		        var h,ss;
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     		for(i=0;i<z[x].length;i++){
		     			z[x]=z[x].replace('9','%');
		     		    }
		     		document.getElementById("cmbunidades").options[x+1].text=z[x];
			     
			    }		    		
		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }	
}

function ingresarUnidad(h){

	var nomb=h.txtnomunidad.value;
	nomb=encodeURIComponent(nomb);
	for(i=0;i<nomb.length;i++){
	      nomb=nomb.replace('%','9');
	    }
	if(nomb==""){
		alert("No Se Han LLenado El Nombre De La Unidad")
		}
	else{
		//window.location.href="ControlCrearUnidad?nomb="+nomb;
		//alert(nomb);
		   ajax=getXMLObject();
	   ajax.open("POST","ControlCrearUnidad",true); //getname will be the servlet name
	   
		   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					if(ajax.status == 200) {
					//mostrar resultados en esta capa
						alert("Ingreso Exitoso!!!");
						h.txtnomunidad.value="";
						ajaxFunction();
						
					}
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor="+0+"&nomb="+nomb+""); //Posting txtname to Servlet
	
		
		}
	
}
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}


</script>
</head>
<body onLoad="ajaxFunction()" id="back">
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
			//	con.cerrar();
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Creaciones-Crear Unidad</b></div>
</td>
</tr>
  
<tr id="principal1">
<td>

<div >

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" class="style11" style="margin-top:-15px;" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="Copiamenu.jsp"%>
			</td>

			<td width="100%">                
					<br/>
					  <form id="form1" style="margin-top:-17px;" name="form1"   onkeypress = "return pulsar(event);">
					    <table width="100%" >   
			              <tr>
			                <td colspan="3" ><div align="center" class="style11" id="cabecera2">CREAR UNIDAD </div></td>
			              </tr>
			              <tr> 
			                <td colspan="3">&nbsp;</td>
			              </tr>
 
			              <tr>
			                <td colspan="3" class="style6" align="center">NOMBRE UNIDAD <input name="txtnomunidad" type="text" id="txtnomunidad"><input name="btningresar" class="boton4" type="button" id="btningresar" value="Ingresar" onClick="ingresarUnidad(form1)"> UNIDADES CREADAS <select name="cmbunidades" id="cmbunidades"><option>SELECCIONE</option></select> </td>
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