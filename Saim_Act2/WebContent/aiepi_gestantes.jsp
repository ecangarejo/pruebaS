<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" import="java.util.*" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css"></link>
		<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico"></link>
		<link rel="STYLESHEET" type="text/css" href="estilos/EstiloMenu.css"></link>
		<script language=javascript src="aiepi_configuracionGestantes.js"></script>
		<script language=javascript src="js/jquery.js"></script>
		<script language=javascript src="jquery/jquery_ui.js"></script>
		<script src=”./include/jquery.maskedinput-1.2.2.js” type=”text/javascript”></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/redmond/jquery-ui.css" />
		
		<!-- obtener el codigo del usuario logeado -->
		<%
			String codigou =(String)session.getAttribute("codusuario");
			if(codigou==null){
				codigou="";
			}
		%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<title>Estrategia AIEPI: Mujeres Gestantes</title>
		<script language=javascript src="aiepi_configuracion.js"></script>
		<script>
			function pulsar(e){
		        tecla = (document.all) ? e.keyCode : e.which;
		        return (tecla != 13);
		    }
		</script>
		<script>
			if (history.forward(1)){
				location.replace(history.forward(1));
			}
		</script>
		<script>
			$(document).ready(function(){
			    $(document).bind("contextmenu",function(e){
			        return false;
			    });
			});
		</script>
	</head>
		<body onload="fcha()"scroll="no">
			<% if(codigou.equals("")){%>
				<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");window.location.href="Seg_login.jsp";</script>
			<%}else{ %>
			<%
				Conexion con=new Conexion();
				ResultSet rs2;
				Statement st2 = null;
	   			String idu="";         
	       		st2 = con.conn.createStatement(); 	
	       		rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
				<%
					while(rs2.next()){
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
					con.cerrar();
	       		%>
				<table width="100%">
					<tr>
						<td>
	  						<table width="100%">
								<tr>
									<td>
										<div><a href="mensajes.jsp?TB_iframe=true&height=520&width=700"  class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin %></b></font></a> <%if (madmin>0){ %><bgsound src="Imagenes/INNERMK.WAV" loop="2"></bgsound><img src="Imagenes/sobr0001.gif"/><%}%></div>
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
							<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="ConsultaExterna.jsp">Consulta Externa</a>-AIEPI-Encuesta Gestantes </b></div>
						</td>						
					</tr>
					<tr  id="principal1">  
						<td>
							<div>
								<p></p>
								<center><div id="cabecera2" align="center"class="style11">HISTORIA CLINICA DE DETECCION DE LAS ALTERACIONES DE LA GESTANTE</div></center>
								<p></p>
								<table width="100%">
									<tr>
										<td width="100%">  
											<form id="form1" name="form1" onkeypress = "return pulsar(event);"><!-- return pulsar(event); -->
												<table width="100%" border="0">
  													<tr>
    													<td align="right">Tipo Documento: </td>
    														<td width="15%"align="right">
																<select name="cmbTipoDoc" id="cmbTipoDoc">
	      															<option value="Seleccione" selected="selected">Seleccione</option>
	     						         							<%
	 																	ResultSet rs3=null;
	 																	Statement st3 = null;  
	 																	try{
	 																		Conexion con2=new Conexion();
	 																		st3 = con2.conn.createStatement();
	 																		rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");%>
	 																		<%while(rs3.next()){%>
																				<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)%></option>
	 																		<%}
	 																		rs3.getStatement().close();
	 																		con2.cerrar();
																		}catch(SQLException e){
																			out.println("no se pudo realizar la conexion!<br/>"); 
																		}%>
																</select>    
															</td>
    														<td width="19%" align="right">N&uacute;mero Documento: </td>
    														<td width="23%" align="center">
																<input name="txtNumDocumento" type="text" id="txtNumDocumento" onkeyup="soloNum(this)" onkeypress="Enter()"/>
															</td>
    														<td width="27%"><input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Buscar" onclick="verificarGestante();" /></td>
  													</tr>
												</table>
											</form>
											<div id='page'>
												<table width="100%" border="0">  													
													<tr>
														<td colspan="3"><div id="Opcion" align="center"></div></td>
													</tr>
													<tr>
														<td>
															<div id='resultado'></div>
														</td>
													</tr>
													<tr>
														<td>
															<div id='resultadoEnc1'></div>
														</td>
													</tr>
												</table>
											</div>
											<input name="txtCodusuario" type="hidden" id="txtCodusuario" value="<%=codigou %>"/>
											<input name="TxtFechaActual" type="hidden" id="TxtfechaActual"/>
											<input name="TxtTrim" type="hidden" id="Trimestre"/>
											<input name="TxtDias" type="hidden" id="TxtDias"/>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			<%} %>
	</body>
</html>