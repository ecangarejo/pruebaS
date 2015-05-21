package fact_metodo;

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

public class MetodoFurips {
	
	public java.sql.ResultSet Empresas(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT nombre_entidad, ent_nit FROM adm_entidad ORDER BY nombre_entidad ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>Empresas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FacturasNumCta(String NumCTA){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fnu.cod_enc_fact_fk,fnu.consecutivo FROM fact_factenviadas ffe,fact_facturas_enviadas ffv,fact_numeradas fnu WHERE ffe.consEnvio=ffv.consEnvio AND fnu.cod_fact_numerada=ffv.codFact AND ffe.consEnvio='"+NumCTA+"'");
        	System.out.println("SELECT fnu.cod_enc_fact_fk,fnu.consecutivo FROM fact_factenviadas ffe,fact_facturas_enviadas ffv,fact_numeradas fnu WHERE ffe.consEnvio=ffv.consEnvio AND fnu.cod_fact_numerada=ffv.codFact AND ffe.consEnvio='"+NumCTA+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>FacturasNumCta "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet Facturas(String eps, String fi, String ff){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT fn.cod_enc_fact_fk,fn.consecutivo,ef.cod_admision FROM fact_numeradas fn, fact_enc_factura ef WHERE fn.cod_enc_fact_fk=ef.cod_enc_factura AND ef.cod_eps='"+eps+"' AND ef.fecha BETWEEN '"+fi+"' AND '"+ff+"' order by fn.consecutivo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>Facturas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet VerFurips(String eps, String adm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT fd.Codigo " +
        			"FROM furips_datos fd,furips_datosaccidente acc, furips_datospropietario pro, furips_datosconductor con," +
        			"furips_datoscertificacion cer,furips_datosamparo amp, furips_datosmedico med,  fact_enc_factura ef " +
        			"WHERE ef.cod_admision='"+adm+"' AND ef.cod_eps='"+eps+"' AND ef.cod_admision=fd.Cod_Admision " +
        			"AND acc.DatosCodigo_fk=fd.Codigo AND pro.NumeroDocumento=acc.DocPropietario  AND con.Documento=acc.DocConductor AND cer.DatosCodigo_fk=fd.Codigo " +
        			"AND med.NumeroDocumento=fd.DocMedico AND amp.DatosCodigo_fk=fd.Codigo  " +
        			"GROUP BY fd.Codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoFurips>>Furips "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet Prestador(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codprestador FROM empresa ");
        	System.out.println(" SELECT codprestador FROM empresa-------------------------------- ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>Prestador "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet SqlPruebaRips(){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT tipo_documento,numero_documento,nombre,primer_apellido,segundo_apellido,genero FROM adm_paciente");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>SqlPruebaRips "+ex);
		}
		return rs;
	}
	

	/***Consulta rips AF transacciones*****/	
	public java.sql.ResultSet AF(String fi, String ff, String f){	
		System.out.println("AF");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT emp.codprestador,emp.nombre AS NomEntidad,'NI',LEFT(emp.nit,LOCATE('',emp.nit,9)) AS nit,fnu.consecutivo,DATE_FORMAT(fef.fecha,'%d/%m/%Y') as fecha,DATE_FORMAT(fef.fecha_ingreso,'%d/%m/%Y') as ingreso,DATE_FORMAT(fef.fecha_egreso,'%d/%m/%Y') as egreso,aen.cod_prestador,LEFT(aen.nombre_entidad,30),fcv.num_contrato,'0',if(fcv.poliza<>null,fcv.poliza,''),fef.copago,'0','0',fef.valor FROM fact_enc_factura fef,adm_entidad aen,empresa emp,fact_convenios fcv,fact_numeradas fnu WHERE fef.cod_eps=aen.ent_nit AND fcv.cod_entidad=aen.ent_nit AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fnu.consecutivo='"+f+"' ");
        	
        	System.out.println("SELECT emp.codprestador,emp.nombre AS NomEntidad,'NI',LEFT(emp.nit,LOCATE('',emp.nit,9)) AS nit,fnu.consecutivo,DATE_FORMAT(fef.fecha,'%d/%m/%Y') as fecha,DATE_FORMAT(fef.fecha_ingreso,'%d/%m/%Y') as ingreso,DATE_FORMAT(fef.fecha_egreso,'%d/%m/%Y') as egreso,aen.cod_prestador,LEFT(aen.nombre_entidad,30),fcv.num_contrato,'0',if(fcv.poliza<>null,fcv.poliza,''),fef.copago,'0','0',fef.valor FROM fact_enc_factura fef,adm_entidad aen,empresa emp,fact_convenios fcv,fact_numeradas fnu WHERE fef.cod_eps=aen.ent_nit AND fcv.cod_entidad=aen.ent_nit AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fnu.consecutivo='"+f+"' ");
        	//System.out.println("SELECT emp.codprestador,emp.nombre AS NomEntidad,'NI',LEFT(emp.nit,LOCATE('',emp.nit,9)) AS nit,fnu.consecutivo,DATE_FORMAT(fef.fecha,'%d/%m/%Y') as fecha,DATE_FORMAT(fef.fecha_ingreso,'%d/%m/%Y') as ingreso,DATE_FORMAT(fef.fecha_egreso,'%d/%m/%Y') as egreso,aen.cod_prestador,aen.nombre_entidad,fcv.num_contrato,'0',if(fcv.poliza<>null,fcv.poliza,''),fef.copago,'0','0',fef.valor FROM fact_enc_factura fef,adm_entidad aen,empresa emp,fact_convenios fcv,fact_numeradas fnu WHERE fef.cod_eps=aen.ent_nit AND fcv.cod_entidad=aen.ent_nit AND fef.fecha_ingreso BETWEEN '"+fi+"' AND '"+ff+"' AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fnu.consecutivo='"+f+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AF "+ex);
        }	
        return rs;
    }
	
	/***Consulta rips AC CONSULTAS*****/	
	public java.sql.ResultSet AC(String fi, String ff, String f){	
		/**
		 */
		
		System.out.println("AC");
		
		System.out.println("ESTE ES EL METODO AC SELECT DISTINCT  fnu.consecutivo,  emp.codprestador,  ap.tipo_documento,  ap.numero_documento,  " +
    			"DATE_FORMAT(hdi.fecha,'%d/%m/%Y') AS fecha ,  fef.num_autorizacion,  fdf.cod_programa,  " +
    			"'' fin_cons,  '' cau_ext,  '','','','',  '',  fdf.valor,  fef.moderacion, " +
    			" (fdf.cantidad * fdf.valor) AS valor_neto, fdf.cantidad FROM  fact_numeradas fnu,  fact_enc_factura fef, " +
    			" adm_paciente ap,  adm_admisiones aad,  empresa emp,  hic_diagnosticoingreso hdi,  fact_det_factura fdf, " +
    			" fact_programas fpr WHERE ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  " +
    			" AND aad.adm_numero_ingreso = fef.cod_admision   AND fnu.cod_enc_fact_fk = fef.cod_enc_factura  " +
    					"  AND fnu.consecutivo = '"+f+"'  " +
    							" AND fdf.cod_enc_factura_fk = fef.cod_enc_factura " +
    							" AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura ");

    	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT  fnu.consecutivo,  emp.codprestador,  ap.tipo_documento,  ap.numero_documento,  " +
        			"DATE_FORMAT(hdi.fecha,'%d/%m/%Y') AS fecha , fef.num_autorizacion,  fdf.cod_programa,  " +
        			" '' fin_cons,  '' cau_ext,  '','','','',  '',  fdf.valor,  fef.moderacion, " +
        			" (fdf.cantidad * fdf.valor) AS valor_neto, fdf.cantidad FROM  fact_numeradas fnu,  fact_enc_factura fef, " +
        			" adm_paciente ap,  adm_admisiones aad,  empresa emp,  hic_diagnosticoingreso hdi,  fact_det_factura fdf, " +
        			" fact_programas fpr WHERE ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  " +
        			" AND aad.adm_numero_ingreso = fef.cod_admision   AND fnu.cod_enc_fact_fk = fef.cod_enc_factura  " +
        					"  AND fnu.consecutivo = '"+f+"'  " +
        							" AND fdf.cod_enc_factura_fk = fef.cod_enc_factura " +
        							" AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura ");
        	
        	
        	/*System.out.println("SELECT DISTINCT  fnu.consecutivo,  emp.codprestador,  ap.tipo_documento,  ap.numero_documento,  " +
        			"DATE_FORMAT(hdi.fecha,'%d/%m/%Y') AS fecha ,  aad.numero_autorizacion,  fdf.cod_programa,  " +
        			"hdp.fin_cons,  hdp.cau_ext,  hdi.codDiagnostico,'','','',  hdp.tip_diag,  fdf.valor,  fef.moderacion, " +
        			" (fdf.cantidad * fdf.valor) AS valor_neto FROM  fact_numeradas fnu,  fact_enc_factura fef, " +
        			" adm_paciente ap,  adm_admisiones aad,  empresa emp,  hic_diagnosticoingreso hdi,  fact_det_factura fdf, " +
        			" hic_destinopaciente hdp,  fact_programas fpr WHERE ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  " +
        			" AND aad.adm_numero_ingreso = fef.cod_admision   AND fnu.cod_enc_fact_fk = fef.cod_enc_factura  " +
        			" AND aad.adm_numero_ingreso = hdp.codAdm   AND fef.fecha BETWEEN '"+fi+"' AND '"+ff+"' " +
        					"  AND fnu.consecutivo = '"+f+"'  " +
        							" AND hdi.codAdm = fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura " +
        							" AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura ");*/
           }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AC "+ex);
        }	
        return rs;
    }
	

