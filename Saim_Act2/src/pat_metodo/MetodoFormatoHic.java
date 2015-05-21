/**
 * logica: MetodoFormatoHic se encuentran las inserciones,consultas y actualizaciones
 * para el manejo de la creacion de los formatos y la asignacion de las areas
 * para cada formato.
*/
package pat_metodo;

import pat_bean.CrearArea;
import pat_bean.CrearFormatoHic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoFormatoHic {

	public ResultSet listarParaAutocompletarArea(String cod) throws Exception {
		/**
		 * se buscan todas las coincidencias de las areas por medio del parametro
		 * que es el nombre del area.
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT codigo,nombre_area FROM pat_area WHERE nombre_area LIKE '"+cod+"%'");
	        return r;
	    }
	
	public java.sql.ResultSet ObtenerCodigoFormato(String NomFormato){	
		/**
		 * se busca si el formato a crear ya existe y lleva consigo el codigo
		 * y el nombre del formato.... tiene como parametro el nombre del formato.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_formato from pat_formato where nombre_formato='"+NomFormato+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoFormatoHic>>ObtenerCodigoFormato "+ex);
        }	
        return rs;
    }
	
	public void CrearFormato(String NomFormato){
		/**
		 * se crea el formato llevando como parametro el nombre del formato
		 * ya que e codigo es un autoincremental..
		 */
		CrearFormatoHic cfh = new CrearFormatoHic();
		cfh.setNombreFormato(NomFormato);
		String repetido="0";
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into pat_formato(nombre_formato,repetido)values(?,?)");
				    ps.setString(1,cfh.getNombreFormato());
				    ps.setString(2,repetido);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoFormatoHic>>CrearFormato "+ex);
				}
			}
	
	public java.sql.ResultSet ObtenerRelacionAreaFormato(String CodArea,String CodFormato){
		/**
		 * se verfica si ya existe una relacion entre el area y el formato a crear
		 * de ser asi se lleva consigo el codigo de la relacion
		 *  
		 * si no lleva los valores nullos...
		 * tiene como parametro el codigo del area y el codigo del formato 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from pat_formato_area where codigo_area_fk='"+CodArea+"' and codigo_formato_fk='"+CodFormato+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoFormatoHic>>ObtenerRelacionAreaFormato "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerAreasFormato(String CodFormato){
		/**
		 * se buscan las areas que hacen parte del formato al cual se le estan asignando areas
		 * tiene como parametro el codigo del formato
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select ha.nombre_area,hfa.codigo from pat_area ha,pat_formato hf,pat_formato_area hfa where ha.codigo=hfa.codigo_area_fk and hf.codigo=hfa.codigo_formato_fk and hf.codigo='"+CodFormato+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoFormatoHic>>ObtenerAreasFormato "+ex);
        }	
        return rs;
    }
	
	public void RelacionAreaFormato(String CodFormato,String CodArea){
		/**
		 * se crea la relacion del formato con el area, tiene como parametro
		 * el codigo del area y el codigo del formato.
		 */
		CrearFormatoHic cfh = new CrearFormatoHic();
		cfh.setcodigo_area_fk(CodArea);
		cfh.setcodigo_formato_fk(CodFormato);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into pat_formato_area(codigo_area_fk,codigo_formato_fk)values(?,?)");
				    ps.setString(1, cfh.getcodigo_area_fk());
				    ps.setString(2, cfh.getcodigo_formato_fk());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoFormatoHic>>RelacionAreaFormato "+ex);
				}
			}
	
	public void OmitirAsignacion(String codRelacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from pat_formato_area where codigo='"+codRelacion+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoFormatoHic>>OmitirAsignacion:_ "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ObtenerFormatos(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_formato from pat_formato");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoFormatoHic>>ObtenerFormatos "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet ObtenerAreas(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_area from pat_area");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoFormatoHic>>ObtenerAreas "+ex);
        }	
        return rs;
    }
}
