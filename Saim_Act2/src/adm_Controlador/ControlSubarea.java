/**
 * NOMBRE DE LA CLASE: ControlSubarea
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario
 * 				para insertar una subarea. 				
 */
package adm_Controlador;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoSubarea;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class ControlSubarea extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se obtienen las areas mediante una consulta
		 * 	MetodoSubarea/SQLArea
		 */
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		MetodoSubarea sa = new MetodoSubarea();
        ResultSet rs=null;
		rs=sa.SQLArea();
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
	
	
	public void doGet(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * 	se escoge el codigo del area escogida para posteriormente
		 *	insertarlo en la base de datos mediante MetodoSubarea/SQLCodArea
		 * 	posteriormente se hace la insercion de la subarea mediante
		 *	MetodoSubarea/insertar que lleva como parametro el nombre de la subarea
		 *	y el codigo del area.
		 * 
		 */
		res.setContentType("text/html;charset=UTF-8");
		String subarea=null;
		String nombre=null;
		PrintWriter out=res.getWriter();
		nombre = re.getParameter("nombre");	
		subarea=re.getParameter("subarea");		
		MetodoSubarea sa = new MetodoSubarea();
	    ResultSet rs=null;
	    rs=sa.SQLCodArea(nombre);	
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
		sa.insertar(subarea,v[0]);	
		res.sendRedirect("Adm_subarea.jsp");			
	}
}
