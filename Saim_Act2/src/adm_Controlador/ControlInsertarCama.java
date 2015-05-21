/**
 * NOMBRE DE LA CLASE: ControlInsertarCama
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra la isntruccion para crear una cama. 				
 */
package adm_Controlador;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoCrearCama;

public class ControlInsertarCama extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("null")
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	/**
	 * se reciben los datos desde Adm_cama.jsp y se insetan mediante
	 * MetodoCrearCama/insertar y lleva como parametros todos los datos.
	 */
		res.setContentType("text/html;charset=UTF-8");
		MetodoCrearCama ca = new MetodoCrearCama();
		String tipohabitacion1 = null ;
		String area1 = null ;
		String subarea = null;
		String piso_ubicacion = null;
		String tipo_cama = null;
		String observaciones = null;
		String cod_estado = null;
		String cod_subarea = null;
		String cod_cama = null;
		String posy=null;
		String posx=null;
		String posicion=null;
		String Direccionamiento=null;
					
			tipohabitacion1=req.getParameter("tipohabitacion");
			area1=req.getParameter("area");	
			subarea=req.getParameter("subarea");	
			piso_ubicacion=req.getParameter("piso_ubicacion");	
			tipo_cama=req.getParameter("tipo_cama");
			observaciones=req.getParameter("observaciones");
			cod_estado=req.getParameter("cod_estado");
			cod_subarea=req.getParameter("cod_subarea");
			cod_cama=req.getParameter("cod_cama");
			posy=req.getParameter("posy");
			posx=req.getParameter("posx");
			posicion=req.getParameter("posicion");
			Direccionamiento=req.getParameter("Direccionamiento");
							
			ca.insertar(tipohabitacion1, area1, subarea,piso_ubicacion,tipo_cama,observaciones,cod_estado,cod_subarea,cod_cama,posx,posy,posicion,Direccionamiento);
										
		res.sendRedirect("Adm_cama.jsp");
		
		
		
	}
	
		

}