public java.sql.ResultSet AC1(String fi, String ff, String f,String tdiag){	
		/**
		 */
	System.out.println("ESTE ES EL METODO AC1 SELECT DISTINCT  " +
			"  fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, " +
			"  SUBSTRING_INDEX(fef.documento,'-',-1) AS numero_documento, (SELECT DATE_FORMAT(hdi.fecha, '%d/%m/%Y') AS fecha FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoingreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS fecha, " +
					" fef.num_autorizacion,fdf.cod_programa, hdp.fin_cons, hdp.cau_ext, " +
					" (SELECT codDiagnostico FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoingreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS codDiagnostico, " +
							" '','','',hdp.tip_diag,fdf.valor,fef.moderacion,(fdf.cantidad * fdf.valor) AS valor_neto,fdf.cantidad " +
							" FROM fact_numeradas fnu,fact_enc_factura fef,empresa emp,hic_destinopaciente hdp,fact_det_factura fdf,fact_programas fpr " +
							" WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura  AND fnu.consecutivo = '"+f+"' " +
									" AND hdp.codAdm=fef.cod_admision  AND fdf.cod_enc_factura_fk = fef.cod_enc_factura  AND fpr.cod_programa = fdf.cod_programafk " +
									"  AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura  ");
	
		System.out.println("AC1");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT  " +
        			"  fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, " +
        			"  SUBSTRING_INDEX(fef.documento,'-',-1) AS numero_documento, (SELECT DATE_FORMAT(hdi.fecha, '%d/%m/%Y') AS fecha FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoingreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS fecha, " +
        					" fef.num_autorizacion,fdf.cod_programa, hdp.fin_cons, hdp.cau_ext, " +
        					" (SELECT codDiagnostico FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoingreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS codDiagnostico, " +
        							" '','','',hdp.tip_diag,fdf.valor,fef.moderacion,(fdf.cantidad * fdf.valor) AS valor_neto,fdf.cantidad " +
        							" FROM fact_numeradas fnu,fact_enc_factura fef,empresa emp,hic_destinopaciente hdp,fact_det_factura fdf,fact_programas fpr " +
        							" WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura  AND fnu.consecutivo = '"+f+"' " +
        									" AND hdp.codAdm=fef.cod_admision  AND fdf.cod_enc_factura_fk = fef.cod_enc_factura  AND fpr.cod_programa = fdf.cod_programafk " +
        									"  AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura  ");
        	/*rs=st.executeQuery("SELECT DISTINCT  fnu.consecutivo,  emp.codprestador,  ap.tipo_documento,  ap.numero_documento,  " +
        			"DATE_FORMAT(hdi.fecha,'%d/%m/%Y') AS fecha ,  fef.num_autorizacion,  fdf.cod_programa,  " +
        			"hdp.fin_cons,  hdp.cau_ext,  hdi.codDiagnostico,'','','',  hdp.tip_diag,  fdf.valor,  fef.moderacion, " +
        			" (fdf.cantidad * fdf.valor) AS valor_neto, fdf.cantidad FROM  fact_numeradas fnu,  fact_enc_factura fef, " +
        			" adm_paciente ap,  adm_admisiones aad,  empresa emp,  hic_diagnosticoingreso hdi,  fact_det_factura fdf, " +
        			" hic_destinopaciente hdp,  fact_programas fpr WHERE ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  " +
        			" AND aad.adm_numero_ingreso = fef.cod_admision   AND fnu.cod_enc_fact_fk = fef.cod_enc_factura  " +
        			" AND aad.adm_numero_ingreso = hdp.codAdm  " +
        					"  AND fnu.consecutivo = '"+f+"'  " +
        							" AND hdi.codAdm = fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura " +
        							" AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura ");
        	*/
        	
        	
        	/*System.out.println("SELECT DISTINCT  fnu.consecutivo,  emp.codprestador,  ap.tipo_documento,  ap.numero_documento,  " +
        			"DATE_FORMAT(hdi.fecha,'%d/%m/%Y') AS fecha ,  aad.numero_autorizacion,  fdf.cod_programa,  " +
        			"hdp.fin_cons,  hdp.cau_ext,  hdi.codDiagnostico,'','','',  hdp.tip_diag,  fdf.valor,  fef.moderacion, " +
        			" (fdf.cantidad * fdf.valor) AS valor_neto FROM  fact_numeradas fnu,  fact_enc_factura fef, " +
        			" adm_paciente ap,  adm_admisiones aad,  empresa emp,  hic_diagnosticoingreso hdi,  fact_det_factura fdf, " +
        			" hic_destinopaciente hdp,  fact_programas fpr WHERE ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  " +
        			" AND aad.adm_numero_ingreso = fef.cod_admision   AND fnu.cod_enc_fact_fk = fef.cod_enc_factura  " +
        			" AND aad.adm_numero_ingreso = hdp.codAdm   AND fef.fecha BETWEEN '"+fi+"' AND '"+ff+"' " +
        					"  AND fnu.consecutivo = '"+f+"'  " +
        							" AND hdi.codAdm = fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura " +
        							" AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura ");*/
           }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AC1_nu "+ex);
        }	
        return rs;
    }
	
	/***Consulta rips AT OTROS SERVICIOS*****/	
	public java.sql.ResultSet AT(String fi, String ff, String f){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento, " +
        			" fef.num_autorizacion,IF(fdf.clase_servicio = 'HONORARIOS','4',IF(fdf.clase_servicio = 'SERVICIOS Y TRASLADOS','2',IF(fdf.clase_servicio = 'ESTANCIA','3','1'))) AS clase_servicio,fdf.cod_programa,REPLACE(LEFT(fdf.nombre_programa, 60),',','.')  AS nombre_programa,fdf.cantidad, " +
        			" fdf.valor,(fdf.cantidad * fdf.valor) AS ValorTotal " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,fact_det_factura fdf,empresa emp,adm_admisiones aad, " +
        			" adm_paciente ap,fact_programas fpr " +
        			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fef.cod_enc_factura = fdf.cod_enc_factura_fk AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
        			"  AND aad.adm_numero_ingreso = fef.cod_admision AND fpr.cod_programa=fdf.cod_programafk " +
        			" AND fpr.archivo_rip=11 " +
        			"  AND fnu.consecutivo = '"+f+"' " +
        					"  AND (fpr.clase_servicio!=9 AND fpr.clase_servicio!=10)");
        	
        	/*System.out.println("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento, " +
        			" aad.numero_autorizacion,IF(fdf.clase_servicio = 'HONORARIOS','4',IF(fdf.clase_servicio = 'SERVICIOS Y TRASLADOS','2',IF(fdf.clase_servicio = 'ESTANCIA','3','1'))) AS clase_servicio,fdf.cod_programa,LEFT(fdf.nombre_programa,60) AS nombre_programa,fdf.cantidad, " +
        			" fdf.valor,(fdf.cantidad * fdf.valor) AS ValorTotal " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,fact_det_factura fdf,empresa emp,adm_admisiones aad, " +
        			" adm_paciente ap,fact_programas fpr " +
        			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fef.cod_enc_factura = fdf.cod_enc_factura_fk AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
        			"  AND aad.adm_numero_ingreso = fef.cod_admision AND fpr.cod_programa=fdf.cod_programafk " +
        			" AND fpr.archivo_rip=11 " +
        			"  AND fnu.consecutivo = '"+f+"' " +
        					"  AND fef.fecha_ingreso BETWEEN '"+fi+"' AND '"+ff+"' AND (fpr.clase_servicio!=9 AND fpr.clase_servicio!=10)");*/
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AC "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet AP2(String fi, String ff, String f){	
		/**
		 */
		//System.out.println("No c porq entramos aqui AP2");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento," +
        			"DATE_FORMAT(fdf.fecha_realizacion,'%d/%m/%Y') fecha_realizacion,fef.num_autorizacion,fpr.cod_cups,'1' AS ambito," +
        			"'1' AS finalidad,'5' AS perAtiende,'' AS codDiag,'' AS codDiagRela,'' AS complicacion,'' AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor " +
        			" FROM fact_rips fri,fact_numeradas fnu,fact_enc_factura fef,fact_det_factura fdf,empresa emp,adm_admisiones aad,adm_paciente ap,fact_programas fpr " +
        			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fef.cod_enc_factura = fdf.cod_enc_factura_fk " +
        			" AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente AND aad.adm_numero_ingreso = fef.cod_admision " +
        			" AND fpr.cod_programa = fdf.cod_programafk  " +  //AND fpr.archivo_rip = 11
        			" AND fnu.consecutivo = '"+f+"' " +
        					" AND fri.cod_rip=fpr.archivo_rip and fri.cod_rip!=5 " +
        							" AND (fpr.clase_servicio = 9 OR fpr.clase_servicio = 10 )");
        	
        	/*System.out.println("AP2 SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento," +
        			"DATE_FORMAT(fdf.fecha_realizacion,'%d/%m/%Y') fecha_realizacion,aad.numero_autorizacion,fpr.cod_cups,'1' AS ambito," +
        			"'1' AS finalidad,'5' AS perAtiende,'' AS codDiag,'' AS codDiagRela,'' AS complicacion,'' AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,fact_det_factura fdf,empresa emp,adm_admisiones aad,adm_paciente ap,fact_programas fpr " +
        			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fef.cod_enc_factura = fdf.cod_enc_factura_fk " +
        			" AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente AND aad.adm_numero_ingreso = fef.cod_admision " +
        			" AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 11 " +
        			" AND fnu.consecutivo = '"+f+"' " +
        					" AND fef.fecha_ingreso BETWEEN '"+fi+"' AND '"+ff+"' " +
        							" AND (fpr.clase_servicio = 9 OR fpr.clase_servicio = 10 )");   */    	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AP2 "+ex);
        }	
        return rs;
    }
	

