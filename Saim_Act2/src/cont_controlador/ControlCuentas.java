package cont_controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.Conexion;


import cont_metodo.MetodoCuentas;
import cont_metodo.MetodoProveedor;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JDialog;

import fact_metodo.MetodoMovimientos;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ControlCuentas extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2452236813393848348L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ResultSet rs=null;	
		res.setContentType("text/html;charset=UTF-8");
		String Cad =req.getParameter("Cad");  
		MetodoCuentas mc= new MetodoCuentas();
		int accion;
		accion = Integer.parseInt(req.getParameter("txtAccion"));
		if(accion == 26){          
			try {
				rs =mc.AutoCompletarCuenta(Cad);
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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoCuentas mc= new MetodoCuentas();	
		MetodoProveedor mpr= new MetodoProveedor();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs9=null;
		ResultSet rs10=null;
		ResultSet rs11=null;
		ResultSet rs12=null;
		ResultSet rsd1=null;
		ResultSet rsd2=null;
		ResultSet rsrc=null;
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
		java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
		//ResultSet rs3=null;
		//ResultSet rs4=null;
		/**********************************************************/
		String cod_cuenta_fk=req.getParameter("cod_cuenta_fk");
		String estado=req.getParameter("estado");
		String fecha_factura=req.getParameter("fecha_factura");
		String fecha_insercion=req.getParameter("fecha_insercion");
		String hora_insercion=req.getParameter("hora_insercion");
		String Retiva=req.getParameter("Retiva");
		String numero_factura=req.getParameter("numero_factura");
		String observacion=req.getParameter("observacion");
		String precio_factura=req.getParameter("precio_factura");
		String ret_ica=req.getParameter("ret_ica");
		String tipo=req.getParameter("tipo");
		String usuario_insercion=req.getParameter("usuario_insercion");
		String DatoBusqueda=req.getParameter("Busqueda");
		String CodCuenta=req.getParameter("CodCuenta");
		
		String CentroCosto=req.getParameter("CentroCosto");
		String RetFuente=req.getParameter("RetFuente");
		
		String Observacion=req.getParameter("Observacion");
		String NumSoporte=req.getParameter("NumSoporte");
		String fechaPA=req.getParameter("fechaPA");
		String cantidad=req.getParameter("cantidad");
		String CodFactura=req.getParameter("CodFactura");
		/********************************************************/
		String Factura=req.getParameter("Factura");
		String CodEntidad=req.getParameter("CodEntidad");
		String CodUsu=req.getParameter("CodUsu");
		String Tipo_Pago=req.getParameter("Tipo_Pago");
		String ObservacionRC=req.getParameter("ObservacionRC");
		String Concepto=req.getParameter("Concepto");
		String valor_abono=req.getParameter("Abono");
		String valor_Factura=req.getParameter("valor_Factura");
		String FechaPago=req.getParameter("FechaPago");
		String valor_total="";
		String CodDetCaja=req.getParameter("CodDetCaja");
		String CodRecCaja=req.getParameter("CodReciboCaja");
		String ValorPagado=req.getParameter("ValorPagado");
		String FormaPago=req.getParameter("FormaPago");
		String ValorDeduccion=req.getParameter("ValorDeduccion");
		String Deduccion=req.getParameter("Deduccion");
		String TotalValorDeduccion=req.getParameter("TotalValorDeduccion");
		String FechFa="";
		long ValorRecibo=0;
		long ValorSaldo=0;
		
		
		if (va.equals("GRCN")){
			String FechaInicial=req.getParameter("FechaInicial");
			String FechaFinal=req.getParameter("FechaFinal");
			String Entidad=req.getParameter("Entidad");
			String Sql="";
			if(Entidad.equals("Todas")){
				Sql="AND fef.fecha BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"'";
			}else{
				Sql="AND fef.fecha BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' AND fef.cod_eps='"+Entidad+"'";
			}
			
			try {
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr id='cabecera2' class='style11' align='center'><td colspan='14'>REPORTE DE CARTERA ENTRE "+FechaInicial+" Y "+FechaFinal+" DE "+Entidad+"</td></tr>");
				out.print("<tr><td>Entidad</td><td>Numero Factura</td><td>Fecha Emision</td><td>Valor Factura</td><td>Estado</td><td>Notas Debito</td><td>Notas Credito</td><td>Valor Radicado</td><td>Fecha Radicacion</td><td>Cuenta de Cobro</td><td>Glosas Iniciales</td><td>Glosas Aceptadas</td><td>Valor Recaudado</td><td>Saldo Pendiente</td></tr>");
				rs=mpr.ConsultarFacturasCartera(Sql);
				String ValorFactura="";
				while(rs.next()){
					ValorFactura=rs.getString("valor");
					out.print("<tr>");
					out.print("<td>"+rs.getString("razon_social")+"</td>");
					out.print("<td>"+rs.getString("consecutivo")+"</td>");
					out.print("<td>"+rs.getString("fecha")+"</td>");
					out.print("<td>"+FormatoMoneda(rs.getString("valor"))+"</td>");
					
					if(rs.getString("estadoFact").equals("0")){out.print("<td>EMITIDA</td>");}
					if(rs.getString("estadoFact").equals("1")){out.print("<td>ENVIADA</td>");}
					if(rs.getString("estadoFact").equals("2")){out.print("<td>RADICADA</td>");}
					if(rs.getString("estadoFact").equals("3")){out.print("<td>GLOSADA</td>");}
					if(rs.getString("estadoFact").equals("5")){out.print("<td>ANULADA</td>");}
					if(rs.getString("estadoFact").equals("4")){out.print("<td>DEVUELTA</td>");}
					if(rs.getString("estadoFact").equals("6")){out.print("<td>EN AUDITORIA</td>");}
					if(rs.getString("estadoFact").equals("7")){out.print("<td>RADICACION INTERNA</td>");}
					if(rs.getString("estadoFact").equals("8")){out.print("<td>REENVIO</td>");}
					//se consultan las notas debito
					out.print("<td>"+FormatoMoneda("0")+"</td>");
					//se consultan las notas credito
					rs1=mpr.ConsultarNotasCredito(rs.getString("consecutivo"));
					if(rs1.next()){
						if(rs1.getString("NotasCredito")==null){
							out.print("<td>"+FormatoMoneda("0")+"</td>");
						}else{
							out.print("<td>"+FormatoMoneda(rs1.getString("NotasCredito"))+"</td>");
						}
					}else{
						//out.print("<td>0</td>");
					}
					rs1.getStatement().getConnection().close();
					//se consulta el valor radicado y fecha de radicacion
					rs1=mpr.ConsultarDatosRadicacion(rs.getString("consecutivo"));
					if(rs1.next()){						
						out.print("<td>"+FormatoMoneda(rs1.getString("ValorRadicado"))+"</td>");
						out.print("<td>"+rs1.getString("FechaRadicado")+"</td>");						
					}else{
						out.print("<td>"+FormatoMoneda("0")+"</td>");
						out.print("<td>0</td>");						
					}
					rs1.getStatement().getConnection().close();
					//se consulta el consecutivo de la cuenta de cobro
					rs1=mpr.ConsultarCuentaCobro(rs.getString("consecutivo"));					
					if(rs1.next()){						
						out.print("<td>"+rs1.getString("CtaCobro")+"</td>");
					}else{
						out.print("<td>0</td>");
					}
					rs1.getStatement().getConnection().close();
					//se consulta las glosas iniciales 
					rs1=mpr.ConsultarGlosasIniciales(rs.getString("consecutivo"));					
					if(rs1.next()){	
						if(rs1.getString("GlosaInicial")==null){
							out.print("<td>"+FormatoMoneda("0")+"</td>");
						}else{
							out.print("<td>"+FormatoMoneda(rs1.getString("GlosaInicial"))+"</td>");
						}
					}else{
						out.print("<td>0</td>");
					}
					rs1.getStatement().getConnection().close();
					//se consultan las glosas aceptadas
					rs1=mpr.ConsultarGlosasAceptadas(rs.getString("consecutivo"));					
					if(rs1.next()){		
						if(rs1.getString("GlosaAceptada")==null){
							out.print("<td>"+FormatoMoneda("0")+"</td>");
						}else{
							out.print("<td>"+FormatoMoneda(rs1.getString("GlosaAceptada"))+"</td>");
						}
					}else{
						out.print("<td>0</td>");
					}
					rs1.getStatement().getConnection().close();
					//se consulta el valor abonado a ala factura
					rs1=mpr.ConsultarValorAbonado(rs.getString("consecutivo"));					
					if(rs1.next()){	
						if(rs1.getString("ValorRecaudo")==null){
							out.print("<td>"+FormatoMoneda("0")+"</td>");
						}else{
							out.print("<td>"+FormatoMoneda(rs1.getString("ValorRecaudo"))+"</td>");
						}
					}else{
						out.print("<td>0</td>");
					}
					rs1.getStatement().getConnection().close();
					//se consulta el saldo pendiente de la factura
					rs1=mpr.ConsultarSaldoPendiente(rs.getString("consecutivo"));					
					if(rs1.next()){
						if(rs1.getString("SaldoFactura")==null){
							System.out.println("entro en nulo");
							out.print("<td>"+FormatoMoneda(ValorFactura)+"</td>");
						}else{
							System.out.println("entro con valor");
							out.print("<td>"+FormatoMoneda(rs1.getString("SaldoFactura"))+"</td>");
						}
						
					}else{
						System.out.println("no se encontro");
						out.print("<td>"+FormatoMoneda(ValorFactura)+"</td>");
					}
					rs1.getStatement().getConnection().close();				
					out.print("</tr>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(va.equals("BTRC")){
			out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0' ><tr id='cabecera2' class='style11' align='center'><td colspan='6'>REPORTE DE CARTERA</td></tr>");
			out.print("<tr><td>Fecha Inicial</td>" +
					"<td><input id='txtFechaInicial' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td>" +
					"<td>Fecha Final</td>" +
					"<td><input id='txtFechaFinal' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td>" +
					"<td>Entidad</td>" +
					"<td><select id='cmbEntidad' ><option value='Todas'>Todas</option>");
			try {
				rs=mpr.Entidades();			
				while(rs.next()){
					out.print("<option value="+rs.getString("ent_nit")+">"+rs.getString("nombre_entidad")+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print("error al generar "+e);
				e.printStackTrace();
			}
			out.print("</select></td></tr><tr><td colspan='6' align='center'><input type='button' value='     Consultar     ' onclick='GenerarReporteCartera()' ></td></tr></table>");
						
			
		}
		
		
		if(va.equals("ValorPa")){
			try{
				String Tipo="1";
			rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
			long ValorPagadoR=0;
			while(rs.next()){
				ValorPagadoR=ValorPagadoR+rs.getLong(2);
			}
			rs.getStatement().getConnection().close();
			out.print(FormatoMoneda(ValorPagadoR+""));
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("DCC")){
			/// consulta por centro de costo
			MetodoCuentas mmc = new MetodoCuentas();
			String txtFechaIN=req.getParameter("txtFechaIN");
			String txtFechaFI=req.getParameter("txtFechaFI");
			String FechaIni,DiaI,MesI,AnoI=null;
			DiaI=txtFechaIN.substring(0,2);
			MesI=txtFechaIN.substring(3,5);
			AnoI=txtFechaIN.substring(6,10);
			FechaIni=AnoI+"-"+MesI+"-"+DiaI;			
			String FechaFin,DiaF,MesF,AnoF=null;
			DiaF=txtFechaFI.substring(0,2);
			MesF=txtFechaFI.substring(3,5);
			AnoF=txtFechaFI.substring(6,10);
			FechaFin=AnoF+"-"+MesF+"-"+DiaF;
			out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'  > " +
					"<tr class='boton4'><td colspan='7'>REPORTE DETALLADO POR CENTROS DE COSTOS GENERADO ENTRE "+txtFechaIN+" Y "+txtFechaFI+"</td></tr>" +
					//"<tr  bgcolor='#CCCCCC' ><td>EMPRESA</td><td>SUMA DE VALOR FACTURADO</td><td>SUMA DE VALOR PAGADO</td><td>SUMA DE VALOR ASUMIDO</td><td>SUMA DE NETO A PAGAR</td></tr> " +
					"");
			
			rs=mmc.ConsultarEntidadesF(FechaIni,FechaFin);
			try {				
				while(rs.next()){
					out.print("<tr  bgcolor='#CCCCCC' ><td colspan='7'>"+rs.getString("razon_social")+"</td></tr> ");
					out.print("<tr  class='boton4' ><td>Programa de Facturacion</td><td>Servicio y/o Cirugia</td><td>Subcentro de Costo</td><td>Clase de Servicio</td><td>Cantidad</td><td>Valor U</td><td>Valor</td></tr> ");
					rs1=mmc.ConsultarFacturadoCentroCostoD(FechaIni, FechaFin, rs.getString("razon_social"));
					if(rs1.next()){
						long sumatoria=0;
						rs2=mmc.ConsultarFacturadoCentroCostoD(FechaIni, FechaFin, rs.getString("razon_social"));
						while(rs2.next()){
							String Paquete="";
							if(rs2.getString("nombre_paquete")==null){
								Paquete="-";
							}else{
								Paquete=rs2.getString("nombre_paquete");
							}
							out.print("<tr><td>"+rs2.getString("nombre_programa")+"</td><td>"+Paquete+"</td><td>"+rs2.getString("SubcentroCosto")+"</td><td>"+rs2.getString("clase_servicio")+"</td><td>"+rs2.getString("cantidad")+"</td><td>"+FormatoMoneda(rs2.getString("ValPro"))+"</td><td>"+FormatoMoneda(rs2.getString("Valor"))+"</td></tr> ");
							sumatoria=sumatoria+rs2.getLong("Valor");
						}
						out.print("<tr bgcolor='#819FF7' ><td >TOTAL GENERAL</td><td></td><td></td><td></td><td></td><td></td><td>"+FormatoMoneda(sumatoria+"")+"</td></tr> ");
						rs2.getStatement().getConnection().close();
					
					}else{
						out.print("<tr bgcolor='#819FF7'><td>TOTAL GENERAL</td><td></td><td></td><td></td><td></td><td></td><td>"+FormatoMoneda("0")+"</td></tr> ");
					}
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.println("Error al generar "+e);
			}
		}
		
		if(va.equals("CCC")){
			/// consulta por centro de costo
			MetodoCuentas mmc = new MetodoCuentas();
			String txtFechaIN=req.getParameter("txtFechaIN");
			String txtFechaFI=req.getParameter("txtFechaFI");
			String FechaIni,DiaI,MesI,AnoI=null;
			DiaI=txtFechaIN.substring(0,2);
			MesI=txtFechaIN.substring(3,5);
			AnoI=txtFechaIN.substring(6,10);
			FechaIni=AnoI+"-"+MesI+"-"+DiaI;			
			String FechaFin,DiaF,MesF,AnoF=null;
			DiaF=txtFechaFI.substring(0,2);
			MesF=txtFechaFI.substring(3,5);
			AnoF=txtFechaFI.substring(6,10);
			FechaFin=AnoF+"-"+MesF+"-"+DiaF;
			out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'  > " +
					"<tr class='boton4'><td colspan='5'>REPORTE POR CENTROS DE COSTOS GENERADO ENTRE "+txtFechaIN+" Y "+txtFechaFI+"</td></tr>" +
					//"<tr  bgcolor='#CCCCCC' ><td>EMPRESA</td><td>SUMA DE VALOR FACTURADO</td><td>SUMA DE VALOR PAGADO</td><td>SUMA DE VALOR ASUMIDO</td><td>SUMA DE NETO A PAGAR</td></tr> " +
					"");
			
			rs=mmc.ConsultarEntidades(FechaIni, FechaFin);
			try {				
				while(rs.next()){
					out.print("<tr  bgcolor='#CCCCCC' ><td colspan='2'>"+rs.getString("razon_social")+"</td></tr> ");
					rs1=mmc.ConsultarFacturadoCentroCosto(FechaIni, FechaFin, rs.getString("razon_social"));
					if(rs1.next()){
						long sumatoria=0;
						rs2=mmc.ConsultarFacturadoCentroCosto(FechaIni, FechaFin, rs.getString("razon_social"));
						while(rs2.next()){
							out.print("<tr><td>"+rs2.getString("descripcion")+"</td><td>"+FormatoMoneda(rs2.getString("Valor"))+"</td></tr> ");
							sumatoria=sumatoria+rs2.getLong("Valor");
						}
						out.print("<tr bgcolor='#819FF7' ><td >TOTAL GENERAL</td><td>"+FormatoMoneda(sumatoria+"")+"</td></tr> ");
						rs2.getStatement().getConnection().close();
					
					}else{
						out.print("<tr bgcolor='#819FF7'><td>TOTAL GENERAL</td><td>"+FormatoMoneda("0")+"</td></tr> ");
					}
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("CPE")){
			//*
			//consulta general.
			MetodoCuentas mmc = new MetodoCuentas();
			String txtFechaIN=req.getParameter("txtFechaIN");
			String txtFechaFI=req.getParameter("txtFechaFI");
			String FechaIni,DiaI,MesI,AnoI=null;
			DiaI=txtFechaIN.substring(0,2);
			MesI=txtFechaIN.substring(3,5);
			AnoI=txtFechaIN.substring(6,10);
			FechaIni=AnoI+"-"+MesI+"-"+DiaI;			
			String FechaFin,DiaF,MesF,AnoF=null;
			DiaF=txtFechaFI.substring(0,2);
			MesF=txtFechaFI.substring(3,5);
			AnoF=txtFechaFI.substring(6,10);
			FechaFin=AnoF+"-"+MesF+"-"+DiaF;
			out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'  > " +
					"<tr class='boton4'><td colspan='5'>REPORTE GENERADO ENTRE "+txtFechaIN+" Y "+txtFechaFI+"</td></tr>" +
					"<tr  bgcolor='#CCCCCC' ><td>EMPRESA</td><td>SUMA DE VALOR FACTURADO</td><td>SUMA DE VALOR PAGADO</td><td>SUMA DE VALOR ASUMIDO</td><td>SUMA DE NETO A PAGAR</td></tr>");
			//rs=mmc.ConsultarEntidades(FechaIni, FechaFin);
			try {
				//while(rs.next()){					
					rs1=mmc.ConsultarValoresEntidades(FechaIni, FechaFin);
					long TotalFact=0;
					long SumatoriaFacturado=0;
					long SumatoriaPagado=0;
					long SumatoriaAsumido=0;
					long SumatoriaTotal=0;
					while(rs1.next()){
						out.print("<tr>");
						out.print("<td>"+rs1.getString("razon_social")+"</td>");
						TotalFact=rs1.getLong("cantidad")-(rs1.getLong("Pagado")+rs1.getLong("AsumidoESE"));
						out.print("<td>"+FormatoMoneda(rs1.getLong("cantidad")+"")+"</td>");
						SumatoriaFacturado=SumatoriaFacturado+rs1.getLong("cantidad");
						out.print("<td>"+FormatoMoneda(rs1.getLong("Pagado")+"")+"</td>");
						SumatoriaPagado=SumatoriaPagado+rs1.getLong("Pagado");
						out.print("<td>"+FormatoMoneda(rs1.getLong("AsumidoESE")+"")+"</td>");
						SumatoriaAsumido=SumatoriaAsumido+rs1.getLong("AsumidoESE");
						out.print("<td>"+FormatoMoneda(TotalFact+"")+"</td>");
						SumatoriaTotal=SumatoriaTotal+TotalFact;
						out.print("</tr>");
					}
					out.print("<tr><td>TOTALES:</td><td>"+FormatoMoneda(SumatoriaFacturado+"")+"</td> "
							+ "<td>"+FormatoMoneda(SumatoriaPagado+"")+"</td>"
							+ "<td>"+FormatoMoneda(SumatoriaAsumido+"")+"</td>"
							+ "<td>"+FormatoMoneda(SumatoriaTotal+"")+"</td>"
							+ "</tr>");
					rs1.getStatement().getConnection().close();
					
				//}
				out.print("</table>");
				//rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("GRGF")){
			MetodoCuentas mmc = new MetodoCuentas();
			String FechaInicial=req.getParameter("FechaInicial");	
			String FechaFinal=req.getParameter("FechaFinal");
			//int y=Meses.split(":").length;
			//String[] z=Meses.split(":");
			out.print("<table  width='100%' border='1' cellspacing='0' cellpadding='0' >");
			//for(int x=0; x<y; x=x+1){ 
				//if(!z[x].equals("")){
			String FechaIni,DiaI,MesI,AnoI=null;
			DiaI=FechaInicial.substring(0,2);
			MesI=FechaInicial.substring(3,5);
			AnoI=FechaInicial.substring(6,10);
			FechaIni=AnoI+"-"+MesI+"-"+DiaI;
			
			String FechaFin,DiaF,MesF,AnoF=null;
			DiaF=FechaFinal.substring(0,2);
			MesF=FechaFinal.substring(3,5);
			AnoF=FechaFinal.substring(6,10);
			FechaFin=AnoF+"-"+MesF+"-"+DiaF;
					
					out.print("<tr class='boton4'><td colspan='4'>REPORTE REALIZADO DEL "+FechaInicial+" HASTA "+FechaFinal+"</td></tr>");
					out.print("<tr  bgcolor='#CCCCCC' ><td>EMPRESA</td><td>FACTURADO</td><td>RADICADO</td><td>SIN RADICAR</td></tr>");
					rs=mmc.ConsultarEntidades(FechaIni, FechaFin);
					try {
						long TotalFact=0;
						long TotalRadi=0;
						long TotalSinRad=0;
						while(rs.next()){
							long FacturadoV=0;
							long RadicadoV=0;
							long SinRadicarV=0;
							
							out.print("<tr><td>"+rs.getString("razon_social")+"</td> " );
							rs1=mmc.ConsultarFacturadof(FechaIni,FechaFin, rs.getString("cod_eps"));
							if(rs1.next()){
								out.print("<td>"+FormatoMoneda(rs1.getString("Facturado"))+"</td>");									
								FacturadoV=rs1.getLong("Facturado");
								TotalFact=TotalFact+rs1.getLong("Facturado");
							}else{
								out.print("<td>"+FormatoMoneda(0+"")+"</td>");
							}
							rs1.getStatement().getConnection().close();
							rs2=mmc.ConsultarRadicadof(FechaIni,FechaFin, rs.getString("cod_eps"));
							if(rs2.next()){
								out.print("<td>"+FormatoMoneda(rs2.getString("Radicado"))+"</td>");
								RadicadoV=rs2.getLong("Radicado");
								TotalRadi=TotalRadi+rs2.getLong("Radicado");
							}else{
								out.print("<td>"+FormatoMoneda(0+"")+"</td>");
							}
							rs2.getStatement().getConnection().close();
							SinRadicarV=FacturadoV-RadicadoV;
							out.print("<td>"+FormatoMoneda(SinRadicarV+"")+"</td>");
							TotalSinRad=TotalSinRad+SinRadicarV;
							out.print("</tr>");
						}
						out.print("<tr><td><B>TOTALES:</B></td><td><B>"+FormatoMoneda(TotalFact+"")+"</B></td><td><B>"+FormatoMoneda(TotalRadi+"")+"</B></td><td><B>"+FormatoMoneda(TotalSinRad+"")+"</B></td></tr>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					//mm.ActualizarDatallePreFactura(CodNuevoEnca, z[x], "3");
					
				//}
				
			//}
			out.print("</table>");
		}
		
		if(va.equals("GRG")){
			MetodoCuentas mmc = new MetodoCuentas();
			String Anio=req.getParameter("Anio");	
			String Meses=req.getParameter("Meses");
			int y=Meses.split(":").length;
			String[] z=Meses.split(":");
			out.print("<table  width='100%' border='1' cellspacing='0' cellpadding='0' >");
			for(int x=0; x<y; x=x+1){ 
				if(!z[x].equals("")){
					String Periodo=Anio+"-"+z[x];
					String M="";
					if(z[x].equals("01")){M="ENERO "+Anio;}
					if(z[x].equals("02")){M="FEBRERO "+Anio;}
					if(z[x].equals("03")){M="MARZO "+Anio;}
					if(z[x].equals("04")){M="ABRIL "+Anio;}
					if(z[x].equals("05")){M="MAYO "+Anio;}
					if(z[x].equals("06")){M="JUNIO "+Anio;}
					if(z[x].equals("07")){M="JULIO "+Anio;}
					if(z[x].equals("08")){M="AGOSTO "+Anio;}
					if(z[x].equals("09")){M="SEPTIEMBRE "+Anio;}
					if(z[x].equals("10")){M="OCTUBRE "+Anio;}
					if(z[x].equals("11")){M="NOVIEMBRE "+Anio;}
					if(z[x].equals("12")){M="DICIEMBRE "+Anio;}
					
					out.print("<tr class='boton4'><td colspan='4'>"+M+"</td></tr>");
					out.print("<tr  bgcolor='#CCCCCC' ><td>EMPRESA</td><td>FACTURADO</td><td>RADICADO</td><td>SIN RADICAR</td></tr>");
					rs=mmc.ConsultarEntidadesL(Periodo);
					try {
						long TotalFact=0;
						long TotalRadi=0;
						long TotalSinRad=0;
						while(rs.next()){
							long FacturadoV=0;
							long RadicadoV=0;
							long SinRadicarV=0;
							
							out.print("<tr><td>"+rs.getString("razon_social")+"</td> " );
							rs1=mmc.ConsultarFacturado(Periodo, rs.getString("cod_eps"));
							if(rs1.next()){
								out.print("<td>"+FormatoMoneda(rs1.getString("Facturado"))+"</td>");									
								FacturadoV=rs1.getLong("Facturado");
								TotalFact=TotalFact+rs1.getLong("Facturado");
							}else{
								out.print("<td>"+FormatoMoneda(0+"")+"</td>");
							}
							rs1.getStatement().getConnection().close();
							rs2=mmc.ConsultarRadicado(Periodo, rs.getString("cod_eps"));
							if(rs2.next()){
								out.print("<td>"+FormatoMoneda(rs2.getString("Radicado"))+"</td>");
								RadicadoV=rs2.getLong("Radicado");
								TotalRadi=TotalRadi+rs2.getLong("Radicado");
							}else{
								out.print("<td>"+FormatoMoneda(0+"")+"</td>");
							}
							rs2.getStatement().getConnection().close();
							SinRadicarV=FacturadoV-RadicadoV;
							out.print("<td>"+FormatoMoneda(SinRadicarV+"")+"</td>");
							TotalSinRad=TotalSinRad+SinRadicarV;
							out.print("</tr>");
						}
						out.print("<tr><td><B>TOTALES:</B></td><td><B>"+FormatoMoneda(TotalFact+"")+"</B></td><td><B>"+FormatoMoneda(TotalRadi+"")+"</B></td><td><B>"+FormatoMoneda(TotalSinRad+"")+"</B></td></tr>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					//mm.ActualizarDatallePreFactura(CodNuevoEnca, z[x], "3");
					
				}
				
			}
			out.print("</table>");
		}
		
		
		
		if(va.equals("FinRC")){
			String ValorPagadoR=req.getParameter("ValorPagadoR");	
			String ValorTotalCartera=req.getParameter("ValorTotalCartera");
			String ConceptoRC=req.getParameter("ConceptoRC");
			String CodUsuRCC=req.getParameter("CodUsuRCC");
			//String ValorPagadoLetra="";
			//ValorPagadoLetra=FormatoMoneda(ValorPagadoR);
			mc.FinReciboCaja(CodRecCaja, ValorTotalCartera);
			/******************************************************************/
			MetodoMovimientos mm = new MetodoMovimientos();
			//se busca el a�o y periodo
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
			//se busca el consecutivo de el recibo de caja
			String conso="";
			String cons="";
			String consn="";
			rs = mm.ConsecutivosdeCuentas("170");
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
				
				//se crea el registro en la tabla cont_documento 
				mm.CrearDocumento(annio,mes,"170",consn,fechacjmysql,"RECIBO DE CAJA "+ConceptoRC,ValorPagadoR,ValorPagadoR,"0","",CodUsuRCC,fechacjmysql,hora+"");
				
				//se actualiza el consecutivo del documento
				int ctan=Integer.parseInt(conso)+1;
				conso=String.valueOf(ctan);
				mm.ActualizarConsecutivo(conso,"170");
				
				//se busca el codigo de documento creado
				String Coddocu="";
				rs = mm.ConsecutivoUDocumento(consn);if(rs.next()){Coddocu=rs.getString(1);}rs.getStatement().getConnection().close();
				
				//buscamos las cuentas a afectar
				rs3=mm.ConsultarcuentaIngresoClientes();
				String ctacliente="0";String nctacliente="";
				if(rs3.next()){	ctacliente=rs3.getString(1);nctacliente=rs3.getString(2);}rs3.getStatement().getConnection().close();
				
				/*se busca el tercero
				rs3=mm.ConsultarTercerosconNIT(nit);
				String ctat="0";
				if(rs3.next()){ctat=rs3.getString(1);}rs3.getStatement().getConnection().close();*/
				mm.CrearDetalleDocumento(Coddocu,ctacliente,"1","2","2",nctacliente,"0","Recibo de caja "+CodRecCaja,"0",ValorPagadoR,"0","RCA");
				rs=mc.BuscarCuentasReciboCajaDetalle(CodRecCaja);
				String ValorAf="";String NomCueAf="";String CodCuentaAF="";
				
				while(rs.next()){					
					if(rs.getString(5).equals("0")){
						//busca los movimientos sin bancos
						ValorAf=rs.getString(2);
						NomCueAf=rs.getString(7);
						CodCuentaAF=rs.getString(6);
						mm.CrearDetalleDocumento(Coddocu,CodCuentaAF,"1","2","2",NomCueAf,"0","Recibo de caja "+CodRecCaja,ValorAf,"0","0","RCA");
					}
					if(rs.getString(5).equals("1")){
						//busca los pagos con banco
						ValorAf=rs.getString(2);
						NomCueAf=rs.getString(3);
						CodCuentaAF=rs.getString(4);
						mm.CrearDetalleDocumento(Coddocu,CodCuentaAF,"1","2","2",NomCueAf,"0","Recibo de caja "+CodRecCaja, ValorTotalCartera ,"0","0","RCA");
					}
					
					
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			/******************************************************************/
			
			out.print(CodRecCaja);
			//mc.CrearComplementoReciboCaja(FormaPago, ValorPagadoR, CodRecCaja);
			
		}
		if(va.equals("ValDe")){
			try{
				String Tipo="0";
				mc.CrearComplementoReciboCaja(Deduccion, ValorDeduccion, CodRecCaja,Tipo,Tipo);
				rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
				out.print("<table border='1' width='100%'>");
				out.print("<tr><td>Concepto</td><td>Valor</td><td>Accion</td></tr>");
				long ValorDeducciones=0;
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+FormatoMoneda(rs.getString(2))+"</td><td><a href='#' onclick='OmitirDeduccion("+rs.getString(3)+")'>Omitir</a></td></tr>");
					ValorDeducciones=ValorDeducciones+rs.getLong(2);
				}
				rs.getStatement().getConnection().close();
				out.print("<input id='txtTotalValorDeduccion' type='hidden' value="+ValorDeducciones+" /></table>");
				} catch (SQLException e) {
					out.println(e);
					e.printStackTrace();
				}

		}
		if(va.equals("ST")){			
			try {
				rs=mc.BuscarSinTerminar(CodUsu);
				if(rs.next()){
					out.print(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("ch")){
			
		}
		/*
		 * 
		 * 
		 * 
		 * */
		
		if(va.equals("ValPa")){
			try{
				String Banco=req.getParameter("Banco");
				String Tipo="1";
			mc.CrearComplementoReciboCaja(FormaPago, ValorPagado, CodRecCaja,Tipo,Banco);
			rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
			out.print("<table border='1' width='100%'>");
			out.print("<tr><td>Forma Pago</td><td>Valor Pagado</td><td>Banco</td><td>Accion</td></tr>");
			long ValorPagadoR=0;
			while(rs.next()){
				out.print("<tr><td>"+rs.getString(1)+"</td><td>"+FormatoMoneda(rs.getString(2))+"</td><td>"+rs.getString(4)+"</td><td><a href='#' onclick='OmitirDetallePago("+rs.getString(3)+")'>Omitir</a></td></tr>");
				ValorPagadoR=ValorPagadoR+rs.getLong(2);
			}
			rs.getStatement().getConnection().close();
			out.print("<input id='txtValorPagadoR' type='hidden' value="+ValorPagadoR+" /></table>");
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("OmDt")){
			String CodigoDetalle=req.getParameter("CodigoDetalle");
			
			
			try{
				String Tipo="1";
				mc.OmitirComplementoReciboCaja(CodigoDetalle);
				//mc.CrearComplementoReciboCaja(FormaPago, ValorPagado, CodRecCaja);
				rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
				out.print("<table border='1' width='100%'>");
				out.print("<tr><td>Forma Pago</td><td>Valor Pagado</td><td>Accion</td></tr>");
				long ValorPagadoR=0;
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+FormatoMoneda(rs.getString(2))+"</td><td><a href='#' onclick='OmitirDetallePago("+rs.getString(3)+")'>Omitir</a></td></tr>");
					ValorPagadoR=ValorPagadoR+rs.getLong(2);
				}
				rs.getStatement().getConnection().close();
				out.print("<input id='txtValorPagadoR' type='hidden' value="+ValorPagadoR+" /></table>");
				} catch (SQLException e) {
					out.println(e);
					e.printStackTrace();
				}

		}
		if(va.equals("DEDUC")){
			
			
			try{
				String Tipo="0";
				//mc.CrearComplementoReciboCaja(FormaPago, ValorPagado, CodRecCaja);
				rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
				out.print("<table border='1' width='100%'>");
				out.print("<tr><td>Concepto</td><td>Valor</td><td>Accion</td></tr>");
				long ValorDeducciones=0;
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+FormatoMoneda(rs.getString(2))+"</td><td><a href='#' onclick='OmitirDeduccion("+rs.getString(3)+")'>Omitir</a></td></tr>");
					ValorDeducciones=ValorDeducciones+rs.getLong(2);
				}
				rs.getStatement().getConnection().close();
				out.print("<input id='txtTotalValorDeduccion' type='hidden' value="+ValorDeducciones+" /></table>");
				} catch (SQLException e) {
					out.println(e);
					e.printStackTrace();
				}

		}
		
		if(va.equals("OmDed")){
			String CodigoDetalle=req.getParameter("CodigoDetalle");
			
			
			try{
				String Tipo="0";
				mc.OmitirComplementoReciboCaja(CodigoDetalle);
				//mc.CrearComplementoReciboCaja(FormaPago, ValorPagado, CodRecCaja);
				rs=mc.BuscarComplementoReciboCaja(CodRecCaja,Tipo);
				out.print("<table border='1' width='100%'>");
				out.print("<tr><td>Concepto</td><td>Valor</td><td>Accion</td></tr>");
				long ValorDeducciones=0;
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+FormatoMoneda(rs.getString(2))+"</td><td><a href='#' onclick='OmitirDeduccion("+rs.getString(3)+")'>Omitir</a></td></tr>");
					ValorDeducciones=ValorDeducciones+rs.getLong(2);
				}
				rs.getStatement().getConnection().close();
				out.print("<input id='txtTotalValorDeduccion' type='hidden' value="+ValorDeducciones+" /></table>");
				} catch (SQLException e) {
					out.println(e);
					e.printStackTrace();
				}

		}
		
		if(va.equals("ResRC")){
			try{
				//mc.OmitirDetalleReciboCaja(CodDetCaja);
				out.print("<table border='1' width='100%' >");
				out.print("<tr><td>Valor Abono</td></tr>");
				rs1=mc.BuscarDetalleReciboCaja(CodRecCaja);
				while(rs1.next()){
					//out.print("<tr><td>"+rs1.getString(6)+"</td><td>"+FormatoMoneda(rs1.getString(4))+"</td><td>"+FormatoMoneda(rs1.getString(5))+"</td><td><a href='#' onclick='OmitirDetReciboCaja("+rs1.getString(1)+")'>Omitir</a></td></tr>");
					ValorRecibo=ValorRecibo+rs1.getLong(5);
					ValorSaldo=ValorSaldo+rs1.getLong(4);
				}
				rs1.getStatement().getConnection().close();
				out.print("<tr><td><input id='txtValorTotalPagar' type='hidden' value="+ValorRecibo+" />"+FormatoMoneda(ValorRecibo+"")+"</td></tr>");
				out.print("<tr><td>Valor Cartera</td></tr>");
				out.print("<tr><td id='ValPagado'></td></tr>");
				out.print("<tr><td>Valor Deducciones</td></tr>");
				out.print("<tr><td id='ValDeduccion'>"+FormatoMoneda(TotalValorDeduccion)+"</td></tr>");
				out.print("<tr><td>Valor Pagado</td></tr>");
				long ValorCartera=ValorRecibo-Long.parseLong(TotalValorDeduccion);
				out.print("<tr><td><input id='txtValorTotalCartera' type='hidden' value="+ValorCartera+" />"+FormatoMoneda(ValorCartera+"")+"</td></tr>");
				out.print("</table>");
				/*********************************************************************************************/
//				int diferencia=0;
//				diferencia=Integer.parseInt(valor_Factura)-Integer.parseInt(valor_abono);
//				mc.InsertarDetalleFactura(CodFactura, valor_abono, FechaPago, "1", diferencia, NumSoporte, ObservacionRC, fecha, hora, CodUsu);
				/*********************************************************************************************/
				//out.print("<tr><td><input name='txtCodReciboCaja' type='hidden' id='txtCodReciboCaja' value="+CodRecCaja+" /><input name='txtCantidadRC' type='hidden' id='txtCantidadRC' value="+ValorRecibo+" /></td></tr></table>");
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("ENCU")){
			rs=mc.TodosCuentasEmpresa();
			try {
				out.print("<select id='cmbEmpresas'><option value='Seleccione' selected='' >Seleccione</option>");
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(3)+"</option>");
				}
				out.print("</select><input type='button' value='  Depurar  ' onClick='DepurarCarteraEntidad()' >");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("DCE")){
			/************************************************************************/
			/************************SINCRONIZACION DE CARTERA***********************/
			/************************************************************************/
			String CodEnt=req.getParameter("CodEnt");
			//String FechaE=req.getParameter("FechaE");
			System.out.println("ENTRA EN SINCRONIZACION DETALLADA DE CARTERA ");
			String plazo_corto="0";
			String plazo_30="0"; 
			String plazo_60="0"; 
			String plazo_90="0";
			String plazo_180="0";
			String plazo_360="0";
			String Por_vencer="0";
			String Resta="";
			String CodFacturaC="";
			int DiaFact=0;
			int DiaAbo=0;
			int DifPlazo=0;
			String FechaUltDet="";
			String DifPlazoS="";
			try{
				//rs2=mc.SincronizarCarteraGeneral();
				//if(rs2.next()){
					rs=mc.BuscarCarteraEntidad(CodEnt);
					while(rs.next()){
						CodFacturaC=rs.getString(16);
						rsrc=mc.ResumenRestanteFactura(CodFacturaC);
						if(rsrc.next()){
							Resta=rsrc.getString(4);
							FechaUltDet=rsrc.getString(5);
							FechFa=rsrc.getString(6);
							//System.out.println("FechFa "+FechFa+" FechaUltDet "+FechaUltDet);
							rsd1=mc.NumeroDiasFecha(FechFa);
							rsd2=mc.NumeroDiasFechaActual();
							if(rsd1.next()){
								DiaFact=rsd1.getInt(1);
							}
							rsd1.getStatement().getConnection().close();
					
							if(rsd2.next()){
								DiaAbo=rsd2.getInt(1);
							}
							//******
							rsd2.getStatement().getConnection().close();
							DifPlazo=DiaAbo-DiaFact;
							DifPlazoS=DifPlazo+"";
							System.out.println("DifPlazo "+DifPlazo);
							//System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
							if((DifPlazo<=30)){
								/**del por vencer **/
								Por_vencer=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0","0",Por_vencer);
							}
							if((DifPlazo>=31)&&(DifPlazo<=60)){
								/**vencido 1-30**/
								plazo_corto=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,"0","0","0","0","0","0");
							}
							if((DifPlazo>=61)&&(DifPlazo<=90)){
								/**vencido 31-60**/
								plazo_30=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0",plazo_30,"0","0","0","0","0");
							}
							if((DifPlazo>=91)&&(DifPlazo<=120)){
								/**vencido 61-90**/
								plazo_60=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0",plazo_60,"0","0","0","0");
							}
							if((DifPlazo>=121)&&(DifPlazo<=210)){
								/**vencido 91-180**/
								plazo_90=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0",plazo_90,"0","0","0");
							}
							if((DifPlazo>=211)&&(DifPlazo<=390)){
								/**vencido 181-360**/
								plazo_180=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0",plazo_180,"0","0");
							}							
							if(DifPlazo>=391){
								/**mayor de 360**/
								plazo_360=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0",plazo_360,"0");
							}


						}else{
							rs2=mc.BuscarFacturaSinDetalle(CodFacturaC);
							if(rs2.next()){
								Resta=rs2.getString(1);
							//	FechaUltDet=rsrc.getString(5);
								FechFa=rs2.getString(2);
								//System.out.println("FechFa "+FechFa+" FechaUltDet "+FechaUltDet);
								rsd1=mc.NumeroDiasFecha(FechFa);
								rsd2=mc.NumeroDiasFechaActual();
								if(rsd1.next()){
									DiaFact=rsd1.getInt(1);
								}
								rsd1.getStatement().getConnection().close();
						
								if(rsd2.next()){
									DiaAbo=rsd2.getInt(1);
								}
								//******
								rsd2.getStatement().getConnection().close();
								DifPlazo=DiaAbo-DiaFact;
								DifPlazoS=DifPlazo+"";
								//System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
								if((DifPlazo<=30)){
									/**del por vencer **/
									Por_vencer=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0","0",Por_vencer);
								}
								if((DifPlazo>=31)&&(DifPlazo<=60)){
									/**vencido 1-30**/
									plazo_corto=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,"0","0","0","0","0","0");
								}
								if((DifPlazo>=61)&&(DifPlazo<=90)){
									/**vencido 31-60**/
									plazo_30=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0",plazo_30,"0","0","0","0","0");
								}
								if((DifPlazo>=91)&&(DifPlazo<=120)){
									/**vencido 61-90**/
									plazo_60=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0",plazo_60,"0","0","0","0");
								}
								if((DifPlazo>=121)&&(DifPlazo<=210)){
									/**vencido 91-180**/
									plazo_90=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0",plazo_90,"0","0","0");
								}
								if((DifPlazo>=211)&&(DifPlazo<=390)){
									/**vencido 181-360**/
									plazo_180=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0",plazo_180,"0","0");
								}							
								if(DifPlazo>=391){
									/**mayor de 360**/
									plazo_360=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0",plazo_360,"0");
								}
							}
							rs2.getStatement().getConnection().close();
						}
						rsrc.getStatement().getConnection().close();
					}
					rs.getStatement().getConnection().close();		
				//}
				//else{
					/**SE CREA EL RESGITRO EN LA TABLA CONT_CARTERA_PLAZO**/
					//mc.CrearPlazoCartera(numero_factura, valor_factura, Cod_factura);
				//}
		
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
			/************************************************************************/
			/*********************FIN DE SINCRONIZACION DE CARTERA*******************/
			/************************************************************************/
		}
		
		if(va.equals("CEMN")){
			String FechaE=req.getParameter("Fecha");
			String NombreEntidad=req.getParameter("NombreEntidad");
			String CodEnt=req.getParameter("CodEnt");
			
			int DifPlazo=0;
				try {
				long TTotalC=0;long Tpvenc=0;long Tven30=0;long Tven60=0;long Tven90=0;long Tven180=0;long Tven360=0;long Tmas360=0;
				out.print("<table border='1' cellspacing='0' width='100%'>");
				out.print("<tr><td colspan='9'>CARTERA DETALLADA EMITIDA HASTA EL "+FechaE+"</td></tr>");
				out.print("<tr><td colspan='9'>"+NombreEntidad+"</td></tr>");
				out.print("<tr><td>Consecutivo</td><td>Por vencer</td><td>Vencido 1-30</td><td>Vencido 31-60</td><td>Vencido 61-90</td><td>Vencido 91-180</td><td>Vencido 181-360</td><td>Mas 360</td><td>Total</td></tr>");
				long TotalC=0;long pvenc=0;long ven30=0;long ven60=0;long ven90=0;long ven180=0;long ven360=0;long mas360=0;
				rs1=mpr.DetallesFacturas(CodEnt, FechaE);// se consultan todas las facturas de esa entidad hasta la fecha en encabezado.
				while(rs1.next()){
					DifPlazo=rs1.getInt("dias");
						// se consulta si la factura tiene alguna deduccion
					long ValorNeto=0;
					rs2=mpr.VerificarDeduccion(rs1.getString("consecutivo"),FechaE);
					if(rs2.next()){
						// se restan el valor de la factura - el valor de la deduccion
						ValorNeto=rs1.getLong("valor")-rs2.getLong("Deducciones");
						if((DifPlazo<=30)){//pvenc=pvenc+ValorNeto;
						out.print("<tr><td>"+rs1.getString("consecutivo")+"</td><td>"+ValorNeto+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td></tr>");
						}
						if(((DifPlazo>=31)&&(DifPlazo<=60))){//ven30=ven30+ValorNeto;
						out.print("<tr><td>"+rs1.getString("consecutivo")+"</td><td>0</td><td>"+ValorNeto+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td></tr>");
						}
						if(((DifPlazo>=61)&&(DifPlazo<=90))){//ven60=ven60+ValorNeto;
						out.print("<tr><td>"+rs1.getString("consecutivo")+"</td><td>0</td><td>0</td><td>"+ValorNeto+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td></tr>");
						}
						if(((DifPlazo>=91)&&(DifPlazo<=120))){//ven90=ven90+ValorNeto;
						out.print("<tr><td>"+rs1.getString("consecutivo")+"</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td></tr>");
						}
						if(((DifPlazo>=121)&&(DifPlazo<=210))){//ven180=ven180+ValorNeto;
						out.print("<tr><td>"+rs1.getString("consecutivo")+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td><td>0</td><td>0</td><td>"+ValorNeto+"</td></tr>");
						}
						if(((DifPlazo>=211)&&(DifPlazo<=390))){//ven360=ven360+ValorNeto;
						out.print("<tr><td>"+rs1.getString("consecutivo")+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td><td>0</td><td>"+ValorNeto+"</td></tr>");
						}
						if((DifPlazo>390)){//mas360=mas360+ValorNeto;
						out.print("<tr><td>"+rs1.getString("consecutivo")+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorNeto+"</td><td>"+ValorNeto+"</td></tr>");
						}
					}
					rs2.getStatement().getConnection().close();	
					
					if((DifPlazo<=30)){pvenc=pvenc+ValorNeto;}
					if(((DifPlazo>=31)&&(DifPlazo<=60))){ven30=ven30+ValorNeto;}
					if(((DifPlazo>=61)&&(DifPlazo<=90))){ven60=ven60+ValorNeto;}
					if(((DifPlazo>=91)&&(DifPlazo<=120))){ven90=ven90+ValorNeto;}
					if(((DifPlazo>=121)&&(DifPlazo<=210))){ven180=ven180+ValorNeto;}
					if(((DifPlazo>=211)&&(DifPlazo<=390))){ven360=ven360+ValorNeto;}
					if((DifPlazo>390)){mas360=mas360+ValorNeto;}							
				}
				TotalC=pvenc+ven30+ven60+ven90+ven180+ven360+mas360;
				rs1.getStatement().getConnection().close();				
				Tpvenc=Tpvenc+pvenc;
				Tven30=Tven30+ven30;
				Tven60=Tven60+ven60;
				Tven90=Tven90+ven90;
				Tven180=Tven180+ven180;
				Tven360=Tven360+ven360;
				Tmas360=Tmas360+mas360;
				TTotalC=TTotalC+TotalC;					
				out.print("<tr><td>TOTALES:</td><td>"+Tpvenc+"</td><td>"+Tven30+"</td><td>"+Tven60+"</td><td>"+Tven90+"</td><td>"+Tven180+"</td><td>"+Tven360+"</td><td>"+Tmas360+"</td><td>"+TTotalC+"</td></tr>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		if(va.equals("gcc")){
			String TipoReporte=req.getParameter("TipoReporte");
			String FechaE=req.getParameter("FechaE");
			//MetodoProveedor mpr= new MetodoProveedor();
			int DifPlazo=0;
			
			try {
				if(TipoReporte.equals("Emitida")){
					rs=mpr.Entidades();	// se consultan todas las entidades
					long TTotalC=0;long Tpvenc=0;long Tven30=0;long Tven60=0;long Tven90=0;long Tven180=0;long Tven360=0;long Tmas360=0;
					out.print("<table border='1' cellspacing='0' width='100%'>");
					out.print("<tr><td colspan='9'>CARTERA CONSOLIDADA EMITIDA HASTA EL "+FechaE+"</td></tr>");
					out.print("<tr><td>Empresa</td><td>Por vencer</td><td>Vencido 1-30</td><td>Vencido 31-60</td><td>Vencido 61-90</td><td>Vencido 91-180</td><td>Vencido 181-360</td><td>Mas 360</td><td>Total</td></tr>");
					while(rs.next()){						
						long TotalC=0;long pvenc=0;long ven30=0;long ven60=0;long ven90=0;long ven180=0;long ven360=0;long mas360=0;
						out.print("<td>"+rs.getString(2)+"</td>");
						rs1=mpr.DetallesFacturas(rs.getString(1), FechaE);// se consultan todas las facturas de esa entidad hasta la fecha en encabezado.
						while(rs1.next()){
							DifPlazo=rs1.getInt("dias");
							// se consulta si la factura tiene alguna deduccion
							long ValorNeto=0;
							rs2=mpr.VerificarDeduccion(rs1.getString("consecutivo"),FechaE);
							if(rs2.next()){
								// se restan el valor de la factura - el valor de la deduccion
								ValorNeto=rs1.getLong("valor")-rs2.getLong("Deducciones");
							}
							rs2.getStatement().getConnection().close();							
							
							if((DifPlazo<=30)){pvenc=pvenc+ValorNeto;}
							if(((DifPlazo>=31)&&(DifPlazo<=60))){ven30=ven30+ValorNeto;}
							if(((DifPlazo>=61)&&(DifPlazo<=90))){ven60=ven60+ValorNeto;}
							if(((DifPlazo>=91)&&(DifPlazo<=120))){ven90=ven90+ValorNeto;}
							if(((DifPlazo>=121)&&(DifPlazo<=210))){ven180=ven180+ValorNeto;}
							if(((DifPlazo>=211)&&(DifPlazo<=390))){ven360=ven360+ValorNeto;}
							if((DifPlazo>390)){mas360=mas360+ValorNeto;}
							/*if((DifPlazo<=30)){pvenc=pvenc+rs1.getLong("Restante");}
							if(((DifPlazo>=31)&&(DifPlazo<=60))){ven30=ven30+rs1.getLong("Restante");}
							if(((DifPlazo>=61)&&(DifPlazo<=90))){ven60=ven60+rs1.getLong("Restante");}
							if(((DifPlazo>=91)&&(DifPlazo<=120))){ven90=ven90+rs1.getLong("Restante");}
							if(((DifPlazo>=121)&&(DifPlazo<=210))){ven180=ven180+rs1.getLong("Restante");}
							if(((DifPlazo>=211)&&(DifPlazo<=390))){ven360=ven360+rs1.getLong("Restante");}
							if((DifPlazo>390)){mas360=mas360+rs1.getLong("Restante");}*/
								
						}
						TotalC=pvenc+ven30+ven60+ven90+ven180+ven360+mas360;
						rs1.getStatement().getConnection().close();
						out.print("<td>"+pvenc+"</td><td>"+ven30+"</td><td>"+ven60+"</td><td>"+ven90+"</td><td>"+ven180+"</td><td>"+ven360+"</td><td>"+mas360+"</td><td>"+TotalC+"</td></tr>");
						
						Tpvenc=Tpvenc+pvenc;
						Tven30=Tven30+ven30;
						Tven60=Tven60+ven60;
						Tven90=Tven90+ven90;
						Tven180=Tven180+ven180;
						Tven360=Tven360+ven360;
						Tmas360=Tmas360+mas360;
						TTotalC=TTotalC+TotalC;
						
					}
					out.print("<tr><td>TOTALES:</td><td>"+Tpvenc+"</td><td>"+Tven30+"</td><td>"+Tven60+"</td><td>"+Tven90+"</td><td>"+Tven180+"</td><td>"+Tven360+"</td><td>"+Tmas360+"</td><td>"+TTotalC+"</td></tr>");
					rs.getStatement().getConnection().close();
				}
				if(TipoReporte.equals("Radicada")){
					/*CAMBIAR ESTA CONSULTA PONIENDO DE PARAMETRO LA FECHA DE RADICADO DE LA FACTURA*/
					rs=mpr.Entidades();	// se consultan todas las entidades	
					long ValFact=0;long TTotalC=0;long Tpvenc=0;long Tven30=0;long Tven60=0;long Tven90=0;long Tven180=0;long Tven360=0;long Tmas360=0;
					out.print("<table border='1' cellspacing='0' width='100%'>");
					out.print("<tr><td colspan='9'>CARTERA CONSOLIDADA RADICADA HASTA EL "+FechaE+"</td></tr>");
					out.print("<tr><td>Empresa</td><td>Por vencer</td><td>Vencido 1-30</td><td>Vencido 31-60</td><td>Vencido 61-90</td><td>Vencido 91-180</td><td>Vencido 181-360</td><td>Mas 360</td><td>Total</td></tr>");
					while(rs.next()){						
						long TotalC=0;long pvenc=0;long ven30=0;long ven60=0;long ven90=0;long ven180=0;long ven360=0;long mas360=0;
						out.print("<td>"+rs.getString(2)+"</td>");
						rs1=mpr.BuscarFacturasRadicadasIndividual(rs.getString(1), FechaE);
						while(rs1.next()){
							//rs2=mpr.ValorFacturasRadicadasIndividual(rs1.getString("codigo"), FechaE);
							rs2=mpr.VerificarDeduccion(rs1.getString("consecutivo"),FechaE);
							if(rs2.next()){
								ValFact=rs1.getLong("valor")-rs2.getLong("Deducciones");
							}
							rs2.getStatement().getConnection().close();
							//long ValFact=Long.parseLong(ValorFAct);
							//if(rs1.getString(10).equals("2")){
								DifPlazo=rs1.getInt("dias");
								if((DifPlazo<=30)){pvenc=pvenc+ValFact;}
								if(((DifPlazo>=31)&&(DifPlazo<=60))){ven30=ven30+ValFact;}
								if(((DifPlazo>=61)&&(DifPlazo<=90))){ven60=ven60+ValFact;}
								if(((DifPlazo>=91)&&(DifPlazo<=120))){ven90=ven90+ValFact;}
								if(((DifPlazo>=121)&&(DifPlazo<=210))){ven180=ven180+ValFact;}
								if(((DifPlazo>=211)&&(DifPlazo<=390))){ven360=ven360+ValFact;}
								if((DifPlazo>390)){mas360=mas360+ValFact;}
							//}
								
						}
						TotalC=pvenc+ven30+ven60+ven90+ven180+ven360+mas360;
						rs1.getStatement().getConnection().close();
						out.print("<td>"+pvenc+"</td><td>"+ven30+"</td><td>"+ven60+"</td><td>"+ven90+"</td><td>"+ven180+"</td><td>"+ven360+"</td><td>"+mas360+"</td><td>"+TotalC+"</td></tr>");
						Tpvenc=Tpvenc+pvenc;
						Tven30=Tven30+ven30;
						Tven60=Tven60+ven60;
						Tven90=Tven90+ven90;
						Tven180=Tven180+ven180;
						Tven360=Tven360+ven360;
						Tmas360=Tmas360+mas360;
						TTotalC=TTotalC+TotalC;
					}
					out.print("<tr><td>TOTALES:</td><td>"+Tpvenc+"</td><td>"+Tven30+"</td><td>"+Tven60+"</td><td>"+Tven90+"</td><td>"+Tven180+"</td><td>"+Tven360+"</td><td>"+Tmas360+"</td><td>"+TTotalC+"</td></tr>");
					rs.getStatement().getConnection().close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("UCRN")){
			
			String CodEnt=req.getParameter("CodEnt");
			String Fecha=req.getParameter("Fecha");
			System.out.println("ENTRO EN UCRN codent="+CodEnt+" Fecha= "+Fecha);
			long PorVencer=0;long venc30=0;long venc60=0;long venc90=0;long venc180=0;long venc360=0;long venc361=0;long Total=0;
			//MetodoProveedor mpr= new MetodoProveedor();
			rs=mpr.BuscarFacturasRadicadasUnido(CodEnt, Fecha);
			try {
				out.print("<table border='1' width='100%' ><tr><td>RADICADO UNIDO DE EMPRESA</td></tr><tr><td>Entidad</td><td>Nit</td><td>Numero Factura</td><td>Fecha Factura</td><td>Fecha Radicado</td><td>Valor Factura</td><td>Por Vencer</td><td>Vencido 1-30</td><td>Vencido 31-60</td><td>Vencido 61-90</td><td>Vencido 91-180</td><td>Vencido 180-360</td><td>Mas 360</td><td>Total</td></tr>");
				while(rs.next()){
					int DifPlazo=rs.getInt(7);
					String CodFac=rs.getString(1);
					String ValorFAct="";
					out.print("<tr><td>"+rs.getString(5)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(4)+"</td>");
					rs1=mpr.ValorFacturasRadicadasIndividual(CodFac, Fecha);
					if(rs1.next()){
						ValorFAct=rs1.getString(4);
						if(ValorFAct==null){
							ValorFAct=rs.getString(4);
						}
					}
					Total=Total+Long.parseLong(ValorFAct);
					rs1.getStatement().getConnection().close();
					if(DifPlazo<=30){PorVencer=PorVencer+Long.parseLong(ValorFAct);					 out.print("<td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
					if(((DifPlazo>=31)&&(DifPlazo<=60))){venc30=venc30+Long.parseLong(ValorFAct); 	 out.print("<td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
					if(((DifPlazo>=61)&&(DifPlazo<=90))){venc60=venc60+Long.parseLong(ValorFAct);	 out.print("<td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
					if(((DifPlazo>=91)&&(DifPlazo<=120))){venc90=venc90+Long.parseLong(ValorFAct);	 out.print("<td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
					if(((DifPlazo>=121)&&(DifPlazo<=210))){venc180=venc180+Long.parseLong(ValorFAct);out.print("<td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
					if(((DifPlazo>=211)&&(DifPlazo<=390))){venc360=venc360+Long.parseLong(ValorFAct);out.print("<td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>"+ValorFAct+"</td>");}
					if((DifPlazo>390)){venc361=venc361+Long.parseLong(ValorFAct);					 out.print("<td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>"+ValorFAct+"</td>");}
					out.print("</tr>");
				}
				out.print("<tr><td colspan='6' ></td><td>Total por Vencer</td><td>Total 30 Dias</td><td>Total 60 Dias</td><td>Total 90 Dias</td><td>Total 180 Dias</td><td>Total 360 Dias</td><td>Total Mas 360 Dias</td><td>Total</td></tr>");
				out.print("<tr><td colspan='6' ></td><td>"+PorVencer+"</td><td>"+venc30+"</td><td>"+venc60+"</td><td>"+venc90+"</td><td>"+venc180+"</td><td>"+venc360+"</td><td>"+venc361+"</td><td>"+Total+"</td></tr>");

				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("CRN")){
			//System.out.println("ENTRO EN CRN");
			long PorVencer=0;long venc30=0;long venc60=0;long venc90=0;long venc180=0;long venc360=0;long venc361=0;long Total=0;
			String CodEnt=req.getParameter("CodEnt");
			String Fecha=req.getParameter("Fecha");
			String NombreEntidad=req.getParameter("NombreEntidad");
			//MetodoProveedor mpr= new MetodoProveedor();
			rs=mpr.BuscarFacturasRadicadasIndividual(CodEnt, Fecha);
			try {				
				out.print("<table border='1' cellspacing='0'  width='100%' ><tr><td colspan='14' >RADICADO INDIVIDUAL DE EMPRESA</td></tr> " +
						"<tr><td>Entidad</td><td></td><td>Numero Factura</td><td>Fecha Factura</td><td>Fecha Radicado</td><td>Valor Factura</td><td>Por Vencer</td><td>Vencido 1-30</td><td>Vencido 31-60</td><td>Vencido 61-90</td><td>Vencido 91-180</td><td>Vencido 180-360</td><td>Mas 360</td><td>Total</td></tr>");
				while(rs.next()){
					long ValorFAct=0;
					int DifPlazo=rs.getInt("dias");
					out.print("<tr><td>"+NombreEntidad+"</td><td></td><td>"+rs.getString("consecutivo")+"</td><td>"+rs.getString("fecha")+"</td><td>"+rs.getString("fecha_radicado")+"</td><td>"+rs.getString("valor")+"</td>");
					//rs1=mpr.ValorFacturasRadicadasIndividual(CodFac, Fecha);
					//rs1=mpr.BuscarFacturasRadicadasIndividual(CodEnt, Fecha);
					//if(rs1.next()){
						//ValorFAct=rs1.getString(4);
						rs2=mpr.VerificarDeduccion(rs.getString("consecutivo"),Fecha);
						if(rs2.next()){
							ValorFAct=rs.getLong("valor")-rs2.getLong("Deducciones");
						}
						rs2.getStatement().getConnection().close();
						
						Total=Total+ValorFAct;
						//rs1.getStatement().getConnection().close();
						if(DifPlazo<=30){PorVencer=PorVencer+ValorFAct; 					out.print("<td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
						if(((DifPlazo>=31)&&(DifPlazo<=60))){venc30=venc30+ValorFAct; 		out.print("<td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
						if(((DifPlazo>=61)&&(DifPlazo<=90))){venc60=venc60+ValorFAct;		out.print("<td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
						if(((DifPlazo>=91)&&(DifPlazo<=120))){venc90=venc90+ValorFAct;	 	out.print("<td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
						if(((DifPlazo>=121)&&(DifPlazo<=210))){venc180=venc180+ValorFAct;	out.print("<td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>0</td><td>"+ValorFAct+"</td>");}
						if(((DifPlazo>=211)&&(DifPlazo<=390))){venc360=venc360+ValorFAct;	out.print("<td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>0</td><td>"+ValorFAct+"</td>");}
						if((DifPlazo>391)){venc361=venc361+ValorFAct; 						out.print("<td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>0</td><td>"+ValorFAct+"</td><td>"+ValorFAct+"</td>");}
						out.print("</tr>");
					//}
				}
				out.print("<tr><td colspan='6' ></td><td>Total por Vencer</td><td>Total 30 Dias</td><td>Total 60 Dias</td><td>Total 90 Dias</td><td>Total 180 Dias</td><td>Total 360 Dias</td><td>Total Mas 360 Dias</td><td>Total</td></tr>");
				out.print("<tr><td colspan='6' ></td><td>"+PorVencer+"</td><td>"+venc30+"</td><td>"+venc60+"</td><td>"+venc90+"</td><td>"+venc180+"</td><td>"+venc360+"</td><td>"+venc361+"</td><td>"+Total+"</td></tr>");
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("SCG")){
			/************************************************************************/
			/************************SINCRONIZACION DE CARTERA***********************/
			/************************************************************************/
			//String CodEnt=req.getParameter("CodEnt");
			//String FechaE=req.getParameter("FechaE");
			System.out.println("ENTRA EN SINCRONIZACION GENERAL DE CARTERA ");
			String plazo_corto="0";
			String plazo_30="0"; 
			String plazo_60="0"; 
			String plazo_90="0";
			String plazo_180="0";
			String plazo_360="0";
			String Por_vencer="0";
			String Resta="";
			String CodFacturaC="";
			int DiaFact=0;
			int DiaAbo=0;
			int DifPlazo=0;
			String FechaUltDet="";
			String DifPlazoS="";
			try{
				//rs2=mc.SincronizarCarteraGeneral();
				//if(rs2.next()){
					rs=mc.SincronizarCarteraGeneral();
					while(rs.next()){
						CodFacturaC=rs.getString(16);
						rsrc=mc.ResumenRestanteFactura(CodFacturaC);
						if(rsrc.next()){
							Resta=rsrc.getString(4);
							FechaUltDet=rsrc.getString(5);
							FechFa=rsrc.getString(6);
							//System.out.println("FechFa "+FechFa+" FechaUltDet "+FechaUltDet);
							rsd1=mc.NumeroDiasFecha(FechFa);
							rsd2=mc.NumeroDiasFechaActual();
							if(rsd1.next()){
								DiaFact=rsd1.getInt(1);
							}
							rsd1.getStatement().getConnection().close();
					
							if(rsd2.next()){
								DiaAbo=rsd2.getInt(1);
							}
							//******
							rsd2.getStatement().getConnection().close();
							DifPlazo=DiaAbo-DiaFact;
							DifPlazoS=DifPlazo+"";
							System.out.println("DifPlazo "+DifPlazo);
							//System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
							if((DifPlazo<=30)){
								/**del por vencer **/
								Por_vencer=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0","0",Por_vencer);
							}
							if((DifPlazo>=31)&&(DifPlazo<=60)){
								/**vencido 1-30**/
								plazo_corto=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,"0","0","0","0","0","0");
							}
							if((DifPlazo>=61)&&(DifPlazo<=90)){
								/**vencido 31-60**/
								plazo_30=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0",plazo_30,"0","0","0","0","0");
							}
							if((DifPlazo>=91)&&(DifPlazo<=120)){
								/**vencido 61-90**/
								plazo_60=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0",plazo_60,"0","0","0","0");
							}
							if((DifPlazo>=121)&&(DifPlazo<=210)){
								/**vencido 91-180**/
								plazo_90=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0",plazo_90,"0","0","0");
							}
							if((DifPlazo>=211)&&(DifPlazo<=390)){
								/**vencido 181-360**/
								plazo_180=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0",plazo_180,"0","0");
							}							
							if(DifPlazo>=391){
								/**mayor de 360**/
								plazo_360=Resta;
								System.out.println("DifPlazo "+DifPlazo);
								mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0",plazo_360,"0");
							}


						}else{
							rs2=mc.BuscarFacturaSinDetalle(CodFacturaC);
							if(rs2.next()){
								Resta=rs2.getString(1);
							//	FechaUltDet=rsrc.getString(5);
								FechFa=rs2.getString(2);
								//System.out.println("FechFa "+FechFa+" FechaUltDet "+FechaUltDet);
								rsd1=mc.NumeroDiasFecha(FechFa);
								rsd2=mc.NumeroDiasFechaActual();
								if(rsd1.next()){
									DiaFact=rsd1.getInt(1);
								}
								rsd1.getStatement().getConnection().close();
						
								if(rsd2.next()){
									DiaAbo=rsd2.getInt(1);
								}
								//******
								rsd2.getStatement().getConnection().close();
								DifPlazo=DiaAbo-DiaFact;
								DifPlazoS=DifPlazo+"";
								//System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
								if((DifPlazo<=30)){
									/**del por vencer **/
									Por_vencer=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0","0",Por_vencer);
								}
								if((DifPlazo>=31)&&(DifPlazo<=60)){
									/**vencido 1-30**/
									plazo_corto=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,"0","0","0","0","0","0");
								}
								if((DifPlazo>=61)&&(DifPlazo<=90)){
									/**vencido 31-60**/
									plazo_30=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0",plazo_30,"0","0","0","0","0");
								}
								if((DifPlazo>=91)&&(DifPlazo<=120)){
									/**vencido 61-90**/
									plazo_60=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0",plazo_60,"0","0","0","0");
								}
								if((DifPlazo>=121)&&(DifPlazo<=210)){
									/**vencido 91-180**/
									plazo_90=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0",plazo_90,"0","0","0");
								}
								if((DifPlazo>=211)&&(DifPlazo<=390)){
									/**vencido 181-360**/
									plazo_180=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0",plazo_180,"0","0");
								}							
								if(DifPlazo>=391){
									/**mayor de 360**/
									plazo_360=Resta;
									System.out.println("DifPlazo "+DifPlazo);
									mc.ActualizarPlazoCartera(CodFacturaC,"0","0","0","0","0",plazo_360,"0");
								}
							}
							rs2.getStatement().getConnection().close();
						}
						rsrc.getStatement().getConnection().close();
					}
					rs.getStatement().getConnection().close();		
				//}
				//else{
					/**SE CREA EL RESGITRO EN LA TABLA CONT_CARTERA_PLAZO**/
					//mc.CrearPlazoCartera(numero_factura, valor_factura, Cod_factura);
				//}
		
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
			/************************************************************************/
			/*********************FIN DE SINCRONIZACION DE CARTERA*******************/
			/************************************************************************/
		}
		
		if(va.equals("gcd")){
			System.out.println("ENTRA EN SINCRONIZACION DETALLADA DE CARTERA ");
			/*String CodEnt=req.getParameter("CodEnt");
			String FechaE=req.getParameter("FechaE");
			String plazo_corto="0";
			String plazo_30="0"; 
			String plazo_60="0"; 
			String plazo_90="0";
			String plazo_180="0";
			String plazo_360="0";
			String Resta="";
			String CodFacturaC="";
			int DiaFact=0;
			int DiaAbo=0;
			int DifPlazo=0;
			String FechaUltDet="";
			String DifPlazoS="";
			try{			
				rs=mc.SincronizarCarteraDetallada(CodEnt);
				while(rs.next()){
					CodFacturaC=rs.getString(16);
					rsrc=mc.ResumenRestanteFactura(CodFacturaC);
					if(rsrc.next()){
						Resta=rsrc.getString(4);
						FechaUltDet=rsrc.getString(5);
						FechFa=rsrc.getString(6);
						System.out.println("FechFa "+FechFa+" FechaUltDet "+FechaUltDet);
						rsd1=mc.NumeroDiasFecha(FechFa);
						rsd2=mc.NumeroDiasFechaActual();
						if(rsd1.next()){
							DiaFact=rsd1.getInt(1);
						}
						rsd1.getStatement().getConnection().close();
					
						if(rsd2.next()){
							DiaAbo=rsd2.getInt(1);
						}

						rsd2.getStatement().getConnection().close();
						DifPlazo=DiaAbo-DiaFact;
						DifPlazoS=DifPlazo+"";
						System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
						if((DifPlazo<=30)){
							//del 1-30
							//plazo_corto="0";
							plazo_30="0"; 
							plazo_60="0"; 
							plazo_90="0";
							plazo_180="0";
							plazo_360="0";						
							plazo_corto=Resta;
							System.out.println("Entro en 1-30  CodFacturaC "+CodFacturaC+" plazo_corto="+plazo_corto+" plazo_30 "+plazo_30+" plazo_60 "+plazo_60+" plazo_90 "+plazo_90+" plazo_180 "+plazo_180+" plazo_360 "+plazo_360);
							mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,plazo_30,plazo_60,plazo_90,plazo_180,plazo_360);
						}
						if((DifPlazo>=31)&&(DifPlazo<=60)){
							//del 31-60
							plazo_corto="0";
							//plazo_30="0"; 
							plazo_60="0"; 
							plazo_90="0";
							plazo_180="0";
							plazo_360="0";						
							plazo_30=Resta;
							System.out.println("Entro en 31-60  CodFacturaC "+CodFacturaC+" plazo_corto="+plazo_corto+" plazo_30 "+plazo_30+" plazo_60 "+plazo_60+" plazo_90 "+plazo_90+" plazo_180 "+plazo_180+" plazo_360 "+plazo_360);
							mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,plazo_30,plazo_60,plazo_90,plazo_180,plazo_360);
						}
						if((DifPlazo>=61)&&(DifPlazo<=90)){
							//del 61-90
							plazo_corto="0";
							plazo_30="0"; 
							//plazo_60="0"; 
							plazo_90="0";
							plazo_180="0";
							plazo_360="0";						
							plazo_60=Resta;
							System.out.println("Entro en 61-90  CodFacturaC "+CodFacturaC+" plazo_corto="+plazo_corto+" plazo_30 "+plazo_30+" plazo_60 "+plazo_60+" plazo_90 "+plazo_90+" plazo_180 "+plazo_180+" plazo_360 "+plazo_360);
							mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,plazo_30,plazo_60,plazo_90,plazo_180,plazo_360);
						}
						if((DifPlazo>=91)&&(DifPlazo<=180)){
							//del 91-180
							plazo_corto="0";
							plazo_30="0"; 
							plazo_60="0"; 
							//plazo_90="0";
							plazo_180="0";
							plazo_360="0";						
							plazo_90=Resta;
							System.out.println("Entro en 91-180  CodFacturaC "+CodFacturaC+" plazo_corto="+plazo_corto+" plazo_30 "+plazo_30+" plazo_60 "+plazo_60+" plazo_90 "+plazo_90+" plazo_180 "+plazo_180+" plazo_360 "+plazo_360);
							mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,plazo_30,plazo_60,plazo_90,plazo_180,plazo_360);
						}
						if((DifPlazo>=181)&&(DifPlazo<=360)){
							//del 181-360
							plazo_corto="0";
							plazo_30="0"; 
							plazo_60="0"; 
							plazo_90="0";
							//plazo_180="0";
							plazo_360="0";						
							plazo_180=Resta;
							System.out.println("Entro en 181-360  CodFacturaC "+CodFacturaC+" plazo_corto="+plazo_corto+" plazo_30 "+plazo_30+" plazo_60 "+plazo_60+" plazo_90 "+plazo_90+" plazo_180 "+plazo_180+" plazo_360 "+plazo_360);
							mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,plazo_30,plazo_60,plazo_90,plazo_180,plazo_360);
						}
						if((DifPlazo>360)){
							//mayor de 360
							plazo_corto="0";
							plazo_30="0"; 
							plazo_60="0"; 
							plazo_90="0";
							plazo_180="0";
							//plazo_360="0";						
							plazo_360=Resta;
							System.out.println("Entro mayor 360  CodFacturaC "+CodFacturaC+" plazo_corto="+plazo_corto+" plazo_30 "+plazo_30+" plazo_60 "+plazo_60+" plazo_90 "+plazo_90+" plazo_180 "+plazo_180+" plazo_360 "+plazo_360);
							mc.ActualizarPlazoCartera(CodFacturaC,plazo_corto,plazo_30,plazo_60,plazo_90,plazo_180,plazo_360);
						}

					}
					rsrc.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();*/
				/*************************************************************/
				/******************IMPRIMIR REPORTE DESDE JAVA****************/
				/**************FIN IMPRIMIR REPORTE DESDE JAVA****************/
				/*************************************************************/
				
			/*} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}*/
		}
		if(va.equals("obn")){
			String CodEnt=req.getParameter("CodEnt");
			rs=mc.ObtenerNitEmpresa(CodEnt);
			try {
				if(rs.next()){
					out.print(rs.getString(2));
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("OmRc")){
			System.out.println("ENTRA OMITIR DETALLE RECIBO CAJA EN SINCRONIZACION DETALLADA DE CARTERA ");
			String ValorAbono=req.getParameter("ValorAbono");
			String CodFacturaOm=req.getParameter("CodFactura");
			try{
				long PrecioFactura=0;
				long ValorAbonado=0;
				long ValorPendiente=0;
				rs5=mc.CargarCuentaFactura(CodFacturaOm);
				if(rs5.next()){
					PrecioFactura = rs5.getLong(6);
				//	String Estado="0";
					ValorPendiente=rs5.getLong(10);
					ValorAbonado=rs5.getLong(9);
					
					ValorPendiente=ValorPendiente+Long.parseLong(ValorAbono);
					ValorAbonado=ValorAbonado-Long.parseLong(ValorAbono);
					mc.ActualizarFacturaAbonadoPendiente(CodFactura, ValorPendiente+"", ValorAbonado+"");
					//-Integer.parseInt(cantidad);
					
				} rs5.getStatement () .close () ;
				
				mc.OmitirDetalleFactura(ValorAbono, CodFacturaOm);
				mc.OmitirDetalleReciboCaja(CodDetCaja);
				out.print("<table border='1' width='100%' >");
				out.print("<tr><td>No Documento</td><td>Saldo</td><td>Valor Abono</td><td>Accion</td></tr>");
				rs1=mc.BuscarDetalleReciboCaja(CodRecCaja);
				while(rs1.next()){
					out.print("<tr><td>"+rs1.getString(6)+"</td><td>"+FormatoMoneda(rs1.getString(4))+"</td><td>"+FormatoMoneda(rs1.getString(5))+"</td><td><a href='#' onclick='OmitirDetReciboCaja("+rs1.getString(1)+","+rs1.getString(5)+","+rs1.getString(3)+")'>Omitir</a></td></tr>");
					ValorRecibo=ValorRecibo+rs1.getLong(5);
				}
				rs1.getStatement().getConnection().close();
				
				/********************************************************************/	
				
				//int valor_factura2=Integer.parseInt(PrecioFactura);
				long Pagado=0;
				rs6=mc.ObtenerDetalleFactura(CodFacturaOm);
				while(rs6.next()){
					Pagado=rs6.getLong(1)+Pagado;
				}
				if(PrecioFactura == Pagado){
					/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
					mc.CerrarFactura(CodFactura,"1");
				}else{
					mc.CerrarFactura(CodFactura,"0");
				}
				rs6.getStatement().getConnection().close();
				
				/*********************************************************************************************/

				/*********************************************************************************************/
				String Por_vencer="0";
				String plazo_corto="0";
				String plazo_30="0"; 
				String plazo_60="0"; 
				String plazo_90="0";
				String plazo_180="0";
				String plazo_360="0";
				String Resta="";
				int DiaFact=0;
				int DiaAbo=0;
				int DifPlazo=0;
				String FechaUltDet="";
				String DifPlazoS="";
				
				
				rsrc=mc.ResumenRestanteFactura(CodFactura);
				if(rsrc.next()){
					Resta=rsrc.getString(4);
					FechaUltDet=rsrc.getString(5);
					FechFa=rsrc.getString(6);
				}
				rsrc.getStatement().getConnection().close();
				System.out.println("FechFa "+FechFa+" FechaUltDet "+FechaUltDet);
				rsd1=mc.NumeroDiasFecha(FechFa);
				rsd2=mc.NumeroDiasFechaActual();
				if(rsd1.next()){
					DiaFact=rsd1.getInt(1);
				}
				rsd1.getStatement().getConnection().close();
				
				if(rsd2.next()){
					DiaAbo=rsd2.getInt(1);
				}
				
				rsd2.getStatement().getConnection().close();
				DifPlazo=DiaAbo-DiaFact;
				DifPlazoS=DifPlazo+"";
				//System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
				if((DifPlazo<=30)){
					/**del por vencer **/
					Por_vencer=Resta;
					mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0","0",Por_vencer);
				}
				if((DifPlazo>=31)&&(DifPlazo<=60)){
					/**vencido 1-30**/
					plazo_corto=Resta;
					mc.ActualizarPlazoCartera(CodFactura,plazo_corto,"0","0","0","0","0","0");
				}
				if((DifPlazo>=61)&&(DifPlazo<=90)){
					/**vencido 31-60**/
					plazo_30=Resta;
					mc.ActualizarPlazoCartera(CodFactura,"0",plazo_30,"0","0","0","0","0");
				}
				if((DifPlazo>=91)&&(DifPlazo<=120)){
					/**vencido 61-90**/
					plazo_60=Resta;
					mc.ActualizarPlazoCartera(CodFactura,"0","0",plazo_60,"0","0","0","0");
				}
				if((DifPlazo>=121)&&(DifPlazo<=210)){
					/**vencido 91-180**/
					plazo_90=Resta;
					mc.ActualizarPlazoCartera(CodFactura,"0","0","0",plazo_90,"0","0","0");
				}
				if((DifPlazo>=211)&&(DifPlazo<=390)){
					/**vencido 181-360**/
					plazo_180=Resta;
					mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0",plazo_180,"0","0");
				}				
				if(DifPlazo>=391){
					/**mayor de 360**/
					plazo_360=Resta;
					mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0",plazo_360,"0");
				}


				/***************************************************************************/
				//								INT PrecioFactura =0;
				/*el valor abonado es lo q le han abonado a la cuenta
				 * el valor pendiente es lo q falta por pagar de la cuenta
				 * el estado inicial de este campo es el del valor de la factura*/
				
				int diferencia=0;
//				diferencia=Integer.parseInt(valor_Factura)-Integer.parseInt(valor_abono);
//				mc.InsertarDetalleFactura(CodFactura, valor_abono, FechaPago, "1", diferencia, NumSoporte, ObservacionRC, fecha, hora, CodUsu);
				/*********************************************************************************************/
				out.print("<tr><td><input name='txtCodReciboCaja' type='hidden' id='txtCodReciboCaja' value="+CodRecCaja+" /><input name='txtCantidadRC' type='hidden' id='txtCantidadRC' value="+ValorRecibo+" /></td></tr></table>");
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		if(va.equals("grc")){
			
			try {
				long PrecioFactura =0;
				/*el valor abonado es lo q le han abonado a la cuenta
				 * el valor pendiente es lo q falta por pagar de la cuenta
				 * el estado inicial de este campo es el del valor de la factura*/
				//System.out.println("Entro en generar recibo de caja 1");
				long ValorAbonado=0;
				long ValorPendiente=0;
				rs5=mc.CargarCuentaFactura(CodFactura);
				//System.out.println("Entro en generar recibo de caja 1 CodFactura "+CodFactura);
				if(rs5.next()){
				//	PrecioFactura = rs5.getInt(6);
					FechFa=rs5.getString(4);
					//System.out.println("FechFaggggggggg "+FechFa);
					ValorPendiente=rs5.getLong(10);
					ValorAbonado=rs5.getLong(9);
					ValorPendiente=ValorPendiente-Long.parseLong(valor_abono);
					ValorAbonado=ValorAbonado+Long.parseLong(valor_abono);
					String Estado="1";
					//System.out.println("Entro en generar recibo de caja 1 ValorPendiente "+ValorPendiente+" ValorAbonado "+ValorAbonado);
					
					mc.ActualizarFacturaAbonadoPendiente(CodFactura, ValorPendiente+"", ValorAbonado+"");
					
				}
				rs5.getStatement().getConnection().close();

				String CodReciCaja="";
				mc.CrearReciboCaja(valor_total, fecha, hora, CodUsu,CodEntidad, Tipo_Pago, ObservacionRC,Concepto,FechaPago);
				rs=mc.BuscarReciboCaja(fecha, hora);				
				if(rs.next()){
					CodReciCaja=rs.getString(1);
					mc.CrearDetalleReciboCaja(CodReciCaja, CodFactura, valor_Factura,
							valor_abono, Factura);
					
					out.print("<table border='1' width='100%' >");
					out.print("<tr><td>No Documento</td><td>Saldo</td><td>Valor Abono</td><td>Accion</td></tr>");
					
					rs1=mc.BuscarDetalleReciboCaja(CodReciCaja);
					while(rs1.next()){
						out.print("<tr><td>"+rs1.getString(6)+"</td><td>"+FormatoMoneda(rs1.getString(4))+"</td><td>"+FormatoMoneda(rs1.getString(5))+"</td><td><a href='#' onclick='OmitirDetReciboCaja("+rs1.getString(1)+","+rs1.getString(5)+","+rs1.getString(3)+")'>Omitir</a></td></tr>");
						ValorRecibo=ValorRecibo+rs1.getLong(5);
					}
					rs1.getStatement().getConnection().close();
					/*********************************************************************************************/
					long diferencia=0;
					diferencia=Long.parseLong(valor_Factura)-Long.parseLong(valor_abono);
					mc.InsertarDetalleFactura(CodFactura, valor_abono, FechaPago, "1", diferencia+"", NumSoporte, ObservacionRC, fecha+"", hora+"", CodUsu);
				/******************************************************************/	
					String Por_vencer="0";
					String plazo_corto="0";
					String plazo_30="0"; 
					String plazo_60="0"; 
					String plazo_90="0";
					String plazo_180="0";
					String plazo_360="0";
					String Resta="";
					int DiaFact=0;
					int DiaAbo=0;
					int DifPlazo=0;
					String DifPlazoS="";
					
					
					rsrc=mc.ResumenRestanteFactura(CodFactura);
					if(rsrc.next()){
						Resta=rsrc.getString(4);
					}
					rsrc.getStatement().getConnection().close();
					
					rsd1=mc.NumeroDiasFecha(FechFa);
					rsd2=mc.NumeroDiasFecha(FechaPago);
					if(rsd1.next()){
						DiaFact=rsd1.getInt(1);
					}
					rsd1.getStatement().getConnection().close();
					
					if(rsd2.next()){
						DiaAbo=rsd2.getInt(1);
					}
					
					//System.out.println("FechFa "+FechFa+" FechaPago "+FechaPago);
					rsd2.getStatement().getConnection().close();
					DifPlazo=DiaAbo-DiaFact;
					DifPlazoS=DifPlazo+"";
					//System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
					if((DifPlazo<=30)){
						/**del por vencer **/
						Por_vencer=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0","0",Por_vencer);
					}
					if((DifPlazo>=31)&&(DifPlazo<=60)){
						/**vencido 1-30**/
						plazo_corto=Resta;
						mc.ActualizarPlazoCartera(CodFactura,plazo_corto,"0","0","0","0","0","0");
					}
					if((DifPlazo>=61)&&(DifPlazo<=90)){
						/**vencido 31-60**/
						plazo_30=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0",plazo_30,"0","0","0","0","0");
					}
					if((DifPlazo>=91)&&(DifPlazo<=120)){
						/**vencido 61-90**/
						plazo_60=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0",plazo_60,"0","0","0","0");
					}
					if((DifPlazo>=121)&&(DifPlazo<=210)){
						/**vencido 91-180**/
						plazo_90=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0",plazo_90,"0","0","0");
					}
					if((DifPlazo>=211)&&(DifPlazo<=390)){
						/**vencido 181-360**/
						plazo_180=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0",plazo_180,"0","0");
					}
					
					if(DifPlazo>=391){
						/**mayor de 360**/
						plazo_360=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0",plazo_360,"0");
					}

					
					//-Integer.parseInt(cantidad);
					//mc.ActualizarPlazoCartera(CodFactura, plazo_30, plazo_60, plazo_90, plazo_180, plazo_360);
					/********************************************************************/	
					
					long valor_factura2=Long.parseLong(valor_Factura);
					long Pagado=0;
					rs6=mc.ObtenerDetalleFactura(CodFactura);
					while(rs6.next()){
						Pagado=rs6.getLong(1)+Pagado;
					}
					if(valor_factura2 == Pagado){
						/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
						mc.CerrarFactura(CodFactura,"1");
					}else{
						mc.CerrarFactura(CodFactura,"0");
					}
					rs6.getStatement().getConnection().close();
					
					/*********************************************************************************************/
					out.print("<tr><td><input name='txtCodReciboCaja' type='hidden' id='txtCodReciboCaja' value="+CodReciCaja+" /><input name='txtCantidadRC' type='hidden' id='txtCantidadRC' value="+ValorRecibo+" /></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
		}
	
		
		if(va.equals("grc1")){			
			try {
				rs=mc.RelacionRecibocajaFactura(CodRecCaja, CodFactura);
				if(rs.next()){
					out.print("1");
				}else{
					int PrecioFactura =0;
					/*el valor abonado es lo q le han abonado a la cuenta
					 * el valor pendiente es lo q falta por pagar de la cuenta
					 * el estado inicial de este campo es el del valor de la factura*/
					System.out.println("Entro en generar recibo de caja 2");
					long ValorAbonado=0;
					long ValorPendiente=0;
					rs5=mc.CargarCuentaFactura(CodFactura);
					System.out.println("Entro en generar recibo de caja 2 CodFactura "+CodFactura);
					if(rs5.next()){
						//	PrecioFactura = rs5.getInt(6);
						String Estado="1";
						FechFa=rs5.getString(4);
						ValorPendiente=rs5.getLong(10);
						ValorAbonado=rs5.getLong(9);
						ValorPendiente=ValorPendiente-Long.parseLong(valor_abono);
						ValorAbonado=ValorAbonado+Long.parseLong(valor_abono);
						System.out.println("Entro en generar recibo de caja 2 ValorPendiente "+ValorPendiente+" ValorAbonado "+ValorAbonado);
						mc.ActualizarFacturaAbonadoPendiente(CodFactura, ValorPendiente+"", ValorAbonado+"");
						//-Integer.parseInt(cantidad);
					
					}
					rs5.getStatement().getConnection().close();
				
					//rs=mc.BuscarReciboCaja(fecha, hora);				
					mc.CrearDetalleReciboCaja(CodRecCaja, CodFactura, valor_Factura,
							valor_abono, Factura);					
					out.print("<table border='1' width='100%' >");
					out.print("<tr><td>No Documento</td><td>Saldo</td><td>Valor Abono</td><td>Accion</td></tr>");
					rs1=mc.BuscarDetalleReciboCaja(CodRecCaja);
					while(rs1.next()){
						out.print("<tr><td>"+rs1.getString(6)+"</td><td>"+FormatoMoneda(rs1.getString(4))+"</td><td>"+FormatoMoneda(rs1.getString(5))+"</td><td><a href='#' onclick='OmitirDetReciboCaja("+rs1.getString(1)+","+rs1.getString(5)+","+rs1.getString(3)+")'>Omitir</a></td></tr>");
						ValorRecibo=ValorRecibo+rs1.getLong(5);
					}
					rs1.getStatement().getConnection().close();
					/*********************************************************************************************/
					long diferencia=0;
					diferencia=Long.parseLong(valor_Factura)-Long.parseLong(valor_abono);
					mc.InsertarDetalleFactura(CodFactura, valor_abono, FechaPago, "1", diferencia+"", NumSoporte, ObservacionRC, fecha+"", hora+"", CodUsu);
					/*********************************************************************************************/
					/******************************************************************/	
					
					String Por_vencer="0";
					String plazo_corto="0";
					String plazo_30="0"; 
					String plazo_60="0"; 
					String plazo_90="0";
					String plazo_180="0";
					String plazo_360="0";
					String Resta="";
					rsrc=mc.ResumenRestanteFactura(CodFactura);
					if(rsrc.next()){
						Resta=rsrc.getString(4);
					}
					rsrc.getStatement().getConnection().close();
					
					int DiaFact=0;
					int DiaAbo=0;
					int DifPlazo=0;
					String DifPlazoS="";
					System.out.println("FechFa "+FechFa);
					rsd1=mc.NumeroDiasFecha(FechFa);
					rsd2=mc.NumeroDiasFecha(FechaPago);
					if(rsd1.next()){
						DiaFact=rsd1.getInt(1);
					}
					rsd1.getStatement().getConnection().close();
					
					if(rsd2.next()){
						DiaAbo=rsd2.getInt(1);
					}
					rsd2.getStatement().getConnection().close();
					DifPlazo=DiaAbo-DiaFact;
					DifPlazoS=DifPlazo+"";
					
					//falta la actualizacion de los campos cada vez q se ingresa un recibo de caja.
					
					//y el reporte total de las radicadas y emitidas.
					System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
					if((DifPlazo<=30)){
						/**del por vencer **/
						Por_vencer=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0","0",Por_vencer);
					}
					if((DifPlazo>=31)&&(DifPlazo<=60)){
						/**vencido 1-30**/
						plazo_corto=Resta;
						mc.ActualizarPlazoCartera(CodFactura,plazo_corto,"0","0","0","0","0","0");
					}
					if((DifPlazo>=61)&&(DifPlazo<=90)){
						/**vencido 31-60**/
						plazo_30=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0",plazo_30,"0","0","0","0","0");
					}
					if((DifPlazo>=91)&&(DifPlazo<=120)){
						/**vencido 61-90**/
						plazo_60=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0",plazo_60,"0","0","0","0");
					}
					if((DifPlazo>=121)&&(DifPlazo<=210)){
						/**vencido 91-180**/
						plazo_90=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0",plazo_90,"0","0","0");
					}
					if((DifPlazo>=211)&&(DifPlazo<=390)){
						/**vencido 181-360**/
						plazo_180=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0",plazo_180,"0","0");
					}
					
					if(DifPlazo>=391){
						/**mayor de 360**/
						plazo_360=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0",plazo_360,"0");
					}

					
					//-Integer.parseInt(cantidad);
					//mc.ActualizarPlazoCartera(CodFactura, plazo_30, plazo_60, plazo_90, plazo_180, plazo_360);
				/********************************************************************/	
					/********************************************************************/						
					long valor_factura2=Long.parseLong(valor_Factura);
					long Pagado=0;
					rs6=mc.ObtenerDetalleFactura(CodFactura);
					while(rs6.next()){
						Pagado=rs6.getLong(1)+Pagado;
					}
					if(valor_factura2 == Pagado){
						/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
						mc.CerrarFactura(CodFactura,"1");
					}else{
						mc.CerrarFactura(CodFactura,"0");
					}
					rs6.getStatement().getConnection().close();					
					/*********************************************************************************************/

					out.print("<tr><td><input name='txtCodReciboCaja' type='hidden' id='txtCodReciboCaja' value="+CodRecCaja+" /><input name='txtCantidadRC' type='hidden' id='txtCantidadRC' value="+ValorRecibo+" /></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.println(e);
				e.printStackTrace();
			}
		}
		if(va.equals("LLRC")){
			
			try {
				out.print("<table border='1' width='100%' >");
				out.print("<tr><td>No Documento</td><td>Saldo</td><td>Valor Abono</td><td>Accion</td></tr>");
				rs1=mc.BuscarDetalleReciboCaja(CodRecCaja);
				while(rs1.next()){
					out.print("<tr><td>"+rs1.getString(6)+"</td><td>"+FormatoMoneda(rs1.getString(4))+"</td><td>"+FormatoMoneda(rs1.getString(5))+"</td><td><a href='#' onclick='OmitirDetReciboCaja("+rs1.getString(1)+","+rs1.getString(5)+","+rs1.getString(3)+")'>Omitir</a></td></tr>");
					ValorRecibo=ValorRecibo+rs1.getLong(5);
				}
				rs1.getStatement().getConnection().close();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			out.print("<tr><td><input name='txtCodReciboCaja' type='hidden' id='txtCodReciboCaja' value="+CodRecCaja+" /><input name='txtCantidadRC' type='hidden' id='txtCantidadRC' value="+ValorRecibo+" /></td></tr></table>");
		}
		
		if(va.equals("auf")){	
			String BusAgru=req.getParameter("BusAgru");
			try {
				int cont=0;
				String NumFactura="";
				String ValorFactura="";
				String CodigoFactura="";
				String Abono1="";
				out.print("<table>");
				if(BusAgru.equals("1")){
					String Nit="";
					//consulta agrupada con nit
					System.out.println("Entra en Consulta de Facturas Agrupada por nit");
					rs=mc.ObtenerNitCuentaEmpresa(CodEntidad);
					if(rs.next()){
						Nit=rs.getString(2);
						
						rs1=mc.AutocompletaBuscarFacturaNitAgrupado(Nit, Factura);
						while(rs1.next()){
							cont=cont+1;
							NumFactura=rs1.getString(1);
							ValorFactura=rs1.getString(2);
							CodigoFactura=rs1.getString(3);
							out.print("<tr><td><a href='#' onclick='SeleccionFactura(&quot;"+rs1.getString(1)+"&quot;,&quot;"+rs1.getString(2)+"&quot;,"+rs1.getString(3)+")' >"+rs1.getString(1)+"</a> </td></tr>");
						}
						rs1.getStatement().getConnection().close();
					}
					rs.getStatement().getConnection().close();
				}
				if(BusAgru.equals("0")){
					//consulta sin agrupar
					System.out.println("Entra en Consulta de Facturas sin Agrupar por nit");
					rs=mc.AutocompletaBuscarFactura(CodEntidad, Factura);
					while(rs.next()){
						cont=cont+1;
						NumFactura=rs.getString(1);
						ValorFactura=rs.getString(2);
						CodigoFactura=rs.getString(3);
						out.print("<tr><td><a href='#' onclick='SeleccionFactura(&quot;"+rs.getString(1)+"&quot;,&quot;"+rs.getString(2)+"&quot;,"+rs.getString(3)+")' >"+rs.getString(1)+"</a> </td></tr>");
					}
					rs.getStatement().getConnection().close();
				}
				
				out.print("<input type='hidden' id='txtConta' value="+cont+">");
				if(cont==1){ 
					out.print("<input type='hidden' id='txtNF1' value="+NumFactura+">" +
								"<input type='hidden' id='txtVF1' value="+ValorFactura+">" +
								"<input type='hidden' id='txtCF1' value="+CodigoFactura+">");
					rs2=mc.PrecioFactura(NumFactura);
					//out.print("<table>");
					if(rs2.next()){
						Abono1=rs2.getString(1);
					}
					//out.print("</table>");
					out.print("<input type='hidden' id='txtAB1' value="+Abono1+"></table>");
					rs2.getStatement().getConnection().close();
					System.out.print("NumFactura "+NumFactura+" ValorFactura "+ValorFactura+" CodigoFactura "+CodigoFactura+" Abono1 "+Abono1);
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(va.equals("slf")){
			try {
				rs=mc.PrecioFactura(Factura);
				//out.print("<table>");
				if(rs.next()){
					out.print(rs.getString(1));
				}
				//out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/*********************************************************/
	
		if(va.equals("1")){
			try{
				mc.CrearFactura(cod_cuenta_fk, estado, fecha_factura, fecha_insercion, 
						hora_insercion, Retiva, numero_factura, observacion, 
						precio_factura, ret_ica, tipo, usuario_insercion);
				String tipo_pago="0";
				String cod_det_factura="0";
				rs=mc.BuscarFactura(fecha_insercion, hora_insercion);
				if(rs.next()){
					mc.CrearComplementoFactura(rs.getString(1), cod_det_factura, CentroCosto, RetFuente, tipo_pago);
					out.print("Ingreso Exitoso.");
				}
				
				rs.getStatement().getConnection().close();
				
			}catch(Exception ex){
	        	out.println("ERROR EN VA=1 CUENTAS "+ex);
			}
		}
		
		if(va.equals("2")){
				out.print("<table border='1' width='100%' ><tr>");
				if(tipo.equals("0")){
					out.print("<td colspan='2'><div id='cabecera2' class='style11' align='center'>NOTAS DEBITO </div></td>");
					out.print("</tr><tr><td width='11%' >DIGITE NUMERO DE FACTURA</td><td width='37%' ><input name='txtBusqueda' type='text' id='txtBusqueda' size='60' onkeyup='A(this,event)' ><input name='btnBuscar' type='button' id='btnBuscar' value='     Buscar     ' onclick='BuscarFacturaCobrar()'></td></tr></table>");
					out.print("<table width='100%' border='1'>" +	
							"<tr><td><div id='DatosCuenta'></div></td>" +
							"</tr></table>");
				}
				
				if(tipo.equals("1")){
					out.print("<td colspan='2'><div id='cabecera2' class='style11' align='center'>CUENTAS POR COBRAR </div></td>");
					out.print("</tr><tr><td width='11%' >DIGITE NUMERO DE FACTURA</td><td width='37%' ><input name='txtBusqueda' type='text' id='txtBusqueda' size='60' onkeyup='A(this,event)' ><input name='btnBuscar' type='button' id='btnBuscar' value='     Buscar     ' onclick='BuscarFacturaCobrar()'></td></tr></table>");
					out.print("<table width='100%' border='1'>" +	
							"<tr><td><div id='DatosCuenta'></div></td>" +
							"</tr></table>");
				}				
							
		}
		
		if(va.equals("2.1")){	
			try {
				rs=mc.BuscarCargarCuenta(tipo, DatoBusqueda);
				out.print("<table width='100%' border='1' ><tr><td colspan='2'><div id='DatosCuenta'>");
				while(rs.next()){
					out.print("<tr>" +
							"<td width='11%'>"+rs.getString(2)+"</td>" +
							"<td width='37%'><a href='#' onclick='VerEstadoCuenta("+rs.getString(2)+")' >"+rs.getString(3)+"</a></td>" +
							"</tr>");
				}
				out.print("</div></td></tr></table>");
				if(rs==null){
					out.print("No Hay Nada");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("2.11")){	
			String NumeroFactura=req.getParameter("NumFactura");
			if(tipo.equals("1")){
				try {
					rs=mc.BuscarFacturaCobrar(tipo, NumeroFactura);
					out.print("<table width='100%' border='1' ><tr><td>Nombre Cuenta</td><td>Fecha Factura</td><td>Numero Factura</td><td>Precio Factura</td><td>Valor Pendiente</td>");
					while(rs.next()){
						out.print("<tr>" +
								"<td><a href='#' onclick='VerEstadoFactura("+rs.getString(1)+",1)' >"+rs.getString(3)+"</a></td>" +
								"<td>"+rs.getString(4)+"</td>" +
								"<td>"+rs.getString(5)+"</td>" +
								"<td>"+FormatoMoneda(rs.getString(6))+"</td>");
							
						rs1=mc.ValorBonado(rs.getString(1));
						if(rs1.next()){
							long Dif=0;
							Dif=rs.getLong(6)-rs1.getLong(1);
							out.print("<td>"+FormatoMoneda(Dif+"")+"</td>");
						}
						rs1.getStatement().getConnection().close();
						out.print("</tr>");
					}
					out.print("</table>");
					if(rs==null){
						out.print("No Hay Nada");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en Control Cuentas va=2.11 "+e);
					e.printStackTrace();
				}
			}
			if(tipo.equals("0")){
				try {
					rs=mc.BuscarFacturaCobrarND(tipo, NumeroFactura);
					out.print("<table width='100%' border='1' ><tr><td>Nombre Cuenta</td><td>Fecha Factura</td><td>Numero Factura</td><td>Precio Factura</td>");
					while(rs.next()){
						out.print("<tr>" +
								"<td><a href='#' onclick='VerEstadoFacturaND("+rs.getString("cod_enc_factura")+")' >"+rs.getString("razon_social")+"</a></td>" +
								"<td>"+rs.getString("fecha")+"</td>" +
								"<td>"+rs.getString("consecutivo")+"</td>" +
								"<td>"+FormatoMoneda(rs.getString("valor"))+"</td>");
						
						out.print("</tr>");
					}
					out.print("</table>");
					if(rs==null){
						out.print("No Hay Nada");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error en Control Cuentas va=2.11 "+e);
					e.printStackTrace();
				}
			}
		}
		
		if(va.equals("2.2")){			
			try {
				rs=mc.CargarCuenta(CodCuenta);
				out.print("<table border='1' width='100%' ><tr>");
				if(tipo.equals("0")){
					out.print("<td colspan='2'><div id='cabecera2' class='style11' align='center'>CUENTAS POR PAGAR </div></td>");
				}
				
				if(tipo.equals("1")){
					out.print("<td colspan='2'><div id='cabecera2' class='style11' align='center'>CUENTAS POR COBRAR </div></td>");
				}
				
				out.print("</tr></table>");
				out.print("<table width='100%' border='1'><tr class='style3'>" +
						"<td width='11%'><div align='center'>NUMERO DE CUENTA</div></td>" +
						"<td width='37%'><div align='center'>NOMBRE DE CUENTA</div></td>" +
						"<td width='17%'><div align='center'>NUMERO FACTURA</div></td>" +
						"<td width='10%'><div align='center'>FECHA FACTURA</div></td>" +
						"<td width='13%'><div align='center'>PRECIO FACTURA</div></td>" +
						"<td width='6%'><div align='center'>IVA</div></td>" +
						"<td width='6%'><div align='center'>RET.ICA</div></td>" +
						"</tr>");
				while(rs.next()){
					out.print("<tr>" +
							"<td width='11%' >"+rs.getString(2)+"</td>" +
							"<td width='37%' ><a href='#' onclick='VerEstadoFactura("+rs.getString(1)+","+tipo+")' >"+rs.getString(3)+"</a></td>" +
							"<td>"+rs.getString(5)+"</td>" +
							"<td>"+rs.getString(4)+"</td>" +
							"<td>"+rs.getString(6)+"</td>" +
							"<td>"+rs.getString(7)+"</td>" +
							"<td>"+rs.getString(8)+"</td>" +
							"</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("2.3ND")){
			String CodFacturaND=req.getParameter("CodFactura");
			String FechaFija=req.getParameter("FechaFija");
			rs=mc.FacturaND(CodFacturaND);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
					out.print("<tr><td colspan='8' align='center' bgcolor='#999999'><b><font color='white'>NOTAS DEBITO</font></b> </td></tr>");
			        out.print("<tr><td width='11%' >Numero Factura </td><td width='12%' >"+rs.getString("consecutivo")+"</td><td width='5%' >Entidad</td><td width='31%' >"+rs.getString("razon_social")+"</td>");
					out.print("<td width='4%' >Nit</td><td width='15%' >"+rs.getString("nit")+"</td><td width='9%' >Valor Factura </td><td width='13%' >"+FormatoMoneda(rs.getString("ValorFactura"))+"</td></tr>");
					out.print("<tr><td >Concepto Nota </td><td colspan='3' ><input name='txtConceptoND' type='text' id='txtConceptoND' style='width:100%' /></td><td >Valor</td><td ><input name='txtValorNota' type='text' id='txtValorNota' onkeyup='SoloNUm() ' style='width:100%' /></td>");
					//out.print("<td >Fecha</td><td ><input name='txtFechaNota' type='text' id='txtFechaNota' style='width:100%' /></td></tr>");
					out.print("<td >Fecha</td><td >"+FechaFija+"</td></tr>");
					out.print("<tr><td >&nbsp;</td><td colspan='6' align='center' ><input type='button' name='Button' onclick='GuardarND("+CodFacturaND+")' value='     Guardar     '></td><td >&nbsp;</td></tr></table>");
			        out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
					out.print("<tr><th scope='col'>Numero Nota Debito </th><th scope='col'>Numero Factura </th><th scope='col'>Entidad</th><th scope='col'>Fecha Nota </th><th scope='col'>Valor</th></tr>");
					rs1=mc.ListaNotasDebito(CodFacturaND);
					while(rs1.next()){
						out.print("<tr onclick='ReporteNotaDB("+rs1.getString("codigo")+")'><td>"+rs1.getString("codigo")+"</td><td>"+rs1.getString("consecutivo")+"</td><td>"+rs1.getString("razon_social")+"</td><td>"+rs1.getString("fecha_nota")+"</td><td>"+FormatoMoneda(rs1.getString("ValorND"))+"</td></tr>");
					}
					rs1.getStatement().getConnection().close();
					
					out.print("</table>");

				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("GNDB")){
			String CodFacturaND=req.getParameter("CodFacturaND");
			String ConceptoND=req.getParameter("ConceptoND");
			String ValorNota=req.getParameter("ValorNota");
			String CodUsuFk=req.getParameter("CodUsu");
			java.util.Date FechaAc1 = new java.util.Date();
			java.sql.Date Fecha_Insercion1 = new java.sql.Date(FechaAc1.getTime());		
			java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
			String ValLetr=Convertir(ValorNota);
			System.out.println("ConceptoND "+ConceptoND);
			mc.InsertarNotasDebito(CodFacturaND, ConceptoND, ValorNota, Fecha_Insercion1+"", Hora_Insercion1+"", CodUsuFk, Fecha_Insercion1+"",ValLetr);
			rs=mc.VerificarInsercion(Fecha_Insercion1+"", Hora_Insercion1+"");
			try {
				if(rs.next()){ 
					out.print(rs.getString(1));
				}else{
					out.print("NO");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		if(va.equals("2.3")){
			try {
				out.print("<table border='1' width='100%' ><tr>");
				//if(tipo.equals("1")){
					out.print("<td><div id='cabecera2' class='style11' align='center'>CUENTA POR COBRAR</div></td>");
				//}
				out.print("</tr></table>");
				out.print("<table width='100%' border='1'><tr class='style3'>" +
						"<td width='11%'><div align='center'>NUMERO DE CUENTA</div></td>" +
						"<td width='37%'><div align='center'>NOMBRE DE CUENTA</div></td>" +
						"<td width='17%'><div align='center'>NUMERO FACTURA</div></td>" +
						"<td width='10%'><div align='center'>FECHA FACTURA</div></td>" +
						"<td width='13%'><div align='center'>VALOR FACTURA</div></td>" +
						//"<td width='6%'><div align='center'>IVA</div></td>" +
						//"<td width='6%'><div align='center'>RET.ICA</div></td>" +
						"<td width='6%'><div align='center'></div></td>" +
						"<td width='6%'><div align='center'></div></td>" +
				"</tr>");
			long PrecioInicialFactura=0;
					rs=mc.CargarCuentaFactura(CodFactura);
					while(rs.next()){
						out.print("<tr>" +
							"<td>"+rs.getString(2)+"</td>" +
							"<td>"+rs.getString(3)+"</td>" +
							"<td>"+rs.getString(5)+"</td>" +
							"<td>"+rs.getString(4)+"</td>" +
							"<td>"+FormatoMoneda(rs.getString(6))+"</td>" +
							"<td></td>" +
							"<td></td>" );
							//"<td>"+rs.getString(7)+" %</td>" +
							//"<td>"+rs.getString(8)+"</td>" );
						PrecioInicialFactura=rs.getLong(6);
					}
					rs.getStatement().getConnection().close();
					out.print("</tr>");
					out.print("</table>");
					/********************************************************/
					out.print("<table border='1' width='100%'><tr class='style3'>" +
							"<td colspan='4'><div id='cabecera2' class='style11' align='center'>DETALLE DE FACTURA </div></td>" +
							"</tr><tr class='style3'>" +
							"<td width='11%'><div align='center'>FECHA</div></td>" +
							"<td width='22%'><div align='center'>VALOR</div></td>" +
							"<td width='16%'><div align='center'>N� SOPORTE</div></td>" +
							"<td width='47%'><div align='center'>CONCEPTO</div></td>" +
							"</tr><tr><td colspan='4'><div id='DetFact1'>");
					out.print("<table border='0' width='100%'>");
					rs1=mc.ObtenerDetalleFactura(CodFactura);
					long Restante=0;
					long Pagado=0;
					String  CodDetalleFactura="";
					String CodigoFactura="";
					while(rs1.next()){
						Pagado=rs1.getLong(1)+Pagado;
						CodDetalleFactura=rs1.getString(8);
						CodigoFactura=rs1.getString(9);
						/*if(tipo.equals("0")){
							//out.print("<td><div id='cabecera2' class='style11' align='center'>CUENTA POR PAGAR </div></td>");
							
							
							out.print("<tr>" +
										"<td width='11%'><a href='#' onclick='VerReporteDetallePago("+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(5)+"</a></td>" +
										"<td width='24%'>"+FormatoMoneda(rs1.getString(1))+"</td>" +
										"<td width='19%'>"+rs1.getString(4)+"</td>" +
										"<td width='47%'>"+rs1.getString(7)+"</td>" +
										"</tr>");
						}*/
							/*aqui tiene que ir el formato de cuando se le hace un abono a factura 
							ya sea por recibo de caja o por el mismo cuadro en la ventana y 
							tiene q llevar la relacion con la tabla con_recibo de caja.
							
							si el abono es por glosa tiene q cambiar el reporte 
							y que muestre una nota credito */
							
						rs10=mc.ReportesGlosas(CodDetalleFactura);
					if(rs10.next()){
						/**AQUI SE IMPRIMEN LAS NOTAS CREDITO***/
						 out.print("<tr>" +
									"<td width='11%'><a href='#' onclick='VerReporteDetalleCobro("+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(5)+"</a></td>" +
									"<td width='24%'>"+FormatoMoneda(rs1.getString(1))+"</td>" +
									"<td width='19%'>"+rs1.getString(4)+"</td>" +
									"<td width='47%'>"+rs1.getString(7)+"</td>" +
									"</tr>");

					}
					rs10.getStatement().getConnection().close();
					       
					}
					rs1.getStatement().getConnection().close();
					

					/**AQUI SE IMPRIMEN LOS RECIBOS DE CAJA***/
						rs4=mc.FacturaEnRecibodeCaja(CodFactura);
						while(rs4.next()){
							out.print("<tr><td width='11%'><a href='#' onclick='ImprimirReciboCaja("+rs4.getString(2)+")'>"+rs4.getString(7)+"</a></td>");
							out.print("<td width='24%'>"+FormatoMoneda(rs4.getString(5))+"</td>" +
									"<td width='19%'>PAGO DE FACTURA</td>" +
									"<td width='47%'>"+rs4.getString(8)+"</td>" +
									"</tr>");
						}
						rs4.getStatement().getConnection().close();								 
					
					
					rs2=mc.RestanteFactura(CodFactura);
					if(rs2.next()){
						Restante=rs2.getLong(1);
						Restante=PrecioInicialFactura-Restante;
					}
					else{
						Restante=PrecioInicialFactura;
					}
					rs2.getStatement().getConnection().close();
					//rs1.getStatement().getConnection().close();
					out.print("</table>");
					out.print("</div></td></tr><tr><td>VALOR PAGADO:</td><td>"+FormatoMoneda(Pagado+"")+"</td><td>VALOR RESTANTE:</td><td>"+FormatoMoneda(Restante+"")+"</td></tr></table>");
					/*if(tipo.equals("0")){
					out.print("<table border='0' width='100%'><tr class='style3'><td colspan='7'><div id='cabecera2' class='style11' align='center'>DETALLE DE PAGO </div></td></tr>");
					out.print("<tr class='style3'><td width='10%'>DEBITAR DE LA CUENTA </td><td colspan='2'><input name='txtCuenta' type='text' id='txtCuenta' size='40' onkeyup='autocompletarCuentas()'></td><td width='6%'>VALOR</td><td width='19%'><input name='txtcantidad' type='text' id='txtcantidad'></td><td width='7%'>FECHA</td><td width='22%'><input name='txtFechaPA' type='text' id='txtFechaPA'><input name='cmbTipoPago' type='hidden' id='cmbTipoPago' value='0'></td></tr>");
					out.print("<tr class='style3'><td><input name='txtCodigoCuenta' type='hidden' id='txtCodigoCuenta' size='12'></td><td colspan='2'><div id='Cuentas'></div></td><td>N� SOPORTE </td><td><input name='txtNunSoporte' type='text' id='txtNunSoporte'></td><td>CONCEPTO</td><td><textarea name='txtObervacion' cols='30' rows='3' id='txtObervacion'></textarea></td></tr>");
					out.print("<tr class='style3'><td colspan='7'><div align='center'><input name='btnGuardar' type='button' id='btnGuardar' value='     Guardar     ' onclick='GuardarPorCobrar("+CodFactura+","+tipo+")'></div></td></tr></table>");
					}*/
					if(tipo.equals("1")){
						//String FechaFija=req.getParameter("FechaFija");
						
						java.util.Date FechaFija = new java.util.Date();
						java.sql.Date FechaServidor = new java.sql.Date(FechaFija.getTime());
						
						String Fecha=FechaServidor.toString();
						Fecha.split("-");
						
						int y=Fecha.split("-").length;
					 	String[] z=Fecha.split("-");	
					 	String Anio="";
					 	String Mes="";
					 	String Dia="";
					 	for(int x=0; x<y-1; x=x+1)
					 	{ 
					 		Anio=z[0];
					 		Mes=z[1];
					 		Dia=z[2];

					    }

					 	String FechaFinal=Dia+"/"+Mes+"/"+Anio;
//fecha=fecha;
						/*************POR COBRAR*******************/
						out.print("<table border='0' width='100%'><tr class='style3'><td colspan='9'><div id='cabecera2' class='style11' align='center'>DETALLE</div></td></tr>");
						out.print("<tr class='style3'><td width='10%'></td><td colspan='2'><input name='txtCuenta' type='hidden' id='txtCuenta' size='40' onkeyup='autocompletarCuentas()' value='0'></td><td width='6%'>VALOR</td><td width='19%'><input name='txtcantidad' type='text' id='txtcantidad'><input type='hidden' id='txtPendiente2' value="+Restante+"></td><td width='7%'>FECHA (dd/MM/YYYY)</td><td width='22%'><input name='txtFechaPA' type='text' id='txtFechaPA' onKeyUp='masca(this,&quot;/&quot;,patron,true)' value='"+FechaFinal+"'  /></td><td>MOVIMIENTO</td><td><select id='cmbTipoPago'><option value='Seleccione' selected=''>Seleccione</option>");
						/****************************/
						rs10=mc.ObtenerMovimientosCredito();
						while(rs10.next()){
							out.print("<option value="+rs10.getString(1)+">"+rs10.getString(2)+"</option>");
						}
						rs10.getStatement().getConnection().close();
						/****************************/
						out.print("</select></td></tr>");
						out.print("<tr class='style3'><td><input name='txtCodigoCuenta' type='hidden' id='txtCodigoCuenta' size='12' value='1100' /></td><td colspan='2'><div id='Cuentas'></div></td><td>N� SOPORTE </td><td><input name='txtNunSoporte' type='text' id='txtNunSoporte' value='0' ></td><td>CONCEPTO</td><td><textarea name='txtObervacion' cols='30' rows='3' id='txtObervacion'></textarea></td><td></td><td><input type='hidden' name='cmbBanco' id='cmbBanco'>");
						//out.print("<option value='Seleccione'>Seleccione</option>");
					//	while(rs10.next()){
						//	out.print("<option value="+rs10.getString(3)+">"+rs10.getString(2)+"</option>");
						//}
						//rs10.getStatement().getConnection().close();
						out.print("</select></td>");
						out.println("</tr>");
						out.print("<tr class='style3'><td colspan='7'><div align='center' id='BotonH'><input name='btnGuardar' type='button' id='btnGuardar' value='     Guardar     ' onclick='GuardarPorCobrar("+CodFactura+","+tipo+")'></div></td></tr></table>");
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(va.equals("rdff")){
			rs=mc.ObtenerFacturasFaltantes();
			try {
				while(rs.next()){
					rs1=mc.DatosFacturaDetalle(rs.getString(2));
					if(rs1.next()){
						rs3=mc.BuscarFacturasEnCarteraPlazo(rs1.getString(3));
						if(rs3.next()){
							
						}else{
							mc.CrearPlazoCartera(rs1.getString(3), rs1.getString(4), rs1.getString(1));
						}
						rs3.getStatement().getConnection().close();
					}else{
						mc.CrearFactura(rs.getString(5), "0", rs.getString(3), rs.getString(3), "00:00:01", "0", rs.getString(2), "-", rs.getString(4), "0", "1", "74");
						rs2=mc.DatosFacturaDetalle(rs.getString(2));
						if(rs2.next()){
							mc.CrearPlazoCartera(rs2.getString(3), rs2.getString(4), rs2.getString(1));
						}
						rs2.getStatement().getConnection().close();
					}
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*consultar en la tabla facturas_faltantes OK.
			
			consultar si las facturas existen en la tabla cont_facturas 
			no las inserta y las ponemos en un log_temporal OK
			
			si las facturas no estan en la tabla cont_facturas las insertamos 
			en la tabla cont_facturas OK
			
			hacemos un select tomando como parametro el numero de la factura para 
			obtener el codigo,el valor, la fecha de emision
			
			y la insertamos en la tabla cont_cartera_plazos.*/
			
			
			
		}
		
		if(va.equals("SinCar1")){
			
			rs=mc.ListarCuentaEmpresas();
			try {
				out.print("<table border='0' width='100%'><tr><td>Seleccione Entidad</td></tr>");
				out.print("<tr><td><select id='cmbCuentaEmpresa' ><option value='Seleccione' selected='' >Seleccione</option>");
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select><input name='btnGenerar' type='button' id='btnGenerar' value='Generar' onclick='SincronizarContFactura_CarteraPlazo()'></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("SinCar")){
			System.out.print("Entro SinCar");
			String CodCuentaEmpresa=req.getParameter("CodCuentaEmpresa");
			int contador=0;
			rs=mc.ObtenerFacturasCuenta(CodCuentaEmpresa);
			try {
				while(rs.next()){
					rs1=mc.DatosFacturaDetalle(rs.getString(3));
					if(rs1.next()){
						rs3=mc.BuscarFacturasEnCarteraPlazo(rs1.getString(3));
						if(rs3.next()){
							
						}else{
							mc.CrearPlazoCartera(rs1.getString(3), rs1.getString(4), rs1.getString(1));
							contador=contador+1;
						}
						rs3.getStatement().getConnection().close();
					}
					rs1.getStatement().getConnection().close();
				}
				out.print("Proceso Ejecutado Exitosamente\n Se Ingresaron "+contador+" En la Tabla Cartera");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("swr")){			
			try {
				MetodoMovimientos mm = new MetodoMovimientos();
				rs=mc.ObtenerFacturasRadicadas();
				int Conw=0;
				int Cons=0;
				while(rs.next()){
					System.out.println(" "+rs.getString(1)+"--"+rs.getString(3)+"---"+ rs.getString(2));
					rs1=mc.BuscarFacturasEnCarteraPlazo(rs.getString(3));
					if(rs1.next()){
						Cons=Cons+1;
						mc.InsertarFechaEmisionEnCarteraPlazoRadicar(rs.getString(2),rs1.getString(10));
						System.out.println("Radicando Cons "+Cons+" Numero Factura="+rs.getString(3)+" Fecha radicado="+rs.getString(2));
						mm.actualizarEstFactRadicadaenContabilidad(rs.getString(3));
					}else{						
						System.out.println("No Ingreso Nada Conw "+Conw);
						rs2=mc.DatosFacturaDetalle(rs.getString(3));
						if(rs2.next()){
							mc.InsertarTodoEnCarteraPlazoRadicar(rs2.getString(3), rs2.getString(4), rs2.getString(1), rs.getString(2));
							mm.actualizarEstFactRadicadaenContabilidad(rs.getString(3));
							System.out.println("Ingreso Nuevo tabla cont_cartera_plazo factura numero_factura="+rs.getString(3));
						}else{
							System.out.println("No lo encontro en Cont_facturas.");
						}
						Conw=Conw+1;
						rs2.getStatement().getConnection().close();
						System.out.println(" Conw "+Conw);
					}
					rs1.getStatement().getConnection().close();
				}
				out.print("Sincronizacion Terminada Exitosamente.");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("sw")){
			rs=mc.ObtenerFacturas();
			int Conw=0;
			int Cons=0;
			try {
				while(rs.next()){
					System.out.print(" "+rs.getString(3)+"---"+ rs.getString(4)+"---"+ rs.getString(1)+"---"+ rs.getString(5));
					rs1=mc.BuscarFacturasEnCarteraPlazo(rs.getString(3));
					if(rs1.next()){
						Cons=Cons+1;
						mc.InsertarFechaEmisionEnCarteraPlazo(rs.getString(5),rs.getString(1));
						System.out.println("Cons "+Cons);
					}else{
						Conw=Conw+1;
						mc.InsertarTodoEnCarteraPlazo(rs.getString(3), rs.getString(4), rs.getString(1), rs.getString(5));
						System.out.println("Conw "+Conw);
					}
					rs1.getStatement().getConnection().close();
				}
				out.print("Sincronizacion Terminada Exitosamente.");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
if(va.equals("BPCF")){
			
			try {
				String NumDoc=req.getParameter("NumDoc");
				String TipoDoc=req.getParameter("TipoDoc");
				rs=mc.BuscarDatosPacientesFacturar(NumDoc, TipoDoc);
				if(rs.next()){
					out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr><th width='12%' align='left' scope='col'>Nombre Paciente </th><th colspan='3' align='left' scope='col'><input type='hidden' id='txtNombrePaciente' value='"+rs.getString("NombrePaciente")+"' >"+rs.getString("NombrePaciente")+"</th> " +
							"<input type='hidden' id='txtDireccion' value='"+rs.getString("direccion")+"' ><input type='hidden' id='txtTelefono' value='"+rs.getString("Telefono")+"' ><input type='hidden' id='txtTipoAfiliacion' value='"+rs.getString("tipo_afiliacion")+"' ><input type='hidden' id='txtEstrato' value='"+rs.getString("estrato")+"' ><th width='6%' align='left'  scope='col'>Entidad</th><th colspan='5'  align='left' scope='col'> " +
									"<select name='cmbEntidad' id='cmbEntidad'>");					
					out.print("<option value='Seleccione'>Seleccione</option>");
					rs1=mc.ListarEntidades();
					while(rs1.next()){
						out.print("<option value="+rs1.getString("ent_nit")+">"+rs1.getString("nombre_entidad")+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></th></tr><tr><td>Admision</td><td width='15%'><input name='txtCodAdmision' type='text' id='txtCodAdmision' ></td><td width='6%'>F.Ingreso</td><td width='9%'><input name='txtFechaIngreso' type='text' id='txtFechaIngreso' size='20' ></td><td>F.Egreso</td><td width='10%'><input name='txtFechaEgreso' type='text' id='txtFechaEgreso' size='20' ></td>");
			 
					out.print("<td width='11%'>Fecha Factura </td><td width='10%'><input name='txtFechaFactura' type='text' id='txtFechaFactura' size='20' ></td><td width='10%'>Hora Factura </td><td width='11%'><input name='txtHoraFactura' type='text' id='txtHoraFactura' size='20' ></td></tr><tr><td>Valor Factura </td><td><input name='txtValoFactura' type='text' id='txtValoFactura' ></td>");
			 
					out.print("<td>Copago</td><td><input name='txtCopago' type='text' id='txtCopago' size='20' value='0' ></td><td>Usuario</td><td><select name='cmbUsuario' id='cmbUsuario'>");
			 
					out.print("<option value='Seleccione'>Seleccione</option>");
					rs1=mc.ListarUsuarios();
					while(rs1.next()){
						out.print("<option value="+rs1.getString("usu_codigo")+">"+rs1.getString("usuario")+"</option>");
					}
					rs1.getStatement().getConnection().close();
				
					out.print("</select></td><td>Numero Factura </td><td><input name='txtNumeroFactura' type='text' id='txtNumeroFactura' size='20' ></td><td>&nbsp;</td><td><input type='button' name='Button' value='     Guardar     ' onclick='GuardarFactura()' ></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("GFCT")){
			String NumDoc=req.getParameter("NumDoc");
			String TipoDoc=req.getParameter("TipoDoc");
			String txtDireccion=req.getParameter("txtDireccion");
			String txtTelefono=req.getParameter("txtTelefono");
			String txtTipoAfiliacion=req.getParameter("txtTipoAfiliacion");
			String txtEstrato=req.getParameter("txtEstrato");
			String txtNombrePaciente=req.getParameter("txtNombrePaciente");
			String cmbEntidad=req.getParameter("cmbEntidad");
			String txtNumeroFactura=req.getParameter("txtNumeroFactura");
			String txtCopago=req.getParameter("txtCopago");
			String cmbUsuario=req.getParameter("cmbUsuario");
			String txtFechaFactura=req.getParameter("txtFechaFactura");
			String txtHoraFactura=req.getParameter("txtHoraFactura");
			String txtValoFactura=req.getParameter("txtValoFactura");
			String txtFechaEgreso=req.getParameter("txtFechaEgreso");
			String txtFechaIngreso=req.getParameter("txtFechaIngreso");
			String txtCodAdmision=req.getParameter("txtCodAdmision");
			
			String NombreEntidad="";
			String NitEntidad="";
			String DireccionEntidad="";
			String TelefonoEntidad="";
			String CiudadEntidad="";
			
			String Documento=TipoDoc+"-"+NumDoc;
			//- buscar los datos de la eps
			rs1=mc.ListarEntidadesCodigo(cmbEntidad);
			try {
				if(rs1.next()){
					NombreEntidad=rs1.getString("nombre_entidad");
					NitEntidad=rs1.getString("ent_nit_contratante");
					DireccionEntidad=rs1.getString("direccion");
					TelefonoEntidad=rs1.getString("telefono");
					CiudadEntidad=rs1.getString("ciudad");
				}
				rs1.getStatement().getConnection().close();
				//- insertar en fact_enc_factura
				mc.InsertarEncabezadoFactura(cmbEntidad, NombreEntidad, NitEntidad, DireccionEntidad, TelefonoEntidad,
						CiudadEntidad, "30 Dias", txtNombrePaciente, Documento, txtDireccion, 
						txtTelefono, txtTipoAfiliacion, "0", txtFechaIngreso, txtFechaEgreso,
						txtCodAdmision, "", cmbUsuario, "-", txtFechaFactura,
						txtHoraFactura, "2", txtValoFactura, "VL", "0",
						txtCopago, "0", txtCopago, "0");
				String EncaFact="";
				rs1=mc.ConsultarEncabezado(txtFechaFactura, txtHoraFactura);
				if(rs1.next()){
					EncaFact=rs1.getString("cod_enc_factura");
					mc.InsertarNumeradas(rs1.getString("cod_enc_factura"), txtNumeroFactura, txtFechaFactura, "0");
				}
				rs1.getStatement().getConnection().close();
				
				/************************************************************/
				/*****INGRESAR EN LA TABLA CONT_FACTURAS****/
				//7.Creamos la factura en cont_factura 
				String cod_cuenta_fk1="";
				String estado1="0";
				String fecha_factura1="";
				String fecha_insercion1="";
				String hora_insercion1="";
				String iva="0";
				String numero_factura1="";
				String observacion1="-";
				String precio_factura1="";
				String ret_ica1="-";
				String tipo1="1";
					
				//MetodoCuentas mc= new MetodoCuentas();						
				try {
					ResultSet rsfc=null;
					rsfc=mc.BuscarDatosParaFactura(EncaFact);
					if(rsfc.next()){
						cod_cuenta_fk1=rsfc.getString(9);
						fecha_factura1=rsfc.getString(4);
						fecha_insercion1=rsfc.getString(7);
						hora_insercion1=rsfc.getString(8);
						numero_factura1=rsfc.getString(2);
						precio_factura1=rsfc.getString(3);
					}
					rsfc.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mc.CrearFactura(cod_cuenta_fk1, estado1, fecha_factura1, fecha_insercion1, hora_insercion1, iva, numero_factura1, observacion1, precio_factura1, ret_ica1, tipo1, cmbUsuario);

				//8.Creamos la factura en cont_detalle_factura y en cont_cartera_plazo
				try {
					ResultSet rsfc=null;
					rsfc=mc.DatosFacturaDetalle(txtNumeroFactura);
					if(rsfc.next()){					
						mc.CrearDetalleFactura(rsfc.getString(1), "0", fecha_factura1, "1", precio_factura1, "-", "-", fecha_insercion1, hora_insercion1, rsfc.getString(8), "CERO PESOS");
						mc.CrearPlazoCartera(txtNumeroFactura, precio_factura1,rsfc.getString(1));
					}
					rsfc.getStatement().getConnection().close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		if(va.equals("2.4")){
			String CodEstadoCuenta="";
			String CodDetalleFactura="";
			long restante=0;
			String CantidadInicial="";
			
			if(tipo.equals("1")){
				/*******************CUANDO ES POR COBRAR**********************/
				/**
				 * PRIMERO SE INSERTA EN LA TABLA CONT_ESTADO_CUENTA.
				 * SEGUNDO SE INSERTA EN LA TABLA CONT_DETALLE_FACTURA.
				 * PERO PARA INGRESAR EN LA TABLA CONT_DETALLE_FACTURA  HAY QUE RESTAR LO QUE PAGARON
				 * MENOS LO QUE DEBIAN (ANTES-AHORA=RESTANTE) Y ESE SE INGRESA EN EL CAMPO RESTANTE
				 * SI SE ABONA POR SEGUNDA VEZ SE TOMARIA EL RESTANTE DE LA FACTURA MENOS EL NUEVO 
				 * ABONO(ANTIGUO RESTANTE-NUEVO ABONO=NUEVO RESTANTE).
				 * TERCERO SE INGRESA EN LA TABLA CONT_DETFACT_ESTCUENTA SE INSERTAN LOS COD PK.
				 */
				
				try {					
					long UltimoPago=0;
					long CantidadTotalCuenta=0;
					String PrecioFacturaR="";
					rs2=mc.RestanteFactura(CodFactura);
					if(rs2.next()){
						rs4=mc.ObtenerCantidadEstadoCuenta(cod_cuenta_fk);
						while(rs4.next()){
							UltimoPago=rs4.getLong(1);
						}
						rs4.getStatement().getConnection().close();
						CantidadInicial=rs2.getString(1);
						PrecioFacturaR=rs2.getString(2);
						mc.InsertarEstadoCuenta(cod_cuenta_fk, cantidad, fecha, hora,tipo);
						rs=mc.BuscarEstadoCuentaFechaHora(fecha, hora);
						if(rs.next()){
							CodEstadoCuenta=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
						//restante=Integer.parseInt(CantidadInicial)-Integer.parseInt(cantidad);
						//System.out.println("PrecioFacturaR "+PrecioFacturaR+" - CantidadInicial "+CantidadInicial);
						restante=Long.parseLong(PrecioFacturaR)-Long.parseLong(CantidadInicial);
						/**************************************************************/						
						mc.InsertarDetalleFactura(CodFactura, cantidad, fechaPA, tipo, restante+"", NumSoporte, Observacion, fecha+"", hora+"", usuario_insercion);
						rs1=mc.BuscarDetalleFacturaFechaHora(fecha+"", hora+"",CodFactura);
						if(rs1.next()){
							CodDetalleFactura=rs1.getString(1);
							String TipoPago=req.getParameter("TipoPago");
							String ret_fuente="0";
							String cod_centro_costo="0";
							mc.CrearComplementoFactura(CodFactura, CodDetalleFactura, cod_centro_costo, ret_fuente, TipoPago);

						}
						rs1.getStatement().getConnection().close();

						mc.InsertarDetalleFacturaEstadoCuenta(CodEstadoCuenta, CodDetalleFactura);
						CantidadTotalCuenta=UltimoPago+Long.parseLong(cantidad);
						rs3=mc.ObtenerCuentaEstadoGeneral(cod_cuenta_fk,tipo);
						if(rs3.next()){
							mc.ActualizarEstadoGeneralCuenta(cod_cuenta_fk, tipo, CantidadTotalCuenta+"");
						}else{
							mc.InsertarEstadoGeneralCuenta(cod_cuenta_fk, cantidad, tipo);
						}
						rs3.getStatement().getConnection().close();
						/****************************se verifica si la factura ya esta pagada en su totalidad*****/
						long PrecioFactura =0;
						/*el valor abonado es lo q le han abonado a la cuenta
						 * el valor pendiente es lo q falta por pagar de la cuenta
						 * el estado inicial de este campo es el del valor de la factura*/
						long ValorAbonado=0;
						long ValorPendiente=0;
						rs5=mc.CargarCuentaFactura(CodFactura);
						if(rs5.next()){
							String Estado="1";
							PrecioFactura = rs5.getLong(6);
							ValorPendiente=rs5.getLong(10);
							ValorAbonado=rs5.getLong(9);
							ValorPendiente=ValorPendiente-Long.parseLong(cantidad);
							ValorAbonado=ValorAbonado+Long.parseLong(cantidad);
							mc.ActualizarFacturaAbonadoPendiente(CodFactura, ValorPendiente+"", ValorAbonado+"");
							//-Integer.parseInt(cantidad);
							
						}
						rs5.getStatement().getConnection().close();
						
						long Pagado=0;
						rs6=mc.ObtenerDetalleFactura(CodFactura);
						while(rs6.next()){
							Pagado=rs6.getLong(1)+Pagado;
						}
						if(PrecioFactura == Pagado){
							/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
							mc.CerrarFactura(CodFactura,"1");
						}else{
							mc.CerrarFactura(CodFactura,"0");
						}
						rs6.getStatement().getConnection().close();
						/*******************************************************************************************/
						
					}
					else{
						/**
						 * como no hay movimiento sobre esta factura se busca el saldo inicial.
						 * hay que buscar cual es el saldo inicial de la factura.
						 */
						rs3=mc.CargarCuentaFactura(CodFactura);
						if(rs3.next()){
							CantidadInicial=rs3.getString(6);
						}
						rs3.getStatement().getConnection().close();
						rs5=mc.ObtenerCantidadEstadoCuenta(cod_cuenta_fk);
						while(rs5.next()){
							UltimoPago=rs5.getLong(1);
						}
						rs5.getStatement().getConnection().close();
						CantidadTotalCuenta=UltimoPago+Long.parseLong(cantidad);
						mc.InsertarEstadoCuenta(cod_cuenta_fk, cantidad, fecha, hora,tipo);
						rs=mc.BuscarEstadoCuentaFechaHora(fecha, hora);
						if(rs.next()){
							CodEstadoCuenta=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
						restante=Long.parseLong(CantidadInicial)-Long.parseLong(cantidad);
						/**************************************************************/						
						mc.InsertarDetalleFactura(CodFactura, cantidad, fechaPA, tipo, restante+"", NumSoporte, Observacion, fecha+"", hora+"", usuario_insercion);
						rs1=mc.BuscarDetalleFacturaFechaHora(fecha+"", hora+"",CodFactura);
						if(rs1.next()){
							CodDetalleFactura=rs1.getString(1);
							String TipoPago=req.getParameter("TipoPago");
							String ret_fuente="0";
							String cod_centro_costo="0";
							mc.CrearComplementoFactura(CodFactura, CodDetalleFactura, cod_centro_costo, ret_fuente, TipoPago);

						}
						rs1.getStatement().getConnection().close();

						mc.InsertarDetalleFacturaEstadoCuenta(CodEstadoCuenta, CodDetalleFactura);
						rs4=mc.ObtenerCuentaEstadoGeneral(cod_cuenta_fk,tipo);
						if(rs4.next()){
							mc.ActualizarEstadoGeneralCuenta(cod_cuenta_fk, tipo, CantidadTotalCuenta+"");
						}else{
							mc.InsertarEstadoGeneralCuenta(cod_cuenta_fk, cantidad, tipo);
						}
						rs4.getStatement().getConnection().close();
						/****************************se verifica si la factura ya esta pagada en su totalidad*****/
						long PrecioFactura =0;
						/*el valor abonado es lo q le han abonado a la cuenta
						 * el valor pendiente es lo q falta por pagar de la cuenta
						 * el estado inicial de este campo es el del valor de la factura*/
						long ValorAbonado=0;
						long ValorPendiente=0;
						rs5=mc.CargarCuentaFactura(CodFactura);
						if(rs5.next()){
							String Estado="1";
							PrecioFactura = rs5.getLong(6);
							ValorPendiente=rs5.getLong(10);
							ValorAbonado=rs5.getLong(9);
							ValorPendiente=ValorPendiente-Long.parseLong(cantidad);
							ValorAbonado=ValorAbonado+Long.parseLong(cantidad);
							mc.ActualizarFacturaAbonadoPendiente(CodFactura, ValorPendiente+"", ValorAbonado+"");
							//-Integer.parseInt(cantidad);							
						}
						rs5.getStatement().getConnection().close();
					
						
						long Pagado=0;
						rs6=mc.ObtenerDetalleFactura(CodFactura);
						while(rs6.next()){
							Pagado=rs6.getLong(1)+Pagado;
						}
						if(PrecioFactura == Pagado){
							/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
							mc.CerrarFactura(CodFactura,"1");							
						}else{
							mc.CerrarFactura(CodFactura,"0");
						}
						rs6.getStatement().getConnection().close();
						/*******************************************************************************************/
					}
					rs2.getStatement().getConnection().close();
					//*********************************************************************/
					// se sube la nota credito o la glosa a contabilidad
					//1. se verifica el consecutivo de la nota credito;
					
					MetodoMovimientos mm = new MetodoMovimientos();
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
					//se busca el consecutivo de el recibo de caja
					String conso="";
					String cons="";
					String consn="";
					rs = mm.ConsecutivosdeCuentas("130");
					//try {
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
					//2. se crea el documento en la tabla cont_documentos;
					
					mm.CrearDocumento(annio,mes,"130",consn,fechacjmysql,"Nota Credito "+Observacion,cantidad,cantidad,"0","",usuario_insercion,fechacjmysql,hora+"");
					
					//3. se crea el detalle del documento;
					//3.1 se obtiene el codigo del documento creado
					String Coddocu="";
					rs = mm.ConsecutivoUDocumento(consn);if(rs.next()){Coddocu=rs.getString(1);}rs.getStatement().getConnection().close();

					//3.2 se obtiene el codigo de la entidad prestadora;
					rs3=mm.ConsultarcuentaIngresoClientes();
					String ctacliente="0";String nctacliente="";
					if(rs3.next()){	ctacliente=rs3.getString(1);nctacliente=rs3.getString(2);}rs3.getStatement().getConnection().close();
					//3.3 se crea el detalle de credito a la entidad
					mm.CrearDetalleDocumento(Coddocu,ctacliente,"1","2","2",nctacliente,"0","Nota Credito "+CodDetalleFactura,"0",cantidad,"0","NTC");
					//3.4 se obtiene el numero de la cuenta de la nota credito, para crear el detalle debito del documento
					String TipoPago=req.getParameter("TipoPago");
					String ctaNc="0";String nctaNc="";
					rs3=mm.ConsultarCuentaNc(TipoPago);
					if(rs3.next()){ctaNc=rs3.getString(1);nctaNc=rs3.getString(3);rs3.getStatement().getConnection().close();}
					mm.CrearDetalleDocumento(Coddocu,ctaNc,"1","2","2",nctaNc,"0","Nota Credito "+CodDetalleFactura,cantidad,"0","0","NTC");
					//3.5 se actualiza el consecutivo del documento
					int ctan=Integer.parseInt(conso)+1;
					conso=String.valueOf(ctan);
					mm.ActualizarConsecutivo(conso,"130");
					//*********************************************************************/
					/*******************************************************************/
					//aqui se va aponer la actualizacion de la tabla cont_cartera plazo.
					/*********************************************************************************************/
					String Por_vencer="0";
					String plazo_corto="0";
					String plazo_30="0"; 
					String plazo_60="0"; 
					String plazo_90="0";
					String plazo_180="0";
					String plazo_360="0";
					String Resta="";
					int DiaFact=0;
					int DiaAbo=0;
					int DifPlazo=0;
					String FechaUltDet="";
					String DifPlazoS="";
					
					
					rsrc=mc.ResumenRestanteFactura(CodFactura);
					if(rsrc.next()){
						Resta=rsrc.getString(4);
						FechaUltDet=rsrc.getString(5);
						FechFa=rsrc.getString(6);
					}
					rsrc.getStatement().getConnection().close();
					System.out.println("FechFa "+FechFa+" fecha "+fecha);
					rsd1=mc.NumeroDiasFecha(FechFa);
					rsd2=mc.NumeroDiasFecha(fecha+"");
					if(rsd1.next()){
						DiaFact=rsd1.getInt(1);
					}
					rsd1.getStatement().getConnection().close();
					
					if(rsd2.next()){
						DiaAbo=rsd2.getInt(1);
					}
					
					rsd2.getStatement().getConnection().close();
					DifPlazo=DiaAbo-DiaFact;
					DifPlazoS=DifPlazo+"";
					System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS+" Resta "+Resta);
					if((DifPlazo<=30)){
						/**del por vencer **/
						Por_vencer=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0","0",Por_vencer);
					}
					if((DifPlazo>=31)&&(DifPlazo<=60)){
						/**vencido 1-30**/
						plazo_corto=Resta;
						mc.ActualizarPlazoCartera(CodFactura,plazo_corto,"0","0","0","0","0","0");
					}
					if((DifPlazo>=61)&&(DifPlazo<=90)){
						/**vencido 31-60**/
						plazo_30=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0",plazo_30,"0","0","0","0","0");
					}
					if((DifPlazo>=91)&&(DifPlazo<=120)){
						/**vencido 61-90**/
						plazo_60=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0",plazo_60,"0","0","0","0");
					}
					if((DifPlazo>=121)&&(DifPlazo<=210)){
						/**vencido 91-180**/
						plazo_90=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0",plazo_90,"0","0","0");
					}
					if((DifPlazo>=211)&&(DifPlazo<=390)){
						/**vencido 181-360**/
						plazo_180=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0",plazo_180,"0","0");
					}
					
					if(DifPlazo>=391){
						/**mayor de 360**/
						plazo_360=Resta;
						mc.ActualizarPlazoCartera(CodFactura,"0","0","0","0","0",plazo_360,"0");
					}

					/*******************************************************************/
				//	out.print("CodFactura "+CodFactura+" CantidadInicial "+CantidadInicial+" cantidad "+cantidad+" CodEstadoCuenta "+CodEstadoCuenta+" restante "+restante+" CodDetalleFactura "+CodDetalleFactura);
				} catch (SQLException e) {
					out.print(e);
					e.printStackTrace();
				}
			}
			if(tipo.equals("0")){
				/*******************CUANDO ES POR PAGAR*****************************/
				/**
				 * PRIMERO HAY QUE VERIFICAR SI DE LA CUENTA QUE VAMOS A DEBITAR EL DINERO TIENE
				 * FONDOS.
				 * SI LOS TIENE ENTONCES SE PROCEDE A DESCONTAR LO QUE SE VA A PAGAR.
				 * Y A ACTUALIZAR EL VALOR TOTAL DE COMO QUEDA LA CUENTA.
				 * (SALDO CUENTA INICIAL-SALDO A PAGAR)
				 * SI NO LOS TIENE ENTONCES SE MANDA UN MSJ DE ERROR QUE DIGA QUE LA CUENTA
				 * SELECCIONADA NO TIENE FONDOS PARA REALIZAR LA TRANSACCION.
				 */
				
				try {
					long CantidadDisponibleEnCuenta=0;
					//int UltimoPago=0;
					//String CodigoEstadoGeneralCuenta="";
					rs=mc.ObtenerCantidadEstadoCuenta(cod_cuenta_fk);
					
					/*if(rs.next()){
						CantidadDisponibleEnCuenta=rs.getInt(1);
						//CodigoEstadoGeneralCuenta=rs.getString(2);
					}
					if(Integer.parseInt(cantidad) > CantidadDisponibleEnCuenta){
						out.print("No se Puede Debitar Esta Cantidad De Esta Cuenta.\nIntente Seleccionando Otra Cuenta.");
					}
					else{*/
						long CantidadTotalCuenta=0;
						rs2=mc.RestanteFactura(CodFactura);
						if(rs2.next()){
							/***SI LA FACTURA YA TIENE ALGUN MOVIMIENTO***/
							/*rs4=mc.ObtenerDetalleEstadoCuenta(cod_cuenta_fk);
							while(rs4.next()){
								UltimoPago=rs4.getInt(1)+UltimoPago;
							}*/
							//rs4.getStatement().getConnection().close();
							CantidadInicial=rs2.getString(1);
							mc.InsertarEstadoCuenta(cod_cuenta_fk, cantidad, fecha, hora,tipo);
							rs9=mc.BuscarEstadoCuentaFechaHora(fecha, hora);
							if(rs9.next()){
								CodEstadoCuenta=rs9.getString(1);
							}
							rs9.getStatement().getConnection().close();
							restante=Long.parseLong(CantidadInicial)-Long.parseLong(cantidad);
							/**************************************************************/						
							mc.InsertarDetalleFactura(CodFactura, cantidad, fechaPA, tipo, restante+"", NumSoporte, Observacion, fecha+"", hora+"", usuario_insercion);
							rs1=mc.BuscarDetalleFacturaFechaHora(fecha+"", hora+"",CodFactura);
							if(rs1.next()){
								CodDetalleFactura=rs1.getString(1);
							}
							rs1.getStatement().getConnection().close();
							
							mc.InsertarDetalleFacturaEstadoCuenta(CodEstadoCuenta, CodDetalleFactura);
							CantidadTotalCuenta=CantidadDisponibleEnCuenta-Integer.parseInt(cantidad);
							mc.ActualizarEstadoGeneralCuentaMenos(cod_cuenta_fk, CantidadTotalCuenta+"");
							
							/*rs3=mc.ObtenerCuentaEstadoGeneral(cod_cuenta_fk,tipo);
							if(rs3.next()){
								mc.ActualizarEstadoGeneralCuenta(cod_cuenta_fk, tipo, CantidadTotalCuenta);
							}else{
								mc.InsertarEstadoGeneralCuenta(cod_cuenta_fk, cantidad, tipo);
							}
							rs3.getStatement().getConnection().close();*/
							/****************************se verifica si la factura ya esta pagada en su totalidad*****/
							long PrecioFactura =0;
							rs5=mc.CargarCuentaFactura(CodFactura);
							if(rs5.next()){
								PrecioFactura = rs5.getLong(6);
							}
							rs5.getStatement().getConnection().close();
							
							long Pagado=0;
							rs6=mc.ObtenerDetalleFactura(CodFactura);
							while(rs6.next()){
								Pagado=rs6.getLong(1)+Pagado;
							}
							if(PrecioFactura == Pagado){
								/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
								mc.CerrarFactura(CodFactura,"1");
								
							}else{
								mc.CerrarFactura(CodFactura,"0");
							}
							rs6.getStatement().getConnection().close();
							/*******************************************************************************************/
						}
						else{
							/***SI LA FACTURA NO TIENE ALGUN MOVIMIENTO***/
							rs3=mc.CargarCuentaFactura(CodFactura);
							if(rs3.next()){
								CantidadInicial=rs3.getString(6);
							}
							rs3.getStatement().getConnection().close();
							mc.InsertarEstadoCuenta(cod_cuenta_fk, cantidad, fecha, hora,tipo);
							rs5=mc.BuscarEstadoCuentaFechaHora(fecha, hora);
							if(rs5.next()){
								CodEstadoCuenta=rs5.getString(1);
							}
							rs5.getStatement().getConnection().close();
							restante=Long.parseLong(CantidadInicial)-Long.parseLong(cantidad);
							/**************************************************************/						
							mc.InsertarDetalleFactura(CodFactura, cantidad, fechaPA, tipo, restante+"", NumSoporte, Observacion, fecha+"", hora+"", usuario_insercion);
							rs1=mc.BuscarDetalleFacturaFechaHora(fecha+"", hora+"",CodFactura);
							if(rs1.next()){
								CodDetalleFactura=rs1.getString(1);
							}						
							rs1.getStatement().getConnection().close();

							mc.InsertarDetalleFacturaEstadoCuenta(CodEstadoCuenta, CodDetalleFactura);
							CantidadTotalCuenta=CantidadDisponibleEnCuenta-Long.parseLong(cantidad);
							mc.ActualizarEstadoGeneralCuentaMenos(cod_cuenta_fk, CantidadTotalCuenta+"");
							
							/*rs4=mc.ObtenerCuentaEstadoGeneral(cod_cuenta_fk,tipo);
							if(rs4.next()){
								
							}else{
								mc.InsertarEstadoGeneralCuenta(cod_cuenta_fk, cantidad, tipo);
							}
							rs4.getStatement().getConnection().close();*/
							
							/****************************se verifica si la factura ya esta pagada en su totalidad*****/
							long PrecioFactura =0;
							rs5=mc.CargarCuentaFactura(CodFactura);
							if(rs5.next()){
								PrecioFactura = rs5.getLong(6);
							}
							rs5.getStatement().getConnection().close();
							
							long Pagado=0;
							rs6=mc.ObtenerDetalleFactura(CodFactura);
							while(rs6.next()){
								Pagado=rs6.getLong(1)+Pagado;
							}
							if(PrecioFactura == Pagado){
								/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
								mc.CerrarFactura(CodFactura,"1");
								
							}else{
								mc.CerrarFactura(CodFactura,"0");
							}
							rs6.getStatement().getConnection().close();
							/*******************************************************************************************/
						}
						rs2.getStatement().getConnection().close();
					//}//cierra el else de la validacion del monto
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		
	}
	
	/********************************************************************************/
	public String FormatoMoneda(String valor){		
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
	/********************************************************************************/

	///CONVERTIR DE NUMERO A LETRAS
	
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
		

	    /////FIN CONVERTIR DE NUMERO A LETRAS 
	
}
