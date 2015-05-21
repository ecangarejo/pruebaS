package fact_metodo;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.swing.JProgressBar;
import fact_bean.Tarifas;
//import fact_controlador.Rectangle;
import adm_logica.Conexion;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import com.sun.org.apache.bcel.internal.classfile.Code; 

public class MetodoMovimientos {

	
/*
FUNCIONANDO
 
	public ResultSet listarPacientes(String texto) throws Exception {
		/**
		 */
		/*  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        																																																																
	        	r=st.executeQuery(" select a.adm_numero_ingreso, p.nombre, p.primer_apellido, p.segundo_apellido,p.pac_codigo_paciente , c.ent_nit_contratante_fk, e.nombre_entidad, fe.cod_enc_factura from adm_admisiones a, adm_paciente p, adm_convenio c, adm_entidad e, fact_enc_factura fe	where a.estado!='2' and a.atendido='0' and a.aurg='1' and a.ahosp='0' and a.pac_codigo_paciente_fk=p.pac_codigo_paciente and p.conv_numero_contrato_fk=c.conv_numero_contrato and c.ent_nit_contratante_fk=e.ent_nit and a.adm_numero_ingreso=fe.cod_admision and (p.nombre like '"+texto+"%' or p.primer_apellido like '"+texto+"%' or p.segundo_apellido like '"+texto+"%') ");
	        	//r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%'  ");
       	return r;
	}
*/
	public java.sql.ResultSet Dxingreso(String texto){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	       	rs=st.executeQuery(" SELECT c.* FROM hic_diagnosticoingreso i,cie10 c WHERE i.codAdm='"+texto+"' AND i.CodDiag_fk=c.codigo  ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>DXingreso "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet Dxegreso(String texto){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	       	rs=st.executeQuery(" SELECT c.* FROM hic_diagnosticoegreso  e,cie10 c WHERE e.codAdm='"+texto+"' AND e.CodDiag_fk=c.codigo    ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>DXegreso "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet Destinop(String texto){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	       	rs=st.executeQuery(" SELECT d.DestinoPaciente,d.EstadoSalida,f.cod_finconsulta,f.finalidad_consulta ,c.cod_causa_ext,c.causa_externa,IF(d.tip_diag=1,'Impresion Diagnostica',IF(d.tip_diag=2,'Confirmado Nuevo','Confirmado Repetido')) AS tipodx " +
	       			"FROM hic_destinopaciente d, hic_finalidad_consulta f, hic_causa_externa c " +
	       			"WHERE codAdm='"+texto+"' AND d.fin_cons=f.codigo AND d.cau_ext=c.codigo   ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>Destinop "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet finconsulta(){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	       	rs=st.executeQuery(" SELECT * FROM hic_finalidad_consulta  ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>finconsulta "+ex);
	    }	
	    return rs;
	}
	
	
	public java.sql.ResultSet causaext(){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	       	rs=st.executeQuery(" SELECT * FROM hic_causa_externa  ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>causaext "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet CodigoCIE(String texto){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	       	rs=st.executeQuery(" SELECT * FROM cie10 WHERE codigoCIE='"+texto+"'  ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>CodigoCIE "+ex);
	    }	
	    return rs;
	}
	
	public void InsertarDxi(String a,String b,String c,String d,String e,String f,String g,String h){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    
		    if(h.equals("1")){
		    ps=con.conn.prepareStatement("INSERT INTO hic_diagnosticoingreso(codDiagnostico,codUsu,hora,fecha,codAdm,codPac,CodDiag_fk) VALUES (?,?,?,?,?,?,?)");
		    ps.setString(1,a);
		    ps.setString(2,b);		
		    ps.setString(3,c);	
		    ps.setString(4,d);
		    ps.setString(5,e);
		    ps.setString(6,f);
		    ps.setString(7,g);
		 	ps.executeUpdate();
		    }
		    if(h.equals("2")){
			    ps=con.conn.prepareStatement("INSERT INTO hic_diagnosticoegreso(codDiagnostico,codUsu,hora,fecha,codAdm,codPac,TipDiag,CodDiag_fk) VALUES (?,?,?,?,?,?,?,?)");
			    ps.setString(1,a);
			    ps.setString(2,b);		
			    ps.setString(3,c);	
			    ps.setString(4,d);
			    ps.setString(5,e);
			    ps.setString(6,f);
			    ps.setString(7,"EG");
			    ps.setString(8,g);
			 	ps.executeUpdate();
			}
		    
		    
		    
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
	    	System.out.println("ERROR EN. MetodoMovimientos>>InsertarDxi "+ex);
		}
	}
	
	public void InsertarDestino(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    
		    ps=con.conn.prepareStatement("INSERT INTO hic_destinopaciente(DestinoPaciente,codUsu,hora,fecha,codAdm,codPac,EstadoSalida,fin_cons,cau_ext,tip_diag) VALUES (?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,a);
		    ps.setString(2,b);		
		    ps.setString(3,c);	
		    ps.setString(4,d);
		    ps.setString(5,e);
		    ps.setString(6,f);
		    ps.setString(7,g);
		    ps.setString(8,h);
		    ps.setString(9,i);
		    ps.setString(10,j);
		 	ps.executeUpdate();
		   
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
	    	System.out.println("ERROR EN. MetodoMovimientos>>InsertarDestino "+ex);
		}
	}
	
	public void ActualizarEncabezadoaeliminar(String e){	
		System.out.println("ActualizarEncabezado "+e);
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE fact_enc_factura SET estado='3' where cod_enc_factura='"+e+"'  ");
	     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarEncabezado "+ex);
	     	ex.getMessage();	     
	     }	
	 }	
	
	public ResultSet listarPacientes(String texto) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	r=st.executeQuery(" SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura,fe.fecha_ingreso,fe.fecha_egreso,fe.num_autorizacion  FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='1' AND a.ahosp='0' AND a.adm_numero_ingreso=fe.cod_admision AND fe.nombre_paciente LIKE '%"+texto+"%' limit 100");
		 	   	//r=st.executeQuery(" select a.adm_numero_ingreso, p.nombre, p.primer_apellido, p.segundo_apellido,p.pac_codigo_paciente , c.ent_nit_contratante_fk, e.nombre_entidad, fe.cod_enc_factura from adm_admisiones a, adm_paciente p, adm_convenio c, adm_entidad e, fact_enc_factura fe	where a.estado!='2' and a.atendido='0' and a.aurg='1' and a.ahosp='0' and a.pac_codigo_paciente_fk=p.pac_codigo_paciente and p.conv_numero_contrato_fk=c.conv_numero_contrato and c.ent_nit_contratante_fk=e.ent_nit and a.adm_numero_ingreso=fe.cod_admision and (p.nombre like '"+texto+"%' or p.primer_apellido like '"+texto+"%' or p.segundo_apellido like '"+texto+"%') ");
	        	//r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%'  ");
       	return r;
}
	
	public java.sql.ResultSet FactxCentrosP(String sql, String fi, String ff, String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk AND ef.tipo="+sql+" AND ef.fecha BETWEEN '"+fi+"' AND '"+ff+"'  "+eps);
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQL "+ex);
        }	
        return rs;
    }


	
/*
public ResultSet listarPacientesh(String texto) throws Exception {
	/**
	 */
	/*  java.sql.ResultSet r=null;
        Statement st = null;
       Conexion con=new Conexion();
        	st = con.conn.createStatement();
        																																																																
        	r=st.executeQuery(" select a.adm_numero_ingreso, p.nombre, p.primer_apellido, p.segundo_apellido,p.pac_codigo_paciente , c.ent_nit_contratante_fk, e.nombre_entidad, fe.cod_enc_factura from adm_admisiones a, adm_paciente p, adm_convenio c, adm_entidad e, fact_enc_factura fe	where a.estado!='2' and a.atendido='0' and a.ahosp='1' and a.pac_codigo_paciente_fk=p.pac_codigo_paciente and p.conv_numero_contrato_fk=c.conv_numero_contrato and c.ent_nit_contratante_fk=e.ent_nit and a.adm_numero_ingreso=fe.cod_admision and (p.nombre like '"+texto+"%' or p.primer_apellido like '"+texto+"%' or p.segundo_apellido like '"+texto+"%') GROUP BY a.adm_numero_ingreso ");
        	//r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%'  ");
   	return r;
}
	
*/


	public ResultSet listarPacientesh(String texto, String t) throws Exception {
		/**
		 *///System.out.println("TTTTTTTTTT  "+t);
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if(t.equals("0")){
	        	r=st.executeQuery(" SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura,fe.fecha_ingreso,fe.fecha_egreso,fe.num_autorizacion  FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND fe.tipo='2' AND fe.estado='0' AND fe.nombre_paciente LIKE '%"+texto+"%' GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente limit 100 "); 																																																															
	        	System.out.println(" SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura,fe.fecha_ingreso,fe.fecha_egreso,fe.num_autorizacion  FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND fe.tipo='2' AND fe.estado='0' AND fe.nombre_paciente LIKE '%"+texto+"%' GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente limit 100 "); 																																																															
	        	
	        	}
	        	if(t.equals("1")){
	            r=st.executeQuery(" SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura,fe.fecha_ingreso,fe.fecha_egreso,fe.num_autorizacion  FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.tipo='3' or fe.tipo=4) AND fe.estado='0' AND fe.nombre_paciente LIKE '%"+texto+"%' GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente limit 100 "); 																																																															
	            System.out.println(" SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura,fe.fecha_ingreso,fe.fecha_egreso,fe.num_autorizacion  FROM adm_admisiones a, fact_enc_factura fe WHERE a.estado!='2' AND a.atendido='0' AND a.aurg='0' AND a.ahosp='1' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.tipo='3' or fe.tipo=4) AND fe.estado='0' AND fe.nombre_paciente LIKE '%"+texto+"%' GROUP BY fe.razon_social,a.adm_numero_ingreso  ORDER BY fe.nombre_paciente limit 100 "); 																																																															
	            
	        	}
	        	//r=st.executeQuery(" select a.adm_numero_ingreso, p.nombre, p.primer_apellido, p.segundo_apellido,p.pac_codigo_paciente , c.ent_nit_contratante_fk, e.nombre_entidad, fe.cod_enc_factura from adm_admisiones a, adm_paciente p, adm_convenio c, adm_entidad e, fact_enc_factura fe	where a.estado!='2' and a.atendido='0' and a.ahosp='1' and a.pac_codigo_paciente_fk=p.pac_codigo_paciente and p.conv_numero_contrato_fk=c.conv_numero_contrato and c.ent_nit_contratante_fk=e.ent_nit and a.adm_numero_ingreso=fe.cod_admision and (p.nombre like '"+texto+"%' or p.primer_apellido like '"+texto+"%' or p.segundo_apellido like '"+texto+"%') GROUP BY a.adm_numero_ingreso ");
	        	//r=st.executeQuery(" select p.cod_programa, p.descripcion, p.especialidad, e.descripcion, p.clase_servicio, cs.descripcion from fact_programas p, fact_especialidades e, fact_clases_servicio cs where p.especialidad=e.cod_especialidad and p.clase_servicio=cs.cod_clase_servicio and p.descripcion like '"+texto+"%'  ");
	   	return r;
	}
	
	public java.sql.ResultSet obtenerInfoEncaDuplica(String codAdmision,String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' and fef.cod_eps='"+eps+"' and fef.estado='0' ORDER BY  fef.cod_enc_factura DESC LIMIT 1");	
        	//rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE cod_enc_factura='"+codAdmision+"' ");	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerInfoEncaDuplica "+ex);
        }	
        return rs;
    }




	
public ResultSet listarPacientesCE(String texto) throws Exception {
	/**
	 */
	  java.sql.ResultSet r=null;
        Statement st = null;
       Conexion con=new Conexion();
        	st = con.conn.createStatement();
                            	
	
        	r=st.executeQuery("SELECT a.numero_ingreso,p.nombre,p.primer_apellido,p.segundo_apellido,p.pac_codigo_paciente,c.ent_nit_contratante_fk,e.nombre_entidad,fe.cod_enc_factura,a.tipo " +
        			          "FROM adm_ambulatorio a,adm_paciente p,adm_convenio c,adm_entidad e,fact_enc_factura fe " +
        			          "WHERE a.estado != '2' " +
        			          "AND a.estado != '3' " +
        			          "AND a.atendido = '0' " +
        			          "AND a.pac_codigo_paciente_fk = p.pac_codigo_paciente " +
        			          "AND p.conv_numero_contrato_fk = c.conv_numero_contrato " +
        			          "AND c.ent_nit_contratante_fk = e.ent_nit " +
        			          "AND a.numero_ingreso = fe.cod_admision " +
        			          "AND ( p.nombre LIKE '"+texto+"%' OR p.primer_apellido LIKE '"+texto+"%' OR p.segundo_apellido LIKE '"+texto+"%'  ) limit 100");
   
     return r;
}
	

////////// MULTIPLES PACIENTES //////

public java.sql.ResultSet EmpresasDiferentes(String ent){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT ent_nit, nombre_entidad FROM adm_entidad WHERE  ent_nit!='"+ent+"%' ORDER BY nombre_entidad ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>Empresas "+ex);
    }	
    return rs;
}

public java.sql.ResultSet BuscarEntidadNueva(String ceps){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT * FROM adm_entidad WHERE ent_nit="+ceps+"");	
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>InfoEncaxEntidad "+ex);
    }	
    return rs;
}

public java.sql.ResultSet InfoEncaxEntidad(String codAdmision, String eps){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' AND cod_eps='"+eps+"' AND cod_usuario_fk IS NULL AND fecha IS NULL AND hora IS NULL");	
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>InfoEncaxEntidad "+ex);
    }	
    return rs;
}


public java.sql.ResultSet movSinFacturar(String codAdmision,String eps){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT fdf.cod_det_factura,fef.cod_enc_factura,fdf.tipopop,IF(fdf.cod_paquete<>'NULL',fdf.cod_paquete,fdf.cod_programa),IF(fdf.nombre_paquete<>'NULL',fdf.nombre_paquete,fdf.nombre_programa) nombre, " +
    			           "fdf.cod_paquete,fdf.nombre_paquete,fdf.fecha_realizacion,fdf.cantidad,SUM(fdf.valor),CONCAT(sdp.nombre,' ',sdp.apellido) usuario,fdf.clase_servicio,fdf.hora " +
    			           "FROM fact_det_factura fdf, fact_enc_factura fef,seg_usuario su,seg_datos_personales sdp " +
    			           "WHERE fef.cod_admision='"+codAdmision+"' AND fef.cod_eps='"+eps+"' " +
    			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
    			           "AND fdf.cod_usuario=su.usu_codigo " +
    			           "AND su.dat_codigo_fk=sdp.dat_codigo  AND fdf.facturado!=5 "  +
    			           "AND fdf.cod_det_factura NOT IN (SELECT fdf.cod_det_factura " +
    			           "FROM fact_det_factura fdf, fact_enc_factura fef " +
    			           "WHERE fef.cod_admision='"+codAdmision+"' AND fef.cod_eps='"+eps+"' " +
    			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
    			           //"AND fef.cod_enc_factura=fn.cod_enc_fact_fk " +
    			           "AND (fdf.facturado='1' or fdf.facturado='2' or  fdf.facturado='5')) " +
    			           "GROUP BY fdf.hora " +
    			           "ORDER BY fdf.cod_det_factura");
    		
      }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>movSinFacturar "+ex);
    }	
    return rs;
}


public ResultSet BuscarCodFactNumerada(String fecha,String enca,String nf){
		
		//System.out.println(" AsignarNumFact fecha "+fecha+" enca "+enca+" nf "+nf);
		
		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();													
        	rs=st.executeQuery("select cod_fact_numerada from fact_numeradas where cod_enc_fact_fk='"+enca+"' and consecutivo='"+nf+"' and fecha='"+fecha+"' ");
        	
        }catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>BuscarCodFactNumerada"+ex);
			}
        	 return rs;
		}


	public java.sql.ResultSet FactEncabezado(String cod, String lot){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select cod_enc_factura,cod_eps,razon_social from fact_enc_factura where cod_admision='"+cod+"' and cod_eps='"+lot+"'");
        	System.out.println(" select cod_enc_factura,cod_eps,razon_social from fact_enc_factura where cod_admision='"+cod+"' and cod_eps='"+lot+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>FactEncabezado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet EstanciaAmb(String xx){	
		System.out.println("SELECT a.fecha_registro FROM adm_ambulatorio a WHERE a.numero_ingreso = '"+xx+"' ");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT a.fecha_registro FROM adm_ambulatorio a WHERE a.numero_ingreso = '"+xx+"' ");
        	

          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>EstanciaUrg "+ex);
        }	
        return rs;
    }





///INICIO PROCESO DE DEVOLUCION

	public java.sql.ResultSet FactEst168(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso<>NULL,'Sin alta',fef.fecha_egreso) AS 'f.egreso', " +
        			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am " +
        			           "WHERE (fn.estadoFact='8' or fn.estadoFact='6' or fn.estadoFact='1') " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_eps=ae.ent_nit " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod"+sql+" ORDER BY fn.consecutivo ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>FactEst168 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet Fact2Est168(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso<>NULL,'Sin alta',fef.fecha_egreso) AS 'f.egreso', " +
        			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am, fact_facturas_enviadas ffe " +
        			           "WHERE (fn.estadoFact='8' or fn.estadoFact='6' or fn.estadoFact='1') " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_eps=ae.ent_nit " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod  AND ffe.codFact=fn.cod_fact_numerada"+sql+" ORDER BY fn.consecutivo ");
        	
        	System.out.println("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso<>NULL,'Sin alta',fef.fecha_egreso) AS 'f.egreso', " +
			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact " +
			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am, fact_facturas_enviadas ffe " +
			           "WHERE (fn.estadoFact='8' or fn.estadoFact='6' or fn.estadoFact='1') " +
			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
			           "AND fef.cod_eps=ae.ent_nit " +
			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
			           "AND p.mun_codigo_fk=am.muni_cod  AND ffe.codFact=fn.cod_fact_numerada"+sql);
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>Fact2Est168 "+ex);
        }	
        return rs;
    }



	public java.sql.ResultSet BuscarMotivo(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_tipodevoluciones order by descripcion");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarMotivo "+ex);
        }	
        return rs;
    }


	public java.sql.ResultSet BuscarNotasCredito(String consecutivo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf WHERE cdf.cod_fact_fk=cf.codigo AND cf.numero_factura='"+consecutivo+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarNotasCredito "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet BuscarNotasCredito2(String conse){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura='"+conse+"' ");
        	System.out.println("SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura='"+conse+"' ");         

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarNotasCredito2 "+ex);
        }	
        return rs;
    }