public java.sql.ResultSet DAP(String f){	
		/**
		 */
		System.out.println("SELECT hde.codigo,hde.TipDiag "+
	"FROM hic_diagnosticoegreso hde, fact_enc_factura fef, fact_numeradas fn "+
	"WHERE   hde.codAdm=fef.cod_admision "+
	"AND fef.cod_enc_factura=fn.cod_enc_fact_fk  "+
	"AND   fn.consecutivo='"+f+"' order by hde.codigo desc limit 1");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hde.codigo,hde.TipDiag "+
        				"FROM hic_diagnosticoegreso hde, fact_enc_factura fef, fact_numeradas fn "+
        				"WHERE   hde.codAdm=fef.cod_admision "+
        				"AND fef.cod_enc_factura=fn.cod_enc_fact_fk  "+
        				"AND   fn.consecutivo='"+f+"' order by hde.codigo desc limit 1");
        	

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>DAP "+ex);
        }	
        return rs;
    }

	/***Consulta rips AM MEDICAMENTOS*****/	
	public java.sql.ResultSet AM(String fi, String ff, String f){	
		System.out.println("AM");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,fef.num_autorizacion,med.codigoMedicamento, " +
        			" IF(med.clasificacion = 'Pos', '1', '2') AS clasificacion,REPLACE(LEFT(med.nombre,30),',', '.') NomGenerico, ffc.forma_farmaceutica, " +
        			"  REPLACE((med.concentracion),',','.') concentracion,  '' unidad,  fdf.cantidad,  fdf.valor,  (fdf.cantidad * fdf.valor) AS ValorTotal " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,fact_det_factura fdf,empresa emp,adm_admisiones aad, " +
        			" adm_paciente ap,medicamentos med,fact_prog_med fpm,fact_programas fpr,farc_formafarmaceutica ffc " +
        			" WHERE fef.cod_enc_factura = fnu.cod_enc_fact_fk AND fef.cod_enc_factura = fdf.cod_enc_factura_fk  AND aad.adm_numero_ingreso = fef.cod_admision " +
        			" AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente "+
        					" AND fnu.consecutivo = '"+f+"'  AND med.codigo = fpm.cod_med AND fpm.cod_prog = fpr.cod_programa " +
        							" AND ffc.codigo = med.cod_formaFK AND fpr.archivo_rip=9 " +
        							" AND fpr.cod_programa=fdf.cod_programafk");
        	
        	
        	/*System.out.println("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,aad.numero_autorizacion,med.codigoMedicamento, " +
        			" IF(med.clasificacion = 'Pos', '1', '2') AS clasificacion,LEFT(REPLACE( RTRIM(LTRIM(med.nombre_generico)),',', '.' ),30) NomGenerico, ffc.forma_farmaceutica, " +
        			"  REPLACE( RTRIM(LTRIM(med.concentracion)),    ',',    '.'  ) concentracion,  '' unidad,  fdf.cantidad,  fdf.valor,  (fdf.cantidad * fdf.valor) AS ValorTotal " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,fact_det_factura fdf,empresa emp,adm_admisiones aad, " +
        			" adm_paciente ap,medicamentos med,fact_prog_med fpm,fact_programas fpr,farc_formafarmaceutica ffc " +
        			" WHERE fef.cod_enc_factura = fnu.cod_enc_fact_fk AND fef.cod_enc_factura = fdf.cod_enc_factura_fk  AND aad.adm_numero_ingreso = fef.cod_admision " +
        			" AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente AND fef.fecha BETWEEN '"+fi+"' AND '"+ff+"' " +
        					" AND fnu.consecutivo = '"+f+"'  AND med.codigo = fpm.cod_med AND fpm.cod_prog = fpr.cod_programa " +
        							" AND ffc.codigo = med.cod_formaFK AND fpr.archivo_rip=9 " +
        							" AND fpr.cod_programa=fdf.cod_programafk");*/
        	
        	 }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AM"+ex);
        }	
        return rs;
    }
	
	



