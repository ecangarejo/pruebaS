package rmc_metodo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import sun.misc.BASE64Decoder;

import adm_logica.Conexion;

public class MetodoCrearRmc {

	public MetodoCrearRmc() {
		// TODO Auto-generated constructor stub
	}
	
	
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
	/***Metodo de inserccion de Segmentacion Ventricular Izquierda**/
	/***************************************************************/
	public boolean InsertarImagenSegmentacionVentricular(String idInforme, String imagenVentricularIzquierda){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenVentricularIzquierda);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE rmc_vol_masa_general "+
						"SET "+
						"imagen_segmentacion_ventricular = ? "+
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
	
	
	/***************************************************************/
	/***Metodo de inserccion de imagen Fibrosis**/
	/***************************************************************/
	public boolean InsertarImagenFibrosis(String idInforme, String imagenFibrosis){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenFibrosis);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE rmc_tisular "+
						"SET "+
						"imagen_fibrosis = ? "+
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
	
	/***************************************************************/
	/***Metodo de inserccion de imagen edema**/
	/***************************************************************/
	public boolean InsertarImagenEdema(String idInforme, String imagenEdema){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenEdema);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE rmc_tisular "+
						"SET "+
						"imagen_edema = ? "+
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
	
	
	/***************************************************************/
	/***Metodo de inserccion imagen hemorragia**/
	/***************************************************************/
	public boolean InsertarImagenHemorragia(String idInforme, String imagenHemorragia){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenHemorragia);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE rmc_tisular "+
						"SET "+
						"imagen_hemorragia = ? "+
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
	
	
	/***************************************************************/
	/***Metodo de inserccion imagen omv**/
	/***************************************************************/
	public boolean InsertarImagenOmv(String idInforme, String imagenOmv){
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] dataBytes;
		try {
			dataBytes = decoder.decodeBuffer(imagenOmv);
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						"UPDATE rmc_tisular "+
						"SET "+
						"imagen_omv = ? "+
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
	
	
//insertar fibrosis
	public boolean InsertarFibrosis(
			String idInforme,
			String fibrosis
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "UPDATE rmc_tisular "+
				   "SET "+
					"fibrosis = ? "+
					"WHERE idinforme_fk = '"+idInforme+"'");
			psc.setString(1, fibrosis);
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
	
	//insertar edema
	public boolean InsertarEdema(
			String idInforme,
			String edema
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "UPDATE rmc_tisular "+
				   "SET "+
					"edema = ? "+
					"WHERE idinforme_fk = '"+idInforme+"'");
			psc.setString(1, edema);
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
	
	//insertar hemorragia
	public boolean InsertarHemorragia(
			String idInforme,
			String hemorragia
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "UPDATE rmc_tisular "+
				   "SET "+
					"hemorragia = ? "+
					"WHERE idinforme_fk = '"+idInforme+"'");
			psc.setString(1, hemorragia);
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
	
	//insertar omv
	public boolean InsertarOmv(
			String idInforme,
			String omv
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "UPDATE rmc_tisular "+
				   "SET "+
					"omv = ? "+
					"WHERE idinforme_fk = '"+idInforme+"'");
			psc.setString(1, omv);
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
	
	//insertar ritmo
	public boolean actualizarRitmo(
			String idInforme,
			String  ritmo
			) {
		
		
			PreparedStatement psc = null;
			
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "UPDATE rmc_vol_masa_general "+
					   "SET "+
			           "idritmo_fk = (SELECT id_ritmo FROM  rmc_ritmo WHERE ritmo=? )" +
			           "WHERE idinforme_fk = '"+idInforme+"'");
			           
				psc.setString(1, ritmo);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		
		
		
		
            return true;		

	}
	
	//buscar ritmo
	public java.sql.ResultSet buscarRitmo(String ritmo) {
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT ritmo FROM rmc_ritmo WHERE ritmo='"+ritmo+"'"); 
		} catch (Exception ex) {
			System.out.println("Error en MetodoCrearRmc>>BuscarRitmo "	+ ex);
		}
		return rs;
		
		
		
	}
	
	
	/*public Vector <String> obtenerTipodeDatosColumnasPorTabla(String nombreTabla){
		
	
		
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
		            	
		            			
		            	
		            	
		            	vectorResultado.add(rsmd.getColumnTypeName(i));
		            	
		                System.out.println("columna=" + rsmd.getTableName(i) + "."
		                        + rsmd.getColumnName(i) + " --> "
		                        + rsmd.getColumnTypeName(i));
}

		        } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }catch (Exception ex) {
		        	 ex.printStackTrace();
				}
		    
		return vectorResultado;
	}
*/
	
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
	
