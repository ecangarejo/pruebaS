/**
 * logica: MetodoSubarea se encuentran las consultas,inserciones y actualizaciones para  
 * para el ingreso y consulta de las diferentes subareas..
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

import lab_bean.Subarea;

public class MetodoSubarea {
	
	
	public void InsertarProg_Lab(String CodLabora, String CodPrograma, String tipo_examen){
		
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into fact_prog_lab(cod_prog,cod_lab,tipo_examen)values(?,?,?)");
		    ps.setString(1,CodPrograma);
		    ps.setString(2,CodLabora);
		    ps.setString(3,tipo_examen);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();
			
		}catch(Exception ex){
			System.out.println("Error en MetodoSubarea>>InsertarProg_Lab "+ex);
		}
		
		
	}
	
	  public java.sql.ResultSet ObtenerCodigoSubarea(String nombre,String codigoArea){
	    
	    	java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" select codigo from lab_subarea where nombre='"+nombre+"' and codarea_fk='"+codigoArea+"'");
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoSubarea>>ObtenerCodigoSubarea "+ex);
	        }	
	        return rs;
	    }
	
	 public void insertar(String nombre, String codigoArea){
	    	/**
	    	 * en este metodo se insertan los datos de la nueva subarea creada
	    	 * que trae consigo el nombre de la subarea y el codigo interno del area a la
	    	 * cual se va asignar la nueva subarea.
	    	 * toma como dato de entrada el nombre de la subarea y el codigo del area.
	    	 */
	    	
		 Subarea sb = new Subarea();
		 sb.setNombre(nombre);
	     sb.setCodigoArea(codigoArea);
			
			try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into lab_subarea(nombre,codarea_fk)values(?,?)");
			    ps.setString(1, sb.getNombre());
			    ps.setString(2,sb.getCodigoArea());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();
				
			}catch(Exception ex){
				System.out.println("Error en MetodoSubarea>>insertar "+ex);
			}
			
			
		}
	    
	    public java.sql.ResultSet SQLArea(){
	    	/**
			 * en este metodo se obtiene el nombre del area de la clinca
			 * si es urgencia,hospitalizacion,uci's o cualquier otra.
			 * */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre from lab_area where codigo >0");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoSubarea>>SQLArea "+ex);
	        }	
	        return rs;
	    }
		
	    public java.sql.ResultSet SQLCodArea(String nom){
	    	/**
	    	 * en este metodo se obtiene el codigo interno del area 
	    	 * para posteriormente asignarlo en la insercion del subarea
	    	 * se toma como dato de entrada el nombre de la subarea.
	    	 */
	    	java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo from lab_area where nombre='"+nom+"'");
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoSubarea>>SQLCodArea "+ex);
	        }	
	        return rs;
	    }
}
