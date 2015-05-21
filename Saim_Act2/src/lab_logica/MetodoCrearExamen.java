/**
 * logica: MetodoCrearExamen se encuentran las consultas,inserciones y actualizaciones para  
 * para la creacion de los examenes y la consultas de las areas,subareas, unidades etc.
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

import lab_bean.Comentario;
import lab_bean.CrearExamen;
import lab_bean.CrearRango;
import lab_bean.tiporango;

public class MetodoCrearExamen {
	
	public MetodoCrearExamen(){}
	
	
public java.sql.ResultSet obtenerCodigoExamenTexto(String nomb,String tipo, String codAex){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from lab_examen where nombre='"+nomb+"' and tipo='"+tipo+"' and codigoarea_fk='"+codAex+"' ");
        }
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerCodigoExamenTexto "+ex);
        }
        return rs;
    }

	public java.sql.ResultSet obtenerArea(){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre from lab_area where codigo>0");
        }
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerArea "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerUnidad(){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre from lab_unidad");
        }
        catch(Exception ex){ 
        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerUnidad "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerCodigoUnidad(String nom){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from lab_unidad where nombre='"+nom+"'");
        }
        catch(Exception ex){ 
        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerCodigoUnidad "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerCodigoArea(String nomArea){
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from lab_area where nombre='"+nomArea+"'");
        }
        catch(Exception ex){ 
        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerCodigoArea "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerCodigoSubArea(String nomsub,String codarea){
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct lab_subarea.codigo from lab_subarea where lab_subarea.nombre='"+nomsub+"' and lab_subarea.codarea_fk='"+codarea+"'");
        }
        catch(Exception ex){ 
        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerCodigoSubArea "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerSubArea(String a){
		
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lab_subarea.codigo,lab_subarea.nombre  from lab_subarea,lab_area where lab_area.codigo=lab_subarea.codarea_fk and lab_area.nombre='"+a+"'");
        }       
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerSubArea "+ex);			       	
        }
        return rs;
    }
	
	 public void insertarTipoTextoArea(String nomb, String codAex,String codSex,String tipo){
	    CrearExamen ce = new CrearExamen();
		 ce.setNombre(nomb);
		 ce.setCodAreaFk(codAex);
		 ce.setCodSubaFk(codSex);
		 ce.setTipo(tipo);	 
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into lab_examen(nombre,tipo,codigosubarea_fk,codigoarea_fk)values(?,?,?,?)");
			    ps.setString(1, ce.getNombre());
			    ps.setString(2, ce.getTipo());
			    ps.setString(3, ce.getCodSubaFk());
			    ps.setString(4, ce.getCodAreaFk());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearExamen>>insertarTipoTextoArea "+ex);
			}
		}
	 
	 public void insertipo(String tip, String codexa){
		   tiporango ti = new tiporango();
			 ti.setTipo(tip);
			 ti.setCodExamen(codexa);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into lab_tipo_rango(tiporango,codexamen_fk)values(?,?)");
				    ps.setString(1, ti.getTipo());
				    ps.setString(2, ti.getCodExamen());
				  
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearExamen>>insertipo "+ex);
				}
			}
	 
	 public void insertarTipoRangoArea(String valIni,String valFin,String codUni,String codGen,String codCom,String codExa,String edaIni,String edaFin){
		 CrearRango cr=new CrearRango();
		 cr.setvalIni(valIni);
		 cr.setvalFin(valFin);
		 cr.setcodUni(codUni);
		 cr.setcodGen(codGen);
		 cr.setcodCom(codCom);
		 cr.setcodExa(codExa);
		 cr.setedaFin(edaFin);
		 cr.setedaIni(edaIni);
		 try{
			 PreparedStatement ps=null;
			 Conexion con=new Conexion();
			 ps=con.conn.prepareStatement("insert into lab_rango(valorinicial,valorfinal,codunidad_fk,codgenero_fk,codcomentario_fk,codtiporango_fk,edadFinal,edadInicial)values(?,?,?,?,?,?,?,?)");
			 
			 ps.setString(1, cr.getvalIni());
			 ps.setString(2, cr.getvalFin());
			 ps.setString(3, cr.getcodUni());
			 ps.setString(4, cr.getcodGen());
			 ps.setString(5, cr.getcodCom());
			 ps.setString(6, cr.getcodExa());
			 ps.setString(7, cr.getedaFin());
			 ps.setString(8, cr.getedaIni());
			 
			 ps.executeUpdate();
			 ps.close();
			 con.cerrar();
		//	 System.out.println("Ingreso Exitoso...!!!");
		 }catch(Exception ex){
				System.out.println("ERROR EN MetodoCrearExamen>>insertarTipoRangoArea "+ex);	 
		 }
	 }
	 
	 public java.sql.ResultSet obtenertipo(String nomb){
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo from lab_tipo_rango where codexamen_fk="+nomb+"");
	        }
	        catch(Exception ex){ 
	        	System.out.println("ERROR EN MetodoCrearExamen>>obtenertipo "+ex);
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet obtenerCodigoExamenArea(String nomb,String codAex){
		
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo from lab_examen where nombre='"+nomb+"' and codigoarea_fk='"+codAex+"'");
	        }
	        catch(Exception ex){ 
	        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerCodigoExamenArea "+ex);
	        }
	        return rs;
	    }
	
	 
	 public java.sql.ResultSet obtenerNombreGenero(){
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo,especificacion from lab_genero where codigo > 0");
	        }
	       
	        catch(Exception ex){ 
	        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerNombreGenero "+ex);
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet obtenerCodigoGenero(String nomGen){
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo from lab_genero where especificacion='"+nomGen+"'");
	        }
	        catch(Exception ex){ 
	        	System.out.println("ERROR EN MetodoCrearExamen>>obtenerCodigoGenero "+ex);
	        }
	        return rs;
	    }
	 
	 public void insertarComentario(String normalini, String normalfin,String codExamen_fk){
		    //CrearExamen ce = new CrearExamen();
	//	 System.out.println("entro a insertarComentario");
		 Comentario com = new Comentario();
		 com.setnormalfin(normalfin);
		 com.setnormalini(normalini);
		 com.setcodExamen_fk(codExamen_fk);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into lab_rangocomentario(normalini,normalfin,codexamen_fk) values (?,?,?)");
				    ps.setString(1, com.getnormalini());
				    ps.setString(2, com.getnormalfin());
				    ps.setString(3, com.getcodExamen_fk());
				    //ps.setString(4, ce.getCodAreaFk());
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoCrearExamen>>insertarComentario "+ex);
				}
			}

}
