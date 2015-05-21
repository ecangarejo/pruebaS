
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language=javascript src="Validaciones.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script languaje="javascript" src="Adm_Ping.js"></script>
<title> Consulta Casos Atendidos </title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language="javascript" type="text/javascript" src="cons_consultas.js"></script>	
<script language="javascript" type="text/javascript" src="con_AutoDiag.js"></script>	
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:342px; cursor:pointer; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}

</style>


<style type="text/css">
#autoc  li:hover{Cursor : pointer; background-color: #cccccc; }
#autoc { border:1px solid black; margin-left:0.8px;}
#autoc ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#autoc ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias0  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias0 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias0 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias0 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias1  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias1 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias2  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias2 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias2 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias2 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias3  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias3 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias3 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias3 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias4  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias4 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias4 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias4 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias5  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias5 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias5 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias5 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias6  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias6 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias6 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias6 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias7  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias7 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias7 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias7 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias8  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias8 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias8 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias8 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias9  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias9 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias9 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias9 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias10  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias10 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias10 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias10 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias11  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias11 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias11 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias11 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias12  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias12 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias12 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias12 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias13  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias13 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias13 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias13 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias14  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias14 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias14 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias14 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias15  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias15 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias15 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias15 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias16  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias16 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias16 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias16 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias17  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias17 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias17 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias17 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias18  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias18 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias18 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias18 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias19  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias19 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias19 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias19 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias20  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias20 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias20 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias20 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias21  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias21 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias21 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias21 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias22  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias22 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias22 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias22 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias23  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias23 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias23 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias23 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias24  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias24 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias24 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias24 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias25  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias25 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias25 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias25 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias26  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias26 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias26 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias26 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias27  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias27 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias27 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias27 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias28  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias28 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias28 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias28 ul li {padding: .2em; border-bottom: 1px solid silver;}

#sugerencias29  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias29 { border:1px solid black; margin-left:0.8px; display:none;}
#sugerencias29 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias29 ul li {padding: .2em; border-bottom: 1px solid silver;}




.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrollbox {height : 90px ; altura: 90px; desbordamiento: auto; overflow:scroll}

</style>



<script type="text/javascript">
	
	</script>

</head>
<body onLoad="" id="back"> 
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
<%String codigou =(String)session.getAttribute("codusuario");
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
				
	       	%>

<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Calidad.jsp">Calidad</a>-Reporte de Indicadores</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>
 
	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
				<br />
					<div style="margin-top:-16px;" id="cabecera2" class="style11" align="center">MODULO DE CONSULTAS</div>
						<br/><br/>
								<%@include file="CopyConsulta.jsp"%>     
			</td>

			<td width="100%">   
					<br />

				
					<table width="100%" border="0" cellspacing="0" class="style6">
  						<tr>
   							 <td colspan="4" id="cabecera2" class="style11" ><div align="center">Casos Atendidos</div></td>
   						 </tr>
 						<tr>
						<td>
						<table width="100%">
							<tr >
								<td width="20%">
									<br>
									FECHA INICIAL
																		
									<%
										int dia=0;
										out.println("<select name=Idia id=Idia>");
										out.println("<option value=40>---</option>");
										for(int i=0;i<=30;i++){
										dia=dia+1;
										out.println("<option value="+dia+">"+dia+"</option>");
										}
										
										
										
										out.println("</select>");
										out.println("<select name=Imes id=Imes>");
										out.println("<option value=40>---</option>");
										out.println("<option value=1>Enero</option>");
										out.println("<option value=2>Febrero</option>");
										out.println("<option value=3>Marzo</option>");
										out.println("<option value=4>Abril</option>");
										out.println("<option value=5>Mayo</option>");
										out.println("<option value=6>Junio</option>");
										out.println("<option value=7>Julio</option>");
										out.println("<option value=8>Agosto</option>");
										out.println("<option value=9>Septiembre</option>");
										out.println("<option value=10>Octubre</option>");
										out.println("<option value=11>Noviembre</option>");
										out.println("<option value=12>Diciembre</option>");	
										out.println("</select>");
										
										out.println("<select name=Iano id=Iano>");
										out.println("<option value=40>---</option>");
										int ano=2008;
										for(int k=0;k<=100;k++){
											ano=ano+1;
											out.println("<option value="+ano+">"+ano+"</option>");
										}
										out.println("</select>");%>
								</td>
								<td width="20%">
								FECHA FINAL
										<%
										
										int fdia=0;
										out.println("<select name=Fdia id=Fdia>");
										out.println("<option value=40>---</option>");
										for(int i=0;i<=30;i++){
										fdia=fdia+1;
										out.println("<option value="+fdia+">"+fdia+"</option>");
										}
										
										out.println("</select>");
										out.println("<select name=Fmes id=Fmes>");
										out.println("<option value=40>---</option>");
										out.println("<option value=1>Enero</option>");
										out.println("<option value=2>Febrero</option>");
										out.println("<option value=3>Marzo</option>");
										out.println("<option value=4>Abril</option>");
										out.println("<option value=5>Mayo</option>");
										out.println("<option value=6>Junio</option>");
										out.println("<option value=7>Julio</option>");
										out.println("<option value=8>Agosto</option>");
										out.println("<option value=9>Septiembre</option>");
										out.println("<option value=10>Octubre</option>");
										out.println("<option value=11>Noviembre</option>");
										out.println("<option value=12>Diciembre</option>");	
										out.println("</select>");
										
										
										out.println("<select name=Fano id=Fano>");
										out.println("<option value=40>---</option>");
										int fano=2008;
										for(int k=0;k<=100;k++){
											fano=fano+1;
											out.println("<option value="+fano+">"+fano+"</option>");
										}
										out.println("</select>");%>
								</td>
							<tr>
								<td width="50%">
								<br>
								Entidad	:
								<% out.println("<select name=Ent id=Ent style='width:50%'>");
									out.println("<option value='todas'>TODAS</option>"); 
									ResultSet rs5;
									Statement st5=null;
									st5=con.conn.createStatement();
									rs5=st5.executeQuery("SELECT nombre_entidad,ent_nit FROM adm_entidad ORDER BY nombre_entidad");
									while (rs5.next()){
										out.println("<option value="+rs5.getString(2)+">"+rs5.getString(1)+"</option>");
									}
									out.println("</select>");
									rs5.getStatement().close();
								%>
								</td>
								<td>
									Tipos de Casos :

									<%
									out.println("<select id=tcaso >");
									st5=con.conn.createStatement();
									rs5=st5.executeQuery("SELECT * FROM cons_tipocasos  ORDER BY descripcion");
									while (rs5.next()){
										out.println("<option value="+rs5.getString(1)+" >"+rs5.getString(2)+" ("+rs5.getString(3)+")</option>");
									}
									out.println("</select>");
																
									rs5.getStatement().close();
									con.cerrar();
									
									// out.println("<input type='text' id='txtNomDiagnos' onkeyup='autocompletadiag()' />"); %>
								</td>
							</tr>
							<tr>
							<td>
							<% //out.println("<label><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label>"); %>
							</td>
							<td>
								<div id='SugeDiagnostico'></div>
							</td>
							</tr>
								<tr>
								<td>
								<br><br>
								<input type=button value="Consultar" onclick='Casos()' />
								</td>
								</tr>			
					
						</table>
						
						
</div>
</td></tr></table>
<%}%>
</body>
</html>