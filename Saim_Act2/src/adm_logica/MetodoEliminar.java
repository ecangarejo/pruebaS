/**
 * NOMBRE DE LA CLASE: MetodoFinalizarAdmision
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para consultar y eliminar
 * 				areas,subareas y camas.
 * 				
 */
package adm_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class MetodoEliminar {
	
	public void EliminarCama(String codigo){
		/**
		 * en este metodo se elimina la cama seleccionada
		 * se toma como dato de entrada el codigo de la cama.
		 */
	
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("delete from adm_censo_cama where cen_numero_cama='"+codigo+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	public void EliminarSubarea(String codsub){
		
		/**
		 * en este metodo se elimina la subarea seleccionada
		 * se toma como dato de entrada el codigo de la subarea.
		 */
		
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("delete from adm_subarea where codigo='"+codsub+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	public void EliminarArea(String codarea){
	
		/**
		 * en este metodo se elimina la area seleccionada
		 * se toma como dato de entrada el codigo de la area.
		 */

	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("delete from adm_area where codigo='"+codarea+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	public java.sql.ResultSet obtenerAreaEliminar(String area){
		/**
		 * en este metodo se obtiene el codigo del area a eliminar
		 * se toma como dato de entrada el nombre del area.
		 */
		
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
rs=st.executeQuery("select codigo from adm_area where nombre='"+area+"'");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }	
	
	public java.sql.ResultSet obtenerCamaEliminar(String cam){
		
		/**
		 * en este metodo se obtienen los datos de la cama a eliminar
		 * se toma como dato de entrada el nombre de la cama.
		 */
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
rs=st.executeQuery("select cen_numero_cama,tipo_habitacion,piso_ubicacion,tipo_cama,observaciones,posx,posy,posicion from adm_censo_cama where est_codigo_estado_fk=1 and codigocama='"+cam+"'");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	
	
	public java.sql.ResultSet obtenerCodigoSubAre(String subarea){
		
		/**
		 * en este metodo se obtiene el codigo del subarea a eliminar
		 * se toma como dato de entrada el nombre de la subarea.
		 */
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select adm_subarea.codigo,adm_area.codigo  from adm_subarea,adm_area where adm_area.codigo=adm_subarea.codigoarea and adm_subarea.nombre='"+subarea+"'");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	
public java.sql.ResultSet obtenerArea(){
	/**
	 * en este metodo se obtienen los nombres de las areas
	 * 
	 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre  from adm_area");
        }
        catch(Exception ex){ 
        }
        return rs;
    }

public java.sql.ResultSet obtenerSubArea(String a){
	
	/**
	 * en este metodo se obtienen los nombres de las subareas
	 * dependiendo al area seleccionada.
	 * se toma como dato de entrada el nombre del area.
	 * 
	 */
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select adm_subarea.nombre  from adm_subarea,adm_area where adm_area.codigo=adm_subarea.codigoarea and adm_area.nombre='"+a+"'");
    	
    }
    
    
    catch(Exception ex){
    	
    }
    return rs;
}
public java.sql.ResultSet obtenerCama(String cam){
	
	/**
	 * en este metodo se obtienen las camas
	 * dependiendo al area y subareas seleccionada.
	 * se toma como dato de entrada el nombre de la subarea.
	 * 
	 */
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	
    	rs=st.executeQuery("select adm_censo_cama.codigocama from adm_censo_cama,adm_subarea where adm_subarea.codigo=adm_censo_cama.codsubarea and adm_censo_cama.est_codigo_estado_fk=1 and adm_subarea.nombre='"+cam+"'");
    }
    
    
    catch(Exception ex){
    	
    }
    return rs;
}

}
