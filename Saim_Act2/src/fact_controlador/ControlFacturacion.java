package fact_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cont_metodo.MetodoCuentas;

import fact_metodo.MetodoFacturacion;


public class ControlFacturacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
 
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
						
		String va = req.getParameter("valor");//opcion para ir
		String tipomovimiento = req.getParameter("tipo");//si es urgencias, hospitalario o ambulatorio
		String texto = req.getParameter("texto");//texto del autocompletar
		String codneps=req.getParameter("codneps");//codigo de la eps nueva para generar otro encabezado
		String codeps=req.getParameter("codeps");//codigo de la eps
		String eps=req.getParameter("eps");//descripcion de la eps
		String adm=req.getParameter("adm");//admision
		String enca=req.getParameter("enca");//encabezado
		String denca=req.getParameter("denca");//codigo del movimiento o detalle de fact_det_factura
		String fechacargue=req.getParameter("fechacargue");//fecha del cargue digitada
		String taq=req.getParameter("taq");//tipo de acto quirurgico texto
		String vtaq=req.getParameter("vtaq");//tipo de acto quirurgico value
		String aq=req.getParameter("aq");//acto quirurgico texto
		String vaq=req.getParameter("vaq");//acto quirurgico value
		String xx=req.getParameter("xx");//valor utilizado por el autocompletar
		String pos=req.getParameter("pos");//programa o servicio
		String cpos=req.getParameter("cpos");//codigo de programa o servicio
		String refpos=req.getParameter("refpos");//codigo de referencia de programa o servicio
		String descpos=req.getParameter("descpos");//descripcion del programa o servicio
		String ract=req.getParameter("ract");//programa o servicio
		String rmed=req.getParameter("rmed");//programa o servicio
		String movimientos=req.getParameter("movimientos");//Movimientos a cargar
		String nautoriza=req.getParameter("nautoriza");//Numero de Autorizacion
			
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		
		MetodoFacturacion mm = new MetodoFacturacion();
		
		java.util.Date fechaS = new Date();
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		
		String fechacjmysql=annio+"-"+mes+"-"+dia;
		String fechacj=dia+"/"+mes+"/"+annio;
		
		


		if(va.equals("1")){//Interfaz de Movimientos en urgencias
			out.print("<input type='hidden' id='tipomovimiento' value='"+tipomovimiento+"'>");
			out.print("<input type='hidden' id='encabezado' value='0' >");
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Urgencias</div></td></tr>");
			
			
			if(tipomovimiento.equals("0")){//Urgencias
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Paciente</div></i></td><td width='35%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='5%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
				
				out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='Completa(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesU&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
				out.print("<input type='hidden' id='Pacienteh'/></td>");
			}
			if(tipomovimiento.equals("1")){//Facturacion
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Paciente</div></i></td><td width='35%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='5%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
				
				out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='Completa(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesH&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
				out.print("<input type='hidden' id='Pacienteh'/></td>");
			}
			if(tipomovimiento.equals("2")){//Ambulatorio
				out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='45%'><i><div align='center'>Paciente</div></i></td><td width='10%'><i><div align='center'>Nro.Autorizacion</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='5%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
				
			 out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='Completa(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesA&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			 out.print("<input type='hidden' id='Pacienteh'/></td>");
			 out.print("<td><input type='text' id='nautorizacion'/ onblur='ActualizaAutorizacion()'></td>");
			}
			
			
			out.print("<td><div id='deps'><select id='eps' style='width:100%'  ><option value='Seleccione'>Seleccione</option></select></div></td>");//style='width:220px' 
			out.print("<td><input type=text id='adm' style='width:100%' readonly='readonly' ></td>"); 
			
			out.print("<td><div align='center'><input id='iraFact' type=button value='Facturar' onclick='enviarafacturar()'></div></td></tr>"); 
			
			out.print("<tr><td><div id='dPaciente' ></div></td></tr></table>");
			out.print("</table>");
			
			System.out.println("tipomovimiento: "+tipomovimiento);
			out.print("<tr><td><div id='EnunciadoMovimientos' ></div></td></tr></table>");
			out.print("<tr><td><div id='ListadoMovimientos' ></div></td></tr></table>");
			out.print("<tr><td><div id='CrearMovimientos' ></div></td></tr></table>");
		
		}//fin va=1

		
		
