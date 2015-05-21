/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.Modalidad;
import adm_logica.Conexion;

public class MetodoCrearModalidad {
	
	/**
	 * Creamos una modalidad.

	 * @param desc Descripci&oacute;n
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc){
		Modalidad bean = new Modalidad();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_modalidades(descripcion) VALUES(?);");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearModalidad>>Crear\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de
	 * todas las modalidades guardadas en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_modalidad, descripcion FROM fact_modalidades ORDER BY descripcion ASC;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearModalidad>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod
	 * @return {@link ResultSet} con la descripci&oacute;n de una modalidad de
	 * acuerdo al c&oacute;digo del mismo pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion FROM fact_modalidades WHERE cod_modalidad='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearModalidad>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza una modalidad.
	 * @param desc Descripci&oacute;n
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String cod){
		Modalidad bean = new Modalidad();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_modalidades SET descripcion=? WHERE cod_modalidad='"+cod+"';");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearModalidad>>Actualizar\n"+ex);
			return false;
		}
	}
}
