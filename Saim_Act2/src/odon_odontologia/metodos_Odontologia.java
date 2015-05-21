package odon_odontologia;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;
import sun.misc.BASE64Decoder;
import adm_logica.Conexion;

public class metodos_Odontologia {
	public String nombretecnicodientes(){
		java.sql.ResultSet rs = null;
		Statement st = null;
		String cadena = "";
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT no_diente "+
						"FROM odont_nombre_diente "+
						"ORDER BY no_diente"
						);
				while(rs.next()) {
					cadena += rs.getString(1) +"@";
				}
			
				cadena = cadena.substring(0, cadena.length() -1);
			} catch (Exception ex) {
				ex.getMessage();
			}
		return cadena;
		
		
	}
	
	public String obtenernombreGeneralidades(){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		String cadena = "";
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT nombre_generalidad "+
						"FROM "+
						"odont_generalidad "+
						"ORDER BY id_generalidad"
						);
				while(rs.next()) {
					cadena += rs.getString(1) +"@";
				}
			
				cadena = cadena.substring(0, cadena.length() -1);
			} catch (Exception ex) {
				ex.getMessage();
			}
		return cadena;
	}
	
	
	public Vector<String> obtenerOdontogramasSuperiores(String codTratamientoActual,String codHistoriaActual){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT hist.id_historia_odontologica "+
					"FROM "+
					"odont_tratamiento_odontologico trat,"+
					"odont_historia_odontologica hist "+
					"WHERE "+
					"trat.id_tratamiento_odontologico = '"+codTratamientoActual+"' "+
					"AND trat.id_tratamiento_odontologico = hist.id_tratamiento_odontologico_fk "+
					"AND hist.id_historia_odontologica > '"+codHistoriaActual+"' "+
					"AND hist.estado_historia='1'"
					);
			while(rs.next()) {
				mivector.addElement(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return mivector;
   }
	
	public Vector<String> obtenerlaexistenciadegeneralidadesdientes(String codHistoriaActual){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		String cadena = "";
		int num_dientes = numeroDientes();
		
		for (int i = 0 ; i < num_dientes; i++){
			cadena = "";
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT DISTINCT "+
						"gen.id_generalidad_fk,gen.id_nombre_diente "+
						"FROM "+
						"odont_generalidad_diente gen "+
						"WHERE "+
						"gen.id_historia_odontologica_fk = '"+codHistoriaActual+"' "+
						"and id_nombre_diente= '"+(i+1)+"' "+
						"AND gen.valido ='1' "
					
						);
				while(rs.next()) {
					
					
					cadena += rs.getInt(1) +"@";
				     	/*if(rs.getInt(1)>1){ // diente con una generalidad
				     		cadena = 
				    		mivector.addElement("1");
				     	}
				     	else{ // diente sin generalidad
				    		mivector.addElement("0");
				     	}*/
				}
				cadena = cadena.substring(0, cadena.length()-1);
			//	System.out.print(cadena+"\n");
				mivector.add(cadena);
				
			} catch (Exception ex) {
				ex.getMessage();
			}
			
		}
		
		
		return mivector;
		
		
	}
	
	

	// consulta para buscar el paciente si se encuentra registrado
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
					+ "(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))- (RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento,5))AS 'EDAD',"
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
	
	// consulta si el paciente esta realizandose un tratamiento odontologico
	public Vector<String> buscarTratamientosRealizados(String tipo_documento, String numero_documento){
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
	             	"trat.id_tratamiento_odontologico, "+
					"trat.estado_tratamiento "+		
		            "FROM "+
		            "adm_paciente pac,"+
		            "odont_tratamiento_odontologico trat "+
             		"WHERE "+
		            "pac.tipo_documento = '"+tipo_documento+"'"+
		            "AND pac.numero_documento = '"+numero_documento+"'"+
		            "AND pac.pac_codigo_paciente = trat.cod_paciente "+
		            "AND trat.estado_informe= '1'"
					);
		
			while(rs.next()) {
				
				if(rs.getString(2).equals("1")){
					mivector.addElement(rs.getString(1) +"-"+"en curso");
				}else{
					mivector.addElement(rs.getString(1) +"-"+"finalizado");
				}
				
				
				
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return mivector;
	}
	
	
	public int obtenerUltimaHistoriaOdontologicaPorTratamiendo(String CodTratamiento){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int ultimaHistoria=1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT MAX(hist.id_historia_odontologica) "+
					"FROM odont_tratamiento_odontologico trat,"+
					"odont_historia_odontologica hist "+
					"WHERE "+
					"trat.id_tratamiento_odontologico = '"+CodTratamiento+"' "+
					"AND trat.id_tratamiento_odontologico = hist.id_tratamiento_odontologico_fk "+
			        "AND hist.estado_historia='1' "  
					);
			rs.next();
			if(rs.getString(1)!=null){
				ultimaHistoria = Integer.parseInt(rs.getString(1));			
			}
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
        return ultimaHistoria;
	
	}
	

	
	// buscar historias clinicas odontologicas realizadas por tratamiento 
	public Vector<String> buscarHistoriasOdontologicasRealizadas(String codTratamiento ){
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"hist.id_historia_odontologica,"+
					"hist.fecha_realizacion, "+
					"hist.hora_realizacion "+
					"FROM "+
					"odont_historia_odontologica hist "+
					"WHERE "+
					"hist.id_tratamiento_odontologico_fk = '"+codTratamiento+"' "+
			        "AND hist.estado_historia='1' "  
					);
			while(rs.next()) {
				mivector.addElement(rs.getString(1)+"&"+rs.getString(2)+" "+rs.getString(3));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		return mivector;
	}
	
	// metodo para insertar tratamiento odontologico nuevo al paciente
	public boolean insertarTratamientoOdontologico(
			String fechainiciotratamiento,
			String horainiciotratamiento,
		    String codigopaciente,
		    String estadotratamiento
			){
	
		try{
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
    			psc = con.conn.prepareStatement(
						"INSERT INTO odont_tratamiento_odontologico "+
					    "("+
					    "fecha_inicio_tratamiento,"+
					    "hora_inicio_tratamiento,"+
					    "cod_paciente,"+
					    "estado_tratamiento) "+
					"VALUES (?,?,?,?)");
				psc.setString(1, fechainiciotratamiento);
				psc.setString(2, horainiciotratamiento);
				psc.setString(3, codigopaciente);
				psc.setString(4, estadotratamiento);
				
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

	// metodo para actualizar tratamiento 
	public boolean actualizarTratamientoOdontologico(
			String Tratamiento,
		    String fechafinalizacion,
		    String horafinalizacion,
		    String estadotratamiento
			){
	
		try{
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE odont_tratamiento_odontologico "+
						"SET "+
						" fecha_finalizacion_tratamiento = ?,"+
						" hora_finalizacion_tratamiento = ?,"+
						" estado_tratamiento = ?"+
						" WHERE "+
						" id_tratamiento_odontologico = '"+Tratamiento+"'");

				psc.setString(1,fechafinalizacion);
				psc.setString(2,horafinalizacion);
				psc.setString(3,estadotratamiento);
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
	
	
	// metodo buscar ultimo tratamiento realizado del paciente
	
	public int ultimocodTratamientoOdontologicoRealizado(String tipoDocumento, String numero_documento){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int ultimoTratamiento=-1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
			"SELECT "+
			"MAX(est.id_tratamiento_odontologico) "+
	
			"FROM "+
			"odont_tratamiento_odontologico est,"+
			"adm_paciente pac "+
			"WHERE "+
			"pac.tipo_documento = '"+tipoDocumento+"' "+
			"AND pac.numero_documento = '"+numero_documento+"' "+
			"AND pac.pac_codigo_paciente=est.cod_paciente "+
			"AND est.estado_informe = 1 "
			);
			rs.next();
			if(rs.getString(1)!=null){
				
				ultimoTratamiento = Integer.parseInt(rs.getString(1));			
			}
						
			
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		
        return ultimoTratamiento;
		
		
	}
	
	// metodo buscar estado (activo / inactivo) de un tratamiento realizado
	public int estadoultimoTratamientoOdontologicoRealizado(String codTratamiento){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int estadoTratamiento=1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					" SELECT "+ 
					" est.estado_tratamiento "+ 	
					" FROM "+
					" odont_tratamiento_odontologico est"+
                    " WHERE "+ 
					" est.id_tratamiento_odontologico = '"+codTratamiento+"' "+
					" AND est.estado_informe = 1 "
			);
			rs.next();
			if(rs.getString(1)!=null){
				
				estadoTratamiento = Integer.parseInt(rs.getString(1));		
			//	System.out.print(""+estadoTratamiento);
			}
						
			
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		
        return estadoTratamiento;
		
		
	}
    
   
	// buscar ultimo tratamiento odontologico por el cod del paciente
	
	public int ultimocodTratamientoOdontologicoActivo2(String codPaciente){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int cod=-1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
			"SELECT "+
			"MAX(id_tratamiento_odontologico) "+
			"FROM "+
			"odont_tratamiento_odontologico est,"+
			"adm_paciente pac "+
			"WHERE "+
			"pac.pac_codigo_paciente = '"+codPaciente+"' "+
			"AND pac.pac_codigo_paciente=est.cod_paciente "+
			"AND est.estado_tratamiento = 1 "+
			"AND est.estado_informe = 1 "
			);
			rs.next();
			if (rs.getString(1) != null) {
				cod = Integer.parseInt(rs.getString(1));
			}
			
			
		} catch (Exception ex) {
			System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "
					+ ex);
		}
		
        return cod;
		
		
	}
	
	
	public int ultimocodHistoriaOdontologica(String codTratamientoOdontologico){
		java.sql.ResultSet rs = null;
		Statement st = null;
		int cod=-1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"MAX(odont.id_historia_odontologica) "+
					"FROM "+
					"odont_tratamiento_odontologico est,"+
					"odont_historia_odontologica odont "+
					"WHERE "+
					"est.id_tratamiento_odontologico = '"+codTratamientoOdontologico+"' "+
					"AND est.id_tratamiento_odontologico = odont.id_tratamiento_odontologico_fk "+
				    "and odont.estado_historia ='1'"
					);
			rs.next();
		
				
		
				if (rs.getString(1) != null) {
					cod = Integer.parseInt(rs.getString(1));
				}
			
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return cod;
	}
	
	
	// metodo inserccion de historia odontologica
	public boolean insertarhistoriaOdontologica(
		     String fecha_realizacion,
		     String cod_odontologo,
		     String motivo_consulta,
		     String plan_tratamiento,
		     String observaciones,
		     String evolucion,
		     String id_tratamiento_odontologico_fk,
		     String hora_realizacion
			){

			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						" INSERT INTO odont_historia_odontologica "+
				        "("+
				        "fecha_realizacion,"+
				        "cod_odontologo,"+
				        "motivo_consulta,"+
				        "plan_tratamiento,"+
				        "observaciones,"+
				        "evolucion,"+
				        "id_tratamiento_odontologico_fk," +
				        "hora_realizacion) "+
				        "VALUES (?,?,?,?,?,?,?,?)");

			 
			    psc.setString(1, fecha_realizacion);
			    psc.setString(2, cod_odontologo);
			    psc.setString(3, motivo_consulta);
			    psc.setString(4, plan_tratamiento);
			    psc.setString(5, observaciones);
			    psc.setString(6, evolucion);
			    psc.setString(7, id_tratamiento_odontologico_fk);
			    psc.setString(8, hora_realizacion);
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
	
	// metodo actualizar de historia odontologica
	public boolean actualizarhistoriaOdontologica(
		     String motivo_consulta,
		     String plan_tratamiento,
		     String observaciones,
		     String evolucion,
		     String id_historia_odontologica
		 	){

			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					
						" UPDATE odont_historia_odontologica "+
				        "SET motivo_consulta =?,"+
				        "plan_tratamiento=?,"+
				        "observaciones=?,"+
				        "evolucion=? " +
				        "WHERE id_historia_odontologica ='"+id_historia_odontologica + "' "
                         );
			    psc.setString(1, motivo_consulta);
			    psc.setString(2, plan_tratamiento);
			    psc.setString(3, observaciones);
			    psc.setString(4, evolucion);
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
	
	// metodo buscar datos de la historia odontologica por codHistoriaOdontologica
	
	public String seleccionardataexamenclinico (String codHistoriaOdontologica){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		String datos= "";
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					  "SELECT "+
					  "lengua,"+
					  "carrillos,"+
					  "abrasion,"+
					  "atricion,"+
					  "hipoplasias,"+
					  "gingivitis_marginal,"+
					  "gingivitis_difusa,"+
					  "micrognasia,"+
					  "macrognasia,"+
					  "desviacion_linea_media,"+
					  "malposicion,"+
					  "atm,"+
					  "habitos_orales,"+
					  "paladar_blando,"+
					  "piso_de_boca,"+
					  "supernumerarios,"+
					  "hipodoncia,"+
					  "fracturas,"+
					  "periodontitis,"+
					  "retracciones,"+
					  "torus,"+
					  "sobremordida_vertical,"+
					  "sobremordida_horizontal,"+
					  "otros_examenes "+
					  "FROM "+
					  "odont_examen_clinico exa,"+
					  "odont_historia_odontologica hist "+
					  "WHERE "+
					  "hist.id_historia_odontologica = '"+codHistoriaOdontologica+"'"+
					  "AND hist.id_historia_odontologica = exa.id_historia_odontologica_fk "+
					  "AND exa.estado = '1' "
					);

			if(rs.next()) {
				
				datos += rs.getString(1)+"-";
				datos +=rs.getString(2)+"-";
				datos +=rs.getString(3)+"-";
				datos +=rs.getString(4)+"-";
				datos +=rs.getString(5)+"-";
				datos +=rs.getString(6)+"-";
				datos +=rs.getString(7)+"-";
				datos +=rs.getString(8)+"-";
				datos +=rs.getString(9)+"-";
				datos +=rs.getString(10)+"-";
				datos +=rs.getString(11)+"-";
				datos +=rs.getString(12)+"-";
				datos +=rs.getString(13)+"-";
				datos +=rs.getString(14)+"-";
				datos +=rs.getString(15)+"-";
				datos +=rs.getString(16)+"-";
				datos +=rs.getString(17)+"-";
				datos +=rs.getString(18)+"-";
				datos +=rs.getString(19)+"-";
				datos +=rs.getString(20)+"-";
				datos +=rs.getString(21)+"-";
				datos +=rs.getString(22)+"-";
				datos +=rs.getString(23)+"-";
				datos +=rs.getString(24);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return datos;
		
		
		
		
	}
	
	// metodo insertar examen clinico
	
	public boolean insertardataexamenclinico(
			String lengua,
		    String carrillos,
		    String abrasion,
		    String atricion,
		    String hipoplasias,
		    String gingivitis_marginal,
		    String gingivitis_difusa,
		    String micrognasia,
		    String macrognasia,
		    String desviacion_linea_media,
		    String malposicion,
		    String atm,
		    String habitos_orales,
		    String paladar_blando,
		    String piso_de_boca,
		    String supernumerarios,
		    String hipodoncia,
		    String fracturas,
		    String periodontitis,
		    String retracciones,
		    String torus,
		    String sobremordida_vertical,
		    String sobremordida_horizontal,
		    String otros,
		    String id_historia_odontologica_fk
			){
	
		try{
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"INSERT INTO odont_examen_clinico "+
					    "(lengua,"+
					    "carrillos,"+
					    "abrasion,"+
					    "atricion,"+
					    "hipoplasias,"+
					    "gingivitis_marginal,"+
					    "gingivitis_difusa,"+
					    "micrognasia,"+
					    "macrognasia,"+
					    "desviacion_linea_media,"+
					    "malposicion,"+
					    "atm,"+
					    "habitos_orales,"+
					    "paladar_blando,"+
					    "piso_de_boca,"+
					    "supernumerarios,"+
					    "hipodoncia,"+
					    "fracturas,"+
					    "periodontitis,"+
					    "retracciones,"+
					    "torus,"+
					    "sobremordida_vertical,"+
					    "sobremordida_horizontal,"+
					    "otros_examenes,"+
					    "id_historia_odontologica_fk) "+
					    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				psc.setString(1,lengua);
				psc.setString(2, carrillos);
				psc.setString(3, abrasion);
				psc.setString(4, atricion);
				psc.setString(5, hipoplasias);
				psc.setString(6, gingivitis_marginal);
				psc.setString(7, gingivitis_difusa);
				psc.setString(8, micrognasia);
				psc.setString(9, macrognasia);
				psc.setString(10, desviacion_linea_media);
				psc.setString(11, malposicion);
				psc.setString(12, atm);
				psc.setString(13, habitos_orales);
				psc.setString(14, paladar_blando);
				psc.setString(15, piso_de_boca);
				psc.setString(16, supernumerarios);
				psc.setString(17, hipodoncia);
				psc.setString(18, fracturas);
				psc.setString(19, periodontitis);
				psc.setString(20, retracciones);
				psc.setString(21, torus);
				psc.setString(22, sobremordida_vertical);
				psc.setString(23, sobremordida_horizontal);
				psc.setString(24, otros);
				psc.setString(25, id_historia_odontologica_fk);
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
	
	// metodo buscar todas las generalidades de un diente
public Vector<String> seleccionartiposdegeneralidades (){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		Vector<String> mivector= new Vector<String>();
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT *"+
					"FROM odont_generalidad"
					);
		
			while(rs.next()) {
				
				mivector.addElement(rs.getString(1)+"@"+rs.getString(2)+"@"+rs.getString(3));
				
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return mivector;
}
	//metodo seleccionar las direcciones de las generalidades (izquierdo, derecho, superior , inferior , tope)

public Vector<String> seleccionardireccionesdegeneralidades(){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	Vector<String> mivector= new Vector<String>();
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT *"+
				"FROM odont_direccion_generalidad"
				);
	
		while(rs.next()) {
			
			mivector.addElement(rs.getString(1)+"@"+rs.getString(2));
			
		}
	} catch (Exception ex) {
		ex.getMessage();
	}
	
	return mivector;
}

