/**
 * logica: MetodoIngresarResultados se encuentran las inserciones,consultas y actualizaciones
 * para el ingreso de los resultados de los formatos y obtencion de la informacion de los que 
 * ya estan llenos.
*/
package hic_metodo;


import hic_bean.IngresarResultados;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;


public class MetodoIngresarResultados {
	

	public java.sql.ResultSet ObtenerResultadoTr(String CodResul){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("select codigo from hic_diagnosticoingreso where codAdm="+CodAdm+" ");
        	rs=st.executeQuery("select resultados from adm_colaresultados where codigo="+CodResul+" ");
        	System.out.println("select resultados from adm_colaresultados where codigo="+CodResul+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerResultado(String CodResul){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("select codigo from hic_diagnosticoingreso where codAdm="+CodAdm+" ");
        	rs=st.executeQuery("select resultados from hic_resultado where codigo="+CodResul+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultado "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerCodigoDiagnostico(String CodDiagnostico){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM cie10 WHERE codigoCIE='"+CodDiagnostico+"' ORDER BY codigo DESC LIMIT 1 ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerCodigoDiagnostico "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet ObtenerDiagIngreso(String CodAdm){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select CONCAT(ci.nombre,'-',ci.codigoCIE) as Diagnostico from cie10 ci,hic_diagnosticoingreso hdi where ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagIngreso "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDiagEgreso(String CodAdm,String TipoDiagEG){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,TipDiag from hic_diagnosticoegreso where codAdm="+CodAdm+" and TipDiag='"+TipoDiagEG+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagEgreso "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerDiagRE1(String CodAdm,String TipoDiagRel1){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,TipDiag from hic_diagnosticoegreso where codAdm="+CodAdm+" and  TipDiag='"+TipoDiagRel1+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagRE1 "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerDiagRE2(String CodAdm,String TipoDiagRel2){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,TipDiag from hic_diagnosticoegreso where codAdm="+CodAdm+" and TipDiag='"+TipoDiagRel2+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagRE2 "+ex);
        }	
        return rs;
    }
	
	
	public void IngresarDiagnosticosEgreso(String CodDiagnostico,String TipoDiag,String usuario,String hora,String fecha,String CodAdm,String CodPac,String CodDiag_fk){
	
		IngresarResultados ir = new IngresarResultados();
		ir.setCodDiagnostico(CodDiagnostico);
		ir.setTipoDiag(TipoDiag);
		ir.setcodUsu_fk(usuario);
		ir.setHora(hora);
		ir.setFecha(fecha);
		ir.setcodAdm_fk(CodAdm);
		ir.setcodPa_fk(CodPac);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_diagnosticoegreso(codDiagnostico,TipDiag,codUsu,hora,fecha,codAdm,codPac,CodDiag_fk)values(?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getCodDiagnostico());
				    ps.setString(2,ir.getTipoDiag());
				    ps.setString(3,ir.getcodUsu_fk());
				    ps.setString(4,ir.getHora());
				    ps.setString(5,ir.getFecha());
				    ps.setString(6,ir.getcodAdm_fk());
				    ps.setString(7,ir.getcodPa_fk());
				    ps.setString(8, CodDiag_fk);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en MetodoIngresarResultados>>IngresarDiagnosticosEgreso "+ex);
				}
			}
	
	public void IngresarEstadoSalidaDestino(String DestinoPaciente,String EstadoSalida,String usuario,String hora,String fecha,String CodAdm,String CodPac,String tip_diag,String cau_ext,String fin_cons){
		/**
		 * se ingresa el diagnostico de egreso del paciente.
		 */
		IngresarResultados ir = new IngresarResultados();
		ir.setDestinoPaciente(DestinoPaciente);
		ir.setEstadoSalida(EstadoSalida);
		ir.setcodUsu_fk(usuario);
		ir.setHora(hora);
		ir.setFecha(fecha);
		ir.setcodAdm_fk(CodAdm);
		ir.setcodPa_fk(CodPac);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_destinopaciente(DestinoPaciente,EstadoSalida,codUsu,hora,fecha,codAdm,codPac,tip_diag,cau_ext,fin_cons)values(?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getDestinoPaciente());
				    ps.setString(2,ir.getEstadoSalida());
				    ps.setString(3,ir.getcodUsu_fk());
				    ps.setString(4,ir.getHora());
				    ps.setString(5,ir.getFecha());
				    ps.setString(6,ir.getcodAdm_fk());
				    ps.setString(7,ir.getcodPa_fk());
				    ps.setString(8, tip_diag);
				    ps.setString(9, cau_ext);
				    ps.setString(10, fin_cons);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en MetodoIngresarResultados>>IngresarEstadoSalidaDestino "+ex);
				}
			}
	
	
	public java.sql.ResultSet ObtenerDiagnosticoIngreso(String codAdm,String CodResul){	
		/**
		 * esta consulta trae consigo si el diagnostico de ingreso al paciente ya fue realizado o no.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from hic_diagnosticoingreso where codAdm='"+codAdm+"' and codResultado='"+CodResul+"'");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagnosticoIngreso "+ex);
        }	
        return rs;
    }
	
	public void IngresarDiagnosticoIngreso(String CodResul,String CodDiagnostico,String usuario,String hora,String fecha,String CodAdm,String CodPac,String CodDiag_fk){
		
		IngresarResultados ir = new IngresarResultados();
		ir.setCodResul(CodResul);
		ir.setCodDiagnostico(CodDiagnostico);
		ir.setcodUsu_fk(usuario);
		ir.setHora(hora);
		ir.setFecha(fecha);
		ir.setcodAdm_fk(CodAdm);
		ir.setcodPa_fk(CodPac);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_diagnosticoingreso(codResultado,codDiagnostico,codUsu,hora,fecha,codAdm,codPac,CodDiag_fk)values(?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getCodResul());
				    ps.setString(2,ir.getCodDiagnostico());
				    ps.setString(3,ir.getcodUsu_fk());
				    ps.setString(4,ir.getHora());
				    ps.setString(5,ir.getFecha());
				    ps.setString(6,ir.getcodAdm_fk());
				    ps.setString(7,ir.getcodPa_fk());
				    ps.setString(8, CodDiag_fk);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en MetodoIngresarResultados>>IngresarDiagnosticoIngreso "+ex);
				}
			}
	

	public void IngresarHistoria(String codPa_fk,String codAdm_fk,String codPreg_fk,String resultados,String estado,String fecha,String hora,String codUsu_fk,String codArea_Fk,String CodFormato){
		/**
		 * se ingresa a la historia clinica todos los rresultados de los diferentes
		 * formatos de la historia clinica....
		 */
		IngresarResultados ir = new IngresarResultados();
		ir.setcodPa_fk(codPa_fk);
		ir.setcodAdm_fk(codAdm_fk);
		ir.setcodPreg_fk(codPreg_fk);
		ir.setresultados(resultados);
		ir.setestado(estado);
		//ir.setFecha(fecha);
		//ir.setHora(hora);
		ir.setcodUsu_fk(codUsu_fk);
		ir.setcodArea_Fk(codArea_Fk);
		ir.setCodFormato(CodFormato);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_resultado(codPac_fk,codAdm_fk,codPreg_fk,resultados,estado,fecha,hora,codUsu_Fk,codArea_Fk,CodFormato_fk)values(?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getcodPa_fk());
				    ps.setString(2,ir.getcodAdm_fk());
				    ps.setString(3,ir.getcodPreg_fk());
				    ps.setString(4,ir.getresultados());
				    ps.setString(5,ir.getestado());
				    ps.setString(6,fecha);
				    ps.setString(7,hora);
				    ps.setString(8, ir.getcodUsu_fk());
				    ps.setString(9, ir.getcodArea_Fk());
				    ps.setString(10, ir.getCodFormato());
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en MetodoIngresarResultados>>IngresarHistoria "+ex);
				}
			}
	
	
	
	
	public java.sql.ResultSet ObtenerResultadosFormatos1(String CodAdmisi,String CodPacien,String CodigoArea,String FechaFormato,String HoraFormato){	
		/**
		 * esta consulta trae consigo todo los resultados ya digitados en
		 * el campo de las preguntas con el tipo de estudio=1...
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hres.codigo,hres.resultados,hres.codPac_fk,hres.codAdm_fk,hres.codPreg_fk,hres.codArea_Fk,hpre.nombre_pregunta  from hic_resultado hres,adm_admisiones aadm,adm_paciente apac,hic_pregunta hpre,hic_area har,hic_pregunta_tiporespuesta hptr,hic_tipo_respuesta htr where hres.codPac_fk=apac.pac_codigo_paciente and hres.codAdm_fk=aadm.adm_numero_ingreso and hres.codPreg_fk=hpre.codigo and hres.codArea_Fk=har.codigo and hres.codAdm_fk='"+CodAdmisi+"' and hres.codPac_fk='"+CodPacien+"' and hres.codArea_fk='"+CodigoArea+"' and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hpre.codigo=hptr.codigo_pregunta_fk and hptr.codigo_tiporespuesta_fk=htr.codigo and htr.tipo=1");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultadosFormatos "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerAntesIngre1(String CodAdmisi,String CodPacien,String CodigoArea,String FechaFormato,String HoraFormato,String CodPregunta){	
		/**
		 * esta consulta trae consigo todo los resultados ya digitados en
		 * el campo de las preguntas con el tipo de estudio=2...
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hres.codigo,hres.resultados,hres.codPac_fk,hres.codAdm_fk,hres.codPreg_fk,hres.codArea_Fk from hic_resultado hres,adm_admisiones aadm,adm_paciente apac,hic_pregunta hpre,hic_area har,hic_pregunta_tiporespuesta hptr,hic_tipo_respuesta htr where hres.codPac_fk=apac.pac_codigo_paciente and hres.codAdm_fk=aadm.adm_numero_ingreso and hres.codPreg_fk=hpre.codigo and hres.codArea_Fk=har.codigo and hres.codAdm_fk='"+CodAdmisi+"' and hres.codPac_fk='"+CodPacien+"' and hres.codArea_fk='"+CodigoArea+"' and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hres.codPreg_fk='"+CodPregunta+"' and hpre.codigo=hptr.codigo_pregunta_fk and hptr.codigo_tiporespuesta_fk=htr.codigo and htr.tipo=1");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultadosFormatos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerResultadosFormatos2(String CodAdmisi,String CodPacien,String CodigoArea,String FechaFormato,String HoraFormato){	
		/**
		 * esta consulta trae consigo todo los resultados ya digitados en
		 * el campo de las preguntas con el tipo de estudio=2...
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hres.codigo,hres.resultados,hres.codPac_fk,hres.codAdm_fk,hres.codPreg_fk,hres.codArea_Fk,hpre.nombre_pregunta from hic_resultado hres,adm_admisiones aadm,adm_paciente apac,hic_pregunta hpre,hic_area har,hic_pregunta_tiporespuesta hptr,hic_tipo_respuesta htr where hres.codPac_fk=apac.pac_codigo_paciente and hres.codAdm_fk=aadm.adm_numero_ingreso and hres.codPreg_fk=hpre.codigo and hres.codArea_Fk=har.codigo and hres.codAdm_fk='"+CodAdmisi+"' and hres.codPac_fk='"+CodPacien+"' and hres.codArea_fk='"+CodigoArea+"' and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hpre.codigo=hptr.codigo_pregunta_fk and hptr.codigo_tiporespuesta_fk=htr.codigo and htr.tipo=2");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultadosFormatos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerResultadosTodo(String CodArea){	
		/**
		 * esta consulta trae consigo todo los resultados ya digitados en
		 * el campo de las preguntas con el tipo de estudio=2...
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hp.codigo as CodPregunta,ha.codigo as CodArea from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and ha.codigo='"+CodArea+"' and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultadosFormatos "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerAntesIngre2(String CodAdmisi,String CodPacien,String CodigoArea,String FechaFormato,String HoraFormato,String CodPregunta){	
		/**
		 * esta consulta trae consigo todo los resultados ya digitados en
		 * el campo de las preguntas con el tipo de estudio=2...
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hres.codigo,hres.resultados,hres.codPac_fk,hres.codAdm_fk,hres.codPreg_fk,hres.codArea_Fk from hic_resultado hres,adm_admisiones aadm,adm_paciente apac,hic_pregunta hpre,hic_area har,hic_pregunta_tiporespuesta hptr,hic_tipo_respuesta htr where hres.codPac_fk=apac.pac_codigo_paciente and hres.codAdm_fk=aadm.adm_numero_ingreso and hres.codPreg_fk=hpre.codigo and hres.codArea_Fk=har.codigo and hres.codAdm_fk='"+CodAdmisi+"' and hres.codPac_fk='"+CodPacien+"' and hres.codArea_fk='"+CodigoArea+"' and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and hres.codPreg_fk='"+CodPregunta+"' and hpre.codigo=hptr.codigo_pregunta_fk and hptr.codigo_tiporespuesta_fk=htr.codigo and htr.tipo=2");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultadosFormatos "+ex);
        }	
        return rs;
    }
	
	public void ActualizarEncabezadoFechaEgreso(String Fecha, String CodAdm){		
	    
	     try{
	    	 PreparedStatement st = null;
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update fact_enc_factura set fecha_egreso='"+Fecha+"' where cod_admision="+CodAdm+"");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoIngresarResultados>>ActualizarEncabezadoFechaEgreso "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void ActualizarDiagnosticoIngreso(String CodDiagnostico, String CodAdm, String CodResul){		
	    
	     try{
	    	 PreparedStatement st = null;
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update hic_diagnosticoingreso set codDiagnostico='"+CodDiagnostico+"',codResultado='"+CodResul+"' where codAdm='"+CodAdm+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoIngresarResultados>>ActualizarDiagnosticoIngreso "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	public void ActualizarResultadosTr(String Resul, String CodResul){		
		
	    PreparedStatement st = null;
	    IngresarResultados ir = new IngresarResultados();
	    ir.setresultados(Resul);
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE adm_colaresultados SET resultados='"+Resul+"' WHERE codigo="+CodResul+" ");
	     	//System.out.println("UPDATE adm_colaresultados SET resultados=? WHERE codigo=? ");
	     	//st.setString(1,ir.getresultados());
	     	//st.setString(2, CodResul);
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoIngresarResultados>>ActualizarResultados "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void ActualizarResultados(String Resul, String CodResul){		
	    PreparedStatement st = null;
	    IngresarResultados ir = new IngresarResultados();
	    ir.setresultados(Resul);
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update hic_resultado set resultados=? where codigo=? ");
	     	st.setString(1,ir.getresultados());
	     	st.setString(2, CodResul);
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoIngresarResultados>>ActualizarResultados "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public java.sql.ResultSet ObtenerResultados(String codAdm_fk,String codPa_fk,String codArea_Fk,String hora,String fecha){	
		/**
		 * esta consulta trae consigo todo los resultados ya digitados en
		 * el campo de las preguntas...
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hres.codigo,hres.resultados,hres.codPac_fk,hres.codAdm_fk,hres.codPreg_fk,hres.codArea_Fk from hic_resultado hres,adm_admisiones aadm,adm_paciente apac,hic_pregunta hpre,hic_area har,hic_pregunta_tiporespuesta hptr,hic_tipo_respuesta htr where hres.codPac_fk=apac.pac_codigo_paciente and hres.codAdm_fk=aadm.adm_numero_ingreso and hres.codPreg_fk=hpre.codigo and hres.codArea_Fk=har.codigo and hres.codAdm_fk='"+codAdm_fk+"' and hres.codPac_fk='"+codPa_fk+"' and hres.codArea_fk='"+codArea_Fk+"' and hres.hora='"+hora+"' and hres.fecha='"+fecha+"' and hpre.codigo=hptr.codigo_pregunta_fk and hptr.codigo_tiporespuesta_fk=htr.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerResultados "+ex);
        }	
        return rs;
       
    }
}