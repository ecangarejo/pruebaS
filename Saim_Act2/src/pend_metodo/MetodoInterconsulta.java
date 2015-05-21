package pend_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoInterconsulta {
	
	
	 public java.sql.ResultSet ObtenerInterconsultasPendientes(){	    
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT inter.id_interconsulta,  inter.adm_pac, CONCAT(ipac.nombre,' ',ipac.primer_apellido,' ',ipac.segundo_apellido) AS paciente, CONCAT(seg.nombre,' ',seg.apellido) AS medico, esp.nombre_especialidad,aen.nombre_entidad, CONCAT(inter.fecha,' - ',inter.hora) AS fecha, inter.cod_pac,inter.cod_especialidad, inter.motivo FROM interconsulta inter,adm_convenio acv, seg_datos_personales seg, adm_paciente ipac, agm_especialidad esp ,adm_entidad aen, seg_usuario usu WHERE  inter.estado ='-1' AND inter.cod_usuario = usu.usu_codigo  AND usu.dat_codigo_fk=seg.dat_codigo AND inter.cod_especialidad=esp.codigo AND ipac.pac_codigo_paciente=inter.cod_pac AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoInterconsulta>>ObtenerInterconsultasPendientes "+ex);
	        }	
	        return rs;
	    }
	

}
