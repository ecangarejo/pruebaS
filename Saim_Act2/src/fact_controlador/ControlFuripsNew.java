package fact_controlador;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;

import com.lowagie.tools.plugins.Split;

import fact_metodo.MetodoFurips;

public class ControlFuripsNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlFuripsNew() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String va = req.getParameter("valor");
		String ruta = req.getParameter("ruta");
		String fi = req.getParameter("fi");
		String ff = req.getParameter("ff");
		String datos = req.getParameter("datos");
		
		String CodEnt=req.getParameter("CodEnt");
		String ctaf=req.getParameter("ctaf");
		String ctah=req.getParameter("ctah");
		String ctam=req.getParameter("ctam");
		String ctap=req.getParameter("ctap");
		String ctat=req.getParameter("ctat");
		String ctau=req.getParameter("ctau");
		String ctrn=req.getParameter("ctrn");
		String ctus=req.getParameter("ctus");

		PrintWriter out = res.getWriter();
		MetodoFurips mr = new MetodoFurips();
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		java.util.Date fechaS = new Date();
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m = Integer.parseInt(mes) + 1;
		mes = String.valueOf(m);
		int d = Integer.parseInt(dia);
		if (d < 10) {
			dia = "0" + d;
		}
		if (m < 10) {
			mes = "0" + m;
		}

		String fechacjmysql = annio + "-" + mes + "-" + dia;
		String fechacj = dia + "/" + mes + "/" + annio;

		Calendar calendario = Calendar.getInstance();

		int h, minutos, segundos;
		h = calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND);
		String hra = h + ":" + minutos + ":" + segundos;

		if (va.equals("2")) {
			out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Generar Archivos FURIPS</div></td></tr>");
			out.print("<tr><td >Entidad:</td><td ><label><select name='cmbE' id='cmbE' style='width:380px'><option value='Seleccione'>Seleccione</option>");
			out.print("<option title='FOSYGA' value=451>FOSYGA</option>");
			out.print("</select></label></td>");
			out.print("</td><td>Tipo Rips</td><td><select id='cmbTipoRips' onchange='CambTipoRips()'><option value='Seleccione'>Seleccione</option><option value='BusGen'>Busqueda General</option></select></td></tr></table>");
			out.print("<table width='100%' border='0' ><tr><td id='RipsCam'></td></tr></table>");
			out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Facturas</div></td></tr>");
			out.print("<table width='100%' class='style6' border='0'><tr><td>Facturas Disponibles</td><td whidth='10%'></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='>>' onclick='MoverS()' /></td><td>Facturas Seleccionadas</td><td></td></tr>");
			out.print("<tr><td colspan='2'><label><div id='DFD'><select name='FD' multiple  id='FD' style='width:380px; height: 400px'></div></label></td><div align='right'><td><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='<<' onclick='MoverD()' /></td></div><td colspan='2'><label><div id='DFS'><select name='FS' multiple id='FS' style='width:380px; height: 400px'></div></label></td></tr>");
			out.print("<table width='100%' class='style6'  border='0'><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td width='5%'><div align='center'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Exportar FURIPS' onclick='Exportar()' /></div></td></tr></table>");
		}
		

		if (va.equals("3")) {
			String eps = req.getParameter("eps");

			String a1 = fi.substring(0, 2);
			String m1 = fi.substring(3, 5);
			String d1 = fi.substring(6, 10);
			String fin = d1 + "-" + m1 + "-" + a1;

			String a2 = ff.substring(0, 2);
			String m2 = ff.substring(3, 5);
			String d2 = ff.substring(6, 10);
			String ffn = d2 + "-" + m2 + "-" + a2;
			
			out.print("<select name='FD' multiple  id='FD' style='width:380px;  height: 400px'>");
			rs = mr.Facturas(eps, fin, ffn);
			try {
				while (rs.next()) {
					rs2=mr.VerFurips(eps,rs.getString(3));
					if(rs2.next()){
						out.print("<option value='" + rs.getString(1) + "'>"+ rs.getString(2) + "</option>");
					}else{
						out.print("<option style='color: #FF0000' value='" + rs.getString(1) + "'>"+ rs.getString(2) + "</option>");
					}
					
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error " + e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		

		if (va.equals("C3")) {
			String NumCtaCobro = req.getParameter("NumCtaCobro");
			String NumCTA = NumCtaCobro.substring(6, NumCtaCobro.length());
			
			out.print("<select name='FD' multiple  id='FD' style='width:380px;  height: 400px'>");
			rs = mr.FacturasNumCta(NumCTA);
			try {
				while (rs.next()) {
					out.print("<option value='" + rs.getString(1) + "'>"+ rs.getString(2) + "</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error " + e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		

		if (va.equals("5")) {
			String sexo="";
			String condicion="";
			String naturaleza="";
			String aseguramiento="";
			String vehiculo="";
			String autoridad="";
			String poliza="";
			String servicio="";
			String zona="";
			//String AR = req.getParameter("AR");
			//String nom = req.getParameter("nom");
			//System.out.println("nom: " + nom + "AR: " + AR);

			/*int cah = 0;
			int cam = 0;
			int can = 0;
			int cap = 0;
			int cat = 0;
			int cau = 0;
			int cus = 0;
			int cac = 0;
			int caf = 0;*/

			// Numero de facturas y las facturas
			String V[] = new String[2000];
			int i1 = 0;
			StringTokenizer elementos;
			System.out.println("datos: " + datos);
			elementos = new StringTokenizer(datos, "|");

			System.out.println("elementos: " + elementos);
			while (elementos.hasMoreTokens()) {
				V[i1] = elementos.nextToken();
				System.out.println("V["+i1+"]: " + V[i1]);
				i1++;
			}
			
		//captura ruta del usuario del servidor
		String contenido = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\Saim";
			
		//se crea la carpeta
			File carpeta = new File(contenido + "\\FURIPS"); 
			carpeta.mkdir();
			System.out.println("carpeta: " + carpeta);

			rs = mr.Prestador();
			String prestador = "";
			System.out.println("Conseguimos el codigo del prestador: ");
			
			try {
				while (rs.next()) {
					prestador = rs.getString(1);
					System.out.println("prestador: " + prestador);
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error " + e);
				e.printStackTrace();
			}

			String Tdig = null;

			/*** Archivo de Consultas FUNCIONA ***/

		

			
				//se crea el archivo AC
				FileWriter fw = new FileWriter(carpeta + "\\FURIPS1" + prestador + dia + mes +  annio+ ".txt");
				//FileWriter fw = new FileWriter(contenido + "/AC" + nom + ".txt");
				BufferedWriter bw = new BufferedWriter(fw);

				System.out.println("fw " + fw);
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					System.out.println("llegamos al ac v[i] " + V[i]);

					rs2 = mr.F1(V[i],CodEnt);
					try {
						while(rs2.next()){
							System.out.println("factura "+i+" : "+V[i]);
							bw.write(""+rs2.getString(3)+",0,"+ V[i] + ","+ 1 + ","+prestador + ",");
							rs=mr.pac(rs2.getString(6), rs2.getString(7));
							if(rs.next()){
								if(rs.getString(8).equals("Masculino")){
									sexo="M";
								}else {
									sexo="F";
								}
								
							String[] fecnac=rs.getString(7).split("-");
							String[] nom=rs.getString(3).split(" ");
							String dir = rs.getString(9).replace('#','N');
								bw.write(rs.getString(4).toUpperCase()+","+rs.getString(5).toUpperCase()+","+nom[0].toUpperCase()+","+nom[1].toUpperCase()+","+rs.getString(1).toUpperCase()+","+rs.getString(2)+","+
										fecnac[2]+"/"+fecnac[1]+"/"+fecnac[0]+","+sexo+","+dir.toUpperCase()+","+rs.getString(12)+","+rs.getString(13)+","+
										rs.getString(10)+",");
							}
							rs.getStatement().getConnection().close();
							
							if(rs2.getString(9).equals("Conductor")){
								 condicion="1";
							}else{
								if(rs2.getString(9).equals("Peaton")){
								 condicion="2";
								}else{
									if(rs2.getString(9).equals("Ocupante")){
										condicion="3";
									}else{
										if(rs2.getString(9).equals("Ciclista")){
											condicion="4";
										}
									}	
								}
							}
							
							if(rs2.getString(10).equals("Accidente de transito")){
								  naturaleza="01";
							}else{
								if(rs2.getString(10).equals("Sismo")){
								  naturaleza="02";
								}else{
									if(rs2.getString(10).equals("Maremoto")){
										 naturaleza="03";
									}else{
										if(rs2.getString(10).equals("Erupciones Volcanicas")){
											 naturaleza="04";
										}else{
											if(rs2.getString(10).equals("Deslizamientos de Tierra")){
												naturaleza="05";
											}else{
												if(rs2.getString(10).equals("Inundaciones")){
													naturaleza="06";
												}else{
													if(rs2.getString(10).equals("Avalancha")){
														naturaleza="07";
													}else{
														if(rs2.getString(10).equals("Incendio Natural")){
															naturaleza="08";
														}else{
															if(rs2.getString(10).equals("Explosion Terrorista")){
																naturaleza="09";
															}else{
																if(rs2.getString(10).equals("Incendio Terrorista")){
																	naturaleza="10";
																}else{
																	if(rs2.getString(10).equals("Combate")){
																		naturaleza="11";
																	}else{
																		if(rs2.getString(10).equals("Ataques a Municipios")){
																			naturaleza="12";
																		}else{
																			if(rs2.getString(10).equals("Masacre")){
																				naturaleza="13";
																			}else{
																				if(rs2.getString(10).equals("Desplazados")){
																					naturaleza="14";
																				}else{
																					if(rs2.getString(10).equals("Mina Antipersonal")){
																						naturaleza="15";
																					}else{
																						if(rs2.getString(10).equals("Huracan")){
																							naturaleza="16";
																						}else{
																							if(rs2.getString(10).equals("Otro")){
																								naturaleza="17";
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}	
								}
							}
							String dirocu=rs2.getString(11).replace('#','N');
							String fecocu=rs2.getString(12).replace('-','/');
							
							
							if(rs2.getString(45).equals("Asegurado")){
								  aseguramiento="1";
							}else{
								if(rs2.getString(45).equals("No Asegurado")){
								  aseguramiento="2";
								}else{
									if(rs2.getString(45).equals("Vehiculo Fantasma")){
										 aseguramiento="3";
									}else{
										if(rs2.getString(45).equals("Poliza Falsa")){
											 aseguramiento="4";
										}else{
											if(rs2.getString(45).equals("Vehiculo Fuga")){
												 aseguramiento="5";
											}
										}
									}	
								}
							}
							
							if(rs2.getString(48).equals("Particular")){
								  vehiculo="3";
							}else{
								if(rs2.getString(48).equals("Publico")){
									vehiculo="4";
								}else{
									if(rs2.getString(48).equals("Oficial")){
										vehiculo="5";
									}else{
										if(rs2.getString(48).equals("Vehiculo Emergencia")){
											vehiculo="6";
										}else{
											if(rs2.getString(48).equals("Vehiculo Diplomatico")){
												vehiculo="7";
											}else{
												if(rs2.getString(48).equals("Vehiculo Transporte Masivo")){
													vehiculo="8";
												}else{
													if(rs2.getString(48).equals("Vehiculo Escolar")){
														vehiculo="9";
													}
												}
											}
										}
									}	
								}
							}
							
							if(rs2.getString(51).equals("No")){
								 autoridad="0";
							}else{ 
								 autoridad="1";
							}
							if(rs2.getString(54).equals("No")){
								 poliza="0";
							}else{ 
								 poliza="1";
							}
							if(rs2.getString(38).equals("Ambulancia Basica")){
								  servicio="1";
							}else{ 
								  servicio="2";
							}
							if(rs2.getString(18).equals("Urbana")){
								   zona="U";
							}else{ 
								   zona="R";
							}
							String r=rs2.getString(21);
							if(rs2.getString(21).equals("Remision")){
								 r="1";
							}else{ 
								if(rs2.getString(21).equals("Orden de Servicio")){
									 r="2";
								}
								  
							}
							String desc = rs2.getString(19).replace(',',' ');
							bw.write(condicion+","+naturaleza+",,"+dirocu.toUpperCase()+","+fecocu+","+rs2.getString(13)+","+
									rs2.getString(15)+","+rs2.getString(17)+","+zona+","+desc.toUpperCase()+","+aseguramiento+","+
									rs2.getString(46).toUpperCase()+","+rs2.getString(47).toUpperCase()+","+vehiculo+","+rs2.getString(49)+","+rs2.getString(50)+","+
									rs2.getString(52)+","+rs2.getString(53)+","+autoridad+","+poliza+",,,,,,,"+rs2.getString(58)+","+
									rs2.getString(55)+","+rs2.getString(60).toUpperCase()+","+rs2.getString(61).toUpperCase()+","+rs2.getString(62).toUpperCase()+","+rs2.getString(63).toUpperCase()+","+
									rs2.getString(64).toUpperCase()+","+rs2.getString(69)+","+rs2.getString(66)+","+rs2.getString(68)+","+rs2.getString(73).toUpperCase()+","+
									rs2.getString(74).toUpperCase()+","+rs2.getString(75).toUpperCase()+","+rs2.getString(76).toUpperCase()+","+rs2.getString(71)+","+rs2.getString(56)+","+
									rs2.getString(77).toUpperCase()+","+rs2.getString(79)+","+rs2.getString(81)+","+rs2.getString(82)+","+r+","+
									rs2.getString(22)+","+rs2.getString(23)+","+rs2.getString(25)+","+rs2.getString(26).toUpperCase()+","+rs2.getString(27).toUpperCase()+","+
									rs2.getString(28)+","+rs2.getString(29)+","+rs2.getString(31)+","+rs2.getString(32).toUpperCase()+","+rs2.getString(33).toUpperCase()+","+
									rs2.getString(35).toUpperCase()+","+rs2.getString(36).toUpperCase()+","+rs2.getString(37).toUpperCase()+","+rs2.getString(38)+","+rs2.getString(39)+","+
									rs2.getString(85)+","+rs2.getString(86)+","+rs2.getString(87)+","+rs2.getString(88)+","+rs2.getString(89)+","+
									rs2.getString(90)+","+rs2.getString(91)+","+rs2.getString(92)+","+rs2.getString(93)+","+rs2.getString(94)+","+
									rs2.getString(105).toUpperCase()+","+rs2.getString(106).toUpperCase()+","+rs2.getString(107).toUpperCase()+","+rs2.getString(108).toUpperCase()+","+rs2.getString(102)+","+
									rs2.getString(40)+","+rs2.getString(104)+","+rs2.getString(132)+","+rs2.getString(132)+",0,0,1");
							bw.newLine();
							System.out.println("end "+i+" : "+V[i]);
						}
						
						rs2.getStatement().getConnection().close();
				//	System.out.println("AC dio  " + cac);
					} catch (SQLException e) {
						out.print("Error CESARJULIO " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
			//	out.print(cac);
				//se cierra la conexion de la escritura del archivo
				bw.close();
				fw.close();

			
				
				
				//se crea el archivo AC
				FileWriter fw2 = new FileWriter(carpeta + "\\FURIPS2" + prestador + dia + mes +  annio+ ".txt");
				//FileWriter fw = new FileWriter(contenido + "/AC" + nom + ".txt");
				BufferedWriter bw2 = new BufferedWriter(fw2);

				System.out.println("fw " + fw2);
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					System.out.println("llegamos al ac v[i] " + V[i]);

					rs2 = mr.F2(V[i]);
					try {
						while(rs2.next()){
							System.out.println("factura F2"+i+" : "+V[i]);
					
							bw2.write(rs2.getString("consecutivo")+","+rs2.getString("Reclamacion")+","+rs2.getString("cod_programa")+","+
									rs2.getString("cantidad")+","+rs2.getString("ValorUnitario")+","+rs2.getString("ValorFactura")+","+rs2.getString("ValorFactura"));
							bw2.newLine();
							System.out.println("end f2"+i+" : "+V[i]);
						}
						
						rs2.getStatement().getConnection().close();
				//	System.out.println("AC dio  " + cac);
					} catch (SQLException e) {
						out.print("Error CESARJULIO " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
			//	out.print(cac);
				//se cierra la conexion de la escritura del archivo
				bw2.close();
				fw2.close();
				
				
				
				
				
				
		}
		
		
		/* crear carpeta comprimida*/
	/*	if (va.equals("6")) {
			System.out.println("zip");
			String nom=req.getParameter("nom");
	
			//captura la ruta del usuario del servidor
			//String RUTA = System.getProperty("user.home");
			String RUTA = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\Saim";
			
			//se crea carpeta con extension .zip
			String zipFile = RUTA+"\\RIPS.zip";
			System.out.println("ruta: "+zipFile);     
			 try {
				// create byte buffer
				byte[] buffer = new byte[2048];
				FileOutputStream fos = new FileOutputStream(zipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);

				if (ctac.equals("0")) {
					System.out.println("CTAC" + ctac);
				} else {
					System.out.println("CTAC" + ctac);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename =  RUTA + "\\RIPS\\AC" + nom + ".txt";
					System.out.println("CTAC: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra el archivo de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				if (ctaf.equals("0")) {
					System.out.println("ctaf" + ctaf);
				} else {
					System.out.println("ctaf" + ctaf);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename =  RUTA + "\\RIPS\\AF" + nom + ".txt";
					System.out.println("CTAF: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra el archivo de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				if (ctah.equals("0")) {
					System.out.println("ctah" + ctah);
				} else {
					System.out.println("ctah" + ctah);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename =  RUTA + "\\RIPS\\AH" + nom + ".txt";
					System.out.println("CTAh: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra el archivo de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
					
				}

				if (ctam.equals("0")) {
					System.out.println("ctam" + ctam);
				} else {
					System.out.println("ctam" + ctam);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename = RUTA + "\\RIPS\\AM" + nom + ".txt";
					System.out.println("CTAM: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra el archivo de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				if (ctap.equals("0")) {
					System.out.println("ctap" + ctap);
				} else {
					System.out.println("ctap" + ctap);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename = RUTA + "\\RIPS\\AP" + nom + ".txt";
					System.out.println("CTAP: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra archivo de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				if (ctat.equals("0")) {
					System.out.println("ctat" + ctat);
				} else {
					System.out.println("ctat" + ctat);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename = RUTA + "\\RIPS\\AT" + nom + ".txt";
					System.out.println("CTAT: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra el archivo de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				if (ctau.equals("0")) {
					System.out.println("ctau" + ctau);
				} else {
					System.out.println("ctau" + ctau);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename = RUTA + "\\RIPS\\AU" + nom + ".txt";
					System.out.println("CTAU: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra archivo de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				if (ctrn.equals("0")) {
					System.out.println("ctrn" + ctrn);
				} else {
					System.out.println("ctrn" + ctrn);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename = RUTA + "\\RIPS\\RN" + nom + ".txt";
					System.out.println("CTRN: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra registro de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				if (ctus.equals("0")) {
					System.out.println("ctus" + ctus);
				} else {
					System.out.println("ctus" + ctus);
					//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
					String srcFilename = RUTA + "\\RIPS\\US" + nom + ".txt";
					System.out.println("CTUS: "+srcFilename); 
					File srcFile = new File(srcFilename);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to
					// the start of the entry data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
					
					//se borra registros de la carpeta RIPS
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}

				
				//ruta donde se encuentra el archivo para agregarlo a la carpeta RIPS.zip
				String srcFilename = RUTA + "\\RIPS\\CT" + nom + ".txt";
				System.out.println("CTCT: "+srcFilename); 
				System.out.println("CT");
				File srcFile = new File(srcFilename);
				FileInputStream fis = new FileInputStream(srcFile);
				// begin writing a new ZIP entry, positions the stream to the
				// start of the entry data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));

				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}
				zos.closeEntry();
				// close the InputStream
				fis.close();
				out.print("CT");
				
				//se borra archivo de la carpeta RIPS
				if(srcFile.exists()){ 
		            if(srcFile.delete()) 
		                System.out.println("Borrado con exito :)"); 
		            else 
		                System.out.println("Se ocaciono un error al borrar el directorio :("); 
		        }else{ 
		            System.out.println("El directorio no existe :("); 
		        } 
				
				
				// close the ZipOutputStream
				zos.close();

				
				//se borra la carpeta RIPS
				/*File f = new File(RUTA+"/RIPS"); 
				 System.out.println("f"+f); 
		         
		        if(f.exists()){ 
		            if(f.delete()) 
		                System.out.println("Borrado con exito :)"); 
		            else 
		                System.out.println("Se ocaciono un error al borrar el directorio :("); 
		        }else{ 
		            System.out.println("El directorio no existe :("); 
		        } */
				
		/*	} catch (IOException ioe) {
				System.out.println("Error creating zip file" + ioe);
			}
		}*/
		
		
/**************************RIPS AGRUPADOS*******************************/
		
		if(va.equals("1A")){			
			out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Generar Archivos RIPS de facturas Agrupadas</div></td></tr>");
			out.print("<tr><td width='10%'>Entidad:</td><td width='40%'><label><select name='cmbE' id='cmbE' style='width:380px'><option value='Seleccione'>Seleccione</option>");
				rs=mr.Empresas();
				try {
					while(rs.next()){
					out.print("<option title='"+rs.getString(1)+"' value="+rs.getString(2)+">"+rs.getString(1)+"</option>");
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				out.print("</select></label></td>");
				out.print("<table width='100%' class='style6'  border='0'><tr><td width='7%'>Fecha Inicial:</td><td width='15%'><label><input name='FI' type='text' id='FI' size='10'  onKeyup='masca(this,patron,true,0,0,0)' /></label></td><td width='7%'>Fecha Final:</td><td width='15%' ><label><input name='FF' type='text' id='FF' size='10' onKeyup='masca(this,patron,true,0,0,0)'  onBlur='BuscarFA()' /></label></td><td width='5%'>Nombre:</td><td width='15%'><label><input name='txtNombre' type='text' id='txtNombre' size='10'  maxlength=6 /></label></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Consultar' onclick='BuscarFA()' /></td></tr></table>");
				out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Facturas</div></td></tr>");
				out.print("<table width='100%' class='style6' border='0'><tr><td>Facturas Disponibles</td><td whidth='10%'></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='>>' onclick='MoverS()' /></td><td>Facturas Seleccionadas</td><td></td></tr>");
				out.print("<tr><td colspan='2'><label><div id='DFD'><select name='FD' multiple  id='FD' style='width:380px; height: 400px'></div></label></td><div align='right'><td><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='<<' onclick='MoverD()' /></td></div><td colspan='2'><label><div id='DFS'><select name='FS' multiple id='FS' style='width:380px; height: 400px'></div></label></td></tr>");
				out.print("<table width='100%' class='style6'  border='0'><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td width='5%'><div align='center'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Exportar RIPS' onclick='ExportarA()' /></div></td></tr></table>");
		}
		
		
		if(va.equals("3A")){	
			String eps=req.getParameter(" eps");
			
			String a1=fi.substring(0, 2);
			String m1=fi.substring(3, 5);
			String d1=fi.substring(6, 10);
			String fin=d1+"-"+m1+"-"+a1;
						
			String a2=ff.substring(0, 2);
			String m2=ff.substring(3, 5);
			String d2=ff.substring(6, 10);
			String ffn=d2+"-"+m2+"-"+a2;
			
			
			out.print("<select name='FD' multiple  id='FD' style='width:380px;  height: 400px'>");
		rs=mr.FacturasA(eps,fin,ffn);
		try {
			while(rs.next()){
		out.print("<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>");
			}
			rs.getStatement().getConnection().close();
		} catch (SQLException e) {
			out.print("Error "+e);
			e.printStackTrace();
		}
		out.print("</select>");
		}
		
		
		if(va.equals("5A")){	
			String ARA=req.getParameter("ARA");
			String nom=req.getParameter("nom");
		    
			 int cac=0;	 	
		  	 
			//Numero de facturas y las facturas
			String V[] = new String[200];
			int i1=0;
			StringTokenizer elementos;  
			elementos = new StringTokenizer(datos,"|"); 
			 
			while(elementos.hasMoreTokens()){ 
				   V[i1] = elementos.nextToken();  
				   System.out.println("V[i1]: "+V[i1]);
				   i1++;
			 }
					   
				
			//captura ruta del usuario del servidor
			String contenido = System.getProperty("user.home"); 
			
			//se crea la carpeta
			File carpeta = new File(contenido + "/RIPS"); 
			carpeta.mkdir();
			System.out.println("carpeta: " + carpeta);
			
			 try
			 {
			 	 rs=mr.Prestador();
			 	String prestador="";
				try {
					while(rs.next()){
						prestador=rs.getString(1);
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					out.print("Error "+e);
					e.printStackTrace();
				}
				
				String Tdig=null;
		
					
				/*** Archivo de Consultas FUNCIONA ***/	
				if(ARA.equals("1")){	
					FileWriter fw = new FileWriter(carpeta + "/AC" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					//rs2=mr.DAC(V[i]);
					if(rs2.next()){
						Tdig=rs2.getString(1);
						rs=mr.AC1(fi,ff,V[i],Tdig);
				  try {
						 while(rs.next()){
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							
							 bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							bw.newLine();
							 cac++;
						}
						rs.getStatement().getConnection().close();
					 } catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					 }
					
					}else{
					
					rs=mr.AC(fi,ff,V[i]);
				  try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							 bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							 bw.newLine();
							 cac++;
						}
						rs.getStatement().getConnection().close();
					 } catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					 }
					}	
				}
				//se imprime la cantidad de registros encontrados
				out.print(cac);
				//se cierra la conexion de la escritura del archivo
				bw.close();
				fw.close();
				}//FIN ARA=1
				
			 }catch(Exception e){
				 e.printStackTrace();
			 }		
	}//FIN VA =5A
		
	}
}