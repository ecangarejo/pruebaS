<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<style type="text/css">
<!--
.style1 {color: #FFFFFF}
-->
</style>
<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:600px; border:1px solid black;  margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 300px ; overflow : scroll ; desbordamiento: auto;}

</style>
<script src="hic_autocompletarPacientesAdmision.js" type="text/javascript"></script>
<script src="hic_ListaPacientes.js" type="text/javascript"></script>
<script src="hic_VerFormatos.js" type="text/javascript"></script>
</head>

<body onload="Admisiones()">
<table width="100%" border="1" cellspacing="0">
  <tr>
    <td colspan="5" bgcolor="#01426E"><div align="center" class="style1">Lista De Pacientes Admitidos</div></td>
  </tr>
  <tr>
    <td width="19%">Nombre Del Paciente </td>
    <td colspan="4"><input name="txtNomPaciente" type="text" id="txtNomPaciente" size="80" onkeyup="autocompletaPacAdmision()" /></td>
  </tr>
  <tr>
    <td colspan="5" bgcolor="#669999" height="2">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="4"><div id="sugerencias"></div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td width="19%">&nbsp;</td>
    <td width="22%">&nbsp;</td>
    <td width="21%"><input name="btnAtender" type="button" id="btnAtender" value="Atender" /></td>
    <td width="19%">&nbsp;</td>
  </tr>
</table>

</body>
</html>
