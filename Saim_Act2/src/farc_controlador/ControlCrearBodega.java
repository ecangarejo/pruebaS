package farc_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.classfile.Code;

import farc_metodo.MetodoCrearBodega;


public class ControlCrearBodega extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String va = request.getParameter("valor");
		PrintWriter out=response.getWriter();
		MetodoCrearBodega mcb = new MetodoCrearBodega();
		
		String nombreBodega=request.getParameter("nombreBodega");
		String descripcion=request.getParameter("descripcion");
		String nombreEstante=request.getParameter("nombreEstante");
		String observacion=request.getParameter("observacion");	
		String cBodega=request.getParameter("cmbBodega");	
		if(cBodega==null){cBodega="ccc";}
		String cEstante=request.getParameter("cmbEstante");	
		String code=request.getParameter("code");
		String boe=request.getParameter("boe");
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		
	 	if(va.equals("Ingresar")){			
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Crear Bodega</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre de la Bodega</td><td><label><input name='txtNombreBodega' type='text' id='txtNombreBodega' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td>Descripcion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='38' rows='3'></textarea></label></td></tr>");
			out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearBodega' id='btnCrearBodega' value='Crear' onClick='CrearBodega()'></label></div></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Bodegas Creadas</div></td></tr>");

			String codigo1=null;
			rs1=mcb.ObtenerBodegas();
								
			try {
				while(rs1.next()){
					codigo1=rs1.getString(2);
					out.print("<tr><td width='12%'>"+codigo1+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		
		if(va.equals("Crear")){
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Crear Estantes</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre del estante</td><td><label><input name='txtNombreEstante' type='text' id='txtNombreEstante' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
			out.print("<tr><td width='12%'>Observacion</td><td><label><input name='txtObservacion' type='text' id='txtObservacion' size='48' /></label></td></tr>");
			out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearEstante' id='btnCrearEstante' value='Crear' onClick='CrearEstante()'></label></td></tr>");		
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Estantes Creados</div></td></tr>");

			String codigo1=null;
			rs1=mcb.ObtenerEstante();
								
			try {
				while(rs1.next()){
					codigo1=rs1.getString(2);
					out.print("<tr><td width='12%'>"+codigo1+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
		}
		
		if(va.equals("Actualizar")){
			
			//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Asignar o Eliminar Estante</div></td></tr>");
			out.print("<table width='100%' class='style6' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Asignar Estantes</div></td></tr>");
			
		                                                        
			
			out.print("<tr><td width='12%'>Seleccione la Bodega</td><td><label><select name='cmbBodega' id='cmbBodega' onChange='BuscarRelacion()'><option value='Seleccione'>Seleccione</option>");
			rs=mcb.ObtenerBodegas();
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
			out.print("<tr><td colspan='2'><div id='rel'></div></td></tr>");
	       	out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		}
		
		
		if(va.equals("1")){
			
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String codigo=null;
			
			rs=mcb.ObtenerCodigoBodega(nombreBodega);
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			if(codigo==null){
				mcb.CrearBodega(nombreBodega, descripcion);
								
			}else{
				out.println("La bodega ya existe Por Favor Intente nuevamente.");
			}
			
			//out.print("<table width='100%' class='style6'  ><tr><td colspan='5' id='cabecera2'><span class='style11'><div align='center'>CREAR FORMATO </div></span></td></tr><tr><td colspan='4'><span >NOMBRE DEL FORMATO </span></td><td width='35'>&nbsp;</td></tr><tr><td colspan='5'><input name='txtNomFormato' type='text'  id='txtNomFormato' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr><tr><td colspan='2' >SELECCIONE LAS AREAS QUE TIENE EL FORMATO </td><td width='98' align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td width='427' align='center' ><input name='txtArea' type='text' id='txtArea' size='70' onkeyup='autocompletaArea()' /></td><td width='125' align='center' ><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' onclick='AsignarArea()' value='Asignar'  align='middle'/></td><td align='center'><label><span class='style3'><input name='btnFinalizar' type='button' id='btnFinalizar' class='boton4' value='Finalizar' onclick='FinalizarFormato()' /></span></label></td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center'><div id='sugerencias'></div></td><td align='center' ><input name='txtCodArea' type='hidden' id='txtCodArea' size='10' /></td><td align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center' ><div id='areas'></div></td><td colspan='4' align='center' ><span class='style8'>Datos Requeridos *</span></td></tr></table>");
	
			
		}
		
		if(va.equals("2")){
			
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			String codigo=null;
			
			rs=mcb.ObtenerCodigoEstante(nombreEstante);
			
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			if(codigo==null){
				mcb.CrearEstante(nombreEstante, observacion);
								
			}else{
				out.println("El Estante ya existe Por Favor Intente nuevamente.");
			}
			
		}
		
		if(va.equals("3")){
			
			 /* Aqui  se verifica si existe un formato con el mismo nombre.
			 */
			
			String codigo=null;
			String codigo1=null;
			String codigo2=null;
			
	
			rs=mcb.ObtenerBodegaEstante(cBodega, cEstante);
						
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			if(codigo==null){
				mcb.CrearBodegaEstante(cBodega, cEstante);
				//out.println("La Bodega fue creada.");
				/////////////////////////////////////////////////////////////////////////////////////////////////////
								
				rs=mcb.ObtenerBodegaEstante(cBodega);
									
				try {
					if(rs.next()){
						codigo=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
										
				/**
				 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
				 */
				if(codigo==null){
					
					out.print("<table  width='100%' class='style6'><tr><td width='12%'>Seleccione el Estante</td><td><label><select name='cmbEstante' id='cmbEstante'><option value='Seleccione'>Seleccione</option>");
					rs1=mcb.ObtenerEstantes();
					try {
						while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td></tr>");
					out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
					out.print("<tr><td width='12%'><label><input type='button' name='btnAsignarE' id='btnAsignarE' value='Asignar' onClick='Asignar()'></label></td>");		
					out.print("<td colspan='2'><label><input type='button' name='btnFinalizarE' id='btnFinalizarE' value='Finalizar' onClick='Finalizar()'></label></td></tr>");		
					out.print("</table>");
					
					//mcb.CrearBodegaEstante(cBodega, cEstante);
					//out.println("La Asignacion ha sido un exito.");				
				}else{
					//out.println("La Bodega ya tiene asignado ese Estante.");
					out.print("<table  width='100%' class='style6'><tr><td width='12%'>Seleccione el Estante</td><td><label><select name='cmbEstante' id='cmbEstante'><option value='Seleccione'>Seleccione</option>");
					rs1=mcb.ObtenerEstantes();
					try {
						while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					}
					out.print("</select></label></td></tr>");
					out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
					out.print("<tr><td width='12%'><label><input type='button' name='btnAsignarE' id='btnAsignarE' value='Asignar' onClick='Asignar()'></label></td>");		
					out.print("<td colspan='2'><label><input type='button' name='btnFinalizarE' id='btnFinalizarE' value='Finalizar' onClick='Finalizar()'></label></td></tr>");		
					out.print("</table>");
					
					
					out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Estantes Asignados</div></td></tr>");

					
					rs1=mcb.ObtenerEstantedeBodega(cBodega);
										
					try {
						while(rs1.next()){
							codigo1=rs1.getString(1);
							rs2=mcb.ObtenerEstante(codigo1);
							try {
								while(rs2.next()){
									codigo2=rs2.getString(1);
									//out.print("<tr><td width='12%'>"+codigo2+"</td></tr>");
									out.print("<tr><td width='20%'>"+codigo2+"</td><td><a  href='#'onclick='estante("+codigo1+")'>Eliminar</a></td></tr>");
									
								}
								rs2.getStatement().getConnection().close();
							}catch (SQLException e) {
								e.printStackTrace();
							}
									
						}
						rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
					
				}
				////////////////////////////////////////////////////////////////////////////////////////////////////
				
			}else{
				//out.println("La Bodega ya tiene asignado ese Estante.");
			}
			
			//out.print("<table width='100%' class='style6'  ><tr><td colspan='5' id='cabecera2'><span class='style11'><div align='center'>CREAR FORMATO </div></span></td></tr><tr><td colspan='4'><span >NOMBRE DEL FORMATO </span></td><td width='35'>&nbsp;</td></tr><tr><td colspan='5'><input name='txtNomFormato' type='text'  id='txtNomFormato' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr><tr><td colspan='2' >SELECCIONE LAS AREAS QUE TIENE EL FORMATO </td><td width='98' align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td width='427' align='center' ><input name='txtArea' type='text' id='txtArea' size='70' onkeyup='autocompletaArea()' /></td><td width='125' align='center' ><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' onclick='AsignarArea()' value='Asignar'  align='middle'/></td><td align='center'><label><span class='style3'><input name='btnFinalizar' type='button' id='btnFinalizar' class='boton4' value='Finalizar' onclick='FinalizarFormato()' /></span></label></td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center'><div id='sugerencias'></div></td><td align='center' ><input name='txtCodArea' type='hidden' id='txtCodArea' size='10' /></td><td align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center' ><div id='areas'></div></td><td colspan='4' align='center' ><span class='style8'>Datos Requeridos *</span></td></tr></table>");
	
			
		}
		
		
		
		if(va.equals("4")){
			
			 /* Aqui  se verifica si existen estantes para la bodega.
			*/
			String codigo=null;
			String codigo1=null;
			String codigo2=null;
			
			rs=mcb.ObtenerBodegaEstante(cBodega);
								
			try {
				if(rs.next()){
					codigo=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
									
			/**
			 * Si No existe Ninguno Con el mismo Nombre Se Procede a Ingresar El Nuevo Formato.
			 */
			if(codigo==null){
				
				out.print("<table  width='100%' class='style6'><tr><td width='12%'>Seleccione el Estante</td><td><label><select name='cmbEstante' id='cmbEstante'><option value='Seleccione'>Seleccione</option>");
				rs1=mcb.ObtenerEstantes();
				try {
					while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</select></label></td></tr>");
				out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
				out.print("<tr><td width='12%'><label><input type='button' name='btnAsignarE' id='btnAsignarE' value='Asignar' onClick='Asignar()'></label></td>");		
				out.print("<td colspan='2'><label><input type='button' name='btnFinalizarE' id='btnFinalizarE' value='Finalizar' onClick='Finalizar()'></label></td></tr>");		
				out.print("</table>");
				
				//mcb.CrearBodegaEstante(cBodega, cEstante);
				//out.println("La Asignacion ha sido un exito.");				
			}else{
				//out.println("La Bodega ya tiene asignado ese Estante.");
				out.print("<table  width='100%' class='style6'><tr><td width='12%'>Seleccione el Estante</td><td><label><select name='cmbEstante' id='cmbEstante'><option value='Seleccione'>Seleccione</option>");
				rs1=mcb.ObtenerEstantes();
				try {
					while(rs1.next()){
					out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</select></label></td></tr>");
				out.print("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>");
				out.print("<tr><td width='12%'><label><input type='button' name='btnAsignarE' id='btnAsignarE' value='Asignar' onClick='Asignar()'></label></td>");		
				out.print("<td colspan='2'><label><input type='button' name='btnFinalizarE' id='btnFinalizarE' value='Finalizar' onClick='Finalizar()'></label></td></tr>");		
				out.print("</table>");
				
				
				out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Estantes Asignados</div></td></tr>");

				
				rs1=mcb.ObtenerEstantedeBodega(cBodega);
									
				try {
					while(rs1.next()){
						codigo1=rs1.getString(1);
						rs2=mcb.ObtenerEstante(codigo1);
						try {
							while(rs2.next()){
								codigo2=rs2.getString(1);
								out.print("<tr><td width='20%'>"+codigo2+"</td><td><a  href='#'onclick='estante("+codigo1+")'>Eliminar</a></td></tr>");
								//
							}
							rs2.getStatement().getConnection().close();
						}catch (SQLException e) {
							e.printStackTrace();
						}
								
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			//out.print("<table width='100%' class='style6'  ><tr><td colspan='5' id='cabecera2'><span class='style11'><div align='center'>CREAR FORMATO </div></span></td></tr><tr><td colspan='4'><span >NOMBRE DEL FORMATO </span></td><td width='35'>&nbsp;</td></tr><tr><td colspan='5'><input name='txtNomFormato' type='text'  id='txtNomFormato' onkeyup='this.value=this.value.toUpperCase();' size='70' />  <span class='style8'>*</span></td><BR /></tr><tr><td colspan='2' >SELECCIONE LAS AREAS QUE TIENE EL FORMATO </td><td width='98' align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td width='427' align='center' ><input name='txtArea' type='text' id='txtArea' size='70' onkeyup='autocompletaArea()' /></td><td width='125' align='center' ><input name='btnAsignar' class='boton4' type='button' id='btnAsignar' onclick='AsignarArea()' value='Asignar'  align='middle'/></td><td align='center'><label><span class='style3'><input name='btnFinalizar' type='button' id='btnFinalizar' class='boton4' value='Finalizar' onclick='FinalizarFormato()' /></span></label></td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center'><div id='sugerencias'></div></td><td align='center' ><input name='txtCodArea' type='hidden' id='txtCodArea' size='10' /></td><td align='center'>&nbsp;</td><td colspan='2' align='center'>&nbsp;</td></tr><tr><td align='center' ><div id='areas'></div></td><td colspan='4' align='center' ><span class='style8'>Datos Requeridos *</span></td></tr></table>");
	
			
		}
		
		if(va.equals("5")){
			mcb.BorrarEstantesA(code);			
	   }
		
		
		if(va.equals("Mbodest")){
			
			//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Asignar o Eliminar Estante</div></td></tr>");
			out.print("<table width='100%' class='style6' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Modificar Bodegas y Estantes</div></td></tr>");
			out.print("<tr><td width='12%'>Opcion a Modificar: </td><td><label><select name='cmbOpcion' id='cmbOpcion' onChange='BuscarOpcion()'><option value='Seleccione'>Seleccione</option><option value='1'>Bodegas</option><option value='2'>Estantes</option>");
			
			out.print("</select></label></td></tr>");
			//out.print("<tr><td colspan='2'><div id='rel'></div></td></tr>");
	       	out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		}
		
		if(va.equals("Mostrar")){
			//out.print("cbodega "+cBodega);
			//out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Asignar o Eliminar Estante</div></td></tr>");
			
			out.print("<table width='100%' class='style6' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Modificar Bodegas y Estantes</div></td></tr>");
			
			
			if(cBodega.equals("1")){
				out.print("<tr><td width='12%'>Opcion a Modificar: </td><td><label><select name='cmbOpcion' id='cmbOpcion' onChange='BuscarOpcion()'><option value='1'>Bodegas</option><option value='2'>Estantes</option>");
				out.print("</select></label></td></tr>");
				out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
				out.print("<table width='100%' class='style6' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Bodegas Creadas</div></td></tr>");
				String cj="1";
				rs=mcb.ObtenerBodegas();
				try {
					while(rs.next()){
					out.print("<tr><td><a  href='#'onclick='Mbode("+rs.getString(1)+","+cj+")'>"+rs.getString(2)+"</a></td></tr>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
			}
			if(cBodega.equals("2")){
				out.print("<tr><td width='12%'>Opcion a Modificar: </td><td><label><select name='cmbOpcion' id='cmbOpcion' onChange='BuscarOpcion()'><option value='2'>Estantes</option><option value='1'>Bodegas</option>");
				out.print("</select></label></td></tr>");
				out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			
				out.print("<table width='100%' class='style6' ><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Estantes Creados</div></td></tr>");
				String cj="2";
				rs1=mcb.ObtenerEstante();
				try {
					while(rs1.next()){
					out.print("<tr><td><a  href='#'onclick='Mbode("+rs1.getString(1)+","+cj+")'>"+rs1.getString(2)+"</a></td></tr>");
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}	
			}
			
			
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
		}
		/////////////////////////
		
		if(va.equals("MBOE")){	
			//Code
			
			if(boe.equals("1")){
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Modificar Bodega</div></td></tr>");
			rs1=mcb.ObtenerBodega(code);
			try {
				if(rs1.next()){
					out.print("<tr><td width='12%'>Nombre de la Bodega</td><td><label><input name='txtNombreBodega' type='text' id='txtNombreBodega' value='"+rs1.getString(1)+"' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
					out.print("<tr><td>Descripcion</td><td><label><textarea name='txtDescripcion' id='txtDescripcion' cols='38' rows='3'>"+rs1.getString(2)+"</textarea></label></td></tr>");
					out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearBodega' id='btnCrearBodega' value='Modificar' onClick='ModificarBodest(1,"+code+")'></label></div></td></tr>");		
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			}
			
			if(boe.equals("2")){
			out.print("<table width='100%' class='style6'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2'>Modificar Estantes</div></td></tr>");
			rs1=mcb.ObtenerEstante(code);
			try {
				if(rs1.next()){
					out.print("<tr><td width='12%'>Nombre del estante</td><td><label><input name='txtNombreEstante' type='text' id='txtNombreEstante'  value='"+rs1.getString(1)+"' onkeyup='this.value=this.value.toUpperCase();' size='48' /></label></td></tr>");
					out.print("<tr><td width='12%'>Observacion</td><td><label><input name='txtObservacion' type='text' id='txtObservacion' value='"+rs1.getString(2)+"' size='48' /></label></td></tr>");
					out.print("<tr><td colspan='2'><div align='left'><label><input type='button' name='btnCrearEstante' id='btnCrearEstante' value='Modificar' onClick='ModificarBodest(2,"+code+")'></label></td></tr>");		
				}
				rs1.getStatement().getConnection().close();	
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			out.print("<tr><td colspan='2'>&nbsp;</td></tr></table>");
			}
		}
		
		
		
		
		if(va.equals("Modificarboe")){
			if(boe.equals("1")){
				String codigo=null;
				rs=mcb.ObtenerCodigoBodega(nombreBodega);
				try {
					while(rs.next()){
						if(!rs.getString(1).equals(code)){
							codigo=rs.getString(1);
							//out.print("code:"+code+" rs:"+rs.getString(1));
						}	
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
				if(codigo==null){
					mcb.ModificarBodega(nombreBodega, descripcion, code);
									
				}else{
					out.println("La bodega ya existe Por Favor Intente nuevamente.");
				}
			}
			if(boe.equals("2")){
			String codigo=null;		
			rs=mcb.ObtenerCodigoEstante(nombreEstante);
			try {
				if(rs.next()){
					if(!rs.getString(1).equals(code)){
						codigo=rs.getString(1);
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(codigo==null){
				mcb.ModificarEstante(nombreEstante, observacion,code);
								
			}else{
				out.println("El Estante ya existe Por Favor Intente nuevamente.");
			}
		 }
		}
		
		
		
		
		
		////////////////
	}

}
