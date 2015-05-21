package hic_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import adm_logica.Conexion;

public class MetodoOrdPat {

	
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
	
	
	
	//insertar encabezado orden patologia
	public boolean insertarEncabezadoOrdPat(
			String fecha,
			String hora, 
			String cod_usuario, 
			String cod_paciente,
			String admpac,
			String ubicacionPac,
			String estado
			
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO ordenpat_encabezado"+
		           "(codusuario," +
		           "codpaciente," +
		           "admpaciente," +
		           "fecha," +
		           "hora," +
		           "ubicacionPac,"+
		           "estado" +
		           ")" +
		           " VALUES(?,?,?,?,?,?,?)");
			psc.setString(1, cod_usuario);
			psc.setString(2, cod_paciente);
			psc.setString(3, admpac );
			psc.setString(4, fecha);
			psc.setString(5, hora);
			psc.setString(6, ubicacionPac);
			psc.setString(7, estado);
			
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
	
	
	//metodo que devuelve el id de la ultima orden de patologia realizada
	public int codigoActualOrdPat() {
		java.sql.ResultSet rs = null;
		Statement st = null;
		int valor = 1;
		try {
			Conexion con = new Conexion();
			st = con.conn.createStatement();
			rs = st.executeQuery("SELECT MAX(idOrdenPat) FROM ordenpat_encabezado");
			rs.next();
			if (rs.getString(1) != null) {
				valor = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return valor;
	}
	
	
	//metodo ue crea una nueva fila en ordenpat_observaciones con el id y el codigo de la orden de patologia actual
	public boolean asociarObs(
			String IdOrdenPat
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO ordenpat_observaciones"+
		           "(idordenpat_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdOrdenPat);
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
	
	
	//metodo que inserta-actualiza los datos a la tabla ordenpat_observaciones
	public boolean actualizarObs(
			String IdOrdenPat,
			String [] observaciones
			) {
		
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("ordenpat_observaciones");	
	int [] columnasnodeseadas = {0,5};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
	for (int i =0; i < parametros_tabla.size() ; i++){
		
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE ordenpat_observaciones"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idordenpat_fk ='"+ IdOrdenPat+"'");
				
				psc.setObject(1, observaciones[i].toString());
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
	
	
	//metodo ue crea una nueva fila en ordenpat_tipo_procedimiento con el id y el codigo de la orden de patologia actual
	public boolean asociarTipoProc(
			String IdOrdenPat
			) {

		PreparedStatement psc = null;
		try {
			Conexion con = new Conexion();
			psc = con.conn.prepareStatement(
				   "INSERT INTO ordenpat_tipo_procedimiento"+
		           "(idOrdenPat_fk)" +
		           " VALUES(?)");
			psc.setString(1, IdOrdenPat);
			psc.executeUpdate();
			psc.close();
			con.cerrar();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error en asociarTipoProc"+e);
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	
	//metodo que inserta-actualiza los datos a la tabla ordenpat_observaciones
	public boolean actualizarTipoProc(
			String IdOrdenPat,
			String [] TipoProc
			) {
		
	Vector <String> parametros_tabla = obtenerNombresColumnasPorTabla("ordenpat_tipo_procedimiento");	
	int [] columnasnodeseadas = {0,9};
	parametros_tabla = VectorEliminarDatosArreglos(parametros_tabla, columnasnodeseadas);
		
	for (int i =0; i < parametros_tabla.size()+1 ; i++){
		
		if(!TipoProc[i].equals("")){
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
					       "UPDATE ordenpat_tipo_procedimiento"+
					       " SET "+parametros_tabla.get(i)+"=?"+
					       " WHERE idOrdenPat_fk ='"+ IdOrdenPat+"'");
				
				psc.setObject(1, TipoProc[i].toString());
				psc.executeUpdate();
				psc.close();
				con.cerrar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error en actualizarTipoProc"+e);
			e.printStackTrace();
			return false;
		}
			
	}
		
	}
			return true;
}
	
	
	
}