//metodo actualizar datos del examen clinico para una historia determinada

	public boolean actualizardataexamenclinico(
			String lengua,
		    String carrillos,
		    String abrasion,
		    String atricion,
		    String hipoplasias,
		    String gingivitis_marginal,
		    String gingivitis_difusa,
		    String micrognasia,
		    String macrognasia,
		    String desviacion_linea_media,
		    String malposicion,
		    String atm,
		    String habitos_orales,
		    String paladar_blando,
		    String piso_de_boca,
		    String supernumerarios,
		    String hipodoncia,
		    String fracturas,
		    String periodontitis,
		    String retracciones,
		    String torus,
		    String sobremordida_vertical,
		    String sobremordida_horizontal,
		    String otros,
		    String id_historia_odontologica_fk
			){
	
		try{
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						  "UPDATE odont_examen_clinico "+
						  "SET " +
						  "lengua = ?,"+
						  "carrillos = ?,"+
						  "abrasion = ?,"+
						  "atricion = ?,"+
						  "hipoplasias = ?,"+
						  "gingivitis_marginal = ?,"+
						  "gingivitis_difusa = ?,"+
						  "micrognasia = ?,"+
						  "macrognasia = ?,"+
						  "desviacion_linea_media = ?,"+
						  "malposicion = ?,"+
						  "atm = ?,"+
						  "habitos_orales = ?,"+
						  "paladar_blando = ?,"+
						  "piso_de_boca = ?,"+
						  "supernumerarios = ?,"+
						  "hipodoncia = ?,"+
						  "fracturas = ?,"+
						  "periodontitis = ?,"+
						  "retracciones = ?,"+
						  "torus = ?,"+
						  "sobremordida_vertical = ?,"+
						  "sobremordida_horizontal = ?,"+
						  "otros_examenes = ? "+
						  "WHERE "+
						  "id_historia_odontologica_fk = '"+id_historia_odontologica_fk+"'");

				psc.setString(1,lengua);
				psc.setString(2, carrillos);
				psc.setString(3, abrasion);
				psc.setString(4, atricion);
				psc.setString(5, hipoplasias);
				psc.setString(6, gingivitis_marginal);
				psc.setString(7, gingivitis_difusa);
				psc.setString(8, micrognasia);
				psc.setString(9, macrognasia);
				psc.setString(10, desviacion_linea_media);
				psc.setString(11, malposicion);
				psc.setString(12, atm);
				psc.setString(13, habitos_orales);
				psc.setString(14, paladar_blando);
				psc.setString(15, piso_de_boca);
				psc.setString(16, supernumerarios);
				psc.setString(17, hipodoncia);
				psc.setString(18, fracturas);
				psc.setString(19, periodontitis);
				psc.setString(20, retracciones);
				psc.setString(21, torus);
				psc.setString(22, sobremordida_vertical);
				psc.setString(23, sobremordida_horizontal);
				psc.setString(24, otros);
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
	// metodo seleccionar antecedentes medicos por historia
	public String seleccionarDetalleHistoria(String codHistoria){
		java.sql.ResultSet rs = null;
		Statement st = null;
	    String evolucion="";
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"hist.motivo_consulta,"+
					"hist.plan_tratamiento,"+
					"hist.observaciones,"+
					"hist.evolucion "+
					"FROM "+
					"odont_historia_odontologica hist "+
					"WHERE "+
					"hist.id_historia_odontologica = '"+codHistoria+"' "+
					"AND hist.estado_historia ='1'"
					);
			rs.next();
			if(rs.getString(1) != null) {
				
				
			evolucion += rs.getString(1)+"&"+rs.getString(2)+"&"+ rs.getString(3)+"&"+rs.getString(4);
			}
			
		
			
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return evolucion;
		
	}
	
	
	
	public String seleccionarDataAntecedentesMedicos (String codHistoriaOdontologica){
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		String datos= "";
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					  "SELECT "+
					  "alergias,"+
					  "hemorragias,"+
					  "enfermedades_respiratorias,"+
					  "cardiopatias,"+
					  "fiebre_reumatica,"+
					  "enfermedades_renales,"+
					  "hepatitis,"+
					  "trastornos_gastricos,"+
					  "tension_arterial,"+
					  "diabetes,"+
					  "ingesta_medicamentos,"+
					  "cirugias,"+
					  "protesis,"+
					  "hiv,"+
					  "extracciones_dentales,"+
					  "enfermedades_orales,"+
					  "antecedentes_familiares,"+
					  "otros_antecedentes "+
					  " FROM "+
					  "odont_antecedentes_medicos_odontologicos ant,"+
					  "odont_historia_odontologica hist"+
					  " WHERE "+
					  "hist.id_historia_odontologica = '"+codHistoriaOdontologica+"' "+
					  "AND hist.id_historia_odontologica = ant.id_historia_odontologica_fk "+
					  "AND ant.estado='1' "
					); 
			rs.next();
			if(rs.getString(1)!= null) {
				
				datos += rs.getString(1)+"-";
				datos +=rs.getString(2)+"-";
				datos +=rs.getString(3)+"-";
				datos +=rs.getString(4)+"-";
				datos +=rs.getString(5)+"-";
				datos +=rs.getString(6)+"-";
				datos +=rs.getString(7)+"-";
				datos +=rs.getString(8)+"-";
				datos +=rs.getString(9)+"-";
				datos +=rs.getString(10)+"-";
				datos +=rs.getString(11)+"-";
				datos +=rs.getString(12)+"-";
				datos +=rs.getString(13)+"-";
				datos +=rs.getString(14)+"-";
				datos +=rs.getString(15)+"-";
				datos +=rs.getString(16)+"-";
				datos +=rs.getString(17)+"-";
				datos +=rs.getString(18);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}
		
		return datos;
		
		
		
		
	}
	// metodo insertar antecedentes medicos
	public boolean insertarDataAntecedentesMedicos(
			    String alergias,
			    String hemorragias,
			    String enfermedades_respiratorias,
			    String cardiopartias,
			    String fiebre_reumatica,
			    String enfermedades_renales,
			    String hepatitis,
			    String trastornos_gastricos,
			    String tension_arterial,
			    String diabetes,
			    String ingesta_medicamentos,
			    String cirugias,
			    String protesis,
			    String hiv,
			    String extracciones_dentales,
			    String enfermedades_orales,
			    String antecedentes_familiares,
			    String otros,
			    String id_historia_odontologica_fk
			){
	
		try{
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"INSERT INTO odont_antecedentes_medicos_odontologicos "+
			            "("+
			            "alergias,"+
			            "hemorragias,"+
			            "enfermedades_respiratorias,"+
			            "cardiopatias,"+
			            "fiebre_reumatica,"+
			            "enfermedades_renales,"+
			            "hepatitis,"+
			            "trastornos_gastricos,"+
			            "tension_arterial,"+
			            "diabetes,"+
			            "ingesta_medicamentos,"+
			            "cirugias,"+
			            "protesis,"+
			            "hiv,"+
			            "extracciones_dentales,"+
			            "enfermedades_orales,"+
			            "antecedentes_familiares,"+
			            "otros_antecedentes,"+
			            "id_historia_odontologica_fk"+
			            ") "+
			            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
			
				psc.setString(1,alergias);
				psc.setString(2, hemorragias);
				psc.setString(3, enfermedades_respiratorias);
				psc.setString(4, cardiopartias);
				psc.setString(5, fiebre_reumatica);
				psc.setString(6, enfermedades_renales);
				psc.setString(7, hepatitis);
				psc.setString(8, trastornos_gastricos);
				psc.setString(9, tension_arterial);
				psc.setString(10, diabetes);
				psc.setString(11, ingesta_medicamentos);
				psc.setString(12, cirugias);
				psc.setString(13, protesis);
				psc.setString(14, hiv);
				psc.setString(15, extracciones_dentales);
				psc.setString(16, enfermedades_orales);
				psc.setString(17, antecedentes_familiares);
				psc.setString(18, otros);
				psc.setString(19, id_historia_odontologica_fk);
				
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
	// metodo actualizar datos de los antecedentes medicos de la historia 
	
	public boolean actualizarDataAntecedentesMedicos(
		    String alergias,
		    String hemorragias,
		    String enfermedades_respiratorias,
		    String cardiopartias,
		    String fiebre_reumatica,
		    String enfermedades_renales,
		    String hepatitis,
		    String trastornos_gastricos,
		    String tension_arterial,
		    String diabetes,
		    String ingesta_medicamentos,
		    String cirugias,
		    String protesis,
		    String hiv,
		    String extracciones_dentales,
		    String enfermedades_orales,
		    String antecedentes_familiares,
		    String otros,
		    String id_historia_odontologica_fk
			){
	
		try{
			PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						 "UPDATE odont_antecedentes_medicos_odontologicos "+
						 "SET "+
						  "alergias = ?,"+
						  "hemorragias=?,"+
						  "enfermedades_respiratorias=?,"+
						  "cardiopatias=?,"+
						  "fiebre_reumatica=?,"+
						  "enfermedades_renales=?,"+
						  "hepatitis = ?,"+
						  "trastornos_gastricos=?,"+
						  "tension_arterial=?,"+
						  "diabetes=?,"+
						  "ingesta_medicamentos=?,"+
						  "cirugias=?,"+
						  "protesis=?,"+
						  "hiv=?,"+
						  "extracciones_dentales=?,"+
						  "enfermedades_orales=?,"+
						  "antecedentes_familiares=?,"+
						  "otros_antecedentes = ? "+
					  	 "WHERE id_historia_odontologica_fk = '"+id_historia_odontologica_fk+"'");

				psc.setString(1,alergias);
				psc.setString(2, hemorragias);
				psc.setString(3, enfermedades_respiratorias);
				psc.setString(4, cardiopartias);
				psc.setString(5, fiebre_reumatica);
				psc.setString(6, enfermedades_renales);
				psc.setString(7, hepatitis);
				psc.setString(8, trastornos_gastricos);
				psc.setString(9, tension_arterial);
				psc.setString(10, diabetes);
				psc.setString(11, ingesta_medicamentos);
				psc.setString(12, cirugias);
				psc.setString(13, protesis);
				psc.setString(14, hiv);
				psc.setString(15, extracciones_dentales);
				psc.setString(16, enfermedades_orales);
				psc.setString(17, antecedentes_familiares);
				psc.setString(18, otros);
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
	
	
/*public Vector<String> codsHistoriasOdontologicas (String codEstudio){
	java.sql.ResultSet rs = null;
	Statement st = null;
	Vector<String> mivector= new Vector<String>();
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT "+
				"hist.id_historia_odontologica "+
				"FROM "+
				"odont_estudio_odontologico odont,"+
				"odont_historia_odontologica hist"+
				"WHERE "+
				"odont.id_estudio_odontologico = '"+codEstudio+"' "+
				"AND odont.id_estudio_odontologico= hist.id_estudio_odontologico_fk" 
				);
	
		while(rs.next()) {
			
			mivector.addElement(rs.getString(1));
		
		}
	} catch (Exception ex) {
		ex.getMessage();
	}
	
	return mivector;
	
	
}
	*/

	// metodo para la obtencion de la evolucion de una historia anterior
	
public String evolucionHistoriaOdontologica (String codTratamiento){
	java.sql.ResultSet rs = null;
	Statement st = null;
    String evolucion="";
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT hist.fecha_realizacion , hist.hora_realizacion, hist.evolucion "+
				"FROM "+ 
				"odont_tratamiento_odontologico trat, "+
				"odont_historia_odontologica hist "+
				"WHERE "+
				"trat.id_tratamiento_odontologico = '"+codTratamiento+"' "+
				"AND trat.id_tratamiento_odontologico = hist.id_tratamiento_odontologico_fk "+
				"AND hist.estado_historia='1'"
				);
		while(rs.next()) {
		evolucion += "("+(rs.getString(1))+" "+rs.getString(2)+"):@"+ rs.getString(3)+"*";
		}
		
		evolucion = evolucion.substring(0, evolucion.length()-1);
		
	} catch (Exception ex) {
		ex.getMessage();
	}
	
	return evolucion;
	
	
}
	

public String evolucionHistoriaOdontologica2 (String codTratamiento, String codHistoria){
	java.sql.ResultSet rs = null;
	Statement st = null;
    String evolucion="";
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT hist.fecha_realizacion , hist.hora_realizacion, hist.evolucion "+
				"FROM "+
				"odont_tratamiento_odontologico trat,"+
				"odont_historia_odontologica hist "+
				"WHERE "+
				"trat.id_tratamiento_odontologico = '"+codTratamiento+"' "+
				"AND trat.id_tratamiento_odontologico = hist.id_tratamiento_odontologico_fk "+
				"AND hist.id_historia_odontologica < "+codHistoria +
				" AND hist.estado_historia='1' "
				);
		while(rs.next()) {
		evolucion += "("+(rs.getString(1))+" "+rs.getString(2)+"):@"+ rs.getString(3)+"*";
		}
		if(evolucion.length()>0){
			evolucion = evolucion.substring(0, evolucion.length()-1);
		}
		else{
			evolucion = "0";
		}
		
		
	} catch (Exception ex) {
		ex.getMessage();
	}
	
	return evolucion;
	
	
}



