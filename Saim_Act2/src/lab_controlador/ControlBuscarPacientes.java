/**
 * controlador: ControlBuscarPacientes se encuentra el proceso para la 
 * busqueda de los pacientes ya sea por el numero de la identificacion 
 * o por el nombre.
*/
package lab_controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab_logica.MetodolabPa;

/**
 * Servlet implementation class ControlBuscarPacientes
 */
public class ControlBuscarPacientes extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		 int accion;
	     
	        accion = Integer.parseInt(request.getParameter("txtAccion"));
	
		if(accion == 26){
			String cad =request.getParameter("codigo");
			String tipo =request.getParameter("tipo");
			MetodolabPa mp = new MetodolabPa(); 
			ResultSet rs=null;
			try {
				rs = mp.listarParaAutocompletarControl(cad,tipo);
				String cadena ="";
				String nombre ="";
				cadena="[";
				while(rs.next()){
					nombre=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4);
					cadena = cadena+"'"+rs.getString(1)+"|"+nombre+"'";
                   	cadena = cadena +",";	
				}
                   cadena = cadena+"]";
                   rs.getStatement().getConnection().close();
                   response.getWriter().write(cadena);
               } catch (SQLException e) {
                   e.getMessage();
               } catch (Exception e) {
					e.printStackTrace();
				}
	
           }
		
		if(accion == 27){
			String nombre =request.getParameter("nombre");         
			MetodolabPa mp = new MetodolabPa();        
			ResultSet rs=null;
			try {
				rs = mp.listarParaAutocompletarControl_nombre(nombre);
				String cadena ="";
                String nombre1 ="";
                cadena="[";
                while(rs.next()){
                	nombre1=rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(5);
                	cadena = cadena+"'"+nombre1+"|"+rs.getString(1)+"|"+rs.getString(4)+"'";
                	cadena = cadena +",";	
                }
                cadena = cadena+"]";
                rs.getStatement().getConnection().close();
                response.getWriter().write(cadena);
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
					e.printStackTrace();
			}
	
        }
		
		
	}

}
