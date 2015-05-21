package cig_controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cig_metodo.MetodoProgramacion;

public class ControlProgramacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);  
		
		String va = req.getParameter("valor");
		PrintWriter out=res.getWriter();
		ResultSet rs=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ResultSet rs3=null;
		MetodoProgramacion mp=new MetodoProgramacion();
		String fv8="";
		
		if(va.equals("formp")){
			String tp=req.getParameter("tp");
			String fechap=req.getParameter("fechap");
			String usuario=req.getParameter("codigou");
			if((fechap==null)||(fechap.equals(""))){
				
			}else{
			String dv8=fechap.substring(0, 2);
			String mv8=fechap.substring(3, 5);
			String av8=fechap.substring(6, 10);
			fv8=av8+"-"+mv8+"-"+dv8;
			System.out.println("Valor de la fecha en formp "+fv8);
			}
			rs=mp.BuscarEstadoProgramacion(tp,fv8);
			String estado="";
			String codigo="";
			try {
				if(rs.next()){
					estado=rs.getString(1);
					codigo=rs.getString(2);
					System.out.println("valor de estado "+estado);
				
					if(estado.equals("0")){
						
						if(tp.equals("2")){
							//cardiovascular 
							out.print("<table  border='1' align='center' width='100%'><tr><td colspan='7' ><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR CARDIOVASCULAR DEL "+fv8+" </div></td></tr>");
							out.print("<tr align='center' bgcolor='#D7D7DB'><td width='12%'><br>HORA</td><td width='15%'><br>NOMBRE DEL PACIENTE</td><td width='15%'><br>PROCEDIMIENTO</td><td width='15%'><br>EPS</td><td width='15%'><br>CIRUJANO</td><td width='13%'><br>AYUDANTE</td><td width='13%'><br>ANESTESIOLOGO</td><td width='2%'></td></tr>");
							out.print("<tr width='12%'><td width='10%'><input type='text' id='hora' onKeyup='checkhora()' size='1'/><select id='rhora' style='width:50%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td><td width='15%'><input type='text' id='npac' size='32'/></td><td width='15%'><input type='text' id='proc' size='32' /></td>");
							out.print("<td><select id='eps' style='width:100%' style='font-size:9px;'  /><option value='seleccione' >Seleccione</option>");
							rs1=mp.BuscarEps();
							while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
							}
							rs1.getStatement().getConnection().close();
							out.print("</select></td>");
							out.print("<td><select id='ciru' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='SAULO PINEDO'>SAULO PINEDO</option><option value='JAIME AROCA'>JAIME AROCA</option><option value='JOSE MARULANDA'>JOSE MARULANDA</option><option value='MIRIAM LOBELO'>MIRIAM LOBELO</option><option value='PEDRO TURBAY'>PEDRO TURBAY</option><option value='LEONELO ORTEGA'>LEONELO ORTEGA</option><option value='MAURICIO DIAZ'>MAURICIO DIAZ</option><option value='RICHARD SMAIRA'>RICHARD SMAIRA</option>");
							rs1=mp.BuscarCiru();
							while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+" </option>");
							}
							rs1.getStatement().getConnection().close();
							out.print("</select></td>");
							out.print("<td><input type='text' id='ayud' size='30' /></td>");
							out.print("<td><select id='anes' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='EN TURNO' >EN TURNO</option><option value='VICTOR PEREZ'>VICTOR PEREZ</option>");
							rs1=mp.BuscarAnes();
							while(rs1.next()){
								out.print("<option value='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
							}rs1.getStatement().getConnection().close();
							out.print("</select></td>");
							out.print("<td><input type='button' value='+' onclick='Agregar2("+codigo+","+usuario+","+tp+")' /></td></tr></table>");
							//	out.println("prueba");
						}else{
							 if(tp.equals("1")){
								 
								 //cirugia general 
									out.print("<table border='1' align='center' ><tr><td colspan='11'><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR CIRUGIA GENERAL DEL "+fv8+" </div></td></tr>");
									out.print("<tr align='center' bgcolor='#D7D7DB'><td width='10%'><br>HORA</td><td width='10%'><br>NOMBRE DEL PACIENTE</td><td width='4%'><br>TELEFONO</td><td width='9%'><br>PROCEDIMIENTO</td><td width='12%'><br>EPS</td><td width='11%'><br>CIRUJANO</td><td width='5%'><br>AYUDANTE</td><td width='11%'><br>ANESTESIOLOGO</td><td width='13%'><br>SERVICIO</td><td width='5%'><br>HAB</td><td width='8%'><br>QUIROFANO</td><td width='2%'></td></tr>");
									out.print("<tr><td width='10%'><input type='text' id='hora' size='1' onKeyup='checkhora()' /><select id='rhora' style='width:50%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td><td width='10%'><input type='text' id='npac' size='23' /></td><td width='4%'><input type='text'id='tel' size='6'/></td><td width='9%'><input type='text' id='proc' size='20' /></td>");
									out.print("<td width='12%'><select id='eps' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
									rs1=mp.BuscarEps();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
									}
									rs1.getStatement().getConnection().close();
								
									out.print("</select></td>");
									out.print("<td width='11%' ><select id='ciru' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='SAULO PINEDO'>SAULO PINEDO</option><option value='JAIME AROCA'>JAIME AROCA</option><option value='JOSE MARULANDA'>JOSE MARULANDA</option><option value='MIRIAM LOBELO'>MIRIAM LOBELO</option><option value='PEDRO TURBAY'>PEDRO TURBAY</option><option value='LEONELO ORTEGA'>LEONELO ORTEGA</option><option value='MAURICIO DIAZ'>MAURICIO DIAZ</option><option value='RICHARD SMAIRA'>RICHARD SMAIRA</option>");
									
									rs1=mp.BuscarCiru();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+" </option>");
									}
									rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td width='5%'><input type='text' id='ayud' size='10' /></td>");
									out.print("<td width='11%'><select id='anes' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='EN TURNO' >EN TURNO</option><option value='VICTOR PEREZ'>VICTOR PEREZ</option><option value='JORGE ESCOBAR'>JORGE ESCOBAR</option>");
									rs1=mp.BuscarAnes();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
									}rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td width='13%'><select name='serv' id='serv' style='width:100%' style='font-size:9px;' onChange='tipoarea()'><option value='seleccione' > Seleccione </option><option value='DE CASA' >DE CASA </option>");
									rs1=mp.BuscarServ();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(2)+"'>"+rs1.getString(1)+"</option>");
									}rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td width='5%'><div id='subarea' ></div> </td><td width='8%'>");
									out.print("<select id='Quir' style='width:100%' style='font-size:9px;' ><option value='Seleccione'>Seleccione</option>");
							
									rs1=mp.BuscarArea();
									try {
									
										while(rs1.next()){
											out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
										}
										rs1.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									out.println("</select></td><td width='2%'><input type='button' value='+' onclick='Agregar1("+codigo+","+usuario+","+tp+")' /></td></tr>");
									out.println("</table>");
							 }else{
								 if(tp.equals("3")){
									 //oncologia 
									 out.print("<table border='1' align='center' ><tr><td colspan='15'><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR  DE ONCOLOGIA DEL "+fv8+" </div></td></tr>");
									 out.print("<tr  align='center' bgcolor='#D7D7DB'><td width='8%'><br>HORA</br></td><td width='10%'><br>DOCUMENTO</br></td><td width='10%'><br>NOMBRE DEL PACIENTE </br></td><td width='10%'><br>ENTIDAD</br></td><td width='5%'><br>TELEFONO</br></td><td width='5%'><br>TERAPIA INTRATECAL</td><td width='8%'><br>TERAPIA SISTEMATICA</td><td width='12%'><br>SERVICIO</br></td><td width='5%'><br>HAB</td><td width='4%'><br>EDAD</td><td width='7%'><br>DIAGNOSTICO</br></td><td width='3%'><br>OPOR. CITA </br></td><td width='3%'><br>SE</br></td><td width='10%'>OBSERVACIONES</td><td width='2%'></td></tr>");
									 out.print("<tr><td width='8%'><input type='text' id='hora' size='1' onKeyup='checkhora()' /><select id='rhora' style='width:40%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td>");
									 out.print("<td width='10%'><select id='tdoc' style='width:40%' >");
									 rs3=mp.BuscarTipoDoc();
									 while(rs3.next()){
										 out.print("<option value='"+rs3.getString(2)+"' title='"+rs3.getString(1)+"' >"+rs3.getString(2)+"</option>");
									 }
									 rs3.getStatement().getConnection().close();
									 out.print("</select><input type='text' id='numdoc' style='width:50%'></td>");
									 out.print("<td width='10%'><input type='text' id='npac' size='20' /></td>");
									 out.print("<td width='10%'><select id='eps' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
										rs1=mp.BuscarEps();
										while(rs1.next()){
											out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
										}
										rs1.getStatement().getConnection().close();
									
										out.print("</select></td>");
									 out.print("<td width='5%'><input type='text'id='tel' style='width:90%'/></td>");
									 out.print("<td width='5%'><input type='text' id='intra' style='width:90%' /></td><td width='8%'><input type='text' id='sistema' style='width:90%' /></td>");
									 out.print("<td width='12%'><select name='serv' id='serv' style='width:100%' style='font-size:9px;' onChange='tipoarea()'><option value='seleccione' > Seleccione </option><option value='DE CASA' >DE CASA </option>");
										rs1=mp.BuscarServ();
										while(rs1.next()){
											out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(2)+"'>"+rs1.getString(1)+"</option>");
										}rs1.getStatement().getConnection().close();
										out.print("</select></td>");
										out.print("<td width='5%'><div id='subarea' ></div></td>");
										out.print("<td width='4%'><input type='text' id='edad' style='width:90%' /></td><td width='7%'><input type='text' id='diag' style='width:90%' /></td><td width='3%'><select id='ocita' style='width:90%' style='font-size:9px;' ><option value='NO'>NO</option><option value='SI'>SI</option></select></td><td width='3%'><select id='se' style='width:100%' style='font-size:9px;' ><option value='NO'>NO</option><option value='SI'>SI</option></select></td><td width='10%'><input type='text' id='obs' style='width:90%' /></td><td width='2%'><input type='button' value='+' onclick='Agregar1("+codigo+","+usuario+","+tp+")' /></td></tr>");
									 out.print("</table>");
								 }else{
									 if((tp.equals("4"))||(tp.equals("5"))){
										 //hemodinamia y endoscopia 
										 out.print("<table border='1' align='center' ><tr><td colspan='11'><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR DE ");
										  if(tp.equals("4")){
											  out.print("DE HEMODINAMIA"+fv8+" </div></td></tr>");
										  }else{
											  out.print("DE ENDOSCOPIA"+fv8+" </div></td></tr>");
										  }
											out.print("<tr align='center' bgcolor='#D7D7DB'><td width='10%'><br>HORA</td><td width='10%'><br>NOMBRE DEL PACIENTE</td><td width='4%'><br>TELEFONO</td><td width='9%'><br>PROCEDIMIENTO</td><td width='12%'><br>EPS</td><td width='11%'><br>CIRUJANO</td><td width='11%'><br>ANESTESIOLOGO</td><td width='13%'><br>SERVICIO</td><td width='5%'><br>HAB</td><td width='5%'><br>OBSERVACION</td><td width='8%'><br>AREA</td><td width='2%'></td></tr>");
											out.print("<tr><td width='10%'><input type='text' id='hora' size='1' onKeyup='checkhora()' /><select id='rhora' style='width:50%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td><td width='10%'><input type='text' id='npac' size='23' /></td><td width='4%'><input type='text'id='tel' size='6'/></td><td width='9%'><input type='text' id='proc' size='20' /></td>");
											out.print("<td width='12%'><select id='eps' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
											rs1=mp.BuscarEps();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
											}
											rs1.getStatement().getConnection().close();
										
											out.print("</select></td>");
											out.print("<td width='11%' ><select id='ciru' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
											
											rs1=mp.BuscarCiru(tp);
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+" </option>");
											}
											rs1.getStatement().getConnection().close();
											out.print("</select></td>");
											out.print("<td width='11%'><select id='anes' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='EN TURNO' >EN TURNO</option><option value='VICTOR PEREZ'>VICTOR PEREZ</option>");
											rs1=mp.BuscarAnes();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
											}rs1.getStatement().getConnection().close();
											out.print("</select></td>");
											out.print("<td width='13%'><select name='serv' id='serv' style='width:100%' style='font-size:9px;' onChange='tipoarea()'><option value='seleccione' > Seleccione </option><option value='DE CASA' >DE CASA </option>");
											rs1=mp.BuscarServ();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(2)+"'>"+rs1.getString(1)+"</option>");
											}rs1.getStatement().getConnection().close();
											out.print("</select></td>");
											out.print("<td width='5%'><div id='subarea' ></div> </td>");
											out.print("<td width='5%'><input type='text' id='obs' size='10' /></td>");
											out.print("<td width='8%'><select id='Quir' style='width:100%' style='font-size:9px;' ><option value='Seleccione'>Seleccione</option>");
									
											rs1=mp.BuscarArea();
											try {
											
												while(rs1.next()){
													out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
												}
												rs1.getStatement().getConnection().close();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											out.println("</select></td><td width='2%'><input type='button' value='+' onclick='Agregar1("+codigo+","+usuario+","+tp+")' /></td></tr>");
											out.println("</table>");
										 
									 }
								 }
							 }
						}
					}else{
						 out.print("YA EXISTE UNA PROGRAMACION PARA ESTA FECHA Y TIPO DE SERVICIO");
					}
				}else{
					System.out.println("entrando naranja");
					mp.GuardarP(fv8,tp);
					rs2=mp.BuscarEstadoProgramacion(tp,fv8);
					if(rs2.next()){
						codigo=rs2.getString(2);
					}
					rs2.getStatement().getConnection().close();
					if(tp.equals("2")){
						//cardiovascular 
									System.out.println("tp es igual a "+tp);
									out.print("<table  border='1' align='center' width='100%'><tr><td colspan='7' ><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR DEL "+fv8+" </div></td></tr>");
									out.print("<tr align='center' bgcolor='#D7D7DB'><td width='12%'><br>HORA</td><td width='15%'><br>NOMBRE DEL PACIENTE</td><td width='15%'><br>PROCEDIMIENTO</td><td width='15%'><br>EPS</td><td width='15%'><br>CIRUJANO</td><td width='13%'><br>AYUDANTE</td><td width='13%'><br>ANESTESIOLOGO</td><td width='2%'></td></tr>");
									out.print("<tr width='12%'><td width='10%'><input type='text' id='hora' onKeyup='checkhora()' size='1'/><select id='rhora' style='width:50%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td><td width='15%'><input type='text' id='npac' size='32'/></td><td width='15%'><input type='text' id='proc' size='32' /></td>");
									out.print("<td><select id='eps' style='width:100%' style='font-size:9px;'  /><option value='seleccione' >Seleccione</option>");
									rs1=mp.BuscarEps();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
									}
									rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td><select id='ciru' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='SAULO PINEDO'>SAULO PINEDO</option><option value='JAIME AROCA'>JAIME AROCA</option><option value='JOSE MARULANDA'>JOSE MARULANDA</option><option value='MIRIAM LOBELO'>MIRIAM LOBELO</option><option value='PEDRO TURBAY'>PEDRO TURBAY</option><option value='LEONELO ORTEGA'>LEONELO ORTEGA</option><option value='MAURICIO DIAZ'>MAURICIO DIAZ</option><option value='RICHARD SMAIRA'>RICHARD SMAIRA</option>");
									rs1=mp.BuscarCiru();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+" </option>");
									}
									rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td><input type='text' id='ayud' size='30' /></td>");
									out.print("<td><select id='anes' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='EN TURNO' >EN TURNO</option><option value='VICTOR PEREZ'>VICTOR PEREZ</option>");
									rs1=mp.BuscarAnes();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
									}rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td><input type='button' value='+' onclick='Agregar2("+codigo+","+usuario+","+tp+")' /></td></tr></table>");
									//	out.println("prueba");
						}else{
							  if(tp.equals("1")){
									System.out.println("tp es igual a "+tp);
									out.print("<table border='1' align='center' ><tr><td colspan='11'><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR DEL "+fv8+" </div></td></tr>");
									out.print("<tr align='center' bgcolor='#D7D7DB'><td width='10%'><br>HORA</td><td width='10%'><br>NOMBRE DEL PACIENTE</td><td width='4%'><br>TELEFONO</td><td width='9%'><br>PROCEDIMIENTO</td><td width='12%'><br>EPS</td><td width='11%'><br>CIRUJANO</td><td width='5%'><br>AYUDANTE</td><td width='11%'><br>ANESTESIOLOGO</td><td width='13%'><br>SERVICIO</td><td width='5%'><br>HAB</td><td width='8%'><br>QUIROFANO</td><td width='2%'></td></tr>");
									out.print("<tr><td width='10%'><input type='text' id='hora' size='1' onKeyup='checkhora()' /><select id='rhora' style='width:50%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td><td width='10%'><input type='text' id='npac' size='23' /></td><td width='4%'><input type='text'id='tel' size='6'/></td><td width='9%'><input type='text' id='proc' size='20' /></td>");
									out.print("<td width='12%'><select id='eps' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
									rs1=mp.BuscarEps();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
									}
									rs1.getStatement().getConnection().close();
								
									out.print("</select></td>");
									out.print("<td width='11%' ><select id='ciru' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='SAULO PINEDO'>SAULO PINEDO</option><option value='JAIME AROCA'>JAIME AROCA</option><option value='JOSE MARULANDA'>JOSE MARULANDA</option><option value='MIRIAM LOBELO'>MIRIAM LOBELO</option><option value='PEDRO TURBAY'>PEDRO TURBAY</option><option value='LEONELO ORTEGA'>LEONELO ORTEGA</option><option value='MAURICIO DIAZ'>MAURICIO DIAZ</option><option value='RICHARD SMAIRA'>RICHARD SMAIRA</option>");
									
									rs1=mp.BuscarCiru();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+" </option>");
									}
									rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td width='5%'><input type='text' id='ayud' size='10' /></td>");
									out.print("<td width='11%'><select id='anes' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='EN TURNO' >EN TURNO</option><option value='VICTOR PEREZ'>VICTOR PEREZ</option>");
									rs1=mp.BuscarAnes();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
									}rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td width='13%'><select name='serv' id='serv' style='width:100%' style='font-size:9px;' onChange='tipoarea()'><option value='seleccione' > Seleccione </option><option value='DE CASA' >DE CASA </option>");
									rs1=mp.BuscarServ();
									while(rs1.next()){
										out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(2)+"'>"+rs1.getString(1)+"</option>");
									}rs1.getStatement().getConnection().close();
									out.print("</select></td>");
									out.print("<td width='5%'><div id='subarea' ></div> </td><td width='8%'>");
									out.print("<select id='Quir' style='width:100%' style='font-size:9px;' ><option value='Seleccione'>Seleccione</option>");
							
									rs1=mp.BuscarArea();
									try {
									
										while(rs1.next()){
											out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
										}
										rs1.getStatement().getConnection().close();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									out.println("</select></td><td width='2%'><input type='button' value='+' onclick='Agregar1("+codigo+","+usuario+","+tp+")' /></td></tr>");
									out.println("</table>");
							  }else{
								  if(tp.equals("3")){
									  //oncologia 
										 out.print("<table border='1' align='center' ><tr><td colspan='15'><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR  DE ONCOLOGIA DEL "+fv8+" </div></td></tr>");
										 out.print("<tr  align='center' bgcolor='#D7D7DB'><td width='8%'><br>HORA</br></td><td width='10%'><br>DOCUMENTO</br></td><td width='10%'><br>NOMBRE DEL PACIENTE </br></td><td width='10%'><br>ENTIDAD</br></td><td width='5%'><br>TELEFONO</br></td><td width='5%'><br>TERAPIA INTRATECAL</td><td width='8%'><br>TERAPIA SISTEMATICA</td><td width='12%'><br>SERVICIO</br></td><td width='5%'><br>HAB</td><td width='4%'><br>EDAD</td><td width='7%'><br>DIAGNOSTICO</br></td><td width='3%'><br>OPOR. CITA </br></td><td width='3%'><br>SE</br></td><td width='10%'>OBSERVACIONES</td><td width='2%'></td></tr>");
										 out.print("<tr><td width='8%'><input type='text' id='hora' size='1' onKeyup='checkhora()' /><select id='rhora' style='width:40%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td>");
										 out.print("<td width='10%'><select id='tdoc' style='width:40%' >");
										 rs3=mp.BuscarTipoDoc();
										 while(rs3.next()){
											 out.print("<option value='"+rs3.getString(2)+"' title='"+rs3.getString(1)+"' >"+rs3.getString(2)+"</option>");
										 }
										 rs3.getStatement().getConnection().close();
										 out.print("</select><input type='text' id='numdoc' style='width:50%'></td>");
										 out.print("<td width='10%'><input type='text' id='npac' size='20' /></td>");
										 out.print("<td width='10%'><select id='eps' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
											rs1=mp.BuscarEps();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
											}
											rs1.getStatement().getConnection().close();
										
											out.print("</select></td>");
										 out.print("<td width='5%'><input type='text'id='tel' style='width:90%'/></td>");
										 out.print("<td width='5%'><input type='text' id='intra' style='width:90%' /></td><td width='8%'><input type='text' id='sistema' style='width:90%' /></td>");
										 out.print("<td width='12%'><select name='serv' id='serv' style='width:100%' style='font-size:9px;' onChange='tipoarea()'><option value='seleccione' > Seleccione </option><option value='DE CASA' >DE CASA </option>");
											rs1=mp.BuscarServ();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(2)+"'>"+rs1.getString(1)+"</option>");
											}rs1.getStatement().getConnection().close();
											out.print("</select></td>");
											out.print("<td width='5%'><div id='subarea' ></div></td>");
											out.print("<td width='4%'><input type='text' id='edad' style='width:90%' /></td><td width='7%'><input type='text' id='diag' style='width:90%' /></td><td width='3%'><select id='ocita' style='width:90%' style='font-size:9px;' ><option value='NO'>NO</option><option value='SI'>SI</option></select></td><td width='3%'><select id='se' style='width:100%' style='font-size:9px;' ><option value='NO'>NO</option><option value='SI'>SI</option></select></td><td width='10%'><input type='text' id='obs' style='width:90%' /></td><td width='2%'><input type='button' value='+' onclick='Agregar1("+codigo+","+usuario+","+tp+")' /></td></tr>");
										 out.print("</table>");
									  
									  
								  }else{
									  if((tp.equals("4"))||(tp.equals("5"))){
										  //hemodinamia y endoscopia 
										  out.print("<table border='1' align='center' ><tr><td colspan='11'><div align='center' class='style11' id='cabecera2'>PROGRAMACION PRELIMINAR DE ");
										  if(tp.equals("4")){
											  out.print("DE HEMODINAMIA"+fv8+" </div></td></tr>");
										  }else{
											  out.print("DE ENDOSCOPIA"+fv8+" </div></td></tr>");
										  }
											out.print("<tr align='center' bgcolor='#D7D7DB'><td width='10%'><br>HORA</td><td width='10%'><br>NOMBRE DEL PACIENTE</td><td width='4%'><br>TELEFONO</td><td width='9%'><br>PROCEDIMIENTO</td><td width='12%'><br>EPS</td><td width='11%'><br>CIRUJANO</td><td width='11%'><br>ANESTESIOLOGO</td><td width='13%'><br>SERVICIO</td><td width='5%'><br>HAB</td><td width='5%'><br>OBSERVACION</td><td width='8%'><br>AREA</td><td width='2%'></td></tr>");
											out.print("<tr><td width='10%'><input type='text' id='hora' size='1' onKeyup='checkhora()' /><select id='rhora' style='width:50%'><option value='AM'>AM</option><option value='PM'>PM</option></select></td><td width='10%'><input type='text' id='npac' size='23' /></td><td width='4%'><input type='text'id='tel' size='6'/></td><td width='9%'><input type='text' id='proc' size='20' /></td>");
											out.print("<td width='12%'><select id='eps' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
											rs1=mp.BuscarEps();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
											}
											rs1.getStatement().getConnection().close();
										
											out.print("</select></td>");
											out.print("<td width='11%' ><select id='ciru' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option>");
											
											rs1=mp.BuscarCiru(tp);
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(1)+"' >"+rs1.getString(1)+" </option>");
											}
											rs1.getStatement().getConnection().close();
											out.print("</select></td>");
											out.print("<td width='11%'><select id='anes' style='width:100%' style='font-size:9px;' ><option value='seleccione' >Seleccione</option><option value='EN TURNO' >EN TURNO</option><option value='VICTOR PEREZ'>VICTOR PEREZ</option>");
											rs1=mp.BuscarAnes();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' >"+rs1.getString(1)+"</option>");
											}rs1.getStatement().getConnection().close();
											out.print("</select></td>");
											out.print("<td width='13%'><select name='serv' id='serv' style='width:100%' style='font-size:9px;' onChange='tipoarea()'><option value='seleccione' > Seleccione </option><option value='DE CASA' >DE CASA </option>");
											rs1=mp.BuscarServ();
											while(rs1.next()){
												out.print("<option value='"+rs1.getString(1)+"' title='"+rs1.getString(2)+"'>"+rs1.getString(1)+"</option>");
											}rs1.getStatement().getConnection().close();
											out.print("</select></td>");
											out.print("<td width='5%'><div id='subarea' ></div> </td>");
											out.print("<td width='5%'><input type='text' id='obs' size='10' /></td>");
											out.print("<td width='8%'><select id='Quir' style='width:100%' style='font-size:9px;' ><option value='Seleccione'>Seleccione</option>");
									
											rs1=mp.BuscarArea();                                                                                                                            
											try {
											
												while(rs1.next()){
													out.println("<option value='"+rs1.getString(1)+"'>"+rs1.getString(2)+"</option>");
												}
												rs1.getStatement().getConnection().close();
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											out.println("</select></td><td width='2%'><input type='button' value='+' onclick='Agregar1("+codigo+","+usuario+","+tp+")' /></td></tr>");
											out.println("</table>");
										  
										  
									  }
								  }
							  }
							
						}
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//	System.out.println("valor del estado en formp  "+estado);
			
		}

		
		if(va.equals("VistaSubArea")){
			String Serv=req.getParameter("serv");
			String codServ=req.getParameter("codserv");
			//System.out.println(Serv);
			if(Serv.equals("DE CASA")){
				out.print("<table><tr><td><select id='hab' ><option value='NA'>NA</option></select></td></tr></table>");
			}else{
				out.print("<table><tr><td><select id='hab'  ><option value='NA'>NA</option>");
				rs=mp.BuscarHab(codServ);
				try {
					while(rs.next()){
						out.print("<option value='"+rs.getString(1)+"' >"+rs.getString(1)+"</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("</select></td></tr></table>");
			}
			
			
		}
		
		
		if(va.equals("GuardarDetP1")){
			
			String tp=req.getParameter("tp");
			if(tp.equals("1")){
			String horap=req.getParameter("hora");
			String rhora=req.getParameter("rhora");
			String npac=req.getParameter("npac");
			String proc=req.getParameter("proc");
			String eps=req.getParameter("eps");
			String ciru=req.getParameter("ciru");
			String ayud=req.getParameter("ayud");
			String anes=req.getParameter("anes");
			String codigo=req.getParameter("codigo");
			String usuario=req.getParameter("usuario");
			String tel=req.getParameter("tel");
			String qui=req.getParameter("qui");
			String serv=req.getParameter("serv");
			String hab=req.getParameter("hab");
			mp.GuardarDetalleProgramacion(codigo,qui,horap,npac,tel,proc,eps,ciru,ayud,anes,serv,hab,usuario,rhora);
			rs=mp.BuscarDetProg(codigo);
			out.println("<table border='1'><tr><td colspan='12' ><br></td></tr><tr ><td colspan='12' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr align='center' class='style6' ><td width='8%' ><i> AREA </i></td><td width='7%' ><i>HORA</i></td><td width='11%' ><i>NOMBRE DEL PACIENTE</i></td><td width='4%'><i> TELEFONO</i></td><td width='12%' ><i>PROCEDIMIENTO </i></td><td width='12%' ><i>ENTIDAD</i>&nbsp;</td><td width='11%' ><i> CIRUJANO </i></td><td width='5%' ><i>AYUDANTE</i></td><td width='11%' ><i>ANESTESIOLOGO</i></td>" +
			"<td width='12%' ><i>SERVICIO</i></td><td width='5%' ><i>HABITACION </i></td><td width='2%' ><i> ELIMINAR</i></td></tr>");
			try {
				while(rs.next()){
					out.println("<tr><td><font color='black'>"+rs.getString(28)+"</font></td><td><font color='black' > "+rs.getString(4)+" "+rs.getString(26)+"</font></td><td align='center'><font color='black' >"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(10)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
				}
				rs.getStatement().getConnection().close();
				out.print("</table>");
				out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr>");
				out.print("</table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else{
				if(tp.equals("2")){
					String horap=req.getParameter("hora");
					String rhora=req.getParameter("rhora");
					String npac=req.getParameter("npac");
					String proc=req.getParameter("proc");
					String eps=req.getParameter("eps");
					String ciru=req.getParameter("ciru");
					String ayud=req.getParameter("ayud");
					String anes=req.getParameter("anes");
					String codigo=req.getParameter("codigo");
					String usuario=req.getParameter("usuario");
					String tel="";
					String qui="1";
					String serv="";
					String hab="";
					mp.GuardarDetalleProgramacion(codigo,qui,horap,npac,tel,proc,eps,ciru,ayud,anes,serv,hab,usuario,rhora);
					rs=mp.BuscarDetProg(codigo);
					out.println("<table border='1' class='style6'><tr><td colspan='8' ><br></td></tr><tr ><td colspan='8' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr><td width='12%'><i> HORA </i></td><td width='15%'><i>NOMBRE DEL PACIENTE</i></td><td width='15%'><i> PROCEDIMIENTO </i></td><td width='15%'><i>ENTIDAD </i></td><td><i>CIRUJANO</i></td><td width='13%'><i>AYUDANTE</i></td><td width='13%'><i>ANESTESIOLOGO</i></td>" +
					"<td width='2%'><i>ELIMINAR</i></td></tr>");
					try {
						while(rs.next()){
							out.println("<tr><td><font color='black'>"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td><font color='black'>"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(10)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table>");
						out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr>");
						out.print("</table>");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					if(tp.equals("3")){
						String hora=req.getParameter("hora");
						String rhora=req.getParameter("rhora");
						String npac=req.getParameter("npac");
						String codigo=req.getParameter("codigo");
						String usuario=req.getParameter("usuario");
						String hab=req.getParameter("hab");
						String serv=req.getParameter("serv");
						String tel=req.getParameter("tel");
						String eps=req.getParameter("eps");
						String intra=req.getParameter("intra");
						String sistema=req.getParameter("sistema");
						String tdoc=req.getParameter("tdoc");
						String numdoc=req.getParameter("numdoc");
						String diag=req.getParameter("diag");
						String edad=req.getParameter("edad");
						String ocita=req.getParameter("ocita");
						String se=req.getParameter("se");
						String obs=req.getParameter("obs");
						System.out.println("mp.GuardarDetalleProgOnco"+hora+","+rhora+","+npac+","+codigo+","+usuario+","+hab+","+serv+","+tel+","+eps+","+intra+","+sistema+","+tdoc+","+numdoc+","+diag+","+edad+","+ocita+","+se+","+obs);
						mp.GuardarDetalleProgOnco(hora,rhora,npac,codigo,usuario,hab,serv,tel,eps,intra,sistema,tdoc,numdoc,diag,edad,ocita,se,obs);
						rs=mp.BuscarDetProgOnco(codigo);
						out.print("<table border='1' align='center' class='style6' ><tr><td colspan='15'></td></tr><tr ><td colspan='15' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr>");
						 out.print("<tr  align='center' bgcolor='#D7D7DB'><td width='8%'><br>HORA</br></td><td width='10%'><br>DOCUMENTO</br></td><td width='10%'><br>NOMBRE DEL PACIENTE </br></td><td width='10%'><br>ENTIDAD</br></td><td width='5%'><br>TELEFONO</br></td><td width='5%'><br>TERAPIA INTRATECAL</td><td width='8%'><br>TERAPIA SISTEMATICA</td><td width='12%'><br>SERVICIO</br></td><td width='5%'><br>HAB</td><td width='4%'><br>EDAD</td><td width='7%'><br>DIAGNOSTICO</br></td><td width='3%'><br>OPOR. CITA </br></td><td width='3%'><br>SE</br></td><td width='10%'>OBSERVACIONES</td><td width='2%'>ELIMINAR</td></tr>");
						 try {
							while(rs.next()){
								 out.println("<tr><td><font color='black'>"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td><font color='black'>"+rs.getString(16)+"-"+rs.getString(17)+" </font></td><td><font color='black'>"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(24)+"</font></td><td><font color='black'>"+rs.getString(25)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><font color='black'>"+rs.getString(18)+"</font></td><td><font color='black'>"+rs.getString(20)+"</font></td><td><font color='black'>"+rs.getString(21)+"</font></td><td><font color='black'>"+rs.getString(27)+"</font></td><td><font color='black'>"+rs.getString(19)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
							 }
							rs.getStatement().getConnection().close();
							out.print("</table>");
							out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr>");
							out.print("</table>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						if((tp.equals("4"))||(tp.equals("5"))){
							
							String horap=req.getParameter("hora");
							String rhora=req.getParameter("rhora");
							String npac=req.getParameter("npac");
							String proc=req.getParameter("proc");
							String eps=req.getParameter("eps");
							String ciru=req.getParameter("ciru");
							String obs=req.getParameter("obs");
							String anes=req.getParameter("anes");
							String codigo=req.getParameter("codigo");
							String usuario=req.getParameter("usuario");
							String tel=req.getParameter("tel");
							String qui=req.getParameter("qui");
							String serv=req.getParameter("serv");
							String hab=req.getParameter("hab");
							mp.GuardarDetalleProgHemoEndo(codigo,qui,horap,npac,tel,proc,eps,ciru,obs,anes,serv,hab,usuario,rhora);
							rs=mp.BuscarDetProg(codigo);
							out.println("<table border='1'><tr><td colspan='12' ><br></td></tr><tr ><td colspan='12' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr align='center' class='style6' ><td width='8%' ><i> AREA </i></td><td width='7%' ><i>HORA</i></td><td width='11%' ><i>NOMBRE DEL PACIENTE</i></td><td width='4%'><i> TELEFONO</i></td><td width='12%' ><i>PROCEDIMIENTO </i></td><td width='12%' ><i>ENTIDAD</i>&nbsp;</td><td width='11%' ><i> CIRUJANO </i></td><td width='5%' ><i>OBSERVACION</i></td><td width='11%' ><i>ANESTESIOLOGO</i></td>" +
							"<td width='12%' ><i>SERVICIO</i></td><td width='5%' ><i>HABITACION </i></td><td width='2%' ><i> ELIMINAR</i></td></tr>");
							
								try {
									while(rs.next()){
										out.println("<tr><td><font color='black'>"+rs.getString(28)+"</font></td><td><font color='black' > "+rs.getString(4)+" "+rs.getString(26)+"</font></td><td align='center'><font color='black' >"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(19)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
									}rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								out.print("</table>");
								out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr>");
								out.print("</table>");
							
						}
					}
				}
			}
		
			
			}
			
			
		if(va.equals("GenerarRep")){
			String Idia=req.getParameter("Idia");
			String Imes=req.getParameter("Imes");
			String Iano=req.getParameter("Iano");
			String Fano=req.getParameter("Fano");
			String Fmes=req.getParameter("Fmes");
			String Fdia=req.getParameter("Fdia");
			String tp=req.getParameter("tp");
			String Fechai=Iano+"-"+Imes+"-"+Idia;
			String Fechaf=Fano+"-"+Fmes+"-"+Fdia;
			rs=mp.BuscarProg(Fechai,Fechaf,tp);
			out.println("<table class='style6' border='1' width='100%'><tr align='center' bgcolor='#D7D7DB'><td width='25%'><i> <br> FECHA DE PROGRAMACION </br></i></td><td width='30%'><i> <br> CREADO POR   </br></i></td><td width='15%'>&nbsp;<i> <br> FECHA DE CREACION </br></i>&nbsp;</td><td width='15%'>&nbsp;<i> <br> HORA DE CREACION </br></i>&nbsp;</td>" +
			"<td width='15%'>&nbsp;<i> <br> Ver </br></i>&nbsp;</td></tr></table>");
			out.print("<table width='100%'>");
			if(tp.equals("1")){
			try {
					while(rs.next()){
						out.println("<tr><td align='center' width='25%'>"+rs.getString(4)+"&nbsp;</td><td align='center' width='30%'>&nbsp;"+rs.getString(5)+" "+rs.getString(6)+ "</td><td width='15%' align='center' >"+rs.getString(2)+"</td><td width='15%' align='center'>"+rs.getString(3)+"</td><td width='20%' align='center'><input type='radio'  name='radiobutton'  onclick='verrep1("+rs.getString(1)+")' /></td></tr>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				if(tp.equals("2")){
					rs=mp.BuscarProg(Fechai,Fechaf,tp);
					try {
					while(rs.next()){
						out.println("<tr align='center' width='25%'><td>"+rs.getString(4)+"&nbsp;</td><td align='center'  width='30%'>"+rs.getString(5)+" "+rs.getString(6)+ "</td><td width='15%' align='center'>"+rs.getString(2)+"</td><td width='15%' align='center'>"+rs.getString(3)+"</td><td width='20%' align='center'><input type='radio'  name='radiobutton'  onclick='verrep2("+rs.getString(1)+")' /></td></tr>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					if(tp.equals("3")){
						rs=mp.BuscarProgOnco(Fechai,Fechaf,tp);
						try {
						while(rs.next()){
							out.println("<tr align='center' width='25%'><td>"+rs.getString(4)+"&nbsp;</td><td align='center'  width='30%'>"+rs.getString(5)+" "+rs.getString(6)+ "</td><td width='15%' align='center'>"+rs.getString(2)+"</td><td width='15%' align='center'>"+rs.getString(3)+"</td><td width='20%' align='center' ><input type='radio'  name='radiobutton'  onclick='verrep3("+rs.getString(1)+")' /></td></tr>");
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					}else{
						if((tp.equals("4"))||(tp.equals("5"))){
							rs=mp.BuscarProg(Fechai,Fechaf,tp);
							try {
							while(rs.next()){
								out.println("<tr align='center' width='25%'><td>"+rs.getString(4)+"&nbsp;</td><td align='center'  width='30%'>"+rs.getString(5)+" "+rs.getString(6)+ "</td><td width='15%' align='center'>"+rs.getString(2)+"</td><td width='15%' align='center'>"+rs.getString(3)+"</td><td width='20%' align='center'><input type='radio'  name='radiobutton'  onclick='verrep45("+rs.getString(1)+")' /></td></tr>");
							}
							rs.getStatement().getConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
							
						}
					}
				}
		
			}
		}

			
		
		
		if(va.equals("FinalP")){
			String codigou=req.getParameter("codigou");
			String codigo=req.getParameter("codigo");
			java.util.Date fechaActual = new java.util.Date();
			java.sql.Date Fecha = new java.sql.Date(fechaActual.getTime());		
			java.sql.Time Hora = new java.sql.Time(fechaActual.getTime());
			mp.FinalP(Fecha,Hora,codigou,codigo);
			out.println("Programacion Guardada Exitosamente");
		}
		
		if(va.equals("Elim")){
			String coddet=req.getParameter("coddet");
			String codigo=req.getParameter("codigo");
			String tp=req.getParameter("tp");
			if(coddet!=""){
			mp.Elim(coddet);
			}
			out.println("Eliminacion Exitosa");
		}
		
		if(va.equals("VerDatos")){
			String codigo=req.getParameter("codigo");
			String tp=req.getParameter("tp");
			String usuario=req.getParameter("usuario");
			if(tp.equals("1")){
				rs=mp.BuscarDetProg(codigo);
				System.out.println("entrando a codigo "+codigo);
				out.println("<table border='1'><tr><td colspan='12' ><br></td></tr><tr ><td colspan='12' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr align='center' class='style6' ><td width='8%' ><i> AREA </i></td><td width='7%' ><i>HORA</i></td><td width='11%' ><i>NOMBRE DEL PACIENTE</i></td><td width='4%'><i> TELEFONO</i></td><td width='12%' ><i>PROCEDIMIENTO </i></td><td width='12%' ><i>ENTIDAD</i>&nbsp;</td><td width='11%' ><i> CIRUJANO </i></td><td width='5%' ><i>AYUDANTE</i></td><td width='11%' ><i>ANESTESIOLOGO</i></td>" +
				"<td width='12%' ><i>SERVICIO</i></td><td width='5%' ><i>HABITACION </i></td><td width='2%'  ><i> ELIMINAR</i></td></tr>");
				try {
					
					while(rs.next()){
						System.out.println("entrando "+rs.getString(4));
						out.println("<tr><td><font color='black'>"+rs.getString(28)+"</font></td><td><font color='black' >"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td align='center'><font color='black' >"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(10)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("</table>");
				out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr>");
				out.println("</table>");
			}else{
				if(tp.equals("2")){
					rs=mp.BuscarDetProg(codigo);
					out.println("<table border='1' class='style6'><tr><td colspan='8' ><br></td></tr><tr ><td colspan='8' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr><td width='12%'><i> HORA </i></td><td width='15%'><i>NOMBRE DEL PACIENTE</i></td><td width='15%'><i> PROCEDIMIENTO </i></td><td width='15%'><i>ENTIDAD </i></td><td><i>CIRUJANO</i></td><td width='13%'><i>AYUDANTE</i></td><td width='13%'><i>ANESTESIOLOGO</i></td>" +
					"<td width='2%'><i>ELIMINAR</i></td></tr>");
					try {
						while(rs.next()){
							out.println("<tr><td><font color='black'>"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td><font color='black'>"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(10)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table>");
						out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr></table>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					if(tp.equals("3")){
						rs=mp.BuscarDetProgOnco(codigo);
						out.print("<table border='1' align='center' class='style6' ><tr><td colspan='15'></td></tr><tr ><td colspan='15' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION ONCOLOGIA</div></td></tr>");
						 out.print("<tr  align='center' bgcolor='#D7D7DB'><td width='8%'><br>HORA</br></td><td width='10%'><br>DOCUMENTO</br></td><td width='10%'><br>NOMBRE DEL PACIENTE </br></td><td width='10%'><br>ENTIDAD</br></td><td width='5%'><br>TELEFONO</br></td><td width='5%'><br>TERAPIA INTRATECAL</td><td width='8%'><br>TERAPIA SISTEMATICA</td><td width='12%'><br>SERVICIO</br></td><td width='5%'><br>HAB</td><td width='4%'><br>EDAD</td><td width='7%'><br>DIAGNOSTICO</br></td><td width='3%'><br>OPOR. CITA </br></td><td width='3%'><br>SE</br></td><td width='10%'>OBSERVACIONES</td><td width='2%'>ELIMINAR</td></tr>");
						 try {
							while(rs.next()){
								 out.println("<tr><td><font color='black'>"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td><font color='black'>"+rs.getString(16)+"-"+rs.getString(17)+" </font></td><td><font color='black'>"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(24)+"</font></td><td><font color='black'>"+rs.getString(25)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><font color='black'>"+rs.getString(18)+"</font></td><td><font color='black'>"+rs.getString(20)+"</font></td><td><font color='black'>"+rs.getString(21)+"</font></td><td><font color='black'>"+rs.getString(27)+"</font></td><td><font color='black'>"+rs.getString(19)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
							 }
							rs.getStatement().getConnection().close();
							out.print("</table>");
							out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr>");
							out.print("</table>");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else{
						if((tp.equals("4"))||(tp.equals("5"))){
							rs=mp.BuscarDetProg(codigo);
							out.println("<table border='1'><tr><td colspan='12' ><br></td></tr><tr ><td colspan='12' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr align='center' class='style6' ><td width='8%' ><i> AREA </i></td><td width='7%' ><i>HORA</i></td><td width='11%' ><i>NOMBRE DEL PACIENTE</i></td><td width='4%'><i> TELEFONO</i></td><td width='12%' ><i>PROCEDIMIENTO </i></td><td width='12%' ><i>ENTIDAD</i>&nbsp;</td><td width='11%' ><i> CIRUJANO </i></td><td width='5%' ><i>OBSERVACION</i></td><td width='11%' ><i>ANESTESIOLOGO</i></td>" +
							"<td width='12%' ><i>SERVICIO</i></td><td width='5%' ><i>HABITACION </i></td><td width='2%' ><i> ELIMINAR</i></td></tr>");
							
								try {
									while(rs.next()){
										out.println("<tr><td><font color='black'>"+rs.getString(28)+"</font></td><td><font color='black' > "+rs.getString(4)+" "+rs.getString(26)+"</font></td><td align='center'><font color='black' >"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(19)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+usuario+")' /></td></tr>");
									}rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								out.print("</table>");
								out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+usuario+","+codigo+","+tp+")'</td></tr>");
								out.print("</table>");
						}
					}
				}
		}
		}
		
		if(va.equals("VerDetP")){
			String fechap=req.getParameter("fechap");
			String tp=req.getParameter("tp");
			String codigou=req.getParameter("codigou");
			String dv8=fechap.substring(0, 2);
			String mv8=fechap.substring(3, 5);
			String av8=fechap.substring(6, 10);
			fv8=av8+"-"+mv8+"-"+dv8;
			rs=mp.BuscarEstadoProgramacion(tp,fv8);
			String estado="";
			String codigo="";
			try {
				while(rs.next()){
					estado=rs.getString(1);
					codigo=rs.getString(2);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("estado en verdetp es "+estado);
			System.out.println("Valor de tp "+tp);
			int i=0;
			if((estado.equals("0"))&&(tp.equals("1"))){ // cuando hay una programacion no finalizada y es de cirugia general
				rs=mp.BuscarDetProg(codigo);
				System.out.println("codigo de la programacion es  "+codigo);
				out.println("<table border='1'><tr><td colspan='12' ><br></td></tr><tr ><td colspan='12' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr align='center' class='style6' ><td width='8%' ><i> AREA </i></td><td width='7%' ><i>HORA</i></td><td width='11%' ><i>NOMBRE DEL PACIENTE</i></td><td width='4%'><i> TELEFONO</i></td><td width='12%' ><i>PROCEDIMIENTO </i></td><td width='12%' ><i>ENTIDAD</i>&nbsp;</td><td width='11%' ><i> CIRUJANO </i></td><td width='5%' ><i>AYUDANTE</i></td><td width='11%' ><i>ANESTESIOLOGO</i></td>" +
				"<td width='12%' ><i>SERVICIO</i></td><td width='5%' ><i>HABITACION </i></td><td width='2%'  ><i> ELIMINAR</i></td></tr>");
				//out.print("<tr><td colspan='12'><hr></td><tr>");
				//out.print("<table>");
				try {
					
					while(rs.next()){
						System.out.println("entrando "+rs.getString(4));
						i=1;
						out.println("<tr><td><font color='black'>"+rs.getString(28)+"</font></td><td><font color='black' >"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td ><font color='black' >"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td ><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(10)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+codigou+")' /></td></tr>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("</table>");
				if(i!=0){
				out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+codigou+","+codigo+","+tp+")'</td></tr></table>");
				}
				
			}else{
				if((estado.equals("0"))&&(tp.equals("2"))){
					rs=mp.BuscarDetProg(codigo);
					out.println("<table border='1' class='style6'><tr><td colspan='8' ><br></td></tr><tr ><td colspan='8' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr><td width='12%'><i> HORA </i></td><td width='15%'><i>NOMBRE DEL PACIENTE</i></td><td width='15%'><i> PROCEDIMIENTO </i></td><td width='15%'><i>ENTIDAD </i></td><td><i>CIRUJANO</i></td><td width='13%'><i>AYUDANTE</i></td><td width='13%'><i>ANESTESIOLOGO</i></td>" +
					"<td width='2%'><i>ELIMINAR</i></td></tr>");
					try {
						while(rs.next()){
							i=1;
							out.println("<tr><td><font color='black'>"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td><font color='black'>"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(10)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+codigou+")' /></td></tr>");
						}
						rs.getStatement().getConnection().close();
						out.print("</table>");
						if(i!=0){
						out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+codigou+","+codigo+","+tp+")'</td></tr>");
						}out.println("</table>");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				}else{
					if((estado.equals("0"))&&(tp.equals("3"))){
						rs=mp.BuscarDetProgOnco(codigo);
						out.print("<table border='1' align='center' class='style6' ><tr><td colspan='15'></td></tr><tr ><td colspan='15' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION ONCOLOGIA</div></td></tr>");
						 out.print("<tr  align='center' bgcolor='#D7D7DB'><td width='8%'><br>HORA</br></td><td width='10%'><br>DOCUMENTO</br></td><td width='10%'><br>NOMBRE DEL PACIENTE </br></td><td width='10%'><br>ENTIDAD</br></td><td width='5%'><br>TELEFONO</br></td><td width='5%'><br>TERAPIA INTRATECAL</td><td width='8%'><br>TERAPIA SISTEMATICA</td><td width='12%'><br>SERVICIO</br></td><td width='5%'><br>HAB</td><td width='4%'><br>EDAD</td><td width='7%'><br>DIAGNOSTICO</br></td><td width='3%'><br>OPOR. CITA </br></td><td width='3%'><br>SE</br></td><td width='10%'>OBSERVACIONES</td><td width='2%'>ELIMINAR</td></tr>");
						 try {
							while(rs.next()){
								i=1;
								 out.println("<tr><td><font color='black'>"+rs.getString(4)+" "+rs.getString(26)+"</font></td><td><font color='black'>"+rs.getString(16)+"-"+rs.getString(17)+" </font></td><td><font color='black'>"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(24)+"</font></td><td><font color='black'>"+rs.getString(25)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><font color='black'>"+rs.getString(18)+"</font></td><td><font color='black'>"+rs.getString(20)+"</font></td><td><font color='black'>"+rs.getString(21)+"</font></td><td><font color='black'>"+rs.getString(27)+"</font></td><td><font color='black'>"+rs.getString(19)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+codigou+")' /></td></tr>");
							 }
							rs.getStatement().getConnection().close();
							out.print("</table>");
							if(i!=0){
							out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+codigou+","+codigo+","+tp+")'</td></tr>");
							out.print("</table>");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						if((estado.equals("0"))&&((tp.equals("4"))||(tp.equals("5")))){
							
							rs=mp.BuscarDetProg(codigo);
							out.println("<table border='1'><tr><td colspan='12' ><br></td></tr><tr ><td colspan='12' ><div align='center' class='style11' id='cabecera2'>DETALLE PROGRAMACION </div></td></tr><tr align='center' class='style6' ><td width='8%' ><i> AREA </i></td><td width='7%' ><i>HORA</i></td><td width='11%' ><i>NOMBRE DEL PACIENTE</i></td><td width='4%'><i> TELEFONO</i></td><td width='12%' ><i>PROCEDIMIENTO </i></td><td width='12%' ><i>ENTIDAD</i>&nbsp;</td><td width='11%' ><i> CIRUJANO </i></td><td width='5%' ><i>OBSERVACION</i></td><td width='11%' ><i>ANESTESIOLOGO</i></td>" +
							"<td width='12%' ><i>SERVICIO</i></td><td width='5%' ><i>HABITACION </i></td><td width='2%' ><i> ELIMINAR</i></td></tr>");
							
								try {
									while(rs.next()){
										out.println("<tr><td><font color='black'>"+rs.getString(28)+"</font></td><td><font color='black' > "+rs.getString(4)+" "+rs.getString(26)+"</font></td><td align='center'><font color='black' >"+rs.getString(5)+"</font></td><td><font color='black'>"+rs.getString(6)+"</font></td><td><font color='black'>"+rs.getString(7)+"</font></td><td><font color='black'>"+rs.getString(8)+"</font></td><td><font color='black'>"+rs.getString(9)+"</font></td><td><font color='black'>"+rs.getString(19)+"</font></td><td><font color='black'>"+rs.getString(11)+"</font></td><td><font color='black'>"+rs.getString(12)+"</font></td><td><font color='black'>"+rs.getString(13)+"</font></td><td><input type='radio'  name='radiobutton'  onclick='Eliminar("+rs.getString(1)+","+codigo+","+tp+","+codigou+")' /></td></tr>");
									}rs.getStatement().getConnection().close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								out.print("</table>");
								out.print("<table><tr><td colspan='5'><br><input type='button' value=' Finalizar Programacion ' onclick='Finalizar("+codigou+","+codigo+","+tp+")'</td></tr>");
								out.print("</table>");
							
						}
						
					}
				}
			}
			
		}
	}

}
