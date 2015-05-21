/**
 * logica: MetodoLaboratoriosHistoria se encuentran las inserciones,consultas y actualizaciones
 * para el ingreso de los laboratorios que tiene el paciente.
*/
package hic_metodo;

import hic_bean.CrearPacLab;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;



public class MetodoLaboratoriosHistoria {

public void ActualizarFormulacionEstudios(String CodFormlab){
		    PreparedStatement st = null;
		    PreparedStatement st1 = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("UPDATE agm_formlaboratorio SET estado=1 WHERE codigo="+CodFormlab+" ");
		     	st.executeUpdate();	 
		     	st.close();
		     	
		     	st= con.conn.prepareStatement("UPDATE agm_detalleformlab SET estado=1 WHERE CodFormLab_fk="+CodFormlab+" ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("ERROR EN MetodoLaboratoriosHistoria>>ActualizarFormulacionEstudios "+ex);
		     	ex.getMessage();	     
		     }	
		 }

public void EliminarDetalleFormulacionLaboratorio(String CodDetLab){
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("DELETE FROM agm_detalleformlab WHERE codigo="+CodDetLab+" ");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("ERROR EN MetodoLaboratoriosHistoria>>EliminarDetalleFormulacionLaboratorio "+ex);
		     	ex.getMessage();	     
		     }	
		 }



	public void InsertarDetalleFormulacionEstudios(String CodFormLab_fk,String CodEstudiolab_fk){
		   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO agm_detalleformlab(CodFormLab_fk,CodEstudiolab_fk)VALUES(?,?)");
			    ps.setString(1,CodFormLab_fk);
			    ps.setString(2,CodEstudiolab_fk);
			   	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoLaboratoriosHistoria>>InsertarDetalleFormulacionEstudios "+ex);
			}

		}

