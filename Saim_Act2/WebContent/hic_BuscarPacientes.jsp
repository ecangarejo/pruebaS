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
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
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
<title>Buscar Pacientes</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
.style68 {font-size: xx-small}
</style>

<script languaje="javascript" src="hic_SeleccionarPacientes.js"></script>

<script language="javascript">
function focus(){
	document.getElementById("txtNomPaciente").focus();
}
</script>

<%
if(codigou.equals("")){%>

<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");window.location.href="Seg_login.jsp";</script>
<%}else{

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
				//con.cerrar();				
				ResultSet rs3;
				Statement st3=null;
				st3=con.conn.createStatement();
				rs3=st3.executeQuery("SELECT COUNT(estado) FROM hic_adm_formatos_pac WHERE estado=0 AND codigo_usu_fk="+codigou+"");
				int pend =0;
				if(rs3.next()){
					pend=rs3.getInt(1);
				}
				rs3.getStatement().close();
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				rs4.getStatement().close();
				
				int pacientes=0;
				ResultSet rs5;
				Statement st5=null;
				st5=con.conn.createStatement();
				rs5=st5.executeQuery("SELECT DISTINCT ap.pac_codigo_paciente,ap.nombre,ap.primer_apellido,ap.segundo_apellido,ap.tipo_documento,ap.numero_documento,aad.fecha_registro,aad.hora_registro,acc.servicio FROM adm_paciente ap,adm_admisiones aad,adm_censo_cama acc WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.estado=0 AND aad.cen_numero_cama_fk=acc.cen_numero_cama AND acc.servicio=1   ORDER BY aad.fecha_registro,hora_registro ASC");
				
				String fecha;
				String hora;
			
				
				while(rs5.next()){
					fecha=rs5.getString(7);
					hora=rs5.getString(8);
					
					ResultSet rs6;
					Statement st6=null;
					st6=con.conn.createStatement();
					rs6=st6.executeQuery("SELECT TIMESTAMPDIFF(HOUR,('"+fecha+"''"+hora+"'),TIMESTAMP(CURDATE(),CURTIME()))");
					if(rs6.next()){
						pacientes=pacientes+1;
					}
					rs6.getStatement().close();
				}
				
				rs5.getStatement().close();
				
				
				
				ResultSet rs7;
				Statement st7=null;
				st7=con.conn.createStatement();
				rs7=st7.executeQuery("select sdp.profesion from seg_datos_personales sdp,seg_usuario su where sdp.dat_codigo=su.dat_codigo_fk and su.usu_codigo="+codigou+"");
				String prof=null;
				if(rs7.next()){
					prof=rs7.getString(1);
				}
				rs7.getStatement().close();
				System.out.println(prof);
				int pr=0;
				int mens=0;
				if((prof.equals("Enfermera"))||(prof.equals("Fisioterapeuta"))){
					pr=1;
				}else{
					if((prof.equals("Medico General"))||(prof.equals("Especialista"))){
						mens=1;
						System.out.println("Profesion"+prof);
					}
				}
				con.cerrar();	
	       	%>

<script language="javascript">		
function CargarForPend(){
	var CodUsuario=<%=codigou%>;
	var pend=<%=pend%>;
	var pr=<%=pr%>;
	var mens=<%=mens%>;
	if(pend>2){
			if(pr==0){
			CargarAlertas(CodUsuario, pend);
			}else{
				if(pend>15){
					CargarAlertas(CodUsuario, pend);
				}
			}
			}else{
				if(mens==1){
					//TB_show('Notificacion', "notificacion.jsp?TB_iframe=true&height=150&width=700", null);
					}
			}
}
</script>
	       	
	       
</head>

<body onload="CargarForPend()">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="HistoriaClinica.jsp">Historias Clinicas</a>-Consultar Historia Clinica-Ver Historia</b></div>
</td> 
</tr>
<tr align=center>
<td >
		<%if ((codigou.equals("74"))||(codigou.equals("101"))||(codigou.equals("295"))||(codigou.equals("143"))||(codigou.equals("520"))){
			%>
			<font color=red><b>ACTUALMENTE HAY <%=pacientes%> PACIENTES EN URGENCIA CON MAS DE 24 HORAS, CLICK AQUI PARA </b></font><a href='rep24h.jsp?TB_iframe=true&height=520&width=700' class='thickbox' title='Pacientes con mas de 24 Horas'>REVISAR </a>
		<%} //System.out.println(idu); System.out.println(codigou);%>
	
	
<td>
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">  
<form id="form1" name="form1">
<div id="Pacientes"></div>
<table width="100%" border="1" class="style6">
  <tr>
    <td colspan="5" class="style11" id="cabecera2" ><div align='center'>SELECCIONE UNIDAD </div></td>
    </tr>
  <tr>
    <td><label><input name="rbUnidad" type="radio" value="1" onclick="VerUnidad()" /> URGENCIA</label></td>
    <td><label> <select id='cmbHospH' onchange='BuscarPacientesSeleH()' ><option selected="selected">HOSPITALIZACION</option>
    <% 
 //ResultSet rs3=null;
 //Statement st3 = null;  
 try{
 	Conexion con2=new Conexion();
 	st3 = con2.conn.createStatement();
 	rs3=st3.executeQuery("SELECT asb.* FROM adm_area ar,adm_subarea asb WHERE ar.codigo=asb.codigoarea AND ar.nombre='HOSPITALIZACION'");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(1)%>"><%=rs3.getString(2)%></option>
 	<%}
 	
 	rs3.getStatement().close();
 	st3.close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
</select>
</label>

</td>
    <td><select id='cmbHospU' onchange='BuscarPacientesSeleU()'><option selected="selected">UCI</option>
    <% 
 ResultSet rs8=null;
 Statement st8 = null;  
 try{
 	Conexion con8=new Conexion();
 	st8 = con8.conn.createStatement();
 	rs8=st8.executeQuery("SELECT asb.* FROM adm_area ar,adm_subarea asb WHERE ar.codigo=asb.codigoarea AND ar.nombre='UCI'");%>
 	<%while(rs8.next()){%>
	<option value="<%=rs8.getString(1)%>"><%=rs8.getString(2)%></option>
 	<%}
 	
 	rs8.getStatement().close();
 	st8.close();
 	con8.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
</select>
</label></td>
	<td><select id='cmbHospA' onchange='BuscarPacientesSeleA()' ><option selected="selected">AMBULATORIO</option>
    <% 
 ResultSet rs6=null;
 Statement st6 = null;  
 try{
 	Conexion con6=new Conexion();
 	st6 = con6.conn.createStatement();
 	rs6=st6.executeQuery("SELECT asb.* FROM adm_area ar,adm_subarea asb WHERE ar.codigo=asb.codigoarea AND ar.nombre='AMBULATORIO' ");%>
 	<%while(rs6.next()){%>
	<option value="<%=rs6.getString(1)%>"><%=rs6.getString(2)%></option>
 	<%}
 	
 	rs6.getStatement().close();
 	st6.close();
 	con6.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>


</select></label>
<td><input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Pendientes" onClick="redire()"></input></td>
</td>
  </tr>
</table>

<table width="100%" border="1" class="style6">
  <tr>
    <td colspan="2" class="style11" id="cabecera2"><div align='center'>BUSCAR PACIENTES </div></td>
  </tr>
  <tr>
    <td width="74%">Nombre del Paciente :
      <input name="txtNomPaciente" type="text" id="txtNomPaciente" size="100" onkeyup="AutoCompletaPaciente()" /></td>
    <td width="26%"><table width="100%" border="1" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%">&nbsp;
            </th>
        </td>
        <td width="20%" bgcolor="#A9D0F5">&nbsp;</td>
        <td width="20%" bgcolor="yellow">&nbsp;</td>
        <td width="20%" bgcolor="orange">&nbsp;</td>
        <td width="20%" bgcolor="red">&nbsp;</td>
      </tr>
      <tr>
        <td class="pregunta style68">Normal</td>
        <td class="pregunta style68">Sin Formatos </td>
        <td class="pregunta style68">Ult Formato &gt;06 hr </td>
        <td class="pregunta style68">Ult Formato &gt;12 hr </td>
        <td class="pregunta style68">Ult Formato &gt;24 hr </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" class="style11" id="cabecera2"><div align="center">PACIENTES ENCONTRADOS</div></td> 
    </tr>
	</table>
	<table width="100%" border='1'>
  <tr>
    <td width="17%">Ubicacion</td>
    <td width="17%">Identificacion</td>
    <td width="42%">Nombre y Apellido </td>
    <td width="14%">Fecha / Hora de Ingreso </td>
    <td width="10%"><div>Seleccionar</div></td>
  </tr>
  <tr>
    <td colspan="5"><div id="ResultadosBusqueda" ></div></td>
  </tr>
  <tr>
    <td colspan="5"><div align="center">
      <input name="btnObservar" type="button" id="btnObservar" class="boton4" value="     Atender     " onclick="VerMultiples()" />
    </div></td>
  </tr>
</table>
<input name="CodPac" type="hidden" id="CodPac" />
<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
