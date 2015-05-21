package fact_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fact_metodo.MetodoCrearEspecialidad;

public class ControlCrearEspecialidad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		MetodoCrearEspecialidad mce = new MetodoCrearEspecialidad();
		
		/*String NombreTipoEspecialidad = req.getParameter("NombreTipoEspecialidad");
		String NombreEspecialidad = req.getParameter("NombreEspecialidad");
		String CodigoTipoEspecialidadFK = req.getParameter("CodigoTipoEspecialidadFK");
		String CodigoRips = req.getParameter("CodigoRips");*/
		
		String desc = req.getParameter("desc");
		String cod = req.getParameter("cod");
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		
		if(va.equals("CrearRb")){
			out.print("<table width='421' border='0'><tr><td width='88'><div align='right'>Descripci&oacute;n:</div></td><td width='323'><input name='Descripcion' type='text' id='Descripcion' size='45' maxlength='100' onkeydown='A(this, event)' /></td></tr>");
			out.print("<tr><td colspan='2'><div align='right'><input name='Ingresa' type='button' id='Ingresa' value='   Ingresar   ' onclick='Ingresar()' /></div></td></tr></table>");
			out.close();
			/*out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear Tipo Especialidad</div></td></tr>");
			out.print("<tr><td width='18%'>Nombre Tipo Especialidad </td><td width='32%'><input name='txtTipoEspecialidad' type='text' id='txtTipoEspecialidad' size='40' onkeyup='this.value=this.value.toUpperCase();' /><span class='style8'>*</span></td><td width='25%'><input name='btnIngresarTipoEspecialidad' type='button' id='btnIngresarTipoEspecialidad' value='     Ingresar     ' onclick='CrearTipoEspecialidad()' /></td><td width='25%'><span class='style8'>Datos Requeridos *</span></td></tr>");
			out.print("<tr><td>Especialidades Creadas </td><td><select name='cmbTipoEspecialidades' id='cmbTipoEspecialidades'>");
			rs=mce.BuscarTipoEspecialidad();
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
			out.print("</select></td><td>&nbsp;</td><td>&nbsp;</td></tr></table>");*/

		}else if(va.equals("ActualizarRb")){
			rs = mce.getAll();
			out.print("<table width='500' border='0'>");
			out.print("<tr><td width='166'><div align='right'>Seleccione la Especialidad:</div></td><td width='318'><select name='lista' id='lista' onchange='Buscar()'><option value='Seleccione'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></td></tr>");
				out.print("<tr><td colspan='2'><div id='objeto'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();
		}else if(va.equals("Ingresar")){
			if(mce.Crear(desc)){
				out.print("Ingreso exitoso");
			}else{
				out.print("Error al ingresar los datos");
			}
			out.close();
		}else if(va.equals("ver")){
			rs1 = mce.getByCod(cod);
			try {
				if(rs1.next()){
					out.print("<table width='421' border='0'><tr><td width='88'><div align='right'>Descripci&oacute;n:</div></td><td width='323'><input name='Descripcion' type='text' id='Descripcion' value='"+rs1.getString(1)+"' size='45' maxlength='100' onkeydown='B(this, event)' /></td></tr>");
					out.print("<tr><td colspan='2'><div align='right'><input name='Actualiza' type='button' id='Actualiza' value='   Actualizar   ' onclick='Actualizar()' /></div></td></tr></table>");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.print(e.getMessage());
				e.printStackTrace();
			}
			out.close();
		}else if(va.equals("Actualizar")){
			if(mce.Actualizar(desc, cod)){
				out.print("Especialidad actualizada exitosamente");
			}else{
				out.print("Error al actualizar la especialidad");
			}
			out.close();
		}
		
		/*if(va.equals("1.1")){
			
			rs=mce.BuscarNombreTipoEspecialidad(NombreTipoEspecialidad);
			String validar="";
			try {
				if(rs.next()){
					validar=rs.getString(1);
					out.print("Este Tipo Especialidad Ya existe.\n Intente Otravez.");
				}
				else{
					mce.CrearTipoEspecialidad(NombreTipoEspecialidad);
					out.print("Tipo Especialidad Creado Con Exito.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2")){
			out.print("<table width='100%' class='style6'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2'>Crear  Especialidad</div></td></tr>");
			out.print("<tr><td width='18%'>Nombre Especialidad </td><td width='32%'><input name='txtNombreEspecialidad' type='text' id='txtNombreEspecialidad' size='40' onkeyup='this.value=this.value.toUpperCase();' /><span class='style8'>*</span></td><td width='18%'>Seleccione Tipo Especialidad  </td><td width='32%'><select name='cmbTipoEspecialidades' id='cmbTipoEspecialidades' onchange='VerEspecialidadesCreadas()' ><option value='Seleccione'>Seleccione</option>");
			rs=mce.BuscarTipoEspecialidad();
			try {
				while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select><span class='style8'>*</span></td></tr><tr><td>Codigo Para Rips </td><td><input name='txtCodigoRips' type='text' id='txtCodigoRips' onkeyup='validarte(form1.txtCodigoRips);' ><span class='style8'>*</span></td><td><input name='btnIngresarTipoEspecialidad' type='button' id='btnIngresarTipoEspecialidad' value='     Ingresar     ' onClick='CrearEspecialidad()' /></td><td><span class='style8'>Datos Requeridos *</span></td></tr>");
				out.print("<tr><td colspan='4'><div id='EspCreadas'></div></td></tr></table>");
				
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
		
		if(va.equals("2.2")){
			try {
				out.print("<table width='100%' border='0'>");
				rs1=mce.NombreTipoEspecialidad(CodigoTipoEspecialidadFK);
				if(rs1.next()){
					out.print("<tr><td><div align='center'>Tipo Especialidad &gt;&gt;"+rs1.getString(1)+" </div></td></tr>");
				}				
				rs1.getStatement().getConnection().close();
				rs=mce.BuscarEspecialidadPorTipo(CodigoTipoEspecialidadFK);
				while(rs.next()){
					out.print("<tr><td>"+rs.getString(1)+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}		
		}
		
		if(va.equals("2.1")){
			rs=mce.BuscarEspecialidad(NombreEspecialidad);
			try {
				if(rs.next()){
					out.print("Esta Especialidad Ya existe.\n Intente Otravez.");
				}
				else{
					mce.CrearEspecialidad(NombreEspecialidad, CodigoTipoEspecialidadFK, CodigoRips);
					out.print("Especialidad Creada Con Exito.");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("3")){
			out.print("");
			out.print("");
			out.print("");
			out.print("");
		}*/
		
	}
}
