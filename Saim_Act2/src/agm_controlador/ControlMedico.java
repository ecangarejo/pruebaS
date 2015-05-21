/***********************/
/**
 * controlador: ControlEspecialidades se encuentra la forma en que son 
 * creadas los medicos y el como se actualizan.
 */
package agm_controlador;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agm_metodo.MetodoEspecialidades;
import agm_metodo.MetodoMedicos;



/**
 * Servlet implementation class Controimg_area
 */
public class ControlMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		MetodoMedicos mms= new MetodoMedicos();
		MetodoEspecialidades me= new MetodoEspecialidades();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		String TipoDocumento=req.getParameter("TipoDocumento");
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String Nombres=req.getParameter("Nombres");
		String Apellidos=req.getParameter("Apellidos");
		String TarjetaProfesional=req.getParameter("TarjetaProfesional");
		String Profesion=req.getParameter("Profesion");
		String Especialidad=req.getParameter("Especialidad");
		String Direccion=req.getParameter("Direccion");
		String Telefono=req.getParameter("Telefono");
		String TiempoMedico=req.getParameter("TiempoMedico");
		String Codigo=req.getParameter("Codigo");
		
		
		if(va.equals("Ingresar")){

			out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear Medico </div></td></tr><tr><td width='25%'>Tipo Documento </td><td width='25%'><label><select name='cmbTipoDocumento' id='cmbTipoDocumento'><option value='Seleccione' selected='selected'>Seleccione</option>");
			out.print("<option value='CC'>CC</option>");
			out.print("</select></label></td><td width='25%'>Numero Documento </td><td width='25%'><input name='txtNumeroDocumento' type='text' id='txtNumeroDocumento' size='35' /></td></tr><tr><td>Nombres</td><td><input name='txtNombres' type='text' id='txtNombres' size='35' /></td><td>Apellidos</td><td><input name='txtApellidos' type='text' id='txtApellidos' size='35' /></td></tr>");
			out.print("<tr><td>Tarjeta Profesional </td><td><input name='txtTarjetaProfesional'  type='text' id='txtTarjetaProfesional' size='35' /></td><td>Profesion</td><td><input name='txtProfesion' maxlength='20' type='text' id='txtProfesion' size='35' /></td></tr><tr><td>Especialidad</td><td><select name='cmbEspecialidad' id='cmbEspecialidad'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=me.BuscarEspecialidadTodas();
			
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			out.print("</select></td><td>Tiempo de Consulta</td><td><input name='txtTiempoMedico' maxlength='2' type='text' id='txtTiempoMedico' /></td></tr><tr><td>Direccion</td><td><input name='txtDireccion' type='text' id='txtDireccion' size='35' /></td><td>Telefono</td><td><input name='txtTelefono' type='text' id='txtTelefono' size='35' /></td></tr><tr><td colspan='4'><div align='center'><input name='btnIngresarMedico' type='button' id='btnIngresarMedico' value='       Ingresar       '  onclick='IngresarMedico()'/></div></td></tr></table>");
		}
		if(va.equals("1")){
			rs=mms.BuscarMedicoDocumento(NumeroDocumento);
			String validar="";
			try {
				if(rs.next()){
					validar=rs.getString(1);
					if(validar!=""){
						out.print("Este Medico ya Esta Existe.");
					}
				}
				else{
					mms.CrearMedico(TipoDocumento, NumeroDocumento, Nombres, Apellidos, TarjetaProfesional, Profesion, Especialidad, Direccion, Telefono,TiempoMedico);
					out.print("Medico Creado Con Exito.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("Actualizar")){
			out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center'>Buscar Medico </div></td></tr><tr><td width='17%'>Tipo Documento </td><td width='18%'><select name='cmbTipoDoc' id='cmbTipoDoc'><option value='Seleccione' selected='selected'>Seleccione</option><option value='CC'>CC</option></select>    </td>");
			out.print("<td width='20%'>Numero Documento </td><td width='29%'><input name='txtNumDocu' type='text' id='txtNumDocu' size='30'></td><td width='16%'><input name='btnBuscar' type='button' id='btnBuscar' value='Buscar' onClick='BuscarMedico()'></td></tr><tr><td colspan='5'><div id='ActuMedi'></div></td></tr></table>");
		}
		
		if(va.equals("2")){
			rs=mms.BuscarMedicoActualizar(NumeroDocumento, TipoDocumento);			
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Actualizar Medico </div></td></tr><tr><td width='25%'>Tipo Documento </td><td width='25%'><label><select name='cmbTipoDocumento' id='cmbTipoDocumento'><option value="+rs.getString(2)+" selected='selected'>"+rs.getString(2)+"</option><option value='CC'>CC</option>");
					out.print("</select></label></td><td width='25%'>Numero Documento </td><td width='25%'><input name='txtNumeroDocumento' type='text' id='txtNumeroDocumento' size='35' value='"+rs.getString(1)+"' /></td></tr><tr><td>Nombres</td><td><input name='txtNombres' type='text' id='txtNombres' size='35' value='"+rs.getString(3)+"'/></td><td>Apellidos</td><td><input name='txtApellidos' type='text' id='txtApellidos' size='35' value='"+rs.getString(4)+"'/></td></tr>");
					out.print("<tr><td>Tarjeta Profesional </td><td><input name='txtTarjetaProfesional' type='text' id='txtTarjetaProfesional' size='35' value='"+rs.getString(5)+"' /></td><td>Profesion</td><td><input name='txtProfesion' type='text' id='txtProfesion' size='35' value='"+rs.getString(6)+"' /></td></tr><tr><td>Especialidad</td><td><select name='cmbEspecialidad' id='cmbEspecialidad'><option value="+rs.getString(9)+" selected='selected'>"+rs.getString(10)+"</option>");
					rs1=me.BuscarEspecialidadTodas();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
					out.print("</select></td><td>Tiempo de Consulta</td><td><input name='txtTiempoMedico' type='text' id='txtTiempoMedico' value='"+rs.getString(12)+"' /></td></tr><tr><td>Direccion</td><td><input name='txtDireccion' type='text' id='txtDireccion' size='35' value='"+rs.getString(7)+"' /></td><td>Telefono</td><td><input name='txtTelefono' type='text' id='txtTelefono' size='35' value='"+rs.getString(8)+"' /></td></tr><tr><td colspan='4'><div align='center'><input name='txtCodigoMedico' type='hidden' id='txtCodigoMedico' size='5' value='"+rs.getString(11)+"' /><input name='btnIngresarMedico' type='button' id='btnIngresarMedico' value='       Actualizar       '  onclick='ActualizarMedico()'/></div></td></tr></table>");

				}
				else{
					out.print("No Se Ha Encontrado Ningun Registro Con Este Documento");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("3")){
			mms.ActualizarMedico(TipoDocumento, NumeroDocumento, Nombres, Apellidos, TarjetaProfesional, Profesion, Especialidad, Direccion, Telefono, Codigo,TiempoMedico);
			out.print("Datos Actualizados Con Exito.");
		}
		
	}
}
