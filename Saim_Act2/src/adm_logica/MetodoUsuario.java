/**
 * NOMBRE DE LA CLASE:MetodoUsuario
 * AUTOR:Oscar Rolong Schweiger
 * DESCRIPCION: En este metodo se encuentra lo necesario para insertar,consultar 
 * 				los usuarios del sistema.
 */

package adm_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;


import adm_bean.Usuarios;

public class MetodoUsuario {

    public void insertarusuario(String NomUsuario, String Password){
    	/**
    	 * en este metodo se inserta el nuevo usuario con los datos digitados 
    	 * en el formulario.
    	 * se toman como datos de entrada el nombre de usuario y la contraseña de este.
    	 */
    Usuarios us = new Usuarios ();
    us.setNomUsuario(NomUsuario);
    us.setPassword(Password);
		
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into adm_usuarios(nombre_usuario,password) values(?,?)");				
			ps.setString(1, us.getNomUsuario());
			ps.setString(2, us.getPassword());		    
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			
		}catch(Exception ex){
			
		}	
		
	}
    
    
    public java.sql.ResultSet obtenerCodOcupacion(String ocu){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select ocu_codigo from adm_ocupacion_paciente where ocu_nombre='"+ocu+"'");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
    
    
	public java.sql.ResultSet obtenerNomUsuario(String codigou){
		/**
		 * en este metodo se obtiene el nombre del usuario de session.
		 * se toma como dato de entrada el codigo interno del usuario.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre_usuario from adm_usuarios where cod_usuario="+codigou+"");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
    
    
	public java.sql.ResultSet obtenerUsuario(String Usuario,String contra){
		/**
		 * en este metodo se consulta si el usuario existe o no
		 * a la hora de hacer el login.
		 * se toma como dato de entrada el usuario y contraseña.
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cod_usuario from adm_usuarios where nombre_usuario='"+Usuario+"' and password='"+contra+"'");
        }
        catch(Exception ex){ 
        }
        return rs;
    }
    
    
	
}
