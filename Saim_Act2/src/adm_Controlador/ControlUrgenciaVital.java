/**
 * NOMBRE DE LA CLASE: ControlUrgenciaVital
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario
 * 				para ingresar una urgencia vital. 				
 */
package adm_Controlador;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import adm_logica.MetodoUrgenciaVital;



public class ControlUrgenciaVital extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		/**
		 * se obtienen los datos desde el Adm_UrgenciaVital.jsp
		 * y se hace la insercion mediante MetodoUrgenciaVital/insertar
		 * que lleva como parametro los datos a insertar.
		 */
		MetodoUrgenciaVital mvu=new MetodoUrgenciaVital();		
		String fecha=req.getParameter("fecha");		
		String hora=req.getParameter("hora");
		String descripcion=req.getParameter("descripcion");
		String estado=req.getParameter("estado");
	    mvu.insertar(fecha, hora, descripcion, estado);
	    res.sendRedirect("menuadm.jsp");	
	}

}
