
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Comentario</title>
<style type='text/css'>
<!--
.style1 {color: #FF0000}
.style2 {color: #FFFFFF}
-->
</style>
<script language='javascript' src="lab_comentario.js"></script>
</head>
<body>
<table width="480" border="0" >
  <tr>
    <td colspan="5" align="center"><input name="txtcodarea" type="hidden" id="txtcodarea"  />
      COMENTARIOS
      <label>
      <input name="txtcodexa" type="hidden" id="txtcodexa"  />
      </label></td>
  </tr>
  <tr>
    <td colspan="5">Elija el Examen al Cual le va a Crear un Comentario      </td>
  </tr>
  <tr>
    <td width="167">Pertenece Grupo    </td>
    <td width="47"><label>
      <input name="radio" type="radio" value="radiobutton" id="1" onclick="Radio()" />
    </label></td>
    <td colspan="2">Pertenece a SubGrupo </td>
    <td width="85"><input name="radio" type="radio" value="radiobutton" id="2" onclick="Radio()" />
    <input name="txtcodsubar" type="hidden" id="txtcodsubar"  /></td>
  </tr>
</table>
<div id="valor" ></div>
<div id="tipos" ></div>
</body>
</html>