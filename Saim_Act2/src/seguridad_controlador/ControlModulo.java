/**
 * CONTROLADOR: ControlModulo se encuentra el proceso para
 * controlar las opciones disponibles dependiendo de la opcion escogida.
*/
package seguridad_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import seguridad_logica.MetodoModulos;
public class ControlModulo extends HttpServlet {
	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		MetodoModulos mo=new MetodoModulos();
		String yo=re.getParameter("va");
		String area=re.getParameter("area");		
		String codigo;
		ResultSet rs=null;
		String v[] = new String[1000];
		
		if(yo.equals("1")){
			rs=mo.SQLModulo();
		}
		
		if(yo.equals("2")){
			rs=mo.area(area);
		}
		
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
	   


	
}