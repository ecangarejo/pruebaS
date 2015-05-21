package pyp_metodo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import adm_logica.Conexion;

public class PYP_MetodoConsultasAsignacion {
	
	
	public ResultSet InsertarEncabezadoCE(String CodHorarioMedico){
		 java.sql.ResultSet rsc=null;
	        Statement stc = null;
	      //  PreparedStatement psc = null;
	        try{
	        	Conexion con=new Conexion();
	        	stc = con.conn.createStatement();
	        	rsc=stc.executeQuery("SELECT aent.ent_nit,aent.nombre_entidad,aent.ent_nit_contratante," +
	        			"aent.direccion,aent.telefono,aent.ciudad," +
	        			"CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS NombrePAciente," +
	        			"CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Identificacion,apac.direccion AS DirPac," +
	        			"CONCAT(apac.telefono_celular,' ',apac.telefono_fijo,' ',apac.telefono_oficina ) AS TelPac," +
	        			"apac.tipo_afiliacion, apac.estrato,phm.fechas,'',fcv.poliza,pcp.codigo  " +
	        			"FROM adm_paciente apac,pyp_citaspacientes pcp,adm_entidad aent,adm_convenio acv," +
	        			"fact_convenios fcv,pyp_horariomedico phm " +
	        			"WHERE apac.pac_codigo_paciente=pcp.`CodPac_fk` AND fcv.cod_entidad=aent.ent_nit " + 
	        			"AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato " +
	        			"AND phm.codigo=pcp.CodHorMedico_fk " +
	        			"AND acv.ent_nit_contratante_fk=aent.ent_nit AND phm.codigo="+CodHorarioMedico+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>InsertarEncabezadoCE "+ex);
	        }
			return rsc;	
	 }
	
	public void InserEmcabeCE(String cod_eps,String razon_social,String nit,String direccion,String telefono,
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
  			psc.setString(15,fecha_ingreso);
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
	
	public void GuardarOrdenServicio(String CodPac_fk,String CodEnt_fk,String consecutivo,String orden,String observacion,String fecha_insercion,String hora_insercion,String usuario_insercion,String CodAdm_fk,String fecha_validacion,
			String NombreEntidadPre,String NitEntidadPre,String DireccionEntidadPre,String TelefonoEntidadPre,String servicio){
		
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("INSERT INTO fact_orden_servicio" +
	    	 		"(CodPac_fk,CodEnt_fk,consecutivo,orden,observacion,fecha_insercion,hora_insercion," +
	    	 		"usuario_insercion,CodAdm_fk,fecha_validacion,NombreEntidadPre,NitEntidadPre," +
	    	 		"DireccionEntidadPre,TelefonoEntidadPre,servicio)" +
	    	 		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");	
  			psc.setString(1,CodPac_fk);
  			psc.setString(2,CodEnt_fk);
  			psc.setString(3,consecutivo);
  			psc.setString(4,orden);
  			psc.setString(5,observacion);
  			psc.setString(6,fecha_insercion);
  			psc.setString(7,hora_insercion);
  			psc.setString(8,usuario_insercion);
  			psc.setString(9,CodAdm_fk);
  			psc.setString(10,fecha_validacion);
  			psc.setString(11,NombreEntidadPre);
  			psc.setString(12,NitEntidadPre);
  			psc.setString(13,DireccionEntidadPre);
  			psc.setString(14,TelefonoEntidadPre);
  			psc.setString(15,servicio);
 			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>GuardarOrdenServicio "+ex);
	        }	
	 }
	
	public void GuardarDetalleOrdenServicio(String CodOrden_fk,String Nombre_Programa,String CodCups,String CodReferencia,String CodProg_fk,String cantidad,String valor){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("INSERT INTO fact_detalle_orden_servicio(CodOrden_fk,Nombre_Programa,CodCups,CodReferencia,CodProg_fk,cantidad,valor)VALUES(?,?,?,?,?,?,?)");	
  			psc.setString(1,CodOrden_fk);
  			psc.setString(2,Nombre_Programa);
  			psc.setString(3,CodCups);
  			psc.setString(4,CodReferencia);
  			psc.setString(5,CodProg_fk);
  			psc.setString(6,cantidad);
  			psc.setString(7,valor);
 			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>GuardarDetalleOrdenServicio "+ex);
	        }	
	 }
	
	public void ActualizarCitasInasistencia(String CodCita){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_horariomedico SET estado=4 WHERE codigo='"+CodCita+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ActualizarCitasInasistencia "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void ActualizarConsecutivoOrdenServicio(String Consecutivo){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_orden_consecutivo SET consecutivo='"+Consecutivo+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ActualizarConsecutivoOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void FinalizarOrdenServicio(String Codigo,String Estado){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_orden_servicio SET estado='"+Estado+"' WHERE codigo='"+Codigo+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>FinalizarOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarDetalleEncabezadoOrden(String CodEncabezado){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_det_factura SET facturado ='5' WHERE cod_enc_factura_fk='"+CodEncabezado+"' ");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ActualizarOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarEncabezadoOrden(String CodEncabezado){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_enc_factura SET estado ='5' WHERE cod_enc_factura='"+CodEncabezado+"' ");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ActualizarOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarAdmisionOrden(String CodAdmision){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE adm_admisiones SET estado ='2' WHERE adm_numero_ingreso='"+CodAdmision+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ActualizarOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void DevolverAtencionCex(String CodHorarioMedico){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_horariomedico SET estado ='2' WHERE codigo='"+CodHorarioMedico+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ActualizarOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarOrdenServicio(String CodOrden,String AgruObserva,String Fecha_Anulacion,String Hora_Anulacion,String CodusuarioAnula){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE fact_orden_servicio SET estado ='2',observacion_anulacion='"+AgruObserva+"',fecha_anulacion='"+Fecha_Anulacion+"',hora_anulacion='"+Hora_Anulacion+"',usuario_anula='"+CodusuarioAnula+"' WHERE codigo='"+CodOrden+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ActualizarOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void OmitirDetalleOrdenServicio(String Codigo){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM fact_detalle_orden_servicio WHERE codigo='"+Codigo+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoConsultasAsignacion>>OmitirDetalleOrdenServicio "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public java.sql.ResultSet BuscarCodigoOrden(String FechaInsercion,String HoraInsercion){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_orden_servicio WHERE fecha_insercion='"+FechaInsercion+"' AND hora_insercion='"+HoraInsercion+"' ");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarCodigoOrden "+ex);
        }
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarOrdenesVencidas(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_orden_servicio WHERE (estado='0' OR estado='1') AND fecha_validacion<CURDATE()");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarOrdenesVencidas "+ex);
        }
        return rs;
    }
	
	
	
	public java.sql.ResultSet FechaVencimiento(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DATE_ADD(CURDATE(), INTERVAL 30 DAY) AS ProximaFecha");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>FechaVencimiento "+ex);
        }
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerDetalleFacturaOrdenServicio(String CodCups,String CodManual){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.cod_programa,fp.cod_cups,fp.descripcion AS Programa,fcs.descripcion AS ClaseServicio,ft.valor,fp.subcentro_costo FROM fact_programas fp,fact_manuales_tarifarios fmt,fact_tarifas ft,fact_clases_servicio fcs WHERE fp.cod_cups='"+CodCups+"' AND fmt.manual_base=fp.manual_base AND fmt.cod_manual_tarifario='"+CodManual+"' AND ft.programa=fp.cod_programa AND ft.manual_tarifario=fmt.cod_manual_tarifario AND fcs.cod_clase_servicio=fp.clase_servicio LIMIT 1");
        	System.out.println("SELECT fp.cod_programa,fp.cod_cups,fp.descripcion AS Programa,fcs.descripcion AS ClaseServicio,ft.valor,fp.subcentro_costo FROM fact_programas fp,fact_manuales_tarifarios fmt,fact_tarifas ft,fact_clases_servicio fcs WHERE fp.cod_cups='"+CodCups+"' AND fmt.manual_base=fp.manual_base AND fmt.cod_manual_tarifario='"+CodManual+"' AND ft.programa=fp.cod_programa AND ft.manual_tarifario=fmt.cod_manual_tarifario AND fcs.cod_clase_servicio=fp.clase_servicio LIMIT 1");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ObtenerDetalleFacturaOrdenServicio "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerDetalleOrdenServicio(String CodOrden_fk){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_detalle_orden_servicio WHERE CodOrden_fk="+CodOrden_fk+" ");
        	System.out.println("SELECT * FROM fact_detalle_orden_servicio WHERE CodOrden_fk="+CodOrden_fk+" ");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ObtenerDetalleOrdenServicio "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet ProgramaCex(String Programa,String ManualTarifario){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.cod_programa,fp.cod_referencia,fp.descripcion,ft.valor FROM fact_programas fp,fact_tarifas ft WHERE fp.cod_programa=ft.programa AND ft.manual_tarifario='"+ManualTarifario+"' AND (fp.cod_referencia LIKE '%"+Programa+"%' OR fp.descripcion LIKE '%"+Programa+"%')");
        	System.out.println("SELECT fp.cod_programa,fp.cod_referencia,fp.descripcion,ft.valor FROM fact_programas fp,fact_tarifas ft WHERE fp.cod_programa=ft.programa AND ft.manual_tarifario='"+ManualTarifario+"' AND (fp.cod_referencia LIKE '%"+Programa+"%' OR fp.descripcion LIKE '%"+Programa+"%')");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>DiagnosticosCex "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet TipoDocumentos(){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>TipoDocumentos "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet ConsultarAdmisionEncabezadoOrden(String CodOrden){
		
		 //*/
       java.sql.ResultSet rs=null;
       Statement st = null;
       try{
       	Conexion con=new Conexion();
       	st = con.conn.createStatement();
       	rs=st.executeQuery("SELECT * FROM fact_orden_admision WHERE CodOrden='"+CodOrden+"'");
       }
       catch(Exception ex){
       	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ConsultarAdmisionEncabezadoOrden "+ex);
       }
       return rs;
   }
	
	
	public java.sql.ResultSet ConsultarOrdenes(String Parametro){
		
		 //*/
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fos.*,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS Paciente,aent.nombre_entidad AS entidadPaciente " +
        			" FROM fact_orden_servicio fos,adm_paciente ap,adm_convenio ac,adm_entidad aent " +
        			" WHERE fos.CodPac_fk=ap.pac_codigo_paciente AND fos.estado='1' " +
        			" AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato AND ac.ent_nit_contratante_fk=aent.ent_nit "+Parametro+"");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>ConsultarOrdenes "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarPrograma(String Programa,String CodManual){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fp.descripcion,fp.cod_cups,fp.cod_programa,ft.valor,fp.cod_referencia FROM fact_programas fp,fact_tarifas ft WHERE fp.cod_programa=ft.programa AND ft.manual_tarifario='"+CodManual+"' AND (fp.descripcion LIKE '%"+Programa+"%' OR fp.cod_cups LIKE '%"+Programa+"%' OR fp.cod_programa LIKE '%"+Programa+"%')");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarPrograma "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarEntidad(String Entidad){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aen.ent_nit,aen.nombre_entidad,ftc.cod_manualTarifario,aen.ent_nit_contratante,aen.direccion,aen.telefono FROM adm_entidad aen,fact_convenios fc,fact_tarifas_convenios  ftc,fact_manuales_tarifarios fmt WHERE aen.ent_nit=fc.cod_entidad AND fc.cod_convenio=ftc.cod_convenio AND aen.nombre_entidad LIKE '%"+Entidad+"%' AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND (fmt.manual_base='1' OR fmt.manual_base='2')");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarEntidad "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet DiagnosticosCex(String Diagnostico){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cie10 WHERE nombre LIKE '%"+Diagnostico+"%' OR codigoCIE LIKE '%"+Diagnostico+"%'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>DiagnosticosCex "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarConsecutivoOrden(){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM fact_orden_consecutivo");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarConsecutivoOrden "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacienteOrden(String Tipo_Documento,String Numero_Documento){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Documento,apac.pac_codigo_paciente AS CodPac,CONCAT( apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS Paciente,CONCAT(aen.nombre_entidad,'-',aen.ent_nit_contratante) AS entidad,apac.genero,(YEAR(CURDATE()) - YEAR(apac.fecha_nacimiento)) - (RIGHT(CURDATE(), 5) < RIGHT(apac.fecha_nacimiento, 5)) AS edad,apac.fecha_nacimiento,fmt.cod_manual_tarifario FROM adm_paciente apac,adm_convenio aco,adm_entidad aen,fact_convenios fc,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt  WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato   AND aco.ent_nit_contratante_fk = aen.ent_nit   AND apac.tipo_documento = '"+Tipo_Documento+"' AND apac.numero_documento = '"+Numero_Documento+"'  AND fc.cod_entidad = aen.ent_nit  AND ftc.cod_convenio=fc.cod_convenio AND ftc.cod_manualTarifario=fmt.cod_manual_tarifario AND (fmt.manual_base ='1' OR fmt.manual_base='2')");
        	//rs=st.executeQuery("SELECT CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Documento,apac.pac_codigo_paciente as CodPac,CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS Paciente,CONCAT(aen.nombre_entidad,'-',aen.ent_nit_contratante) AS entidad,apac.genero,(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento, 5)) AS edad,apac.fecha_nacimiento FROM adm_paciente apac,adm_convenio aco,adm_entidad aen,fact_convenios fc WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato   AND aco.ent_nit_contratante_fk = aen.ent_nit   AND apac.tipo_documento = '"+Tipo_Documento+"'   AND apac.numero_documento = '"+Numero_Documento+"'   AND fc.cod_entidad = aen.ent_nit ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarPacienteOrden "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacienteAnular(String Tipo_Documento,String Numero_Documento){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS Paciente,CONCAT(aen.nombre_entidad,'-',aen.ent_nit_contratante) AS esntidad,apac.genero,apac.fecha_nacimiento,CONCAT(pme.nombre,' ',pme.apellidos,'-',pes.nombre_especialidad) AS medico,phm.fechas,phm.horas,phm.codigo FROM adm_paciente apac,adm_convenio aco,adm_entidad aen,fact_convenios fc,pyp_horariomedico phm,pyp_citaspacientes pcp,pyp_medico pme,pyp_especialidad pes WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato  AND aco.ent_nit_contratante_fk = aen.ent_nit  AND apac.tipo_documento = '"+Tipo_Documento+"'  AND apac.numero_documento = '"+Numero_Documento+"'  AND fc.cod_entidad = aen.ent_nit  AND phm.codigo=pcp.CodHorMedico_fk  AND pcp.CodPac_fk=apac.pac_codigo_paciente  AND phm.estado='1'   AND pme.codigo=phm.codMedico_fk  AND pes.codigo=pme.codEspe_fk  ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarPacienteAnular "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacienteDevolverAtencion(String Tipo_Documento,String Numero_Documento){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS Paciente,CONCAT(aen.nombre_entidad,'-',aen.ent_nit_contratante) AS esntidad,apac.genero,apac.fecha_nacimiento,CONCAT(pme.nombre,' ',pme.apellidos,'-',pes.nombre_especialidad) AS medico,phm.fechas,phm.horas,phm.codigo FROM adm_paciente apac,adm_convenio aco,adm_entidad aen,fact_convenios fc,pyp_horariomedico phm,pyp_citaspacientes pcp,pyp_medico pme,pyp_especialidad pes WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato  AND aco.ent_nit_contratante_fk = aen.ent_nit  AND apac.tipo_documento = '"+Tipo_Documento+"'  AND apac.numero_documento = '"+Numero_Documento+"'  AND fc.cod_entidad = aen.ent_nit  AND phm.codigo=pcp.CodHorMedico_fk  AND pcp.CodPac_fk=apac.pac_codigo_paciente  AND phm.estado='3'  AND phm.fechas=CURDATE()  AND pme.codigo=phm.codMedico_fk  AND pes.codigo=pme.codEspe_fk  ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>BuscarPacienteAnular "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet VerificarDiagnosticoEgreso(String CodAdm){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM hic_diagnosticoegreso WHERE codAdm='"+CodAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>VerificarDiagnosticoEgreso "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet VerificarInasistencias(){
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.codigo AS phmCodigo FROM pyp_citaspacientes pcp,pyp_horariomedico phm WHERE pcp.CodHorMedico_fk=phm.codigo AND (phm.estado=1 or phm.estado=2) AND phm.fechas<CURDATE()");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>VerificarInasistencias "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet RelacionCitasActivas(String CodPaciente){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.NombrePaciente,CONCAT(pmed.nombre,' ',pmed.apellidos) AS Medico,pes.nombre_especialidad,CONCAT(phm.fechas,'/',phm.horas) AS Fecha,phm.estado,phm.codigo FROM pyp_citaspacientes pcp,pyp_horariomedico phm,pyp_especialidad pes,pyp_medico pmed WHERE pcp.CodPac_fk="+CodPaciente+" AND pcp.CodHorMedico_fk=phm.codigo AND pes.codigo=pcp.CodEspecialidad_fk AND pmed.codigo=pcp.CodMedico_fk AND phm.estado!=0 ORDER BY phm.fechas DESC limit 5");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>RelacionCitasActivas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet RelacionCitas30Dias(String CodPaciente){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.NombrePaciente,CONCAT(pmed.nombre,' ',pmed.apellidos) AS Medico,pes.nombre_especialidad,CONCAT(phm.fechas,'/',phm.horas) AS Fecha,phm.estado FROM pyp_citaspacientes pcp,pyp_horariomedico phm,pyp_especialidad pes,pyp_medico pmed WHERE pcp.CodPac_fk="+CodPaciente+" AND pcp.CodHorMedico_fk=phm.codigo AND pes.codigo=pcp.CodEspecialidad_fk AND pmed.codigo=pcp.CodMedico_fk AND (phm.estado=1 OR phm.estado=3 OR phm.estado=4) AND pcp.fecha BETWEEN (DATE_SUB(CURDATE(),INTERVAL 30 DAY)) AND (CURDATE()) ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>RelacionCitas30Dias "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CantidadCitasMes(String CodPaciente){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT COUNT(pcp.codigo) AS Cantidad FROM pyp_citaspacientes pcp,pyp_horariomedico phm WHERE pcp.CodPac_fk="+CodPaciente+" AND pcp.CodHorMedico_fk=phm.codigo AND (phm.estado=1 OR phm.estado=3 OR phm.estado=4) AND pcp.fecha BETWEEN (DATE_SUB(CURDATE(),INTERVAL 30 DAY)) AND (CURDATE())  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>CantidadCitasMes "+ex);
        }	
        return rs;
    }
	/*****************************consultas 1 variable********************/
	public java.sql.ResultSet consultapormedico(String CodigoMedico){	
		/**
		 * consulta de horarios disponible por medico
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where phm.codMedico_fk="+CodigoMedico+" and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultaporespecialidad(String CodigoEspecialidad){	
		/**
		 * consulta de horarios disponible por especialidad
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pes.codigo="+CodigoEspecialidad+" and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultaporespecialidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultaporhora(String hora){	
		/**
		 * consulta de horarios disponible por hora
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where phm.horas='"+hora+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultaporhora "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultaporfecha(String fecha){	
		/**
		 * consulta de horarios disponible por fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where phm.fechas='"+fecha+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultaporfecha "+ex);
        }	
        return rs;
    }
	/*****************************consultas 2 variables***************************************/
	public java.sql.ResultSet consultapormedicoespecialidad(String CodigoMedico,String CodigoEspecialidad){	
		/**
		 * consulta de horarios disponible por medico y especialidad
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pmd.codigo="+CodigoMedico+" and pes.codigo="+CodigoEspecialidad+" and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo  and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicoespecialidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultapormedicohora(String CodigoMedico,String hora){	
		/**
		 * consulta de horarios disponible por medico y hora
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pmd.codigo="+CodigoMedico+" and phm.horas='"+hora+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicohora "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet TiposCita(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from pyp_tipocita");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>TiposCita "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DatosdelaCita(String CodHorMed){	
		/**
		 * consulta de horarios disponible por medico y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_citaspacientes WHERE CodHorMedico_fk="+CodHorMed+"");
        	System.out.println("SELECT * FROM pyp_citaspacientes WHERE CodHorMedico_fk="+CodHorMed+"");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>DatosdelaCita "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet CitasActivasSoloMedico(String CodigoMedico){	
		/**
		 * consulta de horarios disponible por medico y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo,su.usu_codigo,SUBSTRING(phm.horas, 7, 3) AS jornada FROM pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes,seg_datos_personales sdp,seg_usuario su WHERE pmd.codigo = "+CodigoMedico+"  AND phm.codMedico_fk = pmd.codigo   AND pmd.codEspe_fk = pes.codigo AND phm.estado='0'  AND sdp.numeroDocumento = pmd.numeroDocumento AND su.dat_codigo_fk = sdp.dat_codigo AND phm.fechas >= CURDATE() ORDER BY phm.fechas,jornada,phm.horas LIMIT 100 ");
        	//System.out.println("SELECT ahm.codigo,ahm.horas,ahm.fechas,ahm.NombrePaciente,aes.nombre_especialidad,amd.nombre,amd.apellidos,ahm.estado,aes.codigo,amd.codigo,su.usu_codigo,SUBSTRING(ahm.horas, 7, 3) AS jornada FROM agm_horariomedico ahm,agm_medico amd,agm_especialidad aes,seg_datos_personales sdp,seg_usuario su WHERE amd.codigo = "+CodigoMedico+"  AND ahm.codMedico_fk = amd.codigo   AND amd.codEspe_fk = aes.codigo AND ahm.estado='0'  AND sdp.numeroDocumento = amd.numeroDocumento AND su.dat_codigo_fk = sdp.dat_codigo AND ahm.fechas >= CURDATE() ORDER BY ahm.fechas,jornada,ahm.horas LIMIT 100 ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicofecha "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet CitasDisponibleMedico(String CodMedico,String Fecha){	
		/**
		 * consulta de horarios disponible por medico y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_horariomedico WHERE estado='0' AND codMedico_fk='"+CodMedico+"' AND fechas='"+Fecha+"' AND fechas>=CURDATE() ");
        	System.out.println("SELECT * FROM pyp_horariomedico WHERE estado='0' AND codMedico_fk='"+CodMedico+"' AND fechas='"+Fecha+"' AND fechas>=CURDATE() ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>CitasDisponibleMedico "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet consultapormedicofecha2(String CodigoMedico,String fecha){	
		/**
		 * consulta de horarios disponible por medico y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo,su.usu_codigo,SUBSTRING(phm.horas,7,3) AS jornada  FROM pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes,seg_datos_personales sdp,seg_usuario su WHERE pmd.codigo="+CodigoMedico+" AND phm.fechas='"+fecha+"' AND phm.codMedico_fk = pmd.codigo AND pmd.codEspe_fk = pes.codigo AND sdp.numeroDocumento=pmd.numeroDocumento AND su.dat_codigo_fk=sdp.dat_codigo AND phm.fechas >= CURDATE() ORDER BY jornada,phm.horas  ");
        	System.out.println("SELECT phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo,su.usu_codigo,SUBSTRING(phm.horas,7,3) AS jornada  FROM pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes,seg_datos_personales sdp,seg_usuario su WHERE pmd.codigo="+CodigoMedico+" AND phm.fechas='"+fecha+"' AND phm.codMedico_fk = pmd.codigo AND pmd.codEspe_fk = pes.codigo AND sdp.numeroDocumento=pmd.numeroDocumento AND su.dat_codigo_fk=sdp.dat_codigo AND phm.fechas >= CURDATE() ORDER BY jornada,phm.horas  ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicofecha "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultapormedicofecha(String CodigoMedico,String fecha){	
		/**
		 * consulta de horarios disponible por medico y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pmd.codigo="+CodigoMedico+" and phm.fechas='"+fecha+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicofecha "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultaporespecialidadhora(String CodigoEspecialidad,String hora){	
		/**
		 * consulta de horarios disponible por especialidad y hora
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pes.codigo="+CodigoEspecialidad+" and phm.horas='"+hora+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultaporespecialidadhora "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultaporespecialidadfecha(String CodigoEspecialidad,String fecha){	
		/**
		 * consulta de horarios disponible por especialidad y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pes.codigo="+CodigoEspecialidad+" and phm.fechas='"+fecha+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultaporespecialidadfecha "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultaporhorafecha(String hora,String fecha){	
		/**
		 * consulta de horarios disponible por hora y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where phm.horas='"+hora+"' and phm.fechas='"+fecha+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultaporhorafecha "+ex);
        }	
        return rs;
    }
	/**********************************consulta 3 variables****************************/	
	public java.sql.ResultSet consultapormedicoespecialidadhora(String CodigoMedico,String CodigoEspecialidad,String hora){	
		/**
		 * consulta de horarios disponible por medico,especialidad y hora
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pmd.codigo="+CodigoMedico+" and pes.codigo="+CodigoEspecialidad+" and phm.horas='"+hora+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicoespecialidadhora "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultapormedicoespecialidadfecha(String CodigoMedico,String CodigoEspecialidad,String fecha){	
		/**
		 * consulta de horarios disponible por medico,especialidad y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pmd.codigo="+CodigoMedico+" and pes.codigo="+CodigoEspecialidad+" and phm.fechas='"+fecha+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicoespecialidadfecha "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultapormedicohorafecha(String CodigoMedico,String hora,String fecha){	
		/**
		 * consulta de horarios disponible por medico,hora y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pmd.codigo="+CodigoMedico+" and phm.horas='"+hora+"' and phm.fechas='"+fecha+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultapormedicohorafecha "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet consultaporespecialidadhorafecha(String CodigoEspecialidad,String hora,String fecha){	
		/**
		 * consulta de horarios disponible por medico,hora y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pes.codigo="+CodigoEspecialidad+" and phm.horas='"+hora+"' and phm.fechas='"+fecha+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultaporespecialidadhorafecha "+ex);
        }	
        return rs;
    }
	/**********************************consulta 4 variables********************************/
	public java.sql.ResultSet consultamedicoespecialidadhorafecha(String CodigoMedico,String CodigoEspecialidad,String hora,String fecha){	
		/**
		 * consulta de horarios disponible por medico,especialidad,hora y fecha
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,pmd.nombre,pmd.apellidos,phm.estado,pes.codigo,pmd.codigo from pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes where pmd.codigo="+CodigoMedico+" and pes.codigo="+CodigoEspecialidad+" and phm.fechas='"+fecha+"' and phm.horas='"+hora+"' and phm.codMedico_fk=pmd.codigo and pmd.codEspe_fk=pes.codigo and phm.fechas >= CURDATE() ORDER BY phm.fechas,phm.horas ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoConsultasAsignacion>>consultamedicoespecialidadhorafecha "+ex);
        }	
        return rs;
    }
	
	
}
