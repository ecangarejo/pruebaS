package farc_metodo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import adm_logica.Conexion;
import farc_bean.CrearIvaGrupoUnidad;
import farc_bean.CrearBodega;//Borrar

public class MetodoCrearIvaGrupoUnidad {

	public void CrearIva(String nombreIva,String valor,String descripcion){
		/**
		 * creamos los tipos de iva
		 */
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreIva(nombreIva);
		ci.setvalor(valor);
		ci.setdescripcion(descripcion);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_iva(descripcion,valor,observacion)values(?,?,?)");
			    ps.setString(1,ci.getnombreIva());
			    ps.setString(2,ci.getvalor());
			    ps.setString(3,ci.getdescripcion());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearIva "+ex);
			}

		}
	
	public void CrearGrupo(String nombreGrupo,String observacion, String tipog){
		/**
		 * creamos los grupos
		 */
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreGrupo(nombreGrupo);
		ci.setobservacion(observacion);
		ci.settipo(tipog);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_grupo(descripcion,observacion,tipo)values(?,?,?)");
			    ps.setString(1,ci.getnombreGrupo());
			    ps.setString(2,ci.getobservacion());
			    ps.setString(3,ci.gettipo());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearGrupo "+ex);
			}

		}
	
	
	public void CrearUnidad(String nombreUnidad,String sigla){
		/**
		 * creamos los grupos
		 */
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreUnidad(nombreUnidad);
		ci.setsigla(sigla);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_unidades(descripcion,sigla)values(?,?)");
			    ps.setString(1,ci.getnombreUnidad());
			    ps.setString(2,ci.getsigla());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearUnidad "+ex);
			}

		}
	
	public void CrearForma(String nombreForma,String siglaF){
		/**
		 * creamos los grupos
		 */
		CrearIvaGrupoUnidad ci = new CrearIvaGrupoUnidad();
		
		ci.setnombreForma(nombreForma);
		ci.setsiglaF(siglaF);
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into farc_formafarmaceutica(descripcion,forma_farmaceutica)values(?,?)");
			    ps.setString(1,ci.getnombreForma());
			    ps.setString(2,ci.getsiglaF());
			    ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearIvaGrupoUnidad>>CrearFormaFarmaceutica "+ex);
			}

		}
	
	
public java.sql.ResultSet ObtenerIva(){	
		
		// consulta que obtiene los diferentes tipos de ivas., 
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,descripcion, valor, observacion from farc_iva");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerIva "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet ObtenerGrupo(){	
	
	// consulta que obtiene los diferentes Grupos., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,observacion from farc_grupo");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerGrupo "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerUnidad(){	
	
	// consulta que obtiene los diferentes Unidades., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,sigla from farc_unidades");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerUnidad "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ObtenerForma(){	
	
	// consulta que obtiene los diferentes Formas Farmaceuticas., 
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,descripcion,forma_farmaceutica from farc_formafarmaceutica");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerUnidad "+ex);
    }	
    return rs;
}

	
public java.sql.ResultSet ObtenerCodigoIva(String nombreIva){	
	
	 // consulta que obtiene las bodegas, 
	 
   java.sql.ResultSet rs=null;
   Statement st = null;
   try{
   	Conexion con=new Conexion();
   	st = con.conn.createStatement();
   	rs=st.executeQuery("select codigo from farc_iva where descripcion='"+nombreIva+"'");
   }
   catch(Exception ex){
   	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoIva "+ex);
   }	
   return rs;
}

public java.sql.ResultSet ObtenerCodigoGrupo(String nombreGrupo){	
	
	 // consulta que obtiene las bodegas, 
	 
  java.sql.ResultSet rs=null;
  Statement st = null;
  try{
  	Conexion con=new Conexion();
  	st = con.conn.createStatement();
  	rs=st.executeQuery("select codigo from farc_grupo where descripcion='"+nombreGrupo+"'");
  }
  catch(Exception ex){
  	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoGrupo "+ex);
  }	
  return rs;
}


public java.sql.ResultSet ObtenerCodigoUnidad(String nombreUnidad){	
	
	 // consulta que obtiene las bodegas, 
	 
 java.sql.ResultSet rs=null;
 Statement st = null;
 try{
 	Conexion con=new Conexion();
 	st = con.conn.createStatement();
 	rs=st.executeQuery("select codigo from farc_unidades where descripcion='"+nombreUnidad+"'");
 }
 catch(Exception ex){
 	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoUnidad "+ex);
 }	
 return rs;
}

public java.sql.ResultSet ObtenerCodigoForma(String nombreForma){	
	
	 // consulta que obtiene las bodegas, 
	 
java.sql.ResultSet rs=null;
Statement st = null;
try{
	Conexion con=new Conexion();
	st = con.conn.createStatement();
	rs=st.executeQuery("select codigo from farc_formafarmaceutica where descripcion='"+nombreForma+"'");
}
catch(Exception ex){
	System.out.println("Error en MetodoCrearIvaGrupoUnidad>>ObtenerCodigoForma "+ex);
}	
return rs;
}

}