public java.sql.ResultSet ObtenerCodigoFormulaEstudio(Date fecha,Time hora){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM agm_formlaboratorio WHERE fecha='"+fecha+"' AND hora='"+hora+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerCodigoFormulaEstudio "+ex);
        }	
        return rs;
    }
	

	public void InsertarFormulacionEstudios(String CodPac,String CodCita,String CodUsu,Time hora,Date fecha){
   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO agm_formlaboratorio(CodPac_fk,CodCita_fk,CodUsu_fk,hora,fecha)VALUES(?,?,?,?,?)");
			    ps.setString(1,CodPac);
			    ps.setString(2,CodCita);
			    ps.setString(3,CodUsu);
			    ps.setTime(4,hora);
			    ps.setDate(5,fecha);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoLaboratoriosHistoria>>InsertarFormulacionEstudios "+ex);
			}

		}

	
	public java.sql.ResultSet ObtenerDetalleFormulaEstudio(String CodFormLab){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM(SELECT adl.codigo,lex.nombre FROM agm_formlaboratorio afl,agm_detalleformlab adl,lab_subarea lex WHERE afl.codigo = adl.CodFormLab_fk  AND afl.codigo = "+CodFormLab+" AND lex.codigo = adl.CodEstudiolab_fk  UNION SELECT adl.codigo,lex.nombre FROM agm_formlaboratorio afl,agm_detalleformlab adl,lab_examen lex WHERE afl.codigo = adl.CodFormLab_fk AND afl.codigo = "+CodFormLab+" AND lex.codigo = adl.CodEstudiolab_fk  ) AS a ORDER BY a.codigo");
        	System.out.println("SELECT * FROM(SELECT adl.codigo,lex.nombre FROM agm_formlaboratorio afl,agm_detalleformlab adl,lab_subarea lex WHERE afl.codigo = adl.CodFormLab_fk  AND afl.codigo = "+CodFormLab+" AND lex.codigo = adl.CodEstudiolab_fk  UNION SELECT adl.codigo,lex.nombre FROM agm_formlaboratorio afl,agm_detalleformlab adl,lab_examen lex WHERE afl.codigo = adl.CodFormLab_fk AND afl.codigo = "+CodFormLab+" AND lex.codigo = adl.CodEstudiolab_fk  ) AS a ORDER BY a.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDetalleFormulaEstudio "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ResumenOrdenServicio(String CodAdmision){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('1')AS Tipo FROM hic_orden hod,hic_detalleordenlaboratorio hdl,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdl.CodOrdLab_fk AND hod.TipoOrden=1 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('2')AS Tipo FROM hic_orden hod,hic_detalleordenimagenes hdi,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdi.CodOrdImg_fk AND hod.TipoOrden=2 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hfor.codigo,CONCAT(hfor.fecha,'/',hfor.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('3')AS Tipo FROM hic_formulacion hfor,hic_detalle_formulacion hdf,seg_usuario su,seg_datos_personales sdt WHERE hfor.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hfor.codigo=hdf.CodFormulacion_fk AND hfor.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('4')AS Tipo FROM hic_orden hod,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.TipoOrden=4 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT  hod.codigo, CONCAT(hod.fecha, '/', hod.hora) AS Fecha,CONCAT(sdt.nombre, ' ', sdt.apellido) AS Usuario,    CONCAT(sdt.profesion, ' ', sdt.ocupacion) AS profesion,CONCAT('5') AS Tipo FROM hic_orden hod,hic_detalleordenimagenes hdi,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk = su.usu_codigo AND su.dat_codigo_fk = sdt.dat_codigo AND hod.codigo = hdi.CodOrdImg_fk AND hod.TipoOrden = 5 AND hod.CodAdm_fk = "+CodAdmision+" UNION SELECT DISTINCT  ordpat.idOrdenPat, CONCAT(ordpat.fecha, '/', ordpat.hora) AS Fecha,CONCAT(sdt.nombre, ' ', sdt.apellido) AS Usuario,    CONCAT(sdt.profesion, ' ', sdt.ocupacion) AS profesion,CONCAT('6') AS Tipo FROM ordenpat_encabezado ordpat,seg_usuario su,seg_datos_personales sdt WHERE ordpat.codusuario = su.usu_codigo AND su.dat_codigo_fk = sdt.dat_codigo  AND ordpat.admpaciente = "+CodAdmision+" AND ordpat.estado=1 ) AS Consulta ORDER BY Consulta.Fecha DESC ");
        	System.out.println("SELECT * FROM (SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('1')AS Tipo FROM hic_orden hod,hic_detalleordenlaboratorio hdl,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdl.CodOrdLab_fk AND hod.TipoOrden=1 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('2')AS Tipo FROM hic_orden hod,hic_detalleordenimagenes hdi,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdi.CodOrdImg_fk AND hod.TipoOrden=2 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hfor.codigo,CONCAT(hfor.fecha,'/',hfor.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('3')AS Tipo FROM hic_formulacion hfor,hic_detalle_formulacion hdf,seg_usuario su,seg_datos_personales sdt WHERE hfor.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hfor.codigo=hdf.CodFormulacion_fk AND hfor.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('4')AS Tipo FROM hic_orden hod,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.TipoOrden=4 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT  hod.codigo, CONCAT(hod.fecha, '/', hod.hora) AS Fecha,CONCAT(sdt.nombre, ' ', sdt.apellido) AS Usuario,    CONCAT(sdt.profesion, ' ', sdt.ocupacion) AS profesion,CONCAT('5') AS Tipo FROM hic_orden hod,hic_detalleordenimagenes hdi,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk = su.usu_codigo AND su.dat_codigo_fk = sdt.dat_codigo AND hod.codigo = hdi.CodOrdImg_fk AND hod.TipoOrden = 5 AND hod.CodAdm_fk = "+CodAdmision+" UNION SELECT DISTINCT  ordpat.idOrdenPat, CONCAT(ordpat.fecha, '/', ordpat.hora) AS Fecha,CONCAT(sdt.nombre, ' ', sdt.apellido) AS Usuario,    CONCAT(sdt.profesion, ' ', sdt.ocupacion) AS profesion,CONCAT('6') AS Tipo FROM ordenpat_encabezado ordpat,seg_usuario su,seg_datos_personales sdt WHERE ordpat.codusuario = su.usu_codigo AND su.dat_codigo_fk = sdt.dat_codigo  AND ordpat.admpaciente = "+CodAdmision+" AND ordpat.estado=!=0 ) AS Consulta ORDER BY Consulta.Fecha DESC ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ResumenOrdenServicio "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstadoSalidaH(String CodAdm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,DestinoPaciente,EstadoSalida,fin_cons,cau_ext,tip_diag from hic_destinopaciente where codAdm="+CodAdm+" and (DestinoPaciente='SALIDA DE HOSPITALIZACION' or DestinoPaciente='REMISION A OTRA IPS') ");
        	System.out.println("rs12-rs6 select codigo,DestinoPaciente,EstadoSalida,fin_cons,cau_ext,tip_diag from hic_destinopaciente where codAdm="+CodAdm+" and (DestinoPaciente='SALIDA DE HOSPITALIZACION' or DestinoPaciente='REMISION A OTRA IPS') ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerEstadoSalidaH "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDatosComplementarios(String CodigoAdmision){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select hfc.codigo,hfc.finalidad_consulta,hfc.cod_finconsulta,hce.codigo as codigopkcex,hce.causa_externa,hce.cod_causa_ext,hdp.tip_diag from hic_destinopaciente hdp,hic_finalidad_consulta hfc,hic_causa_externa hce where hdp.fin_cons=hfc.codigo and hdp.codAdm="+CodigoAdmision+" and hce.codigo=hdp.cau_ext");
			System.out.println("rs15 select hfc.codigo,hfc.finalidad_consulta,hfc.cod_finconsulta,hce.codigo as codigopkcex,hce.causa_externa,hce.cod_causa_ext,hdp.tip_diag from hic_destinopaciente hdp,hic_finalidad_consulta hfc,hic_causa_externa hce where hdp.fin_cons=hfc.codigo and hdp.codAdm="+CodigoAdmision+" and hce.codigo=hdp.cau_ext");
		}
		catch(Exception ex){
			System.out.print("Erros en MetodoIngresarResultados>>ObtenerDatosComplementarios "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet ObtenerCausaExterna(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,causa_externa,cod_causa_ext from hic_causa_externa ");
        	System.out.println("rs13 select codigo,causa_externa,cod_causa_ext from hic_causa_externa ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerCausaExterna "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFinalidadConsulta(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,finalidad_consulta,cod_finconsulta from hic_finalidad_consulta ");
        	System.out.println("rs14 select codigo,finalidad_consulta,cod_finconsulta from hic_finalidad_consulta ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerCausaExterna "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerServicioAdmision(String CodAdm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select acc.servicio from adm_admisiones aad,adm_censo_cama acc where aad.cen_numero_cama_fk=acc.cen_numero_cama and aad.adm_numero_ingreso="+CodAdm+" ");
        	System.out.println("rsServ select acc.servicio from adm_admisiones aad,adm_censo_cama acc where aad.cen_numero_cama_fk=acc.cen_numero_cama and aad.adm_numero_ingreso="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerServicioAdmision "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDiagIngreso(String CodAdm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,codResultado from hic_diagnosticoingreso hdi where codAdm="+CodAdm+" ");
        	System.out.println("rsDI select codigo,codResultado from hic_diagnosticoingreso hdi where codAdm="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagIngreso "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEstadoSalida(String CodAdm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select DestinoPaciente,EstadoSalida from hic_destinopaciente where codAdm="+CodAdm+"");
        	System.out.println("rs2 select DestinoPaciente,EstadoSalida from hic_destinopaciente where codAdm="+CodAdm+"");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerEstadoSalida "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerDiagosticosdeIngresoHospitalizacion(String CodAdm){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select CONCAT(ci.nombre,'-->',ci.codigoCIE) as Diagnostico from hic_diagnosticoingreso hdi,cie10 ci where codAdm="+CodAdm+" and hdi.CodDiag_fk=ci.codigo");
        	System.out.println("rs-rs1 select CONCAT(ci.nombre,'-->',ci.codigoCIE) as Diagnostico from hic_diagnosticoingreso hdi,cie10 ci where codAdm="+CodAdm+" and hdi.CodDiag_fk=ci.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagosticosdeIngresoHospitalizacion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDiagosticosdeEgreso(String CodAdm,String TipDiag){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select cie.nombre,cie.codigoCIE,hdc.TipDiag from hic_diagnosticoegreso hdc,cie10 cie where hdc.CodDiag_fk=cie.codigo and hdc.codAdm="+CodAdm+" and hdc.TipDiag='"+TipDiag+"'");
        	System.out.println("rs8-rs9-rs10-rs3-rs4-rs1 select cie.nombre,cie.codigoCIE,hdc.TipDiag from hic_diagnosticoegreso hdc,cie10 cie where hdc.CodDiag_fk=cie.codigo and hdc.codAdm="+CodAdm+" and hdc.TipDiag='"+TipDiag+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagosticosdeEgreso "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerDiagnosticosCIE10(String CodAdmision){
		/**
		 * se obtienen los diagnosticos del cie10
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cie.nombre,cie.codigoCIE FROM cie10 cie,hic_diagnosticoingreso hdi WHERE hdi.CodDiag_fk=cie.codigo and hdi.codAdm='"+CodAdmision+"'");
        	System.out.println("rs SELECT cie.nombre,cie.codigoCIE FROM cie10 cie,hic_diagnosticoingreso hdi WHERE hdi.CodDiag_fk=cie.codigo and hdi.codAdm='"+CodAdmision+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoLaboratoriosHistoria>>ObtenerDiagnosticosCIE10 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerFormatosPermitidosUsuario(String CodigoUsuario){
		/**
		 * se obtienen los formatos al cual tiene acceso los usuarios
		 * para asignarselos algun paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT distinct hf.codigo,hf.nombre_formato FROM hic_formato hf,seg_usuariosformato suf,seg_usuario su WHERE suf.codigoFormato_fk=hf.codigo and suf.codigoUsuario_fk=su.usu_codigo and su.usu_codigo='"+CodigoUsuario+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPaciente "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarDiagnosticosActivos(String CodPac){
		/**
		 * se obtienen los formatos al cual tiene acceso los usuarios
		 * para asignarselos algun paciente.CE
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT c.nombre,c.codigoCIE,hi.fecha,hi.CodDiag_fk FROM hic_diagnosticoingreso hi,cie10 c WHERE hi.CodDiag_fk=c.codigo AND hi.codPac="+CodPac+" UNION SELECT c.nombre,c.codigoCIE,he.fecha,he.CodDiag_fk FROM hic_diagnosticoegreso he,cie10 c WHERE he.CodDiag_fk=c.codigo AND he.codPac="+CodPac+" UNION SELECT c.nombre,c.codigoCIE,ad.fecha,ad.codigoCIE_fk FROM adm_diagnosticocola ad,cie10 c WHERE ad.codigoCIE_fk=c.codigo AND ad.cedpac_fk="+CodPac+" ) AS a GROUP BY a.nombre ORDER BY a.nombre");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo CargarDiagnosticosActivos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormatosPermitidosUsuarioCE(String CodigoUsuario){
		/**
		 * se obtienen los formatos al cual tiene acceso los usuarios
		 * para asignarselos algun paciente.CE
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT distinct hf.codigo,hf.nombre_formato FROM hic_formato hf,seg_usuariosformatosce suf,seg_usuario su WHERE suf.codigoFormato_fk=hf.codigo and suf.codigoUsuario_fk=su.usu_codigo and su.usu_codigo='"+CodigoUsuario+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormatosPermitidosUsuarioCE "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarCoFirmaFormato(String CodForPac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select hc.codigo from hic_cofirmahc hc where hc.CodForPac="+CodForPac+" ");
        	//System.out.println("select hc.codigo from hic_cofirmahc hc where hc.CodForPac="+CodForPac+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo BuscarCoFirmaFormato "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerEspecialidadesPaciente(String CodPaciente,String CodAdmision){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sdp.profesion FROM hic_formato hf,adm_admisiones adm,hic_adm_formatos_pac hafp,seg_usuario sus,seg_datos_personales sdp WHERE hf.codigo = hafp.codigo_for_fk AND adm.adm_numero_ingreso = hafp.codigo_adm_fk AND adm.pac_codigo_paciente_fk = hafp.codigo_pac_fk AND hafp.codigo_pac_fk ='"+CodPaciente+"' AND hafp.codigo_adm_fk='"+CodAdmision+"'  AND hafp.codigo_usu_fk = sus.usu_codigo AND sdp.dat_codigo = sus.dat_codigo_fk GROUP BY sdp.profesion ORDER BY sdp.profesion ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerEspecialidadesPaciente "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerFormatosPacienteE(String CodPaciente,String CodAdmision,String Especialidad){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora,sdp.nombre,sdp.apellido,hafp.codigo_usu_fk,hafp.codigo_pac_fk,hafp.codigo,hafp.estado,hf.repetido,sdp.profesion,sdp.ocupacion FROM hic_formato hf,adm_admisiones adm,hic_adm_formatos_pac hafp,seg_usuario sus, seg_datos_personales sdp WHERE hf.codigo = hafp.codigo_for_fk AND adm.adm_numero_ingreso = hafp.codigo_adm_fk AND adm.pac_codigo_paciente_fk = hafp.codigo_pac_fk AND hafp.codigo_pac_fk = '"+CodPaciente+"' AND hafp.codigo_adm_fk='"+CodAdmision+"' AND hafp.codigo_usu_fk = sus.usu_codigo  AND sdp.dat_codigo = sus.dat_codigo_fk  AND sdp.profesion='"+Especialidad+"' ORDER BY hafp.codigo DESC  ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormatosPacienteE "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerFormatosPaciente(String CodPaciente,String CodAdmision){
		/**
		 * se obtienen los datos del paciente para la mostrarlos como infromacion
		 * lleva como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora,sdp.nombre,sdp.apellido,hafp.codigo_usu_fk,hafp.codigo_pac_fk,hafp.codigo,hafp.estado,hf.repetido  from hic_formato hf,adm_admisiones adm,hic_adm_formatos_pac hafp,seg_usuario sus,seg_datos_personales sdp where hf.codigo=hafp.codigo_for_fk and adm.adm_numero_ingreso=hafp.codigo_adm_fk and adm.pac_codigo_paciente_fk =hafp.codigo_pac_fk and hafp.codigo_pac_fk='"+CodPaciente+"' and hafp.codigo_adm_fk='"+CodAdmision+"' and hafp.codigo_usu_fk=sus.usu_codigo and sdp.dat_codigo=sus.dat_codigo_fk order by hafp.codigo desc  ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerFormatosPaciente "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerNombreUsuario(String usuario){
		/**
		 * se obtienen los datos del usuario k pidio el examen.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select sdp.nombre,sdp.apellido from seg_usuario sus,seg_datos_personales sdp where sus.dat_codigo_fk=sdp.dat_codigo and sus.usu_codigo="+usuario+"");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerNombreUsuario "+ex);
        }	
        return rs;
    }
	public void InsertarCofirma(String UsuarioEntero,String CodigoFormatoPaciente){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into hic_cofirmahc(CodUsu,CodForPac)values(?,?)");
			    ps.setString(1,UsuarioEntero);
			    ps.setString(2,CodigoFormatoPaciente);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoLAboratoiosHistoria>>InsertarCofirma "+ex);
			}

		}
	
	public void RelacionPacienteLaboratorios(String CodEstudio,String TipoEstudio,String CodPaciente,Time hora,Date fecha,String usuario,String TipoPyP){
		/**
		 * creamos la relacion de los formatos con la admision y el paciente 
		 * lleva como parametro el codigo del formato,codigo de la admision,codigo del paciente
		 */
		CrearPacLab cpl = new CrearPacLab();
		cpl.setCodigoEstudio(CodEstudio);
		cpl.setCodigoPac(CodPaciente);
		cpl.setTipoEstudio(TipoEstudio);
		//cpl.setHora(hora);
		//cpl.setFecha(fecha);
		cpl.setusuario(usuario);
		cpl.setTipoPyP(TipoPyP);
	 		
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into ord_pac_lab(codigo_pac_fk,codigo_estudio,tipo_estudio,hora,fecha,usuario,tipopyp)values(?,?,?,?,?,?,?)");
			    ps.setString(1,cpl.getCodigoPac());
			    ps.setString(2,cpl.getCodigoEstudio());
			    ps.setString(3,cpl.getTipoEstudio());
			    ps.setTime(4,hora);
			    ps.setDate(5,fecha);
			    ps.setString(6, cpl.getusuario());
			    ps.setString(7, cpl.getTipoPyP());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN METODO:_ RelacionPacienteLaboratorios "+ex);
			}

		}
	
	public java.sql.ResultSet ObtenerExamenesArea(String CodArea){
		/**
		 * se obtienen los datos del paciente para la mostrarlos como infromacion
		 * lleva como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select lsa.codigo,lsa.nombre,replace(lsa.nombre,lsa.nombre,'2') as tipo from lab_area la,lab_subarea lsa where la.codigo=lsa.codarea_fk and la.codigo='"+CodArea+"' union select lex.codigo,lex.nombre,lex.tipo from lab_examen lex,lab_area la where lex.codigoarea_fk=la.codigo and la.codigo='"+CodArea+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerExamenesArea "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerAreaExamenes(){
		/**
		 * se obtienen los datos del paciente para la mostrarlos como infromacion
		 * lleva como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre from lab_area where codigo>0");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerAreaExamenes "+ex);
        }	
        return rs;
    }
/***************************************************************/	
	public java.sql.ResultSet Busedadygene1(String CodPac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select genero,(year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,nombre,primer_apellido,segundo_apellido from adm_paciente where pac_codigo_paciente="+CodPac+"");
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error MetodoExamen>>Busedadygene "+ex);
        }
      
        return rs;
        
    }
	
	 public java.sql.ResultSet Buscacodge1(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_genero.codigo from lab_genero where lab_genero.especificacion='"+nombre+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Buscacodge "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet Bustip(String CodExamen){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct codigo,tipo,nombre from lab_examen where codigo='"+CodExamen+"' group by nombre");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Bustip "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet Examenom(String CodExam,String CodPac,String gene,int edad){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lpac.numero_documento,lpac.nombre,lpac.primer_apellido,DATE_FORMAT(lres.fecha,'%d/%m/%y'),lres.hora,lex.nombre as exa,lres.resultado,lran.valorinicial,lran.valorfinal,lpac.pac_codigo_paciente,lpac.genero,replace(lun.nombre,'9','%')as unidad,lar.nombre as grupo,lsub.nombre as subgrupo,(year(curdate())-year(lpac.fecha_nacimiento)) - (right(curdate(),5) < right(lpac.fecha_nacimiento, 5)) as edad,lpac.fecha_nacimiento as nacimiento from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_unidad lun,lab_subarea lsub,lab_area lar,lab_tipo_rango ltr where lres.estado=1 and lpac.pac_codigo_paciente="+CodPac+" and lar.codigo=lsub.codarea_fk and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.codigosubarea_fk=lsub.codigo and lex.codigo='"+CodExam+"' and( (lran.edadInicial<="+edad+" and  lran.edadFinal>="+edad+") XOR  lran.codgenero_fk=2 xor ltr.tiporango='GENERAL') and lran.codtiporango_fk=ltr.codigo and ltr.codexamen_fk=lex.codigo and lun.codigo=lran.codunidad_fk order by  lres.fecha desc ");
	       	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Examenom "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet ExamenTexto1(String CodPac, String CodExam){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct DATE_FORMAT(lres.fecha,'%d/%m/%y'),lres.hora,lres.resultado from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,lab_area lar where lpac.pac_codigo_paciente='"+CodPac+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=0 and lex.codigo='"+CodExam+"' and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lres.estado=1 and lres.resultado!='' order by lres.fecha,lres.hora desc");
	        	
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>ExamenTexto1 "+ex);
	        }
	      
	        return rs;
	        
	    }
/********************************************************************/	
	public java.sql.ResultSet ExamenesRealizados(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select distinct lex.nombre,lex.codigo,lex.tipo from lab_examen lex,lab_resultado lres,adm_paciente lpac where lpac.pac_codigo_paciente='"+CodPac+"' and lres.codpac_fk=lpac.pac_codigo_paciente and lres.codexamen_fk=lex.codigo and lres.resultado!='' and lres.resultado!='-' group by lex.nombre order by lex.nombre");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoLaboratoriosHistoria>>ExamenesRealizados "+ex);
		}
		return rs;
	}
	
	 public java.sql.ResultSet Busedadygene(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select genero,(year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,nombre,primer_apellido from adm_paciente where pac_codigo_paciente='"+CodPac+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: "+ex);
	        }
	      
	        return rs;
	        
	    }
/////////////////////////////////////////////////////////////////////
	 public java.sql.ResultSet Buscacodge(String nombre){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lab_genero.codigo from lab_genero where lab_genero.especificacion='"+nombre+"'");
	        //	System.out.println("Buscacodge+select lab_genero.codigo from lab_genero where lab_genero.especificacion='"+nombre+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: Buscacodge "+ex);
	        }
	      
	        return rs;
	        
	    }
/////////////////////////////////////////////////////////////////////
	 public java.sql.ResultSet ExamenTexto(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lex.codigo,lpac.pac_codigo_paciente,lres.codigo from adm_paciente lpac,lab_resultado lres,lab_examen lex where lpac.pac_codigo_paciente='"+CodPac+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=0  and lex.codigosubarea_fk=0 and lres.estado=1 order by lres.fecha,lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: "+ex);
	        }
	      
	        return rs;
	        
	    }
	 ///////////////////////////////////////////////////////////////////////
	 public java.sql.ResultSet ExamenRango(String CodPac,String gene){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lran.valorinicial,lran.valorfinal,lex.codigo,lpac.pac_codigo_paciente,lres.codigo from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_tipo_rango ltr where lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=1 and lran.codtiporango_fk=ltr.codigo and ltr.codexamen_fk=lex.codigo and lex.codigosubarea_fk=0 and lex.codigoarea_fk>0 and lpac.pac_codigo_paciente="+CodPac+" AND ( lran.codgenero_fk="+gene+" XOR ltr.tiporango='GENERAL') and lres.estado=1 order by lres.fecha, lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo::ExamenRango "+ex);
	        }
	      
	        return rs;
	        
	    }
	 public java.sql.ResultSet NombreUsuarioCofirma(String CodUsuario){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select CONCAT(sdt.nombre,' ',sdt.apellido) as NomUsu,sdt.cedula as TarPro,CONCAT(sdt.profesion,' ',sdt.ocupacion) as Cargo from seg_usuario su,seg_datos_personales sdt where sdt.dat_codigo=su.dat_codigo_fk and su.usu_codigo="+CodUsuario+"");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_ MetodoLaboratoriosHistoria>>BuscarPreguntasRequeridas "+ex);
	        }	      
	        return rs;	        
	    }
	 /////////////////////////////////////////////////////////////////
	 public java.sql.ResultSet BuscarPreguntasRequeridas(String FechaFormato,String HoraFormato,String CodAdmision){
	        java.sql.ResultSet rs=null;
	        Statement st = null;	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct hres.codigo, hpr.nombre_pregunta,hpr.requerido,hres.CodFormato_fk,hres.codUsu_Fk from hic_resultado hres,hic_pregunta hpr where hres.codPreg_fk=hpr.codigo and hres.resultados='' and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hres.codAdm_fk="+CodAdmision+" and hpr.requerido!='0'");
	        	//System.out.println("select distinct hres.codigo, hpr.nombre_pregunta,hpr.requerido,hres.CodFormato_fk,hres.codUsu_Fk from hic_resultado hres,hic_pregunta hpr where hres.codPreg_fk=hpr.codigo and hres.resultados='' and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hres.codAdm_fk="+CodAdmision+" and hpr.requerido!='0'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_ MetodoLaboratoriosHistoria>>BuscarPreguntasRequeridas "+ex);
	        }	      
	        return rs;	        
	    }
	 /////////////////////////////////////////////////////////////////
	 public java.sql.ResultSet Examen(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre, lex.nombre,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub where lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lpac.pac_codigo_paciente='"+CodPac+"' and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lres.estado=1 and lex.codigosubarea_fk!=0 and lres.resultado!='' group by lres.fecha,lres.hora desc ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: "+ex);
	        }
	      
	        return rs;
	        
	    }   
/////////////////////////////////////////////////////////////////////
	 public java.sql.ResultSet Buscaedad(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select (year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,genero from adm_paciente where pac_codigo_paciente='"+CodPac+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: "+ex);
	        }
	      
	        return rs;
	        
	    }
/////////////////////////////////////////////////////////////////////
	 public void ActualizarFormatoActivo(String CodigoFormatoPaciente){
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update hic_adm_formatos_pac set estado=1 where codigo='"+CodigoFormatoPaciente+"'");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("ERROR EN METODO EstadoDeFormatoActivo:_ "+ex);
		     	ex.getMessage();	     
		     }	
		 } 
	 
	 public void ActualizarPreguntasFormatoActivo(String FechaFormato,String HoraFormato,String CodAdmision){
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("update hic_resultado hres set hres.estado=1 where hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hres.codAdm_fk='"+CodAdmision+"'");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("ERROR EN METODO ActualizarPreguntasFormatoActivo:_ "+ex);
		     	ex.getMessage();	     
		     }	
		 }
	 
