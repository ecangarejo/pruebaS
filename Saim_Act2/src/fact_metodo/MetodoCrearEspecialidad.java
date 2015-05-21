/**
 * En este metodo estan las consultas,inserciones,actualizaciones.
 * para poder crear las especialidades,tipo de especialidades y detalle de especialidad.
 */
package fact_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import fact_bean.Especialidad;
import fact_bean.Especialidad1;
import adm_logica.Conexion;

public class MetodoCrearEspecialidad {
	
	public ResultSet BuscarEspecialidad(String NombreEspecialidad){
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_especialidad FROM fact_especialidades fe WHERE fe.nombre='"+NombreEspecialidad+"'");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearEspecialidad>>BuscarEspecialidad\n"+ex);
        }
        return rs;
    }
	
	public ResultSet NombreTipoEspecialidad(String CodigoTipoEspecialidadFK){
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("select nombre from fact_tipo_especialidad where codigo="+CodigoTipoEspecialidadFK+"");
        }
        catch(Exception ex){
        	System.err.println("Error en MetodoCrearEspecialidad>>NombreTipoEspecialidad "+ex);
        }	
        return rs;
    }
	
	public ResultSet BuscarEspecialidadPorTipo(String CodigoTipoEspecialidadFK){	
		/**
		 */
        ResultSet rs = null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("select nombre_especialidad from fact_especialidad where cod_tipoEspFK="+CodigoTipoEspecialidadFK+"");
        }
        catch(Exception ex){
        	System.err.println("Error en MetodoCrearEspecialidad>>BuscarEspecialidadPorTipo "+ex);
        }	
        return rs;
    }

	public ResultSet BuscarNombreTipoEspecialidad(String NombreTipoEspecialidad){	
		/**
		 */
        ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from fact_tipo_especialidad where nombre='"+NombreTipoEspecialidad+"'");
        }
        catch(Exception ex){
        	System.err.println("Error en MetodoCrearEspecialidad>>BuscarNombreTipoEspecialidad "+ex);
        }	
        return rs;
    }
	
	
	public ResultSet BuscarTipoEspecialidad(){	
		/**
		 */
        ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre from fact_tipo_especialidad");
        }
        catch(Exception ex){
        	System.err.println("Error en MetodoCrearEspecialidad>>BuscarTipoEspecialidad "+ex);
        }	
        return rs;
    }
	
	/**
	 * creamos Tipo Especialidad.
	 */
	public void CrearTipoEspecialidad(String NombreTipoEspecialidad){
		Especialidad1 es =new Especialidad1();
		es.setNombreTipoEspecialidad(NombreTipoEspecialidad);		
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps = con.conn.prepareStatement("insert into fact_tipo_especialidad(nombre)values(?)");
			ps.setString(1,es.getNombreTipoEspecialidad());
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearEspecialidad>>CrearTipoEspecialidad "+ex);
		}
	}
	
/*///////////////////////////////////////////////////////////////////////////////////////////////////////*/	
	/**
	 * creamos Especialidad.
	 */
	/*public void CrearEspecialidad(String NombreEspecialidad,String CodigoTipoEspecialidadFK,String CodigoRips){
		Especialidad1 es =new Especialidad1();
		es.setNombreEspecialidad(NombreEspecialidad);
		es.setCodigoTipoEspecialidadFK(CodigoTipoEspecialidadFK);
		es.setCodigoRips(CodigoRips);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_especialidad(nombre_especialidad,cod_tipoEspFK,codigo_rip)values(?,?,?)");
			    ps.setString(1,es.getNombreEspecialidad());
			    ps.setString(2,es.getCodigoTipoEspecialidadFK());
			    ps.setString(3,es.getCodigoRips());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.err.println("ERROR EN MetodoCrearEspecialidad>>CrearEspecialidad "+ex);
			}
	}*/
	
	/**
	 * Creamos una especialidad.
	 * @param desc Descripci&oacute;n
	 * @return true si se inserta el registro en la tabla, false en caso contrario.
	 */
	public boolean Crear(String desc){
		Especialidad es = new Especialidad();
		es.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("INSERT INTO fact_especialidades(descripcion) VALUES(?);");
		    ps.setString(1, es.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearEspecialidad>>Crear\n"+ex);
			return false;
		}
	}
	
	/**
	 * @return {@link ResultSet} con el c&oacute;digo y descripci&oacute;n de todas las especialidades guardadas en la BD.
	 */
	public ResultSet getAll(){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT cod_especialidad, descripcion FROM fact_especialidades;");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearEspecialidad>>getAll\n"+ex);
        }
        return rs;
    }
	
	/**
	 * @param cod C&oacute;digo de la especialidad
	 * @return {@link ResultSet} con el nombre y descripci&oacute;n de una especialidad de acuerdo
	 * al c&oacute;digo de especialidad pasado como par&aacute;metro.
	 */
	public ResultSet getByCod(String cod){
		Conexion con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
        	con = new Conexion();
        	st = con.conn.createStatement();
        	rs = st.executeQuery("SELECT descripcion FROM fact_especialidades WHERE cod_especialidad='"+cod+"';");
        } catch(Exception ex){
        	System.err.println("Error en MetodoCrearEspecialidad>>getByCod\n"+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza una especialidad.
	 * @param desc Descripci&oacute;n
	 * @param cod C&oacute;digo de la especialidad
	 * @return true si actualiza el registro en la tabla, false en caso contrario.
	 */
	public boolean Actualizar(String desc, String cod){
		Especialidad es = new Especialidad();
		es.setDesc(desc);
		PreparedStatement ps = null;
		Conexion con = null;
		try{
		    con = new Conexion();
		    ps = con.conn.prepareStatement("UPDATE fact_especialidades SET descripcion=? WHERE cod_especialidad='"+cod+"';");
		    ps.setString(1, es.getDesc());
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			return true;
		}catch(Exception ex){
			System.err.println("ERROR EN MetodoCrearEspecialidad>>Actualizar\n"+ex);
			return false;
		}
	}
}
