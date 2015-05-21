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

public class MetodoRips {
	
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
        	rs=st.executeQuery(" SELECT fn.cod_enc_fact_fk,fn.consecutivo FROM fact_numeradas fn, fact_enc_factura ef WHERE fn.cod_enc_fact_fk=ef.cod_enc_factura AND ef.cod_eps='"+eps+"' AND ef.fecha BETWEEN '"+fi+"' AND '"+ff+"' order by fn.consecutivo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>Facturas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TodasFacturas(String fi, String ff){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT fn.cod_enc_fact_fk,fn.consecutivo FROM fact_numeradas fn, fact_enc_factura ef WHERE fn.cod_enc_fact_fk=ef.cod_enc_factura AND ef.fecha BETWEEN '"+fi+"' AND '"+ff+"' order by fn.consecutivo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>Facturas "+ex);
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
		
		System.out.println("ESTE ES EL METODO AC SELECT DISTINCT  fnu.consecutivo,  emp.codprestador,  SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,  " +
    			"DATE_FORMAT(aad.fecha_registro,'%d/%m/%Y') AS fecha ,  fef.num_autorizacion,  fpr.cod_cups,  " +
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
        	rs=st.executeQuery("SELECT DISTINCT  fnu.consecutivo,  emp.codprestador,  SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,  " +
        			"DATE_FORMAT(aad.fecha_registro,'%d/%m/%Y') AS fecha , fef.num_autorizacion,  fpr.cod_cups,  " +
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
			"  SUBSTRING(fef.documento FROM 4) AS numero_documento, (SELECT DATE_FORMAT(fef.fecha_ingreso, '%d/%m/%Y') AS fecha FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoegreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS fecha, " +
					" fef.num_autorizacion,fpr.cod_cups, hdp.fin_cons, hdp.cau_ext, " +
					" (SELECT codDiagnostico FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoegreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS codDiagnostico, " +
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
        			"  SUBSTRING(fef.documento FROM 4) AS numero_documento, (SELECT DATE_FORMAT(fef.fecha_ingreso, '%d/%m/%Y') AS fecha FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoegreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS fecha, " +
        					" fef.num_autorizacion,fpr.cod_cups, hdp.fin_cons, hdp.cau_ext, " +
        					" (SELECT codDiagnostico FROM fact_numeradas fnu,fact_enc_factura fef,hic_diagnosticoegreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fnu.cod_enc_fact_fk = fef.cod_enc_factura AND fnu.consecutivo = '"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura limit 1 )AS codDiagnostico, " +
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
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento, " +
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
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento," +
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
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,fef.num_autorizacion,med.codigoMedicamento, " +
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
	
	




public java.sql.ResultSet DAC(String f){	
		/**
		 */
	
	System.out.println("ESTE ES EL METODO DAC  SELECT hdi.codigo "+
			"FROM hic_diagnosticoingreso hdi, fact_enc_factura fef, fact_numeradas fn "+
			"WHERE   hdi.codAdm=fef.cod_admision "+
			"AND fef.cod_enc_factura=fn.cod_enc_fact_fk  "+
			"AND   fn.consecutivo='"+f+"' order by hdi.codigo desc limit 1");
		//System.out.println("No c porq entramos aqui AP2");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdi.codigo "+
    				"FROM hic_diagnosticoingreso hdi, fact_enc_factura fef, fact_numeradas fn "+
    				"WHERE   hdi.codAdm=fef.cod_admision "+
    				"AND fef.cod_enc_factura=fn.cod_enc_fact_fk  "+
    				"AND   fn.consecutivo='"+f+"' order by hdi.codigo desc limit 1");
    	        	
        	
        	

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoRips>>DAC "+ex);
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
        	rs=st.executeQuery("SELECT distinct SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,aen.cod_prestador,IF(aen.regimen = 'Contributivo','1',IF(aen.regimen = 'Subsidiado','2','1')) AS regimen,ap.primer_apellido,ap.segundo_apellido,ap.nombre, " +
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
		System.out.println("SELECT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
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
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
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
               		rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION, " +
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
        	rs=st.executeQuery("SELECT DISTINCT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento,fef.tipo AS ViaIngreso,DATE_FORMAT(aad.fecha_registro, '%d/%m/%Y') AS fecha_registro, " +
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
        	rs=st.executeQuery("SELECT fnu.consecutivo,emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING(fef.documento FROM 4) AS numero_documento, " +
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
		
		public java.sql.ResultSet EncabezadosA(String eps){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT enca_facturafk FROM fact_facturasagrupadas, fact_numeradas WHERE fact_numeradafk=cod_fact_numerada AND consecutivo='"+eps+"'  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>EncabezadosA "+ex);
	        }	
	        return rs;
	    }
		
		
		/***Consulta rips US USUARIOS*****/	
		public java.sql.ResultSet USA(String f){	

	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT distinct ap.tipo_documento,ap.numero_documento,aen.cod_prestador,IF(aen.regimen = 'Contributivo','1',IF(aen.regimen = 'Subsidiado','2','1')) AS regimen,ap.primer_apellido,ap.segundo_apellido,ap.nombre, " +
	        			           "IF(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>0,  " +
	        			           "CONCAT(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0),'-1'), " +
	        			           "IF(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>0, "+
	        			           "CONCAT(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0),'-2'), "+
	        			           "CONCAT(DAY(CURDATE()) - DAY(ap.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(ap.fecha_nacimiento,'%d'),-1,0),'-3'))) AS EdadYUnd,IF(ap.genero = 'Femenino','F',IF(ap.genero = 'Masculino','M','')) AS Genero, " +
	        			           "ade.dept_codigo,amc.mun_codigo,if(ap.zona_residencial='Urbana','U',if(ap.zona_residencial='Rural','R','')) as ZResidencial " +
	        			           "FROM adm_paciente ap,adm_entidad aen,adm_departamento ade,adm_municipio amc,fact_enc_factura fef, " +
	        			           "adm_admisiones aad,adm_paciente apc,adm_convenio acv " +
	        			           "WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
	        			           "AND aad.adm_numero_ingreso=fef.cod_admision " +
	        			           "AND fef.cod_eps=aen.ent_nit " +
	        			           "AND ade.dept_codigo=amc.dept_codigo_fk " +
	        			           "AND amc.muni_cod=ap.mun_codigo_fk " +
	        			           "AND apc.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
	        			           "AND apc.conv_numero_contrato_fk=acv.conv_numero_contrato " +
	        			           "AND fef.cod_enc_factura='"+f+"' group by ap.pac_codigo_paciente ");
	        	System.out.println("**************** SELECT distinct ap.tipo_documento,ap.numero_documento,aen.cod_prestador,IF(aen.regimen = 'Contributivo','1',IF(aen.regimen = 'Subsidiado','2','1')) AS regimen,ap.primer_apellido,ap.segundo_apellido,ap.nombre, " +
				           "IF(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>0,  " +
				           "CONCAT(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0),'-1'), " +
				           "IF(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0)>0, "+
				           "CONCAT(MONTH(CURDATE())-MONTH(ap.fecha_nacimiento)+IF(DATE_FORMAT(CURDATE(),'%m-%d')<DATE_FORMAT(ap.fecha_nacimiento,'%m-%d'),-1,0),'-2'), "+
				           "CONCAT(DAY(CURDATE()) - DAY(ap.fecha_nacimiento) + IF(DATE_FORMAT(CURDATE(),'%d')<DATE_FORMAT(ap.fecha_nacimiento,'%d'),-1,0),'-3'))) AS EdadYUnd,IF(ap.genero = 'Femenino','F',IF(ap.genero = 'Masculino','M','')) AS Genero, " +
				           "ade.dept_codigo,amc.mun_codigo,if(ap.zona_residencial='Urbana','U',if(ap.zona_residencial='Rural','R','')) as ZResidencial " +
				           "FROM adm_paciente ap,adm_entidad aen,adm_departamento ade,adm_municipio amc,fact_enc_factura fef, " +
				           "adm_admisiones aad,adm_paciente apc,adm_convenio acv " +
				           "WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
				           "AND aad.adm_numero_ingreso=fef.cod_admision " +
				           "AND fef.cod_eps=aen.ent_nit " +
				           "AND ade.dept_codigo=amc.dept_codigo_fk " +
				           "AND amc.muni_cod=ap.mun_codigo_fk " +
				           "AND apc.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
				           "AND apc.conv_numero_contrato_fk=acv.conv_numero_contrato " +
				           "AND fef.cod_enc_factura='"+f+"' group by ap.pac_codigo_paciente ");
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>USA "+ex);
	        }	
	        return rs;
	    }//fin us
		
		
		/***Consulta rips AM MEDICAMENTOS*****/	
		public java.sql.ResultSet AMA(String fi, String ff, String f){	
			System.out.println("AMA");
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,fef.num_autorizacion,med.codigoMedicamento, " +
	        			"IF(med.clasificacion = 'Pos', '1', '2') AS clasificacion,REPLACE(LEFT(med.nombre,30),',', '.') NomGenerico, ffc.forma_farmaceutica, " +
	        			"REPLACE((med.concentracion),',','.') concentracion,  '' unidad,  fdf.cantidad,  fdf.valor,  (fdf.cantidad * fdf.valor) AS ValorTotal " +
	        			"FROM fact_enc_factura fef,empresa emp,fact_det_factura fdf,adm_admisiones aad,adm_paciente ap,fact_programas fpr,fact_prog_med fpm,medicamentos med,farc_formafarmaceutica ffc,fact_numeradas fnu,fact_facturasagrupadas ffa " +
	        			"WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk AND aad.adm_numero_ingreso = fef.cod_admision AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
	        			"AND fpr.cod_programa=fdf.cod_programafk AND fpm.cod_prog = fpr.cod_programa AND med.codigo = fpm.cod_med AND fpr.archivo_rip=9  AND ffc.codigo = med.cod_formaFK AND fef.cod_enc_factura = ffa.enca_facturafk  AND ffa.fact_numeradafk=fnu.cod_fact_numerada " +
	        			"AND fef.cod_enc_factura='"+f+"' ");
	        	
	        	
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
	        	System.out.println("Error en MetodoRips>>AMA"+ex);
	        }	
	        return rs;
	    }//fin am
		
		
		public java.sql.ResultSet DACA(String f){	
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT hdi.codigo FROM hic_diagnosticoingreso hdi, fact_enc_factura fef " +
	        			"WHERE   hdi.codAdm=fef.cod_admision AND   fef.cod_enc_factura='"+f+"' ORDER BY hdi.codigo DESC LIMIT 1");

	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>DACA "+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet AC1A(String fi, String ff, String f,String tdiag){	
			/**
			 */
		System.out.println("");
		
