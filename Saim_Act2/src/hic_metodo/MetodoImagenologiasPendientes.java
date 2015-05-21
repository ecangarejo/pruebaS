package hic_metodo;

import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoImagenologiasPendientes {
	
	 public java.sql.ResultSet ExaPenHosp(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT a.adm_numero_ingreso,CONCAT(c.pabellon,'>',c.ubicacion_cama,'>',c.codigocama) AS Ubicacion,p.genero,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) AS Paciente,CONCAT(p.tipo_documento,'-',p.numero_documento)AS Documento, ent.nombre_entidad, p.pac_codigo_paciente, a.fecha_registro FROM  adm_paciente p, adm_censo_cama c, adm_admisiones a, adm_entidad ent, adm_convenio con,  img_citas_espera img WHERE a.pac_codigo_paciente_fk = p.pac_codigo_paciente AND c.cen_numero_cama = a.cen_numero_cama_fk AND ent.ent_nit = con.ent_nit_contratante_fk AND con.conv_numero_contrato = p.conv_numero_contrato_fk AND a.estado='0' AND c.pabellon ='HOSPITALIZACION' AND img.codigoPac_fk=p.pac_codigo_paciente GROUP BY p.pac_codigo_paciente ORDER BY a.fecha_registro DESC ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoImagenologiasPendientes>>ExaPen "+ex);
	        }
	        return rs;
	       
	    }
	 
	 public java.sql.ResultSet ExaPenHospPabellon(String pabellon){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT a.adm_numero_ingreso,CONCAT(c.pabellon,'>',c.ubicacion_cama,'>',c.codigocama) AS Ubicacion,p.genero,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) AS Paciente,CONCAT(p.tipo_documento,'-',p.numero_documento)AS Documento, ent.nombre_entidad, p.pac_codigo_paciente, a.fecha_registro FROM  adm_paciente p, adm_censo_cama c, adm_admisiones a, adm_entidad ent, adm_convenio con,  img_citas_espera img WHERE a.pac_codigo_paciente_fk = p.pac_codigo_paciente AND c.cen_numero_cama = a.cen_numero_cama_fk AND ent.ent_nit = con.ent_nit_contratante_fk AND con.conv_numero_contrato = p.conv_numero_contrato_fk AND a.estado='0' AND ((c.pabellon ='HOSPITALIZACION') OR (c.pabellon ='UCI')) AND c.ubicacion_cama='"+pabellon+"' AND img.codigoPac_fk=p.pac_codigo_paciente GROUP BY p.pac_codigo_paciente ORDER BY a.fecha_registro DESC ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoImagenologiasPendientes>>ExaPen "+ex);
	        }
	        return rs;
	       
	    }
	 
	 
	 
	 public java.sql.ResultSet ObtenerServicios(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();        	
	        	rs=st.executeQuery("(SELECT codigo,nombre FROM adm_subarea WHERE codigoarea =(SELECT codigo FROM adm_area WHERE nombre='HOSPITALIZACION' )) UNION (SELECT codigo,CONCAT('UCI- ',nombre)  FROM adm_subarea WHERE codigoarea=(SELECT codigo FROM adm_area WHERE nombre='UCI' )) ");
	        }
	        catch(Exception ex){
	        	System.out.print("Error en MetodoConsultarCenso>>ObtenerServicios "+ex);
	        	ex.getMessage();
	        }
	        return rs;
	    }
	 
	 
	 public String NumImgPendientesPorPaciente(String CodPaciente, String fechaAdm){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        String cad ="";
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT  COUNT(*) FROM img_citas_espera img, img_subexa isu  WHERE codigoPac_fk='"+CodPaciente+"' AND img.fecha_cita BETWEEN '"+fechaAdm+"' AND CURDATE() AND estado ='-1' AND isu.codigo= img.codigoExa_fk AND isu.nombre NOT LIKE '%RESONANCIA NUCLEAR MAGNETICA DE CORAZON %'");
	        	
	        	if (rs.next()){
		        	cad= rs.getString(1);
		        	if(cad=="0"){
		        		cad="1";
		        	}
		        }
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoImagenologiasPendientes>>imgPendientesPorPaciente "+ex);
	        }
	       // System.out.println("numero met "+cad);
	        return cad;
	    }
	 
	 public String nombrePabellon(String pabellon){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        String cad ="";
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT nombre FROM adm_subarea WHERE codigo='"+pabellon+"'");
	        	
	        	if (rs.next()){
		        	cad= rs.getString(1);
		        	
		        }
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoImagenologiasPendientes>>imgPendientesPorPaciente "+ex);
	        }
	       // System.out.println("numero met "+cad);
	        return cad;
	    }
	 
	 
	 public java.sql.ResultSet CargarImagenologiasPendientes(String CodigoPaciente, String fechaAdm){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT ipac.nombre,ipac.primer_apellido,ipac.segundo_apellido,ipac.numero_documento,isu.nombre AS ExamenesPendientes,ice.codigo, CONCAT(ice.fecha_cita,'/',ice.hora_cita)AS fecha  FROM img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu WHERE igr.codigo=isu.cod_gruFk AND isu.codigo=ice.codigoExa_fk AND ipac.pac_codigo_paciente=ice.codigoPac_fk AND ice.estado=-1 AND ice.realizadopor='0' AND ice.codigoPac_fk='"+CodigoPaciente+"' AND ice.fecha_cita BETWEEN '"+fechaAdm+"' AND CURDATE()  AND isu.nombre NOT LIKE '%RESONANCIA NUCLEAR MAGNETICA DE CORAZON %' ORDER BY ice.codigo");
	        	//System.out.println(rs);
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoImagenologiasPendientes>>CargarImagenologiasPendientes "+ex);
	        }	
	        return rs;
	    }
	 
	 
     
}
