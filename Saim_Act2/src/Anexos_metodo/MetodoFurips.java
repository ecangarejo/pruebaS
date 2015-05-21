package Anexos_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoFurips {
	// consultar datos del paciente
	public java.sql.ResultSet BuscarPaciente(String TipoDocumento,String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT apac.tipo_documento, apac.numero_documento, apac.nombre, apac.primer_apellido, apac.segundo_apellido," +
					"apac.pac_codigo_paciente,apac.fecha_nacimiento,apac.genero,apac.direccion, apac.telefono_celular," +
					"apac.telefono_fijo, mun.nombre, dpto.nombre,ent.nombre_entidad,admin.adm_numero_ingreso " +
					"FROM adm_paciente apac, adm_convenio con, adm_entidad ent, adm_municipio mun, adm_departamento dpto,adm_admisiones admin " +
					"WHERE apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"' " +
					"AND apac.conv_numero_contrato_fk = con.conv_numero_contrato AND con.ent_nit_contratante_fk = ent.ent_nit " +
					"AND mun.muni_cod=apac.mun_codigo_fk AND mun.dept_codigo_fk=dpto.dept_codigo AND apac.pac_codigo_paciente=admin.pac_codigo_paciente_fk AND admin.estado='0' ");
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarPaciente " + ex);
		}
		return rs;
	}

	// consultar la institucion prestadora del servicio
	public java.sql.ResultSet BuscarInstitucion() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT nombre, nit, codprestador FROM empresa");
		} catch (Exception ex) {
			System.out.println("Error en MetodoInstitucion>>BuscarInstitucion " + ex);
		}
		return rs;
	}

	// consultar datos del propietario
	public java.sql.ResultSet BuscarPropietario(String TipDoc, String NumDoc) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT Codigo, TipoDocumento, NumeroDocumento, PrimerApellido, SegundoApellido, "
							   + "PrimerNombre, SegundoNombre, Direccion, Departamento, Municipio,Telefono "
							   + "FROM furips_datospropietario WHERE TipoDocumento='"+ TipDoc + "' AND NumeroDocumento=" + NumDoc);
		} catch (Exception ex) {
			System.out.println("Error en BuscarPropietario " + ex);
		}
		return rs;
	}

	// consultar datos del conductor
	public java.sql.ResultSet BuscarConductor(String TD, String ND) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT Codigo, TipoDocumento, Documento, PrimerApellido, SegundoApellido, PrimerNombre, "
							   + "SegundoNombre, Direccion, Departamento, Municipio,Telefono "
							   + "FROM furips_datosconductor WHERE TipoDocumento='"+ TD + "' AND Documento=" + ND);
		} catch (Exception ex) {
			System.out.println("Error en BuscarConductor " + ex);
		}
		return rs;
	}

	// consultar datos del medico
	public java.sql.ResultSet BuscarMedico(String TDM, String NDM) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM furips_datosmedico WHERE TipoDocumento='"+ TDM + "' AND NumeroDocumento=" + NDM);
		} catch (Exception ex) {
			System.out.println("Error en BuscarMedico " + ex);
		}
		return rs;
	}

	// consulta los eventos que se encuentran en la base de datos
	public java.sql.ResultSet SQLDep(String x) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("select CodEvenNaturaleza, Nombre from furips_eventonaturaleza where evento_fk="+ x);
		} catch (Exception ex) {
			System.out.println("Error MostrarEvento" + ex);
		}
		return rs;
	}

	// Insertar Datos Sitio del evento en la tabla furips_datos
	public void insertarDatosSitio(String fec_radicado, String num_radicado,String ant_radicado, String factura, String tipo_documento,
								   String num_documento, String admision, String condicion, String evento,String dir_ocurrencia, String fec_evento, String hora,
								   String dpto_evento, String dptocod_evento, String mun_evento,String muncod_evento, String zona, String descripcion,
								   String fecha_realizada, String hora_realizada) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO furips_datos(FechaRadicado,NumeroRadicado,AntNumeroRadicado,NumeroFactura,"
							+ "TipoDocumento_fk,NumeroDocumento_fk,Cod_Admision,CondicionAccidentado,Evento_fk,Direccion,Fecha,Hora,"
							+ "Departamento_fk,Cod_Departamento,Municipio,Cod_Municipio,Zona,Descripcion,Remision,"
							+ "TipoReferencia,FechaRemision,HoraRemision,PrestadorRemite,CodInscPrestadorRemite,"
							+ "ProfesionalRemite,CargoRemite,FechaAceptacion,HoraAceptacion,PrestadorRecibe,"
							+ "CodInscPrestadorRecibe,ProfesionalRecibe,CargoRecibe,Transporte,TransportePlaca,"
							+ "TransporteVictimaDesde,TransporteVictimaHasta,TipoTransporte,TransporteZona,DocMedico,fecha_realizada,hora_realizada) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'','','','','','','','','','','','','','','','','','','','',0,?,?)");
			ps.setString(1, fec_radicado);
			ps.setString(2, num_radicado);
			ps.setString(3, ant_radicado);
			ps.setString(4, factura);
			ps.setString(5, tipo_documento);
			ps.setString(6, num_documento);
			ps.setString(7, admision);
			ps.setString(8, condicion);
			ps.setString(9, evento);
			ps.setString(10, dir_ocurrencia);
			ps.setString(11, fec_evento);
			ps.setString(12, hora);
			ps.setString(13, dpto_evento);
			ps.setString(14, dptocod_evento);
			ps.setString(15, mun_evento);
			ps.setString(16, muncod_evento);
			ps.setString(17, zona);
			ps.setString(18, descripcion);
			ps.setString(19, fecha_realizada);
			ps.setString(20, hora_realizada);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Insert furips_datos " + ex);
		}
	}

	// busca el codigo donde se encuentre el numero de radicado, este numero es unico
	public java.sql.ResultSet SQLCod(String rad,String fecha,String hra) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT Codigo from furips_datos where NumeroRadicado='"+rad +"' AND  fecha_realizada='"+fecha+"' AND hora_realizada='"+hra+"'");
		} catch (Exception ex) {
			System.out.println("Error Numero radicado " + ex);
		}
		return rs;
	}

	// se utiliza para insertar los datos del vehiculo del accidente de transito
	// en la tabla furips_datosaccidente
	public void insertarDatosAccidente(String codigo, String aseguramiento,String marca, String placa, String servicio, String CodAseguradora,
			String NumPoliza, String Autoridad, String desde, String hasta,String Excedente, String numpropietario, String numconductor) {
		try {
			
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO furips_datosaccidente(DatosCodigo_fk,EstadoAseguramiento,Marca,Placa,TipoServicio,"
							+ "CodigoAseguradora,NumeroPoliza,IntervencionAutoridad,VigenciaDesde,VigenciaHasta,ExcedentePoliza,"
							+ "DocPropietario,DocConductor) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, codigo);
			ps.setString(2, aseguramiento);
			ps.setString(3, marca);
			ps.setString(4, placa);
			ps.setString(5, servicio);
			ps.setString(6, CodAseguradora);
			ps.setString(7, NumPoliza);
			ps.setString(8, Autoridad);
			ps.setString(9, desde);
			ps.setString(10, hasta);
			ps.setString(11, Excedente);
			ps.setString(12, numpropietario);
			ps.setString(13, numconductor);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Insert datos accidente " + ex);
		}
	}

	// se utiliza para insertar los datos del propietario del vehiculo del accidente de transito en la tabla furips_datospropietario
	public void insertarDatosPropietario(String tdpropietario,String numpropietario, String PrimerApellido,String SegundoApellido, 
										 String PrimerNombre, String SegundoNombre,String DirPropietario, String DptoPropietario, 
										 String dptocod,String MunPropietario, String muncod, String TelPropietario) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO furips_datospropietario(TipoDocumento,NumeroDocumento,PrimerApellido,SegundoApellido,"
							+ "PrimerNombre, SegundoNombre,Direccion,Departamento,CodDepartamento,Municipio,CodMunicipio,Telefono) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, tdpropietario);
			ps.setString(2, numpropietario);
			ps.setString(3, PrimerApellido);
			ps.setString(4, SegundoApellido);
			ps.setString(5, PrimerNombre);
			ps.setString(6, SegundoNombre);
			ps.setString(7, DirPropietario);
			ps.setString(8, DptoPropietario);
			ps.setString(9, dptocod);
			ps.setString(10, MunPropietario);
			ps.setString(11, muncod);
			ps.setString(12, TelPropietario);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error Insert datos propietario " + ex);
		}
	}

	// se utiliza para insertar los datos del conductor del vehiculo del accidente de transito en la tabla furips_datosconductor
	public void insertarDatosConductor(String tdconductor, String numconductor,String PrimerApellido, String SegundoApellido, 
									   String PrimerNombre,String SegundoNombre, String Dir, String Dpto, String dptocod,
									   String Mun, String muncod, String Tel) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			System.out.println("INSERT INTO furips_datosconductor(TipoDocumento,Documento,PrimerApellido,SegundoApellido,"
					+ "PrimerNombre, SegundoNombre,Direccion,Departamento,CodDepartamento,Municipio,CodMunicipio,Telefono) "
					+ "VALUES("+tdconductor+","+numconductor+","+PrimerApellido+","+SegundoApellido+","+PrimerNombre+","+SegundoNombre+","+Dir+","+Dpto+","+dptocod+","+Mun+","+muncod+","+Tel+")");
			ps = con.conn.prepareStatement("INSERT INTO furips_datosconductor(TipoDocumento,Documento,PrimerApellido,SegundoApellido,"
							+ "PrimerNombre, SegundoNombre,Direccion,Departamento,CodDepartamento,Municipio,CodMunicipio,Telefono) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, tdconductor);
			ps.setString(2, numconductor);
			ps.setString(3, PrimerApellido);
			ps.setString(4, SegundoApellido);
			ps.setString(5, PrimerNombre);
			ps.setString(6, SegundoNombre);
			ps.setString(7, Dir);
			ps.setString(8, Dpto);
			ps.setString(9, dptocod);
			ps.setString(10, Mun);
			ps.setString(11, muncod);
			ps.setString(12, Tel);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error InsertDatosConductor " + ex);
		}
	}

	// busca el codigo del departamento
	public java.sql.ResultSet CodDep(String nombre) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT dept_codigo FROM adm_departamento WHERE nombre='"+ nombre + "'");
		} catch (Exception ex) {
			System.out.println("Error select departamento " + ex);
		}
		return rs;
	}

	// busca el todos los departamentos
	public java.sql.ResultSet Dep() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM adm_departamento WHERE pais_codigo_fk=1");
		} catch (Exception ex) {
			System.out.println("Error select departamentos " + ex);
		}
		return rs;
	}

	// busca el codigo del municipio
	public java.sql.ResultSet CodMun(String nombre) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(" SELECT nombre FROM adm_municipio WHERE muni_cod='"+ nombre + "'");
		} catch (Exception ex) {
			System.out.println("Error select municipio " + ex);
		}
		return rs;
	}
	
	// busca el codigo del municipio
	public java.sql.ResultSet CodEvento(String evento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(" SELECT Nombre FROM furips_eventonaturaleza WHERE CodEvenNaturaleza="+evento);
		} catch (Exception ex) {
			System.out.println("Error cod evento " + ex);
		}
		return rs;
	}

	// Actualiza Datos de la remision y transporte en la tabla furips_datos
	public void ActualizarDatos(String codigo, String Remision,String referencia, String fec_remision, String hora_remision,String PrestadorRemite, 
								String CodInsRemite,String ProfesionalRemite, String CargoRemite,String fec_aceptacion, String hora_aceptacion,
								String PrestadorRecibe, String CodInsRecibe,String ProfesionalRecibe, String CargoRecibe, String Transporte,
								String placatrans, String transdesde, String transhasta,String transporte, String lugarvictima, String medico) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datos SET Remision='"+ Remision + "', TipoReferencia='" + referencia + "', "
										 + "FechaRemision='" + fec_remision + "', HoraRemision='"+ hora_remision + "'," +
										   "PrestadorRemite='" + PrestadorRemite + "', " + "CodInscPrestadorRemite='" + CodInsRemite
										 + "', ProfesionalRemite='" + ProfesionalRemite + "', FechaAceptacion='" + fec_aceptacion
										 + "', HoraAceptacion='" + hora_aceptacion + "', PrestadorRecibe='" + PrestadorRecibe
										 + "', CodInscPrestadorRecibe='" + CodInsRecibe + "', ProfesionalRecibe='" + ProfesionalRecibe
										 + "' ,CargoRecibe='" + CargoRecibe + "', " 
										 + "Transporte='"+ Transporte + "', " + "TransportePlaca='" + placatrans
										 + "',TransporteVictimaDesde='" + transdesde + "', TransporteVictimaHasta='" + transhasta
										 + "',TipoTransporte='" + transporte + "', TransporteZona='" + lugarvictima + "'," 
										 + "DocMedico='"+ medico + "' where Codigo=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update furips_datosRemisionTransporte "+ ex);
		}
	}

	// Actualiza los datos de Remision en la tabla furips_datos
	public void ActualizarDatosRemision(String codigo, String remision,String referencia, String fec_remision, String hora_remision,
										String PrestadorRemite, String CodInsRemite,String ProfesionalRemite, String CargoRemite,
										String fec_aceptacion, String hora_aceptacion,String PrestadorRecibe, String CodInsRecibe,
										String ProfesionalRecibe, String CargoRecibe, String transporte,String medico) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datos SET Remision='"+ remision + "', TipoReferencia='" + referencia
										 + "',FechaRemision='" + fec_remision + "',HoraRemision='" + hora_remision 
										 + "',PrestadorRemite='"+ PrestadorRemite + "'," + "CodInscPrestadorRemite='"+ CodInsRemite 
										 + "',ProfesionalRemite='"+ ProfesionalRemite + "'," + "CargoRemite='" + CargoRemite
										 + "',FechaAceptacion='" + fec_aceptacion + "',HoraAceptacion='" + hora_aceptacion + "',"
										 + "PrestadorRecibe='" + PrestadorRecibe+ "',CodInscPrestadorRecibe='" + CodInsRecibe + "',"
										 + "ProfesionalRecibe='" + ProfesionalRecibe+ "',CargoRecibe='" + CargoRecibe 
										 + "',Transporte='"	+ transporte + "'," + "DocMedico='" + medico
										 + "' where Codigo=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update furips_datosRemision " + ex);
		}
	}

	// Actualiza lod datos del transporte en la table furips_datos
	public void ActualizarDatosTrans(String codigo, String remision,String Transporte, String placatrans, String transdesde,String transhasta, 
									 String transporte, String lugarvictima,String medico) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datos SET Remision='"+ remision + "',Transporte='" + Transporte + "',"
										 + "TransportePlaca='" + placatrans + "',TransporteVictimaDesde='" + transdesde + "',"
										 + "TransporteVictimaHasta='" + transhasta+ "',TipoTransporte='" + transporte + "',"
										 + "TransporteZona='" + lugarvictima + "'," + "DocMedico='"+ medico + "' where Codigo=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update furips_datosTransporte " + ex);
		}
	}

	// Actualiza la Cedula del Medico en la tabla furips_datos
	public void ActualizarDatosMed(String codigo, String medico) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datos SET DocMedico='"+ medico + "' where Codigo=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update furips_datoscedmedico " + ex);
		}
	}

	// Insertar Datos Sitio del evento en la tabla furips_datosmedico
	public void insertarDatosMedico(String PAMedico,String SAMedico,String PNMedico,String SNMedico,String tdmedico,String NDMedico,String RegistroMedico) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO furips_datosmedico(TipoDocumento,NumeroDocumento,NumRegistroMedico,"
										 + "PrimerApellido,SegundoApellido,PrimerNombre,SegundoNombre) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, tdmedico);
			ps.setString(2, NDMedico);
			ps.setString(3, RegistroMedico);
			ps.setString(4, PAMedico);
			ps.setString(5, SAMedico);
			ps.setString(6, PNMedico);
			ps.setString(7, SNMedico);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Insert furips_datosmedico " + ex);
		}
	}

	// Insertar Datos de la certificacion medica en la tabla furips_datoscertificacion
	public void insertarDatosCertificacion(String codigo, String fec_Ingreso,String hora_ingreso, String fec_Egreso, String hora_egreso,
										   String DiagPrincipalIngreso, String DiagPrincipalEgreso,String OtroDiagIngreso, String OtroDiagEgreso,
										   String OtroDiagIngreso2, String OtroDiagEgreso2) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO furips_datoscertificacion(DatosCodigo_fk,FechaIngreso,HoraIngreso,FechaEgreso,"
										 + "HoraEgreso,CodigoPrincipalIngreso,OtroCodigoIngreso,OtroCodigoIngresos,CodigoPrincipalEgreso,"
										 + "OtroCodigoEgreso,OtroCodigoEgresos) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, codigo);
			ps.setString(2, fec_Ingreso);
			ps.setString(3, hora_ingreso);
			ps.setString(4, fec_Egreso);
			ps.setString(5, hora_egreso);
			ps.setString(6, DiagPrincipalIngreso);
			ps.setString(7, OtroDiagIngreso);
			ps.setString(8, OtroDiagIngreso2);
			ps.setString(9, DiagPrincipalEgreso);
			ps.setString(10, OtroDiagEgreso);
			ps.setString(11, OtroDiagEgreso2);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Insert furips_datoscertificacion " + ex);
		}
	}

	// Insertar Datos del amparo q reclama en la tabla furips_datosamparo
	public void insertarfurips(String Cod_furipsdatos_fk,String Cod_usuario_fk,String fecha,String hora, String estado) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO furips(Cod_furipsdatos_fk,"
										 + "Cod_usuario_fk,fecha,hora,estado) VALUES(?,?,?,?,?)");
			ps.setString(1,Cod_furipsdatos_fk);
			ps.setString(2, Cod_usuario_fk);
			ps.setString(3, fecha);
			ps.setString(4, hora);
			ps.setString(5, estado);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Insert furips " + ex);
		}
	}
	// Insertar Datos del amparo q reclama en la tabla furips_datosamparo
	public void insertarDatosAmparo(String codigo,String GastoMedicoFacturado,String GastoMedicoFosyga,String GastoTransFacturado,String GastoTransFosyga) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("INSERT INTO furips_datosamparo(DatosCodigo_fk,GastoMedicoQuirurgicoFact,"
										 + "GastoMedicoQuirurgicoFosy,GastoTransporteFact,GastoTransporteFosy) VALUES(?,?,?,?,?)");
			ps.setString(1, codigo);
			ps.setString(2, GastoMedicoFacturado);
			ps.setString(3, GastoMedicoFosyga);
			ps.setString(4, GastoTransFacturado);
			ps.setString(5, GastoTransFosyga);

			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Insert furips_datosamparo " + ex);
		}
	}

	// busca algunos datos de las tabla adm_paciente y furips_datos
	public java.sql.ResultSet Buscar(String fec_inicial, String fec_final) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(" SELECT pac.tipo_documento, pac.numero_documento, pac.nombre,pac.primer_apellido,pac.segundo_apellido," +
					"fd.FechaRadicado, fd.NumeroRadicado, fd.Evento_fk, fd.Codigo " +
					"FROM adm_paciente pac, furips_datos fd, furips f " +
					"WHERE pac.tipo_documento=fd.TipoDocumento_fk AND f.Cod_furipsdatos_fk=fd.Codigo " +
					"AND pac.numero_documento=fd.NumeroDocumento_fk AND f.estado='created' AND f.fecha BETWEEN '"+fec_inicial+"' AND '"+fec_final+"' GROUP BY fd.Codigo");
		} catch (Exception ex) {
			System.out.println("Error select buscar furips " + ex);
		}
		return rs;
	}

	// Muestra todas las aseguradoras
	public java.sql.ResultSet MostrarAseguradora() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM furips_datosaseguradora");
		} catch (Exception ex) {
			System.out.println("Error select mostrar datos aseguradora" + ex);
		}
		return rs;
	}

	// muestra todos los diagnosticos de la tabla cie10
	public java.sql.ResultSet MostrarDiagnosticos() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT nombre,codigoCIE FROM cie10");
		} catch (Exception ex) {
			System.out.println("Error select mostrar datos aseguradora" + ex);
		}
		return rs;
	}

	// Actualiza el numero de radicado
	public void ActualizarRadicado(String codigo) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datos SET NumeroRadicado=' ' where Codigo="+ codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update num radicado " + ex);
		}
	}

	// Actualiza el campo de remision y transporte y doc medico
	public void ActualizarDatosTransRem(String codigo, String Remision,String Transporte, String medico) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datos SET Remision='"+ Remision + "',Transporte='" + Transporte + "',"
										 + "DocMedico='" + medico + "' where Codigo=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update furips_datosTransRem " + ex);
		}
	}

	// Actualiza datos del sitio
	public void ActualizarDatosSitio(String codigo, String fec_radicado,String num_radicado, String ant_radicado, String factura,String condicion, 
									 String dir_ocurrencia, String fec_evento,String hora, String dpto_evento, String dptocod_evento,
									 String mun_evento, String muncod_evento, String zona,String descripcion) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datos SET FechaRadicado='"+ fec_radicado + "',NumeroRadicado='"+ num_radicado + "'," + 
										   "AntNumeroRadicado='"+ ant_radicado + "', NumeroFactura='" + factura + "'," + 
										   "CondicionAccidentado='" + condicion+ "', Direccion='" + dir_ocurrencia + "',"+ 
										   "Fecha='" + fec_evento + "', Hora='" + hora+ "'," + 
										   "Departamento_fk='" + dpto_evento+ "', Cod_Departamento='" + dptocod_evento + "',"+ 
										   "Municipio='" + mun_evento + "', Cod_Municipio='"+ muncod_evento + "'," + 
										   "Zona='" + zona+ "', Descripcion='" + descripcion + "'"+ 
										   " where Codigo=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update furips_datos=" + ex);
		}
	}

	// actualiza los datos del accidente
	public void ActualizarDatosAccidente(String codigo, String aseguramiento,String marca, String placa, String servicio, String CodAseguradora,
										 String NumPoliza, String Autoridad, String desde, String hasta,String Excedente, String numpropietario, 
										 String numconductor) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datosaccidente SET EstadoAseguramiento='"+ aseguramiento+ "',Marca='"+ marca
										 + "',Placa='"+ placa+ "',TipoServicio='"+ servicio+ "',CodigoAseguradora='"+ CodAseguradora
										 + "',NumeroPoliza='"+ NumPoliza+ "',IntervencionAutoridad='"+ Autoridad+ "',"
										 + "VigenciaDesde='"+ desde+ "',VigenciaHasta='"+ hasta	+ "',ExcedentePoliza='"	+ Excedente
										 + "',DocPropietario='"+ numpropietario	+ "',DocConductor='"+ numconductor
										 + "' where DatosCodigo_fk=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error datos accidente " + ex);
		}
	}

	
	// actualiza los datos del accidente
	public void ActualizarDatosAccidente2(String codigo, String aseguramiento, String Autoridad, String Excedente, String numconductor) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datosaccidente SET EstadoAseguramiento='"+ aseguramiento+ "'," +
					                       "IntervencionAutoridad='"+ Autoridad+ "',ExcedentePoliza='"	+ Excedente
										 + "',DocConductor='"+ numconductor
										 + "' where DatosCodigo_fk=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error datos accidente 2 " + ex);
		}
	}
	// se actualiza los datos del propietario del vehiculo
	public void ActualizarDatosPropietario(String numpropietario,String PrimerApellido, String SegundoApellido, String PrimerNombre,
										   String SegundoNombre, String DirPropietario,String DptoPropietario, String dptocod, 
										   String MunPropietario,String muncod, String TelPropietario) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datospropietario SET PrimerApellido='"+ PrimerApellido+ "',SegundoApellido='"+ SegundoApellido
										 + "',PrimerNombre='"+ PrimerNombre	+ "', SegundoNombre='"+ SegundoNombre+ "',Direccion='"+ DirPropietario
										 + "',Departamento='"+ DptoPropietario+ "',CodDepartamento='"+ dptocod+ "',Municipio='"+ MunPropietario
										 + "',CodMunicipio='"+ muncod+ "',Telefono='"+ TelPropietario
										 + "' where NumeroDocumento="+ numpropietario);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error update datos propietario" + ex);
		}
	}

	// actualiza los datos del conductor del vehiculo
	public void ActualizarDatosConductor(String numconductor,String PrimerApellido, String SegundoApellido, String PrimerNombre,
										 String SegundoNombre, String Dir, String Dpto, String dptocod,String Mun, String muncod, String Tel) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datosconductor SET PrimerApellido='"+ PrimerApellido+ "',SegundoApellido='"+ SegundoApellido
										 + "',PrimerNombre='"+ PrimerNombre+ "', SegundoNombre='"+ SegundoNombre+ "',Direccion='"+ Dir
										 + "',Departamento='"+ Dpto	+ "',CodDepartamento='"+ dptocod+ "',Municipio='"+ Mun
										 + "',CodMunicipio='"+ muncod+ "',Telefono='"+ Tel
										 + "' where Documento="+ numconductor);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error update datos conductor" + ex);
		}
	}

	// actualiza los datos de la certificacion medica
	public void ActualizarDatosCertificacion(String codigo, String fec_Ingreso,String hora_ingreso, String fec_Egreso, String hora_egreso,
											 String DiagPrincipalIngreso, String DiagPrincipalEgreso,String OtroDiagIngreso, String OtroDiagEgreso,
											 String OtroDiagIngreso2, String OtroDiagEgreso2) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datoscertificacion SET FechaIngreso='"+ fec_Ingreso+ "',HoraIngreso='"+ hora_ingreso
										 + "',FechaEgreso='"+ fec_Egreso+ "', HoraEgreso='"+ hora_egreso
										 + "',CodigoPrincipalIngreso='"+ DiagPrincipalIngreso+ "',OtroCodigoIngreso='"+ OtroDiagIngreso
										 + "',OtroCodigoIngresos='"+ OtroDiagIngreso2+ "',CodigoPrincipalEgreso='"+ DiagPrincipalEgreso
										 + "',OtroCodigoEgreso='"+ OtroDiagEgreso+ "',OtroCodigoEgresos='"+ OtroDiagEgreso2
										 + "' where DatosCodigo_fk=" + codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error update datos certificacion" + ex);
		}
	}

	// actualiza los datos del medico
	public void ActualizarDatosMedico(String PAMedico, String SAMedico,String PNMedico, String SNMedico, String NDMedico,String RegistroMedico) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datosmedico SET NumRegistroMedico='"+ RegistroMedico+ "',PrimerApellido='"+ PAMedico
										 + "',SegundoApellido='"+ SAMedico+ "', PrimerNombre='"+ PNMedico+ "',SegundoNombre='"+ SNMedico
										 + "' where NumeroDocumento=" + NDMedico);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error update furips_datosmedico " + ex);
		}
	}

	// Actualiza los Datos del amparo q reclama en la tabla furips_datosamparo
	public void ActualizarDatosAmparo(String codigo,String GastoMedicoFacturado, String GastoMedicoFosyga,
			String GastoTransFacturado, String GastoTransFosyga) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			ps = con.conn.prepareStatement("UPDATE furips_datosamparo SET GastoMedicoQuirurgicoFact='"+ GastoMedicoFacturado
										 + "',GastoMedicoQuirurgicoFosy='"+ GastoMedicoFosyga+ "',GastoTransporteFact='"+ GastoTransFacturado
										 + "',GastoTransporteFosy='"+ GastoTransFosyga+ "' where DatosCodigo_fk="+ codigo);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error update furips_datosamparo " + ex);
		}
	}
	
	public java.sql.ResultSet Autocompletacie10(String diag){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cie10 WHERE nombre LIKE '%"+diag+"%' or codigoCIE LIKE'"+diag+"%'  limit 5");
        }catch(Exception ex){
        	System.out.println("Error en MetodoFurips>>AutocompletaCie10 "+ex);
        }	
        return rs;
    }
	
}