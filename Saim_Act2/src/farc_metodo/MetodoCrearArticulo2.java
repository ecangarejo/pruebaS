package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import farc_bean.CrearArticulo;

import adm_logica.Conexion;

public class MetodoCrearArticulo2 {

	public ResultSet listarGrupo() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT codigo, descripcion FROM farc_grupo");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearArtciculo2>>listarGrupo "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarUnidades() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT codigo, sigla FROM farc_unidades");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearArtciculo2>>listarUnidades "+ex);
	        }
	        	return r;     
	    }
	
	public ResultSet listarForma() {
		/**
		 */
		  java.sql.ResultSet r=null;
	      Statement st = null;
	      try{
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT codigo, descripcion FROM farc_formafarmaceutica ORDER BY forma_farmaceutica");
	      }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearArtciculo2>>listarForma "+ex);
	        }
	        	return r;     
	    }
	
	public void CrearArticulo(String codigoArticulo,String nombre,String concentracion,String observacion,String grupo,String nombreGenerico,String cod_unidadFK,String tipo,String tipoArticulo,String cod_formaFK,String control){
	/**
		 * creamos el articulo.
	 */
		CrearArticulo ca = new CrearArticulo();
		
		ca.setcodigoArticulo(codigoArticulo);
		ca.setnombre(nombre);
		ca.setconcentracion(concentracion);
		ca.setobservacion(observacion);
		ca.setgrupo(grupo);
		ca.setnombreGenerico(nombreGenerico);
		ca.setcod_unidadFK(cod_unidadFK);
		ca.settipo(tipo);
		ca.settipoArticulo(tipoArticulo);
		ca.setcod_formaFK(cod_formaFK);
		ca.setcontrol(control);
		
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into medicamentos(codigoMedicamento,nombre,concentracion,descripcion,cod_grupoFK,nombre_generico,cod_unidadFK,clasificacion,tipo,cod_formaFK,control)values(?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,ca.getcodigoArticulo());
			    ps.setString(2,ca.getnombre());
			    ps.setString(3,ca.getconcentracion());
			    ps.setString(4,ca.getobservacion());
			    ps.setString(5,ca.getgrupo());
			    ps.setString(6,ca.getnombreGenerico());
			    ps.setString(7,ca.getcod_unidadFK());
			    ps.setString(8,ca.gettipo());
			    ps.setString(9,ca.gettipoArticulo());
			    ps.setString(10,ca.getcod_formaFK());
			    ps.setString(11,ca.getcontrol());
			  	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearArticulo2>>CrearArticulo "+ex);
			}

		}
	
	
	public java.sql.ResultSet ObtenerCodigoMedicamento(String CodigoATC,String NombreMedicamento){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM medicamentos WHERE codigoMedicamento='"+CodigoATC+"' AND nombre='"+NombreMedicamento+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo2>>ObtenerCodigoMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoPrograma(String Fecha,String Hora){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_programa FROM fact_programas WHERE fecha='"+Fecha+"' AND hora='"+Hora+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo2>>ObtenerCodigoPrograma "+ex);
        }	
        return rs;
    }
	
	public void CrearProgramaMedicamento(String CodigoArticulo,String CodigoPrograma){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO fact_prog_med (cod_med,cod_prog) VALUES (?,?)");
				    ps.setString(1,CodigoArticulo);
				    ps.setString(2,CodigoPrograma);
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo2>>CrearProgramaMedicamento "+ex);
				}

			}
	
	public void CrearProgramaFacturacion(String CodigoATC,String NombreArticulo,String Usuario,String Fecha,String Hora){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    //ps=con.conn.prepareStatement("INSERT INTO fact_programas (manual_base,nivel_complejidad,cod_referencia,cod_cups,descripcion,especialidad,clase_servicio,subcentro_costo,archivo_rip,rn,fecha,hora,cod_usuario,actoqx,medico,idporcentajeqx) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps=con.conn.prepareStatement("INSERT INTO fact_programas (manual_base,nivel_complejidad,cod_referencia,descripcion,especialidad,clase_servicio,subcentro_costo,archivo_rip,rn,fecha,hora,cod_usuario,actoqx,medico,idporcentajeqx) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,"3");
				    ps.setString(2,"2");
				    ps.setString(3,CodigoATC);
				    //ps.setString(4,txtCodigoCUM);
				    ps.setString(4,NombreArticulo);
				    ps.setString(5,"86");
				    ps.setString(6,"12");
				    ps.setString(7,"4");
				    ps.setString(8,"9");
				    ps.setString(9,"0");
				    ps.setString(10,Fecha);
				    ps.setString(11,Hora);
				    ps.setString(12,Usuario);
				    ps.setString(13,"0");
				    ps.setString(14,"0");
				    ps.setString(15,"0");
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo2>>CrearProgramaFacturacion "+ex);
				}

			}
	
	public void CrearTarifaArticulo(String manual_tarifario,String CodigoArticulo){

		try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO fact_tarifas (manual_tarifario,programa,fecha_ini,fecha_fin) VALUES (?,?,?,?)");
				    ps.setString(1,manual_tarifario);
				    ps.setString(2,CodigoArticulo);
				    //ps.setString(3,Valor);
				    ps.setString(3,"2014-01-01");
				    ps.setString(4,"2014-12-31");
				  	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearArticulo2>>CrearProgramaMedicamento "+ex);
				}

			}
	
}
