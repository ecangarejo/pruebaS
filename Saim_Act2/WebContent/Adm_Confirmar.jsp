

<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.*" import="logica.Conexion"
    pageEncoding="utf-8"%>
<%@ page import="adm_bean.Paciente,java.util.*" import="adm_logica.* "%>
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
String ce=request.getParameter("cedula");
String ti=request.getParameter("tipo");
String nom=request.getParameter("nom");
String nomadre=request.getParameter("nomadre");
String primadre=request.getParameter("primadre");
String semadre=request.getParameter("semadre");

if(ce==null){
	ce="";
}
if(nom==null){
	nom="";
}
if(nomadre==null){
	nomadre="";
}

if(ti==null){
	ti="";
}
//System.out.println(" Aca en confirmar: "+ti);

%>
<style type="text/css">
.style1 {color: #FFFFFF}
.style2 {font-size: xx-small}
.style3 {font-size: x-small; }
body {
	background-image: url(Imagenes/ultima.jpg);
	background-repeat: repeat-y;
}
</style>
<script language=javascript src="Validaciones.js"></script>
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
				con.conn.close();
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
<form name="form1" id="form1">
     <font face="Arial, Helvetica, sans-serif">
    <table border="1" width="763">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><font face="Arial, Helvetica, sans-serif"><span class="style3">CLINICA DE LA COSTA</span></font></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><span class="style1">
          <input name="txtnom" type="hidden" id="txtnom" value="<%=nom%>" >
          <input name="txtape" type="hidden" id="txtape" value="<%=nomadre %>" >
          <input name="txtce" type="hidden" id="txtce" value="<%=ce%>">
          <input name="txttipo" type="hidden" id="txttipo" value="<%=ti%>" >
          <input name="txtpape" type="hidden" id="txtpape" value="<%=primadre %>" >
          <input name="txtsap" type="hidden" id="txtsap" value="<%=semadre %>">
        </span></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td><font size="2">Nombre del Bebe</font></td>
        <td><font size="2">Primer Apellido de la Madre</font></td>
        <td><font size="2">Segundo Apellido de la Madre</font></td>
        <td><font size="2">Fecha de Nacimiento</font></td>
        <td><font size="2">Codigo</font></td>
        <td><font size="2">Crear Admision</font></td>
      </tr>
      <%
Vector lista =(Vector)request.getSession().getAttribute("listaNacido");
Vector lista1 =(Vector)request.getSession().getAttribute("listaNac"); 
Paciente pa = new Paciente();
//cen = (Censo)lista.get(1);
//out.println(cen.getPaciente());
String codpaciente="";
for(int i=0; i< lista.size();i++){
	pa = (Paciente)lista.get(i);
	//codpaciente = cen.getCodigo();
	
%>
      <tr>
        <td><font size="1"><%=pa.getNombre() %></font></td>
        <td><font size="1"><%=pa.getPapellido()%></font></td>
        <td><font size="1"><%=pa.getSapellido()%></font></td>
        <td><font size="1"><%=pa.getFecha() %></font></td>
        <td><font size="1"><%=lista1.get(i) %></font></td>
        <td><a href="Adm_Ingreso.jsp?z=1&va=1&codigo=1&cedula=<%=ce%>&tipo=<%=pa.getDocumento()%>&nom=<%=pa.getNombre()+" "+pa.getPapellido()+" "+pa.getSapellido()%>&cod=<%=lista1.get(i)%>"><font size="1">Realizar</font></a></td>
      </tr>
      <%} 

%>
    </table>
    </font>
  </center>
  <p>&nbsp;</p>
  <table width="763" border="0">
<tr>
      <td width="236">Ingresar Otro...
        <input name="button" type="button" id="button" value="Confirmar..." onClick="getCheckedValue(form1);"></td>
      <td width="490" nowrap bordercolor="#ECE9D8"><label></label></td>
      <td width="8" nowrap bordercolor="#ECE9D8"><label></label></td>
      <td width="11"><label></label></td>
    </tr>
</table>

<label></label>
<label></label>
</form>
</div>
</div>

</body>
</html>