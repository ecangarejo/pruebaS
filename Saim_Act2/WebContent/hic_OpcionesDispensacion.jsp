<%@ page language="java" contentType="text/html; charset=utf-8" import="adm_logica.Conexion" import="java.sql.*" 
pageEncoding="ISO-8859-1"%>
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
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Detalle de Dispensacion</title>

<script language="javascript">
function A(tecla,e){
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){BuscarDevolverAdmision();}
}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

</script>
<script src="hic_NuevaFormulacionKardex.js" type="text/javascript"></script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onload='' id="back">


<%
String CodHoraDisp=request.getParameter("CodHoraDisp");
if(CodHoraDisp==null){CodHoraDisp="";}

String CodUsu=request.getParameter("CodUsu");
if(CodUsu==null){CodUsu="";}

Conexion con=new Conexion();
ResultSet rs2;
Statement st2 = null;
   String Medicamento="";         
   String Horario="";
   String Paciente="";
      	
       	st2 = con.conn.createStatement();
       	
       	rs2=st2.executeQuery("SELECT CONCAT(md.nombre,' ',md.concentracion,' ',fu.sigla) AS medicamento,CONCAT(hhd.fecha_dosis,'/',hhd.hora_dosis) AS dosis,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS paciente FROM hic_formulacion hf,hic_detalle_formulacion hdf,hic_horas_dosificacion hhd,medicamentos md,farc_unidades fu,adm_paciente ap WHERE md.codigo=hdf.CodMedicamento_fk AND fu.codigo=md.cod_unidadFK AND ap.pac_codigo_paciente=hf.CodPac_fk AND hf.codigo=hdf.CodFormulacion_fk AND hdf.codigo=hhd.CodDetForm_FK AND hhd.codigo="+CodHoraDisp+" ");%>
			<%while(rs2.next()){
				
	        Medicamento=rs2.getString(1);
	        Horario=rs2.getString(2);
	        Paciente=rs2.getString(3);
			}
			rs2.getStatement().close();
			con.cerrar();

%>

<table width="100%" border="1">
  <tr>
    <td colspan="3"><input name='txtCodUsu' type='hidden' id='txtCodUsu'  value="<%=CodUsu %>"   ><input name='txtCodHoraDisp' type='hidden' id='txtCodHoraDisp'   value="<%=CodHoraDisp %>"   ><div id='cabecera2' class='style11' align='center'>DISPENSACION DE <%=Medicamento %> PARA  <%=Paciente %> DEL DIA <%=Horario %></div></td>
    </tr>
  <tr>
    <td><label>
      <input name="radiobutton" type="radio" value="radiobutton" id="Di" >
      Dispensar</label></td>
    <td width="64%" colspan="2">&nbsp;</td>
    </tr>
 <!--  
 <tr>
    <td width="36%"><label>
      <input name="radiobutton" type="radio" value="radiobutton" id="Chm" >
      Cambiar hora medicamento</label></td>
    <td colspan="2"><select name="cmbHora" id="cmbHora">
      <option value="HH">HH</option>
      <option value="01">01</option>
      <option value="02">02</option>
      <option value="03">03</option>
      <option value="04">04</option>
      <option value="05">05</option>
      <option value="06">06</option>
      <option value="07">07</option>
      <option value="08">08</option>
      <option value="09">09</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
    </select>
      <select name="cmbMinutos" id="cmbMinutos">
        <option value="MM">MM</option>
        <option value="00">00</option>
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="30">30</option>
        <option value="40">40</option>
        <option value="50">50</option>
      </select>
      <select name="cmbTipo" id="cmbTipo">
        <option value="AM">AM</option>
        <option value="PM">PM</option>
      </select></td>
    </tr> -->
  <tr>
    <td><label>
      <input name="radiobutton" type="radio" value="radiobutton" id="Cd">
      Cancelar dosis</label></td>
    <td colspan="2"><label>
      <textarea name="txtObservacionCancelar" cols="32" rows="3" id="txtObservacionCancelar"></textarea>
    </label></td>
    </tr>
  <tr>
    <td colspan="3" align="center"><input name="Button" type="button" class="boton4" value="        Aplicar        "  onclick="DispensarDosis()"></td>
    </tr>
</table>
</body>
</html>