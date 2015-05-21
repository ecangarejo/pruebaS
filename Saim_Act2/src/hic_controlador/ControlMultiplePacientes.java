/**
 * controlador: ControlMultiplePacientes se encuentra la forma en que se 
 * buscan varios pacientes al tiempo y se les asigna su menu en forma de pesta�as
 * y su menu de opciones.
 *  */
package hic_controlador;



import hic_metodo.MetodoMultiplePacientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControlMultiplePacientes
 */
public class ControlMultiplePacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoMultiplePacientes mmp=new MetodoMultiplePacientes();		
		int accion;	     
		accion = Integer.parseInt(req.getParameter("txtAccion"));
		
		if(accion == 26){          
			/**
			 * se autocompleta los tipos de procedimientos quirurgicos.
			 */
			try {
				rs =mmp.AutocompletarQuirurjicos(cad);
				String cadena ="";
				String servicio ="";
	            cadena="[";
	            while(rs.next()){
	            	servicio=rs.getString(1);
	            	cadena = cadena+"'"+servicio+"|"+rs.getString(2)+"'";
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
		
		if(accion==27){
			/**
			 * se autocompleta los tipos de medicamentos.
			 */
			if(cad!=""){
			}
			if(cad!=" "){
				String tipo=req.getParameter("tipo");
				if(tipo==null){
					tipo="0";
				}
				if(tipo.equals("0")){
					//tipo="null";
					try {
						rs =mmp.AutocompletarMedicamentosMedico(cad);
						String cadena ="";
						String medicamento ="";
						cadena="[";
						while(rs.next()){
							medicamento=rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(4);
							cadena = cadena+"'"+medicamento+"|"+rs.getString(3)+"'";
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
				if(tipo.equals("1")){
					try {
						rs =mmp.AutocompletarMedicamentos(cad,tipo);
						String cadena ="";
						String medicamento ="";
						cadena="[";
						while(rs.next()){
							medicamento=rs.getString(1)+"/"+rs.getString(2);
							cadena = cadena+"'"+medicamento+"|"+rs.getString(3)+"'";
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
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		String CodPac = req.getParameter("CodPac");
		String NomPac = req.getParameter("NomPac");
		String Diagnos= req.getParameter("Diagnos");
		
		String hora=req.getParameter("hora");
		String fecha=req.getParameter("fecha");
		String usuario=req.getParameter("usuario");
		String Alergia=req.getParameter("Alergia");
		String Reaccion=req.getParameter("Reaccion");
		String Estudio=req.getParameter("Estudio");
		
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
		String cedula=req.getParameter("cedula");
		
		String CedPac=req.getParameter("CedPac");
		
		String CodigoAntPersonal=req.getParameter("CodigoAntPersonal");
		String CodigoQx=req.getParameter("CodigoQx");
		String CodigoAler=req.getParameter("CodigoAler");
		String CodigoTx=req.getParameter("CodigoTx");
		String CodigoAntFamiliar=req.getParameter("CodigoAntFamiliar");
		String CodigoTranfucion=req.getParameter("CodigoTranfucion");
		String CodigoMedicamento=req.getParameter("CodigoMedicamento");
		String CodigoGineco=req.getParameter("CodigoGineco");		
		String CodUnidad=req.getParameter("CodUnidad");
		
		MetodoMultiplePacientes mmp=new MetodoMultiplePacientes();
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;	
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rs8=null;	
		ResultSet rs9=null;
		
		/**************************INICIO EPICRISIS***************************/
		String CodAdm1=req.getParameter("CodAdm");
		String codusu=req.getParameter("codusu");
		String Resumen=req.getParameter("Resumen");
		String Manejo=req.getParameter("Manejo");
		String ProcEst=req.getParameter("ProcEst");
		String Servicio=req.getParameter("Servicio");
		//String CodPac=req.getParameter("codusu");
		if(va.equals("ep1")){
			try{
				rs1=mmp.BuscarEpicrisis(CodAdm1);
				if(rs1.next()){
					//poner aqui la epicrisis con los datos.
					rs=mmp.BuscarDatosEpicrisis(CodAdm1);
					if(rs.next()){
						/*out.print("<table border='0' width='100%' ><tr><td colspan='10'><div id='cabecera2' class='style11' align='center'>EPICRISIS "+rs1.getString(7)+"</div></td></tr><tr bgcolor='#CCCCCC'><td colspan='10' class='style12'>A.) IDENTIFICACION </td></tr>");
						out.print("<tr><td width='14%' class='style12'>Identificacion</td><td width='15%'>"+rs.getString(1)+"</td><td width='9%' class='style12'>Primer Apellido </td><td width='17%'>"+rs.getString(2)+"</td><td width='10%' class='style12'>Segundo Apellido </td>");
						out.print("<td width='12%'>"+rs.getString(3)+"</td><td width='6%' class='style12'>Nombre</td><td colspan='3'>"+rs.getString(4)+"</td></tr>");
						out.print("<tr><td class='style12'>No Historia Clinica </td><td>"+CodAdm1+"</td><td class='style12'>Entidad</td><td colspan='3'>"+rs.getString(5)+"</td><td class='style12'>Edad</td>");
						out.print("<td width='2%'>"+rs.getString(6)+"</td><td width='4%' class='style12'>Genero</td><td width='11%'>"+rs.getString(7)+"</td></tr>");
						out.print("<tr><td class='style12'>Fecha Inicio </td><td>"+rs.getString(8)+"</td><td class='style12'>Servicio </td><td>"+rs.getString(9)+"</td><td class='style12'>Fecha Fin </td><td>"+rs.getString(10)+"</td>");
						out.print("<td class='style12'>Servicio</td><td colspan='3'>"+rs.getString(11)+"</td></tr><tr class='style6' bgcolor='#CCCCCC'><td colspan='10' class='style12'>B.) DIAGNOSTICO,PROCEDIMIENTO Y TRATAMIENTO </td></tr>");
						rs2=mmp.BuscarDiagnosticosEpicrisis(CodAdm1);
						if(rs2.next()){
							out.print("<tr><td class='style12'>Diagnostico de Ingreso </td><td colspan='9'>"+rs2.getString(2)+"</td></tr>");
							out.print("<tr><td class='style12'>Diagnostico de Egreso </td><td colspan='9'>"+rs2.getString(1)+"</td></tr>");
						
						} 
						rs2.getStatement().getConnection().close();
						out.print("<tr><td class='style12'>Procedimientos/Estudios Realizados </td><td colspan='9'>"+rs1.getString(8)+"</td></tr>");
						out.print("<tr><td class='style12'>Manejo</td><td colspan='9'>"+rs1.getString(9)+"</td></tr></table>");
						
						out.print("<table border='0' width='100%' ><tr bgcolor='#CCCCCC'><td colspan='4' class='style12'>C.) ORDENAMIENTO </td></tr>");
						out.print("<tr><td width='30%' class='style2'>1. RESUMEN DE ANAMNESIS Y EXAMEN FISICO </td><td width='20%' class='style2'>2. RESUMEN DE EVOLUCION </td><td width='25%' class='style2'>3. COMPLICACIONES </td>");
						out.print("<td width='25%'  class='style2'>4. CONDICION DEL PACIENTE A LA FINALIZACION(SE�ALAR INCAPACIDAD FUNCIONAL SI LA HUBIERE) </td></tr>");
						out.print("<tr><td class='style2'>5. PRONOSTICO</td><td class='style2'>6. RECOMENDACIONES </td><td class='style2'>7. FECHA Y RESULTADO DE EXAMENES AUXILIARES DE DIAGNOSTICO </td><td class='style2'>8. FIRMA Y CODIGO DEL PROFESIONAL RESPONSABLE </td></tr>");
						out.print("<tr bgcolor='#CCCCCC'><td colspan='4' class='style12'>RESUMEN</td>");
						out.print("<tr><td colspan='4' >"+rs1.getString(10)+"</td></tr>");
						out.print("<tr><td colspan='4' class='style12'><div align='center'><input name='btnGuardar' type='button' id='btnImprimir' value='Imprimir' class='boton4' onclick='ImprimirEpicrisis("+rs1.getString(1)+")'></div></td></tr></table>");
					*/
						out.print("<table border='0' width='100%' ><tr><td colspan='10'><div id='cabecera2' class='style11' align='center'>EPICRISIS <select id='cmbServicio'><option value='Seleccione' selected=''>Seleccione</option><option value='HOSPITALIZACION'>HOSPITALIZACION</option><option value='UCI'>UCI</option></select> </div></td></tr><tr bgcolor='#CCCCCC'><td colspan='10' class='style12'>A.) IDENTIFICACION </td></tr>");
						out.print("<tr><td width='14%' class='style12'>Identificacion</td><td width='15%'>"+rs.getString(1)+"</td><td width='9%' class='style12'>Primer Apellido </td><td width='17%'>"+rs.getString(2)+"</td><td width='10%' class='style12'>Segundo Apellido </td>");
						out.print("<td width='12%'>"+rs.getString(3)+"</td><td width='6%' class='style12'>Nombre</td><td colspan='3'>"+rs.getString(4)+"</td></tr>");
						out.print("<tr><td class='style12'>No Historia Clinica </td><td>"+CodAdm1+"</td><td class='style12'>Entidad</td><td colspan='3'>"+rs.getString(5)+"</td><td class='style12'>Edad</td>");
						out.print("<td width='2%'>"+rs.getString(6)+"</td><td width='4%' class='style12'>Genero</td><td width='11%'>"+rs.getString(7)+"</td></tr>");
						out.print("<tr><td class='style12'>Fecha Inicio </td><td>"+rs.getString(8)+"</td><td class='style12'>Servicio </td><td>"+rs.getString(9)+"</td><td class='style12'>Fecha Fin </td><td>"+rs.getString(10)+"</td>");
						out.print("<td class='style12'>Servicio</td><td colspan='3'>"+rs.getString(11)+"</td></tr><tr class='style6' bgcolor='#CCCCCC'><td colspan='10' class='style12'>B.) DIAGNOSTICO,PROCEDIMIENTO Y TRATAMIENTO </td></tr>");
						rs2=mmp.BuscarDiagnosticosEpicrisis(CodAdm1);
						if(rs2.next()){
							out.print("<tr><td class='style12'>Diagnostico de Ingreso </td><td colspan='9'>"+rs2.getString(2)+"</td></tr>");
							out.print("<tr><td class='style12'>Diagnostico de Egreso </td><td colspan='9'>"+rs2.getString(1)+"</td></tr>");
						
						} 
						rs2.getStatement().getConnection().close();
						out.print("<tr><td class='style12'>Procedimientos/Estudios Realizados </td><td colspan='9'><label><textarea name='txtProcEst' cols='70' rows='4' id='txtProcEst'></textarea></label></td></tr>");
						out.print("<tr><td class='style12'>Manejo</td><td colspan='9'><label><textarea name='txtManejo' cols='70' rows='4' id='txtManejo'></textarea></label></td></tr></table>");
						
						out.print("<table border='0' width='100%' ><tr bgcolor='#CCCCCC'><td colspan='4' class='style12'>C.) ORDENAMIENTO </td></tr>");
						out.print("<tr><td width='30%' class='style2'>1. RESUMEN DE ANAMNESIS Y EXAMEN FISICO </td><td width='20%' class='style2'>2. RESUMEN DE EVOLUCION </td><td width='25%' class='style2'>3. COMPLICACIONES </td>");
						out.print("<td width='25%'  class='style2'>4. CONDICION DEL PACIENTE A LA FINALIZACION(SE�ALAR INCAPACIDAD FUNCIONAL SI LA HUBIERE) </td></tr>");
						out.print("<tr><td class='style2'>5. PRONOSTICO</td><td class='style2'>6. RECOMENDACIONES </td><td class='style2'>7. FECHA Y RESULTADO DE EXAMENES AUXILIARES DE DIAGNOSTICO </td><td class='style2'>8. FIRMA Y CODIGO DEL PROFESIONAL RESPONSABLE </td></tr>");
						out.print("<tr><td colspan='4' class='style12' align='center'><label><textarea name='txtResumen' cols='90' rows='10' id='txtResumen'></textarea></label></td></tr>");
						out.print("<tr><td colspan='4' class='style12'><div align='center'><input name='btnGuardar' type='button' id='btnGuardar' value='Guardar' class='boton4' onclick='GuardarEpicrisis()'></div></td></tr></table>");

						}
					rs.getStatement().getConnection().close();
				}else{
					rs=mmp.BuscarDatosEpicrisis(CodAdm1);
					if(rs.next()){
						out.print("<table border='0' width='100%' ><tr><td colspan='10'><div id='cabecera2' class='style11' align='center'>EPICRISIS <select id='cmbServicio'><option value='Seleccione' selected=''>Seleccione</option><option value='HOSPITALIZACION'>HOSPITALIZACION</option><option value='UCI'>UCI</option></select> </div></td></tr><tr bgcolor='#CCCCCC'><td colspan='10' class='style12'>A.) IDENTIFICACION </td></tr>");
						out.print("<tr><td width='14%' class='style12'>Identificacion</td><td width='15%'>"+rs.getString(1)+"</td><td width='9%' class='style12'>Primer Apellido </td><td width='17%'>"+rs.getString(2)+"</td><td width='10%' class='style12'>Segundo Apellido </td>");
						out.print("<td width='12%'>"+rs.getString(3)+"</td><td width='6%' class='style12'>Nombre</td><td colspan='3'>"+rs.getString(4)+"</td></tr>");
						out.print("<tr><td class='style12'>No Historia Clinica </td><td>"+CodAdm1+"</td><td class='style12'>Entidad</td><td colspan='3'>"+rs.getString(5)+"</td><td class='style12'>Edad</td>");
						out.print("<td width='2%'>"+rs.getString(6)+"</td><td width='4%' class='style12'>Genero</td><td width='11%'>"+rs.getString(7)+"</td></tr>");
						out.print("<tr><td class='style12'>Fecha Inicio </td><td>"+rs.getString(8)+"</td><td class='style12'>Servicio </td><td>"+rs.getString(9)+"</td><td class='style12'>Fecha Fin </td><td>"+rs.getString(10)+"</td>");
						out.print("<td class='style12'>Servicio</td><td colspan='3'>"+rs.getString(11)+"</td></tr><tr class='style6' bgcolor='#CCCCCC'><td colspan='10' class='style12'>B.) DIAGNOSTICO,PROCEDIMIENTO Y TRATAMIENTO </td></tr>");
						rs2=mmp.BuscarDiagnosticosEpicrisis(CodAdm1);
						if(rs2.next()){
							out.print("<tr><td class='style12'>Diagnostico de Ingreso </td><td colspan='9'>"+rs2.getString(2)+"</td></tr>");
							out.print("<tr><td class='style12'>Diagnostico de Egreso </td><td colspan='9'>"+rs2.getString(1)+"</td></tr>");
						
						} 
						rs2.getStatement().getConnection().close();
						out.print("<tr><td class='style12'>Procedimientos/Estudios Realizados </td><td colspan='9'><label><textarea name='txtProcEst' cols='70' rows='4' id='txtProcEst'></textarea></label></td></tr>");
						out.print("<tr><td class='style12'>Manejo</td><td colspan='9'><label><textarea name='txtManejo' cols='70' rows='4' id='txtManejo'></textarea></label></td></tr></table>");
						
						out.print("<table border='0' width='100%' ><tr bgcolor='#CCCCCC'><td colspan='4' class='style12'>C.) ORDENAMIENTO </td></tr>");
						out.print("<tr><td width='30%' class='style2'>1. RESUMEN DE ANAMNESIS Y EXAMEN FISICO </td><td width='20%' class='style2'>2. RESUMEN DE EVOLUCION </td><td width='25%' class='style2'>3. COMPLICACIONES </td>");
						out.print("<td width='25%'  class='style2'>4. CONDICION DEL PACIENTE A LA FINALIZACION(SE�ALAR INCAPACIDAD FUNCIONAL SI LA HUBIERE) </td></tr>");
						out.print("<tr><td class='style2'>5. PRONOSTICO</td><td class='style2'>6. RECOMENDACIONES </td><td class='style2'>7. FECHA Y RESULTADO DE EXAMENES AUXILIARES DE DIAGNOSTICO </td><td class='style2'>8. FIRMA Y CODIGO DEL PROFESIONAL RESPONSABLE </td></tr>");
						out.print("<tr><td colspan='4' class='style12' align='center'><label><textarea name='txtResumen' cols='90' rows='10' id='txtResumen'></textarea></label></td></tr>");
						out.print("<tr><td colspan='4' class='style12'><div align='center'><input name='btnGuardar' type='button' id='btnGuardar' value='Guardar' class='boton4' onclick='GuardarEpicrisis()'></div></td></tr></table>");
					}else{
						out.println("DEBE DAR SALIDA AL PACIENTE ANTES DE GENERAR LA EPICRISIS.");
					}
					rs.getStatement().getConnection().close();
					rs1.getStatement().getConnection().close();
				}
				}
				catch (SQLException e) {
					System.out.println("ERROR EN VA EQUAL 1 ControlMultiplePacientes "+e);
					e.printStackTrace();
				}
		}
		if(va.equals("ep2")){
			java.util.Date fechaActual = new java.util.Date();
			
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime()); 
			mmp.InsertarEpicrisis(CodAdm1, codusu, CodPac, Resumen, Manejo, ProcEst, Hora, Fecha,Servicio);
		}
		if(va.equals("NOACTIVO")){
			out.println("<p class=style67>USTED NO TIENE ESTA OPCION ACTIVADA PORQUE TIENE FORMATOS PENDIENTES POR CERRAR</p>");
			out.println("<p  class=style66><u><i><a href=menu.jsp>CERRAR FORMATO</a></i></u></p>");
		}
		/*****************************FIN EPICRISIS***************************/
		if(va.equals("1")){
			/**
			 * se crean las pesta�as con los pacientes seleccionados con anterioridad.
			 */
			int  NumPestanas=0;		
			String pacientes=req.getParameter("pacientes");
			String CodigoPaciente="";
			String valor = pacientes;
			StringTokenizer tk = new StringTokenizer(valor, "|"); // Cambia aqu� el separador
			out.print("<table width='100%' border='1'><tr><td width='100%'><div><div id='tabsJ'><ul>");
			while(tk.hasMoreElements()){
				CodigoPaciente=tk.nextToken();		    		
				rs4=mmp.DatosPaciente(CodigoPaciente);
				//rs3=mmp.VerificarAdmision(CodigoPaciente);
				NumPestanas=NumPestanas+1;
				
				try {					
					if(rs4.next()){
						//out.print("<li><a href='#' onclick='cambio("+rs4.getString(4)+","+rs4.getString(5)+");' id='tablink"+NumPestanas+"'><span>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</span></a></li>");
						out.print("<li><a href='#' onclick='cambio("+rs4.getString(4)+");' id='tablink"+NumPestanas+"'><span>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</span></a></li>");
					}
					
					rs4.getStatement().getConnection().close();
					//rs3.getStatement().getConnection().close();
				} 				
				catch (SQLException e) {
					System.out.println("ERROR EN VA EQUAL 1 ControlMultiplePacientes "+e);
					e.printStackTrace();
				}
				
			}//fin del tokenrise
			out.print("</ul></div><div id='contenido'></div></div></td></tr></table>");
		}
		if(va.equals("2")){
			String CodAdm="";			
			rs9=mmp.VerificarAdmision(CodPac);
			try {
				if(rs9.next()){ 
					CodAdm=rs9.getString(1);
				}	
				rs9.getStatement().getConnection().close();
				if(CodAdm!=""){
					rs4=mmp.VerificarDatosAdmision(CodPac,CodAdm);
					if(rs4.next()){		
						//out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td style='color:#215b8b' align='center'><b> MEDICO TRATANTE:  "+rs4.getString(17)+"</b></td></tr></table>");
						
						out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</div></td><td width='9%' style='color:#215b8b'>Identificacion</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(6)+" "+rs4.getString(7)+"</div><input name='CedPac' type='hidden' id='CedPac' value='"+rs4.getString(7)+"' /></td><td width='12%' style='color:#215b8b'>Fecha Nacimiento</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(5)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Edad:"+rs4.getString(4)+"</div></td></tr></table>");
						
						out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='5%' style='color:#215b8b'>Entidad:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(11)+"</div></td><td width='35%' style='color:#215b8b'><b>Medico Tratante:  "+rs4.getString(17)+"</b></td><td width='10%' style='color:#215b8b'>Servicio y Ubicacion:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(9)+" Cama "+rs4.getString(10)+"</div><input name='txtCodCama' type='hidden' id='txtCodCama' value="+rs4.getString(12)+"  /> <input name='txtServPac' type='hidden' id='txtServPac' value="+rs4.getString(9)+"  /> <input name='txtCamaPac' type='hidden' id='txtCamaPac' value="+rs4.getString(10)+"  /> <input name='txtCodSubarea' type='hidden' id='txtCodSubarea' value="+rs4.getString(14)+"  /> <input name='txtCodArea' type='hidden' id='txtCodArea' value="+rs4.getString(13)+"  /><input name='txtCodEntidad' type='hidden' id='txtCodEntidad' value="+rs4.getString(15)+"  /></td></tr></table>");
						
						out.print("<br>");			
						/**************menu de la izq*************/
						out.print("<table width='100%' border='1' cellspacing='0'><tr><td width='15%'><div id='MenuVertical'> <div id='button'><ul>");
						rs8=mmp.VerificarPermisosHC(usuario);
						if(rs8.next()){
							rs7=mmp.VerificarPermisosHC(usuario);
							while(rs7.next()){
								if(rs7.getString(1).equals("1")){
									//Permiso de Antecedentes
									out.print("<li><a href='#' onclick='Antecedentes()'>Antecedentes</a></li>");
								
								}
								if(rs7.getString(1).equals("2")){
									//Permiso de Formatos
									out.print("<li><a href='#' onclick='Formatos()'>Formatos</a></li>");
									
								}
								if(rs7.getString(1).equals("3")){
									//Permiso de Orden De Servicio
									out.print("<li><a href='#' onclick='OrdenServicio()'>Ordenes Medicas</a></li>");
									
								}
								if(rs7.getString(1).equals("4")){
									//Permiso de Medicamento
									out.print("<li><a href='#' onclick='MostrarMedicamentos()'>Medicamentos</a></li>");
								}
								
								if(rs7.getString(1).equals("5")){
									//Permiso de Laboratorios
									out.print("<li><a href='#' onclick='MenuLaboratorio()'>Laboratorios</a></li>");
			
								}
								if(rs7.getString(1).equals("6")){
									//Permiso de Imagenologia
									out.print("<li><a href='#' onclick='MostrarImagenologia()'>Imagenologia</a></li>");
									
								}
								if(rs7.getString(1).equals("7")){
									//Permiso de Clasificacion de Diagnostico
									out.print("<li><a href='#' onclick='ClasiDiagnostico()'>Clasificacion de Diagnostico</a></li>");
			
								}
								if(rs7.getString(1).equals("8")){
									//Permiso de Atenciones Anteriores
									out.print("<li><a href='#' onclick='MostrarAtenciones()'>Atenciones Anteriores</a></li>");
								}
								
								if(rs7.getString(1).equals("9")){
									//Permiso de INSUMOS HOSPITALARIOS
									out.print("<li><a href='#' onclick='MostrarInsumosHospitalarios()'>Insumos Hospitalarios</a></li>");
								}
								if(rs7.getString(1).equals("10")){
									//Permiso de Epicrisis
									out.print("<li><a href='#' onclick='MostrarEpicrisis()'>Epicrisis</a></li>");
								}
								if(rs7.getString(1).equals("11")){
									//Permiso de Descripcion quirurgica
									out.print("<li><a href='#' onclick='MostrarDQx("+CodAdm+","+rs4.getString(16)+")'>Descripcion QX</a></li>");
								}
								java.util.Date fechaActual = new java.util.Date();
								java.sql.Date fechaK = new java.sql.Date(fechaActual.getTime());	
								
								if(rs7.getString(1).equals("12")){
									//Permiso de Kardex
									//out.print("<li><a href='#' onclick='MostrarKardexK("+CodAdm+",&quot;"+fechaK+"&quot;)'>Kardex</a></li>");
									out.print("<li><a href='#' onclick='Enfermeria("+CodAdm+",&quot;"+fechaK+"&quot;)'>Enfermeria</a></li>");
								}
							
								
								if(rs7.getString(1).equals("13")){
									//Permiso de interconsulta
									//out.print("<li><a href='#' onclick='MostrarKardexK("+CodAdm+",&quot;"+fechaK+"&quot;)'>Kardex</a></li>");
									out.print("<li><a href='#' onclick=' interconsultas()'>Interconsultas</a></li>");
								}
								
								
							}
							rs7.getStatement().getConnection().close();
						}else{
							out.print("NO TIENE NINGUN PERMISO AUTORIZADO.");
						}
						rs8.getStatement().getConnection().close();
						
						out.print("</ul>");
						out.print("</div></div><input name='CodAdm' type='hidden' id='CodAdm' value='"+CodAdm+"' /></td><td width='85%'><div id='HistoriaPaciente'>");
						/*************SNAPSHOT********************/
						out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' id='cabecera2' class='style11' >RESUMEN DE LA HISTORIA </div></td></tr>");
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						/*
						
						
						
						
						out.print("<tr class='style6'><td width='24%'><div align='center'>Alergias</div></td><td width='27%'><div align='center'>Medicamentos</div></td><td width='24%'><div align='center'>Laboratorios</div></td><td width='25%'><div align='center'>Imagenologia</div></td></tr>");
						out.print("<tr><td bordercolor='#FF0000' ><div id='ResumenAlergias'><table width='100%' border='1' >");
						
						//***************ALERGIAS PRINCIPALES****************************
						rs=mmp.VerificarAlergiasLimite(CodPac);
						while(rs.next()){
						out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
						}
						out.print("<tr><td align='center' ><a href='#' onclick='Antecedentes()' >Mas Informacion Antecedentes</a></td></tr>");
						rs.getStatement().getConnection().close();
						//************FIN ALERGIAS PRINCIPALES***************************
						
						out.print("</table></div></td><td bordercolor='#0033FF' ><div id='ResumenMedicamentos'><table width='100%' border='1' >");
						
						//***************MEDICAMENTOS************************************
						rs5=mmp.VerificarMedicamentosLimite(CodPac);
						while(rs5.next()){
							out.print("<tr><td>"+rs5.getString(1)+"</td></tr>");
						}
						rs5.getStatement().getConnection().close();
						out.print("<tr><td>Ultimos Medicamentos >> <a href='#' onclick='MostrarMedicamentos()' >Mas Info</a></td></tr>");
						//*************FIN MEDICAMENTOS**********************************
						
						out.print("</table></div></td><td bordercolor='#009900' ><div id='ResumenLaboratorios'><table width='100%' border='1' >");
						
						//**************ULTIMOS 5 LABORATORIOS****************************
						rs6=mmp.VerificarLaboratoriosLimite(CodPac);
						while(rs6.next()){
							String Tipo=rs6.getString(10);
							String FechaIni=rs6.getString(1);
							String HoraIni=rs6.getString(2);
							String dia,mes,anio=null; 
							String horas,minutos,segundos=null;
							  
							dia=FechaIni.substring(8,10);
							mes=FechaIni.substring(5,7);
							anio=FechaIni.substring(0,4);
							
							horas=HoraIni.substring(0,2);
							minutos =HoraIni.substring(3,5);
							segundos=HoraIni.substring(6,8);
							String codgenero="1";
							if(Tipo.equals("1")){
								//**Laboratorios Tipo Texto**
								out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+1+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
							}
							
							if(Tipo.equals("2")){
								//**Laboratorios Tipo Rango**
								out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+2+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+","+rs6.getString(8)+","+rs6.getString(9)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
							}
							
							if(Tipo.equals("3")){
								//**Laboratorios En Grupo**
								out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir("+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs6.getString(4)+","+rs6.getString(3)+","+codgenero+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
							}
						}
					rs6.getStatement().getConnection().close();
						out.print("<tr><td>Ultimos Laboratorios >> <a href='#' onclick='MenuLaboratorio()' >Mas Info</a> </td></tr>");
						//***********FIN ULTIMOS LABORATORIOS***************************
						
						out.print("</table></div></td><td bordercolor='#CC6600'><div id='ResumenImagenologia'><table width='100%' border='1' >");
						
						//**********ULTIMOS 5 IMAGENES***********************************
						ResultSet rsImgEco=null;
						ResultSet rsImgRmc=null;
						rs1=mmp.VerificarImagenesLimite(CodPac);
						rsImgEco=mmp.VerificarEcoLimite(CodPac);
						rsImgRmc=mmp.VerificarRmcLimite(CodPac);
						while(rs1.next()){
						out.print("<tr><td><a  href='#'onclick='mostarexamenes("+rs1.getString(4)+","+rs1.getString(5)+")'>"+rs1.getString(3)+"</a></td></tr>");
						}
						
						while(rsImgEco.next()){
							out.print("<tr><td><a  href='#'onclick='mostrarInformesCardiologia("+rsImgEco.getString(4)+")'>"+rsImgEco.getString(3)+"</a></td></tr>");
							}
						
						while(rsImgRmc.next()){
							out.print("<tr><td><a  href='#'onclick='mostrarInformesRmc("+rsImgRmc.getString(4)+")'>"+rsImgRmc.getString(3)+"</a></td></tr>");
							}
						
						out.print("<tr><td align='center' >Ultimas Imagenes >> <a href='#' onclick='MostrarImagenologia()' >Mas Info</a></td></tr>");
						rs1.getStatement().getConnection().close();
						rsImgEco.getStatement().getConnection().close();
						rsImgRmc.getStatement().getConnection().close();
						//**********FIN ULTIMOS 5 IMAGENES*******************************
						
						out.print("</table></div></td></tr><tr class='style6' ><td><div align='center'>Formatos Activos</div></td><td><div align='center'>Ordenes Medicas</div></td><td colspan='2'><div align='center'>Imagenes Diagnosticas Pendientes</div> </td></tr><tr><td bordercolor='#FFFF00' ><div id='ResumenFormatosActivos'><table width='100%' border='1' >");
						
						//*************ULTIMOS 5 FORMATOS REALIZADOS********************
						rs3=mmp.VerificarFormatosLimite(CodAdm, CodPac);
						while(rs3.next()){
							String FechaIni=rs3.getString(3);
							String HoraIni=rs3.getString(4);
							String dia,mes,anio=null; 
							String horas,minutos,segundos=null;
							  
							dia=FechaIni.substring(8,10);
							mes=FechaIni.substring(5,7);
							anio=FechaIni.substring(0,4);
							
							horas=HoraIni.substring(0,2);
							minutos =HoraIni.substring(3,5);
							segundos=HoraIni.substring(6,8);
							out.print("<tr><td><a  href='#' onclick='ImprimirFormato("+rs3.getString(7)+","+rs3.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs3.getString(8)+","+rs3.getString(11)+","+CodAdm+","+rs3.getString(10)+")'>"+rs3.getString(2)+"</a></td></tr>");
						}
						rs3.getStatement().getConnection().close();
						out.print("<tr><td align='center'><a href='#' onclick='Formatos()' >Mas Informacion Formatos</a></td></tr>");
						//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************
						
						out.print("</table></div></td><td bordercolor='#000000' ><div id='resumenOrdenesMedicas'><table width='100%' border='1' >");
						
						//**************ULTIMAS 5 ORDENES MEDICAS*******************
						rs2=mmp.VerificarOrdenesMedicasLimite(CodAdm);
						while(rs2.next()){
							
							String Tipo=rs2.getString(5);
							if(Tipo.equals("1")){
								//**1=LABORATORIO**
								out.print("<tr><td><a href='#' onclick='ReportLabora("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>LABORATORIO</td></tr>");
							}
							
							if(Tipo.equals("2")){
								//**2=IMAGENOLOGIA**
								out.print("<tr><td><a href='#' onclick='ReportImage("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>IMAGENOLOGIA</td></tr>");
							}
							
							if(Tipo.equals("3")){
								//**3=MEDICAMENTOS**
								out.print("<tr><td><a href='#' onclick='ReportMedica("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>MEDICAMENTOS e INSUMOS</td></tr>");
							}
							
							if(Tipo.equals("4")){
								//**4=GENERAL**
								out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>ORDEN GENERAL</td></tr>");
								
							}
						}
						out.print("<tr><td  align='center' ><a href='#' onclick='OrdenServicio()' >Mas Informacion Ordenes Medicas</a></td></tr>");
						rs2.getStatement().getConnection().close();
					//**********FIN ULTIMAS 5 ORDENES MEDICAS******************
						
						//imagenologias pendientes
						out.print("</table></div></td><td colspan='2' bordercolor='#0033FF' ><div id='resumenOrdenesMedicas'><table width='100%' border='1' >");
						out.print("<td ><div id='CatCex'>");
						rs3=mmp.CargarImagenologiasPendientes(CodPac,CodAdm);
						out.print("<table  width='100%' border='1' cellspacing='0'  ><tr></td></tr>");
						while(rs3.next()){
							out.print("<tr><td>"+rs3.getString(1)+"</td></tr>");
						}
						
						rs3.getStatement().getConnection().close();
						out.print("</table>");
						///*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************
						//fgj
						out.print("</div></td>");
						
						
						out.print("</table></div></td></tr></table>");
						out.print("");
						out.print("");
						out.print("");
						out.print("");
						
						
						*/
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						/**********FIN SNAPSHOT*******************/
						
					//	out.print("</div></td></tr></table>");
					}
					rs4.getStatement().getConnection().close();
				}
				//rs9.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				System.out.println("Error en ControlMultiplepaciente valor=2 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("3")){
			int Cont=0;
			out.print("<table width='100%' border='0'>");
			String validar="";
			try {
				rs3=mmp.listarPacientesPorAtender(NomPac);
				while(rs3.next()){
					validar=rs3.getString(2);
					out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
					Cont=Cont+1;					
				}
				out.print("<input name='txtCont' type='hidden' id='txtCont' value='"+Cont+"' />");
				if(validar==""){
					out.print("No Existe Registro de Este Paciente.");
				}
				rs3.getStatement().getConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</table>");			
		}
		if(va.equals("3.0")){
			int Cont=0;
			String CodSubarea=req.getParameter("CodSubarea");
			out.print("<table width='100%' border='0'>");
			String validar="";
			try {
				rs3=mmp.listarPacientesPorAtenderSubarea(CodSubarea);
				while(rs3.next()){
					validar=rs3.getString(2);
					out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
					Cont=Cont+1;
					
				}
				out.print("<input name='txtCont' type='hidden' id='txtCont' value='"+Cont+"' />");
				if(validar==""){
					out.print("No Existe Registro de Este Paciente.");
				}
				rs3.getStatement().getConnection().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</table>");
			
		}
		if(va.equals("3.1")){
			
			int Cont=0;
			out.print("<table width='100%' border='0'>");
			String validar="";
			try {
				rs3=mmp.ListarPacientesPorAtenderUnidad(CodUnidad);
				while(rs3.next()){
					validar=rs3.getString(2);
					//if(rs3.getInt("DifeDia")>=1){
						//son mayor O igual uno
						rs=mmp.BuscarUltimoFormatoPaciente(rs3.getString("adm_numero_ingreso"));
						if(rs.next()){
							String Hora=rs.getString("horas");
							String hor="";
							int h=Hora.split(":").length;
							String[] d=Hora.split(":");		     	
							for(int g=0; g<h-1; g=g+1){hor=d[0];}
							int entero = Integer.parseInt(hor);
							if(entero>=24){
								//el ultimo formato tiene mas de 24 horas.
								//este es color rojo.
								out.print("<tr onMouseOver=this.style.background='#D3D3D3' onmouseout=this.style.background='#FF0040' bgcolor='#FF0040' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
							}else{
								//el ultimo formato tiene mas de 6 horas.
								// este es el color amarillo
								
								if(entero>=6 && entero<12){
									out.print("<tr onMouseOver=this.style.background='#D3D3D3' onmouseout=this.style.background='yellow' bgcolor='yellow' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
									
								}else{
									//el ultimo formato tiene mas de 12 horas.
									//este es el color naranja
									if(entero>=12 && entero<24){
										
										out.print("<tr onMouseOver=this.style.background='#D3D3D3' onmouseout=this.style.background='orange' bgcolor='orange' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
										
									}else{
										//el ultimo formato tiene menos de 6 horas
										// este es el color blanco
										if(entero<6){
											out.print("<tr onMouseOver=this.style.background='#D3D3D3' onmouseout=this.style.background='white' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
										}
									}
								}
							}
						}else{						
							//este es el normal
							// este es el color azul
							out.print("<tr onMouseOver=this.style.background='#D3D3D3' onmouseout=this.style.background='#A9D0F5' bgcolor='#A9D0F5'  ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						}
						rs.getStatement().getConnection().close();
						
					//}
				/*	if(rs3.getString("DifeDia").equals("1")){
						//verificar las horas
						//1. hacer split de la hora
						String Hora=rs3.getString("DifeHora");
						String hor="";
						int h=Hora.split(":").length;
						String[] d=Hora.split(":");		     	
						for(int g=0; g<h-1; g=g+1){ 
							hor=d[0];
						}						
						//2.comprarar que hora es.
						if((hor.equals("06"))||(hor.equals("07"))||(hor.equals("08"))||(hor.equals("09"))||(hor.equals("10"))||(hor.equals("11"))){
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='#F3F781' bgcolor='#F3F781' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						}
						if((hor.equals("12"))||(hor.equals("13"))||(hor.equals("14"))||(hor.equals("15"))||(hor.equals("16"))||(hor.equals("17"))||(hor.equals("18"))||(hor.equals("19"))||(hor.equals("20"))||(hor.equals("21"))||(hor.equals("22"))||(hor.equals("23"))){
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='#FFBF00' bgcolor='#FFBF00' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						}
						if((hor.equals("00"))||(hor.equals("-01"))||(hor.equals("-02"))||(hor.equals("-03"))||(hor.equals("-04"))||(hor.equals("-05"))||(hor.equals("-06"))||(hor.equals("-07"))||(hor.equals("-08"))||(hor.equals("-09"))||(hor.equals("-10"))||(hor.equals("-11"))||(hor.equals("-12"))||(hor.equals("-13"))||(hor.equals("-14"))||(hor.equals("-15"))||(hor.equals("-16"))||(hor.equals("-17"))||(hor.equals("-18"))||(hor.equals("-19"))||(hor.equals("-20"))||(hor.equals("-21"))||(hor.equals("-22"))||(hor.equals("-23"))){
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='#FF0040' bgcolor='#FF0040' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						}
						
					}*/
					/*if(rs3.getString("DifeDia").equals("0")){
						out.print("<tr onMouseOver=this.style.background='#D3D3D3' onmouseout=this.style.background='white' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						String Hora=rs3.getString("DifeHora");
						String hor="";
						int h=Hora.split(":").length;
						String[] d=Hora.split(":");		     	
						for(int g=0; g<h-1; g=g+1){ 
							hor=d[0];
						}
						//son del mismo dia, VERIFICAR LAS HORAS
						if((hor.equals("-06"))||(hor.equals("-07"))||(hor.equals("-08"))||(hor.equals("-09"))||(hor.equals("-10"))||(hor.equals("-11"))){
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='#F3F781' bgcolor='#F3F781' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						}
						if((hor.equals("-12"))||(hor.equals("-13"))||(hor.equals("-14"))||(hor.equals("-15"))||(hor.equals("-16"))||(hor.equals("-17"))||(hor.equals("-18"))||(hor.equals("-19"))||(hor.equals("-20"))||(hor.equals("-21"))||(hor.equals("-22"))||(hor.equals("-23"))){
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='#FFBF00' bgcolor='#FFBF00' ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						}
						if((hor.equals("-01"))||(hor.equals("-02"))||(hor.equals("-03"))||(hor.equals("-04"))||(hor.equals("-05"))){
							out.print("<tr onMouseOver=this.style.background='D3D3D3' onmouseout=this.style.background='white'  ><td width='17%'>"+rs3.getString(10)+"</td><td width='18%'>"+rs3.getString(5)+"-"+rs3.getString(6)+"</td><td width='43%'>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(2)+"</td><td width='17%'>"+rs3.getString(7)+"/"+rs3.getString(8)+"</td><td width='10%'><input name='chkPac' type='checkbox' id='chkPac' value='"+rs3.getString(1)+"' /></td></tr>");
						}
						
					}*/
					
					Cont=Cont+1;
					
				}
				rs3.getStatement().getConnection().close();
				out.print("<input name='txtCont' type='hidden' id='txtCont' value='"+Cont+"' />");
				if(validar==""){
					out.print("No Existe Registro de Este Paciente.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.print("</table>");
			
		}
		/*********************ANTECEDENTES*******************/
		/*******LLENA LAS OPCIONES*******/
		if(va.equals("4")){
			String genero="";
			out.print("<table width='100%' border='1' class='style6' ><tr><td colspan='8'><div align='center' class='style11' id='cabecera2'>SELECCIONE UNA OPCION</div></td></tr><tr><td width='12%'><label><input name='radiobutton' type='radio' value='radiobutton' id='5' onClick='RadioAntecedentes()' />Personales</label></td><td width='14%'><label>");
			
			out.print("<input name='radiobutton' type='radio' value='radiobutton' id='6' onClick='RadioAntecedentes()' />Quirurgicos</label></td><td width='11%'><label><input name='radiobutton' type='radio' value='radiobutton' id='7' onClick='RadioAntecedentes()' />Alergicos</label></td><td width='10%'><label><input name='radiobutton' type='radio' value='radiobutton' id='8' onClick='RadioAntecedentes()' />Toxicos</label></td>");
			
			out.print("<td width='11%'><label><input name='radiobutton' type='radio' value='radiobutton' id='9' onClick='RadioAntecedentes()' />Familiares</label></td><td width='16%'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onClick='RadioAntecedentes()' />Transfusiones</label></td>");
			
			rs=mmp.BuscarGeneroPaciente(CodPac);
			try {
				if(rs.next()){
					genero=rs.getString(1);
					if(genero.equals("Femenino")){
						out.print("<td width='11%'><label><input name='radiobutton' type='radio' value='radiobutton' id='11' onClick='RadioAntecedentes()' />Ginecobstetra</label></td>");
					}
				}				
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			out.print("<td width='15%'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onClick='RadioAntecedentes()' />Medicamentos</label></td></tr><tr>");
			
			out.print("<td colspan='8'><div id='Antecedente'>");
			
			
			
			
			
			
			if(genero.equals("Femenino")){
				out.print("<table><tr><td><div align='center'><input name='btnImprimirAntecedentes' type='button' id='btnImprimirAntecedentes' value='Imprimir' onClick='ImprimirAntecedentes(1)'></div></td></tr></table>");
			}
			if(genero.equals("Masculino")){
				out.print("<table><tr><td><div align='center'><input name='btnImprimirAntecedentes' type='button' id='btnImprimirAntecedentes' value='Imprimir' onClick='ImprimirAntecedentes(2)'></div></td></tr></table>");
			}
			
			try {
				rs1=mmp.VerificarDiagnosticos(CodPac);
				out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Antecedentes Personales </div></td></tr>");
				out.print("<tr><td width='50%'><div align='center'>Diagnostico</div></td><td width='50%'><div align='center'>Observacion</div></td></tr>");
				while(rs1.next()){
					out.print("<tr><td><div>"+rs1.getString(1)+"</div></td><td><div>"+rs1.getString(5)+"</div></td></tr>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</table>");
				out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Antecedentes Quirurgicos </div></td></tr>");
				out.print("<tr><td width='50%'><div align='center'>Procedimiento</div></td><td width='50%'><div align='center'>Observacion</div></td></tr>");
				rs2=mmp.VerificarQx(CedPac);
				while(rs2.next()){
					out.print("<tr><td><div>"+rs2.getString(3)+"</div></td><td><div>"+rs2.getString(1)+"</div></td></tr>");
				}
				rs2.getStatement().getConnection().close();
				out.print("</table>");
				out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Antecedentes Alergicos </div></td></tr>");
				out.print("<tr><td width='50%'><div align='center'>Alergia</div></td><td width='50%'><div align='center'>Reaccion</div></td></tr>");
				rs3=mmp.VerificarAlergias(CedPac);
				while(rs3.next()){
					out.print("<tr><td><div>"+rs3.getString(1)+"</div></td><td><div>"+rs3.getString(2)+"</div></td></tr>");				
				}
				rs3.getStatement().getConnection().close();
				out.print("</table>");
				out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Antecedentes Toxicos </div></td></tr>");
				out.print("<tr><td width='50%'><div align='center'>Toxicologia</div></td><td width='50%'><div align='center'>Observacion</div></td></tr>");
				rs4=mmp.VerificarToxico(CedPac);
				
				while(rs4.next()){
					out.print("<tr><td><div>"+rs4.getString(1)+"</div></td><td><div>"+rs4.getString(2)+"</div></td></tr>");
				}
				rs4.getStatement().getConnection().close();
				out.print("</table>");
				out.print("<table width='100%' border='1' class='style6'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Antecedentes Familiares </div></td></tr>");
				out.print("<tr><td width='14%'><div align='center'>Familiar</div></td><td width='46%'><div align='center'>Antecedentes</div></td><td width='40%'><div align='center'>Observacion</div></td></tr>");
				
				rs5=mmp.VerificarAntFamiliares(CedPac);
				
				while(rs5.next()){
					out.print("<tr><td><div>"+rs5.getString(1)+"</div></td><td><div>"+rs5.getString(2)+"</div></td><td><div align='center'>"+rs5.getString(4)+"</div></td></tr>");
				}
				rs5.getStatement().getConnection().close();
				out.print("</table>");
				out.print("<table width='100%' border='1' class='style6'><tr><td width='100%'><div align='center' class='style11' id='cabecera2'>Antecedentes Transfusiones </div></td></tr>");
				
				rs6=mmp.VerificarTransfu(CedPac);
				
				while(rs6.next()){
					out.print("<tr><td><div>"+rs6.getString(1)+" "+rs6.getString(2)+" "+rs6.getString(3)+" "+rs6.getString(4)+"</div></td></tr>");
				}
				rs6.getStatement().getConnection().close();
				out.print("</table>");
				out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Antecedentes Medicamentos </div></td></tr>");
				out.print("<tr><td width='50%'><div align='center'>Medicamento</div></td><td width='50%'><div align='center'>Observacion</div></td></tr>");
				
				rs7=mmp.Verificarmedicamentos(CedPac);
				while(rs7.next()){
					out.print("<tr><td><div>"+rs7.getString(2)+" "+rs7.getString(3)+"</div></td><td><div>"+rs7.getString(1)+"</div></td></tr>");
				}
				out.print("</table>");
				rs7.getStatement().getConnection().close();
				if(genero.equals("Femenino")){
					out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Antecedentes Gineco-Obstetrico </div></td></tr></table>");
					try {
						rs2=mmp.VerificarGinecobstetra(CedPac);
						if(rs2.next()){
							out.print("<table width='100%' border='0'><tr><td width='20%'><label>G:"+rs2.getString(2)+"</label></td><td width='26%'><label>P:"+rs2.getString(3)+"</label></td><td width='27%'><label>A:"+rs2.getString(4)+"</label></td><td width='27%'><label>C:"+rs2.getString(5)+"</label></td></tr>");
							out.print("<tr><td><label>E:"+rs2.getString(6)+"</label></td><td><label>Menarquia:"+rs2.getString(7)+"</label></td><td><label>FUM:"+rs2.getString(8)+"</label></td><td><label>FURS:"+rs2.getString(9)+"</label></td></tr>");
							out.print("<tr><td><label>IVS:"+rs2.getString(10)+"</label></td><td><label>ITS:"+rs2.getString(11)+"</label></td><td colspan='2'><label>Compa�eros Sexuales:"+rs2.getString(12)+"</label></td></tr>");
							out.print("</table>");
						}						
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</div></td></tr></table>");
		}
		/*******FIN LLENA OPCIONES*******/
		
		/********PERSONALES******/
		if(va.equals("5")){
			out.print("<table width='100%' border='1' class='style6'><tr><td colspan='2'><div align='center'>Clasificacion de Diagnostico</div></td></tr><tr><td width='10%'>Diagnostico</td><td width='90%'><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()' />");
			
			out.print("<input name='btnAsignarDiagnostico' class='boton4' type='button' id='btnAsignarDiagnostico' value='Asignar' onclick='DiagnosticoPaciente()' /></label></td></tr><tr><td><label><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td><td><div id='SugeDiagnostico'></div></td></tr>");
			
			out.print("<tr><td>Observacion</td><td><div align='center'><textarea name='txtObservacioDiagnostico' cols='70' rows='3' id='txtObservacioDiagnostico'></textarea></div></td></tr></table>");
			
			out.print("<table width='100%' border='1' class='style6'><tr class='style11' id='cabecera2' ><td width='80%'><div align='center'>Diagnosticos</div></td><td width='20%'><div align='center'>Fecha y Hora</div></td><td>Accion</td></tr>");
			try {
				rs2=mmp.VerificarDiagnosticos(CodPac);
				while(rs2.next()){
					out.print("<tr><td><a href='#' onclick='Observacion("+rs2.getString(4)+")'>"+rs2.getString(1)+"</a></td><td>"+rs2.getString(2)+"/"+rs2.getString(3)+"</td><td><a href='#' onclick='omitirPersonal("+rs2.getString(4)+")'>Omitir</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</table>");
			
		}	
		
		if(va.equals("omitirAnte")){
			mmp.OmitirAntPersonal(CodigoAntPersonal);
		}
		
		if(va.equals("ObsDia")){
			rs3=mmp.VerObservacionDiagnostico(Diagnos);
			try {
				if(rs3.next()){
					out.print("<table width='100%' border='1' class='style6'><tr><td><div align='center'>"+rs3.getString(2)+"</div></td></tr><tr><td><div align='center'><textarea name='txtObservacion' cols='35' rows='4' id='txtObservacion'>"+rs3.getString(1)+"</textarea></div></td></tr>");					  
					out.print("<tr><td><div align='center'><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' value='"+Diagnos+"' /><input name='btnActualizar' type='button' id='btnActualizar' value='Actualizar' onclick='ActualizarAntePerso()' /></div></td></tr></table>");					  

				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("ActuObser")){
			mmp.ActualizarObservacionAntecedentePersonal(Diagnos,observacion);
		}
		
		/********FIN PERSONALES******/
		
		/********QUIRURGICOS******/
		if(va.equals("6")){
			out.print("<table width='100%' border='1' class='style6'><tr><td width='20%'><div>Cirugia</div></td><td colspan='3'><div><input name='txtCirujia' type='text' id='txtCirujia' size='60' onkeyup='autocompletarQx()' /></div></td></tr><tr><td></td><td colspan='3'><div id='SugerenciaCx'></div></td></tr><tr><td><div>Observacion</div></td><td width='29%'><div><textarea name='txtObservacionCx' cols='40' rows='3' id='txtObservacionCx'></textarea></div></td><td width='10%'><div>Fecha</div></td>");
			out.print("<td width='41%'><div><input name='txtFechaCx' type='text' id='txtFechaCx' maxlength='14' onkeyup='' /></div></td></tr><tr><td colspan='4'><div align='center'><input name='btnIngresarCx' class='boton4' type='button' id='btnIngresarCx' value='     Ingresar     ' onclick='IngresarProcedimiento()' /><input name='txtCodigoQx' type='hidden' id='txtCodigoQx' /></div></td></tr></table>");
			
			out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='51%'><div align='center'>Procedimiento</div></td><td width='49%'><div align='center'>Observacion</div></td><td>Accion</td></tr>");
			try {
				rs2=mmp.VerificarQx(CedPac);
				while(rs2.next()){
					out.print("<tr><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(1)+" "+rs2.getString(2)+"</td><td><a href='#' onclick='omitirQuirurjico("+rs2.getString(4)+")'>Omitir</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</table>");
		}
	
		if(va.equals("IngrQx")){
			mmp.InsertarProcedimientos(codigoQx, fechaQx, observacion, CodPac, usuario, fecha, hora,cedula);
		}
		
		if(va.equals("omitirQx")){
			mmp.OmitirQx(CodigoQx);
		}
		/*******FIN QUIRURGICOS*******/
		
		/********ALERGICOS******/
		if(va.equals("7")){
			out.print("<table width='100%' border='1' class='style6'><tr><td width='50%'><div align='center'>Alergia</div></td><td width='50%'><div align='center'>Reaccion</div></td></tr><tr>");
			out.print("<td><div align='center'>");
			try {
				rs2=mmp.VerTiposAlergias();
				out.print("<select id='txtAlergia'><option value='Seleccione'>Seleccione</option>");
				while(rs2.next()){
					out.print("<option value='"+rs2.getString(2)+"'>'"+rs2.getString(2)+"'</option>");
				}
				out.print("</select>");
				rs2.getStatement().getConnection().close();
				//out.print("<textarea name='txtAlergia' cols='40' rows='3' id='txtAlergia'></textarea>");
				out.println("</div></td>");
				out.print("<td><div align='center'><textarea name='txtReaccion' cols='40' rows='3' id='txtReaccion'></textarea></div></td></tr><tr><td colspan='2'><div align='center'><input name='btnIngreAlergia' type='button' id='btnIngreAlergia' class='boton4' value='       Ingresar      ' onclick='IngresaAlergia()' /></div></td></tr></table>");
			
				out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='50%'><div align='center'>Alergia</div></td><td width='50%'><div align='center'>Reaccion</div><div align='center'></div></td><td>Accion</td></tr>");
			
				rs2=mmp.VerificarAlergias(CedPac);
				while(rs2.next()){
					out.print("<tr><td><div>"+rs2.getString(1)+"</div></td><td><div>"+rs2.getString(2)+"</div></td><td><a href='#' onclick='omitirAlergico("+rs2.getString(3)+")'>Omitir</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</table>");
		}
		
		if(va.equals("IngreAler")){
			mmp.InsertarAlergia(Alergia, Reaccion, CodPac, usuario, hora, fecha,cedula);
		}
		
		if(va.equals("omitirAler")){
			mmp.OmitirAlergico(CodigoAler);
		}
		
		/*******FIN ALERGICOS*******/
		
		/*******TOXICOS*******/
		if(va.equals("8")){
			out.print("<table width='100%' border='1' class='style6'><tr><td width='16%'><div align='center'>Toxicologia</div></td> <td colspan='2'><div align='center'>Observacion</div>  <div align='center'></div></td></tr><tr><td><select name='cmbToxicologia' id='cmbToxicologia'>");
			out.print("<option value='Seleccione' selected='selected'>Seleccione</option><option value='Tabaquismo'>Tabaquismo</option><option value='Alcohol'>Alcohol</option></select></td><td width='28%'><textarea name='txtObservacionTx' cols='40' rows='3' id='txtObservacionTx'></textarea></td><td width='56%'><input name='btnIngreToxico' type='button' id='btnIngreToxico' value='     Ingresar     ' onClick='IngresarToxico()'></td></tr></table>");
			out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='16%'><div align='center'>Toxicologia</div></td><td width='84%'><div align='center'>Observacion</div></td><td>Accion</td></tr>");
			try {
				rs2=mmp.VerificarToxico(CedPac);
				while(rs2.next()){
					out.print("<tr><td><div>"+rs2.getString(1)+"</div></td><td><div>"+rs2.getString(2)+"</div></td><td><a href='#' onclick='omitirToxico("+rs2.getString(3)+")'>Omitir</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</table>");
		}
		
		if(va.equals("IngTox")){
			mmp.InsertarToxicologia(tipoTx, ObservacionTx, CodPac, usuario, fecha, hora,cedula);
		}
		if(va.equals("omitirToxico")){
			mmp.OmitirToxico(CodigoTx);
		}
		/******FIN TOXICOS********/
		
		/******FAMILIARES********/
		if(va.equals("9")){
			out.print("<table width='100%' border='1' class='style6'><tr><td width='18%'><div align='center'>Familiar</div></td><td width='82%'><div align='center'>Antecedente</div></td></tr><tr><td><div>");
			out.print("<select name='cmbFamilia' id='cmbFamilia' ><option value='Seleccion' selected='selected'>Seleccion</option><option value='Abuelo Materno'>Abuelo Materno</option><option value='Abuela Materno'>Abuela Materno</option><option value='Abuelo Paterno'>Abuelo Paterno</option><option value='Abuela Paterno'>Abuela Paterno</option>");
			out.print("<option value='Padre'>Padre</option><option value='Madre'>Madre</option><option value='Hermanos'>Hermanos</option><option value='Hijos'>Hijos</option></select></div></td><td><div><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='60' onKeyUp='autocompletarCIE10()' /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' class='boton4' value='Asignar' onclick='IngresaAntFamiliares()' /></div></td></tr>");
			out.print("<tr><td><div><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></div></td><td><div id='SugeDiagnostico'></div></td></tr><tr><td><div>Observacion</div></td><td><div><textarea name='txtObseFami' cols='60' rows='4' id='txtObseFami'></textarea></div></td></tr></table>");
		
			out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='18%'><div align='center'>Familiar</div></td><td width='82%'><div align='center'>Antecedente</div></td><td>Accion</td></tr>");
			try {
				rs2=mmp.VerificarAntFamiliares(CedPac);
				while(rs2.next()){
					out.print(" <tr><td><div>"+rs2.getString(1)+"</div></td><td><div><a href='#' onClick='MostrarObserFami("+rs2.getString(3)+")'>"+rs2.getString(2)+"</a></div></td><td><a href='#' onClick='omitirAnteFamiliar("+rs2.getString(3)+")'>omitir</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</table>");
		
		
		}
		
		if(va.equals("omitirAnFan")){
			mmp.OmitirAntecedenteFamiliar(CodigoAntFamiliar);
		}
		
		if(va.equals("IngreFami")){
			mmp.InsertarAntefamiliar(Familiar, Observacion, CodCIE_fk, CodPac, usuario, hora, fecha,cedula);
		}
		
		if(va.equals("ObsDiaAntFam")){
			rs3=mmp.VerObservacionDiagnosticoFamiliar(Diagnos);
			try {
				if(rs3.next()){
					out.print("<table width='100%' class='style6' border='1'><tr>");					  
					out.print("<td><div align='center'>"+rs3.getString(2)+"</div></td></tr>");					  
					out.print("<tr><td><div>"+rs3.getString(1)+"</div></td></tr></table>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*******FIN FAMILIARES*******/
		
		/*******TRANSFUNCIONALES*******/
		if(va.equals("10")){
			out.print("<table width='100%' border='1' class='style6'><tr><td width='37%'><div>Transfusiones</div></td><td width='10%'><div><label><input name='radioTrans' type='radio' value='Si' onClick='RadioTrans()' id='Si' />Si</label>&nbsp;&nbsp;&nbsp;<label><input name='radioTrans' type='radio' value='No' onClick='RadioTrans()' id='No'/>No</label></div></td><td width='53%'><div id='OpcTransf'></div></td></tr></table>");
			
			out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td><div align='center'>Transfucion</div></td><td>Accion</td></tr>");
			try {
				rs2=mmp.VerificarTransfu(CedPac);
				while(rs2.next()){
					String cadena=rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" "+rs2.getString(4);
					out.print("<tr><td><div>"+cadena+"</div></td><td><a href='#' onClick='omitirTranfucion("+rs2.getString(5)+")'>omitir</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
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
			mmp.InsertarTransfuciones(transfucion, Cantidad, ObservacionTr, FechaTransfucion, CodPac, usuario, fecha, hora,cedula);
		}
		
		if(va.equals("omitirTranfucion")){
			mmp.OmitirTranfucion(CodigoTranfucion);
		}
		/*******FIN TRANSFUNCIONALES*******/
		
		/*******GINECOBSTETRA*******/
		if(va.equals("11")){
			//out.print("ACTUALMENTE NO DISPONIBLE");
			try {
				rs2=mmp.VerificarGinecobstetra(CedPac);
				if(rs2.next()){
					out.print("<table width='100%' border='1'><tr><td width='20%'><label>G<input name='txtGestas' type='text' id='txtGestas' value='"+rs2.getString(2)+"' /></label></td><td width='26%'><label>P<input name='txtPartos' type='text' id='txtPartos' value='"+rs2.getString(3)+"' /></label></td><td width='27%'><label>A<input name='txtAbortos' type='text' id='txtAbortos' value='"+rs2.getString(4)+"' /></label></td><td width='27%'><label>C<input name='txtCesareas' type='text' id='txtCesareas' value='"+rs2.getString(5)+"' /></label></td></tr>");
					out.print("<tr><td><label>E<input name='txtEspontaneos' type='text' id='txtEspontaneos' value='"+rs2.getString(6)+"' /></label></td><td><label>Menarquia<input name='txtMenarquia' type='text' id='txtMenarquia' value='"+rs2.getString(7)+"' /></label></td><td><label>FUM      <input name='txtFum' type='text' id='txtFum' value='"+rs2.getString(8)+"' /></label></td><td><label>FURS<input name='txtFurs' type='text' id='txtFurs' value='"+rs2.getString(9)+"' /></label></td></tr>");
					out.print("<tr><td><label>IVS<input name='txtIvs' type='text' id='txtIvs' value='"+rs2.getString(10)+"' /></label></td><td><label>ITS<input name='txtIts' type='text' id='txtIts' value='"+rs2.getString(11)+"' /></label></td><td colspan='2'><label>Compa�eros Sexuales<input name='txtComSexu' type='text' id='txtComSexu' value='"+rs2.getString(12)+"' /></label></td></tr>");
					out.print("<tr><td colspan='4'><div align='center'><input name='txtCodGineco' type='hidden' id='txtCodGineco' value='"+rs2.getString(1)+"' /><input name='btnActualizarGineco' class='boton4' type='button' id='btnActualizarGineco' value='     Actualizar     '  onclick='ActualizarGineco()' /></div></td></tr></table>");
			
				}
				else{
					out.print("<table width='100%' border='1'><tr><td width='20%'><label>G<input name='txtGestas' type='text' id='txtGestas' /></label></td><td width='26%'><label>P<input name='txtPartos' type='text' id='txtPartos' /></label></td><td width='27%'><label>A<input name='txtAbortos' type='text' id='txtAbortos' /></label></td><td width='27%'><label>C<input name='txtCesareas' type='text' id='txtCesareas' /></label></td></tr>");
					out.print("<tr><td><label>E<input name='txtEspontaneos' type='text' id='txtEspontaneos' /></label></td><td><label>Menarquia<input name='txtMenarquia' type='text' id='txtMenarquia' /></label></td><td><label>FUM      <input name='txtFum' type='text' id='txtFum' /></label></td><td><label>FURS<input name='txtFurs' type='text' id='txtFurs' /></label></td></tr>");
					out.print("<tr><td><label>IVS<input name='txtIvs' type='text' id='txtIvs' /></label></td><td><label>ITS<input name='txtIts' type='text' id='txtIts' /></label></td><td colspan='2'><label>Compa�eros Sexuales<input name='txtComSexu' type='text' id='txtComSexu' /></label></td></tr>");
					out.print("<tr><td colspan='4'><div align='center'><input name='btnIngresarGineco' class='boton4' type='button' id='btnIngresarGineco' value='     Ingresar     '  onclick='IngresarGineco()' /></div></td></tr></table>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			/***
			 * hacer una consulta si ya los antecedentes estan llenos de ser asi se traen los
			 * datos de los antecedentes y se cambia el nombre ha actualizar.
			 * ***/
						

		}
		
		if(va.equals("IngGine")){
			//mmp.InsertarGinecobstetra(Estudio, observacion, CodPac, usuario, fecha, hora, CedPac);
			String gestas=req.getParameter("gestas");
			String partos=req.getParameter("partos");
			String abortos=req.getParameter("abortos");
			String cesareas=req.getParameter("cesareas");
			String espontaneos=req.getParameter("espontaneos");
			String menarquia=req.getParameter("menarquia");
			String fum=req.getParameter("fum");
			String furs=req.getParameter("furs");
			String ivs=req.getParameter("ivs");
			String its=req.getParameter("its");
			String compasexual=req.getParameter("compasexual");
			
			mmp.InsertarGinecobstetra(gestas, partos, abortos, cesareas, espontaneos, menarquia, fum, furs, ivs, its, compasexual, CodPac, usuario, fecha, hora, CedPac);
		}
		
		if(va.equals("ActuGineco")){
			String gestas=req.getParameter("gestas");
			String partos=req.getParameter("partos");
			String abortos=req.getParameter("abortos");
			String cesareas=req.getParameter("cesareas");
			String espontaneos=req.getParameter("espontaneos");
			String menarquia=req.getParameter("menarquia");
			String fum=req.getParameter("fum");
			String furs=req.getParameter("furs");
			String ivs=req.getParameter("ivs");
			String its=req.getParameter("its");
			String compasexual=req.getParameter("compasexual");
			mmp.ActualizarGinecobstetra(CodigoGineco, gestas, partos, abortos, cesareas, espontaneos, menarquia, fum, furs, ivs, its, compasexual);
		}
		/*******FIN GINECOBSTETRA*******/
		
		/*******MEDICAMENTOS*******/
		if(va.equals("12")){
			out.print("<table width='100%' border='1' class='style6'><tr><td width='20%'><div>Medicamento</div></td><td><div><input name='txtMedicamento' type='text' id='txtMedicamento' size='60' onkeyup='autocompletarMedicamento()' /></div></td></tr><tr><td></td><td><div id='SugerenciaMed'></div></td></tr><tr><td><div>Observacion</div></td><td><div><textarea name='txtObservacionMd' cols='40' rows='3' id='txtObservacionMd'></textarea></div></td>");
			out.print("</tr><tr><td colspan='2'><div align='center'><input name='btnIngresarMd' class='boton4' type='button' id='btnIngresarMd' value='     Ingresar     '  onclick='IngresarMd()' /><input name='txtCodigoMed' type='hidden' id='txtCodigoMed' /></div></td></tr></table>");
			
			out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2'><td width='67%'><div align='center'>Medicamento</div></td><td width='33%'><div align='center'>Observacion</div></td><td>Accion</td></tr>");
			try {
				rs2=mmp.Verificarmedicamentos(CedPac);
				while(rs2.next()){
					String cadena=rs2.getString(2)+" "+rs2.getString(3);
					out.print("<tr><td>"+cadena+"</td><td>"+rs2.getString(1)+"</td><td><a href='#' onClick='omitirAntMedicamento("+rs2.getString(4)+")'>omitir</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			out.print("</table>");
			
		}
		if(va.equals("IngMed")){
			mmp.InsertarMedicamentos(codigoMed, observacion, CodPac, usuario, fecha, hora,cedula);
		}
		
		if(va.equals("omitirAntMedica")){
			mmp.OmitirAnteceMedicamento(CodigoMedicamento);
		}
		/******FIN MEDICAMENTOS********/
		

		/*********************FIN DE ANTECEDENTES*******************/
		
	}
}