public void asignarCuentaDev(String valor,String valorLetra,String cod_usuario,String fecha, String motivo, String ri){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    if(ri.equals("1")){															
				ps=con.conn.prepareStatement("INSERT INTO fact_factdev(valor,valorLetra,cod_usuario,fecha,motivo) VALUES(?,?,?,?,?)");
				System.out.println("Valor de Motivo en insercion de factdev "+motivo);
			    ps.setString(1,valor);
			    ps.setString(2,valorLetra);
			    ps.setString(3,cod_usuario);
			    ps.setString(4,fecha);
			    ps.setString(5,motivo);
			 	ps.executeUpdate();
				ps.close();
			    }
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>asignarCuentaDev "+ex);
			}
		}



public java.sql.ResultSet obtenerConsecutivoCR(String ri){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ri.equals("0")){	
        	rs=st.executeQuery("SELECT consRadicado FROM fact_factradicadas ORDER BY consRadicado DESC LIMIT 1");
        	}
        	if(ri.equals("1")){	
            rs=st.executeQuery("SELECT consRadicado FROM fact_factradicadasi ORDER BY consRadicado DESC LIMIT 1");
            }
        	
        	if(ri.equals("2")){	
        		rs=st.executeQuery("SELECT consDev FROM fact_factDev ORDER BY consDev DESC LIMIT 1");
        	}
        	   
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerConsecutivoCR "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet BuscarEstadoFact(String Codfact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT estadoFact from fact_numeradas where cod_fact_numerada='"+Codfact+"' ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarEstadoFact "+ex);
        }	
        return rs;
    }


public void asignarCDevFact(String consCC,String consFact,String Fecha){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_facturas_devueltas(consDev,codFact,fechadev) VALUES (?,?,?)");
		    ps.setString(1,consCC);
		    ps.setString(2,consFact);		
		    ps.setString(3, Fecha);
		    
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoMovimientos>>asignarCDevFact "+ex);
		}
	}




public void actualizarEstFactR(String codFactNum,String ea){	
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	if(ea.equals("0")){				
	     	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='2' WHERE cod_fact_numerada='"+codFactNum+"'");
	     	}
	     	if(ea.equals("1")){				
		    st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='6' WHERE cod_fact_numerada='"+codFactNum+"'");
		    //System.out.print("UPDATE fact_numeradas SET estadoFact='6' WHERE cod_fact_numerada='"+codFactNum+"'");
	     	}
	     	if(ea.equals("2")){				
		     	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='8' WHERE cod_fact_numerada='"+codFactNum+"'");
		     }
	     	if(ea.equals("3")){				
		     	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='0' WHERE cod_fact_numerada='"+codFactNum+"'");
		     	//System.out.println("UPDATE fact_numeradas SET estadoFact='0' WHERE cod_fact_numerada='"+codFactNum+"'");
		     }

	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>actualizarEstFact "+ex);
	     	ex.getMessage();	     
	     }	
	 }




public void recordEstadoFactDEV(String codFact,String estado,String fecha,String usuario,String motivo, String consecutivo){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_movfacturas(codFactNumerada,estadoFact,fecha,usuario,Observacion,cod_Devfk) VALUES (?,?,?,?,?,?)");
		    System.out.print("ya se inserttaron los mov");
		    ps.setString(1,codFact);
		    ps.setString(2,estado);		
		    ps.setString(3,fecha);	
		    ps.setString(4,usuario);
		    ps.setString(5,motivo);
		    ps.setString(6,consecutivo);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();		
			System.out.println("Ingresando datos a fact_movfacturas");
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoMovimientos>>recordEstadoFactDev "+ex);
		}
	}


public java.sql.ResultSet BuscarCtaE(String Codfact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada, ffe.consEnvio, fn.consecutivo, ffe.codigo FROM fact_facturas_enviadas ffe, fact_numeradas fn WHERE fn.cod_fact_numerada=ffe.codFact AND ffe.codFact='"+Codfact+"' ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarCtaE "+ex);
        }	
        return rs;
    }


public void registroEliminacion(String codFact,String consec,String usuario,Date Fecha, Time Hora,String tabla){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		 														
			ps=con.conn.prepareStatement("INSERT INTO fact_regeliminacion(codFact,consec,Fecha,Hora,usuario,tabla) VALUES(?,?,?,?,?,?)");
			//System.out.println("Valor de Motivo en insercion de factdev "+motivo);
			    ps.setString(1,codFact);
			    ps.setString(2,consec);
			    ps.setDate(3,Fecha);
			    ps.setTime(4,Hora);
			    ps.setString(5,usuario);
			    ps.setString(6,tabla);
			 	ps.executeUpdate();
				ps.close();
		   
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoMovimientos>>registroEliminacion"+ex);
		}
	}


public void EliminarRegCta(String codFact,String consec, String tipo){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	if(tipo.equals("1")){
	    	st= con.conn.prepareStatement(" DELETE FROM fact_facturas_enviadas WHERE consEnvio='"+consec+"' and codFact='"+codFact+"' ");
	    	}else{
	    		if(tipo.equals("2")){
	    			st= con.conn.prepareStatement(" DELETE FROM fact_facturas_auditadas WHERE consAudita='"+consec+"' and codFact='"+codFact+"' ");
	    		}else{
	    			st= con.conn.prepareStatement(" DELETE FROM fact_facturas_radicadasi WHERE consRadicado='"+consec+"' and codFact='"+codFact+"' ");
	    		}
	    	}
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>EliminarRegCta "+ex);
	    
	    }	
	}



public java.sql.ResultSet BuscarValoresCtaCobro(String consec, String tipo){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(tipo.equals("1")){
        		rs=st.executeQuery("SELECT fef.valor, ffenv.codFact, fn.consecutivo FROM fact_facturas_enviadas ffenv, fact_numeradas fn, fact_enc_factura fef "+
        				"WHERE  ffenv.codFact=fn.cod_fact_numerada AND fn.cod_enc_fact_fk=fef.cod_enc_factura AND ffenv.consEnvio='"+consec+"' ");
        	}else{
        		if(tipo.equals("2")){
        			rs=st.executeQuery("SELECT  fef.valor , a.codFact,  fn.consecutivo "+
        								"FROM  fact_facturas_auditadas a, fact_numeradas fn,  fact_enc_factura fef "+
        								"WHERE a.codFact = fn.cod_fact_numerada   AND fn.cod_enc_fact_fk = fef.cod_enc_factura  AND a.consAudita='"+consec+"' ");
        		}else{
        			rs=st.executeQuery("SELECT  fef.valor , a.codFact,  fn.consecutivo "+
							"FROM  fact_facturas_radicadasi a, fact_numeradas fn,  fact_enc_factura fef "+
							"WHERE a.codFact = fn.cod_fact_numerada   AND fn.cod_enc_fact_fk = fef.cod_enc_factura  AND a.consRadicado='"+consec+"' ");
        		}
        	}
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarValoresCtaCobro "+ex);
        }	
        return rs;
    }



public void ActualizarCtaCobro(String consecutivo,long valorfact, String Let,String tipo){	
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	if(tipo.equals("1")){		
	     	st= con.conn.prepareStatement("UPDATE fact_factenviadas SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consEnvio='"+consecutivo+"' ");
	     	//System.out.println("UPDATE fact_factenviadas SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consEnvio='"+consecutivo+"' ");
	     	}else{
	     		if(tipo.equals("2")){
	     			st= con.conn.prepareStatement("UPDATE fact_factauditadas SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consAudita='"+consecutivo+"' ");
	     		}else{
	     			st= con.conn.prepareStatement("UPDATE fact_factradicadasi SET valorLetra='"+Let+"' , valor='"+valorfact+"' WHERE consRadicado='"+consecutivo+"' ");
	     		}
	     	}
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarCtaCobro "+ex);
	     	ex.getMessage();	     
	     }	
	 }


public java.sql.ResultSet BuscarCtaA(String Codfact){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT fn.cod_fact_numerada, fa.consAudita, fn.consecutivo, fa.codigo FROM fact_facturas_auditadas fa, fact_numeradas fn WHERE fa.codFact='"+Codfact+"' AND fn.cod_fact_numerada=fa.codFact ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarCtaA "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet GeneraSQLCtaFactDev(String sql, String ri){	
		/**
		 */
		//System.out.println("JAJAJAJJAAJAJ: "+sql);
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ri.equals("1")){
        	rs=st.executeQuery(" SELECT DISTINCT CONCAT('DEV0000',ffd.consDev)consecutivo,ffd.valor,ffd.fecha,ae.nombre_entidad,CONCAT(sdt.nombre,' ',sdt.apellido) usuario,ftd.descripcion, ffd.consDev "+  
        						"FROM fact_factdev ffd, seg_usuario su, seg_datos_personales sdt, fact_numeradas fn, fact_tipodevoluciones ftd, "+
        						"fact_facturas_devueltas ffdev,fact_enc_factura fef, adm_admisiones ad, adm_paciente ap, adm_entidad ae "+
        						" WHERE ffd.cod_usuario=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND ffd.consDev=ffdev.consdev "+
        						" AND ffdev.codFact=fn.cod_fact_numerada AND fn.cod_enc_fact_fk=fef.cod_enc_factura "+
        						"AND fef.cod_admision=ad.adm_numero_ingreso AND ad.pac_codigo_paciente_fk=ap.pac_codigo_paciente "+
        					     "AND fef.cod_eps=ae.ent_nit AND ftd.codigo=ffd.motivo "+sql);
        	}
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQLCtaFactDev "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet reportedev(String cons){	
		/**
		 */
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery(" SELECT DISTINCT CONCAT('DEV',ffd.consDev)consecutivo, fn.consecutivo, fef.valor, fef.razon_social, fef.nombre_paciente,ffd.motivo, CONCAT(sdt.nombre,' ',sdt.apellido) usuario, ffd.fecha "+ 
        			"FROM fact_factdev ffd, fact_movfacturas ffm, fact_numeradas fn, seg_usuario su, fact_facturas_dev ffdev , fact_enc_factura fef, seg_datos_personales sdt "+
        			"WHERE ffd.consDev=ffdev.consdev AND ffdev.codFact=ffm.codFactNumerada AND fn.cod_fact_numerada=ffm.codFactNumerada AND fef.cod_enc_factura=fn.cod_enc_fact_fk "+
        			"AND ffd.cod_usuario=su.usu_codigo AND sdt.dat_codigo=su.dat_codigo_fk AND ffd.consDev= '"+cons+"'");
        	
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>reportedev "+ex);
        }	
        return rs;
    }

