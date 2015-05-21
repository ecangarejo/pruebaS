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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Creacion De Usuarios</title>
<style type='text/css'>
<!--
/*- Menu Tabs J--------------------------- */

    #tabsJ {
      float:left;
      width:100%;     
      font-size:80%;
      line-height:normal;
          border-bottom:2px solid #1a5581;
      }
    #tabsJ ul {
        margin:0;
        padding:10px 10px 0 50px;
        list-style:none;
      }
    #tabsJ li {
      display:inline;
      margin:0;
      padding:0;
      }
    #tabsJ a {
      float:left;
      background:url('Imagenes/tableftJ.gif') no-repeat left top;
      margin:0;
      padding:0 0 0 5px;
      text-decoration:none;
      }
    #tabsJ a span {
      float:left;
      display:block;
      background:url('Imagenes/tabrightJ.gif') no-repeat right top;
      padding:5px 15px 4px 6px;
      color:#24618E;
      }
    /**Commented Backslash Hack hides rule from IE5-Mac \*/
    #tabsJ a span {float:none;}
    /* End IE5-Mac hack */
    #tabsJ a:hover span {
      color:#FFF;
      }
    #tabsJ a:hover {
      background-position:0% -42px;
      }
    #tabsJ a:hover span {
      background-position:100% -42px;
      }      
    #tabsJ a.tabactive span {
      color:#FFF;
      }
    #tabsJ a.tabactive {
      background-position:0% -42px;
      }
    #tabsJ a.tabactive span {
      background-position:100% -42px;
      }
 	#tabsJ #current a {
      background-position:0% -42px;
      }
    #tabsJ #current a span {
      background-position:100% -42px;
      color:#FFF;
      }
-->
</style>
<script language=javascript src="Usuario.js"></script>

<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
</head>

<body>
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
				rs2.close();
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
<tr align="right" class="style6">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"  style="text-decoration:none" style="color: white" > --Cerrar Session--</a></div>
</td>
</tr>

<tr>
<td>
	<div id="cabecera1" align="center" class="style6"><a href="menu.jsp">Menu Principal</a>-Seguridad-Creacion de Usuario</div>
</td>
</tr>

<tr>
<td id="principal1">
<center>         
			<table width="100%" > 
				<tr>   
		
		
					<td width="100%">
<br />
		<form id="form1"  name="form1"  >	
		<table width="100%" align="center" class="style6">
		  <tr id="cabecera2" class="style11">
		    <td colspan="5" align="center" ><div><span >BUSCAR USUARIO</span></div> </td>
		    </tr>
		  <tr>
		    <td width="349"><div class="style2" align="right">TIPO DOCUMENTO </div></td>
		    <td width="160"><div class="style3">
		      <select name="cmbTipoDoc" id="cmbTipoDoc">
		        <option value="Seleccione" selected="selected">Seleccione</option>
		        <option value="Cedula Ciudadania">Cedula Ciudadania</option>
		        <option value="Pasaporte">Pasaporte</option>
		        <option value="Cedula Extranjeria">Cedula Extranjeria</option>
		        </select>
		      </div></td>
		    <td width="157"><div class="style2">NUMERO DOCUMENTO</div></td>
		    <td width="166"><input name="txtNumeroDocumento" type="text" class="style3" id="txtNumeroDocumento"  size="30" maxlength="15" />
              <span class="style8">*</span></td>
		    <td width="238"><input name="Button" type="button" class="boton4" value="Buscar" onclick="BuscarUsuario()" /></td>
		  </tr>
		  <tr>
		    <td colspan="5"><div id="Resultado"></div></td>
		    </tr>
		</table>
		</form>
</td></tr></table>
</div>
</td></tr></table>
    <%} %>
</body>
</html>
