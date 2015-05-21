<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	import="adm_logica.Conexion" import="java.sql.*"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico">
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<title>Informe de Cateterismo</title>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type="text/javascript" src="jquery.js"></script>
<script lang="javascript" type="text/javascript" src="thickbox.js"></script>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type='text/javascript' src='Browser.js'></script>
<script lang="javascript" type="text/javascript" src="cat_Cateterismo_Formularios.js"></script>
<!--[if IE]><script language="javascript" type="text/javascript" src="excanvas.js"></script><![endif]-->
<script lang="javascript">
	function A(e) {
		tecla = e.keyCode;
		e.which;
		if (tecla == 13) {
			pacienteActivo();
		}
	}
	function pulsar(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		return (tecla != 13);
	}
</script>
<script lang="javascript">
function Verificar(e)
{
	
	var minavegador = new Browser();
	if (minavegador.name == 'firefox'){
	    if(e) 
		document.onkeypress = function(){return true;} ;
		var evt = e?e:event; 
		if(evt.keyCode==116) 
		{ 
		if(e) 
		document.onkeypress = function(){return false;} ;
		else 
		{ 
		evt.keyCode = 0; 
		evt.returnValue = false; 
		} 
		} 
	}
}
</script>
</head>

<body onload="document.getElementById('txtnumdoc').focus();" onKeyDown="Verificar(event)">
	<%
		String codigou =(String)session.getAttribute("codusuario");
	%>
	<%
		Conexion con=new Conexion();
		ResultSet rs;
		Statement st = null;
		String idu="";         
		st = con.conn.createStatement();
		rs=st.executeQuery("select usuario from seg_usuario where usu_codigo="+codigou);
	%>
	<%
		while(rs.next()){
		idu=rs.getString(1);
		}
		rs.getStatement().close();
		
		
		st=null;
		st=con.conn.createStatement();
		rs=st.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
		int madmin=0;
		if(rs.next()){
			madmin=rs.getInt(1);
		}
		rs.getStatement().close();
		con.cerrar();
	%>
	<%
		String cod=request.getParameter("codigo");
	if(codigou==null){
		codigou="";
	}
	if(codigou.equals("")){ //seguridad
	%>
	<script lang="javascript">
		alert("Usted No Tiene Permiso Para ver esta Pagina....");
		window.location.href = "Seg_login.jsp";
	</script>
	<%
		} else {
	%>
<table width="100%">
    <tr>
	    <td>
 	        <table width="100%">
		        <tr>
		            <td>
		                <div>
		                    <a href="mensajes.jsp?TB_iframe=true&height=520&width=700" class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin%></b></font></a>
		                    <%if (madmin > 0) {%>
		                    <bgsound src="Imagenes/INNERMK.WAV" loop="2">
		                    <img src="Imagenes/sobr0001.gif" />
		                    <%}%>
		                </div>
		            </td>
		            <td align="right">
		                <div align="right" id="usuario" align="right" class="style11">
		                Bienvenido,<b><%=idu%></b> |<a
		                href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou%>"
		                style="color: white">--Cerrar Session--</a>
		                </div>
		            </td>
		        </tr>
		    </table>
		</td>
	</tr>

	<tr>
	    <td>
	        <div id="cabecera1" align="center" class="linkmenu">
	        <b><a href="menu.jsp">Men&uacute; Principal</a>-<a
	        href="Hemodinamia.jsp">Hemodinamia</a>-Realizar diagn&oacute;stico
	        Hemodinamia</b>
	        </div>
	    </td>
	</tr>
    
    <tr>
	         <td id="cabecera2" class="style11" align="center">
		     REALIZAR INFORME CATETERISMO
			 </td>
	</tr>

	<tr>
	    <td>
            <table width="100%">
		        <tr>
		            <td align="center">Tipo de Documento</td>
		            <td>
		                <select name="cbafiliacion" size="1"
			            id="cbafiliacion" onchange="document.getElementById('txtnumdoc').focus();">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery("SELECT " + "sigla, " + "descripcion "
				                    + "FROM " + "adm_tipodocumento");
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
		            </td>
			        <td align="center">N&uacute;mero de Documento</td>
				    <td>
 				        <form id="form1" name="form1" onkeypress="return pulsar(event);">
				        <input type="text" name="txtnumdoc" id="txtnumdoc" onkeyup="A(event)" /> 
				        <input type="hidden" name="txtFecha" id="txtFecha" />
				        <input type="hidden" name="txtHora" id="txtHora" /> 
				        <input type="hidden" name="txtUsuario" id="txtUsuario" value="<%=codigou%>" />
				        </form>
				    </td>	
				    <td>
				    <input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Buscar" onClick="pacienteActivo()">
				    </td>
				  
	     	    </tr>
	     	  
	     	    
	        </table>
	    </td>
	</tr>
	
	  <tr>
                   <td >
                      <div id="datos_ingreso_paciente">
                      </div>
                   </td>
     </tr>
     
     <tr>
         <td id="menu_encabezado" style="display: none">
                <table border ="1" width="100%" >
	       <tr>
		      <td id="cabecera2" class="style11" colspan="2" align="center">ENCABEZADO INFORME</td>
   	       </tr>
	       <tr>
			   <td>Estudio Realizado:</td>
			   <td>
			      <select id="estudio_realizado">
				                     <%
						                st = null;
										rs = null;
										try {
    										con = new Conexion();
											st = con.conn.createStatement();
											rs = st.executeQuery("SELECT "
											+ "nombre_estudio_realizado " + "FROM "
	     									+ "cat_estudio_realizado_hemodinamia");
		 							 %>
									 <%
										while (rs.next()) {
									 %>
										<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
									 <%
										}
										    rs.getStatement().close();
										    con.cerrar();
										} catch (SQLException e) {
											System.out.println(e);
											out.println("no se pudo realizar la conexion!<br/>");
										}
									%>
			      </select>
			    </td>
			</tr>
   			<tr>
				<td>Indicaci&oacute;n:</td>
				<td><select id="indicaciones_diagnostico_hemodinamia">
											<%
												st = null;
													rs = null;
													try {
														con = new Conexion();
														st = con.conn.createStatement();
														rs = st.executeQuery("SELECT "
																+ "nombre_indicacion_diagnostico_hemodinamia "
																+ "FROM "
																+ "cat_indicacion_diagnostico_hemodinamia");
											%>
											<%
												while (rs.next()) {
											%>
											<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
											<%
												}
														rs.getStatement().close();
														con.cerrar();
													} catch (SQLException e) {
														System.out.println(e);
														out.println("no se pudo realizar la conexion!<br/>");
													}
											%>
					</select>
				</td>
			</tr>
			<tr>
				<td>Anesteci&oacute;logo</td>
				<td><input type="text" id="txt_anesteciologo" onkeyup="tiene_numeros()" maxlength="40"></td>
			</tr>
            <tr>
				<td id="cabecera2" class="style11" colspan="2" align="center"
					width="100%">JUSTIFICACI&Oacute;N:</td>
			</tr>
			<tr>
				<td colspan="2">
				<div align="center">
				<textarea class="textarea" name="resumen_procedimiento" id="resumen_procedimiento" cols="50" rows="5"></textarea>
				</div>
			</td>
			</tr>
			</table>       
         </td>
     </tr>
     
     <tr>
         <td id="menu_ventriculografia" style="display: none" >
             <table width="100%" >
			    <tr>
					<td id="cabecera2" class="style11" align="center">VENTRICULOGRAFIA
						<input type='checkbox' name='ventriculografia_activacion'
						onclick="realizarFormulario(1)">
					</td>
				</tr>
				
				<tr>
				<td align="center" id="informe_ventriculografia" style="display: none">
							
							<table>
								<tr>
									<td align="center" width="400" height="320">
									    <canvas width="400" height="320" id="ventriculografia"
										onclick="eventoClicksobreImagen('ventriculografia',1,event)">Tu navegador no soporta canvas.
									    </canvas>
									</td>
								</tr>
								
								<tr>
									<td >
										<table border="1">
											<tr>
												<td >Ventriculo Izquierdo Dilatado</td>
												<td >
												    <select id="seleccion_ventriculo_izq_dilatado"
														style="width: 120px"
														onchange="control_Informe_Ventriculografia(1)">
															<option selected="selected" value="no dilatado">No</option>
															<option value="dilatado">Si</option>
													</select>
												</td>
											</tr>
											
											<tr>
												<td colspan="2" align="center">Contractibilidad</td>
											</tr>
											
											<tr>
											    <td>
													-Tipo
												</td>
												
												<td>
												    <select id="seleccion_contratibilidad_1"
														style="width: 120px"
														onchange="control_Informe_Ventriculografia(2)">
														<%
																st = null;
																	rs = null;
																	try {
																		con = new Conexion();
																		st = con.conn.createStatement();
																		rs = st.executeQuery("SELECT " + "tipo_contractibilidad "
																				+ "FROM " + "cat_tipo_contractibilidad");
														%>
														<%
														     	while (rs.next()) {
														%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(1)
														%>  </option>
														<%
																}
																		rs.getStatement().close();
																		con.cerrar();
																	} catch (SQLException e) {
																		System.out.println(e);
																		out.println("no se pudo realizar la conexion!<br/>");
																	}
														%>
													</select>
												</td>
											</tr>
													
											<tr>
												<td>
													-Gravedad
												</td>
												
												<td>
												    <select id="seleccion_contratibilidad_2"
														style="width: 120px"
														onchange="control_Informe_Ventriculografia(3)" disabled="disabled">
														<%
																st = null;
																	rs = null;
																	try {
																		con = new Conexion();
																		st = con.conn.createStatement();
																		rs = st.executeQuery("SELECT " + "descripcion "
																				+ "FROM " + "cat_gravedad_contractibilidad");

														%>
														<%
																while (rs.next()) {
														%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(1)
														%>  </option>
														<%
																}
																		rs.getStatement().close();
																		con.cerrar();
																	} catch (SQLException e) {
																		System.out.println(e);
																		out.println("no se pudo realizar la conexion!<br/>");
																	}
														%>
													</select>
												</td>
											</tr>
												
											<tr>
												<td>
												-Caracter&iacute;stica
												</td>
												<td><select id="seleccion_contratibilidad_3"
														style="width: 120px"
														onchange="control_Informe_Ventriculografia(4)" disabled="disabled">
															<%
																st = null;
																	rs = null;
																	try {
																		con = new Conexion();
																		st = con.conn.createStatement();
																		rs = st.executeQuery("SELECT "
																				+ "caracteristica_contractibilidad " + "FROM "
																				+ "cat_caracteristica_contractibilidad");
															%>
															<%
																while (rs.next()) {
															%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
															<%
																}
																		rs.getStatement().close();
																		con.cerrar();
																	} catch (SQLException e) {
																		System.out.println(e);
																		out.println("no se pudo realizar la conexion!<br/>");
																	}
															%>
													</select>
												</td>
											</tr>
											
											<tr>
												<td>
										        -Lugar		
												</td>
												
												<td><select id="seleccion_contratibilidad_4"
														style="width: 120px"
														onchange="control_Informe_Ventriculografia(5)" disabled="disabled">
															<%
																st = null;
																	rs = null;
																	try {
																		con = new Conexion();
																		st = con.conn.createStatement();
																		rs = st.executeQuery("SELECT " + "lugar_contractibilidad "
																				+ "FROM " + "cat_lugar_contractibilidad");
															%>
															<%
																while (rs.next()) {
															%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
															<%
																}
																		rs.getStatement().close();
																		con.cerrar();
																	} catch (SQLException e) {
																		System.out.println(e);
																		out.println("no se pudo realizar la conexion!<br/>");
																	}
															%>
													</select>
												</td>
											</tr>
											
											<tr>
												<td>Fracci&oacute;n Eyecci&oacute;n Ventriculo Izquierdo (FEVI)</td>
												<td><select id="seleccion_FEVI" style="width: 120px"
														onchange="control_Informe_Ventriculografia(6)">

															<%
																st = null;
																	rs = null;
																	try {
																		con = new Conexion();
																		st = con.conn.createStatement();
																		rs = st.executeQuery("SELECT " + "porcentaje " + "FROM "
																				+ "cat_porcentaje_fevi");
															%>
															<%
																while (rs.next()) {
															%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
															<%
																}
																		rs.getStatement().close();
																		con.cerrar();
																	} catch (SQLException e) {
																		System.out.println(e);
																		out.println("no se pudo realizar la conexion!<br/>");
																	}
															%>
													</select>
												</td>
											</tr>
											
											<tr>
												<td>Aspecto v&aacute;lvula mitral</td>
												<td><select id="seleccion_aspecto_valvulamitral"
														style="width: 120px"
														onchange="control_Informe_Ventriculografia(7)">
															<%
																st = null;
																	rs = null;
																	try {
																		con = new Conexion();
																		st = con.conn.createStatement();
																		rs = st.executeQuery("SELECT " + "aspecto_valvula_mitral "
																				+ "FROM " + "cat_aspecto_valvula_mitral");
															%>
															<%
																while (rs.next()) {
															%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
															<%
																}
																		rs.getStatement().close();
																		con.cerrar();
																	} catch (SQLException e) {
																		System.out.println(e);
																		out.println("no se pudo realizar la conexion!<br/>");
																	}
															%>
												    </select>
												</td>
											</tr>
											
											<tr>
												<td>Insuficiencia v&aacute;lvula mitral</td>
												<td><select id="seleccion_insuficiencia_valvulamitral"
														style="width: 120px"
														onchange="control_Informe_Ventriculografia(8)">
															<%
																st = null;
																	rs = null;
																	try {
																		con = new Conexion();
																		st = con.conn.createStatement();
																		rs = st.executeQuery("SELECT "
																				+ "nivel_de_insuficiencia_mitral " + "FROM "
																				+ "cat_insuficiencia_mitral");
															%>
															<%
																while (rs.next()) {
															%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
															<%
																}
																		rs.getStatement().close();
																		con.cerrar();
																	} catch (SQLException e) {
																		System.out.println(e);
																		out.println("no se pudo realizar la conexion!<br/>");
																	}
															%>
													</select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
					</table>
				</td>
				</tr>
				
				
				
				
			</table>
        </td>
     </tr>
     <tr>				
				   <td id="menu_aortograma" style="display: none">
				       <table width="100%">
				          <tr>
				          <td id="cabecera2" class="style11" colspan="2" align="center">AORTOGRAMA
									<input type='checkbox' name='aortograma_activacion'
										onclick="realizarFormulario(2)">
						  </td>
				          
				          </tr>
				          <tr>
				          <td align="center" id="informe_aortograma" style="display: none">
				          	<table border="1">
								<tr>
									<td>Ra&iacute;z a&oacute;rtica</td>
									<td colspan="2">
									    <select id="seleccion_raiz_aortica"
										style="width: 100px" onchange="control_Informe_Aortograma(1)">
											<option selected="selected">No dilatada</option>
											<option>Dilatada</option>
								    	</select>
									</td>
									<td>medici&oacute;n</td>
									<td align="left"><input type="text"
											id="txt_medicion_raiz_aortica" maxlength="2" size="1"
											disabled="disabled" onkeyup="control_Informe_Aortograma(2)" />
											mm
									</td>
								</tr>
								
								<tr>
									<td>Aorta Ascendente</td>
									<td colspan="2">
									    <select id="seleccion_aorta_ascendente"
										style="width: 100px" onchange="control_Informe_Aortograma(3)">
											<option selected="selected">No dilatada</option>
											<option>Dilatada</option>
								    	</select>
									</td>
									<td>medici&oacute;n</td>
									<td align="left"><input type="text"
											id="txt_medicion_aorta_ascendente" maxlength="2" size="1"
											disabled="disabled" onkeyup="control_Informe_Aortograma(4)" />
											mm
									</td>
								</tr>
								
								
								
								<tr>
									<td>Estenosis a&oacute;rtica</td>
									<td colspan="2" align="left">
									    <select	id="seleccion_estenosis_aortica" style="width: 100px"
										onchange="control_Informe_Aortograma(8)">
												<%
													st = null;
														rs = null;
														try {
															con = new Conexion();
															st = con.conn.createStatement();
															rs = st.executeQuery("SELECT "
																	+ "nivel_de_estenosis_aortica " + "FROM "
																	+ "cat_estenosis_aortica");
												%>
												<%
													while (rs.next()) {
												%>
												<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
												<%
													}
															rs.getStatement().close();
															con.cerrar();
														} catch (SQLException e) {
															System.out.println(e);
															out.println("no se pudo realizar la conexion!<br/>");
														}
												%>
									    </select>
									</td>	
									<td>medici&oacute;n:</td>
									<td align="left"><input type="text"
										id="txt_medicion_gradiente_pico_pico" maxlength="2" size="1"
										disabled="disabled" onkeyup="control_Informe_Aortograma(9)" />
										mm
									</td>
								</tr>
								<tr>
									<td>v&aacute;lvula a&oacute;rtica</td>
									<td align="left" colspan="2">
									    <select id="seleccion_clase_valvula_aortica" style="width: 100px"
	    								onchange="control_Informe_Aortograma(5)">
												<%
													st = null;
														rs = null;
														try {
															con = new Conexion();
															st = con.conn.createStatement();
															rs = st.executeQuery("SELECT " + "clase_valvula_aortica "
																	+ "FROM " + "cat_clase_valvula_aortica");
												%>
												<%
													while (rs.next()) {
												%>
												<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
												<%
													}
															rs.getStatement().close();
															con.cerrar();
														} catch (SQLException e) {
															System.out.println(e);
															out.println("no se pudo realizar la conexion!<br/>");
														}
												%>
									    </select>
									</td>
									<td>clase:</td>
									<td align="left">
									    <select	id="seleccion_aspecto_valvula_aortica" style="width: 100px"
											onchange="control_Informe_Aortograma(6)">
												<%
													st = null;
														rs = null;
														try {
															con = new Conexion();
															st = con.conn.createStatement();
															rs = st.executeQuery("SELECT " + "aspecto_valvula_aortica "
																	+ "FROM " + "cat_aspecto_valvula_aortica");
												%>
												<%
													while (rs.next()) {
												%>
												<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
												<%
													}
															rs.getStatement().close();
															con.cerrar();
														} catch (SQLException e) {
															System.out.println(e);
															out.println("no se pudo realizar la conexion!<br/>");
														}
												%>
									    </select>
									</td>
								</tr>
								<tr>
									<td>Insufiencia a&oacute;rtica</td>
									<td colspan="4" align="left">
									    <select	id="seleccion_insuficiencia_aortica" style="width: 100px"
								        		onchange="control_Informe_Aortograma(7)">
												<%
													    st = null;
														rs = null;
														try {
															con = new Conexion();
															st = con.conn.createStatement();
															rs = st.executeQuery("SELECT "
																	+ "nivel_de_insuficiencia_aortica " + "FROM "
																	+ "cat_insuficiencia_aortica");
												%>
												<%
													while (rs.next()) {
												%>
												<option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
												<%
													}
															rs.getStatement().close();
															con.cerrar();
														} catch (SQLException e) {
															System.out.println(e);
															out.println("no se pudo realizar la conexion!<br/>");
														}
												%>
										</select>
									</td>
								</tr>
		
							</table>
				          </td>
    			          </tr>
  				         
				       </table>
				   
				   </td>

				</tr>
				
				
				<tr>				
				   <td id="menu_bypass" style="display: none">
				       <table width="100%">
				          <tr>
				          <td id="cabecera2" class="style11" colspan="2" align="center">BYPASS
									<input type='checkbox' name='bypass_activacion'
										onclick="realizarFormulario(3)">
						  </td>
				          
				          </tr>
				          <tr>
				          <td align="center" id="informe_bypass" style="display: none">
				          	<table border="1">
								<tr>
								<td>
								 <textarea class="textarea" name="bypass" onkeyup="control_Informe_Bypass(1)" id="bypass" cols="50" rows="5"></textarea>
								</td>
								</tr>
		
							</table>
				          </td>
    			          </tr>
  				         
				       </table>
				   
				   </td>

				</tr>
				
				
				<tr>
				
				<td id="menu_anatomia_coronaria" style="display: none">
				    <table width="100%">
				        <tr>
				            <td id="cabecera2" class="style11" align="center">ANATOMIA
										CORONARIA <input type='checkbox'
										name='anatomia_coronaria_activacion'
										onclick="realizarFormulario(4)">
							</td>
				        </tr>
				        
				         <tr>
	<td align="center" id="informe_anatomia_coronaria" style="display: none">
	    <table border="1" width="60%">
	       <tr>
	            <td width="55%"><div align="center">Dominancia:</div></td>
	    		<td width="45%"><select id="seleccion_dominancia"
								style="width: 264px">
								<option>Derecha</option>
								<option>Izquierda</option>
		               			</select>
				</td>
			</tr>
			<tr>
			    <td>
			        <div align="center">Eliga Arteria:</div>
			    </td>
			    <td>
				    <select style="width: 264px" id='Seleccion_Arteria_combo'
				    		onchange="seleccionAnalisisArteria()">
								    					<%
     													   Vector<String> vector_ramificacion_Arterias = new Vector<String>();
														   Vector<String> vector_abreviaciones_Arterias = new Vector<String>();
														   Vector<String> vector_nombre_arterias = new Vector<String>();
														   Vector<String> vector_estado_normal_arterias = new Vector<String>();
														  
														   
														   int i = 0;
														   st = null;
														   rs = null;
														   try {
																con = new Conexion();
																st = con.conn.createStatement();
																rs = st.executeQuery("SELECT DISTINCT " + "ramificacion "
																+ "FROM " + "cat_arteria");
															while (rs.next()) {
																vector_ramificacion_Arterias.add(i, rs.getString(1));
																i++;
															}
    															rs.getStatement().close();
																con.cerrar();
															} catch (SQLException e) {
																System.out.println(e);
																out.println("no se pudo realizar la conexion!<br/>");
															}

															int j = 0;
															st = null;
	    													rs = null;
															for (i = 0; i < vector_ramificacion_Arterias.size(); i++) {
														%>

															<optgroup label=" <%=vector_ramificacion_Arterias.elementAt(i)%>">
														<%
															try {
																con = new Conexion();
																st = con.conn.createStatement();
																rs = st.executeQuery("SELECT " + "abreviacion, "
																+ "nombre_Arteria, estado_normal " + "FROM " + "cat_arteria "
															    + "WHERE " + "ramificacion='"
																+ vector_ramificacion_Arterias.get(i) + "'");
															while (rs.next()) {
														%>
															<option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
														<%
											     				vector_abreviaciones_Arterias.add(j,rs.getString(1));
																vector_nombre_arterias.add(j, rs.getString(2));
																vector_estado_normal_arterias.add(j, rs.getString(3));
																j++;
															}
														%>
															</optgroup>
														<%
																rs.getStatement().close();
																con.cerrar();
															} catch (SQLException e) {
																System.out.println(e);
																out.println("no se pudo realizar la conexion!<br/>");
															  }
															}
														%>
					</select>
				</td>
			</tr>
    		<tr>
				<td colspan="2" width="600" height="430">
					<canvas onload="cargarImagenVentriculografia()" id="anatomia_coronaria" width="600" height="430"
					onclick="eventoClicksobreImagen('anatomia_coronaria',2,event)"> Tu navegador no soporta canvas. 
					</canvas>
				</td>
			</tr>
				             
				             <%
				                               int ind_estado_normal = 0;
				                               int ind_porcentaje_lesiones = 0;
											   int ind_desc_lecho_distal = 0;
											   int ind_verbo_lecho_distal =0;
 		                                       int ind_oclusion_total = 0;
 		                                       int ind_lesion_colateral = 0;
 		                                       int ind_caracteristicas_lesiones = 0;
 		                                       int ind_flujo_timi = 0;
 		                                       int ind_tipo_lesion = 0;
 		                                       int ind_bifurcaciones = 0;
 		                                       int ind_medina = 0;
 		                                       
 		                                       
 		                                     
 		                                       Vector<String> vector_porcentaje_lesiones = new Vector<String>();
 		                                       Vector<String> vector_desc_lecho_distal = new  Vector<String>();
 		                                       Vector<String> vector_verbo_lecho_distal = new Vector<String>();
 		                                       Vector<String> vector_caracteristicas_lesiones = new Vector<String>();
 		                                       Vector<String> vector_oclusion_total = new Vector<String>();
 		                                       Vector<String> vector_lesion_colateral = new Vector<String>();
 		                                       Vector<String> vector_desc_lesiones = new Vector<String>();
 		                                       Vector<String> vector_flujo_timi = new Vector<String>();
 	                                           Vector<String> vector_tipo_lesion = new Vector<String>();
 		                                       Vector<String> vector_bifurcaciones = new Vector<String>();
 		                                       Vector<String> vector_medina = new Vector<String>();
                                                 
 		                                       
 		                                    //consulta lecho distal
	                                          	st = null;
		                                        rs = null;
		                                        try {
			                                         con = new Conexion();
		                                             st = con.conn.createStatement();
		                                             rs = st.executeQuery("SELECT " + "caracteristica, verbo " + "FROM "
				                                    	+ "cat_lecho_distal where caracteristica <> 'N/A'");
		                                        while (rs.next()) {
		                                           	vector_desc_lecho_distal.add(ind_desc_lecho_distal,rs.getString(1));
		                                        	vector_verbo_lecho_distal.add(ind_verbo_lecho_distal,rs.getString(2));
		                                        	ind_desc_lecho_distal++;
		                                        	ind_verbo_lecho_distal++;
		                                        }
		                                        	rs.getStatement().close();
			                                        con.cerrar();
		                                        } catch (SQLException e) {
			                                        System.out.println(e);
			                                        out.println("no se pudo realizar la conexion!<br/>");
		                                        }


 		                                       //consulta porcentaje bloqueos
 	                                        	st = null;
 		                                        rs = null;
 		                                        try {
 			                                         con = new Conexion();
 		                                             st = con.conn.createStatement();
 			                                         rs = st.executeQuery("SELECT " + "porcentaje " + "FROM "
 				                                    	+ "cat_porcentaje_bloqueos");
 		                                        while (rs.next()) {
 				                                    vector_porcentaje_lesiones.add(ind_porcentaje_lesiones,
 						                            rs.getString(1));
 			                                     	ind_porcentaje_lesiones++;
 			                                    }
 		                                        	rs.getStatement().close();
 			                                        con.cerrar();
 		                                        } catch (SQLException e) {
 			                                        System.out.println(e);
 			                                        out.println("no se pudo realizar la conexion!<br/>");
 		                                        }

 		                                      //consulta tipos de oclusion 
 	                                          	st = null;
 		                                        rs = null;
 		                                        try {
 			                                        con = new Conexion();
 		                                         	st = con.conn.createStatement();
 			                                        rs = st.executeQuery("SELECT " + "descripcion " + "FROM "
 				                                  	+ "cat_oclusion_total "+"where descripcion <> 'N/A'");
 			                                    while (rs.next()) {
 				                                    vector_oclusion_total.add(ind_oclusion_total,
 					                             	rs.getString(1));
 				                                    ind_oclusion_total++;
 			                                    }
 			                                        rs.getStatement().close();
 			                                        con.cerrar();
 	                                         	} catch (SQLException e) {
 		                                         	System.out.println(e);
 			                                        out.println("no se pudo realizar la conexion!<br/>");
 		                                        }

 		                                        //consulta tipos de lesion colateral 
 	                                             st = null;
                                            	 rs = null;
 		                                        try {
 			                                        con = new Conexion();
 			                                        st = con.conn.createStatement();
 			                                        rs = st.executeQuery("SELECT " + "descripcion " + "FROM "
 				                                  	+ "cat_lesion_colateral");
 		                                    	while (rs.next()) {
 			                                     	vector_lesion_colateral.add(ind_lesion_colateral,
 					                            	rs.getString(1));
 			                                    	ind_lesion_colateral++;
 			                                    }
 			                                        rs.getStatement().close();
 			                                        con.cerrar();
 		                                        } catch (SQLException e) {
 		                                         	System.out.println(e);
 			                                        out.println("no se pudo realizar la conexion!<br/>");
 	                                          	}

 		                                        //consulta caracteristica lesion 
 	                                           	st = null;
 		                                        rs = null;
 		                                        try {
 			                                    con = new Conexion();
 		                                       	st = con.conn.createStatement();
 			                                    rs = st.executeQuery("SELECT " + "nombre_caracteristica " + "FROM "
 				                                + "cat_caracteristica_lesion where nombre_caracteristica <> 'N/A'");
 			                                    while (rs.next()) {
 				                                     vector_caracteristicas_lesiones.add(ind_caracteristicas_lesiones, rs.getString(1));
 				                                     ind_caracteristicas_lesiones++;
 			                                    }
 			                                        rs.getStatement().close();
 			                                        con.cerrar();
 		                                        } catch (SQLException e) {
 			                                        System.out.println(e);
 		                                         	out.println("no se pudo realizar la conexion!<br/>");
 		                                        }

 		                                        //consulta flujo timi
 		                                        st = null;
 		                                        rs = null;
 		                                        try {
 			                                        con = new Conexion();
 		                                           	st = con.conn.createStatement();
 			                                        rs = st.executeQuery("SELECT " + "tipo_flujo_timi "
 					                                + "FROM " + "cat_flujo_timi where tipo_flujo_timi <> 'N/A'");
 			                                    while (rs.next()) {
 			                                       	vector_flujo_timi.add(ind_flujo_timi, rs.getString(1));
 			                                     	ind_flujo_timi++;
 		                                       	}
 		                                        	rs.getStatement().close();
 		                                        	con.cerrar();
 		                                        } catch (SQLException e) {
 		                                          System.out.println(e);
 		                                          out.println("no se pudo realizar la conexion!<br/>");
 	                                           	}

 		                                        //consulta tipo lesion
 		                                        st = null;
 	                                         	rs = null;
 		                                        try {
 		                                            con = new Conexion();
 			                                        st = con.conn.createStatement();
 		                                         	rs = st.executeQuery("SELECT " + "tipo_lesion " + "FROM "
 				                                 	+ "cat_tipo_lesion where tipo_lesion <> 'N/A'");
 			                                    while (rs.next()) {
 			                                    	
 				                                    vector_tipo_lesion.add(ind_tipo_lesion, rs.getString(1));
 				                                    ind_tipo_lesion++;
 			                                    }
 			                                        rs.getStatement().close();
 			                                        con.cerrar();
 		                                        } catch (SQLException e) {
 			                                        System.out.println(e);
 			                                        out.println("no se pudo realizar la conexion!<br/>");
 		                                        }

 		                                        //consulta bifurcacion
 		                                        st = null;
 	                                         	rs = null;
 		                                        try {
 			                                        con = new Conexion();
 			                                        st = con.conn.createStatement();
 		                                        	rs = st.executeQuery(
 		                                          		 "SELECT "
 					                                     + "CONCAT(ram.id_rama_bifurcada,';',ram.nombre_rama,';',art.abreviacion) "
 					                                     + "FROM " + "cat_rama_bifurcada ram, "
 					                                     + "cat_arteria art " + "WHERE "
 					                                     + "ram.id_arteria_fk = art.id_arteria");
 			                                    while (rs.next()) {
 				                                    vector_bifurcaciones.add(ind_bifurcaciones,
 					                                rs.getString(1));
                                    				ind_bifurcaciones++;
 			                                    }
 			                                        rs.getStatement().close();
 			                                        con.cerrar();
 	                                        	} catch (SQLException e) {
 			                                      System.out.println(e);
 		                                          out.println("no se pudo realizar la conexion!<br/>");
 		                                        }
 
 	                                          	// consulta medinas
                                          		st = null;
 		                                        rs = null;
 		                                        try {
 			                                        con = new Conexion();
 		                                            st = con.conn.createStatement();
 			                                        rs = st.executeQuery("SELECT " + "medina " + "FROM "
 				                                   	+ "cat_medina " + "WHERE " + "medina <> 'N/A'");
 			                                    while (rs.next()) {
 				                                    vector_medina.add(ind_medina, rs.getString(1));
 				                                    ind_medina++;
 			                                    }
 			                                        rs.getStatement().close();
 			                                        con.cerrar();
 		                                        } catch (SQLException e) {
 			                                         System.out.println(e);
 			                                         out.println("no se pudo realizar la conexion!<br/>");
 		                                        }
 		                                
 		                                        ind_porcentaje_lesiones = 0;
 											    ind_desc_lecho_distal = 0;
 											    ind_verbo_lecho_distal =0;
 		                                        ind_oclusion_total = 0;
 		                                        ind_lesion_colateral = 0;
 		                                        ind_caracteristicas_lesiones = 0;
 		                                        ind_flujo_timi = 0;
 		                                        ind_tipo_lesion = 0;
 		                                        ind_bifurcaciones = 0;
 		                                        ind_medina = 0;
 		                                        int indice_total = 0;
                                             %> 
				        
				        
				      <tr>
				      
				      
				                <td align=center colspan="2" width="100%" >            
				                          
				                <%while (indice_total < vector_abreviaciones_Arterias.size()) { %>
				                 <table align="center" width="100%" border="1" id="Formulario<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>" style="display: none">
				                 <tr>
				                  <td colspan="2" align="center" >
									<b><%=vector_nombre_arterias.elementAt(indice_total)%></b>
								  
								   </td>
		                          </tr>
				                  <tr>
				                    <td colspan="2"  align="center" >Observaci&oacute;n:</td>
				                 </tr>
				                 <tr>
				                  <td colspan="2">
			                           <div align="center">	
								          	<textarea class="textarea" onkeyup="verificarContenidoObservacionesArteria()" disabled="disabled"  name='observacion<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>' id='observacion<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>' cols="50" rows="5"><%=vector_estado_normal_arterias.elementAt(indice_total)%></textarea>
		                               </div>										           
							       </td>
				                 </tr>
				                       <tr>
				                 <td align="center">Flujo timi:</td>
									    <td><select id='flujo_timi<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_combo' style="width: 264px" onChange="control_Informe_Anatomia_Coronaria(5)" disabled="disabled">
									        <%
										         while (ind_flujo_timi < vector_flujo_timi.size()) {
											%>
											    <option><%=vector_flujo_timi.elementAt(ind_flujo_timi)%></option>
											<%
											   	ind_flujo_timi++;
											%>
											<%
												}
											%>
											</select>
										</td>
				                 </tr>
				                 
				                
				                  <tr>
				                 <td align="center">Lecho Distal:</td>
				          				<td><select	id='lecho_distal<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_combo' style="width: 264px" onchange="control_Informe_Anatomia_Coronaria(7)" disabled="disabled">
										   <%
												while (ind_verbo_lecho_distal < vector_desc_lecho_distal.size()) {
										    %>
 										        <option value='<%=vector_verbo_lecho_distal.elementAt(ind_verbo_lecho_distal) %>'><%=vector_desc_lecho_distal.elementAt(ind_desc_lecho_distal)%></option>
										   	<%
										    	ind_verbo_lecho_distal++;
										    	ind_desc_lecho_distal++;
										    %>
										    <%
											}
										    %>
										    </select>
										</td>
				                 
				                 </tr>
				            
				                 <% 
			
				                int idlocalizacionlesiones = 0;
				                Vector<String> vector_localizacion_lesiones = new Vector<String>();
				                st = null;
                               	rs = null;
                                  try {
                                      con = new Conexion();
                                      st = con.conn.createStatement();
                                  	rs = st.executeQuery(
                                  			 "SELECT les.nombre_localizacion "+
            				                 "FROM "+
            				                 "cat_arteria cat,"+
            				                 "cat_localizacion_lesion les,"+
            				                 "cat_segmentos_arterias seg "+
            				                 "WHERE "+
            				                 "cat.abreviacion = '"+vector_abreviaciones_Arterias.elementAt(indice_total)+"'"+
            				                 "AND seg.id_arteria_fk =cat.id_arteria "+
            				                 "AND seg.id_localizacion_fk=  les.id_localizacion"
            				                  );
                                  while (rs.next()) {
                                	    vector_localizacion_lesiones.add(idlocalizacionlesiones,
		                                rs.getString(1));
	                                    idlocalizacionlesiones++;
                                  }
                                      rs.getStatement().close();
                                      con.cerrar();
                              	} catch (SQLException e) {
                                    System.out.println(e);
                                    out.println("no se pudo realizar la conexion!<br/>");
                                  }
                                  
                                
                                  idlocalizacionlesiones =0;
				                 while(idlocalizacionlesiones < vector_localizacion_lesiones.size()){ %>
				                 
				                    <tr>
				                   <td width="55%" align="center">- lesi&oacute;n <%= vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%></td>
								   <td width="45%"><select name='<%=idlocalizacionlesiones%>' id='porcentaje_lesion_seleccion<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%= vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo' style="width: 264px" onChange="control_Informe_Anatomia_Coronaria(1)">
									 
									    <%
									     	while (ind_porcentaje_lesiones < vector_porcentaje_lesiones.size()) {
										%>
										    <option><%=vector_porcentaje_lesiones.elementAt(ind_porcentaje_lesiones)%>
										    </option>
										<%
										    ind_porcentaje_lesiones++;
										%>
										<%
									    }
									    %>
										</select>
								
                                  							</td>
				                   </tr>
				                 
				                 <tr>
		
				                 <td style='visibility: hidden'>&nbsp;</td>
				                 <td style='display: none' id='caracteristicas<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>'  >
				                 
				                 <table border ="1" >
				                 <tr>
				                   <td colspan="2" align="center"> Caracter&iacute;sticas de la lesi&oacute;n <%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%> </td>
								 </tr>
				                 
				                 <tr>
				                   <td width="60%" align="center">Oclusi&oacute;n total</td>
								     <td><select id="lesion_oclusion_total<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo" style="width: 110px" onChange="control_Informe_Anatomia_Coronaria(2)" disabled="disabled">
								        	<% 
												while (ind_oclusion_total < vector_oclusion_total.size()) {
											%>
                                				<option><%=vector_oclusion_total.elementAt(ind_oclusion_total)%></option>
											<%
												ind_oclusion_total++;
											%>
											<%
												}
											%>
										 </select>
									</td>
				                 </tr>
				                  <tr>
				                   <td align="center">Circulaci&oacute;n Colateral</td>
			    				    <td><select	id='tipo_lesion_circulacion_colateral<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo' style="width: 110px" onChange="control_Informe_Anatomia_Coronaria(3)" disabled="disabled">
								    	    <%
									    		while (ind_lesion_colateral < vector_lesion_colateral.size()) {
										    %>
										        <option><%=vector_lesion_colateral.elementAt(ind_lesion_colateral)%></option>
										    <%
										       ind_lesion_colateral++;
										    %>
										    <%
										   	   }
											%>
										</select>
									</td>
				                 </tr>
				                 <tr>
				                   <td align="center">Caracter&iacute;stica</td>
			    					    <td><select id='Caracteristicas_lesion<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo' style="width: 110px" onChange="control_Informe_Anatomia_Coronaria(4)">
                       						<%
									    		while (ind_caracteristicas_lesiones < vector_caracteristicas_lesiones.size()) {
					    					%>
										     	<option><%=vector_caracteristicas_lesiones.elementAt(ind_caracteristicas_lesiones)%></option>
						    				<%
												ind_caracteristicas_lesiones++;
							    			%>
								    		<%
												}
									    	%>
										    </select>
										</td>
				                 </tr>
				                 <tr>
				                 <td align="center">Tipo de Lesi&oacute;n</td>
				          				<td><select	id='tipo_de_lesion<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo' style="width: 110px" onChange="control_Informe_Anatomia_Coronaria(6)">
										    <%
									     		while (ind_tipo_lesion < vector_tipo_lesion.size()) {
										    %>
											    <option><%=vector_tipo_lesion.elementAt(ind_tipo_lesion)%></option>
										   	<%
												ind_tipo_lesion++;
										    %>
										    <%
												}
										    %>
										    </select>
										</td>
				                 </tr>
				                 
				                 








<%
		     		     	            boolean sw_rama_bifurcada = false;
						                while (ind_bifurcaciones < vector_bifurcaciones.size() && (sw_rama_bifurcada == false)) {
												String[] cadena = vector_bifurcaciones.elementAt(ind_bifurcaciones).split(";");
									               		if (cadena[2].equals(vector_abreviaciones_Arterias.elementAt(indice_total))) {
																sw_rama_bifurcada = true;
																}
																ind_bifurcaciones++;
															}
															ind_bifurcaciones = 0;
															
												            %>
												          
												            <%
												         if (sw_rama_bifurcada == true) { 
												        	  
												         %>
											
											 <tr>
													        <td>Lesi&oacute;n Bifurcada?</td>
													        <td><select	name='<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>' id='bifurcada<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo' style="width: 110px" onchange="obtenerelementobifurcado('bifurcada<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo')">
															<option selected="selected">NO</option>
															<option>SI</option>
												            	</select>
                                            </td>
     </tr>
										<tr>
    									<td align="center">Con que rama?</td>
										<td><select	id='con_la_rama<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo' style="width: 110px" onchange="control_Informe_Anatomia_Coronaria(9)" disabled="disabled">
											 <% while (ind_bifurcaciones < vector_bifurcaciones.size()){ 
											 %>
											 <% String[] cadena = vector_bifurcaciones.elementAt(ind_bifurcaciones).split(";");
											    if (cadena[2].equals(vector_abreviaciones_Arterias.elementAt(indice_total))) {%>
											 <option value='<%=cadena[0]%>' ><%= cadena[1] %> </option>
											 <%}%>
											 <%ind_bifurcaciones++;%>
											 <%}%>
											</select>
										</td>
								   </tr>
									<tr>
                                        <td align="center" rowspan="2">Clasificaci&oacute;n de medina</td>
                                        <td>
                                            <select style="width:110px" disabled="disabled" id='clasificacion_medina_seleccion<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>_combo' onchange="control_Informe_Anatomia_Coronaria(10)">
                                            <% while (ind_medina < vector_medina.size()) { %>
                                                <option><%=vector_medina.elementAt(ind_medina)%>
                                                </option>
                                             <%ind_medina++;%>
                                             <%}%>
                                            </select>
                                        </td> 
                                   </tr>
                                    <tr>
                                        <td id='imagen_clasificacion_medina<%=vector_abreviaciones_Arterias.elementAt(indice_total)%>_<%=vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>' style="display: none">
                                        <img src='Imagenes/medina_001.png' width="80" height="80" id='imagen_clasificacion_medina_seleccionada<%=vector_abreviaciones_Arterias.elementAt(indice_total)+"_"+vector_localizacion_lesiones.elementAt(idlocalizacionlesiones)%>'/>
                                        </td>
                                    </tr>	
                                    
                                    
                                    	  
                                    
                                   <%
                                   
                              
												         
												         }%>
				                 
				                 
				                 
				                 </table>
				                 </td>
				                </tr>
				                 <%  
				                 
				                 ind_porcentaje_lesiones = 0;
				                 ind_oclusion_total = 0;
								 ind_lesion_colateral = 0;
								 ind_caracteristicas_lesiones = 0;
								 ind_tipo_lesion = 0;
								 ind_bifurcaciones = 0;
								 ind_medina = 0;
							
				                 idlocalizacionlesiones++;
				                 }   %>
				               
				           
				                 
				                 <!-- 

				                   	    
				                  -->
				                  
				                 
								
							  			         
											         
				                 
				                 
				               </table>
				                <%
										   
				                ind_flujo_timi = 0;
							    ind_verbo_lecho_distal=0;
						    	ind_desc_lecho_distal=0;
										    indice_total++;
							    %>
								<%}%>
				      
				               </td>
				         </tr>
				              
				      

				        
 </table>
</td>
</tr>





</table>
</td>
</tr>


<tr>




</tr>


<tr>
	<td id="Resultados_Informes" style="display: none" >
	    <table width="100%" border="1">
        <tr>
            <td  id="cabecera2" class="style11" align="center" colspan="2" ><div align="center">RESULTADOS: </div></td>
        </tr>
                            <tr>
                                <td>
                                    <div align="center">
                                            <table>
                                                <tr>
                                                    <td id="cabecera2" class="style11" align="center">VENTRICULOGRAFIA</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <textarea class="textarea" id="contenido_informe_ventriculografia" cols="50" rows="5"></textarea>
                                                    </td>
                                                </tr>
                                            </table>
                                    </div>
                                </td>
                                <td>
                                    <div align="center">
                                            <table>
                                                <tr>
                                                    <td id="cabecera2" class="style11" align="center">AORTOGRAMA</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <textarea class="textarea" id="contenido_informe_aortograma" cols="50" rows="5"></textarea>
                                                    </td>
                                                </tr>
                                            </table>
                                    </div>
                                </td>
                            </tr>
                            
                            
                              <tr>
                            
                                <td>
                                    <div align="center">
                                    
                                      <table>
                                                <tr>
                                                    <td id="cabecera2" class="style11" align="center">BYPASS</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <textarea readonly class="textarea" id="contenido_informe_bypass" cols="50" rows="10"></textarea>
                                                    </td>
                                                </tr>
                                            </table>
                                            
                                    </div>
		                        </td>
                         
                                <td>
                                    <div align="center">
                                    <table>
                                                <tr>
                                                    <td id="cabecera2" class="style11" align="center">ANATOMIA CORONARIA</td>
                                                </tr>
                                                <tr>
		                                            <td>
                                                        <textarea  class="textarea" readonly  id="contenido_informe_analisis_anatomia_coronaria" cols="50" rows="10"></textarea>
                                                    </td>
                                                </tr>
        
                                            </table>
                                    
                                          
                                    </div>
                                </td>
                            </tr>
                            
                     
                             
                            <tr>
		                        <td colspan = "2" align="center" id="cabecera2" class="style11">OBSERVACIONES:</td>
		                    </tr>
                            <tr>
		                        <td>
                                    <div align="center">
		                                    <table>
		                                        <tr>
		                                            <td align="center" id="cabecera2" class="style11">CONCLUSIONES:</td>
		                                        </tr>
		                                        <tr>
		                                            <td>
		                                                <textarea class="textarea" name="conclusiones" id="conclusiones" cols="50" rows="5"></textarea>
				                     	            </td>
		                                        </tr>
		                                    </table>
                                    </div>
                        		</td>
		                        <td>
                                    <div align="center">
		                                    <table>
		                                        <tr>
		                                            <td align="center" id="cabecera2" class="style11">RECOMENDACIONES:</td>
	            	                            </tr>
		                                        <tr>
		                                            <td>
	                       		                        <textarea class="textarea" name="recomendaciones" id="recomendaciones" cols="50" rows="5"></textarea>
                               	                    </td>
    	                                        </tr>
		                                    </table>
                                    </div>
		                        </td>
	                     	</tr>
		                    <tr>
		                        <td  colspan="2">
	                                <div align="center">
                                            <input id="guardar" type="button" value="Guardar Informe" onclick="enviardatos()"> 
			                                <input id="anular" type="button" value="Anular Informe" onclick="nuevoInforme()">
	                                </div>
		                        </td>
		                    </tr>
            </table>
    </td>
</tr>
</table>

	<%}%>
</body>
</html>
	