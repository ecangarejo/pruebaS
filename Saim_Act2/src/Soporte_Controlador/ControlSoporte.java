package Soporte_Controlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import soporte_metodo.MetodosProblemas;
import soporte_metodo.MetodosSolicitudes;



public class ControlSoporte extends HttpServlet {
	private static final long serialVersionUID = 1l;
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String va = req.getParameter("valor");
		
		String EstDet = req.getParameter("EstDet");
		String Observaciones = req.getParameter("Observaciones");
		String Problemas = req.getParameter("problema");
		String codigous = req.getParameter("codigous");
		String Codigou = req.getParameter("Codigou");
		String TipoSolicitud = req.getParameter("TipoSolicitud");
		String CodSubArea = req.getParameter("codsubarea");
		String codiprioridad = req.getParameter("codiprioridad");
		String horaactual = req.getParameter("horaactual");
		String fechaactual = req.getParameter("fechaactual");
		String Estado = req.getParameter("Estado");
		String CodProblema = req.getParameter("prob");
		String CodArea=req.getParameter("CodArea");
		int CodDetSolicitud = 0;
		int CodSolicitud = 0;
		MetodosSolicitudes ms = new MetodosSolicitudes();
		MetodosProblemas mp= new MetodosProblemas();
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		ResultSet rs4=null;
		ResultSet rs5=null;
		ResultSet rs99 = null;
		ResultSet rs22 = null;
		ResultSet rs98 = null;
		ResultSet rs8 = null;
		
		int contSol = 0;
    	rs22 = ms.ObtenerCodSolicitudUsuario(codigous);
    	try{
    		while(rs22.next()){
    			contSol++;	
    		}
    		
    		rs22.getStatement().getConnection().close();
    	}catch(SQLException e){
    		e.printStackTrace();
    		
    	}
    	//if(contSol>=3){
    		//out.print("");
    	//}else{
		
