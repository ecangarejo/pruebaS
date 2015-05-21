/**
 * logica: MetodoActuResultadoIndividual se encuentran las consultas,inserciones y actualizaciones para  
 * actualizar el resultado de los examenes individual ya sea tipo rango o tipo texto.
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoActuResultadoIndividual {
	
	
	public java.sql.ResultSet mostrarGrupo(){
		
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select codigo,nombre from lab_area where codigo>0");
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoActuResultadoIndividual>>mostrarGrupo "+ex);
	    }
	    return rs;
	}
	
	public java.sql.ResultSet mostrarExamenes(String codGrupo){
		
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select lex.codigo,lex.nombre from lab_area lar,lab_examen lex where lar.codigo=lex.codigoarea_fk and lar.codigo='"+codGrupo+"'");
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoActuResultadoIndividual>>mostrarExamenes "+ex);
	    }
	    return rs;
	}
	
public java.sql.ResultSet mostrarExamenEncontrados(String cedula,String codSubgrupo,String tipo){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lex.nombre,replace(lun.nombre,'9','%') as unidad,lran.valorinicial,lran.valorfinal,lres.resultado,lpac.pac_codigo_paciente,lres.codigo,lex.tipo,lex.codigo,lpac.segundo_apellido from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,lab_unidad lun,lab_rango lran,lab_tipo_rango ltr where lpac.numero_documento='"+cedula+"' and lpac.tipo_documento='"+tipo+"' and lex.codigo='"+codSubgrupo+"'  and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lran.codunidad_fk=lun.codigo and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigosubarea_fk=0 and ltr.codexamen_fk=lex.codigo and lran.codtiporango_fk=ltr.codigo and lres.estado=0 group by lres.fecha,lres.hora asc union select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lex.nombre,replace(lex.nombre,lex.nombre,'') as unidad,replace(lex.nombre,lex.nombre,'') as valorInicial,replace(lex.nombre,lex.nombre,'') as valorFinal,lres.resultado,lpac.pac_codigo_paciente,lres.codigo,lex.tipo,lex.codigo,lpac.segundo_apellido from adm_paciente lpac,lab_examen lex,lab_resultado lres where lpac.numero_documento='"+cedula+"'  and lex.codigo='"+codSubgrupo+"' and lex.tipo=0 and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigo=lres.codexamen_fk and lex.codigosubarea_fk=0 and lres.estado=0 group by lres.fecha,lres.hora asc");
        }
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoActuResultadoIndividual>>mostrarExamenEncontrados "+ex);
        }
        return rs;
    }

public java.sql.ResultSet verExamenes(String ced,String subarea,String fecha,String horas){
    java.sql.ResultSet rs=null;
    Statement st = null;
 
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lex.nombre,replace(lun.nombre,'9','%') as unidad,lran.valorinicial,lran.valorfinal,lres.resultado,lpac.pac_codigo_paciente,lres.codigo,lex.tipo,lpac.segundo_apellido from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,lab_unidad lun,lab_rango lran,lab_tipo_rango ltr where lres.fecha='"+fecha+"' and lres.hora='"+horas+"' and lpac.pac_codigo_paciente='"+ced+"' and lex.codigo='"+subarea+"' and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lran.codunidad_fk=lun.codigo and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigosubarea_fk=0 and ltr.codexamen_fk=lex.codigo and lran.codtiporango_fk=ltr.codigo and lres.estado=0 group by lres.fecha,lres.hora asc union select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lex.nombre,replace(lex.nombre,lex.nombre,'') as unidad,replace(lex.nombre,lex.nombre,'') as valorInicial,replace(lex.nombre,lex.nombre,'') as valorFinal,lres.resultado,lpac.pac_codigo_paciente,lres.codigo,lex.tipo,lpac.segundo_apellido from adm_paciente lpac,lab_examen lex,lab_resultado lres where lres.fecha='"+fecha+"' and lres.hora='"+horas+"' and lpac.pac_codigo_paciente='"+ced+"'  and lex.codigo='"+subarea+"' and lex.tipo=0 and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigo=lres.codexamen_fk and lex.codigosubarea_fk=0 and lres.estado=0 group by lres.fecha,lres.hora asc");
    }
    catch(Exception ex){
    	ex.getMessage();
    	System.out.println("Error MetodoActuResultadoIndividual>>verExamenes "+ex);
    }
  
    return rs;
    
}
public void ActualizarTipoRango(String codResultado, String resultado){
	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_resultado set resultado='"+codResultado+"' where codigo='"+resultado+"'");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoActuResultadoIndividual>>ActualizarTipoRango:_ "+ex);
     	ex.getMessage();
     
     }	
    
 }

public void ActualizarTipoTexto(String resultado, String codResultado){
	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_texto set descripcion='"+resultado+"' where codresultado_fk='"+codResultado+"'");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoActuResultadoIndividual>>ActualizarTipoTexto:_ "+ex);
     	ex.getMessage();
     
     }	
    
 }

}
