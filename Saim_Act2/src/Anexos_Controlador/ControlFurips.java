package Anexos_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Anexos_metodo.MetodoFurips;
import adm_logica.MetodoAutoCompletar;


public class ControlFurips extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlFurips() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String tp = req.getParameter("TipoDocumento");
		String num = req.getParameter("NumeroDocumento");
		String va = req.getParameter("valor");

		ResultSet rs = null;
		ResultSet rs1= null;
		ResultSet rs2= null;
		ResultSet rs3= null;
		ResultSet rs4= null;
		ResultSet rs5= null;
		MetodoFurips metodos = new MetodoFurips();
		ResultSet busqueda_paciente = null;
		ResultSet busqueda_propietario = null;
		ResultSet busqueda_conductor = null;
		ResultSet busqueda_medico =null;
		PrintWriter out = res.getWriter();
		String x = req.getParameter("x");
		String tipodocumentopaciente = req.getParameter("TipoDocumento");
		String numeroDocumento = req.getParameter("NumeroDocumento");
		String Codigo = req.getParameter("Codigo");
		String usuario = req.getParameter("usuario");
		String codigo= req.getParameter("furips");
		// variables de los datos del sitio
		String fec_radicado = req.getParameter("fec_radicado");
		String num_radicado = req.getParameter("num_radicado");
		String ant_radicado = req.getParameter("ant_radicado");
		String factura = req.getParameter("factura");
		String tipo_documento = req.getParameter("tipo_documento");
		String num_documento = req.getParameter("num_documento");
		String condicion = req.getParameter("condicion");
		String evento = req.getParameter("evento");
		String dir_ocurrencia = req.getParameter("dir_ocurrencia");
		String fec_evento = req.getParameter("fec_evento");
		String hora = req.getParameter("hora");
		String dpto_evento = req.getParameter("dpto_evento");
		String mun_evento = req.getParameter("mun_evento");
		String zona = req.getParameter("zona");
		String descripcion = req.getParameter("descripcion");
		String admision = req.getParameter("admision");
		// variables de los datos del accidente
		String aseguramiento = req.getParameter("aseguramiento");
		String marca = req.getParameter("marca");
		String placa = req.getParameter("placa");
		String servicio = req.getParameter("servicio");
		String CodAseguradora = req.getParameter("CodAseguradora");
		String NumPoliza = req.getParameter("NumPoliza");
		String Autoridad = req.getParameter("Autoridad");
		String desde = req.getParameter("desde");
		String hasta = req.getParameter("hasta");
		String Excedente = req.getParameter("Excedente");
		//variable ced del propietario
		String propietario = req.getParameter("propietario");
		//variables de los datos del propietario
		String tdpropietario = req.getParameter("tdpropietario");
		String numpropietario = req.getParameter("numpropietario");
		String PrimerApellido = req.getParameter("PrimerApellido");
		String SegundoApellido = req.getParameter("SegundoApellido");
		String PrimerNombre = req.getParameter("PrimerNombre");
		String SegundoNombre = req.getParameter("SegundoNombre");
		String DirPropietario = req.getParameter("DirPropietario");
		String DptoPropietario = req.getParameter("DptoPropietario");
		String MunPropietario = req.getParameter("MunPropietario");
		String TelPropietario = req.getParameter("TelPropietario");
		//variable ced del conductor
		String conductor = req.getParameter("conductor");
		//variables de los datos del conductor
		String tdconductor = req.getParameter("tdConductor");
		String NDconductor = req.getParameter("NDconductor");
		String PAConductor = req.getParameter("PAConductor");
		String SAConductor = req.getParameter("SAConductor");
		String PNConductor = req.getParameter("PNConductor");
		String SNConductor = req.getParameter("SNConductor");
		String DirConductor = req.getParameter("DirConductor");
		String TelConductor = req.getParameter("TelConductor");
		String dp = req.getParameter("dp");
		String mun = req.getParameter("mun");
		//variables busqueda de propietario del vehiculo
		String TipDoc = req.getParameter("TipDoc");
		String NumDoc = req.getParameter("NumDoc");
		String TipDoc1 = req.getParameter("TipDoc");
		String NumDoc2 = req.getParameter("NumDoc");
		String TD = req.getParameter("tdconductor");
		String ND = req.getParameter("txtconductor");
		String TD1 = req.getParameter("tdconductor");
		String ND1 = req.getParameter("txtconductor");
		String TDM = req.getParameter("tdmedico");
		String NDM = req.getParameter("txtmedico");
		String TDM1 = req.getParameter("tdmedico");
		String NDM1 = req.getParameter("txtmedico");
		
		String Remision=req.getParameter("Remision");
		String Transporte=req.getParameter("Transporte");
		//datos remision
		String referencia= req.getParameter("referencia");
		String fec_remision=req.getParameter("fec_remision");
		String hora_remision=req.getParameter("hora_remision");
		String PrestadorRemite=req.getParameter("PrestadorRemite");
		String CodInsRemite=req.getParameter("CodInsRemite");
		String ProfesionalRemite=req.getParameter("ProfesionalRemite");
		String CargoRemite=req.getParameter("CargoRemite");
		String fec_aceptacion=req.getParameter("fec_aceptacion");
		String hora_aceptacion=req.getParameter("hora_aceptacion");
		String PrestadorRecibe=req.getParameter("PrestadorRecibe");
		String CodInsRecibe=req.getParameter("CodInsRecibe");
		String ProfesionalRecibe=req.getParameter("ProfesionalRecibe");
		String CargoRecibe=req.getParameter("CargoRecibe");
		//datos medico
		String PAMedico=req.getParameter("PAMedico");
		String SAMedico=req.getParameter("SAMedico");
		String PNMedico=req.getParameter("PNMedico");
		String SNMedico=req.getParameter("SNMedico");
		String tdmedico=req.getParameter("tdmedico");
		String NDMedico=req.getParameter("NDMedico");
		String RegistroMedico=req.getParameter("RegistroMedico");
		//datos amparo transporte
		String placatrans=req.getParameter("placatrans");
		String transdesde=req.getParameter("transdesde");
		String transhasta=req.getParameter("transhasta");
		String transporte=req.getParameter("transporte");	
		String lugarvictima=req.getParameter("lugarvictima");
		//datos certificacion medica
		String fec_Ingreso=req.getParameter("fec_Ingreso");
		String hora_ingreso=req.getParameter("hora_ingreso");
		String fec_Egreso=req.getParameter("fec_Egreso");
		String hora_egreso=req.getParameter("hora_egreso");
		String DiagPrincipalIngreso=req.getParameter("DiagPrincipalIngreso");
		String DiagPrincipalEgreso=req.getParameter("DiagPrincipalEgreso");
		String OtroDiagIngreso=req.getParameter("OtroDiagIngreso");
		String OtroDiagEgreso=req.getParameter("OtroDiagEgreso");
		String OtroDiagIngreso2=req.getParameter("OtroDiagIngreso2");
		String OtroDiagEgreso2=req.getParameter("OtroDiagEgreso2");
		//amparo que reclama
		String GastoMedicoFacturado=req.getParameter("GastoMedicoFacturado");
		String GastoMedicoFosyga=req.getParameter("GastoMedicoFosyga");
		String GastoTransFacturado=req.getParameter("GastoTransFacturado");
		String GastoTransFosyga=req.getParameter("GastoTransFosyga");
		//fecha
		String fec_inicial=req.getParameter("fec_inicial");
		String fec_final=req.getParameter("fec_final");
		
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH));
		String annio = Integer.toString(c.get(Calendar.YEAR));
		int m=Integer.parseInt(mes)+1;
		mes=String.valueOf(m);
		int d=Integer.parseInt(dia);
		if(d<10){dia="0"+d;}
		if(m<10){mes="0"+m;}
		String fecha=dia+"-"+mes+"-"+annio; //para la base de dtaos
		
		
		
		Calendar calendario = Calendar.getInstance();
		//Calendar calendario = new GregorianCalendar();
			int hora2, minutos, segundos;
			hora2 =calendario.get(Calendar.HOUR_OF_DAY);
			minutos = calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND);
			String hra= hora2+":"+minutos+":"+segundos;
		
		
		//Muestra el Paciente
		if (va.equals("1")) {
			busqueda_paciente = metodos.BuscarPaciente(tipodocumentopaciente,numeroDocumento);
			String NombreCompleto = "";
			rs = metodos.BuscarPaciente(tp, num);
			int verif = 0;
			String codpac = "0";
			try {
			   if (rs.next()) {
					codpac = rs.getString(1);
					verif = 1;
					if (busqueda_paciente.next()) {
						out.print("1|"); // existe paciente
						out.print(busqueda_paciente.getString(6) + "|");
						NombreCompleto = busqueda_paciente.getString(3) + " " + busqueda_paciente.getString(4) + " "+ busqueda_paciente.getString(5);
						out.print("<table width='100%'><tr><td><div align='center' class='style11' id='cabecera2' >DATOS DEL PACIENTE </div></td></tr></table>"
										+ "<table border='0'> "
										+ "<tr>"
										+ "<td width='18%'>Tipo de documento: </td>"
										+ "<td width='13%' align='left'>" + busqueda_paciente.getString(1)+ "</td>"
										+ "<td width='18%'>Numero de documento: </td>"
										+ "<td width='20%' align='left'>" + busqueda_paciente.getString(2)+ "</td>"
										+ "<td width='20%'>Nombre del Paciente: </td>"
										+ "<td width='60%' align='center'>"+ NombreCompleto+ "</td>" 
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='18%'>Fecha Nacimiento: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(7)+ "</td>"
										+ "<td width='18%'>Sexo: </td> "
										+ "<td width='3%' align='left'>"+ busqueda_paciente.getString(8)+ "</td>"
										+ "<td width='3%'>Direccion: </td> "
										+ "<td width='60%' align='left'>"+ busqueda_paciente.getString(9)+ "</td>"
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='18%'>Telefono Celular: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(10)+ "</td>"
										+ "<td width='18%'>Telefono Fijo: </td> "
										+ "<td width='3%' align='left'>"+ busqueda_paciente.getString(11)+ "</td>"
										+ "<td width='3%'>Municipio: </td> "
										+ "<td width='60%' align='left'>"+ busqueda_paciente.getString(12)+ "</td>"
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='18%'>Departamento: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_paciente.getString(13)+ "</td>" 
										+ "<input type='hidden' name='admision' id='admision' value='"+ busqueda_paciente.getString(15)+"' />"
										+ "</tr>" 
										+ "</table>");
					}
				} else {
					out.print(verif + "|" +codpac+"|"+ "El paciente no tiene una admision activa");
				}
				rs.getStatement().close();;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Se obtiene Departamentos llamados de una funcion Furips.js ajax
		if (va.equals("2")) {
			rs = metodos.SQLDep(x);
			try {
				while (rs.next()) {
					out.println(rs.getString(1) + "_");
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Se llama el metodo InsertarDatosSitio
		if (va.equals("3")) {
			String dptocod_evento = null;
			String munnom_evento = null;
			String cod_evento = null;
			String Cod_furipsdatos_fk= null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs2= metodos.CodEvento(evento);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					munnom_evento = rs1.getString(1);
				}
				if (rs2.next()) {
					cod_evento = rs2.getString(1);
				}
				System.out.println("metodos.insertarDatosSitio("+fec_radicado+","+ num_radicado+","+ant_radicado+","+factura+","+tipo_documento+","+
						num_documento+","+condicion+","+cod_evento+","+ dir_ocurrencia+","+ fec_evento+","+ hora+","+dpto_evento+","+
						dptocod_evento+","+munnom_evento+","+mun_evento+","+zona+","+descripcion+","+fecha+","+hra+","+admision);
				metodos.insertarDatosSitio(fec_radicado, num_radicado,ant_radicado, factura, tipo_documento, num_documento,admision,
						condicion, cod_evento, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, munnom_evento, mun_evento, zona, descripcion,fecha, hra);
				
				rs.getStatement().close();
				rs1.getStatement().close();
				rs2 = metodos.SQLCod(num_radicado, fecha,hra);
				if (rs2.next()) {
					Cod_furipsdatos_fk = rs2.getString(1);
					out.println("<input type='hidden' name='furips' id='furips' value="+Cod_furipsdatos_fk+" />");
				}
				metodos.insertarfurips(Cod_furipsdatos_fk, usuario, fecha, hra, "created");
				if(num_radicado.equals("t"+num_documento)){
					metodos.ActualizarRadicado(Cod_furipsdatos_fk);
				}
				rs2.getStatement().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		// Se llama el metodo InsertarDatosAccidente,InsertarDatosPropietario e InsertarDatosConductor
		if (va.equals("4")) {
			String dptocod = null;
			String munnom = null;
			String dptocodconductor = null;
			String munnomconductor = null;
			rs1 = metodos.CodDep(DptoPropietario);
			rs2= metodos.CodMun(MunPropietario);
			rs3 = metodos.CodDep(dp);
			rs4= metodos.CodMun(mun);	
			try {
				while (rs1.next()) {
					dptocod = rs1.getString(1);
				}
				while (rs2.next()) {
					munnom = rs2.getString(1);
				}
				while (rs3.next()) {
					dptocodconductor = rs3.getString(1);
				}
				while (rs4.next()) {
					munnomconductor = rs4.getString(1);
				}
			    metodos.insertarDatosAccidente(codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, numpropietario, NDconductor);
				metodos.insertarDatosPropietario(tdpropietario, numpropietario, PrimerApellido, SegundoApellido, PrimerNombre, SegundoNombre, DirPropietario, DptoPropietario,dptocod, munnom,MunPropietario, TelPropietario);
				metodos.insertarDatosConductor(tdconductor, NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocodconductor, munnomconductor,mun, TelConductor);
				rs1.getStatement().close();
				rs2.getStatement().close();
				rs3.getStatement().close();
				rs4.getStatement().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		//Muestra los datos del propietario
		if (va.equals("5")) {
			busqueda_propietario = metodos.BuscarPropietario(TipDoc, NumDoc);
			String NombreCompleto = "";
			rs = metodos.BuscarPropietario(TipDoc1, NumDoc2);
			int verif = 0;
			String codpac = "0";
			try {
				if (rs.next()) {
					codpac = rs.getString(1);
					verif = 1;
					if (busqueda_propietario.next()) {
						out.print("1|"); 
						out.print(busqueda_propietario.getString(1) + "|");
						NombreCompleto = busqueda_propietario.getString(4) + " " + busqueda_propietario.getString(5) + " "+ busqueda_propietario.getString(6)+" "+busqueda_propietario.getString(7);
						out.print("<table width='100%'><tr><td><div align='center' class='style11' id='cabecera2' >DATOS DEL PROPIETARIO </div></td></tr></table>"
										+ "<table border='0'> "
										+ "<tr>"
										+ "<td width='15%'>Tipo de documento: </td>"
										+ "<td width='13%' align='left'>" + busqueda_propietario.getString(2)+ "</td>"
										+ "<td width='18%'>Numero de documento: </td>"
										+ "<td width='13%' align='left'>" + busqueda_propietario.getString(3)+ "</td>"
										+ "<td width='15%'>Nombre del Propietario: </td>"
										+ "<td width='60%' align='center' colspan='2'>"+ NombreCompleto+ "</td>" 
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='15%'>Direccion: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_propietario.getString(8)+ "</td>"
										+ "<td width='18%'>Departamento: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_propietario.getString(9)+ "</td>"
										+ "<td width='15%'>Municipio: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_propietario.getString(10)+ "</td>"
										+ "<td width='18%'>Telefono: </td> "
										+ "<td width='15%' align='left'>"+ busqueda_propietario.getString(11)+ "</td>"
										+ "</tr>"
										+ "</table>");
					}
				} else {
					out.print(verif + "|" + codpac);
				}
				rs.getStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Se llama el metodo InsertarDatosAccidente
		if (va.equals("6")) {
			metodos.insertarDatosAccidente(codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, propietario, conductor);
		}
		
		// Se llama el metodo InsertarDatosAccidente, InsertarDatosPropietario
		if (va.equals("7")) {
			String dptocod = null;
			String munnom = null;
			rs1 = metodos.CodDep(DptoPropietario);
			rs2= metodos.CodMun(MunPropietario);
				
			try {
				while (rs1.next()) {
					dptocod = rs1.getString(1);
				}
				while (rs2.next()) {
					munnom = rs2.getString(1);
				}
				System.out.println("metodos.insertarDatosAccidente("+codigo+","+ aseguramiento+","+marca+","+placa+","+servicio+","+CodAseguradora+","+NumPoliza+","+Autoridad+","+desde+","+hasta+","+Excedente+","+numpropietario+","+conductor);
				System.out.println("metodos.insertarDatosPropietario("+tdpropietario+","+numpropietario+","+PrimerApellido+","+SegundoApellido+","+PrimerNombre+","+SegundoNombre+","+DirPropietario+","+DptoPropietario+","+dptocod+","+munnom+","+MunPropietario+","+TelPropietario);
			    metodos.insertarDatosAccidente(codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, numpropietario, conductor);
				metodos.insertarDatosPropietario(tdpropietario, numpropietario, PrimerApellido, SegundoApellido, PrimerNombre, SegundoNombre, DirPropietario, DptoPropietario,dptocod, munnom,MunPropietario, TelPropietario);
				rs1.getStatement().close();
				rs2.getStatement().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		//Muestra los datos del conductor
		if (va.equals("8")) {
			busqueda_conductor = metodos.BuscarConductor(TD, ND);
			String NombreCompleto = "";
			rs = metodos.BuscarConductor(TD1, ND1);
			int verif = 0;
			String codpac = "0";
			try {
				if (rs.next()) {
					codpac = rs.getString(1);
					verif = 1;
					if (busqueda_conductor.next()) {
						out.print("1|"); 
						out.print(busqueda_conductor.getString(1) + "|");
						NombreCompleto = busqueda_conductor.getString(4) + " " + busqueda_conductor.getString(5) + " "+ busqueda_conductor.getString(6)+" "+busqueda_conductor.getString(7);
						out.print("<table width='100%'><tr><td><div align='center' class='style11' id='cabecera2' >DATOS DEL CONDUCTOR </div></td></tr></table>"
										+ "<table border='0'> "
										+ "<tr>"
										+ "<td width='15%'>Tipo de documento: </td>"
										+ "<td width='13%' align='left'>" + busqueda_conductor.getString(2)+ "</td>"
										+ "<td width='18%'>Numero de documento: </td>"
										+ "<td width='13%' align='left'>" + busqueda_conductor.getString(3)+ "</td>"
										+ "<td width='15%'>Nombre del Propietario: </td>"
										+ "<td width='60%' align='center' colspan='2'>"+ NombreCompleto+ "</td>" 
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='15%'>Direccion: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_conductor.getString(8)+ "</td>"
										+ "<td width='18%'>Departamento: </td>"
										+ "<td width='13%' align='left'>"+ busqueda_conductor.getString(9)+ "</td>"
										+ "<td width='15%'>Municipio: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_conductor.getString(10)+ "</td>"
										+ "<td width='18%'>Telefono: </td> "
										+ "<td width='15%' align='left'>"+ busqueda_conductor.getString(11)+ "</td>"
										+ "</tr>"
										+ "</table>");
					}
				} else {
					out.print(verif + "|" + codpac);
				}
				rs.getStatement().close();;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Se llama el metodo InsertarDatosAccidente, InsertarDatosConductor
		if (va.equals("9")) {
			String dptocod = null;
			String munnom = null;
			rs1 = metodos.CodDep(dp);
			rs2= metodos.CodMun(mun);
				
			try {
				while (rs1.next()) {
					dptocod = rs1.getString(1);
				}
				while (rs2.next()) {
					munnom = rs2.getString(1);
				}
			    metodos.insertarDatosAccidente(codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, propietario, NDconductor);
				metodos.insertarDatosConductor(tdconductor, NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod,munnom,mun, TelConductor);
				rs1.getStatement().close();
				rs2.getStatement().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		//Muestra los datos del medico
		if (va.equals("10")) {
			busqueda_medico = metodos.BuscarMedico(TDM, NDM);
			String NombreCompleto = "";
			rs = metodos.BuscarMedico(TDM1, NDM1);
			int verif = 0;
			String codpac = "0";
			try {
				if (rs.next()) {
					codpac = rs.getString(1);
					verif = 1;
					if (busqueda_medico.next()) {
						out.print("1|");
						out.print(busqueda_medico.getString(1) + "|");
						NombreCompleto = busqueda_medico.getString(5) + " " + busqueda_medico.getString(6) + " "+ busqueda_medico.getString(7)+" "+busqueda_medico.getString(8);
						out.print("<table width='100%'><tr><td><div align='center' class='style11' id='cabecera2' >DATOS DEL MEDICO </div></td></tr></table>"
										+ "<table border='0'> "
										+ "<tr>"
										+ "<td width='15%'>Tipo de documento: </td>"
										+ "<td width='13%' align='left'>" + busqueda_medico.getString(2)+ "</td>"
										+ "<td width='18%'>Numero de documento: </td>"
										+ "<td width='13%' align='left'>" + busqueda_medico.getString(3)+ "</td>"
										+ "<td width='15%'>Registro Medico: </td> "
										+ "<td width='13%' align='left'>"+ busqueda_medico.getString(4)+ "</td>"
										+ "</tr>"
										+ "<tr>" 
										+ "<td width='15%'>Nombre del Medico: </td>"
										+ "<td width='60%' align='center' colspan='2'>"+ NombreCompleto+ "</td>" 
										+ "</tr>"
										+ "</table>");
					}
				} else {
					out.print(verif + "|" + codpac);
				}
				rs.getStatement().close();;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Se llama los metodos ActualizarDatos,InsertarDatosCertificacion, InsertarDatosAmparo
		if (va.equals("11")) {
				metodos.ActualizarDatos(codigo,Remision,referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe,Transporte,placatrans,transdesde,transhasta,transporte,lugarvictima,NDM);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
		}
		
		//Se llama los metodos InsertarDatosRemision, ActualizarDatos, InsertarDatosMedico, InsertardatosTrasnporte, InsertarDatosCertificacion, InsertarDatosAmparo
		if (va.equals("12")) {
				metodos.ActualizarDatos(codigo,Remision,referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe,Transporte,placatrans,transdesde,transhasta,transporte,lugarvictima,NDMedico);
			    metodos.insertarDatosMedico(PAMedico,SAMedico,PNMedico,SNMedico, tdmedico,NDMedico,RegistroMedico);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
		}
		
		//Se llama los metodos InsertarDatosRemision, ActualizarDatos, InsertarDatosMedico, InsertarDatosCertificacion, InsertarDatosAmparo
		if (va.equals("13")) {
			    metodos.ActualizarDatosRemision(codigo,Remision,referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe,Transporte,NDMedico);
			    metodos.insertarDatosMedico(PAMedico,SAMedico,PNMedico,SNMedico, tdmedico,NDMedico,RegistroMedico);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
		}
		
		//Se llama los metodos InsertarDatosRemision, ActualizarDatos, InsertarDatosCertificacion, InsertarDatosAmparo
		if (va.equals("14")) {
			    metodos.ActualizarDatosRemision(codigo,Remision,referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe,Transporte,NDM);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
		}
		
		//Se llama los metodos ActualizarDatos, InsertarDatosMedico, InsertardatosTrasnporte, InsertarDatosCertificacion, InsertarDatosAmparo
		if (va.equals("15")) {
			    metodos.ActualizarDatosTrans(codigo,Remision,Transporte,placatrans,transdesde,transhasta,transporte,lugarvictima,NDMedico);
			    metodos.insertarDatosMedico(PAMedico,SAMedico,PNMedico,SNMedico, tdmedico,NDMedico,RegistroMedico);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
		}
		
		//Se llama los metodos ActualizarDatos, InsertardatosTrasnporte, InsertarDatosCertificacion, InsertarDatosAmparo
		if (va.equals("16")) {
				metodos.ActualizarDatosTrans(codigo,Remision,Transporte,placatrans,transdesde,transhasta,transporte,lugarvictima,NDM);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
		}
		
		//Se llama los metodos ActualizarDatos,InsertarDatosMedico, InsertarDatosCertificacion, InsertarDatosAmparo
		if (va.equals("17")) {
			    metodos.ActualizarDatosMed(codigo, NDMedico);
			    metodos.ActualizarDatosTransRem(codigo,Remision,Transporte,NDMedico);
			    metodos.insertarDatosMedico(PAMedico,SAMedico,PNMedico,SNMedico, tdmedico,NDMedico,RegistroMedico);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
		}
		
		//Se llama los metodos ActualizarDatos, InsertarDatosAmparo
		if (va.equals("18")) {
				metodos.ActualizarDatosMed(codigo, NDM);
				 metodos.ActualizarDatosTransRem(codigo,Remision,Transporte,NDM);
				metodos.insertarDatosCertificacion(codigo,fec_Ingreso,hora_ingreso, fec_Egreso,hora_egreso,DiagPrincipalIngreso, DiagPrincipalEgreso,OtroDiagIngreso,OtroDiagEgreso,OtroDiagIngreso2,OtroDiagEgreso2);
				metodos.insertarDatosAmparo(codigo,GastoMedicoFacturado,GastoMedicoFosyga,GastoTransFacturado,GastoTransFosyga);
			}
			
		//Mostrar todos los radicados
			if (va.equals("19")) {
				rs=metodos.Buscar(fec_inicial, fec_final);
				try {
					out.print("<table width='100%' border='1'><tr><td colspan='6'><div align='center' class='style11' id='cabecera2'>Furips </div></td></tr>");
					out.print("<tr><td>Fecha Radicado</td><td>Identificacion</td><td>Nombre del Paciente</td><td>Evento</td><td colspan='2'></td></tr>");
					while(rs.next()){
						out.print("<tr><td>"+rs.getString(6)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"</td><td>"+rs.getString(8)+"</td><td><a href='#' onclick='Editar("+rs.getString(9)+")'>Editar</a></td>" );
						if((rs.getString(8)).equals("Accidente de transito")){
						out.print("<td><a href='#' onclick='Reporte("+rs.getString(9)+")'>Generar Reporte</a></td></tr>");
						}else{
						out.print("<td><a href='#' onclick='ReporteOtro("+rs.getString(9)+")'>Generar Reporte</a></td></tr>");	
						}
					}
					out.print("</table>");
					rs.getStatement().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("20")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod = null;
			String muncod = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs2 = metodos.CodDep(DptoPropietario);
			rs3= metodos.CodMun(MunPropietario);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs2.next()) {
					dptocod = rs2.getString(1);
				}
				if (rs3.next()) {
					muncod = rs3.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatosPropietario(numpropietario, PrimerApellido, SegundoApellido, PrimerNombre, SegundoNombre, DirPropietario, DptoPropietario,dptocod, MunPropietario,muncod, TelPropietario);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente(Codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, numpropietario, NDconductor);
				metodos.ActualizarDatos(Codigo,Remision,referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe,Transporte,placatrans,transdesde,transhasta,transporte,lugarvictima,NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs2.getStatement().close();
				rs3.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("20a")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente2(Codigo, aseguramiento, Autoridad,Excedente, NDconductor);
				metodos.ActualizarDatos(Codigo,Remision,referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe,Transporte,placatrans,transdesde,transhasta,transporte,lugarvictima,NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("21")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod = null;
			String muncod = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs2 = metodos.CodDep(DptoPropietario);
			rs3= metodos.CodMun(MunPropietario);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs2.next()) {
					dptocod = rs2.getString(1);
				}
				if (rs3.next()) {
					muncod = rs3.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatosPropietario(numpropietario, PrimerApellido, SegundoApellido, PrimerNombre, SegundoNombre, DirPropietario, DptoPropietario,dptocod, MunPropietario,muncod, TelPropietario);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente(Codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, numpropietario, NDconductor);
				metodos.ActualizarDatosRemision(Codigo, Remision, referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe, Transporte, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs2.getStatement().close();
				rs3.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("21a")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente2(Codigo, aseguramiento, Autoridad, Excedente, NDconductor);
				metodos.ActualizarDatosRemision(Codigo, Remision, referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe, Transporte, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("22")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod = null;
			String muncod = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs2 = metodos.CodDep(DptoPropietario);
			rs3= metodos.CodMun(MunPropietario);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs2.next()) {
					dptocod = rs2.getString(1);
				}
				if (rs3.next()) {
					muncod = rs3.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatosPropietario(numpropietario, PrimerApellido, SegundoApellido, PrimerNombre, SegundoNombre, DirPropietario, DptoPropietario,dptocod, MunPropietario,muncod, TelPropietario);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente(Codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, numpropietario, NDconductor);
				metodos.ActualizarDatosTrans(Codigo, Remision, Transporte, placatrans, transdesde, transhasta, transporte, lugarvictima, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs2.getStatement().close();
				rs3.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("22a")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente2(Codigo, aseguramiento, Autoridad, Excedente,  NDconductor);
				metodos.ActualizarDatosTrans(Codigo, Remision, Transporte, placatrans, transdesde, transhasta, transporte, lugarvictima, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("23")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod = null;
			String muncod = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs2 = metodos.CodDep(DptoPropietario);
			rs3= metodos.CodMun(MunPropietario);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs2.next()) {
					dptocod = rs2.getString(1);
				}
				if (rs3.next()) {
					muncod = rs3.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatosPropietario(numpropietario, PrimerApellido, SegundoApellido, PrimerNombre, SegundoNombre, DirPropietario, DptoPropietario,dptocod, MunPropietario,muncod, TelPropietario);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente(Codigo, aseguramiento, marca,placa, servicio, CodAseguradora, NumPoliza, Autoridad,desde, hasta, Excedente, numpropietario, NDconductor);
				metodos.ActualizarDatosTransRem(Codigo, Remision, Transporte, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs2.getStatement().close();
				rs3.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("23a")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				System.out.println(NDconductor+","+PAConductor+","+SAConductor+","+PNConductor+","+SNConductor+","+DirConductor+","+dp+","+dptocod1+","+mun+","+muncod1+","+TelConductor);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				metodos.ActualizarDatosAccidente2(Codigo, aseguramiento, Autoridad, Excedente,  NDconductor);
				metodos.ActualizarDatosTransRem(Codigo, Remision, Transporte, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("24")){
			String dptocod_evento = null;
			String muncod_evento = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatos(Codigo,Remision,referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe,Transporte,placatrans,transdesde,transhasta,transporte,lugarvictima,NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("25")){
			String dptocod_evento = null;
			String muncod_evento = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatosRemision(Codigo, Remision, referencia, fec_remision, hora_remision, PrestadorRemite, CodInsRemite, ProfesionalRemite, CargoRemite, fec_aceptacion, hora_aceptacion, PrestadorRecibe, CodInsRecibe, ProfesionalRecibe, CargoRecibe, Transporte, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos 
		if(va.equals("26")){
			String dptocod_evento = null;
			String muncod_evento = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatosTrans(Codigo, Remision, Transporte, placatrans, transdesde, transhasta, transporte, lugarvictima, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar los datos del sitio, propietario, conductor, accidente, certificacion,medico y gastos
		if(va.equals("27")){
			String dptocod_evento = null;
			String muncod_evento = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				metodos.ActualizarDatosTransRem(Codigo, Remision, Transporte, NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(va.equals("28")){	
			String diag=req.getParameter("diag");
			String vble=req.getParameter("vble");
			try {
				out.print("<table>");
				rs=metodos.Autocompletacie10(diag);
				if(vble.equals("1")){
					while(rs.next()){
						out.print("<tr collspan='3'><td><a href='#' onclick='Seleccion(&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(2)+"&quot;)' >"+rs.getString(2)+"_"+rs.getString(3)+"</a> </td></tr>");
					}
				}else if (vble.equals("2")) {
					while(rs.next()){
						out.print("<tr collspan='3'><td><a href='#' onclick='Seleccion2(&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(2)+"&quot;)' >"+rs.getString(2)+"_"+rs.getString(3)+"</a> </td></tr>");
					}
				}else if (vble.equals("3")) {
					while(rs.next()){
						out.print("<tr collspan='3'><td><a href='#' onclick='Seleccion3(&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(2)+"&quot;)' >"+rs.getString(2)+"_"+rs.getString(3)+"</a> </td></tr>");
					}
				}else if (vble.equals("4")) {
					while(rs.next()){
						out.print("<tr collspan='3'><td><a href='#' onclick='Seleccion4(&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(2)+"&quot;)' >"+rs.getString(2)+"_"+rs.getString(3)+"</a> </td></tr>");
					}
				}else if (vble.equals("5")) {
					while(rs.next()){
						out.print("<tr collspan='3'><td><a href='#' onclick='Seleccion5(&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(2)+"&quot;)' >"+rs.getString(2)+"_"+rs.getString(3)+"</a> </td></tr>");
					}
				}else if (vble.equals("6")) {
					while(rs.next()){
						out.print("<tr collspan='3'><td><a href='#' onclick='Seleccion6(&quot;"+rs.getString(3)+"&quot;,&quot;"+rs.getString(2)+"&quot;)' >"+rs.getString(2)+"_"+rs.getString(3)+"</a> </td></tr>");
					}
				}
				out.print("</table>");
				rs.getStatement().close();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(va.equals("29")){
			String dptocod_evento = null;
			String muncod_evento = null;
			String dptocod1 = null;
			String muncod1 = null;
			rs = metodos.CodDep(dpto_evento);
			rs1= metodos.CodMun(mun_evento);
			rs4 = metodos.CodDep(dp);
			rs5= metodos.CodMun(mun);
			try {
				if (rs.next()) {
					dptocod_evento = rs.getString(1);
				}
				if (rs1.next()) {
					muncod_evento = rs1.getString(1);
				}
				if (rs4.next()) {
					dptocod1 = rs4.getString(1);
				}
				if (rs5.next()) {
					muncod1= rs5.getString(1);
				}
				metodos.ActualizarDatosSitio(Codigo,fec_radicado, num_radicado,ant_radicado, factura, condicion, dir_ocurrencia, fec_evento, hora,dpto_evento, dptocod_evento, mun_evento, muncod_evento, zona, descripcion);
				System.out.println("ActualizarDatosSitio:"+Codigo+" _ "+fec_radicado+" _ "+num_radicado+" _ "+ant_radicado+" _ "+factura+" _ "+condicion+" _ "+dir_ocurrencia+" _ "+fec_evento+" _ "+hora+" _ "+dpto_evento+" _ "+dptocod_evento+" _ "+mun_evento+" _ "+muncod_evento+" _ "+zona+" _ "+descripcion);
				metodos.ActualizarDatosConductor(NDconductor, PAConductor, SAConductor, PNConductor, SNConductor, DirConductor, dp,dptocod1, mun,muncod1, TelConductor);
				System.out.println("ActualizarDatosConductor:"+NDconductor+" _ "+PAConductor+" _ "+SAConductor+" _ "+PNConductor+" _ "+SNConductor+" _ "+DirConductor+" _ "+dp+" _ "+dptocod1+" _ "+mun+" _ "+muncod1+" _ "+TelConductor);
				metodos.ActualizarDatosAccidente2(Codigo, aseguramiento,Autoridad, Excedente, NDconductor);
				System.out.println("ActualizarDatosAccidente2:"+Codigo+" _ "+aseguramiento+" _ "+Autoridad+" _ "+Excedente+" _ "+NDconductor);
				metodos.ActualizarDatosTransRem(Codigo, Remision, Transporte, NDMedico);
				System.out.println("ActualizarDatosTransRem:"+Codigo+" _ "+Remision+" _ "+Transporte+" _ "+NDMedico);
				metodos.ActualizarDatosCertificacion(Codigo, fec_Ingreso, hora_ingreso, fec_Egreso, hora_egreso, DiagPrincipalIngreso, DiagPrincipalEgreso, OtroDiagIngreso, OtroDiagEgreso, OtroDiagIngreso2, OtroDiagEgreso2);
				System.out.println("ActualizarDatosCertificacion:"+Codigo+" _ "+fec_Ingreso+" _ "+hora_ingreso+" _ "+fec_Egreso+" _ "+hora_egreso+" _ "+DiagPrincipalIngreso+" _ "+DiagPrincipalEgreso+" _ "+OtroDiagIngreso+" _ "+OtroDiagEgreso+" _ "+OtroDiagIngreso2+" _ "+OtroDiagEgreso2);
				metodos.ActualizarDatosMedico(PAMedico, SAMedico, PNMedico, SNMedico, NDMedico, RegistroMedico);
				System.out.println("ActualizarDatosMedico:"+PAMedico+" _ "+SAMedico+" _ "+PNMedico+" _ "+SNMedico+" _ "+NDMedico+" _ "+RegistroMedico);
				metodos.ActualizarDatosAmparo(Codigo, GastoMedicoFacturado, GastoMedicoFosyga, GastoTransFacturado, GastoTransFosyga);
				System.out.println("ActualizarDatosAmparo:"+Codigo+" _ "+GastoMedicoFacturado+" _ "+GastoMedicoFosyga+" _ "+GastoTransFacturado+" _ "+GastoTransFosyga);
				metodos.insertarfurips(Codigo, usuario, fecha, hra, "update");
				rs.getStatement().close();
				rs1.getStatement().close();
				rs4.getStatement().close();
				rs5.getStatement().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}