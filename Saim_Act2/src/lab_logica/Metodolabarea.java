/**
 * logica: Metodolabarea se encuentran las consultas,inserciones y actualizaciones para  
 * para consultas de area,unidades y clases de rangos.
*/
package lab_logica;

import java.sql.Statement;

import adm_logica.Conexion;


public class Metodolabarea {
	
	 public java.sql.ResultSet labarea(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre from lab_area  where codigo>0");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error Metodolabarea>>labarea "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet labunidad(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre from lab_unidad");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error Metodolabarea>>labunidad "+ex);
	        }
	      
	        return rs;
	        
	    }
	 
	 public java.sql.ResultSet labrango(String nom){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_rango.valorinicial,lab_rango.valorfinal from lab_unidad,lab_rango WHERE lab_unidad.nombre='"+nom+"' and lab_unidad.codigo=lab_rango.codunidad_fk ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error Metodolabarea>>labrango "+ex);
	        }
	      
	        return rs;
	        
	    }

}
