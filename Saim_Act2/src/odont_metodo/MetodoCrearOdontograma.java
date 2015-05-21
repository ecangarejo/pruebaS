package odont_metodo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import sun.misc.BASE64Decoder;

import adm_logica.Conexion;

public class MetodoCrearOdontograma {
	
	public MetodoCrearOdontograma() {
		// TODO Auto-generated constructor stub
	}
	
	
	public java.sql.ResultSet buscarAdm(String adm){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM odont_encabezado_informe WHERE admpaciente='"+adm+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearOdontograma>>buscarAdm "+ex);
        }	
        return rs;
    }
	
	
	//metodo que obtiene todas las columnas de una tabla
	
	public Vector <String> obtenerNombresColumnasPorTabla(String nombreTabla){
		Vector <String> vectorResultado = new Vector<String>();
		java.sql.ResultSet rs = null;
		Statement st = null;
		ResultSetMetaData rsmd = null;
		         try {
		 			Conexion con = new Conexion();
		 			st = con.conn.createStatement();
		            rs = st.executeQuery("select * from "+nombreTabla);
		            rsmd=rs.getMetaData();
		        	
		            int numeroColumnas = rsmd.getColumnCount();

		            for (int i = 1; i <= numeroColumnas; i++) {
		            	
		            	vectorResultado.add(rsmd.getColumnName(i)); 
		             /*   System.out.println("columna=" + rsmd.getTableName(i) + "."
		                        + rsmd.getColumnName(i) + " --> "
		                        + rsmd.getColumnTypeName(i));*/
		            }

		        } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }catch (Exception ex) {
		        	 ex.printStackTrace();
				}
		    
		return vectorResultado;
	}
	
	
	/***************************************************************/
	/***Metodo de inserccion de imagen odontograma**/
	/***************************************************************/
	public boolean InsertarImagenOdontograma(String idInforme, String imagenOdontograma){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenOdontograma);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE odont_informe_general "+
						"SET "+
						"img_odontograma = ? "+
 						"WHERE idinforme_fk = '"+idInforme+"'");
					
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
	
	
	
	

	
	
	
	public Vector<String> VectorEliminarDatosArreglos(Vector<String> arecorrer, int [] posvalores){
		//int nuevaposicion =0;
		System.out.print("-----------"+arecorrer.toString() +"\n");
		for (int i=0; i<posvalores.length; i++){
			arecorrer.remove(posvalores[i]);
			if(i < posvalores.length-1){
				for (int j=i; j<posvalores.length; j++){
					posvalores[j]= posvalores[j]-1;
			
				}
			}
			
		
			
		}
		return arecorrer;
		
		
	}
	
	//metodo que trae todos lo odontogramas realizados por paciente(pide como parametro numero y tipo documento)
	
public java.sql.ResultSet buscarOdontogramasRealizados(String TipoDocumento,
		String NumeroDocumento) {
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT "+
				"CONCAT(pac.nombre ,' ',pac.primer_apellido ,' ',pac.segundo_apellido),"+
				"inf.idInformeOdontograma,"+
				"CONCAT(inf.fecha_informe,'/',inf.hora_informe),"+
				"pac_codigo_paciente "+
				"FROM "+
				"adm_paciente pac,"+
				"odont_encabezado_informe inf "+
				"WHERE "+
				"pac.tipo_documento = '"+TipoDocumento+"' "+
				"AND pac.numero_documento='"+NumeroDocumento+"' "+
				"AND pac.pac_codigo_paciente = inf.codpaciente "+     
				"AND inf.estado='1'"); 
	} catch (Exception ex) {
		System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "	+ ex);
	}
	return rs;
	
	
	
}


//metodo que trae codigo actual del informe odontograma

