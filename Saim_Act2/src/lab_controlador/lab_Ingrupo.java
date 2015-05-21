/**
 * controlador: lab_Ingrupo se encuentra el proceso para  
 * buscar los codigo de las areas tomando como parametro el nombre del area.
*/
package lab_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab_logica.MetodoResultado;

/**
 * Servlet implementation class lab_Ingrupo
 */
public class lab_Ingrupo extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		MetodoResultado resu= new MetodoResultado();
		String area=request.getParameter("area");
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
		rs=resu.CodArea(area);
		try {
			if(rs.next()){
				out.print(rs.getString(1));
			}
			rs.getStatement().getConnection().close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
	
	}


}
