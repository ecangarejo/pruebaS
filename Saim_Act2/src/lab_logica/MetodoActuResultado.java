/**
 * logica: MetodoActuResultado se encuentran las consultas,inserciones y actualizaciones para  
 * actualizar el resultado de los examenes en grupo ya sea tipo rango o tipo texto.
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoActuResultado {
	
	
public java.sql.ResultSet mostrarExamen(String cedula,String codSubgrupo,String tipo){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre, lex.nombre,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub where lpac.numero_documento="+cedula+" and lpac.tipo_documento='"+tipo+"' and lsub.codigo="+codSubgrupo+" and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigosubarea_fk!=0 and lres.estado=0 group by lres.fecha ,lres.hora asc");

        }
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoActuResultado>>mostrarExamen "+ex);
        }
        return rs;
    }

public java.sql.ResultSet mostrarGrupo(){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select codigo,nombre from lab_area where codigo>0");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoActuResultado>>mostrarGrupo "+ex);
    }
    return rs;
}

public java.sql.ResultSet mostrarSubGrupo(String codGrupo){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select lsub.codigo,lsub.nombre from lab_area lar,lab_subarea lsub where lsub.codarea_fk='"+codGrupo+"' and lar.codigo=lsub.codarea_fk");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoActuResultado>>mostrarSubGrupo "+ex);
    }
    return rs;
}

public java.sql.ResultSet verExamenes(String ced,String subarea,String fecha,String horas){
    java.sql.ResultSet rs=null;
    Statement st = null;
 
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select lres.fecha,lres.hora,lsub.nombre, lex.nombre,replace(lun.nombre,'9','%') as unidad,lran.valorinicial,lran.valorfinal,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.codigo,lex.tipo from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,lab_unidad lun,lab_rango lran,lab_tipo_rango ltr where lsub.codigo='"+subarea+"' and lex.codigosubarea_fk=lsub.codigo and lres.estado=0 and lran.codunidad_fk=lun.codigo and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente='"+ced+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.fecha='"+fecha+"' and lres.hora='"+horas+"' and ltr.codexamen_fk=lex.codigo and lran.codtiporango_fk=ltr.codigo and ((lran.edadInicial<=22 and  lran.edadFinal>=22) XOR  lran.codgenero_fk=2 xor ltr.tiporango='GENERAL') union select lres.fecha,lres.hora,lsub.nombre,lex.nombre,replace(lex.nombre,lex.nombre,'') as unidad,replace(lex.nombre,lex.nombre,'') as valorInicial,replace(lex.nombre,lex.nombre,'') as valorFinal,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.codigo,lex.tipo from adm_paciente lpac,lab_examen lex,lab_resultado lres,lab_subarea lsub where lres.estado=0 and lex.tipo=0 and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigo=lres.codexamen_fk and lres.fecha='"+fecha+"' and lres.hora='"+horas+"' and lsub.codigo='"+subarea+"' and lex.codigosubarea_fk=lsub.codigo");
    }
    catch(Exception ex){
    	ex.getMessage();
    	System.out.println("Error MetodoActuResultado>>verExamenes "+ex);
    }
  
    return rs;
    
}

public void ActualizarTipoRango(String resultado, String codResultado){
	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_resultado set resultado='"+resultado+"' where codigo='"+codResultado+"'");
	
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoActuResultado>>ActualizarTipoRango:_ "+ex);
     	ex.getMessage();
     
     }	
    
 }

public void ActualizarTipoTexto(String codResultado, String resultado){
	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("update lab_texto set descripcion='"+codResultado+"' where codresultado_fk='"+resultado+"'");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoActuResultado>>ActualizarTipoTexto:_ "+ex);
     	ex.getMessage();
     
     }	
    
 }


}
