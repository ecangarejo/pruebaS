package img_logica;

import img_bean.CrearCita;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoAuxClinico {
	
	
	 public java.sql.ResultSet ObtenerExamenesAsignadosNombreTecnico(){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT ice.fecha,ice.hora, CONCAT(ipac.nombre,' ',ipac.primer_apellido,' ',ipac.segundo_apellido)AS NombrePaciente, CONCAT(ipac.tipo_documento,'-',ipac.numero_documento)AS documento,isu.nombre AS ExamenesPendientes,CONCAT(c.pabellon,'>',c.ubicacion_cama,'>',c.codigocama) AS Ubicacion, serv.servicio,ice.codigo,aen.nombre_entidad FROM  adm_censo_cama c, adm_admisiones a,img_grupo igr,img_traslado ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen, img_servicio_traslado serv WHERE igr.codigo=isu.cod_gruFk AND isu.codigo=ice.codigoExa_fk AND ice.estado=0 AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk AND c.cen_numero_cama = a.cen_numero_cama_fk AND a.estado='0' AND ice.codigoPac_fk=ipac.pac_codigo_paciente AND a.pac_codigo_paciente_fk = ipac.pac_codigo_paciente AND serv.codigo=isu.cod_servicio ORDER BY ice.codigo DESC");
	        	//System.out.println("ingreso a ObtenerExamenesAsignadosNombreTecnico");
	        	
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAuxClinico>>ObtenerExamenesAsignadosNombreTecnico "+ex);
	        }	
	        return rs;
	    }
	 
	 public java.sql.ResultSet ObtenerUbicacionPac(String codTraslado){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT CONCAT(c.pabellon,'>',c.ubicacion_cama,'>',c.codigocama) AS Ubicacion, serv.servicio,ice.codigo FROM  adm_censo_cama c, adm_admisiones a,img_grupo igr,img_traslado ice,adm_paciente ipac,img_subexa isu,adm_convenio acv,adm_entidad aen, img_servicio_traslado serv WHERE igr.codigo=isu.cod_gruFk AND isu.codigo=ice.codigoExa_fk AND ice.estado=0 AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk AND c.cen_numero_cama = a.cen_numero_cama_fk AND a.estado='0' AND ice.codigoPac_fk=ipac.pac_codigo_paciente AND a.pac_codigo_paciente_fk = ipac.pac_codigo_paciente AND serv.codigo=isu.cod_servicio AND ice.codigo='"+codTraslado+"' ORDER BY ice.codigo DESC ");
	        	//System.out.println("ingreso a ObtenerExamenesAsignadosNombreTecnico");
	        	
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAuxClinico>>ObtenerUbicacionPac "+ex);
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
	        	System.out.print("Error en MetodoAuxClinico>>ObtenerTiposExam "+ex);
	        	ex.getMessage();
	        }
	        return rs;
	    }
	 
	 
	 
	 public void insertarTraslado(String codigoPac_fk,String codigoExa_fk,String estado,Time hora,Date fecha){
		 
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into img_traslado(codigoPac_fk,codigoExa_fk,fecha,hora,estado)values(?,?,?,?,?)");
				    ps.setString(1, codigoPac_fk);
				    ps.setString(2, codigoExa_fk);
				    ps.setDate(3, fecha);
				    ps.setTime(4, hora);
				    ps.setString(5, estado);				    
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
					System.out.println("Error en MetodoAuxClinico>>InsertarTraslado "+ex);
				}
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
	        	System.out.print("Error en MetodoAuxClinico>>ObtenerModalidadExam "+ex);
	        	ex.getMessage();
	        }
	        return rs;
	    }
	 
	 
	//select auxiliares clinicos
	 public java.sql.ResultSet ObtenerAuxiliares(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();        	
	        	rs=st.executeQuery(" SELECT usu.usu_codigo, CONCAT(nombre,' ',apellido) AS Nombre FROM seg_datos_personales seg,  seg_usuario usu WHERE profesion LIKE '%AUX. CLINICO%' AND usu.dat_codigo_fk=seg.dat_codigo ");
	        }
	        catch(Exception ex){
	        	System.out.print("Error en MetodoAuxClinico>>ObtenerAuxiliares "+ex);
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
	        	System.out.println("Error en MetodoAuxClinico>>ObtenerExamenesPorTipo "+ex);
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
	        	System.out.println("Error en MetodoAuxClinico>>ObtenerExamenesPorModalidad "+ex);
	        }	
	        return rs;
	    }
	
	
	 //busca traslados realizado por usuario
	 public java.sql.ResultSet ObtenerTraslados(String CodUsu){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT CONCAT(tras.fecha,'/',tras.hora) AS fechaSolicitud, CONCAT(tras.fecha_traslado,'/',tras.hora_traslado) AS fechaTraslado FROM img_traslado tras, seg_usuario usu WHERE usu.dat_codigo_fk='"+CodUsu+"' AND tras.usuarioTraslado= usu.usu_codigo AND tras.estado='1' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAuxClinico>>ObtenerExamenesPorModalidad "+ex);
	        }	
	        return rs;
	    }
	 
	 
	//busca traslados realizado por usuario
	 public java.sql.ResultSet ObtenerTrasladosUsuariosFecha(String CodUsu, String fechai, String fechaf){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT CONCAT(tras.fecha,'/',tras.hora) AS fechaSolicitud,CONCAT(tras.fecha_traslado,'/',tras.hora_traslado) AS fechaTraslado, CONCAT(ipac.nombre,' ',ipac.primer_apellido,' ',ipac.segundo_apellido)AS NombrePaciente, CONCAT(ipac.tipo_documento,'-',ipac.numero_documento)AS documento,isu.nombre AS ExamenesPendientes,tras.ubicacion, tras.serv_trasladado, usu.usuario FROM img_traslado tras,adm_paciente ipac,img_subexa isu, seg_usuario usu WHERE  tras.estado='1'  AND ipac.pac_codigo_paciente=tras.codigoPac_fk AND isu.codigo=tras.codigoExa_fk AND tras.usuarioTraslado='"+CodUsu+"' AND usu.usu_codigo = tras.usuarioTraslado AND tras.fecha_traslado BETWEEN '"+fechai+"' AND '"+fechaf+"'    ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAuxClinico>>ObtenerTrasladosUsuariosFecha "+ex);
	        }	
	        return rs;
	    }
	
	 public java.sql.ResultSet ObtenerTrasladosFecha( String fechai, String fechaf){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT CONCAT(tras.fecha,'/',tras.hora) AS fechaSolicitud,CONCAT(tras.fecha_traslado,'/',tras.hora_traslado) AS fechaTraslado, CONCAT(ipac.nombre,' ',ipac.primer_apellido,' ',ipac.segundo_apellido)AS NombrePaciente, CONCAT(ipac.tipo_documento,'-',ipac.numero_documento)AS documento,isu.nombre AS ExamenesPendientes, tras.ubicacion, tras.serv_trasladado, usu.usuario FROM img_traslado tras,adm_paciente ipac,img_subexa isu, seg_usuario usu WHERE  tras.estado='1'  AND ipac.pac_codigo_paciente=tras.codigoPac_fk AND isu.codigo=tras.codigoExa_fk AND usu.usu_codigo = tras.usuarioTraslado AND tras.fecha_traslado BETWEEN '"+fechai+"' AND '"+fechaf+"'    ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAuxClinico>>ObtenerTrasladosUsuariosFecha "+ex);
	        }	
	        return rs;
	    }
	
	 public boolean trasladar(
			    String cod_traslado, 
				String cod_usuario,	
			    String fecha,
				String hora, 
				String estado,
				String servicio,
				String trasladado
				
				) {

		 PreparedStatement st = null;
			try {
				Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("UPDATE img_traslado SET usuarioTraslado='"+cod_usuario+"', fecha_traslado='"+fecha+"'  " +", hora_traslado='"+hora+"' "+", estado='"+estado+"' "+", ubicacion='"+servicio+"' "+", serv_trasladado='"+trasladado+"'  WHERE codigo ='"+cod_traslado+"' ");
		     	
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	
	
	
	
	
	
	

}
