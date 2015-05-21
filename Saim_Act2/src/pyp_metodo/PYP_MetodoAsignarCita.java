package pyp_metodo;

import hic_bean.CrearFormatoHic;
import hic_bean.IngresarResultados;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import pyp_bean.PYP_AsignarCita;

import adm_logica.Conexion;
//import pyp_bean.PYP_AsignarCita;

public class PYP_MetodoAsignarCita {
	
	public void IngresarEstadoSalidaDestino(String DestinoPaciente,String EstadoSalida,String usuario,String hora,String fecha,String CodAdm,String CodPac,String tip_diag,String cau_ext,String fin_cons){
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_destinopaciente(DestinoPaciente,EstadoSalida,codUsu,hora,fecha,codAdm,codPac,tip_diag,cau_ext,fin_cons)values(?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,DestinoPaciente);
				    ps.setString(2,EstadoSalida);
				    ps.setString(3,usuario);
				    ps.setString(4,hora);
				    ps.setString(5,fecha);
				    ps.setString(6,CodAdm);
				    ps.setString(7,CodPac);
				    ps.setString(8, tip_diag);
				    ps.setString(9, cau_ext);
				    ps.setString(10, fin_cons);
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en PYP_MetodoAsignarCita>>IngresarEstadoSalidaDestino "+ex);
				}
			}
	
	public void InsertarReciboEgreso(String concepto,String cantidad,String observacion,
			Date fecha,Time hora,String codusu,String FechaLetra,String TipoPagoEg,
			String ValorLetra,String Nombre,String Identificacion,String ValCheque,String NumCheque){
	    PreparedStatement psc = null;
	     try{
	    	 Conexion con=new Conexion();
	    	 psc=con.conn.prepareStatement("insert into pyp_egresos" +
	    	 		"(concepto,cantidad,observacion,fecha,hora,codusu," +
	    	 		"fechaletras,formapago,valorletras,nombre,identificacion,cantidadcheque,numero_cheque) " +
	    	 		"values(?,?,?,?,?,?,?,?,?,?,?,?,?)");	
  			psc.setString(1,concepto);
  			psc.setString(2,cantidad);
  			psc.setString(3,observacion);
  			psc.setDate(4,fecha);
  			psc.setTime(5,hora);
  			psc.setString(6,codusu);
  			psc.setString(7,FechaLetra );
  			psc.setString(8,TipoPagoEg );
  			psc.setString(9,ValorLetra );
  			psc.setString(10,Nombre );
  			psc.setString(11,Identificacion );
  			psc.setString(12,ValCheque);
  			psc.setString(13,NumCheque);
  			psc.executeUpdate();
 			psc.close();
 			con.cerrar();
 			
	        }
	        catch(Exception ex){
	        	System.out.println("Error en PYP_MetodoAsignarCita>>InsertarReciboEgreso "+ex);
	        }	
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
	public void IngresarDiagnosticoIngreso(String CodResul,String CodDiagnostico,String usuario,Time horaDX,Date fechaDX,String CodAdm,String CodPac,String CodDiag_fk){
		
		IngresarResultados ir = new IngresarResultados();
		ir.setCodResul(CodResul);
		ir.setCodDiagnostico(CodDiagnostico);
		ir.setcodUsu_fk(usuario);
		//ir.setHora(hora);
		//ir.setFecha(fecha);
		ir.setcodAdm_fk(CodAdm);
		ir.setcodPa_fk(CodPac);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_diagnosticoingreso(codResultado,codDiagnostico,codUsu,hora,fecha,codAdm,codPac,CodDiag_fk)values(?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getCodResul());
				    ps.setString(2,ir.getCodDiagnostico());
				    ps.setString(3,ir.getcodUsu_fk());
				    ps.setTime(4,horaDX);
				    ps.setDate(5,fechaDX);
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
	
	public void IngresarDiagnosticosRelaEgreso(String CodDiagnostico,String TipoDiag,String usuario,Time horaDX,Date fechaDX,String CodAdm,String CodPac,String CodDiag_fk){
		
		IngresarResultados ir = new IngresarResultados();
		ir.setCodDiagnostico(CodDiagnostico);
		ir.setTipoDiag(TipoDiag);
		ir.setcodUsu_fk(usuario);
		ir.setcodAdm_fk(CodAdm);
		ir.setcodPa_fk(CodPac);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into hic_diagnosticoegreso(codDiagnostico,TipDiag,codUsu,hora,fecha,codAdm,codPac,CodDiag_fk)values(?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getCodDiagnostico());
				    ps.setString(2,ir.getTipoDiag());
				    ps.setString(3,ir.getcodUsu_fk());
				    ps.setTime(4,horaDX);
				    ps.setDate(5,fechaDX);
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
	
	
	public java.sql.ResultSet ObtenerDiagEgresoRelac(String CodAdm,String TipDiag){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select CONCAT(ci.nombre,'-',ci.codigoCIE) as Diagnostico,hdi.codigo from cie10 ci,hic_diagnosticoegreso hdi where ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+CodAdm+" and hdi.TipDiag='"+TipDiag+"' ");
        	System.out.println("select CONCAT(ci.nombre,'-',ci.codigoCIE) as Diagnostico,hdi.codigo from cie10 ci,hic_diagnosticoegreso hdi where ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+CodAdm+" and hdi.TipDiag='"+TipDiag+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagEgresoRelac "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDiagIngreso(String CodAdm){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select CONCAT(ci.nombre,'-',ci.codigoCIE) as Diagnostico,hdi.codigo from cie10 ci,hic_diagnosticoingreso hdi where ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+CodAdm+" ");
        	System.out.println("select CONCAT(ci.nombre,'-',ci.codigoCIE) as Diagnostico,hdi.codigo from cie10 ci,hic_diagnosticoingreso hdi where ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+CodAdm+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoIngresarResultados>>ObtenerDiagIngreso "+ex);
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
	
public java.sql.ResultSet ObtenerDatosEncabezadoCE(String CodHorarioMedico){	
	

		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aent.ent_nit,aent.nombre_entidad,aent.ent_nit_contratante,"+
			"aent.direccion,aent.telefono,aent.ciudad,"+
			"CONCAT(apac.nombre,' ',apac.primer_apellido,' ',apac.segundo_apellido) AS NombrePAciente,"+
			"CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Identificacion,apac.direccion AS DirPac,"+
			"CONCAT(apac.telefono_celular,' ',apac.telefono_fijo,' ',apac.telefono_oficina ) AS TelPac,"+
			"apac.tipo_afiliacion, apac.estrato,pcp.fecha,'',fcv.poliza,acv.conv_numero_contrato   "+
			"FROM adm_paciente apac,pyp_citaspacientes pcp,adm_entidad aent,adm_convenio acv,fact_convenios fcv,pyp_horariomedico phm  "+
			"WHERE apac.pac_codigo_paciente=pcp.CodPac_fk AND fcv.cod_entidad=aent.ent_nit  "+
			" AND phm.codigo=pcp.CodHorMedico_fk "+
			" AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato "+
			" AND acv.ent_nit_contratante_fk=aent.ent_nit AND phm.codigo="+CodHorarioMedico+" ");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ObtenerDatosEncabezadoCE "+ex);
        }	
        return rs;
    }
	
	
	
	public void RelacionFormatoPacienteCE(String CodFormato,String CodPaciente,String Hora,String Fecha,String CodUsuario,String CodHorMedico){
		/**
		 * creamos la relacion de los formatos con el paciente 
		 * lleva como parametro el codigo del formato,codigo del paciente
		 */
	   CrearFormatoHic cfa = new CrearFormatoHic();
	   cfa.setcodigo_pac_fk(CodPaciente);
	   cfa.setcodigo_formato_fk(CodFormato);
	   cfa.setCodUsuario(CodUsuario);
	   cfa.setHora(Hora);
	   cfa.setFecha(Fecha);
	   String Estado="0";
	   cfa.setCodHorMedico(CodHorMedico);
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_formatos_pac(cod_pac_fk,cod_for_fk,fecha,hora,cod_usu_fk,estado,CodHorMedico_fk)VALUES(?,?,?,?,?,?,?)");
			    ps.setString(1, cfa.getcodigo_pac_fk());
			    ps.setString(2, cfa.getcodigo_formato_fk());
			    ps.setString(3, cfa.getFecha());
			    ps.setString(4, cfa.getHora());
			    ps.setString(5, cfa.getCodUsuario());
			    ps.setString(6, Estado);
			    ps.setString(7, cfa.getCodHorMedico());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>RelacionFormatoPacienteCE "+ex);
			}

		}
	
	
	public java.sql.ResultSet ObtenerReciboIngreso(String NumRecibo){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_detalle_recibo_caja WHERE codigo="+NumRecibo+"");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo PYP_MetodoAsignarCita>>ObtenerReciboIngreso "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerReciboEgreso(String NumRecibo){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_egresos WHERE codigo="+NumRecibo+"");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo PYP_MetodoAsignarCita>>ObtenerReciboEgreso "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerTipoDocumento(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo PYP_MetodoAsignarCita>>ObtenerTipoDocumento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerEgresos(){
		/**
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_egresos");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo PYP_MetodoAsignarCita>>ObtenerEgresos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormatosPacienteCE(String CodigoPaciente,String CodHorarioMedico){
		/**
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hf.codigo,hf.nombre_formato,pfp.fecha,pfp.hora,sdp.nombre,sdp.apellido,pfp.cod_usu_fk,pfp.cod_pac_fk,pfp.codigo,pfp.estado,hf.repetido FROM hic_formato hf,pyp_horariomedico phm,pyp_formatos_pac pfp,seg_usuario sus,seg_datos_personales sdp,pyp_citaspacientes pcp WHERE hf.codigo=pfp.cod_for_fk AND phm.codigo=pfp.CodHorMedico_fk AND pcp.CodPac_fk =pfp.cod_pac_fk AND pcp.CodHorMedico_fk=phm.codigo AND pcp.CodHorMedico_fk=pfp.CodHorMedico_fk AND pfp.CodHorMedico_fk="+CodHorarioMedico+" AND pfp.cod_pac_fk="+CodigoPaciente+" AND pfp.cod_usu_fk=sus.usu_codigo AND sdp.dat_codigo=sus.dat_codigo_fk ORDER BY pfp.fecha DESC ");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo PYP_MetodoAsignarCita>>ObtenerFormatosPacienteCE "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerUsuarioFormatoCE(String CodArea,String fecha,String hora,String CodUsuario,String CodHorarioMedico){	   
	    java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT pres.codUsu_Fk,pres.estado FROM hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,pyp_resultadoce pres WHERE ha.codigo=hap.codigo_area_fk AND hp.codigo=hap.codigo_pregunta_fk AND hp.codigo=hpt.codigo_pregunta_fk AND htr.codigo=hpt.codigo_tiporespuesta_fk AND hfor.codigo=hfa.codigo_formato_fk AND hfa.codigo_area_fk=ha.codigo AND pres.codArea_Fk=ha.codigo AND pres.codPreg_fk=hp.codigo AND pres.fecha='"+fecha+"' AND pres.hora='"+hora+"' AND ha.codigo='"+CodArea+"' AND pres.codUsu_Fk='"+CodUsuario+"' AND pres.CodHorMedico_fk='"+CodHorarioMedico+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ObtenerUsuarioFormatoCE "+ex);
        }	
        return rs;
    }
	
	public void ActualizarResultadosCE(String Resul, String CodResul){		
	    PreparedStatement st = null;
	    IngresarResultados ir = new IngresarResultados();
	    ir.setresultados(Resul);
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_resultadoce SET resultado=? WHERE codigo=? ");
	     	st.setString(1,ir.getresultados());
	     	st.setString(2, CodResul);
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoAsignarCita>>ActualizarResultadosCE "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
public java.sql.ResultSet ObtenerDiagnosInicialCE(String CodResul){	   
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select resultado from pyp_resultadoce where codigo="+CodResul+"");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ObtenerDiagnosInicialCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerHoraFechaFormatoCE(String CodForPacCE){	   
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fecha,hora FROM pyp_formatos_pac WHERE codigo="+CodForPacCE+" ");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ObtenerHoraFechaFormatoCE "+ex);
        }	
        return rs;
    }
	
	public void FinalizarAtencionCE(String CodHorarioMedico){		
	    PreparedStatement st = null;	  
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_horariomedico SET estado=3 WHERE codigo="+CodHorarioMedico+" ");
	      	st.executeUpdate();
	     	st.close();
	     	con.cerrar();	     	
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoAsignarCita>>FinalizarAtencionCE "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void OmitirDxIngre(String codigo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM hic_diagnosticoingreso WHERE codigo="+codigo+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoAsignarCita>>OmitirDxIngre "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirDxEgre(String codigo){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM hic_diagnosticoegreso WHERE codigo="+codigo+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoAsignarCita>>OmitirDxEgre "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirFormatoPacienteCE(String CodForPacCE){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM pyp_formatos_pac WHERE codigo="+CodForPacCE+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoAsignarCita>>OmitirFormatoPacienteCE "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirRegistrosFormatoCE(String Fecha,String Hora){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM pyp_resultadoce WHERE fecha='"+Fecha+"' AND hora='"+Hora+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en PYP_MetodoAsignarCita>>OmitirRegistrosFormatoCE "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet BuscarPreguntasRequeridasCE(String fecha,String hora){
        java.sql.ResultSet rs=null;
        Statement st = null;	      
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT pres.codigo, hpr.nombre_pregunta,hpr.requerido FROM pyp_resultadoce pres,hic_pregunta hpr WHERE pres.codPreg_fk=hpr.codigo AND pres.resultado='' AND pres.fecha='"+fecha+"' AND pres.hora='"+hora+"' AND hpr.requerido!='0' ");
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error_ PYP_MetodoAsignarCita>>BuscarPreguntasRequeridasCE "+ex);
        }	      
        return rs;	        
    }
	
	 public void ActualizarFormatoActivoCE(String CodForPacCE){
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("UPDATE pyp_formatos_pac SET estado=1 WHERE codigo='"+CodForPacCE+"'");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error_ PYP_MetodoAsignarCita>>ActualizarFormatoActivoCE "+ex);
		     	ex.getMessage();	     
		     }	
		 }
	 
	 public void ActualizarPreguntasFormatoActivoCE(String fecha,String hora){
		    PreparedStatement st = null;
		     try{
		     	Conexion con=new Conexion();
		     	st= con.conn.prepareStatement("UPDATE pyp_resultadoce pres SET pres.estado=1 WHERE pres.fecha='"+fecha+"' AND pres.hora='"+hora+"'");
		     	st.executeUpdate();	 
		     	st.close();
		     	con.cerrar();
		     }
		     catch(Exception ex){
		     	System.out.println("Error_ PYP_MetodoAsignarCita>>ActualizarPreguntasFormatoActivoCE "+ex);
		     	ex.getMessage();	     
		     }	
		 }
	
	public java.sql.ResultSet ObtenerPreguntasAreaLlenoCE(String CodArea,String fecha,String hora,String CodHorarioMedico){	   
		/**
		 * se obtienen las preguntas del area tiene como parametro el codigo del area
		 * y devuelve como respuesta el nombre de la pregunta, el tipo de respuesta de la pregunta,y el codigo del tipo de respuesta
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT hp.nombre_pregunta,htr.tipo AS TipoRespuesta,htr.codigo AS CodTipoRes,pres.codigo AS CodigoResultado,pres.resultado,hp.unidad FROM hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,pyp_resultadoce pres WHERE ha.codigo=hap.codigo_area_fk AND hp.codigo=hap.codigo_pregunta_fk AND hp.codigo=hpt.codigo_pregunta_fk AND htr.codigo=hpt.codigo_tiporespuesta_fk AND hfor.codigo=hfa.codigo_formato_fk AND hfa.codigo_area_fk=ha.codigo AND pres.codArea_Fk=ha.codigo AND pres.codPreg_fk=hp.codigo AND pres.fecha='"+fecha+"' AND pres.hora='"+hora+"' AND ha.codigo='"+CodArea+"' AND pres.CodHorMedico_fk='"+CodHorarioMedico+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo PYP_MetodoAsignarCita>>ObtenerPreguntasAreaLlenoCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerAreaFormatoCE(String CodFormato,String fecha,String hora,String CodHorarioMedico){
		/**
		 * se obtienen las areas del formato seleccionado, tiene como parametro el
		 * codigo del formato y da como respuesta el codigo del area, el nombre del area
		 * el nombre del formato.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ha.codigo,ha.nombre_area,pfp.hora,pfp.fecha,pfp.cod_usu_fk,pfp.cod_pac_fk FROM hic_formato hf,hic_formato_area hfa,hic_area ha,pyp_formatos_pac pfp WHERE hf.codigo=hfa.codigo_formato_fk AND ha.codigo=hfa.codigo_area_fk AND hf.codigo=pfp.cod_for_fk AND hf.codigo='"+CodFormato+"' AND pfp.fecha='"+fecha+"' AND pfp.hora='"+hora+"' AND pfp.CodHorMedico_fk='"+CodHorarioMedico+"'" );
        	
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ObtenerAreaFormatoCE "+ex);
        }	
        return rs;
    }
	
	public void IngresarHistoriaCE(String codPa_fk,String codPreg_fk,String resultados,String estado,String fecha,String hora,String codUsu_fk,String codArea_Fk,String CodFormato,String CodHorarioMedico){
		/**
		 * se ingresa a la historia clinica todos los rresultados de los diferentes
		 * formatos de la historia clinica....
		 */
		IngresarResultados ir = new IngresarResultados();
		ir.setcodPa_fk(codPa_fk);
		ir.setcodPreg_fk(codPreg_fk);
		ir.setresultados(resultados);
		ir.setestado(estado);
		ir.setFecha(fecha);
		ir.setHora(hora);
		ir.setcodUsu_fk(codUsu_fk);
		ir.setcodArea_Fk(codArea_Fk);
		ir.setCodFormato(CodFormato);
		ir.setcodAdm_fk(CodHorarioMedico);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("INSERT INTO pyp_resultadoce(codPac_fk,codPreg_fk,resultado,estado,fecha,hora,codUsu_fk,codArea_fk,codFormato_fk,CodHorMedico_fk)VALUES(?,?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,ir.getcodPa_fk());
				    ps.setString(2,ir.getcodPreg_fk());
				    ps.setString(3,ir.getresultados());
				    ps.setString(4,ir.getestado());
				    ps.setString(5,ir.getFecha());
				    ps.setString(6,ir.getHora());
				    ps.setString(7, ir.getcodUsu_fk());
				    ps.setString(8, ir.getcodArea_Fk());
				    ps.setString(9, ir.getCodFormato());
				    ps.setString(10, ir.getcodAdm_fk());
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en PYP_MetodoAsignarCita>>IngresarHistoriaCE "+ex);
				}
			}
	
	
	public void IngresarReciboCaja(String CodPac_fk,String valor_total,Date fecha,Time hora,String CodUsu_fk){
			
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_recibo_caja(CodPac_fk,valor_total,fecha,hora,CodUsu_fk,estado)VALUES(?,?,?,?,?,?) ");
			    ps.setString(1,CodPac_fk);
			    ps.setString(2,valor_total);
			    ps.setDate(3,fecha);
			    ps.setTime(4,hora);
			    ps.setString(5,CodUsu_fk);
			    ps.setString(6, "1");
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>IngresarReciboCaja "+ex);
			}
		}
	
	public void IngresarDetalleReciboCaja(String codReCaja_fk,String abono,Date fecha,Time hora,String CodUsu_fk,
			String FechaLetras,String ValorLetras,String ValorRecibido,String ValorPendiente,
			String ValoraRecibir,String TipoPago,String concepto,String CodCita,String CuotaModeradora,
			String NumOrden,String ValorOrden,String cheqconsig,String Observacion){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_detalle_recibo_caja(codReCaja_fk,abono,fecha,hora,CodUsu_fk,FechaLetras,ValorLetras,ValorRecibido,ValorPendiente,ValoraRecibir,TipoPago,concepto,codCita,CuotaModeradora,ValorOrden,NumOrden,cheqconsig,Observacion)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			    ps.setString(1,codReCaja_fk);
			    ps.setString(2,abono);
			    ps.setDate(3,fecha);
			    ps.setTime(4,hora);
			    ps.setString(5,CodUsu_fk);
			    ps.setString(6,FechaLetras);
			    ps.setString(7,ValorLetras);
			    ps.setString(8,ValorRecibido);
			    ps.setString(9,ValorPendiente);
			    ps.setString(10,ValoraRecibir);
			    ps.setString(11,TipoPago);
			    ps.setString(12, concepto);
			    ps.setString(13, CodCita);
			    ps.setString(14, CuotaModeradora);
			    ps.setString(15, ValorOrden);
			    ps.setString(16, NumOrden);
			    ps.setString(17, cheqconsig);
			    ps.setString(18, Observacion);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>IngresarDetalleReciboCaja "+ex);
			}
		}
	
	
	public void ActualizarEstadoReciboCaja(String codReCaja){
	    /**
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_recibo_caja SET estado=1 WHERE codigo="+codReCaja+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarReciboCaja "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarReciboCaja(String codReCaja,String concepto){
	    /**
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_recibo_caja SET concepto='"+concepto+"' WHERE codigo="+codReCaja+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarReciboCaja "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void InsertarInmuno(String dilucion,String via_administra,String vol_inyectado,String alergeno,String via,String observacion,String tipo_control,Date fecha,Time hora,String CodUsu,String CodHorarioMedico,String CodPac){
		/**
		 * asignamos la cita al paciente.
		 */
		
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_inmunoterapia (dilucion,via_administra,vol_inyectado,alergeno,via,observacion,tipo_control,fecha,hora,CodUsu,CodHorarioMedico,CodPac) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,dilucion);
			    ps.setString(2,via_administra);
			    ps.setString(3,vol_inyectado);
			    ps.setString(4,alergeno);
			    ps.setString(5,via);
			    ps.setString(6,observacion);
			    ps.setString(7,tipo_control);
			    ps.setDate(8,fecha);
			    ps.setTime(9,hora);
			    ps.setString(10,CodUsu);
			    ps.setString(11,CodHorarioMedico);
			    ps.setString(12,CodPac);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>InsertarInmuno "+ex);
			}
		}
	
	
	public void InsertarCitasCanceladas(String codHorMedico_fk,String fecha_cancelacion,String hora_cancelacion,String usuario_cancela,String usuario_que_asigno,String fecha_que_se_asigno,String hora_que_se_asigno,String MotivoAnula){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_citas_canceladas(codHorMedico_fk,fecha_cancelacion,hora_cancelacion,usuario_cancela,usuario_asignacion,fecha_asignacion,hora_asignacion,observacion)VALUES(?,?,?,?,?,?,?,?)");
			    ps.setString(1,codHorMedico_fk);
			    ps.setString(2,fecha_cancelacion);
			    ps.setString(3,hora_cancelacion);
			    ps.setString(4,usuario_cancela);
			    ps.setString(5,usuario_que_asigno);
			    ps.setString(6,fecha_que_se_asigno);
			    ps.setString(7,hora_que_se_asigno);
			    ps.setString(8,MotivoAnula);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>RelacionAdmisionCex "+ex);
			}
		}
	
	public void InsertarDxIngresoCex(String codDiagnostico,String codUsu,String hora,String fecha,String codAdm,String codPac,String CodDiag_fk ){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_diagnosticoingreso(codDiagnostico,codUsu,hora,fecha,codAdm,codPac,CodDiag_fk)VALUES(?,?,?,?,?,?,?)");
			    ps.setString(1,codDiagnostico);
			    ps.setString(2,codUsu);
			    ps.setString(3,hora);
			    ps.setString(4,fecha);
			    ps.setString(5,codAdm);
			    ps.setString(6,codPac);
			    ps.setString(7,CodDiag_fk);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>InsertarDxIngresoCex "+ex);
			}
		}
	
	public void RelacionAdmisionCex(String CodAdmision,String codigoHorarioMedico){
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO pyp_admcex(CodAdm_fk,CodHorMed_fk)VALUES(?,?)");
			    ps.setString(1,CodAdmision);
			    ps.setString(2,codigoHorarioMedico);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>RelacionAdmisionCex "+ex);
			}
		}
	
	
	public void ActualizarMovimientoAgrupado(String CodPro_fk,String CodCuenta_fk,String CodSucursal_fk,String CodCentroSubcentro_fk,String NumOrden,String valor_copago,String valor_moderacion,String valor_programa,String codCita_fk,String CodDx){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("UPDATE cont_movimientos_agrupados SET CodPro_fk="+CodPro_fk+",CodCuenta_fk="+CodCuenta_fk+",CodSucursal_fk="+CodSucursal_fk+",CodCentroSubcentro_fk="+CodCentroSubcentro_fk+",NumOrden='"+NumOrden+"',valor_copago='"+valor_copago+"',valor_moderacion='"+valor_moderacion+"',valor_programa='"+valor_programa+"',CodDx='"+CodDx+"' WHERE codCita_fk="+codCita_fk+" ");
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarMovimientoAgrupado "+ex);
			}
		}
	
	
	public void IngresarMovimientoAgrupado(String CodPro_fk,String CodCuenta_fk,String CodEntidad_fk,String CodTercero_fk,String CodSucursal_fk,String CodCentroSubcentro_fk,String Fecha_orden,String NumOrden,String valor_copago,String valor_moderacion,String Fecha_insercion,String Hora_insercion,String CodUsuario_fk,String valor_programa,String codCita_fk,String CodDx){
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO cont_movimientos_agrupados (CodPro_fk,CodCuenta_fk,CodEntidad_fk,CodTercero_fk,CodSucursal_fk,CodCentroSubcentro_fk,Fecha_orden,NumOrden,valor_copago,valor_moderacion,Fecha_insercion,Hora_insercion,CodUsuario_fk,valor_programa,codCita_fk,CodDx) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			    ps.setString(1,CodPro_fk);
			    ps.setString(2,CodCuenta_fk);
			    ps.setString(3,CodEntidad_fk);
			    ps.setString(4,CodTercero_fk);
			    ps.setString(5,CodSucursal_fk);
			    ps.setString(6,CodCentroSubcentro_fk);
			    ps.setString(7,Fecha_orden);
			    ps.setString(8,NumOrden);
			    ps.setString(9,valor_copago);
			    ps.setString(10,valor_moderacion);
			    ps.setString(11,Fecha_insercion);
			    ps.setString(12,Hora_insercion);
			    ps.setString(13,CodUsuario_fk);
			    ps.setString(14,valor_programa);
			    ps.setString(15,codCita_fk);
			    ps.setString(16, CodDx);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>IngresarMovimientoAgrupado "+ex);
			}
		}
	
	
	public void AsignarCita(String CodigoEspecialidad,String codigoHorarioMedico,String CodigoMedico,String CodigoPaciente,String UsuarioInsercion,String FechaCita,String Fecha_Insercion,String Hora_Insercion,String Tipo_cita,String Observacion){
		/**
		 * asignamos la cita al paciente.
		 */
		//System.out.println("TipoCita="+Tipo_cita+" Observacion="+Observacion); 
		
		PYP_AsignarCita ac = new PYP_AsignarCita();
		ac.setCodigoEspecialidad(CodigoEspecialidad);
		ac.setcodigoHorarioMedico(codigoHorarioMedico);
		ac.setCodigoMedico(CodigoMedico);
		ac.setCodigoPaciente(CodigoPaciente);
		ac.setUsuarioInsercion(UsuarioInsercion);
		ac.setFechaCita(FechaCita);
		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into pyp_citaspacientes(CodEspecialidad_fk,CodHorMedico_fk,CodMedico_fk,CodPac_fk,UsuarioInsercion,fecha,fecha_insercion,hora_insercion,tipo_consulta,observacion)values(?,?,?,?,?,?,?,?,?,?)");
			    ps.setString(1,ac.getCodigoEspecialidad());
			    ps.setString(2,ac.getcodigoHorarioMedico());
			    ps.setString(3,ac.getCodigoMedico());
			    ps.setString(4,ac.getCodigoPaciente());
			    ps.setString(5,ac.getUsuarioInsercion());
			    ps.setString(6, ac.getFechaCita());
			    ps.setString(7, Fecha_Insercion);
			    ps.setString(8, Hora_Insercion);
			    ps.setString(9, Tipo_cita);
			    ps.setString(10, Observacion);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN PYP_MetodoAsignarCita>>AsignarCita "+ex);
			}
		}
	
	
	
	public void ActualizarHorasFechasMedicoIgual(String HoraCita,String FechaCita,String CodigoMedico ){
		//System.out.println("UPDATE agm_horariomedico SET estado='1' WHERE horas like'%"+HoraCita+"%' AND fechas='"+FechaCita+"' AND codMedico_fk='"+CodigoMedico+"'");
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_horariomedico SET estado='1' WHERE horas like'%"+HoraCita+"%' AND fechas='"+FechaCita+"' AND codMedico_fk='"+CodigoMedico+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarHorasFechasMedicoIgual "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarRegistroHorarioMedico(String codigoHorarioMedico,String NombrePaciente){
	    /**
	     * actualiza el estado del la tabla donde esta el horario del medico.
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update pyp_horariomedico set estado=1,NombrePaciente='"+NombrePaciente+"' where codigo='"+codigoHorarioMedico+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarRegistroHorarioMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void CancelarCita(String codigoHorarioMedico){
	    /**
	     */
		String NombrePaciente=" ";
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update pyp_horariomedico set estado=0,NombrePaciente='"+NombrePaciente+"' where codigo='"+codigoHorarioMedico+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>CancelarCita "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	public void EliminarRegistroDeAsigancion(String codigoHorarioMedico){
	    /**
	     */
		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from pyp_citaspacientes where CodHorMedico_fk='"+codigoHorarioMedico+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>EliminarRegistroDeAsigancion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public java.sql.ResultSet Asignaciondecitaenlugarocupado(String codigoHorarioMedico){	
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pcp.CodPac_fk,pcp.* FROM pyp_citaspacientes pcp,pyp_horariomedico phm WHERE pcp.CodHorMedico_fk=phm.codigo AND phm.codigo="+codigoHorarioMedico+" ");
        	System.out.println("SELECT pcp.CodPac_fk,pcp.* FROM pyp_citaspacientes pcp,pyp_horariomedico phm WHERE pcp.CodHorMedico_fk=phm.codigo AND phm.codigo="+codigoHorarioMedico+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>Asignaciondecitaenlugarocupado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCitasPorMedico(Date fechaServidor,String CodMedico){	
		/**
		 * consulta tiene como parametro el codigo del medico.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.fechas,phm.horas,phm.NombrePaciente,pcp.CodPac_fk,phm.codigo AS CodHorarioMedico,pes.codigo AS CodEspecialidad ,pes.nombre_especialidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico,phm.estado FROM pyp_citaspacientes pcp,pyp_horariomedico phm,pyp_especialidad pes,pyp_medico pme WHERE phm.codigo=pcp.CodHorMedico_fk AND pes.codigo=pme.codEspe_fk AND pes.codigo=pcp.CodEspecialidad_fk AND pme.codigo=phm.codMedico_fk AND phm.estado!=0 AND phm.fechas='"+fechaServidor+"' AND pme.codigo="+CodMedico+" ORDER BY SUBSTRING(phm.horas,7,3),phm.horas  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarCitasPorMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDiagnosticoEgreso(String CodAdm){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(ci.nombre,'-',ci.codigoCIE)  AS dx FROM hic_diagnosticoegreso hdi,cie10 ci WHERE hdi.CodDiag_fk=ci.codigo AND hdi.codAdm='"+CodAdm+"'");
        	//rs=st.executeQuery("select * from hic_diagnosticoegreso where codAdm='"+CodAdm+"'");
        	//System.out.println("select * from hic_diagnosticoingreso where codAdm='"+CodAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarDiagnosticoCex "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDiagnosticoCex(String CodAdm){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(ci.nombre,'-',ci.codigoCIE)  AS dx FROM hic_diagnosticoingreso hdi,cie10 ci WHERE hdi.CodDiag_fk=ci.codigo AND hdi.codAdm='"+CodAdm+"'");
        	//rs=st.executeQuery("select * from hic_diagnosticoingreso where codAdm='"+CodAdm+"'");
        	//System.out.println("select * from hic_diagnosticoingreso where codAdm='"+CodAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarDiagnosticoCex "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarFinalidadConsultaCex(String CodAdm){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select * from hic_destinopaciente where codAdm='"+CodAdm+"'");
        	//System.out.println("select * from hic_destinopaciente where codAdm='"+CodAdm+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarFinalidadConsultaCex "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarEmpresa(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM empresa ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarEmpresa "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet UsuariosCitasConsultaExterna(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT su.usu_codigo,CONCAT(sdp.nombre,' ',sdp.apellido) AS NombreUsuario FROM pyp_citaspacientes pcp,seg_usuario su,seg_datos_personales sdp WHERE sdp.dat_codigo=su.dat_codigo_fk AND pcp.UsuarioInsercion=su.usu_codigo GROUP BY su.usu_codigo ORDER BY NombreUsuario");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>UsuariosCitasConsultaExterna "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarTipoConsulta(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_tipocita ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarTipoConsulta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarTodosMedicos(String Validacion){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pm.codigo,CONCAT(pm.nombre, ' ', pm.apellidos) AS nombre,ps.nombre_especialidad FROM  pyp_medico pm,pyp_especialidad ps WHERE pm.estado = '0' AND pm.codEspe_fk=ps.codigo "+Validacion);
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarTodosMedicos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet RipsEntidad(String FechaInicial,String FechaFinal,String Entidad ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  pag.*,phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(ap.fecha_nacimiento, 5)) AS edad, aen.nombre_entidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico FROM  pyp_horariomedico phm,pyp_admcex pag,adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme WHERE aen.ent_nit='"+Entidad+"' AND phm.estado!='0'  AND pme.codigo=phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"'  AND pag.CodHorMed_fk=phm.codigo AND aad.adm_numero_ingreso=pag.CodAdm_fk AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk  AND ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND aen.ent_nit=acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas,7,3),phm.horas ");
        	//rs=st.executeQuery("SELECT ahm.horas,CONCAT(apa.tipo_documento,'-',apa.numero_documento) AS Documento,CONCAT(apa.nombre,' ',apa.primer_apellido,' ',apa.segundo_apellido) AS Nombre_Paciente,(YEAR(CURDATE())-YEAR(apa.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,aen.nombre_entidad,hdi.codDiagnostico,'' AS'diag_rel1','' AS 'diag_rel2',hde.fin_cons,hde.cau_ext,CONCAT(ame.nombre,' ',ame.apellidos) AS medico FROM adm_admisiones aad,agm_admcex aag,agm_horariomedico ahm,adm_paciente apa,adm_convenio acv,adm_entidad aen,agm_medico ame,hic_destinopaciente hde,hic_diagnosticoingreso hdi WHERE aad.adm_numero_ingreso=aag.CodAdm_fk AND aag.CodHorMed_fk=ahm.codigo  AND apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND acv.conv_numero_contrato=apa.conv_numero_contrato_fk AND acv.ent_nit_contratante_fk=aen.ent_nit AND ahm.fechas BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' AND aen.ent_nit='"+Entidad+"' AND ahm.estado='4' AND ame.codigo=ahm.codMedico_fk AND hde.codAdm=aad.adm_numero_ingreso AND hdi.codAdm=aad.adm_numero_ingreso ORDER BY SUBSTRING(ahm.horas,7,3),ahm.horas  ");
        	System.out.println("SELECT  pag.*,phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(ap.fecha_nacimiento, 5)) AS edad, aen.nombre_entidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico FROM  pyp_horariomedico phm,pyp_admcex pag,adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme WHERE aen.ent_nit='"+Entidad+"' AND phm.estado!='0'  AND pme.codigo=phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"'  AND pag.CodHorMed_fk=phm.codigo AND aad.adm_numero_ingreso=pag.CodAdm_fk AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk  AND ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND aen.ent_nit=acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas,7,3),phm.horas ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>RipsEntidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet SoloRipsMedico(String FechaInicial,String FechaFinal,String Medico ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE()) - YEAR(ap.fecha_nacimiento)) - (RIGHT(CURDATE(), 5) < RIGHT(ap.fecha_nacimiento, 5) ) AS edad,aen.nombre_entidad,CONCAT(pme.nombre, ' ', pme.apellidos) AS medico FROM pyp_horariomedico phm,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme,pyp_citaspacientes pcp WHERE phm.codMedico_fk = '"+Medico+"' AND phm.estado != '0' AND pme.codigo = phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"'  AND ap.conv_numero_contrato_fk = acv.conv_numero_contrato AND pcp.CodHorMedico_fk=phm.codigo AND pcp.CodPac_fk=ap.pac_codigo_paciente AND aen.ent_nit = acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas, 7, 3), phm.horas ");
        	//rs=st.executeQuery("SELECT ahm.horas,CONCAT(apa.tipo_documento,'-',apa.numero_documento) AS Documento,CONCAT(apa.nombre,' ',apa.primer_apellido,' ',apa.segundo_apellido) AS Nombre_Paciente,(YEAR(CURDATE())-YEAR(apa.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,aen.nombre_entidad,hdi.codDiagnostico,'' AS'diag_rel1','' AS 'diag_rel2',hde.fin_cons,hde.cau_ext,CONCAT(ame.nombre,' ',ame.apellidos) AS medico FROM adm_admisiones aad,agm_admcex aag,agm_horariomedico ahm,adm_paciente apa,adm_convenio acv,adm_entidad aen,agm_medico ame,hic_destinopaciente hde,hic_diagnosticoingreso hdi WHERE aad.adm_numero_ingreso=aag.CodAdm_fk AND aag.CodHorMed_fk=ahm.codigo AND apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND acv.conv_numero_contrato=apa.conv_numero_contrato_fk AND acv.ent_nit_contratante_fk=aen.ent_nit AND ahm.fechas BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' AND ahm.codMedico_fk='"+Medico+"' AND ahm.estado='4' AND ame.codigo=ahm.codMedico_fk AND hde.codAdm=aad.adm_numero_ingreso AND hdi.codAdm=aad.adm_numero_ingreso ORDER BY SUBSTRING(ahm.horas,7,3),ahm.horas ");
        	System.out.println("SELECT phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE()) - YEAR(ap.fecha_nacimiento)) - (RIGHT(CURDATE(), 5) < RIGHT(ap.fecha_nacimiento, 5) ) AS edad,aen.nombre_entidad,CONCAT(pme.nombre, ' ', pme.apellidos) AS medico FROM pyp_horariomedico phm,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme,pyp_citaspacientes pcp WHERE phm.codMedico_fk = '"+Medico+"' AND phm.estado != '0' AND pme.codigo = phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"'  AND ap.conv_numero_contrato_fk = acv.conv_numero_contrato AND pcp.CodHorMedico_fk=phm.codigo AND pcp.CodPac_fk=ap.pac_codigo_paciente AND aen.ent_nit = acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas, 7, 3), phm.horas ");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>RipsMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet RipsMedico(String FechaInicial,String FechaFinal,String Medico ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  pag.*,phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(ap.fecha_nacimiento, 5)) AS edad, aen.nombre_entidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico FROM  pyp_horariomedico phm,pyp_admcex pag,adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme WHERE phm.codMedico_fk = '"+Medico+"' AND phm.estado!='0'  AND pme.codigo=phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"'  AND pag.CodHorMed_fk=phm.codigo AND aad.adm_numero_ingreso=pag.CodAdm_fk AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk  AND ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND aen.ent_nit=acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas,7,3),phm.horas ");
        	//rs=st.executeQuery("SELECT ahm.horas,CONCAT(apa.tipo_documento,'-',apa.numero_documento) AS Documento,CONCAT(apa.nombre,' ',apa.primer_apellido,' ',apa.segundo_apellido) AS Nombre_Paciente,(YEAR(CURDATE())-YEAR(apa.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,aen.nombre_entidad,hdi.codDiagnostico,'' AS'diag_rel1','' AS 'diag_rel2',hde.fin_cons,hde.cau_ext,CONCAT(ame.nombre,' ',ame.apellidos) AS medico FROM adm_admisiones aad,agm_admcex aag,agm_horariomedico ahm,adm_paciente apa,adm_convenio acv,adm_entidad aen,agm_medico ame,hic_destinopaciente hde,hic_diagnosticoingreso hdi WHERE aad.adm_numero_ingreso=aag.CodAdm_fk AND aag.CodHorMed_fk=ahm.codigo AND apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND acv.conv_numero_contrato=apa.conv_numero_contrato_fk AND acv.ent_nit_contratante_fk=aen.ent_nit AND ahm.fechas BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' AND ahm.codMedico_fk='"+Medico+"' AND ahm.estado='4' AND ame.codigo=ahm.codMedico_fk AND hde.codAdm=aad.adm_numero_ingreso AND hdi.codAdm=aad.adm_numero_ingreso ORDER BY SUBSTRING(ahm.horas,7,3),ahm.horas ");
        	System.out.println("SELECT  pag.*,phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(ap.fecha_nacimiento, 5)) AS edad, aen.nombre_entidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico FROM  pyp_horariomedico phm,pyp_admcex pag,adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme WHERE phm.codMedico_fk = '"+Medico+"' AND phm.estado!='0'  AND pme.codigo=phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"'  AND pag.CodHorMed_fk=phm.codigo AND aad.adm_numero_ingreso=pag.CodAdm_fk AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk  AND ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND aen.ent_nit=acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas,7,3),phm.horas ");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>RipsMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet RipsMedicoEntidad(String FechaInicial,String FechaFinal,String Medico,String Entidad ){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT  pag.*,phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(ap.fecha_nacimiento, 5)) AS edad, aen.nombre_entidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico FROM  pyp_horariomedico phm,pyp_admcex pag,adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme WHERE phm.codMedico_fk = '"+Medico+"' AND phm.estado!='0' AND aen.ent_nit='"+Entidad+"'  AND pme.codigo=phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"'  AND pag.CodHorMed_fk=phm.codigo AND aad.adm_numero_ingreso=pag.CodAdm_fk AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk  AND ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND aen.ent_nit=acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas,7,3),phm.horas ");
        	//rs=st.executeQuery("SELECT ahm.horas,CONCAT(apa.tipo_documento,'-',apa.numero_documento) AS Documento,CONCAT(apa.nombre,' ',apa.primer_apellido,' ',apa.segundo_apellido) AS Nombre_Paciente,(YEAR(CURDATE())-YEAR(apa.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,aen.nombre_entidad,hdi.codDiagnostico,'' AS'diag_rel1','' AS 'diag_rel2',hde.fin_cons,hde.cau_ext,CONCAT(ame.nombre,' ',ame.apellidos) AS medico FROM adm_admisiones aad,agm_admcex aag,agm_horariomedico ahm,adm_paciente apa,adm_convenio acv,adm_entidad aen,agm_medico ame,hic_destinopaciente hde,hic_diagnosticoingreso hdi WHERE aad.adm_numero_ingreso=aag.CodAdm_fk AND aag.CodHorMed_fk=ahm.codigo AND apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND acv.conv_numero_contrato=apa.conv_numero_contrato_fk AND acv.ent_nit_contratante_fk=aen.ent_nit AND ahm.fechas BETWEEN '"+FechaInicial+"' AND '"+FechaFinal+"' AND ahm.codMedico_fk='"+Medico+"' AND aen.ent_nit='"+Entidad+"' AND ahm.estado='4' AND ame.codigo=ahm.codMedico_fk AND hde.codAdm=aad.adm_numero_ingreso AND hdi.codAdm=aad.adm_numero_ingreso ORDER BY SUBSTRING(ahm.horas,7,3),ahm.horas ");
        	System.out.println("SELECT  pag.*,phm.horas,CONCAT(ap.tipo_documento,'-',ap.numero_documento) AS identificacion,CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS nombre,(YEAR(CURDATE())-YEAR(ap.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(ap.fecha_nacimiento, 5)) AS edad, aen.nombre_entidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico FROM  pyp_horariomedico phm,pyp_admcex pag,adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,pyp_medico pme WHERE phm.codMedico_fk = '"+Medico+"' AND phm.estado!='0' AND aen.ent_nit='"+Entidad+"'  AND pme.codigo=phm.codMedico_fk  AND phm.fechas BETWEEN '"+FechaInicial+"'  AND '"+FechaFinal+"'  AND pag.CodHorMed_fk=phm.codigo AND aad.adm_numero_ingreso=pag.CodAdm_fk AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk  AND ap.conv_numero_contrato_fk=acv.conv_numero_contrato AND aen.ent_nit=acv.ent_nit_contratante_fk ORDER BY SUBSTRING(phm.horas,7,3),phm.horas ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>RipsMedicoEntidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarEntidades(){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ent_nit,nombre_entidad FROM adm_entidad WHERE nombre_entidad!='' ORDER BY nombre_entidad ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarTodosMedicos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCitasPorEspecialidad(Date fechaServidor,String CodEspecialista){	
		/**
		 * consulta tiene como parametro el codigo de la especialidad.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.fechas,phm.horas,phm.NombrePaciente,pcp.CodPac_fk,phm.codigo AS CodHorarioMedico,pes.codigo AS CodEspecialidad ,pes.nombre_especialidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico,phm.estado FROM pyp_citaspacientes pcp,pyp_horariomedico phm,pyp_especialidad pes,pyp_medico pme WHERE phm.codigo=pcp.CodHorMedico_fk AND pes.codigo=pme.codEspe_fk AND pes.codigo=pcp.CodEspecialidad_fk AND pme.codigo=phm.codMedico_fk AND phm.estado!=0 AND phm.fechas='"+fechaServidor+"' AND pes.codigo="+CodEspecialista+" ORDER BY SUBSTRING(phm.horas,7,3),phm.horas  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarCitasPorEspecialidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarCitasPorMedicoEspecialidad(Date fechaServidor,String CodMedico,String CodEspecialista){	
		/**
		 * consulta tiene como parametro el codigo del medico y el codigo de la especialidad.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.fechas,phm.horas,phm.NombrePaciente,pcp.CodPac_fk,phm.codigo AS CodHorarioMedico,pes.codigo AS CodEspecialidad ,pes.nombre_especialidad,CONCAT(pme.nombre,' ',pme.apellidos) AS medico,phm.estado FROM pyp_citaspacientes pcp,pyp_horariomedico phm,pyp_especialidad pes,pyp_medico pme WHERE phm.codigo=pcp.CodHorMedico_fk AND pes.codigo=pme.codEspe_fk AND pes.codigo=pcp.CodEspecialidad_fk AND pme.codigo=phm.codMedico_fk AND phm.estado!=0 AND phm.fechas='"+fechaServidor+"' AND pme.codigo="+CodMedico+" AND pes.codigo="+CodEspecialista+" ORDER BY SUBSTRING(phm.horas,7,3),phm.horas  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarCitasPorMedicoEspecialidad "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarCitasPorIdentificacion(Date fechaServidor,String Identificacion){	
		/**
		 * consulta tiene como parametro la identificacion.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.fechas,phm.horas,phm.NombrePaciente,pcp.CodPac_fk,phm.codigo AS CodHorarioMedico,pes.codigo AS CodEspecialidad,pes.nombre_especialidad,CONCAT(pme.nombre, ' ', pme.apellidos) AS medico,phm.estado FROM pyp_citaspacientes pcp,pyp_horariomedico phm,pyp_especialidad pes,pyp_medico pme, adm_paciente ap WHERE phm.codigo = pcp.CodHorMedico_fk  AND pes.codigo = pme.codEspe_fk  AND pes.codigo = pcp.CodEspecialidad_fk  AND pme.codigo = phm.codMedico_fk  AND ap.pac_codigo_paciente=pcp.CodPac_fk AND phm.estado != 0  AND phm.fechas = '"+fechaServidor+"'  AND ap.numero_documento LIKE '%"+Identificacion+"%' ORDER BY SUBSTRING(phm.horas, 7, 3),phm.horas ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarCitasPorIdentificacion "+ex);
        }	
        return rs;
    }

	
	
	
	public void ActualizarRegistroAsignacionHorarioMedico(String CodHorarioMedico){
	    /**
	     * actualiza el estado del la tabla donde esta el horario del medico a 2
	     * que quiere decir que el paciente se presento a la cita.
	     */		
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_horariomedico SET estado=2 WHERE codigo="+CodHorarioMedico+" ");
	     	System.out.println("UPDATE pyp_horariomedico SET estado=2 WHERE codigo="+CodHorarioMedico+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarRegistroAsignacionHorarioMedico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet ObtenerDatosAdmisionCE(String CodHorarioMedico){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pcp.CodPac_fk,su.usuario,pcp.fecha FROM pyp_horariomedico phm,pyp_citaspacientes pcp,seg_usuario su WHERE pcp.CodHorMedico_fk=phm.codigo AND phm.codigo="+CodHorarioMedico+" AND su.usu_codigo=pcp.UsuarioInsercion ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>FormulacionesPacientesCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FormulacionesPacientesCE(String CodPac){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM(SELECT CONCAT(pf.fecha, '/', pf.hora) AS fecha,pf.codigo,'Laboratorio','1' FROM pyp_formlaboratorio pf,adm_paciente apac,pyp_detalleformlab pdf WHERE pf.CodPac_fk = apac.pac_codigo_paciente   AND apac.pac_codigo_paciente = "+CodPac+" AND pdf.CodFormLab_fk=pf.codigo group by pf.codigo " +
        			"UNION " +
        			"SELECT CONCAT(pf.fecha,'/',pf.hora) AS fecha,pf.codigo,'Formulacion','2' FROM pyp_formulacion pf,adm_paciente apac WHERE pf.CodPac_fk=apac.pac_codigo_paciente AND apac.pac_codigo_paciente="+CodPac+" " +
        					") AS h ORDER BY fecha");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>FormulacionesPacientesCE "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet HistoricoInmuniterapias(String CodPac){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_inmunoterapia pin WHERE pin.CodPac="+CodPac+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>HistoricoInmuniterapias "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FormulacionesPacientesLabCE(String CodPac){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT CONCAT(pf.fecha, '/', pf.hora) AS fecha,pf.codigo,'Laboratorio' FROM pyp_formlaboratorio pf,adm_paciente apac WHERE pf.CodPac_fk = apac.pac_codigo_paciente   AND apac.pac_codigo_paciente = "+CodPac+"  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>FormulacionesPacientesLabCE "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet CargarProcedimientosCex(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT map.servicio,CONCAT(hqx.observacionQx,' en ',hqx.fechaQx) AS Observacion,CONCAT('Fecha de Registro:',hqx.fecha) AS fecha FROM hic_quirurjicos hqx, mapipo map WHERE hqx.codigoQx_fk=map.codigo AND hqx.codpac="+CodPac+" ORDER BY map.servicio");
			//System.out.println("SELECT alergia FROM hic_alergias WHERE codpac_fk="+CodPac+" LIMIT 5 ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarAlergiasLimite "+ex);
		}
		return rs;
	}
	
	
	public java.sql.ResultSet CargarMedicamentosCex(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT med.nombre,hm.observacionMedi FROM hic_medicamentos hm,medicamentos med WHERE hm.codigoMedi_fk=med.codigo AND hm.codpac="+CodPac+" ORDER BY med.nombre ");
			//System.out.println("SELECT alergia FROM hic_alergias WHERE codpac_fk="+CodPac+" LIMIT 5 ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarAlergiasLimite "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarAlergiasLimite(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT CONCAT(alergia,'-',reaccion) FROM hic_alergias WHERE codpac_fk="+CodPac+" ");
			//System.out.println("SELECT alergia FROM hic_alergias WHERE codpac_fk="+CodPac+" LIMIT 5 ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarAlergiasLimite "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet CargarImagenesCateterismos(String CodigoPaciente){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM (SELECT ice.codigo,isu.nombre,ice.fecha,su.usu_codigo,'1' FROM img_citas_espera ice,img_subexa isu,seg_usuario su WHERE ice.codigoExa_fk=isu.codigo AND ice.codigoPac_fk='"+CodigoPaciente+"' AND su.usuario=ice.aprobacion UNION SELECT cid.id_informe_diagnostico_hemodinamia,cer.nombre_estudio_realizado,cid.fecha_diagnostico,'','2' FROM cat_informe_diagnostico_hemodinamia cid,cat_estudio_realizado_hemodinamia cer WHERE cid.id_estudio_realizado_hemodinamia_fk=cer.id_estudio_realizado_hemodinamia AND cid.id_paciente='"+CodigoPaciente+"' ) AS a ORDER BY a.fecha DESC");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarImagenesCateterismos "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarReportesCex(String CodigoPaciente){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hf.nombre_formato,pfp.fecha,pfp.hora,CONCAT(sdp.nombre,' ',sdp.apellido) AS usuario,hf.codigo,su.usu_codigo,pfp.CodHorMedico_fk FROM pyp_formatos_pac pfp,hic_formato hf,seg_usuario su,seg_datos_personales sdp WHERE hf.codigo=pfp.cod_for_fk AND pfp.cod_pac_fk='"+CodigoPaciente+"' AND su.usu_codigo=pfp.cod_usu_fk AND sdp.dat_codigo=su.dat_codigo_fk ORDER BY pfp.fecha DESC");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarReportesCex "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarAdmEncabe(String CodHorMed){	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pa.CodAdm_fk,fef.cod_enc_factura FROM pyp_admcex pa,fact_enc_factura fef WHERE pa.CodAdm_fk=fef.cod_admision AND pa.CodHorMed_fk='"+CodHorMed+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarAdmEncabe "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDatosPacienteCE(String CodigoPaciente){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido,(YEAR(CURDATE())-YEAR(pac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(pac.fecha_nacimiento, 5)) AS edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,aen.nombre_entidad,aen.ent_nit  FROM adm_paciente pac,adm_convenio acv,adm_entidad aen WHERE pac.pac_codigo_paciente="+CodigoPaciente+" AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=pac.conv_numero_contrato_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarDatosPacienteCE "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDatosPacienteCE(String TipoDoc,String NumDocumento){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido,(YEAR(CURDATE())-YEAR(pac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(pac.fecha_nacimiento, 5)) AS edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,aen.nombre_entidad,pac.pac_codigo_paciente FROM adm_paciente pac,adm_convenio acv,adm_entidad aen WHERE pac.tipo_documento='"+TipoDoc+"' and pac.numero_documento='"+NumDocumento+"' AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=pac.conv_numero_contrato_fk ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarDatosPacienteCE "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarListaCitasMedico(String CodUsuario,Date fecha){	
		/**
		 * consulta tiene como parametro el codigo de usuario activo.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT DISTINCT phm.horas,phm.NombrePaciente,phm.codigo AS CodHorarioMedico,pcp.CodMedico_fk,pcp.CodPac_fk,apac.numero_documento AS DocPaciente, ppac.CodAdm_fk,adm.hora_registro FROM seg_datos_personales sdt,seg_usuario su,pyp_medico pme,pyp_horariomedico phm,pyp_citaspacientes pcp,adm_paciente apac,pyp_admcex ppac,adm_admisiones adm WHERE su.dat_codigo_fk = sdt.dat_codigo AND sdt.numeroDocumento = pme.numeroDocumento AND pme.codigo = pcp.CodMedico_fk AND pcp.CodHorMedico_fk = phm.codigo AND phm.estado = 2 AND apac.pac_codigo_paciente = pcp.CodPac_fk  AND su.usu_codigo="+CodUsuario+" AND phm.fechas='"+fecha+"' AND ppac.CodHorMed_fk = phm.codigo AND adm.adm_numero_ingreso=ppac.CodAdm_fk ORDER BY ppac.CodAdm_fk  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarListaCitasMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacienteActivo(String CodigoPaciente){	
		/**
		 * consulta tiene como parametro el tipo documento y el numero documento.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.fechas,phm.horas,phm.estado,CONCAT(pme.nombre,' ',pme.apellidos ) AS Medico FROM pyp_citaspacientes pcp,pyp_horariomedico phm,pyp_medico pme WHERE phm.codigo=pcp.CodHorMedico_fk AND pme.codigo=pcp.CodMedico_fk AND phm.estado!=3 AND phm.estado!=4 AND pcp.CodPac_fk="+CodigoPaciente+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarPacienteActivo "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarFormatosPendientesCE(String CodHorarioMedico){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pfp.codigo,pfp.estado,CONCAT(pfp.fecha,'/',pfp.hora)AS FechaHora,hf.nombre_formato FROM pyp_formatos_pac pfp,hic_formato hf WHERE pfp.CodHorMedico_fk="+CodHorarioMedico+" AND hf.codigo=pfp.cod_for_fk AND pfp.estado=0  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarFormatosPendientesCE "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet BuscarFormatosPendientesCE1(String CodHorarioMedico){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pfp.codigo,pfp.estado,CONCAT(pfp.fecha,'/',pfp.hora)AS FechaHora,hf.nombre_formato FROM pyp_formatos_pac pfp,hic_formato hf WHERE pfp.CodHorMedico_fk="+CodHorarioMedico+" AND hf.codigo=pfp.cod_for_fk  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarFormatosPendientesCE "+ex);
        }	
        return rs;
    }
public java.sql.ResultSet EspecialistaUsuario(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT su.usu_codigo,CONCAT(sd.nombre,' ',sd.apellido) AS usuario FROM pyp_medico pm,seg_datos_personales sd,seg_usuario su WHERE sd.dat_codigo = su.dat_codigo_fk AND sd.numeroDocumento = pm.numeroDocumento ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>EspecialistaUsuario "+ex);
        }	
        return rs;
    }



	public java.sql.ResultSet Departamentos(){	
	
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_departamento ORDER BY nombre ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>Departamentos "+ex);
        }	
        return rs;
    }

	



public java.sql.ResultSet Municipios(String CodDep){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_municipio WHERE dept_codigo_fk="+CodDep+" ORDER BY nombre ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>Departamentos "+ex);
        }	
        return rs;
    }


public java.sql.ResultSet BuscarHorasDisponibles(String CodMed,String Fecha){	

	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT phm.horas FROM  pyp_horariomedico phm,pyp_medico pm,seg_datos_personales sdp,seg_usuario su WHERE phm.codMedico_fk =pm.codigo AND sdp.numeroDocumento=pm.numeroDocumento AND su.dat_codigo_fk=sdp.dat_codigo AND su.usu_codigo='"+CodMed+"' AND phm.estado = 0 AND phm.fechas='"+Fecha+"' GROUP BY phm.horas ORDER BY phm.horas  ");
    	//System.out.println("SELECT ahm.horas FROM  agm_horariomedico ahm,agm_medico am,seg_datos_personales sdp,seg_usuario su WHERE ahm.codMedico_fk =am.codigo AND sdp.numeroDocumento=am.numeroDocumento AND su.dat_codigo_fk=sdp.dat_codigo AND su.usu_codigo='"+CodMed+"' AND ahm.estado = 0 AND ahm.fechas='"+Fecha+"' GROUP BY ahm.horas ORDER BY ahm.horas ");
    }
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarHorasDisponibles "+ex);
    }	
    return rs;
}

public java.sql.ResultSet BuscarMedicosConsultaExterna(){	

	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT pm.codigo,CONCAT(pm.nombre,' ',pm.apellidos) AS Medico,sdp.profesion,sdp.ocupacion,DATE_FORMAT(phm.fechas,'%W') AS Dia, " +
    			" SUBSTRING(phm.horas, 7, 3) jornada " +
    			" FROM pyp_medico pm,pyp_horariomedico phm,seg_datos_personales sdp " +
    			" WHERE pm.codigo=phm.codMedico_fk AND sdp.numeroDocumento=pm.numeroDocumento " +
    			" GROUP BY pm.codigo ORDER BY Medico");
    	//System.out.println("SELECT ahm.horas FROM  agm_horariomedico ahm,agm_medico am,seg_datos_personales sdp,seg_usuario su WHERE ahm.codMedico_fk =am.codigo AND sdp.numeroDocumento=am.numeroDocumento AND su.dat_codigo_fk=sdp.dat_codigo AND su.usu_codigo='"+CodMed+"' AND ahm.estado = 0 AND ahm.fechas='"+Fecha+"' GROUP BY ahm.horas ORDER BY ahm.horas ");
    }
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarMedicosConsultaExterna "+ex);
    }	
    return rs;
}
public java.sql.ResultSet BuscarMedicosConsultaExternaDetalle(String CodMedico){	

	java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT pm.codigo,CONCAT(pm.nombre,' ',pm.apellidos) AS Medico,sdp.profesion,sdp.ocupacion,DATE_FORMAT(phm.fechas,'%W') AS Dia, " +
    			" SUBSTRING(phm.horas, 7, 3) jornada " +
    			" FROM pyp_medico pm,pyp_horariomedico phm,seg_datos_personales sdp " +
    			" WHERE pm.codigo=phm.codMedico_fk AND sdp.numeroDocumento=pm.numeroDocumento AND pm.codigo='"+CodMedico+"' AND SUBSTRING(phm.horas, 7, 3)!='MD'  " +
    			" GROUP BY Dia,SUBSTRING(phm.horas, 7, 3),pm.codigo ORDER BY DATE_FORMAT(phm.fechas,'%w'),jornada ");
    	//System.out.println("SELECT ahm.horas FROM  agm_horariomedico ahm,agm_medico am,seg_datos_personales sdp,seg_usuario su WHERE ahm.codMedico_fk =am.codigo AND sdp.numeroDocumento=am.numeroDocumento AND su.dat_codigo_fk=sdp.dat_codigo AND su.usu_codigo='"+CodMed+"' AND ahm.estado = 0 AND ahm.fechas='"+Fecha+"' GROUP BY ahm.horas ORDER BY ahm.horas ");
    }
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarMedicosConsultaExternaDetalle "+ex);
    }	
    return rs;
}

	

	public java.sql.ResultSet BuscarPaciente(String TipoDocumento,String NumeroDocumento){	
		/**
		 * consulta tiene como parametro el tipo documento y el numero documento.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido, aen.nombre_entidad,apac.genero, apac.fecha_nacimiento,fmt.descripcion,fmt.cod_manual_tarifario,aen.ent_nit,aen.ent_nit_contratante,(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(apac.fecha_nacimiento, 5)) AS edad  FROM adm_paciente apac,adm_convenio aco,adm_entidad aen, fact_convenios fc,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato  AND aco.ent_nit_contratante_fk = aen.ent_nit AND apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"' AND fc.cod_entidad=aen.ent_nit AND ftc.cod_convenio=fc.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND (fmt.manual_base=1 OR fmt.manual_base=2)");
        	//System.out.println("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido, aen.nombre_entidad,apac.genero, apac.fecha_nacimiento,fmt.descripcion,fmt.cod_manual_tarifario,aen.ent_nit,aen.ent_nit_contratante FROM adm_paciente apac,adm_convenio aco,adm_entidad aen, fact_convenios fc,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato  AND aco.ent_nit_contratante_fk = aen.ent_nit AND apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"' AND fc.cod_entidad=aen.ent_nit AND ftc.cod_convenio=fc.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND (fmt.manual_base=1 OR fmt.manual_base=2)");
        	//System.out.println("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido,aen.nombre_entidad,apac.genero,apac.fecha_nacimiento FROM adm_paciente apac,adm_convenio aco,adm_entidad aen WHERE apac.conv_numero_contrato_fk=aco.conv_numero_contrato AND aco.ent_nit_contratante_fk=aen.ent_nit AND apac.tipo_documento='"+TipoDocumento+"' AND apac.numero_documento='"+NumeroDocumento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarPaciente "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacienteRC(String TipoDocumento,String NumeroDocumento){	
		/**
		 * consulta tiene como parametro el tipo documento y el numero documento.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido,aen.nombre_entidad,acp.codigo FROM adm_paciente apac, adm_convenio aco, adm_entidad aen, agm_citaspacientes acp, agm_horariomedico ahm WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato AND aco.ent_nit_contratante_fk = aen.ent_nit  AND apac.tipo_documento = '"+TipoDocumento+"'   AND apac.numero_documento = '"+NumeroDocumento+"'   AND acp.CodPac_fk=apac.pac_codigo_paciente   AND acp.CodHorMedico_fk=ahm.codigo   AND (ahm.estado=2 OR ahm.estado=1) ");
        	rs=st.executeQuery(" SELECT apac.pac_codigo_paciente,apac.nombre,apac.primer_apellido,apac.segundo_apellido,aen.nombre_entidad,'0' FROM  adm_paciente apac,adm_convenio aco,adm_entidad aen WHERE apac.conv_numero_contrato_fk = aco.conv_numero_contrato AND aco.ent_nit_contratante_fk = aen.ent_nit  AND apac.tipo_documento = '"+TipoDocumento+"'   AND apac.numero_documento = '"+NumeroDocumento+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarPaciente "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet CiudadFechaRecibo(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT emp.municipio,YEAR(CURDATE()) AS anio,MONTH(CURDATE()) AS mes,DAY(CURDATE()) AS dia FROM empresa emp  ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CiudadFechaRecibo "+ex);
        }	
        return rs;
    }
public java.sql.ResultSet TipoConcepto(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_tipo_concepto ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>TipoConcepto "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet AbonosReciboCaja(String codReCaja_fk){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	//rs=st.executeQuery("SELECT * FROM agm_detalle_recibo_caja WHERE codReCaja_fk="+codReCaja_fk+"");
    	rs=st.executeQuery("SELECT pdr.*,prc.valor_total FROM pyp_detalle_recibo_caja pdr,pyp_recibo_caja prc WHERE prc.codigo=pdr.codReCaja_fk AND pdr.codReCaja_fk="+codReCaja_fk+"");
    }
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>AbonosReciboCaja "+ex);
    }	
    return rs;
}
	
	public java.sql.ResultSet BuscarRecibodeCajaActivo(String CodPac){	
		/**
		 * consulta tiene como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_recibo_caja WHERE CodPac_fk="+CodPac+" and estado=1 ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarRecibodeCajaActivo "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarRecibodeCajaHistorial(String CodPac){	
		/**
		 * consulta tiene como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_recibo_caja WHERE CodPac_fk="+CodPac+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarRecibodeCajaHistorial "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDetalleReciboCaja(Date fecha,Time hora){	
		/**
		 * consulta tiene como parametro el codigo del paciente.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_detalle_recibo_caja WHERE fecha='"+fecha+"' AND hora='"+hora+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarDetalleReciboCaja "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet CargarSubCentrosCosto(String CodCCosto){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ccs.codigo,fsc.* FROM fact_subcentros_costo fsc,cont_centro_subcentro ccs WHERE fsc.cod_subcentro_costo=ccs.cod_sub_centro_costo_fk AND ccs.cod_centro_costo_fk='"+CodCCosto+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarSubCentrosCosto "+ex);
        }	
        return rs;
    }
	
public java.sql.ResultSet CargarCentrosCosto(String CodSuc){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fc.* FROM cont_surcursal_centrocosto csc,fact_centrocosto fc WHERE fc.codigo=csc.cod_centro_costo_fk AND csc.cod_sucursal_fk='"+CodSuc+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>CargarCentrosCosto "+ex);
        }	
        return rs;
    }

public java.sql.ResultSet VerificarAutorizacion(String CodHorMed){	
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT cma.*,fp.cod_referencia,fp.descripcion AS Programa,cc.CodigoCuenta,cc.NombreCuenta,aen.ent_nit_contratante,aen.nombre_entidad,fmt.cod_manual_tarifario,csu.codigo,csu.nombre,fcc.codigo,fcc.NombreCentroCosto,fsc.descripcion AS Subcentrocosto,ce.codigoCIE,ce.nombre AS Nombredx FROM cont_movimientos_agrupados cma,fact_programas fp,cont_cuentas cc,adm_entidad aen,fact_convenios fcv,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt,cont_sucursales csu,cont_centro_subcentro ccs,fact_centrocosto fcc,fact_subcentros_costo fsc,cie10 ce WHERE cma.codCita_fk = "+CodHorMed+" AND fp.cod_programa = cma.CodPro_fk AND cc.codigo = cma.CodCuenta_fk AND aen.ent_nit = cma.CodEntidad_fk AND fcv.cod_entidad = aen.ent_nit AND ftc.cod_convenio = fcv.cod_convenio AND fmt.cod_manual_tarifario = ftc.cod_manualTarifario AND (fmt.manual_base = '1' OR fmt.manual_base = '2') AND csu.codigo = cma.CodSucursal_fk AND ccs.codigo = cma.CodCentroSubcentro_fk  AND fcc.codigo = ccs.cod_centro_costo_fk  AND fsc.cod_subcentro_costo = ccs.cod_sub_centro_costo_fk  AND ce.codigo=cma.CodDx ");
    	//rs=st.executeQuery("SELECT cma.*,fp.cod_referencia,fp.descripcion,cc.CodigoCuenta,cc.NombreCuenta,aen.ent_nit_contratante,aen.nombre_entidad,fmt.cod_manual_tarifario,csu.codigo,csu.nombre,fcc.codigo,fcc.NombreCentroCosto,fsc.descripcion FROM cont_movimientos_agrupados cma,fact_programas fp,cont_cuentas cc,adm_entidad aen,fact_convenios fcv,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt,cont_sucursales csu,cont_centro_subcentro ccs, fact_centrocosto fcc,fact_subcentros_costo fsc WHERE cma.codCita_fk = "+CodHorMed+" AND fp.cod_programa = cma.CodPro_fk AND cc.codigo = cma.CodCuenta_fk AND aen.ent_nit = cma.CodEntidad_fk AND fcv.cod_entidad = aen.ent_nit AND ftc.cod_convenio = fcv.cod_convenio AND fmt.cod_manual_tarifario = ftc.cod_manualTarifario AND (fmt.manual_base = '1' OR fmt.manual_base = '2' ) AND csu.codigo=cma.CodSucursal_fk AND ccs.codigo=cma.CodCentroSubcentro_fk AND fcc.codigo=ccs.cod_centro_costo_fk AND fsc.cod_subcentro_costo=ccs.cod_sub_centro_costo_fk");
    }
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>VerificarAutorizacion "+ex);
    }	
    return rs;
}


public java.sql.ResultSet BuscarManualTarifarioCEX(String CodHorMed){	
	
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT fmt.cod_manual_tarifario,aen.ent_nit_contratante,aen.nombre_entidad,fmt.descripcion,aen.ent_nit FROM pyp_citaspacientes pcp,adm_paciente ap,adm_convenio acv,adm_entidad aen,fact_convenios fcv,fact_tarifas_convenios ftc,fact_manuales_tarifarios fmt WHERE pcp.CodHorMedico_fk="+CodHorMed+" AND pcp.CodPac_fk=ap.pac_codigo_paciente AND acv.conv_numero_contrato=ap.conv_numero_contrato_fk AND aen.ent_nit=acv.ent_nit_contratante_fk AND fcv.cod_entidad=aen.ent_nit AND ftc.cod_convenio=fcv.cod_convenio AND fmt.cod_manual_tarifario=ftc.cod_manualTarifario AND (fmt.manual_base='1' OR fmt.manual_base='2')");
    }
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarManualTarifarioCEX "+ex);
    }	
    return rs;
}

	public java.sql.ResultSet CargarSucursales(){	
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cont_sucursales ORDER BY nombre");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoAsignarCita>>CargarSucursales "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarEspecialidadTodas(String Validacion){	
		/**
		 * consulta que busca todas las especialidades
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo,nombre_especialidad from pyp_especialidad WHERE estado='0' "+Validacion);
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarEspecialidadTodas "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet buscarHoras(){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT horas FROM pyp_horariomedico GROUP BY horas");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>buscarHoras "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMedicoEspecialidad(String CodigoEspecialidad){	
		/**
		 * consulta los medicos de una determinada especialidad
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select pme.codigo,pme.nombre,pme.apellidos from pyp_especialidad pes,pyp_medico pme where pes.codigo=pme.codEspe_fk and pes.codigo="+CodigoEspecialidad+"");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarMedicoEspecialidad "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarDiaSemana(String fecha){	
		/**
		 * verifica en k dia de la semana fue seleccionado
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select DAYNAME('"+fecha+"')");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>DiaSemana "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ValidarDiaSemana(String DiaSemana){	
		/**
		 * verifica si el dia de la semana es valido para atencion.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select codigo from pyp_horadia where dia='"+DiaSemana+"'");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ValidarDiaSemana "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarHorarioMedico(String fecha,String CodigoMedico){	
		/**
		 * Busca el horario del medico dependiendo la fecha.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select phm.codigo,phm.horas,phm.estado,phm.fechas,phm.NombrePaciente from pyp_horariomedico phm,pyp_medico pmed where phm.codMedico_fk=pmed.codigo and pmed.codigo="+CodigoMedico+" and phm.fechas='"+fecha+"'");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>BuscarHorarioMedico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet FechaCompleta(String fecha){	
		/**
		 * verifica en k dia de la semana fue seleccionado
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct DAYNAME('"+fecha+"') as Nomday,day('"+fecha+"') as Dia,MONTHNAME('"+fecha+"') as NomMes,YEAR('"+fecha+"') AS ANIO,month('"+fecha+"')");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>FechaCompleta "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet NombreCitasOcupado(){	
		/**
		 * busca los nombres de los pacientes con citas
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.pac_codigo_paciente from adm_paciente apac,pyp_citaspacientes pcp,pyp_horariomedico phm where apac.pac_codigo_paciente=pcp.CodPac_fk and phm.codigo=pcp.CodHorMedico_fk");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>NombreCitasOcupado "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet NombreCitasOcupadoNombres(String CodiPaci){	
		/**
		 * busca los nombres de los pacientes con citas
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.pac_codigo_paciente from adm_paciente apac,pyp_citaspacientes pcp,pyp_horariomedico phm where apac.pac_codigo_paciente=pcp.CodPac_fk and phm.codigo=pcp.CodHorMedico_fk and apac.pac_codigo_paciente='"+CodiPaci+"'");

        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>NombreCitasOcupadoNombres "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ListaDeMedicosCitasFecha(String fecha){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT pes.nombre_especialidad,CONCAT(pmd.nombre, ' ', pmd.apellidos) AS NombreMedico,pmd.codigo FROM pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes,pyp_citaspacientes pcp,adm_paciente apa,adm_entidad aent,adm_convenio acon WHERE  phm.fechas = '"+fecha+"' AND phm.codMedico_fk = pmd.codigo AND pmd.codEspe_fk = pes.codigo AND pcp.CodHorMedico_fk = phm.codigo AND pcp.CodPac_fk = apa.pac_codigo_paciente AND apa.conv_numero_contrato_fk = acon.conv_numero_contrato  AND acon.ent_nit_contratante_fk = aent.ent_nit GROUP BY pmd.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ListaDeMedicosCitasFecha "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet PacientesConHC(String CodPac){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM pyp_formatos_pac WHERE cod_pac_fk="+CodPac+"");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>PacientesConHC "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ListaDeCitas(String CodigoMedico,String fecha){	
		/**
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT phm.horas,phm.fechas,phm.NombrePaciente,pes.nombre_especialidad,CONCAT(pmd.nombre,' ',pmd.apellidos) AS NombreMedico,phm.estado,CONCAT(apa.tipo_documento,'-',apa.numero_documento) AS IdentPac,aent.nombre_entidad,CONCAT(apa.telefono_celular,'-',apa.telefono_fijo,'-',apa.telefono_oficina ) AS telefonos,apa.pac_codigo_paciente  FROM pyp_horariomedico phm,pyp_medico pmd,pyp_especialidad pes,pyp_citaspacientes pcp,adm_paciente apa,adm_entidad aent,adm_convenio acon WHERE phm.codMedico_fk="+CodigoMedico+" AND phm.fechas='"+fecha+"' AND phm.codMedico_fk=pmd.codigo AND pmd.codEspe_fk=pes.codigo AND pcp.CodHorMedico_fk=phm.codigo AND pcp.CodPac_fk=apa.pac_codigo_paciente AND apa.conv_numero_contrato_fk=acon.conv_numero_contrato AND acon.ent_nit_contratante_fk=aent.ent_nit ORDER BY SUBSTRING(phm.horas,7,3),phm.horas ");
        }
        catch(Exception ex){
        	System.out.println("Error en PYP_MetodoAsignarCita>>ListaDeCitas "+ex);
        }	
        return rs;
    }
	
	/////recibo DE CAJA ////

	
	public void ActualizarReciboCajaPrincipal(String CodReciboCajaPrincipal,String Valor){
	  	
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_recibo_caja SET valor_total="+Valor+" WHERE codigo="+CodReciboCajaPrincipal+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarReciboCajaPrincipal "+ex);
	     	ex.getMessage();	     
	     }	
	 }

public void ActualizarDetalleReciboCaja(String ValorAbono,String CuotaModeradora,String ValorOrden,String NumOrden,String ValorCheq,String CodReciboCaja,String ValorLetra,String concepto,String tpago){
	System.out.println("UPDATE pyp_detalle_recibo_caja SET abono="+ValorAbono+",CuotaModeradora="+CuotaModeradora+",ValorOrden="+ValorOrden+",NumOrden='"+NumOrden+"',cheqconsig="+ValorCheq+",ValorLetras='"+ValorLetra+"' , concepto='"+concepto+"' , TipoPago='"+tpago+"' WHERE codigo="+CodReciboCaja+"");
	  	
		PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE pyp_detalle_recibo_caja SET abono="+ValorAbono+",CuotaModeradora="+CuotaModeradora+",ValorOrden="+ValorOrden+",NumOrden='"+NumOrden+"',cheqconsig="+ValorCheq+",ValorLetras='"+ValorLetra+"' , concepto='"+concepto+"' , TipoPago='"+tpago+"' WHERE codigo="+CodReciboCaja+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarDetalleReciboCaja "+ex);
	     	ex.getMessage();	     
	     }	
	 }

public java.sql.ResultSet DatosParaReciboDeCaja(String NumeroRecibo){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT pd.*,pr.* FROM pyp_detalle_recibo_caja pd,pyp_recibo_caja pr WHERE pr.codigo=pd.codReCaja_fk AND pd.codigo="+NumeroRecibo+" ");
    	System.out.println("SELECT pd.*,pr.* FROM pyp_detalle_recibo_caja pd,pyp_recibo_caja pr WHERE pr.codigo=pd.codReCaja_fk AND pd.codigo="+NumeroRecibo+" ");
    	}
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>DatosParaReciboDeCaja "+ex);
    }	
    return rs;
}



	public java.sql.ResultSet DatosDelPaciente(String CodigoPaciente){	
	/**
	 */
    java.sql.ResultSet rs=null;
    Statement st = null;
    try{
    	Conexion con=new Conexion();
    	st = con.conn.createStatement();
    	rs=st.executeQuery("SELECT CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) paciente,ae.nombre_entidad FROM adm_paciente ap,adm_entidad ae,adm_convenio ac WHERE ae.ent_nit=ac.ent_nit_contratante_fk AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato AND ap.pac_codigo_paciente="+CodigoPaciente+" ");
    	System.out.println("SELECT CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) paciente,ae.nombre_entidad FROM adm_paciente ap,adm_entidad ae,adm_convenio ac WHERE ae.ent_nit=ac.ent_nit_contratante_fk AND ap.conv_numero_contrato_fk=ac.conv_numero_contrato AND ap.pac_codigo_paciente="+CodigoPaciente+" ");
    }
    catch(Exception ex){
    	System.out.println("Error en PYP_MetodoAsignarCita>>DatosDelPaciente "+ex);
    }	
    return rs;
}



public void ActualizarValoresDetalleReciboCaja(String Tot,String Valorpendiente,String ValorAbonado,String CodReciboCaja){
 	
	PreparedStatement st = null;
     try{
     	Conexion con=new Conexion();
     	st= con.conn.prepareStatement("UPDATE pyp_detalle_recibo_caja SET ValorRecibido='"+ValorAbonado+"',ValorPendiente='"+Valorpendiente+"',ValoraRecibir='"+Tot+"' WHERE codigo="+CodReciboCaja+" ");
     	st.executeUpdate();	 
     	st.close();
     	con.cerrar();
     }
     catch(Exception ex){
     	System.out.println("ERROR EN PYP_MetodoAsignarCita>>ActualizarValoresDetalleReciboCaja "+ex);
     	ex.getMessage();	     
     }	
 }
	
	///FIN RECIBO DE CAJA /////


}