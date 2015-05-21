/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.ManualBase;
import adm_logica.Conexion;

public class MetodoCrearManualBase {
	
	/**
	 * Creamos un manual base.

	 * @param desc Descripci&oacute;n
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc){
		ManualBase bean = new ManualBase();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_manuales_base(descripcion) VALUES(?);");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearManualBase>>Crear\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de
	 * todos los manuales base guardados en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_manual_base, descripcion FROM fact_manuales_base;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearManualBase>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod
	 * @return {@link ResultSet} con la descripci&oacute;n de un manual base de
	 * acuerdo al c&oacute;digo del mismo pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion FROM fact_manuales_base WHERE cod_manual_base='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearManualBase>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza un manual base.
	 * @param desc Descripci&oacute;n
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String cod){
		ManualBase bean = new ManualBase();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_manuales_base SET descripcion=? WHERE cod_manual_base='"+cod+"';");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearManualBase>>Actualizar\n"+ex);
			return false;
		}
	}
}
