
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<%@ page import="adm_bean.Preingreso,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Pacientes en Espera</title>
<script type="text/javascript">
function hora(){
	
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	
	  form1.txthora.value = temp
	  id = setTimeout("hora()",1000)
	  }

function Refresco() 
{
	 setTimeout("document.location.href='ControlCola';",20000);		
}
 
</script>



</head>


<body onLoad="hora(),Refresco();" id="back">
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
				//con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
//System.out.println("va.."+va);
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Consultas-Pacientes en Espera</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-16px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/>  
								<%@include file="CopyAdm.jsp"%>
			</td>

			<td width="100%">   
					<br />
<form id="form1" name="form1" style="margin-top:-16px;">
  <table width="100%" border="0" class="style6">
    <tr id="cabecera2" class="style11">    
      <td >  <div align="center">LISTA DE PACIENTES EN ESPERA </div></td>
    </tr>
  </table>
 <br />
  <table width="247"  class="style6" align="center">    
    <tr>
      <td width="10%">Hora Actual. </td>
      <td width="10%"><input name="txthora" type="text"  id="txthora"  /></td>
    </tr>
  </table>
  <p>
 <br />  
<%


LinkedList ls = (LinkedList)request.getSession().getAttribute("lista");
Preingreso p1 = new Preingreso();
%>
<div class="scrollbox1">
  
<TABLE Border="1"   width="100%" >
  <tr id="cabecera2" align="center" class="style11" >
<td width="168" size="2">TIPO DE DOCUMENTO</td>
<td width="150" size="2">NUMERO DE DOCUMENTO</td>
<td width="90"  size="2">EPS</td>
<td width="152" size="2">FECHA ENTRADA</td>
<td width="151" size="2">HORA ENTRADA </td>
</tr>

<%
for(int i=0; i< ls.size();i++){
	p1 = (Preingreso)ls.get(i);
%> 
	 <tr align="center" class="style6" >       
<TD size="1"><%=p1.getTipodocumento()%></TD>
<TD size="1"><%=p1.getCedula()%></TD>
<TD size="1"><%=p1.getEps()%></TD>
<TD size="1"><%=p1.getFecha()%></TD>
<TD size="1"><%=p1.getHora()%></TD>
  
<% }%>

</tr></table></div>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>