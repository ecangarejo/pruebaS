
/***********************/
/**
 * controlador: ControlEspecialidades se encuentra la forma en que son 
 * creadas las especialidades y el como se actualizan.
 */
package pyp_controlador;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fact_metodo.MetodoMovimientos;

import pyp_metodo.PYP_MetodoAsignarCita;
import pyp_metodo.PYP_MetodoEspecialidades;



/**
 * Servlet implementation class Controimg_area
 */
public class PYP_ControlEspecialidades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		PYP_MetodoEspecialidades me= new PYP_MetodoEspecialidades();
		PYP_MetodoAsignarCita mac = new PYP_MetodoAsignarCita();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rsc=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		String NomEspecialidad=req.getParameter("NomEspecialidad");
		String CodigoEspecialidad=req.getParameter("CodigoEspecialidad");
		String CodEspe=req.getParameter("CodEspe");
		String NomEspeNuevo=req.getParameter("NomEspeNuevo");
		String NumReciboCaja=req.getParameter("NumReciboCaja");

		int abonosa=0;
		int ValorPena=0;
		





		if(va.equals("AcRC")){
		
			/*	1) se actualiza el valor del servicio.
				2) se actualiza el valor a recibir.
				3) se actualiza el valor de la cuota moderadora
				4) se actualiza el valor de la orden
				5) se actualiza el numero de la orden.
				6) se actualiza el concepto del recibo de caja
				7) se actualiza el tipo de pago
			*/
			
			
			String Abono=req.getParameter("Abono");
			String ValorLetra=req.getParameter("ValorLetra");
			String CuotaModeradora=req.getParameter("CuotaModeradora");
			String ValorOrden=req.getParameter("ValorOrden");
			String NumOrden=req.getParameter("NumOrden");
			String TotalPagar=req.getParameter("TotalPagar");
			String ValoraRecibir=req.getParameter("ValoraRecibir");
			
			String CodReciboCajaPrincipal=req.getParameter("CodReciboCajaPrincipal");
			String CodReciboCaja=req.getParameter("CodReciboCaja");
			String ValorCheCons=req.getParameter("ValorCheCons");
			String concepto=req.getParameter("concepto");
			String tpago=req.getParameter("tpago");
			//AACTUALIZAR RECIBO DE CAJA.
			
			//ValoraRecibir=formatMoneda(ValoraRecibir);
			String valorpleno="";
			mac.ActualizarReciboCajaPrincipal(CodReciboCajaPrincipal, TotalPagar);
			mac.ActualizarDetalleReciboCaja(Abono, CuotaModeradora, ValorOrden, NumOrden, ValorCheCons, CodReciboCaja,ValorLetra,concepto,tpago);

			String Tot="";
			String ValorAbonado="";
			String Valorpendiente="";
			int TotPagar=0;
			rs=mac.DatosParaReciboDeCaja(CodReciboCaja);
			try {
				if(rs.next()){
					Tot=formatMoneda(rs.getString(21));
					rs4=mac.AbonosReciboCaja(rs.getString(19));
					while(rs4.next()){
						abonosa=(abonosa+rs4.getInt(3))+ (rs4.getInt(15) + rs4.getInt(16)+ rs4.getInt(18));
					}
					rs4.getStatement().getConnection().close();						
					String abo=String.valueOf(abonosa);						
					TotPagar=rs.getInt(21);						
					ValorPena=TotPagar-abonosa;
					String ValPen=String.valueOf(ValorPena);						
					ValorAbonado=formatMoneda(abo);
					Valorpendiente=formatMoneda(ValPen);
					
				}
				rs.getStatement().getConnection().close();
				
				mac.ActualizarValoresDetalleReciboCaja(Tot, Valorpendiente, ValorAbonado, CodReciboCaja);
			//buscar si ya el recibo de caja esta pagado a totailidady cambiarle es tado a 1
				String abonadostr=abonosa+"";
				if(abonadostr.equals(TotPagar+"")){
					mac.ActualizarEstadoReciboCaja(CodReciboCaja);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			
			
		}


		if(va.equals("ARC")){
			rs=mac.DatosParaReciboDeCaja(NumReciboCaja);
			try {
				if(rs.next()){	
						out.print("<table border='0' width='100%' ><tr><td colspan='6'><div id='cabecera2' class='style11' align='center'>RECIBO DE CAJA </div><input type='hidden' name='txtCodReciboCaja' id='txtCodReciboCaja' value="+NumReciboCaja+" /><input type='hidden' name='txtCodReciboCajaPrincipal' id='txtCodReciboCajaPrincipal' value="+rs.getString(19)+" /></td></tr>");
						/**************************************************************/
						rs1=mac.DatosDelPaciente(rs.getString(21));
						if(rs1.next()){
							out.print("<tr><td width='12%' class='style12'>Ciudad y Fecha </td><td width='41%'>"+rs.getString(7)+"</td><td class='style12' colspan='2'>Valor Servicio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtTotalPagar' id='txtTotalPagar'  value="+rs.getString(21)+" /></td><td class='style12'>Valor a Recibir $<input name='txtAbono' type='text' id='txtAbono' value="+rs.getString(3)+"  onblur='Letras()' /></td></tr>");
							/**************************************************************/
							String ValChq="";
							if(rs.getString(18).equals("")){
								ValChq="0";
							}else{
								ValChq=rs.getString(18);
							}
							out.print("<tr><td width='12%' class='style12'>Recibido</td><td width='41%'>"+rs1.getString(1)+"</td><td class='style12' colspan='2' >Cuota Moderadora:&nbsp;&nbsp;$<input type='text' name='txtCuotaModeradora' id='txtCuotaModeradora' value="+rs.getString(15)+" /></td><td class='style12'>Valor Cheque/Cons $<input id='txtValorCheCons' value="+ValChq+" /></td></tr>");//ojo poner el valor del cheque aqui
							out.print("<tr><td width='12%' class='style12'>Empresa</td><td width='41%'>"+rs1.getString(2)+"</td><td class='style12' colspan='2' >Valor Orden:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtValorOrden' id='txtValorOrden' value="+rs.getString(16)+" onkeyup='Letras1()' /></td><td class='style12'>Numero Orden:<input type='text' name='txtNumOrden' id='txtNumOrden' value="+rs.getString(17)+"      ></td></tr>");
						}
						//rs1.getStatement().getConnection().close();
						out.print("<tr><td class='style12'>La Suma De: </td><td colspan='2'><div id='ValorLetras'>"+rs.getString(8)+"<input type=hidden id='txtValorLetra' value='"+rs.getString(8)+"' /></div></td><td></td></tr>");
						out.print("<tr><td class='style12'>Por Concepto De: </td>");
						out.print("<td><select name='cmbConcepto' id='cmbConcepto' ><option value='"+rs.getString(13)+"'>"+rs.getString(13)+"</option>" );
							rs3=mac.TipoConcepto();
							while(rs3.next()){
								out.print("<option value='"+rs3.getString(2)+"' >"+rs3.getString(2)+"</option>");
							}
							rs3.getStatement().getConnection().close();
							out.print("</select>");
						
						out.print("</td>");
						out.print("<td class='style12'>Tipo de Pago :</td><td><select id='tpago' ><option value='"+rs.getString(12)+"'>"+rs.getString(12)+"</option><option value='Efectivo'>Efectivo</option><option value='Consignacion'>Consignacion</option><option value='Cheque'>Cheque</option></select></td>");
						out.print("<td colspan='3' class='style12'><div id='Otros'><input type=hidden id='txtOtro' /></div></td></tr>");
						out.print("<tr><td colspan='6' class='style12' bgcolor='#999999'>&nbsp;</td></tr>");
						String Tot=formatMoneda(rs.getString(21));
						out.print("<tr><td class='style12'>Valor a Recibir </td><td>"+Tot+"</td><input type=hidden id='txtValoraRecibir' value='"+Tot+"' /><input type=hidden id='txtValoraRecibirPleno' value="+rs.getString(21)+" /> " +
								//"<td class='style12'>Tipo Pago </td><td colspan='2' class='style12'><label><input name='radiobutton' type='radio' value='radiobutton' id='Cheque' onclick='DatoTiPa()' >Cheque</label><label><input name='radiobutton' type='radio' value='radiobutton' id='Efectivo' onclick='DatoTiPa()'  >Efectivo</label><label><input name='radiobutton' type='radio' value='radiobutton' id='Consignacion' onclick='DatoTiPa()' >Consignacion</label>" +
								"</td></tr>");
						rs4=mac.AbonosReciboCaja(rs.getString(2));
						while(rs4.next()){
							abonosa=(abonosa+rs4.getInt(3))+ (rs4.getInt(15) + rs4.getInt(16)+ rs4.getInt(18));
						}
						rs4.getStatement().getConnection().close();						
						String abo=String.valueOf(abonosa);						
						int TotPagar=rs.getInt(21);						
						ValorPena=TotPagar-abonosa;
						String ValPen=String.valueOf(ValorPena);						
						out.print("<tr><td class='style12'>Valor Recibido </td><td>"+formatMoneda(abo)+"</td><input type=hidden id='txtValorRecibido' value='"+formatMoneda(abo)+"' /><input type=hidden id='txtValorRecibidoPleno' value='"+abo+"' /><td class='style12'>&nbsp;</td><td colspan='2' class='style12'><div id='TipoPago'><input type=hidden id='txtNumCons' /></div></td></tr>");
						out.print("<tr><td class='style12'>Valor Pendiente </td><td>"+formatMoneda(ValPen)+"</td><input type=hidden id='txtValorPendiente' value='"+formatMoneda(ValPen)+"' /><input type=hidden id='txtValorPendientePleno' value='"+ValPen+"' /><td class='style12'>&nbsp;</td><td colspan='2' class='style12'>&nbsp;</td></tr>");
						out.print("<tr><td colspan='5' class='style12' align='center'>&nbsp;</td></tr>");
						out.print("<tr><td colspan='5' class='style12' align='center'><input name='btnActualizarRc' type='button' class='btnStyle' id='btnActualizarRc' value='Actualizar' onclick='ActualizarReciboCaja()' ><input name='btnLimpiarRc' type='button' class='btnStyle' id='btnLimpiarRc' value='Limpiar' onclick='NuevoRc()'></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		


		
		if(va.equals("Ingresar")){
			out.print("<table width='100%' border='1'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Crear Especialidad </div></td></tr><tr><td>Nombre Especialidad </td><td><label><input name='txtNomEspecialidad' type='text' id='txtNomEspecialidad' size='60' /></label></td></tr>");
			out.print("<tr><td colspan='2'><div align='center'><input name='btnIngresarEspecialidad' type='button' id='btnIngresarEspecialidad' value='       Ingresar       '  onclick='IngresarEspecialidad()'/></div></td></tr></table>");
		}
		
		if(va.equals("Actualizar")){
			
			rs1=me.BuscarEspecialidadTodas();
			
			try {
				out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Actualizar Especialidad </div></td></tr><tr><td width='21%'>Nombre Especialidad </td><td colspan='3'><label><select name='cmbNomEspecialidad' id='cmbNomEspecialidad' onchange='BuscarEspecialidad()'><option value='Seleccione'>Seleccione</option>");
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				out.print("</select></label></td></tr><tr><td colspan='4'><div id='ActuEspe'></div></td></tr></table>");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("EspePerf")){
			out.print("<table width='100%' border='1'><tr><td>Seleccione Perfil</td><td><select name='cmbNomPerfil' id='cmbNomPerfil' ><option value='Seleccione'>Seleccione</option>");
			rs=me.BuscarPerfil();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString("codigo")+">"+rs.getString("nombre")+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</td><td>Seleccione Especialidad</td><td><select name='cmbNomEspecialidad' id='cmbNomEspecialidad' ><option value='Seleccione'>Seleccione</option>");
				rs1=me.BuscarEspecialidadTodas();
				while(rs1.next()){
					out.print("<option value="+rs1.getString("codigo")+">"+rs1.getString("nombre_especialidad")+"</option>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</td><td><input name='btnGuardar' type='button' id='btnGuardar' onClick='GuardarRelPerEsp()' value='Guardar'></td></tr>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("GPE")){
			String CodigoPerfil=req.getParameter("CodigoPerfil");
			rs=me.BuscarRelacionEspecialidadPerfil(CodigoEspecialidad, CodigoPerfil);
			try {
				if(rs.next()){
					out.print("Ya existe la relacion.");	
				}else{
					me.GuardarEspecialidadPerfil(CodigoEspecialidad, CodigoPerfil);
					out.print("Relacion Creada Satisfactoriamente.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		if(va.equals("1")){
			rs=me.BuscarEspecialidadNombre(NomEspecialidad);
			String validar="";
			try {
				if(rs.next()){
					validar=rs.getString(1);
					if(validar!=""){
						out.print("Esta Especialidad ya Esta Creada.");
					}					
				}
				else{
					me.CrearEspecialidad(NomEspecialidad);
					out.print("Especialidad Creada Con Exito.");
				}				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(va.equals("2")){
			rs=me.BuscarEspecialidadCodigo(CodigoEspecialidad);
			
			try {				
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td>Nombre Especialidad </td> <td width='44%'><input name='txtNomEspeNuevo' type='text' id='txtNomEspeNuevo' size='60' value='"+rs.getString(2)+"' /></td></tr>");
					out.print("<tr><td colspan='4'><div align='center'><input name='btnActualizarEspecialidad' type='button' id='btnActualizarEspecialidad' value='       Actualizar       '  onclick='ActualizarEspecialidad()'/><input name='txtCodEspe' type='hidden' id='txtCodEspe' value='"+rs.getString(1)+"'></div></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("3")){
			me.ActualizarEspecialidad(CodEspe, NomEspeNuevo);
			out.print("Especialidad Actualizada Con Exito.");
			
		}
		/*******************EMPIEZA EL CONTROLADOR PARA RECIBOS DE EGRESO********************/
		//MetodoAsignarCita mac= new MetodoAsignarCita();
		String Codusuario=req.getParameter("Codusuario");
		ResultSet re2=null;
		String mes="";
		/************************************************************************************/
		if(va.equals("eg1")){
			
			out.print("<table border='0' width='100%' ><tr><td colspan='11' class='style12'><div id='cabecera2' class='style11' align='center'><p>RELACION DE EGRESOS</p></div></td></tr>");
			
			re2=mac.CiudadFechaRecibo();
			try {
				if(re2.next()){
					mes=re2.getString(3);
					if(mes.equals("1")){mes="Enero";}     if(mes.equals("2")){mes="Febrero";}
					if(mes.equals("3")){mes="Marzo";}     if(mes.equals("4")){mes="Abril";}
					if(mes.equals("5")){mes="Mayo";}      if(mes.equals("6")){mes="Junio";}
					if(mes.equals("7")){mes="Julio";}     if(mes.equals("8")){mes="Agosto";}
					if(mes.equals("9")){mes="Septiembre";}if(mes.equals("10")){mes="Octubre";}
					if(mes.equals("11")){mes="Noviembre";} if(mes.equals("12")){mes="Diciembre";}
					String FechaLetras=re2.getString(1)+",  "+re2.getString(4)+" de "+mes+" "+re2.getString(2);
					out.print("<tr><td class='style12'>Fecha</td><td colspan='7'>"+FechaLetras+"<input type=hidden id='txtFechaLetra' value='"+FechaLetras+"' /></td><td width='5%' class='style12'>Cantidad</td><td width='13%' class='style12'><input name='txtAbono' type='text' id='txtAbono' onkeyup='Letras3()'><input name='txtValorLetra' type='hidden' id='txtValorLetra'/></td><td><div id='ValorLetras'></div></td></tr>");
					//out.print("<tr><td width='12%' class='style12'>Ciudad y Fecha </td><td width='41%'>"+rs2.getString(1)+",  "+rs2.getString(4)+" de "+mes+" "+rs2.getString(2)+"</td><input type=hidden id='txtFechaLetra' value='"+FechaLetras+"' /><td class='style12' colspan='2'>Valor Servicio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtTotalPagar' id='txtTotalPagar' readonly='' value="+rs1.getString(3)+" /></td><td class='style12'>Valor a Recibir $<input name='txtAbono' type='text' id='txtAbono'  onkeyup='Letras()' /></td></tr>");
				}
				re2.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
			out.print("<tr><td class='style12'>Nombre</td><td width='25%'><input name='txtNombre' type='text' id='txtNombre' size='45'></td><td width='6%' class='style12'>&nbsp;</td><td width='7%' class='style12'>&nbsp;</td>");
			out.print("<td width='8%' class='style12'>C.C-NIT</td><td width='5%' class='style12'><input name='txtIdentificacion' type='text' id='txtIdentificacion' size='25'></td><td width='8%' class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td>");
			out.print("<td class='style12'>&nbsp;</td><td>&nbsp;</td></tr><tr><td width='6%' class='style12'>Concepto</td><td colspan='6'><input name='txtConcepto' type='text' id='txtConcepto' size='70'></td><td colspan='3' class='style12'>Forma de Pago &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			out.print("<label><input name='radiobutton' type='radio' value='radiobutton' id='Efectivo' onclick='DatoTiPa()'>Efectivo</label><label><input name='radiobutton' type='radio' value='radiobutton' id='Cheque' onclick='DatoTiPa()' >Cheque</label></td><td width='4%'><div id='TipoPago'><input type=hidden id='txtNumCons' /></div></td></tr>");
			out.print("<tr><td class='style12'>Observacion</td><td><textarea name=' txtObservacion' cols='40' rows='3' id='txtObservacion'></textarea></td><td colspan='5' class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td><td class='style12'>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print("<tr><td colspan='11'><div id='RelacionEgresos'></div></td></tr>");
			out.print("<tr><td colspan='11' align='center'><input name='btnGuardar' type='button' id='btnGuardar' onClick='GuardarEgresos()' value='Guardar'></td></tr></table>");
			
			/*out.print("<table border='0' width='100%' ><tr><td colspan='6' class='style12'><div id='cabecera2' class='style11' align='center'><p>RELACION DE EGRESOS</p></div></td></tr>");
			out.print("<tr><td width='6%' class='style12'>Concepto</td><td width='25%'><input name='txtConcepto' type='text' id='txtConcepto' size='45'></td><td width='5%' class='style12'>Cantidad</td><td width='25%'><input name='txtCantidad' type='text' id='txtCantidad'></td>");
			out.print("<td width='7%' class='style12'>Observacion</td><td width='32%'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion'></textarea></td></tr>");
			out.print("<tr><td colspan='6'><div id='RelacionEgresos'></div></td></tr>");
			out.print("<tr><td colspan='6' align='center'><input name='btnGuardar' type='button' id='btnGuardar' onClick='GuardarEgresos()' value='Guardar'></td></tr></table>");
			*/
			out.print("<table width='100%'><tr><td>Nombre</td><td>Identificacion</td><td>Concepto</td><td>Cantidad Efectivo</td><td>Cantidad Cheque</td><td>Numero Cheque</td><td>Fecha</td><td>Observacion</td></tr>");
			rs=mac.ObtenerEgresos();
			try {
				while(rs.next()){
					
					out.print("<tr><td>"+rs.getString(11)+"</td><td>"+rs.getString(12)+"</td><td><a href='#' onclick='ImprimirReciboEgreso("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+formatMoneda(rs.getString(3))+"</td><td>"+formatMoneda(rs.getString(13))+"</td><td>"+rs.getString(14)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(4)+"</td></tr>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
			
		}
		if(va.equals("eg2")){
			
			String ValCheque="";
			String Concepto=req.getParameter("Concepto");
			String Cantidad=req.getParameter("Cantidad");
			String Observacion=req.getParameter("Observacion");
			String FechaLetra=req.getParameter("FechaLetra");
			String ValorLetra=req.getParameter("ValorLetra");
			String TipoPagoEg=req.getParameter("TipoPago");
			String Nombre=req.getParameter("Nombre");
			String Identificacion=req.getParameter("Identificacion");
			String NumCheque=req.getParameter("NumCheque");
			
			if(TipoPagoEg.equals("Cheque")){
				ValCheque=Cantidad;
				Cantidad="0";
			}else{
				ValCheque="0";
				NumCheque="-";
			}
			
			java.util.Date fechaActualEg = new java.util.Date();
			java.sql.Date FechaEg = new java.sql.Date(fechaActualEg.getTime());		
			java.sql.Time HoraEg = new java.sql.Time(fechaActualEg.getTime());
			
			mac.InsertarReciboEgreso(Concepto, Cantidad, Observacion, FechaEg, HoraEg, Codusuario,FechaLetra,TipoPagoEg,ValorLetra,Nombre,Identificacion,ValCheque,NumCheque);
		}
		
		
		/************************************************************************************/
		/***********************FIN EL CONTROLADOR PARA RECIBOS DE EGRESO********************/
		
		/********************EMPIEZA EL CONTROLADOR PARA RECIBOS DE CAJA**********************/
		
		String TipoDocumento=req.getParameter("TipoDocumento");
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String TotalPagar=req.getParameter("TotalPagar");
		
		String CantAbono=req.getParameter("Abono");
		String ValoraRecibir=req.getParameter("ValoraRecibir");
		String ValorRecibido=req.getParameter("ValorRecibido");
		//String ValorPendiente=req.getParameter("ValorPendiente");
		String FechaLetra=req.getParameter("FechaLetra");
		String CuotaModeradora=req.getParameter("CuotaModeradora");
		String ValorLetra=req.getParameter("ValorLetra"); 
		String Concepto=req.getParameter("Concepto");
		String TipoPago=req.getParameter("TipoPago");
		String CodCita=req.getParameter("CodCita");
		String CodReciboCaja=req.getParameter("CodReciboCaja");
		//ResultSet rs2=null;
		//ResultSet rs3=null;
		//ResultSet rs4=null;
		ResultSet rs5=null;
		//ResultSet rs6=null;
		//ResultSet rs7=null;
		int abonos=0;
		int ValorPen=0;
		//String mes="";
		String CodPac=req.getParameter("CodPac");
		
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		String CodPac1="";
		if(va.equals("rc1")){
			rs=mac.BuscarPacienteRC(TipoDocumento, NumeroDocumento);
			try {
				if(rs.next()){	
					CodPac1=rs.getString(1);
					rs1=mac.BuscarRecibodeCajaActivo("0");
					if(rs1.next()){
						out.print("<table border='0' width='100%' ><tr><td colspan='6'><div id='cabecera2' class='style11' align='center'>RECIBO DE CAJA </div><input type='hidden' name='txtCodReciboCaja' id='txtCodReciboCaja' value="+rs1.getString(1)+" /><input type='hidden' name='txtCodPac' id='txtCodPac' value="+rs.getString(1)+"  /><input type='hidden' name='txtCodCita' id='txtCodCita' value="+rs.getString(6)+" /></td></tr>");
						/**************************************************************/
						rs2=mac.CiudadFechaRecibo();
						if(rs2.next()){
							mes=rs2.getString(3);
							if(mes.equals("1")){mes="Enero";}     if(mes.equals("2")){mes="Febrero";}
							if(mes.equals("3")){mes="Marzo";}     if(mes.equals("4")){mes="Abril";}
							if(mes.equals("5")){mes="Mayo";}      if(mes.equals("6")){mes="Junio";}
							if(mes.equals("7")){mes="Julio";}     if(mes.equals("8")){mes="Agosto";}
							if(mes.equals("9")){mes="Septiembre";}if(mes.equals("10")){mes="Octubre";}
							if(mes.equals("11")){mes="Noviembre";} if(mes.equals("12")){mes="Diciembre";}
							String FechaLetras=rs2.getString(1)+",  "+rs2.getString(4)+" de "+mes+" "+rs2.getString(2);
						out.print("<tr><td width='12%' class='style12'>Ciudad y Fecha </td><td width='41%'>"+rs2.getString(1)+",  "+rs2.getString(4)+" de "+mes+" "+rs2.getString(2)+"</td><input type=hidden id='txtFechaLetra' value='"+FechaLetras+"' /><td class='style12' colspan='2'>Valor Servicio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtTotalPagar' id='txtTotalPagar' readonly='' value="+rs1.getString(3)+" /></td><td class='style12'>Valor a Recibir $<input name='txtAbono' type='text' id='txtAbono'  onkeyup='Letras()' /></td></tr>");
						}
						rs2.getStatement().getConnection().close();
						/**************************************************************/
						out.print("<tr><td width='12%' class='style12'>Recibido</td><td width='41%'>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td><td class='style12' colspan='2' >Cuota Moderadora:&nbsp;&nbsp;$<input type='text' name='txtCuotaModeradora' id='txtCuotaModeradora' /></td></tr>");
						out.print("<tr><td width='12%' class='style12'>Empresa</td><td width='41%'>"+rs.getString(5)+"</td><td class='style12' colspan='2' >Valor Orden:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtValorOrden' id='txtValorOrden' onkeyup='Letras1()' /></td><td class='style12'>Numero Orden:$<input type='text' name='txtNumOrden' id='txtNumOrden' /></td></tr>");
						out.print("<tr><td class='style12'>La Suma De: </td><td colspan='2'><div id='ValorLetras'><input type=hidden id='txtValorLetra' /></div></td><td></td></tr>");
						out.print("<tr><td class='style12'>Por Concepto De: </td><td>");
						if(rs1.getString(5).equals("0")){
							out.print("<select name='cmbConcepto' id='cmbConcepto' onchange='VerOtro()'><option value='Seleccione'>Seleccione</option>" );
						}else{
							out.print("<select name='cmbConcepto' id='cmbConcepto' onchange='VerOtro()'><option value='"+rs1.getString(5)+"'>"+rs1.getString(5)+"</option>" );
						}
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
						rs4=mac.AbonosReciboCaja(rs1.getString(1));
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
						/***************************************************************************************/
						out.print("<table width='100%' border='1'><tr><td width='50%'>");
						out.print("<table width='100%' border='1'><tr><td align='center' colspan='3' >HISTORIAL DE RECIBOS</td></tr>");
						rs5=mac.BuscarRecibodeCajaHistorial(CodPac1);
						out.print("<tr><td>Fecha</td><td>Concepto</td><td>Valor</td></tr>");
						while(rs5.next()){
							out.print("<tr><td><a href='#' onclick='VerRecibos("+rs5.getString(1)+")'>"+rs5.getString(6)+"</a></td><td>"+rs5.getString(5)+"</td><td>"+formatMoneda(rs5.getString(3))+"</td></tr>");
						}
						rs5.getStatement().getConnection().close();
					
						out.print("</table>");
						out.print("</td>");
						out.print("<td width='50%'><table width='100%' border='1' ><tr><td colspan='3' id='subrecibos'></td></tr></table>");
						out.print("</td></table>");
					}else{
/***********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************/
						out.print("<table border='0' width='100%' ><tr><td colspan='6'><div id='cabecera2' class='style11' align='center'>RECIBO DE CAJA </div><input type='hidden' name='txtCodReciboCaja' id='txtCodReciboCaja'  /><input type='hidden' name='txtCodPac' id='txtCodPac' value="+CodPac1+"  /><input type='hidden' name='txtCodCita' id='txtCodCita' value="+rs.getString(6)+" /></td></tr>");
						rs2=mac.CiudadFechaRecibo();
						if(rs2.next()){
							mes=rs2.getString(3);
							if(mes.equals("1")){mes="Enero";}     if(mes.equals("2")){mes="Febrero";}
							if(mes.equals("3")){mes="Marzo";}     if(mes.equals("4")){mes="Abril";}
							if(mes.equals("5")){mes="Mayo";}      if(mes.equals("6")){mes="Junio";}
							if(mes.equals("7")){mes="Julio";}     if(mes.equals("8")){mes="Agosto";}
							if(mes.equals("9")){mes="Septiembre";}if(mes.equals("10")){mes="Octubre";}
							if(mes.equals("11")){mes="Noviembre";} if(mes.equals("12")){mes="Diciembre";}
							
							String FechaLetras=rs2.getString(1)+",  "+rs2.getString(4)+" de "+mes+" "+rs2.getString(2);
						out.print("<tr><td width='12%' class='style12'>Ciudad y Fecha </td><td width='41%'>"+rs2.getString(1)+",  "+rs2.getString(4)+" de "+mes+" "+rs2.getString(2)+"</td><input type=hidden id='txtFechaLetra' value='"+FechaLetras+"' /><td class='style12' colspan='2'>Valor Servicio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$<input type='text' name='txtTotalPagar' id='txtTotalPagar' /></td><td class='style12'>Valor a Recibir $<input name='txtAbono' type='text' id='txtAbono'  onkeyup='Letras()' /></td></tr>");
						}
						rs2.getStatement().getConnection().close();
						out.print("<tr><td width='12%' class='style12'>Recibido</td><td width='41%'>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</td><td class='style12' colspan='2' >&nbsp;&nbsp;<input type='hidden' name='txtCuotaModeradora' id='txtCuotaModeradora' /></td></tr>");
						out.print("<tr><td width='12%' class='style12'>Empresa</td><td width='41%'>"+rs.getString(5)+"</td><td class='style12' colspan='2' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Observacion<textarea id='txtObservacion' rows='3' col='500'></textarea><input type='hidden' name='txtValorOrden' id='txtValorOrden' onkeyup='Letras1()' /></td><td class='style12'><input type='hidden' name='txtNumOrden' id='txtNumOrden' /></td></tr>");
						out.print("<tr><td class='style12'>La Suma De: </td><td colspan='2'><div id='ValorLetras'><input type=hidden id='txtValorLetra' /></div></td><td></td></tr>");
						out.print("<tr><td class='style12'>Por Concepto De: </td><td>");
						out.print("<select name='cmbConcepto' id='cmbConcepto' onchange='VerOtro()'><option value='Seleccione'>Seleccione</option>" );
							rs3=mac.TipoConcepto();
							while(rs3.next()){
								out.print("<option value='"+rs3.getString(1)+"' >"+rs3.getString(2)+"</option>");
							}
							rs3.getStatement().getConnection().close();
							out.print("</select>");
						
						
						out.print("</td><td colspan='3' class='style12'><div id='Otros'><input type=hidden id='txtOtro' /></div></td></tr>");
						out.print("<tr><td colspan='6' class='style12' bgcolor='#999999'>&nbsp;</td></tr>");
						out.print("<tr><td class='style12'>Valor a Recibir </td><td></td><input type=hidden id='txtValoraRecibir' value='' /><input type=hidden id='txtValoraRecibirPleno' value='' /><td class='style12'>Tipo Pago </td><td colspan='2' class='style12'><label><input name='radiobutton' type='radio' value='radiobutton' id='Cheque' onclick='DatoTiPa()' >Cheque</label><label><input name='radiobutton' type='radio' value='radiobutton' id='Efectivo' onclick='DatoTiPa()'  >Efectivo</label><label><input name='radiobutton' type='radio' value='radiobutton' id='Consignacion' onclick='DatoTiPa()' >Consignacion</label></td></tr>");
						String abo=String.valueOf(abonos);						
						String ValPen=String.valueOf(ValorPen);						
						out.print("<tr><td class='style12'>Valor Recibido </td><td>"+formatMoneda(abo)+"</td><input type=hidden id='txtValorRecibido' value='"+formatMoneda(abo)+"' /><input type=hidden id='txtValorRecibidoPleno' value='"+abo+"' /><td class='style12'>&nbsp;</td><td colspan='2' class='style12'><div id='TipoPago'><input type=hidden id='txtNumCons' /></div></td></tr>");
						out.print("<tr><td class='style12'>Valor Pendiente </td><td>"+formatMoneda(ValPen)+"</td><input type=hidden id='txtValorPendiente' value='"+formatMoneda(ValPen)+"' /><input type=hidden id='txtValorPendientePleno' value='"+ValPen+"' /><td class='style12'>&nbsp;</td><td colspan='2' class='style12'>&nbsp;</td></tr>");
						out.print("<tr><td colspan='5' class='style12' align='center'>&nbsp;</td></tr>");
						out.print("<tr><td colspan='5' class='style12' align='center'><input name='btnGuardarRc' type='button' class='btnStyle' id='btnGuardarRc' value='Guardar' onclick='GuardarReciboCaja()' ><input name='btnLimpiarRc' type='button' class='btnStyle' id='btnLimpiarRc' value='Limpiar' onclick='NuevoRc()'></td></tr></table>");
						/***************************************************************************************/
						out.print("<table width='100%' border='1'><tr><td width='50%'>");
						out.print("<table width='100%' border='1'><tr><td align='center' colspan='3' >HISTORIAL DE RECIBOS</td></tr>");
						rs5=mac.BuscarRecibodeCajaHistorial(CodPac1);
						out.print("<tr><td>Fecha</td><td>Concepto</td><td>Valor</td></tr>");
						while(rs5.next()){
							out.print("<tr><td><a href='#' onclick='VerRecibos("+rs5.getString(1)+")'>"+rs5.getString(6)+"</a></td><td>"+rs5.getString(5)+"</td><td>"+formatMoneda(rs5.getString(3))+"</td></tr>");
						}
						rs5.getStatement().getConnection().close();					
						out.print("</table>");
						out.print("</td>");
						out.print("<td width='50%'><table width='100%' border='1' ><tr><td colspan='3' id='subrecibos'></td></tr></table>");
					}
					rs1.getStatement().getConnection().close();
				}else{
					out.print("Paciente Con la Identificacion "+TipoDocumento+"-"+NumeroDocumento+" No Tiene Relacionado Ninguna Cita Activa.\tPorfavor asignar una cita al paciente. ");					
				}					
					rs.getStatement().getConnection().close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
				
		
			} 
				
		
			

		if(va.equals("let")){
			String Letras="";
			Letras=Convertir(CantAbono);
			out.print(Letras+"<input type=hidden id='txtValorLetra' value='"+Letras+"' />");
		}
		if(va.equals("let1")){
			String Letras="";
			Letras=Convertir(CantAbono);
			out.print(Letras);
		}
		
if(va.equals("rc2")){
			
			
			int ValRecPle=0;
			String ValPendi="";
			int ValRecido=0;
			String Observacion=req.getParameter("Observacion");
			String NumOrden=req.getParameter("NumOrden");
			String ValorOrden=req.getParameter("ValorOrden");
			String ValorPendientePleno=req.getParameter("ValorPendientePleno");
			String ValorRecibidoPleno=req.getParameter("ValorRecibidoPleno");
			String ValoraRecibirPleno=req.getParameter("ValoraRecibirPleno");
			String cheqconsig=req.getParameter("cheqconsig");
			//System.out.println("CuotaModeradora "+CuotaModeradora+" ValorOrden "+ValorOrden+" CantAbono "+CantAbono);
			int valple=0;
			try {
				if(CodReciboCaja.equals("")){
					//CUANDO ES UN RECIBO DE CAJA NUEVO O POR PRIMERA VEZ
					mac.IngresarReciboCaja(CodPac, TotalPagar, Fecha, Hora, Codusuario);
					rs1=mac.BuscarRecibodeCajaActivo(CodPac);
					if(rs1.next()){
						CodReciboCaja=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
					//valple=Integer.parseInt(ValorPendientePleno)-Integer.parseInt(CantAbono);		
					ValRecPle=Integer.parseInt(TotalPagar)-Integer.parseInt(CantAbono);
					ValoraRecibir=formatMoneda(ValoraRecibir);
					//String valorpleno="";
					//valorpleno=valple+"";
					if(ValorRecibido.equals("$ 0")){
						ValorRecibido=formatMoneda(ValorOrden);
					}
					//System.out.println(" cheqconsig 111 "+cheqconsig);
					if(!cheqconsig.equals("")){
						ValRecido=(Integer.parseInt(cheqconsig)+ Integer.parseInt(CuotaModeradora));
						ValRecPle=(Integer.parseInt(TotalPagar))-(Integer.parseInt(CuotaModeradora)+Integer.parseInt(cheqconsig));
						valple=Integer.parseInt(ValorPendientePleno)-Integer.parseInt(cheqconsig);	
					}else{					
						ValRecido=(Integer.parseInt(CantAbono)+ Integer.parseInt(CuotaModeradora));
					}
					//ValReciPle=formatMoneda(CantAbono);
					//System.out.print("1111 ValRecido "+ValRecido+" ValRecPle "+ValRecPle+" ValoraRecibirPleno "+ValoraRecibirPleno);
					
					//int ValorRecibidoCuota=(Integer.parseInt(ValorOrden)+ Integer.parseInt(CuotaModeradora));
					if(!ValorOrden.equals("0")){
						CantAbono="0";
						ValRecPle=(Integer.parseInt(ValorOrden)+ Integer.parseInt(CuotaModeradora))-Integer.parseInt(TotalPagar);
						
					}
					mac.IngresarDetalleReciboCaja(CodReciboCaja, CantAbono, Fecha, Hora, 
							Codusuario, FechaLetra, ValorLetra, 
							formatMoneda(ValRecido+""), formatMoneda(ValRecPle+""),formatMoneda(TotalPagar),
							TipoPago,Concepto,CodCita,CuotaModeradora,NumOrden,ValorOrden,cheqconsig,Observacion);
					
					mac.ActualizarReciboCaja(CodReciboCaja, Concepto);
						
					int abonado=0;
					String abonadostr="";
					rs4=mac.AbonosReciboCaja(CodReciboCaja);
					while(rs4.next()){
						abonado=(abonado+rs4.getInt(3))+ (rs4.getInt(15) + rs4.getInt(16)+ rs4.getInt(18));
					}
					rs4.getStatement().getConnection().close();	
					
					abonadostr=abonado+"";
					//System.out.println("ValoraRecibirPleno "+ValoraRecibirPleno+" abonadostr "+abonadostr);
						ValPendi=formatMoneda(ValorPendientePleno);	
						//ValReciPle=formatMoneda(ValRecPle+"");				
						formatMoneda(ValRecPle+""); 	
						if(abonadostr.equals(TotalPagar)){
							mac.ActualizarEstadoReciboCaja(CodReciboCaja);
						}
						/*if(ValoraRecibirPleno.equals(abonadostr)){
							mac.ActualizarEstadoReciboCaja(CodReciboCaja);
						}else{
							if(ValoraRecibirPleno.equals(abonadostr)){
								mac.ActualizarEstadoReciboCaja(CodReciboCaja);
							}
						}*/
						rs=mac.BuscarDetalleReciboCaja(Fecha, Hora);
						String NumeroReciboCaja="";
					if(rs.next()){
						out.print(rs.getString(1));
						NumeroReciboCaja=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				//**	
				/****************************************************/
				//se busca el año y periodo
				java.util.Date fechaS = new Date();
				Calendar c = new GregorianCalendar();
				String dia = Integer.toString(c.get(Calendar.DATE));
				String mes0 = Integer.toString(c.get(Calendar.MONTH));
				String annio = Integer.toString(c.get(Calendar.YEAR));
				int m=Integer.parseInt(mes0)+1;
				mes0=String.valueOf(m);
				int d=Integer.parseInt(dia);
				if(d<10){dia="0"+d;}
				if(m<10){mes0="0"+m;}			
				String fechacjmysql=annio+"-"+mes0+"-"+dia;
				//se busca el consecutivo de el recibo de caja
				String conso="";
				String cons="";
				String consn="";
				MetodoMovimientos mm = new MetodoMovimientos();
				rs = mm.ConsecutivosdeCuentas("170");
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
					
					//se crea el registro en la tabla cont_documento 
					mm.CrearDocumento(annio,mes0,"170",consn,fechacjmysql,"RECIBO DE CAJA MENOR "+Concepto,CantAbono,CantAbono,"0","",Codusuario,fechacjmysql,Hora+"");
					//se actualiza el consecutivo del documento
					int ctan=Integer.parseInt(conso)+1;
					conso=String.valueOf(ctan);
					mm.ActualizarConsecutivo(conso,"170");
					//SE BUSCA EL CODIGO DEL DOCUMENTO CREADO
					String Coddocu="";
					rs = mm.ConsecutivoUDocumento(consn);if(rs.next()){Coddocu=rs.getString(1);}rs.getStatement().getConnection().close();
					//SE CREA EL DETALLE DEL RECIBO DE CAJA
					//se buscar el codigo y nombre de la cuenta de recibos de caja
					mm.CrearDetalleDocumento(Coddocu,"117","1","2","2","PAGOS POR CUENTA DE TERCEROS","0","RCAM "+NumeroReciboCaja,"0",CantAbono,"0","RCAM");
					
				    //CREAR EL DOCUMENTO CONTABLE DE LAS SALIDAS
					
					//VERIFICAR A QUE CUENTA VA HACER REFERENCIA (CAJA)
					
					//GENERAR EL ENCABEZADO DE LA TABLA CONT_DOCUMENTOS
					
					// GENERAR EL DETALLE EN LA TABLA CONT_DETALLE_DOCUMENTOS
					
					// AUMENTAR EL CONSECUITVO DEL DOCUMENTO
					
					
					
				}else{		
					//SI TIENE PAGO PENDIENTE EL RECIBO DE CAJA
					ValoraRecibir=formatMoneda(ValoraRecibir);
					String valorpleno="";
					
					if(ValorRecibido.equals("$ 0")){
						ValorRecibido=formatMoneda(ValorOrden);
					}
					
					//System.out.println(" cheqconsig 22 "+cheqconsig);
					if(!cheqconsig.equals("")){
						ValRecido=(Integer.parseInt(cheqconsig)+ Integer.parseInt(CuotaModeradora))+Integer.parseInt(ValorRecibidoPleno);
						ValRecPle=(Integer.parseInt(TotalPagar))-(Integer.parseInt(CuotaModeradora)+Integer.parseInt(cheqconsig));
						valple=Integer.parseInt(ValorPendientePleno)-Integer.parseInt(cheqconsig);	
					}else{					
						ValRecido=(Integer.parseInt(CantAbono)+ Integer.parseInt(CuotaModeradora))+Integer.parseInt(ValorRecibidoPleno);
						ValRecPle=(Integer.parseInt(TotalPagar)-Integer.parseInt(CantAbono))+ Integer.parseInt(CuotaModeradora);
						valple=Integer.parseInt(ValorPendientePleno)-Integer.parseInt(CantAbono);	
					}
					
					//System.out.print("22222 ValRecido "+ValRecido+" valorpleno "+valorpleno+" ValoraRecibirPleno "+ValoraRecibirPleno);
					valorpleno=valple+"";
					//ValRecido=(Integer.parseInt(CantAbono)+ Integer.parseInt(ValorRecibidoPleno));
					//ValReciPle=formatMoneda(ValRecPle+"");					
					mac.IngresarDetalleReciboCaja(CodReciboCaja, CantAbono, Fecha, Hora, 
							Codusuario, FechaLetra, ValorLetra, formatMoneda(ValRecido+""), 
							formatMoneda(valorpleno), formatMoneda(ValoraRecibirPleno),TipoPago,Concepto,
							CodCita,CuotaModeradora,NumOrden,ValorOrden,cheqconsig,Observacion);
					mac.ActualizarReciboCaja(CodReciboCaja, Concepto);
					rs=mac.BuscarDetalleReciboCaja(Fecha, Hora);	
					int abonado=0;
					String abonadostr="";
					rs4=mac.AbonosReciboCaja(CodReciboCaja);
					while(rs4.next()){						
						abonado=(abonado+rs4.getInt(3))+(rs4.getInt(15) + rs4.getInt(16)+ rs4.getInt(18));
					}
					rs4.getStatement().getConnection().close();	
				
					abonadostr=abonado+"";
					ValPendi=formatMoneda(ValorPendientePleno);	
					formatMoneda(ValRecPle+""); 				
					if(ValoraRecibirPleno.equals(abonadostr)){
						mac.ActualizarEstadoReciboCaja(CodReciboCaja);
					}else{
						if(ValoraRecibirPleno.equals(abonadostr)){
							mac.ActualizarEstadoReciboCaja(CodReciboCaja);
						}
					}
					
				if(rs.next()){
					out.print(rs.getString(1));
				}
				rs.getStatement().getConnection().close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("rc3")){
			String CodRecCaj=req.getParameter("CodRecCaj");
			rs=mac.AbonosReciboCaja(CodRecCaj);
			out.print("<table width='100%' border='1' ><tr><td colspan='5' align='center'>DETALLE RECIBO DE CAJA</td></tr>" +
					"<tr><td>Fecha</td>" +
					"<td>Abono</td>" +
					"<td>Valor Recibido</td>" +
					"<td>Valor Pendiente</td>" +
					"<td>Valor a recibir</td></tr>");
			try {
				while(rs.next()){
				out.print("<tr><td><a href='#' onclick='ImprimirReciboCaja("+rs.getString(1)+")' >"+rs.getString(4)+"</a></td>" +
						"<td>"+formatMoneda(rs.getString(3))+"</td>" +
								"<td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td>" +
										"<td>"+formatMoneda(rs.getString(18))+"</td>" +
												"</tr>");
				}
				rs.getStatement().getConnection().close();
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("rc3.1")){
			if((Concepto.equals("11"))||(Concepto.equals("12"))||(Concepto.equals("10"))){
				out.print("Escriba la Descripcion: <input type=text id='txtOtro' />");
			}else{
				out.print("<input type=hidden id='txtOtro' />");
			}
		}
		
		if(va.equals("rc3.2")){
			if((TipoPago.equals("Cheque"))||(TipoPago.equals("Consignacion"))){
				out.print("Numero de Consignacion o Cheque   <input type=hiden id='txtNumCons' />");
			}else{
				out.print("<input type=hidden id='txtNumCons' />");
			}
		}
		
		/********************TERMINA EL CONTROLADOR PARA RECIBOS DE CAJA**********************/
				
		
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

//public class Numero_a_Letra {

    private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};

   /*public Numero_a_Letra() {
   }*/

    public String Convertir(String numero) {
        String literal = "";
        String parte_decimal;    
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if(numero.indexOf(",")==-1){
            numero = numero + "";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");            
            //de da formato al numero decimal
            parte_decimal =  "  Pesos";
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
           // if (mayusculas) {
                return (literal + parte_decimal).toUpperCase();
           // } else {
               // return (literal + parte_decimal);
            //}
        } else {//error, no se puede convertir
            return literal = null;
        }
    }

    /* funciones para convertir los numeros a literales */

    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private String getCentenas(String num) {// 999 o 099
        if( Integer.parseInt(num)>99 ){//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " cien ";
            } else {
                 return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            } 
        }else{//por Ej. 099 
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num)+"");            
        }        
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n="";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);           
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000        
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if(millon.length()>1){
            n = getCentenas(millon) + "millones ";
        }else{
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);        
    }
//}
}
