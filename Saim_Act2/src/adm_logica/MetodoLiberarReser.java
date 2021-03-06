/**
 * NOMBRE DE LA CLASE: MetodoLiberarReser
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para actualizar y consultar
 * 				para hacer efectiva la liberacion de una cama reservada.
 */
package adm_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class MetodoLiberarReser {
	
	public MetodoLiberarReser(){}

	
	
	public java.sql.ResultSet obtenerArea(){
		/**
		 * en este metodo se obtiene el nombre del area de la clinca
		 * si es urgencia,hospitalizacion,uci's o cualquier otra.
		 * */
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
	
	
	public java.sql.ResultSet obtenerSubArea(String area){
		/**
		 * en este metodo se obtiene el nombre de la subarea
		 * del area previamente seleccionada.
		 * se toma como dato de entrada el nombre del area.
		 *  
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select adm_subarea.nombre  from adm_subarea,adm_area where adm_area.codigo=adm_subarea.codigoarea and adm_area.nombre='"+area+"'");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerCensoReserv(){
		/**
		 * en este metodo se obtienen los datos del reporte de las camas reservadas.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select adm_censo_cama.codigocama,tipo_documento,cedula,apellido,segundo_apellido,nombre,eps,fecha_ingreso,hora_ingreso,numero_autorizacion,autorizado_por,adm_censo_cama.pabellon,adm_censo_cama.ubicacion_cama from adm_preadmision,adm_censo_cama where adm_preadmision.estado=0 and adm_preadmision.codigocama=adm_censo_cama.cen_numero_cama");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerCama(String subarea){
		/**
		 * en este metodo se obtiene las camas del subarea seleccionada
		 * tiene como dato de entrada el nombre de la subarea en donde se quiere
		 * saber las camas que esta contiene.
		 * 
		 **/
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select DISTINCT adm_censo_cama.codigocama from adm_censo_cama,adm_subarea where adm_censo_cama.est_codigo_estado_fk='2'and adm_censo_cama.ubicacion_cama='"+ subarea +"'");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	public java.sql.ResultSet obtenerCodigoCam(String cama){
		/**
		 * en este metodo estan los datos a mostrar para hacer efectiva la liberacion
		 * de la cama reservada.
		 * toma como dato de entrada el nombre de la cama.
		 */
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();      
        	rs=st.executeQuery("select  adm_preadmision.pre_codigo,adm_censo_cama.cen_numero_cama,adm_preadmision.nombre,adm_preadmision.apellido,adm_preadmision.fecha_ingreso,adm_preadmision.cedula,adm_preadmision.eps,adm_preadmision.tipo_documento,adm_preadmision.segundo_apellido,adm_preadmision.numero_autorizacion,adm_preadmision.autorizado_por  from adm_censo_cama ,adm_preadmision  where adm_censo_cama.codigocama='"+cama+"'and adm_preadmision.estado=0 and adm_preadmision.codigocama=adm_censo_cama.cen_numero_cama");
        } 
      
        catch(Exception ex){        	
        }
        return rs;
    }
	
	
	public void ActualizarCamaReser(String Codcama){
		/**
		 * en este metodo se actualiza el estado de la cama que estaba previamente
		 * reservada.
		 * toma como dato de entrada el codigo interno de la cama.
		 */
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=1 where cen_numero_cama ='"+Codcama+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	public void ActualizarPreadmision(String codPre){
		/**
		 * en este metodo se actualiza la preadmision cambiando su 
		 * estado de activo(0)a inactivo(1), esto con el fin de consultar solo 
		 * las preadmisiones activas.
		 * se toma como dato de entrada el codigo interno de la preadmision.
		 */
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_preadmision set estado=1 where pre_codigo='"+codPre+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	

	
}
