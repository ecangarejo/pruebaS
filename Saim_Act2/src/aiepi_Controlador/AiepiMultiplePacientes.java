/**
 * controlador: AiepiMultiplePacientes se encuentra la forma en que se 
 * buscan varios pacientes al tiempo y se les asigna su menu en forma de pestañas
 * y su menu de opciones.
 *  */
package aiepi_Controlador;

import aiepi_metodo.MetodoAiepiMultiplePacientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlMultiplePacientes
 */
public class AiepiMultiplePacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoAiepiMultiplePacientes mmp=new MetodoAiepiMultiplePacientes();		
		int accion;	     
		accion = Integer.parseInt(req.getParameter("txtAccion"));
		
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

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		String CodPac = req.getParameter("CodPac");
		String usuario=req.getParameter("usuario");

		
		MetodoAiepiMultiplePacientes mmp=new MetodoAiepiMultiplePacientes();
		
		res.setContentType("text/html;charset=UTF-8");
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int dd=Integer.parseInt(dia);
		if(dd<10){dia="0"+dd;}
		if(m<10){mes="0"+m;}
		String fechacjmysql=annio+"-"+mes+"-"+dia; //para la base de dtaos
		//String fechacj=dia+"/"+mes+"/"+annio;
		
		
		
		Calendar calendario = Calendar.getInstance();
		//Calendar calendario = new GregorianCalendar();
			int horas, minutos, segundos;
			horas =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= horas+":"+minutos+":"+segundos;
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
		ResultSet rs10=null;
		ResultSet rs11=null;
		ResultSet rs12=null;
		
		if(va.equals("2")){
			String CodAdm="";
			String edadMeses="";
			
			rs9=mmp.VerificarAdmision(CodPac);
			try {
				if(rs9.next()){
					CodAdm=rs9.getString("adm_numero_ingreso");
					edadMeses=rs9.getString("EdadMeses");
				int a;	     				
				a = Integer.parseInt(edadMeses);
				
				rs10=mmp.PreguntarAdmisionCeroDosR(CodAdm);
				if (rs10.next()) {
					rs4=mmp.VerificarDatosAdmision(CodPac,CodAdm);
					if(rs4.next()){
						out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</div></td><td width='15%' style='color:#215b8b'>Identificacion:&nbsp;&nbsp;"+rs4.getString(6)+" "+rs4.getString(7)+"</td><input name='CedPac' type='hidden' id='CedPac' value='"+rs4.getString(7)+"' /></td><td width='15%' style='color:#215b8b'>Fecha Nacimiento:&nbsp;&nbsp;"+rs4.getString(5)+"</td><td style='color:#215b8b'>Edad:&nbsp;&nbsp;&nbsp;&nbsp; Días:"+rs4.getString("EdadDias")+"&nbsp;&nbsp;&nbsp;Meses:"+rs4.getString("EdadMeses")+"&nbsp;&nbsp;&nbsp;Años:"+rs4.getString("edad")+"</td></tr></table>");
						out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='5%' style='color:#215b8b'>Entidad:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(11)+"</div></td><td width='35%' style='color:#215b8b'><b>Medico Tratante:  "+rs4.getString(17)+"</b></td><td width='10%' style='color:#215b8b'>Servicio y Ubicacion:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(9)+" Cama "+rs4.getString(10)+"</div><input name='txtCodCama' type='hidden' id='txtCodCama' value="+rs4.getString(12)+"  /> <input name='txtCodSubarea' type='hidden' id='txtCodSubarea' value="+rs4.getString(14)+"  /> <input name='txtCodArea' type='hidden' id='txtCodArea' value="+rs4.getString(13)+"  /><input name='txtCodEntidad' type='hidden' id='txtCodEntidad' value="+rs4.getString(15)+"  /></td></tr></table>");
						out.print("<br>");			
						/**************menu de la izq*************/
						out.print("<table width='100%' border='1' cellspacing='0'><tr><td width='15%'><div id='MenuVertical'> <div id='button'><ul>");
						rs8=mmp.VerificarPermisosHC(usuario);
						if(rs8.next()){
							rs7=mmp.VerificarPermisosHC(usuario);
							while(rs7.next()){
								
								String genero="";
								rs=mmp.BuscarGeneroPaciente(CodPac);
								if (rs.next()) {
									genero=rs.getString(1);
								}
								rs.getStatement().getConnection().close();
								
								/*Condicion de Aiepi Embarazadas Por Genero*/
								if (genero.equals("Femenino")) {			
								
								
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
									out.print("<li><a href='#' onclick='Enfermeria("+CodAdm+",&quot;"+fechaK+"&quot;)'>Enfermeria</a></li>");
								}
								
								/**Habilitar para seguir trabajando(Se deshabilita para war)**/
								/*if(rs7.getString(1).equals("13")){
									//Permiso de Mostrar Aiepi Embarazadas
									out.print("<li><a href='#' onclick='MostrarAiepiEmbarazadas()'>Aiepi Embarazadas</a></li>");
								}*/
								
								if(rs7.getString(1).equals("14")){
									//Permiso de Consultar Aiepi
									out.print("<li><a href='#' onclick='ConsultarAiepiGeneral()'>Consultar Aiepi</a></li>");
								}
								
								} else {
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
										out.print("<li><a href='#' onclick='Enfermeria("+CodAdm+",&quot;"+fechaK+"&quot;)'>Enfermeria</a></li>");
									}
									
									/*if(rs7.getString(1).equals("13")){
										//Permiso de Mostrar Aiepi Embarazadas
										out.print("<li><a href='#' onclick='MostrarAiepiEmbarazadas()'>Aiepi Embarazadas</a></li>");
									}*/
									
									if(rs7.getString(1).equals("14")){
										//Permiso de Consultar Aiepi
										out.print("<li><a href='#' onclick='ConsultarAiepiGeneral()'>Consultar Aiepi</a></li>");
									}
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
						out.print("<tr class='style6'><td width='24%'><div align='center'>Alergias</div></td><td width='27%'><div align='center'>Medicamentos</div></td><td width='24%'><div align='center'>Laboratorios</div></td><td width='25%'><div align='center'>Imagenologia</div></td></tr>");
						out.print("<tr><td bordercolor='#FF0000' ><div id='ResumenAlergias'><table width='100%' border='1' >");
						
						//***************ALERGIAS PRINCIPALES****************************/
						rs=mmp.VerificarAlergiasLimite(CodPac);
						while(rs.next()){
						out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
						}
						out.print("<tr><td align='center' ><a href='#' onclick='Antecedentes()' >Mas Informacion Antecedentes</a></td></tr>");
						rs.getStatement().getConnection().close();
						//************FIN ALERGIAS PRINCIPALES***************************/
						
						out.print("</table></div></td><td bordercolor='#0033FF' ><div id='ResumenMedicamentos'><table width='100%' border='1' >");
						
						/***************MEDICAMENTOS************************************/
						rs5=mmp.VerificarMedicamentosLimite(CodPac);
						while(rs5.next()){
							out.print("<tr><td>"+rs5.getString(1)+"</td></tr>");
						}
						rs5.getStatement().getConnection().close();
						out.print("<tr><td>Ultimos Medicamentos >> <a href='#' onclick='MostrarMedicamentos()' >Mas Info</a></td></tr>");
						//*************FIN MEDICAMENTOS**********************************/
						
						out.print("</table></div></td><td bordercolor='#009900' ><div id='ResumenLaboratorios'><table width='100%' border='1' >");
						
						/**************ULTIMOS 5 LABORATORIOS****************************/
						rs6=mmp.VerificarLaboratoriosLimite(CodPac);
						while(rs6.next()){
							String Tipo=rs6.getString(10);
							String FechaIni=rs6.getString(1);
							String HoraIni=rs6.getString(2);
							String diaa,mess,anioo=null; 
							String horass,minutoss,segundoss=null;
							  
							diaa=FechaIni.substring(8,10);
							mess=FechaIni.substring(5,7);
							anioo=FechaIni.substring(0,4);
							
							horass=HoraIni.substring(0,2);
							minutoss =HoraIni.substring(3,5);
							segundoss=HoraIni.substring(6,8);
							String codgenero="1";
							if(Tipo.equals("1")){
								//**Laboratorios Tipo Texto**/
								out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+1+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
							}
							
							if(Tipo.equals("2")){
								/**Laboratorios Tipo Rango**/
								out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+2+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+","+rs6.getString(8)+","+rs6.getString(9)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
							}
							
							if(Tipo.equals("3")){
								/**Laboratorios En Grupo**/
								out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs6.getString(4)+","+rs6.getString(3)+","+codgenero+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
							}
						}
						rs6.getStatement().getConnection().close();
						out.print("<tr><td>Ultimos Laboratorios >> <a href='#' onclick='MenuLaboratorio()' >Mas Info</a> </td></tr>");
						//***********FIN ULTIMOS LABORATORIOS***************************/
						
						out.print("</table></div></td><td bordercolor='#CC6600'><div id='ResumenImagenologia'><table width='100%' border='1' >");
						
						/**********ULTIMOS 5 IMAGENES***********************************/
						ResultSet rsImgEco=null;
						ResultSet rsImgRmc=null;
						rs1=mmp.VerificarImagenesLimite(CodPac);
						rsImgEco=mmp.VerificarEcoLimite(CodPac);
						rsImgRmc=mmp.VerificarRmcLimite(CodPac);
						while(rs1.next()){
							out.print("<tr><td><a  href='#'onclick='mostarexamenes("+rs1.getString(4)+","+rs1.getString(5)+")'>"+rs1.getString(3)+"</a></td></tr>");
						}
						rs1.getStatement().getConnection().close();
						
						while(rsImgEco.next()){
							out.print("<tr><td><a  href='#'onclick='mostrarInformesCardiologia("+rsImgEco.getString(4)+")'>"+rsImgEco.getString(3)+"</a></td></tr>");
						}
						rsImgEco.getStatement().getConnection().close();
						
						while(rsImgRmc.next()){
							out.print("<tr><td><a  href='#'onclick='mostrarInformesRmc("+rsImgRmc.getString(4)+")'>"+rsImgRmc.getString(3)+"</a></td></tr>");
						}
						rsImgRmc.getStatement().getConnection().close();
						
						out.print("<tr><td align='center' >Ultimas Imagenes >> <a href='#' onclick='MostrarImagenologia()' >Mas Info</a></td></tr>");
						//rs1.getStatement().getConnection().close(); *Aqui estaba inicialmente*
						//**********FIN ULTIMOS 5 IMAGENES*******************************/
						
						out.print("</table></div></td></tr><tr class='style6' ><td><div align='center'>Formatos Activos</div></td><td><div align='center'>Ordenes Medicas</div></td><td colspan='2'><div align='center'>Imagenes Diagnosticas Pendientes</div> </td></tr><tr><td bordercolor='#FFFF00' ><div id='ResumenFormatosActivos'><table width='100%' border='1' >");
						
						/*************ULTIMOS 5 FORMATOS REALIZADOS********************/
						rs3=mmp.VerificarFormatosLimite(CodAdm, CodPac);
						while(rs3.next()){
							String FechaIni=rs3.getString(3);
							String HoraIni=rs3.getString(4);
							String diaa,mess,anioo=null; 
							String horass,minutoss,segundoss=null;
							  
							diaa=FechaIni.substring(8,10);
							mess=FechaIni.substring(5,7);
							anioo=FechaIni.substring(0,4);
							
							horass=HoraIni.substring(0,2);
							minutoss =HoraIni.substring(3,5);
							segundoss=HoraIni.substring(6,8);
							out.print("<tr><td><a  href='#' onclick='ImprimirFormato("+rs3.getString(7)+","+rs3.getString(1)+","+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs3.getString(8)+","+rs3.getString(11)+","+CodAdm+","+rs3.getString(10)+")'>"+rs3.getString(2)+"</a></td></tr>");
						}
						rs3.getStatement().getConnection().close();
						out.print("<tr><td align='center'><a href='#' onclick='Formatos()' >Mas Informacion Formatos</a></td></tr>");
						/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
						
						out.print("</table></div></td><td bordercolor='#000000' ><div id='resumenOrdenesMedicas'><table width='100%' border='1' >");
						
						/**************ULTIMAS 5 ORDENES MEDICAS*******************/
						rs2=mmp.VerificarOrdenesMedicasLimite(CodAdm);
						while(rs2.next()){
							
							String Tipo=rs2.getString(5);
							if(Tipo.equals("1")){
								/**1=LABORATORIO**/
								out.print("<tr><td><a href='#' onclick='ReportLabora("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>LABORATORIO</td></tr>");
							}
							
							if(Tipo.equals("2")){
								/**2=IMAGENOLOGIA**/
								out.print("<tr><td><a href='#' onclick='ReportImage("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>IMAGENOLOGIA</td></tr>");
							}
							
							if(Tipo.equals("3")){
								/**3=MEDICAMENTOS**/
								out.print("<tr><td><a href='#' onclick='ReportMedica("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>MEDICAMENTOS e INSUMOS</td></tr>");
							}
							
							if(Tipo.equals("4")){
								/**4=GENERAL**/
								out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>ORDEN GENERAL</td></tr>");
								
							}
						}
						out.print("<tr><td  align='center' ><a href='#' onclick='OrdenServicio()' >Mas Informacion Ordenes Medicas</a></td></tr>");
						rs2.getStatement().getConnection().close();
						/**********FIN ULTIMAS 5 ORDENES MEDICAS******************/
						
						
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
						/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
						//fgj
						out.print("</div></td>");
						
						
						out.print("</table></div></td></tr></table>");
						out.print("");
						out.print("");
						out.print("");
						out.print("");
						
						/**********FIN SNAPSHOT*******************/
						
						
					}
					rs4.getStatement().getConnection().close();
				}/*Cierra PreguntarAdmisionCeroDos*/ else {
					rs10=mmp.PreguntarAdmisionDosCincoR(CodAdm);
					if (rs10.next()) {
						rs4=mmp.VerificarDatosAdmision(CodPac,CodAdm);
						if(rs4.next()){
							//out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</div></td><td width='9%' style='color:#215b8b'>Identificacion</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(6)+" "+rs4.getString(7)+"</div><input name='CedPac' type='hidden' id='CedPac' value='"+rs4.getString(7)+"' /></td><td width='12%' style='color:#215b8b'>Fecha Nacimiento</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(5)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Edad:"+rs4.getString(4)+"</div></td></tr></table>");
							out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</div></td><td width='15%' style='color:#215b8b'>Identificacion:&nbsp;&nbsp;"+rs4.getString(6)+" "+rs4.getString(7)+"</td><input name='CedPac' type='hidden' id='CedPac' value='"+rs4.getString(7)+"' /></td><td width='15%' style='color:#215b8b'>Fecha Nacimiento:&nbsp;&nbsp;"+rs4.getString(5)+"</td><td style='color:#215b8b'>Edad:&nbsp;&nbsp;&nbsp;&nbsp; Días:"+rs4.getString("EdadDias")+"&nbsp;&nbsp;&nbsp;Meses:"+rs4.getString("EdadMeses")+"&nbsp;&nbsp;&nbsp;Años:"+rs4.getString("edad")+"</td></tr></table>");
							out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='5%' style='color:#215b8b'>Entidad:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(11)+"</div></td><td width='35%' style='color:#215b8b'><b>Medico Tratante:  "+rs4.getString(17)+"</b></td><td width='10%' style='color:#215b8b'>Servicio y Ubicacion:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(9)+" Cama "+rs4.getString(10)+"</div><input name='txtCodCama' type='hidden' id='txtCodCama' value="+rs4.getString(12)+"  /> <input name='txtCodSubarea' type='hidden' id='txtCodSubarea' value="+rs4.getString(14)+"  /> <input name='txtCodArea' type='hidden' id='txtCodArea' value="+rs4.getString(13)+"  /><input name='txtCodEntidad' type='hidden' id='txtCodEntidad' value="+rs4.getString(15)+"  /></td></tr></table>");						
							out.print("<br>");			
							/**************menu de la izq*************/
							out.print("<table width='100%' border='1' cellspacing='0'><tr><td width='15%'><div id='MenuVertical'> <div id='button'><ul>");
							rs8=mmp.VerificarPermisosHC(usuario);
							if(rs8.next()){
								rs7=mmp.VerificarPermisosHC(usuario);
								while(rs7.next()){
									
									String genero="";
									rs=mmp.BuscarGeneroPaciente(CodPac);
									if (rs.next()) {
										genero=rs.getString(1);
									}
									rs.getStatement().getConnection().close();
									
									/*Condicion de Aiepi Embarazadas Por Genero*/
									if (genero.equals("Femenino")) {				
									
									
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
										out.print("<li><a href='#' onclick='Enfermeria("+CodAdm+",&quot;"+fechaK+"&quot;)'>Enfermeria</a></li>");
									}
									
									/**Habilitar para seguir trabajando(Se deshabilita para war)**/
									/*if(rs7.getString(1).equals("13")){
										//Permiso de Mostrar Aiepi Embarazadas
										out.print("<li><a href='#' onclick='MostrarAiepiEmbarazadas()'>Aiepi Embarazadas</a></li>");
									}*/
									
									if(rs7.getString(1).equals("14")){
										//Permiso de Consultar Aiepi
										out.print("<li><a href='#' onclick='ConsultarAiepiGeneral()'>Consultar Aiepi</a></li>");
									}
									
									} else {
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
											out.print("<li><a href='#' onclick='Enfermeria("+CodAdm+",&quot;"+fechaK+"&quot;)'>Enfermeria</a></li>");
										}
										
										/*if(rs7.getString(1).equals("13")){
											//Permiso de Mostrar Aiepi Embarazadas
											out.print("<li><a href='#' onclick='MostrarAiepiEmbarazadas()'>Aiepi Embarazadas</a></li>");
										}*/
										
										if(rs7.getString(1).equals("14")){
											//Permiso de Consultar Aiepi
											out.print("<li><a href='#' onclick='ConsultarAiepiGeneral()'>Consultar Aiepi</a></li>");
										}
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
							out.print("<tr class='style6'><td width='24%'><div align='center'>Alergias</div></td><td width='27%'><div align='center'>Medicamentos</div></td><td width='24%'><div align='center'>Laboratorios</div></td><td width='25%'><div align='center'>Imagenologia</div></td></tr>");
							out.print("<tr><td bordercolor='#FF0000' ><div id='ResumenAlergias'><table width='100%' border='1' >");
							
							//***************ALERGIAS PRINCIPALES****************************/
							rs=mmp.VerificarAlergiasLimite(CodPac);
							while(rs.next()){
							out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
							}
							out.print("<tr><td align='center' ><a href='#' onclick='Antecedentes()' >Mas Informacion Antecedentes</a></td></tr>");
							rs.getStatement().getConnection().close();
							//************FIN ALERGIAS PRINCIPALES***************************/
							
							out.print("</table></div></td><td bordercolor='#0033FF' ><div id='ResumenMedicamentos'><table width='100%' border='1' >");
							
							/***************MEDICAMENTOS************************************/
							rs5=mmp.VerificarMedicamentosLimite(CodPac);
							while(rs5.next()){
								out.print("<tr><td>"+rs5.getString(1)+"</td></tr>");
							}
							rs5.getStatement().getConnection().close();
							out.print("<tr><td>Ultimos Medicamentos >> <a href='#' onclick='MostrarMedicamentos()' >Mas Info</a></td></tr>");
							//*************FIN MEDICAMENTOS**********************************/
							
							out.print("</table></div></td><td bordercolor='#009900' ><div id='ResumenLaboratorios'><table width='100%' border='1' >");
							
							/**************ULTIMOS 5 LABORATORIOS****************************/
							rs6=mmp.VerificarLaboratoriosLimite(CodPac);
							while(rs6.next()){
								String Tipo=rs6.getString(10);
								String FechaIni=rs6.getString(1);
								String HoraIni=rs6.getString(2);
								String diaa,mess,anioo=null; 
								String horass,minutoss,segundoss=null;
								  
								diaa=FechaIni.substring(8,10);
								mess=FechaIni.substring(5,7);
								anioo=FechaIni.substring(0,4);
								
								horass=HoraIni.substring(0,2);
								minutoss =HoraIni.substring(3,5);
								segundoss=HoraIni.substring(6,8);
								String codgenero="1";
								if(Tipo.equals("1")){
									//**Laboratorios Tipo Texto**/
									out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+1+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
								}
								
								if(Tipo.equals("2")){
									/**Laboratorios Tipo Rango**/
									out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+2+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+","+rs6.getString(8)+","+rs6.getString(9)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
								}
								
								if(Tipo.equals("3")){
									/**Laboratorios En Grupo**/
									out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs6.getString(4)+","+rs6.getString(3)+","+codgenero+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
								}
							}
							rs6.getStatement().getConnection().close();
							out.print("<tr><td>Ultimos Laboratorios >> <a href='#' onclick='MenuLaboratorio()' >Mas Info</a> </td></tr>");
							//***********FIN ULTIMOS LABORATORIOS***************************/
							
							out.print("</table></div></td><td bordercolor='#CC6600'><div id='ResumenImagenologia'><table width='100%' border='1' >");
							
							/**********ULTIMOS 5 IMAGENES***********************************/
							ResultSet rsImgEco=null;
							ResultSet rsImgRmc=null;
							rs1=mmp.VerificarImagenesLimite(CodPac);
							rsImgEco=mmp.VerificarEcoLimite(CodPac);
							rsImgRmc=mmp.VerificarRmcLimite(CodPac);
							while(rs1.next()){
								out.print("<tr><td><a  href='#'onclick='mostarexamenes("+rs1.getString(4)+","+rs1.getString(5)+")'>"+rs1.getString(3)+"</a></td></tr>");
							}
							rs1.getStatement().getConnection().close();
							
							while(rsImgEco.next()){
								out.print("<tr><td><a  href='#'onclick='mostrarInformesCardiologia("+rsImgEco.getString(4)+")'>"+rsImgEco.getString(3)+"</a></td></tr>");
							}
							rsImgEco.getStatement().getConnection().close();
							
							while(rsImgRmc.next()){
								out.print("<tr><td><a  href='#'onclick='mostrarInformesRmc("+rsImgRmc.getString(4)+")'>"+rsImgRmc.getString(3)+"</a></td></tr>");
							}
							rsImgRmc.getStatement().getConnection().close();
							
							out.print("<tr><td align='center' >Ultimas Imagenes >> <a href='#' onclick='MostrarImagenologia()' >Mas Info</a></td></tr>");
							//rs1.getStatement().getConnection().close(); *Aqui estaba inicialmente*
							//**********FIN ULTIMOS 5 IMAGENES*******************************/
							
							out.print("</table></div></td></tr><tr class='style6' ><td><div align='center'>Formatos Activos</div></td><td><div align='center'>Ordenes Medicas</div></td><td colspan='2'><div align='center'>Imagenes Diagnosticas Pendientes</div> </td></tr><tr><td bordercolor='#FFFF00' ><div id='ResumenFormatosActivos'><table width='100%' border='1' >");
							
							/*************ULTIMOS 5 FORMATOS REALIZADOS********************/
							rs3=mmp.VerificarFormatosLimite(CodAdm, CodPac);
							while(rs3.next()){
								String FechaIni=rs3.getString(3);
								String HoraIni=rs3.getString(4);
								String diaa,mess,anioo=null; 
								String horass,minutoss,segundoss=null;
								  
								diaa=FechaIni.substring(8,10);
								mess=FechaIni.substring(5,7);
								anioo=FechaIni.substring(0,4);
								
								horass=HoraIni.substring(0,2);
								minutoss =HoraIni.substring(3,5);
								segundoss=HoraIni.substring(6,8);
								out.print("<tr><td><a  href='#' onclick='ImprimirFormato("+rs3.getString(7)+","+rs3.getString(1)+","+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs3.getString(8)+","+rs3.getString(11)+","+CodAdm+","+rs3.getString(10)+")'>"+rs3.getString(2)+"</a></td></tr>");
							}
							rs3.getStatement().getConnection().close();
							out.print("<tr><td align='center'><a href='#' onclick='Formatos()' >Mas Informacion Formatos</a></td></tr>");
							/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
							
							out.print("</table></div></td><td bordercolor='#000000' ><div id='resumenOrdenesMedicas'><table width='100%' border='1' >");
							
							/**************ULTIMAS 5 ORDENES MEDICAS*******************/
							rs2=mmp.VerificarOrdenesMedicasLimite(CodAdm);
							while(rs2.next()){
								
								String Tipo=rs2.getString(5);
								if(Tipo.equals("1")){
									/**1=LABORATORIO**/
									out.print("<tr><td><a href='#' onclick='ReportLabora("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>LABORATORIO</td></tr>");
								}
								
								if(Tipo.equals("2")){
									/**2=IMAGENOLOGIA**/
									out.print("<tr><td><a href='#' onclick='ReportImage("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>IMAGENOLOGIA</td></tr>");
								}
								
								if(Tipo.equals("3")){
									/**3=MEDICAMENTOS**/
									out.print("<tr><td><a href='#' onclick='ReportMedica("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>MEDICAMENTOS e INSUMOS</td></tr>");
								}
								
								if(Tipo.equals("4")){
									/**4=GENERAL**/
									out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>ORDEN GENERAL</td></tr>");
									
								}
							}
							out.print("<tr><td  align='center' ><a href='#' onclick='OrdenServicio()' >Mas Informacion Ordenes Medicas</a></td></tr>");
							rs2.getStatement().getConnection().close();
							/**********FIN ULTIMAS 5 ORDENES MEDICAS******************/
							
							
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
							/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
							//fgj
							out.print("</div></td>");
							
							
							out.print("</table></div></td></tr></table>");
							out.print("");
							out.print("");
							out.print("");
							out.print("");
							
							/**********FIN SNAPSHOT*******************/
							
							
								}
								rs4.getStatement().getConnection().close();
					}else{

String codigoPrincipalCIECeroDos="";
String codigoCIECeroDos=req.getParameter("DxAiepiCeroDos");
rs12=mmp.ConsultarCodigoPrincipalCIE(codigoCIECeroDos);
	if (rs12.next()) {
		codigoPrincipalCIECeroDos=rs12.getString(1);
	}
	rs12.getStatement().getConnection().close();
						
String codigoPrincipalCIE="";
String codigoCIE=req.getParameter("DxAiepiDosCinco");
rs12=mmp.ConsultarCodigoPrincipalCIE(codigoCIE);
	if (rs12.next()) {
		codigoPrincipalCIE=rs12.getString(1);
	}
	rs12.getStatement().getConnection().close();
	
					if(a <= 1){
						out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><td align='center' bgcolor='#104e8b' style='color:#FFFFFF'><b>ATENCION INTEGRADA AL LACTANTE MENOS DE DOS MESES</b></td>" +
								"</tr></table>");
						out.print("<table width='80%'><tr><td><b>INSTITUCION:</b></td><td>"+rs9.getString("Entidad")+"</td><td></td><td><b>MUNICIPIO:</b></td><td>"+rs9.getString("Municipio")+"</td><td></td><td><b>Nº HISTORIA CLINICA:</b></td><td>"+rs9.getString("adm_numero_ingreso")+"</td></tr>" +
								"<tr><td><b>NOMBRE:</b></td><td>"+rs9.getString("NombreCompleto")+"</td><td></td><td><b>EDAD:</b></td><td>"+rs9.getString("EdadDias")+" dias</td><td></td><td><input type='hidden' name='txtCodReporte' id='txtCodReporte'/></td></tr>" +
								"<tr><td><b>FECHA NACIMIENTO:</b></td><td>"+rs9.getString("fecha_nacimiento")+"</td><td></td><td><b>SEXO:</b></td><td>"+rs9.getString("genero")+"</td><td><input name='CodAdmision' type='hidden' id='CodAdmision' value='"+CodAdm+"' /></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>DATOS ACOMPAÑANTE</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>"+rs9.getString("responsable")+"</td></tr></table>");
						out.print("<table width='100%'><tr><br><td><b>MOTIVO DE CONSULTA Y ENFERMEDAD ACTUAL:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='mot_consulta' id='mot_consulta'></textarea></td></tr>" +
								"<tr><td><b>ANTECEDENTE DE EMBARAZO PARTO DE IMPORTANCIA:</b>" +
								"<tr><td><textarea rows='4' cols='135' name='ant_embarazo' id='ant_embarazo'></textarea></td></tr></table>" +
								"<table width='80%'><tr><td><b>PESO AL NACER:</b></td><td><INPUT type='text' size='1' name='peso_nacer' id='peso_nacer' onkeypress='javascript:return validarNro(event)'/> gr.</td><td><b>TALLA AL NACER:</b></td><td><INPUT type='text' size='1' name='talla_nacer' id='talla_nacer' onkeypress='javascript:return validarNro(event)'/> cm</td>" +
								"<td><b>EDAD GESTACIONAL:</b></td><td><INPUT type='text' size='1' name='edad_gestacional' id='edad_gestacional' onkeypress='javascript:return validarNro(event)'/> Semanas.</td><td><b>HEMOCLASIFICACION:<b></td><td><INPUT type='text' size='1' name='hemoclasificacion' id='hemoclasificacion' onkeypress='javascript:return validarNro(event)'/></td></tr>" +
								"<tr><td><b>PESO: actual</b></td><td><INPUT type='text' size='1' name='peso_actual' id='peso_actual' onkeypress='javascript:return validarNro(event)'/> gr.</td><td><b>TALLA:</b></td><td><INPUT type='text' size='1' name='talla' id='talla' onkeypress='javascript:return validarNro(event)'/> cm</td>" +
								"<td><b>PC:</b></td><td><INPUT type='text' size='1' name='perimetro_cefalico' id='perimetro_cefalico' onkeypress='javascript:return validarNro(event)'/> cm</td><td><b>FC:</b></td><td><INPUT type='text' size='1' name='frecuencia_cardiaca' id='frecuencia_cardiaca' onkeypress='javascript:return validarNro(event)'/> /min</td></tr>" +
								"<tr><td><b>FR:</b></td><td><INPUT type='text' size='1' name='frecuencia_respiratoria' id='frecuencia_respiratoria' onkeypress='javascript:return validarNro(event)'/> /min</td><td><b>Tº:</b></td><td><INPUT type='text' size='1' name='temperatura' id='temperatura' onkeypress='javascript:return validarNro(event)'/></td>" +
								"<td><b>Cº:</b></td><td><INPUT type='text' size='1' name='centigrados' id='centigrados' onkeypress='javascript:return validarNro(event)'/></td></tr></table>");
						out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td align='center' bgcolor='#104e8b' style='color:#FFFFFF'><b>EVALUAR Y CLASIFICAR AL LACTANTE MENOR DE 2 MESES DE EDAD</b></td></tr></table>");
	/*Ver Enf Grave*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI TIENE UNA ENFERMEDAD MUY GRAVE O INFECCION LOCAL</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>¿Puede beber o tomar el pecho?</td><td>Si<INPUT type='radio' name='rad_bebe_pecho' id='si'>No<INPUT type='radio' name='rad_bebe_pecho' id ='no'></td>" +
								"<td>¿Ha tenido fiebre?</td><td>Si<INPUT type='radio' name='rad_fiebre' id='si'>No<INPUT type='radio' name='rad_fiebre' id='no'></td>" +
								"<td>¿Ha tenido hipotermia?</td><td>Si<INPUT type='radio' name='rad_hipotermia' id='si'>No<INPUT type='radio' name='rad_hipotermia' id='no'></td>" +
								"<td>¿Ha tenido convulsiones?</td><td>Si<INPUT type='radio' name='rad_convulsiones' id='si'>No<INPUT type='radio' name='rad_convulsiones' id='no'></td></tr>" +
								"<tr><td>¿Ha tenido vomito?</td><td>Si<INPUT type='radio' name='rad_vomito' id='si' onclick='vomita_todo.disabled=false;vomita_todo.focus()'>No<INPUT type='radio' name='rad_vomito' id='no' onclick='vomita_todo.disabled=true'></td>" +
								"<td>¿Vomita todo?</td><td><INPUT type='text' name='vomita_todo' id='vomita_todo' disabled></td>" +
								"<td>¿Tiene el niño diarrea?</td><td>Si<INPUT type='radio' name='rad_diarrea' id='si' onclick='cuando_diarrea.disabled=false;cuando_diarrea.focus()'>No<INPUT type='radio' name='rad_diarrea' id='no' onclick='cuando_diarrea.disabled=true'></td>" +
								"<td>¿Desde cuando?</td><td><INPUT type='text' size='1' name='cuando_diarrea' id='cuando_diarrea' disabled onkeypress='javascript:return validarNro(event)'> dias</td></tr>" +
								"<tr><td>¿Tiene dificultad para respirar?</td><td>Si<INPUT type='radio' name='rad_dificultad_respirar' id='si' onclick='exp_dificultad_respirar.disabled=false;exp_dificultad_respirar.focus()'>No<INPUT type='radio' name='rad_dificultad_respirar' id='no' onclick='exp_dificultad_respirar.disabled=true'></td><td>Explique:</td><td><INPUT type='text' name='exp_dificultad_respirar' id='exp_dificultad_respirar' disabled></td>" +
								"<td>¿Hay sangre en las heces?</td><td>Si<INPUT type='radio' name='rad_sangre_heces' id='si'>No<INPUT type='radio' name='rad_sangre_heces' id='no'></td>" +
								"<td>¿Cuantos pañales ha orinado en las Ultimas 24 horas?</td><td><INPUT type='text' name='panales_orinados' id='panales_orinados'></td></tr>" +
								//OBSERVAR AL NIÑO(A)
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
						
						out.print("<table width='100%'><tr><br><br><td><b>ENFERMEDAD GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='enfermedad_grave' id='si'></td><td></td><td><b>INFECCIÓN LOCAL</b>&nbsp;&nbsp;<input type='checkbox' name='infeccion_local' id='si'></td><td></td><td><b>NO TIENE ENFERMEDAD GRAVE NI INFECCIÓN LOCAL</b>&nbsp;&nbsp;<input type='checkbox' name='no_enf_grave_no_inf_local' id='si'></td>" +
								"<td><b>DESHIDRATACIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='deshidratacion' id='si'></td><td><b>NO DESHIDRATACIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='no_deshidratacion' id='si'></td><td><b>DIARREA PROLONGADA</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_prolongada' id='si'></td><td></td><td><b>DIARREA CON SANGRE</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_sangre' id='si'></td></tr></table>");
						
	/*Ver Alimentacion*/out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR EL CRECIMIENTO Y LAS PRACTICAS DE ALIMENTACION</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>¿Tiene alguna dificultad para alimentarse?</td><td>Si<INPUT type='radio' name='rad_dificultad_alimentarse' id='si' onclick='cual_dificultad_alimentarse.disabled=false;cual_dificultad_alimentarse.focus()'>No<INPUT type='radio' name='rad_dificultad_alimentarse' id='no' onclick='cual_dificultad_alimentarse.disabled=true'></td>" +
								"<td>¿Cual?</td><td><INPUT type='text' name='cual_dificultad_alimentarse' id='cual_dificultad_alimentarse' disabled></td>" +
								"<td>¿Ha dejado de comer?</td><td>Si<INPUT type='radio' name='rad_ha_dejado_comer' id='si' onclick='desde_cuando_ha_dejado_comer.disabled=false;desde_cuando_ha_dejado_comer.focus()'>No<INPUT type='radio' name='rad_ha_dejado_comer' id='no' onclick='desde_cuando_ha_dejado_comer.disabled=true'></td>" +
								"<td>¿Desde cuándo?</td><td><INPUT type='text' size='1' name='desde_cuando_ha_dejado_comer' id='desde_cuando_ha_dejado_comer' disabled onkeypress='javascript:return validarNro(event)'> días</td></tr>" +
								"<tr><td>¿Se alimenta con leche materna?</td><td>Si<INPUT type='radio' name='rad_leche_materna' id='si' onclick='forma_exclusiva.disabled=false;forma_exclusiva.focus();cuantas_veces_forma_exclusiva.disabled=false'>No<INPUT type='radio' name='rad_leche_materna' id='no' onclick='forma_exclusiva.disabled=true;cuantas_veces_forma_exclusiva.disabled=true'></td>" +
								"<td>¿La ofrece en forma exclusiva?</td><td><input type='text' name='forma_exclusiva' id='forma_exclusiva' disabled></td>" +
								"<td>¿Cuantas veces en 24 horas?</td><td><INPUT type='text' name='cuantas_veces_forma_exclusiva' id='cuantas_veces_forma_exclusiva' disabled></td>" +
								"<td>¿Utiliza chupo?</td><td>Si<INPUT type='radio' name='rad_utiliza_chupo' id='si'>No<INPUT type='radio' name='rad_utiliza_chupo' id='no'></td></tr>" +
								"<tr><td>¿Recibe otra leche, otro alimento o bebidas?</td><td>Si<INPUT type='radio' name='rad_otra_leche' id='si' onclick='cuales_frecuencia_otra_leche.disabled=false;cuales_frecuencia_otra_leche.focus();prepara_otra_leche.disabled=false;que_utiliza_alimentarlo.disabled=false'>No<INPUT type='radio' name='rad_otra_leche' id='no' onclick='cuales_frecuencia_otra_leche.disabled=true;prepara_otra_leche.disabled=true;que_utiliza_alimentarlo.disabled=true'></td>" +
								"<td>¿Cuales y con que frecuencia?</td><td><INPUT type='text' name='cuales_frecuencia_otra_leche' id='cuales_frecuencia_otra_leche' disabled></td>" +
								"<td>¿Como prepara la otra leche?</td><td><INPUT type='text' name='prepara_otra_leche' id='prepara_otra_leche' disabled></td>" +
								"<td>¿Que utiliza para alimentarlo?</td><td><INPUT type='text' name='que_utiliza_alimentarlo' id='que_utiliza_alimentarlo' disabled></td></tr>" +
								"<tr><td>PESO/EDAD</td><td><INPUT type='text' size='2.5' name='peso_edad' id='peso_edad' onkeypress='javascript:return validarNro(event)'> DE</td>" +
								"<td>PESO/TALLA</td><td><INPUT type='text' size='2.5' name='peso_talla' id='peso_talla' onkeypress='javascript:return validarNro(event)'> DE</td>" +
								"<td>Tendencia peso:</td><td><SELECT name='tendencia_peso' id='tendencia_peso'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Ascendente'>Ascendente</OPTION><OPTION value='Descendente'>Descendente</OPTION><OPTION value='Horizontal'>Horizontal</OPTION></SELECT></td></tr>" +
								
								"<tr><td><br><br><b>EVALUAR EL AGARRE:</b></td></tr>" +
								"<tr><td>Tiene la boca bien abierta</td><td><input type='checkbox' name='boca_abierta' id='si'></td>" +
								"<td>Toca el seno con el menton</td><td><input type='checkbox' name='toca_seno' id='si'></td>" +
								"<td>Labio inferior volteado hacia afuera</td><td><input type='checkbox' name='labio_inferior' id='si'></td>" +
								"<td>Se ve mas areola por encima del labio</td><td><input type='checkbox' name='areola_labio' id='si'></td></tr>" +
								"<tr><td><b>EVALUAR POSICION:</b></td></tr>" +
								"<tr><td>Cabeza y cuerpo del niño derechos</td><td><input type='checkbox' name='cab_cuerpo_derecho' id='si'></td>" +
								"<td>Direccion al pecho/nariz frente pezon</td><td><input type='checkbox' name='direccion_pezon' id='si'></td>" +
								"<td>Hijo frente madre:barriga con barriga</td><td><input type='checkbox' name='hijo_frente_madre' id='si'></td>" +
								"<td>Madre sostiene todo el cuerpo</td><td><input type='checkbox' name='madre_sostiene_cuerpo' id='si'></td></tr>" +
								"<tr><td><b>EVALUAR SUCCION:</b></td></tr>" +
								"<tr><td>Lenta y profunda con pausas</td><td><input type='checkbox' name='lenta_profunda' id='si'></td></tr></table>");
						
						out.print("<table width='100%'><tr><br><br><td><b>PROBLEMA SEVERO DE ALIMENTACIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='prob_severo_alimentacion' id='si'></td><td></td><td><b>PESO MUY BAJO</b>&nbsp;&nbsp;<input type='checkbox' name='peso_muy_bajo' id='si'></td>" +
								"<td></td><td><b>PROBLEMAS DE ALIMENTACIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='problemas_alimentacion' id='si'></td><td></td><td><b>PESO BAJO O RIESGO</b>&nbsp;&nbsp;<input type='checkbox' name='peso_bajo' id='si'></td>" +
								"<td></td><td><b>ADECUADAS PRÁCTICAS DE ALIMENTACIÓN Y PESO ADECUADO</b>&nbsp;&nbsp;<input type='checkbox' name='practicas_alimentacion' id='si'></td></tr></table>");
						
	/*Ver Desarrollo*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI EXISTE PROBLEMA EN EL DESARROLLO</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>¿Son parientes los padres?</td><td>Si<INPUT type='radio' name='rad_parientes_padres' id='si'>No<INPUT type='radio' name='rad_parientes_padres' id='no'></td>" +
								"<td>¿Hay un familiar con problema mental o fisico?</td><td>Si<INPUT type='radio' name='rad_familiar_problema_mental' id='si'>No<INPUT type='radio' name='rad_familiar_problema_mental' id='no'></td>" +
								"<td>¿Quien cuida al niño?</td><td><INPUT type='text' name='quien_cuida_nino' id='quien_cuida_nino'></td>" +
								"<td>¿Como ve el desarrollo del niño?</td><td><INPUT type='text' name='desarrollo_nino' id='desarrollo_nino'></td></tr></table>");
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
								"<td><INPUT type='text' readonly='' size='50' name='txtCodDiagnostico' id='txtCodDiagnostico'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' value='Ingresar' onclick='IngresarDxCeroDos()'></td> " +
								"</tr><tr><td></td><td><div id='SugeDiagnostico'></div></td><td></td><td></td></tr><tr><td colspan='4' id='dxAiepi'></td><td><input type='hidden' name='codPrincipalCIECeroDos' id='codPrincipalCIECeroDos' value='"+codigoPrincipalCIECeroDos+"' /></td></tr></table>");
			
						
						out.print("<table width='100%'><tr><td align='center'>TRATAR</td></tr>" +
								"<tr><td><textarea rows='5' cols='135' name='tratar_pac' id='tratar_pac'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><td>ESCRIBIR LAS RECOMENDACIONES Y ORIENTACIONES DADAS SOBRE:</td></tr>" +
								"<tr><td><b>1.CUANDO VOLVER DE INMEDIATO AL SERVICIO(Signos de Alarma):</b></td></tr>" +
								"<tr><td><textarea rows='3' cols='135' name='volver_inmediato' id='volver_inmediato'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>2.CUANDO VOLVER A CONSULTA DE CONTROL:</b></td>" +
								"<td>RECIEN NACIDO:</td><td><INPUT type='text' name='recien_nacido' id='recien_nacido'></td>" +
								"<td>MADRE:</td><td><INPUT type='text' name='madre' id='madre'></td>" +
								"<td><b>3.CUANDO VOLVER A CONSULTA DE NIÑO SANO:</b></td><td><INPUT type='text' name='nino_sano' id='nino_sano'></td>" +
								"<td><b>4.REFERIDO A CONSULTA DE:</b></td><td><INPUT type='text' name='referido_consulta' id='referido_consulta'></td></tr></table>");
						out.print("<table width=100%><tr><td><b>5.MEDIDAS PREVENTIVAS ESPECIFICAS:</b></td>" +
								"<td>-El 1º mes,Despertar si en 3 horas no ha comido</td>" +
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
								"<INPUT id='guardar' type='button' value='Guardar Informe' onclick='GuardarAiepiCeroDosMeses()' disabled>" +
								//"<INPUT id='anular' type='button' value='Anular Informe' onclick='AnularInforme()'>" +
								"</div></td></tr></table>");
						
	/*AIEPI 2-5*/	}else if((a >= 2) && (a <= 60)){
						out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><td align='center' bgcolor='#104e8b' style='color:#FFFFFF'><b>AIEPI ATENCION DEL NIÑO DE 2 MESES A 5 AÑOS</b></td></tr></table>");
						out.print("<table width='80%'><tr><td><b>INSTITUCION:</b></td><td>"+rs9.getString("Entidad")+"</td><td></td><td><b>MUNICIPIO:</b></td><td>"+rs9.getString("Municipio")+"</td><td></td><td><b>Nº HISTORIA CLINICA:</b></td><td>"+rs9.getString("adm_numero_ingreso")+"</td></tr>" +
								"<tr><td><b>NOMBRE:</b></td><td>"+rs9.getString("NombreCompleto")+"</td><td></td><td><b>EDAD:</b></td><td>"+rs9.getString("EdadAnos")+" años</td><td>"+rs9.getString("EdadMeses")+" meses</td><td><input type='hidden' name='CodAdmision' id='CodAdmision' value='"+CodAdm+"' /></td></tr>" +
								"<tr><td><b>FECHA NACIMIENTO:</b></td><td>"+rs9.getString("fecha_nacimiento")+"</td><td></td><td><b>SEXO:</b></td><td>"+rs9.getString("genero")+"</td><td><input type='hidden' name='txtCodReporte' id='txtCodReporte'/></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>DATOS ACOMPAÑANTE</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>"+rs9.getString("responsable")+"</td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>MOTIVO DE CONSULTA Y ENFERMEDAD ACTUAL:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='mot_consulta' id='mot_consulta'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>ANTECEDENTE PRENATALES, NATALES Y POSTNATALES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='ant_natales' id='ant_natales'></textarea></td></tr></table>");
						//out.print("<table width='100%'><tr><td>¿Cómo fue el embarazo? y ¿Cuánto duró?</td><td><input type='text' size='70'></td><td>¿Cómo fue el parto?</td><td><input type='text' size='70'></td></tr></table>");
						//out.print("<table width='70%'><tr><td>Peso al nacer</td><td><input type='text' size='1'> gr.</td><td><input type='hidden' size='1'></td><td>TALLA al nacer</td><td><input type='text' size='1'> cm.</td><td><input type='hidden' size='1'></td><td>¿Presentó algún problema neonatal?</td><td><input type='text'></td></tr></table>");
						//out.print("<table width='100%'><tr><td>Enfermedades previas y hospitalizaciones</td></tr>" +
								//"<tr><td><textarea rows='4' cols='135' name='enf_previas_hospitalizaciones' id='enf_previas_hospitalizaciones'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>APF (ANTECEDENTES PATOLÓGICOS FAMILIARES):</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='apf' id='apf'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><td>TEMPERATURA</td><td><input type='text' size='1' name='temperatura' id='temperatura' onkeypress='javascript:return validarNro(event)'> ºC</td><td>FC</td><td><input type='text' size='1' name='frecuencia_cardiaca' id='frecuencia_cardiaca' onkeypress='javascript:return validarNro(event)'> /min</td>" +
								"<td>FR</td><td><input type='text' size='1' name='frecuencia_respiratoria' id='frecuencia_respiratoria' onkeypress='javascript:return validarNro(event)'> /min<td>TALLA</td><td><input type='text' size='1' name='talla' id='talla' onkeypress='javascript:return validarNro(event)'> cm.</td>" +
								"<td>Peso</td><td><input type='text' size='1' name='peso' id='peso' onkeypress='javascript:return validarNro(event)' onblur='p()'> Kg</td><td>PC</td><td><input type='text' size='1' name='perimetro_cefalico' id='perimetro_cefalico' onkeypress='javascript:return validarNro(event)'> cm.</td>" +
								"<td>IMC</td><td><input type='text' size='1' name='imc' id='imc' onkeypress='javascript:return validarNro(event)'></td></tr></table>");
	/*Ver Sig Peligro*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI EXISTEN SIGNOS DE PELIGRO EN GENERAL</b></td></tr></table>");
						out.print("<table width='60%'><tr><td>No puede beber o tomar del pecho</td><td><input type='checkbox' name='no_bebe_pecho' id='si'></td><td>Letárgico o inconsciente</td><td><input type='checkbox' name='letargico' id='si'></td>" +
								"<td>Vomita todo</td><td><input type='checkbox' name='vomita_todo' id='si'></td><td>Convulsiones</td><td><input type='checkbox' name='convulsiones' id='si'></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_signos_peligro' id='obs_signos_peligro'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><b>ENFERMEDAD MUY GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='enf_muy_grave' id='si'></td></tr></table>");
						
	/*¿TIENE TOS?*/		out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>¿TIENE TOS O DIFICULTAD PARA RESPIRAR?</b></td><td>Si<INPUT type='radio' name='rad_dificultad_respirar' id='si' onclick='preguntas_tos.disabled=false;desde_cuando_tos.focus()'>No<INPUT type='radio' name='rad_dificultad_respirar' id='no' onclick='preguntas_tos.disabled=true'></td></tr></table>");
						out.print("<table width='100%' name='preguntas_tos 'id='preguntas_tos' disabled><tr><td>Desde hace</td><td><input type='text' size='1' name='desde_cuando_tos' id='desde_cuando_tos' onkeypress='javascript:return validarNro(event)'> días</td></tr>" +
								"<tr><td>Primer episodio sibilancias:</td><td>Si<INPUT type='radio' name='rad_episodio_sibilancias' id='si'>No<INPUT type='radio' name='rad_episodio_sibilancias' id='no'></td><td>Sibilancias recurrentes:</td><td>Si<INPUT type='radio' name='rad_sibilancias_recurrentes' id='si'>No<INPUT type='radio' name='rad_sibilancias_recurrentes' id='no'></td>" +
								"<td>Cuadro gripal últimos 3 días:</td><td>Si<INPUT type='radio' name='rad_cuadro_gripal' id='si'>No<INPUT type='radio' name='rad_cuadro_gripal' id='no'></td><td>Antecedentes prematuridad:</td><td>Si<INPUT type='radio' name='rad_antecedentes_prematuridad' id='si'>No<INPUT type='radio' name='rad_antecedentes_prematuridad' id='no'></td></tr>" +
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
								"<tr><td><b>SIBILANCIA (RECURRENTE)</b>&nbsp;&nbsp;<input type='checkbox' name='sibilancia' id='si'></td><td><b>NEUMONÍA GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='neumonia_grave' id='si'></td>" +
								"<td><b>NEUMONÍA</b>&nbsp;&nbsp;<input type='checkbox' name='neumonia' id='si'></td><td><b>TOS O RESFRIADO</b>&nbsp;&nbsp;<input type='checkbox' name='tos' id='si'></td></table>");
						
	/*¿TIENE DIARREA?*/	out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>¿TIENE DIARREA?</b></td><td>Si<INPUT type='radio' name='rad_tiene_diarrea' id='si' onclick='preguntas_diarrea.disabled=false;desde_cuando_diarrea.focus();'>No<INPUT type='radio' name='rad_tiene_diarrea' id='no' onclick='preguntas_diarrea.disabled=true'></td></tr></table>");
						out.print("<table width='100%' name='preguntas_diarrea' id='preguntas_diarrea' disabled><tr><td>Desde hace</td><td><input type='text' size='1' name='desde_cuando_diarrea' id='desde_cuando_diarrea' onkeypress='javascript:return validarNro(event)'> días</td><td>¿Hay sangre en las heces?</td><td>Si<INPUT type='radio' name='rad_sangre_heces' id='si'>No<INPUT type='radio' name='rad_sangre_heces' id='no'></td></tr>" +
								"<tr><td>¿Tiene vomito?</td><td>Si<INPUT type='radio' name='rad_vomito' id='si'>No<INPUT type='radio' name='rad_vomito' id='no'></td><td>Nº Vomitos en las últimas 4h</td><td><input type='text' size='1' name='num_vomitos' id='num_vomitos' onkeypress='javascript:return validarNro(event)'></td>" +
								"<td>Nº Diarreas en las últimas 24h</td><td><input type='text' size='1' name='num_diarreas_vc' id='num_diarreas_vc' onkeypress='javascript:return validarNro(event)'></td><td>Nº Diarreas en las últimas 4h</td><td><input type='text' size='1' name='num_diarreas_c' id='num_diarreas_c' onkeypress='javascript:return validarNro(event)'></td></tr>" +
								"<tr><td><br><br>Letargico o comatoso</td><td><br><br><input type='checkbox' name='comatoso' id='si'></td><td><br><br>Intranquilo o irritable</td><td><br><br><input type='checkbox' name='intranquilo' id='si'></td>" +
								"<td><br><br>Ojos Hundidos</td><td><br><br><input type='checkbox' name='ojos_hundidos' id='si'></td><td><br><br>Bebe mal o no puede beber</td><td><br><br><input type='checkbox' name='bebe_mal' id='si'></td></tr>" +
								"<tr><td>Bebe Ávidamente con sed</td><td><input type='checkbox' name='bebe_avidamente' id='si'></td>" +
								"<td>Pliegue Cutaneo:</td><td><SELECT name='pliegue_cutaneo' id='pliegue_cutaneo'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Inmediato'>Inmediato</OPTION><OPTION value='Lento'>Lento</OPTION><OPTION value='Muy Lento'>Muy Lento</OPTION></SELECT></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_diarrea' id='obs_diarrea'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>DESHIDRATACIÓN GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='deshidratacion_grave' id='si'></td><td><b>ALGÚN GRADO DESHIDRATACIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='grado_deshidratacion' id='si'></td>" +
								"<td><b>ALTO RIESGO DESHIDRATACIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='alto_riesgo_deshidratacion' id='si'></td><td><b>SIN DESHIDRATACIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='sin_deshidratacion' id='si'></td></tr>" +
								"<tr><td><b>DIARREA PERSISTENTE GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_persistente_grave' id='si'></td><td><b>DIARREA PERSISTENTE</b>&nbsp;&nbsp;<input type='checkbox' name='diarrea_persistente' id='si'></td>" +
								"<td><b>DISENTERÍA</b>&nbsp;&nbsp;<input type='checkbox' name='disenteria' id='si'></td></tr></table>");
						
	/*¿TIENE FIEBRE?*/	out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>¿TIENE FIEBRE?</b></td><td>Si<INPUT type='radio' name='rad_tiene_fiebre' id='si' onclick='preguntas_fiebre.disabled=false;desde_cuando_fiebre.focus()'>No<INPUT type='radio' name='rad_tiene_fiebre' id='no' onclick='preguntas_fiebre.disabled=true'></td></tr></table>");
						out.print("<table width='100%' name='preguntas_fiebre' id='preguntas_fiebre' disabled><tr><td>Desde hace</td><td><input type='text' size='1' name='desde_cuando_fiebre' id='desde_cuando_fiebre' onkeypress='javascript:return validarNro(event)'> días</td>" +
								"<td>Fiebre >38 ºC</td><td>Si<INPUT type='radio' name='rad_fiebre_to' id='si'>No<INPUT type='radio' name='rad_fiebre_to' id='no'></td><td> Fiebre >39 ºC</td><td>Si<INPUT type='radio' name='rad_fiebre_tn' id='si'>No<INPUT type='radio' name='rad_fiebre_tn' id='no'></td></tr>" +
								"<tr><td><b>Vive o visitó en los últimos 15 días</b></td><td>Si<INPUT type='radio' name='rad_vive_quince_dias' id='si'>No<INPUT type='radio' name='rad_vive_quince_dias' id='no'></td></tr>" +
								"<tr><td>Zona Dengue(altura <2200 m)</td><td><input type='checkbox' name='zona_dengue' id='si'></td><td>Zona Malaria:</td><td><SELECT name='zona_malaria' id='zona_malaria'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Urbana'>Urbana</OPTION><OPTION value='Rural'>Rural</OPTION></SELECT></td></tr>" +
								
								"<tr><td><br><br>Rigidez de nuca</td><td><br><br><input type='checkbox' name='rigidez_nuca' id='si'></td><td><br><br>Apariencia de enfermo grave</td><td><br><br><input type='checkbox' name='apariencia_enfermo' id='si'></td>" +
								"<td><br><br>Manifestaciones de sangrado</td><td><br><br><input type='checkbox' name='manifestaciones_sangrado' id='si'></td><td><br><br>Aspecto toxico</td><td><br><br><input type='checkbox' name='aspecto_toxico' id='si'></td></tr>" +
								"<tr><td>Respuesta social:</td><td><SELECT name='respuesta_social' id='respuesta_social'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Normal'>Normal</OPTION><OPTION value='Inadecuada'>Inadecuada</OPTION><OPTION value='Sin Respuesta'>Sin Respuesta</OPTION></SELECT></td>" +
								"<td>Piel:</td><td><SELECT name='piel' id='piel'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Palida'>Palida</OPTION><OPTION value='Moteada'>Moteada</OPTION><OPTION value='Cenicienta'>Cenicienta</OPTION><OPTION value='Azul'>Azul</OPTION></SELECT></td>" +
								"<td>Erupcion cutanea generalizada</td><td><input type='checkbox' name='erupcion_cutanea' id='si'></td><td>Dolor Abdominal</td><td><input type='checkbox' name='dolor_abdominal' id='si'></td></tr>" +
								"<tr><td>Cefalea</td><td><input type='checkbox' name='cefalea' id='si'></td><td>Mialgias</td><td><input type='checkbox' name='mialgias' id='si'></td>" +
								"<td>Artralgias</td><td><input type='checkbox' name='artralgias' id='si'></td><td>Dolor retroocular</td><td><input type='checkbox' name='dolor_retroocular' id='si'></td></tr>" +
								"<tr><td>Postración</td><td><input type='checkbox' name='postracion' id='si'></td><td>P. Torniquete</td><td><input type='checkbox' name='p_torniquete' id='si'></td>" +
								"<td>Lipotimia</td><td><input type='checkbox' name='lipotimia' id='si'></td><td>Hepatomegalia</td><td><input type='checkbox' name='hepatomegalia' id='si'></td></tr>" +
								"<tr><td>Pulso rápido y fino</td><td><input type='checkbox' name='pulso_rapido' id='si'></td><td>Llenado capilar >2 seg.</td><td><input type='checkbox' name='llenado_capilar' id='si'></td>" +
								"<td>Ascitis</td><td><input type='checkbox' name='ascitis' id='si'></td><td>Disminución diuresis:</td><td><input type='checkbox' name='disminucion_diuresis' id='si'></td></tr>" +			

								"<tr><td><br><br><b>LABORATORIOS:</b></td></tr>" +
								"<tr><td>CH >15000</td><td><input type='checkbox' name='cuadro_hematico' id='si'></td><td>Leucocitos <4000</td><td><input type='checkbox' name='leucocitos' id='si'></td>" +
								"<td>Neutrófilos >10000</td><td><input type='checkbox' name='neutrofilos' id='si'></td><td>Plaquetas <100000</td><td><input type='checkbox' name='plaquetas' id='si'></td></tr>" +
								"<tr><td>Parcial de Orina compatible con infección</td><td><input type='checkbox' name='parcial_orina' id='si'></td><td>Gota gruesa positiva</td><td><input type='checkbox' name='gota_gruesa' id='si'></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_fiebre' id='obs_fiebre'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>ENF. FEBRIL DE RIESGO</b>&nbsp;&nbsp;<input type='checkbox' name='enf_febril_riesgo' id='si'></td><td><b>ENF. FEBRIL DE RIESGO INTERMEDIO</b>&nbsp;&nbsp;<input type='checkbox' name='enf_febril_riesgo_intermedio' id='si'></td>" +
								"<td><b>ENF. FEBRIL DE RIESGO BAJO</b>&nbsp;&nbsp;<input type='checkbox' name='enf_febril_riesgo_bajo' id='si'></td><td><b>MALARÍA COMPLICADA</b>&nbsp;&nbsp;<input type='checkbox' name='malaria_complicada' id='si'></td></tr>" +
								"<tr><td><b>MALARÍA</b>&nbsp;&nbsp;<input type='checkbox' name='malaria' id='si'></td><td><b>DENGUE GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='dengue_grave' id='si'></td>" +
								"<td><b>DENGUE CON SIGNOS ALARMA</b>&nbsp;&nbsp;<input type='checkbox' name='dengue_signos_alarma' id='si'></td><td><b>PROBABLE DENGUE</b>&nbsp;&nbsp;<input type='checkbox' name='probable_dengue' id='si'></td></tr></table>");
						
	/*¿TIENE OIDO?*/	out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>¿TIENE PROBLEMA DE OIDO?</b></td><td>Si<INPUT type='radio' name='rad_tiene_prob_oido' id='si' onclick='preguntas_oido.disabled=false'>No<INPUT type='radio' name='rad_tiene_prob_oido' id='no' onclick='preguntas_oido.disabled=true'></td></tr></table>");
						out.print("<table width='100%' name='preguntas_oido' id='preguntas_oido' disabled><tr><td>¿Tiene dolor de oído?:</td><td>Si<INPUT type='radio' name='rad_tiene_dolor_oido' id='si'>No<INPUT type='radio' name='rad_tiene_dolor_oido' id='no'></td>" +
								"<td>¿Tiene supuración?:</td><td>Si<INPUT type='radio' name='rad_tiene_supuracion' id='si' onclick='desde_cuando_supuracion.disabled=false;desde_cuando_supuracion.focus();num_episodios_previos.disabled=false'>No<INPUT type='radio' name='rad_tiene_supuracion' id='no' onclick='desde_cuando_supuracion.disabled=true;num_episodios_previos.disabled=true'></td><td>Hace</td><td><input type='text' size='1' name='desde_cuando_supuracion' id='desde_cuando_supuracion' disabled onkeypress='javascript:return validarNro(event)'> días</td>" +
								"<td>Nº episodios previos:</td><td><input type='text' size='1' name='num_episodios_previos' id='num_episodios_previos' disabled onkeypress='javascript:return validarNro(event)'></td></tr>" +
								"<tr><td><br><br>Tumefacción dolorosa detrás de la oreja</td><td><br><br><input type='checkbox' name='tumefaccion_dolorosa' id='si'></td><td><br><br>Tímpano Rojo y Abombado</td><td><br><br><input type='checkbox' name='timpano_rojo' id='si'></td>" +
								"<td><br><br>Supuración de oído</td><td><br><br><input type='checkbox' name='supuracion_oido' id='si'></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_oido' id='obs_oido'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><b>MASTOIDITIS</b>&nbsp;&nbsp;<input type='checkbox' name='mastoiditis' id='si'></td><td><b>OTITIS MEDIA CRÓNICA</b>&nbsp;&nbsp;<input type='checkbox' name='otitis_media_cronica' id='si'></td>" +
								"<td><b>OTITIS MEDIA RECURRENTE</b>&nbsp;&nbsp;<input type='checkbox' name='otitis_media_recurrente' id='si'></td><td><b>OTITIS MEDIA AGUDA</b>&nbsp;&nbsp;<input type='checkbox' name='otitis_media_aguda' id='si'></td>" +
								"<td><b>NO TIENE OTITIS</b>&nbsp;&nbsp;<input type='checkbox' name='no_tiene_otitis' id='si'></td></tr></table>");
						
	/*¿TIENE GARGANTA?*/out.print("<table width='50%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>¿TIENE UN PROBLEMA DE GARGANTA?</b></td><td>Si<INPUT type='radio' name='rad_tiene_prob_garganta' id='si' onclick='preguntas_garganta.disabled=false;checkboxs_garganta.disabled=false'>No<INPUT type='radio' name='rad_tiene_prob_garganta' id='no' onclick='preguntas_garganta.disabled=true;checkboxs_garganta.disabled=true'></td></tr></table>");
						out.print("<table width='30%' name='preguntas_garganta' id='preguntas_garganta' disabled><tr><td>¿Tiene dolor de garganta?</td><td>Si<INPUT type='radio' name='tiene_dolor_garganta' id='si'>No<INPUT type='radio' name='tiene_dolor_garganta' id='no'></td></tr></table>");
						out.print("<table width='60%' name='checkboxs_garganta' id='checkboxs_garganta' disabled><tr><br><br><td>Ganglios del cuello crecidos y dolorosos</td><td><input type='checkbox' name='ganglios_cuello' id='si'></td><td>Amígdalas eritematosas</td><td><input type='checkbox' name='amigdalas_eritematosas' id='si'></td>" +
								"<td>Exudado blanquecino - amarillento en amigdalas</td><td><input type='checkbox' name='exudado_blanquecino' id='si'></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_garganta' id='obs_garganta'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><b>FARINGOAMIGDALITIS</b>&nbsp;&nbsp;<input type='checkbox' name='faringoamigdalitis' id='si'></td><td><b>ESTREPTOCÓCICA</b>&nbsp;&nbsp;<input type='checkbox' name='estreptococica' id='si'></td>" +
								"<td><b>FARINGOAMIGDALITIS VIRAL</b>&nbsp;&nbsp;<input type='checkbox' name='faringoamigdalitis_viral' id='si'></td><td><b>NO TIENE FARINGOAMIGDALITIS</b>&nbsp;&nbsp;<input type='checkbox' name='no_tiene_faringoamigdalitis' id='si'></td></tr></table>");
						
	/*VERIFICAR SBUCAL*/out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>ENSEGUIDA, VERIFICAR LA SALUD BUCAL</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>¿Tiene dolor al comer-masticar?</td><td>Si<INPUT type='radio' name='rad_tiene_dolor_comer' id='si'>No<INPUT type='radio' name='rad_tiene_dolor_comer' id='no'></td><td>¿Tiene dolor en diente?</td><td>Si<INPUT type='radio' name='rad_tiene_dolor_diente' id='si'>No<INPUT type='radio' name='rad_tiene_dolor_diente' id='no'></td>" +
								"<td>¿Trauma en cara o boca?</td><td>Si<INPUT type='radio' name='rad_trauma_cara' id='si'>No<INPUT type='radio' name='rad_trauma_cara' id='no'></td><td>¿Tiene padres/hermanos caries?</td><td>Si<INPUT type='radio' name='rad_tiene_caries' id='si'>No<INPUT type='radio' name='rad_tiene_caries' id='no'></td></tr>" +
								"<tr><td><b>¿CUÁNDO LE LIMPIA LA BOCA?</b></td></tr>" +
								"<tr><td>Mañana</td><td>Si<INPUT type='radio' name='rad_limpia_boca_manana' id='si'>No<INPUT type='radio' name='rad_limpia_boca_manana' id='no'></td>" +
								"<td>Tarde</td><td>Si<INPUT type='radio' name='rad_limpia_boca_tarde' id='si'>No<INPUT type='radio' name='rad_limpia_boca_tarde' id='no'></td>" +
								"<td>Noche</td><td>Si<INPUT type='radio' name='rad_limpia_boca_noche' id='si'>No<INPUT type='radio' name='rad_limpia_boca_noche' id='no'></td></tr>" +
								"<tr><td><b>¿CÓMO SUPERVISA LIMPIEZA?</b></td></tr>" +
								"<tr><td>Le limpia los Dientes</td><td>Si<INPUT type='radio' name='rad_limpia_dientes' id='si'>No<INPUT type='radio' name='rad_limpia_dientes' id='no'></td><td>Niño solo</td><td>Si<INPUT type='radio' name='rad_nino_solo' id='si'>No<INPUT type='radio' name='rad_nino_solo' id='no'></td></tr>" +
								"<tr><td><b>¿QUÉ UTILIZA?</b></td></tr>" +
								"<tr><td>Cepillo:</td><td>Si<INPUT type='radio' name='rad_cepillo' id='si'>No<INPUT type='radio' name='rad_cepillo' id='no'></td><td>Crema:</td><td>Si<INPUT type='radio' name='rad_crema' id='si'>No<INPUT type='radio' name='rad_crema' id='no'></td>" +
								"<td>Seda:</td><td>Si<INPUT type='radio' name='rad_seda' id='si'>No<INPUT type='radio' name='rad_seda' id='no'></td><td>¿Utiliza chupo o biberón?</td><td>Si<INPUT type='radio' name='rad_utiliza_chupo' id='si'>No<INPUT type='radio' name='rad_utiliza_chupo' id='no'></td></tr></table>");
						out.print("<table><tr><td>¿Cuándo fue la última consulta odintológica?</td><td><input type='text' size='70' name='ultima_cons_odontologica' id='ultima_consulta_odontologica'></td></tr></table>");
						
						out.print("<table width='100%'><tr><br><br><td>Inflamación dolorosa del labio</td><td><input type='checkbox' name='inflamacion_labio' id='si'></td><td>No involucra surco</td><td><input type='checkbox' name='no_involucra_surco' id='si'></td>" +
								"<td>Enrojecimiento</td><td><input type='checkbox' name='enrojecimiento' id='si'></td><td>Inflamación encía</td><td><input type='checkbox' name='inflamacion_encia' id='si'></td></tr>" +
								"<tr><td>Localizado</td><td><input type='checkbox' name='localizado' id='si'></td><td>Generalizado</td><td><input type='checkbox' name='generalizado' id='si'></td>" +
								"<td>Deformación contorno de encía</td><td><input type='checkbox' name='deformacion_encia' id='si'></td><td>Exudado-pus</td><td><input type='checkbox' name='exudado_pus' id='si'></td></tr>" +
								"<tr><td>Vesiculas</td><td><input type='checkbox' name='vesiculas' id='si'></td><td>Úlceras</td><td><input type='checkbox' name='ulceras' id='si'></td>" +
								"<td>Placas:</td><td><SELECT name='placas' id='placas'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Encia'>Encía</OPTION><OPTION value='Lengua Paladar'>Lengua Paladar</OPTION></SELECT></td></td><td>Fractura</td><td><input type='checkbox' name='fractura' id='si'></td></tr>" +
								"<tr><td>Movilidad</td><td><input type='checkbox' name='movilidad' id='si'></td><td>Desplazamiento</td><td><input type='checkbox' name='desplazamiento' id='si'></td>" +
								"<td>Extrusión</td><td><input type='checkbox' name='extrusion' id='si'></td><td>Intrusión</td><td><input type='checkbox' name='intrusion' id='si'></td></tr>" +
								"<tr><td>Avulsión</td><td><input type='checkbox' name='avulsion' id='si'></td><td>Herida</td><td><SELECT name='herida' id='herida'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Mucosa Bucal'>Mucosa Bucal</OPTION><OPTION value='Encia'>Encía</OPTION><OPTION value='Lengua'>Lengua</OPTION></SELECT></td><td>Manchas Blancas</td><td><input type='checkbox' name='manchas_blancas' id='si'></td>" +
								"<td>Cafés</td><td><input type='checkbox' name='cafes' id='si'></td></tr>" +
								"<tr><td>Caries cavitacionales</td><td><input type='checkbox' name='caries_cavitacionales' id='si'></td><td>Placa bacteriana</td><td><input type='checkbox' name='placa_bacteriana' id='si'></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_salud_bucal' id='obs_salud_bucal'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><b>CELULITIS FACIAL</b>&nbsp;&nbsp;<input type='checkbox' name='celulitis_facial' id='si'></td><td><b>ENFERMEDAD BUCAL GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='enf_bucal_grave' id='si'></td>" +
								"<td><b>TRAUMA BUCODENTAL</b>&nbsp;&nbsp;<input type='checkbox' name='trauma_bucodental' id='si'></td><td><b>ESTOMATITIS</b>&nbsp;&nbsp;<input type='checkbox' name='estomatitis' id='si'></td>" +
								"<td><b>ENFERMEDAD DENTAL Y GINGIVAL</b>&nbsp;&nbsp;<input type='checkbox' name='enf_dental' id='si'></td><td><b>ALTO RIESGO DE ENFERMEDAD BUCAL</b>&nbsp;&nbsp;<input type='checkbox' name='alto_riesgo_enf_bucal' id='si'></td>" +
								"<td><b>BAJO RIESGO DE ENFERMEDAD BUCAL</b>&nbsp;&nbsp;<input type='checkbox' name='bajo_riesgo_enf_bucal' id='si'></td></tr></table>");
						
	/*VER. CRECIMIENTO*/out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR EL CRECIMIENTO:</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>Emanación visible</td><td>Si<INPUT type='radio' name='emanacion_visible' id='si'>No<INPUT type='radio' name='emanacion_visible' id='no'></td><td>Peso/Edad:</td><td><input type='text' size='1' name='peso_edad' id='peso_edad' onkeypress='javascript:return validarNro(event)'> DE</td>" +
								"<td>Edema en ambos pies</td><td>Si<INPUT type='radio' name='rad_edema_pies' id='si'>No<INPUT type='radio' name='rad_edema_pies' id='no'></td><td>Apariencia</td><td><input type='text' name='apariencia' id='apariencia'></tr>" +
								"<tr><td>IMC/Edad:DE:</td><td><input type='text' name='imc_edad' id='imc_edad' onkeypress='javascript:return validarNro(event)'></td><td>Talla/Edad:DE:</td><td><input type='text' name='talla_edad' id='talla_edad' onkeypress='javascript:return validarNro(event)'></td>" +
								"<td>Peso/Talla:DE:</td><td><input type='text' name='peso_talla' id='peso_talla' onkeypress='javascript:return validarNro(event)'></td><td>Tendencia Peso:</td><td><SELECT name='tendencia_peso' id='tendencia_peso'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Ascendente'>Ascendente</OPTION><OPTION value='Horizontal'>Horizontal</OPTION><OPTION value='Descendente'>Descendente</OPTION></SELECT></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><-3 Desnutrición global severa</td><td><input type='checkbox' name='desnutricion_global_severa' id='si'></td><td><-2 a >-3 Desnutrición global</td><td><input type='checkbox' name='desnutricion_global' id='si'></td>" +
								"<td><-1 a >-2 Riesgo de Desnutrición</td><td><input type='checkbox' name='riesgo_desnutricion' id='si'></td><td><1 a >-1 Peso adecuado para edad</td><td><input type='checkbox' name='peso_adecuado_edad' id='si'></td></tr>" +
								"<tr><td><-2 Desnutrición crónica o Retraso crecimiento</td><td><input type='checkbox' name='desnutricion_cronica' id='si'></td><td>>-2 a <-1 Riesgo DNT con bajo P/T</td><td><input type='checkbox' name='riesgo_DNT' id='si'></td>" +
								"<td>>-1 Talla adecuada / edad</td><td><input type='checkbox' name='talla_adecuada_edad' id='si'></td><td><-3 Desnutrición Aguda Severa</td><td><input type='checkbox' name='desnutricion_aguda' id='si'></td></tr>" +
								"<tr><td>>-3 a <-2 DNT Aguda-Peso bajo/Talla</td><td><input type='checkbox' name='DNT_aguda' id='si'></td><td>>-1 a <1 Peso adecuado para Talla</td><td><input type='checkbox' name='peso_adecuado_talla' id='si'></td>" +
								"<td>>1 a <2 Sobrepeso</td><td><input type='checkbox' name='sobrepeso' id='si'></td><td>>2 Obesidad</td><td><input type='checkbox' name='obesidad' id='si'></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_crecimiento' id='obs_crecimiento'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><b>OBESO</b>&nbsp;&nbsp;<input type='checkbox' name='obeso' id='si'></td><td><b>SOBREPESO</b>&nbsp;&nbsp;<input type='checkbox' name='sobrepeso_r' id='si'></td>" +
								"<td><b>DESNUTRICIÓN SEVERA</b>&nbsp;&nbsp;<input type='checkbox' name='desnutricion_severa' id='si'></td><td><b>DESNUTRICIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='desnutricion' id='si'></td>" +
								"<td><b>RIESGO DESNUTRICIÓN</b>&nbsp;&nbsp;<input type='checkbox' name='riesgo_desnutricion_r'></td><td><b>ADECUADO ESTADO NUTRICIONAL</b>&nbsp;&nbsp;<input type='checkbox' name='estado_nutricional' id='si'></td></tr></table>");
						
	/*VER. ANEMIA*/		out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR SI TIENE ANEMIA</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>Ha recibido hierro en los últimos 6 meses:</td><td>Si<INPUT type='radio' name='rad_recibido_hierro' id='si' onclick='cuando_recibido_hierro.disabled=false;cuando_recibido_hierro.focus();cuanto_tiempo_recibido_hierro.disabled=false'>No<INPUT type='radio' name='rad_recibido_hierro' id='no' onclick='cuando_recibido_hierro.disabled=true;cuanto_tiempo_recibido_hierro.disabled=true'></td>" +
								"<td>¿Cuándo?</td><td><input type='text' name='cuando_recibido_hierro' id='cuando_recibido_hierro' disabled></td><td>¿Cuánto tiempo?</td><td><input type='text' name='cuanto_tiempo_recibido_hierro' id='cuanto_tiempo_recibido_hierro' disabled></td></tr>" +
								"<tr><td>Palidez palmar:</td><td><SELECT name='palidez_palmar' id='palidez_palmar'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Intensa'>Intensa</OPTION><OPTION value='Leve'>Leve</OPTION><OPTION value='No'>No</OPTION></SELECT></td>" +
								"<td>Palidez conjuntival</td><td><SELECT name='palidez_conjuntival' id='palidez_conjuntival'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Intensa'>Intensa</OPTION><OPTION value='Leve'>Leve</OPTION><OPTION value='No'>No</OPTION></SELECT></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OBSERVACIONES:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_anemia' id='obs_anemia'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><b>ANEMIA SEVERA</b>&nbsp;&nbsp;<input type='checkbox' name='anemia_severa' id='si'></td><td><b>ANEMIA</b>&nbsp;&nbsp;<input type='checkbox' name='anemia' id='si'></td>" +
								"<td><b>NO TIENE ANEMIA</b>&nbsp;&nbsp;<input type='checkbox' name='no_tiene_anemia' id='si'></td></tr></table>");
						
	/*VER. MALTRATO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>ENSEGUIDA, VERIFICAR SI TIENE MALTRATO</b></td></tr></table>");
						out.print("<table><tr><td>¿Cómo se produjeron las lesiones?</td><td><input type='text' size='70' name='produjeron_lesiones' id='produjeron_lesiones'></td></tr></table>");
						out.print("<table width='100%'><tr><td>¿El niño relata maltrato?</td><td>Si<INPUT type='radio' name='rad_relata_maltrato' id='si' onclick='quien_maltrato.disabled=false;malt_fisico.disabled=false;malt_sexual.disabled=false;negligencia.disabled=false'>No<INPUT type='radio' name='rad_relata_maltrato' id='no' onclick='malt_fisico.disabled=true;malt_sexual.disabled=true;negligencia.disabled=true;quien_maltrato.disabled=true'></td>" +
								"<td>¿Cuál?</td><td>Físico<INPUT type='checkbox' name='malt_fisico' id='si' disabled>Sexual<INPUT type='checkbox' name='malt_sexual' id='si' disabled>Negligencia<INPUT type='checkbox' name='negligencia' id='si' disabled></td><td>¿Quién?</td><td><input type='text' size='50' name='quien_maltrato' id='quien_maltrato' disabled></td></tr></table>");
						out.print("<table width='100%'><tr><td>¿Hay incongruencia para explicar un Trauma significante?</td><td>Si<INPUT type='radio' name='rad_incongruencia_trauma' id='si'>No<INPUT type='radio' name='rad_incongruencia_trauma' id='no'></td><td>¿Existe incongruencia entre lesión - edad - desarrollo del niño?</td><td>Si<INPUT type='radio' name='rad_existe_incongruencia' id='si' onclick='diferentes_versiones.disabled=false;diferentes_versiones.focus()'>No<INPUT type='radio' name='rad_existe_incongruencia' id='no' onclick='diferentes_versiones.disabled=true'></td>" +
								"<td>¿Hay diferentes versiones?</td><td><input type='text' name='diferentes_versiones' id='diferentes_versiones' disabled></td></tr>" +
								"<tr><td>¿Es tardía la consulta?</td><td>Si<INPUT type='radio' name='rad_tardia_consulta' id='si'>No<INPUT type='radio' name='rad_tardia_consulta' id='no'></td><td>¿Con qué frecuencia se ve obligado a pegarle a su hijo?</td><td><input type='text' name='frecuencia_pegar' id='frecuencia_pegar'></td>" +
								"<td>¿Qué tan desobediente es su hijo que se ve obligado a pegarle?</td><td><input type='text' name='desobediente_hijo' id='desobediente_hijo'></td></tr>" +
								"<tr><td>Comportamiento anormal de los padres:</td><td><SELECT name='comportamiento_anormal_padres' id='comportamiento_anormal_padres'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Desespero'>Desespero</OPTION><OPTION value='Impaciencia'>Impaciencia</OPTION><OPTION value='Intolerancia'>Intolerancia</OPTION><OPTION value='Agresividad en la consulta'>Agresividad en la consulta</OPTION></SELECT></td>" +
								"<td>¿Está descuidado el niño en su salud?</td><td>Si<INPUT type='radio' name='rad_descuidado_nino' id='si' onclick='por_descuidado_nino.disabled=false;por_descuidado_nino.focus();descuidado_nino_en.disabled=false'>No<INPUT type='radio' name='rad_descuidado_nino' id='no' onclick='por_descuidado_nino.disabled=true;descuidado_nino_en.disabled=true'></td><td>Por:</td><td><input type='text' name='por_descuidado_nino' id='por_descuidado_nino' disabled></td></tr>" +
								"<tr><td>Está descuidado el niño en:</td><td><SELECT name='descuidado_nino_en' id='descuidado_nino_en' disabled><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Higiene'>Higiene</OPTION><OPTION value='Proteccion'>Protección</OPTION><OPTION value='Alimentacion'>Alimentación</OPTION><OPTION value='Nino de calle'>Niño de calle</OPTION></SELECT></td>" +
								"<td>Factor de riesgo:</td><td><SELECT name='factor_riesgo' id='factor_riesgo'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Discapacitado'>Discapacitado</OPTION><OPTION value='Hiperactivo'>Hiperactivo</OPTION></SELECT></td>" +
								"<td>¿Actitud anormal del niño?</td><td>Si<INPUT type='radio' name='rad_actitud_anormal' id='si'>No<INPUT type='radio' name='rad_actitud_anormal' id='no'></td></tr></table>");
						out.print("<table width='80%'><tr><br><br><td><b>Lesiones en cráneo:</b></td></tr>" +
								"<tr><td>Fracturas</td><td><input type='checkbox' name='fracturas' id='si'>&nbsp;&nbsp;</td><td>Hematomas</td><td><input type='checkbox' name='hematomas' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Hemorragias retinianas</td><td><input type='checkbox' name='hemorragias_retinianas' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td><b>Quemaduras:</b></td></tr>" +
								"<tr><td>Áreas cubiertas por ropa</td><td><input type='checkbox' name='areas_ropa' id='si'>&nbsp;&nbsp;</td><td>Patrón simétrico,límite bien demarcado</td><td><input type='checkbox' name='patron_simetrico' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Denota el objeto con que fue quemado</td><td><input type='checkbox' name='denota_objeto' id='si'>&nbsp;&nbsp;</td><td>En espalda, dorso, manos o nalgas</td><td><input type='checkbox' name='en_espalda' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td><br><br>Equimosis</td><td><br><br><input type='checkbox' name='equimosis' id='si'>&nbsp;&nbsp;</td><td><br><br>Hematomas</td><td><br><br><input type='checkbox' name='hematomas_maltrato' id='si'>&nbsp;&nbsp;</td>" +
								"<td><br><br>Lasceraciones</td><td><br><br><input type='checkbox' name='lasceraciones' id='si'>&nbsp;&nbsp;</td><td><br><br>Mordiscos</td><td><br><br><input type='checkbox' name='mordiscos' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td>Cicatrices lejos de la prominencia oseo con patrón del objeto agresor</td><td><input type='checkbox' name='cicatrices' id='si'>&nbsp;&nbsp;</td><td>Diferente evolución en niños que no deambulan</td><td><input type='checkbox' name='diferente_evolucion' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Sugestivas de maltrato</td><td><input type='checkbox' name='sugestivas_maltrato' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td><b>Fracturas:</b></td></tr>" +
								"<tr><td>Costillas</td><td><input type='checkbox' name='costillas' id='si'>&nbsp;&nbsp;</td><td>Huesos largos</td><td><input type='checkbox' name='huesos_largos' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Espirales</td><td><input type='checkbox' name='espirales' id='si'>&nbsp;&nbsp;</td><td>Oblicua</td><td><input type='checkbox' name='oblicua' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td>Metafisiarias</td><td><input type='checkbox' name='metafisiarias' id='si'>&nbsp;&nbsp;</td><td>Esternon</td><td><input type='checkbox' name='esternon' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Escápula</td><td><input type='checkbox' name='escapula' id='si'>&nbsp;&nbsp;</td><td>Menor de 5 años</td><td><input type='checkbox' name='menor_cinco_anos' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td><br><br>Trauma visceral</td><td><br><br><input type='checkbox' name='trauma_visceral' id='si'>&nbsp;&nbsp;</td><td><br><br>Trauma grave</td><td><br><br><input type='checkbox' name='trauma_grave' id='si'>&nbsp;&nbsp;</td>" +
								"<td><br><br>Lesiones física sugestival</td><td><br><br><input type='text' name='lesiones_sugestival' id='lesiones_sugestival'>&nbsp;&nbsp;</td><td><br><br>Sangrado vaginal o anal traumático</td><td><br><br><input type='checkbox' name='sangrado_vaginal' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td><b>Trauma genital:</b></td></tr>" +
								"<tr><td>Laceración aguda o equimosis himen</td><td><input type='checkbox' name='lasceracion_aguda' id='si'>&nbsp;&nbsp;</td><td>Laceración perianal desde esfinter</td><td><input type='checkbox' name='lasceracion_perianal' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Ausencia himen</td><td><input type='checkbox' name='ausencia_himen' id='si'>&nbsp;&nbsp;</td><td>Himen cicatrizado</td><td><input type='checkbox' name='himen_cicatrizado' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Cicatriz navicular</td><td><input type='checkbox' name='cicatriz_navicular' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td>Ano dilatado</td><td><input type='checkbox' name='ano_dilatado' id='si'>&nbsp;&nbsp;</td><td>Hallazgo semen</td><td><input type='checkbox' name='hallazgo_semen' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Flujo genital</td><td><input type='checkbox' name='flujo_genital' id='si'>&nbsp;&nbsp;</td><td>Cuerpo extraño en vagina o ano</td><td><input type='checkbox' name='cuerpo_extrano' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Vesículas o verrugas en genitales</td><td><input type='checkbox' name='vesiculas_maltrato' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td><br><br>Juego con contenido sexual</td><td><br><br><input type='checkbox' name='juego_sexual' id='si'>&nbsp;&nbsp;</td><td><br><br>Boca en genitales</td><td><br><br><input type='checkbox' name='boca_genitales' id='si'>&nbsp;&nbsp;</td>" +
								"<td><br><br>VIH</td><td><br><br><input type='checkbox' name='vih' id='si'>&nbsp;&nbsp;</td><td><br><br>Gonorrea</td><td><br><br><input type='checkbox' name='gonorrea' id='si'>&nbsp;&nbsp;</td>" +
								"<td><br><br>Sífilis</td><td><br><br><input type='checkbox' name='sifilis' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td>Trichomona vaginalis >1a</td><td><input type='checkbox' name='trichomona_vaginalis' id='si'>&nbsp;&nbsp;</td><td>Chlamydia Trachomatis >3a</td><td><input type='checkbox' name='chlamydia_trachomatis' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Condilomatosis</td><td><input type='checkbox' name='condilomatosis' id='si'>&nbsp;&nbsp;</td><td>Temeroso</td><td><input type='checkbox' name='temeroso' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Retraido</td><td><input type='checkbox' name='retraido' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td>Rechazo adulto</td><td><input type='checkbox' name='rechazo_adulto' id='si'>&nbsp;&nbsp;</td><td>Deprimido</td><td><input type='checkbox' name='deprimido' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Evita contacto visual</td><td><input type='checkbox' name='evita_contacto_visual' id='si'>&nbsp;&nbsp;</td><td>Trastorno de sueño</td><td><input type='checkbox' name='trastorno_sueno' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Trastorno alimentario</td><td><input type='checkbox' name='trastorno_alimentario' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td>Problemas Psicosomáticos</td><td><input type='checkbox' name='problemas_psicomaticos' id='si'>&nbsp;&nbsp;</td><td>Conductas regresivas</td><td><input type='checkbox' name='conductas_regresivas' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Desarrollo estancado</td><td><input type='checkbox' name='desarrollo_estancado' id='si'>&nbsp;&nbsp;</td><td>Violencia intrafamiliar</td><td><input type='checkbox' name='violencia_intrafamiliar' id='si'>&nbsp;&nbsp;</td>" +
								"<td>Familia caótica</td><td><input type='checkbox' name='familia_caotica' id='si'>&nbsp;&nbsp;</td></tr>" +
								"<tr><td>Cuidadores adictos</td><td><input type='checkbox' name='cuidadores_adictos' id='si'>&nbsp;&nbsp;</td></tr></table>");
						out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_maltrato' id='obs_maltrato'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><br><td><b>MALTRATO FÍSICO MUY GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='maltrato_fisico_muy_grave' id='si'></td><td><b>ABUSO GRAVE</b>&nbsp;&nbsp;<input type='checkbox' name='abuso_grave' id='si'></td>" +
								"<td><b>MALTRATO FÍSICO</b>&nbsp;&nbsp;<input type='checkbox' name='maltrato_fisico' id='si'></td><td><b>SOSPECHA ABUSO SEXUAL</b>&nbsp;&nbsp;<input type='checkbox' name='sospecha_abuso_sexual' id='si'></td>" +
								"<td><b>MALTRATO EMOCIONAL</b>&nbsp;&nbsp;<input type='checkbox' name='maltrato_emocional' id='si'></td><td><b>NEGLIGENCIA O ABANDONO</b>&nbsp;&nbsp;<input type='checkbox' name='abandono' id='si'></td>" +
								"<td><b>NO HAY SOSPECHA MALTRATO</b>&nbsp;&nbsp;<input type='checkbox' name='no_sospecha_maltrato' id='si'></td></tr></table>");
						
	/*EVA DESARROLLO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>ENSEGUIDA, EVALUAR EL DESARROLLO</b></td></tr></table>");
						out.print("<table width='100%'><tr><td>Tiene algún antecedente importante</td><td>Si<INPUT type='radio' name='rad_antecedente_importante' id='si' onclick='para_desarrollo.disabled=false;para_desarrollo.focus();algun_factor_riesgo.disabled=false'>No<INPUT type='radio' name='rad_antecedente_importante' id='no' onclick='para_desarrollo.disabled=true;algun_factor_riesgo.disabled=true'></td><td>Para el desarrollo:</td><td><input type='text' size='70' name='para_desarrollo' id='para_desarrollo' disabled></td><td>Tiene algún factor de riesgo:</td><td><input type='text' size='70' name='algun_factor_riesgo' id='algun_factor_riesgo' disabled></td></tr></table>");
						out.print("<table width='100%'><tr><td>Realiza</td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='realiza_cond_edad_uno' id='si'>2<input type='checkbox' name='realiza_cond_edad_dos' id='si'>3<input type='checkbox' name='realiza_cond_edad_tres' id='si'>4<input type='checkbox' name='realiza_cond_edad_cuatro' id='si'>&nbsp;&nbsp;&nbsp;condiciones para la edad</td>" +
								"<td>Ausencia de </td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='ausencia_cond_edad_uno' id='si'>2<input type='checkbox' name='ausencia_cond_edad_dos' id='si'>3<input type='checkbox' name='ausencia_cond_edad_tres' id='si'>4<input type='checkbox' name='ausencia_cond_edad_cuatro' id='si'>&nbsp;&nbsp;&nbsp;condiciones para la edad</td>" +
								"<td>Ausencia de</td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='ausencia_cond_grupo_anterior_uno' id='si'>2<input type='checkbox' name='ausencia_cond_grupo_anterior_dos' id='si'>3<input type='checkbox' name='ausencia_cond_grupo_anterior_tres' id='si'>4<input type='checkbox' name='ausencia_cond_grupo_anterior_cuatro' id='si'>&nbsp;&nbsp;&nbsp;condiciones del grupo anterior</td></tr>" +
								"<tr><td>Perímetro cefálico:</td><td><input type='text' name='PC' id='PC' onkeypress='javascript:return validarNro(event)'> cm</td><td>Alteraciones fenotípicas</td><td><input type='text' name='alteraciones_fenotipicas' id='alteraciones_fenotipicas'></td></tr></table>");
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
								"<td><b>Streptococo Neumoníae:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='streptococo_neumoniae_uno' id='si'>2<input type='checkbox' name='streptococo_neumoniae_dos' id='si'>3<input type='checkbox' name='streptococo_neumoniae_tres' id='si'></td>" +
								"<td><b>Haemophilus influenza tipo b:</b></td><td>&nbsp;&nbsp;&nbsp;1<input type='checkbox' name='haemophilus_uno' id='si'>2<input type='checkbox' name='haemophilus_dos' id='si'>3<input type='checkbox' name='haemophilus_tres' id='si'>R1<input type='checkbox' name='haemophilus_runo'>R2<input type='checkbox' name='haemophilus_rdos' id='si'></td></tr></table>");
						out.print("<table width='50%'><tr><td><b>Influenza: Última dosis:</b></td><td><input type='text' name='influenza_dosis' id='influenza_dosis'></td><td><b>Fiebre Amarilla: Edad</b></td><td><input type='text' size='1' name='fiebre_amarilla' id='fiebre_amarilla'></td></tr></table>");
						out.print("<table width='100%'><tr><td><br><b>OTRAS VACUNAS:</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='otras_vacunas' id='otras_vacunas'></textarea></td></tr></table>");
						out.print("<table width='80%'><tr><td>Vacunas pendientes:</td><td><input type='text' name='vacunas_pendientes' id='vacunas_pendientes'></td><td>Próximas vacunas:</td><td><input type='text' name='proximas_vacunas' id='proximas_vacunas'></td><td>A los</td><td><input type='text' size='1' name='tiempo_proxima_vacuna' id='tiempo_proxima_vacuna'> (meses-años)</td></tr></table>");

	/*EXAMEN FISICO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>EXÁMEN FÍSICO</b></td></tr></table>");
						out.print("<table width='100%'><tr><td><b>COMPLETAR EXAMEN FISICO</b></td></tr>" +
								"<tr><td><textarea rows='4' cols='135' name='obs_examen_fisico' id='obs_examen_fisico'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><td><b>OTRO PROBLEMA DETECTADO, DIAGNÓSTICO:</b></td></tr>" +
								"<tr><td><textarea rows='3' cols='135' name='otro_prob_detectado' id='otro_prob_detectado'></textarea></td></tr></table>");
						if (a > 6 ) {
							/*EVA ALIMENTACION*/
							out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>EVALUAR LA ALIMENTACIÓN DE TODOS LOS NIÑOS MENORES DE 2 AÑOS y los clasificados como ANEMIA y/o CUALQUIERA DE LAS ALTERACIONES DEL CRECIMIENTO</b></td></tr></table>");
							out.print("<table width='100%'><tr><td>¿Recibe leche materna?</td><td>Si<INPUT type='radio' name='rad_recibe_leche_mat' id='si' onclick='cuantas_veces_leche_mat.disabled=false;cuantas_veces_leche_mat.focus()'>No<INPUT type='radio' name='rad_recibe_leche_mat' id='no' onclick='cuantas_veces_leche_mat.disabled=true;'></td><td>¿Cuántas veces en 24 horas?</td><td><input type='text' name='cuantas_veces_leche_mat' id='cuantas_veces_leche_mat' disabled></td>" +
									"<td>¿Recibe pecho en la noche?</td><td>Si<INPUT type='radio' name='rad_pecho_noche' id='si' onclick='extrae_leche.disabled=false;extrae_leche.focus();guarda_administra_leche.disabled=false'>No<INPUT type='radio' name='rad_pecho_noche' id='no' onclick='extrae_leche.disabled=true;guarda_administra_leche.disabled=true'></td><td>¿Se extrae la leche?</td><td><input type='text' name='extrae_leche' id='extrae_leche' disabled></td></tr>" +
									"<tr><td>¿Cómo guarda y administra la leche?</td><td><input type='text' name='guarda_administra_leche' id='guarda_administra_leche' disabled></td>" +
									"<td>¿El menor de 6 meses recibe otra leche o alimentos?</td><td>Si<INPUT type='radio' name='rad_menor_seis_leche' id='si' onclick='cuales_leche_menor_seis.disabled=false;cuales_leche_menor_seis.focus();cuantas_veces_leche_menor_seis.disabled=false;con_que_recibe_alimento.disabled=false;quien_da_comer_alimento.disabled=false;' disabled>No<INPUT type='radio' name='rad_menor_seis_leche' id='no'onclick='cuales_leche_menor_seis.disabled=true;cuantas_veces_leche_menor_seis.disabled=true;con_que_recibe_alimento.disabled=true;quien_da_comer_alimento.disabled=true;' disabled></td>" +
									"<td>¿Cuáles?</td><td><input type='text' name='cuales_leche_menor_seis' id='cuales_leche_menor_seis' disabled></td><td>¿Cuántas veces?</td><td><input type='text' name='cuantas_veces_leche_menor_seis' id='cuantas_veces_leche_menor_seis' disabled></td></tr>" +
									"<tr><td>¿Con qué recibe el alimento?</td><td><input type='text' name='con_que_recibe_alimento' id='con_que_recibe_alimento' disabled></td><td>¿Quién le da de comer?</td><td><input type='text' name='quien_da_comer_alimento' id='quien_da_comer_alimento' disabled></tr></table>");
							out.print("<table width=100%><tr><td><br><b>EL NIÑO MAYOR DE 6 MESES RECIBE:</b></td></tr>" +
									"<tr><td>¿Cuántas comidas y meriendas recibió el día de ayer?</td><td><input type='text' name='cuantas_comidas_ayer' id='cuantas_comidas_ayer'></td><td>¿De qué tamaño son las porciones que recibió ayer?</td><td><input type='text' name='tamano_porciones_ayer' id='tamano_porciones_ayer'></td>" +
									"<td>¿Cuántas comidas de consistencia espesa recibió el día de ayer?</td><td><input type='text' name='cuantas_comidas_consistencia_ayer' id='cuantas_comidas_consistencia_ayer'></td><td>¿Comió alimentos de origen animal?</td><td><SELECT name='alimentos_origen_animal' id='alimentos_origen_animal'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Carne'>Carne</OPTION><OPTION value='Pescado'>Pescado</OPTION><OPTION value='Menudencias'>Menudencias</OPTION><OPTION value='Huevo'>Huevo</OPTION><OPTION value='No ha comido alimentos de origen animal'>No comió alimentos</OPTION></SELECT></td></tr>" +
									"<tr><td>¿Consumió ayer productos lacteos?</td><td><input type='text' name='productos_lacteos' id='productos_lacteos'></td><td>¿Comió legumbres o semillas ayer?</td><td><input type='text' name='comio_legumbres' id='comio_legumbres'></td>" +
									"<td>¿Comió vegetales o frutas de color rojo o anaranjado y hojas de color verde oscuro ayer?</td><td><input type='text' name='comio_vegetales' id='comio_vegetales'></td><td>¿Agregó una pequeña cantidad de aceite a la comida del niño ayer?</td><td><input type='text' name='cantidad_aceite' id='cantidad_aceite'></td></tr>" +
									"<tr><td>¿Quién le dio la comida ayer al niño?</td><td><input type='text' name='quien_da_comer_ayer_mayor_seis' id='quien_da_comer_ayer_mayor_seis'></td><td>¿El niño come de su propio plato o come de la olla o plato familiar?</td><td><input type='text' name='propio_plato' id='propio_plato'></td>" +
									"<td>¿El niño recibe alguna suplementación de vitaminas y minerales?</td><td><input type='text' name='suplementacion_vitaminas' id='suplementacion_vitaminas'></td></tr>" +
									"<tr><br><td><b>ESTA ENFERMO:</b></td><td>Si<INPUT type='radio' name='rad_esta_enfermo' id='si' onclick='que_comio_enfermedad.disabled=false;que_comio_enfermedad.focus()'>No<INPUT type='radio' name='rad_esta_enfermo' id='no' onclick='que_comio_enfermedad.disabled=true'></td>" +
									"<td>¿Qué ha comido durante la enfermedad?</td><td><input type='text' name='que_comio_enfermedad' id='que_comio_enfermedad' disabled></td></tr>" +
									"<tr><td><b>ES OBESO:</b></td><td>Si<INPUT type='radio' name='rad_es_obeso' id='si' onclick='padres_obesos.disabled=false;padres_obesos.focus();nino_hace_ejercicio.disabled=false;programa_nutricional.disabled=false'>No<INPUT type='radio' name='rad_es_obeso' id='no' onclick='padres_obesos.disabled=true;nino_hace_ejercicio.disabled=true;programa_nutricional.disabled=true'></td>" +
									"<td>¿Son los padres o hermanos obesos?</td><td><input type='text' name='padres_obesos' id='padres_obesos' disabled></td><td>¿El niño hace ejercicio?</td><td><input type='text' name='nino_hace_ejercicio' id='nino_hace_ejercicio' disabled></td><td>¿Está asistiendo a un programa nutricional?</td><td><input type='text' name='programa_nutricional' id='programa_nutricional' disabled></td></tr></table>");
							out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
									"<tr><td><textarea rows='3' cols='135' name='obs_alimentacion' id='obs_alimentacion'></textarea></td></tr></table>");
							out.print("<table width='100%'><tr><br><td><b>PROBLEMA DETECTADO</b></td></tr>" +
									"<tr><td><textarea rows='3' cols='135' name='prob_detectado_alimentacion' id='prob_detectado_alimentacion'></textarea></td></tr></table>");
							out.print("<table width='100%'><tr><br><td><b>RECOMENDACIONES:</b></td></tr>" +
									"<tr><td><textarea rows='3' cols='135' name='recomendaciones_alimentaciones' id='recomendaciones_alimentaciones'></textarea></td></tr></table>");
						} else {
							/*EVA ALIMENTACION*/
							out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>EVALUAR LA ALIMENTACIÓN DE TODOS LOS NIÑOS MENORES DE 2 AÑOS y los clasificados como ANEMIA y/o CUALQUIERA DE LAS ALTERACIONES DEL CRECIMIENTO</b></td></tr></table>");
							out.print("<table width='100%'><tr><td>¿Recibe leche materna?</td><td>Si<INPUT type='radio' name='rad_recibe_leche_mat' id='si' onclick='cuantas_veces_leche_mat.disabled=false;cuantas_veces_leche_mat.focus()'>No<INPUT type='radio' name='rad_recibe_leche_mat' id='no' onclick='cuantas_veces_leche_mat.disabled=true;'></td><td>¿Cuántas veces en 24 horas?</td><td><input type='text' name='cuantas_veces_leche_mat' id='cuantas_veces_leche_mat' disabled></td>" +
									"<td>¿Recibe pecho en la noche?</td><td>Si<INPUT type='radio' name='rad_pecho_noche' id='si' onclick='extrae_leche.disabled=false;extrae_leche.focus();guarda_administra_leche.disabled=false'>No<INPUT type='radio' name='rad_pecho_noche' id='no' onclick='extrae_leche.disabled=true;guarda_administra_leche.disabled=true'></td><td>¿Se extrae la leche?</td><td><input type='text' name='extrae_leche' id='extrae_leche' disabled></td></tr>" +
									"<tr><td>¿Cómo guarda y administra la leche?</td><td><input type='text' name='guarda_administra_leche' id='guarda_administra_leche' disabled></td><td>¿El menor de 6 meses recibe otra leche o alimentos?</td><td>Si<INPUT type='radio' name='rad_menor_seis_leche' id='si' onclick='cuales_leche_menor_seis.disabled=false;cuales_leche_menor_seis.focus();cuantas_veces_leche_menor_seis.disabled=false;con_que_recibe_alimento.disabled=false;quien_da_comer_alimento.disabled=false;'>No<INPUT type='radio' name='rad_menor_seis_leche' id='no'onclick='cuales_leche_menor_seis.disabled=true;cuantas_veces_leche_menor_seis.disabled=true;con_que_recibe_alimento.disabled=true;quien_da_comer_alimento.disabled=true;'></td>" +
									"<td>¿Cuáles?</td><td><input type='text' name='cuales_leche_menor_seis' id='cuales_leche_menor_seis' disabled></td><td>¿Cuántas veces?</td><td><input type='text' name='cuantas_veces_leche_menor_seis' id='cuantas_veces_leche_menor_seis' disabled></td></tr>" +
									"<tr><td>¿Con qué recibe el alimento?</td><td><input type='text' name='con_que_recibe_alimento' id='con_que_recibe_alimento' disabled></td><td>¿Quién le da de comer?</td><td><input type='text' name='quien_da_comer_alimento' id='quien_da_comer_alimento' disabled></tr></table>");
							out.print("<table width='100%' disabled><tr><td><br><b>EL NIÑO MAYOR DE 6 MESES RECIBE:</b></td></tr>" +
									"<tr><td>¿Cuántas comidas y meriendas recibió el día de ayer?</td><td><input type='text' name='cuantas_comidas_ayer' id='cuantas_comidas_ayer'></td><td>¿De qué tamaño son las porciones que recibió ayer?</td><td><input type='text' name='tamano_porciones_ayer' id='tamano_porciones_ayer'></td>" +
									"<td>¿Cuántas comidas de consistencia espesa recibió el día de ayer?</td><td><input type='text' name='cuantas_comidas_consistencia_ayer' id='cuantas_comidas_consistencia_ayer'></td><td>¿Comió alimentos de origen animal?</td><td><SELECT name='alimentos_origen_animal' id='alimentos_origen_animal'><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Carne'>Carne</OPTION><OPTION value='Pescado'>Pescado</OPTION><OPTION value='Menudencias'>Menudencias</OPTION><OPTION value='Huevo'>Huevo</OPTION><OPTION value='No ha comido alimentos de origen animal'>No comió alimentos</OPTION></SELECT></td></tr>" +
									"<tr><td>¿Consumió ayer productos lacteos?</td><td><input type='text' name='productos_lacteos' id='productos_lacteos'></td><td>¿Comió legumbres o semillas ayer?</td><td><input type='text' name='comio_legumbres' id='comio_legumbres'></td>" +
									"<td>¿Comió vegetales o frutas de color rojo o anaranjado y hojas de color verde oscuro ayer?</td><td><input type='text' name='comio_vegetales' id='comio_vegetales'></td><td>¿Agregó una pequeña cantidad de aceite a la comida del niño ayer?</td><td><input type='text' name='cantidad_aceite' id='cantidad_aceite'></td></tr>" +
									"<tr><td>¿Quién le dio la comida ayer al niño?</td><td><input type='text' name='quien_da_comer_ayer_mayor_seis' id='quien_da_comer_ayer_mayor_seis'></td><td>¿El niño come de su propio plato o come de la olla o plato familiar?</td><td><input type='text' name='propio_plato' id='propio_plato'></td>" +
									"<td>¿El niño recibe alguna suplementación de vitaminas y minerales?</td><td><input type='text' name='suplementacion_vitaminas' id='suplementacion_vitaminas'></td></tr>" +
									"<tr><br><td><b>ESTA ENFERMO:</b></td><td>Si<INPUT type='radio' name='rad_esta_enfermo' id='si' onclick='que_comio_enfermedad.disabled=false;que_comio_enfermedad.focus()'>No<INPUT type='radio' name='rad_esta_enfermo' id='no' onclick='que_comio_enfermedad.disabled=true'></td>" +
									"<td>¿Qué ha comido durante la enfermedad?</td><td><input type='text' name='que_comio_enfermedad' id='que_comio_enfermedad' disabled></td></tr>" +
									"<tr><td><b>ES OBESO:</b></td><td>Si<INPUT type='radio' name='rad_es_obeso' id='si' onclick='padres_obesos.disabled=false;padres_obesos.focus();nino_hace_ejercicio.disabled=false;programa_nutricional.disabled=false'>No<INPUT type='radio' name='rad_es_obeso' id='no' onclick='padres_obesos.disabled=true;nino_hace_ejercicio.disabled=true;programa_nutricional.disabled=true'></td>" +
									"<td>¿Son los padres o hermanos obesos?</td><td><input type='text' name='padres_obesos' id='padres_obesos' disabled></td><td>¿El niño hace ejercicio?</td><td><input type='text' name='nino_hace_ejercicio' id='nino_hace_ejercicio' disabled></td><td>¿Está asistiendo a un programa nutricional?</td><td><input type='text' name='programa_nutricional' id='programa_nutricional' disabled></td></tr></table>");
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
								"<td><INPUT type='text' readonly='' size='50' name='txtCodDiagnostico' id='txtCodDiagnostico'><input type='button' value='Ingresar' onclick='IngresarDxDosCinco()'></td> " +
								"</tr><tr><td></td><td><div id='SugeDiagnostico'></div></td><td></td><td></td></tr><tr><td colspan='4' id='dxAiepi'></td><td><input type='hidden' name='codPrincipalCIE' id='codPrincipalCIE' value='"+codigoPrincipalCIE+"' /></td></tr></table>");
						
						
						out.print("<table width='100%'><tr><td align='center'><b>TRATAMIENTO (Describa plan de tratamiento, medicamentos, dosis, tiempo y cualquier recomendación adicional necesaria)<b></td></tr>" +
								"<tr><td><textarea rows='5' cols='135' name='tratar_pac' id='tratar_pac'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><td><b>1.SIGNOS DE ALARMA:</b></td></tr>" +
								"<tr><td><textarea rows='3' cols='135' name='signos_alarma' id='signos_alarma'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><td><b>2.CUÁNDO VOLVER A CONSULTA DE CONTROL:</b></td>" +
								"<td><INPUT type='text' name='consulta_control' id='consulta_control'></td>" +
								"<td><b>DÓNDE:</b></td><td><INPUT type='text' name='donde_consulta_control' id='donde_consulta_control'></td>" +
								"<td><b>3.CUÁNDO VOLVER A CONSULTA DE NIÑO SANO O CRECIMIENTO Y DESARROLLO:</b></td><td><INPUT type='text' name='volver_nino_sano' id='volver_nino_sano'></td>" +
								"<td><b>4.REFERIDO A CONSULTA DE:</b></td><td><INPUT type='text' name='referido_consulta' id='referido_consulta'></td></tr></table>");
						out.print("<table width='100%'><tr><br><td><b>5.RECOMENDACIONES PARA EL DESARROLLO:</b></td></tr>" +
								"<tr><td><textarea rows='3' cols='135' name='recomendaciones_nino' id='recomendaciones_nino'></textarea></td></tr>" +
								"<tr><br><td><b>6.OTRAS RECOMENDACIONES DE BUEN TRATO:</b></td></tr>" +
								"<tr><td><textarea rows='3' cols='135' name='recomendaciones_buen_trato' id='recomendaciones_buen_trato'></textarea></td></tr></table>");
						out.print("<table width='100%'><tr><br><td><b>7.RECIBIÓ VITAMINA A EN LOS ÚLTIMOS 6 MESES:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibio_vitamina_a' id='si' onclick='prox_dosis_vitamina_a.disabled=false;prox_dosis_vitamina_a.focus()'><b>No</b><INPUT type='radio' name='rad_recibio_vitamina_a' id='no' onclick='prox_dosis_vitamina_a.disabled=true;'></td><td><b>PRÓXIMA DOSIS</b></td><td><input type='text' name='prox_dosis_vitamina_a' id='prox_dosis_vitamina_a' disabled></td></tr>" +
								"<tr><td><b>8.RECIBIÓ ALBENDAZOL EN LOS ÚLTIMOS 6 MESES:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibio_albendazol' id='si' onclick='prox_dosis_albendazol.disabled=false;prox_dosis_albendazol.focus()'><b>No</b><INPUT type='radio' name='rad_recibio_albendazol' id='no' onclick='prox_dosis_albendazol.disabled=true'></td><td><b>PRÓXIMA DOSIS</b></td><td><input type='text' name='prox_dosis_albendazol' id='prox_dosis_albendazol' disabled></td></tr>" +
								"<tr><td><b>9.RECIBIÓ HIERRO EN LOS ÚLTIMOS 6 MESES:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibio_hierro' id='si' onclick='cuando_hierro.disabled=false;cuando_hierro.focus();volver_recibir_hierro.disabled=false'><b>No</b><INPUT type='radio' name='rad_recibio_hierro' id='no' onclick='cuando_hierro.disabled=true;volver_recibir_hierro.disabled=true'></td><td><b>CUÁNDO</b></td><td><input type='text' name='cuando_hierro' id='cuando_hierro' disabled></td><td><b>DEBE VOLVER A RECIBIR EN:</b></td><td><input type='text' name='volver_recibir_hierro' id='volver_recibir_hierro' disabled></td></tr>" +
								"<tr><td><b>10.REQUIERE RECIBIR ZINC:</b></td><td><b>Si</b><INPUT type='radio' name='rad_recibir_zinc' id='si' onclick='cuanto_tiempo_zinc.disabled=false;cuanto_tiempo_zinc.focus();inicia_zinc.disabled=false'><b>No</b><INPUT type='radio' name='rad_recibir_zinc' id='no' onclick='cuanto_tiempo_zinc.disabled=true;inicia_zinc.disabled=true'></td><td><b>¿POR CUÁNTO TIEMPO?</b></td><td><input type='text' name='cuanto_tiempo_zinc' id='cuanto_tiempo_zinc' disabled></td><td><b>INICIA:</b></td><td><input type='text' name='inicia_zinc' id='inicia_zinc' disabled></td></table>");
						out.print("<table width='100%'><tr><td colspan='2'><div align='center'><br>" +
								"<INPUT id='guardar' type='button' value='Guardar Informe' onclick='GuardarAiepiDosMesesCincoAnos()' disabled>" +
								//"<INPUT id='anular' type='button' value='Anular Informe' onclick='AnularInforme()'>" +
								"</div></td></tr></table>");
						
					}else{
						rs4=mmp.VerificarDatosAdmision(CodPac,CodAdm);
						if(rs4.next()){
							//out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</div></td><td width='9%' style='color:#215b8b'>Identificacion</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(6)+" "+rs4.getString(7)+"</div><input name='CedPac' type='hidden' id='CedPac' value='"+rs4.getString(7)+"' /></td><td width='12%' style='color:#215b8b'>Fecha Nacimiento</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(5)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Edad:"+rs4.getString(4)+"</div></td></tr></table>");
							out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</div></td><td width='15%' style='color:#215b8b'>Identificacion:&nbsp;&nbsp;"+rs4.getString(6)+" "+rs4.getString(7)+"</td><input name='CedPac' type='hidden' id='CedPac' value='"+rs4.getString(7)+"' /></td><td width='15%' style='color:#215b8b'>Fecha Nacimiento:&nbsp;&nbsp;"+rs4.getString(5)+"</td><td style='color:#215b8b'>Edad:&nbsp;&nbsp;&nbsp;&nbsp; Días:"+rs4.getString("EdadDias")+"&nbsp;&nbsp;&nbsp;Meses:"+rs4.getString("EdadMeses")+"&nbsp;&nbsp;&nbsp;Años:"+rs4.getString("edad")+"</td></tr></table>");
							out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='5%' style='color:#215b8b'>Entidad:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(11)+"</div></td><td width='35%' style='color:#215b8b'><b>Medico Tratante:  "+rs4.getString(17)+"</b></td><td width='10%' style='color:#215b8b'>Servicio y Ubicacion:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(9)+" Cama "+rs4.getString(10)+"</div><input name='txtCodCama' type='hidden' id='txtCodCama' value="+rs4.getString(12)+"  /> <input name='txtCodSubarea' type='hidden' id='txtCodSubarea' value="+rs4.getString(14)+"  /> <input name='txtCodArea' type='hidden' id='txtCodArea' value="+rs4.getString(13)+"  /><input name='txtCodEntidad' type='hidden' id='txtCodEntidad' value="+rs4.getString(15)+"  /></td></tr></table>");
							out.print("<br>");			
							/**************menu de la izq*************/
							out.print("<table width='100%' border='1' cellspacing='0'><tr><td width='15%'><div id='MenuVertical'> <div id='button'><ul>");
							rs8=mmp.VerificarPermisosHC(usuario);
							if(rs8.next()){
								rs7=mmp.VerificarPermisosHC(usuario);
								while(rs7.next()){
									
									String genero="";
									rs=mmp.BuscarGeneroPaciente(CodPac);
									if (rs.next()) {
										genero=rs.getString(1);
									}
									rs.getStatement().getConnection().close();
									
									/*Condicion de Aiepi Embarazadas Por Genero*/
									if (genero.equals("Femenino")) {				
									
									
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
										out.print("<li><a href='#' onclick='Enfermeria("+CodAdm+",&quot;"+fechaK+"&quot;)'>Enfermeria</a></li>");
									}
									
									/**Habilitar para seguir trabajando(Se deshabilita para war)**/
									/*if(rs7.getString(1).equals("13")){
										//Permiso de Mostrar Aiepi Embarazadas
										out.print("<li><a href='#' onclick='MostrarAiepiEmbarazadas()'>Aiepi Embarazadas</a></li>");
									}*/
									
									if(rs7.getString(1).equals("14")){
										//Permiso de Consultar Aiepi
										out.print("<li><a href='#' onclick='ConsultarAiepiGeneral()'>Consultar Aiepi</a></li>");
									}
									
									} else {
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
											out.print("<li><a href='#' onclick='Enfermeria("+CodAdm+",&quot;"+fechaK+"&quot;)'>Enfermeria</a></li>");
										}
										
										/*if(rs7.getString(1).equals("13")){
											//Permiso de Mostrar Aiepi Embarazadas
											out.print("<li><a href='#' onclick='MostrarAiepiEmbarazadas()'>Aiepi Embarazadas</a></li>");
										}*/
										
										if(rs7.getString(1).equals("14")){
											//Permiso de Consultar Aiepi
											out.print("<li><a href='#' onclick='ConsultarAiepiGeneral()'>Consultar Aiepi</a></li>");
										}
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
							out.print("<tr class='style6'><td width='24%'><div align='center'>Alergias</div></td><td width='27%'><div align='center'>Medicamentos</div></td><td width='24%'><div align='center'>Laboratorios</div></td><td width='25%'><div align='center'>Imagenologia</div></td></tr>");
							out.print("<tr><td bordercolor='#FF0000' ><div id='ResumenAlergias'><table width='100%' border='1' >");
							
							//***************ALERGIAS PRINCIPALES****************************/
							rs=mmp.VerificarAlergiasLimite(CodPac);
							while(rs.next()){
							out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
							}
							out.print("<tr><td align='center' ><a href='#' onclick='Antecedentes()' >Mas Informacion Antecedentes</a></td></tr>");
							rs.getStatement().getConnection().close();
							//************FIN ALERGIAS PRINCIPALES***************************/
							
							out.print("</table></div></td><td bordercolor='#0033FF' ><div id='ResumenMedicamentos'><table width='100%' border='1' >");
							
							/***************MEDICAMENTOS************************************/
							rs5=mmp.VerificarMedicamentosLimite(CodPac);
							while(rs5.next()){
								out.print("<tr><td>"+rs5.getString(1)+"</td></tr>");
							}
							rs5.getStatement().getConnection().close();
							out.print("<tr><td>Ultimos Medicamentos >> <a href='#' onclick='MostrarMedicamentos()' >Mas Info</a></td></tr>");
							//*************FIN MEDICAMENTOS**********************************/
							
							out.print("</table></div></td><td bordercolor='#009900' ><div id='ResumenLaboratorios'><table width='100%' border='1' >");
							
							/**************ULTIMOS 5 LABORATORIOS****************************/
							rs6=mmp.VerificarLaboratoriosLimite(CodPac);
							while(rs6.next()){
								String Tipo=rs6.getString(10);
								String FechaIni=rs6.getString(1);
								String HoraIni=rs6.getString(2);
								String diaa,mess,anioo=null; 
								String horass,minutoss,segundoss=null;
								  
								diaa=FechaIni.substring(8,10);
								mess=FechaIni.substring(5,7);
								anioo=FechaIni.substring(0,4);
								
								horass=HoraIni.substring(0,2);
								minutoss =HoraIni.substring(3,5);
								segundoss=HoraIni.substring(6,8);
								String codgenero="1";
								if(Tipo.equals("1")){
									//**Laboratorios Tipo Texto**/
									out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+1+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
								}
								
								if(Tipo.equals("2")){
									/**Laboratorios Tipo Rango**/
									out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+2+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+","+rs6.getString(8)+","+rs6.getString(9)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
								}
								
								if(Tipo.equals("3")){
									/**Laboratorios En Grupo**/
									out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs6.getString(4)+","+rs6.getString(3)+","+codgenero+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
								}
							}
							rs6.getStatement().getConnection().close();
							out.print("<tr><td>Ultimos Laboratorios >> <a href='#' onclick='MenuLaboratorio()' >Mas Info</a> </td></tr>");
							//***********FIN ULTIMOS LABORATORIOS***************************/
							
							out.print("</table></div></td><td bordercolor='#CC6600'><div id='ResumenImagenologia'><table width='100%' border='1' >");
							
							/**********ULTIMOS 5 IMAGENES***********************************/
							ResultSet rsImgEco=null;
							ResultSet rsImgRmc=null;
							rs1=mmp.VerificarImagenesLimite(CodPac);
							rsImgEco=mmp.VerificarEcoLimite(CodPac);
							rsImgRmc=mmp.VerificarRmcLimite(CodPac);
							while(rs1.next()){
								out.print("<tr><td><a  href='#'onclick='mostarexamenes("+rs1.getString(4)+","+rs1.getString(5)+")'>"+rs1.getString(3)+"</a></td></tr>");
							}
							rs1.getStatement().getConnection().close();
							
							while(rsImgEco.next()){
								out.print("<tr><td><a  href='#'onclick='mostrarInformesCardiologia("+rsImgEco.getString(4)+")'>"+rsImgEco.getString(3)+"</a></td></tr>");
							}
							rsImgEco.getStatement().getConnection().close();
							
							while(rsImgRmc.next()){
								out.print("<tr><td><a  href='#'onclick='mostrarInformesRmc("+rsImgRmc.getString(4)+")'>"+rsImgRmc.getString(3)+"</a></td></tr>");
							}
							rsImgRmc.getStatement().getConnection().close();
							
							out.print("<tr><td align='center' >Ultimas Imagenes >> <a href='#' onclick='MostrarImagenologia()' >Mas Info</a></td></tr>");
							//rs1.getStatement().getConnection().close(); *Aqui estaba inicialmente*
							//**********FIN ULTIMOS 5 IMAGENES*******************************/
							
							out.print("</table></div></td></tr><tr class='style6' ><td><div align='center'>Formatos Activos</div></td><td><div align='center'>Ordenes Medicas</div></td><td colspan='2'><div align='center'>Imagenes Diagnosticas Pendientes</div> </td></tr><tr><td bordercolor='#FFFF00' ><div id='ResumenFormatosActivos'><table width='100%' border='1' >");
							
							/*************ULTIMOS 5 FORMATOS REALIZADOS********************/
							rs3=mmp.VerificarFormatosLimite(CodAdm, CodPac);
							while(rs3.next()){
								String FechaIni=rs3.getString(3);
								String HoraIni=rs3.getString(4);
								String diaa,mess,anioo=null; 
								String horass,minutoss,segundoss=null;
								  
								diaa=FechaIni.substring(8,10);
								mess=FechaIni.substring(5,7);
								anioo=FechaIni.substring(0,4);
								
								horass=HoraIni.substring(0,2);
								minutoss =HoraIni.substring(3,5);
								segundoss=HoraIni.substring(6,8);
								out.print("<tr><td><a  href='#' onclick='ImprimirFormato("+rs3.getString(7)+","+rs3.getString(1)+","+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs3.getString(8)+","+rs3.getString(11)+","+CodAdm+","+rs3.getString(10)+")'>"+rs3.getString(2)+"</a></td></tr>");
							}
							rs3.getStatement().getConnection().close();
							out.print("<tr><td align='center'><a href='#' onclick='Formatos()' >Mas Informacion Formatos</a></td></tr>");
							/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
							
							out.print("</table></div></td><td bordercolor='#000000' ><div id='resumenOrdenesMedicas'><table width='100%' border='1' >");
							
							/**************ULTIMAS 5 ORDENES MEDICAS*******************/
							rs2=mmp.VerificarOrdenesMedicasLimite(CodAdm);
							while(rs2.next()){
								
								String Tipo=rs2.getString(5);
								if(Tipo.equals("1")){
									/**1=LABORATORIO**/
									out.print("<tr><td><a href='#' onclick='ReportLabora("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>LABORATORIO</td></tr>");
								}
								
								if(Tipo.equals("2")){
									/**2=IMAGENOLOGIA**/
									out.print("<tr><td><a href='#' onclick='ReportImage("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>IMAGENOLOGIA</td></tr>");
								}
								
								if(Tipo.equals("3")){
									/**3=MEDICAMENTOS**/
									out.print("<tr><td><a href='#' onclick='ReportMedica("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>MEDICAMENTOS e INSUMOS</td></tr>");
								}
								
								if(Tipo.equals("4")){
									/**4=GENERAL**/
									out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>ORDEN GENERAL</td></tr>");

								}
							}
							out.print("<tr><td  align='center' ><a href='#' onclick='OrdenServicio()' >Mas Informacion Ordenes Medicas</a></td></tr>");
							rs2.getStatement().getConnection().close();
							/**********FIN ULTIMAS 5 ORDENES MEDICAS******************/
							
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
							/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
							out.print("</div></td>");

							out.print("</table></div></td></tr></table>");
							out.print("");
							out.print("");
							out.print("");
							out.print("");
							/**********FIN SNAPSHOT*******************/	
						}
						rs4.getStatement().getConnection().close();
					}
				}//Cierra Else Dos Cinco
				//rs10.getStatement().getConnection().close();
				}
				rs10.getStatement().getConnection().close();
			/*val*/	}
					rs9.getStatement().getConnection().close();			
			} catch (SQLException e) {
				System.out.println("Error en AiepiMultiplePacientes valor=2 "+e);
				e.printStackTrace();
			}
		}

		
		if (va.equals("RedireccionFormato")) {
			String CodAdm="";
			
			rs9=mmp.VerificarAdmision(CodPac);
			try {	
				if(rs9.next()){
					CodAdm=rs9.getString("adm_numero_ingreso");
				}
				rs9.getStatement().getConnection().close();
				
			rs4=mmp.VerificarDatosAdmision(CodPac,CodAdm);
			if(rs4.next()){
				out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='13%' style='color:#215b8b'>Historia Clinica De:</td><td width='40%' style='color:#215b8b'><div>"+rs4.getString(1)+" "+rs4.getString(2)+" "+rs4.getString(3)+"</div></td><td width='9%' style='color:#215b8b'>Identificacion</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(6)+" "+rs4.getString(7)+"</div><input name='CedPac' type='hidden' id='CedPac' value='"+rs4.getString(7)+"' /></td><td width='12%' style='color:#215b8b'>Fecha Nacimiento</td><td width='13%' style='color:#215b8b'><div>"+rs4.getString(5)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Edad:"+rs4.getString(4)+"</div></td></tr></table>");
				out.print("<table width='100%' border='1' cellspacing='0' bgcolor='#DADADA' bordercolor='#FFFFFF'><tr><td width='5%' style='color:#215b8b'>Entidad:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(11)+"</div></td><td width='35%' style='color:#215b8b'><b>Medico Tratante:  "+rs4.getString(17)+"</b></td><td width='10%' style='color:#215b8b'>Servicio y Ubicacion:</td><td width='25%' style='color:#215b8b'><div>"+rs4.getString(9)+" Cama "+rs4.getString(10)+"</div><input name='txtCodCama' type='hidden' id='txtCodCama' value="+rs4.getString(12)+"  /> <input name='txtCodSubarea' type='hidden' id='txtCodSubarea' value="+rs4.getString(14)+"  /> <input name='txtCodArea' type='hidden' id='txtCodArea' value="+rs4.getString(13)+"  /><input name='txtCodEntidad' type='hidden' id='txtCodEntidad' value="+rs4.getString(15)+"  /></td></tr></table>");
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
						
						/*if(rs7.getString(1).equals("13")){
							//Permiso de Mostrar Aiepi Embarazadas
							out.print("<li><a href='#' onclick='MostrarAiepiEmbarazadas()'>Aiepi Embarazadas</a></li>");
						}*/
						
						if(rs7.getString(1).equals("14")){
							//Permiso de Consultar Aiepi
							out.print("<li><a href='#' onclick='ConsultarAiepiGeneral()'>Consultar Aiepi</a></li>");
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
				out.print("<tr class='style6'><td width='24%'><div align='center'>Alergias</div></td><td width='27%'><div align='center'>Medicamentos</div></td><td width='24%'><div align='center'>Laboratorios</div></td><td width='25%'><div align='center'>Imagenologia</div></td></tr>");
				out.print("<tr><td bordercolor='#FF0000' ><div id='ResumenAlergias'><table width='100%' border='1' >");
				
				//***************ALERGIAS PRINCIPALES****************************/
				rs=mmp.VerificarAlergiasLimite(CodPac);
				while(rs.next()){
				out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
				}
				out.print("<tr><td align='center' ><a href='#' onclick='Antecedentes()' >Mas Informacion Antecedentes</a></td></tr>");
				rs.getStatement().getConnection().close();
				//************FIN ALERGIAS PRINCIPALES***************************/
				
				out.print("</table></div></td><td bordercolor='#0033FF' ><div id='ResumenMedicamentos'><table width='100%' border='1' >");
				
				/***************MEDICAMENTOS************************************/
				rs5=mmp.VerificarMedicamentosLimite(CodPac);
				while(rs5.next()){
					out.print("<tr><td>"+rs5.getString(1)+"</td></tr>");
				}
				rs5.getStatement().getConnection().close();
				out.print("<tr><td>Ultimos Medicamentos >> <a href='#' onclick='MostrarMedicamentos()' >Mas Info</a></td></tr>");
				//*************FIN MEDICAMENTOS**********************************/
				
				out.print("</table></div></td><td bordercolor='#009900' ><div id='ResumenLaboratorios'><table width='100%' border='1' >");
				
				/**************ULTIMOS 5 LABORATORIOS****************************/
				rs6=mmp.VerificarLaboratoriosLimite(CodPac);
				while(rs6.next()){
					String Tipo=rs6.getString(10);
					String FechaIni=rs6.getString(1);
					String HoraIni=rs6.getString(2);
					String diaa,mess,anioo=null; 
					String horass,minutoss,segundoss=null;
					  
					diaa=FechaIni.substring(8,10);
					mess=FechaIni.substring(5,7);
					anioo=FechaIni.substring(0,4);
					
					horass=HoraIni.substring(0,2);
					minutoss =HoraIni.substring(3,5);
					segundoss=HoraIni.substring(6,8);
					String codgenero="1";
					if(Tipo.equals("1")){
						//**Laboratorios Tipo Texto**/
						out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+1+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
					}
					
					if(Tipo.equals("2")){
						/**Laboratorios Tipo Rango**/
						out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir_ventana("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+2+","+rs6.getString(3)+","+rs6.getString(4)+","+rs6.getString(5)+","+rs6.getString(8)+","+rs6.getString(9)+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
					}
					
					if(Tipo.equals("3")){
						/**Laboratorios En Grupo**/
						out.print("<tr><td align='left'><DIV><a  href='#'onclick='Abrir("+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs6.getString(4)+","+rs6.getString(3)+","+codgenero+")'>"+rs6.getString(7)+"</a></DIV></td></tr>");
					}
				}
				rs6.getStatement().getConnection().close();
				out.print("<tr><td>Ultimos Laboratorios >> <a href='#' onclick='MenuLaboratorio()' >Mas Info</a> </td></tr>");
				//***********FIN ULTIMOS LABORATORIOS***************************/
				
				out.print("</table></div></td><td bordercolor='#CC6600'><div id='ResumenImagenologia'><table width='100%' border='1' >");
				
				/**********ULTIMOS 5 IMAGENES***********************************/
				ResultSet rsImgEco=null;
				ResultSet rsImgRmc=null;
				rs1=mmp.VerificarImagenesLimite(CodPac);
				rsImgEco=mmp.VerificarEcoLimite(CodPac);
				rsImgRmc=mmp.VerificarRmcLimite(CodPac);
				while(rs1.next()){
					out.print("<tr><td><a  href='#'onclick='mostarexamenes("+rs1.getString(4)+","+rs1.getString(5)+")'>"+rs1.getString(3)+"</a></td></tr>");
				}
				rs1.getStatement().getConnection().close();
				
				while(rsImgEco.next()){
					out.print("<tr><td><a  href='#'onclick='mostrarInformesCardiologia("+rsImgEco.getString(4)+")'>"+rsImgEco.getString(3)+"</a></td></tr>");
				}
				rsImgEco.getStatement().getConnection().close();
				
				while(rsImgRmc.next()){
					out.print("<tr><td><a  href='#'onclick='mostrarInformesRmc("+rsImgRmc.getString(4)+")'>"+rsImgRmc.getString(3)+"</a></td></tr>");
				}
				rsImgRmc.getStatement().getConnection().close();
				
				out.print("<tr><td align='center' >Ultimas Imagenes >> <a href='#' onclick='MostrarImagenologia()' >Mas Info</a></td></tr>");
				//rs1.getStatement().getConnection().close(); *Aqui estaba inicialmente*
				//**********FIN ULTIMOS 5 IMAGENES*******************************/
				
				out.print("</table></div></td></tr><tr class='style6' ><td><div align='center'>Formatos Activos</div></td><td><div align='center'>Ordenes Medicas</div></td><td colspan='2'><div align='center'>Imagenes Diagnosticas Pendientes</div> </td></tr><tr><td bordercolor='#FFFF00' ><div id='ResumenFormatosActivos'><table width='100%' border='1' >");
				
				/*************ULTIMOS 5 FORMATOS REALIZADOS********************/
				rs3=mmp.VerificarFormatosLimite(CodAdm, CodPac);
				while(rs3.next()){
					String FechaIni=rs3.getString(3);
					String HoraIni=rs3.getString(4);
					String diaa,mess,anioo=null; 
					String horass,minutoss,segundoss=null;
					  
					diaa=FechaIni.substring(8,10);
					mess=FechaIni.substring(5,7);
					anioo=FechaIni.substring(0,4);
					
					horass=HoraIni.substring(0,2);
					minutoss =HoraIni.substring(3,5);
					segundoss=HoraIni.substring(6,8);
					out.print("<tr><td><a  href='#' onclick='ImprimirFormato("+rs3.getString(7)+","+rs3.getString(1)+","+anioo+","+mess+","+diaa+","+horass+","+minutoss+","+segundoss+","+rs3.getString(8)+","+rs3.getString(11)+","+CodAdm+","+rs3.getString(10)+")'>"+rs3.getString(2)+"</a></td></tr>");
				}
				rs3.getStatement().getConnection().close();
				out.print("<tr><td align='center'><a href='#' onclick='Formatos()' >Mas Informacion Formatos</a></td></tr>");
				/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
				
				out.print("</table></div></td><td bordercolor='#000000' ><div id='resumenOrdenesMedicas'><table width='100%' border='1' >");
				
				/**************ULTIMAS 5 ORDENES MEDICAS*******************/
				rs2=mmp.VerificarOrdenesMedicasLimite(CodAdm);
				while(rs2.next()){
					
					String Tipo=rs2.getString(5);
					if(Tipo.equals("1")){
						/**1=LABORATORIO**/
						out.print("<tr><td><a href='#' onclick='ReportLabora("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>LABORATORIO</td></tr>");
					}
					
					if(Tipo.equals("2")){
						/**2=IMAGENOLOGIA**/
						out.print("<tr><td><a href='#' onclick='ReportImage("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>IMAGENOLOGIA</td></tr>");
					}
					
					if(Tipo.equals("3")){
						/**3=MEDICAMENTOS**/
						out.print("<tr><td><a href='#' onclick='ReportMedica("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>MEDICAMENTOS e INSUMOS</td></tr>");
					}
					
					if(Tipo.equals("4")){
						/**4=GENERAL**/
						out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs2.getString(1)+")' >"+rs2.getString(2)+"</a></td><td>ORDEN GENERAL</td></tr>");
						
					}
				}
				out.print("<tr><td  align='center' ><a href='#' onclick='OrdenServicio()' >Mas Informacion Ordenes Medicas</a></td></tr>");
				rs2.getStatement().getConnection().close();
				/**********FIN ULTIMAS 5 ORDENES MEDICAS******************/
				
				
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
				/*//*************FIN ULTIMOS 5 FORMATOS REALIZADOS****************/
				//fgj
				out.print("</div></td>");
				
				
				out.print("</table></div></td></tr></table>");
				out.print("");
				out.print("");
				out.print("");
				out.print("");
				
				/**********FIN SNAPSHOT*******************/
				
				
			}
			rs4.getStatement().getConnection().close();
		
		} catch (SQLException e) {
			System.out.println("Error en AiepiMultiplePacientes valor=RedireccionFormato "+e);
			e.printStackTrace();
		}
		}

String codigoPrincipalCIECeroDos="";
String codigoCIECeroDos=req.getParameter("DxAiepiCeroDos");
	rs9=mmp.ConsultarCodigoPrincipalCIE(codigoCIECeroDos);
		try {
			if (rs9.next()) {
				codigoPrincipalCIECeroDos=rs9.getString(1);
			}
			rs9.getStatement().getConnection().close();
		} catch (SQLException e2) {
			System.out.println("Error en AiepiMultiplePacientes en Insertar DxAiepiCeroDos "+e2);
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}		
	
String codigoPrincipalCIE="";
String codigoCIE=req.getParameter("DxAiepiDosCinco");
	rs9=mmp.ConsultarCodigoPrincipalCIE(codigoCIE);
		try {
			if (rs9.next()) {
				codigoPrincipalCIE=rs9.getString(1);
			}
			rs9.getStatement().getConnection().close();
		} catch (SQLException e2) {
			System.out.println("Error en AiepiMultiplePacientes en Insertar DxAiepiDosCinco "+e2);
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		

String CodPaciente=req.getParameter("CodPaciente");
String CodUsuario=req.getParameter("CodUsuario");
String CodAdmision=req.getParameter("CodAdmision");

String DxNombreCeroDos=req.getParameter("DxNombreCeroDos");
String DxAiepiCeroDos=req.getParameter("DxAiepiCeroDos");
String DxNombreDosCinco=req.getParameter("DxNombreDosCinco");
String DxAiepiDosCinco=req.getParameter("DxAiepiDosCinco");
//String codReporte=req.getParameter("txtCodReporte");

/**IngresarDxCeroDos**/
if (va.equals("IngresarDxCeroDos")) {
	
	rs10=mmp.PreguntarAdmisionCeroDos(CodAdmision);
	try {
		if (rs10.next()) {
			rs12=mmp.BuscarCodigoInformeCeroDos(CodAdmision);
			String CodForm="";
			try {
				if(rs12.next()){
					out.print(rs12.getString(1));
					CodForm=rs12.getString(1);
				}
				rs12.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mmp.insertarDxIngresoPaciente(codigoCIECeroDos, CodUsuario, hra, fechacjmysql, CodAdmision, CodPaciente, codigoPrincipalCIECeroDos);
			mmp.insertarNombreDxCeroDos(DxNombreCeroDos, DxAiepiCeroDos, CodForm);
		} else {
			mmp.insertarEncabezadoInformeAiepi(CodUsuario, CodPaciente, hra, fechacjmysql, CodAdmision);
			rs12=mmp.BuscarCodigoInformeCeroDos(CodAdmision);
			String CodForm="";
			try {
				if(rs12.next()){
					out.print(rs12.getString(1));
					CodForm=rs12.getString(1);
				}
				rs12.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mmp.insertarDxIngresoPaciente(codigoCIECeroDos, CodUsuario, hra, fechacjmysql, CodAdmision, CodPaciente, codigoPrincipalCIECeroDos);
			mmp.insertarNombreDxCeroDos(DxNombreCeroDos, DxAiepiCeroDos, CodForm);
		}
		rs10.getStatement().getConnection().close();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

/**IngresarDxDosCinco**/
if (va.equals("IngresarDxDosCinco")) {
	
	rs10=mmp.PreguntarAdmisionDosCinco(CodAdmision);
	try {
		if (rs10.next()) {
			rs12=mmp.BuscarCodigoInformeDosCinco(CodAdmision);
			String CodForm="";
			try {
				if(rs12.next()){
					out.print(rs12.getString(1));
					CodForm=rs12.getString(1);
				}
				rs12.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mmp.insertarDxIngresoPaciente(codigoCIE, CodUsuario, hra, fechacjmysql, CodAdmision, CodPaciente, codigoPrincipalCIE);
			mmp.insertarNombreDxDosCinco(DxNombreDosCinco, DxAiepiDosCinco, CodForm);
		} else {
			mmp.insertarEncabezadoInformeAiepiDosCinco(CodUsuario, CodPaciente, hra, fechacjmysql, CodAdmision);
			rs12=mmp.BuscarCodigoInformeDosCinco(CodAdmision);
			String CodForm="";
			try {
				if(rs12.next()){
					out.print(rs12.getString(1));
					CodForm=rs12.getString(1);
				}
				rs12.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mmp.insertarDxIngresoPaciente(codigoCIE, CodUsuario, hra, fechacjmysql, CodAdmision, CodPaciente, codigoPrincipalCIE);
			mmp.insertarNombreDxDosCinco(DxNombreDosCinco, DxAiepiDosCinco, CodForm);
		}
		rs10.getStatement().getConnection().close();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

/**MostrarDxCeroDos**/
if(va.equals("MostrarDxCeroDos")){
	
	rs4=mmp.buscarDxIngresoCeroDos(CodAdmision);
	try {
		while(rs4.next()){
			out.print("<table><tr><td></td><td>"+rs4.getString(1)+"</td><td></td><td>"+rs4.getString(2)+"</td><td><a href='#' onclick='EliminarDxCeroDos("+rs4.getString("codigo")+");EliminarDxAiepiCeroDos("+rs4.getString("id_nombredx")+")'>Omitir</a></td></tr></table>");
		}
		rs4.getStatement().getConnection().close();
	} catch (SQLException e) {
			System.out.println("ERROR EN VA EQUAL MostrarDxCeroDos AiepiMultiplePacientes "+e);
		e.printStackTrace();
	}
}

/**MostrarDxDosCinco**/
if(va.equals("MostrarDxDosCinco")){
	
	rs4=mmp.buscarDxIngreso(CodAdmision);
	try {
		while(rs4.next()){
			out.print("<table><tr><td></td><td>"+rs4.getString(1)+"</td><td></td><td>"+rs4.getString(2)+"</td><td><a href='#' onclick='EliminarDxDosCinco("+rs4.getString("codigo")+");EliminarDxAiepiDosCinco("+rs4.getString("id_nombredx")+")'>Omitir</a></td></tr></table>");
		}
		rs4.getStatement().getConnection().close();
	} catch (SQLException e) {
			System.out.println("ERROR EN VA EQUAL MostrarDxDosCinco AiepiMultiplePacientes "+e);
		e.printStackTrace();
	}
}

/**EliminarDxDosCinco**/
if(va.equals("EliminarDxCeroDos")){
	String codDxCeroDos=req.getParameter("codDxCeroDos");
	mmp.OmitirDxIngreso(codDxCeroDos);
}
/**EliminarDxAiepiCeroDos**/
if(va.equals("EliminarDxAiepiCeroDos")){
	String codAiepiCeroDos=req.getParameter("codAiepiCeroDos");
	mmp.OmitirDxIngresoAiepiCeroDos(codAiepiCeroDos);	
}

/**EliminarDxDosCinco**/
if(va.equals("EliminarDxDosCinco")){
	String codDx=req.getParameter("codDx");
	mmp.OmitirDxIngreso(codDx);
}
/**EliminarDxAiepiDosCinco**/
if(va.equals("EliminarDxAiepiDosCinco")){
	String codAiepi=req.getParameter("codAiepi");
	mmp.OmitirDxIngresoAiepi(codAiepi);	
}

String motConsulta=req.getParameter("motConsulta");
String antEmbarazo=req.getParameter("antEmbarazo");
String pesoNacer=req.getParameter("pesoNacer");
String tallaNacer=req.getParameter("tallaNacer");
String edadGestacional=req.getParameter("edadGestacional");
String hemoclasificacion=req.getParameter("hemoclasificacion");
String pesoActual=req.getParameter("pesoActual");
String talla=req.getParameter("talla");
String perimetroCefalico=req.getParameter("perimetroCefalico");
String frecuenciaCardiaca=req.getParameter("frecuenciaCardiaca");
String frecuenciaRespiratoria=req.getParameter("frecuenciaRespiratoria");
String temperatura=req.getParameter("temperatura");
String centigrados=req.getParameter("centigrados");
String radBebePecho=req.getParameter("radBebePecho");
String radFiebre=req.getParameter("radFiebre");
String radHipotermia=req.getParameter("radHipotermia");
String radConvulsiones=req.getParameter("radConvulsiones");
String radVomito=req.getParameter("radVomito");
String vomitaTodo=req.getParameter("vomitaTodo");
String radDiarrea=req.getParameter("radDiarrea");
String cuandoDiarrea=req.getParameter("cuandoDiarrea");
String radDificultadRespirar=req.getParameter("radDificultadRespirar");
String expliqueDificultadRespirar=req.getParameter("expliqueDificultadRespirar");
String radSangreHeces=req.getParameter("radSangreHeces");
String panalesOrinados=req.getParameter("panalesOrinados");

String ckbMueveSolo=req.getParameter("ckbMueveSolo");
String ckbLetargico=req.getParameter("ckbLetargico");
String ckbLuceMal=req.getParameter("ckbLuceMal");
String ckbIrritable=req.getParameter("ckbIrritable");
String ckbPalidez=req.getParameter("ckbPalidez");
String ckbCianosis=req.getParameter("ckbCianosis");
String ckbApneas=req.getParameter("ckbApneas");
String ckbBilirrubinas=req.getParameter("ckbBilirrubinas");
String ckbEstridor=req.getParameter("ckbEstridor");
String ckbFR=req.getParameter("ckbFR");
String ckbAleteoNasal=req.getParameter("ckbAleteoNasal");
String ckbQuejido=req.getParameter("ckbQuejido");
String ckbSibilancia=req.getParameter("ckbSibilancia");
String ckbFC=req.getParameter("ckbFC");
String ckbSupuracionOido=req.getParameter("ckbSupuracionOido");
String ckbTirajeSubcostal=req.getParameter("ckbTirajeSubcostal");
String ckbSecPurulentaConjuntival=req.getParameter("ckbSecPurulentaConjuntival");
String ckbEdemaPalpebral=req.getParameter("ckbEdemaPalpebral");
String ckbEritemaPeriumbilical=req.getParameter("ckbEritemaPeriumbilical");
String pustulasPiel=req.getParameter("pustulasPiel");
String ckbSecPurulentaOmbligo=req.getParameter("ckbSecPurulentaOmbligo");
String ckbEquimosis=req.getParameter("ckbEquimosis");
String ckbPetequias=req.getParameter("ckbPetequias");
String ckbPlacasBlanquecinas=req.getParameter("ckbPlacasBlanquecinas");
String ckbHemorragia=req.getParameter("ckbHemorragia");
String ckbLlenadoCapilar=req.getParameter("ckbLlenadoCapilar");
String ckbDistensionAbdominal=req.getParameter("ckbDistensionAbdominal");
String ckbFontanelaAbombada=req.getParameter("ckbFontanelaAbombada");
String ckbOjosHundidos=req.getParameter("ckbOjosHundidos");
String estadoGeneral=req.getParameter("estadoGeneral");
String pliegueCutaneo=req.getParameter("pliegueCutaneo");
/*Respuestas Enfermedad Grave o Infeccion Local*/
String ckbEnfermedadGrave=req.getParameter("ckbEnfermedadGrave");
String ckbInfeccionLocal=req.getParameter("ckbInfeccionLocal");
String ckbNoEnfNoInf=req.getParameter("ckbNoEnfNoInf");
String ckbDeshidratacion=req.getParameter("ckbDeshidratacion");
String ckbNoDeshidratacion=req.getParameter("ckbNoDeshidratacion");
String ckbDiarreaProlongada=req.getParameter("ckbDiarreaProlongada");
String ckbDiarreaSangre=req.getParameter("ckbDiarreaSangre");

String radDificultadAlimentarse=req.getParameter("radDificultadAlimentarse");
String cualDifAlimentarse=req.getParameter("cualDifAlimentarse");
String radDejadoComer=req.getParameter("radDejadoComer");
String desdeCuandoDejadoComer=req.getParameter("desdeCuandoDejadoComer");
String radLecheMaterna=req.getParameter("radLecheMaterna");
String formaExclusiva=req.getParameter("formaExclusiva");
String cuantasVecesFormaExclusiva=req.getParameter("cuantasVecesFormaExclusiva");
String radUtilizaChupo=req.getParameter("radUtilizaChupo");
String radOtraLeche=req.getParameter("radOtraLeche");
String cualesFrecuenciaOtraLeche=req.getParameter("cualesFrecuenciaOtraLeche");
String preparaOtraLeche=req.getParameter("preparaOtraLeche");
String queUtilizaAlimentarlo=req.getParameter("queUtilizaAlimentarlo");
String pesoEdad=req.getParameter("pesoEdad");
String pesoTalla=req.getParameter("pesoTalla");
//String perdidaPeso=req.getParameter("perdidaPeso");
String tendenciaPeso=req.getParameter("tendenciaPeso");

String ckbBocaAbierta=req.getParameter("ckbBocaAbierta");
String ckbTocaSeno=req.getParameter("ckbTocaSeno");
String ckbLabioInferior=req.getParameter("ckbLabioInferior");
String ckbAreolaLabio=req.getParameter("ckbAreolaLabio");
String ckbCabCuerpoDerecho=req.getParameter("ckbCabCuerpoDerecho");
String ckbDireccionPezon=req.getParameter("ckbDireccionPezon");
String ckbHijoFrenteMadre=req.getParameter("ckbHijoFrenteMadre");
String ckbMadreSostieneCuerpo=req.getParameter("ckbMadreSostieneCuerpo");
String ckbLentaProfunda=req.getParameter("ckbLentaProfunda");
/*Respuestas Alimentacion*/
String ckbProbSeveroAlimentacion=req.getParameter("ckbProbSeveroAlimentacion");
String ckbPesoMuyBajo=req.getParameter("ckbPesoMuyBajo");
String ckbProblemasAlimentacion=req.getParameter("ckbProblemasAlimentacion");
String ckbPesoBajo=req.getParameter("ckbPesoBajo");
String ckbPracticasAlimentacion=req.getParameter("ckbPracticasAlimentacion");	

String radParientesPadres=req.getParameter("radParientesPadres");
String radFamProblemaMental=req.getParameter("radFamProblemaMental");
String quienCuidaNino=req.getParameter("quienCuidaNino");
String desarrolloNino=req.getParameter("desarrolloNino");
String antNatales=req.getParameter("antNatales");
String alteracionFenitipica=req.getParameter("alteracionFenitipica");
String PC=req.getParameter("PC");
String PCE=req.getParameter("PCE");

String ckbReflejoMoro=req.getParameter("ckbReflejoMoro");
String ckbReflejoSuccion=req.getParameter("ckbReflejoSuccion");
String ckbReflejoCocleo=req.getParameter("ckbReflejoCocleo");
String ckbManosCerradas=req.getParameter("ckbManosCerradas");
String ckbBraPierFlexionadas=req.getParameter("ckbBraPierFlexionadas");
String ckbVocaliza=req.getParameter("ckbVocaliza");
String ckbSonrisaSocial=req.getParameter("ckbSonrisaSocial");
String ckbMovimientoPiernas=req.getParameter("ckbMovimientoPiernas");
String ckbSigueObjetos=req.getParameter("ckbSigueObjetos");
/*Respuestas Desarrollo*/
String ckbRetrasoDesarrollo=req.getParameter("ckbRetrasoDesarrollo");
String ckbRiesgoProblema=req.getParameter("ckbRiesgoProblema");
String ckbDesarrolloNormal=req.getParameter("ckbDesarrolloNormal"); 

String completarExamenFisico=req.getParameter("completarExamenFisico");
//String diagnosticoExamenFisico=req.getParameter("diagnosticoExamenFisico");
//String diagnosticoPac=req.getParameter("diagnosticoPac");
//String codigoCiePac=req.getParameter("codigoCiePac");
String tratarPac=req.getParameter("tratarPac");
String volverInmediato=req.getParameter("volverInmediato");
String recienNacido=req.getParameter("recienNacido");
String madre=req.getParameter("madre");
String ninoSano=req.getParameter("ninoSano");
String referidoConsulta=req.getParameter("referidoConsulta");
String programaVacunacion=req.getParameter("programaVacunacion");
String recomendacionesBuenTrato=req.getParameter("recomendacionesBuenTrato");
String otrasRecomendaciones=req.getParameter("otrasRecomendaciones");

		//Insercion DB AIEPI 0-2
		if(va.equals("2.1")){
			//mmp.insertarEncabezadoInformeAiepi(CodUsuario, CodPaciente, hra, fechacjmysql, CodAdmision);
			rs11=mmp.BuscarCodigoInformeCeroDos(CodAdmision);
			String CodFo="";
			try {
				if(rs11.next()){
					out.print(rs11.getString(1));
					CodFo=rs11.getString(1);
				}
				rs11.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mmp.insertarExamenFisico(motConsulta, antEmbarazo, pesoNacer, tallaNacer, edadGestacional, hemoclasificacion, pesoActual, talla, perimetroCefalico, frecuenciaCardiaca, frecuenciaRespiratoria, temperatura, centigrados, CodFo);
			mmp.insertarSignosEnfermedadUno(radBebePecho,radVomito,vomitaTodo,radDificultadRespirar,expliqueDificultadRespirar,radFiebre,radHipotermia,radConvulsiones,panalesOrinados,radDiarrea,cuandoDiarrea,radSangreHeces,CodFo);
			mmp.insertarSignosEnfermedadDos(ckbMueveSolo, ckbLetargico, ckbLuceMal, ckbIrritable, ckbPalidez, ckbCianosis, ckbBilirrubinas, ckbFR, ckbFC, CodFo);
			mmp.insertarSignosEnfermedadTres(ckbApneas, ckbAleteoNasal, ckbQuejido, ckbEstridor, ckbSibilancia, ckbTirajeSubcostal, ckbSupuracionOido, ckbSecPurulentaConjuntival, ckbEdemaPalpebral, pustulasPiel, CodFo);
			mmp.insertarSignosEnfermedadCuatro(ckbSecPurulentaOmbligo, ckbEritemaPeriumbilical, ckbPlacasBlanquecinas, ckbEquimosis, ckbPetequias, ckbHemorragia, ckbDistensionAbdominal, ckbLlenadoCapilar, ckbFontanelaAbombada, estadoGeneral, ckbOjosHundidos, pliegueCutaneo, CodFo);
			mmp.insertarSignosAlimentacionUno(radDificultadAlimentarse, cualDifAlimentarse, radDejadoComer, desdeCuandoDejadoComer, radLecheMaterna, formaExclusiva, cuantasVecesFormaExclusiva, radOtraLeche, cualesFrecuenciaOtraLeche, preparaOtraLeche, queUtilizaAlimentarlo, radUtilizaChupo, CodFo);
			mmp.insertarSignosAlimentacionDos(pesoEdad, pesoTalla, tendenciaPeso, CodFo);
			mmp.insertarSignosAlimentacionTres(ckbBocaAbierta, ckbTocaSeno, ckbLabioInferior, ckbAreolaLabio, ckbCabCuerpoDerecho, ckbDireccionPezon, ckbHijoFrenteMadre, ckbMadreSostieneCuerpo, ckbLentaProfunda, CodFo);
			mmp.insertarSignosDesarrolloUno(radParientesPadres, radFamProblemaMental, quienCuidaNino, desarrolloNino, antNatales, alteracionFenitipica, CodFo);
			mmp.insertarSignosDesarrolloDos(PC, PCE, ckbReflejoMoro, ckbReflejoSuccion, ckbReflejoCocleo, ckbBraPierFlexionadas, ckbManosCerradas, ckbVocaliza, ckbSonrisaSocial, ckbMovimientoPiernas, ckbSigueObjetos, CodFo);
			mmp.insertarDiagnosticos(completarExamenFisico, tratarPac, CodFo);
			mmp.insertarRecomendaciones(volverInmediato, recienNacido, madre, ninoSano, referidoConsulta, programaVacunacion, recomendacionesBuenTrato, otrasRecomendaciones, CodFo);
			mmp.insertarRespuestasEnfermedadGrave(ckbEnfermedadGrave, ckbInfeccionLocal, ckbNoEnfNoInf, ckbDeshidratacion, ckbNoDeshidratacion, ckbDiarreaProlongada, ckbDiarreaSangre, CodFo);
			mmp.insertarRespuestasAlimentacion(ckbProbSeveroAlimentacion, ckbPesoMuyBajo, ckbProblemasAlimentacion, ckbPesoBajo, ckbPracticasAlimentacion, CodFo);
			mmp.insertarRespuestasDesarrollo(ckbRetrasoDesarrollo, ckbRiesgoProblema, ckbDesarrolloNormal, CodFo);
		}

//AIEPI 2 MESES - 5 AÑOS
String motiConsulta=req.getParameter("motiConsulta");
String anteNatales=req.getParameter("anteNatales");
String APF=req.getParameter("APF");
String temperaturas=req.getParameter("temperaturas");
String frecuenciaCardiacas=req.getParameter("frecuenciaCardiacas");
String frecuenciaRespiratorias=req.getParameter("frecuenciaRespiratorias");
String tallas=req.getParameter("tallas");
String peso=req.getParameter("peso");
String perimetroCefalicos=req.getParameter("perimetroCefalicos");
String IMC=req.getParameter("IMC");
//Signos Peligro
String ckbNoBebePecho=req.getParameter("ckbNoBebePecho");
String ckbxLetargico=req.getParameter("ckbxLetargico");
String ckbVomitaTodo=req.getParameter("ckbVomitaTodo");
String ckbConvulsiones=req.getParameter("ckbConvulsiones");
String observacionesSignosPeligro=req.getParameter("observacionesSignosPeligro");
//Respuestas Signos Peligro
String ckbEnfMuyGrave=req.getParameter("ckbEnfMuyGrave");

//Tos
String radiDificultadRespirar=req.getParameter("radiDificultadRespirar");
String desdeCuandoTos=req.getParameter("desdeCuandoTos");
String radEpisodioSibilancias=req.getParameter("radEpisodioSibilancias");
String radSibilanciasRecurrentes=req.getParameter("radSibilanciasRecurrentes");
String radCuadroGripal=req.getParameter("radCuadroGripal");
String radAntecedentesPrematuridad=req.getParameter("radAntecedentesPrematuridad");

String respiracionesMinutos=req.getParameter("respiracionesMinutos");
String ckbRespiracionRapida=req.getParameter("ckbRespiracionRapida");
String ckbxTirajeSubcostal=req.getParameter("ckbxTirajeSubcostal");
String ckbSaturacionOxigeno=req.getParameter("ckbSaturacionOxigeno");
String ckbTirajeSupraclavicular=req.getParameter("ckbTirajeSupraclavicular");
String ckbxEstridor=req.getParameter("ckbxEstridor");
String ckbSibilancias=req.getParameter("ckbSibilancias");
String ckbApnea=req.getParameter("ckbApnea");
String ckbIncapacidadHablar=req.getParameter("ckbIncapacidadHablar");
String ckbSomnoliento=req.getParameter("ckbSomnoliento");
String ckbConfuso=req.getParameter("ckbConfuso");
String ckbAgitado=req.getParameter("ckbAgitado");
String observacionesTos=req.getParameter("observacionesTos");
//Respuestas Tos
String ckbGrupGrave=req.getParameter("ckbGrupGrave");
String ckbBronquiolitisGrave=req.getParameter("ckbBronquiolitisGrave");
String ckbSibilanciaGrave=req.getParameter("ckbSibilanciaGrave");
String ckbCrup=req.getParameter("ckbCrup");
String ckbxSibilancia=req.getParameter("ckbxSibilancia");
String ckbNeumoniaGrave=req.getParameter("ckbNeumoniaGrave");
String ckbNeumonia=req.getParameter("ckbNeumonia");
String ckbTos=req.getParameter("ckbTos");

//Diarrea
String radTieneDiarrea=req.getParameter("radTieneDiarrea");
String desdeCuandoDiarrea=req.getParameter("desdeCuandoDiarrea");
String radiSangreHeces=req.getParameter("radiSangreHeces");
String radiVomito=req.getParameter("radiVomito");
String numVomitos=req.getParameter("numVomitos");
String numDiarreasVC=req.getParameter("numDiarreasVC");
String numDiarreasC=req.getParameter("numDiarreasC");
String ckbComatoso=req.getParameter("ckbComatoso");
String ckbIntranquilo=req.getParameter("ckbIntranquilo");
String ckbxOjosHundidos=req.getParameter("ckbxOjosHundidos");
String ckbBebeMal=req.getParameter("ckbBebeMal");
String ckbBebeAvidamente=req.getParameter("ckbBebeAvidamente");
String pliegueCutaneos=req.getParameter("pliegueCutaneos");
String observacionesDiarrea=req.getParameter("observacionesDiarrea");
/*Respuestas Diarrea*/
String ckbDeshidratacionGrave=req.getParameter("ckbDeshidratacionGrave");
String ckbGradoDeshidratacion=req.getParameter("ckbGradoDeshidratacion");
String ckbAltoRiesgoDeshidratacion=req.getParameter("ckbAltoRiesgoDeshidratacion");
String ckbSinDeshidratacion=req.getParameter("ckbSinDeshidratacion");
String ckbDiarreaPersistenteGrave=req.getParameter("ckbDiarreaPersistenteGrave");
String ckbDiarreaPersistente=req.getParameter("ckbDiarreaPersistente");
String ckbDisenteria=req.getParameter("ckbDisenteria");

//Fiebre
String radTieneFiebre=req.getParameter("radTieneFiebre");
String desdeCuandoFiebre=req.getParameter("desdeCuandoFiebre");
String radFiebreTO=req.getParameter("radFiebreTO");
String radFiebreTN=req.getParameter("radFiebreTN");
String radViveQuinceDias=req.getParameter("radViveQuinceDias");
String ckbZonaDengue=req.getParameter("ckbZonaDengue");
String zonaMalaria=req.getParameter("zonaMalaria");

String ckbRigidezNuca=req.getParameter("ckbRigidezNuca");
String ckbAparienciaEnfermo=req.getParameter("ckbAparienciaEnfermo");
String ckbManifestacionesSangrado=req.getParameter("ckbManifestacionesSangrado");
String ckbAspectoToxico=req.getParameter("ckbAspectoToxico");
String respuestaSocial=req.getParameter("respuestaSocial");
String piel=req.getParameter("piel");
String ckbErupcionCutanea=req.getParameter("ckbErupcionCutanea");
String ckbDolorAbdominal=req.getParameter("ckbDolorAbdominal");
String ckbCefalea=req.getParameter("ckbCefalea");
String ckbMialgias=req.getParameter("ckbMialgias");
String ckbArtralgias=req.getParameter("ckbArtralgias");
String ckbDolorRetroocular=req.getParameter("ckbDolorRetroocular");
String ckbPostracion=req.getParameter("ckbPostracion");
String ckbPTorniquete=req.getParameter("ckbPTorniquete");
String ckbLipotimia=req.getParameter("ckbLipotimia");
String ckbHepatomegalia=req.getParameter("ckbHepatomegalia");
String ckbPulsoRapido=req.getParameter("ckbPulsoRapido");
String ckbxLlenadoCapilar=req.getParameter("ckbxLlenadoCapilar");
String ckbAscitis=req.getParameter("ckbAscitis");
String ckbDisminucionDiuresis=req.getParameter("ckbDisminucionDiuresis");
String ckbCuadroHematico=req.getParameter("ckbCuadroHematico");	
String ckbLeucocitos=req.getParameter("ckbLeucocitos");
String ckbNeutrofilos=req.getParameter("ckbNeutrofilos");
String ckbPlaquetas=req.getParameter("ckbPlaquetas");
String ckbParcialOrina=req.getParameter("ckbParcialOrina");
String ckbGotaGruesa=req.getParameter("ckbGotaGruesa");
String observacionesFiebre=req.getParameter("observacionesFiebre");
//Respuestas Fiebre
String ckbEnfFebrilRiesgo=req.getParameter("ckbEnfFebrilRiesgo");
String ckbEnfFebrilRiesgoIntermedio=req.getParameter("ckbEnfFebrilRiesgoIntermedio");
String ckbEnfFebrilRiesgoBajo=req.getParameter("ckbEnfFebrilRiesgoBajo");
String ckbMalariaComplicada=req.getParameter("ckbMalariaComplicada");
String ckbMalaria=req.getParameter("ckbMalaria");
String ckbDengueGrave=req.getParameter("ckbDengueGrave");
String ckbDengueSignosAlarma=req.getParameter("ckbDengueSignosAlarma");
String ckbProbableDengue=req.getParameter("ckbProbableDengue");

//Oido
String radTieneProbOido=req.getParameter("radTieneProbOido");
String radTieneDolorOido=req.getParameter("radTieneDolorOido");
String ckbTieneSupuracion=req.getParameter("ckbTieneSupuracion");
String desdeCuandoSupuracion=req.getParameter("desdeCuandoSupuracion");
String numEpisodiosPrevios=req.getParameter("numEpisodiosPrevios");

String ckbTumefaccionDolorosa=req.getParameter("ckbTumefaccionDolorosa"); 
String ckbTimpanoRojo=req.getParameter("ckbTimpanoRojo");
String ckbxSupuracionOido=req.getParameter("ckbxSupuracionOido");
String observacionesOido=req.getParameter("observacionesOido");
//Respuestas Oido
String ckbMastoiditis=req.getParameter("ckbMastoiditis");
String ckbOtitisMediaCronica=req.getParameter("ckbOtitisMediaCronica");
String ckbOtitisMediaRecurrente=req.getParameter("ckbOtitisMediaRecurrente");
String ckbOtitisMediaAguda=req.getParameter("ckbOtitisMediaAguda");
String ckbNoTieneOtitis=req.getParameter("ckbNoTieneOtitis");

//Garganta
String radTieneProbGarganta=req.getParameter("radTieneProbGarganta");
String radTieneDolorGarganta=req.getParameter("radTieneDolorGarganta");

String ckbGangliosCuello=req.getParameter("ckbGangliosCuello");
String ckbAmigdalasEritematosas=req.getParameter("ckbAmigdalasEritematosas");
String ckbExudadoBlanquecino=req.getParameter("ckbExudadoBlanquecino");
String observacionesGarganta=req.getParameter("observacionesGarganta");
//Respuestas Garganta
String ckbFaringoamigdalitis=req.getParameter("ckbFaringoamigdalitis");
String ckbEstreptococica=req.getParameter("ckbEstreptococica");
String ckbFaringoamigdalitisViral=req.getParameter("ckbFaringoamigdalitisViral");
String ckbNoTieneFaringoamigdalitis=req.getParameter("ckbNoTieneFaringoamigdalitis");

//Salud Bucal
String radTieneDolorComer=req.getParameter("radTieneDolorComer");
String radTieneDolorDiente=req.getParameter("radTieneDolorDiente");
String radTraumaCara=req.getParameter("radTraumaCara");
String radTieneCaries=req.getParameter("radTieneCaries");
String radLimpiaBocaManana=req.getParameter("radLimpiaBocaManana");
String radLimpiaBocaTarde=req.getParameter("radLimpiaBocaTarde");
String radLimpiaBocaNoche=req.getParameter("radLimpiaBocaNoche");
String radLimpiaDientes=req.getParameter("radLimpiaDientes");
String radNinoSolo=req.getParameter("radNinoSolo");
String radCepillo=req.getParameter("radCepillo");
String radCrema=req.getParameter("radCrema");
String radSeda=req.getParameter("radSeda");
String radiUtilizaChupo=req.getParameter("radiUtilizaChupo");
String ultimaConsultaOdontologica=req.getParameter("ultimaConsultaOdontologica");

String ckbInflamacionLabio=req.getParameter("ckbInflamacionLabio");
String ckbNoInvolucraSurco=req.getParameter("ckbNoInvolucraSurco");
String ckbEnrojecimiento=req.getParameter("ckbEnrojecimiento");
String ckbInflamacionEncia=req.getParameter("ckbInflamacionEncia");
String ckbLocalizado=req.getParameter("ckbLocalizado");
String ckbGeneralizado=req.getParameter("ckbGeneralizado");
String ckbDeformacionEncia=req.getParameter("ckbDeformacionEncia");	
String ckbExudadoPus=req.getParameter("ckbExudadoPus");
String ckbVesiculas=req.getParameter("ckbVesiculas");
String ckbUlceras=req.getParameter("ckbUlceras");
String placas=req.getParameter("placas");
String ckbFractura=req.getParameter("ckbFractura");
String ckbMovilidad=req.getParameter("ckbMovilidad");
String ckbDesplazamiento=req.getParameter("ckbDesplazamiento");
String ckbExtrusion=req.getParameter("ckbExtrusion");
String ckbIntrusion=req.getParameter("ckbIntrusion");
String ckbAvulsion=req.getParameter("ckbAvulsion");	
String herida=req.getParameter("herida");
String ckbManchasBlancas=req.getParameter("ckbManchasBlancas");
String ckbCafes=req.getParameter("ckbCafes");
String ckbCariesCavitacionales=req.getParameter("ckbCariesCavitacionales");
String ckbPlacaBacteriana=req.getParameter("ckbPlacaBacteriana");
String observacionesSaludBucal=req.getParameter("observacionesSaludBucal");
//Respuestas Salud Bucal
String ckbCelulitisFacial=req.getParameter("ckbCelulitisFacial");	
String ckbEnfBucalGrave=req.getParameter("ckbEnfBucalGrave");
String ckbTraumaBucodental=req.getParameter("ckbTraumaBucodental");
String ckbEstomatitis=req.getParameter("ckbEstomatitis");
String ckbEnfDental=req.getParameter("ckbEnfDental");
String ckbAltoRiesgoEnfBucal=req.getParameter("ckbAltoRiesgoEnfBucal");
String ckbBajoRiesgoEnfBucal=req.getParameter("ckbBajoRiesgoEnfBucal");

//Crecimiento
String radEmanacionVisible=req.getParameter("radEmanacionVisible");
String pesosEdad=req.getParameter("pesosEdad");
String radEdemaPies=req.getParameter("radEdemaPies");
String apariencia=req.getParameter("apariencia");
String IMCEdad=req.getParameter("IMCEdad");
String tallaEdad=req.getParameter("tallaEdad");
String pesosTalla=req.getParameter("pesosTalla");
String tendenciasPeso=req.getParameter("tendenciasPeso");

String ckbDesnutricionGlobalSevera=req.getParameter("ckbDesnutricionGlobalSevera");
String ckbDesnutricionGlobal=req.getParameter("ckbDesnutricionGlobal");
String ckbRiesgoDesnutricion=req.getParameter("ckbRiesgoDesnutricion");
String ckbPesoAdecuadoEdad=req.getParameter("ckbPesoAdecuadoEdad");
String ckbDesnutricionCronica=req.getParameter("ckbDesnutricionCronica");
String ckbRiesgoDNT=req.getParameter("ckbRiesgoDNT");
String ckbTallaAdecuadaEdad=req.getParameter("ckbTallaAdecuadaEdad");
String ckbDesnutricionAguda=req.getParameter("ckbDesnutricionAguda");
String ckbDNTAguda=req.getParameter("ckbDNTAguda");
String ckbPesoAdecuadoTalla=req.getParameter("ckbPesoAdecuadoTalla");
String ckbSobrepeso=req.getParameter("ckbSobrepeso");
String ckbObesidad=req.getParameter("ckbObesidad");
String observacionesCrecimiento=req.getParameter("observacionesCrecimiento");
//Respuestas Crecimiento
String ckbObeso=req.getParameter("ckbObeso");
String ckbSobrepesoR=req.getParameter("ckbSobrepesoR");
String ckbDesnutricionSevera=req.getParameter("ckbDesnutricionSevera");
String ckbDesnutricion=req.getParameter("ckbDesnutricion");
String ckbRiesgoDesnutricionR=req.getParameter("ckbRiesgoDesnutricionR");
String ckbEstadoNutricional=req.getParameter("ckbEstadoNutricional");

//Anemia
String radRecibidoHierro=req.getParameter("radRecibidoHierro");
String cuandoRecibidoHierro=req.getParameter("cuandoRecibidoHierro");
String cuantoTiempoRecibidoHierro=req.getParameter("cuantoTiempoRecibidoHierro");
String palidezPalmar=req.getParameter("palidezPalmar");	
String palidezConjuntival=req.getParameter("palidezConjuntival");
String observacionesAnemia=req.getParameter("observacionesAnemia");
//Respuestas Anemia
String ckbAnemiaSevera=req.getParameter("ckbAnemiaSevera");
String ckbAnemia=req.getParameter("ckbAnemia");
String ckbNoTieneAnemia=req.getParameter("ckbNoTieneAnemia");

//Maltrato
String produjeronLesiones=req.getParameter("produjeronLesiones");
String radRelataMaltrato=req.getParameter("radRelataMaltrato");
String ckbFisico=req.getParameter("ckbFisico");
String ckbSexual=req.getParameter("ckbSexual");
String ckbNegligencia=req.getParameter("ckbNegligencia");
String quien_maltrato=req.getParameter("quien_maltrato");
String radIncongruenciaTrauma=req.getParameter("radIncongruenciaTrauma");
String radExisteIncongruencia=req.getParameter("radExisteIncongruencia");
String diferentesVersiones=req.getParameter("diferentesVersiones");
String radTardiaConsulta=req.getParameter("radTardiaConsulta");
String frecuenciaPegar=req.getParameter("frecuenciaPegar");
String desobedienteHijo=req.getParameter("desobedienteHijo");
String comportamientoAnormalPadres=req.getParameter("comportamientoAnormalPadres");
String radDescuidadoNino=req.getParameter("radDescuidadoNino");
String porDescuidadoNino=req.getParameter("porDescuidadoNino");
String descuidadoNinoEn=req.getParameter("descuidadoNinoEn");
String factorRiesgo=req.getParameter("factorRiesgo");
String radActitudAnormal=req.getParameter("radActitudAnormal");
//Craneo
String ckbFracturas=req.getParameter("ckbFracturas");
String ckbHematomas=req.getParameter("ckbHematomas");
String ckbHemorragiasRetinianas=req.getParameter("ckbHemorragiasRetinianas");
//Quemaduras
String ckbAreasRopa=req.getParameter("ckbAreasRopa");	
String ckbPatronSimetrico=req.getParameter("ckbPatronSimetrico");
String ckbDenotaObjeto=req.getParameter("ckbDenotaObjeto");
String ckbEnEspalda=req.getParameter("ckbEnEspalda");

//Fracturas
String ckbCostillas=req.getParameter("ckbCostillas");
String ckbHuesosLargos=req.getParameter("ckbHuesosLargos");
String ckbEspirales=req.getParameter("ckbEspirales");
String ckbOblicua=req.getParameter("ckbOblicua");
String ckbMetafisiarias=req.getParameter("ckbMetafisiarias");
String ckbEsternon=req.getParameter("ckbEsternon");
String ckbEscapula=req.getParameter("ckbEscapula");
String ckbMenorCincoAnos=req.getParameter("ckbMenorCincoAnos");


//Trauma Genital
String ckbLasceracionAguda=req.getParameter("ckbLasceracionAguda");
String ckbLasceracionPerianal=req.getParameter("ckbLasceracionPerianal");
String ckbAusenciaHimen=req.getParameter("ckbAusenciaHimen");
String ckbHimenCicatrizado=req.getParameter("ckbHimenCicatrizado");
String ckbCicatrizNavicular=req.getParameter("ckbCicatrizNavicular");
String ckbAnoDilatado=req.getParameter("ckbAnoDilatado");
String ckbHalazgoSemen=req.getParameter("ckbHalazgoSemen");
String ckbFlujoGenital=req.getParameter("ckbFlujoGenital");
String ckbCuerpoExtrano=req.getParameter("ckbCuerpoExtrano");
String ckbVesiculasMaltrato=req.getParameter("ckbVesiculasMaltrato");


String ckbxEquimosis=req.getParameter("ckbxEquimosis");
String ckbHematomasMaltrato=req.getParameter("ckbHematomasMaltrato");
String ckbLasceraciones=req.getParameter("ckbLasceraciones");
String ckbMordiscos=req.getParameter("ckbMordiscos");
String ckbCicatrices=req.getParameter("ckbCicatrices");
String ckbDiferenteEvolucion=req.getParameter("ckbDiferenteEvolucion");
String ckbSugestivasMaltrato=req.getParameter("ckbSugestivasMaltrato");
String ckbTraumaVisceral=req.getParameter("ckbTraumaVisceral");
String ckbTraumaGrave=req.getParameter("ckbTraumaGrave");
String lesionesSugestival=req.getParameter("lesionesSugestival");
String ckbSangradoVaginal=req.getParameter("ckbSangradoVaginal");
String ckbJuegoSexual=req.getParameter("ckbJuegoSexual");
String ckbBocaGenitales=req.getParameter("ckbBocaGenitales");
String ckbVIH=req.getParameter("ckbVIH");
String ckbGonorrea=req.getParameter("ckbGonorrea");
String ckbSifilis=req.getParameter("ckbSifilis");	
String ckbTrichomonaVaginalis=req.getParameter("ckbTrichomonaVaginalis");
String ckbChlamydiaTrachomatis=req.getParameter("ckbChlamydiaTrachomatis");
String ckbCondilomatosis=req.getParameter("ckbCondilomatosis");
String ckbTemeroso=req.getParameter("ckbTemeroso");
String ckbRetraido=req.getParameter("ckbRetraido");
String ckbRechazoAdulto=req.getParameter("ckbRechazoAdulto");
String ckbDeprimido=req.getParameter("ckbDeprimido");
String ckbEvitaContactoVisual=req.getParameter("ckbEvitaContactoVisual");
String ckbTrastornoSueno=req.getParameter("ckbTrastornoSueno");
String ckbTrastornoAlimentario=req.getParameter("ckbTrastornoAlimentario");	
String ckbProblemasPsicomaticos=req.getParameter("ckbProblemasPsicomaticos");
String ckbConductasRegresivas=req.getParameter("ckbConductasRegresivas");
String ckbDesarrolloEstancado=req.getParameter("ckbDesarrolloEstancado");
String ckbViolenciaIntrafamiliar=req.getParameter("ckbViolenciaIntrafamiliar");
String ckbFamiliaCaotica=req.getParameter("ckbFamiliaCaotica");
String ckbCuidadoresAdictos=req.getParameter("ckbCuidadoresAdictos");
String observacionesMaltrato=req.getParameter("observacionesMaltrato");
//Respuestas Maltrato
String ckbMaltratoFisicoMuyGrave=req.getParameter("ckbMaltratoFisicoMuyGrave");
String ckbAbusoGrave=req.getParameter("ckbAbusoGrave");
String ckbMaltratoFisico=req.getParameter("ckbMaltratoFisico");
String ckbSospechaAbusoSexual=req.getParameter("ckbSospechaAbusoSexual");
String ckbMaltratoEmocional=req.getParameter("ckbMaltratoEmocional");
String ckbAbandono=req.getParameter("ckbAbandono");
String ckbNoSospechaMaltrato=req.getParameter("ckbNoSospechaMaltrato");

//Desarrollo
String radAntecedenteImportante=req.getParameter("radAntecedenteImportante");
String paraDesarrollo=req.getParameter("paraDesarrollo");
String algunFactorRiesgo=req.getParameter("algunFactorRiesgo");
String PCefalico=req.getParameter("PCefalico");
String AlteracionesFenotipicas=req.getParameter("AlteracionesFenotipicas");	
String ckbMenosDosDE=req.getParameter("ckbMenosDosDE");
String ckbMasDosDE=req.getParameter("ckbMasDosDE");
String observacionesDesarrollo=req.getParameter("observacionesDesarrollo");

String ckbRealizaCondEdadUno=req.getParameter("ckbRealizaCondEdadUno");
String ckbRealizaCondEdadDos=req.getParameter("ckbRealizaCondEdadDos");
String ckbRealizaCondEdadTres=req.getParameter("ckbRealizaCondEdadTres");
String ckbRealizaCondEdadCuatro=req.getParameter("ckbRealizaCondEdadCuatro");	
String ckbAusenciaCondEdadUno=req.getParameter("ckbAusenciaCondEdadUno");
String ckbAusenciaCondEdadDos=req.getParameter("ckbAusenciaCondEdadDos");
String ckbAusenciaCondEdadTres=req.getParameter("ckbAusenciaCondEdadTres");
String ckbAusenciaCondEdadCuatro=req.getParameter("ckbAusenciaCondEdadCuatro");
String ckbAusenciaCondGrupoAnteriorUno=req.getParameter("ckbAusenciaCondGrupoAnteriorUno");
String ckbAusenciaCondGrupoAnteriorDos=req.getParameter("ckbAusenciaCondGrupoAnteriorDos");
String ckbAusenciaCondGrupoAnteriorTres=req.getParameter("ckbAusenciaCondGrupoAnteriorTres");
String ckbAusenciaCondGrupoAnteriorCuatro=req.getParameter("ckbAusenciaCondGrupoAnteriorCuatro");

//Respuestas Desarrollo
String ckbProbRetrasoDesarrollo=req.getParameter("ckbProbRetrasoDesarrollo");
String ckbRiesgoProblemaDesarrollo=req.getParameter("ckbRiesgoProblemaDesarrollo");
String ckbDesarrolloNormalRiesgo=req.getParameter("ckbDesarrolloNormalRiesgo");
String ckbxDesarrolloNormal=req.getParameter("ckbxDesarrolloNormal");

//Antecedentes Vacunacion
String ckbBCGUno=req.getParameter("ckbBCGUno");
String ckbHepatitisBRN=req.getParameter("ckbHepatitisBRN");
String ckbHepatitisBUno=req.getParameter("ckbHepatitisBUno");
String ckbHepatitisBDos=req.getParameter("ckbHepatitisBDos");
String ckbHepatitisBTres=req.getParameter("ckbHepatitisBTres");
String ckbDPTUno=req.getParameter("ckbDPTUno");
String ckbDPTDos=req.getParameter("ckbDPTDos");	
String ckbDPTTres=req.getParameter("ckbDPTTres");
String ckbDPTRUno=req.getParameter("ckbDPTRUno");
String ckbDPTRDos=req.getParameter("ckbDPTRDos");
String ckbVOPUno=req.getParameter("ckbVOPUno");
String ckbVOPDos=req.getParameter("ckbVOPDos");
String ckbVOPTres=req.getParameter("ckbVOPTres");
String ckbVOPRUno=req.getParameter("ckbVOPRUno");
String ckbVOPRDos=req.getParameter("ckbVOPRDos");
String ckbSRPUno=req.getParameter("ckbSRPUno");
String ckbSRPDos=req.getParameter("ckbSRPDos");	
String ckbRotavirusUno=req.getParameter("ckbRotavirusUno");
String ckbRotavirusDos=req.getParameter("ckbRotavirusDos");
String ckbStreptococoNeumoniaeUno=req.getParameter("ckbStreptococoNeumoniaeUno");
String ckbStreptococoNeumoniaeDos=req.getParameter("ckbStreptococoNeumoniaeDos");
String ckbStreptococoNeumoniaeTres=req.getParameter("ckbStreptococoNeumoniaeTres");
String ckbHaemophilusUno=req.getParameter("ckbHaemophilusUno");
String ckbHaemophilusDos=req.getParameter("ckbHaemophilusDos");
String ckbHaemophilusTres=req.getParameter("ckbHaemophilusTres");
String ckbHaemophilusRUno=req.getParameter("ckbHaemophilusRUno");
String ckbHaemophilusRDos=req.getParameter("algunFactorRiesgo");
String influenzaDosis=req.getParameter("influenzaDosis");
String fiebreAmarilla=req.getParameter("fiebreAmarilla");
String otrasVacunas=req.getParameter("otrasVacunas");
String vacunasPendientes=req.getParameter("vacunasPendientes");	
String proximasVacunas=req.getParameter("proximasVacunas");
String tiempoProximaVacuna=req.getParameter("tiempoProximaVacuna");

//Completar Examen Fisico
String observacionesExamenFisico=req.getParameter("observacionesExamenFisico");
String otroProbDetectado=req.getParameter("otroProbDetectado");

//Alimentacion
String radiLecheMaterna=req.getParameter("radiLecheMaterna");
String cuantasVecesLecheMat=req.getParameter("cuantasVecesLecheMat");
String radPechoNoche=req.getParameter("radPechoNoche");
String extraeLeche=req.getParameter("extraeLeche");
String guardaAdministraLeche=req.getParameter("guardaAdministraLeche");
String radMenorSeisLeche=req.getParameter("radMenorSeisLeche");	
String cualesLecheMenorSeis=req.getParameter("cualesLecheMenorSeis");
String cuantasVecesLecheMenorSeis=req.getParameter("cuantasVecesLecheMenorSeis");
String conQueRecibeAlimento=req.getParameter("conQueRecibeAlimento");
String quienDaComerAlimento=req.getParameter("quienDaComerAlimento");
//Mayor Seis
String cuantasComidasAyer=req.getParameter("cuantasComidasAyer");
String tamanoPorcionesAyer=req.getParameter("tamanoPorcionesAyer");
String cuantasComidasConsistenciaAyer=req.getParameter("cuantasComidasConsistenciaAyer");
String alimentosOrigenAnimal=req.getParameter("alimentosOrigenAnimal");
String productosLacteos=req.getParameter("productosLacteos");
String comioLegumbres=req.getParameter("comioLegumbres");
String comioVegetales=req.getParameter("comioVegetales");	
String cantidadAceite=req.getParameter("cantidadAceite");
String quienDaComerAyerMayorSeis=req.getParameter("quienDaComerAyerMayorSeis");
String propioPlato=req.getParameter("propioPlato");
String suplementacionAlimentos=req.getParameter("suplementacionAlimentos");

String radEstaEnfermo=req.getParameter("radEstaEnfermo");
String queComioEnfermedad=req.getParameter("queComioEnfermedad");
String radEsObeso=req.getParameter("radEsObeso");
String padresObesos=req.getParameter("padresObesos");
String ninoHaceEjercicio=req.getParameter("ninoHaceEjercicio");
String programaNutricional=req.getParameter("programaNutricional");	
String observacionesAlimentacion=req.getParameter("observacionesAlimentacion");
String problemaDetectadoAlimentacion=req.getParameter("problemaDetectadoAlimentacion");
String recomendacionesAlimentaciones=req.getParameter("recomendacionesAlimentaciones");

//Recomendaciones
//String diagnosticoPaci=req.getParameter("diagnosticoPaci");
//String codigoPac=req.getParameter("codigoPac");
String tratamiento=req.getParameter("tratamiento");
String signosAlarma=req.getParameter("signosAlarma");
String consultaControl=req.getParameter("consultaControl");
String dondeConsultaControl=req.getParameter("dondeConsultaControl");
String volverNinoSano=req.getParameter("volverNinoSano");	
String referidoConsultas=req.getParameter("referidoConsultas");
String recomendacionesNino=req.getParameter("recomendacionesNino");
String recomendacionBuenTrato=req.getParameter("recomendacionBuenTrato");
String radRecVitaminaA=req.getParameter("radRecVitaminaA");
String proxDosisVitaminaA=req.getParameter("proxDosisVitaminaA");
String radRecAlbendazol=req.getParameter("radRecAlbendazol");
String proxDosisAlbendazol=req.getParameter("proxDosisAlbendazol");
String radRecHierro=req.getParameter("radRecHierro");
String cuandoHierro=req.getParameter("cuandoHierro");
String volverRecibirHierro=req.getParameter("volverRecibirHierro");	
String radRecZinc=req.getParameter("radRecZinc");
String cuantoTiempoZinc=req.getParameter("cuantoTiempoZinc");
String iniciaZinc=req.getParameter("iniciaZinc");

				//Insercion DB AIEPI 2-5
				if(va.equals("2.2")){
					//mmp.insertarEncabezadoInformeAiepiDosCinco(CodUsuario, CodPaciente, hra, fechacjmysql, CodAdmision);
					rs12=mmp.BuscarCodigoInformeDosCinco(CodAdmision);
					String CodFor="";
					try {
						if(rs12.next()){
							out.print(rs12.getString(1));
							CodFor=rs12.getString(1);
						}
						rs12.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					mmp.insertarExamenFisicoDosCinco(motiConsulta, anteNatales, APF, temperaturas, frecuenciaCardiacas, frecuenciaRespiratorias, tallas, peso, perimetroCefalicos, IMC, CodFor);
					mmp.insertarSignosPeligroDosCinco(ckbNoBebePecho, ckbxLetargico, ckbVomitaTodo, ckbConvulsiones, observacionesSignosPeligro, CodFor);
					mmp.insertarTosDosCincoUno(radiDificultadRespirar, desdeCuandoTos, radEpisodioSibilancias, radSibilanciasRecurrentes, radCuadroGripal, radAntecedentesPrematuridad, CodFor);
					mmp.insertarTosDosCincoDos(respiracionesMinutos, ckbRespiracionRapida, ckbxTirajeSubcostal, ckbSaturacionOxigeno, ckbTirajeSupraclavicular, ckbxEstridor, ckbSibilancias, ckbApnea, ckbIncapacidadHablar, ckbSomnoliento, ckbConfuso, ckbAgitado, observacionesTos, CodFor);
					mmp.insertarDiarrea(radTieneDiarrea, desdeCuandoDiarrea, radiSangreHeces, radiVomito, numVomitos, numDiarreasVC, numDiarreasC, CodFor);
					mmp.insertarDiarreaDos(ckbComatoso, ckbIntranquilo, ckbxOjosHundidos, ckbBebeMal, ckbBebeAvidamente, pliegueCutaneos, observacionesDiarrea, CodFor);
					mmp.insertarFiebre(radTieneFiebre, desdeCuandoFiebre, radFiebreTO, radFiebreTN,radViveQuinceDias, ckbZonaDengue, zonaMalaria, CodFor);
					mmp.insertarFiebreDos(ckbRigidezNuca, ckbAparienciaEnfermo, ckbManifestacionesSangrado, ckbAspectoToxico, respuestaSocial, piel, ckbErupcionCutanea, ckbDolorAbdominal, ckbCefalea, ckbMialgias, CodFor);
					mmp.insertarFiebreTres(ckbArtralgias, ckbDolorRetroocular, ckbPostracion, ckbPTorniquete, ckbLipotimia, ckbHepatomegalia, ckbPulsoRapido, ckbxLlenadoCapilar, ckbAscitis, ckbDisminucionDiuresis, CodFor);
					mmp.insertarFiebreCuatro(ckbCuadroHematico, ckbLeucocitos, ckbNeutrofilos, ckbPlaquetas, ckbParcialOrina, ckbGotaGruesa, observacionesFiebre, CodFor);
					mmp.insertarOido(radTieneProbOido, radTieneDolorOido, ckbTieneSupuracion, desdeCuandoSupuracion, numEpisodiosPrevios, ckbTumefaccionDolorosa, ckbTimpanoRojo, ckbxSupuracionOido, observacionesOido, CodFor);
					mmp.insertarGarganta(radTieneProbGarganta, radTieneDolorGarganta, ckbGangliosCuello, ckbAmigdalasEritematosas, ckbExudadoBlanquecino, observacionesGarganta, CodFor);
					mmp.insertarSaludBucal(radTieneDolorComer, radTieneDolorDiente, radTraumaCara, radTieneCaries, radLimpiaBocaManana, radLimpiaBocaTarde, radLimpiaBocaNoche, radLimpiaDientes, radNinoSolo, radCepillo, radCrema, radSeda, radiUtilizaChupo, ultimaConsultaOdontologica, CodFor);
					mmp.insertarSaludBucalDos(ckbInflamacionLabio, ckbNoInvolucraSurco, ckbEnrojecimiento, ckbInflamacionEncia, ckbLocalizado, ckbGeneralizado, ckbDeformacionEncia, ckbExudadoPus, ckbVesiculas, ckbUlceras, CodFor);
					mmp.insertarSaludBucalTres(placas, ckbFractura, ckbMovilidad, ckbDesplazamiento, ckbExtrusion, ckbIntrusion, ckbAvulsion, herida, ckbManchasBlancas, ckbCafes, ckbCariesCavitacionales, ckbPlacaBacteriana, observacionesSaludBucal, CodFor);
					mmp.insertarCrecimiento(radEmanacionVisible, pesosEdad, radEdemaPies, apariencia, IMCEdad, tallaEdad, pesosTalla, tendenciasPeso, CodFor);
					mmp.insertarCrecimientoDos(ckbDesnutricionGlobalSevera, ckbDesnutricionGlobal, ckbRiesgoDesnutricion, ckbPesoAdecuadoEdad, ckbDesnutricionCronica, ckbRiesgoDNT, ckbTallaAdecuadaEdad, ckbDesnutricionAguda, ckbDNTAguda, ckbPesoAdecuadoTalla, ckbSobrepeso, ckbObesidad, observacionesCrecimiento, CodFor);
					mmp.insertarAnemia(radRecibidoHierro, cuandoRecibidoHierro, cuantoTiempoRecibidoHierro, palidezPalmar, palidezConjuntival, observacionesAnemia, CodFor);
					mmp.insertarMaltrato(produjeronLesiones, radRelataMaltrato, ckbFisico, ckbSexual, ckbNegligencia, quien_maltrato, radIncongruenciaTrauma, radExisteIncongruencia, diferentesVersiones, CodFor);
					mmp.insertarMaltratoDos(radTardiaConsulta, frecuenciaPegar, desobedienteHijo, comportamientoAnormalPadres, radDescuidadoNino, porDescuidadoNino, descuidadoNinoEn, factorRiesgo, radActitudAnormal, CodFor);
					mmp.insertarMaltratoTres(ckbxEquimosis, ckbHematomasMaltrato, ckbLasceraciones, ckbMordiscos, ckbCicatrices, ckbDiferenteEvolucion, ckbSugestivasMaltrato, CodFor);
					mmp.insertarMaltratoCuatro(ckbTraumaVisceral, ckbTraumaGrave, lesionesSugestival, ckbSangradoVaginal, ckbJuegoSexual, ckbBocaGenitales, ckbVIH, ckbGonorrea, ckbSifilis, ckbTrichomonaVaginalis, ckbChlamydiaTrachomatis, ckbCondilomatosis, CodFor);
					mmp.insertarMaltratoCinco(ckbTemeroso, ckbRetraido, ckbRechazoAdulto, ckbDeprimido, ckbEvitaContactoVisual, ckbTrastornoSueno, ckbTrastornoAlimentario, ckbProblemasPsicomaticos, ckbConductasRegresivas, ckbDesarrolloEstancado, ckbViolenciaIntrafamiliar, ckbFamiliaCaotica, ckbCuidadoresAdictos, observacionesMaltrato, CodFor);
					mmp.insertarMaltratoCraneo(ckbFracturas, ckbHematomas, ckbHemorragiasRetinianas, CodFor);
					mmp.insertarMaltratoFracturas(ckbCostillas, ckbHuesosLargos, ckbEspirales, ckbOblicua, ckbMetafisiarias, ckbEsternon, ckbEscapula, ckbMenorCincoAnos, CodFor);
					mmp.insertarMaltratoQuemaduras(ckbAreasRopa, ckbPatronSimetrico, ckbDenotaObjeto, ckbEnEspalda, CodFor);
					mmp.insertarMaltratoTraumaGenital(ckbLasceracionAguda, ckbLasceracionPerianal, ckbAusenciaHimen, ckbHimenCicatrizado, ckbCicatrizNavicular, ckbAnoDilatado, ckbHalazgoSemen, ckbFlujoGenital, ckbCuerpoExtrano, ckbVesiculasMaltrato, CodFor);
					mmp.insertarDesarrollo(radAntecedenteImportante, paraDesarrollo, algunFactorRiesgo, PCefalico, AlteracionesFenotipicas, ckbMenosDosDE, ckbMasDosDE, observacionesDesarrollo, CodFor);
					mmp.insertarDesarrolloCondiciones(ckbRealizaCondEdadUno, ckbRealizaCondEdadDos, ckbRealizaCondEdadTres, ckbRealizaCondEdadCuatro, ckbAusenciaCondEdadUno, ckbAusenciaCondEdadDos, ckbAusenciaCondEdadTres, ckbAusenciaCondEdadCuatro, ckbAusenciaCondGrupoAnteriorUno, ckbAusenciaCondGrupoAnteriorDos, ckbAusenciaCondGrupoAnteriorTres, ckbAusenciaCondGrupoAnteriorCuatro, CodFor);
					mmp.insertarAntecedentesVacunacion(ckbBCGUno, ckbHepatitisBRN, ckbHepatitisBUno, ckbHepatitisBDos, ckbHepatitisBTres, ckbDPTUno, ckbDPTDos, ckbDPTTres, ckbDPTRUno, ckbDPTRDos, ckbVOPUno, ckbVOPDos, ckbVOPTres, ckbVOPRUno, ckbVOPRDos, CodFor);
					mmp.insertarAntecedentesVacunacionDos(ckbSRPUno, ckbSRPDos, ckbRotavirusUno, ckbRotavirusDos, ckbStreptococoNeumoniaeUno, ckbStreptococoNeumoniaeDos, ckbStreptococoNeumoniaeTres, ckbHaemophilusUno, ckbHaemophilusDos, ckbHaemophilusTres, ckbHaemophilusRUno, ckbHaemophilusRDos, CodFor);
					mmp.insertarAntecedentesVacunacionTres(influenzaDosis, fiebreAmarilla, otrasVacunas, vacunasPendientes, proximasVacunas, tiempoProximaVacuna, CodFor);
					mmp.insertarCompletarExamenFisico(observacionesExamenFisico, otroProbDetectado, CodFor);
					mmp.insertarAlimentacion(radiLecheMaterna, cuantasVecesLecheMat, radPechoNoche, extraeLeche, guardaAdministraLeche, radMenorSeisLeche, cualesLecheMenorSeis, cuantasVecesLecheMenorSeis, conQueRecibeAlimento, quienDaComerAlimento, CodFor);
					mmp.insertarAlimentacionDos(radEstaEnfermo, queComioEnfermedad, radEsObeso, padresObesos, ninoHaceEjercicio, programaNutricional, observacionesAlimentacion, problemaDetectadoAlimentacion, recomendacionesAlimentaciones, CodFor);
					mmp.insertarAlimentacionMayorSeisMeses(cuantasComidasAyer, tamanoPorcionesAyer, cuantasComidasConsistenciaAyer, alimentosOrigenAnimal, productosLacteos, comioLegumbres, comioVegetales, cantidadAceite, quienDaComerAyerMayorSeis, propioPlato, suplementacionAlimentos, CodFor);
					mmp.insertarRecomendacionesDosCinco(tratamiento, signosAlarma, consultaControl, dondeConsultaControl, volverNinoSano, referidoConsultas, recomendacionesNino, recomendacionBuenTrato, CodFor);
					mmp.insertarRecomendacionesDosis(radRecVitaminaA, proxDosisVitaminaA, radRecAlbendazol, proxDosisAlbendazol, radRecHierro, cuandoHierro, volverRecibirHierro, radRecZinc, cuantoTiempoZinc, iniciaZinc, CodFor);
					mmp.insertarRespuestasAnemia(ckbAnemiaSevera, ckbAnemia, ckbNoTieneAnemia, CodFor);
					mmp.insertarRespuestasCrecimiento(ckbObeso, ckbSobrepesoR, ckbDesnutricionSevera, ckbDesnutricion, ckbRiesgoDesnutricionR, ckbEstadoNutricional, CodFor);
					mmp.insertarRespuestasDesarrolloDosCinco(ckbProbRetrasoDesarrollo, ckbRiesgoProblemaDesarrollo, ckbDesarrolloNormalRiesgo, ckbxDesarrolloNormal, CodFor);
					mmp.insertarRespuestasDiarrea(ckbDeshidratacionGrave, ckbGradoDeshidratacion, ckbAltoRiesgoDeshidratacion, ckbSinDeshidratacion, ckbDiarreaPersistenteGrave, ckbDiarreaPersistente, ckbDisenteria, CodFor);
					mmp.insertarRespuestasFiebre(ckbEnfFebrilRiesgo, ckbEnfFebrilRiesgoIntermedio, ckbEnfFebrilRiesgoBajo, ckbMalariaComplicada, ckbMalaria, ckbDengueGrave, ckbDengueSignosAlarma, ckbProbableDengue, CodFor);
					mmp.insertarRespuestasGarganta(ckbFaringoamigdalitis, ckbEstreptococica, ckbFaringoamigdalitisViral, ckbNoTieneFaringoamigdalitis, CodFor);
					mmp.insertarRespuestasMaltrato(ckbMaltratoFisicoMuyGrave, ckbAbusoGrave, ckbMaltratoFisico, ckbSospechaAbusoSexual, ckbMaltratoEmocional, ckbAbandono, ckbNoSospechaMaltrato, CodFor);
					mmp.insertarRespuestasOido(ckbMastoiditis, ckbOtitisMediaCronica, ckbOtitisMediaRecurrente, ckbOtitisMediaAguda, ckbNoTieneOtitis, CodFor);
					mmp.insertarRespuestasSaludBucal(ckbCelulitisFacial, ckbEnfBucalGrave, ckbTraumaBucodental, ckbEstomatitis, ckbEnfDental, ckbAltoRiesgoEnfBucal, ckbBajoRiesgoEnfBucal, CodFor);
					mmp.insertarRespuestasSignosPeligro(ckbEnfMuyGrave, CodFor);
					mmp.insertarRespuestasTos(ckbGrupGrave, ckbBronquiolitisGrave, ckbSibilanciaGrave, ckbCrup, ckbxSibilancia, ckbNeumoniaGrave, ckbNeumonia, ckbTos, CodFor);
				}	
				
//AIEPI EMBARAZADAS
				/*String motiConsulta=req.getParameter("motiConsulta");
				String anteNatales=req.getParameter("anteNatales");
				String APF=req.getParameter("APF");
				String temperaturas=req.getParameter("temperaturas");
				String frecuenciaCardiacas=req.getParameter("frecuenciaCardiacas");
				String frecuenciaRespiratorias=req.getParameter("frecuenciaRespiratorias");
				String tallas=req.getParameter("tallas");
				String peso=req.getParameter("peso");
				String perimetroCefalicos=req.getParameter("perimetroCefalicos");
				String IMC=req.getParameter("IMC");*/
				
//Riesgo Gestacion
//String radContPrenatal=req.getParameter("radContPrenatal");
//String numControlPrenatal=req.getParameter("numControlPrenatal");
				/*String ckbVomitaTodo=req.getParameter("ckbVomitaTodo");
				String ckbConvulsiones=req.getParameter("ckbConvulsiones");
				String observacionesSignosPeligro=req.getParameter("observacionesSignosPeligro");
				//Respuestas Signos Peligro
				String ckbEnfMuyGrave=req.getParameter("ckbEnfMuyGrave");

				//Tos
				String radiDificultadRespirar=req.getParameter("radiDificultadRespirar");
				String desdeCuandoTos=req.getParameter("desdeCuandoTos");
				String radEpisodioSibilancias=req.getParameter("radEpisodioSibilancias");
				String radSibilanciasRecurrentes=req.getParameter("radSibilanciasRecurrentes");
				String radCuadroGripal=req.getParameter("radCuadroGripal");
				String radAntecedentesPrematuridad=req.getParameter("radAntecedentesPrematuridad");

				String respiracionesMinutos=req.getParameter("respiracionesMinutos");
				String ckbRespiracionRapida=req.getParameter("ckbRespiracionRapida");
				String ckbxTirajeSubcostal=req.getParameter("ckbxTirajeSubcostal");
				String ckbSaturacionOxigeno=req.getParameter("ckbSaturacionOxigeno");
				String ckbTirajeSupraclavicular=req.getParameter("ckbTirajeSupraclavicular");
				String ckbxEstridor=req.getParameter("ckbxEstridor");
				String ckbSibilancias=req.getParameter("ckbSibilancias");
				String ckbApnea=req.getParameter("ckbApnea");
				String ckbIncapacidadHablar=req.getParameter("ckbIncapacidadHablar");
				String ckbSomnoliento=req.getParameter("ckbSomnoliento");
				String ckbConfuso=req.getParameter("ckbConfuso");
				String ckbAgitado=req.getParameter("ckbAgitado");
				String observacionesTos=req.getParameter("observacionesTos");
				//Respuestas Tos
				String ckbGrupGrave=req.getParameter("ckbGrupGrave");
				String ckbBronquiolitisGrave=req.getParameter("ckbBronquiolitisGrave");
				String ckbSibilanciaGrave=req.getParameter("ckbSibilanciaGrave");
				String ckbCrup=req.getParameter("ckbCrup");
				String ckbxSibilancia=req.getParameter("ckbxSibilancia");
				String ckbNeumoniaGrave=req.getParameter("ckbNeumoniaGrave");
				String ckbNeumonia=req.getParameter("ckbNeumonia");
				String ckbTos=req.getParameter("ckbTos");

				//Diarrea
				String radTieneDiarrea=req.getParameter("radTieneDiarrea");
				String desdeCuandoDiarrea=req.getParameter("desdeCuandoDiarrea");
				String radiSangreHeces=req.getParameter("radiSangreHeces");
				String radiVomito=req.getParameter("radiVomito");
				String numVomitos=req.getParameter("numVomitos");
				String numDiarreasVC=req.getParameter("numDiarreasVC");
				String numDiarreasC=req.getParameter("numDiarreasC");
				String ckbComatoso=req.getParameter("ckbComatoso");
				String ckbIntranquilo=req.getParameter("ckbIntranquilo");
				String ckbxOjosHundidos=req.getParameter("ckbxOjosHundidos");
				String ckbBebeMal=req.getParameter("ckbBebeMal");
				String ckbBebeAvidamente=req.getParameter("ckbBebeAvidamente");
				String pliegueCutaneos=req.getParameter("pliegueCutaneos");
				String observacionesDiarrea=req.getParameter("observacionesDiarrea");
				/*Respuestas Diarrea*/
				/*String ckbDeshidratacionGrave=req.getParameter("ckbDeshidratacionGrave");
				String ckbGradoDeshidratacion=req.getParameter("ckbGradoDeshidratacion");
				String ckbAltoRiesgoDeshidratacion=req.getParameter("ckbAltoRiesgoDeshidratacion");
				String ckbSinDeshidratacion=req.getParameter("ckbSinDeshidratacion");
				String ckbDiarreaPersistenteGrave=req.getParameter("ckbDiarreaPersistenteGrave");
				String ckbDiarreaPersistente=req.getParameter("ckbDiarreaPersistente");
				String ckbDisenteria=req.getParameter("ckbDisenteria");*/

				//Fiebre
				/*String radTieneFiebre=req.getParameter("radTieneFiebre");
				String desdeCuandoFiebre=req.getParameter("desdeCuandoFiebre");
				String radFiebreTO=req.getParameter("radFiebreTO");
				String radFiebreTN=req.getParameter("radFiebreTN");
				String radViveQuinceDias=req.getParameter("radViveQuinceDias");
				String ckbZonaDengue=req.getParameter("ckbZonaDengue");
				String zonaMalaria=req.getParameter("zonaMalaria");

				String ckbRigidezNuca=req.getParameter("ckbRigidezNuca");
				String ckbAparienciaEnfermo=req.getParameter("ckbAparienciaEnfermo");
				String ckbManifestacionesSangrado=req.getParameter("ckbManifestacionesSangrado");
				String ckbAspectoToxico=req.getParameter("ckbAspectoToxico");
				String respuestaSocial=req.getParameter("respuestaSocial");
				String piel=req.getParameter("piel");
				String ckbErupcionCutanea=req.getParameter("ckbErupcionCutanea");
				String ckbDolorAbdominal=req.getParameter("ckbDolorAbdominal");
				String ckbCefalea=req.getParameter("ckbCefalea");
				String ckbMialgias=req.getParameter("ckbMialgias");
				String ckbArtralgias=req.getParameter("ckbArtralgias");
				String ckbDolorRetroocular=req.getParameter("ckbDolorRetroocular");
				String ckbPostracion=req.getParameter("ckbPostracion");
				String ckbPTorniquete=req.getParameter("ckbPTorniquete");
				String ckbLipotimia=req.getParameter("ckbLipotimia");
				String ckbHepatomegalia=req.getParameter("ckbHepatomegalia");
				String ckbPulsoRapido=req.getParameter("ckbPulsoRapido");
				String ckbxLlenadoCapilar=req.getParameter("ckbxLlenadoCapilar");
				String ckbAscitis=req.getParameter("ckbAscitis");
				String ckbDisminucionDiuresis=req.getParameter("ckbDisminucionDiuresis");
				String ckbCuadroHematico=req.getParameter("ckbCuadroHematico");	
				String ckbLeucocitos=req.getParameter("ckbLeucocitos");
				String ckbNeutrofilos=req.getParameter("ckbNeutrofilos");
				String ckbPlaquetas=req.getParameter("ckbPlaquetas");
				String ckbParcialOrina=req.getParameter("ckbParcialOrina");
				String ckbGotaGruesa=req.getParameter("ckbGotaGruesa");
				String observacionesFiebre=req.getParameter("observacionesFiebre");*/
				//Respuestas Fiebre
				/*String ckbEnfFebrilRiesgo=req.getParameter("ckbEnfFebrilRiesgo");
				String ckbEnfFebrilRiesgoIntermedio=req.getParameter("ckbEnfFebrilRiesgoIntermedio");
				String ckbEnfFebrilRiesgoBajo=req.getParameter("ckbEnfFebrilRiesgoBajo");
				String ckbMalariaComplicada=req.getParameter("ckbMalariaComplicada");
				String ckbMalaria=req.getParameter("ckbMalaria");
				String ckbDengueGrave=req.getParameter("ckbDengueGrave");
				String ckbDengueSignosAlarma=req.getParameter("ckbDengueSignosAlarma");
				String ckbProbableDengue=req.getParameter("ckbProbableDengue");*/

				//Oido
				/*String radTieneProbOido=req.getParameter("radTieneProbOido");
				String radTieneDolorOido=req.getParameter("radTieneDolorOido");
				String ckbTieneSupuracion=req.getParameter("ckbTieneSupuracion");
				String desdeCuandoSupuracion=req.getParameter("desdeCuandoSupuracion");
				String numEpisodiosPrevios=req.getParameter("numEpisodiosPrevios");

				String ckbTumefaccionDolorosa=req.getParameter("ckbTumefaccionDolorosa"); 
				String ckbTimpanoRojo=req.getParameter("ckbTimpanoRojo");
				String ckbxSupuracionOido=req.getParameter("ckbxSupuracionOido");
				String observacionesOido=req.getParameter("observacionesOido");*/
				//Respuestas Oido
				/*String ckbMastoiditis=req.getParameter("ckbMastoiditis");
				String ckbOtitisMediaCronica=req.getParameter("ckbOtitisMediaCronica");
				String ckbOtitisMediaRecurrente=req.getParameter("ckbOtitisMediaRecurrente");
				String ckbOtitisMediaAguda=req.getParameter("ckbOtitisMediaAguda");
				String ckbNoTieneOtitis=req.getParameter("ckbNoTieneOtitis");

				//Garganta
				/*String radTieneProbGarganta=req.getParameter("radTieneProbGarganta");
				String radTieneDolorGarganta=req.getParameter("radTieneDolorGarganta");

				String ckbGangliosCuello=req.getParameter("ckbGangliosCuello");
				String ckbAmigdalasEritematosas=req.getParameter("ckbAmigdalasEritematosas");
				String ckbExudadoBlanquecino=req.getParameter("ckbExudadoBlanquecino");
				String observacionesGarganta=req.getParameter("observacionesGarganta");*/
				//Respuestas Garganta
				/*String ckbFaringoamigdalitis=req.getParameter("ckbFaringoamigdalitis");
				String ckbEstreptococica=req.getParameter("ckbEstreptococica");
				String ckbFaringoamigdalitisViral=req.getParameter("ckbFaringoamigdalitisViral");
				String ckbNoTieneFaringoamigdalitis=req.getParameter("ckbNoTieneFaringoamigdalitis");*/

				//Salud Bucal
				/*String radTieneDolorComer=req.getParameter("radTieneDolorComer");
				String radTieneDolorDiente=req.getParameter("radTieneDolorDiente");
				String radTraumaCara=req.getParameter("radTraumaCara");
				String radTieneCaries=req.getParameter("radTieneCaries");
				String radLimpiaBocaManana=req.getParameter("radLimpiaBocaManana");
				String radLimpiaBocaTarde=req.getParameter("radLimpiaBocaTarde");
				String radLimpiaBocaNoche=req.getParameter("radLimpiaBocaNoche");
				String radLimpiaDientes=req.getParameter("radLimpiaDientes");
				String radNinoSolo=req.getParameter("radNinoSolo");
				String radCepillo=req.getParameter("radCepillo");
				String radCrema=req.getParameter("radCrema");
				String radSeda=req.getParameter("radSeda");
				String radiUtilizaChupo=req.getParameter("radiUtilizaChupo");
				String ultimaConsultaOdontologica=req.getParameter("ultimaConsultaOdontologica");

				String ckbInflamacionLabio=req.getParameter("ckbInflamacionLabio");
				String ckbNoInvolucraSurco=req.getParameter("ckbNoInvolucraSurco");
				String ckbEnrojecimiento=req.getParameter("ckbEnrojecimiento");
				String ckbInflamacionEncia=req.getParameter("ckbInflamacionEncia");
				String ckbLocalizado=req.getParameter("ckbLocalizado");
				String ckbGeneralizado=req.getParameter("ckbGeneralizado");
				String ckbDeformacionEncia=req.getParameter("ckbDeformacionEncia");	
				String ckbExudadoPus=req.getParameter("ckbExudadoPus");
				String ckbVesiculas=req.getParameter("ckbVesiculas");
				String ckbUlceras=req.getParameter("ckbUlceras");
				String placas=req.getParameter("placas");
				String ckbFractura=req.getParameter("ckbFractura");
				String ckbMovilidad=req.getParameter("ckbMovilidad");
				String ckbDesplazamiento=req.getParameter("ckbDesplazamiento");
				String ckbExtrusion=req.getParameter("ckbExtrusion");
				String ckbIntrusion=req.getParameter("ckbIntrusion");
				String ckbAvulsion=req.getParameter("ckbAvulsion");	
				String herida=req.getParameter("herida");
				String ckbManchasBlancas=req.getParameter("ckbManchasBlancas");
				String ckbCafes=req.getParameter("ckbCafes");
				String ckbCariesCavitacionales=req.getParameter("ckbCariesCavitacionales");
				String ckbPlacaBacteriana=req.getParameter("ckbPlacaBacteriana");
				String observacionesSaludBucal=req.getParameter("observacionesSaludBucal");*/
				//Respuestas Salud Bucal
				/*String ckbCelulitisFacial=req.getParameter("ckbCelulitisFacial");	
				String ckbEnfBucalGrave=req.getParameter("ckbEnfBucalGrave");
				String ckbTraumaBucodental=req.getParameter("ckbTraumaBucodental");
				String ckbEstomatitis=req.getParameter("ckbEstomatitis");
				String ckbEnfDental=req.getParameter("ckbEnfDental");
				String ckbAltoRiesgoEnfBucal=req.getParameter("ckbAltoRiesgoEnfBucal");
				String ckbBajoRiesgoEnfBucal=req.getParameter("ckbBajoRiesgoEnfBucal");*/

				//Crecimiento
				/*String radEmanacionVisible=req.getParameter("radEmanacionVisible");
				String pesosEdad=req.getParameter("pesosEdad");
				String radEdemaPies=req.getParameter("radEdemaPies");
				String apariencia=req.getParameter("apariencia");
				String IMCEdad=req.getParameter("IMCEdad");
				String tallaEdad=req.getParameter("tallaEdad");
				String pesosTalla=req.getParameter("pesosTalla");
				String tendenciasPeso=req.getParameter("tendenciasPeso");

				String ckbDesnutricionGlobalSevera=req.getParameter("ckbDesnutricionGlobalSevera");
				String ckbDesnutricionGlobal=req.getParameter("ckbDesnutricionGlobal");
				String ckbRiesgoDesnutricion=req.getParameter("ckbRiesgoDesnutricion");
				String ckbPesoAdecuadoEdad=req.getParameter("ckbPesoAdecuadoEdad");
				String ckbDesnutricionCronica=req.getParameter("ckbDesnutricionCronica");
				String ckbRiesgoDNT=req.getParameter("ckbRiesgoDNT");
				String ckbTallaAdecuadaEdad=req.getParameter("ckbTallaAdecuadaEdad");
				String ckbDesnutricionAguda=req.getParameter("ckbDesnutricionAguda");
				String ckbDNTAguda=req.getParameter("ckbDNTAguda");
				String ckbPesoAdecuadoTalla=req.getParameter("ckbPesoAdecuadoTalla");
				String ckbSobrepeso=req.getParameter("ckbSobrepeso");
				String ckbObesidad=req.getParameter("ckbObesidad");
				String observacionesCrecimiento=req.getParameter("observacionesCrecimiento");*/
				//Respuestas Crecimiento
				/*String ckbObeso=req.getParameter("ckbObeso");
				String ckbSobrepesoR=req.getParameter("ckbSobrepesoR");
				String ckbDesnutricionSevera=req.getParameter("ckbDesnutricionSevera");
				String ckbDesnutricion=req.getParameter("ckbDesnutricion");
				String ckbRiesgoDesnutricionR=req.getParameter("ckbRiesgoDesnutricionR");
				String ckbEstadoNutricional=req.getParameter("ckbEstadoNutricional");*/

				//Anemia
				/*String radRecibidoHierro=req.getParameter("radRecibidoHierro");
				String cuandoRecibidoHierro=req.getParameter("cuandoRecibidoHierro");
				String cuantoTiempoRecibidoHierro=req.getParameter("cuantoTiempoRecibidoHierro");
				String palidezPalmar=req.getParameter("palidezPalmar");	
				String palidezConjuntival=req.getParameter("palidezConjuntival");
				String observacionesAnemia=req.getParameter("observacionesAnemia");*/
				//Respuestas Anemia
				/*String ckbAnemiaSevera=req.getParameter("ckbAnemiaSevera");
				String ckbAnemia=req.getParameter("ckbAnemia");
				String ckbNoTieneAnemia=req.getParameter("ckbNoTieneAnemia");*/

				//Maltrato
				/*String produjeronLesiones=req.getParameter("produjeronLesiones");
				String radRelataMaltrato=req.getParameter("radRelataMaltrato");
				String ckbFisico=req.getParameter("ckbFisico");
				String ckbSexual=req.getParameter("ckbSexual");
				String ckbNegligencia=req.getParameter("ckbNegligencia");
				String quien_maltrato=req.getParameter("quien_maltrato");
				String radIncongruenciaTrauma=req.getParameter("radIncongruenciaTrauma");
				String radExisteIncongruencia=req.getParameter("radExisteIncongruencia");
				String diferentesVersiones=req.getParameter("diferentesVersiones");
				String radTardiaConsulta=req.getParameter("radTardiaConsulta");
				String frecuenciaPegar=req.getParameter("frecuenciaPegar");
				String desobedienteHijo=req.getParameter("desobedienteHijo");
				String comportamientoAnormalPadres=req.getParameter("comportamientoAnormalPadres");
				String radDescuidadoNino=req.getParameter("radDescuidadoNino");
				String porDescuidadoNino=req.getParameter("porDescuidadoNino");
				String descuidadoNinoEn=req.getParameter("descuidadoNinoEn");
				String factorRiesgo=req.getParameter("factorRiesgo");
				String radActitudAnormal=req.getParameter("radActitudAnormal");*/
				//Craneo
				/*String ckbFracturas=req.getParameter("ckbFracturas");
				String ckbHematomas=req.getParameter("ckbHematomas");
				String ckbHemorragiasRetinianas=req.getParameter("ckbHemorragiasRetinianas");*/
				//Quemaduras
				/*String ckbAreasRopa=req.getParameter("ckbAreasRopa");	
				String ckbPatronSimetrico=req.getParameter("ckbPatronSimetrico");
				String ckbDenotaObjeto=req.getParameter("ckbDenotaObjeto");
				String ckbEnEspalda=req.getParameter("ckbEnEspalda");*/

				//Fracturas
				/*String ckbCostillas=req.getParameter("ckbCostillas");
				String ckbHuesosLargos=req.getParameter("ckbHuesosLargos");
				String ckbEspirales=req.getParameter("ckbEspirales");
				String ckbOblicua=req.getParameter("ckbOblicua");
				String ckbMetafisiarias=req.getParameter("ckbMetafisiarias");
				String ckbEsternon=req.getParameter("ckbEsternon");
				String ckbEscapula=req.getParameter("ckbEscapula");
				String ckbMenorCincoAnos=req.getParameter("ckbMenorCincoAnos");*/


				//Trauma Genital
				/*String ckbLasceracionAguda=req.getParameter("ckbLasceracionAguda");
				String ckbLasceracionPerianal=req.getParameter("ckbLasceracionPerianal");
				String ckbAusenciaHimen=req.getParameter("ckbAusenciaHimen");
				String ckbHimenCicatrizado=req.getParameter("ckbHimenCicatrizado");
				String ckbCicatrizNavicular=req.getParameter("ckbCicatrizNavicular");
				String ckbAnoDilatado=req.getParameter("ckbAnoDilatado");
				String ckbHalazgoSemen=req.getParameter("ckbHalazgoSemen");
				String ckbFlujoGenital=req.getParameter("ckbFlujoGenital");
				String ckbCuerpoExtrano=req.getParameter("ckbCuerpoExtrano");
				String ckbVesiculasMaltrato=req.getParameter("ckbVesiculasMaltrato");


				String ckbxEquimosis=req.getParameter("ckbxEquimosis");
				String ckbHematomasMaltrato=req.getParameter("ckbHematomasMaltrato");
				String ckbLasceraciones=req.getParameter("ckbLasceraciones");
				String ckbMordiscos=req.getParameter("ckbMordiscos");
				String ckbCicatrices=req.getParameter("ckbCicatrices");
				String ckbDiferenteEvolucion=req.getParameter("ckbDiferenteEvolucion");
				String ckbSugestivasMaltrato=req.getParameter("ckbSugestivasMaltrato");
				String ckbTraumaVisceral=req.getParameter("ckbTraumaVisceral");
				String ckbTraumaGrave=req.getParameter("ckbTraumaGrave");
				String lesionesSugestival=req.getParameter("lesionesSugestival");
				String ckbSangradoVaginal=req.getParameter("ckbSangradoVaginal");
				String ckbJuegoSexual=req.getParameter("ckbJuegoSexual");
				String ckbBocaGenitales=req.getParameter("ckbBocaGenitales");
				String ckbVIH=req.getParameter("ckbVIH");
				String ckbGonorrea=req.getParameter("ckbGonorrea");
				String ckbSifilis=req.getParameter("ckbSifilis");	
				String ckbTrichomonaVaginalis=req.getParameter("ckbTrichomonaVaginalis");
				String ckbChlamydiaTrachomatis=req.getParameter("ckbChlamydiaTrachomatis");
				String ckbCondilomatosis=req.getParameter("ckbCondilomatosis");
				String ckbTemeroso=req.getParameter("ckbTemeroso");
				String ckbRetraido=req.getParameter("ckbRetraido");
				String ckbRechazoAdulto=req.getParameter("ckbRechazoAdulto");
				String ckbDeprimido=req.getParameter("ckbDeprimido");
				String ckbEvitaContactoVisual=req.getParameter("ckbEvitaContactoVisual");
				String ckbTrastornoSueno=req.getParameter("ckbTrastornoSueno");
				String ckbTrastornoAlimentario=req.getParameter("ckbTrastornoAlimentario");	
				String ckbProblemasPsicomaticos=req.getParameter("ckbProblemasPsicomaticos");
				String ckbConductasRegresivas=req.getParameter("ckbConductasRegresivas");
				String ckbDesarrolloEstancado=req.getParameter("ckbDesarrolloEstancado");
				String ckbViolenciaIntrafamiliar=req.getParameter("ckbViolenciaIntrafamiliar");
				String ckbFamiliaCaotica=req.getParameter("ckbFamiliaCaotica");
				String ckbCuidadoresAdictos=req.getParameter("ckbCuidadoresAdictos");
				String observacionesMaltrato=req.getParameter("observacionesMaltrato");*/
				//Respuestas Maltrato
				/*String ckbMaltratoFisicoMuyGrave=req.getParameter("ckbMaltratoFisicoMuyGrave");
				String ckbAbusoGrave=req.getParameter("ckbAbusoGrave");
				String ckbMaltratoFisico=req.getParameter("ckbMaltratoFisico");
				String ckbSospechaAbusoSexual=req.getParameter("ckbSospechaAbusoSexual");
				String ckbMaltratoEmocional=req.getParameter("ckbMaltratoEmocional");
				String ckbAbandono=req.getParameter("ckbAbandono");
				String ckbNoSospechaMaltrato=req.getParameter("ckbNoSospechaMaltrato");*/

				//Desarrollo
				/*String radAntecedenteImportante=req.getParameter("radAntecedenteImportante");
				String paraDesarrollo=req.getParameter("paraDesarrollo");
				String algunFactorRiesgo=req.getParameter("algunFactorRiesgo");
				String PCefalico=req.getParameter("PCefalico");
				String AlteracionesFenotipicas=req.getParameter("AlteracionesFenotipicas");	
				String ckbMenosDosDE=req.getParameter("ckbMenosDosDE");
				String ckbMasDosDE=req.getParameter("ckbMasDosDE");
				String observacionesDesarrollo=req.getParameter("observacionesDesarrollo");

				String ckbRealizaCondEdadUno=req.getParameter("ckbRealizaCondEdadUno");
				String ckbRealizaCondEdadDos=req.getParameter("ckbRealizaCondEdadDos");
				String ckbRealizaCondEdadTres=req.getParameter("ckbRealizaCondEdadTres");
				String ckbRealizaCondEdadCuatro=req.getParameter("ckbRealizaCondEdadCuatro");	
				String ckbAusenciaCondEdadUno=req.getParameter("ckbAusenciaCondEdadUno");
				String ckbAusenciaCondEdadDos=req.getParameter("ckbAusenciaCondEdadDos");
				String ckbAusenciaCondEdadTres=req.getParameter("ckbAusenciaCondEdadTres");
				String ckbAusenciaCondEdadCuatro=req.getParameter("ckbAusenciaCondEdadCuatro");
				String ckbAusenciaCondGrupoAnteriorUno=req.getParameter("ckbAusenciaCondGrupoAnteriorUno");
				String ckbAusenciaCondGrupoAnteriorDos=req.getParameter("ckbAusenciaCondGrupoAnteriorDos");
				String ckbAusenciaCondGrupoAnteriorTres=req.getParameter("ckbAusenciaCondGrupoAnteriorTres");
				String ckbAusenciaCondGrupoAnteriorCuatro=req.getParameter("ckbAusenciaCondGrupoAnteriorCuatro");*/

				//Respuestas Desarrollo
				/*String ckbProbRetrasoDesarrollo=req.getParameter("ckbProbRetrasoDesarrollo");
				String ckbRiesgoProblemaDesarrollo=req.getParameter("ckbRiesgoProblemaDesarrollo");
				String ckbDesarrolloNormalRiesgo=req.getParameter("ckbDesarrolloNormalRiesgo");
				String ckbxDesarrolloNormal=req.getParameter("ckbxDesarrolloNormal");*/

				//Antecedentes Vacunacion
				/*String ckbBCGUno=req.getParameter("ckbBCGUno");
				String ckbHepatitisBRN=req.getParameter("ckbHepatitisBRN");
				String ckbHepatitisBUno=req.getParameter("ckbHepatitisBUno");
				String ckbHepatitisBDos=req.getParameter("ckbHepatitisBDos");
				String ckbHepatitisBTres=req.getParameter("ckbHepatitisBTres");
				String ckbDPTUno=req.getParameter("ckbDPTUno");
				String ckbDPTDos=req.getParameter("ckbDPTDos");	
				String ckbDPTTres=req.getParameter("ckbDPTTres");
				String ckbDPTRUno=req.getParameter("ckbDPTRUno");
				String ckbDPTRDos=req.getParameter("ckbDPTRDos");
				String ckbVOPUno=req.getParameter("ckbVOPUno");
				String ckbVOPDos=req.getParameter("ckbVOPDos");
				String ckbVOPTres=req.getParameter("ckbVOPTres");
				String ckbVOPRUno=req.getParameter("ckbVOPRUno");
				String ckbVOPRDos=req.getParameter("ckbVOPRDos");
				String ckbSRPUno=req.getParameter("ckbSRPUno");
				String ckbSRPDos=req.getParameter("ckbSRPDos");	
				String ckbRotavirusUno=req.getParameter("ckbRotavirusUno");
				String ckbRotavirusDos=req.getParameter("ckbRotavirusDos");
				String ckbStreptococoNeumoniaeUno=req.getParameter("ckbStreptococoNeumoniaeUno");
				String ckbStreptococoNeumoniaeDos=req.getParameter("ckbStreptococoNeumoniaeDos");
				String ckbStreptococoNeumoniaeTres=req.getParameter("ckbStreptococoNeumoniaeTres");
				String ckbHaemophilusUno=req.getParameter("ckbHaemophilusUno");
				String ckbHaemophilusDos=req.getParameter("ckbHaemophilusDos");
				String ckbHaemophilusTres=req.getParameter("ckbHaemophilusTres");
				String ckbHaemophilusRUno=req.getParameter("ckbHaemophilusRUno");
				String ckbHaemophilusRDos=req.getParameter("algunFactorRiesgo");
				String influenzaDosis=req.getParameter("influenzaDosis");
				String fiebreAmarilla=req.getParameter("fiebreAmarilla");
				String otrasVacunas=req.getParameter("otrasVacunas");
				String vacunasPendientes=req.getParameter("vacunasPendientes");	
				String proximasVacunas=req.getParameter("proximasVacunas");
				String tiempoProximaVacuna=req.getParameter("tiempoProximaVacuna");*/

				//Completar Examen Fisico
				/*String observacionesExamenFisico=req.getParameter("observacionesExamenFisico");
				String otroProbDetectado=req.getParameter("otroProbDetectado");*/

				//Alimentacion
				/*String radiLecheMaterna=req.getParameter("radiLecheMaterna");
				String cuantasVecesLecheMat=req.getParameter("cuantasVecesLecheMat");
				String radPechoNoche=req.getParameter("radPechoNoche");
				String extraeLeche=req.getParameter("extraeLeche");
				String guardaAdministraLeche=req.getParameter("guardaAdministraLeche");
				String radMenorSeisLeche=req.getParameter("radMenorSeisLeche");	
				String cualesLecheMenorSeis=req.getParameter("cualesLecheMenorSeis");
				String cuantasVecesLecheMenorSeis=req.getParameter("cuantasVecesLecheMenorSeis");
				String conQueRecibeAlimento=req.getParameter("conQueRecibeAlimento");
				String quienDaComerAlimento=req.getParameter("quienDaComerAlimento");*/
				//Mayor Seis
				/*String cuantasComidasAyer=req.getParameter("cuantasComidasAyer");
				String tamanoPorcionesAyer=req.getParameter("tamanoPorcionesAyer");
				String cuantasComidasConsistenciaAyer=req.getParameter("cuantasComidasConsistenciaAyer");
				String alimentosOrigenAnimal=req.getParameter("alimentosOrigenAnimal");
				String productosLacteos=req.getParameter("productosLacteos");
				String comioLegumbres=req.getParameter("comioLegumbres");
				String comioVegetales=req.getParameter("comioVegetales");	
				String cantidadAceite=req.getParameter("cantidadAceite");
				String quienDaComerAyerMayorSeis=req.getParameter("quienDaComerAyerMayorSeis");
				String propioPlato=req.getParameter("propioPlato");
				String suplementacionAlimentos=req.getParameter("suplementacionAlimentos");
				String radEstaEnfermo=req.getParameter("radEstaEnfermo");
				String queComioEnfermedad=req.getParameter("queComioEnfermedad");

				String radEsObeso=req.getParameter("radEsObeso");
				String padresObesos=req.getParameter("padresObesos");
				String ninoHaceEjercicio=req.getParameter("ninoHaceEjercicio");
				String programaNutricional=req.getParameter("programaNutricional");	
				String observacionesAlimentacion=req.getParameter("observacionesAlimentacion");
				String problemaDetectadoAlimentacion=req.getParameter("problemaDetectadoAlimentacion");
				String recomendacionesAlimentaciones=req.getParameter("recomendacionesAlimentaciones");*/

				//Recomendaciones
				/*String diagnosticoPaci=req.getParameter("diagnosticoPaci");
				String codigoPac=req.getParameter("codigoPac");
				String tratamiento=req.getParameter("tratamiento");
				String signosAlarma=req.getParameter("signosAlarma");
				String consultaControl=req.getParameter("consultaControl");
				String dondeConsultaControl=req.getParameter("dondeConsultaControl");
				String volverNinoSano=req.getParameter("volverNinoSano");	
				String referidoConsultas=req.getParameter("referidoConsulta");
				String recomendacionesNino=req.getParameter("recomendacionesNino");
				String recomendacionBuenTrato=req.getParameter("recomendacionBuenTrato");
				String radRecVitaminaA=req.getParameter("radRecVitaminaA");
				String proxDosisVitaminaA=req.getParameter("proxDosisVitaminaA");
				String radRecAlbendazol=req.getParameter("radRecAlbendazol");
				String proxDosisAlbendazol=req.getParameter("proxDosisAlbendazol");
				String radRecHierro=req.getParameter("radRecHierro");
				String cuandoHierro=req.getParameter("cuandoHierro");
				String volverRecibirHierro=req.getParameter("volverRecibirHierro");	
				String radRecZinc=req.getParameter("radRecZinc");
				String cuantoTiempoZinc=req.getParameter("cuantoTiempoZinc");
				String iniciaZinc=req.getParameter("iniciaZinc");*/

								//Insercion DB AIEPI EMBARAZADAS
								/*if(va.equals("2.3")){
									mmp.insertarEncabezadoInformeAiepiEmbarazadas(CodUsuario, CodPaciente, hra, fechacjmysql, CodAdmsion);
									rs12=mmp.ObtenerCodigoInformeEmbarazadas(fechacjmysql, hra);
									String CodForm="";
									try {
										if(rs12.next()){
											out.print(rs12.getString(1));
											CodForm=rs12.getString(1);
										}
										rs12.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}*/
									
									//mmp.insertarExamenFisicoDosCinco(motiConsulta, anteNatales, APF, temperaturas, frecuenciaCardiacas, frecuenciaRespiratorias, tallas, peso, perimetroCefalicos, IMC, CodFor);
									/*mmp.insertarSignosPeligroDosCinco(ckbNoBebePecho, ckbxLetargico, ckbVomitaTodo, ckbConvulsiones, observacionesSignosPeligro, CodFor);
									mmp.insertarTosDosCincoUno(radiDificultadRespirar, desdeCuandoTos, radEpisodioSibilancias, radSibilanciasRecurrentes, radCuadroGripal, radAntecedentesPrematuridad, CodFor);
									mmp.insertarTosDosCincoDos(respiracionesMinutos, ckbRespiracionRapida, ckbxTirajeSubcostal, ckbSaturacionOxigeno, ckbTirajeSupraclavicular, ckbxEstridor, ckbSibilancias, ckbApnea, ckbIncapacidadHablar, ckbSomnoliento, ckbConfuso, ckbAgitado, observacionesTos, CodFor);
									mmp.insertarDiarrea(radTieneDiarrea, desdeCuandoDiarrea, radiSangreHeces, radiVomito, numVomitos, numDiarreasVC, numDiarreasC, CodFor);
									mmp.insertarDiarreaDos(ckbComatoso, ckbIntranquilo, ckbxOjosHundidos, ckbBebeMal, ckbBebeAvidamente, pliegueCutaneos, observacionesDiarrea, CodFor);
									mmp.insertarFiebre(radTieneFiebre, desdeCuandoFiebre, radFiebreTO, radFiebreTN,radViveQuinceDias, ckbZonaDengue, zonaMalaria, CodFor);
									mmp.insertarFiebreDos(ckbRigidezNuca, ckbAparienciaEnfermo, ckbManifestacionesSangrado, ckbAspectoToxico, respuestaSocial, piel, ckbErupcionCutanea, ckbDolorAbdominal, ckbCefalea, ckbMialgias, CodFor);
									mmp.insertarFiebreTres(ckbArtralgias, ckbDolorRetroocular, ckbPostracion, ckbPTorniquete, ckbLipotimia, ckbHepatomegalia, ckbPulsoRapido, ckbxLlenadoCapilar, ckbAscitis, ckbDisminucionDiuresis, CodFor);
									mmp.insertarFiebreCuatro(ckbCuadroHematico, ckbLeucocitos, ckbNeutrofilos, ckbPlaquetas, ckbParcialOrina, ckbGotaGruesa, observacionesFiebre, CodFor);
									mmp.insertarOido(radTieneProbOido, radTieneDolorOido, ckbTieneSupuracion, desdeCuandoSupuracion, numEpisodiosPrevios, ckbTumefaccionDolorosa, ckbTimpanoRojo, ckbxSupuracionOido, observacionesOido, CodFor);
									mmp.insertarGarganta(radTieneProbGarganta, radTieneDolorGarganta, ckbGangliosCuello, ckbAmigdalasEritematosas, ckbExudadoBlanquecino, observacionesGarganta, CodFor);
									mmp.insertarSaludBucal(radTieneDolorComer, radTieneDolorDiente, radTraumaCara, radTieneCaries, radLimpiaBocaManana, radLimpiaBocaTarde, radLimpiaBocaNoche, radLimpiaDientes, radNinoSolo, radCepillo, radCrema, radSeda, radiUtilizaChupo, ultimaConsultaOdontologica, CodFor);
									mmp.insertarSaludBucalDos(ckbInflamacionLabio, ckbNoInvolucraSurco, ckbEnrojecimiento, ckbInflamacionEncia, ckbLocalizado, ckbGeneralizado, ckbDeformacionEncia, ckbExudadoPus, ckbVesiculas, ckbUlceras, CodFor);
									mmp.insertarSaludBucalTres(placas, ckbFractura, ckbMovilidad, ckbDesplazamiento, ckbExtrusion, ckbIntrusion, ckbAvulsion, herida, ckbManchasBlancas, ckbCafes, ckbCariesCavitacionales, ckbPlacaBacteriana, observacionesSaludBucal, CodFor);
									mmp.insertarCrecimiento(radEmanacionVisible, pesosEdad, radEdemaPies, apariencia, IMCEdad, tallaEdad, pesosTalla, tendenciasPeso, CodFor);
									mmp.insertarCrecimientoDos(ckbDesnutricionGlobalSevera, ckbDesnutricionGlobal, ckbRiesgoDesnutricion, ckbPesoAdecuadoEdad, ckbDesnutricionCronica, ckbRiesgoDNT, ckbTallaAdecuadaEdad, ckbDesnutricionAguda, ckbDNTAguda, ckbPesoAdecuadoTalla, ckbSobrepeso, ckbObesidad, observacionesCrecimiento, CodFor);
									mmp.insertarAnemia(radRecibidoHierro, cuandoRecibidoHierro, cuantoTiempoRecibidoHierro, palidezPalmar, palidezConjuntival, observacionesAnemia, CodFor);
									mmp.insertarMaltrato(produjeronLesiones, radRelataMaltrato, ckbFisico, ckbSexual, ckbNegligencia, quien_maltrato, radIncongruenciaTrauma, radExisteIncongruencia, diferentesVersiones, CodFor);
									mmp.insertarMaltratoDos(radTardiaConsulta, frecuenciaPegar, desobedienteHijo, comportamientoAnormalPadres, radDescuidadoNino, porDescuidadoNino, descuidadoNinoEn, factorRiesgo, radActitudAnormal, CodFor);
									mmp.insertarMaltratoTres(ckbxEquimosis, ckbHematomasMaltrato, ckbLasceraciones, ckbMordiscos, ckbCicatrices, ckbDiferenteEvolucion, ckbSugestivasMaltrato, CodFor);
									mmp.insertarMaltratoCuatro(ckbTraumaVisceral, ckbTraumaGrave, lesionesSugestival, ckbSangradoVaginal, ckbJuegoSexual, ckbBocaGenitales, ckbVIH, ckbGonorrea, ckbSifilis, ckbTrichomonaVaginalis, ckbChlamydiaTrachomatis, ckbCondilomatosis, CodFor);
									mmp.insertarMaltratoCinco(ckbTemeroso, ckbRetraido, ckbRechazoAdulto, ckbDeprimido, ckbEvitaContactoVisual, ckbTrastornoSueno, ckbTrastornoAlimentario, ckbProblemasPsicomaticos, ckbConductasRegresivas, ckbDesarrolloEstancado, ckbViolenciaIntrafamiliar, ckbFamiliaCaotica, ckbCuidadoresAdictos, observacionesMaltrato, CodFor);
									mmp.insertarMaltratoCraneo(ckbFracturas, ckbHematomas, ckbHemorragiasRetinianas, CodFor);
									mmp.insertarMaltratoFracturas(ckbCostillas, ckbHuesosLargos, ckbEspirales, ckbOblicua, ckbMetafisiarias, ckbEsternon, ckbEscapula, ckbMenorCincoAnos, CodFor);
									mmp.insertarMaltratoQuemaduras(ckbAreasRopa, ckbPatronSimetrico, ckbDenotaObjeto, ckbEnEspalda, CodFor);
									mmp.insertarMaltratoTraumaGenital(ckbLasceracionAguda, ckbLasceracionPerianal, ckbAusenciaHimen, ckbHimenCicatrizado, ckbCicatrizNavicular, ckbAnoDilatado, ckbHalazgoSemen, ckbFlujoGenital, ckbCuerpoExtrano, ckbVesiculasMaltrato, CodFor);
									mmp.insertarDesarrollo(radAntecedenteImportante, paraDesarrollo, algunFactorRiesgo, PCefalico, AlteracionesFenotipicas, ckbMenosDosDE, ckbMasDosDE, observacionesDesarrollo, CodFor);
									mmp.insertarDesarrolloCondiciones(ckbRealizaCondEdadUno, ckbRealizaCondEdadDos, ckbRealizaCondEdadTres, ckbRealizaCondEdadCuatro, ckbAusenciaCondEdadUno, ckbAusenciaCondEdadDos, ckbAusenciaCondEdadTres, ckbAusenciaCondEdadCuatro, ckbAusenciaCondGrupoAnteriorUno, ckbAusenciaCondGrupoAnteriorDos, ckbAusenciaCondGrupoAnteriorTres, ckbAusenciaCondGrupoAnteriorCuatro, CodFor);
									mmp.insertarAntecedentesVacunacion(ckbBCGUno, ckbHepatitisBRN, ckbHepatitisBUno, ckbHepatitisBDos, ckbHepatitisBTres, ckbDPTUno, ckbDPTDos, ckbDPTTres, ckbDPTRUno, ckbDPTRDos, ckbVOPUno, ckbVOPDos, ckbVOPTres, ckbVOPRUno, ckbVOPRDos, CodFor);
									mmp.insertarAntecedentesVacunacionDos(ckbSRPUno, ckbSRPDos, ckbRotavirusUno, ckbRotavirusDos, ckbStreptococoNeumoniaeUno, ckbStreptococoNeumoniaeDos, ckbStreptococoNeumoniaeTres, ckbHaemophilusUno, ckbHaemophilusDos, ckbHaemophilusTres, ckbHaemophilusRUno, ckbHaemophilusRDos, CodFor);
									mmp.insertarAntecedentesVacunacionTres(influenzaDosis, fiebreAmarilla, otrasVacunas, vacunasPendientes, proximasVacunas, tiempoProximaVacuna, CodFor);
									mmp.insertarCompletarExamenFisico(observacionesExamenFisico, otroProbDetectado, CodFor);
									mmp.insertarAlimentacion(radiLecheMaterna, cuantasVecesLecheMat, radPechoNoche, extraeLeche, guardaAdministraLeche, radMenorSeisLeche, cualesLecheMenorSeis, cuantasVecesLecheMenorSeis, conQueRecibeAlimento, quienDaComerAlimento, CodFor);
									mmp.insertarAlimentacionDos(radEstaEnfermo, queComioEnfermedad, radEsObeso, padresObesos, ninoHaceEjercicio, programaNutricional, observacionesAlimentacion, CodFor);
									mmp.insertarAlimentacionMayorSeisMeses(cuantasComidasAyer, tamanoPorcionesAyer, cuantasComidasConsistenciaAyer, alimentosOrigenAnimal, productosLacteos, comioLegumbres, comioVegetales, cantidadAceite, quienDaComerAyerMayorSeis, propioPlato, suplementacionAlimentos, CodFor);
									mmp.insertarRecomendacionesDosCinco(diagnosticoPaci, codigoPac, tratamiento, signosAlarma, consultaControl, dondeConsultaControl, volverNinoSano, referidoConsultas, recomendacionesNino, recomendacionBuenTrato, CodFor);
									mmp.insertarRecomendacionesDosis(radRecVitaminaA, proxDosisVitaminaA, radRecAlbendazol, proxDosisAlbendazol, radRecHierro, cuandoHierro, volverRecibirHierro, radRecZinc, cuantoTiempoZinc, iniciaZinc, CodFor);
									mmp.insertarRespuestasAnemia(ckbAnemiaSevera, ckbAnemia, ckbNoTieneAnemia, CodFor);
									mmp.insertarRespuestasCrecimiento(ckbObeso, ckbSobrepesoR, ckbDesnutricionSevera, ckbDesnutricion, ckbRiesgoDesnutricionR, ckbEstadoNutricional, CodFor);
									mmp.insertarRespuestasDesarrolloDosCinco(ckbProbRetrasoDesarrollo, ckbRiesgoProblemaDesarrollo, ckbDesarrolloNormalRiesgo, ckbxDesarrolloNormal, CodFor);
									mmp.insertarRespuestasDiarrea(ckbDeshidratacionGrave, ckbGradoDeshidratacion, ckbAltoRiesgoDeshidratacion, ckbSinDeshidratacion, ckbDiarreaPersistenteGrave, ckbDiarreaPersistente, ckbDisenteria, CodFor);
									mmp.insertarRespuestasFiebre(ckbEnfFebrilRiesgo, ckbEnfFebrilRiesgoIntermedio, ckbEnfFebrilRiesgoBajo, ckbMalariaComplicada, ckbMalaria, ckbDengueGrave, ckbDengueSignosAlarma, ckbProbableDengue, CodFor);
									mmp.insertarRespuestasGarganta(ckbFaringoamigdalitis, ckbEstreptococica, ckbFaringoamigdalitisViral, ckbNoTieneFaringoamigdalitis, CodFor);
									mmp.insertarRespuestasMaltrato(ckbMaltratoFisicoMuyGrave, ckbAbusoGrave, ckbMaltratoFisico, ckbSospechaAbusoSexual, ckbMaltratoEmocional, ckbAbandono, ckbNoSospechaMaltrato, CodFor);
									mmp.insertarRespuestasOido(ckbMastoiditis, ckbOtitisMediaCronica, ckbOtitisMediaRecurrente, ckbOtitisMediaAguda, ckbNoTieneOtitis, CodFor);
									mmp.insertarRespuestasSaludBucal(ckbCelulitisFacial, ckbEnfBucalGrave, ckbTraumaBucodental, ckbEstomatitis, ckbEnfDental, ckbAltoRiesgoEnfBucal, ckbBajoRiesgoEnfBucal, CodFor);
									mmp.insertarRespuestasSignosPeligro(ckbEnfMuyGrave, CodFor);
									mmp.insertarRespuestasTos(ckbGrupGrave, ckbBronquiolitisGrave, ckbSibilanciaGrave, ckbCrup, ckbxSibilancia, ckbNeumoniaGrave, ckbNeumonia, ckbTos, CodFor);*/
								//}	

			/**********************    CONSULTAR AIEPI 0-2   *******************************/			
			/*if(va.equals("4.1")){
				
				rs4=mmp.buscarInformesAIEPI0a2Realizados(CodPaciente);
				out.print("<table width='100%' border='1' class='style6' ><tr align='center' id='cabecera2' class='style11'><td >Fecha y Hora </td><td align='left'>Nombre Del Formato </td></tr>");
				try {
					while(rs4.next()){
						out.print("<tr><td width='20%' align='left'><a  href='#'onclick='mostrarInformesAIEPI0a2("+rs4.getString(2)+")'>"+rs4.getString(3)+"</a></td><td width='80%' align='left'>"+rs4.getString(5)+"</td></tr>");
					}
					out.print("</table>");
					rs4.getStatement().getConnection().close();
				} catch (SQLException e) {
						System.out.println("ERROR EN VA EQUAL 4.1 ControlMultiplePacientes "+e);
					e.printStackTrace();
				}
			}*/
			/**********************   FIN DE CONSULTAR AIEPI 0-2  *********************/
		
		
			/**********************    CONSULTAR AIEPI 2-5   *******************************/			
			/*if(va.equals("4.2")){

				rs4=mmp.buscarInformesAIEPI2a5Realizados(CodPaciente);
				out.print("<table width='100%' border='1' class='style6' ><tr align='center' id='cabecera2' class='style11'><td >Fecha y Hora </td><td align='left'>Nombre Del Formato </td></tr>");
				try {
					while(rs4.next()){
						out.print("<tr><td width='20%' align='left'><a  href='#'onclick='mostrarInformesAIEPI2a5("+rs4.getString(2)+")'>"+rs4.getString(3)+"</a></td><td width='80%' align='left'>"+rs4.getString(5)+"</td></tr>");
					}
					out.print("</table>");
					rs4.getStatement().getConnection().close();
				} catch (SQLException e) {
						System.out.println("ERROR EN VA EQUAL 4.2 ControlMultiplePacientes "+e);
					e.printStackTrace();
				}
			}*/
			/**********************   FIN DE CONSULTAR AIEPI 2-5 *********************/
			
			/**********************    CONSULTAR AIEPI GENERAL   *******************************/			
			if(va.equals("4.5")){
				String valIndentificador="";
				
				rs4=mmp.buscarInformesAIEPI(CodPaciente);
				out.print("<table width='100%' border='1' class='style6' ><tr align='center' id='cabecera2' class='style11'><td >Fecha y Hora </td><td align='left'>Nombre Del Formato </td></tr>");
				try {
					while (rs4.next()) {
						valIndentificador=rs4.getString(9);
						if (valIndentificador.equals("1")) {					
							out.print("<tr><td width='20%' align='left'><a  href='#'onclick='mostrarInformesAIEPI0a2("+rs4.getString(4)+")'>"+rs4.getString(5)+"/"+rs4.getString(6)+"</a></td><td width='80%' align='left'>"+rs4.getString(8)+"</td></tr>");
						}
						if (valIndentificador.equals("2")){
							out.print("<tr><td width='20%' align='left'><a  href='#'onclick='mostrarInformesAIEPI2a5("+rs4.getString(4)+")'>"+rs4.getString(5)+"/"+rs4.getString(6)+"</a></td><td width='80%' align='left'>"+rs4.getString(8)+"</td></tr>");
						}
					}				
					out.print("</table>");
					rs4.getStatement().getConnection().close();
				} catch (SQLException e) {
						System.out.println("ERROR EN VA EQUAL 4.5 AiepiMultiplePacientes "+e);
					e.printStackTrace();
				}
			}
			/**********************   FIN DE CONSULTAR AIEPI GENERAL  *********************/
			
			/***************************   AIEPI EMBARAZADAS  **********************************/
			if(va.equals("AiepiEmb")){
				String CodAdm="";
				rs9=mmp.VerificarAdmision(CodPaciente);
				
				try {
					if(rs9.next()){
						CodAdm=rs9.getString(1);
						
						rs10=mmp.PreguntarAdmisionEmbarazadas(CodAdm);
						if (rs10.next()) {
							out.print("El Paciente "+rs9.getString(5)+" Ya Se Realizó Un Informe De AIEPI Embarazdas Con La Admisión Nº "+rs9.getString(1)+" ");
						}else{
/*EMB RC NACIDO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td align='center' bgcolor='#104e8b' style='color:#FFFFFF'><b>ATENCIÓN INTEGRADA DE LA EMBARAZADA Y EL RECIÉN NACIDO</b></td></tr></table>");
					out.print("<table width='80%'><tr><td><b>INSTITUCION:</b></td><td>"+rs9.getString("Entidad")+"</td><td><b>MUNICIPIO:</b></td><td>"+rs9.getString("Municipio")+"</td><td><b>Nº HISTORIA CLINICA:</b></td><td>"+rs9.getString("adm_numero_ingreso")+"</td></tr>" +
							"<tr><td><b>NOMBRE DE LA MADRE:</b></td><td><input type='text' size='50'></td><td>Documento de identidad</td><td><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='TI'>TI</OPTION><OPTION value='CC'>CC</OPTION></SELECT></td><td>Nº&nbsp;&nbsp;<input type='text'></td></tr></table>");
					out.print("<table width='80%'><tr><td>TELÉFONO:</td><td><input type='text'></td><td>DIRECCIÓN:</td><td><input type='text'></td>" +
							"<td>MUNICIPIO:</td><td><input type='text'></td><td>EDAD:</td><td><input type='text' size='1'> años</td></tr>" +
							"<tr><td>PESO:</td><td><input type='text' size='1'> Kg.</td><td>TALLA:</td><td><input type='text' size='1'> Mt.</td>" +
							"<td>IMC:</td><td><input type='text' size='1'></td><td>TA:</td><td><input type='text' size='1'></td><td><input type='hidden' name='codAdmision' id='codAdmision' value='"+CodAdm+"' /></td></tr>" +
							"<tr><td>FC:</td><td><input type='text' size='1'> /min</td><td>FR:</td><td><input type='text' size='1'> /min</td><td>ºC</td><td><input type='text' size='1'></td></tr>" +
							"<tr><td>HEMOCLASIFICACIÓN:</td><td><input type='text' size='1'></td><td>Coombs</td><td><input type='text' size='1'></td></td></tr>" +
							"<tr><td>FECHA ÚLTIMA MENSTRUACIÓN</td><td><input type='text'></td><td>FECHA PROBABLE DE PARTO:</td><td><input type='text'></td></tr></table>");
					
					out.print("<table width='100%'><tr><td><br><b>MOTIVO DE CONSULTA/Enfermedad Actual/Revisión por sistemas:</b></td></tr>" +
							"<tr><td><textarea rows='4' cols='135' name='motivo_consulta' id='motivo_consulta'></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><td><b>ANTECEDENTES OBSTÉTRICOS:</b></td></tr>" +
							"<tr><td><textarea rows='4' cols='135' name='ant_natales' id='ant_natales'></textarea></td></tr></table>");
					out.print("<table width='80%'><tr><td>GESTACIONES:</td><td><input type='text' size='1'></td><td>PARTOS:</td><td><input type='text' size='1'></td>" +
							"<td>CESAREAS:</td><td><input type='text' size='1'></td><td>ABORTOS:</td><td><input type='text' size='1'></tr>" +
							"<tr><td>ESPONTÁNEO:</td><td><input type='checkbox'></td><td>PROVOCADO:</td><td><input type='checkbox'></td><td>HIJO NACIDO MUERTO/EN 1º SEMANA</td><td><input type='checkbox'></td></tr>" +
							"<tr><td>HIJOS PREMATUROS:</td><td><input type='text' size='1'></td><td>HIJOS <2500 gr</td><td><input type='checkbox'></td><td> HIJOS >4000 gr</td><td><input type='checkbox'></td></tr></table>");
					out.print("<table><tr><td>HIJOS MALFORMADOS:</td><td><input type='text' size='70'></td></tr></table>");
					out.print("<table width='80%'><tr><td>HIPERTENSIÓN/PREECLAMPSIA/ECLAMPSIA en el último embarazo:</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td>" +
							"<td>CIRUGÍAS TRACTO REPRODUCTIVO:</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td></tr></table>");
					out.print("<table><tr><td>FECHA ÚLTIMO PARTO:</td><td><input type='text'></td></tr></table>");
					//out.print("<table width='100%'><tr><td>Enfermedades previas y hospitalizaciones</td></tr>" +
							//"<tr><td><textarea rows='4' cols='135' name='enf_previas_hospitalizaciones' id='enf_previas_hospitalizaciones'></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><br><td><b>OTRO ANTECEDENTE:</b></td></tr>" +
							"<tr><td><textarea rows='4' cols='135' name='apf' id='apf'></textarea></td></tr></table>");
					
/*VER RG.GESTACIO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR EL RIESGO DURANTE LA GESTACIÓN QUE AFECTA EL BIENESTAR FETAL</b></td></tr></table>");
					out.print("<table width='100%'><tr><td>¿Ha tenido control prenatal?</td><td>Si<INPUT type='radio' name='rad_control_prenatal' id='si' onclick='num_control_prenatal.disabled=false;num_control_prenatal.focus()'>No<INPUT type='radio' name='rad_control_prenatal' id='no' onclick='num_control_prenatal.disabled=true'>&nbsp;&nbsp;&nbsp;Nº&nbsp;&nbsp;&nbsp;<input type='text' size='1' name='num_control_prenatal' id='num_control_prenatal' onkeypress='javascript:return validarNro(event)' disabled></td>" +
							"<td>¿Percibe movimientos fetales?</td><td>Si<INPUT type='radio' name='rad_mov_fetales' id='si'>No<INPUT type='radio' name='rad_mov_fetales' id='no'></td>" +
							"<td>¿Ha tenido fiebre frecuentemente?</td><td>Si<INPUT type='radio' name='rad_fiebre_frecuente' id='si'>No<INPUT type='radio' name='rad_fiebre_frecuente' id='no'></td><td>¿Le ha salido líquido por la vagina?</td><td>Si<INPUT type='radio' name='rad_liquido_vagina' id='si'>No<INPUT type='radio' name='rad_liquido_vagina' id='no'></td></tr>" +
							"<tr><td>¿Ha tenido flujo vaginal?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿Cigarrillo?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td>" +
							"<td>¿Padece alguna enfermedad?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿Cuál?</td><td><input type='text'></td></tr>" +
							"<tr><td>¿Bebidas alcohólicas?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿Cuál?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td>" +
							"<td>¿Consume drogas?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿Cuál?</td><td><input type='text'></td></tr></table>");
					out.print("<table><tr><td>¿Ha sufrido violencia o maltrato?Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'>&nbsp;&nbsp;&nbsp;Explique:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='text' size='70'></td></tr></table>");
					out.print("<table><tr><td>¿Inmunización toxoide tetánico?&nbsp;Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'>&nbsp;&nbsp;&nbsp;Nº de Dosis&nbsp;&nbsp;&nbsp;<input type='text' size='1'></td></tr></table>");
					out.print("<table width='100%'><tr><br><br><td>Altura uterina:</td><td><input type='text' size='1'> cm</td><td>No correlación con edad gestacional</td><td><input type='checkbox'></td>" +
							"<td>Embarazo múltiple</td><td><input type='checkbox'></td><td>Presentación anómala:</td><td><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Podalico'>Podálico</OPTION><OPTION value='Transverso'>Transverso</OPTION></SELECT></td></tr>" +
							"<tr><td>Palidez palmar:</td><td><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Intensa'>Intensa</OPTION><OPTION value='Leve'>Leve</OPTION></SELECT></td>" +
							"<td>Convulsiones</td><td><input type='checkbox'></td><td>Visión Borrosa</td><td><input type='checkbox'></td></tr>" +
							"<tr><td><b>Edema:</b></td><td></td><td>Cara</td><td><input type='checkbox'></td><td>Mano</td><td><input type='checkbox'></td><td>Piel</td><td><input type='checkbox'></td></tr>" +
							"<tr><td>Pérdida conciencia</td><td><input type='checkbox'></td><td>Cefalea intensa</td><td><input type='checkbox'></td>" +
							"<td>Signos enfermedad transmisión sexual</td><td><input type='checkbox'></td></tr>" +
							"<tr><td><b>Cavidad bucal:</b></td><td></td><td>Sangrado</td><td><input type='checkbox'></td><td>Inflamación</td><td><input type='checkbox'></td><td>Caries</td><td><input type='checkbox'></td><td>Halitosis</td><td><input type='checkbox'></td></tr>" +
							"<tr><td><br><br>Hto:</td><td><br><br><input type='text' size='1'></td><td><br><br>Ho:</td><td><br><br><input type='text' size='1'></td><td><br><br>Toxoplasma:</td><td><br><br><input type='text' size='1'></td></tr>" +
							"<tr><td>VDRL1</td><td><input type='text' size='2'></td><td>VDRL2</td><td><input type='text' size='2'></td><td>VIH1</td><td><input type='text' size='2'></td><td>VIH2</td><td><input type='text' size='2'></td></tr>" +
							"<tr><td>HEPATITIS B:</td><td><input type='text' size='2'></td><td>Otro:</td><td><input type='text'></td><td>Ecografía</td><td><input type='text'></td></tr></table>");
					out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
							"<tr><td><textarea rows='3' cols='135' name='' id=''></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><br><br><td><b>GESTACIÓN CON RIESGO INMINENTE</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>GESTACIÓN DE ALTO RIESGO</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>GESTACOÓN DE BAJO RIESGO</b>&nbsp;&nbsp;<input type='checkbox'></td></tr></table>");
					
/*VER RG TRABAJO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR EL RIESGO DURANTE EL TRABAJO DE PARTO QUE AFECTA EL BIENESTAR FETAL</b></td></tr></table>");
					out.print("<table width='100%'><tr><td>¿Ha tenido contracciones?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿Ha tenido hemorragia vaginal?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td>" +
							"<td>¿Le ha salido líquido por la vagina?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿De qué color?</td><td><input type='text'></td></tr>" +
							"<tr><td>¿Ha tenido dolores de cabeza severos?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿Ha tenido visión borrosa?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td>" +
							"<td>¿Ha tenido convulsiones?</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td></tr>" +
							"<tr><td><br><br>Contracciones en 10 minutos</td><td><br><br><input type='text' size='1'></td><td><br><br>FC Fetal</td><td><br><br><input type='text' size='1'> x minuto</td>" +
							"<td><br><br>Dilatación cervical</td><td><br><br><input type='text'></td><td><br><br>Presentación:</td><td><br><br><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Cefalico'>Cefálico</OPTION><OPTION value='Podalico'>Podálico</OPTION><OPTION value='Otra'>Otra</OPTION></SELECT>&nbsp;&nbsp;<input type='text'></td></tr>" +
							"<tr><td>Edema:</td><td><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Cara'>Cara</OPTION><OPTION value='Manos'>Manos</OPTION><OPTION value='Pies'>Pies</OPTION></SELECT></td><td>Hemorragia vaginal</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td>" +
							"<td>Hto:</td><td><input type='text' size='1'></td><td>Hb:</td><td><input type='text' size='1'></td></tr>" +
							"<tr><td>Hepatitis b:</td><td><input type='text' size='1'></td><td>VDRL antes del parto:</td><td><input type='text' size='1'></td><td>VIH:</td><td><input type='text' size='1'></td></tr></table>");
					out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
							"<tr><td><textarea rows='3' cols='135' name='' id=''></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><br><br><td><b>PARTO CON RIESGO INMINENTE</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>PARTO DE ALTO RIESGO</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>PARTO DE BAJO RIESGO</b>&nbsp;&nbsp;<input type='checkbox'></td></tr></table>");
					
/*ATEN REC NACIDO*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>ATENCIÓN DEL RECIÉN NACIDO EN SALA DE PARTOS</b></td></tr></table>");
					out.print("<table width='100%'><tr><td>FECHA NACIMIENTO:</td><td><input type='text'></td><td>HORA:</td><td><input type='text'size='3'></td>" +
							"<td>NOMBRE:</td><td><input type='text' size='35'></td><td>SEXO:</td><td><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>SELECCIONE</OPTION><OPTION value='FEMENINO'>FEMENINO</OPTION><OPTION value='MASCULINO'>MASCULINO</OPTION></SELECT></td></tr>" +
							"<tr><td>PESO:</td><td><input type='text' size='1'> Kg.</td><td>TALLA:</td><td><input type='text' size='1'> CM</td>" +
							"<td>PC:</td><td><input type='text' size='1'> CM</td><td>FC:</td><td><input type='text' size='1'></td></tr>" +
							"<tr><td>T:</td><td><input type='text' size='1'></td><td>ºC</td><td><input type='text' size='1'></td>" +
							"<td>EDAD GESTACIONAL:</td><td><input type='text' size='1'></td></tr>" +
							"<tr><td><br><b>APGAR:</b></td></tr>" +
							"<tr><td>1 minuto:</td><td><input type='text' size='1'></td><td>5 minutos</td><td><input type='text' size='1'></td>" +
							"<td>10 minutos</td><td><input type='text' size='1'></td><td>20 minutos</td><td><input type='text' size='1'></td></tr></table>");
					out.print("<table width='100%'><tr><br><td><b>OBSERVACIONES</b></td></tr>" +
							"<tr><td><textarea rows='3' cols='135' name='' id=''></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><br><br><td><b>PRETÉRMINO</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>ATÉRMINO</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>POSTÉRMINO</b>&nbsp;&nbsp;<input type='checkbox'></td>" +
							"<td><b>PEG</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>AEG</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>GEG</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>BPN</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>MBPN</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>EBPN</b>&nbsp;&nbsp;<input type='checkbox'></td></tr></table>");
					
/*VER REANIMACION*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR LA NECESIDAD DE REANIMACIÓN</b></td></tr></table>");
					out.print("<table width='80%'><tr><td>Prematuro</td><td><input type='checkbox'></td><td>Meconio</td><td><input type='checkbox'></td><td>No respiración o No llanto</td><td><input type='checkbox'></td><td>Hipotónico</td><td><input type='checkbox'></td></tr>" +
							"<tr><td>Apnea</td><td><input type='checkbox'></td><td>Jadeo</td><td><input type='checkbox'></td><td>Respiración dificultosa</td><td><input type='checkbox'></td><td>Cianosis persistente:</td><td><input type='checkbox'></td></tr>" +
							"<tr><td>Bradicardia</td><td><input type='checkbox'></td><td>Hipoxemia</td><td><input type='checkbox'></td></tr>" +
							"<tr><td><br><br><b>REANIMACIÓN</b></td></tr>" +
							"<tr><td>Estimulación</td><td><input type='checkbox'></td><td>Ventilación con presión positiva</td><td><input type='checkbox'></td>" +
							"<td>Comprensiones torácicas</td><td><input type='checkbox'></td><td>Intubación</td><td><input type='checkbox'></td></tr></table>");
					out.print("<table><tr><td><br>Medicamentos:</td><td><br><input type='text' size='70'></td></tr></table>");
					out.print("<table width='80%'><tr><br><br><td><b>REANIMACIÓN</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>CUIDADOS RUTINARIOS</b>&nbsp;&nbsp;<input type='checkbox'></td></tr></table>");
					
/*VER RG.NEONATAL*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>VERIFICAR RIESGO NEONATAL PRIMERA VALORACIÓN DEL RECIÉN NACIDO</b></td></tr></table>");
					out.print("<table width='100%'><tr><td>Ruptura prematura de membranas:</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>Tiempo:</td><td><input type='text' size='1'> horas</td>" +
							"<td>Líquido</td><td><input type='text'></td><td>Coriamnionitis FC:</td><td><input type='text' size='1'> /min.</td></tr>" +
							"<tr><td>Fiebre materna:</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>Tiempo</td><td><input type='text'></td>" +
							"<td>Infección intrauterina:</td><td><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='TORCH'>TORCH</OPTION><OPTION value='SIDA'>SIDA</OPTION></SELECT></td><td>Madre >20 años:</td><td><input type='text' size='1'></td></tr>" +
							"<tr><td>Historia de ingesta de:</td><td><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Alcohol'>Alcohol</OPTION><OPTION value='Cigarrillo'>Cigarrillo</OPTION><OPTION value='Drogas'>Drogas</OPTION></SELECT></td>" +
							"<td>Antecedentes de violencia o maltrato</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td></tr>" +
							"<tr><td><br><br><br><br>Respiración</td><td><br><br><br><br><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Normal'>Normal</OPTION><OPTION value='Anormal'>Anormal</OPTION></SELECT></td><td><br><br><br><br>Llanto</td><td><br><br><br><br><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Normal'>Normal</OPTION><OPTION value='Anormal'>Anormal</OPTION></SELECT></td>" +
							"<td><br><br><br><br>Vitalidad</td><td><br><br><br><br><SELECT name='' id=''><OPTION value='Seleccione' selected='selected'>Seleccione</OPTION><OPTION value='Normal'>Normal</OPTION><OPTION value='Anormal'>Anormal</OPTION></SELECT></td><td><br><br><br><br>Taquicardia</td><td><br><br><br><br><input type='checkbox'></td></tr>" +
							"<tr><td>Bradicardia</td><td><input type='checkbox'></td><td>Palidez</td><td><input type='checkbox'></td>" +
							"<td>Ictericia</td><td><input type='checkbox'></td><td>Plétora</td><td><input type='checkbox'></td></tr>" +
							"<tr><td>Cianosis</td><td><input type='checkbox'></td><td>Lesiones debidas al parto:</td><td><input type='text'></td>" +
							"<td>Anomalías congénitas:</td><td>Si<INPUT type='radio' name='' id='si'>No<INPUT type='radio' name='' id='no'></td><td>¿Cuál?</td><td><input type='text'></td></tr></table>");
					out.print("<table width='100%'><tr><br><td><b>OTRAS ALTERACIONES</b></td></tr>" +
							"<tr><td><textarea rows='3' cols='135' name='' id=''></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><br><br><td><b>ALTO RIESGO AL NACER</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>MEDIANO RIESGO AL NACER</b>&nbsp;&nbsp;<input type='checkbox'></td><td><b>BAJO RIESGO AL NACER</b>&nbsp;&nbsp;<input type='checkbox'></td></tr></table>");
					
/*RECOMENDACIONES*/	out.print("<table width='100%' border='1' bordercolor='#104e8b'><tr><br><td bgcolor='#104e8b' style='color:#FFFFFF'><b>RECOMENDACIONES</b></td></tr></table>");
					out.print("<table width='100%'><tr><td>COMPLETAR EL EXAMEN FISICO Y EVALUAR OTROS PROBLEMAS</td>" +
							"<tr><td><textarea rows='4' cols='135' name='completar_examen_fisico' id='completar_examen_fisico'></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><td>Diagnostico</td><td><INPUT type='text' size='50' name='diagnostico_pac' id='diagnostico_pac'></td>" +
							"<td>Codigo</td><td><INPUT type='text' size='50' name='codigo_cie_pac' id='codigo_cie_pac'></td></tr></table>");
					out.print("<table width='100%'><tr><td align='center'>TRATAR</td></tr>" +
							"<tr><td><textarea rows='4' cols='135' name='tratar_pac' id='tratar_pac'></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><td>ESCRIBIR LAS RECOMENDACIONES Y ORIENTACIONES DADAS SOBRE:</td></tr>" +
							"<tr><td><b>1.CUANDO VOLVER DE INMEDIATO AL SERVICIO(Signos de Alarma):</b></td></tr>" +
							"<tr><td><textarea rows='3' cols='135' name='volver_inmediato' id='volver_inmediato'></textarea></td></tr></table>");
					out.print("<table width='100%'><tr><td><b>2.CUANDO VOLVER A CONSULTA DE CONTROL:</b></td>" +
							"<td>RECIEN NACIDO:</td><td><INPUT type='text' name='recien_nacido' id='recien_nacido' onKeyup='mascaaa(this,patron,true,0,0,0)' onblur='vfiii()';></td>" +
							"<td>MADRE:</td><td><INPUT type='text' name='madre' id='madre'></td>" +
							"<td><b>3.CUANDO VOLVER A CONSULTA DE NIÑO SANO:</b></td><td><INPUT type='text' name='nino_sano' id='nino_sano'></td>" +
							"<td><b>4.REFERIDO A CONSULTA DE:</b></td><td><INPUT type='text' name='referido_consulta' id='referido_consulta'></td></tr></table>");
					out.print("<table width=100%><tr><td><b>5.MEDIDAS PREVENTIVAS ESPECIFICAS:</b></td>" +
							"<td>-El 1º mes,Despertar si en 3 horas no ha comido</td>" +
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
							"<INPUT id='guardar' type='button' value='Guardar Informe' onclick='GuardarAiepiCeroDosMeses()'>" +
							"<INPUT id='anular' type='button' value='Anular Informe' onclick='AnularInforme()'>" +
							"</div></td></tr></table>");
						}
						rs10.getStatement().getConnection().close();
				  }
				 	rs9.getStatement().getConnection().close();	
				} catch (SQLException  e) {
					 	System.out.println("Error en AiepiMultiplePacientes valor=AiepiEmb "+e);
					 	e.printStackTrace();
				}
			}
					
			/***************************   FIN DE  AIEPI EMBARAZADAS  *********************/		
	}
}
