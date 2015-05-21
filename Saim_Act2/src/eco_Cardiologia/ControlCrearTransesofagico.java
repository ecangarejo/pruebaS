package eco_Cardiologia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.GregorianCalendar;

import eco_Cardiologia.MetodoTransesofagico;

/**
 * Servlet implementation class ControlCrearTransesofagico
 */
public class ControlCrearTransesofagico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlCrearTransesofagico() {
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
		MetodoTransesofagico metodos = new MetodoTransesofagico();
		ResultSet busqueda_paciente = null;
		ResultSet busqueda_informe = null;
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		
		//hora y fecha
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		
		String fechacjmysql=annio+"-"+mes+"-"+dia; //para la base de datos

		Calendar calendario = Calendar.getInstance();
		int hora;
		int minutos, segundos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hracjmysql= hora+":"+minutos+":"+segundos; 
		//fin hora y fecha
		
	
		if(va.equals("1")){// buscar el paciente 
			
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
					       "<td colspan='8'>"+
					       
					       //contenido eco-transesofagico
					       "<table border='0' width='100%'>"+
					       "<tr>"+
					       		"<td colspan='3'>"+
					       		"<div align='center' class='style11' id='cabecera2' >ECOCARDIOGRAMA TRANSESOFAGICO</div>"+
					       		"</td>"+
					            "</tr>"+
					            
					          //indicacion
					            "<tr><td align='center'>"+
					            "<label>Indicacion</label></td><td >" +
								"<select name='cmbIndicacion' id='cmbIndicacion' >" +
								"<option value='Seleccione' selected='selected'>Seleccione</option>");
						rs1=metodos.ObtenerIndicacion();
						try {
							while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
							}
							out.print("</select></td><td><div align='center'></div></td></tr>");
							rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						}
						out.print( 
					            
					           "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >AURÍCULA IZQUIERDA </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoAurIzq' cols='110' rows='5'>" +
									"De tamaño normal, no se aprecian imágenes de masas en su interior, apéndice " +
									"auricular izquierdo libre de lesión, con adecuadas velocidades " +
									"en su interior, drenaje venoso pulmonar normal." +
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					        //valvula mitral
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >VÁLVULA MITRAL </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoValMitral' cols='110' rows='5'>" +
									"Estructural y funcionalmente normal."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					        //ventriculo izquierdo
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >VENTRÍCULO IZQUIERDO </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoVentIzq' cols='110' rows='5'>" +
									"De tamaño normal, con espesor normal de sus paredes, " +
									"con contractibilidad global y segmentaria preservada, FE 60%."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					        //valvula aortica
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >VÁLVULA AÓRTICA </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoValAort' cols='110' rows='5'>" +
									"Trivalva, estructural y funcionalmente normal"+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					        //aorta
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >AORTA </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoAorta' cols='110' rows='5'>" +
									"En todo su recorrido hasta el nivel infradiafragmático " +
									"normal."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					        //septum interauricular e interventricular
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >SEPTUM INTERAURICULAR E INTERVENTRICULAR </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoSeptum' cols='110' rows='5'>" +
									"Íntegros."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					        //VENTRICULO DERECHO
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >VENTRICULO DERECHO </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtVentDer' cols='110' rows='5'>" +
									"De tamaño y función normal."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					        //auricula derecha
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >AURÍCULA DERECHA </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoAurDer' cols='110' rows='5'>" +
									"De tamaño y morfología normal."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					        //valvula tricuspidea
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >VALVULA TRICUSPIDEA </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoValTri' cols='110' rows='5'>" +
									"Estructural y funcionalmente normal."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					        //valvula pulmonar
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >VALVULA PULMONAR </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtValPul' cols='110' rows='5'>" +
									"Estructural y funcionalmente normal."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					        //pericardio
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >PERICARDIO </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoPericardio' cols='110' rows='5'>" +
									"No se detectó líquido en el espacio pericárdico."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					        //observaciones
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >OBSERVACIONES </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoObs' cols='110' rows='5'>" +
									"NO SE OBSERVAN MASAS NI TROMBOS INTRACAVITARIOS."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					        //conclusiones
					            "<tr>"+
								"<td colspan='3' align='center'>"+
									"<div align='center' class='style11' id='cabecera2' >CONCLUSION </div>"+
					              "</td>"+
					          "</tr>"+
					             "<tr>"+
								"<td colspan='3' align='center'>"+
									"<textarea id='txtEcoCon' cols='110' rows='5'>" +
									"1. Estudio dentro de limites normales."+
									"</textarea> <br>"+
									
					             "</td>"+
					          "</tr>"+
					          
					       "</table>"+//fin tabla eco-transesofagico
					       
					       "</td>"+
					       "</tr>"+
					       
					      " <tr>"+
						//botones para enviar y anular informe 
											"<td style='border-width: 0px' colspan='3' align='center'><input type='button' id='envio'"+
												"value='Enviar Datos' onclick='enviar_informe()' />"+
											"<input type='button' id='anular'"+
												"value='Anular Informe' onclick='anular_informe()' /></td>"+
										"</tr>"+
					       
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
			
			
		}//fin buscar usuario
		
		if(va.equals("2")){//guardar informe
			
			String codigoPaciente =  request.getParameter("codpaciente");
			String codUsuario =  request.getParameter("codusuario");
			String indicacion =  request.getParameter("indicacion");
			String [] observacionesEcoTrans =  request.getParameter("observaciones").split("&");
			boolean insercciones_realizadas_con_exito = false;
			
			//insertar encabezado informe
			insercciones_realizadas_con_exito=metodos.insertarEncabezadoInformeTrans(
					fechacjmysql,
					hracjmysql, 
					codUsuario, 
					codigoPaciente, 
					"1", 
					indicacion
					);
			//fin insertar encabezado informe
			
			String codInformeEcoTrans = ""+metodos.codigoActualInformeEco();
			insercciones_realizadas_con_exito= metodos.asociarObsInformeEcoTrans(codInformeEcoTrans);
			insercciones_realizadas_con_exito=metodos.actualizarObsInformeEcoTrans(codInformeEcoTrans, observacionesEcoTrans);
		
			if(insercciones_realizadas_con_exito == true){
			      out.print("1;Ha realizado correctamente el informe de ecocardiograma transesofagico con fecha "+fechacjmysql+" en la hora "+hracjmysql+";"+codInformeEcoTrans);		  
				  }else{
				  out.print("0;Hubo un error al ingresar los datos al sistema!");
				  }
			
		}//fin guardar informe
		
		
		//buscar informes por paciente
		if (va.equals("3")){
			try {
			busqueda_informe = metodos.buscarInformesDeEcotransRealizados(tipodocumentopaciente, numeroDocumento);
			
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
					   "onclick='mostrarInformesEcotrans(" +
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
			busqueda_informe.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

			
			
		}
		
		
	}

}
