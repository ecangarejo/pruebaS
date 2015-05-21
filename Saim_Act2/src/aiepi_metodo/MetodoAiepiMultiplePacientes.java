/**
 * logica: MetodoAiepiMultiplePacientes se encuentran las inserciones,consultas y actualizaciones
 * para la creacion del menu en forma de pestañas con los multiples pacientes
 * con su respectivo menu de opciones.
*/
package aiepi_metodo;

import hic_bean.CrearAntecedentes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoAiepiMultiplePacientes {
	
	
	public java.sql.ResultSet BuscarGeneroPaciente(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select genero from adm_paciente where pac_codigo_paciente='"+CodPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>BuscarGeneroPaciente "+ex);
		}
		return rs;
	}
	
	public ResultSet AutocompletarMedicamentos(String cod,String tipo) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT med.nombre,med.concentracion,med.codigo FROM medicamentos med,farc_grupo fgr WHERE med.cod_grupoFK=fgr.codigo AND med.estado=1 AND fgr.tipo='"+tipo+"' AND  med.nombre LIKE '"+cod+"%' OR med.concentracion LIKE '"+cod+"%'");
	        	//System.out.print ("SELECT med.nombre,med.concentracion,med.codigo FROM medicamentos med,farc_grupo fgr WHERE med.cod_grupoFK=fgr.codigo AND fgr.tipo='"+tipo+"' AND  med.nombre LIKE '"+cod+"%' OR med.concentracion LIKE '"+cod+"%'");
	        return r;
	    }
	
	public ResultSet AutocompletarMedicamentosMedico(String cod) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("SELECT med.nombre,med.concentracion,med.codigo,fff.forma_farmaceutica FROM medicamentos med,farc_grupo fgr,farc_formafarmaceutica fff WHERE med.cod_grupoFK=fgr.codigo and med.estado=1 and fff.codigo=med.cod_formaFK AND  med.nombre LIKE '"+cod+"%' OR med.concentracion LIKE '"+cod+"%'");
	        	//r=st.executeQuery("SELECT med.nombre,med.concentracion,med.codigo FROM medicamentos med,farc_grupo fgr WHERE med.cod_grupoFK=fgr.codigo  AND  med.nombre LIKE '"+cod+"%' OR med.concentracion LIKE '"+cod+"%'");
	        	//System.out.print ("SELECT med.nombre,med.concentracion,med.codigo FROM medicamentos med,farc_grupo fgr WHERE med.cod_grupoFK=fgr.codigo AND fgr.tipo='"+tipo+"' AND  med.nombre LIKE '"+cod+"%' OR med.concentracion LIKE '"+cod+"%'");
	        return r;
	    }
	
	public java.sql.ResultSet VerificarAdmision(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT aad.adm_numero_ingreso, " +
					"ae.nombre_entidad AS Entidad,am.nombre AS Municipio, " +
					"CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombreCompleto, " +
					"ap.genero,aad.adm_numero_ingreso,ap.fecha_nacimiento, " +
					"TIMESTAMPDIFF(DAY, ap.fecha_nacimiento, NOW()) AS EdadDias, " +
					"TIMESTAMPDIFF(MONTH, ap.fecha_nacimiento, NOW()) AS EdadMeses, " +
					"TIMESTAMPDIFF(YEAR, ap.fecha_nacimiento, NOW()) AS EdadAnos,acc.responsable, " +
					"ap.pac_codigo_paciente " +
					"FROM adm_paciente ap,adm_entidad ae,adm_convenio ac,adm_municipio am,adm_admisiones aad, " +
					"adm_contacto acc " +
					"WHERE ap.conv_numero_contrato_fk=ac.conv_numero_contrato " +
					"AND ac.ent_nit_contratante_fk=ae.ent_nit " +
					"AND ap.mun_codigo_fk=am.muni_cod " +
					"AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
					"AND aad.codigo_contacto_fk=acc.con_contactos_codigo " +
					"AND ap.pac_codigo_paciente='"+CodPac+"' AND aad.estado=0 ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarAdmision "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarDatosAdmision(String CodPac,String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido,(YEAR(CURDATE())-YEAR(pac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(pac.fecha_nacimiento, 5)) AS edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,acc.pabellon,acc.ubicacion_cama,acc.codigocama,aen.nombre_entidad,acc.cen_numero_cama,aar.codigo AS CodArea,asub.codigo AS CodSubarea,aen.ent_nit,fef.cod_enc_factura,amt.medico_texto,TIMESTAMPDIFF(MONTH, pac.fecha_nacimiento, NOW()) AS EdadMeses,TIMESTAMPDIFF(DAY, pac.fecha_nacimiento, NOW()) AS EdadDias FROM adm_paciente pac,adm_admisiones adm,adm_censo_cama acc,adm_convenio acv,adm_entidad aen,adm_area aar,adm_subarea asub,fact_enc_factura fef,adm_medico_tratante amt WHERE pac.pac_codigo_paciente='"+CodPac+"' AND adm.adm_numero_ingreso='"+CodAdm+"' AND adm.cen_numero_cama_fk=acc.cen_numero_cama AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=pac.conv_numero_contrato_fk AND aar.codigo=asub.codigoarea AND asub.codigo=acc.codsubarea AND fef.cod_admision=adm.adm_numero_ingreso AND fef.valor=0  AND amt.codAdm_fk=adm.adm_numero_ingreso AND amt.estado=0 ");
			System.out.println("SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido,(YEAR(CURDATE())-YEAR(pac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(pac.fecha_nacimiento, 5)) AS edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,acc.pabellon,acc.ubicacion_cama,acc.codigocama,aen.nombre_entidad,acc.cen_numero_cama,aar.codigo AS CodArea,asub.codigo AS CodSubarea,aen.ent_nit,fef.cod_enc_factura,amt.medico_texto,TIMESTAMPDIFF(MONTH, pac.fecha_nacimiento, NOW()) AS EdadMeses,TIMESTAMPDIFF(DAY, pac.fecha_nacimiento, NOW()) AS EdadDias FROM adm_paciente pac,adm_admisiones adm,adm_censo_cama acc,adm_convenio acv,adm_entidad aen,adm_area aar,adm_subarea asub,fact_enc_factura fef,adm_medico_tratante amt WHERE pac.pac_codigo_paciente='"+CodPac+"' AND adm.adm_numero_ingreso='"+CodAdm+"' AND adm.cen_numero_cama_fk=acc.cen_numero_cama AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=pac.conv_numero_contrato_fk AND aar.codigo=asub.codigoarea AND asub.codigo=acc.codsubarea AND fef.cod_admision=adm.adm_numero_ingreso AND fef.valor=0  AND amt.codAdm_fk=adm.adm_numero_ingreso AND amt.estado=0 ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarDatosAdmision "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarPermisosHC(String usuario){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT codigoPerhc_fk FROM hic_usuariopermisoshc WHERE codigoUsu_fk='"+usuario+"' order by codigoPerhc_fk");
			System.out.println("SELECT codigoPerhc_fk FROM hic_usuariopermisoshc WHERE codigoUsu_fk='"+usuario+"' order by codigoPerhc_fk");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarPermisosHC "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet PreguntarAdmisionCeroDos(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT aad.adm_numero_ingreso " +
					"FROM adm_admisiones aad,aiepi_0a2_informe infuno " +
					"WHERE aad.adm_numero_ingreso=infuno.cod_admision " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
			System.out.println("1U. SELECT aad.adm_numero_ingreso " +
					"FROM adm_admisiones aad,aiepi_0a2_informe infuno " +
					"WHERE aad.adm_numero_ingreso=infuno.cod_admision " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>PreguntarAdmisionCeroDos "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet PreguntarAdmisionCeroDosR(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT aad.adm_numero_ingreso,exauno.id_informe_aiepi_0a2_fk " +
					"FROM adm_admisiones aad,aiepi_0a2_informe infuno,aiepi_0a2_examen_fisico exauno " +
					"WHERE aad.adm_numero_ingreso=infuno.cod_admision " +
					"AND exauno.id_informe_aiepi_0a2_fk=infuno.id_informe_aiepi_0a2 " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
			System.out.println("1U. SELECT aad.adm_numero_ingreso,exauno.id_informe_aiepi_0a2_fk " +
					"FROM adm_admisiones aad,aiepi_0a2_informe infuno,aiepi_0a2_examen_fisico exauno " +
					"WHERE aad.adm_numero_ingreso=infuno.cod_admision " +
					"AND exauno.id_informe_aiepi_0a2_fk=infuno.id_informe_aiepi_0a2 " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>PreguntarAdmisionCeroDosR "+ex);
		}
		return rs;
	}
	
	
	public java.sql.ResultSet PreguntarAdmisionDosCinco(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT aad.adm_numero_ingreso " +
					"FROM adm_admisiones aad,aiepi_2a5_informe infdos " +
					"WHERE aad.adm_numero_ingreso=infdos.cod_admision " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
			System.out.println("2U. SELECT aad.adm_numero_ingreso " +
					"FROM adm_admisiones aad,aiepi_2a5_informe infdos " +
					"WHERE aad.adm_numero_ingreso=infdos.cod_admision " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>PreguntarAdmisionDosCinco "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet PreguntarAdmisionDosCincoR(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT aad.adm_numero_ingreso,exados.id_informe_aiepi_2a5_fk " +
					"FROM adm_admisiones aad,aiepi_2a5_informe infdos,aiepi_2a5_examen_fisico exados " +
					"WHERE aad.adm_numero_ingreso=infdos.cod_admision " +
					"AND exados.id_informe_aiepi_2a5_fk=infdos.id_informe_aiepi_2a5 " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
			System.out.println("2U. SELECT aad.adm_numero_ingreso,exados.id_informe_aiepi_2a5_fk " +
					"FROM adm_admisiones aad,aiepi_2a5_informe infdos,aiepi_2a5_examen_fisico exados " +
					"WHERE aad.adm_numero_ingreso=infdos.cod_admision " +
					"AND exados.id_informe_aiepi_2a5_fk=infdos.id_informe_aiepi_2a5 " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>PreguntarAdmisionDosCincoR "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet PreguntarAdmisionEmbarazadas(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT aad.adm_numero_ingreso " +
					"FROM adm_admisiones aad,aiepi_emb_informe infemb " +
					"WHERE aad.adm_numero_ingreso=infemb.cod_admision " +
					"AND aad.adm_numero_ingreso='"+CodAdm+"' ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>PreguntarAdmisionEmbarazadas "+ex);
		}
		return rs;
	}

	
	
	//Modificadas
	public java.sql.ResultSet EntidadMunicipioPaciente(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT ae.nombre_entidad AS Entidad,am.nombre AS Municipio, " +
					"CONCAT(ap.nombre,' ',ap.primer_apellido,' ',ap.segundo_apellido) AS NombreCompleto, " +
					"ap.genero,aad.adm_numero_ingreso,ap.fecha_nacimiento, " +
					"TIMESTAMPDIFF(DAY, ap.fecha_nacimiento, NOW()) AS EdadDias,acc.responsable " +
					"FROM adm_paciente ap,adm_entidad ae,adm_convenio ac,adm_municipio am,adm_admisiones aad, " +
					"adm_contacto acc,adm_paciente_contacto apc " +
					"WHERE ap.conv_numero_contrato_fk=ac.conv_numero_contrato " +
					"AND ac.ent_nit_contratante_fk=ae.ent_nit " +
					"AND ap.mun_codigo_fk=am.muni_cod " +
					"AND ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
					"AND ap.pac_codigo_paciente=apc.pac_codigo_paciente_fk " +
					"AND apc.con_contactos_codigo_fk=acc.con_contactos_codigo " +
					"AND ap.pac_codigo_paciente="+CodPac+" ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarEntidadMunicipioPaciente "+ex);
		}
		return rs;
	}
	/*********************************************AIEPI***************************************/	
	public java.sql.ResultSet ConsultarCodigoPrincipalCIE(String codigoCIE){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT * FROM cie10 WHERE codigoCIE LIKE '%"+codigoCIE+"%' ");
	    	//System.out.println("SELECT codigo,codigoCIE,nombre FROM cie10 WHERE codigoCIE LIKE '"+texto+"%' LIMIT 100 ");
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoAiepiMultiplePacientes>>ConsultarCodigoPrincipalCIE "+ex);
	    }	
	    return rs;
	}
	public java.sql.ResultSet ConsultarCodPrincipalDxIngreso(String CodAdm){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT * FROM hic_diagnosticoingreso WHERE codAdm='"+CodAdm+"' ");
	    	//System.out.println("SELECT codigo,codigoCIE,nombre FROM cie10 WHERE codigoCIE LIKE '"+texto+"%' LIMIT 100 ");
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoAiepiMultiplePacientes>>ConsultarCodPrincipalDxIngreso "+ex);
	    }	
	    return rs;
	}

	public java.sql.ResultSet buscarDxIngresoCeroDos(String codAdmision){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT cie.nombre,cie.codigoCIE,hdi.codigo,adx.id_nombredx FROM hic_diagnosticoingreso hdi,cie10 cie,aiepi_0a2_nombredx adx " +
	    			"WHERE hdi.codDiagnostico=cie.codigoCIE AND cie.codigoCIE=adx.codigo_cie_pac AND hdi.codAdm='"+codAdmision+"' ");
	    	//System.out.println("SELECT codigo,codigoCIE,nombre FROM cie10 WHERE codigoCIE LIKE '"+texto+"%' LIMIT 100 ");
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoAiepiMultiplePacientes>>buscarDxIngreso "+ex);
	    }	
	    return rs;
	}
	
	public java.sql.ResultSet buscarDxIngreso(String codAdmision){
	    java.sql.ResultSet rs=null;
	    Statement st = null;
	    try{
	    	Conexion con=new Conexion();
	    	st = con.conn.createStatement();
	    	rs=st.executeQuery("SELECT cie.nombre,cie.codigoCIE,hdi.codigo,adx.id_nombredx FROM hic_diagnosticoingreso hdi,cie10 cie,aiepi_2a5_nombredx adx " +
	    			"WHERE hdi.codDiagnostico=cie.codigoCIE AND cie.codigoCIE=adx.codigo_cie_pac AND hdi.codAdm='"+codAdmision+"' ");
	    	//System.out.println("SELECT codigo,codigoCIE,nombre FROM cie10 WHERE codigoCIE LIKE '"+texto+"%' LIMIT 100 ");
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoAiepiMultiplePacientes>>buscarDxIngreso "+ex);
	    }	
	    return rs;
	}
	
	public void OmitirDxIngreso(String codDx){
		 PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM hic_diagnosticoingreso WHERE codigo='"+codDx+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	    	//System.out.println("SELECT codigo,codigoCIE,nombre FROM cie10 WHERE codigoCIE LIKE '"+texto+"%' LIMIT 100 ");
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoAiepiMultiplePacientes>>OmitirDxIngreso "+ex);
	    }	
	}

	public void OmitirDxIngresoAiepiCeroDos(String codAiepi){
		 PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM aiepi_0a2_nombredx WHERE id_nombredx='"+codAiepi+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	    	//System.out.println("SELECT codigo,codigoCIE,nombre FROM cie10 WHERE codigoCIE LIKE '"+texto+"%' LIMIT 100 ");
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoAiepiMultiplePacientes>>OmitirDxIngresoAiepiCeroDos "+ex);
	    }	
	}
	
	public void OmitirDxIngresoAiepi(String codAiepi){
		 PreparedStatement st = null;
	    try{
	    	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("DELETE FROM aiepi_2a5_nombredx WHERE id_nombredx='"+codAiepi+"' ");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	    	//System.out.println("SELECT codigo,codigoCIE,nombre FROM cie10 WHERE codigoCIE LIKE '"+texto+"%' LIMIT 100 ");
	    	
	    }
	    catch(Exception ex){
	    	System.out.println("Error en MetodoAiepiMultiplePacientes>>OmitirDxIngresoAiepi "+ex);
	    }	
	}
	
	//Método insertarDxIngresoPaciente
	 public void insertarDxIngresoPaciente(String codDiagnostico, String codUsuario, String hora, String fecha, String codAdmision, String codPaciente, String codPrincipalCIE){	 
			System.out.println("3. insertarDxIngresoPaciente");
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO hic_diagnosticoingreso(codDiagnostico, codUsu, hora, fecha, codAdm, codPac, CodDiag_fk) VALUES(?,?,?,?,?,?,?)");				
				ps.setString(1, codDiagnostico);
				ps.setString(2, codUsuario);
				ps.setString(3, hora);
				ps.setString(4, fecha);
				ps.setString(5, codAdmision);
				ps.setString(6, codPaciente);
				ps.setString(7, codPrincipalCIE);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarDxIngresoPaciente "+ex);
			}
					
		}
	
	//Buscar Informe
		 public java.sql.ResultSet buscarInformesAIEPI(String CodPac) {
				java.sql.ResultSet rs = null;
				Statement st = null;
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery(
							"SELECT * FROM (SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido, " +
							"inf.id_informe_aiepi_0a2, " +
							"inf.fecha_informe,inf.hora_informe, " +
							"pac_codigo_paciente,inf.nombre_informe,inf.identificador,exauno.id_examen_fisico " +
							"FROM " +
							"adm_paciente pac, " +
							"aiepi_0a2_informe inf, " +
							"aiepi_0a2_examen_fisico exauno " +
							"WHERE " +
							"pac.pac_codigo_paciente=inf.id_paciente " +
							"AND inf.id_informe_aiepi_0a2=exauno.id_informe_aiepi_0a2_fk " +
							"AND pac.pac_codigo_paciente='"+CodPac+"' " +
							"UNION " +
							"SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido, " +
							"infdos.id_informe_aiepi_2a5, " +
							"infdos.fecha_informe,infdos.hora_informe, " +
							"pac_codigo_paciente,infdos.nombre_informe,infdos.identificador,exados.id_examen_fisico " +
							"FROM " +
							"adm_paciente pac, " +
							"aiepi_2a5_informe infdos, " +
							"aiepi_2a5_examen_fisico exados " +
							"WHERE " +
							"pac.pac_codigo_paciente=infdos.id_paciente " +
							"AND infdos.id_informe_aiepi_2a5=exados.id_informe_aiepi_2a5_fk " +
							"AND pac.pac_codigo_paciente='"+CodPac+"') AS Reporte " +
							"ORDER BY Reporte.fecha_informe,Reporte.hora_informe "); 					
					
				} catch (Exception ex) {
					System.out.println("Error en MetodoAiepiMultiplePacientes>>buscarInformesAIEPI "+ex);
				}
				return rs;
			}
	/*********************************************FIN AIEPI***********************************/
	
	//Insertar Informe
	 public boolean insertarEncabezadoInformeAiepi(String nombreUsuario,String codPaciente,String hora,String fecha, String codAdmision) {
			PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement("INSERT INTO " +
						"aiepi_0a2_informe(" +
						"id_usuario," +
						"id_paciente," +
						"hora_informe," +
						"fecha_informe," +
						"cod_admision" +
						")" +
						" VALUES " +
						"(?,?,?,?,?)");
				psc.setString(1, nombreUsuario);
				psc.setString(2, codPaciente);
				psc.setString(3, hora);
				psc.setString(4, fecha);
				psc.setString(5, codAdmision);

				psc.execute();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception e) {
				System.out.println("Error en MetodoAiepiMultiplePacientes>>insertarEncabezadoInformeAiepi "+e);
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	
	 ///Buscar Informe
	 public java.sql.ResultSet buscarInformesAIEPI0a2Realizados(String CodPac) {
			java.sql.ResultSet rs = null;
			Statement st = null;
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT " +
				        "CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido)," +
						"inf.id_informe_aiepi_0a2," +
						"CONCAT(inf.fecha_informe,'/',inf.hora_informe), " +
						"pac_codigo_paciente,inf.nombre_informe " +
						"FROM " +
						"adm_paciente pac, " +
						"aiepi_0a2_informe inf " +
						"WHERE " +
						"pac.pac_codigo_paciente = inf.id_paciente " +
						"AND pac.pac_codigo_paciente='"+CodPac+"' "); 					
				
			} catch (Exception ex) {
				System.out.println("Error en MetodoAiepiMultiplePacientes>>buscarInformesAIEPI0a2Realizados "+ex);
			}
			return rs;
		}

		/*OBTENER HORA Y FECHA PARA DETERMINAR EL CODIGO DEL INFORME*/
