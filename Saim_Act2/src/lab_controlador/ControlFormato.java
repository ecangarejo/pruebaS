/**
 * controlador: ControlFormato se encuentra el proceso para  
 * buscar un examen y asignar su codigo.
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
import lab_logica.MetodoExamen;

/**
 * Servlet implementation class ControlFormato
 */
public class ControlFormato extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String examen;
		examen=request.getParameter("examen");
		MetodoExamen exa=new MetodoExamen ();
		ResultSet rs;
		rs=exa.BuscarExamen(examen);
		PrintWriter out=response.getWriter();
		try {
			if(rs.next()){
			out.println(" <input type='hidden' name='tipo' id='tipo'  value='"+rs.getString(1)+"' /><input type='hidden' name='examen' id='examen'  value='"+examen+"' />");
				
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
