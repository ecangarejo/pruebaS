package cat_cateterismo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Vector;
import sun.misc.BASE64Decoder;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import adm_logica.Conexion;

public class metodos_cateterismo {
	// consultar arterias que se bifurcan
	public Vector <String> arteriasbifurcadas(){
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT DISTINCT " +
					"art.abreviacion "+
					" FROM "+
					"cat_rama_bifurcada bif,"+
					"cat_arteria art "+
					"WHERE "+
					"bif.id_arteria_fk = art.id_arteria"
					);
			while(rs.next()) {
				
				mivector.addElement(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return mivector;
	}
	
	public int idlocalizacionlesionarteria(String nombre_localizacion){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					
					"SELECT les.id_localizacion"+
					" FROM " +
					"cat_localizacion_lesion les"+
					" WHERE "+
					"les.nombre_localizacion ='"+ nombre_localizacion+"'"
					);
			rs.next();
			if (rs.getString(1) != null) {
			   	valor = Integer.parseInt(rs.getString(1));
			}
			
		} catch (Exception ex) {
			ex.getMessage();
		}
		return valor;
	}
	
	
	public Vector <String> tipolesionesArteriaActual(String abreviacionArteriaActual){
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
		  			 "SELECT les.nombre_localizacion "+
			         "FROM "+
			         "cat_arteria cat,"+
			         "cat_localizacion_lesion les,"+
			         "cat_segmentos_arterias seg "+
			         "WHERE "+
			         "cat.abreviacion = '"+abreviacionArteriaActual+"'"+
			         "AND seg.id_arteria_fk =cat.id_arteria "+
			         "AND seg.id_localizacion_fk=  les.id_localizacion"
					);
			while(rs.next()) {
				
				mivector.addElement(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return mivector;
	}
	
	public Vector <String> tipolesionesArteriasGeneral(){
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		String cadena = "";
		int numeroArterias = numeroArteriasRegistradas();
		for (int i = 0 ; i < numeroArterias; i++ ){
			cadena ="";
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT les.nombre_localizacion "+
				        " FROM "+ 
				        " cat_arteria cat, "+
				        " cat_localizacion_lesion les, "+
				        " cat_segmentos_arterias seg "+
				        " WHERE "+
				        " cat.id_arteria  = '" +(i+1)+"'"+
				        " AND  seg.id_arteria_fk =cat.id_arteria "+
				        " AND seg.id_localizacion_fk=  les.id_localizacion"
						);
			
				
			
				while(rs.next()) {
					
					cadena +=rs.getString(1)+"-";
				}
				cadena = cadena.substring(0, cadena.length()-1);
				mivector.add(cadena);
			
			} catch (Exception ex) {
				ex.getMessage();
			}
			
		}
	
		return mivector;
	}
	
	
	
	public int numeroArteriasRegistradas(){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT COUNT(id_arteria) FROM cat_arteria");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}
	
	
	
	/*****************************************************/
	/***** Metodos de Busqueda de pacientes y Admicion *****/
	/*****************************************************/

	public java.sql.ResultSet BuscarAdmisiones(String TipoDocumento,
			String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "apac.nombre,"
					+ "apac.primer_apellido,"
					+ "apac.segundo_apellido,"
					+ "apac.pac_codigo_paciente,"
					+ "(YEAR(CURDATE())-YEAR(pac.fecha_nacimiento))- (RIGHT(CURDATE(),5)<RIGHT(pac.fecha_nacimiento,5))AS 'EDAD',"
					+ "ent.nombre_entidad,"
			    	+ "aad.adm_numero_ingreso,"
					+ "aad.fecha_registro,"
					+ "aad.hora_registro,"
					+ "aad.estado "
					+ "FROM "
					+ "adm_paciente apac,"
					+ "adm_convenio con,"
					+ "adm_entidad ent,"
					+ "adm_admisiones aad "
					+ "WHERE "
					+ "apac.tipo_documento='"
					+ TipoDocumento
					+ "'"
					+ " AND apac.numero_documento='"
					+ NumeroDocumento
					+ "'"
					+ " AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk"
					+ " AND apac.conv_numero_contrato_fk = con.conv_numero_contrato"
					+ " AND con.ent_nit_contratante_fk = ent.ent_nit"
			        + " ORDER BY aad.adm_numero_ingreso DESC " + "LIMIT 1");
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		return rs;
	}

	public java.sql.ResultSet buscarInformesCateterismosRealizados(String TipoDocumento,String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT " +
			        "CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido),"+
					"inf.id_informe_diagnostico_hemodinamia," +
					"CONCAT(inf.fecha_diagnostico,'/',inf.hora_diagnostico), " +
					"pac_codigo_paciente "+
					"FROM " +
					"adm_paciente pac," +
					"cat_informe_diagnostico_hemodinamia inf " +
					"WHERE " +
					"pac.tipo_documento ='" +TipoDocumento+"' "+
					"AND pac.numero_documento='" +NumeroDocumento+"' "+
					"AND pac.pac_codigo_paciente = inf.id_paciente"); 
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		return rs;
	}
	
	
	public java.sql.ResultSet buscarInformesCateterismosRealizadosParaModificar(String TipoDocumento,String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT " +
			        "CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido),"+
					"inf.id_informe_diagnostico_hemodinamia," +
					"CONCAT(inf.fecha_diagnostico,'/',inf.hora_diagnostico) " +
					
					
					"FROM " +
					"adm_paciente pac," +
					"cat_informe_diagnostico_hemodinamia inf " +
					"WHERE " +
					"pac.tipo_documento ='" +TipoDocumento+"' "+
					"AND pac.numero_documento='" +NumeroDocumento+"' "+
					"AND pac.pac_codigo_paciente = inf.id_paciente"); 
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		return rs;
	}
	
	
	public java.sql.ResultSet BuscarPaciente(String TipoDocumento,
			String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "apac.nombre,"
					+ "apac.primer_apellido,"
					+ "apac.segundo_apellido,"
					+ "apac.pac_codigo_paciente,"
					+ "(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))- (RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento,5))AS 'Edad',"
					+ "ent.nombre_entidad"
					+
					// ",aad.adm_numero_ingreso," +
					// "aad.fecha_registro," +
					// "aad.hora_registro," +
					// "aad.estado "+
					" FROM "
					+ "adm_paciente apac,"
					+ "adm_convenio con,"
					+ "adm_entidad ent"
					+
					// "," +
					// " adm_admisiones aad "+
					" WHERE "
					+ "apac.tipo_documento='"
					+ TipoDocumento
					+ "' "
					+ "AND apac.numero_documento='"
					+ NumeroDocumento
					+ "' "
					+ "AND apac.conv_numero_contrato_fk = con.conv_numero_contrato "
					+ "AND con.ent_nit_contratante_fk = ent.ent_nit"
			// +" AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk"+
			// " ORDER BY aad.adm_numero_ingreso DESC "+
			// "LIMIT 1"
			);
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		return rs;
	}

	public java.sql.ResultSet Buscar_Paciente_Por_Codigo(
			String id_codigo_Paciente) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT "
					+ "apac.nombre,"
					+ "apac.primer_apellido,"
					+ "apac.segundo_apellido,"
					+
					// "apac.pac_codigo_paciente," +
					"(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))- (RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento,5)) AS 'EDAD',"
					+ "ent.nombre_entidad"
					+
					// ",aad.adm_numero_ingreso," +
					// "aad.fecha_registro," +
					// "aad.hora_registro," +
					// "aad.estado "+
					" FROM "
					+ "adm_paciente apac,"
					+ "adm_convenio con,"
					+ "adm_entidad ent"
					+
					// "," +
					// " adm_admisiones aad "+
					" WHERE " + "apac.pac_codigo_paciente="
					+ id_codigo_Paciente + " "
					+
					// "AND apac.numero_documento='"+NumeroDocumento+"' "+
					"AND apac.conv_numero_contrato_fk = con.conv_numero_contrato "
					+ "AND con.ent_nit_contratante_fk = ent.ent_nit"
			// +" AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk"+
			// " ORDER BY aad.adm_numero_ingreso DESC "+
			// "LIMIT 1"
			);
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		return rs;
	}

	/*****************************************************/
	/**** METODO DE BUSQUEDA DE INFORMES DE HEMODINAMIA ****/
	/*****************************************************/

	public java.sql.ResultSet BuscarInformes(String TipoDocumento,
			String NumeroDocumento) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT " +
			/*
			 * "apac.nombre,"+ "apac.primer_apellido,"+
			 * "apac.segundo_apellido,"+ "apac.pac_codigo_paciente,"+
			 * "(YEAR(CURDATE()))-YEAR(apac.fecha_nacimiento) AS 'EDAD',"+
			 * "ent.nombre_entidad,"+
			 */
			"inf.id_informe_hemodinamia," + "inf.fecha_diagnostico,"
					+ "inf.hora_diagnostico" + "apac.pac_codigo_paciente" +
					/*
					 * , aad.adm_numero_ingreso, aad.fecha_registro,
					 * aad.hora_registro, aad.estado
					 */
					" FROM " + "adm_paciente apac," + "adm_convenio con,"
					+ "adm_entidad ent,"
					+ "cat_informe_diagnostico_hemodinamia inf" +
					/* ,adm_admisiones aad */
					" WHERE " + "apac.tipo_documento='" + TipoDocumento + "' "
					+ "AND apac.numero_documento='" + NumeroDocumento + "' " +
					/* AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk */
					"AND apac.conv_numero_contrato_fk = con.conv_numero_contrato "
					+ "AND con.ent_nit_contratante_fk = ent.ent_nit "
					+ "AND apac.pac_codigo_paciente =inf.id_paciente");
			/*
			 * ORDER BY aad.adm_numero_ingreso DESC LIMIT 1
			 */
		} catch (Exception ex) {
			System.out.println("Error en MetodoInforme>>BuscarInforme " + ex);
		}
		return rs;
	}

	/*********************************************************/
	/**  Metodos de busqueda de codigos actuales para       **/
	/**  llaves foraneas en tablas asociadas a informes     **/
	/** de ventriculografia, aortograma y anatomia coronaria**/
	/*********************************************************/
	
	
	public int codigoActualinformeHemodinamia() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(id_informe_diagnostico_hemodinamia)FROM cat_informe_diagnostico_hemodinamia");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}

	public int codigoActualLesionAnatomiaCoronaria() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(id_info_lesion_arteria)FROM cat_info_lesion_arteria");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}
	
	public int codigoActualinformeAnatomiaCoronaria() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(id_informe_anatomia_coronaria)FROM cat_informe_anatomia_coronaria");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}
	
	/*****************************************/
	/******Metodo Insertar Encabezado*********/
	/**********Informe Hemodinamia************/
	/*****************************************/ 

	
	public boolean insertarEncabesadoInformeHemodinamia(
			String nombre_usuario,
			String fecha, 
			String hora, 
			String cod_paciente, 
			String nombre_anesteciologo,
			String cod_tipo_indicacion,
			String cod_estudio_realizado
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement("INSERT INTO " +
					"cat_informe_diagnostico_hemodinamia(" +
					"id_usuario," +
					"fecha_diagnostico," +
					"hora_diagnostico," +
					"id_paciente," +
					"nombre_anesteciologo,"+
					"id_tipo_indicacion_diagnostico_hemodinamia_fk," +
					"id_estudio_realizado_hemodinamia_fk" +
					")" +
					" VALUES " +
					"(?,?,?,?,?,?,?)");
			
			psc.setString(1, nombre_usuario);
			psc.setString(2, fecha);
			psc.setString(3, hora);
			psc.setString(4, cod_paciente);
			psc.setString(5, nombre_anesteciologo);
			psc.setString(6, cod_tipo_indicacion);
			psc.setString(7, cod_estudio_realizado);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
	

	
	
	/**************************************/
	/***Obtener Codigo Informe Insertado***/
	/**************************************/
	
	public int consultarIdUltimoInforme(String fechaActual, String HoraActual){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT " +
					"inf.id_informe_diagnostico_hemodinamia " +
					"FROM " +
					"cat_informe_diagnostico_hemodinamia inf " +
					"WHERE " +
					"inf.fecha_diagnostico='"+fechaActual+"' " +
					"AND inf.hora_diagnostico='"+HoraActual+"' ");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	
		
	}
	
	/**************************************/
	/***Metodo de inserccion de Imagen**/
	/*****Anatomia Coronaria **********/
	/**************************************/
	public boolean insertarImagenAnatomiaCoronaria(String idInforme, String imagenAnatomiaCoronaria){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenAnatomiaCoronaria);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement("UPDATE cat_contenido_informe_hemodinamia SET imagen_anatomia_coronaria = ? WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
				psc.setBytes(1, dataBytes);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	/**************************************/
	/***Metodo de inserccion de Imagen**/
	/*****Ventriculografia **********/
	/**************************************/
	public boolean insertarImagenVentriculografia(String idInforme, String imagenAnatomiaCoronaria){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenAnatomiaCoronaria);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement("UPDATE cat_contenido_informe_hemodinamia SET imagen_ventriculografia = ? WHERE id_informe_diagnostico_hemodinamia_fk ='"+idInforme+"'");
				psc.setBytes(1, dataBytes);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	/**************************************/
	/***Metodo de inserccion de Contenido**/
	/*****Anatomia Coronaria **********/
	/**************************************/
	
	public boolean insertarContenidoGeneralInformeAnatomiaCoronaria(
			String id_informe_diagnostico_fk,
			String descripcion_arteria_TCI,
			String descripcion_arteria_ADA,
			String descripcion_arteria_AC,
			String descripcion_arteria_RI,
			String descripcion_arteria_ACD,
			String descripcion_arteria_ADP,
			String descripcion_arteria_TPL,
			String descripcion_arteria_D1,
			String descripcion_arteria_D2,
			String descripcion_arteria_O1,
			String descripcion_arteria_O2
		) {
		
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
			"INSERT INTO " +
			"cat_contenido_informe_anatomia_coronaria" +
		    "(" +
		    "id_informe_diagnostico_hemodinamia_fk," +
		    "descripcion_arteria_TCI,"+
            "descripcion_arteria_ADA,"+
            "descripcion_arteria_AC,"+
            "descripcion_arteria_RI,"+
            "descripcion_arteria_ACD,"+
            "descripcion_arteria_ADP,"+
            "descripcion_arteria_TPL,"+
            "descripcion_arteria_D1,"+
            "descripcion_arteria_D2,"+
            "descripcion_arteria_O1,"+
            "descripcion_arteria_O2"+
            ") "+
            "VALUES "+
            "(?,?,?,?,?,?,?,?,?,?,?,?)");
			psc.setString(1, id_informe_diagnostico_fk);
			psc.setString(2, descripcion_arteria_TCI);
			psc.setString(3, descripcion_arteria_ADA);
			psc.setString(4, descripcion_arteria_AC);
			psc.setString(5, descripcion_arteria_RI);
			psc.setString(6, descripcion_arteria_ACD);
			psc.setString(7, descripcion_arteria_ADP);
			psc.setString(8, descripcion_arteria_TPL);
			psc.setString(9, descripcion_arteria_D1);
			psc.setString(10, descripcion_arteria_D2);
			psc.setString(11, descripcion_arteria_O1);
			psc.setString(12, descripcion_arteria_O2);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	
	/**************************************/
	/***Metodo de inserccion de Contenido**/
	/*********De informe General **********/
	/**************************************/
	
	
	public boolean insertarContenidoGeneralInformeHemodinamia(
			String id_informe_diagnostico_fk,
			String contenido_informe_ventriculografia, 
			String contenido_informe_aortograma, 
			String resumen_procedimiento,
			String conclusiones,
			String recomendaciones,
			String contenido_informe_bypass
			) {
	
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement("INSERT INTO " +
					"cat_contenido_informe_hemodinamia(" +
					"id_informe_diagnostico_hemodinamia_fk," +
					"contenido_informe_ventriculografia," +
					"contenido_informe_aortograma," +
					"resumen_procedimiento,"+
					"conclusiones," +
					"recomendaciones," +
					"contenido_informe_bypass"+
					")" +
					" VALUES " +
					"(?,?,?,?,?,?,?)");
			
			psc.setString(1, id_informe_diagnostico_fk);
			psc.setString(2, contenido_informe_ventriculografia);
			psc.setString(3, contenido_informe_aortograma);
			psc.setString(4, resumen_procedimiento);
			psc.setString(5, conclusiones);
			psc.setString(6, recomendaciones);
			psc.setString(7, contenido_informe_bypass);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**************************************/
	/** Metodos de inserccion de Informe **/
	/********** Ventriculografia **********/
	/**************************************/
	
	public boolean insertarInformeVentriculografia(
			String ventriculo_izquierdo_dilatado,
		    String id_tipo_contractibilidad,
		    String id_gravedad_contractibilidad,
		    String id_caracteristica_contractibilidad,
		    String id_lugar_contractibilidad,
		    String id_porcentaje_fevi,
		    String id_insuficiencia_mitral,
		    String id_aspecto_valvula_mitral,
		    String id_informe_diagnostico_hemodinamia 
			){
	
		try{
			//System.out.print("\n entra: "+cadenaimagenventriculografia);
			
		/*	BASE64Decoder decoder = new BASE64Decoder();
			
			byte[] dataBytes= decoder.decodeBuffer(cadenaimagenventriculografia);
		   
			for (int i =0; i< dataBytes.length; i++){
				
				System.out.print(dataBytes[i]);
			}*/
			
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				// System.out.print(" id "+cod+"caracteristica "+id_caracteristica_lesion+" flujo "+id_flujo_timi+" les col "+id_lesion_colateral+" les ocl "+id_lesion_oclusion_total+"  tipo lesion "
				// +id_tipo_lesion +"\n");
				
				psc = con.conn.prepareStatement(
						"INSERT INTO cat_informe_ventriculografia (" +
						"ventriculo_izquierdo_dilatado," +
						"id_tipo_contractibilidad_fk," +
						"id_gravedad_contractibilidad_fk,"+
						"id_caracteristica_contractibilidad_fk," +
						"id_lugar_contractibilidad_fk," +
						"id_porcentaje_fevi_fk," +
						"id_insuficiencia_mitral_fk," +
						"id_aspecto_valvula_mitral_fk," +
						"id_informe_diagnostico_hemodinamia_fk" +
						")"+
						"VALUES (?,?,?,?,?,?,?,?,?)");
				psc.setString(1, ventriculo_izquierdo_dilatado);
				psc.setString(2, id_tipo_contractibilidad);
				psc.setString(3, id_gravedad_contractibilidad);
				psc.setString(4, id_caracteristica_contractibilidad);
				psc.setString(5, id_lugar_contractibilidad);
				psc.setString(6, id_porcentaje_fevi);
				psc.setString(7, id_insuficiencia_mitral);
				psc.setString(8, id_aspecto_valvula_mitral);
				psc.setString(9, id_informe_diagnostico_hemodinamia);
				//psc.setBytes(10, dataBytes);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				return true;
				}
			    
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
		
		
		
		}
		catch(Exception e){
		System.out.println(e.getMessage());	
		return false;
		}
		
		
	   
		

	
	}
	
	
	/**************************************/
	/** Metodos de inserccion de Informe **/
	/************** Aortograma*************/
	/**************************************/
	public boolean insertarInformeAortograma(
			        String raiz_aortica_dilatada, 
			        String medicion_raiz_aortica,
			        String aorta_ascendente,
			        String aorta_ascendente_medicion,
	    		    String id_clase_valvula_aortica_fk,
	    		    String id_aspecto_valvula_aortica_fk,
	    		    String id_insuficiencia_aortica_fk,
	    		    String id_estenosis_aortica_fk,
	    		    String gradiente_pico_pico,
	    		    String id_informe_diagnostico_hemodinamia_fk
			){
		try {
		    	PreparedStatement psc = null;
				Conexion con = new Conexion();
    			psc = con.conn.prepareStatement(
    			"INSERT INTO cat_informe_aortograma("+
    			"raiz_aortica_dilatada," +
    		    "medicion_raiz_aortica," +
    			"aorta_ascendente,"+
    		    "medicion_aorta_ascendente,"+
    		    "id_clase_valvula_aortica_fk," +
    		    "id_aspecto_valvula_aortica_fk," +
    		    "id_insuficiencia_aortica_fk," +
    		    "id_estenosis_aortica_fk," +
    		    "gradiente_pico_pico," +
    		    "id_informe_diagnostico_hemodinamia_fk)" +
    		    "VALUES (?,?,?,?,?,?,?,?,?,?)");
    			psc.setString(1,raiz_aortica_dilatada);
				psc.setString(2,medicion_raiz_aortica );
				psc.setString(3,aorta_ascendente);
				psc.setString(4,aorta_ascendente_medicion);
				psc.setString(5,id_clase_valvula_aortica_fk);
				psc.setString(6, id_aspecto_valvula_aortica_fk);
				psc.setString(7, id_insuficiencia_aortica_fk);
				psc.setString(8, id_estenosis_aortica_fk);
				psc.setString(9, gradiente_pico_pico);
				psc.setString(10, id_informe_diagnostico_hemodinamia_fk);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		return true;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**************************************/
	/** Metodos de inserccion de Informe **/
	/********* Anatomia Coronaria *********/
    /**************************************/
	
	
	public boolean insertarCaracterizacionLesion(
			  String id_info_lesion_arteria_fk,
			  String id_localizacion,
			  String id_porcentaje,
		      String id_caracteristica_lesion,
			  String id_lesion_colateral,
			  String id_lesion_oclusion_total,
			  String id_tipo_lesion,
			  String id_rama_bifurcada,
			  String id_medina){
		
		/*System.out.print(
			id_info_lesion_arteria_fk+","+
		    id_localizacion+","+
		    id_porcentaje+","+
		    id_caracteristica_lesion+","+
		    id_lesion_colateral+","+
		    id_lesion_oclusion_total+","+
		    id_tipo_lesion+","+
		    id_rama_bifurcada+","+
		    id_medina
			+"\n");*/
		
		try{
			PreparedStatement psc = null;
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
					"INSERT INTO cat_caracterizacion_arteria"+
		            "("+
		             "id_info_lesion_arteria_fk,"+
		             "id_localizacion_fk,"+
		             "id_porcentaje_fk,"+
		             "id_caracteristica_lesion_fk,"+
		             "id_lesion_colateral_fk,"+
		             "id_lesion_oclusion_total_fk,"+
		             "id_tipo_lesion_fk,"+
		             "id_rama_bifurcada_fk,"+
		             "id_medina_fk) "+
		             "VALUES (?,?,?,?,?,?,?,?,?)");
			psc.setString(1, id_info_lesion_arteria_fk);
			psc.setString(2, id_localizacion);
			psc.setString(3, id_porcentaje);
			psc.setString(4, id_caracteristica_lesion);
			psc.setString(5, id_lesion_colateral);
			psc.setString(6, id_lesion_oclusion_total);
			psc.setString(7, id_tipo_lesion);
			psc.setString(8, id_rama_bifurcada);
			psc.setString(9, id_medina);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
		
			return true;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}

	public boolean insertarlesionArteria(
			String id_flujo_timi, 
			String id_lecho_distal,
			String id_arteria,
		    String id_informe_anatomia_coronaria
			) {
		    PreparedStatement psc = null;
		    try {
			Conexion con = new Conexion();
			// System.out.print(" id "+cod+"caracteristica "+id_caracteristica_lesion+" flujo "+id_flujo_timi+" les col "+id_lesion_colateral+" les ocl "+id_lesion_oclusion_total+"  tipo lesion "
			// +id_tipo_lesion +"\n");
			psc = con.conn.prepareStatement(
					"insert into " +
					"cat_info_lesion_arteria " +
					"("+
					"id_flujo_timi_fk," +
					"id_lecho_distal_fk,"+
					"id_arteria_fk," +
					"id_informe_anatomia_coronaria_fk"+
					") "+ 
					"values(?,?,?,?)");
			
		/*	System.out.print(
			"\n no_arteria: "+id_arteria+
			"\n id_caracteristica_lesion: "+id_caracteristica_lesion+
			"\n id_flujo_timi: "+id_flujo_timi+
			"\n id_lesion_colateral: "+ id_lesion_colateral+
			"\n id_lesion_oclusion_total: " +id_lesion_oclusion_total+
			"\n id_tipo_lesion: "+id_tipo_lesion+
			"\n id_rama_bifurcada: "+id_rama_bifurcada+
			"\n id_medina: " +id_medina+
			"\n id_lecho_distal: "+id_lecho_distal+
            "\n id_informe_anatomia_coronaria: "+id_informe_anatomia_coronaria+
			"\n\n"
			);*/

			
		
			psc.setString(1, id_flujo_timi);
			psc.setString(2, id_lecho_distal);
			psc.setString(3, id_arteria);
			psc.setString(4,id_informe_anatomia_coronaria);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertarInformeAnatomiaCoronaria(String id_informeHemodinamia, String dominancia){
		 PreparedStatement psc = null;
		
		 try{
				//System.out.print("\n entra: "+imagen_anatomia_coronaria);
				
			/*	BASE64Decoder decoder = new BASE64Decoder();
				
				byte[] dataBytes= decoder.decodeBuffer(imagen_anatomia_coronaria);
			   */
			
				
				
				 try {
						Conexion con = new Conexion();
						psc = con.conn.prepareStatement("insert into cat_informe_anatomia_coronaria" +"(id_informe_diagnostico_hemodinamia_fk, dominancia) VALUES (?,?)");
						psc.setString(1, id_informeHemodinamia);
					//	psc.setBytes(2, dataBytes);
						psc.setString(2, dominancia);
						
				        psc.executeUpdate();
						psc.close();
						con.cerrar();
						return true;
					}
				    catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
		 }catch(Exception e){
			 e.getMessage();
			 return false;
		 }
		 
		 
		 
		
	}

	/*******************************/
	/*********Metodos Split*********/
	/*******************************/
	
	public String[] splitCompuesto(String cadena) { // forma
		// {[a-b-c],[d-e-f],[g-h-i]}
		String line =cadena;
		line = line.substring(1, line.length()-1);
		String [] cad = line.split(",");
		String cadaux ="";
		for (int i =0; i < cad.length; i++){
			
			cadaux = cad[i];
			cadaux = cadaux.substring(1,cadaux.length() -1);
			cad[i]= cadaux;
		//	System.out.print(""+cad[i]+"\n");
		}

		return cad;
	}

}
