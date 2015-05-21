

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Crear Area</title>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language='javascript'>
function Crear_Area(h,tecla, e){	

	var area;
	
	area=h.txtArea.value;

	if(area==""){
	   alert("Digite el Area...!");
    
	}else{
		window.location.href="Crear_Area?area="+area+"",true;
		alert("Ingreso Exitoso Del Area...");
		}
	tecla =   e.keyCode;  e.which;
	if(tecla == 13){
		//alert("listo..");
		if(area==""){
			   alert("Digite el Area...!");
		    
			}else{
	        	window.location.href="Crear_Area?area="+area+"",true;
	    		alert("Ingreso Exitoso Del Area...");
	        }
	}
}
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

</script>


<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
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
				con.cerrar();
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
//System.out.println("cod"+cod);
//System.out.println("cod_usuario yosimar"+codigou);
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
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white" > --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp"><b>Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Creaciones-Crear Area</b></b></div>
</td>
</tr>
  
<tr id="principal1">
<td>

<div >    

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br/>
					<div id="cabecera2"  style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="Copiamenu.jsp"%>
			</td>

			<td width="100%">    
                              <br/>
						<form style="margin-top:-17px;" id="form1" name="form1" onkeypress = "return pulsar(event);" >
						  <table width="100%" border="0" align="center" class="margen2">
						    <tr>
						      <td> <div align="center" id="cabecera2" class="style11" >CREAR GRUPO </div></td>
						    </tr>
						  </table>
						 
						  <table width="100%" >
						    <tr>
						      <td align="center" ><span class="style6">NOMBRE DEL GRUPO <input name="txtArea" type="text"  id="txtArea" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtArea);" size="25" > <input type="button" name="Button2" class="boton4" value="          CREAR         "  align="middle" onclick="Crear_Area(form1,this,event)"/></span></td>
			 
						    </tr>
						    
						  </table>
						</form>
					</td>
			</tr>
	</table>
</div>
</td></tr></table>
<%} %>
</body>
</html>
