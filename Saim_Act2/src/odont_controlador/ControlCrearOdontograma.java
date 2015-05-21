package odont_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import odont_metodo.MetodoCrearOdontograma;


public class ControlCrearOdontograma extends HttpServlet{
	
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlCrearOdontograma() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); // respuesta
		String tipodocumentopaciente = request.getParameter("TipoDocumento");
		String numeroDocumento = request.getParameter("NumeroDocumento");
		/*
		String indicacion2 = request.getParameter("indicacion2");
		String indicacion3 = request.getParameter("indicacion3");
		String indicacion4 = request.getParameter("indicacion4");
		*/
		String va = request.getParameter("valor");
		System.out.println(va);
		MetodoCrearOdontograma metodos = new MetodoCrearOdontograma();
		ResultSet busqueda_paciente = null;
		ResultSet busqueda_informe = null;
		
	
		//buscar si admision tiene un odontograma
		if(va.equals("buscarAdm")){
			String AdmPac = request.getParameter("AdmPac");
			
			busqueda_paciente = metodos.buscarAdm(AdmPac);
			try {
			if (busqueda_paciente.next()){
				out.print("1");
				System.out.println("encontrado");
			}else{
				out.print("0");
				System.out.println("No encontrado");
			}
			
			busqueda_paciente.getStatement().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(va.equals("1")){// buscar el paciente para odontograma
			
			busqueda_paciente = metodos.BuscarPaciente(tipodocumentopaciente, numeroDocumento);
					
			try {
				
				if (busqueda_paciente.next()) {
					// resultado.next();
					// /////// nombres paciente.....
					out.print("1|"); // existe paciente
					out.print(busqueda_paciente.getString(4) + "|");
					String NombreCompleto = busqueda_paciente.getString(1)
							+ "&nbsp;&nbsp;&nbsp;&nbsp;" + busqueda_paciente.getString(2) + "&nbsp;&nbsp;&nbsp;"
							+ busqueda_paciente.getString(3);
				
					out.print( 
							
							
							"<table border='1' width='100%'>"+
						"	<tr>"+
					            "<td colspan='8'>"+
					                "<div align='center' class='style11' id='cabecera2' >DATOS DEL PACIENTE </div>"+
					            "</td>"+
					       " </tr>"+
					        
					     " <tr>"+
					     "<input type='hidden' name='txtEdad' id='txtEdad'value="+busqueda_paciente.getString(5)+" />"+
					      "<td width='6%'><div align='center'>Nombre del Paciente: </div></td>"+
						  "<td width='24%'><div align='center'>"+NombreCompleto+"</div></td>"+
					      "<td width='3%'><div align='center'>Edad: </div> </td>"+ 
					      "<td width='5%'><div align='center' id='edad'>"+busqueda_paciente.getString(5)+"</div></td>"+
					      "</tr>"+	
					      "<tr>"+
					      "<td> <div align='center'>Entidad:</div> </td>"+
		                  "<td> <div align='center'>"+busqueda_paciente.getString(6)+"</div></td>"+
					      "<td width='3%'><div align='center'>Genero: </div></td>"+ 
					      "<td width='5%'><div align='center' id='genero'>"+busqueda_paciente.getString(7)+"</div></td>"+
					       "<tr>"+
					      "</table>"
);
				} else {
					out.print("2|");
					out.print("El Documento: " + tipodocumentopaciente + "-"
							+ numeroDocumento + " No Posee Registros.");
				}
				busqueda_paciente.getStatement().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else if (va.equals("2")){
			try {
			busqueda_informe = metodos.buscarOdontogramasRealizados(tipodocumentopaciente, numeroDocumento);
			
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
						"<td colspan='3'>" +
						"Nombre del Paciente:&nbsp;&nbsp;&nbsp;&nbsp;" +
						 busqueda_informe.getString(1) + " " +
						"</td>" +
						"</tr>");
				
				out.print("<tr>" +
						"<td colspan='3' >" +
						"Hora y Fecha Del Informe" +
						"</td>" +
						"</tr>");
				
				do{
					   out.print("<tr>" +
					   "<td width='75%'>" +
                       busqueda_informe.getString(3) 
					   +"</td>" +
					   "<td align='center'>"+
					   "<a href='#' " +
                       "onclick='modificarInformesOdontograma(" +
                        busqueda_informe.getString(2)+
                        ","+busqueda_informe.getString(4)+
                        ")'>"+
                       "modificar"+
                       "</a>" +
					   "</td>"+
					   "<td align='center'>"+
					   "<a href='#' " +
                       "onclick='mostrarInformesOdontograma(" +
                        busqueda_informe.getString(2)
                        +")'>"+
					   "visualizar"+
					   "</a>" +
					   "</td>"+
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
		
		else if (va.equals("3")){
			try {
			busqueda_informe = metodos.buscarOdontogramasRealizados(tipodocumentopaciente, numeroDocumento);
			
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
						"<td colspan='3'>" +
						"Nombre del Paciente:&nbsp;&nbsp;&nbsp;&nbsp;" +
						 busqueda_informe.getString(1) + " " +
						"</td>" +
						"</tr>");
				
				out.print("<tr>" +
						"<td colspan='3' >" +
						"Hora y Fecha Del Informe" +
						"</td>" +
						"</tr>");
				
				do{
					   out.print("<tr>" +
					   "<td width='75%'>" +
                       busqueda_informe.getString(3) 
					   +"</td>" +
					   "<td align='center'>"+
					   "<a href='#' " +
                       "onclick='mostrarInformesOdontograma(" +
                        busqueda_informe.getString(2)
                        +")'>"+
					   "visualizar"+
					   "</a>" +
					   "</td>"+
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
