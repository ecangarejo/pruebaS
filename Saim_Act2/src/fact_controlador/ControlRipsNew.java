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

import fact_metodo.MetodoRips;

public class ControlRipsNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlRipsNew() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String va = req.getParameter("valor");
		String fi = req.getParameter("fi");
		String ff = req.getParameter("ff");
		String datos = req.getParameter("datos");
		
		String ctac=req.getParameter("ctac");
		String ctaf=req.getParameter("ctaf");
		String ctah=req.getParameter("ctah");
		String ctam=req.getParameter("ctam");
		String ctap=req.getParameter("ctap");
		String ctat=req.getParameter("ctat");
		String ctau=req.getParameter("ctau");
		String ctrn=req.getParameter("ctrn");
		String ctus=req.getParameter("ctus");
		
		String contenido=req.getParameter("rut");

		PrintWriter out = res.getWriter();
		MetodoRips mr = new MetodoRips();
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
			out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Generar Archivos RIPS</div></td></tr>");
			out.print("<tr><td >Entidad:</td><td ><label><select name='cmbE' id='cmbE' style='width:380px'><option value='Seleccione'>Seleccione</option>");
			rs = mr.Empresas();
			try {
				while (rs.next()) {
					out.print("<option title='" + rs.getString(1) + "' value="+ rs.getString(2) + ">" + rs.getString(1)+ "</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error " + e);
				e.printStackTrace();
			}
			out.print("</select></label></td>");
			out.print("</td><td>Tipo Rips</td><td><select id='cmbTipoRips' onchange='CambTipoRips()'><option value='Seleccione'>Seleccione</option><option value='CuentaC'>Cuenta de Cobro</option><option value='BusGen'>Busqueda General</option></select></td></tr></table>");
			out.print("<table width='100%' border='0' ><tr><td id='RipsCam'></td></tr></table>");
			out.print("<table width='100%' class='style6'  border='0'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Facturas</div></td></tr>");
			out.print("<table width='100%' class='style6' border='0'><tr><td>Facturas Disponibles</td><td whidth='10%'></td><td width='5%'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='>>' onclick='MoverS()' /></td><td>Facturas Seleccionadas</td><td></td></tr>");
			out.print("<tr><td colspan='2'><label><div id='DFD'><select name='FD' multiple  id='FD' style='width:380px; height: 400px'></div></label></td><div align='right'><td><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='<<' onclick='MoverD()' /></td></div><td colspan='2'><label><div id='DFS'><select name='FS' multiple id='FS' style='width:380px; height: 400px'></div></label></td></tr>");
			out.print("<table width='100%' class='style6'  border='0'><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr></tr><tr><td width='5%'><div align='center'><input name='btnCrearConvenio' type='button' id='btnCrearConvenio' value='Exportar RIPS' onclick='Exportar()' /></div></td></tr></table>");
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
					out.print("<option value='" + rs.getString(1) + "'>"+ rs.getString(2) + "</option>");
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error " + e);
				e.printStackTrace();
			}
			out.print("</select>");
		}
		
		if (va.equals("3.1")) {
			String a1 = fi.substring(0, 2);
			String m1 = fi.substring(3, 5);
			String d1 = fi.substring(6, 10);
			String fin = d1 + "-" + m1 + "-" + a1;

			String a2 = ff.substring(0, 2);
			String m2 = ff.substring(3, 5);
			String d2 = ff.substring(6, 10);
			String ffn = d2 + "-" + m2 + "-" + a2;
			
			out.print("<select name='FD' multiple  id='FD' style='width:380px;  height: 400px'>");
			rs = mr.TodasFacturas(fin, ffn);
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
			String AR = req.getParameter("AR");
			String nom = req.getParameter("nom");
			System.out.println("nom: " + nom + "AR: " + AR);

			int cah = 0;
			int cam = 0;
			int can = 0;
			int cap = 0;
			int cat = 0;
			int cau = 0;
			int cus = 0;
			int cac = 0;
			int caf = 0;

			// Numero de facturas y las facturas
			String V[] = new String[20000];
			int i1 = 0;
			StringTokenizer elementos;
			System.out.println("datos: " + datos);
			elementos = new StringTokenizer(datos, "|");

			System.out.println("elementos: " + elementos);
			while (elementos.hasMoreTokens()) {
				V[i1] = elementos.nextToken();
				System.out.println("V[i1]: " + V[i1]);
				i1++;
			}
			
					
		    //se crea la carpeta
			File carpeta = new File(contenido + "\\"+nom); 
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

			System.out.println("AARARARRARARARAARARAR " + AR);

			if (AR.equals("1")) {
				//se crea el archivo AC
				FileWriter fw = new FileWriter(carpeta + "\\AC" + nom + ".txt");
				BufferedWriter bw = new BufferedWriter(fw);

				System.out.println("llegamos al ac i1 " + i1);
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					System.out.println("llegamos al ac v[i] " + V[i]);

					rs2 = mr.DAC(V[i]);
					try {

						if (rs2.next()) {
							Tdig = rs2.getString(1);
							System.out.println("llegamos al ac" + V[i]+ " no se q es esto " + Tdig);

							rs = mr.AC1(fi, ff, V[i], Tdig);
							System.out.println("Se entra al metodo ACl ");

							while (rs.next()) {
								String ce = rs.getString(9);
								String ce2 = rs.getString(8);
								String cen = "0";
								if (ce.length() < 2) {
									ce = cen + ce;
								}
								if (ce2.length() < 2) {
									ce2 = cen + ce2;
								}

								if (rs.getInt(18) > 1) {
									int vari = 0;
									do {
										//se adiciona registro al archivo AC 
										bw.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + "," + ce2+ "," + ce + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(15)+ "\r\n");
										bw.flush();
								       											
										vari = vari + 1;
									} while (vari < rs.getInt(18));
								} else {
									//se adiciona registro al archivo AC 
									bw.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + "," + ce2 + ","+ ce + "," + rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(17)+ "\r\n");
									bw.flush();
								}

								System.out.println("Esto trajo la consulta AC1 "+ rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + "," + ce2+ "," + ce + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(17)+ "\r\n");
								cac++;
							}
							rs.getStatement().getConnection().close();

							System.out.println("AC1 dio  " + cac);
						} else {

							rs = mr.AC(fi, ff, V[i]);
							System.out.println("Se entra al metodo AC solo ");

							while (rs.next()) {
								String ce = rs.getString(9);
								String ce2 = rs.getString(8);
								String cen = "0";
								if (ce.length() < 2) {
									ce = cen + ce;
								}
								if (ce2.length() < 2) {
									ce2 = cen + ce2;
								}

								if (rs.getInt(18) > 1) {
									int vari = 0;
									do {
										//se adiciona registro al archivo AC
										bw.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + "," + ce2+ "," + ce + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(15)+ "\r\n");
										bw.flush();
																				
										vari = vari + 1;
									} while (vari < rs.getInt(18));
								} else {
									//se adiciona registro al archivo AC
									bw.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + "," + ce2 + ","+ ce + "," + rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(17)+ "\r\n");
									bw.flush();
								}

								System.out.println("Esto trajo la consulta AC "+ rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + "," + ce2 + ","+ ce + "," + rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(17));
								cac++;
							}
							rs.getStatement().getConnection().close();
						}
						System.out.println("AC dio  " + cac);
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error " + e);
						e.printStackTrace();
					}
				}
				
				//se imprime la cantidad de registros encontrados
				out.print(cac);
				//se cierra la conexion de la escritura del archivo
				bw.close();
				fw.close();
		}

			/*** Archivo de Descripcion Agrupada ***/

			/*** Archivo de Transacciones ***/
			if (AR.equals("3")) {
				//se crea el archivo AF	
				FileWriter fw3 = new FileWriter(carpeta + "\\AF" + nom + ".txt");
				BufferedWriter bw3 = new BufferedWriter(fw3);
				System.out.println("ENTRAMOS A TRANSACCIONES AR= " + AR);
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					rs = mr.AF(fi, ff, V[i]);
					try {
						while (rs.next()) {
							//se adiciona registro al archivo AF
							bw3.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","+ rs.getString(8) + ","+ rs.getString(9) + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(17)+ "\r\n");
							bw3.flush();
														
							System.out.println("Esto trajo la consulta Transacciones "+ rs.getString(1)+ ","+ rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)+ ","+ rs.getString(5)+ ","+ rs.getString(6)+ ","+ rs.getString(7)	+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ rs.getString(11)+ ","+ rs.getString(12)+ ","+ rs.getString(13)+ ","+ rs.getString(14)+ ","+ rs.getString(15)+ ","+ rs.getString(16)+ ","+ rs.getString(17));
							caf++;
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(caf);
				//se cierra la conexion de la escritura del archivo
				bw3.close();
				fw3.close();
			}

			
			/*** Archivo de Medicamentos ***/
			if (AR.equals("4")) {
				//se crea el archivo AM
				FileWriter fw4 = new FileWriter(carpeta + "\\AM" + nom + ".txt");
				BufferedWriter bw4 = new BufferedWriter(fw4);
				System.out.println("ENTRAMOS A MEDICAMENTOS AR= " + AR);

				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					rs = mr.AM(fi, ff, V[i]);
					try {
						while (rs.next()) {
							//se adiciona registro al archivo AM y una linea nueva
							bw4.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","+ rs.getString(8) + ","+ rs.getString(9) + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14)+ "\r\n");
							bw4.flush();
														
							System.out.println("Esto trajo la consulta Transacciones "+ rs.getString(1)+ ","+ rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)+ ","+ rs.getString(5)+ ","+ rs.getString(6)+ ","+ rs.getString(7)+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ rs.getString(11)+ ","+ rs.getString(12)+ ","+ rs.getString(13)+ ","+ rs.getString(14));
							cam++;
						}
						rs.getStatement().getConnection().close();
					
					} catch (SQLException e) {
						out.print("Error " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(cam);
				//se cierra la conexion de la escritura del archivo
				bw4.close();
				fw4.close();
			}

			
			/*** Archivo de Hospitalizacion ***/
			if (AR.equals("5")) {
				//se crea el archivo AH
				FileWriter fw5 = new FileWriter(carpeta + "\\AH" + nom + ".txt");
				BufferedWriter bw5 = new BufferedWriter(fw5);
				System.out.println("ENTRAMOS A HOSPITALIZACION AR= " + AR);

				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					rs = mr.AH(fi, ff, V[i]);
					try {
						while (rs.next()) {
							String[] diagEgreso = rs.getString(11).split("_");
							String de1 = "", de2 = "", de3 = "", de4 = "";
							if (diagEgreso.length == 1) {
								de1 = diagEgreso[0];
							} else {
								if (diagEgreso.length == 2) {
									de1 = diagEgreso[0];
									de2 = diagEgreso[1];
								} else {
									if (diagEgreso.length == 3) {
										de1 = diagEgreso[0];
										de2 = diagEgreso[1];
										de3 = diagEgreso[2];
									} else {
										if (diagEgreso.length == 4) {
											de1 = diagEgreso[0];
											de2 = diagEgreso[1];
											de3 = diagEgreso[2];
											de4 = diagEgreso[3];
										}
									}
								}
							}
							//se adiciona registro al archivo AH
							bw5.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","+ rs.getString(8) + ","+ rs.getString(9) + ","+ rs.getString(10) + "," + de1+ "," + de2 + "," + de3 + "," + de4+ "," + rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16)+ "\r\n");
							bw5.flush();
							
							System.out.println("Esto trajo la consulta HOSPITAIZAAS "+ rs.getString(1)+ ","+ rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)+ ","+ rs.getString(5)+ ","+ rs.getString(6)+ ","+ rs.getString(7)+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ de1+ ","+ de2+ ","+ de3+ ","+ de4+ ","+ rs.getString(12)+ ","+ rs.getString(13)+ ","+ rs.getString(14)+ ","+ rs.getString(15)+ ","+ rs.getString(16));
							cah++;
						}
						rs.getStatement().getConnection().close();
						
					} catch (SQLException e) {
						out.print("Error " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(cah);
				//se cierra la conexion de la escritura del archivo
				bw5.close();
				fw5.close();
			}

			
			/*** Archivo de Recien Nacidos ***/
			if (AR.equals("6")) {
				System.out.println("ENTRAMOS A RECIEN NACIDOS AR= " + AR);
				//se crea el archivo RN
				FileWriter fw6 = new FileWriter(carpeta + "\\AN" + nom + ".txt");
				BufferedWriter bw6 = new BufferedWriter(fw6);
				
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					rs = mr.AN(fi, ff, V[i]);
					try {
						while (rs.next()) {
							//se adiciona registro al archivo RN
							bw6.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","+ rs.getString(8) + ","+ rs.getString(9) + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14)+ "\r\n");
							bw6.flush();
												
							System.out.println("Esto trajo la consulta HOSPITAIZAAS "+ rs.getString(1)+ ","+ rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)+ ","+ rs.getString(5)+ ","+ rs.getString(6)+ ","+ rs.getString(7)+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ rs.getString(11)+ ","+ rs.getString(12)+ ","+ rs.getString(13)+ ","+ rs.getString(14));
							can++;
						}
						rs.getStatement().getConnection().close();
						
					} catch (SQLException e) {
						out.print("Error " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(can);
				//se cierra la conexion de la escritura del archivo
				bw6.close();
				fw6.close();
			}

			
			/*** Archivo de Procedimientos ***/
			if (AR.equals("7")) {
				System.out.println("ENTRAMOS A PROCEDIMIENTIOS AR= " + AR);
				//se crea el archivo AP
				FileWriter fw7 = new FileWriter(carpeta + "\\AP" + nom + ".txt");
				BufferedWriter bw7 = new BufferedWriter(fw7);
				
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					rs2 = mr.DAP(V[i]);
					System.out.println("ENTRAMOS DAP AR= " + AR);

					try {
						if (rs2.next()) {
							Tdig = rs2.getString(1);
							rs = mr.AP(fi, ff, V[i], Tdig);
							try {
								while (rs.next()) {
									//se adiciona registro al archivo AP
									bw7.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","+ rs.getString(8) + ","+ rs.getString(9) + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15)+ "\r\n");
									bw7.flush();
																		
									System.out.println("Esto trajo la consulta AP1 "+ rs.getString(1)+ ","+ rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)+ ","+ rs.getString(5)+ ","+ rs.getString(6)+ ","+ rs.getString(7)+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ rs.getString(11)+ ","+ rs.getString(12)+ ","+ rs.getString(13)+ ","+ rs.getString(14)+ ","+ rs.getString(15));				
									cap++;
								}
								rs.getStatement().getConnection().close();

								rs1 = mr.AP2(fi, ff, V[i]);
								while (rs1.next()) {
									//se adiciona registro al archivo AP
									bw7.write(rs1.getString(1) + ","+ rs1.getString(2) + ","+ rs1.getString(3) + ","+ rs1.getString(4) + ","+ rs1.getString(5) + ","+ rs1.getString(6) + ","+ rs1.getString(7) + ","+ rs1.getString(8) + ","+ rs1.getString(9) + ","+ rs1.getString(10) + ","+ rs1.getString(11) + ","+ rs1.getString(12) + ","+ rs1.getString(13) + ","+ rs1.getString(14) + ","+ rs1.getString(15)+ "\r\n");
									bw7.flush();
																		
									System.out.println("Esto trajo la consulta AP2 "+ rs1.getString(1)+ ","+ rs1.getString(2)+ ","+ rs1.getString(3)+ ","+ rs1.getString(4)+ ","+ rs1.getString(5)+ ","+ rs1.getString(6)+ ","+ rs1.getString(7)+ ","+ rs1.getString(8)+ ","+ rs1.getString(9)+ ","+ rs1.getString(10)+ ","+ rs1.getString(11)+ ","+ rs1.getString(12)+ ","+ rs1.getString(13)+ ","+ rs1.getString(14)+ ","+ rs1.getString(15));
									cap++;
								}
								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error " + e);
								e.printStackTrace();
							}
						} else {
							rs = mr.AP1(fi, ff, V[i]);
							System.out.println("ENTRAMOS A AP1 AR= " + AR);

							try {
								while (rs.next()) {
									//se adiciona registro al archivo AP y una linea nueva
									bw7.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","+ rs.getString(8) + ","+ rs.getString(9) + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15)+ "\r\n");
									bw7.flush();
																		
									System.out.println("Esto trajo la consulta AP1 "+ rs.getString(1)+ ","+rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)+ ","+ rs.getString(5)+ ","+ rs.getString(6)+ ","+ rs.getString(7)+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ rs.getString(11)+ ","+ rs.getString(12)+","+ rs.getString(13)+ ","+ rs.getString(14)+ ","+ rs.getString(15));
									cap++;
								}
								rs.getStatement().getConnection().close();

								rs1 = mr.AP2(fi, ff, V[i]);
								while (rs1.next()) {
									//se adiciona registro al archivo AP y una linea nueva
									bw7.write(rs1.getString(1) + ","+ rs1.getString(2) + ","+ rs1.getString(3) + ","+ rs1.getString(4) + ","+ rs1.getString(5) + ","+ rs1.getString(6) + ","+ rs1.getString(7) + ","+ rs1.getString(8) + ","+ rs1.getString(9) + ","+ rs1.getString(10) + ","+ rs1.getString(11) + ","+ rs1.getString(12) + ","+ rs1.getString(13) + ","+ rs1.getString(14) + ","+ rs1.getString(15)+ "\r\n");
									bw7.flush();
																		
									System.out.println("Esto trajo la consulta AP2 "+ rs1.getString(1)+ ","+ rs1.getString(2)+ ","+ rs1.getString(3)+ ","+ rs1.getString(4)+ ","+ rs1.getString(5)+ ","+ rs1.getString(6)+ ","+ rs1.getString(7)+ ","+ rs1.getString(8)+ ","+ rs1.getString(9)+ ","+ rs1.getString(10)+ ","+ rs1.getString(11)+ ","+ rs1.getString(12)+ ","+ rs1.getString(13)+ ","+ rs1.getString(14)+ ","	+ rs1.getString(15));									
									cap++;
								}

								rs1.getStatement().getConnection().close();
							} catch (SQLException e) {
								out.print("Error " + e);
								e.printStackTrace();
							}
						}
						rs2.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(cap);
				//se cierra la conexion de la escritura del archivo
				bw7.close();
				fw7.close();
			}

			
			/*** Archivo de Otros Servicios ***/
			if (AR.equals("8")) {
				System.out.println("ENTRAMOS A AT OTROS SERV AR= " + AR);
				//se crea el archivo AT
				FileWriter fw8 = new FileWriter(carpeta + "\\AT" + nom + ".txt");
				BufferedWriter bw8 = new BufferedWriter(fw8);
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					rs = mr.AT(fi, ff, V[i]);
					try {
						while (rs.next()) {
							System.out.println("AT");
							//se adiciona registro al archivo AT 
							bw8.write(rs.getString(1) + "," + rs.getString(2)+ "," + rs.getString(3) + ","+ rs.getString(4) + "," + rs.getString(5)+ ",1," + rs.getString(7) + ","+ rs.getString(8) + "," + rs.getString(9)+ "," + rs.getString(10) + ","+ rs.getString(11)+ "\r\n");
							bw8.flush();
														
							cat++;
							System.out.println("Esto trajo la consulta OTROS SERVICIOS "+ rs.getString(1)+ ","+ rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)	+ ","+ rs.getString(5)+ ",1,"+ rs.getString(7)+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ rs.getString(11));
						}
						rs.getStatement().getConnection().close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(cat);
				//se cierra la conexion de la escritura del archivo
				bw8.close();
				fw8.close();
			}

			/*** Archivo de Urgencias ***/
			if (AR.equals("9")) { 
				System.out.println("ENTRAMOS A URGENCIAS AR= " + AR);
				//se crea el archivo AU
				FileWriter fw9 = new FileWriter(carpeta + "\\AU" + nom + ".txt");
				BufferedWriter bw9 = new BufferedWriter(fw9);
				
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					rs = mr.AU(fi, ff, V[i]);
					try {
						while (rs.next()) {
							System.out.println("AU");
							//se adiciona registro al archivo AU
							bw9.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ rs.getString(7) + ","+ rs.getString(8) + ","+ rs.getString(9) + ","+ rs.getString(10) + ","+ rs.getString(11) + ","+ rs.getString(12) + ","+ rs.getString(13) + ","+ rs.getString(14) + ","+ rs.getString(15) + ","+ rs.getString(16) + ","+ rs.getString(17)+ "\r\n");
							bw9.flush();
														
							System.out.println("Esto trajo la consulta URGENCIAS "+ rs.getString(1)+ ","+ rs.getString(2)+ ","+ rs.getString(3)+ ","+ rs.getString(4)+ ","+ rs.getString(5)+ ","+ rs.getString(6)+ ","+ rs.getString(7)+ ","+ rs.getString(8)+ ","+ rs.getString(9)+ ","+ rs.getString(10)+ ","+ rs.getString(11)+ ","+ rs.getString(12)+ ","+ rs.getString(13)+ ","+ rs.getString(14)+ ","+ rs.getString(15)+ ","+ rs.getString(16)+ ","+ rs.getString(17));
							cau++;
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.println("Error " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(cau);
				//se cierra la conexion de la escritura del archivos
				bw9.close();
				fw9.close();
			}

			
			/*** Archivo de Usuarios ***/
			if (AR.equals("10")) {
				//se crea el archivo US
				FileWriter fw10 = new FileWriter(carpeta + "\\US" + nom + ".txt");
				BufferedWriter bw10 = new BufferedWriter(fw10);
				
				int icj = 0;
				String CJ[] = new String[10000];
				for (int i = 0; i < i1; i++) {// for de la cantidad de facturas
					String CodEnt = req.getParameter("CodEnt");
					rs = mr.US(fi, ff, CodEnt, V[i]);
					try {
						while (rs.next()) {
							String[] pnombre = rs.getString(7).split(" ");
							String[] edad = rs.getString(8).split("-");

							int swp = 0;
							String cons = rs.getString(1) + ""+ rs.getString(2);
							int lon = CJ.length;

							for (int z = 0; z < lon; z++) {
								if (cons.equals(CJ[z])) {
									swp = 1;
								}
							}

							if (swp == 0) {
								CJ[icj] = rs.getString(1) + ""+ rs.getString(2);
								icj++;

								if (pnombre.length > 1) {
									System.out.println("US1");
									//se adiciona registro al archivo US 
									bw10.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ pnombre[0] + "," + pnombre[1]+ "," + edad[0] + "," + edad[1]+ "," + rs.getString(9) + ",");						
														
									if (String.valueOf(rs.getString(10).length()).equals("1")) {
										//se adiciona registro al archivo US 
										bw10.write("0" + rs.getString(10) + ",");
									} else {
										//se adiciona registro al archivo US 
										bw10.write(rs.getString(10) + ",");
									}
									if (String.valueOf(rs.getString(11).length()).equals("1")) {
										//se adiciona registro al archivo US 
										bw10.write("00" + rs.getString(11)+ ",");
									} else {
										if (String.valueOf(rs.getString(11).length()).equals("2")) {
											//se adiciona registro al archivo US 
											bw10.write("0" + rs.getString(11)+ ",");
										}else{
											//se adiciona registro al archivo US 
											bw10.write(rs.getString(11) + ",");
										}
									}
									
									
									if ((rs.getString(12)).equals("")) {
										//se adiciona registro al archivo US 
										bw10.write("U"+ "\r\n");
									} else {
										//se adiciona registro al archivo US 
										bw10.write(rs.getString(12)+ "\r\n");
									}
									//se crea una nueva linea
									bw10.flush();
								
								} else {
									System.out.println("US2");
									//se adiciona registro al archivo US 
									bw10.write(rs.getString(1) + ","+ rs.getString(2) + ","+ rs.getString(3) + ","+ rs.getString(4) + ","+ rs.getString(5) + ","+ rs.getString(6) + ","+ pnombre[0] + ",," + edad[0] + ","+ edad[1] + "," + rs.getString(9)+ ",");
									
									if (String.valueOf(rs.getString(10).length()).equals("1")) {
										//se adiciona registro al archivo US 
										bw10.write("0" + rs.getString(10) + ",");
									} else {
										//se adiciona registro al archivo US 
										bw10.write(rs.getString(10) + ",");
									}
									if (String.valueOf(rs.getString(11).length()).equals("1")) {
										//se adiciona registro al archivo US 
										bw10.write("00" + rs.getString(11)+ ",");
									} else {
										if (String.valueOf(rs.getString(11).length()).equals("2")) {
											//se adiciona registro al archivo US 
											bw10.write("0" + rs.getString(11)+ ",");
										}else{
											//se adiciona registro al archivo US 
											bw10.write(rs.getString(11) + ",");
										}
									}
									
									if ((rs.getString(12)).equals("")) {
										//se adiciona registro al archivo US 
										bw10.write("U"+ "\r\n");
									} else {
										//se adiciona registro al archivo US 
										bw10.write(rs.getString(12)+ "\r\n");
									}
									// se crea una nueva linea
									bw10.flush();					
								}
								cus++;
							}// fin swp
						}
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						out.print("Error " + e);
						e.printStackTrace();
					}
				}
				//se imprime la cantidad de registros encontrados
				out.print(cus);
				//se cierra la conexion de escritura del archivo
				bw10.close();
				fw10.close();
			}

			/*** Archivo de Control ***/
			if (AR.equals("11")) {// System.out.println("Aqui vamos100000");
				System.out.println("CT");
				//se crea el archivo CT
				FileWriter fw11 = new FileWriter(carpeta + "\\CT" + nom + ".txt");
				BufferedWriter bw11 = new BufferedWriter(fw11);
				
				//verifica que el archivo AC contenga registros
				if(ctac.equals("0")){
					System.out.println("CTAC"+ctac); 
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AC" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("CTAC"+ctac); 
					//se adiciona registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",AC"+nom+","+ctac+ "\r\n");
					bw11.flush();
				}
				
				//verifica que el archivo AF contenga registros
				if(ctaf.equals("0")){
					System.out.println("ctaf"+ctaf); 
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AF" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctaf"+ctaf); 
					//se adiciona registro al archivo CT
					 bw11.write(prestador + "," + fechacj + ",AF"+nom+","+ctaf+ "\r\n");
					 bw11.flush();
					
				}
				
				//verifica que el archivo AH contenga registros
				if(ctah.equals("0")){
					System.out.println("ctah"+ctah);
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AH" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctah"+ctah); 
					//se adiciona un registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",AH"+nom+","+ctah+ "\r\n");
					bw11.flush();
					
				}
				
				//verifica que el archivo AM contenga registros
				if(ctam.equals("0")){
					System.out.println("ctam"+ctam); 
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AM" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctam"+ctam); 
					//se adiciona un registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",AM"+nom+","+ctam+ "\r\n");
					bw11.flush();
					
				}
				
				//verifica que el archivo AP contenga registros
				if(ctap.equals("0")){
					System.out.println("ctap"+ctap); 
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AP" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctap"+ctap); 
					//se adiciona un registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",AP"+nom+","+ctap+ "\r\n");
					bw11.flush();
					
				}
				
				//verifica que el archivo AT contenga registros
				if(ctat.equals("0")){
					System.out.println("ctat"+ctat); 
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AT" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctat"+ctat); 
					//se adiciona registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",AT"+nom+","+ctat+ "\r\n");
					bw11.flush();
					
				}
				
				//verifica que el archivo AU contenga registros
				if(ctau.equals("0")){
					System.out.println("ctau"+ctau);
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AU" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctau"+ctau);
					//se adiciona un registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",AU"+nom+","+ctau+ "\r\n");
					bw11.flush();
					
				}
				
				//verifica que el archivo RN contenga registros
				if(ctrn.equals("0")){
					System.out.println("ctrn"+ctrn);
					//borra el archivo ya que se encuentra vacio
					String srcFilename =  contenido + "\\"+nom+"\\AN" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctrn"+ctrn); 
					//se adiciona una registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",AN"+nom+","+ctrn+ "\r\n");
					bw11.flush();
					
				}
				
				//verifica que el archivo US contenga registros
				if(ctus.equals("0")){
					System.out.println("ctus"+ctus); 
					//borra el archivo 
					String srcFilename =  contenido + "\\"+nom+"\\US" + nom + ".txt";
					File srcFile = new File(srcFilename);
					if(srcFile.exists()){ 
			            if(srcFile.delete()) 
			                System.out.println("Borrado con exito :)"); 
			            else 
			                System.out.println("Se ocaciono un error al borrar el directorio :("); 
			        }else{ 
			            System.out.println("El directorio no existe :("); 
			        } 
				}else{
					System.out.println("ctus"+ctus); 
					//se adciciona un registro al archivo CT
					bw11.write(prestador + "," + fechacj + ",US"+nom+","+ctus+ "\r\n");
					bw11.flush();
					
				}			 
				
				
				out.print("CT");
				//se cierra la conexion de la escritura del archivo
				bw11.close();
				fw11.close();
			}
			/*** FIN ARCHIVOS RIPS ***/
		}
		
		
		/* crear carpeta comprimida*/
		if (va.equals("6")) {
			System.out.println("zip");
			String nom=req.getParameter("nom");
	
			//se crea carpeta con extension .zip
			String zipFile = contenido+"\\"+nom+".zip";
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
					String srcFilename =  contenido + "\\"+nom+"\\AC" + nom + ".txt";
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
					String srcFilename =  contenido + "\\"+nom+"\\AF" + nom + ".txt";
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
					String srcFilename =  contenido + "\\"+nom+"\\AH" + nom + ".txt";
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
					String srcFilename = contenido + "\\"+nom+"\\AM" + nom + ".txt";
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
					String srcFilename = contenido + "\\"+nom+"\\AP" + nom + ".txt";
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
					String srcFilename = contenido + "\\"+nom+"\\AT" + nom + ".txt";
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
					String srcFilename = contenido + "\\"+nom+"\\AU" + nom + ".txt";
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
					String srcFilename = contenido + "\\"+nom+"\\AN" + nom + ".txt";
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
					String srcFilename = contenido + "\\"+nom+"\\US" + nom + ".txt";
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
				String srcFilename = contenido + "\\"+nom+"\\CT" + nom + ".txt";
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
				File f = new File(contenido + "\\"+nom); 
				 System.out.println("f"+f); 
		         
		        if(f.exists()){ 
		            if(f.delete()) 
		                System.out.println("Borrado con exito :)"); 
		            else 
		                System.out.println("Se ocaciono un error al borrar el directorio :("); 
		        }else{ 
		            System.out.println("El directorio no existe :("); 
		        } 
				
			} catch (IOException ioe) {
				System.out.println("Error creating zip file" + ioe);
			}
		}
		
		
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
			String eps=req.getParameter("eps");
			
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
			String facta="";
			String ARA=req.getParameter("ARA");
			String nom=req.getParameter("nom");
		    
			 int cah=0;
		 	 int cam=0;
		 	 int can=0;
		 	 int cap=0;
		 	 int cat=0;
		 	 int cau=0;
		 	 int cus=0;
		  	 int cac=0;
		  	 int caf=0;
		 	
		  	String dias=fi.substring(0, 2);
			String meses=fi.substring(3, 5);
			String anos=fi.substring(6, 10);
			fi=anos+"-"+meses+"-"+dias;
			
			String diasf=ff.substring(0, 2);
			String mesesf=ff.substring(3, 5);
			String anosf=ff.substring(6, 10);
			ff=anosf+"-"+mesesf+"-"+diasf;
		  	 
			facta=datos;
			System.out.println("DATO REAL : "+datos);
			//Busco los encabezados asociados a esta factura agrupada
			rs=mr.EncabezadosA(datos);
			datos="";
			try {
				while(rs.next()){
					datos=datos+rs.getString(1)+"|";
				}
				rs.getStatement().getConnection().close();
			} catch (SQLException e) {
				out.print("Error "+e);
				e.printStackTrace();
			}
			
			
			//Numero de facturas y las facturas
			String V[] = new String[20000];
			int i1=0;
			 StringTokenizer elementos;  
			 elementos = new StringTokenizer(datos,"|"); 
			   while(elementos.hasMoreTokens()){ 
				   V[i1] = elementos.nextToken();  
				   System.out.println("V["+i1+"]: "+V[i1]);
				   i1++;
			   }
					   
				
			 //se crea la carpeta
				File carpeta = new File(contenido + "\\"+nom+""); 
				carpeta.mkdir();
				System.out.println("carpeta: " + carpeta);
				   
			 try {
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
		
					
				System.out.println("DEBERIA FINALIZAR: "+datos);
				
						
				
				/*** Archivo de Consultas FUNCIONA ***/	
			/*	if(ARA.equals("1")){		
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					rs2=mr.DAC(V[i]);
					if(rs2.next()){
						Tdig=rs2.getString(1);
						rs=mr.AC1(fi,ff,V[i],Tdig);
				  try {
						 while(rs.next()){
							 if(cac>0){out.print("\r\n");}
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().close();
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
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().close();
					 } catch (SQLException e) {
						out.print("Error "+e);
						e.printStackTrace();
					 }

					}	
				
				}//fin for
				}//FIN ARA=1
					
		*/
		
		
		
				/*** Archivo de Descripcion Agrupada***/
			 	 
			 	/*** Archivo de Transacciones***/
				if(ARA.equals("1")){	
					//se crea el archivo AF
					FileWriter fw = new FileWriter(carpeta + "\\AF" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					
					rs=mr.AF(fi,ff,facta);
					  try {
							 if(rs.next()){
								bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17)+"\r\n");
								System.out.println("consulta af "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17)+"\r\n");
								caf++;
							}
							 rs.getStatement().getConnection().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
					System.out.println("caf: "+caf);
					out.print(caf);
					bw.close();
					fw.close();
				}
		

				/*** Archivo de Usuarios***/
				if(ARA.equals("2")){//System.out.println("Aqui vamos100000");
					System.out.println("ENTRAMOS A USUARIOS AR= "+ARA);
					int icj=0;
					String CJ[] = new String[10000];
					FileWriter fw = new FileWriter(carpeta + "\\US" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					
					 System.out.println("UU"+i+": "+V[i]);
					
					String CodEnt=req.getParameter("CodEnt");
					 rs=mr.USA(V[i]);
					  try {
							 while(rs.next()){
								 String[] pnombre = rs.getString(7).split(" ");
									String[] edad = rs.getString(8).split("-");
									
									int swp=0;
									String cons=rs.getString(1)+""+rs.getString(2);
									int lon=CJ.length;
									
									for(int z=0;z<lon;z++){
										if(cons.equals(CJ[z])){
											swp=1;
										}
									}
																
									if(swp==0){									
									CJ[icj]= rs.getString(1)+""+rs.getString(2);
									icj++;
																		
									if(pnombre.length>1){
										 System.out.println("US1");
										 bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+pnombre[0]+","+pnombre[1]+","+edad[0]+","+edad[1]+","+rs.getString(9)+",");
																			 
										 if (String.valueOf(rs.getString(10).length()).equals("1")) {
												//se adiciona registro al archivo US 
												bw.write("0" + rs.getString(10) + ",");
											} else {
												//se adiciona registro al archivo US 
												bw.write(rs.getString(10) + ",");
											}
											if (String.valueOf(rs.getString(11).length()).equals("1")) {
												//se adiciona registro al archivo US 
												bw.write("00" + rs.getString(11)+ ",");
											} else {
												if (String.valueOf(rs.getString(11).length()).equals("2")) {
													//se adiciona registro al archivo US 
													bw.write("0" + rs.getString(11)+ ",");
												}else{
													//se adiciona registro al archivo US 
													bw.write(rs.getString(11) + ",");
												}
											}
											
											
											if ((rs.getString(12)).equals("")) {
												//se adiciona registro al archivo US 
												bw.write("U"+ "\r\n");
											} else {
												//se adiciona registro al archivo US 
												bw.write(rs.getString(12)+ "\r\n");
											}
										 
										 
										 
									}else{
										 System.out.println("US2");
										 bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+pnombre[0]+",,"+edad[0]+","+edad[1]+","+rs.getString(9)+",");
										 if (String.valueOf(rs.getString(10).length()).equals("1")) {
												//se adiciona registro al archivo US 
												bw.write("0" + rs.getString(10) + ",");
											} else {
												//se adiciona registro al archivo US 
												bw.write(rs.getString(10) + ",");
											}
											if (String.valueOf(rs.getString(11).length()).equals("1")) {
												//se adiciona registro al archivo US 
												bw.write("00" + rs.getString(11)+ ",");
											} else {
												if (String.valueOf(rs.getString(11).length()).equals("2")) {
													//se adiciona registro al archivo US 
													bw.write("0" + rs.getString(11)+ ",");
												}else{
													//se adiciona registro al archivo US 
													bw.write(rs.getString(11) + ",");
												}
											}
											
											
											if ((rs.getString(12)).equals("")) {
												//se adiciona registro al archivo US 
												bw.write("U"+ "\r\n");
											} else {
												//se adiciona registro al archivo US 
												bw.write(rs.getString(12)+ "\r\n");
											}
									}
									cus++;
									}//fin swp
							}
							rs.getStatement().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
						}
				System.out.println("cus: "+cus);
				out.print(cus);
				 bw.close();
				 fw.close();
					}//fin archivo de usuarios
				
				
				
				/*** Archivo de Medicamentos***/
				if(ARA.equals("3")){
					System.out.println("ENTRAMOS A MEDICAMENTOS AR= "+ARA);
					FileWriter fw = new FileWriter(carpeta + "\\AM" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					///NO APLICAN CARRE?O
				for(int i=0; i<i1; i++){//for de la cantidad de facturas	
					rs=mr.AMA(fi,ff,V[i]);
					  try {
							 while(rs.next()){
								bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+"\r\n");
								System.out.println("Esto trajo la consulta Transacciones "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14));
								cam++;
							}
							rs.getStatement().close();
						 } catch (SQLException e) {
							out.print("Error "+e);
							e.printStackTrace();
						 }
						}
				System.out.println("cam: "+cam);
				out.print(cam);
				bw.close();
				fw.close();
				}
				
				
				
				
				//AC
				if(ARA.equals("4")){	
					System.out.println("llegamos al ac i1 "+i1);
					FileWriter fw = new FileWriter(carpeta + "\\AC" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					
					
				for(int i=0; i<i1; i++){//for de la cantidad de facturas
					System.out.println("llegamos al ac v[i] "+V[i]);
					
					rs2=mr.DACA(V[i]);
				 try {
				
					 if(rs2.next()){
						Tdig=rs2.getString(1);
						System.out.println("llegamos al ac"+V[i]+" no se q es esto "+Tdig);
						
						rs=mr.AC1A(fi,facta,V[i],Tdig);
						System.out.println("Se entra al metodo ACl ");
						
				 		 while(rs.next()){
							 
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							// outputAC.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							//EL Q ESTABA out.print(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							
							 if(rs.getInt(18)>1){
								 int vari=0;
								 	do{
									bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(15)+"\r\n");
									 vari=vari+1;
								 	}while(vari<rs.getInt(18));
								}else{
							  bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17)+"\r\n");	 
							 }

							System.out.println("Esto trajo la consulta AC1 "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().close();
				
					 System.out.println("AC1 dio  "+cac);
						
					}else{
					
					rs=mr.ACA(fi,facta,V[i]);
					System.out.println("Se entra al metodo AC solo ");
					
					 while(rs.next()){
							 String ce=rs.getString(9);
							 String ce2=rs.getString(8);
							 String cen="0";
							 if (ce.length()<2){ce=cen+ce;}
							 if (ce2.length()<2){ce2=cen+ce2;}
							
							 if(rs.getInt(18)>1){
								 	int vari=0;
								 	do{
								 	bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(15)+"\r\n");
									 vari=vari+1;
								 	}while(vari<rs.getInt(18));
								}else{
							  bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17)+"\r\n");	 
							 }
							
							System.out.println("Esto trajo la consulta AC "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+ce2+","+ce+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
							cac++;
						}
						rs.getStatement().close();
					}
				
					 System.out.println("AC dio  "+cac);
					 rs2.getStatement().getConnection().close();
				 } catch (SQLException e) {
						out.print("Error CESARJULIO "+e);
						e.printStackTrace();
					 }
				
				}
				System.out.println("cac: "+cac);
				out.print(cac);
				bw.close();
				fw.close();
				
			}
				
				
				//AT
				/*** Archivo de Otros Servicios***/	
				if(ARA.equals("5")){	
					System.out.println("ENTRAMOS A AT OTROS SERV AR= "+ARA);
					FileWriter fw = new FileWriter(carpeta + "\\AT" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					
					for(int i=0; i<i1; i++){//for de la cantidad de facturas	
					rs=mr.ATA(fi,facta,V[i]);
					  try {
							 while(rs.next()){
								 System.out.println("AT");
								 bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+",1,"+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+"\r\n");
								 //donde va el uno que indica el tipo de servicio iba antes +rs.getString(6)+
								 cat++;
								 System.out.println("Esto trajo la consulta OTROS SERVICIOS "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+",1,"+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11));
							}
							rs.getStatement().close();
						 } catch (SQLException e) {
							//out.print("Error "+e);
							e.printStackTrace();
						 }
						}
					System.out.println("cat: "+cat);
					out.print(cat);
					bw.close();
					fw.close();
					}
				
				
				/*** Archivo de Urgencias***/	
				if(ARA.equals("6")){	
					System.out.println("ENTRAMOS A AU URGENCIAS ARA= "+ARA);
					FileWriter fw = new FileWriter(carpeta + "\\AU" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					for(int i=0; i<i1; i++){//for de la cantidad de facturas	
						rs=mr.AUA(fi,ff,V[i]);
						  try {
								 while(rs.next()){
									 System.out.println("AU");
									 bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17)+"\r\n");	
									 System.out.println("Esto trajo la consulta URGENCIAS "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+","+rs.getString(17));
									 cau++;
								}
								rs.getStatement().close();
							 } catch (SQLException e) {
								out.println("Error "+e);
								e.printStackTrace();
							 }
							}
					System.out.println("cau: "+cau);
					out.print(cau);
					bw.close();
					fw.close();
					
				}
					
				/*** Archivo de Procedimientos***/
				if(ARA.equals("7")){
					System.out.println("ENTRAMOS A PROCEDIMIENTIOS AR= "+ARA);
				
					FileWriter fw = new FileWriter(carpeta + "\\AP" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					
					for(int i=0; i<i1; i++){//for de la cantidad de facturas
					rs2=mr.DAPA(V[i]);
					System.out.println("ENTRAMOS DAP AR= "+ARA);
					
					try{
						if(rs2.next()){
							Tdig=rs2.getString(1);
							rs=mr.APA(fi,facta,V[i],Tdig);
							try {
								while(rs.next()){
									
									bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+"\r\n");
									System.out.println("Esto trajo la consulta AP1 "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15));
									
									cap++;
								}
								rs.getStatement().close();
								
								rs1=mr.APA2(fi,facta,V[i]);
								while(rs1.next()){
									bw.write(rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15)+"\r\n");
									System.out.println("Esto trajo la consulta AP2 "+rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15));
									
									cap++;
								}
								
								rs1.getStatement().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
						}else{	
							rs=mr.APA1(fi,facta,V[i]);	
							System.out.println("ENTRAMOS A AP1 AR= "+ARA);
							
							try {
								while(rs.next()){
									bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+"\r\n");
									System.out.println("Esto trajo la consulta AP1 "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+rs.getString(11)+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15));
									
									cap++;
								}
								rs.getStatement().close();
							 
								rs1=mr.APA2(fi,facta,V[i]);
								while(rs1.next()){
									bw.write(rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15)+"\r\n");
									System.out.println("Esto trajo la consulta AP2 "+rs1.getString(1)+","+rs1.getString(2)+","+rs1.getString(3)+","+rs1.getString(4)+","+rs1.getString(5)+","+rs1.getString(6)+","+rs1.getString(7)+","+rs1.getString(8)+","+rs1.getString(9)+","+rs1.getString(10)+","+rs1.getString(11)+","+rs1.getString(12)+","+rs1.getString(13)+","+rs1.getString(14)+","+rs1.getString(15));
									cap++;
								}
								
								rs1.getStatement().close();
							} catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							}
						}
						rs2.getStatement().getConnection().close();
					 } catch (SQLException e) {
							out.print("Error CESARJULIO2 "+e);
							e.printStackTrace();
						 }
					}
					
					out.print(cap);
					bw.close();
					fw.close();
				}
				
				
				/*** Archivo de Recien Nacidos***/	
				if(ARA.equals("8")){	
					System.out.println("ENTRAMOS A RN RECIEN NACIDOS= "+ARA);
					FileWriter fw = new FileWriter(carpeta + "\\AN" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					out.print(can);
					bw.close();
					fw.close();
				}
				

				/*** Archivo de Hospitalizacion***/	
				if(ARA.equals("9")){	
					System.out.println("ENTRAMOS A AH HOSPITALIZACION= "+ARA);
					FileWriter fw = new FileWriter(carpeta + "\\AH" + nom + ".txt");
					BufferedWriter bw = new BufferedWriter(fw);
					for(int i=0; i<i1; i++){//for de la cantidad de facturas	
						rs=mr.AH(fi,ff,V[i]);
						  try {
								 while(rs.next()){
									 
									 String[] diagEgreso = rs.getString(11).split("_");														
										String de1="",de2="",de3="",de4="";
										if(diagEgreso.length==1){
											de1=diagEgreso[0];
										}else{
											if(diagEgreso.length==2){
												de1=diagEgreso[0];
												de2=diagEgreso[1];
											}else{
												if(diagEgreso.length==3){
													de1=diagEgreso[0];
													de2=diagEgreso[1];
													de3=diagEgreso[2];
												}else{
													if(diagEgreso.length==4){
														de1=diagEgreso[0];
														de2=diagEgreso[1];
														de3=diagEgreso[2];
														de4=diagEgreso[3];
													}
												}
											}
										}
										bw.write(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+de1+","+de2+","+de3+","+de4+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16)+"\r\n");
										System.out.println("Esto trajo la consulta HOSPITAIZAAS "+rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10)+","+de1+","+de2+","+de3+","+de4+","+rs.getString(12)+","+rs.getString(13)+","+rs.getString(14)+","+rs.getString(15)+","+rs.getString(16));
										cah++;
								}
								rs.getStatement().close();
							 } catch (SQLException e) {
								out.print("Error "+e);
								e.printStackTrace();
							 }
							}
					
					out.print(cah);
					bw.close();
					fw.close();
				}
				
				
				/*** Archivo de Control***/
				if(ARA.equals("10")){//System.out.println("Aqui vamos100000");	
					  System.out.println("CT");
						//se crea el archivo CT
						FileWriter fw11 = new FileWriter(carpeta + "\\CT" + nom + ".txt");
						BufferedWriter bw11 = new BufferedWriter(fw11);
						
						//verifica que el archivo AC contenga registros
						if((ctac.equals("0"))||(ctac.equals(""))){
							System.out.println("CTAC"+ctac); 
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AC" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("CTAC"+ctac); 
							//se adiciona registro al archivo CT y nueva linea
							bw11.write(prestador + "," + fechacj + ",AC"+nom+","+ctac+ "\r\n");
							System.out.println(prestador + "," + fechacj + ",AC"+nom+","+ctac+ "\r\n");
							//bw11.write(prestador + "," + fechacj + ",AC"+nom+","+ctac); bw11.newLine();
							 bw11.flush();
							
						}
						
						//verifica que el archivo AF contenga registros
						if((ctaf.equals("0"))||(ctaf.equals(""))){
							System.out.println("ctaf"+ctaf); 
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AF" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctaf"+ctaf); 
							//se adiciona registro al archivo CT y nueva linea
							 //bw11.write(prestador + "," + fechacj + ",AF"+nom+","+ctaf); bw11.newLine();
							 bw11.write(prestador + "," + fechacj + ",AF"+nom+","+ctaf+ "\r\n");
							 bw11.flush();
							
						}
						
						//verifica que el archivo AH contenga registros
						if((ctah.equals("0"))||(ctah.equals(""))){
							System.out.println("ctah"+ctah);
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AH" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctah"+ctah); 
							//se adiciona un registro al archivo CT y una nueva linea
							//bw11.write(prestador + "," + fechacj + ",AH"+nom+","+ctah); bw11.newLine();
							bw11.write(prestador + "," + fechacj + ",AH"+nom+","+ctah+ "\r\n");
							bw11.flush();
							
						}
						
						//verifica que el archivo AM contenga registros
						if((ctam.equals("0"))||(ctam.equals(""))){
							System.out.println("ctam"+ctam); 
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AM" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctam"+ctam); 
							//se adiciona un registro al archivo CT y una linea nueva
							//bw11.write(prestador + "," + fechacj + ",AM"+nom+","+ctam); bw11.newLine();
							bw11.write(prestador + "," + fechacj + ",AM"+nom+","+ctam+ "\r\n");
							bw11.flush();
							
						}
						
						//verifica que el archivo AP contenga registros
						if((ctap.equals("0"))||(ctap.equals(""))){
							System.out.println("ctap"+ctap); 
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AP" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctap"+ctap); 
							//se adiciona un registro al archivo CT y una linea nueva
							//bw11.write(prestador + "," + fechacj + ",AP"+nom+","+ctap); bw11.newLine();
							bw11.write(prestador + "," + fechacj + ",AP"+nom+","+ctap+ "\r\n");
							bw11.flush();
							
						}
						
						//verifica que el archivo AT contenga registros
						if((ctat.equals("0"))||(ctat.equals(""))){
							System.out.println("ctat"+ctat); 
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AT" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctat"+ctat); 
							//se adiciona registro al archivo CT y una nueva linea
							//bw11.write(prestador + "," + fechacj + ",AT"+nom+","+ctat); bw11.newLine();
							bw11.write(prestador + "," + fechacj + ",AT"+nom+","+ctat+ "\r\n");
							bw11.flush();
							
						}
						
						//verifica que el archivo AU contenga registros
						if((ctau.equals("0"))||(ctau.equals(""))){
							System.out.println("ctau"+ctau);
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AU" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctau"+ctau);
							//se adiciona un registro al archivo CT y una nueva linea
							//bw11.write(prestador + "," + fechacj + ",AU"+nom+","+ctau); bw11.newLine();
							bw11.write(prestador + "," + fechacj + ",AU"+nom+","+ctau+ "\r\n");
							bw11.flush();
							
						}
						
						//verifica que el archivo RN contenga registros
						if((ctrn.equals("0"))||(ctrn.equals(""))){
							System.out.println("ctrn"+ctrn);
							//borra el archivo ya que se encuentra vacio
							String srcFilename =  contenido + "\\"+nom+"\\AN" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctrn"+ctrn); 
							//se adiciona una registro al archivo CT y una linea nueva
							//bw11.write(prestador + "," + fechacj + ",RN"+nom+","+ctrn); bw11.newLine();
							bw11.write(prestador + "," + fechacj + ",RN"+nom+","+ctrn+ "\r\n");
							bw11.flush();
							
						}
						
						//verifica que el archivo US contenga registros
						if((ctus.equals("0"))||(ctus.equals(""))){
							System.out.println("ctus"+ctus); 
							//borra el archivo 
							String srcFilename =  contenido + "\\"+nom+"\\US" + nom + ".txt";
							File srcFile = new File(srcFilename);
							if(srcFile.exists()){ 
					            if(srcFile.delete()) 
					                System.out.println("Borrado con exito :)"); 
					            else 
					                System.out.println("Se ocaciono un error al borrar el directorio :("); 
					        }else{ 
					            System.out.println("El directorio no existe :("); 
					        } 
						}else{
							System.out.println("ctus"+ctus); 
							//se adciciona un registro al archivo CT y una linea nueva
							//bw11.write(prestador + "," + fechacj + ",US"+nom+","+ctus); bw11.newLine();
							bw11.write(prestador + "," + fechacj + ",US"+nom+","+ctus+ "\r\n");
							bw11.flush();
							
						}			 
						
						
						out.print("CT");
						//se cierra la conexion de la escritura del archivo
						bw11.close();
						fw11.close();
				}
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}			
	}//FIN VA =5A
		
	}
}