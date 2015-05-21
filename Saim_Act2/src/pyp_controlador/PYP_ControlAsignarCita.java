package pyp_controlador;

//import fact_metodo.MetodoSeguimientoAdmision;
//import hic_metodo.MetodoIngresarResultados;
import hic_metodo.MetodoLaboratoriosHistoria;
import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
//import java.text.DateFormat;
import java.util.Calendar;

//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seguridad_bean.Usuario;

import adm_logica.MetodoAdmision;
import adm_logica.MetodoPaciente;
import pyp_metodo.PYP_MetodoAsignarCita;
import pyp_metodo.PYP_MetodoConsultasAsignacion;
import pyp_metodo.PYP_MetodoMedicos;
import pyp_metodo.PYP_MetodoParametros;


public class PYP_ControlAsignarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		PYP_MetodoAsignarCita mac = new PYP_MetodoAsignarCita();
		PYP_MetodoMedicos mmd = new PYP_MetodoMedicos();
		PYP_MetodoParametros mp = new PYP_MetodoParametros();
		PYP_MetodoConsultasAsignacion mca = new PYP_MetodoConsultasAsignacion();
		MetodoLaboratoriosHistoria mlh=new MetodoLaboratoriosHistoria();
		MetodoPaciente pac=new MetodoPaciente();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		ResultSet rs8=null;
		ResultSet rs13=null;
		ResultSet rs14=null;
		String TipoDocumento=req.getParameter("TipoDocumento");
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String CodigoEspecialidad=req.getParameter("CodigoEspecialidad");
		String op=req.getParameter("op");
		/***/
		String fecha=req.getParameter("fecha");
		String hora=req.getParameter("hora");
		String CodigoPaciente=req.getParameter("CodigoPaciente");
		String CodigoMedico=req.getParameter("CodigoMedico");
		String codigoHorarioMedico=req.getParameter("codigoHorarioMedico");
		String NombrePaciente=req.getParameter("NombrePaciente");
		String UsuarioInsercion=req.getParameter("UsuarioInsercion");
		String FechaCita=req.getParameter("Fecha");
		String HoraCita=req.getParameter("Hora");
		/***/
		String CodMedico=req.getParameter("CodMedico");
		String CodEspecialista=req.getParameter("CodEspecialista");
		String Tip=req.getParameter("Tipo");
		String CodHorarioMedico=req.getParameter("CodHorarioMedico");
		String CodUsuario=req.getParameter("CodUsuario");
		/***/
		String CodArea=req.getParameter("CodArea");
		String CodFormato=req.getParameter("CodFormato");
		MetodoVerFormatos mvf = new MetodoVerFormatos ();
		
		String Resul=req.getParameter("Resul");
		String CodResul=req.getParameter("CodResul");
		String CodForPacCE=req.getParameter("CodForPacCE");
		String CodMed=req.getParameter("CodMed");
		String FechaPR=req.getParameter("FechaPcita");
		//MetodoIngresarResultados mir = new MetodoIngresarResultados();
		
		
		if(va.equals("CHMCEX")){
			rs=mac.BuscarMedicosConsultaExterna();
			try {
				out.print("<table width='100%' border='1' cellspacing='0' ><tr><td colspan='3' align='center'>HORARIOS MEDICOS CONSULTA EXTERNA</td></tr>");
				while(rs.next()){
					out.print("<tr bgcolor='#E6E6E6'><td colspan='2'>"+rs.getString("Medico")+"</td><td>"+rs.getString("profesion")+"-"+rs.getString("ocupacion")+"</td></tr>");
					rs1=mac.BuscarMedicosConsultaExternaDetalle(rs.getString("codigo"));
					while(rs1.next()){
						if(rs1.getString("Dia").equals("Monday")){
							out.print("<tr><td>Lunes</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Tuesday")){
							out.print("<tr><td>Martes</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}							
						}
						if(rs1.getString("Dia").equals("Wednesday")){
							out.print("<tr><td>Miercoles</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Thursday")){
							out.print("<tr><td>Jueves</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Friday")){
							out.print("<tr><td>Viernes</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Saturday")){
							out.print("<tr><td>Sabado</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
					}
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("HorDisp")){			
			try {
				rs=mac.BuscarHorasDisponibles(CodMed,FechaPR);
				out.print("<td><label><div id='CamHoraMed'>Hora<select name='cmbhora' id='cmbhora'><option value='Seleccione' selected='selected'>Seleccione</option>");
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(1)+"</option>");
				}
				out.print("</select></div></label> </td>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
if(va.equals("GNCEX")){
			
			String FechaPcita=req.getParameter("FechaPcita");
			//String HoraPcita=req.getParameter("HoraPcita");
			String CodUsu=req.getParameter("CodUsu");
			String CodPacC=req.getParameter("CodPac");
			String CodMedicoN=req.getParameter("CodMedico");
			String NombreCompleto=req.getParameter("NombreCompleto");
			String CodHorario=req.getParameter("CodHorario");
			String CodEspMed=req.getParameter("CodEspMed");
			String TipoCita=req.getParameter("TipoCita");
			String Observacion=req.getParameter("Observacion");
			/////////////////////////////////////////////////////////
			String CodProg=req.getParameter("CodProg");
			String CodDx=req.getParameter("CodDx");
			String ValorPro=req.getParameter("ValorPro");
			String NumAutori=req.getParameter("NumAutori");
			String Copago=req.getParameter("Copago");
			String Moderacion=req.getParameter("Moderacion");
			String CodCsC=req.getParameter("CodCsC");
			String CodSucur=req.getParameter("CodSucur");
			String CodEnt=req.getParameter("CodEnt");
			String NitEnti=req.getParameter("NitEnti");
			String CodTercero_fk="";
			String CodCuenta_fk="";
			
			//System.out.println("TipoCita="+TipoCita+" Observacion="+Observacion);
			///////////////////////////////////////////////////////
			rs=mp.BuscarFechaHora3(CodHorario);
			try {
				if(rs.next()){
					out.print("La Fecha y Hora de la Cita que esta Tratando de Asignar ya se Encuentra Ocupada.\nRelacionado de la Siguiente forma Nombre Paciente: "+rs.getString(6)+" Fecha y Hora de Cita: "+rs.getString(3)+"/"+rs.getString(2));
				}else{
					mac.ActualizarRegistroHorarioMedico(CodHorario, NombreCompleto);
					java.util.Date FechaAc1 = new java.util.Date();
					java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
					java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
					rs1=mp.BuscarFechaHoraSolo(CodHorario);
					if(rs1.next()){
						FechaPcita=rs1.getString(3);
					}
					rs1.getStatement().getConnection();
					mac.AsignarCita(CodEspMed, CodHorario, CodMedicoN, CodPacC, CodUsu, FechaPcita,Fecha_Insercion1+"",Hora_Insercion1+"",TipoCita,Observacion);
				    //mac.ActualizarHorasFechasMedicoIgual(HoraPcita, FechaPcita, CodMedicoN);
					//se busca la cuenta del programa si no encuentra la cuenta envia 0
					
					rs1=mp.CodigoCuentaPrograma(CodProg);
					if(rs1.next()){
						CodCuenta_fk=rs1.getString(5);
					}else{
						CodCuenta_fk="0";
					}
					rs1.getStatement().getConnection().close();
					//se busca el codigo del tercero
					
					rs1=mp.CodigoTercero(NitEnti);
					if(rs1.next()){
						CodTercero_fk=rs1.getString(1);
					}else{
						CodTercero_fk="0";
					}
					rs1.getStatement().getConnection().close();
					
					// se ingresa en la tabla cont_movimientos_agrupados
					mac.IngresarMovimientoAgrupado(CodProg, CodCuenta_fk, CodEnt, CodTercero_fk, CodSucur, CodCsC, Fecha_Insercion1+"", NumAutori, Copago, Moderacion, Fecha_Insercion1+"", Hora_Insercion1+"", CodUsu, ValorPro, CodHorario,CodDx);
					
					out.print("1");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		
		if(va.equals("CCMT")){
			String Cod_Medico=req.getParameter("CodMedico");
			out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center'>Resultado del Filtro.</div></td></tr>");
			out.print("<tr><td>Nº</td><td width='13%'><div align='center'>Fecha</div></td><td  width='17%' ><div align='center'>Hora</div></td><td <td width='10%'>Tipo Cita </td><td width='30%'><div align='center'>Observacion </div></td><td <td width='30%'><div align='center'>Nombre Paciente</div></td></tr>");
			String FechaSeleccionada="";
			String CodUsuMed="";
			String CodEspMed="";
			int Cant=0;
			try {
				rs3=mca.CitasActivasSoloMedico(Cod_Medico);
				while(rs3.next()){		
					String ValHo=rs3.getString(1);
					FechaSeleccionada=rs3.getString(3);
					CodUsuMed=rs3.getString(11);
					CodEspMed=rs3.getString(9);
					Cant=Cant+1;
					if(rs3.getString(8).equals("0")){
						out.print("<tr ><td>"+Cant+"</td><td><div align='center' style='font-size:20px'>"+rs3.getString(3)+"</div></td><td ><div align='center' style='font-size:20px'>"+rs3.getString(2)+"</div></td>" +
								
								"<td><div align='center'><select style='width:100%' id='cmbTipoCita"+ValHo+"'><option value='Seleccione'>Seleccione</option> ");
										rs=mca.TiposCita();
										while(rs.next()){
											out.print("<option value='"+rs.getString(2)+"' >"+rs.getString(2)+"</option>");
										}
										rs.getStatement().getConnection().close();
						out.print("</select></div></td>" +
								"<td><div align='center'><textarea rows='2' style='width:100%' id='txtObservacion"+ValHo+"'></textarea> </div></td>" +
								"<td bgcolor='#00CC33' onclick='AsignarCita("+ValHo+")'>Click para asignar</td>"+
						"</tr>");
					}
					
				}
				out.print("<input name='txtFechaSel' type='hidden' id='txtFechaSel' value="+FechaSeleccionada+" >");
				out.print("<input name='txtCodUsuMed' type='hidden' id='txtCodUsuMed' value="+CodUsuMed+" >");
				out.print("<input name='txtCodEspMed' type='hidden' id='txtCodEspMed' value="+CodEspMed+" >");
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("CCM")){
			String Fecha_sel=req.getParameter("Fecha");
			String Cod_Medico=req.getParameter("CodMedico");
			
			String Anio="";String Mes="";String Dia="";String Mes_letra="";
			int h=Fecha_sel.split("-").length;
			String[] d=Fecha_sel.split("-");		     	
			for(int g=0; g<h-1; g=g+1)
			{ 
				Anio=d[0];Mes=d[1];Dia=d[2];
			}
			if(Mes.equals("1")){Mes_letra="Enero";}if(Mes.equals("2")){Mes_letra="Febrero";}if(Mes.equals("3")){Mes_letra="Marzo";}
			if(Mes.equals("4")){Mes_letra="Abril";}if(Mes.equals("5")){Mes_letra="Mayo";}if(Mes.equals("6")){Mes_letra="Junio";}
			if(Mes.equals("7")){Mes_letra="Julio";}if(Mes.equals("8")){Mes_letra="Agosto";}if(Mes.equals("9")){Mes_letra="Septiembre";}
			if(Mes.equals("10")){Mes_letra="Octubre";}if(Mes.equals("11")){Mes_letra="Noviembre";}if(Mes.equals("12")){Mes_letra="Diciembre";}
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center'>Resultado del Filtro para el dia "+Dia+" de "+Mes_letra+" de "+Anio+"</div></td></tr>");
			out.print("<tr><td>Nº</td><td  width='17%' ><div align='center'>Hora</div></td><td <td width='15%'>Tipo Cita </td><td width='35%'><div align='center'>Observacion </div></td><td <td width='33%'><div align='center'>Nombre Paciente</div></td></tr>");
			String FechaSeleccionada="";
			String CodUsuMed="";
			String CodEspMed="";
			int Cant=0;
			try {
				rs3=mca.consultapormedicofecha2(Cod_Medico, Fecha_sel);
				while(rs3.next()){		
					String ValHo=rs3.getString(1);
					FechaSeleccionada=rs3.getString(3);
					CodUsuMed=rs3.getString(11);
					CodEspMed=rs3.getString(9);
					Cant=Cant+1;
					if(rs3.getString(8).equals("0")){
						out.print("<tr ><td>"+Cant+"</td><td ><div align='center' style='font-size:20px'>"+rs3.getString(2)+"</div></td>" +
								
								"<td><div align='center'><select style='width:100%' id='cmbTipoCita"+ValHo+"'><option value='Seleccione'>Seleccione</option> ");
										rs=mca.TiposCita();
										while(rs.next()){
											out.print("<option value='"+rs.getString(2)+"' >"+rs.getString(2)+"</option>");
										}
										rs.getStatement().getConnection().close();
						out.print("</select></div></td>" +
								"<td><div align='center'><textarea rows='2' style='width:100%' id='txtObservacion"+ValHo+"'></textarea> </div></td>" +
								"<td bgcolor='#00CC33' onclick='AsignarCita("+ValHo+")'>Click para asignar</td>"+
						"</tr>");
					}else{
						
						out.print("<tr><td>"+Cant+"</td><td ><div align='center' style='font-size:20px' >"+rs3.getString(2)+"</div></td>");
								rs=mca.DatosdelaCita(ValHo);
								if(rs.next()){
									out.print("<td><div align='center' style='width:100%'>"+rs.getString(10)+"</div></td>" +
											"<td><div align='center' style='width:100%'>"+rs.getString(11)+"</div></td>");
								}
								rs.getStatement().getConnection().close();
								
								out.print("<td  style='font-size:16px'  bgcolor='#990000' ><font color='white'>"+rs3.getString(4)+"</font></td>"+
						"</tr>");
					}
					
				}
				out.print("<input name='txtFechaSel' type='hidden' id='txtFechaSel' value="+FechaSeleccionada+" >");
				out.print("<input name='txtCodUsuMed' type='hidden' id='txtCodUsuMed' value="+CodUsuMed+" >");
				out.print("<input name='txtCodEspMed' type='hidden' id='txtCodEspMed' value="+CodEspMed+" >");
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("CargarMes")){
			
			String Cod_Medico=req.getParameter("CodMedico");
			int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;int mes10;
			String NombreMes="";
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			String parametro_anio=req.getParameter("anio");
			String parametro_mes=req.getParameter("mes");
			anio1=Integer.parseInt(parametro_anio);
			//anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=Integer.parseInt(parametro_mes);
			//mes1=hoy1.get(java.util.Calendar.MONTH)+1;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			Calendar currentDate;
			currentDate = Calendar.getInstance();
			mes10=mes1-1;
			currentDate.set(anio1,mes10,dia1);	
			out.print(" ");
			int DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			//mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
	
			nDias1=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");
			out.print("<th align=center>M");out.print("<th align=center>M");
			out.print("<th align=center>J");out.print("<th align=center>V");
			out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			int a1=0;
			for(e=1;e<=nDias1;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	
				
				String Fecha1=anio1+"-"+mes1+"-"+e;
				try {
					rs3=mca.CitasDisponibleMedico(Cod_Medico,Fecha1);
					if(rs3.next()){
						a1=a1+1;out.print("<td bgcolor='#00CC33' align=center>");
					}else{
						a1=a1+1;out.print("<td align=center>");
					}
					rs3.getStatement().getConnection().close();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
				out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			String Fecha=anio1+"-"+mes1+"-"+e;
			out.print("<input name='chkHorarioMedico' type='Radio' id='chkHorarioMedico' onclick='CargarCitasMedico(&quot;"+Fecha+"&quot;)' value="+anio1+"-"+mes1+"-"+e+" />");
			if(e==dia1) //Si el día a escribir es el día de hoy
			{ out.print("</b></font>"); //Cerramos la etiqueta de edición de las propiedades de las fuentes
			}
			columna1++; //Pasamos a la columna siguiente.
			if (columna1==7) //el numero de columnas que contiene el mes.
			{	out.print("<tr>"); //Abrimos una nueva fila
			columna1=0; //Reseteamos la variables de columnas.
			}
			}			
			out.print("</table>"); //Cerramos la tabla.	
			
		}
		
		if(va.equals("CsC")){			
			try {
				String CodCCosto=req.getParameter("CodCCosto");
				rs1=mac.CargarSubCentrosCosto(CodCCosto);
				out.print("<select id='cmbSubCentCost' style='width:150px' ><option value='Seleccione' selected=''>Seleccione</option>");
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(3)+"</option>");
				}
				out.print("</select>");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("CCC")){			
			try {
				String CodSuc=req.getParameter("CodSuc");
				rs1=mac.CargarCentrosCosto(CodSuc);
				out.print("<select id='cmbCentCost' onchange='CargarSubcentroCosto()'><option value='Seleccione' selected=''>Seleccione</option>");
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				out.print("</select>");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("BPDX")){			
			String Programa=req.getParameter("Programa");
			String ManualTarifario=req.getParameter("ManualTarifario");
			try {				
				int conp=0;
				String CodPro="";
				String CodRefPro="";
				String NomPro="";
				String ValorPro="";
				rs=mca.ProgramaCex(Programa,ManualTarifario);
				out.print("<table>");
				while(rs.next()){
					conp=conp+1;
					CodPro=rs.getString(1);
					CodRefPro=rs.getString(2);
					NomPro=rs.getString(3);
					ValorPro=rs.getString(4);
					out.print("<tr><td><a href='#' onclick='AsignarProgCex("+rs.getString(1)+",&quot;"+rs.getString(2)+"&quot;,&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(4)+"&quot;)'>"+rs.getString(2)+"-"+rs.getString(3)+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorP' type='hidden' id='txtContadorP'   value="+conp+" /></td>" +
						"<td><input name='txtCodPro'    type='hidden' id='txtCodProP' value="+CodPro+" /></td>" +
						"<td><input name='txtCodRefPro' type='hidden' id='txtCodRefPro' value="+CodRefPro+" /></td>" +
						"<td><input name='txtNomPro' type='hidden' id='txtNomPro' value='"+NomPro+"' /></td>" +
						"<td><input name='txtValorPro' type='hidden' id='txtValorProP' value='"+ValorPro+"' /></td>" +
								"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("BDX")){			
			String Diagnostico=req.getParameter("Diagnostico");			
			try {				
				int cong=0;
				String CodDx="";
				String Cie10="";
				String NomDx="";
				rs=mca.DiagnosticosCex(Diagnostico);
				out.print("<table>");
				while(rs.next()){
					cong=cong+1;
					CodDx=rs.getString(1);
					NomDx=rs.getString(2);
					Cie10=rs.getString(3);
					out.print("<tr><td><a href='#' onclick='AsignarDxCex("+rs.getString(1)+",&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(2)+"&quot;)'>"+rs.getString(2)+"-"+rs.getString(3)+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorACG' type='hidden' id='txtContadorACG'   value="+cong+" /></td>" +
						"<td><input name='txtCodDxA'    type='hidden' id='txtCodDxA' value="+CodDx+" /></td>" +
						"<td><input name='txtCie10' type='hidden' id='txtCie10' value="+Cie10+" /></td>" +
						"<td><input name='txtNomDx' type='hidden' id='txtNomDx' value='"+NomDx+"' /></td>" +
								"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("AOCEX")){
			//se actualiza la orden ya ingresada con anterioridad
			try {
				String CodProg=req.getParameter("CodProg");
				String CodCsC=req.getParameter("CodCsC");
				String NumAutori=req.getParameter("NumAutori");
				String Copago=req.getParameter("Copago");
				String CodSucur=req.getParameter("CodSucur");
				String Moderacion=req.getParameter("Moderacion");
				String ValorPro=req.getParameter("ValorPro");
				String CodHorMed=req.getParameter("CodHorMed");
				String CodDx=req.getParameter("CodDx");
				String CodCuenta_fk="";
				rs1=mp.CodigoCuentaPrograma(CodProg);
				if(rs1.next()){
					CodCuenta_fk=rs1.getString(5);
				}else{
					CodCuenta_fk="0";
				}
				rs1.getStatement().getConnection().close();
			
				mac.ActualizarMovimientoAgrupado(CodProg, CodCuenta_fk, CodSucur, CodCsC, NumAutori, Copago, Moderacion, ValorPro, CodHorMed,CodDx);
				out.print("1");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("IOCEX")){
			try{
				String CodProg=req.getParameter("CodProg");String CodCsC=req.getParameter("CodCsC");
				String NitEnti=req.getParameter("NitEnti");String NumAutori=req.getParameter("NumAutori");
				String CodEnt=req.getParameter("CodEnt");String Copago=req.getParameter("Copago");
				String CodSucur=req.getParameter("CodSucur");String Moderacion=req.getParameter("Moderacion");
				String CodUsu=req.getParameter("CodUsu");String ValorPro=req.getParameter("ValorPro");
				String CodHorMed=req.getParameter("CodHorMed");String CodDx=req.getParameter("CodDx");
				java.util.Date FechaAc1 = new java.util.Date();
				java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
				java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
				
				String CodCuenta_fk="";String CodTercero_fk="";
				rs1=mp.CodigoCuentaPrograma(CodProg);
				if(rs1.next()){
					CodCuenta_fk=rs1.getString(5);
				}else{
					CodCuenta_fk="0";
				}
				rs1.getStatement().getConnection().close();
				//se busca el codigo del tercero
			
				rs1=mp.CodigoTercero(NitEnti);
				if(rs1.next()){
					CodTercero_fk=rs1.getString(1);
				}else{
					CodTercero_fk="0";
				}
				rs1.getStatement().getConnection().close();
				
				// se ingresa en la tabla cont_movimientos_agrupados
				mac.IngresarMovimientoAgrupado(CodProg, CodCuenta_fk, CodEnt, CodTercero_fk, CodSucur, CodCsC, Fecha_Insercion1+"", NumAutori, Copago, Moderacion, Fecha_Insercion1+"", Hora_Insercion1+"", CodUsu, ValorPro, CodHorMed,CodDx);
				out.print("1");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(va.equals("R1")){
			//icn-hospital niño jesus
			try {
				rs6=mca.VerificarInasistencias();
				while(rs6.next()){
					  // String CodCita="";
					  // CodCita=rs6.getString(1);
					   mca.ActualizarCitasInasistencia(rs6.getString(1));
				}
				rs6.getStatement().getConnection().close();
				rs=mac.BuscarPaciente(TipoDocumento, NumeroDocumento);
				if(rs.next()){
					/********************************************/
					rs5=mca.RelacionCitasActivas(rs.getString(1));
					out.print("<table width='100%' border='1'>");
					//out.print("<tr style='font-size:medium; color:#0033FF; '><td width='26%'><div align='center'><strong>Medico</strong></div></td><td width='20%'><div align='center'><strong>Especialidad</strong></div></td><td width='15%'><div align='center'><strong>Fecha</strong></div></td><td width='8%'><div align='center'><strong>Estado</strong></div></td></tr>");
					while(rs5.next()){
						String Estado="";
						Estado=rs5.getString(5);
						if(Estado.equals("1")){
							//Estado=Asignado
							out.print("<tr style='font-size:15PX; ><td align='center'>EL PACIENTE TIENE UNA <strong><font color='navy'><a href='#' onclick='ReporteRecordatorioCita("+rs5.getString("codigo")+")'>CITA ACTIVA</a></font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
						if(Estado.equals("2")){
							//Estado= en espera de atencion
							out.print("<tr style='font-size:15PX; ><td align='center'><strong><font color='orange'><a href='#' onclick='ReporteRecordatorioCita("+rs5.getString("codigo")+")'>EL PACIENTE ESTA EN ESPERA DE ATENCION </a></font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
						
						if(Estado.equals("3")){
							//Estado= atencion finalizada
							out.print("<tr style='font-size:15PX; ><td align='center'><strong><font color='green'><a href='#' onclick='ReporteRecordatorioCita("+rs5.getString("codigo")+")'>ATENCION FINALIZADA </a></font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
						if(Estado.equals("4")){
							//Estado= inasistente
							out.print("<tr style='font-size:15PX; ><td align='center'><strong><font color='red'><a href='#' onclick='ReporteRecordatorioCita("+rs5.getString("codigo")+")'>EL PACIENTE TIENE NO ASISTIO A LA CITA</a> </font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
					}
					out.print("</table>");
					rs5.getStatement().getConnection().close();
					/********************************************/
					/***se llena apara que se le asigne la cita***/
					String NombreComple=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
					out.print("<table width='100%' border='0'><tr><td colspan='8' align='center' id='cabecera2' class='style11'>DATOS DEL PACIENTE</td></tr><tr class='style12'><td colspan='2'>Nombre Usuario:</td><td colspan='2'>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td><td colspan='2'>Fecha de Nacimiento</td><td colspan='2'>"+rs.getString(7)+"</td></tr>");
					out.print("<tr class='style12'><td colspan='2'>Genero</td><td colspan='2'>"+rs.getString(6)+"</td><td colspan='2'>Entidad</td><td colspan='2'><input name='CodEnt' type='hidden' id='CodEnt' value="+rs.getString(10)+" /><input name='NitEnti' type='hidden' id='NitEnti' value='"+rs.getString(11)+"' />"+rs.getString(5)+"-"+rs.getString(8)+"</td></tr>");
					out.print("<tr class='style12' ><td><input name='CodPac' type='hidden' id='CodPac' value="+rs.getString(1)+" /><input name='CodManualTarifario' type='hidden' id='CodManualTarifario' value="+rs.getString(9)+" /><input name='txtNombreCompleto' type='hidden' id='txtNombreCompleto' value='"+NombreComple+"'/></td></tr>");
					out.print("<tr><td colspan='8' align='center' id='cabecera2' class='style11'>DATOS DE LA CITA</td></tr>");
					String Gene=rs.getString(6);
					String Edad=rs.getString("edad");
					out.print("<tr class='style12'><td>Nº Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
					out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'>CONSULTA, NO ESPECIFICADA.</div></td><td></td><td><input name='txtCodPro'    type='hidden' id='txtCodPro' /><input name='txtValorPro' type='hidden' id='txtValorPro'  /><div id='CodProCex'>autocompleta programa</div></td><td></td><td></td></tr>");
					out.print("<tr class='style12'><td>Sucursal</td>");					
					out.print("<td><select id='cmbSucur' onchange='CentrosCostos()' ><option value='Seleccione' selected=''>Seleccione</option>");
					rs1=mac.CargarSucursales();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td>");
					
					out.print("<td>Centro de costo</td>");
					
					out.print("<td id='CenCost'><select><option value='Seleccione' selected=''>Seleccione</option></select></td>");
					
					out.print("<td>Subcentro de costo</td>");
					
					out.print("<td id='CenSubCost'><select id='cmbSubCentCost'><option value='Seleccione' selected=''>Seleccione</option></select></td>");
					out.print("<td id='CenSubCost'><input type='hidden' id='cmbSubCentCost' value='1'></td>");
					
					out.print("<td></td><td></td></tr>");
					
					out.print("</table>");
					java.util.Calendar hoy = java.util.Calendar.getInstance();
					int anio=hoy.get(java.util.Calendar.YEAR);
					
					
					out.print("<table width='100%' border='0'><tr class='style12'>");
					
					out.print("<td>Especialidad</td><td><select id='cmbEspecialidad' onchange='BuscarMedicos()'><option value='Seleccione'>Seleccione</option>");
					//rs1=mac.BuscarEspecialidadTodas();
					//while(rs1.next()){
					String Condicion="";
					if(Integer.parseInt(Edad)>=18){
						//no mostrar las pediatricas tanto en especialidades como en medicos.
						if(Gene.equals("Masculino")){
							//mostrar todas las especialidades y medicos menos las ginecologicas.
							Condicion="AND nombre_especialidad NOT LIKE '%PEDIATRI%' AND nombre_especialidad NOT LIKE '%GINECO%' ORDER BY nombre_especialidad ";
							rs1=mac.BuscarEspecialidadTodas(Condicion);
							while(rs1.next()){
								out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						}
							
						if(Gene.equals("Femenino")){
							//mostrar todas las especialidades y medicos menos la urologia.
							Condicion="AND nombre_especialidad NOT LIKE '%PEDIATRI%'  ORDER BY nombre_especialidad ";
							rs1=mac.BuscarEspecialidadTodas(Condicion);
							while(rs1.next()){
								out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						}
					}else{
						// son menores de 18 años mostrar todas las especialidades
							//no mostrar las pediatricas tanto en especialidades como en medicos.
							if(Gene.equals("Masculino")){
								//mostrar todas las especialidades y medicos menos las ginecologicas.
								Condicion=" AND nombre_especialidad NOT LIKE '%GINECO%' ORDER BY nombre_especialidad ";
								rs1=mac.BuscarEspecialidadTodas(Condicion);
								while(rs1.next()){
									out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
								}
								rs1.getStatement().getConnection().close();
							}
							if(Gene.equals("Femenino")){
								//mostrar todas las especialidades y medicos menos la urologia.
								Condicion="  ORDER BY nombre_especialidad";
								rs1=mac.BuscarEspecialidadTodas(Condicion);
								while(rs1.next()){
									out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
								}
								rs1.getStatement().getConnection().close();
							}
					}
					
					//}
					//rs1.getStatement().getConnection().close();					
					out.print("</select></td>");
					out.print("<td>Medico</td><td><div id='medico'><select name='cmbMedico' id='cmbMedico' onchange='MostrasCitasDisponiblesMedico()'><option value='Seleccione' selected='selected'>Seleccione</option>");
					//rs1=mac.CargarTodosMedicos();
				/********************************************************************/
				//String Condicion="";
				if(Integer.parseInt(Edad)>=18){
					//no mostrar las pediatricas tanto en especialidades como en medicos.
					if(Gene.equals("Masculino")){
						//mostrar todas las especialidades y medicos menos las ginecologicas.
						Condicion="AND ps.nombre_especialidad NOT LIKE '%PEDIATRI%' AND ps.nombre_especialidad NOT LIKE '%GINECO%' ORDER BY pm.nombre ";
						rs1=mac.CargarTodosMedicos(Condicion);
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+" title='"+rs1.getString(3)+"' >"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					}
						
					if(Gene.equals("Femenino")){
						//mostrar todas las especialidades y medicos menos la urologia.
						Condicion="AND ps.nombre_especialidad NOT LIKE '%PEDIATRI%' AND ps.nombre_especialidad NOT LIKE '%UROLOG%' ORDER BY pm.nombre ";
						rs1=mac.CargarTodosMedicos(Condicion);
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+" title='"+rs1.getString(3)+"' >"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					}
				}else{
					// son menores de 18 años mostrar todas las especialidades
						//no mostrar las pediatricas tanto en especialidades como en medicos.
						if(Gene.equals("Masculino")){
							//mostrar todas las especialidades y medicos menos las ginecologicas.
							Condicion=" AND ps.nombre_especialidad NOT LIKE '%GINECO%' ORDER BY pm.nombre ";
							rs1=mac.CargarTodosMedicos(Condicion);
							while(rs1.next()){
								out.print("<option value="+rs1.getString(1)+" title='"+rs1.getString(3)+"' >"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						}
						if(Gene.equals("Femenino")){
							//mostrar todas las especialidades y medicos menos la urologia.
							Condicion=" AND ps.nombre_especialidad NOT LIKE '%UROLOG%' ORDER BY pm.nombre ";
							rs1=mac.CargarTodosMedicos(Condicion);
							while(rs1.next()){
								out.print("<option value="+rs1.getString(1)+" title='"+rs1.getString(3)+"' >"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						}
				}
				/********************************************************************/
					//while(rs1.next()){
						//out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					//}
					//rs1.getStatement().getConnection().close();
					out.print("</select>");
					out.print("</div></td>");
					
					out.print("<td>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4' value='"+anio+"'></td> " +
							"<td>Mes<select name='cmbmesIni' id='cmbmesIni' onchange='CargarCalendario()'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select></td>");
					
					out.print("<td><div id='CalenMes'>Calendario</div></td></tr>");
					
					out.print("<tr><td colspan='7' id='DetallesCita'></td>");
					
					out.print("</tr></table>");
					
					
					
					/*out.print("<td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>" +
							"Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>" +
							"A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4' value='"+anio+"'></label></td>");
					
					
					out.print("<td>Medico <select id='cmbEspecialista' onchange='VerHorasDisponibles()'><option value='Seleccione' selected='' >Seleccione</option>");
					rs1=mac.EspecialistaUsuario();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td>");
					
					out.print("<td><label><div id='CamHoraMed'>Hora<select name='cmbhora' id='cmbhora'><option value='Seleccione' selected='selected'>Seleccione</option></select></div></label> </td>");
					*/
					
					
					//out.print("");				
					//out.print("<td><input name='btnIng' type='button' value='Ingresar' onClick='IngresarProximaCita()'></label></td></tr></table>");
					
					
					
				}else{
					/***se muestra PARA LLENAR LOS DATOS DEMOGRAFICOS***/
		
					
					out.print("<table width='100%' class='style6'><tr><td colspan='4' id='cabecera2'><div align='center' class='style11'> DATOS DEMOGRAFICOS </div></td></tr>");

					out.print("<tr><td width='18%'><span class='Estilo8'>Primer Apellido</span></td><td width='26%'><label><input name='txtpapellido' type='text' id='txtpapellido' onkeyup='this.value=this.value.toUpperCase();'  maxlength='20'  /><span class='style8'>*</span></label></td><td width='16%'><span class='Estilo8'>Segundo Apellido</span></td><td width='40'><label><input name='txtsapellido' type='text' id='txtsapellido' onkeyup='this.value=this.value.toUpperCase();'   maxlength='20'  /></label></td></tr>");

					out.print("<tr><td><span class='Estilo8'>Nombres</span></td><td><label><input name='txtnombre' type='text' id='txtnombre' onkeyup='this.value=this.value.toUpperCase();'  /><span class='style8'>*</span></label>Estado Civil <select id='cmbEstadoCivil'><option value='Seleccione' selected='' >Seleccione</option><option value='Soltero(a)'>Soltero(a)</option><option value='Casado(a)'>Casado(a)</option><option value='Viudo(a)'>Viudo(a)</option></select></td><td><span class='Estilo8'>Fecha Nacimiento(ddmmaaaa)</span></td><td><label><input type='text' name='txtfechanaci' id='txtfechanaci'   onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'/><span class='style8'>*</span></label></td></tr>");
					
					out.print("<tr class='Estilo8'><td>Tipo Afiliacion</td><td><select id='cmbTipoAfiliacion'><option value='Seleccione' selected='' >Seleccione</option><option value='Beneficiario'>Beneficiario</option><option value='Cotizante'>Cotizante</option><option value='Particular'>Particular</option></select><span class='style8'>*</span></td><td>Nivel Cotizante</td><td><select id='cmbNivelCotizante'><option value='Seleccione' selected='' >Seleccione</option><option value='I'>I</option><option value='II'>II</option><option value='III'>III</option></select><span class='style8'>*</span></td></tr>");
					
					out.print("<tr><td><span class='Estilo8'>Departamento</span></td><td><select name='cbdep' size='1' id='cbdep' onChange='CargarMunicipio()'><option selected='selected'>Seleccione</option>");
					rs2=mac.Departamentos();
					while(rs2.next()){
						out.print("<option value="+rs2.getString(1)+" >"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
					out.print("</select><span class='style8'>*</span></td><td><span class='Estilo8'>Municipio</span></td><td><label><div id='Municipio'><select name='cbmun' size='1' id='cbmun' ><option value='Seleccione' selected='selected'>Seleccione</option></select></div><span class='style5'>*</span></label></td></tr>");
					 
					out.print("<tr><td><span class='Estilo8'>Genero</span></td><td><label><select name='select4' id='cmbGenero' ><option selected='selected' value='Seleccione'>Seleccione</option><option value='Masculino' >Masculino</option><option value='Femenino' >Femenino</option></select><span class='style8'>*</span></label></td><td>E-mail</td><td><span class='style8'><input type='text' name='txtEmail' id='txtEmail' />*</span></td></tr>");
					  
					out.print("<tr><td><span class='Estilo8'>Telefono </span></td><td><input name='txtcelular' type='text' id='txtcelular'   maxlength='20'/><input name='txtTelFijo' type='text' id='txtTelFijo'   maxlength='20'/><input name='txtTelOfi' type='text' id='txtTelOfi'   maxlength='20'/></td><td><span class='Estilo8'>Direccion</span></td><td><label><input name='txtdire' type='text' id='txtdire' maxlength='50'/></label></td></tr>");
					  
					out.print("<tr><td><span class='Estilo8'>Nombre Entidad </span></td><td colspan='3'><label></label><label><input name='cbeps' type='text' id='cbeps' size='80' maxlength='300' onkeyup='autocompleta_nombre()' /><span class='style8'>*</span></label></td></tr>");
					  
					out.print("<tr><td>&nbsp;</td><td colspan='3'><div id='sugerencias'></div></td></tr>");
					  
					out.print("<tr><td colspan='2' class='style8'>Campos Requeridos* </td><td>&nbsp;</td> <td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><label><input name='txtcodmun' type='hidden' id='txtcodmun' /><input name='txtcodentidad' type='hidden' id='txtcodentidad'  /> </label></td><td><label><input type='button' name='btingresar' class='boton4' id='btingresar' value='     Ingresar    ' onclick='GuardarDemograficoCER()' /></label></td></tr></table>");
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("R1-GENERAL")){
			//citas galapa
			try {
				rs6=mca.VerificarInasistencias();
				while(rs6.next()){
					  // String CodCita="";
					  // CodCita=rs6.getString(1);
					   mca.ActualizarCitasInasistencia(rs6.getString(1));
				}
				rs6.getStatement().getConnection().close();
				rs=mac.BuscarPaciente(TipoDocumento, NumeroDocumento);
				if(rs.next()){
					/********************************************/
					rs5=mca.RelacionCitasActivas(rs.getString(1));
					out.print("<table width='100%' border='1'>");
					//out.print("<tr style='font-size:medium; color:#0033FF; '><td width='26%'><div align='center'><strong>Medico</strong></div></td><td width='20%'><div align='center'><strong>Especialidad</strong></div></td><td width='15%'><div align='center'><strong>Fecha</strong></div></td><td width='8%'><div align='center'><strong>Estado</strong></div></td></tr>");
					while(rs5.next()){
						String Estado="";
						Estado=rs5.getString(5);
						if(Estado.equals("1")){
							//Estado=Asignado
							out.print("<tr style='font-size:15PX; ><td align='center'>EL PACIENTE TIENE UNA <strong><font color='navy'>CITA ACTIVA</font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
						if(Estado.equals("2")){
							//Estado= en espera de atencion
							out.print("<tr style='font-size:15PX; ><td align='center'><strong><font color='orange'>EL PACIENTE ESTA EN ESPERA DE ATENCION </font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
						
						if(Estado.equals("3")){
							//Estado= atencion finalizada
							out.print("<tr style='font-size:15PX; ><td align='center'><strong><font color='green'>ATENCION FINALIZADA </font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
						if(Estado.equals("4")){
							//Estado= inasistente
							out.print("<tr style='font-size:15PX; ><td align='center'><strong><font color='red'>EL PACIENTE TIENE NO ASISTIO A LA CITA </font></strong> CON EL MEDICO "+rs5.getString(2)+"-"+rs5.getString(3)+" PARA EL DIA "+rs5.getString(4)+"</td></tr>");
						}
					}
					out.print("</table>");
					rs5.getStatement().getConnection().close();
					/********************************************/
					/***se llena apara que se le asigne la cita***/
					String NombreComple=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
					out.print("<table width='100%' border='0'><tr><td colspan='8' align='center' id='cabecera2' class='style11'>DATOS DEL PACIENTE</td></tr><tr class='style12'><td colspan='2'>Nombre Usuario:</td><td colspan='2'>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td><td colspan='2'>Fecha de Nacimiento</td><td colspan='2'>"+rs.getString(7)+"</td></tr>");
					out.print("<tr class='style12'><td colspan='2'>Genero</td><td colspan='2'>"+rs.getString(6)+"</td><td colspan='2'>Entidad</td><td colspan='2'><input name='CodEnt' type='hidden' id='CodEnt' value="+rs.getString(10)+" /><input name='NitEnti' type='hidden' id='NitEnti' value='"+rs.getString(11)+"' />"+rs.getString(5)+"-"+rs.getString(8)+"</td></tr>");
					out.print("<tr class='style12' ><td><input name='CodPac' type='hidden' id='CodPac' value="+rs.getString(1)+" /><input name='CodManualTarifario' type='hidden' id='CodManualTarifario' value="+rs.getString(9)+" /><input name='txtNombreCompleto' type='hidden' id='txtNombreCompleto' value='"+NombreComple+"'/></td></tr>");
					out.print("<tr><td colspan='8' align='center' id='cabecera2' class='style11'>DATOS DE LA CITA</td></tr>");
					
					//out.print("<tr class='style12'><td>Nº Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
					out.print("<tr class='style12'><td></td><td><input name='txtNumAutor' type='hidden' id='txtNumAutor' value='-' /></td><td></td><td><input name='txtDiagCEX' type='hidden' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td></td><td><input name='txtCodProgCEX' type='hidden' id='txtCodProgCEX' onkeyup='BuscarProgCex()' value='0' /></td><td><input name='txtCopagoCex' type='hidden' id='txtCopagoCex' value='0' /></td><td> <input name='txtModeracionCex' type='hidden' id='txtModeracionCex' value='0' /></td></tr>");
					//out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'>CONSULTA, NO ESPECIFICADA.</div></td><td></td><td><input name='txtCodPro'    type='hidden' id='txtCodPro' /><input name='txtValorPro' type='hidden' id='txtValorPro'  /><div id='CodProCex'>autocompleta programa</div></td><td></td><td></td></tr>");
					out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'></div></td><td></td><td><input name='txtCodPro'  type='hidden' id='txtCodPro' value='0' /><input name='txtValorPro' type='hidden' id='txtValorPro' value='0'  /><div id='CodProCex'></div></td><td></td><td></td></tr>");
					//out.print("<tr class='style12'><td>Sucursal</td>");					
					out.print("<tr class='style12'><td></td>");
					//out.print("<td><select id='cmbSucur' onchange='CentrosCostos()' ><option value='Seleccione' selected=''>Seleccione</option>");
					out.print("<td><input type='hidden' id='cmbSucur' value='1' >");
					rs1=mac.CargarSucursales();
					while(rs1.next()){
						//out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					//out.print("</select></td>");
					
					//out.print("<td>Centro de costo</td>");
					
					//out.print("<td id='CenCost'><select><option value='Seleccione' selected=''>Seleccione</option></select></td>");
					
					//out.print("<td>Subcentro de costo</td>");
					
					//out.print("<td id='CenSubCost'><select id='cmbSubCentCost'><option value='Seleccione' selected=''>Seleccione</option></select></td>");
					out.print("<td id='CenSubCost'><input type='hidden' id='cmbSubCentCost' value='1'></td>");
					
					out.print("<td></td><td></td></tr>");
					
					out.print("</table>");
					java.util.Calendar hoy = java.util.Calendar.getInstance();
					int anio=hoy.get(java.util.Calendar.YEAR);
					
					
					out.print("<table width='100%' border='0'><tr class='style12'>");
					
					out.print("<td>Especialidad</td><td><select id='cmbEspecialidad' onchange='BuscarMedicos()'><option value='Seleccione'>Seleccione</option>");
					rs1=mac.BuscarEspecialidadTodas("");
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();					
					out.print("</select></td>");
					out.print("<td>Medico</td><td><div id='medico'><select name='cmbMedico' id='cmbMedico'><option value='Seleccione' selected='selected'>Seleccione</option>");
					rs1=mac.CargarTodosMedicos("");
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select>");
					out.print("</div></td>");
					
					out.print("<td>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4' value='"+anio+"'></td> " +
							"<td>Mes<select name='cmbmesIni' id='cmbmesIni' onchange='CargarCalendario()'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select></td>");
					
					out.print("<td><div id='CalenMes'>Calendario</div></td></tr>");
					
					out.print("<tr><td colspan='7' id='DetallesCita'></td>");
					
					out.print("</tr></table>");
					
					
					
					/*out.print("<td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>" +
							"Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>" +
							"A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4' value='"+anio+"'></label></td>");
					
					
					out.print("<td>Medico <select id='cmbEspecialista' onchange='VerHorasDisponibles()'><option value='Seleccione' selected='' >Seleccione</option>");
					rs1=mac.EspecialistaUsuario();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td>");
					
					out.print("<td><label><div id='CamHoraMed'>Hora<select name='cmbhora' id='cmbhora'><option value='Seleccione' selected='selected'>Seleccione</option></select></div></label> </td>");
					*/
					
					
					//out.print("");				
					//out.print("<td><input name='btnIng' type='button' value='Ingresar' onClick='IngresarProximaCita()'></label></td></tr></table>");
					
					
					
				}else{
					/***se muestra PARA LLENAR LOS DATOS DEMOGRAFICOS***/
		
					
					out.print("<table width='100%' class='style6'><tr><td colspan='4' id='cabecera2'><div align='center' class='style11'> DATOS DEMOGRAFICOS </div></td></tr>");

					out.print("<tr><td width='18%'><span class='Estilo8'>Primer Apellido</span></td><td width='26%'><label><input name='txtpapellido' type='text' id='txtpapellido' onkeyup='this.value=this.value.toUpperCase();'  maxlength='20'  /><span class='style8'>*</span></label></td><td width='16%'><span class='Estilo8'>Segundo Apellido</span></td><td width='40'><label><input name='txtsapellido' type='text' id='txtsapellido' onkeyup='this.value=this.value.toUpperCase();'   maxlength='20'  /></label></td></tr>");

					out.print("<tr><td><span class='Estilo8'>Nombres</span></td><td><label><input name='txtnombre' type='text' id='txtnombre' onkeyup='this.value=this.value.toUpperCase();'  /><span class='style8'>*</span></label>Estado Civil <select id='cmbEstadoCivil'><option value='Seleccione' selected='' >Seleccione</option><option value='Soltero(a)'>Soltero(a)</option><option value='Casado(a)'>Casado(a)</option><option value='Viudo(a)'>Viudo(a)</option></select></td><td><span class='Estilo8'>Fecha Nacimiento(ddmmaaaa)</span></td><td><label><input type='text' name='txtfechanaci' id='txtfechanaci'   onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'/><span class='style8'>*</span></label></td></tr>");
					
					out.print("<tr class='Estilo8'><td>Tipo Afiliacion</td><td><select id='cmbTipoAfiliacion'><option value='Seleccione' selected='' >Seleccione</option><option value='Beneficiario'>Beneficiario</option><option value='Cotizante'>Cotizante</option><option value='Particular'>Particular</option></select></td><td>Nivel Cotizante</td><td><select id='cmbNivelCotizante'><option value='Seleccione' selected='' >Seleccione</option><option value='I'>I</option><option value='II'>II</option><option value='III'>III</option></select></td></tr>");
					
					out.print("<tr><td><span class='Estilo8'>Departamento</span></td><td><select name='cbdep' size='1' id='cbdep' onChange='CargarMunicipio()'><option selected='selected'>Seleccione</option>");
					rs2=mac.Departamentos();
					while(rs2.next()){
						out.print("<option value="+rs2.getString(1)+" >"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
					out.print("</select><span class='style8'>*</span></td><td><span class='Estilo8'>Municipio</span></td><td><label><div id='Municipio'><select name='cbmun' size='1' id='cbmun' ><option value='Seleccione' selected='selected'>Seleccione</option></select></div><span class='style5'>*</span></label></td></tr>");
					 
					out.print("<tr><td><span class='Estilo8'>Genero</span></td><td><label><select name='select4' id='cmbGenero' ><option selected='selected' value='Seleccione'>Seleccione</option><option value='Masculino' >Masculino</option><option value='Femenino' >Femenino</option></select><span class='style8'>*</span></label></td><td>E-mail</td><td><span class='style8'><input type='text' name='txtEmail' id='txtEmail' />*</span></td></tr>");
					  
					out.print("<tr><td><span class='Estilo8'>Telefono </span></td><td><input name='txtcelular' type='text' id='txtcelular'   maxlength='20'/><input name='txtTelFijo' type='text' id='txtTelFijo'   maxlength='20'/><input name='txtTelOfi' type='text' id='txtTelOfi'   maxlength='20'/></td><td><span class='Estilo8'>Direccion</span></td><td><label><input name='txtdire' type='text' id='txtdire' maxlength='50'/></label></td></tr>");
					  
					out.print("<tr><td><span class='Estilo8'>Nombre Entidad </span></td><td colspan='3'><label></label><label><input name='cbeps' type='text' id='cbeps' size='80' maxlength='300' onkeyup='autocompleta_nombre()' /><span class='style8'>*</span></label></td></tr>");
					  
					out.print("<tr><td>&nbsp;</td><td colspan='3'><div id='sugerencias'></div></td></tr>");
					  
					out.print("<tr><td colspan='2' class='style8'>Campos Requeridos* </td><td>&nbsp;</td> <td>&nbsp;</td></tr><tr><td>&nbsp;</td><td><label><input name='txtcodmun' type='hidden' id='txtcodmun' /><input name='txtcodentidad' type='hidden' id='txtcodentidad'  /> </label></td><td><label><input type='button' name='btingresar' class='boton4' id='btingresar' value='     Ingresar    ' onclick='GuardarDemograficoCER()' /></label></td></tr></table>");
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
if(va.equals("ddr")){
			
			String papellido=req.getParameter("papellido");
			String sapellido=req.getParameter("sapellido");
			String nombre=req.getParameter("nombre");
			String fechanaci=req.getParameter("fechanaci");
			String CodMuni=req.getParameter("CodMuni");
			String Genero=req.getParameter("Genero");
			String Telefono=req.getParameter("Telefono");
			String Entidad=req.getParameter("Entidad");
			String NumDocumento1=req.getParameter("NumDocumento");
			String TipoDoc1=req.getParameter("TipoDoc");
			String Direccion=req.getParameter("Direccion");
			String Email=req.getParameter("Email");
			
			String Telefono_Fijo=req.getParameter("Telefono_Fijo");
			String Telefono_Oficina=req.getParameter("Telefono_Oficina");
			String EstadoCivil=req.getParameter("EstadoCivil");
			String TipoAfiliacion=req.getParameter("TipoAfiliacion");
			String NivelCotizante=req.getParameter("NivelCotizante");
			
			try {
				rs=pac.SQLCodConv(Entidad);
				while(rs.next()){
					Entidad=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				System.out.println("Error en ControlIngresoPac Nombre ResultSet=rs :: "+e);
				e.printStackTrace();
			}
				
				//pac.insertar(tip, ced, afiliacion, nivel, pape, sape, nom, gene, nacionali, dir, tel, telofi, telcel, codOcu, emp, zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, conve);
				pac.insertar(TipoDoc1, NumDocumento1, TipoAfiliacion, NivelCotizante, papellido, sapellido, nombre,
						Genero, "COLOMBIA", Direccion, Telefono_Fijo, Telefono_Oficina, Telefono, "-", "-", "Urbana", "-", EstadoCivil, "-",
						"0", Email, CodMuni, fechanaci, Entidad,"-","","","");

			
		}
		

		
		if(va.equals("ProCitR")){
			String FechaPcita=req.getParameter("FechaPcita");
			String HoraPcita=req.getParameter("HoraPcita");
			String CodUsu=req.getParameter("CodUsu");
			String CodPacC=req.getParameter("CodPac");
			String NombreCompleto=req.getParameter("NombreCompleto");
			String estado="1";
			String CodigoMedico1="";
			String CodigoEspecialidad1="";
			try {
				rs1=mp.BuscarDatosMedico(CodUsu);
				if(rs1.next()){
					CodigoMedico1=rs1.getString(2);
					CodigoEspecialidad1=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();				
				rs=mp.BuscarFechaHora(HoraPcita, FechaPcita,CodigoMedico1);
				if(rs.next()){
					out.print("La Fecha y Hora de la Cita que esta Tratando de Asignar ya se Encuentra Ocupada.\nRelacionado de la Siguiente forma Nombre Paciente: "+rs.getString(6)+" Fecha y Hora de Cita: "+rs.getString(3)+"/"+rs.getString(2));
				}else{
					// es igual a cero.
					//mmd.CrearHorarioMedico1(HoraPcita, FechaPcita, estado, CodigoMedico1,NombreCompleto);
					rs2=mp.BuscarFechaHora2(HoraPcita, FechaPcita,CodigoMedico1);
					if(rs2.next()){
						mac.ActualizarRegistroHorarioMedico(rs2.getString(1), NombreCompleto);
						java.util.Date FechaAc1 = new java.util.Date();
						java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
						java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
						mac.AsignarCita(CodigoEspecialidad1, rs2.getString(1), CodigoMedico1, CodPacC, CodUsu, FechaPcita,Fecha_Insercion1+"",Hora_Insercion1+"","Via AsignarR","Via AsignarR");
					    mac.ActualizarHorasFechasMedicoIgual(HoraPcita, FechaPcita, CodigoMedico1);
					}
					rs2.getStatement().getConnection().close();
					
					out.print("1");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("mun")){
			String CodDep=req.getParameter("CodDep");
			rs=mac.Municipios(CodDep);
			try {
				out.print("<div id='Municipio'><select name='cbmun' size='1' id='cbmun' ><option selected='selected'>Seleccione</option>");
				while(rs.next()){
					out.print("<option value="+rs.getString(4)+">"+rs.getString(3)+"</option>");
				}
				out.print("</select></div>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("BR1")){
			String TipoRec=req.getParameter("TipoRec");
			String NumRecibo=req.getParameter("NumRecibo");
			int abonos=0;
			int ValorPen=0;
			/****** TipoRec=1 por ingreso ****/
			if(TipoRec.equals("1")){				
				try {
					rs=mac.ObtenerReciboIngreso(NumRecibo);
					if(rs.next()){
						out.print("<table border='0' width='100%' ><tr><td colspan='6'><div id='cabecera2' class='style11' align='center'>RECIBO DE CAJA </div><input type='hidden' name='txtCodReciboCaja' id='txtCodReciboCaja' value="+rs.getString(2)+" /><input type='hidden' name='txtCodPac' id='txtCodPac'  /><input type='hidden' name='txtCodCita' id='txtCodCita' value="+rs.getString(13)+" /></td></tr>");
						out.print("<tr><td width='12%' class='style12'>Ciudad y Fecha </td><td width='41%'>"+rs.getString(7)+"</td><input type=hidden id='txtFechaLetra' value='"+rs.getString(7)+"' /><td class='style12' colspan='2'>Valor Servicio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtTotalPagar' id='txtTotalPagar' readonly='' value="+rs1.getString(3)+" /></td><td class='style12'>Valor a Recibir $<input name='txtAbono' type='text' id='txtAbono'  onkeyup='Letras()' /></td></tr>");
						out.print("<tr><td width='12%' class='style12'>Recibido</td><td width='41%'>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td><td class='style12' colspan='2' >Cuota Moderadora:&nbsp;&nbsp;$<input type='text' name='txtCuotaModeradora' id='txtCuotaModeradora' /></td></tr>");
						out.print("<tr><td width='12%' class='style12'>Empresa</td><td width='41%'>"+rs.getString(5)+"</td><td class='style12' colspan='2' >Valor Orden:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtValorOrden' id='txtValorOrden' onkeyup='Letras1()' /></td><td class='style12'>Numero Orden:$<input type='text' name='txtNumOrden' id='txtNumOrden' /></td></tr>");
						out.print("<tr><td class='style12'>La Suma De: </td><td colspan='2'><div id='ValorLetras'><input type=hidden id='txtValorLetra' /></div></td><td></td></tr>");
						out.print("<tr><td class='style12'>Por Concepto De: </td><td>");
						out.print("<select name='cmbConcepto' id='cmbConcepto' onchange='VerOtro()'><option value='"+rs1.getString(5)+"'>"+rs1.getString(5)+"</option>" );
						rs3=mac.TipoConcepto();
						while(rs3.next()){
							out.print("<option value='"+rs3.getString(1)+"' >"+rs3.getString(2)+"</option>");
						}
						rs3.getStatement().getConnection().close();
						out.print("</select>");
						out.print("</td><td colspan='3' class='style12'><div id='Otros'><input type=hidden id='txtOtro' /></div></td></tr>");
						out.print("<tr><td colspan='6' class='style12' bgcolor='#999999'>&nbsp;</td></tr>");
						String Tot=formatMoneda(rs1.getString(3));
						out.print("<tr><td class='style12'>Valor a Recibir </td><td>"+Tot+"</td><input type=hidden id='txtValoraRecibir' value='"+Tot+"' /><input type=hidden id='txtValoraRecibirPleno' value="+rs1.getString(3)+" /><td class='style12'>Tipo Pago </td><td colspan='2' class='style12'><label><input name='radiobutton' type='radio' value='radiobutton' id='Cheque' onclick='DatoTiPa()' >Cheque</label><label><input name='radiobutton' type='radio' value='radiobutton' id='Efectivo' onclick='DatoTiPa()'  >Efectivo</label><label><input name='radiobutton' type='radio' value='radiobutton' id='Consignacion' onclick='DatoTiPa()' >Consignacion</label></td></tr>");
						rs4=mac.AbonosReciboCaja(rs.getString(2));
						while(rs4.next()){
							abonos=(abonos+rs4.getInt(3))+ (rs4.getInt(15) + rs4.getInt(16)+ rs4.getInt(18));
						}
						rs4.getStatement().getConnection().close();						
						String abo=String.valueOf(abonos);						
						int TotPagar=rs1.getInt(3);						
						ValorPen=TotPagar-abonos;
						String ValPen=String.valueOf(ValorPen);						
						out.print("<tr><td class='style12'>Valor Recibido </td><td>"+formatMoneda(abo)+"</td><input type=hidden id='txtValorRecibido' value='"+formatMoneda(abo)+"' /><input type=hidden id='txtValorRecibidoPleno' value='"+abo+"' /><td class='style12'>&nbsp;</td><td colspan='2' class='style12'><div id='TipoPago'><input type=hidden id='txtNumCons' /></div></td></tr>");
						out.print("<tr><td class='style12'>Valor Pendiente </td><td>"+formatMoneda(ValPen)+"</td><input type=hidden id='txtValorPendiente' value='"+formatMoneda(ValPen)+"' /><input type=hidden id='txtValorPendientePleno' value='"+ValPen+"' /><td class='style12'>&nbsp;</td><td colspan='2' class='style12'>&nbsp;</td></tr>");
						out.print("<tr><td colspan='5' class='style12' align='center'>&nbsp;</td></tr>");
						out.print("<tr><td colspan='5' class='style12' align='center'><input name='btnGuardarRc' type='button' class='btnStyle' id='btnGuardarRc' value='Guardar' onclick='GuardarReciboCaja()' ><input name='btnLimpiarRc' type='button' class='btnStyle' id='btnLimpiarRc' value='Limpiar' onclick='NuevoRc()'></td></tr></table>");

						
					}
					else{
						out.print("No se encontro recibo de ingreso con ese consecutivo. Intente otra vez");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			/****** TipoRec=2 por egreso ****/
			if(TipoRec.equals("2")){				
				try {
					rs=mac.ObtenerReciboEgreso(NumRecibo);
					if(rs.next()){
						out.print("<table border='0' width='100%' ><tr><td colspan='11' class='style12'><div id='cabecera2' class='style11' align='center'><p>RELACION DE EGRESOS</p></div></td></tr>");
						out.print("<tr><td class='style12'>Fecha</td><td colspan='7'>"+rs.getString(8)+"<input type=hidden id='txtFechaLetra' value='"+rs.getString(8)+"' /></td><td width='5%' class='style12'>Cantidad</td><td width='13%' class='style12'><input name='txtAbono' type='text' id='txtAbono' value="+rs.getString(3)+"><input name='txtValorLetra' type='hidden' id='txtValorLetra' value='"+rs.getString(10)+"'/></td><td><div id='ValorLetras'></div></td></tr>");
						out.print("<tr><td class='style12'>Nombre</td><td width='25%'><input name='txtNombre' type='text' id='txtNombre' size='45' value='"+rs.getString(11)+"'></td><td width='6%' class='style12'>&nbsp;</td><td width='7%' class='style12'>&nbsp;</td>");
						out.print("<td width='8%' class='style12'>C.C-NIT</td><td width='5%' class='style12'><input name='txtIdentificacion' type='text' id='txtIdentificacion' size='25' value='"+rs.getString(12)+"' ></td><td width='8%' class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td>");
						out.print("<td class='style12'>&nbsp;</td><td>&nbsp;</td></tr><tr><td width='6%' class='style12'>Concepto</td><td colspan='6'><input name='txtConcepto' type='text' id='txtConcepto' size='70' value='"+rs.getString(2)+"'></td><td colspan='3' class='style12'>Forma de Pago &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
						out.print("<label><input name='txtTipoPago' type='text' id='txtTipoPago'  value='"+rs.getString(9)+"'></td><td width='4%'><div id='TipoPago'><input type=hidden id='txtNumCons' /></div></td></tr>");
						out.print("<tr><td class='style12'>Observacion</td><td><textarea name=' txtObservacion' cols='40' rows='3' id='txtObservacion'>"+rs.getString(4)+"</textarea></td><td colspan='5' class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td><td class='style12'>&nbsp;</td><td>&nbsp;</td></tr>");
						//out.print("<tr><td colspan='11'><div id='RelacionEgresos'></div></td></tr>");
						out.print("<tr><td colspan='11' align='center'><input name='btnGuardar' type='button' id='btnGuardar' onClick='' value='Eliminar'><input name='btnActualizar' type='button' id='btnActualizar' onClick='' value='Actualizar'></td></tr></table>");
					}
					else{
						out.print("No se encontro recibo de egreso con ese consecutivo. Intente otra vez");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		if(va.equals("10")){	
			try {
				out.print("<table width='100%' border='0'><tr><td colspan='6'><div align='center' id='cabecera2' class='style11' ><span>Permitir Cita</span></div></td></tr>");
				out.print("<tr><td width='7%'>Medico</td><td width='24%'><select name='cmbMedico' id='cmbMedico'><option value='Seleccione' selected='selected'>Seleccione</option>");
				rs1=mmd.BuscarMedicoTodos();
				while(rs1.next()){
					String nombre=rs1.getString(2);
					String apellidos=rs1.getString(3);
					String Completo=nombre+" "+apellidos;
					out.println("<option value="+rs1.getString(1)+">"+Completo+"</option>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</select></td><td width='10%'>Especialidad</td><td width='29%'><select name='cmbEspecialista' id='cmbEspecialista'><option value='Seleccione' selected='selected'>Seleccione</option>");
				String Vo="";
				rs2=mac.BuscarEspecialidadTodas(Vo);
				while(rs2.next()){
					out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
				}
				rs2.getStatement().getConnection().close();				
				out.print("</select></td><td>Documento<input name='txtNumeroDoc' type='text' id='txtNumeroDoc' /></td><td width='30%'><input name='btnBuscar' type='button' id='btnBuscar' value='Buscar' onclick=' BuscarCitas() ' /></td></tr>");
				out.print("<tr><td colspan='6'><div id='Resultado'></div></td></tr></table>");
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}					
		}
		if(va.equals("VCP")){ 
			//icn
			try {
				String CodHorMed=req.getParameter("CodHorMed");
				rs=mac.VerificarAutorizacion(CodHorMed);
				if(rs.next()){
					out.print("<table width='100%' border='0' > <tr class='style12'><td>Nº Autorizacion</td>" +
							"<td><input name='txtNumAutor' type='text' id='txtNumAutor' value="+rs.getString(11)+" /></td>" +
									"<td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value="+rs.getString("codigoCIE")+" />" +
									"</td><td>Cod Programa</td>" +
									"<td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' value="+rs.getString(21)+" /></td>" +
											"<td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value="+rs.getString(12)+" /></td>" +
													"<td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex'  value="+rs.getString(13)+"  /></td></tr>");
					out.print("<tr class='style12'><td></td><td></td><td></td>" +
							"<td><input id='CodManualTarifario' type='hidden' value="+rs.getString("cod_manual_tarifario")+">" +
									"<input id='txtCodDx' type='hidden' value="+rs.getString("CodDx")+"><div id='DxCex'>"+rs.getString("Nombredx")+"</div></td>" +
									"<td></td><td><input name='txtCodPro' type='hidden' id='txtCodPro' value="+rs.getString(4)+" />" +
											"<input name='txtValorPro' type='hidden' id='txtValorPro' value="+rs.getString(18)+"  />" +
													"<div id='CodProCex'>"+rs.getString(22)+"</div></td><td></td><td></td></tr>");
					out.print("<tr class='style12'><td>Sucursal</td>");					
					out.print("<td><select id='cmbSucur' onchange='CentrosCostos()' ><option value="+rs.getString(8)+" selected=''>"+rs.getString(29)+"</option>");
					rs1=mac.CargarSucursales();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td>");
				
					out.print("<td>Centro de costo</td>");
			
					out.print("<td id='CenCost'><select><option value="+rs.getString(30)+" selected=''>"+rs.getString(31)+"</option></select></td>");
			
					out.print("<td>Subcentro de costo</td>");
			
					out.print("<td id='CenSubCost'><select id='cmbSubCentCost'><option value="+rs.getString(9)+" selected=''>"+rs.getString(32)+"</option></select></td>");
			
					out.print("<td></td><td></td></tr>");
					
					out.print("<tr><td></td><td></td><td></td><td></td><td><input id='btnActualizarOrden' name='btnActualizarOrden' type='button' value='Actualizar Orden' onClick='ActualizarOrden("+CodHorMed+")' ></td><td><input id='btnPasarListaMedico' name='btnPasarListaMedico' type='button' value='Pasar Lista Medico' onClick='ActivarCitaMedico("+CodHorMed+")' ></td><td></td></tr></table>");
				}else{
					String CodManualTarifario="";String NitEnti="";String CodEnt="";
					rs1=mac.BuscarManualTarifarioCEX(CodHorMed);
					if(rs1.next()){
						CodManualTarifario=rs1.getString(1);
						CodEnt=rs1.getString(5);
						NitEnti=rs1.getString(2);
					}
					rs1.getStatement().getConnection().close();
					
					out.print("<table width='100%' border='0' ><tr class='style12'><td>Nº Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
					
					out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='NitEnti' type='hidden' value="+NitEnti+"><input id='CodEnt' type='hidden' value="+CodEnt+"><input id='CodManualTarifario' type='hidden' value="+CodManualTarifario+"><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'>CONSULTA, NO ESPECIFICADA.</div></td><td></td><td><input name='txtCodPro'    type='hidden' id='txtCodPro' /><input name='txtValorPro' type='hidden' id='txtValorPro'  /><div id='CodProCex'>autocompleta programa</div></td><td></td><td></td></tr>");
					out.print("<tr class='style12'><td>Sucursal</td>");					
					out.print("<td><select id='cmbSucur' onchange='CentrosCostos()' ><option value='Seleccione' selected=''>Seleccione</option>");
					rs1=mac.CargarSucursales();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td>");
				
					out.print("<td>Centro de costo</td>");
			
					out.print("<td id='CenCost'><select><option value='Seleccione' selected=''>Seleccione</option></select></td>");
			
					out.print("<td>Subcentro de costo</td>");
			
					out.print("<td id='CenSubCost'><select id='cmbSubCentCost'><option value='Seleccione' selected=''>Seleccione</option></select></td>");
			
					out.print("<td></td><td></td></tr>");
					
					out.print("<tr><td></td><td></td><td></td><td></td><td><input id='btnIngresarOrden' name='btnIngresarOrden' type='button' value='Ingresar Orden' onClick='IngresarOrden("+CodHorMed+")' ></td><td><input id='btnPasarListaMedico' name='btnPasarListaMedico'  disabled='true' type='button' value='Pasar Lista Medico' onClick='ActivarCitaMedico("+CodHorMed+")' ></td><td></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("VCP-GENERAL")){
			//galapa
			
			try {
				String CodHorMed=req.getParameter("CodHorMed");
				rs=mac.VerificarAutorizacion(CodHorMed);
				if(rs.next()){
					//out.print("<table width='100%' border='0' > <tr class='style12'><td>Nº Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' value="+rs.getString(11)+" /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' value="+rs.getString(20)+" /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value="+rs.getString(12)+" /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex'  value="+rs.getString(13)+"  /></td></tr>");
					out.print("<table width='100%' border='0' > <tr class='style12'><td></td><td><input name='txtNumAutor' type='hidden' id='txtNumAutor' value="+rs.getString(11)+" /></td><td></td><td><input name='txtDiagCEX' type='hidden' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td></td><td><input name='txtCodProgCEX' type='hidden' id='txtCodProgCEX' onkeyup='BuscarProgCex()' value="+rs.getString(20)+" /></td><td><input name='txtCopagoCex' type='hidden' id='txtCopagoCex' value="+rs.getString(12)+" /></td><td> <input name='txtModeracionCex' type='hidden' id='txtModeracionCex'  value="+rs.getString(13)+"  /></td></tr>");
					//out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='CodManualTarifario' type='hidden' value="+rs.getString(26)+"><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'>CONSULTA, NO ESPECIFICADA.</div></td><td></td><td><input name='txtCodPro' type='hidden' id='txtCodPro' value="+rs.getString(4)+" /><input name='txtValorPro' type='hidden' id='txtValorPro' value="+rs.getString(18)+"  /><div id='CodProCex'>"+rs.getString(21)+"</div></td><td></td><td></td></tr>");
					out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='CodManualTarifario' type='hidden' value="+rs.getString(26)+"><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'></div></td><td></td><td><input name='txtCodPro' type='hidden' id='txtCodPro' value="+rs.getString(4)+" /><input name='txtValorPro' type='hidden' id='txtValorPro' value="+rs.getString(18)+"  /><div id='CodProCex'></div></td><td></td><td></td></tr>");
					//out.print("<tr class='style12'><td>Sucursal</td>");					
					out.print("<tr class='style12'><td></td>");
					//out.print("<td><select id='cmbSucur' onchange='CentrosCostos()' ><option value="+rs.getString(8)+" selected=''>"+rs.getString(28)+"</option>");
					out.print("<td><input type='hidden' id='cmbSucur' value='1' >");
					rs1=mac.CargarSucursales();
					while(rs1.next()){
						//out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					//out.print("</select></td>");
				
					//out.print("<td>Centro de costo</td>");
			
					//out.print("<td id='CenCost'><select><option value="+rs.getString(29)+" selected=''>"+rs.getString(30)+"</option></select></td>");
			
					//out.print("<td>Subcentro de costo</td>");
			
					//out.print("<td id='CenSubCost'><select id='cmbSubCentCost'><option value="+rs.getString(9)+" selected=''>"+rs.getString(31)+"</option></select></td>");
					out.print("<td id='CenSubCost'><input type='hidden' id='cmbSubCentCost' value='1'></td>");
			
					out.print("<td></td><td></td></tr>");
					
					//out.print("<tr><td></td><td></td><td></td><td></td><td><input id='btnActualizarOrden' name='btnActualizarOrden' type='button' value='Actualizar Orden' onClick='ActualizarOrden("+CodHorMed+")' ></td><td><input id='btnPasarListaMedico' name='btnPasarListaMedico' type='button' value='Pasar Lista Medico' onClick='ActivarCitaMedico("+CodHorMed+")' ></td><td></td></tr></table>");
					out.print("<tr><td></td><td></td><td></td><td></td><td></td><td><input id='btnPasarListaMedico' name='btnPasarListaMedico' type='button' value='Pasar Lista Medico' onClick='ActivarCitaMedico("+CodHorMed+")' ></td><td></td></tr></table>");
				}else{
					String CodManualTarifario="";String NitEnti="";String CodEnt="";
					rs1=mac.BuscarManualTarifarioCEX(CodHorMed);
					if(rs1.next()){
						CodManualTarifario=rs1.getString(1);
						CodEnt=rs1.getString(5);
						NitEnti=rs1.getString(2);
					}
					rs1.getStatement().getConnection().close();
					
					//out.print("<table width='100%' border='0' ><tr class='style12'><td>Nº Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
					out.print("<table width='100%' border='0' ><tr class='style12'><td></td><td><input name='txtNumAutor' type='hidden' id='txtNumAutor' value='-' /></td><td></td><td><input name='txtDiagCEX' type='hidden' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td></td><td><input name='txtCodProgCEX' type='hidden' id='txtCodProgCEX' onkeyup='BuscarProgCex()' value='0' /></td><td><input name='txtCopagoCex' type='hidden' id='txtCopagoCex' value='0' /></td><td><input name='txtModeracionCex' type='hidden' id='txtModeracionCex' value='0' /></td></tr>");
					
					//out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='NitEnti' type='hidden' value="+NitEnti+"><input id='CodEnt' type='hidden' value="+CodEnt+"><input id='CodManualTarifario' type='hidden' value="+CodManualTarifario+"><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'>CONSULTA, NO ESPECIFICADA.</div></td><td></td><td><input name='txtCodPro'    type='hidden' id='txtCodPro' /><input name='txtValorPro' type='hidden' id='txtValorPro'  /><div id='CodProCex'>autocompleta programa</div></td><td></td><td></td></tr>");
					out.print("<tr class='style12'><td></td><td></td><td></td><td><input id='NitEnti' type='hidden' value="+NitEnti+"><input id='CodEnt' type='hidden' value="+CodEnt+"><input id='CodManualTarifario' type='hidden' value="+CodManualTarifario+"><input id='txtCodDx' type='hidden' value='13964'><div id='DxCex'></div></td><td></td><td><input name='txtCodPro'  type='hidden' id='txtCodPro' value='0' /><input name='txtValorPro' type='hidden' id='txtValorPro' value='0' /><div id='CodProCex'></div></td><td></td><td></td></tr>");
					//out.print("<tr class='style12'><td>Sucursal</td>");					
					out.print("<tr class='style12'><td></td>");
					//out.print("<td><select id='cmbSucur' onchange='CentrosCostos()' ><option value='Seleccione' selected=''>Seleccione</option>");
					out.print("<td><input type='hidden' id='cmbSucur' value='1'  >");
					rs1=mac.CargarSucursales();
					while(rs1.next()){
						//out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					//out.print("</select></td>");
				
					//out.print("<td>Centro de costo</td>");
			
					//out.print("<td id='CenCost'><select><option value='Seleccione' selected=''>Seleccione</option></select></td>");
			
					//out.print("<td>Subcentro de costo</td>");
			
					//out.print("<td id='CenSubCost'><select id='cmbSubCentCost'><option value='Seleccione' selected=''>Seleccione</option></select></td>");
					out.print("<td id='CenSubCost'><input type='hidden' id='cmbSubCentCost' value='1' ></td>");
			
					out.print("<td></td><td></td></tr>");
					
					//out.print("<tr><td></td><td></td><td></td><td></td><td><input id='btnIngresarOrden' name='btnIngresarOrden' type='button' value='Ingresar Orden' onClick='IngresarOrden("+CodHorMed+")' ></td><td><input id='btnPasarListaMedico' name='btnPasarListaMedico'  disabled='true' type='button' value='Pasar Lista Medico' onClick='ActivarCitaMedico("+CodHorMed+")' ></td><td></td></tr></table>");
					out.print("<tr><td></td><td></td><td></td><td></td><td></td><td><input id='btnPasarListaMedico' name='btnPasarListaMedico' type='button' value='Pasar Lista Medico' onClick='IngresarOrden("+CodHorMed+")' ></td><td></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("Mer0")){
			//medico
			try {
			String fechaIni=req.getParameter("fechaIni");
			String fechaFin=req.getParameter("fechaFin");
			String Medico=req.getParameter("Medico");
			String Entidad=req.getParameter("Entidad");
			rs=mac.CargarEmpresa();
			if(rs.next()){
				out.print("<table width='100%' border='1' cellspacing='0'><tr align='center' ><td>"+rs.getString(2)+"</td></tr></table>");
			}
			rs.getStatement().getConnection().close();
			
			int co=0;
			rs=mac.RipsMedico(fechaIni, fechaFin, Medico);
				out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%'>N°</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
				while(rs.next()){
					co=co+1;
					out.print("<tr><td>"+co+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td>");
					
					rs1=mac.BuscarDiagnosticoCex(rs.getString(2));
					if(rs1.next()){
						out.print("<td>"+rs1.getString(1)+"</td><td>-</td><td>-</td>");
					}else{
						out.print("<td>-</td><td>-</td><td>-</td>");
					}
					rs1.getStatement().getConnection().close();
					rs1=mac.BuscarFinalidadConsultaCex(rs.getString(2));
					if(rs1.next()){
						out.print("<td>"+rs1.getString(9)+"</td><td>"+rs1.getString(10)+"</td>");
					}else{
					out.print("<td>-</td><td>-</td>");
					}
					rs1.getStatement().getConnection().close();
					
					out.print("<td>"+rs.getString(9)+"</td></tr>");
					
				}
				out.print("<tr><td  colspan='4>Reporte de rango: "+fechaIni+" hasta "+fechaFin+"</td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("Mer1")){
			//entidad
			try{
			String fechaIni=req.getParameter("fechaIni");
			String fechaFin=req.getParameter("fechaFin");
			String Medico=req.getParameter("Medico");
			String Entidad=req.getParameter("Entidad");
			int co=0;
			rs=mac.CargarEmpresa();
			if(rs.next()){
				out.print("<table width='100%' border='1' cellspacing='0'><tr align='center' ><td>"+rs.getString(2)+"</td></tr></table>");
			}
			rs.getStatement().getConnection().close();
			
			rs=mac.RipsEntidad(fechaIni, fechaFin, Entidad);
			out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%'>N°</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
			while(rs.next()){
				co=co+1;
				out.print("<tr><td>"+co+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td>");
				
				rs1=mac.BuscarDiagnosticoCex(rs.getString(2));
				if(rs1.next()){
					out.print("<td>"+rs1.getString(3)+"</td><td>-</td><td>-</td>");
				}else{
					out.print("<td>-</td><td>-</td><td>-</td>");
				}
				rs1.getStatement().getConnection().close();
				rs1=mac.BuscarFinalidadConsultaCex(rs.getString(2));
				if(rs1.next()){
					out.print("<td>"+rs1.getString(9)+"</td><td>"+rs1.getString(10)+"</td>");
				}else{
				out.print("<td>-</td><td>-</td>");
				}
				rs1.getStatement().getConnection().close();
				
				out.print("<td>"+rs.getString(9)+"</td></tr>");
				
			}
			out.print("<tr><td  colspan='4>Reporte de rango: "+fechaIni+" hasta "+fechaFin+"</td></tr></table>");

			rs.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		if(va.equals("Mer2")){
			//medico-entidad
			try{
			String fechaIni=req.getParameter("fechaIni");
			String fechaFin=req.getParameter("fechaFin");
			String Medico=req.getParameter("Medico");
			String Entidad=req.getParameter("Entidad");
			int co=0;
			rs=mac.CargarEmpresa();
			if(rs.next()){
				out.print("<table width='100%' border='1' cellspacing='0'><tr align='center' ><td>"+rs.getString(2)+"</td></tr></table>");
			}
			rs.getStatement().getConnection().close();
			
			rs=mac.RipsMedicoEntidad(fechaIni, fechaFin, Medico, Entidad);
			out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%'>N°</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
			while(rs.next()){
				co=co+1;
				out.print("<tr><td>"+co+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td>");
				
				rs1=mac.BuscarDiagnosticoCex(rs.getString(2));
				if(rs1.next()){
					out.print("<td>"+rs1.getString(3)+"</td><td>-</td><td>-</td>");
				}else{
					out.print("<td>-</td><td>-</td><td>-</td>");
				}
				rs1.getStatement().getConnection().close();
				rs1=mac.BuscarFinalidadConsultaCex(rs.getString(2));
				if(rs1.next()){
					out.print("<td>"+rs1.getString(9)+"</td><td>"+rs1.getString(10)+"</td>");
				}else{
				out.print("<td>-</td><td>-</td>");
				}
				rs1.getStatement().getConnection().close();
				
				out.print("<td>"+rs.getString(9)+"</td></tr>");
				
			}
			out.print("<tr><td  colspan='4>Reporte de rango: "+fechaIni+" hasta "+fechaFin+"</td></tr></table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
		if(va.equals("Mer3")){
			//solo medico
			try{
			String fechaIni=req.getParameter("fechaIni");
			String fechaFin=req.getParameter("fechaFin");
			String Medico=req.getParameter("Medico");
			int co=0;
			rs=mac.CargarEmpresa();
			if(rs.next()){
				out.print("<table width='100%' border='1' cellspacing='0'><tr align='center' ><td>"+rs.getString(2)+"</td></tr></table>");
			}
			rs.getStatement().getConnection().close();
			
			rs=mac.SoloRipsMedico(fechaIni, fechaFin, Medico);
			out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%' >N°</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
			while(rs.next()){
				co=co+1;
				out.print("<tr><td>"+co+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td>");
				out.print("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");			
				out.print("<td>&nbsp;</td><td>&nbsp;</td>");
				out.print("<td>"+rs.getString(6)+"</td></tr>");
				
			}
			out.print("<tr><td colspan='4'>Reporte de rango: "+fechaIni+" hasta "+fechaFin+"</td></tr></table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
		if(va.equals("raccex")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>Fecha Asignación</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr><td align='right'>Fecha Cita</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''></td></tr>");
			out.println("<tr><td align='right'>Usuario   </td><td><select id='cmbUsuarioCex'><option value='VerTodos'>Ver Todos</option>");
			rs1=mac.UsuariosCitasConsultaExterna();
			try {
				while(rs1.next()){
					out.println("<option value="+rs1.getString("usu_codigo")+">"+rs1.getString("NombreUsuario")+"</option>");
				}
				rs1.getStatement().getConnection().close();
				out.println("</select></td></tr><tr><td align='right'><input name='btnPasarListaMedico' type='button' value='Buscar' onClick='BuscarCitasCEX()' ></td></tr><table>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("Inc")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''></td></tr>");
			out.println("<tr ><td  align='right'>TIPO INDICADOR</td><td ><select name='cmbTipoConsulta' id='cmbTipoConsulta'><option value='' selected='selected'>Seleccione</option>");
			
			rs1=mac.CargarTipoConsulta();				
			try {
				while(rs1.next()){
					out.print("<option value='"+rs1.getString(2)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			out.println("</select></td></tr><tr><td >&nbsp;</td><td align=''></td></tr></table>");
			out.println("<table><tr><td  align='right'><input name='btnPasarListaMedico' type='button' value='Buscar' onClick='IndicadoresAtencion()' ></td></tr></table>");

		}
		
		if(va.equals("rat")){
			
			try {
				out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
				out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''></td></tr></table>");
				out.print("<table><tr><td>Tipo Reporte</td><td><select id='TipoReporte'><option value='Seleccione' selected='' >Seleccione</option><option value='Solo' >Solo Citas</option><option value='Diligenciado' >Diligenciado</option></select></td></tr><tr><td>Medico</td><td><div id='medico'><select name='cmbMedico' id='cmbMedico'><option value='Seleccione' selected='selected'>Seleccione</option>");
				rs1=mac.CargarTodosMedicos("");				
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				out.print("</select></td></tr></table>");
				rs1.getStatement().getConnection().close();
				
				out.print("<table><tr><td>Entidad</td><td><div id='medico'><select name='cmbEntidad' id='cmbEntidad'><option value='Seleccione' selected='selected'>Seleccione</option>");
				rs1=mac.CargarEntidades();				
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				out.print("</select></td></tr></table>");
				rs1.getStatement().getConnection().close();
				
				out.print("<table><tr><td><input name='btnPasarListaMedico' type='button' value='Buscar' onClick='BuscarRipsAtencion()' ></td></tr></table>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		if(va.equals("11")){
			
			/********************************************************************/
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date FechaServidor = new java.sql.Date(fechaActual.getTime());
			String Identificacion=req.getParameter("Identificacion");
			if(Tip.equals("E")){rs=mac.BuscarCitasPorEspecialidad(FechaServidor, CodEspecialista);}
			if(Tip.equals("M")){rs=mac.BuscarCitasPorMedico(FechaServidor, CodMedico);}
			if(Tip.equals("ME")){rs=mac.BuscarCitasPorMedicoEspecialidad(FechaServidor, CodMedico, CodEspecialista);}
			if(Tip.equals("ID")){rs=mac.BuscarCitasPorIdentificacion(FechaServidor, Identificacion);}
			int contador=0;
			String NombreMes = "";
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			mes=mes+1;			
			if(mes==1){NombreMes="Enero";}
			if(mes==2){NombreMes="Febrero";}
			if(mes==3){NombreMes="Marzo";}
			if(mes==4){NombreMes="Abril";}
			if(mes==5){NombreMes="Mayo";}
			if(mes==6){NombreMes="Junio";}
			if(mes==7){NombreMes="Julio";}
			if(mes==8){NombreMes="Agosto";}
			if(mes==9){NombreMes="Septiembre";}
			if(mes==10){NombreMes="Octubre";}
			if(mes==11){NombreMes="Noviembre";}
			if(mes==12){NombreMes="Diciembre";}
			try {
				out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11' >Resultado De La Busqueda </div></td></tr>");
				out.print("<tr><td colspan='5'><div align='center'>Citas Para El Dia:"+dia+" de "+NombreMes+" del "+anio+" </div></td></tr>");
				out.print("<tr><td width='11%'><div align='center'>Hora</div></td><td width='34%'><div align='center'>Nombre Paciente</div> </td><td width='23%'><div align='center'>Especialidad</div></td><td width='22%'><div align='center'>Medico</div></td><td width='10%'><div align='center'>Por Atender</div></td></tr>");
				while(rs.next()){
					contador=contador+1;
					if(rs.getString(9).equals("1")){						
					
					out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td><input name='radiobutton' type='radio' id='chkPorAtender' onclick='VerificarDatosCitaCEX("+rs.getString(5)+")' value="+rs.getString(5)+"></td></tr>");
					}
					
					if(rs.getString(9).equals("2")){						
						
						out.print("<tr bgcolor='navy'><td><strong><font color='white'>"+rs.getString(2)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(3)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(7)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(8)+"</font></strong></td><td><strong><font color='white'>EN ESPERA DE ATENCION</font></strong></td></tr>"); 
					}
					
					if(rs.getString(9).equals("3")){						
						
						out.print("<tr bgcolor='green' ><td><strong><font color='white'>"+rs.getString(2)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(3)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(7)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(8)+"</font></strong></td><td><font color='white'>ATENCION FINALIZADA</font></strong></td></tr>");
					}
					//out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td><input name='chkPorAtender' type='checkbox' id='chkPorAtender' value="+rs.getString(5)+"></td></tr>");
				}
				out.print("<tr><td colspan='5'><div id='DatosCitaCEX'></div></td></tr></table>");
				//out.print("<tr><td colspan='5'><div align='center'><input name='txtContador' type='hidden' id='txtContador' value="+contador+" readonly='' ><input name='btnPasarListaMedico' type='button' value='Pasar Lista Medico' onClick='ActivarCita()' ></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}			
		}
		if(va.equals("12")){
			
			try {				
				mac.ActualizarRegistroAsignacionHorarioMedico(CodHorarioMedico);
				String FechaRegistro="";
				String CodAdmision="";
				String num_autorizacion = "";String cod_programafk="";String cod_programa_num="";String nombre_programa="";
				String clase_servicio="";String valor="";String cod_usuario="";String subcentrodecosto="";
				String cod_medico="";String copago="";String moderacion="";String CodDx="";String codDiagnostico="";
				java.util.Date fechaActualDXA = new java.util.Date();
				//java.sql.Date fechaDXA = new java.sql.Date(fechaActualDXA.getTime());		
				java.sql.Time horaDXA = new java.sql.Time(fechaActualDXA.getTime());
				MetodoAdmision ma= new MetodoAdmision();		
				rs=ma.ConsultarAutorizacion(CodHorarioMedico);
				if(rs.next()){
					num_autorizacion=rs.getString("NumOrden");
					cod_programafk=rs.getString("CodPro_fk");					
					cod_programa_num=rs.getString("cod_referencia");
					nombre_programa=rs.getString("descripcion");
					clase_servicio=rs.getString("claseservicio");
					valor=rs.getString("valor_programa");
					cod_usuario=rs.getString("CodUsuario_fk");
					subcentrodecosto=rs.getString("subcentrocosto");
					cod_medico=rs.getString("usu_codigo");
					copago=rs.getString("valor_copago");
					moderacion=rs.getString("valor_moderacion");
					CodDx=rs.getString("CodDx");
					codDiagnostico=rs.getString("codigoCIE");
				}
				rs.getStatement().getConnection().close();
				
				rs=mac.ObtenerDatosAdmisionCE(CodHorarioMedico);
				String CodPac="";
				if(rs.next()){
					FechaRegistro=rs.getString(3);
					CodPac=rs.getString(1);
					ma.insertarAdmision(rs.getString(2),rs.getString(3),horaDXA,rs.getString(1),num_autorizacion);
					ma.InsertarContactoPaciente("1", CodPac);
				}
				rs.getStatement().getConnection().close();
				/*rs=mac.ObtenerDatosAdmisionCE(CodHorarioMedico);
				String CodPac="";
				if(rs.next()){
					FechaRegistro=rs.getString(3);
					CodPac=rs.getString(1);
					ma.insertarAdmision(rs.getString(2),rs.getString(3),horaDXA,rs.getString(1),num_autorizacion);
					ma.InsertarContactoPaciente("1", CodPac);
				}
				rs.getStatement().getConnection().close();*/
				rs=ma.obtenerCodigoAdmision(FechaRegistro, horaDXA+"",CodPac);
				if(rs.next()){
					CodAdmision=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				
				ResultSet rsc=null;
				String cod_eps = "", razon_social = "",
				 nit = "", direccion = "",
				telefono = "",ciudad = "",nombre_paciente = "",documentoP = "",
				direccion_p = "",telefono_p = "",
				tipo_afiliacion = "",estrato = "",fecha_ingreso = "",				
				poliza = null;
				String ViaIng="4";
				//String CodAdmCE="";
				
				
				mac.RelacionAdmisionCex(CodAdmision, CodHorarioMedico);
				rsc=ma.InsertarEncabezadoCex(CodAdmision);
				if(rsc.next()){
					cod_eps=rsc.getString(1);
	        		razon_social=rsc.getString(2);
	        		nit=rsc.getString(3); 
	        		direccion=rsc.getString(4);
					telefono=rsc.getString(5);
					ciudad=rsc.getString(6);
					nombre_paciente=rsc.getString(7);
					documentoP=rsc.getString(8);
					direccion_p=rsc.getString(9);
					telefono_p=rsc.getString(10);
					tipo_afiliacion=rsc.getString(11);
					estrato=rsc.getString(12);
					fecha_ingreso=rsc.getString(13);
					//num_autorizacion=rsc.getString(14);
					poliza=rsc.getString(15);
				}
					ma.InserEmcabeCXE(cod_eps, razon_social, nit, direccion, telefono, ciudad, nombre_paciente, documentoP, direccion_p, telefono_p, tipo_afiliacion, estrato, fecha_ingreso, CodAdmision, num_autorizacion,poliza,ViaIng,copago,moderacion);
					rsc.getStatement().getConnection().close();
					String CodEncFactura="";
					rs=ma.ObtenerCodigoEncabezado(CodAdmision);
					if(rs.next()){
						CodEncFactura=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
					
					mac.InsertarDxIngresoCex(codDiagnostico, cod_medico, horaDXA+"", fecha_ingreso, CodAdmision, CodPac, CodDx);
					//* insertar el diagnostico de ingreso en la tabla
					// obtener el codigo del emcabezado de la cita.
					
					//actualizar la tabla cont_movimientos_agrupados los campos de admision y encabezado.
					ma.ActualizarMovimientosAgrupados(CodEncFactura, CodAdmision, CodHorarioMedico);
					//se crea el detalle de la factura
					ma.CrearDetalleFactura(FechaRegistro, horaDXA+"", cod_programafk, cod_programa_num, nombre_programa, clase_servicio, FechaRegistro, "1", valor, cod_usuario, CodEncFactura, cod_medico, subcentrodecosto);
				
			} catch (SQLException e) {
				System.out.println("Error en rsc.InsertarEncabezado "+e);
				e.printStackTrace();
			}
			
		}
		if(va.equals("13")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());
			/*************************************************************/
			String NombreMes = "";
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			mes=mes+1;			
			if(mes==1){NombreMes="Enero";}
			if(mes==2){NombreMes="Febrero";}
			if(mes==3){NombreMes="Marzo";}
			if(mes==4){NombreMes="Abril";}
			if(mes==5){NombreMes="Mayo";}
			if(mes==6){NombreMes="Junio";}
			if(mes==7){NombreMes="Julio";}
			if(mes==8){NombreMes="Agosto";}
			if(mes==9){NombreMes="Septiembre";}
			if(mes==10){NombreMes="Octubre";}
			if(mes==11){NombreMes="Noviembre";}
			if(mes==12){NombreMes="Diciembre";}
			
			rs=mac.BuscarListaCitasMedico(CodUsuario, Fecha);
			try {
				out.print("<table width='100%' border='1'>");
				out.print("<tr><td colspan='4'><div align='center' id='cabecera2' class='style11' >Citas Para El Dia:"+dia+" de "+NombreMes+" del "+anio+" </div></td></tr>");
				out.print("<tr><td width='11%'><div align='center'>Hora Cita</div></td><td width='11%'><div align='center'>Hora Llegada</div></td><td><div align='center'>Nombre Paciente</div> </td><td width='10%'><div align='center'>Atender</div></td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString("hora_registro")+"</td><td>"+rs.getString(2)+"</td><td><a href='#' onclick='AtenderCita("+rs.getString(3)+","+rs.getString(5)+",&quot;"+rs.getString(6)+"&quot;,"+rs.getString(7)+")'>Atender</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("14")){
			String CodHorMed=req.getParameter("CodHorarioMedico");
			
			try {
				String CodAdm="";
				String CodEnc="";
				rs=mac.BuscarAdmEncabe(CodHorMed);
				if(rs.next()){
					CodAdm=rs.getString("CodAdm_fk");
					CodEnc=rs.getString("cod_enc_factura");
				}
				rs.getStatement().getConnection().close();
				rs=mac.BuscarDatosPacienteCE(CodigoPaciente);				
				if(rs.next()){
				out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA'bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Nombre y Apellido:</td><td width='40%' style='color:#215b8b'><div>"+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+"</div></td><td width='9%' style='color:#215b8b'>Identificacion</td><td width='13%' style='color:#215b8b'><div>"+rs.getString(6)+" "+rs.getString(7)+"</div></td><td width='12%' style='color:#215b8b'>Fecha Nacimiento</td><td width='13%' style='color:#215b8b'><div>"+rs.getString(5)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Edad:"+rs.getString(4)+"</div></td></tr></table>");
				out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'><input type='hidden' id='txtCodEntidad' value="+rs.getString("ent_nit")+">Entidad:</td><td width='49%' style='color:#215b8b'><div>"+rs.getString(8)+"</div></td><td width='13%' style='color:#215b8b'>Servicio</td><td width='25%' style='color:#215b8b'><div>Consulta Externa</div></td></tr></table>");
				out.print("<br>");			
				/**************menu de la izq*************/
				out.print("<table width='100%' border='1' cellspacing='0'><tr><td width='15%'><div id='MenuVertical'> <div id='button'><ul>" +
						"<li><a href='#' onclick='reload()'>Resumen Historia</a></li>" +
						"<li><a href='#' onclick='Antecedentes()'>Antecedentes</a></li>" +
						"<li><a href='#' onclick='FormatosCE()'>Formatos</a></li>"+
						//"<li><a href='#' onclick='InmunoterapiaCE()'>Inmunoterapia</a></li>"+
				        "<li><a href='#' onclick='MenuLaboratorio()'>Laboratorios</a></li>" +
						"<li><a href='#' onclick='OrdenServicioCE()'>Orden De Servicio</a></li>" +
						"<li><a href='#' onclick='VerFinalAtencionCE()'>Finalizar Atencion</a></li>"+
						"<li><a href='#' onclick='MostrarAtenciones()'>Atenciones Anteriores</a></li>" +
						"<li><a href='#' onclick='MostrarDQx("+CodAdm+","+CodEnc+")'>Descripcion QX</a></li></ul>"+
						"</div></div></td><td width='85%'><div id='HistoriaPaciente'>");
				//out.print("");
				/********************RESUMEN DE LA HISTORIA**************************************/
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
				out.print("<tr bgcolor='#DADADA' class='style12'>" +
						"<td width='25%'>MEDICAMENTOS</td>" +
						"<td width='25%'>QUIRURGICOS</td>" +
						"<td width='25%'>ALERGICOS</td>" +
						"<td width='25%'>IMAGENES DIAGNOSTICAS</td>" +
						"</tr>");
				out.print("<tr>");
				
				out.print("<td><div id='MedCex'>");
				rs1=mac.CargarMedicamentosCex(CodigoPaciente);
				out.print("<table width='100%'  border='1' cellspacing='0' >");
				while(rs1.next()){
					out.print("<tr><td>"+rs1.getString(1)+"-"+rs1.getString(2)+"</td></tr>");
				}
				out.print("</table>");
				rs1.getStatement().getConnection().close();
				//--se cargan los medicamentos 
				out.print("</div></td>");
				
				out.print("<td><div id='QxsCex'>");
				rs1=mac.CargarProcedimientosCex(CodigoPaciente);
				out.print("<table width='100%'  border='1' cellspacing='0' >");
				while(rs1.next()){
					out.print("<tr><td><label title='"+rs1.getString(3)+"' >"+rs1.getString(1)+"-"+rs1.getString(2)+"</label></td></tr>");
				}
				out.print("</table>");
				rs1.getStatement().getConnection().close();
				//--se cargan los antecedentes quirurgicos
				
				out.print("</div> </td>");
				out.print("<td><div id='AleCex'>");
				out.print("<table width='100%' border='1' cellspacing='0'>");
				int cal=0;
				rs=mac.VerificarAlergiasLimite(CodigoPaciente);
				while(rs.next()){
					cal=cal+1;
				out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
				}
				if(cal==0){
					out.print("<tr><td>NO REGISTRA ALERGIAS.</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				//--se cargan los ANTECEDENTES ALERGICOS 
				out.print("</div> </td>");		
				
				out.print("<td><div id='CatCex'>");
				rs1=mac.CargarImagenesCateterismos(CodigoPaciente);
				 out.print("<table width='100%' border='1' cellspacing='0'  style='font-size:10px' >");
				while(rs1.next()){					
					if(rs1.getString(5).equals("1")){
						//imagenologia
						out.print("<tr><td width='75%'><a href='#' onclick='mostarexamenesImagenes("+rs1.getString(1)+","+rs1.getString(4)+")' >"+rs1.getString(2)+"</a></td><td width='25%'>"+rs1.getString(3)+"</td></tr>");
					}					
					if(rs1.getString(5).equals("2")){
						//cateterismos
						out.print("<tr><td width='75%'><a href='#' onclick='mostrarInformesHemodinamia("+rs1.getString(1)+")' >"+rs1.getString(2)+"</a></td><td width='25%'>"+rs1.getString(3)+"</td></tr>");
					}					
				}
				out.print("</table>");
				rs1.getStatement().getConnection().close();
				//--se cargan los reportes de imagenologia Y cateterismos
				out.print("</div> </td>");
				
				out.print("</tr><tr bgcolor='#DADADA' class='style12'><td colspan='2'>ATENCIONES POR CONSULTA EXTERNA </td><td colspan='2'>HISTORIAL DE DIAGNOSTICOS </td></tr>");
				out.print("<tr>");
				out.print("<td colspan='2'><div id='ForCex'>");
				rs1=mac.CargarReportesCex(CodigoPaciente);
				out.print("<table width='100%'  border='1' cellspacing='0' >");
				while(rs1.next()){
					out.print("<tr><td><a href='#' onclick='ImprimirFormatoCExterna("+rs1.getString(6)+","+rs1.getString(5)+",&quot;"+rs1.getString(2)+"&quot;,&quot;"+rs1.getString(3)+"&quot;,"+CodigoPaciente+",0,"+rs1.getString(7)+")'>"+rs1.getString(1)+"</a></td><td>"+rs1.getString(2)+"/"+rs1.getString(3)+"</td></tr>");
				}
				out.print("</table>");
				rs1.getStatement().getConnection().close();
				//--SE CARGAN LOS FORMATOS DE CONSULTA EXTERNA
				out.print("</div> </td>");
				
				out.print("<td colspan='2'><div id='DxsCex'>");
				rs=mlh.CargarDiagnosticosActivos(CodigoPaciente);
				out.print("<table  width='100%' border='1' cellspacing='0'  ><tr></td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td></tr>");
				}
				out.print("</table>");
				//--SE CARGAN LOS DIAGNOSTICOS 				
				out.print("</div></td>");
				
				out.print("</tr></table>");
				out.print("");
				out.print("");
				out.print("");
				/**********************************FIN RESUMEN DE LA HISTORIA CEX**************************/
				//**poner el resumen de las atenciones anteriores en cex
			out.print("</div></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		String CodPac=req.getParameter("CodPac");
		/**************************INMUNOTERAPIA************************************/
		if(va.equals("inm")){
			out.print("<table border='0' width='100%' ><tr><td colspan='10'><div id='cabecera2' class='style11' align='center'>INMUNOTERAPIAS</div></td></tr>");
			
			out.print("<tr><td width='5%' class='style12'>Diluciòn</td><td width='10%'><select name='cmbDilucion' id='cmbDilucion'><option value='Seleccione' selected='selected'>Seleccione</option>" +
					" <option value='0.0001'>0.0001</option>" +
					" <option value='0.001'>0.001</option> " +
					" <option value='0.01'>0.01</option> " +
					" <option value='0.1'>0.1</option> " +
					" <option value='1'>1</option>" +
					" <option value='2'>2</option>" +
					" <option value='3'>3</option>" +
					" <option value='4'>4</option>" +
					" <option value='5'>5</option>" +
					" <option value='10'>10</option>" +
					"</select></td>");
			
			out.print("<td width='12%' class='style12'>Via de Administracion </td><td width='10%'><select name='cmbViaAdministracion' id='cmbViaAdministracion'>" +
					"<option value='Seleccione' selected='selected'>Seleccione</option>" +
					"<option value='Subcutanea'>Subcutanea</option>" +
					"<option value='Sublingual'>Sublingual</option>" +
					"</select></td>");
			
			out.print("<td width='10%' class='style12'>Volumen Inyectado </td><td width='14%'><input name='txtVolumenInyectado' type='text' id='txtVolumenInyectado'></td><td width='7%' class='style12'>Alergeno</td>");
			
			out.print("<td width='11%'><select name='cmbAlergeno' id='cmbAlergeno'><option value='Seleccione' selected='selected'>Seleccione</option>" +
					"<option value='Acaros'>Acaros</option>" +
					"<option value='Hongos'>Hongos</option>" +
					"<option value='Gato'>Gato</option>" +
					"<option value='Acaros-Hongos'>Acaros-Hongos</option>" +
					"</select></td>");
			
			out.print("<td width='4%' class='style12'>Via</td><td width='17%'><select name='cmbVia' id='cmbVia'><option value='Seleccione'>Seleccione</option>" +
					"<option value='Brazo Derecho'>Brazo Derecho</option>" +
					"<option value='Brazo Izquierdo'>Brazo Izquierdo</option>" +
					"<option value='Oral'>Oral</option>" +
					"</select></td></tr>");
			
			out.print("<tr><td class='style12'>Observacion</td><td colspan='4'><textarea rows='4' cols='50' id='txtObservacion'></textarea></td><td colspan='5' class='style12'><label><input name='radiobutton' type='radio' value='radiobutton' id='pr'> Primera Vez <input name='radiobutton' type='radio' value='radiobutton' id='co'>Control</label></td></tr>");
			
			out.print("<tr><td colspan='10' class='style12' align='center'><input name='btnIngresar' type='button' id='btnIngresar' value='Ingresar' onClick='IngresarInmuno()'></td></tr>");

			out.print("<tr><td colspan='10' class='style12'><div id='cabecera2' class='style11' align='center'>HISTORIAL DE INMUNOTERAPIAS</div></td></tr>");

			out.print("<tr><td colspan='10' class='style12'><div id='HistoInmuno'>");
		
			/*********CONSULTA HISTORICO INMUNO*******/
			out.print("<table width='100%' border='0'><tr class='style12'><td>Fecha y Hora</td><td>Dilucion</td><td>Volumen Inyectado</td><td>Via Administracion</td><td>Alergeno</td><td>Via</td><td>Observacion</td></tr>");
			rs=mac.HistoricoInmuniterapias(CodPac);
			try {
				while(rs.next()){
				out.print("<tr><td>"+rs.getString(9)+"/"+rs.getString(10)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print("</tr><tr><td colspan='7' align='center'><input name='btnImprimir' type='button' value='Imprimir' onClick='ImprimirHistoInmuno("+CodPac+")'></td></tr></table>");
			/*****FIN CONSULTA HISTORICO INMUNO*******/
			out.print("</div></td></tr>");
			out.print("</table>");

		}
		
		if(va.equals("IGINM")){
			String dilucion=req.getParameter("Dilucion");
			String via_administra=req.getParameter("ViaAdmi");
			String vol_inyectado=req.getParameter("VolInye");
			String alergeno=req.getParameter("Alergeno");
			String via=req.getParameter("Via");
			String observacion=req.getParameter("Observacion");
			String tipo_control=req.getParameter("TipoControl");
			String CodUsu=req.getParameter("Codusuario");
			java.util.Date fechaActualInm = new java.util.Date();
			java.sql.Date FechaInm = new java.sql.Date(fechaActualInm.getTime());		
			java.sql.Time HoraInm = new java.sql.Time(fechaActualInm.getTime());
			
			mac.InsertarInmuno(dilucion, via_administra, vol_inyectado, alergeno, via, observacion, tipo_control, FechaInm, HoraInm, CodUsu, CodHorarioMedico, CodPac);
		}
		/**********************FIN INMUNOTERAPIA************************************/
		
		/********************FORMULACION SOLA*****************************************/
		
		String TipoDoc=req.getParameter("TipoDoc");
		String NumDocumento=req.getParameter("NumDocumento");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date FechaF15 = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time HoraF15 = new java.sql.Time(fechaActual.getTime());
		
		if(va.equals("F15")){
		String CodPaciente="";
			try {
				rs1=mac.BuscarDatosPacienteCE(TipoDoc, NumDocumento);
				if(rs1.next()){
					CodPaciente=rs1.getString(9);
					out.print("<table width='100%' class='style6' border='1' cellspacing='0'>");
					out.print("<tr><td>Nombre Paciente</td><td>"+rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(3)+"</td><td>Edad</td><td>"+rs1.getString(4)+"</td><td>Entidad</td><td>"+rs1.getString(8)+"</td></tr>");
					out.print("<input type='hidden' id='CodPac' value="+CodPaciente+" />");
					out.print("<input type='hidden' id='CodHorarioMedico' value='0' />");
					out.print("<input type='hidden' id='txtHora' value='"+HoraF15+"' />");
					out.print("<input type='hidden' id='txtFecha' value='"+FechaF15+"' />");
					out.print("</table>");
										
				
				out.print("<table width='100%' class='style6' border='1' cellspacing='0'><tr>" +
						"<td width='310'><label><input name='radiobutton' type='radio' value='radiobutton' id='16' onclick='Radio2CE()' />Laboratorio</label></td>" +
						"<td width='100%' colspan='2'><label><input name='radiobutton' type='radio' value='radiobutton' id='77' onclick='Radio2CE()' />Formulacion </label></td>" +
						"</tr><tr><td colspan='3'><div id='opciones'>");
				
				rs=mac.FormulacionesPacientesCE(CodPaciente);
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
				}
				else{
					out.print("No se Encontro Ningun Registro Con esta Identificacion.");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print("</div></td></tr></table>");
		}
		/*******************************************************************************/
		
		if(va.equals("15")){
			out.print("<table width='100%' class='style6' border='1' cellspacing='0'><tr>" +
					"<td width='310'><label><input name='radiobutton' type='radio' value='radiobutton' id='16' onclick='Radio2CE()' />Laboratorio</label></td>" +
					"<td width='100%' colspan='2'><label><input name='radiobutton' type='radio' value='radiobutton' id='77' onclick='Radio2CE()' />Formulacion </label></td>" +
					"<td><label><input name='radiobutton' type='radio' value='radiobutton' id='ICE' onclick='ImagenesCE()' />Imagenes </label></td><td><label><input name='radiobutton' type='radio' value='radiobutton' id='ICE' onclick='ProcedimientosCE()' />Procedimientos </label></td></tr><tr><td colspan='3'><div id='opciones'>");
			try {
				rs=mac.FormulacionesPacientesCE(CodPac);
				out.print("<table width='100%' border='1'><tr><td width='50%'>Fecha Formulacion</td><td width='50%' >Tipo Formulacion</td></tr>");
				while(rs.next()){
					if(rs.getString(4).equals("1")){
						out.print("<tr><td><a href='#' onclick='ImprimirEstudiosCE("+rs.getString(2)+")'>"+rs.getString(1)+"</a></td><td>"+rs.getString(3)+"</td></tr>");
					}
					
					if(rs.getString(4).equals("2")){
						out.print("<tr><td><a href='#' onclick='ReporteFormulacionCE("+rs.getString(2)+")'>"+rs.getString(1)+"</a></td><td>"+rs.getString(3)+"</td></tr>");
					}
					//out.print("<tr><td><a href='#' onclick='ReporteFormulacionCE("+rs.getString(2)+")'>"+rs.getString(1)+"</a></td><td>Medicamentos</td></tr>");
				}
				rs.getStatement().getConnection().close();
				/*rs1=mac.FormulacionesPacientesLabCE(CodPac);
				while(rs1.next()){
					out.print("<tr><td><a href='#' onclick='ImprimirEstudiosCE("+rs1.getString(2)+")'>"+rs1.getString(1)+"</a></td><td>Estudios</td></tr>");
					
					//out.print("<tr><td width='5%' class='style12'>Diluciòn</td><td width='10%'><select name='cmbDilucion' id='cmbDilucion'><option value='Seleccione' selected='selected'>Seleccione</option>");
				}*/
				
				out.print("</table>");
				//rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print("</div></td></tr></table>");
		}
		
		/*if(va.equals("im")){
			out.print("<table border='0' width='100%' ><tr><td colspan='10'><div id='cabecera2' class='style11' align='center'>INMUNOTERAPIAS</div></td></tr>");
			
		}*/
		if(va.equals("16")){			
			/**
			 * si el examen es de laboratorio
			 */
			rs1=mlh.ObtenerAreaExamenes();
			out.print("<table width='100%' class='style6' border='1'><tr style='font-size:9px'><td width='25%'>SELECCIONE GRUPO </td><td width='25%'><select name='cmbgrupos' id='cmbgrupos' onchange='VerExamenesCE()'><option selected='selected'>SELECCIONE</option>");
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option> ");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("</select></td><td width='25%'>Tipo PyP </td><td width='25%'><select name='cmbTipoPyP' id='cmbTipoPyP' ><option value='NO APLICA' selected='selected'>NO APLICA</option><option value='ADULTO MAYOR'>ADULTO MAYOR</option><option value='ADULTO MENOR'>ADULTO MENOR</option><option value='EMBARAZADAS'>EMBARAZADAS</option></select></td></td></tr><tr tyle='font-size:9px'><td colspan='4'><div id='examenes'></div></td></tr>");
			out.print("<tr><td colspan='2'>Examenes </td><td colspan='2'>Accion</td></tr>");
			out.print("<tr><td colspan='4'><div id='Cargados'><input type='hidden' id='txtfechaFr' value='' /><input type='hidden' id='txthoraFr' value='' /> <input type='hidden' id='CodFormlab' value='' /></div></td></tr>");
			out.print("</table>");
		}
		
		if(va.equals("17")){
			int contador=0;
			rs=mlh.ObtenerExamenesArea(CodArea);
			//out.print("<table border='1' class='style6' width='100%'><tr tyle='font-size:9px' id='cabecera2' align='center' class='style11'><td colspan='2'>NOMBRE DEL EXAMEN </td><td align='center'>SELECCIONE<label></label></td></tr>");
			out.print("<select id='cmbExamen'><option value='Seleccione' selected=''>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					//out.print("<tr style='font-size:9px'><td colspan='2'>"+rs.getString(2)+"</td><td width='154' align='center'><label><input type='checkbox' name='ca' id='ca' value='"+rs.getString(1)+"' /><input type='hidden' name='txtTipo' id='txtTipo' value='"+rs.getString(3)+"' />  </label></td></tr>");
					contador=contador+1;
				}
				out.print("</select><input name='btnAsignar'  type='button' id='btnAsignar' value='Asignar' class='boton4' onclick='GuardarLaboratoriosCE2()'>");
				//out.print("<tr><td colspan='2' align='right'></td><td align='center'><label><input name='btnAsignar'  type='button' id='btnAsignar' value='Asignar' class='boton4' onclick='GuardarLaboratoriosCE()'></label></td></tr></table><input name='yo' id='yo' type='hidden' value="+contador+">");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
		/*if(va.equals("18.1")){

			String fechaFr =req.getParameter("fechaFr");
			String horaFr =req.getParameter("horaFr");
			out.print("<table width='100%'>");
			try {
				rs2=mlh.ObtenerCodigoFormulaEstudioFr(fechaFr, horaFr);
				if(rs2.next()){
					rs3=mlh.ObtenerDetalleFormulaEstudio(rs2.getString(1));
					out.print("<input type='hidden' id='CodFormlab' value="+rs2.getString(1)+" />");
					while(rs3.next()){
						out.print("<tr><td colspan='2'>"+rs3.getString(2)+"</td><td colspan='2'><a href='#' onclick='OmitirDetalleLab("+rs3.getString(1)+")'>Omitir</a></td></tr>");
					}
					out.print("<tr><td colspan='4'><input type='button' value='Finalizar' onclick='FinalizarFormLab("+rs2.getString(1)+")' /></td></tr></table>");
					rs3.getStatement().getConnection().close();
					//out.print("<tr><td width='50%'>"+rs2.getString(2)+"</td><td width='50%'><a href='#' onclick='OmitirDetalleLab("+rs2.getString(1)+")'>Omitir</a></td></tr>");
				}
				out.print("<input type='hidden' id='txtfechaFr' value="+fechaFr+" /><input type='hidden' id='txthoraFr' value="+horaFr+" />");
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		
		if(va.equals("18.1")){

			String CodEstudio=req.getParameter("CodEstudio");
			String TipoEstudio=req.getParameter("TipoEstu");
			String TipoPyP=req.getParameter("TipoPyP");
			String codCita=req.getParameter("codCita");			
			String CodFrLab="";
			//String FechaPeticion=req.getParameter("FechaPeticion");
			String CodFormlab=req.getParameter("CodFormlab");
			java.util.Date fechaActual16 = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual16.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual16.getTime());
			if(CodFormlab.equals("")){
				try{
				mlh.InsertarFormulacionEstudios(CodigoPaciente, codCita, CodUsuario, Hora, Fecha);
				rs4=mlh.ObtenerCodigoFormulaEstudio(Fecha, Hora);
				if(rs4.next()){
				CodFrLab=rs4.getString(1);
					mlh.InsertarDetalleFormulacionEstudios(CodFrLab, CodEstudio);					
					//out.print("<input type='hiden' id='CodFormlab' value="+rs4.getString(1)+" />");
				}
				rs4.getStatement().getConnection().close();
				
				rs=mlh.ObtenerDetalleFormulaEstudio(CodFrLab);
				out.print("<table width='100%'><input type='hidden' id='CodFormlab' value="+CodFrLab+" />");
				while(rs.next()){
					out.print("<tr><td colspan='2'>"+rs.getString(2)+"</td><td colspan='2'><a href='#' onclick='OmitirDetalleLab("+rs.getString(1)+","+CodFrLab+")'>Omitir</a></td></tr>");
				}
				out.print("<tr><td colspan='4'><input type='button' value='Finalizar' onclick='FinalizarFormLab("+CodFrLab+")' /></td></tr></table>");
				rs.getStatement().getConnection().close();
				//out.print("<tr><td width='50%'>"+rs2.getString(2)+"</td><td width='50%'><a href='#' onclick='OmitirDetalleLab("+rs2.getString(1)+")'>Omitir</a></td></tr>");
		//	}
			out.print("<input type='hidden' id='txtfechaFr' value="+Fecha+" /><input type='hidden' id='txthoraFr' value="+Hora+" /></table>");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			}else{
				try{
				//out.print("es diferente de vacio");
				mlh.InsertarDetalleFormulacionEstudios(CodFormlab, CodEstudio);
				rs=mlh.ObtenerDetalleFormulaEstudio(CodFormlab);
				out.print("<table width='100%'><input type='hidden' id='CodFormlab' value="+CodFormlab+" />");
				while(rs.next()){
					out.print("<tr><td colspan='2'>"+rs.getString(2)+"</td><td colspan='2'><a href='#' onclick='OmitirDetalleLab("+rs.getString(1)+","+CodFormlab+")'>Omitir</a></td></tr>");
				}
				out.print("<tr><td colspan='4'><input type='button' value='Finalizar' onclick='FinalizarFormLab("+CodFormlab+")' /></td></tr></table>");
				rs.getStatement().getConnection().close();
				//out.print("<tr><td width='50%'>"+rs2.getString(2)+"</td><td width='50%'><a href='#' onclick='OmitirDetalleLab("+rs2.getString(1)+")'>Omitir</a></td></tr>");
		//	}
			out.print("<input type='hidden' id='txtfechaFr' value="+Fecha+" /><input type='hidden' id='txthoraFr' value="+Hora+" /></table>");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			
		}
		
		if(va.equals("18.3")){
			String CodDetLab=req.getParameter("CodDetLab"); 
			String CodFormlab=req.getParameter("CodFormlab");
			String Hora=req.getParameter("Hora");
			String Fecha=req.getParameter("Fecha");
			mlh.EliminarDetalleFormulacionLaboratorio(CodDetLab);
			rs=mlh.ObtenerDetalleFormulaEstudio(CodFormlab);
			try{
			out.print("<table width='100%'><input type='hidden' id='CodFormlab' value="+CodFormlab+" />");
			while(rs.next()){
				out.print("<tr><td colspan='2'>"+rs.getString(2)+"</td><td colspan='2'><a href='#' onclick='OmitirDetalleLab("+rs.getString(1)+","+CodFormlab+")'>Omitir</a></td></tr>");
			}
			out.print("<tr><td colspan='4'><input type='button' value='Finalizar' onclick='FinalizarFormLab("+CodFormlab+")' /></td></tr></table>");
			rs.getStatement().getConnection().close();
			//out.print("<tr><td width='50%'>"+rs2.getString(2)+"</td><td width='50%'><a href='#' onclick='OmitirDetalleLab("+rs2.getString(1)+")'>Omitir</a></td></tr>");
	//	}
		out.print("<input type='hidden' id='txtfechaFr' value="+Fecha+" /><input type='hidden' id='txthoraFr' value="+Hora+" /></table>");

			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("18.2")){
			String CodEstudio=req.getParameter("CodEstudio");
			String TipoEstudio=req.getParameter("TipoEstu");
			String TipoPyP=req.getParameter("TipoPyP");
			String CodFrLab=req.getParameter("CodFrLab");
			
			String fechaFr=req.getParameter("fechaFr");
			String horaFr=req.getParameter("horaFr");
			
			java.util.Date fechaActual16 = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual16.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual16.getTime());
			
			
			/*******************************************************************/
			try {
				//rs2=mlh.ObtenerCodigoFormulaEstudioFr(fechaFr, horaFr);
				if(rs2.next()){
				//	mlh.InsertarDetalleFormulacionEstudios(rs2.getString(1), CodEstudio);
					//rs3=mlh.ObtenerDetalleFormulaEstudio(rs2.getString(1));
					out.print("<input type='hidden' id='CodFormlab' value="+rs2.getString(1)+" />");
					while(rs3.next()){
						out.print("<tr><td colspan='2'>"+rs3.getString(2)+"</td><td colspan='2'><a href='#' onclick='OmitirDetalleLab("+rs3.getString(1)+")'>Omitir</a></td></tr>");
					}
					out.print("<tr><td colspan='4'><input type='button' value='Finalizar' onclick='FinalizarFormLab("+rs2.getString(1)+")' /></td></tr></table>");
					rs3.getStatement().getConnection().close();
					//out.print("<tr><td width='50%'>"+rs2.getString(2)+"</td><td width='50%'><a href='#' onclick='OmitirDetalleLab("+rs2.getString(1)+")'>Omitir</a></td></tr>");
				}
				out.print("<input type='hidden' id='txtfechaFr' value="+fechaFr+" /><input type='hidden' id='txthoraFr' value="+horaFr+" />");
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/*******************************************************************/
			
			rs=mlh.ObtenerNombreUsuario(CodUsuario);
			String NombreUsuario="";
			try {
				if(rs.next()){
					NombreUsuario=rs.getString(1)+" "+rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			mlh.RelacionPacienteLaboratorios(CodEstudio, TipoEstudio, CodigoPaciente, Hora, Fecha,NombreUsuario,TipoPyP);

		}
		
		
		if(va.equals("18.4")){
			String CodFormlab=req.getParameter("CodFormlab"); 
			mlh.ActualizarFormulacionEstudios(CodFormlab);
			out.print(CodFormlab);
		}
		
		
		
		if(va.equals("18")){
			java.util.Date fechaActual15 = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual15.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual15.getTime());
			String CodEstudio=req.getParameter("CodEstudio");
			String TipoEstudio=req.getParameter("TipoEstu");
			String TipoPyP=req.getParameter("TipoPyP");
			String codCita=req.getParameter("codCita");			
			String contador=req.getParameter("contador");
			String CodFrLab="";
			try {
				/*****************************************************************/				
				//rs1=mlh.ObtenerCodigoFormulaEstudio(Fecha, Hora);
				if(rs1.next()){
					CodFrLab=rs1.getString(1);
					//mlh.InsertarDetalleFormulacionEstudios(CodFrLab, CodEstudio);
					//out.print("<input type='hiden' id='CodFormlab' value="+rs1.getString(1)+" />");
				}else{
					if(contador.equals("1")){
						//System.out.println("entro en contador= "+contador);
				//	mlh.InsertarFormulacionEstudios(CodigoPaciente, codCita, CodUsuario, Hora, Fecha);
					//rs4=mlh.ObtenerCodigoFormulaEstudio(Fecha, Hora);
					if(rs4.next()){
						CodFrLab=rs4.getString(1);
					//	mlh.InsertarDetalleFormulacionEstudios(CodFrLab, CodEstudio);
						
						//out.print("<input type='hiden' id='CodFormlab' value="+rs4.getString(1)+" />");
					}
					rs4.getStatement().getConnection().close();
					}else{
						System.out.println("entro en contador= "+contador);
					//rs3=mlh.ObtenerCodigoFormulaEstudio(Fecha, Hora);
					if(rs3.next()){
						CodFrLab=rs3.getString(1);
					//	mlh.InsertarDetalleFormulacionEstudios(CodFrLab, CodEstudio);
						//out.print("<input type='hiden' id='CodFormlab' value="+rs3.getString(1)+" />");
					}
					rs3.getStatement().getConnection().close();
					}
				}
				rs1.getStatement().getConnection().close();
				out.print(Fecha+"|"+Hora);
				/***********************************************************/
				/*rs5=mlh.ObtenerCodigoFormulaEstudio(Fecha, Hora);
				if(rs5.next()){
					CodFrLab=rs5.getString(1);
					mlh.InsertarDetalleFormulacionEstudios(CodFrLab, CodEstudio);
					//out.print("<input type='hidden' id='CodFormlab' value="+rs5.getString(1)+" />");
					//System.out.println("CodFrLab rs55  "+CodFrLab);
					rs2=mlh.ObtenerDetalleFormulaEstudio(CodFrLab);
					while(rs2.next()){
						out.print("<tr><td colspan='2'>"+rs2.getString(2)+"</td><td colspan='2'><a href='#' onclick='OmitirDetalleLab("+rs2.getString(1)+")'>Omitir</a></td></tr>");
					}
					out.print("<tr><td colspan='4'><input type='button' value='Finalizar' onclick='FinalizarFormLab("+CodFrLab+")' /></td></tr></table>");
					rs2.getStatement().getConnection().close();
										
				}
				rs5.getStatement().getConnection().close();*/
				
				/************************************************************/
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/***********************************************************************/
			rs=mlh.ObtenerNombreUsuario(CodUsuario);
			String NombreUsuario="";
			try {
				if(rs.next()){
					NombreUsuario=rs.getString(1)+" "+rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			mlh.RelacionPacienteLaboratorios(CodEstudio, TipoEstudio, CodigoPaciente, Hora, Fecha,NombreUsuario,TipoPyP);
		}
		
		
		
		/*********************FORMATOS**********************************/
		if(va.equals("19")){
			
			//out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td width='192'>Seleccione Formato </td><td width='776'><label><input name='txtFormato' type='text' id='txtFormato' size='90' onkeyup='autocompletarFormato()' /><input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /><input name='txtCodFormato' type='hidden' id='txtCodFormato' /></label></td></tr><tr><td colspan='2'><div class='style6' id='sugerenciasFormato'></div></td></tr></table>");
			out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr><td><div align='center'>Seleccione Formato <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name='txtCodFormato' id='txtCodFormato'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mlh.ObtenerFormatosPermitidosUsuarioCE(CodUsuario);
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			
		
			
				out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormatoCE()' /></label></div></td></tr></table>");
				out.print("<br>");
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td width='20%'>Nombre Del Formato </td><td width='10%'>Hora y Fecha </td><td width='19%'>Usuario</td><td width='14%'>Acciones de Formato</td><td width='37%'><div align='center'>Areas Del Formato</div></td></tr><tr class='style6'><td colspan='4'><div id='FormatosPaciente'></div></td><td><div id='areas' class='style6'></div></td></tr></table>");
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td><div align='center'> Preguntas Del Formato</div></td></tr><tr><td><div class='style6' id='formulario'></div></td></tr></table>");
			
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td align='center'>DIAGNOSTICOS</td></tr>");
				out.print("<tr><td id='dxhis'>");
				rs=mlh.CargarDiagnosticosActivos(CodigoPaciente);
				out.print("<table  width='100%' border='1' ><tr><td>DIAGNOSTICOS</td><td>-</td><td onclick='CargarDxNota()' >Añadir a nota</td><td>-</td></tr>");
				int cont=0;
				while(rs.next()){
					String dx=rs.getString(1)+"-"+rs.getString(2);
					cont=cont+1;
					out.print("<tr><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td colspan='3'><input type='checkbox' name='chkPorAtender' id='chkPorAtender' value='"+dx+"' /></td></tr>");
				}
				out.print("<input type='hidden' id='txtContador' value="+cont+" /><input type='hidden' id='txtSeldx'  /></table>");
				out.print("</td></tr>");
			
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("20")){
			
			String CodigoArea="";
			String CodigoPregunta="";
			//String TipoFormato="";
			/**
			 * se guarda la relacion del formato con la admision y el paciente.... 
			 */
			if(CodFormato!=""){
				//rs3=mvf.ObtenerTipoFormato(CodFormato);
				try {
					/*if(rs3.next()){
						TipoFormato=rs3.getString(1);
					}*/
					//rs3.getStatement().getConnection().close();
					//if(TipoFormato.equals("0")){
						String resultados="";
						mac.RelacionFormatoPacienteCE(CodFormato,  CodigoPaciente, hora, fecha, CodUsuario,CodHorarioMedico);
						rs1=mvf.ObtenerAreaFormatoCE(CodFormato,fecha,hora);
						
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
										mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fecha, hora, CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
										
									}
									if(TipoPregunta.equals("2")){
										/**si tipo pregunta es igual a un tipo seleccion*/
										resultados="Seleccione";
										mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fecha, hora, CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
										
									}
									if(TipoPregunta.equals("3")){
										/**si tipo pregunta es igual a un texto corto */
										mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fecha, hora, CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
									}
									if(TipoPregunta.equals("4")){
										/**si tipo pregunta es igual a un autocompletar de tipo diagnostico */
										mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fecha, hora, CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
									}
									if(TipoPregunta.equals("5")){
										/**si tipo pregunta es igual a un autocompletar de tipo diagnostico varios */
										mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fecha, hora, CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
									}
								}
							}
							rs1.getStatement().getConnection().close();
							rs2.getStatement().getConnection().close();
							
				
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			
			}
		if(va.equals("21")){
			/**
			 * una ves guardada la relacion se procede a mostrar los formatos - 
			 */
			rs=mac.ObtenerFormatosPacienteCE(CodigoPaciente, CodHorarioMedico);
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
					
					out.print("<tr><td width='35%'><a  href='#'onclick='VerAreasCE("+rs.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+CodHorarioMedico+")'>"+rs.getString(2)+"</a></td><td width='17%'>"+rs.getString(3)+" / "+rs.getString(4)+"</td><td width='26%'>"+rs.getString(5)+" "+rs.getString(6)+"</td>");
					if(rs.getString(7).equals(CodUsuario)&& rs.getString(10).equals("0")){
					out.print("<td width='19%'><img title='Guardar' name='btnGuardarFormato' src='Imagenes/Guardar.JPG' id='btnGuardarFormato'onclick='GuardarFormatoCE("+rs.getString(9)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormatoCE("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodHorarioMedico+")' style='cursor:pointer;' />&nbsp;&nbsp;<img title='Eliminar' name='btnEliminarFormato' src='Imagenes/Eliminar.JPG' id='btnGuardarFormato'onclick='OmitirFormatoCE("+rs.getString(9)+","+CodHorarioMedico+")' style='cursor:pointer;' /></td>");
					}
					else{
						if(rs.getString(10).equals("1")){
							out.print("<td width='19%'>G&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormatoCE("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodHorarioMedico+")' style='cursor:pointer;' /></td>");
						}
						else{
							out.print("<td width='19%'>C&nbsp;&nbsp;&nbsp;&nbsp;<img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormatoCE("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(11)+","+CodHorarioMedico+")' style='cursor:pointer;' /></td>");
						}
						
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
		if(va.equals("22")){
			/**
			 * se obtienen las areas del formato llevando como parametro el codigo del formato. 
			 */
			rs=mac.ObtenerAreaFormatoCE(CodFormato,fecha,hora,CodHorarioMedico);
			try {
				out.println("<table width='100%'class='style2' border='0'><tr><td></td></tr>");
				while(rs.next()){
					String NombArea=rs.getString(2);				
					out.println("<tr><td><a href='#'onclick='mostarpreguntasCE("+rs.getString(1)+","+CodHorarioMedico+")'>"+NombArea+"</a><input name='HoraFormato' type='hidden' id='HoraFormato' value='"+rs.getString(3)+"' ><input name='FechaFormato' type='hidden' id='FechaFormato' value='"+rs.getString(4)+"' ><input name='CodArea' type='hidden' id='CodArea' value='"+rs.getString(1)+"' ><input name='CodFormato' type='hidden' id='CodFormato' value='"+CodFormato+"' ></td></tr>");
				}
				out.println("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error ResultSet ControlAsignarCita>>valor=22 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("23")){
			int contador=0;
			/**
			 * se obtienen las preguntas del area llevando como parametro el codigo del area. 
			 */
			String usuario="";
			String estadoPregunta="";
			rs4=mac.ObtenerUsuarioFormatoCE(CodArea, fecha, hora, CodUsuario,CodHorarioMedico);
			try {
				if(rs4.next()){
					usuario=rs4.getString(1);
					estadoPregunta=rs4.getString(2);
				}
				if((usuario=="")&&(estadoPregunta.equals(""))){
					try {
						rs=mac.ObtenerPreguntasAreaLlenoCE(CodArea, fecha, hora,CodHorarioMedico);
						out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
						while(rs.next()){
							String CodTipoResp=rs.getString(3);
							out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
							if(rs.getString(2).equals("1")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
								 */
							out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onkeyup='ActualizarResultados()' readonly='' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
							}
							contador=contador+1;
							if(rs.getString(2).equals("2")){
								/**
								 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
								 */
								rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
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
							/*if(rs.getString(2).equals("5")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								/*out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80'  onkeyup='autocompletarCIE10()' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}*/
						}
						out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("Error ResultSet valor=1 "+e);
						e.printStackTrace();
					}
				}			
				if((usuario!="")&&(estadoPregunta.equals("1"))){
					try {
						rs=mac.ObtenerPreguntasAreaLlenoCE(CodArea, fecha, hora,CodHorarioMedico);
						out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
						while(rs.next()){
							String CodTipoResp=rs.getString(3);
							out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
							if(rs.getString(2).equals("1")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
								 */
							out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onkeyup='ActualizarResultados()' readonly='' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
							}
							contador=contador+1;
							if(rs.getString(2).equals("2")){
								/**
								 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
								 */
								rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
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
								out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"'  readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
							/*if(rs.getString(2).equals("5")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
						/*		out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80'  onkeyup='autocompletarCIE10()' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}*/
						}
						out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("Error ResultSet valor=1 "+e);
						e.printStackTrace();
					}
				}
				if((usuario!="")&&(estadoPregunta.equals("0"))){
					try {
						rs=mac.ObtenerPreguntasAreaLlenoCE(CodArea, fecha, hora,CodHorarioMedico);
						out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
						while(rs.next()){
							String CodTipoResp=rs.getString(3);
							out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
							if(rs.getString(2).equals("1")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
								 */
							out.println("<td width='82%'>");
							/////////////////////////////////////////////
							out.print("");
							out.print("");
							out.print("");
							////////////////////////////////////////////
							out.println("<textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onkeyup='ActualizarResultadosCE()' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+" ");
							
							out.println("<div>");
							//out.println("diagnosaticos");
							//CodPac
							out.println("</div>");
							out.println("</td></tr>");
							}
							contador=contador+1;
							if(rs.getString(2).equals("2")){
								/**
								 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
								 */
								rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
								out.print("<td><select name='txtRespuesta' id='txtRespuesta' onchange='ActualizarResultadosCE()'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
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
								out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onkeyup='ActualizarResultadosCE()' value="+rs.getString(5)+" > "+rs.getString(6)+"</td>");
							}
							if(rs.getString(2).equals("4")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								
								//String Resultado=rs.getString(5);
								//if(Resultado.equals("")){
									out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80'  onkeyup='autocompletarCIE10()'  /><input name='txtNomDiagnos1' type='hidden' id='txtNomDiagnos1' size='80' value='"+rs.getString(5)+"' /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialCEf()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div><div id='DiaCE'>"+rs.getString(5)+"</div></td></tr>");
								//}
								/*else{
									out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								}*/
								
							}
							/*if(rs.getString(2).equals("5")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								
								//String Resultado=rs.getString(5);
								//if(Resultado.equals("")){
								/*	out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialCE()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								//}
								/*else{
									out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								}*/
									//}
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
	
				e1.printStackTrace();
			}
		}
		
		if(va.equals("24")){
			mac.ActualizarResultadosCE(Resul, CodResul);
			rs=mac.ObtenerDiagnosInicialCE(CodResul);
			out.print("<table>");
			try {
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
		}
		
		if(va.equals("25")){
			try {
				rs=mac.ObtenerHoraFechaFormatoCE(CodForPacCE);
				while(rs.next()){
					String FechaR=rs.getString(1);
					String HoraR=rs.getString(2);
					//mac.OmitirRegistrosFormatoCE(FechaR, HoraR);
				}
				mac.OmitirFormatoPacienteCE(CodForPacCE);
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error Va=8 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("26")){
			String val="";
			String TipoReq="";
			rs=mac.BuscarPreguntasRequeridasCE(fecha, hora);
			try {
				if(rs.next()){
					val=rs.getString(1);
					if(val!=""){
						rs1=mac.BuscarPreguntasRequeridasCE(fecha, hora);
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
					mac.ActualizarFormatoActivoCE(CodForPacCE);
					mac.ActualizarPreguntasFormatoActivoCE(fecha, hora);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		/****************************************************************************************************/
		/************************************INICIO GUARGAR DX CEX*******************************************/
		/****************************************************************************************************/
		java.util.Date fechaActualDX = new java.util.Date();
		java.sql.Date fechaDX = new java.sql.Date(fechaActualDX.getTime());		
		java.sql.Time horaDX = new java.sql.Time(fechaActualDX.getTime());
		String CodAdm=req.getParameter("CodAdm");
		String CodDiagnostico=req.getParameter("CodDiagnostico");
		String CodUsu=req.getParameter("CodUsu");
		String TipoDiag=req.getParameter("TipoDiag");
		if(va.equals("DXICE")){
			System.out.print("Entro DXICE");
				try {
					String CodDiag_fk="";
					rs8=mac.ObtenerCodigoDiagnostico(CodDiagnostico);
					if(rs8.next()){
						CodDiag_fk=rs8.getString(1);
					}
					rs8.getStatement().getConnection().close();
					
					mac.IngresarDiagnosticoIngreso(CodResul, CodDiagnostico, CodUsu, horaDX, fechaDX, CodAdm, CodPac, CodDiag_fk);
					rs7=mac.ObtenerDiagIngreso(CodAdm);
					out.print("<table>");
					while(rs7.next()){
						out.print("<tr><td>"+rs7.getString(1)+"</td><td><a href='#' onclick='OmitirDxI("+rs7.getString(2)+")'>Omitir</a></td></tr>");
					}
					out.print("</table>");
					rs7.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//out.print("Ingreso Exitosa.");
		}
		
		if(va.equals("DXRCE")){
			try {
				String CodDiag_fk="";
				rs8=mac.ObtenerCodigoDiagnostico(CodDiagnostico);
				if(rs8.next()){
					CodDiag_fk=rs8.getString(1);
				}
				rs8.getStatement().getConnection().close();
				
				
				
				//if(TipoDiagRel2.equals("RE2")){
					/*rs2=mac.ObtenerDiagRE2(CodAdm, TipoDiagRel2);
					if(rs2.next()){
						out.print("Ya Existe Un Diagnostico Relacionado 2 Para Esta Admision.");
					}
					else{*/
						mac.IngresarDiagnosticosRelaEgreso(CodDiagnostico, TipoDiag, CodUsu, horaDX, fechaDX, CodAdm, CodPac,CodDiag_fk);
					/*}
					rs2.getStatement().getConnection().close();*/
				//}
						if(TipoDiag.equals("RE2H")){
							rs7=mac.ObtenerDiagEgresoRelac(CodAdm,TipoDiag);
							out.print("<table>");
							while(rs7.next()){
								out.print("<tr><td>"+rs7.getString(1)+"</td><td><a href='#' onclick='OmitirDxE("+rs7.getString(2)+")'>Omitir</a></td></tr>");
							}
							out.print("</table>");
							rs7.getStatement().getConnection().close();
						}
						
						if(TipoDiag.equals("EGH")){
							rs7=mac.ObtenerDiagEgresoRelac(CodAdm,TipoDiag);
							out.print("<table>");
							while(rs7.next()){
								out.print("<tr><td>"+rs7.getString(1)+"</td><td><a href='#' onclick='OmitirDxE("+rs7.getString(2)+")'>Omitir</a></td></tr>");
							}
							out.print("</table>");
							rs7.getStatement().getConnection().close();
						}
		
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		/****************************************************************************************************/
		/************************************FIN INGRESO DX CEX**********************************************/
		/****************************************************************************************************/
		
		if(va.equals("odi")){
			String coddxi=req.getParameter("coddxi");
			mac.OmitirDxIngre(coddxi);
		}
		
		if(va.equals("ode")){
			String coddxe=req.getParameter("coddxe");
			mac.OmitirDxEgre(coddxe);
		}
		
		if(va.equals("27")){
			try {

				rs=mac.BuscarFormatosPendientesCE1(CodHorarioMedico);				
				if(rs.next()){
					rs1=mac.BuscarFormatosPendientesCE(CodHorarioMedico);
					if(rs1.next()){	
						rs2=mac.BuscarFormatosPendientesCE(CodHorarioMedico);
						out.println("<table width='100%' border='1'><tr><td colspan='2'><div>No Se Puede Finalizar La Atencion Porque El Paciente Tiene Formatos Pendientes, Se Relacionan de La Siguiente Manera: </div></td></tr>");
						out.println("<tr><td width='50%'><div align='center'>Nombre Formato</div> </td><td width='50%'><div align='center'>Fecha y Hora</div> </td></tr>");
						while(rs2.next()){
							out.println("<tr><td>"+rs2.getString(4)+"</td><td>"+rs2.getString(3)+"</td></tr>");
						}
						out.print("</table>");
						rs2.getStatement().getConnection().close();
					}
					else{
						//out.print("<table width='100%' border='1'><tr><td width='100%'><div>El Paciente No Tiene Formatos Pendientes Puede Finalizar La Atencion. </div></td></tr>");
						//out.print("<tr><td><div align='center'><input name='btnFinAtencionCE' type='button' id='btnFinAtencionCE' value='Finalizar Atencion' onClick='FinalAtencionCE()'></div></td></tr></table>");
						/****************************************************************/
						out.print("<table width='100%' border='0'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Clasificacion de Diagnosticos </div></td></tr>" +
								"<tr><td><b>DIAGNOSTICO DE INGRESO</b></td></tr>" +
								"<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialCE()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' ></label></td>" +
								"</tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
						out.print("<tr><td><div id='DxInCEX'>");
						rs7=mac.ObtenerDiagIngreso(CodAdm);
						out.print("<table>");
						while(rs7.next()){
							out.print("<tr><td>"+rs7.getString(1)+"</td><td><a href='#' onclick='OmitirDxI("+rs7.getString(2)+")'>Omitir</a></td></tr>");
						}
						out.print("</table>");
						rs7.getStatement().getConnection().close();
						
						out.print("</div></td></tr>");
						out.print("<tr><td><div id='DI'></div></td></tr>");
						//out.print("<tr><td><b>DIAGNOSTICO RELACIONADO 1</b></td><td>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela1' type='text' id='txtNomDiagnosRela1' size='80' onkeyup='autocompletarCIE10DiagRela1()'   /><input name='txtCodDiagnosticoRela1' type='text' id='txtCodDiagnosticoRela1' readonly='' /><input name='txtTipoDiagRel1' type='hidden' id='txtTipoDiagRel1' value='RE1H'  /></label></td><td><input name='btnDiagrel1' type='button' id='btnDiagrel1' value='Asignar' onClick='AsignarDiagRel1()'></td></tr><tr><td><div id='SugeDiagnosticoRela1'></div></td><td>&nbsp;</td></tr>");
						out.print("<tr><td width='642'><b>DIAGNOSTICOS SECUNDARIOS</b></td><td width='66'>&nbsp;</td></tr><tr><td><label><input name='txtNomDiagnosRela2' type='text' id='txtNomDiagnosRela2' size='80' onkeyup='autocompletarCIE10DiagRela2()'   /><input name='txtCodDiagnosticoRela2' type='text' id='txtCodDiagnosticoRela2' readonly='' /><input name='txtTipoDiagRel2' type='hidden' id='txtTipoDiagRel2' value='RE2H'  /></label><input name='btnDiagrel2' type='button' id='btnDiagrel2' value='Asignar' onClick='AsignarDiagRelCE()'></td></tr><tr><td><div id='SugeDiagnosticoRela2'></div></td></tr><tr><td><div id='CXDIRE'>");
						rs7=mac.ObtenerDiagEgresoRelac(CodAdm,"RE2H");
						out.print("<table>");
						while(rs7.next()){
							out.print("<tr><td>"+rs7.getString(1)+"</td><td><a href='#' onclick='OmitirDxE("+rs7.getString(2)+")'>Omitir</a></td></tr>");
						}
						out.print("</table>");
						rs7.getStatement().getConnection().close();
						out.print("</div></td></tr>");
						out.print("<tr><td  width='642'><b>DIAGNOSTICO DE EGRESO</b></td><td  width='66'>&nbsp;</td></tr><tr><td><input name='txtNomDiagnosRelaEgreso' type='text' id='txtNomDiagnosRelaEgreso' size='80'  onkeyup='autocompletarCIE10Egreso()'      /><input name='txtCodDiagnosticoEgreso' type='text' id='txtCodDiagnosticoEgreso' readonly=''  /><input name='txtTipoDiagEG' type='hidden' id='txtTipoDiagEG' value='EGH'  /><label><input name='btnDiagEg' type='button' id='btnDiagEg' value='Asignar'  onClick='AsignarDiagEgCE()'></label></td></tr><tr><td><div id='SugeDiagnosticoEgreso'></div></td></tr><tr><td><div id='CXDIEG'>");
						rs7=mac.ObtenerDiagEgresoRelac(CodAdm,"EGH");
						out.print("<table>");
						while(rs7.next()){
							out.print("<tr><td>"+rs7.getString(1)+"</td><td><a href='#' onclick='OmitirDxE("+rs7.getString(2)+")'>Omitir</a></td></tr>");
						}
						out.print("</table>");
						rs7.getStatement().getConnection().close();
						out.print("</div></td></tr></table>");
						
						/*****************************************************************/
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

						out.print("<table width='100%' border='0' ><tr><td><div align='center'><input name='btnFinAtencionCE' type='button' id='btnFinAtencionCE' value='Finalizar Atencion' onClick='FinalAtencionCE()'></div></td></tr></table>");
						
					}
					rs1.getStatement().getConnection().close();
				}
				else{
					out.print("No Se Puede Finalizar la Atencion \n El Paciente No Tiene Formatos.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error ControlAsignarCita>>va.equals(27) "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("28")){
			String CodAdm1=req.getParameter("CodAdm");
			String CodPac1=req.getParameter("CodPac");
			String finacons=req.getParameter("finacons");
			String cauext=req.getParameter("cauext");
			String tipdiag=req.getParameter("tipdiag");
			String CodUsu1=req.getParameter("CodUsu");
			
			java.util.Date fechaDX0 = new java.util.Date();
			java.sql.Date fechaDX1 = new java.sql.Date(fechaDX0.getTime());		
			java.sql.Time horaDX1 = new java.sql.Time(fechaDX0.getTime());
			rs=mca.VerificarDiagnosticoEgreso(CodAdm1);
			try {
				if(rs.next()){					
					mac.IngresarEstadoSalidaDestino("SALIDA CONSULTA EXTERNA", "VIVO", CodUsu1, horaDX1+"", fechaDX1+"", CodAdm1, CodPac1, tipdiag, cauext, finacons);
					mac.FinalizarAtencionCE(CodHorarioMedico);
					out.print("Atencion Finalizada Exitosamente.");					
					
				}else{					
					out.print("-1");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("DA")){	
			mca.DevolverAtencionCex(CodHorarioMedico);
			out.print("Atencion Activada.");
		}
		
		if(va.equals("1DA")){			
			try {
			
				String Tip_Documento =req.getParameter("TipoDocumento");
				String Numero_Documento =req.getParameter("NumeroDocumento");
				rs6=mca.BuscarPacienteDevolverAtencion(Tip_Documento, Numero_Documento);
				if(rs6.next()){
					out.print("<table border='1' width='100%' >" +
							"<tr><td>Nombre Paciente:</td><td>"+rs6.getString(2)+"<input type='hidden' id='NombrePacAnul' value='"+rs6.getString(2)+"' ></td>" +
							"<td>Documento:</td><td>"+Tip_Documento+"-"+Numero_Documento+" <input type='hidden' id='DocumenPac' value='"+Tip_Documento+"-"+Numero_Documento+"' ></td>" +
									"<td>Genero:</td><td>"+rs6.getString(4)+"</td>" +
											"<td>Entidad:</td><td>"+rs6.getString(3)+"</td></tr>");
					
					out.print("<tr><td>Medico Cita</td><td>"+rs6.getString(6)+"</td>" +
							"<td>Fecha y Hora Cita</td><td>"+rs6.getString(7)+"/"+rs6.getString(8)+" </td>" +
									"<td>Accion:</td><td><a href='#' onclick='DevolverAtencion("+rs6.getString(9)+")'>Devolver Atencion</a></td>" +
											"<td></td><td></td></tr>");
					out.print("</table>");
				}else{
					out.print("EL PACIENTE NO TIENE CITA FINALIZADA PARA EL DIA DE HOY");
				}
				rs6.getStatement().getConnection().close();
				
				
				//***anular la cita
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		if(va.equals("1")){			
			try {
				rs6=mca.VerificarInasistencias();
				while(rs6.next()){
					   String CodCita="";
					   CodCita=rs6.getString(1);
					   mca.ActualizarCitasInasistencia(CodCita);
				}
				rs6.getStatement().getConnection().close();
				
				String Tip_Documento =req.getParameter("TipoDocumento");
				String Numero_Documento =req.getParameter("NumeroDocumento");
				rs6=mca.BuscarPacienteAnular(Tip_Documento, Numero_Documento);
				if(rs6.next()){
					out.print("<table border='1' width='100%' ><tr><td>Nombre Paciente:</td><td>"+rs6.getString(2)+"<input type='hidden' id='NombrePacAnul' value='"+rs6.getString(2)+"' ></td><td>Documento:</td><td>"+Tip_Documento+"-"+Numero_Documento+" <input type='hidden' id='DocumenPac' value='"+Tip_Documento+"-"+Numero_Documento+"' ></td><td>Genero:</td><td>"+rs6.getString(4)+"</td><td>Entidad:</td><td>"+rs6.getString(3)+"</td></tr>");
					rs=mca.BuscarPacienteAnular(Tip_Documento, Numero_Documento);
					int co=0;
					out.print("<tr bgcolor='grey'><td>No</td><td>Fecha-Hora</td><td colspan='5'>DESCRIPCION</td><td>MOTIVO DE ANULACION</td><td>ACCION</td></tr>");
					//out.print("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
					while(rs.next()){
						co=co+1;
						out.print("<tr><td>"+co+"</td><td>"+rs.getString(7)+"/"+rs.getString(8)+"<input type='hidden' id='FechaHoraCita' value='"+rs.getString(7)+"/"+rs.getString(8)+"' ></td><td colspan='5'> MEDICO "+rs.getString(6)+"<input type='hidden' id='MedicoCita' value='"+rs.getString(6)+"' ></td><td><textarea id='txtMotivoAnula"+rs.getString(9)+"' ></textarea></td><td><a href='#' onclick='AnularCita("+rs.getString(9)+")'>Anular</a></td></tr>");
					}
					
					out.print("</table>");
					rs.getStatement().getConnection().close();
				}else{
					out.print("EL PACIENTE NO TIENE CITAS ACTIVAS");
				}
				rs6.getStatement().getConnection().close();
				
				
				//***anular la cita
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*java.util.Calendar hoy = java.util.Calendar.getInstance();
			java.util.Date primerDia= hoy.getTime();
			int diasMes[]= new int[12];
			diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
			diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;
			String horaI="";String minutoI="";String JornadaI="";String horaF="";String minutoF="";String JornadaF="";
			String Lapso="";String p1="";String p2="";String p3="";String p1f="";String p2f="";String p3f="";
			int dia;int anio;int columna;int nDias;int diaInicial;int i;int contador = 0;int mes;
			String NombreMes = "";
			anio=hoy.get(java.util.Calendar.YEAR);
			dia=hoy.get(java.util.Calendar.DATE);
			mes=hoy.get(java.util.Calendar.MONTH);
			mes=mes+1;
			if(mes==1){NombreMes="Enero";}
			if(mes==2){NombreMes="Febrero";}
			if(mes==3){NombreMes="Marzo";}
			if(mes==4){NombreMes="Abril";}
			if(mes==5){NombreMes="Mayo";}
			if(mes==6){NombreMes="Junio";}
			if(mes==7){NombreMes="Julio";}
			if(mes==8){NombreMes="Agosto";}
			if(mes==9){NombreMes="Septiembre";}
			if(mes==10){NombreMes="Octubre";}
			if(mes==11){NombreMes="Noviembre";}
			if(mes==12){NombreMes="Diciembre";}
			
			if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) //Compruebo que el año es bisiesto
			{
			diasMes[1] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}
			nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
			primerDia.setDate(1);
			diaInicial=primerDia.getDay();
			
			String NombreCompleto="0";String Entidad="";
			rs=mac.BuscarPaciente(TipoDocumento, NumeroDocumento);
		
			try {
				rs5=mp.BuscarParametros();
			 if(rs5.next()){
				if(rs.next()){
					out.println("<table width='100%' border='0'>");
					NombreCompleto=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
					Entidad=rs.getString(5);
					out.println("<tr><td width='26%'>Nombre Paciente: </td><td>"+NombreCompleto+"<input name='NombreCompleto' type='hidden' id='NombreCompleto' value='"+NombreCompleto+"' /></td></tr>");
					out.println("<tr><td>Entidad:</td><td>"+Entidad+"<input name='txtCodigoPaciente' type='hidden' id='txtCodigoPaciente' value="+rs.getString(1)+" /></td></tr>");
					out.println("</table>");
					
					out.println("<table width='100%' border='1'>");
					out.println("<tr><td><div>Medico</div></td><td><div><select name='cmbMedico' id='cmbMedico'><option value='Seleccione'>Seleccione</option>");
					rs1=mmd.BuscarMedicoTodos();
					while(rs1.next()){
						String nombre=rs1.getString(2);
						String apellidos=rs1.getString(3);
						String Completo=nombre+" "+apellidos;
					out.println("<option value="+rs1.getString(1)+">"+Completo+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.println("</select></div></td></tr>");
					out.println("<tr><td><div>Especialidad</div></td><td><div><select name='cmbEspecialidad' id='cmbEspecialidad'><option value='Seleccione'>Seleccione</option>");
					rs2=mac.BuscarEspecialidadTodas();
					while(rs2.next()){
						out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
					}
					rs2.getStatement().getConnection().close();
					out.println("</select></div></td></tr>");
					out.println("<tr><td width='9%'><div>Hora</div></td><td width='37%'><div><label><select name='cmbHora' id='cmbHora'><option value='Seleccione'>Seleccione</option>");
					
					rs4=mac.buscarHoras();
					while(rs4.next()){
						out.println("<option value="+rs4.getString(1)+">"+rs4.getString(1)+"</option>");
					}
					rs4.getStatement().getConnection().close();*/
					
					 //try {
					/*	 rs4=mp.BuscarParametros();						 
						while(rs4.next()){
							horaI=rs4.getString(2).substring(0,2);
							minutoI=rs4.getString(2).substring(3,5);
							JornadaI=rs4.getString(2).substring(6,8);
							
							horaF=rs4.getString(3).substring(0,2);
							minutoF=rs4.getString(3).substring(3,5);
							JornadaF=rs4.getString(3).substring(6,8);
							
							Lapso=rs4.getString(4);
						}
						rs4.getStatement().getConnection().close();
				//	} 
					 p1 = horaI;//horaInicial
					 p2 = minutoI;//minutosInicial
					 p3 = JornadaI;//jornada AM-PM Inicial
					
					 p1f=horaF;
					 p2f=minutoF;
					 p3f=JornadaF;
					
					 int hi=Integer.parseInt(p1);// hora inicial
					 int mi=Integer.parseInt(p2);// min inicial   p3 es tt
					 int lapso=Integer.parseInt(Lapso);//lapso
					 int xyz=0; 
					 int hf=Integer.parseInt(p1f);// hora final
					 int mf=Integer.parseInt(p2f);// min final   p3f es tt					
					 //validar las horas
					 int sw=0;
					 int c=0;
					 int cm=0;
					 int compara=hi;
					 while(sw==0){
						 c=c+1;
						 compara=compara+1;
						 if(compara==hf){sw=1;}
					 }
					 if(mi<mf){cm=mf-mi;}
					 if(mi>mf){cm=mf+mi; c=c-1;}
					 int suma=(((c*60)+cm)/lapso);
					 int xy=mi;
					 for (i=0; i<suma; i++){
						 contador=contador+1;						
						 if(contador==1){ 
							 out.print("<option value="+p1+":"+p2+":"+p3+">"+p1+":"+p2+":"+p3+"</option>");
						 }else{
							 xy=xy+lapso;
							 //Validaciones de las horas
							 int resta=xy/60;
							 if(xy>59){xy=(xy-(60*resta)); xyz=Integer.parseInt(p1)+resta; p1=String.valueOf(xyz);} //si sobrepasa una hora
							 if((p1.equals("13"))&&(p3.equals("AM"))){p1="1"; p3="PM";}
							 if((p1.equals("13"))&&(p3.equals("PM"))){p1="1"; p3="AM";}
							 if(p3.equals("AM")){
								 if(xy==0){
									 out.println("<option value="+p1+":00:"+p3+">"+p1+":00:"+p3+"</option>");
								 }else{
									 out.println("<option value="+p1+":"+xy+":"+p3+">"+p1+":"+xy+":"+p3+"</option>");
								 }
							 }
							 if(p3.equals("PM")){
								 if(xy==0){
									 out.println("<option value="+p1+":00:"+p3+">"+p1+":00:"+p3+"</option>");
								 }else{
									 out.println("<option value="+p1+":"+xy+":"+p3+">"+p1+":"+xy+":"+p3+"</option>");
								 }
							 }
						 }	
					 }*/
					
					/*out.println("</select></label></div></td></tr>");
					out.println("<tr><td width='11%'><div>Fecha</div></td><td width='43%'>");
					out.print("<table border>");
					out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio); 	*/	
					/*************************************************/
					/*out.print("<tr>");
					out.print("<th align=center>D");
					out.print("<th align=center>L");
					out.print("<th align=center>M");
					out.print("<th align=center>M");
					out.print("<th align=center>J");
					out.print("<th align=center>V");
					out.print("<th align=center>S");
					
					out.print("</tr>");
					out.print("<tr></tr>");
					columna=0;
					for(i=0;i<diaInicial;i++)* /*Bucle para la reserva de espacios de los días en el calendario*/
					/*{
						out.print("<td align=center><font size+=4>.");
						columna++;
						out.print("</font>");
					}
					int a=0;
					for(i=1;i<=nDias;i++)* /*Bucle para la inclusión del número de día en el calendario*/
				/*	{
						a=a+1;
						
						out.print("<td align=center>");

						if(i==dia) //Si el día a escribir es el día de hoy
					{
						//out.print("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el día de hoy
					}
					out.print(i);
					out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio+"-"+mes+"-"+i+" />");

					if(i==dia) //Si el día a escribir es el día de hoy
					{
						out.print("</b></font>"); //Cerramos la etiqueta de edición de las propiedades de las fuentes
					}

					columna++; //Pasamos a la columna siguiente.

					if (columna==7) //el numero de columnas que contiene el mes.
					{
						out.print("<tr>"); //Abrimos una nueva fila
					columna=0; //Reseteamos la variables de columnas.
					}
					}			
					out.print("</table>"); //Cerramos la tabla.
					*/
					/****************************************/
					/**ESPACIO PARA EL MES SIGUIENTE**/
					/*int diasMes1[]= new int[13];
					diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
					diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;
					
					int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;
					
					java.util.Calendar hoy1 = java.util.Calendar.getInstance();
					anio1=hoy1.get(java.util.Calendar.YEAR);
					dia1=1;
					mes1=hoy1.get(java.util.Calendar.MONTH)+1;
					
					if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0))* /*Compruebo que el año es bisiesto*/
					/*{
					diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
					}
					
					Calendar currentDate;
					currentDate = Calendar.getInstance(); 
					currentDate.set(anio1,mes1,dia1);
					
					int DiaSemanaEmpiezaMes=currentDate.getTime().getDay();
					
					mes1=mes1+1;
					if(mes1==1){NombreMes="Enero";}
					if(mes1==2){NombreMes="Febrero";}
					if(mes1==3){NombreMes="Marzo";}
					if(mes1==4){NombreMes="Abril";}
					if(mes1==5){NombreMes="Mayo";}
					if(mes1==6){NombreMes="Junio";}
					if(mes1==7){NombreMes="Julio";}
					if(mes1==8){NombreMes="Agosto";}
					if(mes1==9){NombreMes="Septiembre";}
					if(mes1==10){NombreMes="Octubre";}
					if(mes1==11){NombreMes="Noviembre";}
					if(mes1==12){NombreMes="Diciembre";}					
					if(mes1==13){
						mes1=1;
						anio1=anio1+1;
						NombreMes="Enero";
					}
					nDias1=diasMes1[mes1];
					out.print("<table border>");
					out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); *		
					/*************************************************/
					/*out.print("<tr>");
					out.print("<th align=center>D");
					out.print("<th align=center>L");
					out.print("<th align=center>M");
					out.print("<th align=center>M");
					out.print("<th align=center>J");
					out.print("<th align=center>V");
					out.print("<th align=center>S");					
					out.print("</tr>");
					out.print("<tr></tr>");
					columna1=0;
					//mes1=mes1+1;
					for(e=0;e<DiaSemanaEmpiezaMes;e++)* /*Bucle para la reserva de espacios de los días en el calendario*/
					/*{
						out.print("<td align=center><font size+=4>.");
						columna1++;
						out.print("</font>");
					}
					int a1=0;
					for(e=1;e<=nDias1;e++)* /*Bucle para la inclusión del número de día en el calendario*/
					/*{
						a1=a1+1;						
						out.print("<td align=center>");
						if(e==dia1) //Si el día a escribir es el día de hoy
					{
						//out.print("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el día de hoy
					}
					out.print(e);
					out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");

					if(e==dia1) //Si el día a escribir es el día de hoy
					{
						out.print("</b></font>"); //Cerramos la etiqueta de edición de las propiedades de las fuentes
					}

					columna1++; //Pasamos a la columna siguiente.

					if (columna1==7) //el numero de columnas que contiene el mes.
					{
						out.print("<tr>"); //Abrimos una nueva fila
					columna1=0; //Reseteamos la variables de columnas.
					}
					}			
					out.print("</table>"); //Cerramos la tabla.
					*
					/****************************************/
					//out.println("</td></tr>");
					
				/*	out.println("</tr><tr><td colspan='4'><div align='center'><input name='btnBuscar' type='button' value='     Buscar     ' onclick='buscarCoincidencias()' /></div><input name='txtContadorFecha' type='hidden' id='txtContadorFecha' value="+nDias1+" /></td></tr></table>");
				}
				else{
					out.print("1");
				}
				
				rs.getStatement().getConnection().close();
				 }
			 else{
				 out.print("Configure Los Parametros de Atencion.");
			 }
			 rs5.getStatement().getConnection().close();*
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
			
		if(va.equals("2")){
			String NombreMedico="";			
			out.print("<select name='cmbMedico' id='cmbMedico'><option value='Seleccione' selected='selected'>Seleccione</option>");
			
			try {
				if(CodigoEspecialidad.equals("Seleccione")){
					rs=mac.CargarTodosMedicos("");
					while(rs.next()){
						//NombreMedico=rs.getString(2)+" "+rs.getString(3);
						out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					}
					out.print("</select>");
					rs.getStatement().getConnection().close();
				}else{
					rs=mac.BuscarMedicoEspecialidad(CodigoEspecialidad);
					while(rs.next()){
						NombreMedico=rs.getString(2)+" "+rs.getString(3);
						out.print("<option value="+rs.getString(1)+">"+NombreMedico+"</option>");
					}
					out.print("</select>");
					rs.getStatement().getConnection().close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("3")){
			try {
				//int CantidadAtenciones;
				//rs1=mca.CantidadCitasMes(CodigoPaciente);
				//if(rs1.next()){
				//	CantidadAtenciones=rs1.getInt(1);
					//else{
						rs5=mca.RelacionCitas30Dias(CodigoPaciente);
						out.print("<div id='CantCit'><table width='100%' border='1'><tr><td colspan='5'><div align='center' style='font-size:large; color:#FF0000 ' >Historial de Atenciones Ultimo Mes</div></td></tr>");
						out.print("<tr style='font-size:medium; color:#0033FF; '><td width='31%'><div align='center'><strong>Nombre Paciente </strong></div></td><td width='26%'><div align='center'><strong>Medico</strong></div></td><td width='20%'><div align='center'><strong>Especialidad</strong></div></td><td width='15%'><div align='center'><strong>Fecha</strong></div></td><td width='8%'><div align='center'><strong>Estado</strong></div></td></tr>");
						while(rs5.next()){
							String Estado="";
							Estado=rs5.getString(5);
							if(Estado.equals("1")){
								//Estado=Asignado
								out.print("<tr><td>"+rs5.getString(1)+"</td><td>"+rs5.getString(2)+"</td><td>"+rs5.getString(3)+"</td><td>"+rs5.getString(4)+"</td><td>Cita Asignada</td></tr>");
							}
							if(Estado.equals("3")){
								//Estado=Atendido
								out.print("<tr><td>"+rs5.getString(1)+"</td><td>"+rs5.getString(2)+"</td><td>"+rs5.getString(3)+"</td><td>"+rs5.getString(4)+"</td><td>Atendido</td></tr>");
							}
							if(Estado.equals("4")){
								//Estado=Inasistencia
								out.print("<tr><td>"+rs5.getString(1)+"</td><td>"+rs5.getString(2)+"</td><td>"+rs5.getString(3)+"</td><td>"+rs5.getString(4)+"</td><td>Inasistente</td></tr>");
							}
								
						}
						out.print("</table></div>");
						rs5.getStatement().getConnection().close();
						if(op.equals("m")){rs3=mca.consultapormedico(CodigoMedico);rs4=mca.consultapormedico(CodigoMedico);}
						if(op.equals("e")){rs3=mca.consultaporespecialidad(CodigoEspecialidad);rs4=mca.consultaporespecialidad(CodigoEspecialidad);}
						if(op.equals("h")){rs3=mca.consultaporhora(hora);rs4=mca.consultaporhora(hora);}
						if(op.equals("f")){rs3=mca.consultaporfecha(fecha);rs4=mca.consultaporfecha(fecha);}
						if(op.equals("me")){rs3=mca.consultapormedicoespecialidad(CodigoMedico, CodigoEspecialidad);rs4=mca.consultapormedicoespecialidad(CodigoMedico, CodigoEspecialidad);}
						if(op.equals("mh")){rs3=mca.consultapormedicohora(CodigoMedico, hora);rs4=mca.consultapormedicohora(CodigoMedico, hora);}
						if(op.equals("mf")){rs3=mca.consultapormedicofecha(CodigoMedico, fecha);rs4=mca.consultapormedicofecha(CodigoMedico, fecha);}
						if(op.equals("eh")){rs3=mca.consultaporespecialidadhora(CodigoEspecialidad, hora);rs4=mca.consultaporespecialidadhora(CodigoEspecialidad, hora);}
						if(op.equals("ef")){rs3=mca.consultaporespecialidadfecha(CodigoEspecialidad, fecha);rs4=mca.consultaporespecialidadfecha(CodigoEspecialidad, fecha);}
						if(op.equals("hf")){rs3=mca.consultaporhorafecha(hora, fecha);rs4=mca.consultaporhorafecha(hora, fecha);}
						if(op.equals("meh")){rs3=mca.consultapormedicoespecialidadhora(CodigoMedico, CodigoEspecialidad, hora);rs4=mca.consultapormedicoespecialidadhora(CodigoMedico, CodigoEspecialidad, hora);}
						if(op.equals("mef")){rs3=mca.consultapormedicoespecialidadfecha(CodigoMedico, CodigoEspecialidad, fecha);rs4=mca.consultapormedicoespecialidadfecha(CodigoMedico, CodigoEspecialidad, fecha);}
						if(op.equals("mhf")){rs3=mca.consultapormedicohorafecha(CodigoMedico, hora, fecha);rs4=mca.consultapormedicohorafecha(CodigoMedico, hora, fecha);}
						if(op.equals("ehf")){rs3=mca.consultaporespecialidadhorafecha(CodigoEspecialidad, hora, fecha);rs4=mca.consultaporespecialidadhorafecha(CodigoEspecialidad, hora, fecha);}
						if(op.equals("mehf")){rs3=mca.consultamedicoespecialidadhorafecha(CodigoMedico, CodigoEspecialidad, hora, fecha);rs4=mca.consultamedicoespecialidadhorafecha(CodigoMedico, CodigoEspecialidad, hora, fecha);}
					
						if(rs4.next()){				
							out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center'>Resultado del Filtro</div></td></tr><tr><td width='36%'><div align='center'>Medico</div></td><td width='25%'><div align='center'>Especialidad</div></td><td width='17%'><div align='center'>Fecha y Hora </div></td><td width='22%'><div align='center'>Asignar/Cancelar</div></td></tr>");
							while(rs3.next()){
								String Phora="";String Pminuto="";String Pjornada="";
								String Anio="";String Mes="";String Dia="";
								String Hora=rs3.getString(2);String Fecha=rs3.getString(3);
								String EstadoHorario=rs3.getString(8);
								int y=Hora.split(":").length;
								String[] z=Hora.split(":");		     	
								for(int x=0; x<y-1; x=x+1)
								{ 
									Phora=z[0];Pminuto=z[1];Pjornada=z[2];
								}
								int h=Fecha.split("-").length;
								String[] d=Fecha.split("-");		     	
								for(int g=0; g<h-1; g=g+1)
								{ 
									Anio=d[0];Mes=d[1];Dia=d[2];
								}
								if(Pjornada.equals("AM")){Pjornada="0";}
								if(Pjornada.equals("PM")){Pjornada="1";}
								if(Pjornada.equals("MD")){Pjornada="2";}
								if(EstadoHorario.equals("0")){
									//out.print("<tr><td>"+rs3.getString(6)+" "+rs3.getString(7)+"</td><td>"+rs3.getString(5)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(2)+"</td><td onclick='UnClick("+Phora+","+Pminuto+","+Pjornada+","+rs3.getString(1)+","+Anio+","+Mes+","+Dia+","+rs3.getString(9)+","+rs3.getString(10)+")' bgcolor='#00CC33'>Click Para Asignar</td></tr>");
								}
								if(EstadoHorario.equals("1")){
									out.print("<tr><td>"+rs3.getString(6)+" "+rs3.getString(7)+"</td><td>"+rs3.getString(5)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(2)+"</td><td onclick='DosClick("+Phora+","+Pminuto+","+Pjornada+","+rs3.getString(1)+","+Anio+","+Mes+","+Dia+")'  bgcolor='#990000'>"+rs3.getString(4)+" (Click Para Cancelar)</td></tr>");
								}
							}//fin si rs3
							out.print("</table>");
							rs3.getStatement().getConnection().close();	
						}//fin si rs4
						else{
							out.print("NO SE ENCONTRARON REGISTROS.INTENTE NUEVAMENTE");
						}//fin else rs4
					//}
				//}
				//rs1.getStatement().getConnection().close();
				//////////////////////////////////////////////////////
				
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}//fin va=3
			
		
		if(va.equals("4")){
			/*ingreso de la cita paciente.*/
			//rs=mac.BuscarPacienteActivo(CodigoPaciente);
			//String est=null;
			/*try {
				if(rs.next()){
					est=rs.getString(3);
					if(est.equals("1")){
						out.print("Paciente Tiene Una Cita Activa.\nPara La Fecha: "+rs.getString(1)+" Hora "+rs.getString(2)+".\nCon El Medico "+rs.getString(4));
					}
					if(est.equals("2")){
						out.print("Paciente Tiene Una Cita Activa.\nPara La Fecha: "+rs.getString(1)+" Hora "+rs.getString(2)+".\nCon El Medico "+rs.getString(4));
					}
					
				}
				else{*/
			java.util.Date FechaAc = new java.util.Date();
			java.sql.Date Fecha_Insercion = new java.sql.Date(FechaAc.getTime());		
			java.sql.Time Hora_Insercion = new java.sql.Time(FechaAc.getTime());
			
					mac.AsignarCita(CodigoEspecialidad, codigoHorarioMedico, CodigoMedico, CodigoPaciente,UsuarioInsercion,FechaCita,Fecha_Insercion+"",Hora_Insercion+"","Via Click","Via Click");
					mac.ActualizarRegistroHorarioMedico(codigoHorarioMedico,NombrePaciente);
					mac.ActualizarHorasFechasMedicoIgual(HoraCita, FechaCita, CodigoMedico);
					out.print("1");
				/*}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.print("Error en ControlAsignarCita>>va.equals(4) "+e);
				e.printStackTrace();
			}*/
			
		}
		
		
		if(va.equals("5A")){
			String MotivoAnula=req.getParameter("MotivoAnula");
			rs=mac.Asignaciondecitaenlugarocupado(codigoHorarioMedico);
			try {
				if(rs.next()){						
						mac.CancelarCita(codigoHorarioMedico);
						mac.EliminarRegistroDeAsigancion(codigoHorarioMedico);
						java.util.Date FechaAc = new java.util.Date();
						java.sql.Date Fecha_Insercion = new java.sql.Date(FechaAc.getTime());		
						java.sql.Time Hora_Insercion = new java.sql.Time(FechaAc.getTime());
						mac.InsertarCitasCanceladas(codigoHorarioMedico, Fecha_Insercion+"", Hora_Insercion+"", UsuarioInsercion,rs.getString(7),rs.getString(9),rs.getString(10),MotivoAnula);
						out.print("1");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("5")){
			String MotivoAnula=req.getParameter("MotivoAnula");
			rs=mac.Asignaciondecitaenlugarocupado(codigoHorarioMedico);
			String PacEncontrado="";
			try {
				if(rs.next()){
					PacEncontrado=rs.getString(1);
					if(CodigoPaciente.equals(PacEncontrado)){
						out.print("1");
						mac.CancelarCita(codigoHorarioMedico);
						mac.EliminarRegistroDeAsigancion(codigoHorarioMedico);
						java.util.Date FechaAc = new java.util.Date();
						java.sql.Date Fecha_Insercion = new java.sql.Date(FechaAc.getTime());		
						java.sql.Time Hora_Insercion = new java.sql.Time(FechaAc.getTime());
						mac.InsertarCitasCanceladas(codigoHorarioMedico, Fecha_Insercion+"", Hora_Insercion+"", UsuarioInsercion,rs.getString(7),rs.getString(9),rs.getString(10),MotivoAnula);
						
					}
					else{
						out.print("No Se Puede Cancelar Esta Cita.\n Ingrese El Documento del Paciente Al cual Quiere Cancelar la Cita.");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}if(va.equals("6")){
			
			out.print("<table width='55%' border='0' ><tr><td width='26%'><div>Seleccione Medico </div></td><td width='74%'><div>    <select name='cmbMedico' id='cmbMedico'><option value='Seleccione' selected='selected'>Ver Todos</option>");
			rs=mmd.BuscarMedicoTodos();
			try {
				while(rs.next()){
					String nombre=rs.getString(2);
					String apellidos=rs.getString(3);
					String Completo=nombre+" "+apellidos;
					out.print("<option value="+rs.getString(1)+">"+Completo+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			out.print("</select></div></td></tr>");
			out.print("<tr><td><div>Seleccione Fecha </div></td><td><div>");
			out.print("<tr><td>DIA<select id='cmbDia'><option value='Seleccione' selected=''>Seleccione</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>" +
					"MES<select id='cmbMes'><option value='Seleccione'>Seleccione</option><option value='1'>Enero</option><option value='2'>Febrero</option><option value='3'>Marzo</option><option value='4'>Abril</option><option value='5'>Mayo</option><option value='6'>Junio</option><option value='7'>Julio</option><option value='8'>Agosto</option><option value='9'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select></td> " +
					"<td>AÑO<input type='text' id='txtAnio' ></td></tr>");
			out.print("</div></td></tr>");
			out.print("<tr><td colspan='2'><div align='center'><input name='btnGenerar' type='button' id='btnGenerar' value='Generar' onclick='Generar()' /></div></td></tr></table>");
		
		
		
		
		
		}
		if(va.equals("6--")){
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			java.util.Date primerDia= hoy.getTime();
			int diasMes[]= new int[12];
			diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
			diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;
			int dia;int anio;int columna;int nDias;int diaInicial;int i;int contador = 0;int mes;
			String NombreMes = "";
			anio=hoy.get(java.util.Calendar.YEAR);dia=hoy.get(java.util.Calendar.DATE);mes=hoy.get(java.util.Calendar.MONTH);
			mes=mes+1;
			if(mes==1){NombreMes="Enero";}if(mes==2){NombreMes="Febrero";}if(mes==3){NombreMes="Marzo";}
			if(mes==4){NombreMes="Abril";}if(mes==5){NombreMes="Mayo";}if(mes==6){NombreMes="Junio";}
			if(mes==7){NombreMes="Julio";}if(mes==8){NombreMes="Agosto";}if(mes==9){NombreMes="Septiembre";}
			if(mes==10){NombreMes="Octubre";}if(mes==11){NombreMes="Noviembre";}if(mes==12){NombreMes="Diciembre";}
			if(((anio%4==0) && (anio%100!=0))||(anio%400==0)){diasMes[1] = 29;}
			nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];primerDia.setDate(1);diaInicial=primerDia.getDay();
			out.print("<table width='55%' border='0' ><tr><td width='26%'><div>Seleccione Medico </div></td><td width='74%'><div>    <select name='cmbMedico' id='cmbMedico'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mmd.BuscarMedicoTodos();
			try {
				while(rs.next()){
					String nombre=rs.getString(2);
					String apellidos=rs.getString(3);
					String Completo=nombre+" "+apellidos;
					out.print("<option value="+rs.getString(1)+">"+Completo+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			out.print("</select></div></td></tr>");
			out.print("<tr><td><div>Seleccione Dia </div></td><td><div>");
			out.print("<table border>");
			out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio); 		
			/*************************************************/
			out.print("<tr>");
			out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
			out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
			out.print("</tr>");out.print("<tr></tr>");
			columna=0;
			for(i=0;i<diaInicial;i++){out.print("<td align=center><font size+=4>.");columna++;out.print("</font>");}
			int a=0;
			for(i=1;i<=nDias;i++){a=a+1;out.print("<td align=center>");
			out.print(i);
			out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio+"-"+mes+"-"+i+" />");
			if(i==dia){out.print("</b></font>");}
			columna++;
			if (columna==7){out.print("<tr>");columna=0;}
			}			
			out.print("</table>");
			/**************MES SIGUIENTE***************/
			/**ESPACIO PARA EL MES SIGUIENTE**/
			int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;
			
			int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;
			
			java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+1;
			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{
			diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}
			
			Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);
			
			int DiaSemanaEmpiezaMes=currentDate.getTime().getDay();
			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}
			if(mes1==2){NombreMes="Febrero";}
			if(mes1==3){NombreMes="Marzo";}
			if(mes1==4){NombreMes="Abril";}
			if(mes1==5){NombreMes="Mayo";}
			if(mes1==6){NombreMes="Junio";}
			if(mes1==7){NombreMes="Julio";}
			if(mes1==8){NombreMes="Agosto";}
			if(mes1==9){NombreMes="Septiembre";}
			if(mes1==10){NombreMes="Octubre";}
			if(mes1==11){NombreMes="Noviembre";}
			if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){
				mes1=1;
				anio1=anio1+1;
				NombreMes="Enero";
			}
			nDias1=diasMes1[mes1];
			out.print("<table border>");
			out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");
			out.print("<th align=center>D");
			out.print("<th align=center>L");
			out.print("<th align=center>M");
			out.print("<th align=center>M");
			out.print("<th align=center>J");
			out.print("<th align=center>V");
			out.print("<th align=center>S");					
			out.print("</tr>");
			out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{
				out.print("<td align=center><font size+=4>.");
				columna1++;
				out.print("</font>");
			}
			int a1=0;
			for(e=1;e<=nDias1;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{
				a1=a1+1;						
				out.print("<td align=center>");
				if(e==dia1) //Si el día a escribir es el día de hoy
			{
				//out.print("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el día de hoy
			}
			out.print(e);
			out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");

			if(e==dia1) //Si el día a escribir es el día de hoy
			{
				out.print("</b></font>"); //Cerramos la etiqueta de edición de las propiedades de las fuentes
			}

			columna1++; //Pasamos a la columna siguiente.

			if (columna1==7) //el numero de columnas que contiene el mes.
			{
				out.print("<tr>"); //Abrimos una nueva fila
			columna1=0; //Reseteamos la variables de columnas.
			}
			}			
			out.print("</table>"); //Cerramos la tabla.
			
			/****************************************
			*
			*FIN MES SIGUIENTE
			*
			****************************************/
			out.print("</div></td></tr>");
			out.print("<tr><td colspan='2'><div align='center'><input name='btnGenerar' type='button' id='btnGenerar' value='Generar' onclick='Generar()' /></div></td></tr></table>");
		
		
		
		
		
		}
		
		if(va.equals("77")){
			out.print("<table width='100%' border='1'>");					
			out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Formulacion Medicamentos e Insumos</span></div><input name='txtTipo' type='hidden' id='txtTipo' value='0' /></td></tr>");
			out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' onkeyup='ActualizaDetalleOrden()' ></textarea></td></tr>");
			out.print("<tr><td width='11%'>Medicamento</td><td width='89%'><input name='txtMedicamento' type='text' id='txtMedicamento' size='70' onKeyUp='autocompletarMedicamentoFormulaCE()' /></td></tr>");
			out.print("<tr><td><input name='txtCodigoMed' type='hidden' id='txtCodigoMed' size='18' /></td><td><div id='SugerenciaMedFormulaCE'></div></td></tr>");
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
		
		if(va.equals("7.1")){
			rs=mac.ListaDeMedicosCitasFecha(fecha);			
			String NombreMedico="";String Especialidad="";String CodigoMed="";
			String Anio="";String Mes="";String Dia="";String NomMes="";
			try {
				int h=fecha.split("-").length;
				String[] d=fecha.split("-");		     	
				for(int g=0; g<h-1; g=g+1)
				{ 
					Anio=d[0];Mes=d[1];Dia=d[2];
				}
				if(Mes.equals("1")){NomMes="Enero";}if(Mes.equals("2")){NomMes="Febrero";}
				if(Mes.equals("3")){NomMes="Marzo";}if(Mes.equals("4")){NomMes="Abril";}
				if(Mes.equals("5")){NomMes="Mayo";}if(Mes.equals("6")){NomMes="Junio";}
				if(Mes.equals("7")){NomMes="Julio";}if(Mes.equals("8")){NomMes="Agosto";}
				if(Mes.equals("9")){NomMes="Septiembre";}if(Mes.equals("10")){NomMes="Octubre";}
				if(Mes.equals("11")){NomMes="Noviembre";}if(Mes.equals("12")){NomMes="Diciembre";}
				out.print("<table width='100%' border='1'><tr bgcolor='#E6E6E6' ><td><div align='center' >Citas Para el Dia "+Dia+" de "+NomMes+" del "+Anio+"</div></td></tr></table>");
				int CitasTo=0;
				while(rs.next()){					
					NombreMedico=rs.getString("NombreMedico");
					Especialidad=rs.getString("nombre_especialidad");
					CodigoMed=rs.getString("codigo");
					out.print("<table width='100%' border='1'><tr bgcolor='#E6E6E6'><td width='13%'><div>Nombre Medico </div></td><td width='87%'><div>"+NombreMedico+"("+Especialidad+")</div></td></tr></table>");
					
					out.print("<table width='100%' border='1'><tr><td width='2%'>N.</td><td width='8%'><div align='center'>Hora</div></td><td width='15%'><div>Identificacion </div></td><td width='25%'><div>Nombre Paciente </div></td><td width='20%'>Telefonos</td><td width='30%'><div>Entidad </div></td><td>HC-e</td></tr>");
					rs1=mac.ListaDeCitas(CodigoMed, fecha);
					int Con=0;
					while(rs1.next()){
						Con=Con+1;
						CitasTo=CitasTo+1;
							out.print("<tr><td>"+Con+"</td><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(9)+"</td><td>"+rs1.getString(8)+"</td>");
							rs2=mac.PacientesConHC(rs1.getString("pac_codigo_paciente"));
							if(rs2.next()){
								out.print("<td>Si</td></tr>");
							}else{
								out.print("<td>No</td></tr>");
							}
							rs2.getStatement().getConnection().close();
					}
					//out.print("<tr><td colspan='5'><div align='center'><input name='txtFecha' type='hidden' id='txtFecha' value='"+fecha+"'><input name='txtCodigoMedico' type='hidden' id='txtCodigoMedico' value="+CodigoMedico+"><input name='btnImprimir' type='button' id='btnImprimir' value='Imprimir' onClick='Imprimir()'></div></td></tr></table>");
					out.print("</table>");
					rs1.getStatement().getConnection().close();
		
				}
				out.print("<table><tr><td>Total de Citas:"+CitasTo+"</td></tr></table>");
				rs.getStatement().getConnection().close();
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		if(va.equals("7")){
			rs=mac.ListaDeCitas(CodigoMedico, fecha);
			rs1=mac.ListaDeCitas(CodigoMedico, fecha);
			String NombreMedico="";String Especialidad="";
			String Anio="";String Mes="";String Dia="";String NomMes="";
			try {
				if(rs.next()){
					
					NombreMedico=rs.getString(5);
					Especialidad=rs.getString(4);
				}
				rs.getStatement().getConnection().close();
				int h=fecha.split("-").length;
				String[] d=fecha.split("-");		     	
				for(int g=0; g<h-1; g=g+1)
				{ 
					Anio=d[0];Mes=d[1];Dia=d[2];
				}
				if(Mes.equals("1")){NomMes="Enero";}if(Mes.equals("2")){NomMes="Febrero";}
				if(Mes.equals("3")){NomMes="Marzo";}if(Mes.equals("4")){NomMes="Abril";}
				if(Mes.equals("5")){NomMes="Mayo";}if(Mes.equals("6")){NomMes="Junio";}
				if(Mes.equals("7")){NomMes="Julio";}if(Mes.equals("8")){NomMes="Agosto";}
				if(Mes.equals("9")){NomMes="Septiembre";}if(Mes.equals("10")){NomMes="Octubre";}
				if(Mes.equals("11")){NomMes="Noviembre";}if(Mes.equals("12")){NomMes="Diciembre";}
				out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Citas</div></td></tr>");
				out.print("<tr><td width='13%'><div>Nombre Medico </div></td><td width='87%'><div>"+NombreMedico+"</div></td></tr>");
				out.print("<tr><td><div>Especialidad</div></td><td><div>"+Especialidad+"</div></td></tr>");
				out.print("<tr><td colspan='2'><div align='center'>"+Dia+" de "+NomMes+" del "+Anio+"</div></td></tr></table>");
				
				
				out.print("<table width='100%' border='1'><tr><td><div align='center'>Hora</div></td><td><div>Identificacion </div></td><td><div>Nombre Paciente </div></td><td>Telefonos</td><td><div>Entidad </div></td><td>HC-e</td></tr>");
				while(rs1.next()){
					//String estado=rs1.getString(6);
					//if(estado.equals("1")){
						out.print("<tr><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(9)+"</td><td>"+rs1.getString(8)+"</td>");
						rs=mac.PacientesConHC(rs1.getString("pac_codigo_paciente"));
						if(rs.next()){
							out.print("<td>Si</td></tr>");
						}else{
							out.print("<td>No</td></tr>");
						}
						rs.getStatement().getConnection().close();
						//}
					/*if(estado.equals("0")){
						out.print("<tr><td>"+rs1.getString(1)+"</td><td>Libre</td></tr>");
					}*/
					
				}
				out.print("<tr><td colspan='5'><div align='center'><input name='txtFecha' type='hidden' id='txtFecha' value='"+fecha+"'><input name='txtCodigoMedico' type='hidden' id='txtCodigoMedico' value="+CodigoMedico+"><input name='btnImprimir' type='button' id='btnImprimir' value='Imprimir' onClick='Imprimir()'></div></td><td></td></tr></table>");
				rs1.getStatement().getConnection().close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("8.1")){
			//
			//
		/*	out.print("<table width='100%' border='1'><tr><td width='93'>Cantidad</td><td width='146'><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
			out.print("<td width='58'>Lapso</td><td width='154'><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
			out.print("<td width='106'>Administraci&oacute;n</td><td width='149'><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otro'>Otro</option></select></td>");
			out.print("<td width='166'>Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='10' maxlength='10' readonly='' /></td><td width='166'></td></tr>");
			out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' ></textarea><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarMediCE()' /></td></tr>");
			out.print("</table>");*/
			
			out.print("<table width='100%' border='1'><tr style='visibility:hidden'><td width='93' style='visibility:hidden'>Cantidad</td><td width='146' style='visibility:hidden'><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
			out.print("<td width='58' style='visibility:hidden'>Lapso</td><td width='154' style='visibility:hidden'><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' style='visibility:hidden' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
			out.print("<td width='106' style='visibility:hidden'>Administraci&oacute;n</td><td width='149' style='visibility:hidden'><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otro'>Otro</option></select></td>");
			out.print("<td width='166'>Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidada' type='text' id='txtCantidada' size='10' maxlength='10' readonly='' /></td><td width='166'></td></tr>");
			out.print("<tr><td width='166'>Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='10' maxlength='10' /></td><td width='166'>Dias&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtDias' type='text' id='txtDias' size='10' /></td><td>Observacion</td><td colspan='6'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' ></textarea><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarMediCE()' /></td></tr>");
			out.print("</table>");
		}
		if(va.equals("8")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarConsolidado()'></td></tr></table>");
		}
		
		
		String codFormulacion_fk=req.getParameter("codFormulacion_fk");
		String codPac=req.getParameter("codPac");
		String codCita=req.getParameter("codCita");
		String DetOrden=req.getParameter("DetOrden");
		String codigoMed=req.getParameter("codigoMed");
		String cantidad=req.getParameter("cantidad");
		String dosificacion=req.getParameter("dosificacion");
		String observacion=req.getParameter("observacion");
		String codDetFormulacion_fk=req.getParameter("codDetFormulacion_fk");
		String usuario=req.getParameter("usuario");
		if(va.equals("99")){
			//String hora=req.getParameter("hora");
			//String fecha=req.getParameter("fecha");
			/*String codPac,String codCita,
			String observacion,String estado,String usuario,
			Time hora,Date fecha*/
			
			
			java.util.Date fechaActual18 = new java.util.Date();
			java.sql.Date fecha1 = new java.sql.Date(fechaActual18.getTime());		
			java.sql.Time hora1 = new java.sql.Time(fechaActual18.getTime());	
			String estado="";
			
			if(codFormulacion_fk==""){
				try {
					estado="-1";
					mvf.CrearFormulacionCE(codPac, codCita, DetOrden, estado, usuario, hora1, fecha1);
					rs=mvf.ObtenerCodigoFormulacionCE(hora1, fecha1);
					if(rs.next()){
						codFormulacion_fk=rs.getString(1);
					}
					rs.getStatement().getConnection().close();	
					mvf.CrearDetalleFormulacionCE(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado);
					rs1=mvf.ObtenerCodigoDetalleFormulacionCE(codFormulacion_fk, codigoMed);
					/*if(rs1.next()){
						codDetFormulacion_fk=rs1.getString(1);
					}
					//mvf.CrearDispenacion(codDetFormulacion_fk);
					rs1.getStatement().getConnection().close();	*/					
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='ConteForm'>");
					out.print("<table width='100%' >");
					out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='DetFormula'>");
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
					rs2=mvf.DetalledeFormulacionCE(codFormulacion_fk);
					while(rs2.next()){
						out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs2.getString(5)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacionCE()' ></div></td></tr></table>");
					out.print("</div>");
					rs2.getStatement().getConnection().close(); 					
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
					mvf.CrearDetalleFormulacionCE(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado);
					rs3=mvf.ObtenerCodigoDetalleFormulacionCE(codFormulacion_fk, codigoMed);
					if(rs3.next()){
						codDetFormulacion_fk=rs3.getString(1);
					}
					mvf.CrearDispenacion(codDetFormulacion_fk);
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
					rs4=mvf.DetalledeFormulacionCE(codFormulacion_fk);
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
		
		if(va.equals("100")){
			//finalizar la formulacion.			
			try {
				mvf.ActivarDetalleFormulacionCE(codFormulacion_fk);
				mvf.ActivarFormulacionCE(codFormulacion_fk);			
				rs=mvf.ObtenerEstadoFormulacionCE(codFormulacion_fk);
				if(rs.next()){
					mvf.ActivarDetalleFormulacionCE(codFormulacion_fk);
					mvf.ActivarFormulacionCE(codFormulacion_fk);
					out.print("Error Al Actualizar Registros.");
				}
				else{
					out.print("1");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("9")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarDetallado()'></td></tr></table>");
		}
		
		if(va.equals("9.1")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarMedicoEps()'></td></tr></table>");
		}
		if(va.equals("9.2")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarInasistenciaEps()'></td></tr></table>");
		}
		if(va.equals("9.3")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarConcurrenciaPaciente()'></td></tr></table>");
		}
		
		if(va.equals("RC")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarTotalReciboCaja()'></td></tr></table>");
		}
		if(va.equals("RC1")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarDetalladoReciboCaja()'></td></tr></table>");
		}
		
		if(va.equals("RC2")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''><input name='btngenerar' type='button' id='btngenerar' class='boton4' value='Generar' onClick='GenerarDetalladoReciboEgreso()'></td></tr></table>");
		}
		
		/*************************************************************************************/
		/******************************ORDEN DE SERVICIO*************************************/
		
		if(va.equals("1O")){
			
			try {			
				
				String conso="";
				String cons="";
				String consn="";
				rs = mca.BuscarConsecutivoOrden();
					if(rs.next()){
						//out.print(rs.getString(1)+"|"+rs.getString(2));
						if(rs.getString("consecutivo").length()==1){cons=("00000000"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==2){cons=("0000000"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==3){cons=("000000"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==4){cons=("00000"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==5){cons=("0000"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==6){cons=("000"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==7){cons=("00"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==8){cons=("0"+rs.getString("consecutivo"));}
						if(rs.getString("consecutivo").length()==9){cons=rs.getString("consecutivo");}
						consn=rs.getString("sigla")+"-"+cons;
						conso=rs.getString("consecutivo");
					}
					rs.getStatement().getConnection().close();
					
					
					//se actualiza el consecutivo del documento
					int ctan=Integer.parseInt(conso)+1;
					conso=String.valueOf(ctan);
					mca.ActualizarConsecutivoOrdenServicio(conso);
				
				out.print("<table border='0' width='100%' ><tr><td><div align='center' class='style11' id='cabecera2'>ORDEN DE SERVICIO "+consn+"<input name='txtConsecutivoOrden' type='hidden' id='txtConsecutivoOrden' value="+consn+" ><select id='cmbTipoOrden'><option value='Seleccione'>Seleccione</option><option value='Hospitalizacion'>Hospitalizacion</option><option value='Urgencia'>Urgencia</option><option value='Consulta Externa'>Consulta Externa</option></select></div></td></tr></table>");
				
				String Tip_Documento =req.getParameter("TipoDocumento");
				String Numero_Documento =req.getParameter("NumeroDocumento");
				rs=mca.BuscarPacienteOrden(Tip_Documento, Numero_Documento);
				if(rs.next()){
					out.print("<table border='0' width='100%' ><tr><td width='13%'  class='style12' >PACIENTE <input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString("CodPac")+"  style='width:100%'></td><td width='21%'>"+rs.getString("Paciente")+"</td><td width='3%'  class='style12'>EDAD</td><td width='8%'>"+rs.getString("edad")+" AÑOS</td><td width='7%'  class='style12'>DOCUMENTO</td><td width='15%' >"+rs.getString("Documento")+"</td><td width='6%'  class='style12'>ENTIDAD</td><td colspan='2'>"+rs.getString("entidad")+"</td></tr>");
					
					out.print("<tr class='style12'><td > <input name='txtCodMaEntiPac' type='hidden' id='txtCodMaEntiPac' value="+rs.getString("cod_manual_tarifario")+" /> ENTIDAD PRESTADORA <input name='txtCodMaTr' type='hidden' id='txtCodMaTr' /><input name='txtCodEntidadPrestadora' type='hidden' id='txtCodEntidadPrestadora' /><input name='txtNitEntidadPrestadora' type='hidden' id='txtNitEntidadPrestadora' /><input name='txtTelEntiPrestadora' type='hidden' id='txtTelEntiPrestadora' /><input name='txtDireEntiPrestadora' type='hidden' id='txtDireEntiPrestadora' /></td><td colspan='3'><input name='txtEntidad' type='text' id='txtEntidad' onkeyup='CargarEntidades()'  style='width:100%'></td><td>N° ORDEN </td><td><input name='txtNumOrden' type='text' id='txtNumOrden' /></td><td>OBSERVACION</td><td colspan='2'><input name='txtObservacion' type='text' id='txtObservacion'  style='width:100%'></td></tr>");
					
					out.print("<tr><td class='style12' >&nbsp;</td><td colspan='3' class='style12'><div id='autoentidad'>nombre entidad</div></td><td class='style12' align='center'>&nbsp;</td><td class='style12' align='center'>&nbsp;</td><td class='style12' align='center'>&nbsp;</td><td colspan='2' align='center' class='style12'>&nbsp;</td><td width='0%' align='center' class='style12'>&nbsp;</td></tr>");
					
					out.print("<tr><td class='style12' >SERVICIO</td><td colspan='3' class='style12'><input name='txtServicio' type='text' id='txtServicio' onkeyup='CargarProgramas()'  style='width:100%'><input name='txtCodCups1' type='hidden' id='txtCodCups1' /><input name='txtCodReferencia1' type='hidden' id='txtCodReferencia1' /></td><td class='style12'>CANTIDAD</td><td class='style12'><input name='txtCantidad' type='text' id='txtCantidad' value='1'  style='width:100%'></td><td class='style12'>VALOR</td><td width='16%' class='style12'><input name='txtValorPrograma' type='text' id='txtValorPrograma'  style='width:100%'><input name='txtCodigoPrograma' type='hidden' id='txtCodigoPrograma'  style='width:100%'></td><td width='11%' class='style12'><input type='button' name='Button' onclick='AsignarOrden()' value='        Asignar        '></td><td class='style12'>&nbsp;</td></tr>");
					
					out.print("<tr><td class='style12' >&nbsp;</td><td colspan='3'  class='style12'><div id='autoservi'>nombre servicio </div></td><td class='style12' >&nbsp;</td><td class='style12' >&nbsp;</td><td class='style12' >&nbsp;</td><td colspan='2'  class='style12'>&nbsp;</td><td class='style12' >&nbsp;</td></tr>");
					
					out.print("<tr><td colspan='9'  ><div id='DetalleOrden'>");
					
					out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value='0' ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR UNIT</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
					
					out.print("<tr  class='style12'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
					
					out.print("</table></div></td></tr>");

					out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button' id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
					
					out.print("");
					
					out.print("");
					
				}else{
					out.print("REGISTRO NO ENCONTRADO");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		if(va.equals("AOS")){
						
			String CodigoPrograma=req.getParameter("CodigoPrograma");
			String CodCups=req.getParameter("CodCups");
			String CodReferencia=req.getParameter("CodReferencia");
			String CodEnt=req.getParameter("CodEnt");
			String CodPaciente=req.getParameter("CodPac");
			String Servicio=req.getParameter("Servicio");
			String ValorPrograma=req.getParameter("ValorPrograma");
			String NumOrden=req.getParameter("NumOrden");
			String Observacion=req.getParameter("Observacion");
			String Cantidad=req.getParameter("Cantidad");
			String CodOrden=req.getParameter("CodOrden");
			String Codusuario=req.getParameter("Codusuario");
			String ConsecutivoOrden=req.getParameter("ConsecutivoOrden");
			String NomEntidad=req.getParameter("NomEntidad");
			String DireEntiPrestadora=req.getParameter("DireEntiPrestadora");
			String TelEntiPrestadora=req.getParameter("TelEntiPrestadora");
			String NitEntidadPrestadora=req.getParameter("NitEntidadPrestadora");
			String servicio=req.getParameter("servicio");
			
			java.util.Date FechaAc1 = new java.util.Date();
			java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
			java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
			try {
				//1.) validar si es un movimiento nuevo o no
				if(CodOrden.equals("0")){
					//2.) se hace la insercion en la tabla fact_orden_servicio
					//System.out.print("CodPac_fk="+CodPac_fk+" CodEnt_fk="+CodEnt_fk+" consecutivo="+consecutivo+" orden "+orden+" observacion="+observacion+" fecha_insercion="+fecha_insercion+" hora_insercion ="+hora_insercion+" usuario_insercion="+usuario_insercion+" CodAdm_fk="+CodAdm_fk+" fecha_validacion="+fecha_validacion);
					java.sql.Date Fecha_Validacion = new java.sql.Date(FechaAc1.getTime());	
					rs=mca.BuscarOrdenesVencidas();
					while(rs.next()){
						mca.FinalizarOrdenServicio(rs.getString("codigo"),"3");
					}
					rs.getStatement().getConnection().close();
					rs=mca.FechaVencimiento();
					String FechaVencimiento="";
					if(rs.next()){
						FechaVencimiento=rs.getString("ProximaFecha");
					}
					rs.getStatement().getConnection().close();
					mca.GuardarOrdenServicio(CodPaciente, CodEnt, ConsecutivoOrden, NumOrden, Observacion, Fecha_Insercion1+"", Hora_Insercion1+"", Codusuario, "0", FechaVencimiento+"",
							NomEntidad, NitEntidadPrestadora, DireEntiPrestadora, TelEntiPrestadora,servicio);
					//3.) se consulta el codigo de la orden
					rs=mca.BuscarCodigoOrden(Fecha_Insercion1+"", Hora_Insercion1+"");
					String CodOrden_fk="";
					if(rs.next()){
						CodOrden_fk=rs.getString("codigo");
						//4.) se inserta el detalle de la orden de servicio en la tabla fact_detalle_orden_servicio
						
						mca.GuardarDetalleOrdenServicio(CodOrden_fk, Servicio, CodCups, CodReferencia, CodigoPrograma, Cantidad, ValorPrograma);
						
						out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value="+CodOrden_fk+" ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR UNIT</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
						
						rs1=mca.ObtenerDetalleOrdenServicio(CodOrden_fk);
						while(rs1.next()){
							int ValorTotal=(Integer.parseInt(rs1.getString("cantidad")))*(Integer.parseInt(rs1.getString("valor")));
							out.print("<tr class='style12'><td>"+rs1.getString("CodCups")+"</td>" +
									"<td>"+rs1.getString("Nombre_Programa")+"</td>" +
											"<td>"+rs1.getString("cantidad")+"</td>" +
													"<td>"+rs1.getString("valor")+"</td>" +
													"<td>"+ValorTotal+"</td>" +
															"<td><a href='#' onclick='AnularMovimiento("+rs1.getString("codigo")+")' >Omitir</a></td></tr>");
						}
						rs1.getStatement().getConnection().close();					
						
						out.print("</table>");

					}else{ 
						out.print("No se pudo generar la orden de servicio, verifique los datos.");
					}
					rs.getStatement().getConnection().close();
				}else{
					//4.) se inserta el detalle de la orden de servicio en la tabla fact_detalle_orden_servicio
					mca.GuardarDetalleOrdenServicio(CodOrden, Servicio, CodCups, CodReferencia, CodigoPrograma, Cantidad, ValorPrograma);
					out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value="+CodOrden+" ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
					rs1=mca.ObtenerDetalleOrdenServicio(CodOrden);
					while(rs1.next()){
						int ValorTotal=(Integer.parseInt(rs1.getString("cantidad")))*(Integer.parseInt(rs1.getString("valor")));
						out.print("<tr class='style12'><td>"+rs1.getString("CodCups")+"</td>" +
								"<td>"+rs1.getString("Nombre_Programa")+"</td>" +
										"<td>"+rs1.getString("cantidad")+"</td>" +
												"<td>"+rs1.getString("valor")+"</td>" +
												"<td>"+ValorTotal+"</td>" +
														"<td><a href='#' onclick='AnularMovimiento("+rs1.getString("codigo")+")' >Omitir</a></td></tr>");
					}
					rs1.getStatement().getConnection().close();					
					out.print("</table>");
				}
				/*
				 * poner los campos vacios apenas se asigne--OK
				poner anular un detalle--OK
				finaliozar la orden -- OK
				arrojar el reporte 
				
				actualizar el campo de admision en la tabla fact_orden_servicio--OK
				hacer formulario de consultar ordenes
				hacer formulario de anular ordenes.*/
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("FOS")){
			String CodOrden=req.getParameter("CodOrden");
			//String CodMaTrEmpServ=req.getParameter("CodMaTrEmpServ");
			String CodMaEntiPac=req.getParameter("CodMaEntiPac");
			String CodPacF=req.getParameter("CodPac");
			String NumOrden=req.getParameter("NumOrden");
			String Codusuario=req.getParameter("Codusuario");
			String NomUsu=req.getParameter("NomUsu");
			String CodCups="";
			mca.FinalizarOrdenServicio(CodOrden,"1");
			//out.print("Orden Generada Satisfactoriamente.");
			MetodoAdmision ma= new MetodoAdmision();
			rs1=mca.ObtenerDetalleOrdenServicio(CodOrden);
			try {
				//generar admision --OK
				java.util.Date FechaAc1 = new java.util.Date();
				java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
				java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
				ma.insertarAdmision(NomUsu,Fecha_Insercion1+"",Hora_Insercion1,CodPacF,NumOrden);
				ma.InsertarContactoPaciente("1", CodPacF);
				//encabezado de la orden -- OK
				
				String CodAdmision="";
				rs=ma.obtenerCodigoAdmision(Fecha_Insercion1+"",Hora_Insercion1+"",CodPacF);
				if(rs.next()){
					CodAdmision=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				//ingresar diagnostico inicial
				//ingresar diagnostico final
				//ingresar hic_destino_paciente
				//**
				ResultSet rsc=null;
				String cod_eps = "", razon_social = "",
				 nit = "", direccion = "",
				telefono = "",ciudad = "",nombre_paciente = "",documentoP = "",
				direccion_p = "",telefono_p = "",
				tipo_afiliacion = "",estrato = "",fecha_ingreso = "",num_autorizacion="",				
				poliza = null;
				String ViaIng="4";
				
				rsc=ma.InsertarEncabezadoCex(CodAdmision);
				if(rsc.next()){
					cod_eps=rsc.getString(1);
	        		razon_social=rsc.getString(2);
	        		nit=rsc.getString(3); 
	        		direccion=rsc.getString(4);
					telefono=rsc.getString(5);
					ciudad=rsc.getString(6);
					nombre_paciente=rsc.getString(7);
					documentoP=rsc.getString(8);
					direccion_p=rsc.getString(9);
					telefono_p=rsc.getString(10);
					tipo_afiliacion=rsc.getString(11);
					estrato=rsc.getString(12);
					fecha_ingreso=rsc.getString(13);
					num_autorizacion=rsc.getString(14);
					poliza=rsc.getString(15);
				}
					ma.InserEmcabeCXE(cod_eps, razon_social, nit, direccion, telefono, ciudad, nombre_paciente, documentoP, direccion_p, telefono_p, tipo_afiliacion, estrato, fecha_ingreso, CodAdmision, num_autorizacion,poliza,ViaIng,"0","0");
					rsc.getStatement().getConnection().close();
					String CodEncFactura="";
					rs=ma.ObtenerCodigoEncabezado(CodAdmision);
					if(rs.next()){
						CodEncFactura=rs.getString(1);
						ma.InsertarOrdenAdmision(CodOrden, CodAdmision,CodEncFactura);
					}
					rs.getStatement().getConnection().close();
				/*************************************************************************************************/
				while(rs1.next()){
					CodCups=rs1.getString("CodCups");
					String Canti=rs1.getString("cantidad");
					rs=mca.ObtenerDetalleFacturaOrdenServicio(CodCups, CodMaEntiPac);
					if(rs.next()){											
						ma.CrearDetalleFactura(Fecha_Insercion1+"",Hora_Insercion1+"", 
								rs.getString("cod_programa"), CodCups, 
								rs.getString("Programa"), rs.getString("ClaseServicio"), 
								Fecha_Insercion1+"", Canti, rs.getString("valor"), Codusuario,
								CodEncFactura, "0", rs.getString("subcentro_costo"));
					}
					rs.getStatement().getConnection().close();					
				}
				rs1.getStatement().getConnection().close();	
				out.print(CodOrden);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("ODOS")){
			try{
				String codigodetalle=req.getParameter("codigo");
				String CodOrden=req.getParameter("CodOrden");
				mca.OmitirDetalleOrdenServicio(codigodetalle);
				
				out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value="+CodOrden+" ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
				
				rs1=mca.ObtenerDetalleOrdenServicio(CodOrden);
				while(rs1.next()){
					int ValorTotal=(Integer.parseInt(rs1.getString("cantidad")))*(Integer.parseInt(rs1.getString("valor")));
					out.print("<tr class='style12'><td>"+rs1.getString("CodCups")+"</td>" +
							"<td>"+rs1.getString("Nombre_Programa")+"</td>" +
									"<td>"+rs1.getString("cantidad")+"</td>" +
											"<td>"+rs1.getString("valor")+"</td>" +
											"<td>"+ValorTotal+"</td>" +
													"<td><a href='#' onclick='AnularMovimiento("+rs1.getString("codigo")+")' >Omitir</a></td></tr>");
				}
				rs1.getStatement().getConnection().close();					
			
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("BENT")){			
			String Entidad=req.getParameter("Entidad");			
			try {				
				int conen=0;
				String CodEnt="";
				String ManualTari="";
				String NomEnt="";
				String NumeroNit="";
				String DireccionEn="";
				String TelefonoEnt="";
				rs=mca.BuscarEntidad(Entidad);
				out.print("<table>");
				while(rs.next()){
					conen=conen+1;
					CodEnt=rs.getString("ent_nit");
					ManualTari=rs.getString("cod_manualTarifario");
					NomEnt=rs.getString("nombre_entidad");
					NumeroNit=rs.getString("ent_nit_contratante");
					DireccionEn=rs.getString("direccion");
					TelefonoEnt=rs.getString("telefono");
					
					out.print("<tr><td><a href='#' onclick='AsignarEntidad("+CodEnt+",&quot;"+NomEnt+"&quot;,"+ManualTari+",&quot;"+NumeroNit+"&quot;,&quot;"+DireccionEn+"&quot;,&quot;"+TelefonoEnt+"&quot;)'>"+NomEnt+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorEnt' type='hidden' id='txtContadorEnt'   value="+conen+" /></td>" +
						"<td><input name='txtCodEnt' type='hidden' id='txtCodEnt' value="+CodEnt+" /></td>" +
						"<td><input name='txtCodMT' type='hidden' id='txtCodMT' value="+ManualTari+" /></td>" +
						"<td><input name='txtNomEnt' type='hidden' id='txtNomEnt' value='"+NomEnt+"' /></td>" +
						
						"<td><input name='txtNumeroNit' type='hidden' id='txtNumeroNit' value='"+NumeroNit+"' /></td>" +
						"<td><input name='txtDireccionEnt' type='hidden' id='txtDireccionEnt' value='"+DireccionEn+"' /></td>" +
						"<td><input name='txtTelefonoEnt' type='hidden' id='txtTelefonoEnt' value='"+TelefonoEnt+"' /></td>" +
						
								"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("BPRO")){			
			String Programa=req.getParameter("Programa");	
			String CodManual=req.getParameter("CodManual");
			try {				
				int conpr=0;
				
				String CodPrograma="";
				String NomPrograma="";
				String CodCups="";
				String CodReferencia="";
				String valor="";
				
				rs=mca.BuscarPrograma(Programa,CodManual);
				out.print("<table>");
				while(rs.next()){
					conpr=conpr+1;
					
					CodPrograma=rs.getString("cod_programa");
					NomPrograma=rs.getString("descripcion");
					CodCups=rs.getString("cod_cups");
					CodReferencia=rs.getString("cod_referencia");
					valor=rs.getString("valor");
					
					out.print("<tr><td><a href='#' onclick='AsignarPrograma("+CodPrograma+",&quot;"+NomPrograma+"&quot;,&quot;"+CodCups+"&quot;,&quot;"+CodReferencia+"&quot;,"+valor+")'>"+NomPrograma+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorPro' type='hidden' id='txtContadorPro'   value="+conpr+" /></td>" +
						"<td><input name='txtCodPrograma' type='hidden' id='txtCodPrograma' value="+CodPrograma+" /></td>" +
						"<td><input name='txtNomPrograma' type='hidden' id='txtNomPrograma' value='"+NomPrograma+"' /></td>" +
						"<td><input name='txtCodCups' type='hidden' id='txtCodCups' value='"+CodCups+"' /></td>" +
						"<td><input name='txtCodReferencia' type='hidden' id='txtCodReferencia' value='"+CodReferencia+"' /></td>" +
						"<td><input name='txtvalor' type='hidden' id='txtvalor' value='"+valor+"' /></td>" +
						"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("TCOS")){
			String TipoBus=req.getParameter("TipoBus");
			try {
				if(TipoBus.equals("CDOC")){
					//consulta por documento
					out.print("<table  border='0' cellspacing='0' cellpadding='0'><tr><td>Tipo Documento</td><td><select name='cmbTipoDoc' id='cmbTipoDoc'><option value='Seleccione' selected='selected'>Seleccione</option>");
					rs=mca.TipoDocumentos();
					while(rs.next()){
						out.print("<option value="+rs.getString("sigla")+">"+rs.getString("sigla")+"</option>");
					}
					rs.getStatement().getConnection().close();				
					out.print("</select></td><td><input name='txtNumDocumento' type='text' id='txtNumDocumento' /><input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarOrdenServ()' /></td></tr></table> ");
				}
			
				if(TipoBus.equals("CNOS")){
					//consulta numero de orden de servicio
					out.print("<table border='0' cellspacing='0' cellpadding='0'><tr><td>Numero Orden <input name='txtNumOrdenServicio' type='text' id='txtNumOrdenServicio' /><input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarOrdenServ()' /></td></tr></table>");
				}
				
				if(TipoBus.equals("CNOM")){
					//consulta numero de orden de servicio
					out.print("<table border='0' cellspacing='0' cellpadding='0'><tr><td>Nombre<input name='txtNombrePac' type='text' id='txtNombrePac' /></td><td>Primer Apellido<input name='txtPrimerApellido' type='text' id='txtPrimerApellido' /></td><td>Segundo Apellido<input name='txtSegundoApellido' type='text' id='txtSegundoApellido' /><input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarOrdenServ()' /></td></tr></table>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("RECOS")){
			String TipoBus=req.getParameter("TipoBus");
			String NumOrden=req.getParameter("NumOrden");
			String NumDoc=req.getParameter("NumDoc");
			String TipoDocu=req.getParameter("TipoDoc");
			String NombrePac=req.getParameter("NombrePac");
			String PrimerApellido=req.getParameter("PrimerApellido");
			String SegundoApellido=req.getParameter("SegundoApellido");
			String Parametro="";
			try {
				if(TipoBus.equals("CDOC")){
					Parametro="AND ap.tipo_documento='"+TipoDocu+"' AND ap.numero_documento='"+NumDoc+"'";
				}
				if(TipoBus.equals("CNOS")){
					Parametro="AND fos.consecutivo LIKE '%"+NumOrden+"%'";
				}
				
				if(TipoBus.equals("CNOM")){
					Parametro="AND ap.nombre LIKE '%"+NombrePac+"%' and ap.primer_apellido LIKE '%"+PrimerApellido+"%' and ap.segundo_apellido LIKE '%"+SegundoApellido+"%' ";
				}
				
				
				rs=mca.ConsultarOrdenes(Parametro);	
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr bgcolor='#DADADA' class='style12' ><td>FECHA y HORA</td><td>NUMERO ORDEN</td><td>PACIENTE</td><td>ENTIDAD PRESTADORA</td></tr>");
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='ReporteOrdenServicio("+rs.getString("codigo")+")'>"+rs.getString("fecha_insercion")+"/"+rs.getString("hora_insercion")+"</a></td><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("Paciente")+"</td><td>"+rs.getString("NombreEntidadpre")+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("BOSA")){
			String NumOrden=req.getParameter("NumOrden");
			String Parametro="";
			try {
				Parametro="AND fos.consecutivo LIKE '%"+NumOrden+"%'";
				
				rs=mca.ConsultarOrdenes(Parametro);	
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr bgcolor='#DADADA' class='style12' ><td>FECHA y HORA</td><td>NUMERO ORDEN</td><td>OBSERVACION</td><td>PACIENTE</td><td>ENTIDAD PRESTADORA</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString("fecha_insercion")+"/"+rs.getString("hora_insercion")+"</td><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("observacion")+"<input name='txtObservacion' type='hidden' id='txtObservacion' value='"+rs.getString("observacion")+"' /></td><td>"+rs.getString("Paciente")+"</td><td>"+rs.getString("NombreEntidadpre")+"</td></tr>");
					out.print("<tr><td>MOTIVO ANULACION</td><td colspan='4'><textarea name='txtObserAnula' cols='90' rows='4' id='txtObserAnula'></textarea></td></tr>");
					out.print("<tr><td colspan='5' align='center' ><input name='btnAnularOrden' type='button' id='btnAnularOrden' value='     Anular     ' onclick='AnularOrdenServicio("+rs.getString("codigo")+")' /></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("AOSA")){
			String CodOrden=req.getParameter("CodOrden");
			String AgruObserva=req.getParameter("AgruObserva");
			String CodusuarioAnula=req.getParameter("Codusuario");
			java.util.Date FechaAc1 = new java.util.Date();
			java.sql.Date Fecha_Anulacion = new java.sql.Date(FechaAc1.getTime());		
			java.sql.Time Hora_Anulacion = new java.sql.Time(FechaAc1.getTime());
			rs=mca.ConsultarAdmisionEncabezadoOrden(CodOrden);
			try {
				if(rs.next()){
					//* se actualiza la tabla fact_orden_servicio estado='2',observacion
					mca.ActualizarOrdenServicio(CodOrden, AgruObserva,Fecha_Anulacion+"",Hora_Anulacion+"",CodusuarioAnula);
					//* se actualiza la tabla admision se pone el estado en '2'
					mca.ActualizarAdmisionOrden(rs.getString("CodAdmision"));
					//* se actualiza el encabezado se pone estado='5'
					mca.ActualizarEncabezadoOrden(rs.getString("CodEncFactura"));
					//* se actualiza el detalle del encabezado se ponen los cargues facturado='5'*/
					mca.ActualizarDetalleEncabezadoOrden(rs.getString("CodEncFactura"));
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		
		
	}
	
	public String formatMoneda(String valor){		
		String temp2="";String temp1="";
		int ud=1;int logCad = valor.length();		
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			temp2+=temp1;
			if(ud==3){
				if(i!=0){temp1="."+temp2;}else{temp1=temp2;}ud=0;
			}else{temp1=temp2;}
			ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
	
}






