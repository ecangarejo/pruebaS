/**
 * logica: MetodoCama tiene las consultas,insercion y actualizaciones
 * disponibles para la creacion de la cama.
 */
package adm_logica;

import java.sql.Statement;

public class MetodoCama {

	public java.sql.ResultSet obtenerCama(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select c.cen_numero_cama,c.codigocama, c.codsubarea,c.posicion,c.tipo_cama,c.observaciones,c.posx,c.posy,e.estado_cama " +
        	"from adm_censo_cama c, adm_censo_estado e where  c.est_codigo_estado_fk = e.est_codigo_estado");
        }
        catch(Exception ex){
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerArea(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, nombre  from adm_area");
        }
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerSubArea(String a){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, nombre, codigoarea  from adm_subarea where codigoarea="+a);
        }
        catch(Exception ex){
        
        }
        return rs;
    }
}