/** archivo furips 1
		 */
public java.sql.ResultSet F1(String fact, String eps){	
		
	
	System.out.println("SELECT * " +
        			"FROM furips_datos fd,furips_datosaccidente acc, furips_datospropietario pro, furips_datosconductor con," +
        			"furips_datoscertificacion cer,furips_datosamparo amp, furips_datosmedico med,  fact_enc_factura ef, fact_numeradas fn " +
        			"WHERE fn.consecutivo='"+fact+"' AND  fn.cod_enc_fact_fk=ef.cod_enc_factura AND ef.cod_eps='"+eps+"' AND ef.cod_admision=fd.Cod_Admision " +
        			"AND acc.DatosCodigo_fk=fd.Codigo AND pro.NumeroDocumento=acc.DocPropietario  AND con.Documento=acc.DocConductor AND cer.DatosCodigo_fk=fd.Codigo " +
        			"AND med.NumeroDocumento=fd.DocMedico AND amp.DatosCodigo_fk=fd.Codigo  " +
        			"GROUP BY fd.Codigo");
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * " +
        			"FROM furips_datos fd,furips_datosaccidente acc, furips_datospropietario pro, furips_datosconductor con," +
        			"furips_datoscertificacion cer,furips_datosamparo amp, furips_datosmedico med,  fact_enc_factura ef, fact_numeradas fn " +
        			"WHERE fn.consecutivo='"+fact+"' AND  fn.cod_enc_fact_fk=ef.cod_enc_factura AND ef.cod_eps='"+eps+"' AND ef.cod_admision=fd.Cod_Admision " +
        			"AND acc.DatosCodigo_fk=fd.Codigo AND pro.NumeroDocumento=acc.DocPropietario  AND con.Documento=acc.DocConductor AND cer.DatosCodigo_fk=fd.Codigo " +
        			"AND med.NumeroDocumento=fd.DocMedico AND amp.DatosCodigo_fk=fd.Codigo  " +
        			"GROUP BY fd.Codigo");
        }catch(Exception ex){
        	System.out.println("Error en MetodoFurips>>F1 "+ex);
        }	
        return rs;
    }

/** datos paciente
 */
public java.sql.ResultSet pac(String tp, String nd){	


System.out.println("SELECT apac.tipo_documento, apac.numero_documento, apac.nombre, apac.primer_apellido, apac.segundo_apellido," +
		"apac.pac_codigo_paciente,apac.fecha_nacimiento,apac.genero,apac.direccion, apac.telefono_celular," +
		"apac.telefono_fijo,mun.dept_codigo_fk,mun.mun_codigo" +
		"FROM adm_paciente apac, adm_municipio mun, adm_departamento dpto " +
		"WHERE apac.tipo_documento='"+tp+"' AND apac.numero_documento='"+nd+"' " +
		"AND mun.muni_cod=apac.mun_codigo_fk AND mun.dept_codigo_fk=dpto.dept_codigo");

java.sql.ResultSet rs=null;
Statement st = null;
try{
	Conexion con=new Conexion();
	st = con.conn.createStatement();
	rs=st.executeQuery("SELECT apac.tipo_documento, apac.numero_documento, apac.nombre, apac.primer_apellido, apac.segundo_apellido," +
			"apac.pac_codigo_paciente,apac.fecha_nacimiento,apac.genero,apac.direccion, apac.telefono_celular," +
			"apac.telefono_fijo, mun.dept_codigo_fk,mun.mun_codigo " +
			"FROM adm_paciente apac, adm_municipio mun, adm_departamento dpto " +
			"WHERE apac.tipo_documento='"+tp+"' AND apac.numero_documento='"+nd+"' " +
			"AND mun.muni_cod=apac.mun_codigo_fk AND mun.dept_codigo_fk=dpto.dept_codigo");
}catch(Exception ex){
	System.out.println("Error en MetodoFurips>>F1 "+ex);
}	
return rs;
}


