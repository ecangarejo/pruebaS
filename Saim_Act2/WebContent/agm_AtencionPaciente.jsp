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
<style type="text/css">
#ContenidoLaboratorio{height:351px; overflow : scroll ;}
#ExamenesActivos{height:325px;  overflow : scroll ;}
#HistorialImagenes{height:381px; overflow : scroll ;}
#examenes{height:290px; overflow : scroll ;}

#dxhis{height:100px; overflow : scroll ;}
#FormatosPaciente{height:100px; overflow : scroll ;}
#areas{height:100px; overflow : scroll ;}
#formulario{height:140px; overflow : scroll ;}

#sugerenciasFormato li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerenciasFormato {height:75px; border:1px solid black; display:none; margin-left: 150px; overflow : scroll ;}
#sugerenciasFormato ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerenciasFormato ul li {padding: .2em; border-bottom: 1px solid silver;}

#SugerenciaMed li:hover{Cursor : pointer;background-color: #cccccc; }
#SugerenciaMed {height:75px; border:1px solid black; display:none; overflow : scroll ;}
#SugerenciaMed ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#SugerenciaMed ul li {padding: .2em; border-bottom: 1px solid silver;}

#SugerenciaCx li:hover{Cursor : pointer;background-color: #cccccc; }
#SugerenciaCx {height:75px; border:1px solid black; display:none; overflow : scroll ;}
#SugerenciaCx ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#SugerenciaCx ul li {padding: .2em; border-bottom: 1px solid silver;}

#SugeDiagnostico {width:500px; border:1px solid black; display:none;}
#SugeDiagnostico {height : 90px ; overflow : scroll ;}

#SugeDiagnosticoEgreso {width:500px; border:1px solid black; display:none;}
#SugeDiagnosticoEgreso {height : 90px ; overflow : scroll ;}

#SugeDiagnosticoRela2 {width:500px; border:1px solid black; display:none;}
#SugeDiagnosticoRela2 {height : 90px ; overflow : scroll ;}

#SugeDiagnosticoRela1 {width:500px; border:1px solid black; display:none;}
#SugeDiagnosticoRela1 {height : 90px ; overflow : scroll ;}

#MenuVertical{
position: absolute;
    left: 1%;
    top: 16%;
}

</style>
 <style type="text/css">
<!--
/* CSS Tabs */
#button {
        width: 12em;
        border-right: 1px solid #000;
        padding: 0 0 1em 0;
        margin-bottom: 1em;
        font-family: Tahoma, Arial, sans-serif;
                /*'Trebuchet MS', 'Lucida Grande', Verdana, Arial, sans-serif;*/
        font-size : 11px;
        background-color: #90bade;
        color: #333;
        }

        #button ul {
                list-style: none;
                margin: 0;
                padding: 0;
                border: none;
                }

        #button li {
                border-bottom: 1px solid #90bade;
                margin: 0;
                list-style: none;
                list-style-image: none;
                }

        #button li a {
                display: block;
                padding: 5px 5px 5px 0.5em;
                border-left: 10px solid #1958b7;
                border-right: 10px solid #508fc4;
                background-color: #2175bc;
                color: #fff;
                text-decoration: none;
                width: 100%;
                }

        html>body #button li a {
                width: auto;
                }

        #button li a:hover {
                border-left: 10px solid #1c64d1;
                border-right: 10px solid #5ba3e0;
                background-color: #2586d7;
                color: #fff;
                }

        #button li #active {
                border-left: 10px solid #1c64d1;
                border-right: 10px solid #5ba3e0;
                background-color: #2586d7;
                color: #fff;
                }
-->
</style>
<script languaje="javascript" src="img_autocompletarestudio.js"></script>
<!-- <script language="javascript" type="text/javascript"  charset="UTF-8"  src="aiepi_Cex.js" ></script> -->
<script languaje="javascript" src="hic_SeleccionarPacientes.js"></script>
<script languaje="javascript" src="hic_autocompletarProcedimiento.js">      </script>
<script languaje="javascript" src="AutocompletarCIE10.js">      </script>
<script languaje="javascript" src="AutocompletarCIE10Egreso.js" ></script>
<script languaje="javascript" src="AutocompletarCIE10Relacion1.js"></script>
<script languaje="javascript" src="AutocompletarCIE10Relacion2.js"></script>
<script languaje="javascript" src="hic_Movimientos.js">      </script>
<script languaje="javascript" src="hic_autocompletarProcedimiento.js">      </script>
<script src="hic_autocompletarProgramayServicioh.js" type="text/javascript"></script>
<script src="hic_autocompletarRefh.js" type="text/javascript"></script>
<script language='JavaScript1.2'>
function disabletext(e){
return false
}
function reEnable(){
return true
}
//if the browser is IE4+
document.onselectstart=new Function ("return false")
//if the browser is NS6
if (window.sidebar){
document.onmousedown=disabletext
document.onclick=reEnable
}
</script>

<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}

String CodHorarioMedico=request.getParameter("CodHorarioMedico");
if(CodHorarioMedico==null){
	CodHorarioMedico="";
}

String CodPac=request.getParameter("CodPac");
if(CodPac==null){
	CodPac="";
}
String CodAdm=request.getParameter("CodAdm");
if(CodAdm==null){
	CodAdm="";
}

String DocPaciente=request.getParameter("DocPaciente");
if(DocPaciente==null){
	DocPaciente="";
}

%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8 " />
<title>Atencion de Pacientes</title>

<style type="text/css">
#ResultadosBusqueda{height:336px;  overflow : scroll ; color: #215b8b;}
#MedCex{height:200px; overflow : scroll ;}
#QxsCex{height:200px; overflow : scroll ;}
#AleCex{height:200px; overflow : scroll ;}
#CatCex{height:200px; overflow : scroll ;}

#ForCex{height:220px; overflow : scroll ;}
#DxsCex{height:220px; overflow : scroll ;}
</style>
<script src="agm_AdmitirCita.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript">

</script>
</head>

<body onpaste="return false;"  onload="CargarDatosPaciente(),fecha(),hora()">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="ConsultaExterna.jsp">Consulta Externa </a>-Atencion-Atender Paciente </b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">  
<form id="form1" name="form1">
<div id="FormularioCE"></div>

<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
<input name="CodHorarioMedico" type="hidden" id="CodHorarioMedico" value="<%=CodHorarioMedico %>" />
<input name="CodPac" type="hidden" id="CodPac" value="<%=CodPac %>" />
<input name="CodAdm" type="hidden" id="CodAdm" value="<%=CodAdm %>" />
<input name="CedPac" type="hidden" id="CedPac" value="<%=DocPaciente %>" />
<input name='txtHora' type='hidden' id='txtHora'/>
<input name='txtFecha' type='hidden' id='txtFecha'/>
<input name='CodAdm' type='hidden' id='CodAdm'/>

</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
</body>
</html>
