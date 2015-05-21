package agm_controlador;

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
import agm_metodo.MetodoAsignarCita;
import agm_metodo.MetodoConsultasAsignacion;
import agm_metodo.MetodoMedicos;
import agm_metodo.MetodoParametros;


public class ControlAsignarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoAsignarCita mac = new MetodoAsignarCita();
		MetodoMedicos mmd = new MetodoMedicos();
		MetodoParametros mp = new MetodoParametros();
		MetodoConsultasAsignacion mca = new MetodoConsultasAsignacion();
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
		ResultSet rs12=null;
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
								out.print("<td>Ma&ntilde;ana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Tuesday")){
							out.print("<tr><td>Martes</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Ma&ntilde;ana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}							
						}
						if(rs1.getString("Dia").equals("Wednesday")){
							out.print("<tr><td>Miercoles</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Ma&ntilde;ana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Thursday")){
							out.print("<tr><td>Jueves</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Ma&ntilde;ana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Friday")){
							out.print("<tr><td>Viernes</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Ma&ntilde;ana</td><td>07AM-12PM</td></tr>");
							}
							if(rs1.getString("jornada").equals("PM")){
								out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
							}
						}
						if(rs1.getString("Dia").equals("Saturday")){
							out.print("<tr><td>Sabado</td>");
							if(rs1.getString("jornada").equals("AM")){
								out.print("<td>Ma&ntilde;ana</td><td>07AM-12PM</td></tr>");
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
			out.print("<tr><td>No</td><td width='13%'><div align='center'>Fecha</div></td><td  width='17%' ><div align='center'>Hora</div></td><td <td width='10%'>Tipo Cita </td><td width='30%'><div align='center'>Observacion </div></td><td <td width='30%'><div align='center'>Nombre Paciente</div></td></tr>");
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
			for(int g=0; g<h-1; g=g+1){ 
				Anio=d[0];Mes=d[1];Dia=d[2];
			}
			if(Mes.equals("1")){Mes_letra="Enero";}if(Mes.equals("2")){Mes_letra="Febrero";}if(Mes.equals("3")){Mes_letra="Marzo";}
			if(Mes.equals("4")){Mes_letra="Abril";}if(Mes.equals("5")){Mes_letra="Mayo";}if(Mes.equals("6")){Mes_letra="Junio";}
			if(Mes.equals("7")){Mes_letra="Julio";}if(Mes.equals("8")){Mes_letra="Agosto";}if(Mes.equals("9")){Mes_letra="Septiembre";}
			if(Mes.equals("10")){Mes_letra="Octubre";}if(Mes.equals("11")){Mes_letra="Noviembre";}if(Mes.equals("12")){Mes_letra="Diciembre";}
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center'>Resultado del Filtro para el dia "+Dia+" de "+Mes_letra+" de "+Anio+"</div></td></tr>");
			out.print("<tr><td>No</td><td  width='17%' ><div align='center'>Hora</div></td><td <td width='15%'>Tipo Cita </td><td width='35%'><div align='center'>Observacion </div></td><td <td width='33%'><div align='center'>Nombre Paciente</div></td></tr>");
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
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el a�o es bisiesto*/
			{diasMes1[2] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
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
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los d�as en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			int a1=0;
			for(e=1;e<=nDias1;e++) /*Bucle para la inclusi�n del n�mero de d�a en el calendario*/
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
			if(e==dia1) //Si el d�a a escribir es el d�a de hoy
			{ out.print("</b></font>"); //Cerramos la etiqueta de edici�n de las propiedades de las fuentes
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
			String CodEnt=req.getParameter("CodEnt");
			try {				
				int conp=0;
				String CodPro="";
				String CodRefPro="";
				String NomPro="";
				String ValorPro="";
				rs=mca.ProgramaCex(Programa,ManualTarifario,CodEnt);
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
			//icn-hospital ni�o jesus
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
					out.print("<tr class='style12' ><td>Direccion <input name='CodPac' type='hidden' id='CodPac' value="+rs.getString(1)+" /><input name='CodManualTarifario' type='hidden' id='CodManualTarifario' value="+rs.getString(9)+" /><input name='txtNombreCompleto' type='hidden' id='txtNombreCompleto' value='"+NombreComple+"'/></td><td>"+rs.getString("direccion")+" </td><td>Telefono</td><td>"+rs.getString("Telefono")+"</td></tr>");
					out.print("<tr><td colspan='8' align='center' id='cabecera2' class='style11'>DATOS DE LA CITA</td></tr>");
					String Gene=rs.getString(6);
					String Edad=rs.getString("edad");
					out.print("<tr class='style12'><td>No Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
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
						// son menores de 18 a�os mostrar todas las especialidades
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
								rs1=mac.BuscarEspecialidadTodas2();
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
						Condicion="AND es.nombre_especialidad NOT LIKE '%PEDIATRI%' AND es.nombre_especialidad NOT LIKE '%GINECO%' ORDER BY am.nombre ";
						rs1=mac.CargarTodosMedicos(Condicion);
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+" title='"+rs1.getString(3)+"' >"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					}
						
					if(Gene.equals("Femenino")){
						//mostrar todas las especialidades y medicos menos la urologia.
						Condicion="AND es.nombre_especialidad NOT LIKE '%PEDIATRI%' AND es.nombre_especialidad NOT LIKE '%UROLOG%' ORDER BY am.nombre ";
						rs1=mac.CargarTodosMedicos(Condicion);
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+" title='"+rs1.getString(3)+"' >"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					}
				}else{
					// son menores de 18 a�os mostrar todas las especialidades
						//no mostrar las pediatricas tanto en especialidades como en medicos.
						if(Gene.equals("Masculino")){
							//mostrar todas las especialidades y medicos menos las ginecologicas.
							Condicion=" AND es.nombre_especialidad NOT LIKE '%GINECO%' ORDER BY am.nombre ";
							rs1=mac.CargarTodosMedicos(Condicion);
							while(rs1.next()){
								out.print("<option value="+rs1.getString(1)+" title='"+rs1.getString(3)+"' >"+rs1.getString(2)+"</option>");
							}
							rs1.getStatement().getConnection().close();
						}
						if(Gene.equals("Femenino")){
							//mostrar todas las especialidades y medicos menos la urologia.
							Condicion=" AND es.nombre_especialidad NOT LIKE '%UROLOG%' ORDER BY am.nombre ";
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
					
					//out.print("<tr class='style12'><td>N� Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
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
					out.print("<table width='100%' border='0' > <tr class='style12'><td>N� Autorizacion</td>" +
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
					
					out.print("<table width='100%' border='0' ><tr class='style12'><td>N� Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
					
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
					//out.print("<table width='100%' border='0' > <tr class='style12'><td>N� Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' value="+rs.getString(11)+" /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' value="+rs.getString(20)+" /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value="+rs.getString(12)+" /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex'  value="+rs.getString(13)+"  /></td></tr>");
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
					
					//out.print("<table width='100%' border='0' ><tr class='style12'><td>N� Autorizacion</td><td><input name='txtNumAutor' type='text' id='txtNumAutor' /></td><td>Diagnostico</td><td><input name='txtDiagCEX' type='text' id='txtDiagCEX' onkeyup='BuscarDxCex()' value='Z719' /></td><td>Cod Programa</td><td><input name='txtCodProgCEX' type='text' id='txtCodProgCEX' onkeyup='BuscarProgCex()' /></td><td>Copago<input name='txtCopagoCex' type='text' id='txtCopagoCex' value='0' /></td><td>Moderacion <input name='txtModeracionCex' type='text' id='txtModeracionCex' value='0' /></td></tr>");
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
			if(Medico.equals("Ver Todos")){
				rs=mac.RipsMedicoTodos(fechaIni, fechaFin);
			}else{
				rs=mac.RipsMedico(fechaIni, fechaFin, Medico);	
			}
			
				out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%'>N�</td><td>Fecha</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
				while(rs.next()){
					co=co+1;
					out.print("<tr><td>"+co+"</td><td>"+rs.getString("fechas")+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td>" +
							"<td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td>" +
									"<td>"+rs.getString(8)+"</td>");
					
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
			out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%'>N�</td><td>Fechas</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
			while(rs.next()){
				co=co+1;
				out.print("<tr><td>"+co+"</td><td>"+rs.getString("fechas")+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td>");
				
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
			if(Medico.equals("Ver Todos")){
				rs=mac.RipsMedicoEntidadTodos(fechaIni, fechaFin,  Entidad);
			}else{
				rs=mac.RipsMedicoEntidad(fechaIni, fechaFin, Medico, Entidad);
			}
			out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%'>N�</td><td>Fechas</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
			while(rs.next()){
				co=co+1;
				out.print("<tr><td>"+co+"</td><td>"+rs.getString("fechas")+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td>");
				
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
		
		if(va.equals("Mer3")){
			//solo medico VACIO
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
			out.print("<table width='100%' border='1' cellspacing='0' style='font-size:9px' ><tr><td width='5%' >N�</td><td>Hora</td><td>Identificacion</td><td>Nombre</td><td>Edad</td><td>Entidad</td><td>Diag. Princ</td><td>Diag Rel1</td><td>Diag Rel2</td><td>Finalidad</td><td>Cau Ext</td><td>Profesional</td></tr>");
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
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>Fecha Asignaci�n</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
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
		
		if(va.equals("Ind")){
			String CSQL=req.getParameter("SQL");
			String TipoConsulta=req.getParameter("TipoConsulta");
			String CodEnt=req.getParameter("CodEnt");
			String fechaIni=req.getParameter("fechaIni");
			String fechaFin=req.getParameter("fechaFin");
			try {
			if((TipoConsulta.equals("Todos"))&&(CodEnt.equals("Todas"))){
				rs=mac.ConsultaIndicadores(CSQL);
				out.print("<table width='100%'  border='1' ><tr><td>INDICADORES DE OPORTUNIDAD DESDE "+fechaIni+" HASTA "+fechaFin+" DE "+TipoConsulta+"</td></tr>");
				out.print("<table width='100%'  border='1' ><tr><td>Especialidad</td><td>Sumatoria Dias</td><td>Cantidad Citas</td><td>Promedio Dias</td></tr>");
					while(rs.next()){
						out.print("<tr><td>"+rs.getString("Especialidad")+"</td><td>"+rs.getString("Suma_dias")+"</td>" +
								"<td>"+rs.getString("Cantidad_citas")+"</td><td>"+rs.getString("Promedio")+"</td></tr>");
					}
					rs.getStatement().getConnection().close();
				out.print("</table>");
			}
			if((!TipoConsulta.equals("Todos"))&&(CodEnt.equals("Todas"))){
				rs=mac.ConsultaIndicadores(CSQL);
				out.print("<table width='100%'  border='1' ><tr><td>INDICADORES DE OPORTUNIDAD DESDE "+fechaIni+" HASTA "+fechaFin+" DE "+TipoConsulta+"</td></tr>");
				out.print("<table width='100%'  border='1' ><tr><td>Especialidad</td><td>Sumatoria Dias</td><td>Cantidad Citas</td><td>Promedio Dias</td></tr>");
					while(rs.next()){
						out.print("<tr><td>"+rs.getString("Especialidad")+"</td><td>"+rs.getString("Suma_dias")+"</td>" +
								"<td>"+rs.getString("Cantidad_citas")+"</td><td>"+rs.getString("Promedio")+"</td></tr>");
					}
					rs.getStatement().getConnection().close();
				out.print("</table>");

			}
			
			if((TipoConsulta.equals("Todos"))&&(!CodEnt.equals("Todas"))){
				rs1=mac.ConsultaIndicadores(CSQL);
				out.print("<table width='100%'  border='1' ><tr><td>INDICADORES DE OPORTUNIDAD DESDE "+fechaIni+" HASTA "+fechaFin+" DE "+TipoConsulta+"</td></tr>");
				if(rs1.next()){
					out.print("<tr><td colspan='4'>"+rs1.getString("nombre_entidad")+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
				rs=mac.ConsultaIndicadores(CSQL);
				out.print("<table width='100%'  border='1' ><tr><td>Especialidad</td><td>Sumatoria Dias</td><td>Cantidad Citas</td><td>Promedio Dias</td></tr>");
					while(rs.next()){
						out.print("<tr><td>"+rs.getString("Especialidad")+"</td><td>"+rs.getString("Suma_dias")+"</td>" +
								"<td>"+rs.getString("Cantidad_citas")+"</td><td>"+rs.getString("Promedio")+"</td></tr>");
					}
					rs.getStatement().getConnection().close();
				out.print("</table>");

			}
			
			if((!TipoConsulta.equals("Todos"))&&(!CodEnt.equals("Todas"))){
				rs1=mac.ConsultaIndicadores(CSQL);
				out.print("<table width='100%' border='1' ><tr><td>INDICADORES DE OPORTUNIDAD DESDE "+fechaIni+" HASTA "+fechaFin+" DE "+TipoConsulta+"</td></tr>");
				if(rs1.next()){
					out.print("<tr><td colspan='4'>"+rs1.getString("nombre_entidad")+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
				rs=mac.ConsultaIndicadores(CSQL);
				out.print("<table width='100%'  border='1' ><tr><td>Especialidad</td><td>Sumatoria Dias</td><td>Cantidad Citas</td><td>Promedio Dias</td></tr>");
					while(rs.next()){
						out.print("<tr><td>"+rs.getString("Especialidad")+"</td><td>"+rs.getString("Suma_dias")+"</td>" +
								"<td>"+rs.getString("Cantidad_citas")+"</td><td>"+rs.getString("Promedio")+"</td></tr>");
					}
					rs.getStatement().getConnection().close();
				out.print("</table>");

			}
			
			if(CodEnt.equals("Todas")){
				//se hace una consulta de todas las entidades 

				//se ejecuta con los parametros que trae SQL
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("Inc")){
			out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
			out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''></td></tr>");
			out.println("<tr ><td  align='right'>TIPO INDICADOR</td><td ><select name='cmbTipoConsulta' id='cmbTipoConsulta'><option value='Todos' selected='selected'>Todos</option>");
			try {
			rs1=mac.CargarTipoConsulta();				
			
				while(rs1.next()){
					out.print("<option value='"+rs1.getString(2)+"'>"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection();
						
			
			out.println("</select></td></tr><tr><td >Seleccione Entidad</td><td><select id='cmbEntidad'><option value='Todas'>Todas</option>");
			rs1=mac.CargarEntidades();				
			
			while(rs1.next()){
				out.print("<option value='"+rs1.getString("ent_nit")+"'>"+rs1.getString("nombre_entidad")+"</option>");
			}
			rs1.getStatement().getConnection();
			out.println("</select></td></tr></table>");
			out.println("<table><tr><td  align='right'><input name='btnPasarListaMedico' type='button' value='Buscar' onClick='IndicadoresAtencion()' ></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("rat")){
			
			try {
				out.println("<table width='100%'  class='style6'><tr><td  align='right'>FECHA INICIAL:</td><td><label>Dia<select name='cmbdiaIni' id='cmbdiaIni'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option> <option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesIni' id='cmbmesIni'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioIni' type='text' id='txtanioIni' size='4' maxlength='4'></label></td></tr>");
				out.println("<tr ><td  align='right'>FECHA FINAL:</td><td >Dia<select name='cmbdiaFin' id='cmbdiaFin'><option value='Dia' selected='selected'>Dia</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option><option value='13'>13</option><option value='14'>14</option><option value='15'>15</option><option value='16'>16</option><option value='17'>17</option><option value='18'>18</option><option value='19'>19</option><option value='20'>20</option><option value='21'>21</option><option value='22'>22</option><option value='23'>23</option><option value='24'>24</option><option value='25'>25</option><option value='26'>26</option><option value='27'>27</option><option value='28'>28</option><option value='29'>29</option><option value='30'>30</option><option value='31'>31</option></select>Mes<select name='cmbmesFin' id='cmbmesFin'><option value='Mes' selected='selected'>Mes</option><option value='01'>Enero</option><option value='02'>Febrero</option><option value='03'>Marzo</option><option value='04'>Abril</option><option value='05'>Mayo</option><option value='06'>Junio</option><option value='07'>Julio</option><option value='08'>Agosto</option><option value='09'>Septiembre</option><option value='10'>Octubre</option><option value='11'>Noviembre</option><option value='12'>Diciembre</option></select>A&ntilde;o<input name='txtanioFin' type='text' id='txtanioFin' size='4' maxlength='4'></td></tr><tr><td >&nbsp;</td><td align=''></td></tr></table>");
				out.print("<table><tr><td>Tipo Reporte</td><td><select id='TipoReporte'><option value='Seleccione' selected='' >Seleccione</option><option value='Solo' >Solo Citas</option><option value='Diligenciado' >Diligenciado</option></select></td></tr><tr><td>Medico</td><td><div id='medico'><select name='cmbMedico' id='cmbMedico'><option value='Ver Todos' selected='selected'>Ver Todos</option>");
				rs1=mac.CargarTodosMedicos("ORDER BY am.nombre");				
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
				out.print("<table width='100%' border='1'><tr><td colspan='7'><div align='center' id='cabecera2' class='style11' >Resultado De La Busqueda </div></td></tr>");
				out.print("<tr><td colspan='7'><div align='center'>Citas Para El Dia:"+dia+" de "+NombreMes+" del "+anio+" </div></td></tr>");
				out.print("<tr><td><div align='center'>Hora</div></td><td><div align='center'>Nombre Paciente</div> </td><td>Documento</td><td>Entidad</td><td><div align='center'>Especialidad</div></td><td><div align='center'>Medico</div></td><td><div align='center'>Por Atender</div></td></tr>");
				while(rs.next()){
					contador=contador+1;
					if(rs.getString(9).equals("1")){						
					
					out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString("DocumentoPac")+"</td><td>"+rs.getString("nombre_entidad")+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td><input name='radiobutton' type='radio' id='chkPorAtender' onclick='VerificarDatosCitaCEX("+rs.getString(5)+")' value="+rs.getString(5)+"></td></tr>");
					}
					
					if(rs.getString(9).equals("2")){						
						
						out.print("<tr bgcolor='navy'><td><strong><font color='white'>"+rs.getString(2)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(3)+"</font></strong></td><td><strong><font color='white'>"+rs.getString("DocumentoPac")+"</font></strong></td><td><strong><font color='white'>"+rs.getString("nombre_entidad")+"</font></strong></td><td><strong><font color='white'>"+rs.getString(7)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(8)+"</font></strong></td><td><strong><font color='white'>EN ESPERA DE ATENCION</font></strong></td></tr>"); 
					}
					
					if(rs.getString(9).equals("3")){						
						
						out.print("<tr bgcolor='green' ><td><strong><font color='white'>"+rs.getString(2)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(3)+"</font></strong></td><td><strong><font color='white'>"+rs.getString("DocumentoPac")+"</font></strong></td><td><strong><font color='white'>"+rs.getString("nombre_entidad")+"</font></strong></td><td><strong><font color='white'>"+rs.getString(7)+"</font></strong></td><td><strong><font color='white'>"+rs.getString(8)+"</font></strong></td><td><font color='white'>ATENCION FINALIZADA</font></strong></td></tr>");
					}
					//out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td><input name='chkPorAtender' type='checkbox' id='chkPorAtender' value="+rs.getString(5)+"></td></tr>");
				}
				out.print("<tr><td colspan='7'><div id='DatosCitaCEX'></div></td></tr></table>");
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
				String cod_eps = "", razon_social = "",
				 nit = "", direccion = "",
				telefono = "",ciudad = "",nombre_paciente = "",documentoP = "",
				direccion_p = "",telefono_p = "",
				tipo_afiliacion = "",estrato = "",fecha_ingreso = "",				
				poliza = null;
				String ViaIng="4";
				rs=ma.obtenerCodigoAdmision(FechaRegistro, horaDXA+"",CodPac);
				if(rs.next()){
					CodAdmision=rs.getString(1);
					ResultSet rsc=null;				
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
				
				}
				rs.getStatement().getConnection().close();
				
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
					
					out.print(CodEncFactura);
				
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
						"<li><a href='#' onclick='MostrarDQx("+CodAdm+","+CodEnc+")'>Descripcion QX</a></li>"+
						"<li><a href='Odont_realizar_ce.jsp?docPac="+rs.getString(6)+"&numDoc="+rs.getString(7)+"&CodHorarioMedico="+CodHorMed+"&CodAdm="+CodAdm+"&CodPac="+CodigoPaciente+"' >Odontologia</a></li></ul>" +
						/*"<li><a href='#' onclick='realizarAiepi()'>Realizar AIEPI</a></li>" +
						"<li><a href='#' onclick='consultarAiepi()'>Consultar AIEPI</a></li></ul>" +*/
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
					if(rs1.getString(5).equals("3")){
						//ecocardiogramas
						out.print("<tr><td width='75%'><a href='#' onclick='mostrarInformesCardiologia("+rs1.getString(1)+")' >"+rs1.getString(2)+"</a></td><td width='25%'>"+rs1.getString(3)+"</td></tr>");
					}
					
					if(rs1.getString(5).equals("4")){
						//resonancias
						out.print("<tr><td width='75%'><a href='#' onclick='mostrarInformesRmc("+rs1.getString(1)+")' >"+rs1.getString(2)+"</a></td><td width='25%'>"+rs1.getString(3)+"</td></tr>");
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
			
			out.print("<tr><td width='5%' class='style12'>Diluci&oacute;n</td><td width='10%'><select name='cmbDilucion' id='cmbDilucion'><option value='Seleccione' selected='selected'>Seleccione</option>" +
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
					
					//out.print("<tr><td width='5%' class='style12'>Diluci�n</td><td width='10%'><select name='cmbDilucion' id='cmbDilucion'><option value='Seleccione' selected='selected'>Seleccione</option>");
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
		
		String CodiPaciente=req.getParameter("CodiPaciente");	
		if (va.equals("10.0")) {
			String CodAdm="";
			String edadMeses="";
			
			rs1=mac.VerificarAdmision(CodiPaciente);
			try {
				if (rs1.next()) {
					
				CodAdm=rs1.getString("adm_numero_ingreso");
				edadMeses=rs1.getString("EdadMeses");
			int a;	     				
			a = Integer.parseInt(edadMeses);
			
			rs2=mac.PreguntarAdmisionCeroDosR(CodAdm);
			if (rs2.next()) {
				out.print("El paciente "+rs1.getString("NombreCompleto")+" con Numero de Admision "+rs1.getString("adm_numero_ingreso")+" ya tiene un formato realizado de AIEPI de 0 meses a 2 meses");
			}else {
				rs3=mac.PreguntarAdmisionDosCincoR(CodAdm);
				if (rs3.next()) {
					out.print("El paciente "+rs1.getString("NombreCompleto")+" con Numero de Admision "+rs1.getString("adm_numero_ingreso")+" ya tiene un formato realizado de AIEPI de 2 meses a 5 a�os");
				} else {
					String codigoPrincipalCIECeroDos="";
					String codigoCIECeroDos=req.getParameter("DxAiepiCeroDos");
					rs12=mac.ConsultarCodigoPrincipalCIE(codigoCIECeroDos);
					if (rs12.next()) {
						codigoPrincipalCIECeroDos=rs12.getString(1);
					}
					rs12.getStatement().getConnection().close();
											
					String codigoPrincipalCIE="";
					String codigoCIE=req.getParameter("DxAiepiDosCinco");
					rs12=mac.ConsultarCodigoPrincipalCIE(codigoCIE);
					if (rs12.next()) {
						codigoPrincipalCIE=rs12.getString(1);
					}
					rs12.getStatement().getConnection().close();
											
										if(a <= 1){
											out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><td align='center' bgcolor='#104e8b' style='color:#FFFFFF'><b>ATENCION INTEGRADA AL LACTANTE MENOS DE DOS MESES</b></td>" +
													"</tr></table>");
											out.print("<table width='80%'><tr><td><b>INSTITUCION:</b></td><td>"+rs1.getString("Entidad")+"</td><td></td><td><b>MUNICIPIO:</b></td><td>"+rs1.getString("Municipio")+"</td><td></td><td><b>N� HISTORIA CLINICA:</b></td><td>"+rs1.getString("adm_numero_ingreso")+"</td></tr>" +
													"<tr><td><b>NOMBRE:</b></td><td>"+rs1.getString("NombreCompleto")+"</td><td></td><td><b>EDAD:</b></td><td>"+rs1.getString("EdadDias")+" dias</td><td></td><td><input type='hidden' name='txtCodReporte' id='txtCodReporte'/></td></tr>" +
													"<tr><td><b>FECHA NACIMIENTO:</b></td><td>"+rs1.getString("fecha_nacimiento")+"</td><td></td><td><b>SEXO:</b></td><td>"+rs1.getString("genero")+"</td><td><input name='CodAdmision' type='hidden' id='CodAdmision' value='"+CodAdm+"' /></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>DATOS ACOMPA&Ntilde;ANTE</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>"+rs1.getString("responsable")+"</td></tr></table>");
											out.print("<table width='100%'><tr><br><td><b>MOTIVO DE CONSULTA Y ENFERMEDAD ACTUAL:</b></td></tr><tr><td><textarea rows='4' cols='135' name='mot_consulta' id='mot_consulta'></textarea></td></tr>" +
													"<tr><td><b>ANTECEDENTE DE EMBARAZO PARTO DE IMPORTANCIA:</b><tr><td><textarea rows='4' cols='135' name='ant_embarazo' id='ant_embarazo'></textarea></td></tr></table>" +
													"<table width='80%'><tr><td><b>PESO AL NACER:</b></td><td><INPUT type='text' size='1' name='peso_nacer' id='peso_nacer' onkeypress='javascript:return validarNro(event)'/> gr.</td><td><b>TALLA AL NACER:</b></td><td><INPUT type='text' size='1' name='talla_nacer' id='talla_nacer' onkeypress='javascript:return validarNro(event)'/> cm</td>" +
													"<td><b>EDAD GESTACIONAL:</b></td><td><INPUT type='text' size='1' name='edad_gestacional' id='edad_gestacional' onkeypress='javascript:return validarNro(event)'/> Semanas.</td><td><b>HEMOCLASIFICACION:<b></td><td><INPUT type='text' size='1' name='hemoclasificacion' id='hemoclasificacion' onkeypress='javascript:return validarNro(event)'/></td></tr>" +
													"<tr><td><b>PESO: actual</b></td><td><INPUT type='text' size='1' name='peso_actual' id='peso_actual' onkeypress='javascript:return validarNro(event)'/> gr.</td><td><b>TALLA:</b></td><td><INPUT type='text' size='1' name='talla' id='talla' onkeypress='javascript:return validarNro(event)'/> cm</td>" +
													"<td><b>PC:</b></td><td><INPUT type='text' size='1' name='perimetro_cefalico' id='perimetro_cefalico' onkeypress='javascript:return validarNro(event)'/> cm</td><td><b>FC:</b></td><td><INPUT type='text' size='1' name='frecuencia_cardiaca' id='frecuencia_cardiaca' onkeypress='javascript:return validarNro(event)'/> /min</td></tr>" +
													"<tr><td><b>FR:</b></td><td><INPUT type='text' size='1' name='frecuencia_respiratoria' id='frecuencia_respiratoria' onkeypress='javascript:return validarNro(event)'/> /min</td><td><b>T&deg;:</b></td><td><INPUT type='text' size='1' name='temperatura' id='temperatura' onkeypress='javascript:return validarNro(event)'/></td>" +
													"<td><b>C&deg;:</b></td><td><INPUT type='text' size='1' name='centigrados' id='centigrados' onkeypress='javascript:return validarNro(event)'/></td></tr></table>");
											out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td align='center' bgcolor='#104e8b' style='color:#FFFFFF'><b>EVALUAR Y CLASIFICAR AL LACTANTE MENOR DE 2 MESES DE EDAD</b></td></tr></table>");
						/*Ver Enf Grave*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI TIENE UNA ENFERMEDAD MUY GRAVE O INFECCION LOCAL</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>&iquest;Puede beber o tomar el pecho?</td><td>Si<INPUT type='radio' name='rad_bebe_pecho' id='si'>No<INPUT type='radio' name='rad_bebe_pecho' id ='no'></td>" +
													"<td>&iquest;Ha tenido fiebre?</td><td>Si<INPUT type='radio' name='rad_fiebre' id='si'>No<INPUT type='radio' name='rad_fiebre' id='no'></td>" +
													"<td>&iquest;Ha tenido hipotermia?</td><td>Si<INPUT type='radio' name='rad_hipotermia' id='si'>No<INPUT type='radio' name='rad_hipotermia' id='no'></td>" +
													"<td>&iquest;Ha tenido convulsiones?</td><td>Si<INPUT type='radio' name='rad_convulsiones' id='si'>No<INPUT type='radio' name='rad_convulsiones' id='no'></td></tr>" +
													"<tr><td>&iquest;Ha tenido vomito?</td><td>Si<INPUT type='radio' name='rad_vomito' id='si' onclick='vomita_todo.disabled=false;vomita_todo.focus()'>No<INPUT type='radio' name='rad_vomito' id='no' onclick='vomita_todo.disabled=true'></td>" +
													"<td>&iquest;Vomita todo?</td><td><INPUT type='text' name='vomita_todo' id='vomita_todo' disabled></td>" +
													"<td>&iquest;Tiene el ni&ntilde;o diarrea?</td><td>Si<INPUT type='radio' name='rad_diarrea' id='si' onclick='cuando_diarrea.disabled=false;cuando_diarrea.focus()'>No<INPUT type='radio' name='rad_diarrea' id='no' onclick='cuando_diarrea.disabled=true'></td>" +
													"<td>&iquest;Desde cuando?</td><td><INPUT type='text' size='1' name='cuando_diarrea' id='cuando_diarrea' disabled onkeypress='javascript:return validarNro(event)'> dias</td></tr>" +
													"<tr><td>&iquest;Tiene dificultad para respirar?</td><td>Si<INPUT type='radio' name='rad_dificultad_respirar' id='si' onclick='exp_dificultad_respirar.disabled=false;exp_dificultad_respirar.focus()'>No<INPUT type='radio' name='rad_dificultad_respirar' id='no' onclick='exp_dificultad_respirar.disabled=true'></td><td>Explique:</td><td><INPUT type='text' name='exp_dificultad_respirar' id='exp_dificultad_respirar' disabled></td>" +
													"<td>&iquest;Hay sangre en las heces?</td><td>Si<INPUT type='radio' name='rad_sangre_heces' id='si'>No<INPUT type='radio' name='rad_sangre_heces' id='no'></td>" +
													"<td>&iquest;Cuantos pa&ntilde;ales ha orinado en las Ultimas 24 horas?</td><td><INPUT type='text' name='panales_orinados' id='panales_orinados'></td></tr>" +
													//OBSERVAR AL NI�O(A)
													"<tr><td><br><br><br>Se mueve solo al estimulo</td><td><br><br><br><input type='checkbox' name='mueve_solo' id='si'></td>" +
													"<td><br><br><br>Letargico</td><td><br><br><br><input type='checkbox' name='letargico' id='si'></td>" +
													"<td><br><br><br>'Se ve o luce mal'</td><td><br><br><br><input type='checkbox' name='luce_mal' id='si'></td>" +
													"<td><br><br><br>Irritable</td><td><br><br><br><input type='checkbox' name='irritable' id='si'></td></tr>" +
													"<tr><td>Palidez</td><td><input type='checkbox' name='palidez' id='si'></td>" +
													"<td>Cianosis</td><td><input type='checkbox' name='cianosis' id='si'></td>" +
													"<td>Apneas</td><td><input type='checkbox' name='apneas' id='si'></td>" +
													"<td>Ictericia precoz o importante por clinica o bilirrubinas</td><td><input type='checkbox' name='bilirrubinas' id='si'></td></tr>" +
													"<tr><td>Estridor</td><td><input type='checkbox' name='estridor' id='si'></td>" +
													"<td>FR >60 o <30 por minuto</td><td><input type='checkbox' name='fr' id='si'></td>" +
													"<td>Aleteo nasal</td><td><input type='checkbox' name='aleteo_nasal' id='si'></td>" +
													"<td>Quejido</td><td><input type='checkbox' name='quejido' id='si'></tr>" +
													"<tr><td>Sibilancia</td><td><input type='checkbox' name='sibilancia' id='si'></td>" +
													"<td>FC >180 o <100 por minuto</td><td><input type='checkbox' name='fc' id='si'></td>" +
													"<td>Supuracion de oido</td><td><input type='checkbox' name='supuracion_oido' id='si'></td>" +
													"<td>Tiraje subcostal grave</td><td><input type='checkbox' name='tiraje_subcostal' id='si'></td></tr>" +
													"<tr><td>Secrecion purulenta conjuntival</td><td><input type='checkbox' name='sec_purulenta_conjuntival' id='si'></td>" +
													"<td>Edema palpebral</td><td><input type='checkbox' name='edema_palpebral' id='si'></td>" +
													"<td>Eritema periumbilical</td><td><input type='checkbox' name='eritema_periumbilical' id='si'></td>" +
													"<td>Pustulas o vesiculas en piel</td><td><SELECT name='pustulas_piel' id='pustulas_piel'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Muchas o Extensas'>Muchas o extensas</OPTION><OPTION value='Pocas y Localizadas'>Pocas y localizadas</OPTION></SELECT></td></tr>" +
													"<tr><td>Secrecion purulenta ombligo</td><td><input type='checkbox' name='sec_purulenta_ombligo' id='si'></td>" +
													"<td>Equimosis</td><td><input type='checkbox' name='equimosis' id='si'></td>" +
													"<td>Petequias</td><td><input type='checkbox' name='petequias' id='si'></td>" +
													"<td>Placas blanquecinas en la boca</td><td><input type='checkbox' name='placas_blanquecinas' id='si'></td></tr>" +
													"<tr><td>Hemorragia</td><td><input type='checkbox' name='hemorragia' id='si'></td>" +
													"<td>Llenado capilar >3 seg.</td><td><input type='checkbox' name='llenado_capilar' id='si'></td>" +
													"<td>Distension abdominal</td><td><input type='checkbox' name='distension_abdominal' id='si'></td>" +
													"<td>Fontanela abombada</td><td><input type='checkbox' name='fontanela_abombada' id='si'></td></tr>" +
													"<tr><td>Ojos hundidos</td><td><input type='checkbox' name='ojos_hundidos' id='si'></td>" +
													"<td>Estado general:</td><td><SELECT name='estado_general' id='estado_general'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Letargico o Comatoso'>Letargico o comatoso</OPTION><OPTION value='Intranquilo o Irritable'>Intranquilo o irritable</OPTION></SELECT></td>" +
													"<td>Pliegue cutaneo:</td><td><SELECT name='pliegue_cutaneo' id='pliegue_cutaneo'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Inmediato'>Inmediato</OPTION><OPTION value='Lento'>Lento</OPTION></SELECT></td></tr></table>");
											
											out.print("<table width='100%'><tr><br><br><td><b>ENFERMEDAD GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='enfermedad_grave' id='si'></td><td></td><td><b>INFECCI%Oacutre;N LOCAL</b>&nbsp;&nbsp;<input type='checkbox' name='infeccion_local' id='si'></td><td></td><td><b>NO TIENE ENFERMEDAD GRAVE NI INFECCI&Oacute;N LOCAL</b>&nbsp;&nbsp;<input type='checkbox' name='no_enf_grave_no_inf_local' id='si'></td>" +
													"<td><b>DESHIDRATACI&oacute;N</b>&nbsp;&nbsp;<input type='checkbox' name='deshidratacion' id='si'></td><td><b>NO DESHIDRATACI&Oacute;N</b>&nbsp;&nbsp;<input type='checkbox' name='no_deshidratacion' id='si'></td><td><b>DIARREA PROLONGADA</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_prolongada' id='si'></td><td></td><td><b>DIARREA CON SANGRE</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_sangre' id='si'></td></tr></table>");
											
						/*Ver Alimentacion*/out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR EL CRECIMIENTO Y LAS PRACTICAS DE ALIMENTACION</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>&iquest;Tiene alguna dificultad para alimentarse?</td><td>Si<INPUT type='radio' name='rad_dificultad_alimentarse' id='si' onclick='cual_dificultad_alimentarse.disabled=false;cual_dificultad_alimentarse.focus()'>No<INPUT type='radio' name='rad_dificultad_alimentarse' id='no' onclick='cual_dificultad_alimentarse.disabled=true'></td>" +
													"<td>&iquest;Cual?</td><td><INPUT type='text' name='cual_dificultad_alimentarse' id='cual_dificultad_alimentarse' disabled></td>" +
													"<td>&iquest;Ha dejado de comer?</td><td>Si<INPUT type='radio' name='rad_ha_dejado_comer' id='si' onclick='desde_cuando_ha_dejado_comer.disabled=false;desde_cuando_ha_dejado_comer.focus()'>No<INPUT type='radio' name='rad_ha_dejado_comer' id='no' onclick='desde_cuando_ha_dejado_comer.disabled=true'></td>" +
													"<td>&iquest;Desde cu&aacute;ndo?</td><td><INPUT type='text' size='1' name='desde_cuando_ha_dejado_comer' id='desde_cuando_ha_dejado_comer' disabled onkeypress='javascript:return validarNro(event)'> d�as</td></tr>" +
													"<tr><td>&iquest;Se alimenta con leche materna?</td><td>Si<INPUT type='radio' name='rad_leche_materna' id='si' onclick='forma_exclusiva.disabled=false;forma_exclusiva.focus();cuantas_veces_forma_exclusiva.disabled=false'>No<INPUT type='radio' name='rad_leche_materna' id='no' onclick='forma_exclusiva.disabled=true;cuantas_veces_forma_exclusiva.disabled=true'></td>" +
													"<td>&iquest;La ofrece en forma exclusiva?</td><td><input type='text' name='forma_exclusiva' id='forma_exclusiva' disabled></td>" +
													"<td>&iquest;Cuantas veces en 24 horas?</td><td><INPUT type='text' name='cuantas_veces_forma_exclusiva' id='cuantas_veces_forma_exclusiva' disabled></td>" +
													"<td>&iquest;Utiliza chupo?</td><td>Si<INPUT type='radio' name='rad_utiliza_chupo' id='si'>No<INPUT type='radio' name='rad_utiliza_chupo' id='no'></td></tr>" +
													"<tr><td>&iquest;Recibe otra leche, otro alimento o bebidas?</td><td>Si<INPUT type='radio' name='rad_otra_leche' id='si' onclick='cuales_frecuencia_otra_leche.disabled=false;cuales_frecuencia_otra_leche.focus();prepara_otra_leche.disabled=false;que_utiliza_alimentarlo.disabled=false'>No<INPUT type='radio' name='rad_otra_leche' id='no' onclick='cuales_frecuencia_otra_leche.disabled=true;prepara_otra_leche.disabled=true;que_utiliza_alimentarlo.disabled=true'></td>" +
													"<td>&iquest;Cuales y con que frecuencia?</td><td><INPUT type='text' name='cuales_frecuencia_otra_leche' id='cuales_frecuencia_otra_leche' disabled></td>" +
													"<td>&iquest;Como prepara la otra leche?</td><td><INPUT type='text' name='prepara_otra_leche' id='prepara_otra_leche' disabled></td>" +
													"<td>&iquest;Que utiliza para alimentarlo?</td><td><INPUT type='text' name='que_utiliza_alimentarlo' id='que_utiliza_alimentarlo' disabled></td></tr>" +
													"<tr><td>PESO/EDAD</td><td><INPUT type='text' size='2.5' name='peso_edad' id='peso_edad' onkeypress='javascript:return validarNro(event)'> DE</td>" +
													"<td>PESO/TALLA</td><td><INPUT type='text' size='2.5' name='peso_talla' id='peso_talla' onkeypress='javascript:return validarNro(event)'> DE</td>" +
													"<td>Tendencia peso:</td><td><SELECT name='tendencia_peso' id='tendencia_peso'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Ascendente'>Ascendente</OPTION><OPTION value='Descendente'>Descendente</OPTION><OPTION value='Horizontal'>Horizontal</OPTION></SELECT></td></tr>" +
													
													"<tr><td><br><br><b>EVALUAR EL AGARRE:</b></td></tr>" +
													"<tr><td>Tiene la boca bien abierta</td><td><input type='checkbox' name='boca_abierta' id='si'></td>" +
													"<td>Toca el seno con el menton</td><td><input type='checkbox' name='toca_seno' id='si'></td>" +
													"<td>Labio inferior volteado hacia afuera</td><td><input type='checkbox' name='labio_inferior' id='si'></td>" +
													"<td>Se ve mas areola por encima del labio</td><td><input type='checkbox' name='areola_labio' id='si'></td></tr>" +
													"<tr><td><b>EVALUAR POSICION:</b></td></tr>" +
													"<tr><td>Cabeza y cuerpo del ni&ntilde;o derechos</td><td><input type='checkbox' name='cab_cuerpo_derecho' id='si'></td>" +
													"<td>Direccion al pecho/nariz frente pezon</td><td><input type='checkbox' name='direccion_pezon' id='si'></td>" +
													"<td>Hijo frente madre:barriga con barriga</td><td><input type='checkbox' name='hijo_frente_madre' id='si'></td>" +
													"<td>Madre sostiene todo el cuerpo</td><td><input type='checkbox' name='madre_sostiene_cuerpo' id='si'></td></tr>" +
													"<tr><td><b>EVALUAR SUCCION:</b></td></tr>" +
													"<tr><td>Lenta y profunda con pausas</td><td><input type='checkbox' name='lenta_profunda' id='si'></td></tr></table>");
											
											out.print("<table width='100%'><tr><br><br><td><b>PROBLEMA SEVERO DE ALIMENTACI&Oacute;N</b>&nbsp;&nbsp;<input type='checkbox' name='prob_severo_alimentacion' id='si'></td><td></td><td><b>PESO MUY BAJO</b>&nbsp;&nbsp;<input type='checkbox' name='peso_muy_bajo' id='si'></td>" +
													"<td></td><td><b>PROBLEMAS DE ALIMENTACI&Oacute;N</b>&nbsp;&nbsp;<input type='checkbox' name='problemas_alimentacion' id='si'></td><td></td><td><b>PESO BAJO O RIESGO</b>&nbsp;&nbsp;<input type='checkbox' name='peso_bajo' id='si'></td>" +
													"<td></td><td><b>ADECUADAS PR&Aacute;CTICAS DE ALIMENTACI&Oacute;N Y PESO ADECUADO</b>&nbsp;&nbsp;<input type='checkbox' name='practicas_alimentacion' id='si'></td></tr></table>");
											
						/*Ver Desarrollo*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI EXISTE PROBLEMA EN EL DESARROLLO</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>&iquest;Son parientes los padres?</td><td>Si<INPUT type='radio' name='rad_parientes_padres' id='si'>No<INPUT type='radio' name='rad_parientes_padres' id='no'></td>" +
													"<td>&iquest;Hay un familiar con problema mental o fisico?</td><td>Si<INPUT type='radio' name='rad_familiar_problema_mental' id='si'>No<INPUT type='radio' name='rad_familiar_problema_mental' id='no'></td>" +
													"<td>&iquest;Quien cuida al ni&ntilde;o?</td><td><INPUT type='text' name='quien_cuida_nino' id='quien_cuida_nino'></td>" +
													"<td>&iquest;Como ve el desarrollo del ni&ntilde;o?</td><td><INPUT type='text' name='desarrollo_nino' id='desarrollo_nino'></td></tr></table>");
											out.print("<table width='100%'><tr><td>Antecedente en Prenatal, Natal y Postnatal:</td>" +
													"<tr><td><textarea rows='3' cols='135' name='ant_natales' id='ant_natales'></textarea></td></tr>" +
													"<tr><td>Alteracion fenitipica" +
													"<tr><td><textarea rows='3' cols='135' name='alteracion_fenitipica' id='alteracion_fenitipica'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td>PC:</td><td><INPUT type='text' size='1' name='pc' id='pc' onkeypress='javascript:return validarNro(event)'> cm</td>" +
													"<td>PC/E:</td><td><INPUT type='text' size='1' name='pc_e' id='pc_e' onkeypress='javascript:return validarNro(event)'> DE</td></tr>" +
													
													"<tr><td><br><br><b>EL MENOR DE 1 MES REALIZA:</b></td></tr>" +
													"<tr><td>Reflejo de Moro</td><td><input type='checkbox' name='reflejo_moro' id='si'></td>" +
													"<td>Reflejo de succion</td><td><input type='checkbox' name='reflejo_succion' id='si'></td>" +
													"<td>Reflejo Cocleo-palpebral</td><td><input type='checkbox' name='reflejo_cocleo' id='si'></td>" +
													"<td>Manos cerradas</td><td><input type='checkbox' name='manos_cerradas' id='si'></td></tr>" +
													"<tr><td>Brazos y piernas flexionadas</td><td><input type='checkbox' name='brazos_piernas_flexionadas' id='si'></td></tr>" +
													"<tr><td><br><br><b>DE 1 A 2 MESES DE EDAD:</b></td></tr>" +
													"<tr><td>Vocaliza</td><td><input type='checkbox' name='vocaliza' id='si'></td>" +
													"<td>Sonrisa social</td><td><input type='checkbox' name='sonrisa_social' id='si'></td>" +
													"<td>Movimiento de piernas alterando</td><td><input type='checkbox' name='movimiento_piernas' id='si'></td>" +
													"<td>Sigue objetos en la linea media</td><td><input type='checkbox' name='sigue_objetos' id='si'></td></tr></table>");
											
											out.print("<table width='100%'><tr><br><br><td><b>PROBABLE RETRASO EN EL DESARROLLO</b><input type='checkbox' name='retraso_desarrollo' id='si'></td><td></td><td><b>RIESGO DE PROBLEMA O CON FACTORES DE RIESGO</b><input type='checkbox' name='riesgo_problema' id='si'></td>" +
													"<td></td><td><b>DESARROLLO NORMAL</b><input type='checkbox' name='desarrollo_normal' id='si'></td></tr></table>");
											
						/*Recomendaciones*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>RECOMENDACIONES</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>COMPLETAR EL EXAMEN FISICO Y EVALUAR OTROS PROBLEMAS</td>" +
													"<tr><td><textarea rows='3' cols='135' name='completar_examen_fisico' id='completar_examen_fisico'></textarea></td></tr></table>");
											
											out.print("<table width='100%'><tr><td>Diagnostico</td> " +
													"<td><INPUT type='text' size='50' name='txtNomDiagnos' id='txtNomDiagnos' onkeyup='autocompletarCIE10()' ></td>" +
													"<td>Codigo</td> " +
													"<td><INPUT type='text' readonly='' size='50' name='txtCodDiagnostico' id='txtCodDiagnostico'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='Ingresar' onclick='IngresarDxCeroDosCex()'></td> " +
													"</tr><tr><td></td><td><div id='SugeDiagnostico'></div></td><td></td><td></td></tr><tr><td colspan='4' id='dxAiepi'></td><td><input type='hidden' name='codPrincipalCIECeroDos' id='codPrincipalCIECeroDos' value='"+codigoPrincipalCIECeroDos+"' /></td></tr></table>");
								
											
											out.print("<table width='100%'><tr><td align='center'>TRATAR</td></tr>" +
													"<tr><td><textarea rows='5' cols='135' name='tratar_pac' id='tratar_pac'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td>ESCRIBIR LAS RECOMENDACIONES Y ORIENTACIONES DADAS SOBRE:</td></tr>" +
													"<tr><td><b>1.CUANDO VOLVER DE INMEDIATO AL SERVICIO(Signos de Alarma):</b></td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='volver_inmediato' id='volver_inmediato'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>2.CUANDO VOLVER A CONSULTA DE CONTROL:</b></td>" +
													"<td>RECIEN NACIDO:</td><td><INPUT type='text' name='recien_nacido' id='recien_nacido'></td>" +
													"<td>MADRE:</td><td><INPUT type='text' name='madre' id='madre'></td>" +
													"<td><b>3.CUANDO VOLVER A CONSULTA DE NI&Ntilde;O SANO:</b></td><td><INPUT type='text' name='nino_sano' id='nino_sano'></td>" +
													"<td><b>4.REFERIDO A CONSULTA DE:</b></td><td><INPUT type='text' name='referido_consulta' id='referido_consulta'></td></tr></table>");
											out.print("<table width=100%><tr><td><b>5.MEDIDAS PREVENTIVAS ESPECIFICAS:</b></td>" +
													"<td>-El 1&deg; mes,Despertar si en 3 horas no ha comido</td>" +
													"<td>-Leche materna exclusiva</td>" +
													"<td>-Sacar gases y acostar boca arriba</td>" +
													"<td>-Revision por medico a los 3 dia del alta</td></tr></table>");
											out.print("<table width='100%'><tr><td>Programa de Crecimiento y Desarrollo</td></tr>" +
													"<tr><td>Programa de Vacunacion</td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='programa_vacunacion' id='programa_vacunacion'></textarea></td></tr>" +
													"<tr><td><b>6.RECOMENDACIONES DE BUEN TRATO:</b></td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='recomendaciones_buen_trato' id='recomendaciones_buen_trato'></textarea></td></tr>" +
													"<tr><td><b>7.OTRAS RECOMENDACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='otras_recomendaciones' id='otras_recomendaciones'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td colspan='2'><div align='center'><br>" +
													"<INPUT id='guardar' type='button' value='Guardar Informe' onclick='GuardarAiepiCeroDosMesesCex()' disabled>" +
													//"<INPUT id='anular' type='button' value='Anular Informe' onclick='AnularInforme()'>" +
													"</div></td></tr></table>");
											
						/*AIEPI 2-5*/	}else if((a >= 2) && (a <= 60)){
											out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><td align='center' bgcolor='#104e8b' style='color:#FFFFFF'><b>AIEPI ATENCION DEL NI�O DE 2 MESES A 5 A�OS</b></td></tr></table>");
											out.print("<table width='80%'><tr><td><b>INSTITUCION:</b></td><td>"+rs1.getString("Entidad")+"</td><td></td><td><b>MUNICIPIO:</b></td><td>"+rs1.getString("Municipio")+"</td><td></td><td><b>N� HISTORIA CLINICA:</b></td><td>"+rs1.getString("adm_numero_ingreso")+"</td></tr>" +
													"<tr><td><b>NOMBRE:</b></td><td>"+rs1.getString("NombreCompleto")+"</td><td></td><td><b>EDAD:</b></td><td>"+rs1.getString("EdadAnos")+" a�os</td><td>"+rs1.getString("EdadMeses")+" meses</td><td><input type='hidden' name='CodAdmision' id='CodAdmision' value='"+CodAdm+"' /></td></tr>" +
													"<tr><td><b>FECHA NACIMIENTO:</b></td><td>"+rs1.getString("fecha_nacimiento")+"</td><td></td><td><b>SEXO:</b></td><td>"+rs1.getString("genero")+"</td><td><input type='hidden' name='txtCodReporte' id='txtCodReporte'/></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>DATOS ACOMPA&Ntilde;ANTE</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>"+rs1.getString("responsable")+"</td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>MOTIVO DE CONSULTA Y ENFERMEDAD ACTUAL:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='mot_consulta' id='mot_consulta'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>ANTECEDENTE PRENATALES, NATALES Y POSTNATALES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='ant_natales' id='ant_natales'></textarea></td></tr></table>");
											//out.print("<table width='100%'><tr><td>�C�mo fue el embarazo? y �Cu�nto dur�?</td><td><input type='text' size='70'></td><td>�C�mo fue el parto?</td><td><input type='text' size='70'></td></tr></table>");
											//out.print("<table width='70%'><tr><td>Peso al nacer</td><td><input type='text' size='1'> gr.</td><td><input type='hidden' size='1'></td><td>TALLA al nacer</td><td><input type='text' size='1'> cm.</td><td><input type='hidden' size='1'></td><td>�Present� alg�n problema neonatal?</td><td><input type='text'></td></tr></table>");
											//out.print("<table width='100%'><tr><td>Enfermedades previas y hospitalizaciones</td></tr>" +
													//"<tr><td><textarea rows='4' cols='135' name='enf_previas_hospitalizaciones' id='enf_previas_hospitalizaciones'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>APF (ANTECEDENTES PATOL&Oacute;GICOS FAMILIARES):</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='apf' id='apf'></textarea></td></tr></table>");
											
											out.print("<table width='100%'><tr><td>TEMPERATURA</td><td><input type='text' size='1' name='temperatura' id='temperatura' onkeypress='javascript:return validarNro(event)'> �C</td>"
													+ "<td>FC</td><td><input type='text' size='1' name='frecuencia_cardiaca' id='frecuencia_cardiaca' onkeypress='javascript:return validarNro(event)'> /min</td>" +
													"<td>FR</td><td><input type='text' size='1' name='frecuencia_respiratoria' id='frecuencia_respiratoria' onkeypress='javascript:return validarNro(event)'> /min"
													+ "<td>TALLA</td><td><input type='text' size='1' name='talla' id='talla' onkeypress='javascript:return validarNro(event)'> cm.</td>" +
													"<td>Peso</td><td><input type='text' size='1' name='peso' id='peso' onkeypress='javascript:return validarNro(event)' onblur='p()'> Kg</td>"
													+ "<td>PC</td><td><input type='text' size='1' name='perimetro_cefalico' id='perimetro_cefalico' onkeypress='javascript:return validarNro(event)'> cm.</td>" +
													"<td>IMC</td><td><input type='text' size='1' name='imc' id='imc' onkeypress='javascript:return validarNro(event)'></td></tr></table>");
											
						/*Ver Sig Peligro*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI EXISTEN SIGNOS DE PELIGRO EN GENERAL</b></td></tr></table>");
											out.print("<table width='60%'><tr><td>No puede beber o tomar del pecho</td><td><input type='checkbox' name='no_bebe_pecho' id='si'></td>"
													+ "<td>Let&aacute;rgico o inconsciente</td><td><input type='checkbox' name='letargico' id='si'></td>" +
													"<td>Vomita todo</td><td><input type='checkbox' name='vomita_todo' id='si'></td>"
													+ "<td>Convulsiones</td><td><input type='checkbox' name='convulsiones' id='si'></td></tr></table>"+
													"<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr><tr><td><textarea rows='4' cols='135' name='obs_signos_peligro' id='obs_signos_peligro'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>ENFERMEDAD MUY GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='enf_muy_grave' id='si'></td></tr></table>");
											
						/*�TIENE TOS?*/		out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>�TIENE TOS O DIFICULTAD PARA RESPIRAR?</b></td><td>Si<INPUT type='radio' name='rad_dificultad_respirar' id='si' onclick='preguntas_tos.disabled=false;desde_cuando_tos.focus()'>No<INPUT type='radio' name='rad_dificultad_respirar' id='no' onclick='preguntas_tos.disabled=true'></td></tr></table>");
											out.print("<table width='100%' name='preguntas_tos 'id='preguntas_tos' disabled><tr><td>Desde hace</td><td><input type='text' size='1' name='desde_cuando_tos' id='desde_cuando_tos' onkeypress='javascript:return validarNro(event)'> d�as</td></tr>" +
													"<tr><td>Primer episodio sibilancias:</td><td>Si<INPUT type='radio' name='rad_episodio_sibilancias' id='si'>No<INPUT type='radio' name='rad_episodio_sibilancias' id='no'></td><td>Sibilancias recurrentes:</td><td>Si<INPUT type='radio' name='rad_sibilancias_recurrentes' id='si'>No<INPUT type='radio' name='rad_sibilancias_recurrentes' id='no'></td>" +
													"<td>Cuadro gripal &uacute;ltimos 3 d&iacute;as:</td><td>Si<INPUT type='radio' name='rad_cuadro_gripal' id='si'>No<INPUT type='radio' name='rad_cuadro_gripal' id='no'></td><td>Antecedentes prematuridad:</td><td>Si<INPUT type='radio' name='rad_antecedentes_prematuridad' id='si'>No<INPUT type='radio' name='rad_antecedentes_prematuridad' id='no'></td></tr>" +
													"<tr><td><br><br>Respiraciones por minutos</td><td><br><br><input type='text' size='1' name='respiraciones_minutos' id='respiraciones_minutos' onkeypress='javascript:return validarNro(event)'></td><td><br><br>Respiracion Rapida</td><td><br><br><input type='checkbox' name='respiracion_rapida' id='si'></td>" +
													"<td><br><br>Tiraje subcostal</td><td><br><br><input type='checkbox' name='tiraje_subcostal' id='si'></td><td><br><br>SaO2 <92% (90% altura >2500 msnm)</td><td><br><br><input type='checkbox' name='saturacion_oxigeno' id='si'></td></tr>" +
													"<tr><td>Tiraje supraclavicular</td><td><input type='checkbox' name='tiraje_supraclavicular' id='si'></td><td>Estridor</td><td><input type='checkbox' name='estridor' id='si'></td>" +
													"<td>Sibilancias</td><td><input type='checkbox' name='sibilancias' id='si'></td><td>Apnea</td><td><input type='checkbox' name='apnea' id='si'></td></tr>" +
													"<tr><td>Incapacidad para hablar o beber</td><td><input type='checkbox' name='incapacidad_hablar' id='si'></td><td>Somnoliento</td><td><input type='checkbox' name='somnoliento' id='si'></td>" +
													"<td>Confuso</td><td><input type='checkbox' name='confuso' id='si'></td><td>Agitado</td><td><input type='checkbox' name='agitado' id='si'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_tos' id='obs_tos'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>GRUP GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='grup_grave' id='si'></td><td><b>BRONQUIOLITIS GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='bronquiolitis_grave' id='si'></td>" +
													"<td><b>SIBILANCIA (RECURRENTE) GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='sibilancia_grave' id='si'></td><td><b>CRUP</b>&nbsp;&nbsp;<input type='checkbox' name='crup' id='si'></td></tr>" +
													"<tr><td><br><br><b>BRONQUIOLITIS:</b></td></tr>" +
													"<tr><td><b>SIBILANCIA (RECURRENTE)</b>&nbsp;&nbsp;<input type='checkbox' name='sibilancia' id='si'></td><td><b>NEUMON�A GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='neumonia_grave' id='si'></td>" +
													"<td><b>NEUMON&Iacute;A</b>&nbsp;&nbsp;<input type='checkbox' name='neumonia' id='si'></td><td><b>TOS O RESFRIADO</b>&nbsp;&nbsp;<input type='checkbox' name='tos' id='si'></td></table>");
											
						/*�TIENE DIARREA?*/	out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>�TIENE DIARREA?</b></td><td>Si<INPUT type='radio' name='rad_tiene_diarrea' id='si' onclick='preguntas_diarrea.disabled=false;desde_cuando_diarrea.focus();'>No<INPUT type='radio' name='rad_tiene_diarrea' id='no' onclick='preguntas_diarrea.disabled=true'></td></tr></table>");
											out.print("<table width='100%' name='preguntas_diarrea' id='preguntas_diarrea' disabled><tr><td>Desde hace</td><td><input type='text' size='1' name='desde_cuando_diarrea' id='desde_cuando_diarrea' onkeypress='javascript:return validarNro(event)'> d�as</td><td>�Hay sangre en las heces?</td><td>Si<INPUT type='radio' name='rad_sangre_heces' id='si'>No<INPUT type='radio' name='rad_sangre_heces' id='no'></td></tr>" +
													"<tr><td>&iquest;Tiene vomito?</td><td>Si<INPUT type='radio' name='rad_vomito' id='si'>No<INPUT type='radio' name='rad_vomito' id='no'></td><td>N� Vomitos en las �ltimas 4h</td><td><input type='text' size='1' name='num_vomitos' id='num_vomitos' onkeypress='javascript:return validarNro(event)'></td>" +
													"<td>N&deg; Diarreas en las &uacute;ltimas 24h</td><td><input type='text' size='1' name='num_diarreas_vc' id='num_diarreas_vc' onkeypress='javascript:return validarNro(event)'></td><td>N� Diarreas en las �ltimas 4h</td><td><input type='text' size='1' name='num_diarreas_c' id='num_diarreas_c' onkeypress='javascript:return validarNro(event)'></td></tr>" +
													"<tr><td><br><br>Letargico o comatoso</td><td><br><br><input type='checkbox' name='comatoso' id='si'></td><td><br><br>Intranquilo o irritable</td><td><br><br><input type='checkbox' name='intranquilo' id='si'></td>" +
													"<td><br><br>Ojos Hundidos</td><td><br><br><input type='checkbox' name='ojos_hundidos' id='si'></td><td><br><br>Bebe mal o no puede beber</td><td><br><br><input type='checkbox' name='bebe_mal' id='si'></td></tr>" +
													"<tr><td>Bebe &aacute;vidamente con sed</td><td><input type='checkbox' name='bebe_avidamente' id='si'></td>" +
													"<td>Pliegue Cutaneo:</td><td><SELECT name='pliegue_cutaneo' id='pliegue_cutaneo'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Inmediato'>Inmediato</OPTION><OPTION value='Lento'>Lento</OPTION><OPTION value='Muy Lento'>Muy Lento</OPTION></SELECT></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_diarrea' id='obs_diarrea'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>DESHIDRATACI&Oacute;N GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='deshidratacion_grave' id='si'></td><td><b>ALG&Uacute;N GRADO DESHIDRATACI&Oacute;N</b>&nbsp;&nbsp;<input type='checkbox' name='grado_deshidratacion' id='si'></td>" +
													"<td><b>ALTO RIESGO DESHIDRATACI&Oacute;N</b>&nbsp;&nbsp;<input type='checkbox' name='alto_riesgo_deshidratacion' id='si'></td><td><b>SIN DESHIDRATACI&Oacute;N</b>&nbsp;&nbsp;<input type='checkbox' name='sin_deshidratacion' id='si'></td></tr>" +
													"<tr><td><b>DIARREA PERSISTENTE GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_persistente_grave' id='si'></td><td><b>DIARREA PERSISTENTE</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_persistente' id='si'></td>" +
													"<td><b>DISENTER&Iacute;A</b>&nbsp;&nbsp;<input type='checkbox' name='disenteria' id='si'></td></tr></table>");
											
						/*�TIENE FIEBRE?*/	out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>&iquest;TIENE FIEBRE?</b></td><td>Si<INPUT type='radio' name='rad_tiene_fiebre' id='si' onclick='preguntas_fiebre.disabled=false;desde_cuando_fiebre.focus()'>No<INPUT type='radio' name='rad_tiene_fiebre' id='no' onclick='preguntas_fiebre.disabled=true'></td></tr></table>");
											out.print("<table width='100%' name='preguntas_fiebre' id='preguntas_fiebre' disabled><tr><td>Desde hace</td><td><input type='text' size='1' name='desde_cuando_fiebre' id='desde_cuando_fiebre' onkeypress='javascript:return validarNro(event)'> d&iacute;as</td>" +
													"<td>Fiebre >38 &deg;C</td><td>Si<INPUT type='radio' name='rad_fiebre_to' id='si'>No<INPUT type='radio' name='rad_fiebre_to' id='no'></td><td> Fiebre >39 %deg;C</td><td>Si<INPUT type='radio' name='rad_fiebre_tn' id='si'>No<INPUT type='radio' name='rad_fiebre_tn' id='no'></td></tr>" +
													"<tr><td><b>Vive o visit&oacute; en los &uacute;ltimos 15 d&iacute;as</b></td><td>Si<INPUT type='radio' name='rad_vive_quince_dias' id='si'>No<INPUT type='radio' name='rad_vive_quince_dias' id='no'></td></tr>" +
													"<tr><td>Zona Dengue(altura <2200 m)</td><td><input type='checkbox' name='zona_dengue' id='si'></td><td>Zona Malaria:</td><td><SELECT name='zona_malaria' id='zona_malaria'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Urbana'>Urbana</OPTION><OPTION value='Rural'>Rural</OPTION></SELECT></td></tr>" +
													
													"<tr><td><br><br>Rigidez de nuca</td><td><br><br><input type='checkbox' name='rigidez_nuca' id='si'></td><td><br><br>Apariencia de enfermo grave</td><td><br><br><input type='checkbox' name='apariencia_enfermo' id='si'></td>" +
													"<td><br><br>Manifestaciones de sangrado</td><td><br><br><input type='checkbox' name='manifestaciones_sangrado' id='si'></td><td><br><br>Aspecto toxico</td><td><br><br><input type='checkbox' name='aspecto_toxico' id='si'></td></tr>" +
													"<tr><td>Respuesta social:</td><td><SELECT name='respuesta_social' id='respuesta_social'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Normal'>Normal</OPTION><OPTION value='Inadecuada'>Inadecuada</OPTION><OPTION value='Sin Respuesta'>Sin Respuesta</OPTION></SELECT></td>" +
													"<td>Piel:</td><td><SELECT name='piel' id='piel'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Palida'>Palida</OPTION><OPTION value='Moteada'>Moteada</OPTION><OPTION value='Cenicienta'>Cenicienta</OPTION><OPTION value='Azul'>Azul</OPTION></SELECT></td>" +
													"<td>Erupcion cutanea generalizada</td><td><input type='checkbox' name='erupcion_cutanea' id='si'></td><td>Dolor Abdominal</td><td><input type='checkbox' name='dolor_abdominal' id='si'></td></tr>" +
													"<tr><td>Cefalea</td><td><input type='checkbox' name='cefalea' id='si'></td><td>Mialgias</td><td><input type='checkbox' name='mialgias' id='si'></td>" +
													"<td>Artralgias</td><td><input type='checkbox' name='artralgias' id='si'></td><td>Dolor retroocular</td><td><input type='checkbox' name='dolor_retroocular' id='si'></td></tr>" +
													"<tr><td>Postraci&oacute;n</td><td><input type='checkbox' name='postracion' id='si'></td><td>P. Torniquete</td><td><input type='checkbox' name='p_torniquete' id='si'></td>" +
													"<td>Lipotimia</td><td><input type='checkbox' name='lipotimia' id='si'></td><td>Hepatomegalia</td><td><input type='checkbox' name='hepatomegalia' id='si'></td></tr>" +
													"<tr><td>Pulso r&aacute;pido y fino</td><td><input type='checkbox' name='pulso_rapido' id='si'></td><td>Llenado capilar >2 seg.</td><td><input type='checkbox' name='llenado_capilar' id='si'></td>" +
													"<td>Ascitis</td><td><input type='checkbox' name='ascitis' id='si'></td><td>Disminuci&oacute;n diuresis:</td><td><input type='checkbox' name='disminucion_diuresis' id='si'></td></tr>" +			

													"<tr><td><br><br><b>LABORATORIOS:</b></td></tr>" +
													"<tr><td>CH >15000</td><td><input type='checkbox' name='cuadro_hematico' id='si'></td><td>Leucocitos <4000</td><td><input type='checkbox' name='leucocitos' id='si'></td>" +
													"<td>Neutr&oacute;filos >10000</td><td><input type='checkbox' name='neutrofilos' id='si'></td><td>Plaquetas <100000</td><td><input type='checkbox' name='plaquetas' id='si'></td></tr>" +
													"<tr><td>Parcial de Orina compatible con infecci&oacute;n</td><td><input type='checkbox' name='parcial_orina' id='si'></td><td>Gota gruesa positiva</td><td><input type='checkbox' name='gota_gruesa' id='si'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_fiebre' id='obs_fiebre'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>ENF. FEBRIL DE RIESGO</b>&nbsp;&nbsp;<input type='checkbox' name='enf_febril_riesgo' id='si'></td><td><b>ENF. FEBRIL DE RIESGO INTERMEDIO</b>&nbsp;&nbsp;<input type='checkbox' name='enf_febril_riesgo_intermedio' id='si'></td>" +
													"<td><b>ENF. FEBRIL DE RIESGO BAJO</b>&nbsp;&nbsp;<input type='checkbox' name='enf_febril_riesgo_bajo' id='si'></td><td><b>MALAR&Iacute;A COMPLICADA</b>&nbsp;&nbsp;<input type='checkbox' name='malaria_complicada' id='si'></td></tr>" +
													"<tr><td><b>MALAR&Iacute;A</b>&nbsp;&nbsp;<input type='checkbox' name='malaria' id='si'></td><td><b>DENGUE GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='dengue_grave' id='si'></td>" +
													"<td><b>DENGUE CON SIGNOS ALARMA</b>&nbsp;&nbsp;<input type='checkbox' name='dengue_signos_alarma' id='si'></td><td><b>PROBABLE DENGUE</b>&nbsp;&nbsp;<input type='checkbox' name='probable_dengue' id='si'></td></tr></table>");
											
						/*�TIENE PROBLEMA DE  OIDO?*/	out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>&iquest;TIENE PROBLEMA DE OIDO?</b></td><td>Si<INPUT type='radio' name='rad_tiene_prob_oido' id='si' onclick='preguntas_oido.disabled=false'>No<INPUT type='radio' name='rad_tiene_prob_oido' id='no' onclick='preguntas_oido.disabled=true'></td></tr></table>");
											out.print("<table width='100%' name='preguntas_oido' id='preguntas_oido' disabled><tr><td>&iquest;Tiene dolor de o&iacute;do?:</td><td>Si<INPUT type='radio' name='rad_tiene_dolor_oido' id='si'>No<INPUT type='radio' name='rad_tiene_dolor_oido' id='no'></td>" +
													"<td>&iquest;Tiene supuraci&oacute;n?:</td><td>Si<INPUT type='radio' name='rad_tiene_supuracion' id='si' onclick='desde_cuando_supuracion.disabled=false;desde_cuando_supuracion.focus();num_episodios_previos.disabled=false'>No<INPUT type='radio' name='rad_tiene_supuracion' id='no' onclick='desde_cuando_supuracion.disabled=true;num_episodios_previos.disabled=true'></td><td>Hace</td><td><input type='text' size='1' name='desde_cuando_supuracion' id='desde_cuando_supuracion' disabled onkeypress='javascript:return validarNro(event)'> d&iacute;as</td>" +
													"<td>No episodios previos:</td><td><input type='text' size='1' name='num_episodios_previos' id='num_episodios_previos' disabled onkeypress='javascript:return validarNro(event)'></td></tr>" +
													"<tr><td><br><br>Tumefacci%oacute;n dolorosa detr&aacute;s de la oreja</td><td><br><br><input type='checkbox' name='tumefaccion_dolorosa' id='si'></td><td><br><br>T&iacute;mpano Rojo y Abombado</td><td><br><br><input type='checkbox' name='timpano_rojo' id='si'></td>" +
													"<td><br><br>Supuraci&oacute;n de o&iacute;do</td><td><br><br><input type='checkbox' name='supuracion_oido' id='si'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_oido' id='obs_oido'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>MASTOIDITIS</b>&nbsp;&nbsp;<input type='checkbox' name='mastoiditis' id='si'></td><td><b>OTITIS MEDIA CR&Oacute;NICA</b>&nbsp;&nbsp;<input type='checkbox' name='otitis_media_cronica' id='si'></td>" +
													"<td><b>OTITIS MEDIA RECURRENTE</b>&nbsp;&nbsp;<input type='checkbox' name='otitis_media_recurrente' id='si'></td><td><b>OTITIS MEDIA AGUDA</b>&nbsp;&nbsp;<input type='checkbox' name='otitis_media_aguda' id='si'></td>" +
													"<td><b>NO TIENE OTITIS</b>&nbsp;&nbsp;<input type='checkbox' name='no_tiene_otitis' id='si'></td></tr></table>");
											
						/*�TIENE PROBLEMA DE GARGANTA?*/out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>&iquest;TIENE UN PROBLEMA DE GARGANTA?</b></td><td>Si<INPUT type='radio' name='rad_tiene_prob_garganta' id='si' onclick='preguntas_garganta.disabled=false;checkboxs_garganta.disabled=false'>No<INPUT type='radio' name='rad_tiene_prob_garganta' id='no' onclick='preguntas_garganta.disabled=true;checkboxs_garganta.disabled=true'></td></tr></table>");
											out.print("<table width='30%' name='preguntas_garganta' id='preguntas_garganta' disabled><tr><td>&iquest;Tiene dolor de garganta?</td><td>Si<INPUT type='radio' name='tiene_dolor_garganta' id='si'>No<INPUT type='radio' name='tiene_dolor_garganta' id='no'></td></tr></table>");
											out.print("<table width='60%' name='checkboxs_garganta' id='checkboxs_garganta' disabled><tr><br><br><td>Ganglios del cuello crecidos y dolorosos</td><td><input type='checkbox' name='ganglios_cuello' id='si'></td><td>Am&iacute;gdalas eritematosas</td><td><input type='checkbox' name='amigdalas_eritematosas' id='si'></td>" +
													"<td>Exudado blanquecino - amarillento en amigdalas</td><td><input type='checkbox' name='exudado_blanquecino' id='si'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_garganta' id='obs_garganta'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>FARINGOAMIGDALITIS</b>&nbsp;&nbsp;<input type='checkbox' name='faringoamigdalitis' id='si'></td><td><b>ESTREPTOC�CICA</b>&nbsp;&nbsp;<input type='checkbox' name='estreptococica' id='si'></td>" +
													"<td><b>FARINGOAMIGDALITIS VIRAL</b>&nbsp;&nbsp;<input type='checkbox' name='faringoamigdalitis_viral' id='si'></td><td><b>NO TIENE FARINGOAMIGDALITIS</b>&nbsp;&nbsp;<input type='checkbox' name='no_tiene_faringoamigdalitis' id='si'></td></tr></table>");
											
						/*VERIFICAR SBUCAL*/out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>ENSEGUIDA, VERIFICAR LA SALUD BUCAL</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>&iquest;Tiene dolor al comer-masticar?</td><td>Si<INPUT type='radio' name='rad_tiene_dolor_comer' id='si'>No<INPUT type='radio' name='rad_tiene_dolor_comer' id='no'></td><td>�Tiene dolor en diente?</td><td>Si<INPUT type='radio' name='rad_tiene_dolor_diente' id='si'>No<INPUT type='radio' name='rad_tiene_dolor_diente' id='no'></td>" +
													"<td>&iquest;Trauma en cara o boca?</td><td>Si<INPUT type='radio' name='rad_trauma_cara' id='si'>No<INPUT type='radio' name='rad_trauma_cara' id='no'></td><td>�Tiene padres/hermanos caries?</td><td>Si<INPUT type='radio' name='rad_tiene_caries' id='si'>No<INPUT type='radio' name='rad_tiene_caries' id='no'></td></tr>" +
													"<tr><td><b>&iquest;CU&aacute;NDO LE LIMPIA LA BOCA?</b></td></tr>" +
													"<tr><td>Ma&ntilde;ana</td><td>Si<INPUT type='radio' name='rad_limpia_boca_manana' id='si'>No<INPUT type='radio' name='rad_limpia_boca_manana' id='no'></td>" +
													"<td>Tarde</td><td>Si<INPUT type='radio' name='rad_limpia_boca_tarde' id='si'>No<INPUT type='radio' name='rad_limpia_boca_tarde' id='no'></td>" +
													"<td>Noche</td><td>Si<INPUT type='radio' name='rad_limpia_boca_noche' id='si'>No<INPUT type='radio' name='rad_limpia_boca_noche' id='no'></td></tr>" +
													"<tr><td><b>&iquest;C&Oacute;MO SUPERVISA LIMPIEZA?</b></td></tr>" +
													"<tr><td>Le limpia los Dientes</td><td>Si<INPUT type='radio' name='rad_limpia_dientes' id='si'>No<INPUT type='radio' name='rad_limpia_dientes' id='no'></td><td>Ni�o solo</td><td>Si<INPUT type='radio' name='rad_nino_solo' id='si'>No<INPUT type='radio' name='rad_nino_solo' id='no'></td></tr>" +
													"<tr><td><b>&iquest;QU&Eacute; UTILIZA?</b></td></tr>" +
													"<tr><td>Cepillo:</td><td>Si<INPUT type='radio' name='rad_cepillo' id='si'>No<INPUT type='radio' name='rad_cepillo' id='no'></td><td>Crema:</td><td>Si<INPUT type='radio' name='rad_crema' id='si'>No<INPUT type='radio' name='rad_crema' id='no'></td>" +
													"<td>Seda:</td><td>Si<INPUT type='radio' name='rad_seda' id='si'>No<INPUT type='radio' name='rad_seda' id='no'></td><td>&iquest;Utiliza chupo o biber�n?</td><td>Si<INPUT type='radio' name='rad_utiliza_chupo' id='si'>No<INPUT type='radio' name='rad_utiliza_chupo' id='no'></td></tr></table>");
											out.print("<table><tr><td>&iquest;Cu&aacute;ndo fue la &uacute;ltima consulta odontol&oacute;gica?</td><td><input type='text' size='70' name='ultima_cons_odontologica' id='ultima_consulta_odontologica'></td></tr></table>");
											
											out.print("<table width='100%'><tr><br><br><td>Inflamaci�n dolorosa del labio</td><td><input type='checkbox' name='inflamacion_labio' id='si'></td><td>No involucra surco</td><td><input type='checkbox' name='no_involucra_surco' id='si'></td>" +
													"<td>Enrojecimiento</td><td><input type='checkbox' name='enrojecimiento' id='si'></td><td>Inflamaci�n enc�a</td><td><input type='checkbox' name='inflamacion_encia' id='si'></td></tr>" +
													"<tr><td>Localizado</td><td><input type='checkbox' name='localizado' id='si'></td><td>Generalizado</td><td><input type='checkbox' name='generalizado' id='si'></td>" +
													"<td>Deformaci�n contorno de enc�a</td><td><input type='checkbox' name='deformacion_encia' id='si'></td><td>Exudado-pus</td><td><input type='checkbox' name='exudado_pus' id='si'></td></tr>" +
													"<tr><td>Vesiculas</td><td><input type='checkbox' name='vesiculas' id='si'></td><td>�lceras</td><td><input type='checkbox' name='ulceras' id='si'></td>" +
													"<td>Placas:</td><td><SELECT name='placas' id='placas'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Encia'>Enc�a</OPTION><OPTION value='Lengua Paladar'>Lengua Paladar</OPTION></SELECT></td></td><td>Fractura</td><td><input type='checkbox' name='fractura' id='si'></td></tr>" +
													"<tr><td>Movilidad</td><td><input type='checkbox' name='movilidad' id='si'></td><td>Desplazamiento</td><td><input type='checkbox' name='desplazamiento' id='si'></td>" +
													"<td>Extrusi�n</td><td><input type='checkbox' name='extrusion' id='si'></td><td>Intrusi�n</td><td><input type='checkbox' name='intrusion' id='si'></td></tr>" +
													"<tr><td>Avulsi�n</td><td><input type='checkbox' name='avulsion' id='si'></td><td>Herida</td><td><SELECT name='herida' id='herida'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Mucosa Bucal'>Mucosa Bucal</OPTION><OPTION value='Encia'>Enc�a</OPTION><OPTION value='Lengua'>Lengua</OPTION></SELECT></td><td>Manchas Blancas</td><td><input type='checkbox' name='manchas_blancas' id='si'></td>" +
													"<td>Caf�s</td><td><input type='checkbox' name='cafes' id='si'></td></tr>" +
													"<tr><td>Caries cavitacionales</td><td><input type='checkbox' name='caries_cavitacionales' id='si'></td><td>Placa bacteriana</td><td><input type='checkbox' name='placa_bacteriana' id='si'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_salud_bucal' id='obs_salud_bucal'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>CELULITIS FACIAL</b>&nbsp;&nbsp;<input type='checkbox' name='celulitis_facial' id='si'></td><td><b>ENFERMEDAD BUCAL GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='enf_bucal_grave' id='si'></td>" +
													"<td><b>TRAUMA BUCODENTAL</b>&nbsp;&nbsp;<input type='checkbox' name='trauma_bucodental' id='si'></td><td><b>ESTOMATITIS</b>&nbsp;&nbsp;<input type='checkbox' name='estomatitis' id='si'></td>" +
													"<td><b>ENFERMEDAD DENTAL Y GINGIVAL</b>&nbsp;&nbsp;<input type='checkbox' name='enf_dental' id='si'></td><td><b>ALTO RIESGO DE ENFERMEDAD BUCAL</b>&nbsp;&nbsp;<input type='checkbox' name='alto_riesgo_enf_bucal' id='si'></td>" +
													"<td><b>BAJO RIESGO DE ENFERMEDAD BUCAL</b>&nbsp;&nbsp;<input type='checkbox' name='bajo_riesgo_enf_bucal' id='si'></td></tr></table>");
											
						/*VER. CRECIMIENTO*/out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR EL CRECIMIENTO:</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>Emanaci�n visible</td><td>Si<INPUT type='radio' name='emanacion_visible' id='si'>No<INPUT type='radio' name='emanacion_visible' id='no'></td><td>Peso/Edad:</td><td><input type='text' size='1' name='peso_edad' id='peso_edad' onkeypress='javascript:return validarNro(event)'> DE</td>" +
													"<td>Edema en ambos pies</td><td>Si<INPUT type='radio' name='rad_edema_pies' id='si'>No<INPUT type='radio' name='rad_edema_pies' id='no'></td><td>Apariencia</td><td><input type='text' name='apariencia' id='apariencia'></tr>" +
													"<tr><td>IMC/Edad:DE:</td><td><input type='text' name='imc_edad' id='imc_edad' onkeypress='javascript:return validarNro(event)'></td><td>Talla/Edad:DE:</td><td><input type='text' name='talla_edad' id='talla_edad' onkeypress='javascript:return validarNro(event)'></td>" +
													"<td>Peso/Talla:DE:</td><td><input type='text' name='peso_talla' id='peso_talla' onkeypress='javascript:return validarNro(event)'></td><td>Tendencia Peso:</td><td><SELECT name='tendencia_peso' id='tendencia_peso'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Ascendente'>Ascendente</OPTION><OPTION value='Horizontal'>Horizontal</OPTION><OPTION value='Descendente'>Descendente</OPTION></SELECT></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><-3 Desnutrici�n global severa</td><td><input type='checkbox' name='desnutricion_global_severa' id='si'></td><td><-2 a >-3 Desnutrici�n global</td><td><input type='checkbox' name='desnutricion_global' id='si'></td>" +
													"<td><-1 a >-2 Riesgo de Desnutrici�n</td><td><input type='checkbox' name='riesgo_desnutricion' id='si'></td><td><1 a >-1 Peso adecuado para edad</td><td><input type='checkbox' name='peso_adecuado_edad' id='si'></td></tr>" +
													"<tr><td><-2 Desnutrici�n cr�nica o Retraso crecimiento</td><td><input type='checkbox' name='desnutricion_cronica' id='si'></td><td>>-2 a <-1 Riesgo DNT con bajo P/T</td><td><input type='checkbox' name='riesgo_DNT' id='si'></td>" +
													"<td>>-1 Talla adecuada / edad</td><td><input type='checkbox' name='talla_adecuada_edad' id='si'></td><td><-3 Desnutrici�n Aguda Severa</td><td><input type='checkbox' name='desnutricion_aguda' id='si'></td></tr>" +
													"<tr><td>>-3 a <-2 DNT Aguda-Peso bajo/Talla</td><td><input type='checkbox' name='DNT_aguda' id='si'></td><td>>-1 a <1 Peso adecuado para Talla</td><td><input type='checkbox' name='peso_adecuado_talla' id='si'></td>" +
													"<td>>1 a <2 Sobrepeso</td><td><input type='checkbox' name='sobrepeso' id='si'></td><td>>2 Obesidad</td><td><input type='checkbox' name='obesidad' id='si'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_crecimiento' id='obs_crecimiento'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>OBESO</b>&nbsp;&nbsp;<input type='checkbox' name='obeso' id='si'></td><td><b>SOBREPESO</b>&nbsp;&nbsp;<input type='checkbox' name='sobrepeso_r' id='si'></td>" +
													"<td><b>DESNUTRICI�N SEVERA</b>&nbsp;&nbsp;<input type='checkbox' name='desnutricion_severa' id='si'></td><td><b>DESNUTRICI�N</b>&nbsp;&nbsp;<input type='checkbox' name='desnutricion' id='si'></td>" +
													"<td><b>RIESGO DESNUTRICI�N</b>&nbsp;&nbsp;<input type='checkbox' name='riesgo_desnutricion_r'></td><td><b>ADECUADO ESTADO NUTRICIONAL</b>&nbsp;&nbsp;<input type='checkbox' name='estado_nutricional' id='si'></td></tr></table>");
											
						/*VER. ANEMIA*/		out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI TIENE ANEMIA</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>Ha recibido hierro en los �ltimos 6 meses:</td><td>Si<INPUT type='radio' name='rad_recibido_hierro' id='si' onclick='cuando_recibido_hierro.disabled=false;cuando_recibido_hierro.focus();cuanto_tiempo_recibido_hierro.disabled=false'>No<INPUT type='radio' name='rad_recibido_hierro' id='no' onclick='cuando_recibido_hierro.disabled=true;cuanto_tiempo_recibido_hierro.disabled=true'></td>" +
													"<td>�Cu�ndo?</td><td><input type='text' name='cuando_recibido_hierro' id='cuando_recibido_hierro' disabled></td>"
													+ "<td>�Cu�nto tiempo?</td><td><input type='text' name='cuanto_tiempo_recibido_hierro' id='cuanto_tiempo_recibido_hierro' disabled></td></tr>" +
													"<tr><td>Palidez palmar:</td><td><SELECT name='palidez_palmar' id='palidez_palmar'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Intensa'>Intensa</OPTION><OPTION value='Leve'>Leve</OPTION><OPTION value='No'>No</OPTION></SELECT></td>" +
													"<td>Palidez conjuntival</td><td><SELECT name='palidez_conjuntival' id='palidez_conjuntival'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Intensa'>Intensa</OPTION><OPTION value='Leve'>Leve</OPTION><OPTION value='No'>No</OPTION></SELECT></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_anemia' id='obs_anemia'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>ANEMIA SEVERA</b>&nbsp;&nbsp;<input type='checkbox' name='anemia_severa' id='si'></td><td><b>ANEMIA</b>&nbsp;&nbsp;<input type='checkbox' name='anemia' id='si'></td>" +
													"<td><b>NO TIENE ANEMIA</b>&nbsp;&nbsp;<input type='checkbox' name='no_tiene_anemia' id='si'></td></tr></table>");
											
						/*VER. MALTRATO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>ENSEGUIDA, VERIFICAR SI TIENE MALTRATO</b></td></tr></table>");
											out.print("<table><tr><td>�C�mo se produjeron las lesiones?</td><td><input type='text' size='70' name='produjeron_lesiones' id='produjeron_lesiones'></td></tr></table>");
											out.print("<table width='100%'><tr><td>�El ni�o relata maltrato?</td><td>Si<INPUT type='radio' name='rad_relata_maltrato' id='si' onclick='quien_maltrato.disabled=false;malt_fisico.disabled=false;malt_sexual.disabled=false;negligencia.disabled=false'>No<INPUT type='radio' name='rad_relata_maltrato' id='no' onclick='malt_fisico.disabled=true;malt_sexual.disabled=true;negligencia.disabled=true;quien_maltrato.disabled=true'></td>" +
													"<td>�Cu�l?</td><td>F�sico<INPUT type='checkbox' name='malt_fisico' id='si' disabled>Sexual<INPUT type='checkbox' name='malt_sexual' id='si' disabled>Negligencia<INPUT type='checkbox' name='negligencia' id='si' disabled></td><td>�Qui�n?</td><td><input type='text' size='50' name='quien_maltrato' id='quien_maltrato' disabled></td></tr></table>");
											out.print("<table width='100%'><tr><td>�Hay incongruencia para explicar un Trauma significante?</td><td>Si<INPUT type='radio' name='rad_incongruencia_trauma' id='si'>No<INPUT type='radio' name='rad_incongruencia_trauma' id='no'></td><td>�Existe incongruencia entre lesi�n - edad - desarrollo del ni�o?</td><td>Si<INPUT type='radio' name='rad_existe_incongruencia' id='si' onclick='diferentes_versiones.disabled=false;diferentes_versiones.focus()'>No<INPUT type='radio' name='rad_existe_incongruencia' id='no' onclick='diferentes_versiones.disabled=true'></td>" +
													"<td>�Hay diferentes versiones?</td><td><input type='text' name='diferentes_versiones' id='diferentes_versiones' disabled></td></tr>" +
													"<tr><td>�Es tard�a la consulta?</td><td>Si<INPUT type='radio' name='rad_tardia_consulta' id='si'>No<INPUT type='radio' name='rad_tardia_consulta' id='no'></td><td>�Con qu� frecuencia se ve obligado a pegarle a su hijo?</td><td><input type='text' name='frecuencia_pegar' id='frecuencia_pegar'></td>" +
													"<td>�Qu� tan desobediente es su hijo que se ve obligado a pegarle?</td><td><input type='text' name='desobediente_hijo' id='desobediente_hijo'></td></tr>" +
													"<tr><td>Comportamiento anormal de los padres:</td><td><SELECT name='comportamiento_anormal_padres' id='comportamiento_anormal_padres'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Desespero'>Desespero</OPTION><OPTION value='Impaciencia'>Impaciencia</OPTION><OPTION value='Intolerancia'>Intolerancia</OPTION><OPTION value='Agresividad en la consulta'>Agresividad en la consulta</OPTION><OPTION value='No tiene'>No tiene</OPTION></SELECT></td>" +
													"<td>�Est� descuidado el ni�o en su salud?</td><td>Si<INPUT type='radio' name='rad_descuidado_nino' id='si' onclick='por_descuidado_nino.disabled=false;por_descuidado_nino.focus();descuidado_nino_en.disabled=false'>No<INPUT type='radio' name='rad_descuidado_nino' id='no' onclick='por_descuidado_nino.disabled=true;descuidado_nino_en.disabled=true'></td><td>Por:</td><td><input type='text' name='por_descuidado_nino' id='por_descuidado_nino' disabled></td></tr>" +
													"<tr><td>Est� descuidado el ni�o en:</td><td><SELECT name='descuidado_nino_en' id='descuidado_nino_en' disabled><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Higiene'>Higiene</OPTION><OPTION value='Proteccion'>Protecci�n</OPTION><OPTION value='Alimentacion'>Alimentaci�n</OPTION><OPTION value='Nino de calle'>Ni�o de calle</OPTION></SELECT></td>" +
													"<td>Factor de riesgo:</td><td><SELECT name='factor_riesgo' id='factor_riesgo'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Discapacitado'>Discapacitado</OPTION><OPTION value='Hiperactivo'>Hiperactivo</OPTION><OPTION value='Ninguno'>Ninguno</OPTION></SELECT></td>" +
													"<td>�Actitud anormal del ni�o?</td><td>Si<INPUT type='radio' name='rad_actitud_anormal' id='si'>No<INPUT type='radio' name='rad_actitud_anormal' id='no'></td></tr></table>");
											out.print("<table width='80%'><tr><br><br><td><b>Lesiones en cr�neo:</b></td></tr>" +
													"<tr><td>Fracturas</td><td><input type='checkbox' name='fracturas' id='si'>&nbsp;&nbsp;</td><td>Hematomas</td><td><input type='checkbox' name='hematomas' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Hemorragias retinianas</td><td><input type='checkbox' name='hemorragias_retinianas' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td><b>Quemaduras:</b></td></tr>" +
													"<tr><td>�reas cubiertas por ropa</td><td><input type='checkbox' name='areas_ropa' id='si'>&nbsp;&nbsp;</td><td>Patr�n sim�trico,l�mite bien demarcado</td><td><input type='checkbox' name='patron_simetrico' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Denota el objeto con que fue quemado</td><td><input type='checkbox' name='denota_objeto' id='si'>&nbsp;&nbsp;</td><td>En espalda, dorso, manos o nalgas</td><td><input type='checkbox' name='en_espalda' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td><br><br>Equimosis</td><td><br><br><input type='checkbox' name='equimosis' id='si'>&nbsp;&nbsp;</td><td><br><br>Hematomas</td><td><br><br><input type='checkbox' name='hematomas_maltrato' id='si'>&nbsp;&nbsp;</td>" +
													"<td><br><br>Lasceraciones</td><td><br><br><input type='checkbox' name='lasceraciones' id='si'>&nbsp;&nbsp;</td><td><br><br>Mordiscos</td><td><br><br><input type='checkbox' name='mordiscos' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td>Cicatrices lejos de la prominencia oseo con patr�n del objeto agresor</td><td><input type='checkbox' name='cicatrices' id='si'>&nbsp;&nbsp;</td><td>Diferente evoluci�n en ni�os que no deambulan</td><td><input type='checkbox' name='diferente_evolucion' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Sugestivas de maltrato</td><td><input type='checkbox' name='sugestivas_maltrato' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td><b>Fracturas:</b></td></tr>" +
													"<tr><td>Costillas</td><td><input type='checkbox' name='costillas' id='si'>&nbsp;&nbsp;</td><td>Huesos largos</td><td><input type='checkbox' name='huesos_largos' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Espirales</td><td><input type='checkbox' name='espirales' id='si'>&nbsp;&nbsp;</td><td>Oblicua</td><td><input type='checkbox' name='oblicua' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td>Metafisiarias</td><td><input type='checkbox' name='metafisiarias' id='si'>&nbsp;&nbsp;</td><td>Esternon</td><td><input type='checkbox' name='esternon' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Esc�pula</td><td><input type='checkbox' name='escapula' id='si'>&nbsp;&nbsp;</td><td>Menor de 5 a�os</td><td><input type='checkbox' name='menor_cinco_anos' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td><br><br>Trauma visceral</td><td><br><br><input type='checkbox' name='trauma_visceral' id='si'>&nbsp;&nbsp;</td><td><br><br>Trauma grave</td><td><br><br><input type='checkbox' name='trauma_grave' id='si'>&nbsp;&nbsp;</td>" +
													"<td><br><br>Lesiones f�sica sugestival</td><td><br><br><input type='text' name='lesiones_sugestival' id='lesiones_sugestival'>&nbsp;&nbsp;</td><td><br><br>Sangrado vaginal o anal traum�tico</td><td><br><br><input type='checkbox' name='sangrado_vaginal' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td><b>Trauma genital:</b></td></tr>" +
													"<tr><td>Laceraci�n aguda o equimosis himen</td><td><input type='checkbox' name='lasceracion_aguda' id='si'>&nbsp;&nbsp;</td><td>Laceraci�n perianal desde esfinter</td><td><input type='checkbox' name='lasceracion_perianal' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Ausencia himen</td><td><input type='checkbox' name='ausencia_himen' id='si'>&nbsp;&nbsp;</td><td>Himen cicatrizado</td><td><input type='checkbox' name='himen_cicatrizado' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Cicatriz navicular</td><td><input type='checkbox' name='cicatriz_navicular' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td>Ano dilatado</td><td><input type='checkbox' name='ano_dilatado' id='si'>&nbsp;&nbsp;</td><td>Hallazgo semen</td><td><input type='checkbox' name='hallazgo_semen' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Flujo genital</td><td><input type='checkbox' name='flujo_genital' id='si'>&nbsp;&nbsp;</td><td>Cuerpo extra�o en vagina o ano</td><td><input type='checkbox' name='cuerpo_extrano' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Ves�culas o verrugas en genitales</td><td><input type='checkbox' name='vesiculas_maltrato' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td><br><br>Juego con contenido sexual</td><td><br><br><input type='checkbox' name='juego_sexual' id='si'>&nbsp;&nbsp;</td><td><br><br>Boca en genitales</td><td><br><br><input type='checkbox' name='boca_genitales' id='si'>&nbsp;&nbsp;</td>" +
													"<td><br><br>VIH</td><td><br><br><input type='checkbox' name='vih' id='si'>&nbsp;&nbsp;</td><td><br><br>Gonorrea</td><td><br><br><input type='checkbox' name='gonorrea' id='si'>&nbsp;&nbsp;</td>" +
													"<td><br><br>S�filis</td><td><br><br><input type='checkbox' name='sifilis' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td>Trichomona vaginalis >1a</td><td><input type='checkbox' name='trichomona_vaginalis' id='si'>&nbsp;&nbsp;</td><td>Chlamydia Trachomatis >3a</td><td><input type='checkbox' name='chlamydia_trachomatis' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Condilomatosis</td><td><input type='checkbox' name='condilomatosis' id='si'>&nbsp;&nbsp;</td><td>Temeroso</td><td><input type='checkbox' name='temeroso' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Retraido</td><td><input type='checkbox' name='retraido' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td>Rechazo adulto</td><td><input type='checkbox' name='rechazo_adulto' id='si'>&nbsp;&nbsp;</td><td>Deprimido</td><td><input type='checkbox' name='deprimido' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Evita contacto visual</td><td><input type='checkbox' name='evita_contacto_visual' id='si'>&nbsp;&nbsp;</td><td>Trastorno de sue�o</td><td><input type='checkbox' name='trastorno_sueno' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Trastorno alimentario</td><td><input type='checkbox' name='trastorno_alimentario' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td>Problemas Psicosom�ticos</td><td><input type='checkbox' name='problemas_psicomaticos' id='si'>&nbsp;&nbsp;</td><td>Conductas regresivas</td><td><input type='checkbox' name='conductas_regresivas' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Desarrollo estancado</td><td><input type='checkbox' name='desarrollo_estancado' id='si'>&nbsp;&nbsp;</td><td>Violencia intrafamiliar</td><td><input type='checkbox' name='violencia_intrafamiliar' id='si'>&nbsp;&nbsp;</td>" +
													"<td>Familia ca�tica</td><td><input type='checkbox' name='familia_caotica' id='si'>&nbsp;&nbsp;</td></tr>" +
													"<tr><td>Cuidadores adictos</td><td><input type='checkbox' name='cuidadores_adictos' id='si'>&nbsp;&nbsp;</td></tr></table>");
											out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_maltrato' id='obs_maltrato'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>MALTRATO F�SICO MUY GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='maltrato_fisico_muy_grave' id='si'></td><td><b>ABUSO GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='abuso_grave' id='si'></td>" +
													"<td><b>MALTRATO F�SICO</b>&nbsp;&nbsp;<input type='checkbox' name='maltrato_fisico' id='si'></td><td><b>SOSPECHA ABUSO SEXUAL</b>&nbsp;&nbsp;<input type='checkbox' name='sospecha_abuso_sexual' id='si'></td>" +
													"<td><b>MALTRATO EMOCIONAL</b>&nbsp;&nbsp;<input type='checkbox' name='maltrato_emocional' id='si'></td><td><b>NEGLIGENCIA O ABANDONO</b>&nbsp;&nbsp;<input type='checkbox' name='abandono' id='si'></td>" +
													"<td><b>NO HAY SOSPECHA MALTRATO</b>&nbsp;&nbsp;<input type='checkbox' name='no_sospecha_maltrato' id='si'></td></tr></table>");
											
						/*EVA DESARROLLO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>ENSEGUIDA, EVALUAR EL DESARROLLO</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>Tiene alg�n antecedente importante</td><td>Si<INPUT type='radio' name='rad_antecedente_importante' id='si' onclick='para_desarrollo.disabled=false;para_desarrollo.focus();algun_factor_riesgo.disabled=false'>No<INPUT type='radio' name='rad_antecedente_importante' id='no' onclick='para_desarrollo.disabled=true;algun_factor_riesgo.disabled=true'></td><td>Para el desarrollo:</td><td><input type='text' size='70' name='para_desarrollo' id='para_desarrollo' disabled></td><td>Tiene alg�n factor de riesgo:</td><td><input type='text' size='70' name='algun_factor_riesgo' id='algun_factor_riesgo' disabled></td></tr></table>");
											out.print("<table width='100%'><tr><td>Realiza</td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='realiza_cond_edad_uno' id='si'>2<input type='checkbox' name='realiza_cond_edad_dos' id='si'>3<input type='checkbox' name='realiza_cond_edad_tres' id='si'>4<input type='checkbox' name='realiza_cond_edad_cuatro' id='si'>&nbsp;&nbsp;&nbsp;condiciones para la edad</td>" +
													"<td>Ausencia de </td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='ausencia_cond_edad_uno' id='si'>2<input type='checkbox' name='ausencia_cond_edad_dos' id='si'>3<input type='checkbox' name='ausencia_cond_edad_tres' id='si'>4<input type='checkbox' name='ausencia_cond_edad_cuatro' id='si'>&nbsp;&nbsp;&nbsp;condiciones para la edad</td>" +
													"<td>Ausencia de</td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='ausencia_cond_grupo_anterior_uno' id='si'>2<input type='checkbox' name='ausencia_cond_grupo_anterior_dos' id='si'>3<input type='checkbox' name='ausencia_cond_grupo_anterior_tres' id='si'>4<input type='checkbox' name='ausencia_cond_grupo_anterior_cuatro' id='si'>&nbsp;&nbsp;&nbsp;condiciones del grupo anterior</td></tr>" +
													"<tr><td>Per�metro cef�lico:</td><td><input type='text' name='PC' id='PC' onkeypress='javascript:return validarNro(event)'> cm</td><td>Alteraciones fenot�picas</td><td><input type='text' name='alteraciones_fenotipicas' id='alteraciones_fenotipicas'></td></tr></table>");
											out.print("<table width='30%'><tr><br><br><td><-2 DE&nbsp;&nbsp;<input type='checkbox' name='menos_dos_de' id='si'></td><td>>+2 DE&nbsp;&nbsp;<input type='checkbox' name='mas_dos_de' id='si'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_desarrollo' id='obs_desarrollo'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><br><td><b>PROBABLE RETRASO DESARROLLO</b>&nbsp;&nbsp;<input type='checkbox' name='prob_retraso_desarrollo' id='si'></td><td><b>RIESGO PROBLEMA DESARROLLO</b>&nbsp;&nbsp;<input type='checkbox' name='riesgo_problema_desarrollo' id='si'></td>" +
													"<td><b>DESARROLLO NORMAL CON FACTOR DE RIESGO</b>&nbsp;&nbsp;<input type='checkbox' name='desarrollo_normal_riesgo' id='si'></td><td><b>DESARROLLO NORMAL</b>&nbsp;&nbsp;<input type='checkbox' nmae='desarrollo_normal' id='si'></td></tr></table>");
											
						/*VER VACUNACION*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR LOS ANTECEDENTES DE VACUNACION</b></td></tr></table>");
											out.print("<table width='100%'><tr><td>(Marque las dosis ya aplicadas)</td></tr></table>");
											out.print("<table width='80%'><tr><td><b>BCG:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='bcg_uno' id='si'></td><td><b>Hepatitis B:</b></td><td>&nbsp;&nbsp;&nbsp;RN<input type='checkbox' name='hepatitis_b_rn' id='si'>1<input type='checkbox' name='hepatitis_b_uno' id='si'>2<input type='checkbox' name='hepatitis_b_dos' id='si'>3<input type='checkbox' name='hepatitis_b_tres' id='si'></td>" +
													"<td><b>DPT:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='dpt_uno' id='si'>2<input type='checkbox' name='dpt_dos' id='si'>3<input type='checkbox' name='dpt_tres' id='si'>R1<input type='checkbox' name='dpt_runo' id='si'>R2<input type='checkbox' name='dpt_rdos' id='si'></td>" +
													"<td><b>VOP:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='vop_uno' id='si'>2<input type='checkbox' name='vop_dos' id='si'>3<input type='checkbox' name='vop_tres' id='si'>R1<input type='checkbox' name='vop_runo' id='si'>R2<input type='checkbox' name='vop_rdos' id='si'></td></tr>" +
													"<tr><td><b>SRP:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='srp_uno' id='si'>2<input type='checkbox' name='srp_dos' id='si'></td><td><b>Rotavirus:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='rotavirus_uno' id='si'>2<input type='checkbox' name='rotavirus_dos' id='si'></td>" +
													"<td><b>Streptococo Neumon�ae:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='streptococo_neumoniae_uno' id='si'>2<input type='checkbox' name='streptococo_neumoniae_dos' id='si'>3<input type='checkbox' name='streptococo_neumoniae_tres' id='si'></td>" +
													"<td><b>Haemophilus influenza tipo b:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='haemophilus_uno' id='si'>2<input type='checkbox' name='haemophilus_dos' id='si'>3<input type='checkbox' name='haemophilus_tres' id='si'>R1<input type='checkbox' name='haemophilus_runo'>R2<input type='checkbox' name='haemophilus_rdos' id='si'></td></tr></table>");
											out.print("<table width='50%'><tr><td><b>Influenza: �ltima dosis:</b></td><td><input type='text' name='influenza_dosis' id='influenza_dosis'></td><td><b>Fiebre Amarilla: Edad</b></td><td><input type='text' size='1' name='fiebre_amarilla' id='fiebre_amarilla'></td></tr></table>");
											out.print("<table width='100%'><tr><td><br><b>OTRAS VACUNAS:</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='otras_vacunas' id='otras_vacunas'></textarea></td></tr></table>");
											out.print("<table width='80%'><tr><td>Vacunas pendientes:</td><td><input type='text' name='vacunas_pendientes' id='vacunas_pendientes'></td><td>Pr�ximas vacunas:</td><td><input type='text' name='proximas_vacunas' id='proximas_vacunas'></td><td>A los</td><td><input type='text' size='1' name='tiempo_proxima_vacuna' id='tiempo_proxima_vacuna'> (meses-a�os)</td></tr></table>");

						/*EXAMEN FISICO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>EX�MEN F�SICO</b></td></tr></table>");
											out.print("<table width='100%'><tr><td><b>COMPLETAR EXAMEN FISICO</b></td></tr>" +
													"<tr><td><textarea rows='4' cols='135' name='obs_examen_fisico' id='obs_examen_fisico'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><td><b>OTRO PROBLEMA DETECTADO, DIAGN�STICO:</b></td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='otro_prob_detectado' id='otro_prob_detectado'></textarea></td></tr></table>");
											if (a > 6 ) {
												/*EVA ALIMENTACION*/
												out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>EVALUAR LA ALIMENTACI�N DE TODOS LOS NI�OS MENORES DE 2 A�OS y los clasificados como ANEMIA y/o CUALQUIERA DE LAS ALTERACIONES DEL CRECIMIENTO</b></td></tr></table>");
												out.print("<table width='100%'><tr><td>�Recibe leche materna?</td><td>Si<INPUT type='radio' name='rad_recibe_leche_mat' id='si' onclick='cuantas_veces_leche_mat.disabled=false;cuantas_veces_leche_mat.focus()'>No<INPUT type='radio' name='rad_recibe_leche_mat' id='no' onclick='cuantas_veces_leche_mat.disabled=true;'></td><td>�Cu�ntas veces en 24 horas?</td><td><input type='text' name='cuantas_veces_leche_mat' id='cuantas_veces_leche_mat' disabled></td>" +
														"<td>�Recibe pecho en la noche?</td><td>Si<INPUT type='radio' name='rad_pecho_noche' id='si' onclick='extrae_leche.disabled=false;extrae_leche.focus();guarda_administra_leche.disabled=false'>No<INPUT type='radio' name='rad_pecho_noche' id='no' onclick='extrae_leche.disabled=true;guarda_administra_leche.disabled=true'></td><td>�Se extrae la leche?</td><td><input type='text' name='extrae_leche' id='extrae_leche' disabled></td></tr>" +
														"<tr><td>�C�mo guarda y administra la leche?</td><td><input type='text' name='guarda_administra_leche' id='guarda_administra_leche' disabled></td>" +
														"<td>�El menor de 6 meses recibe otra leche o alimentos?</td><td>Si<INPUT type='radio' name='rad_menor_seis_leche' id='si' onclick='cuales_leche_menor_seis.disabled=false;cuales_leche_menor_seis.focus();cuantas_veces_leche_menor_seis.disabled=false;con_que_recibe_alimento.disabled=false;quien_da_comer_alimento.disabled=false;' disabled>No<INPUT type='radio' name='rad_menor_seis_leche' id='no'onclick='cuales_leche_menor_seis.disabled=true;cuantas_veces_leche_menor_seis.disabled=true;con_que_recibe_alimento.disabled=true;quien_da_comer_alimento.disabled=true;' disabled></td>" +
														"<td>�Cu�les?</td><td><input type='text' name='cuales_leche_menor_seis' id='cuales_leche_menor_seis' disabled></td><td>�Cu�ntas veces?</td><td><input type='text' name='cuantas_veces_leche_menor_seis' id='cuantas_veces_leche_menor_seis' disabled></td></tr>" +
														"<tr><td>�Con qu� recibe el alimento?</td><td><input type='text' name='con_que_recibe_alimento' id='con_que_recibe_alimento' disabled></td><td>�Qui�n le da de comer?</td><td><input type='text' name='quien_da_comer_alimento' id='quien_da_comer_alimento' disabled></tr></table>");
												out.print("<table width=100%><tr><td><br><b>EL NI�O MAYOR DE 6 MESES RECIBE:</b></td></tr>" +
														"<tr><td>�Cu�ntas comidas y meriendas recibi� el d�a de ayer?</td><td><input type='text' name='cuantas_comidas_ayer' id='cuantas_comidas_ayer'></td><td>�De qu� tama�o son las porciones que recibi� ayer?</td><td><input type='text' name='tamano_porciones_ayer' id='tamano_porciones_ayer'></td>" +
														"<td>�Cu�ntas comidas de consistencia espesa recibi� el d�a de ayer?</td><td><input type='text' name='cuantas_comidas_consistencia_ayer' id='cuantas_comidas_consistencia_ayer'></td><td>�Comi� alimentos de origen animal?</td><td><SELECT name='alimentos_origen_animal' id='alimentos_origen_animal'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Carne'>Carne</OPTION><OPTION value='Pescado'>Pescado</OPTION><OPTION value='Menudencias'>Menudencias</OPTION><OPTION value='Huevo'>Huevo</OPTION><OPTION value='No ha comido alimentos de origen animal'>No comi� alimentos</OPTION></SELECT></td></tr>" +
														"<tr><td>�Consumi� ayer productos lacteos?</td><td><input type='text' name='productos_lacteos' id='productos_lacteos'></td><td>�Comi� legumbres o semillas ayer?</td><td><input type='text' name='comio_legumbres' id='comio_legumbres'></td>" +
														"<td>�Comi� vegetales o frutas de color rojo o anaranjado y hojas de color verde oscuro ayer?</td><td><input type='text' name='comio_vegetales' id='comio_vegetales'></td><td>�Agreg� una peque�a cantidad de aceite a la comida del ni�o ayer?</td><td><input type='text' name='cantidad_aceite' id='cantidad_aceite'></td></tr>" +
														"<tr><td>�Qui�n le dio la comida ayer al ni�o?</td><td><input type='text' name='quien_da_comer_ayer_mayor_seis' id='quien_da_comer_ayer_mayor_seis'></td><td>�El ni�o come de su propio plato o come de la olla o plato familiar?</td><td><input type='text' name='propio_plato' id='propio_plato'></td>" +
														"<td>�El ni�o recibe alguna suplementaci�n de vitaminas y minerales?</td><td><input type='text' name='suplementacion_vitaminas' id='suplementacion_vitaminas'></td></tr>" +
														"<tr><br><td><b>ESTA ENFERMO:</b></td><td>Si<INPUT type='radio' name='rad_esta_enfermo' id='si' onclick='que_comio_enfermedad.disabled=false;que_comio_enfermedad.focus()'>No<INPUT type='radio' name='rad_esta_enfermo' id='no' onclick='que_comio_enfermedad.disabled=true'></td>" +
														"<td>�Qu� ha comido durante la enfermedad?</td><td><input type='text' name='que_comio_enfermedad' id='que_comio_enfermedad' disabled></td></tr>" +
														"<tr><td><b>ES OBESO:</b></td><td>Si<INPUT type='radio' name='rad_es_obeso' id='si' onclick='padres_obesos.disabled=false;padres_obesos.focus();nino_hace_ejercicio.disabled=false;programa_nutricional.disabled=false'>No<INPUT type='radio' name='rad_es_obeso' id='no' onclick='padres_obesos.disabled=true;nino_hace_ejercicio.disabled=true;programa_nutricional.disabled=true'></td>" +
														"<td>�Son los padres o hermanos obesos?</td><td><input type='text' name='padres_obesos' id='padres_obesos' disabled></td><td>�El ni�o hace ejercicio?</td><td><input type='text' name='nino_hace_ejercicio' id='nino_hace_ejercicio' disabled></td><td>�Est� asistiendo a un programa nutricional?</td><td><input type='text' name='programa_nutricional' id='programa_nutricional' disabled></td></tr></table>");
												out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
														"<tr><td><textarea rows='3' cols='135' name='obs_alimentacion' id='obs_alimentacion'></textarea></td></tr></table>");
												out.print("<table width='100%'><tr><br><td><b>PROBLEMA DETECTADO</b></td></tr>" +
														"<tr><td><textarea rows='3' cols='135' name='prob_detectado_alimentacion' id='prob_detectado_alimentacion'></textarea></td></tr></table>");
												out.print("<table width='100%'><tr><br><td><b>RECOMENDACIONES:</b></td></tr>" +
														"<tr><td><textarea rows='3' cols='135' name='recomendaciones_alimentaciones' id='recomendaciones_alimentaciones'></textarea></td></tr></table>");
											} else {
												/*EVA ALIMENTACION*/
												out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>EVALUAR LA ALIMENTACI�N DE TODOS LOS NI�OS MENORES DE 2 A�OS y los clasificados como ANEMIA y/o CUALQUIERA DE LAS ALTERACIONES DEL CRECIMIENTO</b></td></tr></table>");
												out.print("<table width='100%'><tr><td>�Recibe leche materna?</td><td>Si<INPUT type='radio' name='rad_recibe_leche_mat' id='si' onclick='cuantas_veces_leche_mat.disabled=false;cuantas_veces_leche_mat.focus()'>No<INPUT type='radio' name='rad_recibe_leche_mat' id='no' onclick='cuantas_veces_leche_mat.disabled=true;'></td><td>�Cu�ntas veces en 24 horas?</td><td><input type='text' name='cuantas_veces_leche_mat' id='cuantas_veces_leche_mat' disabled></td>" +
														"<td>�Recibe pecho en la noche?</td><td>Si<INPUT type='radio' name='rad_pecho_noche' id='si' onclick='extrae_leche.disabled=false;extrae_leche.focus();guarda_administra_leche.disabled=false'>No<INPUT type='radio' name='rad_pecho_noche' id='no' onclick='extrae_leche.disabled=true;guarda_administra_leche.disabled=true'></td><td>�Se extrae la leche?</td><td><input type='text' name='extrae_leche' id='extrae_leche' disabled></td></tr>" +
														"<tr><td>�C�mo guarda y administra la leche?</td><td><input type='text' name='guarda_administra_leche' id='guarda_administra_leche' disabled></td><td>�El menor de 6 meses recibe otra leche o alimentos?</td><td>Si<INPUT type='radio' name='rad_menor_seis_leche' id='si' onclick='cuales_leche_menor_seis.disabled=false;cuales_leche_menor_seis.focus();cuantas_veces_leche_menor_seis.disabled=false;con_que_recibe_alimento.disabled=false;quien_da_comer_alimento.disabled=false;'>No<INPUT type='radio' name='rad_menor_seis_leche' id='no'onclick='cuales_leche_menor_seis.disabled=true;cuantas_veces_leche_menor_seis.disabled=true;con_que_recibe_alimento.disabled=true;quien_da_comer_alimento.disabled=true;'></td>" +
														"<td>�Cu�les?</td><td><input type='text' name='cuales_leche_menor_seis' id='cuales_leche_menor_seis' disabled></td><td>�Cu�ntas veces?</td><td><input type='text' name='cuantas_veces_leche_menor_seis' id='cuantas_veces_leche_menor_seis' disabled></td></tr>" +
														"<tr><td>�Con qu� recibe el alimento?</td><td><input type='text' name='con_que_recibe_alimento' id='con_que_recibe_alimento' disabled></td><td>�Qui�n le da de comer?</td><td><input type='text' name='quien_da_comer_alimento' id='quien_da_comer_alimento' disabled></tr></table>");
												out.print("<table width='100%' disabled><tr><td><br><b>EL NI�O MAYOR DE 6 MESES RECIBE:</b></td></tr>" +
														"<tr><td>�Cu�ntas comidas y meriendas recibi� el d�a de ayer?</td><td><input type='text' name='cuantas_comidas_ayer' id='cuantas_comidas_ayer'></td><td>�De qu� tama�o son las porciones que recibi� ayer?</td><td><input type='text' name='tamano_porciones_ayer' id='tamano_porciones_ayer'></td>" +
														"<td>�Cu�ntas comidas de consistencia espesa recibi� el d�a de ayer?</td><td><input type='text' name='cuantas_comidas_consistencia_ayer' id='cuantas_comidas_consistencia_ayer'></td><td>�Comi� alimentos de origen animal?</td><td><SELECT name='alimentos_origen_animal' id='alimentos_origen_animal'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Carne'>Carne</OPTION><OPTION value='Pescado'>Pescado</OPTION><OPTION value='Menudencias'>Menudencias</OPTION><OPTION value='Huevo'>Huevo</OPTION><OPTION value='No ha comido alimentos de origen animal'>No comi� alimentos</OPTION></SELECT></td></tr>" +
														"<tr><td>�Consumi� ayer productos lacteos?</td><td><input type='text' name='productos_lacteos' id='productos_lacteos'></td><td>�Comi� legumbres o semillas ayer?</td><td><input type='text' name='comio_legumbres' id='comio_legumbres'></td>" +
														"<td>�Comi� vegetales o frutas de color rojo o anaranjado y hojas de color verde oscuro ayer?</td><td><input type='text' name='comio_vegetales' id='comio_vegetales'></td><td>�Agreg� una peque�a cantidad de aceite a la comida del ni�o ayer?</td><td><input type='text' name='cantidad_aceite' id='cantidad_aceite'></td></tr>" +
														"<tr><td>�Qui�n le dio la comida ayer al ni�o?</td><td><input type='text' name='quien_da_comer_ayer_mayor_seis' id='quien_da_comer_ayer_mayor_seis'></td><td>�El ni�o come de su propio plato o come de la olla o plato familiar?</td><td><input type='text' name='propio_plato' id='propio_plato'></td>" +
														"<td>�El ni�o recibe alguna suplementaci�n de vitaminas y minerales?</td><td><input type='text' name='suplementacion_vitaminas' id='suplementacion_vitaminas'></td></tr>" +
														"<tr><br><td><b>ESTA ENFERMO:</b></td><td>Si<INPUT type='radio' name='rad_esta_enfermo' id='si' onclick='que_comio_enfermedad.disabled=false;que_comio_enfermedad.focus()'>No<INPUT type='radio' name='rad_esta_enfermo' id='no' onclick='que_comio_enfermedad.disabled=true'></td>" +
														"<td>�Qu� ha comido durante la enfermedad?</td><td><input type='text' name='que_comio_enfermedad' id='que_comio_enfermedad' disabled></td></tr>" +
														"<tr><td><b>ES OBESO:</b></td><td>Si<INPUT type='radio' name='rad_es_obeso' id='si' onclick='padres_obesos.disabled=false;padres_obesos.focus();nino_hace_ejercicio.disabled=false;programa_nutricional.disabled=false'>No<INPUT type='radio' name='rad_es_obeso' id='no' onclick='padres_obesos.disabled=true;nino_hace_ejercicio.disabled=true;programa_nutricional.disabled=true'></td>" +
														"<td>�Son los padres o hermanos obesos?</td><td><input type='text' name='padres_obesos' id='padres_obesos' disabled></td><td>�El ni�o hace ejercicio?</td><td><input type='text' name='nino_hace_ejercicio' id='nino_hace_ejercicio' disabled></td><td>�Est� asistiendo a un programa nutricional?</td><td><input type='text' name='programa_nutricional' id='programa_nutricional' disabled></td></tr></table>");
												out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
														"<tr><td><textarea rows='3' cols='135' name='obs_alimentacion' id='obs_alimentacion'></textarea></td></tr></table>");
												out.print("<table width='100%'><tr><br><td><b>PROBLEMA DETECTADO</b></td></tr>" +
														"<tr><td><textarea rows='3' cols='135' name='prob_detectado_alimentacion' id='prob_detectado_alimentacion'></textarea></td></tr></table>");
												out.print("<table width='100%'><tr><br><td><b>RECOMENDACIONES:</b></td></tr>" +
														"<tr><td><textarea rows='3' cols='135' name='recomendaciones_alimentaciones' id='recomendaciones_alimentaciones'></textarea></td></tr></table>");
											}
						
											
						/*RECOMENDACIONES*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>RECOMENDACIONES</b></td></tr></table>");
											
											
											out.print("<table width='100%'><tr><td>Diagnostico</td> " +
													"<td><INPUT type='text' size='50' name='txtNomDiagnos' id='txtNomDiagnos' onkeyup='autocompletarCIE10()' ></td>" +
													"<td>Codigo</td> " +
													"<td><INPUT type='text' readonly='' size='50' name='txtCodDiagnostico' id='txtCodDiagnostico'><input type='button' value='Ingresar' onclick='IngresarDxDosCincoCex()'></td> " +
													"</tr><tr><td></td><td><div id='SugeDiagnostico'></div></td><td></td><td></td></tr><tr><td colspan='4' id='dxAiepi'></td><td><input type='hidden' name='codPrincipalCIE' id='codPrincipalCIE' value='"+codigoPrincipalCIE+"' /></td></tr></table>");
											
											
											out.print("<table width='100%'><tr><td align='center'>TRATAMIENTO (Describa plan de tratamiento, medicamentos, dosis, tiempo y cualquier recomendaci�n adicional necesaria)</td></tr>" +
													"<tr><td><textarea rows='5' cols='135' name='tratar_pac' id='tratar_pac'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><td><b>1.SIGNOS DE ALARMA:</b></td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='signos_alarma' id='signos_alarma'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><td><b>2.CU�NDO VOLVER A CONSULTA DE CONTROL:</b></td>" +
													"<td><INPUT type='text' name='consulta_control' id='consulta_control'></td>" +
													"<td><b>D�NDE:</b></td><td><INPUT type='text' name='donde_consulta_control' id='donde_consulta_control'></td>" +
													"<td><b>3.CU�NDO VOLVER A CONSULTA DE NI�O SANO O CRECIMIENTO Y DESARROLLO:</b></td><td><INPUT type='text' name='volver_nino_sano' id='volver_nino_sano'></td>" +
													"<td><b>4.REFERIDO A CONSULTA DE:</b></td><td><INPUT type='text' name='referido_consulta' id='referido_consulta'></td></tr></table>");
											out.print("<table width='100%'><tr><br><td><b>5.RECOMENDACIONES PARA EL DESARROLLO:</b></td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='recomendaciones_nino' id='recomendaciones_nino'></textarea></td></tr>" +
													"<tr><br><td><b>6.OTRAS RECOMENDACIONES DE BUEN TRATO:</b></td></tr>" +
													"<tr><td><textarea rows='3' cols='135' name='recomendaciones_buen_trato' id='recomendaciones_buen_trato'></textarea></td></tr></table>");
											out.print("<table width='100%'><tr><br><td><b>7.RECIBI� VITAMINA A EN LOS �LTIMOS 6 MESES:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibio_vitamina_a' id='si' onclick='prox_dosis_vitamina_a.disabled=false;prox_dosis_vitamina_a.focus()'><b>No</b><INPUT type='radio' name='rad_recibio_vitamina_a' id='no' onclick='prox_dosis_vitamina_a.disabled=true;'></td><td><b>PR�XIMA DOSIS</b></td><td><input type='text' name='prox_dosis_vitamina_a' id='prox_dosis_vitamina_a' disabled></td></tr>" +
													"<tr><td><b>8.RECIBI� ALBENDAZOL EN LOS �LTIMOS 6 MESES:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibio_albendazol' id='si' onclick='prox_dosis_albendazol.disabled=false;prox_dosis_albendazol.focus()'><b>No</b><INPUT type='radio' name='rad_recibio_albendazol' id='no' onclick='prox_dosis_albendazol.disabled=true'></td><td><b>PR�XIMA DOSIS</b></td><td><input type='text' name='prox_dosis_albendazol' id='prox_dosis_albendazol' disabled></td></tr>" +
													"<tr><td><b>9.RECIBI� HIERRO EN LOS �LTIMOS 6 MESES:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibio_hierro' id='si' onclick='cuando_hierro.disabled=false;cuando_hierro.focus();volver_recibir_hierro.disabled=false'><b>No</b><INPUT type='radio' name='rad_recibio_hierro' id='no' onclick='cuando_hierro.disabled=true;volver_recibir_hierro.disabled=true'></td><td><b>CU�NDO</b></td><td><input type='text' name='cuando_hierro' id='cuando_hierro' disabled></td><td><b>DEBE VOLVER A RECIBIR EN:</b></td><td><input type='text' name='volver_recibir_hierro' id='volver_recibir_hierro' disabled></td></tr>" +
													"<tr><td><b>10.REQUIERE RECIBIR ZINC:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibir_zinc' id='si' onclick='cuanto_tiempo_zinc.disabled=false;cuanto_tiempo_zinc.focus();inicia_zinc.disabled=false'><b>No</b><INPUT type='radio' name='rad_recibir_zinc' id='no' onclick='cuanto_tiempo_zinc.disabled=true;inicia_zinc.disabled=true'></td><td><b>�POR CU�NTO TIEMPO?</b></td><td><input type='text' name='cuanto_tiempo_zinc' id='cuanto_tiempo_zinc' disabled></td><td><b>INICIA:</b></td><td><input type='text' name='inicia_zinc' id='inicia_zinc' disabled></td></table>");
											out.print("<table width='100%'><tr><td colspan='2'><div align='center'><br>" +
													"<INPUT id='guardar' type='button' value='Guardar Informe' onclick='GuardarAiepiDosMesesCincoAnosCex()' disabled>" +
													//"<INPUT id='anular' type='button' value='Anular Informe' onclick='AnularInforme()'>" +
													"</div></td></tr></table>");
				}else{
					out.print("El paciente no aplica para realizar el formato AIEPI");	
				}
					
					
					
				}
				rs3.getStatement().getConnection().close();
			}
			rs2.getStatement().getConnection().close();
				}//Termina SI de rs1
				else{
					out.print("El paciente no tiene cita asignada");
				}
				rs1.getStatement().getConnection().close();
			
		//Try Catch Inicial
		} catch (Exception e) {
			System.out.println("Error en ControlAsignarCita valor=10.0 "+e);
			e.printStackTrace();
		}
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
			
		
			
				out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' id='btnVerFormatos' value='Asignar' onclick='AsignarFormatoCE()' /></label></div></td></tr></table>");
				out.print("<br>");
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td width='20%'>Nombre Del Formato </td><td width='10%'>Hora y Fecha </td><td width='19%'>Usuario</td><td width='14%'>Acciones de Formato</td><td width='37%'><div align='center'>Areas Del Formato</div></td></tr><tr class='style6'><td colspan='4'><div id='FormatosPaciente'></div></td><td><div id='areas' class='style6'></div></td></tr></table>");
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td><div align='center'> Preguntas Del Formato</div></td></tr><tr><td><div class='style6' id='formulario'></div></td></tr></table>");
			
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td align='center'>DIAGNOSTICOS</td></tr>");
				out.print("<tr><td id='dxhis'>");
				rs=mlh.CargarDiagnosticosActivos(CodigoPaciente);
				out.print("<table  width='100%' border='1' ><tr><td>DIAGNOSTICOS</td><td>-</td><td onclick='CargarDxNota()' >A�adir a nota</td><td>-</td></tr>");
				int cont=0;
				while(rs.next()){
					String dx=rs.getString(1)+"-"+rs.getString(2);
					cont=cont+1;
					out.print("<tr><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td colspan='3'><input type='checkbox' name='chkPorAtender' id='chkPorAtender' value='"+dx+"' /></td></tr>");
				}
				rs.getStatement().getConnection().close();
				out.print("<input type='hidden' id='txtContador' value="+cont+" /><input type='hidden' id='txtSeldx'  /></table>");
				out.print("</td></tr>");
			
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("20")){
			
			
			java.util.Date fechaActualDX = new java.util.Date();
			java.sql.Date fechaDX = new java.sql.Date(fechaActualDX.getTime());		
			java.sql.Time horaDX = new java.sql.Time(fechaActualDX.getTime());
			
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
						rs3=mvf.VerificarFormatoConHoraFechaIgual(fechaDX+"", horaDX+"");
						if(rs3.next()){
							
						}else{
							mac.RelacionFormatoPacienteCE(CodFormato,  CodigoPaciente, horaDX+"", fechaDX+"", CodUsuario,CodHorarioMedico);
							rs1=mvf.ObtenerAreaFormatoCE(CodFormato,fechaDX+"",horaDX+"");						
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
											mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fechaDX+"", horaDX+"", CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
										}
										if(TipoPregunta.equals("2")){
											/**si tipo pregunta es igual a un tipo seleccion*/
											resultados="Seleccione";
											mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fechaDX+"", horaDX+"", CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
										}
										if(TipoPregunta.equals("3")){
											/**si tipo pregunta es igual a un texto corto */
											mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fechaDX+"", horaDX+"", CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
										}
										if(TipoPregunta.equals("4")){
											/**si tipo pregunta es igual a un autocompletar de tipo diagnostico */
											mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fechaDX+"", horaDX+"", CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
										}
										if(TipoPregunta.equals("5")){
											/**si tipo pregunta es igual a un autocompletar de tipo diagnostico varios */
											mac.IngresarHistoriaCE(CodigoPaciente, CodigoPregunta, resultados, estado, fechaDX+"", horaDX+"", CodUsuario, CodigoArea, CodFormato,CodHorarioMedico);
										}
									}
									rs2.getStatement().getConnection().close();
								}
								rs1.getStatement().getConnection().close();
						}
						rs3.getStatement().getConnection().close();
				
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
							out.println("<textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onblur='ActualizarResultadosCE()' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+" ");
							
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
								out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onblur='ActualizarResultadosCE()' value="+rs.getString(5)+" > "+rs.getString(6)+"</td>");
							}
							if(rs.getString(2).equals("4")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								
								//String Resultado=rs.getString(5);
								//if(Resultado.equals("")){
									out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80'  onblur='autocompletarCIE10()'  /><input name='txtNomDiagnos1' type='hidden' id='txtNomDiagnos1' size='80' value='"+rs.getString(5)+"' /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicialCEf()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div><div id='DiaCE'>"+rs.getString(5)+"</div></td></tr>");
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
				rs3= mac.BuscarFormatosPendientesCE(CodAdm);
				/*rs4=mac.PreguntarAdmisionCeroDosR(CodAdm);
				rs5=mac.PreguntarAdmisionDosCincoR(CodAdm);
				rs6=mac.BuscarFormatosOdont(CodAdm);*/
				//if((rs.next())||(rs3.next())||(rs4.next())||(rs5.next())||(rs6.next())){
				if((rs.next())||(rs3.next())){
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
				rs3.getStatement().getConnection().close();
				/*rs4.getStatement().getConnection().close();
				rs5.getStatement().getConnection().close();
				rs6.getStatement().getConnection().close();*/
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
				rs.getStatement().getConnection().close();
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
			
			if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) //Compruebo que el a�o es bisiesto
			{
			diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
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
					for(i=0;i<diaInicial;i++)* /*Bucle para la reserva de espacios de los d�as en el calendario*/
					/*{
						out.print("<td align=center><font size+=4>.");
						columna++;
						out.print("</font>");
					}
					int a=0;
					for(i=1;i<=nDias;i++)* /*Bucle para la inclusi�n del n�mero de d�a en el calendario*/
				/*	{
						a=a+1;
						
						out.print("<td align=center>");

						if(i==dia) //Si el d�a a escribir es el d�a de hoy
					{
						//out.print("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el d�a de hoy
					}
					out.print(i);
					out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio+"-"+mes+"-"+i+" />");

					if(i==dia) //Si el d�a a escribir es el d�a de hoy
					{
						out.print("</b></font>"); //Cerramos la etiqueta de edici�n de las propiedades de las fuentes
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
					
					if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0))* /*Compruebo que el a�o es bisiesto*/
					/*{
					diasMes1[2] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
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
					for(e=0;e<DiaSemanaEmpiezaMes;e++)* /*Bucle para la reserva de espacios de los d�as en el calendario*/
					/*{
						out.print("<td align=center><font size+=4>.");
						columna1++;
						out.print("</font>");
					}
					int a1=0;
					for(e=1;e<=nDias1;e++)* /*Bucle para la inclusi�n del n�mero de d�a en el calendario*/
					/*{
						a1=a1+1;						
						out.print("<td align=center>");
						if(e==dia1) //Si el d�a a escribir es el d�a de hoy
					{
						//out.print("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el d�a de hoy
					}
					out.print(e);
					out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");

					if(e==dia1) //Si el d�a a escribir es el d�a de hoy
					{
						out.print("</b></font>"); //Cerramos la etiqueta de edici�n de las propiedades de las fuentes
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
						rs4.getStatement().getConnection().close();
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
						mac.EliminarRegistroDeAsigancion2(codigoHorarioMedico);
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
					"<td>A�O<input type='text' id='txtAnio' ></td></tr>");
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
			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el a�o es bisiesto*/
			{
			diasMes1[2] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
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
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los d�as en el calendario*/
			{
				out.print("<td align=center><font size+=4>.");
				columna1++;
				out.print("</font>");
			}
			int a1=0;
			for(e=1;e<=nDias1;e++) /*Bucle para la inclusi�n del n�mero de d�a en el calendario*/
			{
				a1=a1+1;						
				out.print("<td align=center>");
				if(e==dia1) //Si el d�a a escribir es el d�a de hoy
			{
				//out.print("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el d�a de hoy
			}
			out.print(e);
			out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");

			if(e==dia1) //Si el d�a a escribir es el d�a de hoy
			{
				out.print("</b></font>"); //Cerramos la etiqueta de edici�n de las propiedades de las fuentes
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
				
				
				
				out.print("<table border='0' width='100%' ><tr><td><div align='center' class='style11' id='cabecera2'>ORDEN DE SERVICIO<select id='cmbTipoOrden'><option value='Seleccione'>Seleccione</option><option value='Hospitalizacion'>Hospitalizacion</option><option value='Urgencia'>Urgencia</option><option value='Consulta Externa'>Consulta Externa</option></select></div></td></tr></table>");
				
				String Tip_Documento =req.getParameter("TipoDocumento");
				String Numero_Documento =req.getParameter("NumeroDocumento");
				rs=mca.BuscarPacienteOrden(Tip_Documento, Numero_Documento);
				if(rs.next()){
					/*************************/
					rs1=mca.BuscarAdmisionActiva(rs.getString("CodPac"));
					if(rs1.next()){
						out.print("<table  border='0' width='100%' ><tr bgcolor='red'><td><strong><font color='white'>EL PACIENTE TIENE UNA ADMISION ACTIVA, SE ENCUENTRA UBICADO EN "+rs1.getString("Ubicacion")+". NUMERO DE ADMISION: "+rs1.getString("adm_numero_ingreso")+" </font></strong></td></tr></table>");
					}
					rs1.getStatement().getConnection().close();
					/*************************/
					out.print("<table border='0' width='100%' ><tr><td width='13%'  class='style12' >PACIENTE <input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString("CodPac")+"  style='width:100%'></td><td width='21%'>"+rs.getString("Paciente")+"</td><td width='3%'  class='style12'>EDAD</td><td width='8%'>"+rs.getString("edad")+" A�OS</td><td width='7%'  class='style12'>DOCUMENTO</td><td width='15%' >"+rs.getString("Documento")+"</td><td width='6%'  class='style12'>ENTIDAD</td><td colspan='2'>"+rs.getString("entidad")+"</td></tr>");
					
					out.print("<tr class='style12'><td > <input name='txtCodMaEntiPac' type='hidden' id='txtCodMaEntiPac' value="+rs.getString("cod_manual_tarifario")+" /> ENTIDAD PRESTADORA <input name='txtCodMaTr' type='hidden' id='txtCodMaTr' /><input name='txtCodEntidadPrestadora' type='hidden' id='txtCodEntidadPrestadora' /><input name='txtNitEntidadPrestadora' type='hidden' id='txtNitEntidadPrestadora' /><input name='txtTelEntiPrestadora' type='hidden' id='txtTelEntiPrestadora' /><input name='txtDireEntiPrestadora' type='hidden' id='txtDireEntiPrestadora' /></td><td colspan='3'><input name='txtEntidad' type='text' id='txtEntidad' onkeyup='CargarEntidades()'  style='width:100%'></td><td>N� AUTORIZACION </td><td><input name='txtNumOrden' type='text' id='txtNumOrden' /></td><td>OBSERVACION</td><td colspan='2'><input name='txtObservacion' type='text' id='txtObservacion'  style='width:100%'></td></tr>");
					
					out.print("<tr><td class='style12' >&nbsp;</td><td colspan='3' class='style12'><div id='autoentidad'>nombre entidad</div></td><td class='style12' align='center'>&nbsp;</td><td class='style12' align='center'>&nbsp;</td><td class='style12' align='center'>&nbsp;</td><td colspan='2' align='center' class='style12'>&nbsp;</td><td width='0%' align='center' class='style12'>&nbsp;</td></tr>");
					
					out.print("<tr><td class='style12' >SERVICIO</td><td colspan='3' class='style12'><input name='txtServicio' type='text' id='txtServicio' onkeyup='CargarProgramas()'  style='width:100%'><input name='txtCodCups1' type='hidden' id='txtCodCups1' /><input name='txtCodReferencia1' type='hidden' id='txtCodReferencia1' /></td><td class='style12'>CANTIDAD</td><td class='style12'><input name='txtCantidad' type='text' id='txtCantidad' value='1'  style='width:100%'></td><td class='style12'>VALOR</td><td width='16%' class='style12'><input name='txtValorPrograma' type='text' id='txtValorPrograma'  style='width:100%'><input name='txtCodigoPrograma' type='hidden' id='txtCodigoPrograma'  style='width:100%'></td><td width='11%' class='style12'><input type='button' name='Button' onclick='AsignarOrden()' value='        Asignar        '></td><td class='style12'>&nbsp;</td></tr>");
					
					out.print("<tr><td class='style12' >&nbsp;</td><td colspan='3'  class='style12'><div id='autoservi'>nombre servicio </div></td><td class='style12' >&nbsp;</td><td class='style12' >&nbsp;</td><td class='style12' >&nbsp;</td><td colspan='2'  class='style12'>&nbsp;</td><td class='style12' >&nbsp;</td></tr>");
					
					out.print("<tr><td colspan='9'  ><div id='DetalleOrden'>");
					
					out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value='0' ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR UNIT</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
					
					out.print("<tr  class='style12'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;<input name='txtConsecutivoOrden' type='hidden' id='txtConsecutivoOrden' value='0' ></td></tr>");
					
					out.print("</table></div></td></tr>");

					//out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button' id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
					out.print("</table>");
					
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
						mca.FinalizarOrdenServicioP(rs.getString("codigo"),"3");
					}
					rs.getStatement().getConnection().close();
					rs=mca.FechaVencimiento();
					String FechaVencimiento="";
					if(rs.next()){
						FechaVencimiento=rs.getString("ProximaFecha");
					}
					rs.getStatement().getConnection().close();
				
						
					mca.GuardarOrdenServicio(CodPaciente, CodEnt, "", NumOrden, Observacion, Fecha_Insercion1+"", Hora_Insercion1+"", Codusuario, "0", FechaVencimiento+"",
							NomEntidad, NitEntidadPrestadora, DireEntiPrestadora, TelEntiPrestadora,servicio);
					//3.) se consulta el codigo de la orden
					rs=mca.BuscarCodigoOrden(Fecha_Insercion1+"", Hora_Insercion1+"",CodPaciente,Codusuario);
					String CodOrden_fk="";
					if(rs.next()){
						CodOrden_fk=rs.getString("codigo");
						//4.) se inserta el detalle de la orden de servicio en la tabla fact_detalle_orden_servicio
						
						mca.GuardarDetalleOrdenServicio(CodOrden_fk, Servicio, CodCups, CodReferencia, CodigoPrograma, Cantidad, ValorPrograma);
						//
						//aqui va el numero de la orden de servicio
					
							
						//
						//out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td align='center'>ORDEN DE SERVICIO "+consn+"<input name='txtConsecutivoOrden' type='hidden' id='txtConsecutivoOrden' value="+consn+" ></td></tr></table>");
						out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value="+CodOrden_fk+" ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR UNIT</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
						int cont=0;
						rs1=mca.ObtenerDetalleOrdenServicio(CodOrden_fk);
						while(rs1.next()){
							int ValorTotal=(Integer.parseInt(rs1.getString("cantidad")))*(Integer.parseInt(rs1.getString("valor")));
							out.print("<tr class='style12'><td>"+rs1.getString("CodCups")+"</td>" +
									"<td>"+rs1.getString("Nombre_Programa")+"</td>" +
											"<td>"+rs1.getString("cantidad")+"</td>" +
													"<td>"+rs1.getString("valor")+"</td>" +
													"<td>"+ValorTotal+"</td>" +
															"<td><a href='#' onclick='AnularMovimiento("+rs1.getString("codigo")+")' >Omitir</a></td></tr>");
							cont=cont+1;
						}
						rs1.getStatement().getConnection().close();					
						
						if(cont==0){
							out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button' disabled='true'  id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
						}else{
							out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button'  id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
						}
						

					}else{ 
						out.print("No se pudo generar la orden de servicio, verifique los datos.");
					}
					rs.getStatement().getConnection().close();
				}else{
					//**
					//out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td align='center' >ORDEN DE SERVICIO "+ConsecutivoOrden+" <input name='txtConsecutivoOrden' type='hidden' id='txtConsecutivoOrden' value="+ConsecutivoOrden+" ></td></tr></table>");
					//4.) se inserta el detalle de la orden de servicio en la tabla fact_detalle_orden_servicio
					mca.GuardarDetalleOrdenServicio(CodOrden, Servicio, CodCups, CodReferencia, CodigoPrograma, Cantidad, ValorPrograma);
					out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value="+CodOrden+" ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
					int cont=0;
					rs1=mca.ObtenerDetalleOrdenServicio(CodOrden);
					while(rs1.next()){
						int ValorTotal=(Integer.parseInt(rs1.getString("cantidad")))*(Integer.parseInt(rs1.getString("valor")));
						out.print("<tr class='style12'><td>"+rs1.getString("CodCups")+"</td>" +
								"<td>"+rs1.getString("Nombre_Programa")+"</td>" +
										"<td>"+rs1.getString("cantidad")+"</td>" +
												"<td>"+rs1.getString("valor")+"</td>" +
												"<td>"+ValorTotal+"</td>" +
														"<td><a href='#' onclick='AnularMovimiento("+rs1.getString("codigo")+")' >Omitir</a></td></tr>");
						cont=cont+1;
					}
					rs1.getStatement().getConnection().close();					
					if(cont==0){
						out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button' disabled='true'  id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
					}else{
						out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button'  id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
					}
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
			String conso="";
			String cons="";
			String consn="";
			try {
			rs2 = mca.BuscarConsecutivoOrden();
				if(rs2.next()){
					//out.print(rs.getString(1)+"|"+rs.getString(2));
					if(rs2.getString("consecutivo").length()==1){cons=("00000000"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==2){cons=("0000000"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==3){cons=("000000"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==4){cons=("00000"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==5){cons=("0000"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==6){cons=("000"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==7){cons=("00"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==8){cons=("0"+rs2.getString("consecutivo"));}
					if(rs2.getString("consecutivo").length()==9){cons=rs2.getString("consecutivo");}
					consn=rs2.getString("sigla")+"-"+cons;
					conso=rs2.getString("consecutivo");
				}
				rs2.getStatement().getConnection().close();
				
				
				//se actualiza el consecutivo del documento
				int ctan=Integer.parseInt(conso)+1;
				conso=String.valueOf(ctan);
				mca.ActualizarConsecutivoOrdenServicio(conso);
				///////////////////////////////
			mca.FinalizarOrdenServicio(CodOrden,"1",consn);
			//out.print("Orden Generada Satisfactoriamente.");
			MetodoAdmision ma= new MetodoAdmision();
			rs1=mca.ObtenerDetalleOrdenServicio(CodOrden);
			
				java.util.Date FechaAc1 = new java.util.Date();
				java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
				java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
				String CodAdmision="";
				rs2=ma.obtenerCodigoAdmisionActiva(CodPacF);
				if(rs2.next()){
					// si tiene admision activa.
					CodAdmision=rs2.getString("adm_numero_ingreso");
				}else{
					//generar admision --OK
					
					ma.insertarAdmision(NomUsu,Fecha_Insercion1+"",Hora_Insercion1,CodPacF,NumOrden);
					ma.InsertarContactoPaciente("1", CodPacF);
					//encabezado de la orden -- OK				
					
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
						ma.InserEmcabeCXE(cod_eps, razon_social, nit, direccion, telefono, ciudad, nombre_paciente, documentoP, direccion_p, telefono_p, tipo_afiliacion, estrato, fecha_ingreso, CodAdmision, num_autorizacion,poliza,ViaIng,"0","0");
					}					
					rsc.getStatement().getConnection().close();
				}
				/*********************************************************************************************/	
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
								rs.getString("cod_programa"), rs.getString("cod_referencia"), 
								rs.getString("Programa"), rs.getString("ClaseServicio"), 
								Fecha_Insercion1+"", Canti, rs.getString("valor"), Codusuario,
								CodEncFactura, "0", rs.getString("subcentro_costo"));
					}else{
						//out.print("consultar los datos del servicio incruento o servicio."+CodCups);
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
				String ConsecutivoOrden=req.getParameter("ConsecutivoOrden");
				mca.OmitirDetalleOrdenServicio(codigodetalle);
				//out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td align='center' >ORDEN DE SERVICIO "+ConsecutivoOrden+" <input name='txtConsecutivoOrden' type='hidden' id='txtConsecutivoOrden' value="+ConsecutivoOrden+" ></td></tr></table>");
				out.print(" <table width='100%' border='1' cellspacing='0' cellpadding='0'><tr bgcolor='#DADADA' class='style12'><td width='7%'>CODIGO <input name='txtCodOrden' type='hidden' id='txtCodOrden' value="+CodOrden+" ></td><td width='61%'>SERVICIO</td><td width='12%'>CANTIDAD</td><td width='12%'>VALOR</td><td width='12%'>VALOR TOTAL</td><td width='8%'>ACCION</td></tr>");
				int cont=0;
				rs1=mca.ObtenerDetalleOrdenServicio(CodOrden);
				while(rs1.next()){
					int ValorTotal=(Integer.parseInt(rs1.getString("cantidad")))*(Integer.parseInt(rs1.getString("valor")));
					out.print("<tr class='style12'><td>"+rs1.getString("CodCups")+"</td>" +
							"<td>"+rs1.getString("Nombre_Programa")+"</td>" +
									"<td>"+rs1.getString("cantidad")+"</td>" +
											"<td>"+rs1.getString("valor")+"</td>" +
											"<td>"+ValorTotal+"</td>" +
													"<td><a href='#' onclick='AnularMovimiento("+rs1.getString("codigo")+")' >Omitir</a></td></tr>");
					cont=cont+1;
				}
				rs1.getStatement().getConnection().close();					
			
			if(cont==0){
				out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button' disabled='true'  id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
			}else{
				out.print("<tr><td colspan='9' class='style12' align='center' ><input name='BTNFINALIZAR' type='button'  id='BTNFINALIZAR' onclick='FinalizarOS()'  value='          FINALIZAR          '></td><td class='style12' >&nbsp;</td></tr></table>");
			}
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
			String CodEnt=req.getParameter("CodEnt");
			try {				
				int conpr=0;				
				String CodPrograma="";
				String NomPrograma="";
				String CodCups="";
				String CodReferencia="";
				String valor="";				
				rs=mca.BuscarPrograma(Programa,CodManual,CodEnt);
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
				
				if(TipoBus.equals("CFEH")){
					//consulta por fechas orden de servicio
					out.print("<table border='0' cellspacing='0' cellpadding='0'><tr> " +
							"<td>Fecha Inicial <input name='txtFechaInicial' type='text' id='txtFechaInicial' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /></td> " +
							"<td>Fecha Final <input name='txtFechaFinal' type='text' id='txtFechaFinal' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'  /> " +
							"<input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarOrdenServ()' /></td></tr></table>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("BDDI")){
			String txtFechaInicial=req.getParameter("txtFechaInicial");
			String txtFechaFinal=req.getParameter("txtFechaFinal");
			String CodEnt=req.getParameter("CodEnt");
			String CodUsua=req.getParameter("CodUsu");
			String TipoBusqueda=req.getParameter("TipoBusqueda");
			String NombreEntidad=req.getParameter("NombreEntidad");
			String NombreUsuario=req.getParameter("NombreUsuario");
			String FechaIni,DiaI,MesI,AnoI=null;
			DiaI=txtFechaInicial.substring(0,2);
			MesI=txtFechaInicial.substring(3,5);
			AnoI=txtFechaInicial.substring(6,10);
			FechaIni=AnoI+"-"+MesI+"-"+DiaI;
			
			String FechaFin,DiaF,MesF,AnoF=null;
			DiaF=txtFechaFinal.substring(0,2);
			MesF=txtFechaFinal.substring(3,5);
			AnoF=txtFechaFinal.substring(6,10);
			FechaFin=AnoF+"-"+MesF+"-"+DiaF;
			//aqui van las validaciones de las consultas
			String ParametroFacturacion="";
			String ParametroReciboCajas="";
			long SumaValorFacturado=0;long SumaCopago=0;long SumaAnticipos=0;long SumaDescuentos=0;long SumaValorNeto=0;long SumaRecibosCaja=0;
			try {
				out.print("<table border='1' width='100%'  cellspacing='0' cellpadding='0'>");
				if(TipoBusqueda.equals("FE")){
					ParametroFacturacion="and fef.fecha between '"+FechaIni+"' and '"+FechaFin+"' order by fn.consecutivo";
					ParametroReciboCajas="and adrc.fecha between '"+FechaIni+"' and '"+FechaFin+"' order by adrc.codigo";
					rs=mca.ConsultaFacturacionDI(ParametroFacturacion);
					rs1=mca.ConsultaRecibosCajaDI(ParametroReciboCajas);
					out.print("<tr><td colspan='11' align='center'  ><b>BUSQUEDA REALIZADA CON LOS SIGUIENTES PARAMETROS FECHA INICIAL "+FechaIni+" FECHA FINAL "+FechaFin+"</b></td></tr>");	
					out.print("<tr bgcolor='red'><td colspan='11' align='center'><b><font color='white'>FACTURAS GENERADAS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA' ><td>Numero Factura</td><td>Fecha Factura</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Facturado</td><td>Copago</td><td>Recibido</td><td>Descuentos</td><td>Valor Neto</td></tr>");
					while(rs.next()){
						SumaValorFacturado=SumaValorFacturado+rs.getLong("ValorFacturado");
						SumaCopago=SumaCopago+rs.getLong("copago");
						SumaAnticipos=SumaAnticipos+rs.getLong("anticipos");
						SumaDescuentos=SumaDescuentos+rs.getLong("Descuentos");
						SumaValorNeto=SumaValorNeto+rs.getLong("valorNeto");
						out.print("<tr><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("fecha")+"</td><td>"+rs.getString("razon_social")+"</td><td>"+rs.getString("nombre_paciente")+"</td><td>"+rs.getString("documento")+"</td><td>"+rs.getString("usuario")+"</td><td>"+formatMoneda(rs.getString("ValorFacturado"))+"</td><td>"+formatMoneda(rs.getString("copago"))+"</td><td>"+formatMoneda(rs.getString("anticipos"))+"</td><td>"+formatMoneda(rs.getString("Descuentos"))+"</td><td>"+formatMoneda(rs.getString("valorNeto"))+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTALES</td><td>"+formatMoneda(SumaValorFacturado+"")+"</td><td>"+formatMoneda(SumaCopago+"")+"</td><td>"+formatMoneda(SumaAnticipos+"")+"</td><td>"+formatMoneda(SumaDescuentos+"")+"</td><td>"+formatMoneda(SumaValorNeto+"")+"</td></tr>");
					rs.getStatement().getConnection().close();
					out.print("<tr bgcolor='blue'><td colspan='11' align='center'><b><font color='white'>RECIBOS DE CAJA GENERADOS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA' ><td>Numero Recibo Caja</td><td>Fecha</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Recibo</td><td colspan='2'>Concepto</td><td colspan='2'>Observacion</td></tr>");
					while(rs1.next()){
						SumaRecibosCaja=SumaRecibosCaja+rs1.getLong("abono");
						out.print("<tr><td>"+rs1.getString("codigo")+"</td><td>"+rs1.getString("fecha")+"</td><td>"+rs1.getString("nombre_entidad")+"</td><td>"+rs1.getString("Nombrepaciente")+"</td><td>"+rs1.getString("Documento")+"</td><td>"+rs1.getString("usuario")+"</td><td>"+formatMoneda(rs1.getString("abono"))+"</td><td colspan='2'>"+rs1.getString("concepto")+"</td><td colspan='2'>"+rs1.getString("Observacion")+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTAL</td><td>"+formatMoneda(SumaRecibosCaja+"")+"</td><td colspan='2'></td><td colspan='2'></td></tr></table>");
					rs1.getStatement().getConnection().close();
				
				}
			
				if(TipoBusqueda.equals("FEEN")){
					ParametroFacturacion="and fef.fecha between '"+FechaIni+"' and '"+FechaFin+"' and fef.cod_eps='"+CodEnt+"' order by fn.consecutivo";
					ParametroReciboCajas="and adrc.fecha between '"+FechaIni+"' and '"+FechaFin+"' and aent.ent_nit='"+CodEnt+"' order by adrc.codigo";
					rs=mca.ConsultaFacturacionDI(ParametroFacturacion);
					rs1=mca.ConsultaRecibosCajaDI(ParametroReciboCajas);
					out.print("<tr><td colspan='11' align='center' ><b>BUSQUEDA REALIZADA CON LOS SIGUIENTES PARAMETROS FECHA INICIAL "+FechaIni+" FECHA FINAL "+FechaFin+" ENTIDAD "+NombreEntidad+"</b></td></tr>");
					out.print("<tr bgcolor='red'><td colspan='11' align='center'><b><font color='white'>FACTURAS GENERADAS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA'><td>Numero Factura</td><td>Fecha Factura</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Facturado</td><td>Copago</td><td>Recibido</td><td>Descuentos</td><td>Valor Neto</td></tr>");
					while(rs.next()){
						SumaValorFacturado=SumaValorFacturado+rs.getLong("ValorFacturado");
						SumaCopago=SumaCopago+rs.getLong("copago");
						SumaAnticipos=SumaAnticipos+rs.getLong("anticipos");
						SumaDescuentos=SumaDescuentos+rs.getLong("Descuentos");
						SumaValorNeto=SumaValorNeto+rs.getLong("valorNeto");
						out.print("<tr><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("fecha")+"</td><td>"+rs.getString("razon_social")+"</td><td>"+rs.getString("nombre_paciente")+"</td><td>"+rs.getString("documento")+"</td><td>"+rs.getString("usuario")+"</td><td>"+formatMoneda(rs.getString("ValorFacturado"))+"</td><td>"+formatMoneda(rs.getString("copago"))+"</td><td>"+formatMoneda(rs.getString("anticipos"))+"</td><td>"+formatMoneda(rs.getString("Descuentos"))+"</td><td>"+formatMoneda(rs.getString("valorNeto"))+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTALES</td><td>"+formatMoneda(SumaValorFacturado+"")+"</td><td>"+formatMoneda(SumaCopago+"")+"</td><td>"+formatMoneda(SumaAnticipos+"")+"</td><td>"+formatMoneda(SumaDescuentos+"")+"</td><td>"+formatMoneda(SumaValorNeto+"")+"</td></tr>");
					rs.getStatement().getConnection().close();
					out.print("<tr bgcolor='blue'><td colspan='11' align='center'><b><font color='white'>RECIBOS DE CAJA GENERADOS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA'><td>Numero Recibo Caja</td><td>Fecha</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Recibo</td><td colspan='2'>Concepto</td><td colspan='2'>Observacion</td></tr>");
					while(rs1.next()){
						SumaRecibosCaja=SumaRecibosCaja+rs1.getLong("abono");
						out.print("<tr><td>"+rs1.getString("codigo")+"</td><td>"+rs1.getString("fecha")+"</td><td>"+rs1.getString("nombre_entidad")+"</td><td>"+rs1.getString("Nombrepaciente")+"</td><td>"+rs1.getString("Documento")+"</td><td>"+rs1.getString("usuario")+"</td><td>"+formatMoneda(rs1.getString("abono"))+"</td><td colspan='2'>"+rs1.getString("concepto")+"</td><td colspan='2'>"+rs1.getString("Observacion")+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTAL</td><td>"+formatMoneda(SumaRecibosCaja+"")+"</td><td colspan='2'></td><td colspan='2'></td></tr></table>");
					rs1.getStatement().getConnection().close();
				}
				
				if(TipoBusqueda.equals("FEUS")){
					ParametroFacturacion="and fef.fecha between '"+FechaIni+"' and '"+FechaFin+"' and fef.cod_usuario_fk='"+CodUsua+"' order by fn.consecutivo";
					ParametroReciboCajas="and adrc.fecha between '"+FechaIni+"' and '"+FechaFin+"' and adrc.CodUsu_fk='"+CodUsua+"' order by adrc.codigo";
					rs=mca.ConsultaFacturacionDI(ParametroFacturacion);
					rs1=mca.ConsultaRecibosCajaDI(ParametroReciboCajas);
					out.print("<tr><td colspan='11' align='center' ><b>BUSQUEDA REALIZADA CON LOS SIGUIENTES PARAMETROS FECHA INICIAL "+FechaIni+" FECHA FINAL "+FechaFin+" USUARIO "+NombreUsuario+"</b></td></tr>");
					out.print("<tr bgcolor='red'><td colspan='11' align='center'><b><font color='white'>FACTURAS GENERADAS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA'><td>Numero Factura</td><td>Fecha Factura</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Facturado</td><td>Copago</td><td>Recibido</td><td>Descuentos</td><td>Valor Neto</td></tr>");
					while(rs.next()){
						SumaValorFacturado=SumaValorFacturado+rs.getLong("ValorFacturado");
						SumaCopago=SumaCopago+rs.getLong("copago");
						SumaAnticipos=SumaAnticipos+rs.getLong("anticipos");
						SumaDescuentos=SumaDescuentos+rs.getLong("Descuentos");
						SumaValorNeto=SumaValorNeto+rs.getLong("valorNeto");
						out.print("<tr><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("fecha")+"</td><td>"+rs.getString("razon_social")+"</td><td>"+rs.getString("nombre_paciente")+"</td><td>"+rs.getString("documento")+"</td><td>"+rs.getString("usuario")+"</td><td>"+formatMoneda(rs.getString("ValorFacturado"))+"</td><td>"+formatMoneda(rs.getString("copago"))+"</td><td>"+formatMoneda(rs.getString("anticipos"))+"</td><td>"+formatMoneda(rs.getString("Descuentos"))+"</td><td>"+formatMoneda(rs.getString("valorNeto"))+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTALES</td><td>"+formatMoneda(SumaValorFacturado+"")+"</td><td>"+formatMoneda(SumaCopago+"")+"</td><td>"+formatMoneda(SumaAnticipos+"")+"</td><td>"+formatMoneda(SumaDescuentos+"")+"</td><td>"+formatMoneda(SumaValorNeto+"")+"</td></tr>");
					rs.getStatement().getConnection().close();
					out.print("<tr bgcolor='blue'><td colspan='11' align='center'><b><font color='white'>RECIBOS DE CAJA GENERADOS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA'><td>Numero Recibo Caja</td><td>Fecha</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Recibo</td><td colspan='2'>Concepto</td><td colspan='2'>Observacion</td></tr>");
					while(rs1.next()){
						SumaRecibosCaja=SumaRecibosCaja+rs1.getLong("abono");
						out.print("<tr><td>"+rs1.getString("codigo")+"</td><td>"+rs1.getString("fecha")+"</td><td>"+rs1.getString("nombre_entidad")+"</td><td>"+rs1.getString("Nombrepaciente")+"</td><td>"+rs1.getString("Documento")+"</td><td>"+rs1.getString("usuario")+"</td><td>"+formatMoneda(rs1.getString("abono"))+"</td><td colspan='2'>"+rs1.getString("concepto")+"</td><td colspan='2'>"+rs1.getString("Observacion")+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTAL</td><td>"+formatMoneda(SumaRecibosCaja+"")+"</td><td colspan='2'></td><td colspan='2'></td></tr></table>");
					rs1.getStatement().getConnection().close();
				}
			
				if(TipoBusqueda.equals("FEENUS")){
					ParametroFacturacion="and fef.fecha between '"+FechaIni+"' and '"+FechaFin+"' and fef.cod_eps='"+CodEnt+"' and fef.cod_usuario_fk='"+CodUsua+"' order by fn.consecutivo";
					ParametroReciboCajas="and adrc.fecha between '"+FechaIni+"' and '"+FechaFin+"' and aent.ent_nit='"+CodEnt+"' and adrc.CodUsu_fk='"+CodUsua+"' order by adrc.codigo";
					rs=mca.ConsultaFacturacionDI(ParametroFacturacion);
					rs1=mca.ConsultaRecibosCajaDI(ParametroReciboCajas);
					out.print("<tr><td colspan='11' align='center' ><b>BUSQUEDA REALIZADA CON LOS SIGUIENTES PARAMETROS FECHA INICIAL "+FechaIni+" FECHA FINAL "+FechaFin+" ENTIDAD "+NombreEntidad+" USUARIO "+NombreUsuario+"</b></td></tr>");
					out.print("<tr bgcolor='red'><td colspan='11' align='center'><b><font color='white'>FACTURAS GENERADAS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA'><td>Numero Factura</td><td>Fecha Factura</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Facturado</td><td>Copago</td><td>Recibido</td><td>Descuentos</td><td>Valor Neto</td></tr>");
					while(rs.next()){
						SumaValorFacturado=SumaValorFacturado+rs.getLong("ValorFacturado");
						SumaCopago=SumaCopago+rs.getLong("copago");
						SumaAnticipos=SumaAnticipos+rs.getLong("anticipos");
						SumaDescuentos=SumaDescuentos+rs.getLong("Descuentos");
						SumaValorNeto=SumaValorNeto+rs.getLong("valorNeto");
						out.print("<tr><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("fecha")+"</td><td>"+rs.getString("razon_social")+"</td><td>"+rs.getString("nombre_paciente")+"</td><td>"+rs.getString("documento")+"</td><td>"+rs.getString("usuario")+"</td><td>"+formatMoneda(rs.getString("ValorFacturado"))+"</td><td>"+formatMoneda(rs.getString("copago"))+"</td><td>"+formatMoneda(rs.getString("anticipos"))+"</td><td>"+formatMoneda(rs.getString("Descuentos"))+"</td><td>"+formatMoneda(rs.getString("valorNeto"))+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTALES</td><td>"+formatMoneda(SumaValorFacturado+"")+"</td><td>"+formatMoneda(SumaCopago+"")+"</td><td>"+formatMoneda(SumaAnticipos+"")+"</td><td>"+formatMoneda(SumaDescuentos+"")+"</td><td>"+formatMoneda(SumaValorNeto+"")+"</td></tr>");
					rs.getStatement().getConnection().close();
					out.print("<tr bgcolor='blue'><td colspan='11' align='center'><b><font color='white'>RECIBOS DE CAJA GENERADOS</font></b></td></tr>");
					out.print("<tr bgcolor='#DADADA'><td>Numero Recibo Caja</td><td>Fecha</td><td>Entidad</td><td>Nombre Paciente</td><td>Documento Paciente</td><td>Usuario Factura</td><td>Valor Recibo</td><td colspan='2'>Concepto</td><td colspan='2'>Observacion</td></tr>");
					while(rs1.next()){
						SumaRecibosCaja=SumaRecibosCaja+rs1.getLong("abono");
						out.print("<tr><td>"+rs1.getString("codigo")+"</td><td>"+rs1.getString("fecha")+"</td><td>"+rs1.getString("nombre_entidad")+"</td><td>"+rs1.getString("Nombrepaciente")+"</td><td>"+rs1.getString("Documento")+"</td><td>"+rs1.getString("usuario")+"</td><td>"+formatMoneda(rs1.getString("abono"))+"</td><td colspan='2'>"+rs1.getString("concepto")+"</td><td colspan='2'>"+rs1.getString("Observacion")+"</td></tr>");
					}
					out.print("<tr bgcolor='#DADADA'><td></td><td></td><td></td><td></td><td></td><td>TOTAL</td><td>"+formatMoneda(SumaRecibosCaja+"")+"</td><td colspan='2'></td><td colspan='2'></td></tr></table>");
					rs1.getStatement().getConnection().close();
				}
				//out.print("<table border='1' cellspacing='0' cellpadding='0'>");
				//out.print("<tr><td></td></tr>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("CCADI")){
			try {
				
				out.print("<table border='0' cellspacing='0' cellpadding='0'> " +
					"<tr><td>Fecha Inicial <input name='txtFechaInicial' type='text' id='txtFechaInicial' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /> " +
					"<td>Fecha Final <input name='txtFechaFinal' type='text' id='txtFechaFinal' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10'  /></td> ");
				out.print("<td>Entidad  <select id='CodEnt'><option value='Seleccione' selected=''>Seleccione</option>");
				rs=mca.ListarEntidades();			
				while(rs.next()){
					out.print("<option value="+rs.getString("ent_nit")+">"+rs.getString("nombre_entidad")+"</option>");
				}
				out.print("</select></td>");
				rs.getStatement().getConnection().close();
				out.print("<td>Usuario <select id='CodUsu'><option value='Seleccione' selected=''>Seleccione</option>");
				rs=mca.ListarUsuarios();
				while(rs.next()){
					out.print("<option value="+rs.getString("usu_codigo")+">"+rs.getString("usuario")+"</option>");
				}
				out.print("</select></td>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("");
			out.print("<td><input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Buscar' onclick='BuscarDetalleIngresos()' /></td></tr></table>");

		}
		
		if(va.equals("RECOS")){
			String TipoBus=req.getParameter("TipoBus");
			String NumOrden=req.getParameter("NumOrden");
			String NumDoc=req.getParameter("NumDoc");
			String TipoDocu=req.getParameter("TipoDoc");
			String NombrePac=req.getParameter("NombrePac");
			String PrimerApellido=req.getParameter("PrimerApellido");
			String SegundoApellido=req.getParameter("SegundoApellido");
			String txtFechaInicial=req.getParameter("txtFechaInicial");
			String txtFechaFinal=req.getParameter("txtFechaFinal");
			
			
			
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
				
				if(TipoBus.equals("CFEH")){
					String FechaIni,DiaI,MesI,AnoI=null;
					DiaI=txtFechaInicial.substring(0,2);
					MesI=txtFechaInicial.substring(3,5);
					AnoI=txtFechaInicial.substring(6,10);
					FechaIni=AnoI+"-"+MesI+"-"+DiaI;
					
					String FechaFin,DiaF,MesF,AnoF=null;
					DiaF=txtFechaFinal.substring(0,2);
					MesF=txtFechaFinal.substring(3,5);
					AnoF=txtFechaFinal.substring(6,10);
					FechaFin=AnoF+"-"+MesF+"-"+DiaF;
					
					Parametro="AND fos.fecha_insercion BETWEEN '"+FechaIni+"' AND '"+FechaFin+"' ";
				}
				
				rs=mca.ConsultarOrdenes(Parametro);	
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr bgcolor='#DADADA' class='style12' ><td>FECHA y HORA</td><td>NUMERO ORDEN</td><td>PACIENTE</td><td>ENTIDAD PRESTADORA</td><td>ESTADO ORDEN</td></tr>");
				while(rs.next()){
					String EstadoOrd="";
					
					out.print("<tr><td><a href='#' onclick='ReporteOrdenServicio("+rs.getString("codigo")+")'>"+rs.getString("fecha_insercion")+"/"+rs.getString("hora_insercion")+"</a></td><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("Paciente")+"</td><td>"+rs.getString("NombreEntidadpre")+"</td>");
					if(rs.getString("estado").equals("1")){
						EstadoOrd="ACTIVA";
						out.print("<td bgcolor='green' ><font color='white'>"+EstadoOrd+"</font></td></tr>");
					}
					if(rs.getString("estado").equals("2")){
						EstadoOrd="ANULADA";
						out.print("<td bgcolor='red'><font color='white'>"+EstadoOrd+"</font></td></tr>");	
					}
					if(rs.getString("estado").equals("3")){
						EstadoOrd="VENCIDA";
						out.print("<td bgcolor='blue'><font color='white'>"+EstadoOrd+"</font></td></tr>");	
					}
					//out.print("<td>"+EstadoOrd+"</td></tr>");
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
					if(rs.getString("servicio").equals("Consulta Externa")){
						//* se actualiza la tabla admision se pone el estado en '2'
						mca.ActualizarAdmisionOrden(rs.getString("CodAdmision"));
						//* se actualiza el encabezado se pone estado='5'
						mca.ActualizarEncabezadoOrden(rs.getString("CodEncFactura"));
						//* se actualiza el detalle del encabezado se ponen los cargues facturado='5'*/
						mca.ActualizarDetalleEncabezadoOrden(rs.getString("CodEncFactura"));
					}
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






