/**
 * controlador: ControlExamen se encuentra el proceso para  
 * asignar examen al pacientes, si este no existe lo dirige ala insercion
 * del paciente, de lo contrario le muestra las diferentes examenes que le puede asignar 
 * al paciente.
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodolabPa;
import lab_logica.MetodoExamen;

/**
 * Servlet implementation class ControlExamen
 */
public class ControlExamen extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String cedu,tipo,nombre,codigo;
		cedu=request.getParameter("ced");
		tipo=request.getParameter("tipo");
		ResultSet rs1=null;
	
		MetodolabPa labpa=new MetodolabPa();
		
		rs1=labpa.SQLlabPac(cedu, tipo);
		
		try {
			
			if(rs1.next()){
				nombre=rs1.getString(1)+" "+rs1.getString(2)+" "+rs1.getString(5);
				codigo=rs1.getString(3);
				response.sendRedirect("lab_Realizar_Examen.jsp?cod="+codigo+"&id="+1+"&nombre="+nombre+"&numero="+cedu+"&tipo="+tipo+"&z="+1+"&gene="+rs1.getString(4)+"&edad="+rs1.getString(6)+"");
			}else{
				response.sendRedirect("adm_IngresarDemografico.jsp");
			}
			rs1.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
			
	 }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre=request.getParameter("nom");
		MetodoExamen exa=new MetodoExamen();
		
		ResultSet rs=null;
		rs=exa.MetodoExamen1(nombre);
		PrintWriter out=response.getWriter();
		
		out.print("<table align='center'>");
		 
		out.print("<tr><td>Examen</td><td><select name='cbexamen' size='1' id='cbexamen' onchange='yosi()'>");
		 
	       String yosi="SELECCIONE...";
		try {
			out.print("<option selected>"+yosi+"</option>");
			while(rs.next()){
				out.println("<option>"+rs.getString(1)+"</option>");
				
			}
			rs.getStatement().getConnection().close();
			out.print("</select></td></tr></table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			
	 }

	

}
