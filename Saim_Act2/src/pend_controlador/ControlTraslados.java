package pend_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pend_metodo.MetodoInterconsulta;
import pend_metodo.metodoTraslados;
import hic_metodo.MetodoVerFormatos;
import hic_metodo.MetodoMultiplePacientes;

/**
 * Servlet implementation class ControlTraslados
 */
public class ControlTraslados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlTraslados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		
		metodoTraslados mtras= new metodoTraslados();
		MetodoVerFormatos mvf= new MetodoVerFormatos();
		MetodoMultiplePacientes mmp= new MetodoMultiplePacientes();
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		String va = req.getParameter("valor");
		
		
		if(va.equals("1")){
			int contador=0;
			rs=mtras.ObtenerTrasladosPendientes();
			
			out.println("<table width='100%' ><tr style='font-size:9px'><td colspan='6' align='center' id='cabecera2' >" +
					"<div align='center' class='style11'>TRASLADOS PENDIENTES</span></td></tr></table>   " +
					"<div class='scrollbox5'><table class='style6' width='100%' border='1' bordercolor='#1C777B' >" +
					"<tr id='cabecera2' class='style11' align='center'>" +
					"<td><strong>Fecha </strong></td>" +
					"<td ><strong>Paciente </strong></td>" +
					"<td ><strong>Documento</strong></td>" +
					"<td ><strong>Entidad</strong></td>" +
					"<td ><strong>Cama Actual </strong></td> " +
					"<td ><strong>Trasladar a </strong></td>" +
					"<td ><strong>Solicitado por </strong></td></tr>");
			try{				 
				while(rs.next()){
					out.println("<tr align='center'><td>"+rs.getString(2)+"</td>" +
							"<td>"+rs.getString(3)+"</td>" +
							"<td>"+rs.getString(4)+"</td>" +
							"<td>"+rs.getString(5)+"</td>" +
							"<td>"+rs.getString(6)+"</td>" +
							"<td>"+rs.getString(7)+"</td>" +
							"<td>"+rs.getString(8)+"</td></tr>");
					contador=contador+1;
				}
				out.println("</table></div>");
				rs.getStatement().getConnection().close();
			}
			catch(SQLException e){
				System.out.println("Error en ControlTraslados va=1 "+e);
				e.printStackTrace();
			}
		}
		
		
		 if(va.equals("guardar_traslado")){
	    	   java.util.Date fechaActual = new java.util.Date();
				java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
				java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());	
	    	    String CodPacTras=req.getParameter("CodPac");
				String codAdmTras=req.getParameter("codAdm");
				String Usuario=req.getParameter("usuario");
				String motivo_traslado=req.getParameter("motivo_traslado");
				String traslado=req.getParameter("traslado");
				String cama_act="";
				
				rs1= mvf.BuscarTraslado(codAdmTras, "0");
				rs2= mmp.VerificarDatosAdmision(CodPacTras, codAdmTras);
				try {
					
					if(rs2.next()){
						cama_act=rs2.getString(9)+" Cama "+rs2.getString(10);
					
					
					if(rs1.next()){
						out.print("1");

					}else{
						 mvf.InsertarSolicitudTraslado(Fecha, Hora, cama_act, traslado, motivo_traslado, CodPacTras, codAdmTras, Usuario, "0");
						 out.print("0");
					}
					rs1.getStatement().getConnection().close();
				}
					rs2.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		
		
	}

}
