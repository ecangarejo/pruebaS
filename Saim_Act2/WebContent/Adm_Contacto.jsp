


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language=javascript src="1.js"></script>
<script language=javascript src="Validaciones.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Datos del Acompañante</title>

</head>
<body onLoad="focus(form1.txtnombre);">



<%String va=request.getParameter("va");
if(va==null){va="";}
%>
<script>
</script>
<%
if(va.equals("1")){%>
	<script> </script>
<%}
%>
<form id="form1" name="form1"  >
  <table width="100%" class="style6">
    <tr>
      <td colspan="4" id="cabecera2" class="style11"><div align="center"><strong>DATOS DEL ACOMPAÑANTE</strong></div></td>
    </tr>
    <tr>
      <td colspan='2'><input name='chkPorAtender' type='checkbox' id='chkPorAtender' onclick='VinoSoloDatos()'> Vino Solo</td>
      <td width="107">&nbsp;</td>
      <td width="178">&nbsp;</td>
    </tr>
    <tr>
      <td>Nombres</td>
      <td>
        <input type="text" name="txtnombre" id="txtnombre" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtnombre);" maxlength="20"  />
        <span class="Estilo1 style8">*</span>
		<b style="display:none" id="no" class="style8">(x)</b>      </td>
      <td>Apellidos</td>
      <td><label>
        <input type="text" name="txtapellido" id="txtapellido" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtapellido);" maxlength="20"/>
		<span class="Estilo1 style8">*</span>
      </label></td>
    </tr>
    <tr>
      <td>Cedula</td>
      <td><label>
        <input type="text" name="txtcedula" id="txtcedula"   maxlength="12" />
        <span class="Estilo1 style8"></span></label>	  </td>
      <td>Telefono</td>
      <td><label>
        <input type="text" name="txtelefono" id="txtelefono"  onkeyup="validarte(form1.txtelefono);" maxlength="12" />
        <span class="Estilo1 style8">*</span></label>
		<b style="display:none" id="te" class="style8">(x)</b></td>
    </tr>
    <tr>
      <td>Direccion</td>
      <td><label>
        <input type="text" name="txtdireccion" id="txtdireccion" onKeyUp="this.value=this.value.toUpperCase();" maxlength="30"/>
<input type="hidden" name="txtCodAcompa" id="txtCodAcompa"  />
      </label></td>
      <td>Parentesco</td>
      <td><label>
        <select name="cbparentesco" size="1" id="cbparentesco" >
		<option value="Seleccione" selected="selected">SELECCIONE...</option>
		
          <option value="PADRES">PADRES</option>
          <option value="ESPOSO(a)">ESPOSO(a)</option>
          <option value="ABUELO(a)">ABUELO(a)</option>
          <option value="HIJO">HIJO</option>
          <option value="HERMANO(a)">HERMANO(a)</option>
          <option value="TIO(a)">TIO(a)</option>
          <option value="SOBRINO(a)">SOBRINO(a)</option>
          <option value="PRIMO(a)">PRIMO(a)</option>
          <option value="AMIGO(a)">AMIGO(a)</option>
          <option value="CONOCIDO">CONOCIDO</option>
        </select>
        <span class="Estilo1 style8">*</span></label>
		<b style="display:none" id="pa" class="style8">(x)</b></td>
    </tr>
    <tr>
      <td colspan="4" id="cabecera2" class="style11"><div align="center"><strong>DATOS DEL RESPONSABLE</strong></div></td>
    </tr>
 <tr>
      <td colspan='2'><input name='chkPorAtender1' type='checkbox' id='chkPorAtender1' onclick='IgualAcompa()'> Igual Acompañante</td>
      <td width="107">&nbsp;</td>
      <td width="178">&nbsp;</td>
    </tr>
    <tr>
      <td>Nombres</td>
      <td><input type="text" name="txtnombre1" id="txtnombre1" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtnombre);" maxlength="20"  />
          <span class="Estilo1 style8">*</span> <b style="display:none" id="no" class="style8">(x)</b> </td>
      <td>Apellidos</td>
      <td><label>
        <input type="text" name="txtapellido1" id="txtapellido1" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtapellido);" maxlength="20"/>
		<span class="Estilo1 style8">*</span>
      </label></td>
    </tr>
    <tr>
      <td>Cedula</td>
      <td><label>
        <input type="text" name="txtcedula1" id="txtcedula1"   maxlength="12" />
        <span class="Estilo1 style8"></span></label>      </td>
      <td>Telefono</td>
      <td><label>
        <input type="text" name="txtelefono1" id="txtelefono1"  onkeyup="validarte(form1.txtelefono);" maxlength="12" />
        <span class="Estilo1 style8">*</span></label>
          <b style="display:none" id="te" class="style8">(x)</b></td>
    </tr>
    <tr>
      <td>Direccion</td>
      <td><label>
        <input type="text" name="txtdireccion1" id="txtdireccion1" onKeyUp="this.value=this.value.toUpperCase();" maxlength="30"/>
      </label></td>
      <td>Parentesco</td>
      <td><label>
        <select name="cbparentesco1" size="1" id="cbparentesco1" >
          <option value="Seleccione" selected="selected">SELECCIONE...</option>
          <option value="PADRES">PADRES</option>
          <option value="ESPOSO(a)">ESPOSO(a)</option>
          <option value="ABUELO(a)">ABUELO(a)</option>
          <option value="HIJO">HIJO</option>
          <option value="HERMANO(a)">HERMANO(a)</option>
          <option value="TIO(a)">TIO(a)</option>
          <option value="SOBRINO(a)">SOBRINO(a)</option>
          <option value="PRIMO(a)">PRIMO(a)</option>
          <option value="AMIGO(a)">AMIGO(a)</option>
          <option value="CONOCIDO">CONOCIDO</option>
        </select>
        <span class="Estilo1 style8">*</span></label>
          <b style="display:none" id="pa" class="style8">(x)</b></td>
    </tr>
    
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="4" align="center"><input type="button" class="boton4" name="button" id="button" value="      Guardar      "  onClick="vac()" /></td>
    </tr>
    <tr>
      <td><label></label></td>
      <td><span class="Estilo1 style8">Datos Requeridos *</span> <b style="display:none" id="va" class="style8">(x) Vacio</b></td>
      <td>&nbsp;</td>
      <td><label></label></td>
    </tr>
  </table>
</form>

</body>
</html>