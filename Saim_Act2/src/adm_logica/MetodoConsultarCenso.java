/**
 * logica:MetodoConsultarCenso
 * Desarrollado:yosimar valega
 */
package adm_logica;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 * Obtiene datos para mostrar el censo
 *
 */
public class MetodoConsultarCenso {
	
	public java.sql.ResultSet ObtenerPacientesSalidaEntidad(String FechaInicial,String FechaFinal,String TipoSalida,String CodEps){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS Paciente, CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Documento,aent.nombre_entidad AS Entidad,aad.fecha_registro AS Ingreso,fef.fecha_egreso AS Egreso,CONCAT(hdp.EstadoSalida) AS Estado,((YEAR(aad.fecha_registro))-YEAR(apac.fecha_nacimiento))AS edad,aad.adm_numero_ingreso,apac.pac_codigo_paciente,apac.nivel_cotizante,apac.tipo_afiliacion  FROM adm_paciente apac,adm_admisiones aad,hic_destinopaciente hdp,adm_entidad aent,adm_convenio acv,empresa emp,fact_enc_factura fef WHERE apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aent.ent_nit=acv.ent_nit_contratante_fk AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato AND aad.adm_numero_ingreso=hdp.codAdm AND aad.estado=1 AND fef.cod_admision=aad.adm_numero_ingreso AND hdp.DestinoPaciente='"+TipoSalida+"' AND fef.fecha_egreso BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' AND apac.conv_numero_contrato_fk="+CodEps+" GROUP BY aad.adm_numero_ingreso");
        	System.out.println("SELECT CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS Paciente, CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Documento,aent.nombre_entidad AS Entidad,aad.fecha_registro AS Ingreso,fef.fecha_egreso AS Egreso,CONCAT(hdp.EstadoSalida) AS Estado,((YEAR(aad.fecha_registro))-YEAR(apac.fecha_nacimiento))AS edad,aad.adm_numero_ingreso,apac.pac_codigo_paciente,apac.nivel_cotizante,apac.tipo_afiliacion  FROM adm_paciente apac,adm_admisiones aad,hic_destinopaciente hdp,adm_entidad aent,adm_convenio acv,empresa emp,fact_enc_factura fef WHERE apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aent.ent_nit=acv.ent_nit_contratante_fk AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato AND aad.adm_numero_ingreso=hdp.codAdm AND aad.estado=1 AND fef.cod_admision=aad.adm_numero_ingreso AND hdp.DestinoPaciente='"+TipoSalida+"' AND fef.fecha_egreso BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' AND apac.conv_numero_contrato_fk="+CodEps+" GROUP BY aad.adm_numero_ingreso");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>ObtenerPacientesSalida "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPacientesSalida(String FechaInicial,String FechaFinal,String TipoSalida){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS Paciente, CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Documento,aent.nombre_entidad AS Entidad,aad.fecha_registro AS Ingreso,fef.fecha_egreso AS Egreso,CONCAT(hdp.EstadoSalida) AS Estado,((YEAR(aad.fecha_registro))-YEAR(apac.fecha_nacimiento))AS edad,aad.adm_numero_ingreso,apac.pac_codigo_paciente,apac.nivel_cotizante,apac.tipo_afiliacion  FROM adm_paciente apac,adm_admisiones aad,hic_destinopaciente hdp,adm_entidad aent,adm_convenio acv,empresa emp,fact_enc_factura fef WHERE apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aent.ent_nit=acv.ent_nit_contratante_fk AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato AND aad.adm_numero_ingreso=hdp.codAdm AND aad.estado=1 AND fef.cod_admision=aad.adm_numero_ingreso AND hdp.DestinoPaciente='"+TipoSalida+"' AND fef.fecha_egreso BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' GROUP BY aad.adm_numero_ingreso");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>ObtenerPacientesSalida "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerEntidades(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT ent_nit,nombre_entidad FROM adm_entidad ORDER BY nombre_entidad");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>ObtenerEntidades "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	public java.sql.ResultSet SumatoriaDias(String Subarea,String FechaInicial,String FechaFinal){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT b.*,SUM(IF(b.Diferencia=0,1,Diferencia)) AS Diferencia2 FROM  (SELECT aad.adm_numero_ingreso,aad.fecha_registro,acc.codigocama,CONCAT(acc.pabellon,' ',acc.ubicacion_cama) AS Ubicacion,ahc.fecha_inicial, ahc.fecha_final,DATEDIFF((IF(ahc.fecha_final ='0000-00-00',CURDATE(),(IF(ahc.fecha_final>'"+FechaFinal+"' ,'"+FechaFinal+"',ahc.fecha_final)))),ahc.fecha_inicial) AS Diferencia,    (IF(ahc.fecha_final> '"+FechaFinal+"','"+FechaFinal+"',ahc.fecha_final)) AS FechaFinalMes FROM adm_admisiones aad,adm_historico_cama ahc,adm_censo_cama acc WHERE aad.adm_numero_ingreso = ahc.adm_numero_ingreso_fk AND acc.cen_numero_cama = ahc.cen_numero_cama_fk     AND ahc.fecha_inicial BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"' AND acc.codsubarea = '"+Subarea+"') AS b ");
        	System.out.println("SELECT b.*,SUM(IF(b.Diferencia=0,1,Diferencia)) AS Diferencia2 FROM  (SELECT aad.adm_numero_ingreso,aad.fecha_registro,acc.codigocama,CONCAT(acc.pabellon,' ',acc.ubicacion_cama) AS Ubicacion,ahc.fecha_inicial, ahc.fecha_final,DATEDIFF((IF(ahc.fecha_final ='0000-00-00',CURDATE(),(IF(ahc.fecha_final>'"+FechaFinal+"' ,'"+FechaFinal+"',ahc.fecha_final)))),ahc.fecha_inicial) AS Diferencia,    (IF(ahc.fecha_final> '"+FechaFinal+"','"+FechaFinal+"',ahc.fecha_final)) AS FechaFinalMes FROM adm_admisiones aad,adm_historico_cama ahc,adm_censo_cama acc WHERE aad.adm_numero_ingreso = ahc.adm_numero_ingreso_fk AND acc.cen_numero_cama = ahc.cen_numero_cama_fk     AND ahc.fecha_inicial BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"' AND acc.codsubarea = '"+Subarea+"') AS b ");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>SumatoriaDias "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	
	public java.sql.ResultSet CantidadPacientes(String Subarea,String FechaInicial,String FechaFinal){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT COUNT(a.adm_numero_ingreso) AS Cantidad FROM (SELECT aad.adm_numero_ingreso FROM adm_admisiones aad,adm_historico_cama ahc,adm_censo_cama acc WHERE aad.adm_numero_ingreso=ahc.adm_numero_ingreso_fk AND acc.cen_numero_cama=ahc.cen_numero_cama_fk AND ahc.fecha_inicial BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"'  AND acc.codsubarea="+Subarea+" GROUP BY aad.adm_numero_ingreso) AS a");
        	System.out.println("SELECT COUNT(a.adm_numero_ingreso) AS Cantidad FROM (SELECT aad.adm_numero_ingreso FROM adm_admisiones aad,adm_historico_cama ahc,adm_censo_cama acc WHERE aad.adm_numero_ingreso=ahc.adm_numero_ingreso_fk AND acc.cen_numero_cama=ahc.cen_numero_cama_fk AND ahc.fecha_inicial BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"'  AND acc.codsubarea="+Subarea+" GROUP BY aad.adm_numero_ingreso) AS a");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>CantidadPacientes "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	public java.sql.ResultSet UnidadesHospitalarias(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT CONCAT(pabellon,'>>',ubicacion_cama) AS Ubicacion,codsubarea FROM adm_censo_cama WHERE pabellon ='UCI' OR pabellon='HOSPITALIZACION' GROUP BY codsubarea ORDER BY Ubicacion");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>UnidadesHospitalarias "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarCamasUnidad(String CodSubarea){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT * FROM adm_censo_cama WHERE codsubarea="+CodSubarea+" ");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>BuscarCamasUnidad "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEntidadesConvenio(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("select aent.nombre_entidad,acv.conv_numero_contrato from adm_entidad aent,adm_convenio acv where aent.ent_nit=acv.ent_nit_contratante_fk order by aent.nombre_entidad");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>ObtenerEntidadesConvenio "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerServicios(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();        	
        	rs=st.executeQuery("SELECT codigo,nombre FROM adm_subarea");
        }
        catch(Exception ex){
        	System.out.print("Error en MetodoConsultarCenso>>ObtenerServicios "+ex);
        	ex.getMessage();
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerCenso(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("");
rs=st.executeQuery("select c.codigocama, a.semana_cotizadas, p.genero, p.primer_apellido, p.nombre, ent.nombre_entidad, p.pac_codigo_paciente,p.fecha_nacimiento,a.observaciones,a.fecha_registro,DATEDIFF(CURDATE(),a.fecha_registro) as cesar,c.pabellon,c.ubicacion_cama " +
        			"from  adm_paciente p, adm_censo_cama c, adm_admisiones a, adm_entidad ent, adm_convenio con " +
        			"where a.pac_codigo_paciente_fk = p.pac_codigo_paciente and " +
        			"c.cen_numero_cama = a.cen_numero_cama_fk and "+
"ent.ent_nit = con.ent_nit_contratante_fk and "+
"con.conv_numero_contrato = p.conv_numero_contrato_fk and a.estado='0'ORDER BY c.pabellon");

        	
        }
        catch(Exception ex){
        	ex.getMessage();
        }
        return rs;
    }
	
	/**
	 * obtiene edad del paciente consulta current_date
	 */
	
	public java.sql.ResultSet obtenerEdad(String codigo) throws SQLException{
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	st.executeQuery("SELECT CURRENT_DATE,(YEAR(CURRENT_DATE) - YEAR(fecha_nacimiento)) -(RIGHT(CURRENT_DATE,5) < RIGHT(fecha_nacimiento,5)) AS edad FROM adm_paciente");
        	      	
        	
        }
        catch(Exception ex){
        	ex.getMessage();
        }
        return rs;
    }
	
	/**
	 * obtiene edad del paciente por funcion
	 */
	
	public int calcularEdad(Calendar fechaNac){
	    Calendar today = Calendar.getInstance();
	   
	    int diff_year = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
	    int diff_month = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
	    int diff_day = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

	    //Si está en ese año pero todavía no los ha cumplido
	    if(diff_month<0 || (diff_month==0 && diff_day<0)){
	        diff_year = diff_year - 1; //no aparecían los dos guiones del postincremento :|
	    }
	    return diff_year;
	}
}