public java.sql.ResultSet F2(String fact){	


	System.out.println("SELECT fn.consecutivo,CONCAT('1') AS Reclamacion,fdf.cod_programa,fdf.cantidad,CONCAT(fdf.valor) AS ValorUnitario,CONCAT(fef.valor) AS ValorFactura" +
			"FROM fact_enc_factura fef,fact_det_factura fdf,furips_datos ffd,fact_numeradas fn" +
			"WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk" +
			"AND ffd.Cod_Admision=fef.cod_admision " +
			"AND fn.cod_enc_fact_fk=fef.cod_enc_factura" +
			"AND fn.consecutivo='"+fact+"'");

	java.sql.ResultSet rs=null;
	Statement st = null;
	try{
		Conexion con=new Conexion();
		st = con.conn.createStatement();
		rs=st.executeQuery("SELECT fn.consecutivo,CONCAT('1') AS Reclamacion,fdf.cod_programa,fdf.cantidad,CONCAT(fdf.valor) AS ValorUnitario,CONCAT(fef.valor) AS ValorFactura" +
				" FROM fact_enc_factura fef,fact_det_factura fdf,furips_datos ffd,fact_numeradas fn" +
				" WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
				"AND ffd.Cod_Admision=fef.cod_admision " +
				"AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
				"AND fn.consecutivo='"+fact+"'");
	}catch(Exception ex){
		System.out.println("Error en MetodoFurips>>F2 "+ex);
	}	
	return rs;
	}



	/***Consulta rips US USUARIOS*****/	
	public java.sql.ResultSet US(String fi, String ff,String CodEnt,String f){	
		/**
		 */
		/*System.out.println("USUARIOSSSSSS");
    	System.out.println("SELECT distinct ap.tipo_documento,ap.numero_documento,aen.cod_prestador,IF(aen.regimen = 'Contributivo','1',IF(aen.regimen = 'Subsidiado','2','1')) AS regimen,ap.primer_apellido,ap.segundo_apellido,ap.nombre, " +
		           "if(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>0, " +
		           "concat(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0),'-1'), " +
		           "concat(MONTH(CURDATE()) - MONTH(ap.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(ap.fecha_nacimiento,'%d'),-1,0),'-2')) as EdadYUnd,IF(ap.genero = 'Femenino','F',IF(ap.genero = 'Masculino','M','')) AS Genero, " +
		           "ade.dept_codigo,amc.mun_codigo,if(ap.zona_residencial='Urbana','U',if(ap.zona_residencial='Rural','R','')) as ZResidencial " +
		           "FROM adm_paciente ap,adm_entidad aen,adm_departamento ade,adm_municipio amc,fact_enc_factura fef, " +
		           "adm_admisiones aad,adm_paciente apc,adm_convenio acv,fact_numeradas fnu " +
		           "WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
		           "AND aad.adm_numero_ingreso=fef.cod_admision " +
		           "AND fnu.cod_enc_fact_fk=fef.cod_enc_factura " +
		           "AND fef.cod_eps=aen.ent_nit " +
		           "AND ade.dept_codigo=amc.dept_codigo_fk " +
		           "AND amc.muni_cod=ap.mun_codigo_fk " +
		           "AND apc.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
		           "AND apc.conv_numero_contrato_fk=acv.conv_numero_contrato " +
		           "AND acv.ent_nit_contratante_fk=aen.ent_nit " +
		           "AND fef.fecha_ingreso BETWEEN '"+fi+"' AND '"+ff+"' and aen.ent_nit="+CodEnt+" group by ap.pac_codigo_paciente ");

		
		*/
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT distinct ap.tipo_documento,ap.numero_documento,aen.cod_prestador,IF(aen.regimen = 'Contributivo','1',IF(aen.regimen = 'Subsidiado','2','1')) AS regimen,ap.primer_apellido,ap.segundo_apellido,ap.nombre, " +
        			           "IF(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>1 " +
        			           ",CONCAT(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento),'-1')" +
        			           "	,IF(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(ap.fecha_nacimiento,'%d'),-1,0)>1 " +
        			           "	,CONCAT(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento),'-2')" +
        			           "	,IF(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(ap.fecha_nacimiento,'%d'),-1,0)<0 " +
        			           "	,CONCAT(MONTH(CURDATE())+12-MONTH(ap.fecha_nacimiento),'-2') " +
        			           "	,IF(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(ap.fecha_nacimiento,'%d'),-1,0)=1 " +
        			           "	,IF(CURDATE()-ap.fecha_nacimiento>30 " +
        			           "	,CONCAT('1','-2')" +
        			           "	,CONCAT(CURDATE()-ap.fecha_nacimiento,'-3')" +
        			           "	)" +
        			           "	,IF(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)=1 " +
        			           "	,CONCAT('1','-1')" +
        			           "	,CONCAT(CURDATE()-ap.fecha_nacimiento,'-3')" +
        			           "	) " +
        			           "	) " +
        			           "	) " +
        			           "    ) " +
        			           "    ) AS EdadYUnd,IF(ap.genero = 'Femenino','F',IF(ap.genero = 'Masculino','M','')) AS Genero, " +
        			           "ade.dept_codigo,amc.mun_codigo,if(ap.zona_residencial='Urbana','U',if(ap.zona_residencial='Rural','R','')) as ZResidencial " +
        			           "FROM adm_paciente ap,adm_entidad aen,adm_departamento ade,adm_municipio amc,fact_enc_factura fef, " +
        			           "adm_admisiones aad,adm_paciente apc,adm_convenio acv,fact_numeradas fnu " +
        			           "WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
        			           "AND aad.adm_numero_ingreso=fef.cod_admision " +
        			           "AND fnu.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_eps=aen.ent_nit " +
        			           "AND ade.dept_codigo=amc.dept_codigo_fk " +
        			           "AND amc.muni_cod=ap.mun_codigo_fk " +
        			           "AND apc.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
        			           "AND apc.conv_numero_contrato_fk=acv.conv_numero_contrato " +
        			           "AND fnu.consecutivo='"+f+"' group by ap.pac_codigo_paciente ");
        	System.out.println("SELECT distinct ap.tipo_documento,ap.numero_documento,aen.cod_prestador,IF(aen.regimen = 'Contributivo','1',IF(aen.regimen = 'Subsidiado','2','1')) AS regimen,ap.primer_apellido,ap.segundo_apellido,ap.nombre, " +
			           "IF(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>0,  " +
			           "CONCAT(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0),'-1'), " +
			           "IF(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>0, "+
			           "CONCAT(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0),'-2'), "+
			           "CONCAT(DAY(CURDATE()) - DAY(ap.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(ap.fecha_nacimiento,'%d'),-1,0),'-3'))) AS EdadYUnd,IF(ap.genero = 'Femenino','F',IF(ap.genero = 'Masculino','M','')) AS Genero, " +
			           "ade.dept_codigo,amc.mun_codigo,if(ap.zona_residencial='Urbana','U',if(ap.zona_residencial='Rural','R','')) as ZResidencial " +
			           "FROM adm_paciente ap,adm_entidad aen,adm_departamento ade,adm_municipio amc,fact_enc_factura fef, " +
			           "adm_admisiones aad,adm_paciente apc,adm_convenio acv,fact_numeradas fnu " +
			           "WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
			           "AND aad.adm_numero_ingreso=fef.cod_admision " +
			           "AND fnu.cod_enc_fact_fk=fef.cod_enc_factura " +
			           "AND fef.cod_eps=aen.ent_nit " +
			           "AND ade.dept_codigo=amc.dept_codigo_fk " +
			           "AND amc.muni_cod=ap.mun_codigo_fk " +
			           "AND apc.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
			           "AND apc.conv_numero_contrato_fk=acv.conv_numero_contrato " +
			           "AND fnu.consecutivo='"+f+"' group by ap.pac_codigo_paciente ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>US "+ex);
        }	
        return rs;
    }
	
	
	

	/***Consulta rips AP PROCEDIMIENTOS*****/	
	public java.sql.ResultSet AP(String fi, String ff, String f, String Tdig){	
		/**
		 */
		System.out.println("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
		"fef.num_autorizacion,fpr.cod_cups,IF(aad.aurg = '0' && aad.ahosp = '0','1',IF(aad.aurg = '1' && aad.ahosp = '0','2',IF(aad.aurg = '0' && aad.ahosp = '1','3',''))) AS ambito, " +
		"'1' AS finalidad,IF(sdp.profesion = 'Administracion','',IF(sdp.profesion = 'Especialista','1',IF(sdp.profesion = 'Medico General','2',IF(sdp.profesion = 'Enfermera','3','4' )))) AS PerAtiende," +
		"hde.codDiagnostico AS codDiag,'' AS dxrela,'' AS complicacion,IF(fdf.formaactoqx>2 AND fdf.formaactoqx<14 ,'2',IF(fdf.formaactoqx>13 AND fdf.formaactoqx<18 ,'3',IF(fdf.formaactoqx>17 AND fdf.formaactoqx<38 ,'4',IF(fdf.formaactoqx>37,'5','')))) AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor " +
		" FROM fact_numeradas fnu,fact_enc_factura fef,empresa emp,adm_admisiones aad,adm_paciente ap,fact_det_factura fdf,seg_usuario su,seg_datos_personales sdp,fact_programas fpr,fact_rips fri,hic_diagnosticoegreso hde " +
		" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura " +
		" AND fnu.consecutivo = '"+f+"' " +
						" AND fef.cod_admision = aad.adm_numero_ingreso AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk " +
						" AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk " +
						" AND fri.cod_rip = fpr.archivo_rip AND fri.cod_rip = 5 AND fef.cod_admision = aad.adm_numero_ingreso AND fnu.cod_enc_fact_fk = fdf.cod_enc_factura_fk " +
						" AND fdf.cod_medico = su.usu_codigo AND su.dat_codigo_fk = sdp.dat_codigo AND hde.codAdm=fef.cod_admision " +
						"and hde.codigo='"+Tdig+"' order by fpr.descripcion");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
        			"fef.num_autorizacion,fpr.cod_cups,IF(aad.aurg = '0' && aad.ahosp = '0','1',IF(aad.aurg = '1' && aad.ahosp = '0','2',IF(aad.aurg = '0' && aad.ahosp = '1','3',''))) AS ambito, " +
        			"'1' AS finalidad,IF(sdp.profesion = 'Administracion','',IF(sdp.profesion = 'Especialista','1',IF(sdp.profesion = 'Medico General','2',IF(sdp.profesion = 'Enfermera','3','4' )))) AS PerAtiende," +
        			"hde.codDiagnostico AS codDiag,'' AS dxrela,'' AS complicacion,IF(fdf.formaactoqx>2 AND fdf.formaactoqx<14 ,'2',IF(fdf.formaactoqx>13 AND fdf.formaactoqx<18 ,'3',IF(fdf.formaactoqx>17 AND fdf.formaactoqx<38 ,'4',IF(fdf.formaactoqx>37,'5','')))) AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,empresa emp,adm_admisiones aad,adm_paciente ap,fact_det_factura fdf,seg_usuario su,seg_datos_personales sdp,fact_programas fpr,fact_rips fri,hic_diagnosticoegreso hde " +
        			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura " +
        			" AND fnu.consecutivo = '"+f+"' " +
        							" AND fef.cod_admision = aad.adm_numero_ingreso AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk " +
        							" AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk " +
        							" AND fri.cod_rip = fpr.archivo_rip AND fri.cod_rip = 5 AND fef.cod_admision = aad.adm_numero_ingreso AND fnu.cod_enc_fact_fk = fdf.cod_enc_factura_fk " +
        							" AND fdf.cod_medico = su.usu_codigo AND su.dat_codigo_fk = sdp.dat_codigo AND hde.codAdm=fef.cod_admision " +
        							"and hde.codigo='"+Tdig+"' order by fpr.descripcion"); 
        	

        	/*System.out.println("AP SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
        			"aad.numero_autorizacion,fpr.cod_cups,IF(aad.aurg = '0' && aad.ahosp = '0','1',IF(aad.aurg = '1' && aad.ahosp = '0','2',IF(aad.aurg = '0' && aad.ahosp = '1','3',''))) AS ambito, " +
        			"'1' AS finalidad,IF(sdp.profesion = 'Administracion','',IF(sdp.profesion = 'Especialista','1',IF(sdp.profesion = 'Medico General','2',IF(sdp.profesion = 'Enfermera','3','4' )))) AS PerAtiende," +
        			"hde.codDiagnostico AS codDiag,'' AS dxrela,'' AS complicacion,fdf.formaactoqx,fdf.valor " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,empresa emp,adm_admisiones aad,adm_paciente ap,fact_det_factura fdf,seg_usuario su,seg_datos_personales sdp,fact_programas fpr,fact_rips fri,hic_diagnosticoegreso hde " +
        			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura " +
        			" AND fnu.consecutivo = '"+f+"' " +
        					" AND fef.fecha_ingreso BETWEEN '"+fi+"' AND '"+ff+"' " +
        							" AND fef.cod_admision = aad.adm_numero_ingreso AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk " +
        							" AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk " +
        							" AND fri.cod_rip = fpr.archivo_rip AND fri.cod_rip = 5 AND fef.cod_admision = aad.adm_numero_ingreso AND fnu.cod_enc_fact_fk = fdf.cod_enc_factura_fk " +
        							" AND fdf.cod_medico = su.usu_codigo AND su.dat_codigo_fk = sdp.dat_codigo AND hde.codAdm=fef.cod_admision " +
        							" AND hde.TipDiag='EGH' GROUP BY fpr.cod_cups ");
*/
       
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AP "+ex);
        }	
        return rs;
    }
	

