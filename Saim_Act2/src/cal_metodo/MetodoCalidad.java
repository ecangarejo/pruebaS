package cal_metodo;

import java.sql.Statement;

import adm_logica.Conexion;

public class MetodoCalidad {

	
	
	public java.sql.ResultSet MorbilidadUrgenciaConsolidado(String FechaIN,String FechaFI){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select count(ci.codigoCIE) as Cantidad,concat(ci.nombre) as Dx,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='0' and aad.aurg='1' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE order by Cantidad desc limit 20 ");
	        	System.out.println("select count(ci.codigoCIE) as Cantidad,concat(ci.nombre) as Dx,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='0' and aad.aurg='1' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE order by Cantidad desc limit 20 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadUrgenciaConsolidado "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet MorbilidadConsultaExternaConsolidado(String FechaIN,String FechaFI){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select count(ci.codigoCIE) as Cantidad,concat(ci.nombre) as Dx,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE order by Cantidad desc limit 30 ");
	        	System.out.println("select count(ci.codigoCIE) as Cantidad,concat(ci.nombre) as Dx,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE order by Cantidad desc limit 30 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadHospitalizacionConsolidado "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet MorbilidadHospitalizacionConsolidado(String FechaIN,String FechaFI){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select count(ci.codigoCIE) as Cantidad,concat(ci.nombre) as Dx,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE order by Cantidad desc limit 20 ");
	        	System.out.println("select count(ci.codigoCIE) as Cantidad,concat(ci.nombre) as Dx,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE order by Cantidad desc limit 20 ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadHospitalizacionConsolidado "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet MorbilidadUrgencia(String FechaIN,String FechaFI,String Sql){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select sum(a.Cantidad) as Total,a.* from(select apa.genero,count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='0' and aad.aurg='1' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE,edad,apa.genero) as a "+Sql);
	        	System.out.println("select sum(a.Cantidad) as Total,a.* from(select apa.genero,count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='0' and aad.aurg='1' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE,edad,apa.genero) as a "+Sql);
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadUrgencia "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet EspecialidadesMorbilidadCex(String FechaIN,String FechaFI){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a group by a.ocupacion");
	        	System.out.println("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a group by a.ocupacion");	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadUrgencia "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet MorbilidadHospitalizacion(String FechaIN,String FechaFI,String Sql){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select sum(a.Cantidad) as Total,a.* from(select apa.genero,count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE,edad,apa.genero) as a "+Sql);
	        	System.out.println("select sum(a.Cantidad) as Total,a.* from(select apa.genero,count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk!='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE,edad,apa.genero) as a "+Sql);
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadHospitalizacion "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet MorbilidadConsultaExternaC(String FechaIN,String FechaFI,String Sql){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select sum(a.Cantidad) as Total,a.* from(select apa.genero,count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE,edad,apa.genero) as a "+Sql);
	        	System.out.println("select sum(a.Cantidad) as Total,a.* from(select apa.genero,count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,ci.codigoCIE from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.ahosp='1' and aad.aurg='0' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk group by ci.codigoCIE,edad,apa.genero) as a "+Sql);
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadConsultaExternaC "+ex);
	            }	
	            return rs;
	}
	/**********************************************************************************************/
	public java.sql.ResultSet ConsultaReporte2193UrgenciaGeneral(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Consultas de medicina general urgentes realizadas' AS Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
					" and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
					" and fdf.tipopop='1' and (fdf.cod_programa='890701' or fdf.cod_programa='I39134' or fdf.cod_programa='39145' or fdf.cod_programa='CON001') " +
			" GROUP BY  aen.regimen");
			System.out.println("select 'Consultas de medicina general urgentes realizadas' AS Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
					" and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
					" and fdf.tipopop='1' and (fdf.cod_programa='890701' or fdf.cod_programa='I39134' or fdf.cod_programa='39145' or fdf.cod_programa='CON001') " +
			" GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193UrgenciaGeneral "+ex);
		}	
		return rs;
	}
	public java.sql.ResultSet ConsultaReporte2193PacientesObservacion(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("SELECT con.Programa,con.regimen,COUNT(con.adm_numero_ingreso) AS cantidad  FROM(SELECT 'Pacientes en Observación' AS Programa,aen.regimen,aad.adm_numero_ingreso,  "
					+ " aad.fecha_registro,aad.hora_registro,hdp.fecha,hdp.hora, "
					+ " CONCAT(TIMEDIFF((CONCAT(hdp.fecha,' ',hdp.hora)),(CONCAT(aad.fecha_registro,' ',aad.hora_registro))),'' )AS horas , "
					+ " SUBSTRING_INDEX(CONCAT(TIMEDIFF((CONCAT(hdp.fecha,' ',hdp.hora)),(CONCAT(aad.fecha_registro,' ',aad.hora_registro))),''),':',1)AS horasS "
					+ " FROM adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,hic_destinopaciente hdp "
					+ " WHERE aad.pac_codigo_paciente_fk = ap.pac_codigo_paciente "
					+ " AND ap.conv_numero_contrato_fk = acv.conv_numero_contrato "
					+ " AND acv.ent_nit_contratante_fk = aen.ent_nit "
					+ " AND aad.estado != '2' AND aurg = '1' AND ahosp = '0' "
					+ " AND aad.fecha_registro BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' "
					+ " AND hdp.codAdm=aad.adm_numero_ingreso) AS con WHERE con.horasS<=6 GROUP BY con.regimen  ");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193PacientesObservacion "+ex);
		}	
		return rs;
	}
	
	/*public java.sql.ResultSet ConsultaReporte2193PacientesObservacion(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select con.Programa,con.regimen,sum(con.cantidad)as cantidad from(select 'Pacientes en Observación' as Programa,aen.regimen,count(aad.adm_numero_ingreso) as cantidad " +
					" from adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen " +
					" where aad.pac_codigo_paciente_fk=ap.pac_codigo_paciente  " +
					" and ap.conv_numero_contrato_fk=acv.conv_numero_contrato " +
					" and acv.ent_nit_contratante_fk=aen.ent_nit " +
					" and aad.estado!='2' and aurg='1' and ahosp='0' " +
					" and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' " +
					" group by aen.regimen " +
					" union " +
					" select 'Pacientes en Observación' as Programa,aen.regimen,count(aad.adm_numero_ingreso) as cantidad " +
					" from adm_admisiones aad,adm_paciente ap,adm_convenio acv,adm_entidad aen,adm_trasladourg_hosp ath " +
					" where aad.pac_codigo_paciente_fk=ap.pac_codigo_paciente " +
					" and ap.conv_numero_contrato_fk=acv.conv_numero_contrato " +
					" and acv.ent_nit_contratante_fk=aen.ent_nit " +
					" and aad.estado!='2' " +
					" and ath.CodAdm=aad.adm_numero_ingreso " +
					" and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' group by aen.regimen) as con group by con.Programa,con.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193PacientesObservacion "+ex);
		}	
		return rs;
	}*/
	
	public java.sql.ResultSet ConsultaReporte2193Citologias(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Citologías cervicovaginales tomadas' AS Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk and fef.cod_eps=aen.ent_nit " +
					" and fef.estado!='5' and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
					" and fdf.tipopop='1' and fdf.cod_programa='898001' " +
			" GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193Citologias "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193UrgenciaEspecializada(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Consultas de medicina especializada urgentes realizadas' AS Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
					" and fdf.tipopop='1' and (fdf.cod_programa='890702' or fdf.cod_programa='36100' or fdf.cod_programa='I39135' or fdf.cod_programa='890402' or fdf.cod_programa='39140' or fdf.cod_programa='39132') " +
			" GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193UrgenciaEspecializada "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193Ambulatoria(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Consultas de medicina especializada electivas realizadas' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
					" and fdf.tipopop='1' and (fdf.cod_programa='39143' or fdf.cod_programa='I39132' or fdf.cod_programa='890202' or fdf.cod_programa='890302') " +
			" GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193Ambulatoria "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193OtrasConsultasAmbulatorias(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Otras consultas electivas realizadas por profesionales diferentes a médico, enfermero u odontólogo (Incluye Psicología, Nutricionista, Optometria y otras)' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_enc_factura fef,adm_entidad aen,fact_det_factura fdf where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' and fdf.tipopop='1' " +
					" and (fdf.cod_programa='890308' or fdf.cod_programa='890206' or fdf.cod_programa='890208' or fdf.cod_programa='890306') " +
			"GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193OtrasConsultasAmbulatorias "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193Laboratorios(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Exámenes de laboratorio' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_enc_factura fef,adm_entidad aen,fact_det_factura fdf where fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
					" and fef.cod_eps=aen.ent_nit and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
			" and fdf.tipopop='1' and fdf.clase_servicio='LABORATORIO' GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193Laboratorios "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193Imagenologia(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Número de imágenes diagnósticas tomadas' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_enc_factura fef,adm_entidad aen,fact_det_factura fdf where fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
					" and fef.cod_eps=aen.ent_nit and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
			" and fdf.tipopop='1' and fdf.clase_servicio='IMAGENOLOGIA' GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193Imagenologia "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193TerapiasRespiratorias(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Número de sesiones de terapias respiratorias realizadas' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
					" and fef.cod_eps=aen.ent_nit and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' " +
					" and fdf.tipopop='1' and (fdf.cod_programa='939400' or fdf.cod_programa='29117') " +
			"GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193TerapiasRespiratorias "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193TerapiasFisicas(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select 'Número de sesiones de terapias físicas realizadas' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
					" and fef.cod_eps=aen.ent_nit and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"' and fdf.tipopop='1' " +
			" and (fdf.cod_programa='931000' or fdf.cod_programa='29112') GROUP BY  aen.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193TerapiasFisicas "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193PartosNormales(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("SELECT sum(con.cantidad) as cantidad,con.Programa,con.regimen FROM( " +
					" select 'Partos vaginales' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk  " +
					" and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"'  " +
					" and fdf.tipopop='1'  " +
					" and (fdf.cod_programa='735300') " +
					" GROUP BY  aen.regimen " +
					" UNION " +
					" select 'Partos vaginales' as Programa,a.regimen,count(a.cantidad) as total from " +
					" (select aen.regimen,fdf.nombre_programa,sum(fdf.cantidad) as cantidad,fdf.clase_servicio,fdf.nombre_paquete,fdf.cod_paquete " + 
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where  " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk  " +
					" and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"'  and fdf.tipopop='2'  " +
					" and (fdf.cod_paquete='735300' or fdf.cod_paquete='12101') " +
					" group by fdf.nombre_paquete,fdf.fecha,fdf.hora order by fdf.nombre_paquete) as a  " +
					" group by a.regimen ) AS con group by con.Programa,con.regimen");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193PartosNormales "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193Cesareas(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("SELECT sum(con.cantidad) as cantidad,con.Programa,con.regimen FROM( "+
					" select 'Partos por cesárea' as Programa,aen.regimen,sum(fdf.cantidad) as cantidad " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where  " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk  " +
					" and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"'  " +
					" and fdf.tipopop='1'  " +
					" and (fdf.cod_programa='740100' OR fdf.cod_programa='12110') " +
					" GROUP BY  aen.regimen " +
					" union " +
					" select 'Partos por cesárea' as Programa,a.regimen,count(a.cantidad) as total from " +
					" (select aen.regimen,fdf.nombre_programa,sum(fdf.cantidad) as cantidad,fdf.clase_servicio,fdf.nombre_paquete,fdf.cod_paquete " + 
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where  " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk  " +
					" and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"'  and fdf.tipopop='2' " + 
					" and (fdf.cod_paquete='740100' or fdf.cod_paquete='12110') " +
					" group by fdf.nombre_paquete,fdf.fecha,fdf.hora order by fdf.nombre_paquete) as a " + 
					" group by a.regimen ) AS con group by con.Programa,con.regimen ");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193Cesareas "+ex);
		}	
		return rs;
	}
	
	public java.sql.ResultSet ConsultaReporte2193CirugiasSinCesareasyPartosvaginales(String FechaIN,String FechaFI){
		java.sql.ResultSet rs=null;
		Statement st = null;
		try{
			Conexion con=new Conexion();
			st = con.conn.createStatement();
			rs=st.executeQuery("select a.nombre_programa as Programa,count(a.cantidad) as cantidad,a.regimen from( " +
					" select aen.regimen,fdf.cod_paquete,fdf.nombre_programa,sum(fdf.cantidad) as cantidad,fdf.clase_servicio,fdf.nombre_paquete " +
					" from fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen where " +
					" fef.cod_enc_factura=fdf.cod_enc_factura_fk " +
					" and fef.cod_eps=aen.ent_nit " +
					" and fdf.fecha_realizacion between '"+FechaIN+"' and '"+FechaFI+"'  " +
					" and fdf.tipopop='2'  " +
					" and (fdf.cod_paquete!='735300' and fdf.cod_paquete!='12101' and fdf.cod_paquete!='740100' and fdf.cod_paquete!='12110') " +
					" and (fdf.nombre_programa like '%GRUPO%' OR fdf.nombre_programa like '%UVR%') " +
					" group by fdf.nombre_paquete,fdf.fecha,fdf.hora order by fdf.nombre_paquete) as a  " +
					" group by a.nombre_programa,a.regimen order by a.nombre_programa");
		}catch(Exception ex){
			System.out.println("Error en MetodoCalidad>>ConsultaReporte2193CirugiasSinCesareasyPartosvaginales "+ex);
		}	
		return rs;
	}
	
	/************************************************************************************************/
	public java.sql.ResultSet ReporteProduccionServicio(String FechaIN,String FechaFI){

		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT a.nombre_programa,COUNT(a.cantidad) AS total,a.nombre_paquete,a.cod_paquete,a.regimen FROM (SELECT fdf.nombre_programa,SUM(fdf.cantidad) AS cantidad,fdf.clase_servicio,fdf.nombre_paquete,fdf.cod_paquete,aen.regimen FROM fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_eps=aen.ent_nit AND fdf.fecha_realizacion BETWEEN '"+FechaIN+"'  AND '"+FechaFI+"' AND fdf.tipopop = '2' GROUP BY fdf.nombre_paquete,fdf.fecha,fdf.hora ORDER BY fdf.nombre_paquete) AS a GROUP BY a.nombre_paquete,a.regimen ORDER BY a.nombre_paquete ");
	        	//System.out.println("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion,apa.genero from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a "+Sql);	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>ReporteProduccionServicio "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet ReporteProduccionServicioConsolidado(String FechaIN,String FechaFI){

		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT a.nombre_programa,COUNT(a.cantidad) AS total,a.nombre_paquete,a.regimen FROM (SELECT fdf.nombre_programa,SUM(fdf.cantidad) AS cantidad,fdf.clase_servicio,fdf.nombre_paquete,aen.regimen FROM fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen WHERE fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_eps=aen.ent_nit AND fdf.fecha_realizacion BETWEEN '"+FechaIN+"' AND '"+FechaFI+"' AND fdf.tipopop = '2' GROUP BY fdf.nombre_paquete,fdf.fecha,fdf.hora ORDER BY fdf.nombre_paquete) AS a GROUP BY a.nombre_programa,a.regimen ORDER BY a.nombre_programa ");
	        	//System.out.println("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion,apa.genero from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a "+Sql);	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>ReporteProduccionServicioConsolidado "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet ReporteProduccionProgramas(String FechaIN,String FechaFI){

		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT fdf.cod_programa,fdf.cod_programa,fdf.nombre_programa,SUM(fdf.cantidad) AS cantidad,fdf.clase_servicio,aen.regimen FROM fact_det_factura fdf,fact_enc_factura fef,adm_entidad aen WHERE  fef.cod_enc_factura=fdf.cod_enc_factura_fk AND fef.cod_eps=aen.ent_nit AND fdf.fecha_realizacion BETWEEN '"+FechaIN+"' AND '"+FechaFI+"'  AND fdf.tipopop = '1' AND fdf.clase_servicio != 'MEDICAMENTOS' AND fdf.clase_servicio != 'MATERIALES' GROUP BY fdf.nombre_programa,aen.regimen ORDER BY fdf.nombre_programa ASC,cantidad DESC ");
	        	//System.out.println("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion,apa.genero from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a "+Sql);	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>ReporteProduccionProgramas "+ex);
	            }	
	            return rs;
	}
	
	
	public java.sql.ResultSet MorbilidadConsultaExterna(String FechaIN,String FechaFI,String Sql){
    	System.out.println("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion,apa.genero from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a "+Sql);	        	

		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion,apa.genero from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a "+Sql);
	        	//System.out.println("select sum(a.cantidad) as Total ,a.* from(select count(ci.codigoCIE) as Cantidad,concat(ci.codigoCIE,' ',ci.nombre) as Dx,(YEAR('"+FechaIN+"')-YEAR(apa.fecha_nacimiento))-(RIGHT('"+FechaIN+"',5)<RIGHT(apa.fecha_nacimiento, 5)) AS edad,sdp.ocupacion,apa.genero from adm_admisiones aad,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apa,seg_usuario su,seg_datos_personales sdp where aad.adm_numero_ingreso=hde.codadm and ci.codigo=hde.CodDiag_fk and aad.cen_numero_cama_fk='1099' and aad.fecha_registro between '"+FechaIN+"' and '"+FechaFI+"' and apa.pac_codigo_paciente=aad.pac_codigo_paciente_fk  and su.usu_codigo=hde.codUsu and su.dat_codigo_fk=sdp.dat_codigo group by ci.codigoCIE,edad,sdp.ocupacion order by sdp.ocupacion asc,edad,Cantidad) as a "+Sql);	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>MorbilidadConsultaExterna "+ex);
	            }	
	            return rs;
	}
	
	
	
	public java.sql.ResultSet BuscarArea(String Serv){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	rs=st.executeQuery("SELECT codigo, nombre FROM adm_subarea WHERE codigoarea='"+Serv+"' ");
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>BuscarArea "+ex);
	            }	
	            return rs;
	}
		
	
	public java.sql.ResultSet BuscarGeneroval1(String Fechai,String Fechaf,String  Serv,String ftent,int rep){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       /***
	        * Esta es la consulta que busca la cantidad de pacientes por genero para  todos los servicios cuando escogen TODAS 
	        * y para el servicio de urgencia
	        */
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if((ftent.equals("todas"))&&(Serv.equals("todas"))){
	        		if(rep==0){
		        		rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
		        							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		        							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
		        						     "AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        							"AND aa.estado!=2 "+ 
		        							"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		        							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        		}else{
	        			rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        					"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
    						     "AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    							"AND aa.estado!=2 "+ 
    							"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        			
	        			System.out.println("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        					"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
    						     "AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    							"AND aa.estado!=2 "+ 
    							"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        		}
	        	}else{
	        		if(((ftent.equals("todas"))&&(Serv.equals("1")))){
	        			
	        			if(rep==0){
	        			rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic "+
    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
    							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    							"AND hic.estado=1 "+
    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    							"AND aa.estado!=2 "+
    							" AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) "+
    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        			}else{
	        				rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        						"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic "+
	    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
	    							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	    							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	    							"AND hic.estado=1 "+
	    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	    							"AND aa.estado!=2 "+
	    							" AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) "+
	    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        			}
	        		}else{
	        			if(Serv.equals("1")){
	        				if(rep==0){
		        				rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic "+
		    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
		    							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    							"AND aa.estado!=2 AND ae.ent_nit='"+ftent+"' "+
		    							"AND hic.estado=1 "+
		    							"AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) "+
		    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        				}else{
	        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic "+
		    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
		    							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    							"AND aa.estado!=2 AND ae.ent_nit='"+ftent+"' "+
		    							"AND hic.estado=1 "+
		    							"AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) "+
		    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        				}
	        			}else{
	        				if(rep==0){
		        				rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
		    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
		    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    							"AND aa.estado!=2 AND ae.ent_nit='"+ftent+"' "+
		    							"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        				}else{
	        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa "+
		    							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    							"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
		    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    							"AND aa.estado!=2 AND ae.ent_nit='"+ftent+"' "+
		    							"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    							"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        				}
	        			}
	        		}
	        	}
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>BuscarGeneroval1 "+ex);
	            }	
	            return rs;
	}
	
 
	public java.sql.ResultSet BuscarEstadoSalida(String adm, String codpac, String Serv){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       /***
	        * Esta Consulta busca por el codpac y adm el ultimo estado de la salida del paciente segun el Serv (Hosp, Urg, Uci, Cons Ext)
	        * si son todos los servicios se toma la ultima salida del pac. 
	        */
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	if(Serv.equals("todas")){
	        		rs = st.executeQuery("SELECT EstadoSalida from hic_destinopaciente where codAdm='"+adm+"' and codPac='"+codpac+"' order by fecha desc limit 1 ");
	        	}else{
	        		if((Serv.equals("23"))||(Serv.equals("24"))){
	        			rs = st.executeQuery("SELECT EstadoSalida from hic_destinopaciente where codAdm='"+adm+"' and codPac='"+codpac+"' and DestinoPaciente='SALIDA DE HOSPITALIZACION' order by fecha desc limit 1 ");
	        		}else{
	        			if(Serv.equals("25")){
	        				rs = st.executeQuery("SELECT EstadoSalida from hic_destinopaciente where codAdm='"+adm+"' and codPac='"+codpac+"' and (DestinoPaciente='SALIDA CONSULTA EXTERNA' OR DestinoPaciente='SALIDA DE AMBULATORIO' ) order by fecha desc limit 1 ");
	        			}else{
	        				if(Serv.equals("1")){
	        					rs = st.executeQuery("SELECT EstadoSalida from hic_destinopaciente where codAdm='"+adm+"' and codPac='"+codpac+"' and (DestinoPaciente='HOSPITALIZACION' OR DestinoPaciente='SALIDA DE URGENCIA' ) order by fecha desc limit 1 ");
	        				}
	        			}
	        		}
	        		
	        	}
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>BuscarEstadoSalida "+ex);
	            }	
	            return rs;
	}
	
	
	
	public java.sql.ResultSet BuscarGenero(String Fechai,String Fechaf,String  Serv,String ftent,String area,int rep){
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	       /***
	        * Esta es la consulta que busca los generos de los pacientes para los servicios de hospitalizacion, Uci y ambulatorio dejando por fuera los pacientes que
	        * les fue anulada su admision y contando solo a los pacientes que tiene el respectivo formato (Historia clinica Hosp, Uci y Amb) segun el area escogida 
	        * y siempre y cuando el formato se encuentre cerrado
	        */
	        try{
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	
	        	if((Serv.equals("23"))||(Serv.equals("24"))||(Serv.equals("25"))){
	        		if((ftent.equals("todas"))&&(area.equals("todas"))){
	        			if(Serv.equals("24")){
	        				//HOSP 
	        				if(rep==0){
	        				rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	        								"AND aa.estado!=2 "+
	        								"AND hic.codigo_for_fk=hf.codigo "+
	        								"AND (hic.codigo_for_fk=33) "+
	        								"AND hic.estado=1 "+
	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado ");
	        				}else{
	        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        							"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        								"AND aa.estado!=2 "+
        								"AND hic.codigo_for_fk=hf.codigo "+
        								"AND (hic.codigo_for_fk=33) "+
        								"AND hic.estado=1 "+
        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        				}
	        			}else{
	        				if(Serv.equals("23")){
	        					//UCI
	        					if(rep==0){
		        					rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	        								"AND aa.estado!=2 "+
	        								"AND hic.codigo_for_fk=hf.codigo "+
	        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
	        								"AND hic.estado=1 "+
	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado ");
	        					}else{
	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        								"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	        								"AND aa.estado!=2 "+
	        								"AND hic.codigo_for_fk=hf.codigo "+
	        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
	        								"AND hic.estado=1 "+
	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        					}
	        				}else{
	        					if(Serv.equals("25")){
	        						//AMB
	        						if(rep==0){
	        						rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	        								"AND aa.estado!=2 "+
	        								"AND hic.codigo_for_fk=hf.codigo "+
	        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
	        								"AND hic.estado=1 "+
	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado ");
	        						}else{
	        							
	        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        									"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        								"AND aa.estado!=2 "+
		        								"AND hic.codigo_for_fk=hf.codigo "+
		        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
		        								"AND hic.estado=1 "+
		        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        						}
	        					}
	        				}
	        			}
	        		}else{
	        			if(ftent.equals("todas")){
	        				if(Serv.equals("23")){
	        					//UCI
	        					if(rep==0){
	        					rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        								"AND aa.estado!=2 "+
        								"AND hic.codigo_for_fk=hf.codigo "+
        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
        								"AND hic.estado=1 "+
        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        					}else{
		        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
		        							"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	        								"AND aa.estado!=2 "+
	        								"AND hic.codigo_for_fk=hf.codigo "+
	        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
	        								"AND hic.estado=1 "+
	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        						
	        					}
	        				}else{
	        					if(Serv.equals("24")){
	        						//HOSP 
		        						if(rep==0){
		    	        				rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		    	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		    	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.estado!=2 "+
		    	        								"AND hic.codigo_for_fk=hf.codigo "+
		    	        								"AND (hic.codigo_for_fk=33) "+
		    	        								"AND hic.estado=1 "+
		    	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		    	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
		        						}else{
		        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
		        									"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	    	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	    	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	    	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	    	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	    	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	    	        								"AND aa.estado!=2 "+
	    	        								"AND hic.codigo_for_fk=hf.codigo "+
	    	        								"AND (hic.codigo_for_fk=33) "+
	    	        								"AND hic.estado=1 "+
	    	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	    	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        						}
	        						}else{
	        						if(Serv.equals("25")){
	        							//AMB
	        							if(rep==0){
		        						rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        								"AND aa.estado!=2 "+
		        								"AND hic.codigo_for_fk=hf.codigo "+
		        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
		        								"AND hic.estado=1 "+
		        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        							}else{
	        								rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
			        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
			        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
			        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
			        								"AND aa.estado!=2 "+
			        								"AND hic.codigo_for_fk=hf.codigo "+
			        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
			        								"AND hic.estado=1 "+
			        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
			        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        							}
	        						}
	        					}
	        				}
	        			
	        			}else{
	        				if((area.equals("todas"))){
	        					if (Serv.equals("23")){
	        						//UCI
	        						if(rep==0){
				        					rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
			        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
			        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
			        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
			        								"AND aa.estado!=2 "+
			        								"AND hic.codigo_for_fk=hf.codigo "+
			        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
			        								"AND hic.estado=1 "+
			        								"AND ae.ent_nit='"+ftent+"' "+
			        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        						}else{
	        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        									"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        								"AND aa.estado!=2 "+
		        								"AND hic.codigo_for_fk=hf.codigo "+
		        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
		        								"AND hic.estado=1 "+
		        								"AND ae.ent_nit='"+ftent+"' "+
		        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        						}
	        					}else{
	        						if(Serv.equals("24")){
	        							
	        							//HOSP 
	        							if(rep==0){
		        	        				rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		        	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		        	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        	        								"AND aa.estado!=2 "+
		        	        								"AND hic.codigo_for_fk=hf.codigo "+
		        	        								"AND (hic.codigo_for_fk=33) "+
		        	        								"AND hic.estado=1 "+
		        	        								"AND ae.ent_nit='"+ftent+"' "+
		        	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        							}else{
	        								rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        										"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
        	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        	        								"AND aa.estado!=2 "+
        	        								"AND hic.codigo_for_fk=hf.codigo "+
        	        								"AND (hic.codigo_for_fk=33) "+
        	        								"AND hic.estado=1 "+
        	        								"AND ae.ent_nit='"+ftent+"' "+
        	          								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        							}
	        						}else{
	        							if(Serv.equals("25")){
	        								//AMB
	        								if(rep==0){
	    	        						rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	    	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	    	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	    	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	    	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	    	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	    	        								"AND aa.estado!=2 "+
	    	        								"AND hic.codigo_for_fk=hf.codigo "+
	    	        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
	    	        								"AND hic.estado=1 "+
	    	        								"AND ae.ent_nit='"+ftent+"' "+
	    	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        								}else{
	        									rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        											"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		    	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		    	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.estado!=2 "+
		    	        								"AND hic.codigo_for_fk=hf.codigo "+
		    	        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
		    	        								"AND hic.estado=1 "+
		    	        								"AND ae.ent_nit='"+ftent+"' "+
		    	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        								}
	        							}
	        						}
	        					}
	        					
	        				}else{
	        					//Para eps en particular  y subarea en particular 
	        					if (Serv.equals("23")){
	        						//UCI
	        						if(rep==0){
		        					rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	        								"AND aa.estado!=2 "+
	        								"AND hic.codigo_for_fk=hf.codigo "+
	        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
	        								"AND hic.estado=1 "+
	        								"AND ae.ent_nit='"+ftent+"' "+
	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        						}else{
	        							rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        								"AND aa.estado!=2 "+
		        								"AND hic.codigo_for_fk=hf.codigo "+
		        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) "+
		        								"AND hic.estado=1 "+
		        								"AND ae.ent_nit='"+ftent+"' "+
		        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        						}
	        					}else{
	        						if(Serv.equals("24")){
	        							
	        							//HOSP 
	        							if(rep==0){
	        	        				rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
	        	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	        	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	        	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
	        	        								"AND aa.estado!=2 "+
	        	        								"AND hic.codigo_for_fk=hf.codigo "+
	        	        								"AND (hic.codigo_for_fk=33) "+
	        	        								"AND hic.estado=1 "+
	        	        								"AND ae.ent_nit='"+ftent+"' "+
	        	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	        	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        							}else{
	        								rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        										"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
        	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        	        								"AND aa.estado!=2 "+
        	        								"AND hic.codigo_for_fk=hf.codigo "+
        	        								"AND (hic.codigo_for_fk=33) "+
        	        								"AND hic.estado=1 "+
        	        								"AND ae.ent_nit='"+ftent+"' "+
        	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
        	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        							}
	        						}else{
	        							if(Serv.equals("25")){
	        								//AMB
	        								if(rep==0){
		    	        						rs=st.executeQuery("SELECT pac.genero, COUNT(pac.genero), aa.estado FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		    	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		    	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.estado!=2 "+
		    	        								"AND hic.codigo_for_fk=hf.codigo "+
		    	        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
		    	        								"AND hic.estado=1 "+
		    	        								"AND ae.ent_nit='"+ftent+"' "+
		    	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		    	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.genero, aa.estado");
	        								}else{
	        									rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ae.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente "+
	        											"FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_formato hf "+
		    	        								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
		    	        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		    	        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    	        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    	        								"AND aa.estado!=2 "+
		    	        								"AND hic.codigo_for_fk=hf.codigo "+
		    	        								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) "+
		    	        								"AND hic.estado=1 "+
		    	        								"AND ae.ent_nit='"+ftent+"' "+
		    	        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		    	        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        								}
	        							}
	        						}
	        					}
	        				}
	        			}
	        			
	        		
	        		}
	        	}
	        	
	        	
	        }catch(Exception ex){
	            	System.out.println("Error en MetodoCalidad>>BuscarGenero "+ex);
	            }	
	            return rs;
	}
	
	public java.sql.ResultSet BuscarEdades(int Ed1, int Ed2, String Fechai, String Fechaf, String  ftent,String Serv, int rep){	
		/**
		 * Consulta la cantidad de ingresos de paciente clasificado por fecha, edades por servicio
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((ftent.equals("todas"))&&(Serv.equals("todas"))){
        			if(rep==0){
		        	rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
		        						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa  "+
		        						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
		        						"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
		        						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
		        						"AND aa.estado!=2 "+
		        						"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'"		);
        			}else{
        				rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad  "+
        						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa  "+
        						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
        						"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
        						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
        						"AND aa.estado!=2 "+
        						"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'"		);
        			}
        	}else{
        		if(((ftent.equals("todas"))&&(Serv.equals("1")))){
        			if(rep==0){
        				rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
        							"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa , hic_adm_formatos_pac hic "+
        							"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
        							"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
        							"AND con.ent_nit_contratante_fk=ent.ent_nit "+
        							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
	    							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
	    							"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND hic.estado=1 "+
        							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        				
		        		System.out.println("SELECT COUNT(pac.pac_codigo_paciente) "+
								"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa , hic_adm_formatos_pac hic "+
								"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
								"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
								"AND con.ent_nit_contratante_fk=ent.ent_nit   "+
								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
								"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND hic.estado=1 "+
								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        			}else{
        				rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad  "+
    							"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa , hic_adm_formatos_pac hic "+
    							"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
    							"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
    							"AND con.ent_nit_contratante_fk=ent.ent_nit "+
    							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
    							"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND hic.estado=1 "+
    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        			}
        		}else{
        			if(Serv.equals("1")){
        				if(rep==0){
        				rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
    							"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa , hic_adm_formatos_pac hic "+
    							"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
    							"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
    							"AND con.ent_nit_contratante_fk=ent.ent_nit  AND ent.ent_nit='"+ftent+"' "+
    							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
    							"AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) AND hic.estado=1  AND ent.ent_nit='"+ftent+"' "+
    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        				}else{
        					
        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
        							"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa , hic_adm_formatos_pac hic "+
        							"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
        							"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
        							"AND con.ent_nit_contratante_fk=ent.ent_nit  AND ent.ent_nit='"+ftent+"' "+
        							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
        							"AND (hic.`codigo_for_fk`=26 OR hic.codigo_for_fk=43) AND hic.estado=1  AND ent.ent_nit='"+ftent+"' "+
        							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        				}
        			}else{
        				if(rep==0){
        				rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
    							"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa "+
    							"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
    							"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
    							"AND con.ent_nit_contratante_fk=ent.ent_nit  AND ent.ent_nit='"+ftent+"' "+
    							"AND ent.ent_nit='"+ftent+"' AND aa.estado!=2 "+
    							"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        				}else{
        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
        							"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa "+
        							"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' "+
        							"AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
        							"AND con.ent_nit_contratante_fk=ent.ent_nit  AND ent.ent_nit='"+ftent+"' "+
        							"AND ent.ent_nit='"+ftent+"' AND aa.estado!=2 "+
        							"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
        							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'");
        				}
        			}
        		}
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarEdades "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarEdadesVal2(int Ed1, int Ed2, String Fechai, String Fechaf, String  ftent,String Serv, String area,int rep){	
		/**
		 * Consulta la cantidad de ingresos de paciente clasificado por fecha, edades por servicio esta consulta es solo para los servicios 
		 * de HOSPITALIZACION, UCI, AMBULATORIO
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf+"Ent "+ftent);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((Serv.equals("23"))||(Serv.equals("24"))||(Serv.equals("25"))){
        		if((ftent.equals("todas"))&&(area.equals("todas"))){
        			if(Serv.equals("24")){
        				//HOSP 
        				if(rep==0){
        				 	rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
            						"WHERE  ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
            						"AND aa.estado!=2 "+
            						"AND hic.codigo_for_fk=hf.codigo "+
            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
            						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        				
        				}else{
        					//consulta para reporte
        					
        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad   "+
            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
            						"WHERE  ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
            						"AND aa.estado!=2 "+
            						"AND hic.codigo_for_fk=hf.codigo "+
            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
            						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        					
        					System.out.println("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
            						"WHERE  ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
            						"AND aa.estado!=2 "+
            						"AND hic.codigo_for_fk=hf.codigo "+
            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
            						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        				
        				}
        			}else{
        				if(Serv.equals("23")){
		        					if(rep==0){
		        					rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
		            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
		            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
		            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
		            						"AND aa.estado!=2 "+
		            						"AND hic.codigo_for_fk=hf.codigo "+
		            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		            						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
		            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        					}else{
		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad  "+
			            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
			            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
			            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
			            						"AND aa.estado!=2 "+
			            						"AND hic.codigo_for_fk=hf.codigo "+
			            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			            						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
			            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        					}
        					}else{
        						if(Serv.equals("25")){
        							if(rep==0){
    		        					rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
    		            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
    		            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
    		            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
    		            						"AND aa.estado!=2 "+
    		            						"AND hic.codigo_for_fk=hf.codigo "+
    		            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    		            						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 "+
    		            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
    		        					}else{
    		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
        		            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
        		            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
        		            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
        		            						"AND aa.estado!=2 "+
        		            						"AND hic.codigo_for_fk=hf.codigo "+
        		            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        		            						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 "+
        		            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
    		        					}
        						}
        					
        				}
        			}
        		}else{
        			if(ftent.equals("todas")){
        				if(Serv.equals("24")){
        					if(rep==0){
        					rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
            						"AND aa.estado!=2 "+
            						"AND hic.codigo_for_fk=hf.codigo "+
            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
            						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
            						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        					}else{
        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
                						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                						"AND aa.estado!=2 "+
                						"AND hic.codigo_for_fk=hf.codigo "+
                						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
                						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
                						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        					}
        				}else{
        					if(Serv.equals("23")){
		        						if(rep==0){
		        						  
		        						rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
		                						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
		                						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
		                						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
		                						"AND aa.estado!=2 "+
		                						"AND hic.codigo_for_fk=hf.codigo "+
		                						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		                						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
		                						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		                						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        						}else{
		        							
		        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
			                						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
			                						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
			                						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
			                						"AND aa.estado!=2 "+
			                						"AND hic.codigo_for_fk=hf.codigo "+
			                						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			                						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
			                						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
			                						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        						}
        						}else{
        						if(Serv.equals("25")){
	        							if(rep==0){
	        							rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
	                    						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
	                    						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
	                    						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
	                    						"AND aa.estado!=2 "+
	                    						"AND hic.codigo_for_fk=hf.codigo "+
	                    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	                    						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 "+
	                    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	                    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        							}else{
	        								rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
		                    						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
		                    						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
		                    						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
		                    						"AND aa.estado!=2 "+
		                    						"AND hic.codigo_for_fk=hf.codigo "+
		                    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		                    						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 "+
		                    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		                    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        							}
        							}
        					}
        				}
        			}else{
        				if(area.equals("todas")){
        					if(Serv.equals("24")){
        						if(rep==0){
        							rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
                    						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                    						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                    						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                    						"AND aa.estado!=2 "+
                    						"AND hic.codigo_for_fk=hf.codigo "+
                    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                    						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
                    						"AND ent.ent_nit='"+ftent+"' "+
                    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        							
        						}else{
        							rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
                    						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                    						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                    						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                    						"AND aa.estado!=2 "+
                    						"AND hic.codigo_for_fk=hf.codigo "+
                    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                    						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
                    						"AND ent.ent_nit='"+ftent+"' "+
                    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        						}
        						
        					}else{
        						if(Serv.equals("23")){
        							 if(rep==0){
        									rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
                            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                            						"AND aa.estado!=2 "+
                            						"AND hic.codigo_for_fk=hf.codigo "+
                            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                            						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34)  AND hic.estado=1 "+
                            						"AND ent.ent_nit='"+ftent+"' "+
                            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        							 }else{
        								 rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
                         						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                         						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                         						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                         						"AND aa.estado!=2 "+
                         						"AND hic.codigo_for_fk=hf.codigo "+
                         						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                         						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34)  AND hic.estado=1 "+
                         						"AND ent.ent_nit='"+ftent+"' "+
                         						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        							 }
        							
        						}else{
        							if(Serv.equals("25")){
	        								if(rep==0){
	        									rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
	                            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
	                            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
	                            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
	                            						"AND aa.estado!=2 "+
	                            						"AND hic.codigo_for_fk=hf.codigo "+
	                            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	                            						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49)  AND hic.estado=1 "+
	                            						"AND ent.ent_nit='"+ftent+"' "+
	                            						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	                            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        								}else{
	        									rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
	                            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
	                            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
	                            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
	                            						"AND aa.estado!=2 "+
	                            						"AND hic.codigo_for_fk=hf.codigo "+
	                            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	                            						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49)  AND hic.estado=1 "+
	                            						"AND ent.ent_nit='"+ftent+"' "+
	                            						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
	                            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        								}
        							}
        						}
        					}
        					
        				}else{
        					if(Serv.equals("24")){
	        						if(rep==0){
		        						rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
		                						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
		                						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
		                						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
		                						"AND aa.estado!=2 "+
		                						"AND hic.codigo_for_fk=hf.codigo "+
		                						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		                						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
		                						"AND ent.ent_nit='"+ftent+"' "+
		                						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		                						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        						}else{
	        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
		                						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
		                						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
		                						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
		                						"AND aa.estado!=2 "+
		                						"AND hic.codigo_for_fk=hf.codigo "+
		                						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		                						"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
		                						"AND ent.ent_nit='"+ftent+"' "+
		                						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
		                						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
	        							
	        							
	        						}
        					}else{
        						if(Serv.equals("23")){
        							if(rep==0){
        							rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
                    						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                    						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                    						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                    						"AND aa.estado!=2 "+
                    						"AND hic.codigo_for_fk=hf.codigo "+
                    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                    						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34)  AND hic.estado=1 "+
                    						"AND ent.ent_nit='"+ftent+"' "+
                    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
                    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        							}else{
        								rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
                        						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                        						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                        						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                        						"AND aa.estado!=2 "+
                        						"AND hic.codigo_for_fk=hf.codigo "+
                        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                        						"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34)  AND hic.estado=1 "+
                        						"AND ent.ent_nit='"+ftent+"' "+
                        						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
                        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        							}
        						}else{
        							if(Serv.equals("25")){
        								
        								if(rep==0){
        								rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) "+
                        						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                        						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                        						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                        						"AND aa.estado!=2 "+
                        						"AND hic.codigo_for_fk=hf.codigo "+
                        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                        						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49)  AND hic.estado=1 "+
                        						"AND ent.ent_nit='"+ftent+"' "+
                        						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
                        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        								}else{
        									rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad "+
                            						"FROM adm_paciente pac,adm_convenio con, adm_entidad ent, adm_admisiones aa ,hic_adm_formatos_pac hic, hic_formato hf "+
                            						"WHERE ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) BETWEEN '"+Ed1+"' AND '"+Ed2+"' AND  pac.conv_numero_contrato_fk=con.conv_numero_contrato "+
                            						"AND con.ent_nit_contratante_fk=ent.ent_nit "+
                            						"AND aa.estado!=2 "+
                            						"AND hic.codigo_for_fk=hf.codigo "+
                            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                            						"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49)  AND hic.estado=1 "+
                            						"AND ent.ent_nit='"+ftent+"' "+
                            						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) " +
                            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        								}
        							}
        						}
        					}
        				}
        			}
        		}
        	
       
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarEdadesVal2 "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarEntidades(String Fechai, String Fechaf,String Serv, int rep, String codent ){	
		/**
		 * Consulta las entidades mas usadas
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(Serv.equals("todas")){
        		if(rep==0){
        		rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad, ent.ent_nit "+
        						"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa   "+                
        						"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
        						"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente and aa.estado!=2 "+
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
        						"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
        		}else{
        			if(codent.equals("0")){
        			
        				rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad "+
        									"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa    "+             
        									"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
        									"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
        									"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2  "+
        									"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  "+
        									"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) ");
        			}else{
        				
        				rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad "+
								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa    "+             
								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 AND ent.ent_nit='"+codent+"' "+
								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  "+
								"AND aa.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) ");
        				
        			}
        		}
        	}else{
        		if(Serv.equals("1")){
        			if(rep==0){
		        			rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad, ent.ent_nit "+
		    						"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  , hic_adm_formatos_pac hic  "+                
		    						"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    						"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    				    	"AND hic.codigo_pac_fk=pac.pac_codigo_paciente and aa.estado!=2 "+
		    				    	"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND hic.estado=1 "+
		    						"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
        			}else{
        				if(codent.equals("0")){
        					
        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad "+
		    						"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  , hic_adm_formatos_pac hic  "+                
		    						"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    						"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    				    	"AND hic.codigo_pac_fk=pac.pac_codigo_paciente and aa.estado!=2 "+
		    				    	"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND hic.estado=1 ");
        					
        				}else{
        					
        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad "+
		    						"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  , hic_adm_formatos_pac hic  "+                
		    						"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    						"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		    						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    				    	"AND hic.codigo_pac_fk=pac.pac_codigo_paciente and aa.estado!=2 "+
		    				    	"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND hic.estado=1 ");
        					
        				}
        			}
        		}
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarEntidades "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarEntidadesVal2(String Fechai, String Fechaf,String Serv, String area, int rep, String codent){	
		/**
		 * Consulta las entidades mas usadas para 	UCI, HOSP y AMB
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
       // System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((Serv.equals("23"))||(Serv.equals("24"))||(Serv.equals("25"))){
        		if((area.equals("todas"))){
        			if(Serv.equals("24")){
			        			if(rep==0){
			        				
			        				rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad, ent.ent_nit "+
			        								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  ,  hic_adm_formatos_pac hic  "+           
			        								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
			        								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
			        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
			        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
			        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
			        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			        								"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
			        								"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
			        				
			        			}else{
			        				if(codent.equals("0")){
			        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
			    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
			    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
			    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
			    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
			    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
			    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
			    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			    								"AND hic.codigo_for_fk=33 AND hic.estado=1 ");
			        				}else{
			        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
			    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
			    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
			    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
			    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
			    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
			    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+ 
			    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ent.ent_nit='"+codent+"' "+
			    								"AND hic.codigo_for_fk=33 AND hic.estado=1 ");
			        				}
			        			}
        			}else{
        				if(Serv.equals("23")){
        					if(rep==0){
        						rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad, ent.ent_nit "+
        								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  ,  hic_adm_formatos_pac hic  "+           
        								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
        								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
        								"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
        					}else{
        						if(codent.equals("0")){
        							
        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 ");
        						}else{
        							
        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ent.ent_nit='"+codent+"'"+
		    								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34 AND hic.estado=1 ");
        							
        						}
        					}
        				}else{
        					if(Serv.equals("25")){
        						if(rep==0){
        							
        							rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 ");
        							
        						}else{
        							if(codent.equals("0")){
        								
        								rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
    		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
    		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
    		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
    		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
    		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
    		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    		    								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 ");
        								
        							}else{
        								
        								rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
    		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
    		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
    		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
    		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
    		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
    		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ent.ent_nit='"+codent+"' "+
    		    								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49 AND hic.estado=1 ");
        								
        							}
        						}
        						
        					}
        				}
        			}
        			
        			
        		}else{
        			if(Serv.equals("24")){
		        			if(rep==0){
		        				
		        				rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad, ent.ent_nit "+
		        								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  ,  hic_adm_formatos_pac hic    "+ 
		        								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		        								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit` "+
		        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso  "+
		        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        								"AND hic.codigo_for_fk=33 AND hic.estado=1  "+
		        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		        								"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
		        				
		        			}else{
		        				if(codent.equals("0")){
		        					
		        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    								"AND hic.codigo_for_fk=33 AND hic.estado=1 ");
		        				}else{
		        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ent.ent_nit='"+codent+"' "+
		    								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    								"AND hic.codigo_for_fk=33 AND hic.estado=1 ");
		        				}
		        				
		        			}
        			}else{
        				if(Serv.equals("23")){
        					
        					if(rep==0){
		        				
        						rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad, ent.ent_nit "+
        								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  ,  hic_adm_formatos_pac hic    "+ 
        								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
        								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit` "+
        								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
        								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso  "+
        								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34)  AND hic.estado=1  "+
        								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
        								"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
		        			}else{
		        				if(codent.equals("0")){
		        					
		        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    								"AND  (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 ");
		        				}else{
		        					
		        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ent.ent_nit='"+codent+"' "+
		    								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    								"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 ");
		        					
		        				}
		        				
		        			}
        					
        				}else{
        					if(Serv.equals("25")){
        						
        						if(rep==0){
    		        				
        							rs=st.executeQuery("SELECT COUNT(ent.nombre_entidad) AS  Numero_Afiliados  ,ent.nombre_entidad, ent.ent_nit "+
            								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa  ,  hic_adm_formatos_pac hic    "+ 
            								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
            								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit` "+
            								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
            								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
            								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso  "+
            								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
            								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49)  AND hic.estado=1  "+
            								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
            								"GROUP BY ent.nombre_entidad ORDER BY Numero_Afiliados DESC ");
    		        				
    		        				
    		        			}else{
    		        				if(codent.equals("0")){
    		        					
    		        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
    		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
    		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
    		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
    		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
    		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
    		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    		    								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    		    								"AND  (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 ");
    		        					
    		        				}else{
    		        					
    		        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aa.estado,aa.adm_numero_ingreso,aa.fecha_registro, ent.nombre_entidad, aa.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aa.fecha_registro))-YEAR(pac.fecha_nacimiento)) AS edad   "+
    		    								"FROM adm_paciente pac, adm_convenio con, adm_entidad ent , adm_admisiones aa ,  hic_adm_formatos_pac hic  "+           
    		    								"WHERE con.`conv_numero_contrato`=pac.`conv_numero_contrato_fk` "+
    		    								"AND con.`ent_nit_contratante_fk`=ent.`ent_nit`  "+
    		    								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND aa.estado!=2 "+
    		    								"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
    		    								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    		    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ent.ent_nit='"+codent+"' "+
    		    								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    		    								"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 ");
    		        					
    		        				}
    		        				
    		        			}
        						
        					}
        				}
        			}
        			
        		}
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarEntidadesVal2 "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarNombEnt(String ftent){	
		/**
		 * Consulta el nombre de la entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("ftent "+ftent);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ae.nombre_entidad FROM adm_entidad ae WHERE ae.ent_nit='"+ftent+"'");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarNombEnt "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDiagIngr(String adm,String codpac){	
		/**
		 * Consulta el nombre de la entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ci.codigoCIE, ci.nombre  FROM hic_diagnosticoingreso hdi, cie10 ci WHERE ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+adm+" and hdi.codPac="+codpac+" ORDER BY hdi.codigo DESC LIMIT 1 ");
        	//System.out.println("SELECT ci.codigoCIE, ci.nombre  FROM hic_diagnosticoingreso hdi, cie10 ci WHERE ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+adm+" and hdi.codPac="+codpac+" ORDER BY hdi.codigo DESC LIMIT 1 ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarDiagIngr "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarDiagIngre(String adm,String codpac){	
		/**
		 * Consulta el nombre de la entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT hdi.CodDiag_fk FROM hic_diagnosticoingreso hdi, cie10 ci WHERE ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+adm+" and hdi.codPac="+codpac+" ORDER BY hdi.codigo DESC");
        /*System.out.println("BuscarDiagIngre");
        	System.out.println("SELECT hdi.CodDiag_fk FROM hic_diagnosticoingreso hdi, cie10 ci WHERE ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+adm+" and hdi.codPac="+codpac+" ORDER BY hdi.codigo DESC");
        	//System.out.println("SELECT ci.codigoCIE, ci.nombre  FROM hic_diagnosticoingreso hdi, cie10 ci WHERE ci.codigo=hdi.CodDiag_fk and hdi.codAdm="+adm+" and hdi.codPac="+codpac+" ORDER BY hdi.codigo DESC LIMIT 1 ");
        	*/
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarDiagIngre "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDiagEgres(String adm,String codpac){	
		/**
		 * Consulta el nombre de la entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT ci.codigoCIE, ci.nombre  FROM hic_diagnosticoegreso hde, cie10 ci WHERE ci.codigo=hde.CodDiag_fk and hde.codAdm="+adm+" and hde.codPac="+codpac+" ORDER BY hde.codigo DESC LIMIT 1 ");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarDiagEgres"+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarServ(String Serv){	
		/**
		 * Consulta el nombre del servicio. 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Serv  "+Serv);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT aa.nombre FROM adm_area aa WHERE aa.codigo='"+Serv+"'");
        	System.out.println("SELECT aa.nombre FROM adm_area aa WHERE aa.codigo='"+Serv+"'");
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarServ "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarSubArea(String Serv, String area){	
		/**
		 * Consulta el nombre del subarea dependiendo del servicio y el subarea seleccionado. 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Serv  "+Serv+"Area "+area);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	rs=st.executeQuery("SELECT asub.nombre FROM adm_area aa,adm_subarea asub WHERE aa.codigo=asub.codigoarea AND aa.codigo='"+Serv+"' AND asub.codigo='"+area+"' ");
        	System.out.println("SELECT asub.nombre FROM adm_area aa,adm_subarea asub WHERE aa.codigo=asub.codigoarea AND aa.codigo='"+Serv+"' AND asub.codigo='"+area+"' ");
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarSubArea "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarPacIngreRepetido(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta si el paciente tiene mas de una admision dentro del rango de la fecha dada 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT DISTINCT * FROM (SELECT COUNT(pac.pac_codigo_paciente) AS contador, pac.pac_codigo_paciente "+
        						"FROM adm_paciente pac, hic_adm_formatos_pac hic "+
        						"WHERE hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        						"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) "+
        						"AND hic.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.pac_codigo_paciente ) AS A WHERE a.contador>1 ORDER BY a.contador DESC ");
        	
        	
        	System.out.println("SELECT DISTINCT * FROM (SELECT COUNT(pac.pac_codigo_paciente) AS contador, pac.pac_codigo_paciente "+
					"FROM adm_paciente pac, hic_adm_formatos_pac hic "+
					"WHERE hic.codigo_pac_fk=pac.pac_codigo_paciente "+
					"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) "+
					"AND hic.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.pac_codigo_paciente ) AS A WHERE a.contador>1 ORDER BY a.contador DESC ");
        	
        	}else{
        		rs=st.executeQuery("SELECT DISTINCT * FROM (SELECT COUNT(pac.pac_codigo_paciente) AS contador, pac.pac_codigo_paciente "+
						"FROM adm_paciente pac, hic_adm_formatos_pac hic,adm_convenio ac, adm_entidad ae  "+
						"WHERE  hic.codigo_pac_fk=pac.pac_codigo_paciente  AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND ae.ent_nit='"+ftent+"' "+
						"AND hic.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.pac_codigo_paciente ) AS A WHERE a.contador>1 ORDER BY a.contador DESC ");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarPacIngreRepetido "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarPacIngreRepetidoHosp(String Fechai, String Fechaf, String ftent, String area){	
		/**
		 * Consulta si el paciente tiene mas de una admision dentro del rango de la fecha dada 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
		        	rs=st.executeQuery("SELECT DISTINCT * FROM (SELECT COUNT(pac.pac_codigo_paciente) AS contador, pac.pac_codigo_paciente, pac.numero_documento,pac.tipo_documento,hic.codigo_for_fk, hic.fecha, hic.hora "+
		        						"FROM adm_paciente pac, hic_adm_formatos_pac hic , adm_convenio ac, adm_entidad ae "+
		        						"WHERE hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        						"AND (hic.codigo_for_fk=33) AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ac.ent_nit_contratante_fk=ae.ent_nit  "+
		        						"AND hic.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.pac_codigo_paciente) AS A WHERE a.contador>1 ORDER BY a.contador DESC ");
		        	
        	}else{
        		
        		
        		rs=st.executeQuery("SELECT DISTINCT * FROM (SELECT COUNT(pac.pac_codigo_paciente) AS contador, pac.pac_codigo_paciente, pac.numero_documento,pac.tipo_documento,hic.codigo_for_fk, hic.fecha, hic.hora "+
                                  "FROM adm_paciente pac, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae "+
                                  "WHERE hic.codigo_pac_fk=pac.pac_codigo_paciente "+
                                  "AND (hic.codigo_for_fk=33)  AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ac.ent_nit_contratante_fk=ae.ent_nit AND ae.ent_nit='"+ftent+"' "+
                                  "AND hic.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY pac.pac_codigo_paciente) AS A WHERE a.contador>1 ORDER BY a.contador DESC");
        		
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarPacIngreRepetidoHosp "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarNoAdm(String CodPac,String Fechai,String Fechaf,String ftent,String area){	
		/**
		 * Consulta si el paciente tiene mas de una admision dentro del rango de la fecha dada 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	
		        	rs=st.executeQuery("SELECT COUNT(aa.adm_numero_ingreso) as numadm , aa.pac_codigo_paciente_fk "+
		        			"FROM adm_admisiones aa , adm_paciente pac, adm_convenio ac, adm_entidad ae "+
		        			"WHERE aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND  pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
		        			" and aa.pac_codigo_paciente_fk="+CodPac+" and aa.estado!=2  AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY aa.pac_codigo_paciente_fk ");
		        	
		        	/*System.out.println("SELECT COUNT(aa.adm_numero_ingreso) as numadm,aa.pac_codigo_paciente_fk "+
		        			"FROM adm_admisiones aa "+
		        			"WHERE  "+
		        			"aa.pac_codigo_paciente_fk="+CodPac+" AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY aa.pac_codigo_paciente_fk ");*/
		        	  	
        	}else{
        	
        		rs=st.executeQuery("SELECT COUNT(aa.adm_numero_ingreso) as numadm , aa.pac_codigo_paciente_fk "+
	        			"FROM adm_admisiones aa ,adm_paciente pac, adm_convenio ac, adm_entidad ae "+
	        			"WHERE aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND  pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' "+
	        			"AND aa.pac_codigo_paciente_fk="+CodPac+" and aa.estado!=2  AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY aa.pac_codigo_paciente_fk ");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarNoAdm "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarNoAdmUrg(String CodPac,String Fechai,String Fechaf,String ftent){	
		/**
		 * Consulta si el paciente tiene mas de una admision dentro del rango de la fecha dada 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
		        	rs=st.executeQuery("SELECT COUNT(aa.adm_numero_ingreso) as numadm , aa.pac_codigo_paciente_fk "+
		        			"FROM adm_admisiones aa "+
		        			"WHERE "+
		        			"aa.pac_codigo_paciente_fk="+CodPac+" and aa.estado!=2  AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY aa.pac_codigo_paciente_fk ");
		        	
		        	/*System.out.println("SELECT COUNT(aa.adm_numero_ingreso) as numadm,aa.pac_codigo_paciente_fk "+
		        			"FROM adm_admisiones aa "+
		        			"WHERE  "+
		        			"aa.pac_codigo_paciente_fk="+CodPac+" AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY aa.pac_codigo_paciente_fk ");*/ 	
        	}else{
        		rs=st.executeQuery("SELECT COUNT(aa.adm_numero_ingreso) AS numadm , aa.pac_codigo_paciente_fk "+
        							"FROM adm_admisiones aa, adm_paciente pac, adm_convenio ac, adm_entidad ae "+
        							"WHERE aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND  pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' "+
        							"AND aa.pac_codigo_paciente_fk="+CodPac+" AND aa.estado!=2  AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY aa.pac_codigo_paciente_fk ");	
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarNoAdmUrg "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDetAdm(String CodPac,String Fechai,String Fechaf,String ftent,String area){	
		/**
		 * Consulta si el paciente tiene mas de una admision dentro del rango de la fecha dada 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		    		
		        	rs=st.executeQuery("SELECT aa.adm_numero_ingreso , aa.pac_codigo_paciente_fk, aa.fecha_registro  "+
		        			"FROM adm_admisiones aa  "+
		        			"WHERE "+
		        			" aa.pac_codigo_paciente_fk="+CodPac+" and aa.estado!=2 AND aa.fecha_registro BETWEEN '"+Fechai+"' AND'"+Fechaf+"'  ");
		        	
		        	//System.out.println
		        	
        		
		        	
		        	
        	}else{
        		rs=st.executeQuery("SELECT aa.adm_numero_ingreso , aa.pac_codigo_paciente_fk, aa.fecha_registro  "+
	        			"FROM adm_admisiones aa, adm_paciente pac, adm_convenio ac, adm_entidad ae "+
	        			"WHERE "+
	        			"aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND  pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' "+
	        			"AND aa.pac_codigo_paciente_fk="+CodPac+" and aa.estado!=2 AND aa.fecha_registro BETWEEN '"+Fechai+"' AND'"+Fechaf+"'  ");
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarDetAdm "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarDetAdmUrg(String CodPac,String Fechai,String Fechaf,String ftent){	
		/**
		 * Consulta si el paciente tiene mas de una admision dentro del rango de la fecha dada 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        			System.out.println(" Fechai "+Fechai+" Fechaf"+Fechaf);
		        	rs=st.executeQuery("SELECT aa.adm_numero_ingreso , aa.pac_codigo_paciente_fk, aa.fecha_registro  "+
		        			"FROM adm_admisiones aa "+
		        			"WHERE "+
		        			" aa.pac_codigo_paciente_fk="+CodPac+" and aa.estado!=2 AND aa.fecha_registro BETWEEN '"+Fechai+"' AND'"+Fechaf+"'  ");
		        	
		        	
		        	
        	}else{
        		
        		rs=st.executeQuery("SELECT aa.adm_numero_ingreso , aa.pac_codigo_paciente_fk, aa.fecha_registro  "+
		        			"FROM adm_admisiones aa, adm_paciente pac, adm_convenio ac, adm_entidad ae "+
		        			"WHERE "+
		        			" aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND  pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' "+
		        			"AND aa.pac_codigo_paciente_fk="+CodPac+" AND aa.estado!=2  AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY aa.pac_codigo_paciente_fk ");
        		

        		System.out.println("SELECT aa.adm_numero_ingreso , aa.pac_codigo_paciente_fk, aa.fecha_registro  "+
		        			"FROM adm_admisiones aa, adm_paciente pac, adm_convenio ac, adm_entidad ae "+
		        			"WHERE "+
		        			" aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente AND  pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' "+
		        			"AND aa.pac_codigo_paciente_fk="+CodPac+" AND aa.estado!=2  AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY aa.pac_codigo_paciente_fk ");
        		
        	}
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarDetAdmUrg "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarFechaRegPac(String CodPac,String Fechai,String Fechaf, String ftent){	
		/**
		 * Consulta las admisiones que ha tenido el paciente segun el rango de fecha y entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
        						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic ,hic_diagnosticoingreso hdxing "+
        						"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        						"AND hdxing.codAdm=aa.adm_numero_ingreso "+
        						"AND hdxing.codPac=pac.pac_codigo_paciente "+
        						"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        	
        	System.out.println("SELECT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
					"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic ,hic_diagnosticoingreso hdxing "+
					"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
					"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
					"AND hdxing.codAdm=aa.adm_numero_ingreso "+
					"AND hdxing.codPac=pac.pac_codigo_paciente "+
					"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        	}else{
        		rs=st.executeQuery("SELECT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae, hic_diagnosticoingreso hdxing"+
						"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND hdxing.codAdm=aa.adm_numero_ingreso "+
						"AND hdxing.codPac=pac.pac_codigo_paciente "+
						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
						"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarFechaRegPac "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarFechaRegPacHosp(String CodPac,String Fechai,String Fechaf, String ftent,String area){	
		/**
		 * Consulta las admisiones que ha tenido el paciente segun el rango de fecha y entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		if(area.equals("todas")){
		        	rs=st.executeQuery("SELECT DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
		        						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic ,hic_diagnosticoingreso hdxing "+
		        						"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
		        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        						"AND hdxing.codAdm=aa.adm_numero_ingreso "+
		        						"AND hdxing.codPac=pac.pac_codigo_paciente "+
		        						"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
		        	System.out.println("BuscarFechaRegPacHosp ");
		        	System.out.println("SELECT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
							"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic ,hic_diagnosticoingreso hdxing "+
							"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							"AND hdxing.codAdm=aa.adm_numero_ingreso "+
							"AND hdxing.codPac=pac.pac_codigo_paciente "+
							"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        		}else{
        			
        			rs=st.executeQuery("SELECT DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
    						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic ,hic_diagnosticoingreso hdxing "+
    						"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND hdxing.codAdm=aa.adm_numero_ingreso "+
    						"AND hdxing.codPac=pac.pac_codigo_paciente "+
    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    						"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        			
        		}
        		}else{
        			
        			if(area.equals("todas")){
			        		rs=st.executeQuery("SELECT DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
									"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae, hic_diagnosticoingreso hdxing"+
									"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
									"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
									"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
									"AND hdxing.codAdm=aa.adm_numero_ingreso "+
									"AND hdxing.codPac=pac.pac_codigo_paciente "+
									"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
									"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        			}else{
        				
        				rs=st.executeQuery("SELECT DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, hdxing.CodDiag_fk "+
								"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae, hic_diagnosticoingreso hdxing"+
								"WHERE pac.pac_codigo_paciente='"+CodPac+"' "+ 
								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								"AND hdxing.codAdm=aa.adm_numero_ingreso "+
								"AND hdxing.codPac=pac.pac_codigo_paciente "+
								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
								"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
								"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        				
        				
        			}
        		}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarFechaRegPacHosp "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarPacientesReingresados(String CodPac,String Fechai,String Fechaf, String ftent, String fechareg, String dingre, String CodAdm){	
		/**
		 * Consulta las admisiones que ha tenido el paciente segun el rango de fecha y entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Valor de dx Ingreso "+dingre);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery(" SELECT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias , hdxing.CodDiag_fk "+
        						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
        						"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '3' "+
        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente  and aa.adm_numero_ingreso!='"+CodAdm+"' "+
        						"AND hdxing.codAdm=aa.adm_numero_ingreso AND  hdxing.CodDiag_fk='"+dingre+"' "+
        						"AND hdxing.codPac=pac.pac_codigo_paciente "+
        						"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        	
        	System.out.println(" SELECT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias , hdxing.CodDiag_fk "+
					"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
					"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '3' "+
					"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente  and aa.adm_numero_ingreso!='"+CodAdm+"' "+
					"AND hdxing.codAdm=aa.adm_numero_ingreso AND  hdxing.CodDiag_fk='"+dingre+"' "+
					"AND hdxing.codPac=pac.pac_codigo_paciente "+
					"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");

        	
        	}else{
        		rs=st.executeQuery(" SELECT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias ,hdxing.CodDiag_fk  "+
						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
						"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '3' "+
						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso  AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
						"AND hdxing.codAdm=aa.adm_numero_ingreso and aa.adm_numero_ingreso!='"+CodAdm+"' "+
						"AND hdxing.codPac=pac.pac_codigo_paciente AND  hdxing.CodDiag_fk='"+dingre+"' "+
						"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarPacientesReingresados "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarPacientesReingHosp(String CodPac,String Fechai,String Fechaf, String ftent, String fechareg, String dingre,String area,String CodAdm){	
		/**
		 * Consulta las admisiones que ha tenido el paciente segun el rango de fecha y entidad
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
       // System.out.println("Valor de dx Ingreso "+dingre);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		if(area.equals("todas")){
        		
				  rs=st.executeQuery("SELECT  DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias , hdxing.CodDiag_fk, CONCAT(pac.tipo_documento,'-',numero_documento) as documento, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido) as nombrepac "+
				        						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
				        						"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '20' "+
				        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
				        						"AND ae.ent_nit=ac.ent_nit_contratante_fk and aa.adm_numero_ingreso!='"+CodAdm+"'"+
				        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente  "+
				        						"AND hdxing.codAdm=aa.adm_numero_ingreso AND  hdxing.CodDiag_fk='"+dingre+"' "+
				        						"AND hdxing.codPac=pac.pac_codigo_paciente "+
				        						"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
				/*  System.out.println("SELECT  DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias , hdxing.CodDiag_fk, CONCAT(pac.tipo_documento,'-',numero_documento) as documento, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido) as nombrepac "+
					"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
					"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '20' "+
					"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
					"AND ae.ent_nit=ac.ent_nit_contratante_fk and aa.adm_numero_ingreso!='"+CodAdm+"' "+
					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente  "+
					"AND hdxing.codAdm=aa.adm_numero_ingreso AND  hdxing.CodDiag_fk='"+dingre+"' "+
					"AND hdxing.codPac=pac.pac_codigo_paciente "+
					"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
				       */ 	
        		}else{
        			
        			rs=st.executeQuery("SELECT DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias , hdxing.CodDiag_fk, CONCAT(pac.tipo_documento,'-',numero_documento) as documento, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido) as nombrepac "+
    						"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
    						"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '20' "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente  "+
    						"AND hdxing.codAdm=aa.adm_numero_ingreso AND  hdxing.CodDiag_fk='"+dingre+"' "+
    						"AND hdxing.codPac=pac.pac_codigo_paciente "+
    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    						"AND (hic.codigo_for_fk=33) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        			
        			
        		}
        	
        	}else{
        		if(area.equals("todas")){
		        		rs=st.executeQuery(" SELECT DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias ,hdxing.CodDiag_fk  "+
								"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
								"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '20' "+
								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso  AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
								"AND hdxing.codAdm=aa.adm_numero_ingreso "+
								"AND hdxing.codPac=pac.pac_codigo_paciente AND  hdxing.CodDiag_fk='"+dingre+"' "+
								"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
		        }else{
        			
	        		rs=st.executeQuery(" SELECT  DISTINCT pac.pac_codigo_paciente, aa.adm_numero_ingreso, aa.fecha_registro, aa.hora_registro, ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) AS dias ,hdxing.CodDiag_fk  "+
							"FROM adm_paciente pac, adm_admisiones aa, hic_adm_formatos_pac hic, adm_convenio ac, adm_entidad ae ,hic_diagnosticoingreso hdxing "+
							"WHERE pac.pac_codigo_paciente='"+CodPac+"' AND ((YEAR(aa.fecha_registro))-YEAR('"+fechareg+"'))  BETWEEN '0' AND '0' AND ((MONTH(aa.fecha_registro))-MONTH('"+fechareg+"')) BETWEEN '0' AND '1' AND ((DAY(aa.fecha_registro))-DAY('"+fechareg+"')) BETWEEN '0' AND '20' "+
							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso  AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
							"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
							"AND hdxing.codAdm=aa.adm_numero_ingreso "+
							"AND hdxing.codPac=pac.pac_codigo_paciente AND  hdxing.CodDiag_fk='"+dingre+"' "+
							"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND pac.pac_codigo_paciente=aa.pac_codigo_paciente_fk AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ORDER BY aa.fecha_registro, aa.hora_registro DESC");
        			
        		}
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarPacientesReingHosp"+ex);
        }	
        return rs;
    }
	
	
	
	
	
	
	
	
	public java.sql.ResultSet BuscarFalleUrg(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los pacientes fallecidos en urgencia. 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
        						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.estado=1 "+
        						"AND (hic.`codigo_for_fk`=26 OR hic.`codigo_for_fk`=43 ) "+
        						"AND hd.codAdm=aa.adm_numero_ingreso "+
        						"AND hd.codPac=pac.pac_codigo_paciente "+
        						"AND hd.EstadoSalida='MUERTO' "+
        						"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	}else{
        		rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
						"AND aa.estado=1 "+
						"AND (hic.`codigo_for_fk`=26 OR hic.`codigo_for_fk`=43) "+
						"AND hd.codAdm=aa.adm_numero_ingreso "+
						"AND hd.codPac=pac.pac_codigo_paciente "+
						"AND hd.EstadoSalida='MUERTO' AND ae.ent_nit='"+ftent+"' "+
						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarFalleUrg "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarVivoUrg(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los pacientes fallecidos en urgencia. 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
        						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.estado=1 "+
        						"AND (hic.`codigo_for_fk`=26 OR hic.`codigo_for_fk`=43 ) "+
        						"AND hd.codAdm=aa.adm_numero_ingreso "+
        						"AND hd.codPac=pac.pac_codigo_paciente "+
        						"AND hd.EstadoSalida='VIVO' "+
        						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	}else{
        		rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
						"AND aa.estado=1 "+
						"AND (hic.`codigo_for_fk`=26 OR hic.`codigo_for_fk`=43) "+
						"AND hd.codAdm=aa.adm_numero_ingreso "+
						"AND hd.codPac=pac.pac_codigo_paciente "+
						"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
						"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		
        		System.out.println("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
				"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
				"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
				"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
				"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
				"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
				"AND aa.estado=1 "+
				"AND (hic.`codigo_for_fk`=26 OR hic.`codigo_for_fk`=43) "+
				"AND hd.codAdm=aa.adm_numero_ingreso "+
				"AND hd.codPac=pac.pac_codigo_paciente "+
				"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
				"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarVivoUrg "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarVivoHosp(String Fechai, String Fechaf, String ftent, String area){	
		/**
		 * Consulta los pacientes fallecidos en urgencia. 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		if(area.equals("todas")){
		        	rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
		        						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
		        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        						"AND aa.estado=1 "+
		        						"AND (hic.`codigo_for_fk`=33 ) "+
		        						"AND hd.codAdm=aa.adm_numero_ingreso "+
		        						"AND hd.codPac=pac.pac_codigo_paciente "+
		        						"AND hd.EstadoSalida='VIVO' "+
		        						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        	
		        	System.out.println("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
    						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    						"AND aa.estado=1 "+
    						"AND (hic.`codigo_for_fk`=33 ) "+
    						"AND hd.codAdm=aa.adm_numero_ingreso "+
    						"AND hd.codPac=pac.pac_codigo_paciente "+
    						"AND hd.EstadoSalida='VIVO' "+
    						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		}else{
        			
        			rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
    						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    						"AND aa.estado=1 "+
    						"AND (hic.`codigo_for_fk`=33 ) "+
    						"AND hd.codAdm=aa.adm_numero_ingreso "+
    						"AND hd.codPac=pac.pac_codigo_paciente "+
    						"AND hd.EstadoSalida='VIVO' "+
    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        			
        			System.out.println("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
    						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    						"AND aa.estado=1 "+
    						"AND (hic.`codigo_for_fk`=33 ) "+
    						"AND hd.codAdm=aa.adm_numero_ingreso "+
    						"AND hd.codPac=pac.pac_codigo_paciente "+
    						"AND hd.EstadoSalida='VIVO' "+
    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        			
        		}
        	}else{
        		if(area.equals("todas")){
		        		rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
								"AND aa.estado=1 "+
								"AND (hic.`codigo_for_fk`=33 ) "+
								"AND hd.codAdm=aa.adm_numero_ingreso "+
								"AND hd.codPac=pac.pac_codigo_paciente "+
								"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
								"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        		
		        		System.out.println("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
								"AND aa.estado=1 "+
								"AND (hic.`codigo_for_fk`=33 ) "+
								"AND hd.codAdm=aa.adm_numero_ingreso "+
								"AND hd.codPac=pac.pac_codigo_paciente "+
								"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
								"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		}else{
        			rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
							"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
							"AND aa.estado=1 "+
							"AND (hic.`codigo_for_fk`=33 ) "+
							"AND hd.codAdm=aa.adm_numero_ingreso "+
							"AND hd.codPac=pac.pac_codigo_paciente "+
							"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
							"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		
        		
        			System.out.println("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
							"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
							"AND aa.estado=1 "+
							"AND (hic.`codigo_for_fk`=33 ) "+
							"AND hd.codAdm=aa.adm_numero_ingreso "+
							"AND hd.codPac=pac.pac_codigo_paciente "+
							"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
							"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		}
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarVivoHosp "+ex);
        }	
        return rs;
    }
	
	
	
	public java.sql.ResultSet BuscarDetVivoHosp(String Fechai, String Fechaf, String ftent, String area){	
		/**
		 * Consulta los pacientes fallecidos en urgencia. 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		if(area.equals("todas")){
		        	rs=st.executeQuery("SELECT CONCAT(pac.tipo_documento,'-',pac.numero_documento) as docpac, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',segundo_apellido) as nompac, ae.nombre_entidad, aa.adm_numero_ingreso FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
		        						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
		        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
		        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
		        						"AND aa.estado=1 "+
		        						"AND (hic.`codigo_for_fk`=33 ) "+
		        						"AND hd.codAdm=aa.adm_numero_ingreso "+
		        						"AND hd.codPac=pac.pac_codigo_paciente "+
		        						"AND hd.EstadoSalida='VIVO' "+
		        						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        	
		        	System.out.println("SELECT CONCAT(pac.tipo_documento,'-',pac.numero_documento) as docpac, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',segundo_apellido) as nompac, ae.nombre_entidad, aa.adm_numero_ingreso FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
    						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    						"AND aa.estado=1 "+
    						"AND (hic.`codigo_for_fk`=33 ) "+
    						"AND hd.codAdm=aa.adm_numero_ingreso "+
    						"AND hd.codPac=pac.pac_codigo_paciente "+
    						"AND hd.EstadoSalida='VIVO' "+
    						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		}else{
        			
        			rs=st.executeQuery("SELECT CONCAT(pac.tipo_documento,'-',pac.numero_documento) as docpac, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',segundo_apellido) as nompac, ae.nombre_entidad FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
    						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    						"AND aa.estado=1 "+
    						"AND (hic.`codigo_for_fk`=33 ) "+
    						"AND hd.codAdm=aa.adm_numero_ingreso "+
    						"AND hd.codPac=pac.pac_codigo_paciente "+
    						"AND hd.EstadoSalida='VIVO' "+
    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        			
        			System.out.println("SELECT CONCAT(pac.tipo_documento,'-',pac.numero_documento) as docpac, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',segundo_apellido) as nompac, ae.nombre_entidad FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
    						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    						"AND aa.estado=1 "+
    						"AND (hic.`codigo_for_fk`=33 ) "+
    						"AND hd.codAdm=aa.adm_numero_ingreso "+
    						"AND hd.codPac=pac.pac_codigo_paciente "+
    						"AND hd.EstadoSalida='VIVO' "+
    						"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
    						"AND  hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        			
        		}
        	}else{
        		if(area.equals("todas")){
		        		rs=st.executeQuery("SELECT CONCAT(pac.tipo_documento,'-',pac.numero_documento) as docpac, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',segundo_apellido) as nompac, ae.nombre_entidad FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
								"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
								"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
								"AND aa.estado=1 "+
								"AND (hic.`codigo_for_fk`=33 ) "+
								"AND hd.codAdm=aa.adm_numero_ingreso "+
								"AND hd.codPac=pac.pac_codigo_paciente "+
								"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
								"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		        		
        		}else{
        			rs=st.executeQuery("SELECT CONCAT(pac.tipo_documento,'-',pac.numero_documento) as docpac, CONCAT(pac.nombre,' ',pac.primer_apellido,' ',segundo_apellido) as nompac, ae.nombre_entidad FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic, hic_destinopaciente hd "+
							"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
							"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
							"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
							"AND aa.estado=1 "+
							"AND (hic.`codigo_for_fk`=33 ) "+
							"AND hd.codAdm=aa.adm_numero_ingreso "+
							"AND hd.codPac=pac.pac_codigo_paciente "+
							"AND hd.EstadoSalida='VIVO' AND ae.ent_nit='"+ftent+"' "+
							"AND aa.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							"AND hd.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		}
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarDetVivoHosp "+ex);
        }	
        return rs;
    }
	
	
	
	
	public java.sql.ResultSet BuscarPacUrgen(String Fechai, String Fechaf, String ftent, int tipo){	
		/**
		 * Consulta de Paciente atendidos en urgencia 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")&&(tipo==1)){
        	rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic  "+
        						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.estado=1 "+
        						"AND hic.`codigo_for_fk`=26 "+
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY aa.estado");
        	
        	
        	}else{
        		if(tipo==1){
        			rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic  "+
    						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
    						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
    						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
    						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
    						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
    						"AND aa.estado=1 "+
    						"AND hic.`codigo_for_fk`=26 AND ae.ent_nit='"+ftent+"' "+
    						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY aa.estado");
        		}else{
        			if(ftent.equals("todas")&&(tipo==2)){
        				rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic  "+
        						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
        						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
        						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.estado=1 "+
        						"AND hic.`codigo_for_fk`=43 " +
        						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY aa.estado");
        			}else{
        				if(tipo==2){
        					rs=st.executeQuery("SELECT COUNT(pac.pac_codigo_paciente) FROM adm_paciente pac, adm_convenio ac, adm_entidad ae, adm_admisiones aa, hic_adm_formatos_pac hic  "+
            						"WHERE  ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
            						"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
            						"AND hic.codigo_adm_fk=aa.adm_numero_ingreso "+
            						"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
            						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
            						"AND aa.estado=1 "+
            						"AND hic.`codigo_for_fk`=43 AND ae.ent_nit='"+ftent+"' "+
            						"AND aa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY aa.estado");
        				}
        			}
        		}
        		
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarPacUrgen "+ex);
        }	
        return rs;
    }
	
	public java.sql.ResultSet BuscarMinHC(String Fechai, String Fechaf, String ftent, int tipo){	
		/**
		 * Consulta los minutos transcurrido entre la admision del paciente y su atencion por el medico
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")&&(tipo==1)){
        		//tipo 1 es triage I y II osea formato 26 
        	rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido, "+
        						"hic.codigo_adm_fk AS codigo2, "+
        						"CONCAT(hic.fecha,' ', hic.hora) AS hora_Historia, "+
        						"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(hic.fecha,' ', hic.hora),CONCAT(adm.fecha_registro,' ', adm.hora_registro)))/60) "+
        						"AS Minutos_Admisiones_HistoriaClinica, adm.adm_numero_ingreso "+
        						"FROM `adm_admisiones` adm ,`hic_adm_formatos_pac` hic, adm_paciente pac, adm_convenio ac, adm_entidad ae  "+
        						"WHERE hic.codigo_adm_fk = adm.adm_numero_ingreso "+
        						"AND adm.`pac_codigo_paciente_fk`=pac.pac_codigo_paciente "+
        						"AND hic.`codigo_for_fk`=26 "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND adm.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk ");
        	}else{
        		if(tipo==1){
        		rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido, "+
						"hic.codigo_adm_fk AS codigo2, "+
						"CONCAT(hic.fecha,' ', hic.hora) AS hora_Historia, "+
						"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(hic.fecha,' ', hic.hora),CONCAT(adm.fecha_registro,' ', adm.hora_registro)))/60) "+
						"AS Minutos_Admisiones_HistoriaClinica, adm.adm_numero_ingreso "+
						"FROM `adm_admisiones` adm ,`hic_adm_formatos_pac` hic, adm_paciente pac, adm_convenio ac, adm_entidad ae  "+
						"WHERE hic.codigo_adm_fk = adm.adm_numero_ingreso "+
						"AND adm.`pac_codigo_paciente_fk`=pac.pac_codigo_paciente "+
						"AND hic.`codigo_for_fk`=26  "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
						"AND adm.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
						"AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' ");
        		}else{
        			if(ftent.equals("todas")&&(tipo==2)){
        				
        				rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido, "+
        						"hic.codigo_adm_fk AS codigo2, "+
        						"CONCAT(hic.fecha,' ', hic.hora) AS hora_Historia, "+
        						"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(hic.fecha,' ', hic.hora),CONCAT(adm.fecha_registro,' ', adm.hora_registro)))/60) "+
        						"AS Minutos_Admisiones_HistoriaClinica, adm.adm_numero_ingreso "+
        						"FROM `adm_admisiones` adm ,`hic_adm_formatos_pac` hic, adm_paciente pac, adm_convenio ac, adm_entidad ae  "+
        						"WHERE hic.codigo_adm_fk = adm.adm_numero_ingreso "+
        						"AND adm.`pac_codigo_paciente_fk`=pac.pac_codigo_paciente "+
        						"AND hic.`codigo_for_fk`=43  "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        						"AND adm.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"AND ae.ent_nit=ac.ent_nit_contratante_fk ");
        				
        			}else{
        				if(tipo==2){
        					rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido, "+
        							"hic.codigo_adm_fk AS codigo2, "+
        							"CONCAT(hic.fecha,' ', hic.hora) AS hora_Historia, "+
        							"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(hic.fecha,' ', hic.hora),CONCAT(adm.fecha_registro,' ', adm.hora_registro)))/60) "+
        							"AS Minutos_Admisiones_HistoriaClinica, adm.adm_numero_ingreso "+
        							"FROM `adm_admisiones` adm ,`hic_adm_formatos_pac` hic, adm_paciente pac, adm_convenio ac, adm_entidad ae  "+
        							"WHERE hic.codigo_adm_fk = adm.adm_numero_ingreso "+
        							"AND adm.`pac_codigo_paciente_fk`=pac.pac_codigo_paciente "+
        							"AND hic.`codigo_for_fk`=43  "+
        							"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        							"AND adm.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        							"AND ae.ent_nit=ac.ent_nit_contratante_fk AND ae.ent_nit='"+ftent+"' ");
        				}
        			}
        		}
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarMinHC "+ex);
        }	
        return rs;
    }

	public java.sql.ResultSet BuscarMinApoyD(String Fechai,String Fechaf,String ftent){	
		/**
		 * Consulta los minutos transcurrido desde el momento de la generacion de la orden de imagenologia hasta su aprobacion
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT ice.fecha_cita, ice.hora_cita, ice.fecha, ice.hora, "+
        				  		"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(ice.fecha, ' ', ice.hora),CONCAT(ice.fecha_cita,' ',ice.hora_cita))) / 60) , ent.nombre_entidad, pac.pac_codigo_paciente,ice.codigoExa_fk "+
        				  		"FROM  img_citas_espera ice, adm_paciente pac, img_subexa isu,  adm_convenio ac, adm_entidad ent "+
        				  		"WHERE ice.codigoPac_fk = pac.pac_codigo_paciente  "+
        				  		"AND ice.codigoExa_fk = isu.codigo "+
        				  		"AND ice.estado = 1 "+
        				  		"AND ice.fecha_cita BETWEEN '"+Fechai+"'   AND '"+Fechaf+"'  "+
        				  		"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
        				  		"AND ent.ent_nit = ac.ent_nit_contratante_fk ");
        		
        	/*System.out.println("SELECT ice.fecha_cita, ice.hora_cita, ice.fecha, ice.hora, "+
				  		"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(ice.fecha, ' ', ice.hora),CONCAT(ice.fecha_cita,' ',ice.hora_cita))) / 60) , ent.nombre_entidad, pac.pac_codigo_paciente,ice.codigoExa_fk "+
				  		"FROM  img_citas_espera ice, adm_paciente pac, img_subexa isu,  adm_convenio ac, adm_entidad ent "+
				  		"WHERE ice.codigoPac_fk = pac.pac_codigo_paciente  "+
				  		"AND ice.codigoExa_fk = isu.codigo "+
				  		"AND ice.estado = 1 "+
				  		"AND ice.fecha_cita BETWEEN '"+Fechai+"'   AND '"+Fechaf+"'  "+
				  		"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
				  		"AND ent.ent_nit = ac.ent_nit_contratante_fk ");*/
        		
        	}else{
        		
        		rs=st.executeQuery("SELECT ice.fecha_cita, ice.hora_cita, ice.fecha, ice.hora, "+
				  		"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(ice.fecha, ' ', ice.hora),CONCAT(ice.fecha_cita,' ',ice.hora_cita))) / 60) , ent.nombre_entidad, pac.pac_codigo_paciente,ice.codigoExa_fk "+
				  		"FROM  img_citas_espera ice, adm_paciente pac, img_subexa isu,  adm_convenio ac, adm_entidad ent "+
				  		"WHERE ice.codigoPac_fk = pac.pac_codigo_paciente  "+
				  		"AND ice.codigoExa_fk = isu.codigo "+
				  		"AND ice.estado = 1 "+
				  		"AND ice.fecha_cita BETWEEN '"+Fechai+"'   AND '"+Fechaf+"'  "+
				  		"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
				  		"AND ent.ent_nit = ac.ent_nit_contratante_fk AND ent.ent_nit='"+ftent+"' ");
        		
        	/*	System.out.println("SELECT ice.fecha_cita, ice.hora_cita, ice.fecha, ice.hora, "+
				  		"ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(ice.fecha, ' ', ice.hora),CONCAT(ice.fecha_cita,' ',ice.hora_cita))) / 60) , ent.nombre_entidad, pac.pac_codigo_paciente,ice.codigoExa_fk "+
				  		"FROM  img_citas_espera ice, adm_paciente pac, img_subexa isu,  adm_convenio ac, adm_entidad ent "+
				  		"WHERE ice.codigoPac_fk = pac.pac_codigo_paciente  "+
				  		"AND ice.codigoExa_fk = isu.codigo "+
				  		"AND ice.estado = 1 "+
				  		"AND ice.fecha_cita BETWEEN '"+Fechai+"'   AND '"+Fechaf+"'  "+
				  		"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
				  		"AND ent.ent_nit = ac.ent_nit_contratante_fk AND ent.ent_nit='"+ftent+"' ");*/
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarMinApoyD "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarOrdenesImg(String Fechai,String Fechaf,String ftent,String Img){	
		/**
		 * Consulta el Numero de ordenes realizadas por tipo de grupo, agrupa la consulta por tipo de examen
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT isu.nombre, COUNT(ice.codigo) "+
        							"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
        							"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
        							"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
        							"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
        							"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
        							"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
        							"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
        							"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
        							"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' "+
        							"AND ice.estado=1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY isu.nombre ");
        		
         		System.out.println("SELECT 	isu.nombre, COUNT(ice.codigo) "+
						"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
						"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
						"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
						"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
						"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
						"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
						"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
						"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
						"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' "+
						"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY isu.nombre ");
        		
        		
        	}else{
        		
        		rs=st.executeQuery("SELECT isu.nombre,	COUNT(ice.codigo) "+
						"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
						"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
						"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
						"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
						"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
						"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
						"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
						"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
						"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' AND ent.ent_nit='"+ftent+"' "+
						"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY isu.nombre ");
        		
        		System.out.println("SELECT isu.nombre,	COUNT(ice.codigo) "+
						"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
						"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
						"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
						"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
						"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
						"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
						"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
						"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
						"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' AND ent.ent_nit='"+ftent+"' "+
						"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY isu.nombre ");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdenesImg "+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarOrdEntImg(String Fechai,String Fechaf,String ftent,String Img){	
		/**
		 * Consulta el Numero de ordenes realizadas por tipo de grupo, agrupado por eps
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	System.out.println("BuscarOrdEntImg ftent"+ftent);
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT ent.nombre_entidad , COUNT(ice.codigo) "+
        							"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
        							"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
        							"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
        							"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
        							"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
        							"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
        							"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
        							"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
        							"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' "+
        							"AND ice.estado=1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY ent.nombre_entidad ");
        		
         		System.out.println("SELECT 	ent.nombre_entidad, COUNT(ice.codigo) "+
						"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
						"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
						"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
						"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
						"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
						"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
						"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
						"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
						"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' "+
						"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY ent.nombre_entidad ");
        		
        		
        	}else{
        		
        		rs=st.executeQuery("SELECT ent.nombre_entidad,	COUNT(ice.codigo) "+
						"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
						"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
						"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
						"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
						"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
						"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
						"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
						"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
						"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' AND ent.ent_nit='"+ftent+"' "+
						"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY ent.nombre_entidad ");
        		System.out.println("SELECT ent.nombre_entidad,	COUNT(ice.codigo) "+
						"FROM  hic_orden ho, hic_detalleordenimagenes himg, img_citas_espera ice, adm_paciente pac, "+
						"adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig "+
						"WHERE ho.codigo = himg.CodOrdImg_fk AND ho.estado = 1 "+
						"AND ho.TipoOrden = 2 AND ice.codigoPac_fk = ho.CodPac_fk "+
						"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
						"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
						"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk  "+
						"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
						"AND isu.cod_gruFk=ig.codigo AND isu.codigo=ice.codigoExa_fk AND ig.codigo='"+Img+"' AND ent.ent_nit='"+ftent+"' "+
						"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'   GROUP BY ent.nombre_entidad ");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdEntImg "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarOrdEntLab(String Fechai,String Fechaf,String ftent,String Lab){	
		/**
		 * Consulta el Numero de ordenes realizadas por tipo de grupo, agrupado por eps
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo), ent.nombre_entidad "+
        							"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent, lab_subarea ls "+
        							"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora "+
        							"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk and ls.codigo='"+Lab+"' "+
        							"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ls.codigo=hdl.CodExa_fk AND opl.codigo_estudio=ls.codigo "+
        							"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' GROUP BY ent.nombre_entidad ");
        		
         		
        		
        		
        	}else{
        		
          		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo), ent.nombre_entidad "+
						"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent, lab_subarea ls "+
						"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora "+
						"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk and ls.codigo='"+Lab+"' AND ent.ent_nit='"+ftent+"' "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ls.codigo=hdl.CodExa_fk AND opl.codigo_estudio=ls.codigo "+
						"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' GROUP BY ent.nombre_entidad ");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdEntImg "+ex);
        }	
        return rs;
	}
	
	
	
	
	public java.sql.ResultSet BuscarOrdEntPat(String Fechai,String Fechaf,String ftent,String Pat){	
		/**
		 * Consulta el Numero de ordenes realizadas por tipo de grupo, agrupado por eps
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT hfr.nombre_formato, aent.nombre_entidad, COUNT(afc.codigo) "+
						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
						"WHERE hfr.codigo=hfa.codigo_formato_fk "+
						"AND har.codigo=hfa.codigo_area_fk  "+
						"AND afc.codigo_for_fk=hfr.codigo "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND afc.estado=1 "+
						"AND hfr.codigo='"+Pat+"' "+				
						"AND afc.codigo_usu_fk=sgu.usu_codigo  "+
						"AND sdt.dat_codigo=sgu.dat_codigo_fk  "+
						"AND pdc.codAsigForm_fk=afc.codigo  "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND aent.ent_nit=acv.ent_nit_contratante_fk "+
						"AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' group by aent.nombre_entidad ");
        		
        	}else{
        		
        		rs=st.executeQuery("SELECT hfr.nombre_formato, aent.nombre_entidad, COUNT(afc.codigo) "+
						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
						"WHERE hfr.codigo=hfa.codigo_formato_fk "+
						"AND har.codigo=hfa.codigo_area_fk  "+
						"AND afc.codigo_for_fk=hfr.codigo "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND afc.estado=1 "+
						"AND hfr.codigo='"+Pat+"' "+				
						"AND afc.codigo_usu_fk=sgu.usu_codigo  "+
						"AND sdt.dat_codigo=sgu.dat_codigo_fk  "+
						"AND pdc.codAsigForm_fk=afc.codigo  "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND aent.ent_nit=acv.ent_nit_contratante_fk "+
						"AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk AND aent.ent_nit='"+ftent+"' "+
						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' group by aent.nombre_entidad ");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdEntPat "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarOrdeServLab(String Fechai,String Fechaf,String ftent,String Lab){	
		/**
		 * Consulta el Numero de ordenes realizadas por tipo de grupo, agrupado por Servicio 
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo), ls.nombre, acs.pabellon,acs.ubicacion_cama, acs.codigocama "+
        						"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent, lab_subarea ls, adm_admisiones aa, adm_censo_cama acs "+
        						"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora  "+
        						"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
        						"AND aa.pac_codigo_paciente_fk=ho.CodPac_fk AND aa.adm_numero_ingreso=ho.CodAdm_fk AND aa.pac_codigo_paciente_fk=opl.codigo_pac_fk "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ls.codigo=hdl.CodExa_fk AND opl.codigo_estudio=ls.codigo "+
        						"AND acs.cen_numero_cama=aa.cen_numero_cama_fk and ls.codigo ='"+Lab+"' "+
        						"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' GROUP BY acs.ubicacion_cama");
        		
               		
        		
        	}else{
        		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo), ls.nombre, acs.pabellon,acs.ubicacion_cama, acs.codigocama "+
						"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent, lab_subarea ls, adm_admisiones aa, adm_censo_cama acs "+
						"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora  "+
						"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente "+
						"AND aa.pac_codigo_paciente_fk=ho.CodPac_fk AND aa.adm_numero_ingreso=ho.CodAdm_fk AND aa.pac_codigo_paciente_fk=opl.codigo_pac_fk "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ls.codigo=hdl.CodExa_fk AND opl.codigo_estudio=ls.codigo "+
						"AND acs.cen_numero_cama=aa.cen_numero_cama_fk and ls.codigo ='"+Lab+"' AND ent.ent_nit='"+ftent+"' "+
						"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' GROUP BY acs.ubicacion_cama");
        		
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdeServLab "+ex);
        }	
        return rs;
	}
	
	
	
	public java.sql.ResultSet BuscarOrdeServPat(String Fechai,String Fechaf,String ftent,String Pat){	
		/**
		 * Consulta el Numero de ordenes realizadas por tipo de grupo, agrupado por Servicio 
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT hfr.nombre_formato, aent.nombre_entidad, COUNT(afc.codigo) "+
						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
						"WHERE hfr.codigo=hfa.codigo_formato_fk "+
						"AND har.codigo=hfa.codigo_area_fk  "+
						"AND afc.codigo_for_fk=hfr.codigo "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND afc.estado=1 "+
						"AND hfr.codigo='"+Pat+"' "+				
						"AND afc.codigo_usu_fk=sgu.usu_codigo  "+
						"AND sdt.dat_codigo=sgu.dat_codigo_fk  "+
						"AND pdc.codAsigForm_fk=afc.codigo  "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND aent.ent_nit=acv.ent_nit_contratante_fk "+
						"AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' group by aent.nombre_entidad ");
        		
               		
        		
        	}else{
        		
        		rs=st.executeQuery("SELECT hfr.nombre_formato, aent.nombre_entidad, COUNT(afc.codigo) "+
						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
						"WHERE hfr.codigo=hfa.codigo_formato_fk "+
						"AND har.codigo=hfa.codigo_area_fk  "+
						"AND afc.codigo_for_fk=hfr.codigo "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND afc.estado=1 "+
						"AND hfr.codigo='"+Pat+"' "+				
						"AND afc.codigo_usu_fk=sgu.usu_codigo  "+
						"AND sdt.dat_codigo=sgu.dat_codigo_fk  "+
						"AND pdc.codAsigForm_fk=afc.codigo  "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND aent.ent_nit=acv.ent_nit_contratante_fk "+
						"AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk  AND aent.ent_nit='"+ftent+"' "+
						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' group by aent.nombre_entidad ");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdeServPat "+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarOrdeServImg(String Fechai,String Fechaf,String ftent,String Img){	
		/**
		 * Consulta el Numero de ordenes realizadas por tipo de grupo, agrupado por Servicio 
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT acc.pabellon, acc.ubicacion_cama, COUNT(ice.codigo) "+
        							"FROM hic_orden ho,hic_detalleordenimagenes himg,img_citas_espera ice, "+
        							"adm_paciente pac, adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig, "+
        							"adm_censo_cama acc, adm_admisiones aa " +
        							"WHERE ho.codigo = himg.CodOrdImg_fk  AND ho.estado = 1  AND ho.TipoOrden = 2 "+
        							"AND ice.codigoPac_fk = ho.CodPac_fk  AND himg.CodEstIma_fk = ice.codigoExa_fk  AND ice.fecha_cita = ho.fecha "+
        							"AND ice.hora_cita = ho.hora  AND pac.pac_codigo_paciente = ice.codigoPac_fk "+
        							"AND pac.pac_codigo_paciente = ho.CodPac_fk  AND pac.conv_numero_contrato_fk = ac.conv_numero_contrato "+
        							"AND ent.ent_nit = ac.ent_nit_contratante_fk AND aa.adm_numero_ingreso=ho.CodAdm_fk "+
        							"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente  AND aa.cen_numero_cama_fk=acc.cen_numero_cama "+
        							"AND isu.cod_gruFk = ig.codigo AND isu.codigo = ice.codigoExa_fk  AND ig.codigo = '"+Img+"' "+
        							"AND ice.estado = 1  AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY acc.ubicacion_cama");
        		
               		
        		
        	}else{
        		rs=st.executeQuery("SELECT acc.pabellon, acc.ubicacion_cama, COUNT(ice.codigo) "+
						"FROM hic_orden ho,hic_detalleordenimagenes himg,img_citas_espera ice, "+
						"adm_paciente pac, adm_convenio ac, adm_entidad ent, img_subexa isu, img_grupo ig, "+
						"adm_censo_cama acc, adm_admisiones aa " +
						"WHERE ho.codigo = himg.CodOrdImg_fk  AND ho.estado = 1  AND ho.TipoOrden = 2 "+
						"AND ice.codigoPac_fk = ho.CodPac_fk  AND himg.CodEstIma_fk = ice.codigoExa_fk  AND ice.fecha_cita = ho.fecha "+
						"AND ice.hora_cita = ho.hora  AND pac.pac_codigo_paciente = ice.codigoPac_fk "+
						"AND pac.pac_codigo_paciente = ho.CodPac_fk  AND pac.conv_numero_contrato_fk = ac.conv_numero_contrato "+
						"AND ent.ent_nit = ac.ent_nit_contratante_fk AND aa.adm_numero_ingreso=ho.CodAdm_fk "+
						"AND aa.pac_codigo_paciente_fk=pac.pac_codigo_paciente  AND aa.cen_numero_cama_fk=acc.cen_numero_cama "+
						"AND isu.cod_gruFk = ig.codigo AND isu.codigo = ice.codigoExa_fk  AND ig.codigo = '"+Img+"' AND ent.ent_nit='"+ftent+"' "+
						"AND ice.estado = 1  AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"'  GROUP BY acc.ubicacion_cama");
        		
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdeServImg "+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarEnHicOrden(String fecha_cita, String Hora_cita, String codpac, String codexa, String Fechai,String Fechaf,String ftent){	
		/**
		 * Consulta si la orden ice se encuentra en hic_orden, esto para  validar que la orden hubiera sido fomulada y asi mantenga los mismo criterios de consulta 
		 * que la consulta de TOTAL DE ORDENES, no se incluye los parametros en la consulta BuscarMinApoyD, porque demoraria mucho asi q se divide la consulta. 
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT * "+
        						   "FROM hic_orden ho, hic_detalleordenimagenes himg, adm_paciente pac, adm_convenio ac, adm_entidad ent "+
        						   "WHERE ho.CodPac_fk='"+codpac+"' AND himg.CodOrdImg_fk=ho.codigo AND himg.CodEstIma_fk='"+codexa+"'  "+
        		 					"AND pac.pac_codigo_paciente=ho.CodPac_fk AND  ho.fecha='"+fecha_cita+"'  AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ac.ent_nit_contratante_fk=ent.ent_nit AND ho.hora='"+Hora_cita+"' LIMIT 1 ");
        	
        		System.out.println("SELECT * "+
						   "FROM hic_orden ho, hic_detalleordenimagenes himg, adm_paciente pac, adm_convenio ac, adm_entidad ent "+
						   "WHERE ho.CodPac_fk='"+codpac+"' AND himg.CodOrdImg_fk=ho.codigo AND himg.CodEstIma_fk='"+codexa+"'  "+
		 					"AND pac.pac_codigo_paciente=ho.CodPac_fk AND  ho.fecha='"+fecha_cita+"'  AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ac.ent_nit_contratante_fk=ent.ent_nit AND ho.hora='"+Hora_cita+"' LIMIT 1 ");
		 					
        	}else{
        		
        		rs=st.executeQuery("SELECT * "+
						   "FROM hic_orden ho, hic_detalleordenimagenes himg, adm_paciente pac, adm_convenio ac, adm_entidad ent "+
						   "WHERE ho.CodPac_fk='"+codpac+"' AND himg.CodOrdImg_fk=ho.codigo AND himg.CodEstIma_fk='"+codexa+"' and ho.fecha='"+fecha_cita+"' AND ho.hora='"+Hora_cita+"' AND ent.ent_nit='"+ftent+"' "+
        				   "AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ac.ent_nit_contratante_fk=ent.ent_nit LIMIT 1 ");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarEnHicOrden "+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarMinLab(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los minutos trancurridos desde la generacion de la orden de laboratorio en orden_pac_lab hasta su aprobacion en lab_resultado
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT ho.codigo,opl.codigo,lr.fecha, lr.hora, ho.fecha, ho.hora, opl.fecha, opl.hora, ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(lr.fecha, ' ', lr.hora),CONCAT(opl.fecha,' ',opl.hora))) / 60), ent.nombre_entidad "+
        						"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, lab_resultado lr, adm_paciente pac, adm_convenio ac , adm_entidad ent "+
        						"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk "+
        						"AND opl.codigo_estudio=hdl.CodExa_fk AND opl.estado=1 AND lr.codpac_fk=ho.CodPac_fk AND lr.codord_fk=opl.codigo AND lr.estado=1 "+
        						"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk AND pac.pac_codigo_paciente=lr.codpac_fk "+
        						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ho.fecha=opl.fecha AND ho.hora=opl.hora  "+
        						"AND ho.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY opl.codigo ");
		 					
        	}else{
        		
        		rs=st.executeQuery("SELECT ho.codigo,opl.codigo,lr.fecha, lr.hora, ho.fecha, ho.hora, opl.fecha, opl.hora, ROUND(TIME_TO_SEC(TIMEDIFF(CONCAT(lr.fecha, ' ', lr.hora),CONCAT(opl.fecha,' ',opl.hora))) / 60), ent.nombre_entidad "+
						"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, lab_resultado lr, adm_paciente pac, adm_convenio ac , adm_entidad ent "+
						"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk "+
						"AND opl.codigo_estudio=hdl.CodExa_fk AND opl.estado=1 AND lr.codpac_fk=ho.CodPac_fk AND lr.codord_fk=opl.codigo AND lr.estado=1 "+
						"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk AND pac.pac_codigo_paciente=lr.codpac_fk "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ent.ent_nit='"+ftent+"' AND ho.fecha=opl.fecha AND ho.hora=opl.hora "+
						"AND ho.fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' GROUP BY opl.codigo ");       		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarMinLab "+ex);
        }	
        return rs;
	}
	
	
	
	public java.sql.ResultSet BuscarDiasPat(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los minutos trancurridos desde la generacion de la orden de laboratorio en orden_pac_lab hasta su aprobacion en lab_resultado
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT hres.fecha, hres.hora, pdc.fechaentrega, pdc.fecharecibo, afc.codigo, afc.fecha, afc.hora,DATEDIFF(STR_TO_DATE(pdc.fechaentrega,'%d/%m/%Y'), STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y')) AS DIAS "+
        					"FROM pat_formato hfr,pat_area har,pat_pregunta hpr,pat_resultado hres,empresa emp,adm_paciente apac, "+
        					"pat_formato_area hfa,adm_entidad aent,adm_convenio acv, seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, "+
        					"pat_datoscomplementarios pdc,pat_area_pregunta pap WHERE hfr.codigo=hfa.codigo_formato_fk  AND har.codigo=hfa.codigo_area_fk "+
        					"AND hfr.codigo=hres.CodFormato_fk  AND har.codigo=hres.codArea_Fk  AND pap.codigo_pregunta_fk=hres.codPreg_fk "+
        					"AND pap.codigo_area_fk=hres.codArea_Fk  AND pap.codigo_pregunta_fk=hpr.codigo  AND afc.codigo_for_fk=hfr.codigo "+
        					"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  AND afc.estado=1  AND afc.fecha=hres.fecha "+
        					"AND afc.hora=hres.hora  AND afc.codigo_usu_fk=sgu.usu_codigo  AND sdt.dat_codigo=sgu.dat_codigo_fk "+
        					"AND pdc.codAsigForm_fk=afc.codigo  AND apac.pac_codigo_paciente=afc.codigo_pac_fk "+
        					"AND aent.ent_nit=acv.ent_nit_contratante_fk  AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk "+
        					"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		 					
        	}else{
        		
        		rs=st.executeQuery("SELECT hres.fecha, hres.hora, pdc.fechaentrega, pdc.fecharecibo, afc.codigo, afc.fecha, afc.hora,DATEDIFF(STR_TO_DATE(pdc.fechaentrega,'%d/%m/%Y'), STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y')) AS DIAS "+
    					"FROM pat_formato hfr,pat_area har,pat_pregunta hpr,pat_resultado hres,empresa emp,adm_paciente apac, "+
    					"pat_formato_area hfa,adm_entidad aent,adm_convenio acv, seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, "+
    					"pat_datoscomplementarios pdc,pat_area_pregunta pap WHERE hfr.codigo=hfa.codigo_formato_fk  AND har.codigo=hfa.codigo_area_fk "+
    					"AND hfr.codigo=hres.CodFormato_fk  AND har.codigo=hres.codArea_Fk  AND pap.codigo_pregunta_fk=hres.codPreg_fk "+
    					"AND pap.codigo_area_fk=hres.codArea_Fk  AND pap.codigo_pregunta_fk=hpr.codigo  AND afc.codigo_for_fk=hfr.codigo "+
    					"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  AND afc.estado=1  AND afc.fecha=hres.fecha "+
    					"AND afc.hora=hres.hora  AND afc.codigo_usu_fk=sgu.usu_codigo  AND sdt.dat_codigo=sgu.dat_codigo_fk "+
    					"AND pdc.codAsigForm_fk=afc.codigo  AND apac.pac_codigo_paciente=afc.codigo_pac_fk  AND aent.ent_nit='"+ftent+"' "+
    					"AND aent.ent_nit=acv.ent_nit_contratante_fk  AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk "+
    					"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");      		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarMinLab "+ex);
        }	
        return rs;
	}
	
	
	
	public java.sql.ResultSet BuscarTotalPat(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los minutos trancurridos desde la generacion de la orden de laboratorio en orden_pac_lab hasta su aprobacion en lab_resultado
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT pdc.fechaentrega, pdc.fecharecibo, afc.codigo, afc.fecha, afc.hora, COUNT(afc.codigo) "+
        							"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
        							"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
        							"WHERE hfr.codigo=hfa.codigo_formato_fk  AND har.codigo=hfa.codigo_area_fk  AND afc.codigo_for_fk=hfr.codigo "+
        							"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  AND afc.estado=1  AND afc.codigo_usu_fk=sgu.usu_codigo  "+
        							"AND sdt.dat_codigo=sgu.dat_codigo_fk  AND pdc.codAsigForm_fk=afc.codigo  AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
        							"AND aent.ent_nit=acv.ent_nit_contratante_fk  AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
        							"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		 					
        	}else{
        		
        		rs=st.executeQuery("SELECT pdc.fechaentrega, pdc.fecharecibo, afc.codigo, afc.fecha, afc.hora, COUNT(afc.codigo) "+
						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
						"WHERE hfr.codigo=hfa.codigo_formato_fk  AND har.codigo=hfa.codigo_area_fk  AND afc.codigo_for_fk=hfr.codigo "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  AND afc.estado=1  AND afc.codigo_usu_fk=sgu.usu_codigo  "+
						"AND sdt.dat_codigo=sgu.dat_codigo_fk  AND pdc.codAsigForm_fk=afc.codigo  AND apac.pac_codigo_paciente=afc.codigo_pac_fk AND aent.ent_nit='"+ftent+"' "+
						"AND aent.ent_nit=acv.ent_nit_contratante_fk  AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");    		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarMinLab "+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarTotalLab(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta los minutos trancurridos desde la generacion de la orden de laboratorio en orden_pac_lab hasta su aprobacion en lab_resultado
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo) "+
        							"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent "+
        							"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora "+
        							"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk "+
        							"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit "+
        							"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' ");
		 					
        	}else{
        		
        		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo) "+
						"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent "+
						"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora "+
						"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk "+
						"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ent.ent_nit='"+ftent+"' "+
						"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' ");	
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarTotalLab "+ex);
        }	
        return rs;
	}
	
	
	
	public java.sql.ResultSet BuscarOrdenesLab(String Fechai, String Fechaf, String ftent, String Lab ){	
		/**
		 * Consulta los minutos trancurridos desde la generacion de la orden de laboratorio en orden_pac_lab hasta su aprobacion en lab_resultado
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo), ls.nombre "+
        					"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent, lab_subarea ls "+
        					"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora "+
        					"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk and ls.codigo='"+Lab+"' "+
        					"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ls.codigo=hdl.CodExa_fk AND opl.codigo_estudio=ls.codigo "+
        					"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' GROUP BY ls.nombre ");
		 					
        	}else{
        		
        		rs=st.executeQuery("SELECT  ho.codigo,opl.codigo, hdl.CodExa_fk, COUNT(opl.codigo), ls.nombre "+
    					"FROM hic_orden ho, hic_detalleordenlaboratorio hdl, ord_pac_lab opl, adm_paciente pac, adm_convenio ac , adm_entidad ent, lab_subarea ls "+
    					"WHERE ho.codigo=hdl.CodOrdLab_fk  AND opl.codigo_pac_fk=ho.CodPac_fk AND opl.codigo_estudio=hdl.CodExa_fk  AND ho.fecha=opl.fecha AND ho.hora=opl.hora "+
    					"AND pac.pac_codigo_paciente=ho.CodPac_fk AND pac.pac_codigo_paciente=opl.codigo_pac_fk AND ent.ent_nit='"+ftent+"' and ls.codigo='"+Lab+"' "+
    					"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk AND ac.ent_nit_contratante_fk=ent.ent_nit AND ls.codigo=hdl.CodExa_fk AND opl.codigo_estudio=ls.codigo "+
    					"AND  opl.estado=1 AND ho.fecha BETWEEN  '"+Fechai+"' AND '"+Fechaf+"' GROUP BY ls.nombre ");	
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdenesLab "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarOrdenesPat(String Fechai, String Fechaf, String ftent, String Pat ){	
		/**
		 * Consulta los minutos trancurridos desde la generacion de la orden de laboratorio en orden_pac_lab hasta su aprobacion en lab_resultado
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("1")){
        		
        		rs=st.executeQuery("SELECT hfr.nombre_formato, COUNT(afc.codigo) "+
        						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
        						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
        						"WHERE hfr.codigo=hfa.codigo_formato_fk "+
        						"AND har.codigo=hfa.codigo_area_fk  "+
        						"AND afc.codigo_for_fk=hfr.codigo "+
        						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
        						"AND afc.estado=1 "+
        						"AND hfr.codigo='"+Pat+"' "+				
        						"AND afc.codigo_usu_fk=sgu.usu_codigo  "+
        						"AND sdt.dat_codigo=sgu.dat_codigo_fk  "+
        						"AND pdc.codAsigForm_fk=afc.codigo  "+
        						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
        						"AND aent.ent_nit=acv.ent_nit_contratante_fk "+
        						"AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk "+
        						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		
        		System.out.println("SELECT hfr.nombre_formato, COUNT(afc.codigo) "+
						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
						"WHERE hfr.codigo=hfa.codigo_formato_fk "+
						"AND har.codigo=hfa.codigo_area_fk  "+
						"AND afc.codigo_for_fk=hfr.codigo "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND afc.estado=1 "+
						"AND hfr.codigo='"+Pat+"' "+				
						"AND afc.codigo_usu_fk=sgu.usu_codigo  "+
						"AND sdt.dat_codigo=sgu.dat_codigo_fk  "+
						"AND pdc.codAsigForm_fk=afc.codigo  "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND aent.ent_nit=acv.ent_nit_contratante_fk "+
						"AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
		 					
        	}else{
        		
        		rs=st.executeQuery("SELECT hfr.nombre_formato, COUNT(afc.codigo) "+
						"FROM pat_formato hfr,pat_area har, empresa emp,adm_paciente apac, pat_formato_area hfa,adm_entidad aent,adm_convenio acv, "+
						"seg_usuario sgu,seg_datos_personales sdt,pat_adm_formatos_pac afc, pat_datoscomplementarios pdc "+
						"WHERE hfr.codigo=hfa.codigo_formato_fk "+
						"AND har.codigo=hfa.codigo_area_fk  "+
						"AND afc.codigo_for_fk=hfr.codigo "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND afc.estado=1 "+
						"AND hfr.codigo='"+Pat+"' "+				
						"AND afc.codigo_usu_fk=sgu.usu_codigo  "+
						"AND sdt.dat_codigo=sgu.dat_codigo_fk  "+
						"AND pdc.codAsigForm_fk=afc.codigo  "+
						"AND apac.pac_codigo_paciente=afc.codigo_pac_fk  "+
						"AND aent.ent_nit=acv.ent_nit_contratante_fk "+
						"AND acv.conv_numero_contrato=apac.conv_numero_contrato_fk  AND aent.ent_nit='"+ftent+"' "+
						"AND STR_TO_DATE(pdc.fecharecibo,'%d/%m/%Y') BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	
        }
        }catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarOrdenesLab "+ex);
        }	
        return rs;
	}
	
	public java.sql.ResultSet BuscarTipoExaImagenologia(){	
		/**
		 * Este busca todos los grupos de imagenologia que existen. 
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery(" SELECT * FROM img_grupo ");
        	
        		
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarTipoExaImagenologia "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarTipoExaLabo(){	
		/**
		 * Este busca todos los tipo de examen de laboratorio que existen
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery(" SELECT * FROM lab_subarea where codigo!=0  ");
        	
        		
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarTipoExaLabo "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarTipoExaPatologia(){	
		/**
		 * Este busca todos los grupos de imagenologia que existen. 
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	
        	rs=st.executeQuery(" SELECT * FROM pat_formato ");
        	
        		
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarTipoExaPatologia "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarTotalOrdAdiag(String Fechai,String Fechaf,String ftent){	
		/**
		 * Consulta los minutos transcurrido desde el momento de la generacion de la orden de imagenologia hasta su aprobacion
		 */
      java.sql.ResultSet rs=null;
        Statement st = null;
        
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        		
        		rs=st.executeQuery("SELECT COUNT(ice.codigo) "+
        							"FROM  hic_orden ho,hic_detalleordenimagenes himg,img_citas_espera ice, adm_paciente pac,adm_convenio ac,adm_entidad ent "+
        							"WHERE ho.codigo = himg.CodOrdImg_fk  AND ho.estado = 1 "+
        							"AND ho.TipoOrden = 2  AND ice.codigoPac_fk = ho.CodPac_fk  "+
        							"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
        							"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
        							"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk "+
        							"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk "+
        							"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        	}else{
        		
        		rs=st.executeQuery("SELECT COUNT(ice.codigo) "+
						"FROM  hic_orden ho,hic_detalleordenimagenes himg,img_citas_espera ice, adm_paciente pac,adm_convenio ac,adm_entidad ent "+
						"WHERE ho.codigo = himg.CodOrdImg_fk  AND ho.estado = 1 "+
						"AND ho.TipoOrden = 2  AND ice.codigoPac_fk = ho.CodPac_fk  "+
						"AND himg.CodEstIma_fk = ice.codigoExa_fk "+
						"AND ice.fecha_cita=ho.fecha AND ice.hora_cita=ho.hora "+
						"AND pac.pac_codigo_paciente=ice.codigoPac_fk AND pac.pac_codigo_paciente= ho.CodPac_fk "+
						"AND pac.conv_numero_contrato_fk=ac.conv_numero_contrato AND ent.ent_nit=ac.ent_nit_contratante_fk AND ent.ent_nit='"+ftent+"' "+
						"AND ice.estado = 1 AND ice.fecha_cita BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ");
        		
        	}
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarTotalOrdAdiag "+ex);
        }	
        return rs;
	}
	
	
	public java.sql.ResultSet BuscarPatologia(String Fechai, String Fechaf,String ftent, String Serv, int rep,String codDiag){	
		/**
		 * Consulta las diagnosticos mas comunes segun fecha 
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if((ftent.equals("todas"))&&(Serv.equals("todas"))){
        		if(rep==0){
		        	rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico  "+
		        								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae "+
		        								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
		        								"AND hde.TipDiag='EG' "+
		        								"AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
		        								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
		        								"AND hde.CodDiag_fk=ci.codigo "+
		        								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
		        								"AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
		        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        								"AND aad.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		        								"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15");
        		}else{
        			if(codDiag.equals("0")){
        				
        				rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae "+
								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
								"AND hde.TipDiag='EG' "+
								"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
								"AND hde.CodDiag_fk=ci.codigo "+
								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
								"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
								"AND aad.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
								"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
        				
        			}else{
        				        			
	        			rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae "+
								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
								"AND hde.TipDiag='EG' "+
								"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
								"AND hde.CodDiag_fk=ci.codigo "+
								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
								"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk AND hde.codDiagnostico='"+codDiag+"'  "+
								"AND aad.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
								"AND aad.estado!=2  ");
        			}
        		}
        	
        	}else{
        		if(((ftent.equals("todas"))&&(Serv.equals("1")))){
        			if(rep==0){
        			rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
        								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac, adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic "+
        								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
        								"AND hde.TipDiag='EG' "+
        								"AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
        								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
        								"AND hde.CodDiag_fk=ci.codigo "+
        								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        								"AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
        								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
        								"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
        								"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
        								"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) AND hic.estado=1 "+
        								"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15");
        			}else{
        				if(codDiag.equals("0")){
        					
        					rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico "+
									"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac, adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic "+
									"WHERE aad.adm_numero_ingreso=hde.codAdm "+
									"AND hde.TipDiag='EG' "+
									"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
									"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
									"AND hde.CodDiag_fk=ci.codigo "+
									"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
									"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
									"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
									"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
									"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
									"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) and hic.estado=1 "+
									"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
        				}else{
	        				rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico "+
									"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac, adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic "+
									"WHERE aad.adm_numero_ingreso=hde.codAdm "+
									"AND hde.TipDiag='EG' "+
									"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
									"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
									"AND hde.CodDiag_fk=ci.codigo "+
									"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
									"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
									"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
									"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
									"AND hic.codigo_pac_fk=pac.pac_codigo_paciente and hic.estado=1 "+
									"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43)   AND hde.codDiagnostico='"+codDiag+"' "+
									"AND aad.estado!=2 ");
        				}
        			}
        		}else{
        			if(Serv.equals("1")){
        				if(rep==0){
        				
        				rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac, adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic "+
								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
								"AND hde.TipDiag='EG' "+
								"AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
								"AND hde.CodDiag_fk=ci.codigo "+
								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
								"AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
								"AND hic.codigo_pac_fk=apac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
								"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) and hic.estado=1 "+
								"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15");
        				}else{
        					
        					if(codDiag.equals("0")){
        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico "+
	    								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac, adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic "+
	    								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	    								"AND hde.TipDiag='EG' "+
	    								"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
	    								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	    								"AND hde.CodDiag_fk=ci.codigo "+
	    								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
	    								"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	    								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	    								"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
	    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
	    								"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43) and hic.estado=1 "+
	    								"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
        					}else{       					
	        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico "+
	    								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac, adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic "+
	    								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	    								"AND hde.TipDiag='EG' "+
	    								"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
	    								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	    								"AND hde.CodDiag_fk=ci.codigo "+
	    								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
	    								"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	    								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	    								"AND hic.codigo_adm_fk=aad.adm_numero_ingreso and hic.estado=1  "+
	    								"AND hic.codigo_pac_fk=pac.pac_codigo_paciente AND ae.ent_nit='"+ftent+"' "+
	    								"AND (hic.codigo_for_fk=26 OR hic.codigo_for_fk=43)  AND hde.codDiagnostico='"+codDiag+"' "+
	    								"AND aad.estado!=2 ");
        					}
        				}
        			}else{
        				if(rep==0){
        				rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac, adm_convenio ac, adm_entidad ae "+
								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
								"AND hde.TipDiag='EG' "+
								"AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
								"AND hde.CodDiag_fk=ci.codigo "+
								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
								"AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
								"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								"AND ae.ent_nit='"+ftent+"' "+
								"AND aad.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
								"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15");
        				}else{
        					
        					if(codDiag.equals("0")){
        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico "+
	    								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac, adm_convenio ac, adm_entidad ae "+
	    								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	    								"AND hde.TipDiag='EG' "+
	    								"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
	    								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	    								"AND hde.CodDiag_fk=ci.codigo "+
	    								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
	    								"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	    								"AND ae.ent_nit=ac.ent_nit_contratante_fk  "+
	    								"AND ae.ent_nit='"+ftent+"' "+
	    								"AND aad.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
	    								"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
        						
        					}else{
        						
	        					rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico "+
	    								"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac, adm_convenio ac, adm_entidad ae "+
	    								"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	    								"AND hde.TipDiag='EG' "+
	    								"AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk "+
	    								"AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	    								"AND hde.CodDiag_fk=ci.codigo "+
	    								"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
	    								"AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
	    								"AND ae.ent_nit=ac.ent_nit_contratante_fk AND hde.codDiagnostico='"+codDiag+"' "+
	    								"AND ae.ent_nit='"+ftent+"' "+
	    								"AND aad.adm_numero_ingreso IN (SELECT hic.codigo_adm_fk FROM hic_adm_formatos_pac hic,adm_admisiones aad WHERE aad.adm_numero_ingreso=hic.codigo_adm_fk AND hic.estado=1 AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
	    								"AND aad.estado!=2 ");
        					}
        					
        				}
        				
        			}
        		}
        		
        	}
        	}
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarPatologia "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarPatologiaVal2(String Fechai, String Fechaf, String ftent,String Serv,String area, int rep,String codDiag){	
		 java.sql.ResultSet rs=null;
	        Statement st = null;
	        System.out.println("Serv"+Serv+" area"+area+" ftent"+ftent+" rep"+rep+"Fechai"+Fechai+"Fechaf"+Fechaf);
	        try{      
	        	Conexion con=new Conexion();
	        	st = con.conn.createStatement();
			   if((Serv.equals("23"))||(Serv.equals("24"))||(Serv.equals("25"))){
	        		if((ftent.equals("todas"))&&(area.equals("todas"))){
	        			if(Serv.equals("24")){
	        				//HOSP 
	        				if(rep==0){
	        					System.out.println("E1");
	        					rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	        					"AND hde.CodDiag_fk=ci.codigo "+
	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
	        					"AND hic.codigo_for_fk=hf.codigo "+
	        					"AND hic.codigo_for_fk=33 AND hic.estado=1  "+
	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
	        					
	        					System.out.println("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	    	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	    	        					"AND hde.CodDiag_fk=ci.codigo "+
	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
	    	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
	    	        					"AND hic.codigo_for_fk=hf.codigo "+
	    	        					"AND hic.codigo_for_fk=33 AND hic.estado=1  "+
	    	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
	        				
	        				}else{
	        					if(codDiag.equals("0")){
	        						System.out.println("E2");
	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
	        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
	        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	        	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	        	        					"AND hde.CodDiag_fk=ci.codigo "+
	        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
	        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
	        	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        	        					"AND hic.codigo_for_fk=hf.codigo "+
	        	        					"AND hic.codigo_for_fk=33 AND hic.estado=1  "+
	        	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
	        						
	        					}else{
	        						System.out.println("E3");
	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
	        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
	        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	        	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	        	        					"AND hde.CodDiag_fk=ci.codigo "+
	        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
	        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
	        	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
	        	        					"AND hic.codigo_for_fk=hf.codigo "+
	        	        					"AND hic.codigo_for_fk=33 AND hic.estado=1 AND hde.codDiagnostico='"+codDiag+"' "+
	        	        					"AND aad.estado!=2 ");	
	        						
	        						System.out.println("entrando a validacion de BuscarPatologiaVal2");
	        					}
	        				}
	        			}else{
	        				if(Serv.equals("23")){
	        					//UCI
	        					if(rep==0){
	        						System.out.println("E4");
	        						rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
	        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
	        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	        	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	        	        					"AND hde.CodDiag_fk=ci.codigo "+
	        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
	        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
	        	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
	        	        					"AND hic.codigo_for_fk=hf.codigo "+
	        	        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1  "+
	        	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
	    	        				
		        				}else{
		        					if(codDiag.equals("0")){
		        						System.out.println("E5");
		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
		        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
		        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
		        	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
		        	        					"AND hde.CodDiag_fk=ci.codigo "+
		        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
		        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
		        	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        	        					"AND hic.codigo_for_fk=hf.codigo "+
		        	        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1  "+
		        	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
		        						
		        					}else{
		        						System.out.println("E6");
		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
		        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
		        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
		        	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
		        	        					"AND hde.CodDiag_fk=ci.codigo "+
		        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
		        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
		        	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		        	        					"AND hic.codigo_for_fk=hf.codigo "+
		        	        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 AND hde.codDiagnostico='"+codDiag+"' "+
		        	        					"AND aad.estado!=2 ");
		        						
		        					}
		        				}
	        					
	        				}else{
	        					if(Serv.equals("25")){
	        						//AMBULATORIO
	        						if(rep==0){
	        							System.out.println("E7");
	        							rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
		        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
		        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
		        	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
		        	        					"AND hde.CodDiag_fk=ci.codigo "+
		        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
		        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
		        	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
		        	        					"AND hic.codigo_for_fk=hf.codigo "+
		        	        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1  "+
		        	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
	    	        				}else{
	    	        					if(codDiag.equals("0")){
	    	        						System.out.println("E8");
	    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
			        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
			        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
			        	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
			        	        					"AND hde.CodDiag_fk=ci.codigo "+
			        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
			        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
			        	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			        	        					"AND hic.codigo_for_fk=hf.codigo "+
			        	        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1  "+
			        	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
	    	        						
	    	        						
	    	        					}else{
	    	        						System.out.println("E9");
	    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
			        	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
			        	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
			        	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
			        	        					"AND hde.CodDiag_fk=ci.codigo "+
			        	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
			        	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			        	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
			        	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			        	        					"AND hic.codigo_for_fk=hf.codigo "+
			        	        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 AND hde.codDiagnostico='"+codDiag+"' "+
			        	        					"AND aad.estado!=2 ");
	    	        						
	    	        						
	    	        					}
	    	        				}
	        					}
	        					
	        					
	        					
	        				}
	        			}
	        		}else{
	        			if(ftent.equals("todas")){
	        				if(Serv.equals("24")){
			        				//HOSP 
			        				if(rep==0){
			        					System.out.println("E10");
			        					rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
			        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
			        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
			        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
			        					"AND hde.CodDiag_fk=ci.codigo "+
			        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
			        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
			        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
			        					"AND hic.codigo_for_fk=hf.codigo "+
			        					"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
			        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
			        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
			        					
			        				}else{
			        					if(codDiag.equals("0")){
			        						System.out.println("E11");
			        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
						        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
						        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
						        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
						        					"AND hde.CodDiag_fk=ci.codigo "+
						        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
						        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
						        					"AND hic.codigo_for_fk=hf.codigo "+
						        					"AND hic.codigo_for_fk=33 AND hic.estado=1 "+
						        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
						        					"AND aad.estado!=2  ORDER BY hde.codDiagnostico  ");
			        						
			        					}else{
			        						System.out.println("E12");
			        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
						        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
						        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
						        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
						        					"AND hde.CodDiag_fk=ci.codigo "+
						        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
						        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
						        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
						        					"AND hic.codigo_for_fk=hf.codigo "+
						        					"AND hic.codigo_for_fk=33 AND hic.estado=1  AND hde.codDiagnostico='"+codDiag+"' "+
						        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
						        					"AND aad.estado!=2  ORDER BY hde.codDiagnostico  ");
			        						
			        					}
			        				}
			        			}else{
			        				if(Serv.equals("23")){
			        					//UCI
			        					
			        					if(rep==0){
			        						System.out.println("E13");
			        						rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
						        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
						        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
						        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
						        					"AND hde.CodDiag_fk=ci.codigo "+
						        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
						        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
						        					"AND hic.codigo_for_fk=hf.codigo "+
						        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
						        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
						        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
			        						System.out.println("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
						        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
						        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
						        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
						        					"AND hde.CodDiag_fk=ci.codigo "+
						        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
						        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
						        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
						        					"AND hic.codigo_for_fk=hf.codigo "+
						        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
						        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
						        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
			    	        				
				        				}else{
				        					if(codDiag.equals("0")){
				        						System.out.println("E14");
				        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
							        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
							        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
							        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
							        					"AND hde.CodDiag_fk=ci.codigo "+
							        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
							        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
							        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							        					"AND hic.codigo_for_fk=hf.codigo "+
							        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
							        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							        					"AND aad.estado!=2  ORDER BY hde.codDiagnostico  ");
				        						
				        						System.out.println("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
							        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
							        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
							        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
							        					"AND hde.CodDiag_fk=ci.codigo "+
							        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
							        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
							        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							        					"AND hic.codigo_for_fk=hf.codigo "+
							        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 "+
							        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							        					"AND aad.estado!=2  ORDER BY hde.codDiagnostico  ");
				        						
				        					}else{
				        						System.out.println("E15");
				        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
							        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
							        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
							        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
							        					"AND hde.CodDiag_fk=ci.codigo "+
							        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
							        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
							        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							        					"AND hic.codigo_for_fk=hf.codigo "+
							        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34)  AND hic.estado=1  AND hde.codDiagnostico='"+codDiag+"' "+
							        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							        					"AND aad.estado!=2   ");
				        						
				        						System.out.println("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
							        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
							        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
							        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
							        					"AND hde.CodDiag_fk=ci.codigo "+
							        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
							        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
							        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
							        					"AND hic.codigo_for_fk=hf.codigo "+
							        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34)  AND hic.estado=1  AND hde.codDiagnostico='"+codDiag+"' "+
							        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							        					"AND aad.estado!=2   ");
				        						
				        					}
				        				}
			        					
			        				}else{
			        					if(Serv.equals("25")){
			        						//AMBULATORIO
			        						if(rep==0){
			        							System.out.println("E16");
			        							rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
							        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
							        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
							        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
							        					"AND hde.CodDiag_fk=ci.codigo "+
							        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk "+
							        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
							        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
							        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
							        					"AND hic.codigo_for_fk=hf.codigo "+
							        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 "+
							        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
							        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
			        	        				
			    	        				}else{
			    	        					if(codDiag.equals("0")){
			    	        						System.out.println("E17");
			    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
								        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
								        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
								        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
								        					"AND hde.CodDiag_fk=ci.codigo "+
								        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
								        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
								        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
								        					"AND hic.codigo_for_fk=hf.codigo "+
								        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 "+
								        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
								        					"AND aad.estado!=2  ORDER BY hde.codDiagnostico  ");
			    	        						
			    	        					}else{
			    	        						
			    	        						System.out.println("E18");
					        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
								        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
								        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
								        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
								        					"AND hde.CodDiag_fk=ci.codigo "+
								        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk "+
								        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
								        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
								        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
								        					"AND hic.codigo_for_fk=hf.codigo "+
								        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49)  AND hic.estado=1  AND hde.codDiagnostico='"+codDiag+"' "+
								        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
								        					"AND aad.estado!=2  ORDER BY hde.codDiagnostico  ");
			    	        					}
			    	        				}
			        					}
			        					
			        					
			        					
			        				}
			        			}
	        				
	        				
	        				
	        			}else{//termina validacion  de entidades
	        				if(area.equals("todas")){
	        					
			        					if(Serv.equals("24")){
			    	        				//HOSP 
			    	        				if(rep==0){
			    	        					System.out.println("E19");
			    	        					rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
			    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
			    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
			    	    	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
			    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
			    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
			    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
			    	    	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
			    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
			    	    	        					"AND hic.codigo_for_fk=33 AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
			    	    	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
			    	        				
			    	        				}else{
			    	        					if(codDiag.equals("0")){
			    	        						System.out.println("E20");
			    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
				    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
				    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
				    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
				    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
				    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
				    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
				    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
				    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
				    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
				    	    	        					"AND hic.codigo_for_fk=33 AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
				    	    	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
			    	        						
			    	        					}else{
			    	        						System.out.println("E21");
			    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
				    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
				    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
				    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
				    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
				    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
				    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
				    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
				    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
				    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
				    	    	        					"AND hic.codigo_for_fk=33 AND hic.estado=1 AND ae.ent_nit='"+ftent+"' AND hde.codDiagnostico='"+codDiag+"' "+
				    	    	        					"AND aad.estado!=2 ");
			    	        						
			    	        					}
			    	        				}
			    	        			}else{
			    	        				if(Serv.equals("23")){
			    	        					//UCI
			    	        					
			    	        					if(rep==0){
			    	        						System.out.println("E23");
			    	        						rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
				    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
				    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
				    	    	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
				    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
				    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
				    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
				    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
				    	    	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
				    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
				    	    	        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
				    	    	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
			    		        				}else{
			    		        					if(codDiag.equals("0")){
			    		        						System.out.println("E24");
			    		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
					    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
					    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
					    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
					    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
					    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
					    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
					    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
					    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
					    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
					    	    	        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
					    	    	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
			    		        						
			    		        					}else{
			    		        						System.out.println("E25");
			    		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
					    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
					    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
					    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
					    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
					    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
					    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
					    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
					    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
					    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
					    	    	        					"AND (hic.codigo_for_fk=32 OR hic.codigo_for_fk=34) AND hic.estado=1 AND ae.ent_nit='"+ftent+"' AND hde.codDiagnostico='"+codDiag+"' "+
					    	    	        					"AND aad.estado!=2 ");
			    		        						
			    		        					}
			    		        				}
			    	        					
			    	        				}else{
			    	        					if(Serv.equals("25")){
			    	        						//AMBULATORIO
			    	        						if(rep==0){
			    	        							System.out.println("E26");
			    	        							rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
					    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
					    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
					    	    	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
					    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
					    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
					    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
					    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
					    	    	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
					    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
					    	    	        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
					    	    	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
			    	        	        				
			    	    	        				}else{
			    	    	        					if(codDiag.equals("0")){
			    	    	        						System.out.println("E27");
			    	    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
						    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
						    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
						    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
						    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
						    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
						    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
						    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
						    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
						    	    	        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
						    	    	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico ");
			    	    	        						
			    	    	        						
			    	    	        					}else{
			    	    	        						System.out.println("E28");
			    	    	        						
			    	    	        						rs=st.executeQuery("SELECT  pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
						    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
						    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
						    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
						    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
						    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
						    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
						    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
						    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
						    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
						    	    	        					"AND (hic.codigo_for_fk=44 OR hic.codigo_for_fk=49) AND hic.estado=1 AND ae.ent_nit='"+ftent+"' AND hde.codDiagnostico='"+codDiag+"' "+
						    	    	        					"AND aad.estado!=2 ");
			    	    	        						
			    	    	        					}
			    	    	        				}
			    	        					}
			    	        					
			    	        					
			    	        					
			    	        				}
			    	        			}
	        					
	        					
	        				}else{//termina validacion de area
	        				
	        					if(Serv.equals("24")){
	    	        				//HOSP 
	    	        				if(rep==0){
	    	        					System.out.println("E29");
	    	        					rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
	    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
	    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
	    	    	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
	    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
	    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
	    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
	    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
	    	    	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
	    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
	    	    	        					"AND hic.codigo_for_fk=33 AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
	    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
	    	    	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
	    	        				
	    	        				}else{
	    	        					if(codDiag.equals("0")){
	    	        						System.out.println("E30");
	    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
		    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
		    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
		    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
		    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
		    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
		    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
		    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
		    	    	        					"AND hic.codigo_for_fk=33 AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
		    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    	    	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico  ");
	    	        						
	    	        					}else{
	    	        						System.out.println("E31");
	    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
		    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
		    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
		    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
		    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
		    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
		    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
		    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
		    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
		    	    	        					"AND hic.codigo_for_fk=33 AND hic.estado=1 AND ae.ent_nit='"+ftent+"' AND hde.codDiagnostico='"+codDiag+"'  "+
		    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    	    	        					"AND aad.estado!=2  ");
	    	        						
	    	        					}
	    	        				}
	    	        			}else{
	    	        				if(Serv.equals("23")){
	    	        					//UCI
	    	        					
	    	        					if(rep==0){
	    	        						System.out.println("E32");
	    	        						rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
		    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
		    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
		    	    	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
		    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
		    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
		    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
		    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
		    	    	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
		    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
		    	    	        					"AND (hic.codigo_for_fk=32 OR  hic.codigo_for_fk=34) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
		    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
		    	    	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
	    	        						
	    	    	        				
	    		        				}else{
	    		        					if(codDiag.equals("0")){
	    		        						System.out.println("E33");
	    		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
			    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
			    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
			    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
			    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
			    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
			    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
			    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
			    	    	        					"AND (hic.codigo_for_fk=32 OR  hic.codigo_for_fk=34) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
			    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
			    	    	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico  ");
	    		        						
	    		        					}else{
	    		        						System.out.println("E34");
	    		        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
			    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
			    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
			    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
			    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
			    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
			    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
			    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
			    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
			    	    	        					"AND (hic.codigo_for_fk=32 OR  hic.codigo_for_fk=34) AND hic.estado=1 AND ae.ent_nit='"+ftent+"' AND hde.codDiagnostico='"+codDiag+"'  "+
			    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
			    	    	        					"AND aad.estado!=2  ");
	    		        					}
	    		        				}
	    	        					
	    	        				}else{
	    	        					if(Serv.equals("25")){
	    	        						//AMBULATORIO
	    	        						if(rep==0){
	    	        							System.out.println("E35");
	    	        							rs=st.executeQuery("SELECT ci.nombre, COUNT(hde.codDiagnostico) AS cantidad , hde.codDiagnostico "+
			    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente apac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
			    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
			    	    	        					"AND hde.TipDiag='EG' AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
			    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
			    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=apac.conv_numero_contrato_fk  "+
			    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
			    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
			    	    	        					"AND hic.codigo_pac_fk=apac.pac_codigo_paciente "+
			    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
			    	    	        					"AND (hic.codigo_for_fk=44 OR  hic.codigo_for_fk=49) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
			    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
			    	    	        					"AND aad.estado!=2 GROUP BY hde.codDiagnostico ORDER BY cantidad DESC LIMIT 15 ");
	    	        	        				
	    	    	        				}else{
	    	    	        					if(codDiag.equals("0")){
	    	    	        						System.out.println("E36");
	    	    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
				    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
				    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
				    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
				    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
				    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
				    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
				    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
				    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
				    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
				    	    	        					"AND (hic.codigo_for_fk=44 OR  hic.codigo_for_fk=49) AND hic.estado=1 AND ae.ent_nit='"+ftent+"'  "+
				    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
				    	    	        					"AND aad.estado!=2 ORDER BY hde.codDiagnostico  ");
	    	    	        						
	    	    	        						
	    	    	        					}else{
	    	    	        						System.out.println("E37");
	    	    	        						rs=st.executeQuery("SELECT pac.tipo_documento, pac.numero_documento ,pac.nombre, pac.primer_apellido, pac.segundo_apellido, pac.genero, aad.estado,aad.adm_numero_ingreso,aad.fecha_registro, ae.nombre_entidad, aad.adm_numero_ingreso, pac.pac_codigo_paciente, ((YEAR(aad.fecha_registro))-YEAR(pac.fecha_nacimiento)) as edad, ci.nombre, hde.codDiagnostico  "+
				    	    	        					"FROM adm_admisiones aad,adm_censo_cama cama,hic_diagnosticoegreso hde,cie10 ci,adm_paciente pac,adm_convenio ac, adm_entidad ae, hic_adm_formatos_pac hic, hic_formato hf "+
				    	    	        					"WHERE aad.adm_numero_ingreso=hde.codAdm "+
				    	    	        					"AND hde.TipDiag='EG' AND pac.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.cen_numero_cama_fk=cama.cen_numero_cama "+
				    	    	        					"AND hde.CodDiag_fk=ci.codigo "+
				    	    	        					"AND aad.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' AND ac.conv_numero_contrato=pac.conv_numero_contrato_fk  "+
				    	    	        					"AND ae.ent_nit=ac.ent_nit_contratante_fk "+
				    	    	        					"AND hic.codigo_adm_fk=aad.adm_numero_ingreso "+
				    	    	        					"AND hic.codigo_pac_fk=pac.pac_codigo_paciente "+
				    	    	        					"AND hic.codigo_for_fk=hf.codigo "+
				    	    	        					"AND (hic.codigo_for_fk=44 OR  hic.codigo_for_fk=49) AND hic.estado=1 AND ae.ent_nit='"+ftent+"' AND hde.codDiagnostico='"+codDiag+"'  "+
				    	    	        					"AND aad.adm_numero_ingreso IN (SELECT aaa.adm_numero_ingreso FROM adm_historico_cama ahc, adm_censo_cama acc, adm_admisiones aaa WHERE ahc.adm_numero_ingreso_fk=aaa.adm_numero_ingreso AND ahc.cen_numero_cama_fk=acc.cen_numero_cama AND acc.codsubarea='"+area+"' AND aaa.fecha_registro BETWEEN '"+Fechai+"' AND '"+Fechaf+"' ) "+
				    	    	        					"AND aad.estado!=2  ");
	    	    	        					}
	    	    	        				}
	    	        					}
	    	        					
	    	        					
	    	        					
	    	        				}
	    	        			}
	        				
	        				}
	        			}
	        		}
			   }
	        	
	        }catch(Exception ex){
			    	System.out.println("Error en MetodoCalidad>>BuscarPatologiaVal2 "+ex);
	        }	
			  return rs;
	}
	
	
	
	
	public java.sql.ResultSet BuscarTraslados(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta las entidades mas usadas en limite de 10
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT acc.ubicacion_cama, ent.nombre_entidad, COUNT(acc.ubicacion_cama) "+
        						"FROM `adm_trasladourg_hosp` hosp,`adm_admisiones` adm, `adm_paciente` pac, `adm_convenio` con, `adm_entidad` ent, adm_censo_cama acc "+
        						"WHERE fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"AND hosp.CodAdm = adm.`adm_numero_ingreso` "+
        						"AND adm.`pac_codigo_paciente_fk`= pac.`pac_codigo_paciente` "+
        						"AND `pac`.`conv_numero_contrato_fk`=con.conv_numero_contrato "+
        						"AND acc.cen_numero_cama=hosp.CodCamaFin "+
        						"AND con.ent_nit_contratante_fk=ent.ent_nit GROUP BY acc.ubicacion_cama, ent.ent_nit ORDER BY acc.ubicacion_cama, ent.nombre_entidad  ");
        	}else{
        		
        		rs=st.executeQuery("SELECT acc.ubicacion_cama, ent.nombre_entidad, COUNT(acc.ubicacion_cama) "+
						"FROM `adm_trasladourg_hosp` hosp,`adm_admisiones` adm, `adm_paciente` pac, `adm_convenio` con, `adm_entidad` ent, adm_censo_cama acc "+
						"WHERE fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
						"AND hosp.CodAdm = adm.`adm_numero_ingreso` "+
						"AND adm.`pac_codigo_paciente_fk`= pac.`pac_codigo_paciente` "+
						"AND `pac`.`conv_numero_contrato_fk`=con.conv_numero_contrato "+
						"AND acc.cen_numero_cama=hosp.CodCamaFin "+
						"AND con.ent_nit_contratante_fk=ent.ent_nit AND ent.ent_nit='"+ftent+"' GROUP BY acc.ubicacion_cama, ent.ent_nit ORDER BY acc.ubicacion_cama, ent.nombre_entidad  ");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarTraslados "+ex);
        }	
        return rs;
    }
	
	
	public java.sql.ResultSet BuscarTrasUrg(String Fechai, String Fechaf, String ftent){	
		/**
		 * Consulta las entidades mas usadas en limite de 10
		 * 
		 */
        java.sql.ResultSet rs=null;
        Statement st = null;
        System.out.println("Fechai "+Fechai+"Fechaf "+Fechaf);
        try{
        	Conexion con=new Conexion();
        	st = con.conn.createStatement();
        	if(ftent.equals("todas")){
        	rs=st.executeQuery("SELECT  COUNT(acc.ubicacion_cama) "+
        						"FROM `adm_trasladourg_hosp` hosp,`adm_admisiones` adm, `adm_paciente` pac, `adm_convenio` con, `adm_entidad` ent, adm_censo_cama acc "+
        						"WHERE fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
        						"AND hosp.CodAdm = adm.`adm_numero_ingreso` "+
        						"AND adm.`pac_codigo_paciente_fk`= pac.`pac_codigo_paciente` "+
        						"AND `pac`.`conv_numero_contrato_fk`=con.conv_numero_contrato "+
        						"AND acc.cen_numero_cama=hosp.CodCamaFin "+
        						"AND con.ent_nit_contratante_fk=ent.ent_nit  ");
        	}else{
        		
        		rs=st.executeQuery("SELECT COUNT(acc.ubicacion_cama) "+
						"FROM `adm_trasladourg_hosp` hosp,`adm_admisiones` adm, `adm_paciente` pac, `adm_convenio` con, `adm_entidad` ent, adm_censo_cama acc "+
						"WHERE fecha BETWEEN '"+Fechai+"' AND '"+Fechaf+"' "+
						"AND hosp.CodAdm = adm.`adm_numero_ingreso` "+
						"AND adm.`pac_codigo_paciente_fk`= pac.`pac_codigo_paciente` "+
						"AND `pac`.`conv_numero_contrato_fk`=con.conv_numero_contrato "+
						"AND acc.cen_numero_cama=hosp.CodCamaFin "+
						"AND con.ent_nit_contratante_fk=ent.ent_nit AND ent.ent_nit='"+ftent+"'  ");
        	}
        	
        	
        }
        catch(Exception ex){
        	System.out.println("Error en MetodoCalidad>>BuscarTraslados "+ex);
        }	
        return rs;
    }
	
	
	
}