/*public boolean insertarOdontograma(
		String [] odontograma,
		String id_historia_odontologica_fk
		){
	BASE64Decoder decoder = new BASE64Decoder();
	byte[] dataBytes;
	
	try{
		
		dataBytes = decoder.decodeBuffer(cadenaimagendiente);
		
		PreparedStatement psc = null;
		    try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
					"INSERT INTO odont_descripcion_imagenes_dientes"+
				    "(canvas_dsi1,"+
					"canvas_dsi2,"+
				    "canvas_dsi3,"+
				    "canvas_dsi4,"+
				    "canvas_dsi5,"+
				    "canvas_dsi6,"+
				    "canvas_dsi7,"+
				    "canvas_dsi8,"+
				    "canvas_dsd1,"+
				    "canvas_dsd2,"+
				    "canvas_dsd3,"+
				    "canvas_dsd4,"+
				    "canvas_dsd5,"+
				    "canvas_dsd6,"+
				    "canvas_dsd7,"+
				    "canvas_dsd8,"+
				    "canvas_dii1,"+
				    "canvas_dii2,"+
				    "canvas_dii3,"+
				    "canvas_dii4,"+
				    "canvas_dii5,"+
				    "canvas_dii6,"+
				    "canvas_dii7,"+
				    "canvas_dii8,"+
				    "canvas_did1,"+
				    "canvas_did2,"+
				    "canvas_did3,"+
				    "canvas_did4,"+
				    "canvas_did5,"+
				    "canvas_did6,"+
				    "canvas_did7,"+
				    "canvas_did8,"+
				    "canvas_dcsi1,"+
				    "canvas_dcsi2,"+
				    "canvas_dcsi3,"+
				    "canvas_dcsi4,"+
				    "canvas_dcsi5,"+
				    "canvas_dcsd1,"+
				    "canvas_dcsd2,"+
				    "canvas_dcsd3,"+
				    "canvas_dcsd4,"+
				    "canvas_dcsd5,"+
				    "canvas_dcii1,"+
				    "canvas_dcii2,"+
				    "canvas_dcii3,"+
				    "canvas_dcii4,"+
				    "canvas_dcii5,"+
				    "canvas_dcid1,"+
				    "canvas_dcid2,"+
				    "canvas_dcid3,"+
				    "canvas_dcid4,"+
				    "canvas_dcid5,"+
				    "id_historia_odontologica_fk)"+
				    "VALUES " +
				    "(?,?,?,?,?,?,?,?,?,?,"+
				    "?,?,?,?,?,?,?,?,?,?,"+
			        "?,?,?,?,?,?,?,?,?,?,"+
				    "?,?,?,?,?,?,?,?,?,?,"+
				    "?,?,?,?,?,?,?,?,?,?,"+
				    "?,?,?,?)" );
		
			psc.setString(1,odontograma[0]);
			psc.setString(2, odontograma[1]);
			psc.setString(3, odontograma[2]);
			psc.setString(4, odontograma[3]);
			psc.setString(5, odontograma[4]);
			psc.setString(6, odontograma[5]);
			psc.setString(7, odontograma[6]);
			psc.setString(8, odontograma[7]);
			psc.setString(9, odontograma[8]);
			psc.setString(10, odontograma[9]);
			psc.setString(11, odontograma[10]);
			psc.setString(12, odontograma[11]);
			psc.setString(13, odontograma[12]);
			psc.setString(14, odontograma[13]);
			psc.setString(15, odontograma[14]);
			psc.setString(16, odontograma[15]);
			psc.setString(17, odontograma[16]);
			psc.setString(18, odontograma[17]);
			psc.setString(19, odontograma[18]);
			psc.setString(20, odontograma[19]);
			psc.setString(21, odontograma[20]);
			psc.setString(22, odontograma[21]);
			psc.setString(23, odontograma[22]);
			psc.setString(24, odontograma[23]);
			psc.setString(25, odontograma[24]);
			psc.setString(26, odontograma[25]);
			psc.setString(27, odontograma[26]);
			psc.setString(28, odontograma[27]);
			psc.setString(29, odontograma[28]);
			psc.setString(30, odontograma[29]);
			psc.setString(31, odontograma[30]);
			psc.setString(32, odontograma[31]);
			psc.setString(33, odontograma[32]);
			psc.setString(34, odontograma[33]);
			psc.setString(35, odontograma[34]);
			psc.setString(36, odontograma[35]);
			psc.setString(37, odontograma[36]);
			psc.setString(38, odontograma[37]);
			psc.setString(39, odontograma[38]);
			psc.setString(40, odontograma[39]);
			psc.setString(41, odontograma[40]);
			psc.setString(42, odontograma[41]);
			psc.setString(43, odontograma[42]);
			psc.setString(44, odontograma[43]);
			psc.setString(45, odontograma[44]);
			psc.setString(46, odontograma[45]);
			psc.setString(47, odontograma[46]);
			psc.setString(48, odontograma[47]);
			psc.setString(49, odontograma[48]);
			psc.setString(50, odontograma[49]);
			psc.setString(51, odontograma[50]);
			psc.setString(52, odontograma[51]);
			psc.setString(53, id_historia_odontologica_fk);
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
	
	
	
}*/

