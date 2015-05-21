package pat_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fact_metodo.MetodoSeguimientoAdmision;
import pat_metodo.MetodoEstudiosPorAsignar;

/**
 * Servlet implementation class pat_controlEstudiosPorAsignar
 */
public class pat_controlEstudiosPorAsignar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		MetodoEstudiosPorAsignar mepa =new MetodoEstudiosPorAsignar();
		ResultSet rs=null;
		
		if(va.equals("examPorApr")){
			int contador=0;
			rs=mepa.examPorApr();
			
			out.println("<table width='100%' ><tr style='font-size:9px'><td colspan='6' align='center' id='cabecera2' ><div align='center' class='style11'>ESTUDIOS POR ASIGNAR</span></td></tr></table> " +
					"  <div class='scrollbox5'><table class='style6' width='100%' border='1' bordercolor='#1C777B' >" +
					"<tr id='cabecera2' class='style11' align='center'>" +
					"<td width='147' ><strong>Fecha de la Orden </strong></td>" +
					"<td width='157'><strong>Paciente </strong></td>" +
					"<td width='157'><strong>documento</strong></td>" +
					"<td width='94'><strong>Entidad</strong></td>" +
					"<td width='94'><strong>Solicitado por</strong></td>" +
					"<td width='89'><strong>Ver Orden</strong></td>" +
					"<td width='89'><strong>Asignar</strong></td></tr>");
			try{				 
				while(rs.next()){
					out.println("<input type='hidden' id='txttipodoc' value='"+rs.getString(7)+"' />"+
					"<input type='hidden' id='txtNomUsu' value='"+rs.getString(8)+"' />" +
							"<tr align='center'><td>"+rs.getString(2)+"</td>" +
							"<td>"+rs.getString(3)+"</td>" +
							"<td>"+rs.getString(4)+"</td>" +
							"<td>"+rs.getString(5)+"</td>" +
							"<td>"+rs.getString(6)+"</td>" +
							"<td><a href='#' onclick='mostrarOrdenPat("+rs.getString(1)+")'><strong><em>Ver</em></strong></a></td>" +
							"<td><a href='#' onclick='BuscarPaciente("+rs.getString(1)+")'><strong><em>Asignar</em></strong></a></td></tr>");
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en pat_controlEstudiosPorAsignar va=examPorApr "+e);
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
