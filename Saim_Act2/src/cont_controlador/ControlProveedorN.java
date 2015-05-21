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
import cont_metodo.MetodoProveedorN;

public class ControlProveedorN extends HttpServlet {
	private static final long serialVersionUID = 2452236813393848348L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out = res.getWriter();
		MetodoProveedorN mpr = new MetodoProveedorN();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		/**********************************************************/
		String Tipo_identificacion = req.getParameter("Tipo_identificacion");
		String Numero_identificacion = req.getParameter("Numero_identificacion");
		String Primer_nombre = req.getParameter("Primer_nombre");
		String segundo_nombre = req.getParameter("segundo_nombre");
		String Primer_apellido = req.getParameter("Primer_apellido");
		String segundo_apellido = req.getParameter("segundo_apellido");
		String direccion = req.getParameter("direccion");
		String telefono = req.getParameter("telefono");
		String OpExt = req.getParameter("OpExt");
		String IndCliente = req.getParameter("IndCliente");
		String IndProveedor = req.getParameter("IndProveedor");
		String IndEmpleado = req.getParameter("IndEmpleado");
		String cbdep = req.getParameter("cbdep");
		String cbmun = req.getParameter("cbmun");
		String contacto = req.getParameter("contacto");
		String tel_contacto = req.getParameter("tel_contacto");
		String email_contacto = req.getParameter("email_contacto");
		String regimen = req.getParameter("regimen");
		String retIva = req.getParameter("retIva");
		String retIca = req.getParameter("retIca");
		String DiasPlazo = req.getParameter("DiasPlazo");
		String AutoRetenedor = req.getParameter("AutoRetenedor");
		String FechaIngreso = req.getParameter("FechaIngreso");
		String PagWeb = req.getParameter("PagWeb");
		String txtBanco = req.getParameter("txtBanco");
		String TipoCuenta = req.getParameter("TipoCuenta");
		String NumCuenta = req.getParameter("NumCuenta");

		String razon_social = req.getParameter("razon_social");
		String fax = req.getParameter("fax");
		String usuario = req.getParameter("usuario");
		String codtercero = req.getParameter("codtercero");
		String estado = req.getParameter("estado");

		String cmbPer = req.getParameter("cmbPer");

		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m = Integer.parseInt(mes) + 1;
		mes = String.valueOf(m);
		int d = Integer.parseInt(dia);
		if (d < 10) {
			dia = "0" + d;
		}
		if (m < 10) {
			mes = "0" + m;
		}
		String fecha = annio + "-" + mes + "-" + dia; // para la base de datos

		Calendar calendario = Calendar.getInstance();
		int hora2, minutos, segundos;
		hora2 = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra = hora2 + ":" + minutos + ":" + segundos;

