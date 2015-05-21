

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
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>ACTUALIZAR PACIENTE</title>
<script language=javascript src="autocompletar_nombre.js"></script>
<style type="text/css">
#sugerencias  li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias { border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrollbox {height : 90px ; altura: 90px; desbordamiento: auto; overflow:scroll}
</style>
<script language="javascript">
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

function getXMLObject() {
	var xmlhttp;
	if(typeof(XMLHttpRequest) != 'undefined'){
    	try{
      		xmlhttp = new XMLHttpRequest();
    	}catch(e){ }
  	}
  	else{
    	try{
      		xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
    	}catch(e){
      		xmlhttp = new ActiveXObject('Msxml2.XMLHTTP');
    	}
  	}
  	return xmlhttp;
}
var xmlhttp = new getXMLObject();

function buscar() {
 	
	div = document.getElementById('detalle');
	nombre=document.getElementById('nombre').value;
	cedula=document.getElementById('cedula').value;
	tipo=document.getElementById('tipodoce').value;	
	//instanciamos el objetoAjax
	ajax=getXMLObject();
	//uso del medotod POST
	ajax.open("POST", "Controllab_Pac");
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			if(ajax.status == 200) {
			//mostrar resultados en esta capa
			div.innerHTML = ajax.responseText		
			}
		}
	}
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
	ajax.send("yo="+1+"&nombre="+nombre+"&cedula="+cedula+"&tipo="+tipo+"");
 }

function actualizar() {

	
 	
	div = document.getElementById('detalle');
	nombre=document.getElementById('txtnom').value;
	apellido=document.getElementById('txtape').value;
	edad=document.getElementById('txtedad').value;
	cedula=document.getElementById('txtced').value;
	fecha=document.getElementById('txtfecha').value;
	telefono=document.getElementById('txttelefono').value;
	direccion=document.getElementById('txtdire').value;
	codigo=document.getElementById('txtcod').value;
	id=document.getElementById('cbtipo').selectedIndex;
	tipo=document.getElementById('cbtipo').options[id].text;
	
	//id1=document.getElementById('cbgenero').selectedIndex;
	genero=document.getElementById('cbgenero').value;
	
	//tipo=document.getElementById('tipodoce').value;
	
	//instanciamos el objetoAjax
	ajax=getXMLObject();
	//uso del medotod POST
	//alert();
	if((nombre=="")||(apellido=="")||(edad=="")||(cedula=="")){
		alert("Datos no pueden ir vacios");
	}else{
	ajax.open("POST", "Controllab_Pac");
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			if(ajax.status == 200) {
			//mostrar resultados en esta capa
			div.innerHTML = '';
			alert(ajax.responseText);
			document.getElementById('nombre').value="";
			window.location.reload();
			
			}
		}
	}
	//como hacemos uso del metodo POST
	ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
	//enviando el codigo del empleado
	ajax.send("yo="+2+"&nombre="+nombre+"&cedula="+cedula+"&tipo="+tipo+"&apellido="+apellido+"&edad="+edad+"&fecha="+fecha+"&telefono="+telefono+"&direccion="+direccion+"&codigo="+codigo+"&genero="+genero+"");
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"    style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>   
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Datos Demograficos-Actualizar Datos Paciente</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br/>
					<div id="cabecera2" class="style11" style="margin-top:-15px;" align="center">MODULO LABORATORIO</div>
					<br/><br/>  
								<%@include file="Copiamenu.jsp"%>
			</td>

			<td width="100%">   
					<br /> 
						<form  name="form1" style="margin-top:-17px;" id="form1" onkeypress = "return pulsar(event);">
						<table width="100%" align="center">              
									  <tr>
									    <td colspan="4" id="cabecera2" align="center"> <div class="style11">DATOS DEMOGRAFICOS</div> </td>
									  </tr>
						  <tr class="style6" ><td>Nombre del Paciente:</td><td>
						<input type='text'  name='nombre' id='nombre' onkeyup='autocompleta_nombre();' value='' size='50' maxlength='100' /></td><td><input type='hidden' name='cedula' id='cedula' onkeyup='' /><input type='hidden' name='tipodoce' id='tipodoce' onkeyup='' /></td><td><input name="btbuscar" class="boton4" type="button" id="btbuscar" onClick="buscar();" value="Buscar..."></td></tr><tr><td></td><td><div id='sugerencias' class="style6" ></div></td><td colspan="4"><div  class="style6" id="resultado" style="display:none" ></div></td></tr>
						</table>
						<br> 
						<div  class="style6" id="detalle"></div> 
						</form>
			</td>  
		</tr>
	</table>  
</div>
</td>
</tr>

</table>
<%}%>
</body>
</html>
