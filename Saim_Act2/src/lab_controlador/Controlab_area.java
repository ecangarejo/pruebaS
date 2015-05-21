/**
 * controlador: Controlab_area se encuentra el proceso para la 
 * consulta de las areas creadas.
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

import lab_logica.MetodolabPa;
import lab_logica.Metodolabarea;

/**
 * Servlet implementation class Controlab_area
 */
public class Controlab_area extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		Metodolabarea a=new Metodolabarea();
		
		rs=a.labarea();
		String v[] = new String[1000];
		
		try {
			int c=0;
			while(rs.next()){
				
				v[c]=rs.getString(1);
				out.println(v[c]+"_");
				c++;
			}
			rs.getStatement().getConnection().close();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse res)throws ServletException, IOException {
		
	}

}
