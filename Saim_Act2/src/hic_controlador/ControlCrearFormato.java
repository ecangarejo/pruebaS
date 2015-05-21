/**
 * controlador: ControlCrearFormato se encuentra la forma en que son 
 * creadas los formatos.
 */
package hic_controlador;

import hic_metodo.MetodoCrearFormato;
import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agm_metodo.MetodoAsignarCita;

public class ControlCrearFormato extends HttpServlet {
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
		String tipo=req.getParameter("tipo");
		String va = req.getParameter("valor");
		String pregunta = req.getParameter("pregunta");
		String respuesta = req.getParameter("respuesta");
		String NumDocumento=req.getParameter("NumDocumento");
		String TipoDoc=req.getParameter("TipoDoc");
		MetodoCrearFormato mcf = new MetodoCrearFormato();
		ResultSet rs=null;	
		ResultSet rs1=null;
		if(va.equals("0")){
			/**
			 * Aqui se llena el formulario apenas se carga  hic_CrearItems.jsp
			 */
			out.println("<table width='100%' border='0' class='style6' align='center'><tr><td align='center' valign='middle' id='cabecera2' colspan='4'> <div align='center' class='style11' >CREAR CONDICIONES </div></td></tr><tr><td colspan='4'  align='center'><span >ESCRIBA LA CONDICION </span><br><textarea name='txtArea' cols='40' id='txtArea' onKeyUp='this.value=this.value.toUpperCase();'></textarea><span class='style8'>*</span></td></tr><tr ><td height='22' colspan='4'><div align='center'>SELECCIONE EL TIPO DE RESPUESTA </div>        </td><BR /></tr><tr ><td width='34%' height='21' align='center'><label><input name='radiobutton' type='radio' value='radiobutton' onclick='getCheckedRadio()' id='1' /><label>TEXTO LARGO </label></td><td colspan='2' align='center'><label><input name='radiobutton' type='radio' value='radiobutton' onclick='getCheckedRadio()' id='5' />TEXTO CORTO </label></td><td width='32%' align='center'><label><input name='radiobutton' type='radio' value='radiobutton' onclick='getCheckedRadio()' id='2' />SELECCION</label></td></tr><tr><td colspan='4' ><div id='cambio'></div></td></tr></table>");
		}
		if(va.equals("1")){
			/**
			 * Aqui se llena el formulario si la eleccion es si es de tipo texto largo este se carga  hic_CrearItems.jsp
			 */
			out.println("<tr ><td height='21' colspan='5' align='center'> <div align='center' ><input name='btnTexto' class='boton4' type='button' id='btnTexto' value='Crear' onClick='CrearTexto()' /></div></td></tr>");
		}
		if(va.equals("2")){
			/**
			 * Aqui se llena el formulario si la eleccion es si es de tipo seleccion este se carga  hic_CrearItems.jsp
			 */
			out.println("<table border='0'class='style6' width='100%'><tr ><td height='23' colspan='2'>A&ntilde;adir Respuesta...<input name='btnOtro' class='boton4' type='button' id='btnOtro' value='+' onclick='insertarFila(0)' /></td></tr><tbody id='tbody'><tr ><td height='23'><input name='txtNomSeleccion' type='text' id='txtNomSeleccion'></td><td >&nbsp;</td></tr><tr ><td width='31%' height='23'><label><input name='btnCrear' type='button' class='boton4' id='btnCrear' value='Crear' onClick='buscardatos()'></label></td><td width='60%' ><label></label></td></tr></tbody></table>");
		}
		if(va.equals("5")){
			/**
			 * Aqui se llena el formulario si la eleccion es si es de tipo texto corto este se carga  hic_CrearItems.jsp
			 */
			out.println("<tr ><td height='21' colspan='5' align='center'> <div align='center' ><input name='btnTexto' class='boton4' type='button' id='btnTexto' value='Crear' onClick='CrearTextoCorto()' /></div></td></tr>");
		}
		if(va.equals("3")){
			/**
			 * Aqui se guarda en la base de datos el nombre de la pregunta si es de tipo texto largo se guarda como tipo (1),
			 * si es de tipo seleccion se guarda como tipo (2) y si es de tipo texto largo se guarda tipo (3)
			 */
			String preg=null;
			rs=mcf.ObtenerCodigoPregunta(pregunta);
			try {
				if(rs.next()){
					preg=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(preg==null){
				mcf.CrearPregunta(pregunta, tipo);
				out.println("Pregunta Creada Con Exito.");
			}else{
				out.println("Ya Existe Este Nombre.");
				out.println("Por Favor Cambielo de Nombre.");
			}
			
		}
		if(va.equals("4")){
			/**
			 * Aqui se guarda en la base de datos todas las posibles respuestas de la pregunta
			 */
			
			String codPregunta="";
			String codRespuesta="";
			String tipoPregunta="";
			rs=mcf.ObtenerCodigoPregunta(pregunta);
			try {
				if(rs.next()){
					codPregunta=rs.getString(1);
					tipoPregunta=rs.getString(3);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error Cuando Consulta El Codigo De La Pregunta "+e);
			}
			
			if(tipoPregunta.equals("2")){
				/**
				 * si el tipo de respuesta es igual a 2 osea (cerrada) se procede a 
				 * ingresar las opciones de esta tipo de respuesta
				 */
			mcf.CrearRespuesta(respuesta);
			rs1=mcf.ObtenerCodigoRespuesta(respuesta);
			try {
				while(rs1.next()){
					codRespuesta=rs1.getString(1);
				}				
				rs1.getStatement().getConnection().close();
			} catch (SQLException ex) {
				System.out.println("Error Cuando Consulta El Codigo De La Respuesta "+ex);
			}
			/**
			 * se ingresa la relacion de la pregunta cerrada con las respuesta.
			 */
			mcf.RelacionPreguntaRespuesta(codPregunta, codRespuesta);
			}
		}
		
		if(va.equals("pre")){			
			rs=mcf.ObtenerFormatosPreingreso(NumDocumento, TipoDoc);
			rs1=mcf.DatosPaciente(NumDocumento, TipoDoc);			
			try {
				if(rs1.next()){
					out.print("<table width='100%' border='1'>" +
							"<tr>" +
							"<td>Nombre Paciente </td>" +
							"<td>"+rs1.getString(1)+"</td>" +
									"<td>Numero Documento </td>" +
									"<td>"+rs1.getString(2)+"</td>" +
									"<td>Entidad</td>" +
									"<td>"+rs1.getString(5)+"</td>" +
									"<td>Fecha de Nacimiento</td>" +
									"<td>"+rs1.getString(3)+" Edad:"+rs1.getString(4)+" Años</td>" +
											"</tr>" +
											"<tr id='cabecera2' class='style11'>" +
											"<td colspan='8' >&nbsp;</td>" +
											"</tr>" +
											"</table>");
				}
				rs1.getStatement().getConnection().close();
				out.print("<table width='100%' border='1'><tr><td width='42%'><div align='center'>Nombre Formato </div></td><td width='13%'><div align='center'> Hora y Fecha</div></td><td width='30%'><div align='center'>Usuario</div></td><td width='15%'><div align='center'>Accion Formato</div></td></tr>");
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
					out.print("<tr><td><div>"+rs.getString(2)+"</div></td><td><div>"+rs.getString(3)+" / "+rs.getString(4)+"</div></td><td><div>"+rs.getString(5)+" "+rs.getString(6)+"</div></td><td><div><img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormatoPreingreso("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+")' style='cursor:pointer;' /></div></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("epi")){
					
			try {
				
				rs1=mcf.DatosPaciente(NumDocumento, TipoDoc);	
				if(rs1.next()){
					out.print("<table width='100%' border='1'>" +
							"<tr>" +
							"<td>Nombre Paciente </td>" +
							"<td>"+rs1.getString(1)+"</td>" +
									"<td>Numero Documento </td>" +
									"<td>"+rs1.getString(2)+"</td>" +
									"<td>Entidad</td>" +
									"<td>"+rs1.getString(5)+"</td>" +
									"<td>Fecha de Nacimiento</td>" +
									"<td>"+rs1.getString(3)+" Edad:"+rs1.getString(4)+" Años</td>" +
											"</tr>" +
											"<tr id='cabecera2' class='style11'>" +
											"<td colspan='8' >&nbsp;</td>" +
											"</tr>" +
											"</table>");
				}
				rs1.getStatement().getConnection().close();
				out.print("<table width='100%' border='1'><tr><td width='42%'><div align='center'>Nombre Formato </div></td><td width='13%'><div align='center'> Hora y Fecha</div></td><td width='30%'><div align='center'>Usuario</div></td><td width='15%'><div align='center'>Accion Formato</div></td></tr>");
				rs=mcf.ObtenerEpicrisis(NumDocumento, TipoDoc);
				while(rs.next()){			
					out.print("<tr><td><div>EPICRISIS "+rs.getString(2)+"</div></td><td><div>"+rs.getString(3)+"</div></td><td><div>"+rs.getString(4)+"</div></td><td><div><img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirEpicrisis("+rs.getString(1)+")' style='cursor:pointer;' /></div></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("qx")){
			
			try {
				
				rs1=mcf.DatosPaciente(NumDocumento, TipoDoc);	
				if(rs1.next()){
					out.print("<table width='100%' border='1'>" +
							"<tr>" +
							"<td>Nombre Paciente </td>" +
							"<td>"+rs1.getString(1)+"</td>" +
									"<td>Numero Documento </td>" +
									"<td>"+rs1.getString(2)+"</td>" +
									"<td>Entidad</td>" +
									"<td>"+rs1.getString(5)+"</td>" +
									"<td>Fecha de Nacimiento</td>" +
									"<td>"+rs1.getString(3)+" Edad:"+rs1.getString(4)+" Años</td>" +
											"</tr>" +
											"<tr id='cabecera2' class='style11'>" +
											"<td colspan='8' >&nbsp;</td>" +
											"</tr>" +
											"</table>");
				}
				rs1.getStatement().getConnection().close();
				out.print("<table width='100%' border='1'><tr><td width='42%'><div align='center'>Nombre Formato </div></td><td width='13%'><div align='center'>Fecha y Hora</div></td><td width='30%'><div align='center'>Usuario</div></td><td width='15%'><div align='center'>Accion Formato</div></td></tr>");
				rs=mcf.ObtenerDescripcion(NumDocumento, TipoDoc);
				while(rs.next()){			
					out.print("<tr><td><div>DESCRIPCION QX  "+rs.getString(1)+"</div></td><td><div>"+rs.getString(2)+" / "+rs.getString(3)+"</div></td><td><div>"+rs.getString(5)+" "+rs.getString(4)+"</div></td><td><div><img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirDescripcion("+rs.getString(1)+")' style='cursor:pointer;' /></div></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		if(va.equals("hos")){
			rs=mcf.ObtenerFormatosHospitalizacion(NumDocumento, TipoDoc);
			rs1=mcf.DatosPaciente(NumDocumento, TipoDoc);			
			try {
				if(rs1.next()){
					out.print("<table width='100%' border='1'>" +
							"<tr>" +
							"<td>Nombre Paciente </td>" +
							"<td>"+rs1.getString(1)+"</td>" +
									"<td>Numero Documento </td>" +
									"<td>"+rs1.getString(2)+"</td>" +
									"<td>Entidad</td>" +
									"<td>"+rs1.getString(5)+"</td>" +
									"<td>Fecha de Nacimiento</td>" +
									"<td>"+rs1.getString(3)+" Edad:"+rs1.getString(4)+" Años</td>" +
											"</tr>" +
											"<tr id='cabecera2' class='style11'>" +
											"<td colspan='8' >&nbsp;</td>" +
											"</tr>" +
											"</table>");
				}
				rs1.getStatement().getConnection().close();
				out.print("<table width='100%' border='1'><tr><td width='42%'><div align='center'>Nombre Formato </div></td><td width='13%'><div align='center'> Hora y Fecha</div></td><td width='30%'><div align='center'>Usuario</div></td><td width='15%'><div align='center'>Accion Formato</div></td></tr>");
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
					out.print("<tr><td><div>"+rs.getString(2)+"</div></td><td><div>"+rs.getString(3)+" / "+rs.getString(4)+"</div></td><td><div>"+rs.getString(5)+" "+rs.getString(6)+"</div></td><td><div><img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormatoHospitalizacion("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(14)+","+rs.getString(15)+")' style='cursor:pointer;' /></div></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("cex")){
			rs=mcf.ObtenerFormatosCexterna(NumDocumento, TipoDoc);
			rs1=mcf.DatosPaciente(NumDocumento, TipoDoc);			
			try {
				if(rs1.next()){
					out.print("<table width='100%' border='1'>" +
							"<tr>" +
							"<td>Nombre Paciente </td>" +
							"<td>"+rs1.getString(1)+"</td>" +
									"<td>Numero Documento </td>" +
									"<td>"+rs1.getString(2)+"</td>" +
									"<td>Entidad</td>" +
									"<td>"+rs1.getString(5)+"</td>" +
									"<td>Fecha de Nacimiento</td>" +
									"<td>"+rs1.getString(3)+" Edad:"+rs1.getString(4)+" Años</td>" +
											"</tr>" +
											"<tr id='cabecera2' class='style11'>" +
											"<td colspan='8' >&nbsp;</td>" +
											"</tr>" +
											"</table>");
				}
				rs1.getStatement().getConnection().close();
				out.print("<table width='100%' border='1'><tr><td width='42%'><div align='center'>Nombre Formato </div></td><td width='13%'><div align='center'> Hora y Fecha</div></td><td width='30%'><div align='center'>Usuario</div></td><td width='15%'><div align='center'>Accion Formato</div></td></tr>");
				while(rs.next()){
					String FechaIni=rs.getString(1);
					String HoraIni=rs.getString(2);
					String dia,mes,anio=null; 
					String horas,minutos,segundos=null;
					  
					dia=FechaIni.substring(8,10);
					mes=FechaIni.substring(5,7);
					anio=FechaIni.substring(0,4);
					
					horas=HoraIni.substring(0,2);
					minutos =HoraIni.substring(3,5);
					segundos=HoraIni.substring(6,8);
					out.print("<tr><td><div>"+rs.getString(6)+"</div></td><td><div>"+rs.getString(1)+" / "+rs.getString(2)+"</div></td><td><div>"+rs.getString(8)+"</div></td><td><div><img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormatoCexterna("+rs.getString(4)+","+rs.getString(3)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(5)+")' style='cursor:pointer;' /></div></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(va.equals("cor")){
			try {
				rs1=mcf.DatosPaciente(NumDocumento, TipoDoc);
				if(rs1.next()){
					out.print("<table width='100%' border='1'>" +
							"<tr>" +
							"<td>Nombre Paciente </td>" +
							"<td>"+rs1.getString(1)+"</td>" +
									"<td>Numero Documento </td>" +
									"<td>"+rs1.getString(2)+"</td>" +
									"<td>Entidad</td>" +
									"<td>"+rs1.getString(5)+"</td>" +
									"<td>Fecha de Nacimiento</td>" +
									"<td>"+rs1.getString(3)+" Edad:"+rs1.getString(4)+" Años</td>" +
											"</tr>" +
											"<tr id='cabecera2' class='style11'>" +
											"<td colspan='8' >&nbsp;</td>" +
											"</tr>" +
											"</table>");
				}
				rs1.getStatement().getConnection().close();
				rs=mcf.ObtenerOrdenesMedicas(NumDocumento, TipoDoc);
				out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' id='cabecera2' class='style11' >RESUMEN ORDENES MEDICAS </div></td></tr>");
				out.print("<tr><td width='27%'><div align='center'>Fecha y Hora </div></td><td width='26%'><div align='center'>Usuario</div></td><td width='23%'><div align='center'>Profesion</div></td><td width='24%'><div align='center'>Tipo Orden</div></td></tr>");
				while(rs.next()){
					String Tipo=rs.getString(5);
					if(Tipo.equals("1")){
						/**1=LABORATORIO**/
						out.print("<tr><td><a href='#' onclick='ReportLabora("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>LABORATORIO</td></tr>");
					}
					
					if(Tipo.equals("2")){
						/**2=IMAGENOLOGIA**/
						out.print("<tr><td><a href='#' onclick='ReportImage("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>IMAGENOLOGIA</td></tr>");
					}
					
					if(Tipo.equals("3")){
						/**3=MEDICAMENTOS**/
						out.print("<tr><td><a href='#' onclick='ReportMedica("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>MEDICAMENTOS e INSUMOS</td></tr>");
					}
					
					if(Tipo.equals("4")){
						/**4=GENERAL**/
						out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN GENERAL</td></tr>");
					}
					if(Tipo.equals("5")){
						/**5=Procedimientos**/
						out.print("<tr><td><a href='#' onclick='ReportesOrdenProcedimiento("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN DE PROCEDIMIENTOS</td></tr>");
					}
					if(Tipo.equals("6")){
						//6=ORDEN DE PATOLOGIA
						System.out.println("entro a ver orden pat");
						out.print("<tr><td><a href='#' onclick='mostrarOrdenPat("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN DE PATOLOGIA</td></tr>");
					}
					
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("corex")){
			MetodoAsignarCita mac = new MetodoAsignarCita();
			String CodPac="";
			try {
				rs1=mcf.DatosPaciente(NumDocumento, TipoDoc);
				if(rs1.next()){
					CodPac=rs1.getString(6);
					out.print("<table width='100%' border='1'>" +
							"<tr>" +
							"<td>Nombre Paciente </td>" +
							"<td>"+rs1.getString(1)+"</td>" +
									"<td>Numero Documento </td>" +
									"<td>"+rs1.getString(2)+"</td>" +
									"<td>Entidad</td>" +
									"<td>"+rs1.getString(5)+"</td>" +
									"<td>Fecha de Nacimiento</td>" +
									"<td>"+rs1.getString(3)+" Edad:"+rs1.getString(4)+" Años</td>" +
											"</tr>" +
											"<tr id='cabecera2' class='style11'>" +
											"<td colspan='8' >&nbsp;</td>" +
											"</tr>" +
											"</table>");
				}
				rs1.getStatement().getConnection().close();
				
				rs=mac.FormulacionesPacientesCE(CodPac);
				out.print("<table width='100%' border='1'><tr><td width='50%'>Fecha Formulacion</td><td width='50%' >Tipo Formulacion</td></tr>");
				while(rs.next()){
					if(rs.getString(4).equals("1")){
						out.print("<tr><td><a href='#' onclick='ImprimirEstudiosCE("+rs.getString(2)+")'>"+rs.getString(1)+"</a></td><td>"+rs.getString(3)+"</td></tr>");
					}
						
					if(rs.getString(4).equals("2")){
						out.print("<tr><td><a href='#' onclick='ReporteFormulacionCE("+rs.getString(2)+")'>"+rs.getString(1)+"</a></td><td>"+rs.getString(3)+"</td></tr>");
					}
				}
				rs.getStatement().getConnection().close();				
				out.print("</table>");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
}
