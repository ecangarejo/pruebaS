/**
 * controlador: ControlCrearUnidad se encuentra el proceso para la 
 * creacion de las unidades.
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

import lab_logica.MetodoUnidad;

/**
 * Servlet implementation class ControlCrearUnidad
 */
public class ControlCrearUnidad extends HttpServlet {
	//private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		MetodoUnidad mu=new MetodoUnidad ();		
		String va = req.getParameter("valor");		
		ResultSet rs=null;		
		if(va.equals("0")){
			String nombre=req.getParameter("nomb");			
		//	System.out.println("CONTROL "+nombre);
			int i;
			for(i=0;i<nombre.length();i++){
			      nombre=nombre.replace('9','%');
			    }
			
			//System.out.println("CONTROLCREARUNIDAD EL NOMBRE QUE LLEGA "+nombre);
			mu.insertarUnidad(nombre);
			res.sendRedirect("lab_unidades.jsp");
		}
		
		if(va.equals("1")){
			rs=mu.obtenerUnidad();
			//rs=mce.obtenerArea() ;
			String v[] = new String[1000];
			try{
				int c=0;
				while(rs.next()){
					v[c]=rs.getString(1);
						
					if(v[c].equals("ñ")){
						for(int i=0;i<v[c].length();i++){
			     		      v[c]=v[c].replace('ñ','%');
			     		    }
					}
					out.println(v[c]+"_");
					c++;					 
				}	
				rs.getStatement().getConnection().close();
			}
			 catch(SQLException e1){
				 System.out.println("ERROR EN CONTROLADOR_: "+e1);
				 e1.getMessage();
			  }
			}
	}

}
