package odon_odontologia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cat_cateterismo.metodos_cateterismo;

/**
 * Servlet implementation class registroPaciente
 */
public class registroPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registroPaciente() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(
    		HttpServletRequest request,
    		HttpServletResponse response) 
    		throws ServletException, IOException {
    		response.setContentType("text/html;charset=UTF-8");
    		PrintWriter out = response.getWriter(); // respuesta
    		String tipodocumentopaciente = request.getParameter("TipoDocumento");
    		String numeroDocumento = request.getParameter("NumeroDocumento");
    		String va = request.getParameter("valor");
      		metodos_Odontologia metodos = new metodos_Odontologia();
    		ResultSet busqueda_paciente = null;
    		int busqueda_informe = -1;
    		busqueda_paciente = metodos.BuscarPaciente(tipodocumentopaciente,numeroDocumento);
    		String NombreCompleto ="";
    		if (va.equals("1")) {
    		
    			try {
    				
    				if (busqueda_paciente.next()) {
    			//System.out.print("jejeje");		
    					// resultado.next();
    					// /////// nombres paciente.....
       					out.print("1|"); // existe paciente
    					out.print(busqueda_paciente.getString(4) + "|");
    					 NombreCompleto = busqueda_paciente.getString(1)
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
    					busqueda_informe  = metodos.ultimocodTratamientoOdontologicoRealizado(tipodocumentopaciente, numeroDocumento);
                  //     System.out.print("cccc: "+busqueda_informe);
    					if(busqueda_informe == -1){// el paciente no tiene ningun tratamiento odontologico registrado
    						
    						out.print("|0"); 
    						
    					}
    					
    					else{
    						
    					
    						String nuevoTratamiento="";
    						
    						int estadoInforme = metodos.estadoultimoTratamientoOdontologicoRealizado(String.valueOf(busqueda_informe));
    						String cadevolucion ="";
    						if(estadoInforme == 0){
    							nuevoTratamiento = "1";// insertar nuevo tratamiento
    						    cadevolucion = "-";
    						}
    						else{
    							nuevoTratamiento = "2"; // no insertar nuevo tratamiento
    						    cadevolucion = metodos.evolucionHistoriaOdontologica(String.valueOf(busqueda_informe));
    						}
    						
    						int busqueda_ultima_historia = metodos.ultimocodHistoriaOdontologica(String.valueOf(busqueda_informe));
    						//System.out.print("ultima historia: "+busqueda_ultima_historia +"\n");
    						
    					    int numero_dientes = metodos.numeroDientes();
    					    String cadGeneralidades ="";
    					//    System.out.println("\n");
    					    
    					    for (int i = 0; i < numero_dientes;i++){
    							
    							cadGeneralidades += metodos.seleccionargeneralidadporDiente(String.valueOf(busqueda_ultima_historia), String.valueOf((i+1)))+"@";

    							
    						}
    					  
    					    
    					    String cadAntecedentesprevios = metodos.seleccionarDataAntecedentesMedicos(String.valueOf(busqueda_ultima_historia));
    					    String cadexamenclinico = metodos.seleccionardataexamenclinico(String.valueOf(busqueda_ultima_historia));
    					    
    					    cadGeneralidades = cadGeneralidades.substring(0, cadGeneralidades.length()-1);
    					    
    					 //   System.out.println(""+cadGeneralidades);
    					    
    					    
    						out.print("|"+cadevolucion+"&"+cadGeneralidades+"&"+cadAntecedentesprevios+"&"+cadexamenclinico+"&"+nuevoTratamiento);
    					   
    					  //out.print("|"+"a"+"&"+cadGeneralidades+"&"+cadAntecedentesprevios+"&"+cadexamenclinico+"&"+nuevoTratamiento);

    					}
    					
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
    		else if (va.equals("2")) {
    		
		       try {
    				
    				if (busqueda_paciente.next()) {
    					
    					 NombreCompleto = busqueda_paciente.getString(1)
     							+ " " + busqueda_paciente.getString(2) + " "
     							+ busqueda_paciente.getString(3);
    					
    						Vector <String> informes_realizados = metodos.buscarTratamientosRealizados(tipodocumentopaciente, numeroDocumento);
							if (!informes_realizados.isEmpty()) {
								out.print("<table width='100%' border='1'>" +
										"<tr>" +
										"<td colspan='3'>" +
										"<div align='center' class='style11'id='cabecera2'>" +
										"TRATAMIENTOS ENCONTRADOS " +
										"</div>" +
										"</td>" +
										"</tr>");
								
								out.print("<tr>" +
										"<td width='13%'>" +
										"Nombre del Paciente:" +
										"</td>" +
										"<td width='87%'>"
										+ NombreCompleto + "" +
										"</td>" +
										"</tr>");
								
								out.print("<tr>" +
										"<td colspan='2'>" +
										"Hora y Fecha Del Informe" +
										"</td>" +
										"</tr>");
								Vector <String> historiasEncontradas = new 	Vector <String>();
								int q = 0;
								int ultimaHistoriaPorTratamiento = 0;
								String [] auxhistorias;
								String [] auxTratamientos;
								for(int p = 0; p < informes_realizados.size(); p++){
									
									auxTratamientos = informes_realizados.elementAt(p).split("-");
									ultimaHistoriaPorTratamiento = metodos.obtenerUltimaHistoriaOdontologicaPorTratamiendo(auxTratamientos[0]);
									out.print("<tr>" +
											"<td colspan='2'>" +
											"Tratamiento # "+(p+1)+" ("+auxTratamientos[1]+")"+"  <a href='#' onclick='mostrarResumenOdontologico("+auxTratamientos[0]+","+ultimaHistoriaPorTratamiento+")'> <u>ver resumen</u>" +"</a>"+
											"</td>" +
											"</tr>");
									historiasEncontradas = new 	Vector <String>();
									historiasEncontradas = metodos.buscarHistoriasOdontologicasRealizadas(auxTratamientos[0]);
								
									for( q = 0; q < historiasEncontradas.size(); q++){
										auxhistorias = historiasEncontradas.elementAt(q).split("&");
										
										out.print("<tr>" +
												"<td colspan='2'>" +
												"<a href='#' onclick='mostrarInformeOdontologia("+auxTratamientos[0]+","+auxhistorias[0]+")'>"+auxhistorias[1]+"</a>"+
												
												"</td>" +
												"</tr>");
									//	System.out.print("<a href='#' onclick='mostrarInformeOdontologia("+informes_realizados.elementAt(p)+","+auxhistorias[0]+")'>"+auxhistorias[1]+"</a>\n");
									}
									
									
								}
								
								/*do{
									   out.print("<tr>" +
									   "<td colspan='3'>" +
									   "<a href='#' " +
									   "onclick='mostrarInformesHemodinamia(" +
									   busqueda_informe.getString(2)+")'>"+
									   busqueda_informe.getString(3) 
									   +"</a>" +
									   "</td>" +
									   "</tr>");

								}while (busqueda_informe.next());*/
								out.print("</table>");
								
							}else{
								out.print("El paciente "+NombreCompleto + " con identificacion "+ numeroDocumento+" no tiene ningun tratamiento odontologico realizado"
										);
							}
    				}else{
    					 out.print("El Documento: " + tipodocumentopaciente + "-"
      							+ numeroDocumento + " No se encuentra Registrado.");
    				}
      				busqueda_paciente.getStatement().getConnection().close();
		       }catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
    		}
    			
    			
    		

    		}
    		
    		else if(va.equals("3")){
    			
    			
    		       try {
       				
       				if (busqueda_paciente.next()) {
       					
       					 NombreCompleto = busqueda_paciente.getString(1)
        							+ " " + busqueda_paciente.getString(2) + " "
        							+ busqueda_paciente.getString(3);
       					
       					
       						Vector <String> informes_realizados = metodos.buscarTratamientosRealizados(tipodocumentopaciente, numeroDocumento);
   							if (!informes_realizados.isEmpty()) {
   								out.print("<table width='100%' border='1'>" +
   										"<tr>" +
   										"<td colspan='3'>" +
   										"<div align='center' class='style11'id='cabecera2'>" +
   										"TRATAMIENTOS ENCONTRADOS " +
   										"</div>" +
   										"</td>" +
   										"</tr>");
   								
   								out.print("<tr>" +
   										"<td width='13%'>" +
   										"Nombre del Paciente:" +
   										"</td>" +
   										"<td width='87%'>"
   										+ NombreCompleto + "" +
   										"</td>" +
   										"</tr>");
   								
   								out.print("<tr>" +
   										"<td colspan='2'>" +
   										"Hora y Fecha Del Informe" +
   										"</td>" +
   										"</tr>");
   								Vector <String> historiasEncontradas = new 	Vector <String>();
   								int q = 0;
   								String [] auxhistorias;
   								String [] auxTratamientos;
   								for(int p = 0; p < informes_realizados.size(); p++){
   									auxTratamientos = informes_realizados.elementAt(p).split("-");
   											out.print("<tr>" +
   											"<td colspan='2'>" +
   											"Tratamiento # "+(p+1)+  "        ("+auxTratamientos[1]+")  "+
   											"<a href='#' onclick='eliminarTratamiento("+auxTratamientos[0]+")'> <u>eliminar Tratamiento</u>" +
													"</a>"+
   											
   											"</td>" +
   											"</tr>");
   								historiasEncontradas = new 	Vector <String>();
   									historiasEncontradas = metodos.buscarHistoriasOdontologicasRealizadas(auxTratamientos[0]);
   								
   									for( q = 0; q < historiasEncontradas.size(); q++){
   										auxhistorias = historiasEncontradas.elementAt(q).split("&");
   										
   										out.print("<tr>" +
   												"<td colspan='2'>" +
   												"<a href='#' onclick='mostrarInformeOdontologiaModificacion("+auxTratamientos[0]+","+auxhistorias[0]+","+busqueda_paciente.getString(4)+")'>"+auxhistorias[1]+"</a>"+
   												"            "+"<a href='#' onclick='eliminarHistoria("+auxTratamientos[0]+","+auxhistorias[0]+")'> <u>eliminar Historia</u>"+
   												"</td>" +
   												"</tr>");
   									}
			
   								}
   								
   							
   								out.print("</table>");
   								
   							}else{
   								out.print("El paciente "+NombreCompleto + " con identificacion "+ numeroDocumento+" no tiene ningun tratamiento odontologico realizado"
										);
   							}
       				}else{
   					 out.print("El Documento: " + tipodocumentopaciente + "-"
   							+ numeroDocumento + " No se encuentra Registrado.");
       				}
       				
       				busqueda_paciente.getStatement().getConnection().close();
    		       }catch (SQLException e) {
    		        	// TODO Auto-generated catch block
    		        	e.printStackTrace();
    		    	}
    			
    			
    		}
    		
    		else if (va.equals("4")){
    			
    			String codTratamiento= request.getParameter("idTratamiento");
    			
    			Vector <String> historiasEncontradas = new 	Vector <String>();
    			historiasEncontradas = metodos.buscarHistoriasOdontologicasRealizadas(codTratamiento);
    			int u=0;
    			int r=0;
    			String [] cadaux;
    			int numerodedientes= metodos.numeroDientes();
    			
    			
    			for(u=0;u<historiasEncontradas.size();u++){
    				cadaux=historiasEncontradas.elementAt(u).split("&");
    				metodos.eliminarAntecedentes(cadaux[0]);
    				metodos.eliminarExamenClinico(cadaux[0]);
    				metodos.eliminarImagenesDientesHistoria(cadaux[0]);
    				
    				r=0;
    				
    				
    				while (r < numerodedientes){
    					
    					metodos.cambiarestadogeneralidaddiente(cadaux[0], "0", ""+(r+1));
    					r++;
    				}
    				
    				
    				metodos.eliminarHistoria(cadaux[0]);
    				
    			}
    			
    			metodos.eliminarTratamiento(codTratamiento);
    			
    			
    			 try {
    				 
    					if (busqueda_paciente.next()) {
    						
    						NombreCompleto = busqueda_paciente.getString(1)
    								+ " " + busqueda_paciente.getString(2) + " "
    								+ busqueda_paciente.getString(3);
    					
    				 
				
			
				
				
					Vector <String> informes_realizados = metodos.buscarTratamientosRealizados(tipodocumentopaciente, numeroDocumento);
					if (!informes_realizados.isEmpty()) {
						out.print("<table width='100%' border='1'>" +
								"<tr>" +
								"<td colspan='3'>" +
								"<div align='center' class='style11'id='cabecera2'>" +
								"TRATAMIENTOS ENCONTRADOS " +
								"</div>" +
								"</td>" +
								"</tr>");
						
						out.print("<tr>" +
								"<td width='13%'>" +
								"Nombre del Paciente:" +
								"</td>" +
								"<td width='87%'>"
								+ NombreCompleto + "" +
								"</td>" +
								"</tr>");
						
						out.print("<tr>" +
								"<td colspan='2'>" +
								"Hora y Fecha Del Informe" +
								"</td>" +
								"</tr>");
						historiasEncontradas = new 	Vector <String>();
						int q = 0;
				
						String [] auxhistorias;
						String [] auxTratamientos;
						for(int p = 0; p < informes_realizados.size(); p++){
							auxTratamientos = informes_realizados.elementAt(p).split("-");
								out.print("<tr>" +
									"<td colspan='2'>" +
									"Tratamiento # "+(p+1)+  "        ("+auxTratamientos[1]+")  "+
									"<a href='#' onclick='eliminarTratamiento("+auxTratamientos[0]+")'> <u>eliminar Tratamiento</u>" +
											"</a>"+
									
									"</td>" +
									"</tr>");
							historiasEncontradas = new 	Vector <String>();
							historiasEncontradas = metodos.buscarHistoriasOdontologicasRealizadas(auxTratamientos[0]);
						
							for( q = 0; q < historiasEncontradas.size(); q++){
								auxhistorias = historiasEncontradas.elementAt(q).split("&");
								
								out.print("<tr>" +
										"<td colspan='2'>" +
										"<a href='#' onclick='mostrarInformeOdontologiaModificacion("+auxTratamientos[0]+","+auxhistorias[0]+","+busqueda_paciente.getString(4)+")'>"+auxhistorias[1]+"</a>"+
										"            "+"<a href='#' onclick='eliminarHistoria("+auxTratamientos[0]+","+auxhistorias[0]+")'> <u>eliminar Historia</u>"+
										"</td>" +
										"</tr>");
							//	System.out.print("<a href='#' onclick='mostrarInformeOdontologia("+informes_realizados.elementAt(p)+","+auxhistorias[0]+")'>"+auxhistorias[1]+"</a>\n");
							}
    			
						}
					}	
					else{
						out.print("El paciente "+NombreCompleto + " con identificacion "+ numeroDocumento+" no tiene ningun tratamiento odontologico realizado"
								);
					}
		        	}else{
				 out.print("El Documento: " + tipodocumentopaciente + "-"
			   				+ numeroDocumento + " No se encuentra Registrado.");
	        		}
				busqueda_paciente.getStatement().getConnection().close();
    					
    			
    			
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			
    			
    			
    			
    			
    			
    			
    			
    		}
    		
    		else if(va.equals("5")){
    			
    			
    			
    			
    			
    			String codTratamiento= request.getParameter("idTratamiento");
    			String codHistoria= request.getParameter("idHistoria");
    			Vector <String> historiasEncontradas = new 	Vector <String>();
    			historiasEncontradas = metodos.buscarHistoriasOdontologicasRealizadas(codTratamiento);
    			if(historiasEncontradas.size()==1){
    				
    				metodos.eliminarTratamiento(codTratamiento);
    			}
    			
    			
    			
    			
    			
    			metodos.eliminarAntecedentes(codHistoria);
				metodos.eliminarExamenClinico(codHistoria);
				metodos.eliminarImagenesDientesHistoria(codHistoria);
				int r=0;
				int numerodedientes= metodos.numeroDientes();
				while (r < numerodedientes){
					
					metodos.cambiarestadogeneralidaddiente(codHistoria, "0", ""+(r+1));
					r++;
				}
				
				
				metodos.eliminarHistoria(codHistoria);
    			
   			
				 try {
    				 
 					if (busqueda_paciente.next()) {
 						
 						NombreCompleto = busqueda_paciente.getString(1)
 								+ " " + busqueda_paciente.getString(2) + " "
 								+ busqueda_paciente.getString(3);
 					
 				 
				
			
				
				
					Vector <String> informes_realizados = metodos.buscarTratamientosRealizados(tipodocumentopaciente, numeroDocumento);
					if (!informes_realizados.isEmpty()) {
						out.print("<table width='100%' border='1'>" +
								"<tr>" +
								"<td colspan='3'>" +
								"<div align='center' class='style11'id='cabecera2'>" +
								"TRATAMIENTOS ENCONTRADOS " +
								"</div>" +
								"</td>" +
								"</tr>");
						
						out.print("<tr>" +
								"<td width='13%'>" +
								"Nombre del Paciente:" +
								"</td>" +
								"<td width='87%'>"
								+ NombreCompleto + "" +
								"</td>" +
								"</tr>");
						
						out.print("<tr>" +
								"<td colspan='2'>" +
								"Hora y Fecha Del Informe" +
								"</td>" +
								"</tr>");
						historiasEncontradas = new 	Vector <String>();
						int q = 0;
				
						String [] auxhistorias;
						String [] auxTratamientos;
						for(int p = 0; p < informes_realizados.size(); p++){
							auxTratamientos = informes_realizados.elementAt(p).split("-");
								out.print("<tr>" +
									"<td colspan='2'>" +
									"Tratamiento # "+(p+1)+  "        ("+auxTratamientos[1]+")  "+
									"<a href='#' onclick='eliminarTratamiento("+auxTratamientos[0]+")'> <u>eliminar Tratamiento</u>" +
											"</a>"+
									
									"</td>" +
									"</tr>");
							historiasEncontradas = new 	Vector <String>();
							historiasEncontradas = metodos.buscarHistoriasOdontologicasRealizadas(auxTratamientos[0]);
						
							for( q = 0; q < historiasEncontradas.size(); q++){
								auxhistorias = historiasEncontradas.elementAt(q).split("&");
								
								out.print("<tr>" +
										"<td colspan='2'>" +
										"<a href='#' onclick='mostrarInformeOdontologiaModificacion("+auxTratamientos[0]+","+auxhistorias[0]+","+busqueda_paciente.getString(4)+")'>"+auxhistorias[1]+"</a>"+
										"            "+"<a href='#' onclick='eliminarHistoria("+auxTratamientos[0]+","+auxhistorias[0]+")'> <u>eliminar Historia</u>"+
										"</td>" +
										"</tr>");
							//	System.out.print("<a href='#' onclick='mostrarInformeOdontologia("+informes_realizados.elementAt(p)+","+auxhistorias[0]+")'>"+auxhistorias[1]+"</a>\n");
							}
 			
						}
					}	
					else{
						out.print("El paciente "+NombreCompleto + " con identificacion "+ numeroDocumento+" no tiene ningun tratamiento odontologico realizado"
								);
					}
		        	}else{
				 out.print("El Documento: " + tipodocumentopaciente + "-"
			   				+ numeroDocumento + " No se encuentra Registrado.");
	        		}
				busqueda_paciente.getStatement().getConnection().close();
 					
 			
 			
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 			
    			
    			
    			
    			
    			
    		}

    	}


}
