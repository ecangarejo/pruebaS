
package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import adm_logica.Conexion;
import farc_bean.CrearBodega;

public class MetodoCrearBodega {

	
	public void CrearBodega(String nombreBodega,String descripcion){
		/**
		 * creamos la Bodega
		 */
		CrearBodega cb = new CrearBodega();
		
		cb.setnombreBodega(nombreBodega);
		cb.setdescripcion(descripcion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_bodegas(descripcion,nombre)values(?,?)");
			    ps.setString(1,cb.getdescripcion());
			    ps.setString(2,cb.getnombreBodega());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearBodega>>CrearBodega "+ex);
			}

		}
	
	public void ModificarBodega(String nombreBodega,String descripcion, String c){
	
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement("update farc_bodegas set nombre='"+nombreBodega+"', descripcion='"+descripcion+"' where codigo='"+c+"'");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearBodega>>ModificarBodega "+ex);
				}

			}
	
	public void CrearEstante(String nombreEstante,String observacion){
		/**
		 * creamos El estante
		 */
		CrearBodega cb = new CrearBodega();
		
		cb.setnombreEstante(nombreEstante);
		cb.setobservacion(observacion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_estante(descripcion,observacion)values(?,?)");
			    ps.setString(1,cb.getnombreEstante());
			    ps.setString(2,cb.getobservacion());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearBodega>>CrearEstante "+ex);
			}

		}
	
	
	public void ModificarEstante(String nombreEstante,String observacion, String c){
		
		try{
			PreparedStatement st = null;
			    Conexion con=new Conexion();                                                      
			  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
			    st= con.conn.prepareStatement("update farc_estante set descripcion='"+nombreEstante+"', observacion='"+observacion+"' where codigo='"+c+"'");
		    	st.executeUpdate();
		    	st.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearBodega>>ModificarEstante "+ex);
			}

		}
	
	public void CrearBodegaEstante(String cBodega,String cEstante){
		/**
		 * creamos El estante
		 */
	//	CrearBodega cb = new CrearBodega();
		
	//	cb.setnombreEstante(nombreEstante);
	//	cb.setobservacion(observacion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_bodest(cod_bodegaFk,cod_estanteFk)values(?,?)");
			    ps.setString(1,cBodega);
			    ps.setString(2,cEstante);
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearBodega>>CrearEstante "+ex);
			}

		}
	
	
	public java.sql.ResultSet ObtenerCodigoBodega(String nombreBodega){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from farc_bodegas where nombre='"+nombreBodega+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoBodega "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerCodigoEstante(String nombreEstante){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from farc_estante where descripcion='"+nombreEstante+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoEstante "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerBodegas(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, nombre from farc_bodegas");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerBodegas "+ex);
        }	
        return rs;
    }
	
		
	public java.sql.ResultSet ObtenerEstantes(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select e.codigo, e.descripcion from farc_estante e where e.codigo not in (select b.cod_estanteFk from farc_bodest b)");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerEstantes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstante(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo, descripcion from farc_estante");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerEstantes "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerBodegaEstante(String codBodega, String codEstante){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from farc_bodest where cod_bodegaFk='"+codBodega+"' and cod_estanteFk= '"+codEstante+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoEstante "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerBodega(String codBod){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre, descripcion from farc_bodegas where codigo='"+codBod+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoEstante "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstante(String codEstante){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select descripcion, observacion from farc_estante where codigo='"+codEstante+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoEstante "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerBodegaEstante(String codBodega){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from farc_bodest where cod_bodegaFk='"+codBodega+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoEstante "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstantedeBodega(String codBodega){	
		/**
		 * consulta que obtiene las bodegas, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cod_estanteFk from farc_bodest where cod_bodegaFk='"+codBodega+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearBodega>>ObtenerCodigoEstante "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerUnidad(){	
		/**
		 * consulta que obtiene las unidades del modulo de farmacia, 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,sigla from farc_unidades");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerUnidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerIva(){	
		/**
		 * consulta que obtiene los diferentes tipos de ivas., 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion from farc_iva");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerIva "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormaFarmaceutica(){	
		/**
		 * consulta que obtiene las diferentes formas farmaceuticas 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion  from farc_formafarmaceutica");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearArticulo>>ObtenerFormaFarmaceutica "+ex);
        }	
        return rs;
    }
	
	
	public void BorrarEstantesA(String code){
			try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("delete from farc_bodest where cod_estanteFk='"+code+"'");
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearBodega>>BorrarEstantesA "+ex);
			}

		}
	
	
}











