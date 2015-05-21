<%@ page contentType='text/html; charset=iso-8859-1' language='java' import='java.sql.*' errorPage='' %>
<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>
<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1' />
<title>Untitled Document</title>
</head>

<body>

<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr><td colspan='7'><div id='cabecera2' class='style11' align='center'><input name='hidden' type='hidden' id='txtPerfil' value='1'>FORMULACION DE MEDICAMENTOS </div></td></tr>

  <tr><td width='8%'><span class='style12'>Medicamento</span></td><td colspan='3'><input name='txtMedicamento' type='text' id='txtMedicamento' size='50' onkeyup='AutoMedicamentoCardex()' /></td><td width='51%' colspan='3' rowspan='5' id='InfusionY'><table width="100%" border="1" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="4"  align="center">SELECCIONE INFUSION </td>
      </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table></td>
  </tr>
  
  
  <tr><td>&nbsp;</td><td colspan='3'><div id='MedCar'>MedCar</div></td></tr>

  <tr><td><span class='style12'>F.Farm</span></td><td width='18%'><div id='FF'><select name='cmbFFarmaceutica' id='cmbFFarmaceutica'><option value='Seleccione'>Seleccione</option></select></div></td>
  
    <td width='4%'><span class='style12'>Conc</span></td><td width='19%'><div id='CCN'><select name='cmbConcentracion' id='cmbConcentracion'><option value='Seleccione'>Seleccione</option></select></div></td></tr>
	
	
  <tr><td><span class='style12'>Unidad </span></td><td><div id='UNI'><select name='cmbUnidad' id='cmbUnidad'><option value='Seleccione'>Seleccione</option></select></div></td><td><span class='style12'>Lapso</span></td><td><span class='style12'><select name='cmbLapsoMed' id='cmbLapsoMed' onchange='CalcularDosis()'><option value='Seleccione'>Seleccione</option>
  
  <option value='&quot;+rs.getString(2)+&quot;'>&quot;+rs.getString(2)+&quot;</option>
  
  </select></span></td></tr>
  
  <tr><td>&nbsp;</td><td colspan='3' id='CargaInfu'>CargaInfu</td></tr></table>

</body>
</html>
