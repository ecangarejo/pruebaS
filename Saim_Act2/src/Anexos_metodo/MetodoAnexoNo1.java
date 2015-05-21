package Anexos_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoAnexoNo1 {
	// consultar datos del paciente
	public java.sql.ResultSet BuscarPaciente(String TipoDocumento,String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT apac.tipo_documento, apac.numero_documento, apac.nombre, apac.primer_apellido, apac.segundo_apellido," +
								 "apac.pac_codigo_paciente,apac.fecha_nacimiento,apac.genero,apac.direccion, apac.telefono_celular," +
								 "apac.telefono_fijo, mun.nombre, dpto.nombre,ent.nombre_entidad, ent.regimen , apac.pac_codigo_paciente" +
								 " FROM adm_paciente apac, adm_convenio con, adm_entidad ent, adm_municipio mun, adm_departamento dpto " +
							 	 " WHERE apac.tipo_documento='"+ TipoDocumento + "' AND apac.numero_documento='"+ NumeroDocumento + "' " +
							 	 "AND apac.conv_numero_contrato_fk = con.conv_numero_contrato " +
							 	 "AND con.ent_nit_contratante_fk = ent.ent_nit AND mun.muni_cod=apac.mun_codigo_fk " +
							 	 "AND mun.dept_codigo_fk=dpto.dept_codigo ");
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarPaciente " + ex);
		}
		return rs;
	}

	// consultar la institucion prestadora del servicio
	public java.sql.ResultSet consultaranexos(String tp, String nd) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT anex.Codigo, anex.Fecha, anex.Hora FROM anexo1 anex, adm_paciente pac " +
					             "WHERE pac.tipo_documento= '"+ tp + "' AND pac.numero_documento='"+ nd + "' " +
					             "AND anex.CodPaciente_fk=pac.pac_codigo_paciente");
		} catch (Exception ex) {
			System.out.println("Error en Metodo Consultar Anexos TEcnicos No 1 " + ex);
		}
		return rs;
	}


	//metodo insertar anexo tecnico no 1
public void InsertarAnexoNo1(String codpaciente, String codusuario,String fecha, String hora, String inconsistencia, String cobertura,
		String papellido, String sapellido, String pnombre, String snombre,String tipodocumento, String numdocumento, String fecnacimiento,String observacion) {
	try {
		PreparedStatement ps = null;
		Conexion con = new Conexion();
		ps = con.conn.prepareStatement("INSERT INTO anexo1(CodPaciente_fk,CodUsuario_fk,Fecha,Hora,TipoInconsistencia,Cobertura,"
						+ "PrimerApellido,SegundoApellido,PrimerNombre,SegundoNombre,TipoDocumento,NumeroDocumento,"
						+ "FechaNacimiento,Observacion) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, codpaciente);
		ps.setString(2, codusuario);
		ps.setString(3, fecha);
		ps.setString(4, hora);
		ps.setString(5, inconsistencia);
		ps.setString(6, cobertura);
		ps.setString(7, papellido);
		ps.setString(8, sapellido);
		ps.setString(9, pnombre);
		ps.setString(10, snombre);
		ps.setString(11, tipodocumento);
		ps.setString(12, numdocumento);
		ps.setString(13, fecnacimiento);
		ps.setString(14, observacion);

		ps.execute();
		ps.close();
		con.cerrar();
	} catch (Exception ex) {
		ex.getMessage();
		System.out.print("Error Insert datos accidente " + ex);
	}
}
}