public String codigoActualInformePaciente(String cod_paciente) {
	java.sql.ResultSet rs = null;
	Statement st = null;
	String valor = "0";
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT MAX(idInformeOdontograma) FROM odont_encabezado_informe WHERE codpaciente= '"+cod_paciente+"'");
		rs.next();
		if (rs.getString(1) != null) {
			valor = rs.getString(1);
		}
	} catch (Exception ex) {
		ex.getMessage();
	}

	return valor;
}

//metodo que trae codigo actual del informe odontograma

public int codigoActualInformeOdont() {
	java.sql.ResultSet rs = null;
	Statement st = null;
	int valor = 1;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT MAX(idInformeOdontograma) FROM odont_encabezado_informe");
		rs.next();
		if (rs.getString(1) != null) {
			valor = Integer.parseInt(rs.getString(1));
		}
	} catch (Exception ex) {
		ex.getMessage();
	}

	return valor;
}




//metodo que trae el codigo actual de antecedentes odontologicos
public int codigoActualantecedentesMed() {
	java.sql.ResultSet rs = null;
	Statement st = null;
	int valor = 1;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT MAX(id_antecedentes_odont) FROM odont_antecendentes_odont");
		rs.next();
		if (rs.getString(1) != null) {
			valor = Integer.parseInt(rs.getString(1));
		}
	} catch (Exception ex) {
		ex.getMessage();
	}

	return valor;


}

//metodo que trae el codigo actual de examenes clinico
public int codigoActualexamclinico() {
	java.sql.ResultSet rs = null;
	Statement st = null;
	int valor = 1;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT MAX(id_examen_clinico_odont) FROM odont_examen_clinico");
		rs.next();
		if (rs.getString(1) != null) {
			valor = Integer.parseInt(rs.getString(1));
		}
	} catch (Exception ex) {
		ex.getMessage();
	}

	return valor;


}


