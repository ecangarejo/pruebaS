/**
 * Controlador:Actualizar la Admision
 * Actualiza datos de la admision y del acompañanate del paciente a una  Admision previamente realizadas
 * Desarrollado:yosimar valega
 */

package adm_Controlador;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAcompanante;
import adm_logica.MetodoAdmision;


/**
 * Servlet implementation class ControlActAd
 */
public class ControlActAd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Primero Obtenemos todos los datos a Actualizar tanto de la Admision como del Acompañanate que estan en la funcion ActAdm del js Validaciones
	 * luego se llama a procedimiento ActualizarAd/MetodoAdmision y ActualizarAcomp/MetodoAcompanante
	 * con los parametros indicados.
	 * 
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String medio,numauto,autorizado,fecha,registro,hora,remitido,semana,afiliacion,ingreso;
		
		medio=request.getParameter("medio");
		numauto=request.getParameter("numauto");
		autorizado=request.getParameter("autorizado");
		fecha=request.getParameter("fecha");
		registro=request.getParameter("registro");
		hora=request.getParameter("hora");
		remitido=request.getParameter("remitido");
		semana=request.getParameter("semana");
		afiliacion=request.getParameter("afiliacion");
		ingreso=request.getParameter("ingreso");
		
		//contacto
		
		String nombreacon,telefonoacon,parentescoacon,apellidosacon,direccionacon,codigoacon,ceduacon;
		nombreacon="";
		telefonoacon="";
		parentescoacon="";
		apellidosacon="";
		direccionacon="";
		codigoacon="";
		ceduacon="";
		
		nombreacon=request.getParameter("nombreacon");
		telefonoacon=request.getParameter("telefonoacon");
		parentescoacon=request.getParameter("parentescoacon");
		apellidosacon=request.getParameter("apellidosacon");
		direccionacon=request.getParameter("direccionacon");
		codigoacon=request.getParameter("codigoacon");
		ceduacon=request.getParameter("ceduacon");
		
		MetodoAdmision ad=new MetodoAdmision();
		ad.ActualizarAd(ingreso,numauto,medio,autorizado,registro,fecha,hora,afiliacion,remitido,semana);
		
		MetodoAcompanante acon=new MetodoAcompanante();
		acon.ActualizarAcomp(codigoacon, nombreacon, apellidosacon, direccionacon, telefonoacon, ceduacon, parentescoacon);
		
		response.sendRedirect("menuadm.jsp");
		
	}

	

}
