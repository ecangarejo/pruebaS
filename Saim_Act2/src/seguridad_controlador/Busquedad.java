/**
 * CONTROLADOR: Busquedad se encuentra el proceso para
 * busqueda de los permisos que tiene es usuario al cual se le van actualizar.
*/
package seguridad_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seguridad_logica.MetodoModulos;

/**
 * Servlet implementation class Busquedad
 */
public class Busquedad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest re, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		MetodoModulos mo=new MetodoModulos();
		String codigo=re.getParameter("ced");
		ResultSet rs=null;
		ResultSet rs1=null;
		String NombreMenu="";
		String OpcionMenu="";
		String NombreMenuComparacion="";
		String OpcionMenuComparacion="";
		rs=mo.permisos(codigo);
		PrintWriter out=res.getWriter();
		int contador=0;		
		try {
			//int c=0;	
			out.println("<br><center><table id='prueba'  name='prueba' width='650px' border='1' cellpadding='2'><tr id='cabecera1' class='style6' align='center' ><td  width='600'>"+"Nombre"+"</td><td  width='600'>"+"(Seleccionar Todos"+"<input type='checkbox' name='all' id='all' onclick='checkAll();'  />"+")"+"</td></tr>");
			while(rs.next()){
				NombreMenu=rs.getString(5);
				OpcionMenu=rs.getString(4);
				NombreMenuComparacion=rs.getString(5);
				OpcionMenuComparacion=rs.getString(4);
				//out.print("NombreMenu "+NombreMenu);
			//	out.print("OpcionMenu "+OpcionMenu);
				//out.print("NombreMenuComparacion "+NombreMenuComparacion);
				//out.print("OpcionMenuComparacion "+OpcionMenuComparacion);
				//out.print("<tr><td colspan='2'>ggggg</td></tr>");
				//if(NombreMenu.equals(NombreMenuComparacion)){					
					//while(OpcionMenu!=OpcionMenuComparacion){
						out.print("<tr><td colspan='2'>"+NombreMenu+">>"+OpcionMenu+"</td></tr>");
						out.print("<tr class='style6'><td name='tu'>"+rs.getString(1)+"</td><td align='center' ><input type='checkbox'name='ca' id='ca' value="+rs.getString(1)+" ><input name='fe' id='fe' type='hidden' value="+rs.getString(2)+"><input name='fu' id='fu' type='hidden' value="+rs.getString(3)+"></td></tr>");
					//}
				//}
				//out.println("<tr class='style6'><td name='tu'>"+rs.getString(1)+"</td><td align='center' ><input type='checkbox'name='ca' id='ca' value="+rs.getString(1)+" ><input name='fe' id='fe' type='hidden' value="+rs.getString(2)+"><input name='fu' id='fu' type='hidden' value="+rs.getString(3)+"></td></tr>");
				//out.println("<input name='fe' id='fe' type='hidden' value="+rs.getString(2)+">");
				//out.println("<input name='fu' id='fu' type='hidden' value="+rs.getString(3)+">");
				contador=contador+1;
			}
			
			rs.getStatement().getConnection().close();	
			
			
		out.println("</table></center>");
		out.println("<input name='yo' type='hidden' value="+contador+">");
				
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
}