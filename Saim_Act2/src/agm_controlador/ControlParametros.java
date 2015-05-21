/***********************/
/**
 * controlador: ControlParametros se encuentra la forma en que son 
 * creadas los parametros que se van a usar para la creacion de los horarios
 * de atencion.
 */
package agm_controlador;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import agm_metodo.MetodoMedicos;
import agm_metodo.MetodoParametros;



/**
 * Servlet implementation class ControlParametros
 */
public class ControlParametros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}


	//@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String va = req.getParameter("valor");
		String Dia=req.getParameter("Dia");
		String HoraInicial=req.getParameter("HoraInicial");
		String HoraFinal=req.getParameter("HoraFinal");
		String tiempoCita=req.getParameter("tiempoCita");		
		String CodigoParametro=req.getParameter("CodigoParametro");
		/********variables para guardar el horario del medico***********/
		String CodigoMedico=req.getParameter("CodigoMedico");
		String HorasMedico=req.getParameter("HorasMedico");
		String FechaMedico=req.getParameter("FechaMedico");
		String FechaMes=req.getParameter("FechaMes");
		String CodHorMed=req.getParameter("CodHorMed");
		String estado="0";
		/*******************/
		PrintWriter out=res.getWriter();
		MetodoParametros mp = new MetodoParametros();
		MetodoMedicos mmd = new MetodoMedicos();
		ResultSet rs=null;
		ResultSet rs1=null;	
		ResultSet rs5=null;	
		
		if(va.equals("EDHM2")){
			String Man=req.getParameter("Man");
			String Med=req.getParameter("Med");
			String Tar=req.getParameter("Tar");
			String FechaSelEliminar=req.getParameter("FechaSelEliminar");
			String sql="";
			if(Man.equals("0")){Man="";System.out.print("Entro AM");}else{
				Man="SUBSTRING(horas, 7, 3)='AM'";
				sql="AND fechas='"+FechaSelEliminar+"' AND ("+Man+")";
				mp.EliminarCalendarioDiaMedico(CodigoMedico, sql);
			}
			if(Med.equals("0")){Med="";System.out.print("Entro MD");}else{
				Med="SUBSTRING(horas, 7, 3)='MD'";
				sql="AND fechas='"+FechaSelEliminar+"' AND ("+Med+")";
				mp.EliminarCalendarioDiaMedico(CodigoMedico, sql);				
			}
			if(Tar.equals("0")){Tar="";System.out.print("Entro PM");}else{
				Tar="SUBSTRING(horas, 7, 3)='PM'";
				sql="AND fechas='"+FechaSelEliminar+"' AND ("+Tar+")";
				mp.EliminarCalendarioDiaMedico(CodigoMedico, sql);
			}

			
			
		}
		
		if(va.equals("EDC")){
			mp.EliminarParametrosRegistroMedico(CodHorMed);
		}
		
		if(va.equals("VDSHM")){
			String FechaSel=req.getParameter("FechaSel");
			String FechaClick=req.getParameter("FechaClick");
			rs=mp.DetalleActivosHorarioMedicoPorDia(CodigoMedico, FechaSel);
			try {
				out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2' align='center' ><td colspan='5' >DETALLE HORARIO "+FechaClick+" <select id='cmbFechaEl' onchange='VerDiaSeleccionado()'><option value='Seleccione' >Seleccione</option> ");
				rs1=mp.DetalleActivosHorarioMedicoAgrupado(CodigoMedico, FechaClick);
				while(rs1.next()){
					out.print("<option value="+rs1.getString("fechas")+">"+rs1.getString("fechas")+"</option>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</select></td></tr><tr>");
				out.print("<tr></tr>");
				int columna=0;
				int ContDisponibles=0;
				out.print("<tr><td><label><input type='checkbox' name='checkboxAM' id='checkboxAM' onclick='CHKAME()' value='checkbox'>Mañana</label>" +
				"<label><input type='checkbox' name='checkboxMD' id='checkboxMD' onclick='CHKMDE()' value='checkbox'>Mediodia</label>" +
				"<label><input type='checkbox' name='checkboxPM' id='checkboxPM' onclick='CHKPME()' value='checkbox'>Tarde</label><input name='btnIngresarHoraDia' type='button' id='btnIngresarHoraDia' value='     Eliminar     ' onclick='EliminarHorasDiasSeleccionada()' /></td></tr>" );
				while(rs.next()){
					//SumaHoras=SumaHoras+rs.getInt(7);
					String Est=rs.getString(4);
					if(Est.equals("0")){
						//disponible verde
						ContDisponibles=ContDisponibles+1;
						out.print("<td title='Click para eliminar'  onclick='EliminarHora("+rs.getString(1)+","+CodigoMedico+",&quot;"+FechaMes+"&quot;)'><input name='ChkHora' type='CheckBox' id='ChkHora' value="+rs.getString(2)+" />"+rs.getString(3)+"/"+rs.getString(2)+"</td>");
						}
					if(Est.equals("1")){
						//asignada roja
						out.print("<td style='background:#FF0000' onclick='EliminarHora(&quot;na&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");
						}
					if(Est.equals("2")){
						//en consultorio azul
						out.print("<td style='background:#0000FF' onclick='EliminarHora(&quot;at&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");
						}
					if(Est.equals("3")){
						//atendida morado
						out.print("<td style='background:#800080' onclick='EliminarHora(&quot;fa&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");	
					}
					if(Est.equals("4")){
						//fuera de fecha
						out.print("<td style='background:#FFFF00' onclick='EliminarHora(&quot;ff&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");
					}					
					columna++;
					if(columna==5){out.print("</tr>");columna=0;}
		
				}
				out.print("<input id='txtCon' type='hidden' value="+ContDisponibles+"></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("VDHM")){
			
			try {
				
				out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2' align='center' ><td colspan='5' >DETALLE HORARIO "+FechaMes+" <select id='cmbFechaEl' onchange='VerDiaSeleccionado()'><option value='Seleccione' >Seleccione</option> ");
				rs1=mp.DetalleActivosHorarioMedicoAgrupado(CodigoMedico, FechaMes);
				while(rs1.next()){
					out.print("<option value="+rs1.getString("fechas")+">"+rs1.getString("fechas")+"</option>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</select></td></tr><tr>");
				out.print("<tr></tr>");
				int columna=0;
				int SumaHoras=0;
				rs=mp.DetalleActivosHorarioMedico(CodigoMedico, FechaMes);
				while(rs.next()){
					SumaHoras=SumaHoras+rs.getInt(7);
					String Est=rs.getString(4);
					if(Est.equals("0")){
						//disponible verde
						out.print("<td >"+rs.getString(3)+"/"+rs.getString(2)+"</td>");
						}
					if(Est.equals("1")){
						//asignada roja
						out.print("<td style='background:#FF0000' onclick='EliminarHora(&quot;na&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");
						}
					if(Est.equals("2")){
						//en consultorio azul
						out.print("<td style='background:#0000FF' onclick='EliminarHora(&quot;at&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");
						}
					if(Est.equals("3")){
						//atendida morado
						out.print("<td style='background:#800080' onclick='EliminarHora(&quot;fa&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");	
					}
					if(Est.equals("4")){
						//fuera de fecha
						out.print("<td style='background:#FFFF00' onclick='EliminarHora(&quot;ff&quot;)'><font color='white'>"+rs.getString(3)+"/"+rs.getString(2)+"</font></td>");
					}					
					columna++;
					if(columna==5){out.print("</tr>");columna=0;}
				}
				SumaHoras=SumaHoras/60;
				out.print("<tr><td colspan='5'>Total Horas:"+SumaHoras+"</td></tr>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		if(va.equals("EDSOK")){
			String DiaE=req.getParameter("Dia");
			String Jornada=req.getParameter("Jornada");
			String sql="";
			if(Jornada.equals("AM")){
				sql="AND SUBSTRING(horas, 7, 3)='AM'";
			}
			if(Jornada.equals("PM")){
				sql="AND (SUBSTRING(horas, 7, 3)='PM' OR SUBSTRING(horas, 7, 3)='MD')";
			}
			mp.EliminarDiaMedico(CodigoMedico, DiaE,sql);
		}
		if(va.equals("EDCS")){
			out.print("<table width='100%' border='1'><tr bgcolor='#BDBDBD'><td colspan='3'>ELIMINAR DIAS CALENDARIO DE ATENCION</td></tr>");
		}
		
		
		if(va.equals("EDS")){
			rs=mp.VerDiasAtencionMedico(CodigoMedico);
			try {
				out.print("<table width='100%' border='1'><tr bgcolor='#BDBDBD'><td colspan='3'>ELIMINAR DIAS DE ATENCION</td></tr>");
				while(rs.next()){
					if(rs.getString("Dia").equals("Monday")){
						//out.print("<tr><td><label><input name='dia' type='checkbox' id='dia' value='Monday' />Lunes</label></td><td><label><input name='dia' type='checkbox' id='dia' value='AM' />AM</label><label><input name='dia' type='checkbox' id='dia' value='PM' />PM</label></td></tr>");
						out.print("<tr onclick='EliminarDia("+CodigoMedico+",&quot;"+rs.getString("Dia")+"&quot;,&quot;"+rs.getString("jornada")+"&quot;)' ><td>Lunes</td>");
						if(rs.getString("jornada").equals("AM")){
							out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
						}
						if(rs.getString("jornada").equals("PM")){
							out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
						}
					}
					if(rs.getString("Dia").equals("Tuesday")){
						//out.print("<tr><td><label><input name='dia' type='checkbox' id='dia' value='Tuesday' />Martes</label></td><td><label><input name='dia' type='checkbox' id='dia' value='AM' />AM</label><label><input name='dia' type='checkbox' id='dia' value='PM' />PM</label></td></tr>");
						out.print("<tr onclick='EliminarDia("+CodigoMedico+",&quot;"+rs.getString("Dia")+"&quot;,&quot;"+rs.getString("jornada")+"&quot;)'><td>Martes</td>");
						if(rs.getString("jornada").equals("AM")){
							out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
						}
						if(rs.getString("jornada").equals("PM")){
							out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
						}
					}
					if(rs.getString("Dia").equals("Wednesday")){
						//out.print("<tr><td><label><input name='dia' type='checkbox' id='dia' value='Wednesday' />Miercoles</label></td><td><label><input name='dia' type='checkbox' id='dia' value='AM' />AM</label><label><input name='dia' type='checkbox' id='dia' value='PM' />PM</label></td></tr>");
						out.print("<tr onclick='EliminarDia("+CodigoMedico+",&quot;"+rs.getString("Dia")+"&quot;,&quot;"+rs.getString("jornada")+"&quot;)'><td>Miercoles</td>");
						if(rs.getString("jornada").equals("AM")){
							out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
						}
						if(rs.getString("jornada").equals("PM")){
							out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
						}
					}
					if(rs.getString("Dia").equals("Thursday")){
						//out.print("<tr><td><label><input name='dia' type='checkbox' id='dia' value='Thursday' />Jueves</label></td><td><label><input name='dia' type='checkbox' id='dia' value='AM' />AM</label><label><input name='dia' type='checkbox' id='dia' value='PM' />PM</label></td></tr>");
						out.print("<tr onclick='EliminarDia("+CodigoMedico+",&quot;"+rs.getString("Dia")+"&quot;,&quot;"+rs.getString("jornada")+"&quot;)'><td>Jueves</td>");
						if(rs.getString("jornada").equals("AM")){
							out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
						}
						if(rs.getString("jornada").equals("PM")){
							out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
						}
					}
					if(rs.getString("Dia").equals("Friday")){
						//out.print("<tr><td><label><input name='dia' type='checkbox' id='dia' value='Friday' />Viernes</label></td><td><label><input name='dia' type='checkbox' id='dia' value='AM' />AM</label><label><input name='dia' type='checkbox' id='dia' value='PM' />PM</label></td></tr>");
						out.print("<tr onclick='EliminarDia("+CodigoMedico+",&quot;"+rs.getString("Dia")+"&quot;,&quot;"+rs.getString("jornada")+"&quot;)'><td>Viernes</td>");
						if(rs.getString("jornada").equals("AM")){
							out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
						}
						if(rs.getString("jornada").equals("PM")){
							out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
						}
					}
					if(rs.getString("Dia").equals("Saturday")){
						//out.print("<tr><td><label><input name='dia' type='checkbox' id='dia' value='Saturday' />Sabado</label></td><td><label><input name='dia' type='checkbox' id='dia' value='AM' />AM</label><label><input name='dia' type='checkbox' id='dia' value='PM' />PM</label></td></tr>");
						out.print("<tr onclick='EliminarDia("+CodigoMedico+",&quot;"+rs.getString("Dia")+"&quot;,&quot;"+rs.getString("jornada")+"&quot;)'><td>Sabado</td>");
						if(rs.getString("jornada").equals("AM")){
							out.print("<td>Mañana</td><td>07AM-12PM</td></tr>");
						}
						if(rs.getString("jornada").equals("PM")){
							out.print("<td>Tarde</td><td>01PM-04PM</td></tr>");
						}
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("VHM")){
			rs=mp.BuscarMesesActivosHorarioMedico(CodigoMedico);
			try {
				out.print("<table width='100%' border='1'><tr class='style11' id='cabecera2' align='center'><td colspan='6' >RESULTADO DE LA BUSQUEDA</td></tr><tr>");
				out.print("<tr class='style12'><td>Fecha<input id='FechaClick' type='hidden' value=''><input id='FechaSeleccionada' type='hidden' value=''></td><td>Nº Citas disponibles</td><td>Tiempo Consulta</td><td>Total (horas)</td></tr>");
				while(rs.next()){
					String Me="";
					int totalhoras=rs.getInt(7)*rs.getInt(10);
					
					if(rs.getInt(8)<10){
						Me="0"+rs.getString(8);
					}else{
						Me=rs.getString(8);
					}
					String AF=rs.getString(9)+"-"+Me;
					out.print("<tr title='Click para ver detallado' ><td onclick='VerDetalleHorario(&quot;"+AF+"&quot;,"+CodigoMedico+")' >"+AF+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(10)+"</td><td>"+totalhoras/60+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("guardar")){
			rs=mp.BuscarFechaHora2(HorasMedico, FechaMedico,CodigoMedico);
			try {
				if(rs.next()){
					
				}else{
					mmd.CrearHorarioMedico(HorasMedico, FechaMedico, estado, CodigoMedico);
					out.print("Ingresado Exitosamente.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("Ingresar")){
			out.print("<table width='100%' border='1'><tr><td colspan='8'><div align='center' class='style11' id='cabecera2'>Parametrizar Dias de Atencion</div></td></tr>");
			out.print("<tr><td width='8%'><label><input name='dia' type='checkbox' id='dia' value='Monday' />Lunes</label></td><td width='10%'><label><input name='dia2' type='checkbox' id='dia' value='Tuesday' />Martes</label></td><td width='13%'><label><input name='dia2' type='checkbox' id='dia' value='Wednesday' />Miercoles</label></td>");
			out.print("<td width='10%'><label><input name='dia2' type='checkbox' id='dia' value='Thursday' />Jueves</label></td><td width='11%'><label><input name='dia2' type='checkbox' id='dia' value='Friday' />Viernes</label></td><td width='11%'><label><input name='dia2' type='checkbox' id='dia' value='Saturday' />Sabado</label></td>");
			out.print("<td width='13%'><label><input name='dia2' type='checkbox' id='dia' value='Sunday' />Domingo</label></td><td width='24%'><label><input name='chkTodos' type='checkbox' id='chkTodos' onclick='checkAll()' />Seleccionar Todos</label></td></tr></table>");
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Parametrizar Hora</div></td></tr>");
			out.print("<tr><td width='15%'>Hora Inicio Atencion </td><td colspan='2'><label>Hora<select name='cmbHoraIni' id='cmbHoraIni'><option value='01'>01</option><option value='02'>02</option><option value='03'>03</option><option value='04'>04</option><option value='05'>05</option><option value='06'>06</option>");
			out.print("<option value='07'>07</option><option value='08'>08</option><option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>Min<select name='cmbMinIni' id='cmbMinIni'><option value='00'>00</option><option value='30'>30</option></select>");
			out.print("Jornada<select name='cmbJorIni' id='cmbJorIni'><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td><td width='13%'><p>Hora Fin Atencion</p></td><td width='38%'>Hora<select name='cmnHoraFin' id='cmnHoraFin'><option value='01'>01</option><option value='02'>02</option>");
			out.print("<option value='03'>03</option><option value='04'>04</option><option value='05'>05</option><option value='06'>06</option><option value='07'>07</option><option value='08'>08</option><option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>");
			out.print("Min<select name='cmbMinFin' id='cmbMinFin'><option value='00'>00</option><option value='30'>30</option></select>Jornada<select name='cmbJorFin' id='cmbJorFin'><option value='AM'>AM</option><option value='PM'>PM</option></select></td></tr>");
			out.print("<tr><td>&nbsp;</td><td width='19%'>Duracion de la Consulta </td><td colspan='2'><label> Min <input name='txtMinuConsulta' type='text' id='txtMinuConsulta' size='5' maxlength='2' /></label></td><td>&nbsp;</td></tr>");
			out.print("<tr><td colspan='5'><div align='center'><input name='btnIngresarHoraDia' type='button' id='btnIngresarHoraDia' value='     Ingresar     ' onclick='GuardarHorasDias()' /></div></td></tr></table>");
		}
		
		if(va.equals("2")){
			mp.ActualizarParametros(HoraInicial, HoraFinal, tiempoCita, CodigoParametro);
			out.print("Actualizacion Exitosa.");
		}
		
		if(va.equals("3")){
			mp.OmitirParametro(CodigoParametro);
			out.print("Parametro Omitido Exitosamente.");
			
		}
		
		if(va.equals("Actualizar")){
			String HorarioInicial="";
			String HoraInicio="";
			String MinutoInicio="";
			String JornadaInicio="";
			
			String HorarioFinal="";
			String HoraFin="";
			String MinutoFin="";
			String JornadaFin="";
			
			String CodigoHorario="";
			String TiempoConsulta="";
			String DiaSemana="";
			String DiaSemanaEspanol="";
			
			int HorasFinal=0;
			int cont=0;
			String HoradeFin="";
			
			rs=mp.BuscarDatosParametros();
			rs1=mp.BuscarDatosParametros();
			out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2' >Actualizacion De Parametros</div></td></tr>");
			try {
				if(rs.next()){
					
					HorarioInicial=rs.getString(3);
					HorarioFinal=rs.getString(4);
					TiempoConsulta=rs.getString(5);
					int y=HorarioInicial.split(":").length;
					String[] z=HorarioInicial.split(":");		     	
					for(int x=0; x<y-1; x=x+1)
					{ 
						HoraInicio=z[0];MinutoInicio=z[1];JornadaInicio=z[2];
					}
					
					int h=HorarioFinal.split(":").length;
					String[] d=HorarioFinal.split(":");
					
					for(int g=0; g<h-1; g=g+1)
					{ 
						HoraFin=d[0];MinutoFin=d[1];JornadaFin=d[2];
						if(JornadaFin.equals("PM")){
							int hi=Integer.parseInt(HoraFin);
							
							HorasFinal=hi-12;
							HoradeFin="0"+String.valueOf(HorasFinal);
						}
					}
				}
				out.print("<tr><td width='14%'>Hora Inicio Atencion </td><td width='26%'><label>Hora<select name='cmbHoraInicio' id='cmbHoraInicio'><option selected='selected' value="+HoraInicio+">"+HoraInicio+"</option><option value='01'>01</option><option value='02'>02</option><option value='03'>03</option><option value='04'>04</option><option value='05'>05</option>");
				out.print("<option value='06'>06</option><option value='07'>07</option><option value='08'>08</option><option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>");
				out.print("Min<select name='cmbMinutoInicio' id='cmbMinutoInicio'><option selected='selected' value="+MinutoInicio+">"+MinutoInicio+"</option><option value='00'>00</option><option value='30'>30</option></select>");
				out.print("Jornada<select name='cmbJornadaInicio' id='cmbJornadaInicio'><option selected='selected' value="+JornadaInicio+">"+JornadaInicio+"</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>");
				out.print("<td width='12%'>Hora Fin Atencion </td><td width='23%'>Hora<select name='cmbHoraFin' id='cmbHoraFin'><option selected='selected' value="+HoradeFin+">"+HoradeFin+"</option><option value='01'>01</option><option value='02'>02</option><option value='03'>03</option><option value='04'>04</option><option value='05'>05</option><option value='06'>06</option><option value='07'>07</option><option value='08'>08</option>");
				out.print("<option value='09'>09</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>Min<select name='cmbMinutoFin' id='cmbMinutoFin'><option selected='selected' value="+MinutoFin+">"+MinutoFin+"</option><option value='00'>00</option><option value='30'>30</option></select>Jornada<select name='cmbJornadaFin' id='cmbJornadaFin'><option selected='selected' value="+JornadaFin+">"+JornadaFin+"</option><option value='AM'>AM</option><option value='PM'>PM</option></select></td>");
				out.print("<td width='12%'>Duracion Consulta </td><td width='13%'><label>Min<input name='txtDuracionConsulta' type='text' id='txtDuracionConsulta' size='10' value="+TiempoConsulta+"></label></td></tr></table>");
				
				rs.getStatement().getConnection().close();
				out.print("<table width='100%' border='1'><tr>");
				while(rs1.next()){
					CodigoHorario=rs1.getString(1);
					DiaSemana=rs1.getString(2);
					if(DiaSemana.equals("Monday")){DiaSemanaEspanol="Lunes";}
					if(DiaSemana.equals("Tuesday")){DiaSemanaEspanol="Martes";}
					if(DiaSemana.equals("Wednesday")){DiaSemanaEspanol="Miercoles";}
					if(DiaSemana.equals("Thursday")){DiaSemanaEspanol="Jueves";}
					if(DiaSemana.equals("Friday")){DiaSemanaEspanol="Viernes";}
					if(DiaSemana.equals("Saturday")){DiaSemanaEspanol="Sabado";}
					if(DiaSemana.equals("Sunday")){DiaSemanaEspanol="Domingo";}
					cont=cont+1;
					out.print("<td>"+DiaSemanaEspanol+"   ---   <a href='#' onclick='OmitirParametro("+CodigoHorario+")'>Omitir</a><input name='txtCodigoParametro' type='hidden' id='txtCodigoParametro' value="+CodigoHorario+"></td>");
				}
				out.print("</tr><input name='txtContador' type='hidden' id='txtContador' value="+cont+"></table>");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("<table width='100%' border='1'><tr><td><div align='center'><input name='btnActualizarParametros' type='button' id='btnActualizarParametros' value='     Actualizar     ' onClick='ActualizarParametros()'></div></td></tr></table>");
		}
		
		if(va.equals("1")){
		mp.CrearParametro(Dia, HoraInicial, HoraFinal, tiempoCita);
		out.print("Ingreso Exitoso.");
		}
		
		if(va.equals("horario")){
			rs=mmd.BuscarMedicoTodos();
			out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Asignar Horario Medico </div></td></tr><tr><td width='15%'><div>Seleccione Medico </div></td><td><div><select name='cmbMedico' id='cmbMedico' onchange='horario()'><option value='Seleccione' selected='selected'>Seleccione</option>");
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
			out.print("</select></div></td></tr><tr><td colspan='2'><div id='Horario'></div></td></tr></table>");
		}	

		/*********************************************************************************************************************************/
		/*********************************************************************************************************************************/
		/*********************************************************************************************************************************/
		if(va.equals("asignar-nj")){
			//ASIGNACION HORARIO NIÑO JESUS.
		//	String CodigoMedico=req.getParameter("CodigoMedico");
			String TiempoCon="";
			rs=mp.BuscarLapsoMedico(CodigoMedico);
			try {
				if(rs.next()){
					TiempoCon=rs.getString("tiempo_consulta");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print("<table border='0' width='100%' ><tr><td>Tiempo Consulta:</td><td>"+TiempoCon+" Minutos</td></tr><tr><td width='178' class='style12'>FECHA INICIAL</td><td><span class='style12'>Dia<select name='cmbDia' id='cmbDia'><option value='DD'>DD</option>");
			
			for(int i=1;i<=31;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select> Mes <select name='cmbMes' id='cmbMes'><option value='MM'>MM</option>");
			
			for(int i=1;i<=12;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select> Año <input type='text' name='cmbAnio' id='cmbAnio'>");
			
			//out.print("<option value='AAAA'>AAAA</option>");//CAMPO ABIERTO
			
			out.print(" </select></span></td><td width='127'><span class='style12'>FECHA FINAL </span></td><td width='594'><span class='style12'>Dia<select name='cmbDiaF' id='cmbDiaF'><option value='DD'>DD</option>");
			
			for(int i=1;i<=31;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select>Mes<select name='cmbMesF' id='cmbMesF'><option value='MM'>MM</option>");//DENTRO DE UN WHILE DE MESES
			
			for(int i=1;i<=12;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select>Año<input type='text' name='cmbAnioF' id='cmbAnioF'></span></td></tr>");//txt con el año
			
			out.print("<tr><td class='style12'>HORA INICIAL </td><td class='style12' >Hora<span class='style12'><select name='cmbHoraI' id='cmbHoraI'><option value='HH'>HH</option>");
			
			for(int i=1;i<=12;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select></span>Minutos<span class='style12'><select name='cmbMinutosI' id='cmbMinutosI'><option value='MM'>MM</option>");
			
			for(int i=0;i<=59;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select></span>Jornada<span class='style12'><select name='cmbJornadaI' id='cmbJornadaI'><option value='JJ'>JJ</option>");
			
			out.print("<option value='AM'>AM</option>");
			out.print("<option value='PM'>PM</option>");
			
			out.print("</select></span></td><td><span class='style12'>HORA FINAL</span> </td><td class='style12'>Hora<span class='style12'><select name='cmbHoraF' id='cmbHoraF'><option value='HH'>HH</option>");
			
			for(int i=1;i<=12;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select></span>Minutos<span class='style12'><select name='cmbMinutosF' id='cmbMinutosF'><option value='MM'>MM</option>");
			
			for(int i=0;i<=59;i++){out.print("<option value="+i+">"+i+"</option>");}
			
			out.print("</select></span>Jornada<span class='style12'><select name='cmbJornadaF' id='cmbJornadaF'><option value='JJ'>JJ</option>");
			
			out.print("<option value='AM'>AM</option>");
			out.print("<option value='PM'>PM</option>");
			
			out.print("</select></span></td></tr>");
			
			out.print("<tr><td class='style12'>DIAS DE ATENCION </td><td colspan='2' class='style12'><label><input type='checkbox' name='checkboxL'  id='checkboxL' value='checkbox'>Lunes</label><label><input type='checkbox' name='checkboxM' id='checkboxM' value='checkbox'>Martes</label><label><input type='checkbox' name='checkboxMI' id='checkboxMI' value='checkbox'>Miercoles</label><label><input type='checkbox' name='checkboxJ' id='checkboxJ' value='checkbox'>Jueves</label><label><input type='checkbox' name='checkboxV' id='checkboxV' value='checkbox'>Viernes</label><label><input type='checkbox' name='checkboxS' id='checkboxS' value='checkbox'>Sabado</label><label><input type='checkbox' name='checkboxD' id='checkboxD' value='checkbox'>Domingo</label></td>");
			
			out.print("<td><label><input type='button' name='Button' value='     Ingresar     ' onclick='GuardarHorarioNV()' ></label></td></tr><tr><td colspan='4' class='style12' align='center'>&nbsp;</td></tr></table>");
			out.print("<table border='0' width='100%' ><tr><td id='' ></td></tr></table>");
			out.print("");
			out.print("");
			out.print("");
			out.print("");
			
		}
		
		if(va.equals("asignar")){
			//ASIGNACION HORARIO ANTES
			out.print("<table width='100%' border='1'><tr><td class='style12'>DIAS DE ATENCION </td>" +
					"<td colspan='2' class='style12'><label><input type='checkbox' onclick='CHKLunes()' name='checkboxL'  id='checkboxL' value='checkbox'>Lunes</label>" +
					"<label><input type='checkbox' name='checkboxM' id='checkboxM' onclick='CHKMartes()' value='checkbox'>Martes</label>" +
					"<label><input type='checkbox' name='checkboxMI' id='checkboxMI' onclick='CHKMiercoles()' value='checkbox'>Miercoles</label>" +
					"<label><input type='checkbox' name='checkboxJ' id='checkboxJ' onclick='CHKJueves()' value='checkbox'>Jueves</label>" +
					"<label><input type='checkbox' name='checkboxV' id='checkboxV' onclick='CHKViernes()' value='checkbox'>Viernes</label>" +
					"<label><input type='checkbox' name='checkboxS' id='checkboxS' onclick='CHKSabado()' value='checkbox'>Sabado</label>" +
					"</td>" +
					"<td>JORNADA DE ATENCION</td><td><label><input type='checkbox' name='checkboxAM' id='checkboxAM' onclick='CHKAM()' value='checkbox'>Mañana</label>" +
					"<label><input type='checkbox' name='checkboxMD' id='checkboxMD' onclick='CHKMD()' value='checkbox'>Mediodia</label>" +
					"<label><input type='checkbox' name='checkboxPM' id='checkboxPM' onclick='CHKPM()' value='checkbox'>Tarde</label>" +
					"</td><td><input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Asignar Horario' onclick='Chkdato()' /></td></tr></table>");
			
			out.print("<table width='100%' border='1'><tr><td>");
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			java.util.Date primerDia= hoy.getTime();
			int diasMes[]= new int[12];
			diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
			diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;
			
			int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
			String NombreMes = "";
			anio=hoy.get(java.util.Calendar.YEAR);
			dia=hoy.get(java.util.Calendar.DATE);
			mes=hoy.get(java.util.Calendar.MONTH);
			mes=mes+1;
			if(mes==1){NombreMes="Enero";}if(mes==2){NombreMes="Febrero";}if(mes==3){NombreMes="Marzo";}if(mes==4){NombreMes="Abril";}if(mes==5){NombreMes="Mayo";}if(mes==6){NombreMes="Junio";}if(mes==7){NombreMes="Julio";}if(mes==8){NombreMes="Agosto";}if(mes==9){NombreMes="Septiembre";}if(mes==10){NombreMes="Octubre";}if(mes==11){NombreMes="Noviembre";}if(mes==12){NombreMes="Diciembre";}
			
			if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) /*Compruebo que el año es bisiesto*/
			{
			diasMes[1] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}
			nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
			primerDia.setDate(1);
			diaInicial=primerDia.getDay();
			out.print("<table border>");
			out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio+" </tr> "); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
			out.print("</tr>");out.print("<tr></tr>");
			columna=0;
			for(i=0;i<diaInicial;i++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{
				out.print("<td align=center><font size+=4>.");
				columna++;
				out.print("</font>");
			}
			int a=0;
			for(i=1;i<=nDias;i++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a=a+1;				
				out.print("<td align=center>");
				if(i==dia) //Si el día a escribir es el día de hoy
			{
				//out.print("<font color=\"#ff0000\" size+=7><b>"); //Resaltamos el día de hoy
			}
			out.print(i);
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio+"/"+mes+"/"+i+" />");

			if(i==dia) //Si el día a escribir es el día de hoy
			{
				out.print("</b></font>"); //Cerramos la etiqueta de edición de las propiedades de las fuentes
			}

			columna++; //Pasamos a la columna siguiente.

			if (columna==7) //el numero de columnas que contiene el mes.
			{out.print("<tr>"); //Abrimos una nueva fila
			columna=0; //Reseteamos la variables de columnas.
			}
			}			
			out.print("</table>"); //Cerramos la tabla.
			out.print("</td><td>");
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+1;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			int DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias1=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			int a1=0;
			for(e=1;e<=nDias1;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			out.print("</td><td>");
			/****************************************/
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias2;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+2;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias2=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias2;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			out.print("</td><td>");
			/****************************************/
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias3;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+3;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias3=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias3;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			/****************************************/
			out.print("</td><td>");
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias4;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+4;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias4=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias4;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			/****************************************/			
			out.print("</td></tr>");
			
			
			out.print("<tr><td>");
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias5;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+5;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias5=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias5;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			/****************************************/
			out.print("</td><td>");
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias6;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+6;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias6=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias6;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			/****************************************/	
			//out.print("</td><td>");
			/****************************************/
			out.print("</td><td>");
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias7;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+7;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias7=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias7;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			/****************************************/
			out.print("</td><td>");
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias8;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+8;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			
			nDias8=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias8;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			/****************************************/
			out.print("</td><td>");
			/**ESPACIO PARA EL MES SIGUIENTE**/
			//String NombreMes = "";
			//int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			//int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			//java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			int nDias9;
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+9;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			//Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			 DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			if(mes1==14){mes1=2;anio1=anio1+1;NombreMes="Febrero";}
			if(mes1==15){mes1=3;anio1=anio1+1;NombreMes="Marzo";}
			if(mes1==16){mes1=4;anio1=anio1+1;NombreMes="Abril";}
			if(mes1==17){mes1=5;anio1=anio1+1;NombreMes="Mayo";}
			if(mes1==18){mes1=6;anio1=anio1+1;NombreMes="Junio";}
			if(mes1==19){mes1=7;anio1=anio1+1;NombreMes="Julio";}
			if(mes1==20){mes1=8;anio1=anio1+1;NombreMes="Agosto";}
			if(mes1==21){mes1=9;anio1=anio1+1;NombreMes="Septiembre";}
			if(mes1==22){mes1=10;anio1=anio1+1;NombreMes="Octubre";}
			if(mes1==23){mes1=10;anio1=anio1+1;NombreMes="Noviembre";}
			if(mes1==24){mes1=10;anio1=anio1+1;NombreMes="Diciembre";}
			nDias9=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			 a1=0;
			for(e=1;e<=nDias9;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"/"+mes1+"/"+e+" />");
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
			out.print("</td></tr>");
			
			out.print("</table>");
			
			int TotnDias=nDias+nDias1+nDias2+nDias3+nDias4+nDias5+nDias6+nDias7+nDias8+nDias9;
			out.print("<input name='txtContadorFecha' type='hidden' id='txtContadorFecha' value="+TotnDias+" /></tr></table>");
			
			out.print("<table><tr><td>Seleccione Las Horas </td></tr></table>");
			/********************************************************/
			/**
			 * contenido de los rangos de horas
			 */
			/*************nuevo *************/
			String horaI="";String minutoI="";String JornadaI="";String horaF="";
			String minutoF="";String JornadaF="";String Lapso="";
		//	String p1="";String p2="";String p3="";String p3f="";String p1f="";String p2f="";
			String CodMedico=req.getParameter("CodigoMedico");
			int contador = 0;		
			int contadorChk = 0;
			 try {
				 rs5=mp.BuscarLapsoMedico(CodMedico);
				 if(rs5.next()){
					 Lapso=rs5.getString(11);	
				 }
				 rs5.getStatement().getConnection().close();
				 rs5=mp.BuscarParametros();
				 if(rs5.next()){				 
				// rs1=mp.BuscarParametros();
				//if(rs1.next()){
					horaI=rs5.getString(2).substring(0,2);
					minutoI=rs5.getString(2).substring(3,5);
					JornadaI=rs5.getString(2).substring(6,8);
					horaF=rs5.getString(3).substring(0,2);
					minutoF=rs5.getString(3).substring(3,5);
					JornadaF=rs5.getString(3).substring(6,8);
					//Lapso=rs5.getString(4);					
			//	}
			//	rs1.getStatement().getConnection().close();
						
			// p1=horaI;p2 = minutoI;p3 = JornadaI;	
					
			// p1f=horaF;p2f=minutoF;p3f =JornadaF;
			/*******************fin nuevo****************************/
			 int hi=Integer.parseInt(horaI);
			 int mi=Integer.parseInt(minutoI);
			 int lapso=Integer.parseInt(Lapso);
		     int xyz=0;
		     int hf=Integer.parseInt(horaF);
		     int mf=Integer.parseInt(minutoF);			
		   
				//validar las horas
				int sw=0;int c=0;int cm=0;int compara=hi;
				//System.out.println("compara SOLO "+compara);
				while(sw==0){
					c=c+1;
					compara=compara+1;
					if(compara==hf){
						sw=1;
					}
				}
				if(mi<mf){
					cm=mf-mi;
				}
				if(mi>mf){
					cm=mf+mi;
					c=c-1;
				}

				int suma=(((c*60)+cm)/lapso);
				int xy=mi;	
				out.print("<table border='1' width='100%' ><tr>");
				for (i=0; i<suma; i++){
					
					contador=contador+1;
					contadorChk=contadorChk+1;
					if(contador==1){ 
						out.print("<td width='6%' ><input name='ChkHora' type='CheckBox' id='ChkHora' value="+horaI+":"+minutoI+":"+JornadaI+" />"+horaI+":"+minutoI+" "+JornadaI+"</td> ");
					}					
					else{
						String Minutos="";
						xy=xy+lapso;
						//Validaciones de las horas
						int resta=xy/60;
						if(xy>59){
							xy=(xy-(60*resta)); 
							xyz=Integer.parseInt(horaI)+resta; 
							horaI="0"+String.valueOf(xyz);
						} //si sobrepasa una hora
						//String hh="";
						Minutos=String.valueOf(xy);
						int cho=Minutos.length();
						if(cho==1){
							Minutos="0"+Minutos;
						}
						int tan=horaI.length();
						if(tan==3){
							horaI=horaI.substring(1,3);
						}
						/*if(tan==3){
							hh=horaI.substring(1,3);
						}*/
						//  System.out.println("hh "+hh+" Tamaño "+tan+" horaI= "+horaI+" compara "+compara+" xy "+xy+" JornadaI "+JornadaI+" suma "+suma);
						if((horaI.equals("12"))&&(JornadaI.equals("AM"))){horaI="12"; JornadaI="MD";}
						if((horaI.equals("13"))&&(JornadaI.equals("MD"))){horaI="01"; JornadaI="PM";}
						if((horaI.equals("13"))&&(JornadaI.equals("PM"))){horaI="01"; JornadaI="PM";}
						//if((horaI.equals("12"))&&(JornadaI.equals("AM"))){horaI="12"; JornadaI="PM";}
						//if((p1.equals("13"))&&(p3.equals("PM"))){p1="01"; p3="PM";}
						//System.out.println("compara= "+compara+" p1= "+p1+" xyz= "+xyz+" p3 "+p3);
						
					
						
						//System.out.print("p1 SOLO "+p1);
						if (contadorChk==10){
							out.print("<tr><td></td></tr>");
							contadorChk=0;
							//out.print("<tr><td></td></tr>");
						}else{
							//out.print("<tr><td></td></tr>");
						}
						//System.out.println("tan="+tan+" horaI="+horaI+" Minuto-xy="+xy+" Minutos="+Minutos);
					//	if(JornadaI.equals("AM")){
							if(xy==0){	
								out.print("<td width='6%'><input name='ChkHora' type='CheckBox' id='ChkHora' value="+horaI+":00:"+JornadaI+" />"+horaI+":00:"+JornadaI+"</td>");
							}else{
								out.print("<td width='6%'><input name='ChkHora' type='CheckBox' id='ChkHora' value="+horaI+":"+Minutos+":"+JornadaI+" />"+horaI+":"+Minutos+":"+JornadaI+"</td>");
							}
					//	}
					//	out.print("<table width='100%' border='1'><tr>");
						/*if(p3f.equals("PM")){
							if(xy==0){
								out.print("<td width='6%'><input name='ChkHora' type='CheckBox' id='ChkHora' value="+p1+":00:"+p3+" />"+p1+":00:"+p3f+"</td>");
							}else{
								out.print("<td width='6%'><input name='ChkHora' type='CheckBox' id='ChkHora' value="+p1+":"+xy+":"+p3+" />"+p1+":"+xy+":"+p3f+"</td>");
							}
						}*/
					}
				}
				out.print("</tr></table>");
				/**********************************************/	
			out.print("<table border='0'><tr><td><input name='txtContadorHora' type='hidden' id='txtContadorHora' value="+contador+" /></td></tr></table>");
			 }
				 else{
					 out.print("Configure Los Parametros de Atencion.");
				 }
				 rs5.getStatement().getConnection().close();
			 } catch (SQLException ej) {
					out.print("Error "+ej);
					ej.printStackTrace();
				}
		}
		
		if(va.equals("sgt")){		
			/**ESPACIO PARA EL MES SIGUIENTE**/
			String NombreMes = "";
			int diasMes1[]= new int[13];
			diasMes1[1] = 31;diasMes1[2] = 28;diasMes1[3] = 31;diasMes1[4] = 30;diasMes1[5] = 31;diasMes1[6] = 30;
			diasMes1[7] = 31;diasMes1[8] = 31;diasMes1[9] = 30;diasMes1[10] = 31;diasMes1[11] = 30;diasMes1[12] = 31;			
			int dia1;int anio1;int columna1;int nDias1;int diaInicial1;int e;int contador1 = 0;int mes1;			
			java.util.Calendar hoy1 = java.util.Calendar.getInstance();
			anio1=hoy1.get(java.util.Calendar.YEAR);
			dia1=1;
			mes1=hoy1.get(java.util.Calendar.MONTH)+1;			
			if(((anio1%4==0) && (anio1%100!=0))||(anio1%400==0)) /*Compruebo que el año es bisiesto*/
			{diasMes1[2] = 29; //y si lo es varío el número de días del mes de febrero en el array
			}			
			Calendar currentDate;
			currentDate = Calendar.getInstance(); 
			currentDate.set(anio1,mes1,dia1);			
			int DiaSemanaEmpiezaMes=currentDate.getTime().getDay();			
			mes1=mes1+1;
			if(mes1==1){NombreMes="Enero";}if(mes1==2){NombreMes="Febrero";}if(mes1==3){NombreMes="Marzo";}if(mes1==4){NombreMes="Abril";}if(mes1==5){NombreMes="Mayo";}if(mes1==6){NombreMes="Junio";}if(mes1==7){NombreMes="Julio";}if(mes1==8){NombreMes="Agosto";}if(mes1==9){NombreMes="Septiembre";}if(mes1==10){NombreMes="Octubre";}if(mes1==11){NombreMes="Noviembre";}if(mes1==12){NombreMes="Diciembre";}					
			if(mes1==13){mes1=1;anio1=anio1+1;NombreMes="Enero";}
			nDias1=diasMes1[mes1];
			out.print("<table border>");out.print("<tr><th colspan=7 align=center>"+NombreMes+" Del "+anio1); 		
			/*************************************************/
			out.print("<tr>");out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");out.print("</tr>");out.print("<tr></tr>");
			columna1=0;
			//mes1=mes1+1;
			for(e=0;e<DiaSemanaEmpiezaMes;e++) /*Bucle para la reserva de espacios de los días en el calendario*/
			{out.print("<td align=center><font size+=4>.");columna1++;out.print("</font>");
			}
			int a1=0;
			for(e=1;e<=nDias1;e++) /*Bucle para la inclusión del número de día en el calendario*/
			{	a1=a1+1;out.print("<td align=center>");
			out.print(e);
			//out.print("<input name='rbFecha' type='Radio' id='rbFecha' value="+anio1+"-"+mes1+"-"+e+" />");
			out.print("<input name='chkHorarioMedico' type='CheckBox' id='chkHorarioMedico' value="+anio1+"-"+mes1+"-"+e+" />");
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
			/****************************************/
			
			out.print("<input name='txtContadorFecha' type='hidden' id='txtContadorFecha' value="+nDias1+" /></tr></table>");					
			out.print("<table><tr><td>Seleccione Las Horas </td></tr></table>");
			/********************************************************/
			/**
			 * contenido de los rangos de horas
			 */
			/*************nuevo *************/
			String horaI="";String minutoI="";String JornadaI="";String horaF="";
			String minutoF="";String JornadaF="";String Lapso="";
			String p1="";String p2="";String p3="";String p1f="";String p2f="";String p3f="";
			
			int i;int contador = 0;	int contadorChk = 0;		
			rs1=mp.BuscarParametros();
			 try {
				while(rs1.next()){
					horaI=rs1.getString(2).substring(0,2);minutoI=rs1.getString(2).substring(3,5);
					JornadaI=rs1.getString(2).substring(6,8);horaF=rs1.getString(3).substring(0,2);
					minutoF=rs1.getString(3).substring(3,5);JornadaF=rs1.getString(3).substring(6,8);
					Lapso=rs1.getString(4);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}			
			 p1 =horaI;p2 =minutoI;p3 =JornadaI;			
			 p1f=horaF;p2f=minutoF;p3f=JornadaF;
			/*******************fin nuevo****************************/
			 int hi=Integer.parseInt(p1);int mi=Integer.parseInt(p2);int lapso=Integer.parseInt(Lapso);
		    	int xyz=0;int hf=Integer.parseInt(p1f);int mf=Integer.parseInt(p2f);					
				//validar las horas
				int sw=0;int c=0;int cm=0;int compara=hi;
				while(sw==0){compara=compara+1;c=c+1;if(compara==hf){sw=1;}}
				if(mi<mf){cm=mf-mi;}if(mi>mf){cm=mf+mi;c=c-1;}
				int suma=(((c*60)+cm)/lapso);int xy=mi;	
				out.print("<table border='1' width='100%' ><tr>");
				for (i=0; i<suma; i++){
					contador=contador+1;
					contadorChk=contadorChk+1;
					if(contador==1){ 
						out.print("<td width='6%' ><input name='ChkHora' type='CheckBox' id='ChkHora' value="+p1+":"+p2+":"+p3+" />"+p1+":"+p2+" "+p3+"</td> ");
					}					
					else{
						xy=xy+lapso;
						//Validaciones de las horas
						int resta=xy/60;
						if(xy>59){xy=(xy-(60*resta)); xyz=Integer.parseInt(p1)+resta; p1=String.valueOf(xyz);} //si sobrepasa una hora
						if((p1.equals("13"))&&(p3.equals("AM"))){p1="1"; p3="PM";}
						if((p1.equals("13"))&&(p3.equals("PM"))){p1="1"; p3="AM";}
						if (contadorChk==10){
							out.print("<tr><td></td></tr>");
							contadorChk=0;
						}
						//if(p3.equals("AM")){						
							if(xy==0){						        	 
								out.print("<td width='6%'><input name='ChkHora' type='CheckBox' id='ChkHora' value="+p1+":00:"+p3+" />"+p1+":00:"+p3+"</td> ");
							}else{
								out.print("<td width='6%' ><input name='ChkHora' type='CheckBox' id='ChkHora' value="+p1+":"+xy+":"+p3+" />"+p1+":"+xy+":"+p3+" </td>");
							}
						//}
					/*	out.print("<table width='100%' border='1'><tr>");
						if(p3.equals("PM")){
							if(xy==0){
								out.print("<td><input name='ChkHora' type='CheckBox' id='ChkHora' value="+p1+":00:"+p3+" />"+p1+":00:"+p3+"</td>");
							}else{
								out.print("<td><input name='ChkHora' type='CheckBox' id='ChkHora' value="+p1+":"+xy+":"+p3+" />"+p1+":"+xy+":"+p3+"</td> ");
							}
						}*/
					}
				}
				out.print("</tr></table>");
				/**********************************************/	
			out.print("<table border='0'><tr><td><input name='txtContadorHora' type='hidden' id='txtContadorHora' value="+contador+" /><input name='btnBuscarPac' type='button' id='btnBuscarPac' value='Asignar Horario' onclick='Chkdato()' /></td></tr></table>");
		}
		
		
		
	}
}

