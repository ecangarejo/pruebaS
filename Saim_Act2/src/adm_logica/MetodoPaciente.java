/**
 * logica:MetodoPaciente
 * Desarrollado:yosimar valega
 */
package adm_logica;
import java.sql.*;

import adm_bean.Paciente;
import adm_logica.Conexion;

public class MetodoPaciente {
	
	
	
	public void ActualizarEstadoCama(String CodCama){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_censo_cama set est_codigo_estado_fk=3 where cen_numero_cama="+CodCama+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>ActualizarEstadoCama "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarHistoricoCama(String CodHistoricoCama){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_historico_cama set fecha_final='0000-00-00',hora_final='00:00:00',usuarioFin='-1' where his_codigo="+CodHistoricoCama+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>ActualizarHistoricoCama "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	public void ActualizarHistoricoCama1(String CodHistoricoCama,String CamaNueva){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_historico_cama set fecha_final='0000-00-00',hora_final='00:00:00',usuarioFin='-1',cen_numero_cama_fk="+CamaNueva+" where his_codigo="+CodHistoricoCama+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>ActualizarHistoricoCama1 "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void DevolverActivaAdmisionDevolucion1(String CodAdm,String CamaNueva){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_admisiones set estado=0,cen_numero_cama_fk="+CamaNueva+" where adm_numero_ingreso="+CodAdm+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>DevolverActivaAdmisionDevolucion1 "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void DevolverActivaAdmisionDevolucion(String CodAdm){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_admisiones set estado=0 where adm_numero_ingreso="+CodAdm+"  ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>DevolverActivaAdmisionDevolucion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void EliminarDestinoDevolucion(String CodAdm,String Destino){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_destinopaciente where codAdm="+CodAdm+" and DestinoPaciente='"+Destino+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>EliminarDestinoDevolucion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void EliminarDiagnosticosEgresoDevolucion(String CodAdm,String TipDiag){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_diagnosticoegreso where codAdm="+CodAdm+" and TipDiag='"+TipDiag+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>EliminarDiagnosticosEgresoDevolucion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	public java.sql.ResultSet LlenarCamas(String CodSubArea){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cen_numero_cama,codigocama from adm_censo_cama where codsubarea="+CodSubArea+" and est_codigo_estado_fk=1 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPaciente>>LlenarCamas "+ex);
        }
        return rs;
    }
	
	
	 public java.sql.ResultSet LlenarAreaCamasHosp(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT asu.codigo,CONCAT(aa.nombre,'>>',asu.nombre) AreaH FROM adm_area aa,adm_censo_cama acc,adm_subarea asu WHERE aa.codigo=asu.codigoarea AND asu.codigo=acc.codsubarea AND (acc.servicio=3 OR acc.servicio=2) GROUP BY asu.codigo  ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>LlenarAreaCamas "+ex);
	        }
	        return rs;
	    }
	
	 public java.sql.ResultSet LlenarAreaCamas(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select * from adm_area");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>LlenarAreaCamas "+ex);
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet LlenarSubAreaCamas(String CodArea){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select * from adm_subarea where codigoarea="+CodArea+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>LlenarSubAreaCamas "+ex);
	        }
	        return rs;
	    }
	
	 
	 public java.sql.ResultSet VerDetalleAdmision(String CodAdm){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select aad.numero_autorizacion,aad.medio_autorizacion,aad.autorizado_por,aad.registrado_por,CONCAT(aad.fecha_registro,'/',aad.hora_registro) as FechaRegistro,CONCAT(acc.pabellon,'>>',acc.ubicacion_cama,'>>',acc.codigocama) as Ubicacion,acc.est_codigo_estado_fk,acc.servicio from adm_admisiones aad,adm_censo_cama acc where aad.cen_numero_cama_fk=acc.cen_numero_cama and aad.adm_numero_ingreso="+CodAdm+" ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>VerDetalleAdmision "+ex);
	        }
	        return rs;
	       
	    }
	
	 public java.sql.ResultSet RegistrosDevolverAlta(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as NombrePaciente,CONCAT(apac.tipo_documento,'-',apac.numero_documento) as Documeto,adm.adm_numero_ingreso,acc.cen_numero_cama,CONCAT(adm.fecha_registro,'/',adm.hora_registro) as Registro,CONCAT(ahc.fecha_final,'/',ahc.hora_final) as FechaFinal,ahc.his_codigo FROM adm_paciente apac,adm_admisiones adm,adm_censo_cama acc,adm_historico_cama ahc WHERE apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' and apac.pac_codigo_paciente=adm.pac_codigo_paciente_fk and adm.estado=1 and acc.cen_numero_cama=adm.cen_numero_cama_fk and ahc.adm_numero_ingreso_fk=adm.adm_numero_ingreso and ahc.cen_numero_cama_fk=acc.cen_numero_cama ");
	        	System.out.print(rs);
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>RegistrosDevolverAlta "+ex);
	        }
	        return rs;
	       
	    }
	
	
	public void ActualizarHistoricoCama(String CodigoAdmision,String CodigoCama,String Fecha,String Hora,String CodigoUsuario){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_historico_cama set fecha_final='"+Fecha+"',hora_final='"+Hora+"',usuarioFin="+CodigoUsuario+" where cen_numero_cama_fk="+CodigoCama+" and adm_numero_ingreso_fk="+CodigoAdmision+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>ActualizarHistoricoCama "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void OmitirAdmision(String CodigoAdmision,String txtMotivoAnulacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_admisiones set estado=2,observaciones='"+txtMotivoAnulacion+"' where adm_numero_ingreso='"+CodigoAdmision+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>OmitirAdmision "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarEstadodeCama(String CodigoCama){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_censo_cama set est_codigo_estado_fk=1 where cen_numero_cama='"+CodigoCama+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	    	 System.out.println("Error en MetodoPaciente>>ActualizarEstadodeCama "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet BuscarDiagnosticoIngresoAdmisiones(String CodAdmision){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cie.nombre,cie.codigoCIE from hic_diagnosticoingreso hdi,cie10 cie where hdi.codAdm='"+CodAdmision+"' and hdi.CodDiag_fk=cie.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPaciente>>BuscarDiagnosticoIngresoAdmisiones "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarDiagnosticoEgresoAdmisiones(String CodAdmision){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cie.nombre,cie.codigoCIE from hic_diagnosticoegreso hde,cie10 cie where hde.codAdm='"+CodAdmision+"' and hde.CodDiag_fk=cie.codigo and hde.TipDiag='EG' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPaciente>>BuscarDiagnosticoEgresoAdmisiones "+ex);
        }
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDiagnosticoEgresoAdmisionesH(String CodAdmision){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cie.nombre,cie.codigoCIE from hic_diagnosticoegreso hde,cie10 cie where hde.codAdm='"+CodAdmision+"' and hde.CodDiag_fk=cie.codigo and hde.TipDiag='EGH' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPaciente>>BuscarDiagnosticoEgresoAdmisionesH "+ex);
        }
        return rs;
    }
	
	public java.sql.ResultSet BuscarTrasladoUrgHos(String TipoDocumento,String NumeroDocumento){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select aad.adm_numero_ingreso as CodAdm,CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as Paciente,CONCAT(ath.Fecha,'/',ath.Hora) as FechaTraslado,aad.estado from adm_trasladourg_hosp ath,adm_admisiones aad,adm_paciente apac where apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and aad.adm_numero_ingreso=ath.CodAdm and apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPaciente>>BuscarTrasladoUrgHos "+ex);
        }
        return rs;
    }
	
	 public java.sql.ResultSet BuscarCambioMedicoTratante(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS NombPac,aad.adm_numero_ingreso,amt.medico_texto,amt.codigo FROM adm_admisiones aad,adm_paciente apac,adm_medico_tratante amt WHERE apac.pac_codigo_paciente = aad.pac_codigo_paciente_fk AND apac.tipo_documento = '"+TipoDocumento+"'  AND apac.numero_documento = '"+NumeroDocumento+"' AND aad.estado=0 AND amt.codAdm_fk=aad.adm_numero_ingreso AND amt.estado=0 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>BuscarCambioMedicoTratante "+ex);
	        }
	        return rs;
	    }
	
	 public java.sql.ResultSet BuscarAdmisiones(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.nombre,apac.primer_apellido,apac.segundo_apellido,aad.adm_numero_ingreso,aad.fecha_registro,aad.hora_registro,aad.estado from adm_admisiones aad,adm_paciente apac where apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>BuscarAdmisiones "+ex);
	        }
	        return rs;
	    }
	 
	 
	 public java.sql.ResultSet BuscarAdmisionAnular(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select apac.nombre,apac.primer_apellido,apac.segundo_apellido,aad.adm_numero_ingreso,aad.registrado_por,aad.fecha_registro,aad.hora_registro,aad.cen_numero_cama_fk,acc.pabellon,acc.ubicacion_cama,acc.codigocama from adm_paciente apac,adm_admisiones aad,adm_censo_cama acc where apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' and aad.estado=0 and acc.cen_numero_cama=aad.cen_numero_cama_fk");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>BuscarAdmisionAnular "+ex);
	        }
	        return rs;
	    }
	 
	 
	 
	
	 public java.sql.ResultSet datosPaciente(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.tipo_documento,apac.numero_documento,adm.adm_numero_ingreso,acc.pabellon,acc.ubicacion_cama,acc.codigocama,acc.cen_numero_cama FROM adm_paciente apac,adm_admisiones adm,adm_censo_cama acc WHERE apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' and apac.pac_codigo_paciente=adm.pac_codigo_paciente_fk and adm.estado=0  and acc.cen_numero_cama=adm.cen_numero_cama_fk");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>datosPaciente "+ex);
	        }
	        return rs;
	       
	    }
	 
	 public java.sql.ResultSet BuscarDatosPacienteSQL(String sql){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(ap.primer_apellido,' ',ap.segundo_apellido,' ',ap.nombre) AS NombrePaciente,ap.genero,ap.fecha_nacimiento,CONCAT(amu.nombre,'-',adp.nombre) AS Municipio,ap.direccion,CONCAT(ap.telefono_celular,'-',ap.telefono_fijo,'-',ap.telefono_oficina) AS Telefonos,ap.estado_civil,aen.nombre_entidad,aen.ent_nit_contratante,ap.tipo_afiliacion,ap.nivel_cotizante,fmt.descripcion AS ManualTarifario,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS Documento FROM adm_paciente ap,adm_convenio acv,adm_entidad aen,adm_municipio amu,adm_departamento adp,fact_convenios fcv,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt,fact_manuales_base fmb WHERE ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND acv.ent_nit_contratante_fk=aen.ent_nit AND ap.mun_codigo_fk=amu.muni_cod AND adp.dept_codigo=amu.dept_codigo_fk AND fcv.cod_entidad=aen.ent_nit AND ftc.cod_convenio=fcv.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND fmb.cod_manual_base=fmt.manual_base AND (fmb.cod_manual_base='1' OR fmb.cod_manual_base='2') "+sql);
	        	System.out.println("SELECT CONCAT(ap.primer_apellido,' ',ap.segundo_apellido,' ',ap.nombre) AS NombrePaciente,ap.genero,ap.fecha_nacimiento,CONCAT(amu.nombre,'-',adp.nombre) AS Municipio,ap.direccion,CONCAT(ap.telefono_celular,'-',ap.telefono_fijo,'-',ap.telefono_oficina) AS Telefonos,ap.estado_civil,aen.nombre_entidad,aen.ent_nit_contratante,ap.tipo_afiliacion,ap.nivel_cotizante,fmt.descripcion AS ManualTarifario,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS Documento FROM adm_paciente ap,adm_convenio acv,adm_entidad aen,adm_municipio amu,adm_departamento adp,fact_convenios fcv,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt,fact_manuales_base fmb WHERE ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND acv.ent_nit_contratante_fk=aen.ent_nit AND ap.mun_codigo_fk=amu.muni_cod AND adp.dept_codigo=amu.dept_codigo_fk AND fcv.cod_entidad=aen.ent_nit AND ftc.cod_convenio=fcv.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND fmb.cod_manual_base=fmt.manual_base AND (fmb.cod_manual_base='1' OR fmb.cod_manual_base='2') "+sql);
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>BuscarDatosPaciente "+ex);
	        }
	        return rs;
	       
	    }
	 
	 public java.sql.ResultSet BuscarDatosPaciente(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(ap.primer_apellido,' ',ap.segundo_apellido,' ',ap.nombre) AS NombrePaciente,ap.genero,ap.fecha_nacimiento,CONCAT(amu.nombre,'-',adp.nombre) AS Municipio,ap.direccion,CONCAT(ap.telefono_celular,'-',ap.telefono_fijo,'-',ap.telefono_oficina) AS Telefonos,ap.estado_civil,aen.nombre_entidad,aen.ent_nit_contratante,ap.tipo_afiliacion,ap.nivel_cotizante,fmt.descripcion AS ManualTarifario,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS Documento FROM adm_paciente ap,adm_convenio acv,adm_entidad aen,adm_municipio amu,adm_departamento adp,fact_convenios fcv,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt,fact_manuales_base fmb WHERE ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND acv.ent_nit_contratante_fk=aen.ent_nit AND ap.mun_codigo_fk=amu.muni_cod AND adp.dept_codigo=amu.dept_codigo_fk AND fcv.cod_entidad=aen.ent_nit AND ftc.cod_convenio=fcv.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND fmb.cod_manual_base=fmt.manual_base AND (fmb.cod_manual_base='1' OR fmb.cod_manual_base='2') AND ap.tipo_documento='"+TipoDocumento+"' AND ap.numero_documento LIKE '%"+NumeroDocumento+"%'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>BuscarDatosPaciente "+ex);
	        }
	        return rs;
	       
	    }
	 
	 
	 
	 public java.sql.ResultSet datosPacienteAmbHosp(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.tipo_documento,apac.numero_documento,adm.adm_numero_ingreso,acc.pabellon,acc.ubicacion_cama,acc.codigocama,acc.cen_numero_cama FROM adm_paciente apac,adm_admisiones adm,adm_censo_cama acc WHERE apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' and apac.pac_codigo_paciente=adm.pac_codigo_paciente_fk and adm.estado=0  and acc.cen_numero_cama=adm.cen_numero_cama_fk");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>datosPacienteAmbHosp "+ex);
	        }
	        return rs;
	       
	    }
	 
	 public java.sql.ResultSet Censo(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT a.adm_numero_ingreso,CONCAT(c.pabellon,'>',c.ubicacion_cama,'>',c.codigocama) as Ubicacion,p.genero,CONCAT(p.nombre,' ',p.primer_apellido,' ',p.segundo_apellido) AS Paciente,CONCAT(p.tipo_documento,'-',p.numero_documento)as Documento, ent.nombre_entidad FROM  adm_paciente p, adm_censo_cama c, adm_admisiones a, adm_entidad ent, adm_convenio con  WHERE a.pac_codigo_paciente_fk = p.pac_codigo_paciente AND c.cen_numero_cama = a.cen_numero_cama_fk AND ent.ent_nit = con.ent_nit_contratante_fk AND con.conv_numero_contrato = p.conv_numero_contrato_fk AND a.estado='0' group by p.pac_codigo_paciente ORDER BY a.fecha_registro desc ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>Censo "+ex);
	        }
	        return rs;
	       
	    }
	 
	 
	 public java.sql.ResultSet RegistrosAltaPaciente(String TipoDocumento,String NumeroDocumento){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) as NombrePaciente,CONCAT(apac.tipo_documento,'-',apac.numero_documento) as Documeto,adm.adm_numero_ingreso,acc.cen_numero_cama,CONCAT(adm.fecha_registro,'/',adm.hora_registro) as Registro FROM adm_paciente apac,adm_admisiones adm,adm_censo_cama acc WHERE apac.tipo_documento='"+TipoDocumento+"' and apac.numero_documento='"+NumeroDocumento+"' and apac.pac_codigo_paciente=adm.pac_codigo_paciente_fk and adm.estado=1 and acc.cen_numero_cama=adm.cen_numero_cama_fk");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>RegistrosAltaPaciente "+ex);
	        }
	        return rs;
	       
	    }
	 
	 public java.sql.ResultSet CodigoPaciente(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.tipo_documento,apac.numero_documento,adm.adm_numero_ingreso,acc.pabellon,acc.ubicacion_cama,acc.codigocama,acc.cen_numero_cama FROM adm_paciente apac,adm_admisiones adm,adm_censo_cama acc WHERE apac.pac_codigo_paciente='"+CodPac+"' and apac.pac_codigo_paciente=adm.pac_codigo_paciente_fk and adm.estado=0 and adm.atendido=0 and acc.cen_numero_cama=adm.cen_numero_cama_fk");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>CodigoPaciente "+ex);
	        }
	        return rs;
	       
	    }
	 public java.sql.ResultSet datosPacienteAlta(String CodAdmision,String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct concat(hfor.nombre_formato,'->',hafp.fecha,'/',hafp.hora,' - ',sdt.apellido,' ',sdt.nombre) as Formato from hic_formato hfor,hic_adm_formatos_pac hafp,seg_usuario sgu,seg_datos_personales sdt,adm_admisiones adm where hafp.codigo_for_fk= hfor.codigo and hafp.estado=0 and sgu.dat_codigo_fk=sdt.dat_codigo and sgu.usu_codigo=hafp.codigo_usu_fk and adm.adm_numero_ingreso=hafp.codigo_adm_fk and hafp.codigo_adm_fk='"+CodAdmision+"' and hafp.codigo_pac_fk='"+CodPac+"' ");
	        	//System.out.println("select distinct hfor.nombre_formato,hafp.fecha,hafp.hora,sdt.apellido,sdt.nombre from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,hic_resultado hres,hic_adm_formatos_pac hafp,seg_usuario sgu,seg_datos_personales sdt,adm_admisiones adm where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.codPreg_fk=hp.codigo and hafp.codigo_for_fk= hfor.codigo and hafp.estado=0 and hafp.codigo_pac_fk='"+CodPac+"' and sgu.dat_codigo_fk=sdt.dat_codigo and sgu.usu_codigo=hafp.codigo_usu_fk and adm.adm_numero_ingreso=hafp.codigo_adm_fk and hafp.codigo_adm_fk='"+CodAdmision+"'");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>datosPacienteAlta "+ex);
	        }
	        return rs;
	       
	    }
	 
	 public java.sql.ResultSet VerificarFormatosPendientes(String CodigoAdmision){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select codigo from hic_adm_formatos_pac where codigo_adm_fk="+CodigoAdmision+" and estado=0 ");
	        }
	        catch(Exception ex){
	        	System.out.println("Error en MetodoPaciente>>VerificarFormatosPendientes "+ex);
	        }
	        return rs;
	       
	    }
	
	/**
	 * obtiene el nombre apellido de la madre admitida
	 */
	
	 public java.sql.ResultSet SQLPacMadre(String ce){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        //String numero="Nacido Vivo";
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select adm_paciente.nombre,adm_paciente.primer_apellido,adm_paciente.segundo_apellido from adm_paciente,adm_admisiones where numero_documento="+ce+" and genero='Femenino' and adm_admisiones.pac_codigo_paciente_fk=adm_paciente.pac_codigo_paciente");

	        }
	        catch(Exception ex){
	        	
	        }
	      
	        return rs;
	        
	    }
	 
	 /**
	  * obtiene el codigo del paciente admitido
	  */
	 public java.sql.ResultSet SQLPacPa(String ce, String tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        //String numero="Nacido Vivo";
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select adm_paciente.pac_codigo_paciente,adm_paciente.primer_apellido,adm_paciente.segundo_apellido,adm_paciente.nombre from adm_paciente where numero_documento='"+ce+"' and tipo_documento='"+tipo+"' ");

	        }
	        catch(Exception ex){
	        	
	        }
	      
	        return rs;
	        
	    }
	 
	 /**
	  * obtienen la lista del nacidos vivos sin admision
	  */
	
	 public java.sql.ResultSet SQLNacido(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        String numero="Nacido Vivo";
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	          //rs=st.executeQuery("select DISTINCT nombre,primer_apellido,segundo_apellido,fecha_nacimiento,pac_codigo_paciente,numero_documento,tipo_documento from adm_paciente  where  tipo_documento='Nacido Vivo' and pac_codigo_paciente not in (select pac_codigo_paciente_fk from adm_admisiones)");
	        	rs=st.executeQuery("select DISTINCT nombre,primer_apellido,segundo_apellido,fecha_nacimiento,pac_codigo_paciente,numero_documento,tipo_documento from adm_paciente  where  tipo_documento='Nacido Vivo' and pac_codigo_paciente in (select pac_codigo_paciente_fk from adm_admisiones)");
	        }
	        catch(Exception ex){
	        	
	        }//("select nombre_entidad from adm_entidad");
	      
	        return rs;
	        
	    }
	 /**
	  * obtiene entidad,nombre de paciente, primer apellido
	  */
	 
	 public java.sql.ResultSet SQLEntidad(String numero,String Tipo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select adm_entidad.nombre_entidad,adm_paciente.nombre,adm_paciente.primer_apellido,adm_paciente.segundo_apellido,adm_paciente.direccion, adm_municipio.nombre,adm_departamento.nombre,adm_pais.nombre,adm_paciente.longitud, adm_paciente.latitud,adm_paciente.barrio,adm_departamento.dept_codigo,adm_departamento.pais_codigo_fk,adm_paciente.mun_codigo_fk from adm_paciente, adm_entidad, adm_convenio,adm_municipio,adm_departamento,adm_pais where adm_paciente.tipo_documento='"+Tipo+"' and adm_paciente.numero_documento='"+ numero+"'and adm_paciente.conv_numero_contrato_fk=adm_convenio.conv_numero_contrato and adm_convenio.ent_nit_contratante_fk=adm_entidad.ent_nit and adm_municipio.muni_cod=adm_paciente.mun_codigo_fk and adm_municipio.dept_codigo_fk=adm_departamento.dept_codigo and adm_pais.pais_codigo=adm_departamento.pais_codigo_fk ");
	        }
	        catch(Exception ex){
	        	
	        }//("select nombre_entidad from adm_entidad");
	      
	        return rs;
	        
	    }
	 /**
	  * obtiene nombre de entidad, numero de convenio
	  */
	 
	 public java.sql.ResultSet SQLEps(){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select adm_entidad.nombre_entidad, adm_convenio.conv_numero_contrato from  adm_entidad, adm_convenio where adm_convenio.ent_nit_contratante_fk=adm_entidad.ent_nit");
	        }
	        catch(Exception ex){
	        	
	        }//("select nombre_entidad from adm_entidad");
	      
	        return rs;
	        
	    }
	 /**
	  * obtiene Direcciones de pacientes by: Greys Hernandez
	  */
	 
	  public java.sql.ResultSet SQLDirecciones(){
		  java.sql.ResultSet rs= null;
		  Statement st=null;
		  try{
			  Conexion con=new Conexion();
			  st=con.conn.createStatement();
			  rs=st.executeQuery("SELECT numero_documento,direccion FROM adm_paciente LIMIT 0,10");
			  
		  }catch(Exception ex){
			  
			  
		  }
		  return rs;
	  }
	
	  /**
		  * obtiene cantidad de direcciones en la tabla adm_paciente by: Greys Hernandez
		  */
	  public java.sql.ResultSet SQLcantidad(){
		  java.sql.ResultSet rs= null;
		  Statement st=null;
		  try{
			  Conexion con=new Conexion();
			  st=con.conn.createStatement();
			  rs=st.executeQuery("select count(*)direccion from adm_paciente");
			  
		  }catch(Exception ex){
			  
			  
		  }
		  return rs;
	  }
	  
	  /**
		  * obtiene nombre completo y direccion del paciente by: Greys Hernandez
		  */
	  public java.sql.ResultSet SQlPuntos(){
		  java.sql.ResultSet rs=null;
		  Statement st=null;
		  try{
			  Conexion con= new Conexion();
			  st=con.conn.createStatement();
			  rs=st.executeQuery("SELECT CONCAT(adm_paciente.nombre,' ',adm_paciente.primer_apellido,' ',adm_paciente.segundo_apellido,' ', adm_paciente.direccion,' ', adm_municipio.nombre,'-',adm_departamento.nombre,' ',adm_pais.nombre),adm_paciente.longitud,adm_paciente.latitud FROM adm_paciente,adm_municipio,adm_departamento,adm_pais WHERE adm_municipio.muni_cod=adm_paciente.mun_codigo_fk AND adm_municipio.dept_codigo_fk=adm_departamento.dept_codigo AND adm_pais.pais_codigo=adm_departamento.pais_codigo_fk ORDER BY pac_codigo_paciente LIMIT 500");
			  
			  
		  }catch(Exception ex){
			  
		  }
		  return rs;
	  }
	  
	  /**
		  * Busqueda de paciente Mapa by: Greys Hernandez
		  */
	  public java.sql.ResultSet SQLBusqueda(String Dato,String tipo){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT numero_documento,nombre,primer_apellido,segundo_apellido,longitud,latitud FROM adm_paciente WHERE numero_documento='"+Dato+"' AND tipo_documento='"+tipo+"'");
						
		}catch(Exception ex){
			
		}
			
		return rs;
		
	  }
	  
	  public java.sql.ResultSet SQLBusquedaPa(String Nom,String Apell){
			java.sql.ResultSet rs=null;
			Statement st=null;
			try{
				Conexion con=new Conexion();
				st=con.conn.createStatement();
				rs=st.executeQuery("SELECT numero_documento,nombre,primer_apellido,segundo_apellido,longitud,latitud FROM adm_paciente WHERE nombre='"+Nom+"' AND primer_apellido='"+Apell+"'");
							
			}catch(Exception ex){
				
			}
				
			return rs;
			
		  }
	  
	  
	  
	/* public int CantidadDeDatosDireccion(ResultSet r){
		  ResultSet rs=r;
		  int valor=0;
		   try{
			  
			  while(rs.next()){
				  
				 valor=rs.getInt(1); 
				  
			  }
			  		  
			  
			}catch(SQLException e){
			   
	 	  }

		 
			return valor;
	  }*/
	
	
		public void ActualizarResultados(String codigo,String latitud,String longitud){		
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update adm_paciente set longitud='"+longitud+"',latitud='"+latitud+"' where pac_codigo_paciente='"+codigo+"'");
		     	//System.out.println(" ActualizarResultados update adm_paciente set longitud='"+longitud+"',latitud='"+longitud+"' where pac_codigo_paciente='"+codigo+"'");
		     	st.executeUpdate();
		     	st.close();
		     	con.cerrar();	     	
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoCrearFormato>>ActualizarResultados "+ex);
		     	ex.getMessage();	     
		     }		    
		 }
		

		public void ActualizarDireccionAdmision(String direccion,String latitud,String longitud, String tipo , String ced,String barrio, String codmunicipio){		
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update adm_paciente set direccion='"+direccion+"', longitud='"+longitud+"',latitud='"+latitud+"', barrio='"+barrio+"',mun_codigo_fk='"+codmunicipio+"' where tipo_documento='"+tipo+"' and numero_documento='"+ced+"'");
		     	//System.out.println(" ActualizarResultados update adm_paciente set longitud='"+longitud+"',latitud='"+longitud+"' where pac_codigo_paciente='"+codigo+"'");
		     	st.executeUpdate();
		     	st.close();
		     	con.cerrar();	     	
		     }
		     catch(Exception ex){
		     	System.out.println("Error en MetodoCrearFormato>>ActualizarResultados "+ex);
		     	ex.getMessage();	     
		     }		    
		 }
	  
	// by: Greys Hernandez Vargas
		 
		/* public java.sql.ResultSet CodigoMunicipio(String municipio, String departamento){
				java.sql.ResultSet rs=null;
				
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT m.muni_cod FROM adm_municipio m, adm_departamento d WHERE m.nombre='"+municipio+"' AND d.nombre='"+departamento+"' AND m.dept_codigo_fk=d.dept_codigo");
					System.out.println("SELECT m.muni_cod FROM adm_municipio m, adm_departamento d WHERE m.nombre='"+municipio+"' AND d.nombre='"+departamento+"' AND m.dept_codigo_fk=d.dept_codigo");
								
				}catch(Exception ex){
					ex.getMessage();
				}
					
				return rs;
				
			  }*/
	 /**
	  * obtiene numero de convenio
	  */
	 public java.sql.ResultSet SQLCodConv(String nom){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select  adm_convenio.conv_numero_contrato from  adm_entidad, adm_convenio where adm_entidad.ent_nit='"+nom+"'and adm_convenio.ent_nit_contratante_fk=adm_entidad.ent_nit");
	        }
	        catch(Exception ex){
	        	
	        }
	      
	        
	        return rs;
	        
	    }
	 
	 
	 
	 /**
	  * obtiene datos de los pacientes segun el codigo
	  */
	 
	 public java.sql.ResultSet SQLDatosPac(String codigo){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select nombre,numero_documento,fecha_nacimiento,genero,tipo_afiliacion from  adm_paciente where pac_codigo_paciente="+codigo+"");
	        }
	        catch(Exception ex){
	        	
	        }//("select nombre_entidad from adm_entidad");
	      
	        return rs;
	        
	    }
	 /**
	  * ingresar datos de pacientes en la table adm_demografico
	  */
	 
	 
	 public void GuardarAnulacionAdmision(String cod_adm,String cod_usu,String motivo_consulta,String fecha_anulacion,String hora_anulacion){
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO adm_admisiones_anuladas(cod_adm,cod_usu,motivo_consulta,fecha_anulacion,hora_anulacion) VALUES(?,?,?,?,?)");				
			    ps.setString(1, cod_adm);
				ps.setString(2,cod_usu);
				ps.setString(3, motivo_consulta);
				ps.setString(4, fecha_anulacion);
				ps.setString(5, hora_anulacion);
				ps.executeUpdate();
				ps.close();
				con.cerrar();
			
			
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoPaciente>>insertar "+ex);
			}
			
			
		}
	 
	 
	 
	 
	 public void insertar(String tip, String ced,String afiliacion,String nivel,  String pape,String sape,String nom,String gene,String nacionali,String dir,String tel,String telofi,String telcel,String ocu,String emp,String zona,String reli,String estadoci,String ra,String estra,String ema,String mun_cod,String fechanaci, String numcontra,String ocupacion1,String longi,String lati,String bar){
		 Paciente pac = new Paciente();
			pac.setDocumento(tip);
			pac.setCedula(ced);
			pac.setAfiliacion(afiliacion);
			pac.setCotizante(nivel);
			pac.setPapellido(pape);
			pac.setSapellido(sape);
			pac.setNombre(nom);
			pac.setGenero(gene);
			pac.setNacionalidad(nacionali);
			pac.setDireccion(dir);
			pac.setTelefonofijo(tel);
			pac.setTelefonooficina(telofi);
			pac.setTelefonocelular(telcel);
			pac.setOcupacion(ocu);
			pac.setEmpresa(emp);
			pac.setZona(zona);
			pac.setReligion(reli);
			pac.setEstadocivil(estadoci);
			pac.setRaza(ra);
			pac.setEstrato(estra);
			pac.setEmail(ema);
			pac.setCodigoMun(mun_cod);
			pac.setFecha(fechanaci);
			pac.setContrato(numcontra);
			pac.setLongitud(longi);
			pac.setLatitud(lati);
			pac.setBarrio(bar);
			
			try{
				
				String fechapre,ini,med,fin=null;
				ini=pac.getFecha().substring(0,2);
				med=pac.getFecha().substring(3,5);
				fin=pac.getFecha().substring(6,10);
				fechapre=fin+"/"+med+"/"+ini;
				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into adm_paciente(tipo_documento,numero_documento,tipo_afiliacion,nivel_cotizante,primer_apellido,segundo_apellido,nombre,genero,nacionalidad,direccion,telefono_fijo,telefono_oficina,telefono_celular,ocu_codigo_fk,empresa_labora,zona_residencial,religion,estado_civil,raza,estrato,email,mun_codigo_fk,fecha_nacimiento,conv_numero_contrato_fk,longitud,latitud,barrio) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				
			    ps.setString(1, pac.getDocumento());
				ps.setString(2,pac.getCedula());
				ps.setString(3, pac.getAfiliacion());
				ps.setString(4,pac.getCotizante());
				ps.setString(5, pac.getPapellido());
				ps.setString(6,pac.getSapellido());
				ps.setString(7,pac.getNombre());
				ps.setString(8,pac.getGenero());
				ps.setString(9,pac.getNacionalidad());
				ps.setString(10,pac.getDireccion());
				ps.setString(11,pac.getTelefonofijo());
				ps.setString(12,pac.getTelefonooficina());
				ps.setString(13,pac.getTelefonocelular());
				ps.setString(14,ocupacion1);
				ps.setString(15,pac.getEmpresa());
				ps.setString(16,pac.getZona());
				ps.setString(17,pac.getReligion());
				ps.setString(18,pac.getEstadocivil());
				ps.setString(19,pac.getRaza());
				ps.setString(20,pac.getEstrato());
				ps.setString(21,pac.getEmail());
				ps.setString(22,pac.getCodigoMun());
				ps.setString(23,fechapre);
				ps.setString(24,pac.getContrato());
				ps.setString(25,pac.getLongitud());
				ps.setString(26,pac.getLatitud());
				ps.setString(27,pac.getBarrio());
				
				ps.executeUpdate();
				ps.close();
				con.cerrar();
			
			
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoPaciente>>insertar "+ex);
			}
			
			
		}
	
}

