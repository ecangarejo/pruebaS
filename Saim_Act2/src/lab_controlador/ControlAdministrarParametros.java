package lab_controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoAdministrarParametros;
import lab_logica.MetodolabPa;

public class ControlAdministrarParametros extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va=req.getParameter("valor");
		String CodigoArea=req.getParameter("CodigoArea");
		String NombreArea=req.getParameter("NombreArea");
		String CodigoSubarea=req.getParameter("CodigoSubarea");
		String NombreSubarea=req.getParameter("NombreSubarea");
		String CodigoExamen=req.getParameter("CodigoExamen");
		String NombreExamen=req.getParameter("NombreExamen");
		String CodigoUnidad=req.getParameter("CodigoUnidad");
		String NombreUnidad=req.getParameter("NombreUnidad");
		String Fecha=req.getParameter("Fecha");
		String Hora=req.getParameter("Hora");
		String CodUsuario=req.getParameter("CodUsuario");
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		MetodoAdministrarParametros map=new MetodoAdministrarParametros();
		MetodolabPa pa=new MetodolabPa();
		if(va.equals("1")){
			/**Actualizar Nombre del Area**/
			rs=map.BuscarAreas();
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Actualizar Nombre Area </div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp; <select name='cmbArea' id='cmbArea' onchange='VerNombreArea()'><option value='S'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div align='center' id='texto'><label><input name='txtNuevaArea' type='text' id='txtNuevaArea' size='50' /></label></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnActualizarArea' type='button' id='btnActualizarArea' value='     Actualizar     ' onclick='ActualizarArea()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("1.1")){
			rs=map.NombreArea(CodigoArea);
			try {
				if(rs.next()){
					out.print("<label><input name='txtNuevaArea' type='text' id='txtNuevaArea' size='50' value='"+rs.getString(1)+"' /><input name='txtCodigoArea' type='hidden' id='txtCodigoArea' value="+CodigoArea+" /></label>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(va.equals("1.2")){
			map.ActualizarArea(NombreArea, CodigoArea);
			out.print("Area Actualizada Con Exito.");
		}
		/****************************************************************************/
		if(va.equals("2")){
			/**Actualizar Nombre de una Subarea**/
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Actualizar Nombre Subarea</div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbArea' id='cmbArea' onchange='BuscarSubarea()' ><option value='S'>Seleccione</option>");
			rs=map.BuscarAreas();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div align='center' id='subarea'>Seleccione La Subarea &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbSubarea' id='cmbSubarea'><option value='S'>Seleccione</option>");
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div align='center' id='texto'><input name='txtNuevaSubarea' type='text' id='txtNuevaSubarea' size='50' /></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnActualizarSubrea' type='button' id='btnActualizarSubrea' value='     Actualizar     ' onclick='ActualizarSubarea()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("2.1")){
			rs=map.BuscarSubareas(CodigoArea);
			out.print("Seleccione La Subarea &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbSubarea' id='cmbSubarea' onchange='VerNombreSubarea()'><option value='S'>Seleccione</option>");
			try {
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
		
		if(va.equals("2.2")){
			rs=map.NombreSubArea(CodigoSubarea);
			try {
				if(rs.next()){
					out.print("<input name='txtNuevaSubarea' type='text' id='txtNuevaSubarea' size='50' value='"+rs.getString(1)+"' />");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("2.3")){
			map.ActualizarSubArea(NombreSubarea, CodigoSubarea);
			out.print("Subarea Actualizada.");
		}
		/****************************************************************************/
		if(va.equals("3")){
			/**Actualizar Nombre de un Examen**/
			out.print("<table  class='style6' width='100%' ><tr><td colspan='6' align='center' id='cabecera2' class='style11'><div  >Seleccione Una Opcion </div></td></tr></table><table  width='100%' class='style6'>");
			out.print("<tr><td width='38%'><label><input name='radiobutton' type='radio' value='1'  id='3.1' onClick='getRadio()'/>Pertenece Area</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input name='radiobutton' type='radio' value='2' id='3.2' onClick='getRadio()' />Pertenece a Subarea</label></td></tr>");
			out.print("<tr><td><div id='opcion'></div></td></tr></table>");
			
		}
		if(va.equals("3.1")){
			rs=map.BuscarAreas();
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Actualizar Nombre Examen Perteneciente a un Area </div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbArea' id='cmbArea' onchange='VerExamenArea()'><option value='S'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div id='examen' align='center'>Seleccione Examen &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbExamen' id='cmbExamen' onchange='VerNombreExamen()'><option value='S'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td><div align='center' id='texto'><label><input name='txtNuevoExamen' type='text' id='txtNuevoExamen' size='50' /></label></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnActualizarExamen' type='button' id='btnActualizarExamen' value='     Actualizar     ' onclick='ActualizarExamen()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("3.11")){
			rs=map.BuscarExamenesArea(CodigoArea);
			out.print("Seleccione Examen &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbExamen' id='cmbExamen' onchange='VerNombreExamen()'><option value='S'>Seleccione</option>");
			try {
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
		
		if(va.equals("3.12")){
			rs=map.NombreExamen(CodigoExamen);
			
			try {
				if(rs.next()){
					out.print("<label><input name='txtNuevoExamen' type='text' id='txtNuevoExamen' size='50' value='"+rs.getString(1)+"' /></label>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("3.13")){
			map.ActualizarExamen(CodigoExamen, NombreExamen);
			out.print("Examen Actualizado.");
		}
		
		/***********************************************************************/
		if(va.equals("3.2")){
			rs=map.BuscarAreas();
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Actualizar Nombre Examen Perteneciente a una Subarea </div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp; <select name='cmbArea' id='cmbArea' onchange='VerSubarea()'><option value='S'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div id='subarea' align='center'>Seleccione Subarea &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbSubarea' id='cmbSubarea' onChange='VerExamenesSubarea()'><option value='S'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td><div id='examen' align='center'>Seleccione Examen &nbsp;&nbsp;&nbsp;&nbsp; <select name='cmbExamen' id='cmbExamen' onChange='VerNombreExamen()'><option value='S'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td><div align='center' id='texto'><label><input name='txtNuevoExamen' type='text' id='txtNuevoExamen' size='50' /></label></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnActualizarArea' type='button' id='btnActualizarArea' value='     Actualizar     ' onclick='ActualizarExamen()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(va.equals("3.21")){
			rs=map.BuscarSubareas(CodigoArea);
			out.print("Seleccione La Subarea &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbSubarea' id='cmbSubarea' onchange='VerExamenesSubarea()'><option value='S'>Seleccione</option>");
			try {
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
		
		if(va.equals("3.22")){
			rs=map.BuscarExamenesSubarea(CodigoSubarea);
			out.print("Seleccione Examen &nbsp;&nbsp;&nbsp;&nbsp; <select name='cmbExamen' id='cmbExamen' onChange='VerNombreExamen()'><option value='S'>Seleccione</option>");
			try {
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
		/****************************************************************************/
		if(va.equals("4")){
			/**Actualizar Unidad**/
			rs=map.BuscarUnidad();
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Actualizar Unidad </div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Unidad &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbUnidad' id='cmbUnidad' onchange='VerUnidad()'><option value='S'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div align='center' id='texto'><label><input name='txtNuevaUnidad' type='text' id='txtNuevaUnidad' size='20' /></label></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnActualizarUnidad' type='button' id='btnActualizarUnidad' value='     Actualizar     ' onclick='ActualizarUnidad()' /></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("4.1")){
			rs=map.VerNombreUnidad(CodigoUnidad);
			try {
				if(rs.next()){
					out.print("<label><input name='txtNuevaUnidad' type='text' id='txtNuevaUnidad' size='20' value='"+rs.getString(2)+"' /></label>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("4.2")){
			map.ActualizarUnidad(CodigoUnidad, NombreUnidad);
			out.print("Unidad Actualizada");
		}
		/****************************************************************************/
		if(va.equals("5")){
			/**Eliminar Area**/
			rs=map.BuscarAreas();
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Eliminar Area </div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp; <select name='cmbArea' id='cmbArea' onchange='VerNombreArea()'><option value='S'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnEliminarArea' type='button' id='btnEliminarArea' value='     Eliminar     ' onclick='EliminarArea()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("5.1")){
			map.EliminarArea(CodigoArea);
			out.print("Area Eliminada");
		}
		
		/****************************************************************************/
	
		if(va.equals("6")){
			/**Eliminar Subarea**/
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Eliminar Subarea</div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbArea' id='cmbArea' onchange='BuscarSubarea()' ><option value='S'>Seleccione</option>");
			rs=map.BuscarAreas();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div align='center' id='subarea'>Seleccione La Subarea &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbSubarea' id='cmbSubarea'><option value='S'>Seleccione</option>");
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnEliminarSubrea' type='button' id='btnEliminarSubrea' value='     Eliminar     ' onclick='EliminarSubarea()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		if(va.equals("6.1")){
			map.EliminarSubarea(CodigoSubarea);
			out.print("Subarea Eliminada.");
		}
		/****************************************************************************/
		if(va.equals("7")){
			/**Eliminar Examen**/
			out.print("<table  class='style6' width='100%' ><tr><td colspan='6' align='center' id='cabecera2' class='style11'><div  >Seleccione Una Opcion </div></td></tr></table><table  width='100%' class='style6'>");
			out.print("<tr><td width='38%'><label><input name='radiobutton' type='radio' value='7'  id='7.1' onClick='getRadio()'/>Pertenece Area</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input name='radiobutton' type='radio' value='7' id='7.2' onClick='getRadio()' />Pertenece a Subarea</label></td></tr>");
			out.print("<tr><td><div id='opcion'></div></td></tr></table>");
			
		}
		
		if(va.equals("7.1")){
			rs=map.BuscarAreas();
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Eliminar Examen Perteneciente a un Area </div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbArea' id='cmbArea' onchange='VerExamenArea()'><option value='S'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div id='examen' align='center'>Seleccione Examen &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbExamen' id='cmbExamen' onchange='VerNombreExamen()'><option value='S'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnEliminarExamen' type='button' id='btnEliminarExamen' value='     Eliminar     ' onclick='EliminarExamen()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("7.2")){
			rs=map.BuscarAreas();
			out.print("<table width='100%' border='1'><tr><td><div align='center' id='cabecera2' class='style11' >Eliminar Examen Perteneciente a una Subarea </div></td></tr>");
			out.print("<tr><td><div align='center'>Seleccione  Area &nbsp;&nbsp;&nbsp;&nbsp; <select name='cmbArea' id='cmbArea' onchange='VerSubarea()'><option value='S'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				rs.getStatement().getConnection().close();
				out.print("</select></div></td></tr>");
				out.print("<tr><td><div id='subarea' align='center'>Seleccione Subarea &nbsp;&nbsp;&nbsp;&nbsp;<select name='cmbSubarea' id='cmbSubarea' onChange='VerExamenesSubarea()'><option value='S'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td><div id='examen' align='center'>Seleccione Examen &nbsp;&nbsp;&nbsp;&nbsp; <select name='cmbExamen' id='cmbExamen' onChange='VerNombreExamen()'><option value='S'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td><div align='center'><input name='btnEliminarExamen' type='button' id='btnEliminarExamen' value='     Eliminar     ' onclick='EliminarExamen()' /></div></td></tr></table>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("7.3")){
			map.EliminarExamen(CodigoExamen);
			out.print("Examen Eliminado");
		}
		
		if(va.equals("8")){
			out.print("<table width='100%' border='0'><tr><td><div align='center'><input name='btnCrearBackUp' type='button' id='btnCrearBackUp' value='     Crear Backup     ' onclick='CrearBackup()' /></div></td></tr>");
			//out.print("<tr><td><div align='center'><input name='btnImportarBackUp' type='button' id='btnImportarBackUp' value='     Importar Datos     ' onclick='ActualizarBackup()' /></div></td></tr>");
			out.print("</table>");		

		}
		if(va.equals("8.1")){
			String FechaCompleta=Fecha+" "+Hora; 
			String NombreUsuario="";
			String NombreArchivo=("C:\\Backup "+FechaCompleta+".sql");			
			try
			{
				rs2=pa.buscarUsu(CodUsuario);
				if(rs2.next()){
					NombreUsuario=rs2.getString(1)+" "+rs2.getString(2);
				}
				Runtime runtime = Runtime.getRuntime();
				File backupFile = new File(String.valueOf("C:\\Backup "+FechaCompleta+".sql"));
				FileWriter fw = new FileWriter(backupFile);
				Process child = runtime.exec("C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\mysqldump --opt --password=123 --user=root saim");
				/*Process es el que ejecuta el comando para buscar el mysqldump.exe*/
				InputStreamReader irs = new InputStreamReader(child.getInputStream());
				BufferedReader br = new BufferedReader(irs);
				/* se escribe sobre el archivo*/
				String line;
				while( (line=br.readLine()) != null ) {
					fw.write(line + "\n");
				}
				map.CrearAuditoriaBackup(NombreArchivo, NombreUsuario);
				fw.close();
				irs.close();
				br.close();
				rs2.getStatement().getConnection().close();
				out.print("Backup Realizado Con Exito.\nRuta C:\\Backup "+FechaCompleta+".sql");
			}
			catch(Exception e){
				e.printStackTrace();
				out.print("Error:: "+e);
			} 		
		}
		
		if(va.equals("9")){
			rs=map.BuscarDiagnosticos();
			try {
				while(rs.next()){
					map.ActualizarDiagnosticosIngreso(rs.getString(1), rs.getString(3));
				}
				out.print("Diagnosticos Actualizados Exitosamente");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error "+e);
				e.printStackTrace();
			}
		}
		if(va.equals("10")){
			rs=map.BuscarDiagnosticos();
			try {
				while(rs.next()){
					map.ActualizarDiagnosticosEgreso(rs.getString(1), rs.getString(3));
				}
				out.print("Diagnosticos Actualizados Exitosamente");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error "+e);
				e.printStackTrace();
			}
		}
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	}

}
