package eco_Cardiologia;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import sun.misc.BASE64Decoder;




import adm_logica.Conexion;

public class Metodos_cardiologia {

	public Metodos_cardiologia() {
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
						"UPDATE eco_ventriculo_izquierdo "+
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
				"eco_encabezado_informe inf "+
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
	
	public int codigoActualInformeEco() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(idInformeEcocardio) FROM eco_encabezado_informe");
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

	
	public boolean insertarEncabesadoInformeEco(
			String fecha,
			String hora, 
			String cod_usuario, 
			String cod_paciente,
			String estado,
			String tipo_indicacion,
			String tipo_aspecto_aorta
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_encabezado_informe"+
		           "(codusuario," +
		           "codpaciente," +
		           "estado," +
		           "fecha_informe," +
		           "hora_informe," +
		           "id_indicacion_fk," +
		           "id_aspecto_aorta_fk"+
		           ")" +
		           " VALUES(?,?,?,?,?,?,?)");
			psc.setString(1, cod_usuario);
			psc.setString(2, cod_paciente);
			psc.setString(3, estado);
			psc.setString(4, fecha);
			psc.setString(5, hora);
			psc.setString(6, tipo_indicacion);
			psc.setString(7, tipo_aspecto_aorta);
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
	
	
	
	public boolean asociarObservacionesInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_observaciones_generales_informe_eco_cardio"+
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
	
	
	
	
	public boolean asociarAnalisisAortaInformeEco(
			String IdInformeEco,
			int medidasAnalisis
			) {

		for (int i=0; i < medidasAnalisis; i++ ){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO eco_analisis_aorta"+
			           "(idInforme_fk,id_tipo_analisis_aorta_fk )" +
			           " VALUES(?,?)");
				psc.setString(1, IdInformeEco);
				psc.setString(2, ""+(i+1));
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
			String id_valvula
			) {

		
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO eco_aspecto_valvulas"+
			           "(idInforme_fk,id_valvulas_fk )" +
			           " VALUES(?,?)");
				psc.setString(1, IdInformeEco);
				psc.setString(2, id_valvula);
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
	
	
	public boolean asociarInsuficienciaValvulaAortaInformeEco(
			String IdInformeEco
		
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_insuficiencia_valvula_aorta"+
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

	
	public boolean asociarInsuficienciaValvulaMitralInformeEco(
			String IdInformeEco
		
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_insuficiencia_valvula_mitral"+
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
	
	
	public boolean asociarInsuficienciaValvulaTricuspideaInformeEco(
			String IdInformeEco
		
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_insuficiencia_valvula_tricuspidea"+
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
	
	
	public boolean asociarInsuficienciaValvulaPulmonalInformeEco(
			String IdInformeEco
		
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_insuficiencia_valvula_pulmonal"+
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
	
	
	public boolean asociarEstenosisValvulaAortaInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_estenosis_valvula_aorta"+
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
	
	public boolean asociarEstenosisValvulaMitralInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_estenosis_valvula_mitral"+
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
	
	public boolean asociarEstenosisValvulaTricuspideaInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_estenosis_valvula_tricuspidea"+
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
	
	
	public boolean asociarEstenosisValvulaPulmonalInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_estenosis_valvula_Pulmonal"+
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
	
	
	public boolean asociarProtesisValvulaAorticaInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_valvula_aorta_protesica"+
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
	
	public boolean asociarProtesisValvulaMitralInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_valvula_mitral_protesica"+
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
	
	public boolean asociarProtesisValvulaTricuspideaInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_valvula_tricuspidea_protesica"+
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
	
	public boolean asociarProtesisValvulaPulmonalInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_valvula_pulmonal_protesica"+
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
	
	
	
	public boolean asociarAnalisisVentriculoIzquierdoInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_ventriculo_izquierdo"+
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
	
	public boolean asociarMotilidadVentricularIzquierdaInformeEco(String idInforme_fk, String id_sector_segmentacion_fk, String [] valores_motilidad_por_segmento){
		
		
		String codVentriculoIzquierdo_fk= ""+codigoActualVentriculoIzquierdo(idInforme_fk);
		
		for(int i =0; i < valores_motilidad_por_segmento.length; i++){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					   "INSERT INTO eco_segmentacion_ventricular_izquierda"+
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
	
	public boolean asociarAnalisisVentriculoDerechoInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_ventriculo_derecho"+
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
	
	
	public boolean asociarAnalisisAuriculaIzquierda(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_auricula_izquierda"+
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
	
	
	public boolean asociarAnalisisAuriculaDerecha(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_auricula_derecha"+
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
	
	public boolean asociarAnalisisVenaCavaInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_vena_cava"+
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
		 /* id_diametro_vena_cava_inferior_fk,
		  id_colapso_respiratorio_fk,
		  presion_rap
*/
	}
	
	public boolean asociarAnalisisPericardioInformeEco(
			String IdInformeEco
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO eco_analisis_pericardio"+
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
	
	
	public String codigoActualAnalisisAspectoValvulaInformeEco(String idInforme_fk, String id_valvula) {
		
		String codigo="";
		java.sql.ResultSet rs = null;
		Statement st = null;

		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT id_aspecto_valvulas "+ 
								  "FROM eco_aspecto_valvulas "+ 
								  "WHERE idInforme_fk='"+idInforme_fk+"' "+
								  " AND id_valvulas_fk='"+id_valvula+"' "
								  );

			if(rs.next()) {
				
		  		codigo =rs.getString(1);
			}
    	} catch (Exception ex) {
			ex.getMessage();
		}

		return codigo;
	}
	
	public Vector<String> codigosActualesAnalisisAortaInformeEco(String idInforme_fk) {
		
		Vector <String> vectorResultado = new Vector<String>();
		java.sql.ResultSet rs = null;
		Statement st = null;

		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT id_analisis_aorta FROM eco_analisis_aorta WHERE idInforme_fk='"+idInforme_fk+"' ORDER BY id_analisis_aorta ASC");
		  	while(rs.next()) {
				
          		vectorResultado.add(rs.getString(1));
			}
    	} catch (Exception ex) {
			ex.getMessage();
		}

		return vectorResultado;
	}
	
	public int codigoActualVentriculoIzquierdo(String idInforme_fk) {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT id_ventriculo_izquierdo FROM eco_ventriculo_izquierdo WHERE idInforme_fk='"+idInforme_fk+"'");
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
					"FROM eco_segmentacion_ventricular_izquierda " +
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
	public boolean actualizarAnalisisAortaInformeEco(
			String IdInformeEco,
			String [] medicionesAorta,
			String [] patologiasAorta
			){
		/*String [] parametros_tabla = {
				"medicion",
				"aneurisma",
				"diseccion",
				"ulcera",
				"trombo",
				"sintoma",
				"cuartacion"
		};
*/
		
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_aorta");	
		int [] columnasnodeseadas = {0,1,9};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
		Vector <String> patologiasParticulares = new Vector<String>();
		String cadenaasociacion ="";
		for (int i =0; i < medicionesAorta.length;i++){
			cadenaasociacion +=medicionesAorta[i]+"-"; // medicion
			if(i > 0){
		
				cadenaasociacion +=patologiasAorta[i-1]; // patologias
			}
			patologiasParticulares.add(cadenaasociacion);
			cadenaasociacion ="";
		}
		String [] vectoralmacenamiento;
		Vector <String> codActualanalisisInformeEco= codigosActualesAnalisisAortaInformeEco(IdInformeEco);
		for (int k =0; k < codActualanalisisInformeEco.size(); k++){
		vectoralmacenamiento =patologiasParticulares.get(k).split("-");
		for (int l =0; l < vectoralmacenamiento.length ; l++){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_aorta"+
					       " SET "+parametros_tabla.get(l)+"="+vectoralmacenamiento[l].toString()+
					       " WHERE id_analisis_aorta ='"+codActualanalisisInformeEco.elementAt(k) +"'");
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
	
	public boolean actualizarObservacionesInformeEco(
			String IdInformeEco,
			String [] observaciones_generales 
			) {
		
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_observaciones_generales_informe_eco_cardio");	
	int [] columnasnodeseadas = {0,22};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!observaciones_generales[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_observaciones_generales_informe_eco_cardio"+
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
	
	
	
	public boolean actualizarAnalisisAspectosValvulasInformeEco(
			String IdInformeEco,
			String [] AspectosValvulas,
			String idValvula
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

		
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_aspecto_valvulas");	
		int [] columnasnodeseadas = {0,1,11};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		

		String [] vectoralmacenamiento;
		String codActualanalisisInformeEco= codigoActualAnalisisAspectoValvulaInformeEco(IdInformeEco,idValvula);
    	vectoralmacenamiento =AspectosValvulas[Integer.parseInt(idValvula)-1].split("-");
	
		
		
		for (int l =0; l < vectoralmacenamiento.length ; l++){
			
			PreparedStatement psc = null;
			if(!vectoralmacenamiento[l].equals("")){
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement(
						       "UPDATE eco_aspecto_valvulas"+
						       " SET "+parametros_tabla.get(l)+"=?"+
						       " WHERE id_aspecto_valvulas ='"+codActualanalisisInformeEco +"'");
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
	
	public boolean actualizarInsuficienciasValvulaAortaInformeEco(
			String IdInformeEco,
			String [] insuficienciaValvulaAorta 
			) {

		
		
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_insuficiencia_valvula_aorta");	
		int [] columnasnodeseadas = {0,15};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
		if (parametros_tabla.size() > insuficienciaValvulaAorta.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< insuficienciaValvulaAorta.length; i++){
				arregloTemporal[i]=insuficienciaValvulaAorta[i];
			}
			
			for (int i = insuficienciaValvulaAorta.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			insuficienciaValvulaAorta = arregloTemporal;
		}
		
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!insuficienciaValvulaAorta[i].equals("")){
			PreparedStatement psc = null;
			try {
				
				//System.out.print("-"+insuficienciaValvulaAorta[i]+"-\n");
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_insuficiencia_valvula_aorta"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, insuficienciaValvulaAorta[i]);
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
	
	
	public boolean actualizarInsuficienciasValvulaMitralInformeEco(
			String IdInformeEco,
			String [] insuficienciaValvulaMitral 
			) {
		/*String [] parametros_tabla = {
	             "id_vena_contracta_valvula_mitral_fk",
	             "id_jet_central_valvula_mitral_fk",
	             "id_prolapso_de_valva_valvula_mitral_fk",
	             "id_flujo_venas_pulmonales_valvula_mitral_fk",
	             "id_flujo_transmitral_valvula_mitral_fk",
	             "id_densidad_valvula_mitral_fk",
	             "id_forma_jet_valvula_mitral_fk",
	             "id_tamaño_ventriculo_valvula_mitral_fk",
	             "vtima",
	             "pad",
	             "vtipo",
	             "rvol",
	             "rfmitral",
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_insuficiencia_valvula_mitral");	
		int [] columnasnodeseadas = {0,14};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
		if (parametros_tabla.size() > insuficienciaValvulaMitral.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< insuficienciaValvulaMitral.length; i++){
				arregloTemporal[i]=insuficienciaValvulaMitral[i];
			}
			
			for (int i = insuficienciaValvulaMitral.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			insuficienciaValvulaMitral = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!insuficienciaValvulaMitral[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_insuficiencia_valvula_mitral"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, insuficienciaValvulaMitral[i]);
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
	
	
	public boolean actualizarInsuficienciasValvulaTricuspideaInformeEco(
			String IdInformeEco,
			String [] insuficienciaValvulaTricuspidea 
			) {
	/*	String [] parametros_tabla = {
				 "id_morfologia_valvula_tricuspidea_fk",
	             "id_tamano_cavidades_derechas_valvula_tricuspidea_fk",
	             "id_area_jet_central_valvula_tricuspidea_fk",
	             "id_densidad_jet_valvula_tricuspidea_fk",
	             "id_contorno_jet_valvula_tricuspidea_fk",
	             "id_diametro_vena_contracta_valvula_tricuspidea_fk",
	             "id_radio_pisa_valvula_tricuspidea_fk",
	             "id_flujo_hepatico_valvula_tricuspidea_fk",
		};
		*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_insuficiencia_valvula_tricuspidea");	
		int [] columnasnodeseadas = {0,9};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
		if (parametros_tabla.size() > insuficienciaValvulaTricuspidea.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< insuficienciaValvulaTricuspidea.length; i++){
				arregloTemporal[i]=insuficienciaValvulaTricuspidea[i];
			}
			
			for (int i = insuficienciaValvulaTricuspidea.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			insuficienciaValvulaTricuspidea = arregloTemporal;
		}
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!insuficienciaValvulaTricuspidea[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_insuficiencia_valvula_tricuspidea"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, insuficienciaValvulaTricuspidea[i]);
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
	
	
	public boolean actualizarInsuficienciasValvulaPulmonalInformeEco(
			String IdInformeEco,
			String [] insuficienciaValvulaPulmonal
			) {
		/*String [] parametros_tabla = {
				 "id_analisis_insufiencia_valvula_pulmonar",
	             "id_morfologia_valvula_pulmonar_fk",
	             "id_tamano_ventriculo_derecho_valvula_pulmonar_fk",
	             "id_tamano_jet_valvula_pulmonar_fk",
	             "id_densidad_jet_valvula_pulmonar_fk",
	             "id_flujo_pulmonar_flujo_sistemico_valvula_pulmonar_fk",
		};
		*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_insuficiencia_valvula_pulmonal");	
		int [] columnasnodeseadas = {0,6};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > insuficienciaValvulaPulmonal.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< insuficienciaValvulaPulmonal.length; i++){
				arregloTemporal[i]=insuficienciaValvulaPulmonal[i];
			}
			
			for (int i = insuficienciaValvulaPulmonal.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			insuficienciaValvulaPulmonal = arregloTemporal;
		}
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!insuficienciaValvulaPulmonal[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_insuficiencia_valvula_pulmonal"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, insuficienciaValvulaPulmonal[i]);
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
	
	public boolean actualizarEstenosisValvulaAortaInformeEco(
			String IdInformeEco,
			String [] EstenosisValvulaAorta 
			) {
		/*String [] parametros_tabla = {
				  "gradiente_pico",
				  "gradiente_medio",
				  "velocidad_pico",
				  "velocidad_medio",
				  "vti",
				  "tph",
				  "tsvi",
				  "vsi_tsvi",
				  "ava",
				  "ava_indexado",
				  "velpico_tsvi",
				  "vel_radio",
		};
		*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_estenosis_valvula_aorta");	
		int [] columnasnodeseadas = {0,9};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
		if (parametros_tabla.size() > EstenosisValvulaAorta.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< EstenosisValvulaAorta.length; i++){
				arregloTemporal[i]=EstenosisValvulaAorta[i];
			}
			
			for (int i = EstenosisValvulaAorta.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			EstenosisValvulaAorta = arregloTemporal;
		}
		
		
	for (int i =0; i < parametros_tabla.size() ; i++){
	if(!EstenosisValvulaAorta[i].equals("")){
		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				       "UPDATE eco_analisis_estenosis_valvula_aorta"+
				       " SET "+parametros_tabla.get(i)+"=?"+
				       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
			psc.setObject(1, EstenosisValvulaAorta[i]);
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
	
	public boolean actualizarEstenosisValvulaMitralInformeEco(
			String IdInformeEco,
			String [] EstenosisValvulaMitral
			) {
		/*String [] parametros_tabla = {
			    "gradiente_pico",
		        "gradiente_medio",
		        "velocidad_pico",
		        "velocidad_medio",
		        "vti",
		        "thp",
		        "area_valvula_mitral",
		       // "vel_max_regurgitacion",
		       // "pap"
		};
		*/
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_estenosis_valvula_mitral");	
		int [] columnasnodeseadas = {0,8};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);

		if (parametros_tabla.size() > EstenosisValvulaMitral.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< EstenosisValvulaMitral.length; i++){
				arregloTemporal[i]=EstenosisValvulaMitral[i];
			}
			
			for (int i = EstenosisValvulaMitral.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			EstenosisValvulaMitral = arregloTemporal;
		}

		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!EstenosisValvulaMitral[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_estenosis_valvula_mitral"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, EstenosisValvulaMitral[i]);
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
	public boolean actualizarEstenosisValvulaTricuspideInformeEco(
			String IdInformeEco,
			String [] EstenosisValvulaTricuspide
			) {
	/*	String [] parametros_tabla = {
				  "gradiente_pico",
				  "gradiente_medio",
				  "velocidad_pico",
				  "velocidad_medio",
				  "vti",
				  "thp",
				  "area_valvular"
		};
		
		*/
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_estenosis_valvula_tricuspidea");	
		int [] columnasnodeseadas = {0,8};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > EstenosisValvulaTricuspide.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< EstenosisValvulaTricuspide.length; i++){
				arregloTemporal[i]=EstenosisValvulaTricuspide[i];
			}
			
			for (int i = EstenosisValvulaTricuspide.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			EstenosisValvulaTricuspide = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!EstenosisValvulaTricuspide[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_estenosis_valvula_tricuspidea"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, EstenosisValvulaTricuspide[i]);
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
	
	public boolean actualizarEstenosisValvulaPulmonalInformeEco(
			String IdInformeEco,
			String [] EstenosisValvulaPulmonal
			) {
		/*String [] parametros_tabla = {
				
					  "gradiente_pico",
					  "gradiente_medio",
					  "velocidad_pico",
					  "velocidad_medio",
					  "vti",
					  "thp",
					  "diametro_valvular"
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_estenosis_valvula_pulmonal");	
		int [] columnasnodeseadas = {0,8};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > EstenosisValvulaPulmonal.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< EstenosisValvulaPulmonal.length; i++){
				arregloTemporal[i]=EstenosisValvulaPulmonal[i];
			}
			
			for (int i = EstenosisValvulaPulmonal.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			EstenosisValvulaPulmonal = arregloTemporal;
		}
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!EstenosisValvulaPulmonal[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_estenosis_valvula_pulmonal"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, EstenosisValvulaPulmonal[i]);
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
	
	
	public boolean actualizarProtesisValvulaAortaInformeEco(
			String IdInformeEco,
			String [] ProtesisValvulaAorta
			) {
	/*	String [] parametros_tabla = {
				   "id_protesis_valvulas_fk",
		           "regurgitacion_paravalvular",
		           "id_contorno_jet_valvula_aorta_protesica_fk",
		           "id_tiempo_aceleracion_valvula_aorta_protesica_fk",
		           "vel_pico",
		           "gradiente_medio",
		           "vti_ts",
		           "vti",
		           "radio",
		           "dvi",
		           "csa",
		           "area_orificio"
					
		};*/
		
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_valvula_aorta_protesica");	
	int [] columnasnodeseadas = {0,13};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
	if (parametros_tabla.size() > ProtesisValvulaAorta.length){
		String [] arregloTemporal = new String[parametros_tabla.size()];
		
		for(int i = 0 ; i< ProtesisValvulaAorta.length; i++){
			arregloTemporal[i]=ProtesisValvulaAorta[i];
		}
		
		for (int i = ProtesisValvulaAorta.length; i < parametros_tabla.size() ; i++){
			arregloTemporal[i]="";
		}
		
		ProtesisValvulaAorta = arregloTemporal;
	}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!ProtesisValvulaAorta[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_valvula_aorta_protesica"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, ProtesisValvulaAorta[i]);
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
	
	
	
	public boolean actualizarProtesisValvulaMitralInformeEco(
			String IdInformeEco,
			String [] ProtesisValvulaMitral
			) {
		/*String [] parametros_tabla = {
				     "id_protesis_valvulas_fk",
			        "regurgitacion_paravalvular",
			        "id_tiempo_hemipresion_valvula_mitral_protesica_fk",
			        "vel_pico",
			        "gradiente_medio",
			        "vti",
			        "vti_ts",
			        "radio",
			        "dvi",
			        "csa",
			        "eoa"
					
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_valvula_mitral_protesica");	
		int [] columnasnodeseadas = {0,12};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > ProtesisValvulaMitral.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< ProtesisValvulaMitral.length; i++){
				arregloTemporal[i]=ProtesisValvulaMitral[i];
			}
			
			for (int i = ProtesisValvulaMitral.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			ProtesisValvulaMitral = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!ProtesisValvulaMitral[i].equals("")){
			PreparedStatement psc = null;
			
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_valvula_mitral_protesica"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, ProtesisValvulaMitral[i]);
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
	
	
	
	
	public boolean actualizarProtesisValvulaTricuspideaInformeEco(
			String IdInformeEco,
			String [] ProtesisValvulaTricuspidea
			) {
		/*String [] parametros_tabla = {
				 "id_protesis_valvulas_fk",
	             "regurgitacion_paravalvular",
	             "vel_pico",
	             "gradiente_medio",
	             "tiempo_hemipresion"
					
		};
		*/
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_valvula_tricuspidea_protesica");	
		int [] columnasnodeseadas = {0,6};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > ProtesisValvulaTricuspidea.length){
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< ProtesisValvulaTricuspidea.length; i++){
				arregloTemporal[i]=ProtesisValvulaTricuspidea[i];
			}
			
			for (int i = ProtesisValvulaTricuspidea.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			ProtesisValvulaTricuspidea = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		PreparedStatement psc = null;
		if(!ProtesisValvulaTricuspidea[i].equals("")){
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_valvula_tricuspidea_protesica"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, ProtesisValvulaTricuspidea[i]);
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
	
	
	public boolean actualizarProtesisValvulaPulmonalInformeEco(
			String IdInformeEco,
			String [] ProtesisValvulaPulmonal
			) {
	/*	String [] parametros_tabla = {
	             "id_protesis_valvulas_fk",
	             "regurgitacion_paravalvular",
	             "vel_pico",
	             "valvula_engrosada_inmovil",
	             "etrechamiento",
	             "aumento_vel_pico_estudios",
	             "disfuncion"
					
		};*/
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_valvula_pulmonal_protesica");	
		int [] columnasnodeseadas = {0,8};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > ProtesisValvulaPulmonal.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< ProtesisValvulaPulmonal.length; i++){
				arregloTemporal[i]=ProtesisValvulaPulmonal[i];
			}
			
			for (int i = ProtesisValvulaPulmonal.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			ProtesisValvulaPulmonal = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!ProtesisValvulaPulmonal[i].equals("")){
		PreparedStatement psc = null;
	
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_analisis_valvula_pulmonal_protesica"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, ProtesisValvulaPulmonal[i]);
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
	/*	String [] parametros_tabla = {
				  "id_forma_ventriculo_izquierdo_fk",
				  "fevi",
				  "grosor_septum_diastole",
				  "diametro_ventriculo_diastole",
				  "grosor_pared_posterior_diastole",
				  "grosor_septum_sistole",
				  "diametro_ventriculo_sistole",
				  "grosor_pared_posterior_sistole",
				  "grosor_relativo",
				  "lvmass",
				  "id_hipertrofia_ventriculo_izquierdo_fk",
				  "eprima_sepal",
				  "eprima_lateral",
				  "vol_auricula_izquierda",
				  "e",
				  "a",
				  "tiempo_desaceleracion"
					
		};
		*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_ventriculo_izquierdo");	
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
					       "UPDATE eco_ventriculo_izquierdo"+
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
	
	
	public boolean actualizarMotilidadPorSegmentacionVentriculoIzquierdoInformeEco(
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
					"UPDATE eco_segmentacion_ventricular_izquierda"+
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
	
	/*UPDATE eco_segmentacion_ventricular_izquierda
	SET 
	  id_cara_segmentacion_fk = ?,
	  id_descripcion_segmetacion_fk =?
	WHERE 
	id_ventriculo_izquierdo_fk=""
	AND id_sector_segmentacion_fk = "";*/

	
	public boolean actualizarVentriculoDerechoInformeEco(
			String IdInformeEco,
			String [] VentriculoDerecho
			) {
	/*	String [] parametros_tabla = {
				  "diametro_basal",
				  "diametro_medio",
				  "longitud_base_apex",
				  "diametro_arteria_pulmonal",
				  "diametro_valvula_pulmonal",
				  "diametro_tracto_salida_ven_derecho",
				  "tapse"
					
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_ventriculo_derecho");	
		int [] columnasnodeseadas = {0,8};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > VentriculoDerecho.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< VentriculoDerecho.length; i++){
				arregloTemporal[i]=VentriculoDerecho[i];
			}
			
			for (int i = VentriculoDerecho.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			VentriculoDerecho = arregloTemporal;
		}
	for (int i =0; i < parametros_tabla.size() ; i++){

		if(!VentriculoDerecho[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_ventriculo_derecho"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setObject(1, VentriculoDerecho[i]);
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
	
	public boolean actualizarAuriculaIzquierdaInformeEco(
			String IdInformeEco,
			String [] AuriculaIzquierda
			) {
		/*String [] parametros_tabla = {
				
				"area_en_4c",
		        "area_en_2c",
		        "longitud_en_4c",
		        "longitud_en_2c",
		        "volumen"

					
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_auricula_izquierda");	
		int [] columnasnodeseadas = {0,6};
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
					       "UPDATE eco_analisis_auricula_izquierda"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
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
	
	public boolean actualizarAuriculaDerechaInformeEco(
			String IdInformeEco,
			String [] AuriculaDerecha
			) {
		/*String [] parametros_tabla = {
				
				"area"
					
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_auricula_derecha");	
		int [] columnasnodeseadas = {0,2};
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
					       "UPDATE eco_analisis_auricula_derecha"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
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
	
	
	public boolean actualizarVenaCavaInformeEco(
			String IdInformeEco,
			String [] VenaCava
			) {
	/*	String [] parametros_tabla = {
                        "id_diametro_vena_cava_inferior_fk",
	                    "id_colapso_respiratorio_fk",
	                    "presion_rap"
					
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_vena_cava");	
		int [] columnasnodeseadas = {0,4};
		parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		if (parametros_tabla.size() > VenaCava.length){
			
			String [] arregloTemporal = new String[parametros_tabla.size()];
			
			for(int i = 0 ; i< VenaCava.length; i++){
				arregloTemporal[i]=VenaCava[i];
			}
			
			for (int i = VenaCava.length; i < parametros_tabla.size() ; i++){
				arregloTemporal[i]="";
			}
			
			VenaCava = arregloTemporal;
		}
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!VenaCava[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE eco_vena_cava"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
				psc.setString(1, VenaCava[i]);
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
	
	
	public boolean actualizarPericardioInformeEco(
			String IdInformeEco,
			String [] Pericardio
			) {
	/*	String [] parametros_tabla = {
				

				  "tipo_grosor_pericardio_fk",
				  "tipo_apariencia_pericardio_fk",
				  "tiene_efucion",
				  "es_loculada",
				  "localizacion_loculada",
				  "grosor_loculada",
				  "medicion_no_loculado"

					
		};*/
		
		Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("eco_analisis_pericardio");	
		int [] columnasnodeseadas = {0,8};
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
					       "UPDATE eco_analisis_pericardio"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInforme_fk ='"+ IdInformeEco+"'");
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
}