if(va.equals("1F")){//Interfaz de Facturacion en urgencias
			
			out.print("<input type='hidden' id='tipomovimiento' value='"+tipomovimiento+"'>");
			out.print("<input type='hidden' id='encabezado' value='0' >");
			out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Urgencias</div></td></tr></table>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Paciente</div></i></td><td width='35%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='5%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
			
			if(tipomovimiento.equals("0")){//Urgencias
			 out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='CompletaF(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesU&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			}
			if(tipomovimiento.equals("1")){//Facturacion
			 out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='CompletaF(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesH&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			}
			if(tipomovimiento.equals("2")){//Ambulatorio
			 out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='CompletaF(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesA&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			}
			
			out.print("<input type='hidden' id='Pacienteh'/></td>");
			out.print("<td><div id='deps'><select id='eps' style='width:100%' readonly='readonly' ><option value='Seleccione'>Seleccione</option></select></div></td>");//style='width:220px' 
			out.print("<td><input type=text id='adm' style='width:100%' readonly='readonly' ></td>"); 
			out.print("<td><div align='center'><input id='iraFact' type=button value='Movimientos' onclick='volveramovimientos()' ></div></td></tr>"); 
			
			out.print("<tr><td><div id='dPaciente' ></div></td></tr></table>");
			//out.print("</table>");
			
			//System.out.println("tipomovimiento: "+tipomovimiento);
			out.print("<table width='100%' border='1' ><tr><td><div id='EnunciadoMovimientos' ></div></td></tr></table>");
			out.print("<table width='100%' border='1' ><tr><td><div id='ListadoMovimientos' ></div></td></tr></table>");
			
			out.print("<table width='100%' border='1' ><tr><td><div id='CrearMovimientos' ></div></td></tr></table>");

			
			out.print("<table width='100%' border='1' ><tr><td><div id='FacturasPorGenerar' ></div></td></tr></table>");

		
		}//fin va=1F
		
		/*/	if(va.equals("100")){//Prueba boton facturacion
				out.print("<input type='hidden' id='tipomovimiento' value='"+tipomovimiento+"'>");
				out.print("<input type='hidden' id='encabezado' value='0' >");
				out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Urgencias</div></td></tr>");
				
				String cp="31012";
				String np="CESAR JULIO CHEVEL ENAMORADO";
				String ce="321";
				String ne="COOMEVA EPS  S.A";
				String a="36";
				String e="12347";
				
				out.print("<table width='100%' border='1' class='style6' >");
				out.print("<tr><td><input id='iraFact' type=button value='Facturar' onclick='volveramovimientos("+cp+",&quot;"+np+"&quot;,"+ce+",&quot;"+ne+"&quot;,"+a+","+e+")'></div></td></tr>"); 
				out.print("</table>");
				
				System.out.println("tipomovimiento: "+tipomovimiento);
			
			
			}//fin va=100
		*/
		
		/*if(va.equals("1")){//Interfaz de Movimientos en urgencias
			out.print("<input type='hidden' id='tipomovimiento' value='"+tipomovimiento+"'>");
			out.print("<input type='hidden' id='encabezado' value='0' >");
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimiento en Urgencias</div></td></tr>");
			
			out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='50%'><i><div align='center'>Paciente</div></i></td><td width='35%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td><td width='5%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
			
			if(tipomovimiento.equals("0")){//Urgencias
			 out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='Completa(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesU&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			}
			if(tipomovimiento.equals("1")){//Facturacion
			 out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='Completa(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesH&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			}
			if(tipomovimiento.equals("2")){//Ambulatorio
			 out.print("<tr><td><input type=text id='Paciente' style='width:100%' Onkeyup='Completa(this,dPaciente,event,&quot;ControlFacturacion&quot;,&quot;BuscarPacientesA&quot;,4,1)' >");//onfocus='limpiarmovimientos(0)' onkeyup='autocompletaPacientes(0,0)'  >"); //crear un onfocus aqui y en 1h para limpiar el div y revisar bn ambulatorio urg y hosp
			}
			
			out.print("<input type='hidden' id='Pacienteh'/></td>");
			out.print("<td><div id='deps'><select id='eps' style='width:100%'  ><option value='Seleccione'>Seleccione</option></select></div></td>");//style='width:220px' 
			out.print("<td><input type=text id='adm' style='width:100%' readonly='readonly' ></td>"); 
			out.print("<td><div align='center'><input id='iraFact' type=button value='Facturar' ></div></td></tr>"); 
			
			out.print("<tr><td><div id='dPaciente' ></div></td></tr></table>");
			out.print("</table>");
			
			System.out.println("tipomovimiento: "+tipomovimiento);
			out.print("<tr><td><div id='EnunciadoMovimientos' ></div></td></tr></table>");
			out.print("<tr><td><div id='ListadoMovimientos' ></div></td></tr></table>");
			out.print("<tr><td><div id='CrearMovimientos' ></div></td></tr></table>");
		
		}//fin va=1*/

		
		if(va.equals("BuscarPacientesU")){//Buscar pacientes en urgencias
			try {
				rs =mm.listarPacientesUrgencias(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"'";
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
		
		if(va.equals("BuscarPacientesH")){//Buscar pacientes en hospitalizacion
			try {
				rs =mm.listarPacientesHospitalariosyAmbulatorios(texto,"0");
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"'";
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



		if(va.equals("BuscarPacientesA")){//Buscar pacientes Ambulatorios
			try {
				rs =mm.listarPacientesHospitalariosyAmbulatorios(texto,"1");
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"'";
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
		
		
		if(va.equals("CargarEPS")){//Muestra la eps del usuario y las demas eps si desea cambiarla
			out.print("<select id='eps' style='width:100%'  onChange='CambiardeEntidad("+codeps+",&quot;"+eps+"&quot;)' ><option value='"+codeps+"'>"+eps+"</option>");//style='width:220px' 
			rs1=mm.EmpresasDiferentes(codeps);
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select>");
		}//fin CargarEPS
		
		
		if(va.equals("CambiarEPS")){//Cambia la eps del usuario creando otro encabezado nuevo	
						 
			String codenca="";
			rs = mm.InfoEncaxEntidad(adm,codneps);
			try {
				if(rs.next()){
					codenca=rs.getString(1);
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(codenca.equals("")){//Mo hay encabezado hay que crearlo
				rs = mm.obtenerInfoEnca(adm,codeps);
				try {
					if(rs.next()){
						rs1=mm.BuscarEntidadNueva(codneps);
						if(rs1.next()){
							mm.duplicarEncabezados(codneps,
									eps,
									rs1.getString(1),
									rs1.getString(7),
									rs1.getString(8),
									rs1.getString(12),
									rs.getString(8),
									rs.getString(9),
									rs.getString(10),
									rs.getString(11),
									rs.getString(12),
									rs.getString(13),
									rs.getString(14),
									rs.getString(15),
									adm,
									rs.getString(18),
									rs.getString(20),
									rs.getString(23),
									rs.getString(16),
									rs.getString("anticipos"),
									rs.getString("copago"),
									rs.getString("moderacion"),
									rs.getString("efectivo"));
							// consultar el ultimo encbezado creado
							rs2=mm.BuscarUltioEncabezadoCreado(adm);
							if(rs2.next()){
								out.print(rs2.getString(1));
							}
							rs2.getStatement().getConnection().close();
						}
						rs1.getStatement().getConnection().close();					
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}//fin CambiarEPS
		
		
		if(va.equals("2")){//Mostrar Movimientos de fact det factura
			String opcion = req.getParameter("opcion");	
			out.print("<table width='100%' border='1' class='style6' ><tr >" +
					"<td width='10%'></td><td width='50%'></td>" +
					"<td width='10%'></td><td width='10%'></td>" +
					"<td width='10%'></td><td width='10%'></td></tr>");
				
					rs2=mm.FactDetalle(enca);
					//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
					try {
						while(rs2.next()){
							if(rs2.getString(2).equals("1")){
								out.print("<td><input type=text id='refdescr' style='width:100%' readonly='readonly' value='"+rs2.getString(3)+"' ></td>");
								out.print("<td><input type=text id='descr' size='100%' readonly='readonly' value='"+rs2.getString(4)+"' ></td>");
								out.print("<td>"+rs2.getString(7)+"</td>");	
								out.print("<td>"+rs2.getString(8)+"</td>");
								out.print("<td align='right'>"+mm.formatMoneda(rs2.getString(9))+"</td>");
								if(rs2.getString(10).equals("0")){
									out.print("<td><div align='center'><a  href='#'onclick='EliminarCargue("+rs2.getString(1)+")'>Anular</a></div></td></tr>");//onclick='EliminarCargue("+rs2.getString(1)+","+venc+","+lot+",&quot;"+opcion+"&quot;)
								}else{
									out.print("<td><div align='center'><font color='gray'>Anular</font></div></td></tr>");
								}
								
							}else{
								out.print("<td><input type=text id='refdescr' style='width:100%' readonly='readonly' value='"+rs2.getString(5)+"' ></td>");
								out.print("<td><input type=text id='descr' size='100%' readonly='readonly' value='"+rs2.getString(6)+" ("+rs2.getString(4)+")' ></td>");
								out.print("<td>"+rs2.getString(7)+"</td>");	
								out.print("<td>"+rs2.getString(8)+"</td>");
								out.print("<td align='right'>"+mm.formatMoneda(rs2.getString(9))+"</td>");
								if(rs2.getString(10).equals("0")){
									out.print("<td><div align='center'><a  href='#'onclick='EliminarCargue("+rs2.getString(1)+")'>Anular</a></div></td></tr>");//onclick='EliminarCargue("+rs2.getString(1)+","+venc+","+lot+")'
								}else{
									out.print("<td><div align='center' ><font color='gray'>Anular</font></div></td></tr>");
								}
							}
							
						}
						rs2.getStatement().getConnection().close();
						} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
						}
					/////////////////////				
			}//fin va=2
		
		
		if(va.equals("3")){//Anular Movimientos
			mm.AnularMov(denca);
			out.print("Movimiento Anulado Exitosamente!!!");
		}//fin va=7


		if(va.equals("4")){//Interfaz para crear Movimientos
			out.print("<input type='hidden' id='epsh' value='"+codeps+"' >");
			out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></td></tr>");
			
			out.print("<tr><td>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0,event)' onBlur='Estancias();' /></td>");
			out.print("<td>Tipo de Acto Quir�rgico: <select  style='width:442px'  id='taq' onchange='CargarActoqx()'><option value='Seleccione'>Seleccione</option>");
			rs1=mm.listarFormasActoQx();
			try {
				while(rs1.next()){
				out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td>");
			out.print("<td><div id='divaqx'>Acto Qx: <select  style='width:442px'  id='aq'><option value='Seleccione'>Seleccione</option></div></td></tr>");
			out.print("</table>");
		
			out.print("<table width='100%' border='0' class='style6' id='lineasCreacion'><tr BGCOLOR='#D3D3D3' ><td width='38%'><i><div align='center'>No. de Refererencia. / Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%'><i><div align='center'>Acci�n</div></i></td></tr>");  //
			
			out.print("<td><input type=text id='new0' style='width:100%;' Onkeyup='Completa(this,sugerenciasprog0,event,&quot;ControlFacturacion&quot;,&quot;autobuscaprogoref&quot;,2,2,&quot;epsh&quot;)' onfocus='ActivarCampos()'>"); 
			out.print("<input  id='ref0'  type='hidden'>");
			out.print("<input  id='desct0' type='hidden'>"); 
			out.print("<input  id='descch0' type='hidden' ></td>");
			
			out.print("<td><div id='dserv0' ><select  style='width:100%' id='serv0' ><option value=''></option><input type='hidden' id='servh0'></div></td>"); 
			out.print("<td><input type=text id='cant0' style='width:100%;' onkeyup='checknumcantidad(0,event,0);'  ></td>");// onkeyup='tab(this,0,event); checknumcantidad(0);' onkeypress='return limita(3,this,event);' 
			out.print("<td><div id='dmed0' ><input type='text'  style='width:100%' id='med0' ></div></td>"); 
			out.print("<td><div id='dvalorpyp0' ><input readonly='readonly' type=text id='valorpyp0'  style='width:100%' ></div></td>");
			out.print("</table>");

			
			out.print("<table width='100%' border='0'><tr><td width='38%'></td><td width='20%'></td><td width='5%'></td><td width='10%'></td><td width='3%'></td><td width='3%'><div id='carga'  align='center'><a  href='#'onclick='CargarMovimientos("+enca+")' id='cargar'>Cargar</a><input type='hidden' id='j'  value='1' ></div></td></tr>");
			out.print("</table>");
			
			
			
			out.print("<table width='100%' border='0'  id='lineasCreacion'><tr><td width='38%'><div id='sugerenciasprog0' ></div></td><td width='20%'></td><td width='5%'></td><td width='10%'><div id='sugerenciasmed0' ></div></td><td width='3%'></td><td width='3%'></td></tr>");  //
			
		//	out.print("<tr><td><div id='sugerenciasprog' ></div></td><td></td><td></td><td><div id='sugerenciasmed' ></div></td><td></td><td></td></tr></table>");
			
			out.print("</table>");
		}//fin va=4

		
	
		if(va.equals("5")){//Consultamos la estancia del paciente
			
			String esti="";
			String estf="";
								
			rs1=mm.Estancias(enca);
			try {
				if(rs1.next()){
					esti=rs1.getString(1);
					estf=rs1.getString(2);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			if(estf==null){
				estf=fechacjmysql;
			}
			
			String dias=fechacargue.substring(0, 2);
			String meses=fechacargue.substring(3, 5);
			String anos=fechacargue.substring(6, 10);
			String efes=anos+"-"+meses+"-"+dias;
			
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fecd = null;
			Date feci = null;
			Date fecf = null;
						
			try {
				fecd= formatoDelTexto.parse(efes);
				feci= formatoDelTexto.parse(esti);
				fecf= formatoDelTexto.parse(estf);
			} catch (ParseException ex) {
			ex.printStackTrace();
			}
			
			String a1=esti.substring(0, 4);
			String m1=esti.substring(5, 7);
			String d1=esti.substring(8, 10);
			String e1=d1+"/"+m1+"/"+a1;
			
			String a2=estf.substring(0, 4);
			String m2=estf.substring(5, 7);
			String d2=estf.substring(8, 10);
			String e2=d2+"/"+m2+"/"+a2;
			
			int sw=0;
			if (fecd.compareTo(feci) < 0){
				sw=1;
			}
			if (fecd.compareTo(fecf) > 0){
				sw=1;
			}
			if (sw==1){
				out.print("Fecha por fuera del rango de estancia del paciente!!! \n");
				out.print("\nFecha Ingreso:    "+e1);
				out.print("\nFecha Egreso:     "+e2);
			}else{out.print("1");}		
		}//fin va=5
		
		
		if(va.equals("6")){//Buscar Actos qx segun tipo de acto
			
			if(taq.equals("Seleccione")){
				out.print("Acto Qx: <select  style='width:442px'  id='aq'>");
				out.print("<option value='Seleccione'>Seleccione</option>");
			}else{
			rs1=mm.obtenerManualBase(eps);
			String ManualBase = "";
			try {
				while(rs1.next()){
					ManualBase = rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String[] detManualBase = ManualBase.split(",");
			if(detManualBase.length>1){
				rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[1]);
			}else{
				rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[0]);
			}
			
			out.print("Acto Qx: <select  style='width:442px'  id='aq'>");
			try {
				while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
		}
		}//fin 6
		
		
		if(va.equals("7")){//Interfaz para crear Movimientos de servicios o hemodinamia
			
			int j=0;//contador de servicios
			
			if(pos.equals("2")){//Si es un servicio
							 	
				out.print("<input type='hidden' id='epsh' value='"+codeps+"' >");
				out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></td></tr>");
				
				out.print("<tr><td>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0,event)' onBlur='Estancias();' value='"+fechacargue+"'/></td>");
				out.print("<td>Tipo de Acto Quir�rgico: <select  style='width:442px'  id='taq' onchange='CargarActoqx()'><option value='"+vtaq+"'>"+taq+"</option>");
				if(vtaq.equals("Seleccione")){
				 rs1=mm.listarFormasActoQx();
				}
				else{
				 rs1=mm.listarFormasActoQx(vtaq);
				}
				try {
					out.print("<option value='Seleccione'>Seleccione</option>");
					while(rs1.next()){
					out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</select></td>");
				out.print("<td><div id='divaqx'>Acto Qx: <select  style='width:442px'  id='aq'><option title='"+aq+"'  value='"+vaq+"'>"+aq+"</option>");
				
				rs1=mm.obtenerManualBase(codeps);
				String ManualBase = "";
				try {
					while(rs1.next()){
						ManualBase = rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				String[] detManualBase = ManualBase.split(",");
				if(detManualBase.length>1){
					if(vaq.equals("Seleccione")){
					rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[1]);
					}else{
					rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[1],vaq);	
					}
				}else{
					if(vaq.equals("Seleccione")){
					rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[0]);
					}else{
					rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[0],vaq);	
					}
				}
				try {
					while(rs1.next()){
							out.print("<option title='"+rs1.getString(2)+"'  value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
				
				out.print("</div></td></tr>");
				out.print("</table>");
			
				out.print("<table width='100%' border='0' class='style6' id='lineasCreacion'><tr BGCOLOR='#D3D3D3' ><td width='38%'><i><div align='center'>No. de Refererencia. / Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%' id='tdultimo'><i><div align='center'>Acci�n</div></i><input type='checkbox' name='all' id='all' checked='true' onclick='Checkear()'></td></tr>");  //
			
				
					try {
					rs1=mm.listarProgramasdeServicios(cpos,codeps);
					while(rs1.next()){
						if(j==0){
							out.print("<tr><td><input type=text id='new"+j+"' style='width:100%;' Onkeyup='Completa(this,sugerenciasprog0,event,&quot;ControlFacturacion&quot;,&quot;autobuscaprogoref&quot;,2,2,&quot;epsh&quot;)' onfocus='ActivarCampos()' value='"+refpos+" "+descpos+"'>"); 
						}else{
							out.print("<tr><td><input type=text id='new"+j+"' style='width:100%;' value='"+refpos+" "+descpos+"'>"); 
						}
						out.print("<input  id='ref"+j+"'  type='hidden' value="+rs1.getString(2)+" >");
						out.print("<input  id='desct"+j+"' type='hidden' value='"+descpos+"' >"); 
						out.print("<input  id='descch"+j+"' type='hidden' value="+rs1.getString(1)+" ></td>");
						out.print("<td><div id='dserv"+j+"' ><select  style='width:100%' id='serv"+j+"' ><option value='"+refpos+"'>"+rs1.getString(3)+"</option><input type='hidden' id='servh"+j+"'></div></td>"); 
						out.print("<td><input type=text id='cant"+j+"' style='width:100%;' value='1'  onkeyup='checknumcantidad("+j+",event,"+j+");' ></td>");// onkeyup='tab(this,0,event); checknumcantidad(0);' onkeypress='return limita(3,this,event);' 
						
						if(rs1.getString(6).equals("1")){
							out.print("<td><div id='dmed"+j+"' ><input type='text'  style='width:100%' id='med"+j+"' Onkeyup='Completa(this,sugerenciasmed"+j+",event,&quot;ControlFacturacion&quot;,&quot;autobuscamedicos&quot;,2,3)' >");
							out.print("<input type='hidden' id='medh"+j+"' /></div></td>");
						}else{
							out.print("<td><div id='dmed"+j+"' ><input type='text'  style='width:100%' readonly id='med"+j+"' value='No es requerido'></div></td>");
						}
						
						out.print("<td><div id='dvalorpyp"+j+"' ><input readonly='readonly' type=text id='valorpyp"+j+"'  style='width:100%' value="+rs1.getString(5)+"></div></td>");
						
						out.print("<td id='chkCarg'><input type='checkbox'  name='checkbox' id='cargarc"+j+"' value='"+j+"'   checked='true'  >");
						
						out.print("<input type='hidden' id='pos'  value='"+pos+"'/>");
						out.print("<input name='raqx' type='hidden' id='raqx"+j+"'  value="+rs1.getString(4)+" >");
						out.print("<input name='rmed' type='hidden' id='rmed"+j+"'  value="+rs1.getString(6)+" >");
						out.print("<input name='rporc' type='hidden' id='rporc"+j+"'  value="+rs1.getString(7)+" ></td>");
						
						
						out.print("<tr><td><div id='sugerenciasprog"+j+"' ></div></td><td></td><td></td><td><div id='sugerenciasmed"+j+"' ></div></td><td></td><td></td></tr>");  //
						
						
						out.print("</tr>");
						j++;
					}
					rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					
					if(j==0){
					out.print("<td colspan='5'>Este servicio no posee ningun programa con tarifa vigente!!!  </td>");
					}else{
					out.print("</table>");	
					out.print("<table width='100%' border='0'><tr><td width='38%'></td><td width='20%'></td><td width='5%'></td><td width='10%'></td><td width='3%'></td><td width='3%'><div id='carga'  align='center'><a  href='#'onclick='CargarMovimientos("+enca+")' id='cargar'>Cargar</a><input type='hidden' id='j'  value="+j+" ></div></td></tr>");
					}
					out.print("</table>|lt2");
		//			
			}else{//si es un programa=1
			 
				rs = mm.obtenerEspPrograma(cpos,codeps);//Consulto la especialidad para ver si es de cardiologia o hemodinamia y poder imprimir las lineas necesarias de hemodinamia
				String espProg = "";
				int valorPrograma = 0;
				try {
					while(rs.next()){
						espProg = rs.getString(1);
						valorPrograma = Integer.parseInt(rs.getString(2));
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				System.out.println("es un programa de especialidad "+espProg);
				if(!vtaq.equals("6")){//Si no es de cardiologia ni hemodinamia
					System.out.println("entra a cambiar lt1"+espProg);
					out.print("<input name='raqx' type='hidden' id='raqx0'  value='"+ract+"'/></td>");
					out.print("<input name='rmed' type='hidden' id='rmed0'  value='"+rmed+"'/></td>");
					out.print("<input type='hidden' id='pos'  value='"+pos+"'/></td>");
					out.print("<select  style='width:100%' name='serv0' id='serv0' ><option value='"+cpos+"'>"+descpos+"</option>");
					out.print("|lt1");
				}else{//es de cardiologia y hemodinamia
					
					System.out.println("entra a hemodinamia"+espProg);
					
					out.print("<input type='hidden' id='epsh' value='"+codeps+"' >");
					out.print("<table width='100%' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Cargar Movimientos</div></td></tr>");
					
					out.print("<tr><td>Fecha de Movimiento: <input type=text id='fechamo' size='7%' onKeyup='masca(this,patron,true,0,0,0,event)' onBlur='Estancias();' value='"+fechacargue+"'/></td>");
					out.print("<td>Tipo de Acto Quir�rgico: <select  style='width:442px'  id='taq' onchange='CargarActoqx()'><option value='"+vtaq+"'>"+taq+"</option>");
					if(vtaq.equals("Seleccione")){
					 rs1=mm.listarFormasActoQx();
					}
					else{
					 rs1=mm.listarFormasActoQx(vtaq);
					}
					try {
						out.print("<option value='Seleccione'>Seleccione</option>");
						while(rs1.next()){
						out.print("<option title='"+rs1.getString(2)+"' value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></td>");
					out.print("<td><div id='divaqx'>Acto Qx: <select  style='width:442px'  id='aq'><option title='"+aq+"'  value='"+vaq+"'>"+aq+"</option>");
					
					rs1=mm.obtenerManualBase(codeps);
					String ManualBase = "";
					try {
						while(rs1.next()){
							ManualBase = rs1.getString(1);
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					String[] detManualBase = ManualBase.split(",");
					if(detManualBase.length>1){
						if(vaq.equals("Seleccione")){
						rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[1]);
						}else{
						rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[1],vaq);	
						}
					}else{
						if(vaq.equals("Seleccione")){
						rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[0]);
						}else{
						rs1=mm.listarActoQx(taq,detManualBase[0],detManualBase[0],vaq);	
						}
					}
					try {
						while(rs1.next()){
								out.print("<option title='"+rs1.getString(2)+"'  value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					out.print("</div></td></tr>");
					out.print("</table>");
				
					out.print("<table width='100%' border='0' class='style6' id='lineasCreacion'><tr BGCOLOR='#D3D3D3' ><td width='38%'><i><div align='center'>No. de Refererencia. / Programa</div></i></td><td width='20%'><i><div align='center'>Servicio</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td><td width='10%'><i><div align='center'>Medico</div></i></td><td width='3%'><i><div align='center'>Valor</div></i></td><td width='3%' id='tdultimo'><i><div align='center'>Acci�n</div></i><input type='checkbox' name='all' id='all'  checked='true' onclick='Checkear()'></td></tr>");  //
				
					
					rs1=mm.listarPHemodinamia(codeps);//Busco los programas de hemodinamia
					try {
						while(rs1.next()){
							
							if(j==0){
								out.print("<tr><td><input type=text id='new"+j+"' style='width:100%;' Onkeyup='Completa(this,sugerenciasprog0,event,&quot;ControlFacturacion&quot;,&quot;autobuscaprogoref&quot;,2,2,&quot;epsh&quot;)' onfocus='ActivarCampos()' value='"+refpos+" "+descpos+"'>"); 
							}else{
								out.print("<tr><td><input type=text id='new"+j+"' style='width:100%;' value='"+refpos+" "+descpos+"'>"); 
							}
							/*out.print("<input  id='ref"+j+"'  type='hidden' value="+refpos+" >");
							out.print("<input  id='desct"+j+"' type='hidden' value='"+descpos+"' >"); 
							out.print("<input  id='descch"+j+"' type='hidden' value="+cpos+" ></td>");
							out.print("<td><div id='dserv"+j+"' ><select  style='width:100%' id='serv"+j+"' ><option value='"+rs1.getString(1)+"'>"+rs1.getString(3)+"</option><input type='hidden' id='servh"+j+"'></div></td>"); 
							out.print("<td><input type=text id='cant"+j+"' style='width:100%;' value='1' onkeyup='checknumcantidad("+j+");' ></td>");// onkeyup='tab(this,0,event); checknumcantidad(0);' onkeypress='return limita(3,this,event);' 
							*/

							out.print("<input  id='ref"+j+"'  type='hidden' value="+rs1.getString(2)+" >");
							out.print("<input  id='desct"+j+"' type='hidden' value='"+descpos+"' >"); 
							out.print("<input  id='descch"+j+"' type='hidden' value="+rs1.getString(1)+" ></td>");
							out.print("<td><div id='dserv"+j+"' ><select  style='width:100%' id='serv"+j+"' ><option value='"+refpos+"'>"+rs1.getString(3)+"</option><input type='hidden' id='servh"+j+"'></div></td>"); 
							out.print("<td><input type=text id='cant"+j+"' style='width:100%;' value='1' onkeyup='checknumcantidad("+j+",event,"+j+");' ></td>");// onkeyup='tab(this,0,event); checknumcantidad(0);' onkeypress='return limita(3,this,event);' 
							
							if(rs1.getString(6).equals("1")){
								out.print("<td><div id='dmed"+j+"' ><input type='text'  style='width:100%' id='med"+j+"' Onkeyup='Completa(this,sugerenciasmed"+j+",event,&quot;ControlFacturacion&quot;,&quot;autobuscamedicos&quot;,2,3)' >");
								out.print("<input type='hidden' id='medh"+j+"' /></div></td>");
							}else{
								out.print("<td><div id='dmed"+j+"' ><input type='text'  style='width:100%' readonly id='med"+j+"' value='No es requerido'></div></td>");
							}
							
							out.print("<td><div id='dvalorpyp"+j+"' ><input readonly='readonly' type=text id='valorpyp"+j+"'  style='width:100%' value="+valorPrograma+"></div></td>");
							
							out.print("<td id='chkCarg'><input type='checkbox'  name='checkbox' id='cargarc"+j+"' value='"+j+"' checked='true' >");
							out.print("<input type='hidden' id='pos'  value='"+pos+"'/>");
							out.print("<input name='raqx' type='hidden' id='raqx"+j+"'  value="+rs1.getString(4)+" >");
							out.print("<input name='rmed' type='hidden' id='rmed"+j+"'  value="+rs1.getString(6)+" >");
							out.print("<input name='rporc' type='hidden' id='rporc"+j+"'  value="+rs1.getString(7)+" ></td>");
							
							out.print("<tr><td><div id='sugerenciasprog"+j+"' ></div></td><td></td><td></td><td><div id='sugerenciasmed"+j+"' ></div></td><td></td><td></td></tr>");  //
						
							out.print("</tr>");
							j++;
							
							
						}
						rs1.getStatement().getConnection().close();
					}catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					
					
					if(j==0){
						out.print("<td colspan='5'>Este servicio no posee ningun programa con tarifa vigente!!!  </td>");
					}else{
						out.print("</table>");	
						out.print("<table width='100%' border='0'><tr><td width='38%'></td><td width='20%'></td><td width='5%'></td><td width='10%'></td><td width='3%'></td><td width='3%'><div id='carga'  align='center'><a  href='#'onclick='CargarMovimientos("+enca+")' id='cargar'>Cargar</a><input type='hidden' id='j'  value="+j+" ></div></td></tr>");
					}
					
					out.print("</table>|hemodinamia");
					
				}//fin es de cardiologia y hemodinamia
			}//fin de si es programa
	
		}//fin va=7
		
		
		
		if(va.equals("8")){//Imprime el campo medico con el autocompletar
			out.print("<td><input type='text'  style='width:100%' id='med0' Onkeyup='Completa(this,sugerenciasmed0,event,&quot;ControlFacturacion&quot;,&quot;autobuscamedicos&quot;,2,3)' >");
			out.print("<input type='hidden' id='medh0' /></td>");
		}//fin va=8
		
		
		
		if(va.equals("9")){
			
			Calendar calendario = Calendar.getInstance();
			//	Calendar calendario = new GregorianCalendar();
				int hora, minutos, segundos;
				hora =calendario.get(Calendar.HOUR_OF_DAY);
				minutos = calendario.get(Calendar.MINUTE);
				segundos = calendario.get(Calendar.SECOND);
				String hra= hora+":"+minutos+":"+segundos;
				
				
			movimientos=movimientos.replace("|", "_");
			movimientos = movimientos.substring(1,movimientos.length()-1);
			String V[] = movimientos.split("_");
			
			System.out.println("Longitud: "+movimientos.length());		   
			System.out.println("movimientos: "+movimientos);		   
			
			for(int i=0; i<V.length; i=i+14){//recorre la informacion de programas
				//System.out.println("V["+(i+3)+"]: "+V[i+3]);	
							
				String cs="";
				String sc="";
				rs=mm.listarPrograma(V[i+1]);
				System.out.println("V[i+1] "+V[i+1]);
				try {
					if(rs.next()){
					cs=rs.getString(1);//descripcion clase de servicio
					System.out.println("cs "+cs);
					sc=rs.getString(2);//subcentro de costo
					System.out.println("sc "+sc);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				
				String valorn="";
				String porcs="";
				if(V[i+12].equals("Seleccione")){
					valorn=V[i+8];
				}else{
					int indice=Integer.parseInt(V[i+13]);
					rs2=mm.PorcentajeQx(V[i+12]);
					try {
						if(rs2.next()){
							porcs=rs2.getString(indice);
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					if(porcs.equals("")){
						porcs = "100";
					}
					
					int critpe=Math.round(Integer.parseInt(V[i+8])*(Float.parseFloat(porcs)/100));
					valorn=String.valueOf(critpe);
				}//fin else
				
				
				
				String di=V[i+6].substring(0, 2);
				String me=V[i+6].substring(3, 5);
				String a=V[i+6].substring(6, 10);
				String f=a+"-"+me+"-"+di;
				
				//int critpe=Math.round(Integer.parseInt(critp)*(Float.parseFloat(porcs)/100));
				//critpn=String.valueOf(critpe);
				
				//V[0]:programa o servicio
				//V[1]:codigo de programa 
				//V[2]:codigo de referencia programa 
				//V[3]:descripcion de programa 
				//V[4]:codigo de servicio
				//V[5]:descripcion de servicio
				//cs
				//V[6]:fecha de movimiento
				//V[7]:cantidad 
				//V[8]:valor
				//V[9]:usuario
				//V[10]:encabezado
				//V[11]:medico
				//sc
				//V[12]:acto quirurgico
				//V[13]:porcentaje
				
				if(V[i].equals("1")){
					mm.CargarDetalle(fechacjmysql,hra,V[i],V[i+1],V[i+2],V[i+3], null , null ,cs,f,V[i+7],valorn,V[i+9],V[i+10],V[i+11],sc,V[i+12],"0");
					System.out.println("1.f: "+fechacjmysql+" h "+hra+" V[i] "+V[i]+" V[i+1] "+V[i+1]+" V[i+2] "+V[i+2]+" V[i+3] "+V[i+3]+" null "+null+" null "+ null +" cs "+cs+ " f " +f+" V[i+7] "+V[i+7]+" valorn "+valorn+" V[i+9] "+V[i+9]+" V[i+10] "+V[i+10]+" V[i+11] "+V[i+11]+" sc "+sc+" V[i+12] "+V[i+12]+"0");
				}else{
					mm.CargarDetalle(fechacjmysql,hra,V[i],V[i+1],V[i+2],V[i+5],V[i+4],V[i+3],cs,f,V[i+7],valorn,V[i+9],V[i+10],V[i+11],sc,V[i+12],porcs);
					System.out.println("2.f: "+fechacjmysql+" hra "+hra+" V[i] "+V[i]+"V[i+1] "+V[i+1]+" V[i+2] "+V[i+2]+" V[i+5] "+V[i+5]+" V[i+4] "+V[i+4]+" V[i+3] "+V[i+3]+" cs "+cs+" f "+f+" V[i+7] "+V[i+7]+" valorn "+valorn+" V[i+9] "+V[i+9]+" V[i+10] "+V[i+10]+" V[i+11] "+V[i+11]+" sc "+sc+" V[i+12] "+V[i+12]+" porcs "+porcs);
				}
			}//fin recorrer la informacion
		}//fin==9	
			

		
		if(va.equals("10")){//Prueba boton facturacion
			// System.out.println("DIEZ: "+enca+" - "+nautoriza);
			  mm.ActualizarNumAutorizacion(enca,nautoriza);
			}
		
		
		if(va.equals("autobuscaprogoref")){//consulta a la base de datos programas y servicios 
			System.out.print(texto+" - "+xx);
			try {
				rs =mm.listarPYSoRef(texto,xx);
				
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9)+"|"+rs.getString(10)+"|"+rs.getString(11)+"'";
	            	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				res.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
		}
		
		
		if(va.equals("autobuscamedicos")){//consulta a la base de datos medicos
			try {
				//System.out.print("ESSSTOO."+xx);
				rs =mm.listarMed(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					//cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(1)+"'";
					cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+" "+rs.getString(3)+"|"+rs.getString(4)+"'";
	            	cadena = cadena +",";	
				}
				cadena = cadena+"]";
				//System.out.print("ESSSTOO."+cadena);
				res.getWriter().write(cadena);
				rs.getStatement().getConnection().close();
			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
		
		
		
		
		
		/*****************************************************************************************************/
		/******************************************************************************************************/
		
		if(va.equals("BCPR")){
			String CodEncFactura=req.getParameter("CodEncFactura");
			try {
				out.print("<table width='100%' border='1' >");
				rs=mm.ConsultarCarguesEncabezado(CodEncFactura);
				Long Total=0L;
				while(rs.next()){
					Long Subtotal=(rs.getLong("cantidad")*rs.getLong("valor"));
					if(rs.getString("tipopop").equals("1")){
						out.print("<tr>" +
								"<td width='7%'>"+rs.getString("cod_programa")+"</td>");
						out.print("<td width='52%'>"+rs.getString("nombre_programa")+"</td>");
						out.print("<td width='7%'>"+rs.getString("fecha_realizacion")+"</td>");	
						out.print("<td width='7%' align='center'>"+rs.getString("cantidad")+"</td>");
						out.print("<td width='7%' align='center'>"+mm.formatMoneda(rs.getString("valor"))+"</td>");
						out.print("<td width='10%' align='center'>"+mm.formatMoneda(Subtotal+"")+"</td>");	
						out.print("<td width='10%' align='center'><a href='#' onclick='MoverDetalleFactura("+rs.getString("cod_det_factura")+")' >Mover</a></td></tr>");						
					}else{
						out.print("<tr><td width='7%'>"+rs.getString("cod_paquete")+"</td>");
						out.print("<td width='52%'>"+rs.getString("nombre_paquete")+" ("+rs.getString("nombre_programa")+") </td>");
						out.print("<td width='7%'>"+rs.getString("fecha_realizacion")+"</td>");	
						out.print("<td width='7%' align='center'>"+rs.getString("cantidad")+"</td>");
						out.print("<td width='7%' align='center'>"+mm.formatMoneda(rs.getString("valor"))+"</td>");		
						out.print("<td width='10%' align='center'>"+mm.formatMoneda(Subtotal+"")+"</td>");
						out.print("<td width='10%' align='center'><a href='#' onclick='MoverDetalleFactura("+rs.getString("cod_det_factura")+")' >Mover</a></td></tr>");						
					}
					Total=Total+Subtotal;
				
				}
				rs.getStatement().getConnection().close();
				String TipoRegimen="";String Anticipos="";String Copago="";String Descuentos="";
				rs1=mm.VerificarRegimenEntidad(CodEncFactura);
				if(rs1.next()){
					TipoRegimen=rs1.getString("regimen");
					Anticipos=rs1.getString("anticipos");
					Copago=rs1.getString("copago");
					Descuentos=rs1.getString("Descuento");
				}
				rs1.getStatement().getConnection().close();
				out.print("<tr><td width='7%'></td>");
				out.print("<td width='52%'></td>");
				out.print("<td width='7%'></td>");	
				out.print("<td width='7%' align='center'></td>");
				out.print("<td width='7%' align='right'>Total:</td>");		
				out.print("<td width='10%' align='center'>"+mm.formatMoneda(Total+"")+"</td>");
				out.print("<td width='10%' align='center'><input type='button' value='Generar Factura' onclick='GenerarDatosFactura("+CodEncFactura+",&quot;"+mm.formatMoneda(Total+"")+"&quot;,"+Total+",&quot;"+TipoRegimen+"&quot;,"+Anticipos+","+Copago+","+Descuentos+")' ></td></tr>");
				out.print("</table>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("VDF")){
			String CodEnc =req.getParameter("CodEnc");
		/*	
			out.print("<table width='100%' border='1' class='style6' ><tr> " );
			out.print("<td>Credito Empresa</td><td><input type='text' id='txtValorEmpresa' ></td>");
			out.print("<td>Efectivo</td><td><input type='text' id='txtEfectivo' onblur='DeduccirValor()' ></td>");
			out.print("<td>Subtotal</td><td><input type='text' id='txtSubtotal' ></td></tr> " );
			out.print("<tr> " );
			out.print("<td>Anticipos</td><td><input type='text' id='txtAnticipos' onblur='DeduccirValor()' ></td>");
			out.print("<td>Descuentos</td><td><input type='text' id='txtDescuentos' onblur='DeduccirValor()'></td>");
			out.print("<td>Copago</td><td><input type='text' id='txtCopago' onblur='DeduccirValor()' ></td></tr>  " );
			out.print("</table> ");*/
			
		}
		if(va.equals("GENEFAC")){
			String CodEncFactura =req.getParameter("CodEncFactura");
			String txtValorEmpresa=req.getParameter("txtValorEmpresa");
			String txtEfectivo=req.getParameter("txtEfectivo");
			String txtAnticipos=req.getParameter("txtAnticipos");
			String txtDescuentos=req.getParameter("txtDescuentos");
			String txtCopago=req.getParameter("txtCopago");
			String TipoFactura=req.getParameter("TipoFactura");
			String subtl=req.getParameter("subtl");
			String txtCodusuario=req.getParameter("txtCodusuario");
			String CodEps=req.getParameter("CodEps");
			String CodAdm=req.getParameter("CodAdm");
			java.util.Date FechaAc1 = new java.util.Date();
			java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
			java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
			int Efectivo=0;
			Efectivo=Integer.parseInt(txtAnticipos)+Integer.parseInt(txtCopago);
			
			/*if(TipoFactura.equals("Entidad")){				
				mm.ActualizarEncabezadoPreFactura(txtValorEmpresa, subtl, "0", "0", "0", "0", CodEncFactura);
			}
				
			if(TipoFactura.equals("Compartido")){
				mm.ActualizarEncabezadoPreFactura(txtValorEmpresa, subtl, txtAnticipos, txtCopago, txtDescuentos, Efectivo+"", CodEncFactura);
			}
				
			if(TipoFactura.equals("Usuario")){
				mm.ActualizarEncabezadoPreFactura(txtEfectivo, subtl, txtAnticipos, txtCopago, txtDescuentos, Efectivo+"", CodEncFactura);
			}*/	
			
			if(TipoFactura.equals("Entidad")){
				int ValorFactura=0;
				ValorFactura=Integer.parseInt(txtValorEmpresa)-(Integer.parseInt(txtDescuentos)+Efectivo);
				mm.ActualizarEncabezadoFactura(ValorFactura+"", subtl, "0", "0", "0", "0", CodEncFactura,Fecha_Insercion1+"",Hora_Insercion1+"",txtCodusuario);
				//actualizar los detalles del encabezado
				mm.ActualizarDetalleFactura(CodEncFactura, "1");
			}
			
			if(TipoFactura.equals("Compartido")){
				int ValorFactura=0;
				ValorFactura=Integer.parseInt(txtValorEmpresa)-(Integer.parseInt(txtDescuentos)+Efectivo);
				mm.ActualizarEncabezadoFactura(ValorFactura+"", subtl, txtAnticipos, txtCopago, txtDescuentos, Efectivo+"", CodEncFactura,Fecha_Insercion1+"",Hora_Insercion1+"",txtCodusuario);
				//actualizar los detalles del encabezado
				mm.ActualizarDetalleFactura(CodEncFactura, "1");
			}
			
			if(TipoFactura.equals("Usuario")){
				int ValorFactura=0;
				ValorFactura=Integer.parseInt(txtEfectivo)-(Integer.parseInt(txtDescuentos)+Efectivo);
				mm.ActualizarEncabezadoFactura(ValorFactura+"", subtl, txtAnticipos, txtCopago, txtDescuentos, Efectivo+"", CodEncFactura,Fecha_Insercion1+"",Hora_Insercion1+"",txtCodusuario);
				//actualizar los detalles del encabezado
				mm.ActualizarDetalleFactura(CodEncFactura, "1");
			}
			/***********************************************************************************/
			/************************************************************************************/
			try {		
				//TIPOS DE FACTURA
				//tipoFact=0 hospitalizacion
				//tipoFact=1 urgencia
				//tipoFact=2 Consulta Externa y Ambulatorio
				//tipoFact=3 capitado??
				
				rs=mm.ConsultarMovimientosActivos(CodAdm);
				if(rs.next()){
						//tiene detalles se deja quieto.
						// si busca algun movimiento en facturado=0 o 3, 
						//entonces el atendido sigue dela admsion wueda en 0					
				
				}else{
					//si no tiene ningun detalle es 0 o 3
					//entonces el atendido se pone en 1
					mm.CerrarAdmisionParaFacturar(CodAdm);
				}
				rs.getStatement().getConnection().close();
				
			//verificar si el encabezado esta en fact_numeradas
				String zc="";
			rs=mm.ConsultarEncabezadoNumeradas(CodEncFactura);
			if(rs.next()){
				// este encabezado ya esta facturado
				System.out.print("EL ENCABEZADO YA FUE FACTURADO, INTENTE CON OTRO");
				out.print("F");
			}else{
				int cont=0;
				rs2=mm.NumFacturas();
				while(rs2.next()){
					cont=cont+1;
				}
				rs2.getStatement().getConnection().close();
						
				//2. Consultamos ultimo consecutivo de numeradas
				String nf="";
				String Prefijo="";
				rs2=mm.NumFacturas();
				if(rs2.next()){
					nf=rs2.getString("consecutivo");
					Prefijo=rs2.getString("sigla_consecutivo");
				}
				rs2.getStatement().getConnection().close();
			
				//3. Aumentamos el consecutivo de numeradas
				int z=Integer.parseInt(nf)+1;
				zc=String.valueOf(z);					
				mm.ActualizarNumFacturas(zc);
						
				//4. Ingresamos a facturas numeradas
				zc=Prefijo+""+zc;
				mm.AsignarNumFact(Fecha_Insercion1+"",CodEncFactura,zc);
						
				//5.Ingresamos en fact_movfacturas
				rs2=mm.BuscarCodFactNumerada(Fecha_Insercion1+"",CodEncFactura,zc);
				String codfactNum="";
				if(rs2.next()){
					codfactNum=rs2.getString(1);
					mm.recordEstadoFact(codfactNum, "0", Fecha_Insercion1+"", txtCodusuario);
				}
				rs2.getStatement().getConnection().close();

			}
			rs.getStatement().getConnection().close();
			
						//***		
			//7.Creamos la factura en cont_factura 
			String cod_cuenta_fk="";
			String estado="0";
			String fecha_factura="";
			String fecha_insercion="";
			String hora_insercion="";
			String iva="0";
			String numero_factura="";
			String observacion="-";
			String precio_factura="";
			String ret_ica="-";
			String tipo="1";
				
			MetodoCuentas mc= new MetodoCuentas();						
			try {
				ResultSet rsfc=null;
				rsfc=mc.BuscarDatosParaFactura(CodEncFactura);
				if(rsfc.next()){
					cod_cuenta_fk=rsfc.getString(9);
					fecha_factura=rsfc.getString(4);
					fecha_insercion=rsfc.getString(7);
					hora_insercion=rsfc.getString(8);
					numero_factura=rsfc.getString(2);
					precio_factura=rsfc.getString(3);
				}
				rsfc.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			mc.CrearFactura(cod_cuenta_fk, estado, fecha_factura, fecha_insercion, hora_insercion, iva, numero_factura, observacion, precio_factura, ret_ica, tipo, txtCodusuario);

			//8.Creamos la factura en cont_detalle_factura y en cont_cartera_plazo
			try {
				ResultSet rsfc=null;
				rsfc=mc.DatosFacturaDetalle(numero_factura);
				if(rsfc.next()){					
					mc.CrearDetalleFactura(rsfc.getString(1), "0", fecha_factura, "1", precio_factura, "-", "-", fecha_insercion, hora_insercion, rsfc.getString(8), "CERO PESOS");
					mc.CrearPlazoCartera(numero_factura, precio_factura,rsfc.getString(1));
				}
				rsfc.getStatement().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
					
					
			//9.Actualizamos auditoria de convenios o auditoria de facturas
			String nit="0";
			rs = mm.ValorFact(CodEncFactura);
			String val="";
			try {
				while(rs.next()){
					val=rs.getString(1);
					nit=rs.getString(2);
				}
				rs.getStatement().getConnection().close();	
					
			} catch (SQLException e) { 
				out.print("Error "+e);
				e.printStackTrace();
			}
			mm.ActualizarAuditoriadeConvenios(CodEps,val);
		
	/***Cargar los costos de todos los medicamentos***/
			rs2=mm.ConsultarProgramasValores(CodEncFactura);
			double costot=0;
			double valort=0;
			ResultSet rs3=null;
			while(rs2.next()){//ME TRAIGO LOS CODIGOS DE LOS PROGRAMAS MEDICAMENTOS E INSUMOS
				rs3=mm.ConsultarProgramasCostos(CodAdm,rs2.getString(1));
				double costop=0;
				String xxx="0";
				if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
					//costop=costop+Double.parseDouble(rs3.getString(3));//
					if(rs3.getString(3)==null){xxx="0";}
					//if(rs3.getString(3).equals(null)){xxx="0";}
					costop=costop+Double.parseDouble(xxx);//
				}
				rs3.getStatement().getConnection().close();
				
				costot=costot+costop;
				valort=valort+Double.parseDouble(rs2.getString(3));
			}
			rs2.getStatement().getConnection().close();
					
			mm.actualizarCostosdeDispensaciont(CodEncFactura,String.valueOf(valort),String.valueOf(costot)); 
			/***FIN Cargar los costos de todos los medicamentos***/
			
			String conso="";
			String cons="";
			String consn="";
			rs = mm.ConsecutivosdeCuentas("10");
			try {
				if(rs.next()){
					//out.print(rs.getString(1)+"|"+rs.getString(2));
					if(rs.getString(2).length()==1){cons=("00000000"+rs.getString(2));}
					if(rs.getString(2).length()==2){cons=("0000000"+rs.getString(2));}
					if(rs.getString(2).length()==3){cons=("000000"+rs.getString(2));}
					if(rs.getString(2).length()==4){cons=("00000"+rs.getString(2));}
					if(rs.getString(2).length()==5){cons=("0000"+rs.getString(2));}
					if(rs.getString(2).length()==6){cons=("000"+rs.getString(2));}
					if(rs.getString(2).length()==7){cons=("00"+rs.getString(2));}
					if(rs.getString(2).length()==8){cons=("0"+rs.getString(2));}
					if(rs.getString(2).length()==9){cons=rs.getString(2);}
					consn=rs.getString(1)+""+cons;
					conso=rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			mm.CrearDocumento(annio,mes,"010",consn,fechacjmysql,"FACTURA DE VENTA "+zc,"0","0","0","",txtCodusuario,Fecha_Insercion1+"",Hora_Insercion1+"");
					
			String docu="";
			rs = mm.ConsecutivoUDocumento(consn);
			try {
				if(rs.next()){
					docu=rs.getString(1)+1;
					System.out.println("docu: "+docu);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}	
			
			int ctan=Integer.parseInt(conso)+1;
			conso=String.valueOf(ctan);
			mm.ActualizarConsecutivo(conso,"10");
				
			int doc2=Integer.parseInt(docu);
			String doc=String.valueOf(doc2);
			
			//BUSCAMOS LA CUENTA DE INGRESO 13050505 
			rs3=mm.ConsultarcuentaIngresoClientes();
			String ctai="0";
			String nctai="";
			if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
				ctai=rs3.getString(1);//
				nctai=rs3.getString(2);//
			}
			rs3.getStatement().getConnection().close();
			
			
			//ME TRAIGO EL CODIGO DE LA TABLA TERCERO CON ESTE NIT
			rs3=mm.ConsultarTercerosconNIT(nit);
			String ctat="0";
			if(rs3.next()){
				ctat=rs3.getString(1);//
			}
			rs3.getStatement().getConnection().close();
			
			//Hay q modificar el centro y subcentro y el tercero
			String sc="0";
			String cs="0";
			String cc="0";
			String scc="0";
			/*if(tipoFact.equals("1")){	sc="1"; 	cs="1";   cc="1"; scc="1";  }//Factura de Urgencias
			if(tipoFact.equals("0")){	sc="2"; 	cs="2";   cc="2"; scc="2"; 	}//Factura de Hospitalizacion
			if(tipoFact.equals("2")){	sc="4"; 	cs="15";  cc="3"; scc="15"; 	}//Factura de Ambulatorio y COnsulta Externa
			if(tipoFact.equals("3")){	sc="4"; 	cs="15";  cc="3"; scc="15"; 	}//Factura Capitado*/
			
			rs3=mm.ConsultarEncabezado(CodEncFactura);		
			String tipoFact="1";
			if(rs3.next()){
				//tipoFact=rs3.getString(23);//
				tipoFact=rs3.getString("tipo");	
			}
			rs3.getStatement().getConnection().close();
			
			if(tipoFact.equals("1")){	sc="1"; 	cs="1";   cc="1"; scc="1";  }//Factura de Urgencias
			if(tipoFact.equals("2")){	sc="1"; 	cs="2";   cc="2"; scc="2"; 	}//Factura de Hospitalizacion
			if(tipoFact.equals("3")){	sc="1"; 	cs="15";  cc="3"; scc="15"; }//Factura de Ambulatorio 
			if(tipoFact.equals("4")){	sc="1"; 	cs="15";  cc="3"; scc="15"; }//Factura COnsulta Externa
			if(tipoFact.equals("5")){	sc="1"; 	cs="15";  cc="3"; scc="15"; }//Factura Capitado
			if(tipoFact.equals("6")){	sc="1"; 	cs="15";  cc="3"; scc="15"; }//Factura Agrupado
			
			//1. Creo el detalle de ingreso 13050505
			mm.CrearDetalleDocumento(doc,ctai,sc,cs,ctat,nctai,"0",numero_factura,precio_factura,"0","0","FAC");//1ra linea
			
			Double vtd=0.0;
			String progc="";
			String valortc="";
			String nombrec="";
			String clases="";
			rs2=mm.ConsultarProgramasValores(CodEncFactura);
			while(rs2.next()){//ME TRAIGO LOS CODIGOS DE LOS PROGRAMAS MEDICAMENTOS E INSUMOS
				progc=rs2.getString(1);
				valortc=rs2.getString(3);
				nombrec=rs2.getString(4);
				clases=rs2.getString(5);
				
				//Aqui buscamos el programa la cuenta
				rs3=mm.Consultarcuentasdeprogramas(cc,scc,progc);
				String cuentapp="0";
				String cuentappc="0";
				String cuentappg="0";
				if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
					cuentapp=rs3.getString(1);//
					cuentappc=rs3.getString(2);//
					cuentappg=rs3.getString(3);//
				}
				rs3.getStatement().getConnection().close();
				
				if(cuentapp.equals("")){cuentapp="0";}
				
				mm.CrearDetalleDocumento(doc,cuentapp,sc,cs,ctat,nombrec,"0",numero_factura,"0",valortc,"0","FAC");//demas lineas
				
				if((clases.equals("MEDICAMENTOS"))||(clases.equals("MATERIALES"))){
					
					//Consulto el costo del programa
					rs3=mm.ConsultarProgramasCostos(CodAdm,progc);
					double costop=0;
					String xxx="0";
					if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
						xxx=rs3.getString(3);//
						if(rs3.getString(3)==null){xxx="0";}
						costop=Double.parseDouble(xxx);//
					}
					rs3.getStatement().getConnection().close();
					
					String cps=String.valueOf(costop);
					
					mm.CrearDetalleDocumento(doc,cuentappc,sc,cs,ctat,nombrec,"0",numero_factura,cps,"0","0","FAC");//tercera linea
					mm.CrearDetalleDocumento(doc,cuentappg,sc,cs,ctat,nombrec,"0",numero_factura,"0",cps,"0","FAC");//cuarta linea
					vtd=vtd+Double.parseDouble(cps);
				}
						
				vtd=vtd+Double.parseDouble(valortc);
			}
			rs2.getStatement().getConnection().close();
						
					
					//BUSCAMOS SI HAY QUE HACER DETALLE DE COPAGO Y MODERACION
			rs3=mm.Consultarcopagoymoderacionodescuento(CodEncFactura);
			String cop="0";
			String mod="0";
			if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
				cop=rs3.getString(1);//
				mod=rs3.getString(2);//
			}
			rs3.getStatement().getConnection().close();
			
			if(!cop.equals("0")){
				//BUSCAMOS LA CUENTA DE COPAGO 42050505 
				rs3=mm.ConsultarcuentaCopago();
				String ctacop="0";
				String nctacop="";
				if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
					ctacop=rs3.getString(1);//
					nctacop=rs3.getString(2);//
				}
				rs3.getStatement().getConnection().close();
				
				mm.CrearDetalleDocumento(doc,ctacop,sc,cs,ctat,nctacop,"0",numero_factura,cop,"0","0","FAC");//linea de copago
			}//fin si tiene copago	
			
			if(!mod.equals("0")){
				//BUSCAMOS LA CUENTA DE COPAGO 42050505 
				rs3=mm.ConsultarcuentaModeracion();
				String ctacop="0";
				String nctacop="";
				if(rs3.next()){//ME TRAIGO EL COSTO PROMEDIO DE CADA PROGRAMA
					ctacop=rs3.getString(1);//
					nctacop=rs3.getString(2);//
				}
				rs3.getStatement().getConnection().close();
						
				mm.CrearDetalleDocumento(doc,ctacop,sc,cs,ctat,nctacop,"0",numero_factura,cop,"0","0","FAC");//linea de copago
			}//fin si tiene descuento o moderacion	
					
			mm.ActualizarDocumentoDebitoCredito(doc, String.valueOf(vtd), String.valueOf(vtd));

			} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
			/***********************************************************************************/
			/*************************************************************************************/
			
			
		}
		if(va.equals("GDPREF")){
			String CodEncFactura =req.getParameter("CodEncFactura");
			String txtValorEmpresa=req.getParameter("txtValorEmpresa");
			String txtEfectivo=req.getParameter("txtEfectivo");
			String txtAnticipos=req.getParameter("txtAnticipos");
			String txtDescuentos=req.getParameter("txtDescuentos");
			String txtCopago=req.getParameter("txtCopago");
			String TipoFactura=req.getParameter("TipoFactura");
			String subtl=req.getParameter("subtl");
			int Efectivo=0;
			Efectivo=Integer.parseInt(txtAnticipos)+Integer.parseInt(txtCopago);
			if(TipoFactura.equals("Entidad")){	
				int ValorFactura=0;
				ValorFactura=Integer.parseInt(txtValorEmpresa)-(Integer.parseInt(txtDescuentos)+Efectivo);
				mm.ActualizarEncabezadoPreFactura(ValorFactura+"", subtl, "0", "0", "0", "0", CodEncFactura);
			}
			
			if(TipoFactura.equals("Compartido")){
				int ValorFactura=0;
				ValorFactura=Integer.parseInt(txtValorEmpresa)-(Integer.parseInt(txtDescuentos)+Efectivo);
				mm.ActualizarEncabezadoPreFactura(ValorFactura+"", subtl, txtAnticipos, txtCopago, txtDescuentos, Efectivo+"", CodEncFactura);
			}
			
			if(TipoFactura.equals("Usuario")){
				int ValorFactura=0;
				ValorFactura=Integer.parseInt(txtEfectivo)-(Integer.parseInt(txtDescuentos)+Efectivo);
				mm.ActualizarEncabezadoPreFactura(ValorFactura+"", subtl, txtAnticipos, txtCopago, txtDescuentos, Efectivo+"", CodEncFactura);
			}
			//String =req.getParameter("");
			//String =req.getParameter("");
		}
		
		
		if(va.equals("MDFN")){
			try {
				String CodDetalleFact=req.getParameter("CodDetalleFact");
				String CodEnc=req.getParameter("CodEnc");
				//1. verificar el estado del encabezado 
				rs=mm.ConsultarEncabezado(CodEnc);			
				if(rs.next()){
					String EstadoEnc=rs.getString("estado");
					// se hace el update llevando como parametro el encabezado, el estado del movimiento, y el codigo del movimiento;
					mm.ActualizarDatallePreFactura(CodEnc, CodDetalleFact, EstadoEnc);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("CRPF")){
			try {
				String CodAdm=req.getParameter("CodAdmision");
				String CodEps=req.getParameter("CodEnt");				
				out.print("<table width='100%' border='0'><tr>");
				rs=mm.ConsultarMovimientosActivos(CodAdm, CodEps);
				if(rs.next()){
					out.print("<td><input name='radiobutton' type='radio' value='radiobutton' id='"+rs.getString("cod_enc_factura")+"' ><a href='#' onclick='GenerarFacturaPrefacturaM("+rs.getString("cod_enc_factura")+")'>Movimientos Activos</a></td>");
				}
				rs.getStatement().getConnection().close();
				rs=mm.ConsultarPrefacturasPendientes(CodAdm, CodEps);
				int pr=0;			
				while(rs.next()){
					pr=pr+1;
					out.print("<td><input name='radiobutton' type='radio' value='radiobutton' id='"+rs.getString("cod_enc_factura")+"' ><a href='#' onclick='GenerarFacturaPrefactura("+rs.getString("cod_enc_factura")+","+pr+")'>Prefactura("+pr+")</a></td>");
				}
				rs.getStatement().getConnection().close();			
				out.print("</tr></table>");			
				//hacer la consulta si hay algun encabezado en estado 3
				out.print("<table width='100%' border='1' ><tr><td><div id='FacturasGeneradas' >");
				rs=mm.ConsultaFacturasGeneradas(CodAdm);
				while(rs.next()){
					out.print("<td><a href='#' onclick='ReporteFactura("+rs.getString("cod_enc_fact_fk")+")'>"+rs.getString("consecutivo")+"</a></td>");
				}
				rs.getStatement().getConnection().close();
				//hacer la consulta con los encabezados en estado 1 que son facturas generadas			
				out.print("</div></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("2F")){//Mostrar Movimientos de fact det factura
			String CodAdm = req.getParameter("CodAdm");	
			
			out.print("<table width='100%' border='1' class='style6' ><tr>" +
					"<td width='10%'></td>" +
					"<td width='53%'></td>" +
					"<td width='11%'></td>" +
					"<td width='10%'></td>" +
					"<td width='10%'></td>" +
					"<td width='7%'></td></tr>");
				
					
					//out.print("<input name='enca' type='hidden' id='enca' value='"+rs1.getString(1)+"' />");
					int cont=0;
					try {
						rs=mm.ConsultarPrefacturasVacias(CodAdm);
						while(rs.next()){
							String CodEnca=rs.getString("cod_enc_factura");
							rs1=mm.VerificarDetalleEncabezado(rs.getString("cod_enc_factura"));
							if(rs1.next()){
								//tiene detalles se deja quieto.
								// si busca algun movimiento en facturado=0 o 3, 
								//entonces el atendido sigue dela admsion wueda en 0
								//si no tiene ningun detalle es 0 o 3
								//entonces el atendido se pone en 1
							}else{
								//no tiene detalles se elimina el encabezado.
								mm.EliminarEncabezado(CodEnca);
							}rs1.getStatement().getConnection().close();
						}
						rs.getStatement().getConnection().close();
						rs2=mm.FactDetalleFacturar(enca);//se tiene  que consultar los cargues que esten en estado 0
						while(rs2.next()){
							cont=cont+1;
							if(rs2.getString("tipopop").equals("1")){
								out.print("<tr><td>"+rs2.getString("cod_programa")+"</td>");
								out.print("<td>"+rs2.getString("nombre_programa")+"</td>");
								out.print("<td>"+rs2.getString("fecha_realizacion")+"</td>");	
								out.print("<td align='center'>"+rs2.getString("cantidad")+"</td>");
								out.print("<td align='right'>"+mm.formatMoneda(rs2.getString("valor"))+"</td>");								
								out.print("<td align='center'><input type='checkbox' name='chkDetalleFact' id='chkDetalleFact' value='"+rs2.getString("cod_det_factura")+"' /></td></tr>");						
							}else{
								out.print("<tr><td>"+rs2.getString("cod_paquete")+"</td>");
								out.print("<td>"+rs2.getString("nombre_paquete")+" ("+rs2.getString("nombre_programa")+") </td>");
								out.print("<td>"+rs2.getString("fecha_realizacion")+"</td>");	
								out.print("<td align='center'>"+rs2.getString("cantidad")+"</td>");
								out.print("<td align='right'>"+mm.formatMoneda(rs2.getString("valor"))+"</td>");								
								out.print("<td align='center'><input type='checkbox' name='chkDetalleFact' id='chkDetalleFact' value='"+rs2.getString("cod_det_factura")+"' /></td></tr>");
								
							}
							
						}
						out.print("<input type='hidden' id='txtContador' value="+cont+" /></table>");
						rs2.getStatement().close();
						} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
						}
					/////////////////////				
			}//fin va=2F
		
		
		if(va.equals("CPF")){
			///facturacion por cargues 
			String CodDetalleFact=req.getParameter("CodDetalleFact");
			String CodAdmision=req.getParameter("CodAdmision");
			String CodEnt=req.getParameter("CodEnt");
			String Cant=req.getParameter("Cant");
			try {
				//1. se crea el nuevo encabezado.
				mm.CrearNuevoEncabezado(CodEnt, CodAdmision);
				rs=mm.ConsultarNuevoEncabezado(CodEnt, CodAdmision,"3");			
				if(rs.next()){
					///se obtiene el codigo del encabezado nuevo
					String CodNuevoEnca=rs.getString("cod_enc_factura");
					if(Cant.equals("V")){
						int y=CodDetalleFact.split(":").length;
						String[] z=CodDetalleFact.split(":");		     	
						for(int x=0; x<y; x=x+1){ 
							mm.ActualizarDatallePreFactura(CodNuevoEnca, z[x], "3");
						}
					}
					if(Cant.equals("S")){
						mm.ActualizarDatallePreFactura(CodNuevoEnca, CodDetalleFact, "3");
					}
				}else{
					out.print("Ocurrio un error");
				}
				rs.getStatement().getConnection().close();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}//Fin dopost

}//Fin Control Facturacion
