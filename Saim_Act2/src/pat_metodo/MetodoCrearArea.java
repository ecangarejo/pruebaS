/**
 * logica: MetodoCrearArea se encuentran las inserciones,consultasy actualizaciones
 * para el manejo de las preguntas, los tipos de respuesta y la relacion entre estas.  
*/
package pat_metodo;

import pat_bean.CrearArea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoCrearArea {
	
	
	public void OmitirAsignacion(String codRelacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from pat_area_pregunta where codigo='"+codRelacion+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoCrearArea>>OmitirAsignacion: "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void CrearArea(String NomArea){
		/**
		 * creamos el area tiene como parametro el nombre del area ya que el codigo es un autoincremental.
		 */
	   CrearArea ca = new CrearArea();
	   ca.setNombreArea(NomArea);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pat_area(nombre_area)values(?)");
			    ps.setString(1, ca.getNombreArea());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArea>>CrearArea: "+ex);
			}

		}
	
	public ResultSet listarParaAutocompletarPregunta(String cod) throws Exception {
		/**
		 * la consulta tiene como parametro el nombre de la pregunta
		 * y arroja como resultado el nombre y el codigo de la pregunta
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT codigo,nombre_pregunta FROM pat_pregunta WHERE nombre_pregunta LIKE '"+cod+"%'");
	        return r;
	    }
	
	public java.sql.ResultSet ObtenerCodigoArea(String NomArea){	
		/**
		 * consulta tiene como parametro el nombre del area, 
		 * para buscar si ya existe una con ese nombre.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_area from pat_area where nombre_area='"+NomArea+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArea>>ObtenerCodigoArea "+ex);
        }	
        return rs;
    }
	
	public void RelacionAreaPregunta(String CodArea,String CodPregunta){
		/**
		 * se crea la relacion del area con la pregunta tiene como parametro
		 * el codigo del area y el codigo de la pregunta.
		 */
		   CrearArea ca = new CrearArea();
		   ca.setcodigo_area_fk(CodArea);
		   ca.setcodigo_pregunta_fk(CodPregunta);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into pat_area_pregunta(codigo_area_fk,codigo_pregunta_fk)values(?,?)");
				    ps.setString(1, ca.getcodigo_area_fk());
				    ps.setString(2, ca.getcodigo_pregunta_fk());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArea>>RelacionAreaPregunta "+ex);
				}
			}
	
	public java.sql.ResultSet ObtenerRelacionAreaPregunta(String CodPregunta,String CodArea){
		/**
		 * se busca si la combinacion pregunta area ya existe para evitar duplicidad en los datos
		 * tiene como parametro el codigo de la pregunta y el codigo del area 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from pat_area_pregunta where codigo_pregunta_fk='"+CodPregunta+"' and codigo_area_fk='"+CodArea+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArea>>ObtenerRelacionAreaPregunta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPreguntasTipoRespuestas(String CodArea){
		/**
		 * se obtienen las preguntas que tiene el area a la cual se le esta asignado
		 * para evitar duplicidad en los datos, lleva como parametro el codigo del area.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.nombre_tiporespuesta,hap.codigo from pat_area ha,pat_area_pregunta hap,pat_pregunta hp,pat_pregunta_tiporespuesta hpt,pat_respuestas hr,pat_tipo_respuesta htr where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and ha.codigo='"+CodArea+"' and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArea>>ObtenerPreguntasTipoRespuestas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerAreas(){
		/**
		 * se obtienen las areas creadas que van a pertenecer a los formatos.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_area from pat_area");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArea>>ObtenerAreas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPreguntas(){
		/**
		 * se obtienen las preguntas que se le van a asignar a una area determinada.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_pregunta from pat_pregunta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArea>>ObtenerPreguntas "+ex);
        }	
        return rs;
    }
	
	
}
