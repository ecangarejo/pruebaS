package pyp_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pyp_metodo.MetodoFormPyp;

/**
 * Servlet implementation class pypDATA
 */
public class pypModificarData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pypModificarData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // respuesta
		String tipodocumentopaciente = request.getParameter("TipoDocumento");
		String numeroDocumento = request.getParameter("NumeroDocumento");
		String va = request.getParameter("valor");
		MetodoFormPyp pypdata = new MetodoFormPyp();
		ResultSet busqueda_informe = null;
		
	    		if (va.equals("Modificar")) { // modulo modificacion
	    			
	     			try {
	    				busqueda_informe = pypdata.buscarInformesPYPRealizados(tipodocumentopaciente, numeroDocumento);
	    				if (busqueda_informe.next()) {
	    					out.print("<table width='100%' border='1'>" +
	    							"<tr>" +
	    							"<td colspan='3'>" +
	    							"<div align='center' class='style11'id='cabecera2'>" +
	    							"INFORMES ENCONTRADOS " +
	    							"</div>" +
	    							"</td>" +
	    							"</tr>");
	    					
	    					out.print("<tr>" +
	    							"<td width='13%'>" +
	    							"Nombre del Paciente:" +
	    							"</td>" +
	    							"<td width='87%'>"
	    							+ busqueda_informe.getString(1) + "" +
	    							"</td>" +
	    							"</tr>");
	    					
	    					out.print("<tr>" +
	    							"<td colspan='2'>" +
	    							"Hora y Fecha Del Informe" +
	    							"</td>" +
	    							"</tr>");
	    					
	    					do{
	    						   out.print("<tr>" +
	    						   "<td colspan='3'>" +
	    						   "<a href='#' " +
	    						   "onclick='modificarInformePYPPlanFam(" +
	    						   busqueda_informe.getString(2)+","+  busqueda_informe.getString(4)+")'>"+
	    						   busqueda_informe.getString(3) 
	    						   +"</a>" +
	    						   "</td>" +
	    						   "</tr>");

	    					}while (busqueda_informe.next());
	    					out.print("</table>");
	    					
	    				}else{
	    					out.print("El Documento: " + tipodocumentopaciente + "-"
	    							+ numeroDocumento + " No Posee Registros.");
	    				}
	    				busqueda_informe.getStatement().close();
	    			} catch (SQLException e) {
	    				e.printStackTrace();
	    			}
	    			
	    		}
	  
	    		
	    		if (va.equals("Consultar")) { // modulo consulta
	    			
	     			try {
	    				busqueda_informe = pypdata.buscarInformesPYPRealizados(tipodocumentopaciente, numeroDocumento);
	    				if (busqueda_informe.next()) {
	    					out.print("<table width='100%' border='1'>" +
	    							"<tr>" +
	    							"<td colspan='3'>" +
	    							"<div align='center' class='style11'id='cabecera2'>" +
	    							"INFORMES ENCONTRADOS " +
	    							"</div>" +
	    							"</td>" +
	    							"</tr>");
	    					
	    					out.print("<tr>" +
	    							"<td width='13%'>" +
	    							"Nombre del Paciente:" +
	    							"</td>" +
	    							"<td width='87%'>"
	    							+ busqueda_informe.getString(1) + "" +
	    							"</td>" +
	    							"</tr>");
	    					
	    					out.print("<tr>" +
	    							"<td colspan='2'>" +
	    							"Hora y Fecha Del Informe" +
	    							"</td>" +
	    							"</tr>");
	    					
	    					do{
	    						   out.print("<tr>" +
	    						   "<td colspan='3'>" +
	    						   "<a href='#' " +
	    						   "onclick='mostrarInformesPYPPlanFam(" +
	    						   busqueda_informe.getString(2)+","+  busqueda_informe.getString(4)+")'>"+
	    						   busqueda_informe.getString(3) 
	    						   +"</a>" +
	    						   "</td>" +
	    						   "</tr>");

	    					}while (busqueda_informe.next());
	    					out.print("</table>");
	    					
	    				}else{
	    					out.print("El Documento: " + tipodocumentopaciente + "-"
	    							+ numeroDocumento + " No Posee Registros.");
	    				}
	    				busqueda_informe.getStatement().close();
	    			} catch (SQLException e) {
	    				e.printStackTrace();
	    			}
	    			
	    		}
	
	
	
	
	
	}
	
	}
  


