/**
 * logica:MetodoPreingreso
 * Desarrollado:yosimar valega
 */

package adm_logica;


import hic_bean.CrearAntecedentes;

import java.sql.*;


import adm_bean.DiagnosticoCola;
import adm_bean.FormatoPacCola;
import adm_bean.Preingreso;
import adm_bean.ResultadoFormCola;



public class MetodoPreingreso {
	
	
	public MetodoPreingreso(){
    	
    }
	
	public java.sql.ResultSet ObtenerServicios(){	   
		/**
		 * busca los servicios.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM adm_unidades_ambulatorias ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerServicios "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet CargarCamasSubarea(String CodSubarea){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT cen_numero_cama,codigocama FROM adm_censo_cama WHERE codsubarea='"+CodSubarea+"' AND est_codigo_estado_fk=1 ORDER BY codigocama ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>CargarCamasSubarea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet DetalledeFormulacionTr(String CodigoFormulacion){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT CONCAT(med.nombre,'/',med.concentracion) AS NomMedicamento,hdf.dosis,hdf.observacion,hdf.cantidad,hdf.codigo FROM adm_detalle_formulaciontr hdf,medicamentos med WHERE hdf.CodMedicamento_fk=med.codigo AND hdf.CodFormulacion_fk="+CodigoFormulacion+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>DetalledeFormulacionTr "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoDetalleFormulacionTr(String codFormulacion_fk,String codigoMed){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdf.codigo FROM adm_detalle_formulaciontr hdf WHERE hdf.CodFormulacion_fk="+codFormulacion_fk+" AND hdf.CodMedicamento_fk="+codigoMed+"  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerCodigoDetalleFormulacionTr "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerCodigoFormulacionTr(Time hora,Date fecha){	   
		/**
		 * busca el codigo de la formulacion en curso.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hfrc.codigo FROM adm_formulaciontr hfrc WHERE hfrc.hora='"+hora+"' AND hfrc.fecha='"+fecha+"'  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerCodigoFormulacionTr "+ex);
        }	
        return rs;
    }
	public void CrearFormulacionTr(String codCita,String observacion,String estado,String usuario,Time hora,Date fecha){
		/**
		 */	    
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO adm_formulaciontr(CodCita_fk,observacion,estado,CodUsu_fk,hora,fecha)VALUES(?,?,?,?,?,?)");
			    ps.setString(1, codCita);
			    ps.setString(2, observacion);
			    ps.setString(3, estado);
			    ps.setString(4, usuario);
			    ps.setTime(5, hora);
			    ps.setDate(6,fecha);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPreingreso>>CrearFormulacionTr "+ex);
			}
		}
	public void CrearDetalleFormulacionTr(String codFormulacion_fk,String codigoMed,String cantidad,String dosificacion,String observacion,String estado){
		/**
		 */
	  	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO adm_detalle_formulaciontr(CodFormulacion_fk,CodMedicamento_fk,cantidad,dosis,observacion,estado)VALUES(?,?,?,?,?,?)");
			    ps.setString(1, codFormulacion_fk);
			    ps.setString(2, codigoMed);
			    ps.setString(3, cantidad);
			    ps.setString(4, dosificacion);
			    ps.setString(5, observacion);
			    ps.setString(6, estado);
			 
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPreingreso>>CrearDetalleFormulacionTr "+ex);
			}

		}
	public void ActualizarListaColaFin(String CodColaFin){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE adm_finalcola SET estado=2 WHERE codigo="+CodColaFin+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoPreingreso>>ActualizarListaColaFin "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	
	public java.sql.ResultSet VerificarPacientesPorAdmision(){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT ac.tipo_documento,ac.numero_documento,ac.nombre,ac.primer_apellido,ac.segundo_apellido,afc.tipoTriage,afc.fecha,afc.hora,ac.eps,afc.codigo FROM adm_finalcola afc,adm_cola ac WHERE ac.col_codigo=afc.CodCola_fk AND afc.direccionar=3 AND afc.estado=0");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarPacientesPorAdmision "+ex);
		}
		return rs;
	}
	
	public void InsertarDatosFinalCola(String TipoSalida,String TipoTriage,String CodCola_Fk,String CodUsu_Fk,String Fecha,String Hora,String direccionar,String Estado){
	    
    	Preingreso pre = new Preingreso();
		pre.setTipoSalida(TipoSalida);
		pre.setTipoTriage(TipoTriage);
		pre.setCodCola_Fk(CodCola_Fk);
		pre.setCodUsu_Fk(CodUsu_Fk);
		pre.setFecha(Fecha);
		pre.setHora(Hora);
		pre.setdireccionar(direccionar);
		pre.setEstado(Estado);
		
		try{	
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    
		    ps=con.conn.prepareStatement("INSERT INTO adm_finalcola(tipoSalida,tipoTriage,CodCola_fk,CodUsu_fk,Fecha,Hora,direccionar,estado) VALUES(?,?,?,?,?,?,?,?)");				
			ps.setString(1, pre.getTipoSalida());
			ps.setString(2, pre.getTipoTriage());
			ps.setString(3, pre.getCodCola_Fk());
			ps.setString(4, pre.getCodUsu_Fk());
			ps.setString(5, pre.getFecha());
			ps.setString(6, pre.getHora());
			ps.setString(7, pre.getdireccionar());
			ps.setString(8, pre.getEstado());
			ps.executeUpdate();
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.print("Error en MetodoPreingreso>>InsertarDatosFinalCola "+ex);
			ex.getStackTrace();
		}
		
		
	}

	public void OmitirRegistrosFormatoCola(String FechaFormato,String HoraFormato){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM adm_colaresultados WHERE fecha='"+FechaFormato+"' AND hora='"+HoraFormato+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoPreingreso>>OmitirRegistrosFormatoCola "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirFormatoColaPaciente(String CodigoFormatoPaciente){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM adm_formatoscola WHERE codigo="+CodigoFormatoPaciente+"");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoPreingreso>>OmitirFormatoColaPaciente "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void InsertarMedicamentos(String codigoMed,String Observacion,String CodPac,String CodUsu,String fecha ,String hora,String cedula){
		/**
		 * creamos los medicamentos historicas del paciente.
		 * 
		 * codigoMedi_fk,observacionMedi,codpac,codusu,fecha,hora
		 */
	CrearAntecedentes cad = new CrearAntecedentes();
	cad.setcodigoMd(codigoMed);
	cad.setObservacion(Observacion);
	cad.setCodPac(CodPac);
	cad.setCodUsu(CodUsu);
	cad.setfecha(fecha);
	cad.sethora(hora);
	cad.setcedula(cedula); 
	   
	try{
		PreparedStatement ps = null;
		Conexion con=new Conexion();
		ps=con.conn.prepareStatement("insert into hic_medicamentos(codigoMedi_fk,observacionMedi,codpac,codusu,fecha,hora,cedpac_fk)values(?,?,?,?,?,?,?)");
		ps.setString(1, cad.getcodigoMd());
		ps.setString(2, cad.getObservacion());
		ps.setString(3, cad.getCodPac());
		ps.setString(4, cad.getCodUsu());
		ps.setString(5, cad.getfecha());
		ps.setString(6, cad.gethora());
		ps.setString(7, cad.getcedula());
			    
		ps.executeUpdate();
		ps.close();
		con.cerrar();				
	}catch(Exception ex){
		System.out.println("Error MetodoPreingreso>>InsertarMedicamentos "+ex);
	}
	   
}
	
	
	public java.sql.ResultSet VerificarFormatosPendientes(String CodAdmCola){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select distinct hfor.nombre_formato,afc.fecha,afc.hora,sdt.apellido,sdt.nombre from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,adm_colaresultados hres,adm_formatoscola afc,seg_usuario sgu,seg_datos_personales sdt,adm_cola ac where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.cod_preg_fk=hp.codigo and afc.codigo_for_fk= hfor.codigo and afc.estado=0 and sgu.dat_codigo_fk=sdt.dat_codigo and sgu.usu_codigo=afc.codigo_usu_fk and ac.col_codigo=afc.codigo_cola_fk and afc.codigo_cola_fk='"+CodAdmCola+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarFormatosPendientes "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet Verificarmedicamentos(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select hmd.observacionMedi,med.nombre,med.concentracion from hic_medicamentos hmd,medicamentos med where med.codigo=hmd.codigoMedi_fk and hmd.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>Verificarmedicamentos "+ex);
		}
		return rs;
	}
	
	public void InsertarTransfuciones(String transfusion,String cantidad,String Observacion,String fechaTrans,String CodPac,String CodUsu,String fecha ,String hora,String cedula){
		/**
		 * creamos las tranfuciones historicas del paciente.
		 */
	   CrearAntecedentes cad = new CrearAntecedentes();
	   cad.settransfucion(transfusion);
	   cad.setcantidad(cantidad);
	   cad.setObservacion(Observacion);
	   cad.setfechaTrans(fechaTrans);
	   cad.setCodPac(CodPac);
	   cad.setCodUsu(CodUsu);
	   cad.setfecha(fecha);
	   cad.sethora(hora);
	   cad.setcedula(cedula);
	   
	   try{
		   PreparedStatement ps = null;
		   Conexion con=new Conexion();
		   ps=con.conn.prepareStatement("insert into hic_transfuciones(transfusion,cantidad,observacion,fechaTrans,codpac_fk,codusu_fk,fecha,hora,cedpac_fk)values(?,?,?,?,?,?,?,?,?)");
		   ps.setString(1, cad.gettransfucion());
		   ps.setString(2, cad.getcantidad());
		   ps.setString(3, cad.getObservacion());
		   ps.setString(4, cad.getfechaTrans());
		   ps.setString(5, cad.getCodPac());
		   ps.setString(6, cad.getCodUsu());
		   ps.setString(7, cad.getfecha());
		   ps.setString(8, cad.gethora());
		   ps.setString(9, cad.getcedula());
		   ps.executeUpdate();
		   ps.close();
		   con.cerrar();				
	   }catch(Exception ex){
		   System.out.println("Error MetodoPreingreso>>InsertarTransfuciones "+ex);
	   }
	   
}
	
	public java.sql.ResultSet VerificarTransfu(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select transfusion,cantidad,observacion,fechaTrans from hic_transfuciones where cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarTransfu "+ex);
		}
		return rs;
	}
	
	
	public java.sql.ResultSet VerObservacionDiagnosticoFamiliar(String Diagnos){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select haf.observacion,cie.nombre,haf.familiar from hic_antfamiliar haf,cie10 cie where haf.codigo='"+Diagnos+"' and haf.codigoCIE_fk=cie.codigoCIE");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerObservacionDiagnosticoFamiliar "+ex);
		}
		return rs;
	}
	