//Obtener Hora y Fecha
public java.sql.ResultSet ObtenerCodigoInforme(String Fecha, String Hora) {

java.sql.ResultSet rs = null;
Statement st = null;

try {
Conexion con = new Conexion();
st = con.conn.createStatement();
rs = st.executeQuery("SELECT * FROM aiepi_0a2_informe WHERE fecha_informe='"+Fecha+"' AND hora_informe='"+Hora+"' ");

} catch (Exception ex) {
ex.getMessage();
}

return rs;
}

//Obtener Admision
public java.sql.ResultSet BuscarCodigoInformeCeroDos(String codAdmision) {

java.sql.ResultSet rs = null;
Statement st = null;

try {
Conexion con = new Conexion();
st = con.conn.createStatement();
rs = st.executeQuery("SELECT * FROM aiepi_0a2_informe WHERE cod_admision='"+codAdmision+"' ");
System.out.println("2. SELECT * FROM aiepi_0a2_informe WHERE cod_admision='"+codAdmision+"' ");

} catch (Exception ex) {
ex.getMessage();
}

return rs;
}
	/* FIN DE OBTENER HORA Y FECHA PARA DETERMINAR EL CODIGO DEL INFORME*/

//Método insertarExamenFisico
public void insertarExamenFisico(String motConsulta, String antEmbarazo, String pesoNacer, String tallaNacer, String edadGestacional, String hemoclasificacion, String pesoActual, String talla, String perimetroCefalico, String frecuenciaCardiaca, String frecuenciaRespiratoria, String temperatura, String centigrados, String codReporte){	 
		
		try{				
			PreparedStatement ps = null;
		    Conexion con=new Conexion();
		    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_examen_fisico(mot_consulta, ant_embarazo, peso_nacer, talla_nacer, edad_gestacional, hemoclasificacion, peso_actual, talla, perimetro_cefalico, frecuencia_cardiaca, frecuencia_respiratoria, temperatura, centigrados, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
			ps.setString(1, motConsulta);
			ps.setString(2, antEmbarazo);
			ps.setString(3, pesoNacer);
			ps.setString(4, tallaNacer);
			ps.setString(5, edadGestacional);
			ps.setString(6, hemoclasificacion);
			ps.setString(7, pesoActual);
			ps.setString(8, talla);
			ps.setString(9, perimetroCefalico);
			ps.setString(10, frecuenciaCardiaca);
			ps.setString(11, frecuenciaRespiratoria);
			ps.setString(12, temperatura);
			ps.setString(13, centigrados);			
			ps.setString(14, codReporte);
	
			ps.execute();
			ps.close();
			con.cerrar();
					
		}catch(Exception ex){
			ex.getMessage();
			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarExamenFisico "+ex);
		}
				
	}

	 //Método insertarSignosEnfermedad1
	 public void insertarSignosEnfermedadUno(String bebePecho, String vomito, String vomitaTodo, String dificultadRepirar, String expDificultadRespirar, String fiebre, String hipotermia, String convulsiones, String panalesOrinados, String diarrea, String desdeCuandoDiarrea, String sangreHeces, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_enfermedad_1(beber_pecho, vomito, vomita_todo, dificultad_respirar, exp_dificultad_respirar, fiebre, hipotermia, convulsiones, panales_orinados, diarrea, desde_cuando_diarrea, sangre_heces, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, bebePecho);
				ps.setString(2, vomito);
				ps.setString(3, vomitaTodo);
				ps.setString(4, dificultadRepirar);
				ps.setString(5, expDificultadRespirar);
				ps.setString(6, fiebre);
				ps.setString(7, hipotermia);
				ps.setString(8, convulsiones);
				ps.setString(9, panalesOrinados);
				ps.setString(10, diarrea);
				ps.setString(11, desdeCuandoDiarrea);
				ps.setString(12, sangreHeces);
				ps.setString(13, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosEnfermedadUno "+ex);
			}
					
		}
	 
	//Método insertarSignosEnfermedad2
	 public void insertarSignosEnfermedadDos(String mueveSolo, String letargico, String luceMal, String irritable, String palidez, String cianosis, String bilirrubinas, String frecuenciaRespiratoria, String frecuenciaCardiaca, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_enfermedad_2(mueve_solo, letargico, luce_mal, irritable, palidez, cianosis, bilirrubinas, frecuencia_respiratoria, frecuencia_cardiaca, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, mueveSolo);
				ps.setString(2, letargico);
				ps.setString(3, luceMal);
				ps.setString(4, irritable);
				ps.setString(5, palidez);
				ps.setString(6, cianosis);
				ps.setString(7, bilirrubinas);
				ps.setString(8, frecuenciaRespiratoria);
				ps.setString(9, frecuenciaCardiaca);
				ps.setString(10, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosEnfermedadDos "+ex);
			}
					
		}
	 
	//Método insertarSignosEnfermedad3
	 public void insertarSignosEnfermedadTres(String apneas, String aleteoNasal, String quejido, String estridor, String sibilancia, String tirajeSubcostal, String supuracionOido, String secrecionPurulentaConjuntival, String edemaPalpebral, String pustulasPiel, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_enfermedad_3(apneas, aleteo_nasal, quejido, estridor, sibilancia, tiraje_subcostal, supuracion_oido, secrecion_purulenta_conjuntival, edema_palpebral, pustulas_piel, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, apneas);
				ps.setString(2, aleteoNasal);
				ps.setString(3, quejido);
				ps.setString(4, estridor);
				ps.setString(5, sibilancia);
				ps.setString(6, tirajeSubcostal);
				ps.setString(7, supuracionOido);
				ps.setString(8, secrecionPurulentaConjuntival);
				ps.setString(9, edemaPalpebral);
				ps.setString(10, pustulasPiel);
				ps.setString(11, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosEnfermedadTres "+ex);
			}
					
		}
	 
	//Método insertarSignosEnfermedad4
	 public void insertarSignosEnfermedadCuatro(String secrecionPurulentaOmbligo, String eritemaPeriumbilical, String placasBlanquecinas, String equimosis, String petequias, String hemorragia, String distensionAbdominal, String llenadoCapilar, String fontanelaAbombada, String estadoGeneral, String ojosHundidos, String pliegueCutaneo, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_enfermedad_4(secrecion_purulenta_ombligo, eritema_periumbilical, placas_blanquecinas, equimosis, petequias, hemorragia, distension_abdominal, llenado_capilar, fontanela_abombada, estado_general, ojos_hundidos, pliegue_cutaneo, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, secrecionPurulentaOmbligo);
				ps.setString(2, eritemaPeriumbilical);
				ps.setString(3, placasBlanquecinas);
				ps.setString(4, equimosis);
				ps.setString(5, petequias);
				ps.setString(6, hemorragia);
				ps.setString(7, distensionAbdominal);
				ps.setString(8, llenadoCapilar);
				ps.setString(9, fontanelaAbombada);
				ps.setString(10, estadoGeneral);
				ps.setString(11, ojosHundidos);
				ps.setString(12, pliegueCutaneo);
				ps.setString(13, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosEnfermedadCuatro "+ex);
			}
					
		}
	 
	//Método insertarSignosAlimentacionUno
	 public void insertarSignosAlimentacionUno(String dificultadAlimentarse, String cualDificultadAlimentarse, String dejadoComer, String desdeCuandoDejadoComer, String lecheMaterna, String formaExclusivaLecheMaterna, String cuantasVecesLecheMaterna, String otraLeche, String cualesFrecuenciaOtraLeche, String preparaOtraLeche, String utilizaAlimentarlo, String utilizaChupo, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_alimentacion_1(dificultad_alimentarse, cual_dificultad_alimentarse, dejado_comer, desde_cuando_dejado_comer, leche_materna, forma_exclusiva_leche_materna, cuantas_veces_leche_materna, otra_leche, cuales_frecuencia_otra_leche, prepara_otra_leche, utiliza_alimentarlo, utiliza_chupo, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, dificultadAlimentarse);
				ps.setString(2, cualDificultadAlimentarse);
				ps.setString(3, dejadoComer);
				ps.setString(4, desdeCuandoDejadoComer);
				ps.setString(5, lecheMaterna);
				ps.setString(6, formaExclusivaLecheMaterna);
				ps.setString(7, cuantasVecesLecheMaterna);
				ps.setString(8, otraLeche);
				ps.setString(9, cualesFrecuenciaOtraLeche);
				ps.setString(10, preparaOtraLeche);
				ps.setString(11, utilizaAlimentarlo);
				ps.setString(12, utilizaChupo);
				ps.setString(13, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosAlimentacionUno "+ex);
			}
					
		}
	 
	//Método insertarSignosAlimentacion2
	 public void insertarSignosAlimentacionDos(String pesoEdad, String pesoTalla, String tendenciaPeso, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_alimentacion_2(peso_edad, peso_talla, tendencia_peso, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?)");				
				ps.setString(1, pesoEdad);
				ps.setString(2, pesoTalla);
				ps.setString(3, tendenciaPeso);
				ps.setString(4, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosAlimentacionDos "+ex);
			}
					
		}
	 
	//Método insertarSignosAlimentacion3
	 public void insertarSignosAlimentacionTres(String bocaAbierta, String tocaSeno, String labioInferior, String areolaLabio, String cabezaCuerpoDerecho, String direccionPezon, String hijoFrenteMadre, String madreSostiene, String lentaProfunda, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_alimentacion_3(boca_abierta, toca_seno, labio_inferior, areola_labio, cabeza_cuerpo_derecho, direccion_pezon, hijo_frente_madre, madre_sostiene, lenta_profunda, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, bocaAbierta);
				ps.setString(2, tocaSeno);
				ps.setString(3, labioInferior);
				ps.setString(4, areolaLabio);
				ps.setString(5, cabezaCuerpoDerecho);
				ps.setString(6, direccionPezon);
				ps.setString(7, hijoFrenteMadre);
				ps.setString(8, madreSostiene);
				ps.setString(9, lentaProfunda);
				ps.setString(10, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosAlimentacionTres "+ex);
			}
					
		}
	 
	//Método insertarSignosDesarrollo1
	 public void insertarSignosDesarrolloUno(String parientesPadres, String familiarProblema, String cuidaNino, String desarrolloNino, String antNatales, String alteracionFenitipica, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_desarrollo_1(parientes_padres, familiar_problema, cuida_nino, desarrollo_nino, antecedentes_natales, alteracion_fenitipica, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?)");				
				ps.setString(1, parientesPadres);
				ps.setString(2, familiarProblema);
				ps.setString(3, cuidaNino);
				ps.setString(4, desarrolloNino);
				ps.setString(5, antNatales);
				ps.setString(6, alteracionFenitipica);
				ps.setString(7, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosDesarrolloUno "+ex);
			}
					
		}
	 
	//Método insertarSignosDesarrollo2
	 public void insertarSignosDesarrolloDos(String perimetroCefalico, String perimetroCefalicoEdad, String reflejoMoro, String reflejoSuccion, String reflejoCocleo, String brazosPiernasFlexionadas, String manosCerradas, String vocaliza, String sonrisaSocial, String movPiernas, String sigueObjetos, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_signos_desarrollo_2(perimetro_cefalico, perimetro_cefalico_edad, reflejo_moro, reflejo_succion, reflejo_cocleo, brazos_piernas_flexionadas, manos_cerradas, vocaliza, sonrisa_social, movimiento_piernas, sigue_objetos, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, perimetroCefalico);
				ps.setString(2, perimetroCefalicoEdad);
				ps.setString(3, reflejoMoro);
				ps.setString(4, reflejoSuccion);
				ps.setString(5, reflejoCocleo);
				ps.setString(6, brazosPiernasFlexionadas);
				ps.setString(7, manosCerradas);
				ps.setString(8, vocaliza);
				ps.setString(9, sonrisaSocial);
				ps.setString(10, movPiernas);
				ps.setString(11, sigueObjetos);
				ps.setString(12, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosDesarrolloDos "+ex);
			}
					
		}
	 
	//Método insertarDiagnosticos
	 public void insertarDiagnosticos(String completarExamenFisico, String tratar, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_diagnosticos(completar_examen_fisico, tratar, id_informe_aiepi_0a2_fk) VALUES(?,?,?)");				
				ps.setString(1, completarExamenFisico);
				ps.setString(2, tratar);
				ps.setString(3, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarDiagnosticos "+ex);
			}
					
		}
	 
	//Método insertarRecomendaciones
	 public void insertarRecomendaciones(String volverInmediato, String volverRecienNacido, String volverMadre, String volverNinoSano, String referidoConsulta, String programaVacunacion, String recomendacionesBuenTrato, String otrasRecomendaciones, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_recomendaciones(volver_inmediato, volver_recien_nacido, volver_madre, volver_nino_sano, referido_consulta, programa_vacunacion, recomendaciones_buen_trato, otras_recomendaciones, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
				ps.setString(1, volverInmediato);
				ps.setString(2, volverRecienNacido);
				ps.setString(3, volverMadre);
				ps.setString(4, volverNinoSano);
				ps.setString(5, referidoConsulta);
				ps.setString(6, programaVacunacion);
				ps.setString(7, recomendacionesBuenTrato);
				ps.setString(8, otrasRecomendaciones);
				ps.setString(9, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRecomendaciones "+ex);
			}
					
		}
	 
	 //Método insertarNombreDxCeroDos
	 public void insertarNombreDxCeroDos(String diagnosticoPac, String codigoCIEPac, String CodReporte){ 
		 System.out.println("4. insertarNombreDxCeroDos");
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_nombredx(diagnostico_pac, codigo_cie_pac, id_informe_aiepi_0a2_fk) VALUES(?,?,?)");				
	 			ps.setString(1, diagnosticoPac);
	 			ps.setString(2, codigoCIEPac);
	 			ps.setString(3, CodReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarNombreDxCeroDos "+ex);
	 		}
	 				
	 	}
	 
	//Método insertarRespuestasEnfermedadGrave
	 public void insertarRespuestasEnfermedadGrave(String enfermedadGrave, String infeccionLocal, String noEnfermedadNoInfeccion, String deshidratacion, String noDeshidratacion, String diarreaProlongada, String diarreaSangre, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_respuestas_enfermedad_grave(enfermedad_grave, infeccion_local, no_enfermedad_no_infeccion, deshidratacion, no_deshidratacion, diarrea_prolongada, diarrea_sangre, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?,?,?)");				
				ps.setString(1, enfermedadGrave);
				ps.setString(2, infeccionLocal);
				ps.setString(3, noEnfermedadNoInfeccion);
				ps.setString(4, deshidratacion);
				ps.setString(5, noDeshidratacion);
				ps.setString(6, diarreaProlongada);
				ps.setString(7, diarreaSangre);
				ps.setString(8, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasEnfermedadGrave "+ex);
			}
					
		}
	 
		//Método insertarRespuestasAlimentacion
	 public void insertarRespuestasAlimentacion(String probSeveroAlimentacion, String pesoMuyBajo, String problemasAlimentacion, String pesoBajo, String adecuadasPracticasAlimentacion, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_respuestas_alimentacion(problema_severo_alimentacion, peso_muy_bajo, problemas_alimentacion, peso_bajo, adecuadas_practicas_alimentacion, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?,?,?)");				
				ps.setString(1, probSeveroAlimentacion);
				ps.setString(2, pesoMuyBajo);
				ps.setString(3, problemasAlimentacion);
				ps.setString(4, pesoBajo);
				ps.setString(5, adecuadasPracticasAlimentacion);
				ps.setString(6, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasAlimentacion "+ex);
			}
					
		}
	
		//Método insertarRespuestasDesarrollo
	 public void insertarRespuestasDesarrollo(String probRetrasoDesarrollo, String riesgoProblema, String desarrolloNormal, String codReporte){	 
			
			try{				
				PreparedStatement ps = null;
			    Conexion con=new Conexion();
			    ps=con.conn.prepareStatement("INSERT INTO aiepi_0a2_respuestas_Desarrollo(probable_retraso_desarrollo, riesgo_problema, desarrollo_normal, id_informe_aiepi_0a2_fk) VALUES(?,?,?,?)");				
				ps.setString(1, probRetrasoDesarrollo);
				ps.setString(2, riesgoProblema);
				ps.setString(3, desarrolloNormal);
				ps.setString(4, codReporte);
		
				ps.execute();
				ps.close();
				con.cerrar();
						
			}catch(Exception ex){
				ex.getMessage();
				System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasDesarrollo "+ex);
			}
					
		}
	
	/******************************************************************************************************/
	/*************************************AIEPI 2 MESES - 5 AÑOS*******************************************/
	/******************************************************************************************************/
		/*OBTENER HORA Y FECHA PARA DETERMINAR EL CODIGO DEL INFORME*/
	//Obtener Hora y Fecha
	public java.sql.ResultSet ObtenerCodigoInformeDosCinco(String Fecha, String Hora) {

	java.sql.ResultSet rs = null;
	Statement st = null;

	try {
	Conexion con = new Conexion();
	st = con.conn.createStatement();
	rs = st.executeQuery("SELECT * FROM aiepi_2a5_informe WHERE fecha_informe='"+Fecha+"' AND hora_informe='"+Hora+"' ");
	System.out.println("2. SELECT * FROM aiepi_2a5_informe WHERE fecha_informe='"+Fecha+"' AND hora_informe='"+Hora+"' ");

	} catch (Exception ex) {
	ex.getMessage();
	}

	return rs;
	}
	
	//Obtener Admision
	public java.sql.ResultSet BuscarCodigoInformeDosCinco(String codAdmision) {

	java.sql.ResultSet rs = null;
	Statement st = null;

	try {
	Conexion con = new Conexion();
	st = con.conn.createStatement();
	rs = st.executeQuery("SELECT * FROM aiepi_2a5_informe WHERE cod_admision='"+codAdmision+"' ");
	System.out.println("2. SELECT * FROM aiepi_2a5_informe WHERE cod_admision='"+codAdmision+"' ");

	} catch (Exception ex) {
	ex.getMessage();
	}

	return rs;
	}
	
	 ///Buscar Informe
	 public java.sql.ResultSet buscarInformesAIEPI2a5Realizados(String CodPac) {
			java.sql.ResultSet rs = null;
			Statement st = null;
			try {
				Conexion con = new Conexion();
				st = con.conn.createStatement();
				rs = st.executeQuery(
						"SELECT " +
				        "CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido)," +
						"inf.id_informe_aiepi_2a5," +
						"CONCAT(inf.fecha_informe,'/',inf.hora_informe), " +
						"pac_codigo_paciente,inf.nombre_informe " +
						"FROM " +
						"adm_paciente pac, " +
						"aiepi_2a5_informe inf " +
						"WHERE " +
						"pac.pac_codigo_paciente = inf.id_paciente " +
						"AND pac.pac_codigo_paciente='"+CodPac+"' "); 					
				
			} catch (Exception ex) {
				System.out.println("Error en MetodoAiepiMultiplePacientes>>buscarInformesAIEPI2a5Realizados "+ex);
			}
			return rs;
		}
	
	
	 //Insertar Informe
	 public boolean insertarEncabezadoInformeAiepiDosCinco(String nombreUsuario,String codPaciente,String hora,String fecha, String codAdmision) {
			System.out.println("5. insertarEncabezadoInformeAiepiDosCinco");
		 PreparedStatement psc = null;
			try {
				Conexion con = new Conexion();
				psc = con.conn.prepareStatement("INSERT INTO " +
						"aiepi_2a5_informe(" +
						"id_usuario," +
						"id_paciente," +
						"hora_informe," +
						"fecha_informe," +
						"cod_admision" +
						")" +
						" VALUES " +
						"(?,?,?,?,?)");
				psc.setString(1, nombreUsuario);
				psc.setString(2, codPaciente);
				psc.setString(3, hora);
				psc.setString(4, fecha);
				psc.setString(5, codAdmision);

				psc.execute();
				psc.close();
				con.cerrar();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	 
	//Método insertarExamenFisicoDosCinco
	 public void insertarExamenFisicoDosCinco(String motConsulta, String antNatales, String apf, String temperatura, String frecuenciaCardiaca, String frecuenciaRespiratoria, String talla, String peso, String perimetroCefalico, String imc, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_examen_fisico(motivo_consulta, ant_natales, apf, temperatura, frecuencia_cardiaca, frecuencia_respiratoria, talla, peso, perimetro_cefalico, imc, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
	 		 //System.out.print ("SELECT med.nombre,med.concentracion,med.codigo FROM medicamentos med,farc_grupo fgr WHERE med.cod_grupoFK=fgr.codigo AND fgr.tipo='"+tipo+"' AND  med.nombre LIKE '"+cod+"%' OR med.concentracion LIKE '"+cod+"%'");
	 			ps.setString(1, motConsulta);
	 			ps.setString(2, antNatales);
	 			ps.setString(3, apf);
	 			ps.setString(4, temperatura);
	 			ps.setString(5, frecuenciaCardiaca);
	 			ps.setString(6, frecuenciaRespiratoria);
	 			ps.setString(7, talla);
	 			ps.setString(8, peso);
	 			ps.setString(9, perimetroCefalico);
	 			ps.setString(10, imc);		
	 			ps.setString(11, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarExamenFisicoDosCinco "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarSignosPeligroDosCinco
	 public void insertarSignosPeligroDosCinco(String noBebePecho, String letargico, String vomitaTodo, String convulsiones, String obsSignosPeligro, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_signos_peligro(no_bebe_pecho, letargico, vomita_todo, convulsiones, observaciones_signos_peligro, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?)");				
	 			ps.setString(1, noBebePecho);
	 			ps.setString(2, letargico);
	 			ps.setString(3, vomitaTodo);
	 			ps.setString(4, convulsiones);
	 			ps.setString(5, obsSignosPeligro);		
	 			ps.setString(6, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSignosPeligroDosCinco "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarTosDosCincoUno
	 public void insertarTosDosCincoUno(String tieneTos, String desdeCuandoTosDias, String primerEpisodioSibilancias, String sibilanciasRecurrentes, String cuadroGrupal, String antPrematuridad, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_tos(tiene_tos, desde_cuando_tos_dias, primer_episodio_sibilancias, sibilancias_recurrentes, cuadro_gripal, antecedentes_prematuridad, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
	 			ps.setString(1, tieneTos);
	 			ps.setString(2, desdeCuandoTosDias);
	 			ps.setString(3, primerEpisodioSibilancias);
	 			ps.setString(4, sibilanciasRecurrentes);
	 			ps.setString(5, cuadroGrupal);
	 			ps.setString(6, antPrematuridad);		
	 			ps.setString(7, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarTosDosCincoUno "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarTosDosCincoDos
	 public void insertarTosDosCincoDos(String respiracionMinutos, String respiracionRapida, String tirajeSubcostal, String saturacionOxigeno, String tirajeSupraclavicular, String estridor, String sibilancias, String apnea, String incapacidadHablar, String somnoliento, String confuso, String agitado, String obsTos, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_tos_2(respiraciones_por_minutos, respiracion_rapida, tiraje_subcostal, saturacion_oxigeno, tiraje_supraclavicular, estridor, sibilancias, apnea, incapacidad_hablar, somnoliento, confuso, agitado, observaciones_tos, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, respiracionMinutos);
	 			ps.setString(2, respiracionRapida);
	 			ps.setString(3, tirajeSubcostal);
	 			ps.setString(4, saturacionOxigeno);
	 			ps.setString(5, tirajeSupraclavicular);
	 			ps.setString(6, estridor);
	 			ps.setString(7, sibilancias);
	 			ps.setString(8, apnea);
	 			ps.setString(9, incapacidadHablar);
	 			ps.setString(10, somnoliento);	
	 			ps.setString(11, confuso);
	 			ps.setString(12, agitado);
	 			ps.setString(13, obsTos);	
	 			ps.setString(14, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarTosDosCincoDos "+ex);
	 		}
	 				
	 	}
	
		//Método insertarDiarrea
	 public void insertarDiarrea(String tieneDiarrea, String desdeCuandoDiarreaDias, String sangreHeces, String vomito, String numVomitosUlt24H, String numDiarreasUlt24H, String numDiarreasUlt4H, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_diarrea(tiene_diarrea, desde_cuando_diarrea_dias, sangre_heces, vomito, num_vomitos_ult_24h, num_diarreas_ult_24h, num_diarreas_ult_4h, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, tieneDiarrea);
	 			ps.setString(2, desdeCuandoDiarreaDias);
	 			ps.setString(3, sangreHeces);
	 			ps.setString(4, vomito);
	 			ps.setString(5, numVomitosUlt24H);
	 			ps.setString(6, numDiarreasUlt24H);
	 			ps.setString(7, numDiarreasUlt4H);	
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarDiarrea "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarDiarreaDos
	 public void insertarDiarreaDos(String comatoso, String intranquilo, String ojosHundidos, String bebeMal, String bebeAvidamente, String pliegueCutaneo, String obsDiarrea, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_diarrea_2(comatoso, intranquilo, ojos_hundidos, bebe_mal, bebe_avidamente, pliegue_cutaneo, observaciones_diarrea, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, comatoso);
	 			ps.setString(2, intranquilo);
	 			ps.setString(3, ojosHundidos);
	 			ps.setString(4, bebeMal);
	 			ps.setString(5, bebeAvidamente);
	 			ps.setString(6, pliegueCutaneo);
	 			ps.setString(7, obsDiarrea);	
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarDiarreaDos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarFiebre
	 public void insertarFiebre(String tieneFiebre, String desdeCuandoFiebreDias, String fiebre38Centigrados, String fiebre39Centigrados,String visitoUlt15Dias, String zonaDengue, String zonaMalaria, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre(tiene_fiebre, desde_cuando_fiebre_dias, fiebre_38_centigrados, fiebre_39_centigrados,visito_ult_15_dias, zona_dengue, zona_malaria, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, tieneFiebre);
	 			ps.setString(2, desdeCuandoFiebreDias);
	 			ps.setString(3, fiebre38Centigrados);
	 			ps.setString(4, fiebre39Centigrados);
	 			ps.setString(5, visitoUlt15Dias);
	 			ps.setString(6, zonaDengue);
	 			ps.setString(7, zonaMalaria);
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarFiebre "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarFiebreDos
	 public void insertarFiebreDos(String rigidezNuca, String aparienciaEnfermoGrave, String manifestacionesSangrado, String aspectoToxico, String respuestaSocial, String piel, String erupcionCutanea, String dolorAbdominal, String cefalea, String mialgias, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre_2(rigidez_nuca, apariencia_enfermo_grave, manifestaciones_sangrado, aspecto_toxico, respuesta_social, piel, erupcion_cutanea, dolor_abdominal, cefalea, mialgias, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, rigidezNuca);
	 			ps.setString(2, aparienciaEnfermoGrave);
	 			ps.setString(3, manifestacionesSangrado);
	 			ps.setString(4, aspectoToxico);
	 			ps.setString(5, respuestaSocial);
	 			ps.setString(6, piel);
	 			ps.setString(7, erupcionCutanea);
	 			ps.setString(8, dolorAbdominal);
	 			ps.setString(9, cefalea);
	 			ps.setString(10, mialgias);		
	 			ps.setString(11, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarFiebreDos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarFiebreTres
	 public void insertarFiebreTres(String artralgias, String dolorRetroocular, String postracion, String pruebaTorniquete, String lipotimia, String hepatomegalia, String pulsoRapido, String llenadoCapilar, String ascitis, String disminucionDiuresis, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre_3(artralgias, dolor_retroocular, postracion, prueba_torniquete, lipotimia, hepatomegalia, pulso_rapido, llenado_capilar, ascitis, disminucion_diuresis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, artralgias);
	 			ps.setString(2, dolorRetroocular);
	 			ps.setString(3, postracion);
	 			ps.setString(4, pruebaTorniquete);
	 			ps.setString(5, lipotimia);
	 			ps.setString(6, hepatomegalia);
	 			ps.setString(7, pulsoRapido);
	 			ps.setString(8, llenadoCapilar);
	 			ps.setString(9, ascitis);
	 			ps.setString(10, disminucionDiuresis);		
	 			ps.setString(11, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarFiebreTres "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarFiebreCuatro
	 public void insertarFiebreCuatro(String cuadroHematico, String leucocitos, String neutrofilos, String plaquetas, String parcialOrinaInfeccion, String gotaGruesaPositiva, String obsFiebre, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre_4(cuadro_hematico, leucocitos, neutrofilos, plaquetas, parcial_orina_infeccion, gota_gruesa_positiva, observaciones_fiebre, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, cuadroHematico);
	 			ps.setString(2, leucocitos);
	 			ps.setString(3, neutrofilos);
	 			ps.setString(4, plaquetas);
	 			ps.setString(5, parcialOrinaInfeccion);
	 			ps.setString(6, gotaGruesaPositiva);
	 			ps.setString(7, obsFiebre);
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarFiebreCuatro "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarOido
	 public void insertarOido(String tieneProblemaOido, String dolorOido, String supuracion, String desdeCuandoSupuracionDias, String numEpisodiosPrevios, String tumefaccionDetrasOreja, String timpanoRojo, String supuracionOido, String obsOido, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_oido(tiene_problema_oido, dolor_oido, supuracion, desde_cuando_supuracion_dias, num_episodios_previos, tumefaccion_detras_oreja, timpano_rojo, supuracion_oido, observaciones_oido, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, tieneProblemaOido);
	 			ps.setString(2, dolorOido);
	 			ps.setString(3, supuracion);
	 			ps.setString(4, desdeCuandoSupuracionDias);
	 			ps.setString(5, numEpisodiosPrevios);
	 			ps.setString(6, tumefaccionDetrasOreja);
	 			ps.setString(7, timpanoRojo);
	 			ps.setString(8, supuracionOido);
	 			ps.setString(9, obsOido);
	 			ps.setString(10, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarOido "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarGarganta
	 public void insertarGarganta(String tieneProblemaGarganta, String dolorGarganta, String gangliosCuelloDolorosos, String amigdalasEritematosas, String exudadoBlanquecino, String obsGarganta, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_garganta(tiene_problema_garganta, dolor_garganta, ganglios_cuello_dolorosos, amigdalas_eritematosas, exudado_blanquecino, observaciones_garganta, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
	 			ps.setString(1, tieneProblemaGarganta);
	 			ps.setString(2, dolorGarganta);
	 			ps.setString(3, gangliosCuelloDolorosos);
	 			ps.setString(4, amigdalasEritematosas);
	 			ps.setString(5, exudadoBlanquecino);
	 			ps.setString(6, obsGarganta);	
	 			ps.setString(7, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarGarganta "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarSaludBucal
	 public void insertarSaludBucal(String dolorAlComer, String dolorDiente, String traumaCara, String padresCaries, String limpiaBocaManana, String limpiaBocaTarde, String limpiaBocaNoche, String limpiaDientes, String ninoSoloLimpiaDientes, String utilizaCepillo, String utilizaCrema, String utilizaChupo, String consultaOdontologica, String obsTos, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_salud_bucal(dolor_al_comer, dolor_diente, trauma_cara, padres_caries, limpia_boca_manana, limpia_boca_tarde, limpia_boca_noche, limpia_dientes, nino_solo_limpia_dientes, utiliza_cepillo, utiliza_crema, utiliza_seda, utiliza_chupo, consulta_odontologica, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, dolorAlComer);
	 			ps.setString(2, dolorDiente);
	 			ps.setString(3, traumaCara);
	 			ps.setString(4, padresCaries);
	 			ps.setString(5, limpiaBocaManana);
	 			ps.setString(6, limpiaBocaTarde);
	 			ps.setString(7, limpiaBocaNoche);
	 			ps.setString(8, limpiaDientes);
	 			ps.setString(9, ninoSoloLimpiaDientes);
	 			ps.setString(10, utilizaCepillo);	
	 			ps.setString(11, utilizaCrema);
	 			ps.setString(12, utilizaChupo);
	 			ps.setString(13, consultaOdontologica);
	 			ps.setString(14, obsTos);
	 			ps.setString(15, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSaludBucal "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarSaludBucalDos
	 public void insertarSaludBucalDos(String inflamacionDolorosaLabio, String noInvolucraSurco, String enrojecimiento, String inflamacionEncia, String localizado, String generalizado, String deformacionEncia, String exudadoPus, String vesiculas, String ulceras, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_salud_bucal_2(inflamacion_dolorosa_labio, no_involucra_surco, enrojecimiento, inflamacion_encia, localizado, generalizado, deformacion_encia, exudado_pus, vesiculas, ulceras, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, inflamacionDolorosaLabio);
	 			ps.setString(2, noInvolucraSurco);
	 			ps.setString(3, enrojecimiento);
	 			ps.setString(4, inflamacionEncia);
	 			ps.setString(5, localizado);
	 			ps.setString(6, generalizado);
	 			ps.setString(7, deformacionEncia);
	 			ps.setString(8, exudadoPus);
	 			ps.setString(9, vesiculas);
	 			ps.setString(10, ulceras);		
	 			ps.setString(11, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSaludBucalDos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarSaludBucalTres
	 public void insertarSaludBucalTres(String placas, String fractura, String movilidad, String desplazamiento, String extrusion, String intrusion, String avulsion, String herida, String manchasBlancas, String cafes, String cariesCavitacionales, String placaBacteriana, String obsSaludBucal, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_salud_bucal_3(placas, fractura, movilidad, desplazamiento, extrusion, intrusion, avulsion, herida, manchas_blancas, cafes, caries_cavitacionales, placa_bacteriana, observaciones_salud_bucal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, placas);
	 			ps.setString(2, fractura);
	 			ps.setString(3, movilidad);
	 			ps.setString(4, desplazamiento);
	 			ps.setString(5, extrusion);
	 			ps.setString(6, intrusion);
	 			ps.setString(7, avulsion);
	 			ps.setString(8, herida);
	 			ps.setString(9, manchasBlancas);
	 			ps.setString(10, cafes);	
	 			ps.setString(11, cariesCavitacionales);
	 			ps.setString(12, placaBacteriana);
	 			ps.setString(13, obsSaludBucal);	
	 			ps.setString(14, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarSaludBucalTres "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarCrecimiento
	 public void insertarCrecimiento(String emanacionVisible, String pesoEdad, String edemaAmbosPies, String apariencia, String imcEdad, String tallaEdad, String pesoTalla, String tendenciaPeso, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_crecimiento(emanacion_visible, peso_edad, edema_ambos_pies, apariencia, imc_edad, talla_edad, peso_talla, tendencia_peso, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, emanacionVisible);
	 			ps.setString(2, pesoEdad);
	 			ps.setString(3, edemaAmbosPies);
	 			ps.setString(4, apariencia);
	 			ps.setString(5, imcEdad);
	 			ps.setString(6, tallaEdad);
	 			ps.setString(7, pesoTalla);
	 			ps.setString(8, tendenciaPeso);	
	 			ps.setString(9, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarCrecimiento "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarCrecimientoDos
	 public void insertarCrecimientoDos(String desnutricionGlobalSevera, String desnutricionGlobal, String riesgoDesnutricion, String pesoAdecuadoEdad, String desnutricionCronica, String riesgoDNT, String tallaAdecuadaEdad, String desnutricionAgudaSevera, String DNTAguda, String pesoAdecuadoTalla, String sobrepeso, String obesidad, String obsCrecimiento, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_crecimiento_2(desnutricion_global_severa, desnutricion_global, riesgo_desnutricion, peso_adecuado_edad, desnutricion_cronica, riesgo_dnt, talla_adecuada_edad, desnutricion_aguda_severa, dnt_aguda, peso_adecuado_talla, sobrepeso, obesidad, observaciones_crecimiento, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, desnutricionGlobalSevera);
	 			ps.setString(2, desnutricionGlobal);
	 			ps.setString(3, riesgoDesnutricion);
	 			ps.setString(4, pesoAdecuadoEdad);
	 			ps.setString(5, desnutricionCronica);
	 			ps.setString(6, riesgoDNT);
	 			ps.setString(7, tallaAdecuadaEdad);
	 			ps.setString(8, desnutricionAgudaSevera);
	 			ps.setString(9, DNTAguda);
	 			ps.setString(10, pesoAdecuadoTalla);	
	 			ps.setString(11, sobrepeso);
	 			ps.setString(12, obesidad);
	 			ps.setString(13, obsCrecimiento);	
	 			ps.setString(14, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarCrecimientoDos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarAnemia
	 public void insertarAnemia(String recHierroUlt6Meses, String cuandoRecHierro, String cuantoTiempoRecHierro, String palidezPalmar, String palidezConjuntival, String obsAnemia, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_anemia(recibido_hierro_ult_6_meses, cuando_recibio_hierro, cuanto_tiempo_recibio_hierro, palidez_palmar, palidez_conjuntival, observaciones_anemia, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
	 			ps.setString(1, recHierroUlt6Meses);
	 			ps.setString(2, cuandoRecHierro);
	 			ps.setString(3, cuantoTiempoRecHierro);
	 			ps.setString(4, palidezPalmar);
	 			ps.setString(5, palidezConjuntival);
	 			ps.setString(6, obsAnemia);
	 			ps.setString(7, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarAnemia "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltrato
	 public void insertarMaltrato(String comoProdujeronLesiones, String relataMaltrato, String maltratoFisico, String maltratoSexual, String negligencia, String quienMaltrato, String incongruenciaTrauma, String incongruenciaLesionNino, String diferentesVersiones, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato(como_produjeron_lesiones, relata_maltrato, maltrato_fisico, maltrato_sexual, negligencia, quien_maltrato, incongruencia_trauma, incongruencia_lesion_nino, diferentes_versiones, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, comoProdujeronLesiones);
	 			ps.setString(2, relataMaltrato);
	 			ps.setString(3, maltratoFisico);
	 			ps.setString(4, maltratoSexual);
	 			ps.setString(5, negligencia);
	 			ps.setString(6, quienMaltrato);
	 			ps.setString(7, incongruenciaTrauma);
	 			ps.setString(8, incongruenciaLesionNino);
	 			ps.setString(9, diferentesVersiones);
	 			ps.setString(10, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltrato "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoDos
	 public void insertarMaltratoDos(String consultaTardia, String frecuenciaPegaHijo, String desobedienteHijo, String comportamientoAnormalPadres, String descuidoNinoSalud, String porqueDescuidoNinoSalud, String enQueDescuidoNino, String factorRiesgo, String actitudAnormalNino, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_2(consulta_tardia, frecuencia_pega_hijo, desobediente_hijo, comportamiento_anormal_padres, descuido_nino_salud, porque_descuido_nino_salud, en_que_descuido_nino, factor_riesgo, actitud_anormal_nino, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, consultaTardia);
	 			ps.setString(2, frecuenciaPegaHijo);
	 			ps.setString(3, desobedienteHijo);
	 			ps.setString(4, comportamientoAnormalPadres);
	 			ps.setString(5, descuidoNinoSalud);
	 			ps.setString(6, porqueDescuidoNinoSalud);
	 			ps.setString(7, enQueDescuidoNino);
	 			ps.setString(8, factorRiesgo);
	 			ps.setString(9, actitudAnormalNino);
	 			ps.setString(10, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoDos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoTres
	 public void insertarMaltratoTres(String equimosis, String hematomas, String lasceraciones, String mordiscos, String cicatrices, String diferenteEvolucionNinos, String sugestivasMaltrato, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_3(equimosis, hematomas, lasceraciones, mordiscos, cicatrices, dif_evolucion_ninos, sugestivas_maltrato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, equimosis);
	 			ps.setString(2, hematomas);
	 			ps.setString(3, lasceraciones);
	 			ps.setString(4, mordiscos);
	 			ps.setString(5, cicatrices);
	 			ps.setString(6, diferenteEvolucionNinos);
	 			ps.setString(7, sugestivasMaltrato);
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoTres "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoCuatro
	 public void insertarMaltratoCuatro(String traumaVisceral, String traumaGrave, String lesionFisicaSugestiva, String sangradoVaginal, String juegoSexual, String bocaGenitales, String vih, String gonorrea, String sifilis, String trichomona, String chlamydiaTrachomatis, String condilomatosis, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_4(trauma_visceral, trauma_grave, lesion_fisica_sugestiva, sangrado_vaginal, juego_sexual, boca_genitales, vih, gonorrea, sifilis, trichomona, chlamydia_trachomatis, condilomatosis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, traumaVisceral);
	 			ps.setString(2, traumaGrave);
	 			ps.setString(3, lesionFisicaSugestiva);
	 			ps.setString(4, sangradoVaginal);
	 			ps.setString(5, juegoSexual);
	 			ps.setString(6, bocaGenitales);
	 			ps.setString(7, vih);
	 			ps.setString(8, gonorrea);
	 			ps.setString(9, sifilis);
	 			ps.setString(10, trichomona);	
	 			ps.setString(11, chlamydiaTrachomatis);
	 			ps.setString(12, condilomatosis);
	 			ps.setString(13, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoCuatro "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoCinco
	 public void insertarMaltratoCinco(String temeroso, String retraido, String rechazoAdulto, String deprimido, String evitaContactoVisual, String trastornoSueno, String trastornoAlimentario, String problemasPsicosomaticos, String conductasRegresivas, String desarrolloEstancado, String violenciaIntrafamiliar, String familiaCaotica, String cuidadoresAdictos, String obsMaltrato, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_5(temeroso, retraido, rechazo_adulto, deprimido, evita_contacto_visual, trastorno_sueno, trastorno_alimentario, problemas_psicosomaticos, conductas_regresivas, desarrollo_estancado, violencia_intrafamiliar, familia_caotica, cuidadores_adictos, observaciones_maltrato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, temeroso);
	 			ps.setString(2, retraido);
	 			ps.setString(3, rechazoAdulto);
	 			ps.setString(4, deprimido);
	 			ps.setString(5, evitaContactoVisual);
	 			ps.setString(6, trastornoSueno);
	 			ps.setString(7, trastornoAlimentario);
	 			ps.setString(8, problemasPsicosomaticos);
	 			ps.setString(9, conductasRegresivas);
	 			ps.setString(10, desarrolloEstancado);	
	 			ps.setString(11, violenciaIntrafamiliar);
	 			ps.setString(12, familiaCaotica);
	 			ps.setString(13, cuidadoresAdictos);
	 			ps.setString(14, obsMaltrato);
	 			ps.setString(15, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoCinco "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoCraneo
	 public void insertarMaltratoCraneo(String fracturas, String hematomas, String hemorragiasRetinianas, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_craneo(fracturas, hematomas, hemorragias_retinianas, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?)");				
	 			ps.setString(1, fracturas);
	 			ps.setString(2, hematomas);
	 			ps.setString(3, hemorragiasRetinianas);
	 			ps.setString(4, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoCraneo "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoFracturas
	 public void insertarMaltratoFracturas(String costillas, String huesosLargos, String espirales, String oblicua, String metafisiarias, String esternon, String escapula, String menorCincoAnos, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_fracturas(costillas, huesos_largos, espirales, oblicua, metafisiarias, esternon, escapula, menor_cinco_anos, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, costillas);
	 			ps.setString(2, huesosLargos);
	 			ps.setString(3, espirales);
	 			ps.setString(4, oblicua);
	 			ps.setString(5, metafisiarias);
	 			ps.setString(6, esternon);
	 			ps.setString(7, escapula);
	 			ps.setString(8, menorCincoAnos);
	 			ps.setString(9, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoFracturas "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoQuemaduras
	 public void insertarMaltratoQuemaduras(String areasCubiertasRopa, String patronSimetricoDemarcado, String denotaObjetoQuemadura, String quemaduraCuerpo, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_quemaduras(areas_cubiertas_ropa, patron_simetrico_demarcado, denota_objeto_quemadura, quemadura_cuerpo, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?)");				
	 			ps.setString(1, areasCubiertasRopa);
	 			ps.setString(2, patronSimetricoDemarcado);
	 			ps.setString(3, denotaObjetoQuemadura);
	 			ps.setString(4, quemaduraCuerpo);
	 			ps.setString(5, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoQuemaduras "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarMaltratoTraumaGenital
	 public void insertarMaltratoTraumaGenital(String lasceracionAguda, String lasceracionPerianal, String ausenciaHimen, String himenCicatrizado, String cicatrizNavicular, String anoDilatado, String hallazgoSemen, String flujoGenital, String cuerpoExtranoVaginaAno, String vesiculas, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_trauma_genital(lasceracion_aguda, lasceracion_perianal, ausencia_himen, himen_cicatrizado, cicatriz_navicular, ano_dilatado, hallazgo_semen, flujo_genital, cuerpo_extrano_vagina_ano, vesiculas, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, lasceracionAguda);
	 			ps.setString(2, lasceracionPerianal);
	 			ps.setString(3, ausenciaHimen);
	 			ps.setString(4, himenCicatrizado);
	 			ps.setString(5, cicatrizNavicular);
	 			ps.setString(6, anoDilatado);
	 			ps.setString(7, hallazgoSemen);
	 			ps.setString(8, flujoGenital);
	 			ps.setString(9, cuerpoExtranoVaginaAno);
	 			ps.setString(10, vesiculas);	
	 			ps.setString(11, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarMaltratoTraumaGenital "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarDesarrollo
	 public void insertarDesarrollo(String antImportante, String antDesarrollo, String factorRiesgoAnt, String perimetroCefalico, String alteracionesFenotipicas, String menosDosDe, String masDosDe, String obsDesarrollo, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_desarrollo(antecedente_importante, antecedente_para_desarrollo, factor_riesgo_antecedente, perimetro_cefalico, alteraciones_fenotipicas, menos_dos_de, mas_dos_de, observaciones_desarrollo, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, antImportante);
	 			ps.setString(2, antDesarrollo);
	 			ps.setString(3, factorRiesgoAnt);
	 			ps.setString(4, perimetroCefalico);
	 			ps.setString(5, alteracionesFenotipicas);
	 			ps.setString(6, menosDosDe);
	 			ps.setString(7, masDosDe);
	 			ps.setString(8, obsDesarrollo);
	 			ps.setString(9, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarDesarrollo "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarDesarrolloCondiciones
	 public void insertarDesarrolloCondiciones(String realizaUnaCondicionEdad, String realizaDosCondicionEdad, String realizaTresCondicionEdad, String realizaCuatroCondicionEdad, String ausenciaUnaCondicionEdad, String ausenciaDosCondicionEdad, String ausenciaTresCondicionEdad, String ausenciaCuatroCondicionEdad, String ausenciaUnaCondicionGrupoAnterior, String ausenciaDosCondicionGrupoAnterior, String ausenciaTresCondicionGrupoAnterior, String ausenciaCuatroCondicionGrupoAnterior, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_desarrollo_condiciones(realiza_una_condicion_edad, realiza_dos_condicion_edad, realiza_tres_condicion_edad, realiza_cuatro_condicion_edad, ausencia_una_condicion_edad, ausencia_dos_condicion_edad, ausencia_tres_condicion_edad, ausencia_cuatro_condicion_edad, ausencia_una_condicion_grupo_anterior, ausencia_dos_condicion_grupo_anterior, ausencia_tres_condicion_grupo_anterior, ausencia_cuatro_condicion_grupo_anterior, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, realizaUnaCondicionEdad);
	 			ps.setString(2, realizaDosCondicionEdad);
	 			ps.setString(3, realizaTresCondicionEdad);
	 			ps.setString(4, realizaCuatroCondicionEdad);
	 			ps.setString(5, ausenciaUnaCondicionEdad);
	 			ps.setString(6, ausenciaDosCondicionEdad);
	 			ps.setString(7, ausenciaTresCondicionEdad);
	 			ps.setString(8, ausenciaCuatroCondicionEdad);
	 			ps.setString(9, ausenciaUnaCondicionGrupoAnterior);
	 			ps.setString(10, ausenciaDosCondicionGrupoAnterior);	
	 			ps.setString(11, ausenciaTresCondicionGrupoAnterior);
	 			ps.setString(12, ausenciaCuatroCondicionGrupoAnterior);
	 			ps.setString(13, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarDesarrolloCondiciones "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarAntecedentesVacunacion
	 public void insertarAntecedentesVacunacion(String BCG1, String hepatitisBRN, String hepatitisB1, String hepatitisB2, String hepatitisB3, String DPT1, String DPT2, String DPT3, String DPTR1, String DPTR2, String VOP1, String VOP2, String VOP3, String VOPR1, String VOPR2, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_ant_vacunacion(bcg_1, hepatitis_b_rn, hepatitis_b_1, hepatitis_b_2, hepatitis_b_3, dpt_1, dpt_2, dpt_3, dpt_r1, dpt_r2, vop_1, vop_2, vop_3, vop_r1, vop_r2, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, BCG1);
	 			ps.setString(2, hepatitisBRN);
	 			ps.setString(3, hepatitisB1);
	 			ps.setString(4, hepatitisB2);
	 			ps.setString(5, hepatitisB3);
	 			ps.setString(6, DPT1);
	 			ps.setString(7, DPT2);
	 			ps.setString(8, DPT3);
	 			ps.setString(9, DPTR1);
	 			ps.setString(10, DPTR2);	
	 			ps.setString(11, VOP1);
	 			ps.setString(12, VOP2);
	 			ps.setString(13, VOP3);
	 			ps.setString(14, VOPR1);
	 			ps.setString(15, VOPR2);
	 			ps.setString(16, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarAntecedentesVacunacion "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarAntecedentesVacunacionDos
	 public void insertarAntecedentesVacunacionDos(String SRP1, String SRP2, String rotavirus1, String rotavirus2, String streptococoNeumoniae1, String streptococoNeumoniae2, String streptococoNeumoniae3, String haemophilus1, String haemophilus2, String haemophilus3, String haemophilusR1, String haemophilusR2, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_ant_vacunacion_2(srp_1, srp_2, rotavirus_1, rotavirus_2, streptococo_neumoniae_1, streptococo_neumoniae_2, streptococo_neumoniae_3, haemophilus_1, haemophilus_2, haemophilus_3, haemophilus_r1, haemophilus_r2, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, SRP1);
	 			ps.setString(2, SRP2);
	 			ps.setString(3, rotavirus1);
	 			ps.setString(4, rotavirus2);
	 			ps.setString(5, streptococoNeumoniae1);
	 			ps.setString(6, streptococoNeumoniae2);
	 			ps.setString(7, streptococoNeumoniae3);
	 			ps.setString(8, haemophilus1);
	 			ps.setString(9, haemophilus2);
	 			ps.setString(10, haemophilus3);	
	 			ps.setString(11, haemophilusR1);
	 			ps.setString(12, haemophilusR2);
	 			ps.setString(13, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarAntecedentesVacunacionDos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarAntecedentesVacunacionTres
	 public void insertarAntecedentesVacunacionTres(String influenzaDosis, String fiebreAmarillaEdad, String otrasVacunas, String vacunasPendientes, String proximasVacunas, String tiempoVacunaMeses, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_ant_vacunacion_3(influenza_dosis, fiebre_amarilla_edad, otras_vacunas, vacunas_pendientes, proximas_vacunas, tiempo_vacuna_meses, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
	 			ps.setString(1, influenzaDosis);
	 			ps.setString(2, fiebreAmarillaEdad);
	 			ps.setString(3, otrasVacunas);
	 			ps.setString(4, vacunasPendientes);
	 			ps.setString(5, proximasVacunas);
	 			ps.setString(6, tiempoVacunaMeses);
	 			ps.setString(7, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarAntecedentesVacunacionTres "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarCompletarExamenFisico
	 public void insertarCompletarExamenFisico(String completarExamenFisico, String otroProblemaDetectado, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_completar_exa_fisico(completar_examen_fisico, otro_problema_detectado, id_informe_aiepi_2a5_fk) VALUES(?,?,?)");				
	 			ps.setString(1, completarExamenFisico);
	 			ps.setString(2, otroProblemaDetectado);	
	 			ps.setString(3, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarCompletarExamenFisico "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarAlimentacion
	 public void insertarAlimentacion(String lecheMaterna, String cuantasVecesLecMat24H, String recibePechoNoche, String extraeLeche, String comoGuardaLeche, String menor6MesesRecOtraLeche, String queAlimentosRecibe, String cuantasVecesRecAlimentos, String conQueRecAlimento, String quienDaComer, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_alimentacion(leche_materna, cuantas_veces_lec_mat_24h, recibe_pecho_noche, extrae_leche, como_guarda_leche, menor_6_meses_rec_otra_leche, que_alimentos_recibe, cuantas_veces_rec_alimentos, con_que_rec_alimento, quien_da_comer, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, lecheMaterna);
	 			ps.setString(2, cuantasVecesLecMat24H);
	 			ps.setString(3, recibePechoNoche);
	 			ps.setString(4, extraeLeche);
	 			ps.setString(5, comoGuardaLeche);
	 			ps.setString(6, menor6MesesRecOtraLeche);
	 			ps.setString(7, queAlimentosRecibe);
	 			ps.setString(8, cuantasVecesRecAlimentos);
	 			ps.setString(9, conQueRecAlimento);
	 			ps.setString(10, quienDaComer);	
	 			ps.setString(11, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarAlimentacion "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarAlimentacionDos
	 public void insertarAlimentacionDos(String enfermo, String queComioEnfermedad, String obeso, String padresHermanosObesos, String ninoEjercicio, String asisteProgramaNutricional, String obsAlimentacion, String problemaDetectado, String recomendacionesAlimentacion, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_alimentacion_2(enfermo, que_comio_enfermedad, obeso, padres_hermanos_obesos, nino_ejercicio, asiste_prog_nutricional, observaciones_alimentacion, problema_detectado, recomendaciones, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
	 		    ps.setString(1, enfermo);
	 		   	ps.setString(2, queComioEnfermedad);
	 		    ps.setString(3, obeso);
	 			ps.setString(4, padresHermanosObesos);
	 			ps.setString(5, ninoEjercicio);
	 			ps.setString(6, asisteProgramaNutricional);
	 			ps.setString(7, obsAlimentacion);
	 			ps.setString(8, problemaDetectado);
	 			ps.setString(9, recomendacionesAlimentacion);
	 			ps.setString(10, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarAlimentacionDos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarAlimentacionMayorSeisMeses
	 public void insertarAlimentacionMayorSeisMeses(String cuantasComidasRecAyer, String tamanoPorcionesAyer, String comidasEspesasAyer, String comidaOrigenAnimal, String productosLacteosAyer, String legumbresAyer, String vegetalesAyer, String aceiteComidaAyer, String quienDioComidaAyer, String ninoComePropioPlato, String ninoRecVitaminas, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_alimentacion_mayor_6_meses(cuantas_comidas_rec_ayer, tamano_porciones_ayer, comidas_espesas_ayer, comida_origen_animal, productos_lacteos_ayer, legumbres_ayer, vegetales_ayer, aceite_comida_ayer, quien_dio_comida_ayer, nino_come_propio_plato, nino_rec_vitaminas, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, cuantasComidasRecAyer);
	 			ps.setString(2, tamanoPorcionesAyer);
	 			ps.setString(3, comidasEspesasAyer);
	 			ps.setString(4, comidaOrigenAnimal);
	 			ps.setString(5, productosLacteosAyer);
	 			ps.setString(6, legumbresAyer);
	 			ps.setString(7, vegetalesAyer);
	 			ps.setString(8, aceiteComidaAyer);
	 			ps.setString(9, quienDioComidaAyer);
	 			ps.setString(10, ninoComePropioPlato);	
	 			ps.setString(11, ninoRecVitaminas);
	 			ps.setString(12, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarAlimentacionMayorSeisMeses "+ex);
	 		}
	 				
	 	}
	 
	 //Método insertarRecomendacionesDosCinco
	 public void insertarRecomendacionesDosCinco(String tratamientoPac, String signosAlarma, String volverConsControl, String dondeConsControl, String volverConsNinoSano, String referidoConsulta, String recomendacionesDesarrollo, String recomendacionesBuenTrato, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_recomendaciones(tratamiento_pac, signos_alarma, volver_cons_control, donde_cons_control, volver_cons_nino_sano, referido_consulta, recomendaciones_desarrollo, recomendaciones_buen_trato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, tratamientoPac);
	 			ps.setString(2, signosAlarma);
	 			ps.setString(3, volverConsControl);
	 			ps.setString(4, dondeConsControl);
	 			ps.setString(5, volverConsNinoSano);
	 			ps.setString(6, referidoConsulta);
	 			ps.setString(7, recomendacionesDesarrollo);
	 			ps.setString(8, recomendacionesBuenTrato);	
	 			ps.setString(9, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRecomendacionesDosCinco "+ex);
	 		}
	 				
	 	}
	 
	 //Método insertarNombreDxCeroDos
	 public void insertarNombreDxDosCinco(String diagnosticoPac, String codigoCIEPac, String CodReporte){ 
		 System.out.println("4. insertarNombreDxDosCinco");
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_nombredx(diagnostico_pac, codigo_cie_pac, id_informe_aiepi_2a5_fk) VALUES(?,?,?)");				
	 			ps.setString(1, diagnosticoPac);
	 			ps.setString(2, codigoCIEPac);
	 			ps.setString(3, CodReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarNombreDxDosCinco "+ex);
	 		}
	 				
	 	}
	 
	 //Método insertarRespuestasAnemia
	 public void insertarRespuestasAnemia(String anemiaSevera, String anemia, String noTieneAnemia, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_anemia(anemia_severa, anemia, no_tiene_anemia, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?)");				
	 			ps.setString(1, anemiaSevera);
	 			ps.setString(2, anemia);
	 			ps.setString(3, noTieneAnemia);	
	 			ps.setString(4, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasAnemia "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasCrecimiento
	 public void insertarRespuestasCrecimiento(String obeso, String sobrepeso, String desnutricionSevera, String desnutricion, String riesgoDesnutricion, String adecuadoEstadoNutriconal, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_crecimiento(obeso, sobrepeso, desnutricion_severa, desnutricion, riesgo_desnutricion, adecuado_estado_nutriconal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
	 			ps.setString(1, obeso);
	 			ps.setString(2, sobrepeso);
	 			ps.setString(3, desnutricionSevera);
	 			ps.setString(4, desnutricion);
	 			ps.setString(5, riesgoDesnutricion);
	 			ps.setString(6, adecuadoEstadoNutriconal);
	 			ps.setString(7, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasCrecimiento "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasDesarrolloDosCinco
	 public void insertarRespuestasDesarrolloDosCinco(String probableRetrasoDesarrollo, String riesgoProblemaDesarrollo, String desarrolloNormalFactorRiesgo, String desarrolloNormal, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_desarrollo(probable_retraso_desarrollo, riesgo_problema_desarrollo, desarrollo_normal_factor_riesgo, desarrollo_normal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?)");				
	 			ps.setString(1, probableRetrasoDesarrollo);
	 			ps.setString(2, riesgoProblemaDesarrollo);
	 			ps.setString(3, desarrolloNormalFactorRiesgo);
	 			ps.setString(4, desarrolloNormal);
	 			ps.setString(5, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasDesarrolloDosCinco "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasDiarrea
	 public void insertarRespuestasDiarrea(String deshidratacionGrave, String algunGradoDeshidratacion, String altoRiesgoDeshidratacion, String sinDeshidratacion, String diarreaPersistenteGrave, String diarreaPersistente, String disenteria, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_diarrea(deshidratacion_grave, algun_grado_deshidratacion, alto_riesgo_deshidratacion, sin_deshidratacion, diarrea_persistente_grave, diarrea_persistente, disenteria, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, deshidratacionGrave);
	 			ps.setString(2, algunGradoDeshidratacion);
	 			ps.setString(3, altoRiesgoDeshidratacion);
	 			ps.setString(4, sinDeshidratacion);
	 			ps.setString(5, diarreaPersistenteGrave);
	 			ps.setString(6, diarreaPersistente);
	 			ps.setString(7, disenteria);
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasDiarrea "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasFiebre
	 public void insertarRespuestasFiebre(String enfFebrilRiesgoAlto, String enfFebrilRiesgoIntermedio, String enfFebrilRiesgoBajo, String malariaComplicada, String malaria, String dengueGrave, String dengueSignosAlarma, String probableDengue, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_fiebre(enf_febril_riesgo_alto, enf_febril_riesgo_intermedio, enf_febril_riesgo_bajo, malaria_complicada, malaria, dengue_grave, dengue_signos_alarma, probable_dengue, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, enfFebrilRiesgoAlto);
	 			ps.setString(2, enfFebrilRiesgoIntermedio);
	 			ps.setString(3, enfFebrilRiesgoBajo);
	 			ps.setString(4, malariaComplicada);
	 			ps.setString(5, malaria);
	 			ps.setString(6, dengueGrave);
	 			ps.setString(7, dengueSignosAlarma);
	 			ps.setString(8, probableDengue);
	 			ps.setString(9, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasFiebre "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasGarganta
	 public void insertarRespuestasGarganta(String faringoamigdalitis, String estreptococica, String faringoamigdalitisViral, String noTieneFaringoamigdalitis, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_garganta(faringoamigdalitis, estreptococica, faringoamigdalitis_viral, no_tiene_faringoamigdalitis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?)");				
	 			ps.setString(1, faringoamigdalitis);
	 			ps.setString(2, estreptococica);
	 			ps.setString(3, faringoamigdalitisViral);
	 			ps.setString(4, noTieneFaringoamigdalitis);	
	 			ps.setString(5, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasGarganta "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasMaltrato
	 public void insertarRespuestasMaltrato(String maltratoFisicoMuyGrave, String abusoGrave, String maltratoFisicoResp, String sospechaAbusoSexual, String maltratoEmocional, String negligenciaAbandono, String noSospechaMaltrato, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_maltrato(maltrato_fisico_muy_grave, abuso_grave, maltrato_fisico_resp, sospecha_abuso_sexual, maltrato_emocional, negligencia_abandono, no_sospecha_maltrato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, maltratoFisicoMuyGrave);
	 			ps.setString(2, abusoGrave);
	 			ps.setString(3, maltratoFisicoResp);
	 			ps.setString(4, sospechaAbusoSexual);
	 			ps.setString(5, maltratoEmocional);
	 			ps.setString(6, negligenciaAbandono);
	 			ps.setString(7, noSospechaMaltrato);
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasMaltrato "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasOido
	 public void insertarRespuestasOido(String mastoiditis, String otitisMediaCronica, String otitisMediaRecurrente, String otitisMediaAguda, String noTieneOtitis, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_oido(mastoiditis, otitis_media_cronica, otitis_media_recurrente, otitis_media_aguda, no_tiene_otitis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?)");				
	 			ps.setString(1, mastoiditis);
	 			ps.setString(2, otitisMediaCronica);
	 			ps.setString(3, otitisMediaRecurrente);
	 			ps.setString(4, otitisMediaAguda);
	 			ps.setString(5, noTieneOtitis);	
	 			ps.setString(6, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasOido "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasSaludBucal
	 public void insertarRespuestasSaludBucal(String celulitisFacial, String enfBucalGrave, String traumaBucodental, String estomatitis, String enfDentalGingival, String altoRiesgoEnfBucal, String bajoRiesgoEnfBucal, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_salud_bucal(celulitis_facial, enf_bucal_grave, trauma_bucodental, estomatitis, enf_dental_gingival, alto_riesgo_enf_bucal, bajo_riesgo_enf_bucal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, celulitisFacial);
	 			ps.setString(2, enfBucalGrave);
	 			ps.setString(3, traumaBucodental);
	 			ps.setString(4, estomatitis);
	 			ps.setString(5, enfDentalGingival);
	 			ps.setString(6, altoRiesgoEnfBucal);
	 			ps.setString(7, bajoRiesgoEnfBucal);
	 			ps.setString(8, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasSaludBucal "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasSignosPeligro
	 public void insertarRespuestasSignosPeligro(String enfMuyGrave, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_signos_peligro(enf_muy_grave, id_informe_aiepi_2a5_fk) VALUES(?,?)");				
	 			ps.setString(1, enfMuyGrave);	
	 			ps.setString(2, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasSignosPeligro "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRespuestasTos
	 public void insertarRespuestasTos(String grupGrave, String bronquiolitisGrave, String sibilanciaGrave, String crup, String sibilanciaRecurrente, String neumoniaGrave, String neumonia, String tos, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_tos(grup_grave, bronquiolitis_grave, sibilancia_grave, crup, sibilancia_recurrente, neumonia_grave, neumonia, tos, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, grupGrave);
	 			ps.setString(2, bronquiolitisGrave);
	 			ps.setString(3, sibilanciaGrave);
	 			ps.setString(4, crup);
	 			ps.setString(5, sibilanciaRecurrente);
	 			ps.setString(6, neumoniaGrave);
	 			ps.setString(7, neumonia);
	 			ps.setString(8, tos);	
	 			ps.setString(9, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRespuestasTos "+ex);
	 		}
	 				
	 	}
	 
		//Método insertarRecomendacionesDosis
	 public void insertarRecomendacionesDosis(String recVitaminaA, String proxDosisVitaminaA, String recAlbendazol, String proxDosisAlbendazol, String recHierro, String cuandoRecHierro, String volverRecHierro, String recZinc, String cuantoTiempoZinc, String iniciaZinc, String codReporte){	 
	 		
	 		try{				
	 			PreparedStatement ps = null;
	 		    Conexion con=new Conexion();
	 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_recomendaciones_dosis(rec_vitamina_a, prox_dosis_vitamina_a, rec_albendazol, prox_dosis_albendazol, rec_hierro, cuando_rec_hierro, volver_rec_hierro, rec_zinc, cuanto_tiempo_zinc, inicia_zinc, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
	 			ps.setString(1, recVitaminaA);
	 			ps.setString(2, proxDosisVitaminaA);
	 			ps.setString(3, recAlbendazol);
	 			ps.setString(4, proxDosisAlbendazol);
	 			ps.setString(5, recHierro);
	 			ps.setString(6, cuandoRecHierro);
	 			ps.setString(7, volverRecHierro);
	 			ps.setString(8, recZinc);
	 			ps.setString(9, cuantoTiempoZinc);
	 			ps.setString(10, iniciaZinc);	
	 			ps.setString(11, codReporte);
	 	
	 			ps.execute();
	 			ps.close();
	 			con.cerrar();
	 					
	 		}catch(Exception ex){
	 			ex.getMessage();
	 			System.out.print("Error en MetodoAiepiMultiplePacientes>>insertarRecomendacionesDosis "+ex);
	 		}
	 				
	 	}
	 
	 	/***************************************************************************************************/
		/*************************************AIEPI EMBARAZADAS*********************************************/
		/***************************************************************************************************/
			/*OBTENER HORA Y FECHA PARA DETERMINAR EL CODIGO DEL INFORME*/
		//Obtener Hora y Fecha
		public java.sql.ResultSet ObtenerCodigoInformeEmbarazadas(String Fecha, String Hora) {

		java.sql.ResultSet rs = null;
		Statement st = null;

		try {
		Conexion con = new Conexion();
		st = con.conn.createStatement();
		rs = st.executeQuery("SELECT * FROM aiepi_emb_informe WHERE fecha_informe='"+Fecha+"' AND hora_informe='"+Hora+"' ");

		} catch (Exception ex) {
		ex.getMessage();
		}

		return rs;
		}
		
		 ///Buscar Informe
		 public java.sql.ResultSet buscarInformesAIEPIEmbarazadasRealizados(String CodPac) {
				java.sql.ResultSet rs = null;
				Statement st = null;
				try {
					Conexion con = new Conexion();
					st = con.conn.createStatement();
					rs = st.executeQuery(
							"SELECT " +
					        "CONCAT(pac.nombre,' ',pac.primer_apellido,' ',pac.segundo_apellido)," +
							"inf.id_informe_aiepi_embarazadas," +
							"CONCAT(inf.fecha_informe,'/',inf.hora_informe), " +
							"pac_codigo_paciente,inf.nombre_informe " +
							"FROM " +
							"adm_paciente pac, " +
							"aiepi_emb_informe inf " +
							"WHERE " +
							"pac.pac_codigo_paciente = inf.id_paciente " +
							"AND pac.pac_codigo_paciente='"+CodPac+"' "); 					
					
				} catch (Exception ex) {
					System.out.println("Error en MetodoAiepiMultiplePacientes>>buscarInformesAIEPIEmbarazadasRealizados "+ex);
				}
				return rs;
			}
		
		
		 //Insertar Informe
		 public boolean insertarEncabezadoInformeAiepiEmbarazadas(String nombreUsuario,String codPaciente,String hora,String fecha, String codAdmision) {
				PreparedStatement psc = null;
				try {
					Conexion con = new Conexion();
					psc = con.conn.prepareStatement("INSERT INTO " +
							"aiepi_emb_informe(" +
							"id_usuario," +
							"id_paciente," +
							"hora_informe," +
							"fecha_informe" +
							"cod_admision" +
							")" +
							" VALUES " +
							"(?,?,?,?,?)");
					psc.setString(1, nombreUsuario);
					psc.setString(2, codPaciente);
					psc.setString(3, hora);
					psc.setString(4, fecha);
					psc.setString(5, codAdmision);

					psc.execute();
					psc.close();
					con.cerrar();
					return true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		 
		//Método insertarExamenFisicoDosCinco
		/* public void insertarExamenFisicoDosCinco(String motConsulta, String antNatales, String apf, String temperatura, String frecuenciaCardiaca, String frecuenciaRespiratoria, String talla, String peso, String perimetroCefalico, String imc, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_examen_fisico(motivo_consulta, ant_natales, apf, temperatura, frecuencia_cardiaca, frecuencia_respiratoria, talla, peso, perimetro_cefalico, imc, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		 		 //System.out.print ("SELECT med.nombre,med.concentracion,med.codigo FROM medicamentos med,farc_grupo fgr WHERE med.cod_grupoFK=fgr.codigo AND fgr.tipo='"+tipo+"' AND  med.nombre LIKE '"+cod+"%' OR med.concentracion LIKE '"+cod+"%'");
		 			ps.setString(1, motConsulta);
		 			ps.setString(2, antNatales);
		 			ps.setString(3, apf);
		 			ps.setString(4, temperatura);
		 			ps.setString(5, frecuenciaCardiaca);
		 			ps.setString(6, frecuenciaRespiratoria);
		 			ps.setString(7, talla);
		 			ps.setString(8, peso);
		 			ps.setString(9, perimetroCefalico);
		 			ps.setString(10, imc);		
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarExamenFisicoDosCinco "+ex);
		 		}
		 				
		 	}
		 
		 //Método insertarRiesgoGestacionEmb
		 public void insertarRiesgoGestacionEmb(String controlPrenatal, String percibeMovFetales, String fiebreFrecuente, String liquidoVagina, String flujoVaginal, String padeceEnfermedad, String cualEnfermedad, String cigarrillo, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_emb_riesgo_gestacion(id_riesgo_gestacion, control_prenatal, percibe_movimientos_fetales, fiebre_frecuente, liquido_vagina, flujo_vaginal, padece_enfermedad, cual_enfermedad, cigarrillo, id_informe_aiepi_embarazadas_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, controlPrenatal);
		 			ps.setString(2, percibeMovFetales);
		 			ps.setString(3, fiebreFrecuente);
		 			ps.setString(4, liquidoVagina);
		 			ps.setString(5, flujoVaginal);
		 			ps.setString(6, padeceEnfermedad);
		 			ps.setString(7, cualEnfermedad);
		 			ps.setString(8, cigarrillo);
		 			ps.setString(9, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRiesgoGestacionEmb "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarTosDosCincoUno
		 public void insertarTosDosCincoUno(String tieneTos, String desdeCuandoTosDias, String primerEpisodioSibilancias, String sibilanciasRecurrentes, String cuadroGrupal, String antPrematuridad, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_tos(tiene_tos, desde_cuando_tos_dias, primer_episodio_sibilancias, sibilancias_recurrentes, cuadro_gripal, antecedentes_prematuridad, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
		 			ps.setString(1, tieneTos);
		 			ps.setString(2, desdeCuandoTosDias);
		 			ps.setString(3, primerEpisodioSibilancias);
		 			ps.setString(4, sibilanciasRecurrentes);
		 			ps.setString(5, cuadroGrupal);
		 			ps.setString(6, antPrematuridad);		
		 			ps.setString(7, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarTosDosCincoUno "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarTosDosCincoDos
		 public void insertarTosDosCincoDos(String respiracionMinutos, String respiracionRapida, String tirajeSubcostal, String saturacionOxigeno, String tirajeSupraclavicular, String estridor, String sibilancias, String apnea, String incapacidadHablar, String somnoliento, String confuso, String agitado, String obsTos, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_tos_2(respiraciones_por_minutos, respiracion_rapida, tiraje_subcostal, saturacion_oxigeno, tiraje_supraclavicular, estridor, sibilancias, apnea, incapacidad_hablar, somnoliento, confuso, agitado, observaciones_tos, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, respiracionMinutos);
		 			ps.setString(2, respiracionRapida);
		 			ps.setString(3, tirajeSubcostal);
		 			ps.setString(4, saturacionOxigeno);
		 			ps.setString(5, tirajeSupraclavicular);
		 			ps.setString(6, estridor);
		 			ps.setString(7, sibilancias);
		 			ps.setString(8, apnea);
		 			ps.setString(9, incapacidadHablar);
		 			ps.setString(10, somnoliento);	
		 			ps.setString(11, confuso);
		 			ps.setString(12, agitado);
		 			ps.setString(13, obsTos);	
		 			ps.setString(14, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarTosDosCincoDos "+ex);
		 		}
		 				
		 	}
		
			//Método insertarDiarrea
		 public void insertarDiarrea(String tieneDiarrea, String desdeCuandoDiarreaDias, String sangreHeces, String vomito, String numVomitosUlt24H, String numDiarreasUlt24H, String numDiarreasUlt4H, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_diarrea(tiene_diarrea, desde_cuando_diarrea_dias, sangre_heces, vomito, num_vomitos_ult_24h, num_diarreas_ult_24h, num_diarreas_ult_4h, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, tieneDiarrea);
		 			ps.setString(2, desdeCuandoDiarreaDias);
		 			ps.setString(3, sangreHeces);
		 			ps.setString(4, vomito);
		 			ps.setString(5, numVomitosUlt24H);
		 			ps.setString(6, numDiarreasUlt24H);
		 			ps.setString(7, numDiarreasUlt4H);	
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarDiarrea "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarDiarreaDos
		 public void insertarDiarreaDos(String comatoso, String intranquilo, String ojosHundidos, String bebeMal, String bebeAvidamente, String pliegueCutaneo, String obsDiarrea, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_diarrea_2(comatoso, intranquilo, ojos_hundidos, bebe_mal, bebe_avidamente, pliegue_cutaneo, observaciones_diarrea, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, comatoso);
		 			ps.setString(2, intranquilo);
		 			ps.setString(3, ojosHundidos);
		 			ps.setString(4, bebeMal);
		 			ps.setString(5, bebeAvidamente);
		 			ps.setString(6, pliegueCutaneo);
		 			ps.setString(7, obsDiarrea);	
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarDiarreaDos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarFiebre
		 public void insertarFiebre(String tieneFiebre, String desdeCuandoFiebreDias, String fiebre38Centigrados, String fiebre39Centigrados,String visitoUlt15Dias, String zonaDengue, String zonaMalaria, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre(tiene_fiebre, desde_cuando_fiebre_dias, fiebre_38_centigrados, fiebre_39_centigrados,visito_ult_15_dias, zona_dengue, zona_malaria, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, tieneFiebre);
		 			ps.setString(2, desdeCuandoFiebreDias);
		 			ps.setString(3, fiebre38Centigrados);
		 			ps.setString(4, fiebre39Centigrados);
		 			ps.setString(5, visitoUlt15Dias);
		 			ps.setString(6, zonaDengue);
		 			ps.setString(7, zonaMalaria);
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarFiebre "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarFiebreDos
		 public void insertarFiebreDos(String rigidezNuca, String aparienciaEnfermoGrave, String manifestacionesSangrado, String aspectoToxico, String respuestaSocial, String piel, String erupcionCutanea, String dolorAbdominal, String cefalea, String mialgias, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre_2(rigidez_nuca, apariencia_enfermo_grave, manifestaciones_sangrado, aspecto_toxico, respuesta_social, piel, erupcion_cutanea, dolor_abdominal, cefalea, mialgias, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, rigidezNuca);
		 			ps.setString(2, aparienciaEnfermoGrave);
		 			ps.setString(3, manifestacionesSangrado);
		 			ps.setString(4, aspectoToxico);
		 			ps.setString(5, respuestaSocial);
		 			ps.setString(6, piel);
		 			ps.setString(7, erupcionCutanea);
		 			ps.setString(8, dolorAbdominal);
		 			ps.setString(9, cefalea);
		 			ps.setString(10, mialgias);		
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarFiebreDos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarFiebreTres
		 public void insertarFiebreTres(String artralgias, String dolorRetroocular, String postracion, String pruebaTorniquete, String lipotimia, String hepatomegalia, String pulsoRapido, String llenadoCapilar, String ascitis, String disminucionDiuresis, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre_3(artralgias, dolor_retroocular, postracion, prueba_torniquete, lipotimia, hepatomegalia, pulso_rapido, llenado_capilar, ascitis, disminucion_diuresis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, artralgias);
		 			ps.setString(2, dolorRetroocular);
		 			ps.setString(3, postracion);
		 			ps.setString(4, pruebaTorniquete);
		 			ps.setString(5, lipotimia);
		 			ps.setString(6, hepatomegalia);
		 			ps.setString(7, pulsoRapido);
		 			ps.setString(8, llenadoCapilar);
		 			ps.setString(9, ascitis);
		 			ps.setString(10, disminucionDiuresis);		
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarFiebreTres "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarFiebreCuatro
		 public void insertarFiebreCuatro(String cuadroHematico, String leucocitos, String neutrofilos, String plaquetas, String parcialOrinaInfeccion, String gotaGruesaPositiva, String obsFiebre, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_fiebre_4(cuadro_hematico, leucocitos, neutrofilos, plaquetas, parcial_orina_infeccion, gota_gruesa_positiva, observaciones_fiebre, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, cuadroHematico);
		 			ps.setString(2, leucocitos);
		 			ps.setString(3, neutrofilos);
		 			ps.setString(4, plaquetas);
		 			ps.setString(5, parcialOrinaInfeccion);
		 			ps.setString(6, gotaGruesaPositiva);
		 			ps.setString(7, obsFiebre);
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarFiebreCuatro "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarOido
		 public void insertarOido(String tieneProblemaOido, String dolorOido, String supuracion, String desdeCuandoSupuracionDias, String numEpisodiosPrevios, String tumefaccionDetrasOreja, String timpanoRojo, String supuracionOido, String obsOido, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_oido(tiene_problema_oido, dolor_oido, supuracion, desde_cuando_supuracion_dias, num_episodios_previos, tumefaccion_detras_oreja, timpano_rojo, supuracion_oido, observaciones_oido, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, tieneProblemaOido);
		 			ps.setString(2, dolorOido);
		 			ps.setString(3, supuracion);
		 			ps.setString(4, desdeCuandoSupuracionDias);
		 			ps.setString(5, numEpisodiosPrevios);
		 			ps.setString(6, tumefaccionDetrasOreja);
		 			ps.setString(7, timpanoRojo);
		 			ps.setString(8, supuracionOido);
		 			ps.setString(9, obsOido);
		 			ps.setString(10, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarOido "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarGarganta
		 public void insertarGarganta(String tieneProblemaGarganta, String dolorGarganta, String gangliosCuelloDolorosos, String amigdalasEritematosas, String exudadoBlanquecino, String obsGarganta, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_garganta(tiene_problema_garganta, dolor_garganta, ganglios_cuello_dolorosos, amigdalas_eritematosas, exudado_blanquecino, observaciones_garganta, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
		 			ps.setString(1, tieneProblemaGarganta);
		 			ps.setString(2, dolorGarganta);
		 			ps.setString(3, gangliosCuelloDolorosos);
		 			ps.setString(4, amigdalasEritematosas);
		 			ps.setString(5, exudadoBlanquecino);
		 			ps.setString(6, obsGarganta);	
		 			ps.setString(7, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarGarganta "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarSaludBucal
		 public void insertarSaludBucal(String dolorAlComer, String dolorDiente, String traumaCara, String padresCaries, String limpiaBocaManana, String limpiaBocaTarde, String limpiaBocaNoche, String limpiaDientes, String ninoSoloLimpiaDientes, String utilizaCepillo, String utilizaCrema, String utilizaChupo, String consultaOdontologica, String obsTos, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_salud_bucal(dolor_al_comer, dolor_diente, trauma_cara, padres_caries, limpia_boca_manana, limpia_boca_tarde, limpia_boca_noche, limpia_dientes, nino_solo_limpia_dientes, utiliza_cepillo, utiliza_crema, utiliza_seda, utiliza_chupo, consulta_odontologica, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, dolorAlComer);
		 			ps.setString(2, dolorDiente);
		 			ps.setString(3, traumaCara);
		 			ps.setString(4, padresCaries);
		 			ps.setString(5, limpiaBocaManana);
		 			ps.setString(6, limpiaBocaTarde);
		 			ps.setString(7, limpiaBocaNoche);
		 			ps.setString(8, limpiaDientes);
		 			ps.setString(9, ninoSoloLimpiaDientes);
		 			ps.setString(10, utilizaCepillo);	
		 			ps.setString(11, utilizaCrema);
		 			ps.setString(12, utilizaChupo);
		 			ps.setString(13, consultaOdontologica);
		 			ps.setString(14, obsTos);
		 			ps.setString(15, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarSaludBucal "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarSaludBucalDos
		 public void insertarSaludBucalDos(String inflamacionDolorosaLabio, String noInvolucraSurco, String enrojecimiento, String inflamacionEncia, String localizado, String generalizado, String deformacionEncia, String exudadoPus, String vesiculas, String ulceras, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_salud_bucal_2(inflamacion_dolorosa_labio, no_involucra_surco, enrojecimiento, inflamacion_encia, localizado, generalizado, deformacion_encia, exudado_pus, vesiculas, ulceras, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, inflamacionDolorosaLabio);
		 			ps.setString(2, noInvolucraSurco);
		 			ps.setString(3, enrojecimiento);
		 			ps.setString(4, inflamacionEncia);
		 			ps.setString(5, localizado);
		 			ps.setString(6, generalizado);
		 			ps.setString(7, deformacionEncia);
		 			ps.setString(8, exudadoPus);
		 			ps.setString(9, vesiculas);
		 			ps.setString(10, ulceras);		
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarSaludBucalDos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarSaludBucalTres
		 public void insertarSaludBucalTres(String placas, String fractura, String movilidad, String desplazamiento, String extrusion, String intrusion, String avulsion, String herida, String manchasBlancas, String cafes, String cariesCavitacionales, String placaBacteriana, String obsSaludBucal, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_salud_bucal_3(placas, fractura, movilidad, desplazamiento, extrusion, intrusion, avulsion, herida, manchas_blancas, cafes, caries_cavitacionales, placa_bacteriana, observaciones_salud_bucal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, placas);
		 			ps.setString(2, fractura);
		 			ps.setString(3, movilidad);
		 			ps.setString(4, desplazamiento);
		 			ps.setString(5, extrusion);
		 			ps.setString(6, intrusion);
		 			ps.setString(7, avulsion);
		 			ps.setString(8, herida);
		 			ps.setString(9, manchasBlancas);
		 			ps.setString(10, cafes);	
		 			ps.setString(11, cariesCavitacionales);
		 			ps.setString(12, placaBacteriana);
		 			ps.setString(13, obsSaludBucal);	
		 			ps.setString(14, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarSaludBucalTres "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarCrecimiento
		 public void insertarCrecimiento(String emanacionVisible, String pesoEdad, String edemaAmbosPies, String apariencia, String imcEdad, String tallaEdad, String pesoTalla, String tendenciaPeso, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_crecimiento(emanacion_visible, peso_edad, edema_ambos_pies, apariencia, imc_edad, talla_edad, peso_talla, tendencia_peso, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, emanacionVisible);
		 			ps.setString(2, pesoEdad);
		 			ps.setString(3, edemaAmbosPies);
		 			ps.setString(4, apariencia);
		 			ps.setString(5, imcEdad);
		 			ps.setString(6, tallaEdad);
		 			ps.setString(7, pesoTalla);
		 			ps.setString(8, tendenciaPeso);	
		 			ps.setString(9, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarCrecimiento "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarCrecimientoDos
		 public void insertarCrecimientoDos(String desnutricionGlobalSevera, String desnutricionGlobal, String riesgoDesnutricion, String pesoAdecuadoEdad, String desnutricionCronica, String riesgoDNT, String tallaAdecuadaEdad, String desnutricionAgudaSevera, String DNTAguda, String pesoAdecuadoTalla, String sobrepeso, String obesidad, String obsCrecimiento, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_crecimiento_2(desnutricion_global_severa, desnutricion_global, riesgo_desnutricion, peso_adecuado_edad, desnutricion_cronica, riesgo_dnt, talla_adecuada_edad, desnutricion_aguda_severa, dnt_aguda, peso_adecuado_talla, sobrepeso, obesidad, observaciones_crecimiento, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, desnutricionGlobalSevera);
		 			ps.setString(2, desnutricionGlobal);
		 			ps.setString(3, riesgoDesnutricion);
		 			ps.setString(4, pesoAdecuadoEdad);
		 			ps.setString(5, desnutricionCronica);
		 			ps.setString(6, riesgoDNT);
		 			ps.setString(7, tallaAdecuadaEdad);
		 			ps.setString(8, desnutricionAgudaSevera);
		 			ps.setString(9, DNTAguda);
		 			ps.setString(10, pesoAdecuadoTalla);	
		 			ps.setString(11, sobrepeso);
		 			ps.setString(12, obesidad);
		 			ps.setString(13, obsCrecimiento);	
		 			ps.setString(14, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarCrecimientoDos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarAnemia
		 public void insertarAnemia(String recHierroUlt6Meses, String cuandoRecHierro, String cuantoTiempoRecHierro, String palidezPalmar, String palidezConjuntival, String obsAnemia, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_anemia(recibido_hierro_ult_6_meses, cuando_recibio_hierro, cuanto_tiempo_recibio_hierro, palidez_palmar, palidez_conjuntival, observaciones_anemia, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
		 			ps.setString(1, recHierroUlt6Meses);
		 			ps.setString(2, cuandoRecHierro);
		 			ps.setString(3, cuantoTiempoRecHierro);
		 			ps.setString(4, palidezPalmar);
		 			ps.setString(5, palidezConjuntival);
		 			ps.setString(6, obsAnemia);
		 			ps.setString(7, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarAnemia "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltrato
		 public void insertarMaltrato(String comoProdujeronLesiones, String relataMaltrato, String maltratoFisico, String maltratoSexual, String negligencia, String quienMaltrato, String incongruenciaTrauma, String incongruenciaLesionNino, String diferentesVersiones, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato(como_produjeron_lesiones, relata_maltrato, maltrato_fisico, maltrato_sexual, negligencia, quien_maltrato, incongruencia_trauma, incongruencia_lesion_nino, diferentes_versiones, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, comoProdujeronLesiones);
		 			ps.setString(2, relataMaltrato);
		 			ps.setString(3, maltratoFisico);
		 			ps.setString(4, maltratoSexual);
		 			ps.setString(5, negligencia);
		 			ps.setString(6, quienMaltrato);
		 			ps.setString(7, incongruenciaTrauma);
		 			ps.setString(8, incongruenciaLesionNino);
		 			ps.setString(9, diferentesVersiones);
		 			ps.setString(10, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltrato "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoDos
		 public void insertarMaltratoDos(String consultaTardia, String frecuenciaPegaHijo, String desobedienteHijo, String comportamientoAnormalPadres, String descuidoNinoSalud, String porqueDescuidoNinoSalud, String enQueDescuidoNino, String factorRiesgo, String actitudAnormalNino, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_2(consulta_tardia, frecuencia_pega_hijo, desobediente_hijo, comportamiento_anormal_padres, descuido_nino_salud, porque_descuido_nino_salud, en_que_descuido_nino, factor_riesgo, actitud_anormal_nino, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, consultaTardia);
		 			ps.setString(2, frecuenciaPegaHijo);
		 			ps.setString(3, desobedienteHijo);
		 			ps.setString(4, comportamientoAnormalPadres);
		 			ps.setString(5, descuidoNinoSalud);
		 			ps.setString(6, porqueDescuidoNinoSalud);
		 			ps.setString(7, enQueDescuidoNino);
		 			ps.setString(8, factorRiesgo);
		 			ps.setString(9, actitudAnormalNino);
		 			ps.setString(10, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoDos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoTres
		 public void insertarMaltratoTres(String equimosis, String hematomas, String lasceraciones, String mordiscos, String cicatrices, String diferenteEvolucionNinos, String sugestivasMaltrato, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_3(equimosis, hematomas, lasceraciones, mordiscos, cicatrices, dif_evolucion_ninos, sugestivas_maltrato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, equimosis);
		 			ps.setString(2, hematomas);
		 			ps.setString(3, lasceraciones);
		 			ps.setString(4, mordiscos);
		 			ps.setString(5, cicatrices);
		 			ps.setString(6, diferenteEvolucionNinos);
		 			ps.setString(7, sugestivasMaltrato);
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoTres "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoCuatro
		 public void insertarMaltratoCuatro(String traumaVisceral, String traumaGrave, String lesionFisicaSugestiva, String sangradoVaginal, String juegoSexual, String bocaGenitales, String vih, String gonorrea, String sifilis, String trichomona, String chlamydiaTrachomatis, String condilomatosis, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_4(trauma_visceral, trauma_grave, lesion_fisica_sugestiva, sangrado_vaginal, juego_sexual, boca_genitales, vih, gonorrea, sifilis, trichomona, chlamydia_trachomatis, condilomatosis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, traumaVisceral);
		 			ps.setString(2, traumaGrave);
		 			ps.setString(3, lesionFisicaSugestiva);
		 			ps.setString(4, sangradoVaginal);
		 			ps.setString(5, juegoSexual);
		 			ps.setString(6, bocaGenitales);
		 			ps.setString(7, vih);
		 			ps.setString(8, gonorrea);
		 			ps.setString(9, sifilis);
		 			ps.setString(10, trichomona);	
		 			ps.setString(11, chlamydiaTrachomatis);
		 			ps.setString(12, condilomatosis);
		 			ps.setString(13, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoCuatro "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoCinco
		 public void insertarMaltratoCinco(String temeroso, String retraido, String rechazoAdulto, String deprimido, String evitaContactoVisual, String trastornoSueno, String trastornoAlimentario, String problemasPsicosomaticos, String conductasRegresivas, String desarrolloEstancado, String violenciaIntrafamiliar, String familiaCaotica, String cuidadoresAdictos, String obsMaltrato, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_5(temeroso, retraido, rechazo_adulto, deprimido, evita_contacto_visual, trastorno_sueno, trastorno_alimentario, problemas_psicosomaticos, conductas_regresivas, desarrollo_estancado, violencia_intrafamiliar, familia_caotica, cuidadores_adictos, observaciones_maltrato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, temeroso);
		 			ps.setString(2, retraido);
		 			ps.setString(3, rechazoAdulto);
		 			ps.setString(4, deprimido);
		 			ps.setString(5, evitaContactoVisual);
		 			ps.setString(6, trastornoSueno);
		 			ps.setString(7, trastornoAlimentario);
		 			ps.setString(8, problemasPsicosomaticos);
		 			ps.setString(9, conductasRegresivas);
		 			ps.setString(10, desarrolloEstancado);	
		 			ps.setString(11, violenciaIntrafamiliar);
		 			ps.setString(12, familiaCaotica);
		 			ps.setString(13, cuidadoresAdictos);
		 			ps.setString(14, obsMaltrato);
		 			ps.setString(15, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoCinco "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoCraneo
		 public void insertarMaltratoCraneo(String fracturas, String hematomas, String hemorragiasRetinianas, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_craneo(fracturas, hematomas, hemorragias_retinianas, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?)");				
		 			ps.setString(1, fracturas);
		 			ps.setString(2, hematomas);
		 			ps.setString(3, hemorragiasRetinianas);
		 			ps.setString(4, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoCraneo "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoFracturas
		 public void insertarMaltratoFracturas(String costillas, String huesosLargos, String espirales, String oblicua, String metafisiarias, String esternon, String escapula, String menorCincoAnos, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_fracturas(costillas, huesos_largos, espirales, oblicua, metafisiarias, esternon, escapula, menor_cinco_anos, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, costillas);
		 			ps.setString(2, huesosLargos);
		 			ps.setString(3, espirales);
		 			ps.setString(4, oblicua);
		 			ps.setString(5, metafisiarias);
		 			ps.setString(6, esternon);
		 			ps.setString(7, escapula);
		 			ps.setString(8, menorCincoAnos);
		 			ps.setString(9, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoFracturas "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoQuemaduras
		 public void insertarMaltratoQuemaduras(String areasCubiertasRopa, String patronSimetricoDemarcado, String denotaObjetoQuemadura, String quemaduraCuerpo, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_quemaduras(areas_cubiertas_ropa, patron_simetrico_demarcado, denota_objeto_quemadura, quemadura_cuerpo, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?)");				
		 			ps.setString(1, areasCubiertasRopa);
		 			ps.setString(2, patronSimetricoDemarcado);
		 			ps.setString(3, denotaObjetoQuemadura);
		 			ps.setString(4, quemaduraCuerpo);
		 			ps.setString(5, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoQuemaduras "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarMaltratoTraumaGenital
		 public void insertarMaltratoTraumaGenital(String lasceracionAguda, String lasceracionPerianal, String ausenciaHimen, String himenCicatrizado, String cicatrizNavicular, String anoDilatado, String hallazgoSemen, String flujoGenital, String cuerpoExtranoVaginaAno, String vesiculas, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_maltrato_trauma_genital(lasceracion_aguda, lasceracion_perianal, ausencia_himen, himen_cicatrizado, cicatriz_navicular, ano_dilatado, hallazgo_semen, flujo_genital, cuerpo_extrano_vagina_ano, vesiculas, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, lasceracionAguda);
		 			ps.setString(2, lasceracionPerianal);
		 			ps.setString(3, ausenciaHimen);
		 			ps.setString(4, himenCicatrizado);
		 			ps.setString(5, cicatrizNavicular);
		 			ps.setString(6, anoDilatado);
		 			ps.setString(7, hallazgoSemen);
		 			ps.setString(8, flujoGenital);
		 			ps.setString(9, cuerpoExtranoVaginaAno);
		 			ps.setString(10, vesiculas);	
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarMaltratoTraumaGenital "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarDesarrollo
		 public void insertarDesarrollo(String antImportante, String antDesarrollo, String factorRiesgoAnt, String perimetroCefalico, String alteracionesFenotipicas, String menosDosDe, String masDosDe, String obsDesarrollo, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_desarrollo(antecedente_importante, antecedente_para_desarrollo, factor_riesgo_antecedente, perimetro_cefalico, alteraciones_fenotipicas, menos_dos_de, mas_dos_de, observaciones_desarrollo, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, antImportante);
		 			ps.setString(2, antDesarrollo);
		 			ps.setString(3, factorRiesgoAnt);
		 			ps.setString(4, perimetroCefalico);
		 			ps.setString(5, alteracionesFenotipicas);
		 			ps.setString(6, menosDosDe);
		 			ps.setString(7, masDosDe);
		 			ps.setString(8, obsDesarrollo);
		 			ps.setString(9, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarDesarrollo "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarDesarrolloCondiciones
		 public void insertarDesarrolloCondiciones(String realizaUnaCondicionEdad, String realizaDosCondicionEdad, String realizaTresCondicionEdad, String realizaCuatroCondicionEdad, String ausenciaUnaCondicionEdad, String ausenciaDosCondicionEdad, String ausenciaTresCondicionEdad, String ausenciaCuatroCondicionEdad, String ausenciaUnaCondicionGrupoAnterior, String ausenciaDosCondicionGrupoAnterior, String ausenciaTresCondicionGrupoAnterior, String ausenciaCuatroCondicionGrupoAnterior, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_desarrollo_condiciones(realiza_una_condicion_edad, realiza_dos_condicion_edad, realiza_tres_condicion_edad, realiza_cuatro_condicion_edad, ausencia_una_condicion_edad, ausencia_dos_condicion_edad, ausencia_tres_condicion_edad, ausencia_cuatro_condicion_edad, ausencia_una_condicion_grupo_anterior, ausencia_dos_condicion_grupo_anterior, ausencia_tres_condicion_grupo_anterior, ausencia_cuatro_condicion_grupo_anterior, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, realizaUnaCondicionEdad);
		 			ps.setString(2, realizaDosCondicionEdad);
		 			ps.setString(3, realizaTresCondicionEdad);
		 			ps.setString(4, realizaCuatroCondicionEdad);
		 			ps.setString(5, ausenciaUnaCondicionEdad);
		 			ps.setString(6, ausenciaDosCondicionEdad);
		 			ps.setString(7, ausenciaTresCondicionEdad);
		 			ps.setString(8, ausenciaCuatroCondicionEdad);
		 			ps.setString(9, ausenciaUnaCondicionGrupoAnterior);
		 			ps.setString(10, ausenciaDosCondicionGrupoAnterior);	
		 			ps.setString(11, ausenciaTresCondicionGrupoAnterior);
		 			ps.setString(12, ausenciaCuatroCondicionGrupoAnterior);
		 			ps.setString(13, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarDesarrolloCondiciones "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarAntecedentesVacunacion
		 public void insertarAntecedentesVacunacion(String BCG1, String hepatitisBRN, String hepatitisB1, String hepatitisB2, String hepatitisB3, String DPT1, String DPT2, String DPT3, String DPTR1, String DPTR2, String VOP1, String VOP2, String VOP3, String VOPR1, String VOPR2, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_ant_vacunacion(bcg_1, hepatitis_b_rn, hepatitis_b_1, hepatitis_b_2, hepatitis_b_3, dpt_1, dpt_2, dpt_3, dpt_r1, dpt_r2, vop_1, vop_2, vop_3, vop_r1, vop_r2, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, BCG1);
		 			ps.setString(2, hepatitisBRN);
		 			ps.setString(3, hepatitisB1);
		 			ps.setString(4, hepatitisB2);
		 			ps.setString(5, hepatitisB3);
		 			ps.setString(6, DPT1);
		 			ps.setString(7, DPT2);
		 			ps.setString(8, DPT3);
		 			ps.setString(9, DPTR1);
		 			ps.setString(10, DPTR2);	
		 			ps.setString(11, VOP1);
		 			ps.setString(12, VOP2);
		 			ps.setString(13, VOP3);
		 			ps.setString(14, VOPR1);
		 			ps.setString(15, VOPR2);
		 			ps.setString(16, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarAntecedentesVacunacion "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarAntecedentesVacunacionDos
		 public void insertarAntecedentesVacunacionDos(String SRP1, String SRP2, String rotavirus1, String rotavirus2, String streptococoNeumoniae1, String streptococoNeumoniae2, String streptococoNeumoniae3, String haemophilus1, String haemophilus2, String haemophilus3, String haemophilusR1, String haemophilusR2, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_ant_vacunacion_2(srp_1, srp_2, rotavirus_1, rotavirus_2, streptococo_neumoniae_1, streptococo_neumoniae_2, streptococo_neumoniae_3, haemophilus_1, haemophilus_2, haemophilus_3, haemophilus_r1, haemophilus_r2, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, SRP1);
		 			ps.setString(2, SRP2);
		 			ps.setString(3, rotavirus1);
		 			ps.setString(4, rotavirus2);
		 			ps.setString(5, streptococoNeumoniae1);
		 			ps.setString(6, streptococoNeumoniae2);
		 			ps.setString(7, streptococoNeumoniae3);
		 			ps.setString(8, haemophilus1);
		 			ps.setString(9, haemophilus2);
		 			ps.setString(10, haemophilus3);	
		 			ps.setString(11, haemophilusR1);
		 			ps.setString(12, haemophilusR2);
		 			ps.setString(13, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarAntecedentesVacunacionDos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarAntecedentesVacunacionTres
		 public void insertarAntecedentesVacunacionTres(String influenzaDosis, String fiebreAmarillaEdad, String otrasVacunas, String vacunasPendientes, String proximasVacunas, String tiempoVacunaMeses, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_ant_vacunacion_3(influenza_dosis, fiebre_amarilla_edad, otras_vacunas, vacunas_pendientes, proximas_vacunas, tiempo_vacuna_meses, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
		 			ps.setString(1, influenzaDosis);
		 			ps.setString(2, fiebreAmarillaEdad);
		 			ps.setString(3, otrasVacunas);
		 			ps.setString(4, vacunasPendientes);
		 			ps.setString(5, proximasVacunas);
		 			ps.setString(6, tiempoVacunaMeses);
		 			ps.setString(7, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarAntecedentesVacunacionTres "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarCompletarExamenFisico
		 public void insertarCompletarExamenFisico(String completarExamenFisico, String otroProblemaDetectado, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_completar_exa_fisico(completar_examen_fisico, otro_problema_detectado, id_informe_aiepi_2a5_fk) VALUES(?,?,?)");				
		 			ps.setString(1, completarExamenFisico);
		 			ps.setString(2, otroProblemaDetectado);	
		 			ps.setString(3, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarCompletarExamenFisico "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarAlimentacion
		 public void insertarAlimentacion(String lecheMaterna, String cuantasVecesLecMat24H, String recibePechoNoche, String extraeLeche, String comoGuardaLeche, String menor6MesesRecOtraLeche, String queAlimentosRecibe, String cuantasVecesRecAlimentos, String conQueRecAlimento, String quienDaComer, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_alimentacion(leche_materna, cuantas_veces_lec_mat_24h, recibe_pecho_noche, extrae_leche, como_guarda_leche, menor_6_meses_rec_otra_leche, que_alimentos_recibe, cuantas_veces_rec_alimentos, con_que_rec_alimento, quien_da_comer, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, lecheMaterna);
		 			ps.setString(2, cuantasVecesLecMat24H);
		 			ps.setString(3, recibePechoNoche);
		 			ps.setString(4, extraeLeche);
		 			ps.setString(5, comoGuardaLeche);
		 			ps.setString(6, menor6MesesRecOtraLeche);
		 			ps.setString(7, queAlimentosRecibe);
		 			ps.setString(8, cuantasVecesRecAlimentos);
		 			ps.setString(9, conQueRecAlimento);
		 			ps.setString(10, quienDaComer);	
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarAlimentacion "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarAlimentacionDos
		 public void insertarAlimentacionDos(String enfermo, String queComioEnfermedad, String obeso, String padresHermanosObesos, String ninoEjercicio, String asisteProgramaNutricional, String obsAlimentacion, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_alimentacion_2(enfermo, que_comio_enfermedad, obeso, padres_hermanos_obesos, nino_ejercicio, asiste_prog_nutricional, observaciones_alimentacion, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 		    ps.setString(1, enfermo);
		 		   	ps.setString(2, queComioEnfermedad);
		 		    ps.setString(3, obeso);
		 			ps.setString(4, padresHermanosObesos);
		 			ps.setString(5, ninoEjercicio);
		 			ps.setString(6, asisteProgramaNutricional);
		 			ps.setString(7, obsAlimentacion);	
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarAlimentacionDos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarAlimentacionMayorSeisMeses
		 public void insertarAlimentacionMayorSeisMeses(String cuantasComidasRecAyer, String tamanoPorcionesAyer, String comidasEspesasAyer, String comidaOrigenAnimal, String productosLacteosAyer, String legumbresAyer, String vegetalesAyer, String aceiteComidaAyer, String quienDioComidaAyer, String ninoComePropioPlato, String ninoRecVitaminas, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_alimentacion_mayor_6_meses(cuantas_comidas_rec_ayer, tamano_porciones_ayer, comidas_espesas_ayer, comida_origen_animal, productos_lacteos_ayer, legumbres_ayer, vegetales_ayer, aceite_comida_ayer, quien_dio_comida_ayer, nino_come_propio_plato, nino_rec_vitaminas, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, cuantasComidasRecAyer);
		 			ps.setString(2, tamanoPorcionesAyer);
		 			ps.setString(3, comidasEspesasAyer);
		 			ps.setString(4, comidaOrigenAnimal);
		 			ps.setString(5, productosLacteosAyer);
		 			ps.setString(6, legumbresAyer);
		 			ps.setString(7, vegetalesAyer);
		 			ps.setString(8, aceiteComidaAyer);
		 			ps.setString(9, quienDioComidaAyer);
		 			ps.setString(10, ninoComePropioPlato);	
		 			ps.setString(11, ninoRecVitaminas);
		 			ps.setString(12, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarAlimentacionMayorSeisMeses "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRecomendacionesDosCinco
		 public void insertarRecomendacionesDosCinco(String diagnosticoPac, String codigoCIEPac, String tratamientoPac, String signosAlarma, String volverConsControl, String dondeConsControl, String volverConsNinoSano, String referidoConsulta, String recomendacionesDesarrollo, String recomendacionesBuenTrato, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_recomendaciones(diagnostico_pac, codigo_cie_pac, tratamiento_pac, signos_alarma, volver_cons_control, donde_cons_control, volver_cons_nino_sano, referido_consulta, recomendaciones_desarrollo, recomendaciones_buen_trato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, diagnosticoPac);
		 			ps.setString(2, codigoCIEPac);
		 			ps.setString(3, tratamientoPac);
		 			ps.setString(4, signosAlarma);
		 			ps.setString(5, volverConsControl);
		 			ps.setString(6, dondeConsControl);
		 			ps.setString(7, volverConsNinoSano);
		 			ps.setString(8, referidoConsulta);
		 			ps.setString(9, recomendacionesDesarrollo);
		 			ps.setString(10, recomendacionesBuenTrato);	
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRecomendacionesDosCinco "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasAnemia
		 public void insertarRespuestasAnemia(String anemiaSevera, String anemia, String noTieneAnemia, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_anemia(anemia_severa, anemia, no_tiene_anemia, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?)");				
		 			ps.setString(1, anemiaSevera);
		 			ps.setString(2, anemia);
		 			ps.setString(3, noTieneAnemia);	
		 			ps.setString(4, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasAnemia "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasCrecimiento
		 public void insertarRespuestasCrecimiento(String obeso, String sobrepeso, String desnutricionSevera, String desnutricion, String riesgoDesnutricion, String adecuadoEstadoNutriconal, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_crecimiento(obeso, sobrepeso, desnutricion_severa, desnutricion, riesgo_desnutricion, adecuado_estado_nutriconal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?)");				
		 			ps.setString(1, obeso);
		 			ps.setString(2, sobrepeso);
		 			ps.setString(3, desnutricionSevera);
		 			ps.setString(4, desnutricion);
		 			ps.setString(5, riesgoDesnutricion);
		 			ps.setString(6, adecuadoEstadoNutriconal);
		 			ps.setString(7, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasCrecimiento "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasDesarrolloDosCinco
		 public void insertarRespuestasDesarrolloDosCinco(String probableRetrasoDesarrollo, String riesgoProblemaDesarrollo, String desarrolloNormalFactorRiesgo, String desarrolloNormal, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_desarrollo(probable_retraso_desarrollo, riesgo_problema_desarrollo, desarrollo_normal_factor_riesgo, desarrollo_normal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?)");				
		 			ps.setString(1, probableRetrasoDesarrollo);
		 			ps.setString(2, riesgoProblemaDesarrollo);
		 			ps.setString(3, desarrolloNormalFactorRiesgo);
		 			ps.setString(4, desarrolloNormal);
		 			ps.setString(5, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasDesarrolloDosCinco "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasDiarrea
		 public void insertarRespuestasDiarrea(String deshidratacionGrave, String algunGradoDeshidratacion, String altoRiesgoDeshidratacion, String sinDeshidratacion, String diarreaPersistenteGrave, String diarreaPersistente, String disenteria, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_diarrea(deshidratacion_grave, algun_grado_deshidratacion, alto_riesgo_deshidratacion, sin_deshidratacion, diarrea_persistente_grave, diarrea_persistente, disenteria, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, deshidratacionGrave);
		 			ps.setString(2, algunGradoDeshidratacion);
		 			ps.setString(3, altoRiesgoDeshidratacion);
		 			ps.setString(4, sinDeshidratacion);
		 			ps.setString(5, diarreaPersistenteGrave);
		 			ps.setString(6, diarreaPersistente);
		 			ps.setString(7, disenteria);
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasDiarrea "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasFiebre
		 public void insertarRespuestasFiebre(String enfFebrilRiesgoAlto, String enfFebrilRiesgoIntermedio, String enfFebrilRiesgoBajo, String malariaComplicada, String malaria, String dengueGrave, String dengueSignosAlarma, String probableDengue, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_fiebre(enf_febril_riesgo_alto, enf_febril_riesgo_intermedio, enf_febril_riesgo_bajo, malaria_complicada, malaria, dengue_grave, dengue_signos_alarma, probable_dengue, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, enfFebrilRiesgoAlto);
		 			ps.setString(2, enfFebrilRiesgoIntermedio);
		 			ps.setString(3, enfFebrilRiesgoBajo);
		 			ps.setString(4, malariaComplicada);
		 			ps.setString(5, malaria);
		 			ps.setString(6, dengueGrave);
		 			ps.setString(7, dengueSignosAlarma);
		 			ps.setString(8, probableDengue);
		 			ps.setString(9, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasFiebre "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasGarganta
		 public void insertarRespuestasGarganta(String faringoamigdalitis, String estreptococica, String faringoamigdalitisViral, String noTieneFaringoamigdalitis, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_garganta(faringoamigdalitis, estreptococica, faringoamigdalitis_viral, no_tiene_faringoamigdalitis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?)");				
		 			ps.setString(1, faringoamigdalitis);
		 			ps.setString(2, estreptococica);
		 			ps.setString(3, faringoamigdalitisViral);
		 			ps.setString(4, noTieneFaringoamigdalitis);	
		 			ps.setString(5, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasGarganta "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasMaltrato
		 public void insertarRespuestasMaltrato(String maltratoFisicoMuyGrave, String abusoGrave, String maltratoFisicoResp, String sospechaAbusoSexual, String maltratoEmocional, String negligenciaAbandono, String noSospechaMaltrato, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_maltrato(maltrato_fisico_muy_grave, abuso_grave, maltrato_fisico_resp, sospecha_abuso_sexual, maltrato_emocional, negligencia_abandono, no_sospecha_maltrato, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, maltratoFisicoMuyGrave);
		 			ps.setString(2, abusoGrave);
		 			ps.setString(3, maltratoFisicoResp);
		 			ps.setString(4, sospechaAbusoSexual);
		 			ps.setString(5, maltratoEmocional);
		 			ps.setString(6, negligenciaAbandono);
		 			ps.setString(7, noSospechaMaltrato);
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasMaltrato "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasOido
		 public void insertarRespuestasOido(String mastoiditis, String otitisMediaCronica, String otitisMediaRecurrente, String otitisMediaAguda, String noTieneOtitis, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_oido(mastoiditis, otitis_media_cronica, otitis_media_recurrente, otitis_media_aguda, no_tiene_otitis, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?)");				
		 			ps.setString(1, mastoiditis);
		 			ps.setString(2, otitisMediaCronica);
		 			ps.setString(3, otitisMediaRecurrente);
		 			ps.setString(4, otitisMediaAguda);
		 			ps.setString(5, noTieneOtitis);	
		 			ps.setString(6, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasOido "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasSaludBucal
		 public void insertarRespuestasSaludBucal(String celulitisFacial, String enfBucalGrave, String traumaBucodental, String estomatitis, String enfDentalGingival, String altoRiesgoEnfBucal, String bajoRiesgoEnfBucal, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_salud_bucal(celulitis_facial, enf_bucal_grave, trauma_bucodental, estomatitis, enf_dental_gingival, alto_riesgo_enf_bucal, bajo_riesgo_enf_bucal, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, celulitisFacial);
		 			ps.setString(2, enfBucalGrave);
		 			ps.setString(3, traumaBucodental);
		 			ps.setString(4, estomatitis);
		 			ps.setString(5, enfDentalGingival);
		 			ps.setString(6, altoRiesgoEnfBucal);
		 			ps.setString(7, bajoRiesgoEnfBucal);
		 			ps.setString(8, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasSaludBucal "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasSignosPeligro
		 public void insertarRespuestasSignosPeligro(String enfMuyGrave, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_signos_peligro(enf_muy_grave, id_informe_aiepi_2a5_fk) VALUES(?,?)");				
		 			ps.setString(1, enfMuyGrave);	
		 			ps.setString(2, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasSignosPeligro "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRespuestasTos
		 public void insertarRespuestasTos(String grupGrave, String bronquiolitisGrave, String sibilanciaGrave, String crup, String sibilanciaRecurrente, String neumoniaGrave, String neumonia, String tos, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_respuestas_tos(grup_grave, bronquiolitis_grave, sibilancia_grave, crup, sibilancia_recurrente, neumonia_grave, neumonia, tos, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, grupGrave);
		 			ps.setString(2, bronquiolitisGrave);
		 			ps.setString(3, sibilanciaGrave);
		 			ps.setString(4, crup);
		 			ps.setString(5, sibilanciaRecurrente);
		 			ps.setString(6, neumoniaGrave);
		 			ps.setString(7, neumonia);
		 			ps.setString(8, tos);	
		 			ps.setString(9, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRespuestasTos "+ex);
		 		}
		 				
		 	}
		 
			//Método insertarRecomendacionesDosis
		 public void insertarRecomendacionesDosis(String recVitaminaA, String proxDosisVitaminaA, String recAlbendazol, String proxDosisAlbendazol, String recHierro, String cuandoRecHierro, String volverRecHierro, String recZinc, String cuantoTiempoZinc, String iniciaZinc, String codReporte){	 
		 		
		 		try{				
		 			PreparedStatement ps = null;
		 		    Conexion con=new Conexion();
		 		    ps=con.conn.prepareStatement("INSERT INTO aiepi_2a5_recomendaciones_dosis(rec_vitamina_a, prox_dosis_vitamina_a, rec_albendazol, prox_dosis_albendazol, rec_hierro, cuando_rec_hierro, volver_rec_hierro, rec_zinc, cuanto_tiempo_zinc, inicia_zinc, id_informe_aiepi_2a5_fk) VALUES(?,?,?,?,?,?,?,?,?,?,?)");				
		 			ps.setString(1, recVitaminaA);
		 			ps.setString(2, proxDosisVitaminaA);
		 			ps.setString(3, recAlbendazol);
		 			ps.setString(4, proxDosisAlbendazol);
		 			ps.setString(5, recHierro);
		 			ps.setString(6, cuandoRecHierro);
		 			ps.setString(7, volverRecHierro);
		 			ps.setString(8, recZinc);
		 			ps.setString(9, cuantoTiempoZinc);
		 			ps.setString(10, iniciaZinc);	
		 			ps.setString(11, codReporte);
		 	
		 			ps.execute();
		 			ps.close();
		 			con.cerrar();
		 					
		 		}catch(Exception ex){
		 			ex.getMessage();
		 			System.out.print("Error en MetodoMultiplePacientes>>insertarRecomendacionesDosis "+ex);
		 		}
		 				
		 	}*/
		 
		 
		 /*********************************SNAPSHOT*********************************************/
			public java.sql.ResultSet VerificarAlergiasLimite(String CodPac){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT alergia FROM hic_alergias WHERE codpac_fk="+CodPac+" LIMIT 5 ");
					//System.out.println("SELECT alergia FROM hic_alergias WHERE codpac_fk="+CodPac+" LIMIT 5 ");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarAlergiasLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet VerificarMedicamentosLimite(String CodPac){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT CONCAT(med.nombre,'/',med.concentracion) AS Medicamento FROM hic_formulacion hfr,hic_detalle_formulacion hdf,medicamentos med WHERE hfr.CodPac_fk="+CodPac+" AND hfr.codigo=hdf.CodFormulacion_fk AND hdf.CodMedicamento_fk=med.codigo LIMIT 5 ");
					System.out.println("SELECT CONCAT(med.nombre,'/',med.concentracion) AS Medicamento FROM hic_formulacion hfr,hic_detalle_formulacion hdf,medicamentos med WHERE hfr.CodPac_fk="+CodPac+" AND hfr.codigo=hdf.CodFormulacion_fk AND hdf.CodMedicamento_fk=med.codigo LIMIT 5 ");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarMedicamentosLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet VerificarLaboratoriosLimite(String CodPac){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT * FROM(SELECT DISTINCT lres.fecha,lres.hora,lex.codigo AS CodExamen,lpac.pac_codigo_paciente AS CodPac,lres.codigo AS CodResul,CONCAT('') AS genero,lex.nombre AS NombreExamen,CONCAT('') AS valorinicial,CONCAT('') AS valorfinal,CONCAT('1')AS Tipo FROM adm_paciente lpac,lab_resultado lres,lab_examen lex WHERE lpac.pac_codigo_paciente="+CodPac+" AND lpac.pac_codigo_paciente=lres.codpac_fk AND lres.codexamen_fk=lex.codigo AND lex.tipo=0  AND lex.codigosubarea_fk=0 AND lres.estado=1 UNION SELECT DISTINCT lres.fecha,lres.hora,lex.codigo AS CodExamen,lpac.pac_codigo_paciente AS CodPac,lres.codigo AS CodResul,CONCAT('') AS genero,lex.nombre AS NombreExamen,lran.valorinicial,lran.valorfinal,CONCAT('2')AS Tipo FROM adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_tipo_rango ltr WHERE lpac.pac_codigo_paciente=lres.codpac_fk AND lres.codexamen_fk=lex.codigo AND lex.tipo=1 AND lran.codtiporango_fk=ltr.codigo AND ltr.codexamen_fk=lex.codigo AND lex.codigosubarea_fk=0 AND lex.codigoarea_fk>0 AND lpac.pac_codigo_paciente="+CodPac+" AND ltr.tiporango='GENERAL' AND lres.estado=1 UNION SELECT DISTINCT lres.fecha,lres.hora,lsub.codigo AS CodExamen,lpac.pac_codigo_paciente AS CodPac,CONCAT('') AS CodResul,CONCAT('genero1') AS genero,lsub.nombre AS NombreExamen,CONCAT('') AS valorinicial,CONCAT('') AS valorfinal,CONCAT('3')AS Tipo FROM lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub WHERE lar.codigo=lsub.codarea_fk AND lex.codigosubarea_fk=lsub.codigo AND lpac.pac_codigo_paciente="+CodPac+" AND lres.codexamen_fk=lex.codigo AND lpac.pac_codigo_paciente=lres.codpac_fk AND lres.estado=1 AND lex.codigosubarea_fk!=0 AND lres.resultado!='' ) AS Consulta ORDER BY Consulta.fecha DESC LIMIT 5 ");
					System.out.println("SELECT * FROM(SELECT DISTINCT lres.fecha,lres.hora,lex.codigo AS CodExamen,lpac.pac_codigo_paciente AS CodPac,lres.codigo AS CodResul,CONCAT('') AS genero,lex.nombre AS NombreExamen,CONCAT('') AS valorinicial,CONCAT('') AS valorfinal,CONCAT('1')AS Tipo FROM adm_paciente lpac,lab_resultado lres,lab_examen lex WHERE lpac.pac_codigo_paciente="+CodPac+" AND lpac.pac_codigo_paciente=lres.codpac_fk AND lres.codexamen_fk=lex.codigo AND lex.tipo=0  AND lex.codigosubarea_fk=0 AND lres.estado=1 UNION SELECT DISTINCT lres.fecha,lres.hora,lex.codigo AS CodExamen,lpac.pac_codigo_paciente AS CodPac,lres.codigo AS CodResul,CONCAT('') AS genero,lex.nombre AS NombreExamen,lran.valorinicial,lran.valorfinal,CONCAT('2')AS Tipo FROM adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_rango lran,lab_tipo_rango ltr WHERE lpac.pac_codigo_paciente=lres.codpac_fk AND lres.codexamen_fk=lex.codigo AND lex.tipo=1 AND lran.codtiporango_fk=ltr.codigo AND ltr.codexamen_fk=lex.codigo AND lex.codigosubarea_fk=0 AND lex.codigoarea_fk>0 AND lpac.pac_codigo_paciente="+CodPac+" AND ltr.tiporango='GENERAL' AND lres.estado=1 UNION SELECT DISTINCT lres.fecha,lres.hora,lsub.codigo AS CodExamen,lpac.pac_codigo_paciente AS CodPac,CONCAT('') AS CodResul,CONCAT('genero1') AS genero,lsub.nombre AS NombreExamen,CONCAT('') AS valorinicial,CONCAT('') AS valorfinal,CONCAT('3')AS Tipo FROM lab_area lar,adm_paciente lpac,lab_resultado lres,lab_examen lex,lab_subarea lsub WHERE lar.codigo=lsub.codarea_fk AND lex.codigosubarea_fk=lsub.codigo AND lpac.pac_codigo_paciente="+CodPac+" AND lres.codexamen_fk=lex.codigo AND lpac.pac_codigo_paciente=lres.codpac_fk AND lres.estado=1 AND lex.codigosubarea_fk!=0 AND lres.resultado!='' ) AS Consulta ORDER BY Consulta.fecha DESC LIMIT 5 ");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarLaboratoriosLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet VerificarImagenesLimite(String CodPac){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT ice.fecha,ice.hora,isu.nombre,ice.codigo,sgu.usu_codigo FROM img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt ,adm_convenio acv,adm_entidad aen WHERE igr.codigo=isu.cod_gruFk AND isu.codigo=ice.codigoExa_fk AND ipac.pac_codigo_paciente=ice.codigoPac_fk AND ice.estado=1 AND sdt.dat_codigo=sgu.dat_codigo_fk AND sgu.usuario=ice.aprobacion AND aen.ent_nit=acv.ent_nit_contratante_fk AND ipac.pac_codigo_paciente="+CodPac+" AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk ORDER BY ice.codigo DESC LIMIT 5");
					System.out.println("SELECT ice.fecha,ice.hora,isu.nombre,ice.codigo,sgu.usu_codigo FROM img_grupo igr,img_citas_espera ice,adm_paciente ipac,img_subexa isu,seg_usuario sgu,seg_datos_personales sdt ,adm_convenio acv,adm_entidad aen WHERE igr.codigo=isu.cod_gruFk AND isu.codigo=ice.codigoExa_fk AND ipac.pac_codigo_paciente=ice.codigoPac_fk AND ice.estado=1 AND sdt.dat_codigo=sgu.dat_codigo_fk AND sgu.usuario=ice.aprobacion AND aen.ent_nit=acv.ent_nit_contratante_fk AND ipac.pac_codigo_paciente="+CodPac+" AND acv.conv_numero_contrato=ipac.conv_numero_contrato_fk ORDER BY ice.codigo DESC LIMIT 5");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarImagenesLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet VerificarEcoLimite(String CodPac){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT fecha_informe, hora_informe, 'ECOCARDIOGRAMA TRANS TORÁCICO' AS nombreExamen, idInformeEcocardio, codusuario  FROM eco_encabezado_informe WHERE codpaciente='"+CodPac+"' AND estado='1' ORDER BY idInformeEcocardio DESC LIMIT 5");
					System.out.println("SELECT fecha_informe, hora_informe, 'ECOCARDIOGRAMA TRANS TORÁCICO' AS nombreExamen, idInformeEcocardio, codusuario  FROM eco_encabezado_informe WHERE codpaciente='"+CodPac+"' AND estado='1' ORDER BY idInformeEcocardio DESC LIMIT 5");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarEcoLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet VerificarRmcLimite(String CodPac){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT rmc.fecha_informe, rmc.hora_informe, rmcind.nombre_indicacion, rmc.idInformeEcocardio, rmc.codusuario  FROM rmc_encabezado_informe rmc, rmc_indicaciones rmcind WHERE rmc.codpaciente='"+CodPac+"' AND rmc.estado='1' AND rmcind.id_indicacion=rmc.id_indicacion_fk ORDER BY rmc.idInformeEcocardio DESC LIMIT 5");
					System.out.println("SELECT rmc.fecha_informe, rmc.hora_informe, rmcind.nombre_indicacion, rmc.idInformeEcocardio, rmc.codusuario  FROM rmc_encabezado_informe rmc, rmc_indicaciones rmcind WHERE rmc.codpaciente='"+CodPac+"' AND rmc.estado='1' AND rmcind.id_indicacion=rmc.id_indicacion_fk ORDER BY rmc.idInformeEcocardio DESC LIMIT 5");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarRmcLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet VerificarFormatosLimite(String CodAdmision, String CodPaciente){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora,sdp.nombre,sdp.apellido,hafp.codigo_usu_fk,hafp.codigo_pac_fk,hafp.codigo,hafp.estado,hf.repetido  FROM hic_formato hf,adm_admisiones adm,hic_adm_formatos_pac hafp,seg_usuario sus,seg_datos_personales sdp WHERE hf.codigo=hafp.codigo_for_fk AND adm.adm_numero_ingreso=hafp.codigo_adm_fk AND adm.pac_codigo_paciente_fk =hafp.codigo_pac_fk AND hafp.codigo_pac_fk='"+CodPaciente+"' AND hafp.codigo_adm_fk='"+CodAdmision+"' AND hafp.codigo_usu_fk=sus.usu_codigo AND sdp.dat_codigo=sus.dat_codigo_fk ORDER BY hafp.codigo DESC LIMIT 5");
					System.out.println("SELECT hf.codigo,hf.nombre_formato,hafp.fecha,hafp.hora,sdp.nombre,sdp.apellido,hafp.codigo_usu_fk,hafp.codigo_pac_fk,hafp.codigo,hafp.estado,hf.repetido  FROM hic_formato hf,adm_admisiones adm,hic_adm_formatos_pac hafp,seg_usuario sus,seg_datos_personales sdp WHERE hf.codigo=hafp.codigo_for_fk AND adm.adm_numero_ingreso=hafp.codigo_adm_fk AND adm.pac_codigo_paciente_fk =hafp.codigo_pac_fk AND hafp.codigo_pac_fk='"+CodPaciente+"' AND hafp.codigo_adm_fk='"+CodAdmision+"' AND hafp.codigo_usu_fk=sus.usu_codigo AND sdp.dat_codigo=sus.dat_codigo_fk ORDER BY hafp.codigo DESC LIMIT 5");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarFormatosLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet VerificarOrdenesMedicasLimite(String CodAdmision){
				java.sql.ResultSet rs=null;
				Statement st=null;
				try{
					Conexion con=new Conexion();
					st=con.conn.createStatement();
					rs=st.executeQuery("SELECT * FROM (SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('1')AS Tipo FROM hic_orden hod,hic_detalleordenlaboratorio hdl,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdl.CodOrdLab_fk AND hod.TipoOrden=1 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('2')AS Tipo FROM hic_orden hod,hic_detalleordenimagenes hdi,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdi.CodOrdImg_fk AND hod.TipoOrden=2 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hfor.codigo,CONCAT(hfor.fecha,'/',hfor.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('3')AS Tipo FROM hic_formulacion hfor,hic_detalle_formulacion hdf,seg_usuario su,seg_datos_personales sdt WHERE hfor.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hfor.codigo=hdf.CodFormulacion_fk AND hfor.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('4')AS Tipo FROM hic_orden hod,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.TipoOrden=4 AND hod.CodAdm_fk="+CodAdmision+" ) AS Consulta ORDER BY Consulta.Fecha DESC LIMIT 5 ");
					System.out.println("SELECT * FROM (SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('1')AS Tipo FROM hic_orden hod,hic_detalleordenlaboratorio hdl,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdl.CodOrdLab_fk AND hod.TipoOrden=1 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('2')AS Tipo FROM hic_orden hod,hic_detalleordenimagenes hdi,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.codigo=hdi.CodOrdImg_fk AND hod.TipoOrden=2 AND hod.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hfor.codigo,CONCAT(hfor.fecha,'/',hfor.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('3')AS Tipo FROM hic_formulacion hfor,hic_detalle_formulacion hdf,seg_usuario su,seg_datos_personales sdt WHERE hfor.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hfor.codigo=hdf.CodFormulacion_fk AND hfor.CodAdm_fk="+CodAdmision+" UNION SELECT DISTINCT hod.codigo,CONCAT(hod.fecha,'/',hod.hora) AS Fecha, CONCAT(sdt.nombre,' ',sdt.apellido) AS Usuario,CONCAT(sdt.profesion,' ',sdt.ocupacion) AS profesion,CONCAT('4')AS Tipo FROM hic_orden hod,seg_usuario su,seg_datos_personales sdt WHERE hod.CodUsu_fk=su.usu_codigo AND su.dat_codigo_fk=sdt.dat_codigo AND hod.TipoOrden=4 AND hod.CodAdm_fk="+CodAdmision+" ) AS Consulta ORDER BY Consulta.Fecha DESC LIMIT 5 ");
				}
				catch(Exception ex){
					ex.getMessage();
					System.out.println("Error MetodoAiepiMultiplePacientes>>VerificarOrdenesMedicasLimite "+ex);
				}
				return rs;
			}
			
			public java.sql.ResultSet CargarImagenologiasPendientes(String CodigoPaciente, String codAdm){	
				/**
				 */
		        java.sql.ResultSet rs=null;
		        Statement st = null;
		        try{
		        	Conexion con=new Conexion();
		        	st = con.conn.createStatement();
		        	rs=st.executeQuery("SELECT isu.nombre AS ExamenesPendientes FROM img_citas_espera ice,img_subexa isu, adm_admisiones adm WHERE  isu.codigo=ice.codigoExa_fk  AND ice.estado=-1 AND isu.nombre NOT LIKE '%RESONANCIA NUCLEAR MAGNETICA DE CORAZON %'  AND codigoPac_fk='"+CodigoPaciente+"' AND adm.adm_numero_ingreso='"+codAdm+"'  AND ice.fecha_cita BETWEEN adm.fecha_registro AND CURDATE()");
		        }
		        catch(Exception ex){
		        	System.out.println("Error en MetodoAiepiMultiplePacientes>>CargarImagenologiasPendientes "+ex);
		        }	
		        return rs;
		    }
		 
		 


}