public java.sql.ResultSet AP1(String fi, String ff, String f){	
		/**
		 *///System.out.println("APpppppppppppp");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
               		rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
            			"fef.num_autorizacion,fpr.cod_cups,IF(aad.aurg = '0' && aad.ahosp = '0','1',IF(aad.aurg = '1' && aad.ahosp = '0','2',IF(aad.aurg = '0' && aad.ahosp = '1','3',''))) AS ambito, " +
            			"'1' AS finalidad,IF(sdp.profesion = 'Administracion','',IF(sdp.profesion = 'Especialista','1',IF(sdp.profesion = 'Medico General','2',IF(sdp.profesion = 'Enfermera','3','4' )))) AS PerAtiende," +
            			" ' ' AS codDiag,'' AS dxrela,'' AS complicacion,IF(fdf.formaactoqx>2 AND fdf.formaactoqx<14 ,'2',IF(fdf.formaactoqx>13 AND fdf.formaactoqx<18 ,'3',IF(fdf.formaactoqx>17 AND fdf.formaactoqx<38 ,'4',IF(fdf.formaactoqx>37,'5','')))) AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor " +
            			" FROM fact_numeradas fnu,fact_enc_factura fef,empresa emp,adm_admisiones aad,adm_paciente ap,fact_det_factura fdf,seg_usuario su,seg_datos_personales sdp,fact_programas fpr,fact_rips fri " +
            			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura " +
            			" AND fnu.consecutivo = '"+f+"' " +
            							" AND fef.cod_admision = aad.adm_numero_ingreso AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk " +
            							" AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk " +
            							" AND fri.cod_rip = fpr.archivo_rip AND fri.cod_rip = 5 AND fef.cod_admision = aad.adm_numero_ingreso AND fnu.cod_enc_fact_fk = fdf.cod_enc_factura_fk " +
            							" AND fdf.cod_medico = su.usu_codigo AND su.dat_codigo_fk = sdp.dat_codigo " +
            							" order by fpr.descripcion");
        	

        	/*System.out.println("AP SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
        			"aad.numero_autorizacion,fpr.cod_cups,IF(aad.aurg = '0' && aad.ahosp = '0','1',IF(aad.aurg = '1' && aad.ahosp = '0','2',IF(aad.aurg = '0' && aad.ahosp = '1','3',''))) AS ambito, " +
        			"'1' AS finalidad,IF(sdp.profesion = 'Administracion','',IF(sdp.profesion = 'Especialista','1',IF(sdp.profesion = 'Medico General','2',IF(sdp.profesion = 'Enfermera','3','4' )))) AS PerAtiende," +
        			"hde.codDiagnostico AS codDiag,'' AS dxrela,'' AS complicacion,fdf.formaactoqx,fdf.valor " +
        			" FROM fact_numeradas fnu,fact_enc_factura fef,empresa emp,adm_admisiones aad,adm_paciente ap,fact_det_factura fdf,seg_usuario su,seg_datos_personales sdp,fact_programas fpr,fact_rips fri,hic_diagnosticoegreso hde " +
        			" WHERE fnu.cod_enc_fact_fk = fef.cod_enc_factura " +
        			" AND fnu.consecutivo = '"+f+"' " +
        					" AND fef.fecha_ingreso BETWEEN '"+fi+"' AND '"+ff+"' " +
        							" AND fef.cod_admision = aad.adm_numero_ingreso AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk " +
        							" AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk " +
        							" AND fri.cod_rip = fpr.archivo_rip AND fri.cod_rip = 5 AND fef.cod_admision = aad.adm_numero_ingreso AND fnu.cod_enc_fact_fk = fdf.cod_enc_factura_fk " +
        							" AND fdf.cod_medico = su.usu_codigo AND su.dat_codigo_fk = sdp.dat_codigo AND hde.codAdm=fef.cod_admision " +
        							" AND hde.TipDiag='EGH' GROUP BY fpr.cod_cups ");
*/
       
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AP "+ex);
        }	
        return rs;
    }
	/***Consulta rips AH HOSPITALIZACION*****/	
	public java.sql.ResultSet AH(String fi, String ff, String f){	
		/**
		 */
	/*	System.out.println("SELECT DISTINCT fnu.consecutivo,emp.codprestador,ap.tipo_documento, " +
				"ap.numero_documento,fef.tipo AS ViaIngreso,DATE_FORMAT(aad.fecha_registro, '%d/%m/%Y') AS fecha_registro, " +
				"DATE_FORMAT(aad.hora_registro, '%H:%i') AS hora_registro,aad.numero_autorizacion,hce.cod_causa_ext AS CauExt, " +
				"(SELECT hdi.codDiagnostico FROM hic_diagnosticoingreso hdi,fact_numeradas fnu,adm_admisiones aad,fact_enc_factura fef WHERE hdi.codAdm = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_admision = aad.adm_numero_ingreso LIMIT 1 ) AS dxing, " +
						"(SELECT GROUP_CONCAT(hde.codDiagnostico SEPARATOR '_') AS dxegr FROM hic_diagnosticoegreso hde,fact_numeradas fnu,adm_admisiones aad,fact_enc_factura fef WHERE hde.codAdm = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_admision = aad.adm_numero_ingreso ) AS dxegr, " +
								" '' AS DiagComplicacion, IF(hdp.EstadoSalida = 'VIVO', '1', '2') AS EstSalida, IF(hdp.EstadoSalida <> 'VIVO','','') AS DiagMuerte, " +
								" DATE_FORMAT(hdp.fecha, '%d/%m/%Y') AS fecha_egr,  hdp.hora AS hora_egr " +
								" FROM fact_numeradas fnu, empresa emp, adm_paciente ap, adm_admisiones aad, " +
								" fact_enc_factura fef, hic_destinopaciente hdp, hic_causa_externa hce  " +
								" WHERE fef.cod_admision = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' " +
										" AND fef.fecha_ingreso AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente AND hdp.codAdm = aad.adm_numero_ingreso " +
										" AND aad.pac_codigo_paciente_fk = hdp.codPac AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND hdp.cau_ext = hce.codigo AND hdp.DestinoPaciente = 'SALIDA DE HOSPITALIZACION' " +
										" GROUP BY aad.adm_numero_ingreso ");
		*/
		//System.out.println("AH");
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT fnu.consecutivo,emp.codprestador,ap.tipo_documento, " +
    				"ap.numero_documento,fef.tipo AS ViaIngreso,DATE_FORMAT(aad.fecha_registro, '%d/%m/%Y') AS fecha_registro, " +
    				"DATE_FORMAT(aad.hora_registro, '%H:%i') AS hora_registro,fef.num_autorizacion,hce.cod_causa_ext AS CauExt, " +
    				"(SELECT hdi.codDiagnostico FROM hic_diagnosticoingreso hdi,fact_numeradas fnu,adm_admisiones aad,fact_enc_factura fef WHERE hdi.codAdm = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_admision = aad.adm_numero_ingreso LIMIT 1 ) AS dxing, " +
    						"(SELECT GROUP_CONCAT(hde.codDiagnostico SEPARATOR '_') AS dxegr FROM hic_diagnosticoegreso hde,fact_numeradas fnu,adm_admisiones aad,fact_enc_factura fef WHERE hde.codAdm = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_admision = aad.adm_numero_ingreso ) AS dxegr, " +
    								" '' AS DiagComplicacion, IF(hdp.EstadoSalida = 'VIVO', '1', '2') AS EstSalida, IF(hdp.EstadoSalida <> 'VIVO','','') AS DiagMuerte, " +
    								" DATE_FORMAT(hdp.fecha, '%d/%m/%Y') AS fecha_egr, DATE_FORMAT( hdp.hora , '%H:%i') AS hora_egr " +
    								" FROM fact_numeradas fnu, empresa emp, adm_paciente ap, adm_admisiones aad, " +
    								" fact_enc_factura fef, hic_destinopaciente hdp, hic_causa_externa hce  " +
    								" WHERE fef.cod_admision = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' " +
    										" AND fef.fecha_ingreso AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente AND hdp.codAdm = aad.adm_numero_ingreso " +
    										" AND aad.pac_codigo_paciente_fk = hdp.codPac AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND hdp.cau_ext = hce.codigo AND hdp.DestinoPaciente = 'SALIDA DE HOSPITALIZACION' " +
    										" GROUP BY aad.adm_numero_ingreso ");
        	
        	
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AH "+ex);
        }	
        return rs;
    }
	

	/***Consulta rips AU URGENCIAS****/	
	public java.sql.ResultSet AU(String fi, String ff, String f){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento, " +
        			" DATE_FORMAT(aad.fecha_registro, '%d/%m/%Y') AS fecha_registro,DATE_FORMAT(aad.hora_registro, '%H:%i') AS fecha_registro, " +
        			" fef.num_autorizacion,hce.cod_causa_ext AS CauExt,(SELECT hde.codDiagnostico FROM hic_diagnosticoegreso hde,fact_numeradas fnu,adm_admisiones aad,fact_enc_factura fef WHERE hde.codAdm = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_admision = aad.adm_numero_ingreso LIMIT 1 ) AS dxegr, " +
        					" '','','', IF(hdp.DestinoPaciente = 'SALIDA DE URGENCIA','1',IF(hdp.DestinoPaciente = 'REMISION A OTRA IPS','2','3')) AS DestSalida,  IF(hdp.EstadoSalida = 'VIVO', '1', '2') AS EstSalida, " +
        					" IF(hdp.EstadoSalida <> 'VIVO','-','') AS DiagMuerte, DATE_FORMAT(hdp.fecha, '%d/%m/%Y') AS fecha_egr, DATE_FORMAT(hdp.hora, '%H:%i') AS hora_egr " +
        					" FROM fact_numeradas fnu,empresa emp,adm_paciente ap,adm_admisiones aad,fact_enc_factura fef,hic_destinopaciente hdp, " +
        					" hic_causa_externa hce WHERE fef.cod_admision = aad.adm_numero_ingreso " +
        					" AND fnu.consecutivo = '"+f+"'  AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
        							"AND (hdp.DestinoPaciente = 'HOSPITALIZACION' OR hdp.DestinoPaciente = 'REMISION A OTRA IPS' OR hdp.DestinoPaciente = 'SALIDA DE URGENCIA' ) AND hdp.codAdm = aad.adm_numero_ingreso " +
        							"AND aad.pac_codigo_paciente_fk = hdp.codPac AND hdp.cau_ext = hce.codigo AND fnu.cod_enc_fact_fk=fef.cod_enc_factura " +
        							" GROUP BY aad.adm_numero_ingreso ");
        	
        /*	System.out.println("SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento, " +
        			" DATE_FORMAT(aad.fecha_registro, '%d/%m/%Y') AS fecha_registro,DATE_FORMAT(aad.hora_registro, '%H:%i') AS fecha_registro, " +
        			" aad.numero_autorizacion,hce.cod_causa_ext AS CauExt,(SELECT hde.codDiagnostico FROM hic_diagnosticoegreso hde,fact_numeradas fnu,adm_admisiones aad,fact_enc_factura fef WHERE hde.codAdm = aad.adm_numero_ingreso AND fnu.consecutivo = '"+f+"' AND fnu.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_admision = aad.adm_numero_ingreso LIMIT 1 ) AS dxegr, " +
        					" '','','', IF(hdp.DestinoPaciente = 'SALIDA DE URGENCIA','1',IF(hdp.DestinoPaciente = 'REMISION A OTRA IPS','2','3')) AS DestSalida,  IF(hdp.EstadoSalida = 'VIVO', '1', '2') AS EstSalida, " +
        					" IF(hdp.EstadoSalida <> 'VIVO','-','') AS DiagMuerte, DATE_FORMAT(hdp.fecha, '%d/%m/%Y') AS fecha_egr, DATE_FORMAT(hdp.hora, '%H:%i') AS hora_egr " +
        					" FROM fact_numeradas fnu,empresa emp,adm_paciente ap,adm_admisiones aad,fact_enc_factura fef,hic_destinopaciente hdp, " +
        					" hic_causa_externa hce WHERE fef.cod_admision = aad.adm_numero_ingreso " +
        					" AND fnu.consecutivo = '"+f+"'  AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
        							"AND (hdp.DestinoPaciente = 'HOSPITALIZACION' OR hdp.DestinoPaciente = 'REMISION A OTRA IPS' OR hdp.DestinoPaciente = 'SALIDA DE URGENCIA' ) AND hdp.codAdm = aad.adm_numero_ingreso " +
        							"AND aad.pac_codigo_paciente_fk = hdp.codPac AND hdp.cau_ext = hce.codigo AND fnu.cod_enc_fact_fk=fef.cod_enc_factura " +
        							" GROUP BY aad.adm_numero_ingreso ");
        	*/
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AC "+ex);
        }	
        return rs;
    }
	

	
	/***Consulta rips AN RECIEN NACIDOS****/	
	public java.sql.ResultSet AN(String fi, String ff, String f){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  fnu.consecutivo,emp.codprestador,'' AS TImadre, '' AS NImadre, '' AS FechaNaci, '' AS HoraNaci, '' AS EdadGest, '' AS ContParent, '' AS Genero, '' AS Peso, '' AS CodDiagRN, '' AS CausaMuerte, '' AS FechaMuerte, '' AS HoraMuerte FROM fact_numeradas fnu, fact_enc_factura ef, fact_det_factura df, fact_programas p,empresa emp WHERE fnu.consecutivo='"+f+"' AND  fnu.cod_enc_fact_fk=ef.cod_enc_factura AND df.cod_enc_factura_fk=ef.cod_enc_factura AND df.cod_programafk=p.cod_programa AND p.rn=1" );
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>AC "+ex);
        }	
        return rs;
    }
	
	
	
	public void CrearManualTarifario(String NombreManual){
		/**
		 * creamos los manuales tarifarios
		 */
		Tarifas t = new Tarifas();
		t.setManual(NombreManual);   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_manualestarifarios(NombreManual)values(?)");
			    ps.setString(1,t.getManual());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoCrearTarifas>>CrearManualTarifario "+ex);
			}
		}
		
		
	
	
	
	  public ResultSet listarArticulos(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	//cesar listar art pero de la tabla inv
	        	r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs, fact_manuales_tarifarios mt where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%' and p.manual_base=mt.manual_base and mt.cod_manual_tarifario='"+xx+"' ");
	        return r;
	    }
	
	 
	
	
	
	  
		public void ModificarTExistente(String sql){
			
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement(sql);
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProgramas>>ModificarPExistente "+ex);
			}

		}
	
		
		
			  	
		public void EliminarTarifa(String ct){
			
			try{
				PreparedStatement st = null;
				    Conexion con=new Conexion();                                                      
				  //  ps=con.conn.prepareStatement("insert into farc_proveedor(nit,municipio_fk,telefono,fax,email,contacto,clase_contribuyente,razon_social,direccion,comentario)values(?,?,?,?,?,?,?,?,?,?)");
				    st= con.conn.prepareStatement("delete from fact_tarifas where cod_tarifa ='"+ct+"' ");
			    	st.executeUpdate();
			    	st.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoProgramas>>EliminarTarifa "+ex);
			}

		}
		
		
		
		

		/**************************RIPS AGRUPADOS*******************************/
		
		
		
		
		public java.sql.ResultSet FacturasA(String eps, String fi, String ff){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT fn.cod_enc_fact_fk,fn.consecutivo FROM fact_numeradas fn, fact_enc_factura ef WHERE fn.cod_enc_fact_fk=ef.cod_enc_factura AND ef.tipo='6' AND ef.cod_eps='"+eps+"' AND ef.fecha BETWEEN '"+fi+"' AND '"+ff+"' order by fn.consecutivo ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>Facturas "+ex);
	        }	
	        return rs;
	    }
		
		
		
		
		
	
}