// metodo para inserter el id del odontograma
public boolean insertarOdontograma(
String id_historia_odontologica_fk
){

PreparedStatement psc = null;
    try {
	Conexion con = new Conexion();
	psc = con.conn.prepareStatement(
			"INSERT INTO odont_descripcion_imagenes_dientes"+
		    "(id_historia_odontologica_fk) "+
		    "VALUES " +
		    "(?)" );

    psc.setString(1, id_historia_odontologica_fk);
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

// metodo para insertar cada dibujo de los dientes que conforman el odontograma
public boolean actualizarOdontograma(
		String nombre_columna,
		String cadena_imagen,
		String id_historia_odontologica_fk
		){
	
	//System.out.print(nombre_columna + " " +id_historia_odontologica_fk);
	
    BASE64Decoder decoder = new BASE64Decoder();
	byte[] dataBytes;
	try{
		dataBytes = decoder.decodeBuffer(cadena_imagen);
		PreparedStatement psc = null;
			    try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE "+ 
						"odont_descripcion_imagenes_dientes "+
						"SET "+
	 					nombre_columna +"= ? "+
						"WHERE id_historia_odontologica_fk = '"+ id_historia_odontologica_fk+"'");
			
				psc.setBytes(1,dataBytes);
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
		System.out.println(e.getMessage());	
		return false;
	}
}

// metodo para transformar los nombres generalidades obtenidas de los dientes, en numeros para ser interpretados en la vista del modulo de odontologia
public Vector<String> caracterizacionpordiente(Vector<String> generalidadesDiente,Vector<String> direccionesGeneralidades, String cadenaEvaluar){
	

	Vector<String> cararacterizacion = new Vector<String>();
	if (cadenaEvaluar.equalsIgnoreCase("normal")){
		cararacterizacion.add("1");
		cararacterizacion.add("1");
	}
	else{
		String [] aux =  cadenaEvaluar.split("_");
		
		String auxidgeneralizacion = "1";
		String auxtienedireccion = "0";
	    String auxdireccion = "1";
		
	/*	System.out.print("!!!!!!!"+aux[2]);	// direccion de la generalidad
  
		System.out.print("!!!!!!!"+aux[3]);	// generalidad	*/
	  	String [] aux2;
		for (int a = 0 ; a < generalidadesDiente.size() ; a++){
			aux2 = generalidadesDiente.elementAt(a).split("@");
			if(aux[3].equalsIgnoreCase(aux2[1])){
				auxidgeneralizacion = aux2[0];
				auxtienedireccion =aux2[2];
				a = generalidadesDiente.size();
			}
		}
		if(auxtienedireccion.equalsIgnoreCase("1")){
			
			for (int b = 0 ; b < direccionesGeneralidades.size() ; b++){
				aux2 = direccionesGeneralidades.elementAt(b).split("@");
				if(aux[2].equalsIgnoreCase(aux2[1])){
					auxdireccion = aux2[0];
					b = direccionesGeneralidades.size();
				}
			}
			
		}
		cararacterizacion.add(auxidgeneralizacion);
		cararacterizacion.add(auxdireccion);
	}
	
	
	
	return cararacterizacion;
	
	
	
}

// metodo para insertar la generalidad de un diente
public boolean insertargeneralidadporDiente(
		String id_generalidad_fk,
		String id_direccion_generalidad_fk,
		String id_nombre_diente,
		String valido,
		String id_historia_odontologica_fk
		){

	try{
		PreparedStatement psc = null;
		    try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
			"INSERT INTO odont_generalidad_diente "+
			"(id_generalidad_fk,"+
			"id_direccion_generalidad_fk,"+
			"id_nombre_diente,"+
			"valido,"+
			"id_historia_odontologica_fk)"+
			" VALUES (?,?,?,?,?) ");
			psc.setString(1,id_generalidad_fk);
			psc.setString(2, id_direccion_generalidad_fk);
			psc.setString(3, id_nombre_diente);
			psc.setString(4, valido);
			psc.setString(5, id_historia_odontologica_fk);
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

//metodo para obtener el numero de dientes que conforma el odontograma
public int numeroDientes(){
	java.sql.ResultSet rs = null;
	Statement st = null;
int numero_dientes= 0;
	
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				  "SELECT COUNT(id_nombre_diente) "+
				  "FROM odont_nombre_diente"
				);
		rs.next();
		if (rs.getString(1) != null) {
			numero_dientes = Integer.parseInt(rs.getString(1));
		}
	} catch (Exception ex) {
		ex.getMessage();
	}
	
	return numero_dientes;
 
	
}



