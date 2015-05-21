package pat_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.Conexion;
import pat_metodo.MetodoCrearPaciente;

/**
 * Servlet implementation class ControlCrearPaciente
 */
public class Pat_ControlCrearPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoCrearPaciente mcp = new MetodoCrearPaciente();
		ResultSet rs=null;
		
		String cad =req.getParameter("codigo");
		String tipo =req.getParameter("tipoDoc");
		int accion;
		accion = Integer.parseInt(req.getParameter("txtAccion"));
		
		if(accion == 26){    	    
            try {
                rs =mcp.AutocompletarPacientePatologia(cad,tipo);
                String cadena ="";
                String nombre ="";
                cadena="[";
                while(rs.next()){
                	nombre=rs.getString(2)+" "+rs.getString(3);
                	cadena = cadena+"'"+rs.getString(1)+"|"+nombre+"'";
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
		
		if(accion == 27){    	    
            try {
                rs =mcp.listarParaAutocompletarNombrePaciente(cad);
                String cadena ="";
                String nombre ="";
                cadena="[";
                while(rs.next()){
                	nombre=rs.getString(2)+" "+rs.getString(3);
                	cadena = cadena+"'"+rs.getString(4)+"|"+rs.getString(1)+"|"+nombre+"'";
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
		
		String va=req.getParameter("valor");
		/////////////////////////////////////////////////////////////
		String TipoDocumento=req.getParameter("TipoDocumento");
		String Identificacion=req.getParameter("Identificacion");
		String Apellidos=req.getParameter("Apellidos");
		String Nombre=req.getParameter("Nombre");
		String FechaNacimiento=req.getParameter("FechaNacimiento");
		String Telefono=req.getParameter("Telefono");
		String Direccion=req.getParameter("Direccion");
		String Eps=req.getParameter("Eps");
		String Email=req.getParameter("Email");
		String Genero=req.getParameter("Genero");
		/////////////////////////////////////////////////////////////
		String NombreArea=req.getParameter("NombreArea");
		String NombreEstudio=req.getParameter("NombreEstudio");
		String CodArea_fk=req.getParameter("CodArea_fk");
		/////////////////////////////////////////////////////////////
		String CodigoEstudio=req.getParameter("CodigoEstudio");
		/////////////////////////////////////////////////////////////
		String CodPac=req.getParameter("CodPac");
		String Protocolo=req.getParameter("Protocolo");
		String TipoEspecimen=req.getParameter("TipoEspecimen");
		String DiagnosticoClinico=req.getParameter("DiagnosticoClinico");
		String Medico=req.getParameter("Medico");
		String FechaRecibo=req.getParameter("FechaRecibo");
		String FechaEntrega=req.getParameter("FechaEntrega");
		String CodAsignacion=req.getParameter("CodAsignacion");
		//String DescMacro=req.getParameter("DescMacro");
//		String DescMicro=req.getParameter("DescMicro");
		String ConcDiagn=req.getParameter("ConcDiagn");
		String FechaSistema=req.getParameter("FechaSistema");
		String HoraSistema=req.getParameter("HoraSistema");
		String CodUsuario=req.getParameter("CodUsuario");
		/////////////////////////////////////////////////////////////
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		MetodoCrearPaciente mcp = new MetodoCrearPaciente();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs3=null;
		/////////////////////////////////////////////////////////////
		
		if(va.equals("1")){
			mcp.IngresarPaciente(TipoDocumento, Identificacion, Nombre, Apellidos, FechaNacimiento, Genero, Eps, Telefono, Direccion, Email);
			out.print("Ingreso Exitoso");
		}
		
		if(va.equals("2")){
			out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Crear Area</div></td></tr>");
			out.print("<tr><td width='12%'>Nombre Del Area </td><td colspan='2'><input name='txtNomArea' type='text' id='txtNomArea' size='100' /><input name='btnCrearEstudio' type='button' id='btnCrearEstudio' value='     Crear     ' onClick='CrearArea()'></td></tr>");
			out.print("<tr><td colspan='3'><div align='center'>Areas Creadas</div></td></tr>");
			rs=mcp.ObtenerAreasCreadas();
			try {
				while(rs.next()){
					out.print("<tr><td colspan='3'>"+rs.getString(2)+"</td></tr>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</table>");
		}
		if(va.equals("2.1")){
			mcp.CrearArea(NombreArea);
			out.print("Ingreso Exitoso.");
		}
		
		if(va.equals("3")){
			out.print("<table width='100%' border='1'><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Crear Estudio </div></td></tr>");
			out.print("<tr><td>Seleccione Area </td><td colspan='2'><select name='cmbArea' id='cmbArea' onchange='CargarEstudioPorArea()' ><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mcp.ObtenerAreasCreadas();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</select></td></tr><tr><td width='12%'>Nombre Del Estudio </td><td colspan='2'><input name='txtNomEstudio' type='text' id='txtNomEstudio' size='100' /><input name='btnCrearEstudio' type='button' id='btnCrearEstudio' value='     Crear     ' onClick='CrearEstudio()'></td></tr>");
			out.print("<tr><td colspan='3'><div id='relacion'></div></td></tr></table>");
		}
		if(va.equals("3.1")){
			mcp.CrearEstudio(NombreEstudio, CodArea_fk);
			out.print("Ingreso Exitoso.");
		}
		if(va.equals("3.2")){
			rs=mcp.ObtenerEstudiosPorArea(CodArea_fk);
			String NomArea="";
			try {
				if(rs.next()){
					NomArea=rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("<table width='100%' border='1'><tr><td><div align='center'>Estudios Del Area:"+NomArea+" </div></td></tr>");
			
			rs1=mcp.ObtenerEstudiosPorArea(CodArea_fk);
			try {
				while(rs1.next()){
					out.print("<tr><td>"+rs1.getString(1)+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			out.print("</table>");
		}
		
		if(va.equals("4")){
		
			rs=mcp.ObtenerDatosPaciente(TipoDocumento, Identificacion);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td colspan='8'><div align='center' id='cabecera2' class='style11' >DATOS PACIENTE </div></td></tr>");
					out.print("<tr><td width='8%'>Identificacion:</td><td width='13%'>"+rs.getString(2)+"-"+rs.getString(3)+"</td>");
					out.print("<td width='11%'>Nombre Paciente: </td><td width='35%'>"+rs.getString(4)+" "+rs.getString(5)+"</td>");
					out.print("<td width='5%'>Edad:</td><td width='8%'>"+rs.getString(7)+" Anios </td>");
					out.print("<td width='7%'>Genero:</td><td width='13%'>"+rs.getString(6)+"</td><input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString(1)+" ></tr>");
					
					//out.print("<tr><td colspan='2'> Seleccione Estudio: </td><td colspan='6'><select name='cmbEstudios' id='cmbEstudios' onchange='VerFormato()'><option value='Seleccione' selected='selected'>Seleccione</option>");
					//rs1=mcp.ObtenerEstudios();
					out.print("<tr><td colspan='2'><div align='center'>Seleccione Estudio <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name='txtCodFormato' id='txtCodFormato'><option value='Seleccione' selected='selected'>Seleccione</option>");
					rs1=mcp.ObtenerFormatos();
					
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();				
					out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /></label></div></td></tr>");
					out.print("<tr><td colspan='8'><div id='Formato'></div></td></tr></table>");
				}
				else{
					
				}
				rs.getStatement().getConnection().close();
			
				
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("4.1")){
			String TipoFormato="";
			String NomEstudio="";
			rs=mcp.ObtenerNombreEstudiosTipoFormato(CodigoEstudio);
			try {
				if(rs.next()){
					TipoFormato=rs.getString(2);
					NomEstudio=rs.getString(1);
				}
				rs.getStatement().getConnection().close();
				if(TipoFormato.equals("1")){
					//formato normal.
					out.print("<table width='100%' border='1'><tr><td height='27' colspan='6'><div align='center' id='cabecera2' class='style11' >"+NomEstudio+" </div></td></tr>");
					out.print("<tr><td width='6%'>Protocolo</td><td width='17%'><input name='txtProtocolo' type='text' id='txtProtocolo' size='30'></td>");
					out.print("<td width='10%'>Tipo Especimen </td><td width='22%'><input name='txtTipoEspecimen' type='text' id='txtTipoEspecimen' size='40'></td>");
					out.print("<td width='13%'>Diagnostico Clinico </td><td width='32%'><input name='txtDiagClinico' type='text' id='txtDiagClinico' size='60'></td></tr>");
					
					out.print("<tr><td>Medico</td><td><input name='txtMedicoTratante' type='text' id='txtMedicoTratante' size='30'></td>");
					out.print("<td>Fecha Recibo </td><td><input name='txtFechaRecibo' type='text' id='txtFechaRecibo' size='40'></td>");
					out.print("<td>Fecha Entrega </td><td><input name='txtFechaEntrega' type='text' id='txtFechaEntrega' size='40'></td></tr>");
					out.print("<tr><td colspan='6'>Descripcion Macroscopica. </td></tr><tr><td colspan='6'><textarea name='txtDescMacro' cols='80' rows='4' id='txtDescMacro'></textarea></td></tr>");
					out.print("<tr><td colspan='6'>Descripcion Microscopica </td></tr><tr><td colspan='6'><textarea name='txtDescMicro' cols='80' rows='4' id='txtDescMicro'></textarea></td></tr>");
					out.print("<tr><td colspan='6'>Conclusion y/o Diagnostico</td></tr><tr><td colspan='6'><textarea name='txtConcDiag' cols='80' rows='4' id='txtConcDiag'></textarea></td></tr>");
					out.print("<tr><td colspan='6'><div align='center'><input name='btnIngresarEstudioSimple' type='button' id='btnIngresarEstudioSimple' value='     Ingresar     ' onClick='IngresarEstudioSimple()'></div></td></tr></table>");
				}
				if(TipoFormato.equals("2")){}
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		/*********************************************************************/
		
		if(va.equals("4.22")){
			
			//rs=mcp.ObtenerDatosPaciente(TipoDocumento, Identificacion);
			try {
				//if(rs.next()){
					//out.print("<table width='100%' border='1'><tr><td colspan='8'><div align='center' id='cabecera2' class='style11' >DATOS PACIENTE </div></td></tr>");
					//out.print("<tr><td width='8%'>Identificacion:</td><td width='13%'>"+rs.getString(2)+"-"+rs.getString(3)+"</td>");
					//out.print("<td width='11%'>Nombre Paciente: </td><td width='35%'>"+rs.getString(4)+" "+rs.getString(5)+"</td>");
					//out.print("<td width='5%'>Edad:</td><td width='8%'>"+rs.getString(7)+" Anios </td>");
					//out.print("<td width='7%'>Genero:</td><td width='13%'>"+rs.getString(6)+"</td><input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString(1)+" ></tr>");
					//out.print("<tr><td colspan='2'> Seleccione Estudio: </td><td colspan='6'><select name='cmbEstudios' id='cmbEstudios' onchange='VerFormato()'><option value='Seleccione' selected='selected'>Seleccione</option>");
					//rs1=mcp.ObtenerEstudios();
					out.print("<table width='100%' border='1'><tr><td colspan='8'>Nombre Estudio</td><td>Nombre Paciente</td></tr>");
					rs1=mcp.ObtenerEstudiosPacientePorAprobar(TipoDocumento, Identificacion);
					while(rs1.next()){
						String FechaIni=rs1.getString(3);
						String HoraIni=rs1.getString(4);
						String dia,mes,anio=null; 
						String horas,minutos,segundos=null;
						  
						dia=FechaIni.substring(8,10);
						mes=FechaIni.substring(5,7);
						anio=FechaIni.substring(0,4);
						
						horas=HoraIni.substring(0,2);
						minutos =HoraIni.substring(3,5);
						segundos=HoraIni.substring(6,8);
						out.print("<tr><td colspan='7'><a  href='#' onclick='VerAprobPato("+rs1.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+","+rs1.getString(7)+",&quot;"+rs1.getString(8)+"&quot;,&quot;"+rs1.getString(9)+"&quot;)'>"+rs1.getString(2)+"</a></td><td>"+rs1.getString(3)+"/"+rs1.getString(4)+"</td><td>"+rs1.getString(5)+" "+rs1.getString(6)+"</td></tr>");
						//
					}
					rs1.getStatement().getConnection().close();				
					out.print("<tr><td colspan='8'><div id='Formato'></div></td></tr></table>");
				//}
				//else{
					
				//}
				//rs.getStatement().getConnection().close();
			
				
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		
		
		
		
		if(va.equals("4.2")){
			
			rs=mcp.ObtenerDatosPaciente(TipoDocumento, Identificacion);
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td colspan='8'><div align='center' id='cabecera2' class='style11' >DATOS PACIENTE </div></td></tr>");
					out.print("<tr><td width='8%'>Identificacion:</td><td width='13%'>"+rs.getString(2)+"-"+rs.getString(3)+"</td>");
					out.print("<td width='11%'>Nombre Paciente: </td><td width='35%'>"+rs.getString(4)+" "+rs.getString(5)+"</td>");
					out.print("<td width='5%'>Edad:</td><td width='8%'>"+rs.getString(7)+" Anios </td>");
					out.print("<td width='7%'>Genero:</td><td width='13%'>"+rs.getString(6)+"</td><input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString(1)+" ></tr>");
					//out.print("<tr><td colspan='2'> Seleccione Estudio: </td><td colspan='6'><select name='cmbEstudios' id='cmbEstudios' onchange='VerFormato()'><option value='Seleccione' selected='selected'>Seleccione</option>");
					//rs1=mcp.ObtenerEstudios();
					out.print("<tr><td colspan='8'>Nombre Estudio</td></tr>");
					rs1=mcp.ObtenerEstudiosPaciente(TipoDocumento, Identificacion);
					while(rs1.next()){
						String FechaIni=rs1.getString(3);
						String HoraIni=rs1.getString(4);
						String dia,mes,anio=null; 
						String horas,minutos,segundos=null;
						  
						dia=FechaIni.substring(8,10);
						mes=FechaIni.substring(5,7);
						anio=FechaIni.substring(0,4);
						
						horas=HoraIni.substring(0,2);
						minutos =HoraIni.substring(3,5);
						segundos=HoraIni.substring(6,8);
						out.print("<tr><td colspan='7'><a  href='#' onclick='VerActuPato("+rs1.getString(1)+","+dia+","+mes+","+anio+","+horas+","+minutos+","+segundos+")'>"+rs1.getString(2)+"</a></td><td>"+rs1.getString(3)+"/"+rs1.getString(4)+"</td</tr>");
						//
					}
					rs1.getStatement().getConnection().close();				
					out.print("<tr><td colspan='8'><div id='Formato'></div></td></tr></table>");
				}
				else{
					
				}
				rs.getStatement().getConnection().close();
			
				
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("4.33")){
			String Tipo=req.getParameter("Tipo");
			
			
			try {
				rs=mcp.ObtenerDatosPaciente(TipoDocumento, Identificacion);
				if(rs.next()){
				out.print("<table width='100%' border='1'><tr><td colspan='8'><div align='center' id='cabecera2' class='style11' >DATOS PACIENTE </div></td></tr>");
				out.print("<tr><td width='8%'>Identificacion:</td><td width='13%'>"+rs.getString(2)+"-"+rs.getString(3)+"</td>");
				out.print("<td width='11%'>Nombre Paciente: </td><td width='35%'>"+rs.getString(4)+" "+rs.getString(5)+"</td>");
				out.print("<td width='5%'>Edad:</td><td width='8%'>"+rs.getString(7)+" Anios </td>");
				out.print("<td width='7%'>Genero:</td><td width='13%'>"+rs.getString(6)+"</td><input name='txtCodPac' type='hidden' id='txtCodPac' value="+rs.getString(1)+" ></tr></table>");
				}
				rs.getStatement().getConnection().close();
				
				
				
				if(Tipo.equals("0")){
					//out.print("Entro a Tipo=0");
					int contador=0;
					rs=mcp.ObtenerResultadosEstudiosActu(CodAsignacion);
					if(rs.next()){						
						 out.print("<table width='100%' border='1' class='style6'><tr><td width='9%'><div>Protocolo</div></td><td width='17%'><input name='txtProtocolo' type='text' id='txtProtocolo' size='30' maxlength='10' value='"+rs.getString(13)+"' /></td> <td width='11%'><div>Tipo Especimen </div></td><td width='22%'><label><input name='txtTipoespecimen' type='text' id='txtTipoespecimen' size='40' maxlength='50' value='"+rs.getString(14)+"' /></label></td>");
						 out.print("<td width='12%'><div>Medico Tratante</div> </td><td width='29%'><input name='txtMedicoTratante' type='text' id='txtMedicoTratante' size='40' maxlength='40' value='"+rs.getString(12)+"' /></td></tr><tr><td><div>Fecha Recibo </div></td><td><input name='txtFechaRecibo' type='text' id='txtFechaRecibo' value='"+rs.getString(11)+"' /></td><td><div>Fecha Entrega</div></td><td><input name='txtFechaEntrega' type='text' id='txtFechaEntrega' size='30' maxlength='20' value='"+rs.getString(10)+"' /></td>");
						 out.print("<td><div>Diagnostico Clinico</div></td><td><label><textarea name='txtDiagClinico' id='txtDiagClinico' cols='40' rows='2'  >"+rs.getString(9)+"</textarea></label></td></tr><tr><td colspan='6'><div align='center'><input name='btnActualizarDatosComplementarios' type='button' value='     Aprobar     ' onClick='AprobarPatologia0()' /></div></td></tr></table>");
						 
						 out.print("<table width='100%' border='1' class='style6'><tr><input name='txtCodAsignacion' type='hidden' id='txtCodAsignacion' value="+CodAsignacion+" ><input name='txtCodDatosComplementarios' type='hidden' id='txtCodDatosComplementarios' value="+rs.getString(2)+" ><td colspan='4' class='style11' id='cabecera2'><div align='center'> ESTUDIO&gt;&gt; "+rs.getString(3)+" </div></td></tr></table>");
					}				
					rs.getStatement().getConnection().close();

					rs1=mcp.ObtenerResultadosEstudiosActu(CodAsignacion);
					out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
					while(rs1.next()){					
						contador=contador+1;
						out.println("<tr><td width='18%'>"+rs1.getString(5)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs1.getString(1)+" ></td>");
					 
						out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' >"+rs1.getString(6)+"</textarea></td></tr>");
					 
					}
					out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
					rs1.getStatement().getConnection().close();
				}
				if(Tipo.equals("1")){
					rs=mcp.ObtenerResultadosEstudiosAprobar(CodAsignacion);
					if(rs.next()){
						
						 out.print("<table width='100%' border='1' class='style6'><tr><td width='9%'><div>Protocolo</div></td><td width='17%'><input name='txtProtocolo' type='text' id='txtProtocolo' size='30' maxlength='10' value='"+rs.getString(7)+"' /></td> <td width='11%'><div>Tipo Especimen </div></td><td width='22%'><label><input name='txtTipoespecimen' type='text' id='txtTipoespecimen' size='40' maxlength='50' value='"+rs.getString(4)+"'     /></label></td>");
						 out.print("<td width='12%'><div>Medico Tratante</div> </td><td width='29%'><input name='txtMedicoTratante' type='text' id='txtMedicoTratante' size='40' maxlength='40' value='"+rs.getString(6)+"' /></td></tr><tr><td><div>Fecha Recibo </div></td><td><input name='txtFechaRecibo' type='text' id='txtFechaRecibo' value='"+rs.getString(2)+"' /></td><td><div>Fecha Entrega</div></td><td><input name='txtFechaEntrega' type='text' id='txtFechaEntrega' size='30' maxlength='20' value='"+rs.getString(3)+"' /></td>");
						 out.print("<td><div>Diagnostico Clinico</div></td><td><label><textarea name='txtDiagClinico' id='txtDiagClinico' cols='40' rows='2'  >"+rs.getString(5)+"</textarea></label></td></tr><tr><td colspan='6'><div align='center'><input name='btnActualizarDatosComplementarios' type='button' value='     Aprobar     ' onClick='AprobarPatologia1()' /></div></td></tr></table>");
						 
						 out.print("<table width='100%' border='1' class='style6'><tr><input name='txtCodAsignacion' type='hidden' id='txtCodAsignacion' value="+CodAsignacion+" ><input name='txtCodDatosComplementarios' type='hidden' id='txtCodDatosComplementarios' value="+rs.getString(1)+" ><td colspan='4' class='style11' id='cabecera2'><div align='center'></div></td></tr></table>");

						
						
						out.print("<table border='0' width='100%' ><tr><td colspan='4'><div id='cabecera2' class='style11' align='center'>FORMATO DE BIOPSIA RENAL </div></td></tr>");
						out.print("<tr><td width='25%' class='style12'>DESCRIPCION MACROSCOPICA </td><td colspan='3'><textarea name='txtDesMacro' cols='65' rows='5' id='txtDesMacro'>"+rs.getString(11)+"</textarea></td></tr>");
						out.print("<tr><td class='style12'>DESCRIPCION MICROSCOPICA </td><td colspan='3'><textarea name='txtDescMicro1' cols='65' rows='5' id='txtDescMicro1'>"+rs.getString(12)+"</textarea></td></tr>");
						out.print("<tr><td class='style12'>RESUMEN HISTORIA CLINICA </td><td colspan='3'><textarea name='txtDescMicro2' cols='65' rows='5' id='txtDescMicro2'>"+rs.getString(13)+"</textarea></td></tr>");
						out.print("<tr><td colspan='2' class='style12'>ESTUDIO DE INMUNOFLUORESCENCIA DIRECTA RIÑON </td><td width='17%' class='style12'>&nbsp;</td><td width='29%'>&nbsp;</td></tr></table>");
						out.print("<table border='0' width='100%' ><tr><td colspan='4'><input name='txtObserLleno' type='text' id='txtObserLleno' style='width:100%'  value='"+rs.getString(14)+"'></td></tr>");
						out.print("<tr><td width='11%'>ANTICUERPO</td><td width='10%'>RESULTADO</td><td width='14%'>INTENSIDAD</td><td width='65%'>PATRON</td></tr>");
						out.print("<tr><td>IgA</td><td><select name='RE1' id='RE1'><option value="+rs.getString(15)+" selected='selected'>"+rs.getString(15)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT1' type='text' id='IT1' value='"+rs.getString(25)+"' ></td><td><input name='PTR1' type='text' id='PTR1' value='"+rs.getString(35)+"' ></td></tr>");
						out.print("<tr><td>IgG</td><td><select name='RE2' id='RE2'><option value="+rs.getString(16)+" selected='selected'>"+rs.getString(16)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT2' type='text' id='IT2' value='"+rs.getString(26)+"' ></td><td><input name='PTR2' type='text' id='PTR2' value='"+rs.getString(36)+"' ></td></tr>");
						out.print("<tr><td>IgM</td><td><select name='RE3' id='RE3'><option value="+rs.getString(17)+" selected='selected'>"+rs.getString(17)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT3' type='text' id='IT3' value='"+rs.getString(27)+"' ></td><td><input name='PTR3' type='text' id='PTR3' value='"+rs.getString(37)+"' ></td></tr>");
						out.print("<tr><td>C3</td><td><select name='RE4' id='RE4'><option value="+rs.getString(18)+" selected='selected'>"+rs.getString(18)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT4' type='text' id='IT4' value='"+rs.getString(28)+"' ></td><td><input name='PTR4' type='text' id='PTR4' value='"+rs.getString(38)+"' ></td></tr>");
						out.print("<tr><td>C4</td><td><select name='RE5' id='RE5'><option value="+rs.getString(19)+" selected='selected'>"+rs.getString(19)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT5' type='text' id='IT5' value='"+rs.getString(29)+"' ></td><td><input name='PTR5' type='text' id='PTR5' value='"+rs.getString(39)+"' ></td></tr>");
						out.print("<tr><td>C1q</td><td><select name='RE6' id='RE6'><option value="+rs.getString(20)+" selected='selected'>"+rs.getString(20)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT6' type='text' id='IT6' value='"+rs.getString(30)+"' ></td><td><input name='PTR6' type='text' id='PTR6' value='"+rs.getString(40)+"' ></td></tr>");
						out.print("<tr><td>Cadenas Lamda </td><td><select name='RE7' id='RE7'><option value="+rs.getString(21)+" selected='selected'>"+rs.getString(21)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT7' type='text' id='IT7' value='"+rs.getString(31)+"' ></td><td><input name='PTR7' type='text' id='PTR7' value='"+rs.getString(41)+"' ></td></tr>");
						out.print("<tr><td>Cadenas Cappa </td><td><select name='RE8' id='RE8'><option value="+rs.getString(22)+" selected='selected'>"+rs.getString(22)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT8' type='text' id='IT8' value='"+rs.getString(32)+"' ></td><td><input name='PTR8' type='text' id='PTR8' value='"+rs.getString(42)+"' ></td></tr>");
						out.print("<tr><td>Albumina</td><td><select name='RE9' id='RE9'><option value="+rs.getString(23)+" selected='selected'>"+rs.getString(23)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT9' type='text' id='IT9' value='"+rs.getString(33)+"' ></td><td><input name='PTR9' type='text' id='PTR9' value='"+rs.getString(43)+"' ></td></tr>");
						out.print("<tr><td>Fribrinogeno</td><td><select name='RE10' id='RE10'><option value="+rs.getString(24)+" selected='selected'>"+rs.getString(24)+"</option><option value='Positivo'>Positivo</option><option value='Negativo'>Negativo</option></select></td><td><input name='IT10' type='text' id='IT10' value='"+rs.getString(34)+"' ></td><td><input name='PTR10' type='text' id='PTR10' value='"+rs.getString(44)+"' ></td></tr>");
						out.print("<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
						out.print("<tr><td width='11%' class='style12'>DIAGNOSTICO</td><td colspan='3'><textarea name='txtDiagnostico' cols='65' rows='5' id='txtDiagnostico'>"+rs.getString(45)+"</textarea></td></tr>");
						out.print("<tr><td class='style12'><input type='hidden' id='txtCodBiopsia' value="+rs.getString(9)+"></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>");
						out.print("</table>");


					}
					rs.getStatement().getConnection().close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		if(va.equals("4.3")){
			int contador=0;
			rs=mcp.ObtenerResultadosEstudiosActu(CodAsignacion);
			try {
				if(rs.next()){						
					 out.print("<table width='100%' border='1' class='style6'><tr><td width='9%'><div>Protocolo</div></td><td width='17%'><input name='txtProtocolo' type='text' id='txtProtocolo' size='30' maxlength='10' value='"+rs.getString(13)+"' /></td> <td width='11%'><div>Tipo Especimen </div></td><td width='22%'><label><input name='txtTipoespecimen' type='text' id='txtTipoespecimen' size='40' maxlength='50' value='"+rs.getString(14)+"' /></label></td>");
					 out.print("<td width='12%'><div>Medico Tratante</div> </td><td width='29%'><input name='txtMedicoTratante' type='text' id='txtMedicoTratante' size='40' maxlength='40' value='"+rs.getString(12)+"' /></td></tr><tr><td><div>Fecha Recibo </div></td><td><input name='txtFechaRecibo' type='text' id='txtFechaRecibo' value='"+rs.getString(11)+"' /></td><td><div>Fecha Entrega</div></td><td><input name='txtFechaEntrega' type='text' id='txtFechaEntrega' size='30' maxlength='20' value='"+rs.getString(10)+"' /></td>");
					 out.print("<td><div>Diagnostico Clinico</div></td><td><label><textarea name='txtDiagClinico' id='txtDiagClinico' cols='40' rows='2'  >"+rs.getString(9)+"</textarea></label></td></tr><tr><td colspan='6'><div align='center'><input name='btnActualizarDatosComplementarios' type='button' value='     Actualizar     ' onClick='ActualizarPatologia()' /></div></td></tr></table>");
					 
					 out.print("<table width='100%' border='1' class='style6'><tr><input name='txtCodAsignacion' type='hidden' id='txtCodAsignacion' value="+CodAsignacion+" ><input name='txtCodDatosComplementarios' type='hidden' id='txtCodDatosComplementarios' value="+rs.getString(2)+" ><td colspan='4' class='style11' id='cabecera2'><div align='center'> ESTUDIO&gt;&gt; "+rs.getString(3)+" </div></td></tr></table>");
				}				
				rs.getStatement().getConnection().close();
				rs1=mcp.ObtenerResultadosEstudiosActu(CodAsignacion);
				 out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
				while(rs1.next()){					
					contador=contador+1;
					 out.println("<tr><td width='18%'>"+rs1.getString(5)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs1.getString(1)+" ></td>");
					 
					 out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' >"+rs1.getString(6)+"</textarea></td></tr>");
					 
				}
				 out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/* 
			 
			 out.println("<table width='100%' class='style2' border='1'><tr><td colspan='2'></td></tr>");
			 
			 out.println("<tr><td width='18%'>"+rs.getString(1)+"<input name='txtCodResultado' type='hidden' id='txtCodResultado' value="+rs.getString(4)+" ></td>");
			 
			 out.println("<td width='82%'><textarea name='txtRespuesta' id='txtRespuesta' cols='60' rows='6' onkeyup='ActualizarResultados()' >"+rs.getString(5)+"</textarea> "+rs.getString(6)+"</td></tr>");
			 
			 out.println("<input name='txtTotal' type='hidden' id='txtTotal' value="+contador+" ></table>");
			 */
		}
		/*********************************************************************/
		
		if(va.equals("5BR")){
			
			mcp.CrearDatosComplementarios(Protocolo, TipoEspecimen, DiagnosticoClinico, Medico, FechaRecibo, FechaEntrega, CodAsignacion);
			rs=mcp.ObtenerDatosComplementarios(CodAsignacion);
			String DesMacro=req.getParameter("DesMacro"); String DescMicro1=req.getParameter("DescMicro1");
			String DescMicro2=req.getParameter("DescMicro2"); String ObserLleno=req.getParameter("ObserLleno"); 
			
			String RE1=req.getParameter("RE1");String  RE2=req.getParameter("RE2"); 
			String RE3=req.getParameter("RE3"); String RE4=req.getParameter("RE4");
			String RE5=req.getParameter("RE5"); String RE6=req.getParameter("RE6");
			String RE7=req.getParameter("RE7"); String RE8=req.getParameter("RE8");
			String RE9=req.getParameter("RE9"); String RE10=req.getParameter("RE10"); 
			
			String IT1=req.getParameter("IT1"); String IT2=req.getParameter("IT2"); 
			String IT3=req.getParameter("IT3"); String IT4=req.getParameter("IT4"); 
			String IT5=req.getParameter("IT5"); String IT6=req.getParameter("IT6"); 
			String IT7=req.getParameter("IT7"); String IT8=req.getParameter("IT8"); 
			String IT9=req.getParameter("IT9"); String IT10=req.getParameter("IT10"); 
			
			String PTR1=req.getParameter("PTR1"); String PTR2=req.getParameter("PTR2"); 
			String PTR3=req.getParameter("PTR3"); String PTR4=req.getParameter("PTR4"); 
			String PTR5=req.getParameter("PTR5"); String PTR6=req.getParameter("PTR6"); 
			String PTR7=req.getParameter("PTR7"); String PTR8=req.getParameter("PTR8"); 
			String PTR9=req.getParameter("PTR9"); String PTR10=req.getParameter("PTR10"); 
			String Diagnostico=req.getParameter("Diagnostico");
			try {
				if(rs.next()){
					mcp.CrearBiopsiaRenal(rs.getString(1), DesMacro, DescMicro1, DescMicro2,
							ObserLleno, RE1, RE2, RE3, RE4, RE5, RE6, RE7, RE8, RE9, RE10,
							IT1, IT2, IT3, IT4, IT5, IT6, IT7, IT8, IT9, IT10, 
							PTR1, PTR2, PTR3, PTR4, PTR5, PTR6, PTR7, PTR8, PTR9, PTR10, 
							Diagnostico);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String Est="2";
			mcp.ActualizarAsignacion(CodAsignacion,Est);
			
//			mcp.CrearReporteSimple(CodigoEstudio, CodPac, Protocolo, TipoEspecimen, DiagnosticoClinico, Medico, FechaRecibo, FechaEntrega, DescMacro, DescMicro, ConcDiagn, FechaSistema, HoraSistema, CodUsuario);
			out.print("Ingreso Exitoso.");
		}
		
		
		if(va.equals("5")){
			String Est="2";
			mcp.CrearDatosComplementarios(Protocolo, TipoEspecimen, DiagnosticoClinico, Medico, FechaRecibo, FechaEntrega, CodAsignacion);
			mcp.ActualizarAsignacion(CodAsignacion,Est);
			
//			mcp.CrearReporteSimple(CodigoEstudio, CodPac, Protocolo, TipoEspecimen, DiagnosticoClinico, Medico, FechaRecibo, FechaEntrega, DescMacro, DescMicro, ConcDiagn, FechaSistema, HoraSistema, CodUsuario);
			out.print("Ingreso Exitoso.");
		}
		if(va.equals("6")){ 			
			out.println("<table class='style6' width='100%' border='0'><tr><td><input name='txtnombre' type='text' id='txtnombre' value='' size='100' onkeyup='autocompletaNombrePaciente()'  /></td><td><input name='btnBuscar2' class='boton4' type='button' id='btnBuscar2' value='Buscar' onclick='buscar()' /></td></tr><tr><td><div id='sugerencias1'></div></td><td>&nbsp;</td></tr><tr><td width='548'><input name='cbafiliacion' type='hidden' id='cbafiliacion'  /><input name='txtnumdoc' type='hidden' id='txtnumdoc'  /></td><td width='205'>&nbsp;</td></tr><tr><td colspan='2'></td></tr></table>");
		}
		if(va.equals("7")){
			out.println("<table class='style6' width='100%' border='0' ><tr>" +
					"<td width='186'>TIPO DE DOCUMENTO</td><td width='162'><label>" +
					"<select name='cbafiliacion' size='1' id='cbafiliacion'>");
					try {
						Conexion con2;
						java.sql.Statement st3 = null;  
						con2 = new Conexion();
						st3 = con2.conn.createStatement();
						rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");
						while(rs3.next()){
							out.print("<option value="+rs3.getString(1)+">"+rs3.getString(1)+"</option>");
						}
						rs3.getStatement().getConnection().close();
						st3.close();
						con2.cerrar();
					}catch(SQLException e){
								  
						System.out.println(e);
						out.println("no se pudo realizar la conexion!<br/>"); 
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.println("</select>" +
					"</label></td><td width='101'><label>NUMERO</label></td><td width='432'><label><input name='txtnumdoc' type='text' id='txtnumdoc' onkeyup='autocompleta()' size='30' /></label></td><td width='328'><input name='btnBuscar' class='boton4' type='button' id='btnBuscar' value='Buscar' onclick='buscar()' /></td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td><div id='sugerencias'></div></td><td>&nbsp;</td><tr><td colspan='5'></td></tr></table>");
		}
		if(va.equals("6.7")){
			String NombrePaciente="";
			try {
				out.print("  <table width='100%' border='1' class='style6'><tr><td colspan='3'><div align='center' id='cabecera2' class='style11'>Registros Encontrados</div></td></tr>");
				rs=mcp.ObtenerEstudiosPaciente(TipoDocumento, Identificacion);
				if(rs.next()){
					NombrePaciente=rs.getString(5)+" "+rs.getString(6);
				}
				rs.getStatement().getConnection().close();
				out.print("<tr><td width='12%'><div>Nombre Paciente</div></td><td width='88%' colspan='2'>"+NombrePaciente+"</td></tr></table>");
				out.print("<table width='100%' border='1' class='style6' ><tr><td width='77%'><div>Nombre Estudio</div></td><td width='12%'><div>Fecha de Registro</div></td><td width='11%'><div>Hora de Registro</div></td></tr>");
				rs1=mcp.ObtenerEstudiosPaciente(TipoDocumento, Identificacion);
				while(rs1.next()){
					out.print("<tr><td><a  href='#' onclick='mostarReporteEstudio("+rs1.getString(1)+","+rs1.getString(7)+")'>"+rs1.getString(2)+"</a></td><td>"+rs1.getString(3)+"</td><td>"+rs1.getString(4)+"</td></tr>");
				}
				rs1.getStatement().getConnection().close();
				out.print("</table>");				
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		/*if(va.equals("10")){			
			out.print("<tr><td><div align='center'>Seleccione Formato <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name='txtCodFormato' id='txtCodFormato'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mcp.ObtenerFormatos();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='btnVerFormato' type='button' id='btnVerFormato' class='boton4' value='Asignar' onclick='AsignarFormato()' /></label></div></td></tr>");

		}*/
		
	}

}