		if (va.equals("1p")) {
			out.print("<table width='100%' border='0'>");
			if (cmbPer.equals("1")) {
				out.print("<tr >");
				out.print("<td>Tipo Identificacion <span class='style8'>*</span> </td>"
								+ "<td><select name='Tipo_identificacion' id='Tipo_identificacion'>"
								+ "<option value='CC'>CC</option>"
								+ "<option value='CE'>CE</option>"
								+ "</select></td>"
								+ "<td>&nbsp;&nbsp;</td>"
								+ "<td>Numero Identificacion <span class='style8'>*</span> </td><td><input name='Numero_identificacion' type='text' id='Numero_identificacion' size='60' onblur='verificar()'/></td>");
				out.print("</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>Primer Nombre <span class='style8'>*</span> </td><td><input name='Primer_nombre' type='text' id='Primer_nombre' size='60'/></td>"
								+ "<td>&nbsp;&nbsp;</td>"
								+ "<td>Segundo Nombre</td><td><input name='Segundo_nombre' type='text' id='segundo_nombre' size='60'/></td>");
				out.print("</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>Primer Apellido <span class='style8'>*</span> </td><td><input name='Primer_apellido' type='text' id='Primer_apellido' size='60'/></td>"
								+ "<td>&nbsp;&nbsp;</td>"
								+ "<td>Segundo Apellido</td><td><input name='Segundo_apellido' type='text' id='segundo_apellido' size='60'/></td>");
				out.print("</td>");
				out.print("</tr>");
			}
			if (cmbPer.equals("2")) {
				out.print("<tr>");
				out.print("<td>Tipo Identificacion</td><td>"
								+ "<select name='Tipo_identificacion' id='Tipo_identificacion'>"
								+ "<option value='NIT'>NIT</option>"
								+ "</select></td>"
								+ "<td>&nbsp;&nbsp;</td>"
								+ "<td>Numero Identificacion</td><td><input name='Numero_identificacion' type='text' id='Numero_identificacion' size='60' /></td>");
				out.print("</td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td >Razon Social <span class='style8'>*</span></td><td colspan='4'><input name='razon_social' type='text' id='razon_social' size='60'/></td>");
				out.print("</td>");
				out.print("</tr>");
			}

			out.print("<tr>");
			out.print("<td>Direccion <span class='style8'>*</span></td><td><input name='direccion' type='text' id='direccion' size='60'/></td>"
							+ "<td>&nbsp;&nbsp;</td>"
							+ "<td>Telefono: <span class='style8'>*</span></td><td><input name='telefono' type='text' id='telefono' size='30'/> &nbsp;&nbsp; "
							+ "Fax:<input name='fax' type='text' id='fax' size='19'/></td>");
			out.print("</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<td >Departamento: <span class='style8'>*</span></td>");
			out.print("<td ><label> <select name='cbdep' size='1' id='cbdep' onChange='ajaxxMun(form2)' style='width: 155px'>");
			out.print("<option value='Seleccione' selected='selected'>SELECCIONE</option>");
			rs = mpr.BuscarDpto();
			try {
				while (rs.next()) {
					out.print("<option value='" + rs.getString(1) + "'>"+ rs.getString(1) + "</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.print("</select> </label></td><td>&nbsp;&nbsp;</td>");
			out.print("<td >Municipio: &nbsp; <span class='style8'>*</span></td>");
			out.print("<td ><div id='cbmun2'><select name='cbmun' id='cbmun' style='width: 155px'>");
			out.print("<option value='Seleccione' selected='selected'>SELECCIONE</option>");
			out.print("</select> </div></td>");
			out.print("</tr>");
			out.print("<tr >"
							+ "<td>Contacto </td><td> <input id='contacto' size='30'/> </td>"
							+ "<td>&nbsp;&nbsp;</td>"
							+ "<td>Telefono Contacto </td><td><input id='tel_contacto' size='30'/></td></tr>"
							+ "<tr><td>Correo Electronico Contacto </td><td><input id='email_contacto' size='30'/></td><td>&nbsp;&nbsp;</td>");

			out.print("<td >Tipo Regimen <span class='style8'>*</span></td>");
			out.print("<td ><select name='regimen'  id='regimen'  >");
			out.print("<option selected='selected'>SELECCIONE</option>");
			rs = mpr.BuscarRegimen();
			try {
				while (rs.next()) {
					out.print("<option value='" + rs.getString(1) + "'>"+ rs.getString(1) + "</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.print("</select> </td></tr>");
			out.print("<tr >"
							+ "<td>Fecha Ingreso <span class='style8'>*</span></td><td> <input id='FechaIngreso' size='10' maxlength='10' onKeyUp='masca(this,&quot;/&quot;,patron,true)' /> &nbsp;&nbsp;"
							+ "Pagina Web <input id='PagWeb' size='10' maxlength='10' onKeyUp='masca(this,&quot;/&quot;,patron,true)' /> </td>"
							+ "<td>&nbsp;&nbsp;</td>"
							+ "<td>Dias de Plazo </td><td><input id='DiasPlazo' size='4' maxlength='4'  onkeypress='ValidaSoloNumeros()'/> &nbsp;&nbsp;&nbsp;"
							+ "</tr>");
			if (cmbPer.equals("1")) {
				out.print("<tr class='style3'>"
								+ "<td colspan='5'><div align='center'><input name='btnGuardarTercero' type='button' id='btnGuardarTercero' onclick='GuardarProveedorPN()' value='          Guardar          ' /></div></td>"
								+ "</tr>");
			}
			if (cmbPer.equals("2")) {
				out.print("<tr class='style3'>"
								+ "<td colspan='5'><div align='center'><input name='btnGuardarTercero' type='button' id='btnGuardarTercero' onclick='GuardarProveedorPJ()' value='          Guardar          ' /></div></td>"
								+ "</tr>");
			}
			out.print("</table>");
		}

		if (va.equals("1.1")) {
			String[] fec = FechaIngreso.split("/");
			String FechaIngres = fec[2] + "/" + fec[1] + "/" + fec[0];
			String razon_socia = Primer_nombre + " " + segundo_nombre + " "+ Primer_apellido + " " + segundo_apellido;
			String munnom = "";
			rs1 = mpr.CodMun(cbmun);

			try {
				if (rs1.next()) {
					munnom = rs1.getString(1);
				}
				mpr.CrearProveedor(Tipo_identificacion,Numero_identificacion, Primer_nombre,segundo_nombre, Primer_apellido,segundo_apellido, razon_socia, regimen,
								direccion, telefono, contacto, tel_contacto,email_contacto, cbdep, munnom, DiasPlazo,FechaIngres, PagWeb, fax, "0");
				System.out.println("Insert Into cont_terceros: Tipo identificacion= "+ Tipo_identificacion
								+ " ,Numero identificacion= "+ Numero_identificacion+ " ,Primer nombre="+ Primer_nombre+ " ,Segundo nombre="+ segundo_nombre
								+ " ,Primer Apellido="+ Primer_apellido+ " ,Segundo Apellido="+ segundo_apellido+ " ,razon social="+ razon_social
								+ " ,regimen="+ regimen	+ " ,direccion="+ direccion+ " ,telefono="+ telefono+ " ,contacto="	+ contacto+ " ,tel contacto="+ tel_contacto
								+ " ,email contacto="+ email_contacto+ " ,departamento="+ cbdep+ " ,municipio="+ munnom+ " ,dias plazo="+ DiasPlazo
								+ " ,fecha ingreso="+ FechaIngres+ " ,pagina web=" + PagWeb + " ,fax=" + fax);

				mpr.InsertLog(fecha, hra, usuario,"Insert Into cont_terceros: Tipo identificacion= "+ Tipo_identificacion
								+ " ,Numero identificacion= "+ Numero_identificacion + " ,Primer nombre="+ Primer_nombre + " ,Segundo nombre="+ segundo_nombre + " ,Primer Apellido="+ Primer_apellido 
								+ " ,Segundo Apellido="+ segundo_apellido + " ,razon social="+ razon_socia + " ,regimen=" + regimen+ " ,direccion=" + direccion + " ,telefono="+ telefono 
								+ " ,contacto=" + contacto+ " ,tel contacto=" + tel_contacto+ " ,email contacto=" + email_contacto+ " ,departamento=" + cbdep + " ,municipio="+ munnom 
								+ " ,dias plazo=" + DiasPlazo+ " ,fecha ingreso=" + FechaIngres	+ " ,pagina web=" + PagWeb + " ,fax=" + fax);
				out.print("Ingresado Con Exito.");
				rs1.getStatement().getConnection().close();
			} catch (Exception e) {
				out.println("Error Al Ingresar " + e);
				e.printStackTrace();
			}
		}

		if (va.equals("1.2")) {
			String[] fec = FechaIngreso.split("/");
			String FechaIngres = fec[2] + "/" + fec[1] + "/" + fec[0];
			Primer_nombre = "";
			segundo_nombre = "";
			Primer_apellido = "";
			segundo_apellido = "";
			String munnom = "";
			rs1 = mpr.CodMun(cbmun);

			try {
				if (rs1.next()) {
					munnom = rs1.getString(1);
				}
				mpr.CrearProveedor(Tipo_identificacion,Numero_identificacion, Primer_nombre,segundo_nombre, Primer_apellido,segundo_apellido, razon_social, regimen,
								direccion, telefono, contacto, tel_contacto,email_contacto, cbdep, munnom, DiasPlazo,FechaIngres, PagWeb, fax, "0");
				System.out.println("Insert Into cont_terceros: Tipo identificacion= "+ Tipo_identificacion+ " ,Numero identificacion= "+ Numero_identificacion
								+ " ,Primer nombre="+ Primer_nombre+ " ,Segundo nombre="+ segundo_nombre+ " ,Primer Apellido="+ Primer_apellido+ " ,Segundo Apellido="+ segundo_apellido
								+ " ,razon social="+ razon_social+ " ,regimen="+ regimen+ " ,direccion="+ direccion	+ " ,telefono="+ telefono
								+ " ,contacto="+ contacto+ " ,tel contacto="+ tel_contacto+ " ,email contacto="+ email_contacto+ " ,departamento="+ cbdep
								+ " ,municipio="+ munnom+ " ,dias plazo="+ DiasPlazo+ " ,fecha ingreso="+ FechaIngres+ " ,pagina web=" + PagWeb + " ,fax=" + fax);

				mpr.InsertLog(fecha, hra, usuario,"Insert Into cont_terceros: Tipo identificacion= "+ Tipo_identificacion+ " ,Numero identificacion= "+ Numero_identificacion + " ,Primer nombre="+ Primer_nombre 
								+ " ,Segundo nombre="+ segundo_nombre + " ,Primer Apellido="+ Primer_apellido + " ,Segundo Apellido="+ segundo_apellido + " ,razon social="+ razon_social + " ,regimen=" + regimen
								+ " ,direccion=" + direccion + " ,telefono="+ telefono + " ,contacto=" + contacto+ " ,tel contacto=" + tel_contacto	+ " ,email contacto=" + email_contacto+ " ,departamento=" + cbdep 
								+ " ,municipio="+ munnom + " ,dias plazo=" + DiasPlazo+ " ,fecha ingreso=" + FechaIngres+ " ,pagina web=" + PagWeb + " ,fax=" + fax);

				out.print("Ingresado Con Exito.");
				rs1.getStatement().getConnection().close();
			} catch (Exception e) {
				out.println("Error Al Ingresar " + e);
				e.printStackTrace();
			}
		}

		if (va.equals("LT")) {
			try {
				rs = mpr.ListarTodosTerceros();
				out.print("<table width='100%' border='0'><tr><td>Nit</td><td>Razon Social</td></tr>");
				while (rs.next()) {
					out.print("<tr><td>"+ rs.getString("tipo_identificacion")+ " "+ rs.getString("numero_documento")+ "</td>" +
							  "<td><a href='#' onclick='MostrarTercero(&quot;"+ rs.getString("numero_documento")+ "&quot;,&quot;" + rs.getString("codigo")+ "&quot;)'>"+ rs.getString("razon_social")+ "</a></td>" +
							  "</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (va.equals("LTCom")) {
			try {
				rs = mpr.ListarTodosTerceros();
				out.print("<table width='100%' border='0'><tr><td>Nit</td><td>Razon Social</td></tr>");
				while (rs.next()) {
					out.print("<tr><td>"+ rs.getString("tipo_identificacion")+ " "+ rs.getString("numero_documento")+ "</td>" +
							"<td><a href='#' onclick='MostrarTerceroCom(&quot;"	+ rs.getString("numero_documento")+ "&quot;,&quot;" + rs.getString("codigo")+ "&quot;)'>"+ rs.getString("razon_social")+ "</a></td>" +
							"</tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (va.equals("1")) {
			rs = mpr.BuscarProveedor(Numero_identificacion);
			try {
				if (rs.next()) {
					out.print("<table width='100%' border='0'>");
					out.print("<tr HEIGHT=25>");
					out.print("<td >TIPO IDENTIFICACION: </td><td>"+ rs.getString("tipo_identificacion") + "</td>"+ "<td>&nbsp;&nbsp;</td>"
							+ "<td>NUMERO IDENTIFICACION: </td><td>"+ rs.getString("numero_documento") + "</td>");
					out.print("</tr>");
					out.print("<tr HEIGHT=25>");
					out.print("<td >RAZON SOCIAL: </td><td colspan='4'>"+ rs.getString("razon_social") + "</td>");
					out.print("</tr>");
					out.print("<tr HEIGHT=25>");
					out.print("<td>DIRECCION: </td><td>"+ rs.getString("direccion") + "</td>"+ "<td>&nbsp;&nbsp;</td>"+ "<td>TELEFONO: </td><td>"+ rs.getString("telefono") + "</td>");
					out.print("</tr>");
					out.print("<tr HEIGHT=25>");
					out.print("<td >DEPARTAMENTO: </td>"+"<td >" + rs.getString("departamento")+ "</td><td>&nbsp;&nbsp;</td>");
					out.print("<td >MUNICIPIO: &nbsp; </td>"+"<td >" + rs.getString("ciudad") + "</td>");
					out.print("</tr>");
					out.print("<tr HEIGHT=25>" 
							+ "<td>CONTACTO: </td><td> "+ rs.getString("contacto") + "</td>"+ "<td>&nbsp;&nbsp;</td>"
							+ "<td>TELEFONO CONTACTO: </td><td>"+ rs.getString("telcontacto")+ "&nbsp;&nbsp; FAX: " + rs.getString("fax")+ "</td>" 
							+ "</tr>" 
							+ "<tr HEIGHT=25>"
							+ "<td >CORREO ELECTRONICO CONTACTO: </td><td>"+ rs.getString("emailcontac")+ "</td><td>&nbsp;&nbsp;</td>");
					out.print("<td >TIPO REGIMEN: </td>"+"<td >" + rs.getString("tipo_regimen")	+ "</td>"
							+ "</tr>");
					out.print("<tr HEIGHT=25>"
							+ "<td>% RET.IVA: </td><td> <input id='retIva' size='2' maxlength='5'  value='"+ rs.getString("porc_retiva")+ "' /> "+ "% RET.ICA: <input id='retIca' size='2' maxlength='5' value='"+ rs.getString("porc_retica")+ "'/></td>"
							+ "<td>&nbsp;&nbsp;</td>"
							+ "<td>DIAS DE PLAZO: </td><td>"+ rs.getString("dias_plazo")+ "&nbsp;&nbsp;&nbsp;AUTORETENEDOR <span class='style8'>*</span> <select name='AutoRetenedor' id='AutoRetenedor' >");

					if (rs.getString("autoretenedor").equals("")) {
						out.print(" <option value='Seleccione' selected=''>Seleccione</option><option value='Si'>Si</option><option value='No'>No</option></select>");
					} else {
						if (rs.getString("autoretenedor").equals("Si")) {
							out.print("<option value='Si'>Si</option><option value='No'>No</option></select>");
						} else {
							out.print(" <option value='No'>No</option><option value='Si'>Si</option></select>");
						}
					}
					out.print("</td></tr>");

					out.print("<tr HEIGHT=25>" + "<td>FECHA INGRESO </td><td> "+ rs.getString("fecha_ingreso") + " &nbsp;&nbsp;"+ "Pagina Web " + rs.getString("paginaweb")	+ "</td>" 
							+ "<td>&nbsp;&nbsp;</td>"
							+ "<td>OP. EXT <select id='OpExt'>");
					if (rs.getString("operacion_extranjera").equals("")) {
						out.print(" <option value='Seleccione' selected=''>Seleccione</option><option value='Si'>Si</option><option value='No'>No</option></select>");
					} else {
						if (rs.getString("operacion_extranjera").equals("Si")) {
							out.print("<option value='Si'>Si</option><option value='No'>No</option></select>");
						} else {
							out.print(" <option value='No'>No</option><option value='Si'>Si</option></select>");
						}
					}
					out.print("</td>");
					out.print("<td colspan='3'>");
					if (rs.getString("Ind_Cliente").equals("1")) {
						out.print("<input type='checkbox' name='checkbox' value='checkbox' id='chk' /><label for='checkbox'>Ind.Cliente ");
					} else {
						out.print("<input type='checkbox' name='checkbox' value='checkbox' id='chk' checked /><label for='checkbox'>Ind.Cliente ");
					}
					if (rs.getString("Ind_Proveedor").equals("1")) {
						out.print("<input type='checkbox' name='checkbox2' value='checkbox' id='chk2' />Ind.Proveedor");
					} else {
						out.print("<input type='checkbox' name='checkbox2' value='checkbox' id='chk2' checked/>Ind.Proveedor");
					}
					if (rs.getString("Ind_Cliente").equals("1")) {
						out.print("<input type='checkbox' name='checkbox3' value='checkbox' id='chk3' /> Ind.Empleado</label>");
					} else {
						out.print("<input type='checkbox' name='checkbox3' value='checkbox' id='chk3' checked/> Ind.Empleado</label>");
					}
					out.print("</td></tr>");

					out.print("<tr HEIGHT=25>");

					if (rs.getString("banco").equals("0")) {
						out.print("<td>BANCO: </td><td> <input id='txtBanco' size='30' onkeyup='autocompletaBanco()'  /> "+ "<input type='hidden' id='txtCodBanco' size='30' /> </td>");
					} else {
						rs1 = mpr.BuscarBancosCodigo(rs.getString("banco"));
						if (rs1.next()) {
							out.print("<td>BANCO: </td><td> <input id='txtBanco' size='30' onkeyup='autocompletaBanco()' value='"+ rs1.getString("nombre")+ "' /> "
											+ "<input type='hidden' id='txtCodBanco' size='30' value='"	+ rs.getString("banco")+ "' /> </td>");
						}
					}
					out.print("<td>&nbsp;&nbsp;</td>"
							+ "<td>TIPO DE CUENTA: </td><td> <select id='TipoCuenta'>");
					if (rs.getString("tipo_cuenta").equals("0")) {
						out.print("<option value='Seleccione' selected='' >Seleccione</option>"+ "<option value='1'>Ahorro</option><option value='2'>Corriente</option></select> &nbsp;&nbsp;");
					} else {
						if (rs.getString("tipo_cuenta").equals("1")) {
							out.print("<option value='1'>Ahorro</option><option value='2'>Corriente</option></select> &nbsp;&nbsp;");
						} else {
							out.print("<option value='2'>Corriente</option><option value='1'>Ahorro</option></select> &nbsp;&nbsp;");
						}
					}

					if (rs.getString("cuenta_banco").equals("0")) {
						out.print("No CUENTA:   <input id='NumCuenta' /></td></tr>");
					} else {
						out.print("No CUENTA:   <input id='NumCuenta' value='"+ rs.getString("cuenta_banco")+ "'/></td></tr>");
					}

					out.print("<tr >"+ "<td></td><td><div id='banco'></div></td><td></td><td></td><td></td><td></td>"+ "</tr>");
					out.print("<tr class='style3' HEIGHT=25>"+ "<td colspan='5'><div align='center'><input name='btnGuardarTercero' type='button' id='btnGuardarTercero' onclick='ActualizarProveedorCont()' value='          GUARDAR          ' /></div></td>"
							+ "</tr>");
					out.print("</table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String NomBanco = req.getParameter("NomBanco");
		if (va.equals("abc")) {
			int con = 0;
			String CodBanco = "";
			String NombrBanco = "";
			try {
				out.print("<table>");
				rs = mpr.BuscarBancos(NomBanco);
				while (rs.next()) {
					out.print("<tr><td><a href='#' onclick='SeleccionarBanco("+ rs.getString(1) + ",&quot;" + rs.getString(2)+ "&quot;)'>" + rs.getString(2) + "</a></td></tr>");
					NombrBanco = rs.getString(2);
					CodBanco = rs.getString(1);
					con = con + 1;
				}
				out.print("<tr><td><input type='hidden' id='txtCont' value="+ con + " />");
				out.print("" + "<input type='hidden' id='txtNombBanco' value='"+ NombrBanco + "' />" + "</td></tr>");
				out.print("<input type='hidden' id='txtCodBanco' value="+ CodBanco + " /></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (va.equals("1.3")) {
			mpr.ActualizarTerceroCont(OpExt, IndCliente, IndProveedor,IndEmpleado, txtBanco, TipoCuenta, retIva, retIca,AutoRetenedor, NumCuenta, codtercero);

			mpr.InsertLog(fecha, hra, usuario, "UPDATE  cont_terceros SET "+ " operacion_extranjera='"+ OpExt+ "',Ind_Cliente="+ IndCliente+ ",Ind_Proveedor="+ IndProveedor+ ", "
					+ " Ind_Empleado="+ IndEmpleado+ ",porc_retiva='"+ retIva+ "',porc_retica='"+ retIca+ "', "+ " cuenta_banco='"+ NumCuenta+ "',banco='"+ txtBanco
					+ "',tipo_cuenta='"+ TipoCuenta	+ "',autoretenedor='"+ AutoRetenedor+ "' "+ "WHERE codigo = " + codtercero + " ");

			out.print("Ingresado Con Exito.");
		}

		if (va.equals("1.4")) {
			String munnom = "";

			rs1 = mpr.CodMun(cbmun);
			try {
				if (rs1.next()) {
					munnom = rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (munnom.equals("")) {
				munnom = cbmun;
			}

			mpr.ActualizarTerceroCom(Tipo_identificacion,Numero_identificacion, razon_social, direccion, telefono,cbdep, munnom, contacto, tel_contacto, email_contacto, fax,
					regimen, DiasPlazo, FechaIngreso, PagWeb, estado,codtercero);
			System.out.println("UPDATE  cont_terceros SET "+ " tipo_identificacion = '"+ Tipo_identificacion+ "', numero_documento = '"+ Numero_identificacion+ "', "+ " razon_social = '"+ razon_social
					+ "', fax = '"+ fax+ "', "+ " direccion = '"+ direccion	+ "',telefono = '"+ telefono+ "' , telcontacto='"+ tel_contacto	+ "', "+ " emailcontac='"+ email_contacto
					+ "', departamento='" + cbdep + "', ciudad='" + munnom + "', " + " contacto='" + contacto + "'," + "dias_plazo='" + DiasPlazo + "',estado='"+ estado
					+ "',fecha_ingreso='" + FechaIngreso + "', " + "paginaweb='" + PagWeb + "' " + " WHERE codigo = " + codtercero + " ");

			mpr.InsertLog(fecha, hra, usuario, "UPDATE  cont_terceros SET " + " tipo_identificacion = '"+ Tipo_identificacion+ "', numero_documento = '"+ Numero_identificacion+ "', "+ " razon_social = '"+ razon_social+ "', fax = '"+ fax+ "', "
					+ " direccion = '"+ direccion+ "',telefono = '"+ telefono+ "' , telcontacto='"+ tel_contacto+ "', "+ " emailcontac='"+ email_contacto
					+ "', departamento='"+ cbdep+ "', ciudad='"+ munnom	+ "', " + " contacto='" + contacto + "'," + "dias_plazo='" + DiasPlazo+ "',estado='" + estado
					+ "',fecha_ingreso='" + FechaIngreso + "', " + "paginaweb='" + PagWeb + "' "+ " WHERE codigo = " + codtercero + " ");

			out.print("Actualizado Con Exito.");
		}

		if (va.equals("2")) {
			rs = mpr.BuscarProveedor(Numero_identificacion);
			try {
				if (rs.next()) {
					out.print("<table width='100%' border='0'>");
					out.print("<tr HEIGHT=25>");
					out.print("<td >TIPO IDENTIFICACION: </td><td><select name='Tipo_identificacion' id='Tipo_identificacion'>");
					if (rs.getString("tipo_identificacion").equals("CC")) {
						out.print("<option value='CC'>CC</option><option value='CE'>CE</option><option value='NIT'>NIT</option>");
					} else {
						if (rs.getString("tipo_identificacion").equals("CE")) {
							out.print("<option value='CE'>CE</option><option value='CC'>CC</option><option value='NIT'>NIT</option>");
						} else {
							out.print("<option value='NIT'>NIT</option><option value='CC'>CC</option><option value='CE'>CE</option>");
						}
					}
					out.print("</select></td>"+ "<td>&nbsp;&nbsp;</td>"
									+ "<td>NUMERO IDENTIFICACION: </td><td><input name='Numero_identificacion' type='text' id='Numero_identificacion' size='60' value='"+ rs.getString("numero_documento")
									+ "'/></td>");
					out.print("</td>");
					out.print("</tr>");
					out.print("<tr HEIGHT=25>");
					out.print("<td >RAZON SOCIAL: </td><td colspan='4'><input name='razon_social' type='text' id='razon_social' size='60' value='"+ rs.getString("razon_social") + "'/></td>");
					out.print("</td>");
					out.print("</tr>");
					out.print("<tr HEIGHT=25>");
					out.print("<td>DIRECCION: </td><td><input name='direccion' type='text' id='direccion' size='60' value='"+ rs.getString("direccion")+ "'/></td>"
									+ "<td>&nbsp;&nbsp;</td>"+ "<td>TELEFONO: </td><td><input name='telefono' type='text' id='telefono' size='30' value='"+ rs.getString("telefono") + "'/></td>");
					out.print("</td>");
					out.print("</tr>");
					out.print("<tr HEIGHT=25>");
					out.print("<td >DEPARTAMENTO: </td>");
					out.print("<td ><label> <select name='cbdep' size='1' id='cbdep' onChange='ajaxxMun(form2)' style='width: 155px'>");
					out.print("<option value='" + rs.getString("departamento")+ "' selected='selected'>"+rs.getString("departamento") + "</option>");
					rs1 = mpr.BuscarDpto();
					try {
						while (rs1.next()) {
							out.print("<option value='" + rs1.getString(1)+ "'>" + rs1.getString(1) + "</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					out.print("</select> </label></td><td>&nbsp;&nbsp;</td>");

					out.print("<td >MUNICIPIO: &nbsp; </td>");
					out.print("<td ><div id='cbmun2'><select name='cbmun' id='cbmun' style='width: 155px'>");
					out.print("<option value='" + rs.getString("ciudad")+ "' selected='selected'>" + rs.getString("ciudad")+ "</option>");
					out.print("</select> </div></td>");

					out.print("</tr>");
					out.print("<tr HEIGHT=25>"
									+ "<td>CONTACTO: </td><td>  <input id='contacto' size='30' value='"+ rs.getString("contacto")+ "'/></td>"	+ "<td>&nbsp;&nbsp;</td>"
									+ "<td>TELEFONO CONTACTO: </td><td><input id='tel_contacto' size='30' value='"+ rs.getString("telcontacto")+ "'/>&nbsp;&nbsp; " +
									  "FAX: <input name='fax' type='text' id='fax' size='19' value='"+ rs.getString("fax")+ "'/></td>"
									+ "</tr>"
									+ "<tr HEIGHT=25>"
									+ "<td >CORREO ELECTRONICO CONTACTO: </td><td><input id='email_contacto' size='30' value='"+ rs.getString("emailcontac")+ "' /></td><td>&nbsp;&nbsp;</td>");

					out.print("<td >TIPO REGIMEN: </td>");
					out.print("<td ><select name='regimen'  id='regimen'  >");
					out.print("<option selected='selected' value='"+ rs.getString("tipo_regimen") + "'>"+ rs.getString("tipo_regimen") + "</option>");
					rs1 = mpr.BuscarRegimen();
					try {
						while (rs1.next()) {
							out.print("<option value='" + rs1.getString(1)+ "'>" + rs1.getString(1) + "</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					out.print("</select> </td></tr>");
					out.print("<tr HEIGHT=25>"
									+ "<td>DIAS DE PLAZO: </td><td><input id='DiasPlazo' size='4' onkeypress='ValidaSoloNumeros()' "+ "value='" + rs.getString("dias_plazo")+ "'/></td> <td>&nbsp;&nbsp;</td>");
					out.print("<td>FECHA INGRESO </td><td> <input id='FechaIngreso' size='10' maxlength='10' onKeyUp='masca(this,&quot;/&quot;,patron,true)' value='"
									+ rs.getString("fecha_ingreso")+ "' /> &nbsp;&nbsp;"
									+ "Pagina Web <input id='PagWeb' size='30' value='"+ rs.getString("paginaweb")+ "' /></td>"
									+ "</tr>");

					out.print("<tr>"+ "<td>ESTADO: </td><td> <select id='estado'>");
					if (rs.getString("estado").equals("0")) {
						out.print("<option value='0'>Activo</option><option value='1'>Inactivo</option></select> &nbsp;&nbsp;");
					} else {
						out.print("<option value='1'>Inactivo</option><option value='0'>Activo</option></select> &nbsp;&nbsp;");
					}
					out.print("</select> </td></tr>");
					out.print("<tr class='style3' HEIGHT=25>"
									+ "<td colspan='5'><div align='center'><input name='btnGuardarTercero' type='button' id='btnGuardarTercero' onclick='ActualizarProveedorCom()' value='          GUARDAR          ' /></div></td>"
									+ "</tr>");
					out.print("</table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (va.equals("3")) {
			rs2 = mpr.Tercero(Numero_identificacion);
			try {
				if (rs2.next()) {
					out.print("1");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}