
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">


<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Estudios Asignados</title>


<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
<script language="javascript">


</script>

<script src="img_ConsultarTrasladosRealizados.js" type="text/javascript"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onload="fecha_gru(),hora_gru();" id="back" >
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="MenuAuxClinico.jsp">Aux. Clinico</a>-Traslados-Traslados </b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%">   
		<tr>
			
    
			<td width="100%">       
					<br />
 <%
String id=request.getParameter("id");String nombre=request.getParameter("nombre");String numero=request.getParameter("numero");String tip=request.getParameter("tipo");String z=request.getParameter("z");String cod=request.getParameter("cod");
String gene=request.getParameter("gene");
String edad=request.getParameter("edad");
if(id==null){
	id="";
};if(nombre==null){nombre="";};if(numero==null){numero="";};if(tip==null){tip="";};if(z==null){z="";};if(cod==null){cod="";};
if(edad==null){
	edad="";
}
%>

 <form id="form1" style="margin-top:-15px;" name="form1" onkeypress = "return pulsar(event);" >

<input type="hidden" id="txtCodUsu" value="<%=codigou %>" />
<input type="hidden" id="txtNomUsu" value="<%=idu %>" />



<table width="100%" class="style6">
  
<tr>
    <td colspan="4"><div align="center" class="style11" id="cabecera2">TRASLADOS REALIZADOS </div></td>
    </tr>
  <tr>

<td width="25%"><label><input name="radiobutton" type="radio" value="radiobutton" id="todosUsu" onclick="Radio()" /> Todos los Usuarios</label></td>
   <td width="25%"><label><input name="radiobutton" type="radio" value="radiobutton" id="cargarUsu" onclick="Radio()" />Por Usuario</label></td>
	 <!--<td width="25%"><label><input name="radiobutton" type="radio" value="radiobutton" id="pent" onclick="Radio()" /> Censo Por Entidad</label></td>
<td width="25%"><label><input name="radiobutton" type="radio" value="radiobutton" id="pent" onclick="ImprimeCensoNombre()" /> Censo Por Nombre</label></td>-->
    </tr>
 
  
  <tr>
    <td colspan="4"><div id='examenes'></div></td>
    </tr>
</table>



     </form>
</td></tr></table>
</div>
</td></tr></table>
	 <%} %>
 
</body>
</html>
