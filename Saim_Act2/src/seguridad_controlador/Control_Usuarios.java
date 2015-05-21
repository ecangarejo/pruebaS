/**
 * CONTROLADOR: Control_Usuarios se encuentra el proceso para
 * validar si el usuario existe y tambien para cambiar la contraseña del usuario. 
*/
package seguridad_controlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agm_metodo.MetodoMedicos;

import seguridad_logica.MetodoOpcionesAutorizadas;
import seguridad_logica.Metodo_Usuario;


/**
 * Servlet implementation class Control_Usuarios
 */
public class Control_Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Metodo_Usuario mu=new Metodo_Usuario();
		MetodoOpcionesAutorizadas au=new MetodoOpcionesAutorizadas();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va=req.getParameter("valor");
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String CodUsuario=req.getParameter("CodUsuario");
		String NuevaContra=req.getParameter("NuevaContra");
		String AntiContra=req.getParameter("AntiContra");
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rsp=null;
		ResultSet rsl=null;
		String nom=req.getParameter("nom");
		String ape=req.getParameter("ape");
		String dire=req.getParameter("dire");
		String tel=req.getParameter("tel");
		String mail=req.getParameter("mail");
		String pro=req.getParameter("pro");
		String ocu=req.getParameter("ocu");
		String ced=req.getParameter("ced");
		String contra=req.getParameter("contra");
		String usu=req.getParameter("usu");
		String estado=req.getParameter("estado");
		String CodigoMenu=req.getParameter("CodigoMenu");
		String tipoDocumento=req.getParameter("tipoDocumento");
		String CodigoOpcMenu=req.getParameter("CodigoOpcMenu");
		String CodigoAsignacion=req.getParameter("CodigoAsignacion");
		String CodigoOpcionDispo=req.getParameter("CodigoOpcionDispo");
		String CodigoDatosPersonales=req.getParameter("CodigoDatosPersonales");
		
		
		if(va.equals("CLU")){			
			try {
				rs=mu.ListarUsuarios();
				out.print("<table width='100%' border='1'><tr bgcolor='#E6E6E6'><td>NOMBRES Y APELLIDOS</td><td>AMBITO</td><td>ESPECIALIDAD o PROFESION</td><td>TIPO DOCUMENTO</td><td>NUMERO DOCUMENTO</td><td>USUARIO</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString("Nombre_Usuario")+"</td><td>"+rs.getString("profesion")+"</td><td>"+rs.getString("ocupacion")+"</td><td>"+rs.getString("tipoDocumento")+"</td><td>"+rs.getString("numeroDocumento")+"</td><td>"+rs.getString("usuario")+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("cp")){
			// crear profesion
			out.print("<table width='100%'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2' >CREAR PROFESION</div></td></tr>");
			out.print("<tr><td>Nombre de la Profesion:<input name='txtProfesion' type='text' class='style3' id='txtProfesion'  size='50' maxlength='50'  /><input name='Button' type='button' value='Crear' onclick='GuardarProfesion()' /></td><td></td></tr>");
			out.print("</table>");
		}
		
		if(va.equals("cc")){
			//crear cargo
			out.print("<table width='100%'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2' >CREAR CARGO</div></td></tr>");
			out.print("<tr><td>Nombre del Cargo:<input name='txtCargo' type='text' class='style3' id='txtCargo'  size='50' maxlength='50'  /><input name='Button' type='button' value='Crear' onclick='GuardarCargo()' /></td><td></td></tr>");
			out.print("</table>");
		}
		
		if(va.equals("rpc")){
			//relacion profesion-cargo
			
			out.print("<table width='100%'><tr><td colspan='2'><div align='center' class='style11' id='cabecera2' >RELACION CARGO-PROFESION</div></td></tr>");
			out.print("<tr><td>Nombre del Cargo:<input name='txtCargo' type='text' class='style3' id='txtCargo'  size='50' maxlength='50'  /></td></tr>");
			out.print("</table>");
		}
		
		if(va.equals("ActPer")){
			try {
				
				mu.ActualizarDatosPersonalesUsuario(ape, dire, mail, nom, ocu, pro, tel, ced, estado, NumeroDocumento, tipoDocumento, CodigoDatosPersonales);
				MetodoMedicos mms= new MetodoMedicos();
				String Tconsulta=req.getParameter("Tconsulta");
				String Activo=req.getParameter("Activo");
				String CodOcupacion=req.getParameter("CodOcupacion");
			
				if(tipoDocumento.equals("Cedula Ciudadania")){tipoDocumento="CC";}
				if(tipoDocumento.equals("Pasaporte")){tipoDocumento="PA";}
				if(tipoDocumento.equals("Cedula Extranjeria")){tipoDocumento="CE";}	
				String Estado="";
				rs2=mu.BuscarTiempoMedico(tipoDocumento,NumeroDocumento,Estado);
				if(rs2.next()){
					if(Activo.equals("1")){
						System.out.println("Entro a activo y se va actualizar el medico en agm_medico");
						//medico activo de consulta externa
						//2. si existe se actualizan los datos en la tabla agm_medico
						mms.ActualizarMedico(tipoDocumento, NumeroDocumento, nom, ape, ced, pro, CodOcupacion, dire, tel, rs2.getString("codigo"),Tconsulta);
						mms.ActualizarEstadoMedico(rs2.getString("codigo"),"0");
					}
					if(Activo.equals("0")){
						System.out.println("Entro a inactivo y no se actualiza en agm_medico");
						//medico inactivo de consulta externa
						//2. se le cambia el estado a 1 en la tabla agm_medico
						mms.ActualizarEstadoMedico(rs2.getString("codigo"),"1");
					}
				}else{
					if(Activo.equals("1")){
						//medico activo de consulta externa
						//2. no existe 
						//2.1 crear el registro en la tabla agm_medico
						System.out.println("se crea en agm_medico");
						mms.CrearMedico(tipoDocumento, NumeroDocumento, nom, ape, ced, pro, CodOcupacion, dire, tel,Tconsulta);
					}
					if(Activo.equals("0")){
						//medico inactivo de consulta externa
						//no se hace nada
				
					}
				}
				rs2.getStatement().getConnection().close();		
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("ActUsu")){			
			try {
				rs=mu.BuscarContraUsu(CodUsuario);
				String CompContra="";
				if(rs.next()){
					CompContra=rs.getString(1);
					//out.println("CompContra La Actual en la DB= "+CompContra+" AntiContra La Que se Digito Como Antigua = "+AntiContra+"--> ");
					if(CompContra.equals(AntiContra)){
						//out.print("Se Puede Actualizar El usuario="+CodUsuario+" Con Este Password "+NuevaContra);
						mu.ActualizarContraUsuario(CodUsuario, NuevaContra);
						out.print("1");
					}
					else{
						out.print("El Password Anterior No Coincide Con El Digitado.\n Intente Otra Vez.");
					}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("PerMo")){			
			try {
				rs=mu.TodosMenus();
				out.print("<table width='100%' class='style6' border='1'><tr><td width='18%'><div align='center' class='style11' id='cabecera2' >Menus</div></td><td width='29%'><div align='center' class='style11' id='cabecera2' >Opciones de Menu</div></td><td width='28%'><div align='center' class='style11' id='cabecera2' ><input type='checkbox'  name='allPD' id='allPD' onclick='checkAllPD();'  />Permisos Disponibles</div></td><td width='25%'><div align='center' class='style11' id='cabecera2' ><input type='checkbox'  name='allPA' id='allPA' onclick='checkAllPA();'  />Permisos Autorizados</div></td></tr>");
				out.print("<tr><td><div id='Menus'><select name='cmbMenu' size='10' multiple class='style3' id='cmbMenu' onChange='VerOpcionesDisponibles()' >");
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></div></td><td><div id='OpMenus' align='center'></div></td><td><div id='PerDispo'></div></td><td><div id='PerAuto'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("OpcMenu")){			
			try {
				rs=mu.OpcionesMenu(CodigoMenu);
				out.print("<select name='cmbOpcMenu' size='10' multiple class='style3' id='cmbOpcMenu' onChange='VerOpcionesMenu()'  >");
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("OpcDisMenu")){			
			try {
				int ContOpcMenuDispo=0;
				rs=mu.OpcionesDisponibleOpcMenu(CodigoOpcMenu, CodUsuario);
				out.print("<table>");
				while(rs.next()){
					out.print("<tr><td class='style3'><label><input name='chkDisponibleOpcMenu' type='checkbox' value="+rs.getString(1)+" id='chkDisponibleOpcMenu' />"+rs.getString(2)+"</label></td></tr>");
					ContOpcMenuDispo=ContOpcMenuDispo+1;
				}
				out.print("</table>");
				out.print("<table border='1' width='100%' ><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarRelacionPermisos()'><input name='txtContadorOpcMenDisp' type='hidden' id='txtContadorOpcMenDisp' value="+ContOpcMenuDispo+" /></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("OpcAutMenu")){
			
			try {
				int ContOpcMenuAuto=0;
				rs=mu.OpcionesAutorizadasOpcMenu(CodigoOpcMenu, CodUsuario);
				out.print("<table>");
				while(rs.next()){
					out.print("<tr><td class='style3'><label><input name='chkAutorizadoOpcMenu' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadoOpcMenu' />"+rs.getString(2)+"</label></td></tr>");
					ContOpcMenuAuto=ContOpcMenuAuto+1;
				}
				out.print("</table>");
				out.print("<table border='1' width='100%' ><tr><td><div align='center'><input title='Omitir' name='btnAsignar' type='button' id='btnAsignar' value='&lt;&lt;' onClick='OmitirRelacionPermisos()'><input name='txtContadorOpcMenAuto' type='hidden' id='txtContadorOpcMenAuto' value="+ContOpcMenuAuto+" /></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("AsiPer")){
			mu.InsertarPermisosUsuario(CodigoOpcionDispo, CodUsuario);
		}
		
		
		if(va.equals("OmiPer")){
			mu.OmitirAsigacionPermisos(CodigoAsignacion);
		}
		
		/********************************************************************************/
		if(va.equals("PerFor")){		
			try {
				out.print("<table width='100%'><tr><td width='50%'>");
				int ContAutorizados=0;
				int ContDisponibles=0;				
				rsp=au.BuscarFormatosActivosHosp(CodUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Formatos de Atencion </div></td></tr>");
				out.print("<tr><td><div align='center'>Opciones Disponibles</div></td><td width='50%'><div align='center'>Opciones Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				if(rsp.next()){
					rs1=au.BuscarFormatosDisponiblesHosp(CodUsuario);
					while(rs1.next()){
						out.print("<tr><td><label><input name='chkDisponible' type='checkbox' value="+rs1.getString(1)+" id='chkDisponible' class='style3' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rs1.getStatement().getConnection().close();
				}
				else{
					rsl=au.TodosFormatos();
					while(rsl.next()){
						out.print("<tr><td><label><input name='chkDisponible' type='checkbox' value="+rsl.getString(1)+" id='chkDisponible' class='style3' />"+rsl.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rsl.getStatement().getConnection().close();
				}
				rsp.getStatement().getConnection().close();
				rs=au.BuscarFormatosActivosHosp(CodUsuario);
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadas' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadas' class='style3'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizados=ContAutorizados+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarRelacionFormatos()'><input name='txtConDisponibles' type='hidden' id='txtConDisponibles' value="+ContDisponibles+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoFormato()'><input name='txtContAutorizadas' type='hidden' id='txtContAutorizadas' value="+ContAutorizados+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en PermisosPagina>>valor=Hosp" +e);
				e.printStackTrace();
			}
			///////////////////////////////////////////////////////////////////// 			
			
			try {
				out.print("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td width='50%'>");
				int ContAutorizadosCE=0;
				int ContDisponiblesCE=0;				
				rsp=au.BuscarFormatosActivosCE(CodUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Formatos de Consulta Externa </div></td></tr>");
				out.print("<tr><td><div align='center'>Opciones Disponibles</div></td><td width='50%'><div align='center'>Opciones Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				if(rsp.next()){
					rs1=au.BuscarFormatosDisponiblesCE(CodUsuario);
					while(rs1.next()){
						out.print("<tr><td><label><input name='chkDisponibleCE' type='checkbox' value="+rs1.getString(1)+" id='chkDisponibleCE' class='style3' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponiblesCE=ContDisponiblesCE+1;
					}
					out.print("</table></td><td><table>");
					rs1.getStatement().getConnection().close();
				}
				else{
					rsl=au.TodosFormatos();
					while(rsl.next()){
						out.print("<tr><td><label><input name='chkDisponibleCE' type='checkbox' value="+rsl.getString(1)+" id='chkDisponibleCE' class='style3'  />"+rsl.getString(2)+"</label></td></tr>");
						ContDisponiblesCE=ContDisponiblesCE+1;
					}
					out.print("</table></td><td><table>");
					rsl.getStatement().getConnection().close();
				}
				rsp.getStatement().getConnection().close();	
				rs=au.BuscarFormatosActivosCE(CodUsuario);	
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadasCE' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadasCE' class='style3'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizadosCE=ContAutorizadosCE+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarRelacionFormatosCE()'><input name='txtConDisponiblesCE' type='hidden' id='txtConDisponiblesCE' value="+ContDisponiblesCE+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoFormatoCE()'><input name='txtContAutorizadasCE' type='hidden' id='txtContAutorizadasCE' value="+ContAutorizadosCE+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en PermisosPagina>>valor=Hosp" +e);
				e.printStackTrace();
			}
			out.print("</td></tr></table>");
		}
		/******************************************************************************************/
		if(va.equals("PerHc")){
			int ContAutorizados=0;
			int ContDisponibles=0;
			try {
				rs=au.BuscarPermisosActivosHC(CodUsuario);			
				rs1=au.BuscarPermisosDisponiblesHC(CodUsuario);
				rsp=au.BuscarPermisosActivosHC(CodUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Item Historia Clinica </div></td></tr>");
				out.print("<tr><td><div align='center'>Opciones Disponibles</div></td><td width='50%'><div align='center'>Opciones Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				if(rsp.next()){
					while(rs1.next()){
						out.print("<tr><td><label><input name='chkDisponibleHC' type='checkbox' value="+rs1.getString(1)+" id='chkDisponibleHC' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rs1.getStatement().getConnection().close();
				}
				else{
					rsl=au.TodosPermisosHC();
					while(rsl.next()){
						out.print("<tr><td><label><input name='chkDisponibleHC' type='checkbox' value="+rsl.getString(1)+" id='chkDisponibleHC' />"+rsl.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
					}
					out.print("</table></td><td><table>");
					rsl.getStatement().getConnection().close();
				}
				rsp.getStatement().getConnection().close();				
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadasHC' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadasHC'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizados=ContAutorizados+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarRelacionPermisoHC()'><input name='txtConDisponiblesHC' type='hidden' id='txtConDisponiblesHC' value="+ContDisponibles+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoHC()'><input name='txtContAutorizadasHC' type='hidden' id='txtContAutorizadasHC' value="+ContAutorizados+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en PermisosPagina>>valor=Hosp" +e);
				e.printStackTrace();
			}
		}
		
		
		/******************************************************************************************/
		if(va.equals("PerBo")){
			int ContAutorizados=0;
			int ContDisponibles=0;
			try {
				rs1=mu.BodegasDisponibles(CodUsuario);			
				rs=mu.BodegasAutorizadas(CodUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Bodegas del Sistema </div></td></tr>");
				out.print("<tr><td><div align='center'>Bodegas Disponibles</div></td><td width='50%'><div align='center'>Bodegas Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				while(rs1.next()){
					
						out.print("<tr><td><label><input name='chkDisponibleBo' type='checkbox' value="+rs1.getString(1)+" id='chkDisponibleHC' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
				}
				rs1.getStatement().getConnection().close();		
				out.print("</table></td><td width='50%'><table>");
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadasBo' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadasHC'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizados=ContAutorizados+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarBodega()'><input name='txtConDisponiblesBo' type='hidden' id='txtConDisponiblesBo' value="+ContDisponibles+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoBo()'><input name='txtContAutorizadasBo' type='hidden' id='txtContAutorizadasBo' value="+ContAutorizados+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en PermisosPagina>>valor=Bod" +e);
				e.printStackTrace();
			}
		}

	if(va.equals("PerPro")){
			int ContAutorizados=0;
			int ContDisponibles=0;
			try {
				rs1=mu.ProgramacionesDisponibles(CodUsuario);			
				rs=mu.ProgramacionesAutorizadas(CodUsuario);
				out.print("<table width='100%' class='style6' border='1' ><tr><td colspan='3'><div align='center' class='style11' id='cabecera2'>Programaciones del Sistema </div></td></tr>");
				out.print("<tr><td><div align='center'>Programaciones Disponibles</div></td><td width='50%'><div align='center'>Programaciones Autorizadas</div></td></tr>");
				out.print("<tr><td width='50%'><table>");
				while(rs1.next()){
					
						out.print("<tr><td><label><input name='chkDisponiblePro' type='checkbox' value="+rs1.getString(1)+" id='chkDisponibleHC' />"+rs1.getString(2)+"</label></td></tr>");
						ContDisponibles=ContDisponibles+1;
				}
				rs1.getStatement().getConnection().close();		
				out.print("</table></td><td width='50%'><table>");
				while(rs.next()){
					out.print("<tr><td><label><input name='chkAutorizadasPro' type='checkbox' value="+rs.getString(1)+" id='chkAutorizadasHC'  />"+rs.getString(2)+"</label></td></tr>");
					ContAutorizados=ContAutorizados+1;
				}
				out.print("</table></td></tr><tr><td><div align='center'><input title='Asignar' name='btnAsignar' type='button' id='btnAsignar' value='&gt;&gt;' onClick='GuardarProgramacion()'><input name='txtConDisponiblesPro' type='hidden' id='txtConDisponiblesPro' value="+ContDisponibles+"></div></td>");
				out.print("<td><div align='center'><input title='Omitir' name='btnOmitir' type='button' id='btnOmitir' value='&lt;&lt;' onClick='OmitirPermisoPro()'><input name='txtContAutorizadasPro' type='hidden' id='txtContAutorizadasPro' value="+ContAutorizados+"></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error en Control Usuarios>>valor=Progrmacion" +e);
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("GuardarPro")){
			String CodigoUsuario=req.getParameter("CodigoUsuario");
			String CodigoPermiso=req.getParameter("CodigoPermiso");
			mu.InsertarProgramacion(CodigoPermiso,CodigoUsuario);
			//out.print("Formatos Relacionados Con Exito");
		}
		
		if(va.equals("OmitirPro")){
			String CodigoUsuario=req.getParameter("CodigoUsuario");
			mu.OmitirPermisoPro(CodigoAsignacion,CodigoUsuario);
		}
		/******************************************************************************************/
		if(va.equals("-1")){
			try {
				rs=mu.BuscarDatosPersonalesUsuario(NumeroDocumento, tipoDocumento);
				if(rs.next()){
					String CodDatosPersonales=rs.getString(1);
					String Estado=rs.getString(10);
					out.print("<table width='100%' align='center' class='style6'><tr id='cabecera2' class='style11'><td colspan='6' align='center' ><div><span >DATOS DEL USUARIO</span><input name='txtCodigoDatosPersonales' type='hidden' id='txtCodigoDatosPersonales' value='"+rs.getString(1)+"'></div> </td></tr>");
					out.print("<tr><td width='146'><div class='style2'>TARJETA PROFESIONAL</div></td><td width='205'><label><input name='txtcedula' type='text' class='style3' id='txtcedula' size='30' maxlength='15' value='"+rs.getString(9)+"' /><span class='style8'>*</span></label></td><td width='170'><div class='style2'>NOMBRE</div></td><td width='203'><input name='txtnombre' type='text' class='style3' id='txtnombre' onkeyup='this.value=this.value.toUpperCase()' size='28' maxlength='70' value='"+rs.getString(2)+"' /><span class='style8'>*</span></td><td width='95'><div class='style2'>APELLIDOS</div></td><td width='247'><input name='txtapellido' type='text' class='style3' id='txtapellido' onkeyup='this.value=this.value.toUpperCase()' size='30' maxlength='70' value='"+rs.getString(3)+"' /><span class='style8'>*</span></td></tr>");
					out.print("<tr><td><div class='style2'>PROFESION</div></td>" +
							"<td><label><select name='txtprofesion' id='txtprofesion' onchange='CambiarOcupacion()' ><option value='"+rs.getString(7)+"' selected='selected'>"+rs.getString(7)+"</option>");
								rs2=mu.BuscarProfesion();
								while(rs2.next()){
									out.print("<option value="+rs2.getString("codigo")+">"+rs2.getString("nombre")+"</option>");
								}
								rs2.getStatement().getConnection().close();
									out.print("</select><span class='style8'>*</span></label></td>" +
									"<td><div class='style2'>OCUPACION</div></td><td> " +
									"<div id='Ocupa'> ");
									//if(rs.getString(8).equals("")){
									//	out.print("<select name='txtocupacion' id='txtocupacion'><option value=' ' selected=''>Seleccione</option></select>");
									//}else{
									rs2=mu.BuscarEspecialidades(rs.getString(8));
									if(rs2.next()){
										out.print("<select name='txtocupacion' id='txtocupacion'><option value='"+rs2.getString("codigo")+"' selected=''>"+rs.getString(8)+"</option></select>");
									}else{
										out.print("<select name='txtocupacion' id='txtocupacion'><option value='0' selected=''>"+rs.getString(8)+"</option></select>");
									}
									rs2.getStatement().getConnection().close();
										
									//}
												//"<input name='txtocupacion' type='text' class='style3' id='txtocupacion' onkeyup='this.value=this.value.toUpperCase()' size='28' maxlength='50' value='"+rs.getString(8)+"' /> " +
									out.print("</div></td>");
									if(tipoDocumento.equals("Cedula Ciudadania")){tipoDocumento="CC";}
									if(tipoDocumento.equals("Pasaporte")){tipoDocumento="PA";}
									if(tipoDocumento.equals("Cedula Extranjeria")){tipoDocumento="CE";}	
									String EstadoSQL="AND am.estado='0'";
									rs2=mu.BuscarTiempoMedico(tipoDocumento,NumeroDocumento,EstadoSQL);
									if(rs2.next()){
										out.print("<td><input name='chkMedCex' type='checkbox' id='chkMedCex' onClick='MedicoCEX()' checked='true' >Consulta Externa</td>" +
											"<td id='DetMedCex' >Tiempo Consulta<input name='txtTiempoConsulta' type='text' id='txtTiempoConsulta' value='"+rs2.getString("tiempo_consulta")+"' /><input name='Activo' type='hidden' id='Activo' value='1' /></td>");
										//out.print("<option value="+rs2.getString("codigo")+">"+rs2.getString("nombre")+"</option>");
									}else{
										out.print("<td><input name='chkMedCex' type='checkbox' id='chkMedCex' onClick='MedicoCEX()'  >Consulta Externa</td>" +
											"<td id='DetMedCex' ><input name='txtTiempoConsulta' type='hidden' id='txtTiempoConsulta' value='NA' /><input name='Activo' type='hidden' id='Activo' value='0' /></td>");
									}
									rs2.getStatement().getConnection().close();
									
									
									out.print("</tr>");
					out.print("<tr><td><div class='style2'>EMAIL</div></td><td><label><input name='txtemail' type='text' class='style3' id='txtemail' size='28' maxlength='30' value='"+rs.getString(6)+"' /></label></td><td><div class='style2'>TELEFONO</div></td><td><label><input name='txttelefono' type='text' class='style3' id='txttelefono' size='30' maxlength='10' value='"+rs.getString(5)+"' /><span class='style8'>*</span></label></td><td><div class='style2'>DIRECCION</div></td><td><input name='txtdireccion' type='text' class='style3' id='txtdireccion' size='28' maxlength='80' value='"+rs.getString(4)+"' /><span class='style8'>*</span></td></tr>" +
							"<tr><td><div class='style2'>ESTADO</div></td><td colspan='3'><label> " +
								"<select name='cmbestado' class='style3' id='cmbestado'>");
								if(Estado.equals("0")){
									out.print("<option value='0' selected='selected'>ACTIVO</option>" );
									out.print("<option value='1'>INACTIVO</option>" );
								}
								if(Estado.equals("1")){
									out.print("<option value='1' selected='selected'>INACTIVO</option>" );
									out.print("<option value='0'>ACTIVO</option>" );
								}
								
								out.print("</select><span class='style8'>*</span></label></td><td><input name='btnActualizarDatDemo' type='button' id='btnActualizarDatDemo' value=' Actualizar Datos' onclick='ActualizarDatosUsuario()' class='boton4' /></td></tr>");

					rs1=mu.BuscarDatosUsuario(CodDatosPersonales);
					if(rs1.next()){						
						out.print("<tr><td colspan='6' align='center' id='cabecera2' class='style11'>USUARIO Y CONTRASE&Ntilde;A<input name='txtCodUsuarioAct' type='hidden' id='txtCodUsuarioAct' value="+rs1.getString(1)+" /><input type='hidden' id='CodUsuario' value="+CodDatosPersonales+" /></td></tr>");
						out.print("<tr><td><div class='style2'>USUARIO</div></td><td><input name='txtusuario' type='text' class='style3' id='txtusuario' onKeyUp='this.value=this.value.toUpperCase()' size='30' maxlength='50' value='"+rs1.getString(2)+"'  /><span class='style8'>*</span></td></tr>");
								out.print("<tr><td><div class='style2'>ANTIGUA CONTRASE&Ntilde;A</div></td><td><label><input name='txtcontrasena' type='password' class='style3' id='txtcontrasena' onKeyUp='this.value=this.value.toUpperCase()' size='32' maxlength='50' /><span class='style8'>*</span></label></td><td><div class='style2'>NUEVA CONTRASE&Ntilde;A</div></td><td><input name='txtrepcontra' type='password' class='style3' id='txtrepcontra' onKeyUp='this.value=this.value.toUpperCase()' size='30' maxlength='50' /><span class='style8'>*</span></td><td>&nbsp;</td><td><input name='btnActualizarUsuario' type='button' id='btnActualiazarUsuario' value=' Actualizar Usuario' onclick='ActualizarUsuyContra()' class='boton4' /></td></tr>");
								out.print("<tr><td colspan='6'><label><span class='style8'>Campos Requeridos *</span></label></td></tr>");
								out.print("<tr><td colspan='6'><div></div></td></tr></table>");
						
								out.print("<table width='100%' border='0'><tr><td width='100%'><div id='tabsJ'><ul>"+
										"<li><a href='#' onclick='pestanas(1);'><span>Permisos Modulos</span></a></li>"+
										"<li><a href='#' onclick='pestanas(2);'><span>Permisos Formatos</span></a></li>"+
										"<li><a href='#' onclick='pestanas(3);'><span>Permisos Historia Clinica</span></a></li>"+
										"<li><a href='#' onclick='pestanas(4);'><span>Permisos de Bodegas</span></a></li>"+
										"<li><a href='#' onclick='pestanas(5);'><span>Permisos de Programacion</span></a></li>"+
										"<li><a href='#' onclick='pestanas(6);'><span>Firma</span></a></li>"+
								"</ul></div><div id='ContPestanas' class='style3'></div></td></tr></table>");
					
					}
					rs1.getStatement().getConnection().close();
				}
				else{
					out.print("<table width='100%' align='center' class='style6'><tr id='cabecera2' class='style11'><td colspan='6' align='center' ><div><span >DATOS DEL USUARIO</span></div> </td></tr>");
					out.print("<tr><td width='146'><div class='style2'>TARJETA PROFESIONAL</div></td><td width='205'><label><input name='txtcedula' type='text' class='style3' id='txtcedula' size='30' maxlength='15' /><span class='style8'>*</span></label></td><td width='170'><div class='style2'>NOMBRE</div></td><td width='203'><input name='txtnombre' type='text' class='style3' id='txtnombre' onkeyup='this.value=this.value.toUpperCase()' size='28' maxlength='70' /><span class='style8'>*</span></td><td width='95'><div class='style2'>APELLIDOS</div></td><td width='247'><input name='txtapellido' type='text' class='style3' id='txtapellido' onkeyup='this.value=this.value.toUpperCase()' size='30' maxlength='70' /><span class='style8'>*</span></td></tr>");
					out.print("<tr><td><div class='style2'>PROFESION</div></td><td><label><select name='txtprofesion' id='txtprofesion' onchange='CambiarOcupacion()'><option value='Seleccione' selected=''>Seleccione</option>");
					/*<option value='Seleccione'>Seleccione</option><option value='Medico General'>Medico General</option><option value='Especialista'>Especialista</option><option value='Enfermera'>Enfermera</option><option value='Fisioterapeuta'>Fisioterapeuta</option><option value='Administracion'>Administracion</option>*/
					rs2=mu.BuscarProfesion();
					while(rs2.next()){
						out.print("<option value="+rs2.getString("codigo")+">"+rs2.getString("nombre")+"</option>");
					}
					rs2.getStatement().getConnection().close();
					out.print("</select><span class='style8'>*</span></label></td>" +
							"<td><div class='style2'>OCUPACION</div></td>" +
							"<td>" +
							"<div id='Ocupa'> " +
							"<select name='txtocupacion' id='txtocupacion'><option value='Seleccione' selected=''>Seleccione</option></select>"+
							//"<input name='txtocupacion' type='text' class='style3' id='txtocupacion' onkeyup='this.value=this.value.toUpperCase()' size='28' maxlength='50'  />" +
							"</div> " +
							"</td>" +
							"<td><input name='chkMedCex' type='checkbox' id='chkMedCex' onClick='MedicoCEX()' >Consulta Externa</td><td id='DetMedCex'><input name='Activo' type='hidden' id='Activo' value='0' /></td></tr>");
					out.print("<tr><td><div class='style2'>EMAIL</div></td><td><label><input name='txtemail' type='text' class='style3' id='txtemail' size='28' maxlength='30' /></label></td><td><div class='style2'>TELEFONO</div></td><td><label><input name='txttelefono' type='text' class='style3' id='txttelefono' size='30' maxlength='10' /><span class='style8'>*</span></label></td><td><div class='style2'>DIRECCION</div></td><td><input name='txtdireccion' type='text' class='style3' id='txtdireccion' size='28' maxlength='80'  /><span class='style8'>*</span></td></tr>");
					
					out.print("<tr><td colspan='6' align='center' id='cabecera2' class='style11'>USUARIO Y CONTRASE&Ntilde;A</td></tr>");
					out.print("<tr><td><div class='style2'>USUARIO</div></td><td><input name='txtusuario' type='text' class='style3' id='txtusuario' onKeyUp='this.value=this.value.toUpperCase()' size='30' maxlength='50'  /><span class='style8'>*</span></td><td><div class='style2'>ESTADO</div></td><td colspan='3'><label> <select name='cmbestado' class='style3' id='cmbestado'><option value='Seleccione'>SELECCIONE</option><option value='0'>ACTIVO</option><option value='1'>INACTIVO</option></select><span class='style8'>*</span></label></td></tr>");
					out.print("<tr><td><div class='style2'>CONTRASE&Ntilde;A</div></td><td><label><input name='txtcontrasena' type='password' class='style3' id='txtcontrasena' onKeyUp='this.value=this.value.toUpperCase()' size='32' maxlength='50' /><span class='style8'>*</span></label></td><td><div class='style2'>REPETIR CONTRASE&Ntilde;A</div></td><td><input name='txtrepcontra' type='password' class='style3' id='txtrepcontra' onKeyUp='this.value=this.value.toUpperCase()' size='30' maxlength='50' /><span class='style8'>*</span></td><td>&nbsp;</td><td><input name='btningresar' type='button' id='btningresar' value=' Ingresar ' onclick='GuardarDatosPersonales()' class='boton4' /></td></tr>");
					out.print("<tr><td colspan='6'><label><span class='style8'>Campos Requeridos *</span></label></td></tr>");
					out.print("<tr><td colspan='6'><div id='OpcionSeguridad'></div></td></tr></table>");
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("COU")){
			try {
				String CodProfesion=req.getParameter("CodProfesion");
					
			out.println("<select name='txtocupacion' id='txtocupacion'><option value='Seleccione' selected=''>Seleccione</option>");
			rs2=mu.BuscarOcupacion(CodProfesion);			
				while(rs2.next()){
					out.print("<option value="+rs2.getString("codigo")+">"+rs2.getString("nombre_especialidad")+"</option>");
				}
				rs2.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			out.println("</select>");
		}
		if(va.equals("0")){				
			try {
				String usuario = null,cedula = null,codDatPer = null;
				
				rs2=mu.VerificarExistensiaNombreUsuario(usu);	
				if(rs2.next()){
					usuario=rs2.getString(1);
					out.print("Existe Un Usuario Con Este Mismo User.");
				}
				else{
					rs1=mu.VerificarExistensia(NumeroDocumento);
					if(rs1.next()){				
						cedula=rs1.getString(1);
						out.print("Ya Existe Un Usuario Con Este Documento");
					}
					else{
						if((cedula==null)&&(usuario==null)){
							mu.insertarusuario(ape, dire, mail, nom, ocu, pro, tel, ced,estado,NumeroDocumento,tipoDocumento);	
							rs=mu.obtenerCodigoDatosPersonales(ced);
							if(rs.next()){				
								codDatPer=rs.getString(1);			
							}
							rs.getStatement().getConnection().close();							 
							mu.insertarLogin(usu, contra, codDatPer);
							/*******************************************************************************/
							String Tconsulta=req.getParameter("Tconsulta");
							String CodOcupacion=req.getParameter("CodOcupacion");
							if(Tconsulta.equals("NA")){
								//no pertenece a consulta externa.
							}else{
						//*		//se ingresa como medico de consulta externa.
								//se ingresa a la tabla agm_medico
								if(tipoDocumento.equals("Cedula Ciudadania")){tipoDocumento="CC";}
								if(tipoDocumento.equals("Pasaporte")){tipoDocumento="PA";}
								if(tipoDocumento.equals("Cedula Extranjeria")){tipoDocumento="CE";}
								MetodoMedicos mms= new MetodoMedicos();
								mms.CrearMedico(tipoDocumento, NumeroDocumento, nom, ape, ced, pro, CodOcupacion, dire, tel,Tconsulta);
							}
							
							
							/********************************************************************************/
							rs3=mu.obtenerCodigousuario(codDatPer);
							if(rs3.next()){
								out.print("<table width='100%' border='0'><tr><td width='100%'><input name='txtCodUsuarioAct' type='hidden' id='txtCodUsuarioAct' value="+rs3.getString(1)+" /><div id='tabsJ'><ul>"+
										"<li><a href='#' onclick='pestanas(1);'><span>Permisos Modulos</span></a></li>"+
										"<li><a href='#' onclick='pestanas(2);'><span>Permisos Formatos</span></a></li>"+
										"<li><a href='#' onclick='pestanas(3);'><span>Permisos Historia Clinica</span></a></li>"+
										"<li><a href='#' onclick='pestanas(4);'><span>Permisos de Bodegas</span></a></li>"+
										"<li><a href='#' onclick='pestanas(5);'><span>Permisos de Programacion</span></a></li>"+
								"</ul></div><div id='ContPestanas'></div></td></tr></table>");
							}
							rs3.getStatement().getConnection().close();
						}
					}
					rs1.getStatement().getConnection().close();
				}
				rs2.getStatement().getConnection().close();				
			} catch (SQLException e) {
				System.out.println("Error "+e);
				e.printStackTrace();
			}
		}//fin va=0
		if(va.equals("1")){
			rs=mu.BuscarUsuario(NumeroDocumento);
			
			try {
				if(rs.next()){					
					out.print("<table width='100%' class='style6' align='center'>" +
							"		<tr align='center' class='style11' id='cabecera2'><td colspan='2'>Datos Del Usuario </td></tr>" +
							"		<tr><td>&nbsp;</td></tr>"+
							"		<tr><td align='right'>Nombre del Usuario:</td> <td class='style9'>"+rs.getString(4)+" "+rs.getString(5)+ "</td></tr>" +
							"		<tr><td align='right'>Identificacion:</td> <td class='style9'>"+NumeroDocumento+"</td></tr>" +
							"		<tr><td align='right'>Usuario:</td> <td class='style9'>"+rs.getString(2)+"</td></tr>" +
							"		<tr><td align='right'>Contrase&ntilde;a Actual:</td> <td><input name='txtAntiguaContra' type='password' id='txtAntiguaContra' /></td></tr>" +
							"		<tr><td align='right'>Nueva Contrase&ntilde;a:</td> <td><input name='txtNuevaContra' type='password' id='txtNuevaContra' /></td></tr>" +
							"		<tr><td align='right'>Repita Contrase&ntilde;a:</td> <td><input name='txtRepNuevaContra' type='password' id='txtRepNuevaContra' /></td></tr>" +
							"		<tr><td><span class='style4'><input name='txtViejaContra' type='hidden' id='txtViejaContra' value='"+rs.getString(3)+"'></span></td> <td><input name='btnActuUsuario' type='button' id='btnActuUsuario' class='boton4' value='Actualizar' onclick='cambiarContra()' /><span class='style4'></td></tr>" +
							"<tr><td><input name='txtCodUsuario' type='hidden' id='txtCodUsuario' value='"+rs.getString(1)+"'></span></td></tr>"+
							"</table>");
				}
				else{
					out.print("<table border='0'>");
					out.print("<tr><td>ERROR EN BUSQUEDA.</td></tr>");
					out.print("<tr><td>EL REGISTRO CON IDENTIFICACION "+NumeroDocumento+" NO EXISTE</td></tr>");
					out.print("<tr><td>INTENTE OTRA VEZ.</td></tr>");
					out.print("</table>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error valor igual=1 "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2")){
			mu.ActualizarContraUsuario(CodUsuario, NuevaContra);
			out.print("Contraseña Actualizada Con Exito.");
		}
		
		if(va.equals("GuardarBo")){
			String CodigoUsuario=req.getParameter("CodigoUsuario");
			String CodigoPermiso=req.getParameter("CodigoPermiso");
			mu.InsertarBodega(CodigoPermiso,CodigoUsuario);
			//out.print("Formatos Relacionados Con Exito");
		}
		
		if(va.equals("OmitirBo")){
			String CodigoUsuario=req.getParameter("CodigoUsuario");
			mu.OmitirPermisoBo(CodigoAsignacion,CodigoUsuario);
		}
		
		if(va.equals("Firma")){
			String CodDatosPersonales=req.getParameter("CodDatosPersonales");
			out.print("Selecciona la Imagen: <input type='file' id='fileInput' name='myimage' ></input> <input type='submit'  value='Adjuntar'></input><div id='fileDisplayArea'></div> </td><td width='34%'><div align='center'></div></td></tr>");

			out.print("<input name='CodUsuario' type='hidden' id='CodUsuario'  value="+CodDatosPersonales+">");
			System.out.print("CodDatosPersonales= "+CodDatosPersonales);
			out.print("<img src='file://127.0.0.1/Firma-Saim/"+CodDatosPersonales+".jpg'  WIDTH=320 HEIGHT=150>");
			
		}
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//String nom,ape,dire,tel,mail,pro,ocu,ced,contra,usu,codus="",cedula = null,usuario = null;
		//String estado,est="";
		
	}
	
}
