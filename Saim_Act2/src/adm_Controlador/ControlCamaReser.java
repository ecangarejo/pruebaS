/**
 * NOMBRE DE LA CLASE: ControlCamaReser
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este Controlador se encuentra lo necesario para 
 * 				generar un reporte de las camas reservadas. 				
 */
package adm_Controlador;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import adm_bean.Remision;
import adm_logica.MetodoLiberarReser;


public class ControlCamaReser  extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/**
	 * se genera un reporte mediante una consulta a 
	 * MetodoLiberarReser/obtenerCensoReserv
	 */
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		ResultSet rs = null;
		MetodoLiberarReser ml = new MetodoLiberarReser ();
		rs=ml.obtenerCensoReserv();
		Vector l = new Vector();
		
		String cama,tipodoc,cedula,apellido,segundoape,nombre,eps,fecha,hora,numeroauto,autoripor,area,subarea;
		
		try{
			while(rs.next()){
				Remision r = new Remision();			
				cama=rs.getString(1);
				r.setCama(cama);
				tipodoc=rs.getString(2);
				r.setTipodocumento(tipodoc);
				cedula=rs.getString(3);
				r.setCedula(cedula);
				apellido=rs.getString(4);
				r.setPapellido(apellido);
				segundoape=rs.getString(5);
				r.setSapellido(segundoape);
				nombre=rs.getString(6);
				r.setNombre(nombre);
				eps=rs.getString(7);
				r.setEps(eps);				
				fecha=rs.getString(8);
				r.setFecha(fecha);				
				hora=rs.getString(9);
				r.setDireccion(hora);
				numeroauto=rs.getString(10);
				r.setNautorizacion(numeroauto);	
				autoripor=rs.getString(11);
				r.setAutorizacion(autoripor);	
				area=rs.getString(12);
				r.setArea(area);
				subarea=rs.getString(13);
				r.setsubArea(subarea);
				l.add(r);
			}
			rs.getStatement().getConnection().close();
			
			
			session.setAttribute("listacenso", l);
			response.sendRedirect("Adm_CensoCamaReser.jsp");
			
			
		}
		catch(Exception ex){
			ex.getMessage();
		}
		
	}

}
