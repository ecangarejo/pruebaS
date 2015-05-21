/**
 * NOMBRE DE LA CLASE: MetodoActualizarCama
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para consultar y actualizar
 * 				los datos de una cama.
 * 				
 */
package adm_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class MetodoActualizarCama {
	
	public MetodoActualizarCama(){}
	
	public java.sql.ResultSet obtenerPosicion(){
		/**
		 * en este metodo se obtiene la posicion de la cama
		 * si esta en forma vertical u horizontal dentro de la habitacion.
		 * 
		*/
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select posicion from adm_posicion");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerTipoHab(){
		/**
		 * en este metodo se obtiene el tipo de habitacion 
		 * si es personal,bipersonal o cualquier otra.
		 * */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select descripcion from adm_tipohabitacion");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerTipoCama(){
		
		/**
		 * en este metodo se obtiene el tipo de cama 
		 * si es sencilla, camilla,servocuna,cuna,camacuna,quirofano, etc.
		 * */		
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select descripcion from adm_tipocama");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
	
	
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
	public java.sql.ResultSet obtenerSubArea(String a){
		/**
		 * en este metodo se obtiene el nombre de la subarea
		 * es decir si el area es hosptalizacion el resultado de este seria 
		 * el nombre de los pabellones que en el se encuentran ej: pabellon mar,brisas,etc.
		 * lleva como dato de entrada el nombre del area.
		 * */
		
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
		 * en este metodo se obtiene las camas del subarea seleccionada
		 * tiene como dato de entrada el nombre de la subarea en donde se quiere
		 * saber las camas que esta contiene.
		 * 
		 * */
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery("select adm_censo_cama.codigocama from adm_censo_cama,adm_subarea where adm_subarea.codigo=adm_censo_cama.codsubarea and adm_subarea.nombre='"+cam+"'");
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerDatosCama(String cama){
		/**
		 * en este metodo se obtiene los datos de la cama seleccionada
		 * para su posterior actualizacion.	  
		 * lleva como dato de entrada el nombre de la cama.
		 * */
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	
rs=st.executeQuery("select posx,posy,observaciones,piso_ubicacion,tipo_habitacion,posicion,tipo_cama,cen_numero_cama,codigocama from adm_censo_cama,adm_subarea where adm_censo_cama.codsubarea=adm_subarea.codigo and codigocama='"+cama+"'");
        }       
        catch(Exception ex){        	
        }
        return rs;
    }
	
public void ActualizarCama(String codigo,String tipocama,String tipohab,String posicion,String observ,String piso,String posy,String posx,String nomcama){
		
	/**
	 * en este metodo se actualizan los datos modificados de la cama
	 * posteriormente seleccionada.
	 * 	  
	 * */	

	if(posx.equals("")){				
		posx="0";
	}
    if(posy.equals("")){
		posy="0";
	}
    if(posicion.equals("HORIZONTAL")){
		posicion="H";
	}
    if(posicion.equals("VERTICAL")){
    	posicion="V";
    }
	
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("update adm_censo_cama set posx='"+posx+"',posy='"+posy+"',tipo_cama='"+tipocama+"',observaciones='"+observ+"',piso_ubicacion='"+piso+"',posicion='"+posicion+"',tipo_habitacion='"+tipohab+"',codigocama='"+nomcama+"' where cen_numero_cama='"+codigo+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	
	

}