    // SE CREA LA EL DISEÑO DE REALIZAR UNA SOLICITUD POR PARATE DEL USUARIO
    if(va.equals("Hardware")){
    	
    
		out.print("<table  class='SolicitudInicial' border='1'>");
				out.print("<tr><td>");
						out.print("<p class='LArea'><b>AREA<b> </p><p class='SelArea'><select  id='area' onchange='MostrarSubArea2()' ><option value='Seleccione'>Seleccione</option> ");
			
				
				rs1 = mp.MostrarAreas();
				try{
					while(rs1.next()){
						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
					}
					rs1.getStatement().getConnection().close();
				}catch(SQLException e){
					e.printStackTrace();
	
			}
				   out.print("</select></p><div id='ActuMedi'></div>");
				   	   
				   out.print("<br><br><br><b><p class='LProblema' align='center'><div id='encselpro'>SELECCIONE EL PROBLEMA QUE PRESENTA</div></p><b><br><br>");
				   out.print("<p class='SelProblemas'><select name='probelmas' id='P1' class='ProbSolicitud'><option value='Seleccione'>Seleccione</option>");
					rs=mp.BuscarProblemasTodos();
					try {
						while(rs.next()){
							out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
					
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</select></p><input type='button' value='Asignar' id='asignar' class='IAsignar' name='btnasignar' onclick='MostrarProblemas()'>");
					out.print("<p class='LPrioridad'>PRIORIDAD</p>");
					out.print("<select id='prioridad' class='SelPrioridad'><option value='Seleccione'>Seleccione</option>");
					rs4 = mp.ObtenerPrioridad();
					try{
						while(rs4.next()){
							out.print("<option value="+rs4.getString(1)+">"+rs4.getString(2)+"</option>");
						}
						rs4.getStatement().getConnection().close();
					}catch(SQLException e){
						e.printStackTrace();
		
				}
					
					
					out.print("</select>");
					
					out.print("<div id='Actu'><select multiple name='textarea' id='selmul'  style='padding-right: 100px' class='Mostrar'></select></div>");
					rs5 = mp.ObtenerEstado();
					try{
						while(rs5.next()){
							out.print("<br><br><br><input type='hidden' id='EstSoliG' size='10' value='"+rs5.getString(1)+"'>");
						}
						rs5.getStatement().getConnection().close();
					}catch(SQLException e){
						e.printStackTrace();
					}
					out.print("<br><br><br><input type='button' value='Enviar' id='BtnEnviar' onClick='Ingresarsolicitud()' class='EnviarSol'><input type='button' value='Modificar' id='BtnModSol' onClick='crear()' class='ModificarSol'></td></tr></table>");
					
    	}
    
    	//}
    // SE CREA EL SELECT DEL SUBAREA
    if(va.equals("1")){
    
    	out.print("<p class='LSubArea'><b>SUB AREA<b></p><p class='SubArea2'><select name='subarea' id='sbarea' ><option value='Seleccione'>Seleccione</option>");
    	
    	rs2 = mp.MostrarSubAreas(CodArea);
    	try{
			while(rs2.next()){
				out.print("<option value="+rs2.getString(1)+">"+rs2.getString(2)+"</option>");
			}
			rs2.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		out.print("</select></p>");
		
		
    }

    
    	// INGRESAMOS LOS DATOS EN LA BASE DE DATOS DE LA SOLICITUD, DEL DETALLE SOLICITUD Y PROBLEMA	
		if(va.equals("2")){
			int k;
			int contSo = 0;
			int contPro = 0;
			int codsol2=0;
			String ObserDetalle="";
			String codigoos = req.getParameter("contenido");
			
			

			codigoos = codigoos.substring(0,codigoos.length()-1);			
			String[] detcodigoos = codigoos.split(";"); 
			for(k=0;k<detcodigoos.length;k++){		
				String[] dtcod = detcodigoos[k].split("-");	
				rs99 = ms.ValidarSolicitud(Codigou, CodSubArea, dtcod[0]);
				try{
		        	while(rs99.next()){
		        		System.out.println("consulta: "+rs99.getString(1));
		        		if(dtcod[0].equals(rs99.getString(1))){
		        			
		        			contPro++;
		        		}
		        				        	
		        	}
		        	rs99.getStatement().getConnection().close();
		        }catch(SQLException e){
		        	e.printStackTrace();
		        }
				
			}	
			if(contPro==contSo){
			rs = mp.ObtenerCodigoSolicitud();
			try{
				while(rs.next()){
					 CodSolicitud = rs.getInt(1);
				}
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			CodSolicitud++;
			mp.IngresarSolicitud( CodSubArea, Codigou, horaactual, fechaactual);
			out.print("Solicitud enviada con exito.");
		    mp.IngresarDetalle( EstDet,ObserDetalle,CodSolicitud,codiprioridad,TipoSolicitud);
		   
		    rs1 = mp.ObtenerCodigoSolicitud();
		    try{
				while(rs1.next()){
					codsol2 = rs1.getInt(1);
				}
				rs1.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
			int t;
			for(t=0;t<detcodigoos.length;t++){
				
				String[] dtcod2 = detcodigoos[t].split("-");
				String codProblema = dtcod2[0];
				String obsProblema = "";
				if(!dtcod2[1].equals("|")){
					obsProblema = dtcod2[1];		
				}
				mp.IngresarDetalleProblema(codProblema,obsProblema ,EstDet ,codsol2);
					
			}	
			}else{
				out.print("Ya tiene una solicitud activa con el mismo problema");
			}
			
		}
    
	// SE CREA LA VISTA DE TUS SOLICITUDES QUE ES LA QUE VE EL DE MANTENIMIENTO	
		if(va.equals("4")){
			String fechahoy = req.getParameter("fechaactual");
			String estadoAT = "";
			out.print("<p class='esttussol'>Tu estado actual es: </p>");
			rs1 = ms.ObtenerEstadoAlex();
			try{
				while(rs1.next()){
							
						    estadoAT = rs1.getString(2);
						}
					
						rs1.getStatement().getConnection().close();
					}catch(SQLException e){
						e.printStackTrace();
						
					}
			out.print("<p class='textesttus'><input type='text' size='10' value='"+estadoAT+"' disabled></p> ");
	    	out.print("<div id='encSolicitudes'>");
	    	out.print("<p class='encusu'> Usuario</p>");
			out.print("<p class='encsubarea'>Sub Area</p>");
			out.print("<p class='encfchEnvio'>Fecha de Envio</p>");
			out.print("<p class='encHrsEnvio'>Hora de Envio</p>");
			out.print("<p class='encPrio'>Prioridad</p>");
			out.print("<p class='encestado'>Estado</p>");
			out.print("<p class='encver'></p></div>");
			out.print("<div id='listSolicitudes'>");
			rs = ms.ObtenerSolicitud(fechahoy);
			try{
				while(rs.next()){
					out.print("<div id='lineas'>");
			    	out.print("<p class='usu'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='subarea'>"+rs.getString(2)+"</p>");
			    	out.print("<p class='fchEnvio'>"+rs.getString(3)+"</p>");
			    	out.print("<p class='HrsEnvio'>"+rs.getString(4)+"</p>");
			    	out.print("<p class='Prio'>"+rs.getString(5)+"</p>");
			    	out.print("<p class='estado' >"+rs.getString(7)+"</p>");
			    	out.print("<p class='ver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitud("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
				}
				
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
		out.print("</div>");
		
		}
		//  SE CREA LA VENTANA DE "VER PROBLEMAS" EN TUS SOLICITUDES, MUESTRA LOS PROBLEMAS Y LAS OBSERVACIONES DEL USUARIO
if(va.equals("5")){
			
	        
			String codsol = req.getParameter("codsol");
			out.print("<form id='form3' name='form3'>");
			out.print("<div id='esteactu'>");
			out.print("<div id='estados'>");
	        out.print("<p class='observa'>Observaciones</p>");
			out.print("<p class='problemas'>Problemas</p>");
			out.print("<p class='estad'>Estado</p></div><br>");
			out.print("<div id='est'>");
			
			rs = ms.ObtenerProblemas(codsol);
			
			try{
				while(rs.next()){
				    
					out.print("<div id='line'>");
			    	out.print("<p class='obser'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='problema' value='"+rs.getString(3)+"' id='pro'>"+rs.getString(2)+"</p>");
			        rs1 = ms.TipoEstado();
			        out.print("<select id='estads' value='Enviado' class='estads'><option value='"+rs.getString(4)+"'>"+rs.getString(5)+"</option>");
			        try{
			        	while(rs1.next()){
			        		out.print("<option value='"+rs1.getString(1)+"'id='esta1'>"+rs1.getString(2)+"</option>");
			        		
			        	}
			        	rs1.getStatement().getConnection().close();
			        }catch(SQLException e){
			        	e.printStackTrace();
			        }
			        out.print("</select></div>");
				}
				
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
		
			
			out.print("</div>");
			out.print("<div id='alinear2'>");
			out.print("<br><p class='ObservacionAlexTus'><b>OBSERVACIONES REALIZADAS<b></p>");
			out.print("<textarea name='textareaAlex' id='observacionAlex' rows='5' cols='60' class='ObservaAlexA' readonly='readonly'>");
			
			rs3 = ms.ObtenerDatosObservacion(codsol);
			try{
				while(rs3.next()){
					if(!rs3.getString(2).equals("")){
					out.print(""+rs3.getString(1)+": "+rs3.getString(2)+"\n");
					}
				}
				rs3.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			out.print("</textarea></div>");
			
			
			out.print("<div id='alinear2'>");
            out.print("<br><p class='ObservacionAlex'><b>AGREGAR OBSERVACION<b></p>");
			out.print("<textarea name='textareaAlex' id='observacionesAlex' rows='5' cols='40' class='ObservaAlexTus'></textarea>");
			
			out.print("<br><br><input name='btnCancelar' type='button' id='btnCerrarProblemas' value='Cerrar' onclick='cerrar(0)' class='btnStyle btnCerrar2'/>");
			out.print("<input name='btnAceptar' type='button' id='btnGuradarEstProblemas'  value='Aceptar' onclick='Records("+codsol+")' class='btnStyle btnAceptar2'/>");
			out.print("</div></div>");
			out.print("</form>");
		}
		
		// GUARDAMOS LOS DATOS EN LA TABLA RECORDS, Y ACTUALIZAMOS EN DETALLE DE PROBLEMA Y DE SOLICUTD LOS ESTADOS
		if(va.equals("6")){
			
			String Observacion = req.getParameter("Observaciones");
			String codsol = req.getParameter("codsol");
			String Codigos = req.getParameter("codigos");
			String fechaactual2 = req.getParameter("fechaactual2");
			String horaactual2 = req.getParameter("horaactual2");
			
			 int i;
			    String[] detcodprob = Codigos.split(",");
			   
			    for(i=0;i<detcodprob.length;i++){
			 
			    	String[] detalles = detcodprob[i].split("_");
		
			    	ms.IngresarRecord(codsol, detalles[0], detalles[1], fechaactual2, horaactual2,Observaciones);
			    	ms.ActualizarEstado(detalles[1], codsol, detalles[0]);
			    	if(!Observacion.equals("")){
			    	ms.ActualizarDetalleObservacion(Observacion, codsol);
			    	}
			    }
			    out.print("");
			    int conest=0;
			    int conpro=0;
			    rs98 = ms.ObtenerEstadoProblemas(codsol);
			    try{
			    	while(rs98.next()){
			    		if(rs98.getString(1).equals("5")){
			    	        conest++;
			    		}else{
			    			if(rs98.getString(1).equals("6")){
			    				conest++;
			    			}
			    		}
			    		conpro++;
			    	}
			      rs98.getStatement().getConnection().close();
			    }catch(SQLException e){
			    	e.printStackTrace();
			    }
			    if(conest==conpro){
			    	rs1 = ms.TipoEstadoFinalizado();
			    	try{
			    		while(rs1.next()){
			    			ms.ActualizarDetalleSolicitud(rs1.getString(1),fechaactual2, horaactual2, codsol);
			    			
			    		}
			    		rs1.getStatement().getConnection().close();
			    	}catch(SQLException e){
			    		e.printStackTrace();
			    	}
					
			    }
			    	    	
			   
		}
		
		
		
		
		
    	// SE CREA LA LISTA DE SOLICITUDES DEL USUARIO PARA QUE PUEDA VER EN QUE ESTADO SE ENCUENTRA SU SOLICITUD
		if(va.equals("Estado")){
			String FechaHoy = req.getParameter("fechatoday");
			
			out.print("<table border  width='450' class='tablistsol'><tr><td>");
			out.print("<div id='esteactu12'>");
			out.print("<div id='estados12'>");
			out.print("<p class='enclista'>LISTA DE SOLICITUDES</p>");
			out.print("</div>");
			
			rs= ms.ObtenerCodSolicitudUsuarioMostrar(codigous,FechaHoy);
			try{
				while(rs.next()){
							out.print("<p class='solicitudno'>SOLICITUD No "+rs.getString(1)+"</p>");
							out.print("<p class='detsoll'><a onclick='ventCrearSolicitud2("+rs.getString(1)+")' href='#'> Ver Detalle</a></p> ");
							
							
							
						}
					
						rs.getStatement().getConnection().close();
					}catch(SQLException e){
						e.printStackTrace();
						
					}
					String estadoA = "";
					out.print("</div></td></tr></table>");
					out.print("<table class='tabestadoA' border='2'><tr><td>");
					out.print("<p class='estadoAl'><b>Alex se encuentra<b></p>");
					rs1 = ms.ObtenerEstadoAlex();
					try{
						while(rs1.next()){
									
								    estadoA = rs1.getString(2);
								}
							
								rs1.getStatement().getConnection().close();
							}catch(SQLException e){
								e.printStackTrace();
								
							}
					out.print("<input type='text' class='textestadoalex' size='10' value='"+estadoA+"' disabled>");
					out.print("</td></tr></table>");
				}
		// SE CREA LA VENTANA EN LA CUAL EL USUARIO MIRA EL ESTADO DE LA SOLICITUD Y LAS OBSERVACIONES QUE REALIZA EL TECNICO
		if(va.equals("8")){
			
			
		    String obserAle3="";
			String codsol3 = req.getParameter("codsol");
			out.print("<form id='form3' name='form3'>");
			out.print("<div id='esteactu'>");
			out.print("<div id='estados'>");
	        out.print("<p class='observa'>Observaciones</p>");
			out.print("<p class='problemas'>Problemas</p>");
			out.print("<p class='estad'>Estado</p></div><br>");
			out.print("<div id='est'>");
			rs = ms.ObtenerProblemas(codsol3);
			
			try{
				while(rs.next()){
				    
					out.print("<div id='line'>");
			    	out.print("<p class='obser'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='problema' value='"+rs.getString(3)+"' id='pro'>"+rs.getString(2)+"</p>");
			 
			        out.print("<input type='text' class='estads' size='11' disabled value='"+rs.getString(5)+"' id='"+rs.getString(4)+"'></div>");
			       
			       
				}
				 out.print("</div>");
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
			rs2 = ms.ObtenerObservacionDetalle(codsol3);
			try{
				while(rs2.next()){
					obserAle3 = rs2.getString(1);
					
				}
				
				rs2.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			out.print("</div>");
			if(!(obserAle3.equals(""))){
			out.print("<div id='alinear2'>");
			out.print("<br><p class='ObservacionAlexU'><b>Observacion<b></p>");
			out.print("<textarea name='textareaAlex' id='observacionesAlex' rows='5' cols='40' class='ObservaAlex' readonly='readonly'>");
			rs1 = ms.ObtenerDatosObservacion(codsol3);
			try{
				while(rs1.next()){
					if(!rs1.getString(2).equals("")){
					out.print(""+rs1.getString(1)+": "+rs1.getString(2)+"\n");
					}
				}
				rs1.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			out.print("</textarea>");
			}
			out.print("<br><br><input name='btnCancelar' type='button' id='btnEvoCoordenadas' value='Cerrar' onclick='cerrar(2)' class='btnStyle btnCerrarA2'/>");
			out.print("</div>");
		}
				
		// SE CREA LA VISTA DEL HISTORICO DE SOLICITUDES -------------------------//////////////////////--------------------*
    	if(va.equals("Todas")){
    		
	    	out.print("<table><tr><td><div id='HencSolicitudes'>");
	    	out.print("<p class='Husu'> Usuario</p>");
			out.print("<p class='Hsubarea'>Sub Area</p>");
			out.print("<p class='HfchEnvio'>Fecha de Envio</p>");
			out.print("<p class='HHrsEnvio'>Hora de Envio</p>");
			out.print("<p class='HPrio'>Prioridad</p>");
			out.print("<p class='Hestado'>Estado</p>");
			out.print("<p class='HfchFin'>Fecha Finalizado</p>");
			out.print("<p class='HHrsFin'>Hora Finalizado</p>");
			out.print("<p class='Hver'></p></div>");
			out.print("<div id='HlistSolicitudes'>");
			rs = ms.ObtenerSolicitudesFinalizadas();
			try{
				while(rs.next()){
					out.print("<div id='Henclineas'>");
			    	out.print("<p class='Hencusu'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='Hencsubarea'>"+rs.getString(2)+"</p>");
			    	out.print("<p class='HencfchEnvio'>"+rs.getString(3)+"</p>");
			    	out.print("<p class='HencHrsEnvio'>"+rs.getString(4)+"</p>");
			    	out.print("<p class='HencPrio'>"+rs.getString(5)+"</p>");
			    	out.print("<p class='Hencestado' >"+rs.getString(7)+"</p></div>");
			    	out.print("<p class='HencfchFin'>"+rs.getString(8)+"</p>");
					out.print("<p class='HencHrsFin'>"+rs.getString(9)+"</p>");
			    	out.print("<p class='Hencver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitud3("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
				}
				
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
		out.print("</div></td></tr></table>");
		
		}
    	// SE CREA LA VENTANA DE HISTORICO DE SOLICITUDES QUE MUESTA LAS OBSERVACIONES Y LOS ESTADOS DE CADA PROBLEMA DE LAS SOLICITUDES
    	if(va.equals("11")){
		    String obserAle3="";
			String codsol3 = req.getParameter("codsol");
			out.print("<form id='form3' name='form3'>");
			out.print("<div id='esteactu'>");
			out.print("<div id='estados'>");
	        out.print("<p class='observa'>Observaciones</p>");
			out.print("<p class='problemas'>Problemas</p>");
			out.print("<p class='estad'>Estado</p></div><br>");
			out.print("<div id='est'>");
			rs = ms.ObtenerProblemas(codsol3);
			
			try{
				while(rs.next()){
				    
					out.print("<div id='line'>");
			    	out.print("<p class='obser'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='problema' value='"+rs.getString(3)+"' id='pro'>"+rs.getString(2)+"</p>");
			        
			        out.print("<input type='text' class='estads' size='11' disabled value='"+rs.getString(5)+"' id='"+rs.getString(4)+"'></div>");
			  
			       
				}
				 out.print("</div>");
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
			rs2 = ms.ObtenerObservacionDetalle(codsol3);
			try{
				while(rs2.next()){
					obserAle3 = rs2.getString(1);
					
				}
				
				rs2.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			out.print("</div>");
			if(!(obserAle3.equals(""))){
			out.print("<div id='alinear2'>");
			out.print("<br><p class='ObservacionAlexH'><b>Observacion<b></p>");
			out.print("<textarea name='textareaAlex' id='observacionesAlex' rows='5' cols='60' class='ObservaAlexH' readonly='readonly'>");
			
			rs1 = ms.ObtenerDatosObservacion(codsol3);
			try{
				while(rs1.next()){
					if(!rs1.getString(2).equals("")){
						out.print(""+rs1.getString(1)+": "+rs1.getString(2)+"\n");
						}
					
				}
				rs1.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			out.print("</textarea>");
			
			}
			out.print("<br><input name='btnCancelar' type='button' id='btnEvoCoordenadas' value='Cerrar' onclick='cerrar(4)' class='btnStyle btnCerrarA2'/>");
			out.print("</div>");
		}
    		
    	
    	
    	
    	if(va.equals("Reporte")){
    		out.print(" " +
    				"<p class='titulo'>Problema que más se presenta</p> " +
    				"<p class='enunciado'>Seleccione el intervalo de fechas en la cual va a realizar la consulta.</p> " +
    				"<p class='fechainicial'>Fecha Inicial</p><input type='text' onKeyup='masca(this,patron,true,0,0,0)' size='10' id='fchini' class='textfechaf'> " +
    				"<p class='ejemplo'>(dia-mes-año)</p> " +
    				"<p class='fechafinal'>Fecha Final</p><input type='text' size='10' onKeyup='masca(this,patron,true,0,0,0)' id='fchfin'class='textfechaf'>(dia-mes-año) " +
    				"<br><br><br><input type='button' id='btnaceptarR' onclick='ventCrearSolicitud8()' value='Aceptar'class='btnproblema'>");
    		
    	}
    	
    	if(va.equals("12")){
    		String codestatec = req.getParameter("codesttec");
    		String fchestado = req.getParameter("fechaactualEst");
    		String hraestado = req.getParameter("horaactualEst");
    		ms.IngresarEstadistica(codestatec, fchestado, hraestado);
    		
    	}
    	
    	if(va.equals("13")){
    		
    		String FechaActualRep = req.getParameter("fechatodayRep");
    		String fecharepini = req.getParameter("fechaini");
    		String fecharepfin = req.getParameter("fechafin");
    		
    		String anio = fecharepini.substring(6,10);   		
    		String dia = fecharepini.substring(0,2);	
    		String mes = fecharepini.substring(3,5);
    		
    		
    		String aniofin = fecharepfin.substring(6,10);
    		String diafin = fecharepfin.substring(0,2);
    		String mesfin = fecharepfin.substring(3,5);
    		
    		String fchestadosub = anio+"-"+mes+"-"+dia;
    		String hraestadosub = aniofin+"-"+mesfin+"-"+diafin;
    		
    		
    		
    		out.print("<div id='reportepro'>");
    		out.print("<div id='encreptit'>Reporte de Problemas</div>");
    		out.print("<div id='titrep'>");
    		out.print("<p class='totprorep'><b>Total Problemas<b></p>");
    		out.print("<p class='probcomun'><b>Problema mas Solicitado<b></p>");
    		out.print("<p class='reportesol'><b>No veces Solicitado<b></p>");
    		out.print("<p class='reportesubarea'><b>Sub Area que mas lo Solicita<b></p>");
    		out.print("<p class='reportcantsol'><b>Cantidad de Solicitdes<b></p></div>");
    		out.print("</div>");
    		out.print("<div id='repest'>");
    		rs8 = ms.TotalProblemas(fchestadosub, hraestadosub);
			try{
				while(rs8.next()){ 
					out.print("<div id='repline'>");
			    	out.print("<p class='reptotpro'>"+rs8.getString(1)+"</p>");
			    	  
				}
				 
				rs8.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
    		
			rs = ms.ReporteProblema(fchestadosub, hraestadosub);
			
			try{
				while(rs.next()){
				    
					
			    	out.print("<p class='repproblema'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='repsol' value='' id='pro'>"+rs.getString(2)+"</p>");
			  
			       
				}
				 
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
			rs1 = ms.CodProblemaComun(fchestadosub, hraestadosub);
			String CodProCom = "";
			try{
				while(rs1.next()){
					CodProCom = rs1.getString(1);
					
				}
				
				rs1.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
			rs2 = ms.ObtenerSubAreaComun(CodProCom,fchestadosub, hraestadosub);
			try{
				while(rs2.next()){
					out.print("<input type='hidden' id='FechaActualRep' value='"+FechaActualRep+"'>");
					out.print("<input type='hidden' id='fchestadosub' value='"+fchestadosub+"'>");
					out.print("<input type='hidden' id='hraestadosub' value='"+hraestadosub+"'>");
					out.print("<input type='hidden' id='codprobcomunrep' value='"+CodProCom+"'>");
					out.print("<p class='repsubacon'>"+rs2.getString(1)+"</p>");
					out.print("<p class='repsubacant'>"+rs2.getString(2)+"</p>");
					
				}
				out.print("</div>");
				rs2.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			ms.IngresarReporte(FechaActualRep, fchestadosub, hraestadosub);
			out.print("</div><br><input type='button' onclick='cerrar(8)' value='Cerrar' class='repbtncerrar'><input type='button' onclick='ImprimirReporte()' value='Imprimir' class='repbtnimprimir'>");
			
    	}
    	if(va.equals("ReporteS")){
    		out.print(" " +
    				"<p class='titulo'>Sub Area que más Solicitudes Realiza</p> " +
    				"<p class='enunciado'>Seleccione el intervalo de fechas en la cual va a realizar la consulta.</p> " +
    				"<p class='fechainicial'>Fecha Inicial</p><input type='text' onKeyup='masca(this,patron,true,0,0,0)' size='10' id='fchinisub' class='textfechaf'> " +
    				"<p class='ejemplo'>(dia-mes-año)</p> " +
    				"<p class='fechafinal'>Fecha Final</p><input type='text' size='10' onKeyup='masca(this,patron,true,0,0,0)' id='fchfinsub' class='textfechaf'>(dia-mes-año) " +
    				"<br><br><br><input type='button' id='btnaceptarR' onclick='validarfechareporte2()' value='Aceptar'class='btnproblema'>");
    		
    	}
    	if(va.equals("14")){
    		
    		String fechatodayRep2 = req.getParameter("fechatodayRep2");
    		String fecharepinisub = req.getParameter("fechainisub");
    		String fecharepfinsub = req.getParameter("fechafinsub");
    		
    		String anio = fecharepinisub.substring(6,10);   		
    		String dia = fecharepinisub.substring(0,2);	
    		String mes = fecharepinisub.substring(3,5);
    		
    		
    		String aniofin = fecharepfinsub.substring(6,10);
    		String diafin = fecharepfinsub.substring(0,2);
    		String mesfin = fecharepfinsub.substring(3,5);
    		
    		String fchestadosub2 = anio+"-"+mes+"-"+dia;
    		String hraestadosub2 = aniofin+"-"+mesfin+"-"+diafin;
    		
    		
    		out.print("<div id='reporteprosub'>");
    		out.print("<div id='encreptitsub'>Sub Area que realiza más Solicitudes</div>");
    		out.print("<div id='titrepsub'>");
    		out.print("<p class='repnosol'><b>No Solicitudes<b></p>");
    		out.print("<p class='repsubarea'><b>Sub Area<b></p>");
    		out.print("<p class='repsubsol'><b>Solicitudes Realizadas<b></p></div>");
    		out.print("</div>");
    		out.print("<div id='repest'>");
    		
    		rs8 = ms.TotalSolicitudes(fchestadosub2, hraestadosub2);
			try{
				while(rs8.next()){ 
					out.print("<div id='repline'>");
			    	out.print("<p class='repnosolsub'>"+rs8.getString(1)+"</p>");
			    	  
				}
				 
				rs8.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
    		
    		
			rs = ms.ObtenerSubAreaRep(fchestadosub2, hraestadosub2);
			
			try{
				while(rs.next()){
					out.print("<input type='hidden' id='FechaActualRep2' value='"+fechatodayRep2+"'>");
					out.print("<input type='hidden' id='fchestadosub2' value='"+fchestadosub2+"'>");
					out.print("<input type='hidden' id='hraestadosub2' value='"+hraestadosub2+"'>");
			    	out.print("<p class='repproblemasub'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='repsolsub' value='' id='pro'>"+rs.getString(2)+"</p>");
			  
			       
				}
				 out.print("</div>");
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
			out.print("</div><br><br><input type='button' onclick='cerrar(9)' value='Cerrar' class='repbtncerrarsub'><input type='button' onclick='ImprimirReporte2()' value='Imprimir' class='repbtnimprimir2'>");
    		
    	}
    	
    	if(va.equals("BuscarF")){
    		
    		out.print(" " +
    				"<p class='enunciado'>Seleccione el intervalo de fechas en la cual va a realizar la consulta.</p> " +
    				"<p class='fechainicial'>Fecha Inicial</p><input type='text' onKeyup='masca(this,patron,true,0,0,0)' size='10' id='fchinibus' class='textfechaf'> " +
    				"<p class='ejemplo'>(dia-mes-año)</p> " +
    				"<p class='fechafinal'>Fecha Final</p><input type='text' size='10' onKeyup='masca(this,patron,true,0,0,0)' id='fchfinbus'class='textfechaf'>(dia-mes-año) " +
    				"<br><br><br><input type='button' id='btnaceptarR' onclick='mostrarSol()' value='Aceptar'class='btnproblema'>");
    		
    	}
    	
    	if(va.equals("16")){
    		
    		String FechaIniBuscar = req.getParameter("fechaini");
    		String FechaFinBuscar = req.getParameter("fechafin");
    		
    		String anio = FechaIniBuscar.substring(6,10);   		
    		String dia = FechaIniBuscar.substring(0,2);	
    		String mes = FechaIniBuscar.substring(3,5);
    		
    		
    		String aniofin = FechaFinBuscar.substring(6,10);
    		String diafin = FechaFinBuscar.substring(0,2);
    		String mesfin = FechaFinBuscar.substring(3,5);
    		
    		String FechaIniBus = anio+"-"+mes+"-"+dia;
    		String FechaFinBus = aniofin+"-"+mesfin+"-"+diafin;
    		
    		
    		
    		out.print("<table><tr><td><div id='HencSolicitudes'>");
	    	out.print("<p class='Husu'> Usuario</p>");
			out.print("<p class='Hsubarea'>Sub Area</p>");
			out.print("<p class='HfchEnvio'>Fecha de Envio</p>");
			out.print("<p class='HHrsEnvio'>Hora de Envio</p>");
			out.print("<p class='HPrio'>Prioridad</p>");
			out.print("<p class='Hestado'>Estado</p>");
			out.print("<p class='HfchFin'>Fecha Finalizado</p>");
			out.print("<p class='HHrsFin'>Hora Finalizado</p>");
			out.print("<p class='Hver'></p></div>");
			out.print("<div id='HlistSolicitudes'>");
	        rs = ms.ObtenerSolicitudesPorFechas(FechaIniBus, FechaFinBus);	
			try{
				while(rs.next()){
					out.print("<div id='Henclineas'>");
			    	out.print("<p class='Hencusu'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='Hencsubarea'>"+rs.getString(2)+"</p>");
			    	out.print("<p class='HencfchEnvio'>"+rs.getString(3)+"</p>");
			    	out.print("<p class='HencHrsEnvio'>"+rs.getString(4)+"</p>");
			    	out.print("<p class='HencPrio'>"+rs.getString(5)+"</p>");
			    	out.print("<p class='Hencestado' >"+rs.getString(7)+"</p></div>");
			    	out.print("<p class='HencfchFin'>"+rs.getString(8)+"</p>");
					out.print("<p class='HencHrsFin'>"+rs.getString(9)+"</p>");
			    	out.print("<p class='Hencver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitud3("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
				}
				
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
		out.print("</div></td></tr></table>");
    	}
    	
    	if(va.equals("BuscarA")){
    		out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2' >Reporte Solicitudes por Area </div></td></tr>");
			out.print("<tr><td width='15%'>Seleccione el  Area </td><td width='21%'><select name='cmbMenu' id='cmbMenusop' onChange='CambioOpcionesMenusop()'><option value='Seleccione' selected='selected'>Seleccione</option>");
			rs=mp.MostrarAreas();
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></td><td width='17%'>Seleccione La Sub Area </td><td><div id='OpcionesMenusop'><select name='cmbOpMenu' id='cmbOpMenu'><option value='Seleccione'>Seleccione</option></select></div></td></tr>");
				out.print("<tr><td colspan='4'><div id='OpDisponibles'></div></td></tr></table>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		
    	}
    	
    	if(va.equals("3.1")){
    		String CodigoAreaSop=req.getParameter("CodigoAreaSop");
			rs=mp.MostrarSubAreas(CodigoAreaSop);
			out.print("<div id='OpcionesMenusop'><select name='cmbOpMenu' id='cmbOpMenu' onChange='mostrarSolA()'><option value='Seleccione'>Seleccione</option>");
			try {
				while(rs.next()){
					out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
				}
				out.print("</select></div>");
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error: "+e);
				e.printStackTrace();
			}
		}
    	
    	if(va.equals("18")){
    		String codareabus = req.getParameter("codareabus");
    		
    		out.print("<table><tr><td><div id='HencSolicitudes'>");
	    	out.print("<p class='Husu'> Usuario</p>");
			out.print("<p class='Hsubarea'>Sub Area</p>");
			out.print("<p class='HfchEnvio'>Fecha de Envio</p>");
			out.print("<p class='HHrsEnvio'>Hora de Envio</p>");
			out.print("<p class='HPrio'>Prioridad</p>");
			out.print("<p class='Hestado'>Estado</p>");
			out.print("<p class='HfchFin'>Fecha Finalizado</p>");
			out.print("<p class='HHrsFin'>Hora Finalizado</p>");
			out.print("<p class='Hver'></p></div>");
			out.print("<div id='HlistSolicitudes'>");
	        rs = ms.ObtenerSolicitudesPorArea(codareabus);
			try{
				while(rs.next()){
					out.print("<div id='Henclineas'>");
			    	out.print("<p class='Hencusu'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='Hencsubarea'>"+rs.getString(2)+"</p>");
			    	out.print("<p class='HencfchEnvio'>"+rs.getString(3)+"</p>");
			    	out.print("<p class='HencHrsEnvio'>"+rs.getString(4)+"</p>");
			    	out.print("<p class='HencPrio'>"+rs.getString(5)+"</p>");
			    	out.print("<p class='Hencestado' >"+rs.getString(7)+"</p></div>");
			    	out.print("<p class='HencfchFin'>"+rs.getString(8)+"</p>");
					out.print("<p class='HencHrsFin'>"+rs.getString(9)+"</p>");
			    	out.print("<p class='Hencver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitud3("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
				}
				
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
		out.print("</div></td></tr></table>");
    	}
    	
    	if(va.equals("Requerimiento")){
    		

    		out.print("<table  class='SolicitudInicial' border='1'>");
    				out.print("<tr><td>");
    						out.print("<p class='LArea'><b>AREA<b> </p><p class='SelArea'><select  id='area' onchange='MostrarSubArea2()' ><option value='Seleccione'>Seleccione</option> ");
    			
    				
    				rs1 = mp.MostrarAreas();
    				try{
    					while(rs1.next()){
    						out.print("<option value="+rs1.getString(1)+">"+rs1.getString(2)+"</option>");
    					}
    					rs1.getStatement().getConnection().close();
    				}catch(SQLException e){
    					e.printStackTrace();
    	
    			}
    				   out.print("</select></p><div id='ActuMedi'></div>");
    				   	   
    				   out.print("<br><br><br><b><p class='LProblema' align='center'><div id='encselpro'>SELECCIONE EL PROBLEMA QUE PRESENTA</div></p><b><br><br>");
    				   out.print("<p class='SelProblemas'><select name='probelmas' id='P1' class='ProbSolicitud'><option value='Seleccione'>Seleccione</option>");
    					rs=mp.BuscarProblemasReq();
    					try {
    						while(rs.next()){
    							out.print("<option value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
    					
    						}
    						rs.getStatement().getConnection().close();
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					out.print("</select></p><input type='button' value='Asignar' id='asignar' class='IAsignar' name='btnasignar' onclick='MostrarProblemas()'>");
    					out.print("<p class='LPrioridad'>PRIORIDAD</p>");
    					out.print("<select id='prioridad' class='SelPrioridad'><option value='Seleccione'>Seleccione</option>");
    					rs4 = mp.ObtenerPrioridad();
    					try{
    						while(rs4.next()){
    							out.print("<option value="+rs4.getString(1)+">"+rs4.getString(2)+"</option>");
    						}
    						rs4.getStatement().getConnection().close();
    					}catch(SQLException e){
    						e.printStackTrace();
    		
    				}
    					
    					
    					out.print("</select>");
    					
    					out.print("<div id='Actu'><select multiple name='textarea' id='selmul'  style='padding-right: 100px' class='Mostrar'></select></div>");
    					rs5 = mp.ObtenerEstado();
    					try{
    						while(rs5.next()){
    							out.print("<br><br><br><input type='hidden' id='EstSoliG' size='10' value='"+rs5.getString(1)+"'>");
    						}
    						rs5.getStatement().getConnection().close();
    					}catch(SQLException e){
    						e.printStackTrace();
    					}
    					out.print("<br><br><br><input type='button' value='Enviar' id='BtnEnviar' onClick='IngresarsolicitudReq()' class='EnviarSol'><input type='button' value='Modificar' id='BtnModSol' onClick='crear()' class='ModificarSol'></td></tr></table>");
    	}
    	
    	if(va.equals("22")){
			int k;
			int contSo = 0;
			int contPro = 0;
			int codsol2=0;
			String ObserDetalle="";
			String codigoos = req.getParameter("contenido");
			

			codigoos = codigoos.substring(0,codigoos.length()-1);			
			String[] detcodigoos = codigoos.split(";"); 
			for(k=0;k<detcodigoos.length;k++){		
				String[] dtcod = detcodigoos[k].split("-");	
				rs99 = ms.ValidarSolicitud(Codigou, CodSubArea, dtcod[0]);
				try{
		        	while(rs99.next()){
		        		System.out.println("consulta: "+rs99.getString(1));
		        		if(dtcod[0].equals(rs99.getString(1))){
		        			
		        			contPro++;
		        		}
		        				        	
		        	}
		        	rs99.getStatement().getConnection().close();
		        }catch(SQLException e){
		        	e.printStackTrace();
		        }
				
			}	
			if(contPro==contSo){
			rs = mp.ObtenerCodigoSolicitud();
			try{
				while(rs.next()){
					 CodSolicitud = rs.getInt(1);
				}
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			CodSolicitud++;
			mp.IngresarSolicitud( CodSubArea, Codigou, horaactual, fechaactual);
			out.print("Solicitud enviada con exito.");
		    mp.IngresarDetalle( EstDet,ObserDetalle,CodSolicitud,codiprioridad,TipoSolicitud);
		   
		    rs1 = mp.ObtenerCodigoSolicitud();
		    try{
				while(rs1.next()){
					codsol2 = rs1.getInt(1);
				}
				rs1.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
			int t;
			for(t=0;t<detcodigoos.length;t++){
				
				String[] dtcod2 = detcodigoos[t].split("-");
				String codProblema = dtcod2[0];
				String obsProblema = "";
				if(!dtcod2[1].equals("|")){
					obsProblema = dtcod2[1];		
				}
				mp.IngresarDetalleProblemaReq(codProblema,obsProblema ,EstDet ,codsol2);
					
			}	
			}else{
				out.print("Ya tiene una solicitud activa con el mismo problema");
			}
			
		}
    	if(va.equals("41")){
			String fechahoy = req.getParameter("fechaactual");
			
	    	out.print("<div id='encSolicitudes'>");
	    	out.print("<p class='encusu'> Usuario</p>");
			out.print("<p class='encsubarea'>Sub Area</p>");
			out.print("<p class='encfchEnvio'>Fecha de Envio</p>");
			out.print("<p class='encHrsEnvio'>Hora de Envio</p>");
			out.print("<p class='encPrio'>Prioridad</p>");
			out.print("<p class='encestado'>Estado</p>");
			out.print("<p class='encver'></p></div>");
			out.print("<div id='listSolicitudes'>");
			rs = ms.ObtenerSolicitudReq(fechahoy);
			try{
				while(rs.next()){
					out.print("<div id='lineas'>");
			    	out.print("<p class='usu'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='subarea'>"+rs.getString(2)+"</p>");
			    	out.print("<p class='fchEnvio'>"+rs.getString(3)+"</p>");
			    	out.print("<p class='HrsEnvio'>"+rs.getString(4)+"</p>");
			    	out.print("<p class='Prio'>"+rs.getString(5)+"</p>");
			    	out.print("<p class='estado' >"+rs.getString(7)+"</p>");
			    	out.print("<p class='ver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitudReq("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
				}
				
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
		out.print("</div>");
		
		}
    	
if(va.equals("51")){
			
	        
			String codsol = req.getParameter("codsol");
			out.print("<form id='form3' name='form3'>");
			out.print("<div id='esteactu'>");
			out.print("<div id='estados'>");
	        out.print("<p class='observa'>Observaciones</p>");
			out.print("<p class='problemas'>Problemas</p>");
			out.print("<p class='estad'>Estado</p></div><br>");
			out.print("<div id='est'>");
			
			rs = ms.ObtenerProblemasReq(codsol);
			
			try{
				while(rs.next()){
				    
					out.print("<div id='line'>");
			    	out.print("<p class='obser'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='problema' value='"+rs.getString(3)+"' id='pro'>"+rs.getString(2)+"</p>");
			        rs1 = ms.TipoEstado();
			        out.print("<select id='estads' value='Enviado' class='estads'><option value='"+rs.getString(4)+"'>"+rs.getString(5)+"</option>");
			        try{
			        	while(rs1.next()){
			        		out.print("<option value='"+rs1.getString(1)+"'id='esta1'>"+rs1.getString(2)+"</option>");
			        		
			        	}
			        	rs1.getStatement().getConnection().close();
			        }catch(SQLException e){
			        	e.printStackTrace();
			        }
			        out.print("</select></div>");
				}
				
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
		
			
			out.print("</div>");
			out.print("<div id='alinear2'>");
			out.print("<br><p class='ObservacionAlexTus'><b>OBSERVACIONES REALIZADAS<b></p>");
			out.print("<textarea name='textareaAlex' id='observacionAlex' rows='5' cols='60' class='ObservaAlexA' readonly='readonly'>");
			
			rs3 = ms.ObtenerDatosObservacion(codsol);
			try{
				while(rs3.next()){
					if(!rs3.getString(2).equals("")){
					out.print(""+rs3.getString(1)+": "+rs3.getString(2)+"\n");
					}
				}
				rs3.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			out.print("</textarea></div>");
			
			
			out.print("<div id='alinear2'>");
            out.print("<br><p class='ObservacionAlex'><b>AGREGAR OBSERVACION<b></p>");
			out.print("<textarea name='textareaAlex' id='observacionesAlex' rows='5' cols='40' class='ObservaAlexTus'></textarea>");
			
			out.print("<br><br><input name='btnCancelar' type='button' id='btnCerrarProblemas' value='Cerrar' onclick='cerrar(10)' class='btnStyle btnCerrar2'/>");
			out.print("<input name='btnAceptar' type='button' id='btnGuradarEstProblemas'  value='Aceptar' onclick='RecordsReq("+codsol+")' class='btnStyle btnAceptar2'/>");
			out.print("</div></div>");
			out.print("</form>");
		}

if(va.equals("61")){
	
	String Observacion = req.getParameter("Observaciones");
	String codsol = req.getParameter("codsol");
	String Codigos = req.getParameter("codigos");
	String fechaactual2 = req.getParameter("fechaactual2");
	String horaactual2 = req.getParameter("horaactual2");
	
	 int i;
	    String[] detcodprob = Codigos.split(",");
	   
	    for(i=0;i<detcodprob.length;i++){
	 
	    	String[] detalles = detcodprob[i].split("_");

	    	ms.IngresarRecordReq(codsol, detalles[0], detalles[1], fechaactual2, horaactual2,Observaciones);
	    	ms.ActualizarEstadoReq(detalles[1], codsol, detalles[0]);
	    	if(!Observacion.equals("")){
	    	ms.ActualizarDetalleObservacion(Observacion, codsol);
	    	}
	    }
	    out.print("");
	    int conest=0;
	    int conpro=0;
	    rs98 = ms.ObtenerEstadoProblemas(codsol);
	    try{
	    	while(rs98.next()){
	    		if(rs98.getString(1).equals("5")){
	    	        conest++;
	    		}else{
	    			if(rs98.getString(1).equals("6")){
	    				conest++;
	    			}
	    		}
	    		conpro++;
	    	}
	      rs98.getStatement().getConnection().close();
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	    if(conest==conpro){
	    	rs1 = ms.TipoEstadoFinalizado();
	    	try{
	    		while(rs1.next()){
	    			ms.ActualizarDetalleSolicitud(rs1.getString(1),fechaactual2, horaactual2, codsol);
	    			
	    		}
	    		rs1.getStatement().getConnection().close();
	    	}catch(SQLException e){
	    		e.printStackTrace();
	    	}
			
	    }
	    	    	   
}

		if(va.equals("SolReq")){
			String FechaHoy = req.getParameter("fechatoday");
			
			out.print("<table border  width='450' class='tablistsol'><tr><td>");
			out.print("<div id='esteactu12'>");
			out.print("<div id='estados12'>");
			out.print("<p class='enclista'>LISTA DE SOLICITUDES</p>");
			out.print("</div>");
			
			rs= ms.ObtenerCodSolicitudUsuarioMostrarReq(codigous,FechaHoy);
			try{
				while(rs.next()){
							out.print("<p class='solicitudno'>SOLICITUD No "+rs.getString(1)+"</p>");
							out.print("<p class='detsoll'><a onclick='ventCrearSolicitud2Req("+rs.getString(1)+")' href='#'> Ver Detalle</a></p> ");
							
							
							
						}
					
						rs.getStatement().getConnection().close();
					}catch(SQLException e){
						e.printStackTrace();
						
					}
					/*String estadoA = "";
					out.print("</div></td></tr></table>");
					out.print("<table class='tabestadoA' border='2'><tr><td>");
					out.print("<p class='estadoAl'><b>Alex se encuentra<b></p>");
					rs1 = ms.ObtenerEstadoAlex();
					try{
						while(rs1.next()){
									
								    estadoA = rs1.getString(2);
								}
							
								rs1.getStatement().getConnection().close();
							}catch(SQLException e){
								e.printStackTrace();
								
							}
					out.print("<input type='text' class='textestadoalex' size='10' value='"+estadoA+"' disabled>");*/
					out.print("</div></td></tr></table>");
				}
		
	if(va.equals("82")){
			
			
		    String obserAle3="";
			String codsol3 = req.getParameter("codsol");
			out.print("<form id='form3' name='form3'>");
			out.print("<div id='esteactu'>");
			out.print("<div id='estados'>");
	        out.print("<p class='observa'>Observaciones</p>");
			out.print("<p class='problemas'>Problemas</p>");
			out.print("<p class='estad'>Estado</p></div><br>");
			out.print("<div id='est'>");
			rs = ms.ObtenerProblemasReq(codsol3);
			
			try{
				while(rs.next()){
				    
					out.print("<div id='line'>");
			    	out.print("<p class='obser'>"+rs.getString(1)+"</p>");
			    	out.print("<p class='problema' value='"+rs.getString(3)+"' id='pro'>"+rs.getString(2)+"</p>");
			 
			        out.print("<input type='text' class='estads' size='11' disabled value='"+rs.getString(5)+"' id='"+rs.getString(4)+"'></div>");
			       
			       
				}
				 out.print("</div>");
				rs.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();

		}
			rs2 = ms.ObtenerObservacionDetalle(codsol3);
			try{
				while(rs2.next()){
					obserAle3 = rs2.getString(1);
					
				}
				
				rs2.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			out.print("</div>");
			if(!(obserAle3.equals(""))){
			out.print("<div id='alinear2'>");
			out.print("<br><p class='ObservacionAlexU'><b>Observacion<b></p>");
			out.print("<textarea name='textareaAlex' id='observacionesAlex' rows='5' cols='40' class='ObservaAlex' readonly='readonly'>");
			rs1 = ms.ObtenerDatosObservacion(codsol3);
			try{
				while(rs1.next()){
					if(!rs1.getString(2).equals("")){
					out.print(""+rs1.getString(1)+": "+rs1.getString(2)+"\n");
					}
				}
				rs1.getStatement().getConnection().close();
			}catch(SQLException e){
				e.printStackTrace();
				
			}
			out.print("</textarea>");
			}
			out.print("<br><br><input name='btnCancelar' type='button' id='btnEvoCoordenadas' value='Cerrar' onclick='cerrar(11)' class='btnStyle btnCerrarA2'/>");
			out.print("</div>");
		}
	
	if(va.equals("TodasReq")){
		
    	out.print("<table><tr><td><div id='HencSolicitudes'>");
    	out.print("<p class='Husu'> Usuario</p>");
		out.print("<p class='Hsubarea'>Sub Area</p>");
		out.print("<p class='HfchEnvio'>Fecha de Envio</p>");
		out.print("<p class='HHrsEnvio'>Hora de Envio</p>");
		out.print("<p class='HPrio'>Prioridad</p>");
		out.print("<p class='Hestado'>Estado</p>");
		out.print("<p class='HfchFin'>Fecha Finalizado</p>");
		out.print("<p class='HHrsFin'>Hora Finalizado</p>");
		out.print("<p class='Hver'></p></div>");
		out.print("<div id='HlistSolicitudes'>");
		rs = ms.ObtenerSolicitudesFinalizadasReq();
		try{
			while(rs.next()){
				out.print("<div id='Henclineas'>");
		    	out.print("<p class='Hencusu'>"+rs.getString(1)+"</p>");
		    	out.print("<p class='Hencsubarea'>"+rs.getString(2)+"</p>");
		    	out.print("<p class='HencfchEnvio'>"+rs.getString(3)+"</p>");
		    	out.print("<p class='HencHrsEnvio'>"+rs.getString(4)+"</p>");
		    	out.print("<p class='HencPrio'>"+rs.getString(5)+"</p>");
		    	out.print("<p class='Hencestado' >"+rs.getString(7)+"</p></div>");
		    	out.print("<p class='HencfchFin'>"+rs.getString(8)+"</p>");
				out.print("<p class='HencHrsFin'>"+rs.getString(9)+"</p>");
		    	out.print("<p class='Hencver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitud3Req("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
			}
			
			rs.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
	out.print("</div></td></tr></table>");
	
	}
	
	if(va.equals("111")){
	    String obserAle3="";
		String codsol3 = req.getParameter("codsol");
		out.print("<div id='esteactu'>");
		out.print("<div id='estados'>");
        out.print("<p class='observa'>Observaciones</p>");
		out.print("<p class='problemas'>Problemas</p>");
		out.print("<p class='estad'>Estado</p></div><br>");
		out.print("<div id='est'>");
		rs = ms.ObtenerProblemasReq(codsol3);
		
		try{
			while(rs.next()){
			    
				out.print("<div id='line'>");
		    	out.print("<p class='obser'>"+rs.getString(1)+"</p>");
		    	out.print("<p class='problema' value='"+rs.getString(3)+"' id='pro'>"+rs.getString(2)+"</p>");
		        
		        out.print("<input type='text' class='estads' size='11' disabled value='"+rs.getString(5)+"' id='"+rs.getString(4)+"'></div>");
		  
		       
			}
			 out.print("</div>");
			rs.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
		rs2 = ms.ObtenerObservacionDetalle(codsol3);
		try{
			while(rs2.next()){
				obserAle3 = rs2.getString(1);
				
			}
			
			rs2.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		out.print("</div>");
		if(!(obserAle3.equals(""))){
		out.print("<div id='alinear2'>");
		out.print("<br><p class='ObservacionAlexH'><b>Observacion<b></p>");
		out.print("<textarea name='textareaAlex' id='observacionesAlex' rows='5' cols='60' class='ObservaAlexH' readonly='readonly'>");
		
		rs1 = ms.ObtenerDatosObservacion(codsol3);
		try{
			while(rs1.next()){
				if(!rs1.getString(2).equals("")){
					out.print(""+rs1.getString(1)+": "+rs1.getString(2)+"\n");
					}
				
			}
			rs1.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		out.print("</textarea>");
		
		}
		out.print("<br><input name='btnCancelar' type='button' id='btnEvoCoordenadas' value='Cerrar' onclick='cerrar(12)' class='btnStyle btnCerrarA2'/>");
		out.print("</div>");
	}
	
	if(va.equals("BuscarFReq")){
		out.print(" " +
				"<p class='enunciado'>Seleccione el intervalo de fechas en la cual va a realizar la consulta.</p> " +
				"<p class='fechainicial'>Fecha Inicial</p><input type='text' onKeyup='masca(this,patron,true,0,0,0)' size='10' id='fchinibus' class='textfechaf'> " +
				"<p class='ejemplo'>(dia-mes-año)</p> " +
				"<p class='fechafinal'>Fecha Final</p><input type='text' size='10' onKeyup='masca(this,patron,true,0,0,0)' id='fchfinbus'class='textfechaf'>(dia-mes-año) " +
				"<br><br><br><input type='button' id='btnaceptarR' onclick='mostrarSolReq()' value='Aceptar'class='btnproblema'>");
		
		
	}
	
	if(va.equals("161")){
		
		String FechaIniBuscar = req.getParameter("fechaini");
		String FechaFinBuscar = req.getParameter("fechafin");
		
		String anio = FechaIniBuscar.substring(6,10);   		
		String dia = FechaIniBuscar.substring(0,2);	
		String mes = FechaIniBuscar.substring(3,5);
		
		
		String aniofin = FechaFinBuscar.substring(6,10);
		String diafin = FechaFinBuscar.substring(0,2);
		String mesfin = FechaFinBuscar.substring(3,5);
		
		String FechaIniBus = anio+"-"+mes+"-"+dia;
		String FechaFinBus = aniofin+"-"+mesfin+"-"+diafin;
		
		
		
		out.print("<table><tr><td><div id='HencSolicitudes'>");
    	out.print("<p class='Husu'> Usuario</p>");
		out.print("<p class='Hsubarea'>Sub Area</p>");
		out.print("<p class='HfchEnvio'>Fecha de Envio</p>");
		out.print("<p class='HHrsEnvio'>Hora de Envio</p>");
		out.print("<p class='HPrio'>Prioridad</p>");
		out.print("<p class='Hestado'>Estado</p>");
		out.print("<p class='HfchFin'>Fecha Finalizado</p>");
		out.print("<p class='HHrsFin'>Hora Finalizado</p>");
		out.print("<p class='Hver'></p></div>");
		out.print("<div id='HlistSolicitudes'>");
        rs = ms.ObtenerSolicitudesPorFechasReq(FechaIniBus, FechaFinBus);	
		try{
			while(rs.next()){
				out.print("<div id='Henclineas'>");
		    	out.print("<p class='Hencusu'>"+rs.getString(1)+"</p>");
		    	out.print("<p class='Hencsubarea'>"+rs.getString(2)+"</p>");
		    	out.print("<p class='HencfchEnvio'>"+rs.getString(3)+"</p>");
		    	out.print("<p class='HencHrsEnvio'>"+rs.getString(4)+"</p>");
		    	out.print("<p class='HencPrio'>"+rs.getString(5)+"</p>");
		    	out.print("<p class='Hencestado' >"+rs.getString(7)+"</p></div>");
		    	out.print("<p class='HencfchFin'>"+rs.getString(8)+"</p>");
				out.print("<p class='HencHrsFin'>"+rs.getString(9)+"</p>");
		    	out.print("<p class='Hencver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitud3Req("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
			}
			
			rs.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
	out.print("</div></td></tr></table>");
	}
	
	
	if(va.equals("BuscarAReq")){
		out.print("<table width='100%' border='1'><tr><td colspan='4'><div align='center' class='style11' id='cabecera2' >Reporte Solicitudes por Area </div></td></tr>");
		out.print("<tr><td width='15%'>Seleccione el  Area </td><td width='21%'><select name='cmbMenu' id='cmbMenusop' onChange='CambioOpcionesMenusopReq()'><option value='Seleccione' selected='selected'>Seleccione</option>");
		rs=mp.MostrarAreas();
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			out.print("</select></td><td width='17%'>Seleccione La Sub Area </td><td><div id='OpcionesMenusop'><select name='cmbOpMenu' id='cmbOpMenu'><option value='Seleccione'>Seleccione</option></select></div></td></tr>");
			out.print("<tr><td colspan='4'><div id='OpDisponibles'></div></td></tr></table>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error: "+e);
			e.printStackTrace();
		}
	
	}
	
	if(va.equals("3.12")){
		String CodigoAreaSop=req.getParameter("CodigoAreaSop");
		rs=mp.MostrarSubAreas(CodigoAreaSop);
		out.print("<div id='OpcionesMenusop'><select name='cmbOpMenu' id='cmbOpMenu' onChange='mostrarSolAReq()'><option value='Seleccione'>Seleccione</option>");
		try {
			while(rs.next()){
				out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
			}
			out.print("</select></div>");
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error: "+e);
			e.printStackTrace();
		}
	}
	if(va.equals("181")){
		String codareabus = req.getParameter("codareabus");
		
		out.print("<table><tr><td><div id='HencSolicitudes'>");
    	out.print("<p class='Husu'> Usuario</p>");
		out.print("<p class='Hsubarea'>Sub Area</p>");
		out.print("<p class='HfchEnvio'>Fecha de Envio</p>");
		out.print("<p class='HHrsEnvio'>Hora de Envio</p>");
		out.print("<p class='HPrio'>Prioridad</p>");
		out.print("<p class='Hestado'>Estado</p>");
		out.print("<p class='HfchFin'>Fecha Finalizado</p>");
		out.print("<p class='HHrsFin'>Hora Finalizado</p>");
		out.print("<p class='Hver'></p></div>");
		out.print("<div id='HlistSolicitudes'>");
        rs = ms.ObtenerSolicitudesPorAreaReq(codareabus);
		try{
			while(rs.next()){
				out.print("<div id='Henclineas'>");
		    	out.print("<p class='Hencusu'>"+rs.getString(1)+"</p>");
		    	out.print("<p class='Hencsubarea'>"+rs.getString(2)+"</p>");
		    	out.print("<p class='HencfchEnvio'>"+rs.getString(3)+"</p>");
		    	out.print("<p class='HencHrsEnvio'>"+rs.getString(4)+"</p>");
		    	out.print("<p class='HencPrio'>"+rs.getString(5)+"</p>");
		    	out.print("<p class='Hencestado' >"+rs.getString(7)+"</p></div>");
		    	out.print("<p class='HencfchFin'>"+rs.getString(8)+"</p>");
				out.print("<p class='HencHrsFin'>"+rs.getString(9)+"</p>");
		    	out.print("<p class='Hencver' id='codisolici2' value='"+rs.getString(6)+"'><a onclick='ventCrearSolicitud3Req("+rs.getString(6)+")' href='#'> Ver Problemas</a></p></div>");
			}
			
			rs.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
	out.print("</div></td></tr></table>");
	}
	
	if(va.equals("ReporteReq")){
		out.print(" " +
				"<p class='titulo'>Problema que más se presenta</p> " +
				"<p class='enunciado'>Seleccione el intervalo de fechas en la cual va a realizar la consulta.</p> " +
				"<p class='fechainicial'>Fecha Inicial</p><input type='text' onKeyup='masca(this,patron,true,0,0,0)' size='10' id='fchini' class='textfechaf'> " +
				"<p class='ejemplo'>(dia-mes-año)</p> " +
				"<p class='fechafinal'>Fecha Final</p><input type='text' size='10' onKeyup='masca(this,patron,true,0,0,0)' id='fchfin'class='textfechaf'>(dia-mes-año) " +
				"<br><br><br><input type='button' id='btnaceptarR' onclick='validarfechareporteReq1()' value='Aceptar'class='btnproblema'>");
		
	}
	
	
	if(va.equals("13.1")){
		
		String FechaActualRep = req.getParameter("fechatodayRep");
		String fecharepini = req.getParameter("fechaini");
		String fecharepfin = req.getParameter("fechafin");
		
		String anio = fecharepini.substring(6,10);   		
		String dia = fecharepini.substring(0,2);	
		String mes = fecharepini.substring(3,5);
		
		
		String aniofin = fecharepfin.substring(6,10);
		String diafin = fecharepfin.substring(0,2);
		String mesfin = fecharepfin.substring(3,5);
		
		String fchestadosub = anio+"-"+mes+"-"+dia;
		String hraestadosub = aniofin+"-"+mesfin+"-"+diafin;
		
		
		
		out.print("<div id='reportepro'>");
		out.print("<div id='encreptit'>Reporte de Problemas</div>");
		out.print("<div id='titrep'>");
		out.print("<p class='totprorep'><b>Total Problemas<b></p>");
		out.print("<p class='probcomun'><b>Problema mas Solicitado<b></p>");
		out.print("<p class='reportesol'><b>No veces Solicitado<b></p>");
		out.print("<p class='reportesubarea'><b>Sub Area que mas lo Solicita<b></p>");
		out.print("<p class='reportcantsol'><b>Cantidad de Solicitdes<b></p></div>");
		out.print("</div>");
		out.print("<div id='repest'>");
		rs8 = ms.TotalProblemasReq(fchestadosub, hraestadosub);
		try{
			while(rs8.next()){ 
				out.print("<div id='repline'>");
		    	out.print("<p class='reptotpro'>"+rs8.getString(1)+"</p>");
		    	  
			}
			 
			rs8.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
		
		rs = ms.ReporteProblemaReq(fchestadosub, hraestadosub);
		
		try{
			while(rs.next()){
			    
				
		    	out.print("<p class='repproblema'>"+rs.getString(1)+"</p>");
		    	out.print("<p class='repsol' value='' id='pro'>"+rs.getString(2)+"</p>");
		  
		       
			}
			 
			rs.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
		rs1 = ms.CodProblemaComunReq(fchestadosub, hraestadosub);
		String CodProCom = "";
		try{
			while(rs1.next()){
				CodProCom = rs1.getString(1);
				
			}
			
			rs1.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
		rs2 = ms.ObtenerSubAreaComunReq(CodProCom,fchestadosub, hraestadosub);
		try{
			while(rs2.next()){
				out.print("<input type='hidden' id='FechaActualRep' value='"+FechaActualRep+"'>");
				out.print("<input type='hidden' id='fchestadosub' value='"+fchestadosub+"'>");
				out.print("<input type='hidden' id='hraestadosub' value='"+hraestadosub+"'>");
				out.print("<input type='hidden' id='codprobcomunrep' value='"+CodProCom+"'>");
				out.print("<p class='repsubacon'>"+rs2.getString(1)+"</p>");
				out.print("<p class='repsubacant'>"+rs2.getString(2)+"</p>");
				
			}
			out.print("</div>");
			rs2.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();
		}

		out.print("</div><br><input type='button' onclick='cerrar(8)' value='Cerrar' class='repbtncerrar'><input type='button' onclick='ImprimirReporte()' value='Imprimir' class='repbtnimprimir'>");
		
	}
	if(va.equals("ReporteSReq")){
		out.print(" " +
				"<p class='titulo'>Sub Area que más Solicitudes Realiza</p> " +
				"<p class='enunciado'>Seleccione el intervalo de fechas en la cual va a realizar la consulta.</p> " +
				"<p class='fechainicial'>Fecha Inicial</p><input type='text' onKeyup='masca(this,patron,true,0,0,0)' size='10' id='fchinisub' class='textfechaf'> " +
				"<p class='ejemplo'>(dia-mes-año)</p> " +
				"<p class='fechafinal'>Fecha Final</p><input type='text' size='10' onKeyup='masca(this,patron,true,0,0,0)' id='fchfinsub' class='textfechaf'>(dia-mes-año) " +
				"<br><br><br><input type='button' id='btnaceptarR' onclick='validarfechareporteReq2()' value='Aceptar'class='btnproblema'>");
		
	}
	if(va.equals("14.1")){
		
		String fechatodayRep2 = req.getParameter("fechatodayRep2");
		String fecharepinisub = req.getParameter("fechainisub");
		String fecharepfinsub = req.getParameter("fechafinsub");
		
		String anio = fecharepinisub.substring(6,10);   		
		String dia = fecharepinisub.substring(0,2);	
		String mes = fecharepinisub.substring(3,5);
		
		
		String aniofin = fecharepfinsub.substring(6,10);
		String diafin = fecharepfinsub.substring(0,2);
		String mesfin = fecharepfinsub.substring(3,5);
		
		String fchestadosub2 = anio+"-"+mes+"-"+dia;
		String hraestadosub2 = aniofin+"-"+mesfin+"-"+diafin;
		
		
		out.print("<div id='reporteprosub'>");
		out.print("<div id='encreptitsub'>Sub Area que realiza más Solicitudes</div>");
		out.print("<div id='titrepsub'>");
		out.print("<p class='repnosol'><b>No Solicitudes<b></p>");
		out.print("<p class='repsubarea'><b>Sub Area<b></p>");
		out.print("<p class='repsubsol'><b>Solicitudes Realizadas<b></p></div>");
		out.print("</div>");
		out.print("<div id='repest'>");
		
		rs8 = ms.TotalSolicitudesReq(fchestadosub2, hraestadosub2);
		try{
			while(rs8.next()){ 
				out.print("<div id='repline'>");
		    	out.print("<p class='repnosolsub'>"+rs8.getString(1)+"</p>");
		    	  
			}
			 
			rs8.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
		
		
		rs = ms.ObtenerSubAreaRepReq(fchestadosub2, hraestadosub2);
		
		try{
			while(rs.next()){
				out.print("<input type='hidden' id='FechaActualRep2' value='"+fechatodayRep2+"'>");
				out.print("<input type='hidden' id='fchestadosub2' value='"+fchestadosub2+"'>");
				out.print("<input type='hidden' id='hraestadosub2' value='"+hraestadosub2+"'>");
		    	out.print("<p class='repproblemasub'>"+rs.getString(1)+"</p>");
		    	out.print("<p class='repsolsub' value='' id='pro'>"+rs.getString(2)+"</p>");
		  
		       
			}
			 out.print("</div>");
			rs.getStatement().getConnection().close();
		}catch(SQLException e){
			e.printStackTrace();

	}
		out.print("</div><br><br><input type='button' onclick='cerrar(9)' value='Cerrar' class='repbtncerrarsub'><input type='button' onclick='ImprimirReporte2()' value='Imprimir' class='repbtnimprimir2'>");
		
	}
	
			
		}

    }

