/**
 * logica: MetodoComentario se encuentran las consultas,inserciones y actualizaciones para  
 * para la insercion de los examenes y la consultas de las areas,subareas que hacen parte.
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;

import lab_bean.Comentario;
import lab_bean.CrearExamen;

public class MetodoComentario {
	
	public java.sql.ResultSet obtenerArea(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre from lab_area where codigo>0");
	        }
	        catch(Exception ex){
	        	System.out.println("ERROR EN MetodoComentario>>obtenerArea:_ "+ex);
	        }
	        return rs;
	    }

	public java.sql.ResultSet obtenerSubArea(String a){
		java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select lab_subarea.codigo,lab_subarea.nombre  from lab_subarea,lab_area where lab_area.codigo=lab_subarea.codarea_fk and lab_area.codigo='"+a+"'");
	    }       
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoComentario>>obtenerSubArea:_ "+ex);			       	
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerExamenGru(String a){
		java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	
	    	rs=st.executeQuery("select la.codigo,la.nombre,le.codigo,le.nombre from lab_area la,lab_examen le where la.codigo=le.codigoarea_fk and la.codigo='"+a+"' and le.tipo=1");
	    }   
	    
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoComentario>>obtenerExamenGru:_ "+ex);			       	
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
	    	System.out.println("ERROR EN MetodoComentario>>obtenerCodigoArea:_ "+ex);
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
	    	System.out.println("ERROR EN MetodoComentario>>obtenerCodigoSubArea:_ "+ex);
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerExamenRango(String CodSubarea){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select  la.codigo,la.nombre,ls.codigo,ls.nombre,le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion from lab_area la,lab_subarea ls,lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg,lab_tipo_rango ltr where la.codigo=ls.codarea_fk and le.codigosubarea_fk=ls.codigo and ls.codigo='"+CodSubarea+"'  and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk and le.tipo=1 and ltr.codexamen_fk=le.codigo and lr.codtiporango_fk=ltr.codigo");
	    }
	    catch(Exception ex){ 
	    	System.out.println("ERROR EN MetodoComentario>>obtenerExamenRango:_ "+ex);
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerExamenRangoArea(String Codarea){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select  la.codigo,la.nombre,ls.codigo,ls.nombre,le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion from lab_area la,lab_subarea ls,lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg where la.codigo=ls.codarea_fk and le.codigoarea_fk=la.codigo and la.codigo='"+Codarea+"' and lr.codexamen_fk=le.codigo and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk and le.tipo=1");
	    }
	    catch(Exception ex){ 
	    	System.out.println("ERROR EN MetodoComentario>>obtenerExamenRangoArea:_ "+ex);
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerCodigoExamenGru(String codsuba,String nombre){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion,lu.codigo,lg.codigo from lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg where le.nombre='"+nombre+"' and le.codigoarea_fk='"+codsuba+"' and lr.codexamen_fk=le.codigo and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk");
	    }
	    catch(Exception ex){ 
	    	System.out.println("ERROR EN MetodoComentario>>obtenerCodigoExamenGru:_ "+ex);
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerCodigoExamen(String codsuba,String nombre){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select le.codigo,le.nombre from lab_area la,lab_subarea ls,lab_examen le where la.codigo=ls.codarea_fk and ls.codigo=le.codigosubarea_fk and le.codigosubarea_fk='"+codsuba+"' and le.nombre='"+nombre+"'");
	    }
	    catch(Exception ex){ 
	    	System.out.println("ERROR EN MetodoComentario>>obtenerCodigoExamen:_ "+ex);
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerValoresExamen(String codExa){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select lr.valorinicial,lr.valorfinal,lrc.normalini,lrc.normalfin,lrc.bajoini,lrc.bajofin,lrc.altoini,lrc.altofin,lrc.bcritini,lrc.bcritfin,lrc.acritini,lrc.acritfin from lab_examen le,lab_rango lr,lab_rangocomentario lrc,lab_tipo_rango ltr where le.codigo='"+codExa+"' and le.codigo=lrc.codexamen_fk and ltr.codexamen_fk=le.codigo and lr.codtiporango_fk=ltr.codigo");
	    }
	    catch(Exception ex){ 
	    	System.out.println("ERROR EN MetodoComentario>>obtenerValoresExamen:_ "+ex);
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerDatosExamen(String codsuba,String nombre){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion,lu.codigo,lg.codigo from lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg where le.nombre='"+nombre+"' and le.codigosubarea_fk='"+codsuba+"' and lr.codexamen_fk=le.codigo and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk");
	    }
	    catch(Exception ex){ 
	    	System.out.println("ERROR EN MetodoComentario>>obtenerDatosExamen:_ "+ex);
	    }
	    return rs;
	}

	public java.sql.ResultSet obtenerCodigoExaGrupo(String codGru,String nomExa){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select le.codigo from lab_area la,lab_examen le where la.codigo=le.codigoarea_fk and le.codigoarea_fk='"+codGru+"' and le.nombre='"+nomExa+"'");
	    }
	    catch(Exception ex){ 
	    	System.out.println("ERROR EN MetodoComentario>>obtenerCodigoExaGrupo:_ "+ex);
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
	        	System.out.println("ERROR EN MetodoComentario>>insertarTipoTextoArea "+ex);
			}
		}
	
	public void insertarComentario(String normalini, String normalfin,String bajoini,String bajofin,String altoini,String altofin,String bcritini,String bcritfin,String acritini,String acritfin,String codExa){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update lab_rangocomentario set normalini='"+normalini+"',normalfin='"+normalfin+"',bajoini='"+bajoini+"',bajofin="+bajofin+",altoini="+altoini+",altofin="+altofin+",bcritini="+bcritini+",bcritfin="+bcritfin+",acritini="+acritini+",acritfin="+acritfin+" where codexamen_fk="+codExa+"");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	System.out.println("ERROR EN MetodoComentario>>insertarComentario:_ "+ex);
	        	ex.getMessage();
	        }	
	    }

}
