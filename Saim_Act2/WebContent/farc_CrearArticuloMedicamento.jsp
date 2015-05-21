<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Crear Medicamento y/o Dispositivo</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script src="farc_CrearArticulo.js" type="text/javascript"></script>
<script language="javascript"></script>
</head>

<body>
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
				//con.cerrar();				
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Farmacia.jsp">Servicio Farmaceutico </a>-Creaciones-Crear Articulo </b></div>
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
    <td><div id="contenido">
      <table width="100%" border="1" cellspacing="0" cellpadding="0">
        <tr>
          <td colspan="12" align="center">CREAR MEDICAMENTO y/o ARTICULO </td>
          </tr>
        <tr>
          <td colspan="2">Nombre Medicamento o Insumo </td>
          <td colspan="4"><input name="txtNombreArticulo" type="text" id="txtNombreArticulo" style='width:100%' /></td>
          <td width="7%">Codigo CUM </td>
          <td colspan="2"><input name="txtCodigoCUM" type="text" id="txtCodigoCUM" style="width:40%" />
          ATC
            <input  name="txtCodigoATC" type="text" id="txtCodigoATC" size="12" /></td>
          <td width="11%">Grupo</td>
          <td colspan="2"><select name="cmbGrupo" id="cmbGrupo" style="width:100%">
            <option value="Seleccione" selected="selected">Seleccione</option>
<%	ResultSet r2;
	Statement s2 = null;
	s2=con.conn.createStatement();
	r2=s2.executeQuery("SELECT * FROM farc_grupo");
	while(r2.next()){
		%>
		<option value="<%=r2.getString("codigo") %>" ><%=r2.getString("descripcion") %></option>
	<%}
	r2.getStatement().close();

%>
          </select>          </td>
        </tr>
        <tr>
          <td width="7%">Concentracion</td>
          <td width="11%"><label>
            <input name="txtConcentracion" type="text" id="txtConcentracion" style="width:50%" />
          </label></td>
          <td width="4%">Unidad</td>
          <td width="11%"><select name="cmbUnidad" id="cmbUnidad" style="width:100%">
            <option value="Seleccione" selected="selected">Seleccione</option>
<%	ResultSet r3;
	Statement s3 = null;
	s3=con.conn.createStatement();
	r3=s3.executeQuery("SELECT * FROM farc_unidades ");
	while(r3.next()){
		%>
		<option value="<%=r3.getString("codigo") %>" ><%=r3.getString("sigla") %></option>
	<%}
	r3.getStatement().close();

%>
          </select>          </td>
          <td width="8%">F.Farmaceutica</td>
          <td width="13%"><select name="cmbFFarmaceutica" id="cmbFFarmaceutica" style="width:100%">
            <option value="Seleccione" selected="selected">Seleccione</option>
<%	ResultSet r4;
	Statement s4 = null;
	s4=con.conn.createStatement();
	r4=s4.executeQuery("SELECT * FROM farc_formafarmaceutica ORDER BY forma_farmaceutica");
	while(r4.next()){
		%>
		<option value="<%=r4.getString("codigo") %>" ><%=r4.getString("descripcion") %></option>
	<%}
	r4.getStatement().close();

%>
          </select>          </td>
          <td>Med.Control</td>
          <td width="8%"><select name="cmbControl" id="cmbControl" style="width:100%">
            <option value="Seleccione" selected="selected">Seleccione</option>
            <option value="Si">Si</option>
            <option value="No">No</option>
          </select>          </td>
          <td width="10%">Clasificacion</td>
          <td><select name="cmbClasificacion" id="cmbClasificacion" style="width:100%">
            <option value="Seleccione">Seleccione</option>
            <option value="Pos">Pos</option>
            <option value="No Pos">No Pos</option>
          </select>          </td>
          <td width="2%">Tipo</td>
          <td width="8%"><select name="cmbTipo" id="cmbTipo" style="width:100%"
		  >
            <option value="Seleccione" selected="selected">Seleccione</option>
            <option value="Generico">Generico</option>
            <option value="Comercial">Comercial</option>
          </select>          </td>
        </tr>
        <tr>
          <td colspan="2">Valor Medicamento o Insumo </td>
          <td colspan="2"><input name="txtValorArticulo" type="text" id="txtValorArticulo" size="1"  /></td>
          <td><input type="button" name="Button" value="Ingresar" onclick='GuardarArticuloPrecio()'  style="width:100%"/></td>
          <td><font color='red'>Todos los campos de este formulario son requeridos.</font></td>
          <td>&nbsp;</td>
          <td colspan="5">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="2">&nbsp;</td>
          <td colspan="4">&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="5">&nbsp;</td>
        </tr>
      </table>
    </div></td>
    </tr>
</table>



<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
