/**
 * controlador: ControlFormatosPestanas se encuentra la forma en que son 
 * creadas las pesta�as las cuales contienen el nombre del paciente, y a cada
 * paciente se le asigna un menu de opciones que contienen estudios realizados,
 * formatos, asignaciones y antecedentes.
 */
package hic_controlador;

import fact_metodo.MetodoSeguimientoAdmision;
import hic_metodo.MetodoLaboratoriosHistoria;
import hic_metodo.MetodoMultiplePacientes;
import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.Conexion;


public class ControlFormatosPestanas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		String CodPac = req.getParameter("CodPac");
		String CodExamen=req.getParameter("CodExamen");
		String CodEstudio=req.getParameter("CodEstudio");
		String TipoEstudio=req.getParameter("TipoEstu");
		String CodigoPac=req.getParameter("CodigoPac");
		String HoraPeticion=req.getParameter("HoraPeticion");
		String FechaPeticion=req.getParameter("FechaPeticion");		
		String CodArea=req.getParameter("CodArea");
		String CodAdmision=req.getParameter("CodAdmision");
		String CodPaciente=req.getParameter("CodPaciente");
		String NombreMedico=req.getParameter("NombreMedico");
		String TipoPyP=req.getParameter("TipoPyP");
		String CodUsu=req.getParameter("CodUsu");
		String usuario=req.getParameter("usuario");
		String FechaFormato=req.getParameter("FechaFormato");
		String HoraFormato=req.getParameter("HoraFormato");
		String CodigoFormatoPaciente=req.getParameter("CodigoFormatoPaciente");
		String CodigoUsuario=req.getParameter("CodigoUsuario");
		MetodoLaboratoriosHistoria mlh=new MetodoLaboratoriosHistoria();
		MetodoSeguimientoAdmision msa=new MetodoSeguimientoAdmision();
		MetodoMultiplePacientes mmp=new MetodoMultiplePacientes();
		MetodoVerFormatos mvf=new MetodoVerFormatos();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		
		ResultSet rs8=null;
		ResultSet rs9=null;
		ResultSet rs10=null;
		ResultSet rs11=null;
		ResultSet rs12=null;
		ResultSet rs13=null;
		ResultSet rs14=null;
		ResultSet rs15=null;
		
		ResultSet rsServ=null;
		ResultSet rsDI=null;
		
		/*************DIAGNOSTICO FINAL*************/
		if(va.equals("dxfin")){
			String TipoDiagE="EG";
			String TipoDiagRE1="RE1";
			String TipoDiagRE2="RE2"; 
			String TipoDiagEH="EGH";
			String TipoDiagRE1H="RE1H";
			String TipoDiagRE2H="RE2H";
			String TipoDestino="";
			String Servicio="";
			String DiagnosticoIngresoUrg="";
			String codResultado="";
			String tipdiag="";
			try {
				rsServ=mlh.ObtenerServicioAdmision(CodAdmision);
				if(rsServ.next()){
					Servicio=rsServ.getString(1);
				}
				rsServ.getStatement().getConnection().close();
				rsDI=mlh.ObtenerDiagIngreso(CodAdmision);
				if(rsDI.next()){
					DiagnosticoIngresoUrg=rsDI.getString(1);
					codResultado=rsDI.getString(2);
				}
				rsDI.getStatement().getConnection().close();
				/*****************************************************/				
				if((!Servicio.equals("1"))&&(DiagnosticoIngresoUrg.equals(""))){
					/*************************************************************************/					
					out.print("<table width='100%' border='0' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Clasificacion de Diagnostico Hospitalizacion </div></td></tr>");
					rs=mlh.ObtenerDiagosticosdeIngresoHospitalizacion(CodAdmision);
					if(rs.next()){
						out.print("<tr><td><b>DIAGNOSTICO DE INGRESO</b></td></tr>" +
								"<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialH()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' ></label></td>" +
								"</tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
						out.print("<tr><td><div id='DI'>");
						rs1=mlh.ObtenerDiagosticosdeIngresoHospitalizacion(CodAdmision);
						while(rs1.next()){
							out.print("<tr><td>"+rs1.getString(1)+"<td></tr>");
						}
						out.print("</div></td></tr>");
						rs1.getStatement().getConnection().close();
					}else{
						out.print("<tr><td><b>DIAGNOSTICO DE INGRESO</b></td></tr>" +
								"<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialH()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' ></label></td>" +
								"</tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
						out.print("<tr><td><div id='DI'></div></td></tr>");
					}
					rs.getStatement().getConnection().close();
					
					
					rs8=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE1H);
					if(rs8.next()){
						out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td></tr><tr><td><label>"+rs8.getString(1)+"-"+rs8.getString(2)+"</label></td></tr><tr><td></td></tr>");
					}
					else{
						out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td><td>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela1' type='text' id='txtNomDiagnosRela1' size='80' onkeyup='autocompletarCIE10DiagRela1()'   /><input name='txtCodDiagnosticoRela1' type='text' id='txtCodDiagnosticoRela1' readonly='' /><input name='txtTipoDiagRel1' type='hidden' id='txtTipoDiagRel1' value='RE1H'  /></label></td><td><input name='btnDiagrel1' type='button' id='btnDiagrel1' value='Asignar' onClick='AsignarDiagRel1()'></td></tr><tr><td><div id='SugeDiagnosticoRela1'></div></td><td>&nbsp;</td></tr>");
					}
					rs8.getStatement().getConnection().close();
					rs9=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE2H);
					if(rs9.next()){
						out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 2</b></td></tr><tr><td><label>"+rs9.getString(1)+"-"+rs9.getString(2)+"</label></td></tr><tr><td></td></tr>");
					}
					else{
						out.print("<tr><td width='642'><b>DIAGNOSTICO RELACIONADO 2</b></td><td width='66'>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela2' type='text' id='txtNomDiagnosRela2' size='80' onkeyup='autocompletarCIE10DiagRela2()'   /><input name='txtCodDiagnosticoRela2' type='text' id='txtCodDiagnosticoRela2' readonly='' /><input name='txtTipoDiagRel2' type='hidden' id='txtTipoDiagRel2' value='RE2H'  /></label></td><td><input name='btnDiagrel2' type='button' id='btnDiagrel2' value='Asignar' onClick='AsignarDiagRel2()'></td></tr><tr><td><div id='SugeDiagnosticoRela2'></div></td><td>&nbsp;</td></tr>");
					}
					rs9.getStatement().getConnection().close();
					rs10=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagEH);
					out.print("<tr><td  width='642'><b>DIAGNOSTICO DE EGRESO</b></td><td  width='66'>&nbsp;</td></tr><tr><td><input name='txtNomDiagnosRelaEgreso' type='text' id='txtNomDiagnosRelaEgreso' size='80'  onkeyup='autocompletarCIE10Egreso()'      /><input name='txtCodDiagnosticoEgreso' type='text' id='txtCodDiagnosticoEgreso' readonly=''  /><input name='txtTipoDiagEG' type='hidden' id='txtTipoDiagEG' value='EGH'  /></td><td><label><input name='btnDiagEg' type='button' id='btnDiagEg' value='Asignar'  onClick='AsignarDiagEg()'></label></td></tr><tr><td><div id='SugeDiagnosticoEgreso'></div></td><td>&nbsp;</td></tr>");
					while(rs10.next()){
						out.print("<tr><td>"+rs10.getString(1)+"-"+rs10.getString(2)+"</td></tr><tr><td></td></tr>");
					}
						rs12=mlh.ObtenerEstadoSalidaH(CodAdmision);
						if(rs12.next()){						
							rs15=mlh.ObtenerDatosComplementarios(CodAdmision);
							if(rs15.next()){								
								out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
								out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'><option value="+rs15.getString(1)+" selected='selected' >"+rs15.getString(3)+":"+rs15.getString(2)+"</option>");
								rs13=mlh.ObtenerFinalidadConsulta();
								while(rs13.next()){
										out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
								}
								rs13.getStatement().getConnection().close();
								out.print("</select></td>");
								out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value="+rs15.getString(4)+" selected='selected' >"+rs15.getString(6)+":"+rs15.getString(5)+"</option>");
								rs14=mlh.ObtenerCausaExterna();
								while(rs14.next()){
								out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
								}
								rs14.getStatement().getConnection().close();
								out.print("</select></td></tr>");
								tipdiag=rs15.getString(7);
								if(tipdiag.equals("01")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01' selected='selected' >Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
											"</select></td><td colspan='2'>&nbsp;</td></tr></table>");
								}
								if(tipdiag.equals("02")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02' selected='selected' >Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
											"</select></td><td colspan='2'>&nbsp;</td></tr></table>");
								}
								if(tipdiag.equals("03")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03' selected='selected' >Confirmado Repetido</option>" +
											"</select></td><td colspan='2'>&nbsp;</td></tr></table>");
								}
								
							}
						rs15.getStatement().getConnection().close();
						}
						else{
							out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
							out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'>");
							rs13=mlh.ObtenerFinalidadConsulta();
							while(rs13.next()){
									out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
							}
							rs13.getStatement().getConnection().close();
							out.print("</select></td>");
							out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value='13' selected='' >13:Enfermedad General</option>");
							rs14=mlh.ObtenerCausaExterna();
							while(rs14.next()){
							out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
							}
							rs14.getStatement().getConnection().close();
							out.print("</select></td></tr>");
							out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
									"<option value='1'>Impresion Diagnostica</option>" +
									"<option value='2'>Confirmado Nuevo</option>" +
									"<option value='3'>Confirmado Repetido</option>" +
									"</select></td><td colspan='2'>&nbsp;</td></tr></table>");	
						}
						rs12.getStatement().getConnection().close();						

					rs10.getStatement().getConnection().close();
					out.print("</table>");
					/************************************************************************/
					rs6=mlh.ObtenerEstadoSalidaH(CodAdmision);
					if(rs6.next()){
						out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
						out.print("<tr><td width='15%'><b>DESTINO DEL PACIENTE</b> </td><td width='85%'>"+rs6.getString(2)+"</td></tr>");
						out.print("<tr><td><b>ESTADO DE SALIDA</b></td><td>"+rs6.getString(3)+"</td></tr></table>");
					
					}
					else{
						out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
						out.print("<tr><td width='29%'><label><input name='rbDestino' type='radio' value='1.1' id='1.1' >SALIDA DE HOSPITALIZACION </label></td><td width='27%'><label><input name='rbDestino' type='radio' value='2.1' id='2.1' >REMISION A OTRA IPS </label></td><td width='44%'><label></td></tr>");
						out.print("<tr><td colspan='3'>SALIDA: &nbsp;&nbsp;&nbsp;&nbsp;<label><input name='rbSalida' type='radio' value='1' id='1' >Vivo</label>&nbsp;&nbsp;&nbsp;&nbsp;    <label><input name='rbSalida' type='radio' value='2' id='2'  >Muerto</label></td></tr>");
						out.print("<tr><td colspan='3'><div align='center'><input name='btndestinoPaciente' type='button' id='btndestinoPaciente' value='     Ingresar     ' onClick='IngresarDestino()' ></div></td></tr></table>");
					}
					rs6.getStatement().getConnection().close();
					/************************************************************************/
				}else{
					if((!Servicio.equals("1"))&&(codResultado==null)){					
						/*************************************************************************/					
						out.print("<table width='100%' border='0' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Clasificacion de Diagnostico Hospitalizacion </div></td></tr>");
						rs=mlh.ObtenerDiagosticosdeIngresoHospitalizacion(CodAdmision);
						if(rs.next()){
							out.print("<tr><td><b>DIAGNOSTICO DE INGRESO</b></td></tr>" +
									"<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialH()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' ></label></td>" +
									"</tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							out.print("<tr><td><div id='DI'>");
							rs1=mlh.ObtenerDiagosticosdeIngresoHospitalizacion(CodAdmision);
							while(rs1.next()){
								out.print("<tr><td>"+rs1.getString(1)+"<td></tr>");
							}
							out.print("</div></td></tr>");
							rs1.getStatement().getConnection().close();
						}else{
							out.print("<tr><td><b>DIAGNOSTICO DE INGRESO</b></td></tr>" +
									"<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialH()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' ></label></td>" +
									"</tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							out.print("<tr><td><div id='DI'></div></td></tr>");
						}
						rs.getStatement().getConnection().close();
						
						
						rs8=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE1H);
						if(rs8.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b> </td></tr><tr><td><label>"+rs8.getString(1)+"-"+rs8.getString(2)+"</label></td></tr><tr><td></td></tr>");
						}
						else{
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td><td>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela1' type='text' id='txtNomDiagnosRela1' size='80' onkeyup='autocompletarCIE10DiagRela1()'   /><input name='txtCodDiagnosticoRela1' type='text' id='txtCodDiagnosticoRela1' readonly='' /><input name='txtTipoDiagRel1' type='hidden' id='txtTipoDiagRel1' value='RE1H'  /></label></td><td><input name='btnDiagrel1' type='button' id='btnDiagrel1' value='Asignar' onClick='AsignarDiagRel1()'></td></tr><tr><td><div id='SugeDiagnosticoRela1'></div></td><td>&nbsp;</td></tr>");
						}
						rs8.getStatement().getConnection().close();
						rs9=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE2H);
						if(rs9.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 2</b></td></tr><tr><td><label>"+rs9.getString(1)+"-"+rs9.getString(2)+"</label></td></tr><tr><td></td></tr>");
						}
						else{
							out.print("<tr><td width='642'><b>DIAGNOSTICO RELACIONADO 2</b></td><td width='66'>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela2' type='text' id='txtNomDiagnosRela2' size='80' onkeyup='autocompletarCIE10DiagRela2()'   /><input name='txtCodDiagnosticoRela2' type='text' id='txtCodDiagnosticoRela2' readonly='' /><input name='txtTipoDiagRel2' type='hidden' id='txtTipoDiagRel2' value='RE2H'  /></label></td><td><input name='btnDiagrel2' type='button' id='btnDiagrel2' value='Asignar' onClick='AsignarDiagRel2()'></td></tr><tr><td><div id='SugeDiagnosticoRela2'></div></td><td>&nbsp;</td></tr>");
						}
						rs9.getStatement().getConnection().close();
						rs10=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagEH);
						out.print("<tr><td  width='642'><b>DIAGNOSTICO DE EGRESO</b></td><td  width='66'>&nbsp;</td></tr><tr><td><input name='txtNomDiagnosRelaEgreso' type='text' id='txtNomDiagnosRelaEgreso' size='80'  onkeyup='autocompletarCIE10Egreso()'      /><input name='txtCodDiagnosticoEgreso' type='text' id='txtCodDiagnosticoEgreso' readonly=''  /><input name='txtTipoDiagEG' type='hidden' id='txtTipoDiagEG' value='EGH'  /></td><td><label><input name='btnDiagEg' type='button' id='btnDiagEg' value='Asignar'  onClick='AsignarDiagEg()'></label></td></tr><tr><td><div id='SugeDiagnosticoEgreso'></div></td><td>&nbsp;</td></tr>");
						while(rs10.next()){
							out.print("<tr><td>"+rs10.getString(1)+"-"+rs10.getString(2)+"</td></tr><tr><td></td></tr>");
						}

						rs12=mlh.ObtenerEstadoSalidaH(CodAdmision);
						if(rs12.next()){						
							rs15=mlh.ObtenerDatosComplementarios(CodAdmision);
							if(rs15.next()){								
								out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
								out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'><option value="+rs15.getString(1)+" selected='selected' >"+rs15.getString(3)+":"+rs15.getString(2)+"</option>");
								rs13=mlh.ObtenerFinalidadConsulta();
								while(rs13.next()){
										out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
								}
								rs13.getStatement().getConnection().close();
								out.print("</select></td>");
								out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value="+rs15.getString(4)+" selected='selected' >"+rs15.getString(6)+":"+rs15.getString(5)+"</option>");
								rs14=mlh.ObtenerCausaExterna();
								while(rs14.next()){
								out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
								}
								rs14.getStatement().getConnection().close();
								out.print("</select></td></tr>");
								tipdiag=rs15.getString(7);
								if(tipdiag.equals("01")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01' selected='selected' >Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
											"</select></td><td colspan='2'>&nbsp;</td></tr></table>");
								}
								if(tipdiag.equals("02")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02' selected='selected' >Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
											"</select></td><td colspan='2'>&nbsp;</td></tr></table>");
								}
								if(tipdiag.equals("03")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03' selected='selected' >Confirmado Repetido</option>" +
											"</select></td><td colspan='2'>&nbsp;</td></tr></table>");
								}
								
							}
						rs15.getStatement().getConnection().close();
						}
						else{
							out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
							out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'>");
							rs13=mlh.ObtenerFinalidadConsulta();
							while(rs13.next()){
									out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
							}
							rs13.getStatement().getConnection().close();
							out.print("</select></td>");
							out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value='13' selected='' >13:Enfermedad General</option>");
							rs14=mlh.ObtenerCausaExterna();
							while(rs14.next()){
							out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
							}
							rs14.getStatement().getConnection().close();
							out.print("</select></td></tr>");
							out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'><option value='1'>Impresion Diagnostica</option><option value='2'>Confirmado Nuevo</option><option value='3'>Confirmado Repetido</option></select></td><td colspan='2'>&nbsp;</td></tr></table>");	
						}
						rs12.getStatement().getConnection().close();

						rs10.getStatement().getConnection().close();
						out.print("</table>");
						/************************************************************************/
						rs6=mlh.ObtenerEstadoSalidaH(CodAdmision);
						if(rs6.next()){
							out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
							out.print("<tr><td width='15%'><b>DESTINO DEL PACIENTE</b> </td><td width='85%'>"+rs6.getString(2)+"</td></tr>");
							out.print("<tr><td><b>ESTADO DE SALIDA</b></td><td>"+rs6.getString(3)+"</td></tr></table>");
						
						}
						else{
							out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
							out.print("<tr><td width='29%'><label><input name='rbDestino' type='radio' value='1.1' id='1.1' >SALIDA DE HOSPITALIZACION </label></td><td width='27%'><label><input name='rbDestino' type='radio' value='2.1' id='2.1' >REMISION A OTRA IPS </label></td><td width='44%'><label></td></tr>");
							out.print("<tr><td colspan='3'>SALIDA: &nbsp;&nbsp;&nbsp;&nbsp;<label><input name='rbSalida' type='radio' value='1' id='1' >Vivo</label>&nbsp;&nbsp;&nbsp;&nbsp;    <label><input name='rbSalida' type='radio' value='2' id='2'  >Muerto</label></td></tr>");
							out.print("<tr><td colspan='3'><div align='center'><input name='btndestinoPaciente' type='button' id='btndestinoPaciente' value='     Ingresar     ' onClick='IngresarDestino()' ></div></td></tr></table>");
						}
						rs6.getStatement().getConnection().close();
					}else{
				/*****************************************************/
				/**********************empieza el else****************/
				rs2=mlh.ObtenerEstadoSalida(CodAdmision);
				if(rs2.next()){
					TipoDestino=rs2.getString(1);
				}
				else{
					out.print("<table width='100%' border='0' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Clasificacion de Diagnostico </div></td></tr>");
					out.print("<tr><td colspan='2' ><b>DIAGNOSTICO DE INGRESO</b> </td></tr>");
					rs=mlh.ObtenerDiagnosticosCIE10(CodAdmision);
					while(rs.next()){
						out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
					}
					rs.getStatement().getConnection().close();
						rs3=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE1);
						if(rs3.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td></tr><tr><td><label><input name='txtNomDiagnosRela1' type='text' id='txtNomDiagnosRela1' size='80' readonly='' value='"+rs3.getString(1)+"'  /><input name='txtCodDiagnosticoRela1' type='text' id='txtCodDiagnosticoRela1' readonly='' value='"+rs3.getString(2)+"'  /><input name='txtTipoDiagRel1' type='hidden' id='txtTipoDiagRel1' value='RE1'  /></label></td></tr><tr><td><div id='SugeDiagnosticoRela1'></div></td></tr>");
						}
						else{
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td><td>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela1' type='text' id='txtNomDiagnosRela1' size='80' onkeyup='autocompletarCIE10DiagRela1()'   /><input name='txtCodDiagnosticoRela1' type='text' id='txtCodDiagnosticoRela1' readonly='' /><input name='txtTipoDiagRel1' type='hidden' id='txtTipoDiagRel1' value='RE1'  /></label></td><td><input name='btnDiagrel1' type='button' id='btnDiagrel1' value='Asignar' onClick='AsignarDiagRel1()'></td></tr><tr><td><div id='SugeDiagnosticoRela1'></div></td><td>&nbsp;</td></tr>");
						}
						rs3.getStatement().getConnection().close();
						rs4=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE2);
						if(rs4.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 2</b></td></tr><tr><td><label><input name='txtNomDiagnosRela2' type='text' id='txtNomDiagnosRela2' size='80' readonly='' value='"+rs4.getString(1)+"'  /><input name='txtCodDiagnosticoRela2' type='text' id='txtCodDiagnosticoRela2' readonly='' value='"+rs4.getString(2)+"'  /><input name='txtTipoDiagRel2' type='hidden' id='txtTipoDiagRel2' value='RE2'  /></label></td></tr><tr><td><div id='SugeDiagnosticoRela2'></div></td></tr>");
						}
						else{
							out.print("<tr><td width='642'><b>DIAGNOSTICO RELACIONADO 2</b></td><td width='66'>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela2' type='text' id='txtNomDiagnosRela2' size='80' onkeyup='autocompletarCIE10DiagRela2()'   /><input name='txtCodDiagnosticoRela2' type='text' id='txtCodDiagnosticoRela2' readonly='' /><input name='txtTipoDiagRel2' type='hidden' id='txtTipoDiagRel2' value='RE2'  /></label></td><td><input name='btnDiagrel2' type='button' id='btnDiagrel2' value='Asignar' onClick='AsignarDiagRel2()'></td></tr><tr><td><div id='SugeDiagnosticoRela2'></div></td><td>&nbsp;</td></tr>");
						}
						rs4.getStatement().getConnection().close();
						rs1=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagE);
						out.print("<tr><td><b>DIAGNOSTICO DE EGRESO</b></td></tr>");
						out.print("<tr><td  width='642'></td><td  width='66'>&nbsp;</td></tr><tr><td><input name='txtNomDiagnosRelaEgreso' type='text' id='txtNomDiagnosRelaEgreso' size='80'  onkeyup='autocompletarCIE10Egreso()'      /><input name='txtCodDiagnosticoEgreso' type='text' id='txtCodDiagnosticoEgreso' readonly=''  /><input name='txtTipoDiagEG' type='hidden' id='txtTipoDiagEG' value='EG'  /></td><td><label><input name='btnDiagEg' type='button' id='btnDiagEg' value='Asignar'  onClick='AsignarDiagEg()'></label></td></tr><tr><td><div id='SugeDiagnosticoEgreso'></div></td><td>&nbsp;</td></tr>");
						while(rs1.next()){
							out.print("<tr><td>"+rs1.getString(1)+"-"+rs1.getString(2)+"</td></tr><tr><td></td></tr>");
						}

						rs12=mlh.ObtenerEstadoSalidaH(CodAdmision);
						if(rs12.next()){							
							rs15=mlh.ObtenerDatosComplementarios(CodAdmision);
							if(rs15.next()){
								out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
								out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'><option value="+rs15.getString(1)+" selected='selected' >"+rs15.getString(3)+":"+rs15.getString(2)+"</option>");
								rs13=mlh.ObtenerFinalidadConsulta();
								while(rs13.next()){
										out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
								}
								rs13.getStatement().getConnection().close();
								out.print("</select></td>");
								out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value="+rs15.getString(4)+" selected='selected' >"+rs15.getString(6)+":"+rs15.getString(5)+"</option>");
								rs14=mlh.ObtenerCausaExterna();
								while(rs14.next()){
								out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
								}
								rs14.getStatement().getConnection().close();
								out.print("</select></td></tr>");								
								tipdiag=rs15.getString(7);
								if(tipdiag.equals("01")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01' selected='selected' >Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
									"</select></td><td colspan='2'>&nbsp;</td></tr>");
								}
								if(tipdiag.equals("02")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02' selected='selected' >Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
									"</select></td><td colspan='2'>&nbsp;</td></tr>");
								}
								if(tipdiag.equals("03")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03' selected='selected' >Confirmado Repetido</option>" +
									"</select></td><td colspan='2'>&nbsp;</td></tr>");
								}				
								out.print("</table>");
							rs15.getStatement().getConnection().close();
							}
						}
						else{
							out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
							out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'>");
							rs13=mlh.ObtenerFinalidadConsulta();
							while(rs13.next()){
									out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
							}
							rs13.getStatement().getConnection().close();
							out.print("</select></td>");
							out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value='13' selected='' >13:Enfermedad General</option>");
							rs14=mlh.ObtenerCausaExterna();
							while(rs14.next()){
							out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
							}
							rs14.getStatement().getConnection().close();
							out.print("</select></td></tr>");						
							out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'><option value='1'>Impresion Diagnostica</option><option value='2'>Confirmado Nuevo</option><option value='3'>Confirmado Repetido</option></select></td><td colspan='2'>&nbsp;</td></tr></table>");	
						}
						rs12.getStatement().getConnection().close();
						//
						rs1.getStatement().getConnection().close();
						out.print("</table>");
						rs5=mlh.ObtenerEstadoSalida(CodAdmision);
						if(rs5.next()){
							out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
							out.print("<tr><td width='15%'><b>DESTINO DEL PACIENTE</b> </td><td width='85%'>"+rs5.getString(1)+"</td></tr>");
							out.print("<tr><td><b>ESTADO DE SALIDA</b></td><td>"+rs5.getString(2)+"</td></tr></table>");
						}
						else{
						out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
						out.print("<tr><td width='29%'><label><input name='rbDestino' type='radio' value='1' id='1' >SALIDA DE URGENCIA </label></td><td width='27%'><label><input name='rbDestino' type='radio' value='2' id='2' >REMISION A OTRA IPS </label></td><td width='44%'><label><input name='rbDestino' type='radio' value='3' id='3'>HOSPITALIZACION</label></td></tr>");
						out.print("<tr><td colspan='3'>SALIDA: &nbsp;&nbsp;&nbsp;&nbsp;<label><input name='rbSalida' type='radio' value='1' id='1' >Vivo</label>&nbsp;&nbsp;&nbsp;&nbsp;    <label><input name='rbSalida' type='radio' value='2' id='2'  >Muerto</label></td></tr>");
						out.print("<tr><td colspan='3'><div align='center'><input name='btndestinoPaciente' type='button' id='btndestinoPaciente' value='     Ingresar     ' onClick='IngresarDestino()' ></div></td></tr></table>");
						}
						rs5.getStatement().getConnection().close();
				}
				rs2.getStatement().getConnection().close();
				
				if(TipoDestino.equals("HOSPITALIZACION")){
					out.print("<table width='100%' border='0' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Clasificacion de Diagnostico Urgencia </div></td></tr>");
					out.print("<tr><td colspan='2' ><b>DIAGNOSTICO DE INGRESO</b></td></tr>");
					rs=mlh.ObtenerDiagnosticosCIE10(CodAdmision);
					while(rs.next()){
						out.print("<tr><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td></tr>");
					}
					rs.getStatement().getConnection().close();
						rs3=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE1);
						if(rs3.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td></tr><tr><td><label>"+rs3.getString(1)+"-"+rs3.getString(2)+"</label></td></tr><tr><td></td></tr>");
						}
						
						rs3.getStatement().getConnection().close();
						rs4=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE2);
						if(rs4.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 2</b></td></tr><tr><td><label>"+rs4.getString(1)+"-"+rs4.getString(2)+"</label></td></tr><tr><td></td></tr>");
						}
						
						rs4.getStatement().getConnection().close();
						rs1=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagE);
						out.print("<tr><td><b>DIAGNOSTICO DE EGRESO</b></td></tr>");
						while(rs1.next()){
							out.print("<tr><td>"+rs1.getString(1)+"-"+rs1.getString(2)+"</td></tr><tr><td></td></tr>");
						}
						
						rs1.getStatement().getConnection().close();
						out.print("</table>");
						rs5=mlh.ObtenerEstadoSalida(CodAdmision);
						if(rs5.next()){
							out.print("<table width='100%' border='1'><tr><td colspan='2'></td></tr>");
							out.print("<tr><td width='15%'><b>DESTINO DEL PACIENTE</b> </td><td width='85%'>"+rs5.getString(1)+"</td></tr>");
							out.print("<tr><td><b>ESTADO DE SALIDA</b></td><td>"+rs5.getString(2)+"</td></tr></table>");
						}					
						rs5.getStatement().getConnection().close();
						/******************SE PONE EL MENU DE DIAGNOSTICOS DE HOSPITALIZACION.******************************************/
						out.print("<table width='100%' border='0' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Clasificacion de Diagnostico Hospitalizacion </div></td></tr>");
						rs8=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE1H);
						if(rs8.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td></tr><tr><td><label>"+rs8.getString(1)+"-"+rs8.getString(2)+"</label></td></tr><tr><td></td></tr>");
						}
						else{
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td><td>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela1' type='text' id='txtNomDiagnosRela1' size='80' onkeyup='autocompletarCIE10DiagRela1()'   /><input name='txtCodDiagnosticoRela1' type='text' id='txtCodDiagnosticoRela1' readonly='' /><input name='txtTipoDiagRel1' type='hidden' id='txtTipoDiagRel1' value='RE1H'  /></label></td><td><input name='btnDiagrel1' type='button' id='btnDiagrel1' value='Asignar' onClick='AsignarDiagRel1()'></td></tr><tr><td><div id='SugeDiagnosticoRela1'></div></td><td>&nbsp;</td></tr>");
						}
						rs8.getStatement().getConnection().close();
						rs9=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagRE2H);
						if(rs9.next()){
							out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 2</b></td></tr><tr><td><label>"+rs9.getString(1)+"-"+rs9.getString(2)+"</label></td></tr><tr><td></td></tr>");
						}
						else{
							out.print("<tr><td width='642'><b>DIAGNOSTICO RELACIONADO 2</b></td><td width='66'>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela2' type='text' id='txtNomDiagnosRela2' size='80' onkeyup='autocompletarCIE10DiagRela2()'   /><input name='txtCodDiagnosticoRela2' type='text' id='txtCodDiagnosticoRela2' readonly='' /><input name='txtTipoDiagRel2' type='hidden' id='txtTipoDiagRel2' value='RE2H'  /></label></td><td><input name='btnDiagrel2' type='button' id='btnDiagrel2' value='Asignar' onClick='AsignarDiagRel2()'></td></tr><tr><td><div id='SugeDiagnosticoRela2'></div></td><td>&nbsp;</td></tr>");
						}
						rs9.getStatement().getConnection().close();
						rs10=mlh.ObtenerDiagosticosdeEgreso(CodAdmision,TipoDiagEH);
						out.print("<tr><td  width='642'><b>DIAGNOSTICO DE EGRESO</b></td><td  width='66'>&nbsp;</td></tr><tr><td><input name='txtNomDiagnosRelaEgreso' type='text' id='txtNomDiagnosRelaEgreso' size='80'  onkeyup='autocompletarCIE10Egreso()'      /><input name='txtCodDiagnosticoEgreso' type='text' id='txtCodDiagnosticoEgreso' readonly=''  /><input name='txtTipoDiagEG' type='hidden' id='txtTipoDiagEG' value='EGH'  /></td><td><label><input name='btnDiagEg' type='button' id='btnDiagEg' value='Asignar'  onClick='AsignarDiagEg()'></label></td></tr><tr><td><div id='SugeDiagnosticoEgreso'></div></td><td>&nbsp;</td></tr>");
						while(rs10.next()){
							out.print("<tr><td>"+rs10.getString(1)+"-"+rs10.getString(2)+"</td></tr><tr><td></td></tr>");
						}
						rs12=mlh.ObtenerEstadoSalidaH(CodAdmision);
						if(rs12.next()){							
							rs15=mlh.ObtenerDatosComplementarios(CodAdmision);
							if(rs15.next()){
								out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
								out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'><option value="+rs15.getString(1)+" selected='selected='selected'' >"+rs15.getString(3)+":"+rs15.getString(2)+"</option>");
								rs13=mlh.ObtenerFinalidadConsulta();
								while(rs13.next()){
										out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
								}
								rs13.getStatement().getConnection().close();
								out.print("</select></td>");
								out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value="+rs15.getString(4)+" selected='selected' >"+rs15.getString(6)+":"+rs15.getString(5)+"</option>");
								rs14=mlh.ObtenerCausaExterna();
								while(rs14.next()){
								out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
								}
								rs14.getStatement().getConnection().close();
								out.print("</select></td></tr>");								
								tipdiag=rs15.getString(7);
								if(tipdiag.equals("01")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01' selected='selected' >Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
									"</select></td><td colspan='2'>&nbsp;</td></tr>");
								}
								if(tipdiag.equals("02")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02' selected='selected' >Confirmado Nuevo</option>" +
											"<option value='03'>Confirmado Repetido</option>" +
									"</select></td><td colspan='2'>&nbsp;</td></tr>");
								}
								if(tipdiag.equals("03")){
									out.print("<tr><td>Tipo Diagnostico </td><td><select name='cmbtipdiag' id='cmbtipdiag'>" +
											"<option value='01'>Impresion Diagnostica</option>" +
											"<option value='02'>Confirmado Nuevo</option>" +
											"<option value='03' selected='selected' >Confirmado Repetido</option>" +
									"</select></td><td colspan='2'>&nbsp;</td></tr>");
								}
								out.print("</table>");									

							rs15.getStatement().getConnection().close();

							}
						}
						else{
							out.print("<table width='100%' border='0' ><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Otros Datos Complementarios </div></td></tr>");
							out.print("<tr><td width='16%'>Finalidad de la Consulta </td><td width='43%'><select name='cmbfinalidadcons' id='cmbfinalidadcons'>");
							rs13=mlh.ObtenerFinalidadConsulta();
							while(rs13.next()){
									out.print("<option value="+rs13.getString(3)+" >"+rs13.getString(3)+":"+rs13.getString(2)+"</option>");
							}
							rs13.getStatement().getConnection().close();
							out.print("</select></td>");
							out.print("<td width='10%'>Causa Externa </td><td width='31%'><select name='cmbcausaexterna' id='cmbcausaexterna'><option value='13' selected='' >13:Enfermedad General</option>");
							rs14=mlh.ObtenerCausaExterna();
							while(rs14.next()){
							out.print("<option value="+rs14.getString(3)+" >"+rs14.getString(3)+":"+rs14.getString(2)+"</option>");
							}
							rs14.getStatement().getConnection().close();
							out.print("</select></td></tr>");
							out.print("<tr><td>Tipo Diagnostico</td><td><select name='cmbtipdiag' id='cmbtipdiag'><option value='1'>Impresion Diagnostica</option><option value='2'>Confirmado Nuevo</option><option value='3'>Confirmado Repetido</option></select></td><td colspan='2'>&nbsp;</td></tr></table>");	
						}
						rs12.getStatement().getConnection().close();
						rs10.getStatement().getConnection().close();
						out.print("</table>");
						rs11=mlh.ObtenerEstadoSalida(CodAdmision);
						String Var="";
						if(rs11.next()){
							Var=rs11.getString(1);							
						}
						if(Var.equals("HOSPITALIZACION")){
							rs6=mlh.ObtenerEstadoSalidaH(CodAdmision);
							if(rs6.next()){
								out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
								out.print("<tr><td width='15%'><b>DESTINO DEL PACIENTE</b></td><td width='85%'>"+rs6.getString(2)+"</td></tr>");
								out.print("<tr><td><b>ESTADO DE SALIDA</b></td><td>"+rs6.getString(3)+"</td></tr></table>");
							
							}
							else{
								out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>DESTINO DEL PACIENTE </div></td></tr>");
								out.print("<tr><td width='29%'><label><input name='rbDestino' type='radio' value='1.1' id='1.1' >SALIDA DE HOSPITALIZACION </label></td><td width='27%'><label><input name='rbDestino' type='radio' value='2.1' id='2.1' >REMISION A OTRA IPS </label></td><td width='44%'><label></td></tr>");
								out.print("<tr><td colspan='3'>SALIDA: &nbsp;&nbsp;&nbsp;&nbsp;<label><input name='rbSalida' type='radio' value='1' id='1' >Vivo</label>&nbsp;&nbsp;&nbsp;&nbsp;    <label><input name='rbSalida' type='radio' value='2' id='2'  >Muerto</label></td></tr>");
								out.print("<tr><td colspan='3'><div align='center'><input name='btndestinoPaciente' type='button' id='btndestinoPaciente' value='     Ingresar     ' onClick='IngresarDestino()' ></div></td></tr></table>");
							}
							rs6.getStatement().getConnection().close();
						}
						rs11.getStatement().getConnection().close();
				}
			
				if(TipoDestino.equals("REMISION A OTRA IPS")){
					// preguntar que opciones se puede poner aqui para hacer el paso de la info.
				}
				/****************************************fin del else************************/
				}//fin del else
				}
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
		}

		/*************LABORATORIOS*************/
		if(va.equals("0")){
			out.print("<table width='50%' border='0' class='style6' cellspacing='0' ><tr><td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='1' onclick='Radio()' />Lista Completa</label></td>");
			out.print("<td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='2' onclick='Radio()' />Seguimiento De Examenes</label></td></tr></table>");
			out.print("<table width='100%' border='1'><tr><td><div id='ContenidoLaboratorio'></div></td></tr></table>");
			
		}
		if(va.equals("NOACTIVO")){
			out.println("<p class=style67>USTED NO TIENE ESTA OPCION ACTIVADA PORQUE TIENE FORMATOS ABIERTOS</p>");
			out.println("<p  class=style66><u><i><a href=menu.jsp>CERRAR FORMATO</a></i></u></p>");
		}
		if(va.equals("1")){
			/**
			 * para llenar los examanes k se ha hecho el paciente en el formato
			 * que salga enseguida se carge la historia clinica
			 */
			//ResultSet rs2=null;
			String edad="";
			String genero="";
			rs2=mlh.Busedadygene(CodPac);
			  try {
				if(rs2.next()){
					  edad=rs2.getString(2);
					  genero=rs2.getString(1);
				  }
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			String edad1=edad;
			rs3=mlh.Buscacodge(genero);
			String codge="";
			try {
				if(rs3.next()){
					codge=rs3.getString(1);
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			rs=mlh.ExamenTexto(CodPac);
			rs1=mlh.ExamenRango(CodPac,codge);
			String nombre="";
			String apellidos="";
			ResultSet pa1=null;
			pa1=mlh.Busedadygene(CodPac);
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
					String hora=rs.getString(5).substring(0,2);
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
					String hora=rs1.getString(5).substring(0,2);
					String minuto=rs1.getString(5).substring(3,5);
					String segundo=rs1.getString(5).substring(6,8);
					out.print("<tr><td align='left' width='20%'><div>"+rs1.getString(4)+"/"+rs1.getString(5)+"</div></td><td width='80%' align='left'><div><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+2+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(6)+"</a></div></td></tr>");	
				}
				rs.getStatement().getConnection().close();
				rs1.getStatement().getConnection().close();			
				rs2.getStatement().getConnection().close();
				ResultSet rsgrupo=null;
				rsgrupo=mlh.Examen(CodPac);
				ResultSet redad=null;
				ResultSet rgene=null;
				redad=mlh.Buscaedad(CodPac);
				String edad2="";
				String genero1="";
				String codgenero="";
				try {
					if(redad.next()){
						edad2=redad.getString(1);
						genero1=redad.getString(2);
					}
					rgene=mlh.Buscacodge(genero);
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
						String hora=rsgrupo.getString(5).substring(0,2);
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
				e1.printStackTrace();
			}
		}
		
		if(va.equals("3")){
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
			int edad1=0;
			
			bus=mlh.Busedadygene1(CodPac);
			pa=mlh.Busedadygene1(CodPac);
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
			ge=mlh.Buscacodge(genero);
			if(ge.next()){
				codge=ge.getString(1);
			}
			 edad1= Integer.parseInt(edad);
			 ge.getStatement().getConnection().close();
			 pa.getStatement().getConnection().close();
             }catch(Exception ex){
				
			}
		     out.print("<br>");
		     
				 rs=mlh.Bustip(CodExamen);
				 try {
					 if(rs.next()){
						 tipoexa=rs.getString(2);
						 exam=rs.getString(3);
					 }
					 rs.getStatement().getConnection().close();
				 } catch (SQLException e) {
					 e.printStackTrace();
				 }
				 try {
					 if(tipoexa.equals("1")){
						

						 co=mlh.Examenom(CodExamen, CodPac, codge, edad1);
						 co2=mlh.Examenom(CodExamen,CodPac, codge, edad1);
 
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
						 co1=mlh.ExamenTexto1(CodPac,CodExamen);
						 co5=mlh.ExamenTexto1(CodPac,CodExamen);			    
   
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
		}
		
		if(va.equals("2")){
			int contador=0;
			rs=mlh.ExamenesRealizados(CodPac);
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
		/*****************************FIN LABORATORIO*******************************/
		
		/**********************    IMAGENOLOGIA    *******************************/
		
		
		if(va.equals("4")){

			ResultSet rsImgEco=null;
			ResultSet rsImgRmc=null;
			
			rsImgEco=mlh.HistorialDeEco(CodPac);
			rsImgRmc=mlh.HistorialDeRmc(CodPac);
			rs4=mlh.HistorialDeImagenologia(CodPac);
			
			out.print("<table width='100%' border='1' class='style6' ><tr align='center' id='cabecera2' class='style11'><td >Fecha y Hora </td><td align='left'>Nombre Del Estudio </td></tr>");
			try {
				while(rs4.next()){
					out.print("<tr><td width='20%' align='left'>"+rs4.getString(1)+"/"+rs4.getString(2)+"</td><td width='80%' align='left'><a  href='#'onclick='mostarexamenes("+rs4.getString(4)+","+rs4.getString(5)+")'>"+rs4.getString(3)+"</a></td></tr>");
				}
				
				while(rsImgEco.next()){
					out.print("<tr><td width='20%' align='left'>"+rsImgEco.getString(1)+"/"+rsImgEco.getString(2)+"</td><td width='80%' align='left'><a  href='#'onclick='mostrarInformesCardiologia("+rsImgEco.getString(4)+")'>"+rsImgEco.getString(3)+"</a></td></tr>");
				}
				
				
				while(rsImgRmc.next()){
					out.print("<tr><td width='20%' align='left'>"+rsImgRmc.getString(1)+"/"+rsImgRmc.getString(2)+"</td><td width='80%' align='left'><a  href='#'onclick='mostrarInformesRmc("+rsImgRmc.getString(4)+")'>"+rsImgRmc.getString(3)+"</a></td></tr>");
				}
				
				out.print("</table>");
				rs4.getStatement().getConnection().close();
				rsImgRmc.getStatement().getConnection().close();
				rsImgEco.getStatement().getConnection().close();
			} catch (SQLException e) {
					System.out.println("ERROR EN VA EQUAL 4 ControlFormatosPestanas "+e);
				e.printStackTrace();
			}
		}
		/**********************   FIN DE IMAGENOLOGIA  *********************/
		
		/*****************************   ORDENES DE SERVICIO  ******************************/
		if(va.equals("5")){			
			try {
				rs1=mlh.ObtenerDiagIngreso(CodAdmision);
				if(rs1.next()){
					out.print("<table width='100%' class='style6' border='1' cellspacing='0'><tr>" +
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='8' onclick='RadioFor()' />Formulacion</label></td>"+
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='lab' onclick='RadioFor()' /> Laboratorio</label></td>"+
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='img' onclick='RadioFor()' />Imagenes Diagnosticas</label></td>" +
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Pro' onclick='RadioFor()' />Procedimientos</label></td>" +
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='pat' onclick='RadioFor()' />Patologias</label></td>" +
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='7' onclick='RadioFor()' />General</label></td>"+
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='interconsulta' onclick='RadioFor()' />Interconsulta</label></td>"+
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='ordTraslado' onclick='RadioFor()' />Traslado</label></td>"+	
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='verFormulacion' onclick='RadioFor()' />Ver Ordenes Medicas</label></td>"+		
							"</tr><tr><td colspan='9'><div id='opciones'>");
					
					
					
				/*	rs=mlh.ResumenOrdenServicio(CodAdmision);
					out.print("<table width='100%' border='1'><tr><td colspan='7'><div align='center' id='cabecera2' class='style11' >RESUMEN ORDENES MEDICAS </div></td></tr>");
					out.print("<tr><td width='27%'><div align='center'>Fecha y Hora </div></td><td width='26%'><div align='center'>Usuario</div></td><td width='23%'><div align='center'>Profesion</div></td><td width='24%'><div align='center'>Tipo Orden</div></td></tr>");
					while(rs.next()){
						String Tipo=rs.getString(5);
						if(Tipo.equals("1")){
							//LABORATORIO
							out.print("<tr><td><a href='#' onclick='ReportLabora("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>LABORATORIO</td></tr>");
						}
						
						if(Tipo.equals("2")){
							//2=IMAGENOLOGIA
							out.print("<tr><td><a href='#' onclick='ReportImage("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>IMAGENOLOGIA</td></tr>");
						}
						
						if(Tipo.equals("3")){
							//3=MEDICAMENTOS
							out.print("<tr><td><a href='#' onclick='ReportMedica("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>MEDICAMENTOS e INSUMOS</td></tr>");
						}
						
						if(Tipo.equals("4")){
							//4=GENERAL
							out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN GENERAL</td></tr>");
							
						}
						if(Tipo.equals("5")){
							//5=PROCEDIMIENTOS
							out.print("<tr><td><a href='#' onclick='ReportesOrdenProcedimiento("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN DE PROCEDIMIENTOS</td></tr>");
						}
						
					}
					out.print("</table>");*/
					
					out.print("</div></td></tr></table>");
				//	rs.getStatement().getConnection().close();

				}else{
					out.print("	ESTA ADMISION NO TIENE NINGUN DIAGNOSTICO DE INGRESO.\nPARA USAR ESTA OPCION DEBE DE DILIGENCIAR UNA HISTORIA CLINICA Y ASIGNAR UN DIAGNOSTICO DE INGRESO.");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(va.equals("6")){
			/**
			 * si el examen es de laboratorio
			 */
			rs1=mlh.ObtenerAreaExamenes();
			out.print("<table width='100%' class='style6' border='1'><tr tyle='font-size:9px'><td width='190'> SELECCIONE GRUPO    </td><td width='563'><select name='cmbgrupos' id='cmbgrupos' onchange='VerExamenes()'><option selected='selected'>SELECCIONE</option>");
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
			out.print("<table width='100%' class='style6' border='1'><tr><td width='197'>DATOS CLINICOS </td><td><textarea name='txtdatosClinicos' cols='60' rows='4' id='txtdatosClinicos'></textarea></td></tr>" +
					"<tr><td height='27'>NOMBRE DEL ESTUDIO  </td><td><input name='txtnomexam' type='text' id='txtnomexam' size='85' onkeyup='autocompleta_nombre()' /><input name='txtcodexamen' type='hidden' id='txtcodexamen' /></td></tr>" +
					"<tr><td height='26'><label><input name='chkportatil' type='checkbox' id='chkportatil' value='(PORTATIL)' />Portatil</label></td><td><div id='sugerencias1'></div></td></tr>" +
					"<tr><td height='24'>&nbsp;</td><td><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' value='Asignar' onclick='enviar()' /></td></tr>" +
					"<tr><td colspan='4'></td></tr></table>");
		}
		
		
		
		if(va.equals("8")){
			int contador=0;
			rs=mlh.ObtenerExamenesArea(CodArea);
			out.print("<table border='1' width='100%' ><tr><td width='18%'>SELECCIONE EXAMEN </td><td width='47%'><select name='cmbExamenes' id='cmbExamenes'><option>Seleccione</option>");
			try {
				while(rs.next()){
					String Value=rs.getString(1)+"|"+rs.getString(3);
					out.print("<option value="+Value+">"+rs.getString(2)+"</option>");
					contador=contador+1;
				}
				out.print("</select></td><td width='16%'><input name='btnAsignar'  type='button' id='btnAsignar' value='     Asignar     ' class='boton4' onclick='AsignarOrdenLab()' /></td><td width='19%'><input name='btnFinalizar'  type='button' id='btnFinalizar' value='     Finalizar     ' class='boton4' onclick='FinalizarLaboratorios()' /></td></tr>");
				out.print("<tr><td>OBSERVACION</td><td colspan='3'><textarea name='txtObservacionExamen' cols='35' rows='2' id='txtObservacionExamen'></textarea></td></tr>");
				out.print("<tr><td colspan='4'><div id='DetOrdLab'>");
				/////////////////////////////////////////////
				out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center'><div align='center' id='cabecera2' class='style11'>Detalle Orden</div><input name='txtCodOrdenLab' type='hidden' id='txtCodOrdenLab' /></div></td></tr>");
				out.print("<tr><td width='42%'><div align='center'>Nombre Examen</div></td><td width='46%'><div align='center'>Observaciones</div></td><td width='12%'><div align='center'>Accion</div></td></tr>");
				out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
				out.print("</table>");
				//////////////////////////////////////////////////////
				out.print("</div></td></tr></table>");

				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String CodOrdenLab=req.getParameter("CodOrdenLab");
		
		if(va.equals("81")){
			//
			int contador=0;
			rs=mlh.ObtenerExamenesArea(CodArea);
			out.print("<table border='1' width='100%' ><tr><td width='18%'>SELECCIONE EXAMEN </td><td width='47%'><select name='cmbExamenes' id='cmbExamenes'><option>Seleccione</option>");
			try {
				while(rs.next()){
					String Value=rs.getString(1)+"|"+rs.getString(3);
					out.print("<option value="+Value+">"+rs.getString(2)+"</option>");
					contador=contador+1;
				}
				rs.getStatement().getConnection().close();
				out.print("</select></td><td width='16%'><input name='btnAsignar'  type='button' id='btnAsignar' value='     Asignar     ' class='boton4' onclick='AsignarOrdenLab()' /></td><td width='19%'><input name='btnFinalizar'  type='button' id='btnFinalizar' value='     Finalizar     ' class='boton4' onclick='FinalizarLaboratorios()' /></td></tr>");
				out.print("<tr><td>OBSERVACION</td><td colspan='3'><textarea name='txtObservacionExamen' cols='35' rows='2' id='txtObservacionExamen'></textarea></td></tr>");
				out.print("<tr><td colspan='4'><div id='DetOrdLab'>");
				/////////////////////////////////////////////
				out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden<input name='txtCodOrdenLab' type='hidden' id='txtCodOrdenLab' value="+CodOrdenLab+" /></div></td></tr>");				
				out.print("<tr><td width='42%'><div align='center'>Nombre Examen</div></td><td width='46%'><div align='center'>Observaciones</div></td><td width='12%'><div align='center'>Accion</div></td></tr>");
				rs3=msa.ObtenerDetalleOrden(CodOrdenLab);
				while(rs3.next()){
					out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenLab("+rs3.getString(1)+")' >Omitir</a></td></tr>");
				}
				out.print("</table>");
				rs3.getStatement().getConnection().close();
				//////////////////////////////////////////////////////
				out.print("</div></td></tr></table>");

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("98")){
			String CodDetOrdLab=req.getParameter("CodDetOrdLab");
			msa.OmitirDetOrdLab(CodDetOrdLab);
			try {
				rs3=msa.ObtenerDetalleOrden(CodOrdenLab);
				if(rs3.next()){
					//todavia tiene detalles la orden 
					out.print(CodOrdenLab);
				}
				else{
					//si no hay ningun detalle en la orden
					msa.OmitirOrdenVacia(CodOrdenLab);
					out.print("");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(va.equals("89")){
			String observacion=req.getParameter("DetOrdenLab");
			//String CodOrdenLab=req.getParameter("CodOrdenLab");
			msa.ActualizarDetalleOrden(CodOrdenLab, observacion);
		}
		
		if(va.equals("9")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			rs=mlh.ObtenerNombreUsuario(usuario);
			String NombreUsuario="";
			try {
				if(rs.next()){
					NombreUsuario=rs.getString(1)+" "+rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(NombreUsuario==""){
				NombreUsuario=NombreMedico;
			}
			mlh.RelacionPacienteLaboratorios(CodEstudio, TipoEstudio, CodigoPac, Hora, Fecha,NombreUsuario,TipoPyP);
			out.print("Ingreso Exitoso");
		}
		
		if(va.equals("FinOrden")){
			String CodOrden=req.getParameter("CodOrden");
			mvf.ActualizarEstadoOrden(CodOrden);
		}
		
		if(va.equals("99")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			
			String ObservacionExamen=req.getParameter("ObservacionExamen");
			String observacion=req.getParameter("DetOrdenLab");
			String TipoOrden="1";
			
			String NombreUsuario="";
			String CodOrdenLab_fk="";
			try {
				rs=mlh.ObtenerNombreUsuario(usuario);
				if(rs.next()){
					NombreUsuario=rs.getString(1)+" "+rs.getString(2);
				}
				rs.getStatement().getConnection().close();
				msa.CrearOrdenEstudios(CodigoPac, CodAdmision, usuario, observacion, Hora, Fecha, TipoOrden);
				rs2=msa.ObtenerCodigoOrden(Hora, Fecha);
				if(rs2.next()){
					CodOrdenLab_fk=rs2.getString(1);
				}				
				msa.CrearDetalleOrdenLabora(CodOrdenLab_fk, CodEstudio, ObservacionExamen);
				rs2.getStatement().getConnection().close();
				out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden<input name='txtCodOrdenLab' type='hidden' id='txtCodOrdenLab' value="+CodOrdenLab_fk+" /></div></td></tr>");				
				out.print("<tr><td width='42%'><div align='center'>Nombre Examen</div></td><td width='46%'><div align='center'>Observaciones</div></td><td width='12%'><div align='center'>Accion</div></td></tr>");
				rs3=msa.ObtenerDetalleOrden(CodOrdenLab_fk);
				while(rs3.next()){
					out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenLab("+rs3.getString(1)+")' >Omitir</a></td></tr>");
				}
				out.print("</table>");
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			mlh.RelacionPacienteLaboratorios(CodEstudio, TipoEstudio, CodigoPac, Hora, Fecha,NombreUsuario,TipoPyP);
		}
		
		if(va.equals("999")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			
			//String CodOrdenLab=req.getParameter("CodOrdenLab");
			String ObservacionExamen=req.getParameter("ObservacionExamen");
			String NombreUsuario="";
			try{
				rs2=msa.ObtenerDetalleOrden(CodOrdenLab, CodEstudio);
				if(rs2.next()){
					out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center'>Detalle Orden<input name='txtCodOrdenLab' type='hidden' id='txtCodOrdenLab' value="+CodOrdenLab+" /></div></td></tr>");				
					out.print("<tr><td width='42%'><div align='center'>Nombre Examen</div></td><td width='46%'><div align='center'>Observaciones</div></td><td width='12%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrden(CodOrdenLab);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenLab("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
					rs3.getStatement().getConnection().close();
					rs=mlh.ObtenerNombreUsuario(usuario);
					if(rs.next()){
						NombreUsuario=rs.getString(1)+" "+rs.getString(2);
					}
					rs.getStatement().getConnection().close();
					mlh.RelacionPacienteLaboratorios(CodEstudio, TipoEstudio, CodigoPac, Hora, Fecha,NombreUsuario,TipoPyP);
				
				}
				else{
					msa.CrearDetalleOrdenLabora(CodOrdenLab, CodEstudio, ObservacionExamen);
					out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center'>Detalle Orden<input name='txtCodOrdenLab' type='hidden' id='txtCodOrdenLab' value="+CodOrdenLab+" /></div></td></tr>");				
					out.print("<tr><td width='42%'><div align='center'>Nombre Examen</div></td><td width='46%'><div align='center'>Observaciones</div></td><td width='12%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrden(CodOrdenLab);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenLab("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
					rs3.getStatement().getConnection().close();
					rs=mlh.ObtenerNombreUsuario(usuario);
					if(rs.next()){
						NombreUsuario=rs.getString(1)+" "+rs.getString(2);
					}
					rs.getStatement().getConnection().close();
					mlh.RelacionPacienteLaboratorios(CodEstudio, TipoEstudio, CodigoPac, Hora, Fecha,NombreUsuario,TipoPyP);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		/*******************************FIN ORDENES DE SERVICIO**********************************/
		
		/*********************FORMATOS**********************************/
		if(va.equals("10")){
			
			//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td width='192'>Seleccione Formato </td><td width='776'><label><input name='txtFormato' type='text' id='txtFormato' size='90' onkeyup='autocompletarFormato()' /><input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /><input name='txtCodFormato' type='hidden' id='txtCodFormato' /></label></td></tr><tr><td colspan='2'><div class='style6' id='sugerenciasFormato'></div></td></tr></table>");
			out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td><div align='center'>Seleccione Formato <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name='txtCodFormato' id='txtCodFormato'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mlh.ObtenerFormatosPermitidosUsuario(CodigoUsuario);
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();			
				out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /></label></div></td></tr></table>");
				out.print("<br>");
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td width='20%'>Nombre Del Formato </td><td width='10%'>Hora y Fecha </td><td width='19%'>Usuario <select name='cmbEspecialidadesCargadas' id='cmbEspecialidadesCargadas' onchange='MostrarFormatosEspecialidad()'><option value='Ver Todos' selected='selected'>Ver Todos...</option> ");
			    rs=mlh.ObtenerEspecialidadesPaciente(CodPaciente, CodAdmision);
			    while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(1)+"</option>");
				}
				rs.getStatement().getConnection().close();
			 
				out.print("</select><a href='#' onclick='ImprimirTodos("+CodAdmision+")'>Imprimir</a></td><td width='14%'>Acciones de Formato</td><td width='37%'><div align='center'>Areas Del Formato</div></td></tr><tr><td colspan='4'><div id='FormatosPaciente'></div></td><td><div id='areas'>.</div></td></tr></table>");
				out.print("<table width='100%' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td><div align='center'> Preguntas Del Formato</div></td></tr><tr><td><div id='formulario'></div></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/********************************************************************************/
		if(va.equals("11E")){
			String Especialidades=req.getParameter("Especialidades");
			
			String est="";
			/**
			 * una ves guardada la relacion se procede a mostrar los formatos - 
			 */
			if((CodUsu.equals("174"))||(CodUsu.equals("258"))){
				//out.print("Entro en la validacion de los usuarios.");
				String CodForPac="";
				String CodUsuario="";
				try {
					rs=mlh.ObtenerFormatosPacienteE(CodPaciente, CodAdmision,Especialidades);
					out.print("<table border='0' width='100%' class='style2' >");
					while(rs.next()){
						String FechaIni=rs.getString(3);
						String HoraIni=rs.getString(4);
						String dia,mes,anio=null; 
						String horas,minutos,segundos=null;
						CodForPac=rs.getString(9);
						dia=FechaIni.substring(8,10);
						mes=FechaIni.substring(5,7);
						anio=FechaIni.substring(0,4);
						CodUsuario=rs.getString(7);
						horas=HoraIni.substring(0,2);
						minutos =HoraIni.substring(3,5);
						segundos=HoraIni.substring(6,8);
						est=rs.getString(10);
						
						/*if((CodUsuario.equals("174"))||(CodUsuario.equals("258"))){
							if(rs.getString(7).equals(CodUsu)&& rs.getString(10).equals("0")){
								out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormato("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+CodAdmision+","+rs.getString(1)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Eliminar' name='btnEliminarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormato("+rs.getString(9)+")' style='cursor:pointer;' /></td>");
								}
								else{
									if(rs.getString(10).equals("1")){
										out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' /></td>");
									}
									else{
										out.print("<td width='19%'><img title='Pendiente' name='btnPendiente' src='Imagenes/P.JPG' id='btnPendiente'  style='cursor:pointer;' />&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' /></td>");
									}
									
								}
						}*/
						rs1=mlh.BuscarCoFirmaFormato(CodForPac);
						if(rs1.next()){
							out.print("<tr><td width='35%'><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+CodAdmision+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
							out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
							/***********************/
							out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",1)' style='cursor:pointer;' />");
							/***********************/
							out.print("</td>");
							out.print("</tr>");
						}else{
							out.print("<tr><td width='35%'><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+CodAdmision+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
							out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormatoM("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+CodAdmision+","+rs.getString(1)+","+CodUsuario+")' style='cursor:pointer;' />&nbsp;&nbsp;");
							
							out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",0)' style='cursor:pointer;' />");
							
							out.print("&nbsp;&nbsp;<img title='Eliminar' name='btnEliminarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormato("+rs.getString(9)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Preliminar' name='btnPreliminar' src='Imagenes/Preliminar.JPG' id='btnPreliminar'onclick='Preliminar("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+CodAdmision+")' style='cursor:pointer;' /></td>");
							out.print("</tr>");
						}
						rs1.getStatement().getConnection().close();
						
					}
					out.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR en Valor Igual 11 ControlFormatosPestanas ResultSet "+e);
					e.printStackTrace();
				}
			}else			
			try {
				rs=mlh.ObtenerFormatosPacienteE(CodPaciente, CodAdmision,Especialidades);
				out.print("<table border='0' width='100%' class='style2' >");
				while(rs.next()){
					String FechaIni=rs.getString(3);
					String HoraIni=rs.getString(4);
					String dia,mes,anio=null; 
					String horas,minutos,segundos=null;
					String CodForPac=rs.getString(9);
					dia=FechaIni.substring(8,10);
					mes=FechaIni.substring(5,7);
					anio=FechaIni.substring(0,4);
					
					horas=HoraIni.substring(0,2);
					minutos =HoraIni.substring(3,5);
					segundos=HoraIni.substring(6,8);
					est=rs.getString(10);
					out.print("<tr><td width='35%'><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+CodAdmision+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
					if(rs.getString(7).equals(CodUsu)&& rs.getString(10).equals("0")){
					out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormato("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+CodAdmision+","+rs.getString(1)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Eliminar' name='btnEliminarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormato("+rs.getString(9)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Preliminar' name='btnPreliminar' src='Imagenes/Preliminar.JPG' id='btnPreliminar'onclick='Preliminar("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+CodAdmision+")' style='cursor:pointer;' /></td>");
					}
					else{
						rs1=mlh.BuscarCoFirmaFormato(CodForPac);
						if(rs1.next()){
							if(rs.getString(10).equals("1")){
								out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",1)' style='cursor:pointer;' />");
								out.print("</td>");
							}
							else{
								out.print("<td width='19%'><img title='Pendiente' name='btnPendiente' src='Imagenes/P.JPG' id='btnPendiente'  style='cursor:pointer;' />&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",1)' style='cursor:pointer;' />");
								out.print("</td>");
							}
						}else{
							if(rs.getString(10).equals("1")){
								out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",0)' style='cursor:pointer;' />");
								out.print("</td>");
							}
							else{
								out.print("<td width='19%'><img title='Pendiente' name='btnPendiente' src='Imagenes/P.JPG' id='btnPendiente'  style='cursor:pointer;' />&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",0)' style='cursor:pointer;' />");
								out.print("</td>");
							}
						}
						rs1.getStatement().getConnection().close();						
					}
					out.print("</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("ERROR en Valor Igual 11 ControlFormatosPestanas ResultSet "+e);
				e.printStackTrace();
			}
		}

		
		/********************************************************************************/
		
		if(va.equals("11")){
			String est="";
			/**
			 * una ves guardada la relacion se procede a mostrar los formatos - 
			 */
			if((CodUsu.equals("174"))||(CodUsu.equals("258"))){
				//out.print("Entro en la validacion de los usuarios.");
				String CodForPac="";
				String CodUsuario="";
				try {
					rs=mlh.ObtenerFormatosPaciente(CodPaciente, CodAdmision);
					out.print("<table border='0' width='100%' >");
					while(rs.next()){
						String FechaIni=rs.getString(3);
						String HoraIni=rs.getString(4);
						String dia,mes,anio=null; 
						String horas,minutos,segundos=null;
						CodForPac=rs.getString(9);
						dia=FechaIni.substring(8,10);
						mes=FechaIni.substring(5,7);
						anio=FechaIni.substring(0,4);
						CodUsuario=rs.getString(7);
						horas=HoraIni.substring(0,2);
						minutos =HoraIni.substring(3,5);
						segundos=HoraIni.substring(6,8);
						est=rs.getString(10);
						
						/*if((CodUsuario.equals("174"))||(CodUsuario.equals("258"))){
							if(rs.getString(7).equals(CodUsu)&& rs.getString(10).equals("0")){
								out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormato("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+CodAdmision+","+rs.getString(1)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Eliminar' name='btnEliminarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormato("+rs.getString(9)+")' style='cursor:pointer;' /></td>");
								}
								else{
									if(rs.getString(10).equals("1")){
										out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' /></td>");
									}
									else{
										out.print("<td width='19%'><img title='Pendiente' name='btnPendiente' src='Imagenes/P.JPG' id='btnPendiente'  style='cursor:pointer;' />&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' /></td>");
									}
									
								}
						}*/
						rs1=mlh.BuscarCoFirmaFormato(CodForPac);
						if(rs1.next()){
							out.print("<tr><td width='35%'><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+CodAdmision+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
							out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
							/***********************/
							out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",1)' style='cursor:pointer;' />");
							/***********************/
							out.print("</td>");
							out.print("</tr>");
						}else{
							out.print("<tr><td width='35%'><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+CodAdmision+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
							out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormatoM("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+CodAdmision+","+rs.getString(1)+","+CodUsuario+")' style='cursor:pointer;' />&nbsp;&nbsp;");
							
							out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",0)' style='cursor:pointer;' />");
							
							out.print("&nbsp;&nbsp;<img title='Eliminar' name='btnEliminarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormato("+rs.getString(9)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Preliminar' name='btnPreliminar' src='Imagenes/Preliminar.JPG' id='btnPreliminar'onclick='Preliminar("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+CodAdmision+")' style='cursor:pointer;' /></td>");
							out.print("</tr>");
						}
						rs1.getStatement().getConnection().close();
						
					}
					out.print("</table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("ERROR en Valor Igual 11 ControlFormatosPestanas ResultSet "+e);
					e.printStackTrace();
				}
			}else			
			try {
				rs=mlh.ObtenerFormatosPaciente(CodPaciente, CodAdmision);
				out.print("<table border='0' width='100%' class='style2' >");
				while(rs.next()){
					String FechaIni=rs.getString(3);
					String HoraIni=rs.getString(4);
					String dia,mes,anio=null; 
					String horas,minutos,segundos=null;
					String CodForPac=rs.getString(9);
					dia=FechaIni.substring(8,10);
					mes=FechaIni.substring(5,7);
					anio=FechaIni.substring(0,4);
					
					horas=HoraIni.substring(0,2);
					minutos =HoraIni.substring(3,5);
					segundos=HoraIni.substring(6,8);
					est=rs.getString(10);
					out.print("<tr><td width='35%'><a  href='#'onclick='VerAreas("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+CodAdmision+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
					if(rs.getString(7).equals(CodUsu)&& rs.getString(10).equals("0")){
					out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormato("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+CodAdmision+","+rs.getString(1)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Eliminar' name='btnEliminarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormato("+rs.getString(9)+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Preliminar' name='btnPreliminar' src='Imagenes/Preliminar.JPG' id='btnPreliminar'onclick='Preliminar("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+CodAdmision+")' style='cursor:pointer;' /></td>");
					}
					else{
						rs1=mlh.BuscarCoFirmaFormato(CodForPac);
						if(rs1.next()){
							if(rs.getString(10).equals("1")){
								out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",1)' style='cursor:pointer;' />");
								out.print("</td>");
							}
							else{
								out.print("<td width='19%'><img title='Pendiente' name='btnPendiente' src='Imagenes/P.JPG' id='btnPendiente'  style='cursor:pointer;' />&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",1)' style='cursor:pointer;' />");
								out.print("</td>");
							}
						}else{
							if(rs.getString(10).equals("1")){
								out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",0)' style='cursor:pointer;' />");
								out.print("</td>");
							}
							else{
								out.print("<td width='19%'><img title='Pendiente' name='btnPendiente' src='Imagenes/P.JPG' id='btnPendiente'  style='cursor:pointer;' />&nbsp;&nbsp;&nbsp;&nbsp;");
								out.print("<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodAdmision+","+est+",0)' style='cursor:pointer;' />");
								out.print("</td>");
							}
						}
						rs1.getStatement().getConnection().close();						
					}
					out.print("</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("ERROR en Valor Igual 11 ControlFormatosPestanas ResultSet "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("PH")){
			
			String CodAdm=req.getParameter("CodAdm");
			String CodFormato=req.getParameter("CodFormato");
			String fecha=req.getParameter("fecha");
			String hora=req.getParameter("hora");
			String CodPaci=req.getParameter("CodPac");
			String CodArea1="";
			String NombreArea="";
			rs8=mmp.VerificarDatosAdmision(CodPaci, CodAdm);
			int contador=0;
			try {
				if(rs8.next()){
					out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA'bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs8.getString(1)+" "+rs8.getString(2)+" "+rs8.getString(3)+"</div></td><td width='9%' style='color:#215b8b'>Identificacion</td><td width='13%' style='color:#215b8b'><div>"+rs8.getString(6)+" "+rs8.getString(7)+"</div><input name='CedPac' type='hidden' id='CedPac' value='"+rs8.getString(7)+"' /></td><td width='12%' style='color:#215b8b'>Fecha Nacimiento</td><td width='13%' style='color:#215b8b'><div>"+rs8.getString(5)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Edad:"+rs8.getString(4)+"</div></td></tr></table>");
					out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Entidad:</td><td width='49%' style='color:#215b8b'><div>"+rs8.getString(11)+"</div></td><td width='13%' style='color:#215b8b'>Servicio y Ubicacion:</td><td width='25%' style='color:#215b8b'><div>"+rs8.getString(9)+" Cama "+rs8.getString(10)+"</div><input name='txtCodCama' type='hidden' id='txtCodCama' value="+rs8.getString(12)+"  /> <input name='txtCodSubarea' type='hidden' id='txtCodSubarea' value="+rs8.getString(14)+"  /> <input name='txtCodArea' type='hidden' id='txtCodArea' value="+rs8.getString(13)+"  /></td></tr></table>");
				}
				rs8.getStatement().getConnection().close();	
				rs4=mvf.ObtenerAreasFormato(CodFormato);
				out.print("<table width='100%'><tr id='pestanas'>");
				while(rs4.next()){	
					contador=contador+1;
					NombreArea=rs4.getString(2);
					out.print("<td ><div  id='ar"+contador+"' class='style11 cabecera2gris' align='center'><a href='#' onclick='MostrarPreguntasPH("+rs4.getString(1)+","+contador+")' style='color:#ffffff'>"+NombreArea+"</a></div></td>");
				}

				out.print("</tr><tr><td colspan="+contador+"><div id='formu'></div></td></tr></table>");
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
			
		}
		if(va.equals("12")){
			String val="";
			String TipoReq="";
			//String CodFormato=req.getParameter("CodFormato");
			String CodUsuario=req.getParameter("CodUsuario");
			String CodUsuario2=req.getParameter("CodUsuario2");
			String NombreUsuario="";
			String TarjetaProfesional="";
			String Cargo="";
			String UsuarioEntero="";
			rs=mlh.BuscarPreguntasRequeridas(FechaFormato, HoraFormato,CodAdmision);
			try {
				if(rs.next()){
					val=rs.getString(1);
					if(val!=""){
						rs1=mlh.BuscarPreguntasRequeridas(FechaFormato, HoraFormato,CodAdmision);
						while(rs1.next()){
							TipoReq=rs1.getString(3);
							if(TipoReq.equals("1")){
								out.print("La Pregunta "+rs1.getString(2)+" Es Requerida y No Puede ir Vacia.\n");
							}
							if(TipoReq.equals("2")){
								out.print("La Pregunta "+rs1.getString(2)+" Es De Tipo Epicrisis y No Puede ir Vacia.\n");
							}
							if(TipoReq.equals("3")){
								out.print("La Pregunta "+rs1.getString(2)+" Es Requerida y de Tipo Epicrisis y No Puede ir Vacia.\n");
							}
						}
						rs1.getStatement().getConnection().close();
					}
				}				
				else{
				if((CodUsuario2.equals("174"))||(CodUsuario2.equals("258"))){
					mlh.ActualizarFormatoActivo(CodigoFormatoPaciente);
					mlh.ActualizarPreguntasFormatoActivo(FechaFormato, HoraFormato,CodAdmision);
				}else{
					if((CodUsuario.equals("174"))||(CodUsuario.equals("258"))){
						rs3=mlh.NombreUsuarioCofirma(CodUsuario);
						if(rs3.next()){
							NombreUsuario=rs3.getString(1);
							TarjetaProfesional=rs3.getString(2);
							Cargo=rs3.getString(3);
							UsuarioEntero="____________________________________ \n Cofirma:"+NombreUsuario+"\n"+Cargo+"\n"+"R.M "+TarjetaProfesional;
							mlh.InsertarCofirma(UsuarioEntero, CodigoFormatoPaciente);
						}
						rs3.getStatement().getConnection().close();
						
					}
					mlh.ActualizarFormatoActivo(CodigoFormatoPaciente);
					mlh.ActualizarPreguntasFormatoActivo(FechaFormato, HoraFormato,CodAdmision);
					
					
					//se verifica si el formato guardado es una interconsulta
					rs3= mvf.BuscarFormInter(CodigoFormatoPaciente);
					//si es un formato de intercolsulta se cambia el estado 
					//en la tabla interconsulta (estado=1) realizada
					if (rs3.next()){
						mvf.ActualizarEstadoInterconsultas(CodUsuario, CodigoFormatoPaciente, CodAdmision);
						//se carga el movimiento (interconsulta) a la prefactura
						java.util.Date fechaActual = new java.util.Date();
						java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
						java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
						ResultSet interrs1=null;
						ResultSet interrs2=null;
						ResultSet interrs3=null;
						ResultSet interrs4=null;
						
						String docpac="";
						String numDocPac="";
						String codMedico="";
						String codManualTarifario="";
						String codRef="";
						String tipoManual="";
						String codEncFact="";
						String valorInterconsulta="";
						
						interrs1= mvf.BuscarDatosPac(CodAdmision, CodUsuario);
						
						if(interrs1.next()){
							 docpac= interrs1.getString(1);
							 numDocPac=interrs1.getString(2);
							 codMedico=interrs1.getString(3);
							 codEncFact=interrs1.getString(4);
							 
							 interrs2 = mvf.BuscarManualTarifario(docpac, numDocPac);
								
								if(interrs2.next()){
									codManualTarifario = interrs2.getString(9);
									tipoManual=interrs2.getString(13);
									
									if (tipoManual.equals("1")){
										//manual iss
										codRef="890402";
										
									}else{
										if (tipoManual.equals("2")){
											//manual SOAT
											codRef="36100";
										}
									}
									
									interrs3 = mvf.BuscarValorInterconsulta(codManualTarifario, codRef);
									if(interrs3.next()){
										mvf.InsertarMovInterconsulta(Fecha,
												Hora,
												interrs3.getString(3),
												codRef,
												interrs3.getString(12),
												interrs3.getString(6),
												CodUsuario,
												codEncFact,
												codMedico,
												interrs3.getString(15));
									}
									interrs3.getStatement().getConnection().close();
									
								}
							 
						}
						
						
						
						
						
						
						
						//fin se carga el movimiento (interconsulta) a la prefactura
						
						
					}
					
					
					
					
					
					
				}
				}
				//******************************/
				/*String TipoUsuario="";
				String CodEncabezadoFact="";
				String NombreFormato="";
				String CodEps="";
				String CodReferencia="";
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
				
				if(NombreFormato.equals("HISTORIA CLINICA DE URGENCIAS")){
					rs4=mvf.ObtenerEncabezadoFactura(CodAdmision);
					if(rs4.next()){
						CodEncabezadoFact=rs4.getString(1);
						CodEps=rs4.getString(2);
						CodReferencia="890701";
						rs5=mvf.ObtenerPrograma(CodEps,CodReferencia);
						if(rs5.next()){
							String cantidad="1";
							mvf.InsertarDetalleFactura(FechaFormato, HoraFormato, rs5.getString(2), rs5.getString(1), rs5.getString(3), rs5.getString(4), FechaFormato, cantidad, rs5.getString(5), CodUsuario, CodEncabezadoFact, CodUsuario);
						}
						rs5.getStatement().getConnection().close();
					}else{}
					rs4.getStatement().getConnection().close();
					if((TipoUsuario.equals("Especialista"))&&(NombreFormato.equals("EVOLUCION MEDICA"))){
						rs4=mvf.ObtenerEncabezadoFactura(CodAdmision);
						if(rs4.next()){
							CodEncabezadoFact=rs4.getString(1);
							CodEps=rs4.getString(2);
						}
						rs4.getStatement().getConnection().close();
						CodReferencia="890702";
						rs5=mvf.ObtenerPrograma(CodEps,CodReferencia);
						if(rs5.next()){
							String cantidad="1";
							mvf.InsertarDetalleFactura(FechaFormato, HoraFormato, rs5.getString(2), rs5.getString(1), rs5.getString(3), rs5.getString(4), FechaFormato, cantidad, rs5.getString(5), CodUsuario, CodEncabezadoFact, CodUsuario);
						}
						rs5.getStatement().getConnection().close();
					}
					else{}
				}*/
				
				/*******************************/
				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		/********************* FIN DE FORMATOS *************************/

		if(va.equals("interPend")){			
			String CodUsuario=req.getParameter("codUsuario");
			
			try {
				rs1=mlh.ObtenerDiagIngreso(CodAdmision);
				if(rs1.next()){
					out.print("<table width='100%' class='style6' border='1' cellspacing='0'><tr>" +
							//"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='8' onclick='RadioFor()' />Formulacion</label></td>"+
							//"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='lab' onclick='RadioFor()' />Laboratorio</label></td>"+
							//"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='img' onclick='RadioFor()' />Imagenes Diagnosticas</label></td>" +
							//"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='Pro' onclick='RadioFor()' />Procedimientos</label></td>" +
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='pendInter' onclick='RadioFor()' />Pendientes</label></td>"+
							"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='interconsulta' onclick='RadioFor()' />Solicitar</label></td>"+
							"</tr><tr><td colspan='6'><div id='opciones'>");				
					rs=mvf.BuscarInterconsultasPendPac(CodAdmision);
					out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' id='cabecera2' class='style11' >INTERCONSULTAS PENDIENTES </div></td></tr>");
					out.print("<tr><td width='27%'><div align='center'>Fecha y Hora </div></td><td width='26%'><div align='center'>Medico que la Solicita</div></td><td width='23%'><div align='center'>Especialidad</div></td><td width='24%'><div align='center'>Realizar</div></td></tr>");
					while(rs.next()){
						
						
							out.print("<tr><td>"+rs.getString(6)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td>");
							
							
							     rs1 = mvf.BuscarEspecialidadUsuario (CodUsuario);
							     if(rs1.next()){
							     if(rs.getString(8).equals(rs1.getString(1)) ){
							    	 out.print("<td align='center'><a href='#' onclick='Formatos()'>realizar</a></td>");
							     }else{
							    	 out.print("<td></td>");
							    	 
							     }rs.getStatement().getConnection().close();
								 }
							
							     out.print("</tr>");
						
					}
					out.print("</table>");
					
					out.print("</div></td></tr></table>");
					//rs.getStatement().getConnection().close();

				}else{
					out.print("	ESTA ADMISION NO TIENE NINGUN DIAGNOSTICO DE INGRESO.\nPARA USAR ESTA OPCION DEBE DE DILIGENCIAR UNA HISTORIA CLINICA Y ASIGNAR UN DIAGNOSTICO DE INGRESO.");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
		
	}
}
