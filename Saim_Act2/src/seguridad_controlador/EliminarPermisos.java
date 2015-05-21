/**
 * CONTROLADOR: EliminarPermisos se encuentra el proceso para
 * eliminar permisos a el usuario escogido.
*/
package seguridad_controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seguridad_logica.Metodo_Usuario;

/**
 * Servlet implementation class EliminarPermisos
 */
public class EliminarPermisos extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigousu=request.getParameter("codusu");
		String codigo=request.getParameter("codigo");		
		Metodo_Usuario me=new Metodo_Usuario();		
		me.eliminar_permisos(codigo,codigousu);
		response.sendRedirect("seg_modificacion.jsp");
	}

}
