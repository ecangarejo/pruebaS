package Anexos_Controlador;

import java.io.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import com.csvreader.CsvReader;
import com.sun.java.swing.plaf.windows.resources.windows;

import Anexos_metodo.MetodoBDCapitado;

public class ControlBDCapitado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Object logger;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		MetodoBDCapitado mp = new MetodoBDCapitado();
		ResultSet rs, rs1, rs2 = null;

		System.out.println("control af post");
		String codempresa = "";
		String codconvenio = "";
		String urg = "0";
		String cex = "0";
		String hosp = "0";
		String amb = "0";
		String pyp = "0";
		String prueba = "0";
		String ext = "";
		String stringtipodoc = "";
		String stringnumdoc = "";
		String stringpape = "";
		String stringsape = "";
		String stringpnombre = "";
		String stringsnombre = "";
		String stringfecnac = "";
		String stringgenero = "";
		String stringmun = "";
		String stringzona = "";
		String stringdir = "";
		int contE = 0;
		int contNE = 0;

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		String destination = "C:\\Capitado";
		DiskFileItemFactory factory1 = new DiskFileItemFactory();
		FileItemFactory factory = new DiskFileItemFactory();
		factory1.setRepository(new File(destination));
		ServletFileUpload upload = new ServletFileUpload(factory);
		ServletFileUpload uploader = new ServletFileUpload(factory1);
		List items = null;
		List items1 = null;

		try {
			items = upload.parseRequest(req);
			items1 = uploader.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();

			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				if (fieldName.equals("codempresa")) {
					codempresa = item.getString();
					System.out.println("este es el codempresa " + codempresa);
				}
				if (fieldName.equals("urg")) {
					urg = item.getString();
					if (urg.equals("on")) {
						urg = "1";
					}
				}
				if (fieldName.equals("cex")) {
					cex = item.getString();
					if (cex.equals("on")) {
						cex = "1";
					}
				}
				if (fieldName.equals("hosp")) {
					hosp = item.getString();
					if (hosp.equals("on")) {
						hosp = "1";
					}
				}
				if (fieldName.equals("amb")) {
					amb = item.getString();
					if (amb.equals("on")) {
						amb = "1";
					}
				}
				if (fieldName.equals("pyp")) {
					pyp = item.getString();
					if (pyp.equals("on")) {
						pyp = "1";
					}
				}
			}
			prueba = item.getName();
		}
		
		// capturo la extension del archivo a cargar
		ext = prueba.substring(prueba.length() - 3, prueba.length());
		try {
			// se consulta el codigo del convenio de la empresa
			rs1 = mp.BuscarCodConvenio(codempresa);
			if (rs1.next()) {
				codconvenio = rs1.getString(1);
			}
			rs1.getStatement().getConnection().close();
			System.out.println("convenio " + codconvenio);

		} catch (Exception e) {
			// TODO: handle exception
		}
		String nombre = "";
		
		// upload al servidor
		try {
			Iterator iterator = items.iterator();

			while (iterator.hasNext()) {
				FileItem item = (FileItem) iterator.next();
				System.out.println("este es el codempresa " + codempresa);
				nombre = item.getName();

				File file = new File(destination, codempresa + "." + ext);
				item.write(file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// fin upload servidor

		//se actualizan todos los estados a 1 
		mp.ActualizarPcteConvenio(codconvenio);

		if (ext.equals("xls")) {
			// leer archivo excel
			Iterator iterator = items.iterator();
			FileItem item = (FileItem) iterator.next();

			List cellDataList = new ArrayList();
			try {
				FileInputStream fileInputStream = new FileInputStream(destination + "\\" + codempresa + ".xls");
				POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);

				HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
				HSSFSheet hssfSheet = workBook.getSheetAt(0);
				Iterator rowIterator = hssfSheet.rowIterator();
				while (rowIterator.hasNext()) {
					HSSFRow hssfRow = (HSSFRow) rowIterator.next();
					Iterator iterato = hssfRow.cellIterator();
					List cellTempList = new ArrayList();
					while (iterato.hasNext()) {
						HSSFCell hssfCell = (HSSFCell) iterato.next();
						cellTempList.add(hssfCell);
					}
					cellDataList.add(cellTempList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("filas " + cellDataList.size());

			for (int i = 0; i < cellDataList.size(); i++) {
				List cellTempList = (List) cellDataList.get(i);

				HSSFCell tipodoc = (HSSFCell) cellTempList.get(0);
				stringtipodoc = tipodoc.toString();

				HSSFCell numdoc = (HSSFCell) cellTempList.get(1);
				stringnumdoc = numdoc.toString();

				HSSFCell pape = (HSSFCell) cellTempList.get(2);
				stringpape = pape.toString();

				HSSFCell sape = (HSSFCell) cellTempList.get(3);
				stringsape = sape.toString();

				HSSFCell pnombre = (HSSFCell) cellTempList.get(4);
				stringpnombre = pnombre.toString();

				HSSFCell snombre = (HSSFCell) cellTempList.get(5);
				stringsnombre = snombre.toString();

				HSSFCell fecnac = (HSSFCell) cellTempList.get(6);
				stringfecnac = fecnac.toString();

				if (stringfecnac.charAt(2) == '-') {
					String[] fec = stringfecnac.split("-");
					String dia = fec[0];
					String mes = fec[1];
					if (mes.equals("ene")) {
						mes = "01";
					}
					if (mes.equals("feb")) {
						mes = "02";
					}
					if (mes.equals("mar")) {
						mes = "03";
					}
					if (mes.equals("abr")) {
						mes = "04";
					}
					if (mes.equals("may")) {
						mes = "05";
					}
					if (mes.equals("jun")) {
						mes = "06";
					}
					if (mes.equals("jul")) {
						mes = "07";
					}
					if (mes.equals("ago")) {
						mes = "08";
					}
					if (mes.equals("sep")) {
						mes = "09";
					}
					if (mes.equals("oct")) {
						mes = "10";
					}
					if (mes.equals("nov")) {
						mes = "11";
					}
					if (mes.equals("dic")) {
						mes = "12";
					}
					String aNo = fec[2];
					stringfecnac = aNo + "-" + mes + "-" + dia;

				}
				if (stringfecnac.charAt(2) == '/') {
					String[] fec = stringfecnac.split("/");
					String dia = fec[0];
					String mes = fec[1];
					String aNo = fec[2];
					stringfecnac = aNo + "-" + mes + "-" + dia;
				}

				HSSFCell genero = (HSSFCell) cellTempList.get(7);
				stringgenero = genero.toString();
				if (stringgenero.equals("F")) {
					stringgenero = "Femenino";
				}
				if (stringgenero.equals("M")) {
					stringgenero = "Masculino";
				}
				
				HSSFCell mun = (HSSFCell) cellTempList.get(8);
				stringmun = mun.toString();
				
				HSSFCell zona = (HSSFCell) cellTempList.get(9);
				stringzona = zona.toString();
				
				if (stringzona.equals("U")) {
					stringzona = "Urbana";
				}
				if (stringzona.equals("R")) {
					stringzona = "Rural";
				}
				
				HSSFCell dir = (HSSFCell) cellTempList.get(10);
				stringdir = dir.toString();

				System.out.println("tip doc " + stringtipodoc + " ,num doc "+ stringnumdoc);

				if (stringnumdoc.charAt(0) == '0') {
					stringnumdoc = stringnumdoc.substring(1, stringnumdoc.length());
					if (stringnumdoc.charAt(0) == '0') {
						stringnumdoc = stringnumdoc.substring(1, stringnumdoc.length());
					}
				}

				rs = mp.BuscarPaciente(stringtipodoc, stringnumdoc);
				try {

					if (rs.next()) {
						System.out.println("paciente encontrado");
						System.out.println("nombre: " + rs.getString(3) + " "+ rs.getString(4) + " " + rs.getString(5)
								+ " fecnac: " + rs.getString(7));
						if (stringdir.equals("")) {
							stringdir = rs.getString(9);
						}
						
						mp.ActualizarDatos(rs.getString(6), stringtipodoc,stringpape, stringsape, stringpnombre + " "+ stringsnombre, stringfecnac,stringgenero, stringmun, stringzona, stringdir,	codempresa);

						// verifica que el paciente ya se encuentra en la tabla
						// pcte capita, se envia codigo del paciente
						rs1 = mp.BuscarPacienteCapita(rs.getString(6));
						if (rs1.next()) {
							mp.ActualizarDatosCapita(rs.getString(6),codconvenio, urg, hosp, amb, cex, pyp);
						} else {
							mp.insertarDatosPcteCapitado(rs.getString(6),codconvenio, urg, hosp, amb, cex, pyp);
						}
						contE = contE + 1;
						rs1.getStatement().getConnection().close();
					} else {
						System.out.println("paciente no encontrado");
						contNE = contNE + 1;
						mp.insertarDatosPcte(stringtipodoc, stringnumdoc,stringpape, stringsape, stringpnombre + " "+ stringsnombre, stringfecnac,stringgenero, stringmun, stringzona, stringdir,codempresa);
						rs1 = mp.BuscarPaciente(stringtipodoc, stringnumdoc);
						if (rs1.next()) {
							rs2 = mp.BuscarPacienteCapita(rs1.getString(6));
							if (rs2.next()) {
								mp.ActualizarDatosCapita(rs1.getString(6),codconvenio, urg, hosp, amb, cex, pyp);
							} else {
								mp.insertarDatosPcteCapitado(rs1.getString(6),codconvenio, urg, hosp, amb, cex, pyp);
							}
							contNE = contNE + 1;
							rs2.getStatement().getConnection().close();
						}
						rs1.getStatement().getConnection().close();
					}
					rs.getStatement().getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				System.out.println();
			}
			System.out.println("pacE: " + contE);
			System.out.println("pacNO E: " + contNE);
		}

		// *** leer archivo cvs

		if (ext.equals("csv")) {
			CsvReader reader = null;
			try {
				// instancio el objeto readerCSV
				reader = new CsvReader(destination + "\\" + codempresa + "."+ ext);
				// asigno separador de valores punto y coma, si no lo cambian
				// queda por defecto la coma
				reader.setDelimiter(';');
				// recorremos las filas del fichero
				while (reader.readRecord()) {
					stringtipodoc = reader.get(0);
					stringnumdoc = reader.get(1);
					stringpape = reader.get(2);
					stringsape = reader.get(3);
					stringpnombre = reader.get(4);
					stringsnombre = reader.get(5);
					stringfecnac = reader.get(6);
					stringgenero = reader.get(7);
					stringmun = reader.get(8);
					stringzona = reader.get(9);
					stringdir = reader.get(10);

					System.out.println("Tipo_documento : " + reader.get(0)
							+ " numero_documento: " + reader.get(1)
							+ " Primer_Apellido: " + reader.get(2)
							+ "Segundo_Apellido : " + reader.get(3)
							+ " Primer_nombre: " + reader.get(4)
							+ " Segundo_nombre: " + reader.get(5)
							+ "fecha_nacimiento : " + reader.get(6)
							+ " genero: " + reader.get(7) + " municipio: "
							+ reader.get(8) + "zona residencial : "
							+ reader.get(9) + " direccion: " + reader.get(10));

					String[] fec = stringfecnac.split("/");
					String dia = fec[0];
					String mes = fec[1];
					String anio = fec[2];
					stringfecnac = anio + "-" + mes + "-" + dia;

					if (stringgenero.equals("F")) {
						stringgenero = "Femenino";
					}
					if (stringgenero.equals("M")) {
						stringgenero = "Masculino";
					}

					if (stringzona.equals("U")) {
						stringzona = "Urbana";
					}
					if (stringzona.equals("R")) {
						stringzona = "Rural";
					}

					if (reader.get(1).charAt(0) == '0') {
						stringnumdoc = reader.get(1).substring(1,reader.get(1).length());
						if (stringnumdoc.charAt(0) == '0') {
							stringnumdoc = stringnumdoc.substring(1,stringnumdoc.length());
						}
					}

					rs = mp.BuscarPaciente(stringtipodoc, stringnumdoc);

					if (rs.next()) {
						System.out.println("paciente encontrado");
						System.out.println("nombre: " + rs.getString(3) + " "
								+ rs.getString(4) + " " + rs.getString(5)
								+ " fecnac: " + rs.getString(7));
						if (stringdir.equals("")) {
							stringdir = rs.getString(9);
						}
						mp.ActualizarDatos(rs.getString(6), stringtipodoc,stringpape, stringsape, stringpnombre + " "+ stringsnombre, stringfecnac,stringgenero, stringmun, stringzona, stringdir,codempresa);

						// verifica que el paciente ya se encuentra en la tabla
						// pcte capita, se envia codigo del paciente
						rs1 = mp.BuscarPacienteCapita(rs.getString(6));
						if (rs1.next()) {
							mp.ActualizarDatosCapita(rs.getString(6),
									codconvenio, urg, hosp, amb, cex, pyp);
						} else {
							mp.insertarDatosPcteCapitado(rs.getString(6),
									codconvenio, urg, hosp, amb, cex, pyp);
						}
						contE = contE + 1;
						rs1.getStatement().getConnection().close();
					} else {
						System.out.println("paciente no encontrado");
						contNE = contNE + 1;
						mp.insertarDatosPcte(stringtipodoc, stringnumdoc,
								stringpape, stringsape, stringpnombre + " "
										+ stringsnombre, stringfecnac,
								stringgenero, stringmun, stringzona, stringdir,
								codempresa);
						rs1 = mp.BuscarPaciente(stringtipodoc, stringnumdoc);
						if (rs1.next()) {
							rs2 = mp.BuscarPacienteCapita(rs1.getString(6));
							if (rs2.next()) {
								mp.ActualizarDatosCapita(rs1.getString(6),
										codconvenio, urg, hosp, amb, cex, pyp);
							} else {
								mp.insertarDatosPcteCapitado(rs1.getString(6),
										codconvenio, urg, hosp, amb, cex, pyp);
							}
							contNE = contNE + 1;
							rs2.getStatement().getConnection().close();
						}
						rs1.getStatement().getConnection().close();
					}
					rs.getStatement().getConnection().close();

					System.out.println();
				}// end while - recorrido del csv
				System.out.println("pacE: " + contE);
				System.out.println("pacNO E: " + contNE);

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				reader.close();
			} // end try
		}
		res.sendRedirect("Facturacion.jsp");

	}
}