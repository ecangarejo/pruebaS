/**
 * Controlador:ControlFinalAdmi
 * se busca al paciente se verifican que no tenga ningun formato pendiente
 * y se procede a dar de alta si no este le avisara cuales formatos tiene pendientes
 * y que usuario los dejo asi.
 * Desarrollado:Oscar Rolong Schweiger
 */

package adm_Controlador;

import hic_metodo.MetodoLaboratoriosHistoria;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAdmision;
import adm_logica.MetodoPaciente;
import adm_logica.MetodoPreingreso;
import adm_logica.MetodoTraslado;


/**
 * Servlet implementation class ControlFinalAdmi
 */
public class ControlFinalAdmi extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String CodigoAdmision=req.getParameter("CodigoAdmision");
		String TipoDocumento=req.getParameter("TipoDocumento");
		String CodigoUsuario=req.getParameter("CodigoUsuario");
		String CodigoCama=req.getParameter("CodigoCama");
		String Hora=req.getParameter("Hora");
		String Fecha=req.getParameter("Fecha");
		String va=req.getParameter("valor");
		
		String CodAdm=req.getParameter("CodAdm");
		String CodHistoricoCama=req.getParameter("CodHistoricoCama");
		String CodCama=req.getParameter("CodCama");
		String CamaNueva=req.getParameter("CamaNueva");
		String servicio=req.getParameter("servicio");
		
		String CodArea=req.getParameter("CodArea");
		String CodSubArea=req.getParameter("CodSubArea");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		MetodoPaciente mp=new MetodoPaciente();
		MetodoLaboratoriosHistoria mlh=new MetodoLaboratoriosHistoria();
		if(va.equals("sa")){
			rs=mp.datosPaciente(TipoDocumento, NumeroDocumento);
			try {
				if(rs.next()){
					out.print("<table border='1' width='100%'>");
					out.print("<tr><td>Nombre Paciente:</td><td>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td>" +
							"<td>Identificado con:</td><td>"+TipoDocumento+"-"+NumeroDocumento+"</td>" +
						    "<td>Ubicado en:</td><td>"+rs.getString(9)+">>"+rs.getString(10)+"</td></tr>");
				
					//if((rs.getString(9).equals("CONSULTA EXTERNA"))||(rs.getString(9).equals("CIRUGIA"))){
					out.print("<tr><td><b>Diagnostico de Ingreso.</b></td>" +
							"<td  colspan='5' > <label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='txtCodDiagnostico' type='text' readonly='' id='txtCodDiagnostico' /><input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString(1)+" /></label></td>" +
					"</tr><tr><td></td><td><div id='SugeDiagnostico'></div></td></tr>");
					out.print("<tr><td><b>Diagnostico de Egreso.</b></td>" +
							"<td  colspan='5' > <label><input name='txtNomDiagnosRelaEgreso' type='text' id='txtNomDiagnosRelaEgreso' size='80' onkeyup='autocompletarCIE10Egreso()'  /><input name='txtCodDiagnosticoEgreso' type='text' readonly='' id='txtCodDiagnosticoEgreso' /></label></td>" +
					"</tr><tr><td></td><td><div id='SugeDiagnosticoEgreso'></div></td></tr>");
					out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'>");
					rs1=mlh.ObtenerFinalidadConsulta();
					while(rs1.next()){
							out.print("<option value="+rs1.getString(3)+" >"+rs1.getString(3)+":"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td>");
					out.print("<td>Causa Externa </td><td colspan='2'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value='13' selected='' >13:Enfermedad General</option>");
					rs1=mlh.ObtenerCausaExterna();
					while(rs1.next()){
					out.print("<option value="+rs1.getString(3)+" >"+rs1.getString(3)+":"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td></tr>");
					out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
							"<option value='1'>Impresion Diagnostica</option>" +
							"<option value='2'>Confirmado Nuevo</option>" +
							"<option value='3'>Confirmado Repetido</option>" +
							"</select></td><td>Estado de la Salida</td><td colspan='2'><select id='TipoTras'><option value='Seleccione' selected='' >Seleccione</option>");
					rs1=mlh.ObtenerEstadoSalida(rs.getString(7));
					
					out.print("<option value='SALIDA DE URGENCIA'>SALIDA DE URGENCIA</option>" );
					out.print("<option value='TRASLADO'>TRASLADO</option>" );
					out.print("<option value='SALIDA DE HOSPITALIZACION'>SALIDA DE HOSPITALIZACION</option>");
					out.print("</select><select id='EstSalida'><option value='Seleccione' selected='' >Seleccione</option><option value='VIVO'>VIVO</option><option value='MUERTO'>MUERTO</option></select></td></tr>");	
					//"<tr><td></td><td></td></tr>");
					out.print("<tr><td colspan='6' align='center'><input name='btnDarAlta' type='button' id='btnDarAlta' value='Generar' onclick='DarAltaAmbulatoria("+rs.getString(7)+","+rs.getString(11)+")' /></td></tr>");
					out.print("</table>");
					/*}else{
						out.print("<tr><td colspan='6' align='center'><input name='btnDarAlta' type='button' id='btnDarAlta' value='Dar Alta' onclick='DarAltaAm("+rs.getString(7)+","+rs.getString(11)+")' /></td></tr>");
						out.print("</table>");
					}*/
					rs1.getStatement().getConnection().close(); 
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-g	enerated catch block
				System.out.print("Error ControlFinalAdmi>>va=sa "+e);
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("BDP")){
			String sql="";
			String sql1="";
			String sql2="";
			String sql3="";
			String sql4="";
			
			String TiDoc=req.getParameter("TipoDocumento");
			String NumDoc=req.getParameter("NumeroDocumento");		
			String PrimerApellido=req.getParameter("PrimerApellido");
			String SegundoApellido=req.getParameter("SegundoApellido");
			String Nombre=req.getParameter("Nombre");
			
			if(TiDoc.equals("Seleccione")){sql="";}else{sql="AND ap.tipo_documento='"+TiDoc+"' ";}			
			if(NumDoc.equals("")){sql1="";}else{sql1="AND ap.numero_documento LIKE '%"+NumDoc+"%' " ;}			
			if(PrimerApellido.equals("")){sql2="";}else{sql2="AND ap.primer_apellido LIKE '%"+PrimerApellido+"%' ";}
			if(SegundoApellido.equals("")){sql3="";}else{sql3="AND ap.segundo_apellido  LIKE '%"+SegundoApellido+"%' ";}
			if(Nombre.equals("")){sql4="";}else{sql4="AND ap.nombre  LIKE '%"+Nombre+"%' ";}
			
			String SQLTODO=sql+""+sql1+""+sql2+""+sql3+""+sql4;
			
			try {
				rs=mp.BuscarDatosPacienteSQL(SQLTODO);
				while(rs.next()){
					out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#CCCCCC'><td colspan='10'><font color='#0033FF'><b>DATOS PERSONALES</b></font> </td></tr>");
					  
					out.print("<tr><td width='5%'>Documento:</td><td width='8%'>"+rs.getString("Documento")+"</td><td width='10%'>Nombre Paciente: </td><td width='30%'>"+rs.getString("NombrePaciente")+"</td><td width='6%'>Genero:</td><td width='7%'>"+rs.getString("genero")+"</td><td width='9%'>Fecha Nacimiento: </td><td width='11%'>"+rs.getString("fecha_nacimiento")+"</td>");
					  
					out.print(" <td width='8%'>Estado Civil: </td><td width='16%'>"+rs.getString("estado_civil")+"</td></tr><tr><td>Departamento:</td><td colspan='2'>"+rs.getString("Municipio")+"</td><td>Direccion:</td><td colspan='3'>"+rs.getString("direccion")+"</td><td>Telefonos:</td><td colspan='2' >"+rs.getString("Telefonos")+"</td></tr>");
					  
					out.print(" <tr bgcolor='#CCCCCC'><td colspan='10'><font color='#0033FF'><b>DATOS ENTIDAD</b></font> </td></tr>");
					  
					out.print(" <tr><td colspan='2' >Nombre Entidad:</td><td colspan='2'>"+rs.getString("nombre_entidad")+"-"+rs.getString("ent_nit_contratante")+"</td><td>Tarifa:</td><td>"+rs.getString("ManualTarifario")+"</td><td>Tipo Afiliacion: </td><td>"+rs.getString("tipo_afiliacion")+"</td><td>Nivel Cotizante: </td><td>"+rs.getString("nivel_cotizante")+"</td></tr>" +
							"<tr  bgcolor='#6699CC' ><td colspan='10' ></td></tr>" +
							"</table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("tah")){
			rs=mp.datosPacienteAmbHosp(TipoDocumento, NumeroDocumento);
			try {
				if(rs.next()){
					out.print("<table border='1' width='100%'>");
					out.print("<tr><td>Nombre Paciente:</td><td>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td>" +
							"<td>Identificado con:</td><td>"+TipoDocumento+"-"+NumeroDocumento+"</td>" +
						    "<td>Ubicado en:</td><td>"+rs.getString(9)+">>"+rs.getString(10)+"</td></tr>");
				
				//	if((rs.getString(9).equals("CONSULTA EXTERNA"))||(rs.getString(9).equals("CIRUGIA"))||(rs.getString(9).equals("HEMODINAMIA"))||(rs.getString(9).equals("ONCOLOGIA"))||(rs.getString(9).equals("UNIDAD RENAL"))){
						out.print("<tr><td><b>DIAGNOSTICO</b></td>" +
								"<td  colspan='5' > <label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='txtCodDiagnostico' type='text' readonly='' id='txtCodDiagnostico' /><input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString(1)+" /></label></td>" +
						"</tr><tr><td></td><td><div id='SugeDiagnostico'></div></td></tr>");
						out.print("<tr><td>Area</td><td><select id='cmbArea' onchange='VerCama()' ><option selected='' value='Seleccione'  >Seleccione</option>");
						rs1=mp.LlenarAreaCamasHosp();
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close(); 
						out.print("</select></td>");
						out.print("<td>Cama</td><td id='Cama'><select><option value='Seleccione' selected='' >Seleccione</option></select></td><td></td><td></td></tr>");
						out.print("<tr><td colspan='6' align='center'><input name='btnDarAlta' type='button' id='btnDarAlta' value='Trasladar' onclick='TrasladarAmbHosp("+rs.getString(7)+","+rs.getString(11)+")' /></td></tr>");
						out.print("</table>");
				//	}else{
				//		out.print("<tr><td colspan='6' align='center'><input name='btnDarAlta' type='button' id='btnDarAlta' value='Trasladar' onclick='DarAltaAm("+rs.getString(7)+","+rs.getString(11)+")' /></td></tr>");
					//	out.print("</table>");
					//}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-g	enerated catch block
				System.out.print("Error ControlFinalAdmi>>va=sa "+e);
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		if(va.equals("1")){
			String CodPac="";
			String CodAdmision="";
			String validacion="";
			rs=mp.datosPaciente(TipoDocumento, NumeroDocumento);
				try {
					if(rs.next()){
						String DiagnosticoIngreso="";
						String CieIngreso="";
						
						String DiagnosticoEgreso="";
						String CieEgreso="";
						CodPac=rs.getString(1);
						CodAdmision=rs.getString(7);
						rs1=mp.datosPacienteAlta(CodAdmision, CodPac);
						if(rs1.next()){
							//valida que el paciente no tenga formatos pendientes.
							validacion=rs1.getString(1);
							if(validacion!=""){
								rs2=mp.datosPacienteAlta(CodAdmision, CodPac);
								out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td colspan='3'>El Paciente "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" Identificado con "+rs.getString(5)+" "+rs.getString(6)+" que se encontraba ocupando la "+rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(10)+", tiene formatos sin finalizar.Se Relacionan de la siguiente manera: </td></tr><tr class='style6'><td width='37%'>NOMBRE DEL FORMATO </td><td width='43%'>USUARIO</td><td width='20%'>FECHA Y HORA </td></tr>");
								while(rs2.next()){
									out.print(" <tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(5)+" "+rs2.getString(4)+"</td><td>"+rs2.getString(2)+" / "+rs2.getString(3)+"</td></tr>");
								}
								out.print("</table>");
								rs2.getStatement().getConnection().close();
								//valida que el paciente tenga los diagnosticos de ingreso y egreso.
								rs3=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdmision);
								
								out.print("<table width='100%' class='style6' border='1' cellspacing='0' >");
								out.print("<tr><td>Codigo Cie10 </td><td width='78%'>Diagnostico</td><td width='11%'>Tipo Diagnostico </td></tr>");
								if(rs3.next()){
									DiagnosticoIngreso=rs3.getString(1);
									CieIngreso=rs3.getString(2);									
									out.print("<tr><td width='11%'>"+CieIngreso+"</td><td>"+DiagnosticoIngreso+"</td><td>Ingreso</td></tr>");
								}
								else{
									out.println("<tr><td colspan='3'>No Tiene Diagnostico De Ingreso.</td></tr>");
								}
								rs3.getStatement().getConnection().close();
								
								rs4=mp.BuscarDiagnosticoEgresoAdmisionesH(CodAdmision);
								if(rs4.next()){
									DiagnosticoEgreso=rs4.getString(1);
									CieEgreso=rs4.getString(2);									
									out.print("<tr><td width='11%'>"+CieEgreso+"</td><td>"+DiagnosticoEgreso+"</td><td>Ingreso</td></tr>");
								}
								else{									
									out.println("<tr><td colspan='3'>No Tiene Diagnostico De Egreso De Hospitalizacion.</td></tr>");
								}
								rs4.getStatement().getConnection().close();
								out.print("</table>");
							}
							
						}
						else{
							rs5=mp.BuscarDiagnosticoIngresoAdmisiones(CodAdmision);
							
							out.print("<table width='100%' class='style6' border='1' cellspacing='0' >");
							if(rs5.next()){
								DiagnosticoIngreso=rs5.getString(1);
								CieIngreso=rs5.getString(2);					
							}
							else{
								out.println("<tr><td>No Tiene Diagnostico De Ingreso</td></tr>");
							}
							rs5.getStatement().getConnection().close();
							
							rs6=mp.BuscarDiagnosticoEgresoAdmisionesH(CodAdmision);
							if(rs6.next()){
								DiagnosticoEgreso=rs6.getString(1);
								CieEgreso=rs6.getString(2);
							}
							else{									
								out.println("<tr><td>No Tiene Diagnostico De Egreso De Hospitalizacion</td></tr>");							}
							rs6.getStatement().getConnection().close();
							out.print("</table>");
							
							if((CieEgreso!="")&&(CieIngreso!="")){
								out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td colspan='3'>El Paciente "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" Identificado con "+rs.getString(5)+" "+rs.getString(6)+" que se encontraba ocupando la "+rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(10)+", No tiene formatos Pendientes.</td></tr>");
								out.println("<tr><td>Codigo Cie10 </td><td width='78%'>Diagnostico</td><td width='11%'>Tipo Diagnostico </td></tr>");
								
								out.println("<tr><td width='11%'>"+CieIngreso+"</td><td>"+DiagnosticoIngreso+"</td><td>Ingreso</td></tr>");
								out.println("<tr><td width='11%'>"+CieEgreso+"</td><td>"+DiagnosticoEgreso+"</td><td>Egreso</td></tr>");
								
								out.println("<tr><td colspan='3'><div align='center'><input name='btnDarAlta' type='button' id='btnDarAlta' value='Dar Alta' onclick='DarAlta("+CodAdmision+","+rs.getString(11)+")' /></div></td></tr></table>");
							}
						}
						rs1.getStatement().getConnection().close();
					}
					else{
						out.print("PACIENTE NO ENCONTRADO O NO TIENE ADMISION ACTIVA");

					}
					
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			
		}
		MetodoTraslado ta= new MetodoTraslado();
		if(va.equals("BMD")){
			
			try {
				rs1=mp.BuscarCambioMedicoTratante(TipoDocumento, NumeroDocumento);
				if(rs1.next()){
					out.print("<table width='100%' border='0'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>REGISTROS ENCONTRADOS </div></td></tr>");
					out.print("<tr><td>Nombre del Paciente: </td><td>"+rs1.getString(1)+"</td><td></td><td></td></tr>");
					out.print("<tr><td> <input type='hidden' name='txtCodAdm' id='txtCodAdm' value="+rs1.getString(2)+" /><input type='hidden' name='txtCodMedTraV' id='txtCodMedTraV' value="+rs1.getString(4)+" /> Medico Tratante Actual</td><td>"+rs1.getString(3)+"</td><td>Medico a Tratar</td><td><select id='cmbMedicoTratante' style='width:100%'><option value='Seleccione' selected=''>Seleccione</option>");
					
					rs=ta.ListarEspecialistas();
					while(rs.next()){
						out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					}
					rs.getStatement().getConnection().close();
					
					out.print("</select></td></tr><tr><td colspan='4'><input name='btnCambiarMedicoTratante' type='button' id='btnCambiarMedicoTratante' value='     Cambiar     ' onClick='CambiarMedicoTratante()'></td></tr>");					

				out.print("</table>");
				}
				else{
					out.print("El Documento: "+TipoDocumento+"-"+NumeroDocumento+" No Posee Registros.");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("CMD")){			
			
			String NomMedico=req.getParameter("NomMedico");
			String CodMedTra=req.getParameter("CodMedTra");
			String CodUsu=req.getParameter("CodUsu");
			java.util.Date fechaActualDXA = new java.util.Date();
			java.sql.Date fechaDXA = new java.sql.Date(fechaActualDXA.getTime());		
			java.sql.Time horaDXA = new java.sql.Time(fechaActualDXA.getTime());
			try {
				rs3=ta.BuscarMedicoTratante(CodAdm);
				if(rs3.next()){
					//ya tiene un medico tratante activo
					//se hace un update al resistro que ya tiene
					ta.ActualizarMedicoTratante(rs3.getString(1));
					ta.IngresarMedicoTratante(CodMedTra, NomMedico, CodAdm,fechaDXA, horaDXA, CodUsu);
					out.print("Cambio de Medico Realizado Satisfactoriamente");
					//se crea registro nuevo
				}else{
					ta.IngresarMedicoTratante(CodMedTra, NomMedico, CodAdm,fechaDXA, horaDXA, CodUsu);
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("2")){
			String NombreCompleto="";
			String Estado="";
			String Estado1="";
			rs=mp.BuscarAdmisiones(TipoDocumento, NumeroDocumento);
			rs1=mp.BuscarAdmisiones(TipoDocumento, NumeroDocumento);
			rs2=mp.BuscarTrasladoUrgHos(TipoDocumento, NumeroDocumento);
			try {
				if(rs1.next()){
					NombreCompleto=rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(3);
					out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>REGISTROS ENCONTRADOS </div></td></tr>");
					out.print("<tr><td width='13%'>Nombre del Paciente: </td><td width='87%'>"+NombreCompleto+"</td></tr>");
					out.print("<tr><td>Estado</td><td>Hora y Fecha Del Registro </td></tr>");
					while(rs.next()){
						Estado=rs.getString(7);
						if(Estado.equals("0")){
							out.print("<tr><td bgcolor='#00CC33' style='color:#FFFFFF'>ACTIVA</td><td><a  href='#'onclick='mostarAdmision("+rs.getString(4)+")'>"+rs.getString(5)+" "+rs.getString(6)+"</a></td></tr>");
						}
						if(Estado.equals("1")){
							out.print("<tr><td bgcolor='#CC0000' style='color:#FFFFFF'>INACTIVA</td><td><a  href='#'onclick='mostarAdmision("+rs.getString(4)+")'>"+rs.getString(5)+" "+rs.getString(6)+"</a></td></tr>");
						}
						if(Estado.equals("2")){
							out.print("<tr><td bgcolor='#0101DF' style='color:#FFFFFF'>ANULADA</td><td><a  href='#'onclick='mostarAdmision("+rs.getString(4)+")'>"+rs.getString(5)+" "+rs.getString(6)+"</a></td></tr>");
						}
					
					}					
					rs.getStatement().getConnection().close();
					
					while(rs2.next()){
						Estado1=rs2.getString(4);
						if(Estado1.equals("0")){
							out.print("<tr><td bgcolor='#00CC33' style='color:#FFFFFF'>Traslado Hospitalizacion Activo</td><td><a  href='#'onclick='ReporteTrasladoUrgHosp("+rs2.getString(1)+")'>"+rs2.getString(3)+" </a></td></tr>");
						}
						if(Estado1.equals("1")){
							out.print("<tr><td bgcolor='#CC0000' style='color:#FFFFFF'>Traslado Hospitalizacion Inactivo</td><td><a  href='#'onclick='ReporteTrasladoUrgHosp("+rs2.getString(1)+")'>"+rs2.getString(3)+" </a></td></tr>");
						}
					}
					rs2.getStatement().getConnection().close();
				out.print("</table>");
				}
				else{
					out.print("El Documento: "+TipoDocumento+"-"+NumeroDocumento+" No Posee Registros.");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("3")){
			rs=mp.BuscarAdmisionAnular(TipoDocumento, NumeroDocumento);
			try {
				if(rs.next()){
					
					out.print("<table width='100%' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Admision Encontrada</div></td></tr>");
					out.print("<tr><td width='11%'>Nombre Paciente </td><td colspan='3'>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"</td><td width='7%'>Ubicacion</td><td width='43%'>"+rs.getString(10)+" "+rs.getString(11)+"</td></tr>");
					out.print("<tr><td>Registrado Por</td><td width='12%'>"+rs.getString(5)+"</td><td width='13%'>Fecha y Hora Registro </td><td width='14%'>"+rs.getString(6)+" / "+rs.getString(7)+"</td><td>&nbsp;</td><td><input name='txtCodigoAdmision' type='hidden' id='txtCodigoAdmision' value="+rs.getString(4)+" ><input name='txtCodigoCama' type='hidden' id='txtCodigoCama' value="+rs.getString(8)+" ></td></tr>");
					out.print("<tr><td colspan='6'><div align='center'>MOTIVO DE ANULACION <textarea cols='50' rows='5'id='txtMotivoAnulacion' ></textarea></div></td></tr>");
					out.print("<tr><td colspan='6'><div align='center'><input name='btnAnularAdmision' type='button' id='btnAnularAdmision' value='     Anular     ' onClick='AnularAdmision()'></div></td></tr>");
					out.print("</table>");

				}
				else{
					out.print("Paciente No Tiene Admision Activa.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error 3 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("3.1")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date FechaServidor = new java.sql.Date(fechaActual.getTime());
			java.sql.Time HoraServidor = new java.sql.Time(fechaActual.getTime());
			//Verificar si tiene formatos esta admision.
			String txtMotivoAnulacion=req.getParameter("txtMotivoAnulacion");
			try {
				rs=mp.VerificarFormatosPendientes(CodigoAdmision);
				if(rs.next()){
					out.print("Esta Admision Tiene Formatos Pendientes.\n Porfavor Finalice Los Formatos.");
				}
				else{
					//Actualizar El Estado de la Admision ponerlo en 2
					mp.OmitirAdmision(CodigoAdmision,txtMotivoAnulacion);					
					//Actualizar El Estado de la Cama ponerlo en 1
					mp.ActualizarEstadodeCama(CodigoCama);
					//Actualizar Tabla Historico Cama
					mp.ActualizarHistoricoCama(CodigoAdmision, CodigoCama, FechaServidor+"", HoraServidor+"", CodigoUsuario);
					
					mp.GuardarAnulacionAdmision(CodigoAdmision, CodigoUsuario, txtMotivoAnulacion, FechaServidor+"", HoraServidor+"");
					out.print("");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error 3.1 "+e);
				e.printStackTrace();
			}
		}
		
		
		/**********************************************************************************/
		if(va.equals("4")){
			rs=mp.RegistrosAltaPaciente(TipoDocumento, NumeroDocumento);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>REGISTROS ENCONTRADOS </div></td></tr>");
					out.print("<tr><td width='13%'>Nombre del Paciente: </td><td width='87%'>"+rs.getString(1)+"</td></tr>");
					out.print("<tr><td colspan='2'>Hora y Fecha Del Registro </td></tr>");
					rs1=mp.RegistrosAltaPaciente(TipoDocumento, NumeroDocumento);
					while(rs1.next()){
						out.print("<tr><td colspan='2'><a  href='#'onclick='mostarAlta("+rs1.getString(3)+","+rs1.getString(4)+")'>"+rs1.getString(5)+" </a></td></tr>");
					}
					rs1.getStatement().getConnection().close();
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		/**********************************************************************************/
		if(va.equals("5")){
			rs=mp.Censo();
			try {
				out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>CENSO </div></td></tr>");
				out.print("<tr><td>Ubicacion</td><td>Nombre Paciente</td><td>Identificacion</td><td>Genero</td><td>Entidad</td></tr>");
				
				
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(2)+"</td><td><a href='#' onclick='mostarAdmision("+rs.getString(1)+")'>"+rs.getString(4)+"</a></td><td>"+rs.getString(5)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(6)+"</td></tr>");
					
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**************************************DEVOLVER ALTA PACIENTE******************************/
		if(va.equals("6")){
			rs=mp.RegistrosDevolverAlta(TipoDocumento, NumeroDocumento);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>REGISTROS DE ALTAS ENCONTRADAS </div></td></tr>");
					out.print("<tr><td width='13%'>Nombre del Paciente: </td><td width='60%'>"+rs.getString(1)+"</td><td width='60%'></td></tr>");
					out.print("<tr><td>Fecha y Hora Del Registro</td><td>Fecha y Hora Del Egreso</td><td>Accion</td></tr>");
					rs1=mp.RegistrosDevolverAlta(TipoDocumento, NumeroDocumento);
					while(rs1.next()){
						out.print("<tr><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td><input name='btnDevolverAlta' type='button' id='btnDevolverAlta' value='...' onClick='DevolverAlta("+rs1.getString(7)+","+rs1.getString(3)+","+rs1.getString(4)+")'></td></tr>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</table>");
				}else{
					out.print("No Se Encontraron Registros Con Los Siguientes Datos Tipo  Documento "+TipoDocumento+" Numero Documento "+NumeroDocumento);
				}
				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		if(va.equals("6.1")){
			String EstCama="";
			rs=mp.VerDetalleAdmision(CodAdm);
			try {
				if(rs.next()){
					EstCama=rs.getString(7);
					if(EstCama.equals("1")){
						out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>DETALLE DE LA ADMISION A DEVOLVER ALTA </div></td></tr>");
						out.print("<tr><td width='13%'>Numero Autorizacion:</td><td>"+rs.getString(1)+"</td><td>Medio Autorizacion:</td><td>"+rs.getString(2)+"</td><td>Autorizado Por:</td><td>"+rs.getString(3)+"</td></tr>");
						out.print("<tr><td width='13%'>Registrado Por:</td><td>"+rs.getString(4)+"</td><td>Fecha Ingreso:</td><td>"+rs.getString(5)+"</td><td>Ubicacion:</td><td>"+rs.getString(6)+"</td></tr>");
						out.print("<tr><td colspan='6'><div align='center'><input type='hidden' name='servicio' id='servicio' value="+rs.getString(8)+" /><input type='hidden' name='cmbCama' id='cmbCama' value='null' /><input type='hidden' name='CodAdm' id='CodAdm' value="+CodAdm+" /><input type='hidden' name='CodHistoricoCama' id='CodHistoricoCama' value="+CodHistoricoCama+" /><input type='hidden' name='CodCama' id='CodCama' value="+CodCama+" /><input name='btnDevolverAlta' type='button' id='btnDevolverAlta' value='Devolver Alta' onClick='DevolverAltaAdm()'></div></td></tr></table>");
					}
					if(EstCama.equals("2")){
						
					}
					if(EstCama.equals("3")){
						out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>DETALLE DE LA ADMISION A DEVOLVER ALTA </div></td></tr>");
						out.print("<tr><td width='13%'>Numero Autorizacion:</td><td>"+rs.getString(1)+"</td><td>Medio Autorizacion:</td><td>"+rs.getString(2)+"</td><td>Autorizado Por:</td><td>"+rs.getString(3)+"</td></tr>");
						out.print("<tr><td width='13%'>Registrado Por:</td><td>"+rs.getString(4)+"</td><td>Fecha Ingreso:</td><td>"+rs.getString(5)+"</td><td>Ubicacion.</td><td>La Cama "+rs.getString(6)+" Esta Ocupada. Seleccione Otra.</td></tr>");
						out.print("<tr><td width='13%'>Area:</td><td><select name='cmbArea' size='1' id='cmbArea' onchange='LlenarSubarea()'>");
						out.print("<option value='Seleccione' selected=''>Seleccione</option>");
						rs1=mp.LlenarAreaCamas();
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+" >"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
						out.print("</select></td><td>Subarea</td><td><div id='SubareaCama'><select name='cmbSubArea' size='1' id='cmbSubArea'><option value='Seleccione' selected=''>Seleccione</option></select></div></td><td>Cama:</td><td><div id='Cama'><select name='cmbCama' size='1' id='cmbCama'><option value='Seleccione' selected=''>Seleccione</option></select></div></td></tr>");
						out.print("<tr><td colspan='6'><div align='center'><input type='hidden' name='servicio' id='servicio' value="+rs.getString(8)+" /><input type='hidden' name='CodAdm' id='CodAdm' value="+CodAdm+" /><input type='hidden' name='CodHistoricoCama' id='CodHistoricoCama' value="+CodHistoricoCama+" /><input type='hidden' name='CodCama' id='CodCama' value="+CodCama+" /><input name='btnDevolverAlta' type='button' id='btnDevolverAlta' value='Devolver Alta' onClick='DevolverAltaAdm()'></div></td></tr></table>");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		if(va.equals("6.2")){			
			try {
				rs=mp.LlenarSubAreaCamas(CodArea);
				out.print("<select name='cmbSubArea' size='1' id='cmbSubArea' onchange='LlenarCamasSubarea()'><option value='Seleccione' selected=''>Seleccione</option>");
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("6.3")){			
			try {
				rs=mp.LlenarCamas(CodSubArea);
				out.print("<select name='cmbCama' size='1' id='cmbCama'><option value='Seleccione' selected=''>Seleccione</option>");
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("6.4")){
			String Destino="";
			String TipDiag="";
			/**********************si la cama es disponible********************************/			
			/**se verifica si es de urgencia o de hospitalizacion o de uci**/
			if(servicio.equals("1")){
				/**Si es de urgencia.**/
				/**se actualiza la cama a ocupada**/
				mp.ActualizarEstadoCama(CodCama);
				/**se actualiza el historico cama**/
				mp.ActualizarHistoricoCama(CodHistoricoCama);
				Destino="SALIDA DE URGENCIA";
				TipDiag="EG";
				mp.EliminarDestinoDevolucion(CodAdm, Destino);
				mp.EliminarDiagnosticosEgresoDevolucion(CodAdm, TipDiag);
				mp.DevolverActivaAdmisionDevolucion(CodAdm);
				}
			else{
				/**Si esta en hospitalizacion o uci**/
				/**se actualiza la cama a ocupada**/
				mp.ActualizarEstadoCama(CodCama);
				/**se actualiza el historico cama**/
				mp.ActualizarHistoricoCama(CodHistoricoCama);
				Destino="SALIDA DE HOSPITALIZACION";
				TipDiag="EGH";
				mp.EliminarDestinoDevolucion(CodAdm, Destino);
				mp.EliminarDiagnosticosEgresoDevolucion(CodAdm, TipDiag);
				mp.DevolverActivaAdmisionDevolucion(CodAdm);
			}			
		}
		
		
		if(va.equals("6.5")){
			String Destino="";
			String TipDiag="";
			/**********************si la cama esta ocupada********************************/			
			/**se verifica si es de urgencia o de hospitalizacion o de uci**/
			if(servicio.equals("1")){
				/**Si es de urgencia.**/
				/**se actualiza la cama a ocupada**/
				mp.ActualizarEstadoCama(CamaNueva);
				/**se actualiza el historico cama**/
				mp.ActualizarHistoricoCama1(CodHistoricoCama,CamaNueva);
				Destino="SALIDA DE URGENCIA";
				TipDiag="EG";
				mp.EliminarDestinoDevolucion(CodAdm, Destino);
				mp.EliminarDiagnosticosEgresoDevolucion(CodAdm, TipDiag);
				mp.DevolverActivaAdmisionDevolucion1(CodAdm,CamaNueva);
				}
			else{
				/**Si esta en hospitalizacion o uci**/
				/**se actualiza la cama a ocupada**/
				mp.ActualizarEstadoCama(CamaNueva);
				/**se actualiza el historico cama**/
				mp.ActualizarHistoricoCama1(CodHistoricoCama,CamaNueva);
				Destino="SALIDA DE HOSPITALIZACION";
				TipDiag="EGH";
				mp.EliminarDestinoDevolucion(CodAdm, Destino);
				mp.EliminarDiagnosticosEgresoDevolucion(CodAdm, TipDiag);
				mp.DevolverActivaAdmisionDevolucion1(CodAdm,CamaNueva);
			}			
		}
		
		/****************************************FIN DEVOLVER ALTA***********************************/
		
	}

}
