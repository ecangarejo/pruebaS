/**
 * logica: MetodoPestanas se encuentran las inserciones,consultas y actualizaciones
 * para ver los datos principales de los pacientes que estan en las pestañas.
*/
package hic_metodo;

import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoPestanas {

	 public java.sql.ResultSet DatosPersonales(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      //System.out.println(nombre);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select pac.nombre,pac.primer_apellido,pac.segundo_apellido,(year(curdate())-year(pac.fecha_nacimiento))-(right(curdate(),5)<right(pac.fecha_nacimiento, 5)) as edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento from adm_paciente pac where pac.pac_codigo_paciente='"+CodPac+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: "+ex);
	        }
	      
	        return rs;
	        
	    }
}
