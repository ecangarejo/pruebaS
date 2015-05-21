/**
 * logica: MetodoAprobarExamenIndividual se encuentran las consultas,inserciones y actualizaciones para  
 * se aprueba los examenes que son tipo individual para que puedan ser vistos
 * por los usuarios finales
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoAprobarExamenIndividual {

	
	
	
public java.sql.ResultSet mostrartodoTexto(){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lex.nombre,lres.fecha,lres.hora,ltxt.descripcion,lres.codigo,lpac.segundo_apellido from adm_paciente lpac,lab_examen lex,lab_resultado lres,lab_texto ltxt where lres.estado=0 and lex.tipo=0 and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigo=lres.codexamen_fk and lres.codigo=ltxt.codresultado_fk and lex.codigosubarea_fk=0 order by lres.fecha,lres.hora  asc");
        }
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoAprobarExamenIndividual>>mostrartodoTexto "+ex);
        }
        return rs;
    }	
	
public java.sql.ResultSet mostrartodo(){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lex.nombre,REPLACE(lun.nombre,'9','%') AS unidad,lran.valorinicial,lran.valorfinal,lres.resultado,lpac.pac_codigo_paciente AS CodPac,lres.codigo AS CodResul,lres.inserccion,lpac.segundo_apellido,opl.codigo FROM adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_unidad lun,lab_tipo_rango ltr,ord_pac_lab opl WHERE lpac.pac_codigo_paciente=lres.codpac_fk AND lres.codexamen_fk=lex.codigo AND lran.codtiporango_fk=ltr.codigo AND ltr.codexamen_fk=lex.codigo AND lun.codigo=lran.codunidad_fk AND lex.codigosubarea_fk=0 AND lres.estado=0 AND lres.resultado!='' AND opl.codigo=lres.codord_fk UNION SELECT lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lex.nombre,REPLACE(lex.nombre,lex.nombre,'') AS unidad,REPLACE(lex.nombre,lex.nombre,'') AS valorInicial,REPLACE(lex.nombre,lex.nombre,'') AS valorFinal,lres.resultado,lpac.pac_codigo_paciente,lres.codigo,lres.inserccion,lpac.segundo_apellido,opl.codigo  FROM adm_paciente lpac,lab_examen lex,lab_resultado lres,ord_pac_lab opl WHERE lres.estado=0 AND lex.tipo=0 AND lpac.pac_codigo_paciente=lres.codpac_fk AND lex.codigo=lres.codexamen_fk AND lex.codigosubarea_fk=0 AND lres.resultado!='' AND opl.codigo=lres.codord_fk");
        }
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoAprobarExamenIndividual>>mostrartodo "+ex);
        }
        return rs;
    }
public void ActualizarResultado(String codigoResul,String usuario,String CodAprobacion){
	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("Update lab_resultado set estado=1,aprobaccion='"+usuario+"',CodUsuAprobacion="+CodAprobacion+" where codigo='"+codigoResul+"'");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();
     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAprobarExamenIndividual>>ActualizarResultado:_ "+ex);
     	ex.getMessage();	        
     }	
 }
public java.sql.ResultSet usuario(String Codigou){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select dato.nombre,dato.apellido from seg_datos_personales dato,seg_usuario usu where dato.dat_codigo=usu.dat_codigo_fk and usu.usu_codigo="+Codigou+" ");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAprobarExamenIndividual>>usuario "+ex);
    }
    return rs;
}

public void Omitir(String codigoResul){
	
    PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("Update lab_resultado set estado=2 where codigo='"+codigoResul+"'");
     	st.executeUpdate();
     	st.close();
     	con.cerrar();     	
     }
     catch(Exception ex){
     	System.out.println("ERROR EN MetodoAprobarExamenIndividual>>Omitir "+ex);
     	ex.getMessage();	        
     }	
 }


}
