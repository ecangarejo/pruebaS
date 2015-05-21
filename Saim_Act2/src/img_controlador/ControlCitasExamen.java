/**
 * controlador: ControlCitasExamen se encuentra el proceso para la 
 * creacion de la cita, su asignacion de estudio,ver los aprobados,los
 * estudios pendientes e ingresar los resultados.
*/
package img_controlador;

import fact_metodo.MetodoSeguimientoAdmision;
import img_logica.MetodoAprobarImg;
import img_logica.MetodoAuxClinico;
import img_logica.MetodoCitasExamen;
//import img_logica.MetodoCrearGrupo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAdmision;

/**
 * Servlet implementation class ControlCitasExamen
 */
public class ControlCitasExamen extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoCitasExamen mce = new MetodoCitasExamen();
		ResultSet rs=null;
		res.setContentType("text/html;charset=UTF-8");
		String z=req.getParameter("z");
		if(z.equals("2")){
	           String nombre1 =req.getParameter("nombre");
      
         try {
             rs = mce.listar(nombre1);
             String cadena ="";
             cadena="[";
             while(rs.next()){
             	cadena = cadena+"'"+rs.getString(1)+"|"+rs.getString(2)+"'";
             	cadena = cadena +",";	
             }
             
             cadena = cadena+"]";            
             res.getWriter().write(cadena);
             rs.getStatement().getConnection().close();
         }  catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		String codGrupo = req.getParameter("codGrupo");
		String cedula=req.getParameter("cedula");
		String codigoPac_fk=req.getParameter("codigoPac_fk");
		String codigoExa_fk=req.getParameter("codigoExa_fk");
		String estado=req.getParameter("estado");
		String nombre="";
		String apellido="";
		String completo="";
		String cc="";
		String datosClinico=req.getParameter("datosClinico");
		String usuario=req.getParameter("usuario");
		String resultado=req.getParameter("Resultado");
		String tipodoc=req.getParameter("tipodoc");
		String codCita=req.getParameter("codExaCita");
		String portatil=req.getParameter("portatil");
		String hora=req.getParameter("hora");
		String todo=req.getParameter("todo");
		String fecha=req.getParameter("fecha");
		String codIce=req.getParameter("codIce");
		String CodAdm=req.getParameter("CodAdm");
		MetodoCitasExamen mce = new MetodoCitasExamen();
		MetodoSeguimientoAdmision msa =new MetodoSeguimientoAdmision();
		MetodoAuxClinico tras = new MetodoAuxClinico();
		MetodoAprobarImg mai=new MetodoAprobarImg ();
		String diagnostico=req.getParameter("descripcion");
		String descripcions=req.getParameter("descripcion");
		String Diagnostico=req.getParameter("Diagnostico");
		String CodExamen=req.getParameter("CodExamen");
		
		if(va.equals("0")){
			if(todo.equals("0")){
				
				
				out.println("<table width='100%'><tr style='font-size:9px'><td colspan='5' align='center' id='cabecera2'><div align='center' class='style11'>EXAMENES REALIZADOS</span></td></tr><tr><td>&nbsp;</td></tr></table>   <div class='scrollbox5'><table width='100%' class='style6' border='1' bordercolor='#1C777B' ><tr align='center' id='cabecera2' class='style11'><td width='147'>Fecha</td><td width='213'>Nombre Del Examen</td><td width='77'>Cedula</td><td width='164'>Nombre Del Paciente </td><td width='99'>Aprobado Por </td></tr>");		
			try {
				try {
					rs=mai.ObtenerAprobadosLimite();
				} catch (Exception e) {
					e.printStackTrace();
				}
				while(rs.next()){
					
					out.println("<tr align='center'><td>"+rs.getString(8)+"/"+rs.getString(9)+"</td><td><a  href='#'onclick='mostarexamenes("+rs.getString(6)+","+rs.getString(12)+")'>"+rs.getString(5)+"</a></td><td>"+rs.getString(10)+"</td><td>"+rs.getString(1)+" "+rs.getString(2)+"</td><td>"+rs.getString(11)+"</td><td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					
				}
				out.println("</table></div>");
				out.println("<div align='center'><input name='btnmostrartodo' class='boton4' type='button' id='btnmostrartodo' value='Mostar Todo' onclick='MostarTodo()' /></div>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va=1 "+e);
				e.printStackTrace();
				} 
			
			}
			if(todo.equals("1")){
				rs1=mai.ObtenerAprobados();
				out.println("<table width='100%'><tr><td colspan='5' align='center' id='cabecera2'><div align='center' class='style11'>EXAMENES REALIZADOS</span></td></tr><tr><td>&nbsp;</td></tr></table>  <div class='scrollbox5'><table width='100%' border='1' class='style6' bordercolor='#1C777B'><tr id='cabecera2' class='style11' align='center'><td width='147'>Fecha</td><td width='213'>Nombre Del Examen</td><td width='77'>Cedula</td><td width='164'>Nombre Del Paciente </td><td width='99'>Aprobado Por </td></tr>");		
				try {
					while(rs1.next()){						
					out.println("<tr align='center'><td>"+rs1.getString(8)+"/"+rs1.getString(9)+"</td><td><a  href='#'onclick='mostarexamenes("+rs1.getString(6)+","+rs1.getString(12)+")'>"+rs1.getString(5)+"</a></td><td>"+rs1.getString(10)+"</td><td>"+rs1.getString(1)+" "+rs1.getString(2)+"</td><td>"+rs1.getString(11)+"</td><td><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					}
					out.println("</table></div>");
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					System.out.println("Error en Controlador va=1 "+e);
					e.printStackTrace();
					}
				
				
			}
		}
		
		
		if(va.equals("1")){
			rs=mai.ObtenerExamenesParaAprobacion();
			out.println("<table width='100%'><tr id='cabecera2'><td colspan='6'><div align='center' class='style11'>EXAMENES POR APROBAR</div></td></tr> <tr><td>&nbsp;</td></tr></table>  <div class='scrollbox5'><table width='100%' border='1' bordercolor='#1C777B' class='style6' ><tr align='center' id='cabecera2' class='style11'><td width='147'>Fecha</td><td width='166'>Nombre Del Examen</td><td width='77'>Cedula</td><td width='144'>Nombre Del Paciente </td><td width='87'>Asignado Por </td><td width='95'>Realizado Por</td></tr>");		
			try {
				while(rs.next()){
					
					out.println("<tr align='center'><td>"+rs.getString(8)+"/"+rs.getString(9)+"</td><td><a  href='#'onclick='mostarexamenes("+rs.getString(6)+","+rs.getString(12)+")'>"+rs.getString(5)+"</a></td><td>"+rs.getString(10)+"</td><td>"+rs.getString(1)+" "+rs.getString(2)+"</td><td>"+rs.getString(13)+"</td><td>"+rs.getString(11)+"</td><td width='0'><label><input name='txtcodIce' type='hidden' id='txtcodIce' /></label></td></tr>");
					
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va=1 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2")){
			int contador=0;
			rs=mce.ObtenerSuArea(codGrupo);
			out.println("<div class='scrollbox1'><table width='100%' border='1' class='style6' bordercolor='#1C777B' cellspacing='0'><tr  id='cabecera2' class='style11' align='center'><td>NOMBRE DEL EXAMEN </td><td align='center'>SELECCIONE<label><input type='checkbox'  name='all' id='all' onclick='checkAll();'  /></label></td></tr>");
			try{
				while(rs.next()){
					out.println("<tr style='font-size:9px'><td width='599'>"+rs.getString(2)+"</td><td width='154' align='center'><label><input type='checkbox' name='chkvalidar' id='ca' value='"+rs.getString(1)+"' />  </label> <input name='patron' id='patron' type='hidden' value="+rs.getString(3)+"></td></tr>");
					contador=contador+1;	
				}
				out.println("</div></table><table width='100%'><tr><td colspan='2' align='right'><label><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' value='Asignar' onclick='Asignar()'></label><label><input class='boton4' name='btnFinalizar' type='button' id='btnFinalizar' value='Finalizar' onclick='finalizar()'></label></td></tr></table><input name='yo' id='yo' type='hidden' value="+contador+">");
				rs.getStatement().getConnection().close();
			}    
			catch (SQLException e) {
				System.out.println("Error en Controlador va=2 "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("3")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			
			String observacion=req.getParameter("observacion");
			String CodOrdenImg="";
			String TipoOrden="2";
			String nomusu="";
			String tipo="4";
			rs1=mce.Obtenerusuario(usuario);
			try{
				if(rs1.next()){
					nomusu=rs1.getString(1);		
				}
				rs1.getStatement().getConnection().close();
				msa.CrearOrdenEstudios(codigoPac_fk, CodAdm, usuario, observacion, Hora, Fecha, TipoOrden);
				rs2=msa.ObtenerCodigoOrden(Hora, Fecha);
				if(rs2.next()){
					CodOrdenImg=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
				msa.CrearDetalleOrdenImg(CodOrdenImg, codigoExa_fk, datosClinico);
				mce.AsignarCitas(codigoPac_fk, codigoExa_fk, estado,nomusu,Hora,Fecha,datosClinico,portatil,cedula);
				
				if (portatil.equals("(PORTATIL)")){
					System.out.println("es portatil ");
				
				}else{
					System.out.println("NO es portatil ");
					tras.insertarTraslado(codigoPac_fk,codigoExa_fk,"0",Hora,Fecha);
				}
				
				
				String CodAdmAmbulatorio="";
				//* 1. verificar si el paciente tiene admision aciva = OK
				rs4=mce.AdmisionActiva(codigoPac_fk);
				if(rs4.next()){
				}else{
					 //* 2. verificar si tiene una estancia de ambulatorio activa=OK 
					rs5=mce.AdmisionAmbulatoriaActiva(codigoPac_fk);
					if(rs5.next()){
					}else{
						//* 3. ingresar en la tabla adm_ambulatorio 
						mce.AsignarAdmisionAmbulatoria(usuario, Fecha, Hora, codigoPac_fk, tipo);
						
						
						//* 4. se ingresa el encabezado de la consulta ambulatoria a fact_encabezado
						rs6=mce.ObtenerCodigoAmbulatorio(Fecha, Hora);
						if(rs6.next()){
							CodAdmAmbulatorio=rs6.getString(1);
							rs7=mce.ConsultaEncabezado(codigoPac_fk);
							if(rs7.next()){
								MetodoAdmision ba=new MetodoAdmision();
								
								String cod_eps=rs7.getString(1);
								String razon_social=rs7.getString(2);
								String nit=rs7.getString(3); 
								String direccion=rs7.getString(4);
								String telefono=rs7.getString(5);
								String ciudad=rs7.getString(6);
								String nombre_paciente=rs7.getString(7);
								String documentoP=rs7.getString(8);
								String direccion_p=rs7.getString(9);
								String telefono_p=rs7.getString(10);
								String tipo_afiliacion=rs7.getString(11);
								String estrato=rs7.getString(12);
								String fecha_ingreso=rs7.getString(13);
								String num_autorizacion=rs7.getString(14);
								String poliza=rs7.getString(15);
								
								ba.InserEmcabe(cod_eps, razon_social, nit, direccion, telefono, ciudad, nombre_paciente, documentoP, direccion_p, telefono_p, tipo_afiliacion, estrato, fecha_ingreso, CodAdmAmbulatorio, num_autorizacion,poliza,"3");
						
							}
							rs7.getStatement().getConnection().close();
						}
						rs6.getStatement().getConnection().close();
					}
					rs5.getStatement().getConnection().close();
				}
				rs4.getStatement().getConnection().close();
				/*****************************************************************/
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenImg' type='hidden' id='txtCodOrdenImg' value="+CodOrdenImg+" /></div></td></tr>");

				out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
				rs3=msa.ObtenerDetalleOrdenImg(CodOrdenImg);
				while(rs3.next()){
					out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenImg("+rs3.getString(1)+")' >Omitir</a></td></tr>");
				}
				rs3.getStatement().getConnection().close();
				out.print("</table>");
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=3 "+e);
				e.printStackTrace();
			}
			//msa.CrearRelacionImagenologiaAdmision(codigoExa_fk, CodAdm, codigoPac_fk, usuario, hora, fecha);
		}
		
		
		if(va.equals("p3")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			
			String observacion=req.getParameter("observacion");
			String CodOrdenPro="";
			//String TipoOrden="2";
			//String nomusu="";
			String tipo="5";
			try{
				
				msa.CrearOrdenEstudios(codigoPac_fk, CodAdm, usuario, observacion, Hora, Fecha, tipo);
				rs2=msa.ObtenerCodigoOrden(Hora, Fecha);
				if(rs2.next()){
					CodOrdenPro=rs2.getString(1);
				}
				rs2.getStatement().getConnection().close();
				msa.CrearDetalleOrdenImg(CodOrdenPro, codigoExa_fk, datosClinico);
				/*****************************************************************/
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Procedimiento<input name='txtCodOrdenPro' type='hidden' id='txtCodOrdenPro' value="+CodOrdenPro+" /></div></td></tr>");

				out.print("<tr><td width='57%'><div align='center'>Nombre Procedimiento</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
				rs3=msa.ObtenerDetalleOrdenProcedi(CodOrdenPro);
				while(rs3.next()){
					out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenImg("+rs3.getString(1)+")' >Omitir</a></td></tr>");
				}
				rs3.getStatement().getConnection().close();
				out.print("</table>");
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=p3 "+e);
				e.printStackTrace();
			}
			//msa.CrearRelacionImagenologiaAdmision(codigoExa_fk, CodAdm, codigoPac_fk, usuario, hora, fecha);
		}

		
		
		if(va.equals("p33")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			
			String CodOrdenPro=req.getParameter("CodOrdenPro");
			String nomusu="";
			try{
				rs2=msa.ObtenerDetalleOrdenImg(CodOrdenPro, codigoExa_fk);
				if(rs2.next()){
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenPro' type='hidden' id='txtCodOrdenPro' value="+CodOrdenPro+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenProcedi(CodOrdenPro);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenImg("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("</table>");
				}else{
					rs1=mce.Obtenerusuario(usuario);
					while(rs1.next()){
						nomusu=rs1.getString(1);		
					}
					rs1.getStatement().getConnection().close();
					msa.CrearDetalleOrdenImg(CodOrdenPro, codigoExa_fk, datosClinico);
					//mce.AsignarCitas(codigoPac_fk, codigoExa_fk, estado,nomusu,Hora,Fecha,datosClinico,portatil,cedula);
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Procedimientos<input name='txtCodOrdenPro' type='hidden' id='txtCodOrdenPro' value="+CodOrdenPro+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenProcedi(CodOrdenPro);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenPro("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("</table>");
				}
				rs2.getStatement().getConnection().close();
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=p33 "+e);
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("33")){
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
			
			String CodOrdenImg=req.getParameter("CodOrdenImg");
			String nomusu="";
			try{
				rs2=msa.ObtenerDetalleOrdenImg(CodOrdenImg, codigoExa_fk);
				if(rs2.next()){
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenImg' type='hidden' id='txtCodOrdenImg' value="+CodOrdenImg+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenImg(CodOrdenImg);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenImg("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("</table>");
				}else{
					rs1=mce.Obtenerusuario(usuario);
					while(rs1.next()){
						nomusu=rs1.getString(1);		
					}
					rs1.getStatement().getConnection().close();
					msa.CrearDetalleOrdenImg(CodOrdenImg, codigoExa_fk, datosClinico);
					mce.AsignarCitas(codigoPac_fk, codigoExa_fk, estado,nomusu,Hora,Fecha,datosClinico,portatil,cedula);
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenImg' type='hidden' id='txtCodOrdenImg' value="+CodOrdenImg+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenImg(CodOrdenImg);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenImg("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("</table>");
				}
				rs2.getStatement().getConnection().close();
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=3 "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("p334")){
			String CodDetOrdPro=req.getParameter("CodDetOrdPro");
			String CodOrdenPro=req.getParameter("CodOrdenPro");			
			try{
				msa.OmitirDetOrdImg(CodDetOrdPro);
				rs=msa.ObtenerDetalleOrdenProcedi(CodOrdenPro);
				if(rs.next()){					
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenPro' type='hidden' id='txtCodOrdenPro' value="+CodOrdenPro+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenProcedi(CodOrdenPro);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenPro("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("</table>");
				}else{
					msa.OmitirOrdenVacia(CodOrdenPro);
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenPro' type='hidden' id='txtCodOrdenPro' /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					out.print("<tr><td></td><td></td><td></td></tr>");
					out.print("</table>");
				}
				rs.getStatement().getConnection().close();
				
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=p334 "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("334")){
			String CodDetOrdImg=req.getParameter("CodDetOrdImg");
			String CodOrdenImg=req.getParameter("CodOrdenImg");			
			try{
				msa.OmitirDetOrdImg(CodDetOrdImg);
				rs=msa.ObtenerDetalleOrdenImg(CodOrdenImg);
				if(rs.next()){					
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenImg' type='hidden' id='txtCodOrdenImg' value="+CodOrdenImg+" /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					rs3=msa.ObtenerDetalleOrdenImg(CodOrdenImg);
					while(rs3.next()){
						out.print("<tr><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+"</td><td><a href='#' onclick='OmitirDetOrdenImg("+rs3.getString(1)+")' >Omitir</a></td></tr>");
					}
					rs3.getStatement().getConnection().close();
					out.print("</table>");
				}else{
					msa.OmitirOrdenVacia(CodOrdenImg);
					out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Detalle Orden Imagenologia<input name='txtCodOrdenImg' type='hidden' id='txtCodOrdenImg' /></div></td></tr>");
					out.print("<tr><td width='57%'><div align='center'>Nombre Estudio</div></td><td width='21%'><div align='center'>Observacion</div></td><td width='22%'><div align='center'>Accion</div></td></tr>");
					out.print("<tr><td></td><td></td><td></td></tr>");
					out.print("</table>");
				}
				rs.getStatement().getConnection().close();
				
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=3 "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("4")){

			int contador=0;
			rs=mce.ObtenerExamenesAsignadosNombre(cedula,tipodoc);
			rs1=mce.ObtenerExamenesAsignadosNombre(cedula,tipodoc);
			try{
				while(rs1.next()){
					nombre=rs1.getString(3);
					apellido=rs1.getString(4);

					cc=rs1.getString(5);
					
				}
				completo=nombre+" "+apellido;
				rs1.getStatement().getConnection().close();
			}
			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
			if(cc.equals("")){
				out.print("No Se Encontro Registro.");
			}
			else{
			out.println("<table width='100%' class='style6'><tr ><td colspan='5'  align='center' id='cabecera2'><div align='center' class='style11'>EXAMENES POR REALIZAR</span></td></tr><tr><td></td>&nbsp;</tr><tr><td><strong>Nombre Del Paciente: </strong><b class='style9'>"+completo+"</b></td>" +
					"<td><strong>Cedula:</strong>"+ cc+"</td></tr></table><br>" +
					"<div class='scrollbox3'><table width='100%' class='style6' border='1' bordercolor='#1C777B' ><tr id='cabecera2' class='style11' align='center'><td><strong>Fecha de la Cita </strong></td><td><strong>Examenes Pendientes </strong></td><td width='117'><strong>Entidad</strong></td><td width='97'><strong>Asignado Por </strong></td><td width='89'><strong>Anular</strong></td></tr>");
			try{
				
				while(rs.next()){
					//String result=rs.getString(7);
					out.println("<tr align='center'><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td><a href='#' onclick='mostarexamenes("+rs.getString(11)+","+rs.getString(8)+")'>"+rs.getString(7)+"</a></td><td>"+rs.getString(9)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='omitir("+rs.getString(8)+")'><strong><em>Omitir</em></strong></a></td></tr>");
					contador=contador+1;
				}
				//out.println("<input name='yo' id='yo' type='hidden' value="+contador+">");
				//out.println("<tr><td colspan='2' align='center'><label><input name='btnIngresar' type='button' id='btnIngresar' value='Ingresar' onclick='finalizar()'></label></td></tr></table>");
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
			}
		}
		
		if(va.equals("5")){
			String nomusu="";
			rs1=mce.Obtenerusuario(usuario);
			if(Diagnostico==""){
				Diagnostico="LO DESCRITO";
				
			}
			try{
				while(rs1.next()){
					nomusu=rs1.getString(1);		
				}
				rs1.getStatement().getConnection().close();
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va=5 "+e);
				e.printStackTrace();
			}			
			mce.ActualizarCitas(resultado, codCita, hora, fecha,nomusu,Diagnostico);
		}
		
		if(va.equals("6")){
			rs=mai.VerExamenParaAprobar(codIce);
			String nombres="";
			String apellidos="";
			String NomCompleto="";
			String fechas="";
			String horas="";
			String FecHorCompleta="";
			String cedulas="";
			String sexo="";
			String edad="";
			String fechaNaci="";
			String descripcion="";
			String NomExamen="";
			String datoClinico="";
			String Diagnosticos="";
			try {
				if(rs.next()){
					nombres=rs.getString(1);
					apellidos=rs.getString(2);
					NomCompleto=nombres+" "+apellidos;
					//
					fechas=rs.getString(5);
					horas=rs.getString(6);
					FecHorCompleta=fechas+" "+horas;
					//
					cedulas=rs.getString(7);
					//
					sexo=rs.getString(8);
					edad=rs.getString(9);
					fechaNaci=rs.getString(10);
					descripcion=rs.getString(4);
					NomExamen=rs.getString(3);
					datoClinico=rs.getString(12);
					Diagnosticos=rs.getString(13);
					out.println(NomCompleto+"_"+FecHorCompleta+"_"+cedulas+"_"+sexo+"_"+edad+"_"+fechaNaci+"_"+descripcion+"_"+NomExamen+"_"+datoClinico+"_"+Diagnosticos);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador va= 6 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("7")){
			String nomusu="";
			rs1=mce.Obtenerusuario(usuario);
			try{
				while(rs1.next()){
					nomusu=rs1.getString(1);		
				}
				rs1.getStatement().getConnection().close();
			}			
			catch (SQLException e) {
				System.out.println("Error en Controlador va= 7 "+e);
				e.printStackTrace();
			}
			mai.AprobarExaImg(codIce, diagnostico,nomusu,Diagnostico);
		}
		
		if(va.equals("8")){
			mai.ActualizarDiagnostico(Diagnostico, codIce,descripcions);
		}
		
		if(va.equals("9")){
			int contador=0;
			rs=mce.ObtenerExamenesAsignadosNombreTecnico();
			
			out.println("<table width='100%' ><tr style='font-size:9px'><td colspan='6' align='center' id='cabecera2' ><div align='center' class='style11'>EXAMENES POR REALIZAR</span></td></tr></table>   <div class='scrollbox5'><table class='style6' width='100%' border='1' bordercolor='#1C777B' ><tr id='cabecera2' class='style11' align='center'><td width='147' ><strong>Fecha de la Cita </strong></td><td width='157'><strong>Examenes Pendientes </strong></td><td width='157'><strong>Paciente</strong></td><td width='94'><strong>Entidad</strong></td><td width='97'><strong>Asignado Por </strong></td><td width='89'><strong>Revisado Por</strong></td><td width='89'><strong>Anular</strong></td></tr>");
			try{				 
				while(rs.next()){
					String nom=rs.getString(3);
					String ape=rs.getString(4);
					String Nombre=nom+" "+ape;
					out.println("<tr align='center'><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td>"+rs.getString(7)+"</td><td>"+Nombre+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='Revisado("+rs.getString(8)+")'><strong><em>Revisado</em></strong></a></td><td><a href='#' onclick='omitir("+rs.getString(8)+")'><strong><em>Omitir</em></strong></a></td></tr>");
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("10")){
			//String datosClinicos="";
			int contador=0;
			rs=mce.ObtenerInsercionPaciente(cedula);		
			try{
				
				while(rs.next()){
					out.print("<table width='763' border='1' bordercolor='#1C777B' cellspacing='0' ><tr style='font-size:9px'>  <td colspan='4' align='center' bgcolor='#01426E' class='style1'><span>PACIENTE  ENCONTRADO </span></td></tr><tr><td width='150'><strong>Nombre Del Paciente</strong></td><td width='288'>"+rs.getString(1)+" "+rs.getString(2)+"</td><td><strong>N&ordm; Documento </strong></td><td>"+rs.getString(4)+"</td></tr><tr><td><strong>Genero</strong></td><td>"+rs.getString(3)+"</td><td width='117'><strong>Entidad</strong></td><td>"+rs.getString(5)+"</td></tr>");
					out.print("<tr style='font-size:9px'><td colspan='4' align='center' bgcolor='#01426E' class='style1'>RESPONSABLE</td></tr><tr><td><strong>Ingresado Por </strong></td><td>"+rs.getString(6)+" "+rs.getString(7)+"</td><td><strong>N&ordm; Documento</strong></td><td>"+rs.getString(8)+"</td></tr><tr><td><strong>Fecha y Hora </strong></td><td colspan='3'>"+rs.getString(9)+" - "+rs.getString(10)+"</td></tr></table>");
					contador=contador+1;
				}
				
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("11")){
			int contador=0;
			rs=mce.ObtenerExamenesAsignadosPorEstudio(CodExamen);			
			out.println("<table width='100%'><tr><td colspan='6' align='center' id='cabecera2'><div align='center' class='style11'>EXAMENES POR REALIZAR</span></td></tr><tr><td>&nbsp;</tr></td></table> <div class='scrollbox1'><table width='100%' border='1' bordercolor='#1C777B' class='style6' ><tr id='cabecera2' align='center' class='style11'><td width='130'><strong>Fecha de la Cita </strong></td><td width='148'><strong>Examenes Pendientes </strong></td><td width='155'><strong>Nombre Paciente  </strong></td><td width='129'><strong>Entidad</strong></td><td width='97'><strong>Asignado Por </strong></td><td width='61'><strong>Anular</strong></td></tr>");
			try{
				
				while(rs.next()){
					out.println("<tr align='center'><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td><a href='#' onclick='mostarexamenes("+rs.getString(5)+","+rs.getString(8)+")'>"+rs.getString(7)+"</a></td><td>"+rs.getString(3)+" "+rs.getString(4)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(6)+"</td><td><a href='#' onclick='omitir("+rs.getString(8)+")'><strong><em>Omitir</em></strong></a></td></tr>");
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en Controlador va=4 "+e);
				e.printStackTrace();
			}
		}
	
		
	}
}