//metodo para buscar paciente por tipo y numero de documento
	
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
					+ "ent.nombre_entidad,"
					+ "apac.genero  "+
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
	
	
	//metodo para buscar paciente por codigo

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
					+ "ent.nombre_entidad,"
					+ "apac.genero  "+
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
	
	

	
	
	
	
	/*****************************************/
	/******Metodo Insertar Encabezado*********/
	/**********Informe odontograma************/
	/*****************************************/ 

	
	public boolean insertarEncabesadoInformeOdont(
			String fecha,
			String hora, 
			String cod_usuario, 
			String cod_paciente,
			String estado,
			String adm_pac
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO odont_encabezado_informe"+
		           "(codusuario," +
		           "codpaciente," +
		           "estado," +
		           "fecha_informe," +
		           "hora_informe," +
		           "admpaciente" +
		           ")" +
		           " VALUES(?,?,?,?,?,?)");
			psc.setString(1, cod_usuario);
			psc.setString(2, cod_paciente);
			psc.setString(3, estado);
			psc.setString(4, fecha);
			psc.setString(5, hora);
			psc.setString(6, adm_pac);
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
	
	//metodo que genera una nueva fila en bd con el idinforme en odont_informe_general
	
	public boolean asociarInformeGeneral(
			String IdInformeOdont
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO odont_informe_general"+
		           "(idinforme_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInformeOdont);
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
	
	
//metodo que genera una nueva fila en bd con el idinforme en odont_informe_general
	
	public boolean asociarPromPrev(
			String IdInformeOdont
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO odont_prom_prev"+
		           "(idinforme_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInformeOdont);
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
	
	
//metodo que genera una nueva fila en bd con el idinforme en odont_edu_oral
	
	public boolean asociarEduOral(
			String IdInformeOdont
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO odont_edu_oral"+
		           "(idinforme_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInformeOdont);
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
	
	
	//metodo que genera una nueva fila en bd con el idinforme en odont_examen_clinico
	public boolean asociarExamenClinico(
			String IdInforme
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO odont_examen_clinico "+
		           "(idinforme_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInforme);
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
	
//metodo que genera una nueva fila en bd con el idinforme en odont_antecendentes_odont
	public boolean asociarAntecedentes(
			String IdInforme
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO odont_antecendentes_odont"+
		           "(idinforme_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInforme);
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


//metodo que inserta las observaciones motivo consulta

	

	public boolean actualizarInformeGeneralOdont(
			String IdInforme,
			String [] MotivoConsulta
			) {

		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_informe_general");	
		int [] columnasnodeseadas = {0,1,5};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > MotivoConsulta.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< MotivoConsulta.length; i++){
				arregloTemporal[i]=MotivoConsulta[i];
			}
			
			for (int i = MotivoConsulta.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			MotivoConsulta = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!MotivoConsulta[i].equals("")){
			
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
							       "UPDATE odont_informe_general"+
							       " SET "+parametros_tabla.get(i)+"=?"+
							       " WHERE idinforme_fk ='"+ IdInforme+"'");
				psc.setString(1, MotivoConsulta[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
	}
	
	
//metodo que inserta las observaciones motivo consulta

	

	public boolean actualizarprom_prev(
			String IdInforme,
			String [] prom_prev
			) {

		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_prom_prev");	
		int [] columnasnodeseadas = {0,7};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > prom_prev.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< prom_prev.length; i++){
				arregloTemporal[i]=prom_prev[i];
			}
			
			for (int i = prom_prev.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			prom_prev = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!prom_prev[i].equals("")){
			
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
							       "UPDATE odont_prom_prev"+
							       " SET "+parametros_tabla.get(i)+"=?"+
							       " WHERE idinforme_fk ='"+ IdInforme+"'");
				psc.setString(1, prom_prev[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println ("ERROR EN METODO ");
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
	}
	
	
	
//metodo que inserta las observaciones motivo consulta

	

	public boolean actualizarEduOral(
			String IdInforme,
			String [] edu_oral
			) {

		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_edu_oral");	
		int [] columnasnodeseadas = {0,8};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > edu_oral.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< edu_oral.length; i++){
				arregloTemporal[i]=edu_oral[i];
			}
			
			for (int i = edu_oral.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			edu_oral = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!edu_oral[i].equals("")){
			
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
							       "UPDATE odont_edu_oral"+
							       " SET "+parametros_tabla.get(i)+"=?"+
							       " WHERE idinforme_fk ='"+ IdInforme+"'");
				psc.setString(1, edu_oral[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
	}
	
	
//metodo que actualiza la tabla antecedentes odontologicos

	
	public boolean actualizarAntecedentesOdont(
			String IdInforme,
			String [] antecedentes
			) {
	
		int codigo = codigoActualantecedentesMed();
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_antecendentes_odont");	
		int [] columnasnodeseadas = {0, 4};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > antecedentes.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< antecedentes.length; i++){
				arregloTemporal[i]=antecedentes[i];
			}
			
			for (int i = antecedentes.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			antecedentes = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		PreparedStatement psc = null;
		if(!antecedentes[i].equals("")){
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE odont_antecendentes_odont"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE id_antecedentes_odont ='"+ codigo+"'");
				psc.setObject(1, antecedentes[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
}
	
	
	
//metodo que actualiza la tabla examen clinico

	

	public boolean actualizarExamClinico(
			String IdInforme,
			String [] ExamClinico
			) {

		int codigo = codigoActualexamclinico();
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("odont_examen_clinico");	
		int [] columnasnodeseadas = {0, 4};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > ExamClinico.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< ExamClinico.length; i++){
				arregloTemporal[i]=ExamClinico[i];
			}
			
			for (int i = ExamClinico.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			ExamClinico = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		PreparedStatement psc = null;
		if(!ExamClinico[i].equals("")){
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE odont_examen_clinico"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE id_examen_clinico_odont ='"+ codigo+"'");
				psc.setObject(1, ExamClinico[i]);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		}
		
	}
			return true;
	

 }
	
	
	
}//fin metodo odontograma