// metodo para obtener la generalidades del diente
public String seleccionargeneralidadporDiente (String id_historia_odontologica_fk, String id_nombre_diente_fk){
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	String cadena= "";
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT "+
				"id_generalidad_fk,"+
				"id_direccion_generalidad_fk"+
				" FROM odont_generalidad_diente"+
				" WHERE "+
				"id_historia_odontologica_fk = '"+id_historia_odontologica_fk+"' "+
				"AND id_nombre_diente = '"+id_nombre_diente_fk+"' "+
				"AND valido = '1' "
				);
		
	
			while(rs.next()) {
				cadena += rs.getString(1)+",";
			    cadena += rs.getString(2)+"-";
			}
			
			if(cadena.length()>0){
				//System.out.print("cadena["+cadena +"]\n");
				cadena = cadena.substring(0,cadena.length()-1);
				return cadena;
			}


		
	} catch (Exception ex) {
		ex.getMessage();
	}
	
	return cadena;
	
	
	
}
// metodo para cambiar la genreralidad de un diente si es o no valida, se utiliza para hacer actualizaciones
public boolean cambiarestadogeneralidaddiente(String id_historia_odontologica_fk, String id_Estado, String idDiente){
	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
				"UPDATE odont_generalidad_diente "+ 
				"SET "+ 			
				"valido = ? "+
				"WHERE id_historia_odontologica_fk = '"+id_historia_odontologica_fk+"' "+
				"AND id_nombre_diente = '"+idDiente+"'");
		         
		
		psc.setString(1, id_Estado);
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



