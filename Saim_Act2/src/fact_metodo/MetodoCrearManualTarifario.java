/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.ManualTarifario;
import adm_logica.Conexion;

public class MetodoCrearManualTarifario {
	
	/**
	 * Creamos un manual tarifario.

	 * @param desc Descripci&oacute;n
	 * @param codManualT
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc, String codManualB){
		ManualTarifario bean = new ManualTarifario();
		bean.setDesc(desc);
		bean.setCodManualB(codManualB);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_manuales_tarifarios(descripcion, manual_base) VALUES(?, ?);");
		    ps.setString(1, bean.getDesc());
		    ps.setString(2, bean.getCodManualB());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearManualTarifario>>Crear\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de
	 * todos los manuales tarifarios guardados en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_manual_tarifario, descripcion FROM fact_manuales_tarifarios;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearManualTarifario>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod
	 * @return {@link ResultSet} con la descripci&oacute;n de un manual tarifario
	 * de acuerdo al c&oacute;digo del mismo pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion, manual_base FROM fact_manuales_tarifarios WHERE cod_manual_tarifario='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearManualTarifario>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza un manual tarifario.
	 * @param desc Descripci&oacute;n
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String codManualB, String cod){
		ManualTarifario bean = new ManualTarifario();
		bean.setDesc(desc);
		bean.setCodManualB(codManualB);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_manuales_tarifarios SET descripcion=?, manual_base=? WHERE cod_manual_tarifario='"+cod+"';");
		    ps.setString(1, bean.getDesc());
		    ps.setString(2, bean.getCodManualB());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearManualTarifario>>Actualizar\n"+ex);
			return false;
		}
	}
}
