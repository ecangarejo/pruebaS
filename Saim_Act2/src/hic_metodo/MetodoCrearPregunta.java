/**
 * logica: MetodoCrearPregunta se encuentran las inserciones,consultas y actualizaciones
 * para el manejo de la creacion de las preguntas y tipos de respuestas.
*/
package hic_metodo;

import hic_bean.CrearPregunta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoCrearPregunta {

	public void CrearPregunta(String nombrePregunta,String PatronNormal,String UnidadPregunta,String TipoRequerimiento){
		/**
		 * se crea la pregunta tiene como parametro el nombre de la pregunta
		 * ya que el codigo es autoincremental.
		 */
		 CrearPregunta cp = new CrearPregunta();
		   cp.setNombrePregunta(nombrePregunta);
		   cp.setPatronNormal(PatronNormal);
		   cp.setUnidadPregunta(UnidadPregunta);
		   cp.setTipoRequerimiento(TipoRequerimiento);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_pregunta(nombre_pregunta,patronNormal,unidad,requerido)values(?,?,?,?)");
				    ps.setString(1, cp.getNombrePregunta());
				    ps.setString(2, cp.getPatronNormal());
				    ps.setString(3, cp.getUnidadPregunta());
				    ps.setString(4, cp.getTipoRequerimiento());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN METODO:_ CrearPregunta "+ex);
				}
			}
	public void RelacionPreguntaTipoRespuesta(String codigo_tiporespuesta_fk,String codigo_pregunta_fk){
		/**
		 * se crea la relacion de la pregunta con el tipo de respuesta
		 * llevando como paramtro el codigo de la pregunta y el del tipo de respuesta.
		 */
		 CrearPregunta cp = new CrearPregunta();
		 cp.setcodigo_tiporespuesta_fk(codigo_tiporespuesta_fk);
		 cp.setcodigo_pregunta_fk(codigo_pregunta_fk);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_pregunta_tiporespuesta(codigo_pregunta_fk,codigo_tiporespuesta_fk) values (?,?)");
				    ps.setString(1, cp.getcodigo_pregunta_fk());
				    ps.setString(2, cp.getcodigo_tiporespuesta_fk());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN METODO:_ RelacionPreguntaTipoRespuesta "+ex);
				}

			}
	
	public ResultSet listarParaAutocompletarTipoRespuesta(String cod) throws Exception {
		/**
		 * se buscan los diferentes tipo de respuesta y se envia de estos el nombre 
		 * y el codigo.... lleva como parametro el nombre del tipo de respuesta.
		 * 
		 * */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT codigo,nombre_tiporespuesta FROM hic_tipo_respuesta WHERE nombre_tiporespuesta LIKE '"+cod+"%'");
	        return r;
	    }
	
	public ResultSet ObtenerCodigoPregunta(String nombrePregunta) throws Exception {
		/**
		 * se obtiene el codigo de la pregunta llevando como parametro el nombre de la pregunta.
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select codigo,nombre_pregunta from hic_pregunta where nombre_pregunta='"+nombrePregunta+"'");
	        return r;
	    }
}
