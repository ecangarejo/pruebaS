/**
 * NOMBRE DE LA CLASE: ControlCodigoSubarea
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra para generar el codigo de la subarea				
 */
package adm_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adm_logica.MetodoCrearCama;


public class ControlCodigoSubarea extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se obtiene el codigo de la subarea mediante una consulta
		 * MetodoCrearCama/obtenerCodSubArea que lleva como parametro el nombre de la subarea.
		 */
		res.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(true);
		MetodoCrearCama ba= new MetodoCrearCama();
		ResultSet rs1=null;
		
		String sub=null;
		sub = req.getParameter("nomsub");
		rs1=ba.obtenerCodSubArea(sub);

		String e[] = new String[1000];
		try{
			int t=0;
			while(rs1.next()){
				PrintWriter out=res.getWriter();
				e[t]=rs1.getString(1);
				out.println(e[t]);
			
				t++;
			}
			rs1.getStatement().getConnection().close();
		
		}
		 catch(SQLException e1){
			 e1.getMessage();
		 }
		
	}

}

