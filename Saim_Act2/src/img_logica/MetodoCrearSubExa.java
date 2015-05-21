/**
 * logica: MetodoCrearSubExa se encuentra las consultas,inserciones y actualizaciones
 * para la creacion de los estudios y asignarlos a las diferentes grupos.
*/
package img_logica;

import img_bean.CrearSubExa;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;



public class MetodoCrearSubExa {
	
	public void Insertprog_imag(String CodImagen, String CodPrograma){
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement("insert into fact_prog_imag(cod_prog,cod_imag)values(?,?)");
			ps.setString(1,CodPrograma);
			ps.setString(2,CodImagen);
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("Error en MetodoCrearSubExa>>Insertprog_imag "+ex);
		}
	}
	
	public java.sql.ResultSet ObtenerCodigoImagen(String cod_gruFk,String numeroExamen,String nombre){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from img_subexa where cod_gruFk='"+cod_gruFk+"' and numeroExamen='"+numeroExamen+"' and nombre='"+nombre+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearSubExa>>ObtenerCodigoImagen "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoPrograma(String nombre,Date fecha,Time hora,String CodUsu){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cod_programa from fact_programas where descripcion='"+nombre+"' and fecha='"+fecha+"' and hora='"+hora+"' and cod_usuario='"+CodUsu+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCrearSubExa>>ObtenerCodigoPrograma "+ex);
        }	
        return rs;
    }
	

	public void CrearPrograma(String mabase,String comp,String cref,String cups,String desc,String espe,String serv,String subc,String rips,String rn,Date fecha,Time hora,String user){
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement("insert into fact_programas(manual_base,nivel_complejidad,cod_referencia,cod_cups,descripcion,especialidad,clase_servicio,subcentro_costo,archivo_rip,rn,fecha,hora,cod_usuario)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,mabase);
			ps.setString(2,comp);
			ps.setString(3,cref);
			ps.setString(4,cups);
			ps.setString(5,desc);
			ps.setString(6,espe);
			ps.setString(7,serv);
			ps.setString(8,subc);
			ps.setString(9,rips);
			ps.setString(10,rn);
			ps.setDate(11,fecha);
			ps.setTime(12,hora);
			ps.setString(13,user);
			ps.executeUpdate();
		    ps.close();
		    con.cerrar();    
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoCrearSubExa>>CrearPrograma "+ex);
		}
	}
	
	  public java.sql.ResultSet ObtenerArea(){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo,nombre from img_grupo");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearSubExa>>ObtenerArea "+ex);
	        }	
	        return rs;
	    }
	
	  public void InsertExaSub(String cod_gruFk, String nombre,String numeroExamen,String patron){
		  CrearSubExa cse = new CrearSubExa();
		  cse.setcod_gruFk(cod_gruFk);
		  cse.setnombre(nombre);
		  cse.setnumeroExamen(numeroExamen);
		  cse.setPatron(patron);

			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into img_subexa(cod_gruFk,nombre,numeroExamen,patron_normal)values(?,?,?,?)");
				    ps.setString(1, cse.getcod_gruFk());
				    ps.setString(2, cse.getnombre());
				    ps.setString(3, cse.getnumeroExamen());	
				    ps.setString(4, cse.getPatron());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
					System.out.println("Error en MetodoCrearSubExa>>InsertExaSub "+ex);
				}
			}
	  
	  public java.sql.ResultSet ObtenerDatosExamen(String codigoExa){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select patron_normal from img_subexa where codigo='"+codigoExa+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCrearSubExa>>InsertExaSub "+ex);
	        }	
	        return rs;
	    }
	  
	  public void ActualizarPatronNormal(String codigoExa, String patronNormal){
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update img_subexa set patron_normal='"+patronNormal+"' where codigo='"+codigoExa+"'");
		     	st.executeUpdate();
		     	st.close();
		     	con.cerrar();
		     	
		     }
		     catch(Exception ex){
		    	 System.out.println("Error en MetodoCrearSubExa>>ActualizarPatronNormal "+ex);
		     	ex.getMessage();
		     
		     }	
		    
		 }
	

}
