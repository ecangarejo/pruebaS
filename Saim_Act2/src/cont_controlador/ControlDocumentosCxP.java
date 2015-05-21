package cont_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.*;

import cont_metodo.MetodoDocumentosCxP;

/**
 * Servlet implementation class ControlDocumentos
 */
public class ControlDocumentosCxP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		PrintWriter out=res.getWriter();
		MetodoDocumentosCxP mdcxp = new MetodoDocumentosCxP();
		
		///////////////Variables/////////////////////
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs12=null;
		String va = req.getParameter("valor");
		String texto=req.getParameter("texto");
		String Ndatos=req.getParameter("Ndatos");
		String docu=req.getParameter("docu");
		String li=req.getParameter("ls");
		String vdebito=req.getParameter("vdebito");
		String vcredito=req.getParameter("vcredito");
		String pc=req.getParameter("pc");
		String ac=req.getParameter("ac");
		String p=req.getParameter("p");
		String b=req.getParameter("b");
		String v=req.getParameter("v");
		String pd=req.getParameter("pd");
		String su=req.getParameter("su");
		String cos=req.getParameter("cos");
		String td=req.getParameter("td");
		String nd=req.getParameter("nd");
		String fd=req.getParameter("fd");
		String dd=req.getParameter("dd");
		String ter=req.getParameter("ter");
		String tern=req.getParameter("tern");
		String sd=req.getParameter("sd");
		String sdn=req.getParameter("sdn");
		String ccd=req.getParameter("ccd");
		String ccdn=req.getParameter("ccdn");
		String scd=req.getParameter("scd");
		String scdn=req.getParameter("scdn");
		String bd=req.getParameter("bd");
		String cc=req.getParameter("cc");
		String id=req.getParameter("id");
		String ls=req.getParameter("l");
		String TipoAfectacion=req.getParameter("TipoAfectacion");
		String Parametro=req.getParameter("Parametro");
		int l=0;
		if(ls==null){
			ls="0";	
		}else{
		  l=Integer.parseInt(ls);
		}
		
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
		
		if(va.equals("1")){	//Selecciona el Periodo
			out.print("<table width='100%'  border='0' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Seleccione Periodo</div></td></tr>");
			//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisión</div></i></td></tr>");  //
			out.print("<table width='100%' border='0' class='style6' ><tr><td><div align='center'>Mes: <input name='MC' type='text' id='MC' size='1' onKeyup='periodocontable(this,patronp,true,0)' />  A&ntilde;o: <input name='AC' type='text' id='AC' size='2' onKeyup='periodocontable(this,patronp,true,1)'  /></div></td></tr>"); 
		}//fin equals 1
		
		if(va.equals("2")){ //Verifica que el periodo exista o este habilitado
			rs = mdcxp.ConsultarPeriodo(pc, ac);
			String sw="00";
			try {
				if(rs.next()){
					sw=rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3);
				}	
				rs.getStatement().getConnection().close();
			out.print(sw);
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}//fin equals 2
		
		if(va.equals("3")){
			//out.print("<table width='100%'  border='1' class='style6'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Periodo</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3'><td  colspan='2' ><div align='center'>Periodo: "+pc+" - "+ac+"<input type='hidden' id='txtac' value="+ac+" /><input type='hidden' id='txtpc' value="+pc+" /></div></td></tr>"); 
			
			if(b.equals("1")){
				//periodo bloqueado 
				out.print("<tr><td  width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(2,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Crear Documento</label></div></td>");
				out.print("<td width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Consultar Documento</label></div></td></tr>");
			}else{
				//opciones de periodo sin bloquear
				out.print("<tr><td  width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(0,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Crear Documento CxP</label></div></td>");
				out.print("<td width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Consultar Documento CxP</label></div></td></tr>");
			}
			out.print("<tr><td colspan='2'><div id='ContenidoCxP'></div></td></tr></table>");
		}//fin equals 3
		
		if(va.equals("cbancos")){			
			try {
				int ContCB=0;
				String CodBancoCB="";
				rs=mdcxp.ConsultarBancos(Parametro);
				out.print("<table>");
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='AsignarBanco("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td></tr>");
					ContCB=ContCB+1;
					CodBancoCB=rs.getString(1);
				}
				//if(ContCB==1){
				out.print("<tr><td><input id='txtContaCB' value="+ContCB+" type='hidden' ></td>" +
						"<input id='txtCodBancoCB' value="+CodBancoCB+" type='hidden' ></td>" +
						"</tr></table>");
				/*}else{
					out.print("</table>");
				}*/
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("4")){
			//out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3'><td   colspan='2'><div align='center'>Periodo: "+pc+" - "+ac+"</div></td></tr>"); 
			/*if(b.equals("1")){//Periodo Bloqueado
				out.print("<tr><td  width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(2,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Crear Documento</label></div></td>");
				out.print("<td width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Consultar Documento</label></div></td></tr></table>");
				//out.print("<table width='100%' border='0' class='style6'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Consultar Documento</div></td></tr>");
				///AQUI DEBERIA PEDIR EL NUMERO DE DOCUMENTO Y CONSULTAR DOCUMENTOS
			}else{//Periodo desbloqueado
				out.print("<tr><td  width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='10' onclick='RadioDoc(0,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Crear Documento</label></div></td>");
				out.print("<td width='50%'><div align='lefth'><label><input name='radiobutton' type='radio' value='radiobutton' id='12' onclick='RadioDoc(1,&quot;"+pc+"&quot;,"+ac+","+p+","+b+")' />Consultar Documento</label></div></td></tr></table>");
				*/
				if(v.equals("0")){//Para crear documento
					try {
						out.println("<table border='0' width='100%' id='TablaCxP' ><tr><td colspan='7' class='style12'><div id='cabecera2' class='style11' align='center'><p>DOCUMENTOS CxP </p></div></td></tr>");
						out.println("<tr><td width='10%' class='style12'>Tipo Documento </td><td><select name='cmbCodTipoDocumento' id='cmbCodTipoDocumento' onChange='TipoDoc(0)' ><option value='Seleccione'>Seleccione</option>");
						rs = mdcxp.ConsultarTiposDocumentoscCxP();
						while(rs.next()){
							out.println("<option value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
						}	
						rs.getStatement().getConnection().close();
						out.println("</select> <select name='cmbNomTipoDocumento' id='cmbNomTipoDocumento' onChange='TipoDoc(1)' ><option value='Seleccione'>Seleccione</option>");
						rs = mdcxp.ConsultarTiposDocumentoscCxP();
						while(rs.next()){
							out.println("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
						out.println("</select></td>" +
								"<td class='style12'>Fecha (dd/mm/aaaa)</td><td><span class='style12'><input name='fd' type='text' id='fd' size='10' onKeyup='masca(this,patron,true,0,0,0,&quot;"+pc+"&quot;,"+ac+")' ></span></td>" +
								"<td class='style12'>Numero Documento </td><td width='26%' class='style12'><input name='txtNumeroDocumentoCxP' type='text' id='txtNumeroDocumentoCxP' readonly='' disabled=true ></td>" +
								"<td width='6%'>&nbsp;</td></tr>");
						
						out.println("<tr><td class='style12'>Detalle</td><td width='26%'><span class='style12'><textarea name='txtDetalle' cols='35' rows='3' id='txtDetalle'></textarea></span></td>" +
								"<td class='style12'>Factura Proveedor </td><td><input name='txtFacturaProveedor' type='text' id='txtFacturaProveedor'></td>" +
								"<td class='style12' ></td><td></td>" +		
						"<td>&nbsp;</td></tr>");
					
						out.println("<tr><td class='style12' >Proveedor</td><td class='style12'><input type='text' name='txtTercero' id='txtTercero' onkeyup='AutocompletarTercero()' ><div id='NombreTercero'></div><input type='hidden' id='txtCodTercero' ></td><td class='style12' >Tipo Afectacion </td><td ><input type='text' name='txtTipoAfectacion' id='txtTipoAfectacion'  onkeyup='AutocompletaIngDistContable()'></td><td class='style12' >Dias Plazo</td><td ><input type='text' name='txtDiasPlazo' id='txtDiasPlazo'></td></tr>");
						out.println("<tr><td class='style12' > </td><td ><div id='Tercero'></div></td> <td class='style12' ></td><td ><div id='DisContable'></div></td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td></tr>");
						//out.println("<tr><td class='style12' >Factura Proveedor </td><td ><input type='text' name='txtFacturaProveedor' id='txtFacturaProveedor'></td>" +
						out.println("<td class='style12' >Fecha recibo (dd/mm/aaaa) </td><td ><label for='textfield'></label><input type='text' name='txtFechaRecibo' id='txtFechaRecibo' onKeyUp='mascaNormal(this,&quot;/&quot;,patron,true)'  ></td>" +								
								"<td ></td></tr>");
						
						out.println("<tr><td colspan='7' ><div id='CuandoEgreso'><input type='hidden' id='txtTipInser' value='0' ></div></td></tr></table>");
						
						out.println("<table border='0' width='100%'><tr><td align='center' ><input name='btnIngresarcxp' type='button' id='btnIngresarcxp' value='Ingresar' onclick='GuardarCxP()' ></td></tr></table>");
						
						out.println("<table border='0' width='100%'><tr><td align='center' ><div id='DetalleCxP'></div></td></tr></table>");
						out.println("<table border='0' width='100%'><tr><td align='center' ><div id='ResultadoDetalleCxP'></div></td></tr></table>");
						//out.println("<tr><td class='style12' >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td> <td >&nbsp;</td><td >&nbsp;</td></tr>");
						//out.println("</table>");
						
						/*out.println("<tr><td width='9%' class='style12'>Banco</td><td width='13%' class='style12'><input name='txtBanco' type='text' id='txtBanco' onkeyup='AutoCompletarBancos()'></td>" +
								"<td width='10%' class='style12'>No Cheque </td><td class='style12'><input name='txtCheque' type='text' id='txtCheque'></td> " +
								"<td class='style12' >Indicador Pago </td><td ><input name='txtIndicadorPago' type='text' id='txtIndicadorPago'></td></tr>");*/
						
						out.println("");
						
						
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
			}//if v=0 	
				
			
			if(v.equals("1")){//Para CONSULTAR  documento
				out.print("<table width='100%' border='0' class='style6'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Consultar Documento</div></td></tr>");
				out.print("<tr><td width='10%'>Numero de Documento</td><td><input type=text id='numdc' size='45' ></td></tr>");
				out.print("<tr><td width='10%'>Numero de Factura</td><td><input type=text id='numfact' size='45' ></td></tr>"); 
				out.print("<tr><td width='10%'>Anno</td><td><input type=text id='anodc' size='45' ></td></tr>"); 
				out.print("<tr><td width='10%'>Periodo</td><td><input type=text id='perdc' size='45'  ></td></tr>"); 
				out.print("<tr><td width='10%'><input type='button' value='  Consultar  ' onClick='ConsultarDocCxP()' ></td></tr>");
				out.print("<tr><td><div id='consul' ></div></td></tr></table>");
			}//if v=0 
			
			//out.print("<tr><td><div id='Cabecera' ></div></td></tr>");
			out.print("<tr><td><div id='Detalle' ></div></td></tr></table>");
			out.print("<tr><td><div id='Cabecera' ></div></td></tr></table>");
			
			//out.print("<table width='100%' border='0' class='style6' ><tr><td><div align='center'>sdfdfsvtha<input name='MC' type='text' id='MC' size='1' onKeyup='periodocontable(this,patronp,true,0)' />  A&ntilde;o: <input name='AC' type='text' id='AC' size='2' onKeyup='periodocontable(this,patronp,true,1)'  /></div></td></tr>"); 
		

		}//fin equals 4
		
		String TipoInsercion=req.getParameter("TipoInsercion");
		String TipoDocumento=req.getParameter("TipoDocumento");
		String FechaMovimientoCxP=req.getParameter("FechaMovimientoCxP");
		String NumeroDocumentoCxP=req.getParameter("NumeroDocumentoCxP");	
		String DetalleCxP=req.getParameter("DetalleCxP");
		String NumeroFacturaProveedor=req.getParameter("NumeroFacturaProveedor");
		String CodTercero=req.getParameter("CodTercero");
		String TipoAfectacionCxP=req.getParameter("TipoAfectacionCxP");
		String DiasPlazo=req.getParameter("DiasPlazo");
		String FechaRecibo=req.getParameter("FechaRecibo");
		String pec=req.getParameter("pec");
		String anc=req.getParameter("anc");
		String Usuario=req.getParameter("Usuario");
		String cod_banco_fk=req.getParameter("cod_banco_fk");
		String cod_hcheque_fk=req.getParameter("cod_hcheque_fk");
		String indicador_pago=req.getParameter("indicador_pago");
		java.util.Date fechaActual = new java.util.Date();
		java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());
		java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
		
		if(va.equals("grcxp")){
			try{
				String CodDocumentoCxP="";
				if(TipoInsercion.equals("0")){
					cod_banco_fk="0";
					cod_hcheque_fk="0";
					indicador_pago="0";
					String numero_cheque="0";
					mdcxp.CrearDocumentoCxP(TipoDocumento, FechaMovimientoCxP, NumeroDocumentoCxP, 
							DetalleCxP, NumeroFacturaProveedor, CodTercero, TipoAfectacionCxP, 
							DiasPlazo, FechaRecibo, pec, anc,Fecha,Hora,Usuario,
							cod_banco_fk,cod_hcheque_fk,indicador_pago,numero_cheque);
					int cta=0;
					rs = mdcxp.ConsecutivosdeCuentas(TipoDocumento);
					try {
						if(rs.next()){
							cta=rs.getInt(2);
						}
						rs.getStatement().getConnection().close();
						
						rs1=mdcxp.ObtenerCodigoDocumentoCxP(NumeroDocumentoCxP);
						if(rs1.next()){
							CodDocumentoCxP=rs1.getString(1);
							int ctan=cta+1;
							mdcxp.ActualizarConsecutivo(ctan+"",TipoDocumento);
						}
						rs1.getStatement().getConnection().close();
						
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					rs12=mdcxp.ObtenerDocumentosEA(NumeroDocumentoCxP, TipoDocumento);
					if(rs12.next()){
						//out.print("Ingreso Exitoso");						
						out.print(" <table width='100%' border='1'>");
						out.print(" <tr>");
						out.print(" <td width='1%'>Suc</td>");
						out.print(" <td width='5%'>c.costo</td>");
						out.print(" <td width='5%'>S.costo</td>");
						out.print(" <td width='4%'>I.cont</td>");
						out.print(" <td width='7%'>Neto</td>");
						out.print(" <td width='5%'>%.desc</td>");
						out.print(" <td width='5%'>V.desc</td>");
						out.print(" <td width='8%'>Stot</td>");
						out.print(" <td width='4%'>%.iva</td>");
						out.print(" <td width='4%'>V.iva</td>");
						out.print(" <td width='8%'>V.Fact</td>");
						out.print(" <td width='4%'>%.retf</td>");
						out.print(" <td width='4%'>V.retf</td>");
						out.print(" <td width='5%'>%.retiva</td>");
						out.print(" <td width='5%'>V.retiva</td>");
						out.print(" <td width='3%'>%ica</td>");
						out.print(" <td width='4%'>V.ica</td>");
						out.print(" <td width='8%'>ValorP</td>");
						out.print(" <td width='4%'><input type='hidden' id='txtCodCxP' value="+CodDocumentoCxP+"  /></td>");
						out.print(" </tr>");
						out.print(" <tr><div id='CamposCxP'>");
						out.print(" <td><select style='width:45px' name='sucuc'  id='sucuc' onchange='VerCentroC()'  style='width:100%' >");//combo sucursal
						String CodCCosto="";
						String CCosto="";
						String CodSuc="";
						String Suc="";
						String CodSCosto="";
						String SCosto="";
						
						try {
							rs2=mdcxp.ObtenerDatosTipoAfectacion(TipoAfectacionCxP);
							if(rs2.next()){
								CodSuc=rs2.getString(4);
								Suc=rs2.getString(5);
								CodCCosto=rs2.getString(6);
								CCosto=rs2.getString(7);	
								CodSCosto=rs2.getString(8);
								SCosto=rs2.getString(9);
								out.print("<option title='"+Suc+"' value="+CodSuc+">"+CodSuc+"</option>");
								rs = mdcxp.Sucursales();
								while(rs.next()){
									out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
								}
								rs.getStatement().getConnection().close();
								
								out.print("</select></td>");
								/******************************************************************************************/
								out.print(" <td><div id='CcostoD'>");
								rs=mdcxp.CentrosdeCostoSolo();
								try {
									out.print("<select style='width:45px' name='txtCCostoD'  id='txtCCostoD' onchange='VerSCentroc()'  style='width:100%' >" +
											"<option title="+CCosto+" value="+CodCCosto+" selected=''>"+CodCCosto+"</option> ");
									while(rs.next()){
										out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
									}
									out.print("</select>");
									rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									out.print("Error "+e);
									e.printStackTrace();
								}
								//out.print("<input type='text' name='txtCCostoD'  id='txtCCostoD'  style='width:100%' />");
								out.print("</div></td>");//combo centrocosto
								
								out.print(" <td><div id='ScostoD'>");
								//*
								rs=mdcxp.SubCentrosdeCostoSolo();
								try {
									out.print("<select style='width:45px' name='txtSCostoD'  id='txtSCostoD'  style='width:100%' >" +
											"<option title='"+SCosto+"' value="+CodSCosto+" selected=''>"+CodSCosto+"</option> ");
									while(rs.next()){
										out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
									}
									out.print("</select>");
									rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									out.print("Error "+e);
									e.printStackTrace();
								}
								//out.print("<input type='text' name='txtSCostoD'  id='txtSCostoD'  style='width:100%' />");
								out.print("</div></td>");//combo subcentrocosto

							}else{
								rs = mdcxp.Sucursales();
								out.print("<option value='--' selected=''>--</option> ");
								while(rs.next()){
									out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
								}
								rs.getStatement().getConnection().close();
								
								out.print("</select></td>");
								out.print(" <td><div id='CcostoD'><input type='text' name='txtCCostoD'  id='txtCCostoD'  style='width:100%' /></div></td>");//combo centrocosto
								out.print(" <td><div id='ScostoD'><input type='text' name='txtSCostoD'  id='txtSCostoD'  style='width:100%' /></div></td>");//combo subcentrocosto

							}
							rs2.getStatement().getConnection().close();
							
							
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						
						out.print(" <td><select name='txtIngConD'  id='txtIngConD' style='width:100%' > <option value='--' selected=''>--</option>");
						
						
						try {
							rs = mdcxp.InterfasContable();
							while(rs.next()){
								out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</select></td>");
						/****hacer la consulta que me traiga los datos del tercero***/
						//CodTercero
						rs1=mdcxp.DatosTerceros(CodTercero);
						String autoretenedor="";
						String porc_retiva="";
						String porc_retica="";
						if(rs1.next()){
							autoretenedor=rs1.getString(10);
							porc_retiva=rs1.getString(23);
							porc_retica=rs1.getString(24);
						}
						rs1.getStatement().getConnection().close();
						out.print(" <td><input type='text' name='txtNetoD'    id='txtNetoD'    style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtPorDesD'  id='txtPorDesD' onblur='DescuentoP()'  style='width:100%' maxlength='3' /></td>");
						out.print(" <td><input type='text' name='txtValDesD'  id='txtValDesD' onblur='DescuentoV()'  style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtSubTotD'  id='txtSubTotD'  style='width:100%' readonly='' /></td>");
						if(!porc_retiva.equals("")){
							out.print(" <td><input type='text' name='txtPorIvaD'  id='txtPorIvaD' onblur='IvaP()' value='"+porc_retiva+"'  style='width:100%' maxlength='3' /></td>");
						}else{
							out.print(" <td><input type='text' name='txtPorIvaD'  id='txtPorIvaD' onblur='IvaP()'  style='width:100%' maxlength='3' /></td>");
						}
						out.print(" <td><input type='text' name='txtValIvaD'  id='txtValIvaD' onblur='IvaV()'  style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtValFacD'  id='txtValFacD'  style='width:100%' readonly='' /></td>");
						if(autoretenedor.equals("Si")){
							out.print(" <td><input type='text' name='txtPorRetfD' id='txtPorRetfD' disabled='' onblur='RetefuenteP()' style='width:100%' maxlength='3' /></td>");
							out.print(" <td><input type='text' name='txtValRetfD' id='txtValRetfD' disabled='' onblur='RetefuenteV()' style='width:100%' /></td>");
						}
						if(autoretenedor.equals("No")){
							out.print(" <td><input type='text' name='txtPorRetfD' id='txtPorRetfD' onblur='RetefuenteP()' style='width:100%' maxlength=3' /></td>");
							out.print(" <td><input type='text' name='txtValRetfD' id='txtValRetfD' onblur='RetefuenteV()' style='width:100%' /></td>");
						}
						out.print(" <td><input type='text' name='txtPorreIvD' id='txtPorreIvD' onblur='ReteivaP()' style='width:100%' maxlength='2' /></td>");
						out.print(" <td><input type='text' name='txtValReIvD' id='txtValReIvD' onblur='ReteivaV()' style='width:100%' /></td>");
						if(!porc_retica.equals("")){
							out.print(" <td><input type='text' name='txtPorcIcaD' id='txtPorcIcaD' onblur='IcaP()' value='"+porc_retica+"' style='width:100%' maxlength='3' /></td>");
						}else{
							out.print(" <td><input type='text' name='txtPorcIcaD' id='txtPorcIcaD' onblur='IcaP()' style='width:100%' maxlength='3' /></td>");
						}
						out.print(" <td><input type='text' name='txtValIcaD'  id='txtValIcaD'  onblur='IcaV()' style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtValPagD'  id='txtValPagD'  style='width:100%' readonly='' /></td>");
						out.print(" <td><input type='hidden' name='txtSalPagaD' id='txtSalPagaD' style='width:100%' readonly='' /></td>");
						out.print(" <td><a href='#' onclick='GuardarDetalleCxP()'>Guardar</a></td>");
						out.print(" </div></tr>");
						out.print("<tr><td colspan='20'>DETALLE DEL DOCUMENTO</td></tr>");
						out.print("<tr><td colspan='20'><div id='DetalleDocCxP'>---------------</div></td></tr>");
						out.print("</table>");

					}else{
						out.print("Error en la Insercion del Documentos.\nIntente Nuevamente.");
					}
					rs12.getStatement().getConnection().close();
				
				
				}
				if(TipoInsercion.equals("1")){
					try {
						//cambiar el insert del encabezado del documento 
						String CodigoChequera=req.getParameter("CodigoChequera");
						String numero_cheque=req.getParameter("NumeroCheque");

						NumeroFacturaProveedor="0";
						CodTercero="0";
						DiasPlazo="0";
						FechaRecibo=null;
						if(indicador_pago.equals("1")){
							numero_cheque=cod_hcheque_fk;
							CodigoChequera="0";
						}
						mdcxp.CrearDocumentoCxP(TipoDocumento, FechaMovimientoCxP, NumeroDocumentoCxP, 
								DetalleCxP, NumeroFacturaProveedor, CodTercero, TipoAfectacionCxP, 
								DiasPlazo, FechaRecibo, pec, anc,Fecha,Hora,Usuario,
								cod_banco_fk,CodigoChequera,indicador_pago,numero_cheque);
						
						if(indicador_pago.equals("2")){
							int co=0;
							co=Integer.parseInt(numero_cheque)+1;
							mdcxp.ActualizarConsecutivoChequera(co+"", CodigoChequera);
						}
						
						int cta=0;
						rs = mdcxp.ConsecutivosdeCuentas(TipoDocumento);
						if(rs.next()){
							cta=rs.getInt(2);
						}
						rs.getStatement().getConnection().close();
						
						rs1=mdcxp.ObtenerCodigoDocumentoCxP(NumeroDocumentoCxP);
						if(rs1.next()){
							CodDocumentoCxP=rs1.getString(1);
							int ctan=cta+1;
							mdcxp.ActualizarConsecutivo(ctan+"",TipoDocumento);
						}
						rs1.getStatement().getConnection().close();
						
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}	
					
					
					rs12=mdcxp.ObtenerDocumentosEA(NumeroDocumentoCxP, TipoDocumento);
					if(rs12.next()){
						out.print(" <table width='50%' border='1' align='left'>");
						out.print(" <tr>");
						out.print(" <td>Seleccione Documento</td>");
						out.print(" <td><select id='cmbDocumentoAE' ><option value='Seleccione' selected='' >Seleccione</option><option value='110'>COMPRAS</option><option value='120'>FACTURA MEDICO</option><option value='130'>NOTA CREDITO</option></select></td>");
						out.print(" <td><input id='txtNumeroDocumentoAE' onkeyup='AutoCompletaDocumentos()'></td><td><div id='AutoDocumentos'></div></td>");
						out.print(" </tr>");
						out.print(" </table>");					
						out.print(" <table width='100%' border='1'>");
						out.print(" <tr>");
						out.print(" <td >Suc</td>");
						out.print(" <td >c.costo</td>");
						out.print(" <td >S.costo</td>");
						out.print(" <td >I.cont</td>");
						out.print(" <td >V.Fact</td>");
						out.print(" <td >V.desc</td>");
						out.print(" <td >V.retf</td>");
						out.print(" <td >V.retiva</td>");
						out.print(" <td >V.ica</td>");
						out.print(" <td >ValorP</td>");					
						out.print(" <td ><input type='hidden' id='txtCodCxP' value="+CodDocumentoCxP+"  /></td>");
						out.print(" </tr>");
						out.print(" <tr><div id='CamposCxP'>");
						out.print(" <td><select style='width:45px' name='sucuc'  id='sucuc' onchange='VerCentroC()'  style='width:100%' ><option value='--' selected=''>--</option> ");//combo sucursal
						rs = mdcxp.Sucursales();
						try {
							while(rs.next()){
								out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</select></td>");
						out.print(" <td><div id='CcostoD'><input type='text' name='txtCCostoD'  id='txtCCostoD'  style='width:100%' /></div></td>");//combo centrocosto
						out.print(" <td><div id='ScostoD'><input type='text' name='txtSCostoD'  id='txtSCostoD'  style='width:100%' /></div></td>");//combo subcentrocosto
						out.print(" <td><select name='txtIngConD'  id='txtIngConD' style='width:100%' > <option value='--' selected=''>--</option>");
						
						try {
							rs = mdcxp.InterfasContable();
							while(rs.next()){
								out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print("</select></td>");
						out.print(" <td><input type='text' name='txtValFacD'  id='txtValFacD'  onkeyup='TotalSaldoEA()' style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtValDesD'  id='txtValDesD'  onkeyup='TotalSaldoEA()' style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtValRetfD' id='txtValRetfD' onkeyup='TotalSaldoEA()' style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtValReIvD' id='txtValReIvD' onkeyup='TotalSaldoEA()' style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtValIcaD'  id='txtValIcaD'  onkeyup='TotalSaldoEA()' style='width:100%' /></td>");
						out.print(" <td><input type='text' name='txtValPagD'  id='txtValPagD'  style='width:100%' readonly='' /></td>");
						out.print(" <td><input type='hidden' name='txtSalPagaD' id='txtSalPagaD' style='width:100%' readonly='' /></td>");
						out.print(" <td><a href='#' onclick='GuardarDetalleCxPEA()'>Guardar</a></td>");
						out.print(" </div></tr>");
						out.print("<tr><td colspan='20'>DETALLE DEL DOCUMENTO</td></tr>");
						out.print("<tr><td colspan='20'><div id='DetalleDocCxP'>---------------</div></td></tr>");
						out.print("</table>");	
						
					}else{	
						out.print("Error en la Insercion del Documentos.\nIntente Nuevamente.");
					}
					rs12.getStatement().getConnection().close();						
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		/*********************************************************************************/
		if(va.equals("grcxpANT")){
			try{
				String CodDocumentoCxP="";
				if(TipoInsercion.equals("0")){
					cod_banco_fk="0";
					cod_hcheque_fk="0";
					indicador_pago="0";
					String numero_cheque="0";
					NumeroFacturaProveedor="0";
					String ValorAnticipo=req.getParameter("ValorAnticipo");					
					mdcxp.CrearDocumentoCxP(TipoDocumento, FechaMovimientoCxP, NumeroDocumentoCxP, 
							DetalleCxP, NumeroFacturaProveedor, CodTercero, TipoAfectacionCxP, 
							DiasPlazo, FechaRecibo, pec, anc,Fecha,Hora,Usuario,
							cod_banco_fk,cod_hcheque_fk,indicador_pago,numero_cheque);
					int cta=0;
					rs = mdcxp.ConsecutivosdeCuentas(TipoDocumento);
					try {
						if(rs.next()){
							cta=rs.getInt(2);
						}
						rs.getStatement().getConnection().close();						
						rs1=mdcxp.ObtenerCodigoDocumentoCxP(NumeroDocumentoCxP);
						if(rs1.next()){
							CodDocumentoCxP=rs1.getString(1);
							int ctan=cta+1;
							mdcxp.ActualizarConsecutivo(ctan+"",TipoDocumento);
						}
						rs1.getStatement().getConnection().close();						
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}					
					rs12=mdcxp.ObtenerDocumentosEA(NumeroDocumentoCxP, TipoDocumento);
					if(rs12.next()){
 					mdcxp.CrearDetalleDocumentoCxPANT(CodDocumentoCxP, "0", "0", "0", "0", TipoDocumento, NumeroDocumentoCxP, "CXP", ValorAnticipo, ValorAnticipo);
					}else{
						out.print("Error en la Insercion del Documentos.\nIntente Nuevamente.");
					}
					rs12.getStatement().getConnection().close();			
				}

			}catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		/*********************************************************************************/
		
		
		String codSucursal=req.getParameter("codSucursal");/****************************************/
		if(va.equals("cco")){
			rs=mdcxp.CentrosdeCosto(codSucursal);
			try {
				out.print("<select style='width:45px' name='txtCCostoD'  id='txtCCostoD' onchange='VerSCentroc()'  style='width:100%' ><option value='--' selected=''>--</option> ");
				while(rs.next()){
					out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
				}
				out.print("</select>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("AuDoc")){
			String NumeroDocumentoEA=req.getParameter("NumeroDocumentoEA");
			String TipoDocumentoEA=req.getParameter("TipoDocumentoEA");
			//String CodTercero=req.getParameter("CodTercero");
			rs=mdcxp.ObtenerDocumentosEAT(NumeroDocumentoEA, TipoDocumentoEA,CodTercero);
			rs1=mdcxp.ObtenerDocumentosEAT(NumeroDocumentoEA, TipoDocumentoEA,CodTercero);
			try {
				if(rs1.next()){
					out.print("<table>");
					while(rs.next()){
						   out.print("<tr><td><a href='#' onclick='SeleccionarDocumentoEA(&quot;"+rs.getString(5)+"&quot;,&quot;"+FormatoMoneda(rs.getString(21))+"&quot;,&quot;"+rs.getString(21)+"&quot;)'>"+rs.getString(5)+"</a></td></tr>");
					}
					out.print("</table>");
					rs.getStatement().getConnection().close();

				}else{
					out.println("No Existe Resultado, para la busqueda.");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String codCCosto=req.getParameter("codCCosto");/****************************************/
		if(va.equals("scco")){
			rs=mdcxp.SubCentrosdeCosto(codCCosto);
			try {
				out.print("<select style='width:45px' name='txtSCostoD'  id='txtSCostoD'  style='width:100%' ><option value='--' selected=''>--</option> ");
				while(rs.next()){
					out.print("<option title='"+rs.getString(2)+"' value="+rs.getString(1)+">"+rs.getString(1)+"</option>");
				}
				out.print("</select>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("auch")){
			try {
				String NomChequera=req.getParameter("NomChequera");
				String CodBanco=req.getParameter("CodBanco");
				int con=0;
				String NomCheq="";
				String NumCheque="";
				String CodigoCheq="";
				rs=mdcxp.AutoCompletarChequera(NomChequera, CodBanco);
				out.print("<table>");
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='AsignarChequera(&quot;"+rs.getString(3)+"&quot;,"+rs.getString(5)+","+rs.getString(1)+")' >"+rs.getString(3)+"</a></td></tr>");
					con=con+1;
					NomCheq=rs.getString(3);
					NumCheque=rs.getString(5);
					CodigoCheq=rs.getString(1);
				}
				out.print("<tr><td><input type='hidden' id='txtContadorCHQ' value="+con+" ></td></tr>" +
						"<tr><td><input type='hidden' id='txtNomCheq' value="+NomCheq+" ></td></tr>" +
						"<tr><td><input type='hidden' id='txtCodigoCheq' value="+CodigoCheq+" ></td></tr>" +
						"<tr><td><input type='hidden' id='txtNumCheque' value="+NumCheque+"></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("adc")){
			try {
				rs=mdcxp.AutocompletaDistribucionContable(TipoAfectacion);
				out.print("<table>");
				int con=0;
				String CodDistCont="";
				String NomDisConta="";
				while(rs.next()){
					//System.out.print("<tr><td><a href='#' onclick='AsignarIngresoDisConta("+rs.getString(1)+")'>"+rs.getString(2)+"</a></td></tr>");
					out.print("<tr><td><a href='#' onclick='AsignarIngresoDisConta("+rs.getString(1)+")'>"+rs.getString(2)+"</a></td></tr>");
					con=con+1;
					CodDistCont=rs.getString(1);
					NomDisConta=rs.getString(2);
				}
				out.print("<tr><td><input type='hidden' id='txtContadorDC' value="+con+" /></td></tr>" +
						"<tr><td><input type='hidden' id='txtCodDC' value="+CodDistCont+" /></td></tr>" +
						"<tr><td><input type='hidden' id='txtNomDC' value='"+NomDisConta+"' /></td></tr>" +
						"<table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("9")){ ////Conseguir el consecutivo para armar el numero
			rs = mdcxp.ConsecutivosdeCuentas(cc);
			try {
				if(rs.next()){
					out.print(rs.getString(1)+"|"+rs.getString(2));
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}		
			//int aa=Integer.parseInt(annio)-2000;
			//out.print("|"+aa+"|"+mes);
		}//fin equals 9
		
		
		if(va.equals("OmDCxPEA")){
			try {
				String CodDocumentoCxP =req.getParameter("CodDocumentoCxP");
				String CodDetCxP =req.getParameter("CodDetCxP");
				String DocumentoEA=req.getParameter("DocumentoEA");
				String ValorOmitir=req.getParameter("ValorOmitir");
				int VDV=Integer.parseInt(ValorOmitir);
				rs1=mdcxp.ConsultarDetalleDocumentoCxpNumeroDocumentoT(DocumentoEA);
				while(rs1.next()){
					int SMQ=rs1.getInt(34);
					int VMQ=rs1.getInt(32);
					System.out.println("SMQ "+SMQ+" VMQ "+VMQ);
					if(VDV>SMQ){
						System.out.println("(VDV>SMQ)");
						int Nval=(VDV+SMQ);
						
						if(Nval>VMQ){
							int Nval1=(VDV+SMQ)-VMQ;
							System.out.println(" (Nval>VMQ) Nval1 "+Nval1);
						}
						if(Nval<VMQ){
							int Nval2=VMQ-(VDV+SMQ);
							System.out.println(" (Nval<VMQ) Nval2 "+Nval2);
						}
						if(Nval==VMQ){
							System.out.println(" (Nval==VMQ) Nval "+Nval);
						}
						
					}
					
					if(VDV<SMQ){
						System.out.println("(VDV<SMQ)");
					}
				}
				rs1.getStatement().getConnection().close();
				mdcxp.OmitirDetalleDocumentoCxP(CodDetCxP);
				rs=mdcxp.ConsultarDetalleDocumentoCxp(CodDocumentoCxP);
				out.print("<table border='1' width='100%' >");
				out.print(" <tr>");
				out.print(" <td >Suc</td>");
				out.print(" <td >c.costo</td>");
				out.print(" <td >S.costo</td>");
				out.print(" <td >I.cont</td>");
				out.print(" <td >Tipo Doc</td>");
				out.print(" <td >Num Doc</td>");
				out.print(" <td >V.Doc</td>");
				out.print(" <td >V.Desc</td>");
				out.print(" <td >V.Retf</td>");
				out.print(" <td >V.RetIva</td>");
				out.print(" <td >V.Ica</td>");
				out.print(" <td >Saldo</td>");
				out.print(" <td >Accion</td>");
				out.print("</tr>");
				while(rs.next()){
					
					out.print(" <tr>");
					out.print(" <td>"+rs.getString(3)+"</td>");
					out.print(" <td>"+rs.getString(4)+"</td>");
					out.print(" <td>"+rs.getString(5)+"</td>");
					out.print(" <td>"+rs.getString(6)+"</td>");
					out.print(" <td>"+rs.getString(7)+"</td>");
					out.print(" <td>"+rs.getString(8)+"</td>");
					out.print(" <td>"+rs.getString(10)+"</td>");
					out.print(" <td>"+rs.getString(21)+"</td>");
					out.print(" <td>"+rs.getString(27)+"</td>");
					out.print(" <td>"+rs.getString(29)+"</td>");
					out.print(" <td>"+rs.getString(31)+"</td>");
					out.print(" <td>"+rs.getString(34)+"</td>");
					
					out.print(" <td width='0%'><a href='#' onclick='EliminarDetallecxPEA("+rs.getString(1)+","+CodDocumentoCxP+")'>Omitir</a></td>");
					out.print("</tr>");
				}
				out.print("<tr><td colspan='19'><input type='button' value='Finalizar' onclick='FinalizarDocumentpCxP() '   /></td></tr>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("OmDCxP")){
			try {
				String CodDocumentoCxP =req.getParameter("CodDocumentoCxP");
				String CodDetCxP =req.getParameter("CodDetCxP");
				
				mdcxp.OmitirDetalleDocumentoCxP(CodDetCxP);
				rs=mdcxp.ConsultarDetalleDocumentoCxp(CodDocumentoCxP);
				out.print("<table border='1' width='100%' >");
				out.print(" <tr>");
				out.print(" <td width='1%'>Suc</td>");
				out.print(" <td width='5%'>c.costo</td>");
				out.print(" <td width='5%'>S.costo</td>");
				out.print(" <td width='4%'>I.cont</td>");
				out.print(" <td width='7%'>Neto</td>");
				out.print(" <td width='5%'>%.desc</td>");
				out.print(" <td width='5%'>V.desc</td>");
				out.print(" <td width='8%'>Stot</td>");
				out.print(" <td width='4%'>%.iva</td>");
				out.print(" <td width='4%'>V.iva</td>");
				out.print(" <td width='8%'>V.Fact</td>");
				out.print(" <td width='4%'>%.retf</td>");
				out.print(" <td width='4%'>V.retf</td>");
				out.print(" <td width='5%'>%.retiva</td>");
				out.print(" <td width='5%'>V.retiva</td>");
				out.print(" <td width='3%'>%ica</td>");
				out.print(" <td width='4%'>V.ica</td>");
				out.print(" <td width='8%'>ValorP</td>");
				//out.print(" <td width='3%'>T.ant</td>");
				//out.print(" <td width='4%'>D.ant</td>");
				//out.print(" <td width='0%'>S.Pagar</td>");
				out.print("</tr>");
				while(rs.next()){
					
					out.print(" <tr>");
					out.print(" <td>"+rs.getString(3)+"</td>");
					out.print(" <td>"+rs.getString(4)+"</td>");
					out.print(" <td>"+rs.getString(5)+"</td>");
					out.print(" <td>"+rs.getString(6)+"</td>");
					out.print(" <td>"+rs.getString(19)+"</td>");
					out.print(" <td>"+rs.getString(20)+"</td>");
					out.print(" <td>"+rs.getString(21)+"</td>");
					out.print(" <td>"+rs.getString(22)+"</td>");
					out.print(" <td>"+rs.getString(23)+"</td>");
					out.print(" <td>"+rs.getString(24)+"</td>");
					out.print(" <td>"+rs.getString(25)+"</td>");
					out.print(" <td>"+rs.getString(26)+"</td>");
					out.print(" <td>"+rs.getString(27)+"</td>");
					out.print(" <td>"+rs.getString(28)+"</td>");
					out.print(" <td>"+rs.getString(29)+"</td>");
					out.print(" <td>"+rs.getString(30)+"</td>");
					out.print(" <td>"+rs.getString(31)+"</td>");
					out.print(" <td>"+rs.getString(32)+"</td>");
					//out.print(" <td width='3%'>T.ant</td>");
					//out.print(" <td width='4%'>D.ant</td>");
					//out.print(" <td>"+rs.getString(34)+"</td>");
					//out.print(" <td width='0%'> xxxx </td>");
					//out.print(" <td width='0%'>xxxx</td>");
					out.print(" <td width='0%'><a href='#' onclick='EliminarDetallecxP("+rs.getString(1)+","+CodDocumentoCxP+")'>Omitir</a></td>");
					out.print("</tr>");
				}
				out.print("<tr><td colspan='19'><input type='button' value='Finalizar' onclick='FinalizarDocumentpCxP() '   /></td></tr>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("GdcxpEA")){			
			try {
				String CodDocumentoCxP =req.getParameter("CodDocumentoCxP");
				String CodSucursal=req.getParameter("CodSucursal");
				String CodCCosto=req.getParameter("CodCCosto");
				String CodScosto=req.getParameter("CodScosto");
				String InterfasContable=req.getParameter("InterfasContable");
				String valor_documento="--";
				String origen="CXP";
				String ValorFactu=req.getParameter("ValorFactu");
				String ValDesc=req.getParameter("ValDesc");
				String ValRetFue=req.getParameter("ValRetFue");				
				String ValRetIva=req.getParameter("ValRetIva");
				String ValIca=req.getParameter("ValIca");
				String ValPagar=req.getParameter("ValPagar");
				String ValDocFCN=req.getParameter("ValDocFCN");
				/****************************************************/
				String tipo_referencia=req.getParameter("TipoDocumentoCFN");
				String documento_referencia=req.getParameter("NumeroDocumentoCFN");
				String tipo_anticipo="";
				String documento_anticipo="";
				String valor_anticipo="";
				String cmbNomTipoDocumento=req.getParameter("cmbNomTipoDocumento");
				
				/****************************************************/
				if((cmbNomTipoDocumento.equals("140"))||(cmbNomTipoDocumento.equals("150"))){
					//EGRESOS
					tipo_anticipo="0";
					documento_anticipo="0";
					valor_anticipo="0";
				}
				if(cmbNomTipoDocumento.equals("160")){
					//ANTICIPOS
					tipo_anticipo=tipo_referencia;
					documento_anticipo=documento_referencia;
					valor_anticipo=ValPagar;
				}
				
				mdcxp.CrearDetalleDocumentoCxPEA(CodDocumentoCxP, CodSucursal, CodCCosto, 
						CodScosto, InterfasContable,tipo_referencia,documento_referencia,
						ValorFactu, origen,ValDesc,
						ValRetFue,ValRetIva,ValIca,ValPagar);
				
				//int Dife=Integer.parseInt(ValPagar)-Integer.parseInt(ValDocFCN);
				rs1=mdcxp.ConsultarDetalleDocumentoCxpNumeroDocumento(documento_referencia);
				int co=0;
				int dif=0;
				int ValFCN=Integer.parseInt(ValDocFCN);
				int ValP=Integer.parseInt(ValPagar);
				while(rs1.next()){
					co=co+1;
					
					int detrs=rs1.getInt(34);
					//System.out.println("Contador "+co+" detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
					
					if(ValFCN>ValP){
						System.out.println("1.)Entro en ValFCN>detrs= Contador "+co+" detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
						ValFCN=ValFCN-ValP;
						if(detrs>ValP){
							System.out.println("1.1) VALORES detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
							detrs=detrs-ValP;
							System.out.println("1.1)ValFCN>detrs=detrs>ValP");
							ValP=0;
							mdcxp.ActualizarValorDocumento(rs1.getString(1), detrs+"");
						}
						if(detrs<ValP){
							System.out.println("1.2) VALORES detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
							ValP=ValP-detrs;
							System.out.println("1.2)ValFCN>detrs=detrs<ValP "+detrs);
							mdcxp.ActualizarValorDocumento(rs1.getString(1), 0+"");
						}
						
						if(detrs==ValP){
							System.out.println("1.3) VALORES detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
							detrs=ValP-detrs;
							System.out.println("1.3)ValFCN>detrs=detrs<ValP "+detrs);
							ValP=0;
							mdcxp.ActualizarValorDocumento(rs1.getString(1), detrs+"");
						}
						
					}
					if(ValFCN<ValP){
						//detrs=detrs-ValFCN;
						ValFCN=ValP-ValFCN;
						System.out.println("2.)Entro en ValFCN<detrs= Contador "+co+" detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
						if(detrs>ValP){
							System.out.println("2.1) VALORES detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
							detrs=detrs-ValP;
							System.out.println("2.1)ValFCN<detrs=detrs>ValP "+detrs);
							mdcxp.ActualizarValorDocumento(rs1.getString(1), detrs+"");
							ValP=0;
						}
						if(detrs<ValP){
							System.out.println("2.2) VALORES detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
							detrs=ValP-detrs;
							System.out.println("2.2)ValFCN<detrs=detrs<ValP "+detrs);
							mdcxp.ActualizarValorDocumento(rs1.getString(1), detrs+"");
						}
						if(detrs==ValP){
							System.out.println("2.3) VALORES detrs "+detrs+" ValFCN "+ValFCN+" ValP "+ValP);
							detrs=ValP-detrs;
							System.out.println("2.3)ValFCN<detrs=detrs<ValP "+detrs);
							mdcxp.ActualizarValorDocumento(rs1.getString(1), detrs+"");
							ValP=0;
						}
						//ValFCN=0;	
						
					}
					
					if(ValP==0){
						System.out.println("Entro en ValP=0 Contador "+co+" ValFCN "+ValFCN);
						break;
						
					}
					
					
				}
				rs1.getStatement().getConnection().close();
				
				
				rs=mdcxp.ConsultarDetalleDocumentoCxp(CodDocumentoCxP);
				out.print("<table border='1' width='100%' >");
				out.print(" <tr>");
				out.print(" <td >Suc</td>");
				out.print(" <td >c.costo</td>");
				out.print(" <td >S.costo</td>");
				out.print(" <td >I.cont</td>");
				out.print(" <td >Tipo Doc</td>");
				out.print(" <td >Num Doc</td>");
				out.print(" <td >V.Doc</td>");
				out.print(" <td >V.Desc</td>");
				out.print(" <td >V.Retf</td>");
				out.print(" <td >V.RetIva</td>");
				out.print(" <td >V.Ica</td>");
				out.print(" <td >Saldo</td>");
				out.print(" <td >Accion</td>");
				out.print("</tr>");
				while(rs.next()){
					
					out.print(" <tr>");
					out.print(" <td>"+rs.getString(3)+"</td>");
					out.print(" <td>"+rs.getString(4)+"</td>");
					out.print(" <td>"+rs.getString(5)+"</td>");
					out.print(" <td>"+rs.getString(6)+"</td>");
					out.print(" <td>"+rs.getString(7)+"</td>");
					out.print(" <td>"+rs.getString(8)+"</td>");
					out.print(" <td>"+rs.getString(10)+"</td>");
					out.print(" <td>"+rs.getString(21)+"</td>");
					out.print(" <td>"+rs.getString(27)+"</td>");
					out.print(" <td>"+rs.getString(29)+"</td>");
					out.print(" <td>"+rs.getString(31)+"</td>");
					out.print(" <td>"+rs.getString(34)+"</td>");
					
					out.print(" <td width='0%'><a href='#' onclick='EliminarDetallecxPEA("+rs.getString(1)+","+CodDocumentoCxP+",&quot;"+rs.getString(8)+"&quot;,&quot;"+rs.getString(34)+"&quot;)'>Omitir</a></td>");
					out.print("</tr>");
				}
				out.print("<tr><td colspan='19'><input type='button' value='Finalizar' onclick='FinalizarDocumentpCxP() '   /></td></tr>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.print("Ingreso Exitoso.");
		}
		
		
		
		
		if(va.equals("Gdcxp")){			
			try {
				String CodDocumentoCxP =req.getParameter("CodDocumentoCxP");
				String CodSucursal=req.getParameter("CodSucursal");
				String CodCCosto=req.getParameter("CodCCosto");
				String CodScosto=req.getParameter("CodScosto");
				String InterfasContable=req.getParameter("InterfasContable");
				String valor_documento="--";
				String origen="CXP";
				String Neto=req.getParameter("Neto");
				String PorDesc=req.getParameter("PorDesc");
				String ValDesc=req.getParameter("ValDesc");
				String Subtot=req.getParameter("Subtot");
				String PorIva=req.getParameter("PorIva");
				String ValIva=req.getParameter("ValIva");
				String ValorFactu=req.getParameter("ValorFactu");
				String PorRetFue=req.getParameter("PorRetFue");
				String ValRetFue=req.getParameter("ValRetFue");
				String PorRetIva=req.getParameter("PorRetIva");
				String ValRetIva=req.getParameter("ValRetIva");
				String PorIca=req.getParameter("PorIca");
				String ValIca=req.getParameter("ValIca");
				String ValPagar=req.getParameter("ValPagar");
				//String SalPagar=req.getParameter("SalPagar");
				String SalPagar=req.getParameter("ValPagar");
				
				mdcxp.CrearDetalleDocumentoCxP(CodDocumentoCxP, CodSucursal, CodCCosto, CodScosto, InterfasContable, valor_documento, origen, Neto, PorDesc, ValDesc, Subtot, PorIva, ValIva, ValorFactu, PorRetFue, ValRetFue, PorRetIva, ValRetIva, PorIca, ValIca, ValPagar, SalPagar);
				
				rs=mdcxp.ConsultarDetalleDocumentoCxp(CodDocumentoCxP);
				out.print("<table border='1' width='100%' >");
				out.print(" <tr>");
				out.print(" <td width='1%'>Suc</td>");
				out.print(" <td width='5%'>c.costo</td>");
				out.print(" <td width='5%'>S.costo</td>");
				out.print(" <td width='4%'>I.cont</td>");
				out.print(" <td width='7%'>Neto</td>");
				out.print(" <td width='5%'>%.desc</td>");
				out.print(" <td width='5%'>V.desc</td>");
				out.print(" <td width='8%'>Stot</td>");
				out.print(" <td width='4%'>%.iva</td>");
				out.print(" <td width='4%'>V.iva</td>");
				out.print(" <td width='8%'>V.Fact</td>");
				out.print(" <td width='4%'>%.retf</td>");
				out.print(" <td width='4%'>V.retf</td>");
				out.print(" <td width='5%'>%.retiva</td>");
				out.print(" <td width='5%'>V.retiva</td>");
				out.print(" <td width='3%'>%ica</td>");
				out.print(" <td width='4%'>V.ica</td>");
				out.print(" <td width='8%'>ValorP</td>");
				//out.print(" <td width='3%'>T.ant</td>");
				//out.print(" <td width='4%'>D.ant</td>");
				//out.print(" <td width='0%'>S.Pagar</td>");
				out.print("</tr>");
				while(rs.next()){
					
					out.print(" <tr>");
					out.print(" <td>"+rs.getString(3)+"</td>");
					out.print(" <td>"+rs.getString(4)+"</td>");
					out.print(" <td>"+rs.getString(5)+"</td>");
					out.print(" <td>"+rs.getString(6)+"</td>");
					out.print(" <td>"+rs.getString(19)+"</td>");
					out.print(" <td>"+rs.getString(20)+"</td>");
					out.print(" <td>"+rs.getString(21)+"</td>");
					out.print(" <td>"+rs.getString(22)+"</td>");
					out.print(" <td>"+rs.getString(23)+"</td>");
					out.print(" <td>"+rs.getString(24)+"</td>");
					out.print(" <td>"+rs.getString(25)+"</td>");
					out.print(" <td>"+rs.getString(26)+"</td>");
					out.print(" <td>"+rs.getString(27)+"</td>");
					out.print(" <td>"+rs.getString(28)+"</td>");
					out.print(" <td>"+rs.getString(29)+"</td>");
					out.print(" <td>"+rs.getString(30)+"</td>");
					out.print(" <td>"+rs.getString(31)+"</td>");
					out.print(" <td>"+rs.getString(32)+"</td>");
					//out.print(" <td width='3%'>T.ant</td>");
					//out.print(" <td width='4%'>D.ant</td>");
					//out.print(" <td>"+rs.getString(34)+"</td>");
					//out.print(" <td width='0%'> xxxx </td>");
					//out.print(" <td width='0%'>xxxx</td>");
					out.print(" <td width='0%'><a href='#' onclick='EliminarDetallecxP("+rs.getString(1)+","+CodDocumentoCxP+")'>Omitir</a></td>");
					out.print("</tr>");
				}
				out.print("<tr><td colspan='19'><input type='button' value='Finalizar' onclick='FinalizarDocumentpCxP() '   /></td></tr>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.print("Ingreso Exitoso.");
		}
		
		if(va.equals("FinCxP")){
			String CodDocumentoCxP=req.getParameter("CodDocumentoCxP");	
			String ValorLetras="";
			try {
				rs=mdcxp.ConsultaSaldoDocumentoCxP(CodDocumentoCxP);
				if(rs.next()){
					ValorLetras=Convertir(rs.getString(1));
					//System.out.println("ValorLetras "+ValorLetras);
					mdcxp.ActualizarValorenLetras(ValorLetras, CodDocumentoCxP);
					String Iva_Retenido="";	String Ica_Retenido="";	String Iva_Pagado=""; 
					String Retencion="";String Iva="";String Anticipos=""; 
					String Gastos="";String Descuentos="";String ProveedorIC="";
					
					rs2=mdcxp.ObtenerInterfasContable(rs.getString(2));
					if(rs2.next()){
						Iva_Retenido=rs2.getString(3);
						Ica_Retenido=rs2.getString(4);
						Iva_Pagado=rs2.getString(5);
						Retencion=rs2.getString(6);
						Iva=rs2.getString(7);
						Anticipos=rs2.getString(8);
						Gastos=rs2.getString(9);
						Descuentos=rs2.getString(10);
						ProveedorIC=rs2.getString(11);
					}
					rs2.getStatement().getConnection().close();
					/*********HTML DE INTERFAZ CONTABLE***************/
					out.println("<table border='0' width='100%' ><tr><td colspan='6'><div id='cabecera2' class='style11' align='center'>INTERFAZ CONTABLE </div></td></tr>");
					out.println("<tr><td width='9%' class='style12'>IVA RETENIDO </td>");
					if(Iva_Retenido.equals("0")){
						out.println("<td width='23%'><input name='txtIvaRetenido' type='text' id='txtIvaRetenido' onkeyup='AutoCompletaCIR()' ></td>");
					}else{
						out.println("<td width='23%'><input name='txtIvaRetenido' type='text' id='txtIvaRetenido' onkeyup='AutoCompletaCIR()' value='"+Iva_Retenido+"'></td>");
					}
					
					
					out.println("<td width='9%'><span class='style12'>ICA RETENIDO  </span></td>");
					if(Ica_Retenido.equals("0")){
						out.println("<td width='28%'><input name='txtIcaRetenido' type='text' id='txtIcaRetenido' onkeyup='AutoCompletaCIcaR()'></td>");
					}else{
						out.println("<td width='28%'><input name='txtIcaRetenido' type='text' id='txtIcaRetenido' onkeyup='AutoCompletaCIcaR()' value='"+Ica_Retenido+"' ></td>");
					}
					
					
					out.println("<td width='9%'><span class='style12'>GASTOS</span></td>");
					if(Gastos.equals("0")){
						out.println("<td width='22%'><input name='txtGastos' type='text' id='txtGastos' onkeyup='AutoCompletaCG()'></td></tr>");
					}else{
						out.println("<td width='22%'><input name='txtGastos' type='text' id='txtGastos' onkeyup='AutoCompletaCG()' value='"+Gastos+"' ></td></tr>");
					}
					
					
					out.println("<tr><td class='style12'>&nbsp;</td><td><div id='CuentaIvaRete'>Iva Retenido</div></td><td>&nbsp;</td><td><div id='CuentaIcaRete'>Ica Retenido</div></td><td>&nbsp;</td><td><div id='CuentaGastos'>Gastos</div></td></tr>");
					
					out.println("<tr><td class='style12'>IVA PAGADO </td>");
					if(Iva_Pagado.equals("0")){
						out.println("<td><input name='txtIvaPagado' type='text' id='txtIvaPagado' onkeyup='AutoCompletaCIP()'></td>");
					}else{
						out.println("<td><input name='txtIvaPagado' type='text' id='txtIvaPagado' onkeyup='AutoCompletaCIP()' value='"+Iva_Pagado+"'></td>");
					}
					
					
					out.println("<td><span class='style12'>RETENCION</span></td>");
					if(Retencion.equals("0")){
						out.println("<td><input name='txtRetencion' type='text' id='txtRetencion' onkeyup='AutoCompletaCRet()'></td>");
					}else{
						out.println("<td><input name='txtRetencion' type='text' id='txtRetencion' onkeyup='AutoCompletaCRet()' value='"+Retencion+"'></td>");
					}
					
					
					out.println("<td><span class='style12'>DESCUENTOS</span></td>");
					if(Descuentos.equals("0")){
						out.println("<td><input name='txtDescuentos' type='text' id='txtDescuentos' onkeyup='AutoCompletaCDes()'></td></tr>");
					}else{
						out.println("<td><input name='txtDescuentos' type='text' id='txtDescuentos' onkeyup='AutoCompletaCDes()' value='"+Descuentos+"' ></td></tr>");
					}
					
					
					out.println("<tr><td class='style12'>&nbsp;</td><td><div id='CuentaIvaPagado'>Iva Pagado</div></td><td>&nbsp;</td><td><div id='CuentaRetencion'>Retencion</div></td><td>&nbsp;</td><td><div id='CuentaDescuento'>Descuento</div></td></tr>");
					out.println("<tr><td class='style12'>IVA</td>");
					if(Iva.equals("0")){
						out.println("<td><input name='txtIva' type='text' id='txtIva' onkeyup='AutoCompletaCIva()'></td>");
					}else{
						out.println("<td><input name='txtIva' type='text' id='txtIva' onkeyup='AutoCompletaCIva()' value='"+Iva+"'></td>");
					}
					
					
					out.println("<td><span class='style12'>ANTICIPOS</span></td>");
					if(Anticipos.equals("0")){
						out.println("<td><input name='txtAnticipos' type='text' id='txtAnticipos' onkeyup='AutoCompletaCAnt()'></td>");
					}else{
						out.println("<td><input name='txtAnticipos' type='text' id='txtAnticipos' onkeyup='AutoCompletaCAnt()' value='"+Anticipos+"' ></td>");
					}
					
					
					out.println("<td><span class='style12'>PROVEEDOR </span></td>");
					if(ProveedorIC.equals("0")){
						out.println("<td><input name='txtProveedorIC' type='text' id='txtProveedorIC' onkeyup='AutoCompletaCPro()' ></td></tr>");
					}else{
						out.println("<td><input name='txtProveedorIC' type='text' id='txtProveedorIC' onkeyup='AutoCompletaCPro()' value='"+ProveedorIC+"' ></td></tr>");
					}
					
					
					out.println("<tr><td class='style12'>&nbsp;</td><td><div id='CuentaIva'>Iva</div></td><td>&nbsp;</td><td><div id='CuentaAnticipos'>Anticipos</div></td><td>&nbsp;</td><td><div id='CuentaProveedor'>Proveedor</div></td></tr>");
					out.println("<tr><td colspan='6' class='style12' align='center'><input name='Button' type='button' class='boton4' value='Ingresar'></td></tr></table>");
					/************************************************/
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String Numero_Cuenta=req.getParameter("Numero_Cuenta");
		if(va.equals("ACIR")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(1,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACIcaR")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(2,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACG")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(3,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACIP")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(4,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACRet")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(5,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACDes")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(6,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACIva")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(7,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACAnt")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(8,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("ACPro")){
			rs=mdcxp.Cuentas(Numero_Cuenta);
			try {
				while(rs.next()){
					out.print("<a href='#' onclick='SeleccionarCuenta(9,"+rs.getString(1)+")'>"+rs.getString(2)+"-"+rs.getString(3)+"</a>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("17")){			
			 /*String docu=req.getParameter("docu");
			 String ac=req.getParameter("ac");
			 String pc=req.getParameter("pc");*/
			String numfact=req.getParameter("numfact");
			String SQL="";
			
			if((numfact.equals(""))&&(!docu.equals(""))&&(ac.equals(""))&&(pc.equals(""))){
				//esta lleno el documento
				out.println("esta lleno el documento");
				
				SQL=SQL+" AND dc.numero_documento LIKE '%"+docu+"%' ";
			}
			
			
			if((!numfact.equals(""))&&(docu.equals(""))&&(ac.equals(""))&&(pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la factura");
				
				SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%' ";
			}
			
			
			if((numfact.equals(""))&&(docu.equals(""))&&(!ac.equals(""))&&(pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno el año");
				
				SQL=SQL+" AND dc.anio='"+ac+"' ";
			}
			
			if((numfact.equals(""))&&(docu.equals(""))&&(ac.equals(""))&&(!pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno el periodo");
				SQL=SQL+" AND dc.periodo='"+pc+"' ";
			}
			
			if((!numfact.equals(""))&&(!docu.equals(""))&&(ac.equals(""))&&(pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la factura-documento");
				
				SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%' AND dc.numero_documento LIKE '%"+docu+"%' ";
			}
			
			if((!numfact.equals(""))&&(docu.equals(""))&&(!ac.equals(""))&&(pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la factura-año");
				
				SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%'  AND dc.anio='"+ac+"' ";
			}
			
			if((!numfact.equals(""))&&(docu.equals(""))&&(ac.equals(""))&&(!pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la factura-periodo");
				
				SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%' AND dc.periodo='"+pc+"' ";
			}
			
			if((numfact.equals(""))&&(!docu.equals(""))&&(!ac.equals(""))&&(pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la documento-año");
				
				SQL=SQL+" AND dc.numero_documento LIKE '%"+docu+"%' AND dc.anio='"+ac+"' ";
			}
			
			if((numfact.equals(""))&&(!docu.equals(""))&&(ac.equals(""))&&(!pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la documento-periodo");
				
				SQL=SQL+" AND dc.numero_documento LIKE '%"+docu+"%' AND dc.periodo='"+pc+"' ";
			}
			
			if((numfact.equals(""))&&(docu.equals(""))&&(!ac.equals(""))&&(!pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la año-periodo");
				SQL=SQL+" AND dc.anio='"+ac+"' AND dc.periodo='"+pc+"' ";
			}
			
			if((!numfact.equals(""))&&(!docu.equals(""))&&(!ac.equals(""))&&(pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la factura-documento-año");
				
				SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%' AND dc.numero_documento LIKE '%"+docu+"%' AND dc.anio='"+ac+"' ";
			}
			
			if((!numfact.equals(""))&&(!docu.equals(""))&&(ac.equals(""))&&(!pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la factura-documento-periodo");
				SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%' AND dc.numero_documento LIKE '%"+docu+"%' AND dc.periodo='"+pc+"' ";
			}
			
			if((!numfact.equals(""))&&(docu.equals(""))&&(!ac.equals(""))&&(!pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la factura-año-periodo");
				SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%'  AND dc.anio='"+ac+"'  AND dc.periodo='"+pc+"' ";
			}
			
			if((numfact.equals(""))&&(!docu.equals(""))&&(!ac.equals(""))&&(!pc.equals(""))){
				//esta lleno el numero de la factura
				out.println("esta lleno la documento-año-periodo");
				SQL=SQL+" AND dc.numero_documento LIKE '%"+docu+"%'  AND dc.anio='"+ac+"'  AND dc.periodo='"+pc+"' ";
			}
			
			
			if((!numfact.equals(""))&&(!docu.equals(""))&&(!ac.equals(""))&&(!pc.equals(""))){
				//estan llenos los dos
				out.println("esta lleno todo.");
				SQL=SQL+" AND dc.numero_documento LIKE '%"+docu+"%' AND dc.factura_proveedor LIKE '%"+numfact+"%'  AND dc.anio='"+ac+"'  AND dc.periodo='"+pc+"' ";
			}
			
			//System.out.print("numfact "+numfact+" docu="+docu ); 
			/*SQL=SQL+" AND dc.factura_proveedor LIKE '%"+numfact+"%' ";
			
			SQL=SQL+" AND dc.numero_documento LIKE '%"+docu+"%' ";*/
			
			//out.println("docu="+docu+" ac="+ac+" pc="+pc+" numfact="+numfact);
			rs=mdcxp.ConsultarDocumentosCxP(SQL);
			try {
				out.print("<table width='100%' border='1'><tr><td>Numero Documento</td><td>Numero Factura</td><td>Fecha Documento</td><td>Terceros</td></tr>");
				if(rs.next()){
					rs1=mdcxp.ConsultarDocumentosCxP(SQL);
					while(rs1.next()){
						out.print("<tr onclick='imprimirReporteCxP("+rs1.getString(1)+")'><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td></tr>");
					}
					rs1.getStatement().getConnection().close();
				out.print("</table>");	
				}else{
					out.print("No existen resultados para su consulta.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
	
	}// Fin doPost
	
	

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
	
	
	public String FormatoMoneda(String valor){		
		String temp2="";String temp1="";
		float ud=1;int logCad = valor.length();		
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
	
}// Fin class Control Documentos
