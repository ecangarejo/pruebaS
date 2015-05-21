/**
 * controlador: lab_subarea se encuentra el proceso para  
 * buscar las subareas de un area.
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
 * Servlet implementation class lab_subarea
 */
public class lab_subarea extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String nombre=request.getParameter("nom");
		MetodoExamen exa=new MetodoExamen();		
		ResultSet rs=null;
		rs=exa.MetodoSubarea(nombre);
		PrintWriter out=response.getWriter();		
		out.print("<select name='cbsubare' size='1' id='cbsubare'>");		 
	       String seleccione="SELECCIONE...";
		try {
			out.print("<option selected>");
			out.print(seleccione);
			out.print("</option>");
			while(rs.next()){
				out.println("<option>"+rs.getString(1)+"</option>");
				
			}
			rs.getStatement().getConnection().close();
			out.print("</select>");
			out.print("<input name='yo' class='boton4' type='button' id='yo' value='Realizar' onclick='ventana_grupo()'/>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
