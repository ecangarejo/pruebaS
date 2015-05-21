/**
 * NOMBRE DE LA CLASE: MetodoFinalizarAdmision
 * AUTOR: Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para insertar y consultar 
 * 				lo necesario para la insercion de una cama.
 * 				
 */
package adm_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_bean.CrearCama;

public class MetodoCrearCama {
	
	public MetodoCrearCama(){}
	
	
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
        	rs=st.executeQuery("select nombre from adm_area");
        	
        }
        catch(Exception ex){ 
        	System.out.println("Error en MetodoCrearCama>>obtenerArea "+ex);
        }
        return rs;
    }
		
	
	public java.sql.ResultSet obtenerCodSubArea(String sub){
		/**
		 * en este metodo se obtiene el codigo de la subarea
		 * mediante el nombre de esta, con el fin de insertar dicho
		 * codigo en la base de datos.
		 * se toma como dato de entrada el nombre de la subarea.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from adm_subarea where nombre='"+sub+"'");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerSubArea(String a){
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
        	rs=st.executeQuery("select adm_subarea.codigo,adm_subarea.nombre  from adm_subarea,adm_area where adm_area.codigo=adm_subarea.codigoarea and adm_area.nombre='"+a+"'");
        	
        }
        
        
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	
	public void insertar(String tipohabitacion1,String area1,String subarea,String piso_ubicacion,String tipo_cama,String observaciones,String cod_estado,String cod_subarea,String cod_cama,String posx,String posy,String posicion,String Direccionamiento){
		/**
		 * en este metodo se toman todos los datos introducidos en el formulario
		 * para hacer la respectiva insercion en la base de datos.
		 * se toman como datos de entrada todos los datos introducidos en el formulario
		 */
		
		CrearCama ca=new CrearCama();
		ca.setTipoHabitacion(tipohabitacion1);		
		
		ca.setArea(area1);		
		ca.setSubArea(subarea);		
		ca.setPisoUbicacion(piso_ubicacion);		
		ca.setTipoCama(tipo_cama);		
		ca.setObservaciones(observaciones);		
		ca.setCodigoEstado(cod_estado);		
		ca.setCodigoSubarea(cod_subarea);		
		ca.setCodigoCama(cod_cama);			
		ca.setPosx(posx);
		ca.setPosy(posy);
		ca.setPosicion(posicion);	
		ca.setDireccionamiento(Direccionamiento);
		try{
						
			posx=ca.getPosx();
			posy=ca.getPosy();
			posicion=ca.getPosicion();			
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
            if(posicion.equals("Seleccione")){
            	posicion="Seleccione";
            }			
			
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into adm_censo_cama(tipo_habitacion,pabellon,ubicacion_cama,piso_ubicacion,tipo_cama,observaciones,est_codigo_estado_fk,codsubarea,codigocama,posx,posy,posicion,servicio) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		    ps.setString(1, ca.getTipoHabitacion());
		    ps.setString(2, ca.getArea());
		    ps.setString(3, ca.getSubArea());
		    ps.setString(4, ca.getPisoUbicacion());
		    ps.setString(5, ca.getTipoCama());
		    ps.setString(6, ca.getObservaciones());
		    ps.setString(7, ca.getCodigoEstado());
		    ps.setString(8, ca.getCodigoSubarea());
		    ps.setString(9, ca.getCodigoCama());
		    ps.setString(10, posx);
		    ps.setString(11, posy);
		    ps.setString(12, posicion);	
		    ps.setString(13, ca.getDireccionamiento());
			ps.executeUpdate();
			ps.close();
			con.cerrar();			
		}
		catch(Exception ex){
			ex.getStackTrace();
			System.out.print("Error en MetodoCrearCama>>Insertar "+ex);
		}		
	}	
	

}

