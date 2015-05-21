package fact_controlador;

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

import cont_metodo.MetodoProveedor;
import cont_metodo.MetodoProveedorN;

import adm_logica.MetodoNacionalidad;
import fact_metodo.MetodoCrearEntidad;

/**
 * Controlador ControlCrearEntidad en el que se permite crear y modificar las entidades ingresadas en la BD
 * @version 1.2.0
 */
public class ControlCrearEntidad extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		String va = req.getParameter("valor");
		
		String NitEntidad=req.getParameter("NitEntidad");
		String NombreEntidad=req.getParameter("NombreEntidad");
		String dir = req.getParameter("dir");
		String tel = req.getParameter("tel");
		String fax = req.getParameter("fax");
		String contacto = req.getParameter("contacto");
		String depto = req.getParameter("depto");
		String ciudad = req.getParameter("ciudad");
		String obs = req.getParameter("obs");
		String regimen = req.getParameter("regimen");
		String modPago = req.getParameter("modPago");
		String codPrestador = req.getParameter("codPrestador");
		String Estado = req.getParameter("Estado");
		
		String NombreRepresentante = req.getParameter("NombreRepresentante");
		String TipoIdentificacion = req.getParameter("TipoIdentificacion");
		String NumeroIdentificacion = req.getParameter("NumeroIdentificacion");
		String usuario = req.getParameter("usuario");
		
		String TarifaFacturacion = "-";
		String ValorContrato = "1";
		String FechaInicio = "0000-00-00";
		String FechaFin = "0000-00-00";
		String Modalidad = "1";
		String TipoContrato = "1";
		String NivelComplejidad = "1";
		
		String CodigoEntidad = "";
		String CodEntidad = req.getParameter("CodEntidad");
		
		MetodoCrearEntidad mce = new MetodoCrearEntidad();
		MetodoProveedor mpr = new MetodoProveedor();
		MetodoProveedorN mp = new MetodoProveedorN();
		
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
		String fecha = annio + "-" + mes + "-" + dia; // para la base de dtaos
		
		Calendar calendario = Calendar.getInstance();
		int hora2, minutos, segundos;
		hora2 = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra = hora2 + ":" + minutos + ":" + segundos;
		
		if(va.equals("Ingresar")){
			/*out.print("<table width='100%' border='1' align='center'><tr><td colspan='8' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Datos de La Entidad</div></td></tr>");
			out.print("<tr><td width='13%'>NIT de la Entidad</td><td width='17%' colspan='2'><input name='txtNitEntidad' type='text' id='txtNitEntidad' maxlength='100' onkeydown='A(this, event)' /><span class='style8'>*</span></td><td width='16%' colspan='2'><div align='right'>Nombre Entidad </div></td><td width='54%' colspan='3'><input name='txtNombreEntidad' type='text' id='txtNombreEntidad' size='54' maxlength='300' onkeydown='A(this, event)'/><span class='style8'>*</span></td></tr>");
			out.print("<tr><td>Direccion</td><td colspan='4'><input name='txtDir' type='text' id='txtDir' size='35' maxlength='150' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Tel&eacute;fono</div></td><td colspan='2'><input name='txtTel' type='text' id='txtTel' maxlength='20' onkeydown='A(this, event)'/><span class='style8'>*</span></td></tr>");
			out.print("<tr><td>Fax</td><td colspan='4'><input name='txtFax' type='text' id='txtFax' maxlength='50' onkeydown='A(this, event)'/></td><td><div align='right'>Contacto</div></td><td colspan='2'><input name='txtContacto' type='text' id='txtContacto' maxlength='100' onkeydown='A(this, event)'/></td></tr>");
			out.print("<tr><td>Departamento</td><td colspan='2'><select name='listDepto' id='listDepto' style='width: 170px;' onkeydown='A(this, event)' onchange='ajaxxMun(this, \"listCiudad\")'><option value='0'>Seleccione ...</option></select><span class='style8'>*</span></td><td colspan='2'><div align='right'>Ciudad</div></td><td colspan='3'><select name='listCiudad' id='listCiudad' style='width: 170px;' onkeydown='A(this, event)'><option value='0'>Seleccione ...</option></select><span class='style8'>*</span></td></tr>");
			out.print("<tr><td>Observaci&oacute;n</td><td colspan='4' rowspan='2'><textarea id='taObservacion' name='taObservacion' cols='30' rows='3' onkeyup='return maximaLongitud(this,300)' style='font-family: Trebuchet MS;'></textarea></td><td>Regimen</td><td colspan='2'><select name='listRegimen' id='listRegimen' style='width: 100px;' onkeydown='A(this, event)'><option value='0' selected='selected'>Seleccione...</option><option value='Contributivo'>Contributivo</option><option value='Subsidiado'>Subsidiado</option></select><span class='style8'>*</span></td></tr>");
			out.print("<tr><td>&nbsp;</td><td>Modalidad de pago </td><td colspan='2'><select name='listModPago' id='listModPago' style='width: 100px;' onkeydown='A(this, event)'><option value='0' selected='selected'>Seleccione...</option><option value='Empresa'>Empresa</option><option value='Usuario'>Usuario</option><option value='Compartido'>Compartido</option></select><span class='style8'>*</span></td></tr>");
			out.print("<tr><td colspan='5'>C&oacute;digo Prestador&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='35' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td>Estado</td><td colspan='2'><select name='listEstado' id='listEstado' style='width: 100px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='40' valign='middle'><div align='center' class='style11' id='cabecera2'>Datos del Representante Legal </div></td></tr>");
			out.print("<tr><td width='18%'>Tipo Identificacion </td><td width='17%'><select name='cmbTipoDocumento' id='cmbTipoDocumento' style='width: 100px;' onkeydown='A(this, event)'><option value='CC'>CC</option></select><span class='style8'>*</span></td><td width='17%'><div align='right'>Numero Identificacion</div></td><td width='48%'><input name='txtNumeroIdentificacion' type='text' id='txtNumeroIdentificacion' onkeypress='return soloNumero(event)' onkeydown='A(this, event)' /><span class='style8'>*</span></td></tr>");
			out.print("<tr><td>Nombre Representante </td><td colspan='3'><input name='txtNombreRepresentante' type='text' id='txtNombreRepresentante' size='52' onkeydown='A(this, event)'/><span class='style8'>*</span></td></tr>");
			out.print("<tr><td colspan='4'><span class='style8'>* Campos Requeridos</span></td></tr>");
			out.print("<tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Crear Entidad     ' onclick='CrearEntidad()' /></div></td></tr></table>");*/
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Datos de La Entidad</div></td></tr>");
			out.print("<tr><td width='15%'><div align='right'>NIT de la Entidad:</div></td><td width='30%'><input name='txtNitEntidad' type='text' id='txtNitEntidad' onkeydown='A(this, event)' size='39' maxlength='100' /><span class='style8'>*</span></td><td width='15%'><div align='right'>Nombre Entidad:</div></td><td width='40%'><input name='txtNombreEntidad' type='text' id='txtNombreEntidad' size='39' maxlength='300' onkeydown='A(this, event)' /><span class='style8'>*</span></td></tr>");
			out.print("<tr><td><div align='right'>Direccion:</div></td><td><input name='txtDir' type='text' id='txtDir' size='39' maxlength='150' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Tel&eacute;fono:</div></td><td><input name='txtTel' type='text' id='txtTel' onkeydown='A(this, event)' size='39' maxlength='20'/><span class='style8'>*</span></td></tr>");
			out.print("<tr><td><div align='right'>Fax:</div></td><td><input name='txtFax' type='text' id='txtFax' onkeydown='A(this, event)' size='39' maxlength='50' /></td><td><div align='right'>Contacto:</div></td><td><input name='txtContacto' type='text' id='txtContacto' onkeydown='A(this, event)' size='39' maxlength='100'/ ></td></tr>");
			out.print("<tr><td><div align='right'>Departamento:</div></td><td><select name='listDepto' id='listDepto' style='width: 266px;' onkeydown='A(this, event)' onchange='ajaxxMun(this, \"listCiudad\")'><option value='0'>Seleccione ...</option></select><span class='style8'>*</span></td><td><div align='right'>Ciudad:</div></td><td><select name='listCiudad' id='listCiudad' style='width: 266px;' onkeydown='A(this, event)'><option value='0'>Seleccione ...</option></select><span class='style8'>*</span></td></tr>");
			out.print("<tr><td rowspan='2' valign='middle' ><div align='right'>Observaci&oacute;n:</div></td><td rowspan='2'><textarea id='taObservacion' name='taObservacion' cols='41' rows='3' onkeyup='return maximaLongitud(this,300)' style='font-family: Trebuchet MS;'></textarea></td><td><div align='right'>Regimen:</div></td><td><select name='listRegimen' id='listRegimen' style='width: 266px;' onkeydown='A(this, event)'><option value='0' selected='selected'>Seleccione...</option>");
			try {
				rs1=mce.BuscarTipoEntidad();
				while(rs1.next()){
					out.print("<option value='"+rs1.getString("tipo_empresa")+"'>"+rs1.getString("tipo_empresa")+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
					//"<option value='Contributivo'>Contributivo</option>" +
					//"<option value='Subsidiado'>Subsidiado</option>" +
			out.print("</select><span class='style8'>*</span></td></tr>");
			out.print("<tr><td><div align='right'>Modalidad de pago:</div></td><td><select name='listModPago' id='listModPago' style='width: 266px;' onkeydown='A(this, event)'><option value='0' selected='selected'>Seleccione...</option><option value='Empresa'>Empresa</option><option value='Usuario'>Usuario</option><option value='Compartido'>Compartido</option></select><span class='style8'>*</span></td></tr>");
			out.print("<tr><td><div align='right'>C&oacute;digo Prestador:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' size='39' maxlength='50' onkeydown='A(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Estado:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='A(this, event)'><option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option></select><span class='style8'>*</span></td></tr></table>");
			
			out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='40' valign='middle'><div align='center' class='style11' id='cabecera2'>Datos del Representante Legal </div></td></tr>");
			out.print("<tr><td width='15%'><div align='right'>Tipo Identificacion:</div></td><td width='30%'><select name='cmbTipoDocumento' id='cmbTipoDocumento' style='width: 266px;' onkeydown='A(this, event)'><option value='CC'>CC</option></select><span class='style8'>*</span></td><td width='15%'><div align='right'>Numero Identificacion:</div></td><td width='40%'><input name='txtNumeroIdentificacion' type='text' id='txtNumeroIdentificacion' onkeypress='return soloNumero(event)' onkeydown='A(this, event)' size='39' maxlength='20' /><span class='style8'>*</span></td></tr>");
			out.print("<tr><td><div align='right'>Nombre Representante:</div></td><td colspan='3'><input name='txtNombreRepresentante' type='text' id='txtNombreRepresentante' size='39' onkeydown='A(this, event)'/><span class='style8'>*</span></td></tr>");
			out.print("<tr><td colspan='4'><span class='style8' style='font-size: 10pt;'>* Campos Requeridos</span></td></tr>");
			out.print("<tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Crear Entidad     ' onclick='CrearEntidad()' /><input name='BorrarForm' type='button' id='BorrarForm' value='     Borrar Campos     ' onclick='Borrar()' /></div></td></tr></table>");
			out.close();
		}else if(va.equals("1")){
			try {
				/*rs = mce.BuscarEntidad(NitEntidad);
				if(rs.next()){
					out.print("Esta Nit Ya le Pertenece a Una Entidad.");
				} else{*/
				//System.out.println("Depto: "+depto+"\nCiudad: "+ciudad);
					mce.CrearEntidad(NitEntidad, NombreEntidad, dir, tel, fax, contacto, depto, ciudad, obs, regimen, modPago, codPrestador, Estado, NombreRepresentante, TipoIdentificacion, NumeroIdentificacion);
					rs1 = mce.BuscarEntidad(NombreEntidad);
					if(rs1.next()){
						CodigoEntidad = rs1.getString(1);
						mce.CrearConvenio(TarifaFacturacion,CodigoEntidad);
						mpr.CrearCuentaEmpresas(NitEntidad, NombreEntidad, "Mayor", "Credito", "1", "Activo", CodigoEntidad,"","","","","0");
						
						//crear entidad en la tabla cont_terceros
						mp.CrearProveedor("NIT",NumeroIdentificacion , "", "", "", "",NombreEntidad,  regimen, dir, tel, contacto, "", "", depto, ciudad,"", fecha, "", fax, CodigoEntidad);
						System.out.println("Insert Into cont_terceros: Tipo identificacion= NIT ,Numero identificacion= "+NumeroIdentificacion+
								" ,razon social="+NombreEntidad+" ,regimen="+regimen+" ,direccion="+dir+" ,telefono="+tel+" ,contacto="+contacto+
								" ,departamento="+depto+" ,municipio="+ciudad+" ,dias plazo= ,fecha ingreso="+fecha+" ,fax="+fax);
						
						
						mp.InsertLog(fecha, hra, usuario, "Insert Into cont_terceros: Tipo identificacion= NIT ,Numero identificacion= "+NumeroIdentificacion+
								" ,razon social="+NombreEntidad+" ,regimen="+regimen+" ,direccion="+dir+" ,telefono="+tel+" ,contacto="+contacto+
								" ,departamento="+depto+" ,municipio="+ciudad+" ,dias plazo= ,fecha ingreso="+fecha+" ,fax="+fax);
					}
					rs1.getStatement().getConnection().close();
					out.print("Entidad Creada Con Exito.");
				/*}
				rs.getStatement().getConnection().close();*/
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.close();
		}else if(va.equals("2")){
			mce.ActualizarEntidad(NitEntidad, NombreEntidad, dir, tel, fax, contacto, depto, ciudad, obs, regimen, modPago, codPrestador, Estado, NombreRepresentante, TipoIdentificacion, NumeroIdentificacion, CodEntidad);
			//mce.ActualizarEntidad(NitEntidad, NombreRepresentante, TipoIdentificacion, NumeroIdentificacion, NombreEntidad, CodEntidad);
			if(Estado.equals("Activo")){
				Estado="0";
			}else{
				Estado="1";
			}
			mce.ActualizarDatos(NitEntidad, NombreEntidad, regimen, dir, depto, ciudad, fax, Estado, CodEntidad);
			mp.InsertLog(fecha, hra, usuario, "UPDATE cont_terceros SET numero_documento='"+NitEntidad+"', razon_social='"+NombreEntidad+"', tipo_regimen='"+regimen+"', direccion='"+dir+"', departamento='"+depto+"', ciudad='"+ciudad+"', fax='"+fax+"',estado='"+Estado+"' WHERE ent_nit='"+CodEntidad+"'");
			
			out.print("Entidad Actualizada Con Exito.");
			out.close();
		}else if(va.equals("Actualizar")){
			rs = mce.BuscarTodasEntidad();
			out.print("<table width='100%' border='0'><tr><td colspan='2'><div align='center' class='style11' id='div'>Actualizar Entidad</div></td></tr>");
			out.print("<tr><td width='15%'>Seleccione Entidad </td><td width='85%'><select name='cmbEntidad' id='cmbEntidad' onchange='BuscarEntidad()'><option value='Seleccione'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></td></tr>");
				out.print("<tr><td colspan='2'><div id='Entidad'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.close();
		}else if(va.equals("verentidad")){
			rs = mce.BuscarEntidadPorCodigo(CodEntidad);
			try {
				if(rs.next()){
					String FaxA = "", ContactoA = "", ObsA = "";
					if(rs.getString(5) != null){FaxA = rs.getString(5);}
					if(rs.getString(6) != null){ContactoA = rs.getString(6);}
					if(rs.getString(9) != null){ObsA = rs.getString(9);}
					
					out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='30' valign='top'><div align='center' class='style11' id='cabecera2'>Datos de La Entidad</div></td></tr>");
					out.print("<tr><td width='15%'><div align='right'>NIT de la Entidad:</div></td><td width='30%'><input name='txtNitEntidad' type='text' id='txtNitEntidad' value='"+rs.getString(1)+"' onkeydown='B(this, event)' size='39' maxlength='100' /><span class='style8'>*</span></td><td width='15%'><div align='right'>Nombre Entidad:</div></td><td width='40%'><input name='txtNombreEntidad' type='text' id='txtNombreEntidad' value='"+rs.getString(2)+"' size='39' maxlength='300' onkeydown='B(this, event)' /><span class='style8'>*</span></td></tr>");
					out.print("<tr><td><div align='right'>Direccion:</div></td><td><input name='txtDir' type='text' id='txtDir' value='"+rs.getString(3)+"' size='39' maxlength='150' onkeydown='B(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Tel&eacute;fono:</div></td><td><input name='txtTel' type='text' id='txtTel' value='"+rs.getString(4)+"' onkeydown='B(this, event)' size='39' maxlength='20'/><span class='style8'>*</span></td></tr>");
					out.print("<tr><td><div align='right'>Fax:</div></td><td><input name='txtFax' type='text' id='txtFax' value='"+FaxA+"' onkeydown='B(this, event)' size='39' maxlength='50' /></td><td><div align='right'>Contacto:</div></td><td><input name='txtContacto' type='text' id='txtContacto' value='"+ContactoA+"' onkeydown='B(this, event)' size='39' maxlength='100'/ ></td></tr>");
					out.print("<tr><td><div align='right'>Departamento:</div></td><td><select name='listDepto' id='listDepto' style='width: 266px;' onkeydown='B(this, event)' onchange='ajaxxMun(this, \"listCiudad\")'>");
					MetodoNacionalidad na = new MetodoNacionalidad();
					String codDepto;
					if(rs.getString(7) == null/* || rs.getString(7).equals("")*/){
						out.print("<option value='0' selected='selected'>Seleccione ...</option>");
						codDepto = "";
					}else{
						out.print("<option value='0'>Seleccione ...</option>");
						codDepto = rs.getString(7);
					}
					ResultSet rs2 = null;
					rs2 = na.getDeptos();
					try {
						while(rs2.next()){
							if(codDepto.equals(rs2.getString(1))){//Comparando entre nombres de departamentos
								out.print("<option value='"+rs2.getString(1)+"' selected='selected'>"+rs2.getString(1)+"</option>");
								codDepto = rs2.getString(2);
							}else{
								out.print("<option value='"+rs2.getString(1)+"'>"+rs2.getString(1)+"</option>");
							}
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					out.print("</select><span class='style8'>*</span></td><td><div align='right'>Ciudad:</div></td><td><select name='listCiudad' id='listCiudad' style='width: 266px;' onkeydown='B(this, event)'><option value='0'>Seleccione ...</option>");
					ResultSet rs3 = null;
					rs3 = na.SQLMun(codDepto);
					try {
						while(rs3.next()){
							if(rs.getString(8).equals(rs3.getString(1).trim())){
								out.print("<option value='"+rs3.getString(1)+"' selected='selected'>"+rs3.getString(1)+"</option>");
							}else{
								out.print("<option value='"+rs3.getString(1)+"'>"+rs3.getString(1)+"</option>");
							}
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					out.print("</select><span class='style8'>*</span></td></tr>");
					out.print("<tr><td valign='middle' rowspan='2'><div align='right' style='/*border: 1px solid green;*/'>Observaci&oacute;n:</div></td><td valign='middle' rowspan='2'><textarea id='taObservacion' name='taObservacion' cols='41' rows='3' onkeyup='return maximaLongitud(this,300)' style='font-family: Trebuchet MS;'>"+ObsA+"</textarea></td><td><div align='right'>Regimen:</div></td><td><select name='listRegimen' id='listRegimen' style='width: 266px;' onkeydown='B(this, event)'>");
					try {
						rs1=mce.BuscarTipoEntidad();
						out.print("<option value='"+rs.getString("regimen")+"'>"+rs.getString("regimen")+"</option>");
						while(rs1.next()){
							out.print("<option value='"+rs1.getString("tipo_empresa")+"'>"+rs1.getString("tipo_empresa")+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}				
					
					/*if(rs.getString(10).equals("") || rs.getString(10) == null){
						out.print("<option value='0' selected='selected'>Seleccione...</option><option value='Contributivo'>Contributivo</option><option value='Subsidiado'>Subsidiado</option>");
					}else if(rs.getString(10).equals("Contributivo")){
						out.print("<option value='0'>Seleccione...</option><option value='Contributivo' selected='selected'>Contributivo</option><option value='Subsidiado'>Subsidiado</option>");
					}else if(rs.getString(10).equals("Subsidiado")){
						out.print("<option value='0'>Seleccione...</option><option value='Contributivo'>Contributivo</option><option value='Subsidiado' selected='selected'>Subsidiado</option>");
					}*/
					out.print("</select><span class='style8'>*</span></td></tr>");
					out.print("<tr><td><div align='right'>Modalidad de pago:</div></td><td><select name='listModPago' id='listModPago' style='width: 266px;' onkeydown='B(this, event)'>");
					if(rs.getString(11).equals("") || rs.getString(11) == null){
						out.print("<option value='0' selected='selected'>Seleccione...</option><option value='Empresa'>Empresa</option><option value='Usuario'>Usuario</option><option value='Compartido'>Compartido</option>");
					}else if(rs.getString(11).equals("Empresa")){
						out.print("<option value='0'>Seleccione...</option><option value='Empresa' selected='selected'>Empresa</option><option value='Usuario'>Usuario</option><option value='Compartido'>Compartido</option>");
					}else if(rs.getString(11).equals("Usuario")){
						out.print("<option value='0'>Seleccione...</option><option value='Empresa'>Empresa</option><option value='Usuario' selected='selected'>Usuario</option><option value='Compartido'>Compartido</option>");
					}else if(rs.getString(11).equals("Compartido")){
						out.print("<option value='0'>Seleccione...</option><option value='Empresa'>Empresa</option><option value='Usuario'>Usuario</option><option value='Compartido' selected='selected'>Compartido</option>");
					}
					out.print("</select><span class='style8'>*</span></td></tr>");
					out.print("<tr><td><div align='right'>C&oacute;digo Prestador:</div></td><td><input name='txtCodigoPrestador' type='text' id='txtCodPrestador' value='"+rs.getString(12)+"' size='39' maxlength='50' onkeydown='B(this, event)'/><span class='style8'>*</span></td><td><div align='right'>Estado:</div></td><td><select name='listEstado' id='listEstado' style='width: 266px;' onkeydown='B(this, event)'>");
					if (rs.getString(13).equals("Inactivo")){
						out.print("<option value='Activo'>Activo</option><option value='Inactivo' selected='selected'>Inactivo</option>");
					}else{
						out.print("<option value='Activo' selected='selected'>Activo</option><option value='Inactivo'>Inactivo</option>");
					}
					out.print("</select><span class='style8'>*</span></td></tr></table>");
					
					out.print("<table width='100%' border='0' align='center'><tr><td colspan='4' height='40' valign='middle'><div align='center' class='style11' id='cabecera2'>Datos del Representante Legal</div></td></tr>");
					out.print("<tr><td width='15%'><div align='right'>Tipo Identificacion:</div></td><td width='30%'><select name='cmbTipoDocumento' id='cmbTipoDocumento' style='width: 266px;' onkeydown='B(this, event)'><option value='CC'>CC</option></select><span class='style8'>*</span></td><td width='15%'><div align='right'>Numero Identificacion:</div></td><td width='40%'><input name='txtNumeroIdentificacion' type='text' id='txtNumeroIdentificacion' value='"+rs.getString(14)+"' onkeypress='return soloNumero(event)' onkeydown='B(this, event)' size='39' maxlength='20' /><span class='style8'>*</span></td></tr>");
					out.print("<tr><td><div align='right'>Nombre Representante:</div></td><td colspan='3'><input name='txtNombreRepresentante' type='text' id='txtNombreRepresentante' value='"+rs.getString(15)+"' size='39' onkeydown='B(this, event)'/><span class='style8'>*</span></td></tr>");
					out.print("<tr><td colspan='4'><span class='style8' style='font-size: 10pt;'>* Campos Requeridos</span></td></tr>");
					out.print("<tr><td colspan='4'><div align='center'><input name='btnCrearEntidad' type='button' id='btnCrearEntidad' value='     Actualizar     ' onclick='ActualizarEntidad()' /></div></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.close();
		}
	}
}
