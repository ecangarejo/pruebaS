/**
 * logica: MetodoMultiplePacientes se encuentran las inserciones,consultas y actualizaciones
 * para la creacion del menu en forma de pestañas con los multiples pacientes
 * con su respectivo menu de opciones.
*/
package hic_metodo;

import hic_bean.CrearAntecedentes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import adm_logica.Conexion;

public class MetodoMultiplePacientes {
	
	public void InsertarEpicrisis(String CodAdm1,String codusu,String CodPac,
			String Resumen,String Manejo,String ProcEst,Time hora,Date fecha,String servicio){
		try{
			PreparedStatement ps = null;
			Conexion con=new Conexion();
			ps=con.conn.prepareStatement("INSERT INTO hic_epicrisis(codadm,codpac,codusu,hora,fecha,procedimiento,manejo,resumen,servicio)VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, CodAdm1);
			ps.setString(2, CodPac);
			ps.setString(3, codusu);
			ps.setTime(4, hora);	
			ps.setDate(5, fecha);
			ps.setString(6, ProcEst);
			ps.setString(7, Manejo);
			ps.setString(8, Resumen);
			ps.setString(9, servicio);	
			ps.executeUpdate();
			ps.close();
			con.cerrar();				
	}catch(Exception ex){
		System.out.println("Error MetodoMultiplePacientes>>InsertarEpicrisis "+ex);
	}
	   
}
	public java.sql.ResultSet BuscarDatosEpicrisis(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT CONCAT(apac.tipo_documento,'-',apac.numero_documento) AS Documento,apac.primer_apellido,apac.segundo_apellido,apac.nombre,aent.nombre_entidad," +
					"(YEAR(CURDATE())-YEAR(apac.fecha_nacimiento)) AS Edad,apac.genero,aad.fecha_registro,adir.nombre_area AS ServicioIngreso,hdp.fecha AS fecha_egreso,acc.pabellon AS ServicioEgreso " +
					"FROM adm_paciente apac,adm_admisiones aad,adm_convenio acv,adm_entidad aent,adm_direccionamiento adir,hic_destinopaciente hdp,adm_censo_cama acc " +
					"WHERE aad.adm_numero_ingreso="+CodAdm+" AND apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk " +
					"AND apac.conv_numero_contrato_fk=acv.conv_numero_contrato AND aent.ent_nit=acv.ent_nit_contratante_fk " +
					"AND adir.dir_codigo=aad.dir_codigo_fk AND hdp.codAdm=aad.adm_numero_ingreso " +
					"AND hdp.codPac=apac.pac_codigo_paciente AND hdp.DestinoPaciente='SALIDA DE HOSPITALIZACION'" +
					"AND aad.cen_numero_cama_fk=acc.cen_numero_cama ");
					
				}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>BuscarDatosEpicrisis "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet BuscarDiagnosticosEpicrisis(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT GROUP_CONCAT(ci.nombre,'-',hde.codDiagnostico,'') AS diag_egre," +
					"(SELECT GROUP_CONCAT(ci.nombre,'-',hdi.codDiagnostico,'') AS diag_ing " +
					" FROM hic_diagnosticoingreso hdi,cie10 ci " +
					"WHERE hdi.codAdm="+CodAdm+" " +
					"AND hdi.CodDiag_fk=ci.codigo) AS Diag_Ingre " +
					"FROM hic_diagnosticoegreso hde,cie10 ci " +
					"WHERE hde.codAdm="+CodAdm+" " +
					"AND hde.CodDiag_fk=ci.codigo ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>BuscarGeneroPaciente "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet BuscarEpicrisis(String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT * FROM hic_epicrisis hep WHERE hep.codadm="+CodAdm+"");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>BuscarEpicrisis "+ex);
		}
		return rs;
	}
	
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
			System.out.println("Error MetodoMultiplePacientes>>BuscarGeneroPaciente "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet SqlPruebaRips(){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT tipo_documento,numero_documento,nombre,primer_apellido,segundo_apellido,genero FROM adm_paciente");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>SqlPruebaRips "+ex);
		}
		return rs;
	}
	
	public void ActualizarGinecobstetra(String CodigoGineco,String gestas,String partos,String abortos,String cesareas,String espontaneos,String menarquia,String fum,String furs,String ivs,String its,String compasexual){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("UPDATE hic_ginecobstetra SET gestas='"+gestas+"',partos='"+partos+"',abortos='"+abortos+"',cesareas='"+cesareas+"',espontaneos='"+espontaneos+"',menarquia='"+menarquia+"',fum='"+fum+"',furs='"+furs+"',ivs='"+ivs+"',its='"+its+"',compasexual='"+compasexual+"' WHERE codigo='"+CodigoGineco+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>ActualizarGinecobstetra "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirAnteceMedicamento(String CodigoMedicamento){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_medicamentos where codigo='"+CodigoMedicamento+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>OmitirAnteceMedicamento "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirTranfucion(String CodigoTranfucion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_transfuciones where codigo='"+CodigoTranfucion+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>OmitirTranfucion "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirAntecedenteFamiliar(String CodigoAntFamiliar){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_antfamiliar where codigo='"+CodigoAntFamiliar+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>OmitirAntecedenteFamiliar "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirToxico(String CodigoTx){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_toxico where codigo='"+CodigoTx+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>OmitirToxico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	
	public void OmitirAlergico(String CodigoAler){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_alergias where codigo='"+CodigoAler+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>OmitirAlergico "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirQx(String CodigoQx){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from hic_quirurjicos where codigo='"+CodigoQx+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>OmitirQx "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void OmitirAntPersonal(String CodigoAntPersonal){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("delete from adm_diagnosticocola where codigo='"+CodigoAntPersonal+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>OmitirAntPersonal "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	
	public void ActualizarObservacionAntecedentePersonal(String Diagnos,String observacion){
	    PreparedStatement st = null;
	     try{
	     	Conexion con=new Conexion();
	     	st= con.conn.prepareStatement("update adm_diagnosticocola set observacion='"+observacion+"' where codigo='"+Diagnos+"'");
	     	st.executeUpdate();	 
	     	st.close();
	     	con.cerrar();
	     }
	     catch(Exception ex){
	     	System.out.println("ERROR EN MetodoMultiplePacientes>>ActualizarObservacionAntecedentePersonal "+ex);
	     	ex.getMessage();	     
	     }	
	 }
	public java.sql.ResultSet VerificarTransfu(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select transfusion,cantidad,observacion,fechaTrans,codigo from hic_transfuciones where cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarTransfu "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarToxico(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select htx.tipo,htx.observacion,htx.codigo from hic_toxico htx where htx.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarToxico "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarAntFamiliares(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select haf.familiar,cie.nombre,haf.codigo,haf.observacion from hic_antfamiliar haf,cie10 cie where haf.codigoCIE_fk=cie.codigoCIE and haf.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarAntFamiliares "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerTiposAlergias(){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT * FROM hic_tipo_alergia ORDER BY TipoAlergia");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerTiposAlergias "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarAlergias(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select alergia,reaccion,codigo from hic_alergias where cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarAlergias "+ex);
		}
		return rs;
	}
	public java.sql.ResultSet VerificarDiagnosticos(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT cie.nombre,adi.fecha,adi.hora,adi.codigo,adi.observacion FROM cie10 cie,adm_diagnosticocola adi WHERE cie.codigo=adi.codigoCIE_fk and adi.cedpac_fk ='"+CedPac+"' order by adi.codigo desc ");

		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarDatosAdmision "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarQx(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select hqx.observacionQx,fechaQx,map.servicio,hqx.codigo from hic_quirurjicos hqx,mapipo map where map.codigo=hqx.codigoQx_fk and hqx.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarQx "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet Verificarmedicamentos(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select hmd.observacionMedi,med.nombre,med.concentracion,hmd.codigo from hic_medicamentos hmd,medicamentos med where med.codigo=hmd.codigoMedi_fk and hmd.cedpac_fk='"+CedPac+"'");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>Verificarmedicamentos "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarGinecobstetra(String CedPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT codigo,gestas,partos,abortos,cesareas,espontaneos,menarquia,fum,furs,ivs,its,compasexual FROM hic_ginecobstetra WHERE CedPac_fk="+CedPac+" ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarGinecobstetra "+ex);
		}
		return rs;
	}
	
	public void InsertarGinecobstetra(String gestas,String partos,String abortos,String cesareas,String espontaneos,String menarquia,String fum,String furs,String ivs,String its,String compasexual,String CodPac,String CodUsu,String fecha ,String hora,String cedula){
		/**
		 * creamos los antecedentes ginecologicos de las pacientes.
		 * 
		 */
	CrearAntecedentes cad = new CrearAntecedentes();
	cad.setCodPac(CodPac);
	cad.setCodUsu(CodUsu);
	cad.setfecha(fecha);
	cad.sethora(hora);
	cad.setcedula(cedula); 
	   
	try{
		PreparedStatement ps = null;
		Conexion con=new Conexion();
		ps=con.conn.prepareStatement("INSERT INTO hic_ginecobstetra(gestas,partos,abortos,cesareas,espontaneos,menarquia,fum,furs,ivs,its,compasexual,CodPac_fk,CedPac_fk,CodUsu_fk,hora,fecha)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, gestas);
		ps.setString(2, partos);
		ps.setString(3, abortos);
		ps.setString(4, cesareas);
		ps.setString(5, espontaneos);
		ps.setString(6, menarquia);
		ps.setString(7, fum);
		ps.setString(8, furs);
		ps.setString(9, ivs);
		ps.setString(10, its);
		ps.setString(11, compasexual);
		ps.setString(12, cad.getCodPac());
		ps.setString(13, cad.getcedula());
		ps.setString(14, cad.getCodUsu());
		ps.setString(15, cad.gethora());
		ps.setString(16, cad.getfecha());
			    
		ps.executeUpdate();
		ps.close();
		con.cerrar();				
	}catch(Exception ex){
		System.out.println("Error MetodoMultiplePacientes>>InsertarGinecobstetra "+ex);
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
		System.out.println("Error MetodoMultiplePacientes>>InsertarMedicamentos "+ex);
	}
	   
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
		System.out.println("Error MetodoMultiplePacientes>>InsertarProcedimientos "+ex);
	}
	   
}	
	public ResultSet AutocompletarQuirurjicos(String cod) throws Exception {
		/**
		 */
		  java.sql.ResultSet r=null;
	        Statement st = null;
	       Conexion con=new Conexion();
	        	st = con.conn.createStatement();
	        	r=st.executeQuery("select servicio,codigo from mapipo where estado=0 and servicio LIKE '%"+cod+"%'");
	        return r;
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
		System.out.println("Error MetodoMultiplePacientes>>InsertarToxicologia "+ex);
	}
	   
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
		   System.out.println("Error MetodoMultiplePacientes>>InsertarTransfuciones "+ex);
	   }
	   
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
				System.out.println("Error MetodoMultiplePacientes>>InsertarAntefamiliar "+ex);
			}

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
				System.out.println("Error MetodoMultiplePacientes>>InsertarAlergia "+ex);
			}

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
			System.out.println("Error MetodoMultiplePacientes>>VerObservacionDiagnosticoFamiliar "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerObservacionDiagnostico "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarDatosAdmision(String CodPac,String CodAdm){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido,(YEAR(CURDATE())-YEAR(pac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(pac.fecha_nacimiento, 5)) AS edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,acc.pabellon,acc.ubicacion_cama,acc.codigocama,aen.nombre_entidad,acc.cen_numero_cama,aar.codigo AS CodArea,asub.codigo AS CodSubarea,aen.ent_nit,fef.cod_enc_factura,amt.medico_texto FROM adm_paciente pac,adm_admisiones adm,adm_censo_cama acc,adm_convenio acv,adm_entidad aen,adm_area aar,adm_subarea asub,fact_enc_factura fef,adm_medico_tratante amt WHERE pac.pac_codigo_paciente='"+CodPac+"' AND adm.adm_numero_ingreso='"+CodAdm+"' AND adm.cen_numero_cama_fk=acc.cen_numero_cama AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=pac.conv_numero_contrato_fk AND aar.codigo=asub.codigoarea AND asub.codigo=acc.codsubarea AND fef.cod_admision=adm.adm_numero_ingreso AND fef.valor=0  AND amt.codAdm_fk=adm.adm_numero_ingreso AND amt.estado=0 ");
			System.out.println("SELECT pac.nombre,pac.primer_apellido,pac.segundo_apellido,(YEAR(CURDATE())-YEAR(pac.fecha_nacimiento))-(RIGHT(CURDATE(),5)<RIGHT(pac.fecha_nacimiento, 5)) AS edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,acc.pabellon,acc.ubicacion_cama,acc.codigocama,aen.nombre_entidad,acc.cen_numero_cama,aar.codigo AS CodArea,asub.codigo AS CodSubarea,aen.ent_nit,fef.cod_enc_factura,amt.medico_texto FROM adm_paciente pac,adm_admisiones adm,adm_censo_cama acc,adm_convenio acv,adm_entidad aen,adm_area aar,adm_subarea asub,fact_enc_factura fef,adm_medico_tratante amt WHERE pac.pac_codigo_paciente='"+CodPac+"' AND adm.adm_numero_ingreso='"+CodAdm+"' AND adm.cen_numero_cama_fk=acc.cen_numero_cama AND aen.ent_nit=acv.ent_nit_contratante_fk AND acv.conv_numero_contrato=pac.conv_numero_contrato_fk AND aar.codigo=asub.codigoarea AND asub.codigo=acc.codsubarea AND fef.cod_admision=adm.adm_numero_ingreso AND fef.valor=0  AND amt.codAdm_fk=adm.adm_numero_ingreso AND amt.estado=0 ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarDatosAdmision "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarPermisosHC(String usuario){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT codigoPerhc_fk FROM hic_usuariopermisoshc WHERE codigoUsu_fk="+usuario+" order by codigoPerhc_fk");
			System.out.println("SELECT codigoPerhc_fk FROM hic_usuariopermisoshc WHERE codigoUsu_fk="+usuario+" order by codigoPerhc_fk");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarPermisosHC "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet VerificarAdmision(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT aad.adm_numero_ingreso FROM adm_admisiones aad,adm_paciente apac WHERE apac.pac_codigo_paciente=aad.pac_codigo_paciente_fk and apac.pac_codigo_paciente='"+CodPac+"' and aad.estado=0 ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>VerificarAdmision "+ex);
		}
		return rs;
	}
	
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarAlergiasLimite "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarMedicamentosLimite "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarLaboratoriosLimite "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarImagenesLimite "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarRmcLimite "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarEcoLimite "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarOrdenesMedicasLimite "+ex);
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
			System.out.println("Error MetodoMultiplePacientes>>VerificarFormatosLimite "+ex);
		}
		return rs;
	}
	
	
	
	public java.sql.ResultSet DatosPaciente(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT apac.nombre,apac.primer_apellido,apac.segundo_apellido,apac.pac_codigo_paciente,apac.numero_documento FROM adm_paciente apac WHERE apac.pac_codigo_paciente="+CodPac+"");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>ExamenesRealizados "+ex);
		}
		return rs;
	}
	
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
			System.out.println("Error MetodoMultiplePacientes>>ExamenesRealizados "+ex);
		}
		return rs;
	}
	
	public java.sql.ResultSet DatosCompletosPaciente(String CodPac){
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("select pac.nombre,pac.primer_apellido,pac.segundo_apellido,(year(curdate())-year(pac.fecha_nacimiento))-(right(curdate(),5)<right(pac.fecha_nacimiento, 5)) as edad,pac.fecha_nacimiento,pac.tipo_documento,pac.numero_documento,aen.nombre_entidad from adm_paciente pac,adm_convenio acv,adm_entidad aen where pac.pac_codigo_paciente='"+CodPac+"' and aen.ent_nit=acv.ent_nit_contratante_fk and acv.conv_numero_contrato=pac.conv_numero_contrato_fk");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>DatosCompletosPaciente "+ex);
		}
		return rs;
	}
	public ResultSet listarPacientesPorAtender(String cod) {
		/**
		 * se buscan todos los pacientes que tienen admision en la base de datos
		 * tiene como parametro el nombre del paciente.
		 */
		
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT DISTINCT ap.pac_codigo_paciente,ap.nombre,ap.primer_apellido,ap.segundo_apellido,ap.tipo_documento,ap.numero_documento,aad.fecha_registro,aad.hora_registro, acc.servicio,CONCAT(acc.ubicacion_cama,'>>',acc.codigocama) AS ubicacion FROM  adm_paciente ap,adm_admisiones aad,adm_censo_cama acc WHERE (ap.nombre LIKE '%"+cod+"%' OR ap.primer_apellido LIKE '%"+cod+"%'  OR ap.segundo_apellido LIKE '%"+cod+"%' ) AND ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk AND aad.estado = 0 AND aad.cen_numero_cama_fk = acc.cen_numero_cama ORDER BY aad.fecha_registro,hora_registro ASC ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>listarPacientesPorAtender "+ex);
		}
		return rs;
	}
	
	public ResultSet listarPacientesPorAtenderSubarea(String cod) {
		System.out.println("SELECT DISTINCT ap.pac_codigo_paciente, ap.nombre,ap.primer_apellido,ap.segundo_apellido, ap.tipo_documento,ap.numero_documento,aad.fecha_registro, aad.hora_registro, acc.servicio,CONCAT(acc.ubicacion_cama,'>>',acc.codigocama) AS ubicacion FROM adm_paciente ap,adm_admisiones aad,adm_censo_cama acc WHERE  ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  AND aad.estado = 0  AND aad.cen_numero_cama_fk = acc.cen_numero_cama  AND acc.codsubarea="+cod+" ORDER BY acc.codigocama ASC ");
		/**
		 * se buscan todos los pacientes que tienen admision en la base de datos
		 * tiene como parametro el codigo de la subarea donde se encuentra el paciente.
		 */
		
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT DISTINCT ap.pac_codigo_paciente, ap.nombre,ap.primer_apellido,ap.segundo_apellido, ap.tipo_documento,ap.numero_documento,aad.fecha_registro, aad.hora_registro, acc.servicio,CONCAT(acc.ubicacion_cama,'>>',acc.codigocama) AS ubicacion FROM adm_paciente ap,adm_admisiones aad,adm_censo_cama acc WHERE  ap.pac_codigo_paciente = aad.pac_codigo_paciente_fk  AND aad.estado = 0  AND aad.cen_numero_cama_fk = acc.cen_numero_cama  AND acc.codsubarea="+cod+" ORDER BY acc.codigocama ASC ");
			
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>listarPacientesPorAtenderSubarea "+ex);
		}
		return rs;
	}
	public ResultSet BuscarUltimoFormatoPaciente(String CodAdm) {
		/**
		 * se busca el ultimo formato realizado al paciente.
		 */
		
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT CONCAT(TIMEDIFF(NOW(),(CONCAT(fecha,' ',hora))),'' )AS horas,hic_adm_formatos_pac.* FROM hic_adm_formatos_pac WHERE codigo_adm_fk='"+CodAdm+"'  ORDER BY codigo DESC LIMIT 1");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>BuscarUltimoFormatoPaciente "+ex);
		}
		return rs;
	}
	
	/*public ResultSet CargarlistarPacientesPorAtender() {
		/**
		 * se buscan todos los pacientes que tienen admision en la base de datos
		 * tiene como parametro el nombre del paciente.
		 */
		
		/*java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT DISTINCT ap.pac_codigo_paciente,ap.nombre,ap.primer_apellido,ap.segundo_apellido,ap.tipo_documento,ap.numero_documento,aad.fecha_registro,aad.hora_registro FROM adm_paciente ap,adm_admisiones aad WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.estado=0 AND aad.atendido=0 ORDER BY aad.fecha_registro,hora_registro ASC");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>CargarlistarPacientesPorAtender "+ex);
		}
		return rs;
	}*/
	
	public ResultSet ListarPacientesPorAtenderUnidad(String CodUnidad) {
		/**
		 * se buscan todos los pacientes que tienen admision en la base de datos
		 */
		
		java.sql.ResultSet rs=null;
		Statement st=null;
		try{
			Conexion con=new Conexion();
			st=con.conn.createStatement();
			rs=st.executeQuery("SELECT DISTINCT ap.pac_codigo_paciente,ap.nombre,ap.primer_apellido,ap.segundo_apellido,ap.tipo_documento,ap.numero_documento,aad.fecha_registro,aad.hora_registro,acc.servicio,CONCAT(acc.ubicacion_cama,'>>',acc.codigocama) AS ubicacion,DATEDIFF(CURDATE(),aad.fecha_registro) as DifeDia,concat(TIMEDIFF(aad.hora_registro,CURTIME()),'') AS DifeHora,aad.adm_numero_ingreso  FROM adm_paciente ap,adm_admisiones aad,adm_censo_cama acc WHERE ap.pac_codigo_paciente=aad.pac_codigo_paciente_fk AND aad.estado=0 AND aad.cen_numero_cama_fk=acc.cen_numero_cama AND acc.servicio="+CodUnidad+" ORDER BY aad.adm_numero_ingreso DESC ");
		}
		catch(Exception ex){
			ex.getMessage();
			System.out.println("Error MetodoMultiplePacientes>>ListarPacientesPorAtenderUnidad "+ex);
		}
		return rs;
	}
	
	
	//#####################
	
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
        	System.out.println("Error en MetodoAsignarCita>>CargarImagenologiasPendientes "+ex);
        }	
        return rs;
    }
	
	
	//###############
	

}
