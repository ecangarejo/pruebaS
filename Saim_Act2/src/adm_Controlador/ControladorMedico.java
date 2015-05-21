/**
 * NOMBRE DE LA CLASE: ControladorMedico
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario para 
 * 				generar un formato que va a ser visto solo por el medico. 				
 */
package adm_Controlador;


import hic_metodo.MetodoIngresarResultados;
import hic_metodo.MetodoLaboratoriosHistoria;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import fact_metodo.MetodoSeguimientoAdmision;


import adm_logica.MetodoPreingreso;

import java.sql.*;


public class ControladorMedico extends HttpServlet {


	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		String va=req.getParameter("valor");
		String CodPac = req.getParameter("CodPac");
		String CedPac=req.getParameter("CedPac");
		String CodAdmCola=req.getParameter("CodAdmCola");	
		String CodFormato=req.getParameter("CodFormato");
		String hora=req.getParameter("hora");
		String fecha=req.getParameter("fecha");
		String usuario=req.getParameter("usuario");
		String FechaFormato=req.getParameter("FechaFormato");
		String HoraFormato=req.getParameter("HoraFormato");
		String CodArea=req.getParameter("CodArea");
		String CodDiagnostico=req.getParameter("CodDiagnostico");
		String CodExamen=req.getParameter("CodExamen");
		String Diagnos= req.getParameter("Diagnos");
		String Alergia=req.getParameter("Alergia");
		String Reaccion=req.getParameter("Reaccion");
		String CodUsu=req.getParameter("CodUsu");
		String DiagPrinc=req.getParameter("DiagPrinc");
		
		String Familiar=req.getParameter("familiar");
		String Observacion=req.getParameter("ObserFami");
		String CodCIE_fk=req.getParameter("Diagnostico");
		
		String ObservacionTx=req.getParameter("ObservacionTx");
		String tipoTx=req.getParameter("tipoTx");
		
		String Cantidad=req.getParameter("Cantidad");
		String FechaTransfucion=req.getParameter("FechaTransfucion");
		String ObservacionTr=req.getParameter("ObservacionTr");
		String transfucion=req.getParameter("transfucion");
		//,,
		String codigoMed=req.getParameter("codigoMed");
		String codigoQx=req.getParameter("codigoQx");
		String fechaQx=req.getParameter("fechaQx");
		String observacion=req.getParameter("observacion");
		String CodigoUsuario=req.getParameter("CodigoUsuario");
		//String cedula=req.getParameter("cedula");
		
		String TipoSalida=req.getParameter("valorSalida");
		String TipoTriage=req.getParameter("CategoriaTriage");
		String CodCola_Fk=req.getParameter("CodAdmCola");
		String CodUsu_Fk=req.getParameter("usuario");
		String direccionar=req.getParameter("tipoSalida");
		String Estado=req.getParameter("estado");
		
		String CodAdm=req.getParameter("CodAdm");
		
		String CodigoFormatoPaciente=req.getParameter("CodigoFormatoPaciente");
		String CodColaFin=req.getParameter("CodColaFin");
		
		MetodoLaboratoriosHistoria mlh=new MetodoLaboratoriosHistoria();
		MetodoPreingreso mp=new MetodoPreingreso();
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		
		/*********************************MEDICAMENTOS********************************************/
		if(va.equals("Medi")){
			out.print("<table width='100%' border='1'>");					
			out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Formulacion Medicamentos e Insumos</span></div><input name='txtTipo' type='hidden' id='txtTipo' value='0' /></td></tr>");
			out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' onkeyup='ActualizaDetalleOrden()' ></textarea></td></tr>");
			out.print("<tr><td width='11%'>Medicamento</td><td width='89%'><input name='txtMedicamento' type='text' id='txtMedicamento' size='70' onKeyUp='autocompletarMedicamentoFormulaTr()' /></td></tr>");
			out.print("<tr><td><input name='txtCodigoMed' type='hidden' id='txtCodigoMed' size='18' /></td><td><div id='SugerenciaMedFormulaTr'></div></td></tr>");
			out.print("</table>");
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			out.print("<div id='ConteForm'>");
			out.print("<table width='100%' >");
			out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
			out.print("</table>");
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			out.print("<div id='DetFormula'>");
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
			out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion'  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
			out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print("</table>");
			out.print("</div>");
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar'></div></td></tr></table>");
			out.print("</div>");
			
		}
		