/////////////////////////////////////////////////////////////////////
	 public java.sql.ResultSet HistorialDeImagenologia(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ice.fecha,ice.hora,isu.nombre,ice.codigo,sgu.usu_codigo from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt ,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=1 and sdt.dat_codigo=sgu.dat_codigo_fk and sgu.usuario=ice.aprobacion and aen.ent_nit=acv.ent_nit_contratante_fk and ipac.pac_codigo_paciente='"+CodPac+"' and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.fecha asc ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo HistorialDeImagenologia "+ex);
	        }
	        return rs;
	    }
	 
	 public java.sql.ResultSet HistorialDeEco(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fecha_informe, hora_informe, 'ECOCARDIOGRAMA TRANS TOR�CICO' AS nombreExamen, idInformeEcocardio, codusuario  FROM eco_encabezado_informe WHERE codpaciente='"+CodPac+"' AND estado='1' ORDER BY idInformeEcocardio ASC");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo_laboratoriosHistoria HistorialDeEco "+ex);
	        }
	        return rs;
	    }
	 
	 
	 public java.sql.ResultSet HistorialDeRmc(String CodPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT rmc.fecha_informe, rmc.hora_informe, rmcind.nombre_indicacion, rmc.idInformeEcocardio, rmc.codusuario  FROM rmc_encabezado_informe rmc, rmc_indicaciones rmcind WHERE rmc.codpaciente='"+CodPac+"' AND rmc.estado='1' AND rmcind.id_indicacion=rmc.id_indicacion_fk ORDER BY rmc.idInformeEcocardio ASC");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo_laboratoriosHistoria HistorialDeRmc "+ex);
	        }
	        return rs;
	    }
	
}