public void InsertarAntefamiliar(String Familiar,String Observacion,String CodCIE_fk,String CodPac,String CodUsu,String hora,String fecha,String cedula){
		
		/**
		 * creamos los antecedentes familiares del paciente.
		 */
	   CrearAntecedentes cad = new CrearAntecedentes();
	   cad.setFamiliar(Familiar);
	   cad.setObservacion(Observacion);
	   cad.setCodCIE_fk(CodCIE_fk);
	   cad.setCodPac(CodPac);
	   cad.setCodUsu(CodUsu);
	   cad.sethora(hora);
	   cad.setfecha(fecha);
	   cad.setcedula(cedula);
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into hic_antfamiliar(familiar,observacion,codigoCIE_fk,codpac_fk,codusu_fk,hora,fecha,cedpac_fk)values(?,?,?,?,?,?,?,?)");
			    ps.setString(1, cad.getFamiliar());
			    ps.setString(2, cad.getObservacion());
			    ps.setString(3, cad.getCodCIE_fk());
			    ps.setString(4, cad.getCodPac());
			    ps.setString(5, cad.getCodUsu());
			    ps.setString(6, cad.gethora());
			    ps.setString(7, cad.getfecha());
			    ps.setString(8, cad.getcedula());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
				System.out.println("Error MetodoPreingreso>>InsertarAntefamiliar "+ex);
			}

		}
	
	
	public java.sql.ResultSet VerificarAntFamiliares(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select haf.familiar,cie.nombre,haf.codigo from hic_antfamiliar haf,cie10 cie where haf.codigoCIE_fk=cie.codigoCIE and haf.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarAntFamiliares "+ex);
		}
		return rs;
	}
	
	
	public void InsertarToxicologia(String tipo,String Observacion,String CodPac,String CodUsu,String fecha ,String hora,String cedula){
		/**
		 * creamos las tranfuciones historicas del paciente.
		 */
	CrearAntecedentes cad = new CrearAntecedentes();
	cad.settipo(tipo);
	cad.setObservacion(Observacion);
	cad.setCodPac(CodPac);
	cad.setCodUsu(CodUsu);
	cad.setfecha(fecha);
	cad.sethora(hora);
	cad.setcedula(cedula);
	   
	try{
		PreparedStatement ps = null;
		Conexion con=new Conexion();
		ps=con.conn.prepareStatement("insert into hic_toxico(tipo,observacion,codpac_fk,codusu_fk,fecha,hora,cedpac_fk)values(?,?,?,?,?,?,?)");
		ps.setString(1, cad.gettipo());
		ps.setString(2, cad.getObservacion());		
		ps.setString(3, cad.getCodPac());
		ps.setString(4, cad.getCodUsu());
		ps.setString(5, cad.getfecha());
		ps.setString(6, cad.gethora());
		ps.setString(7, cad.getcedula());	    
		ps.executeUpdate();
		ps.close();
		con.cerrar();				
	}catch(Exception ex){
		System.out.println("Error MetodoPreingreso>>InsertarToxicologia "+ex);
	}
	   
}
	
	
	public java.sql.ResultSet VerificarToxico(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select htx.tipo,htx.observacion from hic_toxico htx where htx.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarToxico "+ex);
		}
		return rs;
	}
	
public void InsertarAlergia(String NomAlergia,String NomReaccion,String CodPac,String CodUsu,String hora,String fecha,String cedula){
		
		/**
		 * creamos la alergia de los antecedentes del paciente.
		 */
	   CrearAntecedentes cad = new CrearAntecedentes();
	   cad.setNomAlergia(NomAlergia);
	   cad.setNomReaccion(NomReaccion);
	   cad.setCodPac(CodPac);
	   cad.setCodUsu(CodUsu);
	   cad.sethora(hora);
	   cad.setfecha(fecha);
	   cad.setcedula(cedula);
	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into hic_alergias(alergia,reaccion,codpac_fk,codusu_fk,hora,fecha,cedpac_fk)values(?,?,?,?,?,?,?)");
			    ps.setString(1, cad.getNomAlergia());
			    ps.setString(2, cad.getNomReaccion());
			    ps.setString(3, cad.getCodPac());
			    ps.setString(4, cad.getCodUsu());
			    ps.setString(5, cad.gethora());
			    ps.setString(6, cad.getfecha());
			    ps.setString(7, cad.getcedula());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
				System.out.println("Error MetodoPreingreso>>InsertarAlergia "+ex);
			}

		}
	public java.sql.ResultSet VerificarAlergias(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select alergia,reaccion from hic_alergias where cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarAlergias "+ex);
		}
		return rs;
	}
	public void InsertarProcedimientos(String codigoQx,String fechaQx,String Observacion,String CodPac,String CodUsu,String fecha ,String hora,String cedula){
		/**
		 * creamos los procedimientos historicas del paciente.
		 */
	CrearAntecedentes cad = new CrearAntecedentes();
	cad.setcodigoQx(codigoQx);
	cad.setfechaQx(fechaQx);
	cad.setObservacion(Observacion);
	cad.setCodPac(CodPac);
	cad.setCodUsu(CodUsu);
	cad.setfecha(fecha);
	cad.sethora(hora);
	cad.setcedula(cedula);
	  
	   
	try{
		PreparedStatement ps = null;
		Conexion con=new Conexion();
		ps=con.conn.prepareStatement("insert into hic_quirurjicos(codigoQx_fk,fechaQx,observacionQx,codpac,codusu,fecha,hora,cedpac_fk)values(?,?,?,?,?,?,?,?)");
		ps.setString(1, cad.getcodigoQx());
		ps.setString(2, cad.getfechaQx());		
		ps.setString(3, cad.getObservacion());
		ps.setString(4, cad.getCodPac());
		ps.setString(5, cad.getCodUsu());
		ps.setString(6, cad.getfecha());
		ps.setString(7, cad.gethora());
		ps.setString(8, cad.getcedula());	    
		ps.executeUpdate();
		ps.close();
		con.cerrar();				
	}catch(Exception ex){
		System.out.println("Error MetodoPreingreso>>InsertarProcedimientos "+ex);
	}
	   
}	
	public java.sql.ResultSet VerificarQx(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select hqx.observacionQx,fechaQx,map.servicio from hic_quirurjicos hqx,mapipo map where map.codigo=hqx.codigoQx_fk and hqx.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarQx "+ex);
		}
		return rs;
	}
	
	
	public java.sql.ResultSet VerObservacionDiagnostico(String Diagnos){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select adi.observacion,cie.nombre from adm_diagnosticocola adi,cie10 cie where adi.codigo='"+Diagnos+"' and adi.codigoCIE_fk=cie.codigoCIE");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerObservacionDiagnostico "+ex);
		}
		return rs;
	}
	public java.sql.ResultSet VerificarDiagnosticos(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT cie.nombre,adi.fecha,adi.hora,adi.codigo FROM cie10 cie,adm_diagnosticocola adi WHERE cie.codigoCIE=adi.codigoCIE_fk and adi.cedpac_fk ='"+CedPac+"' order by adi.codigo desc ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>VerificarDatosAdmision "+ex);
		}
		return rs;
	}
	
	 public java.sql.ResultSet Buscaedad(String CedPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	     
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select (year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,genero from adm_paciente where numero_documento='"+CedPac+"'");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: "+ex);
	        }
	      
	        return rs;
	        
	    }
	
	public java.sql.ResultSet Examen(String CedPac){
        java.sql.ResultSet rs=null;
        Statement st = null;
      
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct lpac.nombre,lpac.primer_apellido,lpac.numero_documento,lres.fecha,lres.hora,lsub.nombre, lex.nombre,lres.resultado,lpac.pac_codigo_paciente,lsub.codigo from lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub where lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lpac.numero_documento='"+CedPac+"' and lres.codexamen_fk=lex.codigo and lpac.pac_codigo_paciente=lres.codpac_fk and lres.estado=1 and lex.codigosubarea_fk!=0 and lres.resultado!='' group by lres.fecha,lres.hora desc ");
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error_Metodo:: "+ex);
        }
      
        return rs;
        
    }
	
	 public java.sql.ResultSet ExamenRango(String CedPac,String gene){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lran.valorinicial,lran.valorfinal,lex.codigo,lpac.pac_codigo_paciente,lres.codigo from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_tipo_rango ltr where lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=1 and lran.codtiporango_fk=ltr.codigo and ltr.codexamen_fk=lex.codigo and lex.codigosubarea_fk=0 and lex.codigoarea_fk>0 and lpac.numero_documento="+CedPac+" AND ( lran.codgenero_fk="+gene+" XOR ltr.tiporango='GENERAL') and lres.estado=1 order by lres.fecha, lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo::ExamenRango "+ex);
	        }
	      
	        return rs;
	        
	    }
	
	 public java.sql.ResultSet ExamenTexto(String CedPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select distinct lpac.numero_documento,lpac.nombre,lpac.primer_apellido,lres.fecha,lres.hora,lex.nombre,lres.resultado,lex.codigo,lpac.pac_codigo_paciente,lres.codigo from adm_paciente lpac,lab_resultado lres,lab_examen lex where lpac.numero_documento='"+CedPac+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=0  and lex.codigosubarea_fk=0 and lres.estado=1 order by lres.fecha,lres.hora desc");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error_Metodo:: "+ex);
	        }
	      
	        return rs;
	        
	    }
	
	public java.sql.ResultSet Busedadygene(String CedPac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select genero,(year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,nombre,primer_apellido from adm_paciente where numero_documento='"+CedPac+"'");
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error_Metodo:: "+ex);
        }
      
        return rs;
        
    }
	public java.sql.ResultSet ExamenTexto1(String CedPac, String CodExam){
        java.sql.ResultSet rs=null;
        Statement st = null;
      
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct DATE_FORMAT(lres.fecha,'%d/%m/%y'),lres.hora,lres.resultado from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub,lab_area lar where lpac.numero_documento='"+CedPac+"' and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.tipo=0 and lex.codigo='"+CodExam+"' and lar.codigo=lsub.codarea_fk and lex.codigosubarea_fk=lsub.codigo and lres.estado=1 and lres.resultado!='' order by lres.fecha,lres.hora desc");
        	
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error MetodoExamen>>ExamenTexto1 "+ex);
        }
      
        return rs;
        
    }
	 public java.sql.ResultSet Examenom(String CodExam,String CedPac,String gene,int edad){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select lpac.numero_documento,lpac.nombre,lpac.primer_apellido,DATE_FORMAT(lres.fecha,'%d/%m/%y'),lres.hora,lex.nombre as exa,lres.resultado,lran.valorinicial,lran.valorfinal,lpac.pac_codigo_paciente,lpac.genero,replace(lun.nombre,'9','%')as unidad,lar.nombre as grupo,lsub.nombre as subgrupo,(year(curdate())-year(lpac.fecha_nacimiento)) - (right(curdate(),5) < right(lpac.fecha_nacimiento, 5)) as edad,lpac.fecha_nacimiento as nacimiento from adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_unidad lun,lab_subarea lsub,lab_area lar,lab_tipo_rango ltr where lres.estado=1 and lpac.numero_documento="+CedPac+" and lar.codigo=lsub.codarea_fk and lpac.pac_codigo_paciente=lres.codpac_fk and lres.codexamen_fk=lex.codigo and lex.codigosubarea_fk=lsub.codigo and lex.codigo='"+CodExam+"' and( (lran.edadInicial<="+edad+" and  lran.edadFinal>="+edad+") XOR  lran.codgenero_fk=2 xor ltr.tiporango='GENERAL') and lran.codtiporango_fk=ltr.codigo and ltr.codexamen_fk=lex.codigo and lun.codigo=lran.codunidad_fk order by  lres.fecha desc ");
	       	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error MetodoExamen>>Examenom "+ex);
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
	public java.sql.ResultSet Busedadygene1(String CedPac){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select genero,(year(curdate())-year(fecha_nacimiento)) - (right(curdate(),5) < right(fecha_nacimiento, 5)) as edad,nombre,primer_apellido,segundo_apellido from adm_paciente where numero_documento="+CedPac+"");
        }
        catch(Exception ex){
        	ex.getMessage();
        	System.out.println("Error MetodoExamen>>Busedadygene "+ex);
        }
      
        return rs;
        
    }
	public java.sql.ResultSet ExamenesRealizados(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select distinct lex.nombre,lex.codigo,lex.tipo from lab_examen lex,lab_resultado lres,adm_paciente lpac where lpac.numero_documento='"+CedPac+"' and lres.codpac_fk=lpac.pac_codigo_paciente and lres.codexamen_fk=lex.codigo and lres.resultado!='' and lres.resultado!='-' group by lex.nombre order by lex.nombre");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoPreingreso>>ExamenesRealizados "+ex);
		}
		return rs;
	}
	 public java.sql.ResultSet HistorialDeImagenologia(String CedPac){
	        java.sql.ResultSet rs=null;
	        Statement st = null;
	      
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select ice.fecha,ice.hora,isu.nombre,ice.codigo,sgu.usu_codigo from img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt ,adm_convenio acv,adm_entidad aen where igr.codigo=isu.cod_gruFk and isu.codigo=ice.codigoExa_fk and ipac.pac_codigo_paciente=ice.codigoPac_fk and ice.estado=1 and sdt.dat_codigo=sgu.dat_codigo_fk and sgu.usuario=ice.aprobacion and aen.ent_nit=acv.ent_nit_contratante_fk and ipac.numero_documento='"+CedPac+"' and acv.conv_numero_contrato=ipac.conv_numero_contrato_fk order by ice.fecha asc ");
	        }
	        catch(Exception ex){
	        	ex.getMessage();
	        	System.out.println("Error en MetodoPreingreso>>HistorialDeImagenologia "+ex);
	        }
	        return rs;
	    }
	
	public void ActualizarFormatosPreingreso(String CodigoFormatoPaciente){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_formatoscola set estado=1 where codigo='"+CodigoFormatoPaciente+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoPreingreso>>ActualizarFormatosPreingreso "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	public void FinalizarAtencion(String CodAdmCola){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_cola set estado=1 where col_codigo='"+CodAdmCola+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoPreingreso>>FinalizarAtencion "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	public void ActualizarPreguntasFormatosCola(String HoraFormato,String FechaFormato){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_colaresultados set estado=1 where hora='"+HoraFormato+"' and fecha='"+FechaFormato+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoPreingreso>>ActualizarPreguntasFormatosCola "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	
	
	
	public void ActualizarResultadosPreadmision(String Resul, String CodResul){		
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_colaresultados set resultados='"+Resul+"' where codigo='"+CodResul+"'");
	     	st.executeUpdate();
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoPreingreso>>ActualizarResultadosPreadmision "+ex);
	     	ex.getMessage();	     
	     }		    
	 }
	public java.sql.ResultSet VerificarDiagnosticoRepetido(String CodAdmCola,String CedPac){	   
		/**
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM adm_diagnosticocola WHERE cod_cola_fk='"+CodAdmCola+"' and cedpac_fk='"+CedPac+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>VerificarDiagnosticoRepetido "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoDiagnostico(String CodDiagnostico){	   
		/**
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE='"+CodDiagnostico+"'");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerCodigoDiagnostico "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerInsercionDiagnostico(String CodAdmCola,String fecha,String hora){	   
		/**
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT codigo FROM adm_diagnosticocola WHERE cod_cola_fk='"+CodAdmCola+"' and fecha='"+fecha+"' and hora='"+hora+"'");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerInsercionDiagnostico "+ex);
        }	
        return rs;
    }
	
	
	public void InsertarDiagnosticoCola(String codigoCIE,String CodAdmCola,String CedPac,String usuario,String fecha,String hora,String observacion){
		DiagnosticoCola dc = new DiagnosticoCola();
		dc.setcodigoCIE(codigoCIE);
		dc.setCodAdmCola(CodAdmCola);
		dc.setCedPac(CedPac);
		dc.setCodUsuario(usuario);
		dc.setfecha(fecha);
		dc.sethora(hora);
		dc.setobservacion(observacion);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into adm_diagnosticocola(codigoCIE_fk,cod_cola_fk,cedpac_fk,codigoUsuario_fk,fecha,hora,observacion)values(?,?,?,?,?,?,?)");
			    ps.setString(1, dc.getcodigoCIE());
			    ps.setString(2, dc.getCodAdmCola());
			    ps.setString(3, dc.getCedPac());
			    ps.setString(4, dc.getusuario());
			    ps.setString(5, dc.getfecha());
			    ps.setString(6, dc.gethora());
			    ps.setString(7, dc.getobservacion());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPreingreso>>InsertarDiagnosticoCola "+ex);
			}
		}
	
	public ResultSet AutocompletarCIE10(String cod) throws Exception {
		/**
		 * se buscan todos los Diagnosticos que estan en la base de datos del CIE10
		 * tiene como parametro el nombre del diagnostico.
		 */
		  java.sql.ResultSet r=null;
		  Statement st = null;
		  Conexion con=new Conexion();
		  st = con.conn.createStatement();
		 // r=st.executeQuery("SELECT codigoCIE,nombre FROM cie10 WHERE nombre LIKE '%"+cod+"%' or codigoCIE LIKE'"+cod+"%' limit 35");
		  r=st.executeQuery("SELECT * FROM (SELECT  codigoCIE,nombre,LENGTH(codigoCIE) caracteres FROM cie10 WHERE nombre LIKE '%"+cod+"%'   OR codigoCIE LIKE '%"+cod+"%' LIMIT 35 ) AS a WHERE a.caracteres>3 AND a.codigoCIE NOT LIKE '%000%'");

		  return r;
	}
public java.sql.ResultSet ObtenerUsuarioFormato(String CodArea,String FechaFormato,String HoraFormato,String CodUsuario){	   
		
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hres.codUsu_Fk,hres.estado from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,adm_colaresultados hres where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.cod_preg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and ha.codigo='"+CodArea+"' and hres.codUsu_Fk='"+CodUsuario+"'");

        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerUsuarioFormato "+ex);
        }	
        return rs;
    }
	public java.sql.ResultSet ObtenerPreguntasAreaLleno(String CodArea,String FechaFormato,String HoraFormato){	   
		/**
		 * se obtienen las preguntas del area tiene como parametro el codigo del area
		 * y devuelve como respuesta el nombre de la pregunta, el tipo de respuesta de la pregunta,y el codigo del tipo de respuesta
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados,hp.unidad,hp.patronNormal from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,adm_colaresultados hres where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.cod_preg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and ha.codigo='"+CodArea+"'");
        	//System.out.println("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hres.codigo as CodigoResultado,hres.resultados from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa,adm_colaresultados hres where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo and hres.codArea_Fk=ha.codigo and hres.cod_preg_fk=hp.codigo and hres.fecha='"+FechaFormato+"' and hres.hora='"+HoraFormato+"' and ha.codigo='"+CodArea+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerPreguntasAreaLleno "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet OpcionesTipoRespuesta2(String CodTipoResp){
		/**
		 * se obtienen las respuestas de las opciones de respuestas si estas son cerradas
		 * tiene como parametro el codigo el tipo de respuesta
		 * y devuelve como el resultado el nombre de las respuestas con su respectivo codigo.
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select hr.nombre_respuesta,hr.codigo as CodRespuesta from hic_tipo_respuesta htr,hic_condicion_respuesta hcr,hic_respuestas hr where htr.codigo=hcr.codigo_tiporespuesta_fk and hr.codigo=hcr.codigo_respuesta_fk and htr.codigo='"+CodTipoResp+"' order by hr.nombre_respuesta");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>OpcionesTipoRespuesta2 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerAreaFormato2(String CodFormato,String FechaFormato,String HoraFormato){
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
        	rs=st.executeQuery("select ha.codigo,ha.nombre_area,hafp.hora,hafp.fecha from hic_formato hf,hic_formato_area hfa,hic_area ha,adm_formatoscola hafp where hf.codigo=hfa.codigo_formato_fk and ha.codigo=hfa.codigo_area_fk and hf.codigo=hafp.codigo_for_fk and hf.codigo='"+CodFormato+"' and hafp.fecha='"+FechaFormato+"' and hafp.hora='"+HoraFormato+"'");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerAreaFormato "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormatosColaPaciente(String CedPac,String CodAdmCola){
		/**
		 * se obtienen los datos del paciente para la mostrarlos como infromacion
		 * lleva como parametro el codigo del paciente.
		**/
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select hf.codigo,hf.nombre_formato,afc.fecha,afc.hora,sdp.nombre,sdp.apellido,afc.codigo_usu_fk,afc.cedulaPac,afc.codigo,afc.estado from hic_formato hf,adm_cola ac,adm_formatoscola afc,seg_usuario sus,seg_datos_personales sdp where hf.codigo=afc.codigo_for_fk and ac.col_codigo=afc.codigo_cola_fk and ac.numero_documento =afc.cedulaPac and afc.cedulaPac='"+CedPac+"' and afc.codigo_cola_fk='"+CodAdmCola+"' and afc.codigo_usu_fk=sus.usu_codigo and sdp.dat_codigo=sus.dat_codigo_fk");
        	//System.out.println("select hf.codigo,hf.nombre_formato,afc.fecha,afc.hora,sdp.nombre,sdp.apellido from hic_formato hf,adm_cola ac,adm_formatoscola afc,seg_usuario sus,seg_datos_personales sdp where hf.codigo=afc.codigo_for_fk and ac.col_codigo=afc.codigo_cola_fk and ac.numero_documento =afc.cedulaPac and afc.cedulaPac='"+CedPac+"' and afc.codigo_cola_fk='"+CodAdmCola+"' and afc.codigo_usu_fk=sus.usu_codigo and sdp.dat_codigo=sus.dat_codigo_fk");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPaciente "+ex);
        }	
        return rs;
    }
	
	public void IngresarHistoriaCola(String CedPac,String CodAdmCola,String CodigoPregunta,String resultados,String estado,String fecha,String hora,String usuario,String CodigoArea){
		/**
		 * se ingresa a la historia clinica todos los resultados de los diferentes
		 *  formatos de la historia clinica....
		 */
		ResultadoFormCola rfc = new ResultadoFormCola();
		rfc.setCedPac(CedPac);
		rfc.setCodAdmCola(CodAdmCola);
		rfc.setCodigoPregunta(CodigoPregunta);
		rfc.setresultados(resultados);
		rfc.setestado(estado);
		rfc.setFecha(fecha);
		rfc.setHora(hora);
		rfc.setCodUsuario(usuario);
		rfc.setCodigoArea(CodigoArea);
			try{
					PreparedStatement ps = null;
				    Conexion con=new Conexion();
				    ps=con.conn.prepareStatement("insert into adm_colaresultados(cedulaPac,cod_cola_fk,cod_preg_fk,resultados,estado,fecha,hora,codUsu_fk,codArea_fk)values(?,?,?,?,?,?,?,?,?)");
				    ps.setString(1,rfc.getCedPac());
				    ps.setString(2,rfc.getCodAdmCola());
				    ps.setString(3,rfc.getCodigoPregunta());
				    ps.setString(4,rfc.getresultados());
				    ps.setString(5,rfc.getestado());
				    ps.setString(6,rfc.getFecha());
				    ps.setString(7,rfc.getHora());
				    ps.setString(8,rfc.getusuario());
				    ps.setString(9,rfc.getCodigoArea());
				    ps.executeUpdate();
					ps.close();
					con.cerrar();				
				}catch(Exception ex){
		        	System.out.println("Error en MetodoPreingreso>>IngresarHistoriaCola "+ex);
				}
			}
	
	public java.sql.ResultSet ObtenerAreaFormato(String CodFormato,String fecha,String hora){
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
        	rs=st.executeQuery("select ha.codigo,ha.nombre_area,afc.hora,afc.fecha from hic_formato hf,hic_formato_area hfa,hic_area ha,adm_formatoscola afc where hf.codigo=hfa.codigo_formato_fk and ha.codigo=hfa.codigo_area_fk and hf.codigo=afc.codigo_for_fk and hf.codigo='"+CodFormato+"' and afc.fecha='"+fecha+"' and afc.hora='"+hora+"'");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoPreingreso>>ObtenerAreaFormato "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerPreguntasArea(String CodArea){	   
		/**
		 * se obtienen las preguntas del area tiene como parametro el codigo del area
		 * y devuelve como respuesta el nombre de la pregunta, el tipo de respuesta de la pregunta,y el codigo del tipo de respuesta
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	//rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hp.codigo as CodPregunta from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_respuestas hr,hic_tipo_respuesta htr,hic_condicion_respuesta hcr where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and ha.codigo='"+CodArea+"' and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk");
        	rs=st.executeQuery("select distinct hp.nombre_pregunta,htr.tipo as TipoRespuesta,htr.codigo as CodTipoRes,hp.codigo as CodPregunta,ha.codigo as CodArea from hic_area ha,hic_area_pregunta hap,hic_pregunta hp,hic_pregunta_tiporespuesta hpt,hic_tipo_respuesta htr,hic_formato hfor,hic_formato_area hfa where ha.codigo=hap.codigo_area_fk and hp.codigo=hap.codigo_pregunta_fk and ha.codigo='"+CodArea+"' and hp.codigo=hpt.codigo_pregunta_fk and htr.codigo=hpt.codigo_tiporespuesta_fk and hfor.codigo=hfa.codigo_formato_fk and hfa.codigo_area_fk=ha.codigo");
        }
        catch(Exception ex){
        	System.out.println("Error en Metodo ObtenerPreguntasArea "+ex);
        }	
        return rs;
    }
	
	public void RelacionFormatoColaPaciente(String CodFormato,String CodAdmCola,String CedPac,String hora,String fecha,String usuario){
		/**
		 * creamos la relacion de los formatos con la admision y el paciente 
		 * lleva como parametro el codigo del formato,codigo de la admision,codigo del paciente
		 */
		
		FormatoPacCola fpc = new FormatoPacCola();
		fpc.setCodFormato(CodFormato);
		fpc.setCodAdmCola(CodAdmCola);
		fpc.setCedPac(CedPac);
		fpc.setHora(hora);
		fpc.setFecha(fecha);
		fpc.setCodUsuario(usuario);	   
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into adm_formatoscola(codigo_for_fk,codigo_cola_fk,cedulaPac,hora,fecha,codigo_usu_fk)values(?,?,?,?,?,?)");
			    ps.setString(1, fpc.getCodFormato());
			    ps.setString(2, fpc.getCodAdmCola());
			    ps.setString(3, fpc.getCedPac());
			    ps.setString(4, fpc.getHora());
			    ps.setString(5, fpc.getFecha());
			    ps.setString(6, fpc.getusuario());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoPreingreso>>RelacionFormatoColaPaciente "+ex);
			}

		}
	
	/**
	 * mostrar nombre de entidad existentes en la base de datos
	 */
			
    /*public String enviarSQLEntidad(){
    	String sql = "select nombre_entidad from adm_entidad";
		return sql;	
	}*/
    
    /**
     * ingreso de pacientes en la tabla adm_cola
     */
     
	
    public void insertar(String Documento, String Nombre, String PrimerApellido, String SegundoApellido,String Entidad , Date Fecha, Time Hora, String TipoDocumento,String Estado,String Genero,String FechaNaci){

		try{
			PreparedStatement ps = null;
		    Conexion con=new Conexion();  
		    
		    ps=con.conn.prepareStatement("insert into adm_cola(nombre,primer_apellido,segundo_apellido,numero_documento,eps,fecha_entrada,hora_entrada,tipo_documento,estado,genero,fecha_nacimiento) values(?,?,?,?,?,?,?,?,?,?,?)");				
			ps.setString(1, Nombre);
			ps.setString(2,PrimerApellido);
			ps.setString(3,SegundoApellido);
			ps.setString(4,Documento);
			ps.setString(5,Entidad);
			ps.setDate(6,Fecha);
			ps.setTime(7,Hora);
			ps.setString(8,TipoDocumento);
			ps.setString(9,Estado);
			ps.setString(10,Genero);
			ps.setString(11, FechaNaci);
			ps.executeUpdate();
			ps.close();
			con.cerrar();
		}catch(Exception ex){
			System.out.println("Error en MetodopREINGRESO>>insertar "+ex);
		}
		
		
	}
    public java.sql.ResultSet DatosPacienteCola(String CodAdmCola){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT tipo_documento,numero_documento,nombre,primer_apellido,segundo_apellido,genero,eps,fecha_nacimiento,(YEAR(CURDATE())-YEAR(fecha_nacimiento)) - (RIGHT(CURDATE(),5) < RIGHT(fecha_nacimiento, 5)) AS edad FROM adm_cola WHERE col_codigo = '"+CodAdmCola+"' ");
        }
        catch(Exception ex){
        
        }	
        return rs;
    }
    
    /**
	 * mostrar nombre de entidad existentes en la base de datos
	 */
    
    public java.sql.ResultSet SQLEntidad(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT nombre_entidad FROM adm_entidad ");
        }
        catch(Exception ex){
        
        }	
        return rs;
    }
    /**
	 * mostrar nombre de area existentes en la base de datos
	 */
    
    public java.sql.ResultSet SQLArea(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT nombre FROM adm_area ");
        }
        catch(Exception ex){
        	
        }	
        return rs;
    }
    /**
	 * mostrar nombre de subarea existentes en la base de datos
	 */
    
    public java.sql.ResultSet SQLEnt(String x){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select nombre from adm_subarea where codigoarea="+x);
        }
        catch(Exception ex){
        
        }
        return rs;
    }
    /**
	 * mostrar codgio de area segun el nombre existentes en la base de datos
	 */
  /*  public java.sql.ResultSet SQLEnto(String x){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	String urg="URGENCIAS";
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	String sql ="select codigo from adm_area where nombre ='"+urg+"'";
        	
        	rs=st.executeQuery(sql);
        	
        }
        catch(Exception ex){
        	
        }
        return rs;
    }*/
    /**
	 * mostrar codgio de area segun el nombre existentes en la base de datos
	 */
   public ResultSet CargarCodigo(String par){
    	ResultSet rs = null;
    	PreparedStatement st=null;
    	try{
    		Conexion con = new Conexion();
    		st = con.conn.prepareStatement("select codigo from adm_area where nombre = ?");
    		
    		st.setString(1, par);
    		rs=st.executeQuery();
    		
    	}
    	catch(SQLException ex){}
    	catch(Exception e1){}
    	return rs;
    }

    /**
	 * mostrar codgio de subarea segun el nombre existentes en la base de datos
	 */
    public ResultSet SQLEntx(String y, String xx){
        ResultSet rs=null;
        PreparedStatement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.prepareStatement("select codigo from adm_subarea where nombre = ?");
        	st.setString(1, y);
        	rs=st.executeQuery();
        	
        	String xsx = "";
            while(rs.next()){
				xsx=rs.getString(1);
			}
            st = con.conn.prepareStatement("select codigocama from adm_censo_cama where codsubarea = ? and est_codigo_estado_fk=1");	
            st.setString(1, xsx);
            rs=st.executeQuery();
           	}
        catch(Exception ex){
        	
        }
        return rs;
    }
    /**
	 * obtener datos de los pacientes por atender en el triage
	 */
    public java.sql.ResultSet obtenerCola(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select primer_apellido,segundo_apellido,tipo_documento,numero_documento,nombre,eps,fecha_entrada,hora_entrada,col_codigo from adm_cola where estado=0");
        }
        catch(Exception ex){
        	
        }
        return rs;
    }
    /**
	 * mostrar codgio de area segun el nombre existentes en la base de datos segun el numero de documento
	 */
    
    public java.sql.ResultSet SQLEPS(String numero,String Tipo){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("select eps,numero_documento,fecha_entrada,hora_entrada,tipo_documento,estado,primer_apellido,segundo_apellido,nombre,col_codigo from adm_cola where numero_documento='"+numero+"' and tipo_documento='"+Tipo+"' ");
        }
        catch(Exception ex){
        	
        }
      
        return rs;
        
    }
    /**
	 * actualizar la cola en estado 1 cual numero es el digitado por el paciente
	 */
    public  void actualizarCola(String che){
 	   
        PreparedStatement st;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.prepareStatement("update adm_cola set estado=1 where numero_documento='"+che+"'");
        	st.executeUpdate();
        	st.close();
        	con.conn.commit();
        	con.conn.close();
        	
        }
        catch(Exception ex){
        	
        }	
       
    }
    
    
}
