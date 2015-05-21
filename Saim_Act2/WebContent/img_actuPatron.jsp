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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script languaje="javascript" src="img_actupatron.js"></script>

<title>Actualizar Patron</title>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {Cursor : pointer; width:510px; border:1px solid black; display:none; margin-left: 10px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 100px ; overflow : scroll ; desbordamiento: auto;}
</style>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">
function objetoAjax(){
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
  		}
	}

	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}
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
	 document.getElementById('fechagru').value = temp1
	 id = setTimeout("fecha()",1000)	 
}
function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('horagru').value = temp;
	  id = setTimeout("hora()",1000)
}

////////////////////////////////////////////////////////////////////////////////
function Actualizar(){
		var codExamen=document.getElementById("txtcodexamen").value;
		var patronNormal=document.getElementById("txtpatronNormal").value;
		var e;
	  /*  for(e=0;e<patronNormal.length;e++){
	    	patronNormal=patronNormal.replace('Ñ','@');
	    	patronNormal=patronNormal.replace('ñ','@');
	    	patronNormal=patronNormal.replace('Á','A');
	    	patronNormal=patronNormal.replace('É','E');
	    	patronNormal=patronNormal.replace('Í','I');
	    	patronNormal=patronNormal.replace('Ó','O');
	    	patronNormal=patronNormal.replace('Ú','U');
	    	patronNormal=patronNormal.replace('á','a');
	    	patronNormal=patronNormal.replace('é','e');
	    	patronNormal=patronNormal.replace('í','i');
	    	patronNormal=patronNormal.replace('ó','o');
	    	patronNormal=patronNormal.replace('ú','u');
	    }*/
		if(patronNormal==""){
		alert("El Patron No Puede Ir Vacio!!!");
		}
		if(codExamen==""){
		alert("Escoja El Examen!!!");
		}
		if((patronNormal!="")&&(codExamen!="")){
		ajax=objetoAjax();
		ajax.open("POST","ControlCrearSubExa",true); //getname will be the servlet name
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&codigoExa="+codExamen+"&patronNormal="+patronNormal); //Posting txtname to Servlet
		finalizar();
		}
	}
/////////////////////////////////////////////////////////////////
function finalizar(){
alert("Patron Actualizado Satisfactoriamente!!!!");
window.location.href="Imagenologia.jsp";	
}
/////////////////////////////////////////////////////////////////
function buscar(){		
	    DivValor=document.getElementById('txtpatronNormal');		
		var codExamen=document.getElementById("txtcodexamen").value;
		ajax=objetoAjax();	
	    ajax.open("POST","ControlCrearSubExa",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				PatronNormal=ajax.responseText;
				/*var e;
				 for(e=0;e<PatronNormal.length;e++){
				    	PatronNormal=PatronNormal.replace('@','Ñ');
				    }*/
	  			DivValor.innerHTML=PatronNormal;
	  	   	}  	
		}
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&codigoExa="+codExamen); //Posting txtname to Servlet
		
}
////////////////////////////////////////////////////////////////////
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body >
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} */
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Crear Examen</b></div> 
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

 <form id="form1"  name="form1" style="margin-top:-15px;" onkeypress = "return pulsar(event);" >

   <table width="100%" class="style6" border="0" align="center">

     <tr id="cabecera2" class="style11">
       <td colspan="4"><div align="center" >EXAMEN</div></td>
     </tr>

     <tr>
       <td>&nbsp;</td>
       <td width="133">&nbsp;</td>
       <td width="53">&nbsp;</td>
       <td width="332"><label>
         <input name="txtcodexamen" type="hidden" id="txtcodexamen" />
       </label></td>
     </tr>
     <tr>
       <td width="141" align="right">Nombre del Examen </td>
       <td colspan="3" align="center"> <label></label>  <label></label>         <label>
        <input name="txtnumdoc" type="text" id="txtnumdoc" onkeyup="autocompleta()" size="80" />
         <input name="btnBuscar" type="button" id="btnBuscar" class="boton4" value="Buscar" onclick="buscar()" />
       </label></td>
     </tr>
     <tr>
       <td>&nbsp;</td>
       <td colspan="3"><div id='sugerencias'></div></td>
       </tr>	 
	  <tr>
    <td colspan="4"><div id="resultado" style="display:none" ></div></td>
    </tr>
   </table>
   <table width='600' align="center" border='0'>
     <tr><td width="594" align='center' id="cabecera2"><span class='style11'>PATRON NORMAL</span> </td></tr>
    <tr><td align='center'><label><textarea name='txtpatronNormal' cols='90' rows='6' id='txtpatronNormal'></textarea></label></td></tr><tr><td align='center'><label>
     <input name='btnactualizar' class="boton4" type='button' id='btnactualizar' value='Actualizar' onclick='Actualizar()' />
     </label></td></tr></table>
<div id='examenes'></div>
<div id='especifico'>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
     </form>
</td></tr></table>
</div>
</td></tr></table>
<%} %>
 
</body> 
</html>
