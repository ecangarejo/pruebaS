/**
 * NOMBRE DE LA CLASE: ControlArea
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario para 
 * 				crear un area. 				
 */
package adm_Controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.Conexion;
import adm_logica.MetodoArea;
//import Logica.MetodoAcompanante;


public class ControlArea extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest re, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se recibe el dato por parametro desde el Adm_Area.jsp
		 * para posteriormente enviarlo al MetodoArea/insertar
		 * para su insercion.
		 */
		res.setContentType("text/html;charset=UTF-8");
		String nombre;		
		nombre = re.getParameter("area");					
		MetodoArea ma=new MetodoArea();
		ma.insertar(nombre);			
		res.sendRedirect("Adm_Area.jsp");				
	}
	
	

}