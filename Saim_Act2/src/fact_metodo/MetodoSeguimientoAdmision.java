package fact_metodo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;
import fact_bean.SeguimientoAdmision;

public class MetodoSeguimientoAdmision {
	
	public java.sql.ResultSet ObtenerDetalleOrden(String CodOrdLab_fk,String CodEstudio){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT hdl.codigo AS CodDetalle FROM hic_detalleordenlaboratorio hdl WHERE hdl.CodOrdLab_fk="+CodOrdLab_fk+" AND CodExa_fk="+CodEstudio+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerDetalleOrden "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDetalleOrdenImg(String CodOrdImg,String CodEstudio){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT hdi.codigo AS CodDetalle  FROM hic_detalleordenimagenes hdi  WHERE hdi.CodOrdImg_fk="+CodOrdImg+" AND hdi.CodEstIma_fk="+CodEstudio+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerDetalleOrdenImg "+ex);
        }	
        return rs;
    }
	
	public void ActualizarDetalleOrden(String CodOrdenLab,String observacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_orden SET observacion='"+observacion+"' WHERE codigo='"+CodOrdenLab+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoSeguimientoAdmision>>ActualizarDetalleOrden "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	public void OmitirOrdenVacia(String CodOrdenLab){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM hic_orden WHERE codigo="+CodOrdenLab+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoSeguimientoAdmision>>OmitirOrdenVacia "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirDetOrdLab(String CodDetOrdLab){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM hic_detalleordenlaboratorio WHERE codigo="+CodDetOrdLab+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoSeguimientoAdmision>>OmitirDetOrdLab "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirDetOrdImg(String CodDetOrdImg){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement(" DELETE FROM hic_detalleordenimagenes WHERE codigo="+CodDetOrdImg+" ");
	     	System.out.println(" DELETE FROM hic_detalleordenimagenes WHERE codigo="+CodDetOrdImg+" ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("Error en MetodoSeguimientoAdmision>>OmitirDetOrdImg "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public java.sql.ResultSet ObtenerDetalleOrden(String CodOrdLab_fk){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdl.codigo AS CodDetalle,lsa.nombre,hdl.observacion FROM hic_orden hod,adm_paciente ap,lab_area la,lab_subarea lsa,hic_detalleordenlaboratorio hdl,adm_admisiones aad WHERE ap.pac_codigo_paciente=hod.CodPac_fk AND la.codigo=lsa.codarea_fk AND lsa.codigo=hdl.CodExa_fk AND hod.codigo=hdl.CodOrdLab_fk AND aad.adm_numero_ingreso=hod.CodAdm_fk AND hod.codigo="+CodOrdLab_fk+" UNION SELECT hdl.codigo AS CodDetalle,lex.nombre,hdl.observacion FROM hic_orden hod,adm_paciente ap,lab_examen lex,hic_detalleordenlaboratorio hdl,adm_admisiones aad WHERE lex.codigo=hdl.CodExa_fk AND lex.codigosubarea_fk=0 AND hod.CodPac_fk=ap.pac_codigo_paciente AND hod.codigo=hdl.CodOrdLab_fk AND aad.adm_numero_ingreso=hod.CodAdm_fk AND hod.codigo="+CodOrdLab_fk+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerDetalleOrden "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerDetalleOrdenProcedi(String CodOrdLab_fk){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdi.codigo AS CodDetalle,mp.servicio,hdi.observacion FROM  hic_orden hod, adm_paciente ap, mapipo mp, hic_detalleordenimagenes hdi, adm_admisiones aad WHERE ap.pac_codigo_paciente = hod.CodPac_fk   AND mp.codigo = hdi.CodEstIma_fk   AND hod.codigo = hdi.CodOrdImg_fk   AND aad.adm_numero_ingreso = hod.CodAdm_fk   AND hod.codigo = "+CodOrdLab_fk+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerDetalleOrdenProcedi "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerDetalleOrdenImg(String CodOrdLab_fk){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdi.codigo AS CodDetalle,isub.nombre,hdi.observacion FROM hic_orden hod,adm_paciente ap,img_subexa isub,hic_detalleordenimagenes hdi,adm_admisiones aad WHERE ap.pac_codigo_paciente=hod.CodPac_fk AND isub.codigo=hdi.CodEstIma_fk AND hod.codigo=hdi.CodOrdImg_fk AND aad.adm_numero_ingreso=hod.CodAdm_fk AND hod.codigo="+CodOrdLab_fk+" ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerDetalleOrden "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerCodigoOrden(Time hora,Date fecha){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT codigo FROM hic_orden WHERE hora='"+hora+"' AND fecha='"+fecha+"' ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerCodigoOrden "+ex);
        }	
        return rs;
    }

	
	public java.sql.ResultSet ObtenerUnidadesInfusion(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM hic_unidad_infusion ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerUnidadesInfusion "+ex);
        }	
        return rs;
    }
	
/*	public java.sql.ResultSet Empresa(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM empresa ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerUnidadesInfusion "+ex);
        }	
        return rs;
    }*/
	
	public java.sql.ResultSet ObtenerSolventes(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT med.codigo,CONCAT(med.nombre,'-',med.concentracion,'-',fu.sigla) AS Solvente FROM medicamentos med,farc_unidades fu WHERE med.solvente='1' AND fu.codigo=med.cod_unidadFK ORDER BY Solvente ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerUnidadesInfusion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerViaAdministracion(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM hic_via_administracion ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerViaAdministracion "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerMedicamentoCardex(String NombreMedicamento){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM medicamentos WHERE cod_grupoFK=1 AND  estado=1 AND nombre LIKE '%"+NombreMedicamento+"%' GROUP BY nombre  LIMIT 10  ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerFormasFarmaceuticas(String NombreMedicamento){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT med.nombre,ff.forma_farmaceutica,ff.codigo FROM medicamentos med,farc_formafarmaceutica ff WHERE med.cod_grupoFK=1 AND  med.estado=1 AND  ff.codigo=med.cod_formaFK AND med.nombre = '"+NombreMedicamento+"' GROUP BY ff.forma_farmaceutica ");
        	//System.out.println("SELECT med.nombre,ff.forma_farmaceutica,ff.codigo FROM medicamentos med,farc_formafarmaceutica ff WHERE med.cod_grupoFK=1 AND  ff.codigo=med.cod_formaFK AND med.nombre = '"+NombreMedicamento+"' GROUP BY ff.forma_farmaceutica ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerFormasFarmaceuticas "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerConcentracion(String NombreMedicamento,String CodFormaFarmaceutica){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT concentracion FROM medicamentos WHERE cod_grupoFK=1 AND  estado=1 AND estado=1 AND nombre = '"+NombreMedicamento+"' AND cod_formaFK='"+CodFormaFarmaceutica+"'");
        	System.out.println("SELECT concentracion FROM medicamentos WHERE cod_grupoFK=1 AND  estado=1 AND estado=1 AND nombre = '"+NombreMedicamento+"' AND cod_formaFK='"+CodFormaFarmaceutica+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerFormasFarmaceuticas "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerMedicamento(String NombreMedicamento,String CodFormaFarmaceutica,String Concentracion,String UnidadMed){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT med.* FROM medicamentos med WHERE  med.nombre = '"+NombreMedicamento+"' AND med.cod_formaFK='"+CodFormaFarmaceutica+"' AND med.concentracion='"+Concentracion+"' AND med.cod_unidadFK='"+UnidadMed+"'");
        	//System.out.println("SELECT concentracion FROM medicamentos WHERE estado=1 AND nombre = '"+NombreMedicamento+"' AND cod_formaFK='"+CodFormaFarmaceutica+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerMedicamento "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerMedicamentoOrdenProduccion(String CodAdmK,String CodMedK,String Fecha){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fmo.codigo AS cod_med,hdf.codigo AS cod_det_form,acc.ubicacion_cama,acc.codigocama,fu.sigla FROM medicamentos med,farc_med_orden_produccion fmo,hic_detalle_formulacion hdf,adm_censo_cama acc,hic_formulacion hf,farc_unidades fu WHERE hf.codigo = hdf.CodFormulacion_fk AND fmo.cod_medfk = med.codigo AND hdf.estado='1' AND hf.fecha='"+Fecha+"' AND med.codigo = fmo.cod_medfk AND hdf.CodMedicamento_fk = med.codigo AND hdf.CodMedicamento_fk = fmo.cod_medfk AND hf.CodAdm_fk = '"+CodAdmK+"' AND hdf.CodMedicamento_fk = '"+CodMedK+"'  AND acc.cen_numero_cama = hf.CodCama_fk  AND hf.CodSubarea_fk = acc.codsubarea  AND fu.codigo = med.cod_unidadFK ");
        	rs=st.executeQuery("SELECT fmo.codigo AS cod_med,hdf.codigo AS cod_det_form,acc.ubicacion_cama,acc.codigocama,fu.sigla FROM medicamentos med,farc_med_orden_produccion fmo,hic_detalle_formulacion hdf,adm_censo_cama acc,hic_formulacion hf,farc_unidades fu WHERE hf.codigo = hdf.CodFormulacion_fk AND fmo.cod_medfk = med.codigo AND hdf.estado='1' AND hf.fecha='"+Fecha+"' AND med.codigo = fmo.cod_medfk AND hdf.CodMedicamento_fk = med.codigo AND hdf.CodMedicamento_fk = fmo.cod_medfk AND hf.CodAdm_fk = '"+CodAdmK+"' AND hdf.CodMedicamento_fk = '"+CodMedK+"'  AND acc.cen_numero_cama = hf.CodCama_fk  AND hf.CodSubarea_fk = acc.codsubarea  AND fu.codigo = med.cod_unidadFK ");
        	//System.out.println("SELECT concentracion FROM medicamentos WHERE estado=1 AND nombre = '"+NombreMedicamento+"' AND cod_formaFK='"+CodFormaFarmaceutica+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerMedicamentoOrdenProduccion "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet ObtenerUnidad(String NombreMedicamento,String CodFormaFarmaceutica,String Concentracion){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT fu.codigo,fu.sigla FROM medicamentos med,farc_unidades fu WHERE med.cod_grupoFK=1 AND med.estado=1 AND fu.codigo=med.cod_unidadFK AND med.nombre = '"+NombreMedicamento+"' AND med.cod_formaFK='"+CodFormaFarmaceutica+"' AND med.concentracion='"+Concentracion+"'");
        	System.out.print("SELECT fu.codigo,fu.sigla FROM medicamentos med,farc_unidades fu WHERE med.cod_grupoFK=1 AND med.estado=1 AND fu.codigo=med.cod_unidadFK AND med.nombre = '"+NombreMedicamento+"' AND med.cod_formaFK='"+CodFormaFarmaceutica+"' AND med.concentracion='"+Concentracion+"'");
        	//System.out.println("SELECT concentracion FROM medicamentos WHERE estado=1 AND nombre = '"+NombreMedicamento+"' AND cod_formaFK='"+CodFormaFarmaceutica+"'");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerUnidad "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet ObtenerLapsosMed(){
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery(" SELECT * FROM hic_frecuencia ");
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoSeguimientoAdmision>>ObtenerViaAdministracion "+ex);
        }	
        return rs;
    }
	
	public void CrearDetalleOrdenLaboraCodOrd(String CodOrdLab_fk,String CodExa_fk){
		/**
		 */		
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement(" INSERT INTO hic_detalleordenlaboratorio(CodOrdLab_fk,CodExa_fk)VALUES(?,?)  ");
			ps.setString(1,CodOrdLab_fk);
			ps.setString(2,CodExa_fk);			 
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoSeguimientoAdmision>>CrearDetalleOrdenLaboraCodOrd "+ex);
		}

	}
	
	public void CrearDetalleOrdenLaboraelse1(String CodOrdLab_fk,String CodExa_fk){
		/**
		 */		
		try{			
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement(" INSERT INTO hic_detalleordenlaboratorio(CodOrdLab_fk,CodExa_fk)VALUES(?,?)  ");
			ps.setString(1,CodOrdLab_fk);
			ps.setString(2,CodExa_fk);			 
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoSeguimientoAdmision>>CrearDetalleOrdenLaboraelse1 "+ex);
		}

	}
	
	public void CrearDetalleOrdenLabora(String CodOrdLab_fk,String CodExa_fk, String ObservacionExamen){
		/**
		 */		
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement(" INSERT INTO hic_detalleordenlaboratorio(CodOrdLab_fk,CodExa_fk,observacion)VALUES(?,?,?)  ");
			ps.setString(1,CodOrdLab_fk);
			ps.setString(2,CodExa_fk);
			ps.setString(3, ObservacionExamen);
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoSeguimientoAdmision>>CrearDetalleOrdenLabora "+ex);
		}

	}
	
	public void CrearDetalleOrdenImg(String CodOrdLab_fk,String CodExa_fk, String ObservacionExamen){
		/**
		 */		
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement(" INSERT INTO hic_detalleordenimagenes(CodOrdImg_fk,CodEstIma_fk,observacion)VALUES(?,?,?)  ");
			ps.setString(1,CodOrdLab_fk);
			ps.setString(2,CodExa_fk);
			ps.setString(3, ObservacionExamen);
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
		}catch(Exception ex){
			System.out.println("ERROR EN MetodoSeguimientoAdmision>>CrearDetalleOrdenImg "+ex);
		}

	}
	
	public void CrearOrdenEstudios(String codPac_fk,String codAdm_fk,String codUsu_fk,String observacion,Time hora,Date fecha,String TipoOrden){
		/**
		 * creamos la CrearRelacionLaboratoriosAdmision.
		 */		
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_orden(CodPac_fk,CodAdm_fk,CodUsu_fk,observacion,hora,fecha,TipoOrden)VALUES(?,?,?,?,?,?,?)");
			    ps.setString(1,codPac_fk);
			    ps.setString(2,codAdm_fk);
			    ps.setString(3,codUsu_fk);
			    ps.setString(4,observacion);
			    ps.setTime(5,hora);
			    ps.setDate(6,fecha);
			    ps.setString(7,TipoOrden);
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoSeguimientoAdmision>>CrearOrdenEstudios "+ex);
			}

		}
	
	public void CrearRelacionImagenologiaAdmision(String codIma_fk,String codAdm_fk,String codPac_fk,String codUsu_fk,String hora,String fecha){
		/**
		 * creamos la CrearRelacionImagenologiaAdmision.
		 */
		SeguimientoAdmision sa = new SeguimientoAdmision();
		sa.setcodIma_fk(codIma_fk);
		sa.setcodAdm_fk(codAdm_fk);
		sa.setcodPac_fk(codPac_fk);
		sa.setcodUsu_fk(codUsu_fk);
		sa.sethora(hora);
		sa.setfecha(fecha);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_imageadmi(codIma_fk,codAdm_fk,codPac_fk,codUsu_fk,hora,fecha)values(?,?,?,?,?,?)");
			    ps.setString(1,sa.getcodIma_fk());
			    ps.setString(2,sa.getcodAdm_fk());
			    ps.setString(3,sa.getcodPac_fk());
			    ps.setString(4,sa.getcodUsu_fk());
			    ps.setString(5,sa.gethora());
			    ps.setString(6,sa.getfecha());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoSeguimientoAdmision>>CrearRelacionImagenologiaAdmision "+ex);
			}

		}
	
	public void CrearRelacionDiagnosticoAdmision(String CodDiagnostico,String codAdm_fk,String codPac_fk,String codUsu_fk,String hora,String fecha){
		/**
		 * creamos la CrearRelacionDiagnosticoAdmision.
		 */
		SeguimientoAdmision sa = new SeguimientoAdmision();
		sa.setCodDiagnostico(CodDiagnostico);
		sa.setcodAdm_fk(codAdm_fk);
		sa.setcodPac_fk(codPac_fk);
		sa.setcodUsu_fk(codUsu_fk);
		sa.sethora(hora);
		sa.setfecha(fecha);
		try{
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("insert into fact_diagadmi(codDiag_fk,codAdm_fk,codPac_fk,codUsu_fk,hora,fecha)values(?,?,?,?,?,?)");
			    ps.setString(1,sa.getCodDiagnostico());
			    ps.setString(2,sa.getcodAdm_fk());
			    ps.setString(3,sa.getcodPac_fk());
			    ps.setString(4,sa.getcodUsu_fk());
			    ps.setString(5,sa.gethora());
			    ps.setString(6,sa.getfecha());
			 	ps.executeUpdate();
				ps.close();
				con.cerrar();				
			}catch(Exception ex){
	        	System.out.println("ERROR EN MetodoSeguimientoAdmision>>CrearRelacionDiagnosticoAdmision "+ex);
			}

		}
	
}