///FIN PROCESO DE DEVOLUCION

	
	public java.sql.ResultSet Empresas(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT ent_nit, nombre_entidad FROM adm_entidad ORDER BY nombre_entidad ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>Empresas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TipoDoc(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, sigla FROM adm_tipodocumento ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>Empresas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet UsuariosFacturadores(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT usuario,usu_codigo  FROM seg_usuario, fact_enc_factura  WHERE usu_codigo=cod_usuario_fk  GROUP BY usu_codigo ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>Empresas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet FactDetalle(String cod){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
       // 	revisar bn esta consulta para q muestre los cargues de servicios Y los programas asociados
        	rs=st.executeQuery("( SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,SUM(cantidad),valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='1' and facturado!=5 " +
        			           "GROUP BY nombre_programa,cod_medico ) " +
        			           "UNION " +
        			           "( SELECT cod_det_factura,tipopop,cod_programa,nombre_programa,cod_paquete, " +
        			           "nombre_paquete,fecha_realizacion,cantidad,valor,facturado,fecha,hora " +
        			           "FROM fact_det_factura " +
        			           "WHERE cod_enc_factura_fk = '"+cod+"' " +
        			           "AND tipopop='2' and facturado!=5 ) " +
        			           "ORDER BY fecha DESC, hora DESC ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>FactDetalle "+ex);
        }	
        return rs;
    }
	
	
	public ResultSet listarPYS(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	r=st.executeQuery("(SELECT DISTINCT 1,mt.manual_base,p.cod_programa,p.cod_referencia,p.descripcion,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico " +
	  		          "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
			          "AND ft.programa = p.cod_programa " +
			          "AND (p.descripcion LIKE '"+texto+"%' OR p.cod_referencia LIKE '"+texto+"%' OR p.cod_cups LIKE '"+texto+"%' ) " +
			          "AND p.clase_servicio=cs.cod_clase_servicio) " +
			          "UNION " +
			          "(SELECT DISTINCT 2,mt.manual_base,pq.cod_paquete,pq.cod_referencia, pq.descripcion,'','','','','','' " +
			          "FROM fact_tarifas ft,fact_manuales_tarifarios mt,fact_progs_paquetes fpp,fact_paquetes pq " +
			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
			          "AND ft.programa = fpp.cod_programa " +
			          "AND fpp.cod_paquete=pq.cod_paquete " +
			          "AND (pq.descripcion LIKE '"+texto+"%' OR pq.cod_referencia LIKE '"+texto+"%' ) ) limit 100");
  	
	     
	      return r;
	}
	

	
	public java.sql.ResultSet listarSYP(String texto, String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.cod_programa,p.cod_referencia,p.descripcion,p.actoqx, " +
        			           "ft.valor,p.medico,p.idporcentajeqx,fcs.descripcion " +
        			           "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio fcs,fact_progs_paquetes fpp " +
        			           "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc " +
        			           "WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio) " +
        			           		"AND mt.cod_manual_tarifario=ft.manual_tarifario " +
        			           		" AND ft.programa = fpp.cod_programa " +
        			           		"AND fpp.cod_paquete = '"+texto+"' " +
        			           				"AND fpp.cod_programa=p.cod_programa " +
        			           				"AND p.clase_servicio=fcs.cod_clase_servicio");
        	
        	

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarSYP "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarS(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_referencia, descripcion FROM fact_paquetes WHERE cod_paquete='"+texto+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarS "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarProg(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_referencia, descripcion FROM fact_programas WHERE cod_programa='"+texto+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarS "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet listarFormasActoQx(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, descripcion FROM fact_formasactoqx ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarFormasActoQx "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet listarFormasActoQxSC(String cod){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo, descripcion FROM fact_formasactoqx  WHERE codigo!='"+cod+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarFormasActoQxSC "+ex);
        }	
        return rs;
    }
	
		
	public java.sql.ResultSet listarActoQx(String texto,String mb1,String mb2){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, descripcion FROM fact_actoqx WHERE descripcion_rips='"+texto+"' AND (descripcion LIKE '%"+mb1+"%' OR descripcion LIKE '%"+mb2+"%')");
        	System.out.println("SELECT codigo, descripcion FROM fact_actoqx WHERE descripcion_rips='"+texto+"' AND (descripcion LIKE '%"+mb1+"%' OR descripcion LIKE '%"+mb2+"%')");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarActoQx "+ex);
        }	
        return rs;
    }
		
		public java.sql.ResultSet obtenerManualBase(String eps){
			/**
			 */
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT GROUP_CONCAT(mb.descripcion) FROM fact_manuales_base mb WHERE mb.cod_manual_base IN " +
	        			           "(SELECT DISTINCT fmb.cod_manual_base " +
	        			           "FROM fact_manuales_tarifarios fmt,fact_manuales_base fmb " +
	        			           "WHERE fmt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio ) " +
	        			           "AND fmt.manual_base=fmb.cod_manual_base)");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoMovimientos>>obtenerManualBase "+ex);
	        }	
	        return rs;
	    }
		
	
	public java.sql.ResultSet listarP(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.cod_referencia, fcs.descripcion , p.subcentro_costo, p.actoqx, p.medico FROM  fact_programas p, fact_clases_servicio fcs  WHERE p.cod_programa='"+texto+"' AND p.clase_servicio=fcs.cod_clase_servicio ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarP "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarPr(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.cod_programa, p.cod_referencia, p.descripcion, p.actoqx, t.valor, p.medico, p.idporcentajeqx, fcs.descripcion " +
        			           "FROM fact_programas p, fact_tarifas t, fact_clases_servicio fcs " +
        			           "WHERE p.cod_programa='"+texto+"' " +
        			           "AND p.clase_servicio=fcs.cod_clase_servicio " +
        			           "AND p.cod_programa=t.programa");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarPr "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet listarSYPV(String texto, String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ft.valor FROM fact_tarifas ft " +
        			           "WHERE ft.manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio) " +
        			           "AND ft.programa = "+texto);
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarSYPV "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarMedSelect(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT u.usu_codigo,concat(nombre,' ',apellido,'-',ocupacion) as NombreMedico, ocupacion FROM seg_datos_personales dat, seg_usuario u WHERE (dat.profesion='Especialista' OR dat.profesion='Radiologo' OR dat.profesion='Sicologia' OR dat.profesion='Nutricionista' OR dat.profesion='Medico General' ) AND dat_codigo=u.dat_codigo_fk   ORDER BY nombre  ");
        //	SELECT u.usu_codigo, nombre, apellido, ocupacion FROM seg_datos_personales, seg_usuario u WHERE profesion='Especialista' AND dat_codigo=u.dat_codigo_fk  AND (nombre LIKE '"+texto+"%' OR apellido LIKE '"+texto+"%' ) ORDER BY nombre ");
            
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarMed "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarMed(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT u.usu_codigo, nombre, apellido, ocupacion FROM seg_datos_personales dat, seg_usuario u WHERE (dat.profesion='Especialista' OR dat.profesion='Radiologo' OR dat.profesion='Sicologia' OR dat.profesion='Nutricionista' ) AND dat_codigo=u.dat_codigo_fk  AND (nombre LIKE '"+texto+"%' OR apellido LIKE '"+texto+"%' ) ORDER BY nombre limit 100 ");
        //	SELECT u.usu_codigo, nombre, apellido, ocupacion FROM seg_datos_personales, seg_usuario u WHERE profesion='Especialista' AND dat_codigo=u.dat_codigo_fk  AND (nombre LIKE '"+texto+"%' OR apellido LIKE '"+texto+"%' ) ORDER BY nombre ");
            
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarMed "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarMedce(String xx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" select u.usu_codigo, nombre, apellido from seg_datos_personales, seg_usuario u where profesion='Especialista' and dat_codigo=u.dat_codigo_fk AND u.usu_codigo='"+xx+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarMedce "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet EstanciaUrg(String xx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery(" SELECT fecha_ingreso,fecha_egreso FROM fact_enc_factura WHERE cod_admision='"+xx+"'  ");
        	
        	rs=st.executeQuery("select fecha_ingreso,fecha_egreso from fact_enc_factura where cod_admision="+xx+"");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>EstanciaUrg "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet EstanciaUrg2(String xx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT a.fecha_registro FROM adm_admisiones a WHERE a.adm_numero_ingreso='"+xx+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>EstanciaUrg2 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet EstanciaHosp(String xx){	
		//System.out.println("SELECT a.fecha_registro, hd.DestinoPaciente, hd.fecha FROM adm_admisiones a, hic_destinopaciente hd WHERE a.adm_numero_ingreso='"+xx+"' AND a.adm_numero_ingreso=hd.codAdm AND hd.DestinoPaciente='SALIDA DE HOSPITALIZACION' ");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery(" SELECT fecha_ingreso,fecha_egreso FROM fact_enc_factura WHERE cod_admision='"+xx+"' ");
            
        	rs=st.executeQuery("SELECT fecha_ingreso,fecha_egreso from fact_enc_factura where cod_admision="+xx+"");
            }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>EstanciaHosp "+ex);
        }	
        return rs;
    }
	
	
	
	public ResultSet listarRef(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	r=st.executeQuery("(SELECT DISTINCT 1,mt.manual_base,p.cod_programa,p.cod_referencia,p.descripcion,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico " +
	        			          "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = p.cod_programa " +
	        			          "AND p.cod_referencia LIKE '"+texto+"%' " +
	        			          "AND p.clase_servicio=cs.cod_clase_servicio) " +
	        			          "UNION " +
	        			          "(SELECT DISTINCT 2,mt.manual_base,pq.cod_paquete,pq.cod_referencia, pq.descripcion,'','','','','','' " +
	        			          "FROM fact_tarifas ft,fact_manuales_tarifarios mt,fact_progs_paquetes fpp,fact_paquetes pq " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = fpp.cod_programa " +
	        			          "AND fpp.cod_paquete=pq.cod_paquete " +
	        			          "AND pq.cod_referencia LIKE '"+texto+"%') limit 100");
	        	
	        	
	     
	      return r;
	}
	
	
	public void CargarDetalle(String fecha,String hora,String pop,String cod_programafk,String cod_programa,String nombre_programa,String cod_paquete,String nombre_paquete,String clase_servicio,String fecha_realizacion,String cantidad,String valor,String cod_usuario,String cod_enc_factura_fk,String cod_medico,String subc,String formaqx, String p){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_det_factura(fecha,hora,tipopop,cod_programafk,cod_programa,nombre_programa,cod_paquete,nombre_paquete,clase_servicio,fecha_realizacion,cantidad,valor,cod_usuario,cod_enc_factura_fk,cod_medico,subcentrodecosto,formaactoqx,porcentaje)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,fecha);
			    ps.setString(2,hora);
			    ps.setString(3,pop);
			    ps.setString(4,cod_programafk);
			    ps.setString(5,cod_programa);
			    ps.setString(6,nombre_programa);
			    ps.setString(7,cod_paquete);
			    ps.setString(8,nombre_paquete);
			    ps.setString(9,clase_servicio);
			    ps.setString(10,fecha_realizacion);
			    ps.setString(11,cantidad);
			    ps.setString(12,valor);
			    ps.setString(13,cod_usuario);
			    ps.setString(14,cod_enc_factura_fk);
			    ps.setString(15,cod_medico);
			    ps.setString(16,subc);
			    ps.setString(17,formaqx);
			    ps.setString(18,p);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>CargarDetalle "+ex);
			}
		}
	
	
	public void AnularMov(String mov){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from fact_det_factura where cod_det_factura='"+mov+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>Anularmov "+ex);
	    
	    }	
	}
	
	
	public java.sql.ResultSet Valor(String adm, String eps,String codEnca){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("select df.valor, df.cantidad, fef.anticipos, fef.moderacion, fef.copago, fef.valor from fact_enc_factura fef, fact_det_factura df where cod_eps='"+eps+"' and cod_admision='"+adm+"' and fef.cod_enc_factura=cod_enc_factura_fk ");
        	rs=st.executeQuery("SELECT df.valor, df.cantidad, fef.anticipos, fef.moderacion, fef.copago, fef.valor FROM fact_enc_factura fef, fact_det_factura df WHERE fef.cod_enc_factura='"+codEnca+"' AND fef.cod_enc_factura=df.cod_enc_factura_fk and df.facturado!='5'"); 
        	System.out.println("VALOR----->SELECT df.valor, df.cantidad, fef.anticipos, fef.moderacion, fef.copago, fef.valor FROM fact_enc_factura fef, fact_det_factura df WHERE fef.cod_enc_factura='"+codEnca+"' AND fef.cod_enc_factura=df.cod_enc_factura_fk and df.facturado!='5'");

          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>Valor "+ex);
        }	
        return rs;
    }
	
	public void Anticipo(String adm, String eps, String valor,String enca ){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET anticipos='"+valor+"' WHERE cod_eps='"+eps+"' and cod_enc_factura='"+enca+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>Anticipo "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void Modera(String adm, String eps, String valor,String enca){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET moderacion='"+valor+"' WHERE cod_eps='"+eps+"' and cod_enc_factura='"+enca+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>Modera "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	

	public java.sql.ResultSet GeneraSQL(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT n.consecutivo, ef.razon_social, p.tipo_documento, p.numero_documento, p.nombre, p.primer_apellido, p.segundo_apellido, ef.fecha, a.adm_numero_ingreso,ef.cod_enc_factura FROM fact_enc_factura ef, adm_admisiones a, adm_paciente p, fact_numeradas n WHERE ef.cod_admision=a.adm_numero_ingreso AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND ef.cod_enc_factura=n.cod_enc_fact_fk and n.estadoFact!=5 "+sql+ " ORDER BY n.consecutivo");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQL "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet GeneraSQLSelFact(String sql, String ea){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ea.equals("0")){
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso!='NULL',fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
        			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact, " +
        			           "(SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura=fn.consecutivo) AS nc "+
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am " +
        			           "WHERE (fn.estadoFact='0' or fn.estadoFact='8') " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_eps=ae.ent_nit " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod"+sql+ " ORDER BY fn.consecutivo");
          }
        	if(ea.equals("1")){
            	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
            			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso!='NULL',fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
            			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact, " +
            			           "(SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura=fn.consecutivo) AS nc "+
            			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am " +
            			           "WHERE fn.estadoFact='0' " +
            			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
            			           "AND fef.cod_eps=ae.ent_nit " +
            			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
            			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
            			           "AND p.mun_codigo_fk=am.muni_cod"+sql+ " ORDER BY fn.consecutivo");
              }
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQLSelFact "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet GeneraSQLSelFactEnv(String sql, String ea){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ea.equals("0")){
        	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso!='NULL',fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
        			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact, " +
        			           "(SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura=fn.consecutivo) AS nc "+
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am " +
        			           "WHERE (fn.estadoFact='0' or fn.estadoFact='8') " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_eps=ae.ent_nit " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod"+sql+ " ORDER BY fn.consecutivo");
          }
        	if(ea.equals("1")){
            	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
            			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso!='NULL',fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
            			           "fef.valor,ae.nombre_entidad,am.nombre,fn.estadoFact, " +
            			           "(SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura=fn.consecutivo) AS nc "+
            			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, adm_municipio am " +
            			           "WHERE fn.estadoFact='0' " +
            			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
            			           "AND fef.cod_eps=ae.ent_nit " +
            			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
            			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
            			           "AND p.mun_codigo_fk=am.muni_cod"+sql+ " ORDER BY fn.consecutivo");
              }
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQLSelFactEnv "+ex);
        }	
        return rs;
    }


	public java.sql.ResultSet GeneraSQLSelFactR(String sql,String ri){	
		/**
		 */System.out.println("estamos aquiiiri: "+ri);
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ri.equals("0")){
 
        		rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
 			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso!='NULL',fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
 			           "fef.valor,ae.nombre_entidad,CONCAT('CTA000',fe.consEnvio) cta ,fn.estadoFact,fn.fecha, " +
 			          "(SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura=fn.consecutivo) AS nc "+
 			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, fact_facturas_enviadas fe " +
 			           "WHERE (fn.estadoFact='1' OR fn.estadoFact='8')  " +
 			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
 			           "AND fn.cod_fact_numerada=fe.codFact "+
 			           "AND fef.cod_eps=ae.ent_nit " +
 			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
 			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +sql+ " ORDER BY fn.consecutivo");
            		
        				
        				
        	}
        	if(ri.equals("1")){
        		
     		
            	rs=st.executeQuery("SELECT fn.cod_fact_numerada,fn.consecutivo,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
            			"CONCAT(p.tipo_documento,' ',p.numero_documento) documento,fef.fecha_ingreso AS 'f.ingreso', IF(fef.fecha_egreso!='NULL',fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
            			"fef.valor,ae.nombre_entidad,CONCAT('REL000',fe.consAudita) cta,fn.estadoFact, fn.fecha, " +
            			 "(SELECT SUM(cdf.cantidad) FROM cont_detalle_factura cdf, cont_factura cf,  cont_complemento_factura ccf  WHERE cdf.cod_fact_fk=cf.codigo AND ccf.cod_det_factura_fk=cdf.codigo AND ccf.cod_factura_fk=cf.codigo AND cf.numero_factura=fn.consecutivo) AS nc "+
            			"FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p, adm_admisiones adm, fact_facturas_auditadas fe " +
            			"WHERE fn.estadoFact='6'" +
            			"AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
            			"AND fn.cod_fact_numerada=fe.codFact " +
            			"AND fef.cod_eps=ae.ent_nit " +
            			"AND fef.cod_admision=adm.adm_numero_ingreso " +
            			"AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente" +sql+ " ORDER BY fn.consecutivo");
            	
            	
            	}
          }
        
     
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQLSelFact "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet GeneraSQLCtaFact(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select DISTINCT " +
        			           "concat('CTA000', ffe.consEnvio) consecutivo, " +
        			           "ffe.valor,ffe.fecha,ae.nombre_entidad,concat(sdt.nombre, ' ', sdt.apellido) usuario,ffe.consEnvio " +
        			           "from fact_factenviadas ffe,seg_usuario su,seg_datos_personales sdt, " +
        			           "fact_numeradas fn,fact_facturas_enviadas ffev,fact_enc_factura fef, " +
        			           "adm_admisiones ad,adm_paciente ap,adm_entidad ae " +
        			           "where ffe.cod_usuario = su.usu_codigo " +
        			           "and su.dat_codigo_fk = sdt.dat_codigo " +
        			           "and ffe.consEnvio = ffev.consEnvio " +
        			           "and ffev.codFact = fn.cod_fact_numerada " +
        			           "AND fn.cod_enc_fact_fk = fef.cod_enc_factura " +
        			           "and fef.cod_admision = ad.adm_numero_ingreso " +
        			           "and ad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
        			           "and fef.cod_eps = ae.ent_nit "+sql);
        	
        	
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQLCtaFact "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet GeneraSQLCtaFactR(String sql, String ra){	
		/**
		 */
		//System.out.println("JAJAJAJJAAJAJ: "+sql);
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ra.equals("0")){
        	rs=st.executeQuery("select DISTINCT " +
        			           "concat('RAD0', ffe.consRadicado) consecutivo, " +
        			           "ffe.valor,ffe.fecha,ae.nombre_entidad,concat(sdt.nombre, ' ', sdt.apellido) usuario,ffe.consRadicado " +
        			           "from fact_factradicadas ffe,seg_usuario su,seg_datos_personales sdt, " +
        			           "fact_numeradas fn,fact_facturas_radicadas ffev,fact_enc_factura fef, " +
        			           "adm_admisiones ad,adm_paciente ap,adm_entidad ae " +
        			           "where ffe.cod_usuario = su.usu_codigo " +
        			           "and su.dat_codigo_fk = sdt.dat_codigo " +
        			           "and ffe.consRadicado = ffev.consRadicado " +
        			           "and ffev.codFact = fn.cod_fact_numerada " +
        			           "AND fn.cod_enc_fact_fk = fef.cod_enc_factura " +
        			           "and fef.cod_admision = ad.adm_numero_ingreso " +
        			           "and ad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
        			           "and fef.cod_eps = ae.ent_nit "+sql);
        	}
        	if(ra.equals("1")){
            	rs=st.executeQuery("SELECT DISTINCT " +
            			"CONCAT('REL000', ffe.consAudita) consecutivo, " +
            			"ffe.valor,ffe.fecha,ae.nombre_entidad,CONCAT(sdt.nombre, ' ', sdt.apellido) usuario,ffe.consAudita " +
            			"FROM fact_factauditadas ffe,seg_usuario su,seg_datos_personales sdt, " +
            			"fact_numeradas fn,fact_facturas_auditadas ffev,fact_enc_factura fef, " +
            			"adm_admisiones ad,adm_paciente ap,adm_entidad ae " +
            			"WHERE ffe.cod_usuario = su.usu_codigo " +
            			"AND su.dat_codigo_fk = sdt.dat_codigo " +
            			"AND ffe.consAudita = ffev.consAudita " +
            			"AND ffev.codFact = fn.cod_fact_numerada " +
            			"AND fn.cod_enc_fact_fk = fef.cod_enc_factura " +
            			"AND fef.cod_admision = ad.adm_numero_ingreso " +
            			"AND ad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
            			"AND fef.cod_eps = ae.ent_nit "+sql);
            }
        	
        	if(ra.equals("2")){
            	rs=st.executeQuery("SELECT DISTINCT " +
            			"CONCAT('RADI0', ffe.consRadicado) consecutivo, " +
            			"ffe.valor,ffe.fecha,ae.nombre_entidad,CONCAT(sdt.nombre, ' ', sdt.apellido) usuario,ffe.consRadicado " +
            			"FROM fact_factradicadasi ffe,seg_usuario su,seg_datos_personales sdt, " +
            			"fact_numeradas fn,fact_facturas_radicadasi ffev,fact_enc_factura fef, " +
            			"adm_admisiones ad,adm_paciente ap,adm_entidad ae " +
            			"WHERE ffe.cod_usuario = su.usu_codigo " +
            			"AND su.dat_codigo_fk = sdt.dat_codigo " +
            			"AND ffe.consRadicado = ffev.consRadicado " +
            			"AND ffev.codFact = fn.cod_fact_numerada " +
            			"AND fn.cod_enc_fact_fk = fef.cod_enc_factura " +
            			"AND fef.cod_admision = ad.adm_numero_ingreso " +
            			"AND ad.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
            			"AND fef.cod_eps = ae.ent_nit "+sql);
            	}
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>GeneraSQLCtaFactR "+ex);
        }	
        return rs;
    }
	
	
	
	
	
	public void Copago(String adm, String eps, String valor,String enca ){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET copago='"+valor+"' WHERE cod_eps='"+eps+"' and cod_enc_factura='"+enca+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>Copago "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void Efectivo(String adm, String eps, String valor,String enca ){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET efectivo='"+valor+"' WHERE cod_eps='"+eps+"' and cod_enc_factura='"+enca+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>Efectivo "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void Valor(String adm, String eps, String valor, String valorl,String enca ){	
		//System.out.print("Cesar");/**
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET valor='"+valor+"', valorletras='"+valorl+"' WHERE cod_eps='"+eps+"' and cod_enc_factura='"+enca+"' ");
	     	           System.out.println("UPDATE fact_enc_factura SET valor='"+valor+"', valorletras='"+valorl+"' WHERE cod_eps='"+eps+"' and cod_enc_factura='"+enca+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>Valor "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet NumFacturas(){	
		//System.out.println("NumFacturas");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM fact_numeros_fact ");
        	//rs=st.executeQuery(" SELECT consecutivo FROM fact_numeros_fact ");
        	//System.out.println(" SELECT consecutivo FROM fact_numeros_fact ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>NumFacturas "+ex);
        }	
        return rs;
    }
	
	public void ActualizarNumFacturas(String valor){	
		//System.out.println("ActualizarNumFacturas");
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE fact_numeros_fact SET consecutivo='"+valor+"' ");
	     	//System.out.println("UPDATE fact_numeros_fact SET consecutivo='"+valor+"'" );
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarNumFacturas "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarEncabezado(String f, String h, String u, String e){	
		//System.out.println("ActualizarEncabezado");
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' , estado='1' where cod_enc_factura='"+e+"'  ");
	     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"',estado='1' where cod_enc_factura='"+e+"'  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarEncabezado "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	

	public java.sql.ResultSet ValorFact(String enca){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT valor,nit FROM fact_enc_factura WHERE cod_enc_factura='"+enca+"' ");
      }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>NumFacturas "+ex);
    }	
    return rs;
	}
	
	public java.sql.ResultSet ServicioAdmision(String CodAdmision){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT acc.servicio FROM adm_censo_cama acc,adm_admisiones aad WHERE acc.cen_numero_cama=aad.cen_numero_cama_fk AND aad.adm_numero_ingreso="+CodAdmision+" ");
	    	//System.out.println("SELECT acc.servicio FROM adm_censo_cama acc,adm_admisiones aad WHERE acc.cen_numero_cama=aad.cen_numero_cama_fk AND aad.adm_numero_ingreso="+CodAdmision+" ");
	      }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>ServicioAdmision "+ex);
	    }	
	    return rs;
		}
	

	public void ActualizarAuditoriadeConvenios(String ent, String val){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion(); 
	     	st= con.conn.prepareStatement(" UPDATE fact_audita_convenio SET consumido=(consumido+'"+val+"') where cod_entidadfk='"+ent+"'  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarNumFacturas "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarAdmision(String adm){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE adm_admisiones SET atendido='1' where adm_numero_ingreso='"+adm+"'  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarAdmision "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	public void ActualizarAmbulatorio(String adm){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE adm_ambulatorio SET atendido='1' WHERE numero_ingreso='"+adm+"'  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarAdmision "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void AsignarNumFact(String fecha,String enca,String nf){
		//System.out.println(" AsignarNumFact fecha "+fecha+" enca "+enca+" nf "+nf);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_numeradas(cod_enc_fact_fk,consecutivo,fecha)values(?,?,?)");
			    ps.setString(1,enca);
			    ps.setString(2,nf);
			    ps.setString(3,fecha);
						
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>AsignarNumFact "+ex);
			}
		}
	
	
	public void AsignarMovEnca(String fecha,String enca,String nf){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_numeradas(cod_enc_fact_fk,consecutivo,fecha)values(?,?,?)");
			    ps.setString(1,enca);
			    ps.setString(2,nf);
			    ps.setString(3,fecha);
						
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>AsignarMovEnca "+ex);
			}
		}
	
	
	public java.sql.ResultSet ObtenerCodigoAdmision(String Enca){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cod_admision,tipo FROM fact_enc_factura WHERE cod_enc_factura='"+Enca+"' ");
        	System.out.println("OJOOOO10---->SELECT cod_admision,tipo FROM fact_enc_factura WHERE cod_enc_factura='"+Enca+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ObtenerCodigoAdmision "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet PorcentajeQx(String valor){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT honorarios_cirujano,horarios_anestesiologos,horarios_ayudantia,derecho_sala,materiales,otros FROM fact_actoqx WHERE codigo='"+valor+"' ");
        	System.out.println(" SELECT honorarios_cirujano,horarios_anestesiologos,horarios_ayudantia,derecho_sala,materiales,otros FROM fact_actoqx WHERE codigo='"+valor+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>PorcentajeQx "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet movSinFacturar(String codAdmision){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fdf.cod_det_factura,fef.cod_enc_factura,fdf.tipopop,IF(fdf.cod_paquete<>'NULL',fdf.cod_paquete,fdf.cod_programa),IF(fdf.nombre_paquete<>'NULL',fdf.nombre_paquete,fdf.nombre_programa) nombre, " +
        			           "fdf.cod_paquete,fdf.nombre_paquete,fdf.fecha_realizacion,fdf.cantidad,SUM(fdf.valor),CONCAT(sdp.nombre,' ',sdp.apellido) usuario,fdf.clase_servicio,fdf.hora " +
        			           "FROM fact_det_factura fdf, fact_enc_factura fef,seg_usuario su,seg_datos_personales sdp " +
        			           "WHERE fef.cod_admision='"+codAdmision+"' " +
        			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
        			           "AND fdf.cod_usuario=su.usu_codigo " +
        			           "AND su.dat_codigo_fk=sdp.dat_codigo AND fdf.facturado!=5 " +
        			           "AND fdf.cod_det_factura NOT IN (SELECT fdf.cod_det_factura " +
        			           "FROM fact_det_factura fdf, fact_enc_factura fef " +
        			           "WHERE fef.cod_admision='"+codAdmision+"' " +
        			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
        			           //"AND fef.cod_enc_factura=fn.cod_enc_fact_fk " +
        			           "AND fdf.facturado='1') " +
        			           "GROUP BY fdf.hora,fdf.fecha  " +
        			           "ORDER BY fdf.cod_det_factura");
        	
        	System.out.println("SELECT fdf.cod_det_factura,fef.cod_enc_factura,fdf.tipopop,IF(fdf.cod_paquete<>'NULL',fdf.cod_paquete,fdf.cod_programa),IF(fdf.nombre_paquete<>'NULL',fdf.nombre_paquete,fdf.nombre_programa) nombre, " +
			           "fdf.cod_paquete,fdf.nombre_paquete,fdf.fecha_realizacion,fdf.cantidad,SUM(fdf.valor),CONCAT(sdp.nombre,' ',sdp.apellido) usuario,fdf.clase_servicio,fdf.hora " +
			           "FROM fact_det_factura fdf, fact_enc_factura fef,seg_usuario su,seg_datos_personales sdp " +
			           "WHERE fef.cod_admision='"+codAdmision+"' " +
			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
			           "AND fdf.cod_usuario=su.usu_codigo " +
			           "AND su.dat_codigo_fk=sdp.dat_codigo AND fdf.facturado!=5 " +
			           "AND fdf.cod_det_factura NOT IN (SELECT fdf.cod_det_factura " +
			           "FROM fact_det_factura fdf, fact_enc_factura fef " +
			           "WHERE fef.cod_admision='"+codAdmision+"' " +
			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
			           //"AND fef.cod_enc_factura=fn.cod_enc_fact_fk " +
			           "AND fdf.facturado='1') " +
			           "GROUP BY fdf.hora,fdf.fecha  " +
			           "ORDER BY fdf.cod_det_factura");
        		
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>movSinFacturar "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet movxEncabezados(String codEnca){	
		//System.out.println("Error en MetodoMovimientos>>movxEncabezados ");
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT fdf.cod_det_factura,fef.cod_enc_factura,fdf.tipopop,IF(fdf.cod_paquete<>'NULL',fdf.cod_paquete,fdf.cod_programa),IF(fdf.nombre_paquete<>'NULL',CONCAT(fdf.nombre_paquete,' ( ',fdf.nombre_programa,')'),fdf.nombre_programa) nombre, fdf.cod_paquete,fdf.nombre_paquete,fdf.fecha_realizacion,fdf.cantidad,SUM(fdf.valor),CONCAT(sdp.nombre,' ',sdp.apellido) usuario,fdf.clase_servicio,fdf.hora FROM fact_det_factura fdf, fact_enc_factura fef,seg_usuario su,seg_datos_personales sdp WHERE fef.cod_enc_factura='"+codEnca+"' AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fdf.cod_usuario=su.usu_codigo AND su.dat_codigo_fk=sdp.dat_codigo GROUP BY fdf.hora ORDER BY fdf.fecha_realizacion ");		
        	System.out.println(" SELECT fdf.cod_det_factura,fef.cod_enc_factura,fdf.tipopop,IF(fdf.cod_paquete<>'NULL',fdf.cod_paquete,fdf.cod_programa),IF(fdf.nombre_paquete<>'NULL',CONCAT(fdf.nombre_paquete,' ( ',fdf.nombre_programa,')'),fdf.nombre_programa) nombre, fdf.cod_paquete,fdf.nombre_paquete,fdf.fecha_realizacion,fdf.cantidad,SUM(fdf.valor),CONCAT(sdp.nombre,' ',sdp.apellido) usuario,fdf.clase_servicio,fdf.hora FROM fact_det_factura fdf, fact_enc_factura fef,seg_usuario su,seg_datos_personales sdp WHERE fef.cod_enc_factura='"+codEnca+"' AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fdf.cod_usuario=su.usu_codigo AND su.dat_codigo_fk=sdp.dat_codigo GROUP BY fdf.hora ORDER BY fdf.fecha_realizacion ");  
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>movxEncabezados "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerNumFact(String codAdmision){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.consecutivo FROM fact_enc_factura fef, fact_numeradas fn " +
        			           "WHERE fef.cod_admision='"+codAdmision+"' " +
        			           "AND fef.cod_enc_factura=fn.cod_enc_fact_fk");	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerNumFact "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerInfoEncaActivo(String codAdmision){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' and fef.estado=0 ORDER BY  fef.cod_enc_factura DESC LIMIT 1");	
        	System.out.println("ENTRA AQUI--->SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' and fef.estado=0 ORDER BY  fef.cod_enc_factura DESC LIMIT 1");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerInfoEncaActivo "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet obtenerInfoEnca(String codAdmision){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' and fef.estado!=5  ORDER BY  fef.cod_enc_factura DESC LIMIT 1");	
        	System.out.println("ObtenerInfoEnca -- > SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' and fef.estado!=5  ORDER BY  fef.cod_enc_factura DESC LIMIT 1");	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerInfoEnca "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerInfoEnca2(String codAdmision,String encabezado){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' and fef.estado!=5 and fef.cod_enc_factura='"+encabezado+"' ORDER BY  fef.cod_enc_factura DESC LIMIT 1");	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerInfoEnca "+ex);
        }	
        return rs;
    }
	
	
	public void duplicarEncabezado(String cod_eps,String razon_social,String nit,String direccion,String telefono,String ciudad,String condicion_pago,String nombre_paciente,String documento,String direccion_p,String telefono_p,String tipo_afiliacion,String estrato,String fecha_ingreso,String cod_admision,String num_autorizacion,String poliza,String tipo,String fechaEgreso){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("INSERT INTO fact_enc_factura"
			    		+ "(cod_eps,"
			    		+ "razon_social,"
			    		+ "nit,"
			    		+ "direccion,"
			    		+ "telefono,"
			    		+ "ciudad,"
			    		+ "condicion_pago,"
			    		+ "nombre_paciente,"
			    		+ "documento,"
			    		+ "direccion_p,"
			    		+ "telefono_p,"
			    		+ "tipo_afiliacion,"
			    		+ "estrato,"
			    		+ "fecha_ingreso,"
			    		+ "cod_admision,"
			    		+ "num_autorizacion,"
			    		+ "poliza,"
			    		+ "tipo,"
			    		+ "fecha_egreso) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,cod_eps);
			    ps.setString(2,razon_social);
			    ps.setString(3,nit);			   
			    ps.setString(4,direccion);
			    ps.setString(5,telefono);
			    ps.setString(6,ciudad);			    
			    ps.setString(7,condicion_pago);
			    ps.setString(8,nombre_paciente);
			    ps.setString(9,documento);			    
			    ps.setString(10,direccion_p);
			    ps.setString(11,telefono_p);
			    ps.setString(12,tipo_afiliacion);			    
			    ps.setString(13,estrato);
			    ps.setString(14,fecha_ingreso);
			    ps.setString(15,cod_admision);			    
			    ps.setString(16,num_autorizacion);
			    ps.setString(17,poliza);
			    ps.setString(18,tipo);
			    ps.setString(19, fechaEgreso);
						
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>duplicarEncabezado "+ex);
			}
		}
	
	
	public void actualizarMovimiento(String codMov,String codEnca){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_det_factura SET cod_enc_factura_fk='"+codEnca+"', facturado='2' WHERE cod_det_factura='"+codMov+"'");
	     	System.out.println("UPDATE fact_det_factura SET cod_enc_factura_fk='"+codEnca+"', facturado='2' WHERE cod_det_factura='"+codMov+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>actualizarMovimiento "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void anularMovFact(String codMov,String codEnca){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_det_factura SET cod_enc_factura_fk='"+codEnca+"', facturado='0' WHERE cod_det_factura='"+codMov+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>anularMovFact "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet estadoAdmision(String admision){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aad.estado FROM adm_admisiones aad WHERE aad.adm_numero_ingreso='"+admision+"'");
        	//System.out.println("SELECT aad.atendido FROM adm_admisiones aad WHERE aad.adm_numero_ingreso='"+admision+"'");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>estadoAdmision "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet encaSinFacturar(String admision, String eps){//creo q solo son moviminetos en 2
		//System.out.println("Error en MetodoMovimientos>>encaSinFacturar ");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
         	rs=st.executeQuery("SELECT fef.cod_enc_factura FROM fact_enc_factura fef , fact_det_factura fdf WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' AND  fef.estado='0' AND fdf.facturado='2' AND fef.cod_enc_factura=fdf.cod_enc_factura_fk GROUP BY fef.cod_enc_factura");
         	System.out.println("SELECT fef.cod_enc_factura FROM fact_enc_factura fef , fact_det_factura fdf WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' AND  fef.estado='0' AND fdf.facturado='2' AND fef.cod_enc_factura=fdf.cod_enc_factura_fk GROUP BY fef.cod_enc_factura");

        	//rs=st.executeQuery("SELECT fef.cod_enc_factura FROM fact_enc_factura fef , fact_det_factura fdf WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' AND (fef.estado='0' OR fef.estado='2') AND fef.cod_enc_factura=fdf.cod_enc_factura_fk");
        	//System.out.println("SELECT fef.cod_enc_factura FROM fact_enc_factura fef , fact_det_factura fdf WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' AND (fef.estado='0' OR fef.estado='2') AND fef.cod_enc_factura=fdf.cod_enc_factura_fk");
      
        	
        	/*rs=st.executeQuery("SELECT fef.cod_enc_factura FROM fact_enc_factura fef " +
        			           "WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' " +
        			           "AND fef.cod_enc_factura NOT IN " +
        			           "(SELECT fef.cod_enc_factura FROM fact_enc_factura fef, fact_numeradas fn " +
        			           "WHERE  fef.cod_admision='"+admision+"' " +
        			           "AND fef.cod_enc_factura=fn.cod_enc_fact_fk) " +
        			           "ORDER BY fef.cod_enc_factura ");
        	
        	
        	System.out.println("SELECT fef.cod_enc_factura FROM fact_enc_factura fef " +
			           "WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' " +
			           "AND fef.cod_enc_factura NOT IN " +
			           "(SELECT fef.cod_enc_factura FROM fact_enc_factura fef, fact_numeradas fn " +
			           "WHERE  fef.cod_admision='"+admision+"' " +
			           "AND fef.cod_enc_factura=fn.cod_enc_fact_fk) " +
			           "ORDER BY fef.cod_enc_factura ");
        	*/
        	//System.out.println("SELECT aad.atendido FROM adm_admisiones aad WHERE aad.adm_numero_ingreso='"+admision+"'");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>encaSinFacturar "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet encaSinFacturar2(String admision){	
		//System.out.println("Error en MetodoMovimientos>>encaSinFacturar ");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.cod_enc_factura FROM fact_enc_factura fef " +
        			           "WHERE  fef.cod_admision='"+admision+"'  AND fef.estado!='3' " +
        			           "AND fef.cod_enc_factura NOT IN " +
        			           "(SELECT fef.cod_enc_factura FROM fact_enc_factura fef, fact_numeradas fn " +
        			           "WHERE  fef.cod_admision='"+admision+"' " +
        			           "AND fef.cod_enc_factura=fn.cod_enc_fact_fk) " +
        			           "ORDER BY fef.cod_enc_factura ");
        	
        	
        	/*System.out.println("SELECT fef.cod_enc_factura FROM fact_enc_factura fef " +
			           "WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' " +
			           "AND fef.cod_enc_factura NOT IN " +
			           "(SELECT fef.cod_enc_factura FROM fact_enc_factura fef, fact_numeradas fn " +
			           "WHERE  fef.cod_admision='"+admision+"' " +
			           "AND fef.cod_enc_factura=fn.cod_enc_fact_fk) " +
			           "ORDER BY fef.cod_enc_factura ");*/
        	
        	//System.out.println("SELECT aad.atendido FROM adm_admisiones aad WHERE aad.adm_numero_ingreso='"+admision+"'");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>encaSinFacturar "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet encaSinFacturar3(String admision, String eps){//creo q solo son moviminetos en 2
		//System.out.println("Error en MetodoMovimientos>>encaSinFacturar ");
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
         	rs=st.executeQuery("SELECT fef.cod_enc_factura FROM fact_enc_factura fef WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' AND  fef.estado='0' GROUP BY fef.cod_enc_factura");
         	System.out.println("SELECT fef.cod_enc_factura FROM fact_enc_factura fef , fact_det_factura fdf WHERE  fef.cod_admision='"+admision+"' AND fef.cod_eps='"+eps+"' AND  fef.estado='0' GROUP BY fef.cod_enc_factura");

        	//System.out.println("SELECT aad.atendido FROM adm_admisiones aad WHERE aad.adm_numero_ingreso='"+admision+"'");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>encaSinFacturar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet encaFacturados(String codAdmision, String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fef.cod_enc_factura,fn.consecutivo  " +
        			           "FROM fact_enc_factura fef, fact_numeradas fn " +
        			           "WHERE fef.cod_admision='"+codAdmision+"' AND fef.cod_eps='"+eps+"'  AND fef.estado='1' " +
        			           "AND fef.cod_enc_factura=fn.cod_enc_fact_fk " +
        			           "ORDER BY fef.fecha");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>encaFacturados "+ex);
        }	
        return rs;
    }
	

	public java.sql.ResultSet primerEncaAdmision(String codAdmision, String eps){	
		//System.out.println("Error en MetodoMovimientos>>primerEncaAdmision ");
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' AND fef.cod_eps='"+eps+"' AND fef.estado='0' ORDER BY fef.cod_enc_factura LIMIT 1");
        	System.out.println("SELECT * FROM fact_enc_factura fef WHERE fef.cod_admision='"+codAdmision+"' AND fef.cod_eps='"+eps+"' AND fef.estado='0' ORDER BY fef.cod_enc_factura LIMIT 1");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>primerEncaAdmision "+ex);
        }	
        return rs;
    }
	
	public void anularEncabezado(String enca){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from fact_enc_factura WHERE cod_enc_factura='"+enca+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>anularEncabezado "+ex);
	    
	    }	
	}
	
	public void EliminarCargues(String enca){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement(" DELETE FROM fact_det_factura WHERE cod_enc_factura_fk='"+enca+"' AND facturado=0");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>EliminarCargues "+ex);
	    
	    }	
	}
	
	public java.sql.ResultSet obtenerDetMovServicios(String codMov, String codAdm,String hora){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fdf.cod_det_factura " +
        			           "FROM fact_det_factura fdf, fact_enc_factura fef " +
        				       "WHERE fdf.cod_paquete='"+codMov+"' " +
        			           "AND fef.cod_admision='"+codAdm+"' " +
        			           "AND fdf.hora='"+hora+"' " +
        			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fdf.facturado!='5' " +
        			           "UNION " +
        			           "SELECT fdf.cod_det_factura " +
        			           "FROM fact_det_factura fdf, fact_enc_factura fef " +
        			           "WHERE fdf.cod_programa='"+codMov+"' " +
        			           "AND fef.cod_admision='"+codAdm+"' " +
        			           "AND fdf.hora='"+hora+"' " +
        			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fdf.facturado!='5' ");
        	
        /*	System.out.print("SELECT fdf.cod_det_factura " +
			           "FROM fact_det_factura fdf, fact_enc_factura fef " +
				       "WHERE fdf.cod_paquete='"+codMov+"' " +
			           "AND fef.cod_admision='"+codAdm+"' " +
			           "AND fdf.hora='"+hora+"' " +
			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
			           "UNION " +
			           "SELECT fdf.cod_det_factura " +
			           "FROM fact_det_factura fdf, fact_enc_factura fef " +
			           "WHERE fdf.cod_programa='"+codMov+"' " +
			           "AND fef.cod_admision='"+codAdm+"' " +
			           "AND fdf.hora='"+hora+"' " +
			           "AND fef.cod_enc_factura=fdf.cod_enc_factura_fk");*/
        	
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerDetMovServicios "+ex);
        }	
        return rs;
    }

	/*
	public void actualizarMovUrg(String enca){	
		//System.out.println("actualizarMovUrg");
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_det_factura SET facturado='1' WHERE cod_enc_factura_fk='"+enca+"' and facturado!='5' ");
	     	System.out.println("UPDATE fact_det_factura SET facturado='1' WHERE cod_enc_factura_fk='"+enca+"' and facturado!='5'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>actualizarMovUrg "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	 */
	
	public void actualizarMovUrg(String enca){	
		System.out.println("actualizarMovUrg");
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_det_factura SET facturado='1' WHERE cod_enc_factura_fk='"+enca+"'");
	     	//System.out.println("UPDATE fact_det_factura SET facturado='1' WHERE cod_enc_factura_fk='"+enca+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>actualizarMovUrg "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
/*	
	public java.sql.ResultSet VerificarEncabezadoFacturado(String enca){	
		/**
		 */
 /*       java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from fact_numeradas where cod_enc_fact_fk="+enca+" and estadoFact=0");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>VerificarEncabezadoFacturado "+ex);
        }	
        return rs;
    }*/
	
	public java.sql.ResultSet VerificarEncabezadoFacturado(String enca){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from fact_numeradas where cod_enc_fact_fk="+enca+" ");
        	System.out.println("VERIFICARENCABEZADOFACTURADO --> select * from fact_numeradas where cod_enc_fact_fk="+enca+" ");
        	
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>VerificarEncabezadoFacturado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet datPac(String codEnca){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	/*rs=st.executeQuery("SELECT ae.nombre_entidad,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) paciente,ap.pac_codigo_paciente FROM adm_admisiones ad, adm_paciente ap,adm_convenio ac,adm_entidad ae " +
        			           "WHERE ad.adm_numero_ingreso='"+codAdm+"' " +
        			           "AND ad.pac_codigo_paciente_fk=ap.pac_codigo_paciente " +
        			           "AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato " +
        			           "AND ac.ent_nit_contratante_fk=ae.ent_nit");
        	System.out.println("datpac");
        	System.out.println("SELECT ae.nombre_entidad,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) paciente,ap.pac_codigo_paciente FROM adm_admisiones ad, adm_paciente ap,adm_convenio ac,adm_entidad ae " +
			           "WHERE ad.adm_numero_ingreso='"+codAdm+"' " +
			           "AND ad.pac_codigo_paciente_fk=ap.pac_codigo_paciente " +
			           "AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato " +
			           "AND ac.ent_nit_contratante_fk=ae.ent_nit");*/
        	rs=st.executeQuery("SELECT razon_social,nombre_paciente,pac_codigo_paciente_fk,fecha_ingreso,fecha_egreso,num_autorizacion FROM fact_enc_factura, adm_admisiones WHERE cod_enc_factura='"+codEnca+"' AND adm_numero_ingreso=cod_admision" );
        	System.out.println("SELECT razon_social,nombre_paciente,pac_codigo_paciente_fk,fecha_ingreso,fecha_egreso,num_autorizacion FROM fact_enc_factura, adm_admisiones WHERE cod_enc_factura='"+codEnca+"' AND adm_numero_ingreso=cod_admision" );
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>datPac "+ex);
        }	
        return rs;
    }
	
	
	///////////////////////////
	
	public void asignarCuentaCobroFact(String valor,String valorLetra,String cod_usuario,String fecha,String ea){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    if(ea.equals("0")){													
			    ps=con.conn.prepareStatement("INSERT INTO fact_factenviadas(valor,valorLetra,cod_usuario,fecha) VALUES(?,?,?,?)");
			    }
			    if(ea.equals("1")){													
				ps=con.conn.prepareStatement("INSERT INTO fact_factauditadas(valor,valorLetra,cod_usuario,fecha) VALUES(?,?,?,?)");
				//System.out.print("INSERT INTO fact_factauditadas(valor,valorLetra,cod_usuario,fecha) VALUES("+valor+","+valorLetra+","+cod_usuario+","+fecha+")");
			    }
			    ps.setString(1,valor);
			    ps.setString(2,valorLetra);
			    ps.setString(3,cod_usuario);
			    ps.setString(4,fecha);		
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>asignarCuentaCobroFact "+ex);
			}
		}
	
	public void asignarCuentaRad(String valor,String valorLetra,String cod_usuario,String fecha, String ri){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    if(ri.equals("0")){															
			    ps=con.conn.prepareStatement("INSERT INTO fact_factradicadas(valor,valorLetra,cod_usuario,fecha) VALUES(?,?,?,?)");
			    }
			    if(ri.equals("1")){															
				ps=con.conn.prepareStatement("INSERT INTO fact_factradicadasi(valor,valorLetra,cod_usuario,fecha) VALUES(?,?,?,?)");
				}
			    ps.setString(1,valor);
			    ps.setString(2,valorLetra);
			    ps.setString(3,cod_usuario);
			    ps.setString(4,fecha);		
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>asignarCuentaRad "+ex);
			}
		}
	
	
	
	public java.sql.ResultSet obtenerConsecutivoCC(String ea){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ea.equals("0")){			
        	rs=st.executeQuery("SELECT consEnvio FROM fact_factenviadas ORDER BY consEnvio DESC LIMIT 1");
        	}
        	if(ea.equals("1")){			
            rs=st.executeQuery("SELECT consAudita FROM fact_factauditadas ORDER BY consAudita DESC LIMIT 1");
            }
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerConsecutivoCC "+ex);
        }	
        return rs;
    }
	
	
	
	
	
	
	
	public void asignarCCAFact(String consCC,String consFact,String valornc){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_facturas_enviadas(consEnvio,codFact,valornc) VALUES (?,?,?)");
		    ps.setString(1,consCC);
		    ps.setString(2,consFact);
		    ps.setString(3,valornc);
		    
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoMovimientos>>asignarCCAFact "+ex);
		}
	}
	
	
	public void asignarCRAFact(String consCC,String consFact,String f,String ea,String valornc){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    if(ea.equals("0")){																		
		    ps=con.conn.prepareStatement("INSERT INTO fact_facturas_radicadas(consRadicado,codFact,fechaRadicado,valornc) VALUES (?,?,?,?)");
		    }
		    if(ea.equals("1")){																		
			ps=con.conn.prepareStatement("INSERT INTO fact_facturas_auditadas(consAudita,codFact,fechaAudita,valornc) VALUES (?,?,?,?)");
			//System.out.print("INSERT INTO fact_facturas_auditadas(consAudita,codFact,fechaAudita) VALUES ("+consCC+","+consFact+","+f+")");
		    }
		    if(ea.equals("2")){																		
			    ps=con.conn.prepareStatement("INSERT INTO fact_facturas_radicadasi(consRadicado,codFact,fechaRadicado,valornc) VALUES (?,?,?,?)");
			    }
		    
		    if(ea.equals("3")){																		
			    ps=con.conn.prepareStatement("INSERT INTO fact_facturas_dev(consdev,codFact,fechadev,valornc) VALUES (?,?,?,?)");
			    }

		    ps.setString(1,consCC);
		    ps.setString(2,consFact);		
		    ps.setString(3,f);	
		    ps.setString(4,valornc);
		    
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoMovimientos>>asignarCRAFact "+ex);
		}
	}
	
	
	
	public void actualizarEstFact(String codFactNum){	
		 
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_numeradas SET estadoFact='1' WHERE cod_fact_numerada='"+codFactNum+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>actualizarEstFact "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	
	
	

	
	public void recordEstadoFact(String codFact,String estado,String fecha,String usuario){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_movfacturas(codFactNumerada,estadoFact,fecha,usuario) VALUES (?,?,?,?)");
		    System.out.print("ya se inserttaron los mov");
		    ps.setString(1,codFact);
		    ps.setString(2,estado);		
		    ps.setString(3,fecha);	
		    ps.setString(4,usuario);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN. MetodoMovimientos>>recordEstadoFact "+ex);
		}
	}
	
	public java.sql.ResultSet datCTA(String codCTA){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT concat('CTA000',ffe.consEnvio),ffe.valor,ffe.fecha,CONCAT(sdt.nombre,' ',sdt.apellido) usuario " +
        			           "FROM  fact_factenviadas ffe,seg_usuario su,seg_datos_personales sdt " +
        			           "WHERE ffe.consEnvio = '"+codCTA+"' " +
        			           "AND ffe.cod_usuario=su.usu_codigo " +
        			           "AND su.dat_codigo_fk=sdt.dat_codigo ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>datCTA "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet datRAD(String codCTA){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT concat('RAD0',ffe.consRadicado),ffe.valor,ffe.fecha,CONCAT(sdt.nombre,' ',sdt.apellido) usuario " +
        			           "FROM  fact_factradicadas ffe,seg_usuario su,seg_datos_personales sdt " +
        			           "WHERE ffe.consRadicado = '"+codCTA+"' " +
        			           "AND ffe.cod_usuario=su.usu_codigo " +
        			           "AND su.dat_codigo_fk=sdt.dat_codigo ");
        //	System.out.println("este ya");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>datRAD "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet consolidadoCTA(String codCTA){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.consecutivo, " +
        			           "CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento, " +
        			           "fef.fecha_ingreso AS 'f.ingreso', " +
        			           "IF(fef.fecha_egreso <> NULL,fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
        			           "fef.valor,am.nombre,ae.nombre_entidad " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p,adm_admisiones adm, " +
        			           "adm_municipio am,fact_factenviadas ffe,fact_facturas_enviadas ffev " +
        			           "WHERE ffe.consEnvio='"+codCTA+"' " +
        			           "AND ffe.consEnvio=ffev.consEnvio " +
        			           "AND ffev.codFact=fn.cod_fact_numerada " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod " +
        			           "AND fef.cod_eps=ae.ent_nit");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>consolidadoCTA "+ex);
        }	
        return rs;
    }
	
	

	public java.sql.ResultSet consolidadoRAD(String codCTA){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fn.consecutivo, " +
        			           "CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
        			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento, " +
        			           "fef.fecha_ingreso AS 'f.ingreso', " +
        			           "IF(fef.fecha_egreso <> NULL,fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
        			           "fef.valor,ffev.fechaRadicado,ae.nombre_entidad " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p,adm_admisiones adm, " +
        			           "adm_municipio am,fact_factradicadas ffe,fact_facturas_radicadas ffev  " +
        			           "WHERE ffe.consRadicado='"+codCTA+"' " +
        			           "AND ffe.consRadicado=ffev.consRadicado " +
        			           "AND ffev.codFact=fn.cod_fact_numerada " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
        			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
        			           "AND p.mun_codigo_fk=am.muni_cod " +
        			           "AND fef.cod_eps=ae.ent_nit");

        	/*System.out.println("SELECT fn.consecutivo, " +
			           "CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) paciente, " +
			           "CONCAT(p.tipo_documento,' ',p.numero_documento) documento, " +
			           "fef.fecha_ingreso AS 'f.ingreso', " +
			           "IF(fef.fecha_egreso <> NULL,fef.fecha_egreso,'Sin alta') AS 'f.egreso', " +
			           "fef.valor,am.nombre,ae.nombre_entidad " +
			           "FROM fact_numeradas fn,fact_enc_factura fef,adm_entidad ae,adm_paciente p,adm_admisiones adm, " +
			           "adm_municipio am,fact_factradicadas ffe,fact_facturas_radicadas ffev  " +
			           "WHERE ffe.consRadicado='"+codCTA+"' " +
			           "AND ffe.consRadicado=ffev.consRadicado " +
			           "AND ffev.codFact=fn.cod_fact_numerada " +
			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
			           "AND adm.pac_codigo_paciente_fk=p.pac_codigo_paciente " +
			           "AND p.mun_codigo_fk=am.muni_cod " +
			           "AND fef.cod_eps=ae.ent_nit");
        			*/
        			
        			
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>consolidadoCTA "+ex);
        }	
        return rs;
    }
	

	
	public java.sql.ResultSet obtenerEstFactu(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
    		rs=st.executeQuery(" SELECT fn.consecutivo AS 'No Factura', "+
    							"fef.valor AS 'valor fatura', "+
    							"fn.fecha AS 'fecha creacion', "+
    							"IF(fn.estadoFact = '1','ENVIADA',IF(fn.estadoFact = '0','FACTURADA',IF(fn.estadoFact = '2','RADICADA',IF(fn.estadoFact = '3','GLOSA',IF(fn.estadoFact = '4','DEVUELTA',IF(fn.estadoFact = '5','ANULADA',IF(fn.estadoFact = '6','EN AUDITORIA',IF(fn.estadoFact = '7','Radicada Int','PARA REENVIO') "+
    							"))))))) AS estado, "+
    							"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario "+
    							"FROM fact_numeradas fn, "+
    							"fact_enc_factura fef, seg_usuario su, "+
    							"seg_datos_personales sdt "+
    							"WHERE fn.cod_enc_fact_fk = fef.cod_enc_factura "+
    							"AND fef.cod_usuario_fk=su.usu_codigo "+
    							"AND su.dat_codigo_fk = sdt.dat_codigo "+sql);
    		//System.out.println("obtenerEstFactu");
    		System.out.println(" SELECT fn.consecutivo AS 'No Factura', "+
					"fef.valor AS 'valor fatura', "+
					"fn.fecha AS 'fecha creacion', "+
					"IF(fn.estadoFact = '1','ENVIADA',IF(fn.estadoFact = '0','FACTURADA',IF(fn.estadoFact = '2','RADICADA',IF(fn.estadoFact = '3','GLOSA',IF(fn.estadoFact = '4','DEVUELTA',IF(fn.estadoFact = '5','ANULADA',IF(fn.estadoFact = '6','EN AUDITORIA',IF(fn.estadoFact = '7','Radicada Int','PARA REENVIO') "+
					"))))))) AS estado, "+
					"CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario "+
					"FROM fact_numeradas fn, "+
					"fact_enc_factura fef, seg_usuario su, "+
					"seg_datos_personales sdt "+
					"WHERE fn.cod_enc_fact_fk = fef.cod_enc_factura "+
					"AND fef.cod_usuario_fk=su.usu_codigo "+
					"AND su.dat_codigo_fk = sdt.dat_codigo "+sql);
        	
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerEstFactu "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet obtenerEstFact(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
    		rs=st.executeQuery(" SELECT fn.consecutivo AS 'No Factura',fef.valor AS 'valor fatura', fn.fecha AS 'fecha creacion', fmf.fecha AS 'Fecha de estado', IF(fmf.estadoFact='1','ENVIADA',IF(fmf.estadoFact='0','FACTURADA',IF(fmf.estadoFact='2','RADICADA',IF(fmf.estadoFact='3','GLOSA',IF(fmf.estadoFact='4','DEVUELTA',IF(fmf.estadoFact='5','ANULADA',IF(fmf.estadoFact='6','EN AUDITORIA',IF(fmf.estadoFact='7','Radicada Int','PARA REENVIO')))))))) AS estado ,CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario from fact_numeradas fn,fact_enc_factura fef,fact_movfacturas fmf,seg_usuario su,seg_datos_personales sdt where fn.cod_enc_fact_fk = fef.cod_enc_factura AND fn.cod_fact_numerada=fmf.codFactNumerada AND fmf.usuario=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND fn.estadoFact=fmf.estadoFact "+sql);
        //	System.out.println("obtenerEstFact");
    		System.out.println(" SELECT fn.consecutivo AS 'No Factura',fef.valor AS 'valor fatura', fn.fecha AS 'fecha creacion', fmf.fecha AS 'Fecha de estado', IF(fmf.estadoFact='1','ENVIADA',IF(fmf.estadoFact='0','FACTURADA',IF(fmf.estadoFact='2','RADICADA',IF(fmf.estadoFact='3','GLOSA',IF(fmf.estadoFact='4','DEVUELTA',IF(fmf.estadoFact='5','ANULADA',IF(fmf.estadoFact='6','EN AUDITORIA',IF(fmf.estadoFact='7','Radicada Int','PARA REENVIO')))))))) AS estado ,CONCAT(sdt.nombre,' ',sdt.apellido) AS usuario from fact_numeradas fn,fact_enc_factura fef,fact_movfacturas fmf,seg_usuario su,seg_datos_personales sdt where fn.cod_enc_fact_fk = fef.cod_enc_factura AND fn.cod_fact_numerada=fmf.codFactNumerada AND fmf.usuario=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND fn.estadoFact=fmf.estadoFact "+sql);
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerEstFact "+ex);
        }	
        return rs;
    }
	


