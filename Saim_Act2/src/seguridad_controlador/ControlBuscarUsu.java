/**
 * CONTROLADOR: ControlBuscarUsu se encuentra el proceso para
 * el autocompletar de los usuarios activos del sistema.
*/
package seguridad_controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import seguridad_bean.Usuario;
import seguridad_logica.Metodo_Usuario;

/**
 * Servlet implementation class ControlBuscarUsu
 */
public class ControlBuscarUsu extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		 int accion;
	     
	        accion = Integer.parseInt(request.getParameter("txtAccion"));
	
		if(accion == 26){
          
                String cad =request.getParameter("codigo");
              Metodo_Usuario mp = new Metodo_Usuario();
        
             
              
                ResultSet rs=null;
                try {
                    rs = mp.listarParaAutocompletarControl(cad);
                    String cadena ="";
                    String nombre ="";
                    cadena="[";
                    while(rs.next()){
                    	nombre=rs.getString(2)+" "+rs.getString(3)+" - "+rs.getString(4);
                    	cadena = cadena+"'"+rs.getString(1)+"|"+nombre+"'";
                    	cadena = cadena +",";	
                    }
                    
                  
                   
                    cadena = cadena+"]";
                rs.getStatement().getConnection().close();
                //  System.out.println(cadena);
                    response.getWriter().write(cadena);
                } catch (SQLException e) {
                    e.getMessage();
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
            }
		
		
	}

	

}
