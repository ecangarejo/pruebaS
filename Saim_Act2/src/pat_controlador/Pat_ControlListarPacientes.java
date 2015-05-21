/**
 * controlador: ControlListarPacientes se encuentra la forma en que se 
 * complementa datos del paciente en las diferentes parte de la historia clinica
 * se llenan los estudios que tiene pendiente,los formatos que tiene asignados
 * y datos relacionados con este.
 */
package pat_controlador;


import pat_metodo.MetodoCrearFormato;
import pat_metodo.MetodoEstudiosPorAsignar;
import pat_metodo.MetodoCrearPaciente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Controimg_area
 */
public class Pat_ControlListarPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
	String va=req.getParameter("valor");
	String CodFormato=req.getParameter("CodFormato");
	String CodPaciente=req.getParameter("CodPaciente");
	String Hora=req.getParameter("Hora");
	String Fecha=req.getParameter("Fecha");	
	String CodUsuario=req.getParameter("CodUsuario");
	String CodAsignacion=req.getParameter("CodAsignacion");
	MetodoCrearFormato mcf = new MetodoCrearFormato ();
	
	MetodoEstudiosPorAsignar mepa = new MetodoEstudiosPorAsignar();
	
	ResultSet rs=null;
	ResultSet rs1=null;
	ResultSet rs2=null;
	if(va.equals("1")){
		String CodOrden=req.getParameter("CodOrden");
		String CodigoArea="";
		String CodigoPregunta="";
		/**
		 * se guarda la relacion del formato con la admision y el paciente.... 
		 */
		if(CodFormato!=""){
			try {			
				String resultados="";
					mcf.RelacionFormatoAdmisionPaciente(CodFormato, CodPaciente, Hora, Fecha, CodUsuario);
					rs1=mcf.ObtenerAreaFormato(CodFormato,Fecha,Hora);					
						while(rs1.next()){							
							CodigoArea=rs1.getString(1);
							rs2=mcf.ObtenerPreguntasArea(CodigoArea);							
							while(rs2.next()){								
								resultados=rs2.getString(6);								
								String estado="0";
								CodigoPregunta=rs2.getString(4);
								String TipoPregunta=rs2.getString(2);
								if(TipoPregunta.equals("1")){
									/**si tipo pregunta es igual a un texto largo*/
									mcf.IngresarHistoria(CodPaciente, CodigoPregunta, resultados, estado, Fecha, Hora, CodUsuario, CodigoArea, CodFormato);
									
								}
								if(TipoPregunta.equals("2")){
									/**si tipo pregunta es igual a un tipo seleccion*/
									resultados="Seleccione";
									mcf.IngresarHistoria(CodPaciente, CodigoPregunta, resultados, estado, Fecha, Hora, CodUsuario, CodigoArea, CodFormato);
									
								}
								if(TipoPregunta.equals("3")){
									/**si tipo pregunta es igual a un texto corto */
									mcf.IngresarHistoria(CodPaciente, CodigoPregunta, resultados, estado, Fecha, Hora, CodUsuario, CodigoArea, CodFormato);
								}
							}
							rs2.getStatement().getConnection().close();
						}
						rs1.getStatement().getConnection().close();						
						out.print("Ingreso Exitoso.");
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(CodOrden!=""){
			mepa.asignar(CodOrden);
		}
		
	}
	if(va.equals("2")){	
		rs=mcf.ObtenerFormatosPaciente();
		out.print("<table border='1' width='100%' class='style2' ><tr><td><div align='center'>NOMBRE PACIENTE</div></td><td><div align='center'>NOMBRE ESTUDIO</div></td><td><div align='center'>HORA Y FECHA ASIGNACION</div></td><td><div align='center'>ASIGNADO POR</div></td></tr>");
		try {
			while(rs.next()){
				String FechaIni=rs.getString(3);
				String HoraIni=rs.getString(4);
				String dia,mes,anio=null; 
				String horas,minutos,segundos=null;
				  
				dia=FechaIni.substring(8,10);
				mes=FechaIni.substring(5,7);
				anio=FechaIni.substring(0,4);
				
				horas=HoraIni.substring(0,2);
				minutos =HoraIni.substring(3,5);
				segundos=HoraIni.substring(6,8);
				
				out.print("<tr><td>"+rs.getString(10)+" "+rs.getString(11)+"</td><td width='35%'><a  href='#' onclick='VerFormatosLlenar("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(12)+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.println("ERROR en Valor Igual 11 ControlFormatosPestanas ResultSet "+e);
			e.printStackTrace();
		}
	}
	if(va.equals("3")){
		String HoraFormato=req.getParameter("HoraFormato");
		String FechaFormato=req.getParameter("FechaFormato");
		
		String Tipo=req.getParameter("Tipo");
		/**
		 * se obtienen las preguntas del area llevando como parametro el codigo del area. 
		 */
		int contador=0;
			try {
				if(Tipo.equals("1")){
					rs1=mcf.ObtenerPreguntasAreaLlenoBR(FechaFormato, HoraFormato, CodPaciente,CodFormato);
					if(rs1.next()){
						out.print("<table width='100%' border='1' class='style6'><tr><td colspan='4' class='style11' id='cabecera2'><div align='center'>DATOS DEL PACIENTE </div></td></tr>");
						out.print("<tr><td width='13%'><div>Nombre Paciente </div></td><td width='29%'><div>"+rs1.getString(3)+" "+rs1.getString(4)+"</div></td><td width='9%'><div>Identificacion</div></td><td><div>"+rs1.getString(1)+"-"+rs1.getString(2)+"</div></td></tr></table>");
						/*****************************************************************************************/
						out.print("<table width='100%' border='1' class='style6'><tr><td width='9%'><div>Protocolo</div></td><td width='17%'><input name='txtProtocolo' type='text' id='txtProtocolo' size='30' maxlength='10' /></td> <td width='11%'><div>Tipo Especimen </div></td><td width='22%'><label><input name='txtTipoespecimen' type='text' id='txtTipoespecimen' size='40' maxlength='50' /></label></td>");
						out.print("<td width='12%'><div>Medico Tratante</div> </td><td width='29%'><input name='txtMedicoTratante' type='text' id='txtMedicoTratante' size='40' maxlength='40' /></td></tr><tr><td><div>Fecha Recibo </div></td><td><input name='txtFechaRecibo' type='text' id='txtFechaRecibo' /></td><td><div>Fecha Entrega</div></td><td><input name='txtFechaEntrega' type='text' id='txtFechaEntrega' size='30' maxlength='20' /></td>");
						out.print("<td><div>Diagnostico Clinico</div></td><td><label><textarea name='txtDiagClinico' id='txtDiagClinico' cols='40' rows='2'  ></textarea></label></td></tr><tr><td colspan='6'><div align='center'><input name='btnIngresar' type='button' value='     Ingresar     ' onClick='IngresarEstudioBR()' /></div></td></tr></table>");
						/**************************************************************************************/
					
						out.print("<table width='100%' border='1' class='style6'><tr><input name='txtCodAsignacion' type='hidden' id='txtCodAsignacion' value="+CodAsignacion+" ><td colspan='4' class='style11' id='cabecera2'><div align='center'></div></td></tr></table>");
					}
					rs1.getStatement().getConnection().close();
					
					out.print("<table border='0' width='100%' ><tr><td colspan='4'><div id='cabecera2' class='style11' align='center'>FORMATO DE BIOPSIA RENAL </div></td></tr>");
					out.print("<tr><td width='25%' class='style12'>DESCRIPCION MACROSCOPICA </td><td colspan='3'><textarea name='txtDesMacro' cols='65' rows='5' id='txtDesMacro'></textarea></td></tr>");
					out.print("<tr><td class='style12'>DESCRIPCION MICROSCOPICA   </td><td colspan='3'><textarea name='txtDescMicro1' cols='65' rows='5' id='txtDescMicro1'></textarea></td></tr>");
					out.print("<tr><td class='style12'>RESUMEN HISTORIA CLINICA </td><td colspan='3'><textarea name='txtDescMicro2' cols='65' rows='5' id='txtDescMicro2'></textarea></td></tr>");
					out.print("<tr><td colspan='2' class='style12'>ESTUDIO DE INMUNOFLUORESCENCIA DIRECTA RIÑON </td><td width='17%' class='style12'>&nbsp;</td><td width='29%'>&nbsp;</td></tr></table>");
					out.print("<table border='0' width='100%' ><tr><td colspan='4'><input name='txtObserLleno' type='text' id='txtObserLleno' style='width:100%'  value='Se observan de __ a __ glumerulos en las diferentes secciones.'></td></tr>");
					out.print("<tr><td width='11%'>ANTICUERPO</td><td width='10%'>RESULTADO</td><td width='14%'>INTENSIDAD</td><td width='65%'>PATRON</td></tr>");
					out.print("<tr><td>IgA</td><td><select name='RE1' id='RE1'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT1' type='text' id='IT1'></td><td><input name='PTR1' type='text' id='PTR1'></td></tr>");
					out.print("<tr><td>IgG</td><td><select name='RE2' id='RE2'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT2' type='text' id='IT2'></td><td><input name='PTR2' type='text' id='PTR2'></td></tr>");
					out.print("<tr><td>IgM</td><td><select name='RE3' id='RE3'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT3' type='text' id='IT3'></td><td><input name='PTR3' type='text' id='PTR3'></td></tr>");
					out.print("<tr><td>C3</td><td><select name='RE4' id='RE4'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT4' type='text' id='IT4'></td><td><input name='PTR4' type='text' id='PTR4'></td></tr>");
					out.print("<tr><td>C4</td><td><select name='RE5' id='RE5'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT5' type='text' id='IT5'></td><td><input name='PTR5' type='text' id='PTR5'></td></tr>");
					out.print("<tr><td>C1q</td><td><select name='RE6' id='RE6'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT6' type='text' id='IT6'></td><td><input name='PTR6' type='text' id='PTR6'></td></tr>");
					out.print("<tr><td>Cadenas Lamda </td><td><select name='RE7' id='RE7'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT7' type='text' id='IT7'></td><td><input name='PTR7' type='text' id='PTR7'></td></tr>");
					out.print("<tr><td>Cadenas Cappa </td><td><select name='RE8' id='RE8'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT8' type='text' id='IT8'></td><td><input name='PTR8' type='text' id='PTR8'></td></tr>");
					out.print("<tr><td>Albumina</td><td><select name='RE9' id='RE9'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT9' type='text' id='IT9'></td><td><input name='PTR9' type='text' id='PTR9'></td></tr>");
					out.print("<tr><td>Fribrinogeno</td><td><select name='RE10' id='RE10'><option value='Sel' selected='selected'>--</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT10' type='text' id='IT10'></td><td><input name='PTR10' type='text' id='PTR10'></td></tr>");
					out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
					out.print("<tr><td width='11%' class='style12'>DIAGNOSTICO</td><td colspan='3'><textarea name='txtDiagnostico' cols='65' rows='5' id='txtDiagnostico'></textarea></td></tr>");
					out.print("<tr><td class='style12'>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
					out.print("</table>");
					out.print("");
					out.print("");
					out.print("");
					out.print("");
					out.print("");
					
					
					
				}
				if(Tipo.equals("0")){
					rs1=mcf.ObtenerPreguntasAreaLleno(FechaFormato, HoraFormato, CodPaciente,CodFormato);
					if(rs1.next()){
						out.print("<table width='100%' border='1' class='style6'><tr><td colspan='4' class='style11' id='cabecera2'><div align='center'>DATOS DEL PACIENTE </div></td></tr>");
						out.print("<tr><td width='13%'><div>Nombre Paciente </div></td><td width='29%'><div>"+rs1.getString(9)+" "+rs1.getString(10)+"</div></td><td width='9%'><div>Identificacion</div></td><td><div>"+rs1.getString(7)+"-"+rs1.getString(8)+"</div></td></tr></table>");
						/*****************************************************************************************/
						out.print("<table width='100%' border='1' class='style6'><tr><td width='9%'><div>Protocolo</div></td><td width='17%'><input name='txtProtocolo' type='text' id='txtProtocolo' size='30' maxlength='10' /></td> <td width='11%'><div>Tipo Especimen </div></td><td width='22%'><label><input name='txtTipoespecimen' type='text' id='txtTipoespecimen' size='40' maxlength='50' /></label></td>");
						out.print("<td width='12%'><div>Medico Tratante</div> </td><td width='29%'><input name='txtMedicoTratante' type='text' id='txtMedicoTratante' size='40' maxlength='40' /></td></tr><tr><td><div>Fecha Recibo </div></td><td><input name='txtFechaRecibo' type='text' id='txtFechaRecibo' /></td><td><div>Fecha Entrega</div></td><td><input name='txtFechaEntrega' type='text' id='txtFechaEntrega' size='30' maxlength='20' /></td>");
						out.print("<td><div>Diagnostico Clinico</div></td><td><label><textarea name='txtDiagClinico' id='txtDiagClinico' cols='40' rows='2'  ></textarea></label></td></tr><tr><td colspan='6'><div align='center'><input name='btnIngresar' type='button' value='     Ingresar     ' onClick='IngresarEstudio()' /></div></td></tr></table>");
						/**************************************************************************************/
					
						out.print("<table width='100%' border='1' class='style6'><tr><input name='txtCodAsignacion' type='hidden' id='txtCodAsignacion' value="+CodAsignacion+" ><td colspan='4' class='style11' id='cabecera2'><div align='center'> ESTUDIO&gt;&gt; "+rs1.getString(11)+" </div></td></tr></table>");
					}
					rs1.getStatement().getConnection().close();
					rs=mcf.ObtenerPreguntasAreaLleno(FechaFormato, HoraFormato, CodPaciente,CodFormato);
					out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
					while(rs.next()){
						String CodTipoResp=rs.getString(3);
						out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
						if(rs.getString(2).equals("1")){
							/**
							 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
							 */
							out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onkeyup='ActualizarResultados()' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
						}
						contador=contador+1;
						if(rs.getString(2).equals("2")){
							/**
							 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
							 */
							rs1=mcf.OpcionesTipoRespuesta2(CodTipoResp);
							out.print("<td><select name='txtRespuesta' id='txtRespuesta' onchange='ActualizarResultados()'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
							while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(1)+"</option>");	
							}
							rs1.getStatement().getConnection().close();
							out.print("</select></td>");
						}
						if(rs.getString(2).equals("3")){
							/**
							 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto corto
							 */
							out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onkeyup='ActualizarResultados()' value="+rs.getString(5)+" > "+rs.getString(6)+"</td>");
						}
					
					}
					out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
					rs.getStatement().getConnection().close();
				}
				
			} catch (SQLException e) {
				System.out.println("Error ResultSet valor=1 "+e);
				e.printStackTrace();
			}
	}
	if(va.equals("4")){
		String Resul=req.getParameter("Resul");
		String CodResul=req.getParameter("CodResul");
		mcf.ActualizarResultados(Resul, CodResul);
	}
	
	if(va.equals("4.1")){
		//String CodAsignacion1=req.getParameter("CodAsignacion");
		String CodDatosComplementarios=req.getParameter("CodDatosComplementarios");
		String Tipoespecimen=req.getParameter("Tipoespecimen");
		String MedicoTratante=req.getParameter("MedicoTratante");
		String FechaRecibo=req.getParameter("FechaRecibo");
		String FechaEntrega=req.getParameter("FechaEntrega");
		String DiagClinico=req.getParameter("DiagClinico");
		String Resul=req.getParameter("Resul");
		String CodResul=req.getParameter("CodResul");
		String Protocolo=req.getParameter("Protocolo");
		mcf.ActualizarResultados(Resul, CodResul);
		mcf.ActualizarDatosComplementarios(FechaRecibo, FechaEntrega, Tipoespecimen, DiagClinico, MedicoTratante, Protocolo, CodDatosComplementarios);
	}
	
	
	if(va.equals("4.11")){
		String CodAsignacion1=req.getParameter("CodAsignacion");
		String CodDatosComplementarios=req.getParameter("CodDatosComplementarios");
		String Tipoespecimen=req.getParameter("Tipoespecimen");
		String MedicoTratante=req.getParameter("MedicoTratante");
		String FechaRecibo=req.getParameter("FechaRecibo");
		String FechaEntrega=req.getParameter("FechaEntrega");
		String DiagClinico=req.getParameter("DiagClinico");
		String Resul=req.getParameter("Resul");
		String CodResul=req.getParameter("CodResul");
		String Protocolo=req.getParameter("Protocolo");
		String CodUsu=req.getParameter("CodUsu");
		mcf.ActualizarResultados(Resul, CodResul);
		mcf.ActualizarDatosComplementarios(FechaRecibo, FechaEntrega, Tipoespecimen, DiagClinico, MedicoTratante, Protocolo, CodDatosComplementarios);
		MetodoCrearPaciente mcp=new MetodoCrearPaciente();
		String Est="1";
		mcp.ActualizarAprobarAsignacion(CodAsignacion1,Est,CodUsu);
	}
	
	
	if(va.equals("4.111")){
		String CodAsignacion1=req.getParameter("CodAsignacion");
		String CodDatosComplementarios=req.getParameter("CodDatosComplementarios");
		String Tipoespecimen=req.getParameter("Tipoespecimen");
		String MedicoTratante=req.getParameter("MedicoTratante");
		String FechaRecibo=req.getParameter("FechaRecibo");
		String FechaEntrega=req.getParameter("FechaEntrega");
		String DiagClinico=req.getParameter("DiagClinico");
		String Protocolo=req.getParameter("Protocolo");
		String CodUsu=req.getParameter("CodUsu");
		MetodoCrearPaciente mcp=new MetodoCrearPaciente();
		/*******************************************************************/
		String DesMacro=req.getParameter("DesMacro"); String DescMicro1=req.getParameter("DescMicro1");
		String DescMicro2=req.getParameter("DescMicro2"); String ObserLleno=req.getParameter("ObserLleno"); 
		
		String RE1=req.getParameter("RE1"); String RE2=req.getParameter("RE2"); 
		String RE3=req.getParameter("RE3"); String RE4=req.getParameter("RE4");
		String RE5=req.getParameter("RE5"); String RE6=req.getParameter("RE6");
		String RE7=req.getParameter("RE7"); String RE8=req.getParameter("RE8");
		String RE9=req.getParameter("RE9"); String RE10=req.getParameter("RE10"); 
		
		String IT1=req.getParameter("IT1"); String IT2=req.getParameter("IT2"); 
		String IT3=req.getParameter("IT3"); String IT4=req.getParameter("IT4"); 
		String IT5=req.getParameter("IT5"); String IT6=req.getParameter("IT6"); 
		String IT7=req.getParameter("IT7"); String IT8=req.getParameter("IT8"); 
		String IT9=req.getParameter("IT9"); String IT10=req.getParameter("IT10"); 
		
		String PTR1=req.getParameter("PTR1"); String PTR2=req.getParameter("PTR2"); 
		String PTR3=req.getParameter("PTR3"); String PTR4=req.getParameter("PTR4"); 
		String PTR5=req.getParameter("PTR5"); String PTR6=req.getParameter("PTR6"); 
		String PTR7=req.getParameter("PTR7"); String PTR8=req.getParameter("PTR8"); 
		String PTR9=req.getParameter("PTR9"); String PTR10=req.getParameter("PTR10"); 
		String Diagnostico=req.getParameter("Diagnostico");
		String CodBiopsia=req.getParameter("CodBiopsia");
		
		
		mcp.ActualizarBiopsia(CodBiopsia, DesMacro, DescMicro1, DescMicro2,
				ObserLleno, RE1, RE2, RE3, RE4, RE5, RE6, RE7, RE8, RE9, RE10,
				IT1, IT2, IT3, IT4, IT5, IT6, IT7, IT8, IT9, IT10, 
				PTR1, PTR2, PTR3, PTR4, PTR5, PTR6, PTR7, PTR8, PTR9, PTR10, 
				Diagnostico);
		
		/*******************************************************************/
		mcf.ActualizarDatosComplementarios(FechaRecibo, FechaEntrega, Tipoespecimen, DiagClinico, MedicoTratante, Protocolo, CodDatosComplementarios);
		
		String Est="1";
		mcp.ActualizarAprobarAsignacion(CodAsignacion1,Est,CodUsu);
	}
	
		
	}
}