			System.out.println("AC1");
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT DISTINCT  '"+ff+"',emp.codprestador,SUBSTRING_INDEX(fef.documento,'-',1) AS tipo_documento, SUBSTRING_INDEX(fef.documento,'-',-1) AS numero_documento " +
	        			",(SELECT DATE_FORMAT(hdi.fecha, '%d/%m/%Y') AS fecha FROM fact_enc_factura fef,hic_diagnosticoingreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fef.cod_enc_factura   ='"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura LIMIT 1 )AS fecha,  " +
	        			" fef.num_autorizacion,fdf.cod_programa, hdp.fin_cons, hdp.cau_ext " +
	        			",(SELECT codDiagnostico FROM fact_enc_factura fef,hic_diagnosticoingreso hdi,fact_det_factura fdf,fact_programas fpr WHERE  fef.cod_enc_factura ='"+f+"' AND hdi.codAdm=fef.cod_admision AND fdf.cod_enc_factura_fk = fef.cod_enc_factura AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 GROUP BY fdf.cod_det_factura LIMIT 1 )AS codDiagnostico,  " +
	        			"'','','',hdp.tip_diag,fdf.valor,fef.moderacion,(fdf.cantidad * fdf.valor) AS valor_neto,fdf.cantidad  " +
	        			"FROM fact_enc_factura fef,empresa emp,fact_det_factura fdf,fact_programas fpr,hic_destinopaciente hdp  " +
	        			"WHERE fef.cod_enc_factura='"+f+"' AND fdf.cod_enc_factura_fk = fef.cod_enc_factura  " +
	        			"AND fpr.cod_programa = fdf.cod_programafk  AND fpr.archivo_rip = 4 " +
	        			"AND hdp.codAdm=fef.cod_admision  ");

	           }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>AC1A "+ex);
	        }	
	        return rs;
	    }
		
		
		/***Consulta rips AC CONSULTAS*****/	
		public java.sql.ResultSet ACA(String fi, String ff, String f){	
			/**
			 */
			
			System.out.println("AC");
			    	
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT DISTINCT '"+ff+"',emp.codprestador,ap.tipo_documento,ap.numero_documento,DATE_FORMAT(hdi.fecha,'%d/%m/%Y') AS fecha, fef.num_autorizacion,fdf.cod_programa,'' fin_cons,  '' cau_ext,  '','','','',  '',  fdf.valor,  fef.moderacion, (fdf.cantidad * fdf.valor) AS valor_neto, fdf.cantidad " +
	        			"FROM fact_enc_factura fef,empresa emp,adm_paciente ap,adm_admisiones aad,fact_det_factura fdf, fact_programas fpr,hic_diagnosticoingreso hdi " +
	        			"WHERE fef.cod_enc_factura='"+f+"'  AND aad.adm_numero_ingreso = fef.cod_admision  " +
	        			"AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  AND fdf.cod_enc_factura_fk = fef.cod_enc_factura  " +
	        			" AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip = 4 " +
	        			"  AND hdi.codAdm=fef.cod_admision ");
	        	
	             }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>AC "+ex);
	        }	
	        return rs;
	    }///fin ACA
		
		
		/***Consulta rips AT OTROS SERVICIOS*****/	
		public java.sql.ResultSet ATA(String fi, String ff, String f){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT '"+ff+"',emp.codprestador,ap.tipo_documento,ap.numero_documento,fef.num_autorizacion, " +
	        			"IF(fdf.clase_servicio = 'HONORARIOS','4', " +
	        			"  IF(fdf.clase_servicio = 'SERVICIOS Y TRASLADOS','2', " +
	        			"    IF(fdf.clase_servicio = 'ESTANCIA','3','1') " +
	        			"  ) " +
	        			") AS clase_servicio, " +
	        			"fdf.cod_programa,REPLACE(LEFT(fdf.nombre_programa, 60),',','.')  AS nombre_programa,fdf.cantidad,  " +
	        			"fdf.valor,(fdf.cantidad * fdf.valor) AS ValorTotal  " +
	        			"FROM fact_enc_factura fef,fact_det_factura fdf,fact_programas fpr,empresa emp,adm_admisiones aad,adm_paciente ap  " +
	        			"WHERE fef.cod_enc_factura= '"+f+"' AND fef.cod_enc_factura = fdf.cod_enc_factura_fk  " +
	        			"AND aad.adm_numero_ingreso = fef.cod_admision  " +
	        			"AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente  " +
	        			"AND fpr.cod_programa=fdf.cod_programafk  " +
	        			"AND fpr.archivo_rip=11 AND (fpr.clase_servicio!=9 AND fpr.clase_servicio!=10)");
	        	
	        	System.out.println(" SELECT '"+ff+"',emp.codprestador,ap.tipo_documento,ap.numero_documento,fef.num_autorizacion, " +
	        			"IF(fdf.clase_servicio = 'HONORARIOS','4', " +
	        			"  IF(fdf.clase_servicio = 'SERVICIOS Y TRASLADOS','2', " +
	        			"    IF(fdf.clase_servicio = 'ESTANCIA','3','1') " +
	        			"  ) " +
	        			") AS clase_servicio, " +
	        			"fdf.cod_programa,REPLACE(LEFT(fdf.nombre_programa, 60),',','.')  AS nombre_programa,fdf.cantidad,  " +
	        			"fdf.valor,(fdf.cantidad * fdf.valor) AS ValorTotal  " +
	        			"FROM fact_enc_factura fef,fact_det_factura fdf,fact_programas fpr,empresa emp,adm_admisiones aad,adm_paciente ap  " +
	        			"WHERE fef.cod_enc_factura= '"+f+"' AND fef.cod_enc_factura = fdf.cod_enc_factura_fk  " +
	        			"AND aad.adm_numero_ingreso = fef.cod_admision  " +
	        			"AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente  " +
	        			"AND fpr.cod_programa=fdf.cod_programafk  " +
	        			"AND fpr.archivo_rip=11 AND (fpr.clase_servicio!=9 AND fpr.clase_servicio!=10)");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ATA "+ex);
	        }	
	        return rs;
	    }//fin ata
		
		
		/***Consulta rips AU URGENCIAS****/	
		public java.sql.ResultSet AUA(String fi, String ff, String f){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT fnu.consecutivo,emp.codprestador,ap.tipo_documento,ap.numero_documento,DATE_FORMAT(aad.fecha_registro, '%d/%m/%Y') AS fecha_registro,DATE_FORMAT(aad.hora_registro, '%H:%i') AS fecha_registro, " +
	        			"fef.num_autorizacion,hce.cod_causa_ext AS CauExt,(SELECT hde.codDiagnostico FROM fact_enc_factura fef,adm_admisiones aad,hic_diagnosticoegreso hde WHERE fef.cod_enc_factura='"+f+"' AND hde.codAdm = aad.adm_numero_ingreso AND fef.cod_admision = aad.adm_numero_ingreso LIMIT 1) AS dxegr " +
	        			",'','','', IF(hdp.DestinoPaciente = 'SALIDA DE URGENCIA','1',IF(hdp.DestinoPaciente = 'REMISION A OTRA IPS','2','3')) AS DestSalida,  " +
	        			"IF(hdp.EstadoSalida = 'VIVO', '1', '2') AS EstSalida, " +
	        			"IF(hdp.EstadoSalida <> 'VIVO','-','') AS DiagMuerte, DATE_FORMAT(hdp.fecha, '%d/%m/%Y') AS fecha_egr, " +
	        			"DATE_FORMAT(hdp.hora, '%H:%i') AS hora_egr " +
	        			"FROM fact_numeradas fnu,empresa emp,fact_enc_factura fef,adm_admisiones aad,adm_paciente ap,hic_destinopaciente hdp, hic_causa_externa hce,fact_facturasagrupadas ffa  " +
	        			"WHERE fef.cod_admision = aad.adm_numero_ingreso AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
	        			"AND hdp.codAdm = aad.adm_numero_ingreso AND aad.pac_codigo_paciente_fk = hdp.codPac AND (hdp.DestinoPaciente = 'HOSPITALIZACION' OR hdp.DestinoPaciente = 'REMISION A OTRA IPS' OR hdp.DestinoPaciente = 'SALIDA DE URGENCIA' ) " +
	        			"AND hdp.cau_ext = hce.codigo AND fef.cod_enc_factura = ffa.enca_facturafk  AND ffa.fact_numeradafk=fnu.cod_fact_numerada " +
	        			"AND fef.cod_enc_factura='"+f+"' " +
	        			"GROUP BY aad.adm_numero_ingreso ");
	        	
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
		
		
		public java.sql.ResultSet DAPA(String f){	
			/**
			 */
			System.out.println(" SELECT hde.codigo,hde.TipDiag  " +
					"FROM hic_diagnosticoegreso hde, fact_enc_factura fef  " +
					"WHERE   hde.codAdm=fef.cod_admision  " +
					"AND   fef.cod_enc_factura ='"+f+"' order by hde.codigo desc limit 1");
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT hde.codigo,hde.TipDiag  " +
					"FROM hic_diagnosticoegreso hde, fact_enc_factura fef  " +
					"WHERE   hde.codAdm=fef.cod_admision  " +
					"AND   fef.cod_enc_factura ='"+f+"' order by hde.codigo desc limit 1");
	        	

	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>DAPA "+ex);
	        }	
	        return rs;
	    }//fin dapa
		
		
		/***Consulta rips AP PROCEDIMIENTOS*****/	
		public java.sql.ResultSet APA(String fi, String ff, String f, String Tdig){	
			/**
			 */
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT '"+ff+"',emp.codprestador,ap.tipo_documento,ap.numero_documento," +
	        			"DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION,fef.num_autorizacion,fpr.cod_cups," +
	        			"IF(aad.aurg = '0' && aad.ahosp = '0','1',IF(aad.aurg = '1' && aad.ahosp = '0','2',IF(aad.aurg = '0' && aad.ahosp = '1','3',''))) AS ambito," +
	        			"'1' AS finalidad,IF(sdp.profesion = 'Administracion','',IF(sdp.profesion = 'Especialista','1',IF(sdp.profesion = 'Medico General','2',IF(sdp.profesion = 'Enfermera','3','4' )))) AS PerAtiende," +
	        			"hde.codDiagnostico AS codDiag,'' AS dxrela,'' AS complicacion,IF(fdf.formaactoqx>2 AND fdf.formaactoqx<14 ,'2',IF(fdf.formaactoqx>13 AND fdf.formaactoqx<18 ,'3',IF(fdf.formaactoqx>17 AND fdf.formaactoqx<38 ,'4',IF(fdf.formaactoqx>37,'5','')))) AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor  " +
	        			"FROM fact_enc_factura fef,empresa emp,adm_admisiones aad,adm_paciente ap,fact_det_factura fdf,fact_programas fpr,seg_usuario su,seg_datos_personales sdp,hic_diagnosticoegreso hde  " +
	        			"WHERE  fef.cod_enc_factura='"+f+"' AND fef.cod_admision = aad.adm_numero_ingreso  " +
	        			"AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  " +
	        			"AND fdf.cod_enc_factura_fk = fef.cod_enc_factura  " +
	        			"AND fpr.cod_programa = fdf.cod_programafk  " +
	        			"AND fdf.cod_medico = su.usu_codigo  " +
	        			"AND su.dat_codigo_fk = sdp.dat_codigo  " +
	        			"AND fpr.archivo_rip='5' AND hde.codAdm=fef.cod_admision AND hde.codigo='"+Tdig+"' "); 
	        	


	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>APA "+ex);
	        }	
	        return rs;
	    }//fin apa
		
		public java.sql.ResultSet APA2(String fi, String ff, String f){	
			/**
			 */
			System.out.println("No c porq entramos aqui AP2 pero traemos "+ff+"  :  "+f);
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT '"+ff+"',emp.codprestador,ap.tipo_documento,ap.numero_documento," +
	        			"DATE_FORMAT(fdf.fecha_realizacion,'%d/%m/%Y') fecha_realizacion,fef.num_autorizacion,fpr.cod_cups,'1' AS ambito," +
	        			"'1' AS finalidad,'5' AS perAtiende,'' AS codDiag,'' AS codDiagRela,'' AS complicacion,'' AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor  " +
	        			"FROM fact_enc_factura fef,fact_det_factura fdf,empresa emp,adm_paciente ap,adm_admisiones aad,fact_programas fpr  " +
	        			"WHERE fef.cod_enc_factura='"+f+"' AND fef.cod_enc_factura = fdf.cod_enc_factura_fk  " +
	        			"AND aad.adm_numero_ingreso = fef.cod_admision  AND aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente  " +
	        			"AND fpr.cod_programa = fdf.cod_programafk AND fpr.archivo_rip!='5' " +
	        			"AND (fpr.clase_servicio = 9 OR fpr.clase_servicio = 10 )  ");
	        	
   	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>APA2 "+ex);
	        }	
	        return rs;
	    }//fin apa2
		
		
		public java.sql.ResultSet APA1(String fi, String ff, String f){	
			/**
			 *///System.out.println("APpppppppppppp");
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	               		rs=st.executeQuery("  SELECT '"+ff+"',emp.codprestador,ap.tipo_documento,ap.numero_documento, DATE_FORMAT(fdf.fecha_realizacion,'%d/%m%/%Y') AS FECHA_REALIZACION,fef.num_autorizacion," +
	               				"fpr.cod_cups,IF(aad.aurg = '0' && aad.ahosp = '0','1',IF(aad.aurg = '1' && aad.ahosp = '0','2',IF(aad.aurg = '0' && aad.ahosp = '1','3',''))) AS ambito,'1' AS finalidad," +
	               				"IF(sdp.profesion = 'Administracion','',IF(sdp.profesion = 'Especialista','1',IF(sdp.profesion = 'Medico General','2',IF(sdp.profesion = 'Enfermera','3','4' )))) AS PerAtiende " +
	               				",' ' AS codDiag,'' AS dxrela,'' AS complicacion,IF(fdf.formaactoqx>2 AND fdf.formaactoqx<14 ,'2',IF(fdf.formaactoqx>13 AND fdf.formaactoqx<18 ,'3',IF(fdf.formaactoqx>17 AND fdf.formaactoqx<38 ,'4',IF(fdf.formaactoqx>37,'5','')))) AS formaactoqx,(fdf.cantidad * fdf.valor) AS Valor  " +
	               				"FROM fact_enc_factura fef,empresa emp,adm_admisiones aad,adm_paciente ap,fact_det_factura fdf,fact_programas fpr,seg_usuario su,seg_datos_personales sdp  " +
	               				"WHERE fef.cod_enc_factura='"+f+"' AND fef.cod_admision = aad.adm_numero_ingreso AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  " +
	               				"AND fdf.cod_enc_factura_fk = fef.cod_enc_factura   AND fpr.cod_programa = fdf.cod_programafk  AND fdf.cod_medico = su.usu_codigo  " +
	               				"AND su.dat_codigo_fk = sdp.dat_codigo AND fpr.archivo_rip='5' ");
	       
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>APA1 "+ex);
	        }	
	        return rs;
	    }
		
		
		
































	
