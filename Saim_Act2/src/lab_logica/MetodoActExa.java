/**
 * logica: lab_IngresoPac se encuentran las consultas,inserciones y actualizaciones para  
 * actualizar el examen ya sea tipo grupo o individual.
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoActExa {
	
	
	
	public void ActualizarExamenCompl(String codUni, String codGen, String valIni, String valFin, String CodigoRango){
	
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update lab_rango set lab_rango.codunidad_fk='"+codUni+"',lab_rango.codgenero_fk='"+codGen+"',lab_rango.valorinicial='"+valIni+"',lab_rango.valorfinal='"+valFin+"' where lab_rango.codigo='"+CodigoRango+"' ");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	System.out.println("ERROR EN MetodoActExa>>ActualizarExamenCompl:_ "+ex);
	        	ex.getMessage();
	        
	        }	
	       
	    }
	public void ActualizarExamenVarios(String codUni, String codGen, String valIni, String valFin, String CodigoRango){
		
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update lab_rango set lab_rango.codunidad_fk='"+codUni+"',lab_rango.valorinicial='"+valIni+"',lab_rango.valorfinal='"+valFin+"' where lab_rango.codigo='"+CodigoRango+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	System.out.println("ERROR EN MetodoActExa>>ActualizarExamenVarios:_ "+ex);
	        	ex.getMessage();
	        
	        }	
	       
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
        	System.out.println("ERROR EN MetodoActExa>>obtenerArea:_ "+ex);
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerSubArea:_ "+ex);			       	
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerExamenGru:_ "+ex);			       	
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerCodigoArea:_ "+ex);
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerCodigoSubArea:_ "+ex);
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerExamenRango:_ "+ex);
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerExamenRangoArea:_ "+ex);
    }
    return rs;
}


public java.sql.ResultSet obtenerCodigoExamenGru(String codsuba,String nombre){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion,lu.codigo,lg.codigo,lr.codigo from lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg,lab_tipo_rango ltr where le.nombre='"+nombre+"' and le.codigoarea_fk='"+codsuba+"' and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk and ltr.codexamen_fk=le.codigo and lr.codtiporango_fk=ltr.codigo");
    }
    catch(Exception ex){ 
    	System.out.println("ERROR EN MetodoActExa>>obtenerCodigoExamenGru:_ "+ex);
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerCodigoExamen:_ "+ex);
    }
    return rs;
}

public java.sql.ResultSet obtenerDatosExamenGru(String codsuba,String nombre){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion,lu.codigo,lg.codigo from lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg where le.nombre='"+nombre+"' and le.codigoarea_fk='"+codsuba+"' and lr.codexamen_fk=le.codigo and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk");
    	//System.out.println("select le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion,lu.codigo,lg.codigo from lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg where le.nombre='"+nombre+"' and le.codigoarea_fk='"+codsuba+"' and lr.codexamen_fk=le.codigo and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk");
    }
    catch(Exception ex){ 
    	System.out.println("ERROR EN MetodoActExa>>obtenerDatosExamenGru:_ "+ex);
    }
    return rs;
}


public java.sql.ResultSet obtenerDatosExamen(String codsuba,String nombre){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion,lu.codigo,lg.codigo,lr.codigo  from lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg,lab_tipo_rango ltr where le.nombre='"+nombre+"' and le.codigosubarea_fk='"+codsuba+"' and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk and ltr.codexamen_fk=le.codigo and lr.codtiporango_fk=ltr.codigo");
    	//System.out.println("select le.codigo,le.nombre,lu.nombre,lr.valorinicial,lr.valorfinal,lg.especificacion,lu.codigo,lg.codigo from lab_examen le,lab_rango lr,lab_unidad lu,lab_genero lg,lab_tipo_rango ltr where le.nombre='"+nombre+"' and le.codigosubarea_fk='"+codsuba+"' and lu.codigo=lr.codunidad_fk and lg.codigo=lr.codgenero_fk and ltr.codexamen_fk=le.codigo and lr.codtiporango_fk=ltr.codigo");
    }
    catch(Exception ex){ 
    	System.out.println("ERROR EN MetodoActExa>>obtenerDatosExamen:_ "+ex);
    }
    return rs;
}

public java.sql.ResultSet obtenerUnidad(){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,nombre from lab_unidad order by nombre");
    }
    catch(Exception ex){ 
    	System.out.println("ERROR EN MetodoActExa>>obtenerUnidad:_ "+ex);
    }
    return rs;

}

public java.sql.ResultSet obtenerNombreGenero(){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,especificacion from lab_genero where codigo > 0 order by especificacion");
    }
   
    catch(Exception ex){ 
    	System.out.println("ERROR EN MetodoActExa>>obtenerNombreGenero:_ "+ex);
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
    	System.out.println("ERROR EN MetodoActExa>>obtenerCodigoGenero:_ "+ex);
    }
    return rs;
}

public java.sql.ResultSet obtenerCodigoUnidad(String nomUni){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo from lab_unidad where nombre='"+nomUni+"'");
    }
    catch(Exception ex){ 
    	System.out.println("ERROR EN MetodoActExa>>obtenerCodigoUnidad:_ "+ex);
    }
    return rs;
}

}
