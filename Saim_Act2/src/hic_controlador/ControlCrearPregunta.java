/**
 * controlador: ControlCrearPregunta se encuentra la forma en que son 
 * creadas las preguntas las cuales posteriormete se les asigna un area para completar el formato.
 */
package hic_controlador;


import hic_metodo.MetodoCrearPregunta;
import hic_metodo.MetodoIngresarResultados;
import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControlCrearPregunta
 */
public class ControlCrearPregunta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/**
		 * autocompletar de los diferentes tipo de respuesta, tienen como parametro
		 * el nombre del tipo de respuesta.
		 */
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		MetodoCrearPregunta mcp = new MetodoCrearPregunta ();

	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){          

        try {
            rs =mcp.listarParaAutocompletarTipoRespuesta(cad);
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
		PrintWriter out=res.getWriter();
		ResultSet rs=null;	
		ResultSet rs1=null;
		MetodoCrearPregunta mcp= new MetodoCrearPregunta();
		String nombrePregunta=req.getParameter("NombrePregunta");
		String codigo_tiporespuesta_fk=req.getParameter("CodTipoRespuesta");
		String PatronNormal=req.getParameter("PatronNormal");
		String UnidadPregunta=req.getParameter("UnidadPregunta");
		String TipoRequerimiento=req.getParameter("TipoRequerimiento");
		String codigo_pregunta_fk="";
		if(va.equals("0")){
			/**
			 * Aqui se llena el formulario apenas se carga  hic_crearPregunta.jsp
			 */
			//out.println("<table width='100%' class='style6' ><tr><td colspan='3' id='cabecera2'><span class='style11'><div align='center'>CREAR PREGUNTA</div> </span></td></tr><tr><td colspan='3'><span >NOMBRE DE LA PREGUNTA </span></td></tr><tr><td colspan='3'><input name='txtNomPregunta' type='text'  id='txtNomPregunta' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr><tr><td colspan='3' >RESPUESTA AUTOMATICA </td></tr><tr><td colspan='3' ><input name='txtPatronNormal' type='text'  id='txtPatronNormal' onkeyup='this.value=this.value.toUpperCase();' size='70' /></td></tr><tr><td colspan='3' >UNIDAD DE LA PREGUNTA </td></tr><tr><td colspan='3' ><input name='txtUnidadPregunta' type='text' id='txtUnidadPregunta' size='70' maxlength='10'  /><span class='style8'></span></td></tr><tr><td colspan='3' >TIPO DE RESPUESTA </td></tr><tr><td width='565' ><input name='txtTipoRespuesta' type='text' id='txtTipoRespuesta' size='70' onkeyup='autocompletaTipoRespuesta()' /><span class='style8'> *</span></td><td width='228' align='center' ><input name='btnCrearCerrada' type='button' class='boton4' id='btnCrearCerrada' onclick='CrearCerrada()' value='Crear'  align='middle'/></td><td align='center'><input name='txtCodTipoRespuesta' type='hidden' id='txtCodTipoRespuesta' size='10' /></td></tr><tr><td align='center'><div id='sugerencias'></div></td><td align='center' class='style3'><span class='style8'>Datos Requeridos * </span></td><td align='center'>&nbsp;</td></tr></table>");
			out.print("<table width='100%' class='style6' ><tr><td colspan='3' id='cabecera2'><span class='style11'><div align='center'>CREAR PREGUNTA</div> </span></td></tr><tr><td colspan='3'><span >NOMBRE DE LA PREGUNTA </span></td></tr><tr><td colspan='3'><input name='txtNomPregunta' type='text'  id='txtNomPregunta' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr><tr><td colspan='3' >RESPUESTA AUTOMATICA </td></tr><tr><td colspan='3' ><input name='txtPatronNormal' type='text'  id='txtPatronNormal' onkeyup='this.value=this.value.toUpperCase();' size='70' /></td></tr><tr><td colspan='3' >UNIDAD DE LA PREGUNTA </td></tr><tr><td colspan='3' ><input name='txtUnidadPregunta' type='text' id='txtUnidadPregunta' size='70' maxlength='10'  /><span class='style8'></span></td></tr><tr><td >TIPO DE RESPUESTA </td><td colspan='2' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input name='chkRequerido' type='checkbox' id='chkRequerido' value='1' />Requerida</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input name='chkEpicrisis' type='checkbox' id='chkEpicrisis' value='2' />Epicrisis</label></td></tr><tr><td width='446' ><input name='txtTipoRespuesta' type='text' id='txtTipoRespuesta' size='70' onkeyup='autocompletaTipoRespuesta()' /><span class='style8'> *</span></td><td width='347' align='center' ><input name='btnCrearCerrada' type='button' class='boton4' id='btnCrearCerrada' onclick='IngresarPregunta()' value='Crear'  align='middle'/></td><td width='277' align='center'><input name='txtCodTipoRespuesta' type='hidden' id='txtCodTipoRespuesta' size='10' /></td></tr><tr><td align='center'><div id='sugerencias'></div></td><td align='center' class='style3'><span class='style8'>Datos Requeridos * </span></td><td align='center'>&nbsp;</td></tr></table>");
		}		
		if(va.equals("1")){
			/**
			 * se busca si el nombre de la pregunta ya existe en la base de datos.
			 */
			String CodPregunta=null;
			try {
				rs1=mcp.ObtenerCodigoPregunta(nombrePregunta);
				if(rs1.next()){
					CodPregunta=rs1.getString(1);
				}
				rs1.getStatement().getConnection().close();
			} catch (Exception e1) {
				System.out.println("ERROR en el ResultSet 1 "+e1);
				e1.printStackTrace();
			}
			if(CodPregunta==null){
			mcp.CrearPregunta(nombrePregunta,PatronNormal,UnidadPregunta,TipoRequerimiento);
			try {
				rs=mcp.ObtenerCodigoPregunta(nombrePregunta);
				if(rs.next()){
					codigo_pregunta_fk=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				mcp.RelacionPreguntaTipoRespuesta(codigo_tiporespuesta_fk, codigo_pregunta_fk);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			else{
				out.print("Ya Existe una Pregunta Con Este Nombre");
				out.print("Por Favor Intente Con Otro Nombre");
			}
		}
		
		/****************************************************************************************/
		/****************************************************************************************/
		MetodoVerFormatos mvf = new MetodoVerFormatos ();
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		
		if(va.equals("GFCC")){
			String FechaFormato=req.getParameter("FechaFormato");
			String HoraFormato=req.getParameter("HoraFormato");
			String CodAdmision=req.getParameter("CodAdmision");
			
			mvf.GuardarFormatoHCcontingencia(FechaFormato, HoraFormato, CodAdmision);
			mvf.GuardarDetalleFormatoHCcontingencia(FechaFormato, HoraFormato, CodAdmision);
		}
		if(va.equals("ODF")){
			String CodDetFormula=req.getParameter("CodDetalle");
			String txtCodFormula=req.getParameter("txtCodFormula");
			
			mvf.OmitirDetalleFormulacionC(CodDetFormula);
			
			try {
				out.print("<table width='100%' border='1'><tr><td>Nombre Medicamento</td><td>Dosis</td><td>Accion</td></tr>");
				rs1=mvf.ConsultarDetalleFormulaciones(txtCodFormula);
				while(rs1.next()){
					out.print("<tr><td>"+rs1.getString("nombre")+"</td><td>"+rs1.getString("dosis")+"</td><td><a href='#' onclick='omitirDetalle("+rs1.getString("codigo")+")'>Omitir<a></td></tr>");
					}
				rs1.getStatement().getConnection().close();
				out.print("<tr><td><input name='txtCodFormula' type='hidden' id='txtCodFormula' value="+txtCodFormula+" /></td></tr></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("GFR")){
			String cmbCodAdm=req.getParameter("cmbCodAdm");
			String CodUsuario=req.getParameter("CodUsuario");
			//String TipoFormato=req.getParameter("TipoFormato");
			String txtFecha=req.getParameter("txtFecha");
			String HoraFormato=req.getParameter("HoraFormato");
			String txtCodPac=req.getParameter("txtCodPac");
			String txtCodFormula=req.getParameter("txtCodFormula");
			/////////////////////////////////////////////////
			String txtObservacion=req.getParameter("txtObservacion");
			String cmbMedicamento=req.getParameter("cmbMedicamento");
			String txtCantidad=req.getParameter("txtCantidad");
			String txtDosis=req.getParameter("txtDosis");
			String cmbUnidad=req.getParameter("cmbUnidad");
			String cmbViaAdm=req.getParameter("cmbViaAdm");
			String cmbLapso=req.getParameter("cmbLapso");
			//insertar en hic_formulacion
			if(txtCodFormula.equals("0")){
				rs=mvf.BuscarCamaAreaSubarea(cmbCodAdm);
				try {
					if(rs.next()){
						mvf.CrearFormulacion(txtCodPac, cmbCodAdm, txtObservacion, "1", CodUsuario, rs.getString("cen_numero_cama"), rs.getString("codigo"), rs.getString("codsubarea"), HoraFormato, txtFecha);
					}
					rs.getStatement().getConnection().close();
					rs=mvf.ConsultarCodigoFormulacion(cmbCodAdm, txtFecha, HoraFormato);
					if(rs.next()){
						String dosificacion="Dar "+txtCantidad+" cada "+cmbLapso+" Horas. "+cmbViaAdm;						
						mvf.CrearDetalleFormulacion(rs.getString("codigo"), cmbMedicamento, txtCantidad, dosificacion, "-", "1", "(-)", "1 Dia(s)", txtDosis, cmbUnidad, CodUsuario);
						out.print("<table width='100%' border='1'><tr><td>Nombre Medicamento</td><td>Dosis</td><td>Accion</td></tr>");
						rs1=mvf.ConsultarDetalleFormulaciones(rs.getString("codigo"));
						while(rs1.next()){
							out.print("<tr><td>"+rs1.getString("nombre")+"</td><td>"+rs1.getString("dosis")+"</td><td><a href='#' onclick='omitirDetalle("+rs1.getString("codigo")+")'>Omitir<a></td></tr>");
						}
						rs1.getStatement().getConnection().close();
						out.print("<tr><td><input name='txtCodFormula' type='hidden' id='txtCodFormula' value="+rs.getString("codigo")+" /></td></tr></table>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}else{
				try {
					String dosificacion="Dar "+txtCantidad+" cada "+cmbLapso+" Horas. "+cmbViaAdm;						
					mvf.CrearDetalleFormulacion(txtCodFormula, cmbMedicamento, txtCantidad, dosificacion, "-", "1", "(-)", "1 Dia(s)", txtDosis, cmbUnidad, CodUsuario);
					out.print("<table width='100%' border='1'><tr><td>Nombre Medicamento</td><td>Dosis</td><td>Accion</td></tr>");
					rs1=mvf.ConsultarDetalleFormulaciones(txtCodFormula);
					while(rs1.next()){
						out.print("<tr><td>"+rs1.getString("nombre")+"</td><td>"+rs1.getString("dosis")+"</td><td><a href='#' onclick='omitirDetalle("+rs1.getString("codigo")+")'>Omitir<a></td></tr>");
						}
					rs1.getStatement().getConnection().close();
					out.print("<tr><td><input name='txtCodFormula' type='hidden' id='txtCodFormula' value="+txtCodFormula+" /></td></tr></table>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		if(va.equals("GPC")){
			String cmbCodAdm=req.getParameter("cmbCodAdm");
			String CodUsuarioC=req.getParameter("CodUsuario");
			String TipoFormato2=req.getParameter("TipoFormato");
			String txtFecha=req.getParameter("txtFecha");
			String HoraFormatoC=req.getParameter("HoraFormato");
			String txtCodPac=req.getParameter("txtCodPac");
			MetodoIngresarResultados mir = new MetodoIngresarResultados();
			if(TipoFormato2.equals("Formu")){
				out.print("<table width='100%' border='1' ><tr><td colspan='8' align='center' >FORMULACION</td></tr>");
				out.print("<tr><td>Observacion</td><td colspan='7'><textarea id='txtObservacion'></textarea></td></tr>");
				out.print("<tr><td>Med</td><td><select id='cmbMedicamento' > " +
						"<option value='Seleccione' selected='' >Seleccione</option>");
				rs=mvf.ListadoMedicamentosInsumos();
				try {
					while(rs.next()){
						out.print("<option value="+rs.getString("codigo")+">"+rs.getString("Medicamento")+"</option>");
					}
					out.print("</select></td><td>Cant<input name='txtCantidad' type='hiden' size='5' id='txtCantidad' /></td>" +
							"<td>Dosis<input name='txtDosis' size='5' type='hiden' id='txtDosis' /></td>" +
							"<td>UD<select id='cmbUnidad' ><option value='Sel'>Sel</option> ");
					rs1=mvf.ListadoUnidades();
					while(rs1.next()){
						out.print("<option value="+rs1.getString("sigla")+">"+rs1.getString("sigla")+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td>" +
							"<td>VIA<select id='cmbViaAdm'><option value='Sel' selected='' >Sel</option>");
					rs1=mvf.ListarViasAdministracion();
					while(rs1.next()){
						out.print("<option value='"+rs1.getString("nombre")+"'>"+rs1.getString("nombre")+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td> " +
							"<td>Lapso<select id='cmbLapso'><option value='Sel'>Sel</option><option value='4'>4</option> " +
							"<option value='6'>6</option><option value='8'>8</option> " +
							"<option value='12'>12</option><option value='24'>24</option> " +
							"</select></td><td><input name='btnFinalizar' type='button' id='btnFinalizar' value='..' onclick='CrearFormulacion()' /></td></tr></table>");
					out.print("<table width='100%' border='1' ><tr><td id='DetalleFormula'><input name='txtCodFormula' type='hidden' id='txtCodFormula' value='0' /></td></tr></table>");
					//
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(TipoFormato2.equals("OrdMed")){
				out.print("Ordenes Medicas");
			}
			
			if((!TipoFormato2.equals("OrdMed"))&&(!TipoFormato2.equals("Formu"))){
				out.print("Formato de HC");
				String CodigoArea="";
				String CodigoPregunta="";
				/**
				 * se guarda la relacion del formato con la admision y el paciente.... 
				 */
				if(TipoFormato2!=""){
					rs3=mvf.ObtenerTipoFormato(TipoFormato2);
					try {
						String resultados="";
						mvf.RelacionFormatoAdmisionPaciente(TipoFormato2, cmbCodAdm, txtCodPac, HoraFormatoC, txtFecha, CodUsuarioC);
							rs1=mvf.ObtenerAreaFormato(TipoFormato2,txtFecha,HoraFormatoC);							
								while(rs1.next()){									
									CodigoArea=rs1.getString(1);
									rs2=mvf.ObtenerPreguntasArea(CodigoArea);									
									while(rs2.next()){										
										resultados=rs2.getString(6);										
										String estado="0";
										CodigoPregunta=rs2.getString(4);
										String TipoPregunta=rs2.getString(2);
										if(TipoPregunta.equals("1")){
											/**si tipo pregunta es igual a un texto largo*/
											mir.IngresarHistoria(txtCodPac, cmbCodAdm, CodigoPregunta, resultados, estado, txtFecha, HoraFormatoC, CodUsuarioC, CodigoArea, TipoFormato2);
										}
										if(TipoPregunta.equals("2")){
											/**si tipo pregunta es igual a un tipo seleccion*/
											resultados="Seleccione";
											mir.IngresarHistoria(txtCodPac, cmbCodAdm, CodigoPregunta, resultados, estado, txtFecha, HoraFormatoC, CodUsuarioC, CodigoArea, TipoFormato2);
											
										}
										if(TipoPregunta.equals("3")){
											/**si tipo pregunta es igual a un texto corto */
											mir.IngresarHistoria(txtCodPac, cmbCodAdm, CodigoPregunta, resultados, estado, txtFecha, HoraFormatoC, CodUsuarioC, CodigoArea, TipoFormato2);
										}
										if(TipoPregunta.equals("4")){
											/**si tipo pregunta es igual a un autocompletar de tipo diagnostico */
											mir.IngresarHistoria(txtCodPac, cmbCodAdm, CodigoPregunta, resultados, estado, txtFecha, HoraFormatoC, CodUsuarioC, CodigoArea, TipoFormato2);
										}
										if(TipoPregunta.equals("5")){
											/**si tipo pregunta es igual a un autocompletar de tipo diagnostico varios */
											mir.IngresarHistoria(txtCodPac, cmbCodAdm, CodigoPregunta, resultados, estado, txtFecha, HoraFormatoC, CodUsuarioC, CodigoArea, TipoFormato2);
										}
									}
									rs2.getStatement().getConnection().close();
								}
								rs1.getStatement().getConnection().close();
					
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
				}
				/**
				 * una ves guardada la relacion se procede a mostrar los formatos - 
				 */
				String NombreArea="";
				int contador=0;
				try {		
					rs4=mvf.NombreFormatos(TipoFormato2);
					if(rs4.next()){
						out.print("<table width='100%'><tr><td align='center'>"+rs4.getString("nombre_formato")+"<input name='btnFinalizar' type='button' value='Guardar Formato' onclick='GuardarFormatoHCC()' /></td></tr></table>");
					}
					rs4.getStatement().close();
					rs4=mvf.ObtenerAreasFormato(TipoFormato2);
					out.print("<table width='100%'><tr id='pestanas'>");
					while(rs4.next()){	
						contador=contador+1;
						NombreArea=rs4.getString(2);
						out.print("<td ><div  id='ar"+contador+"' class='style11 cabecera2gris' align='center'><a href='#' onclick='MostrarPreguntasPH("+rs4.getString(1)+","+contador+")' style='color:#ffffff'>"+NombreArea+"</a></div></td>");
					}

					out.print("</tr><tr><td colspan="+contador+"><div id='formu'></div></td></tr></table>");
							
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
		
			}
		}
		/********************************************************************************************/
		if(va.equals("GBPCI")){
			String NumeroDocumento=req.getParameter("NumeroDocumento");
			String txtResultadoImg=req.getParameter("txtResultadoImg");
			String FechaSql=req.getParameter("FechaSql");
			String cmbExamen=req.getParameter("cmbExamen");
			String txtCodPac=req.getParameter("txtCodPac");
			java.util.Date FechaAc1 = new java.util.Date();
			java.sql.Time Hora_Insercion1 = new java.sql.Time(FechaAc1.getTime());
			mvf.CrearImagenologia(txtCodPac, cmbExamen, FechaSql, Hora_Insercion1+"", "1", txtResultadoImg, "JAIME AMAYA", "0", "JAIME AMAYA", "JAIME AMAYA", FechaSql, Hora_Insercion1+"", "-", "LO DESCRITO", "", NumeroDocumento);
			
		}
		if(va.equals("BPCI")){
			String TipoDocumento=req.getParameter("TipoDocumento");
			String NumeroDocumento=req.getParameter("NumeroDocumento");
			
			rs=mvf.PacienteContingencia(TipoDocumento, NumeroDocumento);
			try {
				out.print("<table width='100%' border='1' >");
				if(rs.next()){
					out.print("<tr><td>Nombre Paciente:<input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString("pac_codigo_paciente")+"  /></td><td>"+rs.getString("NombrePaciente")+"</td><td>Entidad:</td><td>"+rs.getString("nombre_entidad")+"</td>");


					out.print("<td></td></tr>");
					out.print("<tr><td>Examen</td><td><select id='cmbExamen'><option selected='' value='Seleccione' >Seleccione</option>");
					rs1=mvf.BuscarImagenologiaExamenes();
					while(rs1.next()){
						out.print("<option value="+rs1.getString("codigo")+">"+rs1.getString("nombre")+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td><td>Fecha</td><td><input name='txtFecha' type='text' id='txtFecha' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td></tr>");
					out.print("<tr><td>Resultado</td><td><textarea cols='80' rows='10' id='txtResultadoImg'></textarea></td></tr>");
					out.print("<tr><td><input name='btnFinalizar' type='button' id='btnFinalizar' value='Crear' onclick='CrearImagenesContingencia()' /></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*******************************************************************************************/
		if(va.equals("BPC")){
			String TipoDocumento=req.getParameter("TipoDocumento");
			String NumeroDocumento=req.getParameter("NumeroDocumento");
			
			rs=mvf.PacienteContingencia(TipoDocumento, NumeroDocumento);
			try {
				out.print("<table width='100%' border='1' >");
				if(rs.next()){
					out.print("<tr><td>Nombre Paciente:<input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString("pac_codigo_paciente")+"  /></td><td>"+rs.getString("NombrePaciente")+"</td><td>Entidad:</td><td>"+rs.getString("nombre_entidad")+"</td><td>Fecha Ingreso</td><td><select id='cmbCodAdm' ><option value='Seleccione' selected=''>Seleccione</option>");
					rs1=mvf.PacienteContingencia(TipoDocumento, NumeroDocumento);
					while(rs1.next()){
						out.print("<option value="+rs1.getString("adm_numero_ingreso")+">"+rs1.getString("fecha_registro")+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td><td></td></tr>");
					out.print("<tr><td>Usuario</td><td><select id='CodUsuario'><option selected='' value='Seleccione' >Seleccione</option>");
					rs1=mvf.BuscarUsuariosFormatosContingencia();
					while(rs1.next()){
						out.print("<option value="+rs1.getString("usu_codigo")+">"+rs1.getString("Usuario")+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td><td>Tipo de Formato</td><td><select id='TipoFormato' ><option value='Seleccione' selected='' >Seleccione</option>" +
							"<option value='Formu' >FORMULACION</option>" +
							//"<option value='OrdMed' >ORDENES MEDICAS</option>" +
							"");
					rs1=mvf.CargarFormatos();
					while(rs1.next()){
						out.print("<option value="+rs1.getString("codigo")+">"+rs1.getString("nombre_formato")+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td><td>Fecha<input name='txtFecha' type='text' id='txtFecha' onKeyUp='masca(this,&quot;/&quot;,patron,true)' ></td>");
					out.print("<td>Hora" +
							"<select id='cmbHora'><option value='HH'>HH</option>" +
							"<option value='00'>00</option>" +
							"<option value='01'>01</option>" +
							"<option value='02'>02</option>" +
							"<option value='03'>03</option>" +
							"<option value='04'>04</option>" +
							"<option value='05'>05</option>" +
							"<option value='06'>06</option>" +
							"<option value='07'>07</option>" +
							"<option value='08'>08</option>" +
							"<option value='09'>09</option>" +
							"<option value='10'>10</option>" +
							"<option value='11'>11</option>" +
							"<option value='12'>12</option>" +
							"<option value='13'>13</option>" +
							"<option value='14'>14</option>" +
							"<option value='15'>15</option>" +
							"<option value='16'>16</option>" +
							"<option value='17'>17</option>" +
							"<option value='18'>18</option>" +
							"<option value='19'>19</option>" +
							"<option value='20'>20</option>" +
							"<option value='21'>21</option>" +
							"<option value='22'>22</option>" +
							"<option value='23'>23</option>" +
							
							"</select>");
					out.print("<select id='cmbMinuto'><option value='MM'>MM</option>" +
							"<option value='00'>00</option>" +
							"<option value='01'>01</option>" +
							"<option value='02'>02</option>" +
							"<option value='03'>03</option>" +
							"<option value='04'>04</option>" +
							"<option value='05'>05</option>" +
							"<option value='06'>06</option>" +
							"<option value='07'>07</option>" +
							"<option value='08'>08</option>" +
							"<option value='09'>09</option>" +
							"<option value='10'>10</option>" +
							"<option value='11'>11</option>" +
							"<option value='12'>12</option>" +
							"<option value='13'>13</option>" +
							"<option value='14'>14</option>" +
							"<option value='15'>15</option>" +
							"<option value='16'>16</option>" +
							"<option value='17'>17</option>" +
							"<option value='18'>18</option>" +
							"<option value='19'>19</option>" +
							"<option value='20'>20</option>" +
							"<option value='21'>21</option>" +
							"<option value='22'>22</option>" +
							"<option value='23'>23</option>" +
							"<option value='24'>24</option>" +
							"<option value='25'>25</option>" +
							"<option value='26'>26</option>" +
							"<option value='27'>27</option>" +
							"<option value='28'>28</option>" +
							"<option value='29'>29</option>" +
							"<option value='30'>30</option>" +
							"<option value='31'>31</option>" +
							"<option value='32'>32</option>" +
							"<option value='33'>33</option>" +
							"<option value='34'>34</option>" +
							"<option value='35'>35</option>" +
							"<option value='36'>36</option>" +
							"<option value='37'>37</option>" +
							"<option value='38'>38</option>" +
							"<option value='39'>39</option>" +
							"<option value='40'>40</option>" +
							"<option value='41'>41</option>" +
							"<option value='42'>42</option>" +
							"<option value='43'>43</option>" +
							"<option value='44'>44</option>" +
							"<option value='45'>45</option>" +
							"<option value='46'>46</option>" +
							"<option value='47'>47</option>" +
							"<option value='48'>48</option>" +
							"<option value='49'>49</option>" +
							"<option value='50'>50</option>" +
							"<option value='51'>51</option>" +
							"<option value='52'>52</option>" +
							"<option value='53'>53</option>" +
							"<option value='54'>54</option>" +
							"<option value='55'>55</option>" +
							"<option value='56'>56</option>" +
							"<option value='57'>57</option>" +
							"<option value='58'>58</option>" +
							"<option value='59'>59</option>" +
							"</select></td>");
					out.print("<td><input name='btnFinalizar' type='button' id='btnFinalizar' value='Crear' onclick='CrearFormatoContingencia()' /></td></tr></table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/****************************************************************************************/
		/****************************************************************************************/

	}
}
