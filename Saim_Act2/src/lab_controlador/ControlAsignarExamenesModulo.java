package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodoAsignarExamenModulo;

public class ControlAsignarExamenesModulo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va=req.getParameter("valor");
		String TipoDocumento=req.getParameter("TipoDocumento");
		String NumeroDocumento=req.getParameter("NumeroDocumento");
		String CodArea=req.getParameter("CodArea");
		MetodoAsignarExamenModulo mae = new MetodoAsignarExamenModulo();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		if(va.equals("1")){
			rs=mae.BuscarPaciente(TipoDocumento, NumeroDocumento);
			
			try {
				if(rs.next()){
					out.print("<table width='100%' border='1'>");
					out.print("<tr><td width='16%'>Nombres y Apellidos</td><td colspan='5'><div>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</div></td>");
					out.print("<td width='8%'>Genero</td><td width='29%'><div>"+rs.getString(5)+"</div></td></tr>");
					out.print("<tr><td>Fecha de Nacimiento </td><td width='19%'><div>"+rs.getString(6)+"</div></td><td width='7%'>Edad</td>");
					out.print("<td width='9%'><input name='txtCodPaciente' type='hidden' id='txtCodPaciente' value="+rs.getString(1)+"><div>"+rs.getString(7)+"</div></td><td width='5%'>Eps</td><td colspan='3'><div>"+rs.getString(8)+"</div></td></tr>");
					out.print("</table>");
					
					out.print("<table width='100%' border='1'><tr><td width='17%'>Nombre Del Medico: </td><td width='48%'><input name='txtNombreMedico' type='text' id='txtNombreMedico' size='45' /></td>");
					out.print("<td width='11%'>Tipo de PyP </td><td width='24%'><select name='cmbPyP' id='cmbPyP'><option value='ADULTO MAYOR'>ADULTO MAYOR</option>");
					out.print("<option value='ADULTO MENOR'>ADULTO MENOR</option><option value='EMBARAZADAS'>EMBARAZADAS</option><option value='NO APLICA' selected='selected'>NO APLICA</option></select></td></tr></table>");
					
					out.print("<table width='100%' border='1'>");
					out.print("<tr><td colspan='2'><div id='cabecera2' class='style11' align='center'>Seleccione Grupo Del Examen</div></td></tr>");
					out.print("<tr><td width='20%'>Seleccione Grupo</td><td><select name='cmbAreaExamen' id='cmbAreaExamen' onchange='VerExamenes()'><option value='Seleccione' selected='selected'>Seleccione</option>");
					rs1=mae.ObtenerAreaExamenes();
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					out.print("</select></td></tr>");
					out.print("<tr><td colspan='2'><div id='Examenes'></div></td></tr></table>");
					rs1.getStatement().getConnection().close();
					
				}
				
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(va.equals("2")){
			int contador=0;
			rs=mae.ObtenerExamenesArea(CodArea);
			out.print("<table border='1' class='style6' width='100%'><tr tyle='font-size:9px' id='cabecera2' align='center' class='style11'><td colspan='2'>NOMBRE DEL EXAMEN </td><td align='center'>SELECCIONE<label><input type='checkbox'  name='all' id='all' onclick='checkAll();'  /></label></td></tr>");
			try {
				while(rs.next()){
					out.print("<tr style='font-size:9px'><td colspan='2'>"+rs.getString(2)+"</td><td width='154' align='center'><label><input type='checkbox' name='ca' id='ca' value='"+rs.getString(1)+"' /><input type='hidden' name='txtTipo' id='txtTipo' value='"+rs.getString(3)+"' />  </label></td></tr>");
					contador=contador+1;
				}
				out.print("<tr><td colspan='3' align='right'><div align='center'><label><input name='btnAsignar'  type='button' id='btnAsignar' value='Asignar' class='boton4' onclick='GuardarLaboratorios()'></label></div></td></tr></table><input name='yo' id='yo' type='hidden' value="+contador+">");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
