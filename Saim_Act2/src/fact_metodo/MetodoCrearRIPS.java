/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.RIPS;
import adm_logica.Conexion;

public class MetodoCrearRIPS {
	
	/**
	 * Creamos un RIPS.

	 * @param desc Descripci&oacute;n
	 * @param sigla
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc, String sigla){
		RIPS bean = new RIPS();
		bean.setDesc(desc);
		bean.setSigla(sigla);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_rips(descripcion, sigla) VALUES(?, ?);");
		    ps.setString(1, bean.getDesc());
		    ps.setString(2, bean.getSigla());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR en MetodoCrearRIPS>>Crear\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de
	 * todos los RIPS guardados en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_rip, descripcion FROM fact_rips;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearRIPS>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod
	 * @return {@link ResultSet} con la descripci&oacute;n de un RIPS
	 * de acuerdo al c&oacute;digo del mismo pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion, sigla FROM fact_rips WHERE cod_rip='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearRIPS>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza un RIPS.
	 * @param desc Descripci&oacute;n
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String sigla, String cod){
		RIPS bean = new RIPS();
		bean.setDesc(desc);
		bean.setSigla(sigla);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_rips SET descripcion=?, sigla=? WHERE cod_rip='"+cod+"';");
		    ps.setString(1, bean.getDesc());
		    ps.setString(2, bean.getSigla());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR en MetodoCrearRIPS>>Actualizar\n"+ex);
			return false;
		}
	}
}