/************************************************************GLOSAS******************************************************************/
		



public java.sql.ResultSet GeneraSQL(String sql){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura,n.cod_fact_numerada FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk and n.estadoFact=2 "+sql+ " ORDER BY n.consecutivo");
	        	System.out.println(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura,n.cod_fact_numerada FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk and n.estadoFact!=5 AND n.estadoFact!=3 "+sql+ " ORDER BY n.consecutivo");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>GeneraSQL "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet DetalleFactura(String e){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT d.cod_det_factura,d.tipopop,d.cod_programa,d.nombre_programa,d.cod_paquete,d.nombre_paquete,d.clase_servicio,d.cantidad,d.valor,n.consecutivo " +
	        			"FROM fact_det_factura d,fact_numeradas n WHERE d.cod_enc_factura_fk="+e+" AND d.cod_enc_factura_fk=n.cod_enc_fact_fk  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>DetalleFactura "+ex);
	        }	
	        return rs;
	    }
		
		
		
		public java.sql.ResultSet DetalleFacturaGlosa(String e){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	//si se pone lento SELECT d.conceptog,d.conceptoe,d.valor,d.valorg,d.observacion,d.estado FROM fact_glosasdetalle d,fact_glosas g WHERE g.fact_numerada=34 AND g.encabezado=12353 AND g.codigo=d.cod_glosafk AND d.cod_det_facturafk=206 AND d.estado!=4
	        	rs=st.executeQuery(" SELECT valorg,estado FROM fact_glosasdetalle WHERE cod_det_facturafk="+e+" AND estado!=4 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>DetalleFactura "+ex);
	        }	
	        return rs;
	    }
		
		
		
		
		public java.sql.ResultSet DetalleFacturaD(String e){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete,nombre_paquete,clase_servicio,cantidad,valor FROM fact_det_factura WHERE cod_det_factura="+e+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>DetalleFactura "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet GlosasConceptoG(){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" select * from fact_glosaconceptogeneral ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>GlosasConceptoG "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet GlosasConceptoE(String x){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" 	SELECT e.* FROM fact_glosaconceptoespecifico e, fact_glosaconceptos c WHERE c.cod_conceptog="+x+" AND e.codigo=c.cod_conceptoe ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>GlosasConceptoE "+ex);
	        }	
	        return rs;
	    }
		
		
		
		public java.sql.ResultSet ConsultarCodGlosa(String x){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT codigo FROM fact_glosas WHERE fact_numerada="+x+"");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarCodGlosa "+ex);
	        }	
	        return rs;
	    }
		
		public void InsertarGlosa(String cfn, String enca){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_glosas(fact_numerada,encabezado)values(?,?)");
				    ps.setString(1,cfn);
				    ps.setString(2,enca);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarGlosa "+ex);
				}
			}
		
		
		
		public void InsertarDetalleGlosa(String codg, String cd,String v, String vg,String ccg,String cg,String cce,String ce,String o,String e,String u,String f,String h){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_glosasdetalle(cod_glosafk,cod_det_facturafk,valor,valorg,cod_conceptog,conceptog,cod_conceptoe,conceptoe,observacion,estado,usuario,fechanotificacion,horanotificacion)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,codg);
				    ps.setString(2,cd);
				    ps.setString(3,v);
				    ps.setString(4,vg);
				    ps.setString(5,ccg);
				    ps.setString(6,cg);
				    ps.setString(7,cce);
				    ps.setString(8,ce);
				    ps.setString(9,o);
				    ps.setString(10,e);
				    ps.setString(11,u);
				    ps.setString(12,f);
				    ps.setString(13,h);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarDetalleGlosa "+ex);
				}
			}
		
		
		public java.sql.ResultSet GlosaRevision(String x){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT conceptog,conceptoe,valor,valorg,observacion,estado,cod_glosafk FROM fact_glosasdetalle WHERE cod_det_facturafk="+x+" AND estado!=4 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>GlosaRevision "+ex);
	        }	
	        return rs;
	    }
		
		
		public void AnularNotificacion(String e, String u, String f, String h){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasdetalle SET estado='4', usuario='"+u+"',fechanotificacion='"+f+"',horanotificacion='"+h+"' where cod_det_facturafk='"+e+"' and estado!='4' ");
		     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>AnularNotificacion "+ex);
		     	ex.getMessage();	     
		     }	
		 }	
		
		
		public void AnularRespuesta(String e, String u, String f, String h){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasdetalle SET estado='0', usuariorespuesta='"+u+"',fecharespuesta='"+f+"',horarespuesta='"+h+"' where cod_det_facturafk='"+e+"' and estado!='4' ");
		     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>AnularNotificacion "+ex);
		     	ex.getMessage();	     
		     }	
		 }	
		
		public java.sql.ResultSet ConsultarNotificaciones(String sql){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, " +
	        			"ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura,n.cod_fact_numerada,g.codigo  " +
	        			"FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n,fact_glosas g " +
	        			"WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk " +
	        			"AND n.estadoFact!=5 AND n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=g.encabezado AND g.estado=0 " +
	        			""+sql+ " ORDER BY n.consecutivo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarNotificaciones "+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet ConsultarNotificacionesr(String sql){	
			/**
			 */
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, " +
	        			"ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura,n.cod_fact_numerada,g.codigo  " +
	        			"FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n,fact_glosas g " +
	        			"WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk " +
	        			"AND n.estadoFact!=5 AND n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=g.encabezado AND g.estado=1 " +
	        			""+sql+ " ORDER BY n.consecutivo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarNotificaciones "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet ConsultarNotificacionesrn(String sql){	
			/**
			 */System.out.println("....9999");
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, " +
	        			"ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura,n.cod_fact_numerada,g.codigo  " +
	        			"FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n,fact_glosas g " +
	        			"WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk " +
	        			"AND n.estadoFact!=5 AND n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=g.encabezado AND g.estado=9 " +
	        			""+sql+ " ORDER BY n.consecutivo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarNotificaciones "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet ConsultarNotificacionesf(String sql){	
			/**
			 */
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, " +
	        			"ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura,n.cod_fact_numerada,g.codigo,ef.valor,SUM(d.valorg),SUM(d.valorg-valorgfinal) AS valoraceptado   " +
	        			"FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n,fact_glosas g,fact_glosasdetalle d " +
	        			"WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk " +
	        			"AND n.estadoFact!=5 AND n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=g.encabezado AND g.estado=2 AND g.codigo=d.cod_glosafk " +
	        			""+sql+ " GROUP BY ef.cod_enc_factura ORDER BY n.consecutivo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarNotificaciones "+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet ConsultarNotificacionesPC(String sql){	
			/**
			 */
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, " +
	        			"ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura,n.cod_fact_numerada,g.codigo,ef.valor,SUM(dg.valorg)   " +
	        			"FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n,fact_glosas g,fact_glosasdetalle dg " +
	        			"WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk " +
	        			"AND n.estadoFact!=5 AND n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=g.encabezado AND g.estado=0 AND g.codigo=dg.cod_glosafk " +
	        			""+sql+ " GROUP BY g.codigo  ORDER BY n.consecutivo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarNotificacionesPC "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet ConsultarReportesG(String sql){	
			/**
			 */
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM fact_glosasenviadas WHERE codigo!=0 " +
	        			""+sql+ " ORDER BY codigo  LIMIT 500");
	        	System.out.println(" SELECT * FROM fact_glosasenviadas WHERE codigo!=0 " +
	        			""+sql+ " ORDER BY codigo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarReportesG "+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet ConsultarReportesGN(String sql){	
			/**
			 */
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM fact_glosasnotificadas WHERE codigo!=0 " +
	        			""+sql+ " ORDER BY codigo  LIMIT 500");
	        	System.out.println(" SELECT * FROM fact_glosasnotificadas WHERE codigo!=0 " +
	        			""+sql+ " ORDER BY codigo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarReportesGN "+ex);
	        }	
	        return rs;
	    }
		
		
		public void ResponderGlosadetalle(String e,String r,String u,String v,String c,String f,String h,String c2){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasdetalle SET estado='"+e+"',respuesta='"+r+"',usuariorespuesta='"+u+"',valorgfinal='"+v+"',fecharespuesta='"+f+"',horarespuesta='"+h+"' where cod_glosafk='"+c2+"' and cod_det_facturafk='"+c+"' ");
		     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>ResponderGlosadetalle "+ex);
		     	ex.getMessage();	     
		     }	
		 }	
		
		
		
		public java.sql.ResultSet Detallesactivos(String x){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM fact_glosasdetalle WHERE cod_glosafk="+x+" AND estado=0  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>Detallesactivos "+ex);
	        }	
	        return rs;
	    }
		
		
		public void FinalizarGlosa(String fr,String hr,String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosas SET estado=1,fecharespuestatotal='"+fr+"',horarespuestatotal='"+hr+"' where codigo='"+c+"' ");
		     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>FinalizarGlosa "+ex);
		     	ex.getMessage();	     
		     }	
		 }	
		
		
		public java.sql.ResultSet Glosasxdetalle(String x){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT g.codigo,g.estado,n.cod_fact_numerada,n.consecutivo,e.cod_eps,e.razon_social,e.valor,e.nombre_paciente  " +
	        			"FROM fact_glosas g,fact_numeradas n,fact_enc_factura e  " +
	        			"WHERE g.encabezado="+x+" AND g.encabezado=n.cod_enc_fact_fk AND g.encabezado=e.cod_enc_factura ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>Glosasxdetalle "+ex);
	        }	
	        return rs;
	    }
		
		
		public java.sql.ResultSet ConsultarGlosaNumerada(String x){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT g.codigo,g.fact_numerada,n.consecutivo,d.respuesta,e.valor,d.valor,d.valorg,d.estado,d.valorgfinal, CONCAT(d.conceptog,d.conceptoe), e.nit ,d.cod_det_facturafk,g.encabezado " +
	        			"FROM fact_glosas g,fact_glosasdetalle d,fact_numeradas n,fact_enc_factura e  " +
	        			"WHERE g.codigo="+x+" AND cod_glosafk=g.codigo AND d.estado!=4 AND g.fact_numerada=n.cod_fact_numerada AND g.encabezado=e.cod_enc_factura ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarGlosaNumerada "+ex);
	        }	
	        return rs;
	    }
		
		
		
		
		public void EnviarGlosa(String fr,String hr,String u,String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
		     	System.out.println(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>FinalizarGlosa "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
		public void EnviarNotificacion(String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosas SET estado=0 where codigo='"+c+"' ");
		     //	System.out.println(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>EnviarNotificacion "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		public void ActualizaDetalleNotifica(String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasdetalle SET estado=0 where cod_glosafk='"+c+"' ");
		     //	System.out.println(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>ActualizaDetalleNotifica "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		public void ActualizaGlosaNumerada(String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_numeradas SET estadoFact=3 where cod_fact_numerada='"+c+"' ");
		     	System.out.println(" UPDATE fact_numeradas SET estadoFact=3 where cod_fact_numerada='"+c+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>ActualizaGlosaNumerada "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
	
		public void MovimientoGlosa(String c, String e,String f,String u){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_movfacturas(codFactNumerada,estadoFact,fecha,usuario)values(?,?,?,?)");
				    System.out.println("insert into fact_movfacturas(codFactNumerada,estadoFact,fecha,usuario)values("+c+","+e+","+f+","+u+")");
				    ps.setString(1,c);
				    ps.setString(2,e);
				    ps.setString(3,f);
				    ps.setString(4,u);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>MovimientoGlosa "+ex);
				}
			}
		
		
		
		
		
		public void InsertarFormatoGlosa(String f, String h,String e,String u){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_glosasenviadas(fecha,hora,eps,usuario)values(?,?,?,?)");
				    ps.setString(1,f);
				    ps.setString(2,h);
				    ps.setString(3,e);
				    ps.setString(4,u);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarFormatoGlosa "+ex);
				}
			}
			
		
		public void InsertarFormatoGlosaNotificada(String f, String h,String u){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_glosasnotificadas(fecha,hora,usuario)values(?,?,?)");
				    ps.setString(1,f);
				    ps.setString(2,h);
				    ps.setString(3,u);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarFormatoGlosaNotificada "+ex);
				}
			}
			
		
		public java.sql.ResultSet ConsultarCodFormatoGlosa(String f, String h,String e,String u){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM fact_glosasenviadas WHERE fecha='"+f+"' and hora='"+h+"' and eps='"+e+"' and usuario='"+u+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarCodFormatoGlosa "+ex);
	        }	
	        return rs;
	    }
		
		public java.sql.ResultSet ConsultarCodFormatoGlosaN(String f, String h,String u){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM fact_glosasnotificadas WHERE fecha='"+f+"' and hora='"+h+"' and usuario='"+u+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarCodFormatoGlosaN "+ex);
	        }	
	        return rs;
	    }
		
		public void InsertarFormatoGlosaDetalle(String e,String g,String f,String r,String vf,String vc,String vg,String va,String det){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_glosasenviadasdetalle(cod_glosasenviadasfk,cod_glosafk,factura,repuesta,valorfactura,valorcargue,valorglosa,valoraceptado,cod_detalle_facturafk)values(?,?,?,?,?,?,?,?,?)");
				    System.out.println("insert into fact_glosasenviadasdetalle(cod_glosasenviadasfk,cod_glosafk,factura,repuesta,valorfactura,valorcargue,valorglosa,valoraceptado)values("+e+","+g+","+f+","+r+","+vf+","+vc+","+vg+","+va+")");
				    
				    ps.setString(1,e);
				    ps.setString(2,g);
				    ps.setString(3,f);
				    ps.setString(4,r);
				    ps.setString(5,vf);
				    ps.setString(6,vc);
				    ps.setString(7,vg);
				    ps.setString(8,va);
				    ps.setString(9,det);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarFormatoGlosaDetalle "+ex);
				}
			}
		
		
		public void InsertarFormatoGlosaNotificadaDetalle(String e,String g,String f,String vf,String vc,String vg){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_glosasnotificadassdetalle(cod_glosasnotificadasfk,cod_glosafk,factura,valorfactura,valorcargue,valorglosa)values(?,?,?,?,?,?)");
				   // System.out.println("insert into fact_glosasenviadasdetalle(cod_glosasenviadasfk,cod_glosafk,factura,repuesta,valorfactura,valorcargue,valorglosa,valoraceptado)values("+e+","+g+","+f+","+r+","+vf+","+vc+","+vg+","+va+")");
				    																		                          
				    ps.setString(1,e);
				    ps.setString(2,g);
				    ps.setString(3,f);
				    ps.setString(4,vf);
				    ps.setString(5,vc);
				    ps.setString(6,vg);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarFormatoGlosaNotificadaDetalle "+ex);
				}
			}
		
		
		public void ActualizaFormatoGlosa(String vf,String vg,String va,String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasenviadas SET valorfacturado='"+vf+"',valorglosado='"+vg+"',valoraceptado='"+va+"' where codigo='"+c+"' ");
		     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>ActualizaFormatoGlosa "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		public void ActualizaFormatoGlosaNotificada(String vf,String vg,String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasnotificadas SET valorfacturado='"+vf+"',valorglosado='"+vg+"' where codigo='"+c+"' ");
		     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>ActualizaFormatoGlosaNotificada "+ex);
		     	ex.getMessage();	     
		     }	
		 }
	
		public java.sql.ResultSet ConsultarValoresaCerrar(String c){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("  SELECT n.consecutivo,ef.valor,SUM(d.valorg),SUM(d.valorg-valorgfinal) AS valoraceptado, GROUP_CONCAT(d.conceptog,d.conceptoe) as Concepto " +
	        			" FROM fact_enc_factura ef,fact_numeradas n,fact_glosas g,fact_glosasdetalle d " +
	        			" WHERE g.codigo='"+c+"' AND ef.cod_enc_factura=n.cod_enc_fact_fk AND n.estadoFact!=5 AND n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=g.encabezado AND g.estado=2 AND g.codigo=d.cod_glosafk    ");
	        
	        	System.out.println("  SELECT n.consecutivo,ef.valor,SUM(d.valorg),SUM(d.valorg-valorgfinal) AS valoraceptado, GROUP_CONCAT(d.conceptog,d.conceptoe) as Concepto " +
	        			" FROM fact_enc_factura ef,fact_numeradas n,fact_glosas g,fact_glosasdetalle d " +
	        			" WHERE g.codigo='"+c+"' AND ef.cod_enc_factura=n.cod_enc_fact_fk AND n.estadoFact!=5 AND n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=g.encabezado AND g.estado=2 AND g.codigo=d.cod_glosafk    ");
	     
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarValoresaCerrar "+ex);
	        }	
	        return rs;
	    }
		
		
		
		public java.sql.ResultSet ConsultarValoresaFinalizar(String c){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT n.consecutivo,ef.valor,SUM(d.valorg), GROUP_CONCAT(d.conceptog,d.conceptoe) " +
	        			"FROM fact_glosas g,fact_glosasdetalle d,fact_enc_factura ef,fact_numeradas n " +
	        			"WHERE g.codigo='"+c+"' AND g.codigo=d.cod_glosafk  AND ef.cod_enc_factura=g.encabezado AND " +
	        			"n.cod_fact_numerada=g.fact_numerada AND ef.cod_enc_factura=n.cod_enc_fact_fk AND n.estadoFact!=5 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarValoresaFinalizar "+ex);
	        }	
	        return rs;
	    }
		
		
		public void InsertarFormatoGlosaConciliada(String f, String h,String u){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into fact_glosasconciliadas(fecha,hora,usuario)values(?,?,?)");
				    ps.setString(1,f);
				    ps.setString(2,h);
				    ps.setString(3,u);
				 	ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarFormatoGlosaConciliada "+ex);
				}
			}
		
		
		public java.sql.ResultSet ConsultarCodFormatoGlosaC(String f, String h,String u){	
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo FROM fact_glosasconciliadas WHERE fecha='"+f+"' and hora='"+h+"' and usuario='"+u+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarCodFormatoGlosaC "+ex);
	        }	
	        return rs;
	    }
		
		
		public void FinalizarConciliacion(String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosas SET estado=3 where codigo='"+c+"' ");
		     //	System.out.println(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>FinalizarConciliacion "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
		public void ActualizaDetalleConciliacion(String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasdetalle SET estado=6 where cod_glosafk='"+c+"' ");
		     //	System.out.println(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>ActualizaDetalleConciliacion "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
		public void InsertarFormatoGlosaConciliadaDetalle(String c,String g,String f,String vf,String vg,String va){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				   
				    ps=con.conn.prepareStatement("insert into fact_glosasconciliadassdetalle(cod_glosasconciliadasfk,cod_glosafk,factura,valorfactura,valorglosado,valoraplicado)values(?,?,?,?,?,?)");
				   // System.out.println("insert into fact_glosasenviadasdetalle(cod_glosasenviadasfk,cod_glosafk,factura,repuesta,valorfactura,valorcargue,valorglosa,valoraceptado)values("+e+","+g+","+f+","+r+","+vf+","+vc+","+vg+","+va+")");
				    																		                          
				    ps.setString(1,c);
				    ps.setString(2,g);
				    ps.setString(3,f);
				    ps.setString(4,vf);
				    ps.setString(5,vg);
				    ps.setString(6,va);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("ERROR EN MetodoRips>>InsertarFormatoGlosaConciliadaDetalle "+ex);
				}
			}
		
		
		public void ActualizaFormatoGlosaConciliada(String vf,String vg,String va,String c){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     																	//valorfacturado,valorglosado,
		     	st= con.conn.prepareStatement(" UPDATE fact_glosasconciliadas SET valorfacturado='"+vf+"',valorglosado='"+vg+"',valoraplicado='"+va+"' where codigo='"+c+"' ");
		     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>ActualizaFormatoGlosaConciliada "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
			public void FinalizarConciliacionydetalle(String c,String f,String h,String u){	
			PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement(" UPDATE fact_glosas SET estado=3,fecharespuestatotal='"+f+"',horarespuestatotal='"+h+"',fechafinal='"+f+"',horafinal='"+h+"',usuariofinal='"+u+"'  where codigo='"+c+"' ");
		     //	System.out.println(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoRips>>FinalizarConciliacionydetalle "+ex);
		     	ex.getMessage();	     
		     }	
		 }
		
		
		public void FinalizarConciliaciondetalleglosa(String c,String u,String g,String f,String h){	

			PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE fact_glosasdetalle SET respuesta='CONCILIACION GENERAL',usuariorespuesta='"+u+"',valorgfinal='"+g+"',fecharespuesta='"+f+"',horarespuesta='"+h+"'  where cod_glosafk='"+c+"' ");
	     //	System.out.println(" UPDATE fact_glosas SET estado=2,fechafinal='"+fr+"',horafinal='"+hr+"',usuariofinal='"+u+"' where codigo='"+c+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoRips>>FinalizarConciliaciondetalleglosa "+ex);
	     	ex.getMessage();	     
	     }	
	 }
		
		
		

public java.sql.ResultSet ConsultarReportesGC(String sql){	
			/**
			 */
			
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery(" SELECT * FROM fact_glosasconciliadas WHERE codigo!=0 " +
	        			""+sql+ " ORDER BY codigo  LIMIT 500");
	        	System.out.println(" SELECT * FROM fact_glosasconciliadas WHERE codigo!=0 " +
	        			""+sql+ " ORDER BY codigo  LIMIT 500");
	          }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoRips>>ConsultarReportesGC "+ex);
	        }	
	        return rs;
	    }
























	
}