public java.sql.ResultSet BuscarFact(String consFact){	
		/**
		 */
		
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT fn.consecutivo AS 'No Factura',fef.valor AS 'valor fatura',fn.fecha AS 'fecha creacion',fn.fecha AS 'Fecha de estado', 'FACTURADA' AS estado,CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario FROM fact_numeradas fn, fact_enc_factura fef,seg_datos_personales sdt,seg_usuario su WHERE fn.consecutivo='"+consFact+"' AND fn.cod_enc_fact_fk=fef.cod_enc_factura AND fef.cod_usuario_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>BuscarFact "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet obtenerDetMovFact(String consFact){	
		/**
		 */
		
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	/*rs=st.executeQuery("SELECT fn.consecutivo AS 'No Factura',fef.valor AS 'valor fatura',fn.fecha AS 'fecha creacion',fn.fecha AS 'Fecha de estado', " +
        			           "'FACTURADA' AS estado,CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario " +
        			           "FROM fact_numeradas fn, fact_enc_factura fef,seg_datos_personales sdt,seg_usuario su " +
        			           "WHERE fn.consecutivo='"+consFact+"' " +
        			           "AND fn.cod_enc_fact_fk=fef.cod_enc_factura " +
        			           "AND fef.cod_usuario_fk=su.usu_codigo " +
        			           "AND su.dat_codigo_fk=sdt.dat_codigo " +
        			           "UNION(SELECT fn.consecutivo AS 'No Factura', " +
        			           "fef.valor AS 'valor fatura', " +
        			           "fn.fecha AS 'fecha creacion', " +
        			           "fmf.fecha AS 'Fecha de estado'," +
        			           "IF(fmf.estadoFact = '1','ENVIADA',IF(fmf.estadoFact='1','ENVIADA',IF(fmf.estadoFact='0','FACTURADA',IF(fmf.estadoFact='2','RADICADA',IF(fmf.estadoFact='3','GLOSA',IF(fmf.estadoFact='4','DEVUELTA',IF(fmf.estadoFact='5','ANULADA',IF(fmf.estadoFact='6','EN AUDITORIA',IF(fmf.estadoFact='7','Radicada Int','PARA REENVIO')))))))) AS estado, " +
        			           "CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef,fact_movfacturas fmf, " +
        			           "seg_usuario su, " +
        			           "seg_datos_personales sdt " +
        			           "WHERE fn.consecutivo='"+consFact+"' " +
        			           "AND fn.cod_enc_fact_fk = fef.cod_enc_factura " +
        			           "AND fn.cod_fact_numerada=fmf.codFactNumerada " +
        			          // "AND fn.estadoFact=fmf.estadoFact " +
        			           "AND fmf.usuario=su.usu_codigo " +
        			           "AND su.dat_codigo_fk=sdt.dat_codigo )");
        	*/				


   	 rs=st.executeQuery("SELECT fn.consecutivo AS 'No Factura', fef.valor AS 'valor fatura', fn.fecha AS 'fecha creacion', fmf.fecha AS 'Fecha de estado', IF(fmf.estadoFact='1','ENVIADA',IF(fmf.estadoFact='0','FACTURADA',IF(fmf.estadoFact='2','RADICADA',IF(fmf.estadoFact='3','GLOSA',IF(fmf.estadoFact='4','DEVUELTA',IF(fmf.estadoFact='5','ANULADA',IF(fmf.estadoFact='6','EN AUDITORIA',IF(fmf.estadoFact='7','Radicada Int','PARA REENVIO')))))))) AS estado, CONCAT(sdt.nombre, ' ', sdt.apellido) AS usuario FROM fact_numeradas fn,fact_enc_factura fef,fact_movfacturas fmf, seg_usuario su, seg_datos_personales sdt WHERE fn.consecutivo='"+consFact+"' AND fn.cod_enc_fact_fk = fef.cod_enc_factura AND fn.cod_fact_numerada=fmf.codFactNumerada AND fmf.usuario=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo ");
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerDetMovFact "+ex);
        }	
        return rs;
    }
	
	public String formatMoneda(String valor){
		
		String temp2="";
		String temp1="";
		int ud=1;
		int logCad = valor.length();
		
		for (int i=logCad-1;i>=0;i--){
			
			temp2=valor.substring(i,i+1);

			temp2+=temp1;
			if(ud==3){
				if(i!=0){
					temp1="."+temp2;
				}else{
					temp1=temp2;
				}
				ud=0;
			}else{
				temp1=temp2;
			}
			ud++;
		}
		temp1="$ "+temp1;
		return temp1;
	}
	
	
	public java.sql.ResultSet obtenerAdmSinFact(String sql){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("(SELECT adm.adm_numero_ingreso,ap.nombre,ap.primer_apellido,ap.segundo_apellido, " +
			           "ap.tipo_documento,ap.numero_documento,ae.nombre_entidad,fef.fecha_ingreso, " +
			           "IF(fef.fecha_egreso IS NOT NULL,fef.fecha_egreso,'Sin alta') fecha_ingreso, " +
			           "SUM(fdf.cantidad*fdf.valor) total " +
			           "FROM fact_det_factura fdf, fact_enc_factura fef, adm_admisiones adm, adm_paciente ap, " +
			           "adm_convenio ac, adm_entidad ae " +
			           "WHERE fdf.cod_enc_factura_fk NOT IN ( SELECT fef.cod_enc_factura FROM fact_enc_factura fef,fact_numeradas fn WHERE fef.cod_enc_factura = fn.cod_enc_fact_fk ) " +
			           "AND fdf.cod_enc_factura_fk=fef.cod_enc_factura " +
			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
			           "AND adm.atendido <> '1' " +
			           "AND adm.estado <> '2' " +
			           "AND adm.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
			           "AND ap.conv_numero_contrato_fk = ac.conv_numero_contrato " +
			           "AND ac.ent_nit_contratante_fk = ae.ent_nit "+sql+") " +
			           "UNION " +
			           "(SELECT adm.adm_numero_ingreso ,ap.nombre,ap.primer_apellido,ap.segundo_apellido, ap.tipo_documento,ap.numero_documento, " +
			           "ae.nombre_entidad, fef.fecha_ingreso,IF(fef.fecha_egreso IS NOT NULL,fef.fecha_egreso,'Sin alta') fecha_ingreso, " +
			           "'0' total " +
			           "FROM fact_enc_factura fef, " +
			           "adm_admisiones adm, adm_paciente ap, adm_convenio ac, adm_entidad ae " +
			           "WHERE fef.cod_enc_factura NOT IN ( SELECT DISTINCT fef.cod_enc_factura FROM fact_enc_factura fef,fact_det_factura fdf WHERE fef.cod_enc_factura = fdf.cod_enc_factura_fk ) " +
			           "AND fef.cod_admision=adm.adm_numero_ingreso " +
			           "AND adm.atendido <> '1' " +
			           "AND adm.estado <> '2' " +
			           "AND adm.pac_codigo_paciente_fk = ap.pac_codigo_paciente " +
			           "AND ap.conv_numero_contrato_fk = ac.conv_numero_contrato " +
			           "AND ac.ent_nit_contratante_fk = ae.ent_nit " +sql+") " +
			           "ORDER BY adm_numero_ingreso");
        	
        	
        	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerAdmSinFact "+ex);
        }	
        return rs;
    }
	
	public ResultSet listarRefU(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	r=st.executeQuery("SELECT DISTINCT 1,mt.manual_base,p.cod_programa,p.cod_referencia,p.descripcion,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico " +
	        			          "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = p.cod_programa " +
	        			          "AND p.cod_referencia LIKE '"+texto+"%' " +
	        			          "AND p.clase_servicio=cs.cod_clase_servicio");
	        	
	        	
	     
	      return r;
	}

	public ResultSet listarPYSU(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	r=st.executeQuery("SELECT DISTINCT 1,mt.manual_base,p.cod_programa,p.cod_referencia,p.descripcion,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico " +
	        			          "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = p.cod_programa " +
	        			          "AND p.descripcion LIKE '"+texto+"%' " +
	        			          "AND p.clase_servicio=cs.cod_clase_servicio");   	
	     
	      return r;
	}
	
	public java.sql.ResultSet obtenerValorAdmFact(String codAdm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT SUM(fef.valor) " +
        			           "FROM fact_numeradas fn,fact_enc_factura fef " +
        			           "WHERE fef.cod_admision = '"+codAdm+"' " +
        			           "AND fef.cod_enc_factura = fn.cod_enc_fact_fk ");	
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerValorAdmFact "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet obtenerEspPrograma(String codPr,String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.especialidad,t.valor " +
        			           "FROM fact_programas fp,fact_tarifas t, fact_manuales_tarifarios fmf " +
        			           "WHERE fmf.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio) " +
        			           "AND fmf.cod_manual_tarifario=t.manual_tarifario " +
        			           "AND t.programa = fp.cod_programa " +
        			           "AND fp.cod_programa = "+codPr);
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>obtenerEspPrograma "+ex);
        }	
        return rs;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

public java.sql.ResultSet listarPH(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.cod_referencia, fp.descripcion FROM fact_programas fp WHERE fp.cod_programa='"+texto+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarPH "+ex);
        }	
        return rs;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public java.sql.ResultSet listarSYPHemodinamia(String eps){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT p.cod_programa,p.cod_referencia,p.descripcion,p.actoqx, " +
        			           "t.valor,p.medico,p.idporcentajeqx,fcs.descripcion " +
        			           "FROM fact_manuales_tarifarios fmf, fact_tarifas t, " +
        			           "fact_programas p,fact_progs_paquetes pp,fact_clases_servicio fcs, fact_paquetes fpq " +
        			           "WHERE fmf.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+eps+" AND fc.cod_convenio = ftc.cod_convenio)" +
        			           "AND fmf.cod_manual_tarifario=t.manual_tarifario " +
        			           "AND t.programa=p.cod_programa " +
        			           "AND p.cod_programa=pp.cod_programa " +
        			           "AND pp.cod_paquete = fpq.cod_paquete " +
        			           "AND fpq.descripcion LIKE 'hemodinamia%' " +
        			           "AND p.clase_servicio = fcs.cod_clase_servicio");
        	
        	
        	
        	

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>listarSYPHemodinamia "+ex);
        }	
        return rs;
    }
	
	//////////////////////////
