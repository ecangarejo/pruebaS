/**
 * en el controlador ControlAutoCompletaEps se encuentra los procedimientos
 * necesiarios para la busqueda de las eps que tienen convenio con la institucion.
 */
package adm_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAutoCompletar;


/**
 * Servlet implementation class ControlCitasExamen
 */
public class ControlAutoCompletaEps extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MetodoAutoCompletar mac = new MetodoAutoCompletar();
		ResultSet rs=null;
		res.setContentType("text/html;charset=UTF-8");
		String z=req.getParameter("z");
		if(z.equals("2")){
	           String nombre1 =req.getParameter("nombre");
      
         try {
             rs = mac.listar(nombre1);
             String cadena ="";
             cadena="[";
             while(rs.next()){
             	cadena = cadena+"'"+rs.getString(1)+"_"+rs.getString(2)+"'";
             	cadena = cadena +",";	
             }
             
             cadena = cadena+"]";            
             res.getWriter().write(cadena);
             rs.getStatement().getConnection().close();
         }  catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
}
