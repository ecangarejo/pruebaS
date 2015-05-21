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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Crear Inventario</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
</style>
<script src="farc_CrearArticulo.js" type="text/javascript"></script>
<script src="Validaciones.js" type="text/javascript"></script>
<script language="javascript"></script>
</head>

<body>

<table width="100%">
<tr >
<td>
</td>
</tr>

<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">CREAR INVENTARIO </a></b></div>
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
          <td width="52">Articulo  </td>
          <td width="356"><select name="cmbArticulo" id="cmbArticulo" style="width:100%">
            <option value="Seleccione" selected="selected">Seleccione</option>
            <%	
            Conexion con=new Conexion();
            ResultSet r3;
	Statement s3 = null;
	s3=con.conn.createStatement();
	r3=s3.executeQuery("SELECT med.codigo as CodMed,med.nombre as NomMedicamento FROM  medicamentos med,fact_prog_med fp,fact_programas pr WHERE med.estado = 1 AND med.codigo=fp.cod_med AND fp.cod_prog=pr.cod_programa ORDER BY med.cod_grupoFK,med.nombre  ");
	while(r3.next()){
		%>
            <option value="<%=r3.getString("CodMed") %>" ><%=r3.getString("NomMedicamento") %></option>
            <%}
	r3.getStatement().close();

%>
          </select></td>
          <td width="84">Fecha Venc </td>
          <td width="72"><input name="txtFechaVenci" type="text" id="txtFechaVenci"   onKeyUp="masca(this,'/',patron,true)" style="width:100%" /></td>
          <td width="40">Lote</td>
          <td width="90"><input name="txtLote" type="text" id="txtLote" size="15" /></td>
          <td width="93">Cantidad</td>
          <td width="60"><input name="txtCantidad" type="text" id="txtCantidad" size="10" /></td>
          <td width="53">Invima</td>
          <td width="121"><input name="txtInvima" type="text" id="txtInvima" size="18" /></td>
          <td width="55">Bodega</td>
          <td width="217"><select name="cmbBodega" id="cmbBodega" style="width:100%">
            <%	ResultSet r2=null;
	Statement s2 = null;
	s2=con.conn.createStatement();
	r2=s2.executeQuery("SELECT * FROM farc_bodegas ");
	while(r2.next()){
		%>
            <option value="<%=r2.getString("codigo") %>" ><%=r2.getString("nombre") %></option>
            <%}
	r2.getStatement().close();

%>
          </select></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="8"><input type="button" name="Button" value="Ingresar" style="width:100%" onclick='GuardarInventarioHNJ()' /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
    </div></td>
    </tr>
</table>



</form>
</td></tr></table>
</div>
</td></tr></table>

</body>
</html>
