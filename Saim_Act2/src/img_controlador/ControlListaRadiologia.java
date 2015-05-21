package img_controlador;

import hic_metodo.MetodoImagenologiasPendientes;
import img_logica.MetodoListaRadiologia;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlListaRadiologia
 */
public class ControlListaRadiologia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
res.setContentType("text/html;charset=UTF-8");
		
		String va=req.getParameter("valor");
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		PrintWriter out=res.getWriter();
		MetodoImagenologiasPendientes mp=new MetodoImagenologiasPendientes();
		MetodoListaRadiologia mr = new MetodoListaRadiologia();
		
		
		if(va.equals("9")||va.equals("todos")){
			int contador=0;
			rs=mr.ObtenerExamenesAsignadosNombreTecnico();
			
			out.println("<table width='100%' ><tr style='font-size:9px'><td colspan='6' align='center' id='cabecera2' ><div align='center' class='style11'>EXAMENES POR REALIZAR</span></td></tr></table>   <div class='scrollbox5'><table class='style6' width='100%' border='1' bordercolor='#1C777B' ><tr id='cabecera2' class='style11' align='center'><td width='147' ><strong>Fecha de la Cita </strong></td><td width='157'><strong>Examenes Pendientes </strong></td><td width='157'><strong>Paciente</strong></td><td width='94'><strong>Entidad</strong></td><td width='97'><strong>Asignado Por </strong></td><td width='89'><strong>Revisado Por</strong></td><td width='89'><strong>Anular</strong></td></tr>");
			try{				 
				while(rs.next()){
					String nom=rs.getString(3);
					String ape=rs.getString(4);
					String Nombre=nom+" "+ape;
					out.println("<tr align='center'><td>"+rs.getString(1)+"-"+rs.getString(2)+"</td><td>"+rs.getString(7)+"</td><td>"+Nombre+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(6)+"</td>" +
							"<td><a href='#' onclick='Revisado("+rs.getString(8)+")'><strong><em>Revisado</em></strong></a></td><td><a href='#' onclick='omitir("+rs.getString(8)+")'><strong><em>Omitir</em></strong></a></td></tr>");
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
		
		
		
		if(va.equals("ExamPorModalidad")){
			String modalidad = req.getParameter("modalidad");
			
			
			out.print("<table width='100%' border='0' ><tr class='style6'><td width='8%'>" +
					"<label>Tipo Examen</label></td><td width='43%' colspan='2'>" +
					"<select name='cmbServicio' class='style3' id='cmbServicio' onchange='ExamPorModalidad()'>" +
					"<option value='Seleccione' selected='selected'>Seleccione</option>");
			rs1=mr.ObtenerModalidadExam();
			try {
				while(rs1.next()){
					out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
				}
				out.print("</select></td><td width='49%'><div align='center'></div></td></tr></table>");
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			int contador=0;
			rs=mr.ObtenerExamenesPorModalidad(modalidad);			
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
		
		
		
		/*
		
		if(va.equals("exaPac")){
			String codPac = req.getParameter("codPac");
			String fechaAdm = req.getParameter("fechaAdm");
			rs=mp.CargarImagenologiasPendientes(codPac,fechaAdm);
			try {
				out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Imagenologias pendientes por pabellon </div></td></tr></table><table width='100%' border='1'><tr class='style6'><td width='8%'><label>PABELLON</label></td><td width='43%'><select name='cmbServicio' class='style3' id='cmbServicio' onchange='ExamPorTipo()'><option value='Seleccione' selected='selected'>Seleccione</option>");
				rs1=mp.ObtenerServicios();
				try {
					while(rs1.next()){
						out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
					}
					out.print("</select></td><td width='49%'><div align='center'></div></td></tr></table>");
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("<table width='100%' border='1'><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Imagenologias Pendientes </div></td></tr>");

				out.print("<tr><td>Nombre</td><td>Primer Apellido</td><td>Segundo Apellido</td><td colspan='2'>Examenes Pendientes</td></tr>");
				while(rs.next()){
					
					out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td colspan='2'>"+rs.getString(5)+"</td></tr>");
					
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		*/
		
		
		
		if(va.equals("tipoExam")){			
			out.print("<table border='0' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Imagenologias pendientes por tipo examen </div>" +
					"</td></tr></table><table width='100%' border='0'>" +
					"<tr class='style6'><td width='8%'><label>Tipo Examen</label></td><td width='43%' colspan='2'>" +
					"<select name='cmbServicio' class='style3' id='cmbServicio' onchange='ExamPorModalidad()'>" +
					"<option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mr.ObtenerModalidadExam();
			try {
				while(rs.next()){
					out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
				}
				out.print("</select></td><td width='49%'><div align='center'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}

}
