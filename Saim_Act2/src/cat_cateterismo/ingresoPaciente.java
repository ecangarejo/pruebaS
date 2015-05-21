package cat_cateterismo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class ingresoPaciente
 */
public class ingresoPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(
		HttpServletRequest request,
		HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // respuesta
		String tipodocumentopaciente = request.getParameter("TipoDocumento");
		String numeroDocumento = request.getParameter("NumeroDocumento");
		String va = request.getParameter("valor");
		metodos_cateterismo metodos = new metodos_cateterismo();
		ResultSet busqueda_paciente = null;
		ResultSet busqueda_informe = null;
		if (va.equals("1")) {
			busqueda_paciente = metodos.BuscarPaciente(
					tipodocumentopaciente,
					numeroDocumento);
			try {
				if (busqueda_paciente.next()) {
					// resultado.next();
					// /////// nombres paciente.....
					out.print("1|"); // existe paciente
					out.print(busqueda_paciente.getString(4) + "|");
					String NombreCompleto = busqueda_paciente.getString(1)
							+ " " + busqueda_paciente.getString(2) + " "
							+ busqueda_paciente.getString(3);
				
					out.print( 
							     "<table border='1' width='100%'>" +
							       "<tr>" +
                                       "<td>"+
                                       "<div align='center' class='style11' id='cabecera2' >DATOS DEL PACIENTE " +
                                        "</div>" +
                                       "</td>" +
                                   "</tr>"+
                                   "</table>"+
                                   "<table border='1'> " +
							        "<tr>"+
							        "<td width='13%'>Nombre del Paciente: </td>" +
							        "<td width='28%'align='center'>"
							         + NombreCompleto
							      + "</td>" +
							      						        
							
							  
							      "<td width='4%'>Entidad: </td>" +
							        "<td width='28%' align='center'>"
							       + busqueda_paciente.getString(6) + 
							        "</td>" +
		
							   
                                  "<td width='3%'>Edad: </td> " +
                                  "<td width='3%' align='center'>"
                                  + busqueda_paciente.getString(5)
                                  +"</td>" +
                                  "</tr>"+						       
    						      "</table>");
							
				               
					
					
					/*
					 * out.print(
					 * "<tr><td>Estado</td><td>Hora y Fecha Del Registro </td></tr>"
					 * ); ///////// estado paciente....... String Estado =
					 * busqueda_paciente.getString(10);
					 * 
					 * if(Estado.equals("0")){ out.print(
					 * "<tr><td bgcolor='#00CC33' style='color:#FFFFFF'>Activa</td>"
					 * );//<td><a href='#'onclick=
					 * 'mostarAdmision("+busqueda_paciente.getString(7);busqueda_paciente.getString(4)+")'>"+rs.getString(5)+"
					 * "+rs.getString(6)+"</a></td></tr>"); }
					 * if(Estado.equals("1")){ out.print(
					 * "<tr><td bgcolor='#CC0000' style='color:#FFFFFF'>Inactiva</td>"
					 * );//<td><a href='#'onclick=
					 * 'mostarAdmision("+rs.getString(4)+")'>"+rs.getString(5)+"
					 * "+rs.getString(6)+"</a></td></tr>"); }
					 */
	
				
				} else {
					out.print("2|");
					out.print("El Documento: " + tipodocumentopaciente + "-"
							+ numeroDocumento + " No Posee Registros.");
				}
				busqueda_paciente.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (va.equals("2")) {// modulo consultas
		
			try {
				busqueda_informe = metodos.buscarInformesCateterismosRealizados(tipodocumentopaciente, numeroDocumento);
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
						   "onclick='mostrarInformesHemodinamia(" +
						   busqueda_informe.getString(2)+")'>"+
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
				busqueda_informe.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		if (va.equals("3")) { // modulo modificacion
			
			
			try {
				busqueda_informe = metodos.buscarInformesCateterismosRealizados(tipodocumentopaciente, numeroDocumento);
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
						   "onclick='modificarInformeHemodinamia(" +
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
				busqueda_informe.getStatement().getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		

	}

}
