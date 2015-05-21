/**
 * logica:MetodoRemision
 * Desarrollado:yosimar valega
 */
package adm_logica;
import java.sql.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import adm_bean.Remision;

public class MetodoRemision {
	

	public MetodoRemision(){
    	
    }
	/**
	 * obtener cen_numero_cama segun el codigo
	 */
	
	public java.sql.ResultSet obtenerCodigo(String cod){
        java.sql.ResultSet rs=null;
        PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st= con.conn.prepareStatement("select cen_numero_cama  from adm_censo_cama where codigocama =?");
        	st.setString(1, cod);
        	rs=st.executeQuery();
        }
        catch(Exception ex){
        	ex.getMessage();
        
        }//("select nombre_entidad from adm_entidad");	
        return rs;
    }
	
	/**
	 * Actualizar la cama con estado igual a 2 es decir reservar la cama
	 */
	public void Actualizar(String codcam){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=2 where codigocama ='"+codcam+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        
	        }	
	       
	}
	/**
	 * ingresar datos en la tabla preadmision
	 */
	
	
	    public void insertar(String ced,String ep, String nom, String pape,String td, String sape, String f, String hora, String numeroaut,String autori,String codcam,String estadore){
    	
    	Remision re = new Remision();
		re.setCedula(ced);
		re.setDireccion(hora);
		re.setFecha(f);
		re.setNombre(nom);
		re.setPapellido(pape);
		re.setSapellido(sape);
		re.setTipodocumento(td);
		re.setEps(ep);
		re.setNautorizacion(numeroaut);
		re.setAutorizacion(autori);
		re.setCama(codcam);
		re.setEstado(estadore);
		

	/*	String codigocama =pre.getNumeroCama();
		ResultSet rs2 = obtenerCodigo(codigocama);
	
		String codcam=null;
		if (rs2.next()){
			 codcam = rs2.getString(1);
				
		 }else{
			 codcam="";
		 }*/
		
		try{
			
			  String codigocama=re.getCama();	
			//String codigocama =pre.getNumeroCama();
			//ResultSet rs2 = obtenerCodigo(codcam);
			  ResultSet rs = (ResultSet) obtenerCodigo(codcam);
		
			String codicam=null;
			if (rs.next()){
				 codicam = rs.getString(1);
					
			 }else{
				 codicam="";
			 }
			
			
			String fechapre,ini,med,fin=null;
			ini=re.getFecha().substring(0,2);
			med=re.getFecha().substring(3,5);
			fin=re.getFecha().substring(6,10);
			fechapre=fin+"/"+med+"/"+ini;
			
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("insert into adm_preadmision(cedula,eps,nombre,apellido,tipo_documento,segundo_apellido,fecha_ingreso,hora_ingreso,numero_autorizacion,autorizado_por,codigocama,estado) values(?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,re.getCedula());
			ps.setString(2,re.getEps());
		    ps.setString(3, re.getNombre());
			ps.setString(4,re.getPapellido());
			ps.setString(5,re.getTipodocumento());
			ps.setString(6,re.getSapellido());
			ps.setString(7,fechapre);
			ps.setString(8,re.getHora());
			ps.setString(9,re.getNautorizacion());
			ps.setString(10,re.getAutorizacion());
			ps.setString(11, codicam);
			ps.setString(12, estadore);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();
			
		}catch(SQLException ex){
			ex.getMessage();
		}catch(Exception ex){
			ex.getMessage();
		}
		
		
	}
	    /**
		 * mostrar entidades existentes en la base de datos
		 */
    public java.sql.ResultSet SQLEntidad(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre_entidad from adm_entidad");
        }
        catch(Exception ex){
        	
        }
        return rs;
    }
    /**
	 * mostrar los datos de la preadmision segun el numero t tipo de identificacion
	 */
    public java.sql.ResultSet SQLEPS(String numero,String Tipo){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select eps,cedula,nombre,apellido,tipo_documento,segundo_apellido,hora_ingreso,numero_autorizacion,autorizado_por,codigocama from adm_preadmision where cedula='"+numero+"' and tipo_documento='"+Tipo+"'");
        }
        catch(Exception ex){
        	
        }
      
        return rs;
        
    }
    
    //codigo de la cama en remision
    /**
	 * mostrar codgio de cama segun el numero y estado igual a 0 existentes en la base de datos
	 */
    
    public java.sql.ResultSet codigocama(String numero){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select adm_censo_cama.codigocama from adm_censo_cama, adm_preadmision where adm_censo_cama.cen_numero_cama="+numero+" and adm_preadmision.estado=0 and adm_censo_cama.cen_numero_cama=adm_preadmision.codigocama");
        }
        catch(Exception ex){
        	
        }
      
        return rs;
        
    }
}
