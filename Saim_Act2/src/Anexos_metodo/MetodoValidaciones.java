package Anexos_metodo;

//import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoValidaciones {
	/**
	 * 
	 *obtiene nombre del pais y el codigo de los paises que hay en la base de datos 
	 */
	
	 public java.sql.ResultSet SQLPais(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre,pais_codigo from adm_pais");
       
        }
        catch(Exception ex){
        	
        }
        return rs;
    }
		public ResultSet getDeptos(){
			ResultSet rs = null;
		    Statement st = null;
		    try{
		    	Conexion con=new Conexion();
		    	st = con.conn.createStatement();
		        rs = st.executeQuery("SELECT nombre, dept_codigo FROM adm_departamento WHERE pais_codigo_fk=1 ORDER BY nombre ASC;");
		    } catch(Exception ex){
		    	System.err.println("ERROR en MetodoNacionalidad>>getDeptos\n"+ex);
		    }
		    return rs;
		}
	 public java.sql.ResultSet codconvenio(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        String yosi="PARTICULAR";
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select adm_convenio.conv_numero_contrato from adm_convenio,adm_entidad where adm_entidad.nombre_entidad='"+yosi+"' and adm_entidad.ent_nit_contratante=adm_convenio.ent_nit_contratante_fk");
	       
	        }
	        catch(Exception ex){
	        	
	        }
	        return rs;
	    }
	 
	 
	 /**
	  * Se obtiene el nombre de los departamento q tiene como codigo de pais el ingresado
	  */
	 public java.sql.ResultSet SQLDep(String x){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre from adm_departamento where pais_codigo_fk="+x);
	        }
	        catch(Exception ex){
	        	
	        }
	        return rs;
	    }
	 
	 /**
	  * Se obtiene el nombre de la naturaleza del evento q tiene como codigo de evento el seleccionado
	  */
	 public java.sql.ResultSet SQLEventoNaturaleza(String x){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select * from furips_eventonaturaleza where evento_fk="+x);
	        }
	        catch(Exception ex){
	        	
	        }
	        return rs;
	    }
	 
	 /**
	  * Se obtiene el nombre de los municipios q tiene como codigo de departamento el ingresado
	  */
	 
	 public java.sql.ResultSet SQLMun(String y){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre,muni_cod from adm_municipio where dept_codigo_fk="+y);
	           	
	        	 
	        }
	        catch(Exception ex){
	        System.out.print("Error en MetodoNacionalidad>>SQLMun "+ex);
	        }
	        return rs;
	 }
	 
	 
	 public java.sql.ResultSet SQLCodDepa(String nom){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT dept_codigo from adm_departamento where nombre='"+nom+"'");
	        }
	        catch(Exception ex){
	        	 System.out.println(ex);
	        }
	        return rs;
	 }
	 
	 /**
	  * 
	  * obtener el codigo del municipio segun su nombre
	  */
	 public java.sql.ResultSet SQLMunicipios(String y,String nomdepa){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select muni_cod from adm_municipio,adm_departamento where adm_municipio.nombre='"+y+"' and adm_departamento.nombre='"+nomdepa+"' and adm_municipio.dept_codigo_fk=adm_departamento.dept_codigo");
	        }
	        catch(Exception ex){
	        	
	        }
	        
	        return rs;
	 }
	 
	 /**
	  * obtener el nombre de la ocupacion existenes en la base de datos
	  */
	 
	 public java.sql.ResultSet SQLOcupacion(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ocu_nombre from adm_ocupacion_paciente");
	        	
	        }
	        catch(Exception ex){
	        	
	        }	
	        return rs;
	    }
	 
	 /**
	  * obtener codigo de la ocupacion segun el nombre de la ocupacion
	  */
	 public java.sql.ResultSet SQLCodOcupacion(String x){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ocu_codigo from adm_ocupacion_paciente where ocu_nombre='"+x+"'");
	        
	        }
	        catch(Exception ex){
	        	System.out.println("Error en Metodo SQLCodOcupacion "+ex);
	        }
	        return rs;
	    }
}
