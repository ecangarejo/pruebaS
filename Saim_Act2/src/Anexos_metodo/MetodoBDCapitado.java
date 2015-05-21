package Anexos_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoBDCapitado {
	/** CONSULTAS   **/
	// consultar datos del paciente
	public java.sql.ResultSet BuscarPaciente(String TipoDocumento,String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			System.out.println("tp: "+TipoDocumento+" ,num: "+NumeroDocumento);
			rs = st.executeQuery("SELECT apac.tipo_documento, apac.numero_documento, apac.nombre, apac.primer_apellido, apac.segundo_apellido," +
								 "apac.pac_codigo_paciente,apac.fecha_nacimiento,apac.genero,apac.direccion, apac.telefono_celular," +
								 "apac.telefono_fijo, mun.nombre, dpto.nombre,ent.nombre_entidad" +
								 " FROM adm_paciente apac, adm_convenio con, adm_entidad ent, adm_municipio mun, adm_departamento dpto " +
							 	 " WHERE apac.numero_documento='"+ NumeroDocumento + "' " +
							 	 "AND apac.conv_numero_contrato_fk = con.conv_numero_contrato " +
							 	 "AND con.ent_nit_contratante_fk = ent.ent_nit AND mun.muni_cod=apac.mun_codigo_fk " +
							 	 "AND mun.dept_codigo_fk=dpto.dept_codigo ");
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarPaciente " + ex);
		}
		return rs;
	}

	
	// consultar datos del paciente en convenio capita
	public java.sql.ResultSet BuscarPacienteCapita(String Codpac) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT * FROM fact_pcte_convenio_capita WHERE cod_pcte_fk="+Codpac);
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarPaciente " + ex);
		}
		return rs;
	}
	
	// consultar convenio de la entidad
	public java.sql.ResultSet BuscarCodConvenio(String CodEmpresa) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT cod_convenio FROM fact_convenios WHERE cod_entidad="+CodEmpresa);
		} catch (Exception ex) {
			System.out.println("Error en Metodo>>BuscarCodConvenio " + ex);
		}
		return rs;
	}
	
	
	/** INSERT   **/
	// se utiliza para insertar datos de los pacientes capitados
	public void insertarDatosPcteCapitado(String codpac, String conv,String urg, String hosp,String amb,String cex, String pyp) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			System.out.println("INSERT INTO fact_pcte_convenio_capita(cod_pcte_fk,cod_convenio_fk,urg,hosp,amb,"
					+ "cex, pyp,estado) "
					+ "VALUES("+codpac+","+conv+","+urg+","+hosp+","+amb+","+cex+","+pyp+")");
			ps = con.conn.prepareStatement("INSERT INTO fact_pcte_convenio_capita(cod_pcte_fk,cod_convenio_fk,urg,hos,amb,"
					+ "cex, pyp,estado) VALUES(?,?,?,?,?,?,?,'0')");
			ps.setString(1, codpac);
			ps.setString(2, conv);
			ps.setString(3, urg);
			ps.setString(4, hosp);
			ps.setString(5, amb);
			ps.setString(6, cex);
			ps.setString(7, pyp);
			
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.print("Error InsertDatosPcteCapitado " + ex);
		}
	}
	
	
	//se utiliza para insertar datos de los pacientes
	public void insertarDatosPcte(String tp, String num, String p_ape, String s_ape,String nom, String fec_nac,String gen, String mun,String zona, String dir,	String conv) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			System.out.println("INSERT INTO adm_paciente(tipo_documento,numero_documento,tipo_afiliacion,nivel_cotizante," +
                    "primer_apellido,segundo_apellido,nombre,genero,nacionalidad,direccion,telefono_fijo,telefono_oficina," +
                    "telefono_celular,ocu_codigo_fk,empresa_labora,zona_residencial,religion,estado_civil,raza,estrato,email," +
                    "mun_codigo_fk,fecha_nacimiento,conv_numero_contrato_fk,longitud,latitud,barrio) " +
                    "VALUES("+tp+","+num+",'','',"+p_ape+","+s_ape+","+nom+","+gen+",'COLOMBIA',"+dir+",'0','0','0','0','',"+zona+",'','Soltero(a)','','0','',"+mun+","+fec_nac+","+conv+",'','','')");
			ps = con.conn.prepareStatement("INSERT INTO adm_paciente(tipo_documento,numero_documento,tipo_afiliacion,nivel_cotizante," +
					                       "primer_apellido,segundo_apellido,nombre,genero,nacionalidad,direccion,telefono_fijo,telefono_oficina," +
					                       "telefono_celular,ocu_codigo_fk,empresa_labora,zona_residencial,religion,estado_civil,raza,estrato,email," +
					                       "mun_codigo_fk,fecha_nacimiento,conv_numero_contrato_fk,longitud,latitud,barrio) " +
					                       "VALUES(?,?,'','',?,?,?,?,'COLOMBIA',?,'0','0','0','0','',?,'','Soltero(a)','','0','',?,?,?,'','','')");
			ps.setString(1, tp);
			ps.setString(2, num);
			ps.setString(3, p_ape);
			ps.setString(4, s_ape);
			ps.setString(5, nom);
			ps.setString(6, gen);
			ps.setString(7, dir);
			ps.setString(8, zona);
			ps.setString(9, mun);
			ps.setString(10, fec_nac);
			ps.setString(11, conv);
			
			
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update insertarDatosPcte "+ ex);
		}
	}
	
	
	/** UPDATE   **/
	// Actualiza Datos del paciente
	public void ActualizarDatos(String codpac, String tp, String p_ape, String s_ape,String nom, String fec_nac,String gen, String mun,String zona, String dir,	String conv) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			System.out.println("UPDATE adm_paciente SET tipo_documento='"+tp+"', primer_apellido='"+p_ape+"', segundo_apellido='"+s_ape
					   +"', nombre='"+nom+"',fecha_nacimiento='"+fec_nac+"', genero='"+gen+"', mun_codigo_fk='"+mun+"', zona_residencial='"+zona
					   +"',direccion='"+dir+"',conv_numero_contrato_fk='"+conv+"' WHERE pac_codigo_paciente="+codpac);
			ps = con.conn.prepareStatement("UPDATE adm_paciente SET tipo_documento='"+tp+"', primer_apellido='"+p_ape+"', segundo_apellido='"+s_ape
										   +"', nombre='"+nom+"',fecha_nacimiento='"+fec_nac+"', genero='"+gen+"', mun_codigo_fk='"+mun+"', zona_residencial='"+zona
										   +"',direccion='"+dir+"',conv_numero_contrato_fk='"+conv+"' WHERE pac_codigo_paciente="+codpac);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update adm_paciente "+ ex);
		}
	}

	
	// Actualiza Datos del paciente capitado
	public void ActualizarDatosCapita(String codpac, String conv, String urg, String hosp,String amb, String cex,String pyp) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			System.out.println("UPDATE fact_pcte_convenio_capita SET cod_convenio_fk='"+conv+"', urg='"+urg+"', hos='"+hosp
					   +"', amb='"+amb+"',cex='"+cex+"', pyp='"+pyp+"', estado='0' WHERE cod_pcte_fk="+codpac);
			ps = con.conn.prepareStatement("UPDATE fact_pcte_convenio_capita SET cod_convenio_fk='"+conv+"', urg='"+urg+"', hos='"+hosp
										   +"', amb='"+amb+"',cex='"+cex+"', pyp='"+pyp+"', estado='0' WHERE cod_pcte_fk="+codpac);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update fact_pcte_convenio_capita "+ ex);
		}
	}


	// Actualiza Datos del paciente capitado
	public void ActualizarPcteConvenio(String conv) {
		try {
			PreparedStatement ps = null;
			Conexion con = new Conexion();
			System.out.println("UPDATE fact_pcte_convenio_capita SET estado='1' WHERE cod_convenio_fk="+conv);
			ps = con.conn.prepareStatement("UPDATE fact_pcte_convenio_capita SET estado='1' WHERE cod_convenio_fk="+conv);
			ps.execute();
			ps.close();
			con.cerrar();
		} catch (Exception ex) {
			ex.getMessage();
			System.out.println("Error Update ActualizarPcteConvenio "+ ex);
		}
	}

	
	
	
	
	
}