public java.sql.ResultSet ManualesConvenio(String CodEnt){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT fmt.descripcion FROM fact_tarifas_convenios ftc,fact_convenios fc,fact_manuales_tarifarios fmt WHERE ftc.cod_convenio=fc.cod_convenio AND ftc.cod_manualTarifario=fmt.cod_manual_tarifario AND fc.cod_entidad='"+CodEnt+"' ");
    	
      }
    
 
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ManualesConvenio "+ex);
    }	
    return rs;
}
public java.sql.ResultSet GenerarConveniosxff(){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT c.cod_convenio, c.num_contrato,e.nombre_entidad,c.fecha_ini,c.fecha_final,c.valor,c.autoriza,c.observacion,c.cod_entidad  FROM fact_convenios c, adm_entidad e WHERE c.cod_entidad=e.ent_nit AND c.estado='0' ORDER BY c.fecha_final");
    	
      }
    
 
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>GenerarConveniosxff "+ex);
    }	
    return rs;
}



public java.sql.ResultSet GenerarConveniosxTope(){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT c.cod_convenio, c.num_contrato,e.nombre_entidad,c.fecha_ini,c.fecha_final,c.valor,a.tope,a.consumido, c.valor-consumido AS diferencia,e.ent_nit "+
    						"FROM fact_convenios c, adm_entidad e, fact_audita_convenio a "+
    						"WHERE c.cod_entidad=e.ent_nit AND c.estado='0' AND c.valor>0  AND c.cod_convenio=a.cod_convfk "+
    						"ORDER BY e.nombre_entidad ");
    	
      }
    
 
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>GenerarConveniosxff "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsumidoCartera(String fi, String ff, String eps){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT SUM(ccp.plazo_corto + ccp.plazo_30 + ccp.plazo_60 + ccp.plazo_90 + ccp.plazo_180 + ccp.plazo_360) AS total_fact FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas cc,adm_entidad aen,empresa emp WHERE aen.ent_nit = cc.nit AND cc.codigo = cf.cod_cuenta_fk AND cf.codigo = ccp.cod_factura_fk AND cf.precio_factura > 0 AND cf.fecha_factura BETWEEN  '"+fi+"' AND  '"+ff+"' AND cc.nit= "+eps+" GROUP BY cf.cod_cuenta_fk ");
    	//oSystem.out.println("SELECT SUM(ccp.plazo_corto + ccp.plazo_30 + ccp.plazo_60 + ccp.plazo_90 + ccp.plazo_180 + ccp.plazo_360) AS total_fact FROM cont_cartera_plazo ccp,cont_factura cf,cont_cuentas_empresas cc,adm_entidad aen,empresa emp WHERE aen.ent_nit = cc.nit AND cc.codigo = cf.cod_cuenta_fk AND cf.codigo = ccp.cod_factura_fk AND cf.precio_factura > 0 AND cf.fecha_factura BETWEEN  '"+fi+"' AND  '"+ff+"' AND cc.nit= "+eps+" GROUP BY cf.cod_cuenta_fk ");
      }
    
 
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsumidoCartera "+ex);
    }	
    return rs;
}	


////////////////////Estos dos metodos son para actualizar el estado en contabilidad/////////////////
public java.sql.ResultSet obtenerConsecutivoFactNum(String codFact){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT consecutivo FROM fact_numeradas WHERE cod_fact_numerada='"+codFact+"' ");
      }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>obtenerConsecutivoFactNum "+ex);
    }	
    return rs;
}


public void actualizarFechaRadicacionCarteraPlazo(String ConsFact,String FechaRad){	
	 
	 PreparedStatement st = null;
    try{
    	Conexion con=new Conexion();
    	st= con.conn.prepareStatement("UPDATE cont_cartera_plazo SET fecha_radicado='"+FechaRad+"' WHERE numero_factura='"+ConsFact+"'");
    	st.executeUpdate();	 
    	st.close();
    	con.cerrar();
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>actualizarFechaRadicacionCarteraPlazo "+ex);
    	ex.getMessage();	     
    }	
}




public void actualizarEstFactRadicadaenContabilidad(String ConsFact){	
	 
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE cont_factura SET estado='2' WHERE numero_factura='"+ConsFact+"'");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoMovimientos>>actualizarEstFactRadicadaenContabilidad "+ex);
     	ex.getMessage();	     
     }	
 }


/////////////////////COSTOS DE DISPENSACION//////////

public java.sql.ResultSet ConsultarProgramasValores(String enca){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	//rs=st.executeQuery(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura, fact_prog_med WHERE cod_programafk=cod_prog AND cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk   ");
    	rs=st.executeQuery(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura WHERE cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk   ");
    	System.out.println("yyyyyyyyyyyyyyyyyyyy SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura WHERE cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk   ");

    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarProgramasValores "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsecutivoUDocumento(String cod){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT codigo FROM cont_documentos WHERE numero_documento='"+cod+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsecutivoUDocumento "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarProgramasCostos(String adm, String prog){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT SUM(cunitario), COUNT(codigo), SUM(ctotal) AS costo FROM farc_costosdedispensacion WHERE cod_admfk='"+adm+"' AND cod_programafk='"+prog+"'   ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarProgramasCostos "+ex);
    }	
    return rs;
}


public void actualizarCostosdeDispensaciont(String e, String vi, String ci){	
		System.out.println("ActualizarEncabezado "+e);
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" UPDATE fact_enc_factura SET valor_inventario='"+vi+"', costo_inventario='"+ci+"' where cod_enc_factura='"+e+"'  ");
	     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>actualizarCostosdeDispensaciont "+ex);
	     	ex.getMessage();	     
	     }	
	 }
/////////////////////FIN COSTOS DE DISPENSACION//////////
////////////////DOCUMENTOS DESDE FACTURACION////////////
public java.sql.ResultSet ConsecutivosdeCuentas(String cod){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT sigla, consecutivo FROM cont_tipo_documento WHERE codigo="+cod+" ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsecutivosdeCuentas "+ex);
    }	
    return rs;
}

public void CrearDocumento(String ac,String pc,String td,String nd,String fd,String dd,String vd,String vc,String pd,String bd,String user,String fc,String hc){
	
	try{
			PreparedStatement ps = null;
			 Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO cont_documentos(anio,periodo,tipo_documento_fk,numero_documento,fecha,detalle,valor_debito,valor_credito,cod_plantilla_fk,valor_base,cod_usaurio,fecha_creacion,hora_creacion)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,ac);
		    ps.setString(2,pc);
		    ps.setString(3,td);
		    ps.setString(4,nd);
		    ps.setString(5,fd);
		    ps.setString(6,dd);
		    ps.setString(7,vd);
		    ps.setString(8,vc);
		    ps.setString(9,pd);
		    ps.setString(10,bd);
		    ps.setString(11,user);
		    ps.setString(12,fc);
		    ps.setString(13,hc);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoDocumentos>>CrearDocumento "+ex);
        	try {
        		Conexion.Log("ERROR EN MetodoDocumentos>>CrearDocumento "+ex+"");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


public void CrearDetalleDocumento(String ac,String pc,String td,String nd,String fd,String dd,String vd,String vc,String pd,String bd,String dif,String ct){
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO cont_detalle_documentos (cod_documento_fk,cod_cuenta_fk,cod_sucursal_costo_fk,cod_centro_subcentro_fk,cod_terceros_fk,descripcion,valor_base,documento_referencia,valor_debito,valor_credito,cod_diferido_fk,origen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,ac);
		    ps.setString(2,pc);
		    ps.setString(3,td);
		    ps.setString(4,nd);
		    ps.setString(5,fd);
		    ps.setString(6,dd);
		    ps.setString(7,vd);
		    ps.setString(8,vc);
		    ps.setString(9,pd);
		    ps.setString(10,bd);
		    ps.setString(11,dif);
		    ps.setString(12,ct);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoDocumentos>>CrearDetalleDocumento "+ex);
		}
	}


public void ActualizarDocumentoDebitoCredito(String docu, String vd, String vc){	
	/**
	 */
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE cont_documentos SET valor_debito='"+vd+"', valor_credito='"+vc+"' WHERE codigo='"+docu+"' ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoDocumentos>>ActualizarDocumentoDebitoCredito "+ex);
     	ex.getMessage();	     
     }	
 }

public java.sql.ResultSet Consultarcuentasdeprogramas(String cc, String scc, String p){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cuentaifk,cuentacfk,cuentagfk FROM cont_ninterfaz_facturacion WHERE centrocostofk="+cc+" AND subcentrofk="+scc+" AND programafk="+p+" ");
    	System.out.println("xxxxxxxxxxxxxxxxxxxxx SELECT cuentaifk,cuentacfk,cuentagfk FROM cont_ninterfaz_facturacion WHERE centrocostofk="+cc+" AND subcentrofk="+scc+" AND programafk="+p+" ");
            
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>Consultarcuentasdeprogramas "+ex);
    }	
    return rs;
}



public java.sql.ResultSet ConsultarcuentaIngresoClientes(){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cod_cta_clientesfk,nom_cta_clientes FROM cont_datosiniciales WHERE estado=0 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarcuentaIngreso "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarcuentaCopago(){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cod_cta_copagosfk,nom_cta_copagos FROM cont_datosiniciales WHERE estado=0 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarcuentaCopago "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarcuentaModeracion(){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT cod_cta_cuotamoderadorafk,nom_cta_cuotamoderadora  FROM cont_datosiniciales WHERE estado=0 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarcuentaModeracion "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarCuentaNc(String TipoPago){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT cc.* FROM cont_movimientos_credito cmc,cont_cuentas cc WHERE cmc.codigo = "+TipoPago+" AND cc.codigo=cmc.CodCuenta_fk ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarCuentaNc "+ex);
    }	
    return rs;
}



public java.sql.ResultSet Consultarcopagoymoderacionodescuento(String cod){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT copago,moderacion FROM fact_enc_factura WHERE cod_enc_factura="+cod+" ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>Consultarcopagoymoderacionodescuento "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarTercerosconNIT(String cod){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT codigo FROM cont_terceros WHERE numero_documento='"+cod+"'  AND tipo_identificacion='NIT' ");
    	System.out.println(" SELECT codigo FROM cont_terceros WHERE numero_documento='"+cod+"'  AND tipo_identificacion='NIT' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarTercerosconNIT "+ex);
    }	
    return rs;
}


public void ActualizarConsecutivo(String ctan, String td){	
	/**
	 */
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE cont_tipo_documento SET consecutivo='"+ctan+"' WHERE codigo='"+td+"' ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoDocumentos>>ActualizarConsecutivo "+ex);
     	ex.getMessage();	     
     }	
 }

	




//////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////FACTURACION CAPITADA////////////

public java.sql.ResultSet EmpresasC(){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT c.cod_convenio,c.valor,c.cod_entidad,c.fecha_final,c.estado,e.ent_nit,e.ent_nit_contratante,e.nombre_entidad,e.estado FROM fact_convenios c, adm_entidad e, fact_modalidades_convenio m WHERE e.ent_nit=c.cod_entidad AND c.estado=0 AND c.fecha_final>=CURDATE() AND c.cod_convenio=m.cod_convenio_fk AND m.cod_modalidad_fk=5 ORDER BY e.nombre_entidad ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>EmpresasC "+ex);
    }	
    return rs;
}

public java.sql.ResultSet BuscarPacientesC( String sql){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, a.fecha_registro," +
    			"0,c.cod_convenio,pcc.cod_pcte_convenio,pcc.urg,pcc.hos,pcc.amb,pcc.cex,pcc.pyp " +
    			"FROM adm_admisiones a, fact_enc_factura fe, fact_convenios c, fact_pcte_convenio_capita pcc " +
    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3') " +
    			"AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk AND c.cod_convenio=pcc.cod_convenio_fk AND pcc.estado=0   " +sql+
				"GROUP BY fe.cod_enc_factura");

    	System.out.println(" SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, a.fecha_registro," +
    			"0,c.cod_convenio,pcc.cod_pcte_convenio,pcc.urg,pcc.hos,pcc.amb,pcc.cex,pcc.pyp " +
    			"FROM adm_admisiones a, fact_enc_factura fe, fact_convenios c, fact_pcte_convenio_capita pcc " +
    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3')  " +
    			"AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk AND c.cod_convenio=pcc.cod_convenio_fk AND pcc.estado=0   " +sql+
"GROUP BY fe.cod_enc_factura  ");
    	    	
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>BuscarPacientesC "+ex);
    }	
    return rs;
}

/***NUEVA CONSULTA QUE SACA LOS QUE NO TIENEN CARGUE TAMBIEN nos une la de arriba con la de abajo**/
/*
SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, a.fecha_registro,SUM(df.cantidad*df.valor),c.cod_convenio,pcc.cod_pcte_convenio,pcc.urg,pcc.hos,pcc.amb,pcc.cex,pcc.pyp
FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df, fact_convenios c, fact_pcte_convenio_capita pcc
WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND fe.estado='0' AND fe.cod_enc_factura=df.cod_enc_factura_fk
AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk AND c.cod_convenio=pcc.cod_convenio_fk AND pcc.estado=0

GROUP BY fe.cod_enc_factura
UNION
SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, a.fecha_registro,0,c.cod_convenio,pcc.cod_pcte_convenio,pcc.urg,pcc.hos,pcc.amb,pcc.cex,pcc.pyp
FROM adm_admisiones a, fact_enc_factura fe, fact_convenios c, fact_pcte_convenio_capita pcc
WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND fe.estado='0' 
AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk AND c.cod_convenio=pcc.cod_convenio_fk AND pcc.estado=0
AND fe.cod_enc_factura NOT IN (
SELECT fe.cod_enc_factura
FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df, fact_convenios c, fact_pcte_convenio_capita pcc
WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND fe.estado='0' AND fe.cod_enc_factura=df.cod_enc_factura_fk
AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk AND c.cod_convenio=pcc.cod_convenio_fk AND pcc.estado=0
GROUP BY fe.cod_enc_factura)
GROUP BY fe.cod_enc_factura

*/

public java.sql.ResultSet BuscarPacientesCSinCargue( String sql){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT df.cantidad*df.valor FROM fact_enc_factura fe, fact_det_factura df WHERE fe.cod_enc_factura="+sql+" AND fe.cod_enc_factura=df.cod_enc_factura_fk");
    	
     }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>BuscarPacientesCSinCargue "+ex);
    }	
    return rs;
}


public java.sql.ResultSet Convenios(String eps){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT c.cod_convenio,c.num_contrato,c.valor,c.fecha_final FROM fact_convenios c WHERE c.cod_entidad='"+eps+"' AND c.estado=0 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>Convenios "+ex);
    }	
    return rs;
}



public java.sql.ResultSet ConceptoCapitado(){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT Concepto_Capitado FROM empresa ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConceptoCapitado "+ex);
    }	
    return rs;
}




public void ActualizarEncayAdmisionesCapitadas( String sql){		
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     st= con.conn.prepareStatement(" UPDATE adm_admisiones a, fact_enc_factura fe, fact_convenios c, fact_pcte_convenio_capita pcc  SET a.atendido='1',fe.estado='1'  " +
    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND fe.estado='0'  " +
    			"AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk AND c.cod_convenio=pcc.cod_convenio_fk AND pcc.estado=0   "+sql+"  ");
   
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoMovimientos>>ActualizarEncayAdmisionesCapitadas "+ex);
     	ex.getMessage();	     
     }	
 }


public java.sql.ResultSet obtenerInfoEncaEnca(String enca){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT * FROM fact_enc_factura fef WHERE fef.cod_enc_factura='"+enca+"'  ");	
    	System.out.println("yyyyyySELECT * FROM fact_enc_factura fef WHERE fef.cod_enc_factura='"+enca+"' ");
    	 }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>obtenerInfoEncaEnca "+ex);
    }	
    return rs;
}



public java.sql.ResultSet obtenerPacientesCapita(String sql){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("  SELECT fe.cod_enc_factura  FROM adm_admisiones a, fact_enc_factura fe, fact_convenios c, fact_pcte_convenio_capita pcc  WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND fe.estado='0'  AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk  AND c.cod_convenio=pcc.cod_convenio_fk  AND pcc.estado=0  "+sql);
    	System.out.println("pppppppppppppp  SELECT fe.cod_enc_factura  FROM adm_admisiones a, fact_enc_factura fe, fact_convenios c, fact_pcte_convenio_capita pcc  WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND fe.estado='0'  AND fe.cod_eps=c.cod_entidad AND a.pac_codigo_paciente_fk=pcc.cod_pcte_fk  AND c.cod_convenio=pcc.cod_convenio_fk  AND pcc.estado=0  "+sql);
     }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>obtenerPacientesCapita "+ex);
    }	
    return rs;
}



public void InsertarAgrupacionCapitada(String n,String e,String f,String h,String t){
	try{
		PreparedStatement ps = null;
	    Conexion con=new Conexion();
	    															
	    ps=con.conn.prepareStatement("INSERT INTO fact_facturasagrupadas(fact_numeradafk,enca_facturafk,fecha,hora,tipo) VALUES (?,?,?,?,?)");
	    System.out.print("ya se insertaron los mov en fact_facturasagrupadas "+n+" - "+e+" - "+f+" - "+h+" - "+t);
	    ps.setString(1,n);
	    ps.setString(2,e);		
	    ps.setString(3,f);	
	    ps.setString(4,h);
	    ps.setString(5,t);
	 	ps.executeUpdate();
		ps.close();
		con.cerrar();				
	}catch(Exception ex){
    	System.out.println("ERROR EN. MetodoMovimientos>>InsertarAgrupacionCapitada "+ex);
	}
}



///////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////FACTURACION AGRUPADA////////////////////

public java.sql.ResultSet EmpresasA(){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT c.cod_convenio,c.valor,c.cod_entidad,e.ent_nit,e.ent_nit_contratante,e.nombre_entidad FROM fact_convenios c, adm_entidad e, fact_modalidades_convenio m WHERE e.ent_nit=c.cod_entidad AND c.estado=0 AND c.fecha_final>=CURDATE()  AND c.cod_convenio=m.cod_convenio_fk AND m.cod_modalidad_fk=4  ORDER BY e.nombre_entidad  ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>EmpresasA "+ex);
    }	
    return rs;
}


public java.sql.ResultSet BuscarPacientesA( String sql){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("  SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, " +
    			"a.fecha_registro,df.nombre_programa, SUM(df.cantidad*df.valor),fe.copago " +
    			"FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df , fact_convenios c, cont_movimientos_agrupados m " +
    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3') AND fe.cod_enc_factura=df.cod_enc_factura_fk " +
    			"AND fe.cod_eps=c.cod_entidad  AND a.adm_numero_ingreso=m.CodAdm_fk AND fe.cod_enc_factura=m.CodEnc_fk "+sql+"  ");
    	
    	
    	System.out.println("  SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, " +
    	    			"a.fecha_registro,df.nombre_programa, SUM(df.cantidad*df.valor),fe.copago " +
    	    			"FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df , fact_convenios c, cont_movimientos_agrupados m " +
    	    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3') AND fe.cod_enc_factura=df.cod_enc_factura_fk " +
    	    			"AND fe.cod_eps=c.cod_entidad  AND a.adm_numero_ingreso=m.CodAdm_fk AND fe.cod_enc_factura=m.CodEnc_fk "+sql+"  ");
    			
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>BuscarPacientesA "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConceptoAgrupado(){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT Concepto_Agrupacion FROM empresa ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConceptoAgrupado "+ex);
    }	
    return rs;
}


public void ActualizarEncayAdmisionesAgrupadas( String enca){		
	//System.out.println("ActualizarEncabezado "+e);
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     st= con.conn.prepareStatement(" UPDATE adm_admisiones a, fact_enc_factura fe  SET a.atendido='1',fe.estado='1' WHERE  fe.cod_admision=a.adm_numero_ingreso AND fe.cod_enc_factura='"+enca+"'  ");
   
     	System.out.println(" ttttttttttttttttttt   UPDATE adm_admisiones a, fact_enc_factura fe  SET a.atendido='1',fe.estado='1' WHERE  fe.cod_admision=a.adm_numero_ingreso AND fe.cod_enc_factura='"+enca+"'  ");
     
     	st.executeUpdate();	  
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoMovimientos>>ActualizarEncayAdmisionesAgrupadas "+ex);
     	ex.getMessage();	     
     }	
 }


public void ActualizarMovimientosAgrupados( String enca, String e){		
	PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     st= con.conn.prepareStatement(" UPDATE cont_movimientos_agrupados SET estado='"+e+"' WHERE CodEnc_fk='"+enca+"'  ");
   
     	System.out.println("ActualizarMovimientosAgrupados:::::: UPDATE cont_movimientos_agrupados SET estado='"+e+"' WHERE CodEnc_fk='"+enca+"'  ");
     
     	st.executeUpdate();	  
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoMovimientos>>ActualizarMovimientosAgrupados "+ex);
     	ex.getMessage();	     
     }	
 }


public java.sql.ResultSet ConsultarCuentasAgrupadas(String fi,String ff,String eps,String e){	
	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT CodCuenta_fk,CodSucursal_fk,CodCentroSubcentro_fk,CodTercero_fk,descripcion,SUM(valor_programa) FROM cont_movimientos_agrupados,fact_programas  WHERE Fecha_insercion BETWEEN '"+fi+"' AND '"+ff+"' AND estado='"+e+"' AND CodEntidad_fk='"+eps+"' AND CodPro_fk=cod_programa GROUP BY CodCuenta_fk,CodSucursal_fk,CodCentroSubcentro_fk ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoDocumentos>>ConsultarCuentasAgrupadas "+ex);
    }	
    return rs;
}



public java.sql.ResultSet NumFacturasMultiples(String t){	
	System.out.println("NumFacturas");
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT consecutivo,sigla_consecutivo FROM fact_numeros_fact WHERE tipo_factura='"+t+"' ");
    	System.out.println(" SELECT consecutivo,sigla_consecutivo FROM fact_numeros_fact WHERE tipo_factura='"+t+"' ");
      }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>NumFacturas "+ex);
    }	
    return rs;
}


public void ActualizarNumFacturasMultiples(String valor,String t){	
	System.out.println("ActualizarNumFacturas");
	 PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement(" UPDATE fact_numeros_fact SET consecutivo='"+valor+"'  where tipo_factura='"+t+"' ");
     	System.out.println("UPDATE fact_numeros_fact SET consecutivo='"+valor+"'  where tipo_factura='"+t+"'  " );
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("Error en MetodoMovimientos>>ActualizarNumFacturasMultiples "+ex);
     	ex.getMessage();	     
     }	
 }





public java.sql.ResultSet Consultarcopagoytotal(String enca){	
	System.out.println("NumFacturas");
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT SUM(d.valor*d.cantidad),e.copago FROM fact_det_factura d,fact_enc_factura e WHERE e.cod_enc_factura=d.cod_enc_factura_fk AND cod_enc_factura_fk='"+enca+"'  ");
    	}
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>Consultarcopagoytotal "+ex);
    }	
    return rs;
}




////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////ANEXOS TECNICOS/////////////////////

public java.sql.ResultSet Anexos(){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT a.adm_numero_ingreso,a.fecha_registro,a.hora_registro,p.nombre,p.primer_apellido,p.segundo_apellido,nombre_entidad,regimen FROM adm_admisiones a,adm_paciente p,adm_convenio c,adm_entidad e WHERE a.estado!=2 AND CURDATE()-a.fecha_registro<2 AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND p.conv_numero_contrato_fk=c.conv_numero_contrato AND c.ent_nit_contratante_fk=e.ent_nit  AND regimen='Subsidiado'  AND a.aurg='1' AND a.ahosp='0' ORDER BY a.fecha_registro DESC ,a.hora_registro  DESC ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>Anexos "+ex);
    }	
    return rs;
}

public java.sql.ResultSet Anexosninojesus(){	//sin regimen
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT a.adm_numero_ingreso,a.fecha_registro,a.hora_registro,p.nombre,p.primer_apellido,p.segundo_apellido,nombre_entidad,regimen FROM adm_admisiones a,adm_paciente p,adm_convenio c,adm_entidad e WHERE a.estado!=2 AND CURDATE()-a.fecha_registro<2 AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND p.conv_numero_contrato_fk=c.conv_numero_contrato AND c.ent_nit_contratante_fk=e.ent_nit  AND a.aurg='1' AND a.ahosp='0' ORDER BY a.fecha_registro DESC ,a.hora_registro  DESC ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>Anexosninojesus "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarDx(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT codDiagnostico FROM hic_diagnosticoingreso WHERE codAdm='"+cod+"' ");
    	//System.out.println(" SELECT codDiagnostico FROM hic_diagnosticoingreso WHERE codAdm='"+cod+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarDx "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarAnexo2(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT codigo FROM fact_anexos_tecnicos WHERE admisionfk='"+cod+"' ");
    	//System.out.println(" SELECT codDiagnostico FROM hic_diagnosticoingreso WHERE codAdm='"+cod+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarAnexo2 "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarDatosAnexo2(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT a.fecha_registro,a.hora_registro,a.registrado_por,a.remision,p.nombre,p.primer_apellido,p.segundo_apellido,td.descripcion,p.numero_documento,p.direccion,p.telefono_celular,p.email,m.mun_codigo,p.fecha_nacimiento,p.barrio,e.nombre_entidad,e.cod_prestador,m.nombre,d.nombre,d.dept_codigo,e.regimen,dp.nombre,dp.apellido,dp.ocupacion  " +
    			"FROM adm_admisiones a,adm_paciente p,adm_convenio c,adm_entidad e,adm_municipio m,adm_departamento d,seg_usuario u,seg_datos_personales dp,adm_tipodocumento td " +
    			"WHERE a.adm_numero_ingreso='"+cod+"'  AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND p.conv_numero_contrato_fk=c.conv_numero_contrato AND c.ent_nit_contratante_fk=e.ent_nit " +
    			"AND p.mun_codigo_fk=m.muni_cod AND m.dept_codigo_fk=d.dept_codigo  AND a.registrado_por=u.usuario AND u.dat_codigo_fk=dp.dat_codigo  AND p.tipo_documento=td.sigla ");
    	//System.out.println(" SELECT codDiagnostico FROM hic_diagnosticoingreso WHERE codAdm='"+cod+"' ");
    	System.out.println(" SELECT a.fecha_registro,a.hora_registro,a.registrado_por,a.remision,p.nombre,p.primer_apellido,p.segundo_apellido,td.descripcion,p.numero_documento,p.direccion,p.telefono_celular,p.email,m.mun_codigo,p.fecha_nacimiento,p.barrio,e.nombre_entidad,e.cod_prestador,m.nombre,d.nombre,d.dept_codigo,e.regimen,dp.nombre,dp.apellido,dp.ocupacion  " +
    			"FROM adm_admisiones a,adm_paciente p,adm_convenio c,adm_entidad e,adm_municipio m,adm_departamento d,seg_usuario u,seg_datos_personales dp,adm_tipodocumento td " +
    			"WHERE a.adm_numero_ingreso='"+cod+"'  AND a.pac_codigo_paciente_fk=p.pac_codigo_paciente AND p.conv_numero_contrato_fk=c.conv_numero_contrato AND c.ent_nit_contratante_fk=e.ent_nit " +
    			"AND p.mun_codigo_fk=m.muni_cod AND m.dept_codigo_fk=d.dept_codigo  AND a.registrado_por=u.usuario AND u.dat_codigo_fk=dp.dat_codigo  AND p.tipo_documento=td.sigla ");
    	
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarDatosAnexo2 "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarDatosEmpresa(){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT ep.nombre,ep.nit,ep.codprestador,ep.direccion,ep.telefono,ep.departamento,d.dept_codigo,ep.municipio,m.mun_codigo,ep.fax,ep.celular " +
    			"FROM empresa ep,adm_departamento d,adm_municipio m " +
    			"WHERE ep.departamento=d.nombre AND ep.municipio=m.nombre ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarDatosEmpresa "+ex);
    }	
    return rs;
}



