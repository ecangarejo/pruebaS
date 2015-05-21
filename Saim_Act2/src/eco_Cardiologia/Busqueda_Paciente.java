package eco_Cardiologia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Busqueda_Paciente
 */
public class Busqueda_Paciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Busqueda_Paciente() {
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
		String va = request.getParameter("valor");
		System.out.println(va);
		Metodos_cardiologia metodos = new Metodos_cardiologia();
		ResultSet busqueda_paciente = null;
		ResultSet busqueda_informe = null;
		
	
		if(va.equals("1")){// buscar el paciente para ecocardiograma
			
			busqueda_paciente = metodos.BuscarPaciente(tipodocumentopaciente, numeroDocumento);
					
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
							
							
							"<table border='1' width='100%'>"+
						"	<tr>"+
					            "<td colspan='8'>"+
					                "<div align='center' class='style11' id='cabecera2' >DATOS DEL PACIENTE </div>"+
					            "</td>"+
					       " </tr>"+
					        
					     " <tr>"+
					      "<td width='6%'><div align='center'>Nombre del Paciente: </div></td>"+
						  "<td width='24%'><div align='center'>"+NombreCompleto+"</div></td>"+
					      "<td width='3%'><div align='center'>Edad: </div> </td>"+ 
					      "<td width='5%'><div align='center' id='edad'>"+busqueda_paciente.getString(5)+"</div></td>"+
					      "<td width='3%'> <div align='center'>Talla: </div></td> "+
					      
					      //document.getElementById("a").innerHTML
					      
					      "<td width='4%'> <div align='center'><input id='txt_talla' type='text' maxlength='3' size='2' onkeyup='calcularSuperficie()' />cm </div></td>"+
					      "<td width='3%'><div align='center'>Peso: </div> </td>"+ 
					      "<td width='4%'><div align='center'><input id='txt_peso' type='text' maxlength='3' size='2' onkeyup='calcularSuperficie()'/>kg </div></td>"+
					      "</tr>"+	
					      "<tr>"+
					      "<td> <div align='center'>Entidad:</div> </td>"+
		                  "<td> <div align='center'>"+busqueda_paciente.getString(6)+"</div></td>"+
					      "<td width='3%'><div align='center'>Genero: </div></td>"+ 
					      "<td width='5%'><div align='center' id='genero'>"+busqueda_paciente.getString(7)+"</div></td>"+
					      "<td width='3%'> <div align='center'>Sup. Corp: </div></td>"+ 
					      "<td width='4%' colspan='3'> <div align='center'><input id='txt_sup_corp' type='text' maxlength='3' size='2' disabled='disabled' />kg/ m2 </div></td>"+
					      "<tr>"+
					      "</table>"
);
							
				               
					
					
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
			
			
		}
		else if (va.equals("2")){
			try {
			busqueda_informe = metodos.buscarInformesDeCardiologiaRealizados(tipodocumentopaciente, numeroDocumento);
			
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
					   "onclick='mostrarInformesCardiologia(" +
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
		
	}


}
