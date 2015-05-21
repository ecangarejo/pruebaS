/**
 * logica: MetodoAprobarImg se encuentra las consultas,inserciones y actualizaciones
 * para la aprobacion de los examenes.
*/
package img_logica;

import img_bean.CrearCita;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
//import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;


public class MetodoAprobarImg {

	public java.sql.ResultSet ObtenerAprobadosLimite(){	    
        java.sql.ResultSet rs=null;
        PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.prepareStatement("select ipac.nombre,ipac.primer_apellido,aen.nombre_entidad,isu.codigo,isu.nombre,ice.codigo,ice.resultado,ice.fecha,ice.hora,ipac.numero_documento,ice.aprobacion,sgu.usu_codigo,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=1 and sdt.dat_codigo=sgu.dat_codigo_fk and sgu.usuario=ice.aprobacion and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo desc limit 10");
        	rs=st.executeQuery();
 
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAprobarImg>>ObtenerAprobadosLimite "+ex);
        }	
        return rs;
    }
	
	public void AprobarExaImg(String codIce, String diagnostico,String nomusu,String Diagnostico){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update img_citas_espera set resultado='"+diagnostico+"',estado=1,aprobacion='"+nomusu+"',diagnostico='"+Diagnostico+"' where codigo='"+codIce+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoAprobarImg>>AprobarExaImg "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ObtenerAprobados(){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select ipac.nombre,ipac.primer_apellido,aen.nombre_entidad,isu.codigo,isu.nombre,ice.codigo,ice.resultado,ice.fecha,ice.hora,ipac.numero_documento,ice.aprobacion,sgu.usu_codigo,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=1 and sdt.dat_codigo=sgu.dat_codigo_fk and sgu.usuario=ice.aprobacion and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo desc ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAprobarImg>>ObtenerAprobados "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerExamenesParaAprobacion(){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct ipac.nombre,ipac.primer_apellido,aen.nombre_entidad,isu.codigo,isu.nombre,ice.codigo,ice.resultado,ice.fecha,ice.hora,ipac.numero_documento,ice.insercion,sgu.usu_codigo,ice.asignar_cita,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=0 and sdt.dat_codigo=sgu.dat_codigo_fk and sgu.usuario=ice.insercion and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAprobarImg>>ObtenerExamenesParaAprobacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerExamenParaAprobar(String codIce){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select ipac.nombre,ipac.primer_apellido,isu.nombre as examen,ice.resultado,ice.fecha,ice.hora,ipac.numero_documento,ipac.genero,(year(curdate())-year(ipac.fecha_nacimiento)) - (right(curdate(),5) < right(ipac.fecha_nacimiento, 5)) as edad,ipac.fecha_nacimiento,igr.nombre as grupo,ice.datosClinicos,ice.diagnostico,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=0 and ice.codigo="+codIce+"");
        	
        	}
        catch(Exception ex){
        	System.out.println("Error en MetodoAprobarImg>>VerExamenParaAprobar "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerPacientesPendientes(){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select ice.fecha_cita,ice.hora_cita,ipac.nombre,ipac.primer_apellido,ipac.numero_documento,ice.asignar_cita,isu.nombre as ExamenesPendientes,ice.codigo,aen.nombre_entidad,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=-1 and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo asc ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAprobarImg>>ObtenerPacientesPendientes "+ex);
        }	
        return rs;
    }
	
	public void ActualizarDiagnostico(String Diagnostico, String codIce,String descripcion){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update img_citas_espera set diagnostico='"+Diagnostico+"',resultado='"+descripcion+"' where codigo='"+codIce+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAprobarImg>>ActualizarDiagnostico "+ex);
	        	ex.getMessage();
	        }	
	    }
	public void omitirExamen(String codigo,String Accion){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update img_citas_espera set estado=2,realizadopor='"+Accion+"' where codigo='"+codigo+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAprobarImg>>omitirExamen "+ex);
	        	ex.getMessage();
	        }	
	    }
	
	public void RevisarExamen(String codigo,String NomUsu){
		       PreparedStatement st = null;
		        try{
		        	Conexion con=new Conexion();
		        	st= con.conn.prepareStatement("UPDATE img_citas_espera SET realizadopor='"+NomUsu+"' WHERE codigo='"+codigo+"'");
		        	st.executeUpdate();
		        	st.close();
		        	con.cerrar();
		        }
		        catch(Exception ex){
		        	System.out.println("Error en MetodoAprobarImg>>RevisarExamen "+ex);
		        	ex.getMessage();
		        }	
		    }
	
	 public void InsertarRevisionHistorialImagenes(String codigo,String CodUsu,Date Fecha,Time Hora){
	
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO img_seguimiento_imagen(cod_ice_fk,cod_usu_fk,fecha,hora)VALUES(?,?,?,?)");
				    ps.setString(1, codigo);
				    ps.setString(2, CodUsu);
				    ps.setDate(3, Fecha);
				    ps.setTime(4, Hora);    
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
					System.out.println("Error en MetodoCitasExamen>>InsertarRevisionHistorialImagenes "+ex);
				}
	 }
	
	}
