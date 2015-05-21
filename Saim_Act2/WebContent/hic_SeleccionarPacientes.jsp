<?xml version="1.0" encoding="utf-8" ?>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8"/> 

<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<%
String pacientes=request.getParameter("pacientes");
if(pacientes==null){
	pacientes="";
}
%>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>

<title>Historia Clinica</title>
<style type="text/css">
#ContenidoLaboratorio{height:351px; overflow : scroll ;}
#CambioMedicamentos{height:351px; overflow : scroll ;}
#ExamenesActivos{height:325px;  overflow : scroll ;}
#HistorialImagenes{height:381px; overflow : scroll ;}

#FormatosPaciente{height:100px; overflow : scroll ;}
#areas{height:100px; overflow : scroll ;}
#formulario{height:240px; overflow : scroll ;}

#sugerenciasFormato li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerenciasFormato {height:75px; border:1px solid black; display:none; margin-left: 150px; overflow : scroll ;}
#sugerenciasFormato ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerenciasFormato ul li {padding: .2em; border-bottom: 1px solid silver;}

#SugerenciaMed li:hover{Cursor : pointer;background-color: #cccccc; }
#SugerenciaMed {height:75px; border:1px solid black; display:none; overflow : scroll ;}
#SugerenciaMed ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#SugerenciaMed ul li {padding: .2em; border-bottom: 1px solid silver;}

#SugerenciaMedFormula li:hover{Cursor : pointer;background-color: #cccccc; }
#SugerenciaMedFormula {height:75px; border:1px solid black; display:none; overflow : scroll ;}
#SugerenciaMedFormula ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#SugerenciaMedFormula ul li {padding: .2em; border-bottom: 1px solid silver;}

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
    top: 25%;
}
</style>


<style type='text/css'>
<!--
/*- Menu Tabs J--------------------------- */

    #tabsJ {
      float:left;
      width:100%;     
      font-size:90%;
      line-height:normal;
          border-bottom:2px solid #1a5581;
      }
    #tabsJ ul {
        margin:0;
        padding:10px 10px 0 50px;
        list-style:none;
      }
    #tabsJ li {
      display:inline;
      margin:0;
      padding:0;
      }
    #tabsJ a {
      float:left;
      background:url('Imagenes/tableftJ.gif') no-repeat left top;
      margin:0;
      padding:0 0 0 5px;
      text-decoration:none;
      }
    #tabsJ a span {
      float:left;
      display:block;
      background:url('Imagenes/tabrightJ.gif') no-repeat right top;
      padding:5px 15px 4px 6px;
      color:#24618E;
      }
    /**Commented Backslash Hack hides rule from IE5-Mac \*/
    #tabsJ a span {float:none;}
    /* End IE5-Mac hack */
    #tabsJ a:hover span {
      color:#FFF;
      }
    #tabsJ a:hover {
      background-position:0% -42px;
      }
    #tabsJ a:hover span {
      background-position:100% -42px;
      }      
    #tabsJ a.tabactive span {
      color:#FFF;
      }
    #tabsJ a.tabactive {
      background-position:0% -42px;
      }
    #tabsJ a.tabactive span {
      background-position:100% -42px;
      }
 	#tabsJ #current a {
      background-position:0% -42px;
      }
    #tabsJ #current a span {
      background-position:100% -42px;
      color:#FFF;
      }
-->
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
<script language="javascript" src="AutocompletarCIE10Egreso.js"></script>
<script language="javascript" src="AutocompletarCIE10Relacion1.js"></script>
<script language="javascript" src="AutocompletarCIE10Relacion2.js"></script>

<script language="javascript" src="img_autocompletarestudio.js"></script>
<!-- <script language="javascript" type="text/javascript"  charset="UTF-8"  src="aiepi_SeleccionarPacientes.js" ></script> -->
<script language="javascript" type="text/javascript"  charset="UTF-8"  src="hic_SeleccionarPacientes.js" ></script>
<script language="javascript" src="hic_autocompletarProcedimiento.js">      </script>
<script language="javascript" src="hic_Movimientos.js">      </script>
<script language="javascript" src="hic_NuevaFormulacionKardex.js">      </script>
<script language="javascript" src="AutocompletarCIE10.js">      </script>

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

</head>

<body onpaste="return false;" onload="cargar(),fecha(),hora()" >
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="HistoriaClinica.jsp">Historias Clinicas</a>-<a href="hic_BuscarPacientes.jsp">Ver Formatos</a>-Seleccionar Historia paciente</b></div>
</td> 
</tr>

<tr  id="principal1">  
<td>

<div>

	<table width="100%"> 
		<tr>

			<td width="100%">     
<form id="form1" name="form1" >
<div id="FormatoMultiple"></div>
<input name="CodPac" type="hidden" id="CodPac" />
<!--  <input name="CedPac" type="hidden" id="CedPac" /> -->
<input name='txtHora' type='hidden' id='txtHora'/>
<input name='txtFecha' type='hidden' id='txtFecha'/>
<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
<input name="pacientes" type="hidden" id="pacientes" value="<%=pacientes %>" />
</form>
</td></tr></table>
</div>
</td></tr></table>
<%} %>
</body>
</html>
