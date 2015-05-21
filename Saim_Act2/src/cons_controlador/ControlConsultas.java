package cons_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cons_metodo.MetodoConsultas;

public class ControlConsultas extends HttpServlet  {
	private static final long serialVersionUID = 1L;
    
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);  
		
		String va = req.getParameter("valor");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		MetodoConsultas mc=new MetodoConsultas();
		
		if(va.equals("casos")){
			
			String tcaso=req.getParameter("tcaso");
			rs=mc.BuscarRangocaso(tcaso);
			try {
				while(rs.next()){
					
					out.println(rs.getString(1)+"_"+rs.getString(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("CP")){
			String CodManual=req.getParameter("CodManual");
			String Programa=req.getParameter("Programa");
			rs=mc.BuscarProgramas(CodManual, Programa);
			try {
				if(rs.next()){					
					rs1=mc.BuscarProgramas(CodManual, Programa);
					//out.print("<table width='100%' border='1'><tr><td>CUPS</td><td>Nombre Programa</td><td>Referencia</td></tr></table>");
					out.print("<table width='100%' border='1' >");
					while(rs1.next()){
						out.print("<tr><td>"+rs1.getString("cod_cups")+"</td><td><a href='#' onclick='BuscarValorEntidades("+rs1.getString("cod_programa")+")'>"+rs1.getString(3)+"</a></td><td>"+rs1.getString("cod_referencia")+"</td></tr>");
					}
					out.print("<table width='100%' border='1' ><tr><td id='ValEmp'>Valores por entidades</td></tr></table>");
					rs1.getStatement().getConnection().close();

				}else{
					out.print("No se encontraron registros ");
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(va.equals("MEP")){
			String CodManual=req.getParameter("CodManual");
			String Programa=req.getParameter("Programa");
			
			try {
				rs=mc.BuscarProgramasUnico(CodManual, Programa);
				out.print("<table width='100%' border='1'><tr><td>CUPS</td><td>Nombre Programa</td><td>Referencia</td></tr>");
				if(rs.next()){					
					out.print("<tr bgcolor='#E6E6E6'><td>"+rs.getString("cod_cups")+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString("cod_referencia")+"</td></tr>");
				}				
				else{
					out.print("No se encontraron registros ");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
				rs=mc.BuscarProgramasUnico(CodManual, Programa);
				out.print("<table width='100%' border='1'><tr><td>ENTIDAD</td><td>MANUAL TARIFARIO</td><td>VALOR</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString("nombre_entidad")+"</td><td>"+rs.getString("descripcion")+"</td><td>"+rs.getString("valor")+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		if(va.equals("BDX")){
			String NombreDx=req.getParameter("NombreDx");
			try {				
				rs=mc.BuscarDX(NombreDx);
				out.print("<table width='100%' border='1'><tr bgcolor='#E6E6E6' ><td>CODIGO</td><td>NOMBRE DIAGNOSTICO</td></tr>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString("codigoCIE")+"</td><td>"+rs.getString("nombre")+"</td></tr>");
				}
				out.print("</table>");
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