		if(va.equals("8.1")){
			out.print("<table width='100%' border='1'><tr><td width='93'>Cantidad</td><td width='146'><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
			out.print("<td width='58'>Lapso</td><td width='154'><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
			out.print("<td width='106'>Administraci&oacute;n</td><td width='149'><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otro'>Otro</option></select></td>");
			out.print("<td width='166'>Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='10' maxlength='10' readonly='' /></td><td width='166'></td></tr>");
			out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' ></textarea><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarMediTr()' /></td></tr>");
			out.print("</table>");
		}
		
		String codFormulacion_fk=req.getParameter("codFormulacion_fk");
		//STRING codCita=req.getParameter("codCita");
		String codCita=req.getParameter("codCita");
		String DetOrden=req.getParameter("DetOrden");
		//STRING codigoMed=req.getParameter("codigoMed");
		String cantidad=req.getParameter("cantidad");
		String dosificacion=req.getParameter("dosificacion");
		//STRING observacion=req.getParameter("observacion");
		String codDetFormulacion_fk=req.getParameter("codDetFormulacion_fk");
		//STRING usuario=req.getParameter("usuario");
		if(va.equals("99")){
		
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha1 = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora1 = new java.sql.Time(fechaActual.getTime());	
			String estado="";
			
			if(codFormulacion_fk==""){
				try {
					estado="-1";
					mp.CrearFormulacionTr(codCita, DetOrden, estado, usuario, hora1, fecha1);
					rs=mp.ObtenerCodigoFormulacionTr(hora1, fecha1);
					if(rs.next()){
						codFormulacion_fk=rs.getString(1);
					}
					rs.getStatement().getConnection().close();	
					mp.CrearDetalleFormulacionTr(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado);
					rs1=mp.ObtenerCodigoDetalleFormulacionTr(codFormulacion_fk, codigoMed);						
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='ConteForm'>");
					out.print("<table width='100%' >");
					out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='DetFormula'>");
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
					rs2=mp.DetalledeFormulacionTr(codFormulacion_fk);
					while(rs2.next()){
						out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs2.getString(5)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacionCE()' ></div></td></tr></table>");
					out.print("</div>");
					rs2.getStatement().getConnection().close(); 
					rs1.getStatement().getConnection().close(); 
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
		
		}
		if(va.equals("99.1")){
			String estado="";
			if(codFormulacion_fk!=""){
				try {
					estado="-1";					
					mp.CrearDetalleFormulacionTr(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado);
					rs3=mp.ObtenerCodigoDetalleFormulacionTr(codFormulacion_fk, codigoMed);
					if(rs3.next()){
						codDetFormulacion_fk=rs3.getString(1);
					}
					rs3.getStatement().getConnection().close();						
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='ConteForm'>");
					out.print("<table width='100%' >");
					out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='DetFormula'>");
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
					rs4=mp.DetalledeFormulacionTr(codFormulacion_fk);
					while(rs4.next()){
						out.print("<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getString(3)+"</td><td>"+rs4.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs4.getString(5)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacionCE()' ></div></td></tr></table>");
					out.print("</div>");
					rs4.getStatement().getConnection().close(); 					
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
		}
		
		/*******************************FIN MEDICAMENTOS E INSUMOS*********************************/

		
		if(va.equals("12")){			
			try {
				out.print("<table width='100%' class='style6' border='1'><tr id='cabecera2' class='style11'><td colspan='6'><div align='center'>LISTA DE PACIENTES POR ADMISION</div></td></tr></table><table width='100%' class='style6' border='1'><tr><td width='11%'><strong>Identificacion</strong></td><td width='33%'><strong>Nombre</strong></td><td width='19%'><strong>Entidad</strong></td><td width='18%'><strong>Fecha y Hora Entrada</strong></td><td width='11%'><strong>Tipo Triage</strong></td><td width='8%'><strong>Accion</strong></td></tr>");
				rs=mp.VerificarPacientesPorAdmision();
				while(rs.next()){					
					out.print("<tr><td>"+rs.getString(1)+" - "+rs.getString(2)+"</td><td><a href='Adm_Ingreso.jsp?tipo="+rs.getString(1)+"&cedula="+rs.getString(2)+"&CodColaFin="+rs.getString(10)+"'>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</a></td><td>"+rs.getString(9)+"</td><td>"+rs.getString(7)+"/"+rs.getString(8)+"</td><td>"+rs.getString(6)+"</td><td><a href='#'onclick='OmitirPendiente("+rs.getString(10)+")'>Omitir</a></td></tr>");	
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("13")){
			/**omitir la cola de la lista por atender en urgencia.**/
			mp.ActualizarListaColaFin(CodColaFin);
			
		}
		
	if(va.equals("0")){
		/**
		 * se obtiene un reporte mediante una consulta a 
		 * MetodoPreingreso/obtenerCola
		 */
		rs=mp.obtenerCola();
		try {
			out.print("<table width='100%' class='style6'><tr id='cabecera2' class='style11'><td colspan='6'><div align='center'>LISTA DE PACIENTES POR ATENDER</div></td></tr></table><div class='scrollbox1'><table width='100%' class='style6'><tr id='cabecera2' class='style11' align='center'><td><strong>Tipo Documento</strong></td><td><strong>Identificacion</strong></td><td><strong>Nombre</strong></td><td><strong>Eps</strong></td><td><strong>Fecha y Hora Entrada</strong></td><td>&nbsp;</td></tr>");
			while(rs.next()){
			out.print("<tr><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+" / "+rs.getString(8)+"</td><td><a href='#'onclick='Atender("+rs.getString(9)+","+rs.getString(4)+")'>Atender</a></td></tr>");
			}
			out.print("</table></div>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.println("Error Consulta ControladorMedico >> ObtenerCola >> rs "+e);
			e.printStackTrace();
		}
	}
	if(va.equals("1")){		
		try {
			rs=mp.DatosPacienteCola(CodAdmCola);
			if(rs.next()){
				//antes out.print("<table width='100%'><tr><td colspan='4' id='cabecera2'><div align='center' class='style11'>DATOS DEL PACIENTE </div></td></tr></table><table width='100%' class='style6'><tr class='style6' ><td width='11%'>N&ordm; Documento </td><td width='19%'><p class='style9'>"+rs.getString(1)+" - "+rs.getString(2)+"</p></td>");
				//antes out.print("<td width='16%'>Nombre Completo </td><td width='54%'><p class='style9'>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</p></td></tr><tr><td>Genero</td><td><p class='style9'>"+rs.getString(6)+"</p></td><td>Entidad</td><td><p class='style9'>"+rs.getString(7)+"</p></td></tr></table>");
				out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA'bordercolor='#FFFFFF'><tr style='color:#215b8b'><td width='15%'>Nombres y Apellidos </td><td width='45%'><div>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</div></td><td>Fecha Nacimiento:</td><td>"+rs.getString(8)+"</td><td>Edad:</td><td>"+rs.getString(9)+"</td></tr></table>");
				
				out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA'bordercolor='#FFFFFF'><tr style='color:#215b8b'><td width='15%'>Numero Documento: </td><td width='24%'><div>"+rs.getString(1)+" "+rs.getString(2)+"</div></td><td width='7%'>Entidad:</td><td width='54%'><div>"+rs.getString(7)+"</div></td></tr></table>");
				/**menu izq**/
				out.print("<table width='100%' border='1' cellspacing='0'><tr><td width='15%'><div id='MenuVertical'> <div id='button'><ul>" +
						"<li><a href='#' onclick='Antecedentes()'>Antecedentes</a></li>" +
						"<li><a href='#' onclick='Formatos()'>Formatos</a></li>" +
						"<li><a href='#' onclick='MenuLaboratorio()'>Revisar Resultados</a></li>"
						//"<li><a href='#' onclick='MostrarImagenologia()'>Imagenologia</a></li>"
						);
				
				out.print("<li><a href='#' onclick='FinalizarAtencion()'>Finalizar Atencion</a></li>" +
						"</ul></div></div></td><td width='85%'><div id='HistoriaPaciente'></div></td></tr></table>");
			
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.println("Error Consulta ControladorMedico >> DatosPacienteCola >> rs valor=1 "+e);
			e.printStackTrace();
		}
		/**
		 * seleccion de formatos
		 */
		//out.print("<table width='100%' border='1' class='style6' ><tr><td colspan='2' id='cabecera2' ><div align='center'><span class='style11'>Seleccionar Formatos</span></div></td></tr><tr><td width='192'>Seleccione Formato </td><td width='776'><label><input name='txtFormato' type='text' id='txtFormato' size='80' onkeyup='autocompletarFormato()' /><input name='btnVerFormato' class='boton4' type='button' id='btnVerFormato' value='Asignar' onclick='AsignarFormato()' /><input name='txtCodFormato' type='hidden' id='txtCodFormato' /><input name='txtCodPac' type='hidden' id='txtCodPac' /> <input name='txtCodAdmCola' type='hidden' id='txtCodAdmCola' /></label></td></tr><tr><td colspan='2'><div id='sugerenciasFormato'></div></td></tr></table>");
		/**
		 * Formatos Seleccionados
		 */
		//out.print("<table width='100%' border='1' class='style6' id='form3' ><tr><td colspan='4' id='cabecera2'><div align='center' class='style11'>Formatos Seleccionados</div></td></tr><tr><td width='19%'>Nombre Del Formato </td><td width='12%'>Hora y Fecha </td><td width='23%'>Usuario</td><td width='46%'>Areas Del Formato </td></tr><tr><td colspan='3'><div id='FormatosPaciente'></div></td><td><div id='areas'></div></td></tr></table>");
		/**
		 * Preguntas Del Formato 
		 */
		//out.print("<table width='100%' border='1' cellspacing='0' id='form4' name='form4' ><tr><td bgcolor='#01426E'>&nbsp;</td></tr><tr><td><div align='center'>Preguntas Del Formato</div> </td></tr><tr><td><div id='formulario' align='center'></div></td></tr></table>");
		/**
		 * botones de consulta
		 */
		//out.print("<table width='100%' border='1'><tr><td colspan='5' bgcolor='#01426E'>&nbsp;</td></tr><tr><td width='20%'>&nbsp;</td><td width='19%'><input name='btnDiagnostico' type='button' id='btnDiagnostico' value='Clasificacion de Diagnostico' onclick='ClasificarDiagnostico()' /></td><td width='18%'>&nbsp;</td><td width='21%'>&nbsp;</td><td width='22%'><input name='btnCerrar' type='button' id='btnCerrar' value='     Cerrar     ' /></td></tr></table>");
		/**
		 * 
		 */
	}
	/*********************LABORATORIO***************************/
	if(va.equals("menulab")){
		out.print("<table width='100%' border='0' class='style6' cellspacing='0' ><tr><td width='33%'><label><input name='radiobutton' type='radio' value='radiobutton' id='liscomp' onclick='Radio()' />Lista Completa</label></td>");
		out.print("<td width='33%'><label><input name='radiobutton' type='radio' value='radiobutton' id='segui' onclick='Radio()' />Seguimiento De Examenes</label></td><td width='33%'><label><input name='radiobutton' type='radio' value='radiobutton' id='ordmedi' onclick='Radio()' />Ordenes Medicamentos</label></td></tr></table>");
		out.print("<table width='100%' border='1'><tr><td><div id='ContenidoLaboratorio'></div></td></tr></table>");
		
	}
	if(va.equals("liscomp")){
		/**
		 * para llenar los examanes k se ha hecho el paciente en el formato
		 * que salga enseguida se carge la historia clinica
		 */
		//ResultSet rs2=null;
		String edad="";
		String genero="";
		rs2=mp.Busedadygene(CedPac);
		  try {
			if(rs2.next()){
				  edad=rs2.getString(2);
				  genero=rs2.getString(1);
			  }
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		String edad1=edad;
		rs3=mp.Buscacodge(genero);
		String codge="";
		try {
			if(rs3.next()){
				codge=rs3.getString(1);
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		rs=mp.ExamenTexto(CedPac);
		rs1=mp.ExamenRango(CedPac,codge);
		String nombre="";
		String apellidos="";
		ResultSet pa1=null;
		pa1=mp.Busedadygene(CedPac);
		try {
			if(pa1.next()){
				nombre=pa1.getString(3);
				apellidos=pa1.getString(4);
			}
			pa1.getStatement().getConnection().close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		 out.print("<table width='100%' border='1' class='style6'><tr id='cabecera2' align='rigth' class='style11'><td >Fecha y Hora </td><td >Nombre Del Estudio </td></tr>");
		try {
			out.print("<tr>");
			while(rs.next()){
				String ano=rs.getString(4).substring(0,4);
				String mes=rs.getString(4).substring(5,7);
				String dia=rs.getString(4).substring(8,10); 
				hora=rs.getString(5).substring(0,2);
				String minuto=rs.getString(5).substring(3,5);
				String segundo=rs.getString(5).substring(6,8);
				out.print("<tr><td align='left' width='20%'><div>"+rs.getString(4)+"/"+rs.getString(5)+"</div></td><td width='80%' align='left'><div><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+1+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+")'>"+rs.getString(6)+"</a></div></td></tr>");	
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			while(rs1.next()){
				String ano=rs1.getString(4).substring(0,4);
				String mes=rs1.getString(4).substring(5,7);
				String dia=rs1.getString(4).substring(8,10);
				hora=rs1.getString(5).substring(0,2);
				String minuto=rs1.getString(5).substring(3,5);
				String segundo=rs1.getString(5).substring(6,8);
				out.print("<tr><td align='left' width='20%'><div>"+rs1.getString(4)+"/"+rs1.getString(5)+"</div></td><td width='80%' align='left'><div><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+2+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(6)+"</a></div></td></tr>");	
			}
			rs.getStatement().getConnection().close();
			rs1.getStatement().getConnection().close();			
			rs2.getStatement().getConnection().close();
			ResultSet rsgrupo=null;
			rsgrupo=mp.Examen(CedPac);
			ResultSet redad=null;
			ResultSet rgene=null;
			redad=mp.Buscaedad(CedPac);
			String edad2="";
			String genero1="";
			String codgenero="";
			try {
				if(redad.next()){
					edad2=redad.getString(1);
					genero1=redad.getString(2);
				}
				rgene=mp.Buscacodge(genero);
				if(rgene.next()){
					codgenero=rgene.getString(1);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				while(rsgrupo.next()){
					String ano=rsgrupo.getString(4).substring(0,4);
					String mes=rsgrupo.getString(4).substring(5,7);
					String dia=rsgrupo.getString(4).substring(8,10);
					hora=rsgrupo.getString(5).substring(0,2);
					String minuto=rsgrupo.getString(5).substring(3,5);
					String segundo=rsgrupo.getString(5).substring(6,8);
					out.print("<tr><td align='left' width='20%'><div>"+rsgrupo.getString(4)+"/"+rsgrupo.getString(5)+"</div></td><td width='80%' align='left'><div><a  href='#'onclick='Abrir("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rsgrupo.getString(9)+","+rsgrupo.getString(10)+","+codgenero+")'>"+rsgrupo.getString(6)+"</a></div></td></tr>");	
				}
				out.print("</table>");
				rsgrupo.getStatement().getConnection().close();
				redad.getStatement().getConnection().close();
				rgene.getStatement().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//out.println("</table>");
	}
	
	
	if(va.equals("segui")){
		int contador=0;
		rs=mp.ExamenesRealizados(CedPac);
		out.print("<table width='100%' border='1'><tr><td><div id='ExamenesActivos'><table width='100%' class='style6' border='1'><tr id='cabecera2' class='style11' align='center'> <td width='20%'>Examenes Realizados <label></label></td></tr>");
		try{
			while(rs.next()){
				out.print("<tr><td><label><input name='chkExamenes' type='radio' id='chkExamenes' value="+rs.getString(2)+" onclick='mostrarHistorial()' />"+rs.getString(1)+"</label></td> </tr>");
			contador=contador+1;
			}
			rs.getStatement().getConnection().close();
			out.print("</table></div></td><td width='80%'><div id='HistorialLaboratorios'></div></td></tr><input name='txtContador' type='hidden' id='txtContador' value="+contador+" /></table>");
		}
		
		catch (SQLException e) {
			System.out.println("ERROR EN VA EQUAL 2 ControlFormatosPestanas "+e);
			e.printStackTrace();
		}
	}
	if(va.equals("hstlab")){
		ResultSet bus=null;
		ResultSet pa=null;
		ResultSet ge=null;
		ResultSet co=null;
		ResultSet co2=null;
		String tipoexa="";
		String genero="";
		String edad="";
		String nombre="";
		String apellidos="";
		String codge="";
		String exam="";
		String cont=req.getParameter("cont");
		int edad1=0;
		
		bus=mp.Busedadygene1(CedPac);
		pa=mp.Busedadygene1(CedPac);
		
		
		try{
			if(pa.next()){
				nombre=pa.getString(3);
				apellidos=pa.getString(4);
			}
		
		if(bus.next()){
			genero=bus.getString(1);
			edad=bus.getString(2);
		}
		bus.getStatement().getConnection().close();
		
		ge=mp.Buscacodge(genero);
		
		if(ge.next()){
			codge=ge.getString(1);
		}
		 edad1= Integer.parseInt(edad);
		 ge.getStatement().getConnection().close();
		 pa.getStatement().getConnection().close();
         }catch(Exception ex){
			
		}
	     out.print("<br>");
	     
	   //  while(cont!=("0")){
		//	 exam=tk.nextToken();
			 rs=mp.Bustip(CodExamen);
			 try {
				 if(rs.next()){
					 tipoexa=rs.getString(2);
					 exam=rs.getString(3);
				 }
				 rs.getStatement().getConnection().close();
			 } catch (SQLException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
			 try {
				 if(tipoexa.equals("1")){
					

					 co=mp.Examenom(CodExamen, CedPac, codge, edad1);
					 co2=mp.Examenom(CodExamen,CedPac, codge, edad1);

					 out.println("<div class='scrollbox2'><table class='style6' width='100%' border='1'><tr><td></td></tr><tr align='center'>");
					 out.println("<td></td>"); 
					 while(co.next()){
						 out.print("<td bgcolor='#dbe5f1'><div class='style6' >"+co.getString(4)+"</div><div  class='style6'>"+co.getString(5)+"</div></td>"); 
					 }
					 out.print("</tr>"); 
					 out.println("<tr align='center'><td>"+exam+"</td>");                      
					 while(co2.next()){
						 out.print("<td ><div >"+co2.getString(7)+"</div></td>");
					 }

					 co.getStatement().getConnection().close();
					 co2.getStatement().getConnection().close();
					 out.println("</tr></table></div><br><br>");
				 }
			 }catch (SQLException e) {
				 e.printStackTrace();
			 }  
			 try {
				 if(tipoexa.equals("0")){  
					 ResultSet co1=null;
					 ResultSet co5=null;
					 co1=mp.ExamenTexto1(CedPac,CodExamen);
					 co5=mp.ExamenTexto1(CedPac,CodExamen);			    

					 out.println("<div class='scrollbox2'><table class='style6' width='100%' border='1' ><tr align='center'>");	
					 out.println("<td></td>"); 
					 while(co1.next()){
						 out.print("<td bgcolor='#dbe5f1'><div align='center' class='style6'>"+co1.getString(1)+"</div><div align='center' class='style6'>"+co1.getString(2)+"</div></td>");
					 }
					 out.print("</tr>");
					 out.println("<tr align='center'><td>"+exam+"</td>");
					 co1.getStatement().getConnection().close();
					 while(co5.next()){
						 out.print("<td >"+co5.getString(3)+"</td>");
					 }		    				 
					 co5.getStatement().getConnection().close();
					 out.println("</tr></table></div><br> <br>");
				 }
				 
			 }catch (SQLException e) {
				 e.printStackTrace();
			 }  
		// }
	}
	/*********************FIN LABORATORIO***************************/
	/***********************IMAGENOLOGIA******************************/
	if(va.equals("imag")){

		rs4=mp.HistorialDeImagenologia(CedPac);
		out.print("<table width='100%' border='1' class='style6' ><tr align='center' id='cabecera2' class='style11'><td >Fecha y Hora </td><td align='left'>Nombre Del Estudio </td></tr>");
		try {
			while(rs4.next()){
				out.print("<tr><td width='20%' align='left'>"+rs4.getString(1)+"/"+rs4.getString(2)+"</td><td width='80%' align='left'><a  href='#'onclick='mostarexamenes("+rs4.getString(4)+","+rs4.getString(5)+")'>"+rs4.getString(3)+"</a></td></tr>");
			}
			out.print("</table>");
			rs4.getStatement().getConnection().close();
		} catch (SQLException e) {
				System.out.println("ERROR EN VA EQUAL 4 ControlFormatosPestanas "+e);
			e.printStackTrace();
		}
	}
	/***********************FIN DE IMAGENOLOGIA******************************/
	/****************************antecedentes*************************************/
	if(va.equals("antecedentes")){
		out.print("<table width='100%' border='1' class='style6' ><tr><td colspan='8'><div align='center' class='style11' id='cabecera2'>SELECCIONE UNA OPCION</div></td></tr><tr><td width='12%'><label><input name='radiobutton' type='radio' value='radiobutton' id='perso' onClick='RadioAntecedentes()' />Personales</label></td><td width='14%'><label>");
		
		out.print("<input name='radiobutton' type='radio' value='radiobutton' id='qx' onClick='RadioAntecedentes()' />Quirurgicos</label></td><td width='11%'><label><input name='radiobutton' type='radio' value='radiobutton' id='alerg' onClick='RadioAntecedentes()' />Alergicos</label></td><td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='tx' onClick='RadioAntecedentes()' />Toxicos</label></td>");
		
		out.print("<td width='11%'><label><input name='radiobutton' type='radio' value='radiobutton' id='fami' onClick='RadioAntecedentes()' />Familiares</label></td><td width='16%'><label><input name='radiobutton' type='radio' value='radiobutton' id='trans' onClick='RadioAntecedentes()' />Transfuncionales</label></td><td width='11%'><label><input name='radiobutton' type='radio' value='radiobutton' id='vac' onClick='RadioAntecedentes()' />Vacunas</label></td>");
		
		out.print("<td width='15%'><label><input name='radiobutton' type='radio' value='radiobutton' id='medi' onClick='RadioAntecedentes()' />Medicamentos</label></td></tr><tr><td colspan='8'><div id='Antecedente'></div></td></tr></table>");
	}
	/********PERSONALES******/
	if(va.equals("perso")){
		out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center'>Clasificacion de Diagnostico</div></td></tr><tr><td width='10%'>Diagnostico</td><td width='90%'><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()' />");
		
		out.print("<input name='btnAsignarDiagnostico' class='boton4' type='button' id='btnAsignarDiagnostico' value='Asignar' onclick='DiagnosticoPaciente()' /></label></td></tr><tr><td><label><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td><td><div id='SugeDiagnostico'></div></td></tr>");
		
		out.print("<tr><td>Observacion</td><td><div align='center'><textarea name='txtObservacioDiagnostico' cols='70' rows='3' id='txtObservacioDiagnostico'></textarea></div></td></tr></table>");
		
		out.print("<table width='100%' border='1' class='style6'><tr class='style11' id='cabecera2' ><td width='80%'><div align='center'>Diagnosticos</div></td><td width='20%'><div align='center'>Fecha y Hora</div></td></tr>");
		try {
			rs2=mp.VerificarDiagnosticos(CedPac);
			while(rs2.next()){
				out.print("<tr><td><a href='#' onclick='Observacion("+rs2.getString(4)+")'>"+rs2.getString(1)+"</a></td><td>"+rs2.getString(2)+"/"+rs2.getString(3)+"</td></tr>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
		
	}	
	
	if(va.equals("ObsDia")){
		rs3=mp.VerObservacionDiagnostico(Diagnos);
		try {
			if(rs3.next()){
				out.print("<table width='100%' border='1' class='style6'><tr>");					  
				out.print("<td><div align='center'>"+rs3.getString(2)+"</div></td></tr>");					  
				out.print("<tr><td><div>"+rs3.getString(1)+"</div></td></tr></table>");
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/********FIN PERSONALES******/
	
	/********QUIRURGICOS******/
	if(va.equals("qx")){
		out.print("<table width='100%' border='1' class='style6'><tr><td width='20%'><div>Cirujia</div></td><td colspan='3'><div><input name='txtCirujia' type='text' id='txtCirujia' size='60' onkeyup='autocompletarQx()' /></div></td></tr><tr><td></td><td colspan='3'><div id='SugerenciaCx'></div></td></tr><tr><td><div>Observacion</div></td><td width='29%'><div><textarea name='txtObservacionCx' cols='40' rows='3' id='txtObservacionCx'></textarea></div></td><td width='10%'><div>Fecha</div></td>");
		out.print("<td width='41%'><div><input name='txtFechaCx' type='text' id='txtFechaCx' maxlength='14' onkeyup='' /></div></td></tr><tr><td colspan='4'><div align='center'><input name='btnIngresarCx' class='boton4' type='button' id='btnIngresarCx' value='     Ingresar     ' onclick='IngresarProcedimiento()' /><input name='txtCodigoQx' type='hidden' id='txtCodigoQx' /></div></td></tr></table>");
		
		out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='51%'><div align='center'>Procedimiento</div></td><td width='49%'><div align='center'>Observacion</div></td></tr>");
		try {
			rs2=mp.VerificarQx(CedPac);
			while(rs2.next()){
				out.print("<tr><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(1)+" "+rs2.getString(2)+"</td></tr>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
	}

	if(va.equals("IngrQx")){
		mp.InsertarProcedimientos(codigoQx, fechaQx, observacion, CedPac, usuario, fecha, hora,CedPac);
	}
	/*******FIN QUIRURGICOS*******/
	
	/********ALERGICOS******/
	if(va.equals("alerg")){
		out.print("<table width='100%' border='1' class='style6'><tr><td width='50%'><div align='center'>Alergia</div></td><td width='50%'><div align='center'>Reaccion</div></td></tr><tr><td><div align='center'><textarea name='txtAlergia' cols='40' rows='3' id='txtAlergia'></textarea></div></td>");
		out.print("<td><div align='center'><textarea name='txtReaccion' cols='40' rows='3' id='txtReaccion'></textarea></div></td></tr><tr><td colspan='2'><div align='center'><input name='btnIngreAlergia' type='button' id='btnIngreAlergia' class='boton4' value='       Ingresar      ' onclick='IngresaAlergia()' /></div></td></tr></table>");
		
		out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>HISTORICO</div></td></tr><tr class='style11' id='cabecera2'><td width='50%'><div align='center'>Alergia</div></td><td width='50%'><div align='center'>Reaccion</div><div align='center'></div></td></tr>");
		try {
			rs2=mp.VerificarAlergias(CedPac);
			while(rs2.next()){
				out.print("<tr><td><div>"+rs2.getString(1)+"</div></td><td><div>"+rs2.getString(2)+"</div></td></tr>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
	}
	
	if(va.equals("IngreAler")){
		mp.InsertarAlergia(Alergia, Reaccion, CedPac, usuario, hora, fecha,CedPac);
	}
	
	/*******FIN ALERGICOS*******/
	
	/*******TOXICOS*******/
	if(va.equals("tx")){
		out.print("<table width='100%' border='1' class='style6'><tr><td width='16%'><div align='center'>Toxicologia</div></td> <td colspan='2'><div align='center'>Observacion</div>  <div align='center'></div></td></tr><tr><td><select name='cmbToxicologia' id='cmbToxicologia'>");
		out.print("<option value='Seleccione' selected='selected'>Seleccione</option><option value='Tabaquismo'>Tabaquismo</option><option value='Alcohol'>Alcohol</option></select></td><td width='28%'><textarea name='txtObservacionTx' cols='40' rows='3' id='txtObservacionTx'></textarea></td><td width='56%'><input name='btnIngreToxico' type='button' id='btnIngreToxico' value='     Ingresar     ' onClick='IngresarToxico()'></td></tr></table>");
		out.print("<table width='100%' border='1'><tr><td width='16%'><div align='center'>Toxicologia</div></td><td width='84%'><div align='center'>Observacion</div></td></tr>");
		try {
			rs2=mp.VerificarToxico(CedPac);
			while(rs2.next()){
				out.print("<tr><td><div>"+rs2.getString(1)+"</div></td><td><div>"+rs2.getString(2)+"</div></td></tr>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
	}
	
	if(va.equals("IngTox")){
		mp.InsertarToxicologia(tipoTx, ObservacionTx, CedPac, usuario, fecha, hora,CedPac);
	}
	/******FIN TOXICOS********/
	
	/******FAMILIARES********/
	if(va.equals("fami")){
		//out.print("FAMILIARES");
		out.print("<table width='100%' border='1' class='style6'><tr><td width='18%'><div align='center'>Familiar</div></td><td width='82%'><div align='center'>Antecedente</div></td></tr><tr><td><div>");
		out.print("<select name='cmbFamilia' id='cmbFamilia' ><option value='Seleccion' selected='selected'>Seleccion</option><option value='Abuelo Materno'>Abuelo Materno</option><option value='Abuela Materno'>Abuela Materno</option><option value='Abuelo Paterno'>Abuelo Paterno</option><option value='Abuela Paterno'>Abuela Paterno</option>");
		out.print("<option value='Padre'>Padre</option><option value='Madre'>Madre</option><option value='Hermanos'>Hermanos</option><option value='Hijos'>Hijos</option></select></div></td><td><div><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='60' onKeyUp='autocompletarCIE10()' /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' class='boton4' value='Asignar' onclick='IngresaAntFamiliares()' /></div></td></tr>");
		out.print("<tr><td><div><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></div></td><td><div id='SugeDiagnostico'></div></td></tr><tr><td><div>Observacion</div></td><td><div><textarea name='txtObseFami' cols='60' rows='4' id='txtObseFami'></textarea></div></td></tr></table>");
	
		out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='18%'><div align='center'>Familiar</div></td><td width='82%'><div align='center'>Antecedente</div></td></tr>");
		try {
			rs2=mp.VerificarAntFamiliares(CedPac);
			while(rs2.next()){
				out.print(" <tr><td><div>"+rs2.getString(1)+"</div></td><td><div><a href='#' onClick='MostrarObserFami("+rs2.getString(3)+")'>"+rs2.getString(2)+"</a></div></td></tr>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
	
	
	}
	
	if(va.equals("IngreFami")){
		mp.InsertarAntefamiliar(Familiar, Observacion, CodCIE_fk, CedPac, usuario, hora, fecha,CedPac);
	}
	
	if(va.equals("ObsDiaAntFam")){
		rs3=mp.VerObservacionDiagnosticoFamiliar(Diagnos);
		try {
			if(rs3.next()){
				out.print("<table width='100%' class='style6' border='1'><tr>");					  
				out.print("<td><div align='center'>"+rs3.getString(2)+"</div></td></tr>");					  
				out.print("<tr><td><div>"+rs3.getString(1)+"</div></td></tr></table>");
			}
			rs3.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*******FIN FAMILIARES*******/
	
	/*******TRANSFUNCIONALES*******/
	if(va.equals("trans")){
		out.print("<table width='100%' border='1' class='style6'><tr><td width='37%'><div>Transfusiones</div></td><td width='10%'><div><label><input name='radioTrans' type='radio' value='Si' onClick='RadioTrans()' id='Si' />Si</label>&nbsp;&nbsp;&nbsp;<label><input name='radioTrans' type='radio' value='No' onClick='RadioTrans()' id='No'/>No</label></div></td><td width='53%'><div id='OpcTransf'></div></td></tr></table>");
		
		out.print("<table width='100%' border='1'><tr><td><div align='center'>Transfucion</div></td></tr>");
		try {
			rs2=mp.VerificarTransfu(CedPac);
			while(rs2.next()){
				String cadena=rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" "+rs2.getString(4);
				out.print("<tr><td><div>"+cadena+"</div></td></tr>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print("</table>");
	}
	
	if(va.equals("Si")){
		out.print("<table border='1' width='87%'><tr><td width='5%'><div>Fecha</div></td><td width='18%'><div><input name='txtFechaTransfucion' type='text' id='txtFechaTransfucion' onkeyup='' maxlength='12' /></div></td><td width='8%'><div>Cantidad</div></td><td width='18%'><div><input name='txtCantidad' type='text' id='txtCantidad' maxlength='5' /></div></td><td width='10%'>Observacion</td><td width='41%'><textarea name='txtObservacionTr' rows='3' id='txtObservacionTr'></textarea><input name='btnIngreTransf' class='boton4' type='button' id='btnIngreTransf' value='Ingresar'  onClick='GuardarSiTrans()' /></td></tr></table>");
	}
	if(va.equals("No")){
		out.print("<table width='100%' border='1'><tr> <td><div><input name='btnIngreTransf' class='boton4' type='button' id='btnIngreTransf' value='     Ingresar     ' onClick='GuardarNoTrans()' /></div></td></tr></table>");
	}
	
	if(va.equals("IngTran")){
		mp.InsertarTransfuciones(transfucion, Cantidad, ObservacionTr, FechaTransfucion, CedPac, usuario, fecha, hora,CedPac);
	}
	/*******FIN TRANSFUNCIONALES*******/
	
	/*******VACUNAS*******/
	if(va.equals("vac")){
		out.print("BAJO ESTUDIO");
	}
	/*******FIN VACUNAS*******/
	
	/*******MEDICAMENTOS*******/
	if(va.equals("medi")){
		out.print("<table width='100%' border='1' class='style6'><tr><td width='20%'><div>Medicamento</div></td><td><div><input name='txtMedicamento' type='text' id='txtMedicamento' size='60' onkeyup='autocompletarMedicamento()' /></div></td></tr><tr><td></td><td><div id='SugerenciaMed'></div></td></tr><tr><td><div>Observacion</div></td><td><div><textarea name='txtObservacionMd' cols='40' rows='3' id='txtObservacionMd'></textarea></div></td>");
		out.print("</tr><tr><td colspan='2'><div align='center'><input name='btnIngresarMd' class='boton4' type='button' id='btnIngresarMd' value='     Ingresar     '  onclick='IngresarMd()' /><input name='txtCodigoMed' type='hidden' id='txtCodigoMed' /></div></td></tr></table>");
		
		out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='67%'><div align='center'>Medicamento</div></td><td width='33%'><div align='center'>Observacion</div></td></tr>");
		try {
			rs2=mp.Verificarmedicamentos(CedPac);
			while(rs2.next()){
				String cadena=rs2.getString(2)+" "+rs2.getString(3);
				out.print("<tr><td>"+cadena+"</td><td>"+rs2.getString(1)+"</td></tr>");
			}
			rs2.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		out.print("</table>");
		
	}
	if(va.equals("IngMed")){
		mp.InsertarMedicamentos(codigoMed, observacion, CedPac, usuario, fecha, hora,CedPac);
	}
	/******FIN MEDICAMENTOS********/
	
	
	/****************************fin antecedentes***********************************/
	
	/**********************FORMATOS***********************/
	if(va.equals("formatos")){
		//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td width='192'>Seleccione Formato </td><td width='776'><label><input name='txtFormato' type='text' id='txtFormato' size='90' onkeyup='autocompletarFormato()' /><input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /><input name='txtCodFormato' type='hidden' id='txtCodFormato' /></label></td></tr><tr><td colspan='2'><div class='style6' id='sugerenciasFormato'></div></td></tr></table>");
		//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td width='20%'>Nombre Del Formato </td><td width='10%'>Hora y Fecha </td><td width='19%'>Usuario</td><td width='14%'>Acciones de Formato</td><td width='37%'><div align='center'>Areas Del Formato</div></td></tr><tr class='style6'><td colspan='4'><div id='FormatosPaciente'></div></td><td><div id='areas' class='style6'></div></td></tr></table>");
		//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td><div align='center'> Preguntas Del Formato</div></td></tr><tr><td><div class='style6' id='formulario'></div></td></tr></table>");
		//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td width='192'>Seleccione Formato </td><td width='776'><label><input name='txtFormato' type='text' id='txtFormato' size='90' onkeyup='autocompletarFormato()' /><input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /><input name='txtCodFormato' type='hidden' id='txtCodFormato' /></label></td></tr><tr><td colspan='2'><div class='style6' id='sugerenciasFormato'></div></td></tr></table>");
		//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td width='192'>Seleccione Formato </td><td width='776'><label><input name='txtFormato' type='text' id='txtFormato' size='90' onkeyup='autocompletarFormato()' /><input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /><input name='txtCodFormato' type='hidden' id='txtCodFormato' /></label></td></tr><tr><td colspan='2'><div class='style6' id='sugerenciasFormato'></div></td></tr></table>");
		out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td><div align='center'>Seleccione Formato <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name='txtCodFormato' id='txtCodFormato'><option value='Seleccione' selected='selected'>Seleccione</option>");
		rs=mlh.ObtenerFormatosPermitidosUsuario(CodigoUsuario);
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /></label></div></td></tr></table>");
		out.print("<br>");
		/***********************************************************************************************************************************************************************************************************************/
		out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td width='20%'>Nombre Del Formato </td><td width='10%'>Hora y Fecha </td><td width='19%'>Usuario</td><td width='14%'>Acciones de Formato</td><td width='37%'><div align='center'>Areas Del Formato</div></td></tr><tr class='style6'><td colspan='4'><div id='FormatosPaciente'></div></td><td><div id='areas' class='style6'></div></td></tr></table>");
		/***********************************************************************************************************************************************************************************************************************/
		out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td><div align='center'> Preguntas Del Formato</div></td></tr><tr><td><div class='style6' id='formulario'></div></td></tr></table>");
		
		////////////////boton finalizar////////////////////////
		//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td align='center'><div align='center'><input name='btnCerrar' class='boton4' type='button' id='btnCerrar' value='     Finalizar Atencion     ' onclick='FinalizarAtencion()' /></div></td></tr></table>");
	
	}
	
	
	if(va.equals("2")){
		String CodigoArea="";
		String CodigoPregunta="";
		if(CodFormato!=null){			
			mp.RelacionFormatoColaPaciente(CodFormato, CodAdmCola, CedPac, hora, fecha, usuario);
			rs1=mp.ObtenerAreaFormato(CodFormato,fecha,hora);
			try {
				while(rs1.next()){
					CodigoArea=rs1.getString(1);
					rs2=mp.ObtenerPreguntasArea(CodigoArea);
					while(rs2.next()){
						String resultados="";
						String estado="0";
						CodigoPregunta=rs2.getString(4);
						String TipoPregunta=rs2.getString(2);
						if(TipoPregunta.equals("1")){
							mp.IngresarHistoriaCola(CedPac, CodAdmCola, CodigoPregunta, resultados, estado, fecha, hora, usuario, CodigoArea);
						}
						if(TipoPregunta.equals("2")){
							resultados="Seleccione";
							mp.IngresarHistoriaCola(CedPac, CodAdmCola, CodigoPregunta, resultados, estado, fecha, hora, usuario, CodigoArea);
						}
						if(TipoPregunta.equals("3")){
							/**si tipo pregunta es igual a un texto corto */
							mp.IngresarHistoriaCola(CedPac, CodAdmCola, CodigoPregunta, resultados, estado, fecha, hora, usuario, CodigoArea);
						}
						if(TipoPregunta.equals("4")){
							/**si tipo pregunta es igual a un autocompletar de tipo diagnostico */
							mp.IngresarHistoriaCola(CedPac, CodAdmCola, CodigoPregunta, resultados, estado, fecha, hora, usuario, CodigoArea);
						}
						if(TipoPregunta.equals("5")){
							/**si tipo pregunta es igual a un autocompletar de tipo diagnostico */
							mp.IngresarHistoriaCola(CedPac, CodAdmCola, CodigoPregunta, resultados, estado, fecha, hora, usuario, CodigoArea);
						}
					}
					rs2.getStatement().getConnection().close();
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("ERROR en Valor Igual 2 ControladorMedico ResultSet "+e);
				e.printStackTrace();
			}
		}
		
	}
	
	if(va.equals("3")){
		/**
		 * se obtienen las areas del formato llevando como parametro el codigo del formato. 
		 */
		rs=mp.ObtenerAreaFormato2(CodFormato,FechaFormato,HoraFormato);
		try {
			out.println("<table width='100%' class='style6' border='0'><tr><td></td></tr>");
			while(rs.next()){
				String NombArea=rs.getString(2);
				out.println("<tr><td><a  href='#'onclick='mostarpreguntas("+rs.getString(1)+")'>"+NombArea+"</a><input name='HoraFormato' type='hidden' id='HoraFormato' value='"+rs.getString(3)+"' ><input name='FechaFormato' type='hidden' id='FechaFormato' value='"+rs.getString(4)+"' ><input name='CodArea' type='hidden' id='CodArea' value='"+rs.getString(1)+"' ></td></tr>");
			}
			out.println("</table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.println("ERROR en Valor Igual 3 ControladorMedico ResultSet "+e);
			e.printStackTrace();
		}
	}
	if(va.equals("4")){
		int contador=0;
		String usuarios="";
		String estadoPregunta="";
		/**
		 * se obtienen las preguntas del area llevando como parametro el codigo del area. 
		 */
		rs4=mp.ObtenerUsuarioFormato(CodArea, FechaFormato, HoraFormato, usuario);
		try {
			if(rs4.next()){
				usuarios=rs4.getString(1);
				estadoPregunta=rs4.getString(2);
			}
			if((usuarios=="")&&(estadoPregunta.equals(""))){
				try {
					rs=mp.ObtenerPreguntasAreaLleno(CodArea, FechaFormato, HoraFormato);
					out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
					while(rs.next()){
						String CodTipoResp=rs.getString(3);
						out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
						if(rs.getString(2).equals("1")){
							/**
							 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
							 */
						out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='3' onkeyup='ActualizarResultados()' readonly='' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
						}
						contador=contador+1;
						if(rs.getString(2).equals("2")){
							/**
							 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
							 */
							rs1=mp.OpcionesTipoRespuesta2(CodTipoResp);
							out.print("<td><select name='txtRespuesta' id='txtRespuesta' onchange='ActualizarResultados()' disabled='disabled'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
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
							out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onkeyup='ActualizarResultados()' readonly='' value="+rs.getString(5)+"   > "+rs.getString(6)+"</td>");
						}
						if(rs.getString(2).equals("4")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 */
							out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
						}
						if(rs.getString(2).equals("5")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 *
							 *
							 *
							 * AQUI SE VA PARA INGRESAR VARIOS DIAGNOSTICOS.
							 * **/
							String Resultado="";
							Resultado=rs.getString(5);
							out.print("<td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoSolo()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' value='"+Resultado+"' ><input name='txtCodResultado5' type='hidden' id='txtCodResultado5' value="+rs.getString(4)+" ><br><div id='SugeDiagnostico'></div><div id='DiagnosticoIniciales'>"+Resultado+"</div></label></td></tr>");
						}
					}
					out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
					
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("Error ResultSet valor=1 "+e);
					e.printStackTrace();
				}
			}			
			if((usuarios!="")&&(estadoPregunta.equals("1"))){
				try {
					rs=mp.ObtenerPreguntasAreaLleno(CodArea, FechaFormato, HoraFormato);
					out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
					while(rs.next()){
						String CodTipoResp=rs.getString(3);
						out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
						if(rs.getString(2).equals("1")){
							/**
							 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
							 */
						out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='3' onkeyup='ActualizarResultados()' readonly='' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
						}
						contador=contador+1;
						if(rs.getString(2).equals("2")){
							/**
							 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
							 */
							rs1=mp.OpcionesTipoRespuesta2(CodTipoResp);
							out.print("<td><select name='txtRespuesta' id='txtRespuesta' onchange='ActualizarResultados()' disabled='disabled'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
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
							out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onkeyup='ActualizarResultados()' readonly=''  value="+rs.getString(5)+"  > "+rs.getString(6)+"</td>");
						}
						if(rs.getString(2).equals("4")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 */
							out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
						}
						if(rs.getString(2).equals("5")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 *
							 *
							 *
							 * AQUI SE VA PARA INGRESAR VARIOS DIAGNOSTICOS.
							 * **/
							String Resultado="";
							Resultado=rs.getString(5);
							out.print("<td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoSolo()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' value='"+Resultado+"' ><input name='txtCodResultado5' type='hidden' id='txtCodResultado5' value="+rs.getString(4)+" ><br><div id='SugeDiagnostico'></div><div id='DiagnosticoIniciales'>"+Resultado+"</div></label></td></tr>");
						}
						
					}
					out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
					
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("Error ResultSet valor=1 "+e);
					e.printStackTrace();
				}
			}
			if((usuarios!="")&&(estadoPregunta.equals("0"))){
				try {
					rs=mp.ObtenerPreguntasAreaLleno(CodArea, FechaFormato, HoraFormato);
					out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
					while(rs.next()){
						String CodTipoResp=rs.getString(3);
						out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
						if(rs.getString(2).equals("1")){
							
							/**
							 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
							 */
						
						out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='3' onkeyup='ActualizarResultados()' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
						}
						contador=contador+1;
						if(rs.getString(2).equals("2")){
							/**
							 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
							 */
							rs1=mp.OpcionesTipoRespuesta2(CodTipoResp);
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
							out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onkeyup='ActualizarResultados()' value="+rs.getString(5)+" >"+rs.getString(6)+" </td>");
						}
						if(rs.getString(2).equals("4")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 */
							
							String Resultado=rs.getString(5);
							if(Resultado.equals("")){
								out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicial()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
							else{
								out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
							
						}
						if(rs.getString(2).equals("5")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 *
							 *
							 *
							 * AQUI SE VA PARA INGRESAR VARIOS DIAGNOSTICOS.
							 * **/
							String Resultado="";
							Resultado=rs.getString(5);
							out.print("<td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoSolo()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' value='"+Resultado+"' ><input name='txtCodResultado5' type='hidden' id='txtCodResultado5' value="+rs.getString(4)+" ><br><div id='SugeDiagnostico'></div><div id='DiagnosticoIniciales'>"+Resultado+"</div></label></td></tr>");
						}
						
						
					}
					out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
					
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("Error ResultSet valor=1 "+e);
					e.printStackTrace();
				}
				rs4.getStatement().getConnection().close();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	if(va.equals("1.1")){
		System.out.println("11");
		String Resul=req.getParameter("Resul");
		String CodResul=req.getParameter("CodResul");
		System.out.println("1111 Resul="+Resul+" CodResul="+CodResul);
		ResultSet rs7=null;
		MetodoIngresarResultados mir =new MetodoIngresarResultados();
		String Resultado="";
		if(CodDiagnostico==null){
			System.out.println("1122");
			Resultado=Resul;
			mir.ActualizarResultadosTr(Resultado, CodResul);
		}
		if(CodDiagnostico!=null){
			System.out.println("1133");
			try {
				System.out.println("1144");
				Resultado=Resul+"-"+CodDiagnostico;
				mir.ActualizarResultadosTr(Resultado, CodResul);
				//mir.IngresarDiagnosticoIngreso(CodResul, CodDiagnostico, usuario, hora, fecha, CodAdm, CodPac);
				rs7=mir.ObtenerResultadoTr(CodResul);
				//out.print("<table>");
				while(rs7.next()){
					System.out.println("1155");
					out.print(rs7.getString(1));
				}
				//out.print("</table>");
				rs7.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.print("Ingreso Exitosa.");
			
		}
	}
	if(va.equals("5")){
		/**
		 * una ves guardada la relacion se procede a mostrar los formatos - 
		 */
		rs=mp.ObtenerFormatosColaPaciente(CedPac, CodAdmCola);
	
		out.print("<table border='0' width='100%' class='style2' >");
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
				
				out.print("<tr><td width='35%'><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
				if(rs.getString(7).equals(CodUsu)&& rs.getString(10).equals("0")){
				out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormato("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+")' style='cursor:pointer;' /><img title='Eliminar' name='btnGuardarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormato("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+")' style='cursor:pointer;' /></td>");
				}
				else{
					if(rs.getString(10).equals("1")){
						out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+")' style='cursor:pointer;' /></td>");
					}
					else{
						out.print("<td width='19%'>C&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+")' style='cursor:pointer;' /></td>");
					}
					
				}
				out.print("</tr>");
				
				
			}
			out.print("</table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.println("ERROR en Valor Igual 5 ControladorMedico ResultSet "+e);
			e.printStackTrace();
		}
	}
	//}//FIN DEL DOPOST
	/***********************FIN FORMATOS************************/
	if(va.equals("6")){
		MetodoSeguimientoAdmision msa=new MetodoSeguimientoAdmision();
		String CodInDia ="";
		/*String Ingresado=null;		
		try {
			rs1=mp.VerificarDiagnosticoRepetido(CodAdmCola, CedPac);
			if(rs1.next()){
				Ingresado=rs1.getString(1);				
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			System.out.println("ERROR en Valor Igual 6 ControladorMedico ResultSet "+e);
			e.printStackTrace();
		}*/
		//if(Ingresado==null){
			//*
			try {
				//if(DiagPrinc.equals("1")){
					//msa.CrearRelacionDiagnosticoAdmision(CodDiagnostico, CodAdm, CodPac, usuario, hora, fecha);
				//}
				String CodDx="";
				rs=mp.ObtenerCodigoDiagnostico(CodDiagnostico);
				if(rs.next()){
					CodDx=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				mp.InsertarDiagnosticoCola(CodDx, CodAdmCola, CodPac, usuario, fecha, hora,observacion);
				rs=mp.ObtenerInsercionDiagnostico(CodAdmCola, fecha, hora);
				if(rs.next()){						
					CodInDia=rs.getString(1);
					if(CodInDia!=""){
						out.print("Diagnostico Asignado Exitosamente.");
					}
					else{
						out.print("No Se Ingreso El Diagnostico.");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("ERROR en Valor Igual 6 ControladorMedico ResultSet "+e);
				e.printStackTrace();
			}
			
		/*}else{
			out.print("Ya Existe Un Diagnostico Para Esta Preadmision.");
		}*/
	}
	if(va.equals("7")){
		String Resul=req.getParameter("Resul");
		String CodResul=req.getParameter("CodResul");
		mp.ActualizarResultadosPreadmision(Resul, CodResul);
	}
	if(va.equals("8")){
		//
		mp.ActualizarPreguntasFormatosCola(HoraFormato, FechaFormato);
		mp.ActualizarFormatosPreingreso(CodigoFormatoPaciente);
		//mp.FinalizarCola(CodAdmCola);
		
	}
	
	// * este procedimiento verifica si el formato est apendiente y procede a sacar al 
	/// * paciente de la cola de espera.
	 if(va.equals("11")){
		rs=mp.VerificarFormatosPendientes(CodAdmCola);
		String validar="";
		try {
			if(rs.next()){
				validar=rs.getString(1);
				if(validar!=""){
					out.print("Tiene Formatos Sin Finalizar.");
				}
				
			}
			else{
				mp.InsertarDatosFinalCola(TipoSalida, TipoTriage, CodCola_Fk, CodUsu_Fk, fecha, hora,direccionar,Estado);
				mp.FinalizarAtencion(CodAdmCola);
				out.print("Atencion Finalizada Con Exito.");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	if(va.equals("9")){
		out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' id='cabecera2' class='style11'>Finalizar Atencion Triage </div></td></tr>");
		out.print("<tr><td width='13%'>Clasificacion Triage </td><td width='87%'><select name='cmbTipoTriage' id='cmbTipoTriage'><option value='Seleccione' selected='selected'>Seleccione</option><option value='I'>I</option><option value='II'>II</option><option value='III'>III</option><option value='IV'>IV</option></select></td></tr>");
		out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11' >Tipo de Salida </div></td></tr>");
		out.print("<tr><td colspan='2'><label><input name='radiobutton' type='radio' id='1' value='Atencion Finalizada' />Atencion Finalizada</label></td></tr>");
		out.print("<tr><td colspan='2'><label><input name='radiobutton' type='radio' id='2' value='Cita Por Consulta Externa' />Cita Por Consulta Externa</label></td></tr>");
		out.print("<tr><td colspan='2'><label><input name='radiobutton' type='radio' id='3' value='Atencion Por Urgencia' />Atencion Por Urgencia</label></td></tr>");
		out.print("<tr><td colspan='2'><div align='center'><input name='btnFinalizarCola' type='button' id='btnFinalizarCola' value='Finalizar Atencion' onclick='DarSalida()' /></div></td></tr></table>");
		
	}
	if(va.equals("10")){
		mp.OmitirRegistrosFormatoCola(FechaFormato,HoraFormato);
		mp.OmitirFormatoColaPaciente(CodigoFormatoPaciente);
	}
	
	ResultSet rsau=null;	
	//res.setContentType("text/html;charset=UTF-8");
	String cad =req.getParameter("codigo");  
	//MetodoPreingreso mp=new MetodoPreingreso();
	//int accion;
	//String accion = req.getParameter("txtAccion");
	if(va.equals("26")){
		try {
			rsau =mp.AutocompletarCIE10(cad);
			String cadena ="";
			String nombre ="";
			cadena="[";
			while(rsau.next()){
				nombre=rsau.getString(2);
            	cadena = cadena+"'"+nombre+"|"+rsau.getString(1)+"'";
            	cadena = cadena +",";	
			}
			cadena = cadena+"]";
			System.out.print("cadena "+cadena);
			res.getWriter().write(cadena);
			rsau.getStatement().getConnection().close();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		ResultSet rs=null;	
		res.setContentType("text/html;charset=UTF-8");
		String cad =req.getParameter("codigo");  
		MetodoPreingreso mp=new MetodoPreingreso();
		int accion;
		accion = Integer.parseInt(req.getParameter("txtAccion"));
		if(accion == 26){
			try {
				rs =mp.AutocompletarCIE10(cad);
				String cadena ="";
				String nombre ="";
				cadena="[";
				while(rs.next()){
					nombre=rs.getString(2);
	            	cadena = cadena+"'"+nombre+"|"+rs.getString(1)+"'";
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
	

}