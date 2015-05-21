package img_logica;

import java.sql.Statement;
import adm_logica.Conexion;

public class MetodoListaRadiologia {
	
	
	 public java.sql.ResultSet ObtenerExamenesAsignadosNombreTecnico(){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ice.fecha_cita,ice.hora_cita,ipac.nombre,ipac.primer_apellido,ipac.numero_documento,ice.asignar_cita,isu.nombre as ExamenesPendientes,ice.codigo,aen.nombre_entidad,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=-1 and ice.realizadopor='0' and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo desc limit 150 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoListaRadiologia>>ObtenerExamenesAsignadosNombreTecnico "+ex);
	        }	
	        return rs;
	    }
	
	//select tipo examen 
	 public java.sql.ResultSet ObtenerTiposExam(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();        	
	        	rs=st.executeQuery(" SELECT * FROM img_grupo ");
	        }
	        catch(Exception ex){
	        	System.out.print("Error en MetodoListaRadiologia>>ObtenerTiposExam "+ex);
	        	ex.getMessage();
	        }
	        return rs;
	    }
	 
	//select modalidad examen 
	 public java.sql.ResultSet ObtenerModalidadExam(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();        	
	        	rs=st.executeQuery(" SELECT sigla, nombre FROM img_modalidad ");
	        }
	        catch(Exception ex){
	        	System.out.print("Error en MetodoListaRadiologia>>ObtenerTiposExam "+ex);
	        	ex.getMessage();
	        }
	        return rs;
	    }
	 
	 
	 //busca examen por tipo
	 public java.sql.ResultSet ObtenerExamenesPorTipo(String CodExamen){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT ice.fecha_cita,ice.hora_cita,ipac.nombre,ipac.primer_apellido,ipac.numero_documento,ice.asignar_cita,isu.nombre AS ExamenesPendientes,ice.codigo,aen.nombre_entidad,ipac.segundo_apellido FROM img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen WHERE igr.codigo=isu.cod_gruFk AND isu.codigo=ice.codigoExa_fk AND ipac.pac_codigo_paciente=ice.codigoPac_fk AND ice.estado=-1 AND isu.cod_gruFk='"+CodExamen+"' AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk ORDER BY ice.codigo ASC ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoListaRadiologia>>ObtenerExamenesPorTipo "+ex);
	        }	
	        return rs;
	    }
	 
	 
	 
	 //busca examen por modalidad
	 public java.sql.ResultSet ObtenerExamenesPorModalidad(String CodModalidad){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT ice.fecha_cita,ice.hora_cita,ipac.nombre,ipac.primer_apellido,ipac.numero_documento,ice.asignar_cita,isu.nombre AS ExamenesPendientes,ice.codigo,aen.nombre_entidad,ipac.segundo_apellido FROM img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen WHERE igr.codigo=isu.cod_gruFk AND isu.codigo=ice.codigoExa_fk AND ipac.pac_codigo_paciente=ice.codigoPac_fk AND ice.estado=-1 AND isu.modalidad='"+CodModalidad+"' AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk ORDER BY ice.codigo ASC ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoListaRadiologia>>ObtenerExamenesPorTipo "+ex);
	        }	
	        return rs;
	    }
	

}
