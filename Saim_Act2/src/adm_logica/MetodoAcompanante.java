/**
 * logica:MetodoAcompanante tiene las consultas insercion y actualizaciones
 * disponibles para el manejo de los acompañantes de los pacientes
 * mientras este se encuentre en la institucion. 
 */

package adm_logica;
import java.sql.*;
import java.io.*;
import adm_bean.Acompanante;
import adm_logica.Conexion;


public class MetodoAcompanante {
	
	public MetodoAcompanante(){
	}
	/**
	 * Actualiza los datos del acompañante
	 */
	
	public void ActualizarAcomp(String codigocon, String nombre, String apellido, String direccion, String telefono, String cedula,String parentesco){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_contacto set nombre='"+nombre+"',apellido='"+apellido+"',direccion='"+direccion+"',telefono="+telefono+",cedula="+cedula+",parentesco='"+parentesco+"' where con_contactos_codigo="+codigocon+"");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	    }
	/**
	 * Obtine los datos del acompañanate segun el codigo
	 */
	public java.sql.ResultSet obtenerDatosAcomp(String codigo){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	String cd=null;
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre, apellido, direccion, telefono, cedula, parentesco from adm_contacto where con_contactos_codigo="+codigo+"");
                	
        }
        catch(Exception ex){
        	
        }
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerCodigoDatosAcomp(String nombre,String telefono){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select con_contactos_codigo from adm_contacto where nombre='"+nombre+"' and telefono='"+telefono+"'");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoAcompanante>>obtenerCodigoDatosAcomp "+ex);
        }
        return rs;
    } 
     /**
      * ingresa datos del acompañante en la tabla adm_contacto
      */
		public void insertar(String ced, String nom, String ape, String par, String tel, String dir,String Responsable){
		
			Acompanante a = new Acompanante();
			a.setApellido(ape);
			a.setCedula(ced);
			
			a.setNombre(nom);
			a.setParentesco(par);
			a.setTelefono(tel);
			a.setDireccion(dir);
			
			try{
				String apellido,direccion,telefono;
				
				apellido=a.getApellido();
				
				direccion=a.getDireccion();
				telefono=a.getTelefono();
				if(apellido.equals("")){					
					apellido="";
				}
                if(direccion.equals("")){
					direccion="";
				}
                if(telefono.equals("")){
					telefono="0";
				}
                           
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			 
			    ps=con.conn.prepareStatement("insert into adm_contacto(nombre,apellido,telefono,cedula,parentesco,direccion,responsable) values(?,?,?,?,?,?,?)");				
				ps.setString(1,a.getNombre());
				ps.setString(2,apellido);				
				ps.setString(3,telefono);
				ps.setString(4,a.getCedula());
				ps.setString(5,a.getParentesco());
				ps.setString(6,direccion);
				ps.setString(7, Responsable);
				ps.executeUpdate();
				
				ps.close();
				con.cerrar();
		
			}catch(Exception ex){
				System.out.print("Error en MetodoAcompanante>>insertar "+ex);
			}
			
			
		}

}
