package pat_metodo;

import java.io.IOException;
import java.sql.Statement;

import adm_logica.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sun.misc.BASE64Decoder;


public class MetodoEstudiosPorAsignar {
	
	public java.sql.ResultSet examPorApr(){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT ordpat.idOrdenPat, CONCAT(ordpat.fecha, '/', ordpat.hora) AS Fecha,CONCAT(ipac.nombre,' ',ipac.primer_apellido,' ',ipac.segundo_apellido) AS nombrePac, CONCAT( ipac.tipo_documento, '-' ,ipac.numero_documento)AS documento,aen.nombre_entidad,CONCAT(sdt.nombre, ' ', sdt.apellido) AS Usuario, ipac.tipo_documento,ipac.numero_documento FROM ordenpat_encabezado ordpat, seg_usuario su, seg_datos_personales sdt, adm_paciente ipac, adm_convenio acv, adm_entidad aen WHERE ordpat.codusuario = su.usu_codigo AND su.dat_codigo_fk = sdt.dat_codigo  AND ordpat.estado = 1 AND ipac.pac_codigo_paciente = ordpat.codpaciente AND aen.ent_nit = acv.ent_nit_contratante_fk  AND acv.conv_numero_contrato = ipac.conv_numero_contrato_fk ORDER BY Fecha ASC ");
       
        	System.out.println("MetodoEstudiosPorAsignar");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoEstudiosPorAsignar>>examPorApr "+ex);
        }	
        return rs;
        
    }
	
	
	public void asignar(String idInforme){
		
		System.out.println("cod orden: "+idInforme);
		
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement(
						" UPDATE ordenpat_encabezado SET estado=2 WHERE estado=1 AND idOrdenPat='"+idInforme+"' ");
					
				psc.executeUpdate();
				psc.close();
				con.cerrar();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
	}
	

}
