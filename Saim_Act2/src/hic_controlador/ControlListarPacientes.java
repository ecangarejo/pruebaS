/**
 * controlador: ControlListarPacientes se encuentra la forma en que se 
 * complementa datos del paciente en las diferentes parte de la historia clinica
 * se llenan los estudios que tiene pendiente,los formatos que tiene asignados
 * y datos relacionados con este.
 */
package hic_controlador;


import hic_metodo.MetodoIngresarResultados;
import hic_metodo.MetodoVerFormatos;

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
public class ControlListarPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	/**
	 * autocompletar de los formatos, salen todas las coincidencias de los formatos
	 * existentes en la base de datos.
	 */
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoVerFormatos mvf = new MetodoVerFormatos ();

	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){          

        try {
            rs =mvf.listarPacientesPorAtender(cad);
            String cadena ="";
            String nombre ="";
            cadena="[";
            while(rs.next()){
            	nombre=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
            	cadena = cadena+"'"+nombre+"|"+rs.getString(1)+"|"+rs.getString(5)+"'";
            	cadena = cadena +",";	
            }
            cadena = cadena+"]";
            res.getWriter().write(cadena);
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.getMessage();
			e.printStackTrace();
			}

    }
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va=req.getParameter("valor");
		String CodFormato=req.getParameter("CodFormato");
		String CodAdmision=req.getParameter("CodAdmision");
		String CodPaciente=req.getParameter("CodPaciente");
		String TipoEstudio=req.getParameter("TipoEstudio");
		String CodEstudio=req.getParameter("CodEstudio");
		String CodArea=req.getParameter("CodArea");
		String CodPac=req.getParameter("CodPac");
		String hora=req.getParameter("hora");
		String fecha=req.getParameter("fecha");
		//String Hora=req.getParameter("Hora");
		//String Fecha=req.getParameter("Fecha");
		
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
		String CodUsuario=req.getParameter("CodUsuario");
		String CodigoFormatoPaciente=req.getParameter("CodigoFormatoPaciente");
		MetodoVerFormatos mvf = new MetodoVerFormatos ();
		MetodoIngresarResultados mir = new MetodoIngresarResultados();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		//ResultSet rs5=null;
		////ResultSet rs6=null;
		//ResultSet rs7=null;
		//ResultSet rs8=null;
	
		
		if(va.equals("0")){
			/**
			 * se obtienen las admisiones activas desde la base de datos. 
			 */
			
			String NomPaciente="";
			String Paciente="";
			
			try {
				rs=mvf.PacientesPorAtender();
				out.print("<table width='97%' border='1'>");
				while(rs.next()){
					NomPaciente=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
					Paciente=NomPaciente+"|"+rs.getString(1)+"|"+rs.getString(5);
					
					if(Paciente==null){
						out.print("No hay Pacientes Hospitalizados!!!");
					}else{
					out.print("<tr><td><a href='#'onclick='AsignarFormatos("+rs.getString(5)+","+rs.getString(1)+")'>"+NomPaciente+"</a></td></tr>");
					}
				}
				out.print("</table>");
	
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				out.println("ERROR en ControlListarPacientes.java resultSet 1 "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("1")){
			String CodigoArea="";
			String CodigoPregunta="";
			String TipoFormato="";
			/**
			 * se guarda la relacion del formato con la admision y el paciente.... 
			 */
			if(CodFormato!=""){
				rs3=mvf.ObtenerTipoFormato(CodFormato);
				try {
					if(rs3.next()){
						TipoFormato=rs3.getString(1);
					}
					rs3.getStatement().getConnection().close();
					if(!TipoFormato.equals("1")){
						String resultados="";
						mvf.RelacionFormatoAdmisionPaciente(CodFormato, CodAdmision, CodPaciente, Hora+"", Fecha+"", CodUsuario);
						rs1=mvf.ObtenerAreaFormato(CodFormato,Fecha+"",Hora+"");
						
							while(rs1.next()){
								
								CodigoArea=rs1.getString(1);
								rs2=mvf.ObtenerPreguntasArea(CodigoArea);
								
								while(rs2.next()){
									
									resultados=rs2.getString(6);
									
									String estado="0";
									CodigoPregunta=rs2.getString(4);
									String TipoPregunta=rs2.getString(2);
									if(TipoPregunta.equals("1")){
										/**si tipo pregunta es igual a un texto largo*/
										mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
										
									}
									if(TipoPregunta.equals("2")){
										/**si tipo pregunta es igual a un tipo seleccion*/
										resultados="Seleccione";
										mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
										
									}
									if(TipoPregunta.equals("3")){
										/**si tipo pregunta es igual a un texto corto */
										mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
									}
									if(TipoPregunta.equals("4")){
										/**si tipo pregunta es igual a un autocompletar de tipo diagnostico */
										mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
									}
									if(TipoPregunta.equals("5")){
										/**si tipo pregunta es igual a un autocompletar de tipo diagnostico varios */
										mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
									}
								}
								rs2.getStatement().getConnection().close();
							}
							rs1.getStatement().getConnection().close();
							/*String TipoUsuario="";
							String CodEncabezadoFact="";
							String NombreFormato="";
							String CodEps="";
							rs3=mvf.ObtenerTipoUsuario(CodUsuario);
							if(rs3.next()){
								TipoUsuario=rs3.getString(1);
							}
							rs3.getStatement().getConnection().close();
							rs6=mvf.ObtenerNombreFormato(CodFormato);
							if(rs6.next()){
								NombreFormato=rs6.getString(1);
							}
							rs6.getStatement().getConnection().close();
							if((TipoUsuario.equals("Especialista"))&&(NombreFormato.equals("EVOLUCION MEDICA"))){
								rs4=mvf.ObtenerEncabezadoFactura(CodAdmision);
								if(rs4.next()){
									CodEncabezadoFact=rs4.getString(1);
									CodEps=rs4.getString(2);
								}
								rs4.getStatement().getConnection().close();
								rs5=mvf.ObtenerPrograma(CodEps);
								if(rs5.next()){
									String cantidad="1";
									mvf.InsertarDetalleFactura(Fecha, Hora, rs5.getString(2), rs5.getString(1), rs5.getString(3), rs5.getString(4), fecha, cantidad, rs5.getString(5), CodUsuario, CodEncabezadoFact, CodUsuario);
								}
								rs5.getStatement().getConnection().close();
							}*/
							
					}
					if(TipoFormato.equals("1")){
						rs4=mvf.BuscarRelacionFormatoAdmision1(CodFormato, CodAdmision);
						if(rs4.next()){
							out.print("No Se Puede Asignar Otro Formato de Este Tipo.");
						}
						
						else{
							//hacer incersion normal.
							String resultados="";
							mvf.RelacionFormatoAdmisionPaciente(CodFormato, CodAdmision, CodPaciente, Hora+"", Fecha+"", CodUsuario);
							rs1=mvf.ObtenerAreaFormato(CodFormato,Fecha+"",Hora+"");
							
								while(rs1.next()){
									
									CodigoArea=rs1.getString(1);
									rs2=mvf.ObtenerPreguntasArea(CodigoArea);
									
									while(rs2.next()){
										
										resultados=rs2.getString(6);
										
										String estado="0";
										CodigoPregunta=rs2.getString(4);
										String TipoPregunta=rs2.getString(2);
										if(TipoPregunta.equals("1")){
											/**si tipo pregunta es igual a un texto largo*/
											mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
											
										}
										if(TipoPregunta.equals("2")){
											/**si tipo pregunta es igual a un tipo seleccion*/
											resultados="Seleccione";
											mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
											
										}
										if(TipoPregunta.equals("3")){
											/**si tipo pregunta es igual a un texto corto */
											mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
										}
										if(TipoPregunta.equals("4")){
											/**si tipo pregunta es igual a un autocompletar de tipo diagnostico */
											mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
										}
										if(TipoPregunta.equals("5")){
											/**si tipo pregunta es igual a un autocompletar de tipo diagnostico varios */
											mir.IngresarHistoria(CodPaciente, CodAdmision, CodigoPregunta, resultados, estado, Fecha+"", Hora+"", CodUsuario, CodigoArea, CodFormato);
										}
									}
									rs2.getStatement().getConnection().close();
								}
								rs1.getStatement().getConnection().close();
								
						}
						rs4.getStatement().getConnection().close();
					}
				
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			/**
			 * una ves guardada la relacion se procede a mostrar los formatos - 
			 */
			rs=mvf.ObtenerFormatosPaciente(CodPaciente, CodAdmision);
			out.print("<table width='100%' border='1'>");
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
					
					out.print("<tr><td><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+")'>"+rs.getString(2)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("ERROR en Valor Igual 1 ResultSet "+e);
				e.printStackTrace();
			}
			}
		
		if(va.equals("2")){
			/**
			 * se obtienen los Pacientes  desde la base de datos. 
			 */
			String NomPaciente="";
			String Paciente="";
			try {
				rs=mvf.Pacientes();
				out.print("<table width='97%' border='1'>");
				while(rs.next()){
					NomPaciente=rs.getString(5)+" "+rs.getString(3)+" "+rs.getString(4);
					Paciente=NomPaciente+"|"+rs.getString(2)+"|"+rs.getString(1);
					
					out.print("<tr><td><a  href='#'onclick='PacienteOrdenLab("+rs.getString(6)+")'>"+Paciente+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				System.out.println("ERROR en resultSet 1 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("3")){
			/**
			 * se obtienen los Datos del Paciente seleccionado  desde la base de datos. 
			 */
			String NomPaciente="";
			String Genero="";
			String TipoDoc="";
			String NumDoc="";
			String Edad="";
			String Documento="";
			try {
				rs=mvf.ObtenerPaciente(CodPac);
				out.print("<table width='70%' border='1' cellspacing='0'><tr><td colspan='4' bgcolor='#01426E'><div align='center' class='style1'>Datos del Paciente </div></td></tr>");
				if(rs.next()){
					NomPaciente=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
					Genero=rs.getString(4);
					TipoDoc=rs.getString(5);
					NumDoc=rs.getString(6);
					Edad=rs.getString(7);
					Documento=TipoDoc+" "+NumDoc;
					out.print("<tr><td width='97' >N&ordm; Documento </td><td width='113' >"+Documento+"</td><td width='121' >Nombre Completo </td><td width='414' >"+NomPaciente+"</td></tr><tr><td>Edad</td><td>"+Edad+"</td><td>Sexo</td><td>"+Genero+"</td></tr>");
				}
				out.print("<tr><td colspan='2'><label><input name='radiobutton' type='radio' value='radiobutton' id='6' onclick='Radio()' />Laboratorio</label></td><td colspan='2'><label><input name='radiobutton' type='radio' value='radiobutton' id='7' onclick='Radio()' />Imagenes Diagnosticas</label></td></tr><tr><td colspan='4'><div id='opciones'></div></td></tr></table>");
	
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				System.out.println("ERROR en resultSet 1 "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("4")){
			int contador=0;
			rs=mvf.ObtenerExamenesArea(CodArea);
			out.print("<table border='1' width='100%'><tr tyle='font-size:9px'><td colspan='2'>NOMBRE DEL EXAMEN </td><td align='center'>SELECCIONE<label><input type='checkbox'  name='all' id='all' onclick='checkAll();'  /></label></td></tr>");
			try {
				while(rs.next()){
					out.print("<tr style='font-size:9px'><td colspan='2'>"+rs.getString(2)+"</td><td width='154' align='center'><label><input type='checkbox' name='chkvalidar' id='ca' value='"+rs.getString(1)+"' /><input type='hidden' name='txtTipo' id='txtTipo' value='"+rs.getString(3)+"' />  </label></td></tr>");
					contador=contador+1;
				}
				out.print("<tr><td colspan='2' align='right'><label><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='Asignar()'></label></td><td align='center'><label><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='finalizar()'></label></td></tr></table><input name='yo' id='yo' type='hidden' value="+contador+">");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("5")){
			mvf.RelacionPacienteLaboratorios(CodEstudio, TipoEstudio, CodPaciente, hora, fecha);
		}
		
		if(va.equals("6")){
			/**
			 * si el examen es de laboratorio
			 */
			rs1=mvf.ObtenerAreaExamenes();
			out.print("<table width='100%' border='1'><tr tyle='font-size:9px'><td width='190'>SELECCIONE GRUPO    </td><td width='563'><select name='cmbgrupos' id='cmbgrupos' onchange='VerExamenes()'><option selected='selected'>SELECCIONE</option>");
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option> ");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</select></td></tr><tr tyle='font-size:9px'><td colspan='2'><div id='examenes'></div></td></tr></table>");
			
		}
		
		if(va.equals("7")){
			/**
			 * si el examen es de imagenologia
			 */
			out.print("<table width='100%' border='1'><tr><td width='197'>DATOS CLINICOS </td><td><textarea name='txtdatosClinicos' cols='60' rows='4' id='txtdatosClinicos'></textarea></td></tr><tr><td height='27'>NOMBRE DEL ESTUDIO  </td><td><input name='txtnomexam' type='text' id='txtnomexam' size='85' onkeyup='autocompleta_nombre()' /><input name='txtcodexamen' type='hidden' id='txtcodexamen' /></td></tr><tr><td height='26'><label><input name='chkportatil' type='checkbox' id='chkportatil' value='(PORTATIL)' />Portatil</label></td><td><div id='sugerencias1'></div></td></tr><tr><td height='24'>&nbsp;</td><td><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='enviar()' /></td></tr><tr><td colspan='4'></td></tr></table>");
		}
		if(va.equals("8")){
			try {
				rs=mvf.ObtenerHoraFechaFormato(CodigoFormatoPaciente);
				while(rs.next()){
					String FechaR=rs.getString(1);
					String HoraR=rs.getString(2);
					String CodAdm=rs.getString(3);
					mvf.OmitirRegistrosFormato(FechaR, HoraR,CodAdm);
				}
				mvf.OmitirFormatoPaciente(CodigoFormatoPaciente);
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error Va=8 "+e);
				e.printStackTrace();
			}
		}
		
			
	}
}