public java.sql.ResultSet ConsultarEnfermedadActual(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	/*rs=st.executeQuery(" SELECT GROUP_CONCAT(hres.resultados) AS enfermedad_actual " +
    			"FROM hic_adm_formatos_pac hfp,hic_resultado hres " +
    			"WHERE hfp.fecha=hres.fecha AND hfp.hora=hres.hora AND hfp.codigo_adm_fk='"+cod+"' AND hres.codPreg_fk=57 ");
    */
rs=st.executeQuery(" SELECT hres.resultados AS enfermedad_actual FROM hic_resultado hres WHERE   hres.CodFormato_fk='26' AND hres.codArea_Fk='66' AND hres.codAdm_fk='"+cod+"' AND hres.codPreg_fk=57 ");


}
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarEnfermedadActual "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarDxdeIngreso(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT ci.codigoCIE,ci.nombre FROM hic_diagnosticoingreso hde,cie10 ci " +
    			"WHERE hde.CodDiag_fk=ci.codigo AND hde.codAdm='"+cod+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarDxdeIngreso "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarOrigendeAtencion(){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT * FROM fact_anexos_origendeatencion ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarOrigendeAtencion "+ex);
    }	
    return rs;
}

public java.sql.ResultSet EnfermedadActualTriage(String NumDocu){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT CONCAT(resultados) AS Resultados FROM adm_colaresultados WHERE cedulaPac='"+NumDocu+"' AND (cod_preg_fk=28 OR cod_preg_fk=11 OR cod_preg_fk=102) ORDER BY fecha DESC LIMIT 1 ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>EnfermedadActualTriage "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarDepartamentos(){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT dept_codigo,nombre FROM adm_departamento ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarDepartamentos "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarMunicipios(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT mun_codigo,nombre FROM adm_municipio WHERE dept_codigo_fk='"+cod+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarMunicipios "+ex);
    }	
    return rs;
}



								             
                                                                                                                                                                                                               
                                                                                                                                                                                                            
public void CrearAnexoTecnico2(String a,String b,String c,String d,String e,String f,String g,String h,String i,String j,String k,String l,String m,String n,String o,String p,String q,String r,String s,String t,String u,String v,String w,String x,String y,String z,String aa,String ab,String ac,String ad,String ae,String af,String ag,String ah,String ai,String aj,String ak,String al,String am,String an,String ao,String ap,String aq,String ar,String as,String at,String au,String av,String aw,String ax,String ay,String az,String ba,String bb,String b1,String b2,String b3,String b4,String b5,String b6,String b7,String b8){
	try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO fact_anexos_tecnicos (admisionfk,tipo_anexo,fecha,hora,prestador,tipo_docp,numero_docp,codigo_prestador,direcionp,indicativop,telefonop,cod_dptop,nombre_dptop,cod_municipiop,nombre_municipiop,entidad,codigo_entidad,p_apellido,s_apellido,p_nombre,s_nombre,tipo_doc,numero_doc,fecha_nacimiento,direccion,cod_dpto,nombre_dpto,cod_municipio,nombre_municipio,celular,email,tipo_regimen,origen_atencion,ubicacion,servicio,cama,enfermedad_actual,nombre_usuario,cargo,indicativo,telefono,celularp,fecha_ingreso_urg,hora_ingreso_urg,triage,remitido,remitente,codigo_remitente,cod_dptor,nombre_dptor,cod_municipior,nombre_municipior,destino,coddx1,desdx1,coddx2,desdx2,coddx3,desdx3,coddx4,desdx4,estado) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    ps.setString(1,a);
		    ps.setString(2,b);
		    ps.setString(3,c);
		    ps.setString(4,d);
		    ps.setString(5,e);
		    ps.setString(6,f);
		    ps.setString(7,g);
		    ps.setString(8,h);
		    ps.setString(9,i);
		    ps.setString(10,j);
		    ps.setString(11,k);
		    ps.setString(12,l);
		    ps.setString(13,m);
		    ps.setString(14,n);
		    ps.setString(15,o);
		    ps.setString(16,p);
		    ps.setString(17,q);
		    ps.setString(18,r);
		    ps.setString(19,s);
		    ps.setString(20,t);
		    ps.setString(21,u);
		    ps.setString(22,v);
		    ps.setString(23,w);
		    ps.setString(24,x);
		    ps.setString(25,y);
		    ps.setString(26,z);
		    ps.setString(27,aa);
		    ps.setString(28,ab);
		    ps.setString(29,ac);
		    ps.setString(30,ad);
		    ps.setString(31,ae);
		    ps.setString(32,af);
		    ps.setString(33,ag);
		    ps.setString(34,ah);
		    ps.setString(35,ai);
		    ps.setString(36,aj);
		    ps.setString(37,ak);
		    ps.setString(38,al);
		    ps.setString(39,am);
		    ps.setString(40,an);
		    ps.setString(41,ao);
		    ps.setString(42,ap);
		    ps.setString(43,aq);
		    ps.setString(44,ar);
		    ps.setString(45,as);
		    ps.setString(46,at);
		    ps.setString(47,au);
		    ps.setString(48,av);
		    ps.setString(49,aw);
		    ps.setString(50,ax);
		    ps.setString(51,ay);
		    ps.setString(52,az);
		    ps.setString(53,ba);
		    ps.setString(54,b1);
		    ps.setString(55,b2);
		    ps.setString(56,b3);
		    ps.setString(57,b4);
		    ps.setString(58,b5);
		    ps.setString(59,b6);
		    ps.setString(60,b7);
		    ps.setString(61,b8);
		    ps.setString(62,bb);
		    ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
        	System.out.println("ERROR EN MetodoDocumentos>>CrearAnexoTecnico2 "+ex);
		}
	}




/*DIAGNOSTICOS DE INGRESO Y DE EGRESO
 SELECT ci.codigoCIE,ci.nombre
FROM hic_diagnosticoingreso hde,cie10 ci
WHERE hde.CodDiag_fk=ci.codigo AND hde.codAdm='36'
UNION
SELECT ci.codigoCIE,ci.nombre
FROM hic_diagnosticoegreso hde,cie10 ci
WHERE hde.CodDiag_fk=ci.codigo AND hde.codAdm='36'
 */

////////////////////////////////////////////////////////////////////////////



public java.sql.ResultSet ConsultarCodAnexo3(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT codigo FROM fact_anexos_tecnicos WHERE admisionfk='"+cod+"' AND tipo_anexo='3' AND estado='0'  ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarCodAnexo3 "+ex);
    }	
    return rs;
}



public java.sql.ResultSet ConsultarAnexo3(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT * FROM fact_anexos_tecnicos WHERE tipo_anexo='3' AND estado='0' AND admisionfk='"+cod+"'  ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarAnexo3 "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarCodigosAnexo3(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT codigo FROM fact_anexos_tecnicos WHERE admisionfk='"+cod+"' AND tipo_anexo='3' AND estado='1'  ");
    	System.out.println(" SELECT codigo FROM fact_anexos_tecnicos WHERE admisionfk='"+cod+"' AND tipo_anexo='3' AND estado='1'  ");
    	  }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarCodAnexo3 "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarEnfermedadActualA3(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    /*	rs=st.executeQuery(" SELECT GROUP_CONCAT(hres.resultados) AS enfermedad_actual " +
    			"FROM hic_adm_formatos_pac hfp,hic_resultado hres " +
    			"WHERE hfp.fecha=hres.fecha AND hfp.hora=hres.hora AND hfp.codigo_adm_fk='"+cod+"' AND hres.codPreg_fk=57 ");
    */
    	
    	rs=st.executeQuery("SELECT hres.resultados AS enfermedad_actual " +
    			"FROM hic_resultado hres  " +
    			"WHERE   hres.CodFormato_fk='26' AND hres.codArea_Fk='66' AND hres.codAdm_fk='"+cod+"' AND hres.codPreg_fk=57 " +
    			"UNION		" +
    			"(SELECT hres.resultados " +
    			"FROM hic_resultado hres " +
    			"WHERE   hres.CodFormato_fk='31' AND hres.codArea_Fk='72' AND hres.codAdm_fk='"+cod+"' AND hres.codPreg_fk=72  ORDER BY hres.codigo DESC LIMIT 1) ");

    
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarEnfermedadActualA3 "+ex);
    }	
    return rs;
}



public java.sql.ResultSet ConsultarDxdeEgreso(String cod){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT ci.codigoCIE,ci.nombre FROM hic_diagnosticoegreso hde,cie10 ci " +
    			"WHERE hde.CodDiag_fk=ci.codigo AND hde.codAdm='"+cod+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarDxdeEgreso "+ex);
    }	
    return rs;
}


public java.sql.ResultSet ConsultarUbicacion(String cama){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT pabellon,ubicacion_cama,codigocama FROM adm_censo_cama WHERE cen_numero_cama='"+cama+"' ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarUbicacion "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarEspecialidad(){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery(" SELECT * FROM agm_especialidad  ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarEspecialidad "+ex);
    }	
    return rs;
}

public java.sql.ResultSet ConsultarCUPS(String texto,String t){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	if(t.equals("0")){
    	rs=st.executeQuery(" SELECT cod_programa,cod_cups,descripcion FROM fact_programas WHERE cod_cups LIKE '"+texto+"%' LIMIT 100 ");
    	System.out.println("2222 SELECT cod_programa,cod_cups,descripcion FROM fact_programas WHERE cod_cups LIKE '"+texto+"%' LIMIT 100 ");
    	}
    	if(t.equals("1")){
        rs=st.executeQuery(" SELECT cod_programa,cod_cups,descripcion FROM fact_programas WHERE descripcion LIKE '"+texto+"%' LIMIT 100 ");
        System.out.println("3333 SELECT cod_programa,cod_cups,descripcion FROM fact_programas WHERE descripcion LIKE '"+texto+"%' LIMIT 100 ");
        
    	}
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarCUPS "+ex);
    }	
    return rs;
}


	public java.sql.ResultSet ConsultarCUPSAnexos(String texto){	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
       	rs=st.executeQuery(" SELECT * FROM fact_anexos_procedimientos WHERE cod_anexofk='"+texto+"'  ");
    }
    catch(Exception ex){
    	System.out.println("Error en MetodoMovimientos>>ConsultarCUPSAnexos "+ex);
    }	
    return rs;
}


	
public void InsertarCUPS(String n,String e,String f,String h,String t){
		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    															
		    ps=con.conn.prepareStatement("INSERT INTO fact_anexos_procedimientos(cod_programafk,cod_cups,descripcion,cantidad,cod_anexofk) VALUES (?,?,?,?,?)");
		    ps.setString(1,n);
		    ps.setString(2,e);		
		    ps.setString(3,f);	
		    ps.setString(4,h);
		    ps.setString(5,t);
		 	ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
	    	System.out.println("ERROR EN. MetodoMovimientos>>InsertarCUPS "+ex);
		}
	}
	
	public void EliminarCUPS(String enca){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement(" DELETE FROM fact_anexos_procedimientos WHERE codigo='"+enca+"' ");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoMovimientos>>EliminarCUPS "+ex);
	    
	    }	
	}

public void ActualizarAnexo3(String f,String h,String oa,String ts,String pa,String es,String ea,String cod){	
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("  UPDATE fact_anexos_tecnicos SET fecha='"+f+"',hora='"+h+"',origen_atencion='"+oa+"',triage='"+ts+"',remitido='"+pa+"',destino='"+es+"',enfermedad_actual='"+ea+"',estado='1' WHERE codigo='"+cod+"'  ");
	     	//System.out.println(" UPDATE fact_enc_factura SET fecha='"+f+"' , hora='"+h+"', cod_usuario_fk='"+u+"' where cod_enc_factura='"+e+"'  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>ActualizarAnexo3 "+ex);
	     	ex.getMessage();	     
	     }	
	 }	
	
public java.sql.ResultSet BuscarPacientesACE( String sql){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("  SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, " +
	    			"a.fecha_registro,df.nombre_programa, SUM(df.cantidad*df.valor),fe.copago " +
	    			"FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df , fact_convenios c, cont_movimientos_agrupados m " +
	    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3') AND fe.cod_enc_factura=df.cod_enc_factura_fk " +
	    			"AND fe.cod_eps=c.cod_entidad  AND a.adm_numero_ingreso=m.CodAdm_fk AND fe.cod_enc_factura=m.CodEnc_fk  "+sql+"  ");
	    	
	    	System.out.println("  SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, " +
	    	    			"a.fecha_registro,df.nombre_programa, SUM(df.cantidad*df.valor),fe.copago " +
	    	    			"FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df , fact_convenios c, cont_movimientos_agrupados m " +
	    	    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3') AND fe.cod_enc_factura=df.cod_enc_factura_fk " +
	    	    			"AND fe.cod_eps=c.cod_entidad   AND a.adm_numero_ingreso=m.CodAdm_fk AND fe.cod_enc_factura=m.CodEnc_fk "+sql+"  ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>BuscarPacientesACE "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet BuscarPacientesAOT( String sql){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	        	
	    	rs=st.executeQuery("  SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, " +
	    			"a.fecha_registro,df.nombre_programa, SUM(df.cantidad*df.valor),fe.copago " +
	    			"FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df , fact_convenios c " +
	    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3') AND fe.cod_enc_factura=df.cod_enc_factura_fk " +
	    			"AND fe.cod_eps=c.cod_entidad  "+sql+"  ");
	        	
	    	System.out.println("  SELECT a.adm_numero_ingreso,fe.nombre_paciente,a.pac_codigo_paciente_fk,fe.cod_eps,fe.razon_social,fe.cod_enc_factura, " +
	    			"a.fecha_registro,df.nombre_programa, SUM(df.cantidad*df.valor),fe.copago " +
	    			"FROM adm_admisiones a, fact_enc_factura fe, fact_det_factura df , fact_convenios c " +
	    			"WHERE a.estado!='2' AND a.atendido='0' AND a.adm_numero_ingreso=fe.cod_admision AND (fe.estado='0' OR fe.estado='3') AND fe.cod_enc_factura=df.cod_enc_factura_fk " +
					"AND fe.cod_eps=c.cod_entidad  "+sql+"  ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>BuscarPacientesAOT "+ex);
	    }	
	    return rs;
	}

	public java.sql.ResultSet GeneraSQLAgrupado(String sql){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("   SELECT n.consecutivo,ef.razon_social,ef.fecha,ef.cod_enc_factura " +
	    			" FROM fact_enc_factura ef, fact_numeradas n " +
	    			" WHERE (ef.tipo=6 or ef.tipo=5) AND ef.cod_enc_factura=n.cod_enc_fact_fk  AND n.estadoFact!=5  "+sql+ " " +
	    			" ORDER BY n.consecutivo");
	    	System.out.println("   SELECT n.consecutivo,ef.razon_social,ef.fecha,ef.cod_enc_factura " +
	    			" FROM fact_enc_factura ef, fact_numeradas n " +
	    			" WHERE (ef.tipo=6 or ef.tipo=5) AND ef.cod_enc_factura=n.cod_enc_fact_fk  AND n.estadoFact!=5  "+sql+ " " +
	    			" ORDER BY n.consecutivo");
	      }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>GeneraSQLAgrupado "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet ConsultarEncabezado(String CodEnc ){	
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("select * from fact_enc_factura where cod_enc_factura='"+CodEnc+"' ");
	    	System.out.println("select * from fact_enc_factura where cod_enc_factura='"+CodEnc+"' ");
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>ConsultarEncabezado "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet ConsultarProgramasValoresd(String enca){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	//rs=st.executeQuery(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura, fact_prog_med WHERE cod_programafk=cod_prog AND cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk   ");
	    	rs=st.executeQuery(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura WHERE cod_det_factura='"+enca+"' GROUP BY cod_programafk   ");
	    	System.out.println("yyyyyyyyyyyyyyyyyyyy SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura WHEREcod_det_factura='"+enca+"' GROUP BY cod_programafk   ");

	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>ConsultarProgramasValores "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet ConsultarValoresg(String enca){	
		/**
		 */
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	//rs=st.executeQuery(" SELECT cod_programafk,SUM(cantidad),SUM(cantidad*valor),nombre_programa,clase_servicio  FROM fact_det_factura, fact_prog_med WHERE cod_programafk=cod_prog AND cod_enc_factura_fk='"+enca+"' GROUP BY cod_programafk   ");
	    	rs=st.executeQuery(" SELECT valorg FROM fact_glosasdetalle WHERE cod_det_facturafk="+enca);
	    	System.out.println("SELECT valorg FROM fact_glosasdetalle WHERE cod_det_facturafk="+enca);

	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoMovimientos>>ConsultarProgramasValores "+ex);
	    }	
	    return rs;
	}
}//fin metodo movimientos
