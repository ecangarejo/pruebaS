/**
 * Control:ControlPaciente
 * muestra eps de los pacientes
 * 
 * 
 */

package adm_Controlador;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoNacionalidad;
import adm_logica.MetodoPaciente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

public class ControlPaciente extends HttpServlet{
	
	/**
	 * Realiza una series de consultas de eps SQLEps/MetodoPaciente, de convenio segun la eps SQLCodConv/MetodoPaciente para mostrar en una lista y texto
	 */
	
	public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("text/html;charset=UTF-8");
		String yo=re.getParameter("yosi");
		String codigomun=re.getParameter("yo");
		String nomeps=re.getParameter("nomep");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		MetodoPaciente pa=new MetodoPaciente();
		
		if(yo.equals("1")){
			rs=pa.SQLEps();
			String v[] = new String[1000];			
			try {
				int c=0;
				while(rs.next()){					
					v[c]=rs.getString(1);					
					if (v[c].compareTo(nomeps) != 0){
						out.println(v[c]+"_");
					}	
					c++;
				}
				rs.getStatement().getConnection().close();				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		if(yo.equals("2")){			
			rs=pa.SQLCodConv(nomeps);
			String codigo;				
			try {
				int c=0;
				while(rs.next()){
					codigo=rs.getString(1);							
					out.println(codigo);					
				}
				rs.getStatement().getConnection().close();					
			} catch (SQLException e) {					
				e.printStackTrace();
			}
		}		
	}
}