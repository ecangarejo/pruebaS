/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.NivelComplejidad;
import adm_logica.Conexion;

public class MetodoCrearNivelComplejidad {
	
	/**
	 * Creamos una nivel de complejidad.

	 * @param desc Descripci&oacute;n
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc){
		NivelComplejidad bean = new NivelComplejidad();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_niveles_complejidad(descripcion) VALUES(?);");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearNivelComplejidad>>Crear\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de
	 * todas los niveles de complejidad guardados en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_niv_compl, descripcion FROM fact_niveles_complejidad;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearNivelComplejidad>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param CodClaseS
	 * @return {@link ResultSet} con la descripci&oacute;n de un nivel de complejidad de
	 * acuerdo al c&oacute;digo del nivel de complejidad pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion FROM fact_niveles_complejidad WHERE cod_niv_compl='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearNivelComplejidad>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza un nivel de complejidad.
	 * @param desc Descripci&oacute;n
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String cod){
		NivelComplejidad bean = new NivelComplejidad();
		bean.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_niveles_complejidad SET descripcion=? WHERE cod_niv_compl='"+cod+"';");
		    ps.setString(1, bean.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearNivelComplejidad>>Actualizar\n"+ex);
			return false;
		}
	}
}
