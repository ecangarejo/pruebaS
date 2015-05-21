/**
 * controlador: lab_rango se encuentra el proceso para  
 * buscar los codigo de las areas tomando como parametro el nombre del area.
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
import lab_logica.Metodolabarea;

/**
 * Servlet implementation class lab_rango
 */
public class lab_rango extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String nombreuni=request.getParameter("nomuni");
		Metodolabarea ra=new Metodolabarea();		
		ResultSet rs=null;
		rs=ra.labrango(nombreuni);
		PrintWriter out=response.getWriter();		
		out.print("<select name='cbrango' size='1' id='cbrango'onchange='cambio()'>");
		String seleccione="SELECCIONE...";
		try {
			out.print("<option selected>"+seleccione+"</option>");
			while(rs.next()){
				out.println("<option>"+rs.getString(1)+"-"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
			out.print("</select>");
	       } catch (SQLException e) {
		       // TODO Auto-generated catch block
		      e.printStackTrace();
	       }
	    }


}
