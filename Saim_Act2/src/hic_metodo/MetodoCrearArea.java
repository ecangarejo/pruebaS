/**
 * logica: MetodoCrearArea se encuentran las inserciones,consultasy actualizaciones
 * para el manejo de las preguntas, los tipos de respuesta y la relacion entre estas.  
*/
package hic_metodo;

import hic_bean.CrearArea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoCrearArea {
	
	
	public void OmitirAsignacion(String codRelacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_area_pregunta where codigo='"+codRelacion+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN METODO OmitirAsignacion:_ "+ex);
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
			    ps=con.conn.prepareStatement("insert into hic_area(nombre_area)values(?)");
			    ps.setString(1, ca.getNombreArea());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN METODO:_ CrearGrupo "+ex);
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
	        	r=st.executeQuery("SELECT codigo,nombre_pregunta FROM hic_pregunta WHERE nombre_pregunta LIKE '"+cod+"%'");
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
        	rs=st.executeQuery("select codigo,nombre_area from hic_area where nombre_area='"+NomArea+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerCodigoArea "+ex);
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
				    ps=con.conn.prepareStatement("insert into hic_area_pregunta(codigo_area_fk,codigo_pregunta_fk)values(?,?)");
				    ps.setString(1, ca.getcodigo_area_fk());
				    ps.setString(2, ca.getcodigo_pregunta_fk());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN METODO:_ RelacionAreaPregunta "+ex);
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
        	rs=st.executeQuery("select codigo from hic_area_pregunta where codigo_pregunta_fk='"+CodPregunta+"' and codigo_area_fk='"+CodArea+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerRelacionAreaPregunta "+ex);
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
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.nombre_tiporespuesta,hap.codigo from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_respuestas hr,hic_tipo_respuesta htr,hic_condicion_respuesta hcr where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and ha.codigo='"+CodArea+"' and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPreguntasTipoRespuestas "+ex);
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
        	rs=st.executeQuery("select codigo,nombre_area from hic_area");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerAreas "+ex);
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
        	rs=st.executeQuery("select codigo,nombre_pregunta from hic_pregunta");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPreguntas "+ex);
        }	
        return rs;
    }
	
	
}
