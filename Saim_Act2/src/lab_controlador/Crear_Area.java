/**
 * controlador: Crear_Area se encuentra el proceso para  
 * la insercion de las areas.
*/
package lab_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab_logica.Area;

/**
 * Servlet implementation class Crear_Area
 */
public class Crear_Area extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		
		String area=request.getParameter("area");
		//System.out.println("Listo area..."+area);
		Area a=new Area();
		
		a.insertar(area);
		response.sendRedirect("lab_area.jsp");
	}

	

}
