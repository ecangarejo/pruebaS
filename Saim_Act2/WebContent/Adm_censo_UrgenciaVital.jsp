

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage=""%>
<%@ page import="adm_bean.Urgencia_vital,java.util.*" import="adm_logica.* "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
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
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Censo Urgencia Vital</title>

<script language="javascript">




</script>

</head>
<body id="back">
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
	// DivisiÃƒÂ³n en trozos con la barra como delimitador. 
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Consultas-Urgencia Vital</b></div>

</td>
</tr>

<tr id="principal1">
<td>

<div >   

	<table width="100%">          
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-16px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/>
								<%@include file="CopyAdm.jsp"%>
			</td>

			<td width="100%">   
					<br /><br />
<form  id="form1" name="form1" style="margin-top:-32px;" method="get" action="Control_Datos_UrgenciaV">
<table  width="100%" class="style6">
<tr id="cabecera2" class="style11" align="center"><td colspan="3">CENSO URGENCIA</td> </tr>
<tr><td>&nbsp;</td></tr> 
<tr id="cabecera2" class="style11" align="center">
<td align="center"><strong>Descripcion</strong></td>
<td align="center"><strong>Fecha</strong></td>
<td align="center"><strong>Hora</strong></td>
</tr>

<%
//Vector lista =(Vector)request.getSession().getAttribute("censourgencia");

LinkedList ls = (LinkedList)request.getSession().getAttribute("censourgencia");  
Urgencia_vital uv=new Urgencia_vital();
//cen = (Censo)lista.get(1);
//out.println(cen.getPaciente());
String codigoUrg="";
for(int i=0; i< ls.size();i++){
	uv = (Urgencia_vital)ls.get(i);
	codigoUrg = uv.getCodigo();	
%>
	<tr>
	<td align="left"><a href="Adm_Ingreso.jsp?id=<%=codigoUrg%>"><%=uv.getDescripcion() %></td>
	<td align="center"><%=uv.getFecha() %></td>
	<td align="center"><%=uv.getHora() %></td>
	</tr>


<%} 
//System.out.println("codigo urgencia"+codigoUrg);
%>
</table>
</font>
<br>
<center><input name="sbt_imprimir" type="button" class="boton4" onClick="print();" value="Imprimir"></center>
</form>
</td></tr></table>
</div>
</td></tr></table>
	<%} %>

</body>
</html>