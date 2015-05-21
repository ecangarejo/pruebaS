/**
 * controlador: Controimg_area se encuentra el proceso para la 
 * busqueda del paciente con su autocompletar segun el documento.
*/
package img_controlador;

import img_logica.MetodoimgPa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Controimg_area
 */
public class Controimg_area extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String va=request.getParameter("valor");
		String cedula=request.getParameter("cedula");
		String tipodoc=request.getParameter("tipo");
		ResultSet rs1=null;	
		//String nombre="";
		//String codigo="";
		MetodoimgPa impa=new MetodoimgPa();
		
		if(va.equals("1")){
			rs1=impa.SQLimgPac(cedula, tipodoc);
			try {
				
				if(rs1.next()){					
					//nombre=rs1.getString(1)+" "+rs1.getString(2);
					//codigo=rs1.getString(3);
					response.sendRedirect("img_asignar.jsp?numero="+cedula);
				}else{
					response.sendRedirect("img_paciente.jsp?id="+cedula+"");
				}
				rs1.getStatement().getConnection().close();
			} catch (SQLException e) {
				System.out.println("Error en Controlador "+e);
				e.printStackTrace();
			}
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
			ResultSet rs=null;	
			String cad =request.getParameter("codigo");
			String tipo =request.getParameter("tipoDoc");  
			MetodoimgPa img=new MetodoimgPa();	
			res.setContentType("text/html;charset=UTF-8");
		 int accion;
	     
	        accion = Integer.parseInt(request.getParameter("txtAccion"));
	if(accion == 26){          
    
            try {
                rs =img.listarParaAutocompletarControl(cad,tipo);
                String cadena ="";
                String nombre ="";
                cadena="[";
                while(rs.next()){
                	nombre=rs.getString(2)+" "+rs.getString(3);
                	cadena = cadena+"'"+rs.getString(1)+"|"+nombre+"'";
                	cadena = cadena +",";	
                }
                cadena = cadena+"]";
                res.getWriter().write(cadena);
                rs.getStatement().getConnection().close();
            } catch (Exception e) {
                e.getMessage();
				e.printStackTrace();
				}
	
        }
		
		
	
		
		
	}

}
