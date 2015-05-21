/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.SubcentroCosto;
import adm_logica.Conexion;

public class MetodoCrearSubcentroCosto {
	
	/**
	 * Creamos un subcentro de costo.

	 * @param desc Descripci&oacute;n
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc){
		SubcentroCosto bean = new SubcentroCosto();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_subcentros_costo(descripcion) VALUES(?);");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearSubcentroCosto>>Crear\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de
	 * todos los subcentros de costo guardados en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_subcentro_costo, descripcion FROM fact_subcentros_costo;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearSubcentroCosto>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod
	 * @return {@link ResultSet} con la descripci&oacute;n de un subcentro de costo de
	 * acuerdo al c&oacute;digo de la subcentro de costo pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion FROM fact_subcentros_costo WHERE cod_subcentro_costo='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearSubcentroCosto>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza una Clase de Servicio.
	 * @param desc Descripci&oacute;n
	 * @param cod
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String cod){
		SubcentroCosto bean = new SubcentroCosto();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_subcentros_costo SET descripcion=? WHERE cod_subcentro_costo='"+cod+"';");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearSubcentroCosto>>Actualizar\n"+ex);
			return false;
		}
	}
}