public boolean eliminarTratamiento(String codTratamiento){
	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
				"UPDATE odont_tratamiento_odontologico "+
				"SET estado_informe = ? "+
				"WHERE id_tratamiento_odontologico = '"+codTratamiento+"'");

		psc.setString(1, "0");
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


public boolean eliminarHistoria(String codHistoria){
	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
				"UPDATE odont_historia_odontologica "+
				"SET estado_historia = ? "+
				"WHERE id_historia_odontologica ='"+codHistoria+"'");

		psc.setString(1, "0");
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

public boolean eliminarExamenClinico(String codHistoria){
	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
				"UPDATE odont_examen_clinico "+
				"SET "+ 
				"estado = ? "+
				" WHERE "+
				"id_historia_odontologica_fk ='"+codHistoria+"'" );

		psc.setString(1, "0");
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

public boolean eliminarAntecedentes(String codHistoria){
	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
				"UPDATE odont_antecedentes_medicos_odontologicos "+
				"SET "+ 
				"estado = ? "+
				"WHERE id_historia_odontologica_fk ='"+codHistoria+"'"  );

		psc.setString(1, "0");
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



public boolean eliminarImagenesDientesHistoria(String codHistoria){
	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
				"UPDATE odont_descripcion_imagenes_dientes "+
				"SET estado_descripcion = ? "+
				"WHERE id_historia_odontologica_fk = '" +codHistoria+"'"  );

		
		;
		
		
		psc.setString(1, "0");
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


 







}
