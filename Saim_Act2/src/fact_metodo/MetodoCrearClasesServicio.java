/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.ClaseServicio;
import adm_logica.Conexion;

public class MetodoCrearClasesServicio {
	
	/**
	 * Creamos una clase de servicio.
	 * @param desc Descripci&oacute;n
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc){
		ClaseServicio bean = new ClaseServicio();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_clases_servicio(descripcion) VALUES(?);");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearClasesServicio>>CrearClaseS\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de
	 * todas las clases de servicio guardadas en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_clase_servicio, descripcion FROM fact_clases_servicio;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearClasesServicio>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod
	 * @return {@link ResultSet} con la descripci&oacute;n de una clase de servicio de
	 * acuerdo al c&oacute;digo de la clase de servicio pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion FROM fact_clases_servicio WHERE cod_clase_servicio='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearClasesServicio>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza una clase de servicio.
	 * @param desc Descripci&oacute;n
	 * @param cod C&oacute;digo de la clase de servicio
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String cod){
		ClaseServicio bean = new ClaseServicio();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_clases_servicio SET descripcion=? WHERE cod_clase_servicio='"+cod+"';");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearClasesServicio>>Actualizar\n"+ex);
			return false;
		}
	}
}
