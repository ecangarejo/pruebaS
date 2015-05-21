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
<%
String docPac=request.getParameter("docPac");
String numDoc = request.getParameter("numDoc");
String CodHorarioMedico = request.getParameter("CodHorarioMedico");
String CodAdm = request.getParameter("CodAdm");
String CodPac = request.getParameter("CodPac");%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico">
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<link rel="stylesheet" href="estilos/jquery-ui.css" type="text/css" />
<title>Realizar Odontograma</title>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type="text/javascript" src="jquery.js"></script>
<script lang="javascript" type="text/javascript" src="thickbox.js"></script>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type='text/javascript' src='Browser.js'></script>
<script lang="javascript" type="text/javascript" src="Odont_realizar_ce.js"></script>
<script lang="javascript" type="text/javascript" src="jquery-1.8.3.js"></script>
<script lang="javascript" type="text/javascript" src="jquery-ui.js"></script>
<script lang="javascript">
	function A(e) {
		tecla = e.keyCode;
		e.which;
		if (tecla == 13) {
			BuscarPaciente();
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
<body onload="BuscarPaciente('<%=docPac %>',<%=numDoc %>,<%=CodHorarioMedico %>,<%=CodAdm %>,<%=CodPac %>);"
	onKeyDown="Verificar(event)">
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
				<div><a
					href="mensajes.jsp?TB_iframe=true&height=520&width=700"
					class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin%></b></font></a>
				<%if (madmin > 0) {%> <bgsound src="Imagenes/INNERMK.WAV" loop="2">
				<img src="Imagenes/sobr0001.gif" /> <%}%>
				</div>
				</td>
				<td align="right">
				<div align="right" id="usuario" align="right" class="style11">
				Bienvenido,<b><%=idu%></b> |<a
					href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou%>"
					style="color: white">--Cerrar Session--</a></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<div id="cabecera1" align="center" class="linkmenu"><b><a
			href="menu.jsp">Men&uacute; Principal</a>-<a
			href="Odontologia.jsp">Odontolog&iacute;a</a>-Realizar Odontograma</b></div>
		</td>
	</tr>

	<tr>
		<td id="cabecera2" class="style11" align="center">REALIZAR HISTORIA CLINICA ODONTOLOGICA</td>
	</tr>

	<tr>
		<td>
		<input type="hidden" name="txtFecha"
					id="txtFecha" /> <input type="hidden" name="txtHora" id="txtHora" />
				<input type="hidden" name="txtUsuario" id="txtUsuario"
					value="<%=codigou%>" />
		</td>
	</tr>

	<tr>
		<td>
		<div id="datos_ingreso_paciente"></div>
		</td>
	</tr>
	<tr>
		<td id="informe_formulario_general" style="display: none">

		<div id="formulario_general" >
<div id="formulario_encabezado">
		<div align="center">

		<table border="1" width="90%">
<tr>
						<td colspan="3">
						<div align='center' class='style11' id='cabecera2' >ODONTOGRAMA</div>
						</td>
                    </tr>
              
                     <tr><td colspan="3">
                   <table border="0" width="100%" align="left" >
                    <tr><td width="15%">
                           
                       <table border="0" width="100%" align="left" >
                      <!-- botones ubicados a un lado del canvas -->
                        <tr><td>
                       <button   id="btn5" title="exodoncia indicada" onclick="convencion(5)" > <img src="Imagenes/odontograma/boton-exodoncia.PNG"> </button>
                        
                        </td><td>
                        <button   id="btn4" title="ausente" onclick="convencion(4)" > <img src="Imagenes/odontograma/boton-ausente.PNG"> </button>
                       
                        </td></tr><tr><td>
                         <button   id="btn2" title="caries"  onclick="convencion(2)"> <img src="Imagenes/odontograma/boton-caries.PNG"> </button>
                          </td><td>
                        <button   id="btn1" title="obturacion realizada"  onclick="convencion(1)"> <img src="Imagenes/odontograma/boton-amalgama1.PNG"> </button>
                        </td></tr><tr><td>
                        <button   id="btn10" title="sellante indicado" onclick="convencion(10)" ><img src="Imagenes/odontograma/boton-sellante-indicado.PNG">  </button>
                        </td><td>
                         <button   id="btn11" title="sellante realizado"  onclick="convencion(11)"> <img src="Imagenes/odontograma/boton-sellante-realizado.PNG"> </button>   
                       </td></tr><tr><td>
                       <button   id="btn13" title="endodoncia indicada"  onclick="convencion(13)" > <img src="Imagenes/odontograma/boton-endodoncia-indicada.PNG"> </button> 
                       </td><td>
                       <button   id="btn12" title="endodoncia realizada" onclick="convencion(12)" > <img src="Imagenes/odontograma/boton-endodoncia-realizada.PNG"> </button>
                       
                        </td></tr> <tr><td>    
                         <button   id="btn9" title="diente sano"  onclick="convencion(9)" > <img src="Imagenes/odontograma/boton-sano.png"> </button>         
                         
                        </td><td>
                          <button   id="btn7" title="corona" onclick="convencion(7)"> <img src="Imagenes/odontograma/boton-corona.PNG"> </button>
                         </td></tr><tr><td>              
                        </td></tr><tr><td colspan="2">    
                        <button   id="btn8" title="protesis fija" onclick="convencion(8)" > <img src="Imagenes/odontograma/boton-protfija.PNG"> </button>   
                        </td></tr>         
                        </table>
						
                       </td><td>
						
                       <center>
                      <!-- canvas de odontograma -->
						<canvas id="canvas_odontograma" width="80" height="80" 
						 onclick="click_segmentacion_ventricular_izquierda('canvas_odontograma',event)"	>Tu navegador no soporta canvas.</canvas></td>
                        </center>
                         </td></tr>
                        </table>

					</tr>


 <tr>
			<td colspan="3" align="center">
				<div align='center' class='style11' id='cabecera2' >INDICES</div>
           </td>
        </tr>
        
         <tr>
            <td style="border-width: 0px" colspan="3" >

				C.O.P.<input id='cop' type='text'  size='30'  />
           
        
 <tr>
            <td style="border-width: 0px" colspan="3" >
    C.E.O.<input id='ceo' type='text'  size='30'  />
				
             </td>
</tr>

         <tr>
			<td colspan="3" align="center">
				<div align='center' class='style11' id='cabecera2' >MOTIVO DE CONSULTA</div>
           </td>
        </tr>
        
         <tr>
            <td colspan="3" align="center">

				<textarea id="odont_motivo_consulta" cols="50" rows="5"></textarea> 
				
             </td>
          </tr>


<tr>
			<td colspan="3" align="center">
				<div align='center' class='style11' id='cabecera2' >EVOLUCI&Oacute;N </div>
           </td>
        </tr>
        
         <tr>
            <td colspan="3" align="center">

				<textarea id="odont_evolucion" cols="50" rows="5"></textarea> 
				
             </td>
          </tr>

            <tr>
			<td colspan="3" align="center">
				<div align='center' class='style11' id='cabecera2' >ANTECEDENTES M&Eacute;DICOS Y ODONTOL&Oacute;GICOS</div>
 </td>
          </tr>
<tr>
<td colspan="3" align="center">

                <textarea id="odont_antecendentes_medicos" cols="50" rows="5"></textarea> <br>

 </td>
				
             
          </tr>
           <tr>
             <td>                         
							<!-- checbox y textarea de antecedentes medicos y odontologicos -->			
                               <table border="0" width="100%">
                                  <tr>
                                   <td align="left" >
                                   Alergias</td>
                                     <td align="left"  >
										si <input type="radio"
											id="chkalergia" name="odont_alergia" value="1" onclick="aparecerElemento('div_alergias')" >
										no <input type="radio"
											name="odont_alergia" value="0" 
											checked="checked" onclick="check_no('div_alergias','txt_alergias')" > 
                                            </td>
                                          <td>
                                            <div id=div_alergias  style="display: none">
                                             <textarea id="txt_alergias" cols="15" rows="2"></textarea>
                                            </div>
                                          </td>
                                </tr>
                                    <tr>

                                     <td align="left" >Hemorragias</td>

										 <td align="left" >si <input type="radio"
											id="chkhemorragia" name="odont_hemorragia" value="1" onclick="aparecerElemento('div_hemorragia')">
										no <input type="radio"
											name="odont_hemorragia" value="0" onclick="check_no('div_hemorragia','txt_hemorragia')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_hemorragia  style="display: none">
                                             <textarea id="txt_hemorragia" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                         <tr>
                                     <td align="left">Enfermedades respiratorias</td>
										 <td align="left">si <input type="radio"
											id="chkenf_respiratorias" name="odont_enf_respiratorias" value="1" onclick="aparecerElemento('div_enf_respiratorias')">
										no <input type="radio"
											name="odont_enf_respiratorias" value="0" onclick="check_no('div_enf_respiratorias','txt_enf_respiratorias')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_enf_respiratorias  style="display: none">
                                             <textarea id="txt_enf_respiratorias" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                      <tr>
                                     <td align="left" >Cardiopat&iacute;as</td>
										 <td align="left">si <input type="radio"
											id="chkcardiopatias" name="odont_cardiopatias" value="1" onclick="aparecerElemento('div_cardiopatias')">
										no <input type="radio"
											name="odont_cardiopatias" value="0" onclick="check_no('div_cardiopatias','txt_cardiopatias')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_cardiopatias  style="display: none">
                                             <textarea id="txt_cardiopatias" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                       <tr> 
                                   <td align="left" > Fiebre reum&aacute;tica</td>
										 <td align="left">si <input type="radio"
											id="chkfiebre" name="odont_fiebre_reumatica" value="1" onclick="aparecerElemento('div_fiebre_reumatica')">
										no <input type="radio"
											name="odont_fiebre_reumatica" value="0" onclick="check_no('div_fiebre_reumatica','txt_fiebre_reumatica')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_fiebre_reumatica  style="display: none">
                                             <textarea id="txt_fiebre_reumatica" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr>
                                    <td align="left" >Enfermedades renales</td>
										 <td align="left">si <input type="radio"
											id="chkenf_renales" name="odont_enf_renales" value="1" onclick="aparecerElemento('div_enf_renales')">
										no <input type="radio"
											 name="odont_enf_renales" value="0" onclick="check_no('div_enf_renales','txt_enf_renales')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_enf_renales  style="display: none">
                                             <textarea id="txt_enf_renales" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>  
</table>   
                           </td>
                          <td>
                        <table border="0" width="100%">

                                       <tr>
                                         <td align="left">Hepatitis</td>
										 <td align="left">si <input type="radio"
											id="chkhepatitis"name="odont_hepatitis" value="1" onclick="aparecerElemento('div_hepatitis')">
										no <input type="radio"
											name="odont_hepatitis" value="0" onclick="check_no('div_hepatitis','txt_hepatitis')"
											checked="checked"> </td>
                                          <td>
                                            <div id=div_hepatitis  style="display: none">
                                             <textarea id="txt_hepatitis" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                         <tr>
                                      <td align="left" >Transtornos g&aacute;stricos</td>
										 <td align="left">si <input type="radio"
											id="chktrans_gastricos" name="odont_trans_gastricos" value="1" onclick="aparecerElemento('div_trans_gastricos')">
										no <input type="radio"
											name="odont_trans_gastricos" value="0" onclick="check_no('div_trans_gastricos','txt_trans_gastricos')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_trans_gastricos  style="display: none">
                                             <textarea id="txt_trans_gastricos" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                     <tr>
                                      <td align="left">Tensi&oacute;n arterial</td>
										 <td align="left">si <input type="radio"
											id="chktension_arterial" name="odont_tension_arterial" value="1" onclick="aparecerElemento('div_tension_arterial')">
										no <input type="radio"
											name="odont_tension_arterial" value="0" onclick="check_no('div_tension_arterial','txt_tension_arterial')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_tension_arterial  style="display: none">
                                             <textarea id="txt_tension_arterial" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                      <tr>
                                      <td align="left">Diabetes</td>
										 <td align="left">si <input type="radio"
											id="chkdiabtes" name="odont_diabetes" value="1" onclick="aparecerElemento('div_diabetes')">
										no <input type="radio"
											name="odont_diabetes" value="0" onclick="check_no('div_diabetes','txt_diabetes')"
											checked="checked"> </td> 
                                         <td>
                                            <div id=div_diabetes  style="display: none">
                                             <textarea id="txt_diabetes" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>  
                                    <tr>
                                       <td align="left">Ingesta medicamentos</td>
										 <td align="left">si <input type="radio"
											id="chkmedicamentos" name="odont_medicamentos" value="1" onclick="aparecerElemento('div_medicamentos')">
										no <input type="radio"
											name="odont_medicamentos" value="0" onclick="check_no('div_medicamentos','txt_medicamentos')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_medicamentos  style="display: none">
                                             <textarea id="txt_medicamentos" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr> 
                                     <td align="left">Cirug&iacute;as (incluso orales)</td>
										 <td align="left">si <input type="radio"
											id="chkcirugias" name="odont_cirugias" value="1" onclick="aparecerElemento('div_cirugias','txt_cirugias')">
										no <input type="radio"
											name="odont_cirugias" value="0" onclick="check_no('div_cirugias')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_cirugias style="display: none">
                                             <textarea id="txt_cirugias" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>    
                          </table> </td>
                                      <td>
                                      <table border="0" width="100%">
                                       <tr>
                          
                                        <td align="left">Uso de pr&oacute;tesis o<br>aparatolog&iacute;a oral</td>
										 <td align="left"><br>si <input type="radio"
											id="chkprotesis" name="odont_protesis" value="1" onclick="aparecerElemento('div_protesis')">
										no <input type="radio"
											name="odont_protesis" value="0" onclick="check_no('div_protesis','txt_protesis')"
											checked="checked"> </td>
                                          <td>
                                            <div id=div_protesis style="display: none">
                                             <textarea id="txt_protesis" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                      <tr>
                                     <td align="left">HIV</td>
										 <td align="left">si <input type="radio"
											id="chkhiv" name="odont_hiv" value="1" onclick="aparecerElemento('div_hiv')">
										no <input type="radio"
											name="odont_hiv" value="0" onclick="check_no('div_hiv','txt_hiv')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_hiv  style="display: none">
                                             <textarea id="txt_hiv" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr>
                                    <td align="left"> Extracciones dentales</td>
										 <td align="left">si <input type="radio"
											id="chkextracciones" name="odont_extraciones" value="1" onclick="aparecerElemento('div_extracciones')">
										no <input type="radio"
											name="odont_extraciones" value="0" onclick="check_no('div_extracciones','txt_extracciones')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_extracciones  style="display: none">
                                             <textarea id="txt_extracciones" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                       <tr>
                                      <td align="left">Enfermedades orales</td>
										 <td align="left">si <input type="radio"
											id="chkenf_orales" name="odont_enf_orales" value="1" onclick="aparecerElemento('div_enf_orales')">
										no <input type="radio"
											name="odont_enf_orales" value="0" onclick="check_no('div_enf_orales','txt_enf_orales')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_enf_orales  style="display: none">
                                             <textarea id="txt_enf_orales" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr>  
                                    <td align="left">Antecedentes familiares</td>
										 <td align="left">si <input type="radio"
											id="chkant_familiares" name="odont_ant_familiares" value="1" onclick="aparecerElemento('div_ant_familiares')">
										no <input type="radio"
											name="odont_ant_familiares" value="0" onclick="check_no('div_ant_familiares','txt_ant_familiares')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_ant_familiares  style="display: none">
                                             <textarea id="txt_ant_familiares" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                       <tr>  
                                     <td align="left">Otros</td>
										 <td align="left">si <input type="radio"
											id="chkAnt_otros" name="odont_ant_med_otros" value="1" onclick="aparecerElemento('div_ant_med_otros')">
										no <input type="radio"
											name="odont_ant_med_otros" value="0" onclick="check_no('div_ant_med_otros','txt_ant_medicos_otros')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_ant_med_otros  style="display: none">
                                             <textarea id="txt_ant_medicos_otros" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>    
                           
   </table>
</td> 

</tr>



            <tr>
			<td colspan="3" align="center">
				<div align='center' class='style11' id='cabecera2' >EXAMEN CL&Iacute;NICO </div>
              </td>
          </tr>
             <tr>
			<td colspan="3" align="center">
				<textarea id="odont_examen_clinico" cols="50" rows="5"></textarea> <br>
				
             </td>
          </tr>
          <tr>
             <td>                         
									<!-- checbox y texarea de examen clinico -->	
                               <table border="0" width="100%">
                                  <tr>
                                   <td align="left">
                                   Lengua</td>
                                         
                                      <td align="left">
										si <input type="radio"
											id="chkalengua" name="odont_lengua" value="1" onclick="aparecerElemento('div_lengua')" >
										no <input type="radio"
											name="odont_lengua" value="0" onclick="check_no('div_lengua','txt_lengua')"
											checked="checked"> 
                                            </td>
                                         <td>
                                            <div id=div_lengua  style="display: none">
                                             <textarea id="txt_lengua" cols="15" rows="2"></textarea>
                                            </div>
                                          </td>
                                </tr>
                                    <tr>

                                     <td align="left">Carrillos</td>

										 <td align="left">si <input type="radio"
											id="chkcarillos" name="odont_carrillos" value="1" onclick="aparecerElemento('div_carrillos')">
										no <input type="radio"
											name="odont_carrillos" value="0" onclick="check_no('div_carrillos','txt_carillos')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_carrillos  style="display: none">
                                             <textarea id="txt_carillos" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                         <tr>
                                      <td align="left">Abrasi&oacute;n</td>
										 <td align="left">si <input type="radio"
											id="chkabrasion" name="odont_abrasion" value="1" onclick="aparecerElemento('div_abrasion')">
										no <input type="radio"
											name="odont_abrasion" value="0" onclick="check_no('div_abrasion','txt_abrasion')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_abrasion  style="display: none">
                                             <textarea id="txt_abrasion" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                      <tr>
                                     <td align="left">Atrici&oacute;n</td>
										 <td align="left">si <input type="radio"
											id="chkatricion" name="odont_atricion" value="1" onclick="aparecerElemento('div_atricion')">
										no <input type="radio"
											name="odont_atricion" value="0" onclick="check_no('div_atricion','txt_atricion')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_atricion  style="display: none">
                                             <textarea id="txt_atricion" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                       <tr> 
                                    <td align="left"> Hipoplasias</td>
										 <td align="left">si <input type="radio"
											id="chkhipoplasias" name="odont_hipoplasias" value="1" onclick="aparecerElemento('div_hipoplasias')">
										no <input type="radio"
											name="odont_hipoplasias" value="0" onclick="check_no('div_hipoplasias','txt_hipoplasias')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_hipoplasias  style="display: none">
                                             <textarea id="txt_hipoplasias" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr>
                                     <td align="left">Ginvivitis marginal</td>
										 <td align="left">si <input type="radio"
											id="chkgin_marginal" name="odont_gin_marginal" value="1" onclick="aparecerElemento('div_gin_marginal')">
										no <input type="radio"
											name="odont_gin_marginal" value="0" onclick="check_no('div_gin_marginal','txt_gin_marginal')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_gin_marginal style="display: none">
                                             <textarea id="txt_gin_marginal" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>  
                                      <tr>
                                     <td align="left">Ginvivitis difusa</td>
										 <td align="left">si <input type="radio"
											id="chkgin_difusa" name="odont_gin_difusa" value="1" onclick="aparecerElemento('div_gin_difusa')">
										no <input type="radio"
											name="odont_gin_difusa" value="0" onclick="check_no('div_gin_difusa','txt_gin_difusa')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_gin_difusa style="display: none">
                                             <textarea id="txt_gin_difusa" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>  
                                      <tr>
                                     <td align="left">Micrognasia</td>
										 <td align="left">si <input type="radio"
											id="chkmicrognasia" name="odont_micrognasia" value="1" onclick="aparecerElemento('div_micrognasia')">
										no <input type="radio"
											name="odont_micrognasia" value="0" onclick="check_no('div_micrognasia','txt_micrognasia')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_micrognasia  style="display: none">
                                             <textarea id="txt_micrognasia" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>  
</table>   
                           </td>
                          <td>
                        <table border="0" width="100%">
                                     <tr>
                                     <td align="left">Macrognasia</td>
										 <td align="left">si <input type="radio"
											id="chkmacrognasia" name="odont_macrognasia" value="1" onclick="aparecerElemento('div_macrognasia')">
										no <input type="radio"
											name="odont_macrognasia" value="0" onclick="check_no('div_macrognasia','txt_macrognasia')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_macrognasia  style="display: none">
                                             <textarea id="txt_macrognasia" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>  

                                       <tr>
                                          <td align="left">Desviaci&oacute;n linea media</td>
										 <td align="left">si <input type="radio"
											id="chkdesviacionlm" name="odont_desviacionlm" value="1" onclick="aparecerElemento('div_desviacionlm')">
										no <input type="radio"
											name="odont_desviacionlm" value="0" onclick="check_no('div_desviacionlm','txt_desviacionlm')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_desviacionlm style="display: none">
                                             <textarea id="txt_desviacionlm" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                         <tr>
                                      <td align="left">Malposici&oacute;n</td>
										 <td align="left">si <input type="radio"
											id="chkmalposicion" name="odont_malposicion" value="1" onclick="aparecerElemento('div_malposicion')">
										no <input type="radio"
											name="odont_malposicion" value="0" onclick="check_no('div_malposicion','txt_malposicion')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_malposicion  style="display: none">
                                             <textarea id="txt_malposicion" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                     <tr>
                                      <td align="left">ATM</td>
										 <td align="left">si <input type="radio"
											id="chkatm" name="odont_atm" value="1" onclick="aparecerElemento('div_atm')">
										no <input type="radio"
											name="odont_atm" value="0" onclick="check_no('div_atm','txt_atm')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_atm  style="display: none">
                                             <textarea id="txt_atm" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                      <tr>
                                      <td align="left">Habitos orales</td>
										 <td align="left">si <input type="radio"
											id="chkhab_orales" name="odont_hab_orales" value="1" onclick="aparecerElemento('div_hab_orales')">
										no <input type="radio"
											name="odont_hab_orales" value="0" onclick="check_no('div_hab_orales','txt_hab_orales')"
											checked="checked"> </td> 
                                         <td>
                                            <div id=div_hab_orales  style="display: none">
                                             <textarea id="txt_hab_orales" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>  
                                    <tr>
                                       <td align="left">Paladar blando</td>
										 <td align="left">si <input type="radio"
											id="chkpaladar_blando" name="odont_paladar_blando" value="1" onclick="aparecerElemento('div_paladar_blando')">
										no <input type="radio"
											name="odont_paladar_blando" value="0" onclick="check_no('div_paladar_blando','txt_paladar_blando')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_paladar_blando  style="display: none">
                                             <textarea id="txt_paladar_blando" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr> 
                                     <td align="left">Piso de boca</td>
										 <td align="left">si <input type="radio"
											id="chkpiso_boca" name="odont_piso_boca" value="1" onclick="aparecerElemento('div_piso_boca')">
										no <input type="radio"
											name="odont_piso_boca" value="0" onclick="check_no('div_piso_boca','txt_piso_boca')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_piso_boca  style="display: none">
                                             <textarea id="txt_piso_boca" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>    
                                       <tr> 
                                     <td align="left">Supernumerarios</td>
										 <td align="left">si <input type="radio"
											id="chksupernumerarios" name="odont_supernumerarios" value="1" onclick="aparecerElemento('div_supernumerarios')">
										no <input type="radio"
											name="odont_supernumerarios" value="0" onclick="check_no('div_supernumerarios','txt_supernumerarios')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_supernumerarios  style="display: none">
                                             <textarea id="txt_supernumerarios" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>    
                          </table> </td>
                                      <td>
                                      <table border="0" width="100%">
                                       <tr>
                          
                                         <td align="left">Hipodoncia</td>
										 <td align="left">si <input type="radio"
											id="chkhipodoncia" name="odont_hipodoncia" value="1" onclick="aparecerElemento('div_hipodoncia')">
										no <input type="radio"
											name="odont_hipodoncia" value="0" onclick="check_no('div_hipodoncia','txt_hipodoncia')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_hipodoncia  style="display: none">
                                             <textarea id="txt_hipodoncia" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                      <tr>
                                      <td align="left">Fracturas</td>
										 <td align="left">si <input type="radio"
											id="chkfracturas" name="odont_fracturas" value="1" onclick="aparecerElemento('div_fracturas')">
										no <input type="radio"
											name="odont_fracturas" value="0" onclick="check_no('div_fracturas','txt_fracturas')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_fracturas  style="display: none">
                                             <textarea id="txt_fracturas" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr>
                                     <td align="left">Periodontitis</td>
										 <td align="left">si <input type="radio"
											id="chkperiodontitis" name="odont_periodontitis" value="1" onclick="aparecerElemento('div_periodontitis')">
										no <input type="radio"
											name="odont_periodontitis" value="0" onclick="check_no('div_periodontitis','txt_periodontitis')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_periodontitis  style="display: none">
                                             <textarea id="txt_periodontitis" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                       <tr>
                                      <td align="left">Retracciones</td>
										 <td align="left">si <input type="radio"
											id="chkretracciones" name="odont_retracciones" value="1" onclick="aparecerElemento('div_retracciones')">
										no <input type="radio"
											name="odont_retracciones" value="0" onclick="check_no('div_retracciones','txt_retracciones')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_retracciones  style="display: none">
                                             <textarea id="txt_retracciones" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                        <tr>  
                                    <td align="left">Torus</td>
										 <td align="left">si <input type="radio"
											id="chktorus" name="odont_torus" value="1" onclick="aparecerElemento('div_torus')">
										no <input type="radio"
											name="odont_torus" value="0" onclick="check_no('div_torus','txt_torus')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_torus  style="display: none">
                                             <textarea id="txt_torus" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>
                                       <tr>  
                                     <td align="left">Sobremordida vertical</td>
										 <td align="left">si <input type="radio"
											id="chksob_vertical" name="odont_sob_vertical" value="1" onclick="aparecerElemento('div_sob_vertical')">
										no <input type="radio"
											name="odont_sob_vertical" value="0" onclick="check_no('div_sob_vertical','txt_sob_vertical')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_sob_vertical  style="display: none">
                                             <textarea id="txt_sob_vertical" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr>    
                                       <tr>  
                                     <td align="left">Sobremordida horizontal</td>
										 <td align="left">si <input type="radio"
											id="chksob_horizontal" name="odont_sob_horizontal" value="1" onclick="aparecerElemento('div_sob_horizontal')">
										no <input type="radio"
											name="odont_sob_horizontal" value="0" onclick="check_no('div_sob_horizontal','txt_sob_horizontal')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_sob_horizontal  style="display: none">
                                             <textarea id="txt_sob_horizontal" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr> 
                                       <tr>  
                                     <td align="left">otros</td>
										 <td align="left">si <input type="radio"
											id="chkex_otros" name="odont_ex_clinico_otros" value="1" onclick="aparecerElemento('div_ex_clinico_otros')">
										no <input type="radio"
											name="odont_ex_clinico_otros" value="0" onclick="check_no('div_ex_clinico_otros','txt_ex_clinico_otros')"
											checked="checked"> </td>
                                         <td>
                                            <div id=div_ex_clinico_otros  style="display: none">
                                             <textarea id="txt_ex_clinico_otros" cols="15" rows="2"></textarea>
                                            </div>
                                          </td></tr> 
                           
   </table>
</td> 

</tr>

<tr>
			<td colspan="3" align="center">
				<div align='center' class='style11' id='cabecera2' >PROMOCI&Oacute;N Y PREVENCI&Oacute;N EN SALUD ORAL</div>
           </td>
        </tr>


         <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             CUANTAS VECES SE CEPILLA
</td>
            <td style="border-width: 0px" align="left">
				
				<input id='vecesCepilla' type='text'  size='10' maxlength='2' onkeyup="validar_campos_num('vecesCepilla')"/> </td>
          </tr>

         <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             USA CEPILLO
</td>
            <td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkcepillo" name="cepillo" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="cepillo" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>

         <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             USA SEDA
</td>
            <td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkseda" name="seda" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="seda" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>

         <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             USA ENJUAGUE
</td>
            <td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkenjuague" name="enjuague" value="si" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="enjuague" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>


<tr>
            <td style="border-width: 0px" colspan="2" align="left">
             ESTADO DE LA HIGIENE ORAL DEL PACIENTE
</td>
            <td style="border-width: 0px" align="left">
				
										 bueno <input type="radio"
											id="chkEstadoHig" name="EstHig" value="bueno" checked="checked"> &nbsp;&nbsp;
										regular <input type="radio"
											name="EstHig" value="regular" > &nbsp;&nbsp;
                                        malo <input type="radio"
											name="EstHig" value="malo" > &nbsp;&nbsp;&nbsp;
                                      
				
             </td>
          </tr>


        <tr>
<td style="border-width: 0px" colspan="2" align="left">
<br></br><b>EDUCACI&Oacute;N EN SALUD ORAL EN CUANTO A: </b> <br></br>
</td>
</tr>
         <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             Identificaci&oacute;n de malformaciones cong&eacute;nitas en cavidad oral y 
             tratamiento oportuno
</td>
            <td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkedu1" name="edu1" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="edu1" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>

 <tr>
            <td style="border-width: 0px" colspan="2" align="left">
            H&aacute;bitos de higiene oral y t&eacute;cnicas de cepillado
</td>
<td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkedu2" name="edu2" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="edu2" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>
 <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             Control de placa bacteriana
</td>
<td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkedu3" name="edu3" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="edu3" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>

 <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             Colocaci&oacute;n de fl&uacute;or t&oacute;pico
</td>
<td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkedu4" name="edu4" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="edu4" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>

 <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             Profilaxis
</td>
<td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkedu5" name="edu5" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="edu5" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>

 <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             H&aacute;bitos de higiene en pr&oacute;tesis dental
</td>
<td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkedu6" name="edu6" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="edu6" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>

 <tr>
            <td style="border-width: 0px" colspan="2" align="left">
             Detartraje supra gingival
</td>
<td style="border-width: 0px" align="left">
				
										 si <input type="radio"
											id="chkedu7" name="edu7" value="si" ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										no <input type="radio"
											name="edu7" value="no" checked="checked"> 
                                      
				
             </td>
          </tr>


  <tr>
			<td colspan="3" align="center">
				<div align='center' class='style11' id='cabecera2' >DESCRIPCI&Oacute;N DEL PLAN DE TRATAMIENTO</div>
           </td>
        </tr>
        
         <tr>
            <td style="border-width: 0px" colspan="3" align="center">

				<br></br><B>ACTIVIDADES DE PREVENCI&Oacute;N</B></td></tr>

<tr><td style="border-width: 0px" colspan="3" align="center">
<textarea id="odont_actividades_prevencion" cols="50" rows="5"></textarea> <br></br></td></tr>

<tr><td style="border-width: 0px" colspan="3" align="center">
               <B> ACTIVIDADES DE LIMITACI&Oacute;N DE DAÑO</B></td></tr>
<tr><td style="border-width: 0px" colspan="3" align="center">
<textarea id="odont_actividades_limitacion" cols="50" rows="5"></textarea> 
				
             </td>
          </tr>




<tr>
		<td colspan="3" align="center" >
		<div id="imagen_odont_bd" style="display: none" ></div>
		</td>
	</tr>

<tr>
<!-- botones para enviar y anular informe -->
				<td colspan="3" align="center"><input type="button" id="envio"
					value="Enviar Datos" onclick="enviar_informe()" />
				<input type="button" id="anular"
					value="Anular Informe" onclick="anular_informe()" /></td>
			</tr>
</table>

</div>
</div>
</div>
</td>

</tr>
</td>
</div>

</table>
<%}%>
</body>
</html>