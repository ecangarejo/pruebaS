

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
String numero=request.getParameter("numero");
if(numero==null){
	numero="";
}
%> 
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language=javascript src="Imaginologia.js"></script>
<script languaje="javascript" src="img_autocompletaexamen.js"></script>
<script languaje="javascript" src="img_autocompletarestudio.js"></script>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Asignacion de Examen</title>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:218px; Cursor : pointer; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;}
#sugerencias {height : 100px ; overflow : scroll ; cursor:pointer;}
</style>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias1 li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias1 {width:545px; border:1px solid black; cursor:pointer; display:none; margin-left: 0px;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
</style>
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
/////////////////////////////////////////////////////////////////////////////////
function fecha_gru(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
	 document.getElementById('txtfecha').value = temp1
	 id = setTimeout("fecha_gru()",1000)	 
		  		}
function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txthora').value = temp;
	  id = setTimeout("hora_gru()",1000)
	  }
///////////////////////////////////////////////////////////////////////////
function validar(h){
		if (h.txtnumdoc.value.length==0){
			window.alert('Debe Digitar la Cedula');
			h.nom.focus();
			return false;
		}
	h.submit();
	}
///////////////////////////////////////////////////////////////////////////
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
///////////////////////////////////////////////////////////////////////////
function ComprobarPeticion() {
	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var resultado=xmlhttp.responseText;			
			document.getElementById('pac').innerHTML=resultado;
	    }
	     else {
	        alert("Error during AJAX call. Please try again,ComprobarPeticion");
	     }
	   }
}
////////////////////////////////////////////////////////////////////////
function Buscar(){
	if(xmlhttp) { 
		var cedula=document.getElementById('txtnumdoc').value;
		var TipoDoc=document.getElementById('cbafiliacion').value;
		var hora=document.form1.txthora.value;
		var fecha= document.form1.txtfecha.value;
		xmlhttp.open("POST","ControlCrearGrupo?valor=4&cedula="+cedula+"&TipoDoc="+TipoDoc,true);
		xmlhttp.onreadystatechange  = ComprobarPeticion;
		xmlhttp.send("");
	}	
}
/////////////////////////////////////////////////////////////////
function img_pacienteActualizar(){
	var codigo=document.getElementById('codPac').value;
	var nombre=document.getElementById('txtnombres').value;
	var genero=document.getElementById('cbgenero').value;
	var eps=document.getElementById('txteps').value;
	var telefono=document.getElementById('txtele').value;
	var direccion=document.getElementById('txtdireccion').value;
	var email=document.getElementById('txtemail').value;
	var apellido=document.getElementById('txtapellidos').value;
	var nacimiento=document.getElementById('txtfechanaci').value;
	var TipoDoc=document.getElementById('tipodoc').value;
	if(nacimiento==""){
		nacimiento=""
		}
	var cedula=document.getElementById('txtnumdocu').value;
	//alert(nacimiento);
	window.location.href="ControlCrearGrupo?valor=5&codigo="+codigo+"&nombre="+nombre+"&genero="+genero+"&eps="+eps+"&telefono="+telefono+"&direccion="+direccion+"&email="+email+"&apellido="+apellido+"&nacimiento="+nacimiento+"&cedula="+cedula+"&TipoDoc="+TipoDoc;
	alert("Datos Actualizados Satisfactorimente");
	
}
/////////////////////////////////////////////////////////////////
function finalizar(){
alert("Examenes Asignados Satisfactoriamente!!!!");
window.location.href="Imagenologia.jsp";
}
////////////////////////////////////////////////////////////////////
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Actualizar Demografico</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO IMAGENOLOGIA</div>
					<br/><br/>  
							<%@include file="copymenyhi.jsp"%>	
			</td>
    
			<td width="100%">       
					<br />
 <%
String id=request.getParameter("id");String nombre=request.getParameter("nombre");String tip=request.getParameter("tipo");String z=request.getParameter("z");String cod=request.getParameter("cod");
String gene=request.getParameter("gene");
String edad=request.getParameter("edad");
if(id==null){
	id="";
};if(nombre==null){nombre="";};if(numero==null){numero="";};if(tip==null){tip="";};if(z==null){z="";};if(cod==null){cod="";};
if(edad==null){
	edad="";
}
%>


					 <form id="form1"  name="form1" style="margin-top:-15px;" onkeypress = "return pulsar(event);" >
					
					   <table width="100%" align="center" class="style6">  
					
					     <tr id="cabecera2" class="style11">
					       <td colspan="5"><div align="center" >DATOS DEL PACIENTE </div></td>
					     </tr>
					
					     <tr>
					       <td>&nbsp;</td>
					       <td><input name="txtfecha" type="hidden" id="txtfecha" /></td>
					       <td>&nbsp;</td>
					       <td><input name="txthora" type="hidden" id="txthora" /></td>
					     </tr>
					     <tr align="center">
					       <td colspan="5">TIPO DE DOCUMENTO<label>
					        <select name="cbafiliacion" size="1" id="cbafiliacion">
					        <option selected="selected" value="<%=tip%>"><%=tip%></option>
					       <%if(z.equals("1")){%>
							 <%if(tip.equals("CC")){ %>
					        <option value="TI">TI</option>
					        <option value="RC">RC</option>
					        <option value="Nacido Vivo">Nacido Vivo</option>
					         <%} %>
					       <%if(tip.equals("TI")){ %>
					        <option value="CC">CC</option>
					        <option value="RC">RC</option>
					        <option value="Nacido Vivo">Nacido Vivo</option>
					         <%} %>
					         <%if(tip.equals("RC")){ %>
					        <option value="TI">TI</option>
					        <option value="CC">CC</option>
					        <option value="Nacido Vivo">Nacido Vivo</option>
					         <%} %>
					        <%if(tip.equals("Nacido Vivo")){ %>
					        <option value="TI">TI</option>
					        <option value="RC">RC</option>
					        <option value="CC">CC</option>
					         <%} %>
					       
					          <% }%>
					           <%if(tip.equals("")){ %>
					        <option selected value="CC">CC</option>
					        <option value="TI">TI</option>
					        <option value="RC">RC</option>
					        <option value="Nacido Vivo">Nacido Vivo</option>
					         <%} %> 
					        </select>
					      </label> <label>NUMERO</label><label>
					        <input name="txtnumdoc" type="text" id="txtnumdoc" onkeyup="autocompleta()" value="<%=numero %>" />
					        <input name="btnBuscar" type="button" class="boton4" id="btnBuscar" value="Buscar" onclick="Buscar()" />
					       </label></td>
					<td align="right">&nbsp;</td>
					     </tr>
					     <tr>
					       <td><div  style="margin-left:390px" id='sugerencias'></div></td>
					     </tr>  
					     <tr>
					       <td colspan="5"><div id="pac"></div></td>
					       </tr>	 
						  <tr>
					    <td colspan="4"><div class="style6" id="resultado" style="display:none" ></div></td>
					    </tr>
					   </table>
					     </form>  
</td></tr></table>
</div>
</td></tr></table>
	 <%} %>
 
</body> 
</html>
