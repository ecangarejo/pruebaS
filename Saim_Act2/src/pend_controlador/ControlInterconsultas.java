package pend_controlador;

import pend_metodo.MetodoInterconsulta;
import hic_metodo.MetodoVerFormatos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlInterconsultas
 */
public class ControlInterconsultas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlInterconsultas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs6=null;
		ResultSet rs7=null;
		
		MetodoInterconsulta minter = new MetodoInterconsulta();
		MetodoVerFormatos mvf= new MetodoVerFormatos();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		
		
		if(va.equals("1")){
			int contador=0;
			rs=minter.ObtenerInterconsultasPendientes();
			
			out.println("<table width='100%' ><tr style='font-size:9px'><td colspan='6' align='center' id='cabecera2' >" +
					"<div align='center' class='style11'>INTERCONSULTAS POR REALIZAR</span></td></tr></table>   " +
					"<div class='scrollbox5'><table class='style6' width='100%' border='1' bordercolor='#1C777B' >" +
					"<tr id='cabecera2' class='style11' align='center'><td width='147' >" +
					"<strong>Fecha </strong></td><td width='157'><strong>Interconsultas Pendientes </strong></td>" +
					"<td width='157'><strong>Paciente</strong></td><td width='94'><strong>Entidad</strong></td>" +
					"<td width='97'><strong>Asignada Por </strong></td></tr>");
			try{				 
				while(rs.next()){
					out.println("<tr align='center'><td>"+rs.getString(7)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(4)+"</td></tr>");
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en ControlInterconsultas va=1 "+e);
				e.printStackTrace();
			}
		}
		
		
		
		 if(va.equals("guardar_interconsulta")){
	    	   java.util.Date fechaActual = new java.util.Date();
				java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
				java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
	    	    String CodPacInter=req.getParameter("CodPac");
				String codAdmInter=req.getParameter("codAdm");
				String Usuario=req.getParameter("usuario");
				String motivo_inter=req.getParameter("motivo_inter");
				String cod_especialidad=req.getParameter("cod_especialidad");
				
				rs1= mvf.BuscarInterconsultas(codAdmInter, cod_especialidad,"-1");
				
				try {
					if(rs1.next()){
						out.print("1");

					}else{
						 mvf.InsertarInterconsulta(codAdmInter, motivo_inter, Usuario,CodPacInter, cod_especialidad, Fecha, Hora,"-1");
						 out.print(rs1.getString(2));
					}
					rs1.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
		
		
	}

}