public java.sql.ResultSet buscarInformesDeCardiologiaRealizados(String TipoDocumento,
		String NumeroDocumento) {
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT "+
				"CONCAT(pac.nombre,'',pac.primer_apellido,'',pac.segundo_apellido),"+
				"inf.idInformeEcocardio,"+
				"CONCAT(inf.fecha_informe,'/',inf.hora_informe),"+
				"pac_codigo_paciente "+
				"FROM "+
				"adm_paciente pac,"+
				"rmc_encabezado_informe inf "+
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
	
	public int codigoActualInformeRmc() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(idInformeEcocardio) FROM rmc_encabezado_informe");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}
	
	
	public int codigoActualGrosor() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(id_grosor_septum) FROM rmc_grosor_septum");
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
	/**********Informe Ecocardio************/
	/*****************************************/ 

	
	public boolean insertarEncabesadoInformeRmc(
			String fecha,
			String hora, 
			String cod_usuario, 
			String cod_paciente,
			String estado,
			String tipo_indicacion,
			String tipo_aspecto_aorta,
			String tipo_indicacion2,
			String tipo_indicacion3,
			String tipo_indicacion4,
			String exploracion
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO rmc_encabezado_informe"+
		           "(codusuario," +
		           "codpaciente," +
		           "estado," +
		           "fecha_informe," +
		           "hora_informe," +
		           "id_indicacion_fk," +
		           "id_aspecto_aorta_fk,"+
		           "id_indicacion2_fk,"+
		           "id_indicacion3_fk,"+
		           "id_indicacion4_fk,"+
		           "id_exploracion_fk" +
		           ")" +
		           " VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			psc.setString(1, cod_usuario);
			psc.setString(2, cod_paciente);
			psc.setString(3, estado);
			psc.setString(4, fecha);
			psc.setString(5, hora);
			psc.setString(6, tipo_indicacion);
			psc.setString(7, tipo_aspecto_aorta);
			psc.setString(8, tipo_indicacion2);
			psc.setString(9, tipo_indicacion3);
			psc.setString(10, tipo_indicacion4);
			psc.setString(11, exploracion);
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
	
	
	
	public boolean asociarObservacionesInformeRmc(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO rmc_observaciones_generales_informe"+
		           "(idInforme_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInformeEco);
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
	
	
	public boolean asociarTisular(
			String IdInforme
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO rmc_tisular"+
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
	

	public boolean asociarAnalisisVentriculoIzquierdoInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO rmc_vol_masa"+
		           "(idInforme_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInformeEco);
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
	
	
	public boolean asociarGrosorSeptumInformeRmc(
			String IdInformeEco,
			String [] valores_grosor
			) {
		
		
			PreparedStatement psc = null;
			
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO rmc_grosor_septum"+
			           "(idInforme_fk)" +
			           " VALUES(?)");
				psc.setString(1, IdInformeEco);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		
		
		
		
            return true;		

	}
	
	
	

		
		
	
	
	
	public boolean asociarMotilidadVentricularIzquierdaInformeRmc(String idInforme_fk, String id_sector_segmentacion_fk, String [] valores_motilidad_por_segmento){
		
		
		String codVentriculoIzquierdo_fk= ""+codigoActualVentriculoIzquierdo(idInforme_fk);
		
		for(int i =0; i < valores_motilidad_por_segmento.length; i++){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO rmc_segmentacion_ventricular_izquierda"+
			           "(id_sector_segmentacion_fk,"+
                       "id_ventriculo_izquierdo_fk"+
			           ")" +
			           " VALUES(?,?)");
				psc.setString(1, id_sector_segmentacion_fk);
				psc.setString(2, codVentriculoIzquierdo_fk);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			
			
		}
		
		return true;
		
		
		
	}
	
	
	public int codigoActualVentriculoIzquierdo(String idInforme_fk) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT id_ventriculo_izquierdo FROM rmc_vol_masa WHERE idInforme_fk='"+idInforme_fk+"'");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}
	
public Vector<String> codigosActualesMotilidadSegmentosVentriculoIzquierdoInformeEco(String id_ventriculo_izquierdo_fk, String id_sector_segmentacion_fk) {
		
		Vector <String> vectorResultado = new Vector<String>();
		java.sql.ResultSet rs = null;
		Statement st = null;

		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			
			/*System.out.print("SELECT id_segmentacion_ventricular_izquierda " +
					"FROM eco_segmentacion_ventricular_izquierda " +
					"WHERE id_ventriculo_izquierdo_fk='"+id_ventriculo_izquierdo_fk+"' " +
					"AND id_sector_segmentacion_fk='"+id_sector_segmentacion_fk+"' " +
					"ORDER BY id_segmentacion_ventricular_izquierda ASC");
			*/
			
			rs = st.executeQuery(
					"SELECT id_segmentacion_ventricular_izquierda " +
					"FROM rmc_segmentacion_ventricular_izquierda " +
					"WHERE id_ventriculo_izquierdo_fk='"+id_ventriculo_izquierdo_fk+"' " +
					"AND id_sector_segmentacion_fk='"+id_sector_segmentacion_fk+"' " +
					"ORDER BY id_segmentacion_ventricular_izquierda ASC");
		  	while(rs.next()) {

          		vectorResultado.add(rs.getString(1));
			}
    	} catch (Exception ex) {
			ex.getMessage();
		}

		return vectorResultado;
	}
	
	public boolean actualizarObservacionesInformeRmc(
			String IdInformeEco,
			String [] observaciones_generales 
			) {
		
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_observaciones_generales_informe");	
	int [] columnasnodeseadas = {0,22};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!observaciones_generales[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE rmc_observaciones_generales_informe"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				
				psc.setObject(1, observaciones_generales[i].toString());
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
	
	public boolean actualizarGrosorSeptumInformeRmc(
			String IdInformeEco,
			String [] GrosorSeptum
			) {
	
		int codigo = codigoActualGrosor();
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_grosor_septum");	
		int [] columnasnodeseadas = {0, 3};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > GrosorSeptum.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< GrosorSeptum.length; i++){
				arregloTemporal[i]=GrosorSeptum[i];
			}
			
			for (int i = GrosorSeptum.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			GrosorSeptum = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		PreparedStatement psc = null;
		if(!GrosorSeptum[i].equals("")){
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE rmc_grosor_septum"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE id_grosor_septum ='"+ codigo+"'");
				psc.setObject(1, GrosorSeptum[i]);
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
	

	public boolean actualizarVentriculoIzquierdoInformeEco(
			String IdInformeEco,
			String [] VentriculoIzquierdo
			) {
	
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_vol_masa");	
		int [] columnasnodeseadas = {0,20,21};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > VentriculoIzquierdo.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< VentriculoIzquierdo.length; i++){
				arregloTemporal[i]=VentriculoIzquierdo[i];
			}
			
			for (int i = VentriculoIzquierdo.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			VentriculoIzquierdo = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		PreparedStatement psc = null;
		if(!VentriculoIzquierdo[i].equals("")){
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE rmc_vol_masa"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, VentriculoIzquierdo[i]);
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
	
	
	public boolean actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeRmc(
			String IdInformeEco,
			String id_sector_segmentacion_fk,
			String [] generalidadesasociadas
			) {
		/*String [] parametros_tabla = {
				  "id_cara_segmentacion_fk",
				  "id_descripcion_segmetacion_fk",
					
		};*/
		String idVentriculoIzquierdo = ""+codigoActualVentriculoIzquierdo(IdInformeEco);
		Vector <String> codigosporSegmento= codigosActualesMotilidadSegmentosVentriculoIzquierdoInformeEco(idVentriculoIzquierdo,id_sector_segmentacion_fk);
	
	for (int i =0; i < generalidadesasociadas.length ; i++){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
					"UPDATE rmc_segmentacion_ventricular_izquierda"+
					" SET"+ 
					" id_cara_segmentacion_fk = ?,"+
					" id_descripcion_segmentacion_fk =?"+
					" WHERE "+
					" id_segmentacion_ventricular_izquierda="+codigosporSegmento.get(i)
			
);
			psc.setString(1, ""+(i+1));
			psc.setString(2, generalidadesasociadas[i]);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
			return true;
}
	
	
	
	public boolean asociarAnalisisAspectosValvulasInformeEco(
			String IdInformeEco,
			String id_nombre_segmentacion_vi
			) {

		
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO rmc_grosor_septum"+
			           "(idInforme_fk,id_nombre_segmentacion_vi_fk )" +
			           " VALUES(?,?)");
				psc.setString(1, IdInformeEco);
				psc.setString(2, id_nombre_segmentacion_vi);
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		
		return true;
	}
	
	
public String codigoActualAnalisisAspectoValvulaInformeEco(String idInforme_fk, String id_nombre_segmentacion_vi) {
		
		String codigo="";
		java.sql.ResultSet rs = null;
		Statement st = null;

		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT id_nombre_segmentacion_vi_fk "+ 
								  "FROM rmc_grosor_septum "+ 
								  "WHERE idInforme_fk='"+idInforme_fk+"' "+
								  " AND id_nombre_segmentacion_vi_fk='"+id_nombre_segmentacion_vi+"' "
								  );

			if(rs.next()) {
				
		  		codigo =rs.getString(1);
			}
    	} catch (Exception ex) {
			ex.getMessage();
		}

		return codigo;
	}


public boolean actualizarAnalisisAspectosValvulasInformeEco(
		String IdInformeEco,
		String [] GrosorSeptum,
		String idGrosor
		){
	/*String [] parametros_tabla = {
			    "aspecto",
		        "esclerosis",
		        "calcificacion",
		        "esclorocalsificacion",
		        "engrosamiento",
		        "perforacion",
		        "en_domo",
		        "restriccion",
		        "plastia",
	};*/

	
	
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_grosor_septum");	
	int [] columnasnodeseadas = {0,1,11};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	

	String [] vectoralmacenamiento;
	String codActualanalisisInformeEco= codigoActualAnalisisAspectoValvulaInformeEco(IdInformeEco,idGrosor);
	vectoralmacenamiento =GrosorSeptum[Integer.parseInt(idGrosor)-1].split("-");

	
	
	for (int l =0; l < vectoralmacenamiento.length ; l++){
		
		PreparedStatement psc = null;
		if(!vectoralmacenamiento[l].equals("")){
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE rmc_grosor_septum"+
					       " SET "+parametros_tabla.get(l)+"=?"+
					       " WHERE id_grosor_septum ='"+codActualanalisisInformeEco +"'");
				psc.setObject(1, vectoralmacenamiento[l]);
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


//insertar ritmo

public boolean InsertarRitmo(
		String Ritmo
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_ritmo"+
	           "(ritmo)" +
	           " VALUES(?)");
		psc.setString(1, Ritmo);
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



//insertar alergias, medicacion, contraste, equipo, enfermera y radiologo

public boolean asociarAnalisisDatosGeneralesRmc(
		String IdInformeEco
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_datos_generales_informe"+
	           "(idInforme_fk)" +
	           " VALUES(?)");
		psc.setString(1, IdInformeEco);
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

//fin insertar alergias, medicacion, contraste, equipo, enfermera y radiologo


public boolean actualizarAnalisisDatosGeneralesRmc(
		String IdInformeEco,
		String [] DatosGenerales
		) {

	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_datos_generales_informe");	
	int [] columnasnodeseadas = {0,9};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > DatosGenerales.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< DatosGenerales.length; i++){
			arregloTemporal[i]=DatosGenerales[i];
		}
		
		for (int i = DatosGenerales.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		DatosGenerales = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!DatosGenerales[i].equals("")){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_datos_generales_informe"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
			psc.setString(1, DatosGenerales[i]);
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
	
//insercion y actualizacion en rmc_vol_masa_general

public boolean asociarVolMasaGeneralRmc(
		String IdInformeEco
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_vol_masa_general"+
	           "(idinforme_fk)" +
	           " VALUES(?)");
		psc.setString(1, IdInformeEco);
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




public boolean actualizarVolMasaGeneralRmc(
		String IdInformeEco,
		String [] VolMasaGeneral
		) {

	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_vol_masa_general");	
	int [] columnasnodeseadas = {0,6,9,10};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > VolMasaGeneral.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< VolMasaGeneral.length; i++){
			arregloTemporal[i]=VolMasaGeneral[i];
		}
		
		for (int i = VolMasaGeneral.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		VolMasaGeneral = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!VolMasaGeneral[i].equals("")){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_vol_masa_general"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idinforme_fk ='"+ IdInformeEco+"'");
			psc.setString(1, VolMasaGeneral[i]);
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
	



//fin insercion y actualizacion en rmc_vol_masa_general


//insercion y actualizacion en rmc_vol_masa_vf

public boolean asociarVolMasaVfRmc(
		String IdInformeEco
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_vol_masa_vf"+
	           "(idinforme_fk)" +
	           " VALUES(?)");
		psc.setString(1, IdInformeEco);
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




public boolean actualizarVolMasaVfRmc(
		String IdInformeEco,
		String [] VolMasaVf
		) {

	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_vol_masa_vf");	
	int [] columnasnodeseadas = {0,7};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > VolMasaVf.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< VolMasaVf.length; i++){
			arregloTemporal[i]=VolMasaVf[i];
		}
		
		for (int i = VolMasaVf.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		VolMasaVf = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!VolMasaVf[i].equals("")){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_vol_masa_vf"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idinforme_fk ='"+ IdInformeEco+"'");
			psc.setString(1, VolMasaVf[i]);
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
	



//fin insercion y actualizacion en rmc_vol_masa_vf


//insercion y actualizacion en rmc_medidas_sistole

public boolean asociarMedidasSistoleRmc(
		String IdInformeEco
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_medidas_sistole"+
	           "(idinforme_fk)" +
	           " VALUES(?)");
		psc.setString(1, IdInformeEco);
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








public boolean actualizarMedidasSistole(
		String IdInformeEco,
		String [] MedidasSistole
		) {

	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_medidas_sistole");	
	int [] columnasnodeseadas = {0,5};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > MedidasSistole.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< MedidasSistole.length; i++){
			arregloTemporal[i]=MedidasSistole[i];
		}
		
		for (int i = MedidasSistole.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		MedidasSistole = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!MedidasSistole[i].equals("")){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_medidas_sistole"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idinforme_fk ='"+ IdInformeEco+"'");
			psc.setString(1, MedidasSistole[i]);
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
	



//fin insercion y actualizacion en rmc_medidas_sistole

//insercion y actualizacion en rmc_calculo_hipertrofia

public boolean asociarCalculoHipertrofiaRmc(
		String IdInformeEco
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_calculo_hipertrofia"+
	           "(idinforme_fk)" +
	           " VALUES(?)");
		psc.setString(1, IdInformeEco);
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




public boolean actualizarCalculoHipertrofia(
		String IdInformeEco,
		String [] CalculoHipertrofia
		) {

	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_calculo_hipertrofia");	
	int [] columnasnodeseadas = {0,4};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > CalculoHipertrofia.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< CalculoHipertrofia.length; i++){
			arregloTemporal[i]=CalculoHipertrofia[i];
		}
		
		for (int i = CalculoHipertrofia.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		CalculoHipertrofia = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!CalculoHipertrofia[i].equals("")){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_calculo_hipertrofia"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idinforme_fk ='"+ IdInformeEco+"'");
			psc.setString(1, CalculoHipertrofia[i]);
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
	



//fin insercion y actualizacion en rmc_calculo_hipertrofia


//insercion y actualizacion en rmc_ventriculo_derecho

public boolean asociarVentriculoDerechoRmc(
		String IdInformeEco
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_ventriculo_derecho"+
	           "(idinforme_fk)" +
	           " VALUES(?)");
		psc.setString(1, IdInformeEco);
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




public boolean actualizarVentriculoDerecho(
		String IdInformeEco,
		String [] VentriculoDrecho
		) {

	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_ventriculo_derecho");	
	int [] columnasnodeseadas = {0,7};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > VentriculoDrecho.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< VentriculoDrecho.length; i++){
			arregloTemporal[i]=VentriculoDrecho[i];
		}
		
		for (int i = VentriculoDrecho.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		VentriculoDrecho = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!VentriculoDrecho[i].equals("")){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_ventriculo_derecho"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idinforme_fk ='"+ IdInformeEco+"'");
			psc.setString(1, VentriculoDrecho[i]);
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
	
//fin insercion y actualizacion en rmc_ventriculo_derecho


public boolean asociarAnalisisAuriculaIzquierda(
		String IdInforme
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_auricula_izquierda"+
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


public boolean asociarAnalisisAuriculaDerecha(
		String IdInforme
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_auricula_derecha"+
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


public boolean actualizarAuriculaIzquierda(
		String IdInforme,
		String [] AuriculaIzquierda
		) {
	
	
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_auricula_izquierda");	
	int [] columnasnodeseadas = {0,9};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > AuriculaIzquierda.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< AuriculaIzquierda.length; i++){
			arregloTemporal[i]=AuriculaIzquierda[i];
		}
		
		for (int i = AuriculaIzquierda.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		AuriculaIzquierda = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!AuriculaIzquierda[i].equals("")){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_auricula_izquierda"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idinforme_fk ='"+ IdInforme+"'");
			psc.setObject(1, AuriculaIzquierda[i]);
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

public boolean actualizarAuriculaDerecha(
		String IdInforme,
		String [] AuriculaDerecha
		) {
	/*String [] parametros_tabla = {
			
			"area"
				
	};*/
	
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_auricula_derecha");	
	int [] columnasnodeseadas = {0,7};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > AuriculaDerecha.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< AuriculaDerecha.length; i++){
			arregloTemporal[i]=AuriculaDerecha[i];
		}
		
		for (int i = AuriculaDerecha.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		AuriculaDerecha = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!AuriculaDerecha[i].equals("")){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_auricula_derecha"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idInforme_fk ='"+ IdInforme+"'");
			psc.setString(1, AuriculaDerecha[i]);
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



public boolean asociarPericardio(
		String IdInforme
		) {

	PreparedStatement psc = null;
	try {
		Conexion con = new Conexion();
		psc = con.conn.prepareStatement(
			   "INSERT INTO rmc_pericardio"+
	           "(idInforme_fk)" +
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



public boolean actualizarPericardio(
		String IdInforme,
		String [] Pericardio
		) {

	
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("rmc_pericardio");	
	int [] columnasnodeseadas = {0,9};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > Pericardio.length){
		
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< Pericardio.length; i++){
			arregloTemporal[i]=Pericardio[i];
		}
		
		for (int i = Pericardio.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		Pericardio = arregloTemporal;
	}
	
for (int i =0; i < parametros_tabla.size() ; i++){
	if(!Pericardio[i].equals("")){
		
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE rmc_pericardio"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idInforme_fk ='"+ IdInforme+"'");
			psc.setString(1, Pericardio[i]);
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


//metodo que genera la consulta del select indicacion_informe2 teniendo en cuenta el valor escogido en
//el select anterior para omitirlo en la busqueda

public java.sql.ResultSet buscarIndicacion2(String indicacion2) {
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT * FROM rmc_indicaciones "+
				"WHERE "+
				"id_indicacion != '"+indicacion2+"' "); 
	} catch (Exception ex) {
		System.out.println("Error en MetodoCrearRmc>>buscarIndicacion2 "	+ ex);
	}
	return rs;
	
	
	
}


public java.sql.ResultSet buscarIndicacion3(String indicacion2, String indicacion3) {
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT * FROM rmc_indicaciones "+
				"WHERE "+
				"(id_indicacion != '"+indicacion2+"' AND id_indicacion != '"+indicacion3+"');"); 
	} catch (Exception ex) {
		System.out.println("Error en MetodoCrearRmc>>buscarIndicacion2 "	+ ex);
	}
	return rs;
	
	
	
}

public java.sql.ResultSet buscarIndicacion4(String indicacion2, String indicacion3, String indicacion4) {
	
	java.sql.ResultSet rs = null;
	Statement st = null;
	try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery(
				"SELECT * FROM rmc_indicaciones "+
				"WHERE "+
				"(id_indicacion != '"+indicacion2+"' AND id_indicacion != '"+indicacion3+"' AND id_indicacion != '"+indicacion4+"');"); 
	} catch (Exception ex) {
		System.out.println("Error en MetodoCrearRmc>>buscarIndicacion2 "	+ ex);
	}
	return rs;
	
	
	
}


}
