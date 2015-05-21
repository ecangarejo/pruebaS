/**
 * logica: MetodolabPa se encuentran las consultas,inserciones y actualizaciones para  
 * para actualizacion de los datos del paciente(modulo individual).
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodolabPa {
	
	 public java.sql.ResultSet SQLlabPac(String numero,String Tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.pac_codigo_paciente,lpac.genero,lpac.segundo_apellido,(year(curdate())-year(lpac.fecha_nacimiento)) - (right(curdate(),5) < right(lpac.fecha_nacimiento, 5)) as edad from adm_paciente lpac where lpac.tipo_documento='"+Tipo+"' and lpac.numero_documento='"+ numero+"'");

	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodolabPa>>SQLlabPac "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet Buscarcodpa(String ced,String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	       
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo,nombre,genero,edad,telefono,direccion,apellidos,fecha_nacimiento from lab_paciente where lab_paciente.tipo_documento='"+tipo+"' and lab_paciente.cedula='"+ ced+"' ");
	        }
	        catch(Exception ex){
	        	ex.notify();
	        	System.out.println("Error MetodolabPa>>Buscarcodpa "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet buscarUsu(String usu){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select dato.nombre,dato.apellido from seg_datos_personales dato,seg_usuario usu where dato.dat_codigo=usu.dat_codigo_fk and usu.usu_codigo="+usu+"");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodolabPa>>buscarUsu "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	  public ResultSet listar(String cod) throws Exception {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();	        	
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select distinct lab_examen.nombre from lab_examen where lab_examen.codigosubarea_fk=0 and  lab_examen.nombre like '"+cod+"%'order by nombre ");
	        return r;
	    }
	 
	  public ResultSet listarParaAutocompletarControl(String cod,String tipo) throws Exception {
		  java.sql.ResultSet r=null;
		  Statement st = null;
		  Conexion con=new Conexion();
		  st = con.conn.createStatement();
		  r=st.executeQuery("SELECT numero_documento,nombre,primer_apellido,segundo_apellido FROM adm_paciente WHERE numero_documento LIKE '"+cod+"%'and tipo_documento='"+tipo+"'");
		  return r;
	    }
	  public ResultSet listarParaAutocompletarControl_nombre(String cod) throws Exception {
		  java.sql.ResultSet r=null;
		  Statement st = null;
		  Conexion con=new Conexion();
		  st = con.conn.createStatement();
		  r=st.executeQuery("SELECT numero_documento,nombre,primer_apellido,tipo_documento,segundo_apellido FROM adm_paciente WHERE nombre LIKE '"+cod+"%'or primer_apellido LIKE '"+cod+"%' or segundo_apellido LIKE '"+cod+"%'");
		  return r;
	    }
	  
		public void ActualizarPaciente(String codigo, String nombre, String genero, String cedula, String edad,String telefono, String direccion, String apellidos, String tipo_documento, String fecha_nacimiento){
		       PreparedStatement st = null;
		        try{
		        	Conexion con=new Conexion();
		        	st= con.conn.prepareStatement("Update lab_paciente set lab_paciente.nombre='"+nombre+"',lab_paciente.genero='"+genero+"',lab_paciente.cedula="+cedula+",lab_paciente.edad="+edad+",lab_paciente.telefono="+telefono+",lab_paciente.direccion='"+direccion+"',lab_paciente.apellidos='"+apellidos+"',lab_paciente.tipo_documento='"+tipo_documento+"', lab_paciente.fecha_nacimiento='"+fecha_nacimiento+"' where lab_paciente.codigo="+codigo+"");
		        	st.executeUpdate();
		        	st.close();
		        	con.cerrar();
		        }
		        catch(Exception ex){
		        	System.out.println("ERROR EN MetodolabPa>>ActualizarPaciente "+ex);
		        	ex.getMessage();
		        	ex.notify();		        
		        }			       
		    }
}