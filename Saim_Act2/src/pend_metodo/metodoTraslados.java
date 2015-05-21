package pend_metodo;

import hic_bean.IngresarResultados;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class metodoTraslados {
	
	public java.sql.ResultSet ObtenerTrasladosPendientes(){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT tras.id_solicitud_traslado, CONCAT(tras.fecha,' - ',tras.hora) AS fecha,"+
        			"CONCAT(ipac.nombre,' ',ipac.primer_apellido,' ',ipac.segundo_apellido) AS paciente, "+
        			"CONCAT(ipac.tipo_documento,' - ',ipac.numero_documento) AS documento,aen.nombre_entidad, "+
        			"tras.cama_act, tras.servicio_trasladar, CONCAT(seg.nombre,' ',seg.apellido) AS medico "+
        			" FROM hic_solicitar_traslado tras,adm_convenio acv, seg_datos_personales seg, adm_paciente ipac ,adm_entidad aen, seg_usuario usu "+
        			" WHERE tras.estado='0' AND tras.codusuario = usu.usu_codigo AND usu.dat_codigo_fk=seg.dat_codigo  AND ipac.pac_codigo_paciente=tras.codpac AND aen.ent_nit=acv.ent_nit_contratante_fk "+
        			" AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoTraslado>>ObtenerInterconsultasPendientes "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerPendTransPac(String codAdm){	    
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_solicitar_traslado WHERE codadm='"+codAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoTraslado>>ObtenerPendTransPac "+ex);
        }	
        return rs;
    }
	
	
	public void actualizarTraslado(
			String codAdm,
			String estado
			) {
				
			 PreparedStatement psc = null;
					try {
						Conexion con = new Conexion();
						psc = con.conn.prepareStatement(
								"UPDATE hic_solicitar_traslado SET estado = ? WHERE codadm='"+codAdm+"'");
						psc.setString(1, estado);
						psc.executeUpdate();
						psc.close();
						con.cerrar();
						
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						System.out.println("Error en MetodoVerFormatos>>actualizarTraslado "	+ ex);
						
					}
				
			}
	
	
	

}
