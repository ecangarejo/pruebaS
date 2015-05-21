
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<head>

<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"> 
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>

<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {Cursor : pointer; width:500px; overflow : scroll ;  height : 100px ; border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias1 li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias1 {Cursor : pointer; width:500px; overflow : scroll ;  height : 100px ; border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}


.seleccionado {font-weight:bold; background-color: #cccccc;j=1}

#ContenidoLaboratorio {height : 330px ; overflow : scroll ;}
#ExamenesActivos {height : 305px ; overflow : scroll ;}
#HistorialLaboratorios {height : 305px ; overflow : scroll ;}
#tabcontent2{height : 365px ; overflow : scroll ;}
#tabcontent3{height : 365px ; overflow : scroll ;}
#tabcontent4{height : 365px ; overflow : scroll ;}
#tabcontent5{height : 365px ; overflow : scroll ;}
#tabcontent6{height : 365px ; overflow : scroll ;}
#tabcontent7{height : 365px ; overflow : scroll ;}
#FormatosPaciente {height : 100px ;overflow : scroll ;}
#DiagnosActivo {height : 100px ;overflow : scroll ;}
#areas {height : 127px ; overflow : scroll ;}
#formulario {height : 127px ; overflow : scroll ;}
#formularioAnteriores {height : 100px ;  overflow : scroll ;}

</style>
<%
String CodPac=request.getParameter("CodPac");
if(CodPac==null){
	CodPac="";
}

String CodAdm=request.getParameter("CodAdm");
if(CodAdm==null){
	CodAdm="";
}

String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Historias Clinicas Completas</title>
<script src="hic_pestanas.js" type="text/javascript"></script>
<script language=javascript src="autocompletar.js"></script>
<script language="javascript" src="img_autocompletarestudio.js"></script>
<script src="hic_autocompletarFormato.js" type="text/javascript"></script>
<script type="text/javascript">
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
</script>

<%
Conexion con=new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	   String datos="";
	   String edad="";
	   String Documentos="";
	   String FechaNaci="";
	   String tipodoc="";
	   String numeroDoc="";
	   String servicio="";
	   String locacion="";
	   String numcama="";
	   String TipoNumDoc="";
	   String ubicacion="";
	   String entidad="";
	      	
	       st2 = con.conn.createStatement();
	       	
	       	rs2=st2.executeQuery("select pac.nombre,pac.primer_apellido,pac.segundo_apellido,(year(curdate())-year(pac.fecha_nacimiento))-(right(curdate(),5)<right(pac.fecha_nacimiento, 5)) as edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,acc.pabellon,acc.ubicacion_cama,acc.codigocama,aen.nombre_entidad from adm_paciente pac,adm_admisiones adm,adm_censo_cama acc,adm_convenio acv,adm_entidad aen where pac.pac_codigo_paciente='"+CodPac+"' and adm.adm_numero_ingreso='"+CodAdm+"' and adm.cen_numero_cama_fk=acc.cen_numero_cama and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=pac.conv_numero_contrato_fk");%>
				<%while(rs2.next()){
					
		        datos=rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3);
		        edad=rs2.getString(4);
		        FechaNaci=rs2.getString(5);
		        tipodoc=rs2.getString(6);
		        numeroDoc=rs2.getString(7);
		        TipoNumDoc=tipodoc+" "+numeroDoc;
		        servicio=rs2.getString(8);
		        locacion=rs2.getString(9);
		        numcama=rs2.getString(10);
		        entidad=rs2.getString(11);
		        ubicacion=locacion+" Cama "+numcama;
				}
				rs2.getStatement().close();
			    
				ResultSet rs3;
				Statement st3 = null;
				   String idu="";         
				       	st3 = con.conn.createStatement();
				       	rs3=st3.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
							<%while(rs3.next()){
					        idu=rs3.getString(1);
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
				con.cerrar();
	       	%>
</head>
<body onload="fecha(),hora();cargar()" id="back">
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
     		<td width="100%">    
					<br />
<form name="form1" id="form1" style="margin-top:-17px;" >

<table width='100%'>
<tr id="cabecera2" class="style11" align="center"><td colspan="6">HISTORIA CLINICA</td></tr>
</table>
<table width='100%' class="style6" border='1' cellspacing="0">
<tr><td width="25%">Historia Clinica Completa De:</td>

  <td width="35%"><div class="style9"><%=datos %></div></td>
  <td width="6%">Edad:</td>
  <td width="5%"><div class="style9"> <%=edad %></div></td>
  <td width="16%">Fecha Nacimiento </td>
  <td width="13%"><div class="style9"><%=FechaNaci %></div></td>
</tr></table>
<table width='100%' border='1' class="style6"><tr>
  <td width="19%">Numero Documento: </td>
    <td width="20%"><div class="style9"><%=TipoNumDoc %></div></td>
    <td width="7%">Entidad:</td>
    <td width="54%"><div class="style9"><%=entidad %></div></td>
</tr></table>
<table width='100%' border='1' class="style6"><tr>
  <td width="11%">Servicio:</td>
    <td width="36%"><div class="style9"><%=servicio %></div></td>
    <td width="12%">Ubicacion</td>
    <td width="41%"><div class="style9"><%=ubicacion %></div></td>
</tr></table>

<!--Start of the Tabmenu 1 -->
<div id="tabsJ">
<ul>
<li><a href="#" onclick="easytabs('1', '1');" id="tablink1"><span>Laboratorios</span></a></li>
<li><a href="#" onclick="easytabs('1', '2');" id="tablink2"><span>Imagenes</span></a></li>
<li><a href="#" onclick="easytabs('1', '3');" id="tablink3"><span>Orden De Servicio</span></a></li>
<li><a href="#" onclick="easytabs('1', '4');" id="tablink4"><span>Formatos</span></a></li>
<li><a href="#" onclick="easytabs('1', '5');" id="tablink5"><span>Antecedentes</span></a></li>
<li><a href="#" onclick="easytabs('1', '6');" id="tablink6"><span>En Estudio</span></a></li>
<li><a href="#" onclick="easytabs('1', '7');" id="tablink7"><span>En Estudio</span></a></li>
</ul>
</div>
<div id="contenido"> 
	<!--Contenido--> 
	<!--Start Tabcontent 1 -->

<div id="tabcontent1"></div>
<!--End Tabcontent 1-->

<!--Start Tabcontent 2-->
<div id="tabcontent2"></div>
<!--End Tabcontent 2 -->

<!--Start Tabcontent 3-->
<div id="tabcontent3"></div>
<!--End Tabcontent 3-->

<!--Start Tabcontent 4-->
<div id="tabcontent4"></div>
<!--End Tabcontent 4-->

<!--Start Tabcontent 5-->
<div id="tabcontent5"></div>
<!--End Tabcontent 5-->

<!--Start Tabcontent 6-->
<div id="tabcontent6"></div>
<!--End Tabcontent 6-->

<!--Start Tabcontent 7-->
<div id="tabcontent7"></div>
<!--End Tabcontent 7-->

</div>
<!--End of the Tabmenu 1 -->
<input name="CodPac" type="hidden" id="CodPac" value="<%=CodPac%>" />
<input name="CodAdm" type="hidden" id="CodAdm" value="<%=CodAdm%>" />
<input name="txtFecha" type="hidden" id="txtFecha" />
<input name="txtHora" type="hidden" id="txtHora" />
<input name="txtCodusuario" type="text" id="txtCodusuario" style="display:none"value="<%=codigou %>"/>
</form>
</td></tr></table>
</div>
</td></tr></table>
</body>
</html>
