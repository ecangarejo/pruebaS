

<%@ page language="java" contentType="text/html; charset=utf8" import="java.sql.*" import="adm_logica.Conexion"
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
<title>Insert title here</title>
<%
String cedu=request.getParameter("cedula");
String va=request.getParameter("va");

String tipo=request.getParameter("tipo");
String nomadre=request.getParameter("nomadre");
String primadre=request.getParameter("primadre");
String semadre=request.getParameter("semadre");

if(semadre==null){
	semadre="";
}

if(cedu==null){
	cedu="";
}
if(primadre==null){
	primadre="";
}

if(nomadre==null){
	nomadre="";
}else{
	nomadre="HIJO DE "+nomadre;
}

//System.out.println(va);
if(va==null){
	va="";
}
if(tipo==null){
	tipo="";
}
%>

<script language=javascript src="Validaciones.js"></script>
<style type="text/css">
.style1 {color: #FFFFFF}
.style2 {font-size: xx-small}
.style3 {font-size: x-small; }
body {
	background-image: url(Imagenes/ultima.jpg);
	background-repeat: repeat-y;
}
</style>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
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
				rs2.getStatement().close();
				con.cerrar();
	       	%>
<div id="contenedor">

		<div id="cabecera">
         <img src="Imagenes/Admisiones.jpg" />
	</div>
	<div id="cabecera2">
	  <div align="right" class="style2">Usted Ingreso al Sistema Como:<%=idu %></div>
	</div>

				<div id="lateral">
                <a href="Seguridad_login?accion=cerrar" class="texto" style="text-decoration:blink" style="color:#FF0000"> --Cerrar Session--</a><br><br>
                <a href="menuadm.jsp" class="style3">Volver al Menu</a>
          
		</div>
		<div id="principal">
<form id="form1" name="form1">
  <table width="763" border="0">
    <tr bgcolor="#0066FF">
      <td colspan="4" align="center" bgcolor="#01426E" class="style1">Datos Demograficos</td>
    </tr>
    <tr>
      <td width="24%">&nbsp;</td>
      <td width="20%">&nbsp;</td>
      <td width="24%">&nbsp;</td>
      <td width="32%">&nbsp;</td>
    </tr>
    <tr>
      <td>Tipo de Documento:</td>
      <td><label>
        <input type="text" name="txttipo" value="<%=tipo%>">
        <span class="style1">*</span></label></td>
      <td>Numero de Identificacion: </td>
      <td><label>
        <input type="text" name="txtnum"  value="<%=cedu%>"readonly="">
        <span class="style1">*</span></label></td>
    </tr>
    <tr>
      <td>Tipo Afiliacion: </td>
      <td><label>
        <input type="text" name="txtafi" value="Beneficiario" readonly="">
        <span class="style1">*</span>      </label></td>
      <td>Genero:</td>
      <td><label>
        <select name="cbgenero" size="1" id="cbgenero">
          <option selected="selected">SELECCIONE...</option>
          <option>FEMENINO</option>
          <option>MASCULINO</option>
        </select>
        <span class="style1">*</span></label></td>
    </tr>
    <tr>
      <td>Primer Apellido De la Madre</td>
      <td><input type="text" name="txtape" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtape);" maxlength="30" value="<%=primadre %>" >
      <span class="style1">*</span></td>
      <td>Segundo Apellido De la Madre: </td>
      <td><input type="text" name="txtsape" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtsape);" maxlength="30" value="<%=semadre %>">
      <span class="style1">*</span></td>
    </tr>
    <tr>
      <td>Nombres:</td>
      <td><input type="text" name="txtnombre" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtnombre);" maxlength="30" value="<%=nomadre %>" >
      <span class="style1">*</span></td>
      <td>Fecha De Nacimiento (ddmmaaaa) </td>
      <td><input type="text" name="txtfecha" onKeyUp="masca(this,'/',patron,true)" maxlength="10" /><span class="style1">*</span></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="4" class="style1">Campos Requeridos * </td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><label></label>
        <label></label></td>
      <td align="center"><label>
      <input type="button" name="Submit" value="Ingresar" onClick="Nacido_Vivo(form1);">
      </label></td>
      <td><label></label></td>
    </tr>
  </table>
</form>
</div>
</div>
</body>
</html>