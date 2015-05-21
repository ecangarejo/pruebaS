/**
 * Control:ControlMuni
 * Obtiene la ocupacion del paciente
 * Desarrollado:yosimar valega
 */

package adm_Controlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoNacionalidad;

public class ControlMuni extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		String ocupacion=re.getParameter("ocu");
		String nombreocu=re.getParameter("x");
		MetodoNacionalidad na=new MetodoNacionalidad();
		PrintWriter tu=res.getWriter();
		/**
		 * Obtiene todas las ocupaciones
		 */	
		
			if(ocupacion.equals("1")){
				ResultSet rs1=null;
				rs1=na.SQLOcupacion();
				
				try {
					String v[] = new String[1000];
					int c=0;
					while(rs1.next()){
						
						v[c]=rs1.getString(1);
						
						tu.println(v[c]+"_");
						c++;
					}
					rs1.getStatement().getConnection().close();	
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			}	
			
			/**
			 * Obtiene el codigo de la ocupacion segun el nombre
			 */
			if(ocupacion.equals("2")){
				ResultSet rs2=null;
				rs2=na.SQLCodOcupacion(nombreocu);
				String ocu="";
				try {
					
					while(rs2.next()){		
						ocu=rs2.getString(1);
						tu.println(ocu);						
					}
					rs2.getStatement().getConnection().close();	
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			}	
	}
	
}
