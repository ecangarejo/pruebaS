package agm_metodo;

import agm_bean.CrearEspecialidad;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoEspecialidades {

	
	
	public void ActualizarEspecialidad(String CodEspe,String NomEspeNuevo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update agm_especialidad set nombre_especialidad='"+NomEspeNuevo+"' where codigo='"+CodEspe+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoEspecialidades>>ActualizarEspecialidad "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void GuardarEspecialidadPerfil(String CodigoEspecialidad, String CodigoPerfil){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO seg_cargo_profesion(codCargo_fk,codProfesion_fk)VALUES(?,?)");
			    ps.setString(1, CodigoEspecialidad);
			    ps.setString(2, CodigoPerfil);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoEspecialidades>>GuardarEspecialidadPerfil "+ex);
			}
		}
	
	
	
	public void CrearEspecialidad(String NomEspecialidad){
		/**
		 * creamos el area tiene como parametro el nombre del area ya que el codigo es un autoincremental.
		 */
		CrearEspecialidad ce = new CrearEspecialidad();
	    ce.setNomEspecialidad(NomEspecialidad);
	    //ce.setValorEspecialidad(ValorEspecialidad);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into agm_especialidad(nombre_especialidad)values(?)");
			    ps.setString(1, ce.getNomEspecialidad());
			    //ps.setString(2, ce.getValorEspecialidad());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoEspecialidades>>CrearEspecialidad "+ex);
			}
		}
	
	
	public java.sql.ResultSet BuscarEspecialidadNombre(String NomEspecialidad){	
		/**
		 * consulta tiene como parametro el nombre del especialidad, para buscar si ya existe una con ese nombre.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from agm_especialidad where nombre_especialidad='"+NomEspecialidad+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>BuscarEspecialidadNombre "+ex);
        }	
        return rs;

    }
	
	public java.sql.ResultSet BuscarEspecialidadCodigo(String CodigoEspecialidad){	
		/**
		 * consulta tiene como parametro el codigo de la especialidad y busca una especifica.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_especialidad from agm_especialidad where codigo='"+CodigoEspecialidad+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>BuscarEspecialidadCodigo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarRelacionEspecialidadPerfil(String codEspecialidad,String codPerfil){	
		/**
		 * consulta que busca todos los RelacionEspecialidadPerfil
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM seg_cargo_profesion WHERE codCargo_fk='"+codEspecialidad+"' AND codProfesion_fk='"+codPerfil+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>BuscarRelacionEspecialidadPerfil "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPerfil(){	
		/**
		 * consulta que busca todos los perfiles
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM seg_profesion ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>BuscarEspecialidadPerfil "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarEspecialidadTodas(){	
		/**
		 * consulta que busca todas las especialidades
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_especialidad from agm_especialidad");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEspecialidades>>BuscarEspecialidadTodas "+ex);
        }	
        return rs;
    }
}
