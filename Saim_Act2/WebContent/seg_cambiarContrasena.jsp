
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="pkgsys.*,java.util.*" %>
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
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Cambiar Contraseña</title>
<style type="text/css">
#sugerencias li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias {width:218px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 100px ; overflow : scroll ; Cursor : pointer;}
</style>
<style type="text/css">
#sugerencias1 li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias1 {width:545px; border:1px solid black; display:none; margin-left: 0px; Cursor : pointer;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
</style>
<script src="seg_autocompletarUsuario.js" type="text/javascript"></script>
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
/////////////////////////////////////////////////////////////////////////////////
function BuscarUsuario(){
  var NumeroDocumento=document.getElementById("txtnumdoc").value; 
  ajax=getXMLObject();
  ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
  ajax.onreadystatechange=function() {
  if (ajax.readyState==4) {
	  document.getElementById('Resultado24').innerHTML = ajax.responseText;
	}
}
  ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
  ajax.send("valor=1&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }


function cambiarContra(){
	var NuevaContra=document.getElementById("txtNuevaContra").value; 
	var RepNuevaContra=document.getElementById("txtRepNuevaContra").value; 
	var ViejaContra=document.getElementById("txtViejaContra").value;// aqui viene la contraseña antigua.
	var CodUsuario=document.getElementById("txtCodUsuario").value;
	var AntiguaContra=document.getElementById("txtAntiguaContra").value;//donde se escribe la antigua contraseña

	if(ViejaContra!=AntiguaContra){
		alert("La Contraseña Anterior No Coincide Con la Digitada.\nPor Favor,Intente Otra Vez.");
		}
	if(NuevaContra!=RepNuevaContra){
		alert("La Contraseña Nueva No Son Iguales.\nPor Favor, Intente Otra Vez");
		}
	if(NuevaContra==ViejaContra){
		alert("La Contraseña Nueva, No puede Ser Igual a la Anterior.\n Por Favor, Intente Otra Vez.");
		}

	if((ViejaContra==AntiguaContra)&&(NuevaContra==RepNuevaContra)&&(NuevaContra!=ViejaContra)){
		//alert("Validaciones Correctas.\n Usuario="+CodUsuario+"\n Contraseña="+NuevaContra);
		  ajax=getXMLObject();
		  ajax.open("POST","Control_Usuarios",true); //getname will be the servlet name
		  ajax.onreadystatechange=function() {
		  if (ajax.readyState==4) {
			  alert(ajax.responseText);
			  window.location.reload();
			}
		}
		  ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		  ajax.send("valor=2&CodUsuario="+CodUsuario+"&NuevaContra="+NuevaContra); //Posting txtname to Servlet
				
		}
}
</script>
</head>
<body id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
</script> 

<%
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
				con.cerrar();
	       	%>

<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>

 
<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="seguridad.jsp">Seguridad</a>-Cambiar Contraseña</b></div>
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%">       
					<br />  
  
							 <form id="form1" style="margin-top:-15px;" name="form1"  >
							  
							   <table width="100%" align="center" class="style6">
							
							     <tr id="cabecera2" class="style11">
							       <td colspan="5"><div align="center" class="style4">DATOS DEL USUARIO </div></td>
							     </tr>
							
							     <tr>
							       <td width="1%">&nbsp;</td>
							       <td width="17%"><input name="txtfecha" type="hidden" id="txtfecha" /></td>
							       <td width="27%">&nbsp;</td>
							       <td width="52%"><input name="txthora" type="hidden" id="txthora" /></td>
							     </tr>

							     <tr>
							       <td colspan="2" align="right"><label></label>  </td>
							       <td ><label>NUMERO DE DOCUMENTO </label></td>
							       <td ><label>
							        <input name="txtnumdoc" type="text" id="txtnumdoc"/>
							        <input name="btnBuscar" type="button" class="boton4" id="btnBuscar" value="Buscar" onclick="BuscarUsuario()" /> 
							       </label></td>
							<td width="3%" align="right">&nbsp;</td>
							     </tr>  
							     <tr>
							       <td>&nbsp;</td>
							       <td>&nbsp;</td>
							       <td>&nbsp;</td>
							       <td><div id='sugerencias'></div></td>
							       <td align="right">&nbsp;</td>
							     </tr>	 
								  <tr>
							    <td colspan="4"><div id="resultado" style="display:none" ></div></td>
							    </tr>
							   </table>
							<div id='Resultado24' class="style6"></div>
        </form></td></tr></table>
</div>
</td></tr></table>
	 <%} %>
 
</body> 
</html>
