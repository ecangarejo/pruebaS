
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language=javascript src="Validaciones.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script languaje="javascript" src="Adm_Ping.js"></script>
<title> Reporte de Ordenes de Produccion</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language="javascript" type="text/javascript" src="farc_Consultas.js"></script>	

<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:342px; cursor:pointer; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}

</style>

<script type="text/javascript">
	
	</script>

</head>
<body onLoad="" id="back"> 
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
				
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				
	       	%>

<%
String cod=request.getParameter("codigo");
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Farmacia.jsp">Servicio Farmaceutico</a>-Reporte de Ordenes Produccion </b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>
 
	<table width="100%"> 
		<tr>
			
			<td width="100%">   
					<br />

				
					<table width="100%" border="0" cellspacing="0" class="style6">
  						<tr>
   							 <td colspan="4" id="cabecera2" class="style11" ><div align="center">CONSULTA DE ORDENES DE PRODUCCION</div></td>
   						 </tr>
 						<tr>
						<td>
						<table>
							<tr>
								<td>
									<br>
									
										FECHA INICIAL 
										<%
										int dia=0;
										out.println("<select name=Idia id=Idia>");
										out.println("<option value=40>---</option>");
										for(int i=0;i<=30;i++){
										dia=dia+1;
										out.println("<option value="+dia+">"+dia+"</option>");
										}
										
										out.println("</select>");
										out.println("<select name=Imes id=Imes>");
										out.println("<option value=40>---</option>");
										out.println("<option value=1>Enero</option>");
										out.println("<option value=2>Febrero</option>");
										out.println("<option value=3>Marzo</option>");
										out.println("<option value=4>Abril</option>");
										out.println("<option value=5>Mayo</option>");
										out.println("<option value=6>Junio</option>");
										out.println("<option value=7>Julio</option>");
										out.println("<option value=8>Agosto</option>");
										out.println("<option value=9>Septiembre</option>");
										out.println("<option value=10>Octubre</option>");
										out.println("<option value=11>Noviembre</option>");
										out.println("<option value=12>Diciembre</option>");	
										out.println("</select>");
										
										out.println("<select name=Iano id=Iano>");
										out.println("<option value=40>---</option>");
										int ano=2008;
										for(int k=0;k<=30;k++){
											ano=ano+1;
											out.println("<option value="+ano+">"+ano+"</option>");
										}
										out.println("</select>");%>
									
								</td>
							<tr>
								<td>
									<br>
									FECHA FINAL
										<%
										int fdia=0;
										out.println("<select name=Fdia id=Fdia>");
										out.println("<option value=40>---</option>");
										for(int i=0;i<=30;i++){
										fdia=fdia+1;
										out.println("<option value="+fdia+">"+fdia+"</option>");
										}
										
										out.println("</select>");
										out.println("<select name=Fmes id=Fmes>");
										out.println("<option value=40>---</option>");
										out.println("<option value=1>Enero</option>");
										out.println("<option value=2>Febrero</option>");
										out.println("<option value=3>Marzo</option>");
										out.println("<option value=4>Abril</option>");
										out.println("<option value=5>Mayo</option>");
										out.println("<option value=6>Junio</option>");
										out.println("<option value=7>Julio</option>");
										out.println("<option value=8>Agosto</option>");
										out.println("<option value=9>Septiembre</option>");
										out.println("<option value=10>Octubre</option>");
										out.println("<option value=11>Noviembre</option>");
										out.println("<option value=12>Diciembre</option>");	
										out.println("</select>");
										
										
										out.println("<select name=Fano id=Fano>");
										out.println("<option value=40>---</option>");
										int fano=2008;
										for(int k=0;k<=30;k++){
											fano=fano+1;
											out.println("<option value="+fano+">"+fano+"</option>");
										}
										out.println("</select>");%>
								
								
								<tr>
								<td>
								<br><br>
								<input type=button value="  Consultar  " onclick='ConOrdenProduccion()' />
								</td>
							</tr>
							</tr>
							<tr>
							<td>
							<div id=vista></div>
							</td>
							</tr>
						</table>
						
</div>
</td></tr></table>
<%}%>
</body>
</html>