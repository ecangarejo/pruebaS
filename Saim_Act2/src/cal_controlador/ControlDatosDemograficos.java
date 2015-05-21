package cal_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cal_metodo.MetodoCalidad;



public class ControlDatosDemograficos extends HttpServlet {
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		}

		
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			HttpSession session = req.getSession(true);
		
			String va = req.getParameter("valor");
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out=res.getWriter();
			ResultSet rs=null;
			ResultSet rs1=null;
			ResultSet rs2=null;
			ResultSet rs3=null;
			MetodoCalidad mc=new MetodoCalidad();
			
			if(va.equals("RepDatosDemograficos")){
				String tdia=req.getParameter("tdia");
				String tmes=req.getParameter("tmeses");
				String tano=req.getParameter("tanos");
				String ftdia=req.getParameter("ftdia");
				String ftmes=req.getParameter("ftmeses");
				String ftano=req.getParameter("ftanos");
				String ftent=req.getParameter("ftent");
				String Serv=req.getParameter("Serv");
				String area=req.getParameter("area");
				String Fechai=tano+"-"+tmes+"-"+tdia;
				String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
				System.out.println("Ent"+ftent);
				
				out.println("<br><br><table class='contpre' width='100%'>");
				out.println("<tr align='center'><td colspan='5' id='cabecera2' class='style11'  ><br><b>DATOS ESTADISTICOS DEMOGRAFICOS DE LA POBLACION <br></b></td>");
				int cond=0; //Esta variable me va a indicar que opcion de consulta es la que voy a llamar segun los parametros dados por el usuario
				/*
				 * cond 1 --> Serv = Todas Ent= Todas
				 * cond 2 --> Serv = Todas Ent='eps especifica'
				 * cond 3 --> Serv = Urgencia Ent= Todas 
				 * cond 4 --> Serv = Urgencia Ent= 'eps especifica'
				 * cond 5 --> Serv = Uci, Hospitalizacion, Ambulatorio SubArea= Todas  Ent=Todas
				 * cond 6 --> Serv = Uci, Hospitalizacion, Ambulatorio SubArea= Todas  Ent='eps especifica'
				 * cond 7 --> Serv = Uci, Hospitalizacion, Ambulatorio SubArea= 'servicio especifico' Ent= Todas 
				 * cond 8 --> Serv = Uci, Hospitalizacion, Ambulatorio SubArea= 'servicio especifico' Ent= Todas */
				int valserv=0;
				int valent=0;
				out.print("<tr align='center' class='rep' ><td><br><b>Rango : </b>"+Fechai+" AL "+Fechaf+" &nbsp; &nbsp;</td>");
				if(Serv.equals("todas")){
					out.print("<td align='rigth'> <br><b>SERVICIO : </b>TODOS &nbsp;</td>");
					valserv=1;
				}else{
					if(Serv.equals("1")){
						out.print("<td align='rigth'><br><b>SERVICIO : </b> URGENCIA &nbsp;</td>");
						valserv=2;
					}else{
							rs=mc.BuscarServ(Serv);
							try {
								while(rs.next()){
									out.print("<td align='rigth'><br><b>SERVICIO : </b>"+rs.getString(1)+" &nbsp;</td>");
								}
								rs.getStatement().getConnection().close();
								if(area.equals("todas")){
									out.print("<td align='rigth'><br><b>SUBAREA: </b>TODAS &nbsp;</td>");
									valserv=3;
								
								}else{
									rs=mc.BuscarSubArea(Serv,area);
									while(rs.next()){
										out.print("<td align='rigth'><br><b>SUBAREA: </b>"+rs.getString(1)+" &nbsp;</td>");
										valserv=4;
									}
									rs.getStatement().getConnection().close();
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
					}
				}
				
				if(ftent.equals("todas")){
					out.print("<td align='rigth'><br><b>ENTIDAD : </b> TODAS  &nbsp;</td>");
					valent=1;
				}else{
					rs=mc.BuscarNombEnt(ftent);
					try {
						while(rs.next()){
							out.print("<td align='rigth'><b><br>ENTIDAD : </b>"+rs.getString(1)+" &nbsp;</td>");
							valent=2;
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				out.print("</tr>");
				long sumgenero=0;
				if(((valserv==1)&&(valent==1))||((valserv==1)&&(valent==2))||((valserv==2)&&(valent==1))||((valserv==2)&&(valent==2))){
						/*APLICABLE A LA CONDICION 1 a la  4 
						 * Consultas de todos los datos demograficos (sexo, grupo etareo, patologia mas frecuente, entidades mas frecuentes,
						 *  traslados a los diferentes servicios derivados de la urgencia) solo  */  		
						int rep=0;
						///GENERO////
							rs=mc.BuscarGeneroval1(Fechai,Fechaf, Serv, ftent,rep);
								out.print("<tr align='left' ><td colspan='12' bgcolor='#E3E3E5'><br><b><u>Clasificacion por Sexo(F,M) : </><br></b></td></tr>");
								try {
									while(rs.next()){
										out.print("<tr class='rep'><td align='left'>"+rs.getString(1)+"</td><td align='center'>"+rs.getString(2)+"</td><td align='center'>");
										sumgenero=sumgenero+rs.getLong(2);
										if(rs.getString(3).equals("1")){
											out.print("Dado de Alta");
										}else{
											out.print("En Estancia");
										}
										
									}
									rs.getStatement().getConnection().close();
									out.print("</td></tr><tr class='rep'><td>Total de Pacientes</td><td align='center'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+0+"&tipor="+1+"' >"+sumgenero+"</td></tr>");
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						//// FIN GENERO////
								
						///GRUPO ETAREO////
								out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><br><b><u>Grupo Etareo : </><br></td></tr>");
								int Ed1=0;
								int Ed2=1;
								long SumEdades=0;
								out.println("<tr align='left'><td><i>Rango</i></td><td align='center'><i>No. Pacientes</i></td></tr>");
								int cont=0;
								while(cont!=7){
									rs=mc.BuscarEdades(Ed1,Ed2,Fechai,Fechaf, ftent,Serv,0);
									System.out.println("cont "+cont);
									System.out.println("Buscar Edades: Ed1"+Ed1+" Ed2 "+Ed2+"Fechai "+Fechai+"Fechaf "+Fechaf+" ftent"+ftent);
									if(Ed2<66){
									try {
										if(rs.next()){
										out.println("<tr align='left' class='rep'><td> De "+Ed1+" a "+Ed2+" A&#241;os </td><td align='center'><font color='black'>"+rs.getString(1)+"</font></td></tr>");
										SumEdades=((SumEdades)+(rs.getLong(1)));
										}
										rs.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									Ed1=Ed2+1;
									if(cont==0){
										Ed2=Ed2+4;
										System.out.println("val 0 "+Ed2);
									}else{
										if(cont==1){
											Ed2=Ed2+9;
											System.out.println("val 1 "+Ed2);
										}else{
											if(cont==2){
												Ed2=Ed2+16;
												System.out.println("val 2 "+Ed2);
											}else{
												if(cont==3){
													Ed2=Ed2+20;
													System.out.println("val 3 "+Ed2);
												}else{
													if(cont==4){
													Ed2=Ed2+15;
													System.out.println("val 4 "+Ed2);
													}else{
														if(cont==5){
															Ed2=Ed2+1;
														}
													}
												}
											}
										}
									}
									}else{
										Ed1=66;
										Ed2=200;
										rs=mc.BuscarEdades(Ed1, Ed2, Fechai, Fechaf, ftent,Serv,0);
										try {
											if(rs.next()){
											out.println("<tr align='left' class='rep'><td>Mayor a "+Ed1+" A&#241;os </td><td align='center'><font color='black'>"+rs.getString(1)+"</font></td></tr>");
											SumEdades=((SumEdades)+(rs.getLong(1)));
											}
											rs.getStatement().getConnection().close();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									cont=cont+1;
									
								}
							out.print("<tr class='rep'><td>Total Pacientes</td><td align='center'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+0+"&tipor="+2+"' >"+SumEdades+"</a></td></tr>");
								
								
						///FIN GRUPO ETAREO////
						
						/////PATOLOGIA MAS FRECUENTE////
							
							out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><br><u><b>Patologia mas Frecuente </b></u><br></td>");
							rs=mc.BuscarPatologia(Fechai,Fechaf,ftent,Serv,0,"0");
							out.println("<tr><td align='left'><br><i>Nombre</i></td><td align='center'><br><i><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+0+"&tipor="+3+"&codDiag="+0+"' >No. Pacientes</a></i></td></tr>");
						
							try {
								while(rs.next()){
									out.println("<tr  class='rep'><td align='left'>"+rs.getString(1)+"</td><td align='center'><font color='black'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+0+"&tipor="+3+"&codDiag="+rs.getString(3)+"' >"+rs.getString(2)+"</a></font></td></tr>");				
								}
								rs.getStatement().getConnection().close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						/////FIN PATOLOGIA//////
							
						////ENTIDADES MAS FRECUENTES////	
							if(ftent.equals("todas")){
								out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><u><br><b>Entidades Mas Frecuentes </b></u><br></td>");
								rs=mc.BuscarEntidades(Fechai,Fechaf,Serv,0,"0");
								long SumEntidades=0;
								out.println("<tr align='left'><td><i><br>Nombre EPS</i></td><td align='center'><i><br>No. Pacientes</i></td></tr>");
								try {
									while (rs.next()){
										out.println("<tr align='left' class='rep'><td>"+rs.getString(2)+"</td><td align='center'><font color='black'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+0+"&tipor="+4+"&codent="+rs.getString(3)+"' >"+rs.getString(1)+"</a></font></td></tr>");
										SumEntidades=SumEntidades+rs.getLong(1);
									}
									out.println("<tr class='rep'><td><b>Total de Pacientes por Entidad  </b> </td><td align='center'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+"todas"+"&Serv="+Serv+"&area="+0+"&tipor="+4+"&codent="+0+"' >"+SumEntidades+"</a></td></tr>");
									
									rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
					
							//// FIN ENTIDADES MAS FRECUENTES /////
							
							/////TRASLADOS A LOS DIFERENTES SERVICIOS DERIVADOS DE LA URGENCIA////
							if(Serv.equals("1")){
									out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><u><br>Traslados a los Diferentes Servicios Derivados de la Urgencia </u><br></td>");
									rs=mc.BuscarTraslados(Fechai,Fechaf,ftent);
									out.println("<tr align='left'><td><i>Area</i></td><td><i>Nombre Eps</i></td><td align='center'><i>No.Pacientes</i></td></tr>");
									try {
										while(rs.next()){
											out.println("<tr align='left' class='rep'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td align='center'><font color='black'>"+rs.getString(3)+"</font></td></tr>");
										}
										rs.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
							/////FIN TRASLADOS DE LOS DIFERENTES SERVICIO DERIVADOS DE LA URGENCIA
					
							
							
				}else{
					//out.print("<tr><td>EN DESARROLO ESTE REPORTE </td></tr>");
					
					/***
					 * Esta Validacion es para los condiciones 4 al 8, es decir cuando el servicio es Hospitalizacion, 
					 * Uci o Ambulatorio, y cuando se escoge alguna subarea 
					 */
					System.out.println("Serv"+Serv);
					System.out.println("Sub Area"+area);
					System.out.println("Eps "+ftent);
					///GENERO////
					int rep=0; //indicador para consulta
					rs=mc.BuscarGenero(Fechai,Fechaf, Serv, ftent,area,rep);
						out.print("<tr align='left' ><td colspan='12' bgcolor='#E3E3E5'><br><b><u>Clasificacion por Sexo(F,M) : </><br></b></td></tr>");
						try {
							while(rs.next()){
								out.print("<tr class='rep'><td align='left'>"+rs.getString(1)+"</td><td align='center'>"+rs.getString(2)+"</td><td align='center'>");
								sumgenero=sumgenero+rs.getLong(2);
								if(rs.getString(3).equals("1")){
									out.print("Dado de Alta");
								}else{
									out.print("En Estancia");
								}
								
							}
							rs.getStatement().getConnection().close();
							//out.print("</td></tr><tr class='rep'><td> Total de Pacientes </td><td><a href='Cal_ValidacionReporte.jsp?Fechai="+Fechai+"&Fechaf="+Fechaf+"&Serv="+Serv+"&ftent="+ftent+"&area="+area+"' >"+sumgenero+"</a></td></tr>");
							//out.print("</td></tr><tr class='rep'><td> Total de Pacientes </td><td><a onclick='ReporteDetalleGenero("+Fechai+","+Fechaf+","+Serv+","+ftent+","+area+")' >"+sumgenero+"</a></td></tr>");
							out.print("</td></tr><tr class='rep'><td> Total de Pacientes </td><td align='center'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+area+"&tipor="+1+"' >"+sumgenero+"</a></td></tr>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				//// FIN GENERO////
										
				////GRUPO ETAREO///
						out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><br><b><u>Grupo Etareo : </><br></td></tr>");
						int Ed1=0;
						int Ed2=1;
						long SumEdades=0;
						out.println("<tr align='left'><td><i>Rango</i></td><td align='center'><i>No. Pacientes</i></td></tr>");
						int cont=0;
						while(cont!=7){
							rs=mc.BuscarEdadesVal2(Ed1,Ed2,Fechai,Fechaf, ftent,Serv,area,0);
							System.out.println("cont "+cont);
							System.out.println("Buscar Edades: Ed1"+Ed1+" Ed2 "+Ed2+"Fechai "+Fechai+"Fechaf "+Fechaf+" ftent"+ftent);
							if(Ed2<66){
							try {
								if(rs.next()){
								out.println("<tr align='left' class='rep'><td> De "+Ed1+" a "+Ed2+" A&#241;os </td><td align='center'><font color='black'>"+rs.getString(1)+"</font></td></tr>");
								SumEdades=((SumEdades)+(rs.getLong(1)));
								}
								rs.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Ed1=Ed2+1;
							if(cont==0){
								Ed2=Ed2+4;
								System.out.println("val 0 "+Ed2);
							}else{
								if(cont==1){
									Ed2=Ed2+9;
									System.out.println("val 1 "+Ed2);
								}else{
									if(cont==2){
										Ed2=Ed2+16;
										System.out.println("val 2 "+Ed2);
									}else{
										if(cont==3){
											Ed2=Ed2+20;
											System.out.println("val 3 "+Ed2);
										}else{
											if(cont==4){
											Ed2=Ed2+15;
											System.out.println("val 4 "+Ed2);
											}else{
												if(cont==5){
													Ed2=Ed2+1;
												}
											}
										}
									}
								}
							}
							}else{
								Ed1=66;
								Ed2=200;
								rs=mc.BuscarEdadesVal2(Ed1, Ed2, Fechai, Fechaf, ftent,Serv,area,0);
								try {
									if(rs.next()){
									out.println("<tr align='left' class='rep'><td>Mayor a "+Ed1+" A&#241;os </td><td align='center'><font color='black'>"+rs.getString(1)+"</font></td></tr>");
									SumEdades=((SumEdades)+(rs.getLong(1)));
									}
									rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							cont=cont+1;
							
						}
					out.print("<tr class='rep'><td>Total Pacientes</td><td align='center'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+area+"&tipor="+2+"' >"+SumEdades+"</a></td></tr>");
						
				////FIN GRUPO ETAREO///
					
					
				///INICIO PATOLOGIA MAS FRECUENTE PARA HOSP, UCI Y AMB ////
					
					out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><br><u><b>Patologia mas Frecuente </b></u><br></td>");
					rs=mc.BuscarPatologiaVal2(Fechai,Fechaf,ftent,Serv,area,0,"0");
					out.println("<tr><td align='left'><br><i>Nombre</i></td><td align='center'><br><i><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+area+"&tipor="+3+"&codDiag="+0+"' >No. Pacientes</a></i></td></tr>");
				
					try {
						while(rs.next()){
							out.println("<tr  class='rep'><td align='left'>"+rs.getString(1)+"</td><td align='center'><font color='black'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+area+"&tipor="+3+"&codDiag="+rs.getString(3)+"' >"+rs.getString(2)+"</a></font></td></tr>");				
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				////FIN PATOLOGIA MAS FRECUENTE PARA HOSP, UCI  Y AMB ///
					
					
				///INCIO DE ENTIDADES FRECUENTES PARA HOSP, UCI Y AMB  /////
					
					if(ftent.equals("todas")){
						out.println("<tr align='left'><td colspan='12' bgcolor='#E3E3E5'><u><br><b>Entidades Mas Frecuentes </b></u><br></td>");
						rs=mc.BuscarEntidadesVal2(Fechai,Fechaf,Serv,area,0,"0");
						long SumEntidades=0;
						out.println("<tr align='left'><td><i><br>Nombre EPS</i></td><td align='center'><i><br>No. Pacientes</i></td></tr>");
						try {
							while (rs.next()){
								out.println("<tr align='left' class='rep'><td>"+rs.getString(2)+"</td><td align='center'><font color='black'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+area+"&tipor="+4+"&codent="+rs.getString(3)+"' >"+rs.getString(1)+"</font></td></tr>");
								SumEntidades=SumEntidades+rs.getLong(1);
							}
							out.println("<tr class='rep'><td><b>Total de Pacientes por Entidad  </b> </td><td align='center'><a href='Cal_ValidacionReporte.jsp?tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Serv="+Serv+"&area="+area+"&tipor="+4+"&codent="+0+"' >"+SumEntidades+"</a></td></tr>");
							
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				///FIN DE ENTIDADES FRECUENTES  PARA HOSP, UCI Y AMB  /////
					
				}
				
					out.println("</table>");
			}
			
			if(va.equals("ReporteGenero")){
				
				String tdia=req.getParameter("tdia");
				String tmes=req.getParameter("tmes");
				String tano=req.getParameter("tano");
				String ftdia=req.getParameter("ftdia");
				String ftmes=req.getParameter("ftmes");
				String ftano=req.getParameter("ftano");
				String Serv=req.getParameter("Serv");
				String area=req.getParameter("area");
				String ftent=req.getParameter("ftent");
				System.out.println("Entrando a Reporte Genero");
				String Fechai=tano+"-"+tmes+"-"+tdia;
				String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
				int rep=1; //indicador par consulta
				
				out.print("<table width='100%' >");
				out.print("<tr class='TituloRep' align='center'><td colspan='4'><font color='black'>DETALLE DE PACIENTE POR GENERO</font></td></tr>");
				out.print("<tr><td><table width='100%' class='TituloRep' align='center' >");
				out.print("<tr><td><br>Comprendido de "+Fechai+" al "+Fechaf+"</td>");
				rs1=mc.BuscarServ(Serv);
				System.out.println("Serv "+Serv);
				if(Serv.equals("todas")){
					out.print("<td><br>Servicio : TODAS");
				}else{
					try {
						while(rs1.next()){
							out.print("<td><br>Servicio: "+rs1.getString(1)+"</td>");
						}rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				out.print("</td>");
				if(area.equals("todas")){
					out.print("<td><br> SubArea: TODAS</td>");
				}else{
					if(area.equals("0")){
							out.print("<td><br></td>");
					}else{
							rs1=mc.BuscarSubArea(Serv, area);
							try {
								while(rs1.next()){
									out.print("<td><br> Subarea: "+rs1.getString(1)+"</td>");
								}rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					
				}
				
				System.out.println("ftent "+ftent);
				if(ftent.equals("todas")){
					out.print("<td><br> Entidad : TODAS </td>");
					System.out.println("Entrando a validacion de entidades"+ftent);
				}else{
					rs1=mc.BuscarNombEnt(ftent);
					try {
						while(rs1.next()){
							out.print("<td><br> Entidad :"+rs1.getString(1)+"</td>");
						}rs1.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				out.print("</tr><tr><td colspan='4'><br></td></tr></table></td></tr>");
				if(area.equals("0")){
					System.out.println("entrando cuando el area"+area);
						rs=mc.BuscarGeneroval1(Fechai, Fechaf, Serv, ftent, rep);
						out.print("<tr><td coslpan='4'><table width='100%' border='1'>");
						out.print("<tr class='contpre'  bgcolor='#97C2E9' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
						try {
								while(rs.next()){
								out.print("<tr class='rep'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
								if(rs.getString(7).equals("1")){
									out.print("<td>Dado de Alta</td>");
								}else{
									out.print("<td>En Estancia</td>");
								}
								out.print("<td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td>");
								String adm=rs.getString(11);
								String codpac=rs.getString(12);
								rs2=mc.BuscarDiagIngr(adm,codpac);
								while(rs2.next()){
									out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
								}rs2.getStatement().getConnection().close();
								
								rs2=mc.BuscarDiagEgres(adm,codpac);
								while(rs2.next()){
									out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
								}rs2.getStatement().getConnection().close();
								
								rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
								while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td>");
								}rs2.getStatement().getConnection().close();
								out.print("</tr>");
						}rs.getStatement().getConnection().close();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.print("</table></td></tr>");
				}else{
				
					rs=mc.BuscarGenero(Fechai,Fechaf, Serv, ftent,area,rep);	
					out.print("<tr><td coslpan='4'><table width='100%' border='1'>");
					out.print("<tr class='contpre'  bgcolor='#97C2E9' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Descripcion DI<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
					try {
							while(rs.next()){
							out.print("<tr class='rep'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
							if(rs.getString(7).equals("1")){
								out.print("<td>Dado de Alta</td>");
							}else{
								out.print("<td>En Estancia</td>");
							}
							out.print("<td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td>");
							String adm=rs.getString(11);
							String codpac=rs.getString(12);
							rs2=mc.BuscarDiagIngr(adm,codpac);
							while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
							}rs2.getStatement().getConnection().close();
							
							rs2=mc.BuscarDiagEgres(adm,codpac);
							while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
							}rs2.getStatement().getConnection().close();
							
							rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
							while(rs2.next()){
							out.print("<td>"+rs2.getString(1)+"</td>");
							}rs2.getStatement().getConnection().close();
							
							out.print("</tr>");
					}rs.getStatement().getConnection().close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.print("</table></td></tr>");
				}
				out.print("</table>");
				
				
			}
			
			
			if(va.equals("ReporteEtareo")){
				
				String tdia=req.getParameter("tdia");
				String tmes=req.getParameter("tmes");
				String tano=req.getParameter("tano");
				String ftdia=req.getParameter("ftdia");
				String ftmes=req.getParameter("ftmes");
				String ftano=req.getParameter("ftano");
				String Serv=req.getParameter("Serv");
				String area=req.getParameter("area");
				String ftent=req.getParameter("ftent");
				System.out.println("Entrando a Reporte Etareo");
				String Fechai=tano+"-"+tmes+"-"+tdia;
				String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
				int rep=1; //indicador par consulta
				
				out.print("<table width='100%' >");
				out.print("<tr class='TituloRep' align='center'><td colspan='4'><font color='black'>DETALLE DE PACIENTE POR GRUPO ETAREO</font></td></tr>");
				out.print("<tr><td><table width='100%' class='TituloRep' align='center' >");
									out.print("<tr><td><br>Comprendido de "+Fechai+" al "+Fechaf+"</td>");
									rs1=mc.BuscarServ(Serv);
									//System.out.println("Serv "+Serv);
									if(Serv.equals("todas")){
										out.print("<td><br>Servicio : TODAS");
									}else{
										try {
											while(rs1.next()){
												out.print("<td><br>Servicio: "+rs1.getString(1)+"</td>");
											}rs1.getStatement().getConnection().close();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									out.print("</td>");
									if(area.equals("todas")){
										out.print("<td><br> SubArea: TODAS</td>");
									}else{
										if(area.equals("0")){
												out.print("<td><br></td>");
										}else{
												rs1=mc.BuscarSubArea(Serv, area);
												try {
													while(rs1.next()){
														out.print("<td><br> Subarea: "+rs1.getString(1)+"</td>");
													}rs1.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
										}
					
									}
				
									System.out.println("ftent "+ftent);
								if(ftent.equals("todas")){
									out.print("<td><br> Entidad : TODAS </td>");
									//System.out.println("Entrando a validacion de entidades"+ftent);
								}else{
									rs1=mc.BuscarNombEnt(ftent);
									try {
										while(rs1.next()){
											out.print("<td><br> Entidad :"+rs1.getString(1)+"</td>");
										}rs1.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								out.print("</tr><tr><td colspan='4'><br></td></tr></table>");
				out.println("</td></tr>");
				if(area.equals("0")){
					//System.out.println("entrando cuando el area"+area);
					int Ed1=0;
					int Ed2=1;
					int cont=0;
					while(cont!=7){
						rs=mc.BuscarEdades(Ed1,Ed2,Fechai,Fechaf, ftent,Serv,1);
						if(Ed2<66){
						try {
							if(rs.next()){
							out.println("<tr align='center' class='contpre'  bgcolor='#E0F0FC' ><td colspan='4'><b> De "+Ed1+" a "+Ed2+" A&#241;os </b></td></tr>");
							out.print("<tr><td colspan='4'>");
								out.print("<table width='100%' border='1'>");
									out.print("<tr class='contpre'  bgcolor='#97C2E9' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
									rs3=mc.BuscarEdades(Ed1,Ed2,Fechai,Fechaf, ftent,Serv,1);
									while(rs3.next()){
									out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
										if(rs3.getString(7).equals("1")){
											out.print("<td>Dado de Alta</td>");
										}else{
											out.print("<td>En Estancia</td>");
										}
										out.print("<td>"+rs3.getString(13)+"</td>");
										out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
										String adm=rs3.getString(11);
										String codpac=rs3.getString(12);
										rs2=mc.BuscarDiagIngr(adm,codpac);
										while(rs2.next()){
											out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
										}rs2.getStatement().getConnection().close();
										rs2=mc.BuscarDiagEgres(adm,codpac);
										while(rs2.next()){
											out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
										}rs2.getStatement().getConnection().close();
										
										rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
										while(rs2.next()){
										out.print("<td>"+rs2.getString(1)+"</td>");
										}rs2.getStatement().getConnection().close();
									out.print("</tr>");
								}rs3.getStatement().getConnection().close();//finaliza while
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							Ed1=Ed2+1;
							if(cont==0){
								Ed2=Ed2+4;
								System.out.println("val 0 "+Ed2);
								}else{
									if(cont==1){
										Ed2=Ed2+9;
										System.out.println("val 1 "+Ed2);
									}else{
										if(cont==2){
											Ed2=Ed2+16;
											System.out.println("val 2 "+Ed2);
										}else{
											if(cont==3){
												Ed2=Ed2+20;
												System.out.println("val 3 "+Ed2);
											}else{
												if(cont==4){
													Ed2=Ed2+15;
													System.out.println("val 4 "+Ed2);
												}else{
													if(cont==5){
														Ed2=Ed2+1;
													}
												}
											}
										}
									}
								}
							}else{
								Ed1=66;
								Ed2=200;
								rs=mc.BuscarEdades(Ed1, Ed2, Fechai, Fechaf, ftent,Serv,1);
									try {
										if(rs.next()){
										out.print("<table border='1'");
										out.println("<tr align='center' class='contpre' bgcolor='#E0F0FC' ><td colspan='13'><b>Mayor a "+Ed1+" A&#241;os </b></td></tr>");
										rs3=mc.BuscarEdades(Ed1, Ed2, Fechai, Fechaf, ftent,Serv,1);
											while(rs3.next()){
												out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
													if(rs3.getString(7).equals("1")){
														out.print("<td>Dado de Alta</td>");
													}else{
														out.print("<td>En estancia</td>");
													}
													out.print("<td>"+rs3.getString(13)+"</td>");
													out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
													String adm=rs3.getString(11);
													String codpac=rs3.getString(12);
													rs2=mc.BuscarDiagIngr(adm,codpac);
													while(rs2.next()){
														out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
													}rs2.getStatement().getConnection().close();
													rs2=mc.BuscarDiagEgres(adm,codpac);
													while(rs2.next()){
														out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
													}rs2.getStatement().getConnection().close();
													
													rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
													while(rs2.next()){
													out.print("<td>"+rs2.getString(1)+"</td>");
													}rs2.getStatement().getConnection().close();
												out.print("</tr>");
											}rs3.getStatement().getConnection().close();
										
										}
										rs.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								cont=cont+1;
								out.print("<table></td></tr>");
								
						
					}//finaliza while del contador
						
						out.print("</table>");
					
				}else{
					int Ed1=0;
					int Ed2=1;
					int cont=0;
					while(cont!=7){
						rs=mc.BuscarEdadesVal2(Ed1,Ed2,Fechai,Fechaf, ftent,Serv,area,1);
						if(Ed2<66){
						try {
							if(rs.next()){
							out.println("<tr align='center' class='contpre'  bgcolor='#97C2E9' ><td colspan='4'><b> De "+Ed1+" a "+Ed2+" A&#241;os </b></td></tr>");
							out.print("<tr><td colspan='4'>");
								out.print("<table width='100%' border='1'>");
									out.print("<tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
									rs3=mc.BuscarEdadesVal2(Ed1,Ed2,Fechai,Fechaf, ftent,Serv,area,1);
									while(rs3.next()){
									out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
										if(rs3.getString(7).equals("1")){
											out.print("<td>Dado de Alta</td>");
										}else{
											out.print("<td>En estancia</td>");
										}
										out.print("<td>"+rs3.getString(13)+"</td>");
										out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
										String adm=rs3.getString(11);
										String codpac=rs3.getString(12);
										rs2=mc.BuscarDiagIngr(adm,codpac);
										while(rs2.next()){
											out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
										}rs2.getStatement().getConnection().close();
										rs2=mc.BuscarDiagEgres(adm,codpac);
										while(rs2.next()){
											out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
										}rs2.getStatement().getConnection().close();
										
										rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
										while(rs2.next()){
										out.print("<td>"+rs2.getString(1)+"</td>");
										}rs2.getStatement().getConnection().close();
										
									out.print("</tr>");
								}rs3.getStatement().getConnection().close();//finaliza while
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							Ed1=Ed2+1;
							if(cont==0){
								Ed2=Ed2+4;
								System.out.println("val 0 "+Ed2);
								}else{
									if(cont==1){
										Ed2=Ed2+9;
										System.out.println("val 1 "+Ed2);
									}else{
										if(cont==2){
											Ed2=Ed2+16;
											System.out.println("val 2 "+Ed2);
										}else{
											if(cont==3){
												Ed2=Ed2+20;
												System.out.println("val 3 "+Ed2);
											}else{
												if(cont==4){
													Ed2=Ed2+15;
													System.out.println("val 4 "+Ed2);
												}else{
													if(cont==5){
														Ed2=Ed2+1;
													}
												}
											}
										}
									}
								}
							}else{
								Ed1=66;
								Ed2=200;
								rs=mc.BuscarEdadesVal2(Ed1, Ed2, Fechai, Fechaf, ftent,Serv,area,1);
									try {
										if(rs.next()){
										out.print("<table width='100%' border='1'>");
										out.println("<tr align='center' class='contpre' bgcolor='#97C2E9'><td colspan='13'><b>Mayor a "+Ed1+" A&#241;os </b> </td></tr>");
										out.print("<tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
										rs3=mc.BuscarEdadesVal2(Ed1, Ed2, Fechai, Fechaf, ftent,Serv,area,1);
										while(rs3.next()){
											out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
												if(rs3.getString(7).equals("1")){
													out.print("<td>Dado de Alta</td>");
												}else{
													out.print("<td>En estancia</td>");
												}
												out.print("<td>"+rs3.getString(13)+"</td>");
												out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
												String adm=rs3.getString(11);
												String codpac=rs3.getString(12);
												rs2=mc.BuscarDiagIngr(adm,codpac);
												while(rs2.next()){
													out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
												}rs2.getStatement().getConnection().close();
												rs2=mc.BuscarDiagEgres(adm,codpac);
												while(rs2.next()){
													out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
												}rs2.getStatement().getConnection().close();
												rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
												while(rs2.next()){
												out.print("<td>"+rs2.getString(1)+"</td>");
												}rs2.getStatement().getConnection().close();
											out.print("</tr>");
										}rs3.getStatement().getConnection().close();
										
										}
										rs.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								cont=cont+1;
								out.print("<table></td></tr>");
								
						
					}//finaliza while del contador
						
						out.print("</table>");
					
				
				}
			}
			
			
			
			if(va.equals("ReportePatologia")){
				String tdia=req.getParameter("tdia");
				String tmes=req.getParameter("tmes");
				String tano=req.getParameter("tano");
				String ftdia=req.getParameter("ftdia");
				String ftmes=req.getParameter("ftmes");
				String ftano=req.getParameter("ftano");
				String Serv=req.getParameter("Serv");
				String area=req.getParameter("area");
				String ftent=req.getParameter("ftent");
				System.out.println("Entrando a Reporte Patologia ");
				System.out.println("area"+area);
				String Fechai=tano+"-"+tmes+"-"+tdia;
				String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
				String codDiag=req.getParameter("codDiag");
				int rep=1; //indicador par consulta
				
				out.print("<table width='100%' >");
				out.print("<tr class='TituloRep' align='center'><td colspan='4'><font color='black'>DETALLE DE PACIENTE POR PATOLOGIAS MAS FRECUENTE </font></td></tr>");
				out.print("<tr><td>");
					out.print("<table width='100%' class='TituloRep' align='center' >");
									out.print("<tr>");
										out.print("<td><br>Comprendido de "+Fechai+" al "+Fechaf+"</td>");
											rs1=mc.BuscarServ(Serv);
											//System.out.println("Serv "+Serv);
											if(Serv.equals("todas")){
												out.print("<td><br>Servicio : TODAS");
											}else{
												try {
													while(rs1.next()){
														out.print("<td><br>Servicio: "+rs1.getString(1)+"</td>");
													}rs1.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											out.print("</td>");
											if(area.equals("todas")){
												out.print("<td><br> SubArea: TODAS</td>");
											}else{
												if(area.equals("0")){
													out.print("<td><br></td>");
												}else{
													rs1=mc.BuscarSubArea(Serv, area);
													try {
														while(rs1.next()){
														out.print("<td><br> Subarea: "+rs1.getString(1)+"</td>");
														}rs1.getStatement().getConnection().close();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
											}
											System.out.println("ftent "+ftent);
											if(ftent.equals("todas")){
												out.print("<td><br> Entidad : TODAS </td>");
												//System.out.println("Entrando a validacion de entidades"+ftent);
											}else{
												rs1=mc.BuscarNombEnt(ftent);
												try {
													while(rs1.next()){
														out.print("<td><br> Entidad :"+rs1.getString(1)+"</td>");
													}rs1.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										out.print("</tr>");
										out.print("<tr><td colspan='4'><br></td></tr>");
						out.print("</table>");
					out.println("</td></tr>");
					if(area.equals("0")){
						rs=mc.BuscarPatologia(Fechai, Fechaf, ftent, Serv, 1, codDiag);
						try {
							if(rs.next()){
								out.println("<tr><td colspan='4'><table border='1' width='100%'>");
										if(codDiag.equals("0")){
											rs3=mc.BuscarPatologia(Fechai, Fechaf, ftent, Serv, 1, codDiag);
											out.print("<tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
											while(rs3.next()){
												out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
												if(rs3.getString(7).equals("1")){
													out.print("<td>Dado de Alta</td>");
												}else{
													out.print("<td>En estancia</td>");
												}
												out.print("<td>"+rs3.getString(13)+"</td>");
												out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
												String adm=rs3.getString(11);
												String codpac=rs3.getString(12);
												rs2=mc.BuscarDiagIngr(adm,codpac);
												while(rs2.next()){
													out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("<td>"+rs3.getString(15)+"</td>");
												out.print("<td>"+rs3.getString(14)+"</td>");
												rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
												while(rs2.next()){
												out.print("<td>"+rs2.getString(1)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("</tr>");
											}rs3.getStatement().getConnection().close();
										}else{
											//System.out.println("entrando a validacion 1");
											out.println("<tr><td colspan='11' class='contpre' bgcolor='#97C2E9' align='center' >DIAGNOSTICO EGRESO: <b>"+rs.getString(15)+" "+rs.getString(14)+"</b></td></tr>");
											out.print("<tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
											rs3=mc.BuscarPatologia(Fechai, Fechaf, ftent, Serv, 1, codDiag);
											while(rs3.next()){
												out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
												if(rs3.getString(7).equals("1")){
													out.print("<td>Dado de Alta</td>");
												}else{
													out.print("<td>En estancia</td>");
												}
												out.print("<td>"+rs3.getString(13)+"</td>");
												out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
												String adm=rs3.getString(11);
												String codpac=rs3.getString(12);
												rs2=mc.BuscarDiagIngr(adm,codpac);
												while(rs2.next()){
													out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("<td>"+rs3.getString(15)+"</td>");
												out.print("<td>"+rs3.getString(14)+"</td>");
												rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
												while(rs2.next()){
												out.print("<td>"+rs2.getString(1)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("</tr>");
											}rs3.getStatement().getConnection().close();
											
										}
										out.print("</table></td></tr>");
							}rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						System.out.println("entrando a validacion2");
						rs=mc.BuscarPatologiaVal2(Fechai, Fechaf, ftent, Serv, area, 1, codDiag);
						
						try {
							if(rs.next()){
								out.println("<tr><td colspan='4'><table border='1' width='100%'>");
										if(codDiag.equals("0")){
											out.print("<tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
											rs3=mc.BuscarPatologiaVal2(Fechai, Fechaf, ftent, Serv, area, 1, codDiag);
											while(rs3.next()){
												out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
												if(rs3.getString(7).equals("1")){
													out.print("<td>Dado de Alta</td>");
												}else{
													out.print("<td>En estancia</td>");
												}
												out.print("<td>"+rs3.getString(13)+"</td>");
												out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
												String adm=rs3.getString(11);
												String codpac=rs3.getString(12);
												rs2=mc.BuscarDiagIngr(adm,codpac);
												while(rs2.next()){
													out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("<td>"+rs3.getString(15)+"</td>");
												out.print("<td>"+rs3.getString(14)+"</td>");
												rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
												while(rs2.next()){
												out.print("<td>"+rs2.getString(1)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("</tr>");
											}rs3.getStatement().getConnection().close();
										}else{
											System.out.println("mandar a escribir");
											out.println("<tr><td colspan='11' class='contpre' bgcolor='#97C2E9' align='center' >DIAGNOSTICO EGRESO : <b>"+rs.getString(15)+" "+rs.getString(14)+"</b></td></tr>");
											out.print("<tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
											rs3=mc.BuscarPatologiaVal2(Fechai, Fechaf, ftent, Serv, area, 1, codDiag);
											while(rs3.next()){
												System.out.println("entrando al while");
												out.print("<tr class='rep'><td>"+rs3.getString(1)+"</td><td>"+rs3.getString(2)+"</td><td>"+rs3.getString(3)+" "+rs3.getString(4)+" "+rs3.getString(5)+"</td><td>"+rs3.getString(6)+"</td>");
												if(rs3.getString(7).equals("1")){
													out.print("<td>Dado de Alta</td>");
												}else{
													out.print("<td>En estancia</td>");
												}
												out.print("<td>"+rs3.getString(13)+"</td>");
												out.print("<td>"+rs3.getString(8)+"</td><td>"+rs3.getString(9)+"</td><td>"+rs3.getString(10)+"</td>");
												String adm=rs3.getString(11);
												String codpac=rs3.getString(12);
												rs2=mc.BuscarDiagIngr(adm,codpac);
												while(rs2.next()){
													out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("<td>"+rs3.getString(15)+"</td>");
												out.print("<td>"+rs3.getString(14)+"</td>");
												rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
												while(rs2.next()){
												out.print("<td>"+rs2.getString(1)+"</td>");
												}rs2.getStatement().getConnection().close();
												out.print("</tr>");
											}rs3.getStatement().getConnection().close();
											
										}
										out.print("</table></td></tr>");
							}rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					out.println("</table>");
				
				
			}
			
			
			if(va.equals("ReporteEntidadesFrecuentes")){
				String tdia=req.getParameter("tdia");
				String tmes=req.getParameter("tmes");
				String tano=req.getParameter("tano");
				String ftdia=req.getParameter("ftdia");
				String ftmes=req.getParameter("ftmes");
				String ftano=req.getParameter("ftano");
				String Serv=req.getParameter("Serv");
				String area=req.getParameter("area");
				String ftent=req.getParameter("ftent");
				System.out.println("Entrando a Reporte de Entidades Frecuentes  ");
				//System.out.println("area"+area);
				String Fechai=tano+"-"+tmes+"-"+tdia;
				String Fechaf=ftano+"-"+ftmes+"-"+ftdia;
				String codent=req.getParameter("codent");
				
				out.print("<table width='100%' >");
				out.print("<tr class='TituloRep' align='center'><td colspan='4'><font color='black'>DETALLE DE PACIENTE POR ENTIDADES MAS FRECUENTES </font></td></tr>");
				out.print("<tr><td>");
					out.print("<table width='100%' class='TituloRep' align='center' >");
									out.print("<tr>");
										out.print("<td><br>Comprendido de "+Fechai+" al "+Fechaf+"</td>");
											rs1=mc.BuscarServ(Serv);
											//System.out.println("Serv "+Serv);
											if(Serv.equals("todas")){
												out.print("<td><br>Servicio : TODAS");
											}else{
												try {
													while(rs1.next()){
														out.print("<td><br>Servicio: "+rs1.getString(1)+"</td>");
													}rs1.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											out.print("</td>");
											if(area.equals("todas")){
												out.print("<td><br> SubArea: TODAS</td>");
											}else{
												if(area.equals("0")){
													out.print("<td><br></td>");
												}else{
													rs1=mc.BuscarSubArea(Serv, area);
													try {
														while(rs1.next()){
														out.print("<td><br> Subarea: "+rs1.getString(1)+"</td>");
														}rs1.getStatement().getConnection().close();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
											}
											System.out.println("ftent "+ftent);
											if(ftent.equals("todas")){
												out.print("<td><br> Entidad : TODAS </td>");
												//System.out.println("Entrando a validacion de entidades"+ftent);
											}else{
												rs1=mc.BuscarNombEnt(ftent);
												try {
													while(rs1.next()){
														out.print("<td><br> Entidad :"+rs1.getString(1)+"</td>");
													}rs1.getStatement().getConnection().close();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										out.print("</tr>");
										out.print("<tr><td colspan='4'><br></td></tr>");
						out.print("</table>");
					out.println("</td></tr>");
					
				if(area.equals("0")){
					//esto es para todo y servicio de urgencia 
					
					if(codent.equals("0")){
						out.println("<tr><td colspan='4'></td></tr>");
					}else{
						rs1=mc.BuscarNombEnt(codent);
						try {
							while(rs1.next()){
								out.println("<tr class='contpre'><td colspan='4'>Entidad : <b>"+rs1.getString(1)+"</b></td></tr>");
							}rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(codent.equals("0")){
					rs=mc.BuscarEntidades(Fechai, Fechaf, Serv, 1,codent); 
					out.print("<tr><td colspan='4'><table width='100%' border='1'><tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
					 try {
						while(rs.next()){
							out.print("<tr class='rep'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
							if(rs.getString(7).equals("1")){
								out.print("<td>Dado de Alta</td>");
							}else{
								out.print("<td>En estancia</td>");
							}
							out.print("<td>"+rs.getString(13)+"</td>");
							out.print("<td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td>");
							String adm=rs.getString(11);
							String codpac=rs.getString(12);
							rs2=mc.BuscarDiagIngr(adm,codpac);
							while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
							}rs2.getStatement().getConnection().close();
							rs2=mc.BuscarDiagEgres(adm,codpac);
							while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
							}rs2.getStatement().getConnection().close();
							rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
							while(rs2.next()){
							out.print("<td>"+rs2.getString(1)+"</td>");
							}rs2.getStatement().getConnection().close();
							out.print("</tr>");
						 }rs.getStatement().getConnection().close();
						 out.print("</table></td></tr>");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}else{
						rs=mc.BuscarEntidades(Fechai, Fechaf, Serv, 1,codent); 
						out.print("<tr><td colspan='4'><table width='100%' border='1'><tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
						try {
							while(rs.next()){
								out.print("<tr class='rep'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
								if(rs.getString(7).equals("1")){
									out.print("<td>Dado de Alta</td>");
								}else{
									out.print("<td>En estancia</td>");
								}
								out.print("<td>"+rs.getString(13)+"</td>");
								out.print("<td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td>");
								String adm=rs.getString(11);
								String codpac=rs.getString(12);
								rs2=mc.BuscarDiagIngr(adm,codpac);
								while(rs2.next()){
									out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
								}rs2.getStatement().getConnection().close();
								rs2=mc.BuscarDiagEgres(adm,codpac);
								while(rs2.next()){
									out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
								}rs2.getStatement().getConnection().close();
								rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
								while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td>");
								}rs2.getStatement().getConnection().close();
								out.print("</tr>");
							 }rs.getStatement().getConnection().close();
							 out.print("</table></td></tr>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
				}else{
					//esto es para servicio de HOSP, UCI, AMB Y sus diferentes subareas
									
					if(codent.equals("0")){
						out.println("<tr><td colspan='4'></td></tr>");
					}else{
						rs1=mc.BuscarNombEnt(codent);
						try {
							while(rs1.next()){
								out.println("<tr class='contpre'><td colspan='4'>Entidad :<b>"+rs1.getString(1)+"</b></td></tr>");
							}rs1.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(codent.equals("0")){
					rs=mc.BuscarEntidadesVal2(Fechai, Fechaf, Serv, area, 1, codent);
					out.print("<tr><td colspan='4'><table border='1' width='100%'><tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Nombre de Entidad<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
					 try {
						while(rs.next()){
							out.print("<tr class='rep'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
							if(rs.getString(7).equals("1")){
								out.print("<td>Dado de Alta</td>");
							}else{
								out.print("<td>En estancia</td>");
							}
							out.print("<td>"+rs.getString(13)+"</td>");
							out.print("<td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td>");
							String adm=rs.getString(11);
							String codpac=rs.getString(12);
							rs2=mc.BuscarDiagIngr(adm,codpac);
							while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
							}rs2.getStatement().getConnection().close();
							rs2=mc.BuscarDiagEgres(adm,codpac);
							while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
							}rs2.getStatement().getConnection().close();
							out.print("</tr>");
						 }rs.getStatement().getConnection().close();
						 out.print("</table></td></tr>");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}else{
						rs=mc.BuscarEntidadesVal2(Fechai, Fechaf, Serv, area, 1, codent);
						out.print("<tr><td colspan='4'><table border='1' width='100%'><tr class='contpre'  bgcolor='#E0F0FC' align='center' font='bold' ><td><br>Tipo Doc.<br></td><td><br>Numero de Doc<br></td><td><br>Nombre Paciente<br></td><td><br>Genero<br></td><td><br>Estado<br></td><td><br>Edad</td><td><br>No. Admision<br></td><td><br>Fecha de Ingreso<br></td><td><br>Cod DI<br></td><td><br>Diagnostico de Ingreso<br></td><td><br>Cod DE<br></td><td><br>Diagnostico de Egreso<br></td><td>Salida del Paciente</td></tr>");
						try {
							while(rs.next()){
								out.print("<tr class='rep'><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td>");
								if(rs.getString(7).equals("1")){
									out.print("<td>Dado de Alta</td>");
								}else{
									out.print("<td>En estancia</td>");
								}
								out.print("<td>"+rs.getString(13)+"</td>");
								out.print("<td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td>");
								String adm=rs.getString(11);
								String codpac=rs.getString(12);
								rs2=mc.BuscarDiagIngr(adm,codpac);  
								while(rs2.next()){
									out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
								}rs2.getStatement().getConnection().close();
								rs2=mc.BuscarDiagEgres(adm,codpac);
								while(rs2.next()){
									out.print("<td>"+rs2.getString(1)+"</td><td>"+rs2.getString(2)+"</td>");
								}rs2.getStatement().getConnection().close();
								rs2=mc.BuscarEstadoSalida(adm,codpac,Serv);
								while(rs2.next()){
								out.print("<td>"+rs2.getString(1)+"</td>");
								}rs2.getStatement().getConnection().close();
								out.print("</tr>");
							 }rs.getStatement().getConnection().close();
							 out.print("</table></td></tr>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
					
					
					
				}
				
				out.print("</table>");
				
				
			}
			
			if(va.equals("VistaSubArea")){
				String Serv=req.getParameter("serv");
				//System.out.println(Serv);
				out.print("<table><tr><td>SUBAREA : </td><td> <select id='area'><option value='todas'>TODAS</option>");
				rs=mc.BuscarArea(Serv);
				try {
					while(rs.next()){
						out.print("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
					}rs.getStatement().getConnection().close();
					out.print("</select>");
					out.print("</td></tr></table>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		}
		
		
}
