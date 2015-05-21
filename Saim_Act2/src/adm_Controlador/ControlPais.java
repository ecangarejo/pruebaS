/**
 * Control:ControlPais
 * Obtiene paises, departamentos y municipios de la base de datos
 * Desarrollado:yosimar valega
 */
package adm_Controlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoNacionalidad;
public class ControlPais extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		MetodoNacionalidad na = new MetodoNacionalidad();
		String yo = re.getParameter("yo");
		//String nombre = re.getParameter("nom");
		String x = re.getParameter("x");
		//String y = re.getParameter("y");
		String xx = re.getParameter("de");
		String codigo = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		//ResultSet rs3 = null;
		//String v[] = new String[1000];
		//int c;
		PrintWriter out = res.getWriter();
		
		if(yo.equals("1")){//Se obtiene el paises  llamados de una funcion 1.js ajax
			rs = na.SQLPais();
			try {
				//c = 0;
				while(rs.next()){
					/*v[c] = rs.getString(1);
					out.println(v[c]+"_");*/
					out.println(rs.getString(1)+"_");
					//c++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(yo.equals("2")){//Se obtiene Departamentos llamados de una funcion 1.js ajax
			rs = na.SQLDep(x);
			try {
				//c = 0;
				while(rs.next()){
					/*v[c] = rs.getString(1);
					out.println(v[c]+"_");*/
					out.println(rs.getString(1)+"_");
					//c++;
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(yo.equals("3")){//Se obtiene Municipios llamados de una funcion 1.js ajaxa
			//System.out.println("Depto xx: "+xx);
			rs1 = na.SQLCodDepa(xx);
			try {
				while(rs1.next()){
					codigo = rs1.getString(1);
				}
				//System.out.println("codigo: "+codigo);
				rs = na.SQLMun(codigo);
				rs1.getStatement().getConnection().close();
				//c = 0;
				while(rs.next()){
					/*v[c] = rs.getString(1);
					out.println(v[c]+"_");*/
					out.println(rs.getString(1)+"_");
					//System.out.println("ciudad: "+rs.getString(1));
					//c++;
				}
				rs.getStatement().getConnection().close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}else if(yo.equals("cargaDeptos")){
			rs1 = na.getDeptos();
			try {
				while(rs1.next()){
					out.println(rs1.getString(1)+"_");
				}
				rs1.getStatement().getConnection().close();
			} catch(SQLException e1){
				e1.printStackTrace();
			}
		}
	}
}
