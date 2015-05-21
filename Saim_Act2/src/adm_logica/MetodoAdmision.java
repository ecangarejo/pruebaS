/**
 * logica:MetodoAdmision
 * Desarrollado:yosimar valega
 */

package adm_logica;
import java.sql.*;

import adm_bean.Admisiones;
public class MetodoAdmision {
	
	
	/*************************************************NUEVA ADMISION*********************************/
	public ResultSet ConsultarPacienteAdmision(String TipoDocumento,String NumeroDocumento){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("SELECT ap.pac_codigo_paciente,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombrePaciente,ap.direccion,ap.telefono_celular,ap.telefono_fijo,ap.telefono_oficina,ae.nombre_entidad FROM adm_paciente ap,adm_entidad ae,adm_convenio ac WHERE  ae.ent_nit=ac.ent_nit_contratante_fk AND ac.conv_numero_contrato=ap.conv_numero_contrato_fk AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento='"+NumeroDocumento+"' LIMIT 1 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>ConsultarPacienteAdmision "+ex);
	        }
			return rsc;	
	 }
	
	public ResultSet ConsultarPacienteAdmisionActiva(String CodPac){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("SELECT * FROM adm_admisiones ad WHERE ad.pac_codigo_paciente_fk='"+CodPac+"' AND ad.estado=0");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>ConsultarPacienteAdmisionActiva "+ex);
	        }
			return rsc;	
	 }
	
	
	/************************************************************************************************/
	public void ActualizarAdmisionHosp(String ahosp,String CodAdmision){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_admisiones set ahosp="+ahosp+" where adm_numero_ingreso="+CodAdmision+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoAdmision>>ActualizarFinCola "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarColaAdmision(String CodFinCola){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE adm_finalcola SET estado=1 WHERE codigo="+CodFinCola+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoAdmision>>ActualizarColaAdmision "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void ActualizarAdmisionUrg(String aurg,String CodAdmision){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_admisiones set aurg="+aurg+" where adm_numero_ingreso="+CodAdmision+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoAdmision>>ActualizarFinCola "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public ResultSet ConsultarViaIngreso(String CodAdm){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("select acc.servicio,acc.ubicacion_cama from adm_admisiones aad,adm_censo_cama acc where aad.cen_numero_cama_fk=acc.cen_numero_cama and aad.adm_numero_ingreso="+CodAdm+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>ConsultarViaIngreso "+ex);
	        }
			return rsc;	
	 }
	
	public ResultSet ObtenerCodigoEncabezado(String CodAdmision){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("SELECT * FROM fact_enc_factura WHERE cod_admision="+CodAdmision+"");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>ObtenerCodigoEncabezado "+ex);
	        }
			return rsc;	
	}
	
	
	public ResultSet Empresa(){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("SELECT * FROM empresa");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>Empresa "+ex);
	        }
			return rsc;	
	}
	 
	
	public ResultSet ConsultarAutorizacion(String CodHorMed){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("SELECT cma.*,fp.cod_referencia,fp.descripcion,fcs.descripcion AS claseservicio,fsc.descripcion AS subcentrocosto,su.usu_codigo,ce.codigoCIE FROM cont_movimientos_agrupados cma,fact_programas fp,fact_clases_servicio fcs,fact_subcentros_costo fsc,agm_horariomedico ahm,agm_medico ame,seg_datos_personales sdp,seg_usuario su,cie10 ce WHERE cma.codCita_fk = "+CodHorMed+"   AND fp.cod_programa = cma.CodPro_fk   AND fcs.cod_clase_servicio = fp.clase_servicio AND fsc.cod_subcentro_costo = fp.subcentro_costo  AND ahm.codigo = cma.codCita_fk AND ame.codigo = ahm.codMedico_fk AND sdp.numeroDocumento = ame.numeroDocumento AND su.dat_codigo_fk = sdp.dat_codigo AND ce.codigo=cma.CodDx");
	        	//rsc=stc.executeQuery("SELECT cma.*,fp.cod_referencia,fp.descripcion,fcs.descripcion AS claseservicio,fsc.descripcion AS subcentrocosto,su.usu_codigo FROM cont_movimientos_agrupados cma,fact_programas fp,fact_clases_servicio fcs,fact_subcentros_costo fsc,agm_horariomedico ahm,agm_medico ame,seg_datos_personales sdp,seg_usuario su WHERE cma.codCita_fk="+CodHorMed+" AND fp.cod_programa=cma.CodPro_fk AND fcs.cod_clase_servicio=fp.clase_servicio AND fsc.cod_subcentro_costo=fp.subcentro_costo AND ahm.codigo=cma.codCita_fk AND ame.codigo=ahm.codMedico_fk AND sdp.numeroDocumento=ame.numeroDocumento AND su.dat_codigo_fk=sdp.dat_codigo ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>ConsultarAutorizacion "+ex);
	        }
			return rsc;	
	 }
	
	
	public ResultSet InsertarEncabezadoCex(String CodAdm){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	      //  PreparedStatement psc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("select aent.ent_nit,aent.nombre_entidad,aent.ent_nit_contratante," +
	        			"aent.direccion,aent.telefono,aent.ciudad," +
	        			"CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as NombrePAciente," +
	        			"CONCAT(apac.tipo_documento,'-',apac.numero_documento) as Identificacion,apac.direccion as DirPac," +
	        			"CONCAT(apac.telefono_celular,' ',apac.telefono_fijo,' ',apac.telefono_oficina ) as TelPac," +
	        			"apac.tipo_afiliacion, apac.estrato,aad.fecha_registro,aad.numero_autorizacion,fcv.poliza  " +
	        			"from adm_paciente apac,adm_admisiones aad,adm_entidad aent,adm_convenio acv,fact_convenios fcv  " +
	        			"where apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and fcv.cod_entidad=aent.ent_nit " +
	        			"and apac.conv_numero_contrato_fk=acv.conv_numero_contrato " +
	        			"and acv.ent_nit_contratante_fk=aent.ent_nit and aad.adm_numero_ingreso="+CodAdm+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>InsertarEncabezado "+ex);
	        }
			return rsc;	
	 }
	
	
	public ResultSet InsertarEncabezado(String CodAdm){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	      //  PreparedStatement psc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("select aent.ent_nit,aent.nombre_entidad,aent.ent_nit_contratante," +
	        			"aent.direccion,aent.telefono,aent.ciudad," +
	        			"CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as NombrePAciente," +
	        			"CONCAT(apac.tipo_documento,'-',apac.numero_documento) as Identificacion,apac.direccion as DirPac," +
	        			"CONCAT(apac.telefono_celular,' ',apac.telefono_fijo,' ',apac.telefono_oficina ) as TelPac," +
	        			"apac.tipo_afiliacion, apac.estrato,aad.fecha_registro,aad.numero_autorizacion,fcv.poliza  " +
	        			"from adm_paciente apac,adm_admisiones aad,adm_entidad aent,adm_convenio acv,fact_convenios fcv  " +
	        			"where apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and fcv.cod_entidad=aent.ent_nit " +
	        			"and apac.conv_numero_contrato_fk=acv.conv_numero_contrato " +
	        			"and acv.ent_nit_contratante_fk=aent.ent_nit and aad.adm_numero_ingreso="+CodAdm+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>InsertarEncabezado "+ex);
	        }
			return rsc;	
	 }
	
	public void CrearDetalleFactura(String fecha,String hora,String cod_programafk,String cod_programa_num,String nombre_programa,
			String clase_servicio,String fecha_realizacion,String cantidad,String valor,String cod_usuario,String cod_enc_factura_fk,
			String cod_medico,String subcentrodecosto){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("INSERT INTO fact_det_factura(fecha,hora,tipopop,cod_programafk,cod_programa,nombre_programa,clase_servicio,fecha_realizacion,cantidad,valor,cod_usuario,cod_enc_factura_fk,cod_medico,subcentrodecosto) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
  			psc.setString(1,fecha);
  			psc.setString(2,hora);
  			psc.setString(3,"1");
  			psc.setString(4,cod_programafk);
  			psc.setString(5,cod_programa_num);
  			psc.setString(6,nombre_programa);
  			psc.setString(7,clase_servicio);
  			psc.setString(8,fecha_realizacion);
  			psc.setString(9,cantidad);
  			psc.setString(10,valor);
  			psc.setString(11,cod_usuario);
  			psc.setString(12,cod_enc_factura_fk);
  			psc.setString(13,cod_medico);
  			psc.setString(14,subcentrodecosto);
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>CrearDetalleFactura "+ex);
	        }	
	 }
	
	
	public void InserEmcabeCXE(String cod_eps,String razon_social,String nit,String direccion,String telefono,
			String ciudad,String nombre_paciente,String documento,String direccion_p,String telefono_p,String tipo_afiliacion,
			String estrato,String fecha_ingreso,String CodAdm,String num_autorizacion,String poliza,String ViaIng,String copago,String moderacion){
	    PreparedStatement psc = null;
	    int efectivo=Integer.parseInt(moderacion)+Integer.parseInt(copago);
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("insert into fact_enc_factura(cod_eps, razon_social," +
     				" nit, direccion," +
     				" telefono,ciudad,condicion_pago,nombre_paciente,documento," +
     				"direccion_p,telefono_p," +
     				"tipo_afiliacion,estrato,fecha_ingreso,fecha_egreso," +
     				"cod_admision,num_autorizacion," +
     				"cod_usuario_fk,poliza,tipo,copago,moderacion,efectivo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
  			psc.setString(1,cod_eps);
  			psc.setString(2,razon_social);
  			psc.setString(3,nit);
  			psc.setString(4,direccion);
  			psc.setString(5,telefono);
  			psc.setString(6,ciudad);
  			psc.setString(7,"30 Dias");
  			psc.setString(8,nombre_paciente);
  			psc.setString(9,documento);
  			psc.setString(10,direccion_p);
  			psc.setString(11,telefono_p);
  			psc.setString(12,tipo_afiliacion);
  			psc.setString(13,estrato);
  			psc.setString(14,fecha_ingreso);
  			psc.setString(15,fecha_ingreso);
  			psc.setString(16,CodAdm);
  			psc.setString(17,num_autorizacion);
  			psc.setString(18,null);
  			psc.setString(19,poliza);
  			psc.setString(20, ViaIng);
  			psc.setString(21, copago);
  			psc.setString(22, moderacion);
  			psc.setString(23, efectivo+"");
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>InserEmcabe "+ex);
	        }	
	 }
	
	
	public void InserEmcabe(String cod_eps,String razon_social,String nit,String direccion,String telefono,
			String ciudad,String nombre_paciente,String documento,String direccion_p,String telefono_p,String tipo_afiliacion,
			String estrato,String fecha_ingreso,String CodAdm,String num_autorizacion,String poliza,String ViaIng){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("insert into fact_enc_factura(cod_eps, razon_social," +
     				" nit, direccion," +
     				" telefono,ciudad,condicion_pago,nombre_paciente,documento," +
     				"direccion_p,telefono_p," +
     				"tipo_afiliacion,estrato,fecha_ingreso,fecha_egreso," +
     				"cod_admision,num_autorizacion," +
     				"cod_usuario_fk,poliza,tipo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
  			psc.setString(1,cod_eps);
  			psc.setString(2,razon_social);
  			psc.setString(3,nit);
  			psc.setString(4,direccion);
  			psc.setString(5,telefono);
  			psc.setString(6,ciudad);
  			psc.setString(7,"30 Dias");
  			psc.setString(8,nombre_paciente);
  			psc.setString(9,documento);
  			psc.setString(10,direccion_p);
  			psc.setString(11,telefono_p);
  			psc.setString(12,tipo_afiliacion);
  			psc.setString(13,estrato);
  			psc.setString(14,fecha_ingreso);
  			psc.setString(15,null);
  			psc.setString(16,CodAdm);
  			psc.setString(17,num_autorizacion);
  			psc.setString(18,null);
  			psc.setString(19,poliza);
  			psc.setString(20, ViaIng);
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>InserEmcabe "+ex);
	        }	
	 }
	
	public void ActualizarMovimientosAgrupados(String CodEncFactura,String CodAdmision,String CodHorMed){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE cont_movimientos_agrupados SET CodAdm_fk="+CodAdmision+",CodEnc_fk="+CodEncFactura+" WHERE codCita_fk="+CodHorMed+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoAdmision>>ActualizarMovimientosAgrupados "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarFinCola(String CodColaFin){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE adm_finalcola SET estado=1 WHERE codigo="+CodColaFin+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoAdmision>>ActualizarFinCola "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet ComprobarConvenioEntidadPaciente(String CodPac){
    	//System.out.print("SELECT aent.ent_nit,aent.nombre_entidad FROM adm_paciente apac,adm_entidad aent,adm_convenio acv,fact_convenios fcv WHERE fcv.cod_entidad=aent.ent_nit AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato AND acv.ent_nit_contratante_fk=aent.ent_nit AND apac.pac_codigo_paciente="+CodPac+" ");

		java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aent.ent_nit,aent.nombre_entidad FROM adm_paciente apac,adm_entidad aent,adm_convenio acv,fact_convenios fcv WHERE fcv.cod_entidad=aent.ent_nit AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato AND acv.ent_nit_contratante_fk=aent.ent_nit AND apac.pac_codigo_paciente="+CodPac+" AND CURDATE() <= fcv.fecha_final AND aent.estado='Activo' ");
                	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>ComprobarConvenioEntidadPaciente "+ex);
        }
        return rs;
    }
	
	/**comprobar si hay una admision segun el codigo del paciente
	**/
	public java.sql.ResultSet comprobaradmision(String pac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select estado from adm_admisiones where pac_codigo_paciente_fk="+pac+" and estado=0");
                	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>comprobaradmision "+ex);
        }
        return rs;
    }
	
	/**
	 * Actualiza la admision segun el numero de ingreso
	 */
	
	public void ActualizarAd(String ing, String numauto, String medio, String autorizado, String registrado, String fecha,String hora, String estado_afiliacion, String remision,String Semana){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_admisiones set numero_autorizacion='"+numauto+"',medio_autorizacion='"+medio+"',autorizado_por='"+autorizado+"',registrado_por='"+registrado+"',fecha_registro='"+fecha+"',hora_registro='"+hora+"',estado_afiliacion='"+estado_afiliacion+"',remision='"+remision+"',semana_cotizadas='"+Semana+"' where adm_numero_ingreso="+ing+"");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoAdmision>>ActualizarAd "+ex);
	        	ex.getMessage();
	        }	
	       
	    }

	/**
	 * Obtiene los datos de la admision segun el codigo del paciente
	 */
	
	public java.sql.ResultSet obtenerDatosAd(String pac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select adm_numero_ingreso,numero_autorizacion,medio_autorizacion,autorizado_por,registrado_por,fecha_registro,hora_registro,estado_afiliacion,dir_codigo_fk,semana_cotizadas,documentos,observaciones,remision,cen_numero_cama_fk,pac_codigo_paciente_fk,codigo_contacto_fk,estado,codigoUrgVit from adm_admisiones where adm_admisiones.pac_codigo_paciente_fk="+pac+" and adm_admisiones.estado=0");
                	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>obtenerDatosAd "+ex);
        }
        return rs;
    }
	
	/**
	 * obtiene cen_numero_cama de la cama segun el codigo
	 */
	
	public java.sql.ResultSet obtenerCodigo(String cod){
        java.sql.ResultSet rs=null;
        PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st= con.conn.prepareStatement("select cen_numero_cama,servicio,ubicacion_cama  from adm_censo_cama where codigocama ='"+cod+"'");
        	System.out.println("select cen_numero_cama,servicio  from adm_censo_cama where codigocama ='"+cod+"'");
        	rs=st.executeQuery();
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error en MetodoAdmision>>obtenerCodigo "+ex);
        }	
        return rs;
    }
	
	/**
	 * Actualiza la cama la pone en estado tres segun el codigocama
	 */
	
	public void ActualizarEncabezado(String CodAdm){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET tipo='2' WHERE cod_admision ='"+CodAdm+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoAdmision>>ActualizarEncabezado "+ex);
	        }	
	       
	    }
	public void AsignarCamaAdmision(String CodAdm, String NuevaCama){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("UPDATE adm_admisiones SET cen_numero_cama_fk='"+NuevaCama+"' WHERE adm_numero_ingreso ='"+CodAdm+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoAdmision>>ActualizarCama "+ex);
	        }	
	       
	    }
	
	public void ActualizarCama(String cod){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=3 where cen_numero_cama ="+cod+" ");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoAdmision>>ActualizarCama "+ex);
	        }	
	       
	    }
	
	
	
	public void Actualizar(String cod){
       PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=3 where codigocama ='"+cod+"'");
        	st.executeUpdate();
        	st.close();
        	con.cerrar();
        	
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error en MetodoAdmision>>Actualizar "+ex);
        }	
       
    }
	
	/**
	 * liberar cama en reserva
	 */
	
	public void liberar(String cod){
	       PreparedStatement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st= con.conn.prepareStatement("Update adm_censo_cama set est_codigo_estado_fk=1 where codigocama ='"+cod+"'");
	        	st.executeUpdate();
	        	st.close();
	        	con.cerrar();
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoAdmision>>liberar "+ex);
	        }	
	       
	    }
	
	public java.sql.ResultSet BuscarCodigoCola(String ced){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT afc.codigo FROM adm_finalcola afc,adm_cola ac WHERE ac.col_codigo = afc.CodCola_fk AND afc.estado=0 AND ac.numero_documento='"+ced+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>BuscarCodigoCola "+ex);
		}
		return rs;
	}
	
	
	
	public java.sql.ResultSet obtenerCodigoAdmisionActiva(String CodPac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_admisiones WHERE pac_codigo_paciente_fk='"+CodPac+"' AND estado='0' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>obtenerCodigoAdmisionActiva "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet obtenerCodigoAdmision(String FechaRegistro,String hora,String codpac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT adm_numero_ingreso FROM adm_admisiones WHERE fecha_registro='"+FechaRegistro+"' AND hora_registro='"+hora+"' AND pac_codigo_paciente_fk='"+codpac+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>obtenerCodigoAdmision "+ex);
        }
        return rs;
    }
	
	/**
	 * Se obtiene codigo del paciente segun el numero de documento
	 */
	
	
	public java.sql.ResultSet Datos_Admision(String numingreso){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_admisiones WHERE adm_numero_ingreso="+numingreso+"");
        	//System.out.println("select pac_codigo_paciente  from adm_paciente where numero_documento ='"+cod+"' and tipo_documento='"+tipo+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>Datos_Admision "+ex);
        }
        return rs;
    }
	
	
	
	
	
	public java.sql.ResultSet obtenerCodigoPaciente(String cod,String tipo){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select pac_codigo_paciente  from adm_paciente where numero_documento ='"+cod+"' and tipo_documento='"+tipo+"' ");
        	//System.out.println("select pac_codigo_paciente  from adm_paciente where numero_documento ='"+cod+"' and tipo_documento='"+tipo+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>obtenerCodigoPaciente "+ex);
        }
        return rs;
    }
	
	/**
	 * Se obtiene codigo del contacto segun el numero de documento
	 */
   
	public java.sql.ResultSet obtenerCodigoContacto(String cod){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select con_contactos_codigo  from adm_contacto where cedula="+cod);
        
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAdmision>>obtenerCodigoContacto "+ex);
        }
        return rs;
    }
	
	
	public void InsertarOrdenAdmision(String CodOrden,String CodAdmision,String CodEncFactura){
		try{
			Conexion con=new Conexion();
			PreparedStatement ps = null;
			
			ps=con.conn.prepareStatement("insert into fact_orden_admision(CodOrden,CodAdmision,CodEncFactura) values(?,?,?)");
			ps.setString(1, CodOrden);
			ps.setString(2, CodAdmision);
			ps.setString(3, CodEncFactura);
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception ex){
			ex.getMessage();
			System.out.println("Error en MetodoAdmision>>InsertarContactoPaciente "+ex);
		}
		
	}
	
	
	public void InsertarContactoPaciente(String CodContacto,String Codpac){
		try{
			Conexion con=new Conexion();
			PreparedStatement ps = null;
			
			ps=con.conn.prepareStatement("insert into adm_paciente_contacto(con_contactos_codigo_fk,pac_codigo_paciente_fk) values(?,?)");
			ps.setString(1, CodContacto);
			ps.setString(2, Codpac);
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception ex){
			ex.getMessage();
			System.out.println("Error en MetodoAdmision>>InsertarContactoPaciente "+ex);
		}
		
	}
	
	
	 public void insertarAdmision(
			String registradopor,String fecha, 
			Time hora,String codpac,String NumAutorizacion){
		
		try{
			Conexion con=new Conexion();
			PreparedStatement ps = null;
	    ps=con.conn.prepareStatement("insert into adm_admisiones(numero_autorizacion, medio_autorizacion, autorizado_por, registrado_por, fecha_registro,hora_registro,estado_afiliacion,dir_codigo_fk,semana_cotizadas,documentos,observaciones,remision,cen_numero_cama_fk,pac_codigo_paciente_fk,codigo_contacto_fk,codigoUrgVit,ahosp,estado) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
		ps.setString(1, NumAutorizacion);
		ps.setString(2, "ORDEN");
		ps.setString(3, "ORDEN");
		ps.setString(4, registradopor);
		ps.setString(5, fecha);
		ps.setTime(6, hora);
		ps.setString(7, "ACTIVO");
		ps.setString(8, "3");//dir_codigo_fk direccionamiento
		ps.setString(9, "0");
		ps.setString(10, "si");//documentos
		ps.setString(11, "-");
		ps.setString(12, "-");
		ps.setString(13, "1099");//numero de la cama
		ps.setString(14, codpac);//codigo paciente
		ps.setString(15, "1");//codigo del contacto
		ps.setString(16, "0");
		ps.setString(17, "1");
		ps.setString(18, "1");
		ps.executeUpdate();
		ps.close();

		}catch(Exception ex){
			ex.getMessage();
			System.out.println("Error en MetodoAdmision>>insertar "+ex);
		}
		
	}
	/**
	 * Se ingresa una admision
	 */
	
	public void insertar(String contacto,String codcama,String cedula,
			String numeroautorizacion,String medioauto,String autopor,
			String registradopor,String fecha, 
			String hora,String estadoa,String semanac,String documentos,
			String obs,String remitidode, String na, String urgencia,String tipo){//,String nombrepaciente,
		
		Admisiones pre = new Admisiones();		
		pre.setNumeroAutorizacion(numeroautorizacion);
		pre.setMedioAutorizacion(medioauto);
		pre.setFechaRegistro(fecha);
		pre.setAutorizadoPor(autopor);
		pre.setHoraRegistro(hora);
		pre.setRegistradodoPor(registradopor);
		pre.setRemitidoDe(remitidode);
		pre.setEstadoAfilicacion(estadoa);
		pre.setSemanasCotizadas(semanac);
	    pre.setCedula(cedula);
		pre.setObservaciones(obs);
		pre.setDocumentos(documentos);
		pre.setNumeroCama(codcama);
		pre.setContacto(contacto);		
		
		try{
			/*String codigoconta =pre.getContacto();
			ResultSet rs3;
			rs3=null;
			rs3 = obtenerCodigoContacto(codigoconta);		
			String codcontacto="";
			if (rs3.next()){codcontacto = rs3.getString(1);}else{codcontacto="";}
			rs3.getStatement().getConnection().close();*/
			
			String codigocama =pre.getNumeroCama();
			ResultSet rs2 = obtenerCodigo(codigocama);		
			String codcam=null;
			String ServicioCama="";
			if (rs2.next()){codcam = rs2.getString(1);ServicioCama=rs2.getString(2);}else{codcam="";}	
			rs2.getStatement().getConnection().close();
			
			String codigo=pre.getCedula();			
			ResultSet rs1=null;
			rs1 = obtenerCodigoPaciente(cedula,tipo);			
			String codpac=null;
			if (rs1.next()){codpac = rs1.getString(1);}else{codpac="";}	
			rs1.getStatement().getConnection().close();
			
			if(na.equals("0")){}else{codpac=na;}
		 
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
		    Conexion con=new Conexion();		    
		    String fechainicio,iniin,medin,finin=null;
			iniin=pre.getFechaRegistro().substring(0,2);			
			medin=pre.getFechaRegistro().substring(3,5);
			finin=pre.getFechaRegistro().substring(6,10);
			fechainicio=finin+"/"+medin+"/"+iniin;		    
		    
		    ps=con.conn.prepareStatement("insert into adm_admisiones(numero_autorizacion, medio_autorizacion, autorizado_por, registrado_por, fecha_registro,hora_registro,estado_afiliacion,dir_codigo_fk,semana_cotizadas,documentos,observaciones,remision,cen_numero_cama_fk,pac_codigo_paciente_fk,codigo_contacto_fk,codigoUrgVit) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
			ps.setString(1, pre.getNumeroAutorizacion());
			ps.setString(2, pre.getMedioAutorizacion());
			ps.setString(3, pre.getAutorizadoPor());
			ps.setString(4, pre.getRegistradoPor());
			ps.setString(5, fechainicio);
			ps.setString(6, pre.getHoraRegistro());
			ps.setString(7, pre.getEstadoAfilicacion());
			ps.setString(8, ServicioCama);//dir_codigo_fk direccionamiento
			ps.setString(9, pre.getSemanasCotizadas());
			ps.setString(10, pre.getDocumentos());//documentos
			ps.setString(11, pre.getObservaciones());
			ps.setString(12, pre.getRemitidoDe());
			ps.setString(13, codcam);//numero de la cama
			ps.setString(14, codpac);//codigo paciente
			ps.setString(15, contacto);//codigo del contacto
			ps.setString(16, urgencia);
			ps.executeUpdate();
			ps.close();
			
			ps1=con.conn.prepareStatement("insert into adm_paciente_contacto(con_contactos_codigo_fk,pac_codigo_paciente_fk) values(?,?)");
			ps1.setString(1,contacto);//codigo contacto
			ps1.setString(2,codpac);//codigo del paciente
			ps1.executeUpdate();
			ps1.close();
			con.cerrar();
			
		}catch(Exception ex){
			ex.getMessage();
			System.out.println("Error en MetodoAdmision>>insertar "+ex);
		}
	}
	
	
	
	
	public void CrearNuevaAdmision(String numero_autorizacion,String medio_autorizacion,
			String autorizado_por,String registrado_por,Date fecha_registro,Time hora_registro,
			String estado_afiliacion,String dir_codigo_fk,String semana_cotizadas,
			String documentos,String observaciones,String remision,String cen_numero_cama_fk,
			String pac_codigo_paciente_fk,String codigo_contacto_fk,String estado,
			String codigoUrgVit,String atendido,String aurg,String ahosp){//,String nombrepaciente,
		
		try{
		 
			PreparedStatement ps = null;
		    Conexion con=new Conexion();    
		    
		    ps=con.conn.prepareStatement("insert into adm_admisiones(numero_autorizacion,medio_autorizacion,autorizado_por,registrado_por,fecha_registro,hora_registro,estado_afiliacion,dir_codigo_fk,semana_cotizadas,documentos,observaciones,remision,cen_numero_cama_fk,pac_codigo_paciente_fk,codigo_contacto_fk,estado,codigoUrgVit,atendido,aurg,ahosp) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
			ps.setString(1, numero_autorizacion);
			ps.setString(2, medio_autorizacion);
			ps.setString(3, autorizado_por);
			ps.setString(4, registrado_por);
			ps.setDate(5, fecha_registro);
			ps.setTime(6, hora_registro);
			ps.setString(7, estado_afiliacion);
			ps.setString(8, dir_codigo_fk);//dir_codigo_fk direccionamiento
			ps.setString(9, semana_cotizadas);
			ps.setString(10, documentos);//documentos
			ps.setString(11, observaciones);
			ps.setString(12, remision);
			ps.setString(13, cen_numero_cama_fk);//numero de la cama
			ps.setString(14, pac_codigo_paciente_fk);//codigo paciente
			ps.setString(15, codigo_contacto_fk);//codigo del contacto
			ps.setString(16, estado);
			ps.setString(17, codigoUrgVit);
			ps.setString(18, atendido);
			ps.setString(19, aurg);
			ps.setString(20, ahosp);
			ps.executeUpdate();
			ps.close();
			con.cerrar();
			
		}catch(Exception ex){
			ex.getMessage();
			System.out.println("Error en MetodoAdmision>>insertar "+ex);
		}
	}

	
	
}