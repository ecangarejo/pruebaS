/**
 * CONTROLADOR: Modulos2 se encuentra el proceso para
 * asignarle permisos a el usuario escogido.
*/

package seguridad_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import seguridad_logica.MetodoModulos;
public class Modulos2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		MetodoModulos mo=new MetodoModulos();
		String yo=re.getParameter("va");
		String area=re.getParameter("area");
		String area1=re.getParameter("area1");
		String codigo;
		ResultSet rs=null;
		String v[] = new String[1000];
		if(yo.equals("1")){
			rs=mo.SQLModulo();
		}
		if(yo.equals("2")){
			rs=mo.opcion_disponible(area,area1);
		}              
		int contador=0;
		try { 
			int c=0;                  
			out.println("<table id='prueba' align='center' name='prueba' width='763px' border='1' cellpadding='2'><tr id='cabecera1' align='center' class='style6' ><td  width='600'>"+"Nombre"+"</td><td  width='600'>"+"(Seleccionar Todos"+"<input type='checkbox' name='all' id='all' onclick='checkAll();'  />"+")"+"</td></tr>");
			while(rs.next()){
				out.println("<tr class='style6'><td name='tu'>"+rs.getString(1)+"</td><td><input type='checkbox' name='ca' id='ca' value="+rs.getString(1)+" ></td></tr>");
				out.println("<input name='fe' id='fe' type='hidden' value="+rs.getString(2)+">");
				contador=contador+1;
			}
			out.println("<input type='checkbox'name='ca'  id='ca'style='visibility:hidden' >");
			out.println("<input name='fe' id='fe' type='hidden'>");
			out.println("</table>");
			out.println("<input name='yo' type='hidden' value="+contador+">");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
