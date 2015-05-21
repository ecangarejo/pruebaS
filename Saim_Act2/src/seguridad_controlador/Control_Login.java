/**
 * CONTROLADOR: Control_Login se encuentra el proceso para
 * validar el usuario y la contraseña para ingresar al sistema.
*/
package seguridad_controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import seguridad_logica.Metodo_Usuario;



/**
 * Servlet implementation class Control_Login
 */
public class Control_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		Metodo_Usuario mu= new Metodo_Usuario();
	HttpSession session = req.getSession(true);
	ResultSet rs=null;
	String entro=null;
	String usuario=null;
	String contra=null;
	usuario=req.getParameter("txtusuario");
	contra=req.getParameter("txtcontrasena");

			/**
			 * en esta parte se recibe el nombre de usuario y la contraseña 
			 * del usuario para su posterior consulta mediante
			 * Metodo_Usuario/obtenerUsuario que lleva como variables
			 * el nombre de usuario y contraseña, que se reciben desde
			 * seg_login.jsp. 
			 */
			rs=mu.obtenerUsuario(usuario, contra);
	
			try {
				if(rs.next()){
					entro=rs.getString(1);					
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(entro!=null){
				session.setAttribute("codusuario", entro);
				res.sendRedirect("Admisiones.jsp");
			}
			
			else{
				res.sendRedirect("Seg_login.jsp?ne=0");	
				
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
