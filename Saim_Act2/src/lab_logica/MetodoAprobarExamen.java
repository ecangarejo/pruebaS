/**
 * logica: MetodoAprobarExamen se encuentran las consultas,inserciones y actualizaciones para  
 * se aprueba los examenes que son tipo grupo para que puedan ser vistos
 * por los usuarios finales
*/
package lab_logica;

import java.sql.PreparedStatement;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoAprobarExamen {
	
	
	
public java.sql.ResultSet mostrartodo(){
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("select distinct lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,lex.nombre,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.inserccion,lpac.segundo_apellido,lres.codord_fk  from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,ord_pac_lab opl where lres.resultado!='' and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lres.estado=0 and lex.codigosubarea_fk!=0 and lres.codord_fk=opl.codigo  group by lres.fecha, lres.hora asc");
        	rs=st.executeQuery("SELECT lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,'',lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.inserccion,lpac.segundo_apellido,lres.codord_fk  FROM ord_pac_lab opl ,lab_resultado lres,lab_subarea lsub, adm_paciente lpac WHERE opl.codigo=lres.codord_fk AND lsub.codigo=opl.codigo_estudio AND lpac.pac_codigo_paciente=opl.codigo_pac_fk AND lres.estado = 0 AND lres.resultado!='' GROUP BY opl.codigo ORDER BY lres.fecha DESC");
        	System.out.println("SELECT lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,'',lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.inserccion,lpac.segundo_apellido,lres.codord_fk  FROM ord_pac_lab opl ,lab_resultado lres,lab_subarea lsub, adm_paciente lpac WHERE opl.codigo=lres.codord_fk AND lsub.codigo=opl.codigo_estudio AND lpac.pac_codigo_paciente=opl.codigo_pac_fk AND lres.estado = 0 AND lres.resultado!='' GROUP BY opl.codigo ORDER BY lres.fecha DESC");
        }
        catch(Exception ex){
        	System.out.println("ERROR EN MetodoAprobarExamen>>mostrartodo "+ex);
        }
        return rs;
    }
public java.sql.ResultSet mostrartodotexto(){
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("select lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre,lex.nombre,replace(lex.nombre,lex.nombre,'') as unidad,replace(lex.nombre,lex.nombre,'') as valorinicial,replace(lex.nombre,lex.nombre,'') as valorfinal,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.inserccion,lpac.segundo_apellido from adm_paciente lpac,lab_examen lex,lab_resultado lres,lab_subarea lsub,lab_area lare where lres.resultado!='' and lres.estado=0 and lpac.pac_codigo_paciente=lres.codpac_fk and lex.codigo=lres.codexamen_fk and lare.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lex.codigosubarea_fk=lsub.codigo and lex.codigosubarea_fk!=0 and lex.codigoarea_fk=0 and lex.tipo=0 group by lres.fecha, lres.hora asc");
    }
    catch(Exception ex){
    	System.out.println("ERROR EN MetodoAprobarExamen>>mostrartodotexto "+ex);
    }
    return rs;
}

	
	 public java.sql.ResultSet verExamenes(String ced,String subarea,String fecha,String horas){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT lres.fecha,lres.hora,lsub.nombre,lex.nombre,REPLACE(lun.nombre,'9','%') AS unidad,lran.valorinicial,lran.valorfinal,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.codigo,opl.codigo FROM adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,lab_unidad lun,lab_rango lran,lab_tipo_rango ltr,ord_pac_lab opl WHERE lsub.codigo='"+subarea+"' AND lex.codigosubarea_fk=lsub.codigo AND lran.codunidad_fk=lun.codigo AND lres.codexamen_fk=lex.codigo AND lres.resultado!='' AND lpac.pac_codigo_paciente='"+ced+"' AND lpac.pac_codigo_paciente=lres.codpac_fk AND lres.estado=0 AND lres.fecha='"+fecha+"' AND lres.hora='"+horas+"' AND ltr.codexamen_fk=lex.codigo AND lran.codtiporango_fk=ltr.codigo AND opl.codigo=lres.codord_fk AND ((lran.edadInicial<=22 AND  lran.edadFinal>=22)   XOR  lran.codgenero_fk=2 XOR ltr.tiporango='GENERAL') UNION SELECT lres.fecha,lres.hora,lsub.nombre,lex.nombre,REPLACE(lex.nombre,lex.nombre,'') AS unidad,REPLACE(lex.nombre,lex.nombre,'') AS valorInicial,REPLACE(lex.nombre,lex.nombre,'') AS valorFinal,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo,lres.codigo,opl.codigo FROM adm_paciente lpac,lab_examen lex,lab_resultado lres,lab_subarea lsub,ord_pac_lab opl WHERE lres.estado=0 AND lex.tipo=0 AND lpac.pac_codigo_paciente=lres.codpac_fk AND lex.codigo=lres.codexamen_fk  AND lres.fecha='"+fecha+"' AND lres.hora='"+horas+"' AND lsub.codigo='"+subarea+"' AND lex.codigosubarea_fk=lsub.codigo AND lres.resultado!='' AND opl.codigo=lres.codord_fk");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoAprobarExamen>>verExamenes "+ex);
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
	        	System.out.println("ERROR EN MetodoAprobarExamen>>ActualizarResultado:_ "+ex);
	        	ex.getMessage();	        
	        }	
	        
	    }
	 
	 public void OmitirResultado(String codigoResul){
			
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update lab_resultado set estado=2 where codigo='"+codigoResul+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("ERROR EN MetodoAprobarExamen>>OmitirResultado:_ "+ex);
	        	ex.getMessage();	        
	        }	
	        
	    }
}
