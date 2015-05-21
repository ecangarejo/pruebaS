package hic_controlador;

import hic_metodo.MetodoImagenologiasPendientes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAdmision;
import adm_logica.MetodoPaciente;
import adm_logica.MetodoPreingreso;
import adm_logica.MetodoTraslado;

/**
 * Servlet implementation class ControlImagenologiasPendientes
 */
public class ControlImagenologiasPendientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		
		String va=req.getParameter("valor");
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		PrintWriter out=res.getWriter();
		MetodoImagenologiasPendientes mp=new MetodoImagenologiasPendientes();
		
		if(va.equals("5")||va.equals("gral")){
			rs=mp.ExaPenHosp();
			String num =""; 
			try {
				out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Hospitalizacion Imagenologias Pendientes </div></td></tr>");
				out.print("<tr  bgcolor='#00BFFF' color='white' ><th>UBICACION</th><th>NOMBRE PACIENTE</th><th>IDENTIFICACION</th><th>GENERO</th><th>ENTIDAD</th><th># PENDIENTES</th></tr>");
				
				
				while(rs.next()){
					num=mp.NumImgPendientesPorPaciente(rs.getString(7),rs.getString(8));
					rs1=mp.CargarImagenologiasPendientes(rs.getString(7),rs.getString(8));
					rs2=mp.CargarImagenologiasPendientes(rs.getString(7),rs.getString(8));
					//System.out.println("pac "+rs.getString(7)+"num " +num);
					if(rs2.next()){
					out.print("<tr><th>"+rs.getString(2)+"</th><th>"+rs.getString(4)+"</th><th>"+rs.getString(5)+"</th><th>"+rs.getString(3)+"</th><th>"+rs.getString(6)+"</th><th>"+num+"</th></tr>");
					out.print("<tr><td colspan='2'><b>EXAMENES PENDIENTES</b></td></tr>");
					while(rs1.next()){
						
						out.print("<tr><td colspan='2'>"+rs1.getString(7)+" "+rs1.getString(5)+"</td></tr>");
					}rs1.getStatement().getConnection().close();
					}rs2.getStatement().getConnection().close();
					out.print("<tr><td colspan='2'></td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if(va.equals("exaPabellon")){
			String pabellon = req.getParameter("pabellon");
			String nomPabellon= mp.nombrePabellon(pabellon);
			rs=mp.ExaPenHospPabellon(nomPabellon);
			String num =""; 
			try {
				out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Imagenologias pendientes por pabellon </div></td></tr></table><table width='100%' border='1'><tr class='style6'><td width='8%'><label>PABELLON</label></td><td width='43%'><select name='cmbServicio' class='style3' id='cmbServicio' onchange='ExaPabellon()'><option value='Seleccione' selected='selected'>Seleccione</option>");
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
				out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Hospitalizacion Imagenologias Pendientes </div></td></tr>");

				out.print("<tr  bgcolor='#00BFFF' color='white' ><th>UBICACION</th><th>NOMBRE PACIENTE</th><th>IDENTIFICACION</th><th>GENERO</th><th>ENTIDAD</th><th># PENDIENTES</th></tr>");
				
				
				while(rs.next()){
					num=mp.NumImgPendientesPorPaciente(rs.getString(7),rs.getString(8));
					rs2=mp.CargarImagenologiasPendientes(rs.getString(7),rs.getString(8));
					rs3=mp.CargarImagenologiasPendientes(rs.getString(7),rs.getString(8));
					System.out.println("codigo pac "+rs.getString(7)+"fecha adm"+rs.getString(8));
					//System.out.println("pac "+rs.getString(7)+"num " +num);
					if(rs3.next()){
					out.print("<tr><th>"+rs.getString(2)+"</th><th>"+rs.getString(4)+"</th><th>"+rs.getString(5)+"</th><th>"+rs.getString(3)+"</th><th>"+rs.getString(6)+"</th><th>"+num+"</th></tr>");
					out.print("<tr><td colspan='2'><b>EXAMENES PENDIENTES</b></td></tr>");
                      while(rs2.next()){
						
						out.print("<tr><td colspan='2'>"+rs2.getString(7)+" "+rs2.getString(5)+"</td></tr>");
					}rs2.getStatement().getConnection().close();
					}rs3.getStatement().getConnection().close();
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if(va.equals("exaPac")){
			String codPac = req.getParameter("codPac");
			String fechaAdm = req.getParameter("fechaAdm");
			rs=mp.CargarImagenologiasPendientes(codPac,fechaAdm);
			try {
				out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Imagenologias pendientes por pabellon </div></td></tr></table><table width='100%' border='1'><tr class='style6'><td width='8%'><label>PABELLON</label></td><td width='43%'><select name='cmbServicio' class='style3' id='cmbServicio' onchange='ExaPabellon()'><option value='Seleccione' selected='selected'>Seleccione</option>");
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
		
		
		
		
		
		if(va.equals("pser")){			
			out.print("<table border='1' width='100%' ><tr><td><div id='cabecera2' class='style11' align='center'>Imagenologias pendientes por pabellon </div></td></tr></table><table width='100%' border='1'><tr class='style6'><td width='8%'><label>PABELLON</label></td><td width='43%'><select name='cmbServicio' class='style3' id='cmbServicio' onchange='ExaPabellon()'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mp.ObtenerServicios();
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
