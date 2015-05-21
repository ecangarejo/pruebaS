package cont_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cont_metodo.MetodoCuentas;
import cont_metodo.MetodoProveedor;

public class ControlProveedor extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2452236813393848348L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		MetodoProveedor mpr= new MetodoProveedor();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		//ResultSet rs4=null;
		/**********************************************************/
		String tipo_identificacion=req.getParameter("tipo_identificacion");
		String numero_documento=req.getParameter("numero_documento");
		String nombre_razonsoc=req.getParameter("nombre_razonsoc");
		String Nombre1=req.getParameter("Nombre1");
		String Nombre2=req.getParameter("Nombre2");
		String Apellido1=req.getParameter("Apellido1");
		String Apellido2=req.getParameter("Apellido2");				
		String RazonSocialDian=req.getParameter("RazonSocialDian");
		String OperExt=req.getParameter("OperExt");
		String direccion=req.getParameter("direccion");
		String telefono=req.getParameter("telefono");
		String telcontacto=req.getParameter("telcontacto");
		String emailcontac=req.getParameter("emailcontac");
		String IndCliente=req.getParameter("IndCliente");
		String IndProveedor=req.getParameter("IndProveedor");
		String IndEmpleado=req.getParameter("IndEmpleado");
		String Municipio=req.getParameter("Municipio");
		String Departamento=req.getParameter("Departamento");
		//String codigo=req.getParameter("codigo");
		String CodProv=req.getParameter("CodProv");
		
		String NombreSucursal=req.getParameter("NombreSucursal");
		                                  //    NombreSucursal
		String DireSucursal=req.getParameter("DireSucursal");
		String Telefono1=req.getParameter("Telefono1");
		String Telefono2=req.getParameter("Telefono2");
		
		String Anio=req.getParameter("Anio");
		String Periodo=req.getParameter("Periodo");
		String Bloqueo=req.getParameter("Bloqueo");
		String BloqueoCxP=req.getParameter("BloqueoCxP");
		String CodAP=req.getParameter("CodAP");
		String NombreAPeriodo=req.getParameter("NombreAPeriodo");
		String NomCentroCosto=req.getParameter("NomCentroCosto");
		String SubcentroCosto=req.getParameter("SubcentroCosto");
		/*********************PARA LA CREACION DE LA CUENTA******************/
		String CodigoCuenta=req.getParameter("CodigoCuenta");
		String NombreCuenta=req.getParameter("NombreCuenta");
		String TipoCuenta=req.getParameter("TipoCuenta");
		String NaturalezaCuenta=req.getParameter("NaturalezaCuenta");
		String Nivel=req.getParameter("Nivel");
		String estado=req.getParameter("estado");
		String CodCuenta=req.getParameter("CodCuenta");
		String IndDiferido=req.getParameter("IndDiferido");
		String IndiBase=req.getParameter("IndiBase");
		String IndiCCosto=req.getParameter("IndiCCosto");
		String IndTercero=req.getParameter("IndTercero");
		/***********************************************************************/
		if(va.equals("ALT")){			
			try {
				rs=mpr.BuscarProveedorLike(tipo_identificacion, numero_documento);
				out.print("<table width='100%' border='0'><tr><td>Nit</td><td>Razon Social</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td><a href='#' onclick='MostrarTercero(&quot;"+rs.getString(2)+"&quot;,&quot;"+rs.getString(3)+"&quot;)'>"+rs.getString(8)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		if(va.equals("LT")){			
			try {
				rs=mpr.ListarTodosTerceros();
				out.print("<table width='100%' border='0'><tr><td>Nit</td><td>Razon Social</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(2)+" "+rs.getString(3)+"</td><td><a href='#' onclick='MostrarTercero(&quot;"+rs.getString(2)+"&quot;,&quot;"+rs.getString(3)+"&quot;)'>"+rs.getString(8)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/***********************************************************************/
		/**********************************************************/
		String Parametro=req.getParameter("Parametro");
		if(va.equals("crca")){
			out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>CONSULTAR RECIBOS DE CAJA </td> </tr> " +
					" <tr><td width='16%'>Numero Recibo de Caja</td><td colspan='2'><input name='txtParametro' type='text' id='txtParametro' /></td> </tr> " +
					" <tr><td colspan='3' align='center'><input name='btnRecibocaja' type='button' id='btnRecibocaja' onclick='BuscarReciboCaja()' value='Buscar Recibo Caja' /></td></tr> " +
					"<tr><td colspan='3'><div id='resultado'></div></td></tr></table>");
		}
		/**************************************************************************/
		String CodCCosto=req.getParameter("CodCCosto");
		String CodSubCCosto=req.getParameter("CodSubCCosto");
		
		String CodSucursal=req.getParameter("CodSucursal");
		/**************************************************************************/
		/***********************INICIO DE MAESTRO DE BANCOS**************************/
		//String CuentaBanco=req.getParameter("CuentaBanco");
		String CodigoCuentaBanco=req.getParameter("CodigoCuentaBanco");
		String CodNacional=req.getParameter("SiglaBanco");
		String NombreBanco=req.getParameter("NombreBanco");
		
		if(va.equals("gtp")){
			String FormaPago=req.getParameter("Descripcion");
			String cuenta_fk=req.getParameter("CodCuenta");
			String naturaleza=req.getParameter("");
			String comision=req.getParameter("Comision");
			String retefuente=req.getParameter("Retefuente");
			String iva=req.getParameter("Iva");
			String reteiva=req.getParameter("Reteiva");
			String reteica=req.getParameter("ReteIca");
			rs=mpr.BuscarTipoPago(FormaPago);
			try {
				if(rs.next()){
					out.print("Tipo de pago ya esta creado.");
				}else{
					mpr.InsertarTipoPago(FormaPago, cuenta_fk, naturaleza, comision, retefuente, iva, reteiva, reteica);
					out.print("Ingreso Exitoso");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(va.equals("GMC")){
			String cuenta_fk=req.getParameter("CodCuenta");
			String Descripcion=req.getParameter("Descripcion");

			mpr.InsertarMovimientoCredito(Descripcion, cuenta_fk);
		}
		
		if(va.equals("cgtp")){			
			try {
				out.print("<table border='0' width='100%'>");
				out.print("<tr><td>Tipo de Pago</td><td>% Comision</td><td>% Retencion en la fuente</td><td>% Iva</td><td>% Rete Iva</td><td>% Rete ICA</td><td>Afecta Cuenta</td><td>Naturaleza</td></tr>");
				rs=mpr.TodosTipoPago();
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(12)+"("+rs.getString(11)+")</td><td>"+rs.getString(13)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("tpa")){			
			out.print("<table border='0' width='100%' ><tr><td colspan='12'><div id='cabecera2' class='style11' align='center'>TIPOS DE PAGO </div></td></tr>");
			out.print(" <tr class='style12'><td >Descripcion</td> <td width='37%'><input name='txtDescripcion' type='text' id='txtDescripcion' style='width:100%'></td><td width='7%'>% comision </td><td><input name='txtComision' type='text' id='txtComision' size='10'></td><td>% retefuente </td><td><input name='txtRetefuente' type='text' id='txtRetefuente' size='10'></td>");
			out.print(" <td>%iva</td><td><input name='txtIva' type='text' id='txtIva' size='10'></td><td>%reteiva</td><td><input name='txtReteiva' type='text' id='txtReteiva' size='10'></td><td>% reteica </td><td><input name='txtReteica' type='text' id='txtReteica' size='10'></td></tr>");
			out.print(" <tr><td class='style12'>Cuenta</td><td colspan='2'><input name='txtCuentaBanco' type='text' id='txtCuentaBanco' onkeyup='AutocompletaCuentaBanco()'><input type='hidden' id='CodCuentaBanco' ></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print(" <tr><td width='6%' class='style12'>&nbsp;</td> <td colspan='2'><div id='CuentaBanco'></div></td><td width='5%'>&nbsp;</td><td width='7%'>&nbsp;</td><td width='6%' align='center'>&nbsp;</td><td width='4%'>&nbsp;</td><td width='5%'>&nbsp;</td><td width='5%'>&nbsp;</td><td width='5%'>&nbsp;</td><td width='8%'>&nbsp;</td><td width='5%'>&nbsp;</td></tr>");
			out.print(" <tr><td class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td><td><input type='button' name='Button2' value='Ingresar' onclick='GuardarTipoPago()' ></td><td>&nbsp;</td><td align='center'>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print(" <tr><td colspan='12' ><div id='CTiPago'>");
			/////////////////////////////////////
			try {
				out.print("<table border='0' width='100%'>");
				out.print("<tr class='style12'><td>TIPO DE PAGO</td><td>% COMISION</td><td>% RETE FUENTE</td><td>% IVA</td><td>% RETE IVA</td><td>% RETE ICA</td><td>AFECTA CUENTA</td><td>NATURALEZA</td></tr>");
				rs=mpr.TodosTipoPago();
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(12)+"</td><td>"+rs.getString(13)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/////////////////////////////////////
			
			out.print("</div></td></tr></table>");
		}
		
		//PARAMETRIZAR NOTAS CREDITO
		if(va.equals("PNC")){			
			out.print("<table border='0' width='100%' ><tr><td colspan='12'><div id='cabecera2' class='style11' align='center'>TIPOS DE PAGO </div></td></tr>");
			out.print(" <tr class='style12'><td >Descripcion</td> <td width='37%'><input name='txtDescripcion' type='text' id='txtDescripcion' style='width:100%'></td></tr>");
			//out.print("<td width='7%'>% comision </td><td><input name='txtComision' type='text' id='txtComision' size='10'></td><td>% retefuente </td><td><input name='txtRetefuente' type='text' id='txtRetefuente' size='10'></td>");
			//out.print(" <td>%iva</td><td><input name='txtIva' type='text' id='txtIva' size='10'></td><td>%reteiva</td><td><input name='txtReteiva' type='text' id='txtReteiva' size='10'></td><td>% reteica </td><td><input name='txtReteica' type='text' id='txtReteica' size='10'></td></tr>");
			out.print(" <tr><td class='style12'>Cuenta</td><td colspan='2'><input name='txtCuentaBanco' type='text' id='txtCuentaBanco' onkeyup='AutocompletaCuentaBanco()'><input type='hidden' id='CodCuentaBanco' ></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print(" <tr><td width='6%' class='style12'>&nbsp;</td> <td colspan='2'><div id='CuentaBanco'></div></td><td width='5%'>&nbsp;</td><td width='7%'>&nbsp;</td><td width='6%' align='center'>&nbsp;</td><td width='4%'>&nbsp;</td><td width='5%'>&nbsp;</td><td width='5%'>&nbsp;</td><td width='5%'>&nbsp;</td><td width='8%'>&nbsp;</td><td width='5%'>&nbsp;</td></tr>");
			out.print(" <tr><td class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td><td><input type='button' name='Button2' value='Ingresar' onclick='GuardarMovimientoCredito()' ></td><td>&nbsp;</td><td align='center'>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print(" <tr><td colspan='12' ><div id='CTiPago'>");
			/////////////////////////////////////
			try {
				out.print("<table border='0' width='100%'>");
				out.print("<tr class='style12'><td>Movimiento</td><td>Cuenta</td><td>Nombre Cuenta</td></tr>");
				rs=mpr.MovimientosTipoCredito();
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(2)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/////////////////////////////////////
			
			out.print("</div></td></tr></table>");
		}
		
		if(va.equals("mba")){
			out.print("<table border='0' width='100%' ><tr><td colspan='10'><div id='cabecera2' class='style11' align='center'>TIPOS DE MOVIMIENTOS DE BANCOS </div></td></tr>");
			out.print("<tr class='style12'><td width='6%' >Descripcion</td> <td width='36%'><input name='txtDescripcion' type='text' id='txtDescripcion' style='width:100%' ></td><td width='7%'>Operacion</td><td width='11%'><select name='cmbOperacion' id='cmbOperacion'><option value='Seleccione'>Seleccione</option>");
			out.print("<option value='Entrada'>Entrada</option><option value='Salida'>Salida</option></select></td><td width='6%'>&nbsp;</td><td width='7%'>&nbsp;</td><td width='6%'>&nbsp;</td><td width='7%'>&nbsp;</td><td width='7%'>&nbsp;</td><td width='7%'>&nbsp;</td></tr>");
			out.print(" <tr><td class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print("<tr><td class='style12'>&nbsp;</td><td colspan='2'>&nbsp;</td><td><input type='button' name='Button2' value='Ingresar'></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
			out.print("<tr><td colspan='10' class='style12'>&nbsp;</td></tr></table>");
			out.print("");
		}
		
		if(va.equals("ccb")){
			out.print("<table border='0' width='100%' id='TabDife' ><tr><td colspan='6' class='style12'><div id='cabecera2' class='style11' align='center'><p>MAESTRO BANCOS </p></div></td></tr> " +
					"<tr><td width='11%' class='style12'>Nombre Banco *</td><td width='22%'><input name='txtNomBanco' type='text' id='txtNomBanco'  maxlength='150' style='width:100%'></td><td width='7%' class='style12'>Cuenta Banco * </td>");
			out.print("<td width='15%' class='style12'><input name='txtCuentaBanco' type='text' id='txtCuentaBanco' onkeyup='AutocompletaCuentaBanco()' ><input type='hidden' id='CodCuentaBanco' ></td><td width='11%' class='style12'>Codigo Nacional </td><td width='34%' class='style12'><input name='txtSiglaBanco' type='text' id='txtSiglaBanco' size='5' maxlength='5'></td></tr>");
			out.print("<tr><td class='style12'></td><td><div></div></td> <td class='style12'></td><td><div id='CuentaBanco'></div></td><td class='style12'></td><td class='style12'><div></div></td></tr><tr> <td class='style12' >Descripcion</td><td ><input name='txtDescripcion' type='text' id='txtDescripcion'></td><td class='style12'>Tipo *</td><td  ><select name='cmbTipo' id='cmbTipo'><option value='Seleccione'>Seleccione</option><option value='Corriente'>Corriente</option><option value='Ahorro'>Ahorro</option></select></td>");
			out.print("<td class='style12' >Estado*</td><td ><select name='cmbEstado' id='cmbEstado'><option value='Seleccione'>Seleccione</option><option value='Activo'>Activo</option><option value='Inactivo'>Inactivo</option></select><label class='style12'>Observaciones<textarea name='txtObservacion' cols='35' rows='4' id='txtObservacion'></textarea></label></td></tr>");
			out.print("<tr><td class='style12' >&nbsp;</td><td colspan='4' align='center' ><input name='btnGuardarBanco' type='button' id='btnGuardarBanco' value='          Guardar Banco          ' onclick='GuardarBanco()'></td><td >&nbsp;</td></tr></table>	");
			out.print("<table width='100%'><tr><td id='ToBanc'>");
			try {
				rs=mpr.TodosBancos();
				out.print("<table width='100%'><tr class='style12'><td>NOMBRE</td><td>CODIGO NACIONAL</td><td>CUENTA</td><td>DESCRIPCION</td><td>TIPO</td><td>ESTADO</td><td>OBSERVACION</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(11)+"-"+rs.getString(10)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</td></tr></table>");
			/*out.print("<table border='0' width='100%' id='TabDife' ><tr><td colspan='6' class='style12'><div id='cabecera2' class='style11' align='center'><p>MAESTRO BANCOS </p></div></td></tr>");
			out.print("<tr><td width='11%' class='style12'>Nombre Banco </td><td width='22%'><input name='txtNomBanco' type='text' id='txtNomBanco'  maxlength='150' style='width:100%'></td><td width='12%' class='style12'>Cuenta Banco </td>");
			out.print("<td width='26%' class='style12'><input name='txtCuentaBanco' type='text' id='txtCuentaBanco' style='width:100%' onkeyup='AutocompletaCuentaBanco()' ><input type='hidden' id='CodCuentaBanco' ></td><td width='6%' class='style12'>Sigla</td><td width='23%' class='style12'><input name='txtSiglaBanco' type='text' id='txtSiglaBanco' maxlength='5' style='width:100%'></td></tr>");
			out.print("<tr><td class='style12'></td><td><div></div></td> <td class='style12'></td><td><div id='CuentaBanco'></div></td><td class='style12'></td><td class='style12'><div></div></td></tr>");*/
		//	out.print("<tr><td class='style12'>Fecha Inicial dd/mm/aaaa </td><td><input name='txtFechaInicial' type='text' id='txtFechaInicial' size='10' maxlength='10' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td><td class='style12'>Fecha Final dd/mm/aaaa </td><td><input name='txtFechaFinal' type='text' id='txtFechaFinal' size='10' maxlength='10' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td>");
		//	out.print("<td class='style12'>Monto</td><td class='style12'><input name='txtMonto' type='text' id='txtMonto' style='width:100%'></td></tr>");
		//	out.print("<tr><td class='style12' >Descripcion</td><td colspan='2' ><p><textarea name='txtDescripcion' rows='3' id='txtDescripcion' style='width:100%'></textarea></p></td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td></tr>");
			/*out.print("<tr><td class='style12' >&nbsp;</td><td colspan='4' align='center' ><input name='btnGuardarBanco' type='button' id='btnGuardarBanco' value='          Guardar Banco          ' onclick='GuardarBanco()'></td><td >&nbsp;</td></tr></table>");*/

		}
		
		if(va.equals("Conccb")){
			
			try {
				rs=mpr.TodosBancos();
				out.print("<table width='100%'><tr><td>NOMBRE</td><td>CODIGO NACIONAL</td><td>CUENTA</td><td>DESCRIPCION</td><td>TIPO</td><td>ESTADO</td><td>OBSERVACION</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(11)+"-"+rs.getString(10)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("gccb")){
			String Descripcion=req.getParameter("Descripcion");
			String Tipo=req.getParameter("Tipo");
			String Estado=req.getParameter("Estado");
			String Observacion=req.getParameter("Observacion");

			mpr.CrearBanco(NombreBanco,CodigoCuentaBanco,CodNacional,Descripcion,Tipo,Estado,Observacion);
			out.print("Banco Creado");
		}
		/***********************FIN MAESTRO DE BANCOS********************************/
		/***********************INICIO DE DIFERIDO***********************************/
		
		String parametro=req.getParameter("parametro");
		String CodCuentaDife=req.getParameter("CodCuentaDife");
		String CodTercero=req.getParameter("CodTercero");
		String CodCuentaGasto=req.getParameter("CodCuentaGasto");
		String FechaInicial=req.getParameter("FechaInicial");
		String FechaFinal=req.getParameter("FechaFinal");
		String Monto=req.getParameter("Monto");
		String Descripcion=req.getParameter("Descripcion");
		String CuentaDiferido=req.getParameter("CuentaDiferido");
		String CuentaGasto=req.getParameter("CuentaGasto");
		String Tercero=req.getParameter("Tercero");
		
		if(va.equals("VCC")){			
			try {
				rs=mpr.BuscarCuenta(CodigoCuenta);
				if(rs.next()){
					
				}else{
					out.print("No");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("gd")){
			try{
			
				//	quedar�a: 4.5685
				
				mpr.CrearDiferido(CodCuentaDife, CodTercero, CodCuentaGasto, FechaInicial, FechaFinal, Monto, Descripcion,CuentaDiferido,CuentaGasto,Tercero);
				out.print("Diferido Creado Exitosamente.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("dif")){
			/*int doubleSinRedondear = 11.56;

			BigDecimal bd = new BigDecimal(doubleSinRedondear);

			BigDecimal doubleRedondeado = bd.setScale(4,BigDecimal.ROUND_HALF_UP);
			//quedar�a: 4.5690		
			System.out.print("doubleRedondeado "+doubleRedondeado);
			bd.pow(doubleSinRedondear);
			BigDecimal doubleRedondeado2 = bd.setScale(4,BigDecimal.ROUND_HALF_DOWN);
			System.out.print("doubleRedondeado2 "+doubleRedondeado2);*/
			out.print("<table border='0' width='100%' id='TabDife' ><tr><td colspan='6' class='style12'><div id='cabecera2' class='style11' align='center'><p>MAESTRO DIFERIDOS </p></div></td></tr>");
			out.print("<tr><td width='11%' class='style12'>Cuenta Diferido </td><td width='22%'><input name='txtCuentaDiferido' type='text' id='txtCuentaDiferido' style='width:100%' onkeyup='AutocompletaCuentaDiferido()'><input type='hidden' id='CodCuentaDife' ></td><td width='12%' class='style12'>Cuenta Gasto Ingreso </td>");
			out.print("<td width='26%' class='style12'><input name='txtCuentaGasto' type='text' id='txtCuentaGasto' style='width:100%' onkeyup='AutocompletaCuentaGasto()' ><input type='hidden' id='CodCuentaGasto' ></td><td width='6%' class='style12'>Tercero</td><td width='23%' class='style12'><input name='txtTercero' type='text' onkeyup='AutocompletarTercero()' id='txtTercero' style='width:100%'><input type='hidden' id='txtCodTercero'></td></tr>");
			out.print("<tr><td class='style12'></td><td><div id='CuentaDiferido'></div></td> <td class='style12'></td><td><div id='CuentaGastoIng'></div></td><td class='style12'></td><td class='style12'><div id='Tercero'></div></td></tr>");
			out.print("<tr><td class='style12'>Fecha Inicial dd/mm/aaaa </td><td><input name='txtFechaInicial' type='text' id='txtFechaInicial' size='10' maxlength='10' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td><td class='style12'>Fecha Final dd/mm/aaaa </td><td><input name='txtFechaFinal' type='text' id='txtFechaFinal' size='10' maxlength='10' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td>");
			out.print("<td class='style12'>Monto</td><td class='style12'><input name='txtMonto' type='text' id='txtMonto' style='width:100%'></td></tr>");
			out.print("<tr><td class='style12' >Descripcion</td><td colspan='2' ><p><textarea name='txtDescripcion' rows='3' id='txtDescripcion' style='width:100%'></textarea></p></td><td >&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td></tr>");
			out.print("<tr><td class='style12' >&nbsp;</td><td colspan='4' align='center' ><input name='btnGuardarDiferido' type='button' id='btnGuardarDiferido' value='          Guardar          ' onclick='GuardarDiferidos()'></td><td >&nbsp;</td></tr></table>");
		}
		
		if(va.equals("acd")){			
			try {
				int cond=0;
				String CodCuentaACD="";
				String NumeroCuentaACD="";
				String NombreCuentaACD="";
				rs=mpr.BuscarCuentaDiferidoAutocompletar(parametro);
				out.print("<table>");
				while(rs.next()){
					cond=cond+1;
					CodCuentaACD=rs.getString(1);
					NumeroCuentaACD=rs.getString(2);
					NombreCuentaACD=rs.getString(3);
					out.print("<tr><td><a href='#' onclick='AsignarCuentaDiferido("+rs.getString(1)+",&quot;"+rs.getString(2)+"&quot;,&quot;"+rs.getString(3)+"&quot;)'>"+rs.getString(2)+"-"+rs.getString(3)+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorACD' type='hidden' id='txtContadorACD'     value="+cond+" /></td>" +
						"<td><input name='txtCodCuentaACD'      type='hidden' id='txtCodCuentaACD'    value="+CodCuentaACD+" /></td> " +
						"<td><input name='txtNumeroCuentaACD'   type='hidden' id='txtNumeroCuentaACD' value="+NumeroCuentaACD+" /></td> " +
						"<td><input name='txtNombreCuentaACD'   type='hidden' id='txtNombreCuentaACD' value='"+NombreCuentaACD+"' /></td> " +
						"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
		}
		

		if(va.equals("acgb")){			
			try {
				int cong=0;
				String CodCuentaACG="";
				String NumeroCuentaACG="";
				String NombreCuentaACG="";
				rs=mpr.BuscarCuentaAutocompletar(parametro);
				out.print("<table>");
				while(rs.next()){
					cong=cong+1;
					CodCuentaACG=rs.getString(1);
					NumeroCuentaACG=rs.getString(2);
					NombreCuentaACG=rs.getString(3);
					out.print("<tr><td><a href='#' onclick='AsignarCuentaBanco("+rs.getString(1)+",&quot;"+rs.getString(2)+"&quot;,&quot;"+rs.getString(3)+"&quot;)'>"+rs.getString(2)+"-"+rs.getString(3)+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorACG' type='hidden' id='txtContadorACG'   value="+cong+" /></td>" +
						"<td><input name='txtCodCuentaACG'    type='hidden' id='txtCodCuentaACG'    value="+CodCuentaACG+" /></td>" +
						"<td><input name='txtNumeroCuentaACG' type='hidden' id='txtNumeroCuentaACG' value="+NumeroCuentaACG+" /></td>" +
						"<td><input name='txtNombreCuentaACG' type='hidden' id='txtNombreCuentaACG' value='"+NombreCuentaACG+"' /></td>" +
								"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("acg")){			
			try {
				int cong=0;
				String CodCuentaACG="";
				String NumeroCuentaACG="";
				String NombreCuentaACG="";
				rs=mpr.BuscarCuentaAutocompletar(parametro);
				out.print("<table>");
				while(rs.next()){
					cong=cong+1;
					CodCuentaACG=rs.getString(1);
					NumeroCuentaACG=rs.getString(2);
					NombreCuentaACG=rs.getString(3);
					out.print("<tr><td><a href='#' onclick='AsignarCuentaGasto("+rs.getString(1)+",&quot;"+rs.getString(2)+"&quot;,&quot;"+rs.getString(3)+"&quot;)'>"+rs.getString(2)+"-"+rs.getString(3)+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorACG' type='hidden' id='txtContadorACG'   value="+cong+" /></td>" +
						"<td><input name='txtCodCuentaACG'    type='hidden' id='txtCodCuentaACG'    value="+CodCuentaACG+" /></td>" +
						"<td><input name='txtNumeroCuentaACG' type='hidden' id='txtNumeroCuentaACG' value="+NumeroCuentaACG+" /></td>" +
						"<td><input name='txtNombreCuentaACG' type='hidden' id='txtNombreCuentaACG' value='"+NombreCuentaACG+"' /></td>" +
								"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("act1")){			
			try {
				int conac=0;
				String CodTerceroACT="";
				String DocumentoTerceroACT="";
				String DPlazo="";
				String DigitoVerificacion="";
				String NombreTercero="";
				rs=mpr.BuscarTerceroAutocompletar(parametro);
				out.print("<table>");
				while(rs.next()){
					conac=conac+1;
					CodTerceroACT=rs.getString(1);
					DocumentoTerceroACT=rs.getString(3);
					DigitoVerificacion=rs.getString(33);
					DPlazo=rs.getString(25);
					NombreTercero=rs.getString(9);
					out.print("<tr><td><a href='#' onclick='AsignarTercero("+rs.getString(1)+",&quot;"+rs.getString(3)+"&quot;,"+rs.getString(33)+",&quot;"+rs.getString(9)+"&quot;)'>"+rs.getString(3)+"-"+rs.getString(33)+"|"+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorACT' type='hidden' id='txtContadorACT' value="+conac+" /></td>" +
						"<td><input name='txtCodTerceroACT' type='hidden' id='txtCodTerceroACT' value="+CodTerceroACT+" /></td>" +
						"<td><input name='txtDocumentoTerceroACT' type='hidden' id='txtDocumentoTerceroACT' value="+DocumentoTerceroACT+" /></td>" +
						"<td><input name='txtDplazoACT' type='hidden' id='txtDplazoACT' value="+DPlazo+" /></td>" +
						"<td><input name='txtDV' type='hidden' id='txtDV' value="+DigitoVerificacion+" /></td>" +
						"<td><input name='txtNombreTercero' type='hidden' id='txtNombreTercero' value='"+NombreTercero+"' /></td>" +
								"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("act")){			
			try {
				int conac=0;
				String CodTerceroACT="";
				String DocumentoTerceroACT="";
				String DPlazo="";
				rs=mpr.BuscarTerceroAutocompletar(parametro);
				out.print("<table>");
				while(rs.next()){
					conac=conac+1;
					CodTerceroACT=rs.getString(1);
					DocumentoTerceroACT=rs.getString(3);
					DPlazo=rs.getString(25);
					out.print("<tr><td><a href='#' onclick='AsignarTercero("+rs.getString(1)+",&quot;"+rs.getString(3)+"&quot;)'>"+rs.getString(3)+"-"+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+"</a></td></tr>");
				}
				out.print("<tr><td><input name='txtContadorACT' type='hidden' id='txtContadorACT' value="+conac+" /></td>" +
						"<td><input name='txtCodTerceroACT' type='hidden' id='txtCodTerceroACT' value="+CodTerceroACT+" /></td>" +
						"<td><input name='txtDocumentoTerceroACT' type='hidden' id='txtDocumentoTerceroACT' value="+DocumentoTerceroACT+" /></td>" +
						"<td><input name='txtDplazoACT' type='hidden' id='txtDplazoACT' value="+DPlazo+" /></td>" +
								"</tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("ldif")){
			try {
				rs=mpr.ListarDiferidos();
				out.print("<table border='0' width='100%'><tr class='style12'><td>Cuenta Diferido</td><td>Cuenta Gasto Ingreso</td><td>Tercero</td><td>Fecha Inicial</td><td>Fecha Final</td><td>Descripcion</td><td>Monto</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td><td>"+rs.getString(11)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+FormatoMoneda(rs.getString(8))+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/***********************FIN DE DIFERIDO**************************************/
		
		if(va.equals("rcsG")){
			
			try {
				rs=mpr.BuscarRelacionCentrosCosto(CodCCosto, CodSubCCosto);
				if(rs.next()){
					out.print("Relacion Centro-Subcentro de costo ya existe.\nIntente Nuevamente.");
				}else{
					mpr.RelacionCentroSuncentro(CodCCosto, CodSubCCosto);
					out.print("Ingreso Exitoso.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("rcs")){
			/*************************************************************/
			/*********INICIO DE RELACION CENTRO-SUBCENTRO COSTO***********/
			/*************************************************************/
			try {
			//	out.print("INICIO DE RELACION CENTRO-SUBCENTRO COSTO");
				out.print("<table width='100%' border='0'>");
				out.print("<tr><td colspan='4' id='cabecera2' class='style11' align='center' >RELACION CENTRO-SUBCENTRO COSTO</td></tr>");
				out.print("<tr><td>Centro Costo</td><td><select id='cmbCentroCosto'><option value='Seleccione' selected=''>Seleccione</option>");
				rs=mpr.ListarCentrosCosto();
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></td><td>Subcentro Costo</td><td><select id='cmbSubCentroCosto' ><option value='Seleccione' selected=''>Seleccione</option>");
				rs1=mpr.ListarSubCentrosCosto();
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</select></td></tr><tr><td colspan='4' align='center' ><input type='button' name='btnIngRECCSC' onclick='GuardarCentroSubcentre()' value='Guardar' ></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			/*************************************************************/
			/************FIN DE RELACION CENTRO-SUBCENTRO COSTO***********/
			/*************************************************************/
		}
		if(va.equals("rsccG")){
			try {
				rs=mpr.BuscarRelacionSucursalCentroCosto(CodCCosto, CodSucursal);
				if(rs.next()){
					out.print("Relacion Sucursal-Centro costo ya existe.\nIntente Nuevamente.");
				}else{
					mpr.RelacionCentroSucursal(CodCCosto, CodSucursal);
					out.print("Ingreso Exitoso.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("rscc")){
			/*************************************************************/
			/*********INICIO DE RELACION SUCURSAL-SUBCENTRO COSTO*********/
			/*************************************************************/
			//out.print("INICIO DE RELACION SUCURSAL-SUBCENTRO COSTO");
			try {
				//	out.print("INICIO DE RELACION CENTRO-SUBCENTRO COSTO");
					out.print("<table width='100%' border='0'>");
					out.print("<tr><td colspan='4' id='cabecera2' class='style11' align='center' >RELACION SUCURSAL-CENTRO COSTO</td></tr>");
					out.print("<tr><td>Centro Costo</td><td><select id='cmbCentroCosto'><option value='Seleccione' selected=''>Seleccione</option>");
					rs=mpr.ListarCentrosCosto();
					while(rs.next()){
						out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					}
					rs.getStatement().getConnection().close();
					out.print("</select></td><td>Sucursal</td><td><select id='cmbSucursal' >");
					rs1=mpr.ListarSucursales();
					while(rs1.next()){
						out.print("<option selected='' value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td></tr><tr><td colspan='4' align='center' ><input type='button' name='btnGuardarcentroSucursal' onclick='GuardarCentroSucursal()' value='Guardar' ></td></tr></table>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			/*************************************************************/
			/*********FIN DE RELACION SUCURSAL-SUBCENTRO COSTO************/
			/*************************************************************/
		}
		
		if(va.equals("rdcc")){
			/*************************************************************/
			/***********INICIO DE RELACION DIFERIDO-CENTRO COSTO**********/
			/*************************************************************/
			out.print("INICIO DE RELACION DIFERIDO-CENTRO COSTO");
			
			/*************************************************************/
			/*************FIN DE RELACION DIFERIDO-CENTRO COSTO***********/
			/*************************************************************/
		}
		
		/****************************CONSULTAR RECIBOS DE CAJA****************************/
		if(va.equals("CoRC")){
			
			try {
				rs=mpr.BuscarRecibocaja(Parametro);
				if(rs.next()){
					out.print("<table width='100%' border='0'>");
					out.print("<tr><td>Nombre Entidad</td><td>Concepto</td><td>Fecha</td><td>Accion</td></tr>");
					out.print("<tr><td><a href='#' onclick='ImprimirReciboCaja("+rs.getString(1)+")'>"+rs.getString(2)+"</a></td>" +
							"<td>"+rs.getString(3)+"</td>" +
									"<td>"+rs.getString(4)+"</td>");
					if(rs.getString(6).equals("0")){
						out.print("<td>ANULADO</td></tr>");
					}else{
						out.print("<td><a href='#' onclick='OmitirReciboDeCaja("+rs.getString(1)+")'>Omitir</a></td></tr>");
					}
					out.print("<tr><td colspan='4' ><div id='AnularRCObsrv'></div></td></tr></table>");
					
				}else{
					out.print("No se Encontraron Registros Para este Parametro de Busqueda "+Parametro);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/************************FIN CONSULTAR RECIBOS DE CAJA****************************/
		
		if(va.equals("cpec")){
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			String mess="";
			mes=mes+1;
			if(mes==1){mess="01";}
			if(mes==2){mess="02";}
			if(mes==3){mess="03";}
			if(mes==4){mess="04";}
			if(mes==5){mess="05";}
			if(mes==6){mess="06";}
			if(mes==7){mess="07";}
			if(mes==8){mess="08";}
			if(mes==9){mess="09";}
			if(mes==10){mess="10";}
			if(mes==11){mess="11";}
			if(mes==12){mess="12";}
		
			out.print("<table width='100%' border='0' >");					
			out.print("<tr><td>Fecha Emision</td><td><input type='text' id='txtFechaEmision' onKeyUp='masca(this,&quot;/&quot;,patron,true)' value='"+dia+"/"+mess+"/"+anio+"' ></td></tr>");
			out.print("<tr><td colspan='2' align='center'><input type='button' onclick='VerReporteCarteraGeneral()' value='Generar Reporte'></td></tr></table>");
		
			
		}
		
		
		if(va.equals("cprc")){
			/***REPORTE CARTERA POR EMISION DETALLADO***/	
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			String mess="";
			mes=mes+1;
			if(mes==1){mess="01";}
			if(mes==2){mess="02";}
			if(mes==3){mess="03";}
			if(mes==4){mess="04";}
			if(mes==5){mess="05";}
			if(mes==6){mess="06";}
			if(mes==7){mess="07";}
			if(mes==8){mess="08";}
			if(mes==9){mess="09";}
			if(mes==10){mess="10";}
			if(mes==11){mess="11";}
			if(mes==12){mess="12";}

			try {
				out.print("<table width='100%' border='0' >");
				out.print("<tr><td>Seleccione Entidad</td>");
				
				out.print("<td><select id='cmbEntidades'><option value='Seleccione' selected='' >Seleccione</option>");
				rs=mpr.Entidades();
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				
				
				out.print("</select>Reporte Agrupado<input name='chkNit' type='checkbox' id='chkNit'/></td></tr>");				
				out.print("<tr><td>Fecha Emision</td><td><input type='text' id='txtFechaEmision' onKeyUp='masca(this,&quot;/&quot;,patron,true)' value='"+dia+"/"+mess+"/"+anio+"' ></td></tr>");
				out.print("<tr><td colspan='2' align='center'><input type='button' onclick='GenerarCarteraRadicadaDetallada()' value='Generar Reporte'></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	/////INICIO DE LOS REPORTES DE CONTABILIDAD////////

if(va.equals("cpraux")){
			/***REPORTE DE AUXILIAR DE CONTABILIDAD GENERAL***/
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE AUXILIAR DE CONTABILIDAD GENERAL</td> </tr>");
			//out.print("<tr><td>Digite los siguientes Campos </td>");
			out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; <b> al </b> &nbsp; <input name='MC2' type='text' id='MC2' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; &nbsp; <b> Tipo de Documento: </b> &nbsp; <select id='TDOC'> <option value='todas'>TODAS</option>");
			rs=mpr.BuscarTDoc();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"' >"+rs.getString(3)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("</select></td></tr>"+
						"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>");
			out.print("<tr><td><input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
			out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
			out.print("</td></tr>");
			out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
					/*	"<tr><td><select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
			rs=mpr.BuscarCuentas3();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select>");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
			rs=mpr.BuscarCuentas3();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select></div></td>");*/
			out.print("<td colspan='2' align='center'><input type='button' onclick='ConsultarRepAuxiliarGeneral()' id='Bot0' value='Consultar Reporte '></td></tr></table>");
			
		}
		
		if(va.equals("cprauxCT")){
			/***REPORTE DE AUXILIAR DE CONTABILIDAD POR CUENTA Y TERCERO***/
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE AUXILIAR DE CONTABILIDAD POR CUENTA Y TERCERO</td> </tr>");
			//out.print("<tr><td>Digite los siguientes Campos </td>");
			out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; <b> al </b> &nbsp; <input name='MC2' type='text' id='MC2' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; <b> Tipo de Documento: </b> &nbsp; <select id='TDOC'> <option value='todas'>TODAS</option>");
			rs=mpr.BuscarTDoc();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"' >"+rs.getString(3)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.print("</select>&nbsp; <b> Tipo de Tercero: </b> <input type=text id='ter0'  onkeyup='autocompletarTercero1(0,0)' onBlur='limpiater(0)' value='TODAS' size='10'/> ");
			out.print("</div></td></tr><input type=hidden id='terh0' value='TODAS'><tr><td><div id='dter0' ></div></td></tr>"+
						"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
						"<tr><td>");
			out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
			out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
			out.print("</td></tr>");
			out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
			/*rs=mpr.BuscarTercero(); 
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(3)+"' >"+rs.getString(2)+"</option>");
				}
				out.print("</select></td></tr>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
					out.print("<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
						"<tr><td><select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
			rs=mpr.BuscarCuentas3();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select>");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
			rs=mpr.BuscarCuentas3();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select></div></td>");*/
			out.print("<td colspan='2' align='center'><input type='button' onclick='ConsultarRepAuxiliarCuentaTercero()' id='Bot0' value='Consultar Reporte '></td></tr></table>");
			
		}
		
		if(va.equals("cprbpCT")){
			/***REPORTE DE BALANCE DE PRUEBA POR CUENTA Y POR TERCERO***/
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='6' id='cabecera2' class='style11' align='center'>REPORTE DE BALANCE DE PRUEBA POR CUENTA Y POR TERCERO</td> </tr>");
			//out.print("<tr><td>Digite los siguientes Campos </td>");
			out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> &nbsp; &nbsp; <b> Tipo de Tercero: </b> &nbsp; <input type=text id='ter0'  onkeyup='autocompletarTercero1(0,0)' onBlur='limpiater(0)' value='TODAS'> ");
			out.print("<input type=hidden id='terh0' value='TODAS'>");
			out.print("</td></tr><tr><td><div id='dter0' ></div></td></tr>"+
						"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
						"<tr><td>");
			out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta8(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
			out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta9(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");
			out.print("</td></tr>");
			out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
			/*	rs=mpr.BuscarCuentas3();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select>");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<select id='RC2' style='width:350px'><option value='Seleccione'>Seleccione</> <option value='todas'>Todas</option> ");
			rs=mpr.BuscarCuentas3();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			out.print("</select></div></td>");
			out.print("<td colspan='2' align='center'><input type='button' onclick='ConsBalanceCuentaTercero()'  id='Bot0' value='Consultar Reporte '></td></tr></table>");
			
		}
		
		if(va.equals("autoinv1")){//Seleccionar Cuentas
			String texto=req.getParameter("texto");
			String opc=req.getParameter("opc");
			System.out.println("Valor de OPC "+opc);
			try {	
				if(opc.equals("1")){
					rs =mpr.BuscarCuentas4(texto);
					String cadena ="";
					cadena="[";
					while(rs.next()){
						
						cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
		            	cadena = cadena +",";	
					}
					cadena = cadena+"]";
					res.getWriter().write(cadena);
					
				}else{
					
					rs =mpr.BuscarCuentas5(texto);
					String cadena ="";
					cadena="[";
					System.out.println("entrando al si ");
					if((opc.equals("2"))||(opc.equals("3"))){
							while(rs.next()){
								String val=rs.getString(4);
								int vali=Integer.parseInt(val);
								//System.out.println("valor de opc"+opc);
								if(opc.equals("2")){
										if((vali==1)||(vali==2)||(vali==3)){
											
										//System.out.println("entrando a vali consulta"+vali);
										cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
						            	cadena = cadena +",";	
										}
								}else{
									if(opc.equals("3")){
										if((vali==4)||(vali==5)||(vali==6)||(vali==7)){
											cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"'";
							            	cadena = cadena +",";
										}
									}
									
								}
							}
					}else{
						if(opc.equals("4")){
							rs1=mpr.BuscarCuentasAux(texto);
							while(rs1.next()){
								cadena = cadena+"'"+rs1.getString(1)+"|"+rs1.getString(2)+"|"+rs1.getString(3)+"'";
				            	cadena = cadena +",";
							}
							rs1.getStatement().getConnection().close();
						}
					}
					cadena = cadena+"]";
					res.getWriter().write(cadena);
					
					
				}
				rs.getStatement().getConnection().close();
				
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}
			}
		
		
	
		
		
		if(va.equals("cprbp")){
			/***REPORTE DE BALANCE GENERAL DE PRUEBA***/	
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			String mess="";
			mes=mes+1;
			if(mes==1){mess="01";}
			if(mes==2){mess="02";}
			if(mes==3){mess="03";}
			if(mes==4){mess="04";}
			if(mes==5){mess="05";}
			if(mes==6){mess="06";}
			if(mes==7){mess="07";}
			if(mes==8){mess="08";}
			if(mes==9){mess="09";}
			if(mes==10){mess="10";}
			if(mes==11){mess="11";}
			if(mes==12){mess="12";}

			
				out.print("<table width='100%' border='0'  >");
				out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'>BALANCE DE PRUEBA GENERAL </td> </tr>");
				//out.print("<tr><td>Digite los siguientes Campos </td>");
				out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> </td></tr>"+
							"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
							"<tr><td>");
				/*<select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
				rs=mpr.BuscarCuentas2();
				try {
					while(rs.next()){
						out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("</select>");*/
				out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta2(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
				out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
				out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta3(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");

				out.print("</select></div></td></tr>");
				out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
				out.print("<tr ><td><table id='balance'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
				rs=mpr.BuscarNivel();
				int cont=0;
				try {
					while(rs.next()){
						cont=cont+1;
						out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
					}rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("</table>");
				
				
				out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarRepBalanceGeneral()' value='Consultar Reporte '></td></tr></table>");
		
			
			
		}
		
		if(va.equals("cprbg")){
			
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'> BALANCE GENERAL </td> </tr>");
			//out.print("<tr><td>Digite los siguientes Campos </td>");
			out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> </td></tr>"+
						"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
						"<tr><td>");
			/*<select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
			rs=mpr.BuscarCuentas2();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</select>");*/
			out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta4(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
			out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
			out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta5(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");

			out.print("</select></div></td></tr>");
			out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
			out.print("<tr ><td><table id='balance'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
			rs=mpr.BuscarNivel();
			int cont=0;
			try {
				while(rs.next()){
					cont=cont+1;
					out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
				}rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
			
			out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarBalanceGeneral()' value='Consultar Reporte '></td></tr></table>");
		}
		
		if(va.equals("cprpg")){
			out.print("<table width='100%' border='0'  >");
			out.print("<tr><td colspan='8' id='cabecera2' class='style11' align='center'>REPORTES P Y G </td> </tr>");
			out.print("<tr><td>Seleccione el tipo de informe de Estado de Resultados : <select id='tpg' onChange='Tpg()'><option>---</option><option value='1'> MES </option><option value='2'> ACUMULADO </option><option value='3'> MES Y ACUMULADO</option></td></tr>");
			out.print("<tr><td><div id='menupg' ></div></td></tr>");
		}
		
		if(va.equals("menupg")){
			///REPORTE P Y G - UTILIZAN LOS MISMOS FILTROS UTILIZO LA VARIABLE PG PARA ELEGIR AL REPORTE YA SEA POR MES, ACUMULADO O REP DE MES Y ACUMULADO
			String pg=req.getParameter("pg");
			out.print("<table>");
			out.print("<tr><td><div ><b>A&ntilde;o: </b> &nbsp; &nbsp; <input name='AC' type='text' id='AC' size='3' onKeyup='valAnoPeriodo(this,patronp,true,1)'  />&nbsp; &nbsp;  <b> Periodo: &nbsp;  &nbsp; &nbsp; </b><input name='MC' type='text' id='MC' size='2' onKeyup='valAnoPeriodo(this,patronp,true,0)' /> </td></tr>"+
					"<tr><td > <br>  <b> Rango de Cuentas: </b> <br></td> </tr>"+
					"<tr><td>");
		/*<select id='RC1' style='width:350px' ><option value='Seleccione'>Seleccione</><option value='todas'>Todas</option> ");
		rs=mpr.BuscarCuentas2();
		try {
			while(rs.next()){
				out.print("<option value='"+rs.getString(1)+"' title='"+rs.getString(2)+"'>"+rs.getString(1)+" "+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</select>");*/
		out.print("<input type='text'   id='cta0' onkeyup='autocompletaCta6(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS'/> ");
		out.print("<input type=hidden id='ctah0' ><input type=hidden id='ctahh0' >");
		out.print("&nbsp; &nbsp; al &nbsp; &nbsp;<input type='text'   id='RCC0' onkeyup='autocompletaCta7(0,0)' onBlur='limpcta(0)'	size='10' value='TODAS' />");

		out.print("</select></div></td></tr>");
		out.print("<tr><td width='30%'><div id='dcta0'  width='30%'/></td><td width='30%' align='left'><div id='dctaa0'  width='30%' /></td></tr>");
		out.print("<tr ><td><table id='nivelespg'><tr><td><br><b> Definir Nivel: </b><br></td></tr>");
		rs=mpr.BuscarNivel();
		int cont=0;
		try {
			while(rs.next()){
				cont=cont+1;
				out.print("<tr><td>Nivel "+rs.getString(1)+"<input id='chkEF"+cont+"' type='checkbox' value='"+rs.getString(1)+"' /></td></tr>");
			}rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("</table>");
		
		
		out.print("<tr><td colspan='2' align='center'><input id='Bot0' type='button' onclick='ConsultarPG("+pg+")' value='Consultar Reporte '></td></tr></table>");
			
		}
		
		if(va.equals("ReportePG")){
			String pg=req.getParameter("pg");
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String MC2=req.getParameter("MC2");
			String AC=req.getParameter("AC");
			
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
			
			String cuenta="";
			String nombre="";
			long saldo=0;
			if(pg.equals("1")){
					out.print("<br><br><table width='80%' >");
					out.print("<tr class='contpre'><td></td><td  align='center' colspan='3'> ESTADO DE RESULTADOS MENSUAL DEL PERIODO "+MC+" DEL A�O "+AC+" </td></tr>");
					out.print("<tr class='contpre'><td></td><td  align='left' > Fecha de Generacion: "+Fecha+"</td><td  align='center'> Hora de Generacion: "+Hora+"</td></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					out.print("<tr class='rep' ><b><td >&nbsp; CUENTA </td><td >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td >&nbsp; SALDO CUENTA &nbsp;</td></b></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					
					if(cont>0){
						//String nivel=valorNivel[1];
						rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1");
						try {
							while(rs.next()){
								cuenta=rs.getString(1);
								rs1=mpr.BuscarCuenta(cuenta);
								while(rs1.next()){
									nombre=rs1.getString(3);
								out.print("<tr class='rep'><td align='left' ><b>"+cuenta+"</b></td><td  align='left'><b>"+rs1.getString(3)+"</b></td><td ></td></tr>");
								}
								rs1.getStatement().getConnection().close();
								if(cont==1){
										String nivel=valorNivel[1];
										if(nivel.contains("1")){
											
										}else{
											rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel);
											while(rs2.next()){
												String cuenta1=rs2.getString(1);
												rs3=mpr.BuscarCuenta(cuenta1);
												while(rs3.next()){
													if(cuenta.equals(rs2.getString(9))){
														System.out.println(" naturaleza"+rs2.getString(8));
														if(rs2.getString(8).equals("Debito")){
															
															saldo=((rs2.getLong(5))-(rs2.getLong(6)));
															String sal=Long.toString(saldo);
														//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}else{
															saldo=((rs2.getLong(6))-(rs2.getLong(5)));
															String sal=Long.toString(saldo);
														//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}
														}
												}
												rs3.getStatement().getConnection().close();
											}
											rs2.getStatement().getConnection().close();
										}
										
										
									}else{
										if(cont==2){
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											if(nivel1.contains("1")){
												rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2);
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println(" naturaleza"+rs1.getString(8));
															if(rs1.getString(8).equals("Debito")){
																saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																String sal=Long.toString(saldo);
																//System.out.println("valor de debito "+rs2.getLong(5)+" valor de credito"+rs2.getLong(6)+" valor de la diferencia"+sal);
																//String()
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
															}else{
																System.out.println((rs1.getLong(6))+"-"+(rs1.getLong(5)));
																saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																String sal=Long.toString(saldo);
																//System.out.println("valor de credito "+rs2.getLong(6)+" valor de debito"+rs2.getLong(5)+" valor de la diferencia"+sal);
															//	System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
															}
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}else{
												
												rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2);
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println(" naturaleza"+rs1.getString(8));
															if(rs1.getString(8).equals("Debito")){
																saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																String sal=Long.toString(saldo);
															//System.out.println("valor de debito "+rs1.getLong(5)+" valor de credito"+rs1.getLong(6)+" valor de la diferencia"+sal);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}else{
															saldo=((rs1.getLong(6))-(rs1.getLong(5)));
															String sal=Long.toString(saldo);
															//System.out.println("valor de credito "+rs1.getLong(6)+" valor de debito"+rs1.getLong(5)+" valor de la diferencia"+sal);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
														}
													}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}
										}else{
											if(cont==3){
																						
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3);
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}else{
																	saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																	String sal=Long.toString(saldo);
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}
																}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{
													
													rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3);
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}else{
																	saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																	String sal=Long.toString(saldo);
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																}
																}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}
											
											}else{
												if(cont==4){
													
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													String nivel4=valorNivel[4];
													if(nivel1.contains("1")){
														rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4);
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}else{
														
														rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4);
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}
													
												}else{
													if(cont==5){
														
														String nivel1=valorNivel[1];
														String nivel2=valorNivel[2];
														String nivel3=valorNivel[3];
														String nivel4=valorNivel[4];
														String nivel5=valorNivel[5];
														if(nivel1.contains("1")){
															rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5);
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println(" naturaleza"+rs1.getString(8));
																		if(rs1.getString(8).equals("Debito")){
																			saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																			String sal=Long.toString(saldo);
																		//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																		}else{
																			saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																			String sal=Long.toString(saldo);
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td></tr>");
																		}
																		}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}else{}
															
													}	
												}
											}
										}
									}
								
								long stotal=0;
								String st="";
								if(rs.getString(8).equals("Debito")){
									System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
									stotal=((rs.getLong(5))-(rs.getLong(6)));
									st=Long.toString(stotal);
								}else{
									System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
									stotal=((rs.getLong(6))-(rs.getLong(5)));
									st=Long.toString(stotal);
								}
							out.print("<tr class='rep'><td></td><td align='right' > </td><td align='right' ><b> TOTAL "+nombre+" &nbsp; &nbsp; </b> <u><b> "+FormatMoneda(st)+"</b></u></td></tr>");
							
								
							}
							rs.getStatement().getConnection().close();
							rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1");
							long parcialCta567=0;
							long Cta4=0;
							long Cta567=0;
							while(rs.next()){
								if(rs.getString(9).equals("4")){
									if(rs.getString(8).equals("Debito")){
										Cta4=((rs.getLong(5))-(rs.getLong(6)));
									}else{
										Cta4=((rs.getLong(6))-(rs.getLong(5)));
									}
								}else{
									
									if(rs.getString(8).equals("Debito")){
										parcialCta567=((rs.getLong(5))-(rs.getLong(6)));
									}else{
										parcialCta567=((rs.getLong(6))-(rs.getLong(5)));
									}
									System.out.println("parcial Cta567"+parcialCta567);
									Cta567=Cta567+parcialCta567;
								}
							}
							rs.getStatement().getConnection().close();
							long Util=(Cta4-Cta567);
							String TotalUtil=Long.toString(Util);
							out.print("<tr class='contpre' ><td></td><td align='left'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtil)+"</font></td></tr>");
							out.print("<tr><td colspan='3' align='center'><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					
			}else{
				if(pg.equals("2")){
					out.print("<br><br><table width='70%'>");
					out.print("<tr class='contpre'><td></td><td  align='center' colspan='3'> ESTADO DE RESULTADOS ACUMULADO DEL PERIODO "+MC+" DEL A�O "+AC+" </td></tr>");
					out.print("<tr class='contpre'><td></td><td  align='left' > Fecha de Generacion: "+Fecha+"</td><td  align='center'> Hora de Generacion: "+Hora+"</td></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					out.print("<tr class='rep' ><b><td >&nbsp; CUENTA </td><td >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO CUENTA &nbsp;</td></b></tr>");
					out.print("<tr><td colspan='3'><hr></td>");
					
					if(cont>0){
						//String nivel=valorNivel[1];
						rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1");
						try {
							while(rs.next()){
								cuenta=rs.getString(1);
								rs1=mpr.BuscarCuenta(cuenta);
								while(rs1.next()){
									nombre=rs1.getString(3);
								out.print("<tr class='rep'><td align='left' ><b>"+cuenta+"</b></td><td  align='left'><b>"+rs1.getString(3)+"</b></td><td ></td></tr>");
								}
								rs1.getStatement().getConnection().close();
								if(cont==1){
										String nivel=valorNivel[1];
										if(nivel.contains("1")){
											
										}else{
											rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel);
											while(rs2.next()){
												String cuenta1=rs2.getString(1);
												rs3=mpr.BuscarCuenta(cuenta1);
												while(rs3.next()){
													if(cuenta.equals(rs2.getString(9))){
														//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(rs2.getString(7))+"</td></tr>");
													}
												}
												rs3.getStatement().getConnection().close();
											}
											rs2.getStatement().getConnection().close();
										}
										
										
									}else{
										if(cont==2){
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											if(nivel1.contains("1")){
												rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2);
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}else{
												
												rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2);
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}
										}else{
											if(cont==3){
																						
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3);
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
															//	System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{
													
													rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3);
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}
											
											}else{
												if(cont==4){
													
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													String nivel4=valorNivel[4];
													if(nivel1.contains("1")){
														rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4);
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}else{
														
														rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4);
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}
													
												}else{
													if(cont==5){
														
														String nivel1=valorNivel[1];
														String nivel2=valorNivel[2];
														String nivel3=valorNivel[3];
														String nivel4=valorNivel[4];
														String nivel5=valorNivel[5];
														if(nivel1.contains("1")){
															rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5);
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}else{}
															
													}	
												}
											}
										}
									}
							out.print("<tr class='rep'><td></td><td align='right' > </td><td align='right' ><b> TOTAL "+nombre+" &nbsp; &nbsp; </b> <u><b> "+FormatMoneda(rs.getString(7))+"</b></u></td></tr>");
							
								
							}
							rs.getStatement().getConnection().close();
							rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1");
							long Cta4=0;
							long Cta567=0;
							while(rs.next()){
								if(rs.getString(9).equals("4")){
									Cta4=rs.getLong(7);
								}else{
									Cta567=Cta567+rs.getLong(7);
									System.out.println("Valor de cta567"+Cta567);
								}
							}
							rs.getStatement().getConnection().close();
							System.out.println("Valor Cta4"+Cta4);
							long Util=(Cta4-Cta567);
							System.out.println(Util);
							String TotalUtil=Long.toString(Util);
							out.print("<tr class='contpre' ><td></td><td align='left'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtil)+"</font></td></tr>");
							out.print("<tr><td colspan='3' align='center'><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}else{
					if(pg.equals("3")){
						
						out.print("<br><br><table width='80%' >");
						out.print("<tr class='contpre'><td  align='center' colspan='4'> ESTADO DE RESULTADOS MENSUAL Y ACUMULADO DE DEL PERIODO "+MC+" DEL A�O "+AC+" </td></tr>");
						out.print("<tr class='contpre'><td></td><td  align='left' > Fecha de Generacion: "+Fecha+"</td><td></td><td  align='left'> Hora de Generacion: "+Hora+"</td><td align></td></tr>");
						out.print("<tr><td colspan='4'><hr></td>");
						out.print("<tr class='rep' ><b><td >&nbsp; CUENTA </td><td >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; MES &nbsp;</td><td>&nbsp; ACUMULADO &nbsp;</td></b></tr>");
						out.print("<tr><td colspan='4'><hr></td>");
						
						if(cont>0){
							//String nivel=valorNivel[1];
							rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1");
							try {
								while(rs.next()){
									cuenta=rs.getString(1);
									rs1=mpr.BuscarCuenta(cuenta);
									while(rs1.next()){
										nombre=rs1.getString(3);
									out.print("<tr class='rep'><td align='left' ><b>"+cuenta+"</b></td><td  align='left'><b>"+rs1.getString(3)+"</b></td><td ></td></tr>");
									}
									rs1.getStatement().getConnection().close();
									if(cont==1){
											String nivel=valorNivel[1];
											if(nivel.contains("1")){
												
											}else{
												rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel);
												while(rs2.next()){
													String cuenta1=rs2.getString(1);
													rs3=mpr.BuscarCuenta(cuenta1);
													while(rs3.next()){
														if(cuenta.equals(rs2.getString(9))){
															System.out.println(" naturaleza"+rs2.getString(8));
															if(rs2.getString(8).equals("Debito")){
																
																saldo=((rs2.getLong(5))-(rs2.getLong(6)));
																String sal=Long.toString(saldo);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs2.getString(7))+"</td></tr>");
															}else{
																saldo=((rs2.getLong(6))-(rs2.getLong(5)));
																String sal=Long.toString(saldo);
															//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs2.getString(7))+"</td></tr>");
															}
															}
													}
													rs3.getStatement().getConnection().close();
												}
												rs2.getStatement().getConnection().close();
											}
											
											
										}else{
											if(cont==2){
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2);
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																	//System.out.println("valor de debito "+rs2.getLong(5)+" valor de credito"+rs2.getLong(6)+" valor de la diferencia"+sal);
																	//String()
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}else{
																	System.out.println((rs1.getLong(6))+"-"+(rs1.getLong(5)));
																	saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																	String sal=Long.toString(saldo);
																	//System.out.println("valor de credito "+rs2.getLong(6)+" valor de debito"+rs2.getLong(5)+" valor de la diferencia"+sal);
																//	System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																}
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{
													
													rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2);
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println(" naturaleza"+rs1.getString(8));
																if(rs1.getString(8).equals("Debito")){
																	saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																	String sal=Long.toString(saldo);
																//System.out.println("valor de debito "+rs1.getLong(5)+" valor de credito"+rs1.getLong(6)+" valor de la diferencia"+sal);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}else{
																saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																String sal=Long.toString(saldo);
																//System.out.println("valor de credito "+rs1.getLong(6)+" valor de debito"+rs1.getLong(5)+" valor de la diferencia"+sal);
																//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}
											}else{
												if(cont==3){
																							
													String nivel1=valorNivel[1];
													String nivel2=valorNivel[2];
													String nivel3=valorNivel[3];
													if(nivel1.contains("1")){
														rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3);
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}else{
														
														rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3);
														while(rs1.next()){
															String cuenta1=rs1.getString(1);
															rs2=mpr.BuscarCuenta(cuenta1);
															while(rs2.next()){
																if(cuenta.equals(rs1.getString(9))){
																	System.out.println(" naturaleza"+rs1.getString(8));
																	if(rs1.getString(8).equals("Debito")){
																		saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																		String sal=Long.toString(saldo);
																	//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																	out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}else{
																		saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																		String sal=Long.toString(saldo);
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																	}
																	}
															}rs2.getStatement().getConnection().close();
														}rs1.getStatement().getConnection().close();
													}
												
												}else{
													if(cont==4){
														
														String nivel1=valorNivel[1];
														String nivel2=valorNivel[2];
														String nivel3=valorNivel[3];
														String nivel4=valorNivel[4];
														if(nivel1.contains("1")){
															rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4);
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println(" naturaleza"+rs1.getString(8));
																		if(rs1.getString(8).equals("Debito")){
																			saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																			String sal=Long.toString(saldo);
																		//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}else{
																			saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																			String sal=Long.toString(saldo);
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}
																		}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}else{
															
															rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4);
															while(rs1.next()){
																String cuenta1=rs1.getString(1);
																rs2=mpr.BuscarCuenta(cuenta1);
																while(rs2.next()){
																	if(cuenta.equals(rs1.getString(9))){
																		System.out.println(" naturaleza"+rs1.getString(8));
																		if(rs1.getString(8).equals("Debito")){
																			saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																			String sal=Long.toString(saldo);
																		//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																		out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}else{
																			saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																			String sal=Long.toString(saldo);
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																		}
																		}
																}rs2.getStatement().getConnection().close();
															}rs1.getStatement().getConnection().close();
														}
														
													}else{
														if(cont==5){
															
															String nivel1=valorNivel[1];
															String nivel2=valorNivel[2];
															String nivel3=valorNivel[3];
															String nivel4=valorNivel[4];
															String nivel5=valorNivel[5];
															if(nivel1.contains("1")){
																rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5);
																while(rs1.next()){
																	String cuenta1=rs1.getString(1);
																	rs2=mpr.BuscarCuenta(cuenta1);
																	while(rs2.next()){
																		if(cuenta.equals(rs1.getString(9))){
																			System.out.println(" naturaleza"+rs1.getString(8));
																			if(rs1.getString(8).equals("Debito")){
																				saldo=((rs1.getLong(5))-(rs1.getLong(6)));
																				String sal=Long.toString(saldo);
																			//System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																			out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																			}else{
																				saldo=((rs1.getLong(6))-(rs1.getLong(5)));
																				String sal=Long.toString(saldo);
																				out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left' > &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right' >"+FormatMoneda(sal)+"</td><td align='right' >"+FormatMoneda(rs1.getString(7))+"</td></tr>");
																			}
																			}
																	}rs2.getStatement().getConnection().close();
																}rs1.getStatement().getConnection().close();
															}else{}
																
														}	
													}
												}
											}
										}
									
									long stotal=0;
									String st="";
									if(rs.getString(8).equals("Debito")){
										System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
										stotal=((rs.getLong(5))-(rs.getLong(6)));
										st=Long.toString(stotal);
									}else{
										System.out.println("Totalizacion "+(rs.getLong(5))+"-"+(rs.getLong(6)));
										stotal=((rs.getLong(6))-(rs.getLong(5)));
										st=Long.toString(stotal);
									}
								out.print("<tr class='rep'><td></td><td align='right' > </td><td align='right' ><b> TOTAL "+nombre+" &nbsp; &nbsp; </b> <u><b> "+FormatMoneda(st)+"</b></u></td><td align='right' > <u><b>"+FormatMoneda(rs.getString(7))+" </u><b></td></tr>");
								
									
								}
								rs.getStatement().getConnection().close();
								rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1");
								long parcialCta567=0;
								long Cta4=0;
								long Cta567=0;
								long Cta4A=0;
								long Cta567A=0;
								while(rs.next()){
									if(rs.getString(9).equals("4")){
										if(rs.getString(8).equals("Debito")){
											Cta4=((rs.getLong(5))-(rs.getLong(6)));
										}else{
											Cta4=((rs.getLong(6))-(rs.getLong(5)));
										}
										Cta4A=rs.getLong(7);
									}else{
										
										if(rs.getString(8).equals("Debito")){
											parcialCta567=((rs.getLong(5))-(rs.getLong(6)));
										}else{
											parcialCta567=((rs.getLong(6))-(rs.getLong(5)));
										}
										System.out.println("parcial Cta567"+parcialCta567);
										Cta567=Cta567+parcialCta567;
										Cta567A=Cta567A+rs.getLong(7);
									}
								}
								rs.getStatement().getConnection().close();
								long Util=(Cta4-Cta567);
								long UtilA=(Cta4A-Cta567A);
								String TotalUtil=Long.toString(Util);
								String TotalUtilA=Long.toString(UtilA);
								out.print("<tr class='contpre' ><td></td><td align='left'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtil)+"</font></td><td align='right'><br> <font style='text-decoration: overline underline;'>"+FormatMoneda(TotalUtilA)+"</font></td></tr>");
								out.print("<tr><td colspan='4' align='center'><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}//CIERRE DE PG3
						
				}
			}
			
	}
			
			
		
		
		if(va.equals("RepAuxiliarCT")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String MC2=req.getParameter("MC2");
			String AC=req.getParameter("AC");
			String TDOC=req.getParameter("TDOC");
			String TERC=req.getParameter("TERC"); 
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			out.print("<br><br><table width='90%'><tr class='contpre'><td colspan='7' align='left'>AUXILIAR DE CONTABILIDAD POR CUENTA Y TERCERO</td><td colspan='2' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='7' align='left'>CORRESPONDIENTE A PERIODO "+MC+" AL "+MC2+" DEL A�O "+AC+"</td><td colspan='2' align='rigth'>&nbsp; Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			out.print("<tr class='contpre' ><br><br><b><td>&nbsp; FECHA &nbsp;</td><td>&nbsp; TIPO DOC. &nbsp;</td><td>&nbsp; SUCURSAL &nbsp;</td><td>&nbsp; C. COSTO &nbsp;</td><td>&nbsp; SUB. C. COSTO &nbsp;</td><td>&nbsp; TERCERO &nbsp;</td><td>&nbsp; DESCRIPCION DEL DOCUMENTO &nbsp;</td><td>&nbsp; DOC. REF &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; PARCIAL &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			//rs=mpr.BuscarSaldoCuenT(RC1,RC2,AC,MC,TERC,TDOC,MC2);
			long sumdeb=0;
			long sumcre=0;
			long parcial=0;
			long sa=0;
			float cont2=0;
			int cont=0;
			String  cuenta="";
			rs2=mpr.BuscarCuentaTerc(RC1, RC2, AC, MC, TERC, TDOC,  MC2);
			try {
			while(rs2.next()){
				out.print("<tr class='contpre'><b><td >CUENTA:</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td></b></tr>");
				cuenta=rs2.getString(1);
				System.out.println("Valor de cuenta en rs2"+cuenta);
				rs=mpr.BuscarSaldoCuenT(cuenta,AC,MC,TERC,TDOC,MC2);
				while(rs.next()){
					out.print("<tr class='contpre'><td colspan='2'><br>TERCERO :</td><td colspan='2'><br>"+rs.getString(5)+"</td><td colspan='3'><br>"+rs.getString(6)+"</td><td colspan='2' align='right'><br>SALDO ANTERIOR </td><td colspan='2' align='right'><br>"+FormatMoneda(rs.getString(7))+"</td></tr>");
					// cuenta=rs.getString(1);
					 System.out.println("Valor de cuenta en rs "+cuenta);
					String tercero=rs.getString(4);
					int p=0;
					rs1=mpr.BuscarCuentaAuxDetTer(cuenta,AC,MC, MC2,TDOC,tercero);
					int verif=0;
					while(rs1.next()){
								sa=rs.getLong(7);
								String val=rs1.getString(11);
								System.out.println(" val "+val);
					
								if(val.equals("Debito")){
									if(p==0){
									parcial=(sa+(rs1.getLong(9)));
									p=1;
									}else{
									parcial=(parcial+(rs1.getLong(9)));
									}
									System.out.println("valor p"+p);
									//System.out.println("saldo anterior "+sa);
									System.out.println("Resultado de la suma con debito"+parcial);
									parcial=(parcial-(rs1.getLong(10)));
									
									System.out.println("Resultado de la resta con credito "+parcial);
								}else{
									if(p==0){
										parcial=(sa+(rs1.getLong(10)));
										p=1;
										}else{
										parcial=(parcial+(rs1.getLong(10)));
										}
									//System.out.println("Resultado de la suma con credito"+parcial);
									parcial=(parcial-(rs1.getLong(9)));
									//System.out.println("Resultado de la resta con debito "+parcial);
								}
								
								sumdeb=((rs1.getLong(9))+sumdeb);
								sumcre=((rs1.getLong(10))+sumcre);
						//System.out.print("VALOR  DE PARCIAL EN LONG "+parcial);
						String parcial2=Long.toString(parcial);
					//	System.out.print("VALOR  DE PARCIAL EN String "+parcial2);
						
						out.print("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td align='right'>"+FormatMoneda(rs1.getString(9))+"</td><td align='right'>"+FormatMoneda(rs1.getString(10))+"</td><td align='right'>"+FormatMoneda(parcial2)+"</td></tr>");
						verif=1;
					}
					rs1.getStatement().getConnection().close();
					String sumdeb1=Long.toString(sumdeb);
					String sumcre1=Long.toString(sumcre);
				
						out.print("<tr><td colspan='8'></td><td colspan='3'> <hr></td>");
						out.print("<tr class='rep' ><b><td colspan='8' align='right'> TOTAL TERCERO</td><td colspan='1' align='right'> "+FormatMoneda(sumdeb1)+" </td><td colspan='1' align='right'> "+FormatMoneda(sumcre1)+" </td><td colspan='1' align='right'> "+FormatMoneda(rs.getString(8))+" </td></b></tr>");
			
					
			}
				
				rs1=mpr.BuscarSaldosAcum(cuenta,MC,MC2,AC);
				while(rs1.next()){
				out.print("<tr class='rep' ><b><td colspan='8' align='right'> TOTAL CUENTA </td><td colspan='1' align='right'> "+FormatMoneda(rs1.getString(4))+" </td><td colspan='1' align='right'> "+FormatMoneda(rs1.getString(5))+"</td><td colspan='1' align='right'> "+FormatMoneda(rs1.getString(6))+"</td></b></tr>");
				}	
				rs1.getStatement().getConnection().close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
		}
		
		if(va.equals("RepBalanceTercero")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String TERC=req.getParameter("TERC"); 
			
			System.out.println("Valor de tercero"+TERC);
			rs=mpr.BuscarSaldos(RC1, RC2, MC, AC);	
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			out.print("<br><br><table width='70%'><tr class='contpre'><td colspan='3' align='left'>BALANCE DE PRUEBA GENERAL POR CUENTA Y POR TERCERO</td><td colspan='1' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='3' align='left'>CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+"</td><td colspan='1' align='rigth'>&nbsp; Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			out.print("<tr class='contpre' ><br><br><b><td>&nbsp; CUENTA &nbsp;</td><td>&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO ANTERIOR &nbsp;</td><td>&nbsp; DEBITO &nbsp;</td><td>&nbsp; CREDITO &nbsp;</td><td>&nbsp; SALDO &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			try {
				while(rs.next()){
					
					String cuenta=rs.getString(1);
					rs1=mpr.BuscarCuentaTercero(cuenta,MC,AC,TERC);
					int val=0;
					//System.out.println("valor de val antes de entrar");
					while(rs1.next()){
						if(val==0){
						out.print("<tr class='rep'><b><td>"+rs.getString(2)+"</td><td >"+rs.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+"</td><td align='right'>"+FormatMoneda(rs.getString(6))+"</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td><td align='right'>"+FormatMoneda(rs.getString(5))+"</td></b></tr>");
						val=1;
						//System.out.print("entrando");
						}
						out.println("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td align='right'>"+FormatMoneda(rs1.getString(3))+"</td><td align='right'>"+FormatMoneda(rs1.getString(4))+"</td><td align='right'>"+FormatMoneda(rs1.getString(5))+"</td><td align='right'>"+FormatMoneda(rs1.getString(6))+"</td></tr>");
						//System.out.print("valor de val dentro del ciclo buscar cuenta tercero "+val);
						
					}
					//System.out.println("valor depues del ciclo "+val);
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</table>");
			
			
		}
		
		if(va.equals("ReporteBalanceGeneral")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			
			out.print("<br><br><table width='80%' >");
			out.print("<tr class='contpre'><td></td><td  align='left' > BALANCE GENERAL &nbsp; &nbsp;</td><td align='left'>Fecha de Generacion:</td><td >"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td></td><td  align='left' >CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+" &nbsp; &nbsp;</td><td  align='left'>Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			out.print("<tr class='rep' ><b><td >&nbsp; CUENTA </td><td >&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td colspan='2'>&nbsp; SALDO CUENTA &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			String cuenta="";
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
			long Pasivo=0;
			long Patrimonio=0;
			long TotalPP=0;
			String valp="0";
			String valpat="0";
			String nombre="";
			String TotalPaPa="0";
			if(cont>0){
				//String nivel=valorNivel[1];
				rs=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, "1");
				try {
					while(rs.next()){
						cuenta=rs.getString(1);
						rs1=mpr.BuscarCuenta(cuenta);
						while(rs1.next()){
							nombre=rs1.getString(3);
						out.print("<tr class='rep'><td align='left' ><b>"+cuenta+"</b></td><td colspan='2' align='left'><b>"+rs1.getString(3)+"</b></td><td colspan='2'></td></tr>");
						}
						rs1.getStatement().getConnection().close();
						if(cont==1){
								String nivel=valorNivel[1];
								if(nivel.contains("1")){
									
								}else{
									rs2=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel);
									while(rs2.next()){
										String cuenta1=rs2.getString(1);
										rs3=mpr.BuscarCuenta(cuenta1);
										while(rs3.next()){
											if(cuenta.equals(rs2.getString(9))){
												System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
												out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs3.getString(3)+"</td><td align='right'>"+FormatMoneda(rs2.getString(7))+"</td></tr>");
											}
										}
										rs3.getStatement().getConnection().close();
									}
									rs2.getStatement().getConnection().close();
								}
								
								
							}else{
								if(cont==2){
									String nivel1=valorNivel[1];
									String nivel2=valorNivel[2];
									if(nivel1.contains("1")){
										rs1=mpr.BuscarDatosBalanceG1(RC1, RC2, AC, MC, nivel2);
										while(rs1.next()){
											String cuenta1=rs1.getString(1);
											rs2=mpr.BuscarCuenta(cuenta1);
											while(rs2.next()){
												if(cuenta.equals(rs1.getString(9))){
													System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
													out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
												}
											}rs2.getStatement().getConnection().close();
										}rs1.getStatement().getConnection().close();
									}else{
										
										rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel1, nivel2);
										while(rs1.next()){
											String cuenta1=rs1.getString(1);
											rs2=mpr.BuscarCuenta(cuenta1);
											while(rs2.next()){
												if(cuenta.equals(rs1.getString(9))){
													System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
													out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
												}
											}rs2.getStatement().getConnection().close();
										}rs1.getStatement().getConnection().close();
									}
								}else{
									if(cont==3){
																				
										String nivel1=valorNivel[1];
										String nivel2=valorNivel[2];
										String nivel3=valorNivel[3];
										if(nivel1.contains("1")){
											rs1=mpr.BuscarDatosBalanceG2(RC1, RC2, AC, MC, nivel2,nivel3);
											while(rs1.next()){
												String cuenta1=rs1.getString(1);
												rs2=mpr.BuscarCuenta(cuenta1);
												while(rs2.next()){
													if(cuenta.equals(rs1.getString(9))){
														System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
													}
												}rs2.getStatement().getConnection().close();
											}rs1.getStatement().getConnection().close();
										}else{
											
											rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel1, nivel2,nivel3);
											while(rs1.next()){
												String cuenta1=rs1.getString(1);
												rs2=mpr.BuscarCuenta(cuenta1);
												while(rs2.next()){
													if(cuenta.equals(rs1.getString(9))){
														System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
														out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
													}
												}rs2.getStatement().getConnection().close();
											}rs1.getStatement().getConnection().close();
										}
									
									}else{
										if(cont==4){
											
											String nivel1=valorNivel[1];
											String nivel2=valorNivel[2];
											String nivel3=valorNivel[3];
											String nivel4=valorNivel[4];
											if(nivel1.contains("1")){
												rs1=mpr.BuscarDatosBalanceG3(RC1, RC2, AC, MC, nivel2,nivel3,nivel4);
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}else{
												
												rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel1, nivel2,nivel3,nivel4);
												while(rs1.next()){
													String cuenta1=rs1.getString(1);
													rs2=mpr.BuscarCuenta(cuenta1);
													while(rs2.next()){
														if(cuenta.equals(rs1.getString(9))){
															System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
															out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
														}
													}rs2.getStatement().getConnection().close();
												}rs1.getStatement().getConnection().close();
											}
											
										}else{
											if(cont==5){
												
												String nivel1=valorNivel[1];
												String nivel2=valorNivel[2];
												String nivel3=valorNivel[3];
												String nivel4=valorNivel[4];
												String nivel5=valorNivel[5];
												if(nivel1.contains("1")){
													rs1=mpr.BuscarDatosBalanceG4(RC1, RC2, AC, MC, nivel2,nivel3,nivel4, nivel5);
													while(rs1.next()){
														String cuenta1=rs1.getString(1);
														rs2=mpr.BuscarCuenta(cuenta1);
														while(rs2.next()){
															if(cuenta.equals(rs1.getString(9))){
																System.out.println("valor de cuenta "+cuenta+"valor de valor 9 de la consulta "+rs2.getString(9));
																out.print("<tr class='rep'><td align='left'> &nbsp; &nbsp;"+cuenta1+"</td><td align='left'> &nbsp; &nbsp;"+rs2.getString(3)+"</td><td align='right'>"+FormatMoneda(rs1.getString(7))+"</td></tr>");
															}
														}rs2.getStatement().getConnection().close();
													}rs1.getStatement().getConnection().close();
												}else{}
													
											}	
										}
									}
								}
							}
					out.print("<tr class='rep'><td></td><td align='right'> <b>TOTAL "+nombre+" </b></td><td colspan='2'><u><b> "+FormatMoneda(rs.getString(7))+"</b></u></td></tr>");
					
						
					}
					rs.getStatement().getConnection().close();
					rs=mpr.BuscarDatosBalanceG1("1","39", AC, MC, "1");
					while(rs.next()){
						if(rs.getString(9).equals("2")){
							valp=rs.getString(7);
							Pasivo=Long.parseLong(valp);	
						}else{
							if(rs.getString(9).equals("3")){
								valpat=rs.getString(7);
								Patrimonio=Long.parseLong(valpat);
							}
						}
						System.out.println("valor de la cuenta "+cuenta);
						System.out.println("valor  de valp"+valp);
						System.out.println("valor de  Pasivo"+Pasivo);
						TotalPP=(Pasivo+Patrimonio);
						TotalPaPa=Long.toString(TotalPP);
					}
					rs.getStatement().getConnection().close();
					rs=mpr.BuscarDatosBalanceG1("4","79", AC, MC, "1");
					long Cta4=0;
					long Cta567=0;
					while(rs.next()){
						if(rs.getString(9).equals("4")){
							Cta4=rs.getLong(7);
						}else{
							Cta567=Cta567+rs.getLong(7);
						}
					}
					rs.getStatement().getConnection().close();
					long Util=(Cta4-Cta567);
					String TotalUtil=Long.toString(Util);
					out.print("<tr class='contpre' ><td></td><td align='left'><br><b>UTILIDAD O PERDIDA DEL EJERCICIO </b></td><td align='right'><br>"+FormatMoneda(TotalUtil)+"</td></tr>");
					out.print("<tr class='contpre' ><td></td><td align='left'><b>TOTAL PATRIMONIO  </b></td><td align='right' >"+FormatMoneda(valpat)+"</td></tr>");
					out.print("<tr class='contpre' ><td></td><td align='left'><b>TOTAL PASIVO Y PATRIMONIO </b></td><td align='right' >"+FormatMoneda(TotalPaPa)+"</td><td></td></tr>");
					out.print("<tr><td colspan='3' align='center'><br><br><br><br><br><br>&nbsp; &nbsp;<font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; GERENTE GENERAL &nbsp; &nbsp; &nbsp; &nbsp;</font> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font style='text-decoration: overline;'> &nbsp; &nbsp; &nbsp; &nbsp; REVISOR FISCAL &nbsp; &nbsp; &nbsp; &nbsp;</font></td></tr>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		if(va.equals("RepBalanceGeneral")){
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			if(RC2.equals("TODAS")){
				
			}else{
				/*int rc11=Integer.parseInt(RC1);
				int rc22=Integer.parseInt(RC2);
				if(RC1.equals(RC2)){
				}else{*/
				RC2=RC2+"9";
				/*}*/
			}
			System.out.println(" valor de RC2"+RC2);
			String nivelSeleccionado=req.getParameter("nivelSeleccionado");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			System.out.println("NivelSeleccionado"+nivelSeleccionado);
			String sql="";
			String[] detnivelSeleccionado = nivelSeleccionado.split("_");
			out.print("<br><br><table>");
			out.print("<tr class='contpre'><td colspan='3'> BALANCE DE PRUEBA GENERAL</td><td colspan='2' align='left'>Fecha de Generacion:</td><td colspan='1'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='3' >CORRESPONDIENTE AL PERIODO "+MC+" DEL A�O "+AC+"</td><td colspan='2' align='left'>Hora de Generacion:</td><td colspan='1'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			out.print("<tr class='rep' ><b><td >&nbsp; CUENTA &nbsp;</td><td>&nbsp; NOMBRE DE LA CUENTA &nbsp;</td><td>&nbsp; SALDO ANTERIOR &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; SALDO NUEVO &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='6'><hr></td>");
			String cuenta="";
			String valorNivel [] = new String[6];
			int cont=0;
			for (int i = 0; i < detnivelSeleccionado.length; i++) {
				if(detnivelSeleccionado[i].equals("1")){
					valorNivel[i+1]="1";
				}else{
					if(detnivelSeleccionado[i].equals("2")){
						valorNivel[i+1]="2";
					}else{
						if(detnivelSeleccionado[i].equals("3")){
							valorNivel[i+1]="4";
						}else{
							if(detnivelSeleccionado[i].equals("4")){
								valorNivel[i+1]="6";
							}else{
								if(detnivelSeleccionado[i].equals("5")){						
								valorNivel[i+1]="8";
								}
							}
						}
				}
				
			}
				cont=cont+1;
			}
			long PreSumaDebitoC1=0;
			long PreSumaCreditoC1=0;
			long PreSumaDebitoC2=0;
			long PreSumaCreditoC2=0;
			long PreSumaSAD=0;
			long PreSumaSAC=0;
		
			if(cont>0){
				System.out.println("Valor de cont "+cont);
				if(cont==1){
				String nivel=valorNivel[1];
				System.out.println("valor de nivel"+nivel);
					rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel);
					try {
						
						while(rs.next()){
							cuenta=rs.getString(1);
							if(cuenta.equals("")){
								
							}else{
								rs1=mpr.BuscarCuenta(cuenta);
								while(rs1.next()){
									out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
									String Naturaleza=rs.getString(8);
									if(Naturaleza.equals("Debito")){
											String vald=rs.getString(5);
											long vald1=Long.parseLong(vald); 
											PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
											String valc=rs.getString(6);
											long valc1=Long.parseLong(valc);
											PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
											String valsa=rs.getString(4);
											long valsad=Long.parseLong(valsa);
											PreSumaSAD=(valsad+PreSumaSAD);
											
									}else{
										String valdd=rs.getString(5);
										long vald2=Long.parseLong(valdd);
										PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
										String valcc=rs.getString(6);
										long valc2=Long.parseLong(valcc);
										PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
										String valsa2=rs.getString(4);
										long valsac=Long.parseLong(valsa2);
										PreSumaSAC=(valsac+PreSumaSAC);
										
									}
								}
								rs1.getStatement().getConnection().close();
							}
						}
						long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
						long dif2=0;
						if(PreSumaDebitoC2<PreSumaCreditoC2){
						 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
						}else{
							dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
						}
						
						/*if((RC1.equals("todas"))&&(RC2.equals("todas"))){
							sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+")) as cuenta, cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc,empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"' GROUP BY cuenta  ORDER BY cuenta ASC ");
						}else{
							sql=("SELECT LEFT(cc.CodigoCuenta,LOCATE('',cc.CodigoCuenta,"+nivel+"))as cuenta,cc.CodigoCuenta, cc.NombreCuenta, cdasc.saldo_anterior,SUM(cdasc.debito),SUM(cdasc.credito),cdasc.saldo_nuevo, cc.NaturalezaCuenta, e.* FROM cont_cuentas cc, cont_acumulado_sucursal_centrocosto casc, cont_detalle_acumulado_surcursal_ccosto cdasc, empresa e WHERE cc.codigo=casc.cod_cuenta_fk AND casc.codigo=cdasc.cod_acu_sucur_ccosto_fk and cdasc.periodo='"+MC+"' and casc.anio='"+AC+"'and cc.codigo between '"+RC1+"' and '"+RC2+"' GROUP BY cuenta  ORDER BY cuenta ASC");
						}*/
						int ident=0;
						String PreSumaSAD1=Long.toString(PreSumaSAD);
						String PreSumaSAC1=Long.toString(PreSumaSAC);
						String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
						String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
						String dif11=Long.toString(dif1);
						String dif22=Long.toString(dif2);
						String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
						String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
						long DSA=(PreSumaSAD-PreSumaSAC);
						long DD=(PreSumaSAD-PreSumaSAC);
						long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
						long DDIF=(dif1-dif2);
						String DSA1=Long.toString(DSA);
						String DD1=Long.toString(DD);
						String DC1=Long.toString(DC);
						String DDIF1=Long.toString(DDIF);
						if(nivel.equals("1")){
						out.print("<tr><td colspan='6'> <br><br> </td></tr>");
						out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
						out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
						out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
						ident=1;
						}
						
							//out.println("<tr align='left'><td><p align='left'><input type='button' value=' Generar PDF ' onclick='verrep1("+RC1+","+RC2+","+AC+","+MC+","+nivel+","+PreSumaSAD+","+PreSumaDebitoC1+","+PreSumaCreditoC1+","+PreSumaDebitoC2+","+PreSumaCreditoC2+","+PreSumaSAC+","+dif1+","+dif2+","+ident+","+Fecha+","+Hora+")'/></p></td></tr>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					if(cont==2){
						
						String nivel1=valorNivel[1];
						String nivel2=valorNivel[2];
						rs=mpr.BuscarDatosBalanceG2(RC1,RC2,AC,MC,nivel1,nivel2);
						try {
							while(rs.next()){
								cuenta=rs.getString(1);
								if(cuenta.equals("")){
									
								}else{
									rs1=mpr.BuscarCuenta(cuenta);
									while(rs1.next()){
										out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td></tr>");
									}
									rs1.getStatement().getConnection().close();
								}
							}
							rs.getStatement().getConnection().close();
							if(nivel1.equals("1")){
							rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1);
							while(rs.next()){
								cuenta=rs.getString(1);
								if(cuenta.equals("")){ }else{
										String Naturaleza=rs.getString(8);
										if(Naturaleza.equals("Debito")){
											String vald=rs.getString(5);
											long vald1=Long.parseLong(vald); 
											PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
											String valc=rs.getString(6);
											long valc1=Long.parseLong(valc);
											PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
											String valsa=rs.getString(4);
											long valsad=Long.parseLong(valsa);
											PreSumaSAD=(valsad+PreSumaSAD);
												
										}else{
											String valdd=rs.getString(5);
											long vald2=Long.parseLong(valdd);
											PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
											String valcc=rs.getString(6);
											long valc2=Long.parseLong(valcc);
											PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
											String valsa2=rs.getString(4);
											long valsac=Long.parseLong(valsa2);
											PreSumaSAC=(valsac+PreSumaSAC);
										}
									}
									
								}
							
							long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
							long dif2=0;
							if(PreSumaDebitoC2<PreSumaCreditoC2){
							 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
							}else{
								dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
							}
							
							String PreSumaSAD1=Long.toString(PreSumaSAD);
							String PreSumaSAC1=Long.toString(PreSumaSAC);
							String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
							String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
							String dif11=Long.toString(dif1);
							String dif22=Long.toString(dif2);
							String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
							String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
							long DSA=(PreSumaSAD-PreSumaSAC);
							long DD=(PreSumaSAD-PreSumaSAC);
							long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
							long DDIF=(dif1-dif2);
							String DSA1=Long.toString(DSA);
							String DD1=Long.toString(DD);
							String DC1=Long.toString(DC);
							String DDIF1=Long.toString(DDIF);
							out.print("<tr><td colspan='6'> <br><br> </td></tr>");
							out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+"</td></tr>");
							out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
							out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
							//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						if(cont==3){
							
							String nivel1=valorNivel[1];
							String nivel2=valorNivel[2];
							String nivel3=valorNivel[3];
							rs=mpr.BuscarDatosBalanceG3(RC1,RC2,AC,MC,nivel1,nivel2,nivel3);
							try {
								while(rs.next()){
									cuenta=rs.getString(1);
									if(cuenta.equals("")){
										
									}else{
										rs1=mpr.BuscarCuenta(cuenta);
										while(rs1.next()){
											out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
										}
										rs1.getStatement().getConnection().close();
									}
								}
								rs.getStatement().getConnection().close();
								if(nivel1.equals("1")){
								rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1);
								while(rs.next()){
									cuenta=rs.getString(1);
									if(cuenta.equals("")){ }else{
											String Naturaleza=rs.getString(8);
											if(Naturaleza.equals("Debito")){
												String vald=rs.getString(5);
												long vald1=Long.parseLong(vald); 
												PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
												String valc=rs.getString(6);
												long valc1=Long.parseLong(valc);
												PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
												String valsa=rs.getString(4);
												long valsad=Long.parseLong(valsa);
												PreSumaSAD=(valsad+PreSumaSAD);		
											}else{
												String valdd=rs.getString(5);
												long vald2=Long.parseLong(valdd);
												PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
												String valcc=rs.getString(6);
												long valc2=Long.parseLong(valcc);
												PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
												String valsa2=rs.getString(4);
												long valsac=Long.parseLong(valsa2);
												PreSumaSAC=(valsac+PreSumaSAC);
											}
										}
										
									}
								
								long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
								long dif2=0;
								if(PreSumaDebitoC2<PreSumaCreditoC2){
								 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
								}else{
									dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
								}
								String PreSumaSAD1=Long.toString(PreSumaSAD);
								String PreSumaSAC1=Long.toString(PreSumaSAC);
								String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
								String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
								String dif11=Long.toString(dif1);
								String dif22=Long.toString(dif2);
								String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
								String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
								long DSA=(PreSumaSAD-PreSumaSAC);
								long DD=(PreSumaSAD-PreSumaSAC);
								long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
								long DDIF=(dif1-dif2);
								String DSA1=Long.toString(DSA);
								String DD1=Long.toString(DD);
								String DC1=Long.toString(DC);
								String DDIF1=Long.toString(DDIF);
								out.print("<tr><td colspan='6'> <br><br> </td></tr>");
								out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
								out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
								out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
								//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
								}
									rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							if(cont==4){
								
								String nivel1=valorNivel[1];
								String nivel2=valorNivel[2];
								String nivel3=valorNivel[3];
								String nivel4=valorNivel[4];
								rs=mpr.BuscarDatosBalanceG4(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4);
								try {
									while(rs.next()){
										cuenta=rs.getString(1);
										if(cuenta.equals("")){
											
										}else{
											rs1=mpr.BuscarCuenta(cuenta);
											while(rs1.next()){
												out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
											}
											rs1.getStatement().getConnection().close();
										}
									}
									rs.getStatement().getConnection().close();
									if(nivel1.equals("1")){
									rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1);
									
									while(rs.next()){
										cuenta=rs.getString(1);
										if(cuenta.equals("")){ }else{
												String Naturaleza=rs.getString(8);
												if(Naturaleza.equals("Debito")){
													String vald=rs.getString(5);
													long vald1=Long.parseLong(vald); 
													PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
													String valc=rs.getString(6);
													long valc1=Long.parseLong(valc);
													PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
													String valsa=rs.getString(4);
													long valsad=Long.parseLong(valsa);
													PreSumaSAD=(valsad+PreSumaSAD);
														
												}else{
													String valdd=rs.getString(5);
													long vald2=Long.parseLong(valdd);
													PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
													String valcc=rs.getString(6);
													long valc2=Long.parseLong(valcc);
													PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
													String valsa2=rs.getString(4);
													long valsac=Long.parseLong(valsa2);
													PreSumaSAC=(valsac+PreSumaSAC);
												}
											}
											
										}
									
									long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
									long dif2=0;
									if(PreSumaDebitoC2<PreSumaCreditoC2){
									 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
									}else{
										dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
									}
									String PreSumaSAD1=Long.toString(PreSumaSAD);
									String PreSumaSAC1=Long.toString(PreSumaSAC);
									String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
									String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
									String dif11=Long.toString(dif1);
									String dif22=Long.toString(dif2);
									String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
									String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
									long DSA=(PreSumaSAD-PreSumaSAC);
									long DD=(PreSumaSAD-PreSumaSAC);
									long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
									long DDIF=(dif1-dif2);
									String DSA1=Long.toString(DSA);
									String DD1=Long.toString(DD);
									String DC1=Long.toString(DC);
									String DDIF1=Long.toString(DDIF);
									out.print("<tr><td colspan='6'> <br><br> </td></tr>");
									out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
									out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
									out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+"</td></tr>");
									//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
									rs.getStatement().getConnection().close();
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								if(cont==5){
									System.out.println("entrando a nivel 5");
									String nivel1=valorNivel[1];
									String nivel2=valorNivel[2];
									String nivel3=valorNivel[3];
									String nivel4=valorNivel[4];
									String nivel5=valorNivel[5];
									rs=mpr.BuscarDatosBalanceG5(RC1,RC2,AC,MC,nivel1,nivel2,nivel3,nivel4,nivel5);
									try {
										while(rs.next()){
											cuenta=rs.getString(1);
											if(cuenta.equals("")){
												
											}else{
												rs1=mpr.BuscarCuenta(cuenta);
												while(rs1.next()){
													out.println("<tr align='left' class='rep'><td>"+cuenta+"</td><td>"+rs1.getString(3)+"</td><td align='right'>"+FormatMoneda(rs.getString(4))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(5))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(6))+" &nbsp;</td><td align='right'>"+FormatMoneda(rs.getString(7))+"</td>");
												}
												rs1.getStatement().getConnection().close();
											}
										}
										
										rs.getStatement().getConnection().close();
										if(nivel1.equals("1")){
										rs=mpr.BuscarDatosBalanceG1(RC1,RC2,AC,MC,nivel1);
										while(rs.next()){
											cuenta=rs.getString(1);
											if(cuenta.equals("")){ }else{
													String Naturaleza=rs.getString(8);
													if(Naturaleza.equals("Debito")){
														String vald=rs.getString(5);
														long vald1=Long.parseLong(vald); 
														PreSumaDebitoC1=(vald1+PreSumaDebitoC1);
														String valc=rs.getString(6);
														long valc1=Long.parseLong(valc);
														PreSumaCreditoC1=(valc1+PreSumaCreditoC1);
														String valsa=rs.getString(4);
														long valsad=Long.parseLong(valsa);
														PreSumaSAD=(valsad+PreSumaSAD);
															
													}else{
														String valdd=rs.getString(5);
														long vald2=Long.parseLong(valdd);
														PreSumaDebitoC2=(vald2+PreSumaDebitoC2);
														String valcc=rs.getString(6);
														long valc2=Long.parseLong(valcc);
														PreSumaCreditoC2=(valc2+PreSumaCreditoC2);
														String valsa2=rs.getString(4);
														long valsac=Long.parseLong(valsa2);
														PreSumaSAC=(valsac+PreSumaSAC);
													}
												}
												
											}
										
										long dif1=PreSumaDebitoC1-PreSumaCreditoC1;
										long dif2=0;
										if(PreSumaDebitoC2<PreSumaCreditoC2){
										 dif2=(-(PreSumaDebitoC2-PreSumaCreditoC2));
										}else{
											dif2=(PreSumaDebitoC2-PreSumaCreditoC2);
										}
										String PreSumaSAD1=Long.toString(PreSumaSAD);
										String PreSumaSAC1=Long.toString(PreSumaSAC);
										String PreSumaDebitoC11=Long.toString(PreSumaDebitoC1);
										String PreSumaCreditoC11=Long.toString(PreSumaCreditoC1);
										String dif11=Long.toString(dif1);
										String dif22=Long.toString(dif2);
										String PreSumaDebitoC22=Long.toString(PreSumaDebitoC2);
										String PreSumaCreditoC22=Long.toString(PreSumaCreditoC2);
										long DSA=(PreSumaSAD-PreSumaSAC);
										long DD=(PreSumaSAD-PreSumaSAC);
										long DC=(PreSumaCreditoC1-PreSumaCreditoC2);
										long DDIF=(dif1-dif2);
										String DSA1=Long.toString(DSA);
										String DD1=Long.toString(DD);
										String DC1=Long.toString(DC);
										String DDIF1=Long.toString(DDIF);
										out.print("<tr align='rigth' class='rep'><td colspan='2'class='contpre' ><b>TOTAL DEBITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC11)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif11)+" </td></tr>");
										out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre' ><b>TOTAL CREDITOS</b></td><td align='right'>"+FormatMoneda(PreSumaSAC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaDebitoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(PreSumaCreditoC22)+" &nbsp;</td><td align='right'>"+FormatMoneda(dif22)+" </td></tr>");
										out.print("<tr align='rigth' class='rep'><td colspan='2' class='contpre'><b>DIFERENCIA</b></td><td align='right'>"+FormatMoneda(DSA1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DD1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DC1)+" &nbsp;</td><td align='right'>"+FormatMoneda(DDIF1)+" </td></tr>");
										//out.println("<p align='left'><input type='button' value=' Generar PDF '/></p>");
										rs.getStatement().getConnection().close();
										}
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
			
			/*for (int i = 0; i < detnivelSeleccionado.length; i++) {
				System.out.println("entrando al para"+detnivelSeleccionado[i]);
				if(detnivelSeleccionado[i].equals("1")){
					rs=mpr.BuscarDatosBalance(RC1,RC2,AC,MC,1);
					try {
						while(rs.next()){
						cuenta=rs.getString(1);
						System.out.println(cuenta);
						rs1=mpr.BuscarCuenta(cuenta);
							if(rs1.next()){
								out.print("<tr><td>"+cuenta+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td>");
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}*/
			System.out.println("cadena de consulta"+sql);
			
			
		}
		
		
		if(va.equals("RepAuxiliarGeneral")){
			
			String RC1=req.getParameter("RC1");
			String RC2=req.getParameter("RC2");
			String MC=req.getParameter("MC");
			String AC=req.getParameter("AC");
			String TDOC=req.getParameter("TDOC");
			String MC2=req.getParameter("MC2");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			out.print("<br><br><table width='90%'><tr class='contpre'><td colspan='7' align='left'>AUXILIAR DE CONTABILIDAD GENERAL</td><td colspan='2' align='right'>Fecha de Generacion:</td><td colspan='2'>"+Fecha+"</td></tr>");
			out.print("<tr class='contpre'><td colspan='7' align='left'>CORRESPONDIENTE A PERIODO "+MC+" AL "+MC2+" DEL A�O "+AC+"</td><td colspan='2' align='rigth'>&nbsp; Hora de Generacion:</td><td colspan='2'>"+Hora+"</td></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			out.print("<tr class='contpre' ><br><br><b><td>&nbsp; FECHA &nbsp;</td><td>&nbsp; TIPO DOC. &nbsp;</td><td>&nbsp; SUCURSAL &nbsp;</td><td>&nbsp; C. COSTO &nbsp;</td><td>&nbsp; SUB. C. COSTO &nbsp;</td><td>&nbsp; TERCERO &nbsp;</td><td>&nbsp; DESCRIPCION DEL DOCUMENTO &nbsp;</td><td>&nbsp; DOC. REF &nbsp;</td><td>&nbsp; DEBITOS &nbsp;</td><td>&nbsp; CREDITOS &nbsp;</td><td>&nbsp; PARCIAL &nbsp;</td></b></tr>");
			out.print("<tr><td colspan='11'><hr></td></tr>");
			rs=mpr.BuscarSaldoAnt(RC1,RC2,AC,MC,MC2,TDOC); 
			String cuenta="";
			long sa=0;
			long parcial=0;
			long sumdeb=0;
			long sumcre=0;
			try {
				while(rs.next()){
					System.out.println(rs.getString(1));
					out.println("<tr><td colspan='11'><br></td></tr>");
					out.print("<tr class='contpre' ><b><td colspan='2' align='left'>CUENTA : </td><td colspan='2' align='left'>"+rs.getString(2)+"</td><td colspan='2' align='center'>"+rs.getString(3)+"</td><td colspan='2' align='right'>SALDO ANTERIOR :</td><td colspan='4' align='right'>"+FormatMoneda(rs.getString(4))+"</td></b><tr>");
					out.println("<tr><td colspan='11'><br></td></tr>");
					cuenta=rs.getString(1);
					rs1=mpr.BuscarCuentaAuxDet(cuenta,AC,MC,MC2,TDOC);
					sa=rs.getLong(4);
					sumdeb=0;
					sumcre=0;
					int p=0;
					while(rs1.next()){
						String val=rs1.getString(11);
						System.out.println(" val "+val);
						if(val.equals("Debito")){
							if(p==0){
								parcial=(sa+(rs1.getLong(9)));
								p=1;
								}else{
								parcial=(parcial+(rs1.getLong(9)));
								}
								System.out.println("valor p"+p);
							System.out.println("Resultado de la suma con debito"+parcial);
							parcial=(parcial-(rs1.getLong(10)));
							System.out.println("Resultado de la resta con credito "+parcial);
						}else{
							if(p==0){
								parcial=(sa+(rs1.getLong(10)));
								p=1;
								}else{
								parcial=(parcial+(rs1.getLong(10)));
								}
							System.out.println("Resultado de la suma con credito"+parcial);
							parcial=(parcial-(rs1.getLong(9)));
							System.out.println("Resultado de la resta con debito "+parcial);
						}
						
						sumdeb=((rs1.getLong(9))+sumdeb);
						sumcre=((rs1.getLong(10))+sumcre);
						System.out.print("VALOR  DE PARCIAL EN LONG "+parcial);
						String parcial2=Long.toString(parcial);
						System.out.print("VALOR  DE PARCIAL EN String "+parcial2);
						
						out.print("<tr class='rep'><td>"+rs1.getString(1)+"</td><td>"+rs1.getString(2)+"</td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+"</td><td>"+rs1.getString(6)+"</td><td>"+rs1.getString(7)+"</td><td>"+rs1.getString(8)+"</td><td>"+FormatMoneda(rs1.getString(9))+"</td><td>"+FormatMoneda(rs1.getString(10))+"</td><td>"+FormatMoneda(parcial2)+"</td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					String sumdeb1=Long.toString(sumdeb);
					String sumcre1=Long.toString(sumcre);
					if((sumdeb!=0)||(sumcre!=0)){
						out.print("<tr><td colspan='8'></td><td colspan='3'> <hr></td>");
						out.print("<tr class='rep' ><b><td colspan='8'></td><td colspan='1' align='center'> "+FormatMoneda(sumdeb1)+" </td><td colspan='1' align='center'> "+FormatMoneda(sumcre1)+" </td><td colspan='1' align='center'> "+FormatMoneda(rs.getString(5))+" </td></b></tr>");
					}
				}rs.getStatement().getConnection().close();
				
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		


	////FIN INICIO DE LOS REPORTES DE CONTABILIDAD/////	
		
		
		if(va.equals("chc")){
			/***REPORTE CARTERA POR EMISION DETALLADO***/	
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			String mess="";
			mes=mes+1;
			if(mes==1){mess="01";}
			if(mes==2){mess="02";}
			if(mes==3){mess="03";}
			if(mes==4){mess="04";}
			if(mes==5){mess="05";}
			if(mes==6){mess="06";}
			if(mes==7){mess="07";}
			if(mes==8){mess="08";}
			if(mes==9){mess="09";}
			if(mes==10){mess="10";}
			if(mes==11){mess="11";}
			if(mes==12){mess="12";}

			try {
				out.print("<table width='100%' border='0' >");
				out.print("<tr><td>Seleccione Entidad</td>");
				
				out.print("<td><select id='cmbEntidades'><option value='Seleccione' selected='' >Seleccione</option>");
				rs=mpr.Entidades();
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				
				
				out.print("</select>Reporte Agrupado<input name='chkNit' type='checkbox' id='chkNit'/>Tipo Reporte <select id='TipoReporte'><option value='Seleccione'>Seleccione</option><option value='Emitida'>Emitida</option><option value='Radicada'>Radicada</option></select></td></tr>");				
				out.print("<tr><td>Fecha</td><td><input type='text' id='txtFechaEmision' onKeyUp='masca(this,&quot;/&quot;,patron,true)' value='"+dia+"/"+mess+"/"+anio+"' ></td></tr>");
				out.print("<tr><td colspan='2' align='center'><input type='button' onclick='GenerarCarteraHistorico()' value='Generar Reporte'></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		if(va.equals("cped")){
			/***REPORTE CARTERA POR EMISION DETALLADO***/	
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			int anio=hoy.get(java.util.Calendar.YEAR);
			int dia=hoy.get(java.util.Calendar.DATE);
			int mes=hoy.get(java.util.Calendar.MONTH);
			String mess="";
			mes=mes+1;
			if(mes==1){mess="01";}
			if(mes==2){mess="02";}
			if(mes==3){mess="03";}
			if(mes==4){mess="04";}
			if(mes==5){mess="05";}
			if(mes==6){mess="06";}
			if(mes==7){mess="07";}
			if(mes==8){mess="08";}
			if(mes==9){mess="09";}
			if(mes==10){mess="10";}
			if(mes==11){mess="11";}
			if(mes==12){mess="12";}

			try {
				out.print("<table width='100%' border='0' >");
				out.print("<tr><td>Seleccione Entidad</td>");
				
				out.print("<td><select id='cmbEntidades'><option value='Seleccione' selected='' >Seleccione</option>");
				rs=mpr.Entidades();
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+" >"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				
				
				out.print("</select>Reporte Agrupado<input name='chkNit' type='checkbox' id='chkNit'/></td></tr>");				
				out.print("<tr><td>Fecha Emision</td><td><input type='text' id='txtFechaEmision' onKeyUp='masca(this,&quot;/&quot;,patron,true)' value='"+dia+"/"+mess+"/"+anio+"' ></td></tr>");
				out.print("<tr><td colspan='2' align='center'><input type='button' onclick='GenerarCarteraDetallada()' value='Generar Reporte'></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		

		
		
		/*****************************OMITIR RECIBO DE CAJA*********************************/
		String CodRecCaja=req.getParameter("CodRecCaja");
		String CodUsuario=req.getParameter("CodUsuario");
		String MotivoAnulacion=req.getParameter("MotivoAnulacion");
		if(va.equals("OmRCT")){
			
			
			try {
				MetodoCuentas mc= new MetodoCuentas();
				String MsjOmision="";
				String Valor_Abono="";
				String CodigoFacturaA="";
				rs1=mpr.Nombreusuario(CodUsuario);
				if(rs1.next()){
					MsjOmision="RECIBO DE CAJA ANULADO POR :"+rs1.getString(1)+". MOTIVO ANULACION:"+MotivoAnulacion;
				}
				rs1.getStatement().getConnection().close();				
				mpr.OmitirReciboCajaTotal(CodRecCaja, MsjOmision);
				rs=mpr.FacturasReciboCajaAnular(CodRecCaja);
				while(rs.next()){
					Valor_Abono=rs.getString(1);
					CodigoFacturaA=rs.getString(2);					
					mpr.OmitirDetalleFacturadeReciboCajaAnulado(Valor_Abono, CodigoFacturaA, MsjOmision);
					
					/********************************************************************/						
					int valor_factura2=0;
					rs3=mc.ConsultarPrecioFactura(CodigoFacturaA);
					if(rs3.next()){
						valor_factura2=rs3.getInt(4);
					}
					rs3.getStatement().getConnection().close();					
					int Pagado=0;
					rs2=mc.ObtenerDetalleFactura(CodigoFacturaA);
					while(rs2.next()){
						Pagado=rs2.getInt(1)+Pagado;
					}
					if(valor_factura2 == Pagado){
						/***se actualiza el estado de la factura a 1 que significa que ya esta pagada***/
						mc.CerrarFactura(CodigoFacturaA,"1");
					}else{
						mc.CerrarFactura(CodigoFacturaA,"0");
					}
					rs2.getStatement().getConnection().close();					
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
					String FechFa="";
					ResultSet rsd1=null;
					ResultSet rsd2=null;
					ResultSet rsrc=null;
					
					rsrc=mc.ResumenRestanteFactura(CodigoFacturaA);
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
					System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
					if((DifPlazo<=30)){
						/**del por vencer **/
						Por_vencer=Resta;
						mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0","0","0","0",Por_vencer);
					}
					if((DifPlazo>=31)&&(DifPlazo<=60)){
						/**vencido 1-30**/
						plazo_corto=Resta;
						mc.ActualizarPlazoCartera(CodigoFacturaA,plazo_corto,"0","0","0","0","0","0");
					}
					if((DifPlazo>=61)&&(DifPlazo<=90)){
						/**vencido 31-60**/
						plazo_30=Resta;
						mc.ActualizarPlazoCartera(CodigoFacturaA,"0",plazo_30,"0","0","0","0","0");
					}
					if((DifPlazo>=91)&&(DifPlazo<=120)){
						/**vencido 61-90**/
						plazo_60=Resta;
						mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0",plazo_60,"0","0","0","0");
					}
					if((DifPlazo>=121)&&(DifPlazo<=210)){
						/**vencido 91-180**/
						plazo_90=Resta;
						mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0",plazo_90,"0","0","0");
					}
					if((DifPlazo>=211)&&(DifPlazo<=390)){
						/**vencido 181-360**/
						plazo_180=Resta;
						mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0","0",plazo_180,"0","0");
					}
					
					if(DifPlazo>=391){
						/**mayor de 360**/
						plazo_360=Resta;
						mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0","0","0",plazo_360,"0");
					}
					/***************************************************************************/
				}
				rs.getStatement().getConnection().close();
				mpr.OmitirDetallesReciboCajaTotal(CodRecCaja);
				out.print("Recibo de caja anulado.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("OmRCT1")){
			out.print("<table width='100%' border='0'><tr><td align='center'>ANULAR RECIBO DE CAJA</td></tr>");
			out.print("<tr><td align='center'>Motivo de Anulacion:<textarea id='txtMotivoAnulacion' name='txtMotivoAnulacion' cols='50' rows='5' ></textarea></td></tr>");
			out.print("<tr><td align='center'><input type='button' onclick='OmitirReciboDeCajaTotal("+CodRecCaja+")' value='Omitir Recibo de Caja'></td></tr>");
			//	CodRecCaja
			out.print("</table>");	
		}
		/**********************FIN DE OMITIR RECIBO DE CAJA*********************************/ 
		
		if(va.equals("cntc")){
			out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>CONSULTAR NOTAS CREDITO </td> </tr> " +
					" <tr><td width='16%'>Parametro</td><td colspan='2'><input name='txtParametro' type='text' id='txtParametro'  /></td> </tr> " +
					" <tr><td><input type='checkbox' name='checkbox' value='checkbox' id='nnc' /><label for='checkbox'>Numero Nota Credito</label></td><td><input type='checkbox' name='checkbox' value='checkbox' id='nf' /><label for='checkbox'>Numero factura</label></td></tr>"+
					" <tr><td colspan='3' align='center'><input name='BuscarNotaCredito' type='button' id='btnCrearGrupoCuenta' onclick='BuscarNotaCredito1()' value='Buscar Nota Credito' /></td></tr> " +
					"<tr><td colspan='3'><div id='resultado'></div></td></tr></table>");
		}
		if(va.equals("ONC")){
			String CodDetalleFactura=req.getParameter("CodDetalleFactura");
			MetodoCuentas mc = new MetodoCuentas();
			String Obs="";
			rs=mpr.Nombreusuario(CodUsuario);
			//System.out.print("CodDetalleFactura "+CodDetalleFactura+" CodUsuario "+CodUsuario);
			try {
				if(rs.next()){
					Obs="NOTA CREDITO ANULADA POR:"+rs.getString(1)+". MOTIVO ANULACION:"+MotivoAnulacion;
					mpr.OmitirNotaCredito(Obs, CodDetalleFactura);
					out.print("Anulacion Exitosa.");
				}
				rs.getStatement().getConnection().close();
				rs1=mpr.BuscarCodigoFactura(CodDetalleFactura);
				/*********************************************************************************************/
				String CodigoFacturaA="";
				if(rs1.next()){
					CodigoFacturaA=rs1.getString(2);
				}
				rs1.getStatement().getConnection().close();
				
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
				String FechFa="";
				ResultSet rsd1=null;
				ResultSet rsd2=null;
				ResultSet rsrc=null;
				
				rsrc=mc.ResumenRestanteFactura(CodigoFacturaA);
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
				System.out.println("DiaAbo "+DiaAbo+" DiaFact "+DiaFact+"DifPlazoS "+DifPlazoS);
				if((DifPlazo<=30)){
					/**del por vencer **/
					Por_vencer=Resta;
					mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0","0","0","0",Por_vencer);
				}
				if((DifPlazo>=31)&&(DifPlazo<=60)){
					/**vencido 1-30**/
					plazo_corto=Resta;
					mc.ActualizarPlazoCartera(CodigoFacturaA,plazo_corto,"0","0","0","0","0","0");
				}
				if((DifPlazo>=61)&&(DifPlazo<=90)){
					/**vencido 31-60**/
					plazo_30=Resta;
					mc.ActualizarPlazoCartera(CodigoFacturaA,"0",plazo_30,"0","0","0","0","0");
				}
				if((DifPlazo>=91)&&(DifPlazo<=120)){
					/**vencido 61-90**/
					plazo_60=Resta;
					mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0",plazo_60,"0","0","0","0");
				}
				if((DifPlazo>=121)&&(DifPlazo<=210)){
					/**vencido 91-180**/
					plazo_90=Resta;
					mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0",plazo_90,"0","0","0");
				}
				if((DifPlazo>=211)&&(DifPlazo<=390)){
					/**vencido 181-360**/
					plazo_180=Resta;
					mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0","0",plazo_180,"0","0");
				}
				
				if(DifPlazo>=391){
					/**mayor de 360**/
					plazo_360=Resta;
					mc.ActualizarPlazoCartera(CodigoFacturaA,"0","0","0","0","0",plazo_360,"0");
				}
				/***************************************************************************/

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(va.equals("ONC1")){
			String CodDetalleFactura=req.getParameter("CodDetalleFactura");
			out.print("<table width='100%' border='0'><tr><td align='center'>ANULAR NOTA CREDITO</td></tr>");
			out.print("<tr><td align='center'>Motivo de Anulacion:<textarea id='txtMotivoAnulacion' name='txtMotivoAnulacion' cols='50' rows='5' ></textarea></td></tr>");
			out.print("<tr><td align='center'><input type='button' onclick='OmitirNotaCreditoTotal("+CodDetalleFactura+")' value='Omitir Nota Credito'></td></tr>");
			//	CodRecCaja
			out.print("</table>");				
		}
		/***************************CONSULTAR NOTA CREDITO***************************/
		if(va.equals("CoNT")){
			String NumeroNotaCredito=req.getParameter("NumeroNotaCredito");
			String NumeroFactura=req.getParameter("NumeroFactura");
			if((NumeroNotaCredito.equals("0"))&&(NumeroFactura.equals("1"))){
				//esta seleccionada la opcion de busqueda por Numero Nota Credito				
				try {
					rs1=mpr.BuscarNotaCreditoNC(Parametro);
					if(rs1.next()){
						out.print("<table width='100%' border='0'><tr><td colspan='4'></td></tr>");
						out.print("<tr><td>Nota Credito</td><td>Numero Factura</td><td>Cantidad</td><td>Fecha</td><td>Accion</td></tr>");
						rs=mpr.BuscarNotaCreditoNC(Parametro);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString(6)+"</td><td><a href='#' onclick='VerReporteDetalleCobro("+rs.getString(1)+","+rs.getString(2)+")'>"+rs.getString(3)+"</a></td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td>");
							if(rs.getString(4).equals("0")){
								out.print("<td>ANULADO</td></tr>");
							}else{
								out.print("<td><a href='#' onclick='OmitirNotaCredito("+rs.getString(1)+")'>Omitir Nota</a></td></tr>");
							}
						}
						out.print("<tr><td colspan='4'><div id='AnularRCObsrv'></div></td></tr></table>");
						rs.getStatement().getConnection().close();

					}else{
						out.print("No se Encontro Registro.");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if((NumeroNotaCredito.equals("1"))&&(NumeroFactura.equals("0"))){
				//esta seleccionada la opcion de busqueda por Numero Factura				
				try {
					rs1=mpr.BuscarNotaCreditoNF(Parametro);
					if(rs1.next()){
						out.print("<table width='100%' border='0'><tr><td colspan='4'></td></tr>");
						out.print("<tr><td>Nota Credito</td><td>Numero Factura</td><td>Cantidad</td><td>Fecha</td><td>Accion</td></tr>");
						rs=mpr.BuscarNotaCreditoNF(Parametro);
						while(rs.next()){
							out.print("<tr><td>"+rs.getString(6)+"</td><td><a href='#' onclick='VerReporteDetalleCobro("+rs.getString(1)+","+rs.getString(2)+")'>"+rs.getString(3)+"</a></td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td>");
							if(rs.getString(4).equals("0")){
								out.print("<td>ANULADO</td></tr>");
							}else{
								out.print("<td><a href='#' onclick='OmitirNotaCredito("+rs.getString(1)+")'>Omitir Nota</a></td></tr>");
							}

						}
						out.print("<tr><td colspan='4'><div id='AnularRCObsrv'></div></td></tr></table>");
						rs.getStatement().getConnection().close();
					}else{
						out.print("No se encontro resultados con este parametro "+Parametro);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		/***************************CONSULTAR NOTA CREDITO***************************/
		/*********************************************************/
		if(va.equals("csu")){
			mpr.CrearSucursal(NombreSucursal, DireSucursal, Telefono1, Telefono2, Municipio, Departamento);
			out.print("Sucursal Creada");
		}
		if(va.equals("su")){
			out.print("<table width='100%' border='0'> " +
					"<tr><td colspan='6' id='cabecera2' class='style11' align='center'><p>MAESTRO DE SUCURSALES </p>      </td> " +
					" </tr><tr> <td width='9%'>Nombre</td> <td colspan='2'><input name='txtNombreSu' type='text' id='txtNombreSu' size='50' /></td> " +
					" <td width='6%' align='right'>Direccion</td> <td><input name='txtDireSuc' type='text' id='txtDireSuc' size='50' /></td> " +
					" </tr><tr><td>Departamento</td><td width='18%'><select name='cmbDepartamento' id='cmbDepartamento' onchange='Municipio()' > " +
					" <option value='Seleccione' selected='selected'>Seleccione</option> " );
			rs1=mpr.Departamentos();
			try {
				while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+" >"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print(" </select></td><td>Ciudad</td><td width='23%'> <div id='Muni'><select name='cmbMunicipio' id='cmbMunicipio'> " +
					" <option value='Seleccione' selected='selected'>Seleccione</option> " +
					" </select></div></td><td>Telefonos <input name='txtTelefono1' type='text' id='txtTelefono1' /> " +
					" -  <input name='txtTelefono2' type='text' id='txtTelefono2' /></td></tr> " +
					"<tr><td colspan='5' align='center'><input name='btnGuardarSucursales' onclick='CrearSucursal()' type='button' id='btnGuardarSucursales' value='Guardar Sucursal' /></td> " +
					" </tr></table>");
		}
		
		if(va.equals("cap")){			
			try {
				rs=mpr.BuscarAnioPeriodo(Anio, Periodo);
				if(rs.next()){
					out.print("Relacion A�o-Periodo Ya Existe.");
				}else{
					mpr.CrearAPeriodo(Anio, Periodo, Bloqueo, NombreAPeriodo,BloqueoCxP);			
					out.print("A�o Periodo Creado");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("tc")){
			out.print("<table width='100%' border='0'>");
			out.print("");
			out.print("");
			out.print("");
			out.print("");
			out.print("</table>");
			}
		if(va.equals("map")){
			
			try {
				rs=mpr.AnioPeriodoSeleccionado(CodAP);
				if(rs.next()){
					out.print(" <table width='100%' border='0'><tr><td colspan='4' id='cabecera2' class='style11' align='center'>MAESTRO A&Ntilde;OS-PERIODOS </td>  </tr> ");
					out.print(" <tr><td width='6%'>A&ntilde;o</td><td width='14%'>"+rs.getString(2)+"</td> " );
					out.print(" <td width='9%'>Bloqueo Contable</td> <td width='71%'><select name='cmbBloqueo' id='cmbBloqueo'> " );
					if(rs.getString(4).equals("0")){
						out.print(" <option value='0' selected=''>No</option>" );
						out.print(" <option value='1'>Si</option>" );
					}
					if(rs.getString(4).equals("1")){
						out.print(" <option value='1' selected=''>Si</option>" );
						out.print(" <option value='0'>No</option>" );						
					}
					out.print(" </select> Bloqueo CxP<select name='cmbBloqueoCxP' id='cmbBloqueoCxP'>" );
					if(rs.getString(6).equals("0")){
						out.print(" <option value='0' selected=''>No</option>" );
						out.print(" <option value='1'>Si</option>" );
					}
					if(rs.getString(6).equals("1")){
						out.print(" <option value='1' selected=''>Si</option>" );
						out.print(" <option value='0'>No</option>" );						
					}

					out.print(" </select></td></tr> ");
					out.print(" <tr> <td>Periodo</td> <td>"+rs.getString(3)+"</td><td>Nombre</td> " );
					out.print(" <td>"+rs.getString(5)+"</td></tr><tr> " );
					out.print(" <td colspan='4' align='center'><input name='btnCrearAPeriodo' onclick='ActualizarAP("+CodAP+")' type='button' id='btnActualizarAPeriodo' value='Actualizar A&ntilde;o Periodo' /></td> </tr></table>");

				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("acap")){
			mpr.ActualizarAnioPeriodo(CodAP, Bloqueo, BloqueoCxP);
			out.print("A�o Periodo Actualizado.");
		}
		
		if(va.equals("ap")){
			out.print("<table width='100%' border='0'> <tr><td colspan='4' id='cabecera2' class='style11' align='center'>MAESTRO A&Ntilde;OS-PERIODOS </td>  </tr> " +
					" <tr><td width='6%'>A&ntilde;o</td><td width='14%'><input name='txtAnio' type='text' id='txtAnio' size='4' maxlength='4' /></td> " +
					" <td width='9%'>Bloqueo Contable</td> <td width='71%'><select name='cmbBloqueo' id='cmbBloqueo'> " +
					" <option value='Seleccione' selected='selected'>Seleccione</option> " +
					" <option value='1'>Si</option> <option value='0'>No</option> " +
					" </select> Bloqueo CxP<select name='cmbBloqueoCxP' id='cmbBloqueoCxP'>" +
					" <option value='Seleccione' selected='selected'>Seleccione</option> " +
					" <option value='1'>Si</option> <option value='0'>No</option> " +
					"</select></td></tr> ");
			out.print("<tr> <td>Periodo</td> <td><input name='txtPeriodo' type='text' id='txtPeriodo' size='2' maxlength='2' /></td><td>Nombre</td> " +
					" <td><input name='txtNombreAPeriodo' type='text' id='txtNombreAPeriodo' size='50' /></td></tr><tr> " +
					" <td colspan='4' align='center'><input name='btnCrearAPeriodo' onclick='CrearAPeriodo()' type='button' id='btnCrearAPeriodo' value='Crear A&ntilde;o Periodo' /></td> </tr></table>");
		}
		if(va.equals("bap")){
			
			try {				
				rs=mpr.TodosAnioPeriodo();
				out.print("<table width='100%' border='0'><tr><td>Nombre</td><td>A&ntilde;o</td><td>Periodo</td><td>Bloqueo Contable</td><td>Bloqueo CxP</td></tr>");
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='MostrarAnioPeriodo("+rs.getString(1)+")'>"+rs.getString(5)+"</a></td>" +
							"<td>"+rs.getString(2)+"</td>" +
									"<td>"+rs.getString(3)+"</td>");
					if(rs.getString(4).equals("0")){
						out.print("<td>No</td>");
					}else{
						out.print("<td>Si</td>");
					}
					if(rs.getString(6).equals("0")){
						out.print("<td>No</td>");
					}else{
						out.print("<td>Si</td>");
					}
					
					out.print("</tr>");
					
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("aap")){
			out.print("<table width='100%' border='0'> <tr><td colspan='4' id='cabecera2' class='style11' align='center'>MAESTRO A&Ntilde;OS-PERIODOS </td>  </tr> " +
					" <tr><td width='6%'>A&ntilde;o</td><td width='14%'><input name='txtAnio' type='text' id='txtAnio' size='4' maxlength='4' /></td> " +
					" <td width='9%'>Bloqueo Contable</td> <td width='71%'><select name='cmbBloqueo' id='cmbBloqueo'> " +
					" <option value='Seleccione' selected='selected'>Seleccione</option> " +
					" <option value='1'>Si</option> <option value='0'>No</option> " +
					" </select> Bloqueo CxP<select name='cmbBloqueoCxP' id='cmbBloqueoCxP'>" +
					" <option value='Seleccione' selected='selected'>Seleccione</option> " +
					" <option value='1'>Si</option> <option value='0'>No</option> " +
					"</select></td></tr> ");
			out.print("<tr> <td>Periodo</td> <td><input name='txtPeriodo' type='text' id='txtPeriodo' size='2' maxlength='2' /></td><td>Nombre</td> " +
					" <td><input name='txtNombreAPeriodo' type='text' id='txtNombreAPeriodo' size='50' /></td></tr><tr> " +
					" <td colspan='4' align='center'><input name='btnCrearAPeriodo' onclick='CrearAPeriodo()' type='button' id='btnCrearAPeriodo' value='Crear A&ntilde;o Periodo' /></td> </tr></table>");
		}
		
		if(va.equals("cco")){
			out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>MAESTRO CENTRO DE COSTO </td> </tr> " +
					" <tr><td width='16%'>Nombre Centro de Costo </td><td colspan='2'><input name='txtNomCentroCosto' type='text' id='txtNomCentroCosto' size='70' /></td> </tr> " +
					" <tr><td colspan='3' align='center'><input name='btnCrearCCosto' type='button' id='btnCrearCCosto' onclick='CrearCentroCosto()' value='Crear Centro Costo' /></td></tr> " +
					"</table>");
		}
		if(va.equals("lcco")){			
			try {
				rs=mpr.ListarCentrosCosto();
				out.print("<table width='100%' border='0'><tr><td id='cabecera2' class='style11' align='center'>CENTROS DE COSTO CREADOS</td></tr>");
				out.print("<tr><td>Nombre Centro Costo</td></tr>");
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='ActualizarCCosto("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("lsco")){
			try {
				rs=mpr.ListarSubCentrosCosto();
				out.print("<table width='100%' border='0'><tr><td id='cabecera2' class='style11' align='center'>SUBCENTROS DE COSTO CREADOS</td></tr>");
				out.print("<tr><td>Nombre Subcentro Costo</td></tr>");
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='ActualizarSubCCosto("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("lcco1")){			
			try {
				rs=mpr.ListarCentrosCostoCodigo(CodCCosto);
				out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>MAESTRO CENTRO DE COSTO </td> </tr> ");
				while(rs.next()){
					out.print("<tr><td width='16%'>Nombre Centro de Costo </td><td colspan='2'><input name='txtNomCentroCosto' type='text' id='txtNomCentroCosto' size='70' value='"+rs.getString(2)+"' /></td> </tr> ");
				}
				out.print("<tr><td colspan='3' align='center'><input name='btnCrearCCosto' type='button' id='btnCrearCCosto' onclick='ActualizarCentroCosto("+CodCCosto+")' value='Actualizar Centro Costo' /></td></tr> " );
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("lsco1")){
			try {				
				rs=mpr.ListarSubCentrosCostoCodigo(CodSubCCosto);
				out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>MAESTRO SUBCENTRO DE COSTO </td> </tr> ");
				while(rs.next()){
					out.print("<tr><td width='16%'>Nombre Subentro de Costo </td><td colspan='2'><input name='txtNomSubCentroCosto' type='text' id='txtNomSubCentroCosto' size='70' value='"+rs.getString(2)+"' /></td> </tr> ");
				}
				out.print("<tr><td colspan='3' align='center'><input name='btnCrearCCosto' type='button' id='btnCrearCCosto' onclick='ActualizarSubCentroCosto("+CodSubCCosto+")' value='Actualizar Subcentro Costo' /></td></tr> " );
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("lcco1a")){	
			mpr.ActualizarCentroCosto(CodCCosto, NomCentroCosto);
		}
		if(va.equals("lsco1a")){	
			mpr.ActualizarSubCentroCosto(CodSubCCosto, SubcentroCosto);
		}
		String NombreGruCuenta=req.getParameter("NombreGruCuenta");
		String CodGrupoCuentas=req.getParameter("CodGrupoCuentas");
		if(va.equals("lgc1")){
			/*out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>MAESTRO GRUPO DE CUENTA </td> </tr> " +
					" <tr><td width='16%'>Nombre Grupo Cuenta </td><td colspan='2'><input name='txtNomGrupoCuenta' type='text' id='txtNomGrupoCuenta' size='70' /></td> </tr> " +
					" <tr><td colspan='3' align='center'><input name='btnCrearGrupoCuenta' type='button' id='btnCrearGrupoCuenta' onclick='CrearGrupoCuenta()' value='Crear Grupo Cuenta' /></td></tr> " +
					"</table>");*/
			//System.out.println("va.equals(lgc1)");
			try {
				rs=mpr.ListarGrupoCuenta(CodGrupoCuentas);
				out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>ACTUALIZAR GRUPO CUENTAS </td> </tr> ");
				while(rs.next()){
					out.print("<tr><td width='16%'>Nombre Grupo Cuenta </td><td colspan='2'><input name='txtNomGrupoCuenta' type='text' id='txtNomGrupoCuenta' size='70' value='"+rs.getString(2)+"' /></td> </tr>");
				}
				out.print("<tr><td colspan='3' align='center'><input name='btnActualizarGrupoCuenta' type='button' id='btnActualizarGrupoCuenta' onclick='ActualizarGruCuenta("+CodGrupoCuentas+")' value='Actualizar Centro Costo' /></td></tr> " );
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("lgc1a")){
			mpr.ActualizarGrupoCuenta(CodGrupoCuentas, NombreGruCuenta);
		}
		
		if(va.equals("lgc")){			
			try {
				
				rs=mpr.ListarGrupoCuenta();
				out.print("<table width='100%' border='0'><tr><td id='cabecera2' class='style11' align='center'>SUBCENTROS DE COSTO CREADOS</td></tr>");
				out.print("<tr><td>Nombre Subcentro Costo</td></tr>");
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='ActualizarGrupoCuenta("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("cgc")){
			mpr.CrearGrupoCuenta(NombreGruCuenta);
			out.print("Centro de Costo Creado");
		}
		
		if(va.equals("ccco")){
			mpr.CrearCentroCosto(NomCentroCosto);
			out.print("Centro de Costo Creado");
		}
		if(va.equals("sco")){
			out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>MAESTRO SUBCENTRO DE COSTO </td> </tr> " +
					" <tr><td width='16%'>Nombre Subentro de Costo </td><td colspan='2'><input name='txtNomSubCentroCosto' type='text' id='txtNomSubCentroCosto' size='70' /></td> </tr> " +
					" <tr><td colspan='3' align='center'><input name='btnCrearSubCCosto' type='button' id='btnCrearSubCCosto' onclick='CrearSubCentroCosto()' value='Crear Centro Costo' /></td></tr> " +
					"</table>");
		}
		if(va.equals("csco")){
			mpr.CrearSubcentroCosto(SubcentroCosto);
			out.print("Subcentro de Costo Creado");
		}
		if(va.equals("gc")){
			out.print("<table width='100%' border='0'>  <tr> <td colspan='3' id='cabecera2' class='style11' align='center'>MAESTRO GRUPO DE CUENTA </td> </tr> " +
					" <tr><td width='16%'>Nombre Grupo Cuenta </td><td colspan='2'><input name='txtNomGrupoCuenta' type='text' id='txtNomGrupoCuenta' size='70' /></td> </tr> " +
					" <tr><td colspan='3' align='center'><input name='btnCrearGrupoCuenta' type='button' id='btnCrearGrupoCuenta' onclick='CrearGrupoCuenta()' value='Crear Grupo Cuenta' /></td></tr> " +
					"</table>");
		}
		if(va.equals("1")){
			rs=mpr.BuscarProveedor(tipo_identificacion, numero_documento);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='0'><tr class='style3'> ");
					out.print("<td>NOMBRE 1<span class='style8'>*</span> </td>");
					out.print("<td><input name='txtNombre1' type='text' id='txtNombre1' onkeyup='NombreRazonSocial ()' size='60' value='"+rs.getString(4)+"' /></td> " );
					out.print("<td>NOMBRE 2 </td> ");
					out.print("<td colspan='3'><input name='txtNombre2' type='text' id='txtNombre2' onkeyup='NombreRazonSocial ()' size='60' value='"+rs.getString(5)+"' /></td></tr> " );
					out.print("<tr class='style3'> <td>APELLIDO 1<span class='style8'>*</span> </td>" );
					out.print(" <td><input name='txtApellido1' onkeyup='NombreRazonSocial ()' type='text' id='txtApellido1' size='60' value='"+rs.getString(6)+"' /></td>" );
					out.print(" <td>APELLIDO 2 </td> " );
					out.print("<td colspan='3'><input name='txtApellido2' onkeyup='NombreRazonSocial ()' type='text' id='txtApellido2' size='60' value='"+rs.getString(7)+"' /></td></tr> " );
					out.print("<tr class='style3'> <td width='12%'>NOMBRE /RAZON </td>" );
					out.print("<td width='33%'><input name='txtRazonNombre' type='text' id='txtRazonNombre' readonly='' size='60' maxlength='200' value='"+rs.getString(8)+"' ></td>" );
					out.print(" <td width='14%'>RAZON SOCIAL DIAN<span class='style8'>*</span> </td>" );
					out.print("<td colspan='3'><input name='txtRazonSocialDian' type='text' id='txtRazonSocialDian' size='60' value='"+rs.getString(9)+"' /></td></tr>");
					out.print("<tr class='style3'><td>DIRECCION<span class='style8'>*</span></td>" );
					out.print("<td><input name='txtDireccion' type='text' id='txtDireccion' size='40' maxlength='150' value='"+rs.getString(12)+"' ></td>" );
					out.print(" <td>TELEFONO 1<span class='style8'>*</span></td>" );
					out.print("<td width='11%'><input name='txtTelefono' type='text' id='txtTelefono' size='20' maxlength='50' value='"+rs.getString(13)+"' ></td>" );
					out.print("<td width='30%' colspan='2'>TELEFONO 2<input name='txtTelefonoContacto' type='text' id='txtTelefonoContacto' size='20' maxlength='50' value='"+rs.getString(15)+"' /></td></tr>");
					out.print("<tr class='style3'><td>E-MAIL</td>" );
					out.print("<td><input name='txtEmailContac' type='text' id='txtEmailContac' size='30' maxlength='60' value='"+rs.getString(16)+"' /></td>" );
					out.print(" <td>OP. EXT <select id='cmbOpExt'>");
					if(rs.getString(19).equals("No")){
						out.print("<option value='"+rs.getString(19)+"' selected=''>"+rs.getString(19)+"</option>");
						out.print("<option value='Si'>Si</option>");
					}					
					if(rs.getString(19).equals("Si")){
						out.print("<option value='"+rs.getString(19)+"' selected=''>"+rs.getString(19)+"</option>");
						out.print("<option value='No'>No</option>");
					}					
					out.print("</select></td>" );
					out.print("<td colspan='3'>");
					if(rs.getString(20).equals("0")){
						out.print("<input type='checkbox' name='checkbox' value='checkbox' id='chk' checked=true /><label for='checkbox'>Ind.Cliente " );
					}else{
						out.print("<input type='checkbox' name='checkbox' value='checkbox' id='chk' /><label for='checkbox'>Ind.Cliente " );
					}
					if(rs.getString(21).equals("0")){
						out.print("<input type='checkbox' name='checkbox2' value='checkbox' id='chk2' checked=true />Ind.Proveedor" );
					}else{
						out.print("<input type='checkbox' name='checkbox2' value='checkbox' id='chk2' />Ind.Proveedor" );
					}
					if(rs.getString(22).equals("0")){
						out.print("<input type='checkbox' name='checkbox3' value='checkbox' id='chk3' checked=true /> Ind.Empleado</label>");
					}else{					
						out.print("<input type='checkbox' name='checkbox3' value='checkbox' id='chk3' /> Ind.Empleado</label>");
					}
					out.print("</td></tr>");
					/**************************************************************/
					//out.print("<tr border='1'><td>1</td><td>2</td><td>3</td><td>4</td></tr>");
				
					/******************************************************************/
					out.print("<tr class='style3'><td>% RET.IVA</td><td> <input id='txtPorcretIva' size='2' maxlength='2' value='"+rs.getString(23)+"'  /> % RET.ICA <input id='txtPorcretIca' size='2' maxlength='2' value='"+rs.getString(24)+"'  /></td><td>DIAS DE PLAZO </td><td><input id='txtDiasPlazo' size='4' maxlength='4' value='"+rs.getString(25)+"'  /></td><td>AUTORETENEDOR </td><td><select name='cmbAutoRetenedor' id='cmbAutoRetenedor' ><option value='"+rs.getString(10)+"' selected=''>"+rs.getString(10)+"</option><option value='Si'>Si</option><option value='No'>No</option></select></td></tr>");
					out.print("<tr class='style3'><td>FECHA INGRESO</td><td> <input id='txtFechaIngreso' size='10' maxlength='10'  value='"+rs.getString(27)+"'  /> PAGINA WEB <input id='txtPagWeb' size='25' maxlength='150'  value='"+rs.getString(28)+"'  /></td><td>LINEA DE PRODUCTO </td><td><input id='txtLineaProducto' size='30' maxlength='150' value='"+rs.getString(29)+"'  /></td><td>N� CUENTA </td><td><input id='txtNumCuenta' maxlength='150' value='"+rs.getString(31)+"' /></td></tr>");
					/******************************************************************/

					/**************************************************************/
					out.print("<tr class='style3'><td>DEPARTAMENTO<span class='style8'>*</span></td><td><select name='cmbDepartamento' id='cmbDepartamento' onchange='Municipio()'>");
					out.print("<option value='"+rs.getString(17)+"' selected='selected'>"+rs.getString(17)+"</option>");
					rs1=mpr.Departamentos();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+" >"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td><td>MUNICIPIO<span class='style8'>*</span></td><td colspan='3'><div id='Muni'><select name='cmbMunicipio' id='cmbMunicipio'>");
					out.print("<option value='"+rs.getString(18)+"' selected='selected'>"+rs.getString(18)+"</option>");
					out.print("</select></div></td></tr><tr class='style3'><td>&nbsp;</td><td></td><td>&nbsp;</td><td colspan='3'>&nbsp;</td></tr>");
				
					/*******************************************************************/
					out.print("<tr class='style3'><td>BANCO</td><td>");
					rs2=mpr.BuscarBancosCodigo(rs.getString(31));
					if(rs2.next()){
						out.print("<input id='txtBanco' size='30' onkeyup='autocompletaBanco()' value='"+rs2.getString(2)+"'  />");
					}
					rs2.getStatement().getConnection().close();
					out.print(" TIPO CUENTA <select id='cmbTipoCuenta'> " );
					if(rs.getString(32).equals("1")){
						out.print("<option value='1' selected=''>Ahorro</option><option value='2'>Corriente</option> ");
					}else{
						out.print("<option value='1' >Ahorro</option><option value='2' selected=''>Corriente</option> ");
					}
					
					
					out.print("</select></td><td>ESTADO </td><td><select id='cmbEstado'>");
					if(rs.getString(26).equals("0")){
						out.print("<option value='0' selected=''>Activo</option><option value='1'>Inactivo</option> ");
					}else{
						out.print("<option value='0' >Activo</option><option value='1' selected=''>Inactivo</option> ");
					}
					
					out.print("</select></td><td></td><td></td></tr>");
					out.print("<tr class='style3'><td></td><td><div id='banco'><input type='hidden' id='txtCodBanco' value="+rs.getString(31)+" /></div></td><td></td><td></td><td></td><td></td></tr>");
					/*******************************************************************/

					out.print("<tr class='style3'>" +
							"<td colspan='6'><div align='center'><input name='btnActualizarTercero' type='button' id='btnActualizarTercero' onclick='ActualizarProveedor("+rs.getString(1)+")' value='          Actualizar          ' /></div></td></tr></table>");
					
				}
				else{
					out.print("<table width='100%' border='0'><tr class='style3'> " +
							"  <td>NOMBRE 1<span class='style8'>*</span> </td> <td><input name='txtNombre1' type='text' id='txtNombre1' onkeyup='NombreRazonSocial ()' size='60' /></td> <td>NOMBRE 2 </td> <td colspan='3'><input name='txtNombre2' type='text' id='txtNombre2' onkeyup='NombreRazonSocial ()' size='60' /></td></tr> " +
									" <tr class='style3'> <td>APELLIDO 1<span class='style8'>*</span> </td> <td><input name='txtApellido1' onkeyup='NombreRazonSocial ()' type='text' id='txtApellido1' size='60' /></td> <td>APELLIDO 2 </td> <td colspan='3'><input name='txtApellido2' onkeyup='NombreRazonSocial ()' type='text' id='txtApellido2' size='60' /></td></tr> " +
											"<tr class='style3'> <td width='12%'>NOMBRE /RAZON </td><td width='33%'><input name='txtRazonNombre' type='text' id='txtRazonNombre' readonly='' size='60' maxlength='200' ></td> <td width='14%'>RAZON SOCIAL DIAN<span class='style8'>*</span> </td><td colspan='3'><input name='txtRazonSocialDian' type='text' id='txtRazonSocialDian' size='60' /></td></tr>");
					out.print("<tr class='style3'><td>DIRECCION<span class='style8'>*</span></td><td><input name='txtDireccion' type='text' id='txtDireccion' size='40' maxlength='150' ></td> <td>TELEFONO 1<span class='style8'>*</span></td><td width='11%'><input name='txtTelefono' type='text' id='txtTelefono' size='20' maxlength='50' ></td><td width='30%' colspan='2'>TELEFONO 2<input name='txtTelefonoContacto' type='text' id='txtTelefonoContacto' size='20' maxlength='50' /></td></tr>");
					out.print("<tr class='style3'><td>E-MAIL</td><td><input name='txtEmailContac' type='text' id='txtEmailContac' size='30' maxlength='60' /></td> <td>OP. EXT <select id='cmbOpExt'><option value='No'>No</option><option value='Si'>Si</option></select></td><td colspan='3'><input type='checkbox' name='checkbox' value='checkbox' id='chk' /><label for='checkbox'>Ind.Cliente <input type='checkbox' name='checkbox2' value='checkbox' id='chk2' />Ind.Proveedor<input type='checkbox' name='checkbox3' value='checkbox' id='chk3' /> Ind.Empleado</label></td></tr> ");
				
					/******************************************************************/
					out.print("<tr class='style3'><td>% RET.IVA</td><td> <input id='txtPorcretIva' size='2' maxlength='2'  /> % RET.ICA <input id='txtPorcretIca' size='2' maxlength='2'  /></td><td>DIAS DE PLAZO </td><td><input id='txtDiasPlazo' size='4' maxlength='4'  /></td><td>AUTORETENEDOR </td><td><select name='cmbAutoRetenedor' id='cmbAutoRetenedor' ><option value='Seleccione' selected=''>Seleccione</option><option value='Si'>Si</option><option value='No'>No</option></select></td></tr>");
					out.print("<tr class='style3'><td>FECHA INGRESO</td><td> <input id='txtFechaIngreso' size='10' maxlength='10'  /> PAGINA WEB <input id='txtPagWeb' size='25' maxlength='150'  /></td><td>LINEA DE PRODUCTO </td><td><input id='txtLineaProducto' size='30' maxlength='150'  /></td><td>N� CUENTA </td><td><input id='txtNumCuenta' maxlength='150' /></td></tr>");
					/******************************************************************/
					
					out.print("<tr class='style3'><td>DEPARTAMENTO<span class='style8'>*</span></td><td><select name='cmbDepartamento' id='cmbDepartamento' onchange='Municipio()'>");
					out.print("<option value='Seleccione' selected='selected'>Seleccione</option>");
					rs1=mpr.Departamentos();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+" >"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td><td>MUNICIPIO<span class='style8'>*</span></td><td colspan='3'><div id='Muni'><select name='cmbMunicipio' id='cmbMunicipio'>");
					out.print("<option value='Seleccione' selected='selected'>Seleccione</option>");
					out.print("</select></div></td></tr><tr class='style3'><td>&nbsp;</td><td></td><td>&nbsp;</td><td colspan='3'>&nbsp;</td></tr>");
					
					/*******************************************************************/
					out.print("<tr class='style3'><td>BANCO</td><td> <input id='txtBanco' size='30' onkeyup='autocompletaBanco()'  /> TIPO CUENTA <select id='cmbTipoCuenta'><option value='Seleccione' selected='' >Seleccione</option><option value='1'>Ahorro</option><option value='2'>Corriente</option></select></td><td>ESTADO </td><td><select id='cmbEstado'><option value='Seleccione' selected='' >Seleccione</option><option value='0'>Activo</option><option value='1'>Inactivo</option></select></td><td></td><td></td></tr>");
					out.print("<tr class='style3'><td></td><td><div id='banco'></div></td><td></td><td></td><td></td><td></td></tr>");
					/*******************************************************************/
					
					out.print("<tr class='style3'><td colspan='6'><div align='center'><input name='btnGuardarTercero' type='button' id='btnGuardarTercero' onclick='GuardarProveedor()' value='          Guardar          ' /></div></td></tr></table>");
					
				}				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		String NomBanco=req.getParameter("NomBanco");
		if(va.equals("abc")){
			int con=0;
			String CodBanco="";
			String NombrBanco="";
			try {
				out.print("<table>");
				rs=mpr.BuscarBancos(NomBanco);
				while(rs.next()){
					out.print("<tr><td><a href='#' onclick='SeleccionarBanco("+rs.getString(1)+",&quot;"+rs.getString(2)+"&quot;)'>"+rs.getString(2)+"</a></td></tr>");
					NombrBanco=rs.getString(2);
					CodBanco=rs.getString(1);
					con=con+1;
					
				}
				out.print("<tr><td><input type='hidden' id='txtCont' value="+con+" />");
				//if(con==1){
					
							out.print(		"" +
							"<input type='hidden' id='txtNombBanco' value='"+NombrBanco+"' />" +
							"</td></tr>");
				
				out.print("<input type='hidden' id='txtCodBanco' value="+CodBanco+" /></table>");
			//	}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("Mu")){
			String CodDept=req.getParameter("CodDept");
			out.println("<select name='cmbMunicipio' id='cmbMunicipio'>");
			out.println("<option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mpr.Municipios(CodDept);
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(3)+"' >"+rs.getString(3)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</select>");
		}
		/*************************************************************/
		if(va.equals("1.11")){
			String txtBanco=req.getParameter("txtBanco");
			String cmbTipoCuenta=req.getParameter("cmbTipoCuenta");
			String cmbEstado=req.getParameter("cmbEstado");
			String txtPorcretIva=req.getParameter("txtPorcretIva");
			String txtFechaIngreso=req.getParameter("txtFechaIngreso");
			String txtPorcretIca=req.getParameter("txtPorcretIca");
			String txtPagWeb=req.getParameter("txtPagWeb");
			String txtDiasPlazo=req.getParameter("txtDiasPlazo");
			String txtLineaProducto=req.getParameter("txtLineaProducto");
			String cmbAutoRetenedor=req.getParameter("cmbAutoRetenedor");
			String txtNumCuenta=req.getParameter("txtNumCuenta");
			//String CodTercero=req.getParameter("CodTercero");
			mpr.ActualizarTercero(
					tipo_identificacion, numero_documento, 
					nombre_razonsoc, Nombre1, 
					Nombre2, Apellido1, 
					Apellido2, RazonSocialDian,
					OperExt, direccion, 
					telefono, telcontacto,
					emailcontac, IndCliente, 
					IndProveedor, IndEmpleado,
					Municipio, Departamento,
					txtBanco,cmbTipoCuenta,
					cmbEstado,txtPorcretIva,
					txtFechaIngreso,txtPorcretIca,
					txtPagWeb,txtDiasPlazo,
					txtLineaProducto,cmbAutoRetenedor,
					txtNumCuenta, CodTercero);
			out.print("Actualizacion Exitosa.");
		}
		if(va.equals("1.1")){

			String txtBanco=req.getParameter("txtBanco");
			String cmbTipoCuenta=req.getParameter("cmbTipoCuenta");
			String cmbEstado=req.getParameter("cmbEstado");
			String txtPorcretIva=req.getParameter("txtPorcretIva");
			String txtFechaIngreso=req.getParameter("txtFechaIngreso");
			String txtPorcretIca=req.getParameter("txtPorcretIca");
			String txtPagWeb=req.getParameter("txtPagWeb");
			String txtDiasPlazo=req.getParameter("txtDiasPlazo");
			String txtLineaProducto=req.getParameter("txtLineaProducto");
			String cmbAutoRetenedor=req.getParameter("cmbAutoRetenedor");
			String txtNumCuenta=req.getParameter("txtNumCuenta");
			try {
				mpr.CrearProveedor(tipo_identificacion, numero_documento, nombre_razonsoc, Nombre1,
						Nombre2, Apellido1, Apellido2, RazonSocialDian, OperExt, direccion,
						telefono,telcontacto,emailcontac,IndCliente,IndProveedor,IndEmpleado,Municipio,Departamento,
						txtBanco,cmbTipoCuenta,cmbEstado,txtPorcretIva,txtFechaIngreso,txtPorcretIca,
						txtPagWeb,txtDiasPlazo,txtLineaProducto,cmbAutoRetenedor,txtNumCuenta);
				
				out.print("Ingresado Con Exito.");
				
				/*String CodProveedor="";
				String CodCuen="";
				rs=mpr.BuscarCuenta(CodigoCuenta);
				if(rs.next()){
					out.print("Este Numero de Cuenta Esta Asignada a: "+rs.getString(3));
				}
				else{
					mpr.CrearProveedor(tipo_identificacion, numero_documento, nombre_razonsoc, autoretenedor, tipo_regimen, direccion, telefono, contacto, telcontacto, emailcontac);
					rs1=mpr.BuscarProveedor(tipo_identificacion, numero_documento);
					if(rs1.next()){
						CodProveedor=rs1.getString(1);
					}
					rs1.getStatement().getConnection().close();
					
					mpr.CrearCuenta(CodigoCuenta, NombreCuenta, TipoCuenta, NaturalezaCuenta, Nivel, estado,"");
					rs2=mpr.BuscarCuenta(CodigoCuenta);
					if(rs2.next()){
						CodCuen=rs2.getString(1);
					}
					rs2.getStatement().getConnection().close();
					
					mpr.RelacionProveedorCuenta(CodProveedor, CodCuen);
					
				}
				rs.getStatement().getConnection().close();*/
			} catch (Exception e) {
				out.println("Error Al Ingresar "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("1.2")){
			try {
		//	mpr.ActualizarProveedor(tipo_identificacion, numero_documento, nombre_razonsoc, autoretenedor, tipo_regimen, direccion, telefono, contacto, telcontacto, emailcontac, CodProv);
			//mpr.ActualizarCuenta(CodigoCuenta, NombreCuenta, TipoCuenta, NaturalezaCuenta, Nivel, estado, CodCuenta);
			out.print("Actualizado Con Exito.");
			} catch (Exception e) {
				out.println("Error Al Actualizar "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2")){		
			rs2=mpr.BuscarCuenta(CodigoCuenta);
			try {
				if(rs2.next()){
					out.print("<table width='100%' border='0'><tr class='style3'><td width='170' class='style3'>NOMBRE CUENTA<span class='style8'>*</span> </td><td colspan='7'><input name='txtNombreCuenta' type='text' id='txtNombreCuenta' size='65' maxlength='250' value='"+rs2.getString(3)+"' /><input name='txtCodCuenta' type='hidden' id='txtCodCuenta' value='"+rs2.getString(1)+"' /></td></tr>");
					out.print("<tr class='style3'><td class='style3'>TIPO<span class='style8'>*</span></td><td width='151'><select name='cmbTipoCuenta' id='cmbTipoCuenta'>");
					if(rs2.getString(4).equals("Mayor")){
						out.print("<option value="+rs2.getString(4)+">"+rs2.getString(4)+"</option>");
						out.print("<option value='Auxiliar'>Auxiliar</option>");
					}
					if(rs2.getString(4).equals("Auxiliar")){
						out.print("<option value="+rs2.getString(4)+">"+rs2.getString(4)+"</option>");
						out.print("<option value='Mayor'>Mayor</option>");
					}
					out.print("</select></td><td width='148'>NATURALEZA<span class='style8'>*</span></td><td width='122'><select name='cmbNaturalezaCuenta' id='cmbNaturalezaCuenta'>");
					if(rs2.getString(5).equals("Debito")){
						out.print("<option value="+rs2.getString(5)+">"+rs2.getString(5)+"</option>");
						out.print("<option value='Credito'>Credito</option>");
					}
					
					if(rs2.getString(5).equals("Credito")){
						out.print("<option value="+rs2.getString(5)+">"+rs2.getString(5)+"</option>");
						out.print("<option value='Debito'>Debito</option>");
					}
					
					out.print("</select></td>");
					out.print("<td width='77'>NIVEL</td><td width='121'><select name='cmbNivelCuenta' id='cmbNivelCuenta'><option value="+rs2.getString(6)+" selected>"+rs2.getString(6)+"</option>");
					out.print("<option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6 </option></select> </td>");
					out.print("<td width='96'>INDICADOR DIFERIDO<span class='style8'>*</span></td><td width='184'><select name='cmbIndDiferido' id='cmbIndDiferido'>");
				
					if(rs2.getString(10).equals("0")){
						out.print("<option value="+rs2.getString(10)+">Si</option>");
						out.print("<option value='1'>No</option>");
					}
					
					if(rs2.getString(10).equals("1")){
						out.print("<option value="+rs2.getString(10)+">No</option>");
						out.print("<option value='0'>Si</option>");
					}					
					out.print("</select></td></tr><tr class='style3'>");
					
					out.print("<td width='96'>INDICADOR BASE<span class='style8'>*</span></td><td width='184'><select name='cmbIndiBase' id='cmbIndiBase' onchange='PorcentajeBase()' >");
					if(rs2.getString(11).equals("0")){
						out.print("<option value="+rs2.getString(11)+">Si</option>");
						out.print("<option value='1'>No</option>");
						out.print("</select><div id='IB'>%<input type ='text' id='txtPorcBase' maxlength='3' size='2' value="+rs2.getString(12)+"></div></td>");
					}
					
					if(rs2.getString(11).equals("1")){
						out.print("<option value="+rs2.getString(11)+">No</option>");
						out.print("<option value='0'>Si</option>");
						out.print("</select><div id='IB'> <input type ='hidden' id='txtPorcBase' maxlength='3' size='4' value='0'></div></td>");
					}					
					
					
					out.print("<td width='96'>INDICADOR COSTO<span class='style8'>*</span></td><td width='184'><select name='cmbIndiCCosto' id='cmbIndiCCosto'>");
					if(rs2.getString(8).equals("0")){
						out.print("<option value="+rs2.getString(8)+">Si</option>");
						out.print("<option value='1'>No</option>");
					}
					
					if(rs2.getString(8).equals("1")){
						out.print("<option value="+rs2.getString(8)+">No</option>");
						out.print("<option value='0'>Si</option>");
					}					
					out.print("</select> </td>");
					
					out.print("<td width='96'>INDICADOR TERCERO<span class='style8'>*</span></td><td width='184'><select name='cmbIndTercero' id='cmbIndTercero'>");
					if(rs2.getString(8).equals("0")){
						out.print("<option value="+rs2.getString(8)+">Si</option>");
						out.print("<option value='1'>No</option>");
					}
					
					if(rs2.getString(8).equals("1")){
						out.print("<option value="+rs2.getString(8)+">No</option>");
						out.print("<option value='0'>Si</option>");
					}					
					out.print("</select> </td>");
					
					out.print("<td width='96'>ESTADO<span class='style8'>*</span></td><td width='184'><select name='cmbEstado' id='cmbEstado'>");
					if(rs2.getString(7).equals("Activo")){
						out.print("<option value="+rs2.getString(7)+">"+rs2.getString(7)+"</option>");
						out.print("<option value='Inactivo'>Inactivo</option>");
					}
					
					if(rs2.getString(7).equals("Inactivo")){
						out.print("<option value="+rs2.getString(7)+">"+rs2.getString(7)+"</option>");
						out.print("<option value='Activo'>Activo</option>");
					}					
					out.print("</select> </td></tr>");
					out.print("<tr class='style3'><td colspan='8' class='style3'><div align='center'><input name='btnActualizarCuenta' type='button' onclick='ActualizarCuenta()' value='     Actualizar     '></div></td></tr></table>");
			
				}
				else{
					String can=CodigoCuenta.substring(0,1);
					int c=CodigoCuenta.length();
					System.out.println("Numero de cuenta "+can);
					out.print("<table width='100%' border='0'><tr class='style3'><td width='173' class='style3'>NOMBRE CUENTA<span class='style8'>*</span> </td><td colspan='7'><input name='txtNombreCuenta' type='text' id='txtNombreCuenta' size='65' maxlength='250' /></td></tr>");
					out.print("<tr class='style3'><td class='style3'>TIPO<span class='style8'>*</span></td><td width='154'>" +
							"<select name='cmbTipoCuenta' id='cmbTipoCuenta'>" +
							"<option value='Seleccione' selected='selected'>Seleccione</option>" +
							"<option value='Mayor'>Mayor</option><option value='Auxiliar'>Auxiliar</option></select>    </td>" +
							"<td width='150'>NATURALEZA<span class='style8'>*</span></td><td width='124'>" +
							"<select name='cmbNaturalezaCuenta' id='cmbNaturalezaCuenta' readonly='' >");
					if((can.equals("1"))||(can.equals("5"))||(can.equals("6"))||(can.equals("7"))||(can.equals("8"))){
							out.print("<option value='Debito'>Debito</option>");
					}
					if((can.equals("2"))||(can.equals("3"))||(can.equals("4"))||(can.equals("9"))||(can.equals("8"))){
							out.print("<option value='Credito'>Credito</option>");
					}
									out.print("</select></td>");
					out.print("<td width='87'>NIVEL</td><td width='113'>" +
							"<select name='cmbNivelCuenta' id='cmbNivelCuenta'>" +
							"<option value='Seleccione' selected>Seleccione</option>" +
							"<option value='1'>1</option><option value='2'>2</option>" +
							"<option value='3'>3</option><option value='4'>4</option>" +
							"<option value='5'>5</option><option value='6'>6</option>" +
							"</select> </td>" +
							"<td width='97'>INDICADOR DIFERIDO </td><td width='191'>" +
							"<select name='cmbIndDiferido' id='cmbIndDiferido'>" +
							"<option value='Seleccione' selected='selected'>Seleccione</option>" +
							"<option value='0'>Si</option><option value='1'>No</option>" +
							"</select></td></tr>");
					out.print("<tr class='style3'><td class='style3'>INDICACOR BASE </td><td>" +
							"<select name='cmbIndiBase' id='cmbIndiBase' onchange='PorcentajeBase()'>" +
							"<option value='Seleccione' selected='selected'>Seleccione</option>" +
							"<option value='0'>Si</option>" +
							"<option value='1'>No</option>" +
							"</select><div id='IB'><input type='hidden' maxlength='3' id='txtPorcBase'>" +
							"</div></td><td>INDICADOR C.COSTO </td><td>" +
							"<select name='cmbIndiCCosto' id='cmbIndiCCosto'>" +
							"<option value='Seleccione' selected='selected'>Seleccione</option>" +
							"<option value='0'>Si</option>" +
							"<option value='1'>No</option>" +
							"</select></td>");
					out.print("<td>INDICADOR TERCERO </td><td>" +
							"<select name='cmbIndTercero' id='cmbIndTercero'>" +
							"<option value='Seleccione' selected='selected'>Seleccione</option>" +
							"<option value='0'>Si</option>" +
							"<option value='1'>No</option>" +
							"</select>  </td><td>ESTADO<span class='style8'>*</span></td><td>" +
							"<select name='cmbEstado' id='cmbEstado'>" +
							"<option value='Seleccione' selected>Seleccione</option>" +
							"<option value='Activo'>Activo</option>" +
							"<option value='Inactivo'>Inactivo</option>" +
							"</select></td></tr>");
					out.print("<tr class='style3'><td colspan='8' class='style3'><div align='center'><input name='btnGuardarCuenta' type='button' onclick='GuardarCuenta()' value='     Guardar     '></div></td></tr></table>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en ControlProveedor>>va=2 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("IB")){
			if(IndiBase.equals("0")){
				out.print("%<input type='hiden' id='txtPorcBase' size='4' >");
			}else{
				out.print("<input type='hidden' id='txtPorcBase' size='4' value='0' >");
			}
		}
		String PorcBase=req.getParameter("PorcBase");
		if(va.equals("2.1")){
			//String IndDiferido=req.getParameter("IndDiferido");			
			rs2=mpr.BuscarCuenta(CodigoCuenta);
			try {
				if(rs2.next()){
					out.print("Este Codigo de Cuenta Ya Existe.\nIntente Otravez.");
				}
				else{
					mpr.CrearCuenta(CodigoCuenta, NombreCuenta, TipoCuenta, NaturalezaCuenta, Nivel, estado,"0",IndDiferido,IndiBase,IndiCCosto,IndTercero,PorcBase);
					out.print("1");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en ControlProveedor>>va=2.1 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2.2")){
			try {
				
				mpr.ActualizarCuenta(CodigoCuenta, NombreCuenta, TipoCuenta, NaturalezaCuenta, Nivel, estado, CodCuenta,IndDiferido,IndiBase,IndiCCosto,IndTercero,PorcBase);
				out.print("Cuenta Actualizada Con Exito.");
				} catch (Exception e) {
					out.println("Error Al Actualizar "+e);
					e.printStackTrace();
				}
		}
		
		

/////////////////////////////   ANULACION DE FACTURAS  ////////////////////////////////

if(va.equals("3")){
//La idea es  consultar las facturas
out.print("<table width='100%' border='0' align='center'><tr><td  height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Consulta de Facturas</div></td></tr></table>");

//out.print("<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='60%'><i><div align='center'>Paciente</div></i></td><td width='30%'><i><div align='center'>Empresa</div></i></td><td width='10%'><i><div align='center'>Admisi�n</div></i></td></tr>");  //
out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td colspan='2' height='30' valign='top'><i><div align='center'>Criterios de Busqueda</div></i></td></tr>");
out.print("<tr></tr><tr><td width='50%'><div align='right'>Documento No:&nbsp  &nbsp  &nbsp  &nbsp  &nbsp  &nbsp</div></td><td width='50%'><input name='txtFact' type='text' id='txtFact' size='19' maxlength='100' /></td>");

//out.print("<tr><td><div align='right'>Primer Apellido:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Segundo Apellido:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
out.print("<table width='100%' border='0' class='style6' ><tr><td ><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Consultar Facturas    ' onclick='CFact()' /></div></td></tr><tr></tr><tr></tr></table>");
out.print("<table width='100%' border='0' class='style6' ><tr></tr><tr BGCOLOR='#D3D3D3' ><td colspan='4' height='30' valign='top'><i><div align='center'>Resultados de Busqueda</div></i></td></tr><tr></tr></table>");
out.print("<div id='resultadosf' style='height: 240px; overflow-y: scroll'></div>");


}


		if(va.equals("4")){
			//String sql="";
			String v1=req.getParameter("v1");
			if(v1==null){v1="";}
			int crs=0;	
			int crs2=0;	
			rs2=mpr.GeneraSQL(v1);
			try {
				if(rs2.next()){
					if(rs2.getString(16).equals("0")){
						out.print("No se puede anular el documento "+rs2.getString(1)+". Ya que el valor del documento es cero.");
					}else{							
						if(rs2.getString(9).equals("5")){out.print("El Documento ya fu� Anulado.");}else{	
							if(crs==0){
								out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. de Factura</div></i></td><td width='12%'><i><div align='center'>Empresa</div></i></td><td width='4%'><i><div align='center'>Fecha</div></i></td><td width='4%'><i><div align='center'>Usuario</div></i></td><td width='4%'><i><div align='center'>Estado</div></i></td><td width='5%'><i><div align='center'>Documento</div></i></td><td width='15%'><i><div align='center'>Nombre del Paciente</div></i></td><td width='4%'><i><div align='center'>Valor Usuario</div></i></td><td width='4%'><i><div align='center'>Valor Factura</div></i></td></tr>	");  //
							}
							String EstadoFact="";
							if(rs2.getString(9).equals("0")){EstadoFact="EMITIDA";}
							if(rs2.getString(9).equals("1")){EstadoFact="ENVIADA";}
							if(rs2.getString(9).equals("2")){EstadoFact="RADICADA";}
							if(rs2.getString(9).equals("3")){EstadoFact="GLOSADA";}
							if(rs2.getString(9).equals("4")){EstadoFact="DEVUELTA";}
							if(rs2.getString(9).equals("6")){EstadoFact="EN AUDITORIA";}
							if(rs2.getString(9).equals("7")){EstadoFact="RADICACION INTERNA";}
							if(rs2.getString(9).equals("8")){EstadoFact="REENVIO";}

							out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(10)+"</td><td>"+EstadoFact+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td><td>"+rs2.getString(13)+"</td><td>"+rs2.getString(14)+"</td></tr>");
							out.print("<table width='100%' border='0' class='style6' ><tr><div align='center'>OBSERVACI�N</div></tr><tr><td ><div align='center'><textarea id='taObservacion' name='taObservacion' cols='130' rows='7'  style='font-family: Trebuchet MS;'></textarea></div></td></tr><tr></tr><tr></tr></table>");
							if(!rs2.getString(9).equals("0")){
								out.print("No se puede anular la factura, debido a que esta ");
								if(rs2.getString(9).equals("1")){out.print("ENVIADA");}
								if(rs2.getString(9).equals("2")){out.print("RADICADA");}
								if(rs2.getString(9).equals("3")){out.print("GLOSADA");}
								if(rs2.getString(9).equals("4")){out.print("DEVUELTA");}
								if(rs2.getString(9).equals("6")){out.print("EN AUDITORIA");}
								if(rs2.getString(9).equals("7")){out.print("EN RADICACION INTERNA");}
								if(rs2.getString(9).equals("8")){out.print("PARA REENVIO");}
							}else{
								//rs=mpr.MovimientosFactura(v1);
								//if(rs.next()){
								//	out.print("La Factura no se puede anular, ya que presenta una nota credito relacionada con el siguiente numero:"+rs.getString(1));
								//}else{
									out.print("<table width='100%' border='0' class='style6' ><tr><td ><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Anular Factura    ' onclick='AnularFact(&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(12)+"&quot;,&quot;"+rs2.getString(15)+"&quot;,"+rs2.getString(11)+")' /></div></td></tr><tr></tr><tr></tr></table>");
								//}
								//rs.getStatement().getConnection().close();
								
							}
						}
						crs++;
					}
				}
				rs2.getStatement().getConnection().close();

			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			if(crs==0){///cuando no tiene moviemientos y fue solo facturada
				rs2=mpr.GeneraSQLSF(v1);
				try {
					while(rs2.next()){
						if(crs2==0){
							out.print("<table width='100%' border='0' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. de Factura</div></i></td><td width='12%'><i><div align='center'>Empresa</div></i></td><td width='4%'><i><div align='center'>Fecha</div></i></td><td width='4%'><i><div align='center'>Usuario</div></i></td><td width='4%'><i><div align='center'>Estado</div></i></td><td width='5%'><i><div align='center'>Documento</div></i></td><td width='15%'><i><div align='center'>Nombre del Paciente</div></i></td><td width='4%'><i><div align='center'>Valor Usuario</div></i></td><td width='4%'><i><div align='center'>Valor Factura</div></i></td></tr>	");  //
						}
						out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(8)+"</td><td>"+rs2.getString(10)+"</td><td>"+rs2.getString(9)+"</td><td>"+rs2.getString(3)+" "+rs2.getString(4)+"</td><td> "+rs2.getString(5)+" "+rs2.getString(6)+" "+rs2.getString(7)+"</td><td>"+rs2.getString(13)+"</td><td>"+rs2.getString(14)+"</td></tr>");
						out.print("<table width='100%' border='0' class='style6' ><tr><div align='center'>OBSERVACI�N</div></tr><tr><td ><div align='center'><textarea id='taObservacion' name='taObservacion' cols='130' rows='7'  style='font-family: Trebuchet MS;'></textarea></div></td></tr><tr></tr><tr></tr></table>");
						out.print("<table width='100%' border='0' class='style6' ><tr><td ><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Anular Factura    ' onclick='AnularFact(&quot;"+rs2.getString(1)+"&quot;,&quot;"+rs2.getString(12)+"&quot;,&quot;"+rs2.getString(15)+"&quot;,"+rs2.getString(11)+")' /></div></td></tr><tr></tr><tr></tr></table>");
						crs2++;
					}
					rs2.getStatement().getConnection().close();
	
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}

			}
			if((crs==0)&&(crs2==0)){out.print("No hay resultados para sus criterios de busqueda.");}
		}

if(va.equals("5")){
			
	java.util.Date fechaActual = new java.util.Date();
	java.sql.Date FechaServidor = new java.sql.Date(fechaActual.getTime());
	java.sql.Time HoraServidor = new java.sql.Time(fechaActual.getTime());
	
			//java.util.Date fechaS2 = new Date();
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
			
			String txtCodusuario=req.getParameter("txtCodusuario");
			String obs=req.getParameter("obs");
			String nfact=req.getParameter("nfact");
			String enca=req.getParameter("enca");
			String factn=req.getParameter("factn");
			String CodAdm=req.getParameter("CodAdm");
			String refacturar=req.getParameter("refacturar");
			String ValorFactura="";
			String CodDetalleFactura="";
			String CodigoFactura="";
			MetodoCuentas mc=new MetodoCuentas();
			try {
				rs=mc.TodosCamposFactura(nfact);
				if(rs.next()){
					ValorFactura=rs.getString(4);
					CodigoFactura=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				
				/**************************************************************/						
				mc.InsertarDetalleFactura(CodigoFactura, ValorFactura, fechacjmysql, "1", 0+"", "0", obs, FechaServidor+"", HoraServidor+"", txtCodusuario);
				rs1=mc.BuscarDetalleFacturaFechaHora(FechaServidor+"", HoraServidor+"",CodigoFactura);
				if(rs1.next()){
					CodDetalleFactura=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
				//String TipoPago=req.getParameter("TipoPago");
				//String ret_fuente="0";
				//String cod_centro_costo="0";
				mc.CrearComplementoFactura(CodigoFactura, CodDetalleFactura, "0", "0", "1");
				/***************************************************************/
				
				mpr.ActualizarEstadoCont(nfact);//Actualiza el estado a Anulada en cont_Factura
				mpr.ActualizarEstadoFactNumeradas(nfact);//Actualiza el estado a Anulada en FactNumeradas
				mpr.recordEstadoFact(factn,"5",fechacjmysql,txtCodusuario,obs);//Ingresa el Movimiento de Anulacion en Fact_movfacturas
				mpr.ActualizarEstadoDetalleFactura(enca);//actualiza el estado de los detalles de la factura a 5(anulados)
				mpr.ActualizarEstadoEncabezadoFactura(enca);//actualiza el estado del encabezado de la factura a 5(anulado)	
				if(refacturar.equals("si")){
					mpr.ActualizarEstadoAdmision(CodAdm);//actualiza el campo atendido de la admision para que vuelva a salir en sistema.
					rs1=mc.CamposEncabezado(enca);
					if(rs1.next()){
						mc.InserEmcabe(rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getString(7), rs1.getString(9), rs1.getString(10), rs1.getString(11), rs1.getString(12), rs1.getString(13), rs1.getString(14), rs1.getString(15),rs1.getString(16), rs1.getString(17), rs1.getString(18), rs1.getString(20), rs1.getString(23));
					}
					rs1.getStatement().getConnection().close();
					//verificar si en la tabla encabezado hay algun encabezado con estado=0 de la admision.
					//out.print("Factura Anulada Exitosamente.");
				}
				
			} catch (Exception e) {
			out.println("Error Al Anular Factura "+e);
			e.printStackTrace();
			}
}
////////////////////////////    FIN ANULACION DE FACTURAS   ////////////////////////////////








		
		
		
		
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


	public String FormatMoneda(String valor){		
		String temp2="";String temp1="";String temp3="";
		int ud=1;int logCad = valor.length();		
		int j=0;
		for (int i=logCad-1;i>=0;i--){			
			temp2=valor.substring(i,i+1);
			temp2+=temp1;
			//System.out.println("valor de i en fuera de condional "+i);
			if(ud==3){
				if(i!=0){
					if(valor.contains("-")){
						int resul=(logCad%2);
						//System.out.println("resul "+resul);
						if((logCad%2)==0){
							temp1="."+temp2;
						}else{
						//	System.out.println("Valor de i en condicional contains"+i);
							if(i==1){
								temp1=temp2;
								//System.out.println(" prueba");
								
							}else{
								temp1="."+temp2;
							}
							
						}
					/*if(temp2.contains("-")){
						temp1=temp2;*/
									
					}else{
						temp1="."+temp2;
					}
					//System.out.println("valor de i en condicional "+i);
					/*if(temp2.contains("-")){
						temp1=temp2;
					}
					System.out.println("valor de temp1 "+temp1);
					System.out.println("valor de temp2 "+temp2);
					System.out.println("valor de i en condicional "+i);
					temp1="."+temp2;*/
				}else{
					temp1=temp2;
					
					}
				ud=0;
			}else{
				
				temp1=temp2;
				//j=1;
				}
			//System.out.println("valor de temp1 "+temp1);
			//System.out.println("valor de temp2 "+temp2);
			ud++;
		}
		temp1=temp1+",00";
		return temp1;
	}
	/********************************************************************************/
	
}
