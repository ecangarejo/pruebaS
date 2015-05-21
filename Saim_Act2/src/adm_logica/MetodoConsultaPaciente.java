package adm_logica;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JProgressBar;
import fact_bean.Tarifas;
//import fact_controlador.Rectangle;
import adm_logica.Conexion;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class MetodoConsultaPaciente {

	public java.sql.ResultSet Consultafecha(String fi, String ff){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.nombre, p.primer_apellido, p.segundo_apellido, p.tipo_documento, p.numero_documento, a.estado, c.pabellon, c.ubicacion_cama,  c.codigocama, c.piso_ubicacion, a.fecha_registro, a.hora_registro, a.registrado_por, dp.nombre, dp.apellido FROM saim.adm_admisiones a INNER JOIN saim.adm_paciente p ON p.pac_codigo_paciente=a.pac_codigo_paciente_fk  INNER JOIN saim.adm_censo_cama c ON a.cen_numero_cama_fk=c.cen_numero_cama INNER JOIN saim.seg_usuario su ON a.registrado_por=su.usuario INNER JOIN saim.seg_datos_personales dp ON dp.dat_codigo=su.dat_codigo_fk WHERE a.estado=0 AND a.fecha_registro BETWEEN '"+fi+"' AND '"+ff+"' ORDER BY a.fecha_registro, a.hora_registro ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoConsultaPaciente>>Consultafecha "+ex);
        }	
        return rs;
    }
	
}
