package pyp_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;



import pyp_metodo.MetodoFormPyp;


public class ControlForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String tp=req.getParameter("TipoDocumento");
		String num=req.getParameter("NumeroDocumento");
		String va = req.getParameter("valor");
		
		String Tipo_Documento="";
		String Numero_Documento="";
		
		ResultSet rs=null;
		MetodoFormPyp metodos=new MetodoFormPyp();
		ResultSet busqueda_paciente = null;
		PrintWriter out=res.getWriter();
		
		HttpSession session = req.getSession(true);
		
		String tipodocumentopaciente = req.getParameter("TipoDocumento");
		String numeroDocumento = req.getParameter("NumeroDocumento");
		
		if(va.equals("1")){
		
			busqueda_paciente = metodos.BuscarPaciente(tipodocumentopaciente,numeroDocumento);
			String NombreCompleto = "";	
			rs=metodos.BuscarPaciente(tp, num);
			int verif=0;
			String codpac="0";
			
			try {
				//Recorre 
				if(rs.next()){
					Tipo_Documento=rs.getString("tipo_documento");
					Numero_Documento=rs.getString("numero_documento");
					//session = req.getSession(false);
					//session.setAttribute("tipodocumentopaciente", tipodocumentopaciente);
					//session.setAttribute("numeroDocumento", numeroDocumento);
					
					codpac=rs.getString(1);
					verif=1;
				
					if (busqueda_paciente.next()) {
						// resultado.next();
						// /////// nombres paciente.....
						
						session = req.getSession(false);
						session.setAttribute("TipoDocumento", Tipo_Documento);
						session.setAttribute("NumeroDocumento", Numero_Documento);
						
						
						out.print("1|"); // existe paciente
						out.print(busqueda_paciente.getString(4) + "|");
						  NombreCompleto = busqueda_paciente.getString(1)
								+ " " + busqueda_paciente.getString(2) + " "
								+ busqueda_paciente.getString(3);
					
						out.print( 
								     "<table width='100%'>" +
								       "<tr>" +
	                                       "<td>"+
	                                       "<div align='center' class='style11' id='cabecera2' >DATOS DEL PACIENTE " +
	                                        "</div>" +
	                                       "</td>" +
	                                   "</tr>"+
	                                   "</table>"+
	                                   "<table> " +
								        "<tr>"+
								        "<td width='13%'>Nombre del Paciente: </td>" +
								        "<td width='28%' align='center'>"
								         + NombreCompleto
								      + "</td>" +							      					
								      
								      
								      "<td width='4%'>Genero: </td>" +
								      "<td width='28%' align='center'><input type='hidden' id='txtGenValida' value="+busqueda_paciente.getString("genero")+" >" +
								      busqueda_paciente.getString("genero") + 
								      "</td>" +
								      
								      "<td width='4%'>Entidad: </td>" +
								        "<td width='28%' align='center'>"
								       + busqueda_paciente.getString("nombre_entidad") + 
								        "</td>" +
			
								   
	                                  "<td width='3%'>Edad: </td> " +
	                                  "<td width='3%' align='center'>"
	                                  + busqueda_paciente.getString("Edad")
	                                  +"</td>" +
	                                  "</tr>"+						       
	    						      "</table>");
						//res.sendRedirect("pyp_FormatoHMI.jsp?TipoDocumento="+Tipo_Documento+"&NumeroDocumento="+Numero_Documento);
					
						
					}//Termina Primer IF
					
					
			
					
					
				}else{
					
					session = req.getSession(false);
					session.setAttribute("TipoDocumento", Tipo_Documento);
					session.setAttribute("NumeroDocumento", Numero_Documento);
					
					out.print(verif+"|"+codpac);
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//response.sendRedirect(pa+"?va=1&codigo="+"");
			//out.print("prueba");
			
		}

	}
	}





	

