package eco_Cardiologia;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import adm_logica.Conexion;

public class MetodoTransesofagico {
	
	//metodo que obtiene todos los nombres de las columnas de una tabla
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
	
	
	
	//metodo para excluir columnas
	
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
	
	//metodo para buscar ecotrans por paciente
	public java.sql.ResultSet buscarInformesDeEcotransRealizados(String TipoDocumento,
			String NumeroDocumento) {
		
		java.sql.ResultSet rs = null;
		Statement st = null;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery(
					"SELECT "+
					"CONCAT(pac.nombre,'',pac.primer_apellido,'',pac.segundo_apellido),"+
					"inf.idInformeTransesofagico,"+
					"CONCAT(inf.fecha_informe,'/',inf.hora_informe),"+
					"pac_codigo_paciente "+
					"FROM "+
					"adm_paciente pac,"+
					"ecotrans_encabezado_informe inf "+
					"WHERE "+
					"pac.tipo_documento = '"+TipoDocumento+"' "+
					"AND pac.numero_documento='"+NumeroDocumento+"' "+
					"AND pac.pac_codigo_paciente = inf.codpaciente "+     
					"AND inf.estado='1'"); 
		} catch (Exception ex) {
			System.out.println("Error en MetodoTransesofagico>>buscarInformesDeEcotransRealizados "	+ ex);
		}
		return rs;
		
		
		
	}
	
	/*****************************************/
	/******Metodo Insertar Encabezado*********/
	/**********Informe Ecotransesofagico******/
	/*****************************************/ 

	
	public boolean insertarEncabezadoInformeTrans(
			String fecha,
			String hora, 
			String cod_usuario, 
			String cod_paciente,
			String estado,
			String tipo_indicacion
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO ecotrans_encabezado_informe"+
		           "(codusuario," +
		           "codpaciente," +
		           "estado," +
		           "fecha_informe," +
		           "hora_informe," +
		           "id_indicacion_fk" +
		           ")" +
		           " VALUES(?,?,?,?,?,?)");
			psc.setString(1, cod_usuario);
			psc.setString(2, cod_paciente);
			psc.setString(3, estado);
			psc.setString(4, fecha);
			psc.setString(5, hora);
			psc.setString(6, tipo_indicacion);
			
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
	
	
	//metodo que devuelve el id del ultimo informe realizado
	public int codigoActualInformeEco() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(idInformeTransesofagico) FROM ecotrans_encabezado_informe");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}
	
	
	//metodo ue crea una nueva fila en ecotrans_obs con el id y el codigo del informeAct
	public boolean asociarObsInformeEcoTrans(
			String IdInformeEcoTrans
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO ecotrans_obs"+
		           "(idInformeTransesofagico_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdInformeEcoTrans);
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
	
	
	//metodo que inserta-actualiza los datos a la tabla ecotrans_obs
	public boolean actualizarObsInformeEcoTrans(
			String IdInformeEcoTrans,
			String [] observaciones_generales 
			) {
		
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("ecotrans_obs");	
	int [] columnasnodeseadas = {0,14};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		if(!observaciones_generales[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE ecotrans_obs"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idInformeTransesofagico_fk ='"+ IdInformeEcoTrans+"'");
				
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
			System.out.println("Error en MetodoTransesofagico>>BuscarPaciente "
					+ ex);
		}
		return rs;
	}
	
	
	//select indicacion
	 public java.sql.ResultSet ObtenerIndicacion(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();        	
	        	rs=st.executeQuery(" SELECT * FROM eco_indicaciones ");
	        }
	        catch(Exception ex){
	        	System.out.print("Error en MetodoTransesofagico>>ObtenerIndicacion "+ex);
	        	ex.getMessage();
	        }
	        return rs;
	    }
	
	

}//fin class
