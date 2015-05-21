/**
 * controlador: ControlVerFormatos se encuentra la forma en que se 
 * llenan las plantillas de los formatos que se le asignaron al paciente
 * para la posterior insercion asi como la visualizacion de los demas 
 * formatos y estudios.
 *  
*/
package hic_controlador;


import fact_metodo.MetodoSeguimientoAdmision;
import hic_metodo.MetodoIngresarResultados;
import hic_metodo.MetodoLaboratoriosHistoria;
import hic_metodo.MetodoVerFormatos;
import hic_metodo.MetodoMultiplePacientes;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;




/**
 * Servlet implementation class Controimg_area
 */
public class ControlVerFormatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	/**
	 * autocompletar de los formatos, salen todas las coincidencias de los formatos
	 * existentes en la base de datos.
	 */
		res.setContentType("text/html;charset=UTF-8");
		ResultSet rs=null;	
		String cad =req.getParameter("codigo");  
		String CodigoUsuario=req.getParameter("CodigoUsuario");
		MetodoVerFormatos mvf = new MetodoVerFormatos ();

	 int accion;
     
        accion = Integer.parseInt(req.getParameter("txtAccion"));
if(accion == 26){          

        try {
            rs =mvf.listarParaAutocompletarFormato(cad,CodigoUsuario);
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
		String CodFormato=req.getParameter("CodFormato");
		String CodArea=req.getParameter("CodArea");
		String CodPac=req.getParameter("CodPac");
		String CodAdm=req.getParameter("CodAdm");
		String CodUsuario=req.getParameter("CodUsuario");
		String HoraFormato=req.getParameter("HoraFormato");
		String FechaFormato=req.getParameter("FechaFormato");
		String CodMedicamento=req.getParameter("CodMedicamento");
		String va=req.getParameter("valor");
		MetodoVerFormatos mvf = new MetodoVerFormatos ();
		MetodoLaboratoriosHistoria mlh=new MetodoLaboratoriosHistoria();
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
	
		
		String codPac=req.getParameter("codPac");
		String codAdm=req.getParameter("codAdm");
		String observacion=req.getParameter("observacion");
		String DetOrden=req.getParameter("DetOrden");
		String Codusuario=req.getParameter("usuario");
		String codCama=req.getParameter("codCama");
		String codArea=req.getParameter("codArea");
		String codSubarea=req.getParameter("codSubarea");
		MetodoSeguimientoAdmision msa =new MetodoSeguimientoAdmision();
		MetodoMultiplePacientes mmp = new MetodoMultiplePacientes();
		//////////////////////////////////////////////
		String codFormulacion_fk=req.getParameter("codFormulacion_fk");
		String codigoMed=req.getParameter("codigoMed");
		String cantidad=req.getParameter("cantidad");
		String dosificacion=req.getParameter("dosificacion");
		//////////////////////////////////////////////
		String codDetFormulacion_fk="";
		//req.getParameter("codDetFormulacion_fk");
		//////////////////////////////////////////////
		String VigeTto=req.getParameter("VigeTto");
		
		int contador=0;
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		
		
		//##################
		if(va.equals("ordTraslado")){
			out.print("<table width='100%' border='1'>");			
			out.print("<tr><td colspan='6'><div align='center' id='cabecera2' class='style11'><span>Solicitar Traslado Paciente </span></div></td></tr>");
			out.print("<tr><td ><div><p><p>Motivo Traslado<p></div></td><td colspan='3'><textarea name='txtDetTrasladoOrden' cols='50' rows='4' id='txtDetTrasladoOrden' ></textarea></td></tr>");
			out.print("<tr><td>TRASLADAR  A:</td><td><label><input name='rbTraslado' type='radio' value='observacion' id='transObs' >Observacion </label></td></tr>" +
			"<tr><td>HOSPITALIZAR EN: </td><td><label><input name='rbTraslado' type='radio' value='Sala General' id='TransSala' >sala general </label></td>");
	        out.print("<td><label><input name='rbTraslado' type='radio' value='Cuidado Intermedio' id='TransInter' >cuidado intermedio </label></td><td><label><input name='rbTraslado' type='radio' value='Cuidado Intensivo' id='transInten' >cuidado intensivo </label></td></tr>");
	        out.print("<tr><td colspan='5'><div align='center'><input name='btntrasladoPaciente' type='button' id='btntrasladoPaciente' value='  Solicitar   Traslado    ' onClick='solicitarTraslado()' ></div></td></tr>");
			out.print("</table>");
			
		}
		
		if(va.equals("interconsulta")){
			String CodPacInter=req.getParameter("CodPac");
			String codAdmInter=req.getParameter("codAdm");
			String Usuario=req.getParameter("usuario");
		    
			try {
			rs=mvf.BuscarPaciente(CodPacInter);
			rs2= mvf.BuscarNombreUsuario(Usuario);
			if(rs.next()){
				String NombreComple=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
				String Gene=rs.getString(6);
				String Edad=rs.getString("edad");
			
			out.print("<table width='100%' border='1'>");	
			out.print("<tr>");
			out.print("<tr><td colspan='6'><div align='center' id='cabecera2' class='style11'><span>Interconsulta </span></div></td></tr>");
			if(rs2.next()){
			out.print("<td>De:</td><td colspan='2'><input name='txtNomUsuario' type='text' size='80' id='txtNomUsuario'  disabled='disabled' value='"+rs2.getString(1)+" "+rs2.getString(2)+"'/></td>");
			}rs2.getStatement().getConnection().close();
			out.print("<td>Especialidad</td><td colspan='2'><select id='cmbEspecialidad' ><option value='Seleccione'>Seleccione</option>");
			//rs1=mac.BuscarEspecialidadTodas();
			//while(rs1.next()){
			String Condicion="";
			if(Integer.parseInt(Edad)>=18){
				//no mostrar las pediatricas tanto en especialidades como en medicos.
				if(Gene.equals("Masculino")){
					//mostrar todas las especialidades y medicos menos las ginecologicas.
					Condicion="AND nombre_especialidad NOT LIKE '%PEDIATRI%' AND nombre_especialidad NOT LIKE '%GINECO%' ORDER BY nombre_especialidad ";
					rs1=mvf.BuscarEspecialidadTodas(Condicion);
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				}
					
				if(Gene.equals("Femenino")){
					//mostrar todas las especialidades y medicos menos la urologia.
					Condicion="AND nombre_especialidad NOT LIKE '%PEDIATRI%'  ORDER BY nombre_especialidad ";
					rs1=mvf.BuscarEspecialidadTodas(Condicion);
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				}
			}else{ 
				// son menores de 18 a�os mostrar todas las especialidades
					//no mostrar las pediatricas tanto en especialidades como en medicos.
					if(Gene.equals("Masculino")){
						//mostrar todas las especialidades y medicos menos las ginecologicas.
						Condicion=" AND nombre_especialidad NOT LIKE '%GINECO%' ORDER BY nombre_especialidad ";
						rs1=mvf.BuscarEspecialidadTodas(Condicion);
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					}
					if(Gene.equals("Femenino")){
						//mostrar todas las especialidades y medicos menos la urologia.
						Condicion="  ORDER BY nombre_especialidad";
						rs1=mvf.BuscarEspecialidadTodas2();
						while(rs1.next()){
							out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs1.getStatement().getConnection().close();
					}
			}
			
			//}
			//rs1.getStatement().getConnection().close();					
			out.print("</select></td>");
			
			
			out.print("<tr><td width='13%'><div><p><p>Detalle Interconsulta<p></div></td><td width='87%'><textarea name='txtDetInterconsulta' cols='50' rows='4' id='txtDetInterconsulta' ></textarea></td></tr>");
			out.print("<tr><td colspan='2'><div align='center'><input name='btnInterconsulta' type='button' id='btnInterconsulta' value='Ingresar' onclick='GuardarInterconsulta()' /></div></td></tr>");
			out.print("</table>");
			
		}
		
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		if(va.equals("VerModInter")){
			String CodPacInter=req.getParameter("CodPac");
			String codAdmInter=req.getParameter("codAdm");
			String Usuario=req.getParameter("usuario");
			String codEsp=req.getParameter("codEspecialidad");
		    
			try {
				rs=mvf.BuscarInterconsultas(codAdmInter, codEsp, "-1");
				rs2= mvf.BuscarNombreUsuario(Usuario);
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' id='cabecera2' class='style11' >Modificar interconsultas </div></td></tr>");	
				if(rs2.next()){
				out.print("<td>De:</td><td ><input name='txtNomUsuario' type='text' id='txtNomUsuario' size='80' disabled='disabled' value='"+rs2.getString(1)+" "+rs2.getString(2)+"'/></td>");
				}rs2.getStatement().getConnection().close();
				out.print("<td ><input name='txtEsp' type='text' size='80' id='txtEsp'  disabled='disabled' value='"+rs.getString(4)+"'/></td>");
				
				
				out.print("<tr><td width='13%'><div><p><p>Detalle Interconsulta<p></div></td><td width='87%'><textarea name='txtDetInterconsulta' cols='50' rows='4' id='txtDetInterconsulta' >"+rs.getString(2)+"</textarea></td></tr>");
				out.print("<tr><td colspan='2'><div align='center'><input name='btnInterconsulta' type='button' id='btnInterconsulta' value='Ingresar' onclick='ModificarInterconsulta("+rs.getString(1)+")' /></div></td></tr>");
				out.print("</table>");
				
			}
			
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("pendInter")){			
			String codAdmInter=req.getParameter("codAdm");
			String Usuario=req.getParameter("usuario");
			
			try {
				rs1=mlh.ObtenerDiagIngreso(codAdmInter);
				if(rs1.next()){
								
					rs=mvf.BuscarInterconsultasPendPac(codAdmInter);
					out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' id='cabecera2' class='style11' >INTERCONSULTAS PENDIENTES </div></td></tr>");
					out.print("<tr><td width='27%'><div align='center'>Fecha y Hora </div></td><td width='26%'><div align='center'>Medico que la Solicita</div></td><td width='23%'><div align='center'>Especialidad</div></td><td width='24%'><div align='center'>Realizar</div></td></tr>");
					while(rs.next()){
						
						
							out.print("<tr><td>"+rs.getString(6)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td>");
							
							
							     rs1 = mvf.BuscarEspecialidadUsuario (Usuario);
							     if(rs1.next()){
							     if(rs.getString(8).equals(rs1.getString(1)) ){
							    	 out.print("<td align='center'><a href='#' onclick='Formatos()'>realizar</a></td>");
							     }else{
							    	 out.print("<td></td>");
							    	 
							     }
								 }
							
							     out.print("</tr>");
						
					}rs.getStatement().getConnection().close();
					out.print("</table>");
					
					out.print("</div></td></tr></table>");
					//rs.getStatement().getConnection().close();

				}else{
					out.print("	ESTA ADMISION NO TIENE NINGUN DIAGNOSTICO DE INGRESO.\nPARA USAR ESTA OPCION DEBE DE DILIGENCIAR UNA HISTORIA CLINICA Y ASIGNAR UN DIAGNOSTICO DE INGRESO.");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		 if(va.equals("modificar_interconsulta")){
	    	   
			// String Usuario=req.getParameter("usuario");
				String motivo_inter=req.getParameter("motivo_inter");
				String codInter=req.getParameter("cod_inter");
				mvf.modificarInterconsulta(motivo_inter, codInter);
			}
		
		 
		  
		 
		 
		
		//#################
		
		
		if(va.equals("lab")){
			/**
			 * si el examen es de laboratorio
			 */
			try {
				rs=mvf.ObtenerOrdenLabPendiente(Codusuario, codAdm);
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:large; color:#FF0000 ' >Tiene Una Orden de Laboratorio Sin Guardar.Debe Guardarla Antes de Ordenar Otra.</div></td></tr></table>");
					out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center'>Detalle Orden<input name='txtCodOrdenLab' type='hidden' id='txtCodOrdenLab' value="+rs.getString(1)+" /></div></td></tr>");				
					out.print("<tr><td width='42%'><div align='center'>Nombre Examen</div></td><td width='46%'><div align='center'>Observaciones</div></td></tr>");
					rs3=msa.ObtenerDetalleOrden(rs.getString(1));
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td></tr>");
					}
					out.print("<tr><td colspan='3' ><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalizarLaboratorios()' /></div></td></tr></table>");
					rs3.getStatement().getConnection().close();
				}else{
					rs1=mlh.ObtenerAreaExamenes();
					out.print("<table width='100%'><tr><td><div align='center' id='cabecera2' class='style11' >Orden Medica Laboratorio</div></td></tr></table>");
					out.print("<table width='100%' class='style6' border='1'><tr tyle='font-size:9px'><td width='196'>DETALLE ORDEN </td><td width='236'><textarea name='txtDetOrdenLab' cols='30' rows='3' id='txtDetOrdenLab' onkeyup='ActuDetOrdenLab()' ></textarea></td><td width='218'>SELECCIONE GRUPO </td><td width='425'><select name='cmbgrupos' id='cmbgrupos' onchange='VerExamenes()'><option selected='selected'>SELECCIONE</option>");
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option> ");
					}
					out.print("</select></td></tr><tr tyle='font-size:9px'><td colspan='4'><div id='examenes'>");
					out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' id='cabecera2' class='style11' >Detalle Orden<input name='txtCodOrdenLab' type='hidden' id='txtCodOrdenLab' /></div></td></tr>");				
					out.print("<tr><td width='42%'><div align='center'>Nombre Examen</div></td><td width='46%'><div align='center'>Observaciones</div></td><td width='12%'><div align='center'>Accion</div></td></tr>");
					out.print("<tr><td></td><td></td><td></td></tr>");
					out.print("</table>");			
					out.print("</div></td></tr></table>");
					rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("NOACTIVO")){
			out.println("<p class=style67>USTED NO TIENE ESTA OPCION ACTIVADA PORQUE TIENE FORMATOS ABIERTOS</p>");
			out.println("<p  class=style66><u><i><a href=menu.jsp>CERRAR FORMATO</a></i></u></p>");
		}
		
		if(va.equals("Pro")){
			/*** si procedimiento***/
			rs=mvf.ObtenerOrdenImgPendiente(Codusuario, codAdm);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:large; color:#FF0000 ' >Tiene Una Orden de Procedimiento Sin Guardar.Debe Guardarla Antes de Ordenar Otra.</div></td></tr></table>");
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center'>Detalle Orden Procedimiento<input name='txtCodOrdenPro' type='hidden' id='txtCodOrdenPro' value="+rs.getString(1)+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Procedimiento</div></td><td width='21%'><div align='center'>Observacion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenImg(rs.getString(1));
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("<tr><td colspan='3'><div align='center' ><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalizarOrdenPro()' /></div></td></tr></table>");
				}
				else{
					out.print("<table width='100%' class='style6' border='0'><tr><td colspan='4'><div align='center' id='cabecera2' class='style11' >Orden de Procedimiento</div></td></tr>");
					out.print("<tr><td width='21%'>DETALLE ORDEN </td><td width='32%'><textarea name='txtDetOrden' cols='30' rows='4' id='txtDetOrden'></textarea></td><td width='18%'>DATOS CLINICOS</td><td width='29%'><textarea name='txtdatosClinicos' cols='30' rows='4' id='txtdatosClinicos'></textarea></td></tr></table>");
					out.print("<table width='100%' class='style6' border='0' >");
					out.print("<tr><td>NOMBRE DEL PROCEDIMIENTO  </td><td colspan='2'><input name='txtCirujia' type='text' id='txtCirujia' size='85' onkeyup='autocompletarQx()' /><input name='txtCodigoQx' type='hidden' id='txtCodigoQx' /></td><td><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' value='Asignar' onclick='enviarProcedimiento()' />  <input name='btnFinalOrdenImg' class='boton4' type='button' id='btnFinalOrdenImg' value='Finalizar' onclick='FinalizarOrdenPro()' /></td></tr>");
					out.print("<tr><td><label></td><td colspan='3'><div id='SugerenciaCx'></div></td></tr>");
					out.print("<tr><td>&nbsp;</td><td colspan='3'>&nbsp;</td></tr>");
					out.print("<tr><td colspan='4'><div id='DetOrdImg'>");
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11' >Detalle Orden Procedimiento<input name='txtCodOrdenPro' type='hidden' id='txtCodOrdenPro' /></div></td></tr>");
					out.print("<tr><td width='68%'><div align='center'>Nombre Procedimiento</div></td><td width='32%'><div align='center'>Observacion</div></td><td>Accion</td></tr>");
					out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td></td></tr>");
					out.print("</table></div></td></tr></table>");
					}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		if(va.equals("img")){
			/*** si el examen es de imagenologia***/
			rs=mvf.ObtenerOrdenImgPendiente(Codusuario, codAdm);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:large; color:#FF0000 ' >Tiene Una Orden de Imagenologia Sin Guardar.Debe Guardarla Antes de Ordenar Otra.</div></td></tr></table>");
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center'>Detalle Orden Imagenologia<input name='txtCodOrdenImg' type='hidden' id='txtCodOrdenImg' value="+rs.getString(1)+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenImg(rs.getString(1));
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("<tr><td colspan='3'><div align='center' ><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalizarOrdenImg()' /></div></td></tr></table>");
				}
				else{
					out.print("<table width='100%' class='style6' border='1'><tr><td colspan='4'><div align='center' id='cabecera2' class='style11' >Orden de Imagenologia</div></td></tr>");
					out.print("<tr><td width='21%'>DETALLE ORDEN </td><td width='32%'><textarea name='txtDetOrden' cols='30' rows='4' id='txtDetOrden'></textarea></td><td width='18%'>DATOS CLINICOS</td><td width='29%'><textarea name='txtdatosClinicos' cols='30' rows='4' id='txtdatosClinicos'></textarea></td></tr></table>");
					out.print("<table width='100%' class='style6' border='1' >");
					out.print("<tr><td>NOMBRE DEL ESTUDIO  </td><td colspan='2'><input name='txtnomexam' type='text' id='txtnomexam' size='85' onkeyup='autocompleta_nombre()' /><input name='txtcodexamen' type='hidden' id='txtcodexamen' /></td><td><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' value='Asignar' onclick='enviar()' />  <input name='btnFinalOrdenImg' class='boton4' type='button' id='btnFinalOrdenImg' value='Finalizar' onclick='FinalizarOrdenImg()' /></td></tr>");
					out.print("<tr><td><label><input name='chkportatil' type='checkbox' id='chkportatil' value='(PORTATIL)' />Portatil</label></td><td colspan='3'><div id='sugerencias1'></div></td></tr>");
					out.print("<tr><td>&nbsp;</td><td colspan='3'>&nbsp;</td></tr>");
					out.print("<tr><td colspan='4'><div id='DetOrdImg'>");
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11' >Detalle Orden Imagenologia<input name='txtCodOrdenImg' type='hidden' id='txtCodOrdenImg' /></div></td></tr>");
					out.print("<tr><td width='68%'><div align='center'>Nombre Estudio</div></td><td width='32%'><div align='center'>Observacion</div></td><td>Accion</td></tr>");
					out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td></td></tr>");
					out.print("</table></div></td></tr></table>");
					}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		if(va.equals("pat")){
			/*** orden patologia***/
			
					out.print("<table width='100%' class='style6' border='0'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11' >Orden de Patologia</div></td></tr>");
					out.print("<tr><td><div style='text-align:center;'><table width='80%' border='0'><tr><td width='10%'><label><B>1. BIOPSIA QUIRURGICO DE RUTINA</B></label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkBiopsiaRut' value='chkBiopsiaRut'  /></td>" +
							"<td width='10%'><label><B>3. INMUNOHISTOQUIMICA</B></label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkHistoqui' value='chkHistoqui' /></td>" +
							"</td></tr>"+
							"<tr><td colspan='2'><label><B>2. BIOPSIA RENAL CON:</B></label></td>"+
							"<td width='30%'><label><B>4. CITOLOGIA DE LIQUIDOS CORPORALES</B></label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkCitoLiq' value='chkCitoLiq' /></td></tr>"+
							"<tr><td width='30%'><label>MICROSCOPIA DE LUZ</label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkMicroLuz' value='chkMicroLuz' /></td>"+
							"<td width='30%'><label><B>5. CITOLOGIA CERVICO VAGINAL</B></label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkCitoCerv' value='chkCitoCerv' /></td></tr>"+
							"<tr><td width='30%'><label>INMUNOFLUORESCENCIA</label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkInmuFluor' value='chkInmuFluor' /></td>"+	
							"<td width='30%'><label><B>6. OTROS</B></label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkOtros' value='chkOtros' /></td></tr>"+
							"<tr><td width='30%'><label>MICROSCOPIAELECTRONICA</label></td>" +
							"<td width='10%'><input name='chkPat' type='checkbox' id='chkElectronica' value='chkElectronica' /></td></tr></table></div></td></tr>"+	
							"<tr><td colspan='5'><div align='center' id='cabecera2' class='style11' >MATERIAL REMITIDO</div></td></tr>" +
							"<tr><td colspan='5' align='center'><textarea name='txtMatRem' cols='100' rows='8' id='txtMatRem'></textarea></td></tr>"+
							"<tr><td colspan='5'><div align='center' id='cabecera2' class='style11' >HALLAZGOS QUIRURGICOS</div></td></tr>" +
							"<tr><td colspan='5' align='center'><textarea name='txtHallQuir' cols='100' rows='8' id='txtHallQuir'></textarea></td></tr>"+
							"<tr><td colspan='5'><div align='center' id='cabecera2' class='style11' >DIAGNOSTICO PRESUNTIVO</div></td></tr>" +
							"<tr><td colspan='5' align='center'><textarea name='txtDiagPres' cols='100' rows='8' id='txtDiagPres'></textarea></td></tr>"+
							"<tr><td colspan='5'><div align='center' id='cabecera2' class='style11' >OBSERVACIONES</div></td></tr>" +
							"<tr><td colspan='5' align='center'><textarea name='txtObs' cols='100' rows='8' id='txtObs'></textarea></td></tr>"+
							"<tr><td colspan='5' align='center'><input name='btnIngresar' class='boton4' type='button' id='btnIngresar' value='Ingresar' onclick='enviarOrdPat()' /></td></tr>"
					);
					/*out.print("<tr><td width='21%'>DETALLE ORDEN </td>" +
							
							"<td width='32%'><textarea name='txtDetOrden' cols='30' rows='4' id='txtDetOrden'></textarea></td>" +
							"<td width='18%'>DATOS CLINICOS</td>" +
							"<td width='29%'><textarea name='txtdatosClinicos' cols='30' rows='4' id='txtdatosClinicos'></textarea></td></tr></table>");
					out.print("<table width='100%' class='style6' border='1' >");
					out.print("<tr><td>NOMBRE DEL ESTUDIO  </td><td colspan='2'><input name='txtnomexam' type='text' id='txtnomexam' size='85' onkeyup='autocompleta_nombre()' /><input name='txtcodexamen' type='hidden' id='txtcodexamen' /></td><td><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' value='Asignar' onclick='enviar()' />  <input name='btnFinalOrdenImg' class='boton4' type='button' id='btnFinalOrdenImg' value='Finalizar' onclick='FinalizarOrdenImg()' /></td></tr>");
					out.print("<tr><td><label><input name='chkportatil' type='checkbox' id='chkportatil' value='(PORTATIL)' />Portatil</label></td><td colspan='3'><div id='sugerencias1'></div></td></tr>");
					*/out.print("</table>");
					
			
		}
		
		
		if(va.equals("ilab")){
			//int contador=0;
			rs=mlh.ObtenerExamenesArea(CodArea);
			out.print("<table border='1' class='style6' width='100%'><tr tyle='font-size:9px' id='cabecera2' align='center' class='style11'><td colspan='2'>NOMBRE DEL EXAMEN </td><td align='center'>SELECCIONE<label></label></td></tr>");
			try {
				while(rs.next()){
					out.print("<tr style='font-size:9px'><td colspan='2'>"+rs.getString(2)+"</td><td width='154' align='center'><label><input type='checkbox' name='ca' id='ca' value='"+rs.getString(1)+"' /><input type='hidden' name='txtTipo' id='txtTipo' value='"+rs.getString(3)+"' />  </label></td></tr>");
					contador=contador+1;
				}
				out.print("<tr><td colspan='2' align='right'></td><td align='center'><label><input name='btnAsignar'  type='button' id='btnAsignar' value='Asignar' class='boton4' onclick='GuardarLaboratorios()'></label></td></tr></table><input name='yo' id='yo' type='hidden' value="+contador+">");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("6")){
			out.print("<table width='50%' border='0' class='style6' cellspacing='0' ><tr><td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='7' onclick='RadioFor()' />Medicamentos Activos</label></td>");
			out.print("<td width='50%'><label><input name='radiobutton' type='radio' value='radiobutton' id='8' onclick='RadioFor()' />Orden Medica Formulada</label></td></tr></table>");
			out.print("<table width='100%' border='1'><tr><td><div id='ContenidoFormulacion'></div></td></tr></table>");
			
		}
		if(va.equals("7")){
			out.print("<table width='100%' border='1'>");			
			out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Orden Medica General </span></div></td></tr>");
			out.print("<tr><td width='13%'><div><p><p>Detalle Orden<p></div></td><td width='87%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' ></textarea></td></tr>");
			out.print("<tr><td colspan='2'><div align='center'><input name='btnOrden' type='button' id='btnOrden' value='Ingresar' onclick='OrdenMedicaGeneral()' /></div></td></tr>");
			out.print("</table>");
			
		}
		if(va.equals("71")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			
			String TipoOrden="4";
			msa.CrearOrdenEstudios(codPac, codAdm, Codusuario, DetOrden, Hora, Fecha, TipoOrden);
			rs=msa.ObtenerCodigoOrden(Hora, Fecha);
			try {
				if(rs.next()){
					out.print(rs.getString(1));
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		if(va.equals("VerEnfer")){
			String FechaKardex=req.getParameter("FechaKardex");
			String CodAdmK1=req.getParameter("CodAdmK");
			
			out.print("<table width='100%' border='0'>"); 

			out.print(" <tr><td colspan='4' align='center' bgcolor=''><h1><font color='black'>ENFERMERIA</font></h1></td></tr>");
			  
			out.print(" <tr><td width='33%' id='Krd' onclick='MostrarKardexK("+CodAdmK1+",&quot;"+FechaKardex+"&quot;)' align='center' bgcolor='#2E64FE'><h2><B><font color='white'>KARDEX</font></B></h2></td>");
			
			out.print(" <td width='33%' id='Ihp' onclick='MostrarInsumosHospitalarios2()' align='center' bgcolor='#2E64FE'><font color='white'><h2><B><font color='white'>INSUMOS HOSPITALARIOS</font></B><h2></font></td>");
			
			out.print(" <td width='33%' id='Svt' onclick='SignosVitales("+CodAdmK1+",&quot;"+FechaKardex+"&quot;)' align='center' bgcolor='#2E64FE'><font color='white'><h2><B><font color='white'>SIGNOS VITALES</font></B><h2></font></td></tr>");

			 out.print("<tr><td colspan='4' id='HistoriaPaciente2' >");
				rs=mlh.ResumenOrdenServicio(CodAdmK1);
				out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11' >RESUMEN ORDENES MEDICAS </div></td></tr>");
				out.print("<tr><td width='27%'><div align='center'>Fecha y Hora </div></td><td width='26%'><div align='center'>Usuario</div></td><td width='23%'><div align='center'>Profesion</div></td><td width='24%'><div align='center'>Tipo Orden</div></td></tr>");
				try {
					while(rs.next()){
						String Tipo=rs.getString(5);
						if(Tipo.equals("1")){
							//LABORATORIO
							out.print("<tr><td><a href='#' onclick='ReportLabora("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>LABORATORIO</td></tr>");
						}
						
						if(Tipo.equals("2")){
							//2=IMAGENOLOGIA
							out.print("<tr><td><a href='#' onclick='ReportImage("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>IMAGENOLOGIA</td></tr>");
						}
						
						if(Tipo.equals("3")){
							//3=MEDICAMENTOS
							out.print("<tr><td><a href='#' onclick='ReportMedica("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>MEDICAMENTOS e INSUMOS</td></tr>");
						}
						
						if(Tipo.equals("4")){
							//4=GENERAL
							out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN GENERAL</td></tr>");
						}
						if(Tipo.equals("5")){
							//5=PROCEDIMIENTOS
							out.print("<tr><td><a href='#' onclick='ReportesOrdenProcedimiento("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN DE PROCEDIMIENTOS</td></tr>");
						}						
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("</table>");
			 out.print("</td></tr></table>");
			
		}
		
		if(va.equals("verFormulacion")){
			String CodAdmision= req.getParameter("codAdm");
			System.out.println("CodAdmision: "+CodAdmision);
			rs=mlh.ResumenOrdenServicio(CodAdmision);
			out.print("<table width='100%' border='1'><tr><td colspan='8'><div align='center' id='cabecera2' class='style11' >RESUMEN ORDENES MEDICAS </div></td></tr>");
			out.print("<tr><td width='27%'><div align='center'>Fecha y Hora </div></td><td width='26%'><div align='center'>Usuario</div></td><td width='23%'><div align='center'>Profesion</div></td><td width='24%'><div align='center'>Tipo Orden</div></td></tr>");
			try {
				while(rs.next()){
					String Tipo=rs.getString(5);
					if(Tipo.equals("1")){
						//LABORATORIO
						out.print("<tr><td><a href='#' onclick='ReportLabora("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>LABORATORIO</td></tr>");
					}
					
					if(Tipo.equals("2")){
						//2=IMAGENOLOGIA
						out.print("<tr><td><a href='#' onclick='ReportImage("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>IMAGENOLOGIA</td></tr>");
					}
					
					if(Tipo.equals("3")){
						//3=MEDICAMENTOS
						out.print("<tr><td><a href='#' onclick='ReportMedica("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>MEDICAMENTOS e INSUMOS</td></tr>");
					}
					
					if(Tipo.equals("4")){
						//4=GENERAL
						out.print("<tr><td><a href='#' onclick='ReporteOrdenGeneral("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN GENERAL</td></tr>");
						
					}
					if(Tipo.equals("5")){
						//5=PROCEDIMIENTOS
						System.out.println("");
						out.print("<tr><td><a href='#' onclick='ReportesOrdenProcedimiento("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN DE PROCEDIMIENTOS</td></tr>");
					}
					if(Tipo.equals("6")){
						//6=ORDEN DE PATOLOGIA
						System.out.println("entro a ver orden pat");
						out.print("<tr><td><a href='#' onclick='mostrarOrdenPat("+rs.getString(1)+")' >"+rs.getString(2)+"</a></td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>ORDEN DE PATOLOGIA</td></tr>");
					}
					
				}
				rs.getStatement().getConnection().close();
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		
		
		/*************************************************************************************************************************/
		String NombreMedicamento=req.getParameter("NombreMedicamento");
		//String FormaFarmaceutica=req.getParameter("FormaFarmaceutica");
		String CodFormaFarmaceutica=req.getParameter("CodFormaFarmaceutica");
		String Concentracion=req.getParameter("Concentracion");
		String UnidadMed=req.getParameter("Unidad");
		String ViaAdm=req.getParameter("ViaAdm");
		String LapsoMed=req.getParameter("LapsoMed");
		String CantidadMedicamento=req.getParameter("CantidadMedicamento");
		String CodPacK=req.getParameter("CodPacK");
		String CodAdmK=req.getParameter("CodAdmK");
		String CodUsuK=req.getParameter("CodUsuK");
		String CodCamaK=req.getParameter("CodCamaK");
		String CodAreaK=req.getParameter("CodAreaK");
		String CodSubareaK=req.getParameter("CodSubareaK");
		String FrecuenciaK=req.getParameter("FrecuenciaK");
		String DosisK=req.getParameter("DosisK");
		String NomUnidad=req.getParameter("NomUnidad");
		String PesoK=req.getParameter("PesoK");
		String TallaK=req.getParameter("TallaK");
		String ObserInfu=req.getParameter("ObserInfu");
		if(va.equals("APT")){
			mvf.ActualizarPesoTalla(CodAdmK, PesoK, TallaK);
		}
		if(va.equals("CPT")){
			mvf.CrearPesoTalla(CodAdmK, PesoK, TallaK);
		}
		if(va.equals("GMK")){
			String Dosificacion="Dar "+CantidadMedicamento+" Cada "+LapsoMed+". "+ViaAdm;
			String ObservacionMedKard=req.getParameter("ObservacionMedKard");
			/*
			 * 1.)Consultar el codigo del medicamento. OK
			 * 2.)Ingresar en la tabla hic_formulacion. OK
			 * 3.)Ingresar en la tabla detalle_formulacion OK 
			 * 4.)Consultar si el medicamento pertenece a una orden de produccion OK
			 * 4.)Si pertence a la orden de produccion insertarlo en la tabla de farc_orden_produccion OK
			 * 5.)Consultar Si hay Orden de Produccion Activa Para el dia, 
			 *    si no la hay, crearla validando la hora de cierre de la orden. OK
			 * 6.)se consulta la orden de produccion activa y se toma el codigo junto con el cod_medica y 
			 * 	  se inserta en la tabla hic_detalle_orden_produccion OK
			 * 7.) Mostrar kardes actualizado   
			 */
			
			//1
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
			String CodDetalleFormulacionK="";
			try {
				//el medicamento no pertenece a orden de produccion
				String CodMedK="";
				String CodFormulacionK="";
				//String CodDetalleFormulacionK="";
				rs1=msa.ObtenerMedicamento(NombreMedicamento, CodFormaFarmaceutica, Concentracion, UnidadMed);
				if(rs1.next()){
					CodMedK=rs1.getString(1);
					//crear en hic_formulacion
					//se verifica si hay una orden de formulacion de kardex activa en la db estado=4
					rs2=mvf.ObtenerCodigoFormulacionKardesActiva(CodAdmK,fecha+"");
					if(rs2.next()){
						//si existe una orden de formulacion de kardex
						CodFormulacionK=rs2.getString(1);
						String CanLetra=Convertir(CantidadMedicamento);
						mvf.CrearDetalleFormulacionK(rs2.getString(1), rs1.getString(1), CantidadMedicamento, Dosificacion, ObservacionMedKard, "1", CanLetra, "1 Dia(s)", DosisK, NomUnidad,fecha+"",hora+"",ObserInfu,CodUsuK,ObservacionMedKard);
				
					}else{
						//no existe orden de kardex se crea
						mvf.CrearFormulacion(CodPacK, CodAdmK, "", "2", CodUsuK, CodCamaK, CodAreaK, CodSubareaK, hora+"", fecha+"");
						rs4=mvf.ObtenerCodigoFormulacionKardesActiva(CodAdmK,fecha+"");
						if(rs4.next()){
							//crear detalle formulacion
							CodFormulacionK=rs4.getString(1);
							String CanLetra=Convertir(CantidadMedicamento);
							mvf.CrearDetalleFormulacionK(rs4.getString(1), rs1.getString(1), CantidadMedicamento, Dosificacion, ObservacionMedKard, "1", CanLetra, "1 Dia(s)",DosisK, NomUnidad,fecha+"",hora+"",ObserInfu,CodUsuK,ObservacionMedKard);
							
						}
						rs4.getStatement().getConnection().close();

					}
					rs2.getStatement().getConnection().close();					
					rs2=mvf.ObtenerCodigoDetalleFormulacionK(CodFormulacionK, CodMedK,fecha+"",hora+"");
					if(rs2.next()){
						CodDetalleFormulacionK=rs2.getString(1);
						mvf.CrearDispenacion(rs2.getString(1));
						String FrecK="";
						Date FechaMedicamento=rs2.getDate(2);
						String HoraMedicamento=rs2.getString(3);
						String sep=rs2.getString(4).replace(" ", "_");	
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1){FrecK=d[3];}
						
						String HorFor="";
						int r=HoraMedicamento.split(":").length;
						String [] e=HoraMedicamento.split(":");
						for(int y=0; y<r-1;y++){HorFor=e[0];}
						
						//out.print("FechaMedicamento="+FechaMedicamento+" HoraMedicamento="+HoraMedicamento+" FrecK="+FrecK+" HorFor="+HorFor);
						//verificar la hora proxima de la dosis comparando la hora y fecha que se le asigno el medicamento
						//con la fecha y hora que trae la dosificacion estandar de cada frecuencia
						//insertarlo en la tabla hic_horas_dosificacion
						rs3=mvf.ObtenerHorasDispensacion(FrecK);
						int HoraI=Integer.parseInt(HorFor);
						//Date FechaMa=null;
						while(rs3.next()){
							HoraI=HoraI+Integer.parseInt(FrecK);
							if(HoraI==24){
								HoraI=0;
							}
							if(HoraI>24){
								HoraI=HoraI-24;
								//FechaMedicamento= sumar la fecha mas uno cuando pase de 24 horas
							//	long tiempoActual = FechaMedicamento.getTime();
								//long unDia = 1 * 24 * 60 * 60 * 1000;
								//FechaMa = new Date(tiempoActual + unDia);
								//*
							}
							//System.out.println("HoraI="+HoraI+":00:00 Frecuencia:"+FrecK+" HoraMedicamento="+HoraMedicamento+"FechaMa="+FechaMa);
							mvf.IngresarHoraDosificacion(rs2.getString(1), FechaMedicamento+"", HoraI+":00:00", CodUsuK);
							//HoraI=0;
						}
						rs3.getStatement().getConnection().close();
						//********
					}
					rs2.getStatement().getConnection().close();
					//se consulta el codigo de la formulacion
					//rs2=mvf.ObtenerCodigoFormulacionKardes(hora, fecha, CodAdmK);
				}
				rs1.getStatement().getConnection().close();
				/******************************************************************************/
				rs=msa.ObtenerMedicamentoOrdenProduccion(CodAdmK, CodMedK,fecha+"");
				if(rs.next()){
					//el medicamento pertenece a orden de produccion
					rs1=mvf.ObtenerOrdenProduccion(fecha+"");
					if(rs1.next()){
						//tiene creado orden de produccion para la fecha
						//se crea detalle de la orden de produccion
						//mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), CodFormulacionK, rs.getString(2), CodPacK, rs.getString(3), rs.getString(4), DosisK, FrecuenciaK, rs.getString(5));
						String horString = "";
						rs3=mvf.ObtenerCorteProduccion();
						if(rs3.next()){
							horString=rs3.getString(2);
						}
						rs3.getStatement().getConnection().close();
						String hora1=hora+"";
						String hora2=horString;
						String ho1 = null;
						String ho2 = null;
						String mo1 = null;
						String mo2 = null;
						int f1=hora1.split(":").length;
						String[] j1=hora1.split(":");		     	
						for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
						int f2=hora2.split(":").length;
						String[] j2=hora2.split(":");		     	
						for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
						if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
							//la hora actual es mayor a la del corte de la orden de produccion						
						}else{
							//la hora actual es menor a la del corte de la orden de produccion
							if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
								//los minutos actuales son mayores a los del corte de la orden de produccion
							}else{
								//los minutos actuales son menores a los del corte de la orden de produccion.
								mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), CodFormulacionK, rs.getString(2), CodPacK, rs.getString(3), rs.getString(4), DosisK, FrecuenciaK, rs.getString(5));
								//mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), CodFormulacionK, rs.getString(2), CodPacK, rs.getString(3), rs.getString(4), DosisK, FrecuenciaK, rs.getString(5));
							}
						}

					}else{
						//no tiene orden de produccion creada
						mvf.CrearOrdenProduccion(fecha+"", hora+"");
						rs2=mvf.ObtenerOrdenProduccion(fecha+"");
						if(rs2.next()){
							// se verifica la hora de corte de la orden de produccion
							/*********************************************************************/
							String horString = "";
							rs3=mvf.ObtenerCorteProduccion();
							if(rs3.next()){
								horString=rs3.getString(2);
							}
							rs3.getStatement().getConnection().close();
							
							
							String hora1=hora+"";
							String hora2=horString;
							String ho1 = null;
							String ho2 = null;
							String mo1 = null;
							String mo2 = null;
							int f1=hora1.split(":").length;
							String[] j1=hora1.split(":");		     	
							for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
							int f2=hora2.split(":").length;
							String[] j2=hora2.split(":");		     	
							for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
							if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
								//la hora actual es mayor a la del corte de la orden de produccion							
							}else{
								//la hora actual es menor a la del corte de la orden de produccion
								if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
									//los minutos actuales son mayores a los del corte de la orden de produccion
								}else{
									//los minutos actuales son menores a los del corte de la orden de produccion.
									rs3=msa.ObtenerMedicamentoOrdenProduccion(CodAdmK, CodMedK,fecha+"");
									if(rs3.next()){
										rs4=mvf.ObtenerOrdenProduccion(fecha+"");
										if(rs4.next()){
											mvf.CrearDetalleOrdenProduccion(rs4.getString(1), rs3.getString(1), CodFormulacionK, rs3.getString(2), CodPacK, rs3.getString(3), rs3.getString(4), DosisK, FrecuenciaK, rs3.getString(5));
										}
										rs4.getStatement().getConnection().close();
									}
								}
							}
							/********************************************************************/
						}
						rs2.getStatement().getConnection().close();
					}
					rs1.getStatement().getConnection().close();
					
				}			
				rs.getStatement().getConnection().close();
				/*******************************************************************************/
				java.util.Calendar hoy = java.util.Calendar.getInstance();
				java.util.Date primerDia= hoy.getTime();
				int diasMes[]= new int[12];
				diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
				diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;

				int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
				String NombreMes = "";
				anio=hoy.get(java.util.Calendar.YEAR);
				dia=hoy.get(java.util.Calendar.DATE);
				mes=hoy.get(java.util.Calendar.MONTH);
				mes=mes+1;
				if(mes==1){NombreMes="Enero";}
				if(mes==2){NombreMes="Febrero";}
				if(mes==3){NombreMes="Marzo";}
				if(mes==4){NombreMes="Abril";}
				if(mes==5){NombreMes="Mayo";}
				if(mes==6){NombreMes="Junio";}
				if(mes==7){NombreMes="Julio";}
				if(mes==8){NombreMes="Agosto";}
				if(mes==9){NombreMes="Septiembre";}
				if(mes==10){NombreMes="Octubre";}
				if(mes==11){NombreMes="Noviembre";}
				if(mes==12){NombreMes="Diciembre";}

				if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) /*Compruebo que el a�o es bisiesto*/
				{
					diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
				}
				nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
				primerDia.setDate(1);
				diaInicial=primerDia.getDay();
				
				out.print("<table width='100%' border>");
				out.print("<tr><th colspan=41 align=center>"+NombreMes+" Del "+anio+" "); 		
				/*************************************************/
				out.print("<tr bgcolor='#CCCCCC' >");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");
				/*out.print("<th align=center>M");
				out.print("<th align=center>M");
				out.print("<th align=center>J");
				out.print("<th align=center>V");
				out.print("<th align=center>S");*/
				out.print("</tr>");
				out.print("<tr></tr>");
				columna=0;
				for(i=0;i<diaInicial;i++) /*Bucle para la reserva de espacios de los d�as en el calendario*/
				{
					out.print("<td align=center><font size+=4>.");
					columna++;
					out.print("</font>");
				}
				int a=0;
				for(i=1;i<=nDias;i++) /*Bucle para la inclusi�n del n�mero de d�a en el calendario*/
				{
					a=a+1;
					out.print("<td align=center>");				
					if(i==dia) //Si el d�a a escribir es el d�a de hoy
					{
						String valueF=anio+"-"+mes+"-"+i;
						out.print("<b>"+i+"</b>");
						out.print("<font color=\"#ff0000\" size+=7><b>");
						//out.print("<input name='radiobutton' type='radio' checked='true' onclick='CarguesDiaKardex()' value="+anio+"-"+mes+"-"+i+" />");
						out.print("<input name='radiobutto' type='radio' value='radiobutto' checked='true' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
						out.print("</b></font>"); //Cerramos la etiqueta de edici�n de las propiedades de las fuentes
					}else{
						String valueF=anio+"-"+mes+"-"+i;
						out.print(i);
						//out.print("<input name='chkHorarioMedico' type='radio'  onclick='CarguesDiaKardex()' value="+anio+"-"+mes+"-"+i+" />");
						out.print("<input name='radiobutto' type='radio' value='radiobutto' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
					}				}

			//*	
			out.print("</table>");
			out.print("<table width='100%' ><tr><td width='30%' ></td><td width='70%' ><table width='100%' border='1' style='font-size:smaller'><tr><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td>");
			out.print("<td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td>");
			out.print("<td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td>");
			out.print("<td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td></tr></table></td></tr></table>");
			out.print("<table width='100%' border='1'>" +
					"<tr>" +
					"<td width='100%'><div id='CarguesDiaKardex'></div></td>" +
					"</tr>" +
					"</table>");
			
			
			
			String cp="";
			rs4=mvf.ObtenerCodPerfilft(CodAdmK);
			if(rs4.next()){
				//hay perfil creado
				cp=rs4.getString(1);
				//VIA=VIA INTRAVENOSA ETC.
				//FRECUENCIA=1 CADA 24 HORAS, ETC
				//CANTIDAD=ES LA CANTIDAD DE LA DOSIFICACION NO LA TOTAL.EJ:
				//DAR 2 CADA 8 HORAS.VIA ORAL, LA CANTIDAD=2
				String sep="";String sep1="";String sep2="";String sep3="";
				String sep4="";	String sep5="";String sep6="";String sep7="";
				sep=Dosificacion.replace(" ", "_");	
				System.out.print("Dosificacion="+sep);
				int h=sep.split("_").length;
				String[] d=sep.split("_");		     	
				for(int g=0; g<h-1; g=g+1)
				{ 		
					
					sep1=d[0];sep2=d[1];sep3=d[2];sep4=d[3];
					sep5=d[4];sep6=d[5];sep7=d[6];
					
					System.out.print(sep1+"//"+sep2+"//"+sep3+"//"+sep4+"//"+sep5+"//"+sep6);
				}
				String via=sep7;
				String frecuencia=sep3+" "+sep4+" "+sep5;		
				
				mvf.CrearDetallePerfilft(cp,CodMedK,CodUsuK,via,frecuencia,sep2,fecha+"",hora+"",CodDetalleFormulacionK);
			}else{
				//no hay perfil creado
				String fi="";String codArea1="";String codSubarea1="";String codCama1="";
				String cdx="";String sexo="";String edad="";String Serv="";	String aler="";
				String peso="";
				rs1=mvf.ObtenerDatosPerfilFarmaco(codAdm);
				if(rs1.next()){
					fi=rs1.getString(1);
					codArea1=rs1.getString(4);
					codSubarea1=rs1.getString(5);;
					codCama1=rs1.getString(6);
					cdx=rs1.getString(7);
					sexo=rs1.getString(2);
					if(sexo.equals("Masculino")){sexo="M";}
					if(sexo.equals("Femenino")){sexo="F";}
					edad=rs1.getString(3);
					Serv=rs1.getString(8);		
					mvf.CrearPerfilft(CodAdmK,CodPacK,fi,null,codArea1,codSubarea1,codCama1,cdx,aler,sexo,edad,PesoK,TallaK);
					rs2=mvf.ObtenerCodPerfilft(CodAdmK);
					if(rs2.next()){
						//hay perfil creado
						cp=rs2.getString(1);
						//VIA=VIA INTRAVENOSA ETC.
						//FRECUENCIA=1 CADA 24 HORAS, ETC
						//CANTIDAD=ES LA CANTIDAD DE LA DOSIFICACION NO LA TOTAL.EJ:
						//DAR 2 CADA 8 HORAS.VIA ORAL, LA CANTIDAD=2
						String sep="";String sep1="";String sep2="";String sep3="";
						String sep4="";	String sep5="";String sep6="";String sep7="";
						sep=Dosificacion.replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1)
						{ 		
							sep1=d[0];sep2=d[1];sep3=d[2];sep4=d[3];
							sep5=d[4];sep6=d[5];sep7=d[6];
						}
						String via=sep7;
						String frecuencia=sep3+" "+sep4+" "+sep5;		
						
						mvf.CrearDetallePerfilft(cp,CodMedK,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",CodDetalleFormulacionK);
					}
					rs2.getStatement().getConnection().close();
					
				}
				rs1.getStatement().getConnection().close();
				
			}
			rs4.getStatement().getConnection().close(); 
			
			/*rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(fecha+"", codAdm);
			String CodFormK="";
			if(rs2.next()){
				CodFormK=rs2.getString(1);
				rs=mvf.DetalledeFormulacionK(CodFormK);
				out.print("<table width='100%' border='1'><tr><td width='100%'><div id='CarguesDiaKardex'><table width='100%' border='1'>");
				while(rs.next()){
					out.print("<tr><td width='30%'><table width='100%' border='1' style='font-size:smaller'>");
					
					out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
					
					out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
					
					if(rs.getString(7).equals("4")){
						out.print("<td bgcolor='#3676BD' ><b><font color='white'>ACCIhhhhhhONES</font></b></td>");
					}
					
					if(rs.getString(7).equals("5")){
						out.print("<td bgcolor='#009933' ><b><font color='white'>ACCIhhhhONES</font></b></td>");
					}
					
					if(rs.getString(7).equals("6")){
						out.print("<td bgcolor='#FF9900' ><b><font color='white'>ACCIhhhhONES</font></b></td>");
					}
					if(rs.getString(7).equals("7")){
						out.print("<td bgcolor='#CC3333' ><b><font color='white'>ACCIONhhhhhES</font></b></td>");
					}

					
					rs3=mvf.ObtenerHorasDispensacionMedicamento(CodDetalleFormulacionK);
					while(rs3.next()){
						 int horas_d=rs3.getInt(11);
						 out.print("horas_d INGRESO="+horas_d+"");
					}
					rs3.getStatement().getConnection().close();
					
					
					out.print("	</tr></table></td><td width='70%'><div id='DosisKardex'>");
					/**********************/
					
					/*out.print("<table width='100%' border='1' style='font-size:smaller'><tr><td width='4%' bgcolor='#999999'>01</td><td width='4%' bgcolor='#999999'>02</td><td width='4%' bgcolor='#999999'>03</td><td width='4%' bgcolor='#999999'>04</td><td width='4%' bgcolor='#999999'>05</td><td width='4%' bgcolor='#FFFF66'>06</td><td width='4%' bgcolor='#FFFF66'>07</td><td width='4%' bgcolor='#FFFF66'>08</td>");
					out.print("<td width='4%' bgcolor='#FFFF66'>09</td><td width='4%' bgcolor='#FFFF66'>10</td><td width='4%' bgcolor='#FFFF66'>11</td><td width='4%' bgcolor='#FFFF66'>12</td><td width='4%' bgcolor='#FFFF66'>13</td><td width='4%' bgcolor='#FFFF66'>14</td><td width='4%' bgcolor='#FFFF66'>15</td>");
					out.print("<td width='4%' bgcolor='#FFFF66'>16</td><td width='4%' bgcolor='#FFFF66'>17</td><td width='4%' bgcolor='#FFFF66'>18</td><td width='4%' bgcolor='#999999'>19</td><td width='4%' bgcolor='#999999'>20</td><td width='4%' bgcolor='#999999'>21</td><td width='4%' bgcolor='#999999'>22</td>");
					out.print("<td width='4%' bgcolor='#999999'>23</td><td width='4%' bgcolor='#999999'>24</td></tr>");
					out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
					out.print("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>");
					out.print("");*/
					//*
					/**********************/
				/*	out.print("</div></td></tr>");
					///out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs.getString(5)+")' >Omitir</a></td></tr>");
				}
				out.print("</table></div></td></tr><table>");
				rs.getStatement().getConnection().close();
			}
			else{

				out.print("<table width='100%' border='1'><tr><td width='100%'><div id='CarguesDiaKardex'>No se encontraron registros</div></td></tr></table>");
			}
			rs2.getStatement().getConnection().close();*/

				/*rs=mvf.DetalledeFormulacion(CodFormulacionK);
				out.print("<table width='100%' border='1'><tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div></td><td width='6%'><div align='center'>Accion</div></td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs.getString(5)+")' >Omitir</a></td></tr>");
				}
				out.print("<table>");
				rs.getStatement().getConnection().close();*/
				//mostrar kardex
				/*******************************************************************************/
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/******************************************************************************************************/
		/******************************************************************************************************/
		if(va.equals("GMKI")){
			String Dosificacion="Dar "+CantidadMedicamento+" Cada "+LapsoMed+". "+ViaAdm;
			String ObservacionMedKard=req.getParameter("ObservacionMedKard");
			/*
			 * 1.)Consultar el codigo del medicamento. OK
			 * 2.)Ingresar en la tabla hic_formulacion. OK
			 * 3.)Ingresar en la tabla detalle_formulacion OK 
			 * 4.)Consultar si el medicamento pertenece a una orden de produccion OK
			 * 4.)Si pertence a la orden de produccion insertarlo en la tabla de farc_orden_produccion OK
			 * 5.)Consultar Si hay Orden de Produccion Activa Para el dia, 
			 *    si no la hay, crearla validando la hora de cierre de la orden. OK
			 * 6.)se consulta la orden de produccion activa y se toma el codigo junto con el cod_medica y 
			 * 	  se inserta en la tabla hic_detalle_orden_produccion OK
			 * 7.) Mostrar kardes actualizado   
			 */
			
			//1
			String TiempoInfusion =req.getParameter("txtTiempoInfusion");
			String cmbSolvente=req.getParameter("cmbSolvente"); 
			String NomUnidadInf=req.getParameter("NomUnidadInf");
			String Obser=TiempoInfusion+" "+NomUnidadInf;
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
			String CodDetalleFormulacionK="";
			try {
				//el medicamento no pertenece a orden de produccion
				String CodMedK="";
				String CodFormulacionK="";
				//String CodDetalleFormulacionK="";
				rs1=msa.ObtenerMedicamento(NombreMedicamento, CodFormaFarmaceutica, Concentracion, UnidadMed);
				if(rs1.next()){
					CodMedK=rs1.getString(1);
					//crear en hic_formulacion
					//se verifica si hay una orden de formulacion de kardex activa en la db estado=4
					rs2=mvf.ObtenerCodigoFormulacionKardesActiva(CodAdmK,fecha+"");
					if(rs2.next()){
						//si existe una orden de formulacion de kardex
						CodFormulacionK=rs2.getString(1);
						String CanLetra=Convertir(CantidadMedicamento);
						mvf.CrearDetalleFormulacionK(rs2.getString(1), rs1.getString(1), CantidadMedicamento, Dosificacion+" "+Obser, ObservacionMedKard, "1", CanLetra, "1 Dia(s)", DosisK, NomUnidad,fecha+"",hora+"",ObserInfu,CodUsuK,ObservacionMedKard);
				    	mvf.CrearDetalleFormulacionK(rs2.getString(1), cmbSolvente, "1", Dosificacion+" "+Obser, ObservacionMedKard, "1", CanLetra, "1 Dia(s)", "", NomUnidad,fecha+"",hora+"",ObserInfu,CodUsuK,ObservacionMedKard);
				
					}else{
						//no existe orden de kardex se crea
						mvf.CrearFormulacion(CodPacK, CodAdmK, "", "2", CodUsuK, CodCamaK, CodAreaK, CodSubareaK, hora+"", fecha+"");
						rs4=mvf.ObtenerCodigoFormulacionKardesActiva(CodAdmK,fecha+"");
						if(rs4.next()){
							//crear detalle formulacion
							CodFormulacionK=rs4.getString(1);
							String CanLetra=Convertir(CantidadMedicamento);
							mvf.CrearDetalleFormulacionK(rs4.getString(1), rs1.getString(1), CantidadMedicamento, Dosificacion+" "+Obser, ObservacionMedKard, "1", CanLetra, "1 Dia(s)",DosisK, NomUnidad,fecha+"",hora+"",ObserInfu,CodUsuK,ObservacionMedKard);
							mvf.CrearDetalleFormulacionK(rs2.getString(1), cmbSolvente, "1", Dosificacion+" "+Obser, ObservacionMedKard, "1", CanLetra, "1 Dia(s)", "", NomUnidad,fecha+"",hora+"",ObserInfu,CodUsuK,ObservacionMedKard);
							
						}
						rs4.getStatement().getConnection().close();

					}
					rs2.getStatement().getConnection().close();					
					rs2=mvf.ObtenerCodigoDetalleFormulacionK(CodFormulacionK, CodMedK,fecha+"",hora+"");
					if(rs2.next()){
						CodDetalleFormulacionK=rs2.getString(1);
						mvf.CrearDispenacion(rs2.getString(1));
						String FrecK="";
						Date FechaMedicamento=rs2.getDate(2);
						String HoraMedicamento=rs2.getString(3);
						String sep=rs2.getString(4).replace(" ", "_");	
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1){FrecK=d[3];}
						
						String HorFor="";
						int r=HoraMedicamento.split(":").length;
						String [] e=HoraMedicamento.split(":");
						for(int y=0; y<r-1;y++){HorFor=e[0];}
						
						//out.print("FechaMedicamento="+FechaMedicamento+" HoraMedicamento="+HoraMedicamento+" FrecK="+FrecK+" HorFor="+HorFor);
						//verificar la hora proxima de la dosis comparando la hora y fecha que se le asigno el medicamento
						//con la fecha y hora que trae la dosificacion estandar de cada frecuencia
						//insertarlo en la tabla hic_horas_dosificacion
						rs3=mvf.ObtenerHorasDispensacion(FrecK);
						int HoraI=Integer.parseInt(HorFor);
						Date FechaMa=null;
						while(rs3.next()){
							HoraI=HoraI+Integer.parseInt(FrecK);
							if(HoraI==24){
								HoraI=0;
							}
							if(HoraI>24){
								HoraI=HoraI-24;
								//FechaMedicamento= sumar la fecha mas uno cuando pase de 24 horas
								long tiempoActual = FechaMedicamento.getTime();
								long unDia = 1 * 24 * 60 * 60 * 1000;
								FechaMa = new Date(tiempoActual + unDia);
								//*
							}
							//System.out.println("HoraI="+HoraI+":00:00 Frecuencia:"+FrecK+" HoraMedicamento="+HoraMedicamento+"FechaMa="+FechaMa);
							mvf.IngresarHoraDosificacion(rs2.getString(1), FechaMedicamento+"", HoraI+":00:00", CodUsuK);
							//HoraI=0;
						}
						rs3.getStatement().getConnection().close();
						//********
					}
					rs2.getStatement().getConnection().close();
					//se consulta el codigo de la formulacion
					//rs2=mvf.ObtenerCodigoFormulacionKardes(hora, fecha, CodAdmK);
				}
				rs1.getStatement().getConnection().close();
				/******************************************************************************/
				rs=msa.ObtenerMedicamentoOrdenProduccion(CodAdmK, CodMedK,fecha+"");
				if(rs.next()){
					//el medicamento pertenece a orden de produccion
					rs1=mvf.ObtenerOrdenProduccion(fecha+"");
					if(rs1.next()){
						//tiene creado orden de produccion para la fecha
						//se crea detalle de la orden de produccion
						//mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), CodFormulacionK, rs.getString(2), CodPacK, rs.getString(3), rs.getString(4), DosisK, FrecuenciaK, rs.getString(5));
						String horString = "";
						rs3=mvf.ObtenerCorteProduccion();
						if(rs3.next()){
							horString=rs3.getString(2);
						}
						rs3.getStatement().getConnection().close();
						String hora1=hora+"";
						String hora2=horString;
						String ho1 = null;
						String ho2 = null;
						String mo1 = null;
						String mo2 = null;
						int f1=hora1.split(":").length;
						String[] j1=hora1.split(":");		     	
						for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
						int f2=hora2.split(":").length;
						String[] j2=hora2.split(":");		     	
						for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
						if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
							//la hora actual es mayor a la del corte de la orden de produccion						
						}else{
							//la hora actual es menor a la del corte de la orden de produccion
							if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
								//los minutos actuales son mayores a los del corte de la orden de produccion
							}else{
								//los minutos actuales son menores a los del corte de la orden de produccion.
								mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), CodFormulacionK, rs.getString(2), CodPacK, rs.getString(3), rs.getString(4), DosisK, FrecuenciaK, rs.getString(5));
								//mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), CodFormulacionK, rs.getString(2), CodPacK, rs.getString(3), rs.getString(4), DosisK, FrecuenciaK, rs.getString(5));
							}
						}

					}else{
						//no tiene orden de produccion creada
						mvf.CrearOrdenProduccion(fecha+"", hora+"");
						rs2=mvf.ObtenerOrdenProduccion(fecha+"");
						if(rs2.next()){
							// se verifica la hora de corte de la orden de produccion
							/*********************************************************************/
							String horString = "";
							rs3=mvf.ObtenerCorteProduccion();
							if(rs3.next()){
								horString=rs3.getString(2);
							}
							rs3.getStatement().getConnection().close();
							
							
							String hora1=hora+"";
							String hora2=horString;
							String ho1 = null;
							String ho2 = null;
							String mo1 = null;
							String mo2 = null;
							int f1=hora1.split(":").length;
							String[] j1=hora1.split(":");		     	
							for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
							int f2=hora2.split(":").length;
							String[] j2=hora2.split(":");		     	
							for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
							if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
								//la hora actual es mayor a la del corte de la orden de produccion							
							}else{
								//la hora actual es menor a la del corte de la orden de produccion
								if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
									//los minutos actuales son mayores a los del corte de la orden de produccion
								}else{
									//los minutos actuales son menores a los del corte de la orden de produccion.
									rs3=msa.ObtenerMedicamentoOrdenProduccion(CodAdmK, CodMedK,fecha+"");
									if(rs3.next()){
										rs4=mvf.ObtenerOrdenProduccion(fecha+"");
										if(rs4.next()){
											mvf.CrearDetalleOrdenProduccion(rs4.getString(1), rs3.getString(1), CodFormulacionK, rs3.getString(2), CodPacK, rs3.getString(3), rs3.getString(4), DosisK, FrecuenciaK, rs3.getString(5));
										}
										rs4.getStatement().getConnection().close();
									}
								}
							}
							/********************************************************************/
						}
						rs2.getStatement().getConnection().close();
					}
					rs1.getStatement().getConnection().close();
					
				}			
				rs.getStatement().getConnection().close();
				/*******************************************************************************/
				java.util.Calendar hoy = java.util.Calendar.getInstance();
				java.util.Date primerDia= hoy.getTime();
				int diasMes[]= new int[12];
				diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
				diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;

				int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
				String NombreMes = "";
				anio=hoy.get(java.util.Calendar.YEAR);
				dia=hoy.get(java.util.Calendar.DATE);
				mes=hoy.get(java.util.Calendar.MONTH);
				mes=mes+1;
				if(mes==1){NombreMes="Enero";}
				if(mes==2){NombreMes="Febrero";}
				if(mes==3){NombreMes="Marzo";}
				if(mes==4){NombreMes="Abril";}
				if(mes==5){NombreMes="Mayo";}
				if(mes==6){NombreMes="Junio";}
				if(mes==7){NombreMes="Julio";}
				if(mes==8){NombreMes="Agosto";}
				if(mes==9){NombreMes="Septiembre";}
				if(mes==10){NombreMes="Octubre";}
				if(mes==11){NombreMes="Noviembre";}
				if(mes==12){NombreMes="Diciembre";}

				if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) /*Compruebo que el a�o es bisiesto*/
				{
					diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
				}
				nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
				primerDia.setDate(1);
				diaInicial=primerDia.getDay();
				
				out.print("<table width='100%' border>");
				out.print("<tr><th colspan=41 align=center>"+NombreMes+" Del "+anio+" "); 		
				/*************************************************/
				out.print("<tr bgcolor='#CCCCCC' >");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");
				/*out.print("<th align=center>M");
				out.print("<th align=center>M");
				out.print("<th align=center>J");
				out.print("<th align=center>V");
				out.print("<th align=center>S");*/
				out.print("</tr>");
				out.print("<tr></tr>");
				columna=0;
				for(i=0;i<diaInicial;i++) /*Bucle para la reserva de espacios de los d�as en el calendario*/
				{
					out.print("<td align=center><font size+=4>.");
					columna++;
					out.print("</font>");
				}
				int a=0;
				for(i=1;i<=nDias;i++) /*Bucle para la inclusi�n del n�mero de d�a en el calendario*/
				{
					a=a+1;
					out.print("<td align=center>");				
					if(i==dia) //Si el d�a a escribir es el d�a de hoy
					{
						String valueF=anio+"-"+mes+"-"+i;
						out.print("<b>"+i+"</b>");
						out.print("<font color=\"#ff0000\" size+=7><b>");
						//out.print("<input name='radiobutton' type='radio' checked='true' onclick='CarguesDiaKardex()' value="+anio+"-"+mes+"-"+i+" />");
						out.print("<input name='radiobutto' type='radio' value='radiobutto' checked='true' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
						out.print("</b></font>"); //Cerramos la etiqueta de edici�n de las propiedades de las fuentes
					}else{
						String valueF=anio+"-"+mes+"-"+i;
						out.print(i);
						//out.print("<input name='chkHorarioMedico' type='radio'  onclick='CarguesDiaKardex()' value="+anio+"-"+mes+"-"+i+" />");
						out.print("<input name='radiobutto' type='radio' value='radiobutto' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
					}				}

			//*	
			out.print("</table>");
			out.print("<table width='100%' ><tr><td width='30%' ></td><td width='70%' ><table width='100%' border='1' style='font-size:smaller'><tr><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td>");
			out.print("<td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td>");
			out.print("<td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td>");
			out.print("<td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td></tr></table></td></tr></table>");
			out.print("<table width='100%' border='1'>" +
					"<tr>" +
					"<td width='100%'><div id='CarguesDiaKardex'></div></td>" +
					"</tr>" +
					"</table>");
			
			
			
			String cp="";
			rs4=mvf.ObtenerCodPerfilft(CodAdmK);
			if(rs4.next()){
				//hay perfil creado
				cp=rs4.getString(1);
				//VIA=VIA INTRAVENOSA ETC.
				//FRECUENCIA=1 CADA 24 HORAS, ETC
				//CANTIDAD=ES LA CANTIDAD DE LA DOSIFICACION NO LA TOTAL.EJ:
				//DAR 2 CADA 8 HORAS.VIA ORAL, LA CANTIDAD=2
				String sep="";String sep1="";String sep2="";String sep3="";
				String sep4="";	String sep5="";String sep6="";String sep7="";
				sep=Dosificacion.replace(" ", "_");	
				System.out.print("Dosificacion="+sep);
				int h=sep.split("_").length;
				String[] d=sep.split("_");		     	
				for(int g=0; g<h-1; g=g+1)
				{ 		
					
					sep1=d[0];sep2=d[1];sep3=d[2];sep4=d[3];
					sep5=d[4];sep6=d[5];sep7=d[6];
					
					System.out.print(sep1+"//"+sep2+"//"+sep3+"//"+sep4+"//"+sep5+"//"+sep6);
				}
				String via=sep7;
				String frecuencia=sep3+" "+sep4+" "+sep5;		
				
				mvf.CrearDetallePerfilft(cp,CodMedK,CodUsuK,via,frecuencia,sep2,fecha+"",hora+"",CodDetalleFormulacionK);
			}else{
				//no hay perfil creado
				String fi="";String codArea1="";String codSubarea1="";String codCama1="";
				String cdx="";String sexo="";String edad="";String Serv="";	String aler="";
				String peso="";
				rs1=mvf.ObtenerDatosPerfilFarmaco(codAdm);
				if(rs1.next()){
					fi=rs1.getString(1);
					codArea1=rs1.getString(4);
					codSubarea1=rs1.getString(5);;
					codCama1=rs1.getString(6);
					cdx=rs1.getString(7);
					sexo=rs1.getString(2);
					if(sexo.equals("Masculino")){sexo="M";}
					if(sexo.equals("Femenino")){sexo="F";}
					edad=rs1.getString(3);
					Serv=rs1.getString(8);		
					mvf.CrearPerfilft(CodAdmK,CodPacK,fi,null,codArea1,codSubarea1,codCama1,cdx,aler,sexo,edad,PesoK,TallaK);
					rs2=mvf.ObtenerCodPerfilft(CodAdmK);
					if(rs2.next()){
						//hay perfil creado
						cp=rs2.getString(1);
						//VIA=VIA INTRAVENOSA ETC.
						//FRECUENCIA=1 CADA 24 HORAS, ETC
						//CANTIDAD=ES LA CANTIDAD DE LA DOSIFICACION NO LA TOTAL.EJ:
						//DAR 2 CADA 8 HORAS.VIA ORAL, LA CANTIDAD=2
						String sep="";String sep1="";String sep2="";String sep3="";
						String sep4="";	String sep5="";String sep6="";String sep7="";
						sep=Dosificacion.replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1)
						{ 		
							sep1=d[0];sep2=d[1];sep3=d[2];sep4=d[3];
							sep5=d[4];sep6=d[5];sep7=d[6];
						}
						String via=sep7;
						String frecuencia=sep3+" "+sep4+" "+sep5;		
						
						mvf.CrearDetallePerfilft(cp,CodMedK,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",CodDetalleFormulacionK);
					}
					rs2.getStatement().getConnection().close();
					
				}
				rs1.getStatement().getConnection().close();
				
			}
			rs4.getStatement().getConnection().close(); 
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/******************************************************************************************************/
		/******************************************************************************************************/
		String CodDetForK=req.getParameter("CodDetForK");
		java.util.Date FechServ = new java.util.Date();
		java.sql.Date FechaAdministra = new java.sql.Date(FechServ.getTime());
		java.sql.Time HoraAdministra = new java.sql.Time(FechServ.getTime());	
		
		if(va.equals("ELFK")){			
			rs=mvf.ObtenerCodigoHorasDispensacionMedicamentoEliminar(CodDetForK);
			try {
				if(rs.next()){
					out.print("No");
				}else{
					mvf.OmitirDetalleFormulacion(CodDetForK);
					out.print("Si");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("RFFK")){
			String FechaKardex="";			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());
			java.sql.Date fechaS = new java.sql.Date((fechaActual.getTime())+1);	
			String FechaSgt="";
			rs=mvf.ObtenerCodigoHorasDispensacionMedicamento(CodDetForK);
			try {
				if(rs.next()){
				System.out.println("fecha="+fecha+" fechaS="+fechaS);
						rs1=mvf.DiaSiguiente();	if(rs1.next()){	FechaSgt=rs1.getString(1);	}	rs1.getStatement().getConnection().close();
						//*se verifica si esta creado la formulacion del dia
						//si esta creada{
						//	* se consulta el medicamento para crear un registro igual
						//	* se crea el medicamento en la tabla detalle_formulacion
						//	* se crea el detalle de dosificacion del detalle_formulacion
					//	}
						//sino esta creada{
						//	se crea el dia de Formulacion
						//	*se consulta el codigo de la formulacion
						//	* se crea el medicamento en la tabla detalle_formulacion
						//	* se crea el detalle de dosificacion del detalle_formulacion
					//	}
						
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		if(va.equals("EDFK")){
			//mvf.OmitirDetalleFormulacion(CodDetForK);
			mvf.SuspenderMedicamento(CodDetForK);
			//* buscar los detalles del medicamento
			rs=mvf.ObtenerCodigoHorasDispensacionMedicamento(CodDetForK);
			try {
				while(rs.next()){
					rs1=mvf.ObtenerNombreUsuarioK(CodUsuK);
					if(rs1.next()){
						//mvf.ActualizarDetalleDosis(CodUsu, rs1.getString(1), FechaAdministra+"", HoraAdministra+"", "1", CodHoraDisp,"-");
						if((rs.getString("estado").equals("1"))||(rs.getString("estado").equals("2"))){
							
						}else{
							mvf.ActualizarDetalleDosis(CodUsuK, rs1.getString(1), FechaAdministra+"", HoraAdministra+"", "3", rs.getString("codigo"),"Medicamento Suspendido");
						}
					}
					rs1.getStatement().getConnection().close();
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("UNI")){			
			try {
				int contu=0;
				String CodUn="";String NomUn="";String CodMedica="";String FormaFarma="";
				rs=msa.ObtenerUnidad(NombreMedicamento, CodFormaFarmaceutica, Concentracion);
				out.print("<select name='cmbUnidad' id='cmbUnidad' onchange='BuscarCodMedicamento()' >");
				while(rs.next()){
					contu=contu+1;
					CodUn=rs.getString(1);
					NomUn=rs.getString(2);
				}
				if(contu==1){
					out.print("<option value="+CodUn+">"+NomUn+"</option>");
					//buscar codigo del medicamento
					rs1=msa.ObtenerMedicamento(NombreMedicamento, CodFormaFarmaceutica, Concentracion, CodUn);
					if(rs1.next()){
						CodMedica=rs1.getString(1);
						FormaFarma=rs1.getString(11);
						
						//out.print("<input id='txtCodMedica' name='txtCodMedica' type='hidden' value="+rs1.getString(1)+" >");
					}
					rs1.getStatement().getConnection().close();

				}else{
					rs1=msa.ObtenerUnidad(NombreMedicamento, CodFormaFarmaceutica, Concentracion);
					out.print("<option value='Seleccione'>Seleccione</option>");
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				}
				out.print("<select><div id='CodMedTxt'><input id='txtCodMedica' name='txtCodMedica' type='hidden' value="+CodMedica+" ><input id='txtCodFF' name='txtCodFF' type='hidden' value="+FormaFarma+" ><div>");
				
				if((FormaFarma.equals("45"))||(FormaFarma.equals("9"))||(FormaFarma.equals("11"))||(FormaFarma.equals("15"))||(FormaFarma.equals("50"))||(FormaFarma.equals("30"))||(FormaFarma.equals("44"))||(FormaFarma.equals("3"))){
					out.print("<input name='GInfusion' type='hidden' id='GInfusion' value='1'>");
				}else{
					out.print("<input name='GInfusion' type='hidden' id='GInfusion' value='0'>");				
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//public 
		if(va.equals("BCM")){			
			try {
				rs1=msa.ObtenerMedicamento(NombreMedicamento, CodFormaFarmaceutica, Concentracion, UnidadMed);
				if(rs1.next()){
					String FormaFarma=rs1.getString(11);
					out.print("<input id='txtCodMedica' name='txtCodMedica' type='hidden' value="+rs1.getString(1)+" ><input id='txtCodFF' name='txtCodFF' type='hidden' value="+rs1.getString(11)+" >");
					if((FormaFarma.equals("45"))||(FormaFarma.equals("9"))||(FormaFarma.equals("11"))||(FormaFarma.equals("15"))||(FormaFarma.equals("50"))||(FormaFarma.equals("30"))||(FormaFarma.equals("44"))||(FormaFarma.equals("3"))){
						out.print("<input name='GInfusion' type='hidden' id='GInfusion' value='1'>");
					}else{
						out.print("<input name='GInfusion' type='hidden' id='GInfusion' value='0'>");						
					}
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("VDIN")){
			try {
			out.print("<table border='0' width='100%' ><tr><td width='22%' class='style12'><label><input type='hidden' id='cmbViaAdm' value='Via Infusion'></label>Ra-Do<label><input name='txtRangoI' type='text' id='txtRangoI' size='5'></label></td><td width='16%' class='style12'>Hasta <input name='txtRangoF' type='text' id='txtRangoF' size='5'></td><td width='18%' class='style12'>Razon<input name='txtRazon' type='text' id='txtRazon' size='5'></td><td width='44%' class='style12'>U-Infusion<select name='select' id='select'><option value='Seleccione'>Seleccione</option>");
			//out.print("<option value='unidad1'>unidad1</option>");
			rs=msa.ObtenerUnidadesInfusion();			
			while(rs.next()){out.print("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");}
			rs.getStatement().getConnection().close();
			out.print("</select>");
		} catch (SQLException e){e.printStackTrace();}
			out.print("</select></td></tr></table>");
			
		}
		
		if(va.equals("DF")){
			try {
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
				out.print("<tr class='style12'><td width='10%'>Solvente</td><td colspan='3'><select name='cmbSolvente' id='cmbSolvente'><option value='Seleccione'>Seleccione</option>");
			
				rs=msa.ObtenerSolventes();			
				while(rs.next()){out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");}
				rs.getStatement().getConnection().close();
			
				out.print("</select></td></tr>");
				out.print("<tr class='style12'><td>Dosis</td><td width='47%'><input name='txtDosis' type='text' id='txtDosis'><select name='cmbUnidadInfu' id='cmbUnidadInfu'><option value='Seleccione' selected='' >Seleccione</option>");
			
				rs=msa.ObtenerUnidadesInfusion();			
				while(rs.next()){out.print("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");}
				rs.getStatement().getConnection().close();
			
				out.print("</select></td><td width='20%'>Tiempo de  Infusion </td><td><input name='txtTiempoInfusion' type='text' id='txtTiempoInfusion'></td></tr>");
				out.print("<tr class='style12'><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table>");
			} catch (SQLException e){e.printStackTrace();}
		}
		
		if(va.equals("VAD")){
			String TipoHtml=req.getParameter("TipoHtml");
			try {
				if(TipoHtml.equals("I")){
					/*out.print("<table border='1' width='100%' border='1' cellspacing='0' cellpadding='0' >");
					out.print("<tr><td>Minimo</td><td>Maximo</td><td>Razon</td><td>Und Infusion</td></tr>");
					out.print("<tr><td class='style12'><label><input type='hidden' id='cmbViaAdm' value='Via Infusion'></label><label>" +
							"<input name='txtRangoI' type='text' id='txtRangoI' size='5'></label></td>" +
							"<td class='style12'><input name='txtRangoF' type='text' id='txtRangoF' size='5'></td>" +
							"<td class='style12'><input name='txtRazon' type='text' id='txtRazon' size='5'></td>" +
							"<td class='style12'><select name='select' id='cmbUnidadInfu'><option value='Seleccione'>Seleccione</option>");
					//out.print("<option value='unidad1'>unidad1</option>");
					rs=msa.ObtenerUnidadesInfusion();			
					while(rs.next()){out.print("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");}
					rs.getStatement().getConnection().close();
				//	out.print("</select>");
					out.print("</select></td><td><span class='style12'>Dosis<input name='txtDosis' type='text' id='txtDosis' size='4' maxlength='5'></span><input name='button' type='button' onClick='GuardarMedicamentoKardes()' value='+' /></td></tr></table>");
					*/
					
				/*	TIPOS DE INFUSION.
				 * 	out.println("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr><td colspan='4'  align='center' class='style12'>SELECCIONE INFUSION <select name='cmbTipoInfusion' id='cmbTipoInfusion' onChange='CargarTipoInfusion()'><option value='Seleccione' selected='selected'>Seleccione</option><option value='DF'>Dosis Fija</option><option value='DV'>Dosis Variable</option>");
					out.println("<option value='DU'>Discontinua Unica</option><option value='DR'>Discontinua Repetitivas</option><option value='IF'>Infusion Continua</option><option value='VSP'>Variable Segun Parametro</option><option value='VST'>Variable Segun Tiempo</option></select> </td></tr>");
					out.println("<tr><td colspan='4' id='TipoInfusion'>&nbsp;</td></tr></table>");*/
	
					out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
					out.print("<tr class='style12'><td width='10%'>Solvente</td><td colspan='3'><select name='cmbSolvente' id='cmbSolvente'><option value='Seleccione'>Seleccione</option>");
				
					rs=msa.ObtenerSolventes();			
					while(rs.next()){out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");}
					rs.getStatement().getConnection().close();
				
					out.print("</select></td></tr>");
					out.print("<tr class='style12'><td>Dosis</td><td width='47%'><input name='txtDosis' type='text' id='txtDosis'><select name='cmbUnidadInfu' id='cmbUnidadInfu'><option value='Seleccione' selected='' >Seleccione</option>");
				
					rs=msa.ObtenerUnidadesInfusion();			
					while(rs.next()){out.print("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");}
					rs.getStatement().getConnection().close();
				
					out.print("</select></td><td width='20%'>Tiempo de  Infusion </td><td><input name='txtTiempoInfusion' type='text' id='txtTiempoInfusion'></td></tr>");
					out.print("<tr class='style12'><td>&nbsp;</td><td><input name='button' type='button' onClick='GuardarMedicamentoKardesSolvente()' value='Ingresar' /></td><td>&nbsp;</td><td>&nbsp;</td></tr></table>"); 
					
					/*rs=msa.ObtenerUnidadesInfusion();	
					out.print("<table border='1'><tr>");
					int columna=0;
					while(rs.next()){						
						out.print("<td>"+columna+"</td>");
						columna++;
						if(columna==5){out.print("</tr>");columna=0;}
					}*/
				}
				if(TipoHtml.equals("D")){
					out.print("<table border='1' width='100%' border='1' cellspacing='0' cellpadding='0'><tr><td><span class='style12'>Vias Adm</span></td><td><select name='cmbViaAdm' id='cmbViaAdm'><option value='Seleccione'>Seleccione</option>");
					rs=msa.ObtenerViaAdministracion();			
					while(rs.next()){out.print("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");}
					rs.getStatement().getConnection().close();
					out.print("</select></td>" +
							"<td><span class='style12'>Dosis<input name='txtDosis' type='text' id='txtDosis' size='4' maxlength='5'></span></td></tr>" +
							"<tr><td class='style12'>Observacion</span></td><td><textarea name='textarea' id='txtObservacionMedKard' col='30'></textarea></td>" +
							"<td><input name='button' type='button' onClick='GuardarMedicamentoKardes()' value='     Ingresar     ' /></td></tr>");
				}
			
			} catch (SQLException e){e.printStackTrace();}
			
		}
		
		if(va.equals("CCN")){			
			try {
				int contc=0;
				String CodConc="";String NomConc="";
				rs=msa.ObtenerConcentracion(NombreMedicamento, CodFormaFarmaceutica);
				out.print("<select name='cmbConcentracion' id='cmbConcentracion' onchange='BuscarUnidad()'>");
				while(rs.next()){
					contc=contc+1;
					CodConc=rs.getString(1);
					NomConc=rs.getString(1);
					
				}
				if(contc==1){
					out.print("<option value='"+CodConc+"'>"+NomConc+"</option>");
				}else{
					rs1=msa.ObtenerConcentracion(NombreMedicamento, CodFormaFarmaceutica);
					out.print("<option value='Seleccione'>Seleccione</option>");
					while(rs1.next()){
						out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(1)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				}
				out.print("<select><input id='txtcontc' name='txtcontc' type='hidden' value="+contc+" />");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("LFF")){			
			try {
				int cont=0;
				String Codff="";String Nomff="";
				rs=msa.ObtenerFormasFarmaceuticas(NombreMedicamento);
				out.print("<select name='cmbFFarmaceutica' id='cmbFFarmaceutica' onchange='BuscarConcentracion()'>");
				while(rs.next()){
					cont=cont+1;
					Codff=rs.getString(3);
					Nomff=rs.getString(2);
				}
				if(cont==1){
					out.print("<option value="+Codff+">"+Nomff+"</option>");
				}else{
					rs1=msa.ObtenerFormasFarmaceuticas(NombreMedicamento);
					out.print("<option value='Seleccione'>Seleccione</option>");
					while(rs1.next()){
						out.print("<option value="+rs1.getString(3)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				}
				out.print("<select><input id='txCont' name='txCont' type='hidden' value="+cont+" />");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("AMC")){			
			try {
				rs=msa.ObtenerMedicamentoCardex(NombreMedicamento);
				int cont=0;
				String NomMed="";
				out.print("<table>");
				while(rs.next()){
					
					out.print("<tr><td><a href='#' onclick='SelMedCard(&quot;"+rs.getString(3)+"&quot;)'>"+rs.getString(3)+"</a></td></tr>");
					NomMed=rs.getString(3);
					cont++;
				}
				out.print("<tr><td><input name='txtContador' type='hidden' id='txtContador' value='"+cont+"'  ></td></tr>");
				out.print("<tr><td><input name='txtNomMedi' type='hidden' id='txtNomMedi' value='"+NomMed+"'  ></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/*
		 * 
		 */
		if(va.equals("CamKar")){
			String FechaKardex=req.getParameter("FechaKardex");			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());	
			
			
			
			///////////////////
			 Calendar c1 = GregorianCalendar.getInstance();
		        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        sdf = new SimpleDateFormat("yyyy-MM-dd");
		        c1.add(Calendar.DATE, 1);
			String Perfil=req.getParameter("Perfil");
					
			
			
			try {
				rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(FechaKardex, CodAdmK,"2");
				String CodFormK="";
				String CodDetalleFormulacionK="";
				if(rs2.next()){
					CodFormK=rs2.getString(1);
				
					out.print("<table width='100%' border='1'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+FechaKardex+"'>");
					out.print("<table width='100%' border='1'><tr><td width='30%'><div id='DatosEmerg'>-</div></td>" +
							"<td width='70%'><table width='100%' border='1' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fecha+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
							"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
							"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
							"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
							"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
							"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
							"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +													
							"</tr></table></td></tr></table>");
				
				out.print("<table width='100%' border='1'>");
				rs=mvf.DetalledeFormulacionK(CodFormK);
					while(rs.next()){
						
						CodDetalleFormulacionK=rs.getString(4);
						if(Perfil.equals("0")){
							//perfil enfermera
							if(rs.getString("estado").equals("1")){	
								//medicamento activo
								out.print("<tr title='"+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
								out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
								out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
								//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
								//out.print("<td  bgcolor='#D8D8D8' onclick='SuspenderMK("+CodDetalleFormulacionK+")' ><b><font >SUSPENDER</font></b></td>");
							}
							if(rs.getString("estado").equals("2")){
								//medicamento suspendido
								out.print("<tr bgcolor='#D8D8D8' title='Medicamento Suspendido. Fue Formulado Por:"+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
								out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
								out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
								//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
							}
						}
						if(Perfil.equals("1")){
							//perfil medico
							if(fecha.toString().equals(FechaKardex)){
								System.out.println("SON FECHAS IGUALES fecha="+fecha+" fechaK="+FechaKardex);
								if(rs.getString("estado").equals("1")){	
									//medicamento activo
									out.print("<tr title='Medicamento Formulado Por "+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
									out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td> " +
											"<td  bgcolor='#C94141' onclick='EliminarMK("+CodDetalleFormulacionK+")' ><b><font color='white'>ELIMINAR</font></b></td></tr>");
									out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
									//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
									out.print("<td  bgcolor='#D8D8D8' onclick='SuspenderMK("+CodDetalleFormulacionK+")' ><b><font >SUSPENDER</font></b></td>");
								}
								if(rs.getString("estado").equals("2")){
									//medicamento suspendido
									out.print("<tr bgcolor='#D8D8D8' title='Medicamento Suspendido. Fue Formulado Por:"+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
									out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
									out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
									//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
								}
							}else{
								if(rs.getString("estado").equals("1")){	
									//medicamento activo
									out.print("<tr title='Medicamento Formulado Por "+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
									out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td> " +
											//"<td  bgcolor='#C94141' onclick='EliminarMK("+CodDetalleFormulacionK+")' ><b><font color='white'>ELIMINAR</font></b></td>" +
													"</tr>");
									out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
									//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
									//out.print("<td  bgcolor='#D8D8D8' onclick='SuspenderMK("+CodDetalleFormulacionK+")' ><b><font >SUSPENDER</font></b></td>");
								}
								if(rs.getString("estado").equals("2")){
									//medicamento suspendido
									out.print("<tr bgcolor='#D8D8D8' title='Medicamento Suspendido. Fue Formulado Por:"+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
									out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
									out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
									//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
								}
							}
						}
						out.print("	</tr></table></td><td width='70%'><div id='DosisKardex'>");
						/**********************/
						out.print("<table width='100%' border='1' id='columns' style='font-size:smaller'>");
						/*****ACOMODAR CHECK-BOX A LA HORA********/
						String FrecK="";
						String HoraFre="";
						String sep=rs.getString(6).replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1){FrecK=d[1];}
						String CodAdministracion="";
						rs3=mvf.ObtenerHorasDispensacionMedicamento(CodDetalleFormulacionK);
						while(rs3.next()){
							HoraFre=HoraFre+""+rs3.getInt(12)+"_"+"";
							CodAdministracion=CodAdministracion+""+rs3.getString(1)+"_"+"";
						}
						rs3.getStatement().getConnection().close();
						int ho=HoraFre.split("_").length;
						String[] k=HoraFre.split("_");
						for(int i=7; i<=30; i++){
							int sw=0;
							for(int t=0;t<ho;t++){
								if(k[t].equals("0")){k[t]="24";}if(k[t].equals("1")){k[t]="25";}
								if(k[t].equals("2")){k[t]="26";}if(k[t].equals("3")){k[t]="27";}
								if(k[t].equals("4")){k[t]="28";}if(k[t].equals("5")){k[t]="29";}
								if(k[t].equals("6")){k[t]="30";}
								if(k[t].equals(i+"")){
									if(i==24){i=0;}if(i==25){i=1;}if(i==26){i=2;}
									if(i==27){i=3;}if(i==28){i=4;}if(i==29){i=5;}if(i==30){i=6;}
									rs3=mvf.ObtenerCodigoHorasDispensacionMedicamento(CodDetalleFormulacionK, i+":00:00");
									if(fecha.toString().equals(FechaKardex)){
										System.out.println("SON FECHAS IGUALES fecha="+fecha+" fechaK="+FechaKardex);
										if(rs3.next()){
											if(rs3.getString(10).equals("0")){
												if(Perfil.equals("1")){
													//perfil medico
													out.print("<td width='4%' height='35' bgcolor='#DFDBB3'><div id='dlleno' draggable='true' ></div></td>");
												}
												if(Perfil.equals("0")){
													//perfil enfermera
													out.print("<td width='4%' height='35' bgcolor='#DFDBB3'><div id='dlleno' draggable='true' ><input type='checkbox' name='ca' id='ca' onClick=Dispensar('"+rs3.getString(1)+"') /></div></td>");
												}
											}
											if(rs3.getString(10).equals("1")){
												String Dis="Dispensado por:"+rs3.getString(7)+" el dia "+rs3.getString("fecha_administracion")+" a las "+rs3.getString("hora_administracion");
												out.print("<td width='4%' height='35' bgcolor='#33CC33'  title='"+Dis+"' ></td>");
											}
											if(rs3.getString(10).equals("2")){
												String Dis="Cancelado por:"+rs3.getString(7)+" Debido a:"+rs3.getString(11);
												out.print("<td width='4%' height='35' bgcolor='#C94141'  title='"+Dis+"' ></td>");
											}
										}
										rs3.getStatement().getConnection().close();
									}else{
										System.out.println("SON FECHAS DESIGUALES fecha="+fecha+" fechaK="+FechaKardex);
										if(rs3.next()){
											if(rs3.getString(10).equals("0")){
												out.print("<td width='4%' height='35' bgcolor='#DFDBB3'><div id='dlleno' draggable='true' ></div></td>");
											}
											if(rs3.getString(10).equals("1")){
												String Dis="Dispensado por:"+rs3.getString(7)+" el dia "+rs3.getString("fecha_administracion")+" a las "+rs3.getString("hora_administracion");
												out.print("<td width='4%' height='35' bgcolor='#33CC33' title='"+Dis+"' ></td>");
											}
											if(rs3.getString(10).equals("2")){
												String Dis="Cancelado por:"+rs3.getString(7)+" Debido a:"+rs3.getString(11);
												out.print("<td width='4%' height='35' bgcolor='#C94141' title='"+Dis+"' ></td>");
											}
										}
										rs3.getStatement().getConnection().close();
									}
									
									
									
									sw=1;
								}
								if(i==0){i=24;}if(i==1){i=25;}if(i==2){i=26;}if(i==3){i=27;}
								if(i==4){i=28;}if(i==5){i=29;}if(i==6){i=30;}
							}
							if(sw==0){out.print("<td width='4%' height='35'  rowspan='4' ><div id='dvacio'>-</div></td>");}
						}
						out.print("</tr></table>");
						//*
						/******FIN DE ACOMODAS CHECK-BOX A LA HORA****************/
						out.print("</div></td></tr>");
					}
					rs.getStatement().getConnection().close();
					out.print("</tr></td></div></table>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		
		if(va.equals("MostrarKar")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());	
			
			///////////////////
			 Calendar c1 = GregorianCalendar.getInstance();
		        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        sdf = new SimpleDateFormat("yyyy-MM-dd");
		        c1.add(Calendar.DATE, 1);
			String CodDetalleFormulacionK="";
			try {
				rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(fecha+"", CodAdmK,"2");
				String CodFormK="";
				if(rs2.next()){
					CodFormK=rs2.getString(1);
					rs=mvf.DetalledeFormulacionK(CodFormK);
					out.print("<table width='100%' border='1'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+fecha+"'>");
					out.print("<table width='100%' border='1'><tr><td width='30%'>-</td>" +
							"<td width='70%'><table width='100%' border='1'><tr><td  bgcolor='#E6E6E6' colspan='17' align='center'>"+fecha+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr>" +									
							"<td width='4%' bgcolor='#819FF7'>07</td>" +
							"<td width='4%' bgcolor='#819FF7'>08</td>" +
							"<td width='4%' bgcolor='#819FF7'>09</td>" +
							"<td width='4%' bgcolor='#819FF7'>10</td>" +
							"<td width='4%' bgcolor='#819FF7'>11</td>" +
							"<td width='4%' bgcolor='#819FF7'>12</td>" +
							"<td width='4%' bgcolor='#819FF7'>13</td>" +
							"<td width='4%' bgcolor='#819FF7'>14</td>" +
							"<td width='4%' bgcolor='#819FF7'>15</td>" +
							"<td width='4%' bgcolor='#819FF7'>16</td>" +
							"<td width='4%' bgcolor='#819FF7'>17</td>" +
							"<td width='4%' bgcolor='#819FF7'>18</td>" +
							"<td width='4%' bgcolor='#819FF7'>19</td>" +
							"<td width='4%' bgcolor='#819FF7'>20</td>" +
							"<td width='4%' bgcolor='#819FF7'>21</td>" +
							"<td width='4%' bgcolor='#819FF7'>22</td>" +
							"<td width='4%' bgcolor='#819FF7'>23</td>" +
							"<td width='4%' bgcolor='#5882FA'><font color='white'>24</font></td>" +
							"<td width='4%' bgcolor='#5882FA'><font color='white'>01</font></td>" +
							"<td width='4%' bgcolor='#5882FA'><font color='white'>02</font></td>" +
							"<td width='4%' bgcolor='#5882FA'><font color='white'>03</font></td>" +
							"<td width='4%' bgcolor='#5882FA'><font color='white'>04</font></td>" +
							"<td width='4%' bgcolor='#5882FA'><font color='white'>05</font></td>" +
							"<td width='4%' bgcolor='#5882FA'><font color='white'>06</font></td>" +
							"</tr></table></td></tr></table>");
					while(rs.next()){
						CodDetalleFormulacionK=rs.getString(4);
						if(rs.getString("estado").equals("1")){	
							//medicamento activo
							out.print("<tr title='Medicamento Formulado Por "+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
							out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td><td  bgcolor='#C94141' onclick='EliminarMK("+CodDetalleFormulacionK+")' ><b><font color='white' >ELIMINAR</font></b></td></tr>");
							out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
							//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
							out.print("<td  bgcolor='#D8D8D8' onclick='SuspenderMK("+CodDetalleFormulacionK+")' ><b><font >SUSPENDER</font></b></td>");
						}
						if(rs.getString("estado").equals("2")){
							//medicamento suspendido
							out.print("<tr bgcolor='#D8D8D8' title='Medicamento Suspendido. Fue Formulado Por:"+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
							out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
							out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
							//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
						}						
						out.print("	</tr></table></td><td width='70%'><div id='DosisKardex'>");
						/**********************/
						out.print("<table width='100%' border='1' id='columns' style='font-size:smaller'>");
						/*****ACOMODAR CHECK-BOX A LA HORA********/
						out.print("");
						String FrecK="";
						String HoraFre="";
						String sep=rs.getString(6).replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1){FrecK=d[1];}
						String CodAdministracion="";
						rs3=mvf.ObtenerHorasDispensacionMedicamento(CodDetalleFormulacionK);
						while(rs3.next()){
							HoraFre=HoraFre+""+rs3.getInt(12)+"_"+"";
							CodAdministracion=CodAdministracion+""+rs3.getString(1)+"_"+"";
						}
						rs3.getStatement().getConnection().close();
						int ho=HoraFre.split("_").length;
						String[] k=HoraFre.split("_");
						for(int i=7; i<=30; i++){
							int sw=0;
							for(int t=0;t<ho;t++){
								if(k[t].equals("0")){k[t]="24";}if(k[t].equals("1")){k[t]="25";}
								if(k[t].equals("2")){k[t]="26";}if(k[t].equals("3")){k[t]="27";}
								if(k[t].equals("4")){k[t]="28";}if(k[t].equals("5")){k[t]="29";}
								if(k[t].equals("6")){k[t]="30";}
								if(k[t].equals(i+"")){
									if(i==24){i=0;}if(i==25){i=1;}if(i==26){i=2;}
									if(i==27){i=3;}if(i==28){i=4;}if(i==29){i=5;}if(i==30){i=6;}
									rs3=mvf.ObtenerCodigoHorasDispensacionMedicamento(CodDetalleFormulacionK, i+":00:00");
									if(rs3.next()){
										if(rs3.getString(10).equals("0")){
											out.print("<td width='4%' height='35' bgcolor='#DFDBB3'><div id='dlleno' draggable='true' ><input type='checkbox' name='ca' id='ca' onClick=Dispensar('"+rs3.getString(1)+"') /></div></td>");
										}
										if(rs3.getString(10).equals("1")){
											String Dis="Dispensado por:"+rs3.getString(7)+" el dia "+rs3.getString("fecha_administracion")+" a las "+rs3.getString("hora_administracion");
											out.print("<td width='4%' height='35' bgcolor='#33CC33' title='"+Dis+"' ></td>");
										}
										if(rs3.getString(10).equals("2")){
											String Dis="Cancelado por:"+rs3.getString(7)+" Debido a:"+rs3.getString(11);
											out.print("<td width='4%' height='35' bgcolor='#C94141' title='"+Dis+"' ></td>");
										}
									}
									rs3.getStatement().getConnection().close();
									
									sw=1;
								}
								if(i==0){i=24;}if(i==1){i=25;}if(i==2){i=26;}if(i==3){i=27;}
								if(i==4){i=28;}if(i==5){i=29;}if(i==6){i=30;}
							}
							if(sw==0){out.print("<td width='4%' height='35'  rowspan='4' ><div id='dvacio'>-</div></td>");}
						}
						out.print("</tr></table>");
						/******FIN DE ACOMODAS CHECK-BOX A LA HORA****************/
						out.print("</div></td></tr>");
					}rs.getStatement().getConnection().close();
					out.print("</div></td></tr></table>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*try {
				rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(fecha+"", CodAdmK);
				String CodFormK="";
				if(rs2.next()){
					CodFormK=rs2.getString(1);
					rs=mvf.DetalledeFormulacionK(CodFormK);
					out.print("<table width='100%' border='1'>");
					while(rs.next()){
						CodDetalleFormulacionK=rs.getString(4);
						out.print("<tr><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");
						
						out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
						
						out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
						
						if(rs.getString(7).equals("4")){
							out.print("<td  bgcolor='#3676BD' ><b><font color='white'>ACxxxxCIONES</font></b></td>");
						}
						
						if(rs.getString(7).equals("5")){
							out.print("<td  bgcolor='#009933' ><b><font color='white'>ACCIxxxxxONES</font></b></td>");
						}
						
						if(rs.getString(7).equals("6")){
							out.print("<td  bgcolor='#FF9900'  ><b><font color='white'>ACxxxxxCIONES</font></b></td>");
						}
						if(rs.getString(7).equals("7")){
							out.print("<td  bgcolor='#CC3333'  ><b><font color='white'>ACCIONxxxxxES</font></b></td>");
						}

						
						out.print("	</tr></table></td><td width='70%'><div id='DosisKardex'>");
						out.print("<table width='100%' border='1' style='font-size:smaller'>");
						//out.print("<tr><td width='4%' bgcolor='#999999'>01</td><td width='4%' bgcolor='#999999'>02</td><td width='4%' bgcolor='#999999'>03</td><td width='4%' bgcolor='#999999'>04</td><td width='4%' bgcolor='#999999'>05</td><td width='4%' bgcolor='#FFFF66'>06</td><td width='4%' bgcolor='#FFFF66'>07</td><td width='4%' bgcolor='#FFFF66'>08</td>");
						//out.print("<td width='4%' bgcolor='#FFFF66'>09</td><td width='4%' bgcolor='#FFFF66'>10</td><td width='4%' bgcolor='#FFFF66'>11</td><td width='4%' bgcolor='#FFFF66'>12</td><td width='4%' bgcolor='#FFFF66'>13</td><td width='4%' bgcolor='#FFFF66'>14</td><td width='4%' bgcolor='#FFFF66'>15</td>");
						//out.print("<td width='4%' bgcolor='#FFFF66'>16</td><td width='4%' bgcolor='#FFFF66'>17</td><td width='4%' bgcolor='#FFFF66'>18</td><td width='4%' bgcolor='#999999'>19</td><td width='4%' bgcolor='#999999'>20</td><td width='4%' bgcolor='#999999'>21</td><td width='4%' bgcolor='#999999'>22</td>");
						//out.print("<td width='4%' bgcolor='#999999'>23</td><td width='4%' bgcolor='#999999'>24</td></tr>");
						
						
						rs3=mvf.ObtenerHorasDispensacionMedicamento(CodDetalleFormulacionK);
						while(rs3.next()){
							 int horas_d=rs3.getInt(11);
							 out.print("horas_d="+horas_d+"_"+rs3.getInt(11));
						}
						rs3.getStatement().getConnection().close();
						/*****ACOMODAR CHECK-BOX A LA HORA********/
						/*out.print("<tr>");
						String FrecK="";
						String HoraFre="";
						String sep=rs.getString(6).replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1){FrecK=d[1];}
						rs3=mvf.ObtenerHorasDispensacion(FrecK);
						while(rs3.next()){HoraFre=HoraFre+""+rs3.getString(3)+"_"+"";}
						int ho=HoraFre.split("_").length;
						String[] k=HoraFre.split("_");
						for(int i=1; i<=24; i++){
							int sw=0;
							for(int t=0;t<ho;t++){
								if(k[t].equals(i+"")){
									out.print("<td width='4%' rowspan='2'><input name='radiobutton' type='radio' value='radiobutton' id='f' onclick='Alerta("+i+")' /></td>");
									sw=1;
								}
							}
							if(sw==0){out.print("<td>"+i+"</td>");}
						}
						rs3.getStatement().getConnection().close();
						out.print("</tr></table>");
						//*
						/******FIN DE ACOMODAS CHECK-BOX A LA HORA****************/
						
						
						//out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
						//out.print("<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
						
					//	out.print("</table>");
						//*
					/*	out.print("</div></td></tr>");
						///out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs.getString(5)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		
		String CodHoraDisp=req.getParameter("CodHoraDisp");
		String CodUsu=req.getParameter("CodUsu");
		String Observacion=req.getParameter("Observacion");
		if(va.equals("Di")){			
			try {
				rs=mvf.ObtenerNombreUsuarioK(CodUsu);
				if(rs.next()){
					mvf.ActualizarDetalleDosis(CodUsu, rs.getString(1), FechaAdministra+"", HoraAdministra+"", "1", CodHoraDisp,"-");
				}
				//buscar codigo del medicamento
				
				//buscar programa de facturacion del medicamento
				
				//buscar el encabezado activo de facturacion
				
				//insertar en la tabla detalle encabezado.
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("Cd")){			
			try {
				rs=mvf.ObtenerNombreUsuarioK(CodUsu);
				if(rs.next()){
					mvf.ActualizarDetalleDosis(CodUsu, rs.getString(1), FechaAdministra+"", HoraAdministra+"", "2", CodHoraDisp,Observacion);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("CDO")){
			String CodHoraDispCDO=req.getParameter("CodHoraDisp");
			String CmbHoraNueDis=req.getParameter("CmbHoraNueDis");
			try {				
				//se consulta si ya hay una dosis a esa hora.
				rs=mvf.ObtenerFormulacionMedicamento(CodHoraDispCDO);
				if(rs.next()){
					rs1=mvf.ObtenerHoraExisteFormulacionMedicamento(rs.getString("CodDetForm_FK"), CmbHoraNueDis+":00:00");
					if(rs1.next()){
						out.print("NO");
					}else{
						mvf.ActualizarHoraDetalleDosis(CodHoraDispCDO, CmbHoraNueDis+":00:00");
					}rs1.getStatement().getConnection().close();
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		if(va.equals("VerKardex")){
			//if(va.equals("8")){		
				
				try {
					java.util.Date fechaActual = new java.util.Date();
					java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());	
					
					///////////////////
					 Calendar c1 = GregorianCalendar.getInstance();
				        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
				        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        sdf = new SimpleDateFormat("yyyy-MM-dd");
				        c1.add(Calendar.DATE, 1);
					/////////////////
					String Pes="";
					String Tal="";
					//out.print("<table><tr><td></td></tr></table>");
					rs2=mvf.ObtenerPesoTalla(CodAdmK);
					if(rs2.next()){
						Pes=rs2.getString(3);
						Tal=rs2.getString(4);
						
						//out.print("<table><tr><td>Peso <input name='txtPeso' size='5' type='text' onkeyup='SoloNumeros(form1.txtPeso)' value='"+Pes+"' /> (En Kg. EJ:56)</td> " +
						//		"<td>Talla <input name='txtTalla' size='5' type='text' onkeyup='SoloNumeros(form1.txtTalla)' value='"+Tal+"'  /> (En Mts. EJ:1.68)</td><td></tr></table>");
					}else{
					//	out.print("<table><tr><td>Peso <input name='txtPeso' size='5' type='text' onkeyup='SoloNumeros(form1.txtPeso)' /> (En Kg. EJ:56)</td> " +
						//		"<td>Talla <input name='txtTalla' size='5' type='text' onkeyup='SoloNumeros(form1.txtTalla)'  /> (En Mts. EJ:1.68)</td><td><input name='btnCrear' type='button' class='boton4' id='btnCrear' value='Crear' onClick='CrearPT()'></td></tr></table>");
					}
					rs2.getStatement().getConnection().close();
					
					/////////////////////////////////////////////////////
					out.print("<table width='100%' border='1'>");
					out.print("<tr><input type='hidden' id='txtPerfil' value='0' ><td id='DetalleKardex'>");
					
					java.util.Calendar hoy = java.util.Calendar.getInstance();
					java.util.Date primerDia= hoy.getTime();
					int diasMes[]= new int[12];
					diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
					diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;

					int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
					String NombreMes = "";
					anio=hoy.get(java.util.Calendar.YEAR);
					dia=hoy.get(java.util.Calendar.DATE);
					mes=hoy.get(java.util.Calendar.MONTH);
					mes=mes+1;
					if(mes==1){NombreMes="Enero";}if(mes==2){NombreMes="Febrero";}if(mes==3){NombreMes="Marzo";}
					if(mes==4){NombreMes="Abril";}if(mes==5){NombreMes="Mayo";}if(mes==6){NombreMes="Junio";}
					if(mes==7){NombreMes="Julio";}if(mes==8){NombreMes="Agosto";}if(mes==9){NombreMes="Septiembre";}
					if(mes==10){NombreMes="Octubre";}if(mes==11){NombreMes="Noviembre";}if(mes==12){NombreMes="Diciembre";}

					if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) 
					{
						diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
					}
					nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
					primerDia.setDate(1);
					diaInicial=primerDia.getDay();
					
					out.print("<table width='100%' border >");
					out.print("<tr><th colspan=41 align=center>"+NombreMes+" Del "+anio+" "); 		
					out.print("<tr bgcolor='#CCCCCC'>");
					out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
					out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
					out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
					out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
					out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
					out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");
					out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
					out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
					out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
					out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
					out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
					out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
					out.print("<th align=center>L");
					out.print("</tr>");
					out.print("<tr></tr>");
					columna=0;
					for(i=0;i<diaInicial;i++) 
					{
						out.print("<td align=center><font size+=4>.");
						columna++;
						out.print("</font>");
					}
					int a=0;
					for(i=1;i<=nDias;i++) 
					{
						a=a+1;
						out.print("<td align=center>");				
						if(i==dia) 
						{
							String valueF=anio+"-"+mes+"-"+i;
							out.print("<b>"+i+"</b>");
							out.print("<font color=\"#ff0000\" size+=7><b>");
							out.print("<input name='radiobutto' type='radio' value='radiobutto' checked='true' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
							out.print("</b></font>"); 
						}else{
							String valueF=anio+"-"+mes+"-"+i;
							out.print(i);
							out.print("<input name='radiobutto' type='radio' value='radiobutto' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
						}
					}
					out.print("</table>");
					out.print("<table width='100%' border='0' ><tr><td width='4%' ><input type='hidden' id='txtFechaActuKard' value="+fecha+" ><input type='hidden' id='txtAdmisionCKR' value="+CodAdmK+" ></td></tr></table>");

					try {
						String FechaKardex=req.getParameter("FechaKardex");
						String CodAdmK1=req.getParameter("CodAdmK");
						
						rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(FechaKardex+"", CodAdmK1,"2");
						String CodFormK="";
						String CodDetalleFormulacionK="";
						if(rs2.next()){
							CodFormK=rs2.getString(1);
						
							out.print("<table width='100%'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+FechaKardex+"'> ");
							out.print("<table width='100%' border='1'><tr><td width='30%'><div id='DatosEmerg'>-</div></td>" +
									"<td width='70%'><table width='100%' border='1' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fecha+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
							"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
							"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
							"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
							"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
							"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
							"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +													
							"</tr></table></td></tr></table>");


							out.print("<table width='100%' border='1'>");
							rs=mvf.DetalledeFormulacionK(CodFormK);
							while(rs.next()){
								CodDetalleFormulacionK=rs.getString(4);
								if(rs.getString("estado").equals("1")){	
									//medicamento activo
									out.print("<tr title='Medicamento Formulado Por "+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
									out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
									out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
								
									//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
									//out.print("<td  bgcolor='#D8D8D8' onclick='SuspenderMK("+CodDetalleFormulacionK+")' ><b><font >SUSPENDER</font></b></td>");
								}
								if(rs.getString("estado").equals("2")){
									//medicamento suspendido
									out.print("<tr bgcolor='#D8D8D8' title='Medicamento Suspendido. Fue Formulado Por:"+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
									out.print("<tr><td colspan='2'>"+rs.getString(1)+"1</td><td width='34%'>"+rs.getString(2)+"2</td></tr>");
									out.print("<tr><td width='37%'>"+rs.getString(5)+"3</td><td width='29%'>"+rs.getString(6)+"4</td>");
								
									//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
									//out.print("<td  bgcolor='#C94141' onclick='EliminarMK("+CodDetalleFormulacionK+")' ><b><font color='white'>ELIMINAR</font></b></td>");
								}
								//if(rs.getString(7).equals("0")){out.print("<td  bgcolor='#FF9900' ><b><font color='white'>ACCIONES</font></b></td>");}
								//if(rs.getString(7).equals("1")){out.print("<td  bgcolor='#CC3333' ><b><font color='white'>ACCIONES</font></b></td>");}
								out.print("	</tr></table></td><td width='70%'><div id='DosisKardex'>");
								//out.print("valor=8");
								out.print("<table width='100%' border='1' id='columns' style='font-size:smaller'>");
								//out.print("<tr><td width='4%' bgcolor='#999999'>01</td><td width='4%' bgcolor='#999999'>02</td><td width='4%' bgcolor='#999999'>03</td><td width='4%' bgcolor='#999999'>04</td><td width='4%' bgcolor='#999999'>05</td><td width='4%' bgcolor='#FFFF66'>06</td><td width='4%' bgcolor='#FFFF66'>07</td><td width='4%' bgcolor='#FFFF66'>08</td>");
								//out.print("<td width='4%' bgcolor='#FFFF66'>09</td><td width='4%' bgcolor='#FFFF66'>10</td><td width='4%' bgcolor='#FFFF66'>11</td><td width='4%' bgcolor='#FFFF66'>12</td><td width='4%' bgcolor='#FFFF66'>13</td><td width='4%' bgcolor='#FFFF66'>14</td><td width='4%' bgcolor='#FFFF66'>15</td>");
								//out.print("<td width='4%' bgcolor='#FFFF66'>16</td><td width='4%' bgcolor='#FFFF66'>17</td><td width='4%' bgcolor='#FFFF66'>18</td><td width='4%' bgcolor='#999999'>19</td><td width='4%' bgcolor='#999999'>20</td><td width='4%' bgcolor='#999999'>21</td><td width='4%' bgcolor='#999999'>22</td>");
								//out.print("<td width='4%' bgcolor='#999999'>23</td><td width='4%' bgcolor='#999999'>24</td></tr>");
								/*out.print("<table width='100%' border='1' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fecha+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
										"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
										"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
										"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
										"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
										"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
										"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +													
										"</tr></table>");*/
								out.print("");
								String FrecK="";
								String HoraFre="";
								String sep=rs.getString(6).replace(" ", "_");					
								int h=sep.split("_").length;
								String[] d=sep.split("_");		     	
								for(int g=0; g<h-1; g=g+1){FrecK=d[1];}
								String CodAdministracion="";
								rs3=mvf.ObtenerHorasDispensacionMedicamento(CodDetalleFormulacionK);
								while(rs3.next()){
									HoraFre=HoraFre+""+rs3.getInt(12)+"_"+"";
									CodAdministracion=CodAdministracion+""+rs3.getString(1)+"_"+"";
									// int horas_d=rs3.getInt(11);
									// out.print("horas_d INGRESO="+horas_d+"");
								}
								rs3.getStatement().getConnection().close();
								//rs3=mvf.ObtenerHorasDispensacion(FrecK);
								//while(rs3.next()){HoraFre=HoraFre+""+rs3.getInt(3)+"_"+"";}
								
								int ho=HoraFre.split("_").length;
								String[] k=HoraFre.split("_");
								for( i=7; i<=30; i++){
									
									int sw=0;
									for(int t=0;t<ho;t++){
										if(k[t].equals("0")){k[t]="24";}if(k[t].equals("1")){k[t]="25";}
										if(k[t].equals("2")){k[t]="26";}if(k[t].equals("3")){k[t]="27";}
										if(k[t].equals("4")){k[t]="28";}if(k[t].equals("5")){k[t]="29";}
										if(k[t].equals("6")){k[t]="30";}
										if(k[t].equals(i+"")){
											if(i==24){i=0;}if(i==25){i=1;}if(i==26){i=2;}
											if(i==27){i=3;}if(i==28){i=4;}if(i==29){i=5;}if(i==30){i=6;}
											//out.print("<td>Horas Dosis</td><td>"+i+":00:00</td>");
											rs3=mvf.ObtenerCodigoHorasDispensacionMedicamento(CodDetalleFormulacionK, i+":00:00");
											if(rs3.next()){
												if(rs3.getString(10).equals("0")){
													//System.out.print("<td width='4%' height='35' bgcolor='#DFDBB3' onClick='CambiarHora('"+rs3.getString(1)+"',"+i+")' ><div id='dlleno' onClick='CambiarHora('"+rs3.getString(1)+"',"+i+")'  ><input type='checkbox' name='ca' id='ca' onClick=Dispensar('"+rs3.getString(1)+"') />"+i+"</div></td>");
													out.print("<td width='4%' height='35' bgcolor='#DFDBB3' ondblclick='fr("+rs3.getString(1)+","+i+")'  ><div id='dlleno'><input type='checkbox' name='ca' id='ca"+rs3.getString(1)+"' onClick=Dispensar('"+rs3.getString(1)+"') />"+i+"</div></td>");
												}
												if(rs3.getString(10).equals("1")){
													String Dis="Dispensado por:"+rs3.getString(7)+" el dia "+rs3.getString("fecha_administracion")+" a las "+rs3.getString("hora_administracion");
													out.print("<td width='4%' height='35' bgcolor='#33CC33' title='"+Dis+"' ></td>");
												}
												if(rs3.getString(10).equals("2")){
												String Dis="Cancelado por:"+rs3.getString(7)+" Debido a:"+rs3.getString(11);
													out.print("<td width='4%' height='35' bgcolor='#C94141' title='"+Dis+"' </td>");
												}
											}
											rs3.getStatement().getConnection().close();
											
											sw=1;
										}
										if(i==0){i=24;}if(i==1){i=25;}if(i==2){i=26;}if(i==3){i=27;}
										if(i==4){i=28;}if(i==5){i=29;}if(i==6){i=30;}
									}
									if(sw==0){
										out.print("<td width='4%' height='35'  rowspan='4'  ><div id='dvacio'>-</div></td>");
										}
									}
								//}
								//rs3.getStatement().getConnection().close();
								out.print("</tr></table>");
								
								out.print("</div></td></tr>");
							}rs.getStatement().getConnection().close();
							out.print("</table>");
							out.print("</div></td></tr></table>");
						}else{
							out.print("<div id='CarguesDiaKardex'></div>");
							out.print("<div id='DosisKardex'></div>");
							
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*
				try {
					rs=mvf.ObtenerFormulaPendUsu(codAdm, Codusuario);
					if(rs.next()){
						out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:large; color:#FF0000 ' >Tiene Una Formula Sin Guardar.Debe Guardarla Antes de Formular Otra.</div></td></tr></table>");
						out.print("<table width='100%' border='1'>");					
						out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Detalle Orden</span></div></td></tr>");
						out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' readonly='' >"+rs.getString(2)+"</textarea></td></tr>");
						out.print("</table>");					
						codFormulacion_fk=rs.getString(1);
						out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
						out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='21%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td></tr>");
						
						rs2=mvf.DetalledeFormulacion(codFormulacion_fk);
						while(rs2.next()){
							out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td></tr>");
						}
						rs2.getStatement().getConnection().close();
						out.print("</table>");
						out.print("</div>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
						out.print("</div>");
						
						rs.getStatement().getConnection().close();
					}
					else{
						String Pes="";
						String Tal="";
						rs2=mvf.ObtenerPesoTalla(codAdm);
						if(rs2.next()){
							Pes=rs2.getString(3);
							Tal=rs2.getString(4);
						}
						rs2.getStatement().getConnection().close();
						
						out.print("<table width='100%' border='1'>");					
						out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Formulacion Medicamentos e Insumos</span></div><input name='txtTipo' type='hidden' id='txtTipo' value='0' /></td></tr>");
						out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' onkeyup='ActualizaDetalleOrden()' ></textarea> " +
								"Peso <input name='txtPeso' size='5' type='text' onkeyup='SoloNumeros(form1.txtPeso)' value='"+Pes+"' /> (En Kg. EJ:56)   " +
								"Talla <input name='txtTalla' size='5' type='text' onkeyup='SoloNumeros(form1.txtTalla)' value='"+Tal+"'  /> (En Mts. EJ:1.68) " +
								"</td></tr>");
						out.print("<tr><td width='11%'>Medicamento</td><td width='89%'><input name='txtMedicamento' type='text' id='txtMedicamento' size='70' onKeyUp='autocompletarMedicamentoFormula()' /></td></tr>");
						out.print("<tr><td><input name='txtCodigoMed' type='hidden' id='txtCodigoMed' size='18' /></td><td><div id='SugerenciaMedFormula'></div></td></tr>");
						out.print("</table>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<div id='ConteForm'>");
						out.print("<table width='100%' >");
						out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
						out.print("</table>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<div id='DetFormula'>");
						out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
						out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion'  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
						out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
						out.print("</table>");
						out.print("</div>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						//out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar'></div></td></tr></table>");
						out.print("</div>");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				*/
			
		}
		
		if (va.equals("VerKardex-an")){
			out.print("<table width='100%' border='1'>");
			out.print("<tr><td id='DetalleKardex'>");
			
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			java.util.Date primerDia= hoy.getTime();
			int diasMes[]= new int[12];
			diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
			diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;

			int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
			String NombreMes = "";
			anio=hoy.get(java.util.Calendar.YEAR);
			dia=hoy.get(java.util.Calendar.DATE);
			mes=hoy.get(java.util.Calendar.MONTH);
			mes=mes+1;
			if(mes==1){NombreMes="Enero";}if(mes==2){NombreMes="Febrero";}if(mes==3){NombreMes="Marzo";}
			if(mes==4){NombreMes="Abril";}if(mes==5){NombreMes="Mayo";}if(mes==6){NombreMes="Junio";}
			if(mes==7){NombreMes="Julio";}if(mes==8){NombreMes="Agosto";}if(mes==9){NombreMes="Septiembre";}
			if(mes==10){NombreMes="Octubre";}if(mes==11){NombreMes="Noviembre";}if(mes==12){NombreMes="Diciembre";}

			if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) 
			{
				diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
			}
			nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
			primerDia.setDate(1);
			diaInicial=primerDia.getDay();
			
			out.print("<table width='100%' border >");
			out.print("<tr><th colspan=41 align=center>"+NombreMes+" Del "+anio+" "); 		
			out.print("<tr bgcolor='#CCCCCC'>");
			out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
			out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
			out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
			out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
			out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
			out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");
			out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
			out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
			out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
			out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
			out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
			out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
			out.print("<th align=center>L");
			out.print("</tr>");
			out.print("<tr></tr>");
			columna=0;
			for(i=0;i<diaInicial;i++) 
			{
				out.print("<td align=center><font size+=4>.");
				columna++;
				out.print("</font>");
			}
			int a=0;
			for(i=1;i<=nDias;i++) 
			{
				a=a+1;
				out.print("<td align=center>");				
				if(i==dia) 
				{
					String valueF=anio+"-"+mes+"-"+i;
					out.print("<b>"+i+"</b>");
					out.print("<font color=\"#ff0000\" size+=7><b>");
					out.print("<input name='radiobutto' type='radio' value='radiobutto' checked='true' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
					out.print("</b></font>"); 
				}else{
					String valueF=anio+"-"+mes+"-"+i;
					out.print(i);
					out.print("<input name='radiobutto' type='radio' value='radiobutto' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
				}
			}
			out.print("</table>");
			out.print("<table width='100%' ><tr><td width='30%' ></td><td width='70%' ><table width='100%' border='1' style='font-size:smaller'><tr><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td>");
			out.print("<td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>15</td>");
			out.print("<td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#FFFF66'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>22</td>");
			out.print("<td width='4%' bgcolor='#999999'>-</td><td width='4%' bgcolor='#999999'>-</td></tr></table></td></tr></table>");
			
			/*
			 * 
			 */
			try {
				String FechaKardex=req.getParameter("FechaKardex");
				String CodAdmK1=req.getParameter("CodAdmK");
				rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(FechaKardex+"", CodAdmK1, "2");
				String CodFormK="";
				String CodDetalleFormulacionK="";
				if(rs2.next()){
					CodFormK=rs2.getString(1);
					rs=mvf.DetalledeFormulacionK(CodFormK);
					out.print("<table width='100%' border='1'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+FechaKardex+"' >");
					while(rs.next()){
						CodDetalleFormulacionK=rs.getString(4);
						out.print("<tr><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
						out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
						out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
						
						//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
						//out.print("<td  bgcolor='#C94141' onclick='EliminarMK("+CodDetalleFormulacionK+")' ><b><font color='white'>ELIMINAR</font></b></td>");
						
						//if(rs.getString(7).equals("0")){out.print("<td  bgcolor='#FF9900' ><b><font color='white'>ACCIONES</font></b></td>");}
						//if(rs.getString(7).equals("1")){out.print("<td  bgcolor='#CC3333' ><b><font color='white'>ACCIONES</font></b></td>");}

						
						out.print("	</tr></table></td><td width='70%'><div id='DosisKardex'>");
						/**********************/
						//out.print("valor=VERKARDEX");
						out.print("<table width='100%' border='1' id='columns' style='font-size:smaller'>");
						//out.print("<tr><td width='4%' bgcolor='#999999'>01</td><td width='4%' bgcolor='#999999'>02</td><td width='4%' bgcolor='#999999'>03</td><td width='4%' bgcolor='#999999'>04</td><td width='4%' bgcolor='#999999'>05</td><td width='4%' bgcolor='#FFFF66'>06</td><td width='4%' bgcolor='#FFFF66'>07</td><td width='4%' bgcolor='#FFFF66'>08</td>");
						//out.print("<td width='4%' bgcolor='#FFFF66'>09</td><td width='4%' bgcolor='#FFFF66'>10</td><td width='4%' bgcolor='#FFFF66'>11</td><td width='4%' bgcolor='#FFFF66'>12</td><td width='4%' bgcolor='#FFFF66'>13</td><td width='4%' bgcolor='#FFFF66'>14</td><td width='4%' bgcolor='#FFFF66'>15</td>");
						//out.print("<td width='4%' bgcolor='#FFFF66'>16</td><td width='4%' bgcolor='#FFFF66'>17</td><td width='4%' bgcolor='#FFFF66'>18</td><td width='4%' bgcolor='#999999'>19</td><td width='4%' bgcolor='#999999'>20</td><td width='4%' bgcolor='#999999'>21</td><td width='4%' bgcolor='#999999'>22</td>");
						//out.print("<td width='4%' bgcolor='#999999'>23</td><td width='4%' bgcolor='#999999'>24</td></tr>");
						/*****ACOMODAR CHECK-BOX A LA HORA********/
						out.print("");
						String FrecK="";
						String HoraFre="";
						String sep=rs.getString(6).replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1){FrecK=d[1];}
						String CodAdministracion="";
						rs3=mvf.ObtenerHorasDispensacionMedicamento(CodDetalleFormulacionK);
						while(rs3.next()){
							HoraFre=HoraFre+""+rs3.getInt(12)+"_"+"";
							CodAdministracion=CodAdministracion+""+rs3.getString(1)+"_"+"";
							// int horas_d=rs3.getInt(11);
							// out.print("horas_d INGRESO="+horas_d+"");
						}
						rs3.getStatement().getConnection().close();
						//rs3=mvf.ObtenerHorasDispensacion(FrecK);
						//while(rs3.next()){HoraFre=HoraFre+""+rs3.getInt(3)+"_"+"";}
						int ho=HoraFre.split("_").length;
						String[] k=HoraFre.split("_");
						for( i=7; i<=30; i++){
							int sw=0;
							for(int t=0;t<ho;t++){
								if(k[t].equals("0")){k[t]="24";}if(k[t].equals("1")){k[t]="25";}
								if(k[t].equals("2")){k[t]="26";}if(k[t].equals("3")){k[t]="27";}
								if(k[t].equals("4")){k[t]="28";}if(k[t].equals("5")){k[t]="29";}
								if(k[t].equals("6")){k[t]="30";}
								if(k[t].equals(i+"")){
									if(i==24){i=0;}if(i==25){i=1;}if(i==26){i=2;}
									if(i==27){i=3;}if(i==28){i=4;}if(i==29){i=5;}if(i==30){i=6;}
									rs3=mvf.ObtenerCodigoHorasDispensacionMedicamento(CodDetalleFormulacionK, i+":00:00");
									if(rs3.next()){
										if(rs3.getString(10).equals("0")){
											out.print("<td width='4%' height='35' bgcolor='#DFDBB3'><div id='dlleno' draggable='true' ><input type='checkbox' name='ca' id='ca' onClick=Dispensar('"+rs3.getString(1)+"') /></div></td>");
										}
										if(rs3.getString(10).equals("1")){
											String Dis="Dispensado por:"+rs3.getString(7)+" el dia "+rs3.getString("fecha_administracion")+" a las "+rs3.getString("hora_administracion");
											out.print("<td width='4%' height='35' bgcolor='#33CC33' title='"+Dis+"' ></td>");
										}
										if(rs3.getString(10).equals("2")){
											String Dis="Cancelado por:"+rs3.getString(7)+" Debido a:"+rs3.getString(11);
											out.print("<td width='4%' height='35' bgcolor='#C94141'  title='"+Dis+"' ></td>");
										}
									}
									rs3.getStatement().getConnection().close();
									
									sw=1;
								}
								if(i==0){i=24;}if(i==1){i=25;}if(i==2){i=26;}if(i==3){i=27;}
								if(i==4){i=28;}if(i==5){i=29;}if(i==6){i=30;}
							}
							if(sw==0){out.print("<td width='4%' height='35'  rowspan='4' ><div id='dvacio'>"+i+"</div></td>");}
						}
						//rs3.getStatement().getConnection().close();
						out.print("</tr></table>");
					}rs.getStatement().getConnection().close();
					out.print("</div></td></tr></table>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/******************************************************************************************************/
		/******************************************************************************************************/
		/******************************************************************************************************/
		if(va.equals("GDE")){
			String tPeso=req.getParameter("tPeso");
			String tTalla=req.getParameter("tTalla");
			String tTA=req.getParameter("tTA");
			String tFC=req.getParameter("tFC");
			String tFR=req.getParameter("tFR");
			String tPulso=req.getParameter("tPulso");
			
			String CodAdmDE=req.getParameter("CodAdmDE");
			String HoraDE=req.getParameter("HoraDE");
			String CodUsuDE=req.getParameter("CodUsuDE");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora = new java.sql.Time(fechaActual.getTime());
			mvf.CrearPesoTallaCompleto(CodAdmDE, tPeso, tTalla, tTA, tFC, 
					tFR, tPulso, fecha+"", HoraDE, fecha+"", hora+"", CodUsuDE);
		}
		if(va.equals("MODE")){
			String CodAdmDE=req.getParameter("CodAdmDE");
			String HoraDE=req.getParameter("HoraDE");
			String FechaK=req.getParameter("FechaK");
			
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			//java.sql.Time hora = new java.sql.Time(fechaActual.getTime());
			try {
				rs=mvf.ObtenerPesoTallaTodosDatos(CodAdmDE, FechaK+"", HoraDE);
				if(rs.next()){
					out.print("<table border='1' width='30%'>" +
							"<tr><td colspan='2' >Peso:<input type='text' id='txtPeso' size='3' value='"+rs.getString("peso")+"' >(En Kg.)</td>" +
							"<td colspan='2'>Peso:<input type='text' id='txtTalla' size='3' value='"+rs.getString("talla")+"' >(En Mts.)</td>" +
							"</tr><tr><td>T.A:<input type='text' id='txtTA' size='3' value='"+rs.getString("ta")+"' ></td>" +
							"<td>F.C:<input type='text' id='txtFC' size='3' value='"+rs.getString("fc")+"' ></td>" +
							"<td>F.R:<input type='text' id='txtFR' size='3' value='"+rs.getString("fr")+"' ></td>" +
							"<td>Pulso:<input type='text' id='txtPulso' size='3' value='"+rs.getString("pulso")+"' >" +
							"</td>" +
					"</tr></table>");
				}else{
					String Pes="";
					String Tal="";
					rs2=mvf.ObtenerPesoTalla(CodAdmDE);
					if(rs2.next()){
						Pes=rs2.getString(3);
						Tal=rs2.getString(4);
					}
					rs2.getStatement().getConnection().close();
					System.out.println("fecha="+fecha+" FechaK="+FechaK);
					if(fecha.toString().equals(FechaK)){
						System.out.println("Entra en fechas iguales");
						out.print("<table border='1' width='30%'>" +
								"<tr><td colspan='2' >Peso:<input type='text' id='txtPeso' readonly='' size='4' value='"+Pes+"' >(En Kg.)</td>" +
								"<td colspan='2'>Peso:<input type='text' id='txtTalla' readonly='' size='4' value='"+Tal+"'  >(En Mts.)</td>" +
								"</tr><tr><td>T.A:<input type='text' id='txtTA' size='4' ></td>" +
								"<td>F.C:<input type='text' id='txtFC' size='4' ></td>" +
								"<td>F.R:<input type='text' id='txtFR' size='4' ></td>" +
								"<td>Pulso:<input type='text' id='txtPulso' size='4' >" +
								"<input name='btnFinalizar' type='button' id='btnFinalizar' value='+' onclick='GuardarDatosEmerg("+HoraDE+",1)' ></td>" +
						"</tr></table>");
						}
						else{
							System.out.println("Entra en fechas desiguales");
							out.print("<table border='1' width='30%'>" +
									"<tr><td>No se Registraron Signos</td>" +
									"</tr></table>");

						}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			if(va.equals("-8-")){
		
			try {
				java.util.Date fechaActual = new java.util.Date();
				java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());	
				///////////////////
				 Calendar c1 = GregorianCalendar.getInstance();
			      //  System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        sdf = new SimpleDateFormat("yyyy-MM-dd");
			        c1.add(Calendar.DATE, 1);
				String Pes="";
				String Tal="";
  
				
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'><tr><td colspan='7'><div id='cabecera2' class='style11' align='center'><input name='hidden' type='hidden' id='txtPerfil' value='1'>FORMULACION DE MEDICAMENTOS </div></td></tr>");

				out.print("<tr><td width='8%'><span class='style12'>Medicamento</span></td>" +
						"<td colspan='3'><input name='txtMedicamento' type='text' id='txtMedicamento' size='50' onkeyup='AutoMedicamentoCardex()' /></td>" +
						"<td width='51%' colspan='3' rowspan='5' id='InfusionY'>infusion y/o medicamento normal</td>" +
						"</tr>");
				  
				  
				out.print("<tr><td>&nbsp;</td><td colspan='3'><div id='MedCar'>MedCar</div></td></tr>");

				out.print("<tr><td><span class='style12'>F.Farm</span></td>" +
						"<td width='18%'><div id='FF'><select name='cmbFFarmaceutica' id='cmbFFarmaceutica'><option value='Seleccione'>Seleccione</option></select></div></td>");
				  
				out.print("<td width='4%'><span class='style12'>Conc</span></td>" +
						"<td width='19%'><div id='CCN'><select name='cmbConcentracion' id='cmbConcentracion'><option value='Seleccione'>Seleccione</option></select></div></td>" +
						"</tr>");
					
					
				out.print("<tr><td><span class='style12'>Unidad </span></td>" +
						"<td><div id='UNI'><select name='cmbUnidad' id='cmbUnidad'><option value='Seleccione'>Seleccione</option></select></div></td>" +
						"<td><span class='style12'>Lapso</span></td>" +
						"<td><span class='style12'><select name='cmbLapsoMed' id='cmbLapsoMed' onchange='CalcularDosis()'><option value='Seleccione'>Seleccione</option>");
				
				rs=msa.ObtenerLapsosMed();			
				while(rs.next()){
				out.print("<option value='"+rs.getString(2)+"'>"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				
				out.print("</select></span></td></tr>");
				  
				out.print("<tr><td>&nbsp;</td><td colspan='3' id='CargaInfu'>CargaInfu</td></tr></table>");
				
				/////////////////////////////////////////////////////
				out.print("<table width='100%' border='1'>");
				out.print("<tr><td id='DetalleKardex'>");
				
				java.util.Calendar hoy = java.util.Calendar.getInstance();
				java.util.Date primerDia= hoy.getTime();
				int diasMes[]= new int[12];
				diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
				diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;

				int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
				String NombreMes = "";
				anio=hoy.get(java.util.Calendar.YEAR);
				dia=hoy.get(java.util.Calendar.DATE);
				mes=hoy.get(java.util.Calendar.MONTH);
				mes=mes+1;
				if(mes==1){NombreMes="Enero";}if(mes==2){NombreMes="Febrero";}if(mes==3){NombreMes="Marzo";}
				if(mes==4){NombreMes="Abril";}if(mes==5){NombreMes="Mayo";}if(mes==6){NombreMes="Junio";}
				if(mes==7){NombreMes="Julio";}if(mes==8){NombreMes="Agosto";}if(mes==9){NombreMes="Septiembre";}
				if(mes==10){NombreMes="Octubre";}if(mes==11){NombreMes="Noviembre";}if(mes==12){NombreMes="Diciembre";}

				if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) 
				{
					diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
				}
				nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
				primerDia.setDate(1);
				diaInicial=primerDia.getDay();
				String FAK=anio+"-"+mes+"-"+dia;
				out.print("<table width='100%' border >");
				out.print("<tr><th colspan=41 align=center> "+NombreMes+" Del "+anio+" "); 		
				out.print("<tr bgcolor='#CCCCCC'>");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
				out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
				out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
				out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
				out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
				out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");
				out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
				out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
				out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
				out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
				out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
				out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
				out.print("<th align=center>L");
				out.print("</tr>");
				out.print("<tr></tr>");
				columna=0;
				for(i=0;i<diaInicial;i++) 
				{
					out.print("<td align=center><font size+=4>.");
					columna++;
					out.print("</font>");
				}
				int a=0;
				for(i=1;i<=nDias;i++) 
				{
					a=a+1;
					out.print("<td align=center>");				
					if(i==dia) 
					{
						String valueF=anio+"-"+mes+"-"+i;
						out.print("<b>"+i+"</b>");
						out.print("<font color=\"#ff0000\" size+=7><b>");
						out.print("<input name='radiobutto' type='radio' value='radiobutto' checked='true' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+codAdm+")' />");
						out.print("</b></font>"); 
					}else{
						String valueF=anio+"-"+mes+"-"+i;
						out.print(i);
						out.print("<input name='radiobutto' type='radio' value='radiobutto' id='f' onclick='RadioForU(&quot;"+valueF+"&quot;,"+codAdm+")' />");
					}
				}
				out.print("</table>");
				//out.print("<table width='100%' ><tr><td width='30%' ></td><td width='70%' ><table width='100%' border='1' style='font-size:smaller'><tr><td width='4%' bgcolor='#FFFF66'>07</td><td width='4%' bgcolor='#FFFF66'>08</td>");
				//out.print("<td width='4%' bgcolor='#FFFF66'>09</td><td width='4%' bgcolor='#FFFF66'>10</td><td width='4%' bgcolor='#FFFF66'>11</td><td width='4%' bgcolor='#FFFF66'>12</td><td width='4%' bgcolor='#FFFF66'>13</td><td width='4%' bgcolor='#FFFF66'>14</td><td width='4%' bgcolor='#FFFF66'>15</td>");
				//out.print("<td width='4%' bgcolor='#FFFF66'>16</td><td width='4%' bgcolor='#FFFF66'>17</td><td width='4%' bgcolor='#FFFF66'>18</td><td width='4%' bgcolor='#999999'>19</td><td width='4%' bgcolor='#999999'>20</td><td width='4%' bgcolor='#999999'>21</td><td width='4%' bgcolor='#999999'>22</td>");
				//out.print("<td width='4%' bgcolor='#999999'>23</td><td width='4%' bgcolor='#999999'>24</td><td width='4%' bgcolor='#999999'>01</td><td width='4%' bgcolor='#999999'>02</td><td width='4%' bgcolor='#999999'>03</td><td width='4%' bgcolor='#999999'>04</td><td width='4%' bgcolor='#999999'>05</td><td width='4%' bgcolor='#FFFF66'>06</td></tr></table></td></tr></table>");
				
				
				try {
					rs2=mvf.ObtenerCodigoFormulacionKardesSeleccionado(fecha+"", codAdm,"2");
					String CodFormK="";
					String CodDetalleFormulacionK="";
					if(rs2.next()){
						CodFormK=rs2.getString(1);
						
						out.print("<table width='100%'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+fecha+"' >");
						out.print("<table width='100%' border='1'><tr><td width='30%'><div id='DatosEmerg'>-</div></td>");
						out.print("<td width='70%'><table width='100%' border='1' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fecha+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
							"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
							"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
							"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
							"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
							"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
							"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +													
							"</tr></table></td></tr></table>");
						out.print("<table width='100%' border='1'>");
						rs=mvf.DetalledeFormulacionK(CodFormK);
						while(rs.next()){
							CodDetalleFormulacionK=rs.getString(4);
							if(rs.getString("estado").equals("1")){	
								//medicamento activo
								out.print("<tr title='Medicamento Formulado Por "+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
								out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td><td  bgcolor='#C94141' onclick='EliminarMK("+CodDetalleFormulacionK+")' ><b><font color='white'>ELIMINAR</font></b></td></tr>");
								out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
							
								//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
								out.print("<td  bgcolor='#D8D8D8' onclick='SuspenderMK("+CodDetalleFormulacionK+")' ><b><font >SUSPENDER</font></b></td>");
							}
							if(rs.getString("estado").equals("2")){
								//medicamento suspendido
								out.print("<tr bgcolor='#D8D8D8' title='Medicamento Suspendido. Fue Formulado Por:"+rs.getString("Medico")+"'><td width='30%'><table width='100%' border='1' style='font-size:smaller' >");						
								out.print("<tr><td colspan='2'>"+rs.getString(1)+"</td><td width='34%'>"+rs.getString(2)+"</td></tr>");
								out.print("<tr><td width='37%'>"+rs.getString(5)+"</td><td width='29%'>"+rs.getString(6)+"</td>");
							
								//out.print("<td  bgcolor='#3676BD' onclick='Reformular("+CodDetalleFormulacionK+")' ><b><font color='white'>REFORMULAR</font></b></td>");						
								//out.print("<td  bgcolor='#C94141' onclick='EliminarMK("+CodDetalleFormulacionK+")' ><b><font color='white'>ELIMINAR</font></b></td>");
							}
							
							out.print("	</tr></table></td><td width='70%'><div id='DosisKardex'>");
							//out.print("valor=8");
							out.print("<table width='100%' border='1' id='columns' style='font-size:smaller'>");
							//out.print("<tr><td width='4%' bgcolor='#999999'>01</td><td width='4%' bgcolor='#999999'>02</td><td width='4%' bgcolor='#999999'>03</td><td width='4%' bgcolor='#999999'>04</td><td width='4%' bgcolor='#999999'>05</td><td width='4%' bgcolor='#FFFF66'>06</td><td width='4%' bgcolor='#FFFF66'>07</td><td width='4%' bgcolor='#FFFF66'>08</td>");
							//out.print("<td width='4%' bgcolor='#FFFF66'>09</td><td width='4%' bgcolor='#FFFF66'>10</td><td width='4%' bgcolor='#FFFF66'>11</td><td width='4%' bgcolor='#FFFF66'>12</td><td width='4%' bgcolor='#FFFF66'>13</td><td width='4%' bgcolor='#FFFF66'>14</td><td width='4%' bgcolor='#FFFF66'>15</td>");
							//out.print("<td width='4%' bgcolor='#FFFF66'>16</td><td width='4%' bgcolor='#FFFF66'>17</td><td width='4%' bgcolor='#FFFF66'>18</td><td width='4%' bgcolor='#999999'>19</td><td width='4%' bgcolor='#999999'>20</td><td width='4%' bgcolor='#999999'>21</td><td width='4%' bgcolor='#999999'>22</td>");
							//out.print("<td width='4%' bgcolor='#999999'>23</td><td width='4%' bgcolor='#999999'>24</td></tr>");
							out.print("");
							String FrecK="";
							String HoraFre="";
							String sep=rs.getString(6).replace(" ", "_");					
							int h=sep.split("_").length;
							String[] d=sep.split("_");		     	
							for(int g=0; g<h-1; g=g+1){FrecK=d[1];}
							String CodAdministracion="";
							rs3=mvf.ObtenerHorasDispensacionMedicamento(CodDetalleFormulacionK);
							while(rs3.next()){
								HoraFre=HoraFre+""+rs3.getInt(12)+"_"+"";
								CodAdministracion=CodAdministracion+""+rs3.getString(1)+"_"+"";
								// int horas_d=rs3.getInt(11);
								// out.print("horas_d INGRESO="+horas_d+"");
							}
							rs3.getStatement().getConnection().close();
							//rs3=mvf.ObtenerHorasDispensacion(FrecK);
							//while(rs3.next()){HoraFre=HoraFre+""+rs3.getInt(3)+"_"+"";}
							int ho=HoraFre.split("_").length;
							String[] k=HoraFre.split("_");
							for( i=7; i<=30; i++){
								int sw=0;
								for(int t=0;t<ho;t++){
									if(k[t].equals("0")){k[t]="24";}if(k[t].equals("1")){k[t]="25";}
									if(k[t].equals("2")){k[t]="26";}if(k[t].equals("3")){k[t]="27";}
									if(k[t].equals("4")){k[t]="28";}if(k[t].equals("5")){k[t]="29";}
									if(k[t].equals("6")){k[t]="30";}
									
									if(k[t].equals(i+"")){
										if(i==24){i=0;}if(i==25){i=1;}if(i==26){i=2;}
										if(i==27){i=3;}if(i==28){i=4;}if(i==29){i=5;}if(i==30){i=6;}
										
										//out.print("<td>Horas Dosis</td><td>"+i+":00:00</td>");
										rs3=mvf.ObtenerCodigoHorasDispensacionMedicamento(CodDetalleFormulacionK, i+":00:00");
										if(rs3.next()){
											if(rs3.getString(10).equals("0")){
												//out.print("<td width='4%' height='35' bgcolor='#DFDBB3'><div id='dlleno' draggable='true' ><input type='checkbox' name='ca' id='ca' onClick=Dispensar('"+rs3.getString(1)+"') /></div></td>");
												out.print("<td width='4%' height='35' bgcolor='#DFDBB3'><div id='dlleno' draggable='true' >"+i+"</div></td>");
											}
											if(rs3.getString(10).equals("1")){
												String Dis="Dispensado por:"+rs3.getString(7)+" el dia "+rs3.getString("fecha_administracion")+" a las "+rs3.getString("hora_administracion");
												out.print("<td width='4%' height='35' bgcolor='#33CC33' title='"+Dis+"' ></td>");
											}
											if(rs3.getString(10).equals("2")){
											String Dis="Cancelado por:"+rs3.getString(7)+" Debido a:"+rs3.getString(11);
												out.print("<td width='4%' height='35' bgcolor='#C94141' title='"+Dis+"' ></td>");
											}
										}
										rs3.getStatement().getConnection().close();
										
										sw=1;
										if(i==0){i=24;}if(i==1){i=25;}if(i==2){i=26;}if(i==3){i=27;}
										if(i==4){i=28;}if(i==5){i=29;}if(i==6){i=30;}
									}
								}
								if(sw==0){
									out.print("<td width='4%' height='35'  rowspan='4' ><div id='dvacio'>-</div></td>");
									}
								}
							//}
							//rs3.getStatement().getConnection().close();
							out.print("</tr></table>");
							
							out.print("</div></td></tr>");
						}rs.getStatement().getConnection().close();
						out.print("</table>");
						out.print("</div></td></tr></table>");
					}else{
						out.print("<div id='CarguesDiaKardex'></div>");
						out.print("<div id='DosisKardex'></div>");
						
					}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("8")){
			
			try {
				rs=mvf.ObtenerFormulaPendUsu(codAdm, Codusuario);
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:large; color:#FF0000 ' >Tiene Una Formula Sin Guardar.Debe Guardarla Antes de Formular Otra.</div></td></tr></table>");
					out.print("<table width='100%' border='1'>");					
					out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Detalle Orden</span></div></td></tr>");
					out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' readonly='' >"+rs.getString(2)+"</textarea></td></tr>");
					out.print("</table>");					
					codFormulacion_fk=rs.getString(1);
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='21%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td></tr>");
					
					rs2=mvf.DetalledeFormulacion(codFormulacion_fk);
					while(rs2.next()){
						out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td></tr>");
					}
					rs2.getStatement().getConnection().close();
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
					out.print("</div>");
					
					rs.getStatement().getConnection().close();
				}
				else{
					String Pes="";
					String Tal="";
					rs2=mvf.ObtenerPesoTalla(codAdm);
					if(rs2.next()){
						Pes=rs2.getString(3);
						Tal=rs2.getString(4);
					}
					rs2.getStatement().getConnection().close();
					
					out.print("<table width='100%' border='1'>");					
					out.print("<tr><td colspan='2'><div align='center' id='cabecera2' class='style11'><span>Formulacion Medicamentos e Insumos</span></div><input name='txtTipo' type='hidden' id='txtTipo' value='0' /></td></tr>");
					out.print("<tr><td width='10%'><div><p><p>Detalle Orden<p></div></td><td width='90%'><textarea name='txtDetOrden' cols='50' rows='4' id='txtDetOrden' onkeyup='ActualizaDetalleOrden()' ></textarea> " +
							"Peso <input name='txtPeso' size='5' type='text' onkeyup='SoloNumeros(form1.txtPeso)' value='"+Pes+"' /> (En Kg. EJ:56)   " +
							"Talla <input name='txtTalla' size='5' type='text' onkeyup='SoloNumeros(form1.txtTalla)' value='"+Tal+"'  /> (En Mts. EJ:1.68) " +
							"</td></tr>");
					out.print("<tr><td width='11%'>Medicamento</td><td width='89%'><input name='txtMedicamento' type='text' id='txtMedicamento' size='70' onKeyUp='autocompletarMedicamentoFormula()' /></td></tr>");
					out.print("<tr><td><input name='txtCodigoMed' type='hidden' id='txtCodigoMed' size='18' /></td><td><div id='SugerenciaMedFormula'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='ConteForm'>");
					out.print("<table width='100%' >");
					out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='DetFormula'>");
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion'  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
					out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					//out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar'></div></td></tr></table>");
					out.print("</div>");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		}
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		/*************************************************************************************************************************/
		
		
		
		if(va.equals("8.1")){	
			String TipoPos="";
			String TipoControl="";
			String concentracion="";
			try {
				if(codFormulacion_fk!=""){				
					rs1=mvf.MedicamentoRepetidoEnFormulacion(codFormulacion_fk, CodMedicamento);
					if(rs1.next()){
						out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:medium; color:#FF0000 ' ><strong>El Medicamento Seleccionado Ya Se Encuentra En La Formula.\n Intente Nuevamente.</strong></div></td></tr></table>");
					}else{
						rs=mvf.BuscarDetalleMedicamento(CodMedicamento);						
						if(rs.next()){
							TipoPos=rs.getString(3);
							TipoControl=rs.getString(2);
							concentracion=rs.getString(4);
							if((TipoPos.equals("No Pos"))&&(TipoControl.equals("No"))){	
								//out.print("Medicamento No Pos y No es de Control");
								out.print("<table width='100%' border='1'><tr><td>Cantidad</td><td><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
								out.print("<td>Lapso</td><td><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
								out.print("<td>Administraci&oacute;n</td><td><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
								out.print("<td><input name='txtConcentracion' type='hidden' id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='5' maxlength='10' readonly='' /></td><td>Dosis <input name='txtCantDosis' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='5' maxlength='10'  />Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
								out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >MEDICAMENTO NO POS.</textarea><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarMedi()' /></td></tr>");
								out.print("</table>");
							}
							//////////////////////////////////////////////////////////////////
							if((TipoPos.equals("Pos"))&&(TipoControl.equals("No"))){
								//out.print("Medicamento Pos Y No es De Control");
								out.print("<table width='100%' border='1'><tr><td>Cantidad</td><td width='146'><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
								out.print("<td>Lapso</td><td><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
								out.print("<td>Administraci&oacute;n</td><td><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
								out.print("<td><input name='txtConcentracion' type='hidden' id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='5' maxlength='10' readonly='' /></td><td>Dosis <input name='txtCantDosis' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='5' maxlength='10'  />Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
								out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >NINGUNA.</textarea><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarMedi()' /></td></tr>");
								out.print("</table>");
							}	
						
							if((TipoPos.equals("Pos"))&&(TipoControl.equals("Si"))){
								//out.print("Medicamento Pos Y de Control.Una Sola Formulacion");
								rs2=mvf.DetalledeFormulacion(codFormulacion_fk);
								if(rs2.next()){
									out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:medium; color:#FF0000 ' ><strong>Medicamentos de Control Tienen Que Ir En Una Formulacion Aparte.</strong></div></td></tr></table>");
								}else{
									out.print("<table width='100%' border='1'><tr><td>Cantidad</td><td><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
									out.print("<td>Lapso</td><td><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
									out.print("<td>Administraci&oacute;n</td><td><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
									out.print("<td><input name='txtConcentracion' type='hidden' id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='5' maxlength='10' readonly='' /></td><td width='166'>Dosis <input name='txtCantDosis' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='10' maxlength='10'  />Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
									out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >MEDICAMENTO DE CONTROL</textarea><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='AsignarMediControl()' /></td></tr>");
									out.print("</table>");
								}
								rs2.getStatement().getConnection().close();								
							}

							if((TipoPos.equals("No Pos"))&&(TipoControl.equals("Si"))){
								//out.print("Medicamento No Pos Y de Control.Una Sola Formulacion");
								rs3=mvf.DetalledeFormulacion(codFormulacion_fk);
								if(rs3.next()){
									out.print("<table width='100%' border='1'><tr><td><div align='center' style='font-size:medium; color:#FF0000 ' ><strong>Medicamentos de Control Tienen Que Ir En Una Formulacion Aparte.</strong></div></td></tr></table>");
								}
								else{
									out.print("<table width='100%' border='1'><tr><td width='93'>Cantidad</td><td width='146'><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
									out.print("<td>Lapso</td><td width='154'><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
									out.print("<td>Administraci&oacute;n</td><td width='149'><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
									out.print("<td><input name='txtConcentracion' type='hidden' id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='10' maxlength='10' readonly='' /></td><td width='166'>Dosis <input name='txtCantDosis' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='10' maxlength='10'  />Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
									out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >MEDICAMENTO NO POS Y DE CONTROL.</textarea><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='AsignarMediControl()' /></td></tr>");
									out.print("</table>");
								}
								rs3.getStatement().getConnection().close();
							}
						}
						rs.getStatement().getConnection().close();
					}//fin del else de ver si el medicamento esta en la formula
					rs1.getStatement().getConnection().close();
				}else{
					rs=mvf.BuscarDetalleMedicamento(CodMedicamento);						
					if(rs.next()){
						TipoPos=rs.getString(3);
						TipoControl=rs.getString(2);
						concentracion=rs.getString(4);
						if((TipoPos.equals("No Pos"))&&(TipoControl.equals("No"))){
							//out.print("2)Medicamento No Pos y No es de Control");
							out.print("<table width='100%' border='1'><tr><td>Cantidad</td><td><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
							out.print("<td>Lapso</td><td><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
							out.print("<td>Administraci&oacute;n</td><td><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
							out.print("<td><input name='txtConcentracion' type='hidden' id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='5' maxlength='10' readonly='' /></td><td>Dosis <input name='txtCantDosis' size='5' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='10' maxlength='10'  /></td><td>Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
							out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >MEDICAMENTO NO POS.</textarea><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarMedi()' /></td></tr>");
							out.print("</table>");
						}
						//////////////////////////////////////////////////////////////////
						if((TipoPos.equals("Pos"))&&(TipoControl.equals("No"))){
							//out.print("2)Medicamento Pos Y No es De Control");
							out.print("<table width='100%' border='1'><tr><td>Cantidad</td><td><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
							out.print("<td>Lapso</td><td><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
							out.print("<td>Administraci&oacute;n</td><td><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
							out.print("<td><input name='txtConcentracion' type='hidden' id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='5' maxlength='10' readonly='' /></td><td>Dosis <input name='txtCantDosis' size='5' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='10' maxlength='10'  /></td><td>Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
							out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >NINGUNA.</textarea><input name='btnAsignar' type='button' id='btnAsignar' value='Asignar' onclick='AsignarMedi()' /></td></tr>");
							out.print("</table>");
						}
					
						if((TipoPos.equals("Pos"))&&(TipoControl.equals("Si"))){
							//out.print("2)Medicamento Pos Y de Control.Una Sola Formulacion");
							out.print("<table width='100%' border='1'><tr><td>Cantidad</td><td><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
							out.print("<td>Lapso</td><td><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
							out.print("<td>Administraci&oacute;n</td><td><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
							out.print("<td><input name='txtConcentracion' type='hidden' id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='5' maxlength='10' readonly='' /></td><td>Dosis <input name='txtCantDosis' size='5' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='10' maxlength='10'  /></td><td>Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
							out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >MEDICAMENTO DE CONTROL</textarea><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='AsignarMediControl()' /></td></tr>");
							out.print("</table>");
						}

						if((TipoPos.equals("No Pos"))&&(TipoControl.equals("Si"))){
							//out.print("2)Medicamento No Pos Y de Control.Una Sola Formulacion");
							out.print("<table width='100%' border='1'><tr><td>Cantidad</td><td><select name='cmbCantidad' id='cmbCantidad' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='0.25'>1/4</option><option value='0.5'>1/2</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option></select></td>");
							out.print("<td>Lapso</td><td><select name='cmbLapso' id='cmbLapso' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='4'>4</option><option value='6'>6</option><option value='8'>8</option><option value='12'>12</option><option value='24'>24</option></select>Horas</td>");
							out.print("<td>Administraci&oacute;n</td><td><select name='cmbViaAdmi' id='cmbViaAdmi' onchange='CalcularCantidad()' ><option value='Seleccione' selected='selected'>Seleccione</option><option value='Via Oral'>Via Oral</option><option value='Via Intravenosa'>Via Intravenosa</option><option value='Via Intramuscular'>Via Intramuscular</option><option value='Via Subcutanea'>Via Subcutanea</option><option value='Via Vaginal'>Via Vaginal</option><option value='Via Rectal'>Via Rectal</option><option value='Otra Via'>Otra Via</option></select></td>");
							out.print("<td><input name='txtConcentracion' type='hidden'  id='txtConcentracion' value='"+concentracion+"' />Cantidad&nbsp;&nbsp;&nbsp;&nbsp;<input name='txtCantidad' type='text' id='txtCantidad' size='5' maxlength='10' readonly='' /></td><td>Dosis <input name='txtCantDosis' size='5' type='text' id='txtCantDosis' onkeyup='SoloNumeros(form1.txtCantDosis),CalcularCantidad()' size='10' maxlength='10'  /></td><td>Und<select id='Unidad'><option value='Sel' selected='' >Sel</option>");
							rs2=mvf.ListarUnidades();
							while(rs2.next()){
								out.print("<option value='"+rs2.getString(3)+"'>"+rs2.getString(3)+"</option>");
							}
							rs2.getStatement().getConnection().close();
							out.print("</select>" +
									"Dia.TTO <input name='txtVigeTto' size='5' type='text' onkeyup='SoloNumeros(form1.txtVigeTto)' />(Dias)  " +
									"</td></tr>");
							out.print("<tr><td>Observacion</td><td colspan='7'><textarea name='txtObservacion' cols='40' rows='3' id='txtObservacion' >MEDICAMENTO NO POS Y DE CONTROL.</textarea><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='AsignarMediControl()' /></td></tr>");
							out.print("</table>");
						}
					}
					rs.getStatement().getConnection().close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("9IN")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
			String estado="";
			if(codFormulacion_fk==""){
				try {
					estado="-1";
					mvf.CrearFormulacion(codPac, codAdm, DetOrden, estado, Codusuario, codCama, codArea, codSubarea, hora+"", fecha+"");
					rs=mvf.ObtenerCodigoFormulacion(hora, fecha,codAdm);
					if(rs.next()){
						codFormulacion_fk=rs.getString(1);
					}
					rs.getStatement().getConnection().close();	
					mvf.CrearDetalleFormulacion(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado,"-","-","-","-",Codusuario);
					rs1=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
					if(rs1.next()){
						codDetFormulacion_fk=rs1.getString(1);
					}
					mvf.CrearDispenacion(codDetFormulacion_fk);
					rs1.getStatement().getConnection().close();
					
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='ConteForm'>");
					out.print("<table width='100%' >");
					out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='DetFormula'>");
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
					rs2=mvf.DetalledeFormulacion(codFormulacion_fk);
					while(rs2.next()){
						out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs2.getString(5)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
					out.print("</div>");
					rs2.getStatement().getConnection().close(); 					

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(va.equals("9IN.1")){
			try {
				java.util.Date fechaActual = new java.util.Date();
				java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
				java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
				String estado="";
				estado="-1";
				/*mvf.CrearFormulacion(codPac, codAdm, DetOrden, estado, Codusuario, codCama, codArea, codSubarea, hora, fecha);
				rs=mvf.ObtenerCodigoFormulacion(hora, fecha,codAdm);
				if(rs.next()){
					codFormulacion_fk=rs.getString(1);
				}
				rs.getStatement().getConnection().close();	*/
				mvf.CrearDetalleFormulacion(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado,"-","-","-","-",Codusuario);
				rs1=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
				if(rs1.next()){
					codDetFormulacion_fk=rs1.getString(1);
				}
				mvf.CrearDispenacion(codDetFormulacion_fk);
				rs1.getStatement().getConnection().close();
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				out.print("<div id='ConteForm'>");
				out.print("<table width='100%' >");
				out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
				out.print("</table>");
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				out.print("<div id='DetFormula'>");
				out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
				out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
				rs2=mvf.DetalledeFormulacion(codFormulacion_fk);
				while(rs2.next()){
					out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs2.getString(5)+")' >Omitir</a></td></tr>");
				}
				out.print("</table>");
				out.print("</div>");
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
				out.print("</div>");
				rs2.getStatement().getConnection().close(); 					

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 	}
		if(va.equals("9")){
			String Peso=req.getParameter("Peso");
			String Talla=req.getParameter("Talla");	
			String CantDosis=req.getParameter("CantDosis");
			String Unidad=req.getParameter("Unidad");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
			String estado="";
			if(codFormulacion_fk==""){
				try {
					estado="-1";
					mvf.CrearFormulacion(codPac, codAdm, DetOrden, estado, Codusuario, codCama, codArea, codSubarea, hora+"", fecha+"");
					rs=mvf.ObtenerCodigoFormulacion(hora, fecha,codAdm);
					if(rs.next()){
						codFormulacion_fk=rs.getString(1);
					}
					rs.getStatement().getConnection().close();	
					String CanLe=Convertir(cantidad);
					VigeTto=VigeTto+" Dia(s)";
					System.out.println("cantidad= "+cantidad+" dosificacion= "+dosificacion);
					mvf.CrearDetalleFormulacion(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado,CanLe,VigeTto,CantDosis,Unidad,Codusuario);
					///////////PERFIL FARMACOTERAPEUTICO/////////////////
					rs=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
					String DetFormu="";
					if(rs.next()){
						DetFormu=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
					int sw=0;
					rs3=mvf.ObtenerCodPerfilft(codAdm);
					if(rs3.next()){
						sw=1;
						String cp="";
						rs4=mvf.ObtenerCodPerfilft(codAdm);
						if(rs4.next()){
							cp=rs4.getString(1);
						}
						rs4.getStatement().getConnection().close(); 
						String sep="";
						String sep1="";
						String sep2="";
						String sep3="";
						String sep4="";
						String sep5="";
						String sep6="";
						String sep7="";
						sep=dosificacion.replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1)
						{ 		
							sep1=d[0];
							sep2=d[1];
							sep3=d[2];
							sep4=d[3];
							sep5=d[4];
							sep6=d[5];
							sep7=d[6];
						}
						String via=sep7;
						String frecuencia=sep3+" "+sep4+" "+sep5;					
						System.out.println(" 9 cp"+cp+" codigoMed"+codigoMed+" Codusuario "+Codusuario+" via "+via+" frecuencia "+frecuencia+" cantidad "+cantidad+" fecha "+fecha+" hora "+hora+" codFormulacion_fk "+codFormulacion_fk);
						rs=mvf.SoloMedicamento(codigoMed);
						if(rs.next()){
							mvf.CrearDetallePerfilft(cp,codigoMed,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",DetFormu);	
						}
						rs.getStatement().getConnection().close();
						/**************************************************************************************/
						/*************************INICIO DE LA ORDEN DE PRODUCCION*****************************/
						/*1) CREAR LA ORDEN DE PRODUCCION POR DIA SE PUEDE TOMAR COMO PARA METRO DE REFERENCIA LA FECHA
					       EJ: SI EXISTE UN REGISTRO CON LA FECHA 2012-01-01 NO SE CREA SINO QUE SE ADJUNTA EL DETALLE
						   SI NO EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION, SE HACE EL INSERT EN LA TABLA.
						2) SI EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION SE CONSULTA SI EL MEDICAMENTO PERTENECE
						   A LOS QUE ESTAN CREADOS EN LA TABLA farc_med_orden_produccion.
						   	SI EL MEDICAMENTO PERTENECE SE HACE EL INGRESO A LA TABLA farc_detalle_orden_produccion.
						   	SI NO SE OMITE LA INSERCION LA TABLA.*/
						rs=mvf.ObtenerMedicamentoParaOrden(codigoMed);
						if(rs.next()){
							/**************************************************************/
							//String sep="";String sep4="";						
							String sepp=dosificacion.replace(" ", "_");					
							int h2=sepp.split("_").length;
							String[] d2=sep.split("_");		     	
							for(int g=0; g<h2-1; g=g+1){
								sep4=d2[3];
							}
							frecuencia=sep4;	
							/**************************************************************/
							String codSubarea1="";String codCama1="";						
							rs6=mvf.ObtenerDatosPerfilFarmaco(codAdm);
							if(rs6.next()){
								codSubarea1=rs6.getString(5);;
								codCama1=rs6.getString(6);
								
								//buscamos si hay una orden creada para la fecha actual.
								rs1=mvf.ObtenerOrdenProduccion(fecha+"");
								if(rs1.next()){
									//si existe orden para la fecha actual.							
									//se crea el detalle de la orden de produccion.							
									//dosis=campo nuevo de la db
									//frecuencia= ej:dar 2 cada 12 hora se inserta solo el 12.
									String horString = "";
									rs3=mvf.ObtenerCorteProduccion();
									if(rs3.next()){
										horString=rs3.getString(2);
									}
									rs3.getStatement().getConnection().close();
									String hora1=hora+"";
									String hora2=horString;
									String ho1 = null;
									String ho2 = null;
									String mo1 = null;
									String mo2 = null;
									int f1=hora1.split(":").length;
									String[] j1=hora1.split(":");		     	
									for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
									int f2=hora2.split(":").length;
									String[] j2=hora2.split(":");		     	
									for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
									if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
										System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);
									
									}else{
										System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
										//aqui es donde se inserta//
										if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
										
										}else{
											System.out.println(" Entra en Crear Detalle Orden Produccion 1.");
											rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
											if(rs5.next()){
												codDetFormulacion_fk=rs5.getString(1);
											}
											rs5.getStatement().getConnection().close();
											mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
										}
									}
								}else{
									//no existe orden para el dia actual se crea el registro.
									mvf.CrearOrdenProduccion(fecha+"", hora+"");
									//se consulta la orden de produccion creada.
									rs2=mvf.ObtenerOrdenProduccion(fecha+"");
									if(rs2.next()){
										//se crea el detalle de la orden de produccion.
										String horString = "";
										rs3=mvf.ObtenerCorteProduccion();
										if(rs3.next()){
											horString=rs3.getString(2);
										}
										rs3.getStatement().getConnection().close();
										String hora1=hora+"";
										String hora2=horString;
										String ho1 = null;
										String ho2 = null;
										String mo1 = null;
										String mo2 = null;
										int f1=hora1.split(":").length;
										String[] j1=hora1.split(":");		     	
										for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
										int f2=hora2.split(":").length;
										String[] j2=hora2.split(":");		     	
										for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
										if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
											System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);
										
										}else{
											System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
											//aqui es donde se inserta//
											if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
											
											}else{
												System.out.println(" Entra en Crear Detalle Orden Produccion. 2");
												rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
												if(rs5.next()){
													codDetFormulacion_fk=rs5.getString(1);
												}
												rs5.getStatement().getConnection().close();
												mvf.CrearDetalleOrdenProduccion(rs2.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
											}
										}
									
									}
									rs2.getStatement().getConnection().close();
								}			
								rs1.getStatement().getConnection().close();							
							}
							rs6.getStatement().getConnection().close();
							/**************************************************************/
							//el medicamento pertenece a orden.
						}else{
							
						}
						rs.getStatement().getConnection().close();

					}
					//rs3.getStatement().getConnection().close(); 					
					
					if(sw==0){
						/***no existe el perfil**/
						String fi="";String codArea1="";String codSubarea1="";String codCama1="";
						String cdx="";String sexo="";String edad="";String Serv="";	String aler="";
						String peso="";
						
						rs1=mvf.ObtenerDatosPerfilFarmaco(codAdm);
						if(rs1.next()){
							fi=rs1.getString(1);
							codArea1=rs1.getString(4);
							codSubarea1=rs1.getString(5);;
							codCama1=rs1.getString(6);
							cdx=rs1.getString(7);
							sexo=rs1.getString(2);
							if(sexo.equals("Masculino")){sexo="M";}
							if(sexo.equals("Femenino")){sexo="F";}
							edad=rs1.getString(3);
							Serv=rs1.getString(8);		
							System.out.println("9-->");
							rs2=mvf.ObtenerPesoTalla(codAdm);
							if(rs2.next()){
								//update del peso y la talla
								mvf.ActualizarPesoTalla(codAdm, Peso, Talla);
							}else{
								mvf.CrearPesoTalla(codAdm, Peso, Talla);
							}
							rs2.getStatement().getConnection().close();
						}
						rs1.getStatement().getConnection().close();
						//System.out.println(" 9 codAdm "+codAdm+" codPac "+codPac+" fi "+fi+" codArea1 "+codArea1+" codSubarea1 "+codSubarea1+" codCama1 "+codCama1+" cdx "+cdx+" aler "+aler+" sexo "+sexo);
					mvf.CrearPerfilft(codAdm,codPac,fi,null,codArea1,codSubarea1,codCama1,cdx,aler,sexo,edad,Peso,Talla);
					String cp="";
					rs4=mvf.ObtenerCodPerfilft(codAdm);
					if(rs4.next()){
						cp=rs4.getString(1);
					}
					rs4.getStatement().getConnection().close(); 
					//VIA=VIA INTRAVENOSA ETC.
					//FRECUENCIA=1 CADA 24 HORAS, ETC
					//CANTIDAD=ES LA CANTIDAD DE LA DOSIFICACION NO LA TOTAL.EJ:
					//DAR 2 CADA 8 HORAS.VIA ORAL, LA CANTIDAD=2
					String sep="";String sep1="";String sep2="";String sep3="";
					String sep4="";	String sep5="";String sep6="";String sep7="";
					sep=dosificacion.replace(" ", "_");					
					int h=sep.split("_").length;
					String[] d=sep.split("_");		     	
					for(int g=0; g<h-1; g=g+1)
					{ 		
						sep1=d[0];sep2=d[1];sep3=d[2];sep4=d[3];
						sep5=d[4];sep6=d[5];sep7=d[6];
					}
					String via=sep7;
					String frecuencia=sep3+" "+sep4+" "+sep5;					
					System.out.println(" 9 cp"+cp+" codigoMed"+codigoMed+" Codusuario "+Codusuario+" via "+via+" frecuencia "+frecuencia+" cantidad "+cantidad+" fecha "+fecha+" hora "+hora+" codFormulacion_fk "+codFormulacion_fk);
					rs=mvf.SoloMedicamento(codigoMed);
					if(rs.next()){
						mvf.CrearDetallePerfilft(cp,codigoMed,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",DetFormu);	
					}
					rs.getStatement().getConnection().close();
			//////////FIN PERFIL FARMACOTERAPEUTICO//////////////					
					
					/**************************************************************************************/
					/*************************INICIO DE LA ORDEN DE PRODUCCION*****************************/
					/*1) CREAR LA ORDEN DE PRODUCCION POR DIA SE PUEDE TOMAR COMO PARA METRO DE REFERENCIA LA FECHA
				       EJ: SI EXISTE UN REGISTRO CON LA FECHA 2012-01-01 NO SE CREA SINO QUE SE ADJUNTA EL DETALLE
					   SI NO EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION, SE HACE EL INSERT EN LA TABLA.
					2) SI EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION SE CONSULTA SI EL MEDICAMENTO PERTENECE
					   A LOS QUE ESTAN CREADOS EN LA TABLA farc_med_orden_produccion.
					   	SI EL MEDICAMENTO PERTENECE SE HACE EL INGRESO A LA TABLA farc_detalle_orden_produccion.
					   	SI NO SE OMITE LA INSERCION LA TABLA.*/
					rs=mvf.ObtenerMedicamentoParaOrden(codigoMed);
					String CodMedOr="";
					if(rs.next()){
						CodMedOr=rs.getString(1);
						/**************************************************************/
						//String sep="";String sep4="";						
						String sepp=dosificacion.replace(" ", "_");					
						int h2=sepp.split("_").length;
						String[] d2=sep.split("_");		     	
						for(int g=0; g<h2-1; g=g+1){
							sep4=d2[3];
						}
						frecuencia=sep4;	
						/**************************************************************/
						//String codSubarea1="";String codCama1="";						
						rs6=mvf.ObtenerDatosPerfilFarmaco(codAdm);
						if(rs6.next()){
							codSubarea1=rs6.getString(5);;
							codCama1=rs6.getString(6);
							rs1=mvf.ObtenerOrdenProduccion(fecha+"");
							if(rs1.next()){
								//si existe orden para la fecha actual.							
								//se crea el detalle de la orden de produccion.							
								//dosis=campo nuevo de la db
								//frecuencia= ej:dar 2 cada 12 hora se inserta solo el 12.
								String horString = "";
								String CodOrdPro =rs1.getString(1);
								rs3=mvf.ObtenerCorteProduccion();
								if(rs3.next()){
									horString=rs3.getString(2);
									//CodOrdPro=rs3.getString(1);
								}
								rs3.getStatement().getConnection().close();
								String hora1=hora+"";
								String hora2=horString;
								String ho1 = null;
								String ho2 = null;
								String mo1 = null;
								String mo2 = null;
								int f1=hora1.split(":").length;
								String[] j1=hora1.split(":");		     	
								for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
								int f2=hora2.split(":").length;
								String[] j2=hora2.split(":");		     	
								for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
								if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
									System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);								
								}else{
									System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
									//aqui es donde se inserta//
									if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
									
									}else{
										System.out.println(" Entra en Crear Detalle Orden Produccion.3");
										rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
										if(rs5.next()){
											codDetFormulacion_fk=rs5.getString(1);
										}
										rs5.getStatement().getConnection().close();
										mvf.CrearDetalleOrdenProduccion(CodOrdPro, CodMedOr, codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
									}
								}							
							}else{
								//no existe orden para el dia actual se crea el registro.
								mvf.CrearOrdenProduccion(fecha+"", hora+"");
								//se consulta la orden de produccion creada.
								rs2=mvf.ObtenerOrdenProduccion(fecha+"");
								if(rs2.next()){
									//se crea el detalle de la orden de produccion.
									String horString = "";
									rs3=mvf.ObtenerCorteProduccion();
									if(rs3.next()){
										horString=rs3.getString(2);
									}
									rs3.getStatement().getConnection().close();
									String hora1=hora+"";
									String hora2=horString;
									String ho1 = null;
									String ho2 = null;
									String mo1 = null;
									String mo2 = null;
									int f1=hora1.split(":").length;
									String[] j1=hora1.split(":");		     	
									for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
									int f2=hora2.split(":").length;
									String[] j2=hora2.split(":");		     	
									for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
									if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
										System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);									
									}else{
										System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
										//aqui es donde se inserta//
										if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
										
										}else{
											System.out.println(" Entra en Crear Detalle Orden Produccion.4");
											rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
											if(rs5.next()){
												codDetFormulacion_fk=rs5.getString(1);
											}
											rs5.getStatement().getConnection().close();
											mvf.CrearDetalleOrdenProduccion(rs2.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
										}
									}								
								}
								rs2.getStatement().getConnection().close();
							}	
							rs1.getStatement().getConnection().close();
						}
						rs6.getStatement().getConnection().close();
						/**************************************************************/
						//el medicamento pertenece a orden.
						//buscamos si hay una orden creada para la fecha actual.
						//*								
						
					}else{
						
					}
					rs.getStatement().getConnection().close();
					
					/**************************************************************************************/
					}		
					/****************************************************/
					System.out.println("9-->");
					rs2=mvf.ObtenerPesoTalla(codAdm);
					if(rs2.next()){
						//update del peso y la talla
						mvf.ActualizarPesoTalla(codAdm, Peso, Talla);
					}else{
						mvf.CrearPesoTalla(codAdm, Peso, Talla);
					}
					rs2.getStatement().getConnection().close();
					/****************************************************/
					
					rs1=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
					if(rs1.next()){
						codDetFormulacion_fk=rs1.getString(1);
					}
					mvf.CrearDispenacion(codDetFormulacion_fk);
					rs1.getStatement().getConnection().close();
					
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='ConteForm'>");
					out.print("<table width='100%' >");
					out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
					out.print("</table>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<div id='DetFormula'>");
					out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
					rs2=mvf.DetalledeFormulacion(codFormulacion_fk);
					while(rs2.next()){
						out.print("<tr><td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td><td>"+rs2.getString(3)+"</td><td>"+rs2.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs2.getString(5)+")' >Omitir</a></td></tr>");
					}
					out.print("</table>");
					out.print("</div>");
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
					out.print("</div>");
					rs2.getStatement().getConnection().close(); 					
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
		
		}
		if(va.equals("9.1")){
			String Peso=req.getParameter("Peso");
			String Talla=req.getParameter("Talla");	
		
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora = new java.sql.Time(fechaActual.getTime());
			String CantDosis=req.getParameter("CantDosis");
			String Unidad=req.getParameter("Unidad");
			String estado="";
			if(codFormulacion_fk!=""){
				try {
					System.out.println("9.1");
					rs2=mvf.ObtenerPesoTalla(codAdm);
					if(rs2.next()){
						//update del peso y la talla
						mvf.ActualizarPesoTalla(codAdm, Peso, Talla);
					}else{
						mvf.CrearPesoTalla(codAdm, Peso, Talla);
					}
					rs2.getStatement().getConnection().close();
					
					rs3=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
					if(rs3.next()){
						codDetFormulacion_fk=rs3.getString(1);
						out.print("<div id='ConteForm'>");
						out.print("<table width='100%' >");
						out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
						out.print("</table>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<div id='DetFormula'>");
						out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
						out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
						out.print("<tr>Ya Existe este medicamento/insumo en la formulacion</tr>");
						rs4=mvf.DetalledeFormulacion(codFormulacion_fk);
						while(rs4.next()){
							out.print("<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getString(3)+"</td><td>"+rs4.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs4.getString(5)+")' >Omitir</a></td></tr>");
						}
						rs4.getStatement().getConnection().close();
						out.print("</table>");
						out.print("</div>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
						out.print("</div>");

					}else{
						estado="-1";	
						String CanLe=Convertir(cantidad);
						VigeTto=VigeTto+" Dia(s)";
						mvf.CrearDetalleFormulacion(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado,CanLe,VigeTto,CantDosis,Unidad,Codusuario);
						/*************************PERFIL FARMACOTERAPEUTICO*****************/
						String cp="";
						rs4=mvf.ObtenerCodPerfilft(codAdm);
						if(rs4.next()){
							cp=rs4.getString(1);							
							/**************************************************************************************/
							/*************************INICIO DE LA ORDEN DE PRODUCCION*****************************/
							/*1) CREAR LA ORDEN DE PRODUCCION POR DIA SE PUEDE TOMAR COMO PARA METRO DE REFERENCIA LA FECHA
						       EJ: SI EXISTE UN REGISTRO CON LA FECHA 2012-01-01 NO SE CREA SINO QUE SE ADJUNTA EL DETALLE
							   SI NO EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION, SE HACE EL INSERT EN LA TABLA.
							2) SI EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION SE CONSULTA SI EL MEDICAMENTO PERTENECE
							   A LOS QUE ESTAN CREADOS EN LA TABLA farc_med_orden_produccion.
							   	SI EL MEDICAMENTO PERTENECE SE HACE EL INGRESO A LA TABLA farc_detalle_orden_produccion.
							   	SI NO SE OMITE LA INSERCION LA TABLA.*/
							rs=mvf.ObtenerMedicamentoParaOrden(codigoMed);
							if(rs.next()){
								/**************************************************************/
								String sepp="";String sep4="";						
								sepp=dosificacion.replace(" ", "_");					
								int h1=sepp.split("_").length;
								String[] d1=sepp.split("_");		     	
								for(int g=0; g<h1-1; g=g+1){
									sep4=d1[3];
								}
								String frecuencia1=sep4;	
								/**************************************************************/
								String codSubarea1="";String codCama1="";						
								rs6=mvf.ObtenerDatosPerfilFarmaco(codAdm);
								if(rs6.next()){
									codSubarea1=rs6.getString(5);;
									codCama1=rs6.getString(6);
									rs1=mvf.ObtenerOrdenProduccion(fecha+"");
									if(rs1.next()){
										//si existe orden para la fecha actual.							
										//se crea el detalle de la orden de produccion.							
										//dosis=campo nuevo de la db
										//frecuencia= ej:dar 2 cada 12 hora se inserta solo el 12.
										String horString = "";
										rs3=mvf.ObtenerCorteProduccion();
										if(rs3.next()){
											horString=rs3.getString(2);
										}
										rs3.getStatement().getConnection().close();
										String hora1=hora+"";
										String hora2=horString;
										String ho1 = null;
										String ho2 = null;
										String mo1 = null;
										String mo2 = null;
										int f1=hora1.split(":").length;
										String[] j1=hora1.split(":");		     	
										for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
										int f2=hora2.split(":").length;
										String[] j2=hora2.split(":");		     	
										for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
										if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
											System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);										
										}else{
											System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
											//aqui es donde se inserta//
											if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
											
											}else{
												System.out.println(" Entra en Crear Detalle Orden Produccion.5");
												rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
												if(rs5.next()){
													codDetFormulacion_fk=rs5.getString(1);
												}
												rs5.getStatement().getConnection().close();
												mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia1,Unidad);
											}
										}									
									}else{
										//no existe orden para el dia actual se crea el registro.
										mvf.CrearOrdenProduccion(fecha+"", hora+"");
										//se consulta la orden de produccion creada.
										rs2=mvf.ObtenerOrdenProduccion(fecha+"");
										if(rs2.next()){
											//se crea el detalle de la orden de produccion.
											String horString = "";
											rs3=mvf.ObtenerCorteProduccion();
											if(rs3.next()){
												horString=rs3.getString(2);
											}
											rs3.getStatement().getConnection().close();
											String hora1=hora+"";
											String hora2=horString;
											String ho1 = null;
											String ho2 = null;
											String mo1 = null;
											String mo2 = null;
											int f1=hora1.split(":").length;
											String[] j1=hora1.split(":");		     	
											for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
											int f2=hora2.split(":").length;
											String[] j2=hora2.split(":");		     	
											for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
											if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
												System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);											
											}else{
												System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
												//aqui es donde se inserta//
												if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){												
												}else{
													System.out.println(" Entra en Crear Detalle Orden Produccion.6");
													rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
													if(rs5.next()){
														codDetFormulacion_fk=rs5.getString(1);
													}
													rs5.getStatement().getConnection().close();
													mvf.CrearDetalleOrdenProduccion(rs2.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia1,Unidad);
												}
											}
										}
										rs2.getStatement().getConnection().close();
									}
									rs1.getStatement().getConnection().close();
								}
								rs6.getStatement().getConnection().close();								
								/**************************************************************/
								//el medicamento pertenece a orden.
								//buscamos si hay una orden creada para la fecha actual.											
								//rs1.getStatement().getConnection().close();
							}else{
								
							}
							rs.getStatement().getConnection().close();
							
							/**************************************************************************************/
							
		
						}else{
							//se crea el perfilfarmacologico
							String fi="";String codArea1="";String codSubarea1="";String codCama1="";
							String cdx="";String sexo="";String edad="";String Serv="";	String aler="";
							String peso="";
							
							rs1=mvf.ObtenerDatosPerfilFarmaco(codAdm);
							if(rs1.next()){
								fi=rs1.getString(1);
								codArea1=rs1.getString(4);
								codSubarea1=rs1.getString(5);;
								codCama1=rs1.getString(6);
								cdx=rs1.getString(7);
								sexo=rs1.getString(2);
								if(sexo.equals("Masculino")){sexo="M";}
								if(sexo.equals("Femenino")){sexo="F";}
								edad=rs1.getString(3);
								Serv=rs1.getString(8);	
								System.out.println("9.1");
								rs2=mvf.ObtenerPesoTalla(codAdm);
								if(rs2.next()){
									//update del peso y la talla
									mvf.ActualizarPesoTalla(codAdm, Peso, Talla);
								}else{
									mvf.CrearPesoTalla(codAdm, Peso, Talla);
								}
								rs2.getStatement().getConnection().close();
							}
							rs1.getStatement().getConnection().close();
							//System.out.println(" 9 codAdm "+codAdm+" codPac "+codPac+" fi "+fi+" codArea1 "+codArea1+" codSubarea1 "+codSubarea1+" codCama1 "+codCama1+" cdx "+cdx+" aler "+aler+" sexo "+sexo);
						mvf.CrearPerfilft(codAdm,codPac,fi,null,codArea1,codSubarea1,codCama1,cdx,aler,sexo,edad,Peso,Talla);
						
						String cpp="";
						rs4=mvf.ObtenerCodPerfilft(codAdm);
						if(rs4.next()){
							cpp=rs4.getString(1);
						}
						rs4.getStatement().getConnection().close();
						
						String sep="";
						String sep1="";
						String sep2="";
						String sep3="";
						String sep4="";
						String sep5="";
						String sep6="";
						String sep7="";
						sep=dosificacion.replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1)
						{ 		
							sep1=d[0];
							sep2=d[1];
							sep3=d[2];
							sep4=d[3];
							sep5=d[4];
							sep6=d[5];
							sep7=d[6];
						}
						String via=sep7;
						String frecuencia=sep3+" "+sep4+" "+sep5;	
						
						rs=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
						String DetFormu="";
						if(rs.next()){
							DetFormu=rs.getString(1);
						}
						rs.getStatement().getConnection().close();
						
						System.out.println(" 9 cp"+cp+" codigoMed"+codigoMed+" Codusuario "+Codusuario+" via "+via+" frecuencia "+frecuencia+" cantidad "+cantidad+" fecha "+fecha+" hora "+hora+" codFormulacion_fk "+codFormulacion_fk);
						rs=mvf.SoloMedicamento(codigoMed);
						if(rs.next()){
							mvf.CrearDetallePerfilft(cp,codigoMed,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",DetFormu);	
						}
						rs.getStatement().getConnection().close();
				//////////FIN PERFIL FARMACOTERAPEUTICO//////////////
						rs=mvf.ObtenerMedicamentoParaOrden(codigoMed);
						if(rs.next()){
							/**************************************************************/
							//String sep="";String sep4="";						
							String sepp=dosificacion.replace(" ", "_");					
							int h2=sepp.split("_").length;
							String[] d2=sep.split("_");		     	
							for(int g=0; g<h2-1; g=g+1){
								sep4=d2[3];
							}
							frecuencia=sep4;	
							/**************************************************************/
							//String codSubarea1="";String codCama1="";						
							rs6=mvf.ObtenerDatosPerfilFarmaco(codAdm);
							if(rs6.next()){
								codSubarea1=rs6.getString(5);;
								codCama1=rs6.getString(6);
								rs1=mvf.ObtenerOrdenProduccion(fecha+"");
								if(rs1.next()){
									//si existe orden para la fecha actual.							
									//se crea el detalle de la orden de produccion.							
									//dosis=campo nuevo de la db
									//frecuencia= ej:dar 2 cada 12 hora se inserta solo el 12.
									String horString = "";
									rs3=mvf.ObtenerCorteProduccion();
									if(rs3.next()){
										horString=rs3.getString(2);
									}
									rs3.getStatement().getConnection().close();
									String hora1=hora+"";
									String hora2=horString;
									String ho1 = null;
									String ho2 = null;
									String mo1 = null;
									String mo2 = null;
									int f1=hora1.split(":").length;
									String[] j1=hora1.split(":");		     	
									for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
									int f2=hora2.split(":").length;
									String[] j2=hora2.split(":");		     	
									for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
									if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
										System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);									
									}else{
										System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
										//aqui es donde se inserta//
										if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
										
										}else{
											System.out.println(" Entra en Crear Detalle Orden Produccion.7");
											rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
											if(rs5.next()){
												codDetFormulacion_fk=rs5.getString(1);
											}
											rs5.getStatement().getConnection().close();
											mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
										}
									}
								
								}else{
									//no existe orden para el dia actual se crea el registro.
									mvf.CrearOrdenProduccion(fecha+"", hora+"");
									//se consulta la orden de produccion creada.
									rs2=mvf.ObtenerOrdenProduccion(fecha+"");
									if(rs2.next()){
										//se crea el detalle de la orden de produccion.
										String horString = "";
										rs3=mvf.ObtenerCorteProduccion();
										if(rs3.next()){
											horString=rs3.getString(2);
										}
										rs3.getStatement().getConnection().close();
										String hora1=hora+"";
										String hora2=horString;
										String ho1 = null;
										String ho2 = null;
										String mo1 = null;
										String mo2 = null;
										int f1=hora1.split(":").length;
										String[] j1=hora1.split(":");		     	
										for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
										int f2=hora2.split(":").length;
										String[] j2=hora2.split(":");		     	
										for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
										if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
											System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);										
										}else{
											System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
											//aqui es donde se inserta//
											if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
											
											}else{
												System.out.println(" Entra en Crear Detalle Orden Produccion.8");
												rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
												if(rs5.next()){
													codDetFormulacion_fk=rs5.getString(1);
												}
												rs5.getStatement().getConnection().close();
												mvf.CrearDetalleOrdenProduccion(rs2.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
											}
										}
									}
									rs2.getStatement().getConnection().close();
								}
								rs1.getStatement().getConnection().close();
							}
							rs6.getStatement().getConnection().close();
							
							/**************************************************************/
							//el medicamento pertenece a orden.
							//buscamos si hay una orden creada para la fecha actual.										
							
						}
						
						//se crea el detalle del perfil farmacologico
							//se crea la orden de produccion
							//sino existe se crea la orden de produccion
							//y se crea el detalle de la orden de produccion
							
						}
						
						//rs4.getStatement().getConnection().close(); 						
						String sep="";
						String sep1="";
						String sep2="";
						String sep3="";
						String sep4="";
						String sep5="";
						String sep6="";
						String sep7="";
						sep=dosificacion.replace(" ", "_");					
						int h=sep.split("_").length;
						String[] d=sep.split("_");		     	
						for(int g=0; g<h-1; g=g+1)
						{ 		
							sep1=d[0];
							sep2=d[1];
							sep3=d[2];
							sep4=d[3];
							sep5=d[4];
							sep6=d[5];
							sep7=d[6];
						}
						String via=sep7;
						String frecuencia=sep3+" "+sep4+" "+sep5;
						System.out.println(" 9.1 cp"+cp+" codigoMed"+codigoMed+" Codusuario "+Codusuario+" via "+via+" frecuencia "+frecuencia+" cantidad "+cantidad+" fecha "+fecha+" hora "+hora+" codFormulacion_fk "+codFormulacion_fk);
						
						/*************************FIN DE PERFIL FARMACOTERAPEUTICO************/	
						rs=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
						if(rs.next()){
							//out.print("Ya Existe este medicamento/insumo en la formulacion.");
							codDetFormulacion_fk=rs.getString(1);
							
						}
						mvf.CrearDispenacion(codDetFormulacion_fk);
						
						rs.getStatement().getConnection().close();
						rs=mvf.SoloMedicamento(codigoMed);
						if(rs.next()){
							mvf.CrearDetallePerfilft(cp,codigoMed,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",codDetFormulacion_fk);	
						}
						rs.getStatement().getConnection().close();
						
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<div id='ConteForm'>");
						out.print("<table width='100%' >");
						out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
						out.print("</table>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<div id='DetFormula'>");
						out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
						out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><div align='center'>Cantidad</div><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+"  /></td><td width='6%'><div align='center'>Accion</div></td></tr>");
						rs4=mvf.DetalledeFormulacion(codFormulacion_fk);
						while(rs4.next()){
							out.print("<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getString(3)+"</td><td>"+rs4.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs4.getString(5)+")' >Omitir</a></td></tr>");
						}
						out.print("</table>");
						out.print("</div>");
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
						out.print("</div>");
						rs4.getStatement().getConnection().close(); 	
					}
					//rs3.getStatement().getConnection().close();	
				
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
			}
			
		}
		
		if(va.equals("10")){
			//finalizar la formulacion.			
			try {
				mvf.ActivarDetalleFormulacion(codFormulacion_fk);
				mvf.ActivarFormulacion(codFormulacion_fk,"0");			
				rs=mvf.ObtenerEstadoFormulacion(codFormulacion_fk);
				if(rs.next()){
					mvf.ActivarDetalleFormulacion(codFormulacion_fk);
					mvf.ActivarFormulacion(codFormulacion_fk,"0");
					out.print("Error Al Actualizar Registros.");
				}
				else{
					out.print("1");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("11")){
			//omitir detalle de formulacion
			codDetFormulacion_fk=req.getParameter("codDetFormulacion_fk");
			try {
				mvf.OmitirDetalleFormulacion(codDetFormulacion_fk);
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				out.print("<div id='ConteForm'>");
				out.print("<table width='100%' >");
				out.print("<tr><td><div id='DetAdministra'></div></td></tr>");
				out.print("</table>");
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				out.print("<div id='DetFormula'>");
				out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' id='cabecera2' class='style11'><span>Detalle Formulaci&oacute;n</span></div></td></tr>");
				/***/
				rs=mvf.DetalledeFormulacion(codFormulacion_fk);
				if(rs.next()){
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value="+codFormulacion_fk+" /><div align='center'>Cantidad</div></td><td width='6%'><div align='center'>Accion</div></td></tr>");
				}
				else{
					out.print("<tr><td width='42%'><div align='center'>Medicamento</div></td><td width='27%'><div align='center'>Dosificaci&oacute;n</div></td><td width='15%'><div align='center'>Observaci&oacute;n</div></td><td width='10%'><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion'	 /><div align='center'>Cantidad</div></td><td width='6%'><div align='center'>Accion</div></td></tr>");
				}
				rs.getStatement().getConnection().close();
				/***/
				if(codFormulacion_fk!=""){
					rs3=mvf.DetalledeFormulacion(codFormulacion_fk);
					if(rs3.next()){
						rs4=mvf.DetalledeFormulacion(codFormulacion_fk);
						while(rs4.next()){					
							out.print("<tr><td>"+rs4.getString(1)+"</td><td>"+rs4.getString(2)+"</td><td>"+rs4.getString(3)+"</td><td>"+rs4.getString(4)+"</td><td><a href='#' onclick='OmitirDetalleFormulacion("+rs4.getString(5)+")' >Omitir</a></td></tr>");
						}
						rs4.getStatement().getConnection().close(); 
						out.print("<table width='100%' border='1' ><tr><td><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
					}
					else{
						mvf.OmitirFormulacion(codFormulacion_fk);
						codFormulacion_fk="";
					}
					rs3.getStatement().getConnection().close();
				}
				out.print("</table>");
				out.print("</div>");
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				//out.print("<table width='100%' border='1' ><tr><td><input name='txtCodFormulacion' type='hidden' id='txtCodFormulacion' value='"+codFormulacion_fk+"'  /><div align='center'><input name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='FinalFormulacion()' ></div></td></tr></table>");
				out.print("</div>");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("12")){
			//****
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time hora = new java.sql.Time(fechaActual.getTime());	
			String Peso=req.getParameter("Peso");
			String Talla=req.getParameter("Talla");
			String CantDosis=req.getParameter("CantDosis");
			String Unidad=req.getParameter("Unidad");
			String estado="";
			try {
				
				rs2=mvf.ObtenerPesoTalla(codAdm);
				if(rs2.next()){
					//update del peso y la talla
					mvf.ActualizarPesoTalla(codAdm, Peso, Talla);
				}else{
					mvf.CrearPesoTalla(codAdm, Peso, Talla);
				}
				rs2.getStatement().getConnection().close();
				estado="0";
				mvf.CrearFormulacion(codPac, codAdm, DetOrden, estado, Codusuario, codCama, codArea, codSubarea, hora+"", fecha+"");
				rs=mvf.ObtenerCodigoFormulacion(hora, fecha,codAdm);
				if(rs.next()){
					codFormulacion_fk=rs.getString(1);
				}
				rs.getStatement().getConnection().close();	
				String CanLe=Convertir(cantidad);
				VigeTto=VigeTto+" Dia(s)";
				mvf.CrearDetalleFormulacion(codFormulacion_fk, codigoMed, cantidad, dosificacion, observacion, estado,CanLe,VigeTto,CantDosis,Unidad,Codusuario);
				/**************************************************************************************/
				rs=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
				String DetFormu="";
				if(rs.next()){
					DetFormu=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				int sw=0;
				rs3=mvf.ObtenerCodPerfilft(codAdm);
				if(rs3.next()){
					sw=1;
					String cp="";
					rs4=mvf.ObtenerCodPerfilft(codAdm);
					if(rs4.next()){
						cp=rs4.getString(1);
					}
					rs4.getStatement().getConnection().close(); 
					String sep="";
					String sep1="";
					String sep2="";
					String sep3="";
					String sep4="";
					String sep5="";
					String sep6="";
					String sep7="";
					sep=dosificacion.replace(" ", "_");					
					int h=sep.split("_").length;
					String[] d=sep.split("_");		     	
					for(int g=0; g<h-1; g=g+1)
					{ 		
						sep1=d[0];
						sep2=d[1];
						sep3=d[2];
						sep4=d[3];
						sep5=d[4];
						sep6=d[5];
						sep7=d[6];
					}
					String via=sep7;
					String frecuencia=sep3+" "+sep4+" "+sep5;					
					System.out.println(" 9 cp"+cp+" codigoMed"+codigoMed+" Codusuario "+Codusuario+" via "+via+" frecuencia "+frecuencia+" cantidad "+cantidad+" fecha "+fecha+" hora "+hora+" codFormulacion_fk "+codFormulacion_fk);
					rs=mvf.SoloMedicamento(codigoMed);
					if(rs.next()){
						mvf.CrearDetallePerfilft(cp,codigoMed,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",DetFormu);	
					}
					rs.getStatement().getConnection().close();

				}
				rs3.getStatement().getConnection().close(); 					
				
				if(sw==0){
					/***no existe el perfil**/
					String fi="";String codArea1="";String codSubarea1="";String codCama1="";
					String cdx="";String sexo="";String edad="";String Serv="";	String aler="";
					String peso="";
					
					rs1=mvf.ObtenerDatosPerfilFarmaco(codAdm);
					if(rs1.next()){
						fi=rs1.getString(1);
						codArea1=rs1.getString(4);
						codSubarea1=rs1.getString(5);;
						codCama1=rs1.getString(6);
						cdx=rs1.getString(7);
						sexo=rs1.getString(2);
						if(sexo.equals("Masculino")){sexo="M";}
						if(sexo.equals("Femenino")){sexo="F";}
						edad=rs1.getString(3);
						Serv=rs1.getString(8);							
						rs2=mvf.ObtenerPesoTalla(codAdm);
						if(rs2.next()){
							//update del peso y la talla
							mvf.ActualizarPesoTalla(codAdm, Peso, Talla);
						}else{
							mvf.CrearPesoTalla(codAdm, Peso, Talla);
						}
						rs2.getStatement().getConnection().close();
					}
					rs1.getStatement().getConnection().close();
					//System.out.println(" 9 codAdm "+codAdm+" codPac "+codPac+" fi "+fi+" codArea1 "+codArea1+" codSubarea1 "+codSubarea1+" codCama1 "+codCama1+" cdx "+cdx+" aler "+aler+" sexo "+sexo);
				mvf.CrearPerfilft(codAdm,codPac,fi,null,codArea1,codSubarea1,codCama1,cdx,aler,sexo,edad,Peso,Talla);
				String cp="";
				rs4=mvf.ObtenerCodPerfilft(codAdm);
				if(rs4.next()){
					cp=rs4.getString(1);
				}
				rs4.getStatement().getConnection().close(); 
				//VIA=VIA INTRAVENOSA ETC.
				//FRECUENCIA=1 CADA 24 HORAS, ETC
				//CANTIDAD=ES LA CANTIDAD DE LA DOSIFICACION NO LA TOTAL.EJ:
				//DAR 2 CADA 8 HORAS.VIA ORAL, LA CANTIDAD=2
				String sep="";
				String sep1="";
				String sep2="";
				String sep3="";
				String sep4="";
				String sep5="";
				String sep6="";
				String sep7="";
				sep=dosificacion.replace(" ", "_");					
				int h=sep.split("_").length;
				String[] d=sep.split("_");		     	
				for(int g=0; g<h-1; g=g+1)
				{ 		
					sep1=d[0];
					sep2=d[1];
					sep3=d[2];
					sep4=d[3];
					sep5=d[4];
					sep6=d[5];
					sep7=d[6];
				}
				String via=sep7;
				String frecuencia=sep2+" "+sep3+" "+sep4+" "+sep5;					
				System.out.println(" 9 cp"+cp+" codigoMed"+codigoMed+" Codusuario "+Codusuario+" via "+via+" frecuencia "+frecuencia+" cantidad "+cantidad+" fecha "+fecha+" hora "+hora+" codFormulacion_fk "+codFormulacion_fk);
				rs=mvf.SoloMedicamento(codigoMed);
				if(rs.next()){
					mvf.CrearDetallePerfilft(cp,codigoMed,Codusuario,via,frecuencia,sep2,fecha+"",hora+"",DetFormu);	
				}
				rs.getStatement().getConnection().close();
				
				/**************************************************************************************/
				/*************************INICIO DE LA ORDEN DE PRODUCCION*****************************/
				/*1) CREAR LA ORDEN DE PRODUCCION POR DIA SE PUEDE TOMAR COMO PARA METRO DE REFERENCIA LA FECHA
			       EJ: SI EXISTE UN REGISTRO CON LA FECHA 2012-01-01 NO SE CREA SINO QUE SE ADJUNTA EL DETALLE
				   SI NO EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION, SE HACE EL INSERT EN LA TABLA.
				2) SI EXISTE EL REGISTRO EN LA TABLA FARC_ORDEN_PRODUCCION SE CONSULTA SI EL MEDICAMENTO PERTENECE
				   A LOS QUE ESTAN CREADOS EN LA TABLA farc_med_orden_produccion.
				   	SI EL MEDICAMENTO PERTENECE SE HACE EL INGRESO A LA TABLA farc_detalle_orden_produccion.
				   	SI NO SE OMITE LA INSERCION LA TABLA.*/
				rs=mvf.ObtenerMedicamentoParaOrden(codigoMed);
				if(rs.next()){
					/**************************************************************/
					String sepp="";String sep4p="";						
					sep=dosificacion.replace(" ", "_");					
					int hp=sepp.split("_").length;
					String[] dp=sepp.split("_");		     	
					for(int g=0; g<hp-1; g=g+1){
						sep4p=dp[3];
					}
					frecuencia=sep4p;	
					/**************************************************************/
					//String codSubarea1="";String codCama1="";						
					rs3=mvf.ObtenerDatosPerfilFarmaco(codAdm);
					if(rs3.next()){
						codSubarea1=rs3.getString(5);;
						codCama1=rs3.getString(6);
					}
					rs3.getStatement().getConnection().close();
					/**************************************************************/
					//el medicamento pertenece a orden.
					//buscamos si hay una orden creada para la fecha actual.
					rs1=mvf.ObtenerOrdenProduccion(fecha+"");
					if(rs1.next()){
						//si existe orden para la fecha actual.							
						//se crea el detalle de la orden de produccion.							
						//dosis=campo nuevo de la db
						//frecuencia= ej:dar 2 cada 12 hora se inserta solo el 12.
						mvf.CrearDetalleOrdenProduccion(rs1.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
					}else{
						//no existe orden para el dia actual se crea el registro.
						mvf.CrearOrdenProduccion(fecha+"", hora+"");
						//se consulta la orden de produccion creada.
						rs2=mvf.ObtenerOrdenProduccion(fecha+"");
						if(rs2.next()){
							//se crea el detalle de la orden de produccion.
							String horString = "";
							rs3=mvf.ObtenerCorteProduccion();
							if(rs3.next()){
								horString=rs3.getString(2);
							}
							rs3.getStatement().getConnection().close();
							String hora1=hora+"";
							String hora2=horString;
							String ho1 = null;
							String ho2 = null;
							String mo1 = null;
							String mo2 = null;
							int f1=hora1.split(":").length;
							String[] j1=hora1.split(":");		     	
							for(int g1=0; g1<f1-1; g1=g1+1){ho1=j1[0];mo1=j1[1];}
							int f2=hora2.split(":").length;
							String[] j2=hora2.split(":");		     	
							for(int g2=0; g2<f2-1; g2=g2+1)	{ho2=j2[0];mo2=j1[1];}
							if((Integer.parseInt(ho1))>(Integer.parseInt(ho2))){
								System.out.print("HO1="+ho1+" en mayor que HO2 "+ho2);
								
							}else{
								System.out.print("HO1="+ho1+" en menor que HO2 "+ho2);
								//aqui es donde se inserta//
								if((Integer.parseInt(mo1))>(Integer.parseInt(mo2))){
									
								}else{
									System.out.println(" Entra en Crear Detalle Orden Produccion.9");
									rs5=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
									if(rs5.next()){
										codDetFormulacion_fk=rs5.getString(1);
									}
									rs5.getStatement().getConnection().close();
									mvf.CrearDetalleOrdenProduccion(rs2.getString(1), rs.getString(1), codFormulacion_fk, codDetFormulacion_fk, codPac, codSubarea1, codCama1, CantDosis, frecuencia,Unidad);
								}
							}
							
						}
						rs2.getStatement().getConnection().close();
					}			
					rs1.getStatement().getConnection().close();
				}else{
					
				}
				rs.getStatement().getConnection().close();
				
				/**************************************************************************************/

				}					
				//////////FIN PERFIL FARMACOTERAPEUTICO//////////////

				/**************************************************************************************/
				
				
				rs1=mvf.ObtenerCodigoDetalleFormulacion(codFormulacion_fk, codigoMed);
				if(rs1.next()){
					codDetFormulacion_fk=rs1.getString(1);
				}
				
				mvf.CrearDispenacion(codDetFormulacion_fk);
				
				out.print(codFormulacion_fk);
				rs1.getStatement().getConnection().close();					
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("13")){
			mvf.ActualizarDetalleOrden(codFormulacion_fk, DetOrden);
		}
		
/***********************************************************************************************/	
		if(va.equals("0")){
			/**
			 * se obtienen las areas del formato llevando como parametro el codigo del formato. 
			 */
			rs=mvf.ObtenerAreaFormato2(CodFormato,FechaFormato,HoraFormato,CodAdm);
			try {
				out.println("<table width='100%'class='style2' border='0'><tr><td></td></tr>");
				while(rs.next()){
					String NombArea=rs.getString(2);				
					out.println("<tr><td><a href='#'onclick='mostarpreguntas("+rs.getString(1)+")'>"+NombArea+"</a><input name='HoraFormato' type='hidden' id='HoraFormato' value='"+rs.getString(3)+"' ><input name='FechaFormato' type='hidden' id='FechaFormato' value='"+rs.getString(4)+"' ><input name='CodArea' type='hidden' id='CodArea' value='"+rs.getString(1)+"' ><input name='CodFormato' type='hidden' id='CodFormato' value='"+CodFormato+"' ></td></tr>");
				}
				out.println("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error ResultSet valor=0 "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("1")){
			/**
			 * se obtienen las preguntas del area llevando como parametro el codigo del area. 
			 */
			String usuario="";
			String estadoPregunta="";
			/*****************************************************************//*****************************************************************/
			if((CodUsuario.equals("174"))||(CodUsuario.equals("258"))){
				String CodForPac="";
				//out.print("entro a la validacion de los usuarios");
				try {
					rs=mvf.ObtenerPreguntasAreaLlenoM(CodArea, FechaFormato, HoraFormato,CodAdm);
					out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
					while(rs.next()){
						CodForPac=rs.getString(7);
						rs2=mlh.BuscarCoFirmaFormato(CodForPac);
						String CodTipoResp=rs.getString(3);
						if(rs2.next()){
							out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
							if(rs.getString(2).equals("1")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
								 */
							out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' readonly='' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
							}
							contador=contador+1;
							if(rs.getString(2).equals("2")){
								/**
								 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
								 */
								rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
								out.print("<td><select name='txtRespuesta' id='txtRespuesta' disabled='disabled'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
								while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(1)+"</option>");
								
								}
								rs1.getStatement().getConnection().close();
								out.print("</select></td>");
							}
							if(rs.getString(2).equals("3")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto corto
								 */
								out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' readonly='' value='"+rs.getString(5)+"'   > "+rs.getString(6)+"</td>");
							}
							if(rs.getString(2).equals("4")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								//out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								out.print("<tr><td><label>"+rs.getString(5)+"<input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
							
							if(rs.getString(2).equals("5")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								//out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								out.print("<tr><td><label>"+rs.getString(5)+"<input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
						}else{
						out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
						if(rs.getString(2).equals("1")){
							/**
							 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
							**/						
							String esul=rs.getString(5);
							out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onblur='ActualizarResultados()' >"+esul+"</textarea> "+rs.getString(6)+"</td></tr>");
						}
						contador=contador+1;
						if(rs.getString(2).equals("2")){
							/**
							 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
							 */
							rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
							out.print("<td><select name='txtRespuesta' id='txtRespuesta' onblur='ActualizarResultados()'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
							while(rs1.next()){
							out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(1)+"</option>");	
							}
							rs1.getStatement().getConnection().close();
							out.print("</select></td></tr>");
						}
						if(rs.getString(2).equals("3")){
							/**
							 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto corto
							 */
							out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onblur='ActualizarResultados()' value='"+rs.getString(5)+"' > "+rs.getString(6)+"</td></tr>");
						}
						if(rs.getString(2).equals("4")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 *PARA EL DIAGNOSTICO DE INGRESO DE LA HISTORIA CLINICA DE URGENCIA.
							 */
							/**
							 * AQUI SE VA PARA INGRESAR VARIOS DIAGNOSTICOS.
							 * **/
							String Resultado="";
							Resultado=rs.getString(5);
							out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicial()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' value='"+Resultado+"' ></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							out.print("<table><tr><td><div id='DiagnosticoIniciales'>"+Resultado+"</div></td></tr></table>");
							
						}
						
						if(rs.getString(2).equals("5")){
							/**
							 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
							 *
							 *
							 *
							 * AQUI SE VA PARA INGRESAR VARIOS DIAGNOSTICOS.
							 * **/
							String Resultado="";
							Resultado=rs.getString(5);
							out.print("<td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoSolo()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' value='"+Resultado+"' ><input name='txtCodResultado5' type='hidden' id='txtCodResultado5' value="+rs.getString(4)+" ><br><div id='SugeDiagnostico'></div><div id='DiagnosticoIniciales'>"+Resultado+"</div></label></td></tr>");
						}
						}
						rs2.getStatement().getConnection().close();
					}
					out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("Error ResultSet valor=1 "+e);
					e.printStackTrace();
				}
/**valida usu**/}else
			try {
				rs4=mvf.ObtenerUsuarioFormato(CodArea, FechaFormato, HoraFormato, CodUsuario,CodAdm);
				if(rs4.next()){
					usuario=rs4.getString(1);
					estadoPregunta=rs4.getString(2);
				}
				if((usuario=="")&&(estadoPregunta.equals(""))){
					try {
						rs=mvf.ObtenerPreguntasAreaLleno(CodArea, FechaFormato, HoraFormato,CodAdm);
						out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
						while(rs.next()){
							String CodTipoResp=rs.getString(3);		
							out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
							if(rs.getString(2).equals("1")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
								 */
							out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' readonly='' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
							}
							contador=contador+1;
							if(rs.getString(2).equals("2")){
								/**
								 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
								 */
								rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
								out.print("<td><select name='txtRespuesta' id='txtRespuesta' disabled='disabled'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
								while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(1)+"</option>");
								
								}
								rs1.getStatement().getConnection().close();
								out.print("</select></td>");
							}
							if(rs.getString(2).equals("3")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto corto
								 */
								out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' readonly='' value='"+rs.getString(5)+"'   > "+rs.getString(6)+"</td>");
							}
							if(rs.getString(2).equals("4")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								//out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								out.print("<tr><td><label>"+rs.getString(5)+"<input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
							
							if(rs.getString(2).equals("5")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								//out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								out.print("<tr><td><label>"+rs.getString(5)+"<input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
						}
						out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("Error ResultSet valor=1 "+e);
						e.printStackTrace();
					}
				}			
				if((usuario!="")&&(estadoPregunta.equals("1"))){
					try {
						rs=mvf.ObtenerPreguntasAreaLleno(CodArea, FechaFormato, HoraFormato,CodAdm);
						out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
						while(rs.next()){
							String CodTipoResp=rs.getString(3);
							out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
							if(rs.getString(2).equals("1")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
								 */
							out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' readonly='' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
							}
							contador=contador+1;
							if(rs.getString(2).equals("2")){
								/**
								 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
								 */
								rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
								out.print("<td><select name='txtRespuesta' id='txtRespuesta' disabled='disabled'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
								while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(1)+"</option>");	
								}
								rs1.getStatement().getConnection().close();
								out.print("</select></td>");
							}
							if(rs.getString(2).equals("3")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto corto
								 */
								out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta'  readonly=''  value='"+rs.getString(5)+"'  > "+rs.getString(6)+"</td>");
							}
							if(rs.getString(2).equals("4")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								out.print("<tr><td><label>"+rs.getString(5)+"<input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
							if(rs.getString(2).equals("5")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								//out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' value='"+rs.getString(5)+"' readonly=''  /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								out.print("<tr><td><label>"+rs.getString(5)+"<input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
							}
						}
						out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("Error ResultSet valor=1 "+e);
						e.printStackTrace();
					}
				}
				if((usuario!="")&&(estadoPregunta.equals("0"))){
					try {
						rs=mvf.ObtenerPreguntasAreaLleno(CodArea, FechaFormato, HoraFormato,CodAdm);
						out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
						while(rs.next()){
							String CodTipoResp=rs.getString(3);
							out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
							if(rs.getString(2).equals("1")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto amplio
								**/
							
								String esul=rs.getString(5);
							//out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='3' onkeyup='ActualizarResultados()' >"+esul+"</textarea> "+rs.getString(6)+"</td></tr>");
								out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onblur='ActualizarResultados()' >"+esul+"</textarea> "+rs.getString(6)+"</td></tr>");
							}
							contador=contador+1;
							if(rs.getString(2).equals("2")){
								/**
								 * se obtienen las preguntas que son tipo cerradas como tipo de respuestas con un combobox con las opciones que las respuestas tiene.
								 */
								rs1=mvf.OpcionesTipoRespuesta2(CodTipoResp);
								//out.print("<td><select name='txtRespuesta' id='txtRespuesta' onchange='ActualizarResultados()'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
								out.print("<td><select name='txtRespuesta' id='txtRespuesta' onblur='ActualizarResultados()'><option value='"+rs.getString(5)+"' selected>"+rs.getString(5)+"</option>");
								while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(1)+"</option>");	
								}
								rs1.getStatement().getConnection().close();
								out.print("</select></td></tr>");
							}
							if(rs.getString(2).equals("3")){
								/**
								 * se llenan las preguntas que son tipo abiertas como tipo de respuestas con un campo de texto corto
								 */
								//out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onkeyup='ActualizarResultados()' value="+rs.getString(5)+" > "+rs.getString(6)+"</td>");
								out.println("<td width='82%'><input name='txtRespuesta' type='text' id='txtRespuesta' onblur='ActualizarResultados()' value='"+rs.getString(5)+"' > "+rs.getString(6)+"</td></tr>");
							}
							if(rs.getString(2).equals("4")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								/**
								 * AQUI SE VA PARA INGRESAR VARIOS DIAGNOSTICOS.
								 * **/
								String Resultado="";
								Resultado=rs.getString(5);
								out.print("<tr><td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoInicial()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' value='"+Resultado+"' ></label></td></tr><tr><td><div id='SugeDiagnostico'></div></td></tr>");
								out.print("<table><tr><td><div id='DiagnosticoIniciales'>"+Resultado+"</div></td></tr></table>");
								
							}
							
							if(rs.getString(2).equals("5")){
								/**
								 * se llenan las preguntas abiertas que son de tipo autocompletar en este caso es diagnostico.
								 */
								/**
								 * AQUI SE VA PARA INGRESAR VARIOS DIAGNOSTICOS.
								 * **/
								String Resultado="";
								Resultado=rs.getString(5);
								out.print("<td><label><input name='txtNomDiagnos' type='text' id='txtNomDiagnos' size='80' onkeyup='autocompletarCIE10()'  /><input name='btnAsignarDiagnostico' type='button' id='btnAsignarDiagnostico' value='     Asignar     ' onclick='IngresarDiagnosticoSolo()' /><input name='txtCodDiagnostico' type='hidden' id='txtCodDiagnostico' /><input name='txtResult1' type='hidden' id='txtResult1' value='"+Resultado+"' ><input name='txtCodResultado5' type='hidden' id='txtCodResultado5' value="+rs.getString(4)+" ><br><div id='SugeDiagnostico'></div><div id='DiagnosticoIniciales'>"+Resultado+"</div></label></td></tr>");
								//out.print("<tr><td><div id='DiagnosticoIniciales'>"+Resultado+"</div></td></tr></table>");
								
							}
						}
						out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
						
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						System.out.println("Error ResultSet valor=1 "+e);
						e.printStackTrace();
					}
					rs4.getStatement().getConnection().close();
				}
			} catch (SQLException e1) {	
				e1.printStackTrace();
			}
			/*****************************************************************//*****************************************************************/
			
		}
	
		if(va.equals("2")){
			String NomCompleto="";
			String Genero="";
			String TipoDoc="";
			String NumDocu="";
			String Edad="";
			rs=mvf.ObtenerPaciente(CodPac);
			try {
				if(rs.next()){
					 NomCompleto=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
					 Genero=rs.getString(4);
					 TipoDoc=rs.getString(5);
					 NumDocu=rs.getString(6);
					 Edad=rs.getString(7);
				}
				out.print(NomCompleto+"|"+Genero+"|"+TipoDoc+"|"+NumDocu+"|"+Edad);
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		}
	
	
		if(va.equals("3")){
			/**
			 * para llenar los examanes k se ha hecho el paciente en el formato
			 * que salga enseguida se carge la historia clinica
			 */
			//ResultSet rs2=null;
			String edad="";
			String genero="";
			rs2=mlh.Busedadygene(CodPac);
			  try {
				if(rs2.next()){
					  edad=rs2.getString(2);
					  genero=rs2.getString(1);
				  }
				rs2.getStatement().getConnection().close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
	
			rs3=mlh.Buscacodge(genero);
			String codge="";
			try {
				if(rs3.next()){
					codge=rs3.getString(1);
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			rs=mlh.ExamenTexto(CodPac);
			rs1=mlh.ExamenRango(CodPac,codge);
			//String nombre="";
			//String apellidos="";
			ResultSet pa1=null;
			pa1=mlh.Busedadygene(CodPac);
			try {
				if(pa1.next()){
					//nombre=pa1.getString(3);
					//apellidos=pa1.getString(4);
				}
				pa1.getStatement().getConnection().close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}		
			 out.print("<table width='100%'>");
			try {
				out.print("<tr>");
				while(rs.next()){
					String ano=rs.getString(4).substring(0,4);
					String mes=rs.getString(4).substring(5,7);
					String dia=rs.getString(4).substring(8,10); 
					String hora=rs.getString(5).substring(0,2);
					String minuto=rs.getString(5).substring(3,5);
					String segundo=rs.getString(5).substring(6,8);
					out.print("<tr><td colspan='2'><div>"+rs.getString(4)+"/"+rs.getString(5)+"</div></td><td colspan='2'><div><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+1+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+")'>"+rs.getString(6)+"</a></div></td></tr>");	
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				while(rs1.next()){
					String ano=rs1.getString(4).substring(0,4);
					String mes=rs1.getString(4).substring(5,7);
					String dia=rs1.getString(4).substring(8,10);
					String hora=rs1.getString(5).substring(0,2);
					String minuto=rs1.getString(5).substring(3,5);
					String segundo=rs1.getString(5).substring(6,8);
					out.print("<tr><td colspan='2'><div>"+rs1.getString(4)+"/"+rs1.getString(5)+"</div></td><td colspan='2'><div><a  href='#'onclick='Abrir_ventana("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+2+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(8)+","+rs1.getString(9)+")'>"+rs1.getString(6)+"</a></div></td></tr>");	
				}				
				rs1.getStatement().getConnection().close();			
				
				ResultSet rsgrupo=null;
				rsgrupo=mlh.Examen(CodPac);
				ResultSet redad=null;
				ResultSet rgene=null;
				redad=mlh.Buscaedad(CodPac);
				//String edad2="";
				//String genero1="";
				String codgenero="";
				try {
					if(redad.next()){
						//edad2=redad.getString(1);
						//genero1=redad.getString(2);
					}
					redad.getStatement().getConnection().close();
					rgene=mlh.Buscacodge(genero);
					if(rgene.next()){
						codgenero=rgene.getString(1);
					}
					rgene.getStatement().getConnection().close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				try {
					while(rsgrupo.next()){
						String ano=rsgrupo.getString(4).substring(0,4);
						String mes=rsgrupo.getString(4).substring(5,7);
						String dia=rsgrupo.getString(4).substring(8,10);
						String hora=rsgrupo.getString(5).substring(0,2);
						String minuto=rsgrupo.getString(5).substring(3,5);
						String segundo=rsgrupo.getString(5).substring(6,8);
						out.print("<tr><td colspan='2'><div>"+rsgrupo.getString(4)+"/"+rsgrupo.getString(5)+"</div></td><td colspan='2'><div><a  href='#'onclick='Abrir("+ano+","+mes+","+dia+","+hora+","+minuto+","+segundo+","+rsgrupo.getString(9)+","+rsgrupo.getString(10)+","+edad+","+codgenero+")'>"+rsgrupo.getString(6)+"</a></div></td></tr>");	
					}
					out.print("</table>");
					rsgrupo.getStatement().getConnection().close();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			} catch (SQLException e1) {
	
				e1.printStackTrace();
			}
			out.println("</table>");  
			
		}
	
		if(va.equals("4")){
	
			rs4=mlh.HistorialDeImagenologia(CodPac);
			out.print("<table width='100%'>");
			try {
				while(rs4.next()){
					out.print("<tr style='font-size:9px'><td width='40%'>"+rs4.getString(1)+"/"+rs4.getString(2)+"</td><td width='60%'><a  href='#'onclick='mostarexamenes("+rs4.getString(4)+","+rs4.getString(5)+")'>"+rs4.getString(3)+"</a></td></tr>");
				}
				out.print("</table>");
				rs4.getStatement().getConnection().close();
			} catch (SQLException e) {
					out.println("ERROR EN VA EQUAL 4 CONTROLVERFORMATOS "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("5")){			
			try {
				rs=mvf.BuscarAtencionesAnterioresHospitalizacion(CodPac);
				out.print("<table width='100%' class='style6' border='1'><tr><td width='100%'><div align='center' class='style11' id='cabecera2'>Atenciones Anteriores </div></td></tr><tr><td><table width='100%' border='1'><tr><td width='17%'><table width='100%' border='1'><tr><td><div align='center'>Hospitalizacion</div></td></tr>");
				while(rs.next()){
					out.print("<tr><td><a href='#'onclick='MostrarAtencion("+rs.getString(1)+")'>"+rs.getString(2)+"</a></td></tr>");	
				}
				rs.getStatement().getConnection().close();
				out.print("</table></td><td width='18%'><table width='100%' border='1'><tr><td><div align='center'>Consulta Externa </div></td></tr>");
				rs1=mvf.BuscarAtencionesAnterioresConsultaExterna(CodPac);				
				while(rs1.next()){
					out.print("<tr><td><a href='#'onclick='MostrarAtencionCE("+rs1.getString(3)+")'>"+rs1.getString(2)+"</a></td></tr>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</table></td><td width='18%'><table width='100%' border='1'><tr><td><div align='center'>Epicrisis</div></td></tr>");
				rs2=mvf.BuscarHistorialEpicrisis(CodPac);
				while(rs2.next()){
					out.print("<tr><td><a href='#'onclick='ImprimirEpicrisis("+rs2.getString(1)+")'>"+rs2.getString(2)+"</a></td></tr>");
				}
				rs2.getStatement().getConnection().close();
				out.print("</table></td><td width='65%'><table width='100%' border='1'><tr><td><div id='FormatosAnteriores'>Aqui Van Los Formatos de la Fecha Seleccionada.</div></td></tr></table></td></tr></table></td></tr></table>");
				
			} catch (SQLException e) {
				out.print("Error en ControlVerFormatos Va=5 "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("5.1")){
			try {
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td width='32%'>Nombre Del Formato </td><td width='20%'>Fecha y Hora</td><td width='29%'>Usuario</td><td width='19%'>Acciones de Formato</td></tr>");
				rs=mvf.FormatosAtencionesAnterioresHospitalizacion(CodAdm);
				while(rs.next()){
					String FechaIni=rs.getString(5);
					String HoraIni=rs.getString(6);
					String dia,mes,anio=null; 
					String horas,minutos,segundos=null;
					  
					dia=FechaIni.substring(8,10);
					mes=FechaIni.substring(5,7);
					anio=FechaIni.substring(0,4);
					
					horas=HoraIni.substring(0,2);
					minutos =HoraIni.substring(3,5);
					segundos=HoraIni.substring(6,8);
					
					out.print("<tr class='style6'><td>"+rs.getString(2)+"</td><td>"+rs.getString(5)+"/"+rs.getString(6)+"</td><td>"+rs.getString(9)+" "+rs.getString(10)+"</td><td><img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormato("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(3)+","+CodAdm+")' style='cursor:pointer;' /></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en ControlVerFormatos Va=5.1 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("5.2")){
			String CodHorarioMedico=req.getParameter("CodHorarioMedico");
			try {
				out.print("<table width='100%' class='style6' border='1' cellspacing='0' ><tr bgcolor='#DADADA'><td width='32%'>Nombre Del Formato </td><td width='20%'>Fecha y Hora</td><td width='29%'>Usuario</td><td width='19%'>Acciones de Formato</td></tr>");
				rs=mvf.FormatosAtencionesAnterioresConsultaExterna(CodHorarioMedico);
				while(rs.next()){
					String FechaIni=rs.getString(5);
					String HoraIni=rs.getString(6);
					String dia,mes,anio=null; 
					String horas,minutos,segundos=null;
					  
					dia=FechaIni.substring(8,10);
					mes=FechaIni.substring(5,7);
					anio=FechaIni.substring(0,4);
					
					horas=HoraIni.substring(0,2);
					minutos =HoraIni.substring(3,5);
					segundos=HoraIni.substring(6,8);
					
					out.print("<tr class='style6'><td>"+rs.getString(2)+"</td><td>"+rs.getString(5)+"/"+rs.getString(6)+"</td><td>"+rs.getString(9)+" "+rs.getString(10)+"</td><td><img title='Imprimir' name='btnImprimirFormato' src='Imagenes/imprimir.JPG' id='btnImprimirFormato' onclick='ImprimirFormatoCE("+rs.getString(7)+","+rs.getString(1)+","+anio+","+mes+","+dia+","+horas+","+minutos+","+segundos+","+rs.getString(8)+","+rs.getString(3)+","+CodHorarioMedico+")' style='cursor:pointer;' /></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en ControlVerFormatos Va=5.2 "+e);
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		if(va.equals("VerVitales")){
			
			/* Calendar c1 = GregorianCalendar.getInstance();
		        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        sdf = new SimpleDateFormat("yyyy-MM-dd");
		        c1.add(Calendar.DATE, 1);*/
			/////////////////////////////////////////////////////////////////////////////////////////
		
			////////////////////////////////////////////////////////////////////////////////////////
			out.print("<table width='100%' border='1'>");
			out.print("<tr><td id='DetalleKardex'>");
			
			java.util.Calendar hoy = java.util.Calendar.getInstance();
			java.util.Date primerDia= hoy.getTime();
			int diasMes[]= new int[12];
			diasMes[0] = 31;diasMes[1] = 28;diasMes[2] = 31;diasMes[3] = 30;diasMes[4] = 31;diasMes[5] = 30;
			diasMes[6] = 31;diasMes[7] = 31;diasMes[8] = 30;diasMes[9] = 31;diasMes[10] = 30;diasMes[11] = 31;

			int dia;int anio;int columna;int nDias;int diaInicial;int i;int mes;
			String NombreMes = "";
			anio=hoy.get(java.util.Calendar.YEAR);
			dia=hoy.get(java.util.Calendar.DATE);
			mes=hoy.get(java.util.Calendar.MONTH);
			mes=mes+1;
			if(mes==1){NombreMes="Enero";}if(mes==2){NombreMes="Febrero";}if(mes==3){NombreMes="Marzo";}
			if(mes==4){NombreMes="Abril";}if(mes==5){NombreMes="Mayo";}if(mes==6){NombreMes="Junio";}
			if(mes==7){NombreMes="Julio";}if(mes==8){NombreMes="Agosto";}if(mes==9){NombreMes="Septiembre";}
			if(mes==10){NombreMes="Octubre";}if(mes==11){NombreMes="Noviembre";}if(mes==12){NombreMes="Diciembre";}

			if(((anio%4==0) && (anio%100!=0))||(anio%400==0)) 
			{
				diasMes[1] = 29; //y si lo es var�o el n�mero de d�as del mes de febrero en el array
			}
			nDias=diasMes[hoy.get(java.util.Calendar.MONTH)];		
			primerDia.setDate(1);
			diaInicial=primerDia.getDay();
			
			out.print("<table width='100%' border='1' >");
			out.print("<tr><th colspan=41 align=center>"+NombreMes+" Del "+anio+" "); 		
			out.print("<tr bgcolor='#CCCCCC'>");
			out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
			out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
			out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
			out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
			out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
			out.print("<th align=center>L");out.print("<th align=center>M");out.print("<th align=center>M");
			out.print("<th align=center>J");out.print("<th align=center>V");out.print("<th align=center>S");
			out.print("<th align=center>D");out.print("<th align=center>L");out.print("<th align=center>M");
			out.print("<th align=center>M");out.print("<th align=center>J");out.print("<th align=center>V");
			out.print("<th align=center>S");out.print("<th align=center>D");out.print("<th align=center>L");
			out.print("<th align=center>M");out.print("<th align=center>M");out.print("<th align=center>J");
			out.print("<th align=center>V");out.print("<th align=center>S");out.print("<th align=center>D");
			out.print("<th align=center>L");
			out.print("</tr>");
			out.print("<tr></tr>");
			columna=0;
			for(i=0;i<diaInicial;i++) 
			{
				out.print("<td align=center><font size+=4>.");
				columna++;
				out.print("</font>");
			}
			int a=0;
			for(i=1;i<=nDias;i++) 
			{
				a=a+1;
				out.print("<td align=center>");				
				if(i==dia) 
				{
					String valueF=anio+"-"+mes+"-"+i;
					out.print("<b>"+i+"</b>");
					out.print("<font color=\"#ff0000\" size+=7><b>");
					out.print("<input name='radiobutto' type='radio' value='radiobutto' checked='true' id='f' onclick='RadioForUSV(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
					out.print("</b></font>"); 
				}else{
					String valueF=anio+"-"+mes+"-"+i;
					out.print(i);
					out.print("<input name='radiobutto' type='radio' value='radiobutto' id='f' onclick='RadioForUSV(&quot;"+valueF+"&quot;,"+CodAdmK+")' />");
				}
			}
			out.print("</table>");
			out.print("<table width='100%' border='1' >" +
					"<tr><td><input type='hidden' id='txtAdmisionCKR' value="+CodAdmK+" ></td></tr>" +
							"<tr><td id='CargSigVit'>");
			/*out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
			out.print("<tr><td width='11%'>HORA DE TOMA </td>" +
					"<td width='14%'>TALLA</td>" +
					"<td width='15%'>PESO</td>" +
					"<td width='15%'>T.A</td>" +
					"<td width='15%'>F.C</td>" +
					"<td width='15%'>F.R</td>" +
					"<td width='15%'>PULSO</td>" +
					"<td>Guardar</td></tr>");*/
			
			String CodAdmSV=req.getParameter("CodAdmK");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fechaInsertSV = new java.sql.Date(fechaActual.getTime());	
			 Calendar c1 = GregorianCalendar.getInstance();
		        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        sdf = new SimpleDateFormat("yyyy-MM-dd");
		        c1.add(Calendar.DATE, 1);
			  
			/*out.print("  <tr><td>07 AM </td><td><input name='txtTalla' type='text' id='txtTalla7'></td><td><input name='txtPeso' type='text' id='txtPeso7'></td><td><input name='txtTA' type='text' id='txtTA7'></td><td><input name='txtFC' type='text' id='txtFC7'></td><td><input name='txtFR' type='text' id='txtFR7'></td><td><input name='txtPulso' type='text' id='txtPulso7'></td><td><a href='#' onclick='GuardarSV(7)'>Guardar</a></td></tr>");
			out.print("  <tr><td>08 AM </td><td><input name='txtTalla' type='text' id='txtTalla8'></td><td><input name='txtPeso' type='text' id='txtPeso8'></td><td><input name='txtTA' type='text' id='txtTA8'></td><td><input name='txtFC' type='text' id='txtFC8'></td><td><input name='txtFR' type='text' id='txtFR8'></td><td><input name='txtPulso' type='text' id='txtPulso8'></td><td><a href='#' onclick='GuardarSV(8)'>Guardar</a></td></tr>");
			out.print("  <tr><td>09 AM </td><td><input name='txtTalla' type='text' id='txtTalla9'></td><td><input name='txtPeso' type='text' id='txtPeso9'></td><td><input name='txtTA' type='text' id='txtTA9'></td><td><input name='txtFC' type='text' id='txtFC9'></td><td><input name='txtFR' type='text' id='txtFR9'></td><td><input name='txtPulso' type='text' id='txtPulso9'></td><td><a href='#' onclick='GuardarSV(9)'>Guardar</a></td></tr>");
			out.print("  <tr><td>10 AM </td><td><input name='txtTalla' type='text' id='txtTalla10'></td><td><input name='txtPeso' type='text' id='txtPeso10'></td><td><input name='txtTA' type='text' id='txtTA10'></td><td><input name='txtFC' type='text' id='txtFC10'></td><td><input name='txtFR' type='text' id='txtFR10'></td><td><input name='txtPulso' type='text' id='txtPulso10'></td><td><a href='#' onclick='GuardarSV(10)'>Guardar</a></td></tr>");
			out.print("  <tr><td>11 AM </td><td><input name='txtTalla' type='text' id='txtTalla11'></td><td><input name='txtPeso' type='text' id='txtPeso11'></td><td><input name='txtTA' type='text' id='txtTA11'></td><td><input name='txtFC' type='text' id='txtFC11'></td><td><input name='txtFR' type='text' id='txtFR11'></td><td><input name='txtPulso' type='text' id='txtPulso11'></td><td><a href='#' onclick='GuardarSV(11)'>Guardar</a></td></tr>");
			out.print("  <tr><td>12 PM </td><td><input name='txtTalla' type='text' id='txtTalla12'></td><td><input name='txtPeso' type='text' id='txtPeso12'></td><td><input name='txtTA' type='text' id='txtTA12'></td><td><input name='txtFC' type='text' id='txtFC12'></td><td><input name='txtFR' type='text' id='txtFR12'></td><td><input name='txtPulso' type='text' id='txtPulso12'></td><td><a href='#' onclick='GuardarSV(12)'>Guardar</a></td></tr>");
			out.print("  <tr><td>01 PM </td><td><input name='txtTalla' type='text' id='txtTalla13'></td><td><input name='txtPeso' type='text' id='txtPeso13'></td><td><input name='txtTA' type='text' id='txtTA13'></td><td><input name='txtFC' type='text' id='txtFC13'></td><td><input name='txtFR' type='text' id='txtFR13'></td><td><input name='txtPulso' type='text' id='txtPulso13'></td><td><a href='#' onclick='GuardarSV(13)'>Guardar</a></td></tr>");
			out.print("  <tr><td>02 PM </td><td><input name='txtTalla' type='text' id='txtTalla14'></td><td><input name='txtPeso' type='text' id='txtPeso14'></td><td><input name='txtTA' type='text' id='txtTA14'></td><td><input name='txtFC' type='text' id='txtFC14'></td><td><input name='txtFR' type='text' id='txtFR14'></td><td><input name='txtPulso' type='text' id='txtPulso14'></td><td><a href='#' onclick='GuardarSV(14)'>Guardar</a></td></tr>");
			out.print("  <tr><td>03 PM </td><td><input name='txtTalla' type='text' id='txtTalla15'></td><td><input name='txtPeso' type='text' id='txtPeso15'></td><td><input name='txtTA' type='text' id='txtTA15'></td><td><input name='txtFC' type='text' id='txtFC15'></td><td><input name='txtFR' type='text' id='txtFR15'></td><td><input name='txtPulso' type='text' id='txtPulso15'></td><td><a href='#' onclick='GuardarSV(15)'>Guardar</a></td></tr>");
			out.print("  <tr><td>04 PM </td><td><input name='txtTalla' type='text' id='txtTalla16'></td><td><input name='txtPeso' type='text' id='txtPeso16'></td><td><input name='txtTA' type='text' id='txtTA16'></td><td><input name='txtFC' type='text' id='txtFC16'></td><td><input name='txtFR' type='text' id='txtFR16'></td><td><input name='txtPulso' type='text' id='txtPulso16'></td><td><a href='#' onclick='GuardarSV(16)'>Guardar</a></td></tr>");
			out.print("  <tr><td>05 PM </td><td><input name='txtTalla' type='text' id='txtTalla17'></td><td><input name='txtPeso' type='text' id='txtPeso17'></td><td><input name='txtTA' type='text' id='txtTA17'></td><td><input name='txtFC' type='text' id='txtFC17'></td><td><input name='txtFR' type='text' id='txtFR17'></td><td><input name='txtPulso' type='text' id='txtPulso17'></td><td><a href='#' onclick='GuardarSV(17)'>Guardar</a></td></tr>");
			out.print("  <tr><td>06 PM </td><td><input name='txtTalla' type='text' id='txtTalla18'></td><td><input name='txtPeso' type='text' id='txtPeso18'></td><td><input name='txtTA' type='text' id='txtTA18'></td><td><input name='txtFC' type='text' id='txtFC18'></td><td><input name='txtFR' type='text' id='txtFR18'></td><td><input name='txtPulso' type='text' id='txtPulso18'></td><td><a href='#' onclick='GuardarSV(18)'>Guardar</a></td></tr>");
			out.print("  <tr><td>07 PM </td><td><input name='txtTalla' type='text' id='txtTalla19'></td><td><input name='txtPeso' type='text' id='txtPeso19'></td><td><input name='txtTA' type='text' id='txtTA19'></td><td><input name='txtFC' type='text' id='txtFC19'></td><td><input name='txtFR' type='text' id='txtFR19'></td><td><input name='txtPulso' type='text' id='txtPulso19'></td><td><a href='#' onclick='GuardarSV(19)'>Guardar</a></td></tr>");
			out.print("  <tr><td>08 PM </td><td><input name='txtTalla' type='text' id='txtTalla20'></td><td><input name='txtPeso' type='text' id='txtPeso20'></td><td><input name='txtTA' type='text' id='txtTA20'></td><td><input name='txtFC' type='text' id='txtFC20'></td><td><input name='txtFR' type='text' id='txtFR20'></td><td><input name='txtPulso' type='text' id='txtPulso20'></td><td><a href='#' onclick='GuardarSV(20)'>Guardar</a></td></tr>");
			out.print("  <tr><td>09 PM </td><td><input name='txtTalla' type='text' id='txtTalla21'></td><td><input name='txtPeso' type='text' id='txtPeso21'></td><td><input name='txtTA' type='text' id='txtTA21'></td><td><input name='txtFC' type='text' id='txtFC21'></td><td><input name='txtFR' type='text' id='txtFR21'></td><td><input name='txtPulso' type='text' id='txtPulso21'></td><td><a href='#' onclick='GuardarSV(21)'>Guardar</a></td></tr>");
			out.print("  <tr><td>10 PM </td><td><input name='txtTalla' type='text' id='txtTalla22'></td><td><input name='txtPeso' type='text' id='txtPeso22'></td><td><input name='txtTA' type='text' id='txtTA22'></td><td><input name='txtFC' type='text' id='txtFC22'></td><td><input name='txtFR' type='text' id='txtFR22'></td><td><input name='txtPulso' type='text' id='txtPulso22'></td><td><a href='#' onclick='GuardarSV(22)'>Guardar</a></td></tr>");
			out.print("  <tr><td>11 PM </td><td><input name='txtTalla' type='text' id='txtTalla23'></td><td><input name='txtPeso' type='text' id='txtPeso23'></td><td><input name='txtTA' type='text' id='txtTA23'></td><td><input name='txtFC' type='text' id='txtFC23'></td><td><input name='txtFR' type='text' id='txtFR23'></td><td><input name='txtPulso' type='text' id='txtPulso23'></td><td><a href='#' onclick='GuardarSV(23)'>Guardar</a></td></tr>");
			out.print("	 <tr><td>12 AM </td><td><input name='txtTalla' type='text' id='txtTalla0'></td><td><input name='txtPeso' type='text' id='txtPeso0'></td><td><input name='txtTA' type='text' id='txtTA0'></td><td><input name='txtFC' type='text' id='txtFC0'></td><td><input name='txtFR' type='text' id='txtFR0'></td><td><input name='txtPulso' type='text' id='txtPulso0'></td><td><a href='#' onclick='GuardarSV(0)'>Guardar</a></td></tr>");
			out.print("	 <tr><td>01 AM </td><td><input name='txtTalla' type='text' id='txtTalla1'></td><td><input name='txtPeso' type='text' id='txtPeso1'></td><td><input name='txtTA' type='text' id='txtTA1'></td><td><input name='txtFC' type='text' id='txtFC1'></td><td><input name='txtFR' type='text' id='txtFR1'></td><td><input name='txtPulso' type='text' id='txtPulso1'></td><td><a href='#' onclick='GuardarSV(1)'>Guardar</a></td></tr>");
			out.print("	 <tr><td>02 AM </td><td><input name='txtTalla' type='text' id='txtTalla2'></td><td><input name='txtPeso' type='text' id='txtPeso2'></td><td><input name='txtTA' type='text' id='txtTA2'></td><td><input name='txtFC' type='text' id='txtFC2'></td><td><input name='txtFR' type='text' id='txtFR2'></td><td><input name='txtPulso' type='text' id='txtPulso2'></td><td><a href='#' onclick='GuardarSV(2)'>Guardar</a></td></tr>");
			out.print("	 <tr><td>03 AM </td><td><input name='txtTalla' type='text' id='txtTalla3'></td><td><input name='txtPeso' type='text' id='txtPeso3'></td><td><input name='txtTA' type='text' id='txtTA3'></td><td><input name='txtFC' type='text' id='txtFC3'></td><td><input name='txtFR' type='text' id='txtFR3'></td><td><input name='txtPulso' type='text' id='txtPulso3'></td><td><a href='#' onclick='GuardarSV(3)'>Guardar</a></td></tr>");
			out.print("	 <tr><td>04 AM </td><td><input name='txtTalla' type='text' id='txtTalla4'></td><td><input name='txtPeso' type='text' id='txtPeso4'></td><td><input name='txtTA' type='text' id='txtTA4'></td><td><input name='txtFC' type='text' id='txtFC4'></td><td><input name='txtFR' type='text' id='txtFR4'></td><td><input name='txtPulso' type='text' id='txtPulso4'></td><td><a href='#' onclick='GuardarSV(4)'>Guardar</a></td></tr>");
			out.print("	 <tr><td>05 AM </td><td><input name='txtTalla' type='text' id='txtTalla5'></td><td><input name='txtPeso' type='text' id='txtPeso5'></td><td><input name='txtTA' type='text' id='txtTA5'></td><td><input name='txtFC' type='text' id='txtFC5'></td><td><input name='txtFR' type='text' id='txtFR5'></td><td><input name='txtPulso' type='text' id='txtPulso5'></td><td><a href='#' onclick='GuardarSV(5)'>Guardar</a></td></tr>");
			out.print("	 <tr><td>06 AM </td><td><input name='txtTalla' type='text' id='txtTalla6'></td><td><input name='txtPeso' type='text' id='txtPeso6'></td><td><input name='txtTA' type='text' id='txtTA6'></td><td><input name='txtFC' type='text' id='txtFC6'></td><td><input name='txtFR' type='text' id='txtFR6'></td><td><input name='txtPulso' type='text' id='txtPulso6'></td><td><a href='#' onclick='GuardarSV(6)'>Guardar</a></td></tr>");
			*/
			try {
				String FechaKardex=req.getParameter("FechaKardex");
				//String FechaKardex=req.getParameter("FechaKardex");
				//String CodAdmSV=req.getParameter("CodAdmSV");
				//String FechaSV=req.getParameter("FechaSV");
				/*
				out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
				out.print("<tr><td width='11%'>HORA DE TOMA </td>" +
						"<td width='14%'>TALLA</td>" +
						"<td width='15%'>PESO</td>" +
						"<td width='15%'>T.A</td>" +
						"<td width='15%'>F.C</td>" +
						"<td width='15%'>F.R</td>" +
						"<td width='15%'>PULSO</td>" +
						"<td>Guardar</td></tr>");*/
				
				
				out.print("<table width='100%' ><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+FechaKardex+"'> "+
						"<tr> <td ><div id='tallas'>TALLA</div></td>" +
						"<td><input id='tallasv' type='text' style='border: 1px solid #585858' onkeyup='soloNumerossv(tallasv)'; onblur='guardarHC(tallasv)'/>cms"+
						"</td></tr>"+
						"<tr> <td ><div id='pesos'>PESO</div></td>" +
						"<td><input id='pesosv' type='text' style='border: 1px solid #585858' onkeyup='soloNumerossv(pesosv)'; onblur='guardarHC(pesosv)'/>kg/g"+
						"</td></tr>"+
						"<tr><td>" +
						"<input type='button' id='aparecer_x' value='x' onclick='desocultar_x()' /></td><td>"+
						"<input type='button' id='aparecer_pap' value='P.A.P' onclick='desocultar_pap()' /></td><td>"+
						"<input type='button' id='aparecer_pamhemo' value='parametros hemodinamicos' onclick='desocultar_pamhemo()' /></td><td>"+
						"<input type='button' id='aparecer_pulso' value='pulso' onclick='desocultar_pulso()' /></td><td>"+
						"<input type='button' id='aparecer_pupilas' value='pupilas' onclick='desocultar_pupilas()' /></td>"+
						"</tr>");
				
				out.print("<table width='100%' border='0' ><tr><td width='30%'><div id='DatosEmerg'>-</div></td>" +
						"<td width='100%' align='right'><table style='background-color: #00BFFF; ' width='90%' border='0' align='right' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fechaInsertSV+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
				"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
				"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
				"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
				"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
				"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
				"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +												
				"</tr></table><div style='overflow-y:scroll; height:200px; width:100%'><table width='100%' border='0' >");
				
				//while(rs1.next()){
					System.out.println("Entro al while consulta");
					rs=mvf.RestaFechas(fechaInsertSV+"");
					if(rs.next()){
						System.out.println("Entro al if fecha consulta");
						if(rs.getString(1).equals("0")){
							
							//String espacios ="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							//String espacios4 ="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
							
							out.print("<tr id='temperrr'  bgcolor='#2E9AFE'> <td width='30%'><div id='salidas'style='text-align: center' ><CENTER>BASICOS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.</CENTER></div></td>" +
									"<td width='70%'> " +
									"</td>"+
									"</tr>"+
							
							
							
							
							//temperatura
							"<tr>"+
							//"<td width='100%'> <div id='temp'>T\u00B0 <sup><a href='#' onclick='deshabilitarFilaSv(temp)'>x</sup></div></td>" +
							"<td width='30%'> <div id='temp'>T\u00B0<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(temp)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'>" +
							"<table width='100%' border='0' >" +
							"<tr>" +
							"<td width='2.8%'><input id='temp7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp7)'; onblur='guardarHC(temp7)'/></td>" 
							+"<td  width='2.8%'><input id='temp8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp8)'; onblur='guardarHC(temp8)'/></td>" 
							+"<td  width='2.8%'><input id='temp9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp9)'; onblur='guardarHC(temp9)'/></td>" 
							+"<td  width='2.8%'><input id='temp10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp10)'; onblur='guardarHC(temp10)'/></td>" 
							+"<td  width='2.8%'><input id='temp11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp11)'; onblur='guardarHC(temp11)'/></td>" 
							+"<td  width='2.8%'><input id='temp12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp12)'; onblur='guardarHC(temp12)'/></td>"
							+"<td  width='2.8%'><input id='temp13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp13)'; onblur='guardarHC(temp13)'/></td>" 
							+"<td  width='2.8%'><input id='temp14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp14)'; onblur='guardarHC(temp14)'/></td>" 
							+"<td  width='2.8%'><input id='temp15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp15)'; onblur='guardarHC(temp15)'/></td>" 
							+"<td  width='2.8%'><input id='temp16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp16)'; onblur='guardarHC(temp16)'/></td>" 
							+"<td  width='2.8%'><input id='temp17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp17)'; onblur='guardarHC(temp17)'/></td>" 
							+"<td  width='2.8%'><input id='temp18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp18)'; onblur='guardarHC(temp18)'/></td>" 
							+"<td  width='2.8%'><input id='temp19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp19)'; onblur='guardarHC(temp19)'/></td>" 
							+"<td  width='2.8%'><input id='temp20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp20)'; onblur='guardarHC(temp20)'/></td>" 
							+"<td  width='2.8%'><input id='temp21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp21)'; onblur='guardarHC(temp21)'/></td>" 
							+"<td  width='2.8%'><input id='temp22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp22)'; onblur='guardarHC(temp22)'/></td>" 
							+"<td  width='2.8%'><input id='temp23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp23)'; onblur='guardarHC(temp23)'/></td>" 
							+"<td  width='2.8%'><input id='temp00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp00)'; onblur='guardarHC(temp00)'/></td>" 
							+"<td  width='2.8%'><input id='temp01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(temp01)'; onblur='guardarHC(temp01)'/></td>" 
							+"<td  width='2.8%'><input id='temp02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp02)'; onblur='guardarHC(temp02)'/></td>" 
							+"<td  width='2.8%'><input id='temp03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp03)'; onblur='guardarHC(temp03)'/></td>" 
							+"<td  width='2.8%'><input id='temp04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp04)'; onblur='guardarHC(temp04)'/></td>" 
							+"<td  width='2.8%'><input id='temp05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp05)'; onblur='guardarHC(temp05)'/></td>" 
							+"<td  width='2.8%'><input id='temp06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp06)'; onblur='guardarHC(temp06)'/></td>"													
							+"</tr> </table> </td></tr>" +
							
							
							//"<div style='overflow-y:scroll; height:100px'>"+
							
							//"<table width='100%' border='0'>"+
							
							//fc
							
							//"<tr bgcolor='#D8D8D8' ><td width='30%'><div id='fc'>FC <sub><a href='#' onclick='deshabilitarFilaSv(fc)'>x</a></sub></td>" +
							"<tr bgcolor='#D8D8D8' ><td width='30%'><div id='fc'>FC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(fc)' style='cursor:pointer;'/></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='fc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc7)'; onblur='guardarHC(fc7)'/></td>" 
							+"<td  width='2.8%'><input id='fc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc8)'; onblur='guardarHC(fc8)'/></td>" 
							+"<td  width='2.8%'><input id='fc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc9)'; onblur='guardarHC(fc9)'/></td>" 
							+"<td  width='2.8%'><input id='fc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc10)'; onblur='guardarHC(fc10)'/></td>" 
							+"<td  width='2.8%'><input id='fc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc11)'; onblur='guardarHC(fc11)'/></td>" 
							+"<td  width='2.8%'><input id='fc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc12)'; onblur='guardarHC(fc12)'/></td>"
							+"<td  width='2.8%'><input id='fc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc13)'; onblur='guardarHC(fc13)'/></td>" 
							+"<td  width='2.8%'><input id='fc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc14)'; onblur='guardarHC(fc14)'/></td>" 
							+"<td  width='2.8%'><input id='fc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc15)'; onblur='guardarHC(fc15)'/></td>" 
							+"<td  width='2.8%'><input id='fc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc16)'; onblur='guardarHC(fc16)'/></td>" 
							+"<td  width='2.8%'><input id='fc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc17)'; onblur='guardarHC(fc17)'/></td>" 
							+"<td  width='2.8%'><input id='fc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc18)'; onblur='guardarHC(fc18)'/></td>" 
							+"<td  width='2.8%'><input id='fc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc19)'; onblur='guardarHC(fc19)'/></td>" 
							+"<td  width='2.8%'><input id='fc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc20)'; onblur='guardarHC(fc20)'/></td>" 
							+"<td  width='2.8%'><input id='fc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc21)'; onblur='guardarHC(fc21)'/></td>" 
							+"<td  width='2.8%'><input id='fc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc22)'; onblur='guardarHC(fc22)'/></td>" 
							+"<td  width='2.8%'><input id='fc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc23)'; onblur='guardarHC(fc23)'/></td>" 
							+"<td  width='2.8%'><input id='fc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc00)'; onblur='guardarHC(fc00)'/></td>" 
							+"<td  width='2.8%'><input id='fc01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(fc01)'; onblur='guardarHC(fc01)'/></td>" 
							+"<td  width='2.8%'><input id='fc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc02)'; onblur='guardarHC(fc02)'/></td>" 
							+"<td  width='2.8%'><input id='fc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc03)'; onblur='guardarHC(fc03)'/></td>" 
							+"<td  width='2.8%'><input id='fc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc04)'; onblur='guardarHC(fc04)'/></td>" 
							+"<td  width='2.8%'><input id='fc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc05)'; onblur='guardarHC(fc05)'/></td>" 
							+"<td  width='2.8%'><input id='fc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc06)'; onblur='guardarHC(fc06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							
							//fr
							//"<tr> <td width='30%'><div id='frsv'>FR <br><a href='#' onclick='deshabilitarFilaSv(frsv)'>Eliminar</a></div></td>" +
							"<tr> <td width='30%'><div id='frsv'>FR<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(frsv)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='frsv7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv7)'; onblur='guardarHC(frsv7)'/></td>" 
							+"<td  width='2.8%'><input id='frsv8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv8)'; onblur='guardarHC(frsv8)'/></td>" 
							+"<td  width='2.8%'><input id='frsv9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv9)'; onblur='guardarHC(frsv9)'/></td>" 
							+"<td  width='2.8%'><input id='frsv10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv10)'; onblur='guardarHC(frsv10)'/></td>" 
							+"<td  width='2.8%'><input id='frsv11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv11)'; onblur='guardarHC(frsv11)'/></td>" 
							+"<td  width='2.8%'><input id='frsv12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv12)'; onblur='guardarHC(frsv12)'/></td>"
							+"<td  width='2.8%'><input id='frsv13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv13)'; onblur='guardarHC(frsv13)'/></td>" 
							+"<td  width='2.8%'><input id='frsv14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv14)'; onblur='guardarHC(frsv14)'/></td>" 
							+"<td  width='2.8%'><input id='frsv15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv15)'; onblur='guardarHC(frsv15)'/></td>" 
							+"<td  width='2.8%'><input id='frsv16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv16)'; onblur='guardarHC(frsv16)'/></td>" 
							+"<td  width='2.8%'><input id='frsv17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv17)'; onblur='guardarHC(frsv17)'/></td>" 
							+"<td  width='2.8%'><input id='frsv18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv18)'; onblur='guardarHC(frsv18)'/></td>" 
							+"<td  width='2.8%'><input id='frsv19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv19)'; onblur='guardarHC(frsv19)'/></td>" 
							+"<td  width='2.8%'><input id='frsv20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv20)'; onblur='guardarHC(frsv20)'/></td>" 
							+"<td  width='2.8%'><input id='frsv21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv21)'; onblur='guardarHC(frsv21)'/></td>" 
							+"<td  width='2.8%'><input id='frsv22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv22)'; onblur='guardarHC(frsv22)'/></td>" 
							+"<td  width='2.8%'><input id='frsv23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv23)'; onblur='guardarHC(frsv23)'/></td>" 
							+"<td  width='2.8%'><input id='frsv00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv00)'; onblur='guardarHC(frsv00)'/></td>" 
							+"<td  width='2.8%'><input id='frsv01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(frsv01)'; onblur='guardarHC(frsv01)'/></td>" 
							+"<td  width='2.8%'><input id='frsv02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv02)'; onblur='guardarHC(frsv02)'/></td>" 
							+"<td  width='2.8%'><input id='frsv03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv03)'; onblur='guardarHC(frsv03)'/></td>" 
							+"<td  width='2.8%'><input id='frsv04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv04)'; onblur='guardarHC(frsv04)'/></td>" 
							+"<td  width='2.8%'><input id='frsv05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv05)'; onblur='guardarHC(frsv05)'/></td>" 
							+"<td  width='2.8%'><input id='frsv06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv06)'; onblur='guardarHC(frsv06)'/></td>"													
							+"</tr></table></td></tr>" +
							//saturacion
							//"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='sat'>SATURACI\u00D3N <br><a href='#' onclick='deshabilitarFilaSv(sat)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='sat'>SATURACI\u00D3N <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(sat)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sat7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat7)'; onblur='guardarHC(sat7)'/></td>" 
							+"<td  width='2.8%'><input id='sat8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat8)'; onblur='guardarHC(sat8)'/></td>" 
							+"<td  width='2.8%'><input id='sat9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat9)'; onblur='guardarHC(sat9)'/></td>" 
							+"<td  width='2.8%'><input id='sat10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat10)'; onblur='guardarHC(sat10)'/></td>" 
							+"<td  width='2.8%'><input id='sat11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat11)'; onblur='guardarHC(sat11)'/></td>" 
							+"<td  width='2.8%'><input id='sat12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat12)'; onblur='guardarHC(sat12)'/></td>"
							+"<td  width='2.8%'><input id='sat13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat13)'; onblur='guardarHC(sat13)'/></td>" 
							+"<td  width='2.8%'><input id='sat14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat14)'; onblur='guardarHC(sat14)'/></td>" 
							+"<td  width='2.8%'><input id='sat15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat15)'; onblur='guardarHC(sat15)'/></td>" 
							+"<td  width='2.8%'><input id='sat16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat16)'; onblur='guardarHC(sat16)'/></td>" 
							+"<td  width='2.8%'><input id='sat17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat17)'; onblur='guardarHC(sat17)'/></td>" 
							+"<td  width='2.8%'><input id='sat18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat18)'; onblur='guardarHC(sat18)'/></td>" 
							+"<td  width='2.8%'><input id='sat19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat19)'; onblur='guardarHC(sat19)'/></td>" 
							+"<td  width='2.8%'><input id='sat20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat20)'; onblur='guardarHC(sat20)'/></td>" 
							+"<td  width='2.8%'><input id='sat21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat21)'; onblur='guardarHC(sat21)'/></td>" 
							+"<td  width='2.8%'><input id='sat22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat22)'; onblur='guardarHC(sat22)'/></td>" 
							+"<td  width='2.8%'><input id='sat23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat23)'; onblur='guardarHC(sat23)'/></td>" 
							+"<td  width='2.8%'><input id='sat00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat00)'; onblur='guardarHC(sat00)'/></td>" 
							+"<td  width='2.8%'><input id='sat01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(sat01)'; onblur='guardarHC(sat01)'/></td>" 
							+"<td  width='2.8%'><input id='sat02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat02)'; onblur='guardarHC(sat02)'/></td>" 
							+"<td  width='2.8%'><input id='sat03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat03)'; onblur='guardarHC(sat03)'/></td>" 
							+"<td  width='2.8%'><input id='sat04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat04)'; onblur='guardarHC(sat04)'/></td>" 
							+"<td  width='2.8%'><input id='sat05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat05)'; onblur='guardarHC(sat05)'/></td>" 
							+"<td  width='2.8%'><input id='sat06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat06)'; onblur='guardarHC(sat06)'/></td>"													
							+"</tr></table></td></tr>" +
							//fio2
							
							
							//"<tr> <td width='30%'><div id='fio'>FIO2 <br><a href='#' onclick='deshabilitarFilaSv(fio)'>Eliminar</a></div></td>" +
							"<tr> <td width='30%'><div id='fio'>FIO2<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(fio)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='fio7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio7)'; onblur='guardarHC(fio7)'/></td>" 
						   +"<td width='2.8%'><input id='fio8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio8)'; onblur='guardarHC(fio8)'/></td>" 
						   +"<td width='2.8%'><input id='fio9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio9)'; onblur='guardarHC(fio9)'/></td>" 
						   +"<td width='2.8%'><input id='fio10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio10)'; onblur='guardarHC(fio10)'/></td>" 
						   +"<td width='2.8%'><input id='fio11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio11)'; onblur='guardarHC(fio11)'/></td>" 
						   +"<td width='2.8%'><input id='fio12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio12)'; onblur='guardarHC(fio12)'/></td>"
						   +"<td width='2.8%'><input id='fio13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio13)'; onblur='guardarHC(fio13)'/></td>" 
						   +"<td width='2.8%'><input id='fio14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio14)'; onblur='guardarHC(fio14)'/></td>" 
						   +"<td width='2.8%'><input id='fio15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio15)'; onblur='guardarHC(fio15)'/></td>" 
						   +"<td width='2.8%'><input id='fio16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio16)'; onblur='guardarHC(fio16)'/></td>" 
						   +"<td width='2.8%'><input id='fio17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio17)'; onblur='guardarHC(fio17)'/></td>" 
						   +"<td width='2.8%'><input id='fio18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio18)'; onblur='guardarHC(fio18)'/></td>" 
						   +"<td width='2.8%'><input id='fio19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio19)'; onblur='guardarHC(fio19)'/></td>" 
						   +"<td width='2.8%'><input id='fio20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio20)'; onblur='guardarHC(fio20)'/></td>" 
						   +"<td width='2.8%'><input id='fio21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio21)'; onblur='guardarHC(fio21)'/></td>" 
						   +"<td width='2.8%'><input id='fio22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio22)'; onblur='guardarHC(fio22)'/></td>" 
						   +"<td width='2.8%'><input id='fio23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio23)'; onblur='guardarHC(fio23)'/></td>" 
						   +"<td width='2.8%'><input id='fio00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio00)'; onblur='guardarHC(fio00)'/></td>" 
						   +"<td width='2.8%'><input id='fio01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(fio01)'; onblur='guardarHC(fio01)'/></td>" 
						   +"<td width='2.8%'><input id='fio02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio02)'; onblur='guardarHC(fio02)'/></td>" 
						   +"<td width='2.8%'><input id='fio03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio03)'; onblur='guardarHC(fio03)'/></td>" 
						   +"<td width='2.8%'><input id='fio04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio04)'; onblur='guardarHC(fio04)'/></td>" 
						   +"<td width='2.8%'><input id='fio05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio05)'; onblur='guardarHC(fio05)'/></td>" 
						   +"<td width='2.8%'><input id='fio06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio06)'; onblur='guardarHC(fio06)'/></td>"													
						   +"</tr></table></td></tr>" +
							//sedacion
							//"<tr bgcolor='#D8D8D8' id='ocsedacion' style='display: none'> <td width='30%'><div id='sed'>SEDACION <br><a href='#' onclick='deshabilitarFilaSv(sed)'>Eliminar</a></div></td>" +
						   "<tr bgcolor='#D8D8D8' id='ocsedacion' style='display: none'> <td width='30%'><div id='sed'>SEDACION<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(sed)' style='cursor:pointer;' /></div></td>" +
						   "<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='sed7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed7)'; onblur='guardarHC(sed7)'/></td>" 
							+"<td width='2.8%'><input id='sed8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed8)'; onblur='guardarHC(sed8)'/></td>" 
							+"<td width='2.8%'><input id='sed9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed9)'; onblur='guardarHC(sed9)'/></td>" 
							+"<td width='2.8%'><input id='sed10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed10)'; onblur='guardarHC(sed10)'/></td>" 
							+"<td width='2.8%'><input id='sed11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed11)'; onblur='guardarHC(sed11)'/></td>" 
							+"<td width='2.8%'><input id='sed12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed12)'; onblur='guardarHC(sed12)'/></td>"
							+"<td width='2.8%'><input id='sed13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed13)'; onblur='guardarHC(sed13)'/></td>" 
							+"<td width='2.8%'><input id='sed14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed14)'; onblur='guardarHC(sed14)'/></td>" 
							+"<td width='2.8%'><input id='sed15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed15)'; onblur='guardarHC(sed15)'/></td>" 
							+"<td width='2.8%'><input id='sed16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed16)'; onblur='guardarHC(sed16)'/></td>" 
							+"<td width='2.8%'><input id='sed17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed17)'; onblur='guardarHC(sed17)'/></td>" 
							+"<td width='2.8%'><input id='sed18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed18)'; onblur='guardarHC(sed18)'/></td>" 
							+"<td width='2.8%'><input id='sed19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed19)'; onblur='guardarHC(sed19)'/></td>" 
							+"<td width='2.8%'><input id='sed20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed20)'; onblur='guardarHC(sed20)'/></td>" 
							+"<td width='2.8%'><input id='sed21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed21)'; onblur='guardarHC(sed21)'/></td>" 
							+"<td width='2.8%'><input id='sed22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed22)'; onblur='guardarHC(sed22)'/></td>" 
							+"<td width='2.8%'><input id='sed23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed23)'; onblur='guardarHC(sed23)'/></td>" 
							+"<td width='2.8%'><input id='sed00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed00)'; onblur='guardarHC(sed00)'/></td>" 
							+"<td width='2.8%'><input id='sed01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(sed01)'; onblur='guardarHC(sed01)'/></td>" 
							+"<td width='2.8%'><input id='sed02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed02)'; onblur='guardarHC(sed02)'/></td>" 
							+"<td width='2.8%'><input id='sed03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed03)'; onblur='guardarHC(sed03)'/></td>" 
							+"<td width='2.8%'><input id='sed04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed04)'; onblur='guardarHC(sed04)'/></td>" 
							+"<td width='2.8%'><input id='sed05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed05)'; onblur='guardarHC(sed05)'/></td>" 
							+"<td width='2.8%'><input id='sed06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed06)'; onblur='guardarHC(sed06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							//dolor
							//"<tr id='ocdolor' style='display: none'> <td width='30%'><div id='dol'>DOLOR <br><a href='#' onclick='deshabilitarFilaSv(dol)'>Eliminar</a></div></td>" +
							"<tr id='ocdolor' style='display: none'> <td width='30%'><div id='dol'>DOLOR<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(dol)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='dol7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol7)'; onblur='guardarHC(dol7)'/></td>" 
							+"<td width='2.8%'><input id='dol8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol8)'; onblur='guardarHC(dol8)'/></td>" 
							+"<td width='2.8%'><input id='dol9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol9)'; onblur='guardarHC(dol9)'/></td>" 
							+"<td width='2.8%'><input id='dol10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol10)'; onblur='guardarHC(dol10)'/></td>" 
							+"<td width='2.8%'><input id='dol11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol11)'; onblur='guardarHC(dol11)'/></td>" 
							+"<td width='2.8%'><input id='dol12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol12)'; onblur='guardarHC(dol12)'/></td>"
							+"<td width='2.8%'><input id='dol13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol13)'; onblur='guardarHC(dol13)'/></td>" 
							+"<td width='2.8%'><input id='dol14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol14)'; onblur='guardarHC(dol14)'/></td>" 
							+"<td width='2.8%'><input id='dol15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol15)'; onblur='guardarHC(dol15)'/></td>" 
							+"<td width='2.8%'><input id='dol16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol16)'; onblur='guardarHC(dol16)'/></td>" 
							+"<td width='2.8%'><input id='dol17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol17)'; onblur='guardarHC(dol17)'/></td>" 
							+"<td width='2.8%'><input id='dol18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol18)'; onblur='guardarHC(dol18)'/></td>" 
							+"<td width='2.8%'><input id='dol19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol19)'; onblur='guardarHC(dol19)'/></td>" 
							+"<td width='2.8%'><input id='dol20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol20)'; onblur='guardarHC(dol20)'/></td>" 
							+"<td width='2.8%'><input id='dol21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol21)'; onblur='guardarHC(dol21)'/></td>" 
							+"<td width='2.8%'><input id='dol22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol22)'; onblur='guardarHC(dol22)'/></td>" 
							+"<td width='2.8%'><input id='dol23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol23)'; onblur='guardarHC(dol23)'/></td>" 
							+"<td width='2.8%'><input id='dol00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol00)'; onblur='guardarHC(dol00)'/></td>" 
							+"<td width='2.8%'><input id='dol01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(dol01)'; onblur='guardarHC(dol01)'/></td>" 
							+"<td width='2.8%'><input id='dol02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol02)'; onblur='guardarHC(dol02)'/></td>" 
							+"<td width='2.8%'><input id='dol03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol03)'; onblur='guardarHC(dol03)'/></td>" 
							+"<td width='2.8%'><input id='dol04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol04)'; onblur='guardarHC(dol04)'/></td>" 
							+"<td width='2.8%'><input id='dol05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol05)'; onblur='guardarHC(dol05)'/></td>" 
							+"<td width='2.8%'><input id='dol06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol06)'; onblur='guardarHC(dol06)'/></td>"													
							+"</tr></table></td></tr>" +
							//ta s
							//"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='tas'>TA S <br><a href='#' onclick='deshabilitarFilaSv(tas)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='tas'>TA S <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tas)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tas7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas7)'; onblur='guardarHC(tas7)'/></td>" 
							+"<td  width='2.8%'><input id='tas8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas8)'; onblur='guardarHC(tas8)'/></td>" 
							+"<td  width='2.8%'><input id='tas9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas9)'; onblur='guardarHC(tas9)'/></td>" 
							+"<td  width='2.8%'><input id='tas10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas10)'; onblur='guardarHC(tas10)'/></td>" 
							+"<td  width='2.8%'><input id='tas11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas11)'; onblur='guardarHC(tas11)'/></td>" 
							+"<td  width='2.8%'><input id='tas12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas12)'; onblur='guardarHC(tas12)'/></td>"
							+"<td  width='2.8%'><input id='tas13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas13)'; onblur='guardarHC(tas13)'/></td>" 
							+"<td  width='2.8%'><input id='tas14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas14)'; onblur='guardarHC(tas14)'/></td>" 
							+"<td  width='2.8%'><input id='tas15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas15)'; onblur='guardarHC(tas15)'/></td>" 
							+"<td  width='2.8%'><input id='tas16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas16)'; onblur='guardarHC(tas16)'/></td>" 
							+"<td  width='2.8%'><input id='tas17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas17)'; onblur='guardarHC(tas17)'/></td>" 
							+"<td  width='2.8%'><input id='tas18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas18)'; onblur='guardarHC(tas18)'/></td>" 
							+"<td  width='2.8%'><input id='tas19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas19)'; onblur='guardarHC(tas19)'/></td>" 
							+"<td  width='2.8%'><input id='tas20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas20)'; onblur='guardarHC(tas20)'/></td>" 
							+"<td  width='2.8%'><input id='tas21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas21)'; onblur='guardarHC(tas21)'/></td>" 
							+"<td  width='2.8%'><input id='tas22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas22)'; onblur='guardarHC(tas22)'/></td>" 
							+"<td  width='2.8%'><input id='tas23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas23)'; onblur='guardarHC(tas23)'/></td>" 
							+"<td  width='2.8%'><input id='tas00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas00)'; onblur='guardarHC(tas00)'/></td>" 
							+"<td  width='2.8%'><input id='tas01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(tas01)'; onblur='guardarHC(tas01)'/></td>" 
							+"<td  width='2.8%'><input id='tas02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas02)'; onblur='guardarHC(tas02)'/></td>" 
							+"<td  width='2.8%'><input id='tas03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas03)'; onblur='guardarHC(tas03)'/></td>" 
							+"<td  width='2.8%'><input id='tas04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas04)'; onblur='guardarHC(tas04)'/></td>" 
							+"<td  width='2.8%'><input id='tas05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas05)'; onblur='guardarHC(tas05)'/></td>" 
							+"<td  width='2.8%'><input id='tas06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas06)'; onblur='guardarHC(tas06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							//ta d
							//"<tr> <td width='30%'><div id='tad'>TA D <br><a href='#' onclick='deshabilitarFilaSv(tad)'>Eliminar</a></div></td>" +
							"<tr> <td width='30%'><div id='tad'>TA D<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tad)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tad7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad7)'; onblur='guardarHC(tad7)'/></td>" 
							+"<td  width='2.8%'><input id='tad8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad8)'; onblur='guardarHC(tad8)'/></td>" 
							+"<td  width='2.8%'><input id='tad9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad9)'; onblur='guardarHC(tad9)'/></td>" 
							+"<td  width='2.8%'><input id='tad10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad10)'; onblur='guardarHC(tad10)'/></td>" 
							+"<td  width='2.8%'><input id='tad11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad11)'; onblur='guardarHC(tad11)'/></td>" 
							+"<td  width='2.8%'><input id='tad12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad12)'; onblur='guardarHC(tad12)'/></td>"
							+"<td  width='2.8%'><input id='tad13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad13)'; onblur='guardarHC(tad13)'/></td>" 
							+"<td  width='2.8%'><input id='tad14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad14)'; onblur='guardarHC(tad14)'/></td>" 
							+"<td  width='2.8%'><input id='tad15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad15)'; onblur='guardarHC(tad15)'/></td>" 
							+"<td  width='2.8%'><input id='tad16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad16)'; onblur='guardarHC(tad16)'/></td>" 
							+"<td  width='2.8%'><input id='tad17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad17)'; onblur='guardarHC(tad17)'/></td>" 
							+"<td  width='2.8%'><input id='tad18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad18)'; onblur='guardarHC(tad18)'/></td>" 
							+"<td  width='2.8%'><input id='tad19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad19)'; onblur='guardarHC(tad19)'/></td>" 
							+"<td  width='2.8%'><input id='tad20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad20)'; onblur='guardarHC(tad20)'/></td>" 
							+"<td  width='2.8%'><input id='tad21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad21)'; onblur='guardarHC(tad21)'/></td>" 
							+"<td  width='2.8%'><input id='tad22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad22)'; onblur='guardarHC(tad22)'/></td>" 
							+"<td  width='2.8%'><input id='tad23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad23)'; onblur='guardarHC(tad23)'/></td>" 
							+"<td  width='2.8%'><input id='tad00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad00)'; onblur='guardarHC(tad00)'/></td>" 
							+"<td  width='2.8%'><input id='tad01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(tad01)'; onblur='guardarHC(tad01)'/></td>" 
							+"<td  width='2.8%'><input id='tad02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad02)'; onblur='guardarHC(tad02)'/></td>" 
							+"<td  width='2.8%'><input id='tad03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad03)'; onblur='guardarHC(tad03)'/></td>" 
							+"<td  width='2.8%'><input id='tad04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad04)'; onblur='guardarHC(tad04)'/></td>" 
							+"<td  width='2.8%'><input id='tad05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad05)'; onblur='guardarHC(tad05)'/></td>" 
							+"<td  width='2.8%'><input id='tad06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad06)'; onblur='guardarHC(tad06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							
							
							//tam
							"<tr id='octam' bgcolor='#D8D8D8'> <td width='30%'><div id='tam'>TAM</div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tam7' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam8' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam9' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam10' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam11' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam12' type='text' size='1' disabled='disabled'/></td>"
							+"<td  width='2.8%'><input id='tam13' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam14' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam15' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam16' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam17' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam18' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam19' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam20' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam21' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam22' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam23' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam00' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam01' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam02' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam03' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam04' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam05' type='text' size='1' disabled='disabled'/></td>" 
							+"<td  width='2.8%'><input id='tam06' type='text' size='1' disabled='disabled'/></td>"													
							+"</tr></table></td></tr>" +
							//pvc
							//"<tr id='ocpvc' style='display: none'> <td width='30%'><div id='pvc'>PVC <br><a href='#' onclick='deshabilitarFilaSv(pvc)'>Eliminar</a></div></td>" +
							"<tr id='ocpvc' style='display: none'> <td width='30%'><div id='pvc'>PVC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(pvc)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='pvc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc7)'; onblur='guardarHC(pvc7)'/></td>" 
							+"<td  width='2.8%'><input id='pvc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc8)'; onblur='guardarHC(pvc8)'/></td>" 
							+"<td  width='2.8%'><input id='pvc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc9)'; onblur='guardarHC(pvc9)'/></td>" 
							+"<td  width='2.8%'><input id='pvc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc10)'; onblur='guardarHC(pvc10)'/></td>" 
							+"<td  width='2.8%'><input id='pvc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc11)'; onblur='guardarHC(pvc11)'/></td>" 
							+"<td  width='2.8%'><input id='pvc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc12)'; onblur='guardarHC(pvc12)'/></td>"
							+"<td  width='2.8%'><input id='pvc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc13)'; onblur='guardarHC(pvc13)'/></td>" 
							+"<td  width='2.8%'><input id='pvc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc14)'; onblur='guardarHC(pvc14)'/></td>" 
							+"<td  width='2.8%'><input id='pvc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc15)'; onblur='guardarHC(pvc15)'/></td>" 
							+"<td  width='2.8%'><input id='pvc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc16)'; onblur='guardarHC(pvc16)'/></td>" 
							+"<td  width='2.8%'><input id='pvc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc17)'; onblur='guardarHC(pvc17)'/></td>" 
							+"<td  width='2.8%'><input id='pvc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc18)'; onblur='guardarHC(pvc18)'/></td>" 
							+"<td  width='2.8%'><input id='pvc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc19)'; onblur='guardarHC(pvc19)'/></td>" 
							+"<td  width='2.8%'><input id='pvc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc20)'; onblur='guardarHC(pvc20)'/></td>" 
							+"<td  width='2.8%'><input id='pvc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc21)'; onblur='guardarHC(pvc21)'/></td>" 
							+"<td  width='2.8%'><input id='pvc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc22)'; onblur='guardarHC(pvc22)'/></td>" 
							+"<td  width='2.8%'><input id='pvc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc23)'; onblur='guardarHC(pvc23)'/></td>" 
							+"<td  width='2.8%'><input id='pvc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc00)'; onblur='guardarHC(pvc00)'/></td>" 
							+"<td  width='2.8%'><input id='pvc01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(pvc01)'; onblur='guardarHC(pvc01)'/></td>" 
							+"<td  width='2.8%'><input id='pvc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc02)'; onblur='guardarHC(pvc02)'/></td>" 
							+"<td  width='2.8%'><input id='pvc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc03)'; onblur='guardarHC(pvc03)'/></td>" 
							+"<td  width='2.8%'><input id='pvc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc04)'; onblur='guardarHC(pvc04)'/></td>" 
							+"<td  width='2.8%'><input id='pvc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc05)'; onblur='guardarHC(pvc05)'/></td>" 
							+"<td  width='2.8%'><input id='pvc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc06)'; onblur='guardarHC(pvc06)'/></td>"													
							+"</tr></table></td></tr>" +
							//i.s
							//"<tr id='ocis' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='is'>I.S <br><a href='#' onclick='deshabilitarFilaSv(is)'>Eliminar</a></div></td>" +
							"<tr id='ocis' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='is'>I.S<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(is)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='is7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is7)'; onblur='guardarHC(is7)'/></td>" 
							+"<td  width='2.8%'><input id='is8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is8)'; onblur='guardarHC(is8)'/></td>" 
							+"<td  width='2.8%'><input id='is9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is9)'; onblur='guardarHC(is9)'/></td>" 
							+"<td  width='2.8%'><input id='is10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is10)'; onblur='guardarHC(is10)'/></td>" 
							+"<td  width='2.8%'><input id='is11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is11)'; onblur='guardarHC(is11)'/></td>" 
							+"<td  width='2.8%'><input id='is12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is12)'; onblur='guardarHC(is12)'/></td>"
							+"<td  width='2.8%'><input id='is13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is13)'; onblur='guardarHC(is13)'/></td>" 
							+"<td  width='2.8%'><input id='is14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is14)'; onblur='guardarHC(is14)'/></td>" 
							+"<td  width='2.8%'><input id='is15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is15)'; onblur='guardarHC(is15)'/></td>" 
							+"<td  width='2.8%'><input id='is16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is16)'; onblur='guardarHC(is16)'/></td>" 
							+"<td  width='2.8%'><input id='is17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is17)'; onblur='guardarHC(is17)'/></td>" 
							+"<td  width='2.8%'><input id='is18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is18)'; onblur='guardarHC(is18)'/></td>" 
							+"<td  width='2.8%'><input id='is19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is19)'; onblur='guardarHC(is19)'/></td>" 
							+"<td  width='2.8%'><input id='is20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is20)'; onblur='guardarHC(is20)'/></td>" 
							+"<td  width='2.8%'><input id='is21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is21)'; onblur='guardarHC(is21)'/></td>" 
							+"<td  width='2.8%'><input id='is22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is22)'; onblur='guardarHC(is22)'/></td>" 
							+"<td  width='2.8%'><input id='is23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is23)'; onblur='guardarHC(is23)'/></td>" 
							+"<td  width='2.8%'><input id='is00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is00)'; onblur='guardarHC(is00)'/></td>" 
							+"<td  width='2.8%'><input id='is01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(is01)'; onblur='guardarHC(is01)'/></td>" 
							+"<td  width='2.8%'><input id='is02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is02)'; onblur='guardarHC(is02)'/></td>" 
							+"<td  width='2.8%'><input id='is03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is03)'; onblur='guardarHC(is03)'/></td>" 
							+"<td  width='2.8%'><input id='is04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is04)'; onblur='guardarHC(is04)'/></td>" 
							+"<td  width='2.8%'><input id='is05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is05)'; onblur='guardarHC(is05)'/></td>" 
							+"<td  width='2.8%'><input id='is06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is06)'; onblur='guardarHC(is06)'/></td>"													
							+"</tr></table></td></tr>" +
							//p.a.p.
							"<tr id='ocdivpap' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>P.A.P.</CENTER></div></td>" +
							"<td> <a href='#' onclick='ocultarPap()'><font color='white'>Ocultar</font></a>" +
							"</td>"+
							"</tr>"+
							//s
							//"<tr id='ocpaps' style='display: none'> <td width='30%'><div id='paps'>S <br><a href='#' onclick='deshabilitarFilaSv(paps)'>Eliminar</a></div></td>" +
							"<tr id='ocpaps' style='display: none'> <td width='30%'><div id='paps'>S<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(paps)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='paps7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps7)'; onblur='guardarHC(paps7)'/></td>" 
							+"<td  width='2.8%'><input id='paps8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps8)'; onblur='guardarHC(paps8)'/></td>" 
							+"<td  width='2.8%'><input id='paps9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps9)'; onblur='guardarHC(paps9)'/></td>" 
							+"<td  width='2.8%'><input id='paps10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps10)'; onblur='guardarHC(paps10)'/></td>" 
							+"<td  width='2.8%'><input id='paps11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps11)'; onblur='guardarHC(paps11)'/></td>" 
							+"<td  width='2.8%'><input id='paps12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps12)'; onblur='guardarHC(paps12)'/></td>"
							+"<td  width='2.8%'><input id='paps13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps13)'; onblur='guardarHC(paps13)'/></td>" 
							+"<td  width='2.8%'><input id='paps14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps14)'; onblur='guardarHC(paps14)'/></td>" 
							+"<td  width='2.8%'><input id='paps15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps15)'; onblur='guardarHC(paps15)'/></td>" 
							+"<td  width='2.8%'><input id='paps16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps16)'; onblur='guardarHC(paps16)'/></td>" 
							+"<td  width='2.8%'><input id='paps17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps17)'; onblur='guardarHC(paps17)'/></td>" 
							+"<td  width='2.8%'><input id='paps18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps18)'; onblur='guardarHC(paps18)'/></td>" 
							+"<td  width='2.8%'><input id='paps19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps19)'; onblur='guardarHC(paps19)'/></td>" 
							+"<td  width='2.8%'><input id='paps20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps20)'; onblur='guardarHC(paps20)'/></td>" 
							+"<td  width='2.8%'><input id='paps21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps21)'; onblur='guardarHC(paps21)'/></td>" 
							+"<td  width='2.8%'><input id='paps22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps22)'; onblur='guardarHC(paps22)'/></td>" 
							+"<td  width='2.8%'><input id='paps23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps23)'; onblur='guardarHC(paps23)'/></td>" 
							+"<td  width='2.8%'><input id='paps00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps00)'; onblur='guardarHC(paps00)'/></td>" 
							+"<td  width='2.8%'><input id='paps01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(paps01)'; onblur='guardarHC(paps01)'/></td>" 
							+"<td  width='2.8%'><input id='paps02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps02)'; onblur='guardarHC(paps02)'/></td>" 
							+"<td  width='2.8%'><input id='paps03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps03)'; onblur='guardarHC(paps03)'/></td>" 
							+"<td  width='2.8%'><input id='paps04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps04)'; onblur='guardarHC(paps04)'/></td>" 
							+"<td  width='2.8%'><input id='paps05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps05)'; onblur='guardarHC(paps05)'/></td>" 
							+"<td  width='2.8%'><input id='paps06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps06)'; onblur='guardarHC(paps06)'/></td>"													
							+"</tr></table></td></tr>" +
							//d
							//"<tr bgcolor='#D8D8D8' id='ocpapd' style='display: none'> <td width='30%'><div id='papd'>D <br><a href='#' onclick='deshabilitarFilaSv(papd)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocpapd' style='display: none'> <td width='30%'><div id='papd'>D<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(papd)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='papd7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd7)'; onblur='guardarHC(papd7)'/></td>" 
							+"<td  width='2.8%'><input id='papd8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd8)'; onblur='guardarHC(papd8)'/></td>" 
							+"<td  width='2.8%'><input id='papd9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd9)'; onblur='guardarHC(papd9)'/></td>" 
							+"<td  width='2.8%'><input id='papd10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd10)'; onblur='guardarHC(papd10)'/></td>" 
							+"<td  width='2.8%'><input id='papd11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd11)'; onblur='guardarHC(papd11)'/></td>" 
							+"<td  width='2.8%'><input id='papd12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd12)'; onblur='guardarHC(papd12)'/></td>"
							+"<td  width='2.8%'><input id='papd13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd13)'; onblur='guardarHC(papd13)'/></td>" 
							+"<td  width='2.8%'><input id='papd14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd14)'; onblur='guardarHC(papd14)'/></td>" 
							+"<td  width='2.8%'><input id='papd15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd15)'; onblur='guardarHC(papd15)'/></td>" 
							+"<td  width='2.8%'><input id='papd16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd16)'; onblur='guardarHC(papd16)'/></td>" 
							+"<td  width='2.8%'><input id='papd17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd17)'; onblur='guardarHC(papd17)'/></td>" 
							+"<td  width='2.8%'><input id='papd18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd18)'; onblur='guardarHC(papd18)'/></td>" 
							+"<td  width='2.8%'><input id='papd19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd19)'; onblur='guardarHC(papd19)'/></td>" 
							+"<td  width='2.8%'><input id='papd20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd20)'; onblur='guardarHC(papd20)'/></td>" 
							+"<td  width='2.8%'><input id='papd21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd21)'; onblur='guardarHC(papd21)'/></td>" 
							+"<td  width='2.8%'><input id='papd22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd22)'; onblur='guardarHC(papd22)'/></td>" 
							+"<td  width='2.8%'><input id='papd23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd23)'; onblur='guardarHC(papd23)'/></td>" 
							+"<td  width='2.8%'><input id='papd00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd00)'; onblur='guardarHC(papd00)'/></td>" 
							+"<td  width='2.8%'><input id='papd01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(papd01)'; onblur='guardarHC(papd01)'/></td>" 
							+"<td  width='2.8%'><input id='papd02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd02)'; onblur='guardarHC(papd02)'/></td>" 
							+"<td  width='2.8%'><input id='papd03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd03)'; onblur='guardarHC(papd03)'/></td>" 
							+"<td  width='2.8%'><input id='papd04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd04)'; onblur='guardarHC(papd04)'/></td>" 
							+"<td  width='2.8%'><input id='papd05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd05)'; onblur='guardarHC(papd05)'/></td>" 
							+"<td  width='2.8%'><input id='papd06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd06)'; onblur='guardarHC(papd06)'/></td>"													
							+"</tr></table></td></tr>" +
							//m
							//"<tr id='ocpapm' style='display: none'> <td width='30%'><div id='papm'>M <br><a href='#' onclick='deshabilitarFilaSv(papm)'>Eliminar</a></div></td>" +
							"<tr id='ocpapm' style='display: none'> <td width='30%'><div id='papm'>M<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(papm)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='papm7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm7)'; onblur='guardarHC(papm7)'/></td>" 
							+"<td  width='2.8%'><input id='papm8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm8)'; onblur='guardarHC(papm8)'/></td>" 
							+"<td  width='2.8%'><input id='papm9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm9)'; onblur='guardarHC(papm9)'/></td>" 
							+"<td  width='2.8%'><input id='papm10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm10)'; onblur='guardarHC(papm10)'/></td>" 
							+"<td  width='2.8%'><input id='papm11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm11)'; onblur='guardarHC(papm11)'/></td>" 
							+"<td  width='2.8%'><input id='papm12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm12)'; onblur='guardarHC(papm12)'/></td>"
							+"<td  width='2.8%'><input id='papm13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm13)'; onblur='guardarHC(papm13)'/></td>" 
							+"<td  width='2.8%'><input id='papm14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm14)'; onblur='guardarHC(papm14)'/></td>" 
							+"<td  width='2.8%'><input id='papm15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm15)'; onblur='guardarHC(papm15)'/></td>" 
							+"<td  width='2.8%'><input id='papm16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm16)'; onblur='guardarHC(papm16)'/></td>" 
							+"<td  width='2.8%'><input id='papm17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm17)'; onblur='guardarHC(papm17)'/></td>" 
							+"<td  width='2.8%'><input id='papm18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm18)'; onblur='guardarHC(papm18)'/></td>" 
							+"<td  width='2.8%'><input id='papm19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm19)'; onblur='guardarHC(papm19)'/></td>" 
							+"<td  width='2.8%'><input id='papm20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm20)'; onblur='guardarHC(papm20)'/></td>" 
							+"<td  width='2.8%'><input id='papm21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm21)'; onblur='guardarHC(papm21)'/></td>" 
							+"<td  width='2.8%'><input id='papm22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm22)'; onblur='guardarHC(papm22)'/></td>" 
							+"<td  width='2.8%'><input id='papm23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm23)'; onblur='guardarHC(papm23)'/></td>" 
							+"<td  width='2.8%'><input id='papm00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm00)'; onblur='guardarHC(papm00)'/></td>" 
							+"<td  width='2.8%'><input id='papm01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(papm01)'; onblur='guardarHC(papm01)'/></td>" 
							+"<td  width='2.8%'><input id='papm02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm02)'; onblur='guardarHC(papm02)'/></td>" 
							+"<td  width='2.8%'><input id='papm03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm03)'; onblur='guardarHC(papm03)'/></td>" 
							+"<td  width='2.8%'><input id='papm04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm04)'; onblur='guardarHC(papm04)'/></td>" 
							+"<td  width='2.8%'><input id='papm05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm05)'; onblur='guardarHC(papm05)'/></td>" 
							+"<td  width='2.8%'><input id='papm06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm06)'; onblur='guardarHC(papm06)'/></td>"													
							+"</tr></table></td></tr>" +
							//c
							//"<tr bgcolor='#D8D8D8' id='ocpapc' style='display: none'> <td width='30%'><div id='papc'>C <br><a href='#' onclick='deshabilitarFilaSv(papc)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocpapc' style='display: none'> <td width='30%'><div id='papc'>C<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(papc)'  style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='papc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc7)'; onblur='guardarHC(papc7)'/></td>" 
							+"<td  width='2.8%'><input id='papc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc8)'; onblur='guardarHC(papc8)'/></td>" 
							+"<td  width='2.8%'><input id='papc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc9)'; onblur='guardarHC(papc9)'/></td>" 
							+"<td  width='2.8%'><input id='papc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc10)'; onblur='guardarHC(papc10)'/></td>" 
							+"<td  width='2.8%'><input id='papc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc11)'; onblur='guardarHC(papc11)'/></td>" 
							+"<td  width='2.8%'><input id='papc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc12)'; onblur='guardarHC(papc12)'/></td>"
							+"<td  width='2.8%'><input id='papc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc13)'; onblur='guardarHC(papc13)'/></td>" 
							+"<td  width='2.8%'><input id='papc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc14)'; onblur='guardarHC(papc14)'/></td>" 
							+"<td  width='2.8%'><input id='papc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc15)'; onblur='guardarHC(papc15)'/></td>" 
							+"<td  width='2.8%'><input id='papc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc16)'; onblur='guardarHC(papc16)'/></td>" 
							+"<td  width='2.8%'><input id='papc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc17)'; onblur='guardarHC(papc17)'/></td>" 
							+"<td  width='2.8%'><input id='papc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc18)'; onblur='guardarHC(papc18)'/></td>" 
							+"<td  width='2.8%'><input id='papc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc19)'; onblur='guardarHC(papc19)'/></td>" 
							+"<td  width='2.8%'><input id='papc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc20)'; onblur='guardarHC(papc20)'/></td>" 
							+"<td  width='2.8%'><input id='papc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc21)'; onblur='guardarHC(papc21)'/></td>" 
							+"<td  width='2.8%'><input id='papc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc22)'; onblur='guardarHC(papc22)'/></td>" 
							+"<td  width='2.8%'><input id='papc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc23)'; onblur='guardarHC(papc23)'/></td>" 
							+"<td  width='2.8%'><input id='papc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc00)'; onblur='guardarHC(papc00)'/></td>" 
							+"<td  width='2.8%'><input id='papc01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(papc01)'; onblur='guardarHC(papc01)'/></td>" 
							+"<td  width='2.8%'><input id='papc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc02)'; onblur='guardarHC(papc02)'/></td>" 
							+"<td  width='2.8%'><input id='papc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc03)'; onblur='guardarHC(papc03)'/></td>" 
							+"<td  width='2.8%'><input id='papc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc04)'; onblur='guardarHC(papc04)'/></td>" 
							+"<td  width='2.8%'><input id='papc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc05)'; onblur='guardarHC(papc05)'/></td>" 
							+"<td  width='2.8%'><input id='papc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc06)'; onblur='guardarHC(papc06)'/></td>"													
							+"</tr></table></td></tr>" +
							//PARAMETROS HEMODINAMICOS
							"<tr id='ocdivpamhemo' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>PARAMETROS HEMODINAMICOS</CENTER></div></td>" +
							"<td> <a href='#' onclick='ocultarPamhemo()'><font color='white'>Ocultar</font></a>" +
							"</td>"+
							"</tr>"+
							//pwc
							//"<tr id='ocpwc' style='display: none'> <td width='30%'><div id='pwc'>PWC <br><a href='#' onclick='deshabilitarFilaSv(pwc)'>Eliminar</a></div></td>" +
							"<tr id='ocpwc' style='display: none'> <td width='30%'><div id='pwc'>PWC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(pwc)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='pwc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc7)'; onblur='guardarHC(pwc7)'/></td>" 
							+"<td  width='2.8%'><input id='pwc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc8)'; onblur='guardarHC(pwc8)'/></td>" 
							+"<td  width='2.8%'><input id='pwc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc9)'; onblur='guardarHC(pwc9)'/></td>" 
							+"<td  width='2.8%'><input id='pwc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc10)'; onblur='guardarHC(pwc10)'/></td>" 
							+"<td  width='2.8%'><input id='pwc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc11)'; onblur='guardarHC(pwc11)'/></td>" 
							+"<td  width='2.8%'><input id='pwc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc12)'; onblur='guardarHC(pwc12)'/></td>"
							+"<td  width='2.8%'><input id='pwc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc13)'; onblur='guardarHC(pwc13)'/></td>" 
							+"<td  width='2.8%'><input id='pwc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc14)'; onblur='guardarHC(pwc14)'/></td>" 
							+"<td  width='2.8%'><input id='pwc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc15)'; onblur='guardarHC(pwc15)'/></td>" 
							+"<td  width='2.8%'><input id='pwc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc16)'; onblur='guardarHC(pwc16)'/></td>" 
							+"<td  width='2.8%'><input id='pwc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc17)'; onblur='guardarHC(pwc17)'/></td>" 
							+"<td  width='2.8%'><input id='pwc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc18)'; onblur='guardarHC(pwc18)'/></td>" 
							+"<td  width='2.8%'><input id='pwc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc19)'; onblur='guardarHC(pwc19)'/></td>" 
							+"<td  width='2.8%'><input id='pwc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc20)'; onblur='guardarHC(pwc20)'/></td>" 
							+"<td  width='2.8%'><input id='pwc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc21)'; onblur='guardarHC(pwc21)'/></td>" 
							+"<td  width='2.8%'><input id='pwc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc22)'; onblur='guardarHC(pwc22)'/></td>" 
							+"<td  width='2.8%'><input id='pwc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc23)'; onblur='guardarHC(pwc23)'/></td>" 
							+"<td  width='2.8%'><input id='pwc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc00)'; onblur='guardarHC(pwc00)'/></td>" 
							+"<td  width='2.8%'><input id='pwc01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(pwc01)'; onblur='guardarHC(pwc01)'/></td>" 
							+"<td  width='2.8%'><input id='pwc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc02)'; onblur='guardarHC(pwc02)'/></td>" 
							+"<td  width='2.8%'><input id='pwc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc03)'; onblur='guardarHC(pwc03)'/></td>" 
							+"<td  width='2.8%'><input id='pwc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc04)'; onblur='guardarHC(pwc04)'/></td>" 
							+"<td  width='2.8%'><input id='pwc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc05)'; onblur='guardarHC(pwc05)'/></td>" 
							+"<td  width='2.8%'><input id='pwc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc06)'; onblur='guardarHC(pwc06)'/></td>"													
							+"</tr></table></td></tr>" +
							//vvs
							//"<tr bgcolor='#D8D8D8' id='ocvvs' style='display: none'> <td width='30%'><div id='vvs'>VVS <br><a href='#' onclick='deshabilitarFilaSv(vvs)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocvvs' style='display: none'> <td width='30%'><div id='vvs'>VVS<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(vvs)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='vvs7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs7)'; onblur='guardarHC(vvs7)'/></td>" 
							+"<td  width='2.8%'><input id='vvs8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs8)'; onblur='guardarHC(vvs8)'/></td>" 
							+"<td  width='2.8%'><input id='vvs9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs9)'; onblur='guardarHC(vvs9)'/></td>" 
							+"<td  width='2.8%'><input id='vvs10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs10)'; onblur='guardarHC(vvs10)'/></td>" 
							+"<td  width='2.8%'><input id='vvs11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs11)'; onblur='guardarHC(vvs11)'/></td>" 
							+"<td  width='2.8%'><input id='vvs12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs12)'; onblur='guardarHC(vvs12)'/></td>"
							+"<td  width='2.8%'><input id='vvs13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs13)'; onblur='guardarHC(vvs13)'/></td>" 
							+"<td  width='2.8%'><input id='vvs14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs14)'; onblur='guardarHC(vvs14)'/></td>" 
							+"<td  width='2.8%'><input id='vvs15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs15)'; onblur='guardarHC(vvs15)'/></td>" 
							+"<td  width='2.8%'><input id='vvs16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs16)'; onblur='guardarHC(vvs16)'/></td>" 
							+"<td  width='2.8%'><input id='vvs17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs17)'; onblur='guardarHC(vvs17)'/></td>" 
							+"<td  width='2.8%'><input id='vvs18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs18)'; onblur='guardarHC(vvs18)'/></td>" 
							+"<td  width='2.8%'><input id='vvs19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs19)'; onblur='guardarHC(vvs19)'/></td>" 
							+"<td  width='2.8%'><input id='vvs20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs20)'; onblur='guardarHC(vvs20)'/></td>" 
							+"<td  width='2.8%'><input id='vvs21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs21)'; onblur='guardarHC(vvs21)'/></td>" 
							+"<td  width='2.8%'><input id='vvs22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs22)'; onblur='guardarHC(vvs22)'/></td>" 
							+"<td  width='2.8%'><input id='vvs23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs23)'; onblur='guardarHC(vvs23)'/></td>" 
							+"<td  width='2.8%'><input id='vvs00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs00)'; onblur='guardarHC(vvs00)'/></td>" 
							+"<td  width='2.8%'><input id='vvs01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(vvs01)'; onblur='guardarHC(vvs01)'/></td>" 
							+"<td  width='2.8%'><input id='vvs02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs02)'; onblur='guardarHC(vvs02)'/></td>" 
							+"<td  width='2.8%'><input id='vvs03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs03)'; onblur='guardarHC(vvs03)'/></td>" 
							+"<td  width='2.8%'><input id='vvs04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs05)'; onblur='guardarHC(vvs04)'/></td>" 
							+"<td  width='2.8%'><input id='vvs05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs05)'; onblur='guardarHC(vvs05)'/></td>" 
							+"<td  width='2.8%'><input id='vvs06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs06)'; onblur='guardarHC(vvs06)'/></td>"													
							+"</tr></table></td></tr>" +
							//gc
							//"<tr id='ocgc' style='display: none'> <td width='30%'><div id='gc'>GC <br><a href='#' onclick='deshabilitarFilaSv(gc)'>Eliminar</a></div></td>" +
							"<tr id='ocgc' style='display: none'> <td width='30%'><div id='gc'>GC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(gc)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='gc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc7)'; onblur='guardarHC(gc7)'/></td>" 
							+"<td  width='2.8%'><input id='gc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc8)'; onblur='guardarHC(gc8)'/></td>" 
							+"<td  width='2.8%'><input id='gc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc9)'; onblur='guardarHC(gc9)'/></td>" 
							+"<td  width='2.8%'><input id='gc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc10)'; onblur='guardarHC(gc10)'/></td>" 
							+"<td  width='2.8%'><input id='gc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc11)'; onblur='guardarHC(gc11)'/></td>" 
							+"<td  width='2.8%'><input id='gc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc12)'; onblur='guardarHC(gc12)'/></td>"
							+"<td  width='2.8%'><input id='gc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc13)'; onblur='guardarHC(gc13)'/></td>" 
							+"<td  width='2.8%'><input id='gc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc14)'; onblur='guardarHC(gc14)'/></td>" 
							+"<td  width='2.8%'><input id='gc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc15)'; onblur='guardarHC(gc15)'/></td>" 
							+"<td  width='2.8%'><input id='gc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc16)'; onblur='guardarHC(gc16)'/></td>" 
							+"<td  width='2.8%'><input id='gc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc17)'; onblur='guardarHC(gc17)'/></td>" 
							+"<td  width='2.8%'><input id='gc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc18)'; onblur='guardarHC(gc18)'/></td>" 
							+"<td  width='2.8%'><input id='gc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc19)'; onblur='guardarHC(gc19)'/></td>" 
							+"<td  width='2.8%'><input id='gc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc20)'; onblur='guardarHC(gc20)'/></td>" 
							+"<td  width='2.8%'><input id='gc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc21)'; onblur='guardarHC(gc21)'/></td>" 
							+"<td  width='2.8%'><input id='gc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc22)'; onblur='guardarHC(gc22)'/></td>" 
							+"<td  width='2.8%'><input id='gc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc23)'; onblur='guardarHC(gc23)'/></td>" 
							+"<td  width='2.8%'><input id='gc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc00)'; onblur='guardarHC(gc00)'/></td>" 
							+"<td  width='2.8%'><input id='gc01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(gc01)'; onblur='guardarHC(gc01)'/></td>" 
							+"<td  width='2.8%'><input id='gc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc02)'; onblur='guardarHC(gc02)'/></td>" 
							+"<td  width='2.8%'><input id='gc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc03)'; onblur='guardarHC(gc03)'/></td>" 
							+"<td  width='2.8%'><input id='gc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc04)'; onblur='guardarHC(gc04)'/></td>" 
							+"<td  width='2.8%'><input id='gc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc05)'; onblur='guardarHC(gc05)'/></td>" 
							+"<td  width='2.8%'><input id='gc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc06)'; onblur='guardarHC(gc06)'/></td>"													
							+"</tr></table></td></tr>" +
							//ic
							//"<tr bgcolor='#D8D8D8' id='ocic' style='display: none'> <td width='30%'><div id='ic'>IC <br><a href='#' onclick='deshabilitarFilaSv(ic)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocic' style='display: none'> <td width='30%'><div id='ic'>IC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(ic)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='ic7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic7)'; onblur='guardarHC(ic7)'/></td>" 
							+"<td  width='2.8%'><input id='ic8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic8)'; onblur='guardarHC(ic8)'/></td>" 
							+"<td  width='2.8%'><input id='ic9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic9)'; onblur='guardarHC(ic9)'/></td>" 
							+"<td  width='2.8%'><input id='ic10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic10)'; onblur='guardarHC(ic10)'/></td>" 
							+"<td  width='2.8%'><input id='ic11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic11)'; onblur='guardarHC(ic11)'/></td>" 
							+"<td  width='2.8%'><input id='ic12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic12)'; onblur='guardarHC(ic12)'/></td>"
							+"<td  width='2.8%'><input id='ic13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic13)'; onblur='guardarHC(ic13)'/></td>" 
							+"<td  width='2.8%'><input id='ic14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic14)'; onblur='guardarHC(ic14)'/></td>" 
							+"<td  width='2.8%'><input id='ic15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic15)'; onblur='guardarHC(ic15)'/></td>" 
							+"<td  width='2.8%'><input id='ic16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic16)'; onblur='guardarHC(ic16)'/></td>" 
							+"<td  width='2.8%'><input id='ic17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic17)'; onblur='guardarHC(ic17)'/></td>" 
							+"<td  width='2.8%'><input id='ic18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic18)'; onblur='guardarHC(ic18)'/></td>" 
							+"<td  width='2.8%'><input id='ic19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic19)'; onblur='guardarHC(ic19)'/></td>" 
							+"<td  width='2.8%'><input id='ic20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic20)'; onblur='guardarHC(ic20)'/></td>" 
							+"<td  width='2.8%'><input id='ic21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic21)'; onblur='guardarHC(ic21)'/></td>" 
							+"<td  width='2.8%'><input id='ic22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic22)'; onblur='guardarHC(ic22)'/></td>" 
							+"<td  width='2.8%'><input id='ic23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic23)'; onblur='guardarHC(ic23)'/></td>" 
							+"<td  width='2.8%'><input id='ic00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic00)'; onblur='guardarHC(ic00)'/></td>" 
							+"<td  width='2.8%'><input id='ic01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(ic01)'; onblur='guardarHC(ic01)'/></td>" 
							+"<td  width='2.8%'><input id='ic02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic02)'; onblur='guardarHC(ic02)'/></td>" 
							+"<td  width='2.8%'><input id='ic03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic03)'; onblur='guardarHC(ic03)'/></td>" 
							+"<td  width='2.8%'><input id='ic04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic04)'; onblur='guardarHC(ic04)'/></td>" 
							+"<td  width='2.8%'><input id='ic05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic05)'; onblur='guardarHC(ic05)'/></td>" 
							+"<td  width='2.8%'><input id='ic06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic06)'; onblur='guardarHC(ic06)'/></td>"													
							+"</tr></table></td></tr>" +
							//irvs
							//"<tr id='ocirvs' style='display: none'> <td width='30%'><div id='irvs'>IRVS <br><a href='#' onclick='deshabilitarFilaSv(irvs)'>Eliminar</a></div></td>" +
							"<tr id='ocirvs' style='display: none'> <td width='30%'><div id='irvs'>IRVS<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(irvs)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='irvs7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs7)'; onblur='guardarHC(irvs7)'/></td>" 
							+"<td  width='2.8%'><input id='irvs8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs8)'; onblur='guardarHC(irvs8)'/></td>" 
							+"<td  width='2.8%'><input id='irvs9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs9)'; onblur='guardarHC(irvs9)'/></td>" 
							+"<td  width='2.8%'><input id='irvs10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs10)'; onblur='guardarHC(irvs10)'/></td>" 
							+"<td  width='2.8%'><input id='irvs11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs11)'; onblur='guardarHC(irvs11)'/></td>" 
							+"<td  width='2.8%'><input id='irvs12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs12)'; onblur='guardarHC(irvs12)'/></td>"
							+"<td  width='2.8%'><input id='irvs13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs13)'; onblur='guardarHC(irvs13)'/></td>" 
							+"<td  width='2.8%'><input id='irvs14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs14)'; onblur='guardarHC(irvs14)'/></td>" 
							+"<td  width='2.8%'><input id='irvs15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs15)'; onblur='guardarHC(irvs15)'/></td>" 
							+"<td  width='2.8%'><input id='irvs16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs16)'; onblur='guardarHC(irvs16)'/></td>" 
							+"<td  width='2.8%'><input id='irvs17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs17)'; onblur='guardarHC(irvs17)'/></td>" 
							+"<td  width='2.8%'><input id='irvs18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs18)'; onblur='guardarHC(irvs18)'/></td>" 
							+"<td  width='2.8%'><input id='irvs19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs19)'; onblur='guardarHC(irvs19)'/></td>" 
							+"<td  width='2.8%'><input id='irvs20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs20)'; onblur='guardarHC(irvs20)'/></td>" 
							+"<td  width='2.8%'><input id='irvs21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs21)'; onblur='guardarHC(irvs21)'/></td>" 
							+"<td  width='2.8%'><input id='irvs22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs22)'; onblur='guardarHC(irvs22)'/></td>" 
							+"<td  width='2.8%'><input id='irvs23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs23)'; onblur='guardarHC(irvs23)'/></td>" 
							+"<td  width='2.8%'><input id='irvs00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs00)'; onblur='guardarHC(irvs00)'/></td>" 
							+"<td  width='2.8%'><input id='irvs01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(irvs01)'; onblur='guardarHC(irvs01)'/></td>" 
							+"<td  width='2.8%'><input id='irvs02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs02)'; onblur='guardarHC(irvs02)'/></td>" 
							+"<td  width='2.8%'><input id='irvs03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs03)'; onblur='guardarHC(irvs03)'/></td>" 
							+"<td  width='2.8%'><input id='irvs04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs04)'; onblur='guardarHC(irvs04)'/></td>" 
							+"<td  width='2.8%'><input id='irvs05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs05)'; onblur='guardarHC(irvs05)'/></td>" 
							+"<td  width='2.8%'><input id='irvs06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(irvs06)'; onblur='guardarHC(irvs06)'/></td>"													
							+"</tr></table></td></tr>" +
							//irvp
							//"<tr bgcolor='#D8D8D8' id='ocirvp' style='display: none'> <td width='30%'><div id='irvp'>IRVP <br><a href='#' onclick='deshabilitarFilaSv(irvp)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocirvp' style='display: none'> <td width='30%'><div id='irvp'>IRVP<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(irvp)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='irvp7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp7)'; onblur='guardarHC(irvp7)'/></td>" 
							+"<td  width='2.8%'><input id='irvp8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp8)'; onblur='guardarHC(irvp8)'/></td>" 
							+"<td  width='2.8%'><input id='irvp9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp9)'; onblur='guardarHC(irvp9)'/></td>" 
							+"<td  width='2.8%'><input id='irvp10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp10)'; onblur='guardarHC(irvp10)'/></td>" 
							+"<td  width='2.8%'><input id='irvp11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp11)'; onblur='guardarHC(irvp11)'/></td>" 
							+"<td  width='2.8%'><input id='irvp12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp12)'; onblur='guardarHC(irvp12)'/></td>"
							+"<td  width='2.8%'><input id='irvp13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp13)'; onblur='guardarHC(irvp13)'/></td>" 
							+"<td  width='2.8%'><input id='irvp14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp14)'; onblur='guardarHC(irvp14)'/></td>" 
							+"<td  width='2.8%'><input id='irvp15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp15)'; onblur='guardarHC(irvp15)'/></td>" 
							+"<td  width='2.8%'><input id='irvp16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp16)'; onblur='guardarHC(irvp16)'/></td>" 
							+"<td  width='2.8%'><input id='irvp17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp17)'; onblur='guardarHC(irvp17)'/></td>" 
							+"<td  width='2.8%'><input id='irvp18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp18)'; onblur='guardarHC(irvp18)'/></td>" 
							+"<td  width='2.8%'><input id='irvp19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp19)'; onblur='guardarHC(irvp19)'/></td>" 
							+"<td  width='2.8%'><input id='irvp20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp20)'; onblur='guardarHC(irvp20)'/></td>" 
							+"<td  width='2.8%'><input id='irvp21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp21)'; onblur='guardarHC(irvp21)'/></td>" 
							+"<td  width='2.8%'><input id='irvp22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp22)'; onblur='guardarHC(irvp22)'/></td>" 
							+"<td  width='2.8%'><input id='irvp23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp23)'; onblur='guardarHC(irvp23)'/></td>" 
							+"<td  width='2.8%'><input id='irvp00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp00)'; onblur='guardarHC(irvp00)'/></td>" 
							+"<td  width='2.8%'><input id='irvp01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(irvp01)'; onblur='guardarHC(irvp01)'/></td>" 
							+"<td  width='2.8%'><input id='irvp02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp02)'; onblur='guardarHC(irvp02)'/></td>" 
							+"<td  width='2.8%'><input id='irvp03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp03)'; onblur='guardarHC(irvp03)'/></td>" 
							+"<td  width='2.8%'><input id='irvp04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp04)'; onblur='guardarHC(irvp04)'/></td>" 
							+"<td  width='2.8%'><input id='irvp05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp05)'; onblur='guardarHC(irvp05)'/></td>" 
							+"<td  width='2.8%'><input id='irvp06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp06)'; onblur='guardarHC(irvp06)'/></td>"													
							+"</tr></table></td></tr>" +
							//itvi
							//"<tr id='ocitvi' style='display: none'> <td width='30%'><div id='itvi'>ITVI <br><a href='#' onclick='deshabilitarFilaSv(itvi)'>Eliminar</a></div></td>" +
							"<tr id='ocitvi' style='display: none'> <td width='30%'><div id='itvi'>ITVI<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(itvi)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='itvi7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi7)'; onblur='guardarHC(itvi7)'/></td>" 
							+"<td  width='2.8%'><input id='itvi8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi8)'; onblur='guardarHC(itvi8)'/></td>" 
							+"<td  width='2.8%'><input id='itvi9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi9)'; onblur='guardarHC(itvi9)'/></td>" 
							+"<td  width='2.8%'><input id='itvi10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi10)'; onblur='guardarHC(itvi10)'/></td>" 
							+"<td  width='2.8%'><input id='itvi11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi11)'; onblur='guardarHC(itvi11)'/></td>" 
							+"<td  width='2.8%'><input id='itvi12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi12)'; onblur='guardarHC(itvi12)'/></td>"
							+"<td  width='2.8%'><input id='itvi13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi13)'; onblur='guardarHC(itvi13)'/></td>" 
							+"<td  width='2.8%'><input id='itvi14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi14)'; onblur='guardarHC(itvi14)'/></td>" 
							+"<td  width='2.8%'><input id='itvi15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi15)'; onblur='guardarHC(itvi15)'/></td>" 
							+"<td  width='2.8%'><input id='itvi16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi16)'; onblur='guardarHC(itvi16)'/></td>" 
							+"<td  width='2.8%'><input id='itvi17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi17)'; onblur='guardarHC(itvi17)'/></td>" 
							+"<td  width='2.8%'><input id='itvi18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi18)'; onblur='guardarHC(itvi18)'/></td>" 
							+"<td  width='2.8%'><input id='itvi19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi19)'; onblur='guardarHC(itvi19)'/></td>" 
							+"<td  width='2.8%'><input id='itvi20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi20)'; onblur='guardarHC(itvi20)'/></td>" 
							+"<td  width='2.8%'><input id='itvi21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi21)'; onblur='guardarHC(itvi21)'/></td>" 
							+"<td  width='2.8%'><input id='itvi22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi22)'; onblur='guardarHC(itvi22)'/></td>" 
							+"<td  width='2.8%'><input id='itvi23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi23)'; onblur='guardarHC(itvi23)'/></td>" 
							+"<td  width='2.8%'><input id='itvi00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi00)'; onblur='guardarHC(itvi00)'/></td>" 
							+"<td  width='2.8%'><input id='itvi01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(itvi01)'; onblur='guardarHC(itvi01)'/></td>" 
							+"<td  width='2.8%'><input id='itvi02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi02)'; onblur='guardarHC(itvi02)'/></td>" 
							+"<td  width='2.8%'><input id='itvi03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi03)'; onblur='guardarHC(itvi03)'/></td>" 
							+"<td  width='2.8%'><input id='itvi04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi04)'; onblur='guardarHC(itvi04)'/></td>" 
							+"<td  width='2.8%'><input id='itvi05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi05)'; onblur='guardarHC(itvi05)'/></td>" 
							+"<td  width='2.8%'><input id='itvi06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi06)'; onblur='guardarHC(itvi06)'/></td>"													
							+"</tr></table></td></tr>" +
							//itvd
							//"<tr bgcolor='#D8D8D8' id='ocitvd' style='display: none'> <td width='30%'><div id='itvd'>ITVD <br><a href='#' onclick='deshabilitarFilaSv(itvd)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocitvd' style='display: none'> <td width='30%'><div id='itvd'>ITVD<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(itvd)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='itvd7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd7)'; onblur='guardarHC(itvd7)'/></td>" 
							+"<td  width='2.8%'><input id='itvd8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd8)'; onblur='guardarHC(itvd8)'/></td>" 
							+"<td  width='2.8%'><input id='itvd9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd9)'; onblur='guardarHC(itvd9)'/></td>" 
							+"<td  width='2.8%'><input id='itvd10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd10)'; onblur='guardarHC(itvd10)'/></td>" 
							+"<td  width='2.8%'><input id='itvd11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd11)'; onblur='guardarHC(itvd11)'/></td>" 
							+"<td  width='2.8%'><input id='itvd12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd12)'; onblur='guardarHC(itvd12)'/></td>"
							+"<td  width='2.8%'><input id='itvd13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd13)'; onblur='guardarHC(itvd13)'/></td>" 
							+"<td  width='2.8%'><input id='itvd14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd14)'; onblur='guardarHC(itvd14)'/></td>" 
							+"<td  width='2.8%'><input id='itvd15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd15)'; onblur='guardarHC(itvd15)'/></td>" 
							+"<td  width='2.8%'><input id='itvd16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd16)'; onblur='guardarHC(itvd16)'/></td>" 
							+"<td  width='2.8%'><input id='itvd17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd17)'; onblur='guardarHC(itvd17)'/></td>" 
							+"<td  width='2.8%'><input id='itvd18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd18)'; onblur='guardarHC(itvd18)'/></td>" 
							+"<td  width='2.8%'><input id='itvd19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd19)'; onblur='guardarHC(itvd19)'/></td>" 
							+"<td  width='2.8%'><input id='itvd20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd20)'; onblur='guardarHC(itvd20)'/></td>" 
							+"<td  width='2.8%'><input id='itvd21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd21)'; onblur='guardarHC(itvd21)'/></td>" 
							+"<td  width='2.8%'><input id='itvd22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd22)'; onblur='guardarHC(itvd22)'/></td>" 
							+"<td  width='2.8%'><input id='itvd23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd23)'; onblur='guardarHC(itvd23)'/></td>" 
							+"<td  width='2.8%'><input id='itvd00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd00)'; onblur='guardarHC(itvd00)'/></td>" 
							+"<td  width='2.8%'><input id='itvd01' type='text' style='border: 1px solid #585858' size='1'onkeyup='decimalsv(itvd01)'; onblur='guardarHC(itvd01)'/></td>" 
							+"<td  width='2.8%'><input id='itvd02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd02)'; onblur='guardarHC(itvd02)'/></td>" 
							+"<td  width='2.8%'><input id='itvd03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd03)'; onblur='guardarHC(itvd03)'/></td>" 
							+"<td  width='2.8%'><input id='itvd04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd04)'; onblur='guardarHC(itvd04)'/></td>" 
							+"<td  width='2.8%'><input id='itvd05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd05)'; onblur='guardarHC(itvd05)'/></td>" 
							+"<td  width='2.8%'><input id='itvd06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd06)'; onblur='guardarHC(itvd06)'/></td>"													
							+"</tr></table></td></tr>" +
							//PULSO
							"<tr id='ocdivpulso' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>PULSO</CENTER></div></td>" +
							"<td> <a href='#' onclick='ocultarPulso()'><font color='white'>Ocultar</font></a>" +
							"</td>"+
							"</tr>"+
							
							//"<tr id='ocpuld' style='display: none'> <td width='30%'><div id='puld'>D <br><a href='#' onclick='deshabilitarFilaSv(puld)'>Eliminar</a></div></td>" +
							"<tr id='ocpuld' style='display: none'> <td width='30%'><div id='puld'>D<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(puld)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='puld7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld7)'; onblur='guardarHC(puld7)'/></td>" 
							+"<td  width='2.8%'><input id='puld8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld8)'; onblur='guardarHC(puld8)'/></td>" 
							+"<td  width='2.8%'><input id='puld9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld9)'; onblur='guardarHC(puld9)'/></td>" 
							+"<td  width='2.8%'><input id='puld10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld10)'; onblur='guardarHC(puld10)'/></td>" 
							+"<td  width='2.8%'><input id='puld11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld11)'; onblur='guardarHC(puld11)'/></td>" 
							+"<td  width='2.8%'><input id='puld12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld12)'; onblur='guardarHC(puld12)'/></td>"
							+"<td  width='2.8%'><input id='puld13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld13)'; onblur='guardarHC(puld13)'/></td>" 
							+"<td  width='2.8%'><input id='puld14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld14)'; onblur='guardarHC(puld14)'/></td>" 
							+"<td  width='2.8%'><input id='puld15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld15)'; onblur='guardarHC(puld15)'/></td>" 
							+"<td  width='2.8%'><input id='puld16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld16)'; onblur='guardarHC(puld16)'/></td>" 
							+"<td  width='2.8%'><input id='puld17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld17)'; onblur='guardarHC(puld17)'/></td>" 
							+"<td  width='2.8%'><input id='puld18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld18)'; onblur='guardarHC(puld18)'/></td>" 
							+"<td  width='2.8%'><input id='puld19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld19)'; onblur='guardarHC(puld19)'/></td>" 
							+"<td  width='2.8%'><input id='puld20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld20)'; onblur='guardarHC(puld20)'/></td>" 
							+"<td  width='2.8%'><input id='puld21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld21)'; onblur='guardarHC(puld21)'/></td>" 
							+"<td  width='2.8%'><input id='puld22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld22)'; onblur='guardarHC(puld22)'/></td>" 
							+"<td  width='2.8%'><input id='puld23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld23)'; onblur='guardarHC(puld23)'/></td>" 
							+"<td  width='2.8%'><input id='puld00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld00)'; onblur='guardarHC(puld00)'/></td>" 
							+"<td  width='2.8%'><input id='puld01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(puld01)'; onblur='guardarHC(puld01)'/></td>" 
							+"<td  width='2.8%'><input id='puld02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld02)'; onblur='guardarHC(puld02)'/></td>" 
							+"<td  width='2.8%'><input id='puld03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld03)'; onblur='guardarHC(puld03)'/></td>" 
							+"<td  width='2.8%'><input id='puld04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld04)'; onblur='guardarHC(puld04)'/></td>" 
							+"<td  width='2.8%'><input id='puld05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld05)'; onblur='guardarHC(puld05)'/></td>" 
							+"<td  width='2.8%'><input id='puld06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld06)'; onblur='guardarHC(puld06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							//"<tr bgcolor='#D8D8D8' id='ocpuli' style='display: none'> <td width='30%'><div id='puli'>I <br><a href='#' onclick='deshabilitarFilaSv(puli)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocpuli' style='display: none'> <td width='30%'><div id='puli'>I<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(puli)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='puli7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli7)'; onblur='guardarHC(puli7)'/></td>" 
							+"<td  width='2.8%'><input id='puli8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli8)'; onblur='guardarHC(puli8)'/></td>" 
							+"<td  width='2.8%'><input id='puli9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli9)'; onblur='guardarHC(puli9)'/></td>" 
							+"<td  width='2.8%'><input id='puli10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli10)'; onblur='guardarHC(puli10)'/></td>" 
							+"<td  width='2.8%'><input id='puli11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli11)'; onblur='guardarHC(puli11)'/></td>" 
							+"<td  width='2.8%'><input id='puli12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli12)'; onblur='guardarHC(puli12)'/></td>"
							+"<td  width='2.8%'><input id='puli13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli13)'; onblur='guardarHC(puli13)'/></td>" 
							+"<td  width='2.8%'><input id='puli14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli14)'; onblur='guardarHC(puli14)'/></td>" 
							+"<td  width='2.8%'><input id='puli15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli15)'; onblur='guardarHC(puli15)'/></td>" 
							+"<td  width='2.8%'><input id='puli16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli16)'; onblur='guardarHC(puli16)'/></td>" 
							+"<td  width='2.8%'><input id='puli17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli17)'; onblur='guardarHC(puli17)'/></td>" 
							+"<td  width='2.8%'><input id='puli18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli18)'; onblur='guardarHC(puli18)'/></td>" 
							+"<td  width='2.8%'><input id='puli19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli19)'; onblur='guardarHC(puli19)'/></td>" 
							+"<td  width='2.8%'><input id='puli20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli20)'; onblur='guardarHC(puli20)'/></td>" 
							+"<td  width='2.8%'><input id='puli21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli21)'; onblur='guardarHC(puli21)'/></td>" 
							+"<td  width='2.8%'><input id='puli22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli22)'; onblur='guardarHC(puli22)'/></td>" 
							+"<td  width='2.8%'><input id='puli23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli23)'; onblur='guardarHC(puli23)'/></td>" 
							+"<td  width='2.8%'><input id='puli00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli00)'; onblur='guardarHC(puli00)'/></td>" 
							+"<td  width='2.8%'><input id='puli01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(puli01)'; onblur='guardarHC(puli01)'/></td>" 
							+"<td  width='2.8%'><input id='puli02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli02)'; onblur='guardarHC(puli02)'/></td>" 
							+"<td  width='2.8%'><input id='puli03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli03)'; onblur='guardarHC(puli03)'/></td>" 
							+"<td  width='2.8%'><input id='puli04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli04)'; onblur='guardarHC(puli04)'/></td>" 
							+"<td  width='2.8%'><input id='puli05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli05)'; onblur='guardarHC(puli05)'/></td>" 
							+"<td  width='2.8%'><input id='puli06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli06)'; onblur='guardarHC(puli06)'/></td>"													
							+"</tr></table></td></tr>" +
							//????
							"<tr bgcolor='#2E9AFE' id='ocx' style='display: none'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER></CENTER></div></td>" +
							"<td>  <a href='#' onclick='ocultarX()'><font color='white'>Ocultar</font></a>" +
							"</td>"+
							"</tr>"+
							
							//"<tr id='ocesg' style='display: none'> <td width='30%'><div id='esg'>ESCALA DE GLASGOW <br><a href='#' onclick='deshabilitarFilaSv(esg)'>Eliminar</a></div></td>" +
							"<tr id='ocesg' style='display: none'> <td width='30%'><div id='esg'>ESCALA DE GLASGOW<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(esg)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='esg7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg7)'; onblur='guardarHC(esg7)'/></td>" 
							+"<td  width='2.8%'><input id='esg8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg8)'; onblur='guardarHC(esg8)'/></td>" 
							+"<td  width='2.8%'><input id='esg9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg9)'; onblur='guardarHC(esg9)'/></td>" 
							+"<td  width='2.8%'><input id='esg10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg10)'; onblur='guardarHC(esg10)'/></td>" 
							+"<td  width='2.8%'><input id='esg11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg11)'; onblur='guardarHC(esg11)'/></td>" 
							+"<td  width='2.8%'><input id='esg12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg12)'; onblur='guardarHC(esg12)'/></td>"
							+"<td  width='2.8%'><input id='esg13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg13)'; onblur='guardarHC(esg13)'/></td>" 
							+"<td  width='2.8%'><input id='esg14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg14)'; onblur='guardarHC(esg14)'/></td>" 
							+"<td  width='2.8%'><input id='esg15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg15)'; onblur='guardarHC(esg15)'/></td>" 
							+"<td  width='2.8%'><input id='esg16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg16)'; onblur='guardarHC(esg16)'/></td>" 
							+"<td  width='2.8%'><input id='esg17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg17)'; onblur='guardarHC(esg17)'/></td>" 
							+"<td  width='2.8%'><input id='esg18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg18)'; onblur='guardarHC(esg18)'/></td>" 
							+"<td  width='2.8%'><input id='esg19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg19)'; onblur='guardarHC(esg19)'/></td>" 
							+"<td  width='2.8%'><input id='esg20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg20)'; onblur='guardarHC(esg20)'/></td>" 
							+"<td  width='2.8%'><input id='esg21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg21)'; onblur='guardarHC(esg21)'/></td>" 
							+"<td  width='2.8%'><input id='esg22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg22)'; onblur='guardarHC(esg22)'/></td>" 
							+"<td  width='2.8%'><input id='esg23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg23)'; onblur='guardarHC(esg23)'/></td>" 
							+"<td  width='2.8%'><input id='esg00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg00)'; onblur='guardarHC(esg00)'/></td>" 
							+"<td  width='2.8%'><input id='esg01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(esg01)'; onblur='guardarHC(esg01)'/></td>" 
							+"<td  width='2.8%'><input id='esg02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg02)'; onblur='guardarHC(esg02)'/></td>" 
							+"<td  width='2.8%'><input id='esg03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg03)'; onblur='guardarHC(esg03)'/></td>" 
							+"<td  width='2.8%'><input id='esg04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg04)'; onblur='guardarHC(esg04)'/></td>" 
							+"<td  width='2.8%'><input id='esg05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg05)'; onblur='guardarHC(esg05)'/></td>" 
							+"<td  width='2.8%'><input id='esg06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg06)'; onblur='guardarHC(esg06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							//"<tr id='ocestc' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='estc'>ESTADO DE CONCIENCIA <br><a href='#' onclick='deshabilitarFilaSv(estc)'>Eliminar</a></div></td>" +
							"<tr id='ocestc' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='estc'>ESTADO DE CONCIENCIA <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(estc)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='estc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc7)'; onblur='guardarHC(estc7)'/></td>" 
							+"<td  width='2.8%'><input id='estc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc8)'; onblur='guardarHC(estc8)'/></td>" 
							+"<td  width='2.8%'><input id='estc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc9)'; onblur='guardarHC(estc9)'/></td>" 
							+"<td  width='2.8%'><input id='estc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc10)'; onblur='guardarHC(estc10)'/></td>" 
							+"<td  width='2.8%'><input id='estc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc11)'; onblur='guardarHC(estc11)'/></td>" 
							+"<td  width='2.8%'><input id='estc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc12)'; onblur='guardarHC(estc12)'/></td>"
							+"<td  width='2.8%'><input id='estc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc13)'; onblur='guardarHC(estc13)'/></td>" 
							+"<td  width='2.8%'><input id='estc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc14)'; onblur='guardarHC(estc14)'/></td>" 
							+"<td  width='2.8%'><input id='estc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc15)'; onblur='guardarHC(estc15)'/></td>" 
							+"<td  width='2.8%'><input id='estc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc16)'; onblur='guardarHC(estc16)'/></td>" 
							+"<td  width='2.8%'><input id='estc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc17)'; onblur='guardarHC(estc17)'/></td>" 
							+"<td  width='2.8%'><input id='estc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc18)'; onblur='guardarHC(estc18)'/></td>" 
							+"<td  width='2.8%'><input id='estc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc19)'; onblur='guardarHC(estc19)'/></td>" 
							+"<td  width='2.8%'><input id='estc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc20)'; onblur='guardarHC(estc20)'/></td>" 
							+"<td  width='2.8%'><input id='estc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc21)'; onblur='guardarHC(estc21)'/></td>" 
							+"<td  width='2.8%'><input id='estc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc22)'; onblur='guardarHC(estc22)'/></td>" 
							+"<td  width='2.8%'><input id='estc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc23)'; onblur='guardarHC(estc23)'/></td>" 
							+"<td  width='2.8%'><input id='estc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc00)'; onblur='guardarHC(estc00)'/></td>" 
							+"<td  width='2.8%'><input id='estc01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(estc01)'; onblur='guardarHC(estc01)'/></td>" 
							+"<td  width='2.8%'><input id='estc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc02)'; onblur='guardarHC(estc02)'/></td>" 
							+"<td  width='2.8%'><input id='estc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc03)'; onblur='guardarHC(estc03)'/></td>" 
							+"<td  width='2.8%'><input id='estc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc04)'; onblur='guardarHC(estc04)'/></td>" 
							+"<td  width='2.8%'><input id='estc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc05)'; onblur='guardarHC(estc05)'/></td>" 
							+"<td  width='2.8%'><input id='estc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc06)'; onblur='guardarHC(estc06)'/></td>"													
							+"</tr></table></td></tr>" +
							//PUPILAS
							"<tr id='ocdivpupilas' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>PUPILAS</CENTER></div></td>" +
							"<td>  <a href='#' onclick='ocultarPupilas()'><font color='white'>Ocultar</font></a>" +
							"</td>"+
							"</tr>"+
							
							"<tr id='ocdivd' style='display: none' bgcolor='#FA5858'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>D</CENTER></div></td>" +
							
							"</tr>"+
							
							//"<tr id='octamd' style='display: none'> <td width='30%'><div id='tamd'>TAMA\u00D1O <br><a href='#' onclick='deshabilitarFilaSv(tamd)'>Eliminar</a></div></td>" +
							"<tr id='octamd' style='display: none'> <td width='30%'><div id='tamd'>TAMA\u00D1O <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tamd)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tamd7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd7)'; onblur='guardarHC(tamd7)'/></td>" 
							+"<td  width='2.8%'><input id='tamd8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd8)'; onblur='guardarHC(tamd8)'/></td>" 
							+"<td  width='2.8%'><input id='tamd9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd9)'; onblur='guardarHC(tamd9)'/></td>" 
							+"<td  width='2.8%'><input id='tamd10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd10)'; onblur='guardarHC(tamd10)'/></td>" 
							+"<td  width='2.8%'><input id='tamd11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd11)'; onblur='guardarHC(tamd11)'/></td>" 
							+"<td  width='2.8%'><input id='tamd12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd12)'; onblur='guardarHC(tamd12)'/></td>"
							+"<td  width='2.8%'><input id='tamd13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd13)'; onblur='guardarHC(tamd13)'/></td>" 
							+"<td  width='2.8%'><input id='tamd14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd14)'; onblur='guardarHC(tamd14)'/></td>" 
							+"<td  width='2.8%'><input id='tamd15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd15)'; onblur='guardarHC(tamd15)'/></td>" 
							+"<td  width='2.8%'><input id='tamd16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd16)'; onblur='guardarHC(tamd16)'/></td>" 
							+"<td  width='2.8%'><input id='tamd17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd17)'; onblur='guardarHC(tamd17)'/></td>" 
							+"<td  width='2.8%'><input id='tamd18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd18)'; onblur='guardarHC(tamd18)'/></td>" 
							+"<td  width='2.8%'><input id='tamd19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd19)'; onblur='guardarHC(tamd19)'/></td>" 
							+"<td  width='2.8%'><input id='tamd20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd20)'; onblur='guardarHC(tamd20)'/></td>" 
							+"<td  width='2.8%'><input id='tamd21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd21)'; onblur='guardarHC(tamd21)'/></td>" 
							+"<td  width='2.8%'><input id='tamd22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd22)'; onblur='guardarHC(tamd22)'/></td>" 
							+"<td  width='2.8%'><input id='tamd23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd23)'; onblur='guardarHC(tamd23)'/></td>" 
							+"<td  width='2.8%'><input id='tamd00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd00)'; onblur='guardarHC(tamd00)'/></td>" 
							+"<td  width='2.8%'><input id='tamd01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(tamd01)'; onblur='guardarHC(tamd01)'/></td>" 
							+"<td  width='2.8%'><input id='tamd02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd02)'; onblur='guardarHC(tamd02)'/></td>" 
							+"<td  width='2.8%'><input id='tamd03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd03)'; onblur='guardarHC(tamd03)'/></td>" 
							+"<td  width='2.8%'><input id='tamd04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd04)'; onblur='guardarHC(tamd04)'/></td>" 
							+"<td  width='2.8%'><input id='tamd05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd05)'; onblur='guardarHC(tamd05)'/></td>" 
							+"<td  width='2.8%'><input id='tamd06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd06)'; onblur='guardarHC(tamd06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							//"<tr bgcolor='#D8D8D8' id='ocrccd' style='display: none'> <td width='30%'><div id='rccd'>REACCI\u00D3N <br><a href='#' onclick='deshabilitarFilaSv(rccd)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocrccd' style='display: none'> <td width='30%'><div id='rccd'>REACCI\u00D3N<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(rccd)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='rccd7' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd7)'/></td>" 
							+"<td  width='2.8%'><input id='rccd8' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd8)'/></td>" 
							+"<td  width='2.8%'><input id='rccd9' type='text' style='border: 1px solid #585858' size='1'  onblur='guardarHC(rccd9)'/></td>" 
							+"<td  width='2.8%'><input id='rccd10' type='text' style='border: 1px solid #585858' size='1'  onblur='guardarHC(rccd10)'/></td>" 
							+"<td  width='2.8%'><input id='rccd11' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd11)'/></td>" 
							+"<td  width='2.8%'><input id='rccd12' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd12)'/></td>"
							+"<td  width='2.8%'><input id='rccd13' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd13)'/></td>" 
							+"<td  width='2.8%'><input id='rccd14' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd14)'/></td>" 
							+"<td  width='2.8%'><input id='rccd15' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd15)'/></td>" 
							+"<td  width='2.8%'><input id='rccd16' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd16)'/></td>" 
							+"<td  width='2.8%'><input id='rccd17' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd17)'/></td>" 
							+"<td  width='2.8%'><input id='rccd18' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd18)'/></td>" 
							+"<td  width='2.8%'><input id='rccd19' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd19)'/></td>" 
							+"<td  width='2.8%'><input id='rccd20' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd20)'/></td>" 
							+"<td  width='2.8%'><input id='rccd21' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd21)'/></td>" 
							+"<td  width='2.8%'><input id='rccd22' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd22)'/></td>" 
							+"<td  width='2.8%'><input id='rccd23' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd23)'/></td>" 
							+"<td  width='2.8%'><input id='rccd00' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd00)'/></td>" 
							+"<td  width='2.8%'><input id='rccd01' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd01)'/></td>" 
							+"<td  width='2.8%'><input id='rccd02' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd02)'/></td>" 
							+"<td  width='2.8%'><input id='rccd03' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd03)'/></td>" 
							+"<td  width='2.8%'><input id='rccd04' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd04)'/></td>" 
							+"<td  width='2.8%'><input id='rccd05' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd05)'/></td>" 
							+"<td  width='2.8%'><input id='rccd06' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd06)'/></td>"													
							+"</tr></table></td></tr>" +
							//I
                            "<tr id='ocdivi' style='display: none' bgcolor='#FA5858'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>I</CENTER></div></td>" +
							
							"</tr>"+
							//"<tr id='octami' style='display: none'> <td width='30%'><div id='tami'>TAMA\u00D1O <br><a href='#' onclick='deshabilitarFilaSv(tami)'>Eliminar</a></div></td>" +
							"<tr id='octami' style='display: none'> <td width='30%'><div id='tami'>TAMA\u00D1O<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tami)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='tami7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami7)'; onblur='guardarHC(tami7)'/></td>" 
							+"<td  width='2.8%'><input id='tami8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami8)'; onblur='guardarHC(tami8)'/></td>" 
							+"<td  width='2.8%'><input id='tami9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami9)'; onblur='guardarHC(tami9)'/></td>" 
							+"<td  width='2.8%'><input id='tami10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami10)'; onblur='guardarHC(tami10)'/></td>" 
							+"<td  width='2.8%'><input id='tami11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami11)'; onblur='guardarHC(tami11)'/></td>" 
							+"<td  width='2.8%'><input id='tami12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami12)'; onblur='guardarHC(tami12)'/></td>"
							+"<td  width='2.8%'><input id='tami13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami13)'; onblur='guardarHC(tami13)'/></td>" 
							+"<td  width='2.8%'><input id='tami14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami14)'; onblur='guardarHC(tami14)'/></td>" 
							+"<td  width='2.8%'><input id='tami15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami15)'; onblur='guardarHC(tami15)'/></td>" 
							+"<td  width='2.8%'><input id='tami16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami16)'; onblur='guardarHC(tami16)'/></td>" 
							+"<td  width='2.8%'><input id='tami17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami17)'; onblur='guardarHC(tami17)'/></td>" 
							+"<td  width='2.8%'><input id='tami18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami18)'; onblur='guardarHC(tami18)'/></td>" 
							+"<td  width='2.8%'><input id='tami19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami19)'; onblur='guardarHC(tami19)'/></td>" 
							+"<td  width='2.8%'><input id='tami20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami20)'; onblur='guardarHC(tami20)'/></td>" 
							+"<td  width='2.8%'><input id='tami21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami21)'; onblur='guardarHC(tami21)'/></td>" 
							+"<td  width='2.8%'><input id='tami22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami22)'; onblur='guardarHC(tami22)'/></td>" 
							+"<td  width='2.8%'><input id='tami23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami23)'; onblur='guardarHC(tami23)'/></td>" 
							+"<td  width='2.8%'><input id='tami00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami00)'; onblur='guardarHC(tami00)'/></td>" 
							+"<td  width='2.8%'><input id='tami01' type='text' style='border: 1px solid #585858' size='1'onkeyup='soloNumerossv(tami01)'; onblur='guardarHC(tami01)'/></td>" 
							+"<td  width='2.8%'><input id='tami02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami02)'; onblur='guardarHC(tami02)'/></td>" 
							+"<td  width='2.8%'><input id='tami03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami03)'; onblur='guardarHC(tami03)'/></td>" 
							+"<td  width='2.8%'><input id='tami04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami04)'; onblur='guardarHC(tami04)'/></td>" 
							+"<td  width='2.8%'><input id='tami05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami05)'; onblur='guardarHC(tami05)'/></td>" 
							+"<td  width='2.8%'><input id='tami06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami06)'; onblur='guardarHC(tami06)'/></td>"													
							+"</tr></table></td></tr>" +
							
							//"<tr bgcolor='#D8D8D8' id='ocrcci' style='display: none'> <td width='30%'><div id='rcci'>REACCI\u00D3N <br><a href='#' onclick='deshabilitarFilaSv(rcci)'>Eliminar</a></div></td>" +
							"<tr bgcolor='#D8D8D8' id='ocrcci' style='display: none'> <td width='30%'><div id='rcci'>REACCI\u00D3N<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(rcci)' style='cursor:pointer;' /></div></td>" +
							"<td width='70%'><table width='100%' border='0' ><tr>" +
							"<td width='2.8%'><input id='rcci7' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci7)'/></td>" 
							+"<td  width='2.8%'><input id='rcci8' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci8)'/></td>" 
							+"<td  width='2.8%'><input id='rcci9' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci9)'/></td>" 
							+"<td  width='2.8%'><input id='rcci10' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci10)'/></td>" 
							+"<td  width='2.8%'><input id='rcci11' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci11)'/></td>" 
							+"<td  width='2.8%'><input id='rcci12' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci12)'/></td>"
							+"<td  width='2.8%'><input id='rcci13' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci13)'/></td>" 
							+"<td  width='2.8%'><input id='rcci14' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci14)'/></td>" 
							+"<td  width='2.8%'><input id='rcci15' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci15)'/></td>" 
							+"<td  width='2.8%'><input id='rcci16' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci16)'/></td>" 
							+"<td  width='2.8%'><input id='rcci17' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci17)'/></td>" 
							+"<td  width='2.8%'><input id='rcci18' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci18)'/></td>" 
							+"<td  width='2.8%'><input id='rcci19' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci19)'/></td>" 
							+"<td  width='2.8%'><input id='rcci20' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci20)'/></td>" 
							+"<td  width='2.8%'><input id='rcci21' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci21)'/></td>" 
							+"<td  width='2.8%'><input id='rcci22' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci22)'/></td>" 
							+"<td  width='2.8%'><input id='rcci23' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci23)'/></td>" 
							+"<td  width='2.8%'><input id='rcci00' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci00)'/></td>" 
							+"<td  width='2.8%'><input id='rcci01' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci01)'/></td>" 
							+"<td  width='2.8%'><input id='rcci02' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci02)'/></td>" 
							+"<td  width='2.8%'><input id='rcci03' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci03)'/></td>" 
							+"<td  width='2.8%'><input id='rcci04' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci04)'/></td>" 
							+"<td  width='2.8%'><input id='rcci05' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci05)'/></td>" 
							+"<td  width='2.8%'><input id='rcci06' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci06)'/></td>"													
							+"</tr></table></td></tr>" +
							//"</div>"+
							"</table></div></td></tr>"+
						   "</table>");
							
					
							
							/*
							// es el dia actual se ponen para que puedan ser asignados
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"7");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>07 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>07 AM </td><td><input name='txtTalla' type='text' id='txtTalla7'></td><td><input name='txtPeso' type='text' id='txtPeso7'></td><td><input name='txtTA' type='text' id='txtTA7'></td><td><input name='txtFC' type='text' id='txtFC7'></td><td><input name='txtFR' type='text' id='txtFR7'></td><td><input name='txtPulso' type='text' id='txtPulso7'></td><td><a href='#' onclick='GuardarSV(7)'>Guardar</a></td></tr>");
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"8");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>08 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>08 AM </td><td><input name='txtTalla' type='text' id='txtTalla8'></td><td><input name='txtPeso' type='text' id='txtPeso8'></td><td><input name='txtTA' type='text' id='txtTA8'></td><td><input name='txtFC' type='text' id='txtFC8'></td><td><input name='txtFR' type='text' id='txtFR8'></td><td><input name='txtPulso' type='text' id='txtPulso8'></td><td><a href='#' onclick='GuardarSV(8)'>Guardar</a></td></tr>");
								}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"9");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>09 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>09 AM </td><td><input name='txtTalla' type='text' id='txtTalla9'></td><td><input name='txtPeso' type='text' id='txtPeso9'></td><td><input name='txtTA' type='text' id='txtTA9'></td><td><input name='txtFC' type='text' id='txtFC9'></td><td><input name='txtFR' type='text' id='txtFR9'></td><td><input name='txtPulso' type='text' id='txtPulso9'></td><td><a href='#' onclick='GuardarSV(9)'>Guardar</a></td></tr>");
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"10");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>10 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>10 AM </td><td><input name='txtTalla' type='text' id='txtTalla10'></td><td><input name='txtPeso' type='text' id='txtPeso10'></td><td><input name='txtTA' type='text' id='txtTA10'></td><td><input name='txtFC' type='text' id='txtFC10'></td><td><input name='txtFR' type='text' id='txtFR10'></td><td><input name='txtPulso' type='text' id='txtPulso10'></td><td><a href='#' onclick='GuardarSV(10)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"11");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>11 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>11 AM </td><td><input name='txtTalla' type='text' id='txtTalla11'></td><td><input name='txtPeso' type='text' id='txtPeso11'></td><td><input name='txtTA' type='text' id='txtTA11'></td><td><input name='txtFC' type='text' id='txtFC11'></td><td><input name='txtFR' type='text' id='txtFR11'></td><td><input name='txtPulso' type='text' id='txtPulso11'></td><td><a href='#' onclick='GuardarSV(11)'>Guardar</a></td></tr>");
							
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"12");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>12 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>12 PM </td><td><input name='txtTalla' type='text' id='txtTalla12'></td><td><input name='txtPeso' type='text' id='txtPeso12'></td><td><input name='txtTA' type='text' id='txtTA12'></td><td><input name='txtFC' type='text' id='txtFC12'></td><td><input name='txtFR' type='text' id='txtFR12'></td><td><input name='txtPulso' type='text' id='txtPulso12'></td><td><a href='#' onclick='GuardarSV(12)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"13");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>01 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>01 PM </td><td><input name='txtTalla' type='text' id='txtTalla13'></td><td><input name='txtPeso' type='text' id='txtPeso13'></td><td><input name='txtTA' type='text' id='txtTA13'></td><td><input name='txtFC' type='text' id='txtFC13'></td><td><input name='txtFR' type='text' id='txtFR13'></td><td><input name='txtPulso' type='text' id='txtPulso13'></td><td><a href='#' onclick='GuardarSV(13)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"14");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>02 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>02 PM </td><td><input name='txtTalla' type='text' id='txtTalla14'></td><td><input name='txtPeso' type='text' id='txtPeso14'></td><td><input name='txtTA' type='text' id='txtTA14'></td><td><input name='txtFC' type='text' id='txtFC14'></td><td><input name='txtFR' type='text' id='txtFR14'></td><td><input name='txtPulso' type='text' id='txtPulso14'></td><td><a href='#' onclick='GuardarSV(14)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"15");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>03 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>03 PM </td><td><input name='txtTalla' type='text' id='txtTalla15'></td><td><input name='txtPeso' type='text' id='txtPeso15'></td><td><input name='txtTA' type='text' id='txtTA15'></td><td><input name='txtFC' type='text' id='txtFC15'></td><td><input name='txtFR' type='text' id='txtFR15'></td><td><input name='txtPulso' type='text' id='txtPulso15'></td><td><a href='#' onclick='GuardarSV(15)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"16");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>04 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>04 PM </td><td><input name='txtTalla' type='text' id='txtTalla16'></td><td><input name='txtPeso' type='text' id='txtPeso16'></td><td><input name='txtTA' type='text' id='txtTA16'></td><td><input name='txtFC' type='text' id='txtFC16'></td><td><input name='txtFR' type='text' id='txtFR16'></td><td><input name='txtPulso' type='text' id='txtPulso16'></td><td><a href='#' onclick='GuardarSV(16)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"17");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>05 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>05 PM </td><td><input name='txtTalla' type='text' id='txtTalla17'></td><td><input name='txtPeso' type='text' id='txtPeso17'></td><td><input name='txtTA' type='text' id='txtTA17'></td><td><input name='txtFC' type='text' id='txtFC17'></td><td><input name='txtFR' type='text' id='txtFR17'></td><td><input name='txtPulso' type='text' id='txtPulso17'></td><td><a href='#' onclick='GuardarSV(17)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"18");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>06 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>06 PM </td><td><input name='txtTalla' type='text' id='txtTalla18'></td><td><input name='txtPeso' type='text' id='txtPeso18'></td><td><input name='txtTA' type='text' id='txtTA18'></td><td><input name='txtFC' type='text' id='txtFC18'></td><td><input name='txtFR' type='text' id='txtFR18'></td><td><input name='txtPulso' type='text' id='txtPulso18'></td><td><a href='#' onclick='GuardarSV(18)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"19");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>07 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>07 PM </td><td><input name='txtTalla' type='text' id='txtTalla19'></td><td><input name='txtPeso' type='text' id='txtPeso19'></td><td><input name='txtTA' type='text' id='txtTA19'></td><td><input name='txtFC' type='text' id='txtFC19'></td><td><input name='txtFR' type='text' id='txtFR19'></td><td><input name='txtPulso' type='text' id='txtPulso19'></td><td><a href='#' onclick='GuardarSV(19)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"20");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>08 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>08 PM </td><td><input name='txtTalla' type='text' id='txtTalla20'></td><td><input name='txtPeso' type='text' id='txtPeso20'></td><td><input name='txtTA' type='text' id='txtTA20'></td><td><input name='txtFC' type='text' id='txtFC20'></td><td><input name='txtFR' type='text' id='txtFR20'></td><td><input name='txtPulso' type='text' id='txtPulso20'></td><td><a href='#' onclick='GuardarSV(20)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"21");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>09 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}
							else{
								out.print("  <tr><td>09 PM </td><td><input name='txtTalla' type='text' id='txtTalla21'></td><td><input name='txtPeso' type='text' id='txtPeso21'></td><td><input name='txtTA' type='text' id='txtTA21'></td><td><input name='txtFC' type='text' id='txtFC21'></td><td><input name='txtFR' type='text' id='txtFR21'></td><td><input name='txtPulso' type='text' id='txtPulso21'></td><td><a href='#' onclick='GuardarSV(21)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"22");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>10 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>10 PM </td><td><input name='txtTalla' type='text' id='txtTalla22'></td><td><input name='txtPeso' type='text' id='txtPeso22'></td><td><input name='txtTA' type='text' id='txtTA22'></td><td><input name='txtFC' type='text' id='txtFC22'></td><td><input name='txtFR' type='text' id='txtFR22'></td><td><input name='txtPulso' type='text' id='txtPulso22'></td><td><a href='#' onclick='GuardarSV(22)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"23");
							if(rs1.next()){
								out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>11 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("  <tr><td>11 PM </td><td><input name='txtTalla' type='text' id='txtTalla23'></td><td><input name='txtPeso' type='text' id='txtPeso23'></td><td><input name='txtTA' type='text' id='txtTA23'></td><td><input name='txtFC' type='text' id='txtFC23'></td><td><input name='txtFR' type='text' id='txtFR23'></td><td><input name='txtPulso' type='text' id='txtPulso23'></td><td><a href='#' onclick='GuardarSV(23)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"0");
							if(rs1.next()){
								out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>12 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("	 <tr><td>12 AM </td><td><input name='txtTalla' type='text' id='txtTalla0'></td><td><input name='txtPeso' type='text' id='txtPeso0'></td><td><input name='txtTA' type='text' id='txtTA0'></td><td><input name='txtFC' type='text' id='txtFC0'></td><td><input name='txtFR' type='text' id='txtFR0'></td><td><input name='txtPulso' type='text' id='txtPulso0'></td><td><a href='#' onclick='GuardarSV(0)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"1");
							if(rs1.next()){
								out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>01 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("	 <tr><td>01 AM </td><td><input name='txtTalla' type='text' id='txtTalla1'></td><td><input name='txtPeso' type='text' id='txtPeso1'></td><td><input name='txtTA' type='text' id='txtTA1'></td><td><input name='txtFC' type='text' id='txtFC1'></td><td><input name='txtFR' type='text' id='txtFR1'></td><td><input name='txtPulso' type='text' id='txtPulso1'></td><td><a href='#' onclick='GuardarSV(1)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"2");
							if(rs1.next()){
								out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>02 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("	 <tr><td>02 AM </td><td><input name='txtTalla' type='text' id='txtTalla2'></td><td><input name='txtPeso' type='text' id='txtPeso2'></td><td><input name='txtTA' type='text' id='txtTA2'></td><td><input name='txtFC' type='text' id='txtFC2'></td><td><input name='txtFR' type='text' id='txtFR2'></td><td><input name='txtPulso' type='text' id='txtPulso2'></td><td><a href='#' onclick='GuardarSV(2)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"3");
							if(rs1.next()){
								out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>03 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("	 <tr><td>03 AM </td><td><input name='txtTalla' type='text' id='txtTalla3'></td><td><input name='txtPeso' type='text' id='txtPeso3'></td><td><input name='txtTA' type='text' id='txtTA3'></td><td><input name='txtFC' type='text' id='txtFC3'></td><td><input name='txtFR' type='text' id='txtFR3'></td><td><input name='txtPulso' type='text' id='txtPulso3'></td><td><a href='#' onclick='GuardarSV(3)'>Guardar</a></td></tr>");
								
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"4");
							if(rs1.next()){
								out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>04 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("	 <tr><td>04 AM </td><td><input name='txtTalla' type='text' id='txtTalla4'></td><td><input name='txtPeso' type='text' id='txtPeso4'></td><td><input name='txtTA' type='text' id='txtTA4'></td><td><input name='txtFC' type='text' id='txtFC4'></td><td><input name='txtFR' type='text' id='txtFR4'></td><td><input name='txtPulso' type='text' id='txtPulso4'></td><td><a href='#' onclick='GuardarSV(4)'>Guardar</a></td></tr>");
							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"5");
							if(rs1.next()){
								out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>05 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("	 <tr><td>05 AM </td><td><input name='txtTalla' type='text' id='txtTalla5'></td><td><input name='txtPeso' type='text' id='txtPeso5'></td><td><input name='txtTA' type='text' id='txtTA5'></td><td><input name='txtFC' type='text' id='txtFC5'></td><td><input name='txtFR' type='text' id='txtFR5'></td><td><input name='txtPulso' type='text' id='txtPulso5'></td><td><a href='#' onclick='GuardarSV(5)'>Guardar</a></td></tr>");

							}
							rs1.getStatement().getConnection().close();
							rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"6");
							if(rs1.next()){
								out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>06 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
							}else{
								out.print("	 <tr><td>06 AM </td><td><input name='txtTalla' type='text' id='txtTalla6'></td><td><input name='txtPeso' type='text' id='txtPeso6'></td><td><input name='txtTA' type='text' id='txtTA6'></td><td><input name='txtFC' type='text' id='txtFC6'></td><td><input name='txtFR' type='text' id='txtFR6'></td><td><input name='txtPulso' type='text' id='txtPulso6'></td><td><a href='#' onclick='GuardarSV(6)'>Guardar</a></td></tr>");
							}
							rs1.getStatement().getConnection().close();*/
							
						}
						rs.getStatement().getConnection().close();
					//	}
						
					}
					
					
				//}
					
				out.print("</table>");
				//rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("	</table>");
			
			
			out.print("</td></tr></table>");
		
}

if(va.equals("CamSV")){
	//String CodAdmSV=req.getParameter("CodAdmK");
	java.util.Date fechaActual = new java.util.Date();
	java.sql.Date fechaInsertSV = new java.sql.Date(fechaActual.getTime());	
	 Calendar c1 = GregorianCalendar.getInstance();
        System.out.println("Fecha actual: " + c1.getTime().toLocaleString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        c1.add(Calendar.DATE, 1);

	try {
		String CodAdmSV=req.getParameter("CodAdmSV");
		String FechaSV=req.getParameter("FechaSV");
		
		String FechaKardex=req.getParameter("FechaKardex");
		//String FechaKardex=req.getParameter("FechaKardex");
		//String CodAdmSV=req.getParameter("CodAdmSV");
		//String FechaSV=req.getParameter("FechaSV");
		/*
		out.print("<table width='100%' border='1' cellspacing='0' cellpadding='0'>");
		out.print("<tr><td width='11%'>HORA DE TOMA </td>" +
				"<td width='14%'>TALLA</td>" +
				"<td width='15%'>PESO</td>" +
				"<td width='15%'>T.A</td>" +
				"<td width='15%'>F.C</td>" +
				"<td width='15%'>F.R</td>" +
				"<td width='15%'>PULSO</td>" +
				"<td>Guardar</td></tr>");*/
		
		
		out.print("<table width='100%'><tr><td><div id='CarguesDiaKardex'><input type='hidden' id='txtFechaKardexActivo' value='"+FechaSV+"'> "+
				"<tr> <td ><div id='tallas'>TALLA</div></td>" +
				"<td><input id='tallasv' type='text' style='border: 1px solid #585858' onkeyup='soloNumerossv(tallasv)'; onblur='guardarHC(tallasv)'/>cms"+
				"</td></tr>"+
				"<tr> <td ><div id='pesos'>PESO</div></td>" +
				"<td><input id='pesosv' type='text' style='border: 1px solid #585858' onkeyup='soloNumerossv(pesosv)'; onblur='guardarHC(pesosv)'/>kg/g"+
				"</td></tr>"+
				"<tr><td>" +
				"<input type='button' id='aparecer_x' value='x' onclick='desocultar_x()' /></td><td>"+
				"<input type='button' id='aparecer_pap' value='P.A.P' onclick='desocultar_pap()' /></td><td>"+
				"<input type='button' id='aparecer_pamhemo' value='parametros hemodinamicos' onclick='desocultar_pamhemo()' /></td><td>"+
				"<input type='button' id='aparecer_pulso' value='pulso' onclick='desocultar_pulso()' /></td><td>"+
				"<input type='button' id='aparecer_pupilas' value='pupilas' onclick='desocultar_pupilas()' /></td>"+
				"</tr>");
		
		out.print("<table width='100%' border='0' ><tr><td width='30%'><div id='DatosEmerg'>-</div></td>" +
				"<td width='100%' align='right'><table style='background-color: #00BFFF; ' width='90%' border='0' align='right' ><tr><td bgcolor='#E6E6E6' colspan='17' align='center'>"+fechaInsertSV+"</td><td colspan='7' bgcolor='#E6E6E6' align='center'>"+sdf.format(c1.getTime())+"</td></tr><tr></td>" +
		"<td width='2.8%'>07</td>" +"<td width='2.8%'>08</td>" +"<td width='2.8%'>09</td>" +
		"<td width='2.8%'>10</td>" +"<td width='2.8%'>11</td>" +"<td width='2.8%'>12</td>" +"<td width='2.8%'>13</td>" +
		"<td width='2.8%'>14</td>" +"<td width='2.8%'>15</td>" +"<td width='2.8%'>16</td>" +"<td width='2.8%'>17</td>" +
		"<td width='2.8%'>18</td>" +"<td width='2.8%'>19</td>" +"<td width='2.8%'>20</td>" +"<td width='2.8%'>21</td>" +
		"<td width='2.8%'>22</td>" +"<td width='2.8%'>23</td>" +"<td width='2.8%'>00</td>" +"<td width='2.8%'>01</td>" +"<td width='2.8%'>02</td>" +
		"<td width='2.8%'>03</td>" +"<td width='2.8%'>04</td>" +"<td width='2.8%'>05</td>" +"<td width='2.8%'>06</td>" +												
		"</tr></table><div style='overflow-y:scroll; height:200px; width:100%'><table width='100%' border='0' >");
			
		
		//while(rs1.next()){
			System.out.println("Entro al while consulta");
			rs=mvf.RestaFechas(fechaInsertSV+"");
			if(rs.next()){
				System.out.println("Entro al if fecha consulta");
				if(rs.getString(1).equals("0")){
					
					out.print("<tr id='temperrr'  bgcolor='#2E9AFE'> <td width='30%'><div id='salidas'style='text-align: center' ><CENTER>BASICOS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.</CENTER></div></td>" +
							"<td width='70%'> " +
							"</td>"+
							"</tr>"+
					
					
					
					
					//temperatura
					"<tr>"+
					//"<td width='100%'> <div id='temp'>T\u00B0 <sup><a href='#' onclick='deshabilitarFilaSv(temp)'>x</sup></div></td>" +
					"<td width='30%'> <div id='temp'>T\u00B0<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(temp)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'>" +
					"<table width='100%' border='0' >" +
					"<tr>" +
					"<td width='2.8%'><input id='temp7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp7)'; onblur='guardarHC(temp7)'/></td>" 
					+"<td  width='2.8%'><input id='temp8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp8)'; onblur='guardarHC(temp8)'/></td>" 
					+"<td  width='2.8%'><input id='temp9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp9)'; onblur='guardarHC(temp9)'/></td>" 
					+"<td  width='2.8%'><input id='temp10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp10)'; onblur='guardarHC(temp10)'/></td>" 
					+"<td  width='2.8%'><input id='temp11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp11)'; onblur='guardarHC(temp11)'/></td>" 
					+"<td  width='2.8%'><input id='temp12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp12)'; onblur='guardarHC(temp12)'/></td>"
					+"<td  width='2.8%'><input id='temp13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp13)'; onblur='guardarHC(temp13)'/></td>" 
					+"<td  width='2.8%'><input id='temp14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp14)'; onblur='guardarHC(temp14)'/></td>" 
					+"<td  width='2.8%'><input id='temp15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp15)'; onblur='guardarHC(temp15)'/></td>" 
					+"<td  width='2.8%'><input id='temp16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp16)'; onblur='guardarHC(temp16)'/></td>" 
					+"<td  width='2.8%'><input id='temp17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp17)'; onblur='guardarHC(temp17)'/></td>" 
					+"<td  width='2.8%'><input id='temp18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp18)'; onblur='guardarHC(temp18)'/></td>" 
					+"<td  width='2.8%'><input id='temp19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp19)'; onblur='guardarHC(temp19)'/></td>" 
					+"<td  width='2.8%'><input id='temp20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp20)'; onblur='guardarHC(temp20)'/></td>" 
					+"<td  width='2.8%'><input id='temp21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp21)'; onblur='guardarHC(temp21)'/></td>" 
					+"<td  width='2.8%'><input id='temp22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp22)'; onblur='guardarHC(temp22)'/></td>" 
					+"<td  width='2.8%'><input id='temp23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp23)'; onblur='guardarHC(temp23)'/></td>" 
					+"<td  width='2.8%'><input id='temp00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp00)'; onblur='guardarHC(temp00)'/></td>" 
					+"<td  width='2.8%'><input id='temp01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp01)'; onblur='guardarHC(temp01)'/></td>" 
					+"<td  width='2.8%'><input id='temp02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp02)'; onblur='guardarHC(temp02)'/></td>" 
					+"<td  width='2.8%'><input id='temp03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp03)'; onblur='guardarHC(temp03)'/></td>" 
					+"<td  width='2.8%'><input id='temp04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp04)'; onblur='guardarHC(temp04)'/></td>" 
					+"<td  width='2.8%'><input id='temp05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp05)'; onblur='guardarHC(temp05)'/></td>" 
					+"<td  width='2.8%'><input id='temp06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(temp06)'; onblur='guardarHC(temp06)'/></td>"													
					+"</tr> </table> </td></tr>" +
					
					
					//"<div style='overflow-y:scroll; height:100px'>"+
					
					//"<table width='100%' border='0'>"+
					
					//fc
					
					//"<tr bgcolor='#D8D8D8' ><td width='30%'><div id='fc'>FC <sub><a href='#' onclick='deshabilitarFilaSv(fc)'>x</a></sub></td>" +
					"<tr bgcolor='#D8D8D8' ><td width='30%'><div id='fc'>FC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(fc)' style='cursor:pointer;'/></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='fc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc7)'; onblur='guardarHC(fc7)'/></td>" 
					+"<td  width='2.8%'><input id='fc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc8)'; onblur='guardarHC(fc8)'/></td>" 
					+"<td  width='2.8%'><input id='fc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc9)'; onblur='guardarHC(fc9)'/></td>" 
					+"<td  width='2.8%'><input id='fc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc10)'; onblur='guardarHC(fc10)'/></td>" 
					+"<td  width='2.8%'><input id='fc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc11)'; onblur='guardarHC(fc11)'/></td>" 
					+"<td  width='2.8%'><input id='fc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc12)'; onblur='guardarHC(fc12)'/></td>"
					+"<td  width='2.8%'><input id='fc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc13)'; onblur='guardarHC(fc13)'/></td>" 
					+"<td  width='2.8%'><input id='fc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc14)'; onblur='guardarHC(fc14)'/></td>" 
					+"<td  width='2.8%'><input id='fc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc15)'; onblur='guardarHC(fc15)'/></td>" 
					+"<td  width='2.8%'><input id='fc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc16)'; onblur='guardarHC(fc16)'/></td>" 
					+"<td  width='2.8%'><input id='fc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc17)'; onblur='guardarHC(fc17)'/></td>" 
					+"<td  width='2.8%'><input id='fc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc18)'; onblur='guardarHC(fc18)'/></td>" 
					+"<td  width='2.8%'><input id='fc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc19)'; onblur='guardarHC(fc19)'/></td>" 
					+"<td  width='2.8%'><input id='fc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc20)'; onblur='guardarHC(fc20)'/></td>" 
					+"<td  width='2.8%'><input id='fc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc21)'; onblur='guardarHC(fc21)'/></td>" 
					+"<td  width='2.8%'><input id='fc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc22)'; onblur='guardarHC(fc22)'/></td>" 
					+"<td  width='2.8%'><input id='fc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc23)'; onblur='guardarHC(fc23)'/></td>" 
					+"<td  width='2.8%'><input id='fc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc00)'; onblur='guardarHC(fc00)'/></td>" 
					+"<td  width='2.8%'><input id='fc01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc01)'; onblur='guardarHC(fc01)'/></td>" 
					+"<td  width='2.8%'><input id='fc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc02)'; onblur='guardarHC(fc02)'/></td>" 
					+"<td  width='2.8%'><input id='fc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc03)'; onblur='guardarHC(fc03)'/></td>" 
					+"<td  width='2.8%'><input id='fc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc04)'; onblur='guardarHC(fc04)'/></td>" 
					+"<td  width='2.8%'><input id='fc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc05)'; onblur='guardarHC(fc05)'/></td>" 
					+"<td  width='2.8%'><input id='fc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fc06)'; onblur='guardarHC(fc06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					
					//fr
					//"<tr> <td width='30%'><div id='frsv'>FR <br><a href='#' onclick='deshabilitarFilaSv(frsv)'>Eliminar</a></div></td>" +
					"<tr> <td width='30%'><div id='frsv'>FR<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(frsv)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='frsv7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv7)'; onblur='guardarHC(frsv7)'/></td>" 
					+"<td  width='2.8%'><input id='frsv8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv8)'; onblur='guardarHC(frsv8)'/></td>" 
					+"<td  width='2.8%'><input id='frsv9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv9)'; onblur='guardarHC(frsv9)'/></td>" 
					+"<td  width='2.8%'><input id='frsv10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv10)'; onblur='guardarHC(frsv10)'/></td>" 
					+"<td  width='2.8%'><input id='frsv11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv11)'; onblur='guardarHC(frsv11)'/></td>" 
					+"<td  width='2.8%'><input id='frsv12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv12)'; onblur='guardarHC(frsv12)'/></td>"
					+"<td  width='2.8%'><input id='frsv13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv13)'; onblur='guardarHC(frsv13)'/></td>" 
					+"<td  width='2.8%'><input id='frsv14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv14)'; onblur='guardarHC(frsv14)'/></td>" 
					+"<td  width='2.8%'><input id='frsv15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv15)'; onblur='guardarHC(frsv15)'/></td>" 
					+"<td  width='2.8%'><input id='frsv16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv16)'; onblur='guardarHC(frsv16)'/></td>" 
					+"<td  width='2.8%'><input id='frsv17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv17)'; onblur='guardarHC(frsv17)'/></td>" 
					+"<td  width='2.8%'><input id='frsv18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv18)'; onblur='guardarHC(frsv18)'/></td>" 
					+"<td  width='2.8%'><input id='frsv19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv19)'; onblur='guardarHC(frsv19)'/></td>" 
					+"<td  width='2.8%'><input id='frsv20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv20)'; onblur='guardarHC(frsv20)'/></td>" 
					+"<td  width='2.8%'><input id='frsv21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv21)'; onblur='guardarHC(frsv21)'/></td>" 
					+"<td  width='2.8%'><input id='frsv22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv22)'; onblur='guardarHC(frsv22)'/></td>" 
					+"<td  width='2.8%'><input id='frsv23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv23)'; onblur='guardarHC(frsv23)'/></td>" 
					+"<td  width='2.8%'><input id='frsv00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv00)'; onblur='guardarHC(frsv00)'/></td>" 
					+"<td  width='2.8%'><input id='frsv01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv01)'; onblur='guardarHC(frsv01)'/></td>" 
					+"<td  width='2.8%'><input id='frsv02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv02)'; onblur='guardarHC(frsv02)'/></td>" 
					+"<td  width='2.8%'><input id='frsv03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv03)'; onblur='guardarHC(frsv03)'/></td>" 
					+"<td  width='2.8%'><input id='frsv04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv04)'; onblur='guardarHC(frsv04)'/></td>" 
					+"<td  width='2.8%'><input id='frsv05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv05)'; onblur='guardarHC(frsv05)'/></td>" 
					+"<td  width='2.8%'><input id='frsv06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(frsv06)'; onblur='guardarHC(frsv06)'/></td>"													
					+"</tr></table></td></tr>" +
					//saturacion
					//"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='sat'>SATURACI\u00D3N <br><a href='#' onclick='deshabilitarFilaSv(sat)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='sat'>SATURACI\u00D3N <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(sat)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='sat7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat7)'; onblur='guardarHC(sat7)'/></td>" 
					+"<td  width='2.8%'><input id='sat8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat8)'; onblur='guardarHC(sat8)'/></td>" 
					+"<td  width='2.8%'><input id='sat9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat9)'; onblur='guardarHC(sat9)'/></td>" 
					+"<td  width='2.8%'><input id='sat10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat10)'; onblur='guardarHC(sat10)'/></td>" 
					+"<td  width='2.8%'><input id='sat11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat11)'; onblur='guardarHC(sat11)'/></td>" 
					+"<td  width='2.8%'><input id='sat12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat12)'; onblur='guardarHC(sat12)'/></td>"
					+"<td  width='2.8%'><input id='sat13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat13)'; onblur='guardarHC(sat13)'/></td>" 
					+"<td  width='2.8%'><input id='sat14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat14)'; onblur='guardarHC(sat14)'/></td>" 
					+"<td  width='2.8%'><input id='sat15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat15)'; onblur='guardarHC(sat15)'/></td>" 
					+"<td  width='2.8%'><input id='sat16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat16)'; onblur='guardarHC(sat16)'/></td>" 
					+"<td  width='2.8%'><input id='sat17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat17)'; onblur='guardarHC(sat17)'/></td>" 
					+"<td  width='2.8%'><input id='sat18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat18)'; onblur='guardarHC(sat18)'/></td>" 
					+"<td  width='2.8%'><input id='sat19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat19)'; onblur='guardarHC(sat19)'/></td>" 
					+"<td  width='2.8%'><input id='sat20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat20)'; onblur='guardarHC(sat20)'/></td>" 
					+"<td  width='2.8%'><input id='sat21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat21)'; onblur='guardarHC(sat21)'/></td>" 
					+"<td  width='2.8%'><input id='sat22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat22)'; onblur='guardarHC(sat22)'/></td>" 
					+"<td  width='2.8%'><input id='sat23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat23)'; onblur='guardarHC(sat23)'/></td>" 
					+"<td  width='2.8%'><input id='sat00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat00)'; onblur='guardarHC(sat00)'/></td>" 
					+"<td  width='2.8%'><input id='sat01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat01)'; onblur='guardarHC(sat01)'/></td>" 
					+"<td  width='2.8%'><input id='sat02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat02)'; onblur='guardarHC(sat02)'/></td>" 
					+"<td  width='2.8%'><input id='sat03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat03)'; onblur='guardarHC(sat03)'/></td>" 
					+"<td  width='2.8%'><input id='sat04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat04)'; onblur='guardarHC(sat04)'/></td>" 
					+"<td  width='2.8%'><input id='sat05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat05)'; onblur='guardarHC(sat05)'/></td>" 
					+"<td  width='2.8%'><input id='sat06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sat06)'; onblur='guardarHC(sat06)'/></td>"													
					+"</tr></table></td></tr>" +
					//fio2
					
					
					//"<tr> <td width='30%'><div id='fio'>FIO2 <br><a href='#' onclick='deshabilitarFilaSv(fio)'>Eliminar</a></div></td>" +
					"<tr> <td width='30%'><div id='fio'>FIO2<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(fio)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='fio7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio7)'; onblur='guardarHC(fio7)'/></td>" 
				   +"<td width='2.8%'><input id='fio8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio8)'; onblur='guardarHC(fio8)'/></td>" 
				   +"<td width='2.8%'><input id='fio9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio9)'; onblur='guardarHC(fio9)'/></td>" 
				   +"<td width='2.8%'><input id='fio10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio10)'; onblur='guardarHC(fio10)'/></td>" 
				   +"<td width='2.8%'><input id='fio11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio11)'; onblur='guardarHC(fio11)'/></td>" 
				   +"<td width='2.8%'><input id='fio12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio12)'; onblur='guardarHC(fio12)'/></td>"
				   +"<td width='2.8%'><input id='fio13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio13)'; onblur='guardarHC(fio13)'/></td>" 
				   +"<td width='2.8%'><input id='fio14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio14)'; onblur='guardarHC(fio14)'/></td>" 
				   +"<td width='2.8%'><input id='fio15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio15)'; onblur='guardarHC(fio15)'/></td>" 
				   +"<td width='2.8%'><input id='fio16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio16)'; onblur='guardarHC(fio16)'/></td>" 
				   +"<td width='2.8%'><input id='fio17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio17)'; onblur='guardarHC(fio17)'/></td>" 
				   +"<td width='2.8%'><input id='fio18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio18)'; onblur='guardarHC(fio18)'/></td>" 
				   +"<td width='2.8%'><input id='fio19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio19)'; onblur='guardarHC(fio19)'/></td>" 
				   +"<td width='2.8%'><input id='fio20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio20)'; onblur='guardarHC(fio20)'/></td>" 
				   +"<td width='2.8%'><input id='fio21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio21)'; onblur='guardarHC(fio21)'/></td>" 
				   +"<td width='2.8%'><input id='fio22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio22)'; onblur='guardarHC(fio22)'/></td>" 
				   +"<td width='2.8%'><input id='fio23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio23)'; onblur='guardarHC(fio23)'/></td>" 
				   +"<td width='2.8%'><input id='fio00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio00)'; onblur='guardarHC(fio00)'/></td>" 
				   +"<td width='2.8%'><input id='fio01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio01)'; onblur='guardarHC(fio01)'/></td>" 
				   +"<td width='2.8%'><input id='fio02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio02)'; onblur='guardarHC(fio02)'/></td>" 
				   +"<td width='2.8%'><input id='fio03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio03)'; onblur='guardarHC(fio03)'/></td>" 
				   +"<td width='2.8%'><input id='fio04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio04)'; onblur='guardarHC(fio04)'/></td>" 
				   +"<td width='2.8%'><input id='fio05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio05)'; onblur='guardarHC(fio05)'/></td>" 
				   +"<td width='2.8%'><input id='fio06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(fio06)'; onblur='guardarHC(fio06)'/></td>"													
				   +"</tr></table></td></tr>" +
					//sedacion
					//"<tr bgcolor='#D8D8D8' id='ocsedacion' style='display: none'> <td width='30%'><div id='sed'>SEDACION <br><a href='#' onclick='deshabilitarFilaSv(sed)'>Eliminar</a></div></td>" +
				   "<tr bgcolor='#D8D8D8' id='ocsedacion' style='display: none'> <td width='30%'><div id='sed'>SEDACION<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(fc)' style='cursor:pointer;' /></div></td>" +
				   "<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='sed7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed7)'; onblur='guardarHC(sed7)'/></td>" 
					+"<td width='2.8%'><input id='sed8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed8)'; onblur='guardarHC(sed8)'/></td>" 
					+"<td width='2.8%'><input id='sed9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed9)'; onblur='guardarHC(sed9)'/></td>" 
					+"<td width='2.8%'><input id='sed10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed10)'; onblur='guardarHC(sed10)'/></td>" 
					+"<td width='2.8%'><input id='sed11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed11)'; onblur='guardarHC(sed11)'/></td>" 
					+"<td width='2.8%'><input id='sed12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed12)'; onblur='guardarHC(sed12)'/></td>"
					+"<td width='2.8%'><input id='sed13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed13)'; onblur='guardarHC(sed13)'/></td>" 
					+"<td width='2.8%'><input id='sed14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed14)'; onblur='guardarHC(sed14)'/></td>" 
					+"<td width='2.8%'><input id='sed15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed15)'; onblur='guardarHC(sed15)'/></td>" 
					+"<td width='2.8%'><input id='sed16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed16)'; onblur='guardarHC(sed16)'/></td>" 
					+"<td width='2.8%'><input id='sed17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed17)'; onblur='guardarHC(sed17)'/></td>" 
					+"<td width='2.8%'><input id='sed18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed18)'; onblur='guardarHC(sed18)'/></td>" 
					+"<td width='2.8%'><input id='sed19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed19)'; onblur='guardarHC(sed19)'/></td>" 
					+"<td width='2.8%'><input id='sed20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed20)'; onblur='guardarHC(sed20)'/></td>" 
					+"<td width='2.8%'><input id='sed21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed21)'; onblur='guardarHC(sed21)'/></td>" 
					+"<td width='2.8%'><input id='sed22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed22)'; onblur='guardarHC(sed22)'/></td>" 
					+"<td width='2.8%'><input id='sed23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed23)'; onblur='guardarHC(sed23)'/></td>" 
					+"<td width='2.8%'><input id='sed00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed00)'; onblur='guardarHC(sed00)'/></td>" 
					+"<td width='2.8%'><input id='sed01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed01)'; onblur='guardarHC(sed01)'/></td>" 
					+"<td width='2.8%'><input id='sed02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed02)'; onblur='guardarHC(sed02)'/></td>" 
					+"<td width='2.8%'><input id='sed03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed03)'; onblur='guardarHC(sed03)'/></td>" 
					+"<td width='2.8%'><input id='sed04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed04)'; onblur='guardarHC(sed04)'/></td>" 
					+"<td width='2.8%'><input id='sed05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed05)'; onblur='guardarHC(sed05)'/></td>" 
					+"<td width='2.8%'><input id='sed06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(sed06)'; onblur='guardarHC(sed06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					//dolor
					//"<tr id='ocdolor' style='display: none'> <td width='30%'><div id='dol'>DOLOR <br><a href='#' onclick='deshabilitarFilaSv(dol)'>Eliminar</a></div></td>" +
					"<tr id='ocdolor' style='display: none'> <td width='30%'><div id='dol'>DOLOR<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(dol)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='dol7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol7)'; onblur='guardarHC(dol7)'/></td>" 
					+"<td width='2.8%'><input id='dol8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol8)'; onblur='guardarHC(dol8)'/></td>" 
					+"<td width='2.8%'><input id='dol9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol9)'; onblur='guardarHC(dol9)'/></td>" 
					+"<td width='2.8%'><input id='dol10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol10)'; onblur='guardarHC(dol10)'/></td>" 
					+"<td width='2.8%'><input id='dol11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol11)'; onblur='guardarHC(dol11)'/></td>" 
					+"<td width='2.8%'><input id='dol12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol12)'; onblur='guardarHC(dol12)'/></td>"
					+"<td width='2.8%'><input id='dol13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol13)'; onblur='guardarHC(dol13)'/></td>" 
					+"<td width='2.8%'><input id='dol14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol14)'; onblur='guardarHC(dol14)'/></td>" 
					+"<td width='2.8%'><input id='dol15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol15)'; onblur='guardarHC(dol15)'/></td>" 
					+"<td width='2.8%'><input id='dol16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol16)'; onblur='guardarHC(dol16)'/></td>" 
					+"<td width='2.8%'><input id='dol17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol17)'; onblur='guardarHC(dol17)'/></td>" 
					+"<td width='2.8%'><input id='dol18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol18)'; onblur='guardarHC(dol18)'/></td>" 
					+"<td width='2.8%'><input id='dol19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol19)'; onblur='guardarHC(dol19)'/></td>" 
					+"<td width='2.8%'><input id='dol20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol20)'; onblur='guardarHC(dol20)'/></td>" 
					+"<td width='2.8%'><input id='dol21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol21)'; onblur='guardarHC(dol21)'/></td>" 
					+"<td width='2.8%'><input id='dol22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol22)'; onblur='guardarHC(dol22)'/></td>" 
					+"<td width='2.8%'><input id='dol23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol23)'; onblur='guardarHC(dol23)'/></td>" 
					+"<td width='2.8%'><input id='dol00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol00)'; onblur='guardarHC(dol00)'/></td>" 
					+"<td width='2.8%'><input id='dol01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol01)'; onblur='guardarHC(dol01)'/></td>" 
					+"<td width='2.8%'><input id='dol02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol02)'; onblur='guardarHC(dol02)'/></td>" 
					+"<td width='2.8%'><input id='dol03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol03)'; onblur='guardarHC(dol03)'/></td>" 
					+"<td width='2.8%'><input id='dol04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol04)'; onblur='guardarHC(dol04)'/></td>" 
					+"<td width='2.8%'><input id='dol05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol05)'; onblur='guardarHC(dol05)'/></td>" 
					+"<td width='2.8%'><input id='dol06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(dol06)'; onblur='guardarHC(dol06)'/></td>"													
					+"</tr></table></td></tr>" +
					//ta s
					//"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='tas'>TA S <br><a href='#' onclick='deshabilitarFilaSv(tas)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8'> <td width='30%'><div id='tas'>TA S <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tas)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='tas7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas7)'; onblur='guardarHC(tas7)'/></td>" 
					+"<td  width='2.8%'><input id='tas8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas8)'; onblur='guardarHC(tas8)'/></td>" 
					+"<td  width='2.8%'><input id='tas9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas9)'; onblur='guardarHC(tas9)'/></td>" 
					+"<td  width='2.8%'><input id='tas10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas10)'; onblur='guardarHC(tas10)'/></td>" 
					+"<td  width='2.8%'><input id='tas11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas11)'; onblur='guardarHC(tas11)'/></td>" 
					+"<td  width='2.8%'><input id='tas12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas12)'; onblur='guardarHC(tas12)'/></td>"
					+"<td  width='2.8%'><input id='tas13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas13)'; onblur='guardarHC(tas13)'/></td>" 
					+"<td  width='2.8%'><input id='tas14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas14)'; onblur='guardarHC(tas14)'/></td>" 
					+"<td  width='2.8%'><input id='tas15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas15)'; onblur='guardarHC(tas15)'/></td>" 
					+"<td  width='2.8%'><input id='tas16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas16)'; onblur='guardarHC(tas16)'/></td>" 
					+"<td  width='2.8%'><input id='tas17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas17)'; onblur='guardarHC(tas17)'/></td>" 
					+"<td  width='2.8%'><input id='tas18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas18)'; onblur='guardarHC(tas18)'/></td>" 
					+"<td  width='2.8%'><input id='tas19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas19)'; onblur='guardarHC(tas19)'/></td>" 
					+"<td  width='2.8%'><input id='tas20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas20)'; onblur='guardarHC(tas20)'/></td>" 
					+"<td  width='2.8%'><input id='tas21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas21)'; onblur='guardarHC(tas21)'/></td>" 
					+"<td  width='2.8%'><input id='tas22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas22)'; onblur='guardarHC(tas22)'/></td>" 
					+"<td  width='2.8%'><input id='tas23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas23)'; onblur='guardarHC(tas23)'/></td>" 
					+"<td  width='2.8%'><input id='tas00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas00)'; onblur='guardarHC(tas00)'/></td>" 
					+"<td  width='2.8%'><input id='tas01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas01)'; onblur='guardarHC(tas01)'/></td>" 
					+"<td  width='2.8%'><input id='tas02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas02)'; onblur='guardarHC(tas02)'/></td>" 
					+"<td  width='2.8%'><input id='tas03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas03)'; onblur='guardarHC(tas03)'/></td>" 
					+"<td  width='2.8%'><input id='tas04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas04)'; onblur='guardarHC(tas04)'/></td>" 
					+"<td  width='2.8%'><input id='tas05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas05)'; onblur='guardarHC(tas05)'/></td>" 
					+"<td  width='2.8%'><input id='tas06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tas06)'; onblur='guardarHC(tas06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					//ta d
					//"<tr> <td width='30%'><div id='tad'>TA D <br><a href='#' onclick='deshabilitarFilaSv(tad)'>Eliminar</a></div></td>" +
					"<tr> <td width='30%'><div id='tad'>TA D<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tad)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='tad7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad7)'; onblur='guardarHC(tad7)'/></td>" 
					+"<td  width='2.8%'><input id='tad8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad8)'; onblur='guardarHC(tad8)'/></td>" 
					+"<td  width='2.8%'><input id='tad9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad9)'; onblur='guardarHC(tad9)'/></td>" 
					+"<td  width='2.8%'><input id='tad10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad10)'; onblur='guardarHC(tad10)'/></td>" 
					+"<td  width='2.8%'><input id='tad11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad11)'; onblur='guardarHC(tad11)'/></td>" 
					+"<td  width='2.8%'><input id='tad12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad12)'; onblur='guardarHC(tad12)'/></td>"
					+"<td  width='2.8%'><input id='tad13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad13)'; onblur='guardarHC(tad13)'/></td>" 
					+"<td  width='2.8%'><input id='tad14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad14)'; onblur='guardarHC(tad14)'/></td>" 
					+"<td  width='2.8%'><input id='tad15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad15)'; onblur='guardarHC(tad15)'/></td>" 
					+"<td  width='2.8%'><input id='tad16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad16)'; onblur='guardarHC(tad16)'/></td>" 
					+"<td  width='2.8%'><input id='tad17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad17)'; onblur='guardarHC(tad17)'/></td>" 
					+"<td  width='2.8%'><input id='tad18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad18)'; onblur='guardarHC(tad18)'/></td>" 
					+"<td  width='2.8%'><input id='tad19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad19)'; onblur='guardarHC(tad19)'/></td>" 
					+"<td  width='2.8%'><input id='tad20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad20)'; onblur='guardarHC(tad20)'/></td>" 
					+"<td  width='2.8%'><input id='tad21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad21)'; onblur='guardarHC(tad21)'/></td>" 
					+"<td  width='2.8%'><input id='tad22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad22)'; onblur='guardarHC(tad22)'/></td>" 
					+"<td  width='2.8%'><input id='tad23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad23)'; onblur='guardarHC(tad23)'/></td>" 
					+"<td  width='2.8%'><input id='tad00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad00)'; onblur='guardarHC(tad00)'/></td>" 
					+"<td  width='2.8%'><input id='tad01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad01)'; onblur='guardarHC(tad01)'/></td>" 
					+"<td  width='2.8%'><input id='tad02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad02)'; onblur='guardarHC(tad02)'/></td>" 
					+"<td  width='2.8%'><input id='tad03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad03)'; onblur='guardarHC(tad03)'/></td>" 
					+"<td  width='2.8%'><input id='tad04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad04)'; onblur='guardarHC(tad04)'/></td>" 
					+"<td  width='2.8%'><input id='tad05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad05)'; onblur='guardarHC(tad05)'/></td>" 
					+"<td  width='2.8%'><input id='tad06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tad06)'; onblur='guardarHC(tad06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					
					
					//tam
					"<tr id='octam' bgcolor='#D8D8D8'> <td width='30%'><div id='tam'>TAM</div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='tam7' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam8' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam9' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam10' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam11' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam12' type='text' size='1' disabled='disabled'/></td>"
					+"<td  width='2.8%'><input id='tam13' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam14' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam15' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam16' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam17' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam18' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam19' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam20' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam21' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam22' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam23' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam00' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam01' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam02' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam03' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam04' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam05' type='text' size='1' disabled='disabled'/></td>" 
					+"<td  width='2.8%'><input id='tam06' type='text' size='1' disabled='disabled'/></td>"													
					+"</tr></table></td></tr>" +
					//pvc
					//"<tr id='ocpvc' style='display: none'> <td width='30%'><div id='pvc'>PVC <br><a href='#' onclick='deshabilitarFilaSv(pvc)'>Eliminar</a></div></td>" +
					"<tr id='ocpvc' style='display: none'> <td width='30%'><div id='pvc'>PVC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(pvc)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='pvc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc7)'; onblur='guardarHC(pvc7)'/></td>" 
					+"<td  width='2.8%'><input id='pvc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc8)'; onblur='guardarHC(pvc8)'/></td>" 
					+"<td  width='2.8%'><input id='pvc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc9)'; onblur='guardarHC(pvc9)'/></td>" 
					+"<td  width='2.8%'><input id='pvc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc10)'; onblur='guardarHC(pvc10)'/></td>" 
					+"<td  width='2.8%'><input id='pvc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc11)'; onblur='guardarHC(pvc11)'/></td>" 
					+"<td  width='2.8%'><input id='pvc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc12)'; onblur='guardarHC(pvc12)'/></td>"
					+"<td  width='2.8%'><input id='pvc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc13)'; onblur='guardarHC(pvc13)'/></td>" 
					+"<td  width='2.8%'><input id='pvc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc14)'; onblur='guardarHC(pvc14)'/></td>" 
					+"<td  width='2.8%'><input id='pvc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc15)'; onblur='guardarHC(pvc15)'/></td>" 
					+"<td  width='2.8%'><input id='pvc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc16)'; onblur='guardarHC(pvc16)'/></td>" 
					+"<td  width='2.8%'><input id='pvc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc17)'; onblur='guardarHC(pvc17)'/></td>" 
					+"<td  width='2.8%'><input id='pvc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc18)'; onblur='guardarHC(pvc18)'/></td>" 
					+"<td  width='2.8%'><input id='pvc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc19)'; onblur='guardarHC(pvc19)'/></td>" 
					+"<td  width='2.8%'><input id='pvc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc20)'; onblur='guardarHC(pvc20)'/></td>" 
					+"<td  width='2.8%'><input id='pvc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc21)'; onblur='guardarHC(pvc21)'/></td>" 
					+"<td  width='2.8%'><input id='pvc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc22)'; onblur='guardarHC(pvc22)'/></td>" 
					+"<td  width='2.8%'><input id='pvc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc23)'; onblur='guardarHC(pvc23)'/></td>" 
					+"<td  width='2.8%'><input id='pvc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc00)'; onblur='guardarHC(pvc00)'/></td>" 
					+"<td  width='2.8%'><input id='pvc01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc01)'; onblur='guardarHC(pvc01)'/></td>" 
					+"<td  width='2.8%'><input id='pvc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc02)'; onblur='guardarHC(pvc02)'/></td>" 
					+"<td  width='2.8%'><input id='pvc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc03)'; onblur='guardarHC(pvc03)'/></td>" 
					+"<td  width='2.8%'><input id='pvc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc04)'; onblur='guardarHC(pvc04)'/></td>" 
					+"<td  width='2.8%'><input id='pvc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc05)'; onblur='guardarHC(pvc05)'/></td>" 
					+"<td  width='2.8%'><input id='pvc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(pvc06)'; onblur='guardarHC(pvc06)'/></td>"													
					+"</tr></table></td></tr>" +
					//i.s
					//"<tr id='ocis' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='is'>I.S <br><a href='#' onclick='deshabilitarFilaSv(is)'>Eliminar</a></div></td>" +
					"<tr id='ocis' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='is'>I.S<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(is)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='is7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is7)'; onblur='guardarHC(is7)'/></td>" 
					+"<td  width='2.8%'><input id='is8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is8)'; onblur='guardarHC(is8)'/></td>" 
					+"<td  width='2.8%'><input id='is9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is9)'; onblur='guardarHC(is9)'/></td>" 
					+"<td  width='2.8%'><input id='is10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is10)'; onblur='guardarHC(is10)'/></td>" 
					+"<td  width='2.8%'><input id='is11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is11)'; onblur='guardarHC(is11)'/></td>" 
					+"<td  width='2.8%'><input id='is12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is12)'; onblur='guardarHC(is12)'/></td>"
					+"<td  width='2.8%'><input id='is13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is13)'; onblur='guardarHC(is13)'/></td>" 
					+"<td  width='2.8%'><input id='is14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is14)'; onblur='guardarHC(is14)'/></td>" 
					+"<td  width='2.8%'><input id='is15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is15)'; onblur='guardarHC(is15)'/></td>" 
					+"<td  width='2.8%'><input id='is16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is16)'; onblur='guardarHC(is16)'/></td>" 
					+"<td  width='2.8%'><input id='is17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is17)'; onblur='guardarHC(is17)'/></td>" 
					+"<td  width='2.8%'><input id='is18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is18)'; onblur='guardarHC(is18)'/></td>" 
					+"<td  width='2.8%'><input id='is19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is19)'; onblur='guardarHC(is19)'/></td>" 
					+"<td  width='2.8%'><input id='is20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is20)'; onblur='guardarHC(is20)'/></td>" 
					+"<td  width='2.8%'><input id='is21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is21)'; onblur='guardarHC(is21)'/></td>" 
					+"<td  width='2.8%'><input id='is22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is22)'; onblur='guardarHC(is22)'/></td>" 
					+"<td  width='2.8%'><input id='is23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is23)'; onblur='guardarHC(is23)'/></td>" 
					+"<td  width='2.8%'><input id='is00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is00)'; onblur='guardarHC(is00)'/></td>" 
					+"<td  width='2.8%'><input id='is01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is01)'; onblur='guardarHC(is01)'/></td>" 
					+"<td  width='2.8%'><input id='is02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is02)'; onblur='guardarHC(is02)'/></td>" 
					+"<td  width='2.8%'><input id='is03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is03)'; onblur='guardarHC(is03)'/></td>" 
					+"<td  width='2.8%'><input id='is04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is04)'; onblur='guardarHC(is04)'/></td>" 
					+"<td  width='2.8%'><input id='is05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is05)'; onblur='guardarHC(is05)'/></td>" 
					+"<td  width='2.8%'><input id='is06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(is06)'; onblur='guardarHC(is06)'/></td>"													
					+"</tr></table></td></tr>" +
					//p.a.p.
					"<tr id='ocdivpap' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>P.A.P.</CENTER></div></td>" +
					"<td> <a href='#' onclick='ocultarPap()'><font color='white'>Ocultar</font></a>" +
					"</td>"+
					"</tr>"+
					//s
					//"<tr id='ocpaps' style='display: none'> <td width='30%'><div id='paps'>S <br><a href='#' onclick='deshabilitarFilaSv(paps)'>Eliminar</a></div></td>" +
					"<tr id='ocpaps' style='display: none'> <td width='30%'><div id='paps'>S<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(paps)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='paps7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps7)'; onblur='guardarHC(paps7)'/></td>" 
					+"<td  width='2.8%'><input id='paps8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps8)'; onblur='guardarHC(paps8)'/></td>" 
					+"<td  width='2.8%'><input id='paps9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps9)'; onblur='guardarHC(paps9)'/></td>" 
					+"<td  width='2.8%'><input id='paps10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps10)'; onblur='guardarHC(paps10)'/></td>" 
					+"<td  width='2.8%'><input id='paps11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps11)'; onblur='guardarHC(paps11)'/></td>" 
					+"<td  width='2.8%'><input id='paps12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps12)'; onblur='guardarHC(paps12)'/></td>"
					+"<td  width='2.8%'><input id='paps13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps13)'; onblur='guardarHC(paps13)'/></td>" 
					+"<td  width='2.8%'><input id='paps14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps14)'; onblur='guardarHC(paps14)'/></td>" 
					+"<td  width='2.8%'><input id='paps15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps15)'; onblur='guardarHC(paps15)'/></td>" 
					+"<td  width='2.8%'><input id='paps16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps16)'; onblur='guardarHC(paps16)'/></td>" 
					+"<td  width='2.8%'><input id='paps17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps17)'; onblur='guardarHC(paps17)'/></td>" 
					+"<td  width='2.8%'><input id='paps18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps18)'; onblur='guardarHC(paps18)'/></td>" 
					+"<td  width='2.8%'><input id='paps19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps19)'; onblur='guardarHC(paps19)'/></td>" 
					+"<td  width='2.8%'><input id='paps20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps20)'; onblur='guardarHC(paps20)'/></td>" 
					+"<td  width='2.8%'><input id='paps21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps21)'; onblur='guardarHC(paps21)'/></td>" 
					+"<td  width='2.8%'><input id='paps22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps22)'; onblur='guardarHC(paps22)'/></td>" 
					+"<td  width='2.8%'><input id='paps23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps23)'; onblur='guardarHC(paps23)'/></td>" 
					+"<td  width='2.8%'><input id='paps00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps00)'; onblur='guardarHC(paps00)'/></td>" 
					+"<td  width='2.8%'><input id='paps01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps01)'; onblur='guardarHC(paps01)'/></td>" 
					+"<td  width='2.8%'><input id='paps02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps02)'; onblur='guardarHC(paps02)'/></td>" 
					+"<td  width='2.8%'><input id='paps03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps03)'; onblur='guardarHC(paps03)'/></td>" 
					+"<td  width='2.8%'><input id='paps04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps04)'; onblur='guardarHC(paps04)'/></td>" 
					+"<td  width='2.8%'><input id='paps05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps05)'; onblur='guardarHC(paps05)'/></td>" 
					+"<td  width='2.8%'><input id='paps06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(paps06)'; onblur='guardarHC(paps06)'/></td>"													
					+"</tr></table></td></tr>" +
					//d
					//"<tr bgcolor='#D8D8D8' id='ocpapd' style='display: none'> <td width='30%'><div id='papd'>D <br><a href='#' onclick='deshabilitarFilaSv(papd)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocpapd' style='display: none'> <td width='30%'><div id='papd'>D<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(papd)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='papd7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd7)'; onblur='guardarHC(papd7)'/></td>" 
					+"<td  width='2.8%'><input id='papd8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd8)'; onblur='guardarHC(papd8)'/></td>" 
					+"<td  width='2.8%'><input id='papd9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd9)'; onblur='guardarHC(papd9)'/></td>" 
					+"<td  width='2.8%'><input id='papd10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd10)'; onblur='guardarHC(papd10)'/></td>" 
					+"<td  width='2.8%'><input id='papd11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd11)'; onblur='guardarHC(papd11)'/></td>" 
					+"<td  width='2.8%'><input id='papd12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd12)'; onblur='guardarHC(papd12)'/></td>"
					+"<td  width='2.8%'><input id='papd13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd13)'; onblur='guardarHC(papd13)'/></td>" 
					+"<td  width='2.8%'><input id='papd14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd14)'; onblur='guardarHC(papd14)'/></td>" 
					+"<td  width='2.8%'><input id='papd15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd15)'; onblur='guardarHC(papd15)'/></td>" 
					+"<td  width='2.8%'><input id='papd16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd16)'; onblur='guardarHC(papd16)'/></td>" 
					+"<td  width='2.8%'><input id='papd17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd17)'; onblur='guardarHC(papd17)'/></td>" 
					+"<td  width='2.8%'><input id='papd18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd18)'; onblur='guardarHC(papd18)'/></td>" 
					+"<td  width='2.8%'><input id='papd19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd19)'; onblur='guardarHC(papd19)'/></td>" 
					+"<td  width='2.8%'><input id='papd20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd20)'; onblur='guardarHC(papd20)'/></td>" 
					+"<td  width='2.8%'><input id='papd21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd21)'; onblur='guardarHC(papd21)'/></td>" 
					+"<td  width='2.8%'><input id='papd22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd22)'; onblur='guardarHC(papd22)'/></td>" 
					+"<td  width='2.8%'><input id='papd23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd23)'; onblur='guardarHC(papd23)'/></td>" 
					+"<td  width='2.8%'><input id='papd00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd00)'; onblur='guardarHC(papd00)'/></td>" 
					+"<td  width='2.8%'><input id='papd01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd01)'; onblur='guardarHC(papd01)'/></td>" 
					+"<td  width='2.8%'><input id='papd02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd02)'; onblur='guardarHC(papd02)'/></td>" 
					+"<td  width='2.8%'><input id='papd03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd03)'; onblur='guardarHC(papd03)'/></td>" 
					+"<td  width='2.8%'><input id='papd04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd04)'; onblur='guardarHC(papd04)'/></td>" 
					+"<td  width='2.8%'><input id='papd05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd05)'; onblur='guardarHC(papd05)'/></td>" 
					+"<td  width='2.8%'><input id='papd06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papd06)'; onblur='guardarHC(papd06)'/></td>"													
					+"</tr></table></td></tr>" +
					//m
					//"<tr id='ocpapm' style='display: none'> <td width='30%'><div id='papm'>M <br><a href='#' onclick='deshabilitarFilaSv(papm)'>Eliminar</a></div></td>" +
					"<tr id='ocpapm' style='display: none'> <td width='30%'><div id='papm'>M<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(papm)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='papm7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm7)'; onblur='guardarHC(papm7)'/></td>" 
					+"<td  width='2.8%'><input id='papm8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm8)'; onblur='guardarHC(papm8)'/></td>" 
					+"<td  width='2.8%'><input id='papm9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm9)'; onblur='guardarHC(papm9)'/></td>" 
					+"<td  width='2.8%'><input id='papm10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm10)'; onblur='guardarHC(papm10)'/></td>" 
					+"<td  width='2.8%'><input id='papm11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm11)'; onblur='guardarHC(papm11)'/></td>" 
					+"<td  width='2.8%'><input id='papm12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm12)'; onblur='guardarHC(papm12)'/></td>"
					+"<td  width='2.8%'><input id='papm13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm13)'; onblur='guardarHC(papm13)'/></td>" 
					+"<td  width='2.8%'><input id='papm14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm14)'; onblur='guardarHC(papm14)'/></td>" 
					+"<td  width='2.8%'><input id='papm15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm15)'; onblur='guardarHC(papm15)'/></td>" 
					+"<td  width='2.8%'><input id='papm16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm16)'; onblur='guardarHC(papm16)'/></td>" 
					+"<td  width='2.8%'><input id='papm17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm17)'; onblur='guardarHC(papm17)'/></td>" 
					+"<td  width='2.8%'><input id='papm18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm18)'; onblur='guardarHC(papm18)'/></td>" 
					+"<td  width='2.8%'><input id='papm19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm19)'; onblur='guardarHC(papm19)'/></td>" 
					+"<td  width='2.8%'><input id='papm20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm20)'; onblur='guardarHC(papm20)'/></td>" 
					+"<td  width='2.8%'><input id='papm21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm21)'; onblur='guardarHC(papm21)'/></td>" 
					+"<td  width='2.8%'><input id='papm22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm22)'; onblur='guardarHC(papm22)'/></td>" 
					+"<td  width='2.8%'><input id='papm23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm23)'; onblur='guardarHC(papm23)'/></td>" 
					+"<td  width='2.8%'><input id='papm00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm00)'; onblur='guardarHC(papm00)'/></td>" 
					+"<td  width='2.8%'><input id='papm01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm01)'; onblur='guardarHC(papm01)'/></td>" 
					+"<td  width='2.8%'><input id='papm02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm02)'; onblur='guardarHC(papm02)'/></td>" 
					+"<td  width='2.8%'><input id='papm03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm03)'; onblur='guardarHC(papm03)'/></td>" 
					+"<td  width='2.8%'><input id='papm04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm04)'; onblur='guardarHC(papm04)'/></td>" 
					+"<td  width='2.8%'><input id='papm05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm05)'; onblur='guardarHC(papm05)'/></td>" 
					+"<td  width='2.8%'><input id='papm06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papm06)'; onblur='guardarHC(papm06)'/></td>"													
					+"</tr></table></td></tr>" +
					//c
					//"<tr bgcolor='#D8D8D8' id='ocpapc' style='display: none'> <td width='30%'><div id='papc'>C <br><a href='#' onclick='deshabilitarFilaSv(papc)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocpapc' style='display: none'> <td width='30%'><div id='papc'>C<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(papc)'  style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='papc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc7)'; onblur='guardarHC(papc7)'/></td>" 
					+"<td  width='2.8%'><input id='papc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc8)'; onblur='guardarHC(papc8)'/></td>" 
					+"<td  width='2.8%'><input id='papc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc9)'; onblur='guardarHC(papc9)'/></td>" 
					+"<td  width='2.8%'><input id='papc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc10)'; onblur='guardarHC(papc10)'/></td>" 
					+"<td  width='2.8%'><input id='papc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc11)'; onblur='guardarHC(papc11)'/></td>" 
					+"<td  width='2.8%'><input id='papc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc12)'; onblur='guardarHC(papc12)'/></td>"
					+"<td  width='2.8%'><input id='papc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc13)'; onblur='guardarHC(papc13)'/></td>" 
					+"<td  width='2.8%'><input id='papc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc14)'; onblur='guardarHC(papc14)'/></td>" 
					+"<td  width='2.8%'><input id='papc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc15)'; onblur='guardarHC(papc15)'/></td>" 
					+"<td  width='2.8%'><input id='papc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc16)'; onblur='guardarHC(papc16)'/></td>" 
					+"<td  width='2.8%'><input id='papc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc17)'; onblur='guardarHC(papc17)'/></td>" 
					+"<td  width='2.8%'><input id='papc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc18)'; onblur='guardarHC(papc18)'/></td>" 
					+"<td  width='2.8%'><input id='papc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc19)'; onblur='guardarHC(papc19)'/></td>" 
					+"<td  width='2.8%'><input id='papc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc20)'; onblur='guardarHC(papc20)'/></td>" 
					+"<td  width='2.8%'><input id='papc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc21)'; onblur='guardarHC(papc21)'/></td>" 
					+"<td  width='2.8%'><input id='papc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc22)'; onblur='guardarHC(papc22)'/></td>" 
					+"<td  width='2.8%'><input id='papc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc23)'; onblur='guardarHC(papc23)'/></td>" 
					+"<td  width='2.8%'><input id='papc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc00)'; onblur='guardarHC(papc00)'/></td>" 
					+"<td  width='2.8%'><input id='papc01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc01)'; onblur='guardarHC(papc01)'/></td>" 
					+"<td  width='2.8%'><input id='papc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc02)'; onblur='guardarHC(papc02)'/></td>" 
					+"<td  width='2.8%'><input id='papc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc03)'; onblur='guardarHC(papc03)'/></td>" 
					+"<td  width='2.8%'><input id='papc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc04)'; onblur='guardarHC(papc04)'/></td>" 
					+"<td  width='2.8%'><input id='papc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc05)'; onblur='guardarHC(papc05)'/></td>" 
					+"<td  width='2.8%'><input id='papc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(papc06)'; onblur='guardarHC(papc06)'/></td>"													
					+"</tr></table></td></tr>" +
					//PARAMETROS HEMODINAMICOS
					"<tr id='ocdivpamhemo' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>PARAMETROS HEMODINAMICOS</CENTER></div></td>" +
					"<td> <a href='#' onclick='ocultarPamhemo()'><font color='white'>Ocultar</font></a>" +
					"</td>"+
					"</tr>"+
					//pwc
					//"<tr id='ocpwc' style='display: none'> <td width='30%'><div id='pwc'>PWC <br><a href='#' onclick='deshabilitarFilaSv(pwc)'>Eliminar</a></div></td>" +
					"<tr id='ocpwc' style='display: none'> <td width='30%'><div id='pwc'>PWC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(pwc)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='pwc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc7)'; onblur='guardarHC(pwc7)'/></td>" 
					+"<td  width='2.8%'><input id='pwc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc8)'; onblur='guardarHC(pwc8)'/></td>" 
					+"<td  width='2.8%'><input id='pwc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc9)'; onblur='guardarHC(pwc9)'/></td>" 
					+"<td  width='2.8%'><input id='pwc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc10)'; onblur='guardarHC(pwc10)'/></td>" 
					+"<td  width='2.8%'><input id='pwc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc11)'; onblur='guardarHC(pwc11)'/></td>" 
					+"<td  width='2.8%'><input id='pwc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc12)'; onblur='guardarHC(pwc12)'/></td>"
					+"<td  width='2.8%'><input id='pwc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc13)'; onblur='guardarHC(pwc13)'/></td>" 
					+"<td  width='2.8%'><input id='pwc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc14)'; onblur='guardarHC(pwc14)'/></td>" 
					+"<td  width='2.8%'><input id='pwc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc15)'; onblur='guardarHC(pwc15)'/></td>" 
					+"<td  width='2.8%'><input id='pwc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc16)'; onblur='guardarHC(pwc16)'/></td>" 
					+"<td  width='2.8%'><input id='pwc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc17)'; onblur='guardarHC(pwc17)'/></td>" 
					+"<td  width='2.8%'><input id='pwc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc18)'; onblur='guardarHC(pwc18)'/></td>" 
					+"<td  width='2.8%'><input id='pwc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc19)'; onblur='guardarHC(pwc19)'/></td>" 
					+"<td  width='2.8%'><input id='pwc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc20)'; onblur='guardarHC(pwc20)'/></td>" 
					+"<td  width='2.8%'><input id='pwc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc21)'; onblur='guardarHC(pwc21)'/></td>" 
					+"<td  width='2.8%'><input id='pwc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc22)'; onblur='guardarHC(pwc22)'/></td>" 
					+"<td  width='2.8%'><input id='pwc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc23)'; onblur='guardarHC(pwc23)'/></td>" 
					+"<td  width='2.8%'><input id='pwc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc00)'; onblur='guardarHC(pwc00)'/></td>" 
					+"<td  width='2.8%'><input id='pwc01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc01)'; onblur='guardarHC(pwc01)'/></td>" 
					+"<td  width='2.8%'><input id='pwc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc02)'; onblur='guardarHC(pwc02)'/></td>" 
					+"<td  width='2.8%'><input id='pwc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc03)'; onblur='guardarHC(pwc03)'/></td>" 
					+"<td  width='2.8%'><input id='pwc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc04)'; onblur='guardarHC(pwc04)'/></td>" 
					+"<td  width='2.8%'><input id='pwc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc05)'; onblur='guardarHC(pwc05)'/></td>" 
					+"<td  width='2.8%'><input id='pwc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(pwc06)'; onblur='guardarHC(pwc06)'/></td>"													
					+"</tr></table></td></tr>" +
					//vvs
					//"<tr bgcolor='#D8D8D8' id='ocvvs' style='display: none'> <td width='30%'><div id='vvs'>VVS <br><a href='#' onclick='deshabilitarFilaSv(vvs)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocvvs' style='display: none'> <td width='30%'><div id='vvs'>VVS<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(vvs)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='vvs7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs7)'; onblur='guardarHC(vvs7)'/></td>" 
					+"<td  width='2.8%'><input id='vvs8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs8)'; onblur='guardarHC(vvs8)'/></td>" 
					+"<td  width='2.8%'><input id='vvs9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs9)'; onblur='guardarHC(vvs9)'/></td>" 
					+"<td  width='2.8%'><input id='vvs10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs10)'; onblur='guardarHC(vvs10)'/></td>" 
					+"<td  width='2.8%'><input id='vvs11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs11)'; onblur='guardarHC(vvs11)'/></td>" 
					+"<td  width='2.8%'><input id='vvs12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs12)'; onblur='guardarHC(vvs12)'/></td>"
					+"<td  width='2.8%'><input id='vvs13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs13)'; onblur='guardarHC(vvs13)'/></td>" 
					+"<td  width='2.8%'><input id='vvs14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs14)'; onblur='guardarHC(vvs14)'/></td>" 
					+"<td  width='2.8%'><input id='vvs15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs15)'; onblur='guardarHC(vvs15)'/></td>" 
					+"<td  width='2.8%'><input id='vvs16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs16)'; onblur='guardarHC(vvs16)'/></td>" 
					+"<td  width='2.8%'><input id='vvs17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs17)'; onblur='guardarHC(vvs17)'/></td>" 
					+"<td  width='2.8%'><input id='vvs18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs18)'; onblur='guardarHC(vvs18)'/></td>" 
					+"<td  width='2.8%'><input id='vvs19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs19)'; onblur='guardarHC(vvs19)'/></td>" 
					+"<td  width='2.8%'><input id='vvs20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs20)'; onblur='guardarHC(vvs20)'/></td>" 
					+"<td  width='2.8%'><input id='vvs21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs21)'; onblur='guardarHC(vvs21)'/></td>" 
					+"<td  width='2.8%'><input id='vvs22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs22)'; onblur='guardarHC(vvs22)'/></td>" 
					+"<td  width='2.8%'><input id='vvs23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs23)'; onblur='guardarHC(vvs23)'/></td>" 
					+"<td  width='2.8%'><input id='vvs00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs00)'; onblur='guardarHC(vvs00)'/></td>" 
					+"<td  width='2.8%'><input id='vvs01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs01)'; onblur='guardarHC(vvs01)'/></td>" 
					+"<td  width='2.8%'><input id='vvs02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs02)'; onblur='guardarHC(vvs02)'/></td>" 
					+"<td  width='2.8%'><input id='vvs03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs03)'; onblur='guardarHC(vvs03)'/></td>" 
					+"<td  width='2.8%'><input id='vvs04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs05)'; onblur='guardarHC(vvs04)'/></td>" 
					+"<td  width='2.8%'><input id='vvs05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs05)'; onblur='guardarHC(vvs05)'/></td>" 
					+"<td  width='2.8%'><input id='vvs06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(vvs06)'; onblur='guardarHC(vvs06)'/></td>"													
					+"</tr></table></td></tr>" +
					//gc
					//"<tr id='ocgc' style='display: none'> <td width='30%'><div id='gc'>GC <br><a href='#' onclick='deshabilitarFilaSv(gc)'>Eliminar</a></div></td>" +
					"<tr id='ocgc' style='display: none'> <td width='30%'><div id='gc'>GC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(gc)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='gc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc7)'; onblur='guardarHC(gc7)'/></td>" 
					+"<td  width='2.8%'><input id='gc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc8)'; onblur='guardarHC(gc8)'/></td>" 
					+"<td  width='2.8%'><input id='gc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc9)'; onblur='guardarHC(gc9)'/></td>" 
					+"<td  width='2.8%'><input id='gc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc10)'; onblur='guardarHC(gc10)'/></td>" 
					+"<td  width='2.8%'><input id='gc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc11)'; onblur='guardarHC(gc11)'/></td>" 
					+"<td  width='2.8%'><input id='gc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc12)'; onblur='guardarHC(gc12)'/></td>"
					+"<td  width='2.8%'><input id='gc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc13)'; onblur='guardarHC(gc13)'/></td>" 
					+"<td  width='2.8%'><input id='gc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc14)'; onblur='guardarHC(gc14)'/></td>" 
					+"<td  width='2.8%'><input id='gc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc15)'; onblur='guardarHC(gc15)'/></td>" 
					+"<td  width='2.8%'><input id='gc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc16)'; onblur='guardarHC(gc16)'/></td>" 
					+"<td  width='2.8%'><input id='gc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc17)'; onblur='guardarHC(gc17)'/></td>" 
					+"<td  width='2.8%'><input id='gc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc18)'; onblur='guardarHC(gc18)'/></td>" 
					+"<td  width='2.8%'><input id='gc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc19)'; onblur='guardarHC(gc19)'/></td>" 
					+"<td  width='2.8%'><input id='gc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc20)'; onblur='guardarHC(gc20)'/></td>" 
					+"<td  width='2.8%'><input id='gc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc21)'; onblur='guardarHC(gc21)'/></td>" 
					+"<td  width='2.8%'><input id='gc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc22)'; onblur='guardarHC(gc22)'/></td>" 
					+"<td  width='2.8%'><input id='gc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc23)'; onblur='guardarHC(gc23)'/></td>" 
					+"<td  width='2.8%'><input id='gc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc00)'; onblur='guardarHC(gc00)'/></td>" 
					+"<td  width='2.8%'><input id='gc01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc01)'; onblur='guardarHC(gc01)'/></td>" 
					+"<td  width='2.8%'><input id='gc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc02)'; onblur='guardarHC(gc02)'/></td>" 
					+"<td  width='2.8%'><input id='gc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc03)'; onblur='guardarHC(gc03)'/></td>" 
					+"<td  width='2.8%'><input id='gc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc04)'; onblur='guardarHC(gc04)'/></td>" 
					+"<td  width='2.8%'><input id='gc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc05)'; onblur='guardarHC(gc05)'/></td>" 
					+"<td  width='2.8%'><input id='gc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(gc06)'; onblur='guardarHC(gc06)'/></td>"													
					+"</tr></table></td></tr>" +
					//ic
					//"<tr bgcolor='#D8D8D8' id='ocic' style='display: none'> <td width='30%'><div id='ic'>IC <br><a href='#' onclick='deshabilitarFilaSv(ic)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocic' style='display: none'> <td width='30%'><div id='ic'>IC<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(ic)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='ic7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic7)'; onblur='guardarHC(ic7)'/></td>" 
					+"<td  width='2.8%'><input id='ic8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic8)'; onblur='guardarHC(ic8)'/></td>" 
					+"<td  width='2.8%'><input id='ic9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic9)'; onblur='guardarHC(ic9)'/></td>" 
					+"<td  width='2.8%'><input id='ic10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic10)'; onblur='guardarHC(ic10)'/></td>" 
					+"<td  width='2.8%'><input id='ic11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic11)'; onblur='guardarHC(ic11)'/></td>" 
					+"<td  width='2.8%'><input id='ic12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic12)'; onblur='guardarHC(ic12)'/></td>"
					+"<td  width='2.8%'><input id='ic13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic13)'; onblur='guardarHC(ic13)'/></td>" 
					+"<td  width='2.8%'><input id='ic14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic14)'; onblur='guardarHC(ic14)'/></td>" 
					+"<td  width='2.8%'><input id='ic15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic15)'; onblur='guardarHC(ic15)'/></td>" 
					+"<td  width='2.8%'><input id='ic16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic16)'; onblur='guardarHC(ic16)'/></td>" 
					+"<td  width='2.8%'><input id='ic17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic17)'; onblur='guardarHC(ic17)'/></td>" 
					+"<td  width='2.8%'><input id='ic18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic18)'; onblur='guardarHC(ic18)'/></td>" 
					+"<td  width='2.8%'><input id='ic19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic19)'; onblur='guardarHC(ic19)'/></td>" 
					+"<td  width='2.8%'><input id='ic20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic20)'; onblur='guardarHC(ic20)'/></td>" 
					+"<td  width='2.8%'><input id='ic21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic21)'; onblur='guardarHC(ic21)'/></td>" 
					+"<td  width='2.8%'><input id='ic22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic22)'; onblur='guardarHC(ic22)'/></td>" 
					+"<td  width='2.8%'><input id='ic23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic23)'; onblur='guardarHC(ic23)'/></td>" 
					+"<td  width='2.8%'><input id='ic00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic00)'; onblur='guardarHC(ic00)'/></td>" 
					+"<td  width='2.8%'><input id='ic01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic01)'; onblur='guardarHC(ic01)'/></td>" 
					+"<td  width='2.8%'><input id='ic02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic02)'; onblur='guardarHC(ic02)'/></td>" 
					+"<td  width='2.8%'><input id='ic03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic03)'; onblur='guardarHC(ic03)'/></td>" 
					+"<td  width='2.8%'><input id='ic04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic04)'; onblur='guardarHC(ic04)'/></td>" 
					+"<td  width='2.8%'><input id='ic05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic05)'; onblur='guardarHC(ic05)'/></td>" 
					+"<td  width='2.8%'><input id='ic06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(ic06)'; onblur='guardarHC(ic06)'/></td>"													
					+"</tr></table></td></tr>" +
					//irvs
					//"<tr id='ocirvs' style='display: none'> <td width='30%'><div id='irvs'>IRVS <br><a href='#' onclick='deshabilitarFilaSv(irvs)'>Eliminar</a></div></td>" +
					"<tr id='ocirvs' style='display: none'> <td width='30%'><div id='irvs'>IRVS<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(irvs)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='irvs7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs7)'; onblur='guardarHC(irvs7)'/></td>" 
					+"<td  width='2.8%'><input id='irvs8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs8)'; onblur='guardarHC(irvs8)'/></td>" 
					+"<td  width='2.8%'><input id='irvs9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs9)'; onblur='guardarHC(irvs9)'/></td>" 
					+"<td  width='2.8%'><input id='irvs10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs10)'; onblur='guardarHC(irvs10)'/></td>" 
					+"<td  width='2.8%'><input id='irvs11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs11)'; onblur='guardarHC(irvs11)'/></td>" 
					+"<td  width='2.8%'><input id='irvs12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs12)'; onblur='guardarHC(irvs12)'/></td>"
					+"<td  width='2.8%'><input id='irvs13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs13)'; onblur='guardarHC(irvs13)'/></td>" 
					+"<td  width='2.8%'><input id='irvs14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs14)'; onblur='guardarHC(irvs14)'/></td>" 
					+"<td  width='2.8%'><input id='irvs15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs15)'; onblur='guardarHC(irvs15)'/></td>" 
					+"<td  width='2.8%'><input id='irvs16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs16)'; onblur='guardarHC(irvs16)'/></td>" 
					+"<td  width='2.8%'><input id='irvs17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs17)'; onblur='guardarHC(irvs17)'/></td>" 
					+"<td  width='2.8%'><input id='irvs18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs18)'; onblur='guardarHC(irvs18)'/></td>" 
					+"<td  width='2.8%'><input id='irvs19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs19)'; onblur='guardarHC(irvs19)'/></td>" 
					+"<td  width='2.8%'><input id='irvs20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs20)'; onblur='guardarHC(irvs20)'/></td>" 
					+"<td  width='2.8%'><input id='irvs21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs21)'; onblur='guardarHC(irvs21)'/></td>" 
					+"<td  width='2.8%'><input id='irvs22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs22)'; onblur='guardarHC(irvs22)'/></td>" 
					+"<td  width='2.8%'><input id='irvs23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs23)'; onblur='guardarHC(irvs23)'/></td>" 
					+"<td  width='2.8%'><input id='irvs00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs00)'; onblur='guardarHC(irvs00)'/></td>" 
					+"<td  width='2.8%'><input id='irvs01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs01)'; onblur='guardarHC(irvs01)'/></td>" 
					+"<td  width='2.8%'><input id='irvs02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs02)'; onblur='guardarHC(irvs02)'/></td>" 
					+"<td  width='2.8%'><input id='irvs03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs03)'; onblur='guardarHC(irvs03)'/></td>" 
					+"<td  width='2.8%'><input id='irvs04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs04)'; onblur='guardarHC(irvs04)'/></td>" 
					+"<td  width='2.8%'><input id='irvs05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvs05)'; onblur='guardarHC(irvs05)'/></td>" 
					+"<td  width='2.8%'><input id='irvs06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(irvs06)'; onblur='guardarHC(irvs06)'/></td>"													
					+"</tr></table></td></tr>" +
					//irvp
					//"<tr bgcolor='#D8D8D8' id='ocirvp' style='display: none'> <td width='30%'><div id='irvp'>IRVP <br><a href='#' onclick='deshabilitarFilaSv(irvp)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocirvp' style='display: none'> <td width='30%'><div id='irvp'>IRVP<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(irvp)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='irvp7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp7)'; onblur='guardarHC(irvp7)'/></td>" 
					+"<td  width='2.8%'><input id='irvp8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp8)'; onblur='guardarHC(irvp8)'/></td>" 
					+"<td  width='2.8%'><input id='irvp9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp9)'; onblur='guardarHC(irvp9)'/></td>" 
					+"<td  width='2.8%'><input id='irvp10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp10)'; onblur='guardarHC(irvp10)'/></td>" 
					+"<td  width='2.8%'><input id='irvp11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp11)'; onblur='guardarHC(irvp11)'/></td>" 
					+"<td  width='2.8%'><input id='irvp12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp12)'; onblur='guardarHC(irvp12)'/></td>"
					+"<td  width='2.8%'><input id='irvp13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp13)'; onblur='guardarHC(irvp13)'/></td>" 
					+"<td  width='2.8%'><input id='irvp14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp14)'; onblur='guardarHC(irvp14)'/></td>" 
					+"<td  width='2.8%'><input id='irvp15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp15)'; onblur='guardarHC(irvp15)'/></td>" 
					+"<td  width='2.8%'><input id='irvp16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp16)'; onblur='guardarHC(irvp16)'/></td>" 
					+"<td  width='2.8%'><input id='irvp17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp17)'; onblur='guardarHC(irvp17)'/></td>" 
					+"<td  width='2.8%'><input id='irvp18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp18)'; onblur='guardarHC(irvp18)'/></td>" 
					+"<td  width='2.8%'><input id='irvp19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp19)'; onblur='guardarHC(irvp19)'/></td>" 
					+"<td  width='2.8%'><input id='irvp20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp20)'; onblur='guardarHC(irvp20)'/></td>" 
					+"<td  width='2.8%'><input id='irvp21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp21)'; onblur='guardarHC(irvp21)'/></td>" 
					+"<td  width='2.8%'><input id='irvp22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp22)'; onblur='guardarHC(irvp22)'/></td>" 
					+"<td  width='2.8%'><input id='irvp23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp23)'; onblur='guardarHC(irvp23)'/></td>" 
					+"<td  width='2.8%'><input id='irvp00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp00)'; onblur='guardarHC(irvp00)'/></td>" 
					+"<td  width='2.8%'><input id='irvp01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp01)'; onblur='guardarHC(irvp01)'/></td>" 
					+"<td  width='2.8%'><input id='irvp02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp02)'; onblur='guardarHC(irvp02)'/></td>" 
					+"<td  width='2.8%'><input id='irvp03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp03)'; onblur='guardarHC(irvp03)'/></td>" 
					+"<td  width='2.8%'><input id='irvp04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp04)'; onblur='guardarHC(irvp04)'/></td>" 
					+"<td  width='2.8%'><input id='irvp05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp05)'; onblur='guardarHC(irvp05)'/></td>" 
					+"<td  width='2.8%'><input id='irvp06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(irvp06)'; onblur='guardarHC(irvp06)'/></td>"													
					+"</tr></table></td></tr>" +
					//itvi
					//"<tr id='ocitvi' style='display: none'> <td width='30%'><div id='itvi'>ITVI <br><a href='#' onclick='deshabilitarFilaSv(itvi)'>Eliminar</a></div></td>" +
					"<tr id='ocitvi' style='display: none'> <td width='30%'><div id='itvi'>ITVI<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(itvi)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='itvi7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi7)'; onblur='guardarHC(itvi7)'/></td>" 
					+"<td  width='2.8%'><input id='itvi8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi8)'; onblur='guardarHC(itvi8)'/></td>" 
					+"<td  width='2.8%'><input id='itvi9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi9)'; onblur='guardarHC(itvi9)'/></td>" 
					+"<td  width='2.8%'><input id='itvi10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi10)'; onblur='guardarHC(itvi10)'/></td>" 
					+"<td  width='2.8%'><input id='itvi11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi11)'; onblur='guardarHC(itvi11)'/></td>" 
					+"<td  width='2.8%'><input id='itvi12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi12)'; onblur='guardarHC(itvi12)'/></td>"
					+"<td  width='2.8%'><input id='itvi13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi13)'; onblur='guardarHC(itvi13)'/></td>" 
					+"<td  width='2.8%'><input id='itvi14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi14)'; onblur='guardarHC(itvi14)'/></td>" 
					+"<td  width='2.8%'><input id='itvi15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi15)'; onblur='guardarHC(itvi15)'/></td>" 
					+"<td  width='2.8%'><input id='itvi16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi16)'; onblur='guardarHC(itvi16)'/></td>" 
					+"<td  width='2.8%'><input id='itvi17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi17)'; onblur='guardarHC(itvi17)'/></td>" 
					+"<td  width='2.8%'><input id='itvi18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi18)'; onblur='guardarHC(itvi18)'/></td>" 
					+"<td  width='2.8%'><input id='itvi19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi19)'; onblur='guardarHC(itvi19)'/></td>" 
					+"<td  width='2.8%'><input id='itvi20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi20)'; onblur='guardarHC(itvi20)'/></td>" 
					+"<td  width='2.8%'><input id='itvi21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi21)'; onblur='guardarHC(itvi21)'/></td>" 
					+"<td  width='2.8%'><input id='itvi22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi22)'; onblur='guardarHC(itvi22)'/></td>" 
					+"<td  width='2.8%'><input id='itvi23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi23)'; onblur='guardarHC(itvi23)'/></td>" 
					+"<td  width='2.8%'><input id='itvi00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi00)'; onblur='guardarHC(itvi00)'/></td>" 
					+"<td  width='2.8%'><input id='itvi01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi01)'; onblur='guardarHC(itvi01)'/></td>" 
					+"<td  width='2.8%'><input id='itvi02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi02)'; onblur='guardarHC(itvi02)'/></td>" 
					+"<td  width='2.8%'><input id='itvi03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi03)'; onblur='guardarHC(itvi03)'/></td>" 
					+"<td  width='2.8%'><input id='itvi04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi04)'; onblur='guardarHC(itvi04)'/></td>" 
					+"<td  width='2.8%'><input id='itvi05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi05)'; onblur='guardarHC(itvi05)'/></td>" 
					+"<td  width='2.8%'><input id='itvi06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvi06)'; onblur='guardarHC(itvi06)'/></td>"													
					+"</tr></table></td></tr>" +
					//itvd
					//"<tr bgcolor='#D8D8D8' id='ocitvd' style='display: none'> <td width='30%'><div id='itvd'>ITVD <br><a href='#' onclick='deshabilitarFilaSv(itvd)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocitvd' style='display: none'> <td width='30%'><div id='itvd'>ITVD<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(itvd)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='itvd7' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd7)'; onblur='guardarHC(itvd7)'/></td>" 
					+"<td  width='2.8%'><input id='itvd8' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd8)'; onblur='guardarHC(itvd8)'/></td>" 
					+"<td  width='2.8%'><input id='itvd9' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd9)'; onblur='guardarHC(itvd9)'/></td>" 
					+"<td  width='2.8%'><input id='itvd10' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd10)'; onblur='guardarHC(itvd10)'/></td>" 
					+"<td  width='2.8%'><input id='itvd11' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd11)'; onblur='guardarHC(itvd11)'/></td>" 
					+"<td  width='2.8%'><input id='itvd12' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd12)'; onblur='guardarHC(itvd12)'/></td>"
					+"<td  width='2.8%'><input id='itvd13' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd13)'; onblur='guardarHC(itvd13)'/></td>" 
					+"<td  width='2.8%'><input id='itvd14' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd14)'; onblur='guardarHC(itvd14)'/></td>" 
					+"<td  width='2.8%'><input id='itvd15' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd15)'; onblur='guardarHC(itvd15)'/></td>" 
					+"<td  width='2.8%'><input id='itvd16' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd16)'; onblur='guardarHC(itvd16)'/></td>" 
					+"<td  width='2.8%'><input id='itvd17' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd17)'; onblur='guardarHC(itvd17)'/></td>" 
					+"<td  width='2.8%'><input id='itvd18' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd18)'; onblur='guardarHC(itvd18)'/></td>" 
					+"<td  width='2.8%'><input id='itvd19' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd19)'; onblur='guardarHC(itvd19)'/></td>" 
					+"<td  width='2.8%'><input id='itvd20' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd20)'; onblur='guardarHC(itvd20)'/></td>" 
					+"<td  width='2.8%'><input id='itvd21' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd21)'; onblur='guardarHC(itvd21)'/></td>" 
					+"<td  width='2.8%'><input id='itvd22' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd22)'; onblur='guardarHC(itvd22)'/></td>" 
					+"<td  width='2.8%'><input id='itvd23' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd23)'; onblur='guardarHC(itvd23)'/></td>" 
					+"<td  width='2.8%'><input id='itvd00' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd00)'; onblur='guardarHC(itvd00)'/></td>" 
					+"<td  width='2.8%'><input id='itvd01' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd01)'; onblur='guardarHC(itvd01)'/></td>" 
					+"<td  width='2.8%'><input id='itvd02' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd02)'; onblur='guardarHC(itvd02)'/></td>" 
					+"<td  width='2.8%'><input id='itvd03' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd03)'; onblur='guardarHC(itvd03)'/></td>" 
					+"<td  width='2.8%'><input id='itvd04' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd04)'; onblur='guardarHC(itvd04)'/></td>" 
					+"<td  width='2.8%'><input id='itvd05' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd05)'; onblur='guardarHC(itvd05)'/></td>" 
					+"<td  width='2.8%'><input id='itvd06' type='text' style='border: 1px solid #585858' size='1' onkeyup='decimalsv(itvd06)'; onblur='guardarHC(itvd06)'/></td>"													
					+"</tr></table></td></tr>" +
					//PULSO
					"<tr id='ocdivpulso' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>PULSO</CENTER></div></td>" +
					"<td> <a href='#' onclick='ocultarPulso()'><font color='white'>Ocultar</font></a>" +
					"</td>"+
					"</tr>"+
					
					//"<tr id='ocpuld' style='display: none'> <td width='30%'><div id='puld'>D <br><a href='#' onclick='deshabilitarFilaSv(puld)'>Eliminar</a></div></td>" +
					"<tr id='ocpuld' style='display: none'> <td width='30%'><div id='puld'>D<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(puld)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='puld7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld7)'; onblur='guardarHC(puld7)'/></td>" 
					+"<td  width='2.8%'><input id='puld8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld8)'; onblur='guardarHC(puld8)'/></td>" 
					+"<td  width='2.8%'><input id='puld9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld9)'; onblur='guardarHC(puld9)'/></td>" 
					+"<td  width='2.8%'><input id='puld10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld10)'; onblur='guardarHC(puld10)'/></td>" 
					+"<td  width='2.8%'><input id='puld11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld11)'; onblur='guardarHC(puld11)'/></td>" 
					+"<td  width='2.8%'><input id='puld12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld12)'; onblur='guardarHC(puld12)'/></td>"
					+"<td  width='2.8%'><input id='puld13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld13)'; onblur='guardarHC(puld13)'/></td>" 
					+"<td  width='2.8%'><input id='puld14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld14)'; onblur='guardarHC(puld14)'/></td>" 
					+"<td  width='2.8%'><input id='puld15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld15)'; onblur='guardarHC(puld15)'/></td>" 
					+"<td  width='2.8%'><input id='puld16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld16)'; onblur='guardarHC(puld16)'/></td>" 
					+"<td  width='2.8%'><input id='puld17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld17)'; onblur='guardarHC(puld17)'/></td>" 
					+"<td  width='2.8%'><input id='puld18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld18)'; onblur='guardarHC(puld18)'/></td>" 
					+"<td  width='2.8%'><input id='puld19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld19)'; onblur='guardarHC(puld19)'/></td>" 
					+"<td  width='2.8%'><input id='puld20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld20)'; onblur='guardarHC(puld20)'/></td>" 
					+"<td  width='2.8%'><input id='puld21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld21)'; onblur='guardarHC(puld21)'/></td>" 
					+"<td  width='2.8%'><input id='puld22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld22)'; onblur='guardarHC(puld22)'/></td>" 
					+"<td  width='2.8%'><input id='puld23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld23)'; onblur='guardarHC(puld23)'/></td>" 
					+"<td  width='2.8%'><input id='puld00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld00)'; onblur='guardarHC(puld00)'/></td>" 
					+"<td  width='2.8%'><input id='puld01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld01)'; onblur='guardarHC(puld01)'/></td>" 
					+"<td  width='2.8%'><input id='puld02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld02)'; onblur='guardarHC(puld02)'/></td>" 
					+"<td  width='2.8%'><input id='puld03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld03)'; onblur='guardarHC(puld03)'/></td>" 
					+"<td  width='2.8%'><input id='puld04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld04)'; onblur='guardarHC(puld04)'/></td>" 
					+"<td  width='2.8%'><input id='puld05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld05)'; onblur='guardarHC(puld05)'/></td>" 
					+"<td  width='2.8%'><input id='puld06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puld06)'; onblur='guardarHC(puld06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					//"<tr bgcolor='#D8D8D8' id='ocpuli' style='display: none'> <td width='30%'><div id='puli'>I <br><a href='#' onclick='deshabilitarFilaSv(puli)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocpuli' style='display: none'> <td width='30%'><div id='puli'>I<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(puli)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='puli7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli7)'; onblur='guardarHC(puli7)'/></td>" 
					+"<td  width='2.8%'><input id='puli8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli8)'; onblur='guardarHC(puli8)'/></td>" 
					+"<td  width='2.8%'><input id='puli9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli9)'; onblur='guardarHC(puli9)'/></td>" 
					+"<td  width='2.8%'><input id='puli10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli10)'; onblur='guardarHC(puli10)'/></td>" 
					+"<td  width='2.8%'><input id='puli11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli11)'; onblur='guardarHC(puli11)'/></td>" 
					+"<td  width='2.8%'><input id='puli12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli12)'; onblur='guardarHC(puli12)'/></td>"
					+"<td  width='2.8%'><input id='puli13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli13)'; onblur='guardarHC(puli13)'/></td>" 
					+"<td  width='2.8%'><input id='puli14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli14)'; onblur='guardarHC(puli14)'/></td>" 
					+"<td  width='2.8%'><input id='puli15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli15)'; onblur='guardarHC(puli15)'/></td>" 
					+"<td  width='2.8%'><input id='puli16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli16)'; onblur='guardarHC(puli16)'/></td>" 
					+"<td  width='2.8%'><input id='puli17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli17)'; onblur='guardarHC(puli17)'/></td>" 
					+"<td  width='2.8%'><input id='puli18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli18)'; onblur='guardarHC(puli18)'/></td>" 
					+"<td  width='2.8%'><input id='puli19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli19)'; onblur='guardarHC(puli19)'/></td>" 
					+"<td  width='2.8%'><input id='puli20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli20)'; onblur='guardarHC(puli20)'/></td>" 
					+"<td  width='2.8%'><input id='puli21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli21)'; onblur='guardarHC(puli21)'/></td>" 
					+"<td  width='2.8%'><input id='puli22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli22)'; onblur='guardarHC(puli22)'/></td>" 
					+"<td  width='2.8%'><input id='puli23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli23)'; onblur='guardarHC(puli23)'/></td>" 
					+"<td  width='2.8%'><input id='puli00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli00)'; onblur='guardarHC(puli00)'/></td>" 
					+"<td  width='2.8%'><input id='puli01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli01)'; onblur='guardarHC(puli01)'/></td>" 
					+"<td  width='2.8%'><input id='puli02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli02)'; onblur='guardarHC(puli02)'/></td>" 
					+"<td  width='2.8%'><input id='puli03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli03)'; onblur='guardarHC(puli03)'/></td>" 
					+"<td  width='2.8%'><input id='puli04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli04)'; onblur='guardarHC(puli04)'/></td>" 
					+"<td  width='2.8%'><input id='puli05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli05)'; onblur='guardarHC(puli05)'/></td>" 
					+"<td  width='2.8%'><input id='puli06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(puli06)'; onblur='guardarHC(puli06)'/></td>"													
					+"</tr></table></td></tr>" +
					//????
					"<tr bgcolor='#2E9AFE' id='ocx' style='display: none'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER></CENTER></div></td>" +
					"<td>  <a href='#' onclick='ocultarX()'><font color='white'>Ocultar</font></a>" +
					"</td>"+
					"</tr>"+
					
					//"<tr id='ocesg' style='display: none'> <td width='30%'><div id='esg'>ESCALA DE GLASGOW <br><a href='#' onclick='deshabilitarFilaSv(esg)'>Eliminar</a></div></td>" +
					"<tr id='ocesg' style='display: none'> <td width='30%'><div id='esg'>ESCALA DE GLASGOW<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(esg)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='esg7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg7)'; onblur='guardarHC(esg7)'/></td>" 
					+"<td  width='2.8%'><input id='esg8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg8)'; onblur='guardarHC(esg8)'/></td>" 
					+"<td  width='2.8%'><input id='esg9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg9)'; onblur='guardarHC(esg9)'/></td>" 
					+"<td  width='2.8%'><input id='esg10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg10)'; onblur='guardarHC(esg10)'/></td>" 
					+"<td  width='2.8%'><input id='esg11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg11)'; onblur='guardarHC(esg11)'/></td>" 
					+"<td  width='2.8%'><input id='esg12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg12)'; onblur='guardarHC(esg12)'/></td>"
					+"<td  width='2.8%'><input id='esg13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg13)'; onblur='guardarHC(esg13)'/></td>" 
					+"<td  width='2.8%'><input id='esg14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg14)'; onblur='guardarHC(esg14)'/></td>" 
					+"<td  width='2.8%'><input id='esg15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg15)'; onblur='guardarHC(esg15)'/></td>" 
					+"<td  width='2.8%'><input id='esg16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg16)'; onblur='guardarHC(esg16)'/></td>" 
					+"<td  width='2.8%'><input id='esg17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg17)'; onblur='guardarHC(esg17)'/></td>" 
					+"<td  width='2.8%'><input id='esg18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg18)'; onblur='guardarHC(esg18)'/></td>" 
					+"<td  width='2.8%'><input id='esg19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg19)'; onblur='guardarHC(esg19)'/></td>" 
					+"<td  width='2.8%'><input id='esg20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg20)'; onblur='guardarHC(esg20)'/></td>" 
					+"<td  width='2.8%'><input id='esg21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg21)'; onblur='guardarHC(esg21)'/></td>" 
					+"<td  width='2.8%'><input id='esg22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg22)'; onblur='guardarHC(esg22)'/></td>" 
					+"<td  width='2.8%'><input id='esg23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg23)'; onblur='guardarHC(esg23)'/></td>" 
					+"<td  width='2.8%'><input id='esg00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg00)'; onblur='guardarHC(esg00)'/></td>" 
					+"<td  width='2.8%'><input id='esg01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg01)'; onblur='guardarHC(esg01)'/></td>" 
					+"<td  width='2.8%'><input id='esg02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg02)'; onblur='guardarHC(esg02)'/></td>" 
					+"<td  width='2.8%'><input id='esg03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg03)'; onblur='guardarHC(esg03)'/></td>" 
					+"<td  width='2.8%'><input id='esg04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg04)'; onblur='guardarHC(esg04)'/></td>" 
					+"<td  width='2.8%'><input id='esg05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg05)'; onblur='guardarHC(esg05)'/></td>" 
					+"<td  width='2.8%'><input id='esg06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(esg06)'; onblur='guardarHC(esg06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					//"<tr id='ocestc' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='estc'>ESTADO DE CONCIENCIA <br><a href='#' onclick='deshabilitarFilaSv(estc)'>Eliminar</a></div></td>" +
					"<tr id='ocestc' bgcolor='#D8D8D8' style='display: none'> <td width='30%'><div id='estc'>ESTADO DE CONCIENCIA <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(estc)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='estc7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc7)'; onblur='guardarHC(estc7)'/></td>" 
					+"<td  width='2.8%'><input id='estc8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc8)'; onblur='guardarHC(estc8)'/></td>" 
					+"<td  width='2.8%'><input id='estc9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc9)'; onblur='guardarHC(estc9)'/></td>" 
					+"<td  width='2.8%'><input id='estc10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc10)'; onblur='guardarHC(estc10)'/></td>" 
					+"<td  width='2.8%'><input id='estc11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc11)'; onblur='guardarHC(estc11)'/></td>" 
					+"<td  width='2.8%'><input id='estc12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc12)'; onblur='guardarHC(estc12)'/></td>"
					+"<td  width='2.8%'><input id='estc13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc13)'; onblur='guardarHC(estc13)'/></td>" 
					+"<td  width='2.8%'><input id='estc14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc14)'; onblur='guardarHC(estc14)'/></td>" 
					+"<td  width='2.8%'><input id='estc15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc15)'; onblur='guardarHC(estc15)'/></td>" 
					+"<td  width='2.8%'><input id='estc16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc16)'; onblur='guardarHC(estc16)'/></td>" 
					+"<td  width='2.8%'><input id='estc17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc17)'; onblur='guardarHC(estc17)'/></td>" 
					+"<td  width='2.8%'><input id='estc18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc18)'; onblur='guardarHC(estc18)'/></td>" 
					+"<td  width='2.8%'><input id='estc19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc19)'; onblur='guardarHC(estc19)'/></td>" 
					+"<td  width='2.8%'><input id='estc20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc20)'; onblur='guardarHC(estc20)'/></td>" 
					+"<td  width='2.8%'><input id='estc21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc21)'; onblur='guardarHC(estc21)'/></td>" 
					+"<td  width='2.8%'><input id='estc22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc22)'; onblur='guardarHC(estc22)'/></td>" 
					+"<td  width='2.8%'><input id='estc23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc23)'; onblur='guardarHC(estc23)'/></td>" 
					+"<td  width='2.8%'><input id='estc00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc00)'; onblur='guardarHC(estc00)'/></td>" 
					+"<td  width='2.8%'><input id='estc01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc01)'; onblur='guardarHC(estc01)'/></td>" 
					+"<td  width='2.8%'><input id='estc02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc02)'; onblur='guardarHC(estc02)'/></td>" 
					+"<td  width='2.8%'><input id='estc03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc03)'; onblur='guardarHC(estc03)'/></td>" 
					+"<td  width='2.8%'><input id='estc04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc04)'; onblur='guardarHC(estc04)'/></td>" 
					+"<td  width='2.8%'><input id='estc05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc05)'; onblur='guardarHC(estc05)'/></td>" 
					+"<td  width='2.8%'><input id='estc06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(estc06)'; onblur='guardarHC(estc06)'/></td>"													
					+"</tr></table></td></tr>" +
					//PUPILAS
					"<tr id='ocdivpupilas' style='display: none' bgcolor='#2E9AFE'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>PUPILAS</CENTER></div></td>" +
					"<td>  <a href='#' onclick='ocultarPupilas()'><font color='white'>Ocultar</font></a>" +
					"</td>"+
					"</tr>"+
					
					"<tr id='ocdivd' style='display: none' bgcolor='#FA5858'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>D</CENTER></div></td>" +
					
					"</tr>"+
					
					//"<tr id='octamd' style='display: none'> <td width='30%'><div id='tamd'>TAMA\u00D1O <br><a href='#' onclick='deshabilitarFilaSv(tamd)'>Eliminar</a></div></td>" +
					"<tr id='octamd' style='display: none'> <td width='30%'><div id='tamd'>TAMA\u00D1O <img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tamd)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='tamd7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd7)'; onblur='guardarHC(tamd7)'/></td>" 
					+"<td  width='2.8%'><input id='tamd8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd8)'; onblur='guardarHC(tamd8)'/></td>" 
					+"<td  width='2.8%'><input id='tamd9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd9)'; onblur='guardarHC(tamd9)'/></td>" 
					+"<td  width='2.8%'><input id='tamd10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd10)'; onblur='guardarHC(tamd10)'/></td>" 
					+"<td  width='2.8%'><input id='tamd11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd11)'; onblur='guardarHC(tamd11)'/></td>" 
					+"<td  width='2.8%'><input id='tamd12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd12)'; onblur='guardarHC(tamd12)'/></td>"
					+"<td  width='2.8%'><input id='tamd13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd13)'; onblur='guardarHC(tamd13)'/></td>" 
					+"<td  width='2.8%'><input id='tamd14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd14)'; onblur='guardarHC(tamd14)'/></td>" 
					+"<td  width='2.8%'><input id='tamd15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd15)'; onblur='guardarHC(tamd15)'/></td>" 
					+"<td  width='2.8%'><input id='tamd16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd16)'; onblur='guardarHC(tamd16)'/></td>" 
					+"<td  width='2.8%'><input id='tamd17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd17)'; onblur='guardarHC(tamd17)'/></td>" 
					+"<td  width='2.8%'><input id='tamd18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd18)'; onblur='guardarHC(tamd18)'/></td>" 
					+"<td  width='2.8%'><input id='tamd19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd19)'; onblur='guardarHC(tamd19)'/></td>" 
					+"<td  width='2.8%'><input id='tamd20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd20)'; onblur='guardarHC(tamd20)'/></td>" 
					+"<td  width='2.8%'><input id='tamd21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd21)'; onblur='guardarHC(tamd21)'/></td>" 
					+"<td  width='2.8%'><input id='tamd22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd22)'; onblur='guardarHC(tamd22)'/></td>" 
					+"<td  width='2.8%'><input id='tamd23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd23)'; onblur='guardarHC(tamd23)'/></td>" 
					+"<td  width='2.8%'><input id='tamd00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd00)'; onblur='guardarHC(tamd00)'/></td>" 
					+"<td  width='2.8%'><input id='tamd01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd01)'; onblur='guardarHC(tamd01)'/></td>" 
					+"<td  width='2.8%'><input id='tamd02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd02)'; onblur='guardarHC(tamd02)'/></td>" 
					+"<td  width='2.8%'><input id='tamd03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd03)'; onblur='guardarHC(tamd03)'/></td>" 
					+"<td  width='2.8%'><input id='tamd04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd04)'; onblur='guardarHC(tamd04)'/></td>" 
					+"<td  width='2.8%'><input id='tamd05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd05)'; onblur='guardarHC(tamd05)'/></td>" 
					+"<td  width='2.8%'><input id='tamd06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tamd06)'; onblur='guardarHC(tamd06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					//"<tr bgcolor='#D8D8D8' id='ocrccd' style='display: none'> <td width='30%'><div id='rccd'>REACCI\u00D3N <br><a href='#' onclick='deshabilitarFilaSv(rccd)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocrccd' style='display: none'> <td width='30%'><div id='rccd'>REACCI\u00D3N<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(rccd)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='rccd7' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd7)'/></td>" 
					+"<td  width='2.8%'><input id='rccd8' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd8)'/></td>" 
					+"<td  width='2.8%'><input id='rccd9' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd9)'/></td>" 
					+"<td  width='2.8%'><input id='rccd10' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd10)'/></td>" 
					+"<td  width='2.8%'><input id='rccd11' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd11)'/></td>" 
					+"<td  width='2.8%'><input id='rccd12' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd12)'/></td>"
					+"<td  width='2.8%'><input id='rccd13' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd13)'/></td>" 
					+"<td  width='2.8%'><input id='rccd14' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd14)'/></td>" 
					+"<td  width='2.8%'><input id='rccd15' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd15)'/></td>" 
					+"<td  width='2.8%'><input id='rccd16' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd16)'/></td>" 
					+"<td  width='2.8%'><input id='rccd17' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd17)'/></td>" 
					+"<td  width='2.8%'><input id='rccd18' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd18)'/></td>" 
					+"<td  width='2.8%'><input id='rccd19' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd19)'/></td>" 
					+"<td  width='2.8%'><input id='rccd20' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd20)'/></td>" 
					+"<td  width='2.8%'><input id='rccd21' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd21)'/></td>" 
					+"<td  width='2.8%'><input id='rccd22' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd22)'/></td>" 
					+"<td  width='2.8%'><input id='rccd23' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd23)'/></td>" 
					+"<td  width='2.8%'><input id='rccd00' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd00)'/></td>" 
					+"<td  width='2.8%'><input id='rccd01' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd01)'/></td>" 
					+"<td  width='2.8%'><input id='rccd02' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd02)'/></td>" 
					+"<td  width='2.8%'><input id='rccd03' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd03)'/></td>" 
					+"<td  width='2.8%'><input id='rccd04' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd04)'/></td>" 
					+"<td  width='2.8%'><input id='rccd05' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd05)'/></td>" 
					+"<td  width='2.8%'><input id='rccd06' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rccd06)'/></td>"													
					+"</tr></table></td></tr>" +
					//I
                    "<tr id='ocdivi' style='display: none' bgcolor='#FA5858'> <td width='100%'><div id='salidas'style='text-align: center' ><CENTER>I</CENTER></div></td>" +
					
					"</tr>"+
					//"<tr id='octami' style='display: none'> <td width='30%'><div id='tami'>TAMA\u00D1O <br><a href='#' onclick='deshabilitarFilaSv(tami)'>Eliminar</a></div></td>" +
					"<tr id='octami' style='display: none'> <td width='30%'><div id='tami'>TAMA\u00D1O<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(tami)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='tami7' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami7)'; onblur='guardarHC(tami7)'/></td>" 
					+"<td  width='2.8%'><input id='tami8' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami8)'; onblur='guardarHC(tami8)'/></td>" 
					+"<td  width='2.8%'><input id='tami9' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami9)'; onblur='guardarHC(tami9)'/></td>" 
					+"<td  width='2.8%'><input id='tami10' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami10)'; onblur='guardarHC(tami10)'/></td>" 
					+"<td  width='2.8%'><input id='tami11' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami11)'; onblur='guardarHC(tami11)'/></td>" 
					+"<td  width='2.8%'><input id='tami12' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami12)'; onblur='guardarHC(tami12)'/></td>"
					+"<td  width='2.8%'><input id='tami13' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami13)'; onblur='guardarHC(tami13)'/></td>" 
					+"<td  width='2.8%'><input id='tami14' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami14)'; onblur='guardarHC(tami14)'/></td>" 
					+"<td  width='2.8%'><input id='tami15' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami15)'; onblur='guardarHC(tami15)'/></td>" 
					+"<td  width='2.8%'><input id='tami16' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami16)'; onblur='guardarHC(tami16)'/></td>" 
					+"<td  width='2.8%'><input id='tami17' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami17)'; onblur='guardarHC(tami17)'/></td>" 
					+"<td  width='2.8%'><input id='tami18' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami18)'; onblur='guardarHC(tami18)'/></td>" 
					+"<td  width='2.8%'><input id='tami19' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami19)'; onblur='guardarHC(tami19)'/></td>" 
					+"<td  width='2.8%'><input id='tami20' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami20)'; onblur='guardarHC(tami20)'/></td>" 
					+"<td  width='2.8%'><input id='tami21' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami21)'; onblur='guardarHC(tami21)'/></td>" 
					+"<td  width='2.8%'><input id='tami22' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami22)'; onblur='guardarHC(tami22)'/></td>" 
					+"<td  width='2.8%'><input id='tami23' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami23)'; onblur='guardarHC(tami23)'/></td>" 
					+"<td  width='2.8%'><input id='tami00' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami00)'; onblur='guardarHC(tami00)'/></td>" 
					+"<td  width='2.8%'><input id='tami01' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami01)'; onblur='guardarHC(tami01)'/></td>" 
					+"<td  width='2.8%'><input id='tami02' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami02)'; onblur='guardarHC(tami02)'/></td>" 
					+"<td  width='2.8%'><input id='tami03' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami03)'; onblur='guardarHC(tami03)'/></td>" 
					+"<td  width='2.8%'><input id='tami04' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami04)'; onblur='guardarHC(tami04)'/></td>" 
					+"<td  width='2.8%'><input id='tami05' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami05)'; onblur='guardarHC(tami05)'/></td>" 
					+"<td  width='2.8%'><input id='tami06' type='text' style='border: 1px solid #585858' size='1' onkeyup='soloNumerossv(tami06)'; onblur='guardarHC(tami06)'/></td>"													
					+"</tr></table></td></tr>" +
					
					//"<tr bgcolor='#D8D8D8' id='ocrcci' style='display: none'> <td width='30%'><div id='rcci'>REACCI\u00D3N <br><a href='#' onclick='deshabilitarFilaSv(rcci)'>Eliminar</a></div></td>" +
					"<tr bgcolor='#D8D8D8' id='ocrcci' style='display: none'> <td width='30%'><div id='rcci'>REACCI\u00D3N<img title='Eliminar' src='Imagenes/Eliminar.JPG' onclick='deshabilitarFilaSv(rcci)' style='cursor:pointer;' /></div></td>" +
					"<td width='70%'><table width='100%' border='0' ><tr>" +
					"<td width='2.8%'><input id='rcci7' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci7)'/></td>" 
					+"<td  width='2.8%'><input id='rcci8' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci8)'/></td>" 
					+"<td  width='2.8%'><input id='rcci9' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci9)'/></td>" 
					+"<td  width='2.8%'><input id='rcci10' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci10)'/></td>" 
					+"<td  width='2.8%'><input id='rcci11' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci11)'/></td>" 
					+"<td  width='2.8%'><input id='rcci12' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci12)'/></td>"
					+"<td  width='2.8%'><input id='rcci13' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci13)'/></td>" 
					+"<td  width='2.8%'><input id='rcci14' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci14)'/></td>" 
					+"<td  width='2.8%'><input id='rcci15' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci15)'/></td>" 
					+"<td  width='2.8%'><input id='rcci16' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci16)'/></td>" 
					+"<td  width='2.8%'><input id='rcci17' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci17)'/></td>" 
					+"<td  width='2.8%'><input id='rcci18' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci18)'/></td>" 
					+"<td  width='2.8%'><input id='rcci19' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci19)'/></td>" 
					+"<td  width='2.8%'><input id='rcci20' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci20)'/></td>" 
					+"<td  width='2.8%'><input id='rcci21' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci21)'/></td>" 
					+"<td  width='2.8%'><input id='rcci22' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci22)'/></td>" 
					+"<td  width='2.8%'><input id='rcci23' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci23)'/></td>" 
					+"<td  width='2.8%'><input id='rcci00' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci00)'/></td>" 
					+"<td  width='2.8%'><input id='rcci01' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci01)'/></td>" 
					+"<td  width='2.8%'><input id='rcci02' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci02)'/></td>" 
					+"<td  width='2.8%'><input id='rcci03' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci03)'/></td>" 
					+"<td  width='2.8%'><input id='rcci04' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci04)'/></td>" 
					+"<td  width='2.8%'><input id='rcci05' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci05)'/></td>" 
					+"<td  width='2.8%'><input id='rcci06' type='text' style='border: 1px solid #585858' size='1' onblur='guardarHC(rcci06)'/></td>"													
					+"</tr></table></td></tr>" +
					//"</div>"+
					"</table></div></td></tr>"+
				   "</table>");
					
					
					/*
					// es el dia actual se ponen para que puedan ser asignados
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"7");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>07 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>07 AM </td><td><input name='txtTalla' type='text' id='txtTalla7'></td><td><input name='txtPeso' type='text' id='txtPeso7'></td><td><input name='txtTA' type='text' id='txtTA7'></td><td><input name='txtFC' type='text' id='txtFC7'></td><td><input name='txtFR' type='text' id='txtFR7'></td><td><input name='txtPulso' type='text' id='txtPulso7'></td><td><a href='#' onclick='GuardarSV(7)'>Guardar</a></td></tr>");
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"8");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>08 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>08 AM </td><td><input name='txtTalla' type='text' id='txtTalla8'></td><td><input name='txtPeso' type='text' id='txtPeso8'></td><td><input name='txtTA' type='text' id='txtTA8'></td><td><input name='txtFC' type='text' id='txtFC8'></td><td><input name='txtFR' type='text' id='txtFR8'></td><td><input name='txtPulso' type='text' id='txtPulso8'></td><td><a href='#' onclick='GuardarSV(8)'>Guardar</a></td></tr>");
						}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"9");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>09 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>09 AM </td><td><input name='txtTalla' type='text' id='txtTalla9'></td><td><input name='txtPeso' type='text' id='txtPeso9'></td><td><input name='txtTA' type='text' id='txtTA9'></td><td><input name='txtFC' type='text' id='txtFC9'></td><td><input name='txtFR' type='text' id='txtFR9'></td><td><input name='txtPulso' type='text' id='txtPulso9'></td><td><a href='#' onclick='GuardarSV(9)'>Guardar</a></td></tr>");
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"10");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>10 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>10 AM </td><td><input name='txtTalla' type='text' id='txtTalla10'></td><td><input name='txtPeso' type='text' id='txtPeso10'></td><td><input name='txtTA' type='text' id='txtTA10'></td><td><input name='txtFC' type='text' id='txtFC10'></td><td><input name='txtFR' type='text' id='txtFR10'></td><td><input name='txtPulso' type='text' id='txtPulso10'></td><td><a href='#' onclick='GuardarSV(10)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"11");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>11 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>11 AM </td><td><input name='txtTalla' type='text' id='txtTalla11'></td><td><input name='txtPeso' type='text' id='txtPeso11'></td><td><input name='txtTA' type='text' id='txtTA11'></td><td><input name='txtFC' type='text' id='txtFC11'></td><td><input name='txtFR' type='text' id='txtFR11'></td><td><input name='txtPulso' type='text' id='txtPulso11'></td><td><a href='#' onclick='GuardarSV(11)'>Guardar</a></td></tr>");
					
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"12");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>12 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>12 PM </td><td><input name='txtTalla' type='text' id='txtTalla12'></td><td><input name='txtPeso' type='text' id='txtPeso12'></td><td><input name='txtTA' type='text' id='txtTA12'></td><td><input name='txtFC' type='text' id='txtFC12'></td><td><input name='txtFR' type='text' id='txtFR12'></td><td><input name='txtPulso' type='text' id='txtPulso12'></td><td><a href='#' onclick='GuardarSV(12)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"13");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>01 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>01 PM </td><td><input name='txtTalla' type='text' id='txtTalla13'></td><td><input name='txtPeso' type='text' id='txtPeso13'></td><td><input name='txtTA' type='text' id='txtTA13'></td><td><input name='txtFC' type='text' id='txtFC13'></td><td><input name='txtFR' type='text' id='txtFR13'></td><td><input name='txtPulso' type='text' id='txtPulso13'></td><td><a href='#' onclick='GuardarSV(13)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"14");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>02 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>02 PM </td><td><input name='txtTalla' type='text' id='txtTalla14'></td><td><input name='txtPeso' type='text' id='txtPeso14'></td><td><input name='txtTA' type='text' id='txtTA14'></td><td><input name='txtFC' type='text' id='txtFC14'></td><td><input name='txtFR' type='text' id='txtFR14'></td><td><input name='txtPulso' type='text' id='txtPulso14'></td><td><a href='#' onclick='GuardarSV(14)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"15");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>03 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>03 PM </td><td><input name='txtTalla' type='text' id='txtTalla15'></td><td><input name='txtPeso' type='text' id='txtPeso15'></td><td><input name='txtTA' type='text' id='txtTA15'></td><td><input name='txtFC' type='text' id='txtFC15'></td><td><input name='txtFR' type='text' id='txtFR15'></td><td><input name='txtPulso' type='text' id='txtPulso15'></td><td><a href='#' onclick='GuardarSV(15)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"16");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>04 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>04 PM </td><td><input name='txtTalla' type='text' id='txtTalla16'></td><td><input name='txtPeso' type='text' id='txtPeso16'></td><td><input name='txtTA' type='text' id='txtTA16'></td><td><input name='txtFC' type='text' id='txtFC16'></td><td><input name='txtFR' type='text' id='txtFR16'></td><td><input name='txtPulso' type='text' id='txtPulso16'></td><td><a href='#' onclick='GuardarSV(16)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"17");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>05 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>05 PM </td><td><input name='txtTalla' type='text' id='txtTalla17'></td><td><input name='txtPeso' type='text' id='txtPeso17'></td><td><input name='txtTA' type='text' id='txtTA17'></td><td><input name='txtFC' type='text' id='txtFC17'></td><td><input name='txtFR' type='text' id='txtFR17'></td><td><input name='txtPulso' type='text' id='txtPulso17'></td><td><a href='#' onclick='GuardarSV(17)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"18");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>06 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>06 PM </td><td><input name='txtTalla' type='text' id='txtTalla18'></td><td><input name='txtPeso' type='text' id='txtPeso18'></td><td><input name='txtTA' type='text' id='txtTA18'></td><td><input name='txtFC' type='text' id='txtFC18'></td><td><input name='txtFR' type='text' id='txtFR18'></td><td><input name='txtPulso' type='text' id='txtPulso18'></td><td><a href='#' onclick='GuardarSV(18)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"19");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>07 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>07 PM </td><td><input name='txtTalla' type='text' id='txtTalla19'></td><td><input name='txtPeso' type='text' id='txtPeso19'></td><td><input name='txtTA' type='text' id='txtTA19'></td><td><input name='txtFC' type='text' id='txtFC19'></td><td><input name='txtFR' type='text' id='txtFR19'></td><td><input name='txtPulso' type='text' id='txtPulso19'></td><td><a href='#' onclick='GuardarSV(19)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"20");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>08 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>08 PM </td><td><input name='txtTalla' type='text' id='txtTalla20'></td><td><input name='txtPeso' type='text' id='txtPeso20'></td><td><input name='txtTA' type='text' id='txtTA20'></td><td><input name='txtFC' type='text' id='txtFC20'></td><td><input name='txtFR' type='text' id='txtFR20'></td><td><input name='txtPulso' type='text' id='txtPulso20'></td><td><a href='#' onclick='GuardarSV(20)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"21");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>09 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}
					else{
						out.print("  <tr><td>09 PM </td><td><input name='txtTalla' type='text' id='txtTalla21'></td><td><input name='txtPeso' type='text' id='txtPeso21'></td><td><input name='txtTA' type='text' id='txtTA21'></td><td><input name='txtFC' type='text' id='txtFC21'></td><td><input name='txtFR' type='text' id='txtFR21'></td><td><input name='txtPulso' type='text' id='txtPulso21'></td><td><a href='#' onclick='GuardarSV(21)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"22");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>10 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>10 PM </td><td><input name='txtTalla' type='text' id='txtTalla22'></td><td><input name='txtPeso' type='text' id='txtPeso22'></td><td><input name='txtTA' type='text' id='txtTA22'></td><td><input name='txtFC' type='text' id='txtFC22'></td><td><input name='txtFR' type='text' id='txtFR22'></td><td><input name='txtPulso' type='text' id='txtPulso22'></td><td><a href='#' onclick='GuardarSV(22)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"23");
					if(rs1.next()){
						out.print("  <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>11 PM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("  <tr><td>11 PM </td><td><input name='txtTalla' type='text' id='txtTalla23'></td><td><input name='txtPeso' type='text' id='txtPeso23'></td><td><input name='txtTA' type='text' id='txtTA23'></td><td><input name='txtFC' type='text' id='txtFC23'></td><td><input name='txtFR' type='text' id='txtFR23'></td><td><input name='txtPulso' type='text' id='txtPulso23'></td><td><a href='#' onclick='GuardarSV(23)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"0");
					if(rs1.next()){
						out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>12 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("	 <tr><td>12 AM </td><td><input name='txtTalla' type='text' id='txtTalla0'></td><td><input name='txtPeso' type='text' id='txtPeso0'></td><td><input name='txtTA' type='text' id='txtTA0'></td><td><input name='txtFC' type='text' id='txtFC0'></td><td><input name='txtFR' type='text' id='txtFR0'></td><td><input name='txtPulso' type='text' id='txtPulso0'></td><td><a href='#' onclick='GuardarSV(0)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"1");
					if(rs1.next()){
						out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>01 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("	 <tr><td>01 AM </td><td><input name='txtTalla' type='text' id='txtTalla1'></td><td><input name='txtPeso' type='text' id='txtPeso1'></td><td><input name='txtTA' type='text' id='txtTA1'></td><td><input name='txtFC' type='text' id='txtFC1'></td><td><input name='txtFR' type='text' id='txtFR1'></td><td><input name='txtPulso' type='text' id='txtPulso1'></td><td><a href='#' onclick='GuardarSV(1)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"2");
					if(rs1.next()){
						out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>02 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("	 <tr><td>02 AM </td><td><input name='txtTalla' type='text' id='txtTalla2'></td><td><input name='txtPeso' type='text' id='txtPeso2'></td><td><input name='txtTA' type='text' id='txtTA2'></td><td><input name='txtFC' type='text' id='txtFC2'></td><td><input name='txtFR' type='text' id='txtFR2'></td><td><input name='txtPulso' type='text' id='txtPulso2'></td><td><a href='#' onclick='GuardarSV(2)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"3");
					if(rs1.next()){
						out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>03 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("	 <tr><td>03 AM </td><td><input name='txtTalla' type='text' id='txtTalla3'></td><td><input name='txtPeso' type='text' id='txtPeso3'></td><td><input name='txtTA' type='text' id='txtTA3'></td><td><input name='txtFC' type='text' id='txtFC3'></td><td><input name='txtFR' type='text' id='txtFR3'></td><td><input name='txtPulso' type='text' id='txtPulso3'></td><td><a href='#' onclick='GuardarSV(3)'>Guardar</a></td></tr>");
						
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"4");
					if(rs1.next()){
						out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>04 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("	 <tr><td>04 AM </td><td><input name='txtTalla' type='text' id='txtTalla4'></td><td><input name='txtPeso' type='text' id='txtPeso4'></td><td><input name='txtTA' type='text' id='txtTA4'></td><td><input name='txtFC' type='text' id='txtFC4'></td><td><input name='txtFR' type='text' id='txtFR4'></td><td><input name='txtPulso' type='text' id='txtPulso4'></td><td><a href='#' onclick='GuardarSV(4)'>Guardar</a></td></tr>");
					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"5");
					if(rs1.next()){
						out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>05 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("	 <tr><td>05 AM </td><td><input name='txtTalla' type='text' id='txtTalla5'></td><td><input name='txtPeso' type='text' id='txtPeso5'></td><td><input name='txtTA' type='text' id='txtTA5'></td><td><input name='txtFC' type='text' id='txtFC5'></td><td><input name='txtFR' type='text' id='txtFR5'></td><td><input name='txtPulso' type='text' id='txtPulso5'></td><td><a href='#' onclick='GuardarSV(5)'>Guardar</a></td></tr>");

					}
					rs1.getStatement().getConnection().close();
					rs1=mvf.ConsultarValoresSV(fechaInsertSV+"", CodAdmSV,"6");
					if(rs1.next()){
						out.print("	 <tr title='"+rs1.getString("UsuarioInserta")+"' ><td>06 AM </td><td>"+rs1.getString("talla")+"</td><td>"+rs1.getString("peso")+"</td><td>"+rs1.getString("ta")+"</td><td>"+rs1.getString("fc")+"</td><td>"+rs1.getString("fr")+"</td><td>"+rs1.getString("pulso")+"</td><td>-</td></tr>");
					}else{
						out.print("	 <tr><td>06 AM </td><td><input name='txtTalla' type='text' id='txtTalla6'></td><td><input name='txtPeso' type='text' id='txtPeso6'></td><td><input name='txtTA' type='text' id='txtTA6'></td><td><input name='txtFC' type='text' id='txtFC6'></td><td><input name='txtFR' type='text' id='txtFR6'></td><td><input name='txtPulso' type='text' id='txtPulso6'></td><td><a href='#' onclick='GuardarSV(6)'>Guardar</a></td></tr>");
					}
					rs1.getStatement().getConnection().close();*/
				}
				rs.getStatement().getConnection().close();
			//	}
				
			}
			
		//}
		out.print("	</table>");
		//rs1.getStatement().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}
		
		
		if(va.equals("GURSV")){
			String  usuario=req.getParameter("usuario");
			String 	CodAdmSV=req.getParameter("CodAdm");
			String 	txtTalla=req.getParameter("txtTalla");
			String 	txtPeso=req.getParameter("txtPeso");
			String 	txtTA=req.getParameter("txtTA");
			String 	txtFC=req.getParameter("txtFC");
			String 	txtFR=req.getParameter("txtFR");
			String 	txtPulso=req.getParameter("txtPulso");
			String 	Hora=req.getParameter("Hora");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date fechaInsertSV = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time horaInsertSV = new java.sql.Time(fechaActual.getTime());	

			mvf.InsertarSignosVitales(CodAdmSV, txtPeso, txtTalla, txtTA, txtFC, txtFR, txtPulso, fechaInsertSV+"", Hora, fechaInsertSV+"", horaInsertSV+"", usuario);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
	}
	/**************************************************************************************************/

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
	            parte_decimal =  "  ";
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
	/**************************************************************************************************/
}
