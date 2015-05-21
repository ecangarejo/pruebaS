/**
 * logica: MetodoCitasExamen se encuentra las consultas,inserciones y actualizaciones
 * para asignacion de las estudios a los pacientes.
*/
package img_logica;
import img_bean.CrearCita;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;


public class MetodoCitasExamen {
	
	public ResultSet listar(String cod) throws Exception {
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select distinct nombre,codigo from img_subexa where nombre like '"+cod+"%'order by nombre ");
	        	return r;
	    }
	
	public java.sql.ResultSet ObtenerArea(){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre from img_grupo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCitasExamen>>ObtenerArea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerSuArea(String codGrupo){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select isu.codigo,isu.nombre,isu.patron_normal from img_grupo igr,img_subexa isu where igr.codigo=isu.cod_gruFk and igr.codigo='"+codGrupo+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCitasExamen>>ObtenerSuArea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoPaciente(String cedula,String tipodoc){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select pac_codigo_paciente,nombre,primer_apellido,segundo_apellido from adm_paciente where numero_documento='"+cedula+"' and tipo_documento='"+tipodoc+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCitasExamen>>ObtenerCodigoPaciente "+ex);
        }	
        return rs;
    }
	
	 public void AsignarAdmisionAmbulatoria(String registrado_por,Date fecha_registro,Time hora_registro,String pac_codigo_paciente_fk,String tipo){
				 		 
			try{
					PreparedStatement ps1 = null;
				    Conexion con1=new Conexion();
				    ps1=con1.conn.prepareStatement("INSERT INTO adm_ambulatorio(registrado_por,fecha_registro,hora_registro,pac_codigo_paciente_fk,tipo)VALUES(?,?,?,?,?)");
				    ps1.setString(1, registrado_por);
				    ps1.setDate(2, fecha_registro);
				    ps1.setTime(3, hora_registro);
				    ps1.setString(4, pac_codigo_paciente_fk);
				    ps1.setString(5, tipo);
				    ps1.executeUpdate();
					ps1.close();
					con1.cerrar();				
				}catch(Exception ex){
					System.out.println("Error en MetodoCitasExamen>>AsignarAdmisionAmbulatoria "+ex);
				}
	 }
	 
	 public java.sql.ResultSet ObtenerCodigoAmbulatorio(Date fecha_registro,Time hora_registro){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM adm_ambulatorio aab WHERE aab.fecha_registro='"+fecha_registro+"' AND aab.hora_registro='"+hora_registro+"' ");
	        	//System.out.println("SELECT * FROM adm_ambulatorio aab WHERE aab.fecha_registro='"+fecha_registro+"' AND aab.hora_registro='"+hora_registro+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>ObtenerCodigoAmbulatorio "+ex);
	        }	
	        return rs;
	    }
	 
	 public ResultSet ConsultaEncabezado(String CodPac){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	      //  PreparedStatement psc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("SELECT aent.ent_nit,aent.nombre_entidad,aent.ent_nit_contratante, "+
										"aent.direccion,aent.telefono,aent.ciudad,"+
										"CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS NombrePAciente, "+
										"CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Identificacion,apac.direccion AS DirPac, "+
										"CONCAT(apac.telefono_celular,' ',apac.telefono_fijo,' ',apac.telefono_oficina ) AS TelPac, "+
										"apac.tipo_afiliacion, apac.estrato,aad.fecha_registro,'-',fcv.poliza  "+
										"FROM adm_paciente apac,adm_ambulatorio aad,adm_entidad aent,adm_convenio acv,fact_convenios fcv  "+
										"WHERE apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND fcv.cod_entidad=aent.ent_nit "+
										"AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato "+
										"AND acv.ent_nit_contratante_fk=aent.ent_nit AND aad.estado=0 AND apac.pac_codigo_paciente="+CodPac+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>ConsultaEncabezado "+ex);
	        }
			return rsc;	
	 }
	
	
	 public void AsignarCitas(String codigoPac_fk,String codigoExa_fk,String estado,String usuario,Time hora,Date fecha,String datosClinico,String portatil,String cedula){
		 CrearCita cc=new CrearCita();
		 cc.setCodigoPac_fk(codigoPac_fk);
		 cc.setCodigoExa_fk(codigoExa_fk);
		 cc.setEstado(estado);	
		 cc.setinsercion(usuario);
		// cc.setHoraCita(hora);
		 //cc.setFechaCita(fecha);
		 cc.setdatosClinicos(datosClinico);
		 cc.setportatil(portatil);
		 cc.setcedula(cedula);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into img_citas_espera(codigoPac_fk,codigoExa_fk,estado,asignar_cita,fecha_cita,hora_cita,datosClinicos,portatil,cedpac_fk)values(?,?,?,?,?,?,?,?,?)");
				    ps.setString(1, cc.getCodigoPac_fk());
				    ps.setString(2, cc.getCodigoExa_fk());
				    ps.setString(3, cc.getEstado());
				    ps.setString(4, cc.getinsercion());
				    ps.setDate(5, fecha);
				    ps.setTime(6, hora);
				    ps.setString(7, cc.getdatosClinicos());
				    ps.setString(8, cc.getportatil());
				    ps.setString(9, cc.getcedula());				    
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
					System.out.println("Error en MetodoCitasExamen>>AsignarCitas "+ex);
				}
	 }
	 
	 public java.sql.ResultSet ObtenerExamenesAsignados(String cedula,String codigoIce){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ipac.nombre,ipac.primer_apellido,aen.nombre_entidad,isu.codigo,replace(isu.nombre,'@','Ñ'),ice.codigo,isu.patron_normal,ice.datosClinicos,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ipac.pac_codigo_paciente='"+cedula+"' and ice.estado=-1 and ice.codigo='"+codigoIce+"' and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>ObtenerExamenesAsignados "+ex);
	        }	
	        return rs;
	    }
	 ///////////////////////////
	 public java.sql.ResultSet ObtenerExamenesAsignadosPorEstudio(String CodExamen){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ice.fecha_cita,ice.hora_cita,ipac.nombre,ipac.primer_apellido,ipac.numero_documento,ice.asignar_cita,isu.nombre as ExamenesPendientes,ice.codigo,aen.nombre_entidad,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=-1 and isu.codigo='"+CodExamen+"' and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo asc ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>ObtenerExamenesAsignadosPorEstudio "+ex);
	        }	
	        return rs;
	    }
	 //////////////////////////
	 public java.sql.ResultSet ObtenerExamenesAsignadosNombreTecnico(){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ice.fecha_cita,ice.hora_cita,ipac.nombre,ipac.primer_apellido,ipac.numero_documento,ice.asignar_cita,isu.nombre as ExamenesPendientes,ice.codigo,aen.nombre_entidad,ipac.segundo_apellido from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=-1 and ice.realizadopor='0' and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo desc limit 150 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>ObtenerExamenesAsignadosNombreTecnico "+ex);
	        }	
	        return rs;
	    }
	 //////////////////////////
	 public java.sql.ResultSet ObtenerExamenesAsignadosNombre(String cedula,String tipodoc){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ice.fecha_cita,ice.hora_cita,ipac.nombre,ipac.primer_apellido,ipac.numero_documento,ice.asignar_cita,isu.nombre as ExamenesPendientes,ice.codigo,aen.nombre_entidad,ipac.segundo_apellido,ipac.pac_codigo_paciente from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=-1 and ipac.numero_documento='"+cedula+"' and ipac.tipo_documento='"+tipodoc+"' and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.codigo asc");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>ObtenerExamenesAsignadosNombre "+ex);
	        }	
	        return rs;
	    }
	 //////////////////////////
	 public java.sql.ResultSet ObtenerInsercionPaciente(String cedula){	 
		 /**esta consulta se utiliza para ver quien inserto el paciente en img_paciente(no valido en la union de los proyectos)*/
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ipac.nombre,ipac.apellidos,ipac.genero,ipac.cedula,ipac.eps,sdt.nombre,sdt.apellido,sdt.cedula,ipac.fecha,ipac.hora from img_paciente ipac,seg_usuario sus,seg_datos_personales sdt where ipac.usuario=sus.usu_codigo and sdt.dat_codigo=sus.dat_codigo_fk and ipac.cedula='"+cedula+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>ObtenerArea "+ex);
	        }	
	        return rs;
	    }
	 
	 
	 
	 public void ActualizarCitas(String resultado, String codCita,String hora,String fecha,String usuario,String Diagnostico){
			
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update img_citas_espera set fecha='"+fecha+"',hora='"+hora+"',estado=0,resultado='"+resultado+"',insercion='"+usuario+"',diagnostico='"+Diagnostico+"' where codigo='"+codCita+"'");
		     	st.executeUpdate();
		     	st.close();
		     	con.cerrar();
		     	
		     }
		     catch(Exception ex){
		    	 System.out.println("Error en MetodoCitasExamen>>ActualizarCitas "+ex);
		     	ex.getMessage();
		     
		     }	
		    
		 }
	 
	 public java.sql.ResultSet Obtenerusuario(String usuario){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select usuario from seg_usuario where usu_codigo='"+usuario+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>Obtenerusuario "+ex);
	        }	
	        return rs;
	    }
	 
	 
	 public java.sql.ResultSet AdmisionActiva(String codigoPac_fk){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM adm_admisiones aad WHERE aad.pac_codigo_paciente_fk="+codigoPac_fk+"  AND aad.estado=0 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>AdmisionActiva "+ex);
	        }	
	        return rs;
	    }
	 
	 public java.sql.ResultSet AdmisionAmbulatoriaActiva(String codigoPac_fk){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT * FROM adm_ambulatorio aab WHERE aab.pac_codigo_paciente_fk="+codigoPac_fk+" AND aab.estado=0");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoCitasExamen>>AdmisionAmbulatoriaActiva "+ex);
	        }	
	        return rs;
	    }
	 

}
