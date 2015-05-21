<%@ page language="java" contentType="text/html; charset=utf-8" import="adm_logica.Conexion" import="java.sql.*" 
pageEncoding="utf-8"%>
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
<script languaje="javascript" src="AutocompletarCIE10.js">      </script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Traslado Ambulatorio>>Hospitalizacion</title>

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

/*****************************************************/
 
  function A(tecla,e){
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){BuscarPacienteAmbu();}
}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
 
 function BuscarPacienteAmbu(){
  var TipoDocumento=document.getElementById("cbafiliacion").value; 
  var NumeroDocumento=document.getElementById("txtnumdoc").value; 
  ajax=getXMLObject();
  ajax.open("POST","ControlFinalAdmi",true); //getname will be the servlet name
  ajax.onreadystatechange=function() {
  if (ajax.readyState==4) {
	  document.getElementById('Finalizar').innerHTML = ajax.responseText;
	}
}
  ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
  ajax.send("valor=tah&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }

 function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

 function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }

 
</script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="adm_CancelarAdmision.js" type="text/javascript"></script>

</head>
<body onload='fecha(),hora()' id="back">
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
				//con.cerrar();
	       	%>

<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Administracion-Finalizar Admision</b></div>
</td>
</tr>
  
<tr  id="principal1">
<td>         

<div>

	<table width="100%"> 
		<tr>
			<td width="100%">   
					<br />

<form id="form1"  name="form1" style="margin-top:-17px;" onkeypress = "return pulsar(event);" >
  <table width="100%" border="0" align="center" class="style6">
    <tr  id="cabecera2" class="style11" >
      <td colspan="6"><div align="center">TRASLADAR DE AMBULATORIO A HOSPITALIZACION </div></td>
    </tr>
    <tr>
      <td width="139">Tipo de Documento</td>
      <td width="115"><select name="cbafiliacion" size="1" id="cbafiliacion">
           <% 
 ResultSet rs3=null;
 Statement st3 = null;  
 try{
 	//Conexion con2=new Conexion();
 	st3 = con.conn.createStatement();
 	rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)%></option>
 	<%}
 	rs3.getStatement().close();
 	con.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
      </select></td>
      <td width="169">Numero de Documento</td>
      <td width="144"><input type="text" name="txtnumdoc" id="txtnumdoc" onkeydown="A(this,event)" />
		<input type="hidden" name="txtFecha" id="txtFecha" />
		<input type="hidden" name="txtHora" id="txtHora" />
		<input type="hidden" name="txtUsuario" id="txtUsuario" value="<%=codigou %>"/><input type="hidden" name="txtNomUsuario" id="txtNomUsuario" value="<%=idu%>"/></td>
      <td width="36"><input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Buscar" onClick="BuscarPacienteAmbu()"></td>
      <td width="164">&nbsp;</td>
    </tr>
    <tr><td colspan="6"><div id="Finalizar"></div></td></tr>
  </table>
  </form></td></tr></table>
</div>
</td></tr></table>
<%}%>
</body>
</html>