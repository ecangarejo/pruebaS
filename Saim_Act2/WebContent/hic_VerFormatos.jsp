
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<%
String CodAdm=request.getParameter("CodAdm");
if(CodAdm==null){
	CodAdm="";
}

String CodPac=request.getParameter("CodPac");
if(CodPac==null){
	CodPac="";
}

%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Ver Formato</title>

<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias { Cursor : pointer; width:510px; border:1px solid black; display:none; margin-left: 215px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
#formulario {height : 130px ; altura: 200px;  overflow : scroll ; desbordamiento: auto;}
#FormatosPaciente{height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
#areas {height : 130px ; altura: 200px;  overflow : scroll ; desbordamiento: auto;}
#LaboratoriosPaciente{height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
#ImagenologiaPaciente{height : 90px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
</style>

<script src="hic_autocompletarFormato.js" type="text/javascript"></script>
<script src="hic_VerFormatos.js" type="text/javascript"></script>
<script src="hic_IngresarResultados.js" type="text/javascript"></script>
<script language='javascript'>
function enviar(){
	var CodAdm=<%=CodAdm%>;
	var CodPac=<%=CodPac%>;
	BuscarPaciente(CodPac,CodAdm);
}

function VerHistorialCompleto(){
	var CodigoPaciente=document.getElementById("CodPac").value;
	window.location.href="hic_pestanas.jsp?CodPac="+CodigoPaciente;
}

function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  }

function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }
</script>
</head>

<body onload="enviar(),hora(),fecha()" id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
</script>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
if(codigou.equals("")){%>

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
				con.cerrar();
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="HistoriaClinica.jsp">Historias Clinicas</a>-Crear Historia Clinica-Crear Formato</b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/>
								<%@include file="menuhist.jsp"%>  
			</td>

			<td width="100%">    
					<br />

  <form id="form1"  name="form1" style="margin-top:-15px;"> 
	  <div  class="style2">
	    <table width="100%">
          <tr>
            <td colspan="4" id="cabecera2"><div align="center" class="style11">DATOS DEL PACIENTE </div></td>
          </tr>
		</table>
		<table width="100%" border="1" class="style6" cellspacing="0" >
          <tr>
            <td width="11%">N&ordm; Documento </td>
            <td width="19%"><input name="txtTipoDoc" type="text" id="txtTipoDoc" size="4" readonly="" />
              
              <input name="txtNumDoc" type="text" id="txtNumDoc" size="12" border="0" readonly="" />            </td>
            <td width="16%">Nombre Completo </td>
            <td width="54%"><input name="txtNomPac" type="text" id="txtNomPac" size="80"  readonly=""/></td>
          </tr>
          <tr>
            <td>Edad</td>
            <td><input name="txtEdad" type="text" id="txtEdad" size="4" readonly="" /></td>
            <td>Sexo</td>
            <td><input name="txtSexo" type="text" id="txtSexo" readonly="" /></td>
          </tr>
        </table>
	    
	    <table width="100%" border="1" class="style6" cellspacing="0" >
          <tr>
            <td colspan="2" id="cabecera2"><div align="center"><span class="style11">Seleccionar Formatos</span></div></td>
          </tr>
          <tr>
            <td width="192">Seleccione Formato </td>
            <td width="776"><label>
              <input  name="txtFormato" type="text" id="txtFormato" size="80" onkeyup='autocompletaFormato()' />
              <input name="btnVerFormato" type="button" class="boton4" id="btnVerFormato" value="Asignar" onclick="AsignarFormato()" />
              <input name="txtCodAdm" type="hidden" id="txtCodAdm" size="4" value="<%=CodAdm%>" />
              <input name="txtCodPac" type="hidden" id="txtCodPac" size="4" value="<%=CodPac%>" />
              <input name="txtCodFormato" type="hidden" id="txtCodFormato" size="4" />
            </label></td>
          </tr>
          <tr>
            <td colspan="2"><label></label>
                <div style="margin-left:150px" id="sugerencias"></div></td>
          </tr>
         
	      <tr>
            <td colspan="2"></td>
          </tr>
        </table>
		<table width="100%" border="1" class="style6" cellspacing="0" id="form3" >
          <tr>
            <td colspan="3" id="cabecera2"><div align="center" class="style11">Formatos Seleccionados</div></td>
          </tr>
          <tr>
            <td width="20%">Nombre del Formato </td>
            <td width="40%"><div align="center">Historial de Laboratorios</div></td>
            <td width="40%"><div align="center">Historial Imagenes Diagnosticas</div></td>
          </tr>
          <tr>
            <td><div id="FormatosPaciente"></div></td>
            <td><div id="LaboratoriosPaciente"></div></td>
            <td><div id="ImagenologiaPaciente"></div></td>
          </tr>
        </table>
		
	<table width="100%" border="1"  class="style6" cellspacing="0" id="form4" name="form4" >
	   <tr>
	     <td colspan="2" id="cabecera2">&nbsp;</td>
      </tr>
	   <tr>
	     <td>Areas Del Formato </td>
	     <td>Preguntas Del Formato </td>
   </tr>
	   <tr>
            <td width="20%"><div id="areas"></div></td>
           <td width="80%"><div id="formulario"></div></td>
      </tr>
	    </table>
	  
        <table width="100%" class="style6" border="1">
                    <tr>
       	               <td width="20%"><input name="btnOrdenServicio" class="boton4" type="button" id="btnOrdenServicio" value="Orden De Servicio"  onclick="GenerarOrden()"/></td>
       	               <td width="19%"><input name="btnFormulacion" class="boton4" type="button" id="btnFormulacion" value="Formulacion" onclick="VerHistorialCompleto()"; /></td>
					   <td width="18%"><input name="btnDiagnostico" class="boton4" type="button" id="btnDiagnostico" value="Diagnostico" /></td>
       	        	   <td width="21%"><input name="btnFinAtencion" class="boton4" type="button" id="btnFinAtencion" value="Finalizar Atencion" /></td>
       	               <td width="22%"><input name="btnCerrar" class="boton4" type="button" id="btnCerrar" value="     Cerrar     " /></td>
		               <input name="CodPac" type="hidden" id="CodPac" value="<%=CodPac %>" />
					   <input name="CodAdm" type="hidden" id="CodAdm" value="<%=CodAdm %>" />
					   <input name="txtHora" type="hidden" id="txtHora" />
					   <input name="txtFecha" type="hidden" id="txtFecha" />
					   <input name="txtCodUsu" type="hidden" ide="txtCodUsu" value="<%=codigou %>"/>
                    </tr>
                  </table>
				 </div>
<%} %>
</form>
</td></tr></table>
</div>
</td></tr></table>
</body>
</html>