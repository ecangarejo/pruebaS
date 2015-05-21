
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
<%String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<title>Lista De Pacientes</title>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 200px ; altura: 90px;  overflow : scroll ; desbordamiento: auto;}
</style>
<script language="javascript" src="ord_PacLabo.js"></script>
<script language="javascript" src=".js"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">
function CodPaciente(){

var CodPac=<%=CodPac%>;
AsignarOrdenLab(CodPac);
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
<body >
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} */

</script> 
<%
//if(codigou.equals("")){%>

<%//}else{
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Examen Eco-Cardio</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO IMAGENOLOGIA</div>
					<br/><br/>  
								<%@include file="copymenyhi.jsp"%>
			</td>
    
			<td width="100%">       
					<br />
	
<form name="form1" id="form1" style="margin-top:-17px;" >
<table width="100%" >
  <tr>
    <td colspan="4" id="cabecera2" class="style11" align="center">ECOCARDIOGRAMA TRANS TORACICO MODO M-BIDEMENSIONAL DOPPLER COLOR </td>
  </tr>
</table>

<table width="100%" class="style6">

  <tr>
    <td width="10%">NOMBRE</td>
    <td width="37%">&nbsp;</td>
    <td width="14%">INDICACION</td>
    <td width="39%">&nbsp;</td>
  </tr>
  <tr>
    <td>EDAD</td>
    <td>&nbsp;</td>
    <td>ENTIDAD</td>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="100%" class="style6" border="1">
  <tr>
    <td colspan="3" id="cabecera2" class="style11" align="center">VENTRICULO IZQUIERDO&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VALORES NORMALES </td>
    </tr>
  <tr>
    <td width="37%">Diametro Tele Diast&oacute;lico</td>
    <td width="33%"><input name="txtDtdIzq" type="text" id="txtDtdIzq" size="10" />
      mm</td>
    <td width="30%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;55mm</td>
  </tr>
  <tr>
    <td>Diametro Tele Sist&oacute;lico </td>
    <td><input name="txtDts" type="text" id="txtDts" size="10" />
      mm</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <td>Septum</td>
    <td><input name="txtSeptum" type="text" id="txtSeptum" size="10" />
      mm</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;11mm</td>
  </tr>
  <tr>
    <td>Pared Posterior </td>
    <td><input name="txtParPost" type="text" id="txtParPost" size="10" />
      mm</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;10mm</td>
  </tr>
  <tr>
    <td>Ra&iacute;z A&oacute;rtica </td>
    <td><input name="txtRaiAor" type="text" id="txtRaiAor" size="10" />
      mm</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;35mm</td>
  </tr>
  <tr>
    <td>Aur&iacute;cula Izquierda </td>
    <td><input name="txtAurIzq" type="text" id="txtAurIzq" size="10" />
      mm</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;40mm</td>
  </tr>
  <tr>
    <td>Fracci&oacute;n de Eyecci&oacute;n </td>
    <td><input name="txtFraEye" type="text" id="txtFraEye" size="10" />
      %</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&gt;55%</td>
  </tr>
  <tr>
    <td colspan="3" id="cabecera2" class="style11" align="center">VENTRICULO DERECHO </td>
    </tr>
  <tr>
    <td>Diametro Tele Diast&oacute;lico</td>
    <td><input name="txtDtdDer" type="text" id="txtDtdDer" size="10" />
      mm</td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;25mm</td>
  </tr>
</table>
<table width="100%" border="1" class="style6">
  <tr>
    <td colspan="2" id="cabecera2" class="style11" align="center">FUNCION VALVULAR </td>
    </tr>
  <tr>
    <td width="50%">V&aacute;lvula A&oacute;rtica </td>
    <td width="50%">V&aacute;lvula Mitral </td>
  </tr>
  <tr>
    <td><table width="100%" border="1" class="style6">
      <tr>
        <td width="53%">Velocidad Maxima </td>
        <td width="47%"><input name="txtVelMaxAortica" type="text" id="txtVelMaxAortica" size="10" />
          m/s</td>
      </tr>
      <tr>
        <td>Gradiente Maximo </td>
        <td><input name="txtGraMaxAortica" type="text" id="txtGraMaxAortica" size="10" />
          mmHg</td>
      </tr>
      <tr>
        <td>Gradiente Medio </td>
        <td><input name="txtGraMedAortica" type="text" id="txtGraMedAortica" size="10" />
          mmHg</td>
      </tr>
      <tr>
        <td>Grado de Regurgitacion </td>
        <td><input name="txtGraRegurAortica" type="text" id="txtGraRegurAortica" size="10" /></td>
      </tr>
    </table></td>
    <td><table width="100%" border="1" class="style6">
      <tr>
        <td width="51%">Gradiente Maximo </td>
        <td width="49%"><input name="txtGraMaxMitral" type="text" id="txtGraMaxMitral" size="10" />
          mmHg</td>
      </tr>
      <tr>
        <td>Gradiente Medio </td>
        <td><input name="txtGraMedMitral" type="text" id="txtGraMedMitral" size="10" />
          mmHg</td>
      </tr>
      <tr>
        <td>Grado de Regurgitacion </td>
        <td><input name="txtGraRegurMitral" type="text" id="txtGraRegurMitral" size="10" /></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>

    </table></td>
  </tr>
  <tr>
    <td>V&aacute;lvula Tric&uacute;pide </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width="100%" class="style6" border="1">
      <tr>
        <td width="56%">Grado de Regurgitacion </td>
        <td width="44%"><input name="txtGraRegurTricu" type="text" id="txtGraRegurTricu" size="10" /></td>
      </tr>
      <tr>
        <td>Presion Sistolica Pulmonar </td>
        <td><input name="txtPreSisPulm" type="text" id="txtPreSisPulm" size="10" />
          mmHg</td>
      </tr>
    </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" id="cabecera2" class="style11" align="center">CONCLUSIONES</td>
    </tr>
  <tr>
    <td colspan="2"><textarea name="txtConclusiones" cols="84" rows="10" id="txtConclusiones">Ventriculo izquierdo no dilatado, con hipertrofia septal leve.
Contractalidad global y segmentaria del ventriculo izquierdo conservado.
FEVI %
Raiz aortica No dilatada.
Auricula izquierda no dilatada.
Valvula mitral morfologica y funcionalmente normal.
Valvula aortica morfologica y funcionalmente normal.
Ventriculo derecho no dilatado, de contractilidad conservada.
No se Observa Insuficiencia tricuspidea.
vena cava no dilatada.</textarea></td>
    </tr>
</table>

<input name='txtCodPac' id='txtCodPac' type='hidden' value="<%=CodPac%>">
<input name='txtHora' id='txtHora' type='hidden' >
<input name='txtFecha' id='txtFecha' type='hidden'>

<div id='formulario'></div>
<div id='examenes'></div>
</form>
</td></tr></table>
</div>
</td></tr></table>

<%//} %>
</body>
</html>