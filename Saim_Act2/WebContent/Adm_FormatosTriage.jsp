<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"/>
<title>Formatos Triage</title>
<%
String CodAdmCola=request.getParameter("CodAdmCola");
if(CodAdmCola==null){
	CodAdmCola="";
}

String CedPac=request.getParameter("CedPac");
if(CedPac==null){
	CedPac="";
}
/*
String CodDiagnostico=request.getParameter("CodDiagnostico");
if(CodDiagnostico==null){
	CodDiagnostico="";
}*/

//System.out.println("CodAdmCola "+CodAdmCola+" CedPac "+CedPac);
String codigou =(String)session.getAttribute("codusuario");

%>
<%String CodDiagnostico=(String)session.getAttribute("CodDiagnostico");
if(CodDiagnostico==null){
	CodDiagnostico="";
}
%>

<style type='text/css'>

#FormatosPaciente {height : 100px ;overflow : scroll ; desbordamiento: auto;}
#areas {height : 100px ; overflow : scroll ; desbordamiento: auto;}
#formulario {height : 130px ; overflow : scroll ; desbordamiento: auto;}
#formularioAnteriores {height : 100px ;  overflow : scroll ; desbordamiento: auto;}
#sugerencias {width:470px; border:1px solid black; display:none; margin-left: 215px;}
#sugerencias {height : 90px ;overflow : scroll ; desbordamiento: auto;}
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
<script src="AutocompletarCIE10.js" type="text/javascript"></script>
<script src="Adm_ListaMedico.js" type="text/javascript"></script>
<script languaje="javascript" src="hic_autocompletarProcedimiento.js"></script>
<script languaje="javascript" src="AutocompletarCIE10.js">      </script>

<script language='javascript'>
function fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
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
function cargar(){
	//cargarFormatosTriage(CodAdmCola,CedPac); 
}
</script>
</head>
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
				
				con.conn.close();
	       	%>
<body onLoad="cargarFormatosTriage(),fecha(),hora()">
<table width="100%">
<tr align="right">
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Consulta-Lista Medico</b></div>
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%">   
					<br />
<form name="form1" id="form1">
<div id="document"></div>

<input name='txtFecha' type='hidden' id='txtFecha' />
<input name='txtHora' type='hidden' id='txtHora' />
<input name='txtCodAdmCola' type='hidden' id='txtCodAdmCola' value='<%=CodAdmCola %>' />
<input name='txtCedPac' type='hidden' id='txtCedPac' value='<%=CedPac %>' />
<input name='txtCodCola' type='hidden' id='txtCodCola' value='<%=CodAdmCola %>' />
<input name='txtUsuario' type='hidden' id='txtUsuario' value='<%=codigou %>' />
</form></td></tr></table>
</div>
</td></tr></table>
</body>
</html>