/**
 * Control:ControlRemision
 * Ingreso de pacientes por una remision
 * Desarrollado:yosimar valega
 */

package adm_Controlador;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.Conexion;
import adm_logica.MetodoPreingreso;
import adm_logica.MetodoRemision;


import java.sql.*;
import java.util.*;

public class ControlRemision extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Se ontiene los datos de remison/Validaciones para ingresar insertar/MetodoRemision a la tabla preadmision de la base de datos
	 * Se reserva la Actualizar/MetodoRemision cama escogida en la remision se coloca el estado en 2
	 */
		public void doGet(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
			String ced, nom, pape,sape,eps,fecha,hora,tipodoc,numeroauto,autorizado,cama,estadore;
			res.setContentType("text/html;charset=UTF-8");
		    eps = new String();
			ced = re.getParameter("ced");
			tipodoc=re.getParameter("ti");
			nom = re.getParameter("nom");
			pape = re.getParameter("pape");
			sape = re.getParameter("sape");
			eps = re.getParameter("ep");
			numeroauto = re.getParameter("num");
			autorizado = re.getParameter("aut");
			cama=re.getParameter("cama");
			estadore="0";
		
			fecha =re.getParameter("fe");
			hora =re.getParameter("ho");
			 
			
			MetodoRemision ba = new MetodoRemision();
			ba.insertar(ced,eps, nom,  pape, tipodoc,sape,  fecha,  hora, numeroauto,autorizado,cama,estadore);
			ba.Actualizar(cama);
			
			
			
		}
		/**
		 * Muestra las eps de la base de tados esto se llama desde 1.js
		 */
		public void doPost(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
			MetodoRemision ba = new MetodoRemision();
			ResultSet rs=null;
			rs=ba.SQLEntidad();	
			String v[] = new String[1000];
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out=res.getWriter();
			try {
				int c=0;
				while(rs.next()){					
					v[c]=rs.getString(1);					
					out.println(v[c]+"_");
					c++;
				}
				rs.getStatement().getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
}