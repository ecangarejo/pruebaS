<%@ page contentType="text/html;  charset=UTF-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
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
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Salidas</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script src="farc_Movimientos.js" type="text/javascript"></script>
<script src="farc_autocompletarInventario.js" type="text/javascript"></script>

<style type="text/css">
#autoc  li:hover{Cursor : pointer; background-color: #cccccc; }
#autoc { border:1px solid black; margin-left:0.8px;}
#autoc ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#autoc ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias0  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias0 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias0 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias0 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias1  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias1 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias2  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias2 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias2 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias2 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias3  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias3 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias3 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias3 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias4  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias4 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias4 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias4 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias5  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias5 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias5 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias5 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias6  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias6 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias6 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias6 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias7  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias7 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias7 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias7 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias8  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias8 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias8 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias8 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias9  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias9 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias9 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias9 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias10  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias10 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias10 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias10 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias11  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias11 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias11 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias11 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias12  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias12 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias12 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias12 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias13  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias13 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias13 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias13 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias14  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias14 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias14 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias14 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias15  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias15 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias15 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias15 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias16  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias16 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias16 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias16 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias17  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias17 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias17 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias17 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias18  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias18 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias18 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias18 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias19  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias19 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias19 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias19 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias20  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias20 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias20 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias20 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias21  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias21 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias21 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias21 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias22  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias22 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias22 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias22 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias23  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias23 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias23 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias23 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias24  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias24 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias24 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias24 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias25  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias25 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias25 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias25 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias26  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias26 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias26 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias26 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias27  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias27 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias27 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias27 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias28  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias28 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias28 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias28 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias29  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias29 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias29 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias29 ul li {padding: .2em; border-bottom: 1px solid silver;}




.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrollbox {height : 90px ; altura: 90px; desbordamiento: auto; overflow:scroll}

</style>
<script language="javascript"></script>
</head>

<body onload="Salidas()">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Farmacia.jsp">Servicio Farmaceutico </a>-Movimientos - Salidas </b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">  
<form id="form1" name="form1">
<table width="100%" class="style6">
  <tr>
    <td><div id="contenido"></div></td>
    </tr>
<!--  <tr><td colspan='2'><div align='center'><label><input type='button' name='btnCrearInv' id='btnCrearInv' value='Generar Salidas' onClick='CrearSal(this.form)'></label></div></td></tr>		
		-->	
</table>



<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
