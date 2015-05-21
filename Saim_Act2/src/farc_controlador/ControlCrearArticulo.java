package farc_controlador;

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

import farc_metodo.MetodoCrearArticulo;
import farc_metodo.MetodoEntradas;


public class ControlCrearArticulo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		MetodoCrearArticulo mca = new MetodoCrearArticulo();
		MetodoEntradas me = new MetodoEntradas();
		
		String va = req.getParameter("valor");
		String gen = req.getParameter("gen");
		String codigoArticulo=req.getParameter("codigoArticulo");
		String nombre=req.getParameter("nombre");
		String concentracion=req.getParameter("concentracion");
		String cod_formaFK=req.getParameter("cod_formaFK");
		String cod_unidadFK=req.getParameter("cod_unidadFK");
		String tipoArticulo=req.getParameter("tipoArticulo");
		String nombreGenerico=req.getParameter("nombreGenerico");
		String tipo=req.getParameter("tipo");
		String grupo=req.getParameter("grupo");
		String grupop=req.getParameter("grupop");
		String control=req.getParameter("control");
		String observacion=req.getParameter("observacion");
		String texto=req.getParameter("texto");
		String cart=req.getParameter("cart");
		if(cart==null){cart="";}
			
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		if(va.equals("CRLAB")){
			String txtNombreLaboratorio=req.getParameter("txtNombreLaboratorio");			
			rs=mca.BuscarLaboratorio(txtNombreLaboratorio);
			try {
				if(rs.next()){
					out.print("Este Laboratorio ya esta creado.");
				}else{
					mca.CrearLaboratorio(txtNombreLaboratorio);
					out.print("Laboratorio Creado Exitosamente");
				}
			} catch (SQLException e) {
				System.out.print("Error: "+e);
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("ACEAC")){
			String cmbMedicaMentoActu=req.getParameter("cmbMedicaMentoActu");
			String txtNombreArticulo=req.getParameter("txtNombreArticulo");
			String txtCodigoCUM=req.getParameter("txtCodigoCUM");
			String txtCodigoATC=req.getParameter("txtCodigoATC");
			String cmbGrupo=req.getParameter("cmbGrupo");//medicamento, insumo
			String txtConcentracion=req.getParameter("txtConcentracion");
			String cmbUnidad=req.getParameter("cmbUnidad");
			String cmbFFarmaceutica=req.getParameter("cmbFFarmaceutica");
			String cmbControl=req.getParameter("cmbControl");// si o no
			String cmbClasificacion=req.getParameter("cmbClasificacion");//pos o no pos
			String cmbTipo=req.getParameter("cmbTipo");// generico o comercial
			String txtCodProgramaMed=req.getParameter("txtCodProgramaMed");
			
			//actualizar Medicamento
			mca.ModificarMedicamentoHNJ(cmbMedicaMentoActu, txtCodigoATC, txtNombreArticulo, txtConcentracion,
					cmbGrupo, cmbUnidad, cmbClasificacion, cmbTipo, cmbFFarmaceutica, cmbControl);
			//actualizar programa
		}
		
		
		if(va.equals("MMEAC")){
			String cmbMedicaMentoActu=req.getParameter("cmbMedicaMentoActu");
			rs1=mca.BuscarMedicamentoActualizar(cmbMedicaMentoActu);
			try {
				if(rs1.next()){
					out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'> " +
							" <tr> <td colspan='2'>Nombre Medicamento o Insumo </td> <td colspan='4'><input name='txtNombreArticulo' type='text' id='txtNombreArticulo' style='width:100%' value='"+rs1.getString("nombre")+"' /></td> " +
							" <td width='7%'>Codigo CUM </td> <td colspan='2'><input name='txtCodigoCUM' type='text' id='txtCodigoCUM' style='width:40%' disabled='true' value="+rs1.getString("cod_cups")+" title='El codigo CUM se debe de Actualizar Por el Modulo de Facturacion en el campo CUPS' /> " +
							"  ATC  <input  name='txtCodigoATC' type='text' id='txtCodigoATC' size='12' value="+rs1.getString("codigoMedicamento")+" /></td><td width='11%'>Grupo</td> " +
							"  <td colspan='2'><select name='cmbGrupo' id='cmbGrupo' style='width:100%'>        <option value="+rs1.getString("cod_grupoFK")+" selected='selected'>"+rs1.getString(8)+"</option>");
					rs=mca.ObtenerGrupos();
					while(rs.next()){
						out.print("<option value="+rs.getString("codigo")+">"+rs.getString("descripcion")+"</option>");
					}
					rs.getStatement().getConnection().close();
					out.print("</select></td></tr><tr><td width='7%'>Concentracion</td><td width='11%'><label><input name='txtConcentracion' type='text' id='txtConcentracion' style='width:50%' value="+rs1.getString("concentracion")+" /> " +
							"</label></td><td width='4%'>Unidad</td><td width='11%'><select name='cmbUnidad' id='cmbUnidad' style='width:100%'><option value="+rs1.getString("cod_unidadFK")+" selected='selected'>"+rs1.getString("sigla")+"</option> ");
					rs=mca.ObtenerUnidad();
					while(rs.next()){
						out.print("<option value="+rs.getString("codigo")+">"+rs.getString("sigla")+"</option>");
					}
					rs.getStatement().getConnection().close();
					out.print("</select></td><td width='8%'>F.Farmaceutica</td> <td width='13%'><select name='cmbFFarmaceutica' id='cmbFFarmaceutica' style='width:100%'><option value="+rs1.getString("cod_formaFK")+" selected='selected'>"+rs1.getString(6)+"</option> ");
					rs=mca.ObtenerFormaFarmaceutica();
					while(rs.next()){
						out.print("<option value="+rs.getString("codigo")+">"+rs.getString("descripcion")+"</option>");
					}
					rs.getStatement().getConnection().close();				
					
					
					out.print("</select></td> <td>Med.Control</td><td width='8%'><select name='cmbControl' id='cmbControl' style='width:100%'> " +
							" <option value="+rs1.getString("control")+" selected='selected'>"+rs1.getString("control")+"</option> " +
							" <option value='Si'>Si</option> " +
							" <option value='No'>No</option> " +
							" </select></td><td width='10%'>Clasificacion</td><td><select name='cmbClasificacion' id='cmbClasificacion' style='width:100%'> " +
							" <option value="+rs1.getString("clasificacion")+">"+rs1.getString("clasificacion")+"</option> " +
							" <option value='Pos'>Pos</option> " +
							" <option value='No Pos'>No Pos</option> " +
							" </select></td><td width='2%'>Tipo</td> <td width='8%'><select name='cmbTipo' id='cmbTipo' style='width:100%' > " +
							" <option value="+rs1.getString("tipo")+" selected='selected'>"+rs1.getString("tipo")+"</option> " +
							" <option value='Generico'>Generico</option> " +
							" <option value='Comercial'>Comercial</option> " +
							" </select></td></tr><tr><td colspan='2'>&nbsp;</td><td colspan='7' align='center'><input type='button' name='Button' value='Actualizar' onclick='ActualizarMedicamentoHNJ()' /></td> " +
							" <td><input type='hidden' id='txtCodProgramaMed' value="+rs1.getString("cod_programa")+"></td><td>&nbsp;</td><td>&nbsp;</td></tr></table>");

				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("GIR")){
			String cmbArticulo=req.getParameter("cmbArticulo");
			String txtFechaVenci=req.getParameter("txtFechaVenci");
			String txtLote=req.getParameter("txtLote");
			String txtCantidad=req.getParameter("txtCantidad");
			String txtInvima=req.getParameter("txtInvima");
			String cmbBodega =req.getParameter("cmbBodega");
					
			String fechapre,ini,med,fin=null;
			ini=txtFechaVenci.substring(0,2);
			med=txtFechaVenci.substring(3,5);
			fin=txtFechaVenci.substring(6,10);
			fechapre=fin+"-"+med+"-"+ini;
			
			java.util.Date FechaAc1 = new java.util.Date();
			java.sql.Date Fecha_Insercion = new java.sql.Date(FechaAc1.getTime());		
			java.sql.Time Hora_Insercion = new java.sql.Time(FechaAc1.getTime());
			
			mca.CrearInventarioHNJ(cmbArticulo,fechapre,txtLote,txtCantidad,txtInvima,cmbBodega,Fecha_Insercion+"",Hora_Insercion+"");
		}
		
		if(va.equals("CAV")){
			String txtNombreArticulo=req.getParameter("txtNombreArticulo");
			String txtCodigoATC=req.getParameter("txtCodigoATC");
			String cmbGrupo=req.getParameter("cmbGrupo");
			String txtConcentracion=req.getParameter("txtConcentracion");
			String cmbUnidad=req.getParameter("cmbUnidad");
			String cmbFFarmaceutica=req.getParameter("cmbFFarmaceutica");
			String cmbControl=req.getParameter("cmbControl");
			String cmbClasificacion=req.getParameter("cmbClasificacion");
			String cmbTipo=req.getParameter("cmbTipo");
			String txtValorArticulo=req.getParameter("txtValorArticulo");
			String txtCodusuario=req.getParameter("txtCodusuario");
			String txtCodigoCUM=req.getParameter("txtCodigoCUM");
			try {
			//Crear en la Tabla Medicamento.
			mca.CrearArticulo(txtCodigoATC, txtNombreArticulo, txtConcentracion, "-", cmbGrupo, txtNombreArticulo, cmbUnidad, cmbClasificacion,cmbTipo , cmbFFarmaceutica, cmbControl);
			//se consulta el articulo creado
			rs=mca.ObtenerCodigoMedicamento(txtCodigoATC, txtNombreArticulo);			
				if(rs.next()){
					String CodArticulo=rs.getString("codigo");
					// se crea en la tabla fact_programas.
					java.util.Date FechaAc1 = new java.util.Date();
					java.sql.Date Fecha_Insercion = new java.sql.Date(FechaAc1.getTime());		
					java.sql.Time Hora_Insercion = new java.sql.Time(FechaAc1.getTime());
					mca.CrearProgramaFacturacion(txtCodigoATC, txtNombreArticulo, txtCodusuario, Fecha_Insercion+"", Hora_Insercion+"",txtCodigoCUM);
					// se consulta la creacion de programa facturacion
					rs1=mca.ObtenerCodigoPrograma(Fecha_Insercion+"", Hora_Insercion+"");
					if(rs1.next()){
						String CodPrograma=rs1.getString("cod_programa");
						//se crea en programa medicamento
						mca.CrearProgramaMedicamento(CodArticulo, CodPrograma);
						
						// se crea la tarifa en fact tarifa
						String CodManualTari="";String FechaIni="2014-01-01";String FechaFin="2014-12-31";
						if(cmbGrupo.equals("1")){CodManualTari="16";}
						if(cmbGrupo.equals("2")){CodManualTari="19";}
						mca.CrearTarifaArticulo(CodManualTari, CodPrograma, txtValorArticulo);
						out.print("Articulo Creado Exitosamente.");
					}else{
						out.print("Ocurrio un Error, Vuelva a Intentarlo. No Ingreso en Facturacion");
					}	
					rs1.getStatement().getConnection().close();
				}else{
					out.print("Ocurrio un Error, Vuelva a Intentarlo. No Ingreso Medicamento");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("0")){			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear Articulo</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre Articulo</td><td width='30%'><label><input name='txtNombreArticulo' type='text' id='txtNombreArticulo' size='40' /></label></td>");
			out.print("<td width='13%'>Grupo al que pertenece</td><td width='45%'><label><select name='cmbGrupo' id='cmbGrupo' onChange='medoins()'><option value='Seleccione'>Seleccione</option>");
			rs=mca.ObtenerGrupos();
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");
			//</label></td></tr>
			
			out.print("<tr><td>Concentracion</td><td><label><input name='txtConcentracion' type='text' id='txtConcentracion' size='40'/><td>Forma Farmaceutica</td><td><label><select name='cmbFormaFarmaceutica' id='cmbFormaFarmaceutica' ><option value='Seleccione'>Seleccione</option>");
			rs3=mca.ObtenerFormaFarmaceutica();
			try {
				while(rs3.next()){
				out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td></tr>");
			
			out.print("<tr><td>Unidad de Medida</td><td><label><select name='cmbUnidad' id='cmbUnidad'><option value='Seleccione'>Seleccione</option>");
			rs1=mca.ObtenerUnidad();
			try {
				while(rs1.next()){
				out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");
			out.print("<td>Tipo de Articulo</td><td><label><select name='cmbTipoA' id='cmbTipoA' onChange='Comercial(1)'><option value='Seleccione'>Seleccione</option><option value='Comercial'>Comercial</option><option value='Generico'>Generico</option></select></label></td>");
			out.print("<tr><td colspan='4'><div id='imagina'></div></td></tr>");
		}
		
		if(va.equals("2")){
			out.print("<table  width='100%' class='style6'><tr><td width='12%'>Nombre Generico</td><td  width='30%'><label><input name='txtNombreGenerico' type='text' id='txtNombreGenerico' size='40' /></label></td><td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' onChange='atc()'><option value='Seleccione'>Seleccione</option><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
			out.print("<tr><td width='12%'>Codigo Articulo</td><td width='30%'><label><input name='txtCodigoArticulo' type='text' id='txtCodigoArticulo' size='40' /></label></td>");
			
				out.print("<td>Control Especial</td><td><label><select name='cmbControl' id='cmbControl'><option value='Seleccione'>Seleccione</option><option value='Si'>Si</option><option value='No'>No</option></select></label></td></tr>");
				out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3'></textarea></label></td></tr>");
				out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
				out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Ingresar     ' onClick='IngresarArticulo()'></label></div></td></tr>");
				out.print("<tr><td colspan='4'>&nbsp;</td></tr></table>");
		}
		
		
		if(va.equals("3")){
			out.print("<table  width='100%' class='style6'><tr><td width='12%'>Nombre Generico</td><td  width='30%'><label><input name='txtNombreGenerico' type='text' id='txtNombreGenerico' size='40' value='"+gen+"' readonly /></label></td><td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id=' cmbTipo'onChange='atc()'><option value='Seleccione'>Seleccione</option><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
			out.print("<tr><td width='12%'>Codigo Articulo</td><td width='30%'><label><input name='txtCodigoArticulo' type='text' id='txtCodigoArticulo' size='40'  /></label></td>");
			
				out.print("<td>Control Especial</td><td><label><select name='cmbControl' id='cmbControl'><option value='Seleccione'>Seleccione</option><option value='Si'>Si</option><option value='No'>No</option></select></label></td></tr>");
				out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3'></textarea></label></td></tr>");
				out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
				out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Ingresar     ' onClick='IngresarArticulo()'></label></div></td></tr>");
				out.print("<tr><td colspan='4'>&nbsp;</td></tr></table>");
		}
		
		if(va.equals("ins")){
			out.print("<table  width='100%' class='style6'>");//<tr><td width='12%'>Nombre Generico</td><td  width='30%'><label><input name='txtNombreGenerico' type='text' id='txtNombreGenerico' size='40' /></label></td><td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' onChange='atc()'><option value='Seleccione'>Seleccione</option><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
				out.print("<tr><td width='12%'>Observacion</td><td  width='30%'><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3'></textarea></label></td><td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' ><option value='Seleccione'>Seleccione</option><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
				out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
				out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Ingresar     ' onClick='IngresarArticulo()'></label></div></td></tr>");
				out.print("<tr><td colspan='4'>&nbsp;</td></tr></table>");
		}
		
		if(va.equals("4")){
			out.print("<table  width='100%' class='style6'><tr><td width='12%'>Nombre Generico</td><td  width='30%'><label><input name='txtNombreGenerico' type='text' id='txtNombreGenerico' size='40' value='"+gen+"' readonly /></label></td>");
		    if(tipo.equals("Pos")){
			out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id=' cmbTipo'onChange='atc()'><option value='"+tipo+"'>"+tipo+"</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
		    }else{
		    out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id=' cmbTipo'onChange='atc()'><option value='"+tipo+"'>"+tipo+"</option><option value='Pos'>Pos</option></select></label></td></tr>");
			}
		    
		    out.print("<tr><td width='12%'>Codigo Articulo</td><td width='30%'><label><input name='txtCodigoArticulo' type='text' id='txtCodigoArticulo' size='40'  /></label></td>");
			
			
				out.print("<td>Control Especial</td><td><label><select name='cmbControl' id='cmbControl'><option value='Seleccione'>Seleccione</option><option value='Si'>Si</option><option value='No'>No</option></select></label></td></tr>");
				out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3'></textarea></label></td></tr>");
				out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
				out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Ingresar     ' onClick='IngresarArticulo()'></label></div></td></tr>");
				out.print("<tr><td colspan='4'>&nbsp;</td></tr></table>");
		}
		
		
		if(va.equals("1")){
			//System.out.print("Nombre en crear articulo: "+nombre);
			
			mca.CrearArticulo(codigoArticulo, nombre, concentracion, observacion, grupo, nombreGenerico, cod_unidadFK, tipo, tipoArticulo, cod_formaFK, control);
		
			//crea el articulo en la tabla programas
			Calendar c = new GregorianCalendar();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH));
			String annio = Integer.toString(c.get(Calendar.YEAR));
			int m=Integer.parseInt(mes)+1;
			mes=String.valueOf(m);
			int d=Integer.parseInt(dia);
			if(d<10){dia="0"+d;}
			if(m<10){mes="0"+m;}
			String fecha=annio+"-"+mes+"-"+dia;
			
			Calendar calendario = Calendar.getInstance();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			
			String user = req.getParameter("user");
			String cero="0";
			String blanco="";
			
			//System.out.print("cod_formaFK: "+cod_formaFK);
			//System.out.print("cod_unidadFK: "+cod_unidadFK);
			///////
			String cf="";
			rs3=mca.ObtenerFormaFarmaceuticap(cod_formaFK);
			try {
				if(rs3.next()){
				cf=rs3.getString(2);
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//System.out.print("cf: "+cf);
			String cu="";
			rs2=mca.ObtenerUnidadp(cod_unidadFK);
			try {
				if(rs2.next()){
					cu=rs2.getString(2);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//System.out.print("cu: "+cu);
			//////////
			
			String nombe=nombre+" "+concentracion+" "+cu+" "+cf;  
			
			//System.out.print("Nombre en crear programa: "+nombe);
			
			mca.CrearPrograma(cero, cero, codigoArticulo, blanco, nombe, cero, cero, cero, cero, cero, fecha, hra, user);
			
			String cp="";
			rs=mca.ObtenerPrograma(fecha, hra, user);
			try {
				if(rs.next()){
				cp=rs.getString(1);///aqui va la variable q va a guardar el codigo del programa creado
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			String cm="";
			//System.out.print("Nombre a obtener medicamento: "+nombre);
			rs1=mca.ObtenerMedicamento(codigoArticulo, nombre, concentracion, observacion, grupo, nombreGenerico, cod_unidadFK, tipo, tipoArticulo, cod_formaFK, control);
			try {
				if(rs1.next()){
				cm=rs1.getString(1);///aqui va la variable q va a guardar el codigo del medicamento creado
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			///creo un rs para obtener el cod del medicamento q acabo d crear preferiblemente con los campos mas importantes del insert
			
			//finalmente inserto los dos codigos en la tabla fact_prog_med
			//System.out.print("codigo de crear Progmed: "+cm);
			
			mca.CrearProgMed(cp,cm);

			////para producto////
			mca.CrearProducto(codigoArticulo,nombe,grupo,user,fecha,hra);
			String cproducto="";
			rs1=mca.ObtenerProducto(codigoArticulo,nombe,grupo,user,fecha,hra);
			try {
				if(rs1.next()){
					cproducto=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mca.CrearOrdProgMed(cproducto, cp,cm);
			mca.CrearAsignacionGrupo(cproducto,"2",user,fecha,hra );
			///fin para producto //// 
					
			out.print("Ingreso Exitoso.");
		}
		
		if(va.equals("MP")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Productos en el sistema</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='30%'><u><i><div align='left'>Producto</div></i></u></td><td width='70%'><u><i><div align='left'>Accion</div></i></u></td></tr>");
			//out.print("<table width='100%' border='1' class='style6'>");
			
			String codigo1=null;
			String codigo2=null;
			String codigo3=null;
			
			//Hacer Autocompletar d productos
			out.print("<tr><td><input name='txtTipoMe0' type='text' id='txtTipoMe0' onkeyup='autocompletaMArticulo(0,0,0)' size='39' />");//identificador Div+identificador campo
			out.print("<input name='txtTipoMeH0' type='hidden' id='txtTipoMeH0'/></td>");//identificador Div+identificador campo
			out.print("<input name='grupo' type='hidden' id='grupo'/></td>");//identificador Div+identificador campo
			
			out.print("<td colspan='4'><div><label><input type='button' name='btnMP' id='btnMP' value='Modificar' onClick='MP()' disabled='disabled'></label></div></td></tr>");
			out.print("<tr><td><div id='sugerencias10'></div></td></tr>");
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
			
		}
		
		if(va.equals("MP2")){
			
			if (grupop.equals("2")){
				///////////////////////
				out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Modificar Articulo</div></td></tr>");
				rs=mca.ObtenerArticulo(cart);
				try {
					while(rs.next()){ 
						out.print("<tr><td width='12%'>Nombre Articulo</td><td width='30%'><label><input name='txtNombreArticulo' type='text' id='txtNombreArticulo' size='40' value='"+rs.getString(1)+"' /></label></td>></tr>");
				
						
						if(rs.getString(10)==null){
							out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' ><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
						}else{
						if(rs.getString(10).equals("Pos")){
							out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' ><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
						}else{
							out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' ><option value='No Pos'>No Pos</option><option value='Pos'>Pos</option></select></label></td></tr>");
						}
						}
						out.print("<tr><td>Grupo al que pertenece</td><td><label><select name='cmbGrupo' id='cmbGrupo'><option value='"+rs.getString(11)+"'>"+rs.getString(12)+"</option>");
						rs2=mca.ObtenerGrupos(rs.getString(11));
						out.print("</select></label></td>");
						
						out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3'>"+rs.getString(14)+"</textarea></label></td></tr>");
						out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
						out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Modificar     ' onClick='MPA2("+cart+")'></label></div></td></tr>");	
					
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				//////////////////////
			}else{
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Modificar Articulo</div></td></tr>");
			rs=mca.ObtenerArticulo(cart);
			try {
				while(rs.next()){ 
					out.print("<tr><td width='12%'>Nombre Articulo</td><td width='30%'><label><input name='txtNombreArticulo' type='text' id='txtNombreArticulo' size='40' value='"+rs.getString(1)+"' /></label></td><td width='13%'>Codigo Articulo</td><td width='45%'><label><input name='txtCodigoArticulo' type='text' id='txtCodigoArticulo' size='40' value='"+rs.getString(2)+"' /></label></td></tr>");
			//otro rs para obtener forma farmaceutica
					out.print("<tr><td>Concentracion</td><td><label><input name='txtConcentracion' type='text' id='txtConcentracion' size='40' value='"+rs.getString(3)+"'/><td>Forma Farmaceutica</td><td><label><select name='cmbFormaFarmaceutica' id='cmbFormaFarmaceutica'><option value='"+rs.getString(4)+"'>"+rs.getString(5)+"</option>");
					rs3=mca.ObtenerFormaFarmaceutica(rs.getString(4));
					try {
						while(rs3.next()){
						out.print("<option value="+rs3.getString(1)+">"+rs3.getString(2)+"</option>");
						}
						rs3.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td></tr>");
				
					out.print("<tr><td>Unidad de Medida</td><td><label><select name='cmbUnidad' id='cmbUnidad'><option value='"+rs.getString(6)+"'>"+rs.getString(7)+"</option>");
					rs1=mca.ObtenerUnidad(rs.getString(6));
					try {
						while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					if(rs.getString(8)==null){
						out.print("<td>Tipo de Articulo</td><td><label><select name='cmbTipoA' id='cmbTipoA' ><option value='Generico'>Generico</option><option value='Comercial'>Comercial</option></select></label></td>");	
					}else{
					if(rs.getString(8).equals("Comercial")){
						out.print("<td>Tipo de Articulo</td><td><label><select name='cmbTipoA' id='cmbTipoA' ><option value='Comercial'>Comercial</option><option value='Generico'>Generico</option></select></label></td>");
					}else{
						out.print("<td>Tipo de Articulo</td><td><label><select name='cmbTipoA' id='cmbTipoA' ><option value='Generico'>Generico</option><option value='Comercial'>Comercial</option></select></label></td>");
					}
					}
					out.print("<tr><td width='12%'>Nombre Generico</td><td  width='30%'><label><input name='txtNombreGenerico' type='text' id='txtNombreGenerico' size='40' value='"+rs.getString(9)+"'/></label></td>");
					if(rs.getString(10)==null){
						out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' ><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
					}else{
					if(rs.getString(10).equals("Pos")){
						out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' ><option value='Pos'>Pos</option><option value='No Pos'>No Pos</option></select></label></td></tr>");
					}else{
						out.print("<td width='13%'>Clasificacion</td><td width='45%'><label><select name='cmbTipo' id='cmbTipo' ><option value='No Pos'>No Pos</option><option value='Pos'>Pos</option></select></label></td></tr>");
					}
					}
					out.print("<tr><td>Grupo al que pertenece</td><td><label><select name='cmbGrupo' id='cmbGrupo'><option value='"+rs.getString(11)+"'>"+rs.getString(12)+"</option>");
					rs2=mca.ObtenerGrupos(rs.getString(11));
					try {
						while(rs2.next()){
						out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td>");
					if(rs.getString(13)==null){
						out.print("<td>Control Especial</td><td><label><select name='cmbControl' id='cmbControl'><option value='No'>No</option><option value='Si'>Si</option></select></label></td></tr>");
					}else{
					if(rs.getString(13).equals("Si")){
						out.print("<td>Control Especial</td><td><label><select name='cmbControl' id='cmbControl'><option value='Si'>Si</option><option value='No'>No</option></select></label></td></tr>");
					}else{
						out.print("<td>Control Especial</td><td><label><select name='cmbControl' id='cmbControl'><option value='No'>No</option><option value='Si'>Si</option></select></label></td></tr>");
					}
					}
					out.print("<tr><td>Observacion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='32' rows='3'>"+rs.getString(14)+"</textarea></label></td></tr>");
					out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
					out.print("<tr><td colspan='4'><div align='center'><label><input type='button' name='btnIngresarArticulo' id='btnIngresarArticulo' value='     Modificar     ' onClick='MPA("+cart+")'></label></div></td></tr>");	
				
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		}//fin grupo =1
		if(va.equals("100")){
			
			mca.ModificarArticulo(cart,codigoArticulo, nombre, concentracion, observacion, grupo, nombreGenerico, cod_unidadFK, tipo, tipoArticulo, cod_formaFK, control);
			
			//////////////////////////
			
			//modificar el articulo en la tabla programas
			Calendar c = new GregorianCalendar();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = Integer.toString(c.get(Calendar.MONTH));
			String annio = Integer.toString(c.get(Calendar.YEAR));
			int m=Integer.parseInt(mes)+1;
			mes=String.valueOf(m);
			int d=Integer.parseInt(dia);
			if(d<10){dia="0"+d;}
			if(m<10){mes="0"+m;}
			String fecha=annio+"-"+mes+"-"+dia;
			
			Calendar calendario = Calendar.getInstance();
			int hora, minutos, segundos;
			hora =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora+":"+minutos+":"+segundos;
			
			String user = req.getParameter("user");
			String cero="0";
			String blanco="";
			
			//System.out.print("cod_formaFK: "+cod_formaFK);
			//System.out.print("cod_unidadFK: "+cod_unidadFK);
			///////
			String cf="";
			rs3=mca.ObtenerFormaFarmaceuticap(cod_formaFK);
			try {
				if(rs3.next()){
				cf=rs3.getString(2);
				}
				rs3.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//System.out.print("cf: "+cf);
			String cu="";
			rs2=mca.ObtenerUnidadp(cod_unidadFK);
			try {
				if(rs2.next()){
					cu=rs2.getString(2);
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			//System.out.print("cu: "+cu);
			//////////
			
			String nombe=nombre+" "+concentracion+" "+cu+" "+cf;  
			
			
			String cm="";
			rs1=mca.ObtenerMedicamento(codigoArticulo, nombre, concentracion, observacion, grupo, nombreGenerico, cod_unidadFK, tipo, tipoArticulo, cod_formaFK, control);
			try {
				if(rs1.next()){
				cm=rs1.getString(1);///aqui va la variable q va a guardar el codigo del medicamento creado
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			} 
			
			String cp="";
			rs=mca.ObtenerProgramaPM(cm);
			try {
				if(rs.next()){
				cp=rs.getString(1);///aqui va la variable q va a guardar el codigo del programa creado
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			
			
			mca.ModificarPrograma(codigoArticulo, nombe, fecha, hra, user, cp);
			/////////////////////////////
			
			out.print("Modificación Exitosa!!!.");
		}
		
		
		if(va.equals("CP")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Productos en el sistema</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='30%'><u><i><div align='left'>Criterio de Busqueda</div></i></u></td><td width='70%'><u><i><div align='left'>Accion</div></i></u></td></tr>");
			//out.print("<table width='100%' border='1' class='style6'>");
			out.print("<tr><td><input name='txtTipoMe0' type='text' id='txtTipoMe0' size='39' />");//identificador Div+identificador campo
			out.print("<td colspan='4'><div><label><input type='button' name='btnMP' id='btnMP' value='Consultar' onClick='CP()' ></label></div></td></tr>");
			out.print("<tr><td><div id='sugerencias10'></div></td></tr>");
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
			
		}
		
		
		if(va.equals("CP2")){
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Productos en el sistema</div></td></tr>");
			out.print("<table width='100%' border='0' class='style6'><tr><td width='30%'><u><i><div align='left'>Criterio de Busqueda</div></i></u></td><td width='70%'><u><i><div align='left'>Accion</div></i></u></td></tr>");
			out.print("<tr><td><input name='txtTipoMe0' type='text' id='txtTipoMe0' size='39' value='"+cart+"' />");//identificador Div+identificador campo
			out.print("<td colspan='4'><div><label><input type='button' name='btnMP' id='btnMP' value='Consultar' onClick='CP()' ></label></div></td></tr>");
			
			
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Consultar Articulos</div></td></tr>");
			out.print("<table width='100%' border='1' class='style6'><tr><u><i><td width='35%'><div align='center'>Articulo</div></i></u></td><td width='25%'><div align='center'>Concentracion</div></td><td width='20%'><div align='center'>Presentación</div></td><td width='20%'><div align='center'>Observación</div></td></tr>");
			int x=0;
			try {
				rs =me.listarArticulos(cart);
				while(rs.next()){
					x++;
					out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td></tr>");
				}
				if(x==0){out.print("<tr><td>No existen resultados para los criterios suministrados...</td></tr>");}
					rs.getStatement().getConnection().close();
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				}
				
		}
		
		
		
		if(va.equals("autoart")){
			try {
				rs =me.listarArticulos(texto);
				String cadena ="";
				cadena="[";
				while(rs.next()){
					cadena = cadena+"'"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+"|"+rs.getString(1)+"'";
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
