package hic_metodo;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JProgressBar;
import fact_bean.Tarifas;
import adm_logica.Conexion;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import com.sun.org.apache.bcel.internal.classfile.Code;

public class MetodoCargue {
	
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
        	System.out.println("Error en MetodoCargue>>listarFormasActoQx "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarActoQx(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo, descripcion FROM fact_actoqx WHERE descripcion_rips='"+texto+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarActoQx "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarTipoAnestesia(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_tipoanestesia ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarTipoAnestesia "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarTipoAnestesia(String Cod){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_tipoanestesia where codigo='"+Cod+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarTipoAnestesia "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarDx(String Cod, String tdx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_dx_descripcionqx WHERE cod_descripcionqx='"+Cod+"' AND identificador='"+tdx+"' ");
        	//System.out.println("SELECT * FROM hic_dx_descripcionqx WHERE cod_descripcionqx='"+Cod+"' AND identificador='"+tdx+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarDx "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarCxAnes(String Cod, String tdx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+Cod+"' AND identificador='"+tdx+"' AND asignado='0' ");
        	//System.out.println("SELECT * FROM hic_dx_descripcionqx WHERE cod_descripcionqx='"+Cod+"' AND identificador='"+tdx+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarDx "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarCxAnes(String Cod, String tdx, String tdx2){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+Cod+"' AND (identificador='"+tdx+"' OR identificador='"+tdx2+"' ) AND asignado='0' ");
        	//System.out.println("SELECT * FROM hic_dx_descripcionqx WHERE cod_descripcionqx='"+Cod+"' AND identificador='"+tdx+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarDx "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarDescripcionesGuardadas(String adm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT d.codigo,d.fecha,d.horai,p.apellido,p.nombre FROM hic_descripcionqx d, seg_usuario u, seg_datos_personales p WHERE d.cod_admisionfk='"+adm+"'  AND d.estado='1' AND d.cod_usuariofk=u.usu_codigo AND u.dat_codigo_fk=p.dat_codigo   ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarDescripciones "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarDescripciones(String adm, String user){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM hic_descripcionqx WHERE cod_admisionfk='"+adm+"'  AND estado='0' AND cod_usuariofk='"+user+"' ");
        	System.out.println(" 1 SELECT * FROM hic_descripcionqx WHERE cod_admisionfk='"+adm+"'  AND estado='0' AND cod_usuariofk='"+user+"' ");
            
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarDescripciones "+ex);
        }	
        return rs;
    }



	public java.sql.ResultSet listarProcedimientosconenca(String texto,String enca){	
		/**
		 */
		//System.out.println("ESTE ES EL: "+texto);
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_det_factura,tipopop,cod_programafk,IF(tipopop='1',cod_programa,cod_paquete),IF(tipopop='1',nombre_programa,nombre_paquete),fecha,hora " +
        			" FROM fact_det_factura  WHERE origen='1' AND cod_origen='"+texto+"' AND cod_enc_factura_fk='"+enca+"' GROUP BY nombre_paquete,fecha,hora ");
        	System.out.println(" SELECT cod_det_factura,tipopop,cod_programafk,IF(tipopop='1',cod_programa,cod_paquete),IF(tipopop='1',nombre_programa,nombre_paquete),fecha,hora " +
        			" FROM fact_det_factura  WHERE origen='1' AND cod_origen='"+texto+"' AND cod_enc_factura_fk='"+enca+"' GROUP BY nombre_paquete,fecha,hora ");
     
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarProcedimientosconenca "+ex);
        }	
        return rs;
    }
	



	
	public java.sql.ResultSet listarUsuario(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT nombre,apellido,usu_codigo FROM seg_usuario, seg_datos_personales WHERE usu_codigo='"+texto+"' AND dat_codigo_fk=dat_codigo ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarNoUsuario(String texto){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT nombre,apellido,usu_codigo FROM seg_usuario, seg_datos_personales WHERE usu_codigo!='"+texto+"' AND dat_codigo_fk=dat_codigo AND profesion='Especialista'  ORDER BY apellido ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarNoUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarUsuarios(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT nombre,apellido,usu_codigo FROM seg_usuario, seg_datos_personales WHERE dat_codigo_fk=dat_codigo AND profesion='Especialista'  ORDER BY apellido ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarUsuario "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet listarProcedimientos(String texto){	
		/**
		 */
		//System.out.println("ESTE ES EL: "+texto);
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" (SELECT cod_det_factura,tipopop,cod_programafk,cod_programa,nombre_programa,fecha,hora FROM fact_det_factura " +
        			"WHERE origen='1' AND tipopop='1' AND cod_origen='"+texto+"') " +
        			"UNION" +
        			" (SELECT cod_det_factura,tipopop,cod_programafk,cod_paquete,nombre_paquete,fecha,hora FROM fact_det_factura " +
        			" WHERE origen='1' AND tipopop='2' AND cod_origen='"+texto+"' GROUP BY nombre_paquete,fecha,hora) ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>listarUsuario "+ex);
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
	        	
	        	r=st.executeQuery("(SELECT DISTINCT 1,mt.manual_base,p.cod_programa,p.cod_referencia,p.descripcion,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico,p.subcentro_costo " +
	        			          "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = p.cod_programa " +
	        			          "AND p.descripcion LIKE '%"+texto+"%' " +
	        			          "AND p.clase_servicio=cs.cod_clase_servicio " +
	        			          "AND (p.clase_servicio='4' OR p.clase_servicio='7' OR p.clase_servicio='13' OR p.clase_servicio='14' OR p.clase_servicio='16' OR p.clase_servicio='17')) " +
	        			          "UNION " +
	        			          "(SELECT DISTINCT 2,mt.manual_base,pq.cod_paquete,pq.cod_referencia, pq.descripcion,'','','','','','',pq.cod_subcentro_costo_fk " +
	        			          "FROM fact_tarifas ft,fact_manuales_tarifarios mt,fact_progs_paquetes fpp,fact_paquetes pq " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = fpp.cod_programa " +
	        			          "AND fpp.cod_paquete=pq.cod_paquete " +
	        			          "AND pq.descripcion LIKE '%"+texto+"%') limit 300");
	        	
	        /*	System.out.println("(SELECT DISTINCT 1,mt.manual_base,p.cod_programa,p.cod_referencia,p.descripcion,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico " +
	        			          "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = p.cod_programa " +
	        			          "AND p.descripcion LIKE '"+texto+"%' " +
	        			          "AND p.clase_servicio=cs.cod_clase_servicio) " +
	        			          "UNION " +
	        			          "(SELECT DISTINCT 2,mt.manual_base,pq.cod_paquete,pq.cod_referencia, pq.descripcion,'','','','','','' " +
	        			          "FROM fact_tarifas ft,fact_manuales_tarifarios mt,fact_progs_paquetes fpp,fact_paquetes pq " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " + 
	        			          "AND ft.programa = fpp.cod_programa " +
	        			          "AND fpp.cod_paquete=pq.cod_paquete " +
	        			          "AND pq.descripcion LIKE '"+texto+"%')");*/
	        	     
	      return r;
	}
	
	
	public ResultSet listarRef(String texto, String xx) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	r=st.executeQuery("(SELECT DISTINCT 1,mt.manual_base,p.cod_programa,p.cod_referencia,p.descripcion,cs.descripcion,ft.cod_tarifa,ft.manual_tarifario,ft.valor,p.actoqx,p.medico,p.subcentro_costo  " +
	        			          "FROM fact_programas p,fact_tarifas ft,fact_manuales_tarifarios mt,fact_clases_servicio cs " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = p.cod_programa " +
	        			          "AND p.cod_referencia LIKE '"+texto+"%' " +
	        			          "AND p.clase_servicio=cs.cod_clase_servicio) " +
	        			          "UNION " +
	        			          "(SELECT DISTINCT 2,mt.manual_base,pq.cod_paquete,pq.cod_referencia, pq.descripcion,'','','','','','',pq.cod_subcentro_costo_fk   " +
	        			          "FROM fact_tarifas ft,fact_manuales_tarifarios mt,fact_progs_paquetes fpp,fact_paquetes pq " +
	        			          "WHERE mt.cod_manual_tarifario IN (SELECT ftc.cod_manualTarifario FROM fact_convenios fc,fact_tarifas_convenios ftc WHERE fc.cod_entidad = "+xx+" AND fc.cod_convenio = ftc.cod_convenio) " +
	        			          "AND mt.cod_manual_tarifario=ft.manual_tarifario " +
	        			          "AND ft.programa = fpp.cod_programa " +
	        			          "AND fpp.cod_paquete=pq.cod_paquete " +
	        			          "AND pq.cod_referencia LIKE '"+texto+"%') LIMIT 300 ");
	        	
	        	
	     
	      return r;
	}
	
	
	public void CargarDetalle(String fecha,String hora,String pop,String cod_programafk,String cod_programa,String nombre_programa,String cod_paquete,String nombre_paquete,String clase_servicio,String fecha_realizacion,String cantidad,String valor,String cod_usuario,String cod_enc_factura_fk,String cod_medico,String subc,String formaqx, String p, String ori, String cori){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into fact_det_factura(fecha,hora,tipopop,cod_programafk,cod_programa,nombre_programa,cod_paquete,nombre_paquete,clase_servicio,fecha_realizacion,cantidad,valor,cod_usuario,cod_enc_factura_fk,cod_medico,subcentrodecosto,formaactoqx,porcentaje,origen,cod_origen)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			    ps.setString(19,ori);
			    ps.setString(20,cori);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>CargarDetalle "+ex);
			}
		}
	
	
	public void CargarProcedimientoDQx(String pop,String codp,String des,String dqx, String hra){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into hic_procedimientosdqx(tipopop,cod_programafk,descripcion,cod_descripcionqxfk,hora_cargue)values(?,?,?,?,?)");
			    ps.setString(1,pop);
			    ps.setString(2,codp);
			    ps.setString(3,des);
			    ps.setString(4,dqx);
			    ps.setString(5,hra);
			    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoCargue>>CargarProcedimientoDQx "+ex);
			}
		}
	
	
	public java.sql.ResultSet ProcedimientoDQx(String cp, String dqx, String hra){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM hic_procedimientosdqx WHERE cod_programafk='"+cp+"' AND cod_descripcionqxfk='"+dqx+"' AND hora_cargue='"+hra+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>ProcedimientoDQx "+ex);
        }	
        return rs;
    }
	
	public void CargarHonorariosDQx(String pop,String codp,String des,String dqx){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into hic_honorariosdescripcionqx(cod_hic_procedimientoqxfk,cod_hic_cx_descripcionqx,valor,Observacion)values(?,?,?,?)");
			    ps.setString(1,pop);
			    ps.setString(2,codp);
			    ps.setString(3,des);
			    ps.setString(4,dqx);
					  						    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoCargue>>CargarHonorariosDQx "+ex);
			}
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
	
	
	public java.sql.ResultSet PorcentajeQx(String valor){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT honorarios_cirujano,horarios_anestesiologos,horarios_ayudantia,derecho_sala,materiales,otros FROM fact_actoqx WHERE codigo='"+valor+"' ");
          }
        catch(Exception ex){
        	System.out.println("Error en MetodoMovimientos>>PorcentajeQx "+ex);
        }	
        return rs;
    }
	
	

	public void CrearDescripcionQx(String instru,String anes,String hi,String hf,String fechamo,String tejido,String adm,String enca,String codp,String user,String fechacjmysql,String hra){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into hic_descripcionqx(instrumentadora,cod_tipoanestesiafk,horai,horaf,fecha,tejidoapatologia,cod_admisionfk,cod_encabezadofk,cod_pacientefk,cod_usuariofk,fecha_registro,hora_registro)values(?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,instru);
			    ps.setString(2,anes);
			    ps.setString(3,hi);
			    ps.setString(4,hf);
			    ps.setString(5,fechamo);
			    ps.setString(6,tejido);
			    ps.setString(7,adm);
			    ps.setString(8,enca);
			    ps.setString(9,codp);
			    ps.setString(10,user);
			    ps.setString(11,fechacjmysql);
			    ps.setString(12,hra);
						    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>CrearDescripcionQx "+ex);
			}
		}
	

	
	public void InsertarDx(String cod,String desc,String id,String dqx){
		//System.out.println("InsertarDXXXXXXXXX");
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into hic_dx_descripcionqx(codigo_cie10fk,descripcion,identificador,cod_descripcionqx)values(?,?,?,?)");
			    ps.setString(1,cod);
			    ps.setString(2,desc);
			    ps.setString(3,id);
			    ps.setString(4,dqx);
								    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();	
				System.out.print("insert into hic_dx_descripcionqx(codigo_cie10fk,descripcion,identificador,cod_descripcionqx)values(?,?,?,?)");
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>InsertarDx "+ex);
			}
		}
	
	
	public void InsertarCx(String usu,String nusu,String id,String dqx){
		//System.out.println("InsertarDXXXXXXXXX");
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into hic_cx_descripcionqx(cod_usuariofk,nombre_usuario,identificador,cod_descripcionqx)values(?,?,?,?)");
			    ps.setString(1,usu);
			    ps.setString(2,nusu);
			    ps.setString(3,id);
			    ps.setString(4,dqx);
								    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();	
				//System.out.print("insert into hic_dx_descripcionqx(codigo_cie10fk,descripcion,identificador,cod_descripcionqx)values(?,?,?,?)");
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoMovimientos>>InsertarCx "+ex);
			}
		}
	
	public java.sql.ResultSet CxsinAsignar(String dqx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");
        	//System.out.print(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>CxsinAsignar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Cx2sinAsignar(String dqx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='1' AND asignado='0' ");
        	//System.out.print(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>Cx2sinAsignar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet Cx3sinAsignar(String dqx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='2' AND asignado='0' ");
        	//System.out.print(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>Cx3sinAsignar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CxAnesinAsignar(String dqx, String t){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(t.equals("0")){rs=st.executeQuery(" SELECT codigo FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");}
        	if(t.equals("1")){rs=st.executeQuery(" SELECT codigo FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='1' AND asignado='0' ");}
        	if(t.equals("2")){rs=st.executeQuery(" SELECT codigo FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='2' AND asignado='0' ");}
        	
        	//System.out.print(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>CxsinAsignar "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CxAne(String dqx){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE codigo='"+dqx+"' ");
         	//System.out.print(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>CxsinAsignar "+ex);
        }	
        return rs;
    }
	
	public void AtualizarCxAne(String codMov){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_cx_descripcionqx SET asignado='1' WHERE codigo='"+codMov+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>AtualizarCxAne "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ConsultarPorcentajePro(String cp){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT porc_cx,por_anestesiologo,por_ayudantia FROM hic_porc_descripcionqx WHERE cod_programafk='"+cp+"' AND porc_cx!='(NULL)' AND por_anestesiologo!='(NULL)' AND por_ayudantia!='(NULL)' ");
         	//System.out.print(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>ConsultarPorcentajePro "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ConsultarPorcentajeProCreado(String cp){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM hic_porc_descripcionqx WHERE cod_programafk='"+cp+"'  ");
         	//System.out.print(" SELECT cod_usuariofk FROM hic_cx_descripcionqx WHERE cod_descripcionqx='"+dqx+"' AND identificador='0' AND asignado='0' ");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCargue>>ConsultarPorcentajePro "+ex);
        }	
        return rs;
    }
	
	public void InsertarPorcentajePro(String codp){
		//System.out.print(cod_medico);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    															
			    ps=con.conn.prepareStatement("insert into hic_porc_descripcionqx(cod_programafk)values(?)");
			    ps.setString(1,codp);
	  						    
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN. MetodoCargue>>InsertarPorcentajePro "+ex);
			}
		}
	   
	public void FinalizarDescripcionQx(String h, String d, String f, String hra, String dqx){	
		/**
		 */
		 PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_descripcionqx SET hallazgos='"+h+"', descripcionqx='"+d+"', fecha_finregistro='"+f+"', hora_finregistro='"+hra+"', estado='1' WHERE codigo='"+dqx+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoMovimientos>>AtualizarCxAne "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void EliminarMedico(String mov){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from hic_cx_descripcionqx where codigo='"+mov+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN EliminarMedico>>Anularmov "+ex);
	    
	    }	
	}
	
	public void EliminarDiagnostico(String mov){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from hic_dx_descripcionqx where codigo='"+mov+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN EliminarMedico>>Anularmov "+ex);
	    
	    }	
	}

	public void EliminarProcedimiento(String mov){
		PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st= con.conn.prepareStatement("delete from fact_det_factura where cod_det_factura='"+mov+"'");
	    	st.executeUpdate();
	    	st.close();
	    	con.cerrar();
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("ERROR EN MetodoCargue>>EliminarProcedimiento "+ex);
	    
	    }	
	}